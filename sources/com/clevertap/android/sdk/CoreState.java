package com.clevertap.android.sdk;

import android.content.Context;
import com.clevertap.android.sdk.cryption.CryptHandler;
import com.clevertap.android.sdk.db.BaseDatabaseManager;
import com.clevertap.android.sdk.events.BaseEventQueueManager;
import com.clevertap.android.sdk.events.EventMediator;
import com.clevertap.android.sdk.inapp.ImpressionManager;
import com.clevertap.android.sdk.inapp.InAppController;
import com.clevertap.android.sdk.inapp.customtemplates.TemplatesManager;
import com.clevertap.android.sdk.inapp.evaluation.EvaluationManager;
import com.clevertap.android.sdk.inapp.store.preference.StoreRegistry;
import com.clevertap.android.sdk.login.LoginController;
import com.clevertap.android.sdk.network.NetworkManager;
import com.clevertap.android.sdk.product_config.CTProductConfigController;
import com.clevertap.android.sdk.product_config.CTProductConfigFactory;
import com.clevertap.android.sdk.pushnotification.PushProviders;
import com.clevertap.android.sdk.task.MainLooperHandler;
import com.clevertap.android.sdk.validation.ValidationResultStack;
import com.clevertap.android.sdk.variables.CTVariables;
import com.clevertap.android.sdk.variables.Parser;
import com.clevertap.android.sdk.variables.VarCache;

/* loaded from: classes5.dex */
public class CoreState {
    private ActivityLifeCycleManager activityLifeCycleManager;
    private AnalyticsManager analyticsManager;
    private BaseEventQueueManager baseEventQueueManager;
    private BaseLocationManager baseLocationManager;
    private BaseCallbackManager callbackManager;
    private CleverTapInstanceConfig config;
    private ControllerManager controllerManager;
    private CoreMetaData coreMetaData;
    private CryptHandler cryptHandler;
    private CTLockManager ctLockManager;
    private CTVariables ctVariables;
    private BaseDatabaseManager databaseManager;
    private DeviceInfo deviceInfo;
    private EvaluationManager evaluationManager;
    private EventMediator eventMediator;
    private ImpressionManager impressionManager;
    private InAppController inAppController;
    private LocalDataStore localDataStore;
    private LoginController loginController;
    private MainLooperHandler mainLooperHandler;
    private NetworkManager networkManager;
    private Parser parser;
    private ProfileValueHandler profileValueHandler;
    private PushProviders pushProviders;
    private SessionManager sessionManager;
    private StoreRegistry storeRegistry;
    private TemplatesManager templatesManager;
    private ValidationResultStack validationResultStack;
    private VarCache varCache;

    public ActivityLifeCycleManager getActivityLifeCycleManager() {
        return this.activityLifeCycleManager;
    }

    public AnalyticsManager getAnalyticsManager() {
        return this.analyticsManager;
    }

    public BaseEventQueueManager getBaseEventQueueManager() {
        return this.baseEventQueueManager;
    }

    public CTLockManager getCTLockManager() {
        return this.ctLockManager;
    }

    public CTVariables getCTVariables() {
        return this.ctVariables;
    }

    public BaseCallbackManager getCallbackManager() {
        return this.callbackManager;
    }

    public CleverTapInstanceConfig getConfig() {
        return this.config;
    }

    public ControllerManager getControllerManager() {
        return this.controllerManager;
    }

    public CoreMetaData getCoreMetaData() {
        return this.coreMetaData;
    }

    public CryptHandler getCryptHandler() {
        return this.cryptHandler;
    }

    public BaseDatabaseManager getDatabaseManager() {
        return this.databaseManager;
    }

    public DeviceInfo getDeviceInfo() {
        return this.deviceInfo;
    }

    public EvaluationManager getEvaluationManager() {
        return this.evaluationManager;
    }

    public EventMediator getEventMediator() {
        return this.eventMediator;
    }

    public ImpressionManager getImpressionManager() {
        return this.impressionManager;
    }

    public InAppController getInAppController() {
        return this.inAppController;
    }

    public LocalDataStore getLocalDataStore() {
        return this.localDataStore;
    }

    BaseLocationManager getLocationManager() {
        return this.baseLocationManager;
    }

    public LoginController getLoginController() {
        return this.loginController;
    }

    public MainLooperHandler getMainLooperHandler() {
        return this.mainLooperHandler;
    }

    public NetworkManager getNetworkManager() {
        return this.networkManager;
    }

    public Parser getParser() {
        return this.parser;
    }

    public ProfileValueHandler getProfileValueHandler() {
        return this.profileValueHandler;
    }

