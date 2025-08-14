package com.clevertap.android.sdk.product_config;

import android.text.TextUtils;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.task.CTExecutorFactory;
import com.clevertap.android.sdk.task.OnSuccessListener;
import com.clevertap.android.sdk.utils.FileUtils;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

@Deprecated
/* loaded from: classes5.dex */
public class ProductConfigSettings {
    private final CleverTapInstanceConfig config;
    private final FileUtils fileUtils;
    private String guid;
    private final Map<String, String> settingsMap = Collections.synchronizedMap(new HashMap());

    @Deprecated
    public String getGuid() {
        return this.guid;
    }

    void setGuid(String str) {
        this.guid = str;
    }

    @Deprecated
    ProductConfigSettings(String str, CleverTapInstanceConfig cleverTapInstanceConfig, FileUtils fileUtils) {
        this.guid = str;
        this.config = cleverTapInstanceConfig;
        this.fileUtils = fileUtils;
        initDefaults();
    }

    void eraseStoredSettingsFile(final FileUtils fileUtils) {
        if (fileUtils == null) {
            throw new IllegalArgumentException("FileUtils can't be null");
        }
        CTExecutorFactory.executors(this.config).ioTask().execute("ProductConfigSettings#eraseStoredSettingsFile", new Callable<Void>() { // from class: com.clevertap.android.sdk.product_config.ProductConfigSettings.1
            @Override // java.util.concurrent.Callable
            public Void call() {
                synchronized (this) {
                    try {
                        String fullPath = ProductConfigSettings.this.getFullPath();
                        fileUtils.deleteFile(fullPath);
                        ProductConfigSettings.this.config.getLogger().verbose(ProductConfigUtil.getLogTag(ProductConfigSettings.this.config), "Deleted settings file" + fullPath);
                    } catch (Exception e) {
                        e.printStackTrace();
                        ProductConfigSettings.this.config.getLogger().verbose(ProductConfigUtil.getLogTag(ProductConfigSettings.this.config), "Error while resetting settings" + e.getLocalizedMessage());
                    }
                }
                return null;
            }
        });
    }

    String getDirName() {
        return "Product_Config_" + this.config.getAccountId() + "_" + this.guid;
    }

    String getFullPath() {
        return getDirName() + "/config_settings.json";
    }

