package com.base.data.storage.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.support.annotation.NonNull;

import com.base.data.storage.database.converter.StringListConverter;
import com.base.data.storage.database.dao.SampleDao;
import com.base.data.storage.database.entity.SampleEntity;

@Database(entities = {SampleEntity.class}, version = 1, exportSchema = false)
@TypeConverters({StringListConverter.class})
public abstract class DatabaseStorage extends RoomDatabase {

    public abstract SampleDao sampleDao();

    public static DatabaseStorage getInstance(@NonNull Context context, @NonNull String name) {
        return Room.databaseBuilder(context, DatabaseStorage.class, name)
                .allowMainThreadQueries()
                .build();
    }
}
