package com.clevertap.android.sdk.login;

import android.content.Context;
import com.clevertap.android.sdk.AnalyticsManager;
import com.clevertap.android.sdk.BaseCallbackManager;
import com.clevertap.android.sdk.CTLockManager;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.ControllerManager;
import com.clevertap.android.sdk.CoreMetaData;
import com.clevertap.android.sdk.DeviceInfo;
import com.clevertap.android.sdk.LocalDataStore;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.SessionManager;
import com.clevertap.android.sdk.cryption.CryptHandler;
import com.clevertap.android.sdk.db.BaseDatabaseManager;
import com.clevertap.android.sdk.db.DBManager;
import com.clevertap.android.sdk.events.BaseEventQueueManager;
import com.clevertap.android.sdk.events.EventGroup;
import com.clevertap.android.sdk.featureFlags.CTFeatureFlagsController;
import com.clevertap.android.sdk.product_config.CTProductConfigFactory;
import com.clevertap.android.sdk.pushnotification.PushProviders;
import com.clevertap.android.sdk.task.CTExecutorFactory;
import com.clevertap.android.sdk.validation.ValidationResult;
import com.clevertap.android.sdk.validation.ValidationResultStack;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/* loaded from: classes5.dex */
public class LoginController {
    private final AnalyticsManager analyticsManager;
    private final BaseEventQueueManager baseEventQueueManager;
    private String cachedGUID = null;
    private final BaseCallbackManager callbackManager;
    private final CleverTapInstanceConfig config;
    private final Context context;
    private final ControllerManager controllerManager;
    private final CoreMetaData coreMetaData;
    private final CryptHandler cryptHandler;
    private final CTLockManager ctLockManager;
    private final BaseDatabaseManager dbManager;
    private final DeviceInfo deviceInfo;
    private final LocalDataStore localDataStore;
    private final PushProviders pushProviders;
    private final SessionManager sessionManager;
    private final ValidationResultStack validationResultStack;

    public LoginController(Context context, CleverTapInstanceConfig cleverTapInstanceConfig, DeviceInfo deviceInfo, ValidationResultStack validationResultStack, BaseEventQueueManager baseEventQueueManager, AnalyticsManager analyticsManager, CoreMetaData coreMetaData, ControllerManager controllerManager, SessionManager sessionManager, LocalDataStore localDataStore, BaseCallbackManager baseCallbackManager, DBManager dBManager, CTLockManager cTLockManager, CryptHandler cryptHandler) {
        this.config = cleverTapInstanceConfig;
        this.context = context;
        this.deviceInfo = deviceInfo;
        this.validationResultStack = validationResultStack;
        this.baseEventQueueManager = baseEventQueueManager;
        this.analyticsManager = analyticsManager;
        this.coreMetaData = coreMetaData;
        this.pushProviders = controllerManager.getPushProviders();
        this.sessionManager = sessionManager;
        this.localDataStore = localDataStore;
        this.callbackManager = baseCallbackManager;
        this.dbManager = dBManager;
        this.controllerManager = controllerManager;
        this.ctLockManager = cTLockManager;
        this.cryptHandler = cryptHandler;
    }

