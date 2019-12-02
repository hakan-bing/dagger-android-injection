package com.base.base;

import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * @author Hakan Bingöl
 */
public class ToolbarContent {

    private Builder builder;

    private ToolbarContent(@NonNull Builder builder) {
        this.builder = builder;
    }

    public Integer getResID() {
        return builder.resID;
    }

    public String getTitle() {
        return builder.title;
    }

    public String getSubtitle() {
        return builder.subtitle;
    }

    public Integer getNavigationIcon() {
        return builder.navigationIconID;
    }

    public static class Builder {

        @IdRes
        Integer resID;
        String title;
        String subtitle;
        @DrawableRes
        Integer navigationIconID;

        public Builder(@IdRes int resID) {
            this.resID = resID;
        }

        public Builder setTitle(@Nullable String title) {
            this.title = title;
            return this;
        }

        public Builder setSubtitle(@Nullable String subtitle) {
            this.subtitle = subtitle;
            return this;
        }

        public Builder setNavigationIcon(@DrawableRes Integer navigationIconID) {
            this.navigationIconID = navigationIconID;
            return this;
        }

        public ToolbarContent build() {
            return new ToolbarContent(this);
        }
    }
}
