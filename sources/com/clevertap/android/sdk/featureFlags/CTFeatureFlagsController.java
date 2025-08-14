package com.clevertap.android.sdk.featureFlags;

import android.text.TextUtils;
import com.clevertap.android.sdk.BaseAnalyticsManager;
import com.clevertap.android.sdk.BaseCallbackManager;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.product_config.CTProductConfigConstants;
import com.clevertap.android.sdk.task.CTExecutorFactory;
import com.clevertap.android.sdk.task.OnSuccessListener;
import com.clevertap.android.sdk.task.Task;
import com.clevertap.android.sdk.utils.FileUtils;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Deprecated
/* loaded from: classes5.dex */
public class CTFeatureFlagsController {
    final CleverTapInstanceConfig config;
    String guid;
    final BaseAnalyticsManager mAnalyticsManager;
    final BaseCallbackManager mCallbackManager;
    FileUtils mFileUtils;
    boolean isInitialized = false;
    private final Map<String, Boolean> store = Collections.synchronizedMap(new HashMap());

    String getCachedFileName() {
        return CTFeatureFlagConstants.CACHED_FILE_NAME;
    }

    @Deprecated
    public String getGuid() {
        return this.guid;
    }

    @Deprecated
    public boolean isInitialized() {
        return this.isInitialized;
    }

    @Deprecated
    CTFeatureFlagsController(String str, CleverTapInstanceConfig cleverTapInstanceConfig, BaseCallbackManager baseCallbackManager, BaseAnalyticsManager baseAnalyticsManager, FileUtils fileUtils) {
        this.guid = str;
        this.config = cleverTapInstanceConfig;
        this.mCallbackManager = baseCallbackManager;
        this.mAnalyticsManager = baseAnalyticsManager;
        this.mFileUtils = fileUtils;
        init();
    }

    @Deprecated
    public void fetchFeatureFlags() {
        CTExecutorFactory.executors(this.config).mainTask().execute("fetchFeatureFlags", new Callable<Void>() { // from class: com.clevertap.android.sdk.featureFlags.CTFeatureFlagsController.1
            @Override // java.util.concurrent.Callable
            public Void call() {
                try {
                    CTFeatureFlagsController.this.mAnalyticsManager.fetchFeatureFlags();
                    return null;
                } catch (Exception e) {
                    CTFeatureFlagsController.this.getConfigLogger().verbose(CTFeatureFlagsController.this.getLogTag(), e.getLocalizedMessage());
                    return null;
                }
            }
        });
    }

    @Deprecated
    public Boolean get(String str, boolean z) {
        if (!this.isInitialized) {
            getConfigLogger().verbose(getLogTag(), "Controller not initialized, returning default value - " + z);
            return Boolean.valueOf(z);
        }
        getConfigLogger().verbose(getLogTag(), "Getting feature flag with key - " + str + " and default value - " + z);
        Boolean bool = this.store.get(str);
        if (bool != null) {
            return bool;
        }
        getConfigLogger().verbose(getLogTag(), "Feature flag not found, returning default value - " + z);
        return Boolean.valueOf(z);
    }

    @Deprecated
    public void resetWithGuid(String str) {
        this.guid = str;
        init();
    }

    @Deprecated
    public void setGuidAndInit(String str) {
        if (this.isInitialized) {
            return;
        }
        this.guid = str;
        init();
    }

