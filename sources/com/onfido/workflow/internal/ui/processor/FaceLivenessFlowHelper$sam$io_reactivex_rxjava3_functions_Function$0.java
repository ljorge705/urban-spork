package com.onfido.workflow.internal.ui.processor;

import io.reactivex.rxjava3.functions.Function;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FaceLivenessFlowHelper.kt */
@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
final class FaceLivenessFlowHelper$sam$io_reactivex_rxjava3_functions_Function$0 implements Function {
    private final /* synthetic */ Function1 function;

    FaceLivenessFlowHelper$sam$io_reactivex_rxjava3_functions_Function$0(Function1 function) {
        Intrinsics.checkNotNullParameter(function, "function");
        this.function = function;
    }

    @Override // io.reactivex.rxjava3.functions.Function
    public final /* synthetic */ Object apply(Object obj) {
        return this.function.invoke(obj);
    }
}
