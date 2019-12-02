package com.base.base;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;

/**
 * @author Hakan Bingöl
 */
public interface ITransactionManager {

    int ANIM_DEFAULT = 0;
    int TRANSIT_DEFAULT = android.support.v4.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN;

    /**
     *
     */
    void replaceFragment(Fragment fragment);

    /**
     * @param tag can be null or empty string, in this case will be used
     *            fragment.getClass().getSimpleName() as tag.
     */
    void replaceFragment(Fragment fragment, String tag);

    /**
     * @param tag can be null or empty string, in this case will be used
     *            fragment.getClass().getSimpleName() as tag.
     */
    void replaceFragment(Fragment fragment, String tag, boolean addToBackStack);

    /**
     * @param tag          can be null or empty string, in this case will be used
     *                     fragment.getClass().getSimpleName() as tag.
     * @param transitionId TRANSIT_DEFAULT or transitionId
     */
    void replaceFragment(Fragment fragment, String tag, int transitionId, boolean addToBackStack);

    /**
     * @param tag                  can be null or empty string, in this case will be used
     *                             fragment.getClass().getSimpleName() as tag.
     * @param customAnimationEnter ANIM_DEFAULT or animation
     * @param customAnimationExit  ANIM_DEFAULT or animation
     */
    void replaceFragment(Fragment fragment, String tag, int customAnimationEnter, int customAnimationExit, boolean addToBackStack);

    /**
     * @param tag                     can be null or empty string, in this case will be used
     *                                fragment.getClass().getSimpleName() as tag.
     * @param customAnimationEnter    ANIM_DEFAULT or animation
     * @param customAnimationExit     ANIM_DEFAULT or animation
     * @param customAnimationPopEnter ANIM_DEFAULT or animation
     * @param customAnimationPopExit  ANIM_DEFAULT or animation
     */
    void replaceFragment(Fragment fragment, String tag, int customAnimationEnter, int customAnimationExit, int customAnimationPopEnter, int customAnimationPopExit, boolean addToBackStack);

    /**
     * @param tag                     can be null or empty string, in this case will be used
     *                                fragment.getClass().getSimpleName() as tag.
     * @param transitionId            TRANSIT_DEFAULT or transitionId
     * @param customAnimationEnter    ANIM_DEFAULT or animation
     * @param customAnimationExit     ANIM_DEFAULT or animation
     * @param customAnimationPopEnter ANIM_DEFAULT or animation
     * @param customAnimationPopExit  ANIM_DEFAULT or animation
     */
    void replaceFragment(Fragment fragment, String tag, int transitionId, int customAnimationEnter, int customAnimationExit, int customAnimationPopEnter, int customAnimationPopExit, boolean addToBackStack);

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
    void replaceFragment(Fragment fragment, String tag, int transitionId, int customAnimationEnter, int customAnimationExit, int customAnimationPopEnter, int customAnimationPopExit, boolean addToBackStack, String stackName);

    /**
     *
     */
    void addFragment(Fragment fragment);

    /**
     * @param tag can be null or empty string, in this case will be used
     *            fragment.getClass().getSimpleName() as tag.
     */
    void addFragment(Fragment fragment, String tag);

    /**
     * @param tag can be null or empty string, in this case will be used
     *            fragment.getClass().getSimpleName() as tag.
     */
    void addFragment(Fragment fragment, String tag, boolean addToBackStack);

    /**
     * @param tag          can be null or empty string, in this case will be used
     *                     fragment.getClass().getSimpleName() as tag.
     * @param transitionId TRANSIT_DEFAULT or transitionId
     */
    void addFragment(Fragment fragment, String tag, int transitionId, boolean addToBackStack);

    /**
     * @param tag                  can be null or empty string, in this case will be used
     *                             fragment.getClass().getSimpleName() as tag.
     * @param customAnimationEnter ANIM_DEFAULT or animation
     * @param customAnimationExit  ANIM_DEFAULT or animation
     */
    void addFragment(Fragment fragment, String tag, int customAnimationEnter, int customAnimationExit, boolean addToBackStack);

    /**
     * @param tag                     can be null or empty string, in this case will be used
     *                                fragment.getClass().getSimpleName() as tag.
     * @param customAnimationEnter    ANIM_DEFAULT or animation
     * @param customAnimationExit     ANIM_DEFAULT or animation
     * @param customAnimationPopEnter ANIM_DEFAULT or animation
     * @param customAnimationPopExit  ANIM_DEFAULT or animation
     */
    void addFragment(Fragment fragment, String tag, int customAnimationEnter, int customAnimationExit, int customAnimationPopEnter, int customAnimationPopExit, boolean addToBackStack);

    /**
     * @param tag                     can be null or empty string, in this case will be used
     *                                fragment.getClass().getSimpleName() as tag.
     * @param transitionId            TRANSIT_DEFAULT or transitionId
     * @param customAnimationEnter    ANIM_DEFAULT or animation
     * @param customAnimationExit     ANIM_DEFAULT or animation
     * @param customAnimationPopEnter ANIM_DEFAULT or animation
     * @param customAnimationPopExit  ANIM_DEFAULT or animation
     */
    void addFragment(Fragment fragment, String tag, int transitionId, int customAnimationEnter, int customAnimationExit, int customAnimationPopEnter, int customAnimationPopExit, boolean addToBackStack);

    void removeFragment(Fragment fragment);

    @SuppressWarnings("unchecked")
    <T extends Fragment> T getFragmentById(@IdRes int id);

    @SuppressWarnings("unchecked")
    <T extends Fragment> T getFragmentByTag(Class<T> fragment);

    @SuppressWarnings("unchecked")
    <T extends Fragment> T getFragmentByTag(String tag);

    @SuppressWarnings("unchecked")
    <T extends  Fragment> String getFragmentTag(T fragment);

    void popEntireBackStackFragment();

    void popEntireBackStackImmediateFragment();

    void popBackStackImmediateFragment(String name);

    void popBackStackFragment();

    boolean isFragmentAddedFragment(Fragment fragment);

}
