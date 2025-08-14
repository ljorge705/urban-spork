package com.onfido.android.sdk.capture.utils;

import com.onfido.dagger.internal.Factory;

/* loaded from: classes2.dex */
public final class ImageUtils_Factory implements Factory<ImageUtils> {

    private static final class InstanceHolder {
        private static final ImageUtils_Factory INSTANCE = new ImageUtils_Factory();

        private InstanceHolder() {
        }
    }

    public static ImageUtils_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static ImageUtils newInstance() {
        return new ImageUtils();
    }

    @Override // com.onfido.javax.inject.Provider
    public ImageUtils get() {
        return newInstance();
    }
}
