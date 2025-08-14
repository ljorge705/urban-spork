package androidx.lifecycle;

import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.exifinterface.media.ExifInterface;
import androidx.room.RoomTrackingLiveData;
import io.sentry.ProfilingTraceData;
import java.time.Duration;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.StateFlow;
import org.jmrtd.lds.LDSFile;

/* compiled from: FlowLiveData.kt */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\u001a\u001c\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003\u001a0\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007\u001a2\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\nH\u0007¨\u0006\u000b"}, d2 = {"asFlow", "Lkotlinx/coroutines/flow/Flow;", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/lifecycle/LiveData;", "asLiveData", "context", "Lkotlin/coroutines/CoroutineContext;", ProfilingTraceData.TRUNCATION_REASON_TIMEOUT, "Ljava/time/Duration;", "timeoutInMs", "", "lifecycle-livedata-ktx_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class FlowLiveDataConversions {
    public static final <T> LiveData<T> asLiveData(Flow<? extends T> flow) {
        Intrinsics.checkNotNullParameter(flow, "<this>");
        return asLiveData$default(flow, (CoroutineContext) null, 0L, 3, (Object) null);
    }

    public static final <T> LiveData<T> asLiveData(Flow<? extends T> flow, CoroutineContext context) {
        Intrinsics.checkNotNullParameter(flow, "<this>");
        Intrinsics.checkNotNullParameter(context, "context");
        return asLiveData$default(flow, context, 0L, 2, (Object) null);
    }

    public static /* synthetic */ LiveData asLiveData$default(Flow flow, CoroutineContext coroutineContext, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        if ((i & 2) != 0) {
            j = 5000;
        }
        return asLiveData(flow, coroutineContext, j);
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* compiled from: FlowLiveData.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/lifecycle/LiveDataScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.lifecycle.FlowLiveDataConversions$asLiveData$1", f = "FlowLiveData.kt", i = {}, l = {80}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: androidx.lifecycle.FlowLiveDataConversions$asLiveData$1, reason: invalid class name and case insensitive filesystem */
    static final class C05021<T> extends SuspendLambda implements Function2<LiveDataScope<T>, Continuation<? super Unit>, Object> {
        final /* synthetic */ Flow<T> $this_asLiveData;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C05021(Flow<? extends T> flow, Continuation<? super C05021> continuation) {
            super(2, continuation);
            this.$this_asLiveData = flow;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C05021 c05021 = new C05021(this.$this_asLiveData, continuation);
            c05021.L$0 = obj;
            return c05021;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(LiveDataScope<T> liveDataScope, Continuation<? super Unit> continuation) {
            return ((C05021) create(liveDataScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                final LiveDataScope liveDataScope = (LiveDataScope) this.L$0;
                this.label = 1;
                if (this.$this_asLiveData.collect(new FlowCollector() { // from class: androidx.lifecycle.FlowLiveDataConversions.asLiveData.1.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(T t, Continuation<? super Unit> continuation) {
                        Object objEmit = liveDataScope.emit(t, continuation);
                        return objEmit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objEmit : Unit.INSTANCE;
                    }
                }, this) == coroutine_suspended) {
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
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <T> LiveData<T> asLiveData(Flow<? extends T> flow, CoroutineContext context, long j) {
        Intrinsics.checkNotNullParameter(flow, "<this>");
        Intrinsics.checkNotNullParameter(context, "context");
        RoomTrackingLiveData roomTrackingLiveData = (LiveData<T>) CoroutineLiveDataKt.liveData(context, j, new C05021(flow, null));
        if (flow instanceof StateFlow) {
            if (ArchTaskExecutor.getInstance().isMainThread()) {
                roomTrackingLiveData.setValue(((StateFlow) flow).getValue());
            } else {
                roomTrackingLiveData.postValue(((StateFlow) flow).getValue());
            }
        }
        return roomTrackingLiveData;
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* compiled from: FlowLiveData.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/channels/ProducerScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.lifecycle.FlowLiveDataConversions$asFlow$1", f = "FlowLiveData.kt", i = {0, 0}, l = {LDSFile.EF_DG14_TAG, 114}, m = "invokeSuspend", n = {"$this$callbackFlow", "observer"}, s = {"L$0", "L$1"})
    /* renamed from: androidx.lifecycle.FlowLiveDataConversions$asFlow$1, reason: invalid class name */
    static final class AnonymousClass1<T> extends SuspendLambda implements Function2<ProducerScope<? super T>, Continuation<? super Unit>, Object> {
        final /* synthetic */ LiveData<T> $this_asFlow;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(LiveData<T> liveData, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$this_asFlow = liveData;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$this_asFlow, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(ProducerScope<? super T> producerScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            final Observer observer;
            ProducerScope producerScope;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                final ProducerScope producerScope2 = (ProducerScope) this.L$0;
                observer = new Observer() { // from class: androidx.lifecycle.FlowLiveDataConversions$asFlow$1$$ExternalSyntheticLambda0
                    @Override // androidx.lifecycle.Observer
                    public final void onChanged(Object obj2) {
                        producerScope2.mo7599trySendJP2dKIU(obj2);
                    }
                };
                this.L$0 = producerScope2;
                this.L$1 = observer;
                this.label = 1;
                if (BuildersKt.withContext(Dispatchers.getMain().getImmediate(), new C00931(this.$this_asFlow, observer, null), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                producerScope = producerScope2;
            } else {
                if (i != 1) {
                    if (i != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                }
                observer = (Observer) this.L$1;
                producerScope = (ProducerScope) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            final LiveData<T> liveData = this.$this_asFlow;
            this.L$0 = null;
            this.L$1 = null;
            this.label = 2;
            if (ProduceKt.awaitClose(producerScope, new Function0<Unit>() { // from class: androidx.lifecycle.FlowLiveDataConversions.asFlow.1.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* compiled from: FlowLiveData.kt */
                @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                @DebugMetadata(c = "androidx.lifecycle.FlowLiveDataConversions$asFlow$1$2$1", f = "FlowLiveData.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: androidx.lifecycle.FlowLiveDataConversions$asFlow$1$2$1, reason: invalid class name and collision with other inner class name */
                static final class C00941 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    final /* synthetic */ Observer<T> $observer;
                    final /* synthetic */ LiveData<T> $this_asFlow;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    C00941(LiveData<T> liveData, Observer<T> observer, Continuation<? super C00941> continuation) {
                        super(2, continuation);
                        this.$this_asFlow = liveData;
                        this.$observer = observer;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        return new C00941(this.$this_asFlow, this.$observer, continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((C00941) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        if (this.label == 0) {
                            ResultKt.throwOnFailure(obj);
                            this.$this_asFlow.removeObserver(this.$observer);
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain().getImmediate(), null, new C00941(liveData, observer, null), 2, null);
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }

        /* compiled from: FlowLiveData.kt */
        @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        @DebugMetadata(c = "androidx.lifecycle.FlowLiveDataConversions$asFlow$1$1", f = "FlowLiveData.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: androidx.lifecycle.FlowLiveDataConversions$asFlow$1$1, reason: invalid class name and collision with other inner class name */
        static final class C00931 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ Observer<T> $observer;
            final /* synthetic */ LiveData<T> $this_asFlow;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00931(LiveData<T> liveData, Observer<T> observer, Continuation<? super C00931> continuation) {
                super(2, continuation);
                this.$this_asFlow = liveData;
                this.$observer = observer;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C00931(this.$this_asFlow, this.$observer, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C00931) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    this.$this_asFlow.observeForever(this.$observer);
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    public static final <T> Flow<T> asFlow(LiveData<T> liveData) {
        Intrinsics.checkNotNullParameter(liveData, "<this>");
        return FlowKt.conflate(FlowKt.callbackFlow(new AnonymousClass1(liveData, null)));
    }

    public static /* synthetic */ LiveData asLiveData$default(Flow flow, CoroutineContext coroutineContext, Duration duration, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        return asLiveData(flow, coroutineContext, duration);
    }

    public static final <T> LiveData<T> asLiveData(Flow<? extends T> flow, CoroutineContext context, Duration timeout) {
        Intrinsics.checkNotNullParameter(flow, "<this>");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(timeout, "timeout");
        return asLiveData(flow, context, Api26Impl.INSTANCE.toMillis(timeout));
    }
}
