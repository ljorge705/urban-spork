package com.uxcam.screenshot.flutterviewfinder;

import android.view.ViewGroup;
import io.flutter.embedding.android.FlutterSurfaceView;
import io.flutter.view.FlutterView;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class FlutterViewFinderImpl implements FlutterViewFinder {
    @Override // com.uxcam.screenshot.flutterviewfinder.FlutterViewFinder
    public final FlutterConfig a(ViewGroup viewGroup) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            FlutterView childAt = viewGroup.getChildAt(i);
            if (childAt instanceof ViewGroup) {
                FlutterConfig flutterConfigA = a((ViewGroup) childAt);
                List<WeakReference<FlutterSurfaceView>> list = flutterConfigA.b;
                if (list != null) {
                    arrayList2.addAll(list);
                }
                List<WeakReference<FlutterView>> list2 = flutterConfigA.f263a;
                if (list2 != null) {
                    arrayList.addAll(list2);
                }
            } else if (childAt != null) {
                if (childAt instanceof FlutterView) {
                    arrayList.add(new WeakReference(childAt));
                }
                if (childAt instanceof FlutterSurfaceView) {
                    arrayList2.add(new WeakReference((FlutterSurfaceView) childAt));
                }
            }
        }
        return new FlutterConfig(arrayList, arrayList2);
    }
}
