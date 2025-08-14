package com.rncamerakit.barcode;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;
import com.facebook.react.uimanager.ViewProps;
import com.rncamerakit.R;
import io.sentry.Session;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BarcodeFrame.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u0000 $2\u00020\u0001:\u0001$B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0018\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u0014H\u0002J\u0010\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0010\u0010\u001c\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0014J\u0018\u0010\u001d\u001a\u00020\u00162\u0006\u0010\u001e\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020\u0006H\u0014J\u0010\u0010 \u001a\u00020\u00162\b\b\u0001\u0010!\u001a\u00020\u0006J\u0010\u0010\"\u001a\u00020\u00162\b\b\u0001\u0010#\u001a\u00020\u0006R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lcom/rncamerakit/barcode/BarcodeFrame;", "Landroid/view/View;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "borderMargin", "", "borderPaint", "Landroid/graphics/Paint;", "frameHeight", "frameRect", "Landroid/graphics/Rect;", "getFrameRect", "()Landroid/graphics/Rect;", "setFrameRect", "(Landroid/graphics/Rect;)V", "frameWidth", "laserPaint", "laserY", "previousFrameTime", "", "drawBorder", "", "canvas", "Landroid/graphics/Canvas;", "drawLaser", "timeElapsed", Session.JsonKeys.INIT, "onDraw", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "setFrameColor", ViewProps.BORDER_COLOR, "setLaserColor", "laserColor", "Companion", "react-native-camera-kit_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class BarcodeFrame extends View {
    private static final int ANIMATION_SPEED = 8;
    private static final double HEIGHT_SCALE = 2.75d;
    private static final int STROKE_WIDTH = 5;
    private static final int WIDTH_SCALE = 7;
    private int borderMargin;
    private Paint borderPaint;
    private int frameHeight;
    private Rect frameRect;
    private int frameWidth;
    private Paint laserPaint;
    private int laserY;
    private long previousFrameTime;

    public final Rect getFrameRect() {
        return this.frameRect;
    }

    public final void setFrameRect(Rect rect) {
        Intrinsics.checkNotNullParameter(rect, "<set-?>");
        this.frameRect = rect;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BarcodeFrame(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.borderPaint = new Paint();
        this.laserPaint = new Paint();
        this.frameRect = new Rect();
        this.previousFrameTime = System.currentTimeMillis();
        init(context);
    }

    private final void init(Context context) {
        Paint paint = new Paint();
        this.borderPaint = paint;
        paint.setStyle(Paint.Style.STROKE);
        this.borderPaint.setStrokeWidth(5.0f);
        this.laserPaint.setStyle(Paint.Style.STROKE);
        this.laserPaint.setStrokeWidth(5.0f);
        this.borderMargin = context.getResources().getDimensionPixelSize(R.dimen.border_length);
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.frameWidth = getMeasuredWidth();
        this.frameHeight = getMeasuredHeight();
        int width = getWidth() / 7;
        int height = (int) (getHeight() / HEIGHT_SCALE);
        this.frameRect.left = width;
        this.frameRect.right = getWidth() - width;
        this.frameRect.top = height;
        this.frameRect.bottom = getHeight() - height;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        long jCurrentTimeMillis = System.currentTimeMillis() - this.previousFrameTime;
        super.onDraw(canvas);
        drawBorder(canvas);
        drawLaser(canvas, jCurrentTimeMillis);
        this.previousFrameTime = System.currentTimeMillis();
        invalidate(this.frameRect);
    }

    private final void drawBorder(Canvas canvas) {
        canvas.drawLine(this.frameRect.left, this.frameRect.top, this.frameRect.left, this.frameRect.top + this.borderMargin, this.borderPaint);
        canvas.drawLine(this.frameRect.left, this.frameRect.top, this.frameRect.left + this.borderMargin, this.frameRect.top, this.borderPaint);
        canvas.drawLine(this.frameRect.left, this.frameRect.bottom, this.frameRect.left, this.frameRect.bottom - this.borderMargin, this.borderPaint);
        canvas.drawLine(this.frameRect.left, this.frameRect.bottom, this.frameRect.left + this.borderMargin, this.frameRect.bottom, this.borderPaint);
        canvas.drawLine(this.frameRect.right, this.frameRect.top, this.frameRect.right - this.borderMargin, this.frameRect.top, this.borderPaint);
        canvas.drawLine(this.frameRect.right, this.frameRect.top, this.frameRect.right, this.frameRect.top + this.borderMargin, this.borderPaint);
        canvas.drawLine(this.frameRect.right, this.frameRect.bottom, this.frameRect.right, this.frameRect.bottom - this.borderMargin, this.borderPaint);
        canvas.drawLine(this.frameRect.right, this.frameRect.bottom, this.frameRect.right - this.borderMargin, this.frameRect.bottom, this.borderPaint);
    }

    private final void drawLaser(Canvas canvas, long timeElapsed) {
        if (this.laserY > this.frameRect.bottom || this.laserY < this.frameRect.top) {
            this.laserY = this.frameRect.top;
        }
        canvas.drawLine(this.frameRect.left + 5, this.laserY, this.frameRect.right - 5, this.laserY, this.laserPaint);
        this.laserY += (int) (timeElapsed / 8);
    }

    public final void setFrameColor(int borderColor) {
        this.borderPaint.setColor(borderColor);
    }

    public final void setLaserColor(int laserColor) {
        this.laserPaint.setColor(laserColor);
    }
}
