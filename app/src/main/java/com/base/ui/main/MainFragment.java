package com.base.ui.main;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.base.R;
import com.base.base.BaseFragment;
import com.base.data.storage.database.entity.SampleEntity;
import com.base.databinding.MainFragmentBinding;
import com.base.di.util.ViewModelFactory;
import com.base.viewmodel.BaseViewModel;

import java.util.List;

import javax.inject.Inject;

public class MainFragment extends BaseFragment<MainFragmentBinding> {

    @Inject
    ViewModelFactory viewModelFactory;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    protected Integer getLayoutResID() {
        return R.layout.main_fragment;
    }

    @Override
    protected Boolean hasAndroidInjector() {
        return true;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        BaseViewModel mViewModel = ViewModelProviders.of(this, viewModelFactory).get(BaseViewModel.class);
        mViewModel.findAllSample().observe(this, new Observer<List<SampleEntity>>() {
            @Override
            public void onChanged(@Nullable List<SampleEntity> sampleEntities) {

            }
        });
    }
}
