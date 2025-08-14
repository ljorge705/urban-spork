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

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u0004H\u008a@¨\u0006\u0005"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "R", "Lkotlinx/coroutines/flow/FlowCollector;", "kotlinx/coroutines/flow/FlowKt__EmittersKt$transform$1"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "com.onfido.android.sdk.capture.utils.FlowExtKt$onTrue$$inlined$transform$1", f = "FlowExt.kt", i = {}, l = {40}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class FlowExtKt$onTrue$$inlined$transform$1 extends SuspendLambda implements Function2<FlowCollector<? super Boolean>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1 $action$inlined;
    final /* synthetic */ Flow $this_transform;
    private /* synthetic */ Object L$0;
    int label;

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "R", "value", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__EmittersKt$transform$1$1"}, k = 3, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.onfido.android.sdk.capture.utils.FlowExtKt$onTrue$$inlined$transform$1$1, reason: invalid class name */
    public static final class AnonymousClass1<T> implements FlowCollector {
        final /* synthetic */ FlowCollector<Boolean> $$this$flow;
        final /* synthetic */ Function1 $action$inlined;

        @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
        @DebugMetadata(c = "com.onfido.android.sdk.capture.utils.FlowExtKt$onTrue$$inlined$transform$1$1", f = "FlowExt.kt", i = {0, 0}, l = {224, 226}, m = "emit", n = {"$this$onTrue_u24lambda_u240", "value"}, s = {"L$0", "Z$0"})
        /* renamed from: com.onfido.android.sdk.capture.utils.FlowExtKt$onTrue$$inlined$transform$1$1$1, reason: invalid class name and collision with other inner class name */
        public static final class C01641 extends ContinuationImpl {
            Object L$0;
            boolean Z$0;
            int label;
            /* synthetic */ Object result;

            public C01641(Continuation continuation) {
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
            this.$action$inlined = function1;
            this.$$this$flow = flowCollector;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
        @Override // kotlinx.coroutines.flow.FlowCollector
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object emit(T r6, kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
            /*
                r5 = this;
                boolean r0 = r7 instanceof com.onfido.android.sdk.capture.utils.FlowExtKt$onTrue$$inlined$transform$1.AnonymousClass1.C01641
                if (r0 == 0) goto L13
                r0 = r7
                com.onfido.android.sdk.capture.utils.FlowExtKt$onTrue$$inlined$transform$1$1$1 r0 = (com.onfido.android.sdk.capture.utils.FlowExtKt$onTrue$$inlined$transform$1.AnonymousClass1.C01641) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = r1 & r2
                if (r3 == 0) goto L13
                int r1 = r1 - r2
                r0.label = r1
                goto L18
            L13:
                com.onfido.android.sdk.capture.utils.FlowExtKt$onTrue$$inlined$transform$1$1$1 r0 = new com.onfido.android.sdk.capture.utils.FlowExtKt$onTrue$$inlined$transform$1$1$1
                r0.<init>(r7)
            L18:
                java.lang.Object r7 = r0.result
                java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r2 = r0.label
                r3 = 2
                r4 = 1
                if (r2 == 0) goto L3e
                if (r2 == r4) goto L34
                if (r2 != r3) goto L2c
                kotlin.ResultKt.throwOnFailure(r7)
                goto L72
            L2c:
                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                r6.<init>(r7)
                throw r6
            L34:
                boolean r6 = r0.Z$0
                java.lang.Object r2 = r0.L$0
                kotlinx.coroutines.flow.FlowCollector r2 = (kotlinx.coroutines.flow.FlowCollector) r2
                kotlin.ResultKt.throwOnFailure(r7)
                goto L62
            L3e:
                kotlin.ResultKt.throwOnFailure(r7)
                kotlinx.coroutines.flow.FlowCollector<java.lang.Boolean> r2 = r5.$$this$flow
                java.lang.Boolean r6 = (java.lang.Boolean) r6
                boolean r6 = r6.booleanValue()
                if (r6 == 0) goto L62
                kotlin.jvm.functions.Function1 r7 = r5.$action$inlined
                r0.L$0 = r2
                r0.Z$0 = r6
                r0.label = r4
                r4 = 6
                kotlin.jvm.internal.InlineMarker.mark(r4)
                java.lang.Object r7 = r7.invoke(r0)
                r4 = 7
                kotlin.jvm.internal.InlineMarker.mark(r4)
                if (r7 != r1) goto L62
                return r1
            L62:
                java.lang.Boolean r6 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r6)
                r7 = 0
                r0.L$0 = r7
                r0.label = r3
                java.lang.Object r6 = r2.emit(r6, r0)
                if (r6 != r1) goto L72
                return r1
            L72:
                kotlin.Unit r6 = kotlin.Unit.INSTANCE
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: com.onfido.android.sdk.capture.utils.FlowExtKt$onTrue$$inlined$transform$1.AnonymousClass1.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlowExtKt$onTrue$$inlined$transform$1(Flow flow, Continuation continuation, Function1 function1) {
        super(2, continuation);
        this.$this_transform = flow;
        this.$action$inlined = function1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        FlowExtKt$onTrue$$inlined$transform$1 flowExtKt$onTrue$$inlined$transform$1 = new FlowExtKt$onTrue$$inlined$transform$1(this.$this_transform, continuation, this.$action$inlined);
        flowExtKt$onTrue$$inlined$transform$1.L$0 = obj;
        return flowExtKt$onTrue$$inlined$transform$1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            Flow flow = this.$this_transform;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(flowCollector, this.$action$inlined);
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
    public final Object invoke(FlowCollector<? super Boolean> flowCollector, Continuation<? super Unit> continuation) {
        return ((FlowExtKt$onTrue$$inlined$transform$1) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
