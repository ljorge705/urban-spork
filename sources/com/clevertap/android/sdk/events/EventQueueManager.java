package com.clevertap.android.sdk.events;

import android.content.Context;
import android.location.Location;
import com.clevertap.android.sdk.BaseCallbackManager;
import com.clevertap.android.sdk.CTLockManager;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.ControllerManager;
import com.clevertap.android.sdk.CoreMetaData;
import com.clevertap.android.sdk.DeviceInfo;
import com.clevertap.android.sdk.FailureFlushListener;
import com.clevertap.android.sdk.LocalDataStore;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.SessionManager;
import com.clevertap.android.sdk.Utils;
import com.clevertap.android.sdk.cryption.CryptHandler;
import com.clevertap.android.sdk.db.BaseDatabaseManager;
import com.clevertap.android.sdk.login.IdentityRepo;
import com.clevertap.android.sdk.login.IdentityRepoFactory;
import com.clevertap.android.sdk.login.LoginInfoProvider;
import com.clevertap.android.sdk.network.NetworkManager;
import com.clevertap.android.sdk.task.CTExecutorFactory;
import com.clevertap.android.sdk.task.MainLooperHandler;
import com.clevertap.android.sdk.utils.CTJsonConverter;
import com.clevertap.android.sdk.validation.ValidationResult;
import com.clevertap.android.sdk.validation.ValidationResultStack;
import java.util.Iterator;
import java.util.TimeZone;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class EventQueueManager extends BaseEventQueueManager implements FailureFlushListener {
    private final BaseDatabaseManager baseDatabaseManager;
    private final CoreMetaData cleverTapMetaData;
    private final CleverTapInstanceConfig config;
    private final Context context;
    private final ControllerManager controllerManager;
    private final CryptHandler cryptHandler;
    private final CTLockManager ctLockManager;
    private final DeviceInfo deviceInfo;
    private final EventMediator eventMediator;
    private final LocalDataStore localDataStore;
    private final Logger logger;
    private LoginInfoProvider loginInfoProvider;
    private final MainLooperHandler mainLooperHandler;
    private final NetworkManager networkManager;
    private final SessionManager sessionManager;
    private final ValidationResultStack validationResultStack;
    private Runnable commsRunnable = null;
    private Runnable pushNotificationViewedRunnable = null;

    public LoginInfoProvider getLoginInfoProvider() {
        return this.loginInfoProvider;
    }

    public void setLoginInfoProvider(LoginInfoProvider loginInfoProvider) {
        this.loginInfoProvider = loginInfoProvider;
    }

    public EventQueueManager(BaseDatabaseManager baseDatabaseManager, Context context, CleverTapInstanceConfig cleverTapInstanceConfig, EventMediator eventMediator, SessionManager sessionManager, BaseCallbackManager baseCallbackManager, MainLooperHandler mainLooperHandler, DeviceInfo deviceInfo, ValidationResultStack validationResultStack, NetworkManager networkManager, CoreMetaData coreMetaData, CTLockManager cTLockManager, LocalDataStore localDataStore, ControllerManager controllerManager, CryptHandler cryptHandler) {
        this.baseDatabaseManager = baseDatabaseManager;
        this.context = context;
        this.config = cleverTapInstanceConfig;
        this.eventMediator = eventMediator;
        this.sessionManager = sessionManager;
        this.mainLooperHandler = mainLooperHandler;
        this.deviceInfo = deviceInfo;
        this.validationResultStack = validationResultStack;
        this.networkManager = networkManager;
        this.localDataStore = localDataStore;
        this.logger = cleverTapInstanceConfig.getLogger();
        this.cleverTapMetaData = coreMetaData;
        this.ctLockManager = cTLockManager;
        this.controllerManager = controllerManager;
        this.cryptHandler = cryptHandler;
        baseCallbackManager.setFailureFlushListener(this);
    }

    @Override // com.clevertap.android.sdk.events.BaseEventQueueManager
    public void addToQueue(Context context, JSONObject jSONObject, int i) throws JSONException {
        if (i == 6) {
            this.config.getLogger().verbose(this.config.getAccountId(), "Pushing Notification Viewed event onto separate queue");
            processPushNotificationViewedEvent(context, jSONObject, i);
        } else if (i == 8) {
            processDefineVarsEvent(context, jSONObject);
        } else {
            processEvent(context, jSONObject, i);
        }
    }

    private void processDefineVarsEvent(Context context, JSONObject jSONObject) throws JSONException {
        sendImmediately(context, EventGroup.VARIABLES, jSONObject);
    }

    @Override // com.clevertap.android.sdk.FailureFlushListener
    public void failureFlush(Context context) {
        scheduleQueueFlush(context);
    }

    @Override // com.clevertap.android.sdk.events.BaseEventQueueManager
    public void flush() {
        flushQueueAsync(this.context, EventGroup.REGULAR);
    }

    @Override // com.clevertap.android.sdk.events.BaseEventQueueManager
    public void flushQueueAsync(final Context context, final EventGroup eventGroup) {
        CTExecutorFactory.executors(this.config).postAsyncSafelyTask().execute("CommsManager#flushQueueAsync", new Callable<Void>() { // from class: com.clevertap.android.sdk.events.EventQueueManager.1
            @Override // java.util.concurrent.Callable
            public Void call() throws JSONException {
                if (eventGroup == EventGroup.PUSH_NOTIFICATION_VIEWED) {
                    EventQueueManager.this.logger.verbose(EventQueueManager.this.config.getAccountId(), "Pushing Notification Viewed event onto queue flush sync");
                } else {
                    EventQueueManager.this.logger.verbose(EventQueueManager.this.config.getAccountId(), "Pushing event onto queue flush sync");
                }
                EventQueueManager.this.flushQueueSync(context, eventGroup);
                return null;
            }
        });
    }

    @Override // com.clevertap.android.sdk.events.BaseEventQueueManager
    public void flushQueueSync(Context context, EventGroup eventGroup) throws JSONException {
        flushQueueSync(context, eventGroup, null);
    }

    @Override // com.clevertap.android.sdk.events.BaseEventQueueManager
    public void flushQueueSync(final Context context, final EventGroup eventGroup, final String str) throws JSONException {
        if (!NetworkManager.isNetworkOnline(context)) {
            this.logger.verbose(this.config.getAccountId(), "Network connectivity unavailable. Will retry later");
            this.controllerManager.invokeCallbacksForNetworkError();
            this.controllerManager.invokeBatchListener(new JSONArray(), false);
        } else if (this.cleverTapMetaData.isOffline()) {
            this.logger.debug(this.config.getAccountId(), "CleverTap Instance has been set to offline, won't send events queue");
            this.controllerManager.invokeCallbacksForNetworkError();
            this.controllerManager.invokeBatchListener(new JSONArray(), false);
        } else if (this.networkManager.needsHandshakeForDomain(eventGroup)) {
            this.networkManager.initHandshake(eventGroup, new Runnable() { // from class: com.clevertap.android.sdk.events.EventQueueManager.2
                @Override // java.lang.Runnable
                public void run() throws JSONException {
                    EventQueueManager.this.networkManager.flushDBQueue(context, eventGroup, str);
                }
            });
        } else {
            this.logger.verbose(this.config.getAccountId(), "Pushing Notification Viewed event onto queue DB flush");
            this.networkManager.flushDBQueue(context, eventGroup, str);
        }
    }

    @Override // com.clevertap.android.sdk.events.BaseEventQueueManager
    public void sendImmediately(final Context context, final EventGroup eventGroup, JSONObject jSONObject) throws JSONException {
        if (!NetworkManager.isNetworkOnline(context)) {
            this.logger.verbose(this.config.getAccountId(), "Network connectivity unavailable. Event won't be sent.");
            return;
        }
        if (this.cleverTapMetaData.isOffline()) {
            this.logger.debug(this.config.getAccountId(), "CleverTap Instance has been set to offline, won't send event");
            return;
        }
        final JSONArray jSONArrayPut = new JSONArray().put(jSONObject);
        if (this.networkManager.needsHandshakeForDomain(eventGroup)) {
            this.networkManager.initHandshake(eventGroup, new Runnable() { // from class: com.clevertap.android.sdk.events.EventQueueManager$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() throws JSONException {
                    this.f$0.m4787x89decd0f(context, eventGroup, jSONArrayPut);
                }
            });
        } else {
            this.networkManager.sendQueue(context, eventGroup, jSONArrayPut, null);
        }
    }

    /* renamed from: lambda$sendImmediately$0$com-clevertap-android-sdk-events-EventQueueManager, reason: not valid java name */
    /* synthetic */ void m4787x89decd0f(Context context, EventGroup eventGroup, JSONArray jSONArray) throws JSONException {
        this.networkManager.sendQueue(context, eventGroup, jSONArray, null);
    }

    public int getNow() {
        return (int) (System.currentTimeMillis() / 1000);
    }

    public void processEvent(Context context, JSONObject jSONObject, int i) {
        String str;
        synchronized (this.ctLockManager.getEventLock()) {
            try {
                if (CoreMetaData.getActivityCount() == 0) {
                    CoreMetaData.setActivityCount(1);
                }
                if (i == 1) {
                    str = "page";
                } else if (i == 2) {
                    str = "ping";
                    attachMeta(jSONObject, context);
                    if (jSONObject.has("bk")) {
                        this.cleverTapMetaData.setBgPing(true);
                        jSONObject.remove("bk");
                    }
                    if (this.cleverTapMetaData.isLocationForGeofence()) {
                        jSONObject.put("gf", true);
                        this.cleverTapMetaData.setLocationForGeofence(false);
                        jSONObject.put("gfSDKVersion", this.cleverTapMetaData.getGeofenceSDKVersion());
                        this.cleverTapMetaData.setGeofenceSDKVersion(0);
                    }
                } else {
                    str = i == 3 ? "profile" : i == 5 ? "data" : "event";
                }
                String screenName = this.cleverTapMetaData.getScreenName();
                if (screenName != null) {
                    jSONObject.put("n", screenName);
                }
                jSONObject.put("s", this.cleverTapMetaData.getCurrentSessionId());
                jSONObject.put("pg", CoreMetaData.getActivityCount());
                jSONObject.put("type", str);
                jSONObject.put("ep", getNow());
                jSONObject.put("f", this.cleverTapMetaData.isFirstSession());
                jSONObject.put("lsl", this.cleverTapMetaData.getLastSessionLength());
                attachPackageNameIfRequired(context, jSONObject);
                ValidationResult validationResultPopValidationResult = this.validationResultStack.popValidationResult();
                if (validationResultPopValidationResult != null) {
                    jSONObject.put(Constants.ERROR_KEY, CTJsonConverter.getErrorObject(validationResultPopValidationResult));
                }
                this.localDataStore.setDataSyncFlag(jSONObject);
                this.baseDatabaseManager.queueEventToDB(context, jSONObject, i);
                initInAppEvaluation(context, jSONObject, i);
                scheduleQueueFlush(context);
            } finally {
            }
        }
    }

    public void initInAppEvaluation(Context context, JSONObject jSONObject, int i) throws JSONException {
        String eventName = this.eventMediator.getEventName(jSONObject);
        Location locationFromUser = this.cleverTapMetaData.getLocationFromUser();
        updateLocalStore(eventName, i);
        if (this.eventMediator.isChargedEvent(jSONObject)) {
            this.controllerManager.getInAppController().onQueueChargedEvent(this.eventMediator.getChargedEventDetails(jSONObject), this.eventMediator.getChargedEventItemDetails(jSONObject), locationFromUser);
            return;
        }
        if (!NetworkManager.isNetworkOnline(context) && this.eventMediator.isEvent(jSONObject)) {
            this.controllerManager.getInAppController().onQueueEvent(eventName, this.eventMediator.getEventProperties(jSONObject), locationFromUser);
            return;
        }
        if (i == 3) {
            this.controllerManager.getInAppController().onQueueProfileEvent(this.eventMediator.computeUserAttributeChangeProperties(jSONObject), locationFromUser);
        } else {
            if (this.eventMediator.isAppLaunchedEvent(jSONObject) || !this.eventMediator.isEvent(jSONObject)) {
                return;
            }
            this.controllerManager.getInAppController().onQueueEvent(eventName, this.eventMediator.getEventProperties(jSONObject), locationFromUser);
        }
    }

    public void processPushNotificationViewedEvent(Context context, JSONObject jSONObject, int i) {
        synchronized (this.ctLockManager.getEventLock()) {
            try {
                jSONObject.put("s", this.cleverTapMetaData.getCurrentSessionId());
                jSONObject.put("type", "event");
                jSONObject.put("ep", getNow());
                ValidationResult validationResultPopValidationResult = this.validationResultStack.popValidationResult();
                if (validationResultPopValidationResult != null) {
                    jSONObject.put(Constants.ERROR_KEY, CTJsonConverter.getErrorObject(validationResultPopValidationResult));
                }
                this.config.getLogger().verbose(this.config.getAccountId(), "Pushing Notification Viewed event onto DB");
                this.baseDatabaseManager.queuePushNotificationViewedEventToDB(context, jSONObject);
                initInAppEvaluation(context, jSONObject, i);
                this.config.getLogger().verbose(this.config.getAccountId(), "Pushing Notification Viewed event onto queue flush");
                schedulePushNotificationViewedQueueFlush(context);
            } finally {
            }
        }
    }

    @Override // com.clevertap.android.sdk.events.BaseEventQueueManager
    public void pushBasicProfile(JSONObject jSONObject, boolean z) {
        Object jSONObject2;
        try {
            String cleverTapID = getCleverTapID();
            JSONObject jSONObject3 = new JSONObject();
            if (jSONObject != null && jSONObject.length() > 0) {
                Iterator<String> itKeys = jSONObject.keys();
                IdentityRepo repo = IdentityRepoFactory.getRepo(this.context, this.config, this.deviceInfo, this.validationResultStack);
                setLoginInfoProvider(new LoginInfoProvider(this.context, this.config, this.deviceInfo, this.cryptHandler));
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    try {
                        try {
                            jSONObject2 = jSONObject.getJSONObject(next);
                        } catch (Throwable unused) {
                            jSONObject2 = jSONObject.get(next);
                        }
                    } catch (JSONException unused2) {
                        jSONObject2 = null;
                    }
                    if (jSONObject2 != null) {
                        jSONObject3.put(next, jSONObject2);
                        boolean zHasIdentity = repo.hasIdentity(next);
                        if (zHasIdentity && z) {
                            try {
                                getLoginInfoProvider().removeValueFromCachedGUIDForIdentifier(cleverTapID, next);
                            } catch (Throwable unused3) {
                            }
                        } else if (zHasIdentity) {
                            getLoginInfoProvider().cacheGUIDForIdentifier(cleverTapID, next, jSONObject2.toString());
                        }
                    }
                }
            }
            try {
                String carrier = this.deviceInfo.getCarrier();
                if (carrier != null && !carrier.equals("")) {
                    jSONObject3.put(Constants.CLTAP_CARRIER, carrier);
                }
                String countryCode = this.deviceInfo.getCountryCode();
                if (countryCode != null && !countryCode.equals("")) {
                    jSONObject3.put("cc", countryCode);
                }
                jSONObject3.put("tz", TimeZone.getDefault().getID());
                JSONObject jSONObject4 = new JSONObject();
                jSONObject4.put("profile", jSONObject3);
                queueEvent(this.context, jSONObject4, 3);
            } catch (JSONException unused4) {
                this.config.getLogger().verbose(this.config.getAccountId(), "FATAL: Creating basic profile update event failed!");
            }
        } catch (Throwable th) {
            this.config.getLogger().verbose(this.config.getAccountId(), "Basic profile sync", th);
        }
    }

    @Override // com.clevertap.android.sdk.events.BaseEventQueueManager
    public void pushInitialEventsAsync() {
        if (this.cleverTapMetaData.inCurrentSession()) {
            return;
        }
        CTExecutorFactory.executors(this.config).postAsyncSafelyTask().execute("CleverTapAPI#pushInitialEventsAsync", new Callable<Void>() { // from class: com.clevertap.android.sdk.events.EventQueueManager.3
            @Override // java.util.concurrent.Callable
            public Void call() {
                try {
                    EventQueueManager.this.config.getLogger().verbose(EventQueueManager.this.config.getAccountId(), "Queuing daily events");
                    EventQueueManager.this.pushBasicProfile(null, false);
                } catch (Throwable th) {
                    EventQueueManager.this.config.getLogger().verbose(EventQueueManager.this.config.getAccountId(), "Daily profile sync failed", th);
                }
                return null;
            }
        });
    }

    /* renamed from: com.clevertap.android.sdk.events.EventQueueManager$4, reason: invalid class name */
    class AnonymousClass4 implements Callable<Void> {
        final /* synthetic */ Context val$context;
        final /* synthetic */ JSONObject val$event;
        final /* synthetic */ int val$eventType;

        AnonymousClass4(JSONObject jSONObject, int i, Context context) {
            this.val$event = jSONObject;
            this.val$eventType = i;
            this.val$context = context;
        }

        @Override // java.util.concurrent.Callable
        public Void call() throws JSONException {
            if (EventQueueManager.this.eventMediator.shouldDropEvent(this.val$event, this.val$eventType)) {
                return null;
            }
            if (EventQueueManager.this.eventMediator.shouldDeferProcessingEvent(this.val$event, this.val$eventType)) {
                EventQueueManager.this.config.getLogger().debug(EventQueueManager.this.config.getAccountId(), "App Launched not yet processed, re-queuing event " + this.val$event + "after 2s");
                MainLooperHandler mainLooperHandler = EventQueueManager.this.mainLooperHandler;
                final Context context = this.val$context;
                final JSONObject jSONObject = this.val$event;
                final int i = this.val$eventType;
                mainLooperHandler.postDelayed(new Runnable() { // from class: com.clevertap.android.sdk.events.EventQueueManager$4$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.m4788x8be2abfd(context, jSONObject, i);
                    }
                }, 2000L);
            } else {
                int i2 = this.val$eventType;
                if (i2 != 7 && i2 != 6) {
                    EventQueueManager.this.sessionManager.lazyCreateSession(this.val$context);
                    EventQueueManager.this.pushInitialEventsAsync();
                    EventQueueManager.this.addToQueue(this.val$context, this.val$event, this.val$eventType);
                } else {
                    EventQueueManager.this.addToQueue(this.val$context, this.val$event, i2);
                }
            }
            return null;
        }

        /* renamed from: lambda$call$0$com-clevertap-android-sdk-events-EventQueueManager$4, reason: not valid java name */
        /* synthetic */ void m4788x8be2abfd(final Context context, final JSONObject jSONObject, final int i) {
            CTExecutorFactory.executors(EventQueueManager.this.config).postAsyncSafelyTask().execute("queueEventWithDelay", new Callable<Void>() { // from class: com.clevertap.android.sdk.events.EventQueueManager.4.1
                @Override // java.util.concurrent.Callable
                public Void call() throws JSONException {
                    EventQueueManager.this.sessionManager.lazyCreateSession(context);
                    EventQueueManager.this.pushInitialEventsAsync();
                    EventQueueManager.this.addToQueue(context, jSONObject, i);
                    return null;
                }
            });
        }
    }

    @Override // com.clevertap.android.sdk.events.BaseEventQueueManager
    public Future<?> queueEvent(Context context, JSONObject jSONObject, int i) {
        return CTExecutorFactory.executors(this.config).postAsyncSafelyTask().submit("queueEvent", new AnonymousClass4(jSONObject, i, context));
    }

    @Override // com.clevertap.android.sdk.events.BaseEventQueueManager
    public void scheduleQueueFlush(final Context context) {
        if (this.commsRunnable == null) {
            this.commsRunnable = new Runnable() { // from class: com.clevertap.android.sdk.events.EventQueueManager.5
                @Override // java.lang.Runnable
                public void run() {
                    EventQueueManager.this.flushQueueAsync(context, EventGroup.REGULAR);
                    EventQueueManager.this.flushQueueAsync(context, EventGroup.PUSH_NOTIFICATION_VIEWED);
                }
            };
        }
        this.mainLooperHandler.removeCallbacks(this.commsRunnable);
        this.mainLooperHandler.postDelayed(this.commsRunnable, this.networkManager.getDelayFrequency());
        this.logger.verbose(this.config.getAccountId(), "Scheduling delayed queue flush on main event loop");
    }

    private void attachMeta(JSONObject jSONObject, Context context) {
        try {
            jSONObject.put("mc", Utils.getMemoryConsumption());
        } catch (Throwable unused) {
        }
        try {
            jSONObject.put(Constants.NOTIF_TITLE, Utils.getCurrentNetworkType(context));
        } catch (Throwable unused2) {
        }
    }

    private void attachPackageNameIfRequired(Context context, JSONObject jSONObject) {
        try {
            if ("event".equals(jSONObject.getString("type")) && Constants.APP_LAUNCHED_EVENT.equals(jSONObject.getString(Constants.KEY_EVT_NAME))) {
                jSONObject.put("pai", context.getPackageName());
            }
        } catch (Throwable unused) {
        }
    }

    private String getCleverTapID() {
        return this.deviceInfo.getDeviceID();
    }

    private void schedulePushNotificationViewedQueueFlush(final Context context) {
        if (this.pushNotificationViewedRunnable == null) {
            this.pushNotificationViewedRunnable = new Runnable() { // from class: com.clevertap.android.sdk.events.EventQueueManager.6
                @Override // java.lang.Runnable
                public void run() {
                    EventQueueManager.this.config.getLogger().verbose(EventQueueManager.this.config.getAccountId(), "Pushing Notification Viewed event onto queue flush async");
                    EventQueueManager.this.flushQueueAsync(context, EventGroup.PUSH_NOTIFICATION_VIEWED);
                }
            };
        }
        this.mainLooperHandler.removeCallbacks(this.pushNotificationViewedRunnable);
        this.mainLooperHandler.post(this.pushNotificationViewedRunnable);
    }

    private void updateLocalStore(String str, int i) {
        if (i == 4) {
            this.localDataStore.persistUserEventLog(str);
        }
    }
}