    @Deprecated
    public synchronized void updateFeatureFlags(JSONObject jSONObject) throws JSONException {
        JSONArray jSONArray = jSONObject.getJSONArray(Constants.KEY_KV);
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                this.store.put(jSONObject2.getString("n"), Boolean.valueOf(jSONObject2.getBoolean(CTProductConfigConstants.PRODUCT_CONFIG_JSON_KEY_FOR_VALUE)));
            } catch (JSONException e) {
                getConfigLogger().verbose(getLogTag(), "Error parsing Feature Flag array " + e.getLocalizedMessage());
            }
        }
        getConfigLogger().verbose(getLogTag(), "Updating feature flags..." + this.store);
        archiveData(jSONObject);
        notifyFeatureFlagUpdate();
    }

    String getCachedDirName() {
        return "Feature_Flag_" + this.config.getAccountId() + "_" + this.guid;
    }

    String getCachedFullPath() {
        return getCachedDirName() + "/" + getCachedFileName();
    }

    void init() {
        if (TextUtils.isEmpty(this.guid)) {
            return;
        }
        Task taskIoTask = CTExecutorFactory.executors(this.config).ioTask();
        taskIoTask.addOnSuccessListener(new OnSuccessListener<Boolean>() { // from class: com.clevertap.android.sdk.featureFlags.CTFeatureFlagsController.2
            @Override // com.clevertap.android.sdk.task.OnSuccessListener
            public void onSuccess(Boolean bool) {
                CTFeatureFlagsController.this.isInitialized = bool.booleanValue();
            }
        });
        taskIoTask.execute("initFeatureFlags", new Callable<Boolean>() { // from class: com.clevertap.android.sdk.featureFlags.CTFeatureFlagsController.3
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Boolean call() {
                synchronized (this) {
                    CTFeatureFlagsController.this.getConfigLogger().verbose(CTFeatureFlagsController.this.getLogTag(), "Feature flags init is called");
                    String cachedFullPath = CTFeatureFlagsController.this.getCachedFullPath();
                    try {
                        CTFeatureFlagsController.this.store.clear();
                        String fromFile = CTFeatureFlagsController.this.mFileUtils.readFromFile(cachedFullPath);
                        if (TextUtils.isEmpty(fromFile)) {
                            CTFeatureFlagsController.this.getConfigLogger().verbose(CTFeatureFlagsController.this.getLogTag(), "Feature flags file is empty-" + cachedFullPath);
                        } else {
                            JSONArray jSONArray = new JSONObject(fromFile).getJSONArray(Constants.KEY_KV);
                            if (jSONArray != null && jSONArray.length() > 0) {
                                for (int i = 0; i < jSONArray.length(); i++) {
                                    JSONObject jSONObject = (JSONObject) jSONArray.get(i);
                                    if (jSONObject != null) {
                                        String string = jSONObject.getString("n");
                                        String string2 = jSONObject.getString(CTProductConfigConstants.PRODUCT_CONFIG_JSON_KEY_FOR_VALUE);
                                        if (!TextUtils.isEmpty(string)) {
                                            CTFeatureFlagsController.this.store.put(string, Boolean.valueOf(Boolean.parseBoolean(string2)));
                                        }
                                    }
                                }
                            }
                            CTFeatureFlagsController.this.getConfigLogger().verbose(CTFeatureFlagsController.this.getLogTag(), "Feature flags initialized from file " + cachedFullPath + " with configs  " + CTFeatureFlagsController.this.store);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        CTFeatureFlagsController.this.getConfigLogger().verbose(CTFeatureFlagsController.this.getLogTag(), "UnArchiveData failed file- " + cachedFullPath + StringUtils.SPACE + e.getLocalizedMessage());
                        return false;
                    }
                }
                return true;
            }
        });
    }

    private synchronized void archiveData(JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                this.mFileUtils.writeJsonToFile(getCachedDirName(), getCachedFileName(), jSONObject);
                getConfigLogger().verbose(getLogTag(), "Feature flags saved into file-[" + getCachedFullPath() + "]" + this.store);
            } catch (Exception e) {
                e.printStackTrace();
                getConfigLogger().verbose(getLogTag(), "ArchiveData failed - " + e.getLocalizedMessage());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Logger getConfigLogger() {
        return this.config.getLogger();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getLogTag() {
        return this.config.getAccountId() + "[Feature Flag]";
    }

    private void notifyFeatureFlagUpdate() {
        if (this.mCallbackManager.getFeatureFlagListener() != null) {
            CTExecutorFactory.executors(this.config).mainTask().execute("notifyFeatureFlagUpdate", new Callable<Void>() { // from class: com.clevertap.android.sdk.featureFlags.CTFeatureFlagsController.4
                @Override // java.util.concurrent.Callable
                public Void call() {
                    try {
                        if (CTFeatureFlagsController.this.mCallbackManager.getFeatureFlagListener() == null) {
                            return null;
                        }
                        CTFeatureFlagsController.this.mCallbackManager.getFeatureFlagListener().featureFlagsUpdated();
                        return null;
                    } catch (Exception e) {
                        CTFeatureFlagsController.this.getConfigLogger().verbose(CTFeatureFlagsController.this.getLogTag(), e.getLocalizedMessage());
                        return null;
                    }
                }
            });
        }
    }
}
