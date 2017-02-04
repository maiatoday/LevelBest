package net.maiatoday.levelbest.di;

import net.maiatoday.levelbest.ui.MainActivity;

/**
 * Component for injection
 * Created by maia on 2016/07/25.
 */

public interface LBComponent {

    void inject(MainActivity target);
//    void inject(EntryActivity target);
}
