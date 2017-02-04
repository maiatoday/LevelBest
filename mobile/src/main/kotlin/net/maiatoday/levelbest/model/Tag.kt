package net.maiatoday.levelbest.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required

/**
 * Tag to sort entries
 * Created by maia on 2017/02/04.
 */

open class Tag : RealmObject() {

    @Required @PrimaryKey open var id: String = ""
    open var title: String = ""
}
