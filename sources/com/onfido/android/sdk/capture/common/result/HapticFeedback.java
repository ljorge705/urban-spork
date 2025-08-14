package com.onfido.android.sdk.capture.common.result;

import android.os.Build;
import android.view.View;
import com.onfido.javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0007\b\u0007¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\t"}, d2 = {"Lcom/onfido/android/sdk/capture/common/result/HapticFeedback;", "", "()V", "performError", "", "view", "Landroid/view/View;", "performSuccess", "performTap", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class HapticFeedback {
    @Inject
    public HapticFeedback() {
    }

    public final void performError(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.performHapticFeedback(Build.VERSION.SDK_INT >= 30 ? 17 : 0, 2);
    }

    public final void performSuccess(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.performHapticFeedback(Build.VERSION.SDK_INT >= 30 ? 16 : 0, 2);
    }

    public final void performTap(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.performHapticFeedback(3, 2);
    }
}
