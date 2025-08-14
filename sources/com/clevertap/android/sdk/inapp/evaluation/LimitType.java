package com.clevertap.android.sdk.inapp.evaluation;

import io.sentry.cache.EnvelopeCache;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LimitAdapter.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000e\b\u0086\u0001\u0018\u0000 \u00102\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0010B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000f¨\u0006\u0011"}, d2 = {"Lcom/clevertap/android/sdk/inapp/evaluation/LimitType;", "", "type", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getType", "()Ljava/lang/String;", "Ever", "Session", "Seconds", "Minutes", "Hours", "Days", "Weeks", "OnEvery", "OnExactly", "Companion", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public enum LimitType {
    Ever("ever"),
    Session(EnvelopeCache.PREFIX_CURRENT_SESSION_FILE),
    Seconds("seconds"),
    Minutes("minutes"),
    Hours("hours"),
    Days("days"),
    Weeks("weeks"),
    OnEvery("onEvery"),
    OnExactly("onExactly");


    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String type;

    public final String getType() {
        return this.type;
    }

    LimitType(String str) {
        this.type = str;
    }

    /* compiled from: LimitAdapter.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/clevertap/android/sdk/inapp/evaluation/LimitType$Companion;", "", "()V", "fromString", "Lcom/clevertap/android/sdk/inapp/evaluation/LimitType;", "type", "", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final LimitType fromString(String type) {
            LimitType limitType;
            Intrinsics.checkNotNullParameter(type, "type");
            LimitType[] limitTypeArrValues = LimitType.values();
            int length = limitTypeArrValues.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    limitType = null;
                    break;
                }
                limitType = limitTypeArrValues[i];
                if (Intrinsics.areEqual(limitType.getType(), type)) {
                    break;
                }
                i++;
            }
            return limitType == null ? LimitType.Ever : limitType;
        }
    }
}
