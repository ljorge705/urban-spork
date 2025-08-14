package com.clevertap.android.sdk.product_config;

import android.content.Context;
import android.text.TextUtils;
import com.clevertap.android.sdk.BaseAnalyticsManager;
import com.clevertap.android.sdk.BaseCallbackManager;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.CoreMetaData;
import com.clevertap.android.sdk.task.CTExecutorFactory;
import com.clevertap.android.sdk.task.OnSuccessListener;
import com.clevertap.android.sdk.utils.FileUtils;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Deprecated
/* loaded from: classes5.dex */
public class CTProductConfigController {
    private final BaseAnalyticsManager analyticsManager;
    private final BaseCallbackManager callbackManager;
    private final CleverTapInstanceConfig config;
    private final Context context;
    private final CoreMetaData coreMetaData;
    final FileUtils fileUtils;

    @Deprecated
    private final ProductConfigSettings settings;

    @Deprecated
    final Map<String, String> activatedConfigs = Collections.synchronizedMap(new HashMap());

    @Deprecated
    final Map<String, String> defaultConfigs = Collections.synchronizedMap(new HashMap());
    AtomicBoolean isInitialized = new AtomicBoolean(false);
    private final AtomicBoolean isFetchAndActivating = new AtomicBoolean(false);
    private final Map<String, String> waitingTobeActivatedConfig = Collections.synchronizedMap(new HashMap());

    private enum PROCESSING_STATE {
        INIT,
        FETCHED,
        ACTIVATED
    }

    BaseAnalyticsManager getAnalyticsManager() {
        return this.analyticsManager;
    }

    BaseCallbackManager getCallbackManager() {
        return this.callbackManager;
    }

    CleverTapInstanceConfig getConfig() {
        return this.config;
    }

    CoreMetaData getCoreMetaData() {
        return this.coreMetaData;
    }

    @Deprecated
    public ProductConfigSettings getSettings() {
        return this.settings;
    }

    @Deprecated
    CTProductConfigController(Context context, CleverTapInstanceConfig cleverTapInstanceConfig, BaseAnalyticsManager baseAnalyticsManager, CoreMetaData coreMetaData, BaseCallbackManager baseCallbackManager, ProductConfigSettings productConfigSettings, FileUtils fileUtils) {
        this.context = context;
        this.config = cleverTapInstanceConfig;
        this.coreMetaData = coreMetaData;
        this.callbackManager = baseCallbackManager;
        this.analyticsManager = baseAnalyticsManager;
        this.settings = productConfigSettings;
        this.fileUtils = fileUtils;
        initAsync();
    }

    @Deprecated
    public void activate() {
        if (TextUtils.isEmpty(this.settings.getGuid())) {
            return;
        }
        CTExecutorFactory.executors(this.config).ioTask().addOnSuccessListener(new OnSuccessListener<Void>() { // from class: com.clevertap.android.sdk.product_config.CTProductConfigController.2
            @Override // com.clevertap.android.sdk.task.OnSuccessListener
            public void onSuccess(Void r2) {
                CTProductConfigController.this.sendCallback(PROCESSING_STATE.ACTIVATED);
            }
        }).execute("activateProductConfigs", new Callable<Void>() { // from class: com.clevertap.android.sdk.product_config.CTProductConfigController.1
            @Override // java.util.concurrent.Callable
            public Void call() {
                synchronized (this) {
                    try {
                        HashMap map = new HashMap();
                        if (!CTProductConfigController.this.waitingTobeActivatedConfig.isEmpty()) {
                            map.putAll(CTProductConfigController.this.waitingTobeActivatedConfig);
                            CTProductConfigController.this.waitingTobeActivatedConfig.clear();
                        } else {
                            CTProductConfigController cTProductConfigController = CTProductConfigController.this;
                            map = cTProductConfigController.getStoredValues(cTProductConfigController.getActivatedFullPath());
                        }
                        CTProductConfigController.this.activatedConfigs.clear();
                        if (!CTProductConfigController.this.defaultConfigs.isEmpty()) {
                            CTProductConfigController.this.activatedConfigs.putAll(CTProductConfigController.this.defaultConfigs);
                        }
                        CTProductConfigController.this.activatedConfigs.putAll(map);
                        CTProductConfigController.this.config.getLogger().verbose(ProductConfigUtil.getLogTag(CTProductConfigController.this.config), "Activated successfully with configs: " + CTProductConfigController.this.activatedConfigs);
                    } catch (Exception e) {
                        e.printStackTrace();
                        CTProductConfigController.this.config.getLogger().verbose(ProductConfigUtil.getLogTag(CTProductConfigController.this.config), "Activate failed: " + e.getLocalizedMessage());
                    }
                }
                return null;
            }
        });
    }

