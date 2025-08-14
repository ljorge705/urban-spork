package com.onfido.android.sdk.capture.ui.camera.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.FaceDetectorAvcMLKit;
import com.onfido.android.sdk.capture.internal.camera.OnfidoImage;
import com.onfido.android.sdk.capture.internal.camera.usecase.GetDefaultPictureResolutionUseCase;
import com.onfido.android.sdk.capture.internal.util.Dimension;
import com.onfido.android.sdk.capture.internal.util.logging.Timber;
import com.onfido.android.sdk.capture.ui.camera.CameraResolutionProvider;
import com.onfido.android.sdk.capture.ui.camera.FaceDetectionFrame;
import com.onfido.android.sdk.capture.ui.camera.FrameCallback;
import com.onfido.android.sdk.capture.ui.camera.MediaCaptureCallback;
import com.onfido.android.sdk.capture.ui.camera.TorchMode;
import com.onfido.android.sdk.capture.ui.camera.capture.VideoCaptureConfig;
import com.onfido.android.sdk.capture.ui.camera.exception.UnknownCameraException;
import com.onfido.android.sdk.capture.ui.camera.liveness.LivenessConstants;
import com.onfido.android.sdk.capture.ui.camera.view.CameraSource;
import com.onfido.android.sdk.capture.utils.ImageUtils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import kotlinx.coroutines.DebugKt;

/* loaded from: classes2.dex */
public class CameraSourcePreview extends ViewGroup {
    private static final String DOT = ".";
    private static final String MP4 = "mp4";
    private CameraResolutionProvider.Companion.Factory cameraResolutionProviderFactory;
    private CameraSource cameraSource;
    CameraSourceFactory cameraSourceFactory;
    private final Context context;
    private int desiredFaceTrackingFrameHeight;
    private int desiredFaceTrackingFrameWidth;
    private RectF documentOverlayRect;
    private boolean focusImprovementsEnabled;
    private final ImageUtils imageUtils;
    private boolean isFront;
    private List<Camera.Area> mCameraAreaList;
    private CameraPreviewListener mCameraPreviewListener;
    private double mVerticalWeight;
    private int pictureHeight;
    private int pictureWidth;
    private int previewHeight;
    private int previewHorizontalOffset;
    private int previewVerticalOffset;
    private int previewWidth;
    private float previewZoomFactor;
    private SurfaceCallback surfaceCallback;
    private TextureView textureView;
    private int verticalPositionOffset;
    private Surface videoSurface;

