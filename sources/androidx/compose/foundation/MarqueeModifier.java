package androidx.compose.foundation;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimatableKt;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.DrawModifier;
import androidx.compose.ui.focus.FocusEventModifier;
import androidx.compose.ui.focus.FocusState;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.ClipOp;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.layout.LayoutModifier;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import com.drew.metadata.exif.makernotes.OlympusImageProcessingMakernoteDirectory;
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
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import org.bouncycastle.crypto.CryptoServicesPermission;

/* compiled from: BasicMarquee.kt */
@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u0007\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004B0\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\fø\u0001\u0000¢\u0006\u0002\u0010\rJ\u0010\u00107\u001a\u0002082\u0006\u00109\u001a\u00020:H\u0016J\u0011\u0010;\u001a\u000208H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010<J\f\u0010=\u001a\u000208*\u00020>H\u0016J)\u0010?\u001a\u00020@*\u00020A2\u0006\u0010B\u001a\u00020C2\u0006\u0010D\u001a\u00020EH\u0016ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bF\u0010GR4\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u000f8F@FX\u0086\u008e\u0002ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0012\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R+\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u00068B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u001a\u0010\u0016\u001a\u0004\b\u0018\u0010\u0012\"\u0004\b\u0019\u0010\u0014R+\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u00068B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u001e\u0010\u0016\u001a\u0004\b\u001c\u0010\u0012\"\u0004\b\u001d\u0010\u0014R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082\u0004¢\u0006\u0002\n\u0000R+\u0010\"\u001a\u00020!2\u0006\u0010\u000e\u001a\u00020!8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b'\u0010\u0016\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u000e\u0010\b\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010(\u001a\u000e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020*0)X\u0082\u0004¢\u0006\u0002\n\u0000R+\u0010,\u001a\u00020+2\u0006\u0010\u000e\u001a\u00020+8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b1\u0010\u0016\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u001b\u00102\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b4\u00105\u001a\u0004\b3\u0010\u0012R\u0019\u0010\t\u001a\u00020\nX\u0082\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u00106\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006H"}, d2 = {"Landroidx/compose/foundation/MarqueeModifier;", "Landroidx/compose/ui/Modifier$Element;", "Landroidx/compose/ui/layout/LayoutModifier;", "Landroidx/compose/ui/draw/DrawModifier;", "Landroidx/compose/ui/focus/FocusEventModifier;", "iterations", "", "delayMillis", "initialDelayMillis", "velocity", "Landroidx/compose/ui/unit/Dp;", "density", "Landroidx/compose/ui/unit/Density;", "(IIIFLandroidx/compose/ui/unit/Density;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "<set-?>", "Landroidx/compose/foundation/MarqueeAnimationMode;", "animationMode", "getAnimationMode-ZbEOnfQ", "()I", "setAnimationMode-97h66l8", "(I)V", "animationMode$delegate", "Landroidx/compose/runtime/MutableState;", "containerWidth", "getContainerWidth", "setContainerWidth", "containerWidth$delegate", "contentWidth", "getContentWidth", "setContentWidth", "contentWidth$delegate", "direction", "", "", "hasFocus", "getHasFocus", "()Z", "setHasFocus", "(Z)V", "hasFocus$delegate", "offset", "Landroidx/compose/animation/core/Animatable;", "Landroidx/compose/animation/core/AnimationVector1D;", "Landroidx/compose/foundation/MarqueeSpacing;", "spacing", "getSpacing", "()Landroidx/compose/foundation/MarqueeSpacing;", "setSpacing", "(Landroidx/compose/foundation/MarqueeSpacing;)V", "spacing$delegate", "spacingPx", "getSpacingPx", "spacingPx$delegate", "Landroidx/compose/runtime/State;", "F", "onFocusEvent", "", "focusState", "Landroidx/compose/ui/focus/FocusState;", "runAnimation", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "draw", "Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurable", "Landroidx/compose/ui/layout/Measurable;", CryptoServicesPermission.CONSTRAINTS, "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
final class MarqueeModifier implements Modifier.Element, LayoutModifier, DrawModifier, FocusEventModifier {

    /* renamed from: animationMode$delegate, reason: from kotlin metadata */
    private final MutableState animationMode;

    /* renamed from: containerWidth$delegate, reason: from kotlin metadata */
    private final MutableState containerWidth;

    /* renamed from: contentWidth$delegate, reason: from kotlin metadata */
    private final MutableState contentWidth;
    private final int delayMillis;
    private final Density density;
    private final float direction;

    /* renamed from: hasFocus$delegate, reason: from kotlin metadata */
    private final MutableState hasFocus;
    private final int initialDelayMillis;
    private final int iterations;
    private final Animatable<Float, AnimationVector1D> offset;

    /* renamed from: spacing$delegate, reason: from kotlin metadata */
    private final MutableState spacing;

    /* renamed from: spacingPx$delegate, reason: from kotlin metadata */
    private final State spacingPx;
    private final float velocity;

    public /* synthetic */ MarqueeModifier(int i, int i2, int i3, float f, Density density, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, i2, i3, f, density);
    }

    private MarqueeModifier(int i, int i2, int i3, float f, Density density) {
        this.iterations = i;
        this.delayMillis = i2;
        this.initialDelayMillis = i3;
        this.velocity = f;
        this.density = density;
        this.contentWidth = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(0, null, 2, null);
        this.containerWidth = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(0, null, 2, null);
        this.hasFocus = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
        this.spacing = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(BasicMarqueeKt.getDefaultMarqueeSpacing(), null, 2, null);
        this.animationMode = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(MarqueeAnimationMode.m477boximpl(MarqueeAnimationMode.INSTANCE.m486getImmediatelyZbEOnfQ()), null, 2, null);
        this.offset = AnimatableKt.Animatable$default(0.0f, 0.0f, 2, null);
        this.direction = Math.signum(f);
        this.spacingPx = SnapshotStateKt.derivedStateOf(new Function0<Integer>() { // from class: androidx.compose.foundation.MarqueeModifier$spacingPx$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                MarqueeSpacing spacing = this.this$0.getSpacing();
                MarqueeModifier marqueeModifier = this.this$0;
                return Integer.valueOf(spacing.calculateSpacing(marqueeModifier.density, marqueeModifier.getContentWidth(), marqueeModifier.getContainerWidth()));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final int getContentWidth() {
        return ((Number) this.contentWidth.getValue()).intValue();
    }

    private final void setContentWidth(int i) {
        this.contentWidth.setValue(Integer.valueOf(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final int getContainerWidth() {
        return ((Number) this.containerWidth.getValue()).intValue();
    }

    private final void setContainerWidth(int i) {
        this.containerWidth.setValue(Integer.valueOf(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final boolean getHasFocus() {
        return ((Boolean) this.hasFocus.getValue()).booleanValue();
    }

    private final void setHasFocus(boolean z) {
        this.hasFocus.setValue(Boolean.valueOf(z));
    }

    public final MarqueeSpacing getSpacing() {
        return (MarqueeSpacing) this.spacing.getValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: getAnimationMode-ZbEOnfQ, reason: not valid java name */
    public final int m488getAnimationModeZbEOnfQ() {
        return ((MarqueeAnimationMode) this.animationMode.getValue()).getValue();
    }

    /* renamed from: setAnimationMode-97h66l8, reason: not valid java name */
    public final void m489setAnimationMode97h66l8(int i) {
        this.animationMode.setValue(MarqueeAnimationMode.m477boximpl(i));
    }

    @Override // androidx.compose.ui.layout.LayoutModifier
    /* renamed from: measure-3p2s80s */
    public MeasureResult mo300measure3p2s80s(MeasureScope measure, Measurable measurable, long j) {
        Intrinsics.checkNotNullParameter(measure, "$this$measure");
        Intrinsics.checkNotNullParameter(measurable, "measurable");
        final Placeable placeableMo3396measureBRTryo0 = measurable.mo3396measureBRTryo0(Constraints.m4337copyZbe2FdA$default(j, 0, Integer.MAX_VALUE, 0, 0, 13, null));
        setContainerWidth(ConstraintsKt.m4360constrainWidthK40F9xA(j, placeableMo3396measureBRTryo0.getWidth()));
        setContentWidth(placeableMo3396measureBRTryo0.getWidth());
        return MeasureScope.layout$default(measure, getContainerWidth(), placeableMo3396measureBRTryo0.getHeight(), null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.foundation.MarqueeModifier$measure$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                invoke2(placementScope);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Placeable.PlacementScope layout) {
                Intrinsics.checkNotNullParameter(layout, "$this$layout");
                Placeable.PlacementScope.placeWithLayer$default(layout, placeableMo3396measureBRTryo0, MathKt.roundToInt((-((Number) this.offset.getValue()).floatValue()) * this.direction), 0, 0.0f, null, 12, null);
            }
        }, 4, null);
    }

    @Override // androidx.compose.ui.draw.DrawModifier
    public void draw(ContentDrawScope contentDrawScope) {
        int spacingPx;
        Intrinsics.checkNotNullParameter(contentDrawScope, "<this>");
        float fFloatValue = this.offset.getValue().floatValue();
        float f = this.direction;
        float f2 = fFloatValue * f;
        boolean z = f != 1.0f ? this.offset.getValue().floatValue() < ((float) getContainerWidth()) : this.offset.getValue().floatValue() < ((float) getContentWidth());
        boolean z2 = this.direction != 1.0f ? this.offset.getValue().floatValue() > ((float) getSpacingPx()) : this.offset.getValue().floatValue() > ((float) ((getContentWidth() + getSpacingPx()) - getContainerWidth()));
        if (this.direction == 1.0f) {
            spacingPx = getContentWidth() + getSpacingPx();
        } else {
            spacingPx = (-getContentWidth()) - getSpacingPx();
        }
        float f3 = spacingPx;
        ContentDrawScope contentDrawScope2 = contentDrawScope;
        float fM1705getHeightimpl = Size.m1705getHeightimpl(contentDrawScope2.mo2417getSizeNHjbRc());
        int iM1866getIntersectrtfAjoo = ClipOp.INSTANCE.m1866getIntersectrtfAjoo();
        DrawContext drawContext = contentDrawScope2.getDrawContext();
        long jMo2342getSizeNHjbRc = drawContext.mo2342getSizeNHjbRc();
        drawContext.getCanvas().save();
        drawContext.getTransform().mo2345clipRectN_I0leg(f2, 0.0f, f2 + getContainerWidth(), fM1705getHeightimpl, iM1866getIntersectrtfAjoo);
        if (z) {
            contentDrawScope.drawContent();
        }
        if (z2) {
            contentDrawScope2.getDrawContext().getTransform().translate(f3, 0.0f);
            contentDrawScope.drawContent();
            contentDrawScope2.getDrawContext().getTransform().translate(-f3, -0.0f);
        }
        drawContext.getCanvas().restore();
        drawContext.mo2343setSizeuvyYCjk(jMo2342getSizeNHjbRc);
    }

    @Override // androidx.compose.ui.focus.FocusEventModifier
    public void onFocusEvent(FocusState focusState) {
        Intrinsics.checkNotNullParameter(focusState, "focusState");
        setHasFocus(focusState.getHasFocus());
    }

    public final Object runAnimation(Continuation<? super Unit> continuation) throws Throwable {
        if (this.iterations <= 0) {
            return Unit.INSTANCE;
        }
        Object objWithContext = BuildersKt.withContext(FixedMotionDurationScale.INSTANCE, new AnonymousClass2(null), continuation);
        return objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithContext : Unit.INSTANCE;
    }

    /* compiled from: BasicMarquee.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.MarqueeModifier$runAnimation$2", f = "BasicMarquee.kt", i = {}, l = {272}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: androidx.compose.foundation.MarqueeModifier$runAnimation$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MarqueeModifier.this.new AnonymousClass2(continuation);
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
                final MarqueeModifier marqueeModifier = MarqueeModifier.this;
                this.label = 1;
                if (FlowKt.collectLatest(SnapshotStateKt.snapshotFlow(new Function0<Float>() { // from class: androidx.compose.foundation.MarqueeModifier.runAnimation.2.1
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final Float invoke() {
                        if (marqueeModifier.getContentWidth() <= marqueeModifier.getContainerWidth()) {
                            return null;
                        }
                        if (!MarqueeAnimationMode.m480equalsimpl0(marqueeModifier.m488getAnimationModeZbEOnfQ(), MarqueeAnimationMode.INSTANCE.m487getWhileFocusedZbEOnfQ()) || marqueeModifier.getHasFocus()) {
                            return Float.valueOf(marqueeModifier.getContentWidth() + marqueeModifier.getSpacingPx());
                        }
                        return null;
                    }
                }), new C00132(MarqueeModifier.this, null), this) == coroutine_suspended) {
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

        /* compiled from: BasicMarquee.kt */
        @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u008a@"}, d2 = {"<anonymous>", "", "contentWithSpacingWidth", ""}, k = 3, mv = {1, 8, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.MarqueeModifier$runAnimation$2$2", f = "BasicMarquee.kt", i = {0, 0}, l = {285, OlympusImageProcessingMakernoteDirectory.TagWbGLevel, 289, 289}, m = "invokeSuspend", n = {"contentWithSpacingWidth", "spec"}, s = {"L$0", "L$1"})
        /* renamed from: androidx.compose.foundation.MarqueeModifier$runAnimation$2$2, reason: invalid class name and collision with other inner class name */
        static final class C00132 extends SuspendLambda implements Function2<Float, Continuation<? super Unit>, Object> {
            /* synthetic */ Object L$0;
            Object L$1;
            int label;
            final /* synthetic */ MarqueeModifier this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00132(MarqueeModifier marqueeModifier, Continuation<? super C00132> continuation) {
                super(2, continuation);
                this.this$0 = marqueeModifier;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C00132 c00132 = new C00132(this.this$0, continuation);
                c00132.L$0 = obj;
                return c00132;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Float f, Continuation<? super Unit> continuation) {
                return ((C00132) create(f, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Removed duplicated region for block: B:30:0x00c3 A[RETURN] */
            /* JADX WARN: Type inference failed for: r3v0 */
            /* JADX WARN: Type inference failed for: r3v3, types: [java.lang.Float, java.lang.Object] */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r19) throws java.lang.Throwable {
                /*
                    Method dump skipped, instructions count: 226
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.MarqueeModifier.AnonymousClass2.C00132.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }
    }

    public final void setSpacing(MarqueeSpacing marqueeSpacing) {
        Intrinsics.checkNotNullParameter(marqueeSpacing, "<set-?>");
        this.spacing.setValue(marqueeSpacing);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getSpacingPx() {
        return ((Number) this.spacingPx.getValue()).intValue();
    }
}
