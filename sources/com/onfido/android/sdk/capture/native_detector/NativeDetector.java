package com.onfido.android.sdk.capture.native_detector;

import android.content.Context;
import com.onfido.android.sdk.capture.edge_detector.EdgeDetectionResults;
import com.onfido.android.sdk.capture.ui.camera.DocumentDetectionFrame;
import com.onfido.javax.inject.Inject;
import com.onfido.javax.inject.Singleton;
import io.reactivex.rxjava3.subjects.PublishSubject;

@Singleton
/* loaded from: classes2.dex */
public class NativeDetector {
    private static final int ROTATION_MULTIPLIER = 90;
    private static boolean hasNativeLibrary = true;
    private final PublishSubject<DocumentDetectionFrame> frameDataSubject = PublishSubject.create();

    static {
        try {
            System.loadLibrary("NativeBridge");
        } catch (UnsatisfiedLinkError unused) {
            hasNativeLibrary = false;
        }
    }

    @Inject
    public NativeDetector() {
    }

    private native void clearEdgesNative();

    private native boolean detectBlurCaptureNative(byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, int i7);

    private native boolean detectPathReallyExist(String str);

    public void clearEdges() {
        if (hasNativeLibrary()) {
            clearEdgesNative();
        }
    }

    public native boolean detectBlur(byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, int i7);

    public EdgeDetectionResults detectEdges(DocumentDetectionFrame documentDetectionFrame) {
        if (hasNativeLibrary()) {
            return detectEdges(documentDetectionFrame.getYuv(), documentDetectionFrame.getFrameWidth(), documentDetectionFrame.getFrameHeight(), documentDetectionFrame.getOuterFrame().getWidth(), documentDetectionFrame.getOuterFrame().getHeight(), documentDetectionFrame.getOuterFrame().getLeft(), documentDetectionFrame.getOuterFrame().getTop(), documentDetectionFrame.getRotation() * 90);
        }
        return null;
    }

    public native EdgeDetectionResults detectEdges(byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, int i7);

    public native boolean detectGlare(byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, int i7);

    public native String[] extractMRZ(byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, byte[] bArr2, int i7, int i8, int i9, int i10);

    public PublishSubject<DocumentDetectionFrame> getFrameData() {
        return this.frameDataSubject;
    }

    public boolean hasNativeLibrary() {
        return hasNativeLibrary;
    }

    public boolean isBlurDetected(DocumentDetectionFrame documentDetectionFrame) {
        return hasNativeLibrary() && detectBlur(documentDetectionFrame.getYuv(), documentDetectionFrame.getFrameWidth(), documentDetectionFrame.getFrameHeight(), documentDetectionFrame.getOuterFrame().getWidth(), documentDetectionFrame.getOuterFrame().getHeight(), documentDetectionFrame.getOuterFrame().getLeft(), documentDetectionFrame.getOuterFrame().getTop(), documentDetectionFrame.getRotation() * 90);
    }

    public boolean isBlurDetectedWithRotation(DocumentDetectionFrame documentDetectionFrame) {
        return hasNativeLibrary() && detectBlurCaptureNative(documentDetectionFrame.getYuv(), documentDetectionFrame.getFrameWidth(), documentDetectionFrame.getFrameHeight(), documentDetectionFrame.getOuterFrame().getWidth(), documentDetectionFrame.getOuterFrame().getHeight(), documentDetectionFrame.getOuterFrame().getLeft(), documentDetectionFrame.getOuterFrame().getTop(), documentDetectionFrame.getRotation());
    }

    public boolean isGlareDetected(DocumentDetectionFrame documentDetectionFrame) {
        return hasNativeLibrary() && detectGlare(documentDetectionFrame.getYuv(), documentDetectionFrame.getFrameWidth(), documentDetectionFrame.getFrameHeight(), documentDetectionFrame.getOuterFrame().getWidth(), documentDetectionFrame.getOuterFrame().getHeight(), documentDetectionFrame.getOuterFrame().getLeft(), documentDetectionFrame.getOuterFrame().getTop(), documentDetectionFrame.getRotation() * 90);
    }

    public boolean isInVirtualEnvironment(Context context) {
        return hasNativeLibrary() && detectPathReallyExist(context.getFilesDir().getAbsolutePath());
    }
}
