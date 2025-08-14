package com.onfido.android.sdk.capture.common.permissions;

import android.app.Activity;
import androidx.activity.result.ActivityResultLauncher;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.PermissionsTracker;
import com.onfido.android.sdk.capture.ui.CaptureType;
import com.onfido.javax.inject.Inject;
import com.onfido.reactnative.sdk.ReactNativeBridgeUtiles;
import io.sentry.protocol.App;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001:\u0002+,B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0015\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\fH\u0000¢\u0006\u0002\b\u000fJ*\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00170\u0016J\u0010\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u0017H\u0002J\u0010\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u0017H\u0002J\u001a\u0010\u001b\u001a\u00020\u000e2\u0012\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u001e0\u001dJ\u000e\u0010\u001f\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u0014J\u0010\u0010 \u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u0017H\u0002J*\u0010!\u001a\u00020\u000e2\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00170\u00162\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014J\u0010\u0010\"\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u0017H\u0002J\u0010\u0010#\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0018\u0010$\u001a\u00020\u000e2\u0006\u0010%\u001a\u00020&2\u0006\u0010\u0019\u001a\u00020\u0017H\u0002J$\u0010'\u001a\u00020\u000e2\u0012\u0010(\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00170\u00162\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J&\u0010)\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u00142\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\t0*2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000¨\u0006-"}, d2 = {"Lcom/onfido/android/sdk/capture/common/permissions/PermissionsManagementPresenter;", "", "runtimePermissionsManager", "Lcom/onfido/android/sdk/capture/common/permissions/RuntimePermissionsManager;", "permissionsTracker", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/PermissionsTracker;", "(Lcom/onfido/android/sdk/capture/common/permissions/RuntimePermissionsManager;Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/PermissionsTracker;)V", "missingPermissions", "", "", "requestedPermissions", "view", "Lcom/onfido/android/sdk/capture/common/permissions/PermissionsManagementPresenter$View;", "attachView", "", "attachView$onfido_capture_sdk_core_release", "checkPermissions", "activity", "Landroid/app/Activity;", ReactNativeBridgeUtiles.KEY_CAPTURE_TYPE, "Lcom/onfido/android/sdk/capture/ui/CaptureType;", "permissionsResult", "", "", "onBothPermissionsNeeded", "isRecovery", "onCameraPermissionNeeded", "onEnableButtonClicked", "requestPermissionLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "", "onGoToSettingsButtonClicked", "onMicrophonePermissionNeeded", "onPermissionResults", "onPermissionsNeeded", "setMissingPermissions", "setScreenTitle", "permissionType", "Lcom/onfido/android/sdk/capture/common/permissions/PermissionsManagementPresenter$PermissionType;", "trackPermissions", App.JsonKeys.APP_PERMISSIONS, "trackScreen", "", "PermissionType", "View", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class PermissionsManagementPresenter {
    private List<String> missingPermissions;
    private final PermissionsTracker permissionsTracker;
    private List<String> requestedPermissions;
    private final RuntimePermissionsManager runtimePermissionsManager;
    private View view;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/onfido/android/sdk/capture/common/permissions/PermissionsManagementPresenter$PermissionType;", "", "(Ljava/lang/String;I)V", "CAMERA", "MIC", "CAMERA_AND_MIC", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class PermissionType {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ PermissionType[] $VALUES;
        public static final PermissionType CAMERA = new PermissionType("CAMERA", 0);
        public static final PermissionType MIC = new PermissionType("MIC", 1);
        public static final PermissionType CAMERA_AND_MIC = new PermissionType("CAMERA_AND_MIC", 2);

        private static final /* synthetic */ PermissionType[] $values() {
            return new PermissionType[]{CAMERA, MIC, CAMERA_AND_MIC};
        }

        static {
            PermissionType[] permissionTypeArr$values = $values();
            $VALUES = permissionTypeArr$values;
            $ENTRIES = EnumEntriesKt.enumEntries(permissionTypeArr$values);
        }

        private PermissionType(String str, int i) {
        }

        public static EnumEntries<PermissionType> getEntries() {
            return $ENTRIES;
        }

        public static PermissionType valueOf(String str) {
            return (PermissionType) Enum.valueOf(PermissionType.class, str);
        }

        public static PermissionType[] values() {
            return (PermissionType[]) $VALUES.clone();
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\b`\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\b\u001a\u00020\u0003H&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0012\u0010\n\u001a\u00020\u00032\b\b\u0001\u0010\u000b\u001a\u00020\fH&¨\u0006\r"}, d2 = {"Lcom/onfido/android/sdk/capture/common/permissions/PermissionsManagementPresenter$View;", "", "onCameraAndMicrophonePermissionsNeeded", "", "isRecovery", "", "onCameraPermissionNeeded", "onMicrophonePermissionNeeded", "setGrantedResult", "setIsRecovery", "setScreenTitle", "titleResId", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface View {
        void onCameraAndMicrophonePermissionsNeeded(boolean isRecovery);

        void onCameraPermissionNeeded(boolean isRecovery);

        void onMicrophonePermissionNeeded(boolean isRecovery);

        void setGrantedResult();

        void setIsRecovery(boolean isRecovery);

        void setScreenTitle(int titleResId);
    }

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PermissionType.values().length];
            try {
                iArr[PermissionType.CAMERA.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[PermissionType.MIC.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[PermissionType.CAMERA_AND_MIC.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Inject
    public PermissionsManagementPresenter(RuntimePermissionsManager runtimePermissionsManager, PermissionsTracker permissionsTracker) {
        Intrinsics.checkNotNullParameter(runtimePermissionsManager, "runtimePermissionsManager");
        Intrinsics.checkNotNullParameter(permissionsTracker, "permissionsTracker");
        this.runtimePermissionsManager = runtimePermissionsManager;
        this.permissionsTracker = permissionsTracker;
        this.missingPermissions = new ArrayList();
        this.requestedPermissions = new ArrayList();
    }

    private final void onBothPermissionsNeeded(boolean isRecovery) {
        View view = this.view;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            view = null;
        }
        view.onCameraAndMicrophonePermissionsNeeded(isRecovery);
        setScreenTitle(PermissionType.CAMERA_AND_MIC, isRecovery);
    }

    private final void onCameraPermissionNeeded(boolean isRecovery) {
        View view = this.view;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            view = null;
        }
        view.onCameraPermissionNeeded(isRecovery);
        setScreenTitle(PermissionType.CAMERA, isRecovery);
    }

    private final void onMicrophonePermissionNeeded(boolean isRecovery) {
        View view = this.view;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            view = null;
        }
        view.onMicrophonePermissionNeeded(isRecovery);
        setScreenTitle(PermissionType.MIC, isRecovery);
    }

    private final void onPermissionsNeeded(boolean isRecovery) {
        List<String> list = this.missingPermissions;
        if (list.equals(CollectionsKt.listOf((Object[]) new String[]{"android.permission.CAMERA", "android.permission.RECORD_AUDIO"}))) {
            onBothPermissionsNeeded(isRecovery);
        } else if (list.equals(CollectionsKt.listOf("android.permission.CAMERA"))) {
            onCameraPermissionNeeded(isRecovery);
        } else if (list.equals(CollectionsKt.listOf("android.permission.RECORD_AUDIO"))) {
            onMicrophonePermissionNeeded(isRecovery);
        }
    }

    private final void setMissingPermissions(CaptureType captureType) {
        boolean zHasPermission = this.runtimePermissionsManager.hasPermission("android.permission.CAMERA");
        boolean zHasPermission2 = this.runtimePermissionsManager.hasPermission("android.permission.RECORD_AUDIO");
        if (!zHasPermission && !this.missingPermissions.contains("android.permission.CAMERA")) {
            this.missingPermissions.add("android.permission.CAMERA");
        }
        if (captureType != CaptureType.VIDEO || zHasPermission2 || this.missingPermissions.contains("android.permission.RECORD_AUDIO")) {
            return;
        }
        this.missingPermissions.add("android.permission.RECORD_AUDIO");
    }

    private final void setScreenTitle(PermissionType permissionType, boolean isRecovery) {
        int i;
        int i2 = WhenMappings.$EnumSwitchMapping$0[permissionType.ordinal()];
        if (i2 == 1) {
            i = isRecovery ? R.string.onfido_permission_recovery_title_cam : R.string.onfido_permission_title_cam;
        } else if (i2 == 2) {
            i = isRecovery ? R.string.onfido_permission_recovery_title_mic : R.string.onfido_permission_title_mic;
        } else {
            if (i2 != 3) {
                throw new NoWhenBranchMatchedException();
            }
            i = isRecovery ? R.string.onfido_permission_recovery_title_both : R.string.onfido_permission_title_both;
        }
        View view = this.view;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            view = null;
        }
        view.setScreenTitle(i);
    }

    private final void trackPermissions(Map<String, Boolean> permissions, CaptureType captureType) {
        if (permissions.values().contains(Boolean.FALSE)) {
            this.permissionsTracker.trackPermissionsDenied$onfido_capture_sdk_core_release(captureType);
        } else {
            this.permissionsTracker.trackPermissionsGranted$onfido_capture_sdk_core_release(captureType, (String[]) permissions.keySet().toArray(new String[0]));
        }
    }

    private final void trackScreen(CaptureType captureType, List<String> permissions, Activity activity) {
        String[] strArr = (String[]) permissions.toArray(new String[0]);
        if (!(strArr.length == 0)) {
            this.permissionsTracker.trackPermissionsManagementScreen$onfido_capture_sdk_core_release(captureType, strArr, this.runtimePermissionsManager.getPermissionsStatus(strArr, activity));
        }
    }

    public final void attachView$onfido_capture_sdk_core_release(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        this.view = view;
    }

    public final void checkPermissions(Activity activity, CaptureType captureType, Map<String, Boolean> permissionsResult) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(captureType, "captureType");
        Intrinsics.checkNotNullParameter(permissionsResult, "permissionsResult");
        setMissingPermissions(captureType);
        trackScreen(captureType, this.missingPermissions, activity);
        boolean z = this.runtimePermissionsManager.arePermissionsRevoked(activity, captureType) || permissionsResult.values().contains(Boolean.FALSE);
        View view = this.view;
        View view2 = null;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            view = null;
        }
        view.setIsRecovery(z);
        if (true ^ this.missingPermissions.isEmpty()) {
            onPermissionsNeeded(z);
            return;
        }
        View view3 = this.view;
        if (view3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        } else {
            view2 = view3;
        }
        view2.setGrantedResult();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onEnableButtonClicked(ActivityResultLauncher<String[]> requestPermissionLauncher) {
        Intrinsics.checkNotNullParameter(requestPermissionLauncher, "requestPermissionLauncher");
        List<String> list = this.missingPermissions;
        this.requestedPermissions = list;
        requestPermissionLauncher.launch(list.toArray(new String[0]));
    }

    public final void onGoToSettingsButtonClicked(CaptureType captureType) {
        Intrinsics.checkNotNullParameter(captureType, "captureType");
        this.permissionsTracker.trackPermissionsSettingsButtonClick$onfido_capture_sdk_core_release(captureType);
    }

    public final void onPermissionResults(Map<String, Boolean> permissionsResult, Activity activity, CaptureType captureType) {
        Intrinsics.checkNotNullParameter(permissionsResult, "permissionsResult");
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(captureType, "captureType");
        this.missingPermissions.clear();
        trackPermissions(permissionsResult, captureType);
        checkPermissions(activity, captureType, permissionsResult);
    }
}
