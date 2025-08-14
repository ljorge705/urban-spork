package com.clevertap.android.sdk;

import android.content.Context;
import com.clevertap.android.sdk.cryption.CryptHandler;
import com.clevertap.android.sdk.cryption.CryptUtils;
import com.clevertap.android.sdk.db.DBManager;
import com.clevertap.android.sdk.events.EventMediator;
import com.clevertap.android.sdk.events.EventQueueManager;
import com.clevertap.android.sdk.featureFlags.CTFeatureFlagsFactory;
import com.clevertap.android.sdk.inapp.ImpressionManager;
import com.clevertap.android.sdk.inapp.InAppController;
import com.clevertap.android.sdk.inapp.InAppQueue;
import com.clevertap.android.sdk.inapp.TriggerManager;
import com.clevertap.android.sdk.inapp.customtemplates.TemplatesManager;
import com.clevertap.android.sdk.inapp.evaluation.EvaluationManager;
import com.clevertap.android.sdk.inapp.evaluation.LimitsMatcher;
import com.clevertap.android.sdk.inapp.evaluation.TriggersMatcher;
import com.clevertap.android.sdk.inapp.images.FileResourceProvider;
import com.clevertap.android.sdk.inapp.images.repo.FileResourcesRepoFactory;
import com.clevertap.android.sdk.inapp.images.repo.FileResourcesRepoImpl;
import com.clevertap.android.sdk.inapp.store.preference.ImpressionStore;
import com.clevertap.android.sdk.inapp.store.preference.InAppStore;
import com.clevertap.android.sdk.inapp.store.preference.StoreRegistry;
import com.clevertap.android.sdk.login.LoginController;
import com.clevertap.android.sdk.network.AppLaunchListener;
import com.clevertap.android.sdk.network.CompositeBatchListener;
import com.clevertap.android.sdk.network.FetchInAppListener;
import com.clevertap.android.sdk.network.NetworkManager;
import com.clevertap.android.sdk.network.api.CtApiWrapper;
import com.clevertap.android.sdk.pushnotification.PushProviders;
import com.clevertap.android.sdk.pushnotification.work.CTWorkManager;
import com.clevertap.android.sdk.response.InAppResponse;
import com.clevertap.android.sdk.task.CTExecutorFactory;
import com.clevertap.android.sdk.task.MainLooperHandler;
import com.clevertap.android.sdk.utils.Clock;
import com.clevertap.android.sdk.validation.ValidationResultStack;
import com.clevertap.android.sdk.validation.Validator;
import com.clevertap.android.sdk.variables.CTVariables;
import com.clevertap.android.sdk.variables.Parser;
import com.clevertap.android.sdk.variables.VarCache;
import java.util.concurrent.Callable;

/* loaded from: classes5.dex */
class CleverTapFactory {
    CleverTapFactory() {
    }

