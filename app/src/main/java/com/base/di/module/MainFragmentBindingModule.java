package com.base.di.module;

import com.base.ui.main.MainFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentBindingModule {

    @ContributesAndroidInjector
    abstract MainFragment bindFragment();
}
