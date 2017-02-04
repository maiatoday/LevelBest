package net.maiatoday.levelbest.data;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Tag to sort entries
 * Created by maia on 2017/02/04.
 */

public class Tag extends RealmObject {

    @Required
    @PrimaryKey
    String id;
    String title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
