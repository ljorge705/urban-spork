package com.singular.sdk.internal;

import com.singular.sdk.internal.ConfigManagerRepo;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

/* loaded from: classes6.dex */
public class ConfigManager {
    private static ConfigManager instance;
    private SLRemoteConfiguration currentConfig;
    private ConfigManagerRepo localRepo;
    private ConfigManagerRepo remoteRepo;
    private Map<String, ConfigUpdateHandler> configUpdateHandlers = new HashMap();
    private boolean configFetchError = false;
    private boolean configSynced = false;
    private boolean configChanged = false;

    public interface CompletionHandler {
        void onInitDone();
    }

    public interface ConfigUpdateHandler {
        void onSync(boolean z);

        void onSyncError();
    }

    public static ConfigManager getInstance() {
        return instance;
    }

    public SLRemoteConfiguration getConfig() {
        return this.currentConfig;
    }

    private ConfigManager() {
    }

    private ConfigManager(ConfigManagerRepo configManagerRepo, ConfigManagerRepo configManagerRepo2) {
        this.localRepo = configManagerRepo;
        this.remoteRepo = configManagerRepo2;
    }

    public static void init(final ConfigManagerRepo configManagerRepo, final ConfigManagerRepo configManagerRepo2, final CompletionHandler completionHandler) {
        if (instance != null) {
            return;
        }
        configManagerRepo.getConfig(new ConfigManagerRepo.CompletionHandler() { // from class: com.singular.sdk.internal.ConfigManager.1
            @Override // com.singular.sdk.internal.ConfigManagerRepo.CompletionHandler
            public void onCompleted(SLRemoteConfiguration sLRemoteConfiguration) {
                ConfigManager.handleLocalConfigReceived(configManagerRepo, configManagerRepo2, sLRemoteConfiguration, completionHandler);
            }

            @Override // com.singular.sdk.internal.ConfigManagerRepo.CompletionHandler
            public void onError() {
                ConfigManager.handleLocalConfigReceived(configManagerRepo, configManagerRepo2, null, completionHandler);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void handleLocalConfigReceived(ConfigManagerRepo configManagerRepo, ConfigManagerRepo configManagerRepo2, SLRemoteConfiguration sLRemoteConfiguration, CompletionHandler completionHandler) {
        ConfigManager configManager = new ConfigManager(configManagerRepo, configManagerRepo2);
        if (sLRemoteConfiguration == null) {
            configManager.currentConfig = SLRemoteConfiguration.defaultConfig();
        } else {
            configManager.currentConfig = sLRemoteConfiguration;
        }
        instance = configManager;
        configManager.fetchRemoteConfig();
        completionHandler.onInitDone();
    }

    private void fetchRemoteConfig() {
        this.remoteRepo.getConfig(new ConfigManagerRepo.CompletionHandler() { // from class: com.singular.sdk.internal.ConfigManager.2
            @Override // com.singular.sdk.internal.ConfigManagerRepo.CompletionHandler
            public void onCompleted(SLRemoteConfiguration sLRemoteConfiguration) {
                ConfigManager.this.configSynced = true;
                ConfigManager.this.localRepo.saveConfig(sLRemoteConfiguration, null);
                if (sLRemoteConfiguration.equals(ConfigManager.this.currentConfig)) {
                    ConfigManager.this.configChanged = false;
                } else {
                    ConfigManager.this.configChanged = true;
                }
                ConfigManager.this.currentConfig = sLRemoteConfiguration;
                Iterator it = ConfigManager.this.configUpdateHandlers.entrySet().iterator();
                while (it.hasNext()) {
                    ((ConfigUpdateHandler) ((Map.Entry) it.next()).getValue()).onSync(ConfigManager.this.configChanged);
                }
            }

            @Override // com.singular.sdk.internal.ConfigManagerRepo.CompletionHandler
            public void onError() {
                ConfigManager.this.configFetchError = false;
                Iterator it = ConfigManager.this.configUpdateHandlers.entrySet().iterator();
                while (it.hasNext()) {
                    ((ConfigUpdateHandler) ((Map.Entry) it.next()).getValue()).onSyncError();
                }
            }
        });
    }

    public String registerForConfigUpdates(ConfigUpdateHandler configUpdateHandler) {
        if (this.configSynced) {
            configUpdateHandler.onSync(this.configChanged);
        }
        if (this.configFetchError) {
            configUpdateHandler.onSyncError();
        }
        String str = UUID.randomUUID().toString() + "_" + System.currentTimeMillis();
        this.configUpdateHandlers.put(str, configUpdateHandler);
        return str;
    }

    public void unregisterConfigUpdates(String str) {
        this.configUpdateHandlers.remove(str);
    }
}
