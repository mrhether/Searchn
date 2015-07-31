package com.markhetherington.searchn.component;

import com.markhetherington.searchn.SearchNApplication;
import com.markhetherington.searchn.module.AppContextModule;

import dagger.Component;

@Component(modules = { AppContextModule.class })
public interface AppContextComponent {
    SearchNApplication provideApplication();
}
