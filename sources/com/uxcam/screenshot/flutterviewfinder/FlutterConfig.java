package com.uxcam.screenshot.flutterviewfinder;

import io.flutter.embedding.android.FlutterSurfaceView;
import io.flutter.view.FlutterView;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/uxcam/screenshot/flutterviewfinder/FlutterConfig;", "", "screenshot_littleRelease"}, k = 1, mv = {1, 8, 0})
/* loaded from: classes6.dex */
public final class FlutterConfig {

    /* renamed from: a, reason: collision with root package name */
    public final List<WeakReference<FlutterView>> f263a;
    public final List<WeakReference<FlutterSurfaceView>> b;

    public FlutterConfig(ArrayList flutterViewWeakReferenceList, ArrayList flutterSurfaceViewWeakReferenceList) {
        Intrinsics.checkNotNullParameter(flutterViewWeakReferenceList, "flutterViewWeakReferenceList");
        Intrinsics.checkNotNullParameter(flutterSurfaceViewWeakReferenceList, "flutterSurfaceViewWeakReferenceList");
        this.f263a = flutterViewWeakReferenceList;
        this.b = flutterSurfaceViewWeakReferenceList;
    }
}
