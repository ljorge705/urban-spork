package com.singular.sdk.internal;

import com.clevertap.android.sdk.product_config.CTProductConfigConstants;
import com.singular.sdk.internal.ConfigManagerRepo;
import com.singular.sdk.internal.GeneralHttpServiceBase;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class ConfigManagerRepoNetwork extends ConfigManagerRepo {
    private static final SingularLog logger = SingularLog.getLogger("ConfigManagerRepoNetwork");
    private GeneralHttpServiceBase httpService;

    @Override // com.singular.sdk.internal.ConfigManagerRepo
    public void saveConfig(SLRemoteConfiguration sLRemoteConfiguration, ConfigManagerRepo.CompletionHandler completionHandler) {
    }

    private ConfigManagerRepoNetwork() {
    }

    public ConfigManagerRepoNetwork(GeneralHttpServiceBase generalHttpServiceBase) {
        this.httpService = generalHttpServiceBase;
    }

    @Override // com.singular.sdk.internal.ConfigManagerRepo
    public void getConfig(ConfigManagerRepo.CompletionHandler completionHandler) {
        getConfig(completionHandler, 3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void waitAndRetry(final ConfigManagerRepo.CompletionHandler completionHandler, final int i, String str) {
        ScheduledExecutorService scheduledExecutorServiceNewSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        if (i > 0) {
            scheduledExecutorServiceNewSingleThreadScheduledExecutor.schedule(new Runnable() { // from class: com.singular.sdk.internal.ConfigManagerRepoNetwork.1
                @Override // java.lang.Runnable
                public void run() {
                    ConfigManagerRepoNetwork.this.getConfig(completionHandler, i - 1);
                }
            }, 3000L, TimeUnit.MILLISECONDS);
        } else if (completionHandler != null) {
            completionHandler.onError();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getConfig(final ConfigManagerRepo.CompletionHandler completionHandler, final int i) {
        HashMap map = new HashMap();
        map.put("sdk", Utils.getSdkVersion());
        DeviceInfo deviceInfo = SingularInstance.getInstance().getDeviceInfo();
        if (deviceInfo != null) {
            map.put("p", deviceInfo.platform);
            map.put("n", deviceInfo.appName);
            map.put("i", deviceInfo.packageName);
            map.put(CTProductConfigConstants.PRODUCT_CONFIG_JSON_KEY_FOR_VALUE, deviceInfo.osVersion);
        }
        int andIncrementRetryCountForKey = Utils.getAndIncrementRetryCountForKey(SingularInstance.getInstance().getContext(), "config");
        if (andIncrementRetryCountForKey > 3) {
            map.put(Constants.RETRY_COUNT, String.valueOf(andIncrementRetryCountForKey));
        }
        this.httpService.sendRequest("/config", map, null, new GeneralHttpServiceBase.CompletionHandler() { // from class: com.singular.sdk.internal.ConfigManagerRepoNetwork.2
            @Override // com.singular.sdk.internal.GeneralHttpServiceBase.CompletionHandler
            public void onSuccess(String str, int i2) {
                if (i2 != 200 || str == null) {
                    ConfigManagerRepoNetwork.this.waitAndRetry(completionHandler, i, "get config failed with code = " + i2);
                    return;
                }
                try {
                    SLRemoteConfiguration sLRemoteConfigurationFromJson = SLRemoteConfiguration.fromJson(new JSONObject(str));
                    ConfigManagerRepo.CompletionHandler completionHandler2 = completionHandler;
                    if (completionHandler2 != null) {
                        completionHandler2.onCompleted(sLRemoteConfigurationFromJson);
                    }
                    Utils.resetRetryCountForKey(SingularInstance.getInstance().getContext(), "config");
                } catch (JSONException e) {
                    ConfigManagerRepoNetwork.logger.error(Utils.formatException(e));
                    ConfigManagerRepoNetwork.this.waitAndRetry(completionHandler, i, e.getMessage());
                }
            }

            @Override // com.singular.sdk.internal.GeneralHttpServiceBase.CompletionHandler
            public void onFailure(String str) {
                ConfigManagerRepoNetwork.this.waitAndRetry(completionHandler, i, str);
            }
        });
    }
}
