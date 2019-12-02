package com.base.ui.main;


import android.os.Bundle;

import com.base.R;
import com.base.base.ToolbarActivity;
import com.base.base.ToolbarContent;
import com.base.databinding.MainActivityBinding;

public class MainActivity extends ToolbarActivity<MainActivityBinding> {

    @Override
    protected Integer getOptionsMenu() {
        return null;
    }

    @Override
    protected ToolbarContent getToolbarContent() {
        return new ToolbarContent.Builder(R.id.toolbar)
                .setTitle("Main")
                .build();
    }

    @Override
    protected Integer getLayoutResID() {
        return R.layout.main_activity;
    }

    @Override
    protected Integer getFragmentContainerId() {
        return R.id.content_frame;
    }

    @Override
    protected Boolean hasAndroidInjector() {
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        replaceFragment(MainFragment.newInstance());

    }
}
