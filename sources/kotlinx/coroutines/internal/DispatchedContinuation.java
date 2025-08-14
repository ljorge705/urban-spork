package kotlinx.coroutines.internal;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import androidx.exifinterface.media.ExifInterface;
import com.onfido.android.sdk.capture.core.OnfidoLauncher;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.Volatile;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CompletedWithCancellation;
import kotlinx.coroutines.CompletionStateKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.DispatchedTask;
import kotlinx.coroutines.EventLoop;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.ThreadLocalEventLoop;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* compiled from: DispatchedContinuation.kt */
@Metadata(d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0001\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00060\u0003j\u0002`\u00042\b\u0012\u0004\u0012\u0002H\u00010\u0005B\u001b\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\u0002\u0010\tJ\r\u0010\u001f\u001a\u00020 H\u0000¢\u0006\u0002\b!J\u001f\u0010\"\u001a\u00020 2\b\u0010#\u001a\u0004\u0018\u00010\f2\u0006\u0010$\u001a\u00020%H\u0010¢\u0006\u0002\b&J\u0015\u0010'\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u001cH\u0000¢\u0006\u0002\b(J\u001f\u0010)\u001a\u00020 2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010*\u001a\u00028\u0000H\u0000¢\u0006\u0004\b+\u0010,J\u0010\u0010-\u001a\n\u0018\u00010.j\u0004\u0018\u0001`/H\u0016J\r\u00100\u001a\u000201H\u0000¢\u0006\u0002\b2J\u0015\u00103\u001a\u0002012\u0006\u0010$\u001a\u00020%H\u0000¢\u0006\u0002\b4J\r\u00105\u001a\u00020 H\u0000¢\u0006\u0002\b6JH\u00107\u001a\u00020 2\f\u00108\u001a\b\u0012\u0004\u0012\u00028\u0000092%\b\b\u0010:\u001a\u001f\u0012\u0013\u0012\u00110%¢\u0006\f\b<\u0012\b\b=\u0012\u0004\b\b($\u0012\u0004\u0012\u00020 \u0018\u00010;H\u0080\bø\u0001\u0000¢\u0006\u0004\b>\u0010?J\u0018\u0010@\u001a\u0002012\b\u0010A\u001a\u0004\u0018\u00010\fH\u0080\b¢\u0006\u0002\bBJ!\u0010C\u001a\u00020 2\f\u00108\u001a\b\u0012\u0004\u0012\u00028\u000009H\u0080\bø\u0001\u0000¢\u0006\u0004\bD\u0010EJ\u001e\u0010F\u001a\u00020 2\f\u00108\u001a\b\u0012\u0004\u0012\u00028\u000009H\u0016ø\u0001\u0000¢\u0006\u0002\u0010EJ\u000f\u0010G\u001a\u0004\u0018\u00010\fH\u0010¢\u0006\u0002\bHJ\b\u0010I\u001a\u00020JH\u0016J\u001b\u0010K\u001a\u0004\u0018\u00010%2\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030LH\u0000¢\u0006\u0002\bMR\u0011\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000bX\u0082\u0004R\u001a\u0010\r\u001a\u0004\u0018\u00010\f8\u0000@\u0000X\u0081\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0010\u001a\n\u0018\u00010\u0003j\u0004\u0018\u0001`\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0012\u0010\u0013\u001a\u00020\u0014X\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u00020\f8\u0000X\u0081\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00000\u00058PX\u0090\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0010\u0010\u0006\u001a\u00020\u00078\u0000X\u0081\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u001c8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001e\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006N"}, d2 = {"Lkotlinx/coroutines/internal/DispatchedContinuation;", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/DispatchedTask;", "Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "Lkotlinx/coroutines/internal/CoroutineStackFrame;", "Lkotlin/coroutines/Continuation;", "dispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "continuation", "(Lkotlinx/coroutines/CoroutineDispatcher;Lkotlin/coroutines/Continuation;)V", "_reusableCancellableContinuation", "Lkotlinx/atomicfu/AtomicRef;", "", "_state", "get_state$kotlinx_coroutines_core$annotations", "()V", "callerFrame", "getCallerFrame", "()Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "context", "Lkotlin/coroutines/CoroutineContext;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "countOrElement", "delegate", "getDelegate$kotlinx_coroutines_core", "()Lkotlin/coroutines/Continuation;", "reusableCancellableContinuation", "Lkotlinx/coroutines/CancellableContinuationImpl;", "getReusableCancellableContinuation", "()Lkotlinx/coroutines/CancellableContinuationImpl;", "awaitReusability", "", "awaitReusability$kotlinx_coroutines_core", "cancelCompletedResult", "takenState", "cause", "", "cancelCompletedResult$kotlinx_coroutines_core", "claimReusableCancellableContinuation", "claimReusableCancellableContinuation$kotlinx_coroutines_core", "dispatchYield", "value", "dispatchYield$kotlinx_coroutines_core", "(Lkotlin/coroutines/CoroutineContext;Ljava/lang/Object;)V", "getStackTraceElement", "Ljava/lang/StackTraceElement;", "Lkotlinx/coroutines/internal/StackTraceElement;", "isReusable", "", "isReusable$kotlinx_coroutines_core", "postponeCancellation", "postponeCancellation$kotlinx_coroutines_core", "release", "release$kotlinx_coroutines_core", "resumeCancellableWith", OnfidoLauncher.KEY_RESULT, "Lkotlin/Result;", "onCancellation", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "resumeCancellableWith$kotlinx_coroutines_core", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "resumeCancelled", "state", "resumeCancelled$kotlinx_coroutines_core", "resumeUndispatchedWith", "resumeUndispatchedWith$kotlinx_coroutines_core", "(Ljava/lang/Object;)V", "resumeWith", "takeState", "takeState$kotlinx_coroutines_core", "toString", "", "tryReleaseClaimedContinuation", "Lkotlinx/coroutines/CancellableContinuation;", "tryReleaseClaimedContinuation$kotlinx_coroutines_core", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class DispatchedContinuation<T> extends DispatchedTask<T> implements CoroutineStackFrame, Continuation<T> {
    private static final AtomicReferenceFieldUpdater _reusableCancellableContinuation$FU = AtomicReferenceFieldUpdater.newUpdater(DispatchedContinuation.class, Object.class, "_reusableCancellableContinuation");

    @Volatile
    private volatile Object _reusableCancellableContinuation;
    public Object _state;
    public final Continuation<T> continuation;
    public final Object countOrElement;
    public final CoroutineDispatcher dispatcher;

    public static /* synthetic */ void get_state$kotlinx_coroutines_core$annotations() {
    }

    private final void loop$atomicfu(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater, Function1<Object, Unit> function1, Object obj) {
        while (true) {
            function1.invoke(atomicReferenceFieldUpdater.get(obj));
        }
    }

    @Override // kotlin.coroutines.Continuation
    public CoroutineContext getContext() {
        return this.continuation.getContext();
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public StackTraceElement getStackTraceElement() {
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public DispatchedContinuation(CoroutineDispatcher coroutineDispatcher, Continuation<? super T> continuation) {
        super(-1);
        this.dispatcher = coroutineDispatcher;
        this.continuation = continuation;
        this._state = DispatchedContinuationKt.UNDEFINED;
        this.countOrElement = ThreadContextKt.threadContextElements(getContext());
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public CoroutineStackFrame getCallerFrame() {
        Continuation<T> continuation = this.continuation;
        if (continuation instanceof CoroutineStackFrame) {
            return (CoroutineStackFrame) continuation;
        }
        return null;
    }

    private final CancellableContinuationImpl<?> getReusableCancellableContinuation() {
        Object obj = _reusableCancellableContinuation$FU.get(this);
        if (obj instanceof CancellableContinuationImpl) {
            return (CancellableContinuationImpl) obj;
        }
        return null;
    }

    public final boolean isReusable$kotlinx_coroutines_core() {
        return _reusableCancellableContinuation$FU.get(this) != null;
    }

    public final void awaitReusability$kotlinx_coroutines_core() {
        while (_reusableCancellableContinuation$FU.get(this) == DispatchedContinuationKt.REUSABLE_CLAIMED) {
        }
    }

    public final void release$kotlinx_coroutines_core() {
        awaitReusability$kotlinx_coroutines_core();
        CancellableContinuationImpl<?> reusableCancellableContinuation = getReusableCancellableContinuation();
        if (reusableCancellableContinuation != null) {
            reusableCancellableContinuation.detachChild$kotlinx_coroutines_core();
        }
    }

    public final CancellableContinuationImpl<T> claimReusableCancellableContinuation$kotlinx_coroutines_core() {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _reusableCancellableContinuation$FU;
        while (true) {
            Object obj = atomicReferenceFieldUpdater.get(this);
            if (obj == null) {
                _reusableCancellableContinuation$FU.set(this, DispatchedContinuationKt.REUSABLE_CLAIMED);
                return null;
            }
            if (obj instanceof CancellableContinuationImpl) {
                if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_reusableCancellableContinuation$FU, this, obj, DispatchedContinuationKt.REUSABLE_CLAIMED)) {
                    return (CancellableContinuationImpl) obj;
                }
            } else if (obj != DispatchedContinuationKt.REUSABLE_CLAIMED && !(obj instanceof Throwable)) {
                throw new IllegalStateException(("Inconsistent state " + obj).toString());
            }
        }
    }

    public final Throwable tryReleaseClaimedContinuation$kotlinx_coroutines_core(CancellableContinuation<?> continuation) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _reusableCancellableContinuation$FU;
        do {
            Object obj = atomicReferenceFieldUpdater.get(this);
            if (obj != DispatchedContinuationKt.REUSABLE_CLAIMED) {
                if (obj instanceof Throwable) {
                    if (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_reusableCancellableContinuation$FU, this, obj, null)) {
                        throw new IllegalArgumentException("Failed requirement.".toString());
                    }
                    return (Throwable) obj;
                }
                throw new IllegalStateException(("Inconsistent state " + obj).toString());
            }
        } while (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_reusableCancellableContinuation$FU, this, DispatchedContinuationKt.REUSABLE_CLAIMED, continuation));
        return null;
    }

    public final boolean postponeCancellation$kotlinx_coroutines_core(Throwable cause) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _reusableCancellableContinuation$FU;
        while (true) {
            Object obj = atomicReferenceFieldUpdater.get(this);
            if (Intrinsics.areEqual(obj, DispatchedContinuationKt.REUSABLE_CLAIMED)) {
                if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_reusableCancellableContinuation$FU, this, DispatchedContinuationKt.REUSABLE_CLAIMED, cause)) {
                    return true;
                }
            } else {
                if (obj instanceof Throwable) {
                    return true;
                }
                if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_reusableCancellableContinuation$FU, this, obj, null)) {
                    return false;
                }
            }
        }
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public Object takeState$kotlinx_coroutines_core() {
        Object obj = this._state;
        if (DebugKt.getASSERTIONS_ENABLED() && obj == DispatchedContinuationKt.UNDEFINED) {
            throw new AssertionError();
        }
        this._state = DispatchedContinuationKt.UNDEFINED;
        return obj;
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public Continuation<T> getDelegate$kotlinx_coroutines_core() {
        return this;
    }

    @Override // kotlin.coroutines.Continuation
    public void resumeWith(Object result) {
        CoroutineContext context;
        Object objUpdateThreadContext;
        CoroutineContext context2 = this.continuation.getContext();
        Object state$default = CompletionStateKt.toState$default(result, null, 1, null);
        if (this.dispatcher.isDispatchNeeded(context2)) {
            this._state = state$default;
            this.resumeMode = 0;
            this.dispatcher.mo7654dispatch(context2, this);
            return;
        }
        DebugKt.getASSERTIONS_ENABLED();
        EventLoop eventLoop$kotlinx_coroutines_core = ThreadLocalEventLoop.INSTANCE.getEventLoop$kotlinx_coroutines_core();
        if (eventLoop$kotlinx_coroutines_core.isUnconfinedLoopActive()) {
            this._state = state$default;
            this.resumeMode = 0;
            eventLoop$kotlinx_coroutines_core.dispatchUnconfined(this);
            return;
        }
        DispatchedContinuation<T> dispatchedContinuation = this;
        eventLoop$kotlinx_coroutines_core.incrementUseCount(true);
        try {
            context = getContext();
            objUpdateThreadContext = ThreadContextKt.updateThreadContext(context, this.countOrElement);
        } finally {
            try {
            } finally {
            }
        }
        try {
            this.continuation.resumeWith(result);
            Unit unit = Unit.INSTANCE;
            while (eventLoop$kotlinx_coroutines_core.processUnconfinedEvent()) {
            }
        } finally {
            ThreadContextKt.restoreThreadContext(context, objUpdateThreadContext);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x00a0 A[Catch: all -> 0x00c3, DONT_GENERATE, TryCatch #2 {all -> 0x00c3, blocks: (B:9:0x0045, B:11:0x0055, B:13:0x005b, B:26:0x00a6, B:14:0x0078, B:16:0x0088, B:20:0x0095, B:22:0x009a, B:25:0x00a3, B:24:0x00a0, B:30:0x00b1, B:32:0x00b6, B:35:0x00bf, B:36:0x00c2, B:34:0x00bc, B:19:0x008e), top: B:49:0x0045, inners: #0 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void resumeCancellableWith$kotlinx_coroutines_core(java.lang.Object r8, kotlin.jvm.functions.Function1<? super java.lang.Throwable, kotlin.Unit> r9) {
        /*
            r7 = this;
            java.lang.Object r9 = kotlinx.coroutines.CompletionStateKt.toState(r8, r9)
            kotlinx.coroutines.CoroutineDispatcher r0 = r7.dispatcher
            kotlin.coroutines.CoroutineContext r1 = r7.getContext()
            boolean r0 = r0.isDispatchNeeded(r1)
            r1 = 1
            if (r0 == 0) goto L23
            r7._state = r9
            r7.resumeMode = r1
            kotlinx.coroutines.CoroutineDispatcher r8 = r7.dispatcher
            kotlin.coroutines.CoroutineContext r9 = r7.getContext()
            r0 = r7
            java.lang.Runnable r0 = (java.lang.Runnable) r0
            r8.mo7654dispatch(r9, r0)
            goto Ld0
        L23:
            kotlinx.coroutines.DebugKt.getASSERTIONS_ENABLED()
            kotlinx.coroutines.ThreadLocalEventLoop r0 = kotlinx.coroutines.ThreadLocalEventLoop.INSTANCE
            kotlinx.coroutines.EventLoop r0 = r0.getEventLoop$kotlinx_coroutines_core()
            boolean r2 = r0.isUnconfinedLoopActive()
            if (r2 == 0) goto L3e
            r7._state = r9
            r7.resumeMode = r1
            r8 = r7
            kotlinx.coroutines.DispatchedTask r8 = (kotlinx.coroutines.DispatchedTask) r8
            r0.dispatchUnconfined(r8)
            goto Ld0
        L3e:
            r2 = r7
            kotlinx.coroutines.DispatchedTask r2 = (kotlinx.coroutines.DispatchedTask) r2
            r0.incrementUseCount(r1)
            r3 = 0
            kotlin.coroutines.CoroutineContext r4 = r7.getContext()     // Catch: java.lang.Throwable -> Lc3
            kotlinx.coroutines.Job$Key r5 = kotlinx.coroutines.Job.INSTANCE     // Catch: java.lang.Throwable -> Lc3
            kotlin.coroutines.CoroutineContext$Key r5 = (kotlin.coroutines.CoroutineContext.Key) r5     // Catch: java.lang.Throwable -> Lc3
            kotlin.coroutines.CoroutineContext$Element r4 = r4.get(r5)     // Catch: java.lang.Throwable -> Lc3
            kotlinx.coroutines.Job r4 = (kotlinx.coroutines.Job) r4     // Catch: java.lang.Throwable -> Lc3
            if (r4 == 0) goto L78
            boolean r5 = r4.isActive()     // Catch: java.lang.Throwable -> Lc3
            if (r5 != 0) goto L78
            java.util.concurrent.CancellationException r8 = r4.getCancellationException()     // Catch: java.lang.Throwable -> Lc3
            r4 = r8
            java.lang.Throwable r4 = (java.lang.Throwable) r4     // Catch: java.lang.Throwable -> Lc3
            r7.cancelCompletedResult$kotlinx_coroutines_core(r9, r4)     // Catch: java.lang.Throwable -> Lc3
            r9 = r7
            kotlin.coroutines.Continuation r9 = (kotlin.coroutines.Continuation) r9     // Catch: java.lang.Throwable -> Lc3
            kotlin.Result$Companion r4 = kotlin.Result.INSTANCE     // Catch: java.lang.Throwable -> Lc3
            java.lang.Throwable r8 = (java.lang.Throwable) r8     // Catch: java.lang.Throwable -> Lc3
            java.lang.Object r8 = kotlin.ResultKt.createFailure(r8)     // Catch: java.lang.Throwable -> Lc3
            java.lang.Object r8 = kotlin.Result.m6095constructorimpl(r8)     // Catch: java.lang.Throwable -> Lc3
            r9.resumeWith(r8)     // Catch: java.lang.Throwable -> Lc3
            goto La6
        L78:
            kotlin.coroutines.Continuation<T> r9 = r7.continuation     // Catch: java.lang.Throwable -> Lc3
            java.lang.Object r4 = r7.countOrElement     // Catch: java.lang.Throwable -> Lc3
            kotlin.coroutines.CoroutineContext r5 = r9.getContext()     // Catch: java.lang.Throwable -> Lc3
            java.lang.Object r4 = kotlinx.coroutines.internal.ThreadContextKt.updateThreadContext(r5, r4)     // Catch: java.lang.Throwable -> Lc3
            kotlinx.coroutines.internal.Symbol r6 = kotlinx.coroutines.internal.ThreadContextKt.NO_THREAD_ELEMENTS     // Catch: java.lang.Throwable -> Lc3
            if (r4 == r6) goto L8d
            kotlinx.coroutines.UndispatchedCoroutine r9 = kotlinx.coroutines.CoroutineContextKt.updateUndispatchedCompletion(r9, r5, r4)     // Catch: java.lang.Throwable -> Lc3
            goto L8e
        L8d:
            r9 = r3
        L8e:
            kotlin.coroutines.Continuation<T> r6 = r7.continuation     // Catch: java.lang.Throwable -> Lb0
            r6.resumeWith(r8)     // Catch: java.lang.Throwable -> Lb0
            kotlin.Unit r8 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> Lb0
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)     // Catch: java.lang.Throwable -> Lc3
            if (r9 == 0) goto La0
            boolean r8 = r9.clearThreadContext()     // Catch: java.lang.Throwable -> Lc3
            if (r8 == 0) goto La3
        La0:
            kotlinx.coroutines.internal.ThreadContextKt.restoreThreadContext(r5, r4)     // Catch: java.lang.Throwable -> Lc3
        La3:
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)     // Catch: java.lang.Throwable -> Lc3
        La6:
            boolean r8 = r0.processUnconfinedEvent()     // Catch: java.lang.Throwable -> Lc3
            if (r8 != 0) goto La6
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            goto Lca
        Lb0:
            r8 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)     // Catch: java.lang.Throwable -> Lc3
            if (r9 == 0) goto Lbc
            boolean r9 = r9.clearThreadContext()     // Catch: java.lang.Throwable -> Lc3
            if (r9 == 0) goto Lbf
        Lbc:
            kotlinx.coroutines.internal.ThreadContextKt.restoreThreadContext(r5, r4)     // Catch: java.lang.Throwable -> Lc3
        Lbf:
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)     // Catch: java.lang.Throwable -> Lc3
            throw r8     // Catch: java.lang.Throwable -> Lc3
        Lc3:
            r8 = move-exception
            r2.handleFatalException$kotlinx_coroutines_core(r8, r3)     // Catch: java.lang.Throwable -> Ld1
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
        Lca:
            r0.decrementUseCount(r1)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
        Ld0:
            return
        Ld1:
            r8 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r0.decrementUseCount(r1)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.internal.DispatchedContinuation.resumeCancellableWith$kotlinx_coroutines_core(java.lang.Object, kotlin.jvm.functions.Function1):void");
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public void cancelCompletedResult$kotlinx_coroutines_core(Object takenState, Throwable cause) {
        if (takenState instanceof CompletedWithCancellation) {
            ((CompletedWithCancellation) takenState).onCancellation.invoke(cause);
        }
    }

    public final boolean resumeCancelled$kotlinx_coroutines_core(Object state) {
        Job job = (Job) getContext().get(Job.INSTANCE);
        if (job == null || job.isActive()) {
            return false;
        }
        CancellationException cancellationException = job.getCancellationException();
        cancelCompletedResult$kotlinx_coroutines_core(state, cancellationException);
        Result.Companion companion = Result.INSTANCE;
        resumeWith(Result.m6095constructorimpl(ResultKt.createFailure(cancellationException)));
        return true;
    }

    public final void dispatchYield$kotlinx_coroutines_core(CoroutineContext context, T value) {
        this._state = value;
        this.resumeMode = 1;
        this.dispatcher.dispatchYield(context, this);
    }

    public String toString() {
        return "DispatchedContinuation[" + this.dispatcher + ", " + DebugStringsKt.toDebugString(this.continuation) + AbstractJsonLexerKt.END_LIST;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0029 A[DONT_GENERATE] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void resumeUndispatchedWith$kotlinx_coroutines_core(java.lang.Object r6) {
        /*
            r5 = this;
            kotlin.coroutines.Continuation<T> r0 = r5.continuation
            java.lang.Object r1 = r5.countOrElement
            kotlin.coroutines.CoroutineContext r2 = r0.getContext()
            java.lang.Object r1 = kotlinx.coroutines.internal.ThreadContextKt.updateThreadContext(r2, r1)
            kotlinx.coroutines.internal.Symbol r3 = kotlinx.coroutines.internal.ThreadContextKt.NO_THREAD_ELEMENTS
            if (r1 == r3) goto L15
            kotlinx.coroutines.UndispatchedCoroutine r0 = kotlinx.coroutines.CoroutineContextKt.updateUndispatchedCompletion(r0, r2, r1)
            goto L16
        L15:
            r0 = 0
        L16:
            r3 = 1
            kotlin.coroutines.Continuation<T> r4 = r5.continuation     // Catch: java.lang.Throwable -> L30
            r4.resumeWith(r6)     // Catch: java.lang.Throwable -> L30
            kotlin.Unit r6 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L30
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            if (r0 == 0) goto L29
            boolean r6 = r0.clearThreadContext()
            if (r6 == 0) goto L2c
        L29:
            kotlinx.coroutines.internal.ThreadContextKt.restoreThreadContext(r2, r1)
        L2c:
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            return
        L30:
            r6 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            if (r0 == 0) goto L3c
            boolean r0 = r0.clearThreadContext()
            if (r0 == 0) goto L3f
        L3c:
            kotlinx.coroutines.internal.ThreadContextKt.restoreThreadContext(r2, r1)
        L3f:
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.internal.DispatchedContinuation.resumeUndispatchedWith$kotlinx_coroutines_core(java.lang.Object):void");
    }
}
