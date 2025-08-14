package com.uxcam.screenshot.model;

import androidx.core.internal.view.SupportMenu;
import java.util.List;

/* loaded from: classes6.dex */
public class UXCamOverlay implements UXCamOcclusion {
    private final int color;
    private final boolean excludeMentionedScreens;
    private final List<String> screens;
    private final boolean withoutGesture;

    public static class Builder {
        private final int color = SupportMenu.CATEGORY_MASK;
        private boolean withoutGesture = true;
        private List<String> screens = null;
        private boolean excludeMentionedScreens = false;

        public UXCamOverlay build() {
            return new UXCamOverlay(this);
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

    public int getColor() {
        return this.color;
    }

    @Override // com.uxcam.screenshot.model.UXCamOcclusion
    public boolean getExcludeMentionedScreens() {
        return this.excludeMentionedScreens;
    }

    @Override // com.uxcam.screenshot.model.UXCamOcclusion
    public List<String> getScreens() {
        return this.screens;
    }

    @Override // com.uxcam.screenshot.model.UXCamOcclusion
    public boolean isWithoutGesture() {
        return this.withoutGesture;
    }

    private UXCamOverlay(Builder builder) {
        this.withoutGesture = builder.withoutGesture;
        this.color = SupportMenu.CATEGORY_MASK;
        this.screens = builder.screens;
        this.excludeMentionedScreens = builder.excludeMentionedScreens;
    }
}
