package com.uxcam.screenaction.models;

import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes6.dex */
public class GestureData {

    /* renamed from: a, reason: collision with root package name */
    public String f249a;
    public int b;
    public float c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public Boolean i;
    public boolean j;
    public boolean k;
    public ScreenAction l;
    public ArrayList<GestureData> m;

    public GestureData() {
        this.i = Boolean.FALSE;
        this.j = false;
        this.k = false;
        this.m = new ArrayList<>();
    }

    public GestureData copy() {
        return new GestureData(this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j);
    }

    public void decreaseTimeOffset(float f) {
        if (f < 0.0f) {
            f = 0.0f;
        }
        setTime(this.c - f);
        Iterator<GestureData> it = this.m.iterator();
        while (it.hasNext()) {
            GestureData next = it.next();
            next.setTime(next.getTime() - f);
        }
    }

    public String getActivityName() {
        return this.f249a;
    }

    public int getGesture() {
        return this.b;
    }

    public int getOrientation() {
        return this.h;
    }

    public int getRawX() {
        return this.f;
    }

    public int getRawY() {
        return this.g;
    }

    public ScreenAction getScreenAction() {
        return this.l;
    }

    public float getTime() {
        return this.c;
    }

    public ArrayList<GestureData> getTrail() {
        return this.m;
    }

    public int getX() {
        return this.d;
    }

    public int getY() {
        return this.e;
    }

    public boolean isPlotted() {
        return this.j;
    }

    public boolean isRage() {
        return this.k;
    }

    public Boolean isResponsive() {
        return this.i;
    }

    public boolean isSwipe() {
        int i = this.b;
        return i == 4 || i == 5 || i == 2 || i == 3;
    }

    public void processTrailToMatchWithIos() {
        Iterator<GestureData> it = this.m.iterator();
        while (it.hasNext()) {
            it.next().setGesture(2);
        }
        if (this.m.isEmpty()) {
            return;
        }
        this.m.get(0).setGesture(1);
        ArrayList<GestureData> arrayList = this.m;
        arrayList.get(arrayList.size() - 1).setGesture(3);
    }

    public void setActivityName(String str) {
        this.f249a = str;
    }

    public void setGesture(int i) {
        this.b = i;
    }

    public void setIsPlotted(boolean z) {
        this.j = z;
    }

    public void setOrientation(int i) {
        this.h = i;
    }

    public void setRage(boolean z) {
        this.k = z;
    }

    public void setRawX(int i) {
        this.f = i;
    }

    public void setRawY(int i) {
        this.g = i;
    }

    public void setResponsive(Boolean bool) {
        this.i = bool;
    }

    public void setScreenAction(ScreenAction screenAction) {
        this.l = screenAction;
    }

    public void setTime(float f) {
        if (f < 0.0f) {
            f = 0.0f;
        }
        this.c = f;
    }

    public void setTrail(ArrayList<GestureData> arrayList) {
        this.m = arrayList;
    }

    public void setX(int i) {
        this.d = i;
    }

    public void setY(int i) {
        this.e = i;
    }

    public String toString() {
        StringBuilder sbAppend = new StringBuilder("gesture: ").append(this.b).append(" x: ").append(this.d).append(" y: ").append(this.e).append(" time: ").append(this.c).append(" responsive: ").append(this.i).append(" screenAction: ");
        ScreenAction screenAction = this.l;
        return sbAppend.append(screenAction == null ? "" : screenAction.getJsonObject()).toString();
    }

    public void decreaseOffset(int i, int i2) {
        this.d -= i;
        this.e -= i2;
        Iterator<GestureData> it = this.m.iterator();
        while (it.hasNext()) {
            GestureData next = it.next();
            next.setX(next.getX() - i);
            next.setY(next.getY() - i2);
        }
    }

    public GestureData(int i, float f, int i2, int i3, int i4, int i5) {
        this.i = Boolean.FALSE;
        this.j = false;
        this.k = false;
        this.m = new ArrayList<>();
        this.b = i;
        this.c = f;
        this.d = i2;
        this.e = i3;
        this.f = i4;
        this.g = i5;
    }

    public GestureData(int i, float f, int i2, int i3, int i4, int i5, int i6, Boolean bool, boolean z) {
        this.i = Boolean.FALSE;
        this.j = false;
        this.k = false;
        this.m = new ArrayList<>();
        this.b = i;
        this.c = f;
        this.d = i2;
        this.e = i3;
        this.g = i5;
        this.f = i4;
        this.h = i6;
        this.i = bool;
        this.j = z;
    }
}
