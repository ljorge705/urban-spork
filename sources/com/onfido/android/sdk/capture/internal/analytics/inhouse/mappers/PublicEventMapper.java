package com.onfido.android.sdk.capture.internal.analytics.inhouse.mappers;

import com.onfido.android.sdk.capture.analytics.OnfidoAnalyticsEvent;
import com.onfido.android.sdk.capture.analytics.OnfidoAnalyticsEventType;
import com.onfido.android.sdk.capture.analytics.OnfidoAnalyticsPropertyKey;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.AnalyticsEvent;
import com.onfido.javax.inject.Inject;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0007\b\u0007¢\u0006\u0002\u0010\u0002J \u0010\u0007\u001a\u00020\b2\u0018\u0010\u0003\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\n0\tJ\u0010\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000eR\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/mappers/PublicEventMapper;", "", "()V", "mappers", "", "", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/mappers/PublicAnalyticsEventMapper;", "addMappers", "", "", "Lkotlin/Pair;", "map", "Lcom/onfido/android/sdk/capture/analytics/OnfidoAnalyticsEvent;", "event", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/AnalyticsEvent;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class PublicEventMapper {
    private final Map<String, PublicAnalyticsEventMapper> mappers = new LinkedHashMap();

    @Inject
    public PublicEventMapper() {
    }

    public final void addMappers(List<? extends Pair<String, ? extends PublicAnalyticsEventMapper>> mappers) {
        Intrinsics.checkNotNullParameter(mappers, "mappers");
        Iterator<T> it = mappers.iterator();
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            String str = (String) pair.component1();
            PublicAnalyticsEventMapper publicAnalyticsEventMapper = (PublicAnalyticsEventMapper) pair.component2();
            if (this.mappers.get(str) != null) {
                throw new IllegalArgumentException("An existing analytics mapper exists for " + str + " event. Overriding mappers is not allowed");
            }
            this.mappers.put(str, publicAnalyticsEventMapper);
        }
    }

    public final OnfidoAnalyticsEvent map(AnalyticsEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        OnfidoAnalyticsEventType publicType = event.getEvent().getPublicType();
        if (publicType == null) {
            return null;
        }
        PublicAnalyticsEventMapper publicAnalyticsEventMapper = this.mappers.get(event.getEvent().getName());
        if (publicAnalyticsEventMapper != null) {
            return publicAnalyticsEventMapper.map(event);
        }
        String publicName = event.getEvent().getPublicName();
        if (publicName != null) {
            return new OnfidoAnalyticsEvent(publicType, MapsKt.mapOf(TuplesKt.to(OnfidoAnalyticsPropertyKey.SCREEN_NAME, publicName)));
        }
        return null;
    }
}
