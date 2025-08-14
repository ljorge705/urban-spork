package com.clevertap.android.sdk.inapp.data;

import com.clevertap.android.sdk.CTXtensions;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.inapp.CTInAppNotificationMedia;
import com.clevertap.android.sdk.inapp.customtemplates.CustomTemplateInAppData;
import com.clevertap.android.sdk.inapp.customtemplates.TemplatesManager;
import com.clevertap.android.sdk.inapp.evaluation.LimitAdapter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: InAppResponseAdapter.kt */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0005\b\u0000\u0018\u0000 32\u00020\u0001:\u00013B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001e\u0010,\u001a\u00020-2\f\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00100/2\u0006\u0010\u0004\u001a\u00020\u0005H\u0002J$\u00100\u001a\u00020-2\f\u00101\u001a\b\u0012\u0004\u0012\u00020\u00100/2\f\u00102\u001a\b\u0012\u0004\u0012\u00020\u00100/H\u0002R\u001f\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0006\u0012\u0004\u0018\u00010\n0\b¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001f\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0006\u0012\u0004\u0018\u00010\n0\b¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0017\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016R\u001f\u0010\u0019\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0006\u0012\u0004\u0018\u00010\n0\b¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\fR\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00100\u001c¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR#\u0010\u001f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020 0\b0\u001c¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001eR\u0017\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00100\u001c¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001eR\u0017\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00100\u001c¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001eR\u0017\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00100\u001c¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u001eR\u001f\u0010(\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0006\u0012\u0004\u0018\u00010\n0\b¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\fR\u001f\u0010*\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0006\u0012\u0004\u0018\u00010\n0\b¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\f¨\u00064"}, d2 = {"Lcom/clevertap/android/sdk/inapp/data/InAppResponseAdapter;", "", "responseJson", "Lorg/json/JSONObject;", "templatesManager", "Lcom/clevertap/android/sdk/inapp/customtemplates/TemplatesManager;", "(Lorg/json/JSONObject;Lcom/clevertap/android/sdk/inapp/customtemplates/TemplatesManager;)V", "appLaunchServerSideInApps", "Lkotlin/Pair;", "", "Lorg/json/JSONArray;", "getAppLaunchServerSideInApps", "()Lkotlin/Pair;", "clientSideInApps", "getClientSideInApps", "inAppMode", "", "getInAppMode", "()Ljava/lang/String;", "inAppsPerDay", "", "getInAppsPerDay", "()I", "inAppsPerSession", "getInAppsPerSession", "legacyInApps", "getLegacyInApps", "preloadAssets", "", "getPreloadAssets", "()Ljava/util/List;", "preloadAssetsMeta", "Lcom/clevertap/android/sdk/inapp/data/CtCacheType;", "getPreloadAssetsMeta", "preloadFiles", "getPreloadFiles", "preloadGifs", "getPreloadGifs", "preloadImages", "getPreloadImages", "serverSideInApps", "getServerSideInApps", "staleInApps", "getStaleInApps", "fetchFilesUrlsForTemplates", "", "filesList", "", "fetchMediaUrls", "imageList", "gifList", "Companion", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class InAppResponseAdapter {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String IN_APP_DAILY_KEY = "imp";
    private static final int IN_APP_DEFAULT_DAILY = 10;
    private static final int IN_APP_DEFAULT_SESSION = 10;
    private static final String IN_APP_SESSION_KEY = "imc";
    private final Pair<Boolean, JSONArray> appLaunchServerSideInApps;
    private final Pair<Boolean, JSONArray> clientSideInApps;
    private final String inAppMode;
    private final int inAppsPerDay;
    private final int inAppsPerSession;
    private final Pair<Boolean, JSONArray> legacyInApps;
    private final List<String> preloadAssets;
    private final List<Pair<String, CtCacheType>> preloadAssetsMeta;
    private final List<String> preloadFiles;
    private final List<String> preloadGifs;
    private final List<String> preloadImages;
    private final Pair<Boolean, JSONArray> serverSideInApps;
    private final Pair<Boolean, JSONArray> staleInApps;

    @JvmStatic
    public static final List<LimitAdapter> getListOfWhenLimits(JSONObject jSONObject) {
        return INSTANCE.getListOfWhenLimits(jSONObject);
    }

    public final Pair<Boolean, JSONArray> getAppLaunchServerSideInApps() {
        return this.appLaunchServerSideInApps;
    }

    public final Pair<Boolean, JSONArray> getClientSideInApps() {
        return this.clientSideInApps;
    }

    public final String getInAppMode() {
        return this.inAppMode;
    }

    public final int getInAppsPerDay() {
        return this.inAppsPerDay;
    }

    public final int getInAppsPerSession() {
        return this.inAppsPerSession;
    }

    public final Pair<Boolean, JSONArray> getLegacyInApps() {
        return this.legacyInApps;
    }

    public final List<String> getPreloadAssets() {
        return this.preloadAssets;
    }

    public final List<Pair<String, CtCacheType>> getPreloadAssetsMeta() {
        return this.preloadAssetsMeta;
    }

    public final List<String> getPreloadFiles() {
        return this.preloadFiles;
    }

    public final List<String> getPreloadGifs() {
        return this.preloadGifs;
    }

    public final List<String> getPreloadImages() {
        return this.preloadImages;
    }

    public final Pair<Boolean, JSONArray> getServerSideInApps() {
        return this.serverSideInApps;
    }

    public final Pair<Boolean, JSONArray> getStaleInApps() {
        return this.staleInApps;
    }

    public InAppResponseAdapter(JSONObject responseJson, TemplatesManager templatesManager) throws JSONException {
        Intrinsics.checkNotNullParameter(responseJson, "responseJson");
        Intrinsics.checkNotNullParameter(templatesManager, "templatesManager");
        this.legacyInApps = CTXtensions.safeGetJSONArrayOrNullIfEmpty(responseJson, Constants.INAPP_JSON_RESPONSE_KEY);
        this.clientSideInApps = CTXtensions.safeGetJSONArray(responseJson, "inapp_notifs_cs");
        this.serverSideInApps = CTXtensions.safeGetJSONArray(responseJson, "inapp_notifs_ss");
        this.appLaunchServerSideInApps = CTXtensions.safeGetJSONArrayOrNullIfEmpty(responseJson, Constants.INAPP_NOTIFS_APP_LAUNCHED_KEY);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        fetchMediaUrls(arrayList, arrayList2);
        fetchFilesUrlsForTemplates(arrayList3, templatesManager);
        this.preloadImages = arrayList;
        this.preloadGifs = arrayList2;
        this.preloadFiles = arrayList3;
        ArrayList arrayList4 = arrayList2;
        ArrayList arrayList5 = arrayList3;
        this.preloadAssets = CollectionsKt.plus((Collection) CollectionsKt.plus((Collection) arrayList, (Iterable) arrayList4), (Iterable) arrayList5);
        ArrayList arrayList6 = arrayList;
        ArrayList arrayList7 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList6, 10));
        Iterator it = arrayList6.iterator();
        while (it.hasNext()) {
            arrayList7.add(new Pair((String) it.next(), CtCacheType.IMAGE));
        }
        ArrayList arrayList8 = arrayList7;
        ArrayList arrayList9 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList4, 10));
        Iterator it2 = arrayList4.iterator();
        while (it2.hasNext()) {
            arrayList9.add(new Pair((String) it2.next(), CtCacheType.GIF));
        }
        List listPlus = CollectionsKt.plus((Collection) arrayList8, (Iterable) arrayList9);
        ArrayList arrayList10 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList5, 10));
        Iterator it3 = arrayList5.iterator();
        while (it3.hasNext()) {
            arrayList10.add(new Pair((String) it3.next(), CtCacheType.FILES));
        }
        List listPlus2 = CollectionsKt.plus((Collection) listPlus, (Iterable) arrayList10);
        HashSet hashSet = new HashSet();
        ArrayList arrayList11 = new ArrayList();
        for (Object obj : listPlus2) {
            if (hashSet.add((String) ((Pair) obj).getFirst())) {
                arrayList11.add(obj);
            }
        }
        this.preloadAssetsMeta = arrayList11;
        this.inAppsPerSession = responseJson.optInt("imc", 10);
        this.inAppsPerDay = responseJson.optInt("imp", 10);
        String strOptString = responseJson.optString(Constants.INAPP_DELIVERY_MODE_KEY, "");
        Intrinsics.checkNotNullExpressionValue(strOptString, "responseJson.optString(C…PP_DELIVERY_MODE_KEY, \"\")");
        this.inAppMode = strOptString;
        this.staleInApps = CTXtensions.safeGetJSONArrayOrNullIfEmpty(responseJson, Constants.INAPP_NOTIFS_STALE_KEY);
    }

    /* compiled from: InAppResponseAdapter.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\rH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/clevertap/android/sdk/inapp/data/InAppResponseAdapter$Companion;", "", "()V", "IN_APP_DAILY_KEY", "", "IN_APP_DEFAULT_DAILY", "", "IN_APP_DEFAULT_SESSION", "IN_APP_SESSION_KEY", "getListOfWhenLimits", "", "Lcom/clevertap/android/sdk/inapp/evaluation/LimitAdapter;", "limitJSON", "Lorg/json/JSONObject;", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final List<LimitAdapter> getListOfWhenLimits(JSONObject limitJSON) throws JSONException {
            Intrinsics.checkNotNullParameter(limitJSON, "limitJSON");
            JSONArray jSONArrayOrEmptyArray = CTXtensions.orEmptyArray(limitJSON.optJSONArray(Constants.INAPP_FC_LIMITS));
            ArrayList arrayList = new ArrayList();
            int length = jSONArrayOrEmptyArray.length();
            for (int i = 0; i < length; i++) {
                Object obj = jSONArrayOrEmptyArray.get(i);
                if (obj instanceof JSONObject) {
                    arrayList.add(obj);
                }
            }
            ArrayList arrayList2 = arrayList;
            ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                arrayList3.add(new LimitAdapter((JSONObject) it.next()));
            }
            return CollectionsKt.toMutableList((Collection) arrayList3);
        }
    }

    private final void fetchMediaUrls(List<String> imageList, List<String> gifList) throws JSONException {
        JSONArray second;
        CTInAppNotificationMedia cTInAppNotificationMediaInitWithJSON;
        CTInAppNotificationMedia cTInAppNotificationMediaInitWithJSON2;
        if (!this.clientSideInApps.getFirst().booleanValue() || (second = this.clientSideInApps.getSecond()) == null) {
            return;
        }
        int length = second.length();
        for (int i = 0; i < length; i++) {
            Object obj = second.get(i);
            if (obj instanceof JSONObject) {
                JSONObject jSONObject = (JSONObject) obj;
                JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(Constants.KEY_MEDIA);
                if (jSONObjectOptJSONObject != null && (cTInAppNotificationMediaInitWithJSON2 = new CTInAppNotificationMedia().initWithJSON(jSONObjectOptJSONObject, 1)) != null && cTInAppNotificationMediaInitWithJSON2.getMediaUrl() != null) {
                    if (cTInAppNotificationMediaInitWithJSON2.isImage()) {
                        String mediaUrl = cTInAppNotificationMediaInitWithJSON2.getMediaUrl();
                        Intrinsics.checkNotNullExpressionValue(mediaUrl, "portraitMedia.mediaUrl");
                        imageList.add(mediaUrl);
                    } else if (cTInAppNotificationMediaInitWithJSON2.isGIF()) {
                        String mediaUrl2 = cTInAppNotificationMediaInitWithJSON2.getMediaUrl();
                        Intrinsics.checkNotNullExpressionValue(mediaUrl2, "portraitMedia.mediaUrl");
                        gifList.add(mediaUrl2);
                    }
                }
                JSONObject jSONObjectOptJSONObject2 = jSONObject.optJSONObject(Constants.KEY_MEDIA_LANDSCAPE);
                if (jSONObjectOptJSONObject2 != null && (cTInAppNotificationMediaInitWithJSON = new CTInAppNotificationMedia().initWithJSON(jSONObjectOptJSONObject2, 2)) != null && cTInAppNotificationMediaInitWithJSON.getMediaUrl() != null) {
                    if (cTInAppNotificationMediaInitWithJSON.isImage()) {
                        String mediaUrl3 = cTInAppNotificationMediaInitWithJSON.getMediaUrl();
                        Intrinsics.checkNotNullExpressionValue(mediaUrl3, "landscapeMedia.mediaUrl");
                        imageList.add(mediaUrl3);
                    } else if (cTInAppNotificationMediaInitWithJSON.isGIF()) {
                        String mediaUrl4 = cTInAppNotificationMediaInitWithJSON.getMediaUrl();
                        Intrinsics.checkNotNullExpressionValue(mediaUrl4, "landscapeMedia.mediaUrl");
                        gifList.add(mediaUrl4);
                    }
                }
            }
        }
    }

    private final void fetchFilesUrlsForTemplates(List<String> filesList, TemplatesManager templatesManager) {
        JSONArray second;
        if (!this.clientSideInApps.getFirst().booleanValue() || (second = this.clientSideInApps.getSecond()) == null) {
            return;
        }
        int length = second.length();
        for (int i = 0; i < length; i++) {
            CustomTemplateInAppData customTemplateInAppDataCreateFromJson = CustomTemplateInAppData.INSTANCE.createFromJson(second.optJSONObject(i));
            if (customTemplateInAppDataCreateFromJson != null) {
                customTemplateInAppDataCreateFromJson.getFileArgsUrls(templatesManager, filesList);
            }
        }
    }
}
