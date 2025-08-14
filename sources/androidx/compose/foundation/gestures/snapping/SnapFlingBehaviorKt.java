package androidx.compose.foundation.gestures.snapping;

import androidx.compose.animation.SplineBasedFloatDecayAnimationSpec_androidKt;
import androidx.compose.animation.core.AnimationScope;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.DecayAnimationSpec;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.foundation.gestures.ScrollScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.exifinterface.media.ExifInterface;
import com.facebook.react.uimanager.events.TouchesHelper;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.ClosedFloatingPointRange;
import kotlin.ranges.RangesKt;

/* compiled from: SnapFlingBehavior.kt */
@Metadata(d1 = {"\u0000\u0082\u0001\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000f\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0017\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0082\b\u001a \u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0000\u001a\u0015\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0011\u001a\u00020\u0012H\u0007¢\u0006\u0002\u0010\u0017\u001an\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u001a0\u0019*\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\b2\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u001a0\u001e2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\b0 2!\u0010!\u001a\u001d\u0012\u0013\u0012\u00110\b¢\u0006\f\b#\u0012\b\b$\u0012\u0004\b\b(%\u0012\u0004\u0012\u00020\u000b0\"H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010&\u001av\u0010'\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u001a0\u0019*\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\b2\u0006\u0010(\u001a\u00020\b2\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u001a0\u001e2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\b0*2!\u0010!\u001a\u001d\u0012\u0013\u0012\u00110\b¢\u0006\f\b#\u0012\b\b$\u0012\u0004\b\b(%\u0012\u0004\u0012\u00020\u000b0\"H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010+\u001ax\u0010,\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u001a0\u0019*\u00020\u001b2\u0006\u0010-\u001a\u00020\b2\u0006\u0010.\u001a\u00020\b2\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u001a002\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142!\u0010!\u001a\u001d\u0012\u0013\u0012\u00110\b¢\u0006\f\b#\u0012\b\b$\u0012\u0004\b\b(%\u0012\u0004\u0012\u00020\u000b0\"H\u0082@ø\u0001\u0000¢\u0006\u0002\u00101\u001a\u0014\u00102\u001a\u00020\b*\u00020\b2\u0006\u00103\u001a\u00020\bH\u0002\u001a(\u00104\u001a\u0002H5\"\u000e\b\u0000\u00105*\b\u0012\u0004\u0012\u0002H506*\b\u0012\u0004\u0012\u0002H507H\u0082\u0002¢\u0006\u0002\u00108\u001a(\u00109\u001a\u0002H5\"\u000e\b\u0000\u00105*\b\u0012\u0004\u0012\u0002H506*\b\u0012\u0004\u0012\u0002H507H\u0082\u0002¢\u0006\u0002\u00108\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0019\u0010\u0002\u001a\u00020\u0003X\u0080\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0006\u001a\u0004\b\u0004\u0010\u0005\"\u000e\u0010\u0007\u001a\u00020\bX\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\bX\u0080T¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006:"}, d2 = {"DEBUG", "", "MinFlingVelocityDp", "Landroidx/compose/ui/unit/Dp;", "getMinFlingVelocityDp", "()F", "F", "NoDistance", "", "NoVelocity", "debugLog", "", "generateMsg", "Lkotlin/Function0;", "", "findClosestOffset", "velocity", "snapLayoutInfoProvider", "Landroidx/compose/foundation/gestures/snapping/SnapLayoutInfoProvider;", "density", "Landroidx/compose/ui/unit/Density;", "rememberSnapFlingBehavior", "Landroidx/compose/foundation/gestures/snapping/SnapFlingBehavior;", "(Landroidx/compose/foundation/gestures/snapping/SnapLayoutInfoProvider;Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/gestures/snapping/SnapFlingBehavior;", "animateDecay", "Landroidx/compose/foundation/gestures/snapping/AnimationResult;", "Landroidx/compose/animation/core/AnimationVector1D;", "Landroidx/compose/foundation/gestures/ScrollScope;", "targetOffset", "animationState", "Landroidx/compose/animation/core/AnimationState;", "decayAnimationSpec", "Landroidx/compose/animation/core/DecayAnimationSpec;", "onAnimationStep", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "delta", "(Landroidx/compose/foundation/gestures/ScrollScope;FLandroidx/compose/animation/core/AnimationState;Landroidx/compose/animation/core/DecayAnimationSpec;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "animateSnap", "cancelOffset", "snapAnimationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "(Landroidx/compose/foundation/gestures/ScrollScope;FFLandroidx/compose/animation/core/AnimationState;Landroidx/compose/animation/core/AnimationSpec;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "approach", "initialTargetOffset", "initialVelocity", "animation", "Landroidx/compose/foundation/gestures/snapping/ApproachAnimation;", "(Landroidx/compose/foundation/gestures/ScrollScope;FFLandroidx/compose/foundation/gestures/snapping/ApproachAnimation;Landroidx/compose/foundation/gestures/snapping/SnapLayoutInfoProvider;Landroidx/compose/ui/unit/Density;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "coerceToTarget", TouchesHelper.TARGET_KEY, "component1", ExifInterface.GPS_DIRECTION_TRUE, "", "Lkotlin/ranges/ClosedFloatingPointRange;", "(Lkotlin/ranges/ClosedFloatingPointRange;)Ljava/lang/Comparable;", "component2", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class SnapFlingBehaviorKt {
    private static final boolean DEBUG = false;
    private static final float MinFlingVelocityDp = Dp.m4390constructorimpl(400);
    public static final float NoDistance = 0.0f;
    public static final float NoVelocity = 0.0f;

    /* compiled from: SnapFlingBehavior.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt", f = "SnapFlingBehavior.kt", i = {0, 0, 0}, l = {406}, m = "animateDecay", n = {"animationState", "previousValue", "targetOffset"}, s = {"L$0", "L$1", "F$0"})
    /* renamed from: androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt$animateDecay$1, reason: invalid class name */
    static final class AnonymousClass1 extends ContinuationImpl {
        float F$0;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SnapFlingBehaviorKt.animateDecay(null, 0.0f, null, null, null, this);
        }
    }

    /* compiled from: SnapFlingBehavior.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt", f = "SnapFlingBehavior.kt", i = {0, 0, 0, 0}, l = {451}, m = "animateSnap", n = {"animationState", "consumedUpToNow", "targetOffset", "initialVelocity"}, s = {"L$0", "L$1", "F$0", "F$1"})
    /* renamed from: androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt$animateSnap$1, reason: invalid class name and case insensitive filesystem */
    static final class C02981 extends ContinuationImpl {
        float F$0;
        float F$1;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C02981(Continuation<? super C02981> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SnapFlingBehaviorKt.animateSnap(null, 0.0f, 0.0f, null, null, null, this);
        }
    }

    /* compiled from: SnapFlingBehavior.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt", f = "SnapFlingBehavior.kt", i = {0, 0}, l = {311}, m = "approach", n = {"snapLayoutInfoProvider", "density"}, s = {"L$0", "L$1"})
    /* renamed from: androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt$approach$1, reason: invalid class name and case insensitive filesystem */
    static final class C03001 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C03001(Continuation<? super C03001> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SnapFlingBehaviorKt.approach(null, 0.0f, 0.0f, null, null, null, null, this);
        }
    }

    private static final void debugLog(Function0<String> function0) {
    }

    private static final boolean findClosestOffset$isValidDistance(float f) {
        return (f == Float.POSITIVE_INFINITY || f == Float.NEGATIVE_INFINITY) ? false : true;
    }

    public static final float getMinFlingVelocityDp() {
        return MinFlingVelocityDp;
    }

    public static final SnapFlingBehavior rememberSnapFlingBehavior(SnapLayoutInfoProvider snapLayoutInfoProvider, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(snapLayoutInfoProvider, "snapLayoutInfoProvider");
        composer.startReplaceableGroup(-473984552);
        ComposerKt.sourceInformation(composer, "C(rememberSnapFlingBehavior)271@11479L7,272@11549L26,273@11587L447:SnapFlingBehavior.kt#ppz6w6");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-473984552, i, -1, "androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior (SnapFlingBehavior.kt:268)");
        }
        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
        ComposerKt.sourceInformationMarkerStart(composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = composer.consume(localDensity);
        ComposerKt.sourceInformationMarkerEnd(composer);
        Density density = (Density) objConsume;
        DecayAnimationSpec decayAnimationSpecRememberSplineBasedDecay = SplineBasedFloatDecayAnimationSpec_androidKt.rememberSplineBasedDecay(composer, 0);
        composer.startReplaceableGroup(1618982084);
        ComposerKt.sourceInformation(composer, "CC(remember)P(1,2,3):Composables.kt#9igjgp");
        boolean zChanged = composer.changed(snapLayoutInfoProvider) | composer.changed(decayAnimationSpecRememberSplineBasedDecay) | composer.changed(density);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            Object snapFlingBehavior = new SnapFlingBehavior(snapLayoutInfoProvider, AnimationSpecKt.tween$default(0, 0, EasingKt.getLinearEasing(), 3, null), decayAnimationSpecRememberSplineBasedDecay, AnimationSpecKt.spring$default(0.0f, 400.0f, null, 5, null), density, 0.0f, 32, null);
            composer.updateRememberedValue(snapFlingBehavior);
            objRememberedValue = snapFlingBehavior;
        }
        composer.endReplaceableGroup();
        SnapFlingBehavior snapFlingBehavior2 = (SnapFlingBehavior) objRememberedValue;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return snapFlingBehavior2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object approach(androidx.compose.foundation.gestures.ScrollScope r7, float r8, float r9, androidx.compose.foundation.gestures.snapping.ApproachAnimation<java.lang.Float, androidx.compose.animation.core.AnimationVector1D> r10, androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider r11, androidx.compose.ui.unit.Density r12, kotlin.jvm.functions.Function1<? super java.lang.Float, kotlin.Unit> r13, kotlin.coroutines.Continuation<? super androidx.compose.foundation.gestures.snapping.AnimationResult<java.lang.Float, androidx.compose.animation.core.AnimationVector1D>> r14) {
        /*
            boolean r0 = r14 instanceof androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt.C03001
            if (r0 == 0) goto L14
            r0 = r14
            androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt$approach$1 r0 = (androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt.C03001) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r14 = r0.label
            int r14 = r14 - r2
            r0.label = r14
            goto L19
        L14:
            androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt$approach$1 r0 = new androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt$approach$1
            r0.<init>(r14)
        L19:
            r6 = r0
            java.lang.Object r14 = r6.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r6.label
            r2 = 1
            if (r1 == 0) goto L3d
            if (r1 != r2) goto L35
            java.lang.Object r7 = r6.L$1
            r12 = r7
            androidx.compose.ui.unit.Density r12 = (androidx.compose.ui.unit.Density) r12
            java.lang.Object r7 = r6.L$0
            r11 = r7
            androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider r11 = (androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider) r11
            kotlin.ResultKt.throwOnFailure(r14)
            goto L58
        L35:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L3d:
            kotlin.ResultKt.throwOnFailure(r14)
            java.lang.Float r3 = kotlin.coroutines.jvm.internal.Boxing.boxFloat(r8)
            java.lang.Float r4 = kotlin.coroutines.jvm.internal.Boxing.boxFloat(r9)
            r6.L$0 = r11
            r6.L$1 = r12
            r6.label = r2
            r1 = r10
            r2 = r7
            r5 = r13
            java.lang.Object r14 = r1.approachAnimation(r2, r3, r4, r5, r6)
            if (r14 != r0) goto L58
            return r0
        L58:
            androidx.compose.foundation.gestures.snapping.AnimationResult r14 = (androidx.compose.foundation.gestures.snapping.AnimationResult) r14
            androidx.compose.animation.core.AnimationState r7 = r14.component2()
            java.lang.Object r8 = r7.getVelocity()
            java.lang.Number r8 = (java.lang.Number) r8
            float r8 = r8.floatValue()
            float r8 = findClosestOffset(r8, r11, r12)
            androidx.compose.foundation.gestures.snapping.AnimationResult r9 = new androidx.compose.foundation.gestures.snapping.AnimationResult
            java.lang.Float r8 = kotlin.coroutines.jvm.internal.Boxing.boxFloat(r8)
            r9.<init>(r8, r7)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt.approach(androidx.compose.foundation.gestures.ScrollScope, float, float, androidx.compose.foundation.gestures.snapping.ApproachAnimation, androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider, androidx.compose.ui.unit.Density, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x003f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final float findClosestOffset(float r2, androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider r3, androidx.compose.ui.unit.Density r4) {
        /*
            java.lang.String r0 = "snapLayoutInfoProvider"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.lang.String r0 = "density"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            kotlin.ranges.ClosedFloatingPointRange r3 = r3.calculateSnappingOffsetBounds(r4)
            java.lang.Comparable r4 = component1(r3)
            java.lang.Number r4 = (java.lang.Number) r4
            float r4 = r4.floatValue()
            java.lang.Comparable r3 = component2(r3)
            java.lang.Number r3 = (java.lang.Number) r3
            float r3 = r3.floatValue()
            float r2 = java.lang.Math.signum(r2)
            r0 = 0
            int r1 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r1 != 0) goto L39
            float r2 = java.lang.Math.abs(r3)
            float r1 = java.lang.Math.abs(r4)
            int r2 = (r2 > r1 ? 1 : (r2 == r1 ? 0 : -1))
            if (r2 > 0) goto L49
            goto L3f
        L39:
            r1 = 1065353216(0x3f800000, float:1.0)
            int r1 = (r2 > r1 ? 1 : (r2 == r1 ? 0 : -1))
            if (r1 != 0) goto L41
        L3f:
            r4 = r3
            goto L49
        L41:
            r3 = -1082130432(0xffffffffbf800000, float:-1.0)
            int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r2 != 0) goto L48
            goto L49
        L48:
            r4 = r0
        L49:
            boolean r2 = findClosestOffset$isValidDistance(r4)
            if (r2 == 0) goto L50
            r0 = r4
        L50:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt.findClosestOffset(float, androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider, androidx.compose.ui.unit.Density):float");
    }

    private static final <T extends Comparable<? super T>> T component1(ClosedFloatingPointRange<T> closedFloatingPointRange) {
        Intrinsics.checkNotNullParameter(closedFloatingPointRange, "<this>");
        return closedFloatingPointRange.getStart();
    }

    private static final <T extends Comparable<? super T>> T component2(ClosedFloatingPointRange<T> closedFloatingPointRange) {
        Intrinsics.checkNotNullParameter(closedFloatingPointRange, "<this>");
        return closedFloatingPointRange.getEndInclusive();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object animateDecay(final androidx.compose.foundation.gestures.ScrollScope r5, final float r6, androidx.compose.animation.core.AnimationState<java.lang.Float, androidx.compose.animation.core.AnimationVector1D> r7, androidx.compose.animation.core.DecayAnimationSpec<java.lang.Float> r8, final kotlin.jvm.functions.Function1<? super java.lang.Float, kotlin.Unit> r9, kotlin.coroutines.Continuation<? super androidx.compose.foundation.gestures.snapping.AnimationResult<java.lang.Float, androidx.compose.animation.core.AnimationVector1D>> r10) {
        /*
            boolean r0 = r10 instanceof androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r10
            androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt$animateDecay$1 r0 = (androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L19
        L14:
            androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt$animateDecay$1 r0 = new androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt$animateDecay$1
            r0.<init>(r10)
        L19:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3c
            if (r2 != r3) goto L34
            float r6 = r0.F$0
            java.lang.Object r5 = r0.L$1
            kotlin.jvm.internal.Ref$FloatRef r5 = (kotlin.jvm.internal.Ref.FloatRef) r5
            java.lang.Object r7 = r0.L$0
            androidx.compose.animation.core.AnimationState r7 = (androidx.compose.animation.core.AnimationState) r7
            kotlin.ResultKt.throwOnFailure(r10)
            goto L6e
        L34:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L3c:
            kotlin.ResultKt.throwOnFailure(r10)
            kotlin.jvm.internal.Ref$FloatRef r10 = new kotlin.jvm.internal.Ref$FloatRef
            r10.<init>()
            java.lang.Object r2 = r7.getVelocity()
            java.lang.Number r2 = (java.lang.Number) r2
            float r2 = r2.floatValue()
            r4 = 0
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 != 0) goto L55
            r2 = r3
            goto L56
        L55:
            r2 = 0
        L56:
            r2 = r2 ^ r3
            androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt$animateDecay$2 r4 = new androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt$animateDecay$2
            r4.<init>()
            kotlin.jvm.functions.Function1 r4 = (kotlin.jvm.functions.Function1) r4
            r0.L$0 = r7
            r0.L$1 = r10
            r0.F$0 = r6
            r0.label = r3
            java.lang.Object r5 = androidx.compose.animation.core.SuspendAnimationKt.animateDecay(r7, r8, r2, r4, r0)
            if (r5 != r1) goto L6d
            return r1
        L6d:
            r5 = r10
        L6e:
            androidx.compose.foundation.gestures.snapping.AnimationResult r8 = new androidx.compose.foundation.gestures.snapping.AnimationResult
            float r5 = r5.element
            float r6 = r6 - r5
            java.lang.Float r5 = kotlin.coroutines.jvm.internal.Boxing.boxFloat(r6)
            r8.<init>(r5, r7)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt.animateDecay(androidx.compose.foundation.gestures.ScrollScope, float, androidx.compose.animation.core.AnimationState, androidx.compose.animation.core.DecayAnimationSpec, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void animateDecay$consumeDelta(AnimationScope<Float, AnimationVector1D> animationScope, ScrollScope scrollScope, Function1<? super Float, Unit> function1, float f) {
        float fScrollBy = scrollScope.scrollBy(f);
        function1.invoke(Float.valueOf(fScrollBy));
        if (Math.abs(f - fScrollBy) > 0.5f) {
            animationScope.cancelAnimation();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0016  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object animateSnap(final androidx.compose.foundation.gestures.ScrollScope r12, float r13, final float r14, androidx.compose.animation.core.AnimationState<java.lang.Float, androidx.compose.animation.core.AnimationVector1D> r15, androidx.compose.animation.core.AnimationSpec<java.lang.Float> r16, final kotlin.jvm.functions.Function1<? super java.lang.Float, kotlin.Unit> r17, kotlin.coroutines.Continuation<? super androidx.compose.foundation.gestures.snapping.AnimationResult<java.lang.Float, androidx.compose.animation.core.AnimationVector1D>> r18) {
        /*
            r0 = r18
            boolean r1 = r0 instanceof androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt.C02981
            if (r1 == 0) goto L16
            r1 = r0
            androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt$animateSnap$1 r1 = (androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt.C02981) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L16
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L1b
        L16:
            androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt$animateSnap$1 r1 = new androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt$animateSnap$1
            r1.<init>(r0)
        L1b:
            r7 = r1
            java.lang.Object r0 = r7.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r7.label
            r3 = 1
            if (r2 == 0) goto L43
            if (r2 != r3) goto L3b
            float r1 = r7.F$1
            float r2 = r7.F$0
            java.lang.Object r3 = r7.L$1
            kotlin.jvm.internal.Ref$FloatRef r3 = (kotlin.jvm.internal.Ref.FloatRef) r3
            java.lang.Object r4 = r7.L$0
            androidx.compose.animation.core.AnimationState r4 = (androidx.compose.animation.core.AnimationState) r4
            kotlin.ResultKt.throwOnFailure(r0)
            r10 = r2
            r0 = r4
            goto L93
        L3b:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L43:
            kotlin.ResultKt.throwOnFailure(r0)
            kotlin.jvm.internal.Ref$FloatRef r0 = new kotlin.jvm.internal.Ref$FloatRef
            r0.<init>()
            java.lang.Object r2 = r15.getVelocity()
            java.lang.Number r2 = (java.lang.Number) r2
            float r8 = r2.floatValue()
            java.lang.Float r4 = kotlin.coroutines.jvm.internal.Boxing.boxFloat(r13)
            java.lang.Object r2 = r15.getVelocity()
            java.lang.Number r2 = (java.lang.Number) r2
            float r2 = r2.floatValue()
            r5 = 0
            int r2 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1))
            if (r2 != 0) goto L6a
            r2 = r3
            goto L6b
        L6a:
            r2 = 0
        L6b:
            r5 = r2 ^ 1
            androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt$animateSnap$2 r2 = new androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt$animateSnap$2
            r6 = r12
            r9 = r14
            r10 = r17
            r2.<init>()
            r6 = r2
            kotlin.jvm.functions.Function1 r6 = (kotlin.jvm.functions.Function1) r6
            r9 = r15
            r7.L$0 = r9
            r7.L$1 = r0
            r10 = r13
            r7.F$0 = r10
            r7.F$1 = r8
            r7.label = r3
            r2 = r15
            r3 = r4
            r4 = r16
            java.lang.Object r2 = androidx.compose.animation.core.SuspendAnimationKt.animateTo(r2, r3, r4, r5, r6, r7)
            if (r2 != r1) goto L90
            return r1
        L90:
            r3 = r0
            r1 = r8
            r0 = r9
        L93:
            java.lang.Object r2 = r0.getVelocity()
            java.lang.Number r2 = (java.lang.Number) r2
            float r2 = r2.floatValue()
            float r2 = coerceToTarget(r2, r1)
            androidx.compose.foundation.gestures.snapping.AnimationResult r11 = new androidx.compose.foundation.gestures.snapping.AnimationResult
            float r1 = r3.element
            float r10 = r10 - r1
            java.lang.Float r10 = kotlin.coroutines.jvm.internal.Boxing.boxFloat(r10)
            r1 = 0
            r3 = 0
            r5 = 0
            r7 = 0
            r8 = 29
            r9 = 0
            androidx.compose.animation.core.AnimationState r0 = androidx.compose.animation.core.AnimationStateKt.copy$default(r0, r1, r2, r3, r5, r7, r8, r9)
            r11.<init>(r10, r0)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt.animateSnap(androidx.compose.foundation.gestures.ScrollScope, float, float, androidx.compose.animation.core.AnimationState, androidx.compose.animation.core.AnimationSpec, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float coerceToTarget(float f, float f2) {
        if (f2 == 0.0f) {
            return 0.0f;
        }
        return f2 > 0.0f ? RangesKt.coerceAtMost(f, f2) : RangesKt.coerceAtLeast(f, f2);
    }
}
