package com.onfido.hosted.web.module.utils;

import androidx.exifinterface.media.ExifInterface;
import com.clevertap.android.sdk.Constants;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\u001aD\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u00032\"\u0010\u0004\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0005H\u0080@¢\u0006\u0002\u0010\t¨\u0006\n"}, d2 = {"collectNonNull", "", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/flow/SharedFlow;", Constants.KEY_ACTION, "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "", "(Lkotlinx/coroutines/flow/SharedFlow;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onfido-capture-sdk-core_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class HostedWebModuleExtKt {

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.onfido.hosted.web.module.utils.HostedWebModuleExtKt", f = "HostedWebModuleExt.kt", i = {}, l = {6}, m = "collectNonNull", n = {}, s = {})
    /* renamed from: com.onfido.hosted.web.module.utils.HostedWebModuleExtKt$collectNonNull$1, reason: invalid class name */
    static final class AnonymousClass1<T> extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HostedWebModuleExtKt.collectNonNull(null, null, this);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final <T> java.lang.Object collectNonNull(kotlinx.coroutines.flow.SharedFlow<? extends T> r4, final kotlin.jvm.functions.Function2<? super T, ? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> r5, kotlin.coroutines.Continuation<?> r6) {
        /*
            boolean r0 = r6 instanceof com.onfido.hosted.web.module.utils.HostedWebModuleExtKt.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r6
            com.onfido.hosted.web.module.utils.HostedWebModuleExtKt$collectNonNull$1 r0 = (com.onfido.hosted.web.module.utils.HostedWebModuleExtKt.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.onfido.hosted.web.module.utils.HostedWebModuleExtKt$collectNonNull$1 r0 = new com.onfido.hosted.web.module.utils.HostedWebModuleExtKt$collectNonNull$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L31
            if (r2 == r3) goto L2d
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L2d:
            kotlin.ResultKt.throwOnFailure(r6)
            goto L42
        L31:
            kotlin.ResultKt.throwOnFailure(r6)
            com.onfido.hosted.web.module.utils.HostedWebModuleExtKt$collectNonNull$2 r6 = new com.onfido.hosted.web.module.utils.HostedWebModuleExtKt$collectNonNull$2
            r6.<init>()
            r0.label = r3
            java.lang.Object r4 = r4.collect(r6, r0)
            if (r4 != r1) goto L42
            return r1
        L42:
            kotlin.KotlinNothingValueException r4 = new kotlin.KotlinNothingValueException
            r4.<init>()
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onfido.hosted.web.module.utils.HostedWebModuleExtKt.collectNonNull(kotlinx.coroutines.flow.SharedFlow, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
