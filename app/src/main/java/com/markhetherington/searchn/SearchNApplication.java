package com.markhetherington.searchn;

import android.app.Application;
import android.content.Context;

import com.markhetherington.searchn.component.AppContextComponent;
import com.markhetherington.searchn.component.ApplicationComponent;
import com.markhetherington.searchn.component.DaggerAppContextComponent;
import com.markhetherington.searchn.component.DaggerApplicationComponent;
import com.markhetherington.searchn.module.ApiModule;
import com.markhetherington.searchn.module.AppContextModule;

public class SearchNApplication extends Application {

    private ApplicationComponent mAppComponent;

    public static SearchNApplication get(Context context) {
        return (SearchNApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        AppContextComponent appContextComponent = DaggerAppContextComponent.builder()
                .appContextModule(new AppContextModule(this))
                .apiModule(new ApiModule()).build();
        mAppComponent = DaggerApplicationComponent.builder().appContextComponent(appContextComponent).build();
    }

    public ApplicationComponent getAppComponent() {
        return mAppComponent;
    }
}
