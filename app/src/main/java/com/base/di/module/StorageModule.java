package com.base.di.module;

import android.app.Application;
import android.content.Context;

import com.base.BuildConfig;
import com.base.data.storage.database.DatabaseStorage;
import com.base.data.storage.database.repository.SampleRepository;
import com.base.data.storage.shared.SharedStorage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class StorageModule {

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Singleton
    @Provides
    SharedStorage provideSharedStorage(Context context, @Named("sharedPreferencesName") String name) {
        return new SharedStorage(context, name);
    }

    @Singleton
    @Provides
    @Named("sharedPreferencesName")
    String provideSharedPreferencesName() {
        return BuildConfig.SHARED_PREFERENCES_NAME;
    }

    @Singleton
    @Provides
    DatabaseStorage provideDatabaseStorage(Context context, @Named("roomDatabaseName") String name) {
        return DatabaseStorage.getInstance(context, name);
    }

    @Singleton
    @Provides
    @Named("roomDatabaseName")
    String provideRoomDatabaseName() {
        return BuildConfig.ROOM_DATABASE_NAME;
    }

    @Singleton
    @Provides
    ExecutorService provideExecutorService() {
        return Executors.newSingleThreadExecutor();
    }

    @Singleton
    @Provides
    SampleRepository provideSampleRepository(DatabaseStorage databaseStorage, ExecutorService executorService) {
        return new SampleRepository(databaseStorage, executorService);
    }
}
