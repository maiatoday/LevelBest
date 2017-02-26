package net.maiatoday.levelbest.model

import java.util.Date

import io.realm.RealmList
import io.realm.RealmModel
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import io.realm.annotations.Required

/**
 * Mood/Energy data point entry
 * Created by maia on 2017/02/04.
 */
@RealmClass
open class Entry : RealmModel {
    @Required @PrimaryKey open var id: String = ""
    open var timeStamp: Date = Date()
    open var lat: Float = 0.0F
    open var long: Float = 0.0F
    open var tags: RealmList<Tag> = RealmList()
    open var moods: RealmList<Mood> = RealmList()
    open var sentiment: Int = 0
    open var energyLevel: Int = 0
    open var note:String = ""
    open var title:String = ""

    companion object {
        val TIMESTAMP = "timeStamp"

    }
}
