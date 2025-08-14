package com.uxcam.screenshot.legacyscreenshot;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;
import android.webkit.WebView;
import com.google.android.gms.maps.GoogleMap;
import com.uxcam.screenaction.models.ViewRootData;
import com.uxcam.screenshot.flutterviewfinder.FlutterConfig;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/uxcam/screenshot/legacyscreenshot/LegacyScreenshotConfig;", "", "screenshot_littleRelease"}, k = 1, mv = {1, 8, 0})
/* loaded from: classes6.dex */
public final /* data */ class LegacyScreenshotConfig {

    /* renamed from: a, reason: collision with root package name */
    public final ViewRootData f269a;
    public final Bitmap b;
    public final Canvas c;
    public final FlutterConfig d;
    public final GoogleMap e;
    public final int f;
    public final boolean g;
    public final WeakReference<WebView> h;
    public final boolean i;
    public final WeakReference<View> j;
    public final Bitmap k;

    public LegacyScreenshotConfig(ViewRootData viewRootData, Bitmap bitmap, Canvas canvas, FlutterConfig flutterConfig, GoogleMap googleMap, int i, WeakReference weakReference, boolean z, WeakReference weakReference2, Bitmap bitmap2) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        this.f269a = viewRootData;
        this.b = bitmap;
        this.c = canvas;
        this.d = flutterConfig;
        this.e = googleMap;
        this.f = i;
        this.g = true;
        this.h = weakReference;
        this.i = z;
        this.j = weakReference2;
        this.k = bitmap2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LegacyScreenshotConfig)) {
            return false;
        }
        LegacyScreenshotConfig legacyScreenshotConfig = (LegacyScreenshotConfig) obj;
        return Intrinsics.areEqual(this.f269a, legacyScreenshotConfig.f269a) && Intrinsics.areEqual(this.b, legacyScreenshotConfig.b) && Intrinsics.areEqual(this.c, legacyScreenshotConfig.c) && Intrinsics.areEqual(this.d, legacyScreenshotConfig.d) && Intrinsics.areEqual(this.e, legacyScreenshotConfig.e) && this.f == legacyScreenshotConfig.f && this.g == legacyScreenshotConfig.g && Intrinsics.areEqual(this.h, legacyScreenshotConfig.h) && this.i == legacyScreenshotConfig.i && Intrinsics.areEqual(this.j, legacyScreenshotConfig.j) && Intrinsics.areEqual(this.k, legacyScreenshotConfig.k);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final int hashCode() {
        ViewRootData viewRootData = this.f269a;
        int iHashCode = (this.c.hashCode() + ((this.b.hashCode() + ((viewRootData == null ? 0 : viewRootData.hashCode()) * 31)) * 31)) * 31;
        FlutterConfig flutterConfig = this.d;
        int iHashCode2 = (iHashCode + (flutterConfig == null ? 0 : flutterConfig.hashCode())) * 31;
        GoogleMap googleMap = this.e;
        int iHashCode3 = (Integer.hashCode(this.f) + ((iHashCode2 + (googleMap == null ? 0 : googleMap.hashCode())) * 31)) * 31;
        boolean z = this.g;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        int i2 = (iHashCode3 + i) * 31;
        WeakReference<WebView> weakReference = this.h;
        int iHashCode4 = (i2 + (weakReference == null ? 0 : weakReference.hashCode())) * 31;
        boolean z2 = this.i;
        int i3 = (iHashCode4 + (z2 ? 1 : z2 ? 1 : 0)) * 31;
        WeakReference<View> weakReference2 = this.j;
        int iHashCode5 = (i3 + (weakReference2 == null ? 0 : weakReference2.hashCode())) * 31;
        Bitmap bitmap = this.k;
        return iHashCode5 + (bitmap != null ? bitmap.hashCode() : 0);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("LegacyScreenshotConfig(config=");
        sb.append(this.f269a).append(", bitmap=").append(this.b).append(", canvas=").append(this.c).append(", flutterConfig=").append(this.d).append(", googleMap=").append(this.e).append(", sdkInt=").append(this.f).append(", isAltScreenshotForWebView=").append(this.g).append(", webView=").append(this.h).append(", isFlutter=").append(this.i).append(", googleMapView=").append(this.j).append(", mapBitmap=").append(this.k).append(')');
        return sb.toString();
    }
}
