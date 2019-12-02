package com.base.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.base.util.Objects;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;
import dagger.android.support.AndroidSupportInjection;


/**
 * @author Hakan Bingöl
 */
public abstract class BaseActivity<V extends ViewDataBinding> extends AppCompatActivity implements ITransactionManager, HasAndroidInjector {

    @Nullable
    protected V viewDataBinding;

    @Inject
    protected DispatchingAndroidInjector<Object> androidInjector;

    @LayoutRes
    protected abstract Integer getLayoutResID();

    @IdRes
    protected abstract Integer getFragmentContainerId();

    protected abstract Boolean hasAndroidInjector();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (hasAndroidInjector()) {
            AndroidInjection.inject(this);
        }
        super.onCreate(savedInstanceState);
        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutResID());
        if (Objects.isNull(viewDataBinding)) {
            setContentView(getLayoutResID());
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public AndroidInjector<Object> androidInjector() {
        return this.androidInjector;
    }

    /**
     *
     */
    @Override
    public void replaceFragment(Fragment fragment) {
        replaceFragment(fragment, getFragmentTag(fragment));
    }

    /**
     * @param tag can be null or empty string, in this case will be used
     *            fragment.getClass().getSimpleName() as tag.
     */
    @Override
    public void replaceFragment(Fragment fragment, String tag) {
        replaceFragment(fragment, tag, false);
    }

    /**
     * @param tag can be null or empty string, in this case will be used
     *            fragment.getClass().getSimpleName() as tag.
     */
    @Override
    public void replaceFragment(Fragment fragment,
                                String tag, boolean addToBackStack) {
        replaceFragment(fragment, tag, TRANSIT_DEFAULT, addToBackStack);
    }

    /**
     * @param tag          can be null or empty string, in this case will be used
     *                     fragment.getClass().getSimpleName() as tag.
     * @param transitionId TRANSIT_DEFAULT or transitionId
     */
    @Override
    public void replaceFragment(Fragment fragment,
                                String tag, int transitionId, boolean addToBackStack) {
        replaceFragment(fragment, tag, transitionId, ANIM_DEFAULT, ANIM_DEFAULT,
                ANIM_DEFAULT, ANIM_DEFAULT, addToBackStack);
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
        replaceFragment(fragment, tag, customAnimationEnter, customAnimationExit,
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
    public void replaceFragment(Fragment fragment,
                                String tag, int customAnimationEnter, int customAnimationExit, int customAnimationPopEnter,
                                int customAnimationPopExit, boolean addToBackStack) {
        replaceFragment(fragment, tag, TRANSIT_DEFAULT, customAnimationEnter,
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
    public void replaceFragment(Fragment fragment,
                                String tag, int transitionId, int customAnimationEnter, int customAnimationExit,
                                int customAnimationPopEnter, int customAnimationPopExit, boolean addToBackStack) {
        replaceFragment(fragment, tag, transitionId, customAnimationEnter,
                customAnimationExit, customAnimationPopEnter, customAnimationPopExit, addToBackStack, null);
    }

    /**
     * @param tag                     can be null or empty string, in this case will be used
     *                                fragment.getClass().getSimpleName() as tag.
     * @param transitionId            TRANSIT_DEFAULT or transitionId
     * @param customAnimationEnter    ANIM_DEFAULT or animation
     * @param customAnimationExit     ANIM_DEFAULT or animation
     * @param customAnimationPopEnter ANIM_DEFAULT or animation
     * @param customAnimationPopExit  ANIM_DEFAULT or animations
     * @param stackName               Name of the back stack.
     */
    @Override
    public void replaceFragment(Fragment fragment,
                                String tag, int transitionId, int customAnimationEnter, int customAnimationExit,
                                int customAnimationPopEnter, int customAnimationPopExit, boolean addToBackStack,
                                String stackName) {
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (transitionId != TRANSIT_DEFAULT) {
            transaction.setTransition(transitionId);
        }

        if (customAnimationPopEnter != ANIM_DEFAULT || customAnimationPopExit != ANIM_DEFAULT) {
            transaction.setCustomAnimations(customAnimationEnter, customAnimationExit,
                    customAnimationPopEnter, customAnimationPopExit);
        } else if (customAnimationEnter != ANIM_DEFAULT || customAnimationExit != ANIM_DEFAULT) {
            transaction.setCustomAnimations(customAnimationEnter, customAnimationExit);
        }

        transaction.replace(getFragmentContainerId(), fragment, TextUtils.isEmpty(tag) ? fragment.getClass().getSimpleName() : tag);

        if (addToBackStack) {
            transaction.addToBackStack(tag);
        }

        transaction.commit();
    }

    /**
     *
     */
    @Override
    public void addFragment(Fragment fragment) {
        addFragment(fragment, getFragmentTag(fragment));
    }

    /**
     * @param tag can be null or empty string, in this case will be used
     *            fragment.getClass().getSimpleName() as tag.
     */
    @Override
    public void addFragment(Fragment fragment, String tag) {
        addFragment(fragment, tag, false);
    }

    /**
     * @param tag can be null or empty string, in this case will be used
     *            fragment.getClass().getSimpleName() as tag.
     */
    @Override
    public void addFragment(Fragment fragment,
                            String tag, boolean addToBackStack) {
        addFragment(fragment, tag, TRANSIT_DEFAULT, addToBackStack);
    }

    /**
     * @param tag          can be null or empty string, in this case will be used
     *                     fragment.getClass().getSimpleName() as tag.
     * @param transitionId TRANSIT_DEFAULT or transitionId
     */
    @Override
    public void addFragment(Fragment fragment,
                            String tag, int transitionId, boolean addToBackStack) {
        addFragment(fragment, tag, transitionId, ANIM_DEFAULT, ANIM_DEFAULT,
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
        addFragment(fragment, tag, customAnimationEnter, customAnimationExit,
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
        addFragment(fragment, tag, TRANSIT_DEFAULT, customAnimationEnter,
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
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (transitionId != TRANSIT_DEFAULT) {
            transaction.setTransition(transitionId);
        }

        if (customAnimationPopEnter != ANIM_DEFAULT || customAnimationPopExit != ANIM_DEFAULT) {
            transaction.setCustomAnimations(customAnimationEnter, customAnimationExit,
                    customAnimationPopEnter, customAnimationPopExit);
        } else if (customAnimationEnter != ANIM_DEFAULT || customAnimationExit != ANIM_DEFAULT) {
            transaction.setCustomAnimations(customAnimationEnter, customAnimationExit);
        }

        transaction.add(getFragmentContainerId(), fragment, TextUtils.isEmpty(tag) ? fragment.getClass().getSimpleName() : tag);

        if (addToBackStack) {
            transaction.addToBackStack(tag);
        }

        transaction.commit();
    }

    @Override
    public void removeFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().remove(fragment).commit();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Fragment> T getFragmentById(@IdRes int id) {
        return (T) getSupportFragmentManager().findFragmentById(id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Fragment> T getFragmentByTag(Class<T> fragment) {
        return (T) getSupportFragmentManager().findFragmentByTag(fragment.getSimpleName());
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Fragment> T getFragmentByTag(String tag) {
        return (T) getSupportFragmentManager().findFragmentByTag(tag);
    }


    @Override
    public <T extends Fragment> String getFragmentTag(T fragment) {
        return fragment.getClass().getSimpleName();
    }

    @Override
    public void popEntireBackStackFragment() {
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    @Override
    public void popEntireBackStackImmediateFragment() {
        getSupportFragmentManager().popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    @Override
    public void popBackStackImmediateFragment(String name) {
        getSupportFragmentManager().popBackStackImmediate(name, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    @Override
    public void popBackStackFragment() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public boolean isFragmentAddedFragment(Fragment fragment) {
        return fragment != null && fragment.isAdded();
    }
}
