package com.base.di.module;

import android.app.Application;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.content.SharedPreferences;

import com.base.BuildConfig;
import com.base.data.storage.database.DatabaseStorage;
import com.base.data.storage.shared.SharedStorage;

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
    SharedPreferences provideSharedPreferences(Context context, @Named("sharedPreferencesName") String name) {
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
    RoomDatabase provideRoomDatabase(Context context, @Named("roomDatabaseName") String name) {
        return DatabaseStorage.getInstance(context, name);
    }

    @Singleton
    @Provides
    @Named("roomDatabaseName")
    String provideRoomDatabaseName() {
        return BuildConfig.ROOM_DATABASE_NAME;
    }
}
