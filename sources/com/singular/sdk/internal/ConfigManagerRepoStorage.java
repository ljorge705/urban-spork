package com.singular.sdk.internal;

import android.content.Context;
import android.content.SharedPreferences;
import com.singular.sdk.internal.ConfigManagerRepo;
import java.util.concurrent.Executors;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class ConfigManagerRepoStorage extends ConfigManagerRepo {
    private static final SingularLog logger = SingularLog.getLogger("ConfigManagerRepoStorage");
    private Context context;

    public ConfigManagerRepoStorage(Context context) {
        this.context = context;
    }

    @Override // com.singular.sdk.internal.ConfigManagerRepo
    public void getConfig(final ConfigManagerRepo.CompletionHandler completionHandler) {
        Executors.newSingleThreadExecutor().execute(new Runnable() { // from class: com.singular.sdk.internal.ConfigManagerRepoStorage.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    String string = ConfigManagerRepoStorage.this.context.getSharedPreferences(Constants.PREF_CONFIG_MANAGER, 0).getString(Constants.CONFIG_MANAGER_CONFIG_KEY, null);
                    if (string == null) {
                        ConfigManagerRepo.CompletionHandler completionHandler2 = completionHandler;
                        if (completionHandler2 != null) {
                            completionHandler2.onError();
                            return;
                        }
                        return;
                    }
                    SLRemoteConfiguration sLRemoteConfigurationFromJson = SLRemoteConfiguration.fromJson(new JSONObject(string));
                    ConfigManagerRepo.CompletionHandler completionHandler3 = completionHandler;
                    if (completionHandler3 != null) {
                        completionHandler3.onCompleted(sLRemoteConfigurationFromJson);
                    }
                } catch (Throwable th) {
                    ConfigManagerRepoStorage.logger.error(Utils.formatException(th));
                    ConfigManagerRepo.CompletionHandler completionHandler4 = completionHandler;
                    if (completionHandler4 != null) {
                        completionHandler4.onError();
                    }
                }
            }
        });
    }

    @Override // com.singular.sdk.internal.ConfigManagerRepo
    public void saveConfig(final SLRemoteConfiguration sLRemoteConfiguration, final ConfigManagerRepo.CompletionHandler completionHandler) {
        Executors.newSingleThreadExecutor().execute(new Runnable() { // from class: com.singular.sdk.internal.ConfigManagerRepoStorage.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    SharedPreferences.Editor editorEdit = ConfigManagerRepoStorage.this.context.getSharedPreferences(Constants.PREF_CONFIG_MANAGER, 0).edit();
                    editorEdit.putString(Constants.CONFIG_MANAGER_CONFIG_KEY, sLRemoteConfiguration.toJson().toString());
                    editorEdit.commit();
                    ConfigManagerRepo.CompletionHandler completionHandler2 = completionHandler;
                    if (completionHandler2 != null) {
                        completionHandler2.onCompleted(sLRemoteConfiguration);
                    }
                } catch (Throwable th) {
                    ConfigManagerRepoStorage.logger.error(Utils.formatException(th));
                    ConfigManagerRepo.CompletionHandler completionHandler3 = completionHandler;
                    if (completionHandler3 != null) {
                        completionHandler3.onError();
                    }
                }
            }
        });
    }
}
