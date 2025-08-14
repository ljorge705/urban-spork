package androidx.camera.viewfinder;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.hardware.display.DisplayManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.Display;
import android.view.Surface;
import android.view.View;
import android.widget.FrameLayout;
import androidx.camera.viewfinder.internal.quirk.DeviceQuirks;
import androidx.camera.viewfinder.internal.quirk.SurfaceViewNotCroppedByParentQuirk;
import androidx.camera.viewfinder.internal.quirk.SurfaceViewStretchedQuirk;
import androidx.camera.viewfinder.internal.surface.ViewfinderSurfaceProvider;
import androidx.camera.viewfinder.internal.utils.Logger;
import androidx.camera.viewfinder.internal.utils.Threads;
import androidx.camera.viewfinder.internal.utils.TransformUtils;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import com.google.common.util.concurrent.ListenableFuture;

/* loaded from: classes.dex */
public final class CameraViewfinder extends FrameLayout {
    private static final int DEFAULT_BACKGROUND_COLOR = 17170444;
    private static final ImplementationMode DEFAULT_IMPL_MODE = ImplementationMode.PERFORMANCE;
    private static final String TAG = "CameraViewFinder";
    ViewfinderSurfaceRequest mCurrentSurfaceRequest;
    private final DisplayRotationListener mDisplayRotationListener;
    ViewfinderImplementation mImplementation;
    ImplementationMode mImplementationMode;
    private final View.OnLayoutChangeListener mOnLayoutChangeListener;
    private final Looper mRequiredLooper;
    final ViewfinderSurfaceProvider mSurfaceProvider;
    final ViewfinderTransformation mViewfinderTransformation;

    /* renamed from: lambda$new$0$androidx-camera-viewfinder-CameraViewfinder, reason: not valid java name */
    /* synthetic */ void m276lambda$new$0$androidxcameraviewfinderCameraViewfinder(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        if (i3 - i == i7 - i5 && i4 - i2 == i8 - i6) {
            return;
        }
        redrawViewfinder();
    }

    public CameraViewfinder(Context context) {
        this(context, null);
    }

    public CameraViewfinder(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CameraViewfinder(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public CameraViewfinder(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        ViewfinderTransformation viewfinderTransformation = new ViewfinderTransformation();
        this.mViewfinderTransformation = viewfinderTransformation;
        this.mDisplayRotationListener = new DisplayRotationListener();
        this.mRequiredLooper = Looper.myLooper();
        this.mOnLayoutChangeListener = new View.OnLayoutChangeListener() { // from class: androidx.camera.viewfinder.CameraViewfinder$$ExternalSyntheticLambda0
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
                this.f$0.m276lambda$new$0$androidxcameraviewfinderCameraViewfinder(view, i3, i4, i5, i6, i7, i8, i9, i10);
            }
        };
        this.mSurfaceProvider = new ViewfinderSurfaceProvider() { // from class: androidx.camera.viewfinder.CameraViewfinder.1
            @Override // androidx.camera.viewfinder.internal.surface.ViewfinderSurfaceProvider
            public void onSurfaceRequested(ViewfinderSurfaceRequest viewfinderSurfaceRequest) {
                ViewfinderImplementation surfaceViewImplementation;
                if (!Threads.isMainThread()) {
                    throw new IllegalStateException("onSurfaceRequested must be called on the main  thread");
                }
                Logger.d(CameraViewfinder.TAG, "Surface requested by Viewfinder.");
                if (viewfinderSurfaceRequest.getImplementationMode() != null) {
                    CameraViewfinder.this.mImplementationMode = viewfinderSurfaceRequest.getImplementationMode();
                }
                CameraViewfinder cameraViewfinder = CameraViewfinder.this;
                if (CameraViewfinder.shouldUseTextureView(cameraViewfinder.mImplementationMode)) {
                    CameraViewfinder cameraViewfinder2 = CameraViewfinder.this;
                    surfaceViewImplementation = new TextureViewImplementation(cameraViewfinder2, cameraViewfinder2.mViewfinderTransformation);
                } else {
                    CameraViewfinder cameraViewfinder3 = CameraViewfinder.this;
                    surfaceViewImplementation = new SurfaceViewImplementation(cameraViewfinder3, cameraViewfinder3.mViewfinderTransformation);
                }
                cameraViewfinder.mImplementation = surfaceViewImplementation;
                CameraViewfinder.this.mImplementation.onSurfaceRequested(viewfinderSurfaceRequest);
                Display display = CameraViewfinder.this.getDisplay();
                if (display != null) {
                    CameraViewfinder.this.mViewfinderTransformation.setTransformationInfo(TransformUtils.createTransformInfo(viewfinderSurfaceRequest.getResolution(), display, viewfinderSurfaceRequest.getLensFacing() == 0, viewfinderSurfaceRequest.getSensorOrientation()), viewfinderSurfaceRequest.getResolution(), viewfinderSurfaceRequest.getLensFacing() == 0);
                    CameraViewfinder.this.redrawViewfinder();
                }
            }
        };
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.Viewfinder, i, i2);
        ViewCompat.saveAttributeDataForStyleable(this, context, R.styleable.Viewfinder, attributeSet, typedArrayObtainStyledAttributes, i, i2);
        try {
            setScaleType(ScaleType.fromId(typedArrayObtainStyledAttributes.getInteger(R.styleable.Viewfinder_scaleType, viewfinderTransformation.getScaleType().getId())));
            this.mImplementationMode = ImplementationMode.fromId(typedArrayObtainStyledAttributes.getInteger(R.styleable.Viewfinder_implementationMode, DEFAULT_IMPL_MODE.getId()));
            typedArrayObtainStyledAttributes.recycle();
            if (getBackground() == null) {
                setBackgroundColor(ContextCompat.getColor(getContext(), 17170444));
            }
        } catch (Throwable th) {
            typedArrayObtainStyledAttributes.recycle();
            throw th;
        }
    }