    public PushProviders getPushProviders() {
        return this.pushProviders;
    }

    public SessionManager getSessionManager() {
        return this.sessionManager;
    }

    public StoreRegistry getStoreRegistry() {
        return this.storeRegistry;
    }

    public TemplatesManager getTemplatesManager() {
        return this.templatesManager;
    }

    public ValidationResultStack getValidationResultStack() {
        return this.validationResultStack;
    }

    public VarCache getVarCache() {
        return this.varCache;
    }

    public void setActivityLifeCycleManager(ActivityLifeCycleManager activityLifeCycleManager) {
        this.activityLifeCycleManager = activityLifeCycleManager;
    }

    public void setAnalyticsManager(AnalyticsManager analyticsManager) {
        this.analyticsManager = analyticsManager;
    }

    void setBaseEventQueueManager(BaseEventQueueManager baseEventQueueManager) {
        this.baseEventQueueManager = baseEventQueueManager;
    }

    public void setCTLockManager(CTLockManager cTLockManager) {
        this.ctLockManager = cTLockManager;
    }

    public void setCTVariables(CTVariables cTVariables) {
        this.ctVariables = cTVariables;
    }

    void setCallbackManager(BaseCallbackManager baseCallbackManager) {
        this.callbackManager = baseCallbackManager;
    }

    public void setConfig(CleverTapInstanceConfig cleverTapInstanceConfig) {
        this.config = cleverTapInstanceConfig;
    }

    public void setControllerManager(ControllerManager controllerManager) {
        this.controllerManager = controllerManager;
    }

    void setCoreMetaData(CoreMetaData coreMetaData) {
        this.coreMetaData = coreMetaData;
    }

    public void setCryptHandler(CryptHandler cryptHandler) {
        this.cryptHandler = cryptHandler;
    }

    void setDatabaseManager(BaseDatabaseManager baseDatabaseManager) {
        this.databaseManager = baseDatabaseManager;
    }

    public void setDeviceInfo(DeviceInfo deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public void setEvaluationManager(EvaluationManager evaluationManager) {
        this.evaluationManager = evaluationManager;
    }

    public void setEventMediator(EventMediator eventMediator) {
        this.eventMediator = eventMediator;
    }

    public void setImpressionManager(ImpressionManager impressionManager) {
        this.impressionManager = impressionManager;
    }

    public void setInAppController(InAppController inAppController) {
        this.inAppController = inAppController;
    }

    public void setLocalDataStore(LocalDataStore localDataStore) {
        this.localDataStore = localDataStore;
    }

    void setLocationManager(BaseLocationManager baseLocationManager) {
        this.baseLocationManager = baseLocationManager;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    public void setMainLooperHandler(MainLooperHandler mainLooperHandler) {
        this.mainLooperHandler = mainLooperHandler;
    }

    void setNetworkManager(NetworkManager networkManager) {
        this.networkManager = networkManager;
    }

    public void setParser(Parser parser) {
        this.parser = parser;
    }

    public void setProfileValueHandler(ProfileValueHandler profileValueHandler) {
        this.profileValueHandler = profileValueHandler;
    }

    public void setPushProviders(PushProviders pushProviders) {
        this.pushProviders = pushProviders;
    }

    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    public void setStoreRegistry(StoreRegistry storeRegistry) {
        this.storeRegistry = storeRegistry;
    }

    public void setTemplatesManager(TemplatesManager templatesManager) {
        this.templatesManager = templatesManager;
    }

    public void setValidationResultStack(ValidationResultStack validationResultStack) {
        this.validationResultStack = validationResultStack;
    }

    public void setVarCache(VarCache varCache) {
        this.varCache = varCache;
    }

    @Deprecated
    public CTProductConfigController getCtProductConfigController(Context context) {
        initProductConfig(context);
        return getControllerManager().getCTProductConfigController();
    }

    @Deprecated
    private void initProductConfig(Context context) {
        if (getConfig().isAnalyticsOnly()) {
            getConfig().getLogger().debug(getConfig().getAccountId(), "Product Config is not enabled for this instance");
        } else if (getControllerManager().getCTProductConfigController() == null) {
            getConfig().getLogger().verbose(this.config.getAccountId() + ":async_deviceID", "Initializing Product Config with device Id = " + getDeviceInfo().getDeviceID());
            getControllerManager().setCTProductConfigController(CTProductConfigFactory.getInstance(context, getDeviceInfo(), getConfig(), this.analyticsManager, this.coreMetaData, this.callbackManager));
        }
    }
}
