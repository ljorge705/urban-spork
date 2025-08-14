package io.sentry.android.replay.util;

import io.sentry.util.Random;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Sampling.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\u001a\u001b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0000¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"sample", "", "Lio/sentry/util/Random;", "rate", "", "(Lio/sentry/util/Random;Ljava/lang/Double;)Z", "sentry-android-replay_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class SamplingKt {
    public static final boolean sample(Random random, Double d) {
        Intrinsics.checkNotNullParameter(random, "<this>");
        return d != null && d.doubleValue() >= random.nextDouble();
    }
}
