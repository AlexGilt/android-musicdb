package ru.alexgiltd.musicdb.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.alexgiltd.musicdb.BuildConfig;
import ru.alexgiltd.musicdb.data.local.InMemoryCacheDataSource;
import ru.alexgiltd.musicdb.data.local.LocalDataSource;
import ru.alexgiltd.musicdb.data.remote.RemoteDataSource;
import ru.alexgiltd.musicdb.data.remote.RemoteDataSourceImpl;
import ru.alexgiltd.musicdb.data.remote.api.LastFmService;
import ru.alexgiltd.musicdb.data.repository.Repository;
import ru.alexgiltd.musicdb.data.repository.RepositoryImpl;

@Module
abstract class ApplicationModule {

    @Provides
    @Singleton
    static OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(
                        new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
                )
                .build();
    }

    @Provides
    @Singleton
    static Gson provideGson() {
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
    }

    @Provides
    @Singleton
    static Retrofit provideRetrofit(OkHttpClient client, Gson gson) {
        return new Retrofit.Builder()
                .client(client)
                .baseUrl(BuildConfig.API_HOST)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    @Provides
    @Singleton
    static LastFmService provideLastFmService(Retrofit retrofit) {
        return retrofit.create(LastFmService.class);
    }

    @Binds
    @Singleton
    abstract RemoteDataSource bindRemoteRepository(RemoteDataSourceImpl repository);

    @Binds
    @Singleton
    abstract Repository bindRepository(RepositoryImpl repository);

    @Binds
    @Singleton
    abstract LocalDataSource bindInMemoryCache(InMemoryCacheDataSource inMemoryCacheDataSource);

}
