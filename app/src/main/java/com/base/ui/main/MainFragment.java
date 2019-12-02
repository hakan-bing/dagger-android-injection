package com.base.ui.main;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.base.R;
import com.base.base.BaseFragment;
import com.base.databinding.MainFragmentBinding;
import com.base.di.util.ViewModelFactory;
import com.base.viewmodel.BaseViewModel;

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
        mViewModel.show();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