    @Deprecated
    public void fetch() {
        fetch(this.settings.getNextFetchIntervalInSeconds());
    }

    @Deprecated
    public void fetch(long j) {
        if (canRequest(j)) {
            fetchProductConfig();
        }
    }

    @Deprecated
    public void fetchAndActivate() {
        fetch();
        this.isFetchAndActivating.set(true);
    }

    @Deprecated
    public void fetchProductConfig() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("t", 0);
            jSONObject.put(Constants.KEY_EVT_NAME, Constants.WZRK_FETCH);
            jSONObject.put(Constants.KEY_EVT_DATA, jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.analyticsManager.sendFetchEvent(jSONObject);
        this.coreMetaData.setProductConfigRequested(true);
        this.config.getLogger().verbose(this.config.getAccountId(), "Product Config : Fetching product config");
    }

    @Deprecated
    public Boolean getBoolean(String str) {
        if (this.isInitialized.get() && !TextUtils.isEmpty(str)) {
            String str2 = this.activatedConfigs.get(str);
            if (!TextUtils.isEmpty(str2)) {
                return Boolean.valueOf(Boolean.parseBoolean(str2));
            }
        }
        return CTProductConfigConstants.DEFAULT_VALUE_FOR_BOOLEAN;
    }

    @Deprecated
    public Double getDouble(String str) {
        if (this.isInitialized.get() && !TextUtils.isEmpty(str)) {
            try {
                String str2 = this.activatedConfigs.get(str);
                if (!TextUtils.isEmpty(str2)) {
                    return Double.valueOf(Double.parseDouble(str2));
                }
            } catch (Exception e) {
                e.printStackTrace();
                this.config.getLogger().verbose(ProductConfigUtil.getLogTag(this.config), "Error getting Double for Key-" + str + StringUtils.SPACE + e.getLocalizedMessage());
            }
        }
        return CTProductConfigConstants.DEFAULT_VALUE_FOR_DOUBLE;
    }

    @Deprecated
    public long getLastFetchTimeStampInMillis() {
        return this.settings.getLastFetchTimeStampInMillis();
    }

    @Deprecated
    public Long getLong(String str) {
        if (this.isInitialized.get() && !TextUtils.isEmpty(str)) {
            try {
                String str2 = this.activatedConfigs.get(str);
                if (!TextUtils.isEmpty(str2)) {
                    return Long.valueOf(Long.parseLong(str2));
                }
            } catch (Exception e) {
                e.printStackTrace();
                this.config.getLogger().verbose(ProductConfigUtil.getLogTag(this.config), "Error getting Long for Key-" + str + StringUtils.SPACE + e.getLocalizedMessage());
            }
        }
        return CTProductConfigConstants.DEFAULT_VALUE_FOR_LONG;
    }

    @Deprecated
    public String getString(String str) {
        if (!this.isInitialized.get() || TextUtils.isEmpty(str)) {
            return "";
        }
        String str2 = this.activatedConfigs.get(str);
        return !TextUtils.isEmpty(str2) ? str2 : "";
    }

    @Deprecated
    public boolean isInitialized() {
        return this.isInitialized.get();
    }

