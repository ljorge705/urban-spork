package androidx.camera.viewfinder.internal.surface;

import android.view.Surface;
import androidx.camera.viewfinder.internal.utils.Logger;
import androidx.camera.viewfinder.internal.utils.futures.Futures;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import com.google.common.util.concurrent.ListenableFuture;

/* loaded from: classes.dex */
public abstract class ViewfinderSurface {
    private static final String TAG = "ViewfinderSurface";
    private CallbackToFutureAdapter.Completer<Void> mTerminationCompleter;
    private final Object mLock = new Object();
    private boolean mClosed = false;
    private final ListenableFuture<Void> mTerminationFuture = CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.viewfinder.internal.surface.ViewfinderSurface$$ExternalSyntheticLambda0
        @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
        public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
            return this.f$0.m282x2e1f84bb(completer);
        }
    });

    protected abstract ListenableFuture<Surface> provideSurfaceAsync();

    /* renamed from: lambda$new$0$androidx-camera-viewfinder-internal-surface-ViewfinderSurface, reason: not valid java name */
    /* synthetic */ Object m282x2e1f84bb(CallbackToFutureAdapter.Completer completer) throws Exception {
        synchronized (this.mLock) {
            this.mTerminationCompleter = completer;
        }
        return "ViewfinderSurface-termination(" + this + ")";
    }

    public final ListenableFuture<Surface> getSurface() {
        return provideSurfaceAsync();
    }

    public ListenableFuture<Void> getTerminationFuture() {
        return Futures.nonCancellationPropagating(this.mTerminationFuture);
    }

    public void close() {
        CallbackToFutureAdapter.Completer<Void> completer;
        synchronized (this.mLock) {
            if (this.mClosed) {
                completer = null;
            } else {
                this.mClosed = true;
                completer = this.mTerminationCompleter;
                this.mTerminationCompleter = null;
                Logger.d(TAG, "surface closed,  closed=true " + this);
            }
        }
        if (completer != null) {
            completer.set(null);
        }
    }

    public static final class SurfaceUnavailableException extends Exception {
        public SurfaceUnavailableException(String str) {
            super(str);
        }
    }
}
