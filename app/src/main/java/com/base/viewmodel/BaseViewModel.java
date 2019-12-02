package com.base.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import javax.inject.Inject;

public class BaseViewModel extends ViewModel {

    private final Context context;
    private final SharedPreferences sharedPreferences;

    @Inject
    public BaseViewModel(Context context, SharedPreferences sharedPreferences) {
        this.context = context;
        this.sharedPreferences = sharedPreferences;
    }

    public void show() {
        Toast.makeText(context, sharedPreferences.getString("key", "Absent"), Toast.LENGTH_LONG).show();
    }
}
