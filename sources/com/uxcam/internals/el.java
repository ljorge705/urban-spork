package com.uxcam.internals;

import com.uxcam.screenaction.models.GestureData;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public final class el {

    /* renamed from: a, reason: collision with root package name */
    public final int f137a;
    public final float b;
    public final int c;
    public aa d;
    public ArrayList<GestureData> e = new ArrayList<>();
    public int f;

    public interface aa {
        void a(ArrayList arrayList);
    }

    public el(int i, float f, int i2, aa aaVar) {
        this.f137a = i;
        this.b = f;
        this.c = i2;
        this.d = aaVar;
    }

    public final void a(GestureData gestureData) {
        this.e.add(gestureData);
        if (gestureData.getGesture() == 0) {
            this.f++;
        } else if (gestureData.getGesture() == 1) {
            this.f += 2;
        }
    }
}
