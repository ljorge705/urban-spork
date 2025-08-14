package com.clevertap.android.sdk.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/* loaded from: classes5.dex */
public class HorizontalSquareImageView extends ImageView {
    public HorizontalSquareImageView(Context context) {
        super(context);
    }

    public HorizontalSquareImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public HorizontalSquareImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int measuredHeight = getMeasuredHeight();
        setMeasuredDimension(measuredHeight, measuredHeight);
    }
}
