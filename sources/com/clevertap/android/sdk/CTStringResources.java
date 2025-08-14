package com.clevertap.android.sdk;

import android.content.Context;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CTStringResources.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\u0010\u0004\u001a\u00020\u0005\"\u00020\u0006¢\u0006\u0002\u0010\u0007J\u000b\u0010\f\u001a\u0004\u0018\u00010\nH\u0086\u0002J\u000b\u0010\r\u001a\u0004\u0018\u00010\nH\u0086\u0002J\u000b\u0010\u000e\u001a\u0004\u0018\u00010\nH\u0086\u0002J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\nH\u0086\u0002J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\nH\u0086\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000b¨\u0006\u0011"}, d2 = {"Lcom/clevertap/android/sdk/CTStringResources;", "", "context", "Landroid/content/Context;", "sRID", "", "", "(Landroid/content/Context;[I)V", "sArray", "", "", "[Ljava/lang/String;", "component1", "component2", "component3", "component4", "component5", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class CTStringResources {
    private final Context context;
    private String[] sArray;

    public CTStringResources(Context context, int... sRID) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(sRID, "sRID");
        this.context = context;
        int length = sRID.length;
        String[] strArr = new String[length];
        for (int i = 0; i < length; i++) {
            String string = this.context.getString(sRID[i]);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(sRID[it])");
            strArr[i] = string;
        }
        this.sArray = strArr;
    }

    public final String component1() {
        return (String) ArraysKt.getOrNull(this.sArray, 0);
    }

    public final String component2() {
        return (String) ArraysKt.getOrNull(this.sArray, 1);
    }

    public final String component3() {
        return (String) ArraysKt.getOrNull(this.sArray, 2);
    }

    public final String component4() {
        return (String) ArraysKt.getOrNull(this.sArray, 3);
    }

    public final String component5() {
        return (String) ArraysKt.getOrNull(this.sArray, 4);
    }
}
