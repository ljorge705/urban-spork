package com.clevertap.android.sdk.inapp;

import android.content.Context;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import androidx.fragment.app.Fragment;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.DidClickForHardPermissionListener;
import com.clevertap.android.sdk.customviews.CloseImageView;
import com.clevertap.android.sdk.inapp.images.FileResourceProvider;
import com.clevertap.android.sdk.utils.UriHelper;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.net.URLDecoder;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes5.dex */
public abstract class CTInAppBaseFragment extends Fragment {
    CleverTapInstanceConfig config;
    Context context;
    int currentOrientation;
    private DidClickForHardPermissionListener didClickForHardPermissionListener;
    CTInAppNotification inAppNotification;
    private WeakReference<InAppListener> listenerWeakReference;
    private FileResourceProvider provider;
    CloseImageView closeImageView = null;
    AtomicBoolean isCleanedUp = new AtomicBoolean();

    abstract void cleanup();

    abstract void generateListener();

    public FileResourceProvider resourceProvider() {
        return this.provider;
    }

    class CTInAppNativeButtonClickListener implements View.OnClickListener {
        CTInAppNativeButtonClickListener() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            CTInAppBaseFragment.this.handleButtonClickAtIndex(((Integer) view.getTag()).intValue());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.inAppNotification = (CTInAppNotification) arguments.getParcelable(Constants.INAPP_KEY);
            CleverTapInstanceConfig cleverTapInstanceConfig = (CleverTapInstanceConfig) arguments.getParcelable("config");
            this.config = cleverTapInstanceConfig;
            this.provider = new FileResourceProvider(context, cleverTapInstanceConfig != null ? cleverTapInstanceConfig.getLogger() : null);
            this.currentOrientation = getResources().getConfiguration().orientation;
            generateListener();
            if (context instanceof DidClickForHardPermissionListener) {
                this.didClickForHardPermissionListener = (DidClickForHardPermissionListener) context;
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        didShow(null);
    }

    public void triggerAction(CTInAppAction cTInAppAction, String str, Bundle bundle) {
        if (cTInAppAction.getType() == InAppActionType.OPEN_URL) {
            Bundle allKeyValuePairs = UriHelper.getAllKeyValuePairs(cTInAppAction.getActionUrl(), false);
            String string = allKeyValuePairs.getString(Constants.KEY_C2A);
            allKeyValuePairs.remove(Constants.KEY_C2A);
            if (bundle != null) {
                allKeyValuePairs.putAll(bundle);
            }
            if (string != null) {
                String[] strArrSplit = string.split(Constants.URL_PARAM_DL_SEPARATOR);
                if (strArrSplit.length == 2) {
                    try {
                        string = URLDecoder.decode(strArrSplit[0], "UTF-8");
                    } catch (UnsupportedEncodingException | IllegalArgumentException e) {
                        this.config.getLogger().debug("Error parsing c2a param", e);
                    }
                    cTInAppAction = CTInAppAction.createOpenUrlAction(strArrSplit[1]);
                }
            }
            bundle = allKeyValuePairs;
            if (str == null) {
                str = string;
            }
        }
        if (str == null) {
            str = "";
        }
        didDismiss(notifyActionTriggered(cTInAppAction, str, bundle));
    }

    void openActionUrl(String str) {
        triggerAction(CTInAppAction.createOpenUrlAction(str), null, null);
    }

    public void didDismiss(Bundle bundle) {
        cleanup();
        InAppListener listener = getListener();
        if (listener != null) {
            listener.inAppNotificationDidDismiss(this.inAppNotification, bundle);
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
            this.config.getLogger().verbose(this.config.getAccountId(), "InAppListener is null for notification: " + this.inAppNotification.getJsonDescription());
        }
        return inAppListener;
    }

    void setListener(InAppListener inAppListener) {
        this.listenerWeakReference = new WeakReference<>(inAppListener);
    }

    int getScaledPixels(int i) {
        return (int) TypedValue.applyDimension(1, i, getResources().getDisplayMetrics());
    }

    void handleButtonClickAtIndex(int i) {
        DidClickForHardPermissionListener didClickForHardPermissionListener;
        DidClickForHardPermissionListener didClickForHardPermissionListener2;
        try {
            CTInAppNotificationButton cTInAppNotificationButton = this.inAppNotification.getButtons().get(i);
            Bundle bundleDidClick = didClick(cTInAppNotificationButton);
            if (i == 0 && this.inAppNotification.isLocalInApp() && (didClickForHardPermissionListener2 = this.didClickForHardPermissionListener) != null) {
                didClickForHardPermissionListener2.didClickForHardPermissionWithFallbackSettings(this.inAppNotification.fallBackToNotificationSettings());
                return;
            }
            CTInAppAction action = cTInAppNotificationButton.getAction();
            if (action != null && InAppActionType.REQUEST_FOR_PERMISSIONS == action.getType() && (didClickForHardPermissionListener = this.didClickForHardPermissionListener) != null) {
                didClickForHardPermissionListener.didClickForHardPermissionWithFallbackSettings(action.getShouldFallbackToSettings());
            } else {
                didDismiss(bundleDidClick);
            }
        } catch (Throwable th) {
            this.config.getLogger().debug("Error handling notification button click: " + th.getCause());
            didDismiss(null);
        }
    }

    private Bundle didClick(CTInAppNotificationButton cTInAppNotificationButton) {
        CTInAppAction action = cTInAppNotificationButton.getAction();
        if (action == null) {
            action = CTInAppAction.createCloseAction();
        }
        return notifyActionTriggered(action, cTInAppNotificationButton.getText(), null);
    }

    private Bundle notifyActionTriggered(CTInAppAction cTInAppAction, String str, Bundle bundle) {
        InAppListener listener = getListener();
        if (listener != null) {
            return listener.inAppNotificationActionTriggered(this.inAppNotification, cTInAppAction, str, bundle, getActivity());
        }
        return null;
    }
}
