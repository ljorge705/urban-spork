package com.clevertap.android.sdk.inapp;

import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.webkit.WebView;

/* loaded from: classes5.dex */
class CTInAppWebView extends WebView {
    final Point dim;
    private int height;
    private int heightPercentage;
    private int width;
    private int widthPercentage;

    public CTInAppWebView(Context context, int i, int i2, int i3, int i4) {
        super(context);
        this.dim = new Point();
        this.width = i;
        this.height = i2;
        this.widthPercentage = i3;
        this.heightPercentage = i4;
        setHorizontalScrollBarEnabled(false);
        setVerticalScrollBarEnabled(false);
        setHorizontalFadingEdgeEnabled(false);
        setVerticalFadingEdgeEnabled(false);
        setOverScrollMode(2);
        setBackgroundColor(0);
        setId(188293);
    }

    @Override // android.view.View
    public boolean performClick() {
        return super.performClick();
    }

    @Override // android.webkit.WebView, android.widget.AbsoluteLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        updateDimension();
        setMeasuredDimension(this.dim.x, this.dim.y);
    }

    void updateDimension() {
        int i = this.width;
        if (i != 0) {
            this.dim.x = (int) TypedValue.applyDimension(1, i, getResources().getDisplayMetrics());
        } else {
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            this.dim.x = (int) ((displayMetrics.widthPixels * this.widthPercentage) / 100.0f);
        }
        int i2 = this.height;
        if (i2 != 0) {
            this.dim.y = (int) TypedValue.applyDimension(1, i2, getResources().getDisplayMetrics());
        } else {
            DisplayMetrics displayMetrics2 = getResources().getDisplayMetrics();
            this.dim.y = (int) ((displayMetrics2.heightPixels * this.heightPercentage) / 100.0f);
        }
    }
}
