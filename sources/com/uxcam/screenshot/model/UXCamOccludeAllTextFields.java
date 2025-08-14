package com.uxcam.screenshot.model;

import java.util.List;

/* loaded from: classes6.dex */
public class UXCamOccludeAllTextFields implements UXCamOcclusion {
    private final boolean excludeMentionedScreens;
    private final List<String> toScreens;

    public static class Builder {
        private List<String> screens = null;
        private boolean excludeMentionedScreens = false;

        public UXCamOccludeAllTextFields build() {
            return new UXCamOccludeAllTextFields(this);
        }

        public Builder excludeMentionedScreens(boolean z) {
            this.excludeMentionedScreens = z;
            return this;
        }

        public Builder screens(List<String> list) {
            this.screens = list;
            return this;
        }
    }

    public UXCamOccludeAllTextFields(Builder builder) {
        this.toScreens = builder.screens;
        this.excludeMentionedScreens = builder.excludeMentionedScreens;
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
        return false;
    }
}
