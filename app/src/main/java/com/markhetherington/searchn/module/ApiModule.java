package com.markhetherington.searchn.module;

import android.support.annotation.NonNull;

import com.markhetherington.searchn.SearchNApplication;
import com.markhetherington.searchn.network.DribbbleService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;
import retrofit.client.Client;
import retrofit.client.OkClient;

@Module
public class ApiModule {

    @Provides
    public DribbbleService provideDribbleService(RestAdapter restAdapter) {
        return restAdapter.create(DribbbleService.class);
    }

    @Provides
    public RestAdapter provideRestAdapter(Client client) {
        return new RestAdapter.Builder()
                .setClient(client)
                .setEndpoint("https://api.dribbble.com/v1")
                .build();
    }

    @Provides
    Client provideClient() {
        return new OkClient();
    }
}
