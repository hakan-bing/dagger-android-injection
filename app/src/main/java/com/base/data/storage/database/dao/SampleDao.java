package com.base.data.storage.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.base.data.storage.database.entity.SampleEntity;

import java.util.List;

@Dao
public interface SampleDao {

    @Insert
    long insert(SampleEntity entity);

    @Query("SELECT * FROM sample")
    List<SampleEntity> findAll();

}
