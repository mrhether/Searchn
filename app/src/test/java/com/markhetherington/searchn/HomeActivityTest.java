package com.markhetherington.searchn;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.assertj.android.api.Assertions.*;


@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class HomeActivityTest {

    @Test
    public void testActivitySetup() throws Exception {
        HomeActivity homeActivity = Robolectric.setupActivity(HomeActivity.class);
        assertThat(homeActivity).isNotNull();
    }

}