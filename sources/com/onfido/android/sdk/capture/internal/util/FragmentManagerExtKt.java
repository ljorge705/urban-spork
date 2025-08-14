package com.onfido.android.sdk.capture.internal.util;

import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.LifecycleOwner;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aG\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0012\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00070\u0006\"\u00020\u00072\u0018\u0010\b\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010\tH\u0007¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"setFragmentResultsListeners", "", "Landroidx/fragment/app/FragmentManager;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "requestKeys", "", "", "callback", "Lkotlin/Function2;", "Landroid/os/Bundle;", "(Landroidx/fragment/app/FragmentManager;Landroidx/lifecycle/LifecycleOwner;[Ljava/lang/String;Lkotlin/jvm/functions/Function2;)V", "onfido-capture-sdk-core_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FragmentManagerExtKt {
    public static final void setFragmentResultsListeners(FragmentManager fragmentManager, LifecycleOwner lifecycleOwner, String[] requestKeys, final Function2<? super String, ? super Bundle, Unit> callback) {
        Intrinsics.checkNotNullParameter(fragmentManager, "<this>");
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        Intrinsics.checkNotNullParameter(requestKeys, "requestKeys");
        Intrinsics.checkNotNullParameter(callback, "callback");
        for (String str : requestKeys) {
            fragmentManager.setFragmentResultListener(str, lifecycleOwner, new FragmentResultListener() { // from class: com.onfido.android.sdk.capture.internal.util.FragmentManagerExtKt$$ExternalSyntheticLambda0
                @Override // androidx.fragment.app.FragmentResultListener
                public final void onFragmentResult(String str2, Bundle bundle) {
                    callback.invoke(str2, bundle);
                }
            });
        }
    }
}
