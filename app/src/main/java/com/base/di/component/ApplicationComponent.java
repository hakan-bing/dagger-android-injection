package com.base.di.component;

import android.app.Application;

import com.base.BaseApplication;
import com.base.di.module.ActivityBindingModule;
import com.base.di.module.NetworkModule;
import com.base.di.module.StorageModule;
import com.base.di.module.ViewModelModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.DaggerApplication;

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, ViewModelModule.class, ActivityBindingModule.class, NetworkModule.class, StorageModule.class})
public interface ApplicationComponent extends AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        ApplicationComponent build();
    }

    void inject(BaseApplication application);
}
