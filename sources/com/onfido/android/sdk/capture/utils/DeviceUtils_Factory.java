package com.onfido.android.sdk.capture.utils;

import com.onfido.dagger.internal.Factory;

/* loaded from: classes2.dex */
public final class DeviceUtils_Factory implements Factory<DeviceUtils> {

    private static final class InstanceHolder {
        private static final DeviceUtils_Factory INSTANCE = new DeviceUtils_Factory();

        private InstanceHolder() {
        }
    }

    public static DeviceUtils_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static DeviceUtils newInstance() {
        return new DeviceUtils();
    }

    @Override // com.onfido.javax.inject.Provider
    public DeviceUtils get() {
        return newInstance();
    }
}
