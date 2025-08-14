package com.onfido.hosted.web.module.externallink;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "com.onfido.hosted.web.module.externallink.HostedWebModuleExternalLinkRepositoryImpl", f = "HostedWebModuleExternalLinkRepositoryImpl.kt", i = {0, 0}, l = {55}, m = "fetchExternalLink-gIAlu-s", n = {"this", "url"}, s = {"L$0", "L$1"})
/* loaded from: classes6.dex */
final class HostedWebModuleExternalLinkRepositoryImpl$fetchExternalLink$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ HostedWebModuleExternalLinkRepositoryImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    HostedWebModuleExternalLinkRepositoryImpl$fetchExternalLink$1(HostedWebModuleExternalLinkRepositoryImpl hostedWebModuleExternalLinkRepositoryImpl, Continuation<? super HostedWebModuleExternalLinkRepositoryImpl$fetchExternalLink$1> continuation) {
        super(continuation);
        this.this$0 = hostedWebModuleExternalLinkRepositoryImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object objMo5751fetchExternalLinkgIAlus = this.this$0.mo5751fetchExternalLinkgIAlus(null, this);
        return objMo5751fetchExternalLinkgIAlus == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objMo5751fetchExternalLinkgIAlus : Result.m6094boximpl(objMo5751fetchExternalLinkgIAlus);
    }
}
