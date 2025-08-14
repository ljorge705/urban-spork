package io.sentry.android.replay;

import androidx.compose.ui.semantics.SemanticsPropertyKey;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ModifierExtensions.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lio/sentry/android/replay/SentryReplayModifiers;", "", "()V", "SentryPrivacy", "Landroidx/compose/ui/semantics/SemanticsPropertyKey;", "", "getSentryPrivacy", "()Landroidx/compose/ui/semantics/SemanticsPropertyKey;", "sentry-android-replay_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class SentryReplayModifiers {
    public static final SentryReplayModifiers INSTANCE = new SentryReplayModifiers();
    private static final SemanticsPropertyKey<String> SentryPrivacy = new SemanticsPropertyKey<>("SentryPrivacy", new Function2<String, String, String>() { // from class: io.sentry.android.replay.SentryReplayModifiers$SentryPrivacy$1
        @Override // kotlin.jvm.functions.Function2
        public final String invoke(String str, String str2) {
            Intrinsics.checkNotNullParameter(str2, "<anonymous parameter 1>");
            return str;
        }
    });
    public static final int $stable = SemanticsPropertyKey.$stable;

    public final SemanticsPropertyKey<String> getSentryPrivacy() {
        return SentryPrivacy;
    }

    private SentryReplayModifiers() {
    }
}
