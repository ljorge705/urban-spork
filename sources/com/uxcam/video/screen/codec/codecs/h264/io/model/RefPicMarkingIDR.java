package com.uxcam.video.screen.codec.codecs.h264.io.model;

/* loaded from: classes6.dex */
public class RefPicMarkingIDR {
    boolean discardDecodedPics;
    boolean useForlongTerm;

    public RefPicMarkingIDR(boolean z, boolean z2) {
        this.discardDecodedPics = z;
        this.useForlongTerm = z2;
    }

    public boolean isDiscardDecodedPics() {
        return this.discardDecodedPics;
    }

    public boolean isUseForlongTerm() {
        return this.useForlongTerm;
    }
}
