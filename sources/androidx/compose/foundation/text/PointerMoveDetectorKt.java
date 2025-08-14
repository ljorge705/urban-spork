package androidx.compose.foundation.text;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerEventPass;
import androidx.compose.ui.input.pointer.PointerInputScope;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;

/* compiled from: PointerMoveDetector.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a6\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\u0006H\u0080@ø\u0001\u0000ø\u0001\u0000¢\u0006\u0002\u0010\b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\t"}, d2 = {"detectMoves", "", "Landroidx/compose/ui/input/pointer/PointerInputScope;", "pointerEventPass", "Landroidx/compose/ui/input/pointer/PointerEventPass;", "onMove", "Lkotlin/Function1;", "Landroidx/compose/ui/geometry/Offset;", "(Landroidx/compose/ui/input/pointer/PointerInputScope;Landroidx/compose/ui/input/pointer/PointerEventPass;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class PointerMoveDetectorKt {
    public static /* synthetic */ Object detectMoves$default(PointerInputScope pointerInputScope, PointerEventPass pointerEventPass, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            pointerEventPass = PointerEventPass.Initial;
        }
        return detectMoves(pointerInputScope, pointerEventPass, function1, continuation);
    }

    /* compiled from: PointerMoveDetector.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.PointerMoveDetectorKt$detectMoves$2", f = "PointerMoveDetector.kt", i = {}, l = {41}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: androidx.compose.foundation.text.PointerMoveDetectorKt$detectMoves$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function1<Offset, Unit> $onMove;
        final /* synthetic */ PointerEventPass $pointerEventPass;
        final /* synthetic */ PointerInputScope $this_detectMoves;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass2(PointerInputScope pointerInputScope, PointerEventPass pointerEventPass, Function1<? super Offset, Unit> function1, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$this_detectMoves = pointerInputScope;
            this.$pointerEventPass = pointerEventPass;
            this.$onMove = function1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.$this_detectMoves, this.$pointerEventPass, this.$onMove, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineContext coroutineContext = get$context();
                this.label = 1;
                if (this.$this_detectMoves.awaitPointerEventScope(new AnonymousClass1(coroutineContext, this.$pointerEventPass, this.$onMove, null), this) == coroutine_suspended) {
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

        /* compiled from: PointerMoveDetector.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.text.PointerMoveDetectorKt$detectMoves$2$1", f = "PointerMoveDetector.kt", i = {0, 0}, l = {44}, m = "invokeSuspend", n = {"$this$awaitPointerEventScope", "previousPosition"}, s = {"L$0", "L$1"})
        /* renamed from: androidx.compose.foundation.text.PointerMoveDetectorKt$detectMoves$2$1, reason: invalid class name */
        static final class AnonymousClass1 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ CoroutineContext $currentContext;
            final /* synthetic */ Function1<Offset, Unit> $onMove;
            final /* synthetic */ PointerEventPass $pointerEventPass;
            private /* synthetic */ Object L$0;
            Object L$1;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass1(CoroutineContext coroutineContext, PointerEventPass pointerEventPass, Function1<? super Offset, Unit> function1, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.$currentContext = coroutineContext;
                this.$pointerEventPass = pointerEventPass;
                this.$onMove = function1;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$currentContext, this.$pointerEventPass, this.$onMove, continuation);
                anonymousClass1.L$0 = obj;
                return anonymousClass1;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass1) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Path cross not found for [B:18:0x0064, B:23:0x007d], limit reached: 29 */
            /* JADX WARN: Removed duplicated region for block: B:11:0x0039  */
            /* JADX WARN: Removed duplicated region for block: B:26:0x009c  */
            /* JADX WARN: Removed duplicated region for block: B:28:0x009f  */
            /* JADX WARN: Removed duplicated region for block: B:30:0x00b8  */
            /* JADX WARN: Type inference failed for: r10v14, types: [T, androidx.compose.ui.geometry.Offset] */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:14:0x004b -> B:15:0x0051). Please report as a decompilation issue!!! */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r10) {
                /*
                    r9 = this;
                    java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                    int r1 = r9.label
                    r2 = 1
                    if (r1 == 0) goto L23
                    if (r1 != r2) goto L1b
                    java.lang.Object r1 = r9.L$1
                    kotlin.jvm.internal.Ref$ObjectRef r1 = (kotlin.jvm.internal.Ref.ObjectRef) r1
                    java.lang.Object r3 = r9.L$0
                    androidx.compose.ui.input.pointer.AwaitPointerEventScope r3 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r3
                    kotlin.ResultKt.throwOnFailure(r10)
                    r4 = r3
                    r3 = r1
                    r1 = r0
                    r0 = r9
                    goto L51
                L1b:
                    java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
                    java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                    r10.<init>(r0)
                    throw r10
                L23:
                    kotlin.ResultKt.throwOnFailure(r10)
                    java.lang.Object r10 = r9.L$0
                    androidx.compose.ui.input.pointer.AwaitPointerEventScope r10 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r10
                    kotlin.jvm.internal.Ref$ObjectRef r1 = new kotlin.jvm.internal.Ref$ObjectRef
                    r1.<init>()
                    r3 = r10
                    r10 = r9
                L31:
                    kotlin.coroutines.CoroutineContext r4 = r10.$currentContext
                    boolean r4 = kotlinx.coroutines.JobKt.isActive(r4)
                    if (r4 == 0) goto Lb8
                    androidx.compose.ui.input.pointer.PointerEventPass r4 = r10.$pointerEventPass
                    r5 = r10
                    kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
                    r10.L$0 = r3
                    r10.L$1 = r1
                    r10.label = r2
                    java.lang.Object r4 = r3.awaitPointerEvent(r4, r5)
                    if (r4 != r0) goto L4b
                    return r0
                L4b:
                    r8 = r0
                    r0 = r10
                    r10 = r4
                    r4 = r3
                    r3 = r1
                    r1 = r8
                L51:
                    androidx.compose.ui.input.pointer.PointerEvent r10 = (androidx.compose.ui.input.pointer.PointerEvent) r10
                    int r5 = r10.getType()
                    androidx.compose.ui.input.pointer.PointerEventType$Companion r6 = androidx.compose.ui.input.pointer.PointerEventType.INSTANCE
                    int r6 = r6.m3236getMove7fucELk()
                    boolean r6 = androidx.compose.ui.input.pointer.PointerEventType.m3230equalsimpl0(r5, r6)
                    if (r6 == 0) goto L64
                    goto L7d
                L64:
                    androidx.compose.ui.input.pointer.PointerEventType$Companion r6 = androidx.compose.ui.input.pointer.PointerEventType.INSTANCE
                    int r6 = r6.m3234getEnter7fucELk()
                    boolean r6 = androidx.compose.ui.input.pointer.PointerEventType.m3230equalsimpl0(r5, r6)
                    if (r6 == 0) goto L71
                    goto L7d
                L71:
                    androidx.compose.ui.input.pointer.PointerEventType$Companion r6 = androidx.compose.ui.input.pointer.PointerEventType.INSTANCE
                    int r6 = r6.m3235getExit7fucELk()
                    boolean r5 = androidx.compose.ui.input.pointer.PointerEventType.m3230equalsimpl0(r5, r6)
                    if (r5 == 0) goto Lb2
                L7d:
                    java.util.List r10 = r10.getChanges()
                    java.lang.Object r10 = kotlin.collections.CollectionsKt.first(r10)
                    androidx.compose.ui.input.pointer.PointerInputChange r10 = (androidx.compose.ui.input.pointer.PointerInputChange) r10
                    long r5 = r10.getPosition()
                    androidx.compose.ui.geometry.Offset r10 = androidx.compose.ui.geometry.Offset.m1628boximpl(r5)
                    long r5 = r10.getPackedValue()
                    T r7 = r3.element
                    boolean r5 = androidx.compose.ui.geometry.Offset.m1635equalsimpl(r5, r7)
                    if (r5 != 0) goto L9c
                    goto L9d
                L9c:
                    r10 = 0
                L9d:
                    if (r10 == 0) goto Lb2
                    kotlin.jvm.functions.Function1<androidx.compose.ui.geometry.Offset, kotlin.Unit> r5 = r0.$onMove
                    long r6 = r10.getPackedValue()
                    androidx.compose.ui.geometry.Offset r10 = androidx.compose.ui.geometry.Offset.m1628boximpl(r6)
                    r3.element = r10
                    androidx.compose.ui.geometry.Offset r10 = androidx.compose.ui.geometry.Offset.m1628boximpl(r6)
                    r5.invoke(r10)
                Lb2:
                    r10 = r0
                    r0 = r1
                    r1 = r3
                    r3 = r4
                    goto L31
                Lb8:
                    kotlin.Unit r10 = kotlin.Unit.INSTANCE
                    return r10
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.PointerMoveDetectorKt.AnonymousClass2.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }
    }

    public static final Object detectMoves(PointerInputScope pointerInputScope, PointerEventPass pointerEventPass, Function1<? super Offset, Unit> function1, Continuation<? super Unit> continuation) {
        Object objCoroutineScope = CoroutineScopeKt.coroutineScope(new AnonymousClass2(pointerInputScope, pointerEventPass, function1, null), continuation);
        return objCoroutineScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCoroutineScope : Unit.INSTANCE;
    }
}
