package com.markhetherington.searchn.component;

import com.markhetherington.searchn.SearchNApplication;
import com.markhetherington.searchn.module.ApiModule;
import com.markhetherington.searchn.module.AppContextModule;
import com.markhetherington.searchn.network.DribbbleService;

import dagger.Component;

@Component(modules = { AppContextModule.class, ApiModule.class})
public interface AppContextComponent {
    SearchNApplication provideApplication();
    DribbbleService provideDribbleService();
}
