package com.onfido.hosted.web.module.externallink;

import com.onfido.javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.OkHttpClient;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J$\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0096@ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\n\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006\f"}, d2 = {"Lcom/onfido/hosted/web/module/externallink/HostedWebModuleExternalLinkRepositoryImpl;", "Lcom/onfido/hosted/web/module/externallink/HostedWebModuleExternalLinkRepository;", "okHttpClient", "Lokhttp3/OkHttpClient;", "(Lokhttp3/OkHttpClient;)V", "fetchExternalLink", "Lkotlin/Result;", "Lcom/onfido/hosted/web/module/externallink/HostedWebModuleExternalLinkResult;", "url", "Ljava/net/URL;", "fetchExternalLink-gIAlu-s", "(Ljava/net/URL;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class HostedWebModuleExternalLinkRepositoryImpl implements HostedWebModuleExternalLinkRepository {
    private final OkHttpClient okHttpClient;

    @Inject
    public HostedWebModuleExternalLinkRepositoryImpl(OkHttpClient okHttpClient) {
        Intrinsics.checkNotNullParameter(okHttpClient, "okHttpClient");
        this.okHttpClient = okHttpClient;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // com.onfido.hosted.web.module.externallink.HostedWebModuleExternalLinkRepository
    /* renamed from: fetchExternalLink-gIAlu-s */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object mo5751fetchExternalLinkgIAlus(java.net.URL r5, kotlin.coroutines.Continuation<? super kotlin.Result<? extends com.onfido.hosted.web.module.externallink.HostedWebModuleExternalLinkResult>> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.onfido.hosted.web.module.externallink.HostedWebModuleExternalLinkRepositoryImpl$fetchExternalLink$1
            if (r0 == 0) goto L13
            r0 = r6
            com.onfido.hosted.web.module.externallink.HostedWebModuleExternalLinkRepositoryImpl$fetchExternalLink$1 r0 = (com.onfido.hosted.web.module.externallink.HostedWebModuleExternalLinkRepositoryImpl$fetchExternalLink$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.onfido.hosted.web.module.externallink.HostedWebModuleExternalLinkRepositoryImpl$fetchExternalLink$1 r0 = new com.onfido.hosted.web.module.externallink.HostedWebModuleExternalLinkRepositoryImpl$fetchExternalLink$1
            r0.<init>(r4, r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L39
            if (r2 != r3) goto L31
            java.lang.Object r5 = r0.L$1
            java.net.URL r5 = (java.net.URL) r5
            java.lang.Object r5 = r0.L$0
            com.onfido.hosted.web.module.externallink.HostedWebModuleExternalLinkRepositoryImpl r5 = (com.onfido.hosted.web.module.externallink.HostedWebModuleExternalLinkRepositoryImpl) r5
            kotlin.ResultKt.throwOnFailure(r6)
            goto L83
        L31:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L39:
            kotlin.ResultKt.throwOnFailure(r6)
            r0.L$0 = r4
            r0.L$1 = r5
            r0.label = r3
            kotlinx.coroutines.CancellableContinuationImpl r6 = new kotlinx.coroutines.CancellableContinuationImpl
            kotlin.coroutines.Continuation r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.intercepted(r0)
            r6.<init>(r2, r3)
            r6.initCancellability()
            okhttp3.Request$Builder r2 = new okhttp3.Request$Builder
            r2.<init>()
            okhttp3.Request$Builder r5 = r2.url(r5)
            okhttp3.Request r5 = r5.build()
            okhttp3.OkHttpClient r2 = access$getOkHttpClient$p(r4)
            okhttp3.Call r5 = r2.newCall(r5)
            com.onfido.hosted.web.module.externallink.HostedWebModuleExternalLinkRepositoryImpl$fetchExternalLink$2$1 r2 = new com.onfido.hosted.web.module.externallink.HostedWebModuleExternalLinkRepositoryImpl$fetchExternalLink$2$1
            r2.<init>()
            r5.enqueue(r2)
            com.onfido.hosted.web.module.externallink.HostedWebModuleExternalLinkRepositoryImpl$fetchExternalLink$2$2 r5 = new com.onfido.hosted.web.module.externallink.HostedWebModuleExternalLinkRepositoryImpl$fetchExternalLink$2$2
            r5.<init>()
            r6.invokeOnCancellation(r5)
            java.lang.Object r6 = r6.getResult()
            java.lang.Object r5 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r6 != r5) goto L80
            kotlin.coroutines.jvm.internal.DebugProbesKt.probeCoroutineSuspended(r0)
        L80:
            if (r6 != r1) goto L83
            return r1
        L83:
            kotlin.Result r6 = (kotlin.Result) r6
            java.lang.Object r5 = r6.getValue()
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onfido.hosted.web.module.externallink.HostedWebModuleExternalLinkRepositoryImpl.mo5751fetchExternalLinkgIAlus(java.net.URL, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
