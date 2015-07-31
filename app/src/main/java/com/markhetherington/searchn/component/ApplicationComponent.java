package com.markhetherington.searchn.component;

import com.markhetherington.searchn.HomeActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(dependencies = {AppContextComponent.class})
public interface ApplicationComponent {
    void inject(HomeActivity activity);
}