    JSONObject getJsonObject(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return new JSONObject(str);
        } catch (JSONException e) {
            e.printStackTrace();
            this.config.getLogger().verbose(ProductConfigUtil.getLogTag(this.config), "LoadSettings failed: " + e.getLocalizedMessage());
            return null;
        }
    }

    synchronized long getLastFetchTimeStampInMillis() {
        long j;
        String str = this.settingsMap.get(CTProductConfigConstants.KEY_LAST_FETCHED_TIMESTAMP);
        j = 0;
        try {
            if (!TextUtils.isEmpty(str)) {
                j = (long) Double.parseDouble(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.config.getLogger().verbose(ProductConfigUtil.getLogTag(this.config), "GetLastFetchTimeStampInMillis failed: " + e.getLocalizedMessage());
        }
        return j;
    }

    synchronized void setLastFetchTimeStampInMillis(long j) {
        long lastFetchTimeStampInMillis = getLastFetchTimeStampInMillis();
        if (j >= 0 && lastFetchTimeStampInMillis != j) {
            this.settingsMap.put(CTProductConfigConstants.KEY_LAST_FETCHED_TIMESTAMP, String.valueOf(j));
            updateConfigToFile();
        }
    }

    long getNextFetchIntervalInSeconds() {
        return Math.max(TimeUnit.MINUTES.toSeconds(getWindowIntervalInMinutes() / getNoOfCallsInAllowedWindow()), getMinFetchIntervalInSeconds());
    }

    void initDefaults() {
        this.settingsMap.put(CTProductConfigConstants.PRODUCT_CONFIG_NO_OF_CALLS, String.valueOf(5));
        this.settingsMap.put(CTProductConfigConstants.PRODUCT_CONFIG_WINDOW_LENGTH_MINS, String.valueOf(60));
        this.settingsMap.put(CTProductConfigConstants.KEY_LAST_FETCHED_TIMESTAMP, String.valueOf(0));
        this.settingsMap.put(CTProductConfigConstants.PRODUCT_CONFIG_MIN_INTERVAL_IN_SECONDS, String.valueOf(CTProductConfigConstants.DEFAULT_MIN_FETCH_INTERVAL_SECONDS));
        this.config.getLogger().verbose(ProductConfigUtil.getLogTag(this.config), "Settings loaded with default values: " + this.settingsMap);
    }

    synchronized void loadSettings(FileUtils fileUtils) {
        if (fileUtils == null) {
            throw new IllegalArgumentException("fileutils can't be null");
        }
        try {
            populateMapWithJson(getJsonObject(fileUtils.readFromFile(getFullPath())));
        } catch (Exception e) {
            e.printStackTrace();
            this.config.getLogger().verbose(ProductConfigUtil.getLogTag(this.config), "LoadSettings failed while reading file: " + e.getLocalizedMessage());
        }
    }

    synchronized void populateMapWithJson(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            if (!TextUtils.isEmpty(next)) {
                try {
                    String strValueOf = String.valueOf(jSONObject.get(next));
                    if (!TextUtils.isEmpty(strValueOf)) {
                        this.settingsMap.put(next, strValueOf);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    this.config.getLogger().verbose(ProductConfigUtil.getLogTag(this.config), "Failed loading setting for key " + next + " Error: " + e.getLocalizedMessage());
                }
            }
        }
        this.config.getLogger().verbose(ProductConfigUtil.getLogTag(this.config), "LoadSettings completed with settings: " + this.settingsMap);
    }

    void reset(FileUtils fileUtils) {
        initDefaults();
        eraseStoredSettingsFile(fileUtils);
    }

    void setARPValue(JSONObject jSONObject) throws JSONException {
        if (jSONObject != null) {
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                try {
                    if (!TextUtils.isEmpty(next)) {
                        Object obj = jSONObject.get(next);
                        if (obj instanceof Number) {
                            int iDoubleValue = (int) ((Number) obj).doubleValue();
                            if (CTProductConfigConstants.PRODUCT_CONFIG_NO_OF_CALLS.equalsIgnoreCase(next) || CTProductConfigConstants.PRODUCT_CONFIG_WINDOW_LENGTH_MINS.equalsIgnoreCase(next)) {
                                setProductConfigValuesFromARP(next, iDoubleValue);
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    this.config.getLogger().verbose(ProductConfigUtil.getLogTag(this.config), "Product Config setARPValue failed " + e.getLocalizedMessage());
                }
            }
        }
    }

    synchronized void setMinimumFetchIntervalInSeconds(long j) {
        long minFetchIntervalInSeconds = getMinFetchIntervalInSeconds();
        if (j > 0 && minFetchIntervalInSeconds != j) {
            this.settingsMap.put(CTProductConfigConstants.PRODUCT_CONFIG_MIN_INTERVAL_IN_SECONDS, String.valueOf(j));
        }
    }

    private long getMinFetchIntervalInSeconds() {
        long j = CTProductConfigConstants.DEFAULT_MIN_FETCH_INTERVAL_SECONDS;
        String str = this.settingsMap.get(CTProductConfigConstants.PRODUCT_CONFIG_MIN_INTERVAL_IN_SECONDS);
        try {
            return !TextUtils.isEmpty(str) ? (long) Double.parseDouble(str) : j;
        } catch (Exception e) {
            e.printStackTrace();
            this.config.getLogger().verbose(ProductConfigUtil.getLogTag(this.config), "GetMinFetchIntervalInSeconds failed: " + e.getLocalizedMessage());
            return j;
        }
    }

    private synchronized int getNoOfCallsInAllowedWindow() {
        int i;
        String str = this.settingsMap.get(CTProductConfigConstants.PRODUCT_CONFIG_NO_OF_CALLS);
        i = 5;
        try {
            if (!TextUtils.isEmpty(str)) {
                i = (int) Double.parseDouble(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.config.getLogger().verbose(ProductConfigUtil.getLogTag(this.config), "GetNoOfCallsInAllowedWindow failed: " + e.getLocalizedMessage());
        }
        return i;
    }

    private synchronized void setNoOfCallsInAllowedWindow(int i) {
        long noOfCallsInAllowedWindow = getNoOfCallsInAllowedWindow();
        if (i > 0 && noOfCallsInAllowedWindow != i) {
            this.settingsMap.put(CTProductConfigConstants.PRODUCT_CONFIG_NO_OF_CALLS, String.valueOf(i));
            updateConfigToFile();
        }
    }

    private synchronized int getWindowIntervalInMinutes() {
        int i;
        String str = this.settingsMap.get(CTProductConfigConstants.PRODUCT_CONFIG_WINDOW_LENGTH_MINS);
        i = 60;
        try {
            if (!TextUtils.isEmpty(str)) {
                i = (int) Double.parseDouble(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.config.getLogger().verbose(ProductConfigUtil.getLogTag(this.config), "GetWindowIntervalInMinutes failed: " + e.getLocalizedMessage());
        }
        return i;
    }

    private synchronized void setWindowIntervalInMinutes(int i) {
        int windowIntervalInMinutes = getWindowIntervalInMinutes();
        if (i > 0 && windowIntervalInMinutes != i) {
            this.settingsMap.put(CTProductConfigConstants.PRODUCT_CONFIG_WINDOW_LENGTH_MINS, String.valueOf(i));
            updateConfigToFile();
        }
    }

    private void setProductConfigValuesFromARP(String str, int i) {
        str.hashCode();
        if (str.equals(CTProductConfigConstants.PRODUCT_CONFIG_NO_OF_CALLS)) {
            setNoOfCallsInAllowedWindow(i);
        } else if (str.equals(CTProductConfigConstants.PRODUCT_CONFIG_WINDOW_LENGTH_MINS)) {
            setWindowIntervalInMinutes(i);
        }
    }

    private synchronized void updateConfigToFile() {
        CTExecutorFactory.executors(this.config).ioTask().addOnSuccessListener(new OnSuccessListener<Boolean>() { // from class: com.clevertap.android.sdk.product_config.ProductConfigSettings.3
            @Override // com.clevertap.android.sdk.task.OnSuccessListener
            public void onSuccess(Boolean bool) {
                if (!bool.booleanValue()) {
                    ProductConfigSettings.this.config.getLogger().verbose(ProductConfigUtil.getLogTag(ProductConfigSettings.this.config), "Product Config settings: writing Failed");
                } else {
                    ProductConfigSettings.this.config.getLogger().verbose(ProductConfigUtil.getLogTag(ProductConfigSettings.this.config), "Product Config settings: writing Success " + ProductConfigSettings.this.settingsMap);
                }
            }
        }).execute("ProductConfigSettings#updateConfigToFile", new Callable<Boolean>() { // from class: com.clevertap.android.sdk.product_config.ProductConfigSettings.2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Boolean call() {
                try {
                    HashMap map = new HashMap(ProductConfigSettings.this.settingsMap);
                    map.remove(CTProductConfigConstants.PRODUCT_CONFIG_MIN_INTERVAL_IN_SECONDS);
                    ProductConfigSettings.this.fileUtils.writeJsonToFile(ProductConfigSettings.this.getDirName(), CTProductConfigConstants.FILE_NAME_CONFIG_SETTINGS, new JSONObject(map));
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                    ProductConfigSettings.this.config.getLogger().verbose(ProductConfigUtil.getLogTag(ProductConfigSettings.this.config), "UpdateConfigToFile failed: " + e.getLocalizedMessage());
                    return false;
                }
            }
        });
    }
}
