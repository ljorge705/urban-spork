package androidx.camera.viewfinder;

import android.util.Size;
import android.view.Surface;
import androidx.camera.viewfinder.CameraViewfinder;
import androidx.camera.viewfinder.ViewfinderSurfaceRequest;
import androidx.camera.viewfinder.internal.surface.ViewfinderSurface;
import androidx.camera.viewfinder.internal.utils.Logger;
import androidx.camera.viewfinder.internal.utils.executor.CameraExecutors;
import androidx.camera.viewfinder.internal.utils.futures.FutureCallback;
import androidx.camera.viewfinder.internal.utils.futures.Futures;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Consumer;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
public class ViewfinderSurfaceRequest {
    private static final String TAG = "ViewfinderSurfaceRequest";
    private CameraViewfinder.ImplementationMode mImplementationMode;
    private final ViewfinderSurface mInternalViewfinderSurface;
    private int mLensFacing;
    private final CallbackToFutureAdapter.Completer<Void> mRequestCancellationCompleter;
    private final Size mResolution;
    private int mSensorOrientation;
    private final ListenableFuture<Void> mSessionStatusFuture;
    private final CallbackToFutureAdapter.Completer<Surface> mSurfaceCompleter;
    final ListenableFuture<Surface> mSurfaceFuture;

    public CameraViewfinder.ImplementationMode getImplementationMode() {
        return this.mImplementationMode;
    }

    public int getLensFacing() {
        return this.mLensFacing;
    }

    public Size getResolution() {
        return this.mResolution;
    }

    public int getSensorOrientation() {
        return this.mSensorOrientation;
    }

    ViewfinderSurface getViewfinderSurface() {
        return this.mInternalViewfinderSurface;
    }

