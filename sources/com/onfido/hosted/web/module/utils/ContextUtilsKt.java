package com.onfido.hosted.web.module.utils;

import android.content.Context;
import androidx.core.view.ViewCompat;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u001a\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0001\u0010\u0003\u001a\u00020\u0004H\u0000¨\u0006\u0005"}, d2 = {"hexFrom", "", "Landroid/content/Context;", "colorAttributeResId", "", "onfido-capture-sdk-core_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class ContextUtilsKt {
    public static final String hexFrom(Context context, int i) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        int iColorFromAttr = com.onfido.android.sdk.capture.utils.ContextUtilsKt.colorFromAttr(context, i);
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str = String.format("#%06X", Arrays.copyOf(new Object[]{Integer.valueOf(iColorFromAttr & ViewCompat.MEASURED_SIZE_MASK)}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }
}
