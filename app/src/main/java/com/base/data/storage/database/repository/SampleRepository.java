package com.base.data.storage.database.repository;

import android.arch.lifecycle.MutableLiveData;

import com.base.data.storage.database.DatabaseStorage;
import com.base.data.storage.database.entity.SampleEntity;

import java.util.List;
import java.util.concurrent.ExecutorService;

public class SampleRepository {

    private final DatabaseStorage database;
    private final ExecutorService executorService;

    public SampleRepository(DatabaseStorage database, ExecutorService executorService) {
        this.database = database;
        this.executorService = executorService;
    }

    /**
     * @return
     * @apiNote Insert Update and Delete operations must call on non-UI thread. Otherwise app will crash.
     */
    public void insert(MutableLiveData<Long> response, SampleEntity entity) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                long id = database.sampleDao().insert(entity);
                response.postValue(id);
            }
        });
    }

    public void findAll(MutableLiveData<List<SampleEntity>> response) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                response.postValue(database.sampleDao().findAll());
            }
        });
    }
}
