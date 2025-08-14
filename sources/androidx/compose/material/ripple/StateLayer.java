package androidx.compose.material.ripple;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimatableKt;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.foundation.interaction.DragInteraction;
import androidx.compose.foundation.interaction.Interaction;
import androidx.compose.runtime.State;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.ClipOp;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import com.clevertap.android.sdk.Constants;
import com.drew.metadata.exif.makernotes.PanasonicMakernoteDirectory;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: Ripple.kt */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\u0016\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u0014J'\u0010\u0015\u001a\u00020\u0011*\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001b\u0010\u001cR\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006\u001d"}, d2 = {"Landroidx/compose/material/ripple/StateLayer;", "", "bounded", "", "rippleAlpha", "Landroidx/compose/runtime/State;", "Landroidx/compose/material/ripple/RippleAlpha;", "(ZLandroidx/compose/runtime/State;)V", "animatedAlpha", "Landroidx/compose/animation/core/Animatable;", "", "Landroidx/compose/animation/core/AnimationVector1D;", "currentInteraction", "Landroidx/compose/foundation/interaction/Interaction;", "interactions", "", "handleInteraction", "", "interaction", "scope", "Lkotlinx/coroutines/CoroutineScope;", "drawStateLayer", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", Constants.KEY_RADIUS, "Landroidx/compose/ui/unit/Dp;", "color", "Landroidx/compose/ui/graphics/Color;", "drawStateLayer-H2RKhps", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;FJ)V", "material-ripple_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
final class StateLayer {
    private final Animatable<Float, AnimationVector1D> animatedAlpha;
    private final boolean bounded;
    private Interaction currentInteraction;
    private final List<Interaction> interactions;
    private final State<RippleAlpha> rippleAlpha;

    public StateLayer(boolean z, State<RippleAlpha> rippleAlpha) {
        Intrinsics.checkNotNullParameter(rippleAlpha, "rippleAlpha");
        this.bounded = z;
        this.rippleAlpha = rippleAlpha;
        this.animatedAlpha = AnimatableKt.Animatable$default(0.0f, 0.0f, 2, null);
        this.interactions = new ArrayList();
    }

    public final void handleInteraction(Interaction interaction, CoroutineScope scope) {
        Intrinsics.checkNotNullParameter(interaction, "interaction");
        Intrinsics.checkNotNullParameter(scope, "scope");
        boolean z = interaction instanceof DragInteraction.Start;
        if (z) {
            this.interactions.add(interaction);
        } else if (interaction instanceof DragInteraction.Stop) {
            this.interactions.remove(((DragInteraction.Stop) interaction).getStart());
        } else if (!(interaction instanceof DragInteraction.Cancel)) {
            return;
        } else {
            this.interactions.remove(((DragInteraction.Cancel) interaction).getStart());
        }
        Interaction interaction2 = (Interaction) CollectionsKt.lastOrNull((List) this.interactions);
        if (Intrinsics.areEqual(this.currentInteraction, interaction2)) {
            return;
        }
        if (interaction2 != null) {
            BuildersKt__Builders_commonKt.launch$default(scope, null, null, new AnonymousClass1(z ? this.rippleAlpha.getValue().getDraggedAlpha() : 0.0f, RippleKt.incomingStateLayerAnimationSpecFor(interaction2), null), 3, null);
        } else {
            BuildersKt__Builders_commonKt.launch$default(scope, null, null, new AnonymousClass2(RippleKt.outgoingStateLayerAnimationSpecFor(this.currentInteraction), null), 3, null);
        }
        this.currentInteraction = interaction2;
    }

