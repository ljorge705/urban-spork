package com.onfido.workflow.internal.utils;

import com.onfido.android.sdk.capture.internal.navigation.Navigator;
import com.onfido.workflow.internal.ui.LoadingScreen;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NavigatorExt.kt */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000¨\u0006\u0003"}, d2 = {"backToWorkflowRoot", "", "Lcom/onfido/android/sdk/capture/internal/navigation/Navigator;", "onfido-workflow_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class NavigatorExtKt {
    public static final void backToWorkflowRoot(Navigator navigator) {
        Intrinsics.checkNotNullParameter(navigator, "<this>");
        navigator.backTo(LoadingScreen.INSTANCE);
    }
}
