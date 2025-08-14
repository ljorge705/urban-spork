package com.onfido.android.sdk.capture.utils;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: Add missing generic type declarations: [T] */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u0004H\u008a@¨\u0006\u0005"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "R", "Lkotlinx/coroutines/flow/FlowCollector;", "kotlinx/coroutines/flow/FlowKt__EmittersKt$transform$1"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "com.onfido.android.sdk.capture.utils.FlowExtKt$skipWhile$$inlined$transform$1", f = "FlowExt.kt", i = {}, l = {40}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class FlowExtKt$skipWhile$$inlined$transform$1<T> extends SuspendLambda implements Function2<FlowCollector<? super T>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1 $predicate$inlined;
    final /* synthetic */ Flow $this_transform;
    private /* synthetic */ Object L$0;
    int label;

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "R", "value", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__EmittersKt$transform$1$1"}, k = 3, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.onfido.android.sdk.capture.utils.FlowExtKt$skipWhile$$inlined$transform$1$1, reason: invalid class name */
    public static final class AnonymousClass1<T> implements FlowCollector {
        final /* synthetic */ FlowCollector<T> $$this$flow;
        final /* synthetic */ Function1 $predicate$inlined;

        @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
        @DebugMetadata(c = "com.onfido.android.sdk.capture.utils.FlowExtKt$skipWhile$$inlined$transform$1$1", f = "FlowExt.kt", i = {}, l = {224}, m = "emit", n = {}, s = {})
        /* renamed from: com.onfido.android.sdk.capture.utils.FlowExtKt$skipWhile$$inlined$transform$1$1$1, reason: invalid class name and collision with other inner class name */
        public static final class C01651 extends ContinuationImpl {
            int label;
            /* synthetic */ Object result;

            public C01651(Continuation continuation) {
                super(continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                this.result = obj;
                this.label |= Integer.MIN_VALUE;
                return AnonymousClass1.this.emit(null, this);
            }
        }

        public AnonymousClass1(FlowCollector flowCollector, Function1 function1) {
            this.$predicate$inlined = function1;
            this.$$this$flow = flowCollector;
        }

        /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
        @Override // kotlinx.coroutines.flow.FlowCollector
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object emit(T r5, kotlin.coroutines.Continuation<? super kotlin.Unit> r6) {
            /*
                r4 = this;
                boolean r0 = r6 instanceof com.onfido.android.sdk.capture.utils.FlowExtKt$skipWhile$$inlined$transform$1.AnonymousClass1.C01651
                if (r0 == 0) goto L13
                r0 = r6
                com.onfido.android.sdk.capture.utils.FlowExtKt$skipWhile$$inlined$transform$1$1$1 r0 = (com.onfido.android.sdk.capture.utils.FlowExtKt$skipWhile$$inlined$transform$1.AnonymousClass1.C01651) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = r1 & r2
                if (r3 == 0) goto L13
                int r1 = r1 - r2
                r0.label = r1
                goto L18
            L13:
                com.onfido.android.sdk.capture.utils.FlowExtKt$skipWhile$$inlined$transform$1$1$1 r0 = new com.onfido.android.sdk.capture.utils.FlowExtKt$skipWhile$$inlined$transform$1$1$1
                r0.<init>(r6)
            L18:
                java.lang.Object r6 = r0.result
                java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r2 = r0.label
                r3 = 1
                if (r2 == 0) goto L31
                if (r2 != r3) goto L29
                kotlin.ResultKt.throwOnFailure(r6)
                goto L4d
            L29:
                java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                r5.<init>(r6)
                throw r5
            L31:
                kotlin.ResultKt.throwOnFailure(r6)
                kotlinx.coroutines.flow.FlowCollector<T> r6 = r4.$$this$flow
                kotlin.jvm.functions.Function1 r2 = r4.$predicate$inlined
                java.lang.Object r2 = r2.invoke(r5)
                java.lang.Boolean r2 = (java.lang.Boolean) r2
                boolean r2 = r2.booleanValue()
                if (r2 != 0) goto L4d
                r0.label = r3
                java.lang.Object r5 = r6.emit(r5, r0)
                if (r5 != r1) goto L4d
                return r1
            L4d:
                kotlin.Unit r5 = kotlin.Unit.INSTANCE
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: com.onfido.android.sdk.capture.utils.FlowExtKt$skipWhile$$inlined$transform$1.AnonymousClass1.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlowExtKt$skipWhile$$inlined$transform$1(Flow flow, Continuation continuation, Function1 function1) {
        super(2, continuation);
        this.$this_transform = flow;
        this.$predicate$inlined = function1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        FlowExtKt$skipWhile$$inlined$transform$1 flowExtKt$skipWhile$$inlined$transform$1 = new FlowExtKt$skipWhile$$inlined$transform$1(this.$this_transform, continuation, this.$predicate$inlined);
        flowExtKt$skipWhile$$inlined$transform$1.L$0 = obj;
        return flowExtKt$skipWhile$$inlined$transform$1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            Flow flow = this.$this_transform;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(flowCollector, this.$predicate$inlined);
            this.label = 1;
            if (flow.collect(anonymousClass1, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(FlowCollector<? super T> flowCollector, Continuation<? super Unit> continuation) {
        return ((FlowExtKt$skipWhile$$inlined$transform$1) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
