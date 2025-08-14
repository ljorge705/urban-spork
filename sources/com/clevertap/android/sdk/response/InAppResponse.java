package com.clevertap.android.sdk.response;

import android.content.Context;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.ControllerManager;
import com.clevertap.android.sdk.CoreMetaData;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.inapp.TriggerManager;
import com.clevertap.android.sdk.inapp.customtemplates.TemplatesManager;
import com.clevertap.android.sdk.inapp.data.CtCacheType;
import com.clevertap.android.sdk.inapp.data.InAppResponseAdapter;
import com.clevertap.android.sdk.inapp.images.repo.FileResourcesRepoFactory;
import com.clevertap.android.sdk.inapp.images.repo.FileResourcesRepoImpl;
import com.clevertap.android.sdk.inapp.store.preference.FileStore;
import com.clevertap.android.sdk.inapp.store.preference.ImpressionStore;
import com.clevertap.android.sdk.inapp.store.preference.InAppAssetsStore;
import com.clevertap.android.sdk.inapp.store.preference.InAppStore;
import com.clevertap.android.sdk.inapp.store.preference.LegacyInAppStore;
import com.clevertap.android.sdk.inapp.store.preference.StoreRegistry;
import com.clevertap.android.sdk.task.CTExecutorFactory;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Pair;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class InAppResponse extends CleverTapResponseDecorator {
    private final CleverTapInstanceConfig config;
    private final ControllerManager controllerManager;
    private final CoreMetaData coreMetaData;
    private final boolean isSendTest;
    private final Logger logger;
    private final StoreRegistry storeRegistry;
    private final TemplatesManager templatesManager;
    private final TriggerManager triggerManager;

    public InAppResponse(CleverTapInstanceConfig cleverTapInstanceConfig, ControllerManager controllerManager, boolean z, StoreRegistry storeRegistry, TriggerManager triggerManager, TemplatesManager templatesManager, CoreMetaData coreMetaData) {
        this.config = cleverTapInstanceConfig;
        this.logger = cleverTapInstanceConfig.getLogger();
        this.controllerManager = controllerManager;
        this.isSendTest = z;
        this.storeRegistry = storeRegistry;
        this.triggerManager = triggerManager;
        this.coreMetaData = coreMetaData;
        this.templatesManager = templatesManager;
    }

    @Override // com.clevertap.android.sdk.response.CleverTapResponseDecorator, com.clevertap.android.sdk.response.CleverTapResponse
    public void processResponse(JSONObject jSONObject, String str, Context context) {
        try {
            InAppResponseAdapter inAppResponseAdapter = new InAppResponseAdapter(jSONObject, this.templatesManager);
            ImpressionStore impressionStore = this.storeRegistry.getImpressionStore();
            InAppStore inAppStore = this.storeRegistry.getInAppStore();
            InAppAssetsStore inAppAssetsStore = this.storeRegistry.getInAppAssetsStore();
            FileStore filesStore = this.storeRegistry.getFilesStore();
            LegacyInAppStore legacyInAppStore = this.storeRegistry.getLegacyInAppStore();
            if (impressionStore != null && inAppStore != null && inAppAssetsStore != null && legacyInAppStore != null && filesStore != null) {
                if (this.config.isAnalyticsOnly()) {
                    this.logger.verbose(this.config.getAccountId(), "CleverTap instance is configured to analytics only, not processing inapp messages");
                    return;
                }
                this.logger.verbose(this.config.getAccountId(), "InApp: Processing response");
                int inAppsPerSession = inAppResponseAdapter.getInAppsPerSession();
                int inAppsPerDay = inAppResponseAdapter.getInAppsPerDay();
                if (!this.isSendTest && this.controllerManager.getInAppFCManager() != null) {
                    Logger.v("Updating InAppFC Limits");
                    this.controllerManager.getInAppFCManager().updateLimits(context, inAppsPerDay, inAppsPerSession);
                    this.controllerManager.getInAppFCManager().processResponse(context, jSONObject);
                } else {
                    this.logger.verbose(this.config.getAccountId(), "controllerManager.getInAppFCManager() is NULL, not Updating InAppFC Limits");
                }
                Pair<Boolean, JSONArray> staleInApps = inAppResponseAdapter.getStaleInApps();
                if (staleInApps.getFirst().booleanValue()) {
                    clearStaleInAppCache(staleInApps.getSecond(), impressionStore, this.triggerManager);
                }
                Pair<Boolean, JSONArray> legacyInApps = inAppResponseAdapter.getLegacyInApps();
                if (legacyInApps.getFirst().booleanValue()) {
                    displayInApp(legacyInApps.getSecond());
                }
                Pair<Boolean, JSONArray> appLaunchServerSideInApps = inAppResponseAdapter.getAppLaunchServerSideInApps();
                if (appLaunchServerSideInApps.getFirst().booleanValue()) {
                    handleAppLaunchServerSide(appLaunchServerSideInApps.getSecond());
                }
                Pair<Boolean, JSONArray> clientSideInApps = inAppResponseAdapter.getClientSideInApps();
                if (clientSideInApps.getFirst().booleanValue()) {
                    inAppStore.storeClientSideInApps(clientSideInApps.getSecond());
                }
                Pair<Boolean, JSONArray> serverSideInApps = inAppResponseAdapter.getServerSideInApps();
                if (serverSideInApps.getFirst().booleanValue()) {
                    inAppStore.storeServerSideInAppsMetaData(serverSideInApps.getSecond());
                }
                List<Pair<String, CtCacheType>> preloadAssetsMeta = inAppResponseAdapter.getPreloadAssetsMeta();
                FileResourcesRepoImpl fileResourcesRepoImplCreateFileResourcesRepo = FileResourcesRepoFactory.createFileResourcesRepo(context, this.logger, this.storeRegistry);
                if (!preloadAssetsMeta.isEmpty()) {
                    fileResourcesRepoImplCreateFileResourcesRepo.preloadFilesAndCache(preloadAssetsMeta);
                }
                if (this.isFullResponse) {
                    this.logger.verbose(this.config.getAccountId(), "Handling cache eviction");
                    fileResourcesRepoImplCreateFileResourcesRepo.cleanupStaleFiles(inAppResponseAdapter.getPreloadAssets());
                } else {
                    this.logger.verbose(this.config.getAccountId(), "Ignoring cache eviction");
                }
                String inAppMode = inAppResponseAdapter.getInAppMode();
                if (inAppMode.isEmpty()) {
                    return;
                }
                inAppStore.setMode(inAppMode);
                return;
            }
            this.logger.verbose(this.config.getAccountId(), "Stores are not initialised, ignoring inapps!!!!");
        } catch (Throwable th) {
            Logger.v("InAppManager: Failed to parse response", th);
        }
    }

    private void clearStaleInAppCache(JSONArray jSONArray, ImpressionStore impressionStore, TriggerManager triggerManager) {
        for (int i = 0; i < jSONArray.length(); i++) {
            String strOptString = jSONArray.optString(i);
            impressionStore.clear(strOptString);
            triggerManager.removeTriggers(strOptString);
        }
    }

    private void handleAppLaunchServerSide(JSONArray jSONArray) {
        try {
            this.controllerManager.getInAppController().onAppLaunchServerSideInAppsResponse(jSONArray, this.coreMetaData.getLocationFromUser());
        } catch (Throwable th) {
            this.logger.verbose(this.config.getAccountId(), "InAppManager: Malformed AppLaunched ServerSide inApps");
            this.logger.verbose(this.config.getAccountId(), "InAppManager: Reason: " + th.getMessage(), th);
        }
    }

    private void displayInApp(final JSONArray jSONArray) {
        CTExecutorFactory.executors(this.config).postAsyncSafelyTask(Constants.TAG_FEATURE_IN_APPS).execute("InAppResponse#processResponse", new Callable<Void>() { // from class: com.clevertap.android.sdk.response.InAppResponse.1
            @Override // java.util.concurrent.Callable
            public Void call() {
                InAppResponse.this.controllerManager.getInAppController().addInAppNotificationsToQueue(jSONArray);
                return null;
            }
        });
    }
}
