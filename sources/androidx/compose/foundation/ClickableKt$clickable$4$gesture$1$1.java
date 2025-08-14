package androidx.compose.foundation;

import androidx.compose.foundation.gestures.PressGestureScope;
import androidx.compose.foundation.gestures.TapGestureDetectorKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.interaction.PressInteraction;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSizeKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* compiled from: Clickable.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.ClickableKt$clickable$4$gesture$1$1", f = "Clickable.kt", i = {}, l = {156}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
final class ClickableKt$clickable$4$gesture$1$1 extends SuspendLambda implements Function2<PointerInputScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MutableState<Offset> $centreOffset;
    final /* synthetic */ State<Function0<Boolean>> $delayPressInteraction;
    final /* synthetic */ boolean $enabled;
    final /* synthetic */ MutableInteractionSource $interactionSource;
    final /* synthetic */ State<Function0<Unit>> $onClickState;
    final /* synthetic */ MutableState<PressInteraction.Press> $pressedInteraction;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    ClickableKt$clickable$4$gesture$1$1(MutableState<Offset> mutableState, boolean z, MutableInteractionSource mutableInteractionSource, MutableState<PressInteraction.Press> mutableState2, State<? extends Function0<Boolean>> state, State<? extends Function0<Unit>> state2, Continuation<? super ClickableKt$clickable$4$gesture$1$1> continuation) {
        super(2, continuation);
        this.$centreOffset = mutableState;
        this.$enabled = z;
        this.$interactionSource = mutableInteractionSource;
        this.$pressedInteraction = mutableState2;
        this.$delayPressInteraction = state;
        this.$onClickState = state2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ClickableKt$clickable$4$gesture$1$1 clickableKt$clickable$4$gesture$1$1 = new ClickableKt$clickable$4$gesture$1$1(this.$centreOffset, this.$enabled, this.$interactionSource, this.$pressedInteraction, this.$delayPressInteraction, this.$onClickState, continuation);
        clickableKt$clickable$4$gesture$1$1.L$0 = obj;
        return clickableKt$clickable$4$gesture$1$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
        return ((ClickableKt$clickable$4$gesture$1$1) create(pointerInputScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* compiled from: Clickable.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.ClickableKt$clickable$4$gesture$1$1$1", f = "Clickable.kt", i = {}, l = {159}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: androidx.compose.foundation.ClickableKt$clickable$4$gesture$1$1$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function3<PressGestureScope, Offset, Continuation<? super Unit>, Object> {
        final /* synthetic */ State<Function0<Boolean>> $delayPressInteraction;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ MutableInteractionSource $interactionSource;
        final /* synthetic */ MutableState<PressInteraction.Press> $pressedInteraction;
        /* synthetic */ long J$0;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass1(boolean z, MutableInteractionSource mutableInteractionSource, MutableState<PressInteraction.Press> mutableState, State<? extends Function0<Boolean>> state, Continuation<? super AnonymousClass1> continuation) {
            super(3, continuation);
            this.$enabled = z;
            this.$interactionSource = mutableInteractionSource;
            this.$pressedInteraction = mutableState;
            this.$delayPressInteraction = state;
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(PressGestureScope pressGestureScope, Offset offset, Continuation<? super Unit> continuation) {
            return m459invoked4ec7I(pressGestureScope, offset.getPackedValue(), continuation);
        }

        /* renamed from: invoke-d-4ec7I, reason: not valid java name */
        public final Object m459invoked4ec7I(PressGestureScope pressGestureScope, long j, Continuation<? super Unit> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$enabled, this.$interactionSource, this.$pressedInteraction, this.$delayPressInteraction, continuation);
            anonymousClass1.L$0 = pressGestureScope;
            anonymousClass1.J$0 = j;
            return anonymousClass1.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                PressGestureScope pressGestureScope = (PressGestureScope) this.L$0;
                long j = this.J$0;
                if (this.$enabled) {
                    this.label = 1;
                    if (ClickableKt.m458handlePressInteractionEPk0efs(pressGestureScope, j, this.$interactionSource, this.$pressedInteraction, this.$delayPressInteraction, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
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

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            PointerInputScope pointerInputScope = (PointerInputScope) this.L$0;
            MutableState<Offset> mutableState = this.$centreOffset;
            long jM4556getCenterozmzZPI = IntSizeKt.m4556getCenterozmzZPI(pointerInputScope.m3314getSizeYbymL2g());
            mutableState.setValue(Offset.m1628boximpl(OffsetKt.Offset(IntOffset.m4508getXimpl(jM4556getCenterozmzZPI), IntOffset.m4509getYimpl(jM4556getCenterozmzZPI))));
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$enabled, this.$interactionSource, this.$pressedInteraction, this.$delayPressInteraction, null);
            final boolean z = this.$enabled;
            final State<Function0<Unit>> state = this.$onClickState;
            this.label = 1;
            if (TapGestureDetectorKt.detectTapAndPress(pointerInputScope, anonymousClass1, new Function1<Offset, Unit>() { // from class: androidx.compose.foundation.ClickableKt$clickable$4$gesture$1$1.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Offset offset) {
                    m460invokek4lQ0M(offset.getPackedValue());
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke-k-4lQ0M, reason: not valid java name */
                public final void m460invokek4lQ0M(long j) {
                    if (z) {
                        state.getValue().invoke();
                    }
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
