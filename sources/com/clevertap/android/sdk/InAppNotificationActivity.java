package com.clevertap.android.sdk;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import androidx.activity.OnBackPressedCallback;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import com.clevertap.android.sdk.inapp.CTInAppAction;
import com.clevertap.android.sdk.inapp.CTInAppBaseFullFragment;
import com.clevertap.android.sdk.inapp.CTInAppHtmlCoverFragment;
import com.clevertap.android.sdk.inapp.CTInAppHtmlHalfInterstitialFragment;
import com.clevertap.android.sdk.inapp.CTInAppHtmlInterstitialFragment;
import com.clevertap.android.sdk.inapp.CTInAppNativeCoverFragment;
import com.clevertap.android.sdk.inapp.CTInAppNativeCoverImageFragment;
import com.clevertap.android.sdk.inapp.CTInAppNativeHalfInterstitialFragment;
import com.clevertap.android.sdk.inapp.CTInAppNativeHalfInterstitialImageFragment;
import com.clevertap.android.sdk.inapp.CTInAppNativeInterstitialFragment;
import com.clevertap.android.sdk.inapp.CTInAppNativeInterstitialImageFragment;
import com.clevertap.android.sdk.inapp.CTInAppNotification;
import com.clevertap.android.sdk.inapp.CTInAppNotificationButton;
import com.clevertap.android.sdk.inapp.CTInAppType;
import com.clevertap.android.sdk.inapp.InAppActionType;
import com.clevertap.android.sdk.inapp.InAppController;
import com.clevertap.android.sdk.inapp.InAppListener;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public final class InAppNotificationActivity extends FragmentActivity implements InAppListener, DidClickForHardPermissionListener {
    private static boolean isAlertVisible = false;
    private CleverTapInstanceConfig config;
    private CTInAppNotification inAppNotification;
    private boolean invokedCallbacks = false;
    private WeakReference<InAppListener> listenerWeakReference;
    private PushPermissionManager pushPermissionManager;
    private WeakReference<PushPermissionResultCallback> pushPermissionResultCallbackWeakReference;

    public interface PushPermissionResultCallback {
        void onPushPermissionAccept();

        void onPushPermissionDeny();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) { // from class: com.clevertap.android.sdk.InAppNotificationActivity.1
            @Override // androidx.activity.OnBackPressedCallback
            public void handleOnBackPressed() {
                InAppNotificationActivity.this.finish();
                InAppNotificationActivity.this.didDismiss(null);
            }
        });
        int i = getResources().getConfiguration().orientation;
        if (i == 2) {
            getWindow().addFlags(1024);
        }
        try {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                throw new IllegalArgumentException();
            }
            this.inAppNotification = (CTInAppNotification) extras.getParcelable(Constants.INAPP_KEY);
            boolean z = extras.getBoolean(InAppController.DISPLAY_HARD_PERMISSION_BUNDLE_KEY, false);
            Bundle bundle2 = extras.getBundle("configBundle");
            if (bundle2 != null) {
                this.config = (CleverTapInstanceConfig) bundle2.getParcelable("config");
            }
            setListener(CleverTapAPI.instanceWithConfig(this, this.config).getCoreState().getInAppController());
            setPermissionCallback(CleverTapAPI.instanceWithConfig(this, this.config).getCoreState().getInAppController());
            this.pushPermissionManager = new PushPermissionManager(this, this.config);
            if (z) {
                showHardPermissionPrompt(extras.getBoolean(InAppController.SHOW_FALLBACK_SETTINGS_BUNDLE_KEY, false));
                return;
            }
            CTInAppNotification cTInAppNotification = this.inAppNotification;
            if (cTInAppNotification == null) {
                finish();
                return;
            }
            if (cTInAppNotification.isPortrait() && !this.inAppNotification.isLandscape()) {
                if (i == 2) {
                    Logger.d("App in Landscape, dismissing portrait InApp Notification");
                    finish();
                    didDismiss(null);
                    return;
                }
                Logger.d("App in Portrait, displaying InApp Notification anyway");
            }
            if (!this.inAppNotification.isPortrait() && this.inAppNotification.isLandscape()) {
                if (i == 1) {
                    Logger.d("App in Portrait, dismissing landscape InApp Notification");
                    finish();
                    didDismiss(null);
                    return;
                }
                Logger.d("App in Landscape, displaying InApp Notification anyway");
            }
            if (bundle != null) {
                if (isAlertVisible) {
                    createContentFragment();
                    return;
                }
                return;
            }
            CTInAppBaseFullFragment cTInAppBaseFullFragmentCreateContentFragment = createContentFragment();
            if (cTInAppBaseFullFragmentCreateContentFragment != null) {
                Bundle bundle3 = new Bundle();
                bundle3.putParcelable(Constants.INAPP_KEY, this.inAppNotification);
                bundle3.putParcelable("config", this.config);
                cTInAppBaseFullFragmentCreateContentFragment.setArguments(bundle3);
                getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).add(android.R.id.content, cTInAppBaseFullFragmentCreateContentFragment, getFragmentTag()).commitNow();
            }
        } catch (Throwable th) {
            Logger.v("Cannot find a valid notification bundle to show!", th);
            finish();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        if (!this.pushPermissionManager.isFromNotificationSettingsActivity() || Build.VERSION.SDK_INT < 33) {
            return;
        }
        if (ContextCompat.checkSelfPermission(this, PushPermissionManager.ANDROID_PERMISSION_STRING) == 0) {
            this.pushPermissionResultCallbackWeakReference.get().onPushPermissionAccept();
        } else {
            this.pushPermissionResultCallbackWeakReference.get().onPushPermissionDeny();
        }
        didDismiss(null);
    }

    @Override // android.app.Activity
    public void finish() {
        super.finish();
        if (Build.VERSION.SDK_INT >= 34) {
            overrideActivityTransition(1, android.R.anim.fade_in, android.R.anim.fade_out);
        } else {
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    }

    @Override // com.clevertap.android.sdk.inapp.InAppListener
    public Bundle inAppNotificationDidClick(CTInAppNotification cTInAppNotification, CTInAppNotificationButton cTInAppNotificationButton, Context context) {
        InAppListener listener = getListener();
        if (listener != null) {
            return listener.inAppNotificationDidClick(cTInAppNotification, cTInAppNotificationButton, this);
        }
        return null;
    }

    @Override // com.clevertap.android.sdk.inapp.InAppListener
    public void inAppNotificationDidDismiss(CTInAppNotification cTInAppNotification, Bundle bundle) {
        didDismiss(bundle);
    }

    @Override // com.clevertap.android.sdk.inapp.InAppListener
    public void inAppNotificationDidShow(CTInAppNotification cTInAppNotification, Bundle bundle) {
        didShow(bundle);
    }

    @Override // com.clevertap.android.sdk.inapp.InAppListener
    public Bundle inAppNotificationActionTriggered(CTInAppNotification cTInAppNotification, CTInAppAction cTInAppAction, String str, Bundle bundle, Context context) {
        InAppListener listener = getListener();
        if (listener != null) {
            return listener.inAppNotificationActionTriggered(cTInAppNotification, cTInAppAction, str, bundle, this);
        }
        return null;
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public void setTheme(int i) {
        super.setTheme(android.R.style.Theme.Translucent.NoTitleBar);
    }

    @Override // com.clevertap.android.sdk.DidClickForHardPermissionListener
    public void didClickForHardPermissionWithFallbackSettings(boolean z) {
        showHardPermissionPrompt(z);
    }

    public void showHardPermissionPrompt(boolean z) {
        this.pushPermissionManager.showHardPermissionPrompt(z, this.pushPermissionResultCallbackWeakReference.get());
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        CTPreferenceCache.getInstance(this, this.config).setFirstTimeRequest(false);
        CTPreferenceCache.updateCacheToDisk(this, this.config);
        if (i == 102) {
            if (iArr.length > 0 && iArr[0] == 0) {
                this.pushPermissionResultCallbackWeakReference.get().onPushPermissionAccept();
            } else {
                this.pushPermissionResultCallbackWeakReference.get().onPushPermissionDeny();
            }
            didDismiss(null);
        }
    }

    void didDismiss(Bundle bundle) {
        didDismiss(bundle, true);
    }

    void didDismiss(Bundle bundle, boolean z) {
        CTInAppNotification cTInAppNotification;
        if (isAlertVisible) {
            isAlertVisible = false;
        }
        if (!this.invokedCallbacks) {
            InAppListener listener = getListener();
            if (listener != null && (cTInAppNotification = this.inAppNotification) != null) {
                listener.inAppNotificationDidDismiss(cTInAppNotification, bundle);
            }
            this.invokedCallbacks = true;
        }
        if (z) {
            finish();
        }
    }

    void didShow(Bundle bundle) {
        InAppListener listener = getListener();
        if (listener != null) {
            listener.inAppNotificationDidShow(this.inAppNotification, bundle);
        }
    }

    InAppListener getListener() {
        InAppListener inAppListener;
        try {
            inAppListener = this.listenerWeakReference.get();
        } catch (Throwable unused) {
            inAppListener = null;
        }
        if (inAppListener == null) {
            this.config.getLogger().verbose(this.config.getAccountId(), "InAppActivityListener is null for notification: " + this.inAppNotification.getJsonDescription());
        }
        return inAppListener;
    }

    void setListener(InAppListener inAppListener) {
        this.listenerWeakReference = new WeakReference<>(inAppListener);
    }

    public void setPermissionCallback(PushPermissionResultCallback pushPermissionResultCallback) {
        this.pushPermissionResultCallbackWeakReference = new WeakReference<>(pushPermissionResultCallback);
    }

    public void notifyPermissionDenied() {
        this.pushPermissionResultCallbackWeakReference.get().onPushPermissionDeny();
    }

    private Bundle didClick(CTInAppNotificationButton cTInAppNotificationButton) {
        InAppListener listener = getListener();
        if (listener != null) {
            return listener.inAppNotificationDidClick(this.inAppNotification, cTInAppNotificationButton, this);
        }
        return null;
    }

    private CTInAppBaseFullFragment createContentFragment() {
        CTInAppType inAppType = this.inAppNotification.getInAppType();
        switch (AnonymousClass2.$SwitchMap$com$clevertap$android$sdk$inapp$CTInAppType[inAppType.ordinal()]) {
            case 1:
                return new CTInAppHtmlCoverFragment();
            case 2:
                return new CTInAppHtmlInterstitialFragment();
            case 3:
                return new CTInAppHtmlHalfInterstitialFragment();
            case 4:
                return new CTInAppNativeCoverFragment();
            case 5:
                return new CTInAppNativeInterstitialFragment();
            case 6:
                return new CTInAppNativeHalfInterstitialFragment();
            case 7:
                return new CTInAppNativeCoverImageFragment();
            case 8:
                return new CTInAppNativeInterstitialImageFragment();
            case 9:
                return new CTInAppNativeHalfInterstitialImageFragment();
            case 10:
                showAlertDialogForInApp();
                return null;
            default:
                this.config.getLogger().verbose("InAppNotificationActivity: Unhandled InApp Type: " + inAppType);
                return null;
        }
    }

    /* renamed from: com.clevertap.android.sdk.InAppNotificationActivity$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$clevertap$android$sdk$inapp$CTInAppType;

        static {
            int[] iArr = new int[CTInAppType.values().length];
            $SwitchMap$com$clevertap$android$sdk$inapp$CTInAppType = iArr;
            try {
                iArr[CTInAppType.CTInAppTypeCoverHTML.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$clevertap$android$sdk$inapp$CTInAppType[CTInAppType.CTInAppTypeInterstitialHTML.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$clevertap$android$sdk$inapp$CTInAppType[CTInAppType.CTInAppTypeHalfInterstitialHTML.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$clevertap$android$sdk$inapp$CTInAppType[CTInAppType.CTInAppTypeCover.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$clevertap$android$sdk$inapp$CTInAppType[CTInAppType.CTInAppTypeInterstitial.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$clevertap$android$sdk$inapp$CTInAppType[CTInAppType.CTInAppTypeHalfInterstitial.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$clevertap$android$sdk$inapp$CTInAppType[CTInAppType.CTInAppTypeCoverImageOnly.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$clevertap$android$sdk$inapp$CTInAppType[CTInAppType.CTInAppTypeInterstitialImageOnly.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$clevertap$android$sdk$inapp$CTInAppType[CTInAppType.CTInAppTypeHalfInterstitialImageOnly.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$clevertap$android$sdk$inapp$CTInAppType[CTInAppType.CTInAppTypeAlert.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    private String getFragmentTag() {
        return this.config.getAccountId() + ":CT_INAPP_CONTENT_FRAGMENT";
    }

    private void showAlertDialogForInApp() {
        ArrayList<CTInAppNotificationButton> buttons = this.inAppNotification.getButtons();
        if (buttons.isEmpty()) {
            this.config.getLogger().debug("InAppNotificationActivity: Notification has no buttons, not showing Alert InApp");
            return;
        }
        final CTInAppNotificationButton cTInAppNotificationButton = buttons.get(0);
        AlertDialog alertDialogCreate = new AlertDialog.Builder(this, android.R.style.Theme.Material.Light.Dialog.Alert).setCancelable(false).setTitle(this.inAppNotification.getTitle()).setMessage(this.inAppNotification.getMessage()).setPositiveButton(cTInAppNotificationButton.getText(), new DialogInterface.OnClickListener() { // from class: com.clevertap.android.sdk.InAppNotificationActivity$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                this.f$0.m4779x9d56a46d(cTInAppNotificationButton, dialogInterface, i);
            }
        }).create();
        if (this.inAppNotification.getButtons().size() == 2) {
            final CTInAppNotificationButton cTInAppNotificationButton2 = buttons.get(1);
            alertDialogCreate.setButton(-2, cTInAppNotificationButton2.getText(), new DialogInterface.OnClickListener() { // from class: com.clevertap.android.sdk.InAppNotificationActivity$$ExternalSyntheticLambda1
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    this.f$0.m4780x2a9155ee(cTInAppNotificationButton2, dialogInterface, i);
                }
            });
        }
        if (buttons.size() > 2) {
            final CTInAppNotificationButton cTInAppNotificationButton3 = buttons.get(2);
            alertDialogCreate.setButton(-3, cTInAppNotificationButton3.getText(), new DialogInterface.OnClickListener() { // from class: com.clevertap.android.sdk.InAppNotificationActivity$$ExternalSyntheticLambda4
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    this.f$0.m4783xd2416a71(cTInAppNotificationButton3, dialogInterface, i);
                }
            });
        }
        alertDialogCreate.show();
        isAlertVisible = true;
        didShow(null);
    }

    /* renamed from: lambda$showAlertDialogForInApp$0$com-clevertap-android-sdk-InAppNotificationActivity, reason: not valid java name */
    /* synthetic */ void m4779x9d56a46d(CTInAppNotificationButton cTInAppNotificationButton, DialogInterface dialogInterface, int i) {
        onAlertButtonClick(cTInAppNotificationButton, true);
    }

    /* renamed from: lambda$showAlertDialogForInApp$1$com-clevertap-android-sdk-InAppNotificationActivity, reason: not valid java name */
    /* synthetic */ void m4780x2a9155ee(CTInAppNotificationButton cTInAppNotificationButton, DialogInterface dialogInterface, int i) {
        onAlertButtonClick(cTInAppNotificationButton, false);
    }

    /* renamed from: lambda$showAlertDialogForInApp$2$com-clevertap-android-sdk-InAppNotificationActivity, reason: not valid java name */
    /* synthetic */ void m4781xb7cc076f(CTInAppNotificationButton cTInAppNotificationButton, DialogInterface dialogInterface, int i) {
        onAlertButtonClickLegacy(cTInAppNotificationButton);
    }

    /* renamed from: lambda$showAlertDialogForInApp$3$com-clevertap-android-sdk-InAppNotificationActivity, reason: not valid java name */
    /* synthetic */ void m4782x4506b8f0(CTInAppNotificationButton cTInAppNotificationButton, DialogInterface dialogInterface, int i) {
        onAlertButtonClickLegacy(cTInAppNotificationButton);
    }

    /* renamed from: lambda$showAlertDialogForInApp$4$com-clevertap-android-sdk-InAppNotificationActivity, reason: not valid java name */
    /* synthetic */ void m4783xd2416a71(CTInAppNotificationButton cTInAppNotificationButton, DialogInterface dialogInterface, int i) {
        onAlertButtonClickLegacy(cTInAppNotificationButton);
    }

    private void onAlertButtonClickLegacy(CTInAppNotificationButton cTInAppNotificationButton) {
        didDismiss(didClick(cTInAppNotificationButton));
    }

    private void onAlertButtonClick(CTInAppNotificationButton cTInAppNotificationButton, boolean z) {
        Bundle bundleDidClick = didClick(cTInAppNotificationButton);
        if (z && this.inAppNotification.isLocalInApp()) {
            showHardPermissionPrompt(this.inAppNotification.fallBackToNotificationSettings());
            return;
        }
        CTInAppAction action = cTInAppNotificationButton.getAction();
        if (action != null && InAppActionType.REQUEST_FOR_PERMISSIONS == action.getType()) {
            showHardPermissionPrompt(action.getShouldFallbackToSettings());
        } else {
            didDismiss(bundleDidClick);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        if (isChangingConfigurations()) {
            return;
        }
        didDismiss(null, false);
    }
}
