package io.invertase.firebase.dynamiclinks;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.dynamiclinks.DynamicLink;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.PendingDynamicLinkData;
import com.google.firebase.dynamiclinks.ShortDynamicLink;
import io.invertase.firebase.common.ReactNativeFirebaseEvent;
import io.invertase.firebase.common.ReactNativeFirebaseEventEmitter;
import io.invertase.firebase.common.ReactNativeFirebaseModule;
import java.util.Objects;
import java.util.concurrent.Callable;
import javax.annotation.Nullable;

/* loaded from: classes6.dex */
public class ReactNativeFirebaseDynamicLinksModule extends ReactNativeFirebaseModule implements ActivityEventListener, LifecycleEventListener {
    private static final String SHORT_LINK_TYPE_SHORT = "SHORT";
    private static final String SHORT_LINK_TYPE_UNGUESSABLE = "UNGUESSABLE";
    private static final String TAG = "DynamicLinks";
    private boolean gotInitialLink;
    private boolean hostResumed;
    private int initialLinkMinimumVersion;
    private String initialLinkUrl;
    private Promise initialPromise;
    private boolean launchedFromHistory;

    @Override // com.facebook.react.bridge.ActivityEventListener
    public void onActivityResult(Activity activity, int i, int i2, Intent intent) {
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
        this.initialLinkUrl = null;
        this.gotInitialLink = false;
        this.initialLinkMinimumVersion = 0;
        this.launchedFromHistory = false;
        this.initialPromise = null;
        this.hostResumed = false;
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
        this.hostResumed = false;
    }

    ReactNativeFirebaseDynamicLinksModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext, TAG);
        this.initialLinkUrl = null;
        this.initialLinkMinimumVersion = 0;
        this.gotInitialLink = false;
        this.hostResumed = false;
        this.launchedFromHistory = false;
        this.initialPromise = null;
        getReactApplicationContext().addActivityEventListener(this);
        getReactApplicationContext().addLifecycleEventListener(this);
    }

    @Override // io.invertase.firebase.common.ReactNativeFirebaseModule, com.facebook.react.bridge.NativeModule
    public void onCatalystInstanceDestroy() {
        getReactApplicationContext().removeActivityEventListener(this);
        getReactApplicationContext().addLifecycleEventListener(this);
        super.onCatalystInstanceDestroy();
    }

    @ReactMethod
    public void buildLink(final ReadableMap readableMap, final Promise promise) {
        Tasks.call(getExecutor(), new Callable() { // from class: io.invertase.firebase.dynamiclinks.ReactNativeFirebaseDynamicLinksModule$$ExternalSyntheticLambda2
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.lambda$buildLink$0(readableMap);
            }
        }).addOnCompleteListener(getExecutor(), new OnCompleteListener() { // from class: io.invertase.firebase.dynamiclinks.ReactNativeFirebaseDynamicLinksModule$$ExternalSyntheticLambda3
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                ReactNativeFirebaseDynamicLinksModule.lambda$buildLink$1(promise, task);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ String lambda$buildLink$0(ReadableMap readableMap) throws Exception {
        return createDynamicLinkBuilder(readableMap).buildDynamicLink().getUri().toString();
    }

    static /* synthetic */ void lambda$buildLink$1(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve(task.getResult());
        } else {
            Log.e(TAG, "RNFB: Unknown error while building Dynamic Link ", task.getException());
            rejectPromiseWithCodeAndMessage(promise, "build-failed", task.getException().getMessage());
        }
    }

    @ReactMethod
    public void buildShortLink(final ReadableMap readableMap, final String str, final Promise promise) {
        Tasks.call(getExecutor(), new Callable() { // from class: io.invertase.firebase.dynamiclinks.ReactNativeFirebaseDynamicLinksModule$$ExternalSyntheticLambda4
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.lambda$buildShortLink$2(readableMap, str);
            }
        }).addOnCompleteListener(getExecutor(), new OnCompleteListener() { // from class: io.invertase.firebase.dynamiclinks.ReactNativeFirebaseDynamicLinksModule$$ExternalSyntheticLambda5
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                ReactNativeFirebaseDynamicLinksModule.lambda$buildShortLink$3(promise, task);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ ShortDynamicLink lambda$buildShortLink$2(ReadableMap readableMap, String str) throws Exception {
        DynamicLink.Builder builderCreateDynamicLinkBuilder = createDynamicLinkBuilder(readableMap);
        if (SHORT_LINK_TYPE_SHORT.equals(str)) {
            return (ShortDynamicLink) Tasks.await(builderCreateDynamicLinkBuilder.buildShortDynamicLink(2));
        }
        if (SHORT_LINK_TYPE_UNGUESSABLE.equals(str)) {
            return (ShortDynamicLink) Tasks.await(builderCreateDynamicLinkBuilder.buildShortDynamicLink(1));
        }
        return (ShortDynamicLink) Tasks.await(builderCreateDynamicLinkBuilder.buildShortDynamicLink());
    }

    static /* synthetic */ void lambda$buildShortLink$3(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve(((ShortDynamicLink) task.getResult()).getShortLink().toString());
        } else {
            Log.e(TAG, "RNFB: Unknown error while building Dynamic Link " + task.getException().getMessage());
            rejectPromiseWithCodeAndMessage(promise, "build-failed", task.getException().getMessage());
        }
    }

    @ReactMethod
    public void getInitialLink(final Promise promise) {
        if (this.gotInitialLink) {
            promise.resolve(null);
            return;
        }
        if (!this.hostResumed) {
            this.initialPromise = promise;
            return;
        }
        Activity currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            promise.resolve(null);
            return;
        }
        Intent intent = currentActivity.getIntent();
        if (intent == null) {
            promise.resolve(null);
        } else {
            this.launchedFromHistory = (intent == null || (intent.getFlags() & 1048576) == 0) ? false : true;
            FirebaseDynamicLinks.getInstance().getDynamicLink(intent).addOnCompleteListener(new OnCompleteListener() { // from class: io.invertase.firebase.dynamiclinks.ReactNativeFirebaseDynamicLinksModule$$ExternalSyntheticLambda1
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task) {
                    this.f$0.lambda$getInitialLink$4(promise, task);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$getInitialLink$4(Promise promise, Task task) {
        if (task.isSuccessful()) {
            this.gotInitialLink = true;
            PendingDynamicLinkData pendingDynamicLinkData = (PendingDynamicLinkData) task.getResult();
            WritableNativeMap writableNativeMap = new WritableNativeMap();
            if (pendingDynamicLinkData != null) {
                this.initialLinkUrl = pendingDynamicLinkData.getLink().toString();
                this.initialLinkMinimumVersion = pendingDynamicLinkData.getMinimumAppVersion();
                writableNativeMap = Arguments.makeNativeMap(pendingDynamicLinkData.getUtmParameters());
            }
            String str = this.initialLinkUrl;
            if (str != null && !this.launchedFromHistory) {
                promise.resolve(dynamicLinkToWritableMap(str, this.initialLinkMinimumVersion, writableNativeMap));
                return;
            } else {
                promise.resolve(null);
                return;
            }
        }
        rejectPromiseWithCodeAndMessage(promise, "initial-link-error", task.getException().getMessage());
    }

    @ReactMethod
    public void resolveLink(String str, final Promise promise) {
        try {
            FirebaseDynamicLinks.getInstance().getDynamicLink(Uri.parse(str)).addOnCompleteListener(new OnCompleteListener() { // from class: io.invertase.firebase.dynamiclinks.ReactNativeFirebaseDynamicLinksModule$$ExternalSyntheticLambda6
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task) {
                    this.f$0.lambda$resolveLink$5(promise, task);
                }
            });
        } catch (Exception unused) {
            rejectPromiseWithCodeAndMessage(promise, "resolve-link-error", "Unknown resolve failure");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$resolveLink$5(Promise promise, Task task) {
        if (task.isSuccessful()) {
            PendingDynamicLinkData pendingDynamicLinkData = (PendingDynamicLinkData) task.getResult();
            if (pendingDynamicLinkData != null && pendingDynamicLinkData.getLink() != null && pendingDynamicLinkData.getLink().toString() != null) {
                promise.resolve(dynamicLinkToWritableMap(pendingDynamicLinkData.getLink().toString(), pendingDynamicLinkData.getMinimumAppVersion(), Arguments.makeNativeMap(pendingDynamicLinkData.getUtmParameters())));
                return;
            } else {
                rejectPromiseWithCodeAndMessage(promise, "not-found", "Dynamic link not found");
                return;
            }
        }
        rejectPromiseWithCodeAndMessage(promise, "resolve-link-error", task.getException().getMessage());
    }

    private WritableMap dynamicLinkToWritableMap(String str, int i, WritableMap writableMap) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putString("url", str);
        if (i == 0) {
            writableMapCreateMap.putNull("minimumAppVersion");
        } else {
            writableMapCreateMap.putInt("minimumAppVersion", i);
        }
        writableMapCreateMap.putMap("utmParameters", writableMap);
        return writableMapCreateMap;
    }

    private DynamicLink.Builder createDynamicLinkBuilder(ReadableMap readableMap) {
        DynamicLink.Builder builderCreateDynamicLink = FirebaseDynamicLinks.getInstance().createDynamicLink();
        builderCreateDynamicLink.setLink(Uri.parse(readableMap.getString(DynamicLink.Builder.KEY_LINK)));
        builderCreateDynamicLink.setDomainUriPrefix((String) Objects.requireNonNull(readableMap.getString(DynamicLink.Builder.KEY_DOMAIN_URI_PREFIX)));
        if (readableMap.hasKey("ios")) {
            buildIosParameters(readableMap.getMap("ios"), builderCreateDynamicLink);
        }
        if (readableMap.hasKey("itunes")) {
            buildItunesParameters(readableMap.getMap("itunes"), builderCreateDynamicLink);
        }
        if (readableMap.hasKey(NotificationCompat.CATEGORY_SOCIAL)) {
            buildSocialParameters(readableMap.getMap(NotificationCompat.CATEGORY_SOCIAL), builderCreateDynamicLink);
        }
        if (readableMap.hasKey(Constants.KEY_ANDROID)) {
            buildAndroidParameters(readableMap.getMap(Constants.KEY_ANDROID), builderCreateDynamicLink);
        }
        if (readableMap.hasKey("analytics")) {
            buildAnalyticsParameters(readableMap.getMap("analytics"), builderCreateDynamicLink);
        }
        if (readableMap.hasKey(NotificationCompat.CATEGORY_NAVIGATION)) {
            buildNavigationParameters(readableMap.getMap(NotificationCompat.CATEGORY_NAVIGATION), builderCreateDynamicLink);
        }
        if (readableMap.hasKey("otherPlatform") && readableMap.getMap("otherPlatform").hasKey("fallbackUrl")) {
            builderCreateDynamicLink.setLongLink(Uri.parse(String.valueOf(builderCreateDynamicLink.buildDynamicLink().getUri()) + "&ofl=" + readableMap.getMap("otherPlatform").getString("fallbackUrl")));
        }
        return builderCreateDynamicLink;
    }

    private void buildAnalyticsParameters(@Nullable ReadableMap readableMap, DynamicLink.Builder builder) {
        if (readableMap == null) {
            return;
        }
        DynamicLink.GoogleAnalyticsParameters.Builder builder2 = new DynamicLink.GoogleAnalyticsParameters.Builder();
        if (readableMap.hasKey("campaign")) {
            builder2.setCampaign(readableMap.getString("campaign"));
        }
        if (readableMap.hasKey("content")) {
            builder2.setContent(readableMap.getString("content"));
        }
        if (readableMap.hasKey("medium")) {
            builder2.setMedium(readableMap.getString("medium"));
        }
        if (readableMap.hasKey("source")) {
            builder2.setSource(readableMap.getString("source"));
        }
        if (readableMap.hasKey(FirebaseAnalytics.Param.TERM)) {
            builder2.setTerm(readableMap.getString(FirebaseAnalytics.Param.TERM));
        }
        builder.setGoogleAnalyticsParameters(builder2.build());
    }

    private void buildAndroidParameters(@Nullable ReadableMap readableMap, DynamicLink.Builder builder) {
        if (readableMap == null) {
            return;
        }
        DynamicLink.AndroidParameters.Builder builder2 = new DynamicLink.AndroidParameters.Builder((String) Objects.requireNonNull(readableMap.getString("packageName")));
        if (readableMap.hasKey("fallbackUrl")) {
            builder2.setFallbackUrl(Uri.parse(readableMap.getString("fallbackUrl")));
        }
        if (readableMap.hasKey("minimumVersion")) {
            builder2.setMinimumVersion(Integer.parseInt((String) Objects.requireNonNull(readableMap.getString("minimumVersion"))));
        }
        builder.setAndroidParameters(builder2.build());
    }

    private void buildIosParameters(@Nullable ReadableMap readableMap, DynamicLink.Builder builder) {
        if (readableMap == null) {
            return;
        }
        DynamicLink.IosParameters.Builder builder2 = new DynamicLink.IosParameters.Builder((String) Objects.requireNonNull(readableMap.getString("bundleId")));
        if (readableMap.hasKey("appStoreId")) {
            builder2.setAppStoreId(readableMap.getString("appStoreId"));
        }
        if (readableMap.hasKey("customScheme")) {
            builder2.setCustomScheme(readableMap.getString("customScheme"));
        }
        if (readableMap.hasKey("fallbackUrl")) {
            builder2.setFallbackUrl(Uri.parse(readableMap.getString("fallbackUrl")));
        }
        if (readableMap.hasKey("iPadBundleId")) {
            builder2.setIpadBundleId(readableMap.getString("iPadBundleId"));
        }
        if (readableMap.hasKey("iPadFallbackUrl")) {
            builder2.setIpadFallbackUrl(Uri.parse(readableMap.getString("iPadFallbackUrl")));
        }
        if (readableMap.hasKey("minimumVersion")) {
            builder2.setMinimumVersion(readableMap.getString("minimumVersion"));
        }
        builder.setIosParameters(builder2.build());
    }

    private void buildItunesParameters(@Nullable ReadableMap readableMap, DynamicLink.Builder builder) {
        if (readableMap == null) {
            return;
        }
        DynamicLink.ItunesConnectAnalyticsParameters.Builder builder2 = new DynamicLink.ItunesConnectAnalyticsParameters.Builder();
        if (readableMap.hasKey("affiliateToken")) {
            builder2.setAffiliateToken(readableMap.getString("affiliateToken"));
        }
        if (readableMap.hasKey("campaignToken")) {
            builder2.setCampaignToken(readableMap.getString("campaignToken"));
        }
        if (readableMap.hasKey("providerToken")) {
            builder2.setProviderToken(readableMap.getString("providerToken"));
        }
        builder.setItunesConnectAnalyticsParameters(builder2.build());
    }

    private void buildNavigationParameters(@Nullable ReadableMap readableMap, DynamicLink.Builder builder) {
        if (readableMap == null) {
            return;
        }
        DynamicLink.NavigationInfoParameters.Builder builder2 = new DynamicLink.NavigationInfoParameters.Builder();
        if (readableMap.hasKey("forcedRedirectEnabled")) {
            builder2.setForcedRedirectEnabled(readableMap.getBoolean("forcedRedirectEnabled"));
        }
        builder.setNavigationInfoParameters(builder2.build());
    }

    private void buildSocialParameters(@Nullable ReadableMap readableMap, DynamicLink.Builder builder) {
        if (readableMap == null) {
            return;
        }
        DynamicLink.SocialMetaTagParameters.Builder builder2 = new DynamicLink.SocialMetaTagParameters.Builder();
        if (readableMap.hasKey("descriptionText")) {
            builder2.setDescription(readableMap.getString("descriptionText"));
        }
        if (readableMap.hasKey("imageUrl")) {
            builder2.setImageUrl(Uri.parse(readableMap.getString("imageUrl")));
        }
        if (readableMap.hasKey("title")) {
            builder2.setTitle(readableMap.getString("title"));
        }
        builder.setSocialMetaTagParameters(builder2.build());
    }

    @Override // com.facebook.react.bridge.ActivityEventListener
    public void onNewIntent(Intent intent) {
        FirebaseDynamicLinks.getInstance().getDynamicLink(intent).addOnCompleteListener(new OnCompleteListener() { // from class: io.invertase.firebase.dynamiclinks.ReactNativeFirebaseDynamicLinksModule$$ExternalSyntheticLambda0
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                this.f$0.lambda$onNewIntent$6(task);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onNewIntent$6(Task task) {
        if (task.isSuccessful()) {
            PendingDynamicLinkData pendingDynamicLinkData = (PendingDynamicLinkData) task.getResult();
            if (pendingDynamicLinkData != null) {
                ReactNativeFirebaseEventEmitter.getSharedInstance().sendEvent(new ReactNativeFirebaseEvent("dynamic_links_link_received", dynamicLinkToWritableMap(pendingDynamicLinkData.getLink().toString(), pendingDynamicLinkData.getMinimumAppVersion(), Arguments.makeNativeMap(pendingDynamicLinkData.getUtmParameters()))));
                return;
            }
            return;
        }
        Log.e(TAG, "RNFB: An unknown exception occurred calling getDynamicLink", task.getException());
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
        this.hostResumed = true;
        Promise promise = this.initialPromise;
        if (promise != null) {
            getInitialLink(promise);
            this.initialPromise = null;
        }
    }
}
