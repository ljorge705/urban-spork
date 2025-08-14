package androidx.compose.foundation;

import androidx.camera.view.PreviewView$1$$ExternalSyntheticBackportWithForwarding0;
import androidx.exifinterface.media.ExifInterface;
import com.oblador.keychain.KeychainModule;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;

/* compiled from: MutatorMutex.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001:\u0001\u001aB\u0005¢\u0006\u0002\u0010\u0002J?\u0010\t\u001a\u0002H\n\"\u0004\b\u0000\u0010\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\u001c\u0010\r\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\n0\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000eH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0010JX\u0010\u0011\u001a\u0002H\n\"\u0004\b\u0000\u0010\u0012\"\u0004\b\u0001\u0010\n2\u0006\u0010\u0013\u001a\u0002H\u00122\b\b\u0002\u0010\u000b\u001a\u00020\f2'\u0010\r\u001a#\b\u0001\u0012\u0004\u0012\u0002H\u0012\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\n0\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0014¢\u0006\u0002\b\u0015H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0016J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0005H\u0002R\"\u0010\u0003\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004j\n\u0012\u0006\u0012\u0004\u0018\u00010\u0005`\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001b"}, d2 = {"Landroidx/compose/foundation/MutatorMutex;", "", "()V", "currentMutator", "Ljava/util/concurrent/atomic/AtomicReference;", "Landroidx/compose/foundation/MutatorMutex$Mutator;", "Landroidx/compose/foundation/AtomicReference;", "mutex", "Lkotlinx/coroutines/sync/Mutex;", "mutate", "R", "priority", "Landroidx/compose/foundation/MutatePriority;", "block", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "(Landroidx/compose/foundation/MutatePriority;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "mutateWith", ExifInterface.GPS_DIRECTION_TRUE, "receiver", "Lkotlin/Function2;", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/Object;Landroidx/compose/foundation/MutatePriority;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "tryMutateOrCancel", "", "mutator", "Mutator", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class MutatorMutex {
    public static final int $stable = 0;
    private final AtomicReference<Mutator> currentMutator = new AtomicReference<>(null);
    private final Mutex mutex = MutexKt.Mutex$default(false, 1, null);

    /* compiled from: MutatorMutex.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0000J\u0006\u0010\u000e\u001a\u00020\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0010"}, d2 = {"Landroidx/compose/foundation/MutatorMutex$Mutator;", "", "priority", "Landroidx/compose/foundation/MutatePriority;", "job", "Lkotlinx/coroutines/Job;", "(Landroidx/compose/foundation/MutatePriority;Lkotlinx/coroutines/Job;)V", "getJob", "()Lkotlinx/coroutines/Job;", "getPriority", "()Landroidx/compose/foundation/MutatePriority;", "canInterrupt", "", "other", KeychainModule.AuthPromptOptions.CANCEL, "", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    private static final class Mutator {
        private final Job job;
        private final MutatePriority priority;

        public final Job getJob() {
            return this.job;
        }

        public final MutatePriority getPriority() {
            return this.priority;
        }

        public Mutator(MutatePriority priority, Job job) {
            Intrinsics.checkNotNullParameter(priority, "priority");
            Intrinsics.checkNotNullParameter(job, "job");
            this.priority = priority;
            this.job = job;
        }

        public final boolean canInterrupt(Mutator other) {
            Intrinsics.checkNotNullParameter(other, "other");
            return this.priority.compareTo(other.priority) >= 0;
        }

        public final void cancel() {
            Job.DefaultImpls.cancel$default(this.job, (CancellationException) null, 1, (Object) null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void tryMutateOrCancel(Mutator mutator) {
        Mutator mutator2;
        do {
            mutator2 = this.currentMutator.get();
            if (mutator2 != null && !mutator.canInterrupt(mutator2)) {
                throw new CancellationException("Current mutation had a higher priority");
            }
        } while (!PreviewView$1$$ExternalSyntheticBackportWithForwarding0.m(this.currentMutator, mutator2, mutator));
        if (mutator2 != null) {
            mutator2.cancel();
        }
    }

    public static /* synthetic */ Object mutate$default(MutatorMutex mutatorMutex, MutatePriority mutatePriority, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            mutatePriority = MutatePriority.Default;
        }
        return mutatorMutex.mutate(mutatePriority, function1, continuation);
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* compiled from: MutatorMutex.kt */
    @Metadata(d1 = {"\u0000\b\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "R", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.MutatorMutex$mutate$2", f = "MutatorMutex.kt", i = {0, 0, 1, 1}, l = {173, 119}, m = "invokeSuspend", n = {"mutator", "$this$withLock_u24default$iv", "mutator", "$this$withLock_u24default$iv"}, s = {"L$0", "L$1", "L$0", "L$1"})
    /* renamed from: androidx.compose.foundation.MutatorMutex$mutate$2, reason: invalid class name */
    static final class AnonymousClass2<R> extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super R>, Object> {
        final /* synthetic */ Function1<Continuation<? super R>, Object> $block;
        final /* synthetic */ MutatePriority $priority;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        final /* synthetic */ MutatorMutex this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass2(MutatePriority mutatePriority, MutatorMutex mutatorMutex, Function1<? super Continuation<? super R>, ? extends Object> function1, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$priority = mutatePriority;
            this.this$0 = mutatorMutex;
            this.$block = function1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$priority, this.this$0, this.$block, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super R> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Type inference failed for: r1v0, types: [int, kotlinx.coroutines.sync.Mutex] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Mutex mutex;
            Function1<Continuation<? super R>, Object> function1;
            Mutator mutator;
            MutatorMutex mutatorMutex;
            Mutator mutator2;
            Throwable th;
            MutatorMutex mutatorMutex2;
            Mutex mutex2;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            ?? r1 = this.label;
            try {
                try {
                    if (r1 == 0) {
                        ResultKt.throwOnFailure(obj);
                        CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                        MutatePriority mutatePriority = this.$priority;
                        CoroutineContext.Element element = coroutineScope.getCoroutineContext().get(Job.INSTANCE);
                        Intrinsics.checkNotNull(element);
                        Mutator mutator3 = new Mutator(mutatePriority, (Job) element);
                        this.this$0.tryMutateOrCancel(mutator3);
                        mutex = this.this$0.mutex;
                        Function1<Continuation<? super R>, Object> function12 = this.$block;
                        MutatorMutex mutatorMutex3 = this.this$0;
                        this.L$0 = mutator3;
                        this.L$1 = mutex;
                        this.L$2 = function12;
                        this.L$3 = mutatorMutex3;
                        this.label = 1;
                        if (mutex.lock(null, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        function1 = function12;
                        mutator = mutator3;
                        mutatorMutex = mutatorMutex3;
                    } else {
                        if (r1 != 1) {
                            if (r1 != 2) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            mutatorMutex2 = (MutatorMutex) this.L$2;
                            mutex2 = (Mutex) this.L$1;
                            mutator2 = (Mutator) this.L$0;
                            try {
                                ResultKt.throwOnFailure(obj);
                                PreviewView$1$$ExternalSyntheticBackportWithForwarding0.m(mutatorMutex2.currentMutator, mutator2, null);
                                mutex2.unlock(null);
                                return obj;
                            } catch (Throwable th2) {
                                th = th2;
                                PreviewView$1$$ExternalSyntheticBackportWithForwarding0.m(mutatorMutex2.currentMutator, mutator2, null);
                                throw th;
                            }
                        }
                        mutatorMutex = (MutatorMutex) this.L$3;
                        function1 = (Function1) this.L$2;
                        Mutex mutex3 = (Mutex) this.L$1;
                        mutator = (Mutator) this.L$0;
                        ResultKt.throwOnFailure(obj);
                        mutex = mutex3;
                    }
                    this.L$0 = mutator;
                    this.L$1 = mutex;
                    this.L$2 = mutatorMutex;
                    this.L$3 = null;
                    this.label = 2;
                    Object objInvoke = function1.invoke(this);
                    if (objInvoke == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    mutatorMutex2 = mutatorMutex;
                    mutex2 = mutex;
                    obj = objInvoke;
                    mutator2 = mutator;
                    PreviewView$1$$ExternalSyntheticBackportWithForwarding0.m(mutatorMutex2.currentMutator, mutator2, null);
                    mutex2.unlock(null);
                    return obj;
                } catch (Throwable th3) {
                    mutator2 = mutator;
                    th = th3;
                    mutatorMutex2 = mutatorMutex;
                    PreviewView$1$$ExternalSyntheticBackportWithForwarding0.m(mutatorMutex2.currentMutator, mutator2, null);
                    throw th;
                }
            } catch (Throwable th4) {
                r1.unlock(null);
                throw th4;
            }
        }
    }

    public final <R> Object mutate(MutatePriority mutatePriority, Function1<? super Continuation<? super R>, ? extends Object> function1, Continuation<? super R> continuation) {
        return CoroutineScopeKt.coroutineScope(new AnonymousClass2(mutatePriority, this, function1, null), continuation);
    }

    public static /* synthetic */ Object mutateWith$default(MutatorMutex mutatorMutex, Object obj, MutatePriority mutatePriority, Function2 function2, Continuation continuation, int i, Object obj2) {
        if ((i & 2) != 0) {
            mutatePriority = MutatePriority.Default;
        }
        return mutatorMutex.mutateWith(obj, mutatePriority, function2, continuation);
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* compiled from: MutatorMutex.kt */
    @Metadata(d1 = {"\u0000\b\n\u0002\b\u0003\n\u0002\u0018\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0001*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "R", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.MutatorMutex$mutateWith$2", f = "MutatorMutex.kt", i = {0, 0, 1, 1}, l = {173, 160}, m = "invokeSuspend", n = {"mutator", "$this$withLock_u24default$iv", "mutator", "$this$withLock_u24default$iv"}, s = {"L$0", "L$1", "L$0", "L$1"})
    /* renamed from: androidx.compose.foundation.MutatorMutex$mutateWith$2, reason: invalid class name and case insensitive filesystem */
    static final class C02632<R> extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super R>, Object> {
        final /* synthetic */ Function2<T, Continuation<? super R>, Object> $block;
        final /* synthetic */ MutatePriority $priority;
        final /* synthetic */ T $receiver;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        final /* synthetic */ MutatorMutex this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C02632(MutatePriority mutatePriority, MutatorMutex mutatorMutex, Function2<? super T, ? super Continuation<? super R>, ? extends Object> function2, T t, Continuation<? super C02632> continuation) {
            super(2, continuation);
            this.$priority = mutatePriority;
            this.this$0 = mutatorMutex;
            this.$block = function2;
            this.$receiver = t;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C02632 c02632 = new C02632(this.$priority, this.this$0, this.$block, this.$receiver, continuation);
            c02632.L$0 = obj;
            return c02632;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super R> continuation) {
            return ((C02632) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Type inference failed for: r1v0, types: [int, kotlinx.coroutines.sync.Mutex] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Mutex mutex;
            Function2 function2;
            Object obj2;
            Mutator mutator;
            MutatorMutex mutatorMutex;
            Mutator mutator2;
            Throwable th;
            MutatorMutex mutatorMutex2;
            Mutex mutex2;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            ?? r1 = this.label;
            try {
                try {
                    if (r1 == 0) {
                        ResultKt.throwOnFailure(obj);
                        CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                        MutatePriority mutatePriority = this.$priority;
                        CoroutineContext.Element element = coroutineScope.getCoroutineContext().get(Job.INSTANCE);
                        Intrinsics.checkNotNull(element);
                        Mutator mutator3 = new Mutator(mutatePriority, (Job) element);
                        this.this$0.tryMutateOrCancel(mutator3);
                        mutex = this.this$0.mutex;
                        function2 = this.$block;
                        Object obj3 = this.$receiver;
                        MutatorMutex mutatorMutex3 = this.this$0;
                        this.L$0 = mutator3;
                        this.L$1 = mutex;
                        this.L$2 = function2;
                        this.L$3 = obj3;
                        this.L$4 = mutatorMutex3;
                        this.label = 1;
                        if (mutex.lock(null, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        obj2 = obj3;
                        mutator = mutator3;
                        mutatorMutex = mutatorMutex3;
                    } else {
                        if (r1 != 1) {
                            if (r1 != 2) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            mutatorMutex2 = (MutatorMutex) this.L$2;
                            mutex2 = (Mutex) this.L$1;
                            mutator2 = (Mutator) this.L$0;
                            try {
                                ResultKt.throwOnFailure(obj);
                                PreviewView$1$$ExternalSyntheticBackportWithForwarding0.m(mutatorMutex2.currentMutator, mutator2, null);
                                mutex2.unlock(null);
                                return obj;
                            } catch (Throwable th2) {
                                th = th2;
                                PreviewView$1$$ExternalSyntheticBackportWithForwarding0.m(mutatorMutex2.currentMutator, mutator2, null);
                                throw th;
                            }
                        }
                        mutatorMutex = (MutatorMutex) this.L$4;
                        obj2 = this.L$3;
                        function2 = (Function2) this.L$2;
                        Mutex mutex3 = (Mutex) this.L$1;
                        mutator = (Mutator) this.L$0;
                        ResultKt.throwOnFailure(obj);
                        mutex = mutex3;
                    }
                    this.L$0 = mutator;
                    this.L$1 = mutex;
                    this.L$2 = mutatorMutex;
                    this.L$3 = null;
                    this.L$4 = null;
                    this.label = 2;
                    Object objInvoke = function2.invoke(obj2, this);
                    if (objInvoke == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    mutatorMutex2 = mutatorMutex;
                    mutex2 = mutex;
                    obj = objInvoke;
                    mutator2 = mutator;
                    PreviewView$1$$ExternalSyntheticBackportWithForwarding0.m(mutatorMutex2.currentMutator, mutator2, null);
                    mutex2.unlock(null);
                    return obj;
                } catch (Throwable th3) {
                    mutator2 = mutator;
                    th = th3;
                    mutatorMutex2 = mutatorMutex;
                    PreviewView$1$$ExternalSyntheticBackportWithForwarding0.m(mutatorMutex2.currentMutator, mutator2, null);
                    throw th;
                }
            } catch (Throwable th4) {
                r1.unlock(null);
                throw th4;
            }
        }
    }

    public final <T, R> Object mutateWith(T t, MutatePriority mutatePriority, Function2<? super T, ? super Continuation<? super R>, ? extends Object> function2, Continuation<? super R> continuation) {
        return CoroutineScopeKt.coroutineScope(new C02632(mutatePriority, this, function2, t, null), continuation);
    }
}
