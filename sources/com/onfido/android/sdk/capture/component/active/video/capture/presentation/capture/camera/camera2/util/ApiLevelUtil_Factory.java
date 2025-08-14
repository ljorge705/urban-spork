package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.util;

import com.onfido.dagger.internal.Factory;

/* loaded from: classes2.dex */
public final class ApiLevelUtil_Factory implements Factory<ApiLevelUtil> {

    private static final class InstanceHolder {
        private static final ApiLevelUtil_Factory INSTANCE = new ApiLevelUtil_Factory();

        private InstanceHolder() {
        }
    }

    public static ApiLevelUtil_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static ApiLevelUtil newInstance() {
        return new ApiLevelUtil();
    }

    @Override // com.onfido.javax.inject.Provider
    public ApiLevelUtil get() {
        return newInstance();
    }
}
