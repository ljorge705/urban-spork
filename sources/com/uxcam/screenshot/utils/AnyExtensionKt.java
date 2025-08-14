package com.uxcam.screenshot.utils;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0002\n\u0000¨\u0006\u0000"}, d2 = {"screenshot_littleRelease"}, k = 2, mv = {1, 8, 0})
/* loaded from: classes6.dex */
public final class AnyExtensionKt {
    public static final void a(Object obj) {
        Intrinsics.checkNotNullParameter(obj, "<this>");
        if (obj.getClass().isAnonymousClass()) {
            String name = obj.getClass().getName();
            if (name.length() > 23) {
                Intrinsics.checkNotNullExpressionValue(name, "name");
                name = name.substring(name.length() - 23, name.length());
                Intrinsics.checkNotNullExpressionValue(name, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            Intrinsics.checkNotNullExpressionValue(name, "{\n            val name =…3, name.length)\n        }");
            return;
        }
        String name2 = obj.getClass().getSimpleName();
        if (name2.length() > 23) {
            Intrinsics.checkNotNullExpressionValue(name2, "name");
            name2 = name2.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(name2, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        Intrinsics.checkNotNullExpressionValue(name2, "{\n            val name =…ubstring(0, 23)\n        }");
    }
}
