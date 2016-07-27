package net.maiatoday.levelbest.data;

import net.maiatoday.levelbest.model.Entry;

/**
 * Interface to the entry store
 * Created by maia on 2016/07/27.
 */

public interface EntryStore {
    Entry getEntry(int id);
    void putEntry(Entry e);
}
