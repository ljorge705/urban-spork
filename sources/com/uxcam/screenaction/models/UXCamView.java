package com.uxcam.screenaction.models;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import com.uxcam.screenaction.utils.ReflectionUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class UXCamView {

    /* renamed from: a, reason: collision with root package name */
    public final Rect f250a;
    public WeakReference<View> b;
    public boolean c;
    public ArrayList<UXCamView> d;
    public int e;
    public final boolean f;
    public final boolean g;
    public final boolean h;
    public final boolean i;
    public final boolean j;
    public final boolean k;
    public final boolean l;
    public final boolean m;

    /* renamed from: n, reason: collision with root package name */
    public final boolean f251n;

    public UXCamView() {
        this.f250a = new Rect();
        this.f = false;
        this.g = false;
        this.l = false;
        this.m = false;
        this.f251n = false;
    }

    public int getPosition() {
        return this.e;
    }

    public Rect getRect() {
        return this.f250a;
    }

    public WeakReference<View> getView() {
        return this.b;
    }

    public ArrayList<UXCamView> getViews() {
        return this.d;
    }

    public boolean hasOnClickListeners() {
        return this.f251n;
    }

    public boolean isClickable() {
        return this.f;
    }

    public boolean isEnabled() {
        return this.g;
    }

    public boolean isScrollContainer() {
        return this.m;
    }

    public boolean isScrollable() {
        return this.h || this.i || this.j || this.k;
    }

    public boolean isScrollableDown() {
        return this.i;
    }

    public boolean isScrollableLeft() {
        return this.j;
    }

    public boolean isScrollableRight() {
        return this.k;
    }

    public boolean isScrollableUp() {
        return this.h;
    }

    public boolean isStopTrackingGestures() {
        return this.c;
    }

    public boolean isViewGroup() {
        return this.l;
    }

    public void setPosition(int i) {
        this.e = i;
    }

    public void setStopTrackingGestures(boolean z) {
        this.c = z;
    }

    public void setView(WeakReference<View> weakReference) {
        this.b = weakReference;
    }

    public void setViews(ArrayList<UXCamView> arrayList) {
        this.d = arrayList;
    }

    public UXCamView(View view, Rect rect) {
        new Rect();
        this.f = false;
        this.g = false;
        this.l = false;
        this.m = false;
        this.f251n = false;
        this.f250a = rect;
        view.getGlobalVisibleRect(rect);
        this.g = view.isEnabled();
        this.f = view.isClickable();
        this.h = view.canScrollVertically(1);
        this.i = view.canScrollVertically(-1);
        this.j = view.canScrollHorizontally(-1);
        this.k = view.canScrollHorizontally(1);
        this.l = view instanceof ViewGroup;
        if (view instanceof CompoundButton) {
            if (ReflectionUtil.getFieldValue("mOnCheckedChangeListener", view) != null) {
                this.f251n = true;
            }
        } else if (view instanceof SeekBar) {
            if (ReflectionUtil.getFieldValue("mOnSeekBarChangeListener", view) != null) {
                this.f251n = true;
            }
        } else {
            this.f251n = view.hasOnClickListeners();
        }
        this.m = view.isScrollContainer();
        this.b = new WeakReference<>(view);
    }
}