    public void asyncProfileSwitchUser(final Map<String, Object> map, final String str, final String str2) {
        CTExecutorFactory.executors(this.config).postAsyncSafelyTask().execute("resetProfile", new Callable<Void>() { // from class: com.clevertap.android.sdk.login.LoginController.1
            @Override // java.util.concurrent.Callable
            public Void call() {
                String str3;
                try {
                    Logger logger = LoginController.this.config.getLogger();
                    String accountId = LoginController.this.config.getAccountId();
                    StringBuilder sbAppend = new StringBuilder("asyncProfileSwitchUser:[profile ").append(map).append(" with Cached GUID ");
                    if (str != null) {
                        str3 = LoginController.this.cachedGUID;
                    } else {
                        str3 = "NULL and cleverTapID " + str2;
                    }
                    logger.verbose(accountId, sbAppend.append(str3).toString());
                    LoginController.this.coreMetaData.setCurrentUserOptedOut(false);
                    LoginController.this.pushProviders.forcePushDeviceToken(false);
                    LoginController.this.baseEventQueueManager.flushQueueSync(LoginController.this.context, EventGroup.REGULAR);
                    LoginController.this.baseEventQueueManager.flushQueueSync(LoginController.this.context, EventGroup.PUSH_NOTIFICATION_VIEWED);
                    LoginController.this.dbManager.clearQueues(LoginController.this.context);
                    CoreMetaData.setActivityCount(1);
                    LoginController.this.sessionManager.destroySession();
                    if (str != null) {
                        LoginController.this.deviceInfo.forceUpdateDeviceId(str);
                        LoginController.this.callbackManager.notifyUserProfileInitialized(str);
                    } else if (LoginController.this.config.getEnableCustomCleverTapId()) {
                        LoginController.this.deviceInfo.forceUpdateCustomCleverTapID(str2);
                    } else {
                        LoginController.this.deviceInfo.forceNewDeviceID();
                    }
                    LoginController.this.localDataStore.changeUser();
                    LoginController.this.callbackManager.notifyUserProfileInitialized(LoginController.this.deviceInfo.getDeviceID());
                    LoginController.this.deviceInfo.setCurrentUserOptOutStateFromStorage();
                    LoginController.this.resetVariables();
                    LoginController.this.analyticsManager.forcePushAppLaunchedEvent();
                    if (map != null) {
                        LoginController.this.analyticsManager.pushProfile(map);
                    }
                    LoginController.this.pushProviders.forcePushDeviceToken(true);
                    LoginController.this.resetInbox();
                    LoginController.this.resetFeatureFlags();
                    LoginController.this.resetProductConfigs();
                    LoginController.this.recordDeviceIDErrors();
                    LoginController.this.resetDisplayUnits();
                    LoginController.this.notifyChangeUserCallback();
                    LoginController.this.controllerManager.getInAppFCManager().changeUser(LoginController.this.deviceInfo.getDeviceID());
                    return null;
                } catch (Throwable th) {
                    LoginController.this.config.getLogger().verbose(LoginController.this.config.getAccountId(), "Reset Profile error", th);
                    return null;
                }
            }
        });
    }

    public void notifyChangeUserCallback() {
        List<ChangeUserCallback> changeUserCallbackList = this.callbackManager.getChangeUserCallbackList();
        synchronized (changeUserCallbackList) {
            for (ChangeUserCallback changeUserCallback : changeUserCallbackList) {
                if (changeUserCallback != null) {
                    changeUserCallback.onChangeUser(this.deviceInfo.getDeviceID(), this.config.getAccountId());
                }
            }
        }
    }

    public void onUserLogin(final Map<String, Object> map, final String str) {
        if (this.config.getEnableCustomCleverTapId()) {
            if (str == null) {
                Logger.i("CLEVERTAP_USE_CUSTOM_ID has been specified in the AndroidManifest.xml Please call onUserlogin() and pass a custom CleverTap ID");
            }
        } else if (str != null) {
            Logger.i("CLEVERTAP_USE_CUSTOM_ID has not been specified in the AndroidManifest.xml Please call CleverTapAPI.defaultInstance() without a custom CleverTap ID");
        }
        CTExecutorFactory.executors(this.config).postAsyncSafelyTask().execute("_onUserLogin", new Callable<Void>() { // from class: com.clevertap.android.sdk.login.LoginController.2
            @Override // java.util.concurrent.Callable
            public Void call() {
                LoginController.this._onUserLogin(map, str);
                return null;
            }
        });
    }

