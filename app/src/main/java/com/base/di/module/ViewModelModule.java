package com.base.di.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.base.di.util.ViewModelFactory;
import com.base.di.util.ViewModelKey;
import com.base.viewmodel.BaseViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

    @Binds
    @IntoMap
    @ViewModelKey(BaseViewModel.class)
    abstract ViewModel bindBaseViewModel(BaseViewModel baseViewModel);


}
