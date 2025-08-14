package com.clevertap.android.sdk.pushnotification;

import android.app.job.JobScheduler;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.work.Constraints;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import com.clevertap.android.sdk.AnalyticsManager;
import com.clevertap.android.sdk.CleverTapAPI;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.ControllerManager;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.StorageHelper;
import com.clevertap.android.sdk.db.BaseDatabaseManager;
import com.clevertap.android.sdk.db.DBAdapter;
import com.clevertap.android.sdk.pushnotification.PushConstants;
import com.clevertap.android.sdk.pushnotification.amp.CTPushAmpWorker;
import com.clevertap.android.sdk.pushnotification.work.CTWorkManager;
import com.clevertap.android.sdk.task.CTExecutorFactory;
import com.clevertap.android.sdk.task.OnSuccessListener;
import com.clevertap.android.sdk.task.Task;
import com.clevertap.android.sdk.validation.ValidationResultStack;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class PushProviders implements CTPushProviderListener {
    private static final int DEFAULT_FLEX_INTERVAL = 5;
    private static final String PF_JOB_ID = "pfjobid";
    private static final String PF_WORK_ID = "pfworkid";
    private static final String PING_FREQUENCY = "pf";
    private static final int PING_FREQUENCY_VALUE = 240;
    private static final String inputFormat = "HH:mm";
    private final AnalyticsManager analyticsManager;
    private final BaseDatabaseManager baseDatabaseManager;
    private final CleverTapInstanceConfig config;
    private final Context context;
    private final CTWorkManager ctWorkManager;
    private CleverTapAPI.DevicePushTokenRefreshListener tokenRefreshListener;
    private final ValidationResultStack validationResultStack;
    private final ArrayList<PushConstants.PushType> allEnabledPushTypes = new ArrayList<>();
    private final ArrayList<PushConstants.PushType> allDisabledPushTypes = new ArrayList<>();
    private final ArrayList<CTPushProvider> availableCTPushProviders = new ArrayList<>();
    private final ArrayList<PushConstants.PushType> customEnabledPushTypes = new ArrayList<>();
    private INotificationRenderer iNotificationRenderer = new CoreNotificationRenderer();
    private final Object tokenLock = new Object();
    private final Object pushRenderingLock = new Object();

    public CleverTapAPI.DevicePushTokenRefreshListener getDevicePushTokenRefreshListener() {
        return this.tokenRefreshListener;
    }

    public INotificationRenderer getPushNotificationRenderer() {
        return this.iNotificationRenderer;
    }

    public Object getPushRenderingLock() {
        return this.pushRenderingLock;
    }

    public void setDevicePushTokenRefreshListener(CleverTapAPI.DevicePushTokenRefreshListener devicePushTokenRefreshListener) {
        this.tokenRefreshListener = devicePushTokenRefreshListener;
    }

    public void setPushNotificationRenderer(INotificationRenderer iNotificationRenderer) {
        this.iNotificationRenderer = iNotificationRenderer;
    }

    public static PushProviders load(Context context, CleverTapInstanceConfig cleverTapInstanceConfig, BaseDatabaseManager baseDatabaseManager, ValidationResultStack validationResultStack, AnalyticsManager analyticsManager, ControllerManager controllerManager, CTWorkManager cTWorkManager) throws ClassNotFoundException {
        PushProviders pushProviders = new PushProviders(context, cleverTapInstanceConfig, baseDatabaseManager, validationResultStack, analyticsManager, cTWorkManager);
        pushProviders.init();
        controllerManager.setPushProviders(pushProviders);
        return pushProviders;
    }

    private PushProviders(Context context, CleverTapInstanceConfig cleverTapInstanceConfig, BaseDatabaseManager baseDatabaseManager, ValidationResultStack validationResultStack, AnalyticsManager analyticsManager, CTWorkManager cTWorkManager) {
        this.context = context;
        this.config = cleverTapInstanceConfig;
        this.baseDatabaseManager = baseDatabaseManager;
        this.validationResultStack = validationResultStack;
        this.analyticsManager = analyticsManager;
        this.ctWorkManager = cTWorkManager;
        initPushAmp();
    }

    public void _createNotification(Context context, Bundle bundle, int i) {
        if (bundle == null || bundle.get("wzrk_pn") == null) {
            return;
        }
        if (this.config.isAnalyticsOnly()) {
            this.config.getLogger().debug(this.config.getAccountId(), "Instance is set for Analytics only, cannot create notification");
            return;
        }
        try {
            if (bundle.getString(Constants.WZRK_PUSH_SILENT, "").equalsIgnoreCase("true")) {
                this.analyticsManager.pushNotificationViewedEvent(bundle);
                return;
            }
            String string = bundle.getString(Constants.EXTRAS_FROM);
            if (string == null || !string.equals("PTReceiver")) {
                this.config.getLogger().debug(this.config.getAccountId(), "Handling notification: " + bundle);
                if (bundle.getString(Constants.WZRK_PUSH_ID) != null && this.baseDatabaseManager.loadDBAdapter(context).doesPushNotificationIdExist(bundle.getString(Constants.WZRK_PUSH_ID))) {
                    this.config.getLogger().debug(this.config.getAccountId(), "Push Notification already rendered, not showing again");
                    return;
                }
                String message = this.iNotificationRenderer.getMessage(bundle);
                if (message == null) {
                    message = "";
                }
                if (message.isEmpty()) {
                    this.config.getLogger().verbose(this.config.getAccountId(), "Push notification message is empty, not rendering");
                    this.baseDatabaseManager.loadDBAdapter(context).storeUninstallTimestamp();
                    String string2 = bundle.getString(PING_FREQUENCY, "");
                    if (TextUtils.isEmpty(string2)) {
                        return;
                    }
                    updatePingFrequencyIfNeeded(context, Integer.parseInt(string2));
                    return;
                }
            }
            if (this.iNotificationRenderer.getTitle(bundle, context).isEmpty()) {
                String str = context.getApplicationInfo().name;
            }
            triggerNotification(context, bundle, i);
        } catch (Throwable th) {
            this.config.getLogger().debug(this.config.getAccountId(), "Couldn't render notification: ", th);
        }
    }

    public void cacheToken(final String str, final PushConstants.PushType pushType) {
        if (TextUtils.isEmpty(str) || pushType == null) {
            return;
        }
        try {
            CTExecutorFactory.executors(this.config).ioTask().execute("PushProviders#cacheToken", new Callable<Void>() { // from class: com.clevertap.android.sdk.pushnotification.PushProviders.1
                @Override // java.util.concurrent.Callable
                public Void call() {
                    if (PushProviders.this.alreadyHaveToken(str, pushType)) {
                        return null;
                    }
                    String tokenPrefKey = pushType.getTokenPrefKey();
                    if (TextUtils.isEmpty(tokenPrefKey)) {
                        return null;
                    }
                    StorageHelper.putStringImmediate(PushProviders.this.context, StorageHelper.storageKeyWithSuffix(PushProviders.this.config, tokenPrefKey), str);
                    PushProviders.this.config.log(PushConstants.LOG_TAG, pushType + "Cached New Token successfully " + str);
                    return null;
                }
            });
        } catch (Throwable th) {
            this.config.log(PushConstants.LOG_TAG, pushType + "Unable to cache token " + str, th);
        }
    }

    public void doTokenRefresh(String str, PushConstants.PushType pushType) {
        if (TextUtils.isEmpty(str) || pushType == null) {
            return;
        }
        int i = AnonymousClass6.$SwitchMap$com$clevertap$android$sdk$pushnotification$PushConstants$PushType[pushType.ordinal()];
        if (i == 1) {
            handleToken(str, PushConstants.PushType.FCM, true);
            return;
        }
        if (i == 2) {
            handleToken(str, PushConstants.PushType.HPS, true);
        } else if (i == 3) {
            handleToken(str, PushConstants.PushType.BPS, true);
        } else {
            if (i != 4) {
                return;
            }
            handleToken(str, PushConstants.PushType.ADM, true);
        }
    }

    /* renamed from: com.clevertap.android.sdk.pushnotification.PushProviders$6, reason: invalid class name */
    static /* synthetic */ class AnonymousClass6 {
        static final /* synthetic */ int[] $SwitchMap$com$clevertap$android$sdk$pushnotification$PushConstants$PushType;

        static {
            int[] iArr = new int[PushConstants.PushType.values().length];
            $SwitchMap$com$clevertap$android$sdk$pushnotification$PushConstants$PushType = iArr;
            try {
                iArr[PushConstants.PushType.FCM.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$clevertap$android$sdk$pushnotification$PushConstants$PushType[PushConstants.PushType.HPS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$clevertap$android$sdk$pushnotification$PushConstants$PushType[PushConstants.PushType.BPS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$clevertap$android$sdk$pushnotification$PushConstants$PushType[PushConstants.PushType.ADM.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public void forcePushDeviceToken(boolean z) {
        Iterator<PushConstants.PushType> it = this.allEnabledPushTypes.iterator();
        while (it.hasNext()) {
            pushDeviceTokenEvent(null, z, it.next());
        }
    }

    public ArrayList<PushConstants.PushType> getAvailablePushTypes() {
        ArrayList<PushConstants.PushType> arrayList = new ArrayList<>();
        Iterator<CTPushProvider> it = this.availableCTPushProviders.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getPushType());
        }
        return arrayList;
    }

    public String getCachedToken(PushConstants.PushType pushType) {
        if (pushType != null) {
            String tokenPrefKey = pushType.getTokenPrefKey();
            if (!TextUtils.isEmpty(tokenPrefKey)) {
                String stringFromPrefs = StorageHelper.getStringFromPrefs(this.context, this.config, tokenPrefKey, null);
                this.config.log(PushConstants.LOG_TAG, pushType + "getting Cached Token - " + stringFromPrefs);
                return stringFromPrefs;
            }
        }
        if (pushType != null) {
            this.config.log(PushConstants.LOG_TAG, pushType + " Unable to find cached Token for type ");
        }
        return null;
    }

    public void handleToken(String str, PushConstants.PushType pushType, boolean z) {
        if (z) {
            registerToken(str, pushType);
        } else {
            unregisterToken(str, pushType);
        }
    }

    public boolean isNotificationSupported() {
        Iterator<PushConstants.PushType> it = getAvailablePushTypes().iterator();
        while (it.hasNext()) {
            if (getCachedToken(it.next()) != null) {
                return true;
            }
        }
        return false;
    }

    @Override // com.clevertap.android.sdk.pushnotification.CTPushProviderListener
    public void onNewToken(String str, PushConstants.PushType pushType) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        doTokenRefresh(str, pushType);
        deviceTokenDidRefresh(str, pushType);
    }

    public void onTokenRefresh() {
        refreshAllTokens();
    }

    public void processCustomPushNotification(final Bundle bundle) {
        CTExecutorFactory.executors(this.config).postAsyncSafelyTask().execute("customHandlePushAmplification", new Callable<Void>() { // from class: com.clevertap.android.sdk.pushnotification.PushProviders.2
            @Override // java.util.concurrent.Callable
            public Void call() throws NumberFormatException {
                String string = bundle.getString(Constants.NOTIF_MSG);
                if (string == null) {
                    string = "";
                }
                if (string.isEmpty()) {
                    PushProviders.this.config.getLogger().verbose(PushProviders.this.config.getAccountId(), "Push notification message is empty, not rendering");
                    PushProviders.this.baseDatabaseManager.loadDBAdapter(PushProviders.this.context).storeUninstallTimestamp();
                    String string2 = bundle.getString(PushProviders.PING_FREQUENCY, "");
                    if (TextUtils.isEmpty(string2)) {
                        return null;
                    }
                    PushProviders pushProviders = PushProviders.this;
                    pushProviders.updatePingFrequencyIfNeeded(pushProviders.context, Integer.parseInt(string2));
                    return null;
                }
                String string3 = bundle.getString(Constants.WZRK_PUSH_ID);
                String string4 = bundle.getString("wzrk_ttl", ((System.currentTimeMillis() + Constants.DEFAULT_PUSH_TTL) / 1000) + "");
                long j = Long.parseLong(string4);
                DBAdapter dBAdapterLoadDBAdapter = PushProviders.this.baseDatabaseManager.loadDBAdapter(PushProviders.this.context);
                PushProviders.this.config.getLogger().verbose("Storing Push Notification..." + string3 + " - with ttl - " + string4);
                dBAdapterLoadDBAdapter.storePushNotificationId(string3, j);
                return null;
            }
        });
    }

    public void unregisterToken(String str, PushConstants.PushType pushType) {
        pushDeviceTokenEvent(str, false, pushType);
    }

    public void updatePingFrequencyIfNeeded(Context context, int i) {
        this.config.getLogger().verbose("Ping frequency received - " + i);
        this.config.getLogger().verbose("Stored Ping Frequency - " + getPingFrequency(context));
        if (i != getPingFrequency(context)) {
            setPingFrequency(context, i);
            if (!this.config.isBackgroundSync() || this.config.isAnalyticsOnly()) {
                return;
            }
            CTExecutorFactory.executors(this.config).postAsyncSafelyTask().execute("createOrResetWorker", new Callable<Void>() { // from class: com.clevertap.android.sdk.pushnotification.PushProviders.3
                @Override // java.util.concurrent.Callable
                public Void call() {
                    PushProviders.this.createOrResetWorker(true);
                    return null;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean alreadyHaveToken(String str, PushConstants.PushType pushType) {
        boolean z = (TextUtils.isEmpty(str) || pushType == null || !str.equalsIgnoreCase(getCachedToken(pushType))) ? false : true;
        if (pushType != null) {
            this.config.log(PushConstants.LOG_TAG, pushType + "Token Already available value: " + z);
        }
        return z;
    }

    public void runPushAmpWork(Context context) {
        Logger.v(this.config.getAccountId(), "Pushamp - Running work request");
        if (!isNotificationSupported()) {
            Logger.v(this.config.getAccountId(), "Pushamp - Token is not present, not running the work request");
            return;
        }
        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(11);
        int i2 = calendar.get(12);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(inputFormat, Locale.US);
        if (isTimeBetweenDNDTime(parseTimeToDate(Constants.DND_START, simpleDateFormat), parseTimeToDate(Constants.DND_STOP, simpleDateFormat), parseTimeToDate(i + ":" + i2, simpleDateFormat))) {
            Logger.v(this.config.getAccountId(), "Pushamp won't run in default DND hours");
            return;
        }
        long lastUninstallTimestamp = this.baseDatabaseManager.loadDBAdapter(context).getLastUninstallTimestamp();
        if (lastUninstallTimestamp == 0 || lastUninstallTimestamp > System.currentTimeMillis() - 86400000) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("bk", 1);
                this.analyticsManager.sendPingEvent(jSONObject);
                Logger.v(this.config.getAccountId(), "Pushamp - Successfully completed work request");
            } catch (JSONException unused) {
                Logger.v("Pushamp - Unable to complete work request");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopJobScheduler(Context context) {
        int i = StorageHelper.getInt(context, PF_JOB_ID, -1);
        if (i != -1) {
            ((JobScheduler) context.getSystemService("jobscheduler")).cancel(i);
            StorageHelper.remove(context, PF_JOB_ID);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createOrResetWorker(boolean z) {
        if (Build.VERSION.SDK_INT < 26) {
            this.config.getLogger().debug(this.config.getAccountId(), "Pushamp feature is not supported below Oreo");
            return;
        }
        String string = StorageHelper.getString(this.context, PF_WORK_ID, "");
        int pingFrequency = getPingFrequency(this.context);
        if (string.equals("") && pingFrequency <= 0) {
            this.config.getLogger().debug(this.config.getAccountId(), "Pushamp - There is no running work and nothing to create");
            return;
        }
        if (pingFrequency <= 0) {
            this.config.getLogger().debug(this.config.getAccountId(), "Pushamp - Cancelling worker as pingFrequency <=0 ");
            stopWorker();
            return;
        }
        try {
            WorkManager workManager = WorkManager.getInstance(this.context);
            if (string.equals("") || z) {
                PeriodicWorkRequest periodicWorkRequestBuild = new PeriodicWorkRequest.Builder(CTPushAmpWorker.class, pingFrequency, TimeUnit.MINUTES, 5L, TimeUnit.MINUTES).setConstraints(new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).setRequiresCharging(false).setRequiresBatteryNotLow(true).build()).build();
                if (string.equals("")) {
                    string = this.config.getAccountId();
                }
                workManager.enqueueUniquePeriodicWork(string, ExistingPeriodicWorkPolicy.REPLACE, periodicWorkRequestBuild);
                StorageHelper.putString(this.context, PF_WORK_ID, string);
                this.config.getLogger().debug(this.config.getAccountId(), "Pushamp - Finished scheduling periodic work request - " + string + " with repeatInterval- " + pingFrequency + " minutes");
            }
        } catch (Exception e) {
            this.config.getLogger().debug(this.config.getAccountId(), "Pushamp - Failed scheduling/cancelling periodic work request" + e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopWorker() {
        String string = StorageHelper.getString(this.context, PF_WORK_ID, "");
        if (string.equals("")) {
            return;
        }
        try {
            WorkManager.getInstance(this.context).cancelUniqueWork(string);
            StorageHelper.putString(this.context, PF_WORK_ID, "");
            this.config.getLogger().debug(this.config.getAccountId(), "Pushamp - Successfully cancelled work");
        } catch (Exception unused) {
            this.config.getLogger().debug(this.config.getAccountId(), "Pushamp - Failure while cancelling work");
        }
    }

    private List<CTPushProvider> createProviders() throws ClassNotFoundException {
        ArrayList arrayList = new ArrayList();
        Iterator<PushConstants.PushType> it = this.allEnabledPushTypes.iterator();
        while (it.hasNext()) {
            CTPushProvider cTPushProviderFromPushType = getCTPushProviderFromPushType(it.next(), true);
            if (cTPushProviderFromPushType != null) {
                arrayList.add(cTPushProviderFromPushType);
            }
        }
        return arrayList;
    }

    private CTPushProvider getCTPushProviderFromPushType(PushConstants.PushType pushType, boolean z) throws ClassNotFoundException {
        String ctProviderClassName = pushType.getCtProviderClassName();
        CTPushProvider cTPushProvider = null;
        try {
            Class<?> cls = Class.forName(ctProviderClassName);
            cTPushProvider = z ? (CTPushProvider) cls.getConstructor(CTPushProviderListener.class, Context.class, CleverTapInstanceConfig.class).newInstance(this, this.context, this.config) : (CTPushProvider) cls.getConstructor(CTPushProviderListener.class, Context.class, CleverTapInstanceConfig.class, Boolean.class).newInstance(this, this.context, this.config, false);
            this.config.log(PushConstants.LOG_TAG, "Found provider:" + ctProviderClassName);
        } catch (ClassNotFoundException unused) {
            this.config.log(PushConstants.LOG_TAG, "Unable to create provider ClassNotFoundException" + ctProviderClassName);
        } catch (IllegalAccessException unused2) {
            this.config.log(PushConstants.LOG_TAG, "Unable to create provider IllegalAccessException" + ctProviderClassName);
        } catch (InstantiationException unused3) {
            this.config.log(PushConstants.LOG_TAG, "Unable to create provider InstantiationException" + ctProviderClassName);
        } catch (Exception e) {
            this.config.log(PushConstants.LOG_TAG, "Unable to create provider " + ctProviderClassName + " Exception:" + e.getClass().getName());
        }
        return cTPushProvider;
    }

    private void deviceTokenDidRefresh(String str, PushConstants.PushType pushType) {
        if (this.tokenRefreshListener != null) {
            this.config.getLogger().debug(this.config.getAccountId(), "Notifying devicePushTokenDidRefresh: " + str);
            this.tokenRefreshListener.devicePushTokenDidRefresh(str, pushType);
        }
    }

    private void findCTPushProviders(List<CTPushProvider> list) {
        if (list.isEmpty()) {
            this.config.log(PushConstants.LOG_TAG, "No push providers found!. Make sure to install at least one push provider");
            return;
        }
        for (CTPushProvider cTPushProvider : list) {
            if (!isValid(cTPushProvider)) {
                this.config.log(PushConstants.LOG_TAG, "Invalid Provider: " + cTPushProvider.getClass());
            } else if (!cTPushProvider.isSupported()) {
                this.config.log(PushConstants.LOG_TAG, "Unsupported Provider: " + cTPushProvider.getClass());
            } else if (cTPushProvider.isAvailable()) {
                this.config.log(PushConstants.LOG_TAG, "Available Provider: " + cTPushProvider.getClass());
                this.availableCTPushProviders.add(cTPushProvider);
            } else {
                this.config.log(PushConstants.LOG_TAG, "Unavailable Provider: " + cTPushProvider.getClass());
            }
        }
    }

    private void findCustomEnabledPushTypes() {
        this.customEnabledPushTypes.addAll(this.allEnabledPushTypes);
        Iterator<CTPushProvider> it = this.availableCTPushProviders.iterator();
        while (it.hasNext()) {
            this.customEnabledPushTypes.remove(it.next().getPushType());
        }
    }

    private void findEnabledPushTypes() throws ClassNotFoundException {
        for (PushConstants.PushType pushType : PushNotificationUtil.getPushTypes(this.config.getAllowedPushTypes())) {
            String messagingSDKClassName = pushType.getMessagingSDKClassName();
            try {
                Class.forName(messagingSDKClassName);
                this.allEnabledPushTypes.add(pushType);
                this.config.log(PushConstants.LOG_TAG, "SDK Class Available :" + messagingSDKClassName);
            } catch (Exception e) {
                this.config.log(PushConstants.LOG_TAG, "SDK class Not available " + messagingSDKClassName + " Exception:" + e.getClass().getName());
            }
        }
    }

    private int getPingFrequency(Context context) {
        return StorageHelper.getInt(context, PING_FREQUENCY, 240);
    }

    private void init() throws ClassNotFoundException {
        findEnabledPushTypes();
        final List<CTPushProvider> listCreateProviders = createProviders();
        Task taskPostAsyncSafelyTask = CTExecutorFactory.executors(this.config).postAsyncSafelyTask();
        taskPostAsyncSafelyTask.addOnSuccessListener(new OnSuccessListener() { // from class: com.clevertap.android.sdk.pushnotification.PushProviders$$ExternalSyntheticLambda0
            @Override // com.clevertap.android.sdk.task.OnSuccessListener
            public final void onSuccess(Object obj) {
                this.f$0.m4803xe61576f9((Void) obj);
            }
        });
        taskPostAsyncSafelyTask.execute("asyncFindCTPushProviders", new Callable() { // from class: com.clevertap.android.sdk.pushnotification.PushProviders$$ExternalSyntheticLambda1
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.m4804x73028e18(listCreateProviders);
            }
        });
    }

    /* renamed from: lambda$init$0$com-clevertap-android-sdk-pushnotification-PushProviders, reason: not valid java name */
    /* synthetic */ void m4803xe61576f9(Void r1) {
        findCustomEnabledPushTypes();
    }

    /* renamed from: lambda$init$1$com-clevertap-android-sdk-pushnotification-PushProviders, reason: not valid java name */
    /* synthetic */ Void m4804x73028e18(List list) throws Exception {
        findCTPushProviders(list);
        return null;
    }

    private void initPushAmp() {
        CTExecutorFactory.executors(this.config).postAsyncSafelyTask().execute("createOrResetWorker", new Callable<Void>() { // from class: com.clevertap.android.sdk.pushnotification.PushProviders.4
            @Override // java.util.concurrent.Callable
            public Void call() {
                PushProviders pushProviders = PushProviders.this;
                pushProviders.stopJobScheduler(pushProviders.context);
                if (!PushProviders.this.config.isBackgroundSync() || PushProviders.this.config.isAnalyticsOnly()) {
                    PushProviders.this.config.getLogger().debug(PushProviders.this.config.getAccountId(), "Pushamp - Cancelling worker as background sync is disabled or config is analytics only");
                    PushProviders.this.stopWorker();
                    return null;
                }
                PushProviders.this.createOrResetWorker(false);
                return null;
            }
        });
    }

    private boolean isTimeBetweenDNDTime(Date date, Date date2, Date date3) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date3);
        Calendar calendar3 = Calendar.getInstance();
        calendar3.setTime(date2);
        if (date2.compareTo(date) < 0) {
            if (calendar2.compareTo(calendar3) < 0) {
                calendar2.add(5, 1);
            }
            calendar3.add(5, 1);
        }
        return calendar2.compareTo(calendar) >= 0 && calendar2.compareTo(calendar3) < 0;
    }

    private boolean isValid(CTPushProvider cTPushProvider) {
        if (70102 < cTPushProvider.minSDKSupportVersionCode()) {
            this.config.log(PushConstants.LOG_TAG, "Provider: %s version %s does not match the SDK version %s. Make sure all CleverTap dependencies are the same version.");
            return false;
        }
        int i = AnonymousClass6.$SwitchMap$com$clevertap$android$sdk$pushnotification$PushConstants$PushType[cTPushProvider.getPushType().ordinal()];
        if (i == 1 || i == 2 || i == 3) {
            if (cTPushProvider.getPlatform() != 1) {
                this.config.log(PushConstants.LOG_TAG, "Invalid Provider: " + cTPushProvider.getClass() + " delivery is only available for Android platforms." + cTPushProvider.getPushType());
                return false;
            }
        } else if (i == 4 && cTPushProvider.getPlatform() != 2) {
            this.config.log(PushConstants.LOG_TAG, "Invalid Provider: " + cTPushProvider.getClass() + " ADM delivery is only available for Amazon platforms." + cTPushProvider.getPushType());
            return false;
        }
        return true;
    }

    private Date parseTimeToDate(String str, SimpleDateFormat simpleDateFormat) {
        try {
            return simpleDateFormat.parse(str);
        } catch (ParseException unused) {
            return new Date(0L);
        }
    }

    private void pushDeviceTokenEvent(String str, boolean z, PushConstants.PushType pushType) {
        if (pushType == null) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            str = getCachedToken(pushType);
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        synchronized (this.tokenLock) {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            String str2 = z ? "register" : "unregister";
            try {
                jSONObject2.put(Constants.KEY_ACTION, str2);
                jSONObject2.put("id", str);
                jSONObject2.put("type", pushType.getType());
                jSONObject.put("data", jSONObject2);
                this.config.getLogger().verbose(this.config.getAccountId(), pushType + str2 + " device token " + str);
                this.analyticsManager.sendDataEvent(jSONObject);
            } catch (Throwable th) {
                this.config.getLogger().verbose(this.config.getAccountId(), pushType + str2 + " device token failed", th);
            }
        }
    }

    private void refreshAllTokens() {
        CTExecutorFactory.executors(this.config).ioTask().execute("PushProviders#refreshAllTokens", new Callable<Void>() { // from class: com.clevertap.android.sdk.pushnotification.PushProviders.5
            @Override // java.util.concurrent.Callable
            public Void call() {
                PushProviders.this.refreshCTProviderTokens();
                PushProviders.this.refreshCustomProviderTokens();
                return null;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshCTProviderTokens() {
        Iterator<CTPushProvider> it = this.availableCTPushProviders.iterator();
        while (it.hasNext()) {
            CTPushProvider next = it.next();
            try {
                next.requestToken();
            } catch (Throwable th) {
                this.config.log(PushConstants.LOG_TAG, "Token Refresh error " + next, th);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshCustomProviderTokens() {
        Iterator<PushConstants.PushType> it = this.customEnabledPushTypes.iterator();
        while (it.hasNext()) {
            PushConstants.PushType next = it.next();
            try {
                pushDeviceTokenEvent(getCachedToken(next), true, next);
            } catch (Throwable th) {
                this.config.log(PushConstants.LOG_TAG, "Token Refresh error " + next, th);
            }
        }
    }

    private void registerToken(String str, PushConstants.PushType pushType) {
        pushDeviceTokenEvent(str, true, pushType);
        cacheToken(str, pushType);
    }

    private void setPingFrequency(Context context, int i) {
        StorageHelper.putInt(context, PING_FREQUENCY, i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0060  */
    /* JADX WARN: Type inference failed for: r12v1 */
    /* JADX WARN: Type inference failed for: r12v13 */
    /* JADX WARN: Type inference failed for: r12v2, types: [int] */
    /* JADX WARN: Type inference failed for: r12v5 */
    /* JADX WARN: Type inference failed for: r1v10, types: [androidx.core.app.NotificationCompat$Builder] */
    /* JADX WARN: Type inference failed for: r1v41, types: [androidx.core.app.NotificationCompat$Builder] */
    /* JADX WARN: Type inference failed for: r1v9, types: [androidx.core.app.NotificationCompat$Builder] */
    /* JADX WARN: Type inference failed for: r2v29, types: [com.clevertap.android.sdk.interfaces.AudibleNotification] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void triggerNotification(android.content.Context r18, android.os.Bundle r19, int r20) throws java.lang.NumberFormatException {
        /*
            Method dump skipped, instructions count: 895
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.clevertap.android.sdk.pushnotification.PushProviders.triggerNotification(android.content.Context, android.os.Bundle, int):void");
    }
}
