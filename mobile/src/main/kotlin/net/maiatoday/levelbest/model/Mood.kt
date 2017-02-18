package net.maiatoday.levelbest.model

import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import io.realm.annotations.Required

/**
 * Mood object describing a mood
 * Created by maia on 2017/02/04.
 */

@RealmClass
open class Mood : RealmModel {
    @Required @PrimaryKey open var id: String = ""
    open var title: String = ""
}
