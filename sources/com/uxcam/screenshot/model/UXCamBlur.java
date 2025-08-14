package com.uxcam.screenshot.model;

import java.util.List;

/* loaded from: classes6.dex */
public class UXCamBlur implements UXCamOcclusion {
    private final int blurRadius;
    private final boolean excludeMentionedScreens;
    private final List<String> toScreens;
    private final boolean withoutGesture;

    public static class Builder {
        private int blurRadius = 10;
        private boolean withoutGesture = true;
        private List<String> screens = null;
        private boolean excludeMentionedScreens = false;

        public Builder blurRadius(int i) {
            this.blurRadius = i;
            return this;
        }

        public UXCamBlur build() {
            return new UXCamBlur(this);
        }

        public Builder excludeMentionedScreens(boolean z) {
            this.excludeMentionedScreens = z;
            return this;
        }

        public Builder screens(List<String> list) {
            this.screens = list;
            return this;
        }

        public Builder withoutGesture(boolean z) {
            this.withoutGesture = z;
            return this;
        }
    }

    public int getBlurRadius() {
        return this.blurRadius;
    }

    @Override // com.uxcam.screenshot.model.UXCamOcclusion
    public boolean getExcludeMentionedScreens() {
        return this.excludeMentionedScreens;
    }

    @Override // com.uxcam.screenshot.model.UXCamOcclusion
    public List<String> getScreens() {
        return this.toScreens;
    }

    @Override // com.uxcam.screenshot.model.UXCamOcclusion
    public boolean isWithoutGesture() {
        return this.withoutGesture;
    }

    private UXCamBlur(Builder builder) {
        this.blurRadius = builder.blurRadius;
        this.withoutGesture = builder.withoutGesture;
        this.toScreens = builder.screens;
        this.excludeMentionedScreens = builder.excludeMentionedScreens;
    }
}