    /* compiled from: Ripple.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 5, 1}, xi = 48)
    @DebugMetadata(c = "androidx.compose.material.ripple.StateLayer$handleInteraction$1", f = "Ripple.kt", i = {}, l = {275}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: androidx.compose.material.ripple.StateLayer$handleInteraction$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ AnimationSpec<Float> $incomingAnimationSpec;
        final /* synthetic */ float $targetAlpha;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(float f, AnimationSpec<Float> animationSpec, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$targetAlpha = f;
            this.$incomingAnimationSpec = animationSpec;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return StateLayer.this.new AnonymousClass1(this.$targetAlpha, this.$incomingAnimationSpec, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (Animatable.animateTo$default(StateLayer.this.animatedAlpha, Boxing.boxFloat(this.$targetAlpha), this.$incomingAnimationSpec, null, null, this, 12, null) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i == 1) {
                ResultKt.throwOnFailure(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    /* compiled from: Ripple.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 5, 1}, xi = 48)
    @DebugMetadata(c = "androidx.compose.material.ripple.StateLayer$handleInteraction$2", f = "Ripple.kt", i = {}, l = {281}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: androidx.compose.material.ripple.StateLayer$handleInteraction$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ AnimationSpec<Float> $outgoingAnimationSpec;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(AnimationSpec<Float> animationSpec, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$outgoingAnimationSpec = animationSpec;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return StateLayer.this.new AnonymousClass2(this.$outgoingAnimationSpec, continuation);
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
                this.label = 1;
                if (Animatable.animateTo$default(StateLayer.this.animatedAlpha, Boxing.boxFloat(0.0f), this.$outgoingAnimationSpec, null, null, this, 12, null) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i == 1) {
                ResultKt.throwOnFailure(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    /* renamed from: drawStateLayer-H2RKhps, reason: not valid java name */
    public final void m1489drawStateLayerH2RKhps(DrawScope receiver, float f, long j) {
        float fM1481getRippleEndRadiuscSwnlzA;
        Intrinsics.checkNotNullParameter(receiver, "$receiver");
        if (Float.isNaN(f)) {
            fM1481getRippleEndRadiuscSwnlzA = RippleAnimationKt.m1481getRippleEndRadiuscSwnlzA(receiver, this.bounded, receiver.mo2417getSizeNHjbRc());
        } else {
            fM1481getRippleEndRadiuscSwnlzA = receiver.mo577toPx0680j_4(f);
        }
        float f2 = fM1481getRippleEndRadiuscSwnlzA;
        float fFloatValue = this.animatedAlpha.getValue().floatValue();
        if (fFloatValue > 0.0f) {
            long jM1876copywmQWz5c$default = Color.m1876copywmQWz5c$default(j, fFloatValue, 0.0f, 0.0f, 0.0f, 14, null);
            if (this.bounded) {
                float fM1708getWidthimpl = Size.m1708getWidthimpl(receiver.mo2417getSizeNHjbRc());
                float fM1705getHeightimpl = Size.m1705getHeightimpl(receiver.mo2417getSizeNHjbRc());
                int iM1866getIntersectrtfAjoo = ClipOp.INSTANCE.m1866getIntersectrtfAjoo();
                DrawContext drawContext = receiver.getDrawContext();
                long jMo2342getSizeNHjbRc = drawContext.mo2342getSizeNHjbRc();
                drawContext.getCanvas().save();
                drawContext.getTransform().mo2345clipRectN_I0leg(0.0f, 0.0f, fM1708getWidthimpl, fM1705getHeightimpl, iM1866getIntersectrtfAjoo);
                DrawScope.m2399drawCircleVaOC9Bg$default(receiver, jM1876copywmQWz5c$default, f2, 0L, 0.0f, null, null, 0, PanasonicMakernoteDirectory.TAG_CLEAR_RETOUCH, null);
                drawContext.getCanvas().restore();
                drawContext.mo2343setSizeuvyYCjk(jMo2342getSizeNHjbRc);
                return;
            }
            DrawScope.m2399drawCircleVaOC9Bg$default(receiver, jM1876copywmQWz5c$default, f2, 0L, 0.0f, null, null, 0, PanasonicMakernoteDirectory.TAG_CLEAR_RETOUCH, null);
        }
    }
}
