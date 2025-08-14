package com.onfido.android.sdk.capture.utils;

import androidx.exifinterface.media.ExifInterface;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.clevertap.android.sdk.Constants;
import io.sentry.ProfilingTraceData;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.StateFlow;

@Metadata(d1 = {"\u0000^\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a&\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0080@¢\u0006\u0002\u0010\u0007\u001a\u001a\u0010\b\u001a\u00020\u0001\"\u0004\b\u0000\u0010\t*\n\u0012\u0006\u0012\u0004\u0018\u0001H\t0\nH\u0000\u001a.\u0010\u000b\u001a\u00020\u0001\"\u0004\b\u0000\u0010\t*\n\u0012\u0006\u0012\u0004\u0018\u0001H\t0\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\t0\u000eH\u0080@¢\u0006\u0002\u0010\u000f\u001a&\u0010\u0010\u001a\u00020\u0011\"\u0004\b\u0000\u0010\t*\b\u0012\u0004\u0012\u0002H\t0\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016\u001a;\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00060\u0012*\b\u0012\u0004\u0012\u00020\u00060\u00122\u001c\u0010\u0018\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u0019H\u0000¢\u0006\u0002\u0010\u001c\u001a2\u0010\u001d\u001a\b\u0012\u0004\u0012\u0002H\t0\u0012\"\u0004\b\u0000\u0010\t*\b\u0012\u0004\u0012\u0002H\t0\u001e2\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u00020\u00060\u0019H\u0000¨\u0006 "}, d2 = {"delayUntil", "", ProfilingTraceData.TRUNCATION_REASON_TIMEOUT, "", "condition", "Lkotlin/Function0;", "", "(JLkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "clear", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/flow/MutableStateFlow;", "collectNotNull", "Lkotlinx/coroutines/flow/StateFlow;", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/StateFlow;Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "launchInAndRepeat", "Lkotlinx/coroutines/Job;", "Lkotlinx/coroutines/flow/Flow;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "repeatState", "Landroidx/lifecycle/Lifecycle$State;", "onTrue", Constants.KEY_ACTION, "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function1;)Lkotlinx/coroutines/flow/Flow;", "skipWhile", "Lkotlinx/coroutines/flow/SharedFlow;", "predicate", "onfido-capture-sdk-core_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FlowExtKt {

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.onfido.android.sdk.capture.utils.FlowExtKt", f = "FlowExt.kt", i = {1}, l = {30, 37}, m = "delayUntil", n = {"condition"}, s = {"L$0"})
    /* renamed from: com.onfido.android.sdk.capture.utils.FlowExtKt$delayUntil$1, reason: invalid class name */
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FlowExtKt.delayUntil(0L, null, this);
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.onfido.android.sdk.capture.utils.FlowExtKt$delayUntil$2", f = "FlowExt.kt", i = {}, l = {32}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.onfido.android.sdk.capture.utils.FlowExtKt$delayUntil$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function0<Boolean> $condition;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(Function0<Boolean> function0, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$condition = function0;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.$condition, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i != 0 && i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            while (!this.$condition.invoke().booleanValue()) {
                this.label = 1;
                if (DelayKt.delay(1L, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.onfido.android.sdk.capture.utils.FlowExtKt$launchInAndRepeat$1", f = "FlowExt.kt", i = {}, l = {46}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.onfido.android.sdk.capture.utils.FlowExtKt$launchInAndRepeat$1, reason: invalid class name and case insensitive filesystem */
    static final class C07211 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ LifecycleOwner $lifecycleOwner;
        final /* synthetic */ Lifecycle.State $repeatState;
        final /* synthetic */ Flow<T> $this_launchInAndRepeat;
        int label;

        @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
        @DebugMetadata(c = "com.onfido.android.sdk.capture.utils.FlowExtKt$launchInAndRepeat$1$1", f = "FlowExt.kt", i = {}, l = {47}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.onfido.android.sdk.capture.utils.FlowExtKt$launchInAndRepeat$1$1, reason: invalid class name and collision with other inner class name */
        static final class C01661 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ Flow<T> $this_launchInAndRepeat;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C01661(Flow<? extends T> flow, Continuation<? super C01661> continuation) {
                super(2, continuation);
                this.$this_launchInAndRepeat = flow;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C01661(this.$this_launchInAndRepeat, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    Flow<T> flow = this.$this_launchInAndRepeat;
                    this.label = 1;
                    if (FlowKt.collect(flow, this) == coroutine_suspended) {
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
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C01661) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C07211(LifecycleOwner lifecycleOwner, Lifecycle.State state, Flow<? extends T> flow, Continuation<? super C07211> continuation) {
            super(2, continuation);
            this.$lifecycleOwner = lifecycleOwner;
            this.$repeatState = state;
            this.$this_launchInAndRepeat = flow;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C07211(this.$lifecycleOwner, this.$repeatState, this.$this_launchInAndRepeat, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                LifecycleOwner lifecycleOwner = this.$lifecycleOwner;
                Lifecycle.State state = this.$repeatState;
                C01661 c01661 = new C01661(this.$this_launchInAndRepeat, null);
                this.label = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(lifecycleOwner, state, c01661, this) == coroutine_suspended) {
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
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C07211) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    public static final <T> void clear(MutableStateFlow<T> mutableStateFlow) {
        Intrinsics.checkNotNullParameter(mutableStateFlow, "<this>");
        while (!mutableStateFlow.compareAndSet(mutableStateFlow.getValue(), null)) {
        }
    }

    public static final <T> Object collectNotNull(StateFlow<? extends T> stateFlow, FlowCollector<? super T> flowCollector, Continuation<? super Unit> continuation) {
        Object objCollect = FlowKt.filterNotNull(stateFlow).collect(flowCollector, continuation);
        return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object delayUntil(long r7, kotlin.jvm.functions.Function0<java.lang.Boolean> r9, kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            boolean r0 = r10 instanceof com.onfido.android.sdk.capture.utils.FlowExtKt.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r10
            com.onfido.android.sdk.capture.utils.FlowExtKt$delayUntil$1 r0 = (com.onfido.android.sdk.capture.utils.FlowExtKt.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.onfido.android.sdk.capture.utils.FlowExtKt$delayUntil$1 r0 = new com.onfido.android.sdk.capture.utils.FlowExtKt$delayUntil$1
            r0.<init>(r10)
        L18:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L3d
            if (r2 == r4) goto L39
            if (r2 != r3) goto L31
            java.lang.Object r7 = r0.L$0
            r9 = r7
            kotlin.jvm.functions.Function0 r9 = (kotlin.jvm.functions.Function0) r9
            kotlin.ResultKt.throwOnFailure(r10)
            goto L58
        L31:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L39:
            kotlin.ResultKt.throwOnFailure(r10)
            goto L55
        L3d:
            kotlin.ResultKt.throwOnFailure(r10)
            r5 = 0
            int r10 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            if (r10 <= 0) goto L58
            com.onfido.android.sdk.capture.utils.FlowExtKt$delayUntil$2 r10 = new com.onfido.android.sdk.capture.utils.FlowExtKt$delayUntil$2
            r2 = 0
            r10.<init>(r9, r2)
            r0.label = r4
            java.lang.Object r7 = kotlinx.coroutines.TimeoutKt.withTimeout(r7, r10, r0)
            if (r7 != r1) goto L55
            return r1
        L55:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        L58:
            java.lang.Object r7 = r9.invoke()
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r7 = r7.booleanValue()
            if (r7 != 0) goto L71
            r0.L$0 = r9
            r0.label = r3
            r7 = 1
            java.lang.Object r7 = kotlinx.coroutines.DelayKt.delay(r7, r0)
            if (r7 != r1) goto L58
            return r1
        L71:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onfido.android.sdk.capture.utils.FlowExtKt.delayUntil(long, kotlin.jvm.functions.Function0, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object delayUntil$default(long j, Function0 function0, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            j = -1;
        }
        return delayUntil(j, function0, continuation);
    }

    public static final <T> Job launchInAndRepeat(Flow<? extends T> flow, LifecycleOwner lifecycleOwner, Lifecycle.State repeatState) {
        Intrinsics.checkNotNullParameter(flow, "<this>");
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        Intrinsics.checkNotNullParameter(repeatState, "repeatState");
        return BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(lifecycleOwner), null, null, new C07211(lifecycleOwner, repeatState, flow, null), 3, null);
    }

    public static final Flow<Boolean> onTrue(Flow<Boolean> flow, Function1<? super Continuation<? super Unit>, ? extends Object> action) {
        Intrinsics.checkNotNullParameter(flow, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        return FlowKt.flow(new FlowExtKt$onTrue$$inlined$transform$1(flow, null, action));
    }

    public static final <T> Flow<T> skipWhile(SharedFlow<? extends T> sharedFlow, Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(sharedFlow, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        return FlowKt.flow(new FlowExtKt$skipWhile$$inlined$transform$1(sharedFlow, null, predicate));
    }
}
