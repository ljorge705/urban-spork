package androidx.camera.viewfinder;

import android.graphics.Bitmap;
import android.os.Handler;
import android.util.Size;
import android.view.PixelCopy;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;
import androidx.camera.viewfinder.ViewfinderSurfaceRequest;
import androidx.camera.viewfinder.internal.utils.Logger;
import androidx.core.content.ContextCompat;
import androidx.core.util.Consumer;
import androidx.core.util.Preconditions;

/* loaded from: classes.dex */
final class SurfaceViewImplementation extends ViewfinderImplementation {
    private static final String TAG = "SurfaceViewImpl";
    final SurfaceRequestCallback mSurfaceRequestCallback;
    SurfaceView mSurfaceView;

    static /* synthetic */ void lambda$onSurfaceRequested$0() {
    }

    @Override // androidx.camera.viewfinder.ViewfinderImplementation
    View getViewfinder() {
        return this.mSurfaceView;
    }

    @Override // androidx.camera.viewfinder.ViewfinderImplementation
    void onAttachedToWindow() {
    }

    @Override // androidx.camera.viewfinder.ViewfinderImplementation
    void onDetachedFromWindow() {
    }

    SurfaceViewImplementation(FrameLayout frameLayout, ViewfinderTransformation viewfinderTransformation) {
        super(frameLayout, viewfinderTransformation);
        this.mSurfaceRequestCallback = new SurfaceRequestCallback();
    }

    @Override // androidx.camera.viewfinder.ViewfinderImplementation
    void initializeViewfinder() {
        Preconditions.checkNotNull(this.mParent);
        Preconditions.checkNotNull(this.mResolution);
        SurfaceView surfaceView = new SurfaceView(this.mParent.getContext());
        this.mSurfaceView = surfaceView;
        surfaceView.setLayoutParams(new FrameLayout.LayoutParams(this.mResolution.getWidth(), this.mResolution.getHeight()));
        this.mParent.removeAllViews();
        this.mParent.addView(this.mSurfaceView);
        this.mSurfaceView.getHolder().addCallback(this.mSurfaceRequestCallback);
    }

