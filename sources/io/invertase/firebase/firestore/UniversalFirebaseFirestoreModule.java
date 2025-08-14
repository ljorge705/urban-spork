package io.invertase.firebase.firestore;

import android.content.Context;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.LoadBundleTask;
import io.invertase.firebase.common.UniversalFirebaseModule;
import io.invertase.firebase.common.UniversalFirebasePreferences;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;

/* loaded from: classes6.dex */
public class UniversalFirebaseFirestoreModule extends UniversalFirebaseModule {
    private static HashMap<String, String> emulatorConfigs = new HashMap<>();

    UniversalFirebaseFirestoreModule(Context context, String str) {
        super(context, str);
    }

    Task<Void> disableNetwork(String str) {
        return UniversalFirebaseFirestoreCommon.getFirestoreForApp(str).disableNetwork();
    }

    Task<Void> enableNetwork(String str) {
        return UniversalFirebaseFirestoreCommon.getFirestoreForApp(str).enableNetwork();
    }

    Task<Void> useEmulator(final String str, final String str2, final int i) {
        return Tasks.call(getExecutor(), new Callable() { // from class: io.invertase.firebase.firestore.UniversalFirebaseFirestoreModule$$ExternalSyntheticLambda1
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return UniversalFirebaseFirestoreModule.lambda$useEmulator$0(str, str2, i);
            }
        });
    }

    static /* synthetic */ Void lambda$useEmulator$0(String str, String str2, int i) throws Exception {
        if (emulatorConfigs.get(str) != null) {
            return null;
        }
        emulatorConfigs.put(str, "true");
        UniversalFirebaseFirestoreCommon.getFirestoreForApp(str).useEmulator(str2, i);
        return null;
    }

    Task<Void> settings(final String str, final Map<String, Object> map) {
        return Tasks.call(getExecutor(), new Callable() { // from class: io.invertase.firebase.firestore.UniversalFirebaseFirestoreModule$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return UniversalFirebaseFirestoreModule.lambda$settings$1(map, str);
            }
        });
    }

    static /* synthetic */ Void lambda$settings$1(Map map, String str) throws Exception {
        if (map.containsKey("cacheSizeBytes")) {
            UniversalFirebasePreferences.getSharedInstance().setIntValue(UniversalFirebaseFirestoreStatics.FIRESTORE_CACHE_SIZE + "_" + str, ((Double) Objects.requireNonNull((Double) map.get("cacheSizeBytes"))).intValue());
        }
        if (map.containsKey("host")) {
            UniversalFirebasePreferences.getSharedInstance().setStringValue(UniversalFirebaseFirestoreStatics.FIRESTORE_HOST + "_" + str, (String) map.get("host"));
        }
        if (map.containsKey("persistence")) {
            UniversalFirebasePreferences.getSharedInstance().setBooleanValue(UniversalFirebaseFirestoreStatics.FIRESTORE_PERSISTENCE + "_" + str, ((Boolean) map.get("persistence")).booleanValue());
        }
        if (map.containsKey("ssl")) {
            UniversalFirebasePreferences.getSharedInstance().setBooleanValue(UniversalFirebaseFirestoreStatics.FIRESTORE_SSL + "_" + str, ((Boolean) map.get("ssl")).booleanValue());
        }
        if (!map.containsKey("serverTimestampBehavior")) {
            return null;
        }
        UniversalFirebasePreferences.getSharedInstance().setStringValue(UniversalFirebaseFirestoreStatics.FIRESTORE_SERVER_TIMESTAMP_BEHAVIOR + "_" + str, (String) map.get("serverTimestampBehavior"));
        return null;
    }

    LoadBundleTask loadBundle(String str, String str2) {
        return UniversalFirebaseFirestoreCommon.getFirestoreForApp(str).loadBundle(str2.getBytes(StandardCharsets.UTF_8));
    }

    Task<Void> clearPersistence(String str) {
        return UniversalFirebaseFirestoreCommon.getFirestoreForApp(str).clearPersistence();
    }

    Task<Void> waitForPendingWrites(String str) {
        return UniversalFirebaseFirestoreCommon.getFirestoreForApp(str).waitForPendingWrites();
    }

    Task<Void> terminate(String str) {
        FirebaseFirestore firestoreForApp = UniversalFirebaseFirestoreCommon.getFirestoreForApp(str);
        if (UniversalFirebaseFirestoreCommon.instanceCache.get(str) != null) {
            UniversalFirebaseFirestoreCommon.instanceCache.get(str).clear();
            UniversalFirebaseFirestoreCommon.instanceCache.remove(str);
        }
        return firestoreForApp.terminate();
    }
}
