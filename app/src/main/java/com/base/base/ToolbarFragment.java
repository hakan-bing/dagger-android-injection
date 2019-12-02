package com.base.base;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.MenuRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.base.util.Objects;


/**
 * @author Hakan Bingöl
 */
public abstract class ToolbarFragment<V extends ViewDataBinding> extends BaseFragment<V> {

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Objects.nonNull(getOptionsMenu())) {
            setHasOptionsMenu(true);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ((ToolbarActivity) getActivity()).setToolbar(getToolbarContent());
        toolbar = ((ToolbarActivity) getActivity()).toolbar;
        navigationOnClickListener = ((ToolbarActivity) getActivity()).navigationOnClickListener;
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (Objects.nonNull(getOptionsMenu())) {
            inflater.inflate(getOptionsMenu(), menu);
        }
        super.onCreateOptionsMenu(menu, inflater);
    }
}
