package test.test.myapplication;

import android.content.Intent;
import android.support.v4.app.Fragment;

import test.test.myapplication.supp.Constants;

/**
 * Created by RICHI on 2014.10.01..
 */
public class DetailActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        Intent intent = getIntent();
        return DetailFragment.newInstance(intent.getStringExtra(Constants.EXTRA_NAME));
    }
}
