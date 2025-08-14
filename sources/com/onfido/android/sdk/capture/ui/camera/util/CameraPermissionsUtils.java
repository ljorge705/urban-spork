package com.onfido.android.sdk.capture.ui.camera.util;

import android.content.Context;
import com.onfido.android.sdk.capture.common.SdkController;
import com.onfido.android.sdk.capture.common.permissions.RuntimePermissionsManager;
import com.onfido.android.sdk.capture.ui.CaptureType;
import com.onfido.javax.inject.Inject;
import com.onfido.reactnative.sdk.ReactNativeBridgeUtiles;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001d\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\u000fH\u0000¢\u0006\u0004\b\u0010\u0010\u0011R\u001e\u0010\u0005\u001a\u00020\u00068\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u0012"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/util/CameraPermissionsUtils;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "permissionsManager", "Lcom/onfido/android/sdk/capture/common/permissions/RuntimePermissionsManager;", "getPermissionsManager$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/common/permissions/RuntimePermissionsManager;", "setPermissionsManager$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/common/permissions/RuntimePermissionsManager;)V", "getMissingPermissions", "", "", ReactNativeBridgeUtiles.KEY_CAPTURE_TYPE, "Lcom/onfido/android/sdk/capture/ui/CaptureType;", "getMissingPermissions$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/ui/CaptureType;)[Ljava/lang/String;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class CameraPermissionsUtils {

    @Inject
    public RuntimePermissionsManager permissionsManager;

    public CameraPermissionsUtils(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        SdkController.getSdkComponent$default(SdkController.INSTANCE.getInstance(), context, null, 2, null).inject$onfido_capture_sdk_core_release(this);
    }

    public final String[] getMissingPermissions$onfido_capture_sdk_core_release(CaptureType captureType) {
        Intrinsics.checkNotNullParameter(captureType, "captureType");
        String[] strArr = captureType == CaptureType.VIDEO ? new String[]{"android.permission.CAMERA", "android.permission.RECORD_AUDIO"} : new String[]{"android.permission.CAMERA"};
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            if (!getPermissionsManager$onfido_capture_sdk_core_release().hasPermission(str)) {
                arrayList.add(str);
            }
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    public final RuntimePermissionsManager getPermissionsManager$onfido_capture_sdk_core_release() {
        RuntimePermissionsManager runtimePermissionsManager = this.permissionsManager;
        if (runtimePermissionsManager != null) {
            return runtimePermissionsManager;
        }
        Intrinsics.throwUninitializedPropertyAccessException("permissionsManager");
        return null;
    }

    public final void setPermissionsManager$onfido_capture_sdk_core_release(RuntimePermissionsManager runtimePermissionsManager) {
        Intrinsics.checkNotNullParameter(runtimePermissionsManager, "<set-?>");
        this.permissionsManager = runtimePermissionsManager;
    }
}
