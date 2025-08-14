package com.clevertap.android.sdk.gif;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.widget.ImageView;
import androidx.appcompat.widget.AppCompatImageView;

/* loaded from: classes5.dex */
public class GifImageView extends AppCompatImageView implements Runnable {
    private static final String TAG = "GifDecoderView";
    private boolean animating;
    private OnAnimationStart animationStartCallback;
    private OnAnimationStop animationStopCallback;
    private Thread animationThread;
    private final Runnable cleanupRunnable;
    private OnFrameAvailable frameCallback;
    private long framesDisplayDuration;
    private GifDecoder gifDecoder;
    private final Handler handler;
    private boolean renderFrame;
    private boolean shouldClear;
    private Bitmap tmpBitmap;
    private final Runnable updateResults;

    public interface OnAnimationStart {
        void onAnimationStart();
    }

    public interface OnAnimationStop {
        void onAnimationStop();
    }

    public interface OnFrameAvailable {
        Bitmap onFrameAvailable(Bitmap bitmap);
    }

    private boolean canStart() {
        return (this.animating || this.renderFrame) && this.gifDecoder != null && this.animationThread == null;
    }

    public long getFramesDisplayDuration() {
        return this.framesDisplayDuration;
    }

    public OnAnimationStop getOnAnimationStop() {
        return this.animationStopCallback;
    }

    public OnFrameAvailable getOnFrameAvailable() {
        return this.frameCallback;
    }

    public boolean isAnimating() {
        return this.animating;
    }

    public void setFramesDisplayDuration(long j) {
        this.framesDisplayDuration = j;
    }

    public void setOnAnimationStart(OnAnimationStart onAnimationStart) {
        this.animationStartCallback = onAnimationStart;
    }

    public void setOnAnimationStop(OnAnimationStop onAnimationStop) {
        this.animationStopCallback = onAnimationStop;
    }

    public void setOnFrameAvailable(OnFrameAvailable onFrameAvailable) {
        this.frameCallback = onFrameAvailable;
    }

    public GifImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.animationStartCallback = null;
        this.animationStopCallback = null;
        this.frameCallback = null;
        this.framesDisplayDuration = -1L;
        this.handler = new Handler(Looper.getMainLooper());
        this.cleanupRunnable = new Runnable() { // from class: com.clevertap.android.sdk.gif.GifImageView.1
            @Override // java.lang.Runnable
            public void run() {
                GifImageView.this.tmpBitmap = null;
                GifImageView.this.gifDecoder = null;
                GifImageView.this.animationThread = null;
                GifImageView.this.shouldClear = false;
            }
        };
        this.updateResults = new Runnable() { // from class: com.clevertap.android.sdk.gif.GifImageView.2
            @Override // java.lang.Runnable
            public void run() {
                if (GifImageView.this.tmpBitmap == null || GifImageView.this.tmpBitmap.isRecycled()) {
                    return;
                }
                GifImageView gifImageView = GifImageView.this;
                gifImageView.setImageBitmap(gifImageView.tmpBitmap);
                GifImageView.this.setScaleType(ImageView.ScaleType.FIT_CENTER);
            }
        };
    }

    public GifImageView(Context context) {
        super(context);
        this.animationStartCallback = null;
        this.animationStopCallback = null;
        this.frameCallback = null;
        this.framesDisplayDuration = -1L;
        this.handler = new Handler(Looper.getMainLooper());
        this.cleanupRunnable = new Runnable() { // from class: com.clevertap.android.sdk.gif.GifImageView.1
            @Override // java.lang.Runnable
            public void run() {
                GifImageView.this.tmpBitmap = null;
                GifImageView.this.gifDecoder = null;
                GifImageView.this.animationThread = null;
                GifImageView.this.shouldClear = false;
            }
        };
        this.updateResults = new Runnable() { // from class: com.clevertap.android.sdk.gif.GifImageView.2
            @Override // java.lang.Runnable
            public void run() {
                if (GifImageView.this.tmpBitmap == null || GifImageView.this.tmpBitmap.isRecycled()) {
                    return;
                }
                GifImageView gifImageView = GifImageView.this;
                gifImageView.setImageBitmap(gifImageView.tmpBitmap);
                GifImageView.this.setScaleType(ImageView.ScaleType.FIT_CENTER);
            }
        };
    }

    public void clear() {
        this.animating = false;
        this.renderFrame = false;
        this.shouldClear = true;
        stopAnimation();
        this.handler.post(this.cleanupRunnable);
    }

    public int getFrameCount() {
        return this.gifDecoder.getFrameCount();
    }

    public int getGifHeight() {
        return this.gifDecoder.getHeight();
    }

    public int getGifWidth() {
        return this.gifDecoder.getWidth();
    }

    public void gotoFrame(int i) {
        if (this.gifDecoder.getCurrentFrameIndex() == i || !this.gifDecoder.setFrameIndex(i - 1) || this.animating) {
            return;
        }
        this.renderFrame = true;
        startAnimationThread();
    }

    public void resetAnimation() {
        this.gifDecoder.resetLoopIndex();
        gotoFrame(0);
    }

    @Override // java.lang.Runnable
    public void run() throws InterruptedException {
        long jNanoTime;
        OnAnimationStart onAnimationStart = this.animationStartCallback;
        if (onAnimationStart != null) {
            onAnimationStart.onAnimationStart();
        }
        do {
            if (!this.animating && !this.renderFrame) {
                break;
            }
            boolean zAdvance = this.gifDecoder.advance();
            try {
                long jNanoTime2 = System.nanoTime();
                Bitmap nextFrame = this.gifDecoder.getNextFrame();
                this.tmpBitmap = nextFrame;
                OnFrameAvailable onFrameAvailable = this.frameCallback;
                if (onFrameAvailable != null) {
                    this.tmpBitmap = onFrameAvailable.onFrameAvailable(nextFrame);
                }
                jNanoTime = (System.nanoTime() - jNanoTime2) / 1000000;
                try {
                    this.handler.post(this.updateResults);
                } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException unused) {
                }
            } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException unused2) {
                jNanoTime = 0;
            }
            this.renderFrame = false;
            if (!this.animating || !zAdvance) {
                this.animating = false;
                break;
            }
            try {
                int nextDelay = (int) (this.gifDecoder.getNextDelay() - jNanoTime);
                if (nextDelay > 0) {
                    long j = this.framesDisplayDuration;
                    if (j <= 0) {
                        j = nextDelay;
                    }
                    Thread.sleep(j);
                }
            } catch (InterruptedException unused3) {
            }
        } while (this.animating);
        if (this.shouldClear) {
            this.handler.post(this.cleanupRunnable);
        }
        this.animationThread = null;
        OnAnimationStop onAnimationStop = this.animationStopCallback;
        if (onAnimationStop != null) {
            onAnimationStop.onAnimationStop();
        }
    }

    public void setBytes(byte[] bArr) {
        GifDecoder gifDecoder = new GifDecoder();
        this.gifDecoder = gifDecoder;
        try {
            gifDecoder.read(bArr);
            if (this.animating) {
                startAnimationThread();
            } else {
                gotoFrame(0);
            }
        } catch (Exception unused) {
            this.gifDecoder = null;
        }
    }

    public void startAnimation() {
        this.animating = true;
        startAnimationThread();
    }

    public void stopAnimation() {
        this.animating = false;
        Thread thread = this.animationThread;
        if (thread != null) {
            thread.interrupt();
            this.animationThread = null;
        }
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        clear();
    }

    private void startAnimationThread() {
        if (canStart()) {
            Thread thread = new Thread(this);
            this.animationThread = thread;
            thread.start();
        }
    }
}
