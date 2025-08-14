package com.clevertap.android.sdk.inbox;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import androidx.viewpager.widget.ViewPager;

/* loaded from: classes5.dex */
public class CTCarouselViewPager extends ViewPager {
    public CTCarouselViewPager(Context context) {
        super(context);
    }

    public CTCarouselViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    protected void onMeasure(int i, int i2) throws Resources.NotFoundException {
        int i3 = 0;
        for (int i4 = 0; i4 < getChildCount(); i4++) {
            View childAt = getChildAt(i4);
            childAt.measure(i, View.MeasureSpec.makeMeasureSpec(0, 0));
            int measuredHeight = childAt.getMeasuredHeight();
            if (measuredHeight > i3) {
                i3 = measuredHeight;
            }
        }
        if (i3 != 0) {
            i2 = View.MeasureSpec.makeMeasureSpec(i3, 1073741824);
        }
        super.onMeasure(i, i2);
    }

    private int measureHeight(int i, View view) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == 1073741824) {
            return size;
        }
        int measuredHeight = view != null ? view.getMeasuredHeight() : 0;
        return mode == Integer.MIN_VALUE ? Math.min(measuredHeight, size) : measuredHeight;
    }
}
