package com.uxcam.screenshot.pixelcopyscreenshot;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;
import com.uxcam.screenshot.helper.OnScreenshotTakenCallback;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/uxcam/screenshot/pixelcopyscreenshot/PixelCopyScreenshotConfig;", "", "screenshot_littleRelease"}, k = 1, mv = {1, 8, 0})
/* loaded from: classes6.dex */
public final /* data */ class PixelCopyScreenshotConfig {

    /* renamed from: a, reason: collision with root package name */
    public final Bitmap f275a;
    public final Canvas b;
    public final OnScreenshotTakenCallback c;
    public final List<RectF> d;
    public final Context e;

    public PixelCopyScreenshotConfig(Bitmap bitmap, Canvas canvas, OnScreenshotTakenCallback callback, List sensitiveViewCoordinates, Activity context) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        Intrinsics.checkNotNullParameter(callback, "callback");
        Intrinsics.checkNotNullParameter(sensitiveViewCoordinates, "sensitiveViewCoordinates");
        Intrinsics.checkNotNullParameter(context, "context");
        this.f275a = bitmap;
        this.b = canvas;
        this.c = callback;
        this.d = sensitiveViewCoordinates;
        this.e = context;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PixelCopyScreenshotConfig)) {
            return false;
        }
        PixelCopyScreenshotConfig pixelCopyScreenshotConfig = (PixelCopyScreenshotConfig) obj;
        return Intrinsics.areEqual(this.f275a, pixelCopyScreenshotConfig.f275a) && Intrinsics.areEqual(this.b, pixelCopyScreenshotConfig.b) && Intrinsics.areEqual(this.c, pixelCopyScreenshotConfig.c) && Intrinsics.areEqual(this.d, pixelCopyScreenshotConfig.d) && Intrinsics.areEqual(this.e, pixelCopyScreenshotConfig.e);
    }

    public final int hashCode() {
        return this.e.hashCode() + ((this.d.hashCode() + ((this.c.hashCode() + ((this.b.hashCode() + (this.f275a.hashCode() * 31)) * 31)) * 31)) * 31);
    }

    public final String toString() {
        return "PixelCopyScreenshotConfig(bitmap=" + this.f275a + ", canvas=" + this.b + ", callback=" + this.c + ", sensitiveViewCoordinates=" + this.d + ", context=" + this.e + ')';
    }
}
