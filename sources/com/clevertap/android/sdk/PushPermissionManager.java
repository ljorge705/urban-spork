package com.clevertap.android.sdk;

import android.app.Activity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.clevertap.android.sdk.InAppNotificationActivity;
import com.clevertap.android.sdk.inapp.AlertDialogPromptForSettings;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* loaded from: classes5.dex */
public class PushPermissionManager {
    public static final String ANDROID_PERMISSION_STRING = "android.permission.POST_NOTIFICATIONS";
    private final Activity activity;
    private final CleverTapInstanceConfig config;
    private boolean isFallbackSettingsEnabled;
    private boolean isFromNotificationSettingsActivity = false;

    private boolean shouldShowFallbackAlertDialog() {
        return this.isFallbackSettingsEnabled;
    }

    public boolean isFromNotificationSettingsActivity() {
        return this.isFromNotificationSettingsActivity;
    }

    public PushPermissionManager(Activity activity, CleverTapInstanceConfig cleverTapInstanceConfig) {
        this.activity = activity;
        this.config = cleverTapInstanceConfig;
    }

    public void showHardPermissionPrompt(boolean z, InAppNotificationActivity.PushPermissionResultCallback pushPermissionResultCallback) {
        if (CTXtensions.isPackageAndOsTargetsAbove(this.activity, 32)) {
            this.isFallbackSettingsEnabled = z;
            requestPermission(pushPermissionResultCallback);
        }
    }

    public void requestPermission(InAppNotificationActivity.PushPermissionResultCallback pushPermissionResultCallback) {
        if (ContextCompat.checkSelfPermission(this.activity, ANDROID_PERMISSION_STRING) == -1) {
            boolean zIsFirstTimeRequest = CTPreferenceCache.getInstance(this.activity, this.config).isFirstTimeRequest();
            Activity currentActivity = CoreMetaData.getCurrentActivity();
            if (currentActivity == null) {
                Logger.d("CurrentActivity reference is null. SDK can't prompt the user with Notification Permission! Ensure the following things:\n1. Calling ActivityLifecycleCallback.register(this) in your custom application class before super.onCreate().\n   Alternatively, register CleverTap SDK's Application class in the manifest using com.clevertap.android.sdk.Application.\n2. Ensure that the promptPushPrimer() API is called from the onResume() lifecycle method, not onCreate().");
                return;
            }
            boolean zShouldShowRequestPermissionRationale = ActivityCompat.shouldShowRequestPermissionRationale(currentActivity, ANDROID_PERMISSION_STRING);
            if (!zIsFirstTimeRequest && zShouldShowRequestPermissionRationale && shouldShowFallbackAlertDialog()) {
                showFallbackAlertDialog();
                return;
            } else {
                ActivityCompat.requestPermissions(this.activity, new String[]{ANDROID_PERMISSION_STRING}, 102);
                return;
            }
        }
        pushPermissionResultCallback.onPushPermissionAccept();
        Activity activity = this.activity;
        if (activity instanceof InAppNotificationActivity) {
            ((InAppNotificationActivity) activity).didDismiss(null);
        }
    }

    public void showFallbackAlertDialog() {
        AlertDialogPromptForSettings.show(this.activity, new Function0() { // from class: com.clevertap.android.sdk.PushPermissionManager$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return this.f$0.m4785xe5faa046();
            }
        }, new Function0() { // from class: com.clevertap.android.sdk.PushPermissionManager$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return this.f$0.m4786xe5843a47();
            }
        });
    }

    /* renamed from: lambda$showFallbackAlertDialog$0$com-clevertap-android-sdk-PushPermissionManager, reason: not valid java name */
    /* synthetic */ Unit m4785xe5faa046() {
        Utils.navigateToAndroidSettingsForNotifications(this.activity);
        this.isFromNotificationSettingsActivity = true;
        return Unit.INSTANCE;
    }

    /* renamed from: lambda$showFallbackAlertDialog$1$com-clevertap-android-sdk-PushPermissionManager, reason: not valid java name */
    /* synthetic */ Unit m4786xe5843a47() {
        Activity activity = this.activity;
        if (activity instanceof InAppNotificationActivity) {
            ((InAppNotificationActivity) activity).notifyPermissionDenied();
            ((InAppNotificationActivity) this.activity).didDismiss(null);
        }
        return Unit.INSTANCE;
    }
}
