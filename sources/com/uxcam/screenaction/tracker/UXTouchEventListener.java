package com.uxcam.screenaction.tracker;

import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import com.uxcam.screenaction.models.UXCamView;
import com.uxcam.screenaction.tracker.ScreenActionTracker;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes6.dex */
class UXTouchEventListener implements View.OnTouchListener {

    /* renamed from: a, reason: collision with root package name */
    public final View.OnTouchListener f256a;
    public int b;

    public UXTouchEventListener(View.OnTouchListener onTouchListener, int i) {
        this.f256a = onTouchListener;
        this.b = i;
    }

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        try {
            if (ScreenActionTracker.getClearViewArray()) {
                ScreenActionTracker.Companion companion = ScreenActionTracker.INSTANCE;
                ArrayList arrayList = new ArrayList();
                companion.getClass();
                Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
                ScreenActionTracker.viewArrayList = arrayList;
            }
            boolean z = true;
            if (motionEvent.getAction() != 1) {
                z = false;
            }
            ScreenActionTracker.setClearViewArray(z);
            UXCamView uXCamView = new UXCamView(view, new Rect());
            uXCamView.setPosition(this.b);
            ScreenActionTracker.getViewArrayList().add(uXCamView);
            if (this.f256a != null && Thread.currentThread().getStackTrace()[3].getClassName().equals(View.class.getName())) {
                return this.f256a.onTouch(view, motionEvent);
            }
        } catch (Exception unused) {
        }
        return false;
    }
}
