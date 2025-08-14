package com.onfido.android.sdk.capture.ui.camera.view;

import android.content.Context;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.WindowManager;
import com.onfido.android.sdk.capture.internal.util.Dimension;
import com.onfido.android.sdk.capture.internal.util.logging.Timber;
import com.onfido.android.sdk.capture.ui.camera.CameraResolutionProvider;
import com.onfido.android.sdk.capture.ui.camera.FrameCallback;
import com.onfido.android.sdk.capture.ui.camera.MediaCaptureCallback;
import com.onfido.android.sdk.capture.ui.camera.exception.UnknownCameraException;
import com.onfido.android.sdk.capture.ui.camera.view.CameraSource;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public class CameraSource implements MediaRecorder.OnInfoListener {
    private static final float ASPECT_RATIO_TOLERANCE = 0.01f;
    private static final long AUTO_FOCUS_TRY_TIMEOUT_IN_SECONDS = 5;
    public static final int CAMERA_FACING_BACK = 0;
    public static final int CAMERA_FACING_FRONT = 1;
    private static final int DUMMY_TEXTURE_NAME = 100;
    private static final int MAX_JPEG_QUALITY = 100;
    private static final int SIZE_NOT_SET = -1;
    private static final String TAG = "OpenCameraSource";
    private Disposable compressingDisposable;
    private boolean hasAudio;
    private boolean hasVideo;
    private boolean isPortrait;
    private Camera mCamera;
    private final Object mCameraLock;
    private CameraResolutionProvider mCameraResolutionProvider;
    private Context mContext;
    private SurfaceTexture mDummySurfaceTexture;
    private SurfaceView mDummySurfaceView;
    private int mFacing;
    private String mFlashMode;
    private String mFocusMode;
    private FrameCallback mFrameCallback;
    private boolean mIsCameraPreviewStarted;
    private boolean mIsRecording;
    private MediaCaptureCallback mMediaCaptureCallback;
    private MediaRecorder mMediaRecorder;
    private String mOutputVideoFilePath;
    private Dimension mPreviewDimension;
    private String mPreviousFocusMode;
    private float mRequestedFps;
    private int mRequestedPictureHeight;
    private int mRequestedPictureWidth;
    public int mRequestedPreviewHeight;
    public int mRequestedPreviewWidth;
    private int mRotation;
    Camera.Parameters mTemporaryParameters;
    private Dimension mVideoDimension;
    private int videoMaxDurationMs;
    private int videoQuality;
    private int videoRecordingBitRate;
    private Surface videoSurface;

    public interface AutoFocusCallback {
        void onAutoFocus(boolean z);
    }

    public interface AutoFocusMoveCallback {
        void onAutoFocusMoving(boolean z);
    }

    public static class Builder {
        private final CameraSource mCameraSource;

        public Builder(Context context) {
            CameraSource cameraSource = new CameraSource();
            this.mCameraSource = cameraSource;
            if (context == null) {
                throw new IllegalArgumentException("No context supplied.");
            }
            cameraSource.mContext = context;
        }

        public CameraSource build() {
            return this.mCameraSource;
        }

        public Builder setCameraResolutionProvider(CameraResolutionProvider cameraResolutionProvider) {
            this.mCameraSource.mCameraResolutionProvider = cameraResolutionProvider;
            return this;
        }

        public Builder setFacing(int i) {
            if (i != 0 && i != 1) {
                throw new IllegalArgumentException("Invalid camera: " + i);
            }
            this.mCameraSource.mFacing = i;
            return this;
        }

        public Builder setFlashMode(String str) {
            this.mCameraSource.mFlashMode = str;
            return this;
        }

        public Builder setFocusMode(String str) {
            this.mCameraSource.mFocusMode = str;
            return this;
        }

        public Builder setIsPortrait(boolean z) {
            this.mCameraSource.isPortrait = z;
            return this;
        }

        public Builder setRequestedFps(float f) {
            if (f <= 0.0f) {
                throw new IllegalArgumentException("Invalid fps: " + f);
            }
            this.mCameraSource.mRequestedFps = f;
            return this;
        }

        public Builder setupRecording(int i, int i2, int i3, int i4, Surface surface, boolean z) {
            CameraSource cameraSource = this.mCameraSource;
            cameraSource.videoQuality = i;
            cameraSource.videoRecordingBitRate = i2;
            cameraSource.mRequestedFps = i4;
            cameraSource.videoMaxDurationMs = i3;
            cameraSource.videoSurface = surface;
            cameraSource.hasVideo = true;
            cameraSource.hasAudio = z;
            return this;
        }
    }

    private class CameraAutoFocusCallback implements Camera.AutoFocusCallback {
        private AutoFocusCallback mDelegate;

        private CameraAutoFocusCallback() {
        }

        @Override // android.hardware.Camera.AutoFocusCallback
        public void onAutoFocus(boolean z, Camera camera) {
            AutoFocusCallback autoFocusCallback = this.mDelegate;
            if (autoFocusCallback != null) {
                autoFocusCallback.onAutoFocus(z);
            }
        }
    }

    private class CameraAutoFocusMoveCallback implements Camera.AutoFocusMoveCallback {
        private AutoFocusMoveCallback mDelegate;

        private CameraAutoFocusMoveCallback() {
        }

        @Override // android.hardware.Camera.AutoFocusMoveCallback
        public void onAutoFocusMoving(boolean z, Camera camera) {
            AutoFocusMoveCallback autoFocusMoveCallback = this.mDelegate;
            if (autoFocusMoveCallback != null) {
                autoFocusMoveCallback.onAutoFocusMoving(z);
            }
        }
    }

    static class CameraNotAvailable extends Exception {
        CameraNotAvailable(String str) {
            super(str);
        }
    }

    static class CameraNotFound extends Exception {
        CameraNotFound(String str) {
            super(str);
        }
    }

    private class CameraPreviewCallback implements Camera.PreviewCallback {
        private CameraPreviewCallback() {
        }

        @Override // android.hardware.Camera.PreviewCallback
        public void onPreviewFrame(byte[] bArr, Camera camera) {
            if (CameraSource.this.mFrameCallback == null || bArr == null) {
                return;
            }
            try {
                Camera.Parameters parameters = camera.getParameters();
                int i = parameters.getPreviewSize().width;
                int i2 = parameters.getPreviewSize().height;
                CameraSource cameraSource = CameraSource.this;
                cameraSource.mFrameCallback.onNextFrame(bArr, i, i2, cameraSource.mRotation);
            } catch (RuntimeException e) {
                Timber.e(e, "Error occured on CameraPreviewCallback", new Object[0]);
                MediaCaptureCallback mediaCaptureCallback = CameraSource.this.mMediaCaptureCallback;
                if (mediaCaptureCallback != null) {
                    mediaCaptureCallback.onErrorTakingPicture(new TakePictureException("Error occured on CameraPreviewCallback", e));
                }
            }
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    private @interface FlashMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    private @interface FocusMode {
    }

    private interface IteratorSizeList<T> {
        int getLength();

        T getObject(int i);

        Camera.Size getSize(int i);
    }

    public interface PictureCallback {
        void onPictureTaken(byte[] bArr, int i, int i2);
    }

    private class PictureDoneCallback implements Camera.PictureCallback {
        private PictureCallback mDelegate;

        private PictureDoneCallback() {
        }

        @Override // android.hardware.Camera.PictureCallback
        public void onPictureTaken(byte[] bArr, Camera camera) {
            PictureCallback pictureCallback = this.mDelegate;
            if (pictureCallback != null) {
                pictureCallback.onPictureTaken(bArr, camera.getParameters().getPictureSize().width, camera.getParameters().getPictureSize().height);
            }
        }
    }

    private class PictureStartCallback implements Camera.ShutterCallback {
        private ShutterCallback mDelegate;

        private PictureStartCallback() {
        }

        @Override // android.hardware.Camera.ShutterCallback
        public void onShutter() {
            ShutterCallback shutterCallback = this.mDelegate;
            if (shutterCallback != null) {
                shutterCallback.onShutter();
            }
        }
    }

    public interface SetParametersCallback {
        Camera.Parameters call(Camera.Parameters parameters, Camera camera);
    }

    public interface ShutterCallback {
        void onShutter();
    }

    private static class SizePair {
        private Dimension mPicture;
        private final Dimension mPreview;

        public SizePair(Camera.Size size, Camera.Size size2) {
            this.mPreview = new Dimension(size.width, size.height);
            if (size2 != null) {
                this.mPicture = new Dimension(size2.width, size2.height);
            }
        }

        public Dimension pictureSize() {
            return this.mPicture;
        }

        public Dimension previewSize() {
            return this.mPreview;
        }
    }

    public static class TakePictureException extends Exception {
        public TakePictureException() {
            super("Error taking picture");
        }

        public TakePictureException(String str) {
            super(str);
        }

        public TakePictureException(String str, Throwable th) {
            super(str, th);
        }
    }

    private CameraSource() {
        this.mCameraLock = new Object();
        this.mFacing = 0;
        this.mRequestedFps = 30.0f;
        this.mRequestedPreviewWidth = 1024;
        this.mRequestedPreviewHeight = 768;
        this.compressingDisposable = null;
        this.mRequestedPictureWidth = -1;
        this.mRequestedPictureHeight = -1;
        this.mFocusMode = null;
        this.mPreviousFocusMode = null;
        this.mFlashMode = null;
        this.mIsCameraPreviewStarted = false;
        this.hasVideo = false;
        this.hasAudio = false;
        this.isPortrait = false;
        this.mMediaCaptureCallback = null;
        this.mCameraResolutionProvider = null;
        this.mTemporaryParameters = null;
    }

    private void addToTemporaryParameters(Camera.Parameters parameters) {
        Camera.Parameters parameters2 = this.mTemporaryParameters;
        if (parameters2 == null) {
            this.mTemporaryParameters = parameters;
        } else {
            parameters2.unflatten(this.mTemporaryParameters.flatten() + parameters.flatten());
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:59:?, code lost:
    
        throw r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:?, code lost:
    
        throw r4;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v1, types: [android.hardware.Camera$Parameters, java.lang.Throwable] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private android.hardware.Camera createCamera() throws com.onfido.android.sdk.capture.ui.camera.view.CameraSource.CameraNotAvailable, com.onfido.android.sdk.capture.ui.camera.view.CameraSource.CameraNotFound {
        /*
            Method dump skipped, instructions count: 469
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onfido.android.sdk.capture.ui.camera.view.CameraSource.createCamera():android.hardware.Camera");
    }

    private byte[] createPreviewBuffer(Dimension dimension) {
        byte[] bArr = new byte[((int) Math.ceil(((dimension.getHeight() * dimension.getWidth()) * ImageFormat.getBitsPerPixel(17)) / 8.0d)) + 1];
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        if (byteBufferWrap.hasArray() && byteBufferWrap.array() == bArr) {
            return bArr;
        }
        throw new IllegalStateException("Failed to create valid buffer for camera source.");
    }

    private static Map<Camera.Size, List<Camera.Size>> generateValidSizeList(Camera.Parameters parameters, List<Camera.Size> list) {
        int i;
        List<Camera.Size> supportedPreviewSizes = parameters.getSupportedPreviewSizes();
        HashMap map = new HashMap();
        for (Camera.Size size : supportedPreviewSizes) {
            float f = size.width / size.height;
            ArrayList arrayList = new ArrayList();
            map.put(size, arrayList);
            float f2 = 2.1474836E9f;
            Camera.Size size2 = null;
            for (Camera.Size size3 : list) {
                float fAbs = Math.abs(f - (size3.width / size3.height));
                if (fAbs < f2 && (i = size3.width) <= 1920 && i >= 940) {
                    size2 = size3;
                    f2 = fAbs;
                }
                if (fAbs < 0.01f) {
                    arrayList.add(size3);
                }
            }
            if (arrayList.size() == 0 && size2 != null) {
                arrayList.add(size2);
            }
        }
        return map;
    }

    private static int getIdForRequestedCamera(int i, boolean z) {
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        for (int i2 = 0; i2 < Camera.getNumberOfCameras(); i2++) {
            try {
                Camera.getCameraInfo(i2, cameraInfo);
                if (cameraInfo.facing == i) {
                    return i2;
                }
            } catch (RuntimeException unused) {
            }
        }
        if (z) {
            return getIdForRequestedCamera(i == 0 ? 1 : 0, false);
        }
        return -1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:?, code lost:
    
        throw r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private com.onfido.android.sdk.capture.internal.util.Dimension getVideoDimension() throws com.onfido.android.sdk.capture.ui.camera.view.CameraSource.CameraNotAvailable {
        /*
            r8 = this;
            com.onfido.android.sdk.capture.ui.camera.view.CameraSource$CameraNotAvailable r0 = new com.onfido.android.sdk.capture.ui.camera.view.CameraSource$CameraNotAvailable
            java.lang.String r1 = "Could not open requested camera."
            r0.<init>(r1)
            android.hardware.Camera r1 = r8.getCamera()     // Catch: java.lang.RuntimeException -> L5c
            android.hardware.Camera$Parameters r2 = r1.getParameters()     // Catch: java.lang.RuntimeException -> L5c
            java.util.List r0 = r2.getSupportedVideoSizes()
            if (r0 == 0) goto L5a
            int r3 = r8.mRequestedPreviewWidth
            int r4 = r8.mRequestedPreviewHeight
            int r5 = r8.mRequestedPictureWidth
            int r6 = r8.mRequestedPictureHeight
            java.util.List r7 = r2.getSupportedVideoSizes()
            com.onfido.android.sdk.capture.ui.camera.view.CameraSource$SizePair r0 = selectSizePair(r2, r3, r4, r5, r6, r7)
            r1 = 0
            if (r0 == 0) goto L53
            com.onfido.android.sdk.capture.internal.util.Dimension r2 = r0.pictureSize()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "initMediaRecorder selected sizePair - previewSize: "
            r3.<init>(r4)
            com.onfido.android.sdk.capture.internal.util.Dimension r4 = r0.previewSize()
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r4 = ", pictureSize: "
            java.lang.StringBuilder r3 = r3.append(r4)
            com.onfido.android.sdk.capture.internal.util.Dimension r0 = r0.pictureSize()
            java.lang.StringBuilder r0 = r3.append(r0)
            java.lang.String r0 = r0.toString()
            java.lang.Object[] r1 = new java.lang.Object[r1]
            com.onfido.android.sdk.capture.internal.util.logging.Timber.d(r0, r1)
            goto L5b
        L53:
            java.lang.Object[] r0 = new java.lang.Object[r1]
            java.lang.String r1 = "initMediaRecorder selected sizePair = null"
            com.onfido.android.sdk.capture.internal.util.logging.Timber.d(r1, r0)
        L5a:
            r2 = 0
        L5b:
            return r2
        L5c:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onfido.android.sdk.capture.ui.camera.view.CameraSource.getVideoDimension():com.onfido.android.sdk.capture.internal.util.Dimension");
    }

    private void initMediaRecorder(MediaCaptureCallback mediaCaptureCallback, String str, boolean z) throws IllegalStateException, IOException, IllegalArgumentException {
        if (this.mMediaRecorder == null) {
            if (this.mVideoDimension == null) {
                this.mVideoDimension = getVideoDimension();
            }
            setupVideoCapture(getCamera(), this.mVideoDimension, this.hasAudio, z);
        }
        this.mMediaRecorder.setOutputFile(str);
        this.mMediaRecorder.prepare();
        this.mMediaRecorder.start();
        this.mMediaCaptureCallback = mediaCaptureCallback;
    }

    private boolean isCameraPreviewRunning() {
        boolean z;
        synchronized (this.mCameraLock) {
            z = getCamera() != null && this.mIsCameraPreviewStarted;
        }
        return z;
    }

    static /* synthetic */ Camera.Parameters lambda$autoFocusOnce$7(List list, Camera.Parameters parameters, Camera camera) {
        parameters.setFocusAreas(list);
        return parameters;
    }

    private void resetTemporaryParameters() {
        this.mTemporaryParameters = null;
    }

    private void revertAutoFocusMode() {
        try {
            synchronized (this.mCameraLock) {
                if (this.mPreviousFocusMode == null) {
                    return;
                }
                Timber.d("Reverting focus mode from:" + this.mFocusMode + " to:" + this.mPreviousFocusMode, new Object[0]);
                setParameters(new SetParametersCallback() { // from class: com.onfido.android.sdk.capture.ui.camera.view.CameraSource$$ExternalSyntheticLambda7
                    @Override // com.onfido.android.sdk.capture.ui.camera.view.CameraSource.SetParametersCallback
                    public final Camera.Parameters call(Camera.Parameters parameters, Camera camera) {
                        return this.f$0.m5705x477d8734(parameters, camera);
                    }
                });
                cancelAutoFocus();
                Timber.d("mFocusMode:" + this.mFocusMode + " mPreviousFocusMode:" + this.mPreviousFocusMode, new Object[0]);
                this.mPreviousFocusMode = null;
            }
        } catch (RuntimeException e) {
            Timber.e(e, "Reverting auto focus failed", new Object[0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Camera.Size selectBestSize(final List<Camera.Size> list, int i, int i2) {
        return (Camera.Size) selectBestSize(new IteratorSizeList<Camera.Size>() { // from class: com.onfido.android.sdk.capture.ui.camera.view.CameraSource.2
            @Override // com.onfido.android.sdk.capture.ui.camera.view.CameraSource.IteratorSizeList
            public int getLength() {
                return list.size();
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.onfido.android.sdk.capture.ui.camera.view.CameraSource.IteratorSizeList
            public Camera.Size getObject(int i3) {
                return getSize(i3);
            }

            @Override // com.onfido.android.sdk.capture.ui.camera.view.CameraSource.IteratorSizeList
            public Camera.Size getSize(int i3) {
                return (Camera.Size) list.get(i3);
            }
        }, i, i2);
    }

    private int[] selectPreviewFpsRange(Camera.Parameters parameters, float f) {
        int i = (int) (f * 1000.0f);
        int[] iArr = null;
        int i2 = Integer.MAX_VALUE;
        for (int[] iArr2 : parameters.getSupportedPreviewFpsRange()) {
            int iAbs = Math.abs(i - iArr2[0]) + Math.abs(i - iArr2[1]);
            if (iAbs < i2) {
                iArr = iArr2;
                i2 = iAbs;
            }
        }
        return iArr;
    }

    private static SizePair selectSizePair(Camera.Parameters parameters, int i, int i2, final int i3, final int i4, List<Camera.Size> list) {
        final Map<Camera.Size, List<Camera.Size>> mapGenerateValidSizeList = generateValidSizeList(parameters, list);
        final Camera.Size[] sizeArr = (Camera.Size[]) mapGenerateValidSizeList.keySet().toArray(new Camera.Size[mapGenerateValidSizeList.size()]);
        for (Map.Entry<Camera.Size, List<Camera.Size>> entry : mapGenerateValidSizeList.entrySet()) {
            Camera.Size key = entry.getKey();
            List<Camera.Size> value = entry.getValue();
            Timber.d("previewSize: width=" + key.width + ", height=" + key.height, new Object[0]);
            for (Camera.Size size : value) {
                Timber.d("--- pictureSize: width=" + size.width + ", height=" + size.height, new Object[0]);
            }
        }
        return (SizePair) selectBestSize(new IteratorSizeList<SizePair>() { // from class: com.onfido.android.sdk.capture.ui.camera.view.CameraSource.1
            @Override // com.onfido.android.sdk.capture.ui.camera.view.CameraSource.IteratorSizeList
            public int getLength() {
                return mapGenerateValidSizeList.size();
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.onfido.android.sdk.capture.ui.camera.view.CameraSource.IteratorSizeList
            public SizePair getObject(int i5) {
                int i6;
                Camera.Size size2 = getSize(i5);
                List list2 = (List) mapGenerateValidSizeList.get(size2);
                int i7 = i3;
                return new SizePair(size2, (i7 == -1 || (i6 = i4) == -1) ? list2.size() > 0 ? (Camera.Size) list2.get(0) : null : CameraSource.selectBestSize((List<Camera.Size>) list2, i7, i6));
            }

            @Override // com.onfido.android.sdk.capture.ui.camera.view.CameraSource.IteratorSizeList
            public Camera.Size getSize(int i5) {
                return sizeArr[i5];
            }
        }, i, i2);
    }

    private static boolean setFlashMode(Camera.Parameters parameters, String str) {
        if (str != null) {
            List<String> supportedFlashModes = parameters.getSupportedFlashModes();
            if (supportedFlashModes != null && supportedFlashModes.contains(str)) {
                parameters.setFlashMode(str);
                return true;
            }
            Timber.i("Camera flash mode: " + str + " is not supported on this device.", new Object[0]);
        }
        return false;
    }

    private static boolean setFocusMode(Camera.Parameters parameters, String str) {
        Timber.d("setFocusMode:" + str, new Object[0]);
        if (str != null) {
            if (parameters.getSupportedFocusModes().contains(str)) {
                parameters.setFocusMode(str);
                return true;
            }
            Timber.i("Camera focus mode: " + str + " is not supported on this device.", new Object[0]);
        }
        return false;
    }

    private void setJpegQuality(int i) {
        Camera.Parameters parameters = getCamera().getParameters();
        parameters.setJpegQuality(i);
        getCamera().setParameters(parameters);
    }

    private boolean setParameters(Camera.Parameters parameters) {
        synchronized (this.mCameraLock) {
            if (getCamera() == null || parameters == null) {
                return false;
            }
            if (!isCameraPreviewRunning()) {
                addToTemporaryParameters(parameters);
                return false;
            }
            try {
                getCamera().setParameters(parameters);
                this.mFlashMode = parameters.getFlashMode();
                updateFocusMode(parameters.getFocusMode());
                return true;
            } catch (Exception e) {
                Timber.e(e, "Error: failed to set parameters", new Object[0]);
                return false;
            }
        }
    }

    private void setRotation(Camera camera, Camera.Parameters parameters, int i) {
        int i2;
        int i3;
        int rotation = ((WindowManager) this.mContext.getSystemService("window")).getDefaultDisplay().getRotation();
        int i4 = 0;
        if (rotation != 0) {
            if (rotation == 1) {
                i4 = 90;
            } else if (rotation == 2) {
                i4 = 180;
            } else if (rotation != 3) {
                Timber.e("Bad rotation value: " + rotation, new Object[0]);
            } else {
                i4 = 270;
            }
        }
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        Camera.getCameraInfo(i, cameraInfo);
        if (cameraInfo.facing == 1) {
            i2 = (cameraInfo.orientation + i4) % 360;
            i3 = (360 - i2) % 360;
        } else {
            i2 = ((cameraInfo.orientation - i4) + 360) % 360;
            i3 = i2;
        }
        this.mRotation = i2 / 90;
        camera.setDisplayOrientation(i3);
        parameters.setRotation(i2);
    }

    private void setTemporaryParametersIntoCamera() {
        Camera.Parameters parameters = this.mTemporaryParameters;
        if (parameters != null) {
            setParameters(parameters);
            this.mTemporaryParameters = null;
        }
    }

    private void setupVideoCapture(Camera camera, Dimension dimension, boolean z, boolean z2) throws IllegalStateException, IllegalArgumentException {
        Camera.Parameters parameters;
        int[] iArrSelectPreviewFpsRange;
        MediaRecorder mediaRecorder = new MediaRecorder();
        this.mMediaRecorder = mediaRecorder;
        mediaRecorder.setCamera(camera);
        if (z) {
            this.mMediaRecorder.setAudioSource(5);
        }
        this.mMediaRecorder.setVideoSource(1);
        this.mMediaRecorder.setMaxDuration(this.videoMaxDurationMs);
        this.mMediaRecorder.setOnInfoListener(this);
        CamcorderProfile camcorderProfile = CamcorderProfile.get(this.mFacing, CamcorderProfile.hasProfile(this.mFacing, this.videoQuality) ? this.videoQuality : 0);
        this.mMediaRecorder.setOutputFormat(camcorderProfile.fileFormat);
        int i = camcorderProfile.videoFrameRate;
        try {
            parameters = camera.getParameters();
        } catch (RuntimeException unused) {
            parameters = null;
        }
        float f = this.mRequestedFps;
        if (f > 0.0f && parameters != null && (iArrSelectPreviewFpsRange = selectPreviewFpsRange(parameters, f)) != null && iArrSelectPreviewFpsRange.length > 0) {
            i = iArrSelectPreviewFpsRange[1] / 1000;
        }
        try {
            this.mMediaRecorder.setVideoFrameRate(i);
        } catch (RuntimeException e) {
            Timber.e(e, "Exception while trying to setVideoFrameRate", new Object[0]);
        }
        if (dimension != null && !z2) {
            int height = dimension.getHeight();
            int width = dimension.getWidth();
            if (height > width) {
                width = dimension.getHeight();
                height = dimension.getWidth();
            }
            this.mMediaRecorder.setVideoSize(width, height);
        }
        this.mMediaRecorder.setVideoEncodingBitRate(this.videoRecordingBitRate);
        this.mMediaRecorder.setVideoEncoder(camcorderProfile.videoCodec);
        if (z) {
            this.mMediaRecorder.setAudioEncodingBitRate(camcorderProfile.audioBitRate);
            this.mMediaRecorder.setAudioChannels(camcorderProfile.audioChannels);
            this.mMediaRecorder.setAudioSamplingRate(camcorderProfile.audioSampleRate);
            this.mMediaRecorder.setAudioEncoder(camcorderProfile.audioCodec);
        }
        this.mMediaRecorder.setPreviewDisplay(this.videoSurface);
        this.mMediaRecorder.setOrientationHint(this.mRotation * 90);
    }

    private void startPreview() {
        synchronized (this.mCameraLock) {
            try {
                try {
                    getCamera().startPreview();
                    this.mIsCameraPreviewStarted = true;
                    setTemporaryParametersIntoCamera();
                } catch (RuntimeException e) {
                    throw new UnknownCameraException((String) Objects.requireNonNull(e.getMessage()));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private void stopPreview() {
        synchronized (this.mCameraLock) {
            if (this.mIsRecording) {
                stopVideo();
                notifyCancelRecording();
            }
            getCamera().stopPreview();
            this.mIsCameraPreviewStarted = false;
        }
    }

    private static Dimension toSize(Camera.Size size) {
        return new Dimension(size.width, size.height);
    }

    private void updateFocusMode(String str) {
        boolean z = (str == null || str.equals(this.mFocusMode)) ? false : true;
        Timber.d("focus mode updated:" + z + " newFocusMode:" + str + " mFocusMode:" + this.mFocusMode, new Object[0]);
        this.mPreviousFocusMode = z ? this.mFocusMode : this.mPreviousFocusMode;
        this.mFocusMode = str;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void autoFocus(AutoFocusCallback autoFocusCallback) {
        synchronized (this.mCameraLock) {
            if (getCamera() != null) {
                CameraAutoFocusCallback cameraAutoFocusCallback = null;
                Object[] objArr = 0;
                if (autoFocusCallback != null) {
                    CameraAutoFocusCallback cameraAutoFocusCallback2 = new CameraAutoFocusCallback();
                    cameraAutoFocusCallback2.mDelegate = autoFocusCallback;
                    cameraAutoFocusCallback = cameraAutoFocusCallback2;
                }
                try {
                    getCamera().autoFocus(cameraAutoFocusCallback);
                } catch (Exception e) {
                    Timber.e(e, "Handled exception: Auto focus failed", new Object[0]);
                    if (autoFocusCallback != null) {
                        autoFocusCallback.onAutoFocus(false);
                    }
                }
            }
        }
    }

    public void autoFocusOnce(List<Camera.Area> list, AutoFocusCallback autoFocusCallback) {
        autoFocusOnce(list, autoFocusCallback, true);
    }

    public void cancelAutoFocus() {
        synchronized (this.mCameraLock) {
            if (getCamera() != null) {
                getCamera().cancelAutoFocus();
            }
        }
    }

    public int doZoom(float f) {
        synchronized (this.mCameraLock) {
            int i = 0;
            if (getCamera() == null) {
                return 0;
            }
            Camera.Parameters parameters = getCamera().getParameters();
            if (!parameters.isZoomSupported()) {
                Timber.w("Zoom is not supported on this device", new Object[0]);
                return 0;
            }
            int maxZoom = parameters.getMaxZoom();
            float zoom = parameters.getZoom() + 1;
            int iRound = Math.round(f > 1.0f ? zoom + (f * (maxZoom / 10.0f)) : zoom * f) - 1;
            if (iRound >= 0) {
                i = iRound > maxZoom ? maxZoom : iRound;
            }
            parameters.setZoom(i);
            getCamera().setParameters(parameters);
            return i;
        }
    }

    protected Camera getCamera() {
        return this.mCamera;
    }

    public int getCameraFacing() {
        return this.mFacing;
    }

    public String getFlashMode() {
        return this.mFlashMode;
    }

    public String getFocusMode() {
        return this.mFocusMode;
    }

    public Camera.Parameters getParameters() {
        Camera.Parameters parameters;
        synchronized (this.mCameraLock) {
            parameters = getCamera() != null ? getCamera().getParameters() : null;
        }
        return parameters;
    }

    public Dimension getPictureDimension() {
        Camera.Size pictureSize = getPictureSize();
        return new Dimension(pictureSize.width, pictureSize.height);
    }

    public Camera.Size getPictureSize() {
        return getCamera().getParameters().getPictureSize();
    }

    public Dimension getPreviewSize() {
        return this.mPreviewDimension;
    }

    public boolean isRecording() {
        return this.mIsRecording;
    }

    /* renamed from: lambda$autoFocusOnce$8$com-onfido-android-sdk-capture-ui-camera-view-CameraSource, reason: not valid java name */
    /* synthetic */ void m5704xea735e90(AutoFocusCallback autoFocusCallback, boolean z, boolean z2) {
        if (autoFocusCallback != null) {
            autoFocusCallback.onAutoFocus(z2);
        }
        if (z) {
            revertAutoFocusMode();
        }
    }

    /* renamed from: lambda$revertAutoFocusMode$6$com-onfido-android-sdk-capture-ui-camera-view-CameraSource, reason: not valid java name */
    /* synthetic */ Camera.Parameters m5705x477d8734(Camera.Parameters parameters, Camera camera) {
        Timber.d("Reverting focus mode from:" + parameters.getFocusMode() + " to:" + this.mPreviousFocusMode, new Object[0]);
        if ("auto".equals(parameters.getFocusMode()) && !"auto".equals(this.mPreviousFocusMode)) {
            parameters.setFocusAreas(null);
            cancelAutoFocus();
            Timber.d("canceledAutoFocus and set focus areas to null", new Object[0]);
        }
        setFocusMode(parameters, this.mPreviousFocusMode);
        return parameters;
    }

    /* renamed from: lambda$takePicture$0$com-onfido-android-sdk-capture-ui-camera-view-CameraSource, reason: not valid java name */
    /* synthetic */ void m5706xd143de0b(int i, ShutterCallback shutterCallback) {
        setJpegQuality(i);
        if (shutterCallback != null) {
            shutterCallback.onShutter();
        }
    }

    /* renamed from: lambda$takePictureWithAutoFocus$4$com-onfido-android-sdk-capture-ui-camera-view-CameraSource, reason: not valid java name */
    /* synthetic */ void m5707x33be6fb8(PictureCallback pictureCallback, boolean z, byte[] bArr, int i, int i2) {
        Timber.d("onPictureTaken", new Object[0]);
        pictureCallback.onPictureTaken(bArr, i, i2);
        if (z) {
            revertAutoFocusMode();
        }
    }

    /* renamed from: lambda$takePictureWithAutoFocus$5$com-onfido-android-sdk-capture-ui-camera-view-CameraSource, reason: not valid java name */
    /* synthetic */ void m5708x39c23b17(ShutterCallback shutterCallback, final PictureCallback pictureCallback, boolean z, boolean z2, final boolean z3) {
        try {
            Timber.d("onAutoFocus:" + z3, new Object[0]);
            takePicture(shutterCallback, new PictureCallback() { // from class: com.onfido.android.sdk.capture.ui.camera.view.CameraSource$$ExternalSyntheticLambda0
                @Override // com.onfido.android.sdk.capture.ui.camera.view.CameraSource.PictureCallback
                public final void onPictureTaken(byte[] bArr, int i, int i2) {
                    this.f$0.m5707x33be6fb8(pictureCallback, z3, bArr, i, i2);
                }
            }, z, z2);
        } catch (TakePictureException unused) {
            Timber.d("Error taking picture after autofocus", new Object[0]);
        }
    }

    /* renamed from: lambda$tryTakePictureWithAutoFocus$1$com-onfido-android-sdk-capture-ui-camera-view-CameraSource, reason: not valid java name */
    /* synthetic */ void m5709x8a9921fa(AtomicBoolean atomicBoolean, ShutterCallback shutterCallback, PictureCallback pictureCallback, boolean z, boolean z2, Long l) throws TakePictureException {
        if (atomicBoolean.compareAndSet(false, true)) {
            Timber.d("Auto focus timeout! Taking picture without auto focus", new Object[0]);
            takePicture(shutterCallback, pictureCallback, z, z2);
        }
    }

    /* renamed from: lambda$tryTakePictureWithAutoFocus$2$com-onfido-android-sdk-capture-ui-camera-view-CameraSource, reason: not valid java name */
    /* synthetic */ void m5710x909ced59(PictureCallback pictureCallback, boolean z, byte[] bArr, int i, int i2) {
        Timber.d("Picture taken.", new Object[0]);
        pictureCallback.onPictureTaken(bArr, i, i2);
        if (z) {
            revertAutoFocusMode();
        }
    }

    /* renamed from: lambda$tryTakePictureWithAutoFocus$3$com-onfido-android-sdk-capture-ui-camera-view-CameraSource, reason: not valid java name */
    /* synthetic */ void m5711x96a0b8b8(AtomicBoolean atomicBoolean, Disposable disposable, ShutterCallback shutterCallback, final PictureCallback pictureCallback, boolean z, boolean z2, final boolean z3) {
        Timber.d("Auto focus result: %s", Boolean.valueOf(z3));
        if (!atomicBoolean.compareAndSet(false, true)) {
            Timber.d("Auto focus callback received after timeout. Skipping.", new Object[0]);
            return;
        }
        try {
            disposable.dispose();
            Timber.d("Taking picture after auto focus callback received.", new Object[0]);
            takePicture(shutterCallback, new PictureCallback() { // from class: com.onfido.android.sdk.capture.ui.camera.view.CameraSource$$ExternalSyntheticLambda8
                @Override // com.onfido.android.sdk.capture.ui.camera.view.CameraSource.PictureCallback
                public final void onPictureTaken(byte[] bArr, int i, int i2) {
                    this.f$0.m5710x909ced59(pictureCallback, z3, bArr, i, i2);
                }
            }, z, z2);
        } catch (TakePictureException unused) {
            Timber.d("Error taking picture after autofocus.", new Object[0]);
        }
    }

    public void notifyCancelRecording() {
        synchronized (this.mCameraLock) {
            MediaCaptureCallback mediaCaptureCallback = this.mMediaCaptureCallback;
            if (mediaCaptureCallback != null) {
                mediaCaptureCallback.onVideoCanceled();
            }
        }
    }

    public void notifyFinishRecording(boolean z) {
        synchronized (this.mCameraLock) {
            MediaCaptureCallback mediaCaptureCallback = this.mMediaCaptureCallback;
            if (mediaCaptureCallback != null) {
                mediaCaptureCallback.onVideoCaptured(z, this.mOutputVideoFilePath);
            }
        }
    }

    @Override // android.media.MediaRecorder.OnInfoListener
    public void onInfo(MediaRecorder mediaRecorder, int i, int i2) {
        synchronized (this.mCameraLock) {
            if (i == 800) {
                if (this.mMediaCaptureCallback != null) {
                    stopVideo();
                    this.mMediaCaptureCallback.onVideoTimeoutExceeded();
                }
            }
        }
    }

    public void release() {
        synchronized (this.mCameraLock) {
            stop();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean setAutoFocusMoveCallback(AutoFocusMoveCallback autoFocusMoveCallback) {
        synchronized (this.mCameraLock) {
            if (getCamera() != null) {
                CameraAutoFocusMoveCallback cameraAutoFocusMoveCallback = null;
                Object[] objArr = 0;
                if (autoFocusMoveCallback != null) {
                    CameraAutoFocusMoveCallback cameraAutoFocusMoveCallback2 = new CameraAutoFocusMoveCallback();
                    cameraAutoFocusMoveCallback2.mDelegate = autoFocusMoveCallback;
                    cameraAutoFocusMoveCallback = cameraAutoFocusMoveCallback2;
                }
                getCamera().setAutoFocusMoveCallback(cameraAutoFocusMoveCallback);
            }
        }
        return true;
    }

    void setFrameCallback(FrameCallback frameCallback) {
        this.mFrameCallback = frameCallback;
    }

    public CameraSource start() {
        synchronized (this.mCameraLock) {
            if (getCamera() != null) {
                startPreview();
                return this;
            }
            this.mCamera = createCamera();
            this.mVideoDimension = getVideoDimension();
            this.mDummySurfaceTexture = new SurfaceTexture(100);
            getCamera().setPreviewTexture(this.mDummySurfaceTexture);
            startPreview();
            return this;
        }
    }

    public void startVideo(MediaCaptureCallback mediaCaptureCallback, String str, int i, int i2, int i3, int i4, boolean z) throws TakePictureException {
        this.videoQuality = i;
        this.videoRecordingBitRate = i2;
        this.mRequestedFps = i4;
        this.videoMaxDurationMs = i3;
        this.hasVideo = true;
        this.hasAudio = z;
        try {
            startVideo(mediaCaptureCallback, str, false);
        } catch (Exception unused) {
            this.mMediaRecorder = null;
            getCamera().lock();
            startVideo(mediaCaptureCallback, str, true);
        }
    }

    public void stop() {
        synchronized (this.mCameraLock) {
            Disposable disposable = this.compressingDisposable;
            if (disposable != null && !disposable.isDisposed()) {
                this.compressingDisposable.dispose();
            }
            if (getCamera() != null) {
                stopPreview();
                getCamera().setPreviewCallbackWithBuffer(null);
                try {
                    getCamera().setPreviewTexture(null);
                } catch (Exception e) {
                    Timber.e(e, "Failed to clear camera preview", new Object[0]);
                }
                getCamera().release();
                this.mCamera = null;
            }
        }
    }

    public void stopVideo() {
        synchronized (this.mCameraLock) {
            try {
                this.mMediaRecorder.stop();
                this.mMediaRecorder.reset();
                this.mMediaRecorder.release();
            } catch (RuntimeException unused) {
            } catch (Throwable th) {
                this.mIsRecording = false;
                throw th;
            }
            this.mIsRecording = false;
        }
    }

    public void takePicture(ShutterCallback shutterCallback, PictureCallback pictureCallback) throws TakePictureException {
        takePicture(shutterCallback, pictureCallback, true, true);
    }

    public void takePictureWithAutoFocus(final ShutterCallback shutterCallback, final PictureCallback pictureCallback, List<Camera.Area> list, final boolean z, final boolean z2) {
        autoFocusOnce(list, new AutoFocusCallback() { // from class: com.onfido.android.sdk.capture.ui.camera.view.CameraSource$$ExternalSyntheticLambda3
            @Override // com.onfido.android.sdk.capture.ui.camera.view.CameraSource.AutoFocusCallback
            public final void onAutoFocus(boolean z3) {
                this.f$0.m5708x39c23b17(shutterCallback, pictureCallback, z, z2, z3);
            }
        }, false);
    }

    public void tryTakePictureWithAutoFocus(final ShutterCallback shutterCallback, final PictureCallback pictureCallback, List<Camera.Area> list, final boolean z, final boolean z2) {
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        final Disposable disposableSubscribe = Observable.timer(5L, TimeUnit.SECONDS).subscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.ui.camera.view.CameraSource$$ExternalSyntheticLambda1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) throws CameraSource.TakePictureException {
                this.f$0.m5709x8a9921fa(atomicBoolean, shutterCallback, pictureCallback, z, z2, (Long) obj);
            }
        });
        autoFocusOnce(list, new AutoFocusCallback() { // from class: com.onfido.android.sdk.capture.ui.camera.view.CameraSource$$ExternalSyntheticLambda2
            @Override // com.onfido.android.sdk.capture.ui.camera.view.CameraSource.AutoFocusCallback
            public final void onAutoFocus(boolean z3) {
                this.f$0.m5711x96a0b8b8(atomicBoolean, disposableSubscribe, shutterCallback, pictureCallback, z, z2, z3);
            }
        }, false);
    }

    private void autoFocusOnce(final List<Camera.Area> list, final AutoFocusCallback autoFocusCallback, final boolean z) {
        synchronized (this.mCameraLock) {
            if (getCamera() != null) {
                if (!setFocusMode("auto")) {
                    if (autoFocusCallback != null) {
                        autoFocusCallback.onAutoFocus(false);
                    }
                } else {
                    setParameters(new SetParametersCallback() { // from class: com.onfido.android.sdk.capture.ui.camera.view.CameraSource$$ExternalSyntheticLambda4
                        @Override // com.onfido.android.sdk.capture.ui.camera.view.CameraSource.SetParametersCallback
                        public final Camera.Parameters call(Camera.Parameters parameters, Camera camera) {
                            return CameraSource.lambda$autoFocusOnce$7(list, parameters, camera);
                        }
                    });
                    autoFocus(new AutoFocusCallback() { // from class: com.onfido.android.sdk.capture.ui.camera.view.CameraSource$$ExternalSyntheticLambda5
                        @Override // com.onfido.android.sdk.capture.ui.camera.view.CameraSource.AutoFocusCallback
                        public final void onAutoFocus(boolean z2) {
                            this.f$0.m5704xea735e90(autoFocusCallback, z, z2);
                        }
                    });
                }
            }
        }
    }

    private static Dimension selectBestSize(Camera.Parameters parameters, int i, int i2) {
        Camera.Size sizeSelectBestSize = selectBestSize(parameters.getSupportedPictureSizes(), i, i2);
        if (sizeSelectBestSize == null) {
            return null;
        }
        return toSize(sizeSelectBestSize);
    }

    private static SizePair selectSizePair(Camera.Parameters parameters, int i, int i2, List<Camera.Size> list) {
        return selectSizePair(parameters, i, i2, -1, -1, list);
    }

    public boolean setFlashMode(String str) {
        synchronized (this.mCameraLock) {
            if (getCamera() != null && str != null) {
                Camera.Parameters parameters = getCamera().getParameters();
                if (setFlashMode(parameters, str)) {
                    getCamera().setParameters(parameters);
                    this.mFlashMode = str;
                    return true;
                }
            }
            return false;
        }
    }

    public boolean setFocusMode(String str) {
        synchronized (this.mCameraLock) {
            if (getCamera() != null && str != null) {
                try {
                    Camera.Parameters parameters = getCamera().getParameters();
                    Timber.d("setFocusMode:" + this.mFocusMode + " mPreviousFocusMode:" + this.mPreviousFocusMode, new Object[0]);
                    if (setFocusMode(parameters, str)) {
                        setParameters(parameters);
                        Timber.d("after setting setFocusMode:" + this.mFocusMode + " mPreviousFocusMode:" + this.mPreviousFocusMode, new Object[0]);
                        return true;
                    }
                } catch (RuntimeException unused) {
                    return false;
                }
            }
            return false;
        }
    }

    public boolean setParameters(SetParametersCallback setParametersCallback) {
        synchronized (this.mCameraLock) {
            if (getCamera() != null) {
                try {
                    Camera.Parameters parametersCall = setParametersCallback.call(getCamera().getParameters(), this.mCamera);
                    if (parametersCall != null) {
                        setParameters(parametersCall);
                        return true;
                    }
                } catch (Exception e) {
                    Timber.e(e, "setParameters Error: ", new Object[0]);
                }
            }
            return false;
        }
    }

    public CameraSource start(SurfaceTexture surfaceTexture) {
        synchronized (this.mCameraLock) {
            if (getCamera() != null) {
                getCamera().setPreviewTexture(surfaceTexture);
                startPreview();
                return this;
            }
            this.mCamera = createCamera();
            this.mVideoDimension = getVideoDimension();
            getCamera().setPreviewTexture(surfaceTexture);
            startPreview();
            return this;
        }
    }

    public void startVideo(MediaCaptureCallback mediaCaptureCallback, String str, boolean z) throws TakePictureException {
        try {
            synchronized (this.mCameraLock) {
                if (getCamera() != null) {
                    this.mOutputVideoFilePath = str;
                    getCamera().unlock();
                    initMediaRecorder(mediaCaptureCallback, str, z);
                    this.mIsRecording = true;
                }
            }
        } catch (Exception unused) {
            throw new TakePictureException();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void takePicture(final ShutterCallback shutterCallback, PictureCallback pictureCallback, boolean z, boolean z2) throws TakePictureException {
        try {
            synchronized (this.mCameraLock) {
                if (getCamera() != null) {
                    this.mIsCameraPreviewStarted = false;
                    final int jpegQuality = getCamera().getParameters().getJpegQuality();
                    PictureStartCallback pictureStartCallback = new PictureStartCallback();
                    pictureStartCallback.mDelegate = new ShutterCallback() { // from class: com.onfido.android.sdk.capture.ui.camera.view.CameraSource$$ExternalSyntheticLambda6
                        @Override // com.onfido.android.sdk.capture.ui.camera.view.CameraSource.ShutterCallback
                        public final void onShutter() {
                            this.f$0.m5706xd143de0b(jpegQuality, shutterCallback);
                        }
                    };
                    PictureDoneCallback pictureDoneCallback = new PictureDoneCallback();
                    pictureDoneCallback.mDelegate = pictureCallback;
                    if (!z2) {
                        setJpegQuality(100);
                    }
                    getCamera().takePicture(pictureStartCallback, null, null, pictureDoneCallback);
                }
            }
        } catch (RuntimeException e) {
            throw new TakePictureException("Error when Camera.takePicture got called", e);
        }
    }

    private static <T> T selectBestSize(IteratorSizeList<T> iteratorSizeList, int i, int i2) {
        return (T) selectBestSize(iteratorSizeList, i, i2, false);
    }

    private static <T> T selectBestSize(IteratorSizeList<T> iteratorSizeList, int i, int i2, boolean z) {
        int i3 = Integer.MAX_VALUE;
        int i4 = -1;
        int i5 = -1;
        int i6 = Integer.MAX_VALUE;
        for (int i7 = 0; i7 < iteratorSizeList.getLength(); i7++) {
            Camera.Size size = iteratorSizeList.getSize(i7);
            int i8 = size.width - i;
            int i9 = size.height - i2;
            int iAbs = Math.abs(i8) + Math.abs(i9);
            if (iAbs < i3) {
                i5 = i7;
                i3 = iAbs;
            }
            int i10 = i8 + i9;
            if (z && i8 > 0 && i9 > 0 && i10 < i6) {
                i4 = i7;
                i6 = i10;
            }
        }
        if (i4 <= -1) {
            i4 = i5;
        }
        if (i4 > -1) {
            return iteratorSizeList.getObject(i4);
        }
        return null;
    }
}