    public ImplementationMode getImplementationMode() {
        checkUiThread();
        return this.mImplementationMode;
    }

    public void setScaleType(ScaleType scaleType) {
        checkUiThread();
        this.mViewfinderTransformation.setScaleType(scaleType);
        redrawViewfinder();
    }

    public ScaleType getScaleType() {
        checkUiThread();
        return this.mViewfinderTransformation.getScaleType();
    }

    public ListenableFuture<Surface> requestSurfaceAsync(ViewfinderSurfaceRequest viewfinderSurfaceRequest) {
        checkUiThread();
        ViewfinderSurfaceRequest viewfinderSurfaceRequest2 = this.mCurrentSurfaceRequest;
        if (viewfinderSurfaceRequest2 != null && viewfinderSurfaceRequest.equals(viewfinderSurfaceRequest2)) {
            return this.mCurrentSurfaceRequest.getViewfinderSurface().getSurface();
        }
        ViewfinderSurfaceRequest viewfinderSurfaceRequest3 = this.mCurrentSurfaceRequest;
        if (viewfinderSurfaceRequest3 != null) {
            viewfinderSurfaceRequest3.markSurfaceSafeToRelease();
        }
        ListenableFuture<Surface> surface = viewfinderSurfaceRequest.getViewfinderSurface().getSurface();
        this.mCurrentSurfaceRequest = viewfinderSurfaceRequest;
        provideSurfaceIfReady();
        return surface;
    }

    public Bitmap getBitmap() {
        checkUiThread();
        ViewfinderImplementation viewfinderImplementation = this.mImplementation;
        if (viewfinderImplementation == null) {
            return null;
        }
        return viewfinderImplementation.getBitmap();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        addOnLayoutChangeListener(this.mOnLayoutChangeListener);
        ViewfinderImplementation viewfinderImplementation = this.mImplementation;
        if (viewfinderImplementation != null) {
            viewfinderImplementation.onAttachedToWindow();
        }
        startListeningToDisplayChange();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeOnLayoutChangeListener(this.mOnLayoutChangeListener);
        ViewfinderImplementation viewfinderImplementation = this.mImplementation;
        if (viewfinderImplementation != null) {
            viewfinderImplementation.onDetachedFromWindow();
        }
        ViewfinderSurfaceRequest viewfinderSurfaceRequest = this.mCurrentSurfaceRequest;
        if (viewfinderSurfaceRequest != null) {
            viewfinderSurfaceRequest.markSurfaceSafeToRelease();
            this.mCurrentSurfaceRequest = null;
        }
        stopListeningToDisplayChange();
    }

    static boolean shouldUseTextureView(ImplementationMode implementationMode) {
        boolean z = (DeviceQuirks.get(SurfaceViewStretchedQuirk.class) == null && DeviceQuirks.get(SurfaceViewNotCroppedByParentQuirk.class) == null) ? false : true;
        if (Build.VERSION.SDK_INT <= 24 || z) {
            Logger.d(TAG, "Implementation mode to set is not supported, forcing to use TextureView, because transform APIs are not supported on these devices.");
            return true;
        }
        int i = AnonymousClass2.$SwitchMap$androidx$camera$viewfinder$CameraViewfinder$ImplementationMode[implementationMode.ordinal()];
        if (i == 1) {
            return true;
        }
        if (i == 2) {
            return false;
        }
        throw new IllegalArgumentException("Invalid implementation mode: " + implementationMode);
    }

