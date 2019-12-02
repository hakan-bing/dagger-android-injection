package com.base.base;

import android.databinding.ViewDataBinding;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.MenuRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

import com.base.util.Objects;


/**
 * @author Hakan Bingöl
 */
public abstract class ToolbarActivity<V extends ViewDataBinding> extends BaseActivity<V> {

    /**
     * This function used getting options menu.
     *
     * @return if activity has menu item return menu id, otherwise -1 or 0
     */
    @MenuRes
    protected abstract Integer getOptionsMenu();

    protected abstract ToolbarContent getToolbarContent();

    protected Toolbar toolbar;

    protected View.OnClickListener navigationOnClickListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbar(getToolbarContent());
    }

    protected void setToolbar(@NonNull ToolbarContent toolbarContent) {
        toolbar = findViewById(toolbarContent.getResID());
        // if ActionBar has not been set before
        if (Objects.isNull(getSupportActionBar())) {
            setSupportActionBar(toolbar);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getSupportActionBar().setElevation(16.0F);
        }
        getSupportActionBar().setTitle(toolbarContent.getTitle());
        getSupportActionBar().setSubtitle(toolbarContent.getSubtitle());
        if (Objects.nonNull(toolbarContent.getNavigationIcon())) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationIcon(toolbarContent.getNavigationIcon());
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Objects.nonNull(navigationOnClickListener)) {
                        navigationOnClickListener.onClick(v);
                    }
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (Objects.nonNull(getOptionsMenu())) {
            getMenuInflater().inflate(getOptionsMenu(), menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }
}
