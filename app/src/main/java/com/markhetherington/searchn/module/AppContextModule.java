package com.markhetherington.searchn.module;

import com.markhetherington.searchn.SearchNApplication;

import dagger.Module;
import dagger.Provides;

@Module
public class AppContextModule {

    private final SearchNApplication mApplication;

    public AppContextModule(SearchNApplication mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    public SearchNApplication provideApplication() {
        return mApplication;
    }

}

