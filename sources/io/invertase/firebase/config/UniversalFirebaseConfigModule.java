package io.invertase.firebase.config;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import com.facebook.hermes.intl.Constants;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigInfo;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigValue;
import io.invertase.firebase.common.UniversalFirebaseModule;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/* loaded from: classes6.dex */
public class UniversalFirebaseConfigModule extends UniversalFirebaseModule {
    private static final String SOURCE = "source";
    private static final String VALUE = "value";

    private String lastFetchStatusToString(int i) {
        return i != -1 ? i != 0 ? i != 1 ? i != 2 ? "unknown" : "throttled" : "failure" : "no_fetch_yet" : FirebaseAnalytics.Param.SUCCESS;
    }

    UniversalFirebaseConfigModule(Context context, String str) {
        super(context, str);
    }

    Task<Boolean> activate(String str) {
        return FirebaseRemoteConfig.getInstance(FirebaseApp.getInstance(str)).activate();
    }

    Task<Void> fetch(String str, final long j) {
        final FirebaseApp firebaseApp = FirebaseApp.getInstance(str);
        return Tasks.call(getExecutor(), new Callable() { // from class: io.invertase.firebase.config.UniversalFirebaseConfigModule$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return UniversalFirebaseConfigModule.lambda$fetch$0(firebaseApp, j);
            }
        });
    }

    static /* synthetic */ Void lambda$fetch$0(FirebaseApp firebaseApp, long j) throws Exception {
        FirebaseRemoteConfig firebaseRemoteConfig = FirebaseRemoteConfig.getInstance(firebaseApp);
        Tasks.await(j == -1 ? firebaseRemoteConfig.fetch() : firebaseRemoteConfig.fetch(j));
        return null;
    }

    Task<Boolean> fetchAndActivate(String str) {
        return FirebaseRemoteConfig.getInstance(FirebaseApp.getInstance(str)).fetchAndActivate();
    }

    Task<Void> reset(String str) {
        return FirebaseRemoteConfig.getInstance(FirebaseApp.getInstance(str)).reset();
    }

    Task<Void> setConfigSettings(String str, final Bundle bundle) {
        final FirebaseApp firebaseApp = FirebaseApp.getInstance(str);
        return Tasks.call(getExecutor(), new Callable() { // from class: io.invertase.firebase.config.UniversalFirebaseConfigModule$$ExternalSyntheticLambda2
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return UniversalFirebaseConfigModule.lambda$setConfigSettings$1(bundle, firebaseApp);
            }
        });
    }

    static /* synthetic */ Void lambda$setConfigSettings$1(Bundle bundle, FirebaseApp firebaseApp) throws Exception {
        FirebaseRemoteConfigSettings.Builder builder = new FirebaseRemoteConfigSettings.Builder();
        if (bundle.containsKey("minimumFetchInterval")) {
            builder.setMinimumFetchIntervalInSeconds((long) bundle.getDouble("minimumFetchInterval"));
        }
        if (bundle.containsKey("fetchTimeout")) {
            builder.setFetchTimeoutInSeconds((long) bundle.getDouble("fetchTimeout"));
        }
        FirebaseRemoteConfig.getInstance(firebaseApp).setConfigSettingsAsync(builder.build());
        return null;
    }

    Task<Void> setDefaultsFromResource(String str, final String str2) {
        final FirebaseApp firebaseApp = FirebaseApp.getInstance(str);
        return Tasks.call(getExecutor(), new Callable() { // from class: io.invertase.firebase.config.UniversalFirebaseConfigModule$$ExternalSyntheticLambda1
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.lambda$setDefaultsFromResource$2(str2, firebaseApp);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Void lambda$setDefaultsFromResource$2(String str, FirebaseApp firebaseApp) throws Exception {
        XmlResourceParser xml;
        int xmlResourceIdByName = getXmlResourceIdByName(str);
        try {
            xml = getApplicationContext().getResources().getXml(xmlResourceIdByName);
        } catch (Resources.NotFoundException unused) {
            xml = null;
        }
        if (xml != null) {
            Tasks.await(FirebaseRemoteConfig.getInstance(firebaseApp).setDefaultsAsync(xmlResourceIdByName));
            return null;
        }
        throw new Exception("resource_not_found");
    }

    Task<Void> setDefaults(String str, HashMap<String, Object> map) {
        return FirebaseRemoteConfig.getInstance(FirebaseApp.getInstance(str)).setDefaultsAsync(map);
    }

    Task<FirebaseRemoteConfigInfo> ensureInitialized(String str) {
        Task<FirebaseRemoteConfigInfo> taskEnsureInitialized = FirebaseRemoteConfig.getInstance(FirebaseApp.getInstance(str)).ensureInitialized();
        try {
            Tasks.await(fetchAndActivate(str));
        } catch (Exception unused) {
        }
        return taskEnsureInitialized;
    }

    Map<String, Object> getAllValuesForApp(String str) {
        Map<String, FirebaseRemoteConfigValue> all = FirebaseRemoteConfig.getInstance(FirebaseApp.getInstance(str)).getAll();
        HashMap map = new HashMap(all.size());
        for (Map.Entry<String, FirebaseRemoteConfigValue> entry : all.entrySet()) {
            map.put(entry.getKey(), convertRemoteConfigValue(entry.getValue()));
        }
        return map;
    }

    private int getXmlResourceIdByName(String str) {
        return getApplicationContext().getResources().getIdentifier(str, "xml", getApplicationContext().getPackageName());
    }

    private Bundle convertRemoteConfigValue(FirebaseRemoteConfigValue firebaseRemoteConfigValue) {
        Bundle bundle = new Bundle(2);
        bundle.putString("value", firebaseRemoteConfigValue.asString());
        int source = firebaseRemoteConfigValue.getSource();
        if (source == 1) {
            bundle.putString("source", Constants.COLLATION_DEFAULT);
        } else if (source == 2) {
            bundle.putString("source", "remote");
        } else {
            bundle.putString("source", "static");
        }
        return bundle;
    }

    public Map<String, Object> getConstantsForApp(String str) {
        HashMap map = new HashMap();
        FirebaseRemoteConfigInfo info = FirebaseRemoteConfig.getInstance(FirebaseApp.getInstance(str)).getInfo();
        FirebaseRemoteConfigSettings configSettings = info.getConfigSettings();
        map.put("values", getAllValuesForApp(str));
        map.put("lastFetchTime", Long.valueOf(info.getFetchTimeMillis()));
        map.put("lastFetchStatus", lastFetchStatusToString(info.getLastFetchStatus()));
        map.put("minimumFetchInterval", Long.valueOf(configSettings.getMinimumFetchIntervalInSeconds()));
        map.put("fetchTimeout", Long.valueOf(configSettings.getFetchTimeoutInSeconds()));
        return map;
    }
}
