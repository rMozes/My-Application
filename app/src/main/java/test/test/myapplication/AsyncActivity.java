package test.test.myapplication;

import android.support.v4.app.Fragment;


/**
 * Created by BruSD on 9/30/2014.
 */
public class AsyncActivity extends SingleFragmentActivity {
    @Override
    public Fragment createFragment() {
        return AsyncFragment.newInstance();
    }
}
