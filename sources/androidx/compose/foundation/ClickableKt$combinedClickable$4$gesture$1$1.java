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
import com.drew.metadata.iptc.IptcDirectory;
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
@DebugMetadata(c = "androidx.compose.foundation.ClickableKt$combinedClickable$4$gesture$1$1", f = "Clickable.kt", i = {}, l = {343}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
final class ClickableKt$combinedClickable$4$gesture$1$1 extends SuspendLambda implements Function2<PointerInputScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MutableState<Offset> $centreOffset;
    final /* synthetic */ State<Function0<Boolean>> $delayPressInteraction;
    final /* synthetic */ boolean $enabled;
    final /* synthetic */ boolean $hasDoubleClick;
    final /* synthetic */ boolean $hasLongClick;
    final /* synthetic */ MutableInteractionSource $interactionSource;
    final /* synthetic */ State<Function0<Unit>> $onClickState;
    final /* synthetic */ State<Function0<Unit>> $onDoubleClickState;
    final /* synthetic */ State<Function0<Unit>> $onLongClickState;
    final /* synthetic */ MutableState<PressInteraction.Press> $pressedInteraction;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    ClickableKt$combinedClickable$4$gesture$1$1(MutableState<Offset> mutableState, boolean z, boolean z2, boolean z3, State<? extends Function0<Unit>> state, State<? extends Function0<Unit>> state2, MutableInteractionSource mutableInteractionSource, MutableState<PressInteraction.Press> mutableState2, State<? extends Function0<Boolean>> state3, State<? extends Function0<Unit>> state4, Continuation<? super ClickableKt$combinedClickable$4$gesture$1$1> continuation) {
        super(2, continuation);
        this.$centreOffset = mutableState;
        this.$hasDoubleClick = z;
        this.$enabled = z2;
        this.$hasLongClick = z3;
        this.$onDoubleClickState = state;
        this.$onLongClickState = state2;
        this.$interactionSource = mutableInteractionSource;
        this.$pressedInteraction = mutableState2;
        this.$delayPressInteraction = state3;
        this.$onClickState = state4;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ClickableKt$combinedClickable$4$gesture$1$1 clickableKt$combinedClickable$4$gesture$1$1 = new ClickableKt$combinedClickable$4$gesture$1$1(this.$centreOffset, this.$hasDoubleClick, this.$enabled, this.$hasLongClick, this.$onDoubleClickState, this.$onLongClickState, this.$interactionSource, this.$pressedInteraction, this.$delayPressInteraction, this.$onClickState, continuation);
        clickableKt$combinedClickable$4$gesture$1$1.L$0 = obj;
        return clickableKt$combinedClickable$4$gesture$1$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
        return ((ClickableKt$combinedClickable$4$gesture$1$1) create(pointerInputScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* compiled from: Clickable.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.ClickableKt$combinedClickable$4$gesture$1$1$3", f = "Clickable.kt", i = {}, l = {IptcDirectory.TAG_UNIQUE_OBJECT_NAME}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: androidx.compose.foundation.ClickableKt$combinedClickable$4$gesture$1$1$3, reason: invalid class name */
    static final class AnonymousClass3 extends SuspendLambda implements Function3<PressGestureScope, Offset, Continuation<? super Unit>, Object> {
        final /* synthetic */ State<Function0<Boolean>> $delayPressInteraction;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ MutableInteractionSource $interactionSource;
        final /* synthetic */ MutableState<PressInteraction.Press> $pressedInteraction;
        /* synthetic */ long J$0;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass3(boolean z, MutableInteractionSource mutableInteractionSource, MutableState<PressInteraction.Press> mutableState, State<? extends Function0<Boolean>> state, Continuation<? super AnonymousClass3> continuation) {
            super(3, continuation);
            this.$enabled = z;
            this.$interactionSource = mutableInteractionSource;
            this.$pressedInteraction = mutableState;
            this.$delayPressInteraction = state;
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(PressGestureScope pressGestureScope, Offset offset, Continuation<? super Unit> continuation) {
            return m463invoked4ec7I(pressGestureScope, offset.getPackedValue(), continuation);
        }

        /* renamed from: invoke-d-4ec7I, reason: not valid java name */
        public final Object m463invoked4ec7I(PressGestureScope pressGestureScope, long j, Continuation<? super Unit> continuation) {
            AnonymousClass3 anonymousClass3 = new AnonymousClass3(this.$enabled, this.$interactionSource, this.$pressedInteraction, this.$delayPressInteraction, continuation);
            anonymousClass3.L$0 = pressGestureScope;
            anonymousClass3.J$0 = j;
            return anonymousClass3.invokeSuspend(Unit.INSTANCE);
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
        Function1<Offset, Unit> function1;
        Function1<Offset, Unit> function12;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            PointerInputScope pointerInputScope = (PointerInputScope) this.L$0;
            MutableState<Offset> mutableState = this.$centreOffset;
            long jM4556getCenterozmzZPI = IntSizeKt.m4556getCenterozmzZPI(pointerInputScope.m3314getSizeYbymL2g());
            mutableState.setValue(Offset.m1628boximpl(OffsetKt.Offset(IntOffset.m4508getXimpl(jM4556getCenterozmzZPI), IntOffset.m4509getYimpl(jM4556getCenterozmzZPI))));
            if (this.$hasDoubleClick && this.$enabled) {
                final State<Function0<Unit>> state = this.$onDoubleClickState;
                function1 = new Function1<Offset, Unit>() { // from class: androidx.compose.foundation.ClickableKt$combinedClickable$4$gesture$1$1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Offset offset) {
                        m461invokek4lQ0M(offset.getPackedValue());
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke-k-4lQ0M, reason: not valid java name */
                    public final void m461invokek4lQ0M(long j) {
                        Function0<Unit> value = state.getValue();
                        if (value != null) {
                            value.invoke();
                        }
                    }
                };
            } else {
                function1 = null;
            }
            if (this.$hasLongClick && this.$enabled) {
                final State<Function0<Unit>> state2 = this.$onLongClickState;
                function12 = new Function1<Offset, Unit>() { // from class: androidx.compose.foundation.ClickableKt$combinedClickable$4$gesture$1$1.2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Offset offset) {
                        m462invokek4lQ0M(offset.getPackedValue());
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke-k-4lQ0M, reason: not valid java name */
                    public final void m462invokek4lQ0M(long j) {
                        Function0<Unit> value = state2.getValue();
                        if (value != null) {
                            value.invoke();
                        }
                    }
                };
            } else {
                function12 = null;
            }
            AnonymousClass3 anonymousClass3 = new AnonymousClass3(this.$enabled, this.$interactionSource, this.$pressedInteraction, this.$delayPressInteraction, null);
            final boolean z = this.$enabled;
            final State<Function0<Unit>> state3 = this.$onClickState;
            this.label = 1;
            if (TapGestureDetectorKt.detectTapGestures(pointerInputScope, function1, function12, anonymousClass3, new Function1<Offset, Unit>() { // from class: androidx.compose.foundation.ClickableKt$combinedClickable$4$gesture$1$1.4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Offset offset) {
                    m464invokek4lQ0M(offset.getPackedValue());
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke-k-4lQ0M, reason: not valid java name */
                public final void m464invokek4lQ0M(long j) {
                    if (z) {
                        state3.getValue().invoke();
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
