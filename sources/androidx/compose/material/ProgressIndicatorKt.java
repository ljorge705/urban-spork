package androidx.compose.material;

import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.CubicBezierEasing;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.animation.core.InfiniteRepeatableSpec;
import androidx.compose.animation.core.InfiniteTransition;
import androidx.compose.animation.core.InfiniteTransitionKt;
import androidx.compose.animation.core.KeyframesSpec;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.FocusableKt;
import androidx.compose.foundation.ProgressSemanticsKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import com.drew.metadata.exif.makernotes.LeicaMakernoteDirectory;
import com.drew.metadata.iptc.IptcDirectory;
import com.facebook.react.uimanager.ViewProps;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.FaceDetectorAvcMLKit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.IntCompanionObject;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ProgressIndicator.kt */
@Metadata(d1 = {"\u0000B\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0016\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0010\u001a3\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010 \u001a\u00020!2\b\b\u0002\u0010\"\u001a\u00020#2\b\b\u0002\u0010$\u001a\u00020\u0005H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b%\u0010&\u001a;\u0010\u001e\u001a\u00020\u001f2\u0006\u0010'\u001a\u00020\u00012\b\b\u0002\u0010 \u001a\u00020!2\b\b\u0002\u0010\"\u001a\u00020#2\b\b\u0002\u0010$\u001a\u00020\u0005H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b(\u0010)\u001a3\u0010*\u001a\u00020\u001f2\b\b\u0002\u0010 \u001a\u00020!2\b\b\u0002\u0010\"\u001a\u00020#2\b\b\u0002\u0010+\u001a\u00020#H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b,\u0010-\u001a;\u0010*\u001a\u00020\u001f2\u0006\u0010'\u001a\u00020\u00012\b\b\u0002\u0010 \u001a\u00020!2\b\b\u0002\u0010\"\u001a\u00020#2\b\b\u0002\u0010+\u001a\u00020#H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b.\u0010/\u001a9\u00100\u001a\u00020\u001f*\u0002012\u0006\u00102\u001a\u00020\u00012\u0006\u00103\u001a\u00020\u00012\u0006\u0010\"\u001a\u00020#2\u0006\u00104\u001a\u000205H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b6\u00107\u001a9\u00108\u001a\u00020\u001f*\u0002012\u0006\u00102\u001a\u00020\u00012\u0006\u00103\u001a\u00020\u00012\u0006\u0010\"\u001a\u00020#2\u0006\u00104\u001a\u000205H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b9\u00107\u001aA\u0010:\u001a\u00020\u001f*\u0002012\u0006\u00102\u001a\u00020\u00012\u0006\u0010$\u001a\u00020\u00052\u0006\u00103\u001a\u00020\u00012\u0006\u0010\"\u001a\u00020#2\u0006\u00104\u001a\u000205H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b;\u0010<\u001a9\u0010=\u001a\u00020\u001f*\u0002012\u0006\u0010>\u001a\u00020\u00012\u0006\u0010?\u001a\u00020\u00012\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u0001H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b@\u0010A\u001a)\u0010B\u001a\u00020\u001f*\u0002012\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u0001H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bC\u0010D\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0013\u0010\u0004\u001a\u00020\u0005X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0006\"\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\r\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u000e\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000f\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0010\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0011\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u0013\u0010\u0012\u001a\u00020\u0005X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0006\"\u0013\u0010\u0013\u001a\u00020\u0005X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0006\"\u000e\u0010\u0014\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0015\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0016\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0017\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0018\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0019\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u001a\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001b\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001c\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u001d\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006E"}, d2 = {"BaseRotationAngle", "", "CircularEasing", "Landroidx/compose/animation/core/CubicBezierEasing;", "CircularIndicatorDiameter", "Landroidx/compose/ui/unit/Dp;", "F", "FirstLineHeadDelay", "", "FirstLineHeadDuration", "FirstLineHeadEasing", "FirstLineTailDelay", "FirstLineTailDuration", "FirstLineTailEasing", "HeadAndTailAnimationDuration", "HeadAndTailDelayDuration", "JumpRotationAngle", "LinearAnimationDuration", "LinearIndicatorHeight", "LinearIndicatorWidth", "RotationAngleOffset", "RotationDuration", "RotationsPerCycle", "SecondLineHeadDelay", "SecondLineHeadDuration", "SecondLineHeadEasing", "SecondLineTailDelay", "SecondLineTailDuration", "SecondLineTailEasing", "StartAngleOffset", "CircularProgressIndicator", "", "modifier", "Landroidx/compose/ui/Modifier;", "color", "Landroidx/compose/ui/graphics/Color;", "strokeWidth", "CircularProgressIndicator-aM-cp0Q", "(Landroidx/compose/ui/Modifier;JFLandroidx/compose/runtime/Composer;II)V", "progress", "CircularProgressIndicator-MBs18nI", "(FLandroidx/compose/ui/Modifier;JFLandroidx/compose/runtime/Composer;II)V", "LinearProgressIndicator", ViewProps.BACKGROUND_COLOR, "LinearProgressIndicator-RIQooxk", "(Landroidx/compose/ui/Modifier;JJLandroidx/compose/runtime/Composer;II)V", "LinearProgressIndicator-eaDK9VM", "(FLandroidx/compose/ui/Modifier;JJLandroidx/compose/runtime/Composer;II)V", "drawCircularIndicator", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "startAngle", "sweep", "stroke", "Landroidx/compose/ui/graphics/drawscope/Stroke;", "drawCircularIndicator-42QJj7c", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;FFJLandroidx/compose/ui/graphics/drawscope/Stroke;)V", "drawDeterminateCircularIndicator", "drawDeterminateCircularIndicator-42QJj7c", "drawIndeterminateCircularIndicator", "drawIndeterminateCircularIndicator-hrjfTZI", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;FFFJLandroidx/compose/ui/graphics/drawscope/Stroke;)V", "drawLinearIndicator", "startFraction", "endFraction", "drawLinearIndicator-42QJj7c", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;FFJF)V", "drawLinearIndicatorBackground", "drawLinearIndicatorBackground-bw27NRU", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;JF)V", "material_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class ProgressIndicatorKt {
    private static final float BaseRotationAngle = 286.0f;
    private static final int FirstLineHeadDelay = 0;
    private static final int FirstLineHeadDuration = 750;
    private static final int FirstLineTailDelay = 333;
    private static final int FirstLineTailDuration = 850;
    private static final int HeadAndTailAnimationDuration = 666;
    private static final int HeadAndTailDelayDuration = 666;
    private static final float JumpRotationAngle = 290.0f;
    private static final int LinearAnimationDuration = 1800;
    private static final float RotationAngleOffset = 216.0f;
    private static final int RotationDuration = 1332;
    private static final int RotationsPerCycle = 5;
    private static final int SecondLineHeadDelay = 1000;
    private static final int SecondLineHeadDuration = 567;
    private static final int SecondLineTailDelay = 1267;
    private static final int SecondLineTailDuration = 533;
    private static final float StartAngleOffset = -90.0f;
    private static final float LinearIndicatorHeight = ProgressIndicatorDefaults.INSTANCE.m1356getStrokeWidthD9Ej5fM();
    private static final float LinearIndicatorWidth = Dp.m4390constructorimpl(FaceDetectorAvcMLKit.FACE_DETECTION_FRAME_WIDTH);
    private static final float CircularIndicatorDiameter = Dp.m4390constructorimpl(40);
    private static final CubicBezierEasing FirstLineHeadEasing = new CubicBezierEasing(0.2f, 0.0f, 0.8f, 1.0f);
    private static final CubicBezierEasing FirstLineTailEasing = new CubicBezierEasing(0.4f, 0.0f, 1.0f, 1.0f);
    private static final CubicBezierEasing SecondLineHeadEasing = new CubicBezierEasing(0.0f, 0.0f, 0.65f, 1.0f);
    private static final CubicBezierEasing SecondLineTailEasing = new CubicBezierEasing(0.1f, 0.0f, 0.45f, 1.0f);
    private static final CubicBezierEasing CircularEasing = new CubicBezierEasing(0.4f, 0.0f, 0.2f, 1.0f);

    /* JADX WARN: Removed duplicated region for block: B:26:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x012f  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0153  */
    /* JADX WARN: Removed duplicated region for block: B:82:? A[RETURN, SYNTHETIC] */
    /* renamed from: LinearProgressIndicator-eaDK9VM, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1364LinearProgressIndicatoreaDK9VM(final float r19, androidx.compose.ui.Modifier r20, long r21, long r23, androidx.compose.runtime.Composer r25, final int r26, final int r27) {
        /*
            Method dump skipped, instructions count: 357
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.ProgressIndicatorKt.m1364LinearProgressIndicatoreaDK9VM(float, androidx.compose.ui.Modifier, long, long, androidx.compose.runtime.Composer, int, int):void");
    }

    /* renamed from: LinearProgressIndicator-RIQooxk, reason: not valid java name */
    public static final void m1363LinearProgressIndicatorRIQooxk(Modifier modifier, long j, long j2, Composer composer, final int i, final int i2) {
        Modifier modifier2;
        int i3;
        long j3;
        long jM1876copywmQWz5c$default;
        final Modifier.Companion companion;
        long jM1242getPrimary0d7_KjU;
        final long j4;
        final long j5;
        Composer composerStartRestartGroup = composer.startRestartGroup(96019871);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(LinearProgressIndicator)P(2,1:c#ui.graphics.Color,0:c#ui.graphics.Color)104@4663L6,107@4788L28,111@5070L319,122@5434L319,133@5799L323,144@6168L323,160@6648L557,155@6496L709:ProgressIndicator.kt#jmzs0o");
        int i4 = i2 & 1;
        if (i4 != 0) {
            i3 = i | 6;
            modifier2 = modifier;
        } else if ((i & 14) == 0) {
            modifier2 = modifier;
            i3 = (composerStartRestartGroup.changed(modifier2) ? 4 : 2) | i;
        } else {
            modifier2 = modifier;
            i3 = i;
        }
        if ((i & 112) == 0) {
            j3 = j;
            i3 |= ((i2 & 2) == 0 && composerStartRestartGroup.changed(j3)) ? 32 : 16;
        } else {
            j3 = j;
        }
        if ((i & 896) == 0) {
            jM1876copywmQWz5c$default = j2;
            i3 |= ((i2 & 4) == 0 && composerStartRestartGroup.changed(jM1876copywmQWz5c$default)) ? 256 : 128;
        } else {
            jM1876copywmQWz5c$default = j2;
        }
        if (((i3 & 731) ^ 146) != 0 || !composerStartRestartGroup.getSkipping()) {
            if ((i & 1) != 0 && !composerStartRestartGroup.getDefaultsInvalid()) {
                composerStartRestartGroup.skipCurrentGroup();
                companion = modifier2;
                jM1242getPrimary0d7_KjU = j3;
            } else {
                composerStartRestartGroup.startDefaults();
                companion = i4 != 0 ? Modifier.INSTANCE : modifier2;
                jM1242getPrimary0d7_KjU = (i2 & 2) != 0 ? MaterialTheme.INSTANCE.getColors(composerStartRestartGroup, 0).m1242getPrimary0d7_KjU() : j3;
                if ((i2 & 4) != 0) {
                    jM1876copywmQWz5c$default = Color.m1876copywmQWz5c$default(jM1242getPrimary0d7_KjU, 0.24f, 0.0f, 0.0f, 0.0f, 14, null);
                }
                composerStartRestartGroup.endDefaults();
            }
            final long j6 = jM1876copywmQWz5c$default;
            InfiniteTransition infiniteTransitionRememberInfiniteTransition = InfiniteTransitionKt.rememberInfiniteTransition(composerStartRestartGroup, 0);
            final State stateAnimateFloat = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition, 0.0f, 1.0f, AnimationSpecKt.infiniteRepeatable$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material.ProgressIndicatorKt$LinearProgressIndicator$firstLineHead$2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                    invoke2(keyframesSpecConfig);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframes) {
                    Intrinsics.checkNotNullParameter(keyframes, "$this$keyframes");
                    keyframes.setDurationMillis(1800);
                    keyframes.with(keyframes.at(Float.valueOf(0.0f), 0), ProgressIndicatorKt.FirstLineHeadEasing);
                    keyframes.at(Float.valueOf(1.0f), 750);
                }
            }), null, 2, null), composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9));
            final State stateAnimateFloat2 = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition, 0.0f, 1.0f, AnimationSpecKt.infiniteRepeatable$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material.ProgressIndicatorKt$LinearProgressIndicator$firstLineTail$2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                    invoke2(keyframesSpecConfig);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframes) {
                    Intrinsics.checkNotNullParameter(keyframes, "$this$keyframes");
                    keyframes.setDurationMillis(1800);
                    keyframes.with(keyframes.at(Float.valueOf(0.0f), 333), ProgressIndicatorKt.FirstLineTailEasing);
                    keyframes.at(Float.valueOf(1.0f), 1183);
                }
            }), null, 2, null), composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9));
            final State stateAnimateFloat3 = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition, 0.0f, 1.0f, AnimationSpecKt.infiniteRepeatable$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material.ProgressIndicatorKt$LinearProgressIndicator$secondLineHead$2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                    invoke2(keyframesSpecConfig);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframes) {
                    Intrinsics.checkNotNullParameter(keyframes, "$this$keyframes");
                    keyframes.setDurationMillis(1800);
                    keyframes.with(keyframes.at(Float.valueOf(0.0f), 1000), ProgressIndicatorKt.SecondLineHeadEasing);
                    keyframes.at(Float.valueOf(1.0f), 1567);
                }
            }), null, 2, null), composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9));
            final State stateAnimateFloat4 = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition, 0.0f, 1.0f, AnimationSpecKt.infiniteRepeatable$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material.ProgressIndicatorKt$LinearProgressIndicator$secondLineTail$2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                    invoke2(keyframesSpecConfig);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframes) {
                    Intrinsics.checkNotNullParameter(keyframes, "$this$keyframes");
                    keyframes.setDurationMillis(1800);
                    keyframes.with(keyframes.at(Float.valueOf(0.0f), 1267), ProgressIndicatorKt.SecondLineTailEasing);
                    keyframes.at(Float.valueOf(1.0f), 1800);
                }
            }), null, 2, null), composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9));
            Modifier modifierFocusable$default = FocusableKt.focusable$default(SizeKt.m735sizeVpY3zN4(ProgressSemanticsKt.progressSemantics(companion), LinearIndicatorWidth, LinearIndicatorHeight), false, null, 3, null);
            Object[] objArr = {Color.m1867boximpl(j6), stateAnimateFloat, stateAnimateFloat2, Color.m1867boximpl(jM1242getPrimary0d7_KjU), stateAnimateFloat3, stateAnimateFloat4};
            composerStartRestartGroup.startReplaceableGroup(-3685570);
            ComposerKt.sourceInformation(composerStartRestartGroup, "C(remember)P(1):Composables.kt#9igjgp");
            int i5 = 0;
            boolean zChanged = false;
            while (i5 < 6) {
                Object obj = objArr[i5];
                i5++;
                zChanged |= composerStartRestartGroup.changed(obj);
            }
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                final long j7 = jM1242getPrimary0d7_KjU;
                objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material.ProgressIndicatorKt$LinearProgressIndicator$3$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope) {
                        invoke2(drawScope);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(DrawScope Canvas) {
                        Intrinsics.checkNotNullParameter(Canvas, "$this$Canvas");
                        float fM1705getHeightimpl = Size.m1705getHeightimpl(Canvas.mo2417getSizeNHjbRc());
                        ProgressIndicatorKt.m1385drawLinearIndicatorBackgroundbw27NRU(Canvas, j6, fM1705getHeightimpl);
                        if (ProgressIndicatorKt.m1365LinearProgressIndicator_RIQooxk$lambda1(stateAnimateFloat) - ProgressIndicatorKt.m1366LinearProgressIndicator_RIQooxk$lambda2(stateAnimateFloat2) > 0.0f) {
                            ProgressIndicatorKt.m1384drawLinearIndicator42QJj7c(Canvas, ProgressIndicatorKt.m1365LinearProgressIndicator_RIQooxk$lambda1(stateAnimateFloat), ProgressIndicatorKt.m1366LinearProgressIndicator_RIQooxk$lambda2(stateAnimateFloat2), j7, fM1705getHeightimpl);
                        }
                        if (ProgressIndicatorKt.m1367LinearProgressIndicator_RIQooxk$lambda3(stateAnimateFloat3) - ProgressIndicatorKt.m1368LinearProgressIndicator_RIQooxk$lambda4(stateAnimateFloat4) > 0.0f) {
                            ProgressIndicatorKt.m1384drawLinearIndicator42QJj7c(Canvas, ProgressIndicatorKt.m1367LinearProgressIndicator_RIQooxk$lambda3(stateAnimateFloat3), ProgressIndicatorKt.m1368LinearProgressIndicator_RIQooxk$lambda4(stateAnimateFloat4), j7, fM1705getHeightimpl);
                        }
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            composerStartRestartGroup.endReplaceableGroup();
            CanvasKt.Canvas(modifierFocusable$default, (Function1) objRememberedValue, composerStartRestartGroup, 0);
            j4 = jM1242getPrimary0d7_KjU;
            j5 = j6;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            companion = modifier2;
            j4 = j3;
            j5 = jM1876copywmQWz5c$default;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ProgressIndicatorKt$LinearProgressIndicator$4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                invoke(composer2, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer2, int i6) {
                ProgressIndicatorKt.m1363LinearProgressIndicatorRIQooxk(companion, j4, j5, composer2, i | 1, i2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: drawLinearIndicator-42QJj7c, reason: not valid java name */
    public static final void m1384drawLinearIndicator42QJj7c(DrawScope drawScope, float f, float f2, long j, float f3) {
        float fM1708getWidthimpl = Size.m1708getWidthimpl(drawScope.mo2417getSizeNHjbRc());
        float fM1705getHeightimpl = Size.m1705getHeightimpl(drawScope.mo2417getSizeNHjbRc()) / 2;
        boolean z = drawScope.getLayoutDirection() == LayoutDirection.Ltr;
        DrawScope.m2404drawLineNGM6Ib0$default(drawScope, j, OffsetKt.Offset((z ? f : 1.0f - f2) * fM1708getWidthimpl, fM1705getHeightimpl), OffsetKt.Offset((z ? f2 : 1.0f - f) * fM1708getWidthimpl, fM1705getHeightimpl), f3, 0, null, 0.0f, null, 0, 496, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: drawLinearIndicatorBackground-bw27NRU, reason: not valid java name */
    public static final void m1385drawLinearIndicatorBackgroundbw27NRU(DrawScope drawScope, long j, float f) {
        m1384drawLinearIndicator42QJj7c(drawScope, 0.0f, 1.0f, j, f);
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:77:? A[RETURN, SYNTHETIC] */
    /* renamed from: CircularProgressIndicator-MBs18nI, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1357CircularProgressIndicatorMBs18nI(final float r22, androidx.compose.ui.Modifier r23, long r24, float r26, androidx.compose.runtime.Composer r27, final int r28, final int r29) {
        /*
            Method dump skipped, instructions count: 316
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.ProgressIndicatorKt.m1357CircularProgressIndicatorMBs18nI(float, androidx.compose.ui.Modifier, long, float, androidx.compose.runtime.Composer, int, int):void");
    }

    /* renamed from: CircularProgressIndicator-aM-cp0Q, reason: not valid java name */
    public static final void m1358CircularProgressIndicatoraMcp0Q(Modifier modifier, long j, float f, Composer composer, final int i, final int i2) {
        Modifier modifier2;
        int i3;
        long j2;
        float f2;
        final Modifier.Companion companion;
        long jM1242getPrimary0d7_KjU;
        final float f3;
        final long j3;
        final float f4;
        Composer composerStartRestartGroup = composer.startRestartGroup(1769711483);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(CircularProgressIndicator)P(1,0:c#ui.graphics.Color,2:c#ui.unit.Dp)261@10395L6,*264@10510L7,268@10616L28,270@10774L278,282@11167L230,293@11515L345,305@11895L354,316@12254L607:ProgressIndicator.kt#jmzs0o");
        int i4 = i2 & 1;
        if (i4 != 0) {
            i3 = i | 6;
            modifier2 = modifier;
        } else if ((i & 14) == 0) {
            modifier2 = modifier;
            i3 = (composerStartRestartGroup.changed(modifier2) ? 4 : 2) | i;
        } else {
            modifier2 = modifier;
            i3 = i;
        }
        if ((i & 112) == 0) {
            if ((i2 & 2) == 0) {
                j2 = j;
                int i5 = composerStartRestartGroup.changed(j2) ? 32 : 16;
                i3 |= i5;
            } else {
                j2 = j;
            }
            i3 |= i5;
        } else {
            j2 = j;
        }
        if ((i & 896) == 0) {
            if ((i2 & 4) == 0) {
                f2 = f;
                int i6 = composerStartRestartGroup.changed(f2) ? 256 : 128;
                i3 |= i6;
            } else {
                f2 = f;
            }
            i3 |= i6;
        } else {
            f2 = f;
        }
        if (((i3 & 731) ^ 146) != 0 || !composerStartRestartGroup.getSkipping()) {
            if ((i & 1) != 0 && !composerStartRestartGroup.getDefaultsInvalid()) {
                composerStartRestartGroup.skipCurrentGroup();
                companion = modifier2;
                jM1242getPrimary0d7_KjU = j2;
                f3 = f2;
            } else {
                composerStartRestartGroup.startDefaults();
                companion = i4 != 0 ? Modifier.INSTANCE : modifier2;
                jM1242getPrimary0d7_KjU = (i2 & 2) != 0 ? MaterialTheme.INSTANCE.getColors(composerStartRestartGroup, 0).m1242getPrimary0d7_KjU() : j2;
                float fM1356getStrokeWidthD9Ej5fM = (i2 & 4) != 0 ? ProgressIndicatorDefaults.INSTANCE.m1356getStrokeWidthD9Ej5fM() : f2;
                composerStartRestartGroup.endDefaults();
                f3 = fM1356getStrokeWidthD9Ej5fM;
            }
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 103361330, "C:CompositionLocal.kt#9igjgp");
            Object objConsume = composerStartRestartGroup.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final Stroke stroke = new Stroke(((Density) objConsume).mo577toPx0680j_4(f3), 0.0f, StrokeCap.INSTANCE.m2222getSquareKaPHkGw(), 0, null, 26, null);
            InfiniteTransition infiniteTransitionRememberInfiniteTransition = InfiniteTransitionKt.rememberInfiniteTransition(composerStartRestartGroup, 0);
            final State stateAnimateValue = InfiniteTransitionKt.animateValue(infiniteTransitionRememberInfiniteTransition, 0, 5, VectorConvertersKt.getVectorConverter(IntCompanionObject.INSTANCE), AnimationSpecKt.infiniteRepeatable$default(AnimationSpecKt.tween$default(6660, 0, EasingKt.getLinearEasing(), 2, null), null, 2, null), composerStartRestartGroup, InfiniteTransition.$stable | 4528 | (InfiniteRepeatableSpec.$stable << 12));
            final State stateAnimateFloat = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition, 0.0f, BaseRotationAngle, AnimationSpecKt.infiniteRepeatable$default(AnimationSpecKt.tween$default(RotationDuration, 0, EasingKt.getLinearEasing(), 2, null), null, 2, null), composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9));
            final State stateAnimateFloat2 = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition, 0.0f, JumpRotationAngle, AnimationSpecKt.infiniteRepeatable$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material.ProgressIndicatorKt$CircularProgressIndicator$endAngle$2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                    invoke2(keyframesSpecConfig);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframes) {
                    Intrinsics.checkNotNullParameter(keyframes, "$this$keyframes");
                    keyframes.setDurationMillis(1332);
                    keyframes.with(keyframes.at(Float.valueOf(0.0f), 0), ProgressIndicatorKt.CircularEasing);
                    keyframes.at(Float.valueOf(290.0f), IptcDirectory.TAG_AUDIO_OUTCUE);
                }
            }), null, 2, null), composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9));
            final State stateAnimateFloat3 = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition, 0.0f, JumpRotationAngle, AnimationSpecKt.infiniteRepeatable$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material.ProgressIndicatorKt$CircularProgressIndicator$startAngle$2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                    invoke2(keyframesSpecConfig);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframes) {
                    Intrinsics.checkNotNullParameter(keyframes, "$this$keyframes");
                    keyframes.setDurationMillis(1332);
                    keyframes.with(keyframes.at(Float.valueOf(0.0f), IptcDirectory.TAG_AUDIO_OUTCUE), ProgressIndicatorKt.CircularEasing);
                    keyframes.at(Float.valueOf(290.0f), keyframes.getDurationMillis());
                }
            }), null, 2, null), composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9));
            final long j4 = jM1242getPrimary0d7_KjU;
            CanvasKt.Canvas(FocusableKt.focusable$default(SizeKt.m733size3ABfNKs(ProgressSemanticsKt.progressSemantics(companion), CircularIndicatorDiameter), false, null, 3, null), new Function1<DrawScope, Unit>() { // from class: androidx.compose.material.ProgressIndicatorKt$CircularProgressIndicator$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope) {
                    invoke2(drawScope);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(DrawScope Canvas) {
                    Intrinsics.checkNotNullParameter(Canvas, "$this$Canvas");
                    ProgressIndicatorKt.m1383drawIndeterminateCircularIndicatorhrjfTZI(Canvas, ProgressIndicatorKt.m1360CircularProgressIndicator_aM_cp0Q$lambda11(stateAnimateFloat3) + (((ProgressIndicatorKt.m1361CircularProgressIndicator_aM_cp0Q$lambda8(stateAnimateValue) * 216.0f) % 360.0f) - 90.0f) + ProgressIndicatorKt.m1362CircularProgressIndicator_aM_cp0Q$lambda9(stateAnimateFloat), f3, Math.abs(ProgressIndicatorKt.m1359CircularProgressIndicator_aM_cp0Q$lambda10(stateAnimateFloat2) - ProgressIndicatorKt.m1360CircularProgressIndicator_aM_cp0Q$lambda11(stateAnimateFloat3)), j4, stroke);
                }
            }, composerStartRestartGroup, 0);
            j3 = jM1242getPrimary0d7_KjU;
            f4 = f3;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            companion = modifier2;
            j3 = j2;
            f4 = f2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ProgressIndicatorKt$CircularProgressIndicator$4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                invoke(composer2, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer2, int i7) {
                ProgressIndicatorKt.m1358CircularProgressIndicatoraMcp0Q(companion, j3, f4, composer2, i | 1, i2);
            }
        });
    }

    /* renamed from: drawCircularIndicator-42QJj7c, reason: not valid java name */
    private static final void m1381drawCircularIndicator42QJj7c(DrawScope drawScope, float f, float f2, long j, Stroke stroke) {
        float f3 = 2;
        float width = stroke.getWidth() / f3;
        float fM1708getWidthimpl = Size.m1708getWidthimpl(drawScope.mo2417getSizeNHjbRc()) - (f3 * width);
        DrawScope.m2397drawArcyD3GUKo$default(drawScope, j, f, f2, false, OffsetKt.Offset(width, width), androidx.compose.ui.geometry.SizeKt.Size(fM1708getWidthimpl, fM1708getWidthimpl), 0.0f, stroke, null, 0, LeicaMakernoteDirectory.TAG_IMAGE_ID_NUMBER, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: drawDeterminateCircularIndicator-42QJj7c, reason: not valid java name */
    public static final void m1382drawDeterminateCircularIndicator42QJj7c(DrawScope drawScope, float f, float f2, long j, Stroke stroke) {
        m1381drawCircularIndicator42QJj7c(drawScope, f, f2, j, stroke);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: drawIndeterminateCircularIndicator-hrjfTZI, reason: not valid java name */
    public static final void m1383drawIndeterminateCircularIndicatorhrjfTZI(DrawScope drawScope, float f, float f2, float f3, long j, Stroke stroke) {
        m1381drawCircularIndicator42QJj7c(drawScope, f + (((f2 / Dp.m4390constructorimpl(CircularIndicatorDiameter / 2)) * 57.29578f) / 2.0f), Math.max(f3, 0.1f), j, stroke);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: LinearProgressIndicator_RIQooxk$lambda-1, reason: not valid java name */
    public static final float m1365LinearProgressIndicator_RIQooxk$lambda1(State<Float> state) {
        return state.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: LinearProgressIndicator_RIQooxk$lambda-2, reason: not valid java name */
    public static final float m1366LinearProgressIndicator_RIQooxk$lambda2(State<Float> state) {
        return state.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: LinearProgressIndicator_RIQooxk$lambda-3, reason: not valid java name */
    public static final float m1367LinearProgressIndicator_RIQooxk$lambda3(State<Float> state) {
        return state.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: LinearProgressIndicator_RIQooxk$lambda-4, reason: not valid java name */
    public static final float m1368LinearProgressIndicator_RIQooxk$lambda4(State<Float> state) {
        return state.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: CircularProgressIndicator_aM_cp0Q$lambda-8, reason: not valid java name */
    public static final int m1361CircularProgressIndicator_aM_cp0Q$lambda8(State<Integer> state) {
        return state.getValue().intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: CircularProgressIndicator_aM_cp0Q$lambda-9, reason: not valid java name */
    public static final float m1362CircularProgressIndicator_aM_cp0Q$lambda9(State<Float> state) {
        return state.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: CircularProgressIndicator_aM_cp0Q$lambda-10, reason: not valid java name */
    public static final float m1359CircularProgressIndicator_aM_cp0Q$lambda10(State<Float> state) {
        return state.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: CircularProgressIndicator_aM_cp0Q$lambda-11, reason: not valid java name */
    public static final float m1360CircularProgressIndicator_aM_cp0Q$lambda11(State<Float> state) {
        return state.getValue().floatValue();
    }
}