    public void recordDeviceIDErrors() {
        Iterator<ValidationResult> it = this.deviceInfo.getValidationResults().iterator();
        while (it.hasNext()) {
            this.validationResultStack.pushValidationResult(it.next());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _onUserLogin(Map<String, Object> map, String str) {
        String string;
        if (map == null) {
            return;
        }
        try {
            String deviceID = this.deviceInfo.getDeviceID();
            if (deviceID == null) {
                return;
            }
            LoginInfoProvider loginInfoProvider = new LoginInfoProvider(this.context, this.config, this.deviceInfo, this.cryptHandler);
            IdentityRepo repo = IdentityRepoFactory.getRepo(this.context, this.config, this.deviceInfo, this.validationResultStack);
            boolean z = false;
            for (String str2 : map.keySet()) {
                Object obj = map.get(str2);
                if (repo.hasIdentity(str2)) {
                    if (obj != null) {
                        try {
                            string = obj.toString();
                        } catch (Throwable unused) {
                            continue;
                        }
                    } else {
                        string = null;
                    }
                    if (string != null && string.length() > 0) {
                        z = true;
                        String gUIDForIdentifier = loginInfoProvider.getGUIDForIdentifier(str2, string);
                        this.cachedGUID = gUIDForIdentifier;
                        if (gUIDForIdentifier != null) {
                            break;
                        }
                    }
                }
            }
            if (!this.deviceInfo.isErrorDeviceId() && (!z || loginInfoProvider.isAnonymousDevice())) {
                this.config.getLogger().debug(this.config.getAccountId(), "onUserLogin: no identifier provided or device is anonymous, pushing on current user profile");
                this.analyticsManager.pushProfile(map);
                return;
            }
            String str3 = this.cachedGUID;
            if (str3 != null && str3.equals(deviceID)) {
                this.config.getLogger().debug(this.config.getAccountId(), "onUserLogin: " + map.toString() + " maps to current device id " + deviceID + " pushing on current profile");
                this.analyticsManager.pushProfile(map);
                return;
            }
            Logger logger = this.config.getLogger();
            String accountId = this.config.getAccountId();
            StringBuilder sbAppend = new StringBuilder().append("onUserLogin: queuing reset profile for ").append(map).append(" with Cached GUID ");
            String str4 = this.cachedGUID;
            if (str4 == null) {
                str4 = "NULL";
            }
            logger.verbose(accountId, sbAppend.append(str4).toString());
            asyncProfileSwitchUser(map, this.cachedGUID, str);
        } catch (Throwable th) {
            this.config.getLogger().verbose(this.config.getAccountId(), "onUserLogin failed", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetDisplayUnits() {
        if (this.controllerManager.getCTDisplayUnitController() != null) {
            this.controllerManager.getCTDisplayUnitController().reset();
        } else {
            this.config.getLogger().verbose(this.config.getAccountId(), "DisplayUnit : Can't reset Display Units, DisplayUnitcontroller is null");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetFeatureFlags() {
        CTFeatureFlagsController cTFeatureFlagsController = this.controllerManager.getCTFeatureFlagsController();
        if (cTFeatureFlagsController != null && cTFeatureFlagsController.isInitialized()) {
            cTFeatureFlagsController.resetWithGuid(this.deviceInfo.getDeviceID());
            cTFeatureFlagsController.fetchFeatureFlags();
        } else {
            this.config.getLogger().verbose(this.config.getAccountId(), "DisplayUnit : Can't reset Display Units, CTFeatureFlagsController is null");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetInbox() {
        synchronized (this.ctLockManager.getInboxControllerLock()) {
            this.controllerManager.setCTInboxController(null);
        }
        this.controllerManager.initializeInbox();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetProductConfigs() {
        if (this.config.isAnalyticsOnly()) {
            this.config.getLogger().debug(this.config.getAccountId(), "Product Config is not enabled for this instance");
            return;
        }
        if (this.controllerManager.getCTProductConfigController() != null) {
            this.controllerManager.getCTProductConfigController().resetSettings();
        }
        this.controllerManager.setCTProductConfigController(CTProductConfigFactory.getInstance(this.context, this.deviceInfo, this.config, this.analyticsManager, this.coreMetaData, this.callbackManager));
        this.config.getLogger().verbose(this.config.getAccountId(), "Product Config reset");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetVariables() {
        if (this.controllerManager.getCtVariables() != null) {
            this.controllerManager.getCtVariables().clearUserContent();
        }
    }
}
