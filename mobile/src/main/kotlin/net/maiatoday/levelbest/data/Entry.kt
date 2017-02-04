package net.maiatoday.levelbest.data

import java.util.Date

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required

/**
 * Mood/Energy data point entry
 * Created by maia on 2017/02/04.
 */

open class Entry(
        @Required @PrimaryKey open var id: String = "",
        open var timeStamp: Date = Date(),
        open var where: String = "",
        open var tags: RealmList<Tag> = RealmList(),
        open var moods: RealmList<Mood> = RealmList(),
        open var sentiment: Int = 0,
        open var energyLevel: Int = 0
) : RealmObject() {
    // Realm overloads generated getters and setters
}