    static CoreState getCoreState(final Context context, CleverTapInstanceConfig cleverTapInstanceConfig, String str) throws ClassNotFoundException {
        final CoreState coreState = new CoreState();
        TemplatesManager templatesManagerCreateInstance = TemplatesManager.createInstance(cleverTapInstanceConfig);
        coreState.setTemplatesManager(templatesManagerCreateInstance);
        final StoreProvider storeProvider = StoreProvider.getInstance();
        String accountId = cleverTapInstanceConfig.getAccountId();
        final StoreRegistry storeRegistry = new StoreRegistry(null, null, storeProvider.provideLegacyInAppStore(context, accountId), storeProvider.provideInAppAssetsStore(context, accountId), storeProvider.provideFileStore(context, accountId));
        coreState.setStoreRegistry(storeRegistry);
        CoreMetaData coreMetaData = new CoreMetaData();
        coreState.setCoreMetaData(coreMetaData);
        Validator validator = new Validator();
        ValidationResultStack validationResultStack = new ValidationResultStack();
        coreState.setValidationResultStack(validationResultStack);
        CTLockManager cTLockManager = new CTLockManager();
        coreState.setCTLockManager(cTLockManager);
        MainLooperHandler mainLooperHandler = new MainLooperHandler();
        coreState.setMainLooperHandler(mainLooperHandler);
        final CleverTapInstanceConfig cleverTapInstanceConfig2 = new CleverTapInstanceConfig(cleverTapInstanceConfig);
        coreState.setConfig(cleverTapInstanceConfig2);
        final DBManager dBManager = new DBManager(cleverTapInstanceConfig2, cTLockManager);
        coreState.setDatabaseManager(dBManager);
        final CryptHandler cryptHandler = new CryptHandler(cleverTapInstanceConfig2.getEncryptionLevel(), CryptHandler.EncryptionAlgorithm.AES, cleverTapInstanceConfig2.getAccountId());
        coreState.setCryptHandler(cryptHandler);
        CTExecutorFactory.executors(cleverTapInstanceConfig2).postAsyncSafelyTask().execute("migratingEncryptionLevel", new Callable() { // from class: com.clevertap.android.sdk.CleverTapFactory$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return CleverTapFactory.lambda$getCoreState$0(context, cleverTapInstanceConfig2, cryptHandler, dBManager);
            }
        });
        final DeviceInfo deviceInfo = new DeviceInfo(context, cleverTapInstanceConfig2, str, coreMetaData);
        coreState.setDeviceInfo(deviceInfo);
        deviceInfo.onInitDeviceInfo(str);
        LocalDataStore localDataStore = new LocalDataStore(context, cleverTapInstanceConfig2, cryptHandler, deviceInfo, dBManager);
        coreState.setLocalDataStore(localDataStore);
        ProfileValueHandler profileValueHandler = new ProfileValueHandler(validator, validationResultStack);
        coreState.setProfileValueHandler(profileValueHandler);
        EventMediator eventMediator = new EventMediator(context, cleverTapInstanceConfig2, coreMetaData, localDataStore, profileValueHandler);
        coreState.setEventMediator(eventMediator);
        CTPreferenceCache.getInstance(context, cleverTapInstanceConfig2);
        final BaseCallbackManager callbackManager = new CallbackManager(cleverTapInstanceConfig2, deviceInfo);
        coreState.setCallbackManager(callbackManager);
        SessionManager sessionManager = new SessionManager(cleverTapInstanceConfig2, coreMetaData, validator, localDataStore);
        coreState.setSessionManager(sessionManager);
        final ControllerManager controllerManager = new ControllerManager(context, cleverTapInstanceConfig2, cTLockManager, callbackManager, deviceInfo, dBManager);
        coreState.setControllerManager(controllerManager);
        TriggersMatcher triggersMatcher = new TriggersMatcher(localDataStore);
        TriggerManager triggerManager = new TriggerManager(context, cleverTapInstanceConfig2.getAccountId(), deviceInfo);
        final ImpressionManager impressionManager = new ImpressionManager(storeRegistry);
        LimitsMatcher limitsMatcher = new LimitsMatcher(impressionManager, triggerManager);
        coreState.setImpressionManager(impressionManager);
        final EvaluationManager evaluationManager = new EvaluationManager(triggersMatcher, triggerManager, limitsMatcher, storeRegistry, templatesManagerCreateInstance);
        coreState.setEvaluationManager(evaluationManager);
        CTExecutorFactory.executors(cleverTapInstanceConfig2).ioTask().execute("initStores", new Callable() { // from class: com.clevertap.android.sdk.CleverTapFactory$$ExternalSyntheticLambda1
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return CleverTapFactory.lambda$getCoreState$1(coreState, storeRegistry, storeProvider, context, cryptHandler, deviceInfo, cleverTapInstanceConfig2, evaluationManager, callbackManager);
            }
        });
        CTExecutorFactory.executors(cleverTapInstanceConfig2).ioTask().execute("initFCManager", new Callable<Void>() { // from class: com.clevertap.android.sdk.CleverTapFactory.1
            @Override // java.util.concurrent.Callable
            public Void call() throws Exception {
                if (coreState.getDeviceInfo() == null || coreState.getDeviceInfo().getDeviceID() == null || controllerManager.getInAppFCManager() != null) {
                    return null;
                }
                coreState.getConfig().getLogger().verbose(cleverTapInstanceConfig2.getAccountId() + ":async_deviceID", "Initializing InAppFC with device Id = " + coreState.getDeviceInfo().getDeviceID());
                controllerManager.setInAppFCManager(new InAppFCManager(context, cleverTapInstanceConfig2, coreState.getDeviceInfo().getDeviceID(), storeRegistry, impressionManager));
                return null;
            }
        });
        FileResourcesRepoImpl fileResourcesRepoImplCreateFileResourcesRepo = FileResourcesRepoFactory.createFileResourcesRepo(context, cleverTapInstanceConfig2.getLogger(), storeRegistry);
        FileResourceProvider fileResourceProvider = new FileResourceProvider(context, cleverTapInstanceConfig2.getLogger());
        VarCache varCache = new VarCache(cleverTapInstanceConfig2, context, fileResourcesRepoImplCreateFileResourcesRepo, fileResourceProvider);
        coreState.setVarCache(varCache);
        final CTVariables cTVariables = new CTVariables(varCache);
        coreState.setCTVariables(cTVariables);
        coreState.getControllerManager().setCtVariables(cTVariables);
        coreState.setParser(new Parser(cTVariables));
        CTExecutorFactory.executors(cleverTapInstanceConfig2).ioTask().execute("initCTVariables", new Callable() { // from class: com.clevertap.android.sdk.CleverTapFactory$$ExternalSyntheticLambda2
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return CleverTapFactory.lambda$getCoreState$2(cTVariables);
            }
        });
        NetworkManager networkManager = new NetworkManager(context, cleverTapInstanceConfig2, deviceInfo, coreMetaData, validationResultStack, controllerManager, dBManager, callbackManager, cTLockManager, validator, new InAppResponse(cleverTapInstanceConfig2, controllerManager, false, storeRegistry, triggerManager, templatesManagerCreateInstance, coreMetaData), new CtApiWrapper(context, cleverTapInstanceConfig2, deviceInfo));
        coreState.setNetworkManager(networkManager);
        EventQueueManager eventQueueManager = new EventQueueManager(dBManager, context, cleverTapInstanceConfig2, eventMediator, sessionManager, callbackManager, mainLooperHandler, deviceInfo, validationResultStack, networkManager, coreMetaData, cTLockManager, localDataStore, controllerManager, cryptHandler);
        coreState.setBaseEventQueueManager(eventQueueManager);
        final AnalyticsManager analyticsManager = new AnalyticsManager(context, cleverTapInstanceConfig2, eventQueueManager, validator, validationResultStack, coreMetaData, deviceInfo, callbackManager, controllerManager, cTLockManager, new InAppResponse(cleverTapInstanceConfig2, controllerManager, true, storeRegistry, triggerManager, templatesManagerCreateInstance, coreMetaData), Clock.INSTANCE.getSYSTEM());
        coreState.setAnalyticsManager(analyticsManager);
        networkManager.addNetworkHeadersListener(evaluationManager);
        InAppController inAppController = new InAppController(context, cleverTapInstanceConfig2, mainLooperHandler, controllerManager, callbackManager, analyticsManager, coreMetaData, deviceInfo, new InAppQueue(cleverTapInstanceConfig2, storeRegistry), evaluationManager, fileResourceProvider, templatesManagerCreateInstance, storeRegistry);
        coreState.setInAppController(inAppController);
        coreState.getControllerManager().setInAppController(inAppController);
        AppLaunchListener appLaunchListener = new AppLaunchListener();
        appLaunchListener.addListener(inAppController.onAppLaunchEventSent);
        CompositeBatchListener compositeBatchListener = new CompositeBatchListener();
        compositeBatchListener.addListener(appLaunchListener);
        compositeBatchListener.addListener(new FetchInAppListener(callbackManager));
        callbackManager.setBatchListener(compositeBatchListener);
        CTExecutorFactory.executors(cleverTapInstanceConfig2).ioTask().execute("initFeatureFlags", new Callable<Void>() { // from class: com.clevertap.android.sdk.CleverTapFactory.2
            @Override // java.util.concurrent.Callable
            public Void call() throws Exception {
                CleverTapFactory.initFeatureFlags(context, controllerManager, cleverTapInstanceConfig2, deviceInfo, callbackManager, analyticsManager);
                return null;
            }
        });
        coreState.setLocationManager(new LocationManager(context, cleverTapInstanceConfig2, coreMetaData, eventQueueManager));
        PushProviders pushProvidersLoad = PushProviders.load(context, cleverTapInstanceConfig2, dBManager, validationResultStack, analyticsManager, controllerManager, new CTWorkManager(context, cleverTapInstanceConfig2));
        coreState.setPushProviders(pushProvidersLoad);
        coreState.setActivityLifeCycleManager(new ActivityLifeCycleManager(context, cleverTapInstanceConfig2, analyticsManager, coreMetaData, sessionManager, pushProvidersLoad, callbackManager, inAppController, eventQueueManager));
        coreState.setLoginController(new LoginController(context, cleverTapInstanceConfig2, deviceInfo, validationResultStack, eventQueueManager, analyticsManager, coreMetaData, controllerManager, sessionManager, localDataStore, callbackManager, dBManager, cTLockManager, cryptHandler));
        return coreState;
    }

    static /* synthetic */ Void lambda$getCoreState$0(Context context, CleverTapInstanceConfig cleverTapInstanceConfig, CryptHandler cryptHandler, DBManager dBManager) throws Exception {
        CryptUtils.migrateEncryptionLevel(context, cleverTapInstanceConfig, cryptHandler, dBManager.loadDBAdapter(context));
        return null;
    }

    static /* synthetic */ Void lambda$getCoreState$1(CoreState coreState, StoreRegistry storeRegistry, StoreProvider storeProvider, Context context, CryptHandler cryptHandler, DeviceInfo deviceInfo, CleverTapInstanceConfig cleverTapInstanceConfig, EvaluationManager evaluationManager, BaseCallbackManager baseCallbackManager) throws Exception {
        if (coreState.getDeviceInfo() == null || coreState.getDeviceInfo().getDeviceID() == null) {
            return null;
        }
        if (storeRegistry.getInAppStore() == null) {
            InAppStore inAppStoreProvideInAppStore = storeProvider.provideInAppStore(context, cryptHandler, deviceInfo.getDeviceID(), cleverTapInstanceConfig.getAccountId());
            storeRegistry.setInAppStore(inAppStoreProvideInAppStore);
            evaluationManager.loadSuppressedCSAndEvaluatedSSInAppsIds();
            baseCallbackManager.addChangeUserCallback(inAppStoreProvideInAppStore);
        }
        if (storeRegistry.getImpressionStore() != null) {
            return null;
        }
        ImpressionStore impressionStoreProvideImpressionStore = storeProvider.provideImpressionStore(context, deviceInfo.getDeviceID(), cleverTapInstanceConfig.getAccountId());
        storeRegistry.setImpressionStore(impressionStoreProvideImpressionStore);
        baseCallbackManager.addChangeUserCallback(impressionStoreProvideImpressionStore);
        return null;
    }

    static /* synthetic */ Void lambda$getCoreState$2(CTVariables cTVariables) throws Exception {
        cTVariables.init();
        return null;
    }

    static void initFeatureFlags(Context context, ControllerManager controllerManager, CleverTapInstanceConfig cleverTapInstanceConfig, DeviceInfo deviceInfo, BaseCallbackManager baseCallbackManager, AnalyticsManager analyticsManager) {
        cleverTapInstanceConfig.getLogger().verbose(cleverTapInstanceConfig.getAccountId() + ":async_deviceID", "Initializing Feature Flags with device Id = " + deviceInfo.getDeviceID());
        if (cleverTapInstanceConfig.isAnalyticsOnly()) {
            cleverTapInstanceConfig.getLogger().debug(cleverTapInstanceConfig.getAccountId(), "Feature Flag is not enabled for this instance");
        } else {
            controllerManager.setCTFeatureFlagsController(CTFeatureFlagsFactory.getInstance(context, deviceInfo.getDeviceID(), cleverTapInstanceConfig, baseCallbackManager, analyticsManager));
            cleverTapInstanceConfig.getLogger().verbose(cleverTapInstanceConfig.getAccountId() + ":async_deviceID", "Feature Flags initialized");
        }
    }
}
