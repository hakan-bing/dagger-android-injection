package com.base.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.base.util.Objects;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;
import dagger.android.support.AndroidSupportInjection;


/**
 * @author Hakan Bingöl
 */
public abstract class BaseFragment<V extends ViewDataBinding> extends Fragment implements ITransactionManager, HasAndroidInjector {


    @Nullable
    protected V viewDataBinding;

    @Inject
    protected DispatchingAndroidInjector<Object> androidInjector;

    @LayoutRes
    protected abstract Integer getLayoutResID();

    protected abstract Boolean hasAndroidInjector();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewDataBinding = DataBindingUtil.inflate(inflater, getLayoutResID(), container, false);
        if (Objects.isNull(viewDataBinding)) {
            return inflater.inflate(getLayoutResID(), container, false);
        }
        return viewDataBinding.getRoot();
    }

    protected BaseActivity baseActivity() {
        return (BaseActivity) getActivity();
    }

    @Override
    public AndroidInjector<Object> androidInjector() {
        return androidInjector;
    }

    @Override
    public void onAttach(Context context) {
        if (hasAndroidInjector()) {
            AndroidSupportInjection.inject(this);
        }
        super.onAttach(context);
    }

    /**
     *
     */
    @Override
    public void replaceFragment(Fragment fragment) {
        baseActivity().replaceFragment(fragment);
    }

    /**
     * @param tag can be null or empty string, in this case will be used
     *            fragment.getClass().getSimpleName() as tag.
     */
    @Override
    public void replaceFragment(Fragment fragment, String tag) {
        baseActivity().replaceFragment(fragment, tag);
    }

    /**
     * @param tag can be null or empty string, in this case will be used
     *            fragment.getClass().getSimpleName() as tag.
     */
    @Override
    public void replaceFragment(Fragment fragment,
                                String tag, boolean addToBackStack) {
        baseActivity().replaceFragment(fragment, tag, addToBackStack);
    }

    /**
     * @param tag          can be null or empty string, in this case will be used
     *                     fragment.getClass().getSimpleName() as tag.
     * @param transitionId TRANSIT_DEFAULT or transitionId
     */
    @Override
    public void replaceFragment(Fragment fragment,
                                String tag, int transitionId, boolean addToBackStack) {
        baseActivity().replaceFragment(fragment, tag, transitionId, addToBackStack);
    }

    /**
     * @param tag                  can be null or empty string, in this case will be used
     *                             fragment.getClass().getSimpleName() as tag.
     * @param customAnimationEnter ANIM_DEFAULT or animation
     * @param customAnimationExit  ANIM_DEFAULT or animation
     */
    @Override
    public void replaceFragment(Fragment fragment,
                                String tag, int customAnimationEnter, int customAnimationExit, boolean addToBackStack) {
        baseActivity().replaceFragment(fragment, tag, customAnimationEnter, customAnimationExit, addToBackStack);
    }

    /**
     * @param tag                     can be null or empty string, in this case will be used
     *                                fragment.getClass().getSimpleName() as tag.
     * @param customAnimationEnter    ANIM_DEFAULT or animation
     * @param customAnimationExit     ANIM_DEFAULT or animation
     * @param customAnimationPopEnter ANIM_DEFAULT or animation
     * @param customAnimationPopExit  ANIM_DEFAULT or animation
     */
    @Override
    public void replaceFragment(Fragment fragment,
                                String tag, int customAnimationEnter, int customAnimationExit, int customAnimationPopEnter,
                                int customAnimationPopExit, boolean addToBackStack) {
        baseActivity().replaceFragment(fragment, tag, customAnimationEnter, customAnimationExit, customAnimationPopEnter, customAnimationPopExit, addToBackStack);
    }

    /**
     * @param tag                     can be null or empty string, in this case will be used
     *                                fragment.getClass().getSimpleName() as tag.
     * @param transitionId            TRANSIT_DEFAULT or transitionId
     * @param customAnimationEnter    ANIM_DEFAULT or animation
     * @param customAnimationExit     ANIM_DEFAULT or animation
     * @param customAnimationPopEnter ANIM_DEFAULT or animation
     * @param customAnimationPopExit  ANIM_DEFAULT or animation
     */
    @Override
    public void replaceFragment(Fragment fragment,
                                String tag, int transitionId, int customAnimationEnter, int customAnimationExit,
                                int customAnimationPopEnter, int customAnimationPopExit, boolean addToBackStack) {
        baseActivity().replaceFragment(fragment, tag, transitionId, customAnimationEnter, customAnimationExit, customAnimationPopEnter, customAnimationPopExit, addToBackStack);
    }

    /**
     * @param tag                     can be null or empty string, in this case will be used
     *                                fragment.getClass().getSimpleName() as tag.
     * @param transitionId            TRANSIT_DEFAULT or transitionId
     * @param customAnimationEnter    ANIM_DEFAULT or animation
     * @param customAnimationExit     ANIM_DEFAULT or animation
     * @param customAnimationPopEnter ANIM_DEFAULT or animation
     * @param customAnimationPopExit  ANIM_DEFAULT or animation
     * @param stackName               Name of the back stack.
     */
    @Override
    public void replaceFragment(Fragment fragment,
                                String tag, int transitionId, int customAnimationEnter, int customAnimationExit,
                                int customAnimationPopEnter, int customAnimationPopExit, boolean addToBackStack,
                                String stackName) {
        baseActivity().replaceFragment(fragment, tag, transitionId, customAnimationEnter, customAnimationExit, customAnimationPopEnter, customAnimationPopExit, addToBackStack, stackName);
    }

    /**
     *
     */
    @Override
    public void addFragment(Fragment fragment) {
        baseActivity().addFragment(fragment, getFragmentTag(fragment));
    }

    /**
     * @param tag can be null or empty string, in this case will be used
     *            fragment.getClass().getSimpleName() as tag.
     */
    @Override
    public void addFragment(Fragment fragment, String tag) {
        baseActivity().addFragment(fragment, tag, false);
    }

    /**
     * @param tag can be null or empty string, in this case will be used
     *            fragment.getClass().getSimpleName() as tag.
     */
    @Override
    public void addFragment(Fragment fragment,
                            String tag, boolean addToBackStack) {
        baseActivity().addFragment(fragment, tag, TRANSIT_DEFAULT, addToBackStack);
    }

    /**
     * @param tag          can be null or empty string, in this case will be used
     *                     fragment.getClass().getSimpleName() as tag.
     * @param transitionId TRANSIT_DEFAULT or transitionId
     */
    @Override
    public void addFragment(Fragment fragment,
                            String tag, int transitionId, boolean addToBackStack) {
        baseActivity().addFragment(fragment, tag, transitionId, ANIM_DEFAULT, ANIM_DEFAULT,
                ANIM_DEFAULT, ANIM_DEFAULT, addToBackStack);
    }

    /**
     * @param tag                  can be null or empty string, in this case will be used
     *                             fragment.getClass().getSimpleName() as tag.
     * @param customAnimationEnter ANIM_DEFAULT or animation
     * @param customAnimationExit  ANIM_DEFAULT or animation
     */
    @Override
    public void addFragment(Fragment fragment,
                            String tag, int customAnimationEnter, int customAnimationExit, boolean addToBackStack) {
        baseActivity().addFragment(fragment, tag, customAnimationEnter, customAnimationExit,
                ANIM_DEFAULT, ANIM_DEFAULT, addToBackStack);
    }

    /**
     * @param tag                     can be null or empty string, in this case will be used
     *                                fragment.getClass().getSimpleName() as tag.
     * @param customAnimationEnter    ANIM_DEFAULT or animation
     * @param customAnimationExit     ANIM_DEFAULT or animation
     * @param customAnimationPopEnter ANIM_DEFAULT or animation
     * @param customAnimationPopExit  ANIM_DEFAULT or animation
     */
    @Override
    public void addFragment(Fragment fragment,
                            String tag, int customAnimationEnter, int customAnimationExit, int customAnimationPopEnter,
                            int customAnimationPopExit, boolean addToBackStack) {
        baseActivity().addFragment(fragment, tag, TRANSIT_DEFAULT, customAnimationEnter,
                customAnimationExit, customAnimationPopEnter, customAnimationPopExit, addToBackStack);
    }

    /**
     * @param tag                     can be null or empty string, in this case will be used
     *                                fragment.getClass().getSimpleName() as tag.
     * @param transitionId            TRANSIT_DEFAULT or transitionId
     * @param customAnimationEnter    ANIM_DEFAULT or animation
     * @param customAnimationExit     ANIM_DEFAULT or animation
     * @param customAnimationPopEnter ANIM_DEFAULT or animation
     * @param customAnimationPopExit  ANIM_DEFAULT or animation
     */
    @Override
    public void addFragment(Fragment fragment,
                            String tag, int transitionId, int customAnimationEnter, int customAnimationExit,
                            int customAnimationPopEnter, int customAnimationPopExit, boolean addToBackStack) {
        baseActivity().addFragment(fragment, tag, transitionId, customAnimationEnter,
                customAnimationExit, customAnimationPopEnter, customAnimationPopExit, addToBackStack);
    }

    @Override
    public void removeFragment(Fragment fragment) {
        baseActivity().removeFragment(fragment);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Fragment> T getFragmentById(@IdRes int id) {
        return (T) baseActivity().getFragmentById(id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Fragment> T getFragmentByTag(Class<T> fragment) {
        return (T) baseActivity().getFragmentByTag(fragment);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Fragment> T getFragmentByTag(String tag) {
        return (T) baseActivity().getFragmentByTag(tag);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Fragment> String getFragmentTag(T fragment) {
        return baseActivity().getFragmentTag(fragment);
    }

    @Override
    public void popEntireBackStackFragment() {
        baseActivity().popEntireBackStackFragment();
    }

    @Override
    public void popEntireBackStackImmediateFragment() {
        baseActivity().popEntireBackStackImmediateFragment();
    }

    @Override
    public void popBackStackImmediateFragment(String name) {
        baseActivity().popBackStackImmediateFragment(name);
    }

    @Override
    public void popBackStackFragment() {
        baseActivity().popBackStackFragment();
    }

    @Override
    public boolean isFragmentAddedFragment(Fragment fragment) {
        return baseActivity().isFragmentAddedFragment(fragment);
    }
}
