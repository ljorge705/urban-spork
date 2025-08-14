package androidx.compose.foundation.text.selection;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationVector2D;
import androidx.compose.animation.core.SpringSpec;
import androidx.compose.animation.core.TwoWayConverter;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.foundation.text.selection.SelectionMagnifierKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
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
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: SelectionMagnifier.kt */
@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a$\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00020\fH\u0003ø\u0001\u0000¢\u0006\u0002\u0010\r\u001aF\u0010\u000e\u001a\u00020\u000f*\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00020\f2'\u0010\u0011\u001a#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00020\f¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u000f0\u0012H\u0000ø\u0001\u0000\"\u0017\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0002\n\u0000\"\u0013\u0010\u0003\u001a\u00020\u0002X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0004\"\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001d\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\bX\u0082\u0004ø\u0001\u0000¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0016"}, d2 = {"MagnifierSpringSpec", "Landroidx/compose/animation/core/SpringSpec;", "Landroidx/compose/ui/geometry/Offset;", "OffsetDisplacementThreshold", "J", "UnspecifiedAnimationVector2D", "Landroidx/compose/animation/core/AnimationVector2D;", "UnspecifiedSafeOffsetVectorConverter", "Landroidx/compose/animation/core/TwoWayConverter;", "rememberAnimatedMagnifierPosition", "Landroidx/compose/runtime/State;", "targetCalculation", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "animatedSelectionMagnifier", "Landroidx/compose/ui/Modifier;", "magnifierCenter", "platformMagnifier", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "animatedCenter", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class SelectionMagnifierKt {
    private static final SpringSpec<Offset> MagnifierSpringSpec;
    private static final long OffsetDisplacementThreshold;
    private static final AnimationVector2D UnspecifiedAnimationVector2D = new AnimationVector2D(Float.NaN, Float.NaN);
    private static final TwoWayConverter<Offset, AnimationVector2D> UnspecifiedSafeOffsetVectorConverter = VectorConvertersKt.TwoWayConverter(new Function1<Offset, AnimationVector2D>() { // from class: androidx.compose.foundation.text.selection.SelectionMagnifierKt$UnspecifiedSafeOffsetVectorConverter$1
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ AnimationVector2D invoke(Offset offset) {
            return m1098invokek4lQ0M(offset.getPackedValue());
        }

        /* renamed from: invoke-k-4lQ0M, reason: not valid java name */
        public final AnimationVector2D m1098invokek4lQ0M(long j) {
            if (OffsetKt.m1658isSpecifiedk4lQ0M(j)) {
                return new AnimationVector2D(Offset.m1639getXimpl(j), Offset.m1640getYimpl(j));
            }
            return SelectionMagnifierKt.UnspecifiedAnimationVector2D;
        }
    }, new Function1<AnimationVector2D, Offset>() { // from class: androidx.compose.foundation.text.selection.SelectionMagnifierKt$UnspecifiedSafeOffsetVectorConverter$2
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Offset invoke(AnimationVector2D animationVector2D) {
            return Offset.m1628boximpl(m1099invoketuRUvjQ(animationVector2D));
        }

        /* renamed from: invoke-tuRUvjQ, reason: not valid java name */
        public final long m1099invoketuRUvjQ(AnimationVector2D it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return OffsetKt.Offset(it.getV1(), it.getV2());
        }
    });

    static {
        long jOffset = OffsetKt.Offset(0.01f, 0.01f);
        OffsetDisplacementThreshold = jOffset;
        MagnifierSpringSpec = new SpringSpec<>(0.0f, 0.0f, Offset.m1628boximpl(jOffset), 3, null);
    }

    /* compiled from: SelectionMagnifier.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\u000b¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "Landroidx/compose/ui/Modifier;", "invoke", "(Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/Modifier;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    /* renamed from: androidx.compose.foundation.text.selection.SelectionMagnifierKt$animatedSelectionMagnifier$1, reason: invalid class name */
    static final class AnonymousClass1 extends Lambda implements Function3<Modifier, Composer, Integer, Modifier> {
        final /* synthetic */ Function0<Offset> $magnifierCenter;
        final /* synthetic */ Function1<Function0<Offset>, Modifier> $platformMagnifier;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass1(Function0<Offset> function0, Function1<? super Function0<Offset>, ? extends Modifier> function1) {
            super(3);
            this.$magnifierCenter = function0;
            this.$platformMagnifier = function1;
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier, Composer composer, Integer num) {
            return invoke(modifier, composer, num.intValue());
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final Modifier invoke(Modifier composed, Composer composer, int i) {
            Intrinsics.checkNotNullParameter(composed, "$this$composed");
            composer.startReplaceableGroup(759876635);
            ComposerKt.sourceInformation(composer, "C67@2573L70,68@2682L18:SelectionMagnifier.kt#eksfi3");
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(759876635, i, -1, "androidx.compose.foundation.text.selection.animatedSelectionMagnifier.<anonymous> (SelectionMagnifier.kt:66)");
            }
            final State stateRememberAnimatedMagnifierPosition = SelectionMagnifierKt.rememberAnimatedMagnifierPosition(this.$magnifierCenter, composer, 0);
            Function1<Function0<Offset>, Modifier> function1 = this.$platformMagnifier;
            composer.startReplaceableGroup(1157296644);
            ComposerKt.sourceInformation(composer, "CC(remember)P(1):Composables.kt#9igjgp");
            boolean zChanged = composer.changed(stateRememberAnimatedMagnifierPosition);
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = (Function0) new Function0<Offset>() { // from class: androidx.compose.foundation.text.selection.SelectionMagnifierKt$animatedSelectionMagnifier$1$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Offset invoke() {
                        return Offset.m1628boximpl(m1100invokeF1C5BW0());
                    }

                    /* renamed from: invoke-F1C5BW0, reason: not valid java name */
                    public final long m1100invokeF1C5BW0() {
                        return SelectionMagnifierKt.AnonymousClass1.invoke$lambda$0(stateRememberAnimatedMagnifierPosition);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            composer.endReplaceableGroup();
            Modifier modifier = (Modifier) function1.invoke(objRememberedValue);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composer.endReplaceableGroup();
            return modifier;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final long invoke$lambda$0(State<Offset> state) {
            return state.getValue().getPackedValue();
        }
    }

    public static final Modifier animatedSelectionMagnifier(Modifier modifier, Function0<Offset> magnifierCenter, Function1<? super Function0<Offset>, ? extends Modifier> platformMagnifier) {
        Intrinsics.checkNotNullParameter(modifier, "<this>");
        Intrinsics.checkNotNullParameter(magnifierCenter, "magnifierCenter");
        Intrinsics.checkNotNullParameter(platformMagnifier, "platformMagnifier");
        return ComposedModifierKt.composed$default(modifier, null, new AnonymousClass1(magnifierCenter, platformMagnifier), 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final State<Offset> rememberAnimatedMagnifierPosition(Function0<Offset> function0, Composer composer, int i) {
        composer.startReplaceableGroup(-1589795249);
        ComposerKt.sourceInformation(composer, "C(rememberAnimatedMagnifierPosition)79@3044L46,80@3112L208,84@3325L1186:SelectionMagnifier.kt#eksfi3");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1589795249, i, -1, "androidx.compose.foundation.text.selection.rememberAnimatedMagnifierPosition (SelectionMagnifier.kt:76)");
        }
        composer.startReplaceableGroup(-492369756);
        ComposerKt.sourceInformation(composer, "CC(remember):Composables.kt#9igjgp");
        Object objRememberedValue = composer.rememberedValue();
        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = SnapshotStateKt.derivedStateOf(function0);
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        State state = (State) objRememberedValue;
        composer.startReplaceableGroup(-492369756);
        ComposerKt.sourceInformation(composer, "CC(remember):Composables.kt#9igjgp");
        Object objRememberedValue2 = composer.rememberedValue();
        if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue2 = new Animatable(Offset.m1628boximpl(rememberAnimatedMagnifierPosition$lambda$1(state)), UnspecifiedSafeOffsetVectorConverter, Offset.m1628boximpl(OffsetDisplacementThreshold));
            composer.updateRememberedValue(objRememberedValue2);
        }
        composer.endReplaceableGroup();
        Animatable animatable = (Animatable) objRememberedValue2;
        EffectsKt.LaunchedEffect(Unit.INSTANCE, new C03731(state, animatable, null), composer, 70);
        State<Offset> stateAsState = animatable.asState();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return stateAsState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long rememberAnimatedMagnifierPosition$lambda$1(State<Offset> state) {
        return state.getValue().getPackedValue();
    }

    /* compiled from: SelectionMagnifier.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.selection.SelectionMagnifierKt$rememberAnimatedMagnifierPosition$1", f = "SelectionMagnifier.kt", i = {}, l = {88}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: androidx.compose.foundation.text.selection.SelectionMagnifierKt$rememberAnimatedMagnifierPosition$1, reason: invalid class name and case insensitive filesystem */
    static final class C03731 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Animatable<Offset, AnimationVector2D> $animatable;
        final /* synthetic */ State<Offset> $targetValue$delegate;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03731(State<Offset> state, Animatable<Offset, AnimationVector2D> animatable, Continuation<? super C03731> continuation) {
            super(2, continuation);
            this.$targetValue$delegate = state;
            this.$animatable = animatable;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C03731 c03731 = new C03731(this.$targetValue$delegate, this.$animatable, continuation);
            c03731.L$0 = obj;
            return c03731;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03731) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                final CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                final State<Offset> state = this.$targetValue$delegate;
                Flow flowSnapshotFlow = SnapshotStateKt.snapshotFlow(new Function0<Offset>() { // from class: androidx.compose.foundation.text.selection.SelectionMagnifierKt.rememberAnimatedMagnifierPosition.1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Offset invoke() {
                        return Offset.m1628boximpl(m1101invokeF1C5BW0());
                    }

                    /* renamed from: invoke-F1C5BW0, reason: not valid java name */
                    public final long m1101invokeF1C5BW0() {
                        return SelectionMagnifierKt.rememberAnimatedMagnifierPosition$lambda$1(state);
                    }
                });
                final Animatable<Offset, AnimationVector2D> animatable = this.$animatable;
                this.label = 1;
                if (flowSnapshotFlow.collect(new FlowCollector<Offset>() { // from class: androidx.compose.foundation.text.selection.SelectionMagnifierKt.rememberAnimatedMagnifierPosition.1.2
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Offset offset, Continuation continuation) {
                        return m1102emit3MmeM6k(offset.getPackedValue(), continuation);
                    }

                    /* renamed from: emit-3MmeM6k, reason: not valid java name */
                    public final Object m1102emit3MmeM6k(long j, Continuation<? super Unit> continuation) {
                        if (OffsetKt.m1658isSpecifiedk4lQ0M(animatable.getValue().getPackedValue()) && OffsetKt.m1658isSpecifiedk4lQ0M(j) && Offset.m1640getYimpl(animatable.getValue().getPackedValue()) != Offset.m1640getYimpl(j)) {
                            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new SelectionMagnifierKt$rememberAnimatedMagnifierPosition$1$2$emit$2(animatable, j, null), 3, null);
                            return Unit.INSTANCE;
                        }
                        Object objSnapTo = animatable.snapTo(Offset.m1628boximpl(j), continuation);
                        return objSnapTo == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objSnapTo : Unit.INSTANCE;
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
}
