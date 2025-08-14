package androidx.camera.core.impl;

/* loaded from: classes.dex */
public class CameraCaptureFailure {
    private final Reason mReason;

    public enum Reason {
        ERROR
    }

    public Reason getReason() {
        return this.mReason;
    }

    public CameraCaptureFailure(Reason reason) {
        this.mReason = reason;
    }
}
