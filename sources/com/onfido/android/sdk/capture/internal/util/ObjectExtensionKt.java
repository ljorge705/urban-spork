package com.onfido.android.sdk.capture.internal.util;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001a\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a<\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u0002H\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0017\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\b\u0007H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\b\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\t"}, d2 = {"applyIf", ExifInterface.GPS_DIRECTION_TRUE, "condition", "", "block", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/Object;ZLkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "onfido-capture-sdk-core_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ObjectExtensionKt {
    public static final <T> T applyIf(T t, boolean z, Function1<? super T, Unit> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        if (z) {
            block.invoke(t);
        }
        return t;
    }
}
