package com.uxcam.screenshot.floatingpanel;

import android.graphics.Rect;
import android.view.View;
import android.view.WindowManager;

/* loaded from: classes6.dex */
public final class FloatingPanelData {

    /* renamed from: a, reason: collision with root package name */
    public final View f260a;
    public final Rect b;
    public final WindowManager.LayoutParams c;

    public FloatingPanelData(View view, Rect rect, WindowManager.LayoutParams layoutParams) {
        this.f260a = view;
        this.b = rect;
        this.c = layoutParams;
    }
}
