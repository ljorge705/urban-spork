package com.airbnb.lottie.utils;

/* loaded from: classes5.dex */
public class MeanCalculator {

    /* renamed from: n, reason: collision with root package name */
    private int f57n;
    private float sum;

    public float getMean() {
        int i = this.f57n;
        if (i == 0) {
            return 0.0f;
        }
        return this.sum / i;
    }

    public void add(float f) {
        float f2 = this.sum + f;
        this.sum = f2;
        int i = this.f57n + 1;
        this.f57n = i;
        if (i == Integer.MAX_VALUE) {
            this.sum = f2 / 2.0f;
            this.f57n = i / 2;
        }
    }
}
