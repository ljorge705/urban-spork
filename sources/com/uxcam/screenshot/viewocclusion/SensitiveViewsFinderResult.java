package com.uxcam.screenshot.viewocclusion;

import android.webkit.WebView;
import com.uxcam.screenaction.models.UXCamOccludeView;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class SensitiveViewsFinderResult {

    /* renamed from: a, reason: collision with root package name */
    public final List<UXCamOccludeView> f288a;
    public final List<UXCamOccludeView> b;
    public final WeakReference<WebView> c;

    public SensitiveViewsFinderResult(ArrayList arrayList, List list, WeakReference weakReference) {
        this.f288a = arrayList;
        this.b = list;
        this.c = weakReference;
    }
}
