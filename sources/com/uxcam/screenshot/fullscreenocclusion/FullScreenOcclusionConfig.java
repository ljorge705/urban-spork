package com.uxcam.screenshot.fullscreenocclusion;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/* loaded from: classes6.dex */
public class FullScreenOcclusionConfig {

    /* renamed from: a, reason: collision with root package name */
    public final Bitmap f264a;
    public final Canvas b;
    public final FullScreenOcclusionCallback c;

    public FullScreenOcclusionConfig(Bitmap bitmap, Canvas canvas, FullScreenOcclusionCallback fullScreenOcclusionCallback) {
        this.f264a = bitmap;
        this.b = canvas;
        this.c = fullScreenOcclusionCallback;
    }
}
