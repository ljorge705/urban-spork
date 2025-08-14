package com.onfido.android.sdk.capture.detector.face;

import com.onfido.dagger.internal.Factory;

/* loaded from: classes2.dex */
public final class FaceOnDocumentDetectorEmpty_Factory implements Factory<FaceOnDocumentDetectorEmpty> {

    private static final class InstanceHolder {
        private static final FaceOnDocumentDetectorEmpty_Factory INSTANCE = new FaceOnDocumentDetectorEmpty_Factory();

        private InstanceHolder() {
        }
    }

    public static FaceOnDocumentDetectorEmpty_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static FaceOnDocumentDetectorEmpty newInstance() {
        return new FaceOnDocumentDetectorEmpty();
    }

    @Override // com.onfido.javax.inject.Provider
    public FaceOnDocumentDetectorEmpty get() {
        return newInstance();
    }
}
