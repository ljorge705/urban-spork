package com.clevertap.android.sdk;

import android.os.Handler;
import android.os.Looper;
import com.clevertap.android.sdk.displayunits.DisplayUnitListener;
import com.clevertap.android.sdk.displayunits.model.CleverTapDisplayUnit;
import com.clevertap.android.sdk.inapp.callbacks.FetchInAppsCallback;
import com.clevertap.android.sdk.interfaces.NotificationRenderedListener;
import com.clevertap.android.sdk.interfaces.OnInitCleverTapIDListener;
import com.clevertap.android.sdk.interfaces.SCDomainListener;
import com.clevertap.android.sdk.login.ChangeUserCallback;
import com.clevertap.android.sdk.network.BatchListener;
import com.clevertap.android.sdk.product_config.CTProductConfigListener;
import com.clevertap.android.sdk.pushnotification.CTPushNotificationListener;
import com.clevertap.android.sdk.pushnotification.amp.CTPushAmpListener;
import com.clevertap.android.sdk.variables.callbacks.FetchVariablesCallback;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes5.dex */
public class CallbackManager extends BaseCallbackManager {
    private BatchListener batchListener;
    private final CleverTapInstanceConfig config;
    private final DeviceInfo deviceInfo;
    private WeakReference<DisplayUnitListener> displayUnitListenerWeakReference;
    private FailureFlushListener failureFlushListener;

    @Deprecated
    private WeakReference<CTFeatureFlagsListener> featureFlagListenerWeakReference;
    private FetchInAppsCallback fetchInAppsCallback;
    private FetchVariablesCallback fetchVariablesCallback;
    private GeofenceCallback geofenceCallback;
    private WeakReference<InAppNotificationButtonListener> inAppNotificationButtonListener;
    private InAppNotificationListener inAppNotificationListener;
    private CTInboxListener inboxListener;
    private NotificationRenderedListener notificationRenderedListener;

