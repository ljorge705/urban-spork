package com.onfido.android.sdk.capture.utils;

import android.graphics.Matrix;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0000¨\u0006\u0006"}, d2 = {"postHorizontalFlip", "", "Landroid/graphics/Matrix;", "width", "", "height", "onfido-capture-sdk-core_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class MatrixExtensionsKt {
    public static final void postHorizontalFlip(Matrix matrix, int i, int i2) {
        Intrinsics.checkNotNullParameter(matrix, "<this>");
        matrix.postScale(-1.0f, 1.0f, i * 0.5f, i2 * 0.5f);
    }
}
