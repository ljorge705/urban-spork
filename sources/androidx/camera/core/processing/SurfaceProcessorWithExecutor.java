package androidx.camera.core.processing;

import androidx.camera.core.CameraEffect;
import androidx.camera.core.Logger;
import androidx.camera.core.ProcessingException;
import androidx.camera.core.SurfaceOutput;
import androidx.camera.core.SurfaceProcessor;
import androidx.camera.core.SurfaceRequest;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.core.util.Consumer;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Objects;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
public class SurfaceProcessorWithExecutor implements SurfaceProcessorInternal {
    private static final String TAG = "SurfaceProcessor";
    private final Consumer<Throwable> mErrorListener;
    private final Executor mExecutor;
    private final SurfaceProcessor mSurfaceProcessor;

    public Executor getExecutor() {
        return this.mExecutor;
    }

    public SurfaceProcessor getProcessor() {
        return this.mSurfaceProcessor;
    }

    @Override // androidx.camera.core.processing.SurfaceProcessorInternal
    public void release() {
    }

    public SurfaceProcessorWithExecutor(CameraEffect cameraEffect) {
        this.mSurfaceProcessor = (SurfaceProcessor) Objects.requireNonNull(cameraEffect.getSurfaceProcessor());
        this.mExecutor = cameraEffect.getExecutor();
        this.mErrorListener = cameraEffect.getErrorListener();
    }

    @Override // androidx.camera.core.SurfaceProcessor
    public void onInputSurface(final SurfaceRequest surfaceRequest) {
        this.mExecutor.execute(new Runnable() { // from class: androidx.camera.core.processing.SurfaceProcessorWithExecutor$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m184x3d0be2a7(surfaceRequest);
            }
        });
    }

    /* renamed from: lambda$onInputSurface$0$androidx-camera-core-processing-SurfaceProcessorWithExecutor, reason: not valid java name */
    /* synthetic */ void m184x3d0be2a7(SurfaceRequest surfaceRequest) {
        try {
            this.mSurfaceProcessor.onInputSurface(surfaceRequest);
        } catch (ProcessingException e) {
            Logger.e(TAG, "Failed to setup SurfaceProcessor input.", e);
            this.mErrorListener.accept(e);
        }
    }

    @Override // androidx.camera.core.SurfaceProcessor
    public void onOutputSurface(final SurfaceOutput surfaceOutput) {
        this.mExecutor.execute(new Runnable() { // from class: androidx.camera.core.processing.SurfaceProcessorWithExecutor$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m185x6df3c9b5(surfaceOutput);
            }
        });
    }

    /* renamed from: lambda$onOutputSurface$1$androidx-camera-core-processing-SurfaceProcessorWithExecutor, reason: not valid java name */
    /* synthetic */ void m185x6df3c9b5(SurfaceOutput surfaceOutput) {
        try {
            this.mSurfaceProcessor.onOutputSurface(surfaceOutput);
        } catch (ProcessingException e) {
            Logger.e(TAG, "Failed to setup SurfaceProcessor output.", e);
            this.mErrorListener.accept(e);
        }
    }

    @Override // androidx.camera.core.processing.SurfaceProcessorInternal
    public ListenableFuture<Void> snapshot(int i, int i2) {
        return Futures.immediateFailedFuture(new Exception("Snapshot not supported by external SurfaceProcessor"));
    }
}
