package com.clevertap.android.sdk.utils;

import com.clevertap.android.sdk.utils.Clock;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;

/* compiled from: Clock.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H\u0016J\b\u0010\u0005\u001a\u00020\u0006H&¨\u0006\b"}, d2 = {"Lcom/clevertap/android/sdk/utils/Clock;", "", "currentTimeMillis", "", "currentTimeSeconds", "newDate", "Ljava/util/Date;", "Companion", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public interface Clock {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    long currentTimeMillis();

    long currentTimeSeconds();

    Date newDate();

    /* compiled from: Clock.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    public static final class DefaultImpls {
        public static long currentTimeSeconds(Clock clock) {
            return TimeUnit.MILLISECONDS.toSeconds(clock.currentTimeMillis());
        }
    }

    /* compiled from: Clock.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/clevertap/android/sdk/utils/Clock$Companion;", "", "()V", "SYSTEM", "Lcom/clevertap/android/sdk/utils/Clock;", "getSYSTEM", "()Lcom/clevertap/android/sdk/utils/Clock;", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final Clock SYSTEM = new Clock() { // from class: com.clevertap.android.sdk.utils.Clock$Companion$SYSTEM$1
            @Override // com.clevertap.android.sdk.utils.Clock
            public long currentTimeSeconds() {
                return Clock.DefaultImpls.currentTimeSeconds(this);
            }

            @Override // com.clevertap.android.sdk.utils.Clock
            public long currentTimeMillis() {
                return System.currentTimeMillis();
            }

            @Override // com.clevertap.android.sdk.utils.Clock
            public Date newDate() {
                return new Date();
            }
        };

        public final Clock getSYSTEM() {
            return SYSTEM;
        }

        private Companion() {
        }
    }
}
