package com.clevertap.android.sdk.inapp.evaluation;

import android.location.Location;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.Utils;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: EventAdapter.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001BW\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u0005\u0012\u001c\b\u0002\u0010\u0006\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00050\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u000bJ\u0012\u0010\u0017\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0018\u001a\u00020\u0003H\u0002J\u0017\u0010\u0019\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0018\u001a\u00020\u0003H\u0001¢\u0006\u0002\b\u001aJ\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\u00072\u0006\u0010\u0018\u001a\u00020\u0003J\u000e\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u0018\u001a\u00020\u0003J\u0006\u0010\u001e\u001a\u00020\u001fJ\u0006\u0010 \u001a\u00020\u001fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR%\u0010\u0006\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00050\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\rR \u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000fR\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006!"}, d2 = {"Lcom/clevertap/android/sdk/inapp/evaluation/EventAdapter;", "", "eventName", "", TriggerAdapter.KEY_EVENT_PROPERTIES, "", FirebaseAnalytics.Param.ITEMS, "", "userLocation", "Landroid/location/Location;", TriggerAdapter.KEY_PROFILE_ATTR_NAME, "(Ljava/lang/String;Ljava/util/Map;Ljava/util/List;Landroid/location/Location;Ljava/lang/String;)V", "getEventName", "()Ljava/lang/String;", "getEventProperties", "()Ljava/util/Map;", "getItems", "()Ljava/util/List;", "getProfileAttrName", "systemPropToKey", "getSystemPropToKey$clevertap_core_release", "getUserLocation", "()Landroid/location/Location;", "evaluateActualPropertyValue", TriggerAdapter.INAPP_PROPERTYNAME, "getActualPropertyValue", "getActualPropertyValue$clevertap_core_release", "getItemValue", "Lcom/clevertap/android/sdk/inapp/evaluation/TriggerValue;", "getPropertyValue", "isChargedEvent", "", "isUserAttributeChangeEvent", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class EventAdapter {
    private final String eventName;
    private final Map<String, Object> eventProperties;
    private final List<Map<String, Object>> items;
    private final String profileAttrName;
    private final Map<String, String> systemPropToKey;
    private final Location userLocation;

    public final String getEventName() {
        return this.eventName;
    }

    public final Map<String, Object> getEventProperties() {
        return this.eventProperties;
    }

    public final List<Map<String, Object>> getItems() {
        return this.items;
    }

    public final String getProfileAttrName() {
        return this.profileAttrName;
    }

    public final Map<String, String> getSystemPropToKey$clevertap_core_release() {
        return this.systemPropToKey;
    }

    public final Location getUserLocation() {
        return this.userLocation;
    }

    public final boolean isUserAttributeChangeEvent() {
        return this.profileAttrName != null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public EventAdapter(String eventName, Map<String, ? extends Object> eventProperties, List<? extends Map<String, ? extends Object>> items, Location location, String str) {
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        Intrinsics.checkNotNullParameter(eventProperties, "eventProperties");
        Intrinsics.checkNotNullParameter(items, "items");
        this.eventName = eventName;
        this.eventProperties = eventProperties;
        this.items = items;
        this.userLocation = location;
        this.profileAttrName = str;
        this.systemPropToKey = MapsKt.mapOf(TuplesKt.to("CT App Version", Constants.CLTAP_APP_VERSION), TuplesKt.to("ct_app_version", Constants.CLTAP_APP_VERSION), TuplesKt.to("CT Latitude", Constants.CLTAP_LATITUDE), TuplesKt.to("ct_latitude", Constants.CLTAP_LATITUDE), TuplesKt.to("CT Longitude", Constants.CLTAP_LONGITUDE), TuplesKt.to("ct_longitude", Constants.CLTAP_LONGITUDE), TuplesKt.to("CT OS Version", Constants.CLTAP_OS_VERSION), TuplesKt.to("ct_os_version", Constants.CLTAP_OS_VERSION), TuplesKt.to("CT SDK Version", Constants.CLTAP_SDK_VERSION), TuplesKt.to("ct_sdk_version", Constants.CLTAP_SDK_VERSION), TuplesKt.to("CT Network Carrier", Constants.CLTAP_CARRIER), TuplesKt.to("ct_network_carrier", Constants.CLTAP_CARRIER), TuplesKt.to("CT Network Type", Constants.CLTAP_NETWORK_TYPE), TuplesKt.to("ct_network_type", Constants.CLTAP_NETWORK_TYPE), TuplesKt.to("CT Connected To WiFi", "wifi"), TuplesKt.to("ct_connected_to_wifi", "wifi"), TuplesKt.to("CT Bluetooth Version", Constants.CLTAP_BLUETOOTH_VERSION), TuplesKt.to("ct_bluetooth_version", Constants.CLTAP_BLUETOOTH_VERSION), TuplesKt.to("CT Bluetooth Enabled", Constants.CLTAP_BLUETOOTH_ENABLED), TuplesKt.to("ct_bluetooth_enabled", Constants.CLTAP_BLUETOOTH_ENABLED), TuplesKt.to("CT App Name", "appnId"));
    }

    public /* synthetic */ EventAdapter(String str, Map map, List list, Location location, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, map, (i & 4) != 0 ? CollectionsKt.emptyList() : list, (i & 8) != 0 ? null : location, (i & 16) != 0 ? null : str2);
    }

    public final TriggerValue getPropertyValue(String propertyName) {
        Intrinsics.checkNotNullParameter(propertyName, "propertyName");
        return new TriggerValue(getActualPropertyValue$clevertap_core_release(propertyName), null, 2, null);
    }

    public final List<TriggerValue> getItemValue(String propertyName) {
        Intrinsics.checkNotNullParameter(propertyName, "propertyName");
        List<Map> listFilterNotNull = CollectionsKt.filterNotNull(this.items);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listFilterNotNull, 10));
        for (Map map : listFilterNotNull) {
            Object obj = map.get(propertyName);
            if (obj == null) {
                obj = map.get(Utils.getNormalizedName(propertyName));
            }
            if (obj == null) {
                ArrayList arrayList2 = new ArrayList(map.size());
                for (Map.Entry entry : map.entrySet()) {
                    arrayList2.add(TuplesKt.to(Utils.getNormalizedName((String) entry.getKey()), entry.getValue()));
                }
                obj = MapsKt.toMap(arrayList2).get(Utils.getNormalizedName(propertyName));
            }
            arrayList.add(new TriggerValue(obj, null, 2, null));
        }
        ArrayList arrayList3 = new ArrayList();
        for (Object obj2 : arrayList) {
            if (((TriggerValue) obj2).getValue() != null) {
                arrayList3.add(obj2);
            }
        }
        return arrayList3;
    }

    public final boolean isChargedEvent() {
        return Intrinsics.areEqual(this.eventName, Constants.CHARGED_EVENT);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:22:0x004d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getActualPropertyValue$clevertap_core_release(java.lang.String r6) {
        /*
            r5 = this;
            java.lang.String r0 = "propertyName"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            java.lang.Object r0 = r5.evaluateActualPropertyValue(r6)
            if (r0 != 0) goto L5e
            int r0 = r6.hashCode()
            java.lang.String r1 = "Variant"
            java.lang.String r2 = "wzrk_id"
            java.lang.String r3 = "wzrk_pivot"
            java.lang.String r4 = "Campaign id"
            switch(r0) {
                case -543370741: goto L42;
                case 1035561631: goto L36;
                case 1840075742: goto L2a;
                case 1901439077: goto L1e;
                default: goto L1d;
            }
        L1d:
            goto L4d
        L1e:
            boolean r0 = r6.equals(r1)
            if (r0 != 0) goto L25
            goto L4d
        L25:
            java.lang.Object r6 = r5.evaluateActualPropertyValue(r3)
            goto L5d
        L2a:
            boolean r0 = r6.equals(r2)
            if (r0 != 0) goto L31
            goto L4d
        L31:
            java.lang.Object r6 = r5.evaluateActualPropertyValue(r4)
            goto L5d
        L36:
            boolean r0 = r6.equals(r3)
            if (r0 != 0) goto L3d
            goto L4d
        L3d:
            java.lang.Object r6 = r5.evaluateActualPropertyValue(r1)
            goto L5d
        L42:
            boolean r0 = r6.equals(r4)
            if (r0 == 0) goto L4d
            java.lang.Object r6 = r5.evaluateActualPropertyValue(r2)
            goto L5d
        L4d:
            java.util.Map<java.lang.String, java.lang.String> r0 = r5.systemPropToKey
            java.lang.Object r6 = r0.get(r6)
            java.lang.String r6 = (java.lang.String) r6
            if (r6 == 0) goto L5c
            java.lang.Object r6 = r5.evaluateActualPropertyValue(r6)
            goto L5d
        L5c:
            r6 = 0
        L5d:
            r0 = r6
        L5e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.clevertap.android.sdk.inapp.evaluation.EventAdapter.getActualPropertyValue$clevertap_core_release(java.lang.String):java.lang.Object");
    }

    private final Object evaluateActualPropertyValue(String propertyName) {
        Object obj = this.eventProperties.get(propertyName);
        if (obj == null) {
            obj = this.eventProperties.get(Utils.getNormalizedName(propertyName));
        }
        if (obj != null) {
            return obj;
        }
        Map<String, Object> map = this.eventProperties;
        ArrayList arrayList = new ArrayList(map.size());
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            arrayList.add(TuplesKt.to(Utils.getNormalizedName(entry.getKey()), entry.getValue()));
        }
        return MapsKt.toMap(arrayList).get(Utils.getNormalizedName(propertyName));
    }
}
