package com.onfido.android.sdk.capture.common.permissions;

import android.app.Activity;
import android.content.Context;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.common.preferences.SharedPreferencesDataSource;
import com.onfido.android.sdk.capture.ui.CaptureType;
import com.onfido.javax.inject.Inject;
import com.onfido.reactnative.sdk.ReactNativeBridgeUtiles;
import io.sentry.protocol.App;
import io.sentry.protocol.Request;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u0003\b\u0017\u0018\u00002\u00020\u0001:\u0001*B\u0019\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0017J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\nH\u0012J\u0016\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u00122\u0006\u0010\u000b\u001a\u00020\fH\u0017J#\u0010\u0013\u001a\u00020\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00100\u00162\u0006\u0010\t\u001a\u00020\nH\u0017¢\u0006\u0002\u0010\u0017J\u0010\u0010\u0018\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u0010H\u0017J\u0010\u0010\u0019\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\fH\u0017J\u0018\u0010\u001a\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\nH\u0012J\u0018\u0010\u001b\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\nH\u0012J-\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\t\u001a\u00020\n2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00100\u00162\u0006\u0010\u001e\u001a\u00020\u001fH\u0010¢\u0006\u0004\b \u0010!J+\u0010\"\u001a\u00020\u001d2\u0006\u0010#\u001a\u00020$2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00100\u00122\u0006\u0010\u001e\u001a\u00020\u001fH\u0010¢\u0006\u0002\b%J\u0015\u0010&\u001a\u00020\b2\u0006\u0010'\u001a\u00020(H\u0010¢\u0006\u0002\b)R\u000e\u0010\u0002\u001a\u00020\u0003X\u0092\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0092\u0004¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Lcom/onfido/android/sdk/capture/common/permissions/RuntimePermissionsManager;", "", "context", "Landroid/content/Context;", "sharedPreferencesDataSource", "Lcom/onfido/android/sdk/capture/common/preferences/SharedPreferencesDataSource;", "(Landroid/content/Context;Lcom/onfido/android/sdk/capture/common/preferences/SharedPreferencesDataSource;)V", "arePermissionsRevoked", "", "activity", "Landroid/app/Activity;", ReactNativeBridgeUtiles.KEY_CAPTURE_TYPE, "Lcom/onfido/android/sdk/capture/ui/CaptureType;", "getPermissionStatus", "Lcom/onfido/android/sdk/capture/common/permissions/RuntimePermissionsManager$PermissionStatus;", "permission", "", "getPermissionsForCaptureType", "", "getPermissionsStatus", "Lcom/onfido/android/sdk/capture/common/permissions/PermissionRequestStatus;", App.JsonKeys.APP_PERMISSIONS, "", "([Ljava/lang/String;Landroid/app/Activity;)Lcom/onfido/android/sdk/capture/common/permissions/PermissionRequestStatus;", "hasPermission", "hasPermissionsForCaptureType", "isPermanentlyDenied", "isTemporarilyDenied", "requestPermissions", "", "requestCode", "", "requestPermissions$onfido_capture_sdk_core_release", "(Landroid/app/Activity;[Ljava/lang/String;I)V", "requestPermissionsFromFragment", Request.JsonKeys.FRAGMENT, "Landroidx/fragment/app/Fragment;", "requestPermissionsFromFragment$onfido_capture_sdk_core_release", "werePermissionsGranted", "grantResults", "", "werePermissionsGranted$onfido_capture_sdk_core_release", "PermissionStatus", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public class RuntimePermissionsManager {
    private final Context context;
    private final SharedPreferencesDataSource sharedPreferencesDataSource;

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\u00032\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/onfido/android/sdk/capture/common/permissions/RuntimePermissionsManager$PermissionStatus;", "", "alreadyRequested", "", "shouldAsk", "(ZZ)V", "getAlreadyRequested", "()Z", "getShouldAsk", "component1", "component2", Constants.COPY_TYPE, "equals", "other", "hashCode", "", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final /* data */ class PermissionStatus {
        private final boolean alreadyRequested;
        private final boolean shouldAsk;

        public PermissionStatus(boolean z, boolean z2) {
            this.alreadyRequested = z;
            this.shouldAsk = z2;
        }

        public static /* synthetic */ PermissionStatus copy$default(PermissionStatus permissionStatus, boolean z, boolean z2, int i, Object obj) {
            if ((i & 1) != 0) {
                z = permissionStatus.alreadyRequested;
            }
            if ((i & 2) != 0) {
                z2 = permissionStatus.shouldAsk;
            }
            return permissionStatus.copy(z, z2);
        }

        /* renamed from: component1, reason: from getter */
        public final boolean getAlreadyRequested() {
            return this.alreadyRequested;
        }

        /* renamed from: component2, reason: from getter */
        public final boolean getShouldAsk() {
            return this.shouldAsk;
        }

        public final PermissionStatus copy(boolean alreadyRequested, boolean shouldAsk) {
            return new PermissionStatus(alreadyRequested, shouldAsk);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof PermissionStatus)) {
                return false;
            }
            PermissionStatus permissionStatus = (PermissionStatus) other;
            return this.alreadyRequested == permissionStatus.alreadyRequested && this.shouldAsk == permissionStatus.shouldAsk;
        }

        public final boolean getAlreadyRequested() {
            return this.alreadyRequested;
        }

        public final boolean getShouldAsk() {
            return this.shouldAsk;
        }

        public int hashCode() {
            return (Boolean.hashCode(this.alreadyRequested) * 31) + Boolean.hashCode(this.shouldAsk);
        }

        public String toString() {
            return "PermissionStatus(alreadyRequested=" + this.alreadyRequested + ", shouldAsk=" + this.shouldAsk + ')';
        }
    }

    @Inject
    public RuntimePermissionsManager(Context context, SharedPreferencesDataSource sharedPreferencesDataSource) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(sharedPreferencesDataSource, "sharedPreferencesDataSource");
        this.context = context;
        this.sharedPreferencesDataSource = sharedPreferencesDataSource;
    }

    private PermissionStatus getPermissionStatus(String permission, Activity activity) {
        return new PermissionStatus(this.sharedPreferencesDataSource.hasRequestedPermission$onfido_capture_sdk_core_release(permission), ActivityCompat.shouldShowRequestPermissionRationale(activity, permission));
    }

    private boolean isPermanentlyDenied(String permission, Activity activity) {
        PermissionStatus permissionStatus = getPermissionStatus(permission, activity);
        return permissionStatus.getAlreadyRequested() && permissionStatus.getShouldAsk();
    }

    private boolean isTemporarilyDenied(String permission, Activity activity) {
        PermissionStatus permissionStatus = getPermissionStatus(permission, activity);
        return permissionStatus.getAlreadyRequested() && !permissionStatus.getShouldAsk();
    }

    public boolean arePermissionsRevoked(Activity activity, CaptureType captureType) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(captureType, "captureType");
        List<String> permissionsForCaptureType = getPermissionsForCaptureType(captureType);
        if (!(permissionsForCaptureType instanceof Collection) || !permissionsForCaptureType.isEmpty()) {
            for (String str : permissionsForCaptureType) {
                if (!hasPermission(str) && ActivityCompat.shouldShowRequestPermissionRationale(activity, str)) {
                    return true;
                }
            }
        }
        return false;
    }

    public List<String> getPermissionsForCaptureType(CaptureType captureType) {
        Intrinsics.checkNotNullParameter(captureType, "captureType");
        return captureType == CaptureType.VIDEO ? CollectionsKt.listOf((Object[]) new String[]{"android.permission.CAMERA", "android.permission.RECORD_AUDIO"}) : CollectionsKt.listOf("android.permission.CAMERA");
    }

    public PermissionRequestStatus getPermissionsStatus(String[] permissions, Activity activity) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(activity, "activity");
        for (String str : permissions) {
            if (isPermanentlyDenied(str, activity)) {
                return PermissionRequestStatus.PERMANENTLY_DENIED;
            }
        }
        for (String str2 : permissions) {
            if (isTemporarilyDenied(str2, activity)) {
                return PermissionRequestStatus.TEMPORARILY_DENIED;
            }
        }
        return PermissionRequestStatus.FIRST_REQUEST;
    }

    public boolean hasPermission(String permission) {
        Intrinsics.checkNotNullParameter(permission, "permission");
        return ActivityCompat.checkSelfPermission(this.context, permission) == 0;
    }

    public boolean hasPermissionsForCaptureType(CaptureType captureType) {
        Intrinsics.checkNotNullParameter(captureType, "captureType");
        List<String> permissionsForCaptureType = getPermissionsForCaptureType(captureType);
        if (!(permissionsForCaptureType instanceof Collection) || !permissionsForCaptureType.isEmpty()) {
            Iterator<T> it = permissionsForCaptureType.iterator();
            while (it.hasNext()) {
                if (!hasPermission((String) it.next())) {
                    return false;
                }
            }
        }
        return true;
    }

    public void requestPermissions$onfido_capture_sdk_core_release(Activity activity, String[] permissions, int requestCode) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        for (String str : permissions) {
            this.sharedPreferencesDataSource.setHasRequestedPermission$onfido_capture_sdk_core_release(str);
        }
        ActivityCompat.requestPermissions(activity, permissions, requestCode);
    }

    public void requestPermissionsFromFragment$onfido_capture_sdk_core_release(Fragment fragment, List<String> permissions, int requestCode) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Iterator<T> it = permissions.iterator();
        while (it.hasNext()) {
            this.sharedPreferencesDataSource.setHasRequestedPermission$onfido_capture_sdk_core_release((String) it.next());
        }
        fragment.requestPermissions((String[]) permissions.toArray(new String[0]), requestCode);
    }

    public boolean werePermissionsGranted$onfido_capture_sdk_core_release(int[] grantResults) {
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        for (int i : grantResults) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }
}
