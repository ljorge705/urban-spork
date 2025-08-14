package io.sentry.android.replay;

import android.view.View;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ViewExtensions.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0003\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0004"}, d2 = {"sentryReplayMask", "", "Landroid/view/View;", "sentryReplayUnmask", "sentry-android-replay_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class ViewExtensionsKt {
    public static final void sentryReplayMask(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.setTag(R.id.sentry_privacy, "mask");
    }

    public static final void sentryReplayUnmask(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.setTag(R.id.sentry_privacy, "unmask");
    }
}
