package com.onfido.android.sdk.capture.ui.camera.liveness.capture;

import androidx.exifinterface.media.ExifInterface;
import com.onfido.android.sdk.capture.internal.util.logging.Timber;
import com.onfido.android.sdk.capture.ui.camera.capture.VideoCaptureConfig;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import org.jmrtd.PassportService;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureViewModel$onRecordingStarted$1", f = "LivenessCaptureViewModel.kt", i = {}, l = {181}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class LivenessCaptureViewModel$onRecordingStarted$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ VideoCaptureConfig $videoCaptureConfig;
    int label;
    final /* synthetic */ LivenessCaptureViewModel this$0;

    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", ""}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureViewModel$onRecordingStarted$1$1", f = "LivenessCaptureViewModel.kt", i = {0, 1}, l = {171, 172}, m = "invokeSuspend", n = {"$this$flow", "$this$flow"}, s = {"L$0", "L$0"})
    /* renamed from: com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureViewModel$onRecordingStarted$1$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<FlowCollector<? super Long>, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ LivenessCaptureViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(LivenessCaptureViewModel livenessCaptureViewModel, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.this$0 = livenessCaptureViewModel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        /* JADX WARN: Removed duplicated region for block: B:14:0x0048 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:17:0x0055 A[RETURN] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x0053 -> B:12:0x0030). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r7) {
            /*
                r6 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r6.label
                r2 = 2
                r3 = 1
                if (r1 == 0) goto L27
                if (r1 == r3) goto L1e
                if (r1 != r2) goto L16
                java.lang.Object r1 = r6.L$0
                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                kotlin.ResultKt.throwOnFailure(r7)
                goto L2f
            L16:
                java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r7.<init>(r0)
                throw r7
            L1e:
                java.lang.Object r1 = r6.L$0
                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                kotlin.ResultKt.throwOnFailure(r7)
                r7 = r6
                goto L49
            L27:
                kotlin.ResultKt.throwOnFailure(r7)
                java.lang.Object r7 = r6.L$0
                r1 = r7
                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
            L2f:
                r7 = r6
            L30:
                com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureViewModel r4 = r7.this$0
                com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessInteractor r4 = com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureViewModel.access$getLivenessInteractor$p(r4)
                long r4 = r4.getAvailableStorageSpace()
                java.lang.Long r4 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r4)
                r7.L$0 = r1
                r7.label = r3
                java.lang.Object r4 = r1.emit(r4, r7)
                if (r4 != r0) goto L49
                return r0
            L49:
                r7.L$0 = r1
                r7.label = r2
                r4 = 1000(0x3e8, double:4.94E-321)
                java.lang.Object r4 = kotlinx.coroutines.DelayKt.delay(r4, r7)
                if (r4 != r0) goto L30
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureViewModel$onRecordingStarted$1.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super Long> flowCollector, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0004\u001a\u00020\u0005H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "", "it", ""}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureViewModel$onRecordingStarted$1$2", f = "LivenessCaptureViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureViewModel$onRecordingStarted$1$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function3<FlowCollector<? super Long>, Throwable, Continuation<? super Unit>, Object> {
        /* synthetic */ Object L$0;
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            Throwable th = (Throwable) this.L$0;
            Timber.INSTANCE.e(th, "Error on available storage calculation: " + th.getMessage(), new Object[0]);
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super Long> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(continuation);
            anonymousClass2.L$0 = th;
            return anonymousClass2.invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    LivenessCaptureViewModel$onRecordingStarted$1(LivenessCaptureViewModel livenessCaptureViewModel, VideoCaptureConfig videoCaptureConfig, Continuation<? super LivenessCaptureViewModel$onRecordingStarted$1> continuation) {
        super(2, continuation);
        this.this$0 = livenessCaptureViewModel;
        this.$videoCaptureConfig = videoCaptureConfig;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new LivenessCaptureViewModel$onRecordingStarted$1(this.this$0, this.$videoCaptureConfig, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final Flow flowM7628catch = FlowKt.m7628catch(FlowKt.flow(new AnonymousClass1(this.this$0, null)), new AnonymousClass2(null));
            final VideoCaptureConfig videoCaptureConfig = this.$videoCaptureConfig;
            Flow flowFlowOn = FlowKt.flowOn(FlowKt.take(new Flow<Long>() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureViewModel$onRecordingStarted$1$invokeSuspend$$inlined$filter$1

                @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "R", "value", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$1$1", "kotlinx/coroutines/flow/FlowKt__TransformKt$filter$$inlined$unsafeTransform$1$2"}, k = 3, mv = {1, 9, 0}, xi = 48)
                /* renamed from: com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureViewModel$onRecordingStarted$1$invokeSuspend$$inlined$filter$1$2, reason: invalid class name */
                public static final class AnonymousClass2<T> implements FlowCollector {
                    final /* synthetic */ FlowCollector $this_unsafeFlow;
                    final /* synthetic */ VideoCaptureConfig $videoCaptureConfig$inlined;

                    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
                    @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureViewModel$onRecordingStarted$1$invokeSuspend$$inlined$filter$1$2", f = "LivenessCaptureViewModel.kt", i = {}, l = {PassportService.DEFAULT_MAX_BLOCKSIZE}, m = "emit", n = {}, s = {})
                    /* renamed from: com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureViewModel$onRecordingStarted$1$invokeSuspend$$inlined$filter$1$2$1, reason: invalid class name */
                    public static final class AnonymousClass1 extends ContinuationImpl {
                        Object L$0;
                        Object L$1;
                        int label;
                        /* synthetic */ Object result;

                        public AnonymousClass1(Continuation continuation) {
                            super(continuation);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object obj) {
                            this.result = obj;
                            this.label |= Integer.MIN_VALUE;
                            return AnonymousClass2.this.emit(null, this);
                        }
                    }

                    public AnonymousClass2(FlowCollector flowCollector, VideoCaptureConfig videoCaptureConfig) {
                        this.$this_unsafeFlow = flowCollector;
                        this.$videoCaptureConfig$inlined = videoCaptureConfig;
                    }

                    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct add '--show-bad-code' argument
                    */
                    public final java.lang.Object emit(java.lang.Object r9, kotlin.coroutines.Continuation r10) {
                        /*
                            r8 = this;
                            boolean r0 = r10 instanceof com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureViewModel$onRecordingStarted$1$invokeSuspend$$inlined$filter$1.AnonymousClass2.AnonymousClass1
                            if (r0 == 0) goto L13
                            r0 = r10
                            com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureViewModel$onRecordingStarted$1$invokeSuspend$$inlined$filter$1$2$1 r0 = (com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureViewModel$onRecordingStarted$1$invokeSuspend$$inlined$filter$1.AnonymousClass2.AnonymousClass1) r0
                            int r1 = r0.label
                            r2 = -2147483648(0xffffffff80000000, float:-0.0)
                            r3 = r1 & r2
                            if (r3 == 0) goto L13
                            int r1 = r1 - r2
                            r0.label = r1
                            goto L18
                        L13:
                            com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureViewModel$onRecordingStarted$1$invokeSuspend$$inlined$filter$1$2$1 r0 = new com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureViewModel$onRecordingStarted$1$invokeSuspend$$inlined$filter$1$2$1
                            r0.<init>(r10)
                        L18:
                            java.lang.Object r10 = r0.result
                            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                            int r2 = r0.label
                            r3 = 1
                            if (r2 == 0) goto L31
                            if (r2 != r3) goto L29
                            kotlin.ResultKt.throwOnFailure(r10)
                            goto L50
                        L29:
                            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
                            r9.<init>(r10)
                            throw r9
                        L31:
                            kotlin.ResultKt.throwOnFailure(r10)
                            kotlinx.coroutines.flow.FlowCollector r10 = r8.$this_unsafeFlow
                            r2 = r9
                            java.lang.Number r2 = (java.lang.Number) r2
                            long r4 = r2.longValue()
                            com.onfido.android.sdk.capture.ui.camera.capture.VideoCaptureConfig r2 = r8.$videoCaptureConfig$inlined
                            long r6 = r2.getOutOfStorageThreshold()
                            int r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
                            if (r2 >= 0) goto L50
                            r0.label = r3
                            java.lang.Object r9 = r10.emit(r9, r0)
                            if (r9 != r1) goto L50
                            return r1
                        L50:
                            kotlin.Unit r9 = kotlin.Unit.INSTANCE
                            return r9
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureViewModel$onRecordingStarted$1$invokeSuspend$$inlined$filter$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                    }
                }

                @Override // kotlinx.coroutines.flow.Flow
                public Object collect(FlowCollector<? super Long> flowCollector, Continuation continuation) {
                    Object objCollect = flowM7628catch.collect(new AnonymousClass2(flowCollector, videoCaptureConfig), continuation);
                    return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
                }
            }, 1), this.this$0.dispatchersProvider.mo5607getDefault());
            final LivenessCaptureViewModel livenessCaptureViewModel = this.this$0;
            FlowCollector flowCollector = new FlowCollector() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureViewModel$onRecordingStarted$1.4
                public final Object emit(long j, Continuation<? super Unit> continuation) {
                    livenessCaptureViewModel.getStorageThresholdReachedLiveData$onfido_capture_sdk_core_release().postValue(Boxing.boxBoolean(true));
                    return Unit.INSTANCE;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit(((Number) obj2).longValue(), (Continuation<? super Unit>) continuation);
                }
            };
            this.label = 1;
            if (flowFlowOn.collect(flowCollector, this) == coroutine_suspended) {
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
        return ((LivenessCaptureViewModel$onRecordingStarted$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
