package com.uxcam.video.screen.codec.codecs.h264.io.model;

/* loaded from: classes6.dex */
public enum SliceType {
    P,
    B,
    I,
    SP,
    SI;

    public boolean isInter() {
        return (this == I || this == SI) ? false : true;
    }

    public boolean isIntra() {
        return this == I || this == SI;
    }
}
