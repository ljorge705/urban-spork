package androidx.camera.viewfinder;

import android.graphics.Bitmap;
import android.graphics.SurfaceTexture;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.FrameLayout;
import androidx.camera.viewfinder.ViewfinderSurfaceRequest;
import androidx.camera.viewfinder.internal.utils.Logger;
import androidx.camera.viewfinder.internal.utils.executor.CameraExecutors;
import androidx.camera.viewfinder.internal.utils.futures.FutureCallback;
import androidx.camera.viewfinder.internal.utils.futures.Futures;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.content.ContextCompat;
import androidx.core.util.Consumer;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;

/* loaded from: classes.dex */
final class TextureViewImplementation extends ViewfinderImplementation {
    private static final String TAG = "TextureViewImpl";
    SurfaceTexture mDetachedSurfaceTexture;
    boolean mIsSurfaceTextureDetachedFromView;
    ListenableFuture<ViewfinderSurfaceRequest.Result> mSurfaceReleaseFuture;
    ViewfinderSurfaceRequest mSurfaceRequest;
    SurfaceTexture mSurfaceTexture;
    TextureView mTextureView;

    @Override // androidx.camera.viewfinder.ViewfinderImplementation
    View getViewfinder() {
        return this.mTextureView;
    }

    /* renamed from: lambda$onSurfaceRequested$0$androidx-camera-viewfinder-TextureViewImplementation, reason: not valid java name */
    /* synthetic */ void m278xad81e6cf(ViewfinderSurfaceRequest viewfinderSurfaceRequest) {
        ViewfinderSurfaceRequest viewfinderSurfaceRequest2 = this.mSurfaceRequest;
        if (viewfinderSurfaceRequest2 == null || viewfinderSurfaceRequest2 != viewfinderSurfaceRequest) {
            return;
        }
        this.mSurfaceRequest = null;
        this.mSurfaceReleaseFuture = null;
    }

    @Override // androidx.camera.viewfinder.ViewfinderImplementation
    void onDetachedFromWindow() {
        this.mIsSurfaceTextureDetachedFromView = true;
    }

    TextureViewImplementation(FrameLayout frameLayout, ViewfinderTransformation viewfinderTransformation) {
        super(frameLayout, viewfinderTransformation);
        this.mIsSurfaceTextureDetachedFromView = false;
    }

