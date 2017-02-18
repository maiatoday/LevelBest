package net.maiatoday.levelbest.model

import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import io.realm.annotations.Required

/**
 * Tag to sort entries
 * Created by maia on 2017/02/04.
 */

@RealmClass
open class Tag : RealmModel {

    @Required @PrimaryKey open var id: String = ""
    open var title: String = ""
}
