package com.uxcam.screenshot.screenshotTaker;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.View;
import com.google.android.gms.maps.GoogleMap;
import com.uxcam.screenaction.models.ViewRootData;
import com.uxcam.screenshot.flutterviewfinder.FlutterConfig;
import com.uxcam.screenshot.helper.ScreenshotScalingFactor;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/uxcam/screenshot/screenshotTaker/ScreenshotTakerConfig;", "", "screenshot_littleRelease"}, k = 1, mv = {1, 8, 0})
/* loaded from: classes6.dex */
public final /* data */ class ScreenshotTakerConfig {

    /* renamed from: a, reason: collision with root package name */
    public final Activity f279a;
    public final Bitmap b;
    public final WeakReference<View> c;
    public final GoogleMap d;
    public final FlutterConfig e;
    public final boolean f;
    public final boolean g;
    public final boolean h;
    public final ScreenshotScalingFactor i;
    public final List<ViewRootData> j;

    public ScreenshotTakerConfig(Activity activity, Bitmap bitmap, WeakReference weakReference, GoogleMap googleMap, FlutterConfig flutterConfig, boolean z, boolean z2, boolean z3, ScreenshotScalingFactor scalingFactor, ArrayList viewRootDataList) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        Intrinsics.checkNotNullParameter(scalingFactor, "scalingFactor");
        Intrinsics.checkNotNullParameter(viewRootDataList, "viewRootDataList");
        this.f279a = activity;
        this.b = bitmap;
        this.c = weakReference;
        this.d = googleMap;
        this.e = flutterConfig;
        this.f = z;
        this.g = z2;
        this.h = z3;
        this.i = scalingFactor;
        this.j = viewRootDataList;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ScreenshotTakerConfig)) {
            return false;
        }
        ScreenshotTakerConfig screenshotTakerConfig = (ScreenshotTakerConfig) obj;
        return Intrinsics.areEqual(this.f279a, screenshotTakerConfig.f279a) && Intrinsics.areEqual(this.b, screenshotTakerConfig.b) && Intrinsics.areEqual(this.c, screenshotTakerConfig.c) && Intrinsics.areEqual(this.d, screenshotTakerConfig.d) && Intrinsics.areEqual(this.e, screenshotTakerConfig.e) && this.f == screenshotTakerConfig.f && this.g == screenshotTakerConfig.g && this.h == screenshotTakerConfig.h && Intrinsics.areEqual(this.i, screenshotTakerConfig.i) && Intrinsics.areEqual(this.j, screenshotTakerConfig.j);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final int hashCode() {
        Activity activity = this.f279a;
        int iHashCode = (this.b.hashCode() + ((activity == null ? 0 : activity.hashCode()) * 31)) * 31;
        WeakReference<View> weakReference = this.c;
        int iHashCode2 = (iHashCode + (weakReference == null ? 0 : weakReference.hashCode())) * 31;
        GoogleMap googleMap = this.d;
        int iHashCode3 = (iHashCode2 + (googleMap == null ? 0 : googleMap.hashCode())) * 31;
        FlutterConfig flutterConfig = this.e;
        int iHashCode4 = (iHashCode3 + (flutterConfig != null ? flutterConfig.hashCode() : 0)) * 31;
        boolean z = this.f;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        int i2 = (iHashCode4 + i) * 31;
        boolean z2 = this.g;
        int i3 = z2;
        if (z2 != 0) {
            i3 = 1;
        }
        int i4 = (i2 + i3) * 31;
        boolean z3 = this.h;
        return this.j.hashCode() + ((this.i.hashCode() + ((i4 + (z3 ? 1 : z3 ? 1 : 0)) * 31)) * 31);
    }

    public final String toString() {
        return "ScreenshotTakerConfig(activity=" + this.f279a + ", bitmap=" + this.b + ", googleMapView=" + this.c + ", googleMap=" + this.d + ", flutterConfig=" + this.e + ", isImprovedScreenCaptureInUse=" + this.f + ", isPixelCopySupported=" + this.g + ", isPausedForAnotherApp=" + this.h + ", scalingFactor=" + this.i + ", viewRootDataList=" + this.j + ')';
    }
}
