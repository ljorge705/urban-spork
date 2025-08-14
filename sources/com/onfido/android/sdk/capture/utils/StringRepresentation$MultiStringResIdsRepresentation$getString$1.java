package com.onfido.android.sdk.capture.utils;

import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
/* synthetic */ class StringRepresentation$MultiStringResIdsRepresentation$getString$1 extends FunctionReferenceImpl implements Function1<Integer, String> {
    StringRepresentation$MultiStringResIdsRepresentation$getString$1(Object obj) {
        super(1, obj, Context.class, "getString", "getString(I)Ljava/lang/String;", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ String invoke(Integer num) {
        return invoke(num.intValue());
    }

    public final String invoke(int i) {
        return ((Context) this.receiver).getString(i);
    }
}
