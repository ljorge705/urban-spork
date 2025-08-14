package com.onfido.android.sdk.capture.utils;

import androidx.fragment.app.Fragment;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000¨\u0006\u0003"}, d2 = {"isAttached", "", "Landroidx/fragment/app/Fragment;", "onfido-capture-sdk-core_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FragmentExtentionsKt {
    public static final boolean isAttached(Fragment fragment) {
        Intrinsics.checkNotNullParameter(fragment, "<this>");
        return fragment.isAdded() && fragment.getContext() != null;
    }
}
