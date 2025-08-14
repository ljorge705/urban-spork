package com.sdkmobilereactnative.initContextualDataCollection;

import androidx.work.Data;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.ListenableWorker;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.variables.CTVariableUtils;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.fivvy.profiler.domain.models.AppInstalledInfo;
import com.fivvy.profiler.domain.models.AppUsageInfo;
import com.fivvy.profiler.domain.models.DeviceInfo;
import com.fivvy.profiler.domain.usecases.InitContextDataCollection;
import com.google.gson.Gson;
import io.sentry.SentryEvent;
import io.sentry.protocol.Device;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@ReactModule(name = InitContextualDataCollectionModule.NAME)
/* loaded from: classes6.dex */
public class InitContextualDataCollectionModule extends ReactContextBaseJavaModule {
    private static final int BACKGROUND_TASK_INTERVAL = 1440;
    private static final String COMPANY_ID = "company-bank-id-124";
    public static final String NAME = "InitContextualDataCollectionModule";
    private final InitContextDataCollection initContextualDataCollection;
    private final ReactApplicationContext reactContext;

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    public InitContextualDataCollectionModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.reactContext = reactApplicationContext;
        this.initContextualDataCollection = new InitContextDataCollection(reactApplicationContext);
    }

    @ReactMethod
    public void initContextualData(String str, String str2, String str3, String str4, String str5, int i, Promise promise) throws Throwable {
        WritableMap writableMapCreateMap = Arguments.createMap();
        Map<String, Object> mapExecute = this.initContextualDataCollection.execute(COMPANY_ID, str, i);
        mapExecute.put(Constants.DEVICE_ID_TAG, extractDeviceInfo(mapExecute).getDeviceId());
        scheduleBackgroundTask(new Gson().toJson(mapExecute), str2, str3, str4, str5);
        populateContextualDataBridge(writableMapCreateMap, mapExecute);
        promise.resolve(writableMapCreateMap);
    }

    private DeviceInfo extractDeviceInfo(Map<String, Object> map) {
        return (DeviceInfo) map.get("deviceInfo");
    }

    private void scheduleBackgroundTask(String str, String str2, String str3, String str4, String str5) throws Throwable {
        WorkManager workManager = WorkManager.getInstance(this.reactContext);
        workManager.cancelAllWork();
        workManager.enqueueUniquePeriodicWork("backgroundTaskUnique", ExistingPeriodicWorkPolicy.KEEP, new PeriodicWorkRequest.Builder((Class<? extends ListenableWorker>) BackgroundTask.class, 1440L, TimeUnit.MINUTES).setInputData(new Data.Builder().putString("data_file_path", saveDataToFile(str).getAbsolutePath()).putString("api-key", str2).putString("api-secret", str3).putString("authApiUrl", str4).putString("sendDataApiUrl", str5).build()).build());
    }

    private File saveDataToFile(String str) {
        try {
            File file = new File(this.reactContext.getFilesDir(), "contextual_data.json");
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(str);
            fileWriter.flush();
            fileWriter.close();
            return file;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void populateContextualDataBridge(WritableMap writableMap, Map<String, Object> map) {
        writableMap.putString("companyId", (String) map.get("companyId"));
        writableMap.putString("customerId", (String) map.get("customerId"));
        if (map.get("appUsage") == null) {
            writableMap.putNull("appUsage");
        } else {
            writableMap.putArray("appUsage", mapAppUsage((List) map.get("appUsage")));
        }
        writableMap.putMap("deviceInfo", mapDeviceInfo((DeviceInfo) map.get("deviceInfo")));
        writableMap.putArray("installedAppsInfo", mapInstalledApps((List) map.get("installedAppsInfo")));
    }

    private WritableArray mapEmailAddresses(List<Map<String, String>> list) {
        WritableArray writableArrayCreateArray = Arguments.createArray();
        for (Map<String, String> map : list) {
            WritableMap writableMapCreateMap = Arguments.createMap();
            writableMapCreateMap.putString("label", map.get("label"));
            writableMapCreateMap.putString("email", map.get("email"));
            writableArrayCreateArray.pushMap(writableMapCreateMap);
        }
        return writableArrayCreateArray;
    }

    private WritableArray mapPhoneNumbers(List<Map<String, String>> list) {
        WritableArray writableArrayCreateArray = Arguments.createArray();
        for (Map<String, String> map : list) {
            WritableMap writableMapCreateMap = Arguments.createMap();
            writableMapCreateMap.putString("id", map.get("id"));
            writableMapCreateMap.putString("label", map.get("label"));
            writableMapCreateMap.putString(CTVariableUtils.NUMBER, map.get(CTVariableUtils.NUMBER));
            writableArrayCreateArray.pushMap(writableMapCreateMap);
        }
        return writableArrayCreateArray;
    }

    private WritableArray mapAppUsage(List<AppUsageInfo> list) {
        WritableArray writableArrayCreateArray = Arguments.createArray();
        for (AppUsageInfo appUsageInfo : list) {
            WritableMap writableMapCreateMap = Arguments.createMap();
            writableMapCreateMap.putString("appName", appUsageInfo.getAppName());
            writableMapCreateMap.putString("packageName", appUsageInfo.getPackageName());
            writableMapCreateMap.putDouble(com.facebook.hermes.intl.Constants.COLLATION_OPTION_USAGE, appUsageInfo.getUsage());
            writableArrayCreateArray.pushMap(writableMapCreateMap);
        }
        return writableArrayCreateArray;
    }

    private WritableMap mapDeviceInfo(DeviceInfo deviceInfo) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putInt("battery_status", deviceInfo.getBatteryStatus());
        writableMapCreateMap.putString("api_level", deviceInfo.getApiLevel());
        writableMapCreateMap.putString("product", deviceInfo.getProduct());
        writableMapCreateMap.putString("device_id", deviceInfo.getDeviceId());
        writableMapCreateMap.putString("incremental_version", deviceInfo.getIncrementalVersion());
        writableMapCreateMap.putString("display", deviceInfo.getDisplay());
        writableMapCreateMap.putString("type", deviceInfo.getType());
        writableMapCreateMap.putString(Device.JsonKeys.MANUFACTURER, deviceInfo.getManufacturer());
        writableMapCreateMap.putString("tags", deviceInfo.getTags());
        writableMapCreateMap.putString("host", deviceInfo.getHost());
        writableMapCreateMap.putString(SentryEvent.JsonKeys.FINGERPRINT, deviceInfo.getFingerprint());
        writableMapCreateMap.putString(Device.JsonKeys.MODEL, deviceInfo.getModel());
        writableMapCreateMap.putString("id", deviceInfo.getId());
        writableMapCreateMap.putString("release_version", deviceInfo.getReleaseVersion());
        writableMapCreateMap.putString(Device.TYPE, deviceInfo.getDevice());
        writableMapCreateMap.putString(Device.JsonKeys.BRAND, deviceInfo.getBrand());
        writableMapCreateMap.putString("base_os", deviceInfo.getBaseOs());
        writableMapCreateMap.putString("hardware", deviceInfo.getHardware());
        writableMapCreateMap.putString(com.facebook.hermes.intl.Constants.SENSITIVITY_BASE, deviceInfo.getBase());
        return writableMapCreateMap;
    }

    private WritableArray mapInstalledApps(List<AppInstalledInfo> list) {
        WritableArray writableArrayCreateArray = Arguments.createArray();
        for (AppInstalledInfo appInstalledInfo : list) {
            WritableMap writableMapCreateMap = Arguments.createMap();
            writableMapCreateMap.putString("appName", appInstalledInfo.appName);
            writableMapCreateMap.putString("packageName", appInstalledInfo.packageName);
            writableArrayCreateArray.pushMap(writableMapCreateMap);
        }
        return writableArrayCreateArray;
    }

    private Map<String, Object> convertDataToMap(Data data) {
        HashMap map = new HashMap();
        for (String str : data.getKeyValueMap().keySet()) {
            map.put(str, data.getString(str));
        }
        return map;
    }
}
