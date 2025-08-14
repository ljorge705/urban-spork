package com.onfido.android.sdk.capture.ui.camera.liveness.capture;

import com.onfido.android.sdk.capture.internal.util.logging.Timber;
import com.onfido.android.sdk.capture.ui.camera.liveness.challenges.LivenessChallenge;
import com.onfido.android.sdk.capture.ui.camera.liveness.challenges.LivenessChallengeViewModel;
import com.onfido.android.sdk.capture.ui.camera.liveness.challenges.LivenessChallengesViewModel;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import org.jmrtd.cbeff.ISO781611;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureViewModel$startLivenessProcessing$1", f = "LivenessCaptureViewModel.kt", i = {}, l = {ISO781611.DISCRETIONARY_DATA_FOR_PAYLOAD_CONSTRUCTED_TAG}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class LivenessCaptureViewModel$startLivenessProcessing$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Flow<Integer> $challengesCounterFlow;
    final /* synthetic */ LivenessChallengesViewModel $challengesViewModel;
    int label;
    final /* synthetic */ LivenessCaptureViewModel this$0;

    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0001H\u008a@"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "", "counter"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureViewModel$startLivenessProcessing$1$1", f = "LivenessCaptureViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureViewModel$startLivenessProcessing$1$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function3<Unit, Integer, Continuation<? super Integer>, Object> {
        /* synthetic */ int I$0;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(Unit unit, Integer num, Continuation<? super Integer> continuation) {
            return invoke(unit, num.intValue(), continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return Boxing.boxInt(this.I$0);
        }

        public final Object invoke(Unit unit, int i, Continuation<? super Integer> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(continuation);
            anonymousClass1.I$0 = i;
            return anonymousClass1.invokeSuspend(Unit.INSTANCE);
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@"}, d2 = {"<anonymous>", "Lkotlinx/coroutines/flow/Flow;", "Lkotlin/Pair;", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessChallengeViewModel;", "", "counter", ""}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureViewModel$startLivenessProcessing$1$2", f = "LivenessCaptureViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureViewModel$startLivenessProcessing$1$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<Integer, Continuation<? super Flow<? extends Pair<? extends LivenessChallengeViewModel, ? extends Long>>>, Object> {
        final /* synthetic */ LivenessChallengesViewModel $challengesViewModel;
        /* synthetic */ int I$0;
        int label;
        final /* synthetic */ LivenessCaptureViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(LivenessChallengesViewModel livenessChallengesViewModel, LivenessCaptureViewModel livenessCaptureViewModel, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$challengesViewModel = livenessChallengesViewModel;
            this.this$0 = livenessCaptureViewModel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$challengesViewModel, this.this$0, continuation);
            anonymousClass2.I$0 = ((Number) obj).intValue();
            return anonymousClass2;
        }

        public final Object invoke(int i, Continuation<? super Flow<Pair<LivenessChallengeViewModel, Long>>> continuation) {
            return ((AnonymousClass2) create(Integer.valueOf(i), continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            int i = this.I$0;
            if (i >= this.$challengesViewModel.getChallenges().size()) {
                return FlowKt.emptyFlow();
            }
            return FlowKt.flowOf(TuplesKt.to(new LivenessChallengeViewModel(i, this.$challengesViewModel.getChallenges().get(i), i == CollectionsKt.getLastIndex(this.$challengesViewModel.getChallenges())), Boxing.boxLong(this.this$0.timeProvider.getCurrentTimestamp())));
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Integer num, Continuation<? super Flow<? extends Pair<? extends LivenessChallengeViewModel, ? extends Long>>> continuation) {
            return invoke(num.intValue(), (Continuation<? super Flow<Pair<LivenessChallengeViewModel, Long>>>) continuation);
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\u0010\u0000\u001a\u00020\u0001*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lkotlin/Pair;", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessChallengeViewModel;", ""}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureViewModel$startLivenessProcessing$1$3", f = "LivenessCaptureViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureViewModel$startLivenessProcessing$1$3, reason: invalid class name */
    static final class AnonymousClass3 extends SuspendLambda implements Function2<FlowCollector<? super Pair<? extends LivenessChallengeViewModel, ? extends Long>>, Continuation<? super Unit>, Object> {
        final /* synthetic */ LivenessChallengesViewModel $challengesViewModel;
        int label;
        final /* synthetic */ LivenessCaptureViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(LivenessCaptureViewModel livenessCaptureViewModel, LivenessChallengesViewModel livenessChallengesViewModel, Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
            this.this$0 = livenessCaptureViewModel;
            this.$challengesViewModel = livenessChallengesViewModel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass3(this.this$0, this.$challengesViewModel, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super Pair<? extends LivenessChallengeViewModel, ? extends Long>> flowCollector, Continuation<? super Unit> continuation) {
            return invoke2((FlowCollector<? super Pair<LivenessChallengeViewModel, Long>>) flowCollector, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            this.this$0.livenessInteractor.initializePerformedChallenges(this.$challengesViewModel.getId());
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(FlowCollector<? super Pair<LivenessChallengeViewModel, Long>> flowCollector, Continuation<? super Unit> continuation) {
            return ((AnonymousClass3) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\u0010\u0000\u001a\u00020\u00012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003H\u008a@"}, d2 = {"<anonymous>", "", "<name for destructuring parameter 0>", "Lkotlin/Pair;", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessChallengeViewModel;", ""}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureViewModel$startLivenessProcessing$1$4", f = "LivenessCaptureViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureViewModel$startLivenessProcessing$1$4, reason: invalid class name */
    static final class AnonymousClass4 extends SuspendLambda implements Function2<Pair<? extends LivenessChallengeViewModel, ? extends Long>, Continuation<? super Unit>, Object> {
        final /* synthetic */ LivenessChallengesViewModel $challengesViewModel;
        /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ LivenessCaptureViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass4(LivenessCaptureViewModel livenessCaptureViewModel, LivenessChallengesViewModel livenessChallengesViewModel, Continuation<? super AnonymousClass4> continuation) {
            super(2, continuation);
            this.this$0 = livenessCaptureViewModel;
            this.$challengesViewModel = livenessChallengesViewModel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass4 anonymousClass4 = new AnonymousClass4(this.this$0, this.$challengesViewModel, continuation);
            anonymousClass4.L$0 = obj;
            return anonymousClass4;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Pair<? extends LivenessChallengeViewModel, ? extends Long> pair, Continuation<? super Unit> continuation) {
            return invoke2((Pair<LivenessChallengeViewModel, Long>) pair, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            LivenessChallengeViewModel livenessChallengeViewModel = (LivenessChallengeViewModel) ((Pair) this.L$0).component1();
            this.this$0.processCurrentChallenge(livenessChallengeViewModel, this.$challengesViewModel);
            this.this$0.onNextChallenge$onfido_capture_sdk_core_release(livenessChallengeViewModel.getChallenge());
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(Pair<LivenessChallengeViewModel, Long> pair, Continuation<? super Unit> continuation) {
            return ((AnonymousClass4) create(pair, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00030\u00022\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lkotlin/Pair;", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessChallengeViewModel;", "", "it", ""}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureViewModel$startLivenessProcessing$1$5", f = "LivenessCaptureViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureViewModel$startLivenessProcessing$1$5, reason: invalid class name */
    static final class AnonymousClass5 extends SuspendLambda implements Function3<FlowCollector<? super Pair<? extends LivenessChallengeViewModel, ? extends Long>>, Throwable, Continuation<? super Unit>, Object> {
        final /* synthetic */ LivenessChallengesViewModel $challengesViewModel;
        /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ LivenessCaptureViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass5(LivenessCaptureViewModel livenessCaptureViewModel, LivenessChallengesViewModel livenessChallengesViewModel, Continuation<? super AnonymousClass5> continuation) {
            super(3, continuation);
            this.this$0 = livenessCaptureViewModel;
            this.$challengesViewModel = livenessChallengesViewModel;
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super Pair<? extends LivenessChallengeViewModel, ? extends Long>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            return invoke2((FlowCollector<? super Pair<LivenessChallengeViewModel, Long>>) flowCollector, th, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            if (((Throwable) this.L$0) == null) {
                this.this$0.pushPerformedChallenge$onfido_capture_sdk_core_release((LivenessChallenge) CollectionsKt.last((List) this.$challengesViewModel.getChallenges()));
                this.this$0.getChallengesCompletedLiveData$onfido_capture_sdk_core_release().postValue(Boxing.boxBoolean(true));
            }
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(FlowCollector<? super Pair<LivenessChallengeViewModel, Long>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            AnonymousClass5 anonymousClass5 = new AnonymousClass5(this.this$0, this.$challengesViewModel, continuation);
            anonymousClass5.L$0 = th;
            return anonymousClass5.invokeSuspend(Unit.INSTANCE);
        }
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00030\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lkotlin/Pair;", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessChallengeViewModel;", "", "it", ""}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureViewModel$startLivenessProcessing$1$6", f = "LivenessCaptureViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureViewModel$startLivenessProcessing$1$6, reason: invalid class name */
    static final class AnonymousClass6 extends SuspendLambda implements Function3<FlowCollector<? super Pair<? extends LivenessChallengeViewModel, ? extends Long>>, Throwable, Continuation<? super Unit>, Object> {
        /* synthetic */ Object L$0;
        int label;

        AnonymousClass6(Continuation<? super AnonymousClass6> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super Pair<? extends LivenessChallengeViewModel, ? extends Long>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            return invoke2((FlowCollector<? super Pair<LivenessChallengeViewModel, Long>>) flowCollector, th, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            Throwable th = (Throwable) this.L$0;
            Timber.INSTANCE.i(th, "Error on Liveness challenge provider: " + th.getMessage(), new Object[0]);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(FlowCollector<? super Pair<LivenessChallengeViewModel, Long>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            AnonymousClass6 anonymousClass6 = new AnonymousClass6(continuation);
            anonymousClass6.L$0 = th;
            return anonymousClass6.invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    LivenessCaptureViewModel$startLivenessProcessing$1(LivenessCaptureViewModel livenessCaptureViewModel, Flow<Integer> flow, LivenessChallengesViewModel livenessChallengesViewModel, Continuation<? super LivenessCaptureViewModel$startLivenessProcessing$1> continuation) {
        super(2, continuation);
        this.this$0 = livenessCaptureViewModel;
        this.$challengesCounterFlow = flow;
        this.$challengesViewModel = livenessChallengesViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new LivenessCaptureViewModel$startLivenessProcessing$1(this.this$0, this.$challengesCounterFlow, this.$challengesViewModel, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Flow flowM7628catch = FlowKt.m7628catch(FlowKt.onCompletion(FlowKt.onEach(FlowKt.onStart(FlowKt.flatMapConcat(FlowKt.zip(this.this$0.livenessInteractor.getLivenessControlButtonSharedFlow(), this.$challengesCounterFlow, new AnonymousClass1(null)), new AnonymousClass2(this.$challengesViewModel, this.this$0, null)), new AnonymousClass3(this.this$0, this.$challengesViewModel, null)), new AnonymousClass4(this.this$0, this.$challengesViewModel, null)), new AnonymousClass5(this.this$0, this.$challengesViewModel, null)), new AnonymousClass6(null));
            this.label = 1;
            if (FlowKt.collect(flowM7628catch, this) == coroutine_suspended) {
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
        return ((LivenessCaptureViewModel$startLivenessProcessing$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
