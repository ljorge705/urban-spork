package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.util;

import android.os.Build;
import com.onfido.javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0007\b\u0007¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/camera2/util/ApiLevelUtil;", "", "()V", "isApiAtLeast", "", "level", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/camera2/util/ApiLevel;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ApiLevelUtil {
    @Inject
    public ApiLevelUtil() {
    }

    public final boolean isApiAtLeast(ApiLevel level) {
        Intrinsics.checkNotNullParameter(level, "level");
        return Build.VERSION.SDK_INT >= level.getVersionCode();
    }
}