    @Override // androidx.camera.viewfinder.ViewfinderImplementation
    void onSurfaceRequested(final ViewfinderSurfaceRequest viewfinderSurfaceRequest) {
        this.mResolution = viewfinderSurfaceRequest.getResolution();
        initializeViewfinder();
        viewfinderSurfaceRequest.addRequestCancellationListener(ContextCompat.getMainExecutor(this.mSurfaceView.getContext()), new Runnable() { // from class: androidx.camera.viewfinder.SurfaceViewImplementation$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                SurfaceViewImplementation.lambda$onSurfaceRequested$0();
            }
        });
        this.mSurfaceView.post(new Runnable() { // from class: androidx.camera.viewfinder.SurfaceViewImplementation$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m277x5c9d7c80(viewfinderSurfaceRequest);
            }
        });
    }

    /* renamed from: lambda$onSurfaceRequested$1$androidx-camera-viewfinder-SurfaceViewImplementation, reason: not valid java name */
    /* synthetic */ void m277x5c9d7c80(ViewfinderSurfaceRequest viewfinderSurfaceRequest) {
        this.mSurfaceRequestCallback.setSurfaceRequest(viewfinderSurfaceRequest);
    }

    @Override // androidx.camera.viewfinder.ViewfinderImplementation
    Bitmap getViewfinderBitmap() {
        SurfaceView surfaceView = this.mSurfaceView;
        if (surfaceView == null || surfaceView.getHolder().getSurface() == null || !this.mSurfaceView.getHolder().getSurface().isValid()) {
            return null;
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(this.mSurfaceView.getWidth(), this.mSurfaceView.getHeight(), Bitmap.Config.ARGB_8888);
        Api24Impl.pixelCopyRequest(this.mSurfaceView, bitmapCreateBitmap, new PixelCopy.OnPixelCopyFinishedListener() { // from class: androidx.camera.viewfinder.SurfaceViewImplementation$$ExternalSyntheticLambda0
            @Override // android.view.PixelCopy.OnPixelCopyFinishedListener
            public final void onPixelCopyFinished(int i) {
                SurfaceViewImplementation.lambda$getViewfinderBitmap$2(i);
            }
        }, this.mSurfaceView.getHandler());
        return bitmapCreateBitmap;
    }

    static /* synthetic */ void lambda$getViewfinderBitmap$2(int i) {
        if (i == 0) {
            Logger.d(TAG, "CameraViewfinder.SurfaceViewImplementation.getBitmap() succeeded");
        } else {
            Logger.e(TAG, "CameraViewfinder.SurfaceViewImplementation.getBitmap() failed with error " + i);
        }
    }

    class SurfaceRequestCallback implements SurfaceHolder.Callback {
        private Size mCurrentSurfaceSize;
        private ViewfinderSurfaceRequest mSurfaceRequest;
        private Size mTargetSize;
        private boolean mWasSurfaceProvided = false;

        SurfaceRequestCallback() {
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            Logger.d(SurfaceViewImplementation.TAG, "Surface created.");
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
            Logger.d(SurfaceViewImplementation.TAG, "Surface changed. Size: " + i2 + "x" + i3);
            this.mCurrentSurfaceSize = new Size(i2, i3);
            tryToComplete();
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            Logger.d(SurfaceViewImplementation.TAG, "Surface destroyed.");
            if (this.mWasSurfaceProvided) {
                invalidateSurface();
            } else {
                cancelPreviousRequest();
            }
            this.mWasSurfaceProvided = false;
            this.mSurfaceRequest = null;
            this.mCurrentSurfaceSize = null;
            this.mTargetSize = null;
        }

        void setSurfaceRequest(ViewfinderSurfaceRequest viewfinderSurfaceRequest) {
            cancelPreviousRequest();
            this.mSurfaceRequest = viewfinderSurfaceRequest;
            Size resolution = viewfinderSurfaceRequest.getResolution();
            this.mTargetSize = resolution;
            this.mWasSurfaceProvided = false;
            if (tryToComplete()) {
                return;
            }
            Logger.d(SurfaceViewImplementation.TAG, "Wait for new Surface creation.");
            SurfaceViewImplementation.this.mSurfaceView.getHolder().setFixedSize(resolution.getWidth(), resolution.getHeight());
        }

        private boolean tryToComplete() {
            if (SurfaceViewImplementation.this.mSurfaceView != null && this.mSurfaceRequest != null) {
                Surface surface = SurfaceViewImplementation.this.mSurfaceView.getHolder().getSurface();
                if (canProvideSurface()) {
                    Logger.d(SurfaceViewImplementation.TAG, "Surface set on viewfinder.");
                    this.mSurfaceRequest.provideSurface(surface, ContextCompat.getMainExecutor(SurfaceViewImplementation.this.mSurfaceView.getContext()), new Consumer() { // from class: androidx.camera.viewfinder.SurfaceViewImplementation$SurfaceRequestCallback$$ExternalSyntheticLambda0
                        @Override // androidx.core.util.Consumer
                        public final void accept(Object obj) {
                            Logger.d(SurfaceViewImplementation.TAG, "provide surface result = " + ((ViewfinderSurfaceRequest.Result) obj));
                        }
                    });
                    this.mWasSurfaceProvided = true;
                    SurfaceViewImplementation.this.onSurfaceProvided();
                    return true;
                }
            }
            return false;
        }

        private boolean canProvideSurface() {
            Size size;
            return (this.mWasSurfaceProvided || this.mSurfaceRequest == null || (size = this.mTargetSize) == null || !size.equals(this.mCurrentSurfaceSize)) ? false : true;
        }

        private void cancelPreviousRequest() {
            if (this.mSurfaceRequest != null) {
                Logger.d(SurfaceViewImplementation.TAG, "Request canceled: " + this.mSurfaceRequest);
                this.mSurfaceRequest.willNotProvideSurface();
            }
        }

        private void invalidateSurface() {
            if (this.mSurfaceRequest != null) {
                Logger.d(SurfaceViewImplementation.TAG, "Surface invalidated " + this.mSurfaceRequest);
                this.mSurfaceRequest.getViewfinderSurface().close();
            }
        }
    }

    private static class Api24Impl {
        private Api24Impl() {
        }

        static void pixelCopyRequest(SurfaceView surfaceView, Bitmap bitmap, PixelCopy.OnPixelCopyFinishedListener onPixelCopyFinishedListener, Handler handler) {
            PixelCopy.request(surfaceView, bitmap, onPixelCopyFinishedListener, handler);
        }
    }
}
