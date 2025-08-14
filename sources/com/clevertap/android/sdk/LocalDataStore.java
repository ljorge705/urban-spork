package com.clevertap.android.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import com.clevertap.android.sdk.cryption.CryptHandler;
import com.clevertap.android.sdk.cryption.CryptUtils;
import com.clevertap.android.sdk.db.BaseDatabaseManager;
import com.clevertap.android.sdk.db.DBAdapter;
import com.clevertap.android.sdk.events.EventDetail;
import com.clevertap.android.sdk.usereventlogs.UserEventLog;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class LocalDataStore {
    private static long EXECUTOR_THREAD_ID;
    private final BaseDatabaseManager baseDatabaseManager;
    private final CleverTapInstanceConfig config;
    private final Context context;
    private final CryptHandler cryptHandler;
    private final DeviceInfo deviceInfo;
    private final HashMap<String, Object> PROFILE_FIELDS_IN_THIS_SESSION = new HashMap<>();
    private final String eventNamespace = "local_events";
    private final Set<String> userNormalizedEventLogKeys = Collections.synchronizedSet(new HashSet());
    private final Map<String, String> normalizedEventNames = new HashMap();
    private final ExecutorService es = Executors.newFixedThreadPool(1);

    LocalDataStore(Context context, CleverTapInstanceConfig cleverTapInstanceConfig, CryptHandler cryptHandler, DeviceInfo deviceInfo, BaseDatabaseManager baseDatabaseManager) {
        this.context = context;
        this.config = cleverTapInstanceConfig;
        this.cryptHandler = cryptHandler;
        this.deviceInfo = deviceInfo;
        this.baseDatabaseManager = baseDatabaseManager;
    }

    public void changeUser() {
        this.userNormalizedEventLogKeys.clear();
        resetLocalProfileSync();
    }

    @Deprecated(since = "7.1.0")
    EventDetail getEventDetail(String str) {
        try {
            if (isPersonalisationEnabled()) {
                return decodeEventDetails(str, getStringFromPrefs(str, null, !this.config.isDefaultInstance() ? "local_events:" + this.config.getAccountId() : "local_events"));
            }
            return null;
        } catch (Throwable th) {
            getConfigLogger().verbose(getConfigAccountId(), "Failed to retrieve local event detail", th);
            return null;
        }
    }

    @Deprecated(since = "7.1.0")
    Map<String, EventDetail> getEventHistory(Context context) {
        try {
            Map<String, ?> all = StorageHelper.getPreferences(context, !this.config.isDefaultInstance() ? "local_events:" + this.config.getAccountId() : "local_events").getAll();
            HashMap map = new HashMap();
            for (String str : all.keySet()) {
                map.put(str, decodeEventDetails(str, all.get(str).toString()));
            }
            return map;
        } catch (Throwable th) {
            getConfigLogger().verbose(getConfigAccountId(), "Failed to retrieve local event history", th);
            return null;
        }
    }

    @Deprecated(since = "7.1.0")
    public void persistEvent(Context context, JSONObject jSONObject, int i) {
        if (jSONObject != null && i == 4) {
            try {
                persistEvent(context, jSONObject);
            } catch (Throwable th) {
                getConfigLogger().verbose(getConfigAccountId(), "Failed to sync with upstream", th);
            }
        }
    }

    public boolean persistUserEventLogsInBulk(Set<String> set) {
        HashSet hashSet = new HashSet();
        CollectionsKt.mapTo(set, hashSet, new Function1() { // from class: com.clevertap.android.sdk.LocalDataStore$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f$0.m4784x44b762d4((String) obj);
            }
        });
        return upsertUserEventLogsInBulk(hashSet);
    }

    /* renamed from: lambda$persistUserEventLogsInBulk$0$com-clevertap-android-sdk-LocalDataStore, reason: not valid java name */
    /* synthetic */ Pair m4784x44b762d4(String str) {
        return new Pair(str, getOrPutNormalizedEventName(str));
    }

    public boolean persistUserEventLog(String str) {
        if (str == null) {
            return false;
        }
        Logger logger = this.config.getLogger();
        String accountId = this.config.getAccountId();
        try {
            logger.verbose(accountId, "UserEventLog: Persisting EventLog for event " + str);
            if (isUserEventLogExists(str)) {
                logger.verbose(accountId, "UserEventLog: Updating EventLog for event " + str);
                return updateUserEventLog(str);
            }
            logger.verbose(accountId, "UserEventLog: Inserting EventLog for event " + str);
            return insertUserEventLog(str);
        } catch (Throwable th) {
            logger.verbose(accountId, "UserEventLog: Failed to insert user event log: for event" + str, th);
            return false;
        }
    }

    private boolean updateEventByDeviceIdAndNormalizedEventName(String str, String str2) {
        boolean zUpdateEventByDeviceIdAndNormalizedEventName = this.baseDatabaseManager.loadDBAdapter(this.context).userEventLogDAO().updateEventByDeviceIdAndNormalizedEventName(str, str2);
        getConfigLogger().verbose("updatedEventByDeviceID = " + zUpdateEventByDeviceIdAndNormalizedEventName);
        return zUpdateEventByDeviceIdAndNormalizedEventName;
    }

    public boolean updateUserEventLog(String str) {
        return updateEventByDeviceIdAndNormalizedEventName(this.deviceInfo.getDeviceID(), getOrPutNormalizedEventName(str));
    }

    private boolean upsertUserEventLogsInBulk(Set<Pair<String, String>> set) {
        boolean zUpsertEventsByDeviceIdAndNormalizedEventName = this.baseDatabaseManager.loadDBAdapter(this.context).userEventLogDAO().upsertEventsByDeviceIdAndNormalizedEventName(this.deviceInfo.getDeviceID(), set);
        getConfigLogger().verbose("upsertEventByDeviceID = " + zUpsertEventsByDeviceIdAndNormalizedEventName);
        return zUpsertEventsByDeviceIdAndNormalizedEventName;
    }

    private long insertEvent(String str, String str2, String str3) {
        long jInsertEvent = this.baseDatabaseManager.loadDBAdapter(this.context).userEventLogDAO().insertEvent(str, str2, str3);
        getConfigLogger().verbose("inserted rowId = " + jInsertEvent);
        return jInsertEvent;
    }

    private String getOrPutNormalizedEventName(final String str) {
        return (String) MapsKt.getOrPut((Map<String, V>) this.normalizedEventNames, str, new Function0() { // from class: com.clevertap.android.sdk.LocalDataStore$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Utils.getNormalizedName(str);
            }
        });
    }

    public boolean insertUserEventLog(String str) {
        return insertEvent(this.deviceInfo.getDeviceID(), str, getOrPutNormalizedEventName(str)) >= 0;
    }

    private boolean eventExistsByDeviceIdAndNormalizedEventName(String str, String str2) {
        boolean zEventExistsByDeviceIdAndNormalizedEventName = this.baseDatabaseManager.loadDBAdapter(this.context).userEventLogDAO().eventExistsByDeviceIdAndNormalizedEventName(str, str2);
        getConfigLogger().verbose("eventExists = " + zEventExistsByDeviceIdAndNormalizedEventName);
        return zEventExistsByDeviceIdAndNormalizedEventName;
    }

    public boolean isUserEventLogExists(String str) {
        return eventExistsByDeviceIdAndNormalizedEventName(this.deviceInfo.getDeviceID(), getOrPutNormalizedEventName(str));
    }

    private boolean eventExistsByDeviceIdAndNormalizedEventNameAndCount(String str, String str2, int i) {
        boolean zEventExistsByDeviceIdAndNormalizedEventNameAndCount = this.baseDatabaseManager.loadDBAdapter(this.context).userEventLogDAO().eventExistsByDeviceIdAndNormalizedEventNameAndCount(str, str2, i);
        getConfigLogger().verbose("eventExistsByDeviceIDAndCount = " + zEventExistsByDeviceIdAndNormalizedEventNameAndCount);
        return zEventExistsByDeviceIdAndNormalizedEventNameAndCount;
    }

    public boolean isUserEventLogFirstTime(String str) {
        String orPutNormalizedEventName = getOrPutNormalizedEventName(str);
        if (this.userNormalizedEventLogKeys.contains(orPutNormalizedEventName)) {
            return false;
        }
        int eventCountByDeviceIdAndNormalizedEventName = readEventCountByDeviceIdAndNormalizedEventName(this.deviceInfo.getDeviceID(), orPutNormalizedEventName);
        if (eventCountByDeviceIdAndNormalizedEventName > 1) {
            this.userNormalizedEventLogKeys.add(orPutNormalizedEventName);
        }
        return eventCountByDeviceIdAndNormalizedEventName == 1;
    }

    public boolean cleanUpExtraEvents(int i, int i2) {
        boolean zCleanUpExtraEvents = this.baseDatabaseManager.loadDBAdapter(this.context).userEventLogDAO().cleanUpExtraEvents(i, i2);
        getConfigLogger().verbose("cleanUpExtraEvents boolean= " + zCleanUpExtraEvents);
        return zCleanUpExtraEvents;
    }

    private UserEventLog readEventByDeviceIdAndNormalizedEventName(String str, String str2) {
        return this.baseDatabaseManager.loadDBAdapter(this.context).userEventLogDAO().readEventByDeviceIdAndNormalizedEventName(str, str2);
    }

    public UserEventLog readUserEventLog(String str) {
        return readEventByDeviceIdAndNormalizedEventName(this.deviceInfo.getDeviceID(), getOrPutNormalizedEventName(str));
    }

    private int readEventCountByDeviceIdAndNormalizedEventName(String str, String str2) {
        return this.baseDatabaseManager.loadDBAdapter(this.context).userEventLogDAO().readEventCountByDeviceIdAndNormalizedEventName(str, str2);
    }

    public int readUserEventLogCount(String str) {
        return readEventCountByDeviceIdAndNormalizedEventName(this.deviceInfo.getDeviceID(), getOrPutNormalizedEventName(str));
    }

    private List<UserEventLog> allEventsByDeviceID(String str) {
        return this.baseDatabaseManager.loadDBAdapter(this.context).userEventLogDAO().allEventsByDeviceID(str);
    }

    public List<UserEventLog> readUserEventLogs() {
        return allEventsByDeviceID(this.deviceInfo.getDeviceID());
    }

    public List<UserEventLog> readEventLogsForAllUsers() {
        return this.baseDatabaseManager.loadDBAdapter(this.context).userEventLogDAO().allEvents();
    }

    public void setDataSyncFlag(JSONObject jSONObject) {
        try {
            if (!this.config.isPersonalizationEnabled()) {
                jSONObject.put("dsync", false);
                return;
            }
            String string = jSONObject.getString("type");
            if ("event".equals(string) && Constants.APP_LAUNCHED_EVENT.equals(jSONObject.getString(Constants.KEY_EVT_NAME))) {
                getConfigLogger().verbose(getConfigAccountId(), "Local cache needs to be updated (triggered by App Launched)");
                jSONObject.put("dsync", true);
                return;
            }
            if ("profile".equals(string)) {
                jSONObject.put("dsync", true);
                getConfigLogger().verbose(getConfigAccountId(), "Local cache needs to be updated (profile event)");
                return;
            }
            int iCurrentTimeMillis = (int) (System.currentTimeMillis() / 1000);
            if (getIntFromPrefs("local_cache_last_update", iCurrentTimeMillis) + getLocalCacheExpiryInterval(1200) < iCurrentTimeMillis) {
                jSONObject.put("dsync", true);
                getConfigLogger().verbose(getConfigAccountId(), "Local cache needs to be updated");
            } else {
                jSONObject.put("dsync", false);
                getConfigLogger().verbose(getConfigAccountId(), "Local cache doesn't need to be updated");
            }
        } catch (Throwable th) {
            getConfigLogger().verbose(getConfigAccountId(), "Failed to sync with upstream", th);
        }
    }

    public Object getProfileProperty(String str) {
        if (str == null) {
            return null;
        }
        synchronized (this.PROFILE_FIELDS_IN_THIS_SESSION) {
            try {
                Object obj = this.PROFILE_FIELDS_IN_THIS_SESSION.get(str);
                if ((obj instanceof String) && CryptHandler.isTextEncrypted((String) obj)) {
                    getConfigLogger().verbose(getConfigAccountId(), "Failed to retrieve local profile property because it wasn't decrypted");
                    return null;
                }
                return this.PROFILE_FIELDS_IN_THIS_SESSION.get(str);
            } catch (Throwable th) {
                getConfigLogger().verbose(getConfigAccountId(), "Failed to retrieve local profile property", th);
                return null;
            }
        }
    }

    @Deprecated(since = "7.1.0")
    private EventDetail decodeEventDetails(String str, String str2) {
        if (str2 == null) {
            return null;
        }
        String[] strArrSplit = str2.split("\\|");
        return new EventDetail(Integer.parseInt(strArrSplit[0]), Integer.parseInt(strArrSplit[1]), Integer.parseInt(strArrSplit[2]), str);
    }

    @Deprecated(since = "7.1.0")
    private String encodeEventDetails(int i, int i2, int i3) {
        return i3 + "|" + i + "|" + i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getConfigAccountId() {
        return this.config.getAccountId();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Logger getConfigLogger() {
        return this.config.getLogger();
    }

    private int getIntFromPrefs(String str, int i) {
        if (this.config.isDefaultInstance()) {
            int i2 = StorageHelper.getInt(this.context, storageKeyWithSuffix(str), -1000);
            return i2 != -1000 ? i2 : StorageHelper.getInt(this.context, str, i);
        }
        return StorageHelper.getInt(this.context, storageKeyWithSuffix(str), i);
    }

    private int getLocalCacheExpiryInterval(int i) {
        return getIntFromPrefs("local_cache_expires_in", i);
    }

    @Deprecated(since = "7.1.0")
    private String getStringFromPrefs(String str, String str2, String str3) {
        if (this.config.isDefaultInstance()) {
            String string = StorageHelper.getString(this.context, str3, storageKeyWithSuffix(str), str2);
            return string != null ? string : StorageHelper.getString(this.context, str3, str, str2);
        }
        return StorageHelper.getString(this.context, str3, storageKeyWithSuffix(str), str2);
    }

    private String getUserProfileID() {
        return this.config.getAccountId();
    }

    void inflateLocalProfileAsync(final Context context) {
        final String accountId = this.config.getAccountId();
        postAsyncSafely("LocalDataStore#inflateLocalProfileAsync", new Runnable() { // from class: com.clevertap.android.sdk.LocalDataStore.1
            @Override // java.lang.Runnable
            public void run() {
                JSONObject jSONObjectFetchUserProfileByAccountIdAndDeviceID;
                String strDecrypt;
                DBAdapter dBAdapterLoadDBAdapter = LocalDataStore.this.baseDatabaseManager.loadDBAdapter(context);
                synchronized (LocalDataStore.this.PROFILE_FIELDS_IN_THIS_SESSION) {
                    try {
                        jSONObjectFetchUserProfileByAccountIdAndDeviceID = dBAdapterLoadDBAdapter.fetchUserProfileByAccountIdAndDeviceID(accountId, LocalDataStore.this.deviceInfo.getDeviceID());
                    } catch (Throwable unused) {
                    }
                    if (jSONObjectFetchUserProfileByAccountIdAndDeviceID == null) {
                        return;
                    }
                    Iterator<String> itKeys = jSONObjectFetchUserProfileByAccountIdAndDeviceID.keys();
                    while (itKeys.hasNext()) {
                        try {
                            String next = itKeys.next();
                            Object obj = jSONObjectFetchUserProfileByAccountIdAndDeviceID.get(next);
                            if (obj instanceof JSONObject) {
                                LocalDataStore.this.PROFILE_FIELDS_IN_THIS_SESSION.put(next, jSONObjectFetchUserProfileByAccountIdAndDeviceID.getJSONObject(next));
                            } else if (obj instanceof JSONArray) {
                                LocalDataStore.this.PROFILE_FIELDS_IN_THIS_SESSION.put(next, jSONObjectFetchUserProfileByAccountIdAndDeviceID.getJSONArray(next));
                            } else {
                                if ((obj instanceof String) && (strDecrypt = LocalDataStore.this.cryptHandler.decrypt((String) obj, next)) != null) {
                                    obj = strDecrypt;
                                }
                                LocalDataStore.this.PROFILE_FIELDS_IN_THIS_SESSION.put(next, obj);
                            }
                        } catch (JSONException unused2) {
                        }
                    }
                    LocalDataStore.this.getConfigLogger().verbose(LocalDataStore.this.getConfigAccountId(), "Local Data Store - Inflated local profile " + LocalDataStore.this.PROFILE_FIELDS_IN_THIS_SESSION);
                }
            }
        });
    }

    private boolean isPersonalisationEnabled() {
        return this.config.isPersonalizationEnabled();
    }

    @Deprecated(since = "7.1.0")
    private void persistEvent(Context context, JSONObject jSONObject) {
        try {
            String string = jSONObject.getString(Constants.KEY_EVT_NAME);
            if (string == null) {
                return;
            }
            String str = !this.config.isDefaultInstance() ? "local_events:" + this.config.getAccountId() : "local_events";
            SharedPreferences preferences = StorageHelper.getPreferences(context, str);
            int iCurrentTimeMillis = (int) (System.currentTimeMillis() / 1000);
            EventDetail eventDetailDecodeEventDetails = decodeEventDetails(string, getStringFromPrefs(string, encodeEventDetails(iCurrentTimeMillis, iCurrentTimeMillis, 0), str));
            String strEncodeEventDetails = encodeEventDetails(eventDetailDecodeEventDetails.getFirstTime(), iCurrentTimeMillis, eventDetailDecodeEventDetails.getCount() + 1);
            SharedPreferences.Editor editorEdit = preferences.edit();
            editorEdit.putString(storageKeyWithSuffix(string), strEncodeEventDetails);
            StorageHelper.persist(editorEdit);
        } catch (Throwable th) {
            getConfigLogger().verbose(getConfigAccountId(), "Failed to persist event locally", th);
        }
    }

    private void persistLocalProfileAsync() {
        final String accountId = this.config.getAccountId();
        postAsyncSafely("LocalDataStore#persistLocalProfileAsync", new Runnable() { // from class: com.clevertap.android.sdk.LocalDataStore.2
            @Override // java.lang.Runnable
            public void run() {
                synchronized (LocalDataStore.this.PROFILE_FIELDS_IN_THIS_SESSION) {
                    HashMap map = new HashMap(LocalDataStore.this.PROFILE_FIELDS_IN_THIS_SESSION);
                    Iterator<String> it = Constants.piiDBKeys.iterator();
                    boolean z = true;
                    while (it.hasNext()) {
                        String next = it.next();
                        if (map.get(next) != null) {
                            Object obj = map.get(next);
                            if (obj instanceof String) {
                                String strEncrypt = LocalDataStore.this.cryptHandler.encrypt((String) obj, next);
                                if (strEncrypt == null) {
                                    z = false;
                                } else {
                                    map.put(next, strEncrypt);
                                }
                            }
                        }
                    }
                    JSONObject jSONObject = new JSONObject(map);
                    if (!z) {
                        CryptUtils.updateEncryptionFlagOnFailure(LocalDataStore.this.context, LocalDataStore.this.config, 2, LocalDataStore.this.cryptHandler);
                    }
                    LocalDataStore.this.getConfigLogger().verbose(LocalDataStore.this.getConfigAccountId(), "Persist Local Profile complete with status " + LocalDataStore.this.baseDatabaseManager.loadDBAdapter(LocalDataStore.this.context).storeUserProfile(accountId, LocalDataStore.this.deviceInfo.getDeviceID(), jSONObject) + " for id " + accountId);
                }
            }
        });
    }

    private void postAsyncSafely(final String str, final Runnable runnable) {
        try {
            if (Thread.currentThread().getId() == EXECUTOR_THREAD_ID) {
                runnable.run();
            } else {
                this.es.submit(new Runnable() { // from class: com.clevertap.android.sdk.LocalDataStore.3
                    @Override // java.lang.Runnable
                    public void run() {
                        long unused = LocalDataStore.EXECUTOR_THREAD_ID = Thread.currentThread().getId();
                        try {
                            LocalDataStore.this.getConfigLogger().verbose(LocalDataStore.this.getConfigAccountId(), "Local Data Store Executor service: Starting task - " + str);
                            runnable.run();
                        } catch (Throwable th) {
                            LocalDataStore.this.getConfigLogger().verbose(LocalDataStore.this.getConfigAccountId(), "Executor service: Failed to complete the scheduled task", th);
                        }
                    }
                });
            }
        } catch (Throwable th) {
            getConfigLogger().verbose(getConfigAccountId(), "Failed to submit task to the executor service", th);
        }
    }

    private boolean profileValueIsEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        boolean z = (obj instanceof String) && ((String) obj).trim().length() == 0;
        if (obj instanceof JSONArray) {
            return ((JSONArray) obj).length() <= 0;
        }
        return z;
    }

    private Boolean profileValuesAreEqual(Object obj, Object obj2) {
        return Boolean.valueOf(stringify(obj).equals(stringify(obj2)));
    }

    private void resetLocalProfileSync() {
        synchronized (this.PROFILE_FIELDS_IN_THIS_SESSION) {
            this.PROFILE_FIELDS_IN_THIS_SESSION.clear();
        }
        inflateLocalProfileAsync(this.context);
    }

    private void _removeProfileField(String str) {
        synchronized (this.PROFILE_FIELDS_IN_THIS_SESSION) {
            try {
                this.PROFILE_FIELDS_IN_THIS_SESSION.remove(str);
            } finally {
            }
        }
    }

    private void _setProfileField(String str, Object obj) {
        if (obj == null) {
            return;
        }
        try {
            synchronized (this.PROFILE_FIELDS_IN_THIS_SESSION) {
                this.PROFILE_FIELDS_IN_THIS_SESSION.put(str, obj);
            }
        } catch (Throwable th) {
            getConfigLogger().verbose(getConfigAccountId(), "Failed to set local profile value for key " + str, th);
        }
    }

    public void updateProfileFields(Map<String, Object> map) {
        if (map.isEmpty()) {
            return;
        }
        long jNanoTime = System.nanoTime();
        persistUserEventLogsInBulk(map.keySet());
        this.config.getLogger().verbose(this.config.getAccountId(), "UserEventLog: persistUserEventLog execution time = " + (System.nanoTime() - jNanoTime) + " nano seconds");
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value == null) {
                _removeProfileField(key);
            }
            _setProfileField(key, value);
        }
        persistLocalProfileAsync();
    }

    private String storageKeyWithSuffix(String str) {
        return str + ":" + this.config.getAccountId();
    }

    private String stringify(Object obj) {
        return obj == null ? "" : obj.toString();
    }
}
