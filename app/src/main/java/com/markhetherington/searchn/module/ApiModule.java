package com.markhetherington.searchn.module;

import com.google.gson.GsonBuilder;
import com.markhetherington.searchn.network.DribbbleService;
import com.squareup.okhttp.OkHttpClient;

import dagger.Module;
import dagger.Provides;
import retrofit.Converter;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

@Module
public class ApiModule {

    @Provides
    public DribbbleService provideDribbleService(Retrofit retrofit) {
        return retrofit.create(DribbbleService.class);
    }

    @Provides
    public Retrofit provideRestAdapter(OkHttpClient client, Converter.Factory converterFactory) {
        return new Retrofit.Builder()
                .baseUrl("https://api.dribbble.com/v1/")
                .client(client)
                .addConverterFactory(converterFactory)
                .build();
    }

    @Provides
    OkHttpClient provideOkhttpClient() {
        return new OkHttpClient();
    }

    @Provides
    Converter.Factory provideConverterFactory() {
        GsonBuilder builder = new GsonBuilder();
        builder.excludeFieldsWithoutExposeAnnotation();
        return GsonConverterFactory.create(builder.create());
    }
}
