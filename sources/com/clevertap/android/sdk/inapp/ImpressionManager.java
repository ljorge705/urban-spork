package com.clevertap.android.sdk.inapp;

import com.clevertap.android.sdk.db.Column;
import com.clevertap.android.sdk.inapp.store.preference.ImpressionStore;
import com.clevertap.android.sdk.inapp.store.preference.StoreRegistry;
import com.clevertap.android.sdk.utils.Clock;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ImpressionManager.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010!\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u000f\u0018\u00002\u00020\u0001B#\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010\u0010\u001a\u00020\u0011J\u0010\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u000bH\u0002J\u001d\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\rH\u0000¢\u0006\u0002\b\u0015J\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\r0\u00172\u0006\u0010\u0013\u001a\u00020\u000bJ\u0016\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u000fJ\u0016\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u001b\u001a\u00020\u000fJ\u0016\u0010\u001c\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u001d\u001a\u00020\u000fJ\u0016\u0010\u001e\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u001f\u001a\u00020\u000fJ\u000e\u0010 \u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u000bJ\u0006\u0010!\u001a\u00020\u000fJ\u0016\u0010\"\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u000b2\u0006\u0010#\u001a\u00020\u000fJ\u000e\u0010$\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u000bJ \u0010%\u001a\u00020\u00112\u0018\u0010\t\u001a\u0014\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\nR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\t\u001a\u0014\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lcom/clevertap/android/sdk/inapp/ImpressionManager;", "", "storeRegistry", "Lcom/clevertap/android/sdk/inapp/store/preference/StoreRegistry;", "clock", "Lcom/clevertap/android/sdk/utils/Clock;", "locale", "Ljava/util/Locale;", "(Lcom/clevertap/android/sdk/inapp/store/preference/StoreRegistry;Lcom/clevertap/android/sdk/utils/Clock;Ljava/util/Locale;)V", "sessionImpressions", "", "", "", "", "sessionImpressionsTotal", "", "clearSessionData", "", "getImpressionCount", Column.CAMPAIGN, "timestampStart", "getImpressionCount$clevertap_core_release", "getImpressions", "", "perDay", "days", "perHour", "hours", "perMinute", "minutes", "perSecond", "seconds", "perSession", "perSessionTotal", "perWeek", "weeks", "recordImpression", "setSessionImpressions", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class ImpressionManager {
    private final Clock clock;
    private final Locale locale;
    private Map<String, List<Long>> sessionImpressions;
    private int sessionImpressionsTotal;
    private final StoreRegistry storeRegistry;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ImpressionManager(StoreRegistry storeRegistry) {
        this(storeRegistry, null, null, 6, null);
        Intrinsics.checkNotNullParameter(storeRegistry, "storeRegistry");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ImpressionManager(StoreRegistry storeRegistry, Clock clock) {
        this(storeRegistry, clock, null, 4, null);
        Intrinsics.checkNotNullParameter(storeRegistry, "storeRegistry");
        Intrinsics.checkNotNullParameter(clock, "clock");
    }

    /* renamed from: perSessionTotal, reason: from getter */
    public final int getSessionImpressionsTotal() {
        return this.sessionImpressionsTotal;
    }

    public ImpressionManager(StoreRegistry storeRegistry, Clock clock, Locale locale) {
        Intrinsics.checkNotNullParameter(storeRegistry, "storeRegistry");
        Intrinsics.checkNotNullParameter(clock, "clock");
        Intrinsics.checkNotNullParameter(locale, "locale");
        this.storeRegistry = storeRegistry;
        this.clock = clock;
        this.locale = locale;
        this.sessionImpressions = new LinkedHashMap();
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ ImpressionManager(StoreRegistry storeRegistry, Clock clock, Locale locale, int i, DefaultConstructorMarker defaultConstructorMarker) {
        clock = (i & 2) != 0 ? Clock.INSTANCE.getSYSTEM() : clock;
        if ((i & 4) != 0) {
            locale = Locale.getDefault();
            Intrinsics.checkNotNullExpressionValue(locale, "getDefault()");
        }
        this(storeRegistry, clock, locale);
    }

    public final void setSessionImpressions(Map<String, List<Long>> sessionImpressions) {
        Intrinsics.checkNotNullParameter(sessionImpressions, "sessionImpressions");
        this.sessionImpressions.clear();
        this.sessionImpressions.putAll(sessionImpressions);
    }

    public final void recordImpression(String campaignId) {
        Intrinsics.checkNotNullParameter(campaignId, "campaignId");
        this.sessionImpressionsTotal++;
        long jCurrentTimeSeconds = this.clock.currentTimeSeconds();
        Map<String, List<Long>> map = this.sessionImpressions;
        ArrayList arrayList = map.get(campaignId);
        if (arrayList == null) {
            arrayList = new ArrayList();
            map.put(campaignId, arrayList);
        }
        arrayList.add(Long.valueOf(jCurrentTimeSeconds));
        ImpressionStore impressionStore = this.storeRegistry.getImpressionStore();
        if (impressionStore != null) {
            impressionStore.write(campaignId, jCurrentTimeSeconds);
        }
    }

    public final int perSession(String campaignId) {
        Intrinsics.checkNotNullParameter(campaignId, "campaignId");
        List<Long> list = this.sessionImpressions.get(campaignId);
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public final int perSecond(String campaignId, int seconds) {
        Intrinsics.checkNotNullParameter(campaignId, "campaignId");
        return getImpressionCount$clevertap_core_release(campaignId, this.clock.currentTimeSeconds() - seconds);
    }

    public final int perMinute(String campaignId, int minutes) {
        Intrinsics.checkNotNullParameter(campaignId, "campaignId");
        return getImpressionCount$clevertap_core_release(campaignId, this.clock.currentTimeSeconds() - TimeUnit.MINUTES.toSeconds(minutes));
    }

    public final int perHour(String campaignId, int hours) {
        Intrinsics.checkNotNullParameter(campaignId, "campaignId");
        return getImpressionCount$clevertap_core_release(campaignId, this.clock.currentTimeSeconds() - TimeUnit.HOURS.toSeconds(hours));
    }

    public final int perDay(String campaignId, int days) {
        Intrinsics.checkNotNullParameter(campaignId, "campaignId");
        Calendar calendar = Calendar.getInstance(this.locale);
        calendar.setTime(new Date());
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        calendar.add(6, -days);
        return getImpressionCount$clevertap_core_release(campaignId, TimeUnit.MILLISECONDS.toSeconds(calendar.getTime().getTime()));
    }

    public final int perWeek(String campaignId, int weeks) {
        Intrinsics.checkNotNullParameter(campaignId, "campaignId");
        Calendar calendar = Calendar.getInstance(this.locale);
        calendar.setTime(new Date());
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        calendar.add(6, -(((calendar.get(7) - calendar.getFirstDayOfWeek()) + 7) % 7));
        if (weeks > 1) {
            calendar.add(3, -weeks);
        }
        return getImpressionCount$clevertap_core_release(campaignId, TimeUnit.MILLISECONDS.toSeconds(calendar.getTimeInMillis()));
    }

    private final int getImpressionCount(String campaignId) {
        List<Long> list;
        ImpressionStore impressionStore = this.storeRegistry.getImpressionStore();
        if (impressionStore == null || (list = impressionStore.read(campaignId)) == null) {
            return 0;
        }
        return list.size();
    }

    public final int getImpressionCount$clevertap_core_release(String campaignId, long timestampStart) {
        Intrinsics.checkNotNullParameter(campaignId, "campaignId");
        List<Long> impressions = getImpressions(campaignId);
        int size = impressions.size() - 1;
        int i = 0;
        while (i <= size) {
            int i2 = (i + size) >>> 1;
            if (impressions.get(i2).longValue() < timestampStart) {
                i = i2 + 1;
            } else {
                size = i2 - 1;
            }
        }
        return impressions.size() - i;
    }

    public final List<Long> getImpressions(String campaignId) {
        List<Long> list;
        Intrinsics.checkNotNullParameter(campaignId, "campaignId");
        ImpressionStore impressionStore = this.storeRegistry.getImpressionStore();
        return (impressionStore == null || (list = impressionStore.read(campaignId)) == null) ? CollectionsKt.emptyList() : list;
    }

    public final void clearSessionData() {
        this.sessionImpressions.clear();
        this.sessionImpressionsTotal = 0;
    }
}
