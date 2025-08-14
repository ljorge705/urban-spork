package com.uxcam.screenshot.utils;

import android.graphics.Point;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import com.uxcam.screenaction.utils.Util;

/* loaded from: classes6.dex */
public class ScreenShotBitmapUtil {
    public static ScreenShotBitmapUtil g;

    /* renamed from: a, reason: collision with root package name */
    public float f286a;
    public boolean b = false;
    public int c = 0;
    public int d = 0;
    public int e;
    public int f;

    private ScreenShotBitmapUtil(float f, int i, int i2) {
        this.f286a = f;
        this.e = i;
        this.f = i2;
        new Rect();
    }

    private void calculateBitmapPercentWidthHeight(Rect rect, int i) {
        Point deviceResolution = DeviceInfo.getDeviceResolution(Util.getCurrentApplicationContext());
        int i2 = deviceResolution.y + rect.top;
        int i3 = deviceResolution.x;
        boolean z = i3 > i2;
        this.c = i3;
        if (i3 > i) {
            this.c = i;
            this.f286a = 1.0f;
            if (z) {
                this.f286a = i / i2;
            } else {
                this.f286a = i / i3;
            }
        }
        float f = this.f286a;
        int i4 = (int) (i3 * f);
        this.c = i4;
        int i5 = (int) (i2 * f);
        this.d = i5;
        if (z) {
            this.d = i4;
            this.c = i5;
        }
        int divisibleBySixteenInt = Util.getDivisibleBySixteenInt(this.d);
        int i6 = this.d;
        this.e = divisibleBySixteenInt - i6;
        if (this.f == -1) {
            this.f = this.c > i6 ? 0 : 1;
        }
    }

    private void updateOrientation() {
        DisplayMetrics displayMetrics = Util.getCurrentContext().getResources().getDisplayMetrics();
        if (displayMetrics.widthPixels > displayMetrics.heightPixels) {
            this.f = 0;
        } else {
            this.f = 1;
        }
    }

    public void calculateBitmapMetricsForNewSession(int i) {
        calculateBitmapPercentWidthHeight(new Rect(), i);
        this.b = false;
    }

    public int getBitmapHeight() {
        return this.d;
    }

    public int getBitmapWidth() {
        return this.c;
    }

    public int getCorrectedBitmapHeight() {
        updateOrientation();
        return this.f == 1 ? this.d : this.c;
    }

    public int getCorrectedBitmapWidth() {
        updateOrientation();
        return this.f == 1 ? this.c : this.d;
    }

    public int getHeightOffset() {
        return this.e;
    }

    public float getScalingFactor() {
        return this.f286a;
    }

    public boolean isLandscape() {
        updateOrientation();
        return this.f == 0;
    }

    public boolean isPortrait() {
        return !isLandscape();
    }

    public static ScreenShotBitmapUtil getInstance() {
        if (g == null) {
            g = new ScreenShotBitmapUtil(1.0f, 0, -1);
        }
        return g;
    }

    public void correctlyCalculateBitmapPercentWidthHeight(Rect rect, int i) {
        if (this.b) {
            return;
        }
        this.b = true;
        calculateBitmapPercentWidthHeight(rect, i);
    }
}
