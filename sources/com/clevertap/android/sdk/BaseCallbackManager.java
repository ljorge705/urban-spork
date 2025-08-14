package com.clevertap.android.sdk;

import com.clevertap.android.sdk.displayunits.DisplayUnitListener;
import com.clevertap.android.sdk.displayunits.model.CleverTapDisplayUnit;
import com.clevertap.android.sdk.inapp.callbacks.FetchInAppsCallback;
import com.clevertap.android.sdk.interfaces.OnInitCleverTapIDListener;
import com.clevertap.android.sdk.interfaces.SCDomainListener;
import com.clevertap.android.sdk.login.ChangeUserCallback;
import com.clevertap.android.sdk.network.BatchListener;
import com.clevertap.android.sdk.product_config.CTProductConfigListener;
import com.clevertap.android.sdk.pushnotification.CTPushNotificationListener;
import com.clevertap.android.sdk.pushnotification.amp.CTPushAmpListener;
import com.clevertap.android.sdk.variables.callbacks.FetchVariablesCallback;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public abstract class BaseCallbackManager {
    abstract void _notifyInboxInitialized();

    public abstract void _notifyInboxMessagesDidUpdate();

    public abstract void addChangeUserCallback(ChangeUserCallback changeUserCallback);

    public abstract void addOnInitCleverTapIDListener(OnInitCleverTapIDListener onInitCleverTapIDListener);

    public abstract BatchListener getBatchListener();

    public abstract List<ChangeUserCallback> getChangeUserCallbackList();

    public abstract FailureFlushListener getFailureFlushListener();

    @Deprecated
    public abstract CTFeatureFlagsListener getFeatureFlagListener();

    public abstract FetchInAppsCallback getFetchInAppsCallback();

    public abstract FetchVariablesCallback getFetchVariablesCallback();

    public abstract GeofenceCallback getGeofenceCallback();

    public abstract InAppNotificationButtonListener getInAppNotificationButtonListener();

    public abstract InAppNotificationListener getInAppNotificationListener();

    public abstract CTInboxListener getInboxListener();

    @Deprecated
    public abstract CTProductConfigListener getProductConfigListener();

    public abstract CTPushAmpListener getPushAmpListener();

    public abstract CTPushNotificationListener getPushNotificationListener();

    public abstract List<PushPermissionResponseListener> getPushPermissionResponseListenerList();

    public abstract SCDomainListener getSCDomainListener();

    public abstract SyncListener getSyncListener();

    public abstract void notifyCleverTapIDChanged(String str);

    public abstract void notifyDisplayUnitsLoaded(ArrayList<CleverTapDisplayUnit> arrayList);

    abstract void notifyUserProfileInitialized();

    public abstract void notifyUserProfileInitialized(String str);

    public abstract void registerPushPermissionResponseListener(PushPermissionResponseListener pushPermissionResponseListener);

    public abstract void removeChangeUserCallback(ChangeUserCallback changeUserCallback);

    public abstract void removeOnInitCleverTapIDListener(OnInitCleverTapIDListener onInitCleverTapIDListener);

    public abstract void setBatchListener(BatchListener batchListener);

    public abstract void setDisplayUnitListener(DisplayUnitListener displayUnitListener);

    public abstract void setFailureFlushListener(FailureFlushListener failureFlushListener);

    @Deprecated
    public abstract void setFeatureFlagListener(CTFeatureFlagsListener cTFeatureFlagsListener);

    public abstract void setFetchInAppsCallback(FetchInAppsCallback fetchInAppsCallback);

    public abstract void setFetchVariablesCallback(FetchVariablesCallback fetchVariablesCallback);

    public abstract void setGeofenceCallback(GeofenceCallback geofenceCallback);

    public abstract void setInAppNotificationButtonListener(InAppNotificationButtonListener inAppNotificationButtonListener);

    public abstract void setInAppNotificationListener(InAppNotificationListener inAppNotificationListener);

    public abstract void setInboxListener(CTInboxListener cTInboxListener);

    @Deprecated
    public abstract void setProductConfigListener(CTProductConfigListener cTProductConfigListener);

    public abstract void setPushAmpListener(CTPushAmpListener cTPushAmpListener);

    public abstract void setPushNotificationListener(CTPushNotificationListener cTPushNotificationListener);

    public abstract void setSCDomainListener(SCDomainListener sCDomainListener);

    public abstract void setSyncListener(SyncListener syncListener);

    public abstract void unregisterPushPermissionResponseListener(PushPermissionResponseListener pushPermissionResponseListener);
}
