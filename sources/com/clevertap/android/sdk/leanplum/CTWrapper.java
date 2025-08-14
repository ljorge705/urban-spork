package com.clevertap.android.sdk.leanplum;

import com.clevertap.android.sdk.CleverTapAPI;
import com.clevertap.android.sdk.Logger;
import io.sentry.protocol.Message;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CTWrapper.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010&\n\u0002\b\b\n\u0002\u0010\u0006\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J2\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\b2\u0016\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u000bJ \u0010\f\u001a\u0004\u0018\u00010\u00012\u0014\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000eH\u0002J\u001a\u0010\u000f\u001a\u00020\u00062\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u000bJ\u001e\u0010\u0010\u001a\u00020\u00062\u0016\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u000bJ\u0010\u0010\u0012\u001a\u00020\u00062\b\u0010\u0013\u001a\u0004\u0018\u00010\bJ:\u0010\u0014\u001a\u00020\u00062\b\u0010\u0015\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\t\u001a\u0004\u0018\u00010\b2\u0016\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u000bJV\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\b2\b\u0010\u0019\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u001a\u001a\u0004\u0018\u00010\b2\b\u0010\u001b\u001a\u0004\u0018\u00010\b2\b\u0010\u001c\u001a\u0004\u0018\u00010\b2\u0016\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u000bJ8\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u001a\u001a\u0004\u0018\u00010\b2\u0016\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/clevertap/android/sdk/leanplum/CTWrapper;", "", "ctProvider", "Lcom/clevertap/android/sdk/leanplum/CleverTapProvider;", "(Lcom/clevertap/android/sdk/leanplum/CleverTapProvider;)V", "advanceTo", "", "state", "", "info", Message.JsonKeys.PARAMS, "", "mapNotSupportedValues", "entry", "", "setTrafficSourceInfo", "setUserAttributes", "attributes", "setUserId", "userId", "track", "event", "value", "", "trackGooglePlayPurchase", Constants.IAP_ITEM_PARAM, Constants.CURRENCY_CODE_PARAM, "purchaseData", "dataSignature", "trackPurchase", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class CTWrapper {
    private final CleverTapProvider ctProvider;

    public CTWrapper(CleverTapProvider ctProvider) {
        Intrinsics.checkNotNullParameter(ctProvider, "ctProvider");
        this.ctProvider = ctProvider;
    }

    public final void setUserId(String userId) {
        String str = userId;
        if (str == null || str.length() == 0) {
            return;
        }
        Map<String, Object> mapMapOf = MapsKt.mapOf(TuplesKt.to("Identity", userId));
        Logger.d("CTWrapper", "setUserId will call onUserLogin with " + mapMapOf);
        CleverTapAPI cleverTap = this.ctProvider.getCleverTap();
        if (cleverTap != null) {
            cleverTap.onUserLogin(mapMapOf);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x004c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void advanceTo(java.lang.String r4, java.lang.String r5, java.util.Map<java.lang.String, ? extends java.lang.Object> r6) {
        /*
            r3 = this;
            if (r4 != 0) goto L3
            return
        L3:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "state_"
            r0.<init>(r1)
            java.lang.StringBuilder r4 = r0.append(r4)
            java.lang.String r4 = r4.toString()
            if (r6 == 0) goto L4c
            java.util.LinkedHashMap r0 = new java.util.LinkedHashMap
            int r1 = r6.size()
            int r1 = kotlin.collections.MapsKt.mapCapacity(r1)
            r0.<init>(r1)
            java.util.Map r0 = (java.util.Map) r0
            java.util.Set r6 = r6.entrySet()
            java.lang.Iterable r6 = (java.lang.Iterable) r6
            java.util.Iterator r6 = r6.iterator()
        L2e:
            boolean r1 = r6.hasNext()
            if (r1 == 0) goto L46
            java.lang.Object r1 = r6.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            java.lang.Object r2 = r1.getKey()
            java.lang.Object r1 = r3.mapNotSupportedValues(r1)
            r0.put(r2, r1)
            goto L2e
        L46:
            java.util.Map r6 = kotlin.collections.MapsKt.toMutableMap(r0)
            if (r6 != 0) goto L53
        L4c:
            java.util.LinkedHashMap r6 = new java.util.LinkedHashMap
            r6.<init>()
            java.util.Map r6 = (java.util.Map) r6
        L53:
            if (r5 == 0) goto L5a
            java.lang.String r0 = "info"
            r6.put(r0, r5)
        L5a:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r0 = "advance(...) will call pushEvent with "
            r5.<init>(r0)
            java.lang.StringBuilder r5 = r5.append(r4)
            java.lang.String r0 = " and "
            java.lang.StringBuilder r5 = r5.append(r0)
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r5 = r5.toString()
            java.lang.String r0 = "CTWrapper"
            com.clevertap.android.sdk.Logger.d(r0, r5)
            com.clevertap.android.sdk.leanplum.CleverTapProvider r5 = r3.ctProvider
            com.clevertap.android.sdk.CleverTapAPI r5 = r5.getCleverTap()
            if (r5 == 0) goto L83
            r5.pushEvent(r4, r6)
        L83:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.clevertap.android.sdk.leanplum.CTWrapper.advanceTo(java.lang.String, java.lang.String, java.util.Map):void");
    }

    public final void setUserAttributes(Map<String, ? extends Object> attributes) {
        if (attributes == null || attributes.isEmpty()) {
            return;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<String, ? extends Object> entry : attributes.entrySet()) {
            if (entry.getValue() != null) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        LinkedHashMap linkedHashMap2 = linkedHashMap;
        LinkedHashMap linkedHashMap3 = new LinkedHashMap(MapsKt.mapCapacity(linkedHashMap2.size()));
        for (Map.Entry<String, ? extends Object> entry2 : linkedHashMap2.entrySet()) {
            linkedHashMap3.put(entry2.getKey(), mapNotSupportedValues(entry2));
        }
        Logger.d("CTWrapper", "setUserAttributes will call pushProfile with " + linkedHashMap3);
        CleverTapAPI cleverTap = this.ctProvider.getCleverTap();
        if (cleverTap != null) {
            cleverTap.pushProfile(linkedHashMap3);
        }
        LinkedHashMap linkedHashMap4 = new LinkedHashMap();
        for (Map.Entry<String, ? extends Object> entry3 : attributes.entrySet()) {
            if (entry3.getValue() == null) {
                linkedHashMap4.put(entry3.getKey(), entry3.getValue());
            }
        }
        for (Map.Entry entry4 : linkedHashMap4.entrySet()) {
            Logger.d("CTWrapper", "setUserAttributes will call removeValueForKey with " + ((String) entry4.getKey()));
            CleverTapAPI cleverTap2 = this.ctProvider.getCleverTap();
            if (cleverTap2 != null) {
                cleverTap2.removeValueForKey((String) entry4.getKey());
            }
        }
    }

    private final Object mapNotSupportedValues(Map.Entry<String, ? extends Object> entry) {
        Object value = entry.getValue();
        if (value instanceof Iterable) {
            return CollectionsKt.joinToString$default(CollectionsKt.filterNotNull((Iterable) value), com.clevertap.android.sdk.Constants.SEPARATOR_COMMA, "[", "]", 0, null, null, 56, null);
        }
        return value instanceof Byte ? Integer.valueOf(((Number) value).byteValue()) : value instanceof Short ? Integer.valueOf(((Number) value).shortValue()) : value;
    }

    public final void setTrafficSourceInfo(Map<String, String> info) {
        Intrinsics.checkNotNullParameter(info, "info");
        String str = info.get("publisherName");
        String str2 = info.get("publisherSubPublisher");
        String str3 = info.get("publisherSubCampaign");
        Logger.d("CTWrapper", "setTrafficSourceInfo will call pushInstallReferrer with " + str + ", " + str2 + ", and " + str3);
        CleverTapAPI cleverTap = this.ctProvider.getCleverTap();
        if (cleverTap != null) {
            cleverTap.pushInstallReferrer(str, str2, str3);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x003c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void track(java.lang.String r4, double r5, java.lang.String r7, java.util.Map<java.lang.String, ? extends java.lang.Object> r8) {
        /*
            r3 = this;
            if (r4 != 0) goto L3
            return
        L3:
            if (r8 == 0) goto L3c
            java.util.LinkedHashMap r0 = new java.util.LinkedHashMap
            int r1 = r8.size()
            int r1 = kotlin.collections.MapsKt.mapCapacity(r1)
            r0.<init>(r1)
            java.util.Map r0 = (java.util.Map) r0
            java.util.Set r8 = r8.entrySet()
            java.lang.Iterable r8 = (java.lang.Iterable) r8
            java.util.Iterator r8 = r8.iterator()
        L1e:
            boolean r1 = r8.hasNext()
            if (r1 == 0) goto L36
            java.lang.Object r1 = r8.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            java.lang.Object r2 = r1.getKey()
            java.lang.Object r1 = r3.mapNotSupportedValues(r1)
            r0.put(r2, r1)
            goto L1e
        L36:
            java.util.Map r8 = kotlin.collections.MapsKt.toMutableMap(r0)
            if (r8 != 0) goto L43
        L3c:
            java.util.LinkedHashMap r8 = new java.util.LinkedHashMap
            r8.<init>()
            java.util.Map r8 = (java.util.Map) r8
        L43:
            java.lang.Double r5 = java.lang.Double.valueOf(r5)
            java.lang.String r6 = "value"
            r8.put(r6, r5)
            if (r7 == 0) goto L54
            java.lang.String r5 = "info"
            r8.put(r5, r7)
        L54:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = "track(...) will call pushEvent with "
            r5.<init>(r6)
            java.lang.StringBuilder r5 = r5.append(r4)
            java.lang.String r6 = " and "
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.StringBuilder r5 = r5.append(r8)
            java.lang.String r5 = r5.toString()
            java.lang.String r6 = "CTWrapper"
            com.clevertap.android.sdk.Logger.d(r6, r5)
            com.clevertap.android.sdk.leanplum.CleverTapProvider r5 = r3.ctProvider
            com.clevertap.android.sdk.CleverTapAPI r5 = r5.getCleverTap()
            if (r5 == 0) goto L7e
            r5.pushEvent(r4, r8)
        L7e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.clevertap.android.sdk.leanplum.CTWrapper.track(java.lang.String, double, java.lang.String, java.util.Map):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x003e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void trackPurchase(java.lang.String r5, double r6, java.lang.String r8, java.util.Map<java.lang.String, ? extends java.lang.Object> r9) {
        /*
            r4 = this;
            java.lang.String r0 = "event"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            if (r9 == 0) goto L3e
            java.util.LinkedHashMap r1 = new java.util.LinkedHashMap
            int r2 = r9.size()
            int r2 = kotlin.collections.MapsKt.mapCapacity(r2)
            r1.<init>(r2)
            java.util.Map r1 = (java.util.Map) r1
            java.util.Set r9 = r9.entrySet()
            java.lang.Iterable r9 = (java.lang.Iterable) r9
            java.util.Iterator r9 = r9.iterator()
        L20:
            boolean r2 = r9.hasNext()
            if (r2 == 0) goto L38
            java.lang.Object r2 = r9.next()
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2
            java.lang.Object r3 = r2.getKey()
            java.lang.Object r2 = r4.mapNotSupportedValues(r2)
            r1.put(r3, r2)
            goto L20
        L38:
            java.util.Map r9 = kotlin.collections.MapsKt.toMutableMap(r1)
            if (r9 != 0) goto L45
        L3e:
            java.util.LinkedHashMap r9 = new java.util.LinkedHashMap
            r9.<init>()
            java.util.Map r9 = (java.util.Map) r9
        L45:
            java.util.HashMap r1 = new java.util.HashMap
            r1.<init>(r9)
            r9 = r1
            java.util.Map r9 = (java.util.Map) r9
            r9.put(r0, r5)
            java.lang.Double r5 = java.lang.Double.valueOf(r6)
            java.lang.String r6 = "value"
            r9.put(r6, r5)
            if (r8 == 0) goto L61
            java.lang.String r5 = "currencyCode"
            r9.put(r5, r8)
        L61:
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = "trackPurchase will call pushChargedEvent with "
            r6.<init>(r7)
            java.lang.StringBuilder r6 = r6.append(r1)
            java.lang.String r7 = " and "
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.StringBuilder r6 = r6.append(r5)
            java.lang.String r6 = r6.toString()
            java.lang.String r7 = "CTWrapper"
            com.clevertap.android.sdk.Logger.d(r7, r6)
            com.clevertap.android.sdk.leanplum.CleverTapProvider r6 = r4.ctProvider
            com.clevertap.android.sdk.CleverTapAPI r6 = r6.getCleverTap()
            if (r6 == 0) goto L90
            r6.pushChargedEvent(r1, r5)
        L90:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.clevertap.android.sdk.leanplum.CTWrapper.trackPurchase(java.lang.String, double, java.lang.String, java.util.Map):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x003e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void trackGooglePlayPurchase(java.lang.String r5, java.lang.String r6, double r7, java.lang.String r9, java.lang.String r10, java.lang.String r11, java.util.Map<java.lang.String, ? extends java.lang.Object> r12) {
        /*
            r4 = this;
            java.lang.String r0 = "event"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            if (r12 == 0) goto L3e
            java.util.LinkedHashMap r1 = new java.util.LinkedHashMap
            int r2 = r12.size()
            int r2 = kotlin.collections.MapsKt.mapCapacity(r2)
            r1.<init>(r2)
            java.util.Map r1 = (java.util.Map) r1
            java.util.Set r12 = r12.entrySet()
            java.lang.Iterable r12 = (java.lang.Iterable) r12
            java.util.Iterator r12 = r12.iterator()
        L20:
            boolean r2 = r12.hasNext()
            if (r2 == 0) goto L38
            java.lang.Object r2 = r12.next()
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2
            java.lang.Object r3 = r2.getKey()
            java.lang.Object r2 = r4.mapNotSupportedValues(r2)
            r1.put(r3, r2)
            goto L20
        L38:
            java.util.Map r12 = kotlin.collections.MapsKt.toMutableMap(r1)
            if (r12 != 0) goto L45
        L3e:
            java.util.LinkedHashMap r12 = new java.util.LinkedHashMap
            r12.<init>()
            java.util.Map r12 = (java.util.Map) r12
        L45:
            java.util.HashMap r1 = new java.util.HashMap
            r1.<init>(r12)
            r12 = r1
            java.util.Map r12 = (java.util.Map) r12
            r12.put(r0, r5)
            java.lang.Double r5 = java.lang.Double.valueOf(r7)
            java.lang.String r7 = "value"
            r12.put(r7, r5)
            java.lang.String r5 = "currencyCode"
            r12.put(r5, r9)
            java.lang.String r5 = "googlePlayPurchaseData"
            r12.put(r5, r10)
            java.lang.String r5 = "googlePlayPurchaseDataSignature"
            r12.put(r5, r11)
            java.lang.String r5 = "item"
            r12.put(r5, r6)
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = "trackGooglePlayPurchase will call pushChargedEvent with "
            r6.<init>(r7)
            java.lang.StringBuilder r6 = r6.append(r1)
            java.lang.String r7 = " and "
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.StringBuilder r6 = r6.append(r5)
            java.lang.String r6 = r6.toString()
            java.lang.String r7 = "CTWrapper"
            com.clevertap.android.sdk.Logger.d(r7, r6)
            com.clevertap.android.sdk.leanplum.CleverTapProvider r6 = r4.ctProvider
            com.clevertap.android.sdk.CleverTapAPI r6 = r6.getCleverTap()
            if (r6 == 0) goto L9d
            r6.pushChargedEvent(r1, r5)
        L9d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.clevertap.android.sdk.leanplum.CTWrapper.trackGooglePlayPurchase(java.lang.String, java.lang.String, double, java.lang.String, java.lang.String, java.lang.String, java.util.Map):void");
    }
}
