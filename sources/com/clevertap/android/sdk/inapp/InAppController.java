package com.clevertap.android.sdk.inapp;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import com.clevertap.android.sdk.AnalyticsManager;
import com.clevertap.android.sdk.BaseCallbackManager;
import com.clevertap.android.sdk.CTPreferenceCache;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.ControllerManager;
import com.clevertap.android.sdk.CoreMetaData;
import com.clevertap.android.sdk.DeviceInfo;
import com.clevertap.android.sdk.InAppNotificationActivity;
import com.clevertap.android.sdk.InAppNotificationListener;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.ManifestInfo;
import com.clevertap.android.sdk.PushPermissionManager;
import com.clevertap.android.sdk.PushPermissionResponseListener;
import com.clevertap.android.sdk.StorageHelper;
import com.clevertap.android.sdk.Utils;
import com.clevertap.android.sdk.inapp.customtemplates.CustomTemplate;
import com.clevertap.android.sdk.inapp.customtemplates.CustomTemplateInAppData;
import com.clevertap.android.sdk.inapp.customtemplates.TemplatesManager;
import com.clevertap.android.sdk.inapp.data.CtCacheType;
import com.clevertap.android.sdk.inapp.data.InAppResponseAdapter;
import com.clevertap.android.sdk.inapp.evaluation.EvaluationManager;
import com.clevertap.android.sdk.inapp.images.FileResourceProvider;
import com.clevertap.android.sdk.inapp.images.repo.FileResourcesRepoImpl;
import com.clevertap.android.sdk.inapp.store.preference.StoreRegistry;
import com.clevertap.android.sdk.network.NetworkManager;
import com.clevertap.android.sdk.task.CTExecutorFactory;
import com.clevertap.android.sdk.task.MainLooperHandler;
import com.clevertap.android.sdk.utils.JsonUtilsKt;
import com.clevertap.android.sdk.variables.JsonUtil;
import com.clevertap.android.sdk.video.VideoLibChecker;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Callable;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class InAppController implements InAppListener, InAppNotificationActivity.PushPermissionResultCallback {
    public static final String DISPLAY_HARD_PERMISSION_BUNDLE_KEY = "displayHardPermissionDialog";
    public static final String IS_FIRST_TIME_PERMISSION_REQUEST = "firstTimeRequest";
    public static final String IS_HARD_PERMISSION_REQUEST = "isHardPermissionRequest";
    public static final String LOCAL_INAPP_COUNT = "local_in_app_count";
    public static final String SHOW_FALLBACK_SETTINGS_BUNDLE_KEY = "shouldShowFallbackSettings";
    private static CTInAppNotification currentlyDisplayingInApp;
    private static final List<CTInAppNotification> pendingNotifications = Collections.synchronizedList(new ArrayList());
    private final AnalyticsManager analyticsManager;
    private final BaseCallbackManager callbackManager;
    private final CleverTapInstanceConfig config;
    private final Context context;
    private final ControllerManager controllerManager;
    private final CoreMetaData coreMetaData;
    private final DeviceInfo deviceInfo;
    private final EvaluationManager evaluationManager;
    private final InAppQueue inAppQueue;
    private final Logger logger;
    private final MainLooperHandler mainLooperHandler;
    public final Function0<Unit> onAppLaunchEventSent;
    private final FileResourceProvider resourceProvider;
    private final StoreRegistry storeRegistry;
    private final TemplatesManager templatesManager;
    private HashSet<String> inappActivityExclude = null;
    private InAppState inAppState = InAppState.RESUMED;

    public StoreRegistry getStoreRegistry() {
        return this.storeRegistry;
    }

    public TemplatesManager getTemplatesManager() {
        return this.templatesManager;
    }

    private final class NotificationPrepareRunnable implements Runnable {
        private final WeakReference<InAppController> inAppControllerWeakReference;
        private final JSONObject jsonObject;
        private final boolean videoSupport = VideoLibChecker.haveVideoPlayerSupport;

        NotificationPrepareRunnable(InAppController inAppController, JSONObject jSONObject) {
            this.inAppControllerWeakReference = new WeakReference<>(inAppController);
            this.jsonObject = jSONObject;
        }

        @Override // java.lang.Runnable
        public void run() {
            CTInAppNotification cTInAppNotificationInitWithJSON = new CTInAppNotification().initWithJSON(this.jsonObject, this.videoSupport);
            if (cTInAppNotificationInitWithJSON.getError() != null) {
                InAppController.this.logger.debug(InAppController.this.config.getAccountId(), "Unable to parse inapp notification " + cTInAppNotificationInitWithJSON.getError());
            } else {
                prepareForDisplay(cTInAppNotificationInitWithJSON);
            }
        }

        void prepareForDisplay(CTInAppNotification cTInAppNotification) {
            String templateName;
            List<String> listEmptyList;
            Pair pair = new Pair(InAppController.this.storeRegistry.getFilesStore(), InAppController.this.storeRegistry.getInAppAssetsStore());
            if (CTInAppType.CTInAppTypeCustomCodeTemplate.equals(cTInAppNotification.getInAppType())) {
                CustomTemplateInAppData customTemplateData = cTInAppNotification.getCustomTemplateData();
                if (customTemplateData != null) {
                    templateName = customTemplateData.getTemplateName();
                    listEmptyList = customTemplateData.getFileArgsUrls(InAppController.this.templatesManager);
                } else {
                    listEmptyList = Collections.emptyList();
                    templateName = null;
                }
                for (int i = 0; i < listEmptyList.size(); i++) {
                    String str = listEmptyList.get(i);
                    byte[] bArrFetchFile = InAppController.this.resourceProvider.fetchFile(str);
                    if (bArrFetchFile != null && bArrFetchFile.length > 0) {
                        FileResourcesRepoImpl.saveUrlExpiryToStore(new Pair(str, CtCacheType.FILES), pair);
                    } else {
                        cTInAppNotification.setError("Error processing the custom code in-app template: file download failed.");
                        break;
                    }
                }
            } else {
                Iterator<CTInAppNotificationMedia> it = cTInAppNotification.getMediaList().iterator();
                while (it.hasNext()) {
                    CTInAppNotificationMedia next = it.next();
                    if (next.isGIF()) {
                        byte[] bArrFetchInAppGifV1 = InAppController.this.resourceProvider.fetchInAppGifV1(next.getMediaUrl());
                        if (bArrFetchInAppGifV1 == null || bArrFetchInAppGifV1.length == 0) {
                            cTInAppNotification.setError("Error processing GIF");
                            break;
                        }
                    } else if (next.isImage()) {
                        if (InAppController.this.resourceProvider.fetchInAppImageV1(next.getMediaUrl()) == null) {
                            cTInAppNotification.setError("Error processing image as bitmap was NULL");
                        }
                    } else if (next.isVideo() || next.isAudio()) {
                        if (!cTInAppNotification.isVideoSupported()) {
                            cTInAppNotification.setError("InApp Video/Audio is not supported");
                        }
                    }
                }
                templateName = null;
            }
            InAppController inAppController = this.inAppControllerWeakReference.get();
            if (inAppController != null) {
                inAppController.notificationReady(cTInAppNotification, templateName != null ? InAppController.this.templatesManager.getTemplate(templateName) : null);
            }
        }
    }

    private enum InAppState {
        DISCARDED(-1),
        SUSPENDED(0),
        RESUMED(1);

        final int state;

        int intValue() {
            return this.state;
        }

        InAppState(int i) {
            this.state = i;
        }
    }

    public InAppController(Context context, CleverTapInstanceConfig cleverTapInstanceConfig, MainLooperHandler mainLooperHandler, ControllerManager controllerManager, BaseCallbackManager baseCallbackManager, AnalyticsManager analyticsManager, final CoreMetaData coreMetaData, final DeviceInfo deviceInfo, InAppQueue inAppQueue, final EvaluationManager evaluationManager, FileResourceProvider fileResourceProvider, TemplatesManager templatesManager, StoreRegistry storeRegistry) {
        this.context = context;
        this.config = cleverTapInstanceConfig;
        this.logger = cleverTapInstanceConfig.getLogger();
        this.mainLooperHandler = mainLooperHandler;
        this.controllerManager = controllerManager;
        this.callbackManager = baseCallbackManager;
        this.analyticsManager = analyticsManager;
        this.coreMetaData = coreMetaData;
        this.deviceInfo = deviceInfo;
        this.resourceProvider = fileResourceProvider;
        this.inAppQueue = inAppQueue;
        this.evaluationManager = evaluationManager;
        this.templatesManager = templatesManager;
        this.storeRegistry = storeRegistry;
        this.onAppLaunchEventSent = new Function0() { // from class: com.clevertap.android.sdk.inapp.InAppController$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return this.f$0.m4794lambda$new$0$comclevertapandroidsdkinappInAppController(deviceInfo, evaluationManager, coreMetaData);
            }
        };
    }

    /* renamed from: lambda$new$0$com-clevertap-android-sdk-inapp-InAppController, reason: not valid java name */
    /* synthetic */ Unit m4794lambda$new$0$comclevertapandroidsdkinappInAppController(DeviceInfo deviceInfo, EvaluationManager evaluationManager, CoreMetaData coreMetaData) {
        JSONArray jSONArrayEvaluateOnAppLaunchedClientSide = evaluationManager.evaluateOnAppLaunchedClientSide(JsonUtil.mapFromJson(deviceInfo.getAppLaunchedFields()), coreMetaData.getLocationFromUser());
        if (jSONArrayEvaluateOnAppLaunchedClientSide.length() <= 0) {
            return null;
        }
        addInAppNotificationsToQueue(jSONArrayEvaluateOnAppLaunchedClientSide);
        return null;
    }

    public void checkPendingInAppNotifications(Activity activity) {
        if (canShowInAppOnActivity()) {
            if (this.mainLooperHandler.getPendingRunnable() != null) {
                this.logger.verbose(this.config.getAccountId(), "Found a pending inapp runnable. Scheduling it");
                MainLooperHandler mainLooperHandler = this.mainLooperHandler;
                mainLooperHandler.postDelayed(mainLooperHandler.getPendingRunnable(), 200L);
                this.mainLooperHandler.setPendingRunnable(null);
                return;
            }
            showNotificationIfAvailable();
            return;
        }
        Logger.d("In-app notifications will not be shown for this activity (" + (activity != null ? activity.getLocalClassName() : "") + ")");
    }

    public void promptPushPrimer(JSONObject jSONObject) {
        if (ContextCompat.checkSelfPermission(this.context, PushPermissionManager.ANDROID_PERMISSION_STRING) == -1) {
            boolean zIsFirstTimeRequest = CTPreferenceCache.getInstance(this.context, this.config).isFirstTimeRequest();
            Activity currentActivity = CoreMetaData.getCurrentActivity();
            if (currentActivity == null) {
                Logger.d("CurrentActivity reference is null. SDK can't process the promptPushPrimer(jsonObject) method! Ensure the following things:\n1. Calling ActivityLifecycleCallback.register(this) in your custom application class before super.onCreate().\n   Alternatively, register CleverTap SDK's Application class in the manifest using com.clevertap.android.sdk.Application.\n2. Ensure that the promptPushPrimer() API is called from the onResume() lifecycle method, not onCreate().");
                return;
            }
            boolean zShouldShowRequestPermissionRationale = ActivityCompat.shouldShowRequestPermissionRationale(currentActivity, PushPermissionManager.ANDROID_PERMISSION_STRING);
            if (!zIsFirstTimeRequest && zShouldShowRequestPermissionRationale) {
                if (!jSONObject.optBoolean(CTLocalInApp.FALLBACK_TO_NOTIFICATION_SETTINGS, false)) {
                    Logger.v("Notification permission is denied. Please grant notification permission access in your app's settings to send notifications");
                    notifyPushPermissionResult(false);
                    return;
                } else {
                    showSoftOrHardPrompt(jSONObject);
                    return;
                }
            }
            showSoftOrHardPrompt(jSONObject);
            return;
        }
        notifyPushPermissionResult(true);
    }

    public void promptPermission(boolean z) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(CTLocalInApp.FALLBACK_TO_NOTIFICATION_SETTINGS, z);
            jSONObject.put(IS_HARD_PERMISSION_REQUEST, true);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        promptPushPrimer(jSONObject);
    }

    private void showSoftOrHardPrompt(JSONObject jSONObject) {
        if (jSONObject.optBoolean(IS_HARD_PERMISSION_REQUEST, false)) {
            startPrompt((Activity) Objects.requireNonNull(CoreMetaData.getCurrentActivity()), this.config, jSONObject.optBoolean(CTLocalInApp.FALLBACK_TO_NOTIFICATION_SETTINGS, false));
        } else {
            prepareNotificationForDisplay(jSONObject);
        }
    }

    public static void startPrompt(Activity activity, CleverTapInstanceConfig cleverTapInstanceConfig, boolean z) {
        if (activity.getClass().equals(InAppNotificationActivity.class)) {
            return;
        }
        Intent intent = new Intent(activity, (Class<?>) InAppNotificationActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("config", cleverTapInstanceConfig);
        intent.putExtra("configBundle", bundle);
        intent.putExtra(Constants.INAPP_KEY, currentlyDisplayingInApp);
        intent.putExtra(DISPLAY_HARD_PERMISSION_BUNDLE_KEY, true);
        intent.putExtra(SHOW_FALLBACK_SETTINGS_BUNDLE_KEY, z);
        activity.startActivity(intent);
    }

    public boolean isPushPermissionGranted() {
        return ContextCompat.checkSelfPermission(this.context, PushPermissionManager.ANDROID_PERMISSION_STRING) == 0;
    }

    public void discardInApps() {
        this.inAppState = InAppState.DISCARDED;
        this.logger.verbose(this.config.getAccountId(), "InAppState is DISCARDED");
    }

    @Override // com.clevertap.android.sdk.inapp.InAppListener
    public Bundle inAppNotificationActionTriggered(CTInAppNotification cTInAppNotification, CTInAppAction cTInAppAction, String str, Bundle bundle, Context context) throws JSONException {
        Bundle bundle2;
        if (bundle != null) {
            bundle2 = new Bundle(bundle);
        } else {
            bundle2 = new Bundle();
        }
        bundle2.putString(Constants.NOTIFICATION_ID_TAG, cTInAppNotification.getCampaignId());
        bundle2.putString(Constants.KEY_C2A, str);
        this.analyticsManager.pushInAppNotificationStateEvent(true, cTInAppNotification, bundle2);
        InAppActionType type = cTInAppAction.getType();
        if (type == null) {
            this.logger.debug("Triggered in-app action without type");
            return bundle2;
        }
        int i = AnonymousClass9.$SwitchMap$com$clevertap$android$sdk$inapp$InAppActionType[type.ordinal()];
        if (i == 1) {
            triggerCustomTemplateAction(cTInAppNotification, cTInAppAction.getCustomTemplateInAppData());
        } else if (i != 2) {
            if (i == 3) {
                String actionUrl = cTInAppAction.getActionUrl();
                if (actionUrl != null) {
                    openUrl(actionUrl, context);
                } else {
                    this.logger.debug("Cannot trigger open url action without url value");
                }
            } else if (i == 4 && cTInAppAction.getKeyValues() != null && !cTInAppAction.getKeyValues().isEmpty() && this.callbackManager.getInAppNotificationButtonListener() != null) {
                this.callbackManager.getInAppNotificationButtonListener().onInAppButtonClick(cTInAppAction.getKeyValues());
            }
        } else if (CTInAppType.CTInAppTypeCustomCodeTemplate == cTInAppNotification.getInAppType()) {
            this.templatesManager.closeTemplate(cTInAppNotification);
        }
        return bundle2;
    }

    @Override // com.clevertap.android.sdk.inapp.InAppListener
    public Bundle inAppNotificationDidClick(CTInAppNotification cTInAppNotification, CTInAppNotificationButton cTInAppNotificationButton, Context context) {
        if (cTInAppNotificationButton.getAction() == null) {
            return null;
        }
        return inAppNotificationActionTriggered(cTInAppNotification, cTInAppNotificationButton.getAction(), cTInAppNotificationButton.getText(), null, context);
    }

    @Override // com.clevertap.android.sdk.inapp.InAppListener
    public void inAppNotificationDidDismiss(final CTInAppNotification cTInAppNotification, Bundle bundle) {
        HashMap<String, Object> map;
        cTInAppNotification.didDismiss(this.resourceProvider);
        if (this.controllerManager.getInAppFCManager() != null) {
            this.controllerManager.getInAppFCManager().didDismiss(cTInAppNotification);
            this.logger.verbose(this.config.getAccountId(), "InApp Dismissed: " + cTInAppNotification.getCampaignId() + "  " + (cTInAppNotification.getCustomTemplateData() != null ? cTInAppNotification.getCustomTemplateData().getTemplateName() : ""));
        } else {
            this.logger.verbose(this.config.getAccountId(), "Not calling InApp Dismissed: " + cTInAppNotification.getCampaignId() + " because InAppFCManager is null");
        }
        try {
            InAppNotificationListener inAppNotificationListener = this.callbackManager.getInAppNotificationListener();
            if (inAppNotificationListener != null) {
                if (cTInAppNotification.getCustomExtras() != null) {
                    map = Utils.convertJSONObjectToHashMap(cTInAppNotification.getCustomExtras());
                } else {
                    map = new HashMap<>();
                }
                Logger.v("Calling the in-app listener on behalf of " + this.coreMetaData.getSource());
                if (bundle != null) {
                    inAppNotificationListener.onDismissed(map, Utils.convertBundleObjectToHashMap(bundle));
                } else {
                    inAppNotificationListener.onDismissed(map, null);
                }
            }
        } catch (Throwable th) {
            this.logger.verbose(this.config.getAccountId(), "Failed to call the in-app notification listener", th);
        }
        CTExecutorFactory.executors(this.config).postAsyncSafelyTask(Constants.TAG_FEATURE_IN_APPS).execute("InappController#inAppNotificationDidDismiss", new Callable<Void>() { // from class: com.clevertap.android.sdk.inapp.InAppController.1
            @Override // java.util.concurrent.Callable
            public Void call() {
                InAppController.inAppDidDismiss(InAppController.this.context, InAppController.this.config, cTInAppNotification, InAppController.this);
                InAppController.this._showNotificationIfAvailable();
                return null;
            }
        });
    }

    @Override // com.clevertap.android.sdk.inapp.InAppListener
    public void inAppNotificationDidShow(CTInAppNotification cTInAppNotification, Bundle bundle) {
        this.controllerManager.getInAppFCManager().didShow(this.context, cTInAppNotification);
        this.analyticsManager.pushInAppNotificationStateEvent(false, cTInAppNotification, bundle);
        try {
            InAppNotificationListener inAppNotificationListener = this.callbackManager.getInAppNotificationListener();
            if (inAppNotificationListener != null) {
                inAppNotificationListener.onShow(cTInAppNotification);
            }
        } catch (Throwable th) {
            Logger.v(this.config.getAccountId(), "Failed to call the in-app notification listener", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notificationReady(final CTInAppNotification cTInAppNotification, final CustomTemplate customTemplate) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            this.mainLooperHandler.post(new Runnable() { // from class: com.clevertap.android.sdk.inapp.InAppController.2
                @Override // java.lang.Runnable
                public void run() {
                    InAppController.this.notificationReady(cTInAppNotification, customTemplate);
                }
            });
            return;
        }
        if (cTInAppNotification.getError() != null) {
            this.logger.debug(this.config.getAccountId(), "Unable to process inapp notification " + cTInAppNotification.getError());
            return;
        }
        this.logger.debug(this.config.getAccountId(), "Notification ready: " + cTInAppNotification.getJsonDescription());
        if (customTemplate != null && !customTemplate.getIsVisual()) {
            presentTemplate(cTInAppNotification);
        } else {
            displayNotification(cTInAppNotification);
        }
    }

    @Override // com.clevertap.android.sdk.InAppNotificationActivity.PushPermissionResultCallback
    public void onPushPermissionAccept() {
        notifyPushPermissionResult(true);
    }

    @Override // com.clevertap.android.sdk.InAppNotificationActivity.PushPermissionResultCallback
    public void onPushPermissionDeny() {
        notifyPushPermissionResult(false);
    }

    public void notifyPushPermissionResult(boolean z) {
        for (PushPermissionResponseListener pushPermissionResponseListener : this.callbackManager.getPushPermissionResponseListenerList()) {
            if (pushPermissionResponseListener != null) {
                pushPermissionResponseListener.onPushPermissionResponse(z);
            }
        }
    }

    public void resumeInApps() {
        this.inAppState = InAppState.RESUMED;
        this.logger.verbose(this.config.getAccountId(), "InAppState is RESUMED");
        this.logger.verbose(this.config.getAccountId(), "Resuming InApps by calling showInAppNotificationIfAny()");
        showInAppNotificationIfAny();
    }

    public void addInAppNotificationsToQueue(JSONArray jSONArray) {
        try {
            this.inAppQueue.enqueueAll(filterNonRegisteredCustomTemplates(jSONArray));
            showNotificationIfAvailable();
        } catch (Exception e) {
            this.logger.debug(this.config.getAccountId(), "InAppController: : InApp notification handling error: " + e.getMessage());
        }
    }

    public void showNotificationIfAvailable() {
        if (this.config.isAnalyticsOnly()) {
            return;
        }
        CTExecutorFactory.executors(this.config).postAsyncSafelyTask(Constants.TAG_FEATURE_IN_APPS).execute("InappController#showNotificationIfAvailable", new Callable<Void>() { // from class: com.clevertap.android.sdk.inapp.InAppController.3
            @Override // java.util.concurrent.Callable
            public Void call() {
                InAppController.this._showNotificationIfAvailable();
                return null;
            }
        });
    }

    public void suspendInApps() {
        this.inAppState = InAppState.SUSPENDED;
        this.logger.verbose(this.config.getAccountId(), "InAppState is SUSPENDED");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _showNotificationIfAvailable() {
        try {
            if (!canShowInAppOnActivity()) {
                Logger.v("Not showing notification on blacklisted activity");
                return;
            }
            if (this.inAppState == InAppState.SUSPENDED) {
                this.logger.debug(this.config.getAccountId(), "InApp Notifications are set to be suspended, not showing the InApp Notification");
                return;
            }
            checkPendingNotifications(this.context, this.config, this);
            JSONObject jSONObjectDequeue = this.inAppQueue.dequeue();
            if (jSONObjectDequeue == null) {
                return;
            }
            if (this.inAppState != InAppState.DISCARDED) {
                prepareNotificationForDisplay(jSONObjectDequeue);
            } else {
                this.logger.debug(this.config.getAccountId(), "InApp Notifications are set to be discarded, dropping the InApp Notification");
            }
        } catch (Throwable th) {
            this.logger.verbose(this.config.getAccountId(), "InApp: Couldn't parse JSON array string from prefs", th);
        }
    }

    private void addInAppNotificationInFrontOfQueue(JSONObject jSONObject) {
        if (isNonRegisteredCustomTemplate(jSONObject)) {
            return;
        }
        this.inAppQueue.insertInFront(jSONObject);
        showNotificationIfAvailable();
    }

    private boolean canShowInAppOnActivity() {
        updateBlacklistedActivitySet();
        Iterator<String> it = this.inappActivityExclude.iterator();
        while (it.hasNext()) {
            String next = it.next();
            String currentActivityName = CoreMetaData.getCurrentActivityName();
            if (currentActivityName != null && currentActivityName.contains(next)) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void displayNotification(final CTInAppNotification cTInAppNotification) {
        HashMap<String, Object> map;
        if (Looper.myLooper() != Looper.getMainLooper()) {
            this.mainLooperHandler.post(new Runnable() { // from class: com.clevertap.android.sdk.inapp.InAppController.4
                @Override // java.lang.Runnable
                public void run() {
                    InAppController.this.displayNotification(cTInAppNotification);
                }
            });
            return;
        }
        if (this.controllerManager.getInAppFCManager() != null) {
            if (!this.controllerManager.getInAppFCManager().canShow(cTInAppNotification, new Function2() { // from class: com.clevertap.android.sdk.inapp.InAppController$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return this.f$0.m4792x6d3a94f2((JSONObject) obj, (String) obj2);
                }
            })) {
                this.logger.verbose(this.config.getAccountId(), "InApp has been rejected by FC, not showing " + cTInAppNotification.getCampaignId());
                showInAppNotificationIfAny();
                return;
            }
            InAppNotificationListener inAppNotificationListener = this.callbackManager.getInAppNotificationListener();
            if (inAppNotificationListener != null) {
                if (cTInAppNotification.getCustomExtras() != null) {
                    map = Utils.convertJSONObjectToHashMap(cTInAppNotification.getCustomExtras());
                } else {
                    map = new HashMap<>();
                }
                if (!inAppNotificationListener.beforeShow(map)) {
                    this.logger.verbose(this.config.getAccountId(), "Application has decided to not show this in-app notification: " + cTInAppNotification.getCampaignId());
                    showInAppNotificationIfAny();
                    return;
                }
            }
            showInApp(this.context, cTInAppNotification, this.config, this);
            incrementLocalInAppCountInPersistentStore(this.context, cTInAppNotification);
            return;
        }
        this.logger.verbose(this.config.getAccountId(), "getCoreState().getInAppFCManager() is NULL, not showing " + cTInAppNotification.getCampaignId());
    }

    /* renamed from: lambda$displayNotification$1$com-clevertap-android-sdk-inapp-InAppController, reason: not valid java name */
    /* synthetic */ Boolean m4792x6d3a94f2(JSONObject jSONObject, String str) {
        return Boolean.valueOf(!this.evaluationManager.matchWhenLimitsBeforeDisplay(InAppResponseAdapter.getListOfWhenLimits(jSONObject), str));
    }

    private void prepareNotificationForDisplay(final JSONObject jSONObject) {
        this.logger.debug(this.config.getAccountId(), "Preparing In-App for display: " + jSONObject.toString());
        CTExecutorFactory.executors(this.config).postAsyncSafelyTask(Constants.TAG_FEATURE_IN_APPS).execute("InappController#prepareNotificationForDisplay", new Callable<Void>() { // from class: com.clevertap.android.sdk.inapp.InAppController.5
            @Override // java.util.concurrent.Callable
            public Void call() {
                InAppController inAppController = InAppController.this;
                inAppController.new NotificationPrepareRunnable(inAppController, jSONObject).run();
                return null;
            }
        });
    }

    private void showInAppNotificationIfAny() {
        if (this.config.isAnalyticsOnly()) {
            return;
        }
        CTExecutorFactory.executors(this.config).postAsyncSafelyTask(Constants.TAG_FEATURE_IN_APPS).execute("InAppController#showInAppNotificationIfAny", new Callable<Void>() { // from class: com.clevertap.android.sdk.inapp.InAppController.6
            @Override // java.util.concurrent.Callable
            public Void call() {
                InAppController.this._showNotificationIfAvailable();
                return null;
            }
        });
    }

    private void updateBlacklistedActivitySet() {
        if (this.inappActivityExclude == null) {
            this.inappActivityExclude = new HashSet<>();
            try {
                String excludedActivities = ManifestInfo.getInstance(this.context).getExcludedActivities();
                if (excludedActivities != null) {
                    for (String str : excludedActivities.split(Constants.SEPARATOR_COMMA)) {
                        this.inappActivityExclude.add(str.trim());
                    }
                }
            } catch (Throwable unused) {
            }
            this.logger.debug(this.config.getAccountId(), "In-app notifications will not be shown on " + Arrays.toString(this.inappActivityExclude.toArray()));
        }
    }

    private static void checkPendingNotifications(final Context context, final CleverTapInstanceConfig cleverTapInstanceConfig, final InAppController inAppController) {
        Logger.v(cleverTapInstanceConfig.getAccountId(), "checking Pending Notifications");
        List<CTInAppNotification> list = pendingNotifications;
        if (list == null || list.isEmpty()) {
            return;
        }
        try {
            final CTInAppNotification cTInAppNotification = list.get(0);
            list.remove(0);
            new MainLooperHandler().post(new Runnable() { // from class: com.clevertap.android.sdk.inapp.InAppController.7
                @Override // java.lang.Runnable
                public void run() {
                    InAppController.showInApp(context, cTInAppNotification, cleverTapInstanceConfig, inAppController);
                }
            });
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void inAppDidDismiss(Context context, CleverTapInstanceConfig cleverTapInstanceConfig, CTInAppNotification cTInAppNotification, InAppController inAppController) {
        Logger.v(cleverTapInstanceConfig.getAccountId(), "Running inAppDidDismiss");
        CTInAppNotification cTInAppNotification2 = currentlyDisplayingInApp;
        if (cTInAppNotification2 == null || !cTInAppNotification2.getCampaignId().equals(cTInAppNotification.getCampaignId())) {
            return;
        }
        currentlyDisplayingInApp = null;
        checkPendingNotifications(context, cleverTapInstanceConfig, inAppController);
    }

    private void incrementLocalInAppCountInPersistentStore(final Context context, CTInAppNotification cTInAppNotification) {
        if (cTInAppNotification.isLocalInApp()) {
            this.deviceInfo.incrementLocalInAppCount();
            CTExecutorFactory.executors(this.config).ioTask().execute("InAppController#incrementLocalInAppCountInPersistentStore", new Callable<Void>() { // from class: com.clevertap.android.sdk.inapp.InAppController.8
                @Override // java.util.concurrent.Callable
                public Void call() {
                    StorageHelper.putIntImmediate(context, InAppController.LOCAL_INAPP_COUNT, InAppController.this.deviceInfo.getLocalInAppCount());
                    return null;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void showInApp(Context context, CTInAppNotification cTInAppNotification, CleverTapInstanceConfig cleverTapInstanceConfig, InAppController inAppController) {
        Fragment cTInAppHtmlFooterFragment;
        Activity currentActivity;
        Logger.v(cleverTapInstanceConfig.getAccountId(), "Attempting to show next In-App");
        if (!CoreMetaData.isAppForeground()) {
            pendingNotifications.add(cTInAppNotification);
            Logger.v(cleverTapInstanceConfig.getAccountId(), "Not in foreground, queueing this In App");
            return;
        }
        if (currentlyDisplayingInApp != null) {
            pendingNotifications.add(cTInAppNotification);
            Logger.v(cleverTapInstanceConfig.getAccountId(), "In App already displaying, queueing this In App");
            return;
        }
        if (!inAppController.canShowInAppOnActivity()) {
            pendingNotifications.add(cTInAppNotification);
            Logger.v(cleverTapInstanceConfig.getAccountId(), "Not showing In App on blacklisted activity, queuing this In App");
            return;
        }
        if (System.currentTimeMillis() / 1000 > cTInAppNotification.getTimeToLive()) {
            Logger.d("InApp has elapsed its time to live, not showing the InApp");
            return;
        }
        String type = cTInAppNotification.getType();
        if (type != null && type.equals(Constants.KEY_CUSTOM_HTML) && !NetworkManager.isNetworkOnline(context)) {
            Logger.d(cleverTapInstanceConfig.getAccountId(), "Not showing HTML InApp due to no internet. An active internet connection is required to display the HTML InApp");
            inAppController.showInAppNotificationIfAny();
            return;
        }
        currentlyDisplayingInApp = cTInAppNotification;
        CTInAppType inAppType = cTInAppNotification.getInAppType();
        switch (AnonymousClass9.$SwitchMap$com$clevertap$android$sdk$inapp$CTInAppType[inAppType.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
                Intent intent = new Intent(context, (Class<?>) InAppNotificationActivity.class);
                intent.putExtra(Constants.INAPP_KEY, cTInAppNotification);
                Bundle bundle = new Bundle();
                bundle.putParcelable("config", cleverTapInstanceConfig);
                intent.putExtra("configBundle", bundle);
                try {
                    currentActivity = CoreMetaData.getCurrentActivity();
                } catch (Throwable th) {
                    Logger.v("Please verify the integration of your app. It is not setup to support in-app notifications yet.", th);
                }
                if (currentActivity == null) {
                    throw new IllegalStateException("Current activity reference not found");
                }
                cleverTapInstanceConfig.getLogger().verbose(cleverTapInstanceConfig.getAccountId(), "calling InAppActivity for notification: " + cTInAppNotification.getJsonDescription());
                currentActivity.startActivity(intent);
                Logger.d("Displaying In-App: " + cTInAppNotification.getJsonDescription());
                cTInAppHtmlFooterFragment = null;
                break;
            case 11:
                cTInAppHtmlFooterFragment = new CTInAppHtmlFooterFragment();
                break;
            case 12:
                cTInAppHtmlFooterFragment = new CTInAppHtmlHeaderFragment();
                break;
            case 13:
                cTInAppHtmlFooterFragment = new CTInAppNativeFooterFragment();
                break;
            case 14:
                cTInAppHtmlFooterFragment = new CTInAppNativeHeaderFragment();
                break;
            case 15:
                inAppController.presentTemplate(cTInAppNotification);
                return;
            default:
                Logger.d(cleverTapInstanceConfig.getAccountId(), "Unknown InApp Type found: " + inAppType);
                currentlyDisplayingInApp = null;
                return;
        }
        if (cTInAppHtmlFooterFragment != null) {
            Logger.d("Displaying In-App: " + cTInAppNotification.getJsonDescription());
            try {
                FragmentTransaction fragmentTransactionBeginTransaction = ((FragmentActivity) CoreMetaData.getCurrentActivity()).getSupportFragmentManager().beginTransaction();
                Bundle bundle2 = new Bundle();
                bundle2.putParcelable(Constants.INAPP_KEY, cTInAppNotification);
                bundle2.putParcelable("config", cleverTapInstanceConfig);
                cTInAppHtmlFooterFragment.setArguments(bundle2);
                fragmentTransactionBeginTransaction.setCustomAnimations(R.animator.fade_in, R.animator.fade_out);
                fragmentTransactionBeginTransaction.add(R.id.content, cTInAppHtmlFooterFragment, cTInAppNotification.getType());
                Logger.v(cleverTapInstanceConfig.getAccountId(), "calling InAppFragment " + cTInAppNotification.getCampaignId());
                fragmentTransactionBeginTransaction.commitNow();
            } catch (ClassCastException e) {
                Logger.v(cleverTapInstanceConfig.getAccountId(), "Fragment not able to render, please ensure your Activity is an instance of AppCompatActivity" + e.getMessage());
                currentlyDisplayingInApp = null;
            } catch (Throwable th2) {
                Logger.v(cleverTapInstanceConfig.getAccountId(), "Fragment not able to render", th2);
                currentlyDisplayingInApp = null;
            }
        }
    }

    /* renamed from: com.clevertap.android.sdk.inapp.InAppController$9, reason: invalid class name */
    static /* synthetic */ class AnonymousClass9 {
        static final /* synthetic */ int[] $SwitchMap$com$clevertap$android$sdk$inapp$CTInAppType;
        static final /* synthetic */ int[] $SwitchMap$com$clevertap$android$sdk$inapp$InAppActionType;

        static {
            int[] iArr = new int[CTInAppType.values().length];
            $SwitchMap$com$clevertap$android$sdk$inapp$CTInAppType = iArr;
            try {
                iArr[CTInAppType.CTInAppTypeCoverHTML.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$clevertap$android$sdk$inapp$CTInAppType[CTInAppType.CTInAppTypeInterstitialHTML.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$clevertap$android$sdk$inapp$CTInAppType[CTInAppType.CTInAppTypeHalfInterstitialHTML.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$clevertap$android$sdk$inapp$CTInAppType[CTInAppType.CTInAppTypeCover.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$clevertap$android$sdk$inapp$CTInAppType[CTInAppType.CTInAppTypeHalfInterstitial.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$clevertap$android$sdk$inapp$CTInAppType[CTInAppType.CTInAppTypeInterstitial.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$clevertap$android$sdk$inapp$CTInAppType[CTInAppType.CTInAppTypeAlert.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$clevertap$android$sdk$inapp$CTInAppType[CTInAppType.CTInAppTypeInterstitialImageOnly.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$clevertap$android$sdk$inapp$CTInAppType[CTInAppType.CTInAppTypeHalfInterstitialImageOnly.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$clevertap$android$sdk$inapp$CTInAppType[CTInAppType.CTInAppTypeCoverImageOnly.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$clevertap$android$sdk$inapp$CTInAppType[CTInAppType.CTInAppTypeFooterHTML.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$clevertap$android$sdk$inapp$CTInAppType[CTInAppType.CTInAppTypeHeaderHTML.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$clevertap$android$sdk$inapp$CTInAppType[CTInAppType.CTInAppTypeFooter.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$clevertap$android$sdk$inapp$CTInAppType[CTInAppType.CTInAppTypeHeader.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$clevertap$android$sdk$inapp$CTInAppType[CTInAppType.CTInAppTypeCustomCodeTemplate.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            int[] iArr2 = new int[InAppActionType.values().length];
            $SwitchMap$com$clevertap$android$sdk$inapp$InAppActionType = iArr2;
            try {
                iArr2[InAppActionType.CUSTOM_CODE.ordinal()] = 1;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$clevertap$android$sdk$inapp$InAppActionType[InAppActionType.CLOSE.ordinal()] = 2;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$clevertap$android$sdk$inapp$InAppActionType[InAppActionType.OPEN_URL.ordinal()] = 3;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$com$clevertap$android$sdk$inapp$InAppActionType[InAppActionType.KEY_VALUES.ordinal()] = 4;
            } catch (NoSuchFieldError unused19) {
            }
        }
    }

    public void onQueueEvent(String str, Map<String, Object> map, Location location) {
        Map<String, ? extends Object> mapMapFromJson = JsonUtil.mapFromJson(this.deviceInfo.getAppLaunchedFields());
        mapMapFromJson.putAll(map);
        JSONArray jSONArrayEvaluateOnEvent = this.evaluationManager.evaluateOnEvent(str, mapMapFromJson, location);
        if (jSONArrayEvaluateOnEvent.length() > 0) {
            addInAppNotificationsToQueue(jSONArrayEvaluateOnEvent);
        }
    }

    public void onQueueChargedEvent(Map<String, Object> map, List<Map<String, Object>> list, Location location) {
        Map<String, ? extends Object> mapMapFromJson = JsonUtil.mapFromJson(this.deviceInfo.getAppLaunchedFields());
        mapMapFromJson.putAll(map);
        JSONArray jSONArrayEvaluateOnChargedEvent = this.evaluationManager.evaluateOnChargedEvent(mapMapFromJson, list, location);
        if (jSONArrayEvaluateOnChargedEvent.length() > 0) {
            addInAppNotificationsToQueue(jSONArrayEvaluateOnChargedEvent);
        }
    }

    public void onQueueProfileEvent(Map<String, Map<String, Object>> map, Location location) {
        JSONArray jSONArrayEvaluateOnUserAttributeChange = this.evaluationManager.evaluateOnUserAttributeChange(map, location, JsonUtil.mapFromJson(this.deviceInfo.getAppLaunchedFields()));
        if (jSONArrayEvaluateOnUserAttributeChange.length() > 0) {
            addInAppNotificationsToQueue(jSONArrayEvaluateOnUserAttributeChange);
        }
    }

    public void onAppLaunchServerSideInAppsResponse(JSONArray jSONArray, Location location) throws JSONException {
        Map<String, ? extends Object> mapMapFromJson = JsonUtil.mapFromJson(this.deviceInfo.getAppLaunchedFields());
        JSONArray jSONArrayEvaluateOnAppLaunchedServerSide = this.evaluationManager.evaluateOnAppLaunchedServerSide(Utils.toJSONObjectList(jSONArray), mapMapFromJson, location);
        if (jSONArrayEvaluateOnAppLaunchedServerSide.length() > 0) {
            addInAppNotificationsToQueue(jSONArrayEvaluateOnAppLaunchedServerSide);
        }
    }

    private void presentTemplate(CTInAppNotification cTInAppNotification) {
        this.templatesManager.presentTemplate(cTInAppNotification, this, this.resourceProvider);
    }

    private JSONArray filterNonRegisteredCustomTemplates(JSONArray jSONArray) {
        return JsonUtilsKt.filterObjects(jSONArray, new Function1() { // from class: com.clevertap.android.sdk.inapp.InAppController$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f$0.m4793x9d15431((JSONObject) obj);
            }
        });
    }

    /* renamed from: lambda$filterNonRegisteredCustomTemplates$2$com-clevertap-android-sdk-inapp-InAppController, reason: not valid java name */
    /* synthetic */ Boolean m4793x9d15431(JSONObject jSONObject) {
        return Boolean.valueOf(!isNonRegisteredCustomTemplate(jSONObject));
    }

    private boolean isNonRegisteredCustomTemplate(JSONObject jSONObject) {
        CustomTemplateInAppData customTemplateInAppDataCreateFromJson = CustomTemplateInAppData.createFromJson(jSONObject);
        boolean z = (customTemplateInAppDataCreateFromJson == null || customTemplateInAppDataCreateFromJson.getTemplateName() == null || this.templatesManager.isTemplateRegistered(customTemplateInAppDataCreateFromJson.getTemplateName())) ? false : true;
        if (z) {
            this.logger.info("CustomTemplates", "Template with name \"" + customTemplateInAppDataCreateFromJson.getTemplateName() + "\" is not registered and cannot be presented");
        }
        return z;
    }

    private void triggerCustomTemplateAction(CTInAppNotification cTInAppNotification, CustomTemplateInAppData customTemplateInAppData) throws JSONException {
        if (customTemplateInAppData != null && customTemplateInAppData.getTemplateName() != null) {
            CustomTemplate template = this.templatesManager.getTemplate(customTemplateInAppData.getTemplateName());
            if (template != null) {
                CustomTemplateInAppData customTemplateInAppDataCopy = customTemplateInAppData.copy();
                customTemplateInAppDataCopy.setAction(true);
                CTInAppNotification cTInAppNotificationCreateNotificationForAction = cTInAppNotification.createNotificationForAction(customTemplateInAppDataCopy);
                if (cTInAppNotificationCreateNotificationForAction == null) {
                    this.logger.debug("Failed to present custom template with name: " + customTemplateInAppData.getTemplateName());
                    return;
                } else if (template.getIsVisual()) {
                    addInAppNotificationInFrontOfQueue(cTInAppNotificationCreateNotificationForAction.getJsonDescription());
                    return;
                } else {
                    prepareNotificationForDisplay(cTInAppNotificationCreateNotificationForAction.getJsonDescription());
                    return;
                }
            }
            this.logger.debug("Cannot present non-registered template with name: " + customTemplateInAppData.getTemplateName());
            return;
        }
        this.logger.debug("Cannot present template without name.");
    }

    private void openUrl(String str, Context context) {
        try {
            Uri uri = Uri.parse(str.replace("\n", "").replace(StringUtils.CR, ""));
            Set<String> queryParameterNames = uri.getQueryParameterNames();
            Bundle bundle = new Bundle();
            if (queryParameterNames != null && !queryParameterNames.isEmpty()) {
                for (String str2 : queryParameterNames) {
                    bundle.putString(str2, uri.getQueryParameter(str2));
                }
            }
            Intent intent = new Intent("android.intent.action.VIEW", uri);
            if (!bundle.isEmpty()) {
                intent.putExtras(bundle);
            }
            if (context == null) {
                context = this.context;
                intent.setFlags(268435456);
            }
            Utils.setPackageNameFromResolveInfoList(context, intent);
            context.startActivity(intent);
        } catch (Exception unused) {
            if (str.startsWith(Constants.WZRK_URL_SCHEMA)) {
                return;
            }
            this.logger.debug("No activity found to open url: " + str);
        }
    }
}
