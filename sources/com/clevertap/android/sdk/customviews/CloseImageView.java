package com.clevertap.android.sdk.customviews;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import androidx.appcompat.widget.AppCompatImageView;
import com.clevertap.android.sdk.Logger;

/* loaded from: classes5.dex */
public final class CloseImageView extends AppCompatImageView {
    private final int canvasSize;

    public CloseImageView(Context context) {
        super(context);
        this.canvasSize = getScaledPixels(40);
        setId(199272);
    }

    public CloseImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.canvasSize = getScaledPixels(40);
        setId(199272);
    }

    public CloseImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.canvasSize = getScaledPixels(40);
        setId(199272);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        try {
            Context context = getContext();
            Bitmap bitmapDecodeResource = BitmapFactory.decodeResource(context.getResources(), context.getResources().getIdentifier("ct_close", "drawable", context.getPackageName()), null);
            if (bitmapDecodeResource != null) {
                int i = this.canvasSize;
                canvas.drawBitmap(Bitmap.createScaledBitmap(bitmapDecodeResource, i, i, true), 0.0f, 0.0f, new Paint());
            } else {
                Logger.v("Unable to find inapp notif close button image");
            }
        } catch (Throwable th) {
            Logger.v("Error displaying the inapp notif close button image:", th);
        }
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onMeasure(int i, int i2) {
        int i3 = this.canvasSize;
        setMeasuredDimension(i3, i3);
    }

    private int getScaledPixels(int i) {
        return (int) TypedValue.applyDimension(1, i, getResources().getDisplayMetrics());
    }
}
