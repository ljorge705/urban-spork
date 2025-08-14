package com.onfido.android.sdk.capture.utils;

import androidx.exifinterface.media.ExifInterface;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\u0010\u0011\n\u0002\u0010\u0013\n\u0002\b\u0002\u001a#\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0004\u001a\u0002H\u0002¢\u0006\u0002\u0010\u0005\u001a\u001b\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0003*\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\u0010\n¨\u0006\u000b"}, d2 = {"hasDuplicate", "", ExifInterface.GPS_DIRECTION_TRUE, "", "possibleDuplicateValue", "(Ljava/util/List;Ljava/lang/Object;)Z", "twoDArrayToList", "", "", "", "([[D)Ljava/util/List;", "onfido-capture-sdk-core_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ListExtensionsKt {
    public static final <T> boolean hasDuplicate(List<? extends T> list, T t) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        int iIndexOf = list.indexOf(t);
        return (iIndexOf == -1 || iIndexOf == list.lastIndexOf(t)) ? false : true;
    }

    public static final List<Double> twoDArrayToList(double[][] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        ArrayList arrayList = new ArrayList();
        for (double[] dArr2 : dArr) {
            arrayList.addAll(ArraysKt.toList(dArr2));
        }
        return arrayList;
    }
}
