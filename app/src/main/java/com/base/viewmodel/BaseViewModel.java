package com.base.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.SharedPreferences;

import com.base.data.storage.database.entity.SampleEntity;
import com.base.data.storage.database.repository.SampleRepository;
import com.base.data.storage.shared.SharedStorage;

import java.util.List;

import javax.inject.Inject;

public class BaseViewModel extends ViewModel {

    private final Context context;
    private final SharedStorage sharedStorage;
    private final SampleRepository sampleRepository;

    @Inject
    public BaseViewModel(Context context, SharedStorage sharedStorage, SampleRepository sampleRepository) {
        this.context = context;
        this.sharedStorage = sharedStorage;
        this.sampleRepository = sampleRepository;
    }

    public MutableLiveData<List<SampleEntity>> findAllSample() {
        MutableLiveData<List<SampleEntity>> response = new MutableLiveData<>();
        sampleRepository.findAll(response);

        return response;
    }
}
