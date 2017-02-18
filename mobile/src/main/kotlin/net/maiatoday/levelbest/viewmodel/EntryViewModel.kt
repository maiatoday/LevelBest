package net.maiatoday.levelbest.viewmodel

import android.databinding.BaseObservable
import android.databinding.Bindable
import io.realm.Realm
import io.realm.RealmChangeListener
import io.realm.RealmObject
import net.maiatoday.levelbest.BR
import net.maiatoday.levelbest.model.Entry
import net.maiatoday.levelbest.model.Mood
import net.maiatoday.levelbest.model.Tag
import java.util.*

/**
 * Entry View Model
 * Created by maia on 2017/02/12.
 */
class EntryViewModel(id: String) : BaseObservable() {
    val realm: Realm = Realm.getDefaultInstance()
    val uuid = id
    val entry: Entry = searchEntry(realm, uuid)
    val listener = RealmChangeListener<Entry> { notifyChange() }
    var title: String
        @Bindable
        get() {
            return entry.title
        }
        set(value) {
            // entry.title = value // need to do this in a transaction
            realm.executeTransactionAsync { r ->
                val e = searchEntry(r, uuid)
                e.title = value
            }
            notifyPropertyChanged(BR.title)
        }

    var note: String = entry.note
    var sentiment: Int = entry.sentiment
    var energyLevel: Int = entry.energyLevel

    init {
        RealmObject.addChangeListener(entry, listener)
    }

    fun searchEntry(aRealm: Realm, uuid: String): Entry {
        return aRealm.where(Entry::class.java).equalTo("id", uuid).findFirst()
    }

    fun close() {
        realm.close()
    }

    companion object {
        fun makeNewEntry(): String {
            val uuid = UUID.randomUUID().toString()
            val r: Realm = Realm.getDefaultInstance()
            r.executeTransaction({ realm ->
                val tag = realm.createObject(Tag::class.java, UUID.randomUUID().toString())
                tag.title = "Hello"
                val mood = realm.createObject(Mood::class.java, UUID.randomUUID().toString())
                mood.title = "World"

                val e = realm.createObject(Entry::class.java, uuid)
                e.timeStamp = Date()
                e.sentiment = 7
                e.energyLevel = -2
                e.moods.add(mood)
                e.tags.add(tag)
            }
            )
            r.close()
            return uuid
        }

    }
}
