package com.clevertap.android.sdk;

import android.content.Context;
import com.clevertap.android.sdk.db.BaseDatabaseManager;
import com.clevertap.android.sdk.displayunits.CTDisplayUnitController;
import com.clevertap.android.sdk.featureFlags.CTFeatureFlagsController;
import com.clevertap.android.sdk.inapp.InAppController;
import com.clevertap.android.sdk.inbox.CTInboxController;
import com.clevertap.android.sdk.network.BatchListener;
import com.clevertap.android.sdk.product_config.CTProductConfigController;
import com.clevertap.android.sdk.pushnotification.PushProviders;
import com.clevertap.android.sdk.task.CTExecutorFactory;
import com.clevertap.android.sdk.variables.CTVariables;
import com.clevertap.android.sdk.variables.callbacks.FetchVariablesCallback;
import com.clevertap.android.sdk.video.VideoLibChecker;
import java.util.concurrent.Callable;
import org.json.JSONArray;

/* loaded from: classes5.dex */
public class ControllerManager {
    private final BaseDatabaseManager baseDatabaseManager;
    private final BaseCallbackManager callbackManager;
    private final CleverTapInstanceConfig config;
    private final Context context;
    private CTDisplayUnitController ctDisplayUnitController;

    @Deprecated
    private CTFeatureFlagsController ctFeatureFlagsController;
    private CTInboxController ctInboxController;
    private final CTLockManager ctLockManager;

    @Deprecated
    private CTProductConfigController ctProductConfigController;
    private CTVariables ctVariables;
    private final DeviceInfo deviceInfo;
    private InAppController inAppController;
    private InAppFCManager inAppFCManager;
    private PushProviders pushProviders;

    public CTDisplayUnitController getCTDisplayUnitController() {
        return this.ctDisplayUnitController;
    }

    @Deprecated
    public CTFeatureFlagsController getCTFeatureFlagsController() {
        return this.ctFeatureFlagsController;
    }

    public CTInboxController getCTInboxController() {
        return this.ctInboxController;
    }

    @Deprecated
    public CTProductConfigController getCTProductConfigController() {
        return this.ctProductConfigController;
    }

    public CleverTapInstanceConfig getConfig() {
        return this.config;
    }

    public CTVariables getCtVariables() {
        return this.ctVariables;
    }

    public InAppController getInAppController() {
        return this.inAppController;
    }

    public InAppFCManager getInAppFCManager() {
        return this.inAppFCManager;
    }

    public PushProviders getPushProviders() {
        return this.pushProviders;
    }

    public void setCTDisplayUnitController(CTDisplayUnitController cTDisplayUnitController) {
        this.ctDisplayUnitController = cTDisplayUnitController;
    }

    @Deprecated
    public void setCTFeatureFlagsController(CTFeatureFlagsController cTFeatureFlagsController) {
        this.ctFeatureFlagsController = cTFeatureFlagsController;
    }

    public void setCTInboxController(CTInboxController cTInboxController) {
        this.ctInboxController = cTInboxController;
    }

    @Deprecated
    public void setCTProductConfigController(CTProductConfigController cTProductConfigController) {
        this.ctProductConfigController = cTProductConfigController;
    }

    public void setCtVariables(CTVariables cTVariables) {
        this.ctVariables = cTVariables;
    }

    public void setInAppController(InAppController inAppController) {
        this.inAppController = inAppController;
    }

    public void setInAppFCManager(InAppFCManager inAppFCManager) {
        this.inAppFCManager = inAppFCManager;
    }

    public void setPushProviders(PushProviders pushProviders) {
        this.pushProviders = pushProviders;
    }

    public ControllerManager(Context context, CleverTapInstanceConfig cleverTapInstanceConfig, CTLockManager cTLockManager, BaseCallbackManager baseCallbackManager, DeviceInfo deviceInfo, BaseDatabaseManager baseDatabaseManager) {
        this.config = cleverTapInstanceConfig;
        this.ctLockManager = cTLockManager;
        this.callbackManager = baseCallbackManager;
        this.deviceInfo = deviceInfo;
        this.context = context;
        this.baseDatabaseManager = baseDatabaseManager;
    }

    public void initializeInbox() {
        if (this.config.isAnalyticsOnly()) {
            this.config.getLogger().debug(this.config.getAccountId(), "Instance is analytics only, not initializing Notification Inbox");
        } else {
            CTExecutorFactory.executors(this.config).postAsyncSafelyTask().execute("initializeInbox", new Callable<Void>() { // from class: com.clevertap.android.sdk.ControllerManager.1
                @Override // java.util.concurrent.Callable
                public Void call() {
                    ControllerManager.this._initializeInbox();
                    return null;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _initializeInbox() {
        synchronized (this.ctLockManager.getInboxControllerLock()) {
            if (getCTInboxController() != null) {
                this.callbackManager._notifyInboxInitialized();
                return;
            }
            if (this.deviceInfo.getDeviceID() != null) {
                setCTInboxController(new CTInboxController(this.config, this.deviceInfo.getDeviceID(), this.baseDatabaseManager.loadDBAdapter(this.context), this.ctLockManager, this.callbackManager, VideoLibChecker.haveVideoPlayerSupport));
                this.callbackManager._notifyInboxInitialized();
            } else {
                this.config.getLogger().info("CRITICAL : No device ID found!");
            }
        }
    }

    public void invokeCallbacksForNetworkError() {
        if (this.ctVariables != null) {
            FetchVariablesCallback fetchVariablesCallback = this.callbackManager.getFetchVariablesCallback();
            this.callbackManager.setFetchVariablesCallback(null);
            this.ctVariables.handleVariableResponseError(fetchVariablesCallback);
        }
    }

    public void invokeBatchListener(JSONArray jSONArray, boolean z) {
        BatchListener batchListener = this.callbackManager.getBatchListener();
        if (batchListener != null) {
            batchListener.onBatchSent(jSONArray, z);
        }
    }
}