    /* renamed from: com.onfido.android.sdk.capture.ui.camera.view.CameraSourcePreview$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$onfido$android$sdk$capture$ui$camera$TorchMode;

        static {
            int[] iArr = new int[TorchMode.values().length];
            $SwitchMap$com$onfido$android$sdk$capture$ui$camera$TorchMode = iArr;
            try {
                iArr[TorchMode.ON.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$onfido$android$sdk$capture$ui$camera$TorchMode[TorchMode.OFF.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$onfido$android$sdk$capture$ui$camera$TorchMode[TorchMode.AUTO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public interface CameraPreviewListener {
        void onCameraNotFound();

        void onCameraPreviewAvailable();

        void onCameraUnavailable();

        void onUnknownCameraError(UnknownCameraException unknownCameraException);
    }

    private class SurfaceCallback implements TextureView.SurfaceTextureListener {
        boolean surfaceAvailable;

        private SurfaceCallback() {
            this.surfaceAvailable = false;
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
            this.surfaceAvailable = true;
            CameraSourcePreview.this.mCameraPreviewListener.onCameraPreviewAvailable();
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
            this.surfaceAvailable = false;
            return false;
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        }
    }

    public CameraSourcePreview(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mCameraAreaList = null;
        this.isFront = false;
        this.mVerticalWeight = 1.0d;
        this.previewHorizontalOffset = 0;
        this.previewVerticalOffset = 0;
        this.pictureWidth = 0;
        this.pictureHeight = 0;
        this.previewHeight = 0;
        this.previewWidth = 0;
        this.verticalPositionOffset = 0;
        this.previewZoomFactor = 1.0f;
        this.desiredFaceTrackingFrameWidth = -1;
        this.desiredFaceTrackingFrameHeight = -1;
        this.focusImprovementsEnabled = false;
        this.cameraResolutionProviderFactory = new GetDefaultPictureResolutionUseCase.Companion.Factory();
        this.documentOverlayRect = null;
        this.context = context;
        setOnClickListener(new View.OnClickListener() { // from class: com.onfido.android.sdk.capture.ui.camera.view.CameraSourcePreview$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m5718x15e0d8e6(view);
            }
        });
        this.imageUtils = new ImageUtils();
        this.cameraSourceFactory = CameraSourceFactory.newInstance();
    }

    private File getOutputVideoMediaFile(File file) {
        return new File(file.getPath() + File.separator + LivenessConstants.LIVENESS_VIDEO_PREFIX + new Date().getTime() + LivenessConstants.VIDEO_RECORDING_FILE_FORMAT);
    }

    private boolean isPortraitMode() {
        int i = this.context.getResources().getConfiguration().orientation;
        if (i == 2) {
            return false;
        }
        if (i == 1) {
            return true;
        }
        Timber.d("isPortraitMode returning false by default", new Object[0]);
        return false;
    }

    static /* synthetic */ Camera.Parameters lambda$setTorchMode$2(TorchMode torchMode, Camera.Parameters parameters, Camera camera) {
        String str;
        Iterator<String> it = parameters.getSupportedFlashModes().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            if (it.next().equalsIgnoreCase(torchMode.name())) {
                int i = AnonymousClass2.$SwitchMap$com$onfido$android$sdk$capture$ui$camera$TorchMode[torchMode.ordinal()];
                if (i == 1) {
                    str = "torch";
                } else if (i == 2) {
                    str = DebugKt.DEBUG_PROPERTY_VALUE_OFF;
                } else if (i == 3) {
                    str = "auto";
                }
                parameters.setFlashMode(str);
            }
        }
        return parameters;
    }

    static /* synthetic */ void lambda$takePicture$1(MediaCaptureCallback mediaCaptureCallback, byte[] bArr, int i, int i2) {
        if (bArr != null) {
            mediaCaptureCallback.onPictureCaptured(bArr, i, i2);
        } else {
            mediaCaptureCallback.onErrorTakingPicture(new CameraSource.TakePictureException("pictureData == null"));
        }
    }

    public void createCameraSource(boolean z, boolean z2, VideoCaptureConfig videoCaptureConfig) {
        stop();
        release();
        SurfaceTexture surfaceTexture = this.textureView.getSurfaceTexture();
        SurfaceCallback surfaceCallback = this.surfaceCallback;
        if (surfaceCallback == null || !surfaceCallback.surfaceAvailable || surfaceTexture == null) {
            throw new UnknownCameraException("SurfaceView is not ready");
        }
        boolean z3 = this.isFront;
        Dimension dimension = new Dimension(getWidth(), getHeight());
        this.videoSurface = new Surface(surfaceTexture);
        try {
            this.cameraSource = this.cameraSourceFactory.createCameraSource(getContext(), z3 ? 1 : 0, z, isPortraitMode(), z2, videoCaptureConfig.getHasAudio(), videoCaptureConfig.getQualityProfile(), videoCaptureConfig.getBitrate(), videoCaptureConfig.getMaxFps(), videoCaptureConfig.getMaxDuration(), this.videoSurface, this.cameraResolutionProviderFactory.create(this.mVerticalWeight, dimension));
        } catch (RuntimeException e) {
            throw new UnknownCameraException("Couldn't create camera source: " + e.getMessage());
        }
    }

    public void finishRecording(boolean z) {
        CameraSource cameraSource = this.cameraSource;
        if (cameraSource == null) {
            Timber.e("Camera Source is null", new Object[0]);
        } else {
            cameraSource.stopVideo();
            this.cameraSource.notifyFinishRecording(z);
        }
    }

    public int getActualPreviewHeight() {
        return getChildCount() > 0 ? getChildAt(0).getHeight() : getHeight();
    }

    public int getActualPreviewWidth() {
        return getChildCount() > 0 ? getChildAt(0).getWidth() : getWidth();
    }

    public FaceDetectionFrame getFaceDetectionFrame(int i) {
        if (this.desiredFaceTrackingFrameWidth < 0 || this.desiredFaceTrackingFrameHeight < 0) {
            this.desiredFaceTrackingFrameWidth = i;
            this.desiredFaceTrackingFrameHeight = Math.round((i * (this.pictureHeight / this.pictureWidth)) / 2.0f) * 2;
        }
        Bitmap bitmap = this.textureView.getBitmap(this.desiredFaceTrackingFrameWidth, this.desiredFaceTrackingFrameHeight);
        return new FaceDetectionFrame(this.imageUtils.getNV21(this.desiredFaceTrackingFrameWidth, this.desiredFaceTrackingFrameHeight, bitmap), this.desiredFaceTrackingFrameWidth, this.desiredFaceTrackingFrameHeight, 0, new OnfidoImage.CropRect(1.0f, 0, 0, getWidth(), getHeight()), bitmap);
    }

    public int getHorizontalOffset(int i, int i2) {
        float f = i;
        float height = getHeight() / i2;
        if (getWidth() / f > height) {
            return 0;
        }
        return (((int) (f * height)) - getWidth()) / 2;
    }

    public boolean getIsFront() {
        return this.isFront;
    }

    public int getPictureHeight() {
        return this.pictureHeight;
    }

    public int getPictureWidth() {
        return this.pictureWidth;
    }

    public int getPreviewHorizontalOffset() {
        return this.previewHorizontalOffset;
    }

    public int getPreviewVerticalOffset() {
        return this.previewVerticalOffset;
    }

    public float getPreviewZoomFactor() {
        return this.previewZoomFactor;
    }

    public int getVerticalOffset(int i, int i2) {
        float width = getWidth() / i;
        float f = i2;
        if (width > getHeight() / f) {
            return (((int) (f * width)) - getHeight()) / 2;
        }
        return 0;
    }

    public int getVerticalPositionOffset() {
        return this.verticalPositionOffset;
    }

    public float getZoomFactor(int i, int i2, boolean z) {
        float width = getWidth() / i;
        return z ? width : Math.max(width, getHeight() / i2);
    }

    public boolean hasCameraSource() {
        return this.cameraSource != null;
    }

    public boolean isReady() {
        return this.surfaceCallback.surfaceAvailable;
    }

    public boolean isRecording() {
        CameraSource cameraSource = this.cameraSource;
        return cameraSource != null && cameraSource.isRecording();
    }

    /* renamed from: lambda$new$0$com-onfido-android-sdk-capture-ui-camera-view-CameraSourcePreview, reason: not valid java name */
    /* synthetic */ void m5718x15e0d8e6(View view) {
        CameraSource cameraSource = this.cameraSource;
        if (cameraSource != null) {
            cameraSource.autoFocusOnce(this.mCameraAreaList, null);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7 = i3 - i;
        int i8 = i4 - i2;
        this.pictureWidth = 320;
        this.pictureHeight = FaceDetectorAvcMLKit.FACE_DETECTION_FRAME_WIDTH;
        this.previewWidth = 320;
        this.previewHeight = FaceDetectorAvcMLKit.FACE_DETECTION_FRAME_WIDTH;
        CameraSource cameraSource = this.cameraSource;
        if (cameraSource != null) {
            try {
                Dimension pictureDimension = cameraSource.getPictureDimension();
                Dimension previewSize = this.cameraSource.getPreviewSize();
                if (pictureDimension != null) {
                    this.pictureWidth = pictureDimension.getWidth();
                    this.pictureHeight = pictureDimension.getHeight();
                }
                if (previewSize != null) {
                    this.previewHeight = previewSize.getHeight();
                    this.previewWidth = previewSize.getWidth();
                }
            } catch (RuntimeException e) {
                Timber.e(e.getCause(), "Couldn't get camera parameters", new Object[0]);
            }
        }
        if (isPortraitMode()) {
            int i9 = this.pictureWidth;
            this.pictureWidth = this.pictureHeight;
            this.pictureHeight = i9;
            int i10 = this.previewWidth;
            this.previewWidth = this.previewHeight;
            this.previewHeight = i10;
        }
        int i11 = this.pictureWidth;
        float f = i7 / i11;
        int i12 = this.pictureHeight;
        float f2 = i8 / i12;
        boolean z2 = this.documentOverlayRect != null;
        this.previewZoomFactor = getZoomFactor(i11, i12, z2);
        if (f > f2 || z2) {
            int i13 = (int) (this.pictureHeight * f);
            int i14 = (i13 - i8) / 2;
            i8 = i13;
            i5 = i14;
            i6 = 0;
        } else {
            int i15 = (int) (this.pictureWidth * f2);
            i6 = (i15 - i7) / 2;
            i7 = i15;
            i5 = 0;
        }
        this.previewHorizontalOffset = i6;
        this.previewVerticalOffset = i5;
        if (!z2) {
            for (int i16 = 0; i16 < getChildCount(); i16++) {
                getChildAt(i16).layout(i6 * (-1), i5 * (-1), i7 - i6, i8 - i5);
            }
            return;
        }
        int i17 = i5 * (-1);
        int i18 = i8 - i5;
        int iRound = Math.round((i17 + ((i18 - i17) / 2.0f)) - this.documentOverlayRect.centerY()) * (-1);
        this.verticalPositionOffset = iRound;
        this.previewVerticalOffset -= iRound;
        for (int i19 = 0; i19 < getChildCount(); i19++) {
            View childAt = getChildAt(i19);
            int i20 = this.verticalPositionOffset;
            childAt.layout(0, i17 + i20, i7 - i6, i20 + i18);
        }
    }

    public void release() {
        Surface surface = this.videoSurface;
        if (surface != null) {
            surface.release();
        }
        if (this.cameraSource != null) {
            setTorchMode(TorchMode.AUTO);
            this.cameraSource.release();
            this.cameraSource = null;
        }
    }

    public void setCameraResolutionProviderFactory(CameraResolutionProvider.Companion.Factory factory) {
        this.cameraResolutionProviderFactory = factory;
    }

    public void setDocumentOverlayRect(RectF rectF) {
        this.documentOverlayRect = rectF;
        requestLayout();
        invalidate();
    }

    public void setFocusImprovementsEnabled(boolean z) {
        this.focusImprovementsEnabled = z;
    }

    public void setFocusMeterAreaWeight(float f, float f2) {
        int i = (int) (f2 * 1000.0f);
        int i2 = (int) (f * 1000.0f);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Camera.Area(new Rect(-i2, -i, i2, i), 800));
        this.mCameraAreaList = arrayList;
    }

    public void setFrameCallback(FrameCallback frameCallback) {
        CameraSource cameraSource = this.cameraSource;
        if (cameraSource != null) {
            cameraSource.setFrameCallback(frameCallback);
        } else {
            Timber.d("Couldn't set FrameCallback because cameraSource is null", new Object[0]);
        }
    }

    public void setIsFront(boolean z) {
        if (!CameraSourceFactory.isFrontCameraSupported()) {
            z = false;
        }
        if (this.isFront != z) {
            this.isFront = z;
        }
    }

    public void setListener(CameraPreviewListener cameraPreviewListener) {
        this.mCameraPreviewListener = cameraPreviewListener;
    }

    public void setPictureWeightSize(float f, float f2) {
        this.mVerticalWeight = f2;
    }

    public void setTorchMode(final TorchMode torchMode) {
        CameraSource cameraSource = this.cameraSource;
        if (cameraSource == null) {
            Timber.e("setTorchMode camera source is null", new Object[0]);
            return;
        }
        String flashMode = cameraSource.getFlashMode();
        if (flashMode == null) {
            return;
        }
        if (flashMode.equals("torch") && torchMode == TorchMode.ON) {
            return;
        }
        if (flashMode.equals("auto") && torchMode == TorchMode.AUTO) {
            return;
        }
        if (flashMode.equals(DebugKt.DEBUG_PROPERTY_VALUE_OFF) && torchMode == TorchMode.OFF) {
            return;
        }
        this.cameraSource.setParameters(new CameraSource.SetParametersCallback() { // from class: com.onfido.android.sdk.capture.ui.camera.view.CameraSourcePreview$$ExternalSyntheticLambda0
            @Override // com.onfido.android.sdk.capture.ui.camera.view.CameraSource.SetParametersCallback
            public final Camera.Parameters call(Camera.Parameters parameters, Camera camera) {
                return CameraSourcePreview.lambda$setTorchMode$2(torchMode, parameters, camera);
            }
        });
    }

    public void setupTextureView() {
        this.textureView = new TextureView(this.context);
        this.textureView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        SurfaceCallback surfaceCallback = new SurfaceCallback();
        this.surfaceCallback = surfaceCallback;
        this.textureView.setSurfaceTextureListener(surfaceCallback);
        removeAllViews();
        addView(this.textureView);
    }

    public void start(boolean z, boolean z2, VideoCaptureConfig videoCaptureConfig) {
        try {
            startWithException(z, z2, videoCaptureConfig);
        } catch (UnknownCameraException e) {
            Timber.e(e, "Camera UnknownCameraException", new Object[0]);
            if (this.mCameraPreviewListener != null) {
                this.mCameraPreviewListener.onUnknownCameraError(e);
            }
        } catch (CameraSource.CameraNotAvailable e2) {
            Timber.e(e2, "Camera unavailable", new Object[0]);
            if (this.mCameraPreviewListener != null) {
                this.mCameraPreviewListener.onCameraUnavailable();
            }
        } catch (CameraSource.CameraNotFound e3) {
            Timber.e(e3, "Camera not found", new Object[0]);
            if (this.mCameraPreviewListener != null) {
                this.mCameraPreviewListener.onCameraNotFound();
            }
        } catch (IOException e4) {
            Timber.e(e4, "Unable to startWithException camera source.", new Object[0]);
            release();
        }
    }

    public void startVideo(boolean z, MediaCaptureCallback mediaCaptureCallback, VideoCaptureConfig videoCaptureConfig) {
        startVideo(z, mediaCaptureCallback, null, videoCaptureConfig);
    }

    public void startWithException(boolean z, boolean z2, VideoCaptureConfig videoCaptureConfig) {
        if (this.cameraSource == null) {
            createCameraSource(z, z2, videoCaptureConfig);
        }
        CameraSource cameraSource = this.cameraSource;
        if (cameraSource == null || !this.surfaceCallback.surfaceAvailable) {
            return;
        }
        cameraSource.start(this.textureView.getSurfaceTexture());
        this.cameraSource.setParameters(new CameraSource.SetParametersCallback() { // from class: com.onfido.android.sdk.capture.ui.camera.view.CameraSourcePreview.1
            @Override // com.onfido.android.sdk.capture.ui.camera.view.CameraSource.SetParametersCallback
            public Camera.Parameters call(Camera.Parameters parameters, Camera camera) {
                if (CameraSourcePreview.this.mCameraAreaList.size() <= parameters.getMaxNumMeteringAreas()) {
                    parameters.setMeteringAreas(CameraSourcePreview.this.mCameraAreaList);
                }
                return parameters;
            }
        });
        requestLayout();
        invalidate();
    }

    public void stop() {
        CameraSource cameraSource = this.cameraSource;
        if (cameraSource != null) {
            cameraSource.stop();
        }
    }

    public void stopRecording() {
        CameraSource cameraSource = this.cameraSource;
        if (cameraSource == null) {
            Timber.e("Camera Source is null", new Object[0]);
        } else {
            cameraSource.stopVideo();
            this.cameraSource.notifyCancelRecording();
        }
    }

    public void takePicture(CameraSource.ShutterCallback shutterCallback, final MediaCaptureCallback mediaCaptureCallback, boolean z) {
        try {
            if (this.cameraSource != null) {
                CameraSource.PictureCallback pictureCallback = new CameraSource.PictureCallback() { // from class: com.onfido.android.sdk.capture.ui.camera.view.CameraSourcePreview$$ExternalSyntheticLambda2
                    @Override // com.onfido.android.sdk.capture.ui.camera.view.CameraSource.PictureCallback
                    public final void onPictureTaken(byte[] bArr, int i, int i2) {
                        CameraSourcePreview.lambda$takePicture$1(mediaCaptureCallback, bArr, i, i2);
                    }
                };
                if (this.focusImprovementsEnabled) {
                    this.cameraSource.tryTakePictureWithAutoFocus(shutterCallback, pictureCallback, this.mCameraAreaList, false, z);
                } else {
                    this.cameraSource.takePicture(shutterCallback, pictureCallback, false, z);
                }
            } else {
                mediaCaptureCallback.onErrorTakingPicture(new CameraSource.TakePictureException("Camera Source has not been initialized"));
            }
        } catch (Exception e) {
            mediaCaptureCallback.onErrorTakingPicture(new CameraSource.TakePictureException(e.getMessage()));
        }
    }

    public void startVideo(boolean z, MediaCaptureCallback mediaCaptureCallback, String str, VideoCaptureConfig videoCaptureConfig) {
        try {
            if (this.cameraSource == null) {
                createCameraSource(z, true, videoCaptureConfig);
                Timber.i("#startVideo cameraSource is null. Creating a new instance.", new Object[0]);
            }
            if (str == null) {
                str = getOutputVideoMediaFile(this.context.getFilesDir()).getAbsolutePath();
            }
            this.cameraSource.startVideo(mediaCaptureCallback, str, videoCaptureConfig.getQualityProfile(), videoCaptureConfig.getBitrate(), videoCaptureConfig.getMaxDuration(), videoCaptureConfig.getMaxFps(), videoCaptureConfig.getHasAudio());
        } catch (UnknownCameraException e) {
            e = e;
            Timber.e(e, "Exception during start video", new Object[0]);
            mediaCaptureCallback.onErrorTakingPicture(new CameraSource.TakePictureException(e.getMessage()));
        } catch (CameraSource.CameraNotAvailable e2) {
            e = e2;
            Timber.e(e, "Exception during start video", new Object[0]);
            mediaCaptureCallback.onErrorTakingPicture(new CameraSource.TakePictureException(e.getMessage()));
        } catch (CameraSource.TakePictureException e3) {
            mediaCaptureCallback.onErrorTakingPicture(e3);
        }
    }
}
