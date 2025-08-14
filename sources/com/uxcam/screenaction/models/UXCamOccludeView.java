package com.uxcam.screenaction.models;

/* loaded from: classes6.dex */
public class UXCamOccludeView extends UXCamView {
    public final boolean o;
    public String p = "";

    public UXCamOccludeView(boolean z) {
        this.o = z;
    }

    public String getActivityName() {
        return this.p;
    }

    public boolean isAddedByUser() {
        return this.o;
    }

    public void setActivityName(String str) {
        this.p = str;
    }
}
