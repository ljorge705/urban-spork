package io.sentry.android.replay;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ModifierExtensions.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0001\u001a\n\u0010\u0002\u001a\u00020\u0001*\u00020\u0001¨\u0006\u0003"}, d2 = {"sentryReplayMask", "Landroidx/compose/ui/Modifier;", "sentryReplayUnmask", "sentry-android-replay_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class ModifierExtensionsKt {
    public static final Modifier sentryReplayMask(Modifier modifier) {
        Intrinsics.checkNotNullParameter(modifier, "<this>");
        return SemanticsModifierKt.semantics$default(modifier, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: io.sentry.android.replay.ModifierExtensionsKt.sentryReplayMask.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                invoke2(semanticsPropertyReceiver);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SemanticsPropertyReceiver semantics) {
                Intrinsics.checkNotNullParameter(semantics, "$this$semantics");
                semantics.set(SentryReplayModifiers.INSTANCE.getSentryPrivacy(), "mask");
            }
        }, 1, null);
    }

    public static final Modifier sentryReplayUnmask(Modifier modifier) {
        Intrinsics.checkNotNullParameter(modifier, "<this>");
        return SemanticsModifierKt.semantics$default(modifier, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: io.sentry.android.replay.ModifierExtensionsKt.sentryReplayUnmask.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                invoke2(semanticsPropertyReceiver);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SemanticsPropertyReceiver semantics) {
                Intrinsics.checkNotNullParameter(semantics, "$this$semantics");
                semantics.set(SentryReplayModifiers.INSTANCE.getSentryPrivacy(), "unmask");
            }
        }, 1, null);
    }
}