    /* renamed from: androidx.camera.viewfinder.CameraViewfinder$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$androidx$camera$viewfinder$CameraViewfinder$ImplementationMode;

        static {
            int[] iArr = new int[ImplementationMode.values().length];
            $SwitchMap$androidx$camera$viewfinder$CameraViewfinder$ImplementationMode = iArr;
            try {
                iArr[ImplementationMode.COMPATIBLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$androidx$camera$viewfinder$CameraViewfinder$ImplementationMode[ImplementationMode.PERFORMANCE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    void redrawViewfinder() {
        ViewfinderImplementation viewfinderImplementation = this.mImplementation;
        if (viewfinderImplementation != null) {
            viewfinderImplementation.redrawViewfinder();
        }
    }

    private boolean provideSurfaceIfReady() {
        ViewfinderSurfaceRequest viewfinderSurfaceRequest = this.mCurrentSurfaceRequest;
        ViewfinderSurfaceProvider viewfinderSurfaceProvider = this.mSurfaceProvider;
        if (viewfinderSurfaceProvider == null || viewfinderSurfaceRequest == null) {
            return false;
        }
        viewfinderSurfaceProvider.onSurfaceRequested(viewfinderSurfaceRequest);
        return true;
    }

    private void checkUiThread() {
        if (this.mRequiredLooper != null && Looper.myLooper() != this.mRequiredLooper) {
            throw new RuntimeException(new Throwable("A method was called on thread '" + Thread.currentThread().getName() + "'. All methods must be called on the same thread. (Expected Looper " + this.mRequiredLooper + ", but called on " + Looper.myLooper() + "."));
        }
    }

    public enum ImplementationMode {
        PERFORMANCE(0),
        COMPATIBLE(1);

        private final int mId;

        int getId() {
            return this.mId;
        }

        ImplementationMode(int i) {
            this.mId = i;
        }

        static ImplementationMode fromId(int i) {
            for (ImplementationMode implementationMode : values()) {
                if (implementationMode.mId == i) {
                    return implementationMode;
                }
            }
            throw new IllegalArgumentException("Unknown implementation mode id " + i);
        }
    }

    public enum ScaleType {
        FILL_START(0),
        FILL_CENTER(1),
        FILL_END(2),
        FIT_START(3),
        FIT_CENTER(4),
        FIT_END(5);

        private final int mId;

        int getId() {
            return this.mId;
        }

        ScaleType(int i) {
            this.mId = i;
        }

        static ScaleType fromId(int i) {
            for (ScaleType scaleType : values()) {
                if (scaleType.mId == i) {
                    return scaleType;
                }
            }
            throw new IllegalArgumentException("Unknown scale type id " + i);
        }
    }

    private void startListeningToDisplayChange() {
        DisplayManager displayManager = getDisplayManager();
        if (displayManager == null) {
            return;
        }
        displayManager.registerDisplayListener(this.mDisplayRotationListener, new Handler(Looper.getMainLooper()));
    }

    private void stopListeningToDisplayChange() {
        DisplayManager displayManager = getDisplayManager();
        if (displayManager == null) {
            return;
        }
        displayManager.unregisterDisplayListener(this.mDisplayRotationListener);
    }

    private DisplayManager getDisplayManager() {
        Context context = getContext();
        if (context == null) {
            return null;
        }
        return (DisplayManager) context.getApplicationContext().getSystemService("display");
    }

    class DisplayRotationListener implements DisplayManager.DisplayListener {
        @Override // android.hardware.display.DisplayManager.DisplayListener
        public void onDisplayAdded(int i) {
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public void onDisplayRemoved(int i) {
        }

        DisplayRotationListener() {
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public void onDisplayChanged(int i) {
            ViewfinderSurfaceRequest viewfinderSurfaceRequest;
            Display display = CameraViewfinder.this.getDisplay();
            if (display == null || display.getDisplayId() != i || (viewfinderSurfaceRequest = CameraViewfinder.this.mCurrentSurfaceRequest) == null) {
                return;
            }
            CameraViewfinder.this.mViewfinderTransformation.updateTransformInfo(TransformUtils.createTransformInfo(viewfinderSurfaceRequest.getResolution(), display, viewfinderSurfaceRequest.getLensFacing() == 0, viewfinderSurfaceRequest.getSensorOrientation()));
            CameraViewfinder.this.redrawViewfinder();
        }
    }
}
