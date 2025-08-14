package com.uxcam.video.screen.codec.codecs.h264.io.model;

/* loaded from: classes6.dex */
public class AspectRatio {
    public static final AspectRatio Extended_SAR = new AspectRatio(255);
    private final int value;

    private AspectRatio(int i) {
        this.value = i;
    }

    public static AspectRatio fromValue(int i) {
        AspectRatio aspectRatio = Extended_SAR;
        return i == aspectRatio.value ? aspectRatio : new AspectRatio(i);
    }

    public int getValue() {
        return this.value;
    }
}