    @Deprecated
    public void onFetchFailed() {
        this.isFetchAndActivating.compareAndSet(true, false);
        this.config.getLogger().verbose(ProductConfigUtil.getLogTag(this.config), "Fetch Failed");
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0095 A[Catch: all -> 0x0097, DONT_GENERATE, TryCatch #0 {, blocks: (B:8:0x0013, B:10:0x0071, B:13:0x0076, B:14:0x0095), top: B:19:0x0010, inners: #1 }] */
    @java.lang.Deprecated
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onFetchSuccess(org.json.JSONObject r7) {
        /*
            r6 = this;
            java.lang.String r0 = "Fetch file-["
            com.clevertap.android.sdk.product_config.ProductConfigSettings r1 = r6.settings
            java.lang.String r1 = r1.getGuid()
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 == 0) goto Lf
            return
        Lf:
            monitor-enter(r6)
            if (r7 == 0) goto L95
            r1 = 0
            r6.parseFetchedResponse(r7)     // Catch: java.lang.Exception -> L75 java.lang.Throwable -> L97
            com.clevertap.android.sdk.utils.FileUtils r7 = r6.fileUtils     // Catch: java.lang.Exception -> L75 java.lang.Throwable -> L97
            java.lang.String r2 = r6.getProductConfigDirName()     // Catch: java.lang.Exception -> L75 java.lang.Throwable -> L97
            java.lang.String r3 = "activated.json"
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch: java.lang.Exception -> L75 java.lang.Throwable -> L97
            java.util.Map<java.lang.String, java.lang.String> r5 = r6.waitingTobeActivatedConfig     // Catch: java.lang.Exception -> L75 java.lang.Throwable -> L97
            r4.<init>(r5)     // Catch: java.lang.Exception -> L75 java.lang.Throwable -> L97
            r7.writeJsonToFile(r2, r3, r4)     // Catch: java.lang.Exception -> L75 java.lang.Throwable -> L97
            com.clevertap.android.sdk.CleverTapInstanceConfig r7 = r6.config     // Catch: java.lang.Exception -> L75 java.lang.Throwable -> L97
            com.clevertap.android.sdk.Logger r7 = r7.getLogger()     // Catch: java.lang.Exception -> L75 java.lang.Throwable -> L97
            com.clevertap.android.sdk.CleverTapInstanceConfig r2 = r6.config     // Catch: java.lang.Exception -> L75 java.lang.Throwable -> L97
            java.lang.String r2 = com.clevertap.android.sdk.product_config.ProductConfigUtil.getLogTag(r2)     // Catch: java.lang.Exception -> L75 java.lang.Throwable -> L97
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L75 java.lang.Throwable -> L97
            r3.<init>(r0)     // Catch: java.lang.Exception -> L75 java.lang.Throwable -> L97
            java.lang.String r0 = r6.getActivatedFullPath()     // Catch: java.lang.Exception -> L75 java.lang.Throwable -> L97
            java.lang.StringBuilder r0 = r3.append(r0)     // Catch: java.lang.Exception -> L75 java.lang.Throwable -> L97
            java.lang.String r3 = "] write success: "
            java.lang.StringBuilder r0 = r0.append(r3)     // Catch: java.lang.Exception -> L75 java.lang.Throwable -> L97
            java.util.Map<java.lang.String, java.lang.String> r3 = r6.waitingTobeActivatedConfig     // Catch: java.lang.Exception -> L75 java.lang.Throwable -> L97
            java.lang.StringBuilder r0 = r0.append(r3)     // Catch: java.lang.Exception -> L75 java.lang.Throwable -> L97
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Exception -> L75 java.lang.Throwable -> L97
            r7.verbose(r2, r0)     // Catch: java.lang.Exception -> L75 java.lang.Throwable -> L97
            com.clevertap.android.sdk.CleverTapInstanceConfig r7 = r6.config     // Catch: java.lang.Exception -> L75 java.lang.Throwable -> L97
            com.clevertap.android.sdk.task.CTExecutors r7 = com.clevertap.android.sdk.task.CTExecutorFactory.executors(r7)     // Catch: java.lang.Exception -> L75 java.lang.Throwable -> L97
            com.clevertap.android.sdk.task.Task r7 = r7.mainTask()     // Catch: java.lang.Exception -> L75 java.lang.Throwable -> L97
            java.lang.String r0 = "sendPCFetchSuccessCallback"
            com.clevertap.android.sdk.product_config.CTProductConfigController$3 r2 = new com.clevertap.android.sdk.product_config.CTProductConfigController$3     // Catch: java.lang.Exception -> L75 java.lang.Throwable -> L97
            r2.<init>()     // Catch: java.lang.Exception -> L75 java.lang.Throwable -> L97
            r7.execute(r0, r2)     // Catch: java.lang.Exception -> L75 java.lang.Throwable -> L97
            java.util.concurrent.atomic.AtomicBoolean r7 = r6.isFetchAndActivating     // Catch: java.lang.Exception -> L75 java.lang.Throwable -> L97
            boolean r7 = r7.getAndSet(r1)     // Catch: java.lang.Exception -> L75 java.lang.Throwable -> L97
            if (r7 == 0) goto L95
            r6.activate()     // Catch: java.lang.Exception -> L75 java.lang.Throwable -> L97
            goto L95
        L75:
            r7 = move-exception
            r7.printStackTrace()     // Catch: java.lang.Throwable -> L97
            com.clevertap.android.sdk.CleverTapInstanceConfig r7 = r6.config     // Catch: java.lang.Throwable -> L97
            com.clevertap.android.sdk.Logger r7 = r7.getLogger()     // Catch: java.lang.Throwable -> L97
            com.clevertap.android.sdk.CleverTapInstanceConfig r0 = r6.config     // Catch: java.lang.Throwable -> L97
            java.lang.String r0 = com.clevertap.android.sdk.product_config.ProductConfigUtil.getLogTag(r0)     // Catch: java.lang.Throwable -> L97
            java.lang.String r2 = "Product Config: fetch Failed"
            r7.verbose(r0, r2)     // Catch: java.lang.Throwable -> L97
            com.clevertap.android.sdk.product_config.CTProductConfigController$PROCESSING_STATE r7 = com.clevertap.android.sdk.product_config.CTProductConfigController.PROCESSING_STATE.FETCHED     // Catch: java.lang.Throwable -> L97
            r6.sendCallback(r7)     // Catch: java.lang.Throwable -> L97
            java.util.concurrent.atomic.AtomicBoolean r7 = r6.isFetchAndActivating     // Catch: java.lang.Throwable -> L97
            r0 = 1
            r7.compareAndSet(r0, r1)     // Catch: java.lang.Throwable -> L97
        L95:
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L97
            return
        L97:
            r7 = move-exception
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L97
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.clevertap.android.sdk.product_config.CTProductConfigController.onFetchSuccess(org.json.JSONObject):void");
    }

    @Deprecated
    public void reset() {
        this.defaultConfigs.clear();
        this.activatedConfigs.clear();
        this.settings.initDefaults();
        eraseStoredConfigFiles();
    }

    @Deprecated
    public void resetSettings() {
        this.settings.reset(this.fileUtils);
    }

    @Deprecated
    public void setArpValue(JSONObject jSONObject) throws JSONException {
        this.settings.setARPValue(jSONObject);
    }

    @Deprecated
    public void setDefaults(int i) {
        setDefaultsWithXmlParser(i, new DefaultXmlParser());
    }

    @Deprecated
    public void setDefaults(final HashMap<String, Object> map) {
        CTExecutorFactory.executors(this.config).ioTask().addOnSuccessListener(new OnSuccessListener<Void>() { // from class: com.clevertap.android.sdk.product_config.CTProductConfigController.5
            @Override // com.clevertap.android.sdk.task.OnSuccessListener
            public void onSuccess(Void r1) {
                CTProductConfigController.this.initAsync();
            }
        }).execute("ProductConfig#setDefaultsUsingHashMap", new Callable<Void>() { // from class: com.clevertap.android.sdk.product_config.CTProductConfigController.4
            @Override // java.util.concurrent.Callable
            public Void call() {
                synchronized (this) {
                    HashMap map2 = map;
                    if (map2 == null || map2.isEmpty()) {
                        CTProductConfigController.this.config.getLogger().verbose(ProductConfigUtil.getLogTag(CTProductConfigController.this.config), "Product Config: setDefaults Completed with: " + CTProductConfigController.this.defaultConfigs);
                    } else {
                        for (Map.Entry entry : map.entrySet()) {
                            if (entry != null) {
                                String str = (String) entry.getKey();
                                Object value = entry.getValue();
                                try {
                                    if (!TextUtils.isEmpty(str) && ProductConfigUtil.isSupportedDataType(value)) {
                                        CTProductConfigController.this.defaultConfigs.put(str, String.valueOf(value));
                                    }
                                } catch (Exception e) {
                                    CTProductConfigController.this.config.getLogger().verbose(ProductConfigUtil.getLogTag(CTProductConfigController.this.config), "Product Config: setDefaults Failed for Key: " + str + " with Error: " + e.getLocalizedMessage());
                                }
                            }
                        }
                        CTProductConfigController.this.config.getLogger().verbose(ProductConfigUtil.getLogTag(CTProductConfigController.this.config), "Product Config: setDefaults Completed with: " + CTProductConfigController.this.defaultConfigs);
                    }
                }
                return null;
            }
        });
    }

    @Deprecated
    public void setGuidAndInit(String str) {
        if (isInitialized() || TextUtils.isEmpty(str)) {
            return;
        }
        this.settings.setGuid(str);
        initAsync();
    }

    @Deprecated
    public void setMinimumFetchIntervalInSeconds(long j) {
        this.settings.setMinimumFetchIntervalInSeconds(j);
    }

    @Deprecated
    void eraseStoredConfigFiles() {
        CTExecutorFactory.executors(this.config).ioTask().execute("eraseStoredConfigs", new Callable<Void>() { // from class: com.clevertap.android.sdk.product_config.CTProductConfigController.6
            @Override // java.util.concurrent.Callable
            public Void call() {
                synchronized (this) {
                    try {
                        String productConfigDirName = CTProductConfigController.this.getProductConfigDirName();
                        CTProductConfigController.this.fileUtils.deleteDirectory(productConfigDirName);
                        CTProductConfigController.this.config.getLogger().verbose(ProductConfigUtil.getLogTag(CTProductConfigController.this.config), "Reset Deleted Dir: " + productConfigDirName);
                    } catch (Exception e) {
                        e.printStackTrace();
                        CTProductConfigController.this.config.getLogger().verbose(ProductConfigUtil.getLogTag(CTProductConfigController.this.config), "Reset failed: " + e.getLocalizedMessage());
                    }
                }
                return null;
            }
        });
    }

    String getActivatedFullPath() {
        return getProductConfigDirName() + "/activated.json";
    }

    String getProductConfigDirName() {
        return "Product_Config_" + this.config.getAccountId() + "_" + this.settings.getGuid();
    }

    void initAsync() {
        if (TextUtils.isEmpty(this.settings.getGuid())) {
            return;
        }
        CTExecutorFactory.executors(this.config).ioTask().addOnSuccessListener(new OnSuccessListener<Boolean>() { // from class: com.clevertap.android.sdk.product_config.CTProductConfigController.8
            @Override // com.clevertap.android.sdk.task.OnSuccessListener
            public void onSuccess(Boolean bool) {
                CTProductConfigController.this.sendCallback(PROCESSING_STATE.INIT);
            }
        }).execute("ProductConfig#initAsync", new Callable<Boolean>() { // from class: com.clevertap.android.sdk.product_config.CTProductConfigController.7
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Boolean call() {
                synchronized (this) {
                    try {
                        try {
                            if (!CTProductConfigController.this.defaultConfigs.isEmpty()) {
                                CTProductConfigController.this.activatedConfigs.putAll(CTProductConfigController.this.defaultConfigs);
                            }
                            CTProductConfigController cTProductConfigController = CTProductConfigController.this;
                            HashMap storedValues = cTProductConfigController.getStoredValues(cTProductConfigController.getActivatedFullPath());
                            if (!storedValues.isEmpty()) {
                                CTProductConfigController.this.waitingTobeActivatedConfig.putAll(storedValues);
                            }
                            CTProductConfigController.this.config.getLogger().verbose(ProductConfigUtil.getLogTag(CTProductConfigController.this.config), "Loaded configs ready to be applied: " + CTProductConfigController.this.waitingTobeActivatedConfig);
                            CTProductConfigController.this.settings.loadSettings(CTProductConfigController.this.fileUtils);
                            CTProductConfigController.this.isInitialized.set(true);
                        } catch (Exception e) {
                            e.printStackTrace();
                            CTProductConfigController.this.config.getLogger().verbose(ProductConfigUtil.getLogTag(CTProductConfigController.this.config), "InitAsync failed - " + e.getLocalizedMessage());
                            return false;
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                return true;
            }
        });
    }

    boolean isFetchAndActivating() {
        return this.isFetchAndActivating.get();
    }

    void setDefaultsWithXmlParser(final int i, final DefaultXmlParser defaultXmlParser) {
        CTExecutorFactory.executors(this.config).ioTask().addOnSuccessListener(new OnSuccessListener<Void>() { // from class: com.clevertap.android.sdk.product_config.CTProductConfigController.10
            @Override // com.clevertap.android.sdk.task.OnSuccessListener
            public void onSuccess(Void r1) {
                CTProductConfigController.this.initAsync();
            }
        }).execute("PCController#setDefaultsWithXmlParser", new Callable<Void>() { // from class: com.clevertap.android.sdk.product_config.CTProductConfigController.9
            @Override // java.util.concurrent.Callable
            public Void call() {
                synchronized (this) {
                    CTProductConfigController.this.defaultConfigs.putAll(defaultXmlParser.getDefaultsFromXml(CTProductConfigController.this.context, i));
                    CTProductConfigController.this.config.getLogger().verbose(ProductConfigUtil.getLogTag(CTProductConfigController.this.config), "Product Config: setDefaults Completed with: " + CTProductConfigController.this.defaultConfigs);
                }
                return null;
            }
        });
    }

    private boolean canRequest(long j) {
        if (!(!TextUtils.isEmpty(this.settings.getGuid()))) {
            this.config.getLogger().verbose(ProductConfigUtil.getLogTag(this.config), "Product Config: Throttled due to empty Guid");
            return false;
        }
        long lastFetchTimeStampInMillis = this.settings.getLastFetchTimeStampInMillis();
        long jCurrentTimeMillis = (System.currentTimeMillis() - lastFetchTimeStampInMillis) - TimeUnit.SECONDS.toMillis(j);
        if (jCurrentTimeMillis > 0) {
            return true;
        }
        this.config.getLogger().verbose(ProductConfigUtil.getLogTag(this.config), "Throttled since you made frequent request- [Last Request Time-" + new Date(lastFetchTimeStampInMillis) + "], Try again in " + ((-jCurrentTimeMillis) / 1000) + " seconds");
        return false;
    }

    private HashMap<String, String> convertServerJsonToMap(JSONObject jSONObject) throws JSONException {
        HashMap<String, String> map = new HashMap<>();
        try {
            JSONArray jSONArray = jSONObject.getJSONArray(Constants.KEY_KV);
            if (jSONArray != null && jSONArray.length() > 0) {
                for (int i = 0; i < jSONArray.length(); i++) {
                    try {
                        JSONObject jSONObject2 = (JSONObject) jSONArray.get(i);
                        if (jSONObject2 != null) {
                            String string = jSONObject2.getString("n");
                            String string2 = jSONObject2.getString(CTProductConfigConstants.PRODUCT_CONFIG_JSON_KEY_FOR_VALUE);
                            if (!TextUtils.isEmpty(string)) {
                                map.put(string, string2);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        this.config.getLogger().verbose(ProductConfigUtil.getLogTag(this.config), "ConvertServerJsonToMap failed: " + e.getLocalizedMessage());
                    }
                }
            }
            return map;
        } catch (JSONException e2) {
            e2.printStackTrace();
            this.config.getLogger().verbose(ProductConfigUtil.getLogTag(this.config), "ConvertServerJsonToMap failed - " + e2.getLocalizedMessage());
            return map;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public HashMap<String, String> getStoredValues(String str) throws Throwable {
        HashMap<String, String> map = new HashMap<>();
        try {
            String fromFile = this.fileUtils.readFromFile(str);
            this.config.getLogger().verbose(ProductConfigUtil.getLogTag(this.config), "GetStoredValues reading file success:[ " + str + "]--[Content]" + fromFile);
            if (!TextUtils.isEmpty(fromFile)) {
                try {
                    JSONObject jSONObject = new JSONObject(fromFile);
                    Iterator<String> itKeys = jSONObject.keys();
                    while (itKeys.hasNext()) {
                        String next = itKeys.next();
                        if (!TextUtils.isEmpty(next)) {
                            try {
                                String strValueOf = String.valueOf(jSONObject.get(next));
                                if (!TextUtils.isEmpty(strValueOf)) {
                                    map.put(next, strValueOf);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                this.config.getLogger().verbose(ProductConfigUtil.getLogTag(this.config), "GetStoredValues for key " + next + " while parsing json: " + e.getLocalizedMessage());
                            }
                        }
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                    this.config.getLogger().verbose(ProductConfigUtil.getLogTag(this.config), "GetStoredValues failed due to malformed json: " + e2.getLocalizedMessage());
                }
            }
            return map;
        } catch (Exception e3) {
            e3.printStackTrace();
            this.config.getLogger().verbose(ProductConfigUtil.getLogTag(this.config), "GetStoredValues reading file failed: " + e3.getLocalizedMessage());
            return map;
        }
    }

    private void onActivated() {
        if (this.callbackManager.getProductConfigListener() != null) {
            this.callbackManager.getProductConfigListener().onActivated();
        }
    }

    private void onFetched() {
        if (this.callbackManager.getProductConfigListener() != null) {
            this.callbackManager.getProductConfigListener().onFetched();
        }
    }

    private void onInit() {
        if (this.callbackManager.getProductConfigListener() != null) {
            this.config.getLogger().verbose(this.config.getAccountId(), "Product Config initialized");
            this.callbackManager.getProductConfigListener().onInit();
        }
    }

    private synchronized void parseFetchedResponse(JSONObject jSONObject) {
        Integer num;
        HashMap<String, String> mapConvertServerJsonToMap = convertServerJsonToMap(jSONObject);
        this.waitingTobeActivatedConfig.clear();
        this.waitingTobeActivatedConfig.putAll(mapConvertServerJsonToMap);
        this.config.getLogger().verbose(ProductConfigUtil.getLogTag(this.config), "Product Config: Fetched response:" + jSONObject);
        try {
            num = (Integer) jSONObject.get(CTProductConfigConstants.KEY_LAST_FETCHED_TIMESTAMP);
        } catch (Exception e) {
            e.printStackTrace();
            this.config.getLogger().verbose(ProductConfigUtil.getLogTag(this.config), "ParseFetchedResponse failed: " + e.getLocalizedMessage());
            num = null;
        }
        if (num != null) {
            this.settings.setLastFetchTimeStampInMillis(num.intValue() * 1000);
        }
    }

    /* renamed from: com.clevertap.android.sdk.product_config.CTProductConfigController$11, reason: invalid class name */
    static /* synthetic */ class AnonymousClass11 {
        static final /* synthetic */ int[] $SwitchMap$com$clevertap$android$sdk$product_config$CTProductConfigController$PROCESSING_STATE;

        static {
            int[] iArr = new int[PROCESSING_STATE.values().length];
            $SwitchMap$com$clevertap$android$sdk$product_config$CTProductConfigController$PROCESSING_STATE = iArr;
            try {
                iArr[PROCESSING_STATE.INIT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$clevertap$android$sdk$product_config$CTProductConfigController$PROCESSING_STATE[PROCESSING_STATE.FETCHED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$clevertap$android$sdk$product_config$CTProductConfigController$PROCESSING_STATE[PROCESSING_STATE.ACTIVATED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendCallback(PROCESSING_STATE processing_state) {
        if (processing_state != null) {
            int i = AnonymousClass11.$SwitchMap$com$clevertap$android$sdk$product_config$CTProductConfigController$PROCESSING_STATE[processing_state.ordinal()];
            if (i == 1) {
                onInit();
            } else if (i == 2) {
                onFetched();
            } else {
                if (i != 3) {
                    return;
                }
                onActivated();
            }
        }
    }
}