    @Override // androidx.camera.viewfinder.ViewfinderImplementation
    void initializeViewfinder() {
        Preconditions.checkNotNull(this.mParent);
        Preconditions.checkNotNull(this.mResolution);
        TextureView textureView = new TextureView(this.mParent.getContext());
        this.mTextureView = textureView;
        textureView.setLayoutParams(new FrameLayout.LayoutParams(this.mResolution.getWidth(), this.mResolution.getHeight()));
        this.mTextureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() { // from class: androidx.camera.viewfinder.TextureViewImplementation.1
            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
                Logger.d(TextureViewImplementation.TAG, "SurfaceTexture available. Size: " + i + "x" + i2);
                TextureViewImplementation.this.mSurfaceTexture = surfaceTexture;
                if (TextureViewImplementation.this.mSurfaceReleaseFuture != null && TextureViewImplementation.this.mSurfaceRequest != null) {
                    Preconditions.checkNotNull(TextureViewImplementation.this.mSurfaceRequest);
                    Logger.d(TextureViewImplementation.TAG, "Surface invalidated " + TextureViewImplementation.this.mSurfaceRequest);
                    TextureViewImplementation.this.mSurfaceRequest.getViewfinderSurface().close();
                    return;
                }
                TextureViewImplementation.this.tryToProvideViewfinderSurface();
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
                Logger.d(TextureViewImplementation.TAG, "SurfaceTexture size changed: " + i + "x" + i2);
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public boolean onSurfaceTextureDestroyed(final SurfaceTexture surfaceTexture) {
                TextureViewImplementation.this.mSurfaceTexture = null;
                if (TextureViewImplementation.this.mSurfaceReleaseFuture == null || TextureViewImplementation.this.mTextureView == null) {
                    return true;
                }
                Futures.addCallback(TextureViewImplementation.this.mSurfaceReleaseFuture, new FutureCallback<ViewfinderSurfaceRequest.Result>() { // from class: androidx.camera.viewfinder.TextureViewImplementation.1.1
                    @Override // androidx.camera.viewfinder.internal.utils.futures.FutureCallback
                    public void onSuccess(ViewfinderSurfaceRequest.Result result) {
                        Preconditions.checkState(result.getResultCode() != 3, "Unexpected result from SurfaceRequest. Surface was provided twice.");
                        Logger.d(TextureViewImplementation.TAG, "SurfaceTexture about to manually be destroyed");
                        surfaceTexture.release();
                        if (TextureViewImplementation.this.mDetachedSurfaceTexture != null) {
                            TextureViewImplementation.this.mDetachedSurfaceTexture = null;
                        }
                    }

                    @Override // androidx.camera.viewfinder.internal.utils.futures.FutureCallback
                    public void onFailure(Throwable th) {
                        throw new IllegalStateException("SurfaceReleaseFuture did not complete nicely.", th);
                    }
                }, ContextCompat.getMainExecutor(TextureViewImplementation.this.mTextureView.getContext()));
                TextureViewImplementation.this.mDetachedSurfaceTexture = surfaceTexture;
                return false;
            }
        });
        this.mParent.removeAllViews();
        this.mParent.addView(this.mTextureView);
    }

    @Override // androidx.camera.viewfinder.ViewfinderImplementation
    void onSurfaceRequested(final ViewfinderSurfaceRequest viewfinderSurfaceRequest) {
        this.mResolution = viewfinderSurfaceRequest.getResolution();
        initializeViewfinder();
        ViewfinderSurfaceRequest viewfinderSurfaceRequest2 = this.mSurfaceRequest;
        if (viewfinderSurfaceRequest2 != null) {
            viewfinderSurfaceRequest2.willNotProvideSurface();
        }
        this.mSurfaceRequest = viewfinderSurfaceRequest;
        viewfinderSurfaceRequest.addRequestCancellationListener(ContextCompat.getMainExecutor(this.mTextureView.getContext()), new Runnable() { // from class: androidx.camera.viewfinder.TextureViewImplementation$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m278xad81e6cf(viewfinderSurfaceRequest);
            }
        });
        tryToProvideViewfinderSurface();
    }

    @Override // androidx.camera.viewfinder.ViewfinderImplementation
    void onAttachedToWindow() {
        reattachSurfaceTexture();
    }

    @Override // androidx.camera.viewfinder.ViewfinderImplementation
    Bitmap getViewfinderBitmap() {
        TextureView textureView = this.mTextureView;
        if (textureView == null || !textureView.isAvailable()) {
            return null;
        }
        return this.mTextureView.getBitmap();
    }

    void tryToProvideViewfinderSurface() {
        SurfaceTexture surfaceTexture;
        if (this.mResolution == null || (surfaceTexture = this.mSurfaceTexture) == null || this.mSurfaceRequest == null) {
            return;
        }
        surfaceTexture.setDefaultBufferSize(this.mResolution.getWidth(), this.mResolution.getHeight());
        final Surface surface = new Surface(this.mSurfaceTexture);
        final ViewfinderSurfaceRequest viewfinderSurfaceRequest = this.mSurfaceRequest;
        final ListenableFuture<ViewfinderSurfaceRequest.Result> future = CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.viewfinder.TextureViewImplementation$$ExternalSyntheticLambda1
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                return this.f$0.m279x3b660ff1(surface, completer);
            }
        });
        this.mSurfaceReleaseFuture = future;
        future.addListener(new Runnable() { // from class: androidx.camera.viewfinder.TextureViewImplementation$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m280x55818e90(surface, future, viewfinderSurfaceRequest);
            }
        }, ContextCompat.getMainExecutor(this.mTextureView.getContext()));
        onSurfaceProvided();
    }

    /* renamed from: lambda$tryToProvideViewfinderSurface$1$androidx-camera-viewfinder-TextureViewImplementation, reason: not valid java name */
    /* synthetic */ Object m279x3b660ff1(Surface surface, final CallbackToFutureAdapter.Completer completer) throws Exception {
        Logger.d(TAG, "Surface set on viewfinder.");
        this.mSurfaceRequest.provideSurface(surface, CameraExecutors.directExecutor(), new Consumer<ViewfinderSurfaceRequest.Result>() { // from class: androidx.camera.viewfinder.TextureViewImplementation.2
            @Override // androidx.core.util.Consumer
            public void accept(ViewfinderSurfaceRequest.Result result) {
                Logger.d(TextureViewImplementation.TAG, "provide surface result = " + result);
                completer.set(result);
            }
        });
        return "provideSurface[request=" + this.mSurfaceRequest + " surface=" + surface + "]";
    }

    /* renamed from: lambda$tryToProvideViewfinderSurface$2$androidx-camera-viewfinder-TextureViewImplementation, reason: not valid java name */
    /* synthetic */ void m280x55818e90(Surface surface, ListenableFuture listenableFuture, ViewfinderSurfaceRequest viewfinderSurfaceRequest) {
        Logger.d(TAG, "Safe to release surface.");
        surface.release();
        if (this.mSurfaceReleaseFuture == listenableFuture) {
            this.mSurfaceReleaseFuture = null;
        }
        if (this.mSurfaceRequest == viewfinderSurfaceRequest) {
            this.mSurfaceRequest = null;
        }
    }

    private void reattachSurfaceTexture() {
        if (!this.mIsSurfaceTextureDetachedFromView || this.mDetachedSurfaceTexture == null) {
            return;
        }
        SurfaceTexture surfaceTexture = this.mTextureView.getSurfaceTexture();
        SurfaceTexture surfaceTexture2 = this.mDetachedSurfaceTexture;
        if (surfaceTexture != surfaceTexture2) {
            this.mTextureView.setSurfaceTexture(surfaceTexture2);
            this.mDetachedSurfaceTexture = null;
            this.mIsSurfaceTextureDetachedFromView = false;
        }
    }
}