    ViewfinderSurfaceRequest(Size size, int i, int i2, CameraViewfinder.ImplementationMode implementationMode) {
        this.mResolution = size;
        this.mLensFacing = i;
        this.mSensorOrientation = i2;
        this.mImplementationMode = implementationMode;
        final String str = "SurfaceRequest[size: " + size + ", id: " + hashCode() + "]";
        final AtomicReference atomicReference = new AtomicReference(null);
        final ListenableFuture future = CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.viewfinder.ViewfinderSurfaceRequest$$ExternalSyntheticLambda2
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                return ViewfinderSurfaceRequest.lambda$new$0(atomicReference, str, completer);
            }
        });
        final CallbackToFutureAdapter.Completer<Void> completer = (CallbackToFutureAdapter.Completer) Preconditions.checkNotNull((CallbackToFutureAdapter.Completer) atomicReference.get());
        this.mRequestCancellationCompleter = completer;
        final AtomicReference atomicReference2 = new AtomicReference(null);
        ListenableFuture<Void> future2 = CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.viewfinder.ViewfinderSurfaceRequest$$ExternalSyntheticLambda3
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public final Object attachCompleter(CallbackToFutureAdapter.Completer completer2) {
                return ViewfinderSurfaceRequest.lambda$new$1(atomicReference2, str, completer2);
            }
        });
        this.mSessionStatusFuture = future2;
        Futures.addCallback(future2, new FutureCallback<Void>() { // from class: androidx.camera.viewfinder.ViewfinderSurfaceRequest.1
            @Override // androidx.camera.viewfinder.internal.utils.futures.FutureCallback
            public void onSuccess(Void r2) {
                Preconditions.checkState(completer.set(null));
            }

            @Override // androidx.camera.viewfinder.internal.utils.futures.FutureCallback
            public void onFailure(Throwable th) {
                if (th instanceof RequestCancelledException) {
                    Preconditions.checkState(future.cancel(false));
                } else {
                    Preconditions.checkState(completer.set(null));
                }
            }
        }, CameraExecutors.directExecutor());
        final CallbackToFutureAdapter.Completer completer2 = (CallbackToFutureAdapter.Completer) Preconditions.checkNotNull((CallbackToFutureAdapter.Completer) atomicReference2.get());
        final AtomicReference atomicReference3 = new AtomicReference(null);
        ListenableFuture<Surface> future3 = CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.viewfinder.ViewfinderSurfaceRequest$$ExternalSyntheticLambda4
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public final Object attachCompleter(CallbackToFutureAdapter.Completer completer3) {
                return ViewfinderSurfaceRequest.lambda$new$2(atomicReference3, str, completer3);
            }
        });
        this.mSurfaceFuture = future3;
        this.mSurfaceCompleter = (CallbackToFutureAdapter.Completer) Preconditions.checkNotNull((CallbackToFutureAdapter.Completer) atomicReference3.get());
        ViewfinderSurface viewfinderSurface = new ViewfinderSurface() { // from class: androidx.camera.viewfinder.ViewfinderSurfaceRequest.2
            @Override // androidx.camera.viewfinder.internal.surface.ViewfinderSurface
            protected ListenableFuture<Surface> provideSurfaceAsync() {
                Logger.d(ViewfinderSurfaceRequest.TAG, "mInternalViewfinderSurface + " + ViewfinderSurfaceRequest.this.mInternalViewfinderSurface + " provideSurface");
                return ViewfinderSurfaceRequest.this.mSurfaceFuture;
            }
        };
        this.mInternalViewfinderSurface = viewfinderSurface;
        final ListenableFuture<Void> terminationFuture = viewfinderSurface.getTerminationFuture();
        Futures.addCallback(future3, new FutureCallback<Surface>() { // from class: androidx.camera.viewfinder.ViewfinderSurfaceRequest.3
            @Override // androidx.camera.viewfinder.internal.utils.futures.FutureCallback
            public void onSuccess(Surface surface) {
                Futures.propagate(terminationFuture, completer2);
            }

            @Override // androidx.camera.viewfinder.internal.utils.futures.FutureCallback
            public void onFailure(Throwable th) {
                if (th instanceof CancellationException) {
                    Preconditions.checkState(completer2.setException(new RequestCancelledException(str + " cancelled.", th)));
                } else {
                    completer2.set(null);
                }
            }
        }, CameraExecutors.directExecutor());
        terminationFuture.addListener(new Runnable() { // from class: androidx.camera.viewfinder.ViewfinderSurfaceRequest$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m281lambda$new$3$androidxcameraviewfinderViewfinderSurfaceRequest();
            }
        }, CameraExecutors.directExecutor());
    }

    static /* synthetic */ Object lambda$new$0(AtomicReference atomicReference, String str, CallbackToFutureAdapter.Completer completer) throws Exception {
        atomicReference.set(completer);
        return str + "-cancellation";
    }

    static /* synthetic */ Object lambda$new$1(AtomicReference atomicReference, String str, CallbackToFutureAdapter.Completer completer) throws Exception {
        atomicReference.set(completer);
        return str + "-status";
    }

    static /* synthetic */ Object lambda$new$2(AtomicReference atomicReference, String str, CallbackToFutureAdapter.Completer completer) throws Exception {
        atomicReference.set(completer);
        return str + "-Surface";
    }

    /* renamed from: lambda$new$3$androidx-camera-viewfinder-ViewfinderSurfaceRequest, reason: not valid java name */
    /* synthetic */ void m281lambda$new$3$androidxcameraviewfinderViewfinderSurfaceRequest() {
        Logger.d(TAG, "mInternalViewfinderSurface + " + this.mInternalViewfinderSurface + " terminateFuture triggered");
        this.mSurfaceFuture.cancel(true);
    }

    protected void finalize() throws Throwable {
        this.mInternalViewfinderSurface.close();
        super.finalize();
    }

    public void markSurfaceSafeToRelease() {
        this.mInternalViewfinderSurface.close();
    }

    void addRequestCancellationListener(Executor executor, Runnable runnable) {
        this.mRequestCancellationCompleter.addCancellationListener(runnable, executor);
    }

    void provideSurface(final Surface surface, Executor executor, final Consumer<Result> consumer) {
        if (this.mSurfaceCompleter.set(surface) || this.mSurfaceFuture.isCancelled()) {
            Futures.addCallback(this.mSessionStatusFuture, new FutureCallback<Void>() { // from class: androidx.camera.viewfinder.ViewfinderSurfaceRequest.4
                @Override // androidx.camera.viewfinder.internal.utils.futures.FutureCallback
                public void onSuccess(Void r3) {
                    consumer.accept(Result.of(0, surface));
                }

                @Override // androidx.camera.viewfinder.internal.utils.futures.FutureCallback
                public void onFailure(Throwable th) {
                    Preconditions.checkState(th instanceof RequestCancelledException, "Camera surface session should only fail with request cancellation. Instead failed due to:\n" + th);
                    consumer.accept(Result.of(1, surface));
                }
            }, executor);
            return;
        }
        Preconditions.checkState(this.mSurfaceFuture.isDone());
        try {
            this.mSurfaceFuture.get();
            executor.execute(new Runnable() { // from class: androidx.camera.viewfinder.ViewfinderSurfaceRequest$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    consumer.accept(ViewfinderSurfaceRequest.Result.of(3, surface));
                }
            });
        } catch (InterruptedException | ExecutionException unused) {
            executor.execute(new Runnable() { // from class: androidx.camera.viewfinder.ViewfinderSurfaceRequest$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    consumer.accept(ViewfinderSurfaceRequest.Result.of(4, surface));
                }
            });
        }
    }

    boolean willNotProvideSurface() {
        return this.mSurfaceCompleter.setException(new ViewfinderSurface.SurfaceUnavailableException("Surface request will not complete."));
    }

    public static final class Builder {
        private CameraViewfinder.ImplementationMode mImplementationMode;
        private int mLensFacing;
        private final Size mResolution;
        private int mSensorOrientation;

        public Builder setImplementationMode(CameraViewfinder.ImplementationMode implementationMode) {
            this.mImplementationMode = implementationMode;
            return this;
        }

        public Builder setLensFacing(int i) {
            this.mLensFacing = i;
            return this;
        }

        public Builder setSensorOrientation(int i) {
            this.mSensorOrientation = i;
            return this;
        }

        public Builder(Size size) {
            this.mLensFacing = 1;
            this.mSensorOrientation = 0;
            this.mResolution = size;
        }

        public Builder(Builder builder) {
            this.mLensFacing = 1;
            this.mSensorOrientation = 0;
            this.mResolution = builder.mResolution;
            this.mImplementationMode = builder.mImplementationMode;
            this.mLensFacing = builder.mLensFacing;
            this.mSensorOrientation = builder.mSensorOrientation;
        }

        public Builder(ViewfinderSurfaceRequest viewfinderSurfaceRequest) {
            this.mLensFacing = 1;
            this.mSensorOrientation = 0;
            this.mResolution = viewfinderSurfaceRequest.getResolution();
            this.mImplementationMode = viewfinderSurfaceRequest.getImplementationMode();
            this.mLensFacing = viewfinderSurfaceRequest.getLensFacing();
            this.mSensorOrientation = viewfinderSurfaceRequest.getSensorOrientation();
        }

        public ViewfinderSurfaceRequest build() {
            int i = this.mLensFacing;
            if (i != 0 && i != 1 && i != 2) {
                throw new IllegalArgumentException("Lens facing value: " + this.mLensFacing + " is invalid");
            }
            int i2 = this.mSensorOrientation;
            if (i2 != 0 && i2 != 90 && i2 != 180 && i2 != 270) {
                throw new IllegalArgumentException("Sensor orientation value: " + this.mSensorOrientation + " is invalid");
            }
            return new ViewfinderSurfaceRequest(this.mResolution, this.mLensFacing, this.mSensorOrientation, this.mImplementationMode);
        }
    }

    static final class RequestCancelledException extends RuntimeException {
        RequestCancelledException(String str, Throwable th) {
            super(str, th);
        }
    }

    static abstract class Result {
        public static final int RESULT_INVALID_SURFACE = 2;
        public static final int RESULT_REQUEST_CANCELLED = 1;
        public static final int RESULT_SURFACE_ALREADY_PROVIDED = 3;
        public static final int RESULT_SURFACE_USED_SUCCESSFULLY = 0;
        public static final int RESULT_WILL_NOT_PROVIDE_SURFACE = 4;

        @Retention(RetentionPolicy.SOURCE)
        public @interface ResultCode {
        }

        public abstract int getResultCode();

        public abstract Surface getSurface();

        static Result of(int i, Surface surface) {
            return new AutoValue_ViewfinderSurfaceRequest_Result(i, surface);
        }

        Result() {
        }
    }
}
