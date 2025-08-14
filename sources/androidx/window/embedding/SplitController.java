package androidx.window.embedding;

import android.app.Activity;
import android.content.Context;
import androidx.core.util.Consumer;
import androidx.profileinstaller.ProfileInstallReceiver$$ExternalSyntheticLambda0;
import io.sentry.protocol.SentryStackFrame;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.ExecutorsKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: SplitController.kt */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 $2\u00020\u0001:\u0002$%B\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J,\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0012\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0007H\u0007J\b\u0010\u0018\u001a\u00020\u0012H\u0007J\b\u0010\u0019\u001a\u00020\u001aH\u0007J\b\u0010\u001b\u001a\u00020\u001aH\u0007J\u001c\u0010\u001c\u001a\u00020\u00122\u0012\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0007H\u0007J\u001c\u0010\u001d\u001a\u00020\u00122\u0012\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020!0\u001fH\u0007J\u001a\u0010\"\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0#2\u0006\u0010\u0013\u001a\u00020\u0014R(\u0010\u0005\u001a\u001a\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0007\u0012\u0004\u0012\u00020\n0\u00068\u0002X\u0083\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\r\u001a\u00020\u000e8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010¨\u0006&"}, d2 = {"Landroidx/window/embedding/SplitController;", "", "embeddingBackend", "Landroidx/window/embedding/EmbeddingBackend;", "(Landroidx/window/embedding/EmbeddingBackend;)V", "consumerToJobMap", "", "Landroidx/core/util/Consumer;", "", "Landroidx/window/embedding/SplitInfo;", "Lkotlinx/coroutines/Job;", SentryStackFrame.JsonKeys.LOCK, "Ljava/util/concurrent/locks/ReentrantLock;", "splitSupportStatus", "Landroidx/window/embedding/SplitController$SplitSupportStatus;", "getSplitSupportStatus", "()Landroidx/window/embedding/SplitController$SplitSupportStatus;", "addSplitListener", "", "activity", "Landroid/app/Activity;", "executor", "Ljava/util/concurrent/Executor;", "consumer", "clearSplitAttributesCalculator", "isSplitAttributesCalculatorSupported", "", "isSplitSupported", "removeSplitListener", "setSplitAttributesCalculator", "calculator", "Lkotlin/Function1;", "Landroidx/window/embedding/SplitAttributesCalculatorParams;", "Landroidx/window/embedding/SplitAttributes;", "splitInfoList", "Lkotlinx/coroutines/flow/Flow;", "Companion", "SplitSupportStatus", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class SplitController {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final boolean sDebug = false;
    private final Map<Consumer<List<SplitInfo>>, Job> consumerToJobMap;
    private final EmbeddingBackend embeddingBackend;
    private final ReentrantLock lock;

    @JvmStatic
    public static final SplitController getInstance(Context context) {
        return INSTANCE.getInstance(context);
    }

    public SplitController(EmbeddingBackend embeddingBackend) {
        Intrinsics.checkNotNullParameter(embeddingBackend, "embeddingBackend");
        this.embeddingBackend = embeddingBackend;
        this.lock = new ReentrantLock();
        this.consumerToJobMap = new LinkedHashMap();
    }

    @Deprecated(message = "Replace to provide Flow API to get SplitInfo list", replaceWith = @ReplaceWith(expression = "splitInfoList", imports = {"androidx.window.embedding.SplitController"}))
    public final void addSplitListener(Activity activity, Executor executor, Consumer<List<SplitInfo>> consumer) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(executor, "executor");
        Intrinsics.checkNotNullParameter(consumer, "consumer");
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            if (this.consumerToJobMap.get(consumer) != null) {
                return;
            }
            this.consumerToJobMap.put(consumer, BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(ExecutorsKt.from(executor)), null, null, new SplitController$addSplitListener$1$1(this, activity, consumer, null), 3, null));
            Unit unit = Unit.INSTANCE;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Deprecated(message = "Replace to provide Flow API to get SplitInfo list", replaceWith = @ReplaceWith(expression = "splitInfoList", imports = {"androidx.window.embedding.SplitController"}))
    public final void removeSplitListener(Consumer<List<SplitInfo>> consumer) {
        Intrinsics.checkNotNullParameter(consumer, "consumer");
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            Job job = this.consumerToJobMap.get(consumer);
            if (job != null) {
                Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
            }
            this.consumerToJobMap.remove(consumer);
        } finally {
            reentrantLock.unlock();
        }
    }

    /* compiled from: SplitController.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/channels/ProducerScope;", "", "Landroidx/window/embedding/SplitInfo;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.window.embedding.SplitController$splitInfoList$1", f = "SplitController.kt", i = {}, l = {127}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: androidx.window.embedding.SplitController$splitInfoList$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<ProducerScope<? super List<? extends SplitInfo>>, Continuation<? super Unit>, Object> {
        final /* synthetic */ Activity $activity;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Activity activity, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$activity = activity;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = SplitController.this.new AnonymousClass1(this.$activity, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(ProducerScope<? super List<? extends SplitInfo>> producerScope, Continuation<? super Unit> continuation) {
            return invoke2((ProducerScope<? super List<SplitInfo>>) producerScope, continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(ProducerScope<? super List<SplitInfo>> producerScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                final ProducerScope producerScope = (ProducerScope) this.L$0;
                final Consumer<List<SplitInfo>> consumer = new Consumer() { // from class: androidx.window.embedding.SplitController$splitInfoList$1$$ExternalSyntheticLambda0
                    @Override // androidx.core.util.Consumer
                    public final void accept(Object obj2) {
                        producerScope.mo7599trySendJP2dKIU((List) obj2);
                    }
                };
                SplitController.this.embeddingBackend.addSplitListenerForActivity(this.$activity, new ProfileInstallReceiver$$ExternalSyntheticLambda0(), consumer);
                final SplitController splitController = SplitController.this;
                this.label = 1;
                if (ProduceKt.awaitClose(producerScope, new Function0<Unit>() { // from class: androidx.window.embedding.SplitController.splitInfoList.1.2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        splitController.embeddingBackend.removeSplitListenerForActivity(consumer);
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

    public final Flow<List<SplitInfo>> splitInfoList(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        return FlowKt.callbackFlow(new AnonymousClass1(activity, null));
    }

    @Deprecated(message = "Use splitSupportStatus instead", replaceWith = @ReplaceWith(expression = "splitSupportStatus", imports = {}))
    public final boolean isSplitSupported() {
        return Intrinsics.areEqual(getSplitSupportStatus(), SplitSupportStatus.SPLIT_AVAILABLE);
    }

    public final SplitSupportStatus getSplitSupportStatus() {
        return this.embeddingBackend.getSplitSupportStatus();
    }

    public final void setSplitAttributesCalculator(Function1<? super SplitAttributesCalculatorParams, SplitAttributes> calculator) {
        Intrinsics.checkNotNullParameter(calculator, "calculator");
        this.embeddingBackend.setSplitAttributesCalculator(calculator);
    }

    public final void clearSplitAttributesCalculator() {
        this.embeddingBackend.clearSplitAttributesCalculator();
    }

    public final boolean isSplitAttributesCalculatorSupported() {
        return this.embeddingBackend.isSplitAttributesCalculatorSupported();
    }

    /* compiled from: SplitController.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Landroidx/window/embedding/SplitController$SplitSupportStatus;", "", "rawValue", "", "(I)V", "toString", "", "Companion", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class SplitSupportStatus {
        private final int rawValue;
        public static final SplitSupportStatus SPLIT_AVAILABLE = new SplitSupportStatus(0);
        public static final SplitSupportStatus SPLIT_UNAVAILABLE = new SplitSupportStatus(1);
        public static final SplitSupportStatus SPLIT_ERROR_PROPERTY_NOT_DECLARED = new SplitSupportStatus(2);

        public String toString() {
            int i = this.rawValue;
            return i != 0 ? i != 1 ? i != 2 ? "UNKNOWN" : "SplitSupportStatus: ERROR_SPLIT_PROPERTY_NOT_DECLARED" : "SplitSupportStatus: UNAVAILABLE" : "SplitSupportStatus: AVAILABLE";
        }

        private SplitSupportStatus(int i) {
            this.rawValue = i;
        }
    }

    /* compiled from: SplitController.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Landroidx/window/embedding/SplitController$Companion;", "", "()V", "sDebug", "", "getInstance", "Landroidx/window/embedding/SplitController;", "context", "Landroid/content/Context;", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final SplitController getInstance(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return new SplitController(EmbeddingBackend.INSTANCE.getInstance(context));
        }
    }
}
