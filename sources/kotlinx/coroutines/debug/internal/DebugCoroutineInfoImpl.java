package kotlinx.coroutines.debug.internal;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequenceScope;
import kotlin.sequences.SequencesKt;

/* compiled from: DebugCoroutineInfoImpl.kt */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0001\u0018\u00002\u00020\u0001B#\b\u0000\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u000e\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014H\u0002J\u0013\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014H\u0000¢\u0006\u0002\b&J\b\u0010'\u001a\u00020\u000eH\u0016J)\u0010(\u001a\u00020)2\u0006\u0010 \u001a\u00020\u000e2\n\u0010*\u001a\u0006\u0012\u0002\b\u00030+2\u0006\u0010,\u001a\u00020-H\u0000¢\u0006\u0002\b.J%\u0010/\u001a\u00020)*\b\u0012\u0004\u0012\u00020\u0015002\b\u0010*\u001a\u0004\u0018\u00010\fH\u0082Pø\u0001\u0000¢\u0006\u0002\u00101R\u0016\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\r\u001a\u00020\u000e8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u00038F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00148F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R(\u0010\u0019\u001a\u0004\u0018\u00010\f2\b\u0010\u0018\u001a\u0004\u0018\u00010\f8@@@X\u0080\u000e¢\u0006\f\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001e\u001a\u0004\u0018\u00010\u001f8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0014\u0010 \u001a\u00020\u000e8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\"R\u000e\u0010#\u001a\u00020$X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u00062"}, d2 = {"Lkotlinx/coroutines/debug/internal/DebugCoroutineInfoImpl;", "", "context", "Lkotlin/coroutines/CoroutineContext;", "creationStackBottom", "Lkotlinx/coroutines/debug/internal/StackTraceFrame;", "sequenceNumber", "", "(Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/debug/internal/StackTraceFrame;J)V", "_context", "Ljava/lang/ref/WeakReference;", "_lastObservedFrame", "Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "_state", "", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "getCreationStackBottom$kotlinx_coroutines_core", "()Lkotlinx/coroutines/debug/internal/StackTraceFrame;", "creationStackTrace", "", "Ljava/lang/StackTraceElement;", "getCreationStackTrace", "()Ljava/util/List;", "value", "lastObservedFrame", "getLastObservedFrame$kotlinx_coroutines_core", "()Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "setLastObservedFrame$kotlinx_coroutines_core", "(Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;)V", "lastObservedThread", "Ljava/lang/Thread;", "state", "getState$kotlinx_coroutines_core", "()Ljava/lang/String;", "unmatchedResume", "", "lastObservedStackTrace", "lastObservedStackTrace$kotlinx_coroutines_core", "toString", "updateState", "", "frame", "Lkotlin/coroutines/Continuation;", "shouldBeMatched", "", "updateState$kotlinx_coroutines_core", "yieldFrames", "Lkotlin/sequences/SequenceScope;", "(Lkotlin/sequences/SequenceScope;Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class DebugCoroutineInfoImpl {
    private final WeakReference<CoroutineContext> _context;
    public volatile WeakReference<CoroutineStackFrame> _lastObservedFrame;
    public volatile String _state = DebugCoroutineInfoImplKt.CREATED;
    private final StackTraceFrame creationStackBottom;
    public volatile Thread lastObservedThread;
    public final long sequenceNumber;
    private int unmatchedResume;

    /* compiled from: DebugCoroutineInfoImpl.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.debug.internal.DebugCoroutineInfoImpl", f = "DebugCoroutineInfoImpl.kt", i = {0, 0, 0}, l = {171}, m = "yieldFrames", n = {"this", "$this$yieldFrames", "frame"}, s = {"L$0", "L$1", "L$2"})
    /* renamed from: kotlinx.coroutines.debug.internal.DebugCoroutineInfoImpl$yieldFrames$1, reason: invalid class name and case insensitive filesystem */
    static final class C09281 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C09281(Continuation<? super C09281> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DebugCoroutineInfoImpl.this.yieldFrames(null, null, this);
        }
    }

    /* renamed from: getCreationStackBottom$kotlinx_coroutines_core, reason: from getter */
    public final StackTraceFrame getCreationStackBottom() {
        return this.creationStackBottom;
    }

    /* renamed from: getState$kotlinx_coroutines_core, reason: from getter */
    public final String get_state() {
        return this._state;
    }

    public DebugCoroutineInfoImpl(CoroutineContext coroutineContext, StackTraceFrame stackTraceFrame, long j) {
        this.creationStackBottom = stackTraceFrame;
        this.sequenceNumber = j;
        this._context = new WeakReference<>(coroutineContext);
    }

    public final CoroutineContext getContext() {
        return this._context.get();
    }

    public final List<StackTraceElement> getCreationStackTrace() {
        return creationStackTrace();
    }

    public final synchronized void updateState$kotlinx_coroutines_core(String state, Continuation<?> frame, boolean shouldBeMatched) {
        if (Intrinsics.areEqual(this._state, DebugCoroutineInfoImplKt.RUNNING) && Intrinsics.areEqual(state, DebugCoroutineInfoImplKt.RUNNING) && shouldBeMatched) {
            this.unmatchedResume++;
        } else if (this.unmatchedResume > 0 && Intrinsics.areEqual(state, DebugCoroutineInfoImplKt.SUSPENDED)) {
            this.unmatchedResume--;
            return;
        }
        if (Intrinsics.areEqual(this._state, state) && Intrinsics.areEqual(state, DebugCoroutineInfoImplKt.SUSPENDED) && getLastObservedFrame$kotlinx_coroutines_core() != null) {
            return;
        }
        this._state = state;
        setLastObservedFrame$kotlinx_coroutines_core(frame instanceof CoroutineStackFrame ? (CoroutineStackFrame) frame : null);
        this.lastObservedThread = Intrinsics.areEqual(state, DebugCoroutineInfoImplKt.RUNNING) ? Thread.currentThread() : null;
    }

    public final CoroutineStackFrame getLastObservedFrame$kotlinx_coroutines_core() {
        WeakReference<CoroutineStackFrame> weakReference = this._lastObservedFrame;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    public final void setLastObservedFrame$kotlinx_coroutines_core(CoroutineStackFrame coroutineStackFrame) {
        this._lastObservedFrame = coroutineStackFrame != null ? new WeakReference<>(coroutineStackFrame) : null;
    }

    public final List<StackTraceElement> lastObservedStackTrace$kotlinx_coroutines_core() {
        CoroutineStackFrame lastObservedFrame$kotlinx_coroutines_core = getLastObservedFrame$kotlinx_coroutines_core();
        if (lastObservedFrame$kotlinx_coroutines_core == null) {
            return CollectionsKt.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        while (lastObservedFrame$kotlinx_coroutines_core != null) {
            StackTraceElement stackTraceElement = lastObservedFrame$kotlinx_coroutines_core.getStackTraceElement();
            if (stackTraceElement != null) {
                arrayList.add(stackTraceElement);
            }
            lastObservedFrame$kotlinx_coroutines_core = lastObservedFrame$kotlinx_coroutines_core.getCallerFrame();
        }
        return arrayList;
    }

    private final List<StackTraceElement> creationStackTrace() {
        StackTraceFrame stackTraceFrame = this.creationStackBottom;
        if (stackTraceFrame == null) {
            return CollectionsKt.emptyList();
        }
        return SequencesKt.toList(SequencesKt.sequence(new AnonymousClass1(stackTraceFrame, null)));
    }

    /* compiled from: DebugCoroutineInfoImpl.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlin/sequences/SequenceScope;", "Ljava/lang/StackTraceElement;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.debug.internal.DebugCoroutineInfoImpl$creationStackTrace$1", f = "DebugCoroutineInfoImpl.kt", i = {}, l = {166}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: kotlinx.coroutines.debug.internal.DebugCoroutineInfoImpl$creationStackTrace$1, reason: invalid class name */
    static final class AnonymousClass1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super StackTraceElement>, Continuation<? super Unit>, Object> {
        final /* synthetic */ StackTraceFrame $bottom;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(StackTraceFrame stackTraceFrame, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$bottom = stackTraceFrame;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = DebugCoroutineInfoImpl.this.new AnonymousClass1(this.$bottom, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SequenceScope<? super StackTraceElement> sequenceScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                SequenceScope sequenceScope = (SequenceScope) this.L$0;
                this.label = 1;
                if (DebugCoroutineInfoImpl.this.yieldFrames(sequenceScope, this.$bottom.getCallerFrame(), this) == coroutine_suspended) {
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

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x004b -> B:25:0x0062). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x005c -> B:24:0x005f). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object yieldFrames(kotlin.sequences.SequenceScope<? super java.lang.StackTraceElement> r6, kotlin.coroutines.jvm.internal.CoroutineStackFrame r7, kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            r5 = this;
            boolean r0 = r8 instanceof kotlinx.coroutines.debug.internal.DebugCoroutineInfoImpl.C09281
            if (r0 == 0) goto L14
            r0 = r8
            kotlinx.coroutines.debug.internal.DebugCoroutineInfoImpl$yieldFrames$1 r0 = (kotlinx.coroutines.debug.internal.DebugCoroutineInfoImpl.C09281) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L19
        L14:
            kotlinx.coroutines.debug.internal.DebugCoroutineInfoImpl$yieldFrames$1 r0 = new kotlinx.coroutines.debug.internal.DebugCoroutineInfoImpl$yieldFrames$1
            r0.<init>(r8)
        L19:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3e
            if (r2 != r3) goto L36
            java.lang.Object r6 = r0.L$2
            kotlin.coroutines.jvm.internal.CoroutineStackFrame r6 = (kotlin.coroutines.jvm.internal.CoroutineStackFrame) r6
            java.lang.Object r7 = r0.L$1
            kotlin.sequences.SequenceScope r7 = (kotlin.sequences.SequenceScope) r7
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.debug.internal.DebugCoroutineInfoImpl r2 = (kotlinx.coroutines.debug.internal.DebugCoroutineInfoImpl) r2
            kotlin.ResultKt.throwOnFailure(r8)
            goto L5f
        L36:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L3e:
            kotlin.ResultKt.throwOnFailure(r8)
            r2 = r5
        L42:
            if (r7 != 0) goto L47
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        L47:
            java.lang.StackTraceElement r8 = r7.getStackTraceElement()
            if (r8 == 0) goto L62
            r0.L$0 = r2
            r0.L$1 = r6
            r0.L$2 = r7
            r0.label = r3
            java.lang.Object r8 = r6.yield(r8, r0)
            if (r8 != r1) goto L5c
            return r1
        L5c:
            r4 = r7
            r7 = r6
            r6 = r4
        L5f:
            r4 = r7
            r7 = r6
            r6 = r4
        L62:
            kotlin.coroutines.jvm.internal.CoroutineStackFrame r7 = r7.getCallerFrame()
            if (r7 == 0) goto L69
            goto L42
        L69:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.debug.internal.DebugCoroutineInfoImpl.yieldFrames(kotlin.sequences.SequenceScope, kotlin.coroutines.jvm.internal.CoroutineStackFrame, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public String toString() {
        return "DebugCoroutineInfo(state=" + get_state() + ",context=" + getContext() + ')';
    }
}
