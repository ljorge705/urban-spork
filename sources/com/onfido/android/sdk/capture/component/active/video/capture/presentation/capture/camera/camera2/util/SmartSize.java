package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.util;

import android.util.Size;
import com.singular.sdk.internal.Constants;
import io.sentry.rrweb.RRWebVideoEvent;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\b\u0010\u0013\u001a\u00020\u0014H\u0016R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\tR\u0011\u0010\u000f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0011\u0010\u0011\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\t¨\u0006\u0015"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/camera2/util/SmartSize;", "", "width", "", "height", "(II)V", "landscape", "Landroid/util/Size;", "getLandscape", "()Landroid/util/Size;", Constants.LONG, "getLong", "()I", "portrait", "getPortrait", "short", "getShort", RRWebVideoEvent.JsonKeys.SIZE, "getSize", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class SmartSize {
    private final Size landscape;
    private final int long;
    private final Size portrait;
    private final int short;
    private final Size size;

    public SmartSize(int i, int i2) {
        Size size = new Size(i, i2);
        this.size = size;
        int iMax = Math.max(size.getWidth(), size.getHeight());
        this.long = iMax;
        int iMin = Math.min(size.getWidth(), size.getHeight());
        this.short = iMin;
        this.landscape = new Size(iMax, iMin);
        this.portrait = new Size(iMin, iMax);
    }

    public final Size getLandscape() {
        return this.landscape;
    }

    public final int getLong() {
        return this.long;
    }

    public final Size getPortrait() {
        return this.portrait;
    }

    public final int getShort() {
        return this.short;
    }

    public final Size getSize() {
        return this.size;
    }

    public String toString() {
        return "SmartSize(" + this.long + 'x' + this.short + ')';
    }
}
