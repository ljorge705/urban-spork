package com.clevertap.android.sdk.inapp.evaluation;

import android.location.Location;
import com.clevertap.android.sdk.CTXtensions;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.db.Column;
import com.clevertap.android.sdk.inapp.TriggerManager;
import com.clevertap.android.sdk.inapp.customtemplates.CustomTemplateInAppData;
import com.clevertap.android.sdk.inapp.customtemplates.TemplatesManager;
import com.clevertap.android.sdk.inapp.store.preference.InAppStore;
import com.clevertap.android.sdk.inapp.store.preference.StoreRegistry;
import com.clevertap.android.sdk.network.EndpointId;
import com.clevertap.android.sdk.network.NetworkHeadersListener;
import com.clevertap.android.sdk.utils.Clock;
import com.clevertap.android.sdk.variables.JsonUtil;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.collections.MapsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: EvaluationManager.kt */
@Metadata(d1 = {"\u0000´\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010!\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\b\u0000\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJN\u0010 \u001a\b\u0012\u0004\u0012\u00020\"0!2\u0006\u0010#\u001a\u00020$2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\"0!2#\b\u0002\u0010&\u001a\u001d\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b(\u0012\b\b)\u0012\u0004\b\b(*\u0012\u0004\u0012\u00020+0'H\u0001¢\u0006\u0002\b,J\u001b\u0010-\u001a\u00020.2\f\u0010/\u001a\b\u0012\u0004\u0012\u00020$0!H\u0001¢\u0006\u0002\b0J$\u00101\u001a\u00020.2\u0012\u00102\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u001c0\u001b2\b\u00103\u001a\u0004\u0018\u000104J2\u00105\u001a\u00020.2\f\u00106\u001a\b\u0012\u0004\u0012\u00020\"0!2\u0012\u00102\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u001c0\u001b2\b\u00103\u001a\u0004\u0018\u000104J>\u00107\u001a\u00020.2\u0012\u00108\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u001c0\u001b2\u0018\u00109\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u001c0\u001b0!2\b\u00103\u001a\u0004\u0018\u000104J,\u0010:\u001a\u00020.2\u0006\u0010;\u001a\u00020\u00112\u0012\u00102\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u001c0\u001b2\b\u00103\u001a\u0004\u0018\u000104JD\u0010<\u001a\u00020.2\u001e\u00102\u001a\u001a\u0012\u0004\u0012\u00020\u0011\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u001c0\u001b0\u001b2\b\u00103\u001a\u0004\u0018\u0001042\u0012\u0010=\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u001c0\u001bJ\u001b\u0010>\u001a\u00020+2\f\u0010/\u001a\b\u0012\u0004\u0012\u00020$0!H\u0001¢\u0006\u0002\b?J\u001f\u0010@\u001a\u00020\u00112\u0006\u0010A\u001a\u00020\u00112\b\b\u0002\u0010B\u001a\u00020CH\u0001¢\u0006\u0002\bDJ\u001b\u0010E\u001a\b\u0012\u0004\u0012\u00020F0!2\u0006\u0010G\u001a\u00020\"H\u0000¢\u0006\u0002\bHJ\u001b\u0010I\u001a\b\u0012\u0004\u0012\u00020J0!2\u0006\u0010K\u001a\u00020\"H\u0001¢\u0006\u0002\bLJ\b\u0010M\u001a\u00020+H\u0007J\u001c\u0010N\u001a\u00020O2\f\u0010P\u001a\b\u0012\u0004\u0012\u00020F0!2\u0006\u0010Q\u001a\u00020\u0011J\u001a\u0010R\u001a\u0004\u0018\u00010\"2\u0006\u0010S\u001a\u00020T2\u0006\u0010U\u001a\u00020VH\u0016J \u0010W\u001a\u00020+2\u0006\u0010X\u001a\u00020\"2\u0006\u0010S\u001a\u00020T2\u0006\u0010U\u001a\u00020VH\u0016J\u0018\u0010Y\u001a\u00020+2\u0006\u0010Z\u001a\u00020\"2\u0006\u0010U\u001a\u00020VH\u0002J\u0018\u0010[\u001a\u00020+2\u0006\u0010Z\u001a\u00020\"2\u0006\u0010U\u001a\u00020VH\u0002J\r\u0010\\\u001a\u00020+H\u0001¢\u0006\u0002\b]J\r\u0010^\u001a\u00020+H\u0001¢\u0006\u0002\b_J\u0010\u0010`\u001a\u00020O2\u0006\u0010a\u001a\u00020\"H\u0002J!\u0010b\u001a\b\u0012\u0004\u0012\u00020\"0!2\f\u0010c\u001a\b\u0012\u0004\u0012\u00020\"0!H\u0000¢\u0006\u0002\bdJ\u001d\u0010e\u001a\u00020+2\u0006\u0010a\u001a\u00020\"2\u0006\u0010U\u001a\u00020VH\u0001¢\u0006\u0002\bfJ\u001f\u0010g\u001a\u00020+2\u0006\u0010a\u001a\u00020\"2\b\b\u0002\u0010B\u001a\u00020CH\u0001¢\u0006\u0002\bhR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R6\u0010\u000f\u001a\u0014\u0012\u0004\u0012\u00020\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\u00108\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000RD\u0010\u001a\u001a\"\u0012\u0004\u0012\u00020\u0011\u0012\u0018\u0012\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0011\u0012\u0006\u0012\u0004\u0018\u00010\u001c0\u001b0\u00120\u00108\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u001d\u0010\u0015\u001a\u0004\b\u001e\u0010\u0017\"\u0004\b\u001f\u0010\u0019R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006i"}, d2 = {"Lcom/clevertap/android/sdk/inapp/evaluation/EvaluationManager;", "Lcom/clevertap/android/sdk/network/NetworkHeadersListener;", "triggersMatcher", "Lcom/clevertap/android/sdk/inapp/evaluation/TriggersMatcher;", "triggersManager", "Lcom/clevertap/android/sdk/inapp/TriggerManager;", "limitsMatcher", "Lcom/clevertap/android/sdk/inapp/evaluation/LimitsMatcher;", "storeRegistry", "Lcom/clevertap/android/sdk/inapp/store/preference/StoreRegistry;", "templatesManager", "Lcom/clevertap/android/sdk/inapp/customtemplates/TemplatesManager;", "(Lcom/clevertap/android/sdk/inapp/evaluation/TriggersMatcher;Lcom/clevertap/android/sdk/inapp/TriggerManager;Lcom/clevertap/android/sdk/inapp/evaluation/LimitsMatcher;Lcom/clevertap/android/sdk/inapp/store/preference/StoreRegistry;Lcom/clevertap/android/sdk/inapp/customtemplates/TemplatesManager;)V", "dateFormatter", "Ljava/text/SimpleDateFormat;", "evaluatedServerSideCampaignIds", "", "", "", "", "getEvaluatedServerSideCampaignIds$clevertap_core_release$annotations", "()V", "getEvaluatedServerSideCampaignIds$clevertap_core_release", "()Ljava/util/Map;", "setEvaluatedServerSideCampaignIds$clevertap_core_release", "(Ljava/util/Map;)V", "suppressedClientSideInApps", "", "", "getSuppressedClientSideInApps$clevertap_core_release$annotations", "getSuppressedClientSideInApps$clevertap_core_release", "setSuppressedClientSideInApps$clevertap_core_release", "evaluate", "", "Lorg/json/JSONObject;", "event", "Lcom/clevertap/android/sdk/inapp/evaluation/EventAdapter;", "inappNotifs", "clearResource", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "url", "", "evaluate$clevertap_core_release", "evaluateClientSide", "Lorg/json/JSONArray;", "events", "evaluateClientSide$clevertap_core_release", "evaluateOnAppLaunchedClientSide", TriggerAdapter.KEY_EVENT_PROPERTIES, "userLocation", "Landroid/location/Location;", "evaluateOnAppLaunchedServerSide", "appLaunchedNotifs", "evaluateOnChargedEvent", "details", FirebaseAnalytics.Param.ITEMS, "evaluateOnEvent", "eventName", "evaluateOnUserAttributeChange", "appFields", "evaluateServerSide", "evaluateServerSide$clevertap_core_release", "generateWzrkId", Constants.INAPP_ID_IN_PAYLOAD, "clock", "Lcom/clevertap/android/sdk/utils/Clock;", "generateWzrkId$clevertap_core_release", "getWhenLimits", "Lcom/clevertap/android/sdk/inapp/evaluation/LimitAdapter;", "limitJSON", "getWhenLimits$clevertap_core_release", "getWhenTriggers", "Lcom/clevertap/android/sdk/inapp/evaluation/TriggerAdapter;", "triggerJson", "getWhenTriggers$clevertap_core_release", "loadSuppressedCSAndEvaluatedSSInAppsIds", "matchWhenLimitsBeforeDisplay", "", "listOfLimitAdapter", Column.CAMPAIGN, "onAttachHeaders", "endpointId", "Lcom/clevertap/android/sdk/network/EndpointId;", "eventType", "Lcom/clevertap/android/sdk/inapp/evaluation/EventType;", "onSentHeaders", "allHeaders", "removeSentEvaluatedServerSideCampaignIds", "header", "removeSentSuppressedClientSideInApps", "saveEvaluatedServerSideInAppIds", "saveEvaluatedServerSideInAppIds$clevertap_core_release", "saveSuppressedClientSideInAppIds", "saveSuppressedClientSideInAppIds$clevertap_core_release", "shouldSuppress", Constants.INAPP_KEY, "sortByPriority", "inApps", "sortByPriority$clevertap_core_release", "suppress", "suppress$clevertap_core_release", "updateTTL", "updateTTL$clevertap_core_release", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class EvaluationManager implements NetworkHeadersListener {
    private final SimpleDateFormat dateFormatter;
    private Map<String, List<Long>> evaluatedServerSideCampaignIds;
    private final LimitsMatcher limitsMatcher;
    private final StoreRegistry storeRegistry;
    private Map<String, List<Map<String, Object>>> suppressedClientSideInApps;
    private final TemplatesManager templatesManager;
    private final TriggerManager triggersManager;
    private final TriggersMatcher triggersMatcher;

    public static /* synthetic */ void getEvaluatedServerSideCampaignIds$clevertap_core_release$annotations() {
    }

    public static /* synthetic */ void getSuppressedClientSideInApps$clevertap_core_release$annotations() {
    }

    public final Map<String, List<Long>> getEvaluatedServerSideCampaignIds$clevertap_core_release() {
        return this.evaluatedServerSideCampaignIds;
    }

    public final Map<String, List<Map<String, Object>>> getSuppressedClientSideInApps$clevertap_core_release() {
        return this.suppressedClientSideInApps;
    }

    public final void setEvaluatedServerSideCampaignIds$clevertap_core_release(Map<String, List<Long>> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.evaluatedServerSideCampaignIds = map;
    }

    public final void setSuppressedClientSideInApps$clevertap_core_release(Map<String, List<Map<String, Object>>> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.suppressedClientSideInApps = map;
    }

    public EvaluationManager(TriggersMatcher triggersMatcher, TriggerManager triggersManager, LimitsMatcher limitsMatcher, StoreRegistry storeRegistry, TemplatesManager templatesManager) {
        Intrinsics.checkNotNullParameter(triggersMatcher, "triggersMatcher");
        Intrinsics.checkNotNullParameter(triggersManager, "triggersManager");
        Intrinsics.checkNotNullParameter(limitsMatcher, "limitsMatcher");
        Intrinsics.checkNotNullParameter(storeRegistry, "storeRegistry");
        Intrinsics.checkNotNullParameter(templatesManager, "templatesManager");
        this.triggersMatcher = triggersMatcher;
        this.triggersManager = triggersManager;
        this.limitsMatcher = limitsMatcher;
        this.storeRegistry = storeRegistry;
        this.templatesManager = templatesManager;
        this.evaluatedServerSideCampaignIds = MapsKt.mutableMapOf(TuplesKt.to(Constants.RAISED, new ArrayList()), TuplesKt.to("profile", new ArrayList()));
        this.suppressedClientSideInApps = MapsKt.mutableMapOf(TuplesKt.to(Constants.RAISED, new ArrayList()), TuplesKt.to("profile", new ArrayList()));
        this.dateFormatter = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
    }

    public final JSONArray evaluateOnEvent(String eventName, Map<String, ? extends Object> eventProperties, Location userLocation) {
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        Intrinsics.checkNotNullParameter(eventProperties, "eventProperties");
        List<EventAdapter> listListOf = CollectionsKt.listOf(new EventAdapter(eventName, eventProperties, null, userLocation, null, 20, null));
        evaluateServerSide$clevertap_core_release(listListOf);
        return evaluateClientSide$clevertap_core_release(listListOf);
    }

    public final JSONArray evaluateOnChargedEvent(Map<String, ? extends Object> details, List<? extends Map<String, ? extends Object>> items, Location userLocation) {
        Intrinsics.checkNotNullParameter(details, "details");
        Intrinsics.checkNotNullParameter(items, "items");
        List<EventAdapter> listListOf = CollectionsKt.listOf(new EventAdapter(Constants.CHARGED_EVENT, details, items, userLocation, null, 16, null));
        evaluateServerSide$clevertap_core_release(listListOf);
        return evaluateClientSide$clevertap_core_release(listListOf);
    }

    public final JSONArray evaluateOnAppLaunchedClientSide(Map<String, ? extends Object> eventProperties, Location userLocation) {
        Intrinsics.checkNotNullParameter(eventProperties, "eventProperties");
        return evaluateClientSide$clevertap_core_release(CollectionsKt.listOf(new EventAdapter(Constants.APP_LAUNCHED_EVENT, eventProperties, null, userLocation, null, 20, null)));
    }

    public final JSONArray evaluateOnAppLaunchedServerSide(List<? extends JSONObject> appLaunchedNotifs, Map<String, ? extends Object> eventProperties, Location userLocation) {
        Intrinsics.checkNotNullParameter(appLaunchedNotifs, "appLaunchedNotifs");
        Intrinsics.checkNotNullParameter(eventProperties, "eventProperties");
        boolean z = false;
        for (JSONObject jSONObject : sortByPriority$clevertap_core_release(evaluate$clevertap_core_release$default(this, new EventAdapter(Constants.APP_LAUNCHED_EVENT, eventProperties, null, userLocation, null, 20, null), appLaunchedNotifs, null, 4, null))) {
            if (!shouldSuppress(jSONObject)) {
                if (z) {
                    saveSuppressedClientSideInAppIds$clevertap_core_release();
                }
                JSONArray jSONArray = new JSONArray();
                jSONArray.put(jSONObject);
                return jSONArray;
            }
            suppress$clevertap_core_release(jSONObject, EventType.RAISED);
            z = true;
        }
        if (z) {
            saveSuppressedClientSideInAppIds$clevertap_core_release();
        }
        return new JSONArray();
    }

    public final boolean matchWhenLimitsBeforeDisplay(List<LimitAdapter> listOfLimitAdapter, String campaignId) {
        Intrinsics.checkNotNullParameter(listOfLimitAdapter, "listOfLimitAdapter");
        Intrinsics.checkNotNullParameter(campaignId, "campaignId");
        return this.limitsMatcher.matchWhenLimits(listOfLimitAdapter, campaignId);
    }

    public final void evaluateServerSide$clevertap_core_release(List<EventAdapter> events) throws JSONException {
        Intrinsics.checkNotNullParameter(events, "events");
        ArrayList arrayList = new ArrayList();
        InAppStore inAppStore = this.storeRegistry.getInAppStore();
        if (inAppStore != null) {
            Iterator<EventAdapter> it = events.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                EventAdapter next = it.next();
                JSONArray serverSideInAppsMetaData = inAppStore.readServerSideInAppsMetaData();
                ArrayList arrayList2 = new ArrayList();
                int length = serverSideInAppsMetaData.length();
                for (int i = 0; i < length; i++) {
                    Object obj = serverSideInAppsMetaData.get(i);
                    if (obj instanceof JSONObject) {
                        arrayList2.add(obj);
                    }
                }
                arrayList.addAll(evaluate$clevertap_core_release$default(this, next, arrayList2, null, 4, null));
            }
            Iterator it2 = arrayList.iterator();
            boolean z = false;
            while (it2.hasNext()) {
                long jOptLong = ((JSONObject) it2.next()).optLong(Constants.INAPP_ID_IN_PAYLOAD);
                if (jOptLong != 0) {
                    List<Long> list = this.evaluatedServerSideCampaignIds.get(EventType.INSTANCE.fromBoolean(events.get(0).isUserAttributeChangeEvent()).getKey());
                    if (list != null) {
                        list.add(Long.valueOf(jOptLong));
                    }
                    z = true;
                }
            }
            if (z) {
                saveEvaluatedServerSideInAppIds$clevertap_core_release();
            }
        }
    }

    public final JSONArray evaluateClientSide$clevertap_core_release(List<EventAdapter> events) throws JSONException {
        Intrinsics.checkNotNullParameter(events, "events");
        ArrayList arrayList = new ArrayList();
        InAppStore inAppStore = this.storeRegistry.getInAppStore();
        if (inAppStore != null) {
            Iterator<T> it = events.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                EventAdapter eventAdapter = (EventAdapter) it.next();
                Object obj = eventAdapter.getEventProperties().get(Constants.KEY_OLD_VALUE);
                Object obj2 = eventAdapter.getEventProperties().get(Constants.KEY_NEW_VALUE);
                if (obj2 == null || !Intrinsics.areEqual(obj2, obj)) {
                    JSONArray clientSideInApps = inAppStore.readClientSideInApps();
                    ArrayList arrayList2 = new ArrayList();
                    int length = clientSideInApps.length();
                    for (int i = 0; i < length; i++) {
                        Object obj3 = clientSideInApps.get(i);
                        if (obj3 instanceof JSONObject) {
                            arrayList2.add(obj3);
                        }
                    }
                    arrayList.addAll(evaluate$clevertap_core_release$default(this, eventAdapter, arrayList2, null, 4, null));
                }
            }
            boolean z = false;
            for (JSONObject jSONObject : sortByPriority$clevertap_core_release(arrayList)) {
                if (!shouldSuppress(jSONObject)) {
                    if (z) {
                        saveSuppressedClientSideInAppIds$clevertap_core_release();
                    }
                    updateTTL$clevertap_core_release$default(this, jSONObject, null, 2, null);
                    JSONArray jSONArray = new JSONArray();
                    jSONArray.put(jSONObject);
                    return jSONArray;
                }
                suppress$clevertap_core_release(jSONObject, EventType.INSTANCE.fromBoolean(events.get(0).isUserAttributeChangeEvent()));
                z = true;
            }
            if (z) {
                saveSuppressedClientSideInAppIds$clevertap_core_release();
            }
            Unit unit = Unit.INSTANCE;
        }
        return new JSONArray();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ List evaluate$clevertap_core_release$default(EvaluationManager evaluationManager, EventAdapter eventAdapter, List list, Function1 function1, int i, Object obj) {
        if ((i & 4) != 0) {
            function1 = new Function1<String, Unit>() { // from class: com.clevertap.android.sdk.inapp.evaluation.EvaluationManager$evaluate$1
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(String it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(String str) {
                    invoke2(str);
                    return Unit.INSTANCE;
                }
            };
        }
        return evaluationManager.evaluate$clevertap_core_release(eventAdapter, list, function1);
    }

    public final List<JSONObject> evaluate$clevertap_core_release(EventAdapter event, List<? extends JSONObject> inappNotifs, Function1<? super String, Unit> clearResource) {
        Intrinsics.checkNotNullParameter(event, "event");
        Intrinsics.checkNotNullParameter(inappNotifs, "inappNotifs");
        Intrinsics.checkNotNullParameter(clearResource, "clearResource");
        ArrayList arrayList = new ArrayList();
        for (JSONObject jSONObject : inappNotifs) {
            CustomTemplateInAppData customTemplateInAppDataCreateFromJson = CustomTemplateInAppData.INSTANCE.createFromJson(jSONObject);
            String templateName = customTemplateInAppDataCreateFromJson != null ? customTemplateInAppDataCreateFromJson.getTemplateName() : null;
            if (templateName == null || this.templatesManager.isTemplateRegistered(templateName)) {
                String campaignId = jSONObject.optString(Constants.INAPP_ID_IN_PAYLOAD);
                if (this.triggersMatcher.matchEvent(getWhenTriggers$clevertap_core_release(jSONObject), event)) {
                    Logger.v("INAPP", "Triggers matched for event " + event.getEventName() + " against inApp " + campaignId);
                    TriggerManager triggerManager = this.triggersManager;
                    Intrinsics.checkNotNullExpressionValue(campaignId, "campaignId");
                    triggerManager.increment(campaignId);
                    boolean zMatchWhenLimits = this.limitsMatcher.matchWhenLimits(getWhenLimits$clevertap_core_release(jSONObject), campaignId);
                    if (this.limitsMatcher.shouldDiscard(getWhenLimits$clevertap_core_release(jSONObject), campaignId)) {
                        clearResource.invoke("");
                    }
                    if (zMatchWhenLimits) {
                        Logger.v("INAPP", "Limits matched for event " + event.getEventName() + " against inApp " + campaignId);
                        arrayList.add(jSONObject);
                    } else {
                        Logger.v("INAPP", "Limits did not matched for event " + event.getEventName() + " against inApp " + campaignId);
                    }
                } else {
                    Logger.v("INAPP", "Triggers did not matched for event " + event.getEventName() + " against inApp " + campaignId);
                }
            }
        }
        return arrayList;
    }

    public final List<TriggerAdapter> getWhenTriggers$clevertap_core_release(JSONObject triggerJson) throws JSONException {
        Intrinsics.checkNotNullParameter(triggerJson, "triggerJson");
        JSONArray jSONArrayOrEmptyArray = CTXtensions.orEmptyArray(triggerJson.optJSONArray(Constants.INAPP_WHEN_TRIGGERS));
        IntRange intRangeUntil = RangesKt.until(0, jSONArrayOrEmptyArray.length());
        ArrayList arrayList = new ArrayList();
        Iterator<Integer> it = intRangeUntil.iterator();
        while (it.hasNext()) {
            Object obj = jSONArrayOrEmptyArray.get(((IntIterator) it).nextInt());
            JSONObject jSONObject = obj instanceof JSONObject ? (JSONObject) obj : null;
            TriggerAdapter triggerAdapter = jSONObject != null ? new TriggerAdapter(jSONObject) : null;
            if (triggerAdapter != null) {
                arrayList.add(triggerAdapter);
            }
        }
        return arrayList;
    }

    public final List<LimitAdapter> getWhenLimits$clevertap_core_release(JSONObject limitJSON) throws JSONException {
        Intrinsics.checkNotNullParameter(limitJSON, "limitJSON");
        JSONArray jSONArrayOrEmptyArray = CTXtensions.orEmptyArray(limitJSON.optJSONArray(Constants.INAPP_FC_LIMITS));
        JSONArray jSONArrayOrEmptyArray2 = CTXtensions.orEmptyArray(limitJSON.optJSONArray(Constants.INAPP_OCCURRENCE_LIMITS));
        ArrayList arrayList = new ArrayList();
        int length = jSONArrayOrEmptyArray.length();
        for (int i = 0; i < length; i++) {
            Object obj = jSONArrayOrEmptyArray.get(i);
            if (obj instanceof JSONObject) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = arrayList;
        ArrayList arrayList3 = new ArrayList();
        int length2 = jSONArrayOrEmptyArray2.length();
        for (int i2 = 0; i2 < length2; i2++) {
            Object obj2 = jSONArrayOrEmptyArray2.get(i2);
            if (obj2 instanceof JSONObject) {
                arrayList3.add(obj2);
            }
        }
        List<JSONObject> listPlus = CollectionsKt.plus((Collection) arrayList2, (Iterable) arrayList3);
        ArrayList arrayList4 = new ArrayList();
        for (JSONObject jSONObject : listPlus) {
            LimitAdapter limitAdapter = CTXtensions.isNotNullAndEmpty(jSONObject) ? new LimitAdapter(jSONObject) : null;
            if (limitAdapter != null) {
                arrayList4.add(limitAdapter);
            }
        }
        return arrayList4;
    }

    public final List<JSONObject> sortByPriority$clevertap_core_release(List<? extends JSONObject> inApps) {
        Intrinsics.checkNotNullParameter(inApps, "inApps");
        final EvaluationManager$sortByPriority$priority$1 evaluationManager$sortByPriority$priority$1 = new Function1<JSONObject, Integer>() { // from class: com.clevertap.android.sdk.inapp.evaluation.EvaluationManager$sortByPriority$priority$1
            @Override // kotlin.jvm.functions.Function1
            public final Integer invoke(JSONObject inApp) {
                Intrinsics.checkNotNullParameter(inApp, "inApp");
                return Integer.valueOf(inApp.optInt("priority", 1));
            }
        };
        final EvaluationManager$sortByPriority$ti$1 evaluationManager$sortByPriority$ti$1 = new Function1<JSONObject, String>() { // from class: com.clevertap.android.sdk.inapp.evaluation.EvaluationManager$sortByPriority$ti$1
            @Override // kotlin.jvm.functions.Function1
            public final String invoke(JSONObject inApp) {
                Intrinsics.checkNotNullParameter(inApp, "inApp");
                String strOptString = inApp.optString(Constants.INAPP_ID_IN_PAYLOAD, String.valueOf(Clock.INSTANCE.getSYSTEM().newDate().getTime() / 1000));
                Intrinsics.checkNotNullExpressionValue(strOptString, "inApp.optString(Constant….time / 1000).toString())");
                return strOptString;
            }
        };
        final Comparator comparator = new Comparator() { // from class: com.clevertap.android.sdk.inapp.evaluation.EvaluationManager$sortByPriority$$inlined$compareByDescending$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues((Comparable) evaluationManager$sortByPriority$priority$1.invoke((JSONObject) t2), (Comparable) evaluationManager$sortByPriority$priority$1.invoke((JSONObject) t));
            }
        };
        return CollectionsKt.sortedWith(inApps, new Comparator() { // from class: com.clevertap.android.sdk.inapp.evaluation.EvaluationManager$sortByPriority$$inlined$thenBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                int iCompare = comparator.compare(t, t2);
                if (iCompare != 0) {
                    return iCompare;
                }
                return ComparisonsKt.compareValues((Comparable) evaluationManager$sortByPriority$ti$1.invoke((JSONObject) t), (Comparable) evaluationManager$sortByPriority$ti$1.invoke((JSONObject) t2));
            }
        });
    }

    private final boolean shouldSuppress(JSONObject inApp) {
        return inApp.optBoolean(Constants.INAPP_SUPPRESSED);
    }

    public final void suppress$clevertap_core_release(JSONObject inApp, EventType eventType) {
        Intrinsics.checkNotNullParameter(inApp, "inApp");
        Intrinsics.checkNotNullParameter(eventType, "eventType");
        String campaignId = inApp.optString(Constants.INAPP_ID_IN_PAYLOAD);
        Intrinsics.checkNotNullExpressionValue(campaignId, "campaignId");
        String strGenerateWzrkId$clevertap_core_release$default = generateWzrkId$clevertap_core_release$default(this, campaignId, null, 2, null);
        String strOptString = inApp.optString(Constants.INAPP_WZRK_PIVOT, "wzrk_default");
        int iOptInt = inApp.optInt(Constants.INAPP_WZRK_CGID);
        List<Map<String, Object>> list = this.suppressedClientSideInApps.get(eventType.getKey());
        if (list != null) {
            list.add(MapsKt.mapOf(TuplesKt.to(Constants.NOTIFICATION_ID_TAG, strGenerateWzrkId$clevertap_core_release$default), TuplesKt.to(Constants.INAPP_WZRK_PIVOT, strOptString), TuplesKt.to(Constants.INAPP_WZRK_CGID, Integer.valueOf(iOptInt))));
        }
    }

    public static /* synthetic */ String generateWzrkId$clevertap_core_release$default(EvaluationManager evaluationManager, String str, Clock clock, int i, Object obj) {
        if ((i & 2) != 0) {
            clock = Clock.INSTANCE.getSYSTEM();
        }
        return evaluationManager.generateWzrkId$clevertap_core_release(str, clock);
    }

    public final String generateWzrkId$clevertap_core_release(String ti, Clock clock) {
        Intrinsics.checkNotNullParameter(ti, "ti");
        Intrinsics.checkNotNullParameter(clock, "clock");
        return ti + '_' + this.dateFormatter.format(clock.newDate());
    }

    public static /* synthetic */ void updateTTL$clevertap_core_release$default(EvaluationManager evaluationManager, JSONObject jSONObject, Clock clock, int i, Object obj) throws JSONException {
        if ((i & 2) != 0) {
            clock = Clock.INSTANCE.getSYSTEM();
        }
        evaluationManager.updateTTL$clevertap_core_release(jSONObject, clock);
    }

    public final void updateTTL$clevertap_core_release(JSONObject inApp, Clock clock) throws JSONException {
        Intrinsics.checkNotNullParameter(inApp, "inApp");
        Intrinsics.checkNotNullParameter(clock, "clock");
        Object objOpt = inApp.opt(Constants.WZRK_TIME_TO_LIVE_OFFSET);
        Long l = objOpt instanceof Long ? (Long) objOpt : null;
        if (l != null) {
            inApp.put("wzrk_ttl", clock.currentTimeSeconds() + l.longValue());
        } else {
            inApp.remove("wzrk_ttl");
        }
    }

    private final void removeSentEvaluatedServerSideCampaignIds(JSONObject header, EventType eventType) {
        JSONArray jSONArrayOptJSONArray = header.optJSONArray(Constants.INAPP_SS_EVAL_META);
        if (jSONArrayOptJSONArray != null) {
            int length = jSONArrayOptJSONArray.length();
            boolean z = false;
            for (int i = 0; i < length; i++) {
                final long jOptLong = jSONArrayOptJSONArray.optLong(i);
                if (jOptLong != 0) {
                    List<Long> list = this.evaluatedServerSideCampaignIds.get(eventType.getKey());
                    if (list != null) {
                        CollectionsKt.removeAll((List) list, (Function1) new Function1<Long, Boolean>() { // from class: com.clevertap.android.sdk.inapp.evaluation.EvaluationManager$removeSentEvaluatedServerSideCampaignIds$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            public final Boolean invoke(long j) {
                                return Boolean.valueOf(j == jOptLong);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Boolean invoke(Long l) {
                                return invoke(l.longValue());
                            }
                        });
                    }
                    z = true;
                }
            }
            if (z) {
                saveEvaluatedServerSideInAppIds$clevertap_core_release();
            }
        }
    }

    private final void removeSentSuppressedClientSideInApps(JSONObject header, EventType eventType) {
        List<Map<String, Object>> list;
        Iterator<Map<String, Object>> it;
        JSONArray jSONArrayOptJSONArray = header.optJSONArray(Constants.INAPP_SUPPRESSED_META);
        if (jSONArrayOptJSONArray == null || (list = this.suppressedClientSideInApps.get(eventType.getKey())) == null || (it = list.iterator()) == null) {
            return;
        }
        boolean z = false;
        while (it.hasNext()) {
            Object obj = it.next().get(Constants.NOTIFICATION_ID_TAG);
            String str = obj instanceof String ? (String) obj : null;
            if (str != null) {
                String string = jSONArrayOptJSONArray.toString();
                Intrinsics.checkNotNullExpressionValue(string, "inApps.toString()");
                if (StringsKt.contains$default((CharSequence) string, (CharSequence) str, false, 2, (Object) null)) {
                    it.remove();
                    z = true;
                }
            }
        }
        if (z) {
            saveSuppressedClientSideInAppIds$clevertap_core_release();
        }
    }

    @Override // com.clevertap.android.sdk.network.NetworkHeadersListener
    public JSONObject onAttachHeaders(EndpointId endpointId, EventType eventType) throws JSONException {
        Intrinsics.checkNotNullParameter(endpointId, "endpointId");
        Intrinsics.checkNotNullParameter(eventType, "eventType");
        JSONObject jSONObject = new JSONObject();
        if (endpointId == EndpointId.ENDPOINT_A1) {
            List<Long> list = this.evaluatedServerSideCampaignIds.get(eventType.getKey());
            if (list != null) {
                if (!(!list.isEmpty())) {
                    list = null;
                }
                if (list != null) {
                    jSONObject.put(Constants.INAPP_SS_EVAL_META, JsonUtil.listToJsonArray(list));
                }
            }
            List<Map<String, Object>> list2 = this.suppressedClientSideInApps.get(eventType.getKey());
            if (list2 != null) {
                if (!(!list2.isEmpty())) {
                    list2 = null;
                }
                if (list2 != null) {
                    jSONObject.put(Constants.INAPP_SUPPRESSED_META, JsonUtil.listToJsonArray(list2));
                }
            }
        }
        if (CTXtensions.isNotNullAndEmpty(jSONObject)) {
            return jSONObject;
        }
        return null;
    }

    @Override // com.clevertap.android.sdk.network.NetworkHeadersListener
    public void onSentHeaders(JSONObject allHeaders, EndpointId endpointId, EventType eventType) {
        Intrinsics.checkNotNullParameter(allHeaders, "allHeaders");
        Intrinsics.checkNotNullParameter(endpointId, "endpointId");
        Intrinsics.checkNotNullParameter(eventType, "eventType");
        if (endpointId == EndpointId.ENDPOINT_A1) {
            removeSentEvaluatedServerSideCampaignIds(allHeaders, eventType);
            removeSentSuppressedClientSideInApps(allHeaders, eventType);
        }
    }

    public final void loadSuppressedCSAndEvaluatedSSInAppsIds() {
        InAppStore inAppStore = this.storeRegistry.getInAppStore();
        if (inAppStore != null) {
            Map<String, List<Long>> map = this.evaluatedServerSideCampaignIds;
            Map<? extends String, ? extends List<Long>> mapMapFromJson = JsonUtil.mapFromJson(inAppStore.readEvaluatedServerSideInAppIds());
            Intrinsics.checkNotNullExpressionValue(mapMapFromJson, "mapFromJson(store.readEv…atedServerSideInAppIds())");
            map.putAll(mapMapFromJson);
            Map<String, List<Map<String, Object>>> map2 = this.suppressedClientSideInApps;
            Map<? extends String, ? extends List<Map<String, Object>>> mapMapFromJson2 = JsonUtil.mapFromJson(inAppStore.readSuppressedClientSideInAppIds());
            Intrinsics.checkNotNullExpressionValue(mapMapFromJson2, "mapFromJson(store.readSu…ssedClientSideInAppIds())");
            map2.putAll(mapMapFromJson2);
        }
    }

    public final void saveEvaluatedServerSideInAppIds$clevertap_core_release() {
        InAppStore inAppStore = this.storeRegistry.getInAppStore();
        if (inAppStore != null) {
            inAppStore.storeEvaluatedServerSideInAppIds(new JSONObject(MapsKt.toMap(this.evaluatedServerSideCampaignIds)));
        }
    }

    public final void saveSuppressedClientSideInAppIds$clevertap_core_release() {
        InAppStore inAppStore = this.storeRegistry.getInAppStore();
        if (inAppStore != null) {
            inAppStore.storeSuppressedClientSideInAppIds(new JSONObject(MapsKt.toMap(this.suppressedClientSideInApps)));
        }
    }

    public final JSONArray evaluateOnUserAttributeChange(Map<String, ? extends Map<String, ? extends Object>> eventProperties, Location userLocation, Map<String, ? extends Object> appFields) {
        Intrinsics.checkNotNullParameter(eventProperties, "eventProperties");
        Intrinsics.checkNotNullParameter(appFields, "appFields");
        ArrayList arrayList = new ArrayList(eventProperties.size());
        for (Map.Entry<String, ? extends Map<String, ? extends Object>> entry : eventProperties.entrySet()) {
            Map mutableMap = MapsKt.toMutableMap(entry.getValue());
            mutableMap.putAll(appFields);
            arrayList.add(new EventAdapter(entry.getKey() + Constants.USER_ATTRIBUTE_CHANGE, mutableMap, null, userLocation, entry.getKey(), 4, null));
        }
        ArrayList arrayList2 = arrayList;
        evaluateServerSide$clevertap_core_release(arrayList2);
        return evaluateClientSide$clevertap_core_release(arrayList2);
    }
}