    @Deprecated
    private WeakReference<CTProductConfigListener> productConfigListener;
    private SCDomainListener scDomainListener;
    private final List<PushPermissionResponseListener> pushPermissionResponseListenerList = new ArrayList();
    private final List<OnInitCleverTapIDListener> onInitCleverTapIDListeners = new ArrayList();
    private CTPushAmpListener pushAmpListener = null;
    private CTPushNotificationListener pushNotificationListener = null;
    private SyncListener syncListener = null;
    private final List<ChangeUserCallback> changeUserCallbackList = Collections.synchronizedList(new ArrayList());

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    public BatchListener getBatchListener() {
        return this.batchListener;
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    public List<ChangeUserCallback> getChangeUserCallbackList() {
        return this.changeUserCallbackList;
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    public FailureFlushListener getFailureFlushListener() {
        return this.failureFlushListener;
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    public FetchInAppsCallback getFetchInAppsCallback() {
        return this.fetchInAppsCallback;
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    public FetchVariablesCallback getFetchVariablesCallback() {
        return this.fetchVariablesCallback;
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    public GeofenceCallback getGeofenceCallback() {
        return this.geofenceCallback;
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    public InAppNotificationListener getInAppNotificationListener() {
        return this.inAppNotificationListener;
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    public CTInboxListener getInboxListener() {
        return this.inboxListener;
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    public CTPushAmpListener getPushAmpListener() {
        return this.pushAmpListener;
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    public CTPushNotificationListener getPushNotificationListener() {
        return this.pushNotificationListener;
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    public List<PushPermissionResponseListener> getPushPermissionResponseListenerList() {
        return this.pushPermissionResponseListenerList;
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    public SCDomainListener getSCDomainListener() {
        return this.scDomainListener;
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    public SyncListener getSyncListener() {
        return this.syncListener;
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    public void setBatchListener(BatchListener batchListener) {
        this.batchListener = batchListener;
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    public void setFailureFlushListener(FailureFlushListener failureFlushListener) {
        this.failureFlushListener = failureFlushListener;
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    public void setFetchInAppsCallback(FetchInAppsCallback fetchInAppsCallback) {
        this.fetchInAppsCallback = fetchInAppsCallback;
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    public void setFetchVariablesCallback(FetchVariablesCallback fetchVariablesCallback) {
        this.fetchVariablesCallback = fetchVariablesCallback;
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    public void setGeofenceCallback(GeofenceCallback geofenceCallback) {
        this.geofenceCallback = geofenceCallback;
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    public void setInAppNotificationListener(InAppNotificationListener inAppNotificationListener) {
        this.inAppNotificationListener = inAppNotificationListener;
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    public void setInboxListener(CTInboxListener cTInboxListener) {
        this.inboxListener = cTInboxListener;
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    public void setPushAmpListener(CTPushAmpListener cTPushAmpListener) {
        this.pushAmpListener = cTPushAmpListener;
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    public void setPushNotificationListener(CTPushNotificationListener cTPushNotificationListener) {
        this.pushNotificationListener = cTPushNotificationListener;
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    public void setSCDomainListener(SCDomainListener sCDomainListener) {
        this.scDomainListener = sCDomainListener;
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    public void setSyncListener(SyncListener syncListener) {
        this.syncListener = syncListener;
    }

    public CallbackManager(CleverTapInstanceConfig cleverTapInstanceConfig, DeviceInfo deviceInfo) {
        this.config = cleverTapInstanceConfig;
        this.deviceInfo = deviceInfo;
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    public void _notifyInboxMessagesDidUpdate() {
        if (this.inboxListener != null) {
            Utils.runOnUiThread(new Runnable() { // from class: com.clevertap.android.sdk.CallbackManager.1
                @Override // java.lang.Runnable
                public void run() {
                    if (CallbackManager.this.inboxListener != null) {
                        CallbackManager.this.inboxListener.inboxMessagesDidUpdate();
                    }
                }
            });
        }
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    public void addChangeUserCallback(ChangeUserCallback changeUserCallback) {
        this.changeUserCallbackList.add(changeUserCallback);
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    public void removeChangeUserCallback(ChangeUserCallback changeUserCallback) {
        this.changeUserCallbackList.remove(changeUserCallback);
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    @Deprecated
    public CTFeatureFlagsListener getFeatureFlagListener() {
        WeakReference<CTFeatureFlagsListener> weakReference = this.featureFlagListenerWeakReference;
        if (weakReference == null || weakReference.get() == null) {
            return null;
        }
        return this.featureFlagListenerWeakReference.get();
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    @Deprecated
    public void setFeatureFlagListener(CTFeatureFlagsListener cTFeatureFlagsListener) {
        this.featureFlagListenerWeakReference = new WeakReference<>(cTFeatureFlagsListener);
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    public InAppNotificationButtonListener getInAppNotificationButtonListener() {
        WeakReference<InAppNotificationButtonListener> weakReference = this.inAppNotificationButtonListener;
        if (weakReference == null || weakReference.get() == null) {
            return null;
        }
        return this.inAppNotificationButtonListener.get();
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    public void setInAppNotificationButtonListener(InAppNotificationButtonListener inAppNotificationButtonListener) {
        this.inAppNotificationButtonListener = new WeakReference<>(inAppNotificationButtonListener);
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    public void registerPushPermissionResponseListener(PushPermissionResponseListener pushPermissionResponseListener) {
        this.pushPermissionResponseListenerList.add(pushPermissionResponseListener);
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    public void unregisterPushPermissionResponseListener(PushPermissionResponseListener pushPermissionResponseListener) {
        this.pushPermissionResponseListenerList.remove(pushPermissionResponseListener);
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    @Deprecated
    public CTProductConfigListener getProductConfigListener() {
        WeakReference<CTProductConfigListener> weakReference = this.productConfigListener;
        if (weakReference == null || weakReference.get() == null) {
            return null;
        }
        return this.productConfigListener.get();
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    @Deprecated
    public void setProductConfigListener(CTProductConfigListener cTProductConfigListener) {
        if (cTProductConfigListener != null) {
            this.productConfigListener = new WeakReference<>(cTProductConfigListener);
        }
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    public void addOnInitCleverTapIDListener(OnInitCleverTapIDListener onInitCleverTapIDListener) {
        this.onInitCleverTapIDListeners.add(onInitCleverTapIDListener);
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    public void removeOnInitCleverTapIDListener(OnInitCleverTapIDListener onInitCleverTapIDListener) {
        this.onInitCleverTapIDListeners.remove(onInitCleverTapIDListener);
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    public void notifyCleverTapIDChanged(final String str) {
        Handler handler = new Handler(Looper.getMainLooper());
        for (final OnInitCleverTapIDListener onInitCleverTapIDListener : this.onInitCleverTapIDListeners) {
            handler.post(new Runnable() { // from class: com.clevertap.android.sdk.CallbackManager$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    onInitCleverTapIDListener.onInitCleverTapID(str);
                }
            });
        }
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    public void notifyUserProfileInitialized(String str) {
        if (str == null) {
            str = this.deviceInfo.getDeviceID();
        }
        if (str == null) {
            return;
        }
        try {
            SyncListener syncListener = getSyncListener();
            if (syncListener != null) {
                syncListener.profileDidInitialize(str);
            }
        } catch (Throwable unused) {
        }
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    public void setDisplayUnitListener(DisplayUnitListener displayUnitListener) {
        if (displayUnitListener != null) {
            this.displayUnitListenerWeakReference = new WeakReference<>(displayUnitListener);
        } else {
            this.config.getLogger().verbose(this.config.getAccountId(), "DisplayUnit : Failed to set - DisplayUnitListener can't be null");
        }
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    void _notifyInboxInitialized() {
        CTInboxListener cTInboxListener = this.inboxListener;
        if (cTInboxListener != null) {
            cTInboxListener.inboxDidInitialize();
        }
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    public void notifyDisplayUnitsLoaded(final ArrayList<CleverTapDisplayUnit> arrayList) {
        if (arrayList != null && !arrayList.isEmpty()) {
            WeakReference<DisplayUnitListener> weakReference = this.displayUnitListenerWeakReference;
            if (weakReference != null && weakReference.get() != null) {
                Utils.runOnUiThread(new Runnable() { // from class: com.clevertap.android.sdk.CallbackManager.2
                    @Override // java.lang.Runnable
                    public void run() {
                        if (CallbackManager.this.displayUnitListenerWeakReference == null || CallbackManager.this.displayUnitListenerWeakReference.get() == null) {
                            return;
                        }
                        ((DisplayUnitListener) CallbackManager.this.displayUnitListenerWeakReference.get()).onDisplayUnitsLoaded(arrayList);
                    }
                });
                return;
            } else {
                this.config.getLogger().verbose(this.config.getAccountId(), "DisplayUnit : No registered listener, failed to notify");
                return;
            }
        }
        this.config.getLogger().verbose(this.config.getAccountId(), "DisplayUnit : No Display Units found");
    }

    @Override // com.clevertap.android.sdk.BaseCallbackManager
    void notifyUserProfileInitialized() {
        notifyUserProfileInitialized(this.deviceInfo.getDeviceID());
    }
}
