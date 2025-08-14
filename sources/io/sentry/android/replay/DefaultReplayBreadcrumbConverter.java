package io.sentry.android.replay;

import io.sentry.Breadcrumb;
import io.sentry.ReplayBreadcrumbConverter;
import io.sentry.SpanDataConvention;
import io.sentry.protocol.Response;
import io.sentry.rrweb.RRWebBreadcrumbEvent;
import io.sentry.rrweb.RRWebSpanEvent;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

/* compiled from: DefaultReplayBreadcrumbConverter.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0017\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\f\u0010\t\u001a\u00020\n*\u00020\bH\u0002J\f\u0010\u000b\u001a\u00020\u0004*\u00020\u0004H\u0002J\f\u0010\f\u001a\u00020\r*\u00020\bH\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lio/sentry/android/replay/DefaultReplayBreadcrumbConverter;", "Lio/sentry/ReplayBreadcrumbConverter;", "()V", "lastConnectivityState", "", "convert", "Lio/sentry/rrweb/RRWebEvent;", RRWebBreadcrumbEvent.EVENT_TAG, "Lio/sentry/Breadcrumb;", "isValidForRRWebSpan", "", "snakeToCamelCase", "toRRWebSpanEvent", "Lio/sentry/rrweb/RRWebSpanEvent;", "Companion", "sentry-android-replay_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes6.dex */
public class DefaultReplayBreadcrumbConverter implements ReplayBreadcrumbConverter {
    private static final HashSet<String> supportedNetworkData;
    private String lastConnectivityState;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    private static final Lazy<Regex> snakecasePattern$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<Regex>() { // from class: io.sentry.android.replay.DefaultReplayBreadcrumbConverter$Companion$snakecasePattern$2
        @Override // kotlin.jvm.functions.Function0
        public final Regex invoke() {
            return new Regex("_[a-z]");
        }
    });

    /* compiled from: DefaultReplayBreadcrumbConverter.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u001e\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\nj\b\u0012\u0004\u0012\u00020\u000b`\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lio/sentry/android/replay/DefaultReplayBreadcrumbConverter$Companion;", "", "()V", "snakecasePattern", "Lkotlin/text/Regex;", "getSnakecasePattern", "()Lkotlin/text/Regex;", "snakecasePattern$delegate", "Lkotlin/Lazy;", "supportedNetworkData", "Ljava/util/HashSet;", "", "Lkotlin/collections/HashSet;", "sentry-android-replay_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Regex getSnakecasePattern() {
            return (Regex) DefaultReplayBreadcrumbConverter.snakecasePattern$delegate.getValue();
        }
    }

    static {
        HashSet<String> hashSet = new HashSet<>();
        hashSet.add(Response.JsonKeys.STATUS_CODE);
        hashSet.add("method");
        hashSet.add("response_content_length");
        hashSet.add("request_content_length");
        hashSet.add(SpanDataConvention.HTTP_RESPONSE_CONTENT_LENGTH_KEY);
        hashSet.add("http.request_content_length");
        supportedNetworkData = hashSet;
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0238  */
    /* JADX WARN: Removed duplicated region for block: B:112:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00f2 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00f3  */
    @Override // io.sentry.ReplayBreadcrumbConverter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public io.sentry.rrweb.RRWebEvent convert(io.sentry.Breadcrumb r10) {
        /*
            Method dump skipped, instructions count: 629
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.android.replay.DefaultReplayBreadcrumbConverter.convert(io.sentry.Breadcrumb):io.sentry.rrweb.RRWebEvent");
    }

    private final boolean isValidForRRWebSpan(Breadcrumb breadcrumb) {
        Object obj = breadcrumb.getData().get("url");
        String str = obj instanceof String ? (String) obj : null;
        if (str != null && str.length() != 0) {
            Map<String, Object> data = breadcrumb.getData();
            Intrinsics.checkNotNullExpressionValue(data, "data");
            if (data.containsKey(SpanDataConvention.HTTP_START_TIMESTAMP)) {
                Map<String, Object> data2 = breadcrumb.getData();
                Intrinsics.checkNotNullExpressionValue(data2, "data");
                if (data2.containsKey(SpanDataConvention.HTTP_END_TIMESTAMP)) {
                    return true;
                }
            }
        }
        return false;
    }

    private final String snakeToCamelCase(String str) {
        return INSTANCE.getSnakecasePattern().replace(str, new Function1<MatchResult, CharSequence>() { // from class: io.sentry.android.replay.DefaultReplayBreadcrumbConverter.snakeToCamelCase.1
            @Override // kotlin.jvm.functions.Function1
            public final CharSequence invoke(MatchResult it) {
                Intrinsics.checkNotNullParameter(it, "it");
                String strValueOf = String.valueOf(StringsKt.last(it.getValue()));
                Intrinsics.checkNotNull(strValueOf, "null cannot be cast to non-null type java.lang.String");
                String upperCase = strValueOf.toUpperCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase(Locale.ROOT)");
                return upperCase;
            }
        });
    }

    private final RRWebSpanEvent toRRWebSpanEvent(Breadcrumb breadcrumb) {
        double dLongValue;
        double dLongValue2;
        Object obj = breadcrumb.getData().get(SpanDataConvention.HTTP_START_TIMESTAMP);
        Object obj2 = breadcrumb.getData().get(SpanDataConvention.HTTP_END_TIMESTAMP);
        RRWebSpanEvent rRWebSpanEvent = new RRWebSpanEvent();
        rRWebSpanEvent.setTimestamp(breadcrumb.getTimestamp().getTime());
        rRWebSpanEvent.setOp("resource.http");
        Object obj3 = breadcrumb.getData().get("url");
        Intrinsics.checkNotNull(obj3, "null cannot be cast to non-null type kotlin.String");
        rRWebSpanEvent.setDescription((String) obj3);
        if (obj instanceof Double) {
            dLongValue = ((Number) obj).doubleValue();
        } else {
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Long");
            dLongValue = ((Long) obj).longValue();
        }
        rRWebSpanEvent.setStartTimestamp(dLongValue / 1000.0d);
        if (obj2 instanceof Double) {
            dLongValue2 = ((Number) obj2).doubleValue();
        } else {
            Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.Long");
            dLongValue2 = ((Long) obj2).longValue();
        }
        rRWebSpanEvent.setEndTimestamp(dLongValue2 / 1000.0d);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Map<String, Object> data = breadcrumb.getData();
        Intrinsics.checkNotNullExpressionValue(data, "breadcrumb.data");
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (supportedNetworkData.contains(key)) {
                Intrinsics.checkNotNullExpressionValue(key, "key");
                linkedHashMap.put(snakeToCamelCase(StringsKt.substringAfter$default(StringsKt.replace$default(key, "content_length", "body_size", false, 4, (Object) null), ".", (String) null, 2, (Object) null)), value);
            }
        }
        rRWebSpanEvent.setData(linkedHashMap);
        return rRWebSpanEvent;
    }
}
