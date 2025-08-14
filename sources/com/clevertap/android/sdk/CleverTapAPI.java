package com.clevertap.android.sdk;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.text.TextUtils;
import com.clevertap.android.sdk.cryption.CryptHandler;
import com.clevertap.android.sdk.displayunits.DisplayUnitListener;
import com.clevertap.android.sdk.displayunits.model.CleverTapDisplayUnit;
import com.clevertap.android.sdk.events.EventDetail;
import com.clevertap.android.sdk.events.EventGroup;
import com.clevertap.android.sdk.featureFlags.CTFeatureFlagsController;
import com.clevertap.android.sdk.inapp.callbacks.FetchInAppsCallback;
import com.clevertap.android.sdk.inapp.customtemplates.CustomTemplateContext;
import com.clevertap.android.sdk.inapp.customtemplates.FunctionPresenter;
import com.clevertap.android.sdk.inapp.customtemplates.JsonTemplatesProducer;
import com.clevertap.android.sdk.inapp.customtemplates.TemplatePresenter;
import com.clevertap.android.sdk.inapp.customtemplates.TemplateProducer;
import com.clevertap.android.sdk.inapp.customtemplates.TemplatesManager;
import com.clevertap.android.sdk.inapp.data.CtCacheType;
import com.clevertap.android.sdk.inapp.evaluation.EvaluationManager;
import com.clevertap.android.sdk.inapp.images.repo.FileResourcesRepoFactory;
import com.clevertap.android.sdk.inapp.images.repo.FileResourcesRepoImpl;
import com.clevertap.android.sdk.inapp.store.preference.ImpressionStore;
import com.clevertap.android.sdk.inapp.store.preference.InAppStore;
import com.clevertap.android.sdk.inapp.store.preference.StoreRegistry;
import com.clevertap.android.sdk.inbox.CTInboxActivity;
import com.clevertap.android.sdk.inbox.CTInboxMessage;
import com.clevertap.android.sdk.inbox.CTMessageDAO;
import com.clevertap.android.sdk.interfaces.NotificationHandler;
import com.clevertap.android.sdk.interfaces.NotificationRenderedListener;
import com.clevertap.android.sdk.interfaces.OnInitCleverTapIDListener;
import com.clevertap.android.sdk.interfaces.SCDomainListener;
import com.clevertap.android.sdk.network.NetworkManager;
import com.clevertap.android.sdk.product_config.CTProductConfigController;
import com.clevertap.android.sdk.product_config.CTProductConfigListener;
import com.clevertap.android.sdk.pushnotification.CTPushNotificationListener;
import com.clevertap.android.sdk.pushnotification.CoreNotificationRenderer;
import com.clevertap.android.sdk.pushnotification.INotificationRenderer;
import com.clevertap.android.sdk.pushnotification.NotificationInfo;
import com.clevertap.android.sdk.pushnotification.PushConstants;
import com.clevertap.android.sdk.pushnotification.amp.CTPushAmpListener;
import com.clevertap.android.sdk.task.CTExecutorFactory;
import com.clevertap.android.sdk.usereventlogs.UserEventLog;
import com.clevertap.android.sdk.validation.ManifestValidator;
import com.clevertap.android.sdk.validation.ValidationResult;
import com.clevertap.android.sdk.variables.Var;
import com.clevertap.android.sdk.variables.callbacks.FetchVariablesCallback;
import com.clevertap.android.sdk.variables.callbacks.VariablesChangedCallback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class CleverTapAPI implements CTInboxActivity.InboxActivityListener {
    public static final String NOTIFICATION_TAG = "wzrk_pn";
    static CleverTapInstanceConfig defaultConfig;
    private static HashMap<String, CleverTapAPI> instances;
    private static NotificationHandler sNotificationHandler;
    private static NotificationHandler sSignedCallNotificationHandler;
    private static String sdkVersion;
    private final Context context;
    private CoreState coreState;
    private WeakReference<InboxMessageButtonListener> inboxMessageButtonListener;
    private WeakReference<InboxMessageListener> inboxMessageListener;
    private static int debugLevel = LogLevel.INFO.intValue();
    private static final HashMap<String, NotificationRenderedListener> sNotificationRenderedListenerMap = new HashMap<>();

    public interface DevicePushTokenRefreshListener {
        void devicePushTokenDidRefresh(String str, PushConstants.PushType pushType);
    }

    public interface RequestDevicePushTokenListener {
        void onDevicePushToken(String str, PushConstants.PushType pushType);
    }

    public static int getDebugLevel() {
        return debugLevel;
    }

    public static HashMap<String, CleverTapAPI> getInstances() {
        return instances;
    }

    public static NotificationHandler getNotificationHandler() {
        return sNotificationHandler;
    }

    public static NotificationHandler getSignedCallNotificationHandler() {
        return sSignedCallNotificationHandler;
    }

    public static void setDebugLevel(int i) {
        debugLevel = i;
    }

    public static void setInstances(HashMap<String, CleverTapAPI> map) {
        instances = map;
    }

    public static void setNotificationHandler(NotificationHandler notificationHandler) {
        sNotificationHandler = notificationHandler;
    }

    public static void setSignedCallNotificationHandler(NotificationHandler notificationHandler) {
        sSignedCallNotificationHandler = notificationHandler;
    }

    public CoreState getCoreState() {
        return this.coreState;
    }

    void setCoreState(CoreState coreState) {
        this.coreState = coreState;
    }

    public enum LogLevel {
        OFF(-1),
        INFO(0),
        DEBUG(2),
        VERBOSE(3);

        private final int value;

        public int intValue() {
            return this.value;
        }

        LogLevel(int i) {
            this.value = i;
        }
    }

    public static void changeCredentials(String str, String str2) {
        changeCredentials(str, str2, null);
    }

    public static void changeCredentials(String str, String str2, String str3) {
        if (defaultConfig != null) {
            Logger.i("CleverTap SDK already initialized with accountID:" + defaultConfig.getAccountId() + " and token:" + defaultConfig.getAccountToken() + ". Cannot change credentials to " + str + " and " + str2);
        } else {
            ManifestInfo.changeCredentials(str, str2, str3);
        }
    }

    public static void changeCredentials(String str, String str2, String str3, String str4) {
        if (defaultConfig != null) {
            Logger.i("CleverTap SDK already initialized with accountID:" + defaultConfig.getAccountId() + ", token:" + defaultConfig.getAccountToken() + ", proxyDomain: " + defaultConfig.getProxyDomain() + " and spikyDomain: " + defaultConfig.getSpikyProxyDomain() + ". Cannot change credentials to accountID: " + str + ", token: " + str2 + ", proxyDomain: " + str3 + "and spikyProxyDomain: " + str4);
        } else {
            ManifestInfo.changeCredentials(str, str2, str3, str4);
        }
    }

    public static void changeCredentials(String str, String str2, String str3, String str4, String str5) {
        if (defaultConfig != null) {
            Logger.i("CleverTap SDK already initialized with accountID:" + defaultConfig.getAccountId() + ", token:" + defaultConfig.getAccountToken() + ", proxyDomain: " + defaultConfig.getProxyDomain() + ", spikyDomain: " + defaultConfig.getSpikyProxyDomain() + ", handshakeDomain: " + defaultConfig.getCustomHandshakeDomain() + ". Cannot change credentials to accountID: " + str + ", token: " + str2 + ", proxyDomain: " + str3 + ", spikyProxyDomain: " + str4 + "and customHandshakeDomain: " + str5);
        } else {
            ManifestInfo.changeCredentials(str, str2, str3, str4, str5);
        }
    }

    public static void createNotification(Context context, Bundle bundle, int i) {
        CleverTapAPI cleverTapAPIFromBundle = fromBundle(context, bundle);
        if (cleverTapAPIFromBundle != null) {
            CoreState coreState = cleverTapAPIFromBundle.coreState;
            CleverTapInstanceConfig config = coreState.getConfig();
            try {
                synchronized (coreState.getPushProviders().getPushRenderingLock()) {
                    coreState.getPushProviders().setPushNotificationRenderer(new CoreNotificationRenderer());
                    coreState.getPushProviders()._createNotification(context, bundle, i);
                }
            } catch (Throwable th) {
                config.getLogger().debug(config.getAccountId(), "Failed to process createNotification()", th);
            }
        }
    }

    public static CleverTapAPI getGlobalInstance(Context context, String str) {
        return fromAccountId(context, str);
    }

    public static void processPushNotification(Context context, Bundle bundle) {
        CleverTapAPI cleverTapAPIFromBundle = fromBundle(context, bundle);
        if (cleverTapAPIFromBundle != null) {
            cleverTapAPIFromBundle.coreState.getPushProviders().processCustomPushNotification(bundle);
        }
    }

    public static void createNotification(Context context, Bundle bundle) {
        createNotification(context, bundle, -1000);
    }

    public static void createNotificationChannel(final Context context, final String str, final CharSequence charSequence, final String str2, final int i, final boolean z) {
        final CleverTapAPI defaultInstanceOrFirstOther = getDefaultInstanceOrFirstOther(context);
        if (defaultInstanceOrFirstOther == null) {
            Logger.v("No CleverTap Instance found in CleverTapAPI#createNotificatonChannel");
            return;
        }
        try {
            if (Build.VERSION.SDK_INT >= 26) {
                CTExecutorFactory.executors(defaultInstanceOrFirstOther.coreState.getConfig()).postAsyncSafelyTask().execute("createNotificationChannel", new Callable<Void>() { // from class: com.clevertap.android.sdk.CleverTapAPI.1
                    @Override // java.util.concurrent.Callable
                    public Void call() {
                        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
                        if (notificationManager == null) {
                            return null;
                        }
                        NotificationChannel notificationChannel = new NotificationChannel(str, charSequence, i);
                        notificationChannel.setDescription(str2);
                        notificationChannel.setShowBadge(z);
                        notificationManager.createNotificationChannel(notificationChannel);
                        defaultInstanceOrFirstOther.getConfigLogger().info(defaultInstanceOrFirstOther.getAccountId(), "Notification channel " + charSequence.toString() + " has been created");
                        return null;
                    }
                });
            }
        } catch (Throwable th) {
            defaultInstanceOrFirstOther.getConfigLogger().verbose(defaultInstanceOrFirstOther.getAccountId(), "Failure creating Notification Channel", th);
        }
    }

    public static void createNotificationChannel(final Context context, final String str, final CharSequence charSequence, final String str2, final int i, final String str3, final boolean z) {
        final CleverTapAPI defaultInstanceOrFirstOther = getDefaultInstanceOrFirstOther(context);
        if (defaultInstanceOrFirstOther == null) {
            Logger.v("No CleverTap Instance found in CleverTapAPI#createNotificatonChannel");
            return;
        }
        try {
            if (Build.VERSION.SDK_INT >= 26) {
                CTExecutorFactory.executors(defaultInstanceOrFirstOther.coreState.getConfig()).postAsyncSafelyTask().execute("creatingNotificationChannel", new Callable() { // from class: com.clevertap.android.sdk.CleverTapAPI$$ExternalSyntheticLambda10
                    @Override // java.util.concurrent.Callable
                    public final Object call() {
                        return CleverTapAPI.lambda$createNotificationChannel$0(context, str, charSequence, i, str2, str3, z, defaultInstanceOrFirstOther);
                    }
                });
            }
        } catch (Throwable th) {
            defaultInstanceOrFirstOther.getConfigLogger().verbose(defaultInstanceOrFirstOther.getAccountId(), "Failure creating Notification Channel", th);
        }
    }

    static /* synthetic */ Void lambda$createNotificationChannel$0(Context context, String str, CharSequence charSequence, int i, String str2, String str3, boolean z, CleverTapAPI cleverTapAPI) throws Exception {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        if (notificationManager == null) {
            return null;
        }
        NotificationChannel notificationChannel = new NotificationChannel(str, charSequence, i);
        notificationChannel.setDescription(str2);
        notificationChannel.setGroup(str3);
        notificationChannel.setShowBadge(z);
        notificationManager.createNotificationChannel(notificationChannel);
        cleverTapAPI.getConfigLogger().info(cleverTapAPI.getAccountId(), "Notification channel " + charSequence.toString() + " has been created");
        return null;
    }

    public static void createNotificationChannel(final Context context, final String str, final CharSequence charSequence, final String str2, final int i, final boolean z, final String str3) {
        final CleverTapAPI defaultInstanceOrFirstOther = getDefaultInstanceOrFirstOther(context);
        if (defaultInstanceOrFirstOther == null) {
            Logger.v("No CleverTap Instance found in CleverTapAPI#createNotificatonChannel");
            return;
        }
        try {
            if (Build.VERSION.SDK_INT >= 26) {
                CTExecutorFactory.executors(defaultInstanceOrFirstOther.coreState.getConfig()).postAsyncSafelyTask().execute("createNotificationChannel", new Callable() { // from class: com.clevertap.android.sdk.CleverTapAPI$$ExternalSyntheticLambda11
                    @Override // java.util.concurrent.Callable
                    public final Object call() {
                        return CleverTapAPI.lambda$createNotificationChannel$1(context, str3, defaultInstanceOrFirstOther, str, charSequence, i, str2, z);
                    }
                });
            }
        } catch (Throwable th) {
            defaultInstanceOrFirstOther.getConfigLogger().verbose(defaultInstanceOrFirstOther.getAccountId(), "Failure creating Notification Channel", th);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x006f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static /* synthetic */ java.lang.Void lambda$createNotificationChannel$1(android.content.Context r4, java.lang.String r5, com.clevertap.android.sdk.CleverTapAPI r6, java.lang.String r7, java.lang.CharSequence r8, int r9, java.lang.String r10, boolean r11) throws java.lang.Exception {
        /*
            java.lang.String r0 = "notification"
            java.lang.Object r0 = r4.getSystemService(r0)
            android.app.NotificationManager r0 = (android.app.NotificationManager) r0
            r1 = 0
            if (r0 != 0) goto Ld
            return r1
        Ld:
            boolean r2 = r5.isEmpty()
            if (r2 != 0) goto L6f
            java.lang.String r2 = ".mp3"
            boolean r2 = r5.contains(r2)
            if (r2 != 0) goto L3c
            java.lang.String r2 = ".ogg"
            boolean r2 = r5.contains(r2)
            if (r2 != 0) goto L3c
            java.lang.String r2 = ".wav"
            boolean r2 = r5.contains(r2)
            if (r2 == 0) goto L2c
            goto L3c
        L2c:
            com.clevertap.android.sdk.Logger r5 = r6.getConfigLogger()
            java.lang.String r2 = r6.getAccountId()
            java.lang.String r3 = "Sound file name not supported"
            r5.debug(r2, r3)
            java.lang.String r5 = ""
            goto L47
        L3c:
            int r2 = r5.length()
            int r2 = r2 + (-4)
            r3 = 0
            java.lang.String r5 = r5.substring(r3, r2)
        L47:
            boolean r2 = r5.isEmpty()
            if (r2 != 0) goto L6f
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "android.resource://"
            r2.<init>(r3)
            java.lang.String r4 = r4.getPackageName()
            java.lang.StringBuilder r4 = r2.append(r4)
            java.lang.String r2 = "/raw/"
            java.lang.StringBuilder r4 = r4.append(r2)
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r4 = r4.toString()
            android.net.Uri r4 = android.net.Uri.parse(r4)
            goto L70
        L6f:
            r4 = r1
        L70:
            android.app.NotificationChannel r5 = new android.app.NotificationChannel
            r5.<init>(r7, r8, r9)
            r5.setDescription(r10)
            r5.setShowBadge(r11)
            if (r4 == 0) goto L8f
            android.media.AudioAttributes$Builder r7 = new android.media.AudioAttributes$Builder
            r7.<init>()
            r9 = 5
            android.media.AudioAttributes$Builder r7 = r7.setUsage(r9)
            android.media.AudioAttributes r7 = r7.build()
            r5.setSound(r4, r7)
            goto L9c
        L8f:
            com.clevertap.android.sdk.Logger r4 = r6.getConfigLogger()
            java.lang.String r7 = r6.getAccountId()
            java.lang.String r9 = "Sound file not found, notification channel will be created without custom sound"
            r4.debug(r7, r9)
        L9c:
            r0.createNotificationChannel(r5)
            com.clevertap.android.sdk.Logger r4 = r6.getConfigLogger()
            java.lang.String r5 = r6.getAccountId()
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = "Notification channel "
            r6.<init>(r7)
            java.lang.String r7 = r8.toString()
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.String r7 = " has been created"
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.String r6 = r6.toString()
            r4.info(r5, r6)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.clevertap.android.sdk.CleverTapAPI.lambda$createNotificationChannel$1(android.content.Context, java.lang.String, com.clevertap.android.sdk.CleverTapAPI, java.lang.String, java.lang.CharSequence, int, java.lang.String, boolean):java.lang.Void");
    }

    public static void createNotificationChannel(final Context context, final String str, final CharSequence charSequence, final String str2, final int i, final String str3, final boolean z, final String str4) {
        final CleverTapAPI defaultInstanceOrFirstOther = getDefaultInstanceOrFirstOther(context);
        if (defaultInstanceOrFirstOther == null) {
            Logger.v("No CleverTap Instance found in CleverTapAPI#createNotificatonChannel");
            return;
        }
        try {
            if (Build.VERSION.SDK_INT >= 26) {
                CTExecutorFactory.executors(defaultInstanceOrFirstOther.coreState.getConfig()).postAsyncSafelyTask().execute("creatingNotificationChannel", new Callable() { // from class: com.clevertap.android.sdk.CleverTapAPI$$ExternalSyntheticLambda16
                    @Override // java.util.concurrent.Callable
                    public final Object call() {
                        return CleverTapAPI.lambda$createNotificationChannel$2(context, str4, defaultInstanceOrFirstOther, str, charSequence, i, str2, str3, z);
                    }
                });
            }
        } catch (Throwable th) {
            defaultInstanceOrFirstOther.getConfigLogger().verbose(defaultInstanceOrFirstOther.getAccountId(), "Failure creating Notification Channel", th);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x006f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static /* synthetic */ java.lang.Void lambda$createNotificationChannel$2(android.content.Context r4, java.lang.String r5, com.clevertap.android.sdk.CleverTapAPI r6, java.lang.String r7, java.lang.CharSequence r8, int r9, java.lang.String r10, java.lang.String r11, boolean r12) throws java.lang.Exception {
        /*
            java.lang.String r0 = "notification"
            java.lang.Object r0 = r4.getSystemService(r0)
            android.app.NotificationManager r0 = (android.app.NotificationManager) r0
            r1 = 0
            if (r0 != 0) goto Ld
            return r1
        Ld:
            boolean r2 = r5.isEmpty()
            if (r2 != 0) goto L6f
            java.lang.String r2 = ".mp3"
            boolean r2 = r5.contains(r2)
            if (r2 != 0) goto L3c
            java.lang.String r2 = ".ogg"
            boolean r2 = r5.contains(r2)
            if (r2 != 0) goto L3c
            java.lang.String r2 = ".wav"
            boolean r2 = r5.contains(r2)
            if (r2 == 0) goto L2c
            goto L3c
        L2c:
            com.clevertap.android.sdk.Logger r5 = r6.getConfigLogger()
            java.lang.String r2 = r6.getAccountId()
            java.lang.String r3 = "Sound file name not supported"
            r5.debug(r2, r3)
            java.lang.String r5 = ""
            goto L47
        L3c:
            int r2 = r5.length()
            int r2 = r2 + (-4)
            r3 = 0
            java.lang.String r5 = r5.substring(r3, r2)
        L47:
            boolean r2 = r5.isEmpty()
            if (r2 != 0) goto L6f
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "android.resource://"
            r2.<init>(r3)
            java.lang.String r4 = r4.getPackageName()
            java.lang.StringBuilder r4 = r2.append(r4)
            java.lang.String r2 = "/raw/"
            java.lang.StringBuilder r4 = r4.append(r2)
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r4 = r4.toString()
            android.net.Uri r4 = android.net.Uri.parse(r4)
            goto L70
        L6f:
            r4 = r1
        L70:
            android.app.NotificationChannel r5 = new android.app.NotificationChannel
            r5.<init>(r7, r8, r9)
            r5.setDescription(r10)
            r5.setGroup(r11)
            r5.setShowBadge(r12)
            if (r4 == 0) goto L92
            android.media.AudioAttributes$Builder r7 = new android.media.AudioAttributes$Builder
            r7.<init>()
            r9 = 5
            android.media.AudioAttributes$Builder r7 = r7.setUsage(r9)
            android.media.AudioAttributes r7 = r7.build()
            r5.setSound(r4, r7)
            goto L9f
        L92:
            com.clevertap.android.sdk.Logger r4 = r6.getConfigLogger()
            java.lang.String r7 = r6.getAccountId()
            java.lang.String r9 = "Sound file not found, notification channel will be created without custom sound"
            r4.debug(r7, r9)
        L9f:
            r0.createNotificationChannel(r5)
            com.clevertap.android.sdk.Logger r4 = r6.getConfigLogger()
            java.lang.String r5 = r6.getAccountId()
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = "Notification channel "
            r6.<init>(r7)
            java.lang.String r7 = r8.toString()
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.String r7 = " has been created"
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.String r6 = r6.toString()
            r4.info(r5, r6)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.clevertap.android.sdk.CleverTapAPI.lambda$createNotificationChannel$2(android.content.Context, java.lang.String, com.clevertap.android.sdk.CleverTapAPI, java.lang.String, java.lang.CharSequence, int, java.lang.String, java.lang.String, boolean):java.lang.Void");
    }

    public static void createNotificationChannelGroup(final Context context, final String str, final CharSequence charSequence) {
        final CleverTapAPI defaultInstanceOrFirstOther = getDefaultInstanceOrFirstOther(context);
        if (defaultInstanceOrFirstOther == null) {
            Logger.v("No CleverTap Instance found in CleverTapAPI#createNotificationChannelGroup");
            return;
        }
        try {
            if (Build.VERSION.SDK_INT >= 26) {
                CTExecutorFactory.executors(defaultInstanceOrFirstOther.coreState.getConfig()).postAsyncSafelyTask().execute("creatingNotificationChannelGroup", new Callable() { // from class: com.clevertap.android.sdk.CleverTapAPI$$ExternalSyntheticLambda14
                    @Override // java.util.concurrent.Callable
                    public final Object call() {
                        return CleverTapAPI.lambda$createNotificationChannelGroup$3(context, str, charSequence, defaultInstanceOrFirstOther);
                    }
                });
            }
        } catch (Throwable th) {
            defaultInstanceOrFirstOther.getConfigLogger().verbose(defaultInstanceOrFirstOther.getAccountId(), "Failure creating Notification Channel Group", th);
        }
    }

    static /* synthetic */ Void lambda$createNotificationChannelGroup$3(Context context, String str, CharSequence charSequence, CleverTapAPI cleverTapAPI) throws Exception {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        if (notificationManager == null) {
            return null;
        }
        notificationManager.createNotificationChannelGroup(new NotificationChannelGroup(str, charSequence));
        cleverTapAPI.getConfigLogger().info(cleverTapAPI.getAccountId(), "Notification channel group " + charSequence.toString() + " has been created");
        return null;
    }

    public static void deleteNotificationChannel(final Context context, final String str) {
        final CleverTapAPI defaultInstanceOrFirstOther = getDefaultInstanceOrFirstOther(context);
        if (defaultInstanceOrFirstOther == null) {
            Logger.v("No CleverTap Instance found in CleverTapAPI#deleteNotificationChannel");
            return;
        }
        try {
            if (Build.VERSION.SDK_INT >= 26) {
                CTExecutorFactory.executors(defaultInstanceOrFirstOther.coreState.getConfig()).postAsyncSafelyTask().execute("deletingNotificationChannel", new Callable() { // from class: com.clevertap.android.sdk.CleverTapAPI$$ExternalSyntheticLambda9
                    @Override // java.util.concurrent.Callable
                    public final Object call() {
                        return CleverTapAPI.lambda$deleteNotificationChannel$4(context, str, defaultInstanceOrFirstOther);
                    }
                });
            }
        } catch (Throwable th) {
            defaultInstanceOrFirstOther.getConfigLogger().verbose(defaultInstanceOrFirstOther.getAccountId(), "Failure deleting Notification Channel", th);
        }
    }

    static /* synthetic */ Void lambda$deleteNotificationChannel$4(Context context, String str, CleverTapAPI cleverTapAPI) throws Exception {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        if (notificationManager == null) {
            return null;
        }
        notificationManager.deleteNotificationChannel(str);
        cleverTapAPI.getConfigLogger().info(cleverTapAPI.getAccountId(), "Notification channel " + str + " has been deleted");
        return null;
    }

    public static void deleteNotificationChannelGroup(final Context context, final String str) {
        final CleverTapAPI defaultInstanceOrFirstOther = getDefaultInstanceOrFirstOther(context);
        if (defaultInstanceOrFirstOther == null) {
            Logger.v("No CleverTap Instance found in CleverTapAPI#deleteNotificationChannelGroup");
            return;
        }
        try {
            if (Build.VERSION.SDK_INT >= 26) {
                CTExecutorFactory.executors(defaultInstanceOrFirstOther.coreState.getConfig()).postAsyncSafelyTask().execute("deletingNotificationChannelGroup", new Callable() { // from class: com.clevertap.android.sdk.CleverTapAPI$$ExternalSyntheticLambda1
                    @Override // java.util.concurrent.Callable
                    public final Object call() {
                        return CleverTapAPI.lambda$deleteNotificationChannelGroup$5(context, str, defaultInstanceOrFirstOther);
                    }
                });
            }
        } catch (Throwable th) {
            defaultInstanceOrFirstOther.getConfigLogger().verbose(defaultInstanceOrFirstOther.getAccountId(), "Failure deleting Notification Channel Group", th);
        }
    }

    static /* synthetic */ Void lambda$deleteNotificationChannelGroup$5(Context context, String str, CleverTapAPI cleverTapAPI) throws Exception {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        if (notificationManager == null) {
            return null;
        }
        notificationManager.deleteNotificationChannelGroup(str);
        cleverTapAPI.getConfigLogger().info(cleverTapAPI.getAccountId(), "Notification channel group " + str + " has been deleted");
        return null;
    }

    @Deprecated
    public static void fcmTokenRefresh(Context context, String str) {
        Iterator<CleverTapAPI> it = getAvailableInstances(context).iterator();
        while (it.hasNext()) {
            CleverTapAPI next = it.next();
            if (next == null || next.getCoreState().getConfig().isAnalyticsOnly()) {
                Logger.d("Instance is Analytics Only not processing device token");
            } else {
                next.getCoreState().getPushProviders().doTokenRefresh(str, PushConstants.PushType.FCM);
            }
        }
    }

    public static void setDebugLevel(LogLevel logLevel) {
        debugLevel = logLevel.intValue();
    }

    public static CleverTapAPI getDefaultInstance(Context context, String str) {
        sdkVersion = BuildConfig.SDK_VERSION_STRING;
        CleverTapInstanceConfig cleverTapInstanceConfig = defaultConfig;
        if (cleverTapInstanceConfig != null) {
            return instanceWithConfig(context, cleverTapInstanceConfig, str);
        }
        CleverTapInstanceConfig defaultConfig2 = getDefaultConfig(context);
        defaultConfig = defaultConfig2;
        if (defaultConfig2 != null) {
            return instanceWithConfig(context, defaultConfig2, str);
        }
        return null;
    }

    public static CleverTapAPI getDefaultInstance(Context context) {
        return getDefaultInstance(context, null);
    }

    private static CleverTapAPI fromAccountId(Context context, String str) {
        HashMap<String, CleverTapAPI> map = instances;
        if (map == null) {
            return createInstanceIfAvailable(context, str);
        }
        Iterator<String> it = map.keySet().iterator();
        while (it.hasNext()) {
            CleverTapAPI cleverTapAPI = instances.get(it.next());
            if (cleverTapAPI != null && ((str == null && cleverTapAPI.coreState.getConfig().isDefaultInstance()) || cleverTapAPI.getAccountId().equals(str))) {
                return cleverTapAPI;
            }
        }
        return null;
    }

    public static NotificationInfo getNotificationInfo(Bundle bundle) {
        boolean z = false;
        if (bundle == null) {
            return new NotificationInfo(false, false);
        }
        boolean zContainsKey = bundle.containsKey("wzrk_pn");
        if (zContainsKey && bundle.containsKey(Constants.NOTIF_MSG)) {
            z = true;
        }
        return new NotificationInfo(zContainsKey, z);
    }

    public static void handleNotificationClicked(Context context, Bundle bundle) {
        String string;
        if (bundle == null) {
            return;
        }
        try {
            string = bundle.getString(Constants.WZRK_ACCT_ID_KEY);
        } catch (Throwable unused) {
            string = null;
        }
        HashMap<String, CleverTapAPI> map = instances;
        if (map == null) {
            CleverTapAPI cleverTapAPICreateInstanceIfAvailable = createInstanceIfAvailable(context, string);
            if (cleverTapAPICreateInstanceIfAvailable != null) {
                cleverTapAPICreateInstanceIfAvailable.pushNotificationClickedEvent(bundle);
                return;
            }
            return;
        }
        Iterator<String> it = map.keySet().iterator();
        while (it.hasNext()) {
            CleverTapAPI cleverTapAPI = instances.get(it.next());
            if (cleverTapAPI != null && ((string == null && cleverTapAPI.coreState.getConfig().isDefaultInstance()) || cleverTapAPI.getAccountId().equals(string))) {
                cleverTapAPI.pushNotificationClickedEvent(bundle);
                return;
            }
        }
    }

    public static CleverTapAPI instanceWithConfig(Context context, CleverTapInstanceConfig cleverTapInstanceConfig) {
        return instanceWithConfig(context, cleverTapInstanceConfig, null);
    }

    public static CleverTapAPI instanceWithConfig(Context context, CleverTapInstanceConfig cleverTapInstanceConfig, String str) {
        if (cleverTapInstanceConfig == null) {
            Logger.v("CleverTapInstanceConfig cannot be null");
            return null;
        }
        if (instances == null) {
            instances = new HashMap<>();
        }
        final CleverTapAPI cleverTapAPI = instances.get(cleverTapInstanceConfig.getAccountId());
        if (cleverTapAPI == null) {
            cleverTapAPI = new CleverTapAPI(context, cleverTapInstanceConfig, str);
            instances.put(cleverTapInstanceConfig.getAccountId(), cleverTapAPI);
            CTExecutorFactory.executors(cleverTapAPI.coreState.getConfig()).postAsyncSafelyTask().execute("recordDeviceIDErrors", new Callable() { // from class: com.clevertap.android.sdk.CleverTapAPI$$ExternalSyntheticLambda4
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return CleverTapAPI.lambda$instanceWithConfig$6(this.f$0);
                }
            });
        } else if (cleverTapAPI.isErrorDeviceId() && cleverTapAPI.getConfig().getEnableCustomCleverTapId() && Utils.validateCTID(str)) {
            cleverTapAPI.coreState.getLoginController().asyncProfileSwitchUser(null, null, str);
        }
        Logger.v(cleverTapInstanceConfig.getAccountId() + ":async_deviceID", "CleverTapAPI instance = " + cleverTapAPI);
        return cleverTapAPI;
    }

    static /* synthetic */ Void lambda$instanceWithConfig$6(CleverTapAPI cleverTapAPI) throws Exception {
        if (cleverTapAPI.getCleverTapID() == null) {
            return null;
        }
        cleverTapAPI.coreState.getLoginController().recordDeviceIDErrors();
        return null;
    }

    public static boolean isAppForeground() {
        return CoreMetaData.isAppForeground();
    }

    public static void setAppForeground(boolean z) {
        CoreMetaData.setAppForeground(z);
    }

    public static void onActivityPaused() {
        HashMap<String, CleverTapAPI> map = instances;
        if (map == null) {
            return;
        }
        Iterator<String> it = map.keySet().iterator();
        while (it.hasNext()) {
            CleverTapAPI cleverTapAPI = instances.get(it.next());
            if (cleverTapAPI != null) {
                try {
                    cleverTapAPI.coreState.getActivityLifeCycleManager().activityPaused();
                } catch (Throwable unused) {
                }
            }
        }
    }

    public static void onActivityResumed(Activity activity) {
        onActivityResumed(activity, null);
    }

    public static void onActivityResumed(Activity activity, String str) {
        if (instances == null) {
            createInstanceIfAvailable(activity.getApplicationContext(), null, str);
        }
        CoreMetaData.setAppForeground(true);
        if (instances == null) {
            Logger.v("Instances is null in onActivityResumed!");
            return;
        }
        String currentActivityName = CoreMetaData.getCurrentActivityName();
        CoreMetaData.setCurrentActivity(activity);
        if (currentActivityName == null || !currentActivityName.equals(activity.getLocalClassName())) {
            CoreMetaData.incrementActivityCount();
        }
        if (CoreMetaData.getInitialAppEnteredForegroundTime() <= 0) {
            CoreMetaData.setInitialAppEnteredForegroundTime(Utils.getNow());
        }
        Iterator<String> it = instances.keySet().iterator();
        while (it.hasNext()) {
            CleverTapAPI cleverTapAPI = instances.get(it.next());
            if (cleverTapAPI != null) {
                try {
                    cleverTapAPI.coreState.getActivityLifeCycleManager().activityResumed(activity);
                } catch (Throwable th) {
                    Logger.v("Throwable - " + th.getLocalizedMessage());
                }
            }
        }
    }

    private static CleverTapAPI fromBundle(Context context, Bundle bundle) {
        return fromAccountId(context, bundle.getString(Constants.WZRK_ACCT_ID_KEY));
    }

    private static boolean isDevelopmentMode(Context context) {
        return (context.getApplicationInfo().flags & 2) != 0;
    }

    public static void runJobWork(Context context) {
        HashMap<String, CleverTapAPI> map = instances;
        if (map == null) {
            CleverTapAPI defaultInstance = getDefaultInstance(context);
            if (defaultInstance != null) {
                if (defaultInstance.getConfig().isBackgroundSync()) {
                    defaultInstance.coreState.getPushProviders().runPushAmpWork(context);
                    return;
                } else {
                    Logger.d("Instance doesn't allow Background sync, not running the Job");
                    return;
                }
            }
            return;
        }
        for (String str : map.keySet()) {
            CleverTapAPI cleverTapAPI = instances.get(str);
            if (cleverTapAPI != null && cleverTapAPI.getConfig().isAnalyticsOnly()) {
                Logger.d(str, "Instance is Analytics Only not running the Job");
            } else if (cleverTapAPI == null || !cleverTapAPI.getConfig().isBackgroundSync()) {
                Logger.d(str, "Instance doesn't allow Background sync, not running the Job");
            } else {
                cleverTapAPI.coreState.getPushProviders().runPushAmpWork(context);
            }
        }
    }

    public static void tokenRefresh(Context context, String str, PushConstants.PushType pushType) {
        Iterator<CleverTapAPI> it = getAvailableInstances(context).iterator();
        while (it.hasNext()) {
            it.next().coreState.getPushProviders().doTokenRefresh(str, pushType);
        }
    }

    public static synchronized void registerCustomInAppTemplates(TemplateProducer templateProducer) {
        TemplatesManager.register(templateProducer);
    }

    public static synchronized void registerCustomInAppTemplates(String str, TemplatePresenter templatePresenter, FunctionPresenter functionPresenter) {
        TemplatesManager.register(new JsonTemplatesProducer(str, templatePresenter, functionPresenter));
    }

    public CustomTemplateContext getActiveContextForTemplate(String str) {
        CoreState coreState = this.coreState;
        if (coreState == null || coreState.getTemplatesManager() == null) {
            return null;
        }
        return this.coreState.getTemplatesManager().getActiveContextForTemplate(str);
    }

    public void syncRegisteredInAppTemplates() {
        if (!isDevelopmentMode()) {
            getConfigLogger().debug("CustomTemplates", "Your app is NOT in development mode, templates will not be synced");
            return;
        }
        CoreState coreState = this.coreState;
        if (coreState == null) {
            getConfigLogger().debug("CustomTemplates", "coreState is null, templates cannot be synced");
            return;
        }
        if (coreState.getNetworkManager() == null) {
            getConfigLogger().debug("CustomTemplates", "networkManager is null, templates cannot be synced");
        } else {
            if (this.coreState.getTemplatesManager() == null) {
                getConfigLogger().debug("CustomTemplates", "templateManager is null, templates cannot be synced");
                return;
            }
            final TemplatesManager templatesManager = this.coreState.getTemplatesManager();
            final NetworkManager networkManager = this.coreState.getNetworkManager();
            getCleverTapID(new OnInitCleverTapIDListener() { // from class: com.clevertap.android.sdk.CleverTapAPI$$ExternalSyntheticLambda15
                @Override // com.clevertap.android.sdk.interfaces.OnInitCleverTapIDListener
                public final void onInitCleverTapID(String str) {
                    this.f$0.m4777x1e44ac85(networkManager, templatesManager, str);
                }
            });
        }
    }

    /* renamed from: lambda$syncRegisteredInAppTemplates$8$com-clevertap-android-sdk-CleverTapAPI, reason: not valid java name */
    /* synthetic */ void m4777x1e44ac85(final NetworkManager networkManager, final TemplatesManager templatesManager, String str) {
        CTExecutorFactory.executors(getConfig()).postAsyncSafelyTask().execute("DefineTemplates", new Callable() { // from class: com.clevertap.android.sdk.CleverTapAPI$$ExternalSyntheticLambda18
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.m4776xc726bba6(networkManager, templatesManager);
            }
        });
    }

    /* renamed from: lambda$syncRegisteredInAppTemplates$7$com-clevertap-android-sdk-CleverTapAPI, reason: not valid java name */
    /* synthetic */ Void m4776xc726bba6(NetworkManager networkManager, TemplatesManager templatesManager) throws Exception {
        networkManager.defineTemplates(this.context, templatesManager.getAllRegisteredTemplates());
        return null;
    }

    public boolean isPushPermissionGranted() {
        if (CTXtensions.isPackageAndOsTargetsAbove(this.context, 32)) {
            return this.coreState.getInAppController().isPushPermissionGranted();
        }
        return false;
    }

    public void promptPushPrimer(JSONObject jSONObject) {
        if (CTXtensions.isPackageAndOsTargetsAbove(this.context, 32)) {
            this.coreState.getInAppController().promptPushPrimer(jSONObject);
        } else {
            Logger.v("Ensure your app supports Android 13 to verify permission access for notifications.");
        }
    }

    public void promptForPushPermission(boolean z) {
        if (CTXtensions.isPackageAndOsTargetsAbove(this.context, 32)) {
            this.coreState.getInAppController().promptPermission(z);
        } else {
            Logger.v("Ensure your app supports Android 13 to verify permission access for notifications.");
        }
    }

    private CleverTapAPI(final Context context, final CleverTapInstanceConfig cleverTapInstanceConfig, String str) {
        this.context = context;
        setCoreState(CleverTapFactory.getCoreState(context, cleverTapInstanceConfig, str));
        getConfigLogger().verbose(cleverTapInstanceConfig.getAccountId() + ":async_deviceID", "CoreState is set");
        CTExecutorFactory.executors(cleverTapInstanceConfig).postAsyncSafelyTask().execute("CleverTapAPI#initializeDeviceInfo", new Callable() { // from class: com.clevertap.android.sdk.CleverTapAPI$$ExternalSyntheticLambda5
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.m4773lambda$new$9$comclevertapandroidsdkCleverTapAPI(cleverTapInstanceConfig);
            }
        });
        if (Utils.getNow() - CoreMetaData.getInitialAppEnteredForegroundTime() > 5) {
            this.coreState.getConfig().setCreatedPostAppLaunch();
        }
        CTExecutorFactory.executors(cleverTapInstanceConfig).postAsyncSafelyTask().execute("setStatesAsync", new Callable() { // from class: com.clevertap.android.sdk.CleverTapAPI$$ExternalSyntheticLambda6
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.m4772lambda$new$10$comclevertapandroidsdkCleverTapAPI();
            }
        });
        CTExecutorFactory.executors(cleverTapInstanceConfig).postAsyncSafelyTask().execute("saveConfigtoSharedPrefs", new Callable() { // from class: com.clevertap.android.sdk.CleverTapAPI$$ExternalSyntheticLambda7
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return CleverTapAPI.lambda$new$11(cleverTapInstanceConfig, context);
            }
        });
        Logger.i("CleverTap SDK initialized with accountId: " + cleverTapInstanceConfig.getAccountId() + " accountToken: " + cleverTapInstanceConfig.getAccountToken() + " accountRegion: " + cleverTapInstanceConfig.getAccountRegion());
    }

    /* renamed from: lambda$new$9$com-clevertap-android-sdk-CleverTapAPI, reason: not valid java name */
    /* synthetic */ Void m4773lambda$new$9$comclevertapandroidsdkCleverTapAPI(CleverTapInstanceConfig cleverTapInstanceConfig) throws Exception {
        if (!cleverTapInstanceConfig.isDefaultInstance()) {
            return null;
        }
        manifestAsyncValidation();
        return null;
    }

    /* renamed from: lambda$new$10$com-clevertap-android-sdk-CleverTapAPI, reason: not valid java name */
    /* synthetic */ Void m4772lambda$new$10$comclevertapandroidsdkCleverTapAPI() throws Exception {
        this.coreState.getSessionManager().setLastVisitTime();
        this.coreState.getSessionManager().setUserLastVisitTs();
        this.coreState.getDeviceInfo().setDeviceNetworkInfoReportingFromStorage();
        this.coreState.getDeviceInfo().setCurrentUserOptOutStateFromStorage();
        return null;
    }

    static /* synthetic */ Void lambda$new$11(CleverTapInstanceConfig cleverTapInstanceConfig, Context context) throws Exception {
        String jSONString = cleverTapInstanceConfig.toJSONString();
        if (jSONString == null) {
            Logger.v("Unable to save config to SharedPrefs, config Json is null");
            return null;
        }
        StorageHelper.putString(context, StorageHelper.storageKeyWithSuffix(cleverTapInstanceConfig, "instance"), jSONString);
        return null;
    }

    public void addMultiValueForKey(String str, String str2) {
        if (str2 == null || str2.isEmpty()) {
            this.coreState.getAnalyticsManager()._generateEmptyMultiValueError(str);
        } else {
            addMultiValuesForKey(str, new ArrayList<>(Collections.singletonList(str2)));
        }
    }

    public void addMultiValuesForKey(String str, ArrayList<String> arrayList) {
        this.coreState.getAnalyticsManager().addMultiValuesForKey(str, arrayList);
    }

    public void deleteInboxMessage(CTInboxMessage cTInboxMessage) {
        if (this.coreState.getControllerManager().getCTInboxController() != null) {
            this.coreState.getControllerManager().getCTInboxController().deleteInboxMessage(cTInboxMessage);
        } else {
            getConfigLogger().debug(getAccountId(), "Notification Inbox not initialized");
        }
    }

    public void deleteInboxMessage(String str) {
        deleteInboxMessage(getInboxMessageForId(str));
    }

    public void deleteInboxMessagesForIDs(ArrayList<String> arrayList) {
        if (this.coreState.getControllerManager().getCTInboxController() != null) {
            this.coreState.getControllerManager().getCTInboxController().deleteInboxMessagesForIDs(arrayList);
        } else {
            getConfigLogger().debug(getAccountId(), "Notification Inbox not initialized");
        }
    }

    public void disablePersonalization() {
        this.coreState.getConfig().enablePersonalization(false);
    }

    public void discardInAppNotifications() {
        if (!getCoreState().getConfig().isAnalyticsOnly()) {
            getConfigLogger().debug(getAccountId(), "Discarding InApp Notifications...");
            getConfigLogger().debug(getAccountId(), "Please Note - InApp Notifications will be dropped till resumeInAppNotifications() is not called again");
            getCoreState().getInAppController().discardInApps();
            return;
        }
        getConfigLogger().debug(getAccountId(), "CleverTap instance is set for Analytics only! Cannot discard InApp Notifications.");
    }

    public void enableDeviceNetworkInfoReporting(boolean z) {
        this.coreState.getDeviceInfo().enableDeviceNetworkInfoReporting(z);
    }

    public void enablePersonalization() {
        this.coreState.getConfig().enablePersonalization(true);
    }

    @Deprecated
    public CTFeatureFlagsController featureFlag() {
        if (getConfig().isAnalyticsOnly()) {
            getConfig().getLogger().debug(getAccountId(), "Feature flag is not supported with analytics only configuration");
        }
        return this.coreState.getControllerManager().getCTFeatureFlagsController();
    }

    public void flush() {
        this.coreState.getBaseEventQueueManager().flush();
    }

    public void setSCDomainListener(SCDomainListener sCDomainListener) {
        String domain;
        this.coreState.getCallbackManager().setSCDomainListener(sCDomainListener);
        if (this.coreState.getNetworkManager() == null || (domain = this.coreState.getNetworkManager().getDomain(EventGroup.REGULAR)) == null) {
            return;
        }
        sCDomainListener.onSCDomainAvailable(Utils.getSCDomain(domain));
    }

    public String getAccountId() {
        return this.coreState.getConfig().getAccountId();
    }

    public ArrayList<CleverTapDisplayUnit> getAllDisplayUnits() {
        if (this.coreState.getControllerManager().getCTDisplayUnitController() != null) {
            return this.coreState.getControllerManager().getCTDisplayUnitController().getAllDisplayUnits();
        }
        getConfigLogger().verbose(getAccountId(), "DisplayUnit : Failed to get all Display Units");
        return null;
    }

    public ArrayList<CTInboxMessage> getAllInboxMessages() {
        Logger.d("CleverTapAPI:getAllInboxMessages: called");
        ArrayList<CTInboxMessage> arrayList = new ArrayList<>();
        synchronized (this.coreState.getCTLockManager().getInboxControllerLock()) {
            if (this.coreState.getControllerManager().getCTInboxController() != null) {
                Iterator<CTMessageDAO> it = this.coreState.getControllerManager().getCTInboxController().getMessages().iterator();
                while (it.hasNext()) {
                    CTMessageDAO next = it.next();
                    Logger.v("CTMessage Dao - " + next.toJSON().toString());
                    arrayList.add(new CTInboxMessage(next.toJSON()));
                }
                return arrayList;
            }
            getConfigLogger().debug(getAccountId(), "Notification Inbox not initialized");
            return arrayList;
        }
    }

    public CTInboxListener getCTNotificationInboxListener() {
        return this.coreState.getCallbackManager().getInboxListener();
    }

    public void setCTNotificationInboxListener(CTInboxListener cTInboxListener) {
        this.coreState.getCallbackManager().setInboxListener(cTInboxListener);
    }

    public CTPushAmpListener getCTPushAmpListener() {
        return this.coreState.getCallbackManager().getPushAmpListener();
    }

    public void setCTPushAmpListener(CTPushAmpListener cTPushAmpListener) {
        this.coreState.getCallbackManager().setPushAmpListener(cTPushAmpListener);
    }

    public CTPushNotificationListener getCTPushNotificationListener() {
        return this.coreState.getCallbackManager().getPushNotificationListener();
    }

    public void setCTPushNotificationListener(CTPushNotificationListener cTPushNotificationListener) {
        this.coreState.getCallbackManager().setPushNotificationListener(cTPushNotificationListener);
    }

    @Deprecated
    public String getCleverTapAttributionIdentifier() {
        return this.coreState.getDeviceInfo().getAttributionID();
    }

    public String getCleverTapID() {
        return this.coreState.getDeviceInfo().getDeviceID();
    }

    public void decrementValue(String str, Number number) {
        this.coreState.getAnalyticsManager().decrementValue(str, number);
    }

    @Deprecated(since = "7.1.0")
    public int getCount(String str) {
        EventDetail eventDetail = this.coreState.getLocalDataStore().getEventDetail(str);
        if (eventDetail != null) {
            return eventDetail.getCount();
        }
        return -1;
    }

    public int getUserEventLogCount(String str) {
        if (getConfig().isPersonalizationEnabled()) {
            return this.coreState.getLocalDataStore().readUserEventLogCount(str);
        }
        return -1;
    }

    @Deprecated(since = "7.1.0")
    public EventDetail getDetails(String str) {
        return this.coreState.getLocalDataStore().getEventDetail(str);
    }

    public UserEventLog getUserEventLog(String str) {
        if (getConfig().isPersonalizationEnabled()) {
            return this.coreState.getLocalDataStore().readUserEventLog(str);
        }
        return null;
    }

    public String getDevicePushToken(PushConstants.PushType pushType) {
        return this.coreState.getPushProviders().getCachedToken(pushType);
    }

    public DevicePushTokenRefreshListener getDevicePushTokenRefreshListener() {
        return this.coreState.getPushProviders().getDevicePushTokenRefreshListener();
    }

    public void setDevicePushTokenRefreshListener(DevicePushTokenRefreshListener devicePushTokenRefreshListener) {
        this.coreState.getPushProviders().setDevicePushTokenRefreshListener(devicePushTokenRefreshListener);
    }

    public void setRequestDevicePushTokenListener(final RequestDevicePushTokenListener requestDevicePushTokenListener) {
        try {
            Logger.v(PushConstants.LOG_TAG, PushConstants.FCM_LOG_TAG + "Requesting FCM token using googleservices.json");
            FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() { // from class: com.clevertap.android.sdk.CleverTapAPI.2
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public void onComplete(Task<String> task) {
                    if (!task.isSuccessful()) {
                        Logger.v(PushConstants.LOG_TAG, PushConstants.FCM_LOG_TAG + "FCM token using googleservices.json failed", task.getException());
                        requestDevicePushTokenListener.onDevicePushToken(null, PushConstants.PushType.FCM);
                    } else {
                        String result = task.getResult() != null ? task.getResult() : null;
                        Logger.v(PushConstants.LOG_TAG, PushConstants.FCM_LOG_TAG + "FCM token using googleservices.json - " + result);
                        requestDevicePushTokenListener.onDevicePushToken(result, PushConstants.PushType.FCM);
                    }
                }
            });
        } catch (Throwable th) {
            Logger.v(PushConstants.LOG_TAG, PushConstants.FCM_LOG_TAG + "Error requesting FCM token", th);
            requestDevicePushTokenListener.onDevicePushToken(null, PushConstants.PushType.FCM);
        }
    }

    public CleverTapDisplayUnit getDisplayUnitForId(String str) {
        if (this.coreState.getControllerManager().getCTDisplayUnitController() != null) {
            return this.coreState.getControllerManager().getCTDisplayUnitController().getDisplayUnitForID(str);
        }
        getConfigLogger().verbose(getAccountId(), "DisplayUnit : Failed to get Display Unit for id: " + str);
        return null;
    }

    @Deprecated(since = "7.1.0")
    public int getFirstTime(String str) {
        EventDetail eventDetail = this.coreState.getLocalDataStore().getEventDetail(str);
        if (eventDetail != null) {
            return eventDetail.getFirstTime();
        }
        return -1;
    }

    public GeofenceCallback getGeofenceCallback() {
        return this.coreState.getCallbackManager().getGeofenceCallback();
    }

    public void setGeofenceCallback(GeofenceCallback geofenceCallback) {
        this.coreState.getCallbackManager().setGeofenceCallback(geofenceCallback);
    }

    @Deprecated(since = "7.1.0")
    public Map<String, EventDetail> getHistory() {
        return this.coreState.getLocalDataStore().getEventHistory(this.context);
    }

    public Map<String, UserEventLog> getUserEventLogHistory() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (!getConfig().isPersonalizationEnabled()) {
            return linkedHashMap;
        }
        for (UserEventLog userEventLog : this.coreState.getLocalDataStore().readUserEventLogs()) {
            linkedHashMap.put(userEventLog.getEventName(), userEventLog);
        }
        return linkedHashMap;
    }

    public InAppNotificationListener getInAppNotificationListener() {
        return this.coreState.getCallbackManager().getInAppNotificationListener();
    }

    public void setInAppNotificationListener(InAppNotificationListener inAppNotificationListener) {
        this.coreState.getCallbackManager().setInAppNotificationListener(inAppNotificationListener);
    }

    public void unregisterPushPermissionNotificationResponseListener(PushPermissionResponseListener pushPermissionResponseListener) {
        this.coreState.getCallbackManager().unregisterPushPermissionResponseListener(pushPermissionResponseListener);
    }

    public void registerPushPermissionNotificationResponseListener(PushPermissionResponseListener pushPermissionResponseListener) {
        this.coreState.getCallbackManager().registerPushPermissionResponseListener(pushPermissionResponseListener);
    }

    public int getInboxMessageCount() {
        synchronized (this.coreState.getCTLockManager().getInboxControllerLock()) {
            if (this.coreState.getControllerManager().getCTInboxController() != null) {
                return this.coreState.getControllerManager().getCTInboxController().count();
            }
            getConfigLogger().debug(getAccountId(), "Notification Inbox not initialized");
            return -1;
        }
    }

    public CTInboxMessage getInboxMessageForId(String str) {
        Logger.d("CleverTapAPI:getInboxMessageForId() called with: messageId = [" + str + "]");
        synchronized (this.coreState.getCTLockManager().getInboxControllerLock()) {
            if (this.coreState.getControllerManager().getCTInboxController() != null) {
                CTMessageDAO messageForId = this.coreState.getControllerManager().getCTInboxController().getMessageForId(str);
                return messageForId != null ? new CTInboxMessage(messageForId.toJSON()) : null;
            }
            getConfigLogger().debug(getAccountId(), "Notification Inbox not initialized");
            return null;
        }
    }

    public int getInboxMessageUnreadCount() {
        synchronized (this.coreState.getCTLockManager().getInboxControllerLock()) {
            if (this.coreState.getControllerManager().getCTInboxController() != null) {
                return this.coreState.getControllerManager().getCTInboxController().unreadCount();
            }
            getConfigLogger().debug(getAccountId(), "Notification Inbox not initialized");
            return -1;
        }
    }

    @Deprecated(since = "7.1.0")
    public int getLastTime(String str) {
        EventDetail eventDetail = this.coreState.getLocalDataStore().getEventDetail(str);
        if (eventDetail != null) {
            return eventDetail.getLastTime();
        }
        return -1;
    }

    public Location getLocation() {
        return this.coreState.getLocationManager()._getLocation();
    }

    public void setLocation(Location location) {
        this.coreState.getLocationManager()._setLocation(location);
    }

    @Deprecated(since = "7.1.0")
    public int getPreviousVisitTime() {
        return this.coreState.getSessionManager().getLastVisitTime();
    }

    public long getUserLastVisitTs() {
        if (getConfig().isPersonalizationEnabled()) {
            return this.coreState.getSessionManager().getUserLastVisitTs();
        }
        return -1L;
    }

    public Object getProperty(String str) {
        if (this.coreState.getConfig().isPersonalizationEnabled()) {
            return this.coreState.getLocalDataStore().getProfileProperty(str);
        }
        return null;
    }

    public String getPushToken(PushConstants.PushType pushType) {
        return this.coreState.getPushProviders().getCachedToken(pushType);
    }

    public int getScreenCount() {
        return CoreMetaData.getActivityCount();
    }

    public SyncListener getSyncListener() {
        return this.coreState.getCallbackManager().getSyncListener();
    }

    public void setSyncListener(SyncListener syncListener) {
        this.coreState.getCallbackManager().setSyncListener(syncListener);
    }

    public int getTimeElapsed() {
        int currentSessionId = this.coreState.getCoreMetaData().getCurrentSessionId();
        if (currentSessionId == 0) {
            return -1;
        }
        return Utils.getNow() - currentSessionId;
    }

    @Deprecated(since = "7.1.0")
    public int getTotalVisits() {
        EventDetail eventDetail = this.coreState.getLocalDataStore().getEventDetail(Constants.APP_LAUNCHED_EVENT);
        if (eventDetail != null) {
            return eventDetail.getCount();
        }
        return 0;
    }

    public int getUserAppLaunchCount() {
        if (getConfig().isPersonalizationEnabled()) {
            return this.coreState.getLocalDataStore().readUserEventLogCount(Constants.APP_LAUNCHED_EVENT);
        }
        return -1;
    }

    public UTMDetail getUTMDetails() {
        UTMDetail uTMDetail = new UTMDetail();
        uTMDetail.setSource(this.coreState.getCoreMetaData().getSource());
        uTMDetail.setMedium(this.coreState.getCoreMetaData().getMedium());
        uTMDetail.setCampaign(this.coreState.getCoreMetaData().getCampaign());
        return uTMDetail;
    }

    public ArrayList<CTInboxMessage> getUnreadInboxMessages() {
        ArrayList<CTInboxMessage> arrayList = new ArrayList<>();
        synchronized (this.coreState.getCTLockManager().getInboxControllerLock()) {
            if (this.coreState.getControllerManager().getCTInboxController() != null) {
                Iterator<CTMessageDAO> it = this.coreState.getControllerManager().getCTInboxController().getUnreadMessages().iterator();
                while (it.hasNext()) {
                    arrayList.add(new CTInboxMessage(it.next().toJSON()));
                }
                return arrayList;
            }
            getConfigLogger().debug(getAccountId(), "Notification Inbox not initialized");
            return arrayList;
        }
    }

    public void initializeInbox() {
        this.coreState.getControllerManager().initializeInbox();
    }

    public void markReadInboxMessage(CTInboxMessage cTInboxMessage) {
        if (this.coreState.getControllerManager().getCTInboxController() != null) {
            this.coreState.getControllerManager().getCTInboxController().markReadInboxMessage(cTInboxMessage);
        } else {
            getConfigLogger().debug(getAccountId(), "Notification Inbox not initialized");
        }
    }

    public void markReadInboxMessage(String str) {
        markReadInboxMessage(getInboxMessageForId(str));
    }

    public void markReadInboxMessagesForIDs(ArrayList<String> arrayList) {
        if (this.coreState.getControllerManager().getCTInboxController() != null) {
            this.coreState.getControllerManager().getCTInboxController().markReadInboxMessagesForIDs(arrayList);
        } else {
            getConfigLogger().debug(getAccountId(), "Notification Inbox not initialized");
        }
    }

    @Override // com.clevertap.android.sdk.inbox.CTInboxActivity.InboxActivityListener
    public void messageDidClick(CTInboxActivity cTInboxActivity, int i, CTInboxMessage cTInboxMessage, Bundle bundle, HashMap<String, String> map, int i2) {
        this.coreState.getAnalyticsManager().pushInboxMessageStateEvent(true, cTInboxMessage, bundle);
        Logger.v("clicked inbox notification.");
        WeakReference<InboxMessageListener> weakReference = this.inboxMessageListener;
        if (weakReference != null && weakReference.get() != null) {
            this.inboxMessageListener.get().onInboxItemClicked(cTInboxMessage, i, i2);
        }
        if (map == null || map.isEmpty()) {
            return;
        }
        Logger.v("clicked button of an inbox notification.");
        WeakReference<InboxMessageButtonListener> weakReference2 = this.inboxMessageButtonListener;
        if (weakReference2 == null || weakReference2.get() == null) {
            return;
        }
        this.inboxMessageButtonListener.get().onInboxButtonClick(map);
    }

    @Override // com.clevertap.android.sdk.inbox.CTInboxActivity.InboxActivityListener
    public void messageDidShow(CTInboxActivity cTInboxActivity, final CTInboxMessage cTInboxMessage, final Bundle bundle) {
        CTExecutorFactory.executors(this.coreState.getConfig()).postAsyncSafelyTask().execute("handleMessageDidShow", new Callable() { // from class: com.clevertap.android.sdk.CleverTapAPI$$ExternalSyntheticLambda19
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.m4771lambda$messageDidShow$12$comclevertapandroidsdkCleverTapAPI(cTInboxMessage, bundle);
            }
        });
    }

    /* renamed from: lambda$messageDidShow$12$com-clevertap-android-sdk-CleverTapAPI, reason: not valid java name */
    /* synthetic */ Void m4771lambda$messageDidShow$12$comclevertapandroidsdkCleverTapAPI(CTInboxMessage cTInboxMessage, Bundle bundle) throws Exception {
        Logger.d("CleverTapAPI:messageDidShow() called  in async with: messageId = [" + cTInboxMessage.getMessageId() + "]");
        if (getInboxMessageForId(cTInboxMessage.getMessageId()).isRead()) {
            return null;
        }
        markReadInboxMessage(cTInboxMessage);
        this.coreState.getAnalyticsManager().pushInboxMessageStateEvent(false, cTInboxMessage, bundle);
        return null;
    }

    public void onUserLogin(Map<String, Object> map, String str) {
        this.coreState.getLoginController().onUserLogin(map, str);
    }

    public void onUserLogin(Map<String, Object> map) {
        onUserLogin(map, null);
    }

    @Deprecated
    public CTProductConfigController productConfig() {
        if (getConfig().isAnalyticsOnly()) {
            getConfig().getLogger().debug(getAccountId(), "Product config is not supported with analytics only configuration");
        }
        return this.coreState.getCtProductConfigController(this.context);
    }

    public void pushBaiduRegistrationId(String str, boolean z) {
        this.coreState.getPushProviders().handleToken(str, PushConstants.PushType.BPS, z);
    }

    public void pushChargedEvent(HashMap<String, Object> map, ArrayList<HashMap<String, Object>> arrayList) {
        this.coreState.getAnalyticsManager().pushChargedEvent(map, arrayList);
    }

    public void pushDeepLink(Uri uri) {
        this.coreState.getAnalyticsManager().pushDeepLink(uri, false);
    }

    public void pushDisplayUnitClickedEventForID(String str) {
        this.coreState.getAnalyticsManager().pushDisplayUnitClickedEventForID(str);
    }

    public void pushDisplayUnitViewedEventForID(String str) {
        this.coreState.getAnalyticsManager().pushDisplayUnitViewedEventForID(str);
    }

    public void pushError(String str, int i) {
        this.coreState.getAnalyticsManager().pushError(str, i);
    }

    public void pushEvent(String str) {
        if (str == null || str.trim().isEmpty()) {
            return;
        }
        pushEvent(str, null);
    }

    public void pushEvent(String str, Map<String, Object> map) {
        this.coreState.getAnalyticsManager().pushEvent(str, map);
    }

    public void pushFcmRegistrationId(String str, boolean z) {
        this.coreState.getPushProviders().handleToken(str, PushConstants.PushType.FCM, z);
    }

    public Future<?> pushSignedCallEvent(String str, JSONObject jSONObject) {
        return this.coreState.getAnalyticsManager().raiseEventForSignedCall(str, jSONObject);
    }

    public void pushGeoFenceError(int i, String str) {
        this.coreState.getValidationResultStack().pushValidationResult(new ValidationResult(i, str));
    }

    public Future<?> pushGeoFenceExitedEvent(JSONObject jSONObject) {
        return this.coreState.getAnalyticsManager().raiseEventForGeofences(Constants.GEOFENCE_EXITED_EVENT_NAME, jSONObject);
    }

    public Future<?> pushGeofenceEnteredEvent(JSONObject jSONObject) {
        return this.coreState.getAnalyticsManager().raiseEventForGeofences(Constants.GEOFENCE_ENTERED_EVENT_NAME, jSONObject);
    }

    public void pushHuaweiRegistrationId(String str, boolean z) {
        this.coreState.getPushProviders().handleToken(str, PushConstants.PushType.HPS, z);
    }

    public void pushInboxNotificationClickedEvent(String str) {
        Logger.v("CleverTapAPI:pushInboxNotificationClickedEvent() called with: messageId = [" + str + "]");
        this.coreState.getAnalyticsManager().pushInboxMessageStateEvent(true, getInboxMessageForId(str), null);
    }

    public void pushInboxNotificationViewedEvent(String str) {
        Logger.v("CleverTapAPI:pushInboxNotificationViewedEvent() called with: messageId = [" + str + "]");
        this.coreState.getAnalyticsManager().pushInboxMessageStateEvent(false, getInboxMessageForId(str), null);
    }

    public void pushInstallReferrer(String str) {
        this.coreState.getAnalyticsManager().pushInstallReferrer(str);
    }

    public synchronized void pushInstallReferrer(String str, String str2, String str3) {
        this.coreState.getAnalyticsManager().pushInstallReferrer(str, str2, str3);
    }

    public void pushNotificationClickedEvent(Bundle bundle) {
        this.coreState.getAnalyticsManager().pushNotificationClickedEvent(bundle);
    }

    public void pushNotificationViewedEvent(Bundle bundle) {
        this.coreState.getAnalyticsManager().pushNotificationViewedEvent(bundle);
    }

    public void pushProfile(Map<String, Object> map) {
        this.coreState.getAnalyticsManager().pushProfile(map);
    }

    public void recordScreen(String str) {
        String screenName = this.coreState.getCoreMetaData().getScreenName();
        if (str != null) {
            if (screenName == null || screenName.isEmpty() || !screenName.equals(str)) {
                getConfigLogger().debug(getAccountId(), "Screen changed to " + str);
                this.coreState.getCoreMetaData().setCurrentScreenName(str);
                this.coreState.getAnalyticsManager().recordPageEventWithExtras(null);
            }
        }
    }

    public void removeMultiValueForKey(String str, String str2) {
        if (str2 == null || str2.isEmpty()) {
            this.coreState.getAnalyticsManager()._generateEmptyMultiValueError(str);
        } else {
            removeMultiValuesForKey(str, new ArrayList<>(Collections.singletonList(str2)));
        }
    }

    public void removeMultiValuesForKey(String str, ArrayList<String> arrayList) {
        this.coreState.getAnalyticsManager().removeMultiValuesForKey(str, arrayList);
    }

    public void removeValueForKey(String str) {
        this.coreState.getAnalyticsManager().removeValueForKey(str);
    }

    public void resumeInAppNotifications() {
        if (!getCoreState().getConfig().isAnalyticsOnly()) {
            getConfigLogger().debug(getAccountId(), "Resuming InApp Notifications...");
            getCoreState().getInAppController().resumeInApps();
        } else {
            getConfigLogger().debug(getAccountId(), "CleverTap instance is set for Analytics only! Cannot resume InApp Notifications.");
        }
    }

    public void incrementValue(String str, Number number) {
        this.coreState.getAnalyticsManager().incrementValue(str, number);
    }

    @Deprecated
    public void setCTFeatureFlagsListener(CTFeatureFlagsListener cTFeatureFlagsListener) {
        this.coreState.getCallbackManager().setFeatureFlagListener(cTFeatureFlagsListener);
    }

    @Deprecated
    public void setCTProductConfigListener(CTProductConfigListener cTProductConfigListener) {
        this.coreState.getCallbackManager().setProductConfigListener(cTProductConfigListener);
    }

    public void setDisplayUnitListener(DisplayUnitListener displayUnitListener) {
        this.coreState.getCallbackManager().setDisplayUnitListener(displayUnitListener);
    }

    public void setInAppNotificationButtonListener(InAppNotificationButtonListener inAppNotificationButtonListener) {
        this.coreState.getCallbackManager().setInAppNotificationButtonListener(inAppNotificationButtonListener);
    }

    public void setInboxMessageButtonListener(InboxMessageButtonListener inboxMessageButtonListener) {
        this.inboxMessageButtonListener = new WeakReference<>(inboxMessageButtonListener);
    }

    public void setCTInboxMessageListener(InboxMessageListener inboxMessageListener) {
        this.inboxMessageListener = new WeakReference<>(inboxMessageListener);
    }

    public static void addNotificationRenderedListener(String str, NotificationRenderedListener notificationRenderedListener) {
        sNotificationRenderedListenerMap.put(str, notificationRenderedListener);
    }

    public static NotificationRenderedListener getNotificationRenderedListener(String str) {
        return sNotificationRenderedListenerMap.get(str);
    }

    public static NotificationRenderedListener removeNotificationRenderedListener(String str) {
        return sNotificationRenderedListenerMap.remove(str);
    }

    public void setLibrary(String str) {
        if (this.coreState.getDeviceInfo() != null) {
            this.coreState.getDeviceInfo().setLibrary(str);
        }
    }

    public Future<?> setLocationForGeofences(Location location, int i) {
        this.coreState.getCoreMetaData().setLocationForGeofence(true);
        this.coreState.getCoreMetaData().setGeofenceSDKVersion(i);
        return this.coreState.getLocationManager()._setLocation(location);
    }

    public void setMultiValuesForKey(String str, ArrayList<String> arrayList) {
        this.coreState.getAnalyticsManager().setMultiValuesForKey(str, arrayList);
    }

    public void setOffline(boolean z) {
        this.coreState.getCoreMetaData().setOffline(z);
        if (z) {
            getConfigLogger().debug(getAccountId(), "CleverTap Instance has been set to offline, won't send events queue");
        } else {
            getConfigLogger().debug(getAccountId(), "CleverTap Instance has been set to online, sending events queue");
            flush();
        }
    }

    public void setOptOut(final boolean z) {
        CTExecutorFactory.executors(this.coreState.getConfig()).postAsyncSafelyTask().execute("setOptOut", new Callable() { // from class: com.clevertap.android.sdk.CleverTapAPI$$ExternalSyntheticLambda3
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.m4775lambda$setOptOut$13$comclevertapandroidsdkCleverTapAPI(z);
            }
        });
    }

    /* renamed from: lambda$setOptOut$13$com-clevertap-android-sdk-CleverTapAPI, reason: not valid java name */
    /* synthetic */ Void m4775lambda$setOptOut$13$comclevertapandroidsdkCleverTapAPI(boolean z) throws Exception {
        HashMap map = new HashMap();
        map.put(Constants.CLEVERTAP_OPTOUT, Boolean.valueOf(z));
        if (z) {
            pushProfile(map);
            this.coreState.getCoreMetaData().setCurrentUserOptedOut(true);
        } else {
            this.coreState.getCoreMetaData().setCurrentUserOptedOut(false);
            pushProfile(map);
        }
        String strOptOutKey = this.coreState.getDeviceInfo().optOutKey();
        if (strOptOutKey == null) {
            getConfigLogger().verbose(getAccountId(), "Unable to persist user OptOut state, storage key is null");
            return null;
        }
        StorageHelper.putBoolean(this.context, StorageHelper.storageKeyWithSuffix(getConfig(), strOptOutKey), z);
        getConfigLogger().verbose(getAccountId(), "Set current user OptOut state to: " + z);
        return null;
    }

    public void showAppInbox(CTInboxStyleConfig cTInboxStyleConfig) {
        synchronized (this.coreState.getCTLockManager().getInboxControllerLock()) {
            if (this.coreState.getControllerManager().getCTInboxController() == null) {
                getConfigLogger().debug(getAccountId(), "Notification Inbox not initialized");
                return;
            }
            CTInboxStyleConfig cTInboxStyleConfig2 = new CTInboxStyleConfig(cTInboxStyleConfig);
            Intent intent = new Intent(this.context, (Class<?>) CTInboxActivity.class);
            intent.putExtra("styleConfig", cTInboxStyleConfig2);
            Bundle bundle = new Bundle();
            bundle.putParcelable("config", getConfig());
            intent.putExtra("configBundle", bundle);
            try {
                Activity currentActivity = CoreMetaData.getCurrentActivity();
                if (currentActivity == null) {
                    throw new IllegalStateException("Current activity reference not found");
                }
                currentActivity.startActivity(intent);
                Logger.d("Displaying Notification Inbox");
            } catch (Throwable th) {
                Logger.v("Please verify the integration of your app. It is not setup to support Notification Inbox yet.", th);
            }
        }
    }

    public void dismissAppInbox() {
        try {
            Activity appInboxActivity = getCoreState().getCoreMetaData().getAppInboxActivity();
            if (appInboxActivity == null) {
                throw new IllegalStateException("AppInboxActivity reference not found");
            }
            if (appInboxActivity.isFinishing()) {
                return;
            }
            getConfigLogger().verbose(getAccountId(), "Finishing the App Inbox");
            appInboxActivity.finish();
        } catch (Throwable th) {
            getConfigLogger().verbose(getAccountId(), "Can't dismiss AppInbox, please ensure to call this method after the usage of cleverTapApiInstance.showAppInbox(). \n" + th);
        }
    }

    public void showAppInbox() {
        showAppInbox(new CTInboxStyleConfig());
    }

    public void suspendInAppNotifications() {
        if (!getCoreState().getConfig().isAnalyticsOnly()) {
            getConfigLogger().debug(getAccountId(), "Suspending InApp Notifications...");
            getConfigLogger().debug(getAccountId(), "Please Note - InApp Notifications will be suspended till resumeInAppNotifications() is not called again");
            getCoreState().getInAppController().suspendInApps();
            return;
        }
        getConfigLogger().debug(getAccountId(), "CleverTap instance is set for Analytics only! Cannot suspend InApp Notifications.");
    }

    public int getCustomSdkVersion(String str) {
        return this.coreState.getCoreMetaData().getCustomSdkVersion(str);
    }

    public void setCustomSdkVersion(String str, int i) {
        this.coreState.getCoreMetaData().setCustomSdkVersion(str, i);
    }

    void deviceIDCreated(final String str) {
        final String accountId = this.coreState.getConfig().getAccountId();
        if (this.coreState.getControllerManager() == null) {
            getConfigLogger().verbose(accountId + ":async_deviceID", "ControllerManager not set yet! Returning from deviceIDCreated()");
            return;
        }
        final StoreRegistry storeRegistry = this.coreState.getStoreRegistry();
        final CryptHandler cryptHandler = this.coreState.getCryptHandler();
        final StoreProvider storeProvider = StoreProvider.getInstance();
        final EvaluationManager evaluationManager = this.coreState.getEvaluationManager();
        this.coreState.getLocalDataStore().inflateLocalProfileAsync(this.context);
        CTExecutorFactory.executors(getConfig()).ioTask().execute("initStores", new Callable() { // from class: com.clevertap.android.sdk.CleverTapAPI$$ExternalSyntheticLambda12
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.m4768lambda$deviceIDCreated$14$comclevertapandroidsdkCleverTapAPI(storeRegistry, storeProvider, cryptHandler, str, accountId, evaluationManager);
            }
        });
        if (this.coreState.getControllerManager().getInAppFCManager() == null) {
            getConfigLogger().verbose(accountId + ":async_deviceID", "Initializing InAppFC after Device ID Created = " + str);
            this.coreState.getControllerManager().setInAppFCManager(new InAppFCManager(this.context, this.coreState.getConfig(), str, this.coreState.getStoreRegistry(), this.coreState.getImpressionManager()));
        }
        CTFeatureFlagsController cTFeatureFlagsController = this.coreState.getControllerManager().getCTFeatureFlagsController();
        if (cTFeatureFlagsController != null && TextUtils.isEmpty(cTFeatureFlagsController.getGuid())) {
            getConfigLogger().verbose(accountId + ":async_deviceID", "Initializing Feature Flags after Device ID Created = " + str);
            cTFeatureFlagsController.setGuidAndInit(str);
        }
        CTProductConfigController cTProductConfigController = this.coreState.getControllerManager().getCTProductConfigController();
        if (cTProductConfigController != null && TextUtils.isEmpty(cTProductConfigController.getSettings().getGuid())) {
            getConfigLogger().verbose(accountId + ":async_deviceID", "Initializing Product Config after Device ID Created = " + str);
            cTProductConfigController.setGuidAndInit(str);
        }
        getConfigLogger().verbose(accountId + ":async_deviceID", "Got device id from DeviceInfo, notifying user profile initialized to SyncListener");
        this.coreState.getCallbackManager().notifyUserProfileInitialized(str);
        this.coreState.getCallbackManager().notifyCleverTapIDChanged(str);
    }

    /* renamed from: lambda$deviceIDCreated$14$com-clevertap-android-sdk-CleverTapAPI, reason: not valid java name */
    /* synthetic */ Void m4768lambda$deviceIDCreated$14$comclevertapandroidsdkCleverTapAPI(StoreRegistry storeRegistry, StoreProvider storeProvider, CryptHandler cryptHandler, String str, String str2, EvaluationManager evaluationManager) throws Exception {
        if (storeRegistry.getInAppStore() == null) {
            InAppStore inAppStoreProvideInAppStore = storeProvider.provideInAppStore(this.context, cryptHandler, str, str2);
            storeRegistry.setInAppStore(inAppStoreProvideInAppStore);
            evaluationManager.loadSuppressedCSAndEvaluatedSSInAppsIds();
            this.coreState.getCallbackManager().addChangeUserCallback(inAppStoreProvideInAppStore);
        }
        if (storeRegistry.getImpressionStore() != null) {
            return null;
        }
        ImpressionStore impressionStoreProvideImpressionStore = storeProvider.provideImpressionStore(this.context, str, str2);
        storeRegistry.setImpressionStore(impressionStoreProvideImpressionStore);
        this.coreState.getCallbackManager().addChangeUserCallback(impressionStoreProvideImpressionStore);
        return null;
    }

    private CleverTapInstanceConfig getConfig() {
        return this.coreState.getConfig();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Logger getConfigLogger() {
        return getConfig().getLogger();
    }

    private boolean isErrorDeviceId() {
        return this.coreState.getDeviceInfo().isErrorDeviceId();
    }

    private void manifestAsyncValidation() {
        CTExecutorFactory.executors(this.coreState.getConfig()).postAsyncSafelyTask().execute("Manifest Validation", new Callable() { // from class: com.clevertap.android.sdk.CleverTapAPI$$ExternalSyntheticLambda13
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.m4770x8cfca0b1();
            }
        });
    }

    /* renamed from: lambda$manifestAsyncValidation$15$com-clevertap-android-sdk-CleverTapAPI, reason: not valid java name */
    /* synthetic */ Void m4770x8cfca0b1() throws Exception {
        ManifestValidator.validate(this.context, this.coreState.getDeviceInfo(), this.coreState.getPushProviders());
        return null;
    }

    private void pushAmazonRegistrationId(String str, boolean z) {
        this.coreState.getPushProviders().handleToken(str, PushConstants.PushType.ADM, z);
    }

    static void onActivityCreated(Activity activity) {
        onActivityCreated(activity, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x005c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static void onActivityCreated(android.app.Activity r7, java.lang.String r8) {
        /*
            java.lang.String r0 = "wzrk_from"
            java.lang.String r1 = "wzrk_acct_id"
            java.lang.String r2 = "ActivityLifecycleCallback: Notification Clicked already processed for "
            java.util.HashMap<java.lang.String, com.clevertap.android.sdk.CleverTapAPI> r3 = com.clevertap.android.sdk.CleverTapAPI.instances
            r4 = 0
            if (r3 != 0) goto L14
            android.content.Context r3 = r7.getApplicationContext()
            createInstanceIfAvailable(r3, r4, r8)
        L14:
            java.util.HashMap<java.lang.String, com.clevertap.android.sdk.CleverTapAPI> r8 = com.clevertap.android.sdk.CleverTapAPI.instances
            if (r8 != 0) goto L1e
            java.lang.String r7 = "Instances is null in onActivityCreated!"
            com.clevertap.android.sdk.Logger.v(r7)
            return
        L1e:
            r8 = 1
            android.content.Intent r3 = r7.getIntent()     // Catch: java.lang.Throwable -> L36
            android.net.Uri r3 = r3.getData()     // Catch: java.lang.Throwable -> L36
            if (r3 == 0) goto L37
            java.lang.String r5 = r3.toString()     // Catch: java.lang.Throwable -> L37
            android.os.Bundle r5 = com.clevertap.android.sdk.utils.UriHelper.getAllKeyValuePairs(r5, r8)     // Catch: java.lang.Throwable -> L37
            java.lang.String r5 = r5.getString(r1)     // Catch: java.lang.Throwable -> L37
            goto L38
        L36:
            r3 = r4
        L37:
            r5 = r4
        L38:
            r6 = 0
            android.content.Intent r7 = r7.getIntent()     // Catch: java.lang.Throwable -> L86
            android.os.Bundle r4 = r7.getExtras()     // Catch: java.lang.Throwable -> L86
            if (r4 == 0) goto L86
            boolean r7 = r4.isEmpty()     // Catch: java.lang.Throwable -> L86
            if (r7 != 0) goto L86
            boolean r7 = r4.containsKey(r0)     // Catch: java.lang.Throwable -> L86
            if (r7 == 0) goto L5c
            java.lang.String r7 = "CTPushNotificationReceiver"
            java.lang.Object r0 = r4.get(r0)     // Catch: java.lang.Throwable -> L86
            boolean r7 = r7.equals(r0)     // Catch: java.lang.Throwable -> L86
            if (r7 == 0) goto L5c
            goto L5d
        L5c:
            r8 = r6
        L5d:
            if (r8 == 0) goto L78
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L76
            r7.<init>(r2)     // Catch: java.lang.Throwable -> L76
            java.lang.StringBuilder r7 = r7.append(r4)     // Catch: java.lang.Throwable -> L76
            java.lang.String r0 = ", dropping duplicate."
            java.lang.StringBuilder r7 = r7.append(r0)     // Catch: java.lang.Throwable -> L76
            java.lang.String r7 = r7.toString()     // Catch: java.lang.Throwable -> L76
            com.clevertap.android.sdk.Logger.v(r7)     // Catch: java.lang.Throwable -> L76
            goto L78
        L76:
            r6 = r8
            goto L86
        L78:
            boolean r7 = r4.containsKey(r1)     // Catch: java.lang.Throwable -> L76
            if (r7 == 0) goto L76
            java.lang.Object r7 = r4.get(r1)     // Catch: java.lang.Throwable -> L76
            java.lang.String r7 = (java.lang.String) r7     // Catch: java.lang.Throwable -> L76
            r5 = r7
            goto L76
        L86:
            if (r6 == 0) goto L8b
            if (r3 != 0) goto L8b
            return
        L8b:
            java.util.HashMap<java.lang.String, com.clevertap.android.sdk.CleverTapAPI> r7 = com.clevertap.android.sdk.CleverTapAPI.instances     // Catch: java.lang.Throwable -> Lb5
            java.util.Set r7 = r7.keySet()     // Catch: java.lang.Throwable -> Lb5
            java.util.Iterator r7 = r7.iterator()     // Catch: java.lang.Throwable -> Lb5
        L95:
            boolean r8 = r7.hasNext()     // Catch: java.lang.Throwable -> Lb5
            if (r8 == 0) goto Lcc
            java.lang.Object r8 = r7.next()     // Catch: java.lang.Throwable -> Lb5
            java.lang.String r8 = (java.lang.String) r8     // Catch: java.lang.Throwable -> Lb5
            java.util.HashMap<java.lang.String, com.clevertap.android.sdk.CleverTapAPI> r0 = com.clevertap.android.sdk.CleverTapAPI.instances     // Catch: java.lang.Throwable -> Lb5
            java.lang.Object r8 = r0.get(r8)     // Catch: java.lang.Throwable -> Lb5
            com.clevertap.android.sdk.CleverTapAPI r8 = (com.clevertap.android.sdk.CleverTapAPI) r8     // Catch: java.lang.Throwable -> Lb5
            if (r8 == 0) goto L95
            com.clevertap.android.sdk.CoreState r8 = r8.coreState     // Catch: java.lang.Throwable -> Lb5
            com.clevertap.android.sdk.ActivityLifeCycleManager r8 = r8.getActivityLifeCycleManager()     // Catch: java.lang.Throwable -> Lb5
            r8.onActivityCreated(r4, r3, r5)     // Catch: java.lang.Throwable -> Lb5
            goto L95
        Lb5:
            r7 = move-exception
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r0 = "Throwable - "
            r8.<init>(r0)
            java.lang.String r7 = r7.getLocalizedMessage()
            java.lang.StringBuilder r7 = r8.append(r7)
            java.lang.String r7 = r7.toString()
            com.clevertap.android.sdk.Logger.v(r7)
        Lcc:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.clevertap.android.sdk.CleverTapAPI.onActivityCreated(android.app.Activity, java.lang.String):void");
    }

    private static CleverTapAPI createInstanceIfAvailable(Context context, String str) {
        return createInstanceIfAvailable(context, str, null);
    }

    private static CleverTapAPI createInstanceIfAvailable(Context context, String str, String str2) {
        try {
            if (str == null) {
                try {
                    return getDefaultInstance(context, str2);
                } catch (Throwable th) {
                    Logger.v("Error creating shared Instance: ", th.getCause());
                    return null;
                }
            }
            String string = StorageHelper.getString(context, "instance:" + str, "");
            if (!string.isEmpty()) {
                CleverTapInstanceConfig cleverTapInstanceConfigCreateInstance = CleverTapInstanceConfig.createInstance(string);
                Logger.v("Inflated Instance Config: " + string);
                if (cleverTapInstanceConfigCreateInstance != null) {
                    return instanceWithConfig(context, cleverTapInstanceConfigCreateInstance, str2);
                }
                return null;
            }
            try {
                CleverTapAPI defaultInstance = getDefaultInstance(context);
                if (defaultInstance == null) {
                    return null;
                }
                if (defaultInstance.coreState.getConfig().getAccountId().equals(str)) {
                    return defaultInstance;
                }
                return null;
            } catch (Throwable th2) {
                Logger.v("Error creating shared Instance: ", th2.getCause());
            }
        } catch (Throwable unused) {
        }
        return null;
    }

    public static ArrayList<CleverTapAPI> getAvailableInstances(Context context) {
        ArrayList<CleverTapAPI> arrayList = new ArrayList<>();
        HashMap<String, CleverTapAPI> map = instances;
        if (map == null || map.isEmpty()) {
            CleverTapAPI defaultInstance = getDefaultInstance(context);
            if (defaultInstance != null) {
                arrayList.add(defaultInstance);
            }
        } else {
            arrayList.addAll(instances.values());
        }
        return arrayList;
    }

    private static CleverTapInstanceConfig getDefaultConfig(Context context) {
        ManifestInfo manifestInfo = ManifestInfo.getInstance(context);
        String accountId = manifestInfo.getAccountId();
        String acountToken = manifestInfo.getAcountToken();
        String accountRegion = manifestInfo.getAccountRegion();
        String proxyDomain = manifestInfo.getProxyDomain();
        String spikeyProxyDomain = manifestInfo.getSpikeyProxyDomain();
        String handshakeDomain = manifestInfo.getHandshakeDomain();
        if (accountId == null || acountToken == null) {
            Logger.i("Account ID or Account token is missing from AndroidManifest.xml, unable to create default instance");
            return null;
        }
        if (accountRegion == null) {
            Logger.i("Account Region not specified in the AndroidManifest - using default region");
        }
        CleverTapInstanceConfig cleverTapInstanceConfigCreateDefaultInstance = CleverTapInstanceConfig.createDefaultInstance(context, accountId, acountToken, accountRegion);
        if (proxyDomain != null && !proxyDomain.trim().isEmpty()) {
            cleverTapInstanceConfigCreateDefaultInstance.setProxyDomain(proxyDomain);
        }
        if (spikeyProxyDomain != null && !spikeyProxyDomain.trim().isEmpty()) {
            cleverTapInstanceConfigCreateDefaultInstance.setSpikyProxyDomain(spikeyProxyDomain);
        }
        if (handshakeDomain != null && !handshakeDomain.trim().isEmpty()) {
            cleverTapInstanceConfigCreateDefaultInstance.setCustomHandshakeDomain(handshakeDomain);
        }
        return cleverTapInstanceConfigCreateDefaultInstance;
    }

    private static CleverTapAPI getDefaultInstanceOrFirstOther(Context context) {
        HashMap<String, CleverTapAPI> map;
        CleverTapAPI defaultInstance = getDefaultInstance(context);
        if (defaultInstance == null && (map = instances) != null && !map.isEmpty()) {
            Iterator<String> it = instances.keySet().iterator();
            while (it.hasNext()) {
                defaultInstance = instances.get(it.next());
                if (defaultInstance != null) {
                    break;
                }
            }
        }
        return defaultInstance;
    }

    public void getCleverTapID(final OnInitCleverTapIDListener onInitCleverTapIDListener) {
        CTExecutorFactory.executors(getConfig()).ioTask().execute("getCleverTapID", new Callable() { // from class: com.clevertap.android.sdk.CleverTapAPI$$ExternalSyntheticLambda2
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.m4769lambda$getCleverTapID$17$comclevertapandroidsdkCleverTapAPI(onInitCleverTapIDListener);
            }
        });
    }

    /* renamed from: lambda$getCleverTapID$17$com-clevertap-android-sdk-CleverTapAPI, reason: not valid java name */
    /* synthetic */ Void m4769lambda$getCleverTapID$17$comclevertapandroidsdkCleverTapAPI(final OnInitCleverTapIDListener onInitCleverTapIDListener) throws Exception {
        final String deviceID = this.coreState.getDeviceInfo().getDeviceID();
        if (deviceID != null) {
            Utils.runOnUiThread(new Runnable() { // from class: com.clevertap.android.sdk.CleverTapAPI$$ExternalSyntheticLambda17
                @Override // java.lang.Runnable
                public final void run() {
                    onInitCleverTapIDListener.onInitCleverTapID(deviceID);
                }
            });
        }
        this.coreState.getCallbackManager().addOnInitCleverTapIDListener(onInitCleverTapIDListener);
        return null;
    }

    public void removeCleverTapIDListener(OnInitCleverTapIDListener onInitCleverTapIDListener) {
        CoreState coreState = this.coreState;
        if (coreState == null || coreState.getCallbackManager() == null) {
            return;
        }
        this.coreState.getCallbackManager().removeOnInitCleverTapIDListener(onInitCleverTapIDListener);
    }

    public Future<?> renderPushNotification(final INotificationRenderer iNotificationRenderer, final Context context, final Bundle bundle) {
        CleverTapInstanceConfig config = this.coreState.getConfig();
        try {
            return CTExecutorFactory.executors(config).postAsyncSafelyTask().submit("CleverTapAPI#renderPushNotification", new Callable() { // from class: com.clevertap.android.sdk.CleverTapAPI$$ExternalSyntheticLambda0
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return this.f$0.m4774xc736d0dd(iNotificationRenderer, bundle, context);
                }
            });
        } catch (Throwable th) {
            config.getLogger().debug(config.getAccountId(), "Failed to process renderPushNotification()", th);
            return null;
        }
    }

    /* renamed from: lambda$renderPushNotification$18$com-clevertap-android-sdk-CleverTapAPI, reason: not valid java name */
    /* synthetic */ Void m4774xc736d0dd(INotificationRenderer iNotificationRenderer, Bundle bundle, Context context) throws Exception {
        synchronized (this.coreState.getPushProviders().getPushRenderingLock()) {
            this.coreState.getPushProviders().setPushNotificationRenderer(iNotificationRenderer);
            if (bundle == null || !bundle.containsKey(Constants.PT_NOTIF_ID)) {
                this.coreState.getPushProviders()._createNotification(context, bundle, -1000);
            } else {
                this.coreState.getPushProviders()._createNotification(context, bundle, bundle.getInt(Constants.PT_NOTIF_ID));
            }
        }
        return null;
    }

    public void renderPushNotificationOnCallerThread(INotificationRenderer iNotificationRenderer, Context context, Bundle bundle) {
        CleverTapInstanceConfig config = this.coreState.getConfig();
        try {
            synchronized (this.coreState.getPushProviders().getPushRenderingLock()) {
                config.getLogger().verbose(config.getAccountId(), "rendering push on caller thread with id = " + Thread.currentThread().getId());
                this.coreState.getPushProviders().setPushNotificationRenderer(iNotificationRenderer);
                if (bundle != null && bundle.containsKey(Constants.PT_NOTIF_ID)) {
                    this.coreState.getPushProviders()._createNotification(context, bundle, bundle.getInt(Constants.PT_NOTIF_ID));
                } else {
                    this.coreState.getPushProviders()._createNotification(context, bundle, -1000);
                }
            }
        } catch (Throwable th) {
            config.getLogger().debug(config.getAccountId(), "Failed to process renderPushNotification()", th);
        }
    }

    public static Bitmap getNotificationBitmapWithTimeoutAndSize(Context context, Bundle bundle, String str, boolean z, long j, int i) {
        if (checkNotificationBitmapRequestInvalid(context, bundle, j)) {
            return null;
        }
        if (i < 1) {
            Logger.v("Given sizeInBytes is less than 1 bytes. Not downloading bitmap!");
            return null;
        }
        CleverTapAPI cleverTapAPIFromBundle = fromBundle(context, bundle);
        if (cleverTapAPIFromBundle == null) {
            Logger.v("cleverTapAPI is null. Not downloading bitmap!");
            return null;
        }
        return Utils.getNotificationBitmapWithTimeoutAndSize(str, z, context, cleverTapAPIFromBundle.getConfig(), j, i).getBitmap();
    }

    private static boolean checkNotificationBitmapRequestInvalid(Context context, Bundle bundle, long j) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            Logger.v("Notification Bitmap Download is not allowed on main thread");
            return true;
        }
        if (context == null) {
            Logger.v("Given Context is null. Not downloading bitmap!");
            return true;
        }
        if (bundle == null) {
            Logger.v("Given Bundle is null. Not downloading bitmap!");
            return true;
        }
        if (j < 1) {
            Logger.v("Given timeoutInMillis is less than 1 millis. Not downloading bitmap!");
            return true;
        }
        if (j <= 20000) {
            return false;
        }
        Logger.v("Given timeoutInMillis exceeds 20 secs limit. Not downloading bitmap!");
        return true;
    }

    public static Bitmap getNotificationBitmapWithTimeout(Context context, Bundle bundle, String str, boolean z, long j) {
        if (checkNotificationBitmapRequestInvalid(context, bundle, j)) {
            return null;
        }
        CleverTapAPI cleverTapAPIFromBundle = fromBundle(context, bundle);
        if (cleverTapAPIFromBundle == null) {
            Logger.v("cleverTapAPI is null. Not downloading bitmap!");
            return null;
        }
        return Utils.getNotificationBitmapWithTimeout(str, z, context, cleverTapAPIFromBundle.getConfig(), j).getBitmap();
    }

    public boolean isDevelopmentMode() {
        Context context = this.context;
        return context != null && isDevelopmentMode(context);
    }

    public <T> Var<T> defineVariable(String str, T t) {
        return Var.define(str, t, this.coreState.getCTVariables());
    }

    public Var<String> defineFileVariable(String str) {
        return Var.define(str, null, "file", this.coreState.getCTVariables());
    }

    public void parseVariables(Object... objArr) {
        this.coreState.getParser().parseVariables(objArr);
    }

    public void parseVariablesForClasses(Class<?>... clsArr) {
        this.coreState.getParser().parseVariablesForClasses(clsArr);
    }

    public Object getVariableValue(String str) {
        if (str == null) {
            return null;
        }
        return this.coreState.getVarCache().getMergedValue(str);
    }

    public <T> Var<T> getVariable(String str) {
        if (str == null) {
            return null;
        }
        return this.coreState.getVarCache().getVariable(str);
    }

    public void fetchVariables() throws JSONException {
        fetchVariables(null);
    }

    public void fetchInApps(FetchInAppsCallback fetchInAppsCallback) throws JSONException {
        if (this.coreState.getConfig().isAnalyticsOnly()) {
            return;
        }
        Logger.v("InApp :  Fetching In Apps...");
        if (fetchInAppsCallback != null) {
            this.coreState.getCallbackManager().setFetchInAppsCallback(fetchInAppsCallback);
        }
        this.coreState.getAnalyticsManager().sendFetchEvent(getFetchRequestAsJson(5));
    }

    private JSONObject getFetchRequestAsJson(int i) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("t", i);
            jSONObject.put(Constants.KEY_EVT_NAME, Constants.WZRK_FETCH);
            jSONObject.put(Constants.KEY_EVT_DATA, jSONObject2);
        } catch (JSONException e) {
            Logger.v(Constants.CLEVERTAP_LOG_TAG, "Failed while parsing fetch request as json:", e);
        }
        return jSONObject;
    }

    public void fetchVariables(FetchVariablesCallback fetchVariablesCallback) throws JSONException {
        if (this.coreState.getConfig().isAnalyticsOnly()) {
            return;
        }
        Logger.v("variables", "Fetching  variables");
        if (fetchVariablesCallback != null) {
            this.coreState.getCallbackManager().setFetchVariablesCallback(fetchVariablesCallback);
        }
        this.coreState.getAnalyticsManager().sendFetchEvent(getFetchRequestAsJson(4));
    }

    public void syncVariables() {
        if (isDevelopmentMode()) {
            Logger.v("variables", "syncVariables: waiting for id to be available");
            getCleverTapID(new OnInitCleverTapIDListener() { // from class: com.clevertap.android.sdk.CleverTapAPI$$ExternalSyntheticLambda8
                @Override // com.clevertap.android.sdk.interfaces.OnInitCleverTapIDListener
                public final void onInitCleverTapID(String str) {
                    this.f$0.m4778lambda$syncVariables$19$comclevertapandroidsdkCleverTapAPI(str);
                }
            });
        } else {
            Logger.v("variables", "Your app is NOT in development mode, variables data will not be sent to server");
        }
    }

    /* renamed from: lambda$syncVariables$19$com-clevertap-android-sdk-CleverTapAPI, reason: not valid java name */
    /* synthetic */ void m4778lambda$syncVariables$19$comclevertapandroidsdkCleverTapAPI(String str) {
        JSONObject defineVarsData = this.coreState.getVarCache().getDefineVarsData();
        Logger.v("variables", "syncVariables: sending following vars to server:" + defineVarsData);
        this.coreState.getAnalyticsManager().pushDefineVarsEvent(defineVarsData);
    }

    public void addVariablesChangedCallback(VariablesChangedCallback variablesChangedCallback) {
        this.coreState.getCTVariables().addVariablesChangedCallback(variablesChangedCallback);
    }

    public void addOneTimeVariablesChangedCallback(VariablesChangedCallback variablesChangedCallback) {
        this.coreState.getCTVariables().addOneTimeVariablesChangedCallback(variablesChangedCallback);
    }

    public void removeVariablesChangedCallback(VariablesChangedCallback variablesChangedCallback) {
        this.coreState.getCTVariables().removeVariablesChangedCallback(variablesChangedCallback);
    }

    public void removeOneTimeVariablesChangedCallback(VariablesChangedCallback variablesChangedCallback) {
        this.coreState.getCTVariables().removeOneTimeVariablesChangedHandler(variablesChangedCallback);
    }

    public void onVariablesChangedAndNoDownloadsPending(VariablesChangedCallback variablesChangedCallback) {
        this.coreState.getCTVariables().onVariablesChangedAndNoDownloadsPending(variablesChangedCallback);
    }

    public void onceVariablesChangedAndNoDownloadsPending(VariablesChangedCallback variablesChangedCallback) {
        this.coreState.getCTVariables().onceVariablesChangedAndNoDownloadsPending(variablesChangedCallback);
    }

    public void removeAllVariablesChangedCallbacks() {
        this.coreState.getCTVariables().removeAllVariablesChangedCallbacks();
    }

    public void removeAllOneTimeVariablesChangedCallbacks() {
        this.coreState.getCTVariables().removeAllOneTimeVariablesChangedCallbacks();
    }

    public void setLocale(String str) {
        if (TextUtils.isEmpty(str)) {
            Logger.i("Empty Locale provided for setLocale, not setting it");
        } else {
            this.coreState.getDeviceInfo().setCustomLocale(str);
        }
    }

    public String getLocale() {
        return this.coreState.getDeviceInfo().getCustomLocale();
    }

    public void clearInAppResources(boolean z) {
        Logger configLogger = getConfigLogger();
        StoreRegistry storeRegistry = this.coreState.getStoreRegistry();
        if (storeRegistry == null) {
            configLogger.info("There was a problem clearing resources because instance is not completely initialised, please try again after some time");
            return;
        }
        FileResourcesRepoImpl fileResourcesRepoImplCreateFileResourcesRepo = FileResourcesRepoFactory.createFileResourcesRepo(this.context, configLogger, storeRegistry);
        if (z) {
            fileResourcesRepoImplCreateFileResourcesRepo.cleanupExpiredResources(CtCacheType.IMAGE);
        } else {
            fileResourcesRepoImplCreateFileResourcesRepo.cleanupAllResources(CtCacheType.IMAGE);
        }
    }

    public void clearFileResources(boolean z) {
        Logger configLogger = getConfigLogger();
        StoreRegistry storeRegistry = this.coreState.getStoreRegistry();
        if (storeRegistry == null) {
            configLogger.info("There was a problem clearing file resources because instance is not completely initialised, please try again after some time");
            return;
        }
        FileResourcesRepoImpl fileResourcesRepoImplCreateFileResourcesRepo = FileResourcesRepoFactory.createFileResourcesRepo(this.context, configLogger, storeRegistry);
        if (z) {
            fileResourcesRepoImplCreateFileResourcesRepo.cleanupExpiredResources(CtCacheType.FILES);
        } else {
            fileResourcesRepoImplCreateFileResourcesRepo.cleanupAllResources(CtCacheType.FILES);
        }
    }
}
