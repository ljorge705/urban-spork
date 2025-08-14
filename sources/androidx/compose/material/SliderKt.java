package androidx.compose.material;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimatableKt;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.IndicationKt;
import androidx.compose.foundation.ProgressSemanticsKt;
import androidx.compose.foundation.gestures.DragScope;
import androidx.compose.foundation.gestures.DraggableKt;
import androidx.compose.foundation.gestures.DraggableState;
import androidx.compose.foundation.gestures.ForEachGestureKt;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.PressGestureScope;
import androidx.compose.foundation.gestures.TapGestureDetectorKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.interaction.PressInteraction;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.BoxWithConstraintsScope;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material.SliderKt;
import androidx.compose.material.ripple.RippleKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.snapshots.SnapshotStateList;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ShadowKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.PointMode;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.util.MathHelpersKt;
import com.BV.LinearGradient.LinearGradientManager;
import com.clevertap.android.sdk.Constants;
import com.drew.metadata.exif.makernotes.LeicaMakernoteDirectory;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.events.TouchesHelper;
import com.onfido.android.sdk.capture.internal.camera.camerax.DefaultFrameSampler;
import io.sentry.protocol.SentryThread;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.ClosedFloatingPointRange;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;

/* compiled from: Slider.kt */
@Metadata(d1 = {"\u0000\u008e\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aE\u0010\u0011\u001a\u00020\u00122\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\b0\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\b0\u00182\u0006\u0010\u0019\u001a\u00020\bH\u0003¢\u0006\u0002\u0010\u001a\u001a\u007f\u0010\u001b\u001a\u00020\u00122\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\b0\u00162\u0018\u0010\u001d\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0016\u0012\u0004\u0012\u00020\u00120\u00142\b\b\u0002\u0010\u001e\u001a\u00020\u00012\b\b\u0002\u0010\u001f\u001a\u00020 2\u000e\b\u0002\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\b0\u00162\b\b\u0002\u0010!\u001a\u00020\"2\u0010\b\u0002\u0010#\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010$2\b\b\u0002\u0010%\u001a\u00020&H\u0007¢\u0006\u0002\u0010'\u001a[\u0010(\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010)\u001a\u00020\b2\u0006\u0010*\u001a\u00020\b2\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\b0,2\u0006\u0010%\u001a\u00020&2\u0006\u0010-\u001a\u00020\b2\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020/2\u0006\u0010\u001e\u001a\u00020\u0001H\u0003¢\u0006\u0002\u00101\u001a}\u00102\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\b2\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00120\u00142\b\b\u0002\u0010\u001e\u001a\u00020\u00012\b\b\u0002\u0010\u001f\u001a\u00020 2\u000e\b\u0002\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\b0\u00162\b\b\u0002\u0010!\u001a\u00020\"2\u0010\b\u0002\u0010#\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010$2\b\b\u0002\u00103\u001a\u00020/2\b\b\u0002\u0010%\u001a\u00020&H\u0007¢\u0006\u0002\u00104\u001aK\u00105\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020 2\u0006\u00106\u001a\u00020\b2\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\b0,2\u0006\u0010%\u001a\u00020&2\u0006\u0010-\u001a\u00020\b2\u0006\u00103\u001a\u00020/2\u0006\u0010\u001e\u001a\u00020\u0001H\u0003¢\u0006\u0002\u00107\u001aE\u00108\u001a\u00020\u00122\u0006\u0010\u001e\u001a\u00020\u00012\u0006\u00109\u001a\u00020\u00032\u0006\u00103\u001a\u00020/2\u0006\u0010%\u001a\u00020&2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010:\u001a\u00020\u0003H\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b;\u0010<\u001aS\u0010=\u001a\u00020\u00122\u0006\u0010\u001e\u001a\u00020\u00012\u0006\u0010%\u001a\u00020&2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010)\u001a\u00020\b2\u0006\u0010*\u001a\u00020\b2\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\b0,2\u0006\u0010>\u001a\u00020\b2\u0006\u0010?\u001a\u00020\bH\u0003¢\u0006\u0002\u0010@\u001a1\u0010A\u001a\u00020\u00122\u0006\u0010B\u001a\u00020C2\u0006\u0010D\u001a\u00020\b2\u0006\u0010E\u001a\u00020\b2\u0006\u0010F\u001a\u00020\bH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010G\u001a \u0010H\u001a\u00020\b2\u0006\u0010I\u001a\u00020\b2\u0006\u0010J\u001a\u00020\b2\u0006\u0010K\u001a\u00020\bH\u0002\u001a0\u0010L\u001a\u00020\b2\u0006\u0010M\u001a\u00020\b2\u0006\u0010N\u001a\u00020\b2\u0006\u0010O\u001a\u00020\b2\u0006\u0010P\u001a\u00020\b2\u0006\u0010Q\u001a\u00020\bH\u0002\u001a<\u0010L\u001a\b\u0012\u0004\u0012\u00020\b0\u00162\u0006\u0010M\u001a\u00020\b2\u0006\u0010N\u001a\u00020\b2\f\u0010R\u001a\b\u0012\u0004\u0012\u00020\b0\u00162\u0006\u0010P\u001a\u00020\b2\u0006\u0010Q\u001a\u00020\bH\u0002\u001a.\u0010S\u001a\u00020\b2\u0006\u0010D\u001a\u00020\b2\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\b0,2\u0006\u0010T\u001a\u00020\b2\u0006\u0010U\u001a\u00020\bH\u0002\u001a\u0016\u0010V\u001a\b\u0012\u0004\u0012\u00020\b0,2\u0006\u0010!\u001a\u00020\"H\u0002\u001a3\u0010W\u001a\u0010\u0012\u0004\u0012\u00020Y\u0012\u0004\u0012\u00020\b\u0018\u00010X*\u00020Z2\u0006\u0010[\u001a\u00020\\H\u0082@ø\u0001\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b]\u0010^\u001a\u0092\u0001\u0010_\u001a\u00020\u0001*\u00020\u00012\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020/2\f\u0010`\u001a\b\u0012\u0004\u0012\u00020\b0a2\f\u0010b\u001a\b\u0012\u0004\u0012\u00020\b0a2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010c\u001a\u00020 2\u0006\u0010U\u001a\u00020\b2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\b0\u00162\u0018\u0010d\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\u00120\u00140a2\u0018\u0010e\u001a\u0014\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00120fH\u0002\u001a\\\u0010g\u001a\u00020\u0001*\u00020\u00012\u0006\u0010B\u001a\u00020C2\u0006\u00103\u001a\u00020/2\u0006\u0010U\u001a\u00020\b2\u0006\u0010c\u001a\u00020 2\f\u0010h\u001a\b\u0012\u0004\u0012\u00020\b0a2\u0018\u0010d\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00120\u00140a2\u0006\u0010\u001f\u001a\u00020 H\u0002\u001aX\u0010i\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0019\u001a\u00020\b2\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\b0,2\u0006\u0010\u001f\u001a\u00020 2\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00120\u00142\u000e\b\u0002\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\b0\u00162\b\b\u0002\u0010!\u001a\u00020\"H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0013\u0010\u0002\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0004\"\u0013\u0010\u0005\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0004\"\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0013\u0010\t\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0004\"\u0013\u0010\n\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0004\"\u0019\u0010\u000b\u001a\u00020\u0003X\u0080\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b\f\u0010\r\"\u0013\u0010\u000e\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0004\"\u0019\u0010\u000f\u001a\u00020\u0003X\u0080\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b\u0010\u0010\r\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006j"}, d2 = {"DefaultSliderConstraints", "Landroidx/compose/ui/Modifier;", "SliderHeight", "Landroidx/compose/ui/unit/Dp;", "F", "SliderMinWidth", "SliderToTickAnimation", "Landroidx/compose/animation/core/TweenSpec;", "", "ThumbDefaultElevation", "ThumbPressedElevation", "ThumbRadius", "getThumbRadius", "()F", "ThumbRippleRadius", "TrackHeight", "getTrackHeight", "CorrectValueSideEffect", "", "scaleToOffset", "Lkotlin/Function1;", "valueRange", "Lkotlin/ranges/ClosedFloatingPointRange;", "valueState", "Landroidx/compose/runtime/MutableState;", "value", "(Lkotlin/jvm/functions/Function1;Lkotlin/ranges/ClosedFloatingPointRange;Landroidx/compose/runtime/MutableState;FLandroidx/compose/runtime/Composer;I)V", "RangeSlider", "values", "onValueChange", "modifier", ViewProps.ENABLED, "", "steps", "", "onValueChangeFinished", "Lkotlin/Function0;", LinearGradientManager.PROP_COLORS, "Landroidx/compose/material/SliderColors;", "(Lkotlin/ranges/ClosedFloatingPointRange;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZLkotlin/ranges/ClosedFloatingPointRange;ILkotlin/jvm/functions/Function0;Landroidx/compose/material/SliderColors;Landroidx/compose/runtime/Composer;II)V", "RangeSliderImpl", "positionFractionStart", "positionFractionEnd", "tickFractions", "", "width", "startInteractionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "endInteractionSource", "(ZFFLjava/util/List;Landroidx/compose/material/SliderColors;FLandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;I)V", "Slider", "interactionSource", "(FLkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZLkotlin/ranges/ClosedFloatingPointRange;ILkotlin/jvm/functions/Function0;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/material/SliderColors;Landroidx/compose/runtime/Composer;II)V", "SliderImpl", "positionFraction", "(ZFLjava/util/List;Landroidx/compose/material/SliderColors;FLandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;I)V", "SliderThumb", "offset", "thumbSize", "SliderThumb-gNmqVrs", "(Landroidx/compose/ui/Modifier;FLandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/material/SliderColors;ZFLandroidx/compose/runtime/Composer;I)V", "Track", "thumbPx", "trackStrokeWidth", "(Landroidx/compose/ui/Modifier;Landroidx/compose/material/SliderColors;ZFFLjava/util/List;FFLandroidx/compose/runtime/Composer;I)V", "animateToTarget", "draggableState", "Landroidx/compose/foundation/gestures/DraggableState;", SentryThread.JsonKeys.CURRENT, TouchesHelper.TARGET_KEY, "velocity", "(Landroidx/compose/foundation/gestures/DraggableState;FFFLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "calcFraction", "a", "b", Constants.INAPP_POSITION, "scale", "a1", "b1", "x1", "a2", "b2", "x", "snapValueToTick", "minPx", "maxPx", "stepsToTickFractions", "awaitSlop", "Lkotlin/Pair;", "Landroidx/compose/ui/input/pointer/PointerInputChange;", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "id", "Landroidx/compose/ui/input/pointer/PointerId;", "awaitSlop-rnUCldI", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "rangeSliderPressDragModifier", "rawOffsetStart", "Landroidx/compose/runtime/State;", "rawOffsetEnd", "isRtl", "gestureEndAction", "onDrag", "Lkotlin/Function2;", "sliderPressModifier", "rawOffset", "sliderSemantics", "material_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class SliderKt {
    private static final Modifier DefaultSliderConstraints;
    private static final float SliderHeight;
    private static final float SliderMinWidth;
    private static final TweenSpec<Float> SliderToTickAnimation;
    private static final float ThumbRadius = Dp.m4390constructorimpl(10);
    private static final float ThumbRippleRadius = Dp.m4390constructorimpl(24);
    private static final float ThumbDefaultElevation = Dp.m4390constructorimpl(1);
    private static final float ThumbPressedElevation = Dp.m4390constructorimpl(6);
    private static final float TrackHeight = Dp.m4390constructorimpl(4);

    public static final float getThumbRadius() {
        return ThumbRadius;
    }

    public static final float getTrackHeight() {
        return TrackHeight;
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x013e  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0173  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0178  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x017d  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0180  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0185  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x018f  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0192  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0195  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0198  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x019a  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x019e  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x01c2  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x01c8  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x01f1  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x01fc  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x0200  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x02ba  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x02ce  */
    /* JADX WARN: Removed duplicated region for block: B:160:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00e4  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x011c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void Slider(final float r39, final kotlin.jvm.functions.Function1<? super java.lang.Float, kotlin.Unit> r40, androidx.compose.ui.Modifier r41, boolean r42, kotlin.ranges.ClosedFloatingPointRange<java.lang.Float> r43, int r44, kotlin.jvm.functions.Function0<kotlin.Unit> r45, androidx.compose.foundation.interaction.MutableInteractionSource r46, androidx.compose.material.SliderColors r47, androidx.compose.runtime.Composer r48, final int r49, final int r50) {
        /*
            Method dump skipped, instructions count: 733
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.SliderKt.Slider(float, kotlin.jvm.functions.Function1, androidx.compose.ui.Modifier, boolean, kotlin.ranges.ClosedFloatingPointRange, int, kotlin.jvm.functions.Function0, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.material.SliderColors, androidx.compose.runtime.Composer, int, int):void");
    }

    /* compiled from: Slider.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    /* renamed from: androidx.compose.material.SliderKt$Slider$3, reason: invalid class name and case insensitive filesystem */
    static final class C03893 extends Lambda implements Function3<BoxWithConstraintsScope, Composer, Integer, Unit> {
        final /* synthetic */ int $$dirty;
        final /* synthetic */ SliderColors $colors;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ MutableInteractionSource $interactionSource;
        final /* synthetic */ Function0<Unit> $onValueChangeFinished;
        final /* synthetic */ State<Function1<Float, Unit>> $onValueChangeState;
        final /* synthetic */ List<Float> $tickFractions;
        final /* synthetic */ float $value;
        final /* synthetic */ ClosedFloatingPointRange<Float> $valueRange;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C03893(ClosedFloatingPointRange<Float> closedFloatingPointRange, int i, float f, MutableInteractionSource mutableInteractionSource, boolean z, List<Float> list, SliderColors sliderColors, State<? extends Function1<? super Float, Unit>> state, Function0<Unit> function0) {
            super(3);
            this.$valueRange = closedFloatingPointRange;
            this.$$dirty = i;
            this.$value = f;
            this.$interactionSource = mutableInteractionSource;
            this.$enabled = z;
            this.$tickFractions = list;
            this.$colors = sliderColors;
            this.$onValueChangeState = state;
            this.$onValueChangeFinished = function0;
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(BoxWithConstraintsScope boxWithConstraintsScope, Composer composer, Integer num) {
            invoke(boxWithConstraintsScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(BoxWithConstraintsScope BoxWithConstraints, Composer composer, int i) {
            int i2;
            Continuation continuation;
            Intrinsics.checkNotNullParameter(BoxWithConstraints, "$this$BoxWithConstraints");
            ComposerKt.sourceInformation(composer, "C160@7743L7,170@8132L24,171@8181L49,172@8260L258,179@8528L69,181@8630L618,204@9646L55,211@9987L201:Slider.kt#jmzs0o");
            if ((i & 14) == 0) {
                i2 = i | (composer.changed(BoxWithConstraints) ? 4 : 2);
            } else {
                i2 = i;
            }
            if (((i2 & 91) ^ 18) != 0 || !composer.getSkipping()) {
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart(composer, 103361330, "C:CompositionLocal.kt#9igjgp");
                Object objConsume = composer.consume(localLayoutDirection);
                ComposerKt.sourceInformationMarkerEnd(composer);
                boolean z = objConsume == LayoutDirection.Rtl;
                final float fM4346getMaxWidthimpl = Constraints.m4346getMaxWidthimpl(BoxWithConstraints.getConstraints());
                final float f = 0.0f;
                composer.startReplaceableGroup(-723524056);
                ComposerKt.sourceInformation(composer, "C(rememberCoroutineScope)475@19849L144:Effects.kt#9igjgp");
                composer.startReplaceableGroup(-3687241);
                ComposerKt.sourceInformation(composer, "C(remember):Composables.kt#9igjgp");
                Object objRememberedValue = composer.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    Object compositionScopedCoroutineScopeCanceller = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composer));
                    composer.updateRememberedValue(compositionScopedCoroutineScopeCanceller);
                    objRememberedValue = compositionScopedCoroutineScopeCanceller;
                }
                composer.endReplaceableGroup();
                final CoroutineScope coroutineScope = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
                composer.endReplaceableGroup();
                float f2 = this.$value;
                ClosedFloatingPointRange<Float> closedFloatingPointRange = this.$valueRange;
                composer.startReplaceableGroup(-3687241);
                ComposerKt.sourceInformation(composer, "C(remember):Composables.kt#9igjgp");
                Object objRememberedValue2 = composer.rememberedValue();
                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Float.valueOf(invoke$scaleToOffset(closedFloatingPointRange, 0.0f, fM4346getMaxWidthimpl, f2)), null, 2, null);
                    composer.updateRememberedValue(objRememberedValue2);
                }
                composer.endReplaceableGroup();
                final MutableState mutableState = (MutableState) objRememberedValue2;
                Object objValueOf = Float.valueOf(0.0f);
                Object objValueOf2 = Float.valueOf(fM4346getMaxWidthimpl);
                final ClosedFloatingPointRange<Float> closedFloatingPointRange2 = this.$valueRange;
                final State<Function1<Float, Unit>> state = this.$onValueChangeState;
                composer.startReplaceableGroup(-3686095);
                ComposerKt.sourceInformation(composer, "C(remember)P(1,2,3):Composables.kt#9igjgp");
                boolean zChanged = composer.changed(objValueOf) | composer.changed(objValueOf2) | composer.changed(closedFloatingPointRange2);
                Object objRememberedValue3 = composer.rememberedValue();
                if (zChanged || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    continuation = null;
                    objRememberedValue3 = new SliderDraggableState(new Function1<Float, Unit>() { // from class: androidx.compose.material.SliderKt$Slider$3$draggableState$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Float f3) {
                            invoke(f3.floatValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(float f3) {
                            MutableState<Float> mutableState2 = mutableState;
                            mutableState2.setValue(Float.valueOf(RangesKt.coerceIn(mutableState2.getValue().floatValue() + f3, f, fM4346getMaxWidthimpl)));
                            state.getValue().invoke(Float.valueOf(SliderKt.C03893.invoke$scaleToUserValue(f, fM4346getMaxWidthimpl, closedFloatingPointRange2, mutableState.getValue().floatValue())));
                        }
                    });
                    composer.updateRememberedValue(objRememberedValue3);
                } else {
                    continuation = null;
                }
                composer.endReplaceableGroup();
                final SliderDraggableState sliderDraggableState = (SliderDraggableState) objRememberedValue3;
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$valueRange, 0.0f, fM4346getMaxWidthimpl);
                ClosedFloatingPointRange<Float> closedFloatingPointRange3 = this.$valueRange;
                float f3 = this.$value;
                int i3 = this.$$dirty;
                Continuation continuation2 = continuation;
                SliderKt.CorrectValueSideEffect(anonymousClass1, closedFloatingPointRange3, mutableState, f3, composer, ((i3 >> 9) & 112) | 384 | ((i3 << 9) & 7168));
                final List<Float> list = this.$tickFractions;
                final Function0<Unit> function0 = this.$onValueChangeFinished;
                State stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(new Function1<Float, Unit>() { // from class: androidx.compose.material.SliderKt$Slider$3$gestureEndAction$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Float f4) {
                        invoke(f4.floatValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(float f4) {
                        Function0<Unit> function02;
                        float fFloatValue = mutableState.getValue().floatValue();
                        float fSnapValueToTick = SliderKt.snapValueToTick(fFloatValue, list, f, fM4346getMaxWidthimpl);
                        if (fFloatValue != fSnapValueToTick) {
                            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass1(sliderDraggableState, fFloatValue, fSnapValueToTick, f4, function0, null), 3, null);
                        } else {
                            if (sliderDraggableState.isDragging() || (function02 = function0) == null) {
                                return;
                            }
                            function02.invoke();
                        }
                    }

                    /* compiled from: Slider.kt */
                    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
                    @DebugMetadata(c = "androidx.compose.material.SliderKt$Slider$3$gestureEndAction$1$1", f = "Slider.kt", i = {}, l = {187}, m = "invokeSuspend", n = {}, s = {})
                    /* renamed from: androidx.compose.material.SliderKt$Slider$3$gestureEndAction$1$1, reason: invalid class name */
                    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        final /* synthetic */ float $current;
                        final /* synthetic */ SliderDraggableState $draggableState;
                        final /* synthetic */ Function0<Unit> $onValueChangeFinished;
                        final /* synthetic */ float $target;
                        final /* synthetic */ float $velocity;
                        int label;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        AnonymousClass1(SliderDraggableState sliderDraggableState, float f, float f2, float f3, Function0<Unit> function0, Continuation<? super AnonymousClass1> continuation) {
                            super(2, continuation);
                            this.$draggableState = sliderDraggableState;
                            this.$current = f;
                            this.$target = f2;
                            this.$velocity = f3;
                            this.$onValueChangeFinished = function0;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            return new AnonymousClass1(this.$draggableState, this.$current, this.$target, this.$velocity, this.$onValueChangeFinished, continuation);
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
                                if (SliderKt.animateToTarget(this.$draggableState, this.$current, this.$target, this.$velocity, this) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                            } else {
                                if (i != 1) {
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                }
                                ResultKt.throwOnFailure(obj);
                            }
                            Function0<Unit> function0 = this.$onValueChangeFinished;
                            if (function0 != null) {
                                function0.invoke();
                            }
                            return Unit.INSTANCE;
                        }
                    }
                }, composer, 0);
                SliderDraggableState sliderDraggableState2 = sliderDraggableState;
                Modifier modifierSliderPressModifier = SliderKt.sliderPressModifier(Modifier.INSTANCE, sliderDraggableState2, this.$interactionSource, fM4346getMaxWidthimpl, z, mutableState, stateRememberUpdatedState, this.$enabled);
                Orientation orientation = Orientation.Horizontal;
                boolean zIsDragging = sliderDraggableState.isDragging();
                Modifier.Companion companion = Modifier.INSTANCE;
                boolean z2 = this.$enabled;
                MutableInteractionSource mutableInteractionSource = this.$interactionSource;
                composer.startReplaceableGroup(-3686930);
                ComposerKt.sourceInformation(composer, "C(remember)P(1):Composables.kt#9igjgp");
                boolean zChanged2 = composer.changed(stateRememberUpdatedState);
                Object objRememberedValue4 = composer.rememberedValue();
                if (zChanged2 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue4 = (Function3) new SliderKt$Slider$3$drag$1$1(stateRememberUpdatedState, continuation2);
                    composer.updateRememberedValue(objRememberedValue4);
                }
                composer.endReplaceableGroup();
                Modifier modifierDraggable = DraggableKt.draggable(companion, sliderDraggableState2, orientation, (32 & 4) != 0 ? true : z2, (32 & 8) != 0 ? null : mutableInteractionSource, (32 & 16) != 0 ? false : zIsDragging, (32 & 32) != 0 ? new DraggableKt.C02781(null) : null, (32 & 64) != 0 ? new DraggableKt.AnonymousClass2(null) : (Function3) objRememberedValue4, (32 & 128) != 0 ? false : z);
                float fCalcFraction = SliderKt.calcFraction(this.$valueRange.getStart().floatValue(), this.$valueRange.getEndInclusive().floatValue(), RangesKt.coerceIn(this.$value, this.$valueRange.getStart().floatValue(), this.$valueRange.getEndInclusive().floatValue()));
                boolean z3 = this.$enabled;
                List<Float> list2 = this.$tickFractions;
                SliderColors sliderColors = this.$colors;
                MutableInteractionSource mutableInteractionSource2 = this.$interactionSource;
                Modifier modifierThen = modifierSliderPressModifier.then(modifierDraggable);
                int i4 = this.$$dirty;
                SliderKt.SliderImpl(z3, fCalcFraction, list2, sliderColors, fM4346getMaxWidthimpl, mutableInteractionSource2, modifierThen, composer, ((i4 >> 9) & 14) | 512 | ((i4 >> 15) & 7168) | ((i4 >> 6) & 458752));
                return;
            }
            composer.skipToGroupEnd();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final float invoke$scaleToUserValue(float f, float f2, ClosedFloatingPointRange<Float> closedFloatingPointRange, float f3) {
            return SliderKt.scale(f, f2, f3, closedFloatingPointRange.getStart().floatValue(), closedFloatingPointRange.getEndInclusive().floatValue());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final float invoke$scaleToOffset(ClosedFloatingPointRange<Float> closedFloatingPointRange, float f, float f2, float f3) {
            return SliderKt.scale(closedFloatingPointRange.getStart().floatValue(), closedFloatingPointRange.getEndInclusive().floatValue(), f3, f, f2);
        }

        /* compiled from: Slider.kt */
        @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
        /* renamed from: androidx.compose.material.SliderKt$Slider$3$1, reason: invalid class name */
        /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Float, Float> {
            final /* synthetic */ float $maxPx;
            final /* synthetic */ float $minPx;
            final /* synthetic */ ClosedFloatingPointRange<Float> $valueRange;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(ClosedFloatingPointRange<Float> closedFloatingPointRange, float f, float f2) {
                super(1, Intrinsics.Kotlin.class, "scaleToOffset", "invoke$scaleToOffset(Lkotlin/ranges/ClosedFloatingPointRange;FFF)F", 0);
                this.$valueRange = closedFloatingPointRange;
                this.$minPx = f;
                this.$maxPx = f2;
            }

            public final float invoke(float f) {
                return C03893.invoke$scaleToOffset(this.$valueRange, this.$minPx, this.$maxPx, f);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Float invoke(Float f) {
                return Float.valueOf(invoke(f.floatValue()));
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:108:0x014b  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0150  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0155  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0158  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x015d  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0169  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x016d  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0173  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x01b6  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x01d5  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x01e4  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x01e8  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x0268  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x027c  */
    /* JADX WARN: Removed duplicated region for block: B:148:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x011a  */
    @androidx.compose.material.ExperimentalMaterialApi
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void RangeSlider(final kotlin.ranges.ClosedFloatingPointRange<java.lang.Float> r43, final kotlin.jvm.functions.Function1<? super kotlin.ranges.ClosedFloatingPointRange<java.lang.Float>, kotlin.Unit> r44, androidx.compose.ui.Modifier r45, boolean r46, kotlin.ranges.ClosedFloatingPointRange<java.lang.Float> r47, int r48, kotlin.jvm.functions.Function0<kotlin.Unit> r49, androidx.compose.material.SliderColors r50, androidx.compose.runtime.Composer r51, final int r52, final int r53) {
        /*
            Method dump skipped, instructions count: 651
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.SliderKt.RangeSlider(kotlin.ranges.ClosedFloatingPointRange, kotlin.jvm.functions.Function1, androidx.compose.ui.Modifier, boolean, kotlin.ranges.ClosedFloatingPointRange, int, kotlin.jvm.functions.Function0, androidx.compose.material.SliderColors, androidx.compose.runtime.Composer, int, int):void");
    }

    /* compiled from: Slider.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    /* renamed from: androidx.compose.material.SliderKt$RangeSlider$2, reason: invalid class name and case insensitive filesystem */
    static final class C03872 extends Lambda implements Function3<BoxWithConstraintsScope, Composer, Integer, Unit> {
        final /* synthetic */ int $$dirty;
        final /* synthetic */ SliderColors $colors;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ MutableInteractionSource $endInteractionSource;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ Function0<Unit> $onValueChangeFinished;
        final /* synthetic */ State<Function1<ClosedFloatingPointRange<Float>, Unit>> $onValueChangeState;
        final /* synthetic */ MutableInteractionSource $startInteractionSource;
        final /* synthetic */ List<Float> $tickFractions;
        final /* synthetic */ ClosedFloatingPointRange<Float> $valueRange;
        final /* synthetic */ ClosedFloatingPointRange<Float> $values;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C03872(ClosedFloatingPointRange<Float> closedFloatingPointRange, ClosedFloatingPointRange<Float> closedFloatingPointRange2, int i, Modifier modifier, MutableInteractionSource mutableInteractionSource, MutableInteractionSource mutableInteractionSource2, boolean z, State<? extends Function1<? super ClosedFloatingPointRange<Float>, Unit>> state, List<Float> list, SliderColors sliderColors, Function0<Unit> function0) {
            super(3);
            this.$valueRange = closedFloatingPointRange;
            this.$values = closedFloatingPointRange2;
            this.$$dirty = i;
            this.$modifier = modifier;
            this.$startInteractionSource = mutableInteractionSource;
            this.$endInteractionSource = mutableInteractionSource2;
            this.$enabled = z;
            this.$onValueChangeState = state;
            this.$tickFractions = list;
            this.$colors = sliderColors;
            this.$onValueChangeFinished = function0;
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(BoxWithConstraintsScope boxWithConstraintsScope, Composer composer, Integer num) {
            invoke(boxWithConstraintsScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(BoxWithConstraintsScope BoxWithConstraints, Composer composer, int i) {
            int i2;
            Intrinsics.checkNotNullParameter(BoxWithConstraints, "$this$BoxWithConstraints");
            ComposerKt.sourceInformation(composer, "C279@13015L7,289@13439L56,290@13523L63,292@13596L81,293@13686L86,295@13794L24,296@13850L944,330@15106L481,350@16039L284:Slider.kt#jmzs0o");
            if ((i & 14) == 0) {
                i2 = i | (composer.changed(BoxWithConstraints) ? 4 : 2);
            } else {
                i2 = i;
            }
            if (((i2 & 91) ^ 18) != 0 || !composer.getSkipping()) {
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart(composer, 103361330, "C:CompositionLocal.kt#9igjgp");
                Object objConsume = composer.consume(localLayoutDirection);
                ComposerKt.sourceInformationMarkerEnd(composer);
                boolean z = objConsume == LayoutDirection.Rtl;
                final float fM4346getMaxWidthimpl = Constraints.m4346getMaxWidthimpl(BoxWithConstraints.getConstraints());
                final float f = 0.0f;
                ClosedFloatingPointRange<Float> closedFloatingPointRange = this.$values;
                ClosedFloatingPointRange<Float> closedFloatingPointRange2 = this.$valueRange;
                composer.startReplaceableGroup(-3687241);
                ComposerKt.sourceInformation(composer, "C(remember):Composables.kt#9igjgp");
                Object objRememberedValue = composer.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Float.valueOf(invoke$scaleToOffset(closedFloatingPointRange2, 0.0f, fM4346getMaxWidthimpl, closedFloatingPointRange.getStart().floatValue())), null, 2, null);
                    composer.updateRememberedValue(objRememberedValue);
                }
                composer.endReplaceableGroup();
                final MutableState mutableState = (MutableState) objRememberedValue;
                ClosedFloatingPointRange<Float> closedFloatingPointRange3 = this.$values;
                ClosedFloatingPointRange<Float> closedFloatingPointRange4 = this.$valueRange;
                composer.startReplaceableGroup(-3687241);
                ComposerKt.sourceInformation(composer, "C(remember):Composables.kt#9igjgp");
                Object objRememberedValue2 = composer.rememberedValue();
                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Float.valueOf(invoke$scaleToOffset(closedFloatingPointRange4, 0.0f, fM4346getMaxWidthimpl, closedFloatingPointRange3.getEndInclusive().floatValue())), null, 2, null);
                    composer.updateRememberedValue(objRememberedValue2);
                }
                composer.endReplaceableGroup();
                final MutableState mutableState2 = (MutableState) objRememberedValue2;
                SliderKt.CorrectValueSideEffect(new AnonymousClass1(this.$valueRange, 0.0f, fM4346getMaxWidthimpl), this.$valueRange, mutableState, this.$values.getStart().floatValue(), composer, ((this.$$dirty >> 9) & 112) | 384);
                SliderKt.CorrectValueSideEffect(new C00672(this.$valueRange, 0.0f, fM4346getMaxWidthimpl), this.$valueRange, mutableState2, this.$values.getEndInclusive().floatValue(), composer, ((this.$$dirty >> 9) & 112) | 384);
                composer.startReplaceableGroup(-723524056);
                ComposerKt.sourceInformation(composer, "C(rememberCoroutineScope)475@19849L144:Effects.kt#9igjgp");
                composer.startReplaceableGroup(-3687241);
                ComposerKt.sourceInformation(composer, "C(remember):Composables.kt#9igjgp");
                Object objRememberedValue3 = composer.rememberedValue();
                if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composer));
                    composer.updateRememberedValue(compositionScopedCoroutineScopeCanceller);
                    objRememberedValue3 = compositionScopedCoroutineScopeCanceller;
                }
                composer.endReplaceableGroup();
                final CoroutineScope coroutineScope = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue3).getCoroutineScope();
                composer.endReplaceableGroup();
                final List<Float> list = this.$tickFractions;
                final Function0<Unit> function0 = this.$onValueChangeFinished;
                final State<Function1<ClosedFloatingPointRange<Float>, Unit>> state = this.$onValueChangeState;
                final ClosedFloatingPointRange<Float> closedFloatingPointRange5 = this.$valueRange;
                State stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(new Function1<Boolean, Unit>() { // from class: androidx.compose.material.SliderKt$RangeSlider$2$gestureEndAction$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                        invoke(bool.booleanValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(boolean z2) {
                        float fFloatValue = (z2 ? mutableState : mutableState2).getValue().floatValue();
                        float fSnapValueToTick = SliderKt.snapValueToTick(fFloatValue, list, f, fM4346getMaxWidthimpl);
                        if (fFloatValue != fSnapValueToTick) {
                            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass1(fFloatValue, fSnapValueToTick, function0, z2, mutableState, mutableState2, state, f, fM4346getMaxWidthimpl, closedFloatingPointRange5, null), 3, null);
                            return;
                        }
                        Function0<Unit> function02 = function0;
                        if (function02 == null) {
                            return;
                        }
                        function02.invoke();
                    }

                    /* compiled from: Slider.kt */
                    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
                    @DebugMetadata(c = "androidx.compose.material.SliderKt$RangeSlider$2$gestureEndAction$1$1", f = "Slider.kt", i = {}, l = {307}, m = "invokeSuspend", n = {}, s = {})
                    /* renamed from: androidx.compose.material.SliderKt$RangeSlider$2$gestureEndAction$1$1, reason: invalid class name */
                    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        final /* synthetic */ float $current;
                        final /* synthetic */ boolean $isStart;
                        final /* synthetic */ float $maxPx;
                        final /* synthetic */ float $minPx;
                        final /* synthetic */ Function0<Unit> $onValueChangeFinished;
                        final /* synthetic */ State<Function1<ClosedFloatingPointRange<Float>, Unit>> $onValueChangeState;
                        final /* synthetic */ MutableState<Float> $rawOffsetEnd;
                        final /* synthetic */ MutableState<Float> $rawOffsetStart;
                        final /* synthetic */ float $target;
                        final /* synthetic */ ClosedFloatingPointRange<Float> $valueRange;
                        int label;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        AnonymousClass1(float f, float f2, Function0<Unit> function0, boolean z, MutableState<Float> mutableState, MutableState<Float> mutableState2, State<? extends Function1<? super ClosedFloatingPointRange<Float>, Unit>> state, float f3, float f4, ClosedFloatingPointRange<Float> closedFloatingPointRange, Continuation<? super AnonymousClass1> continuation) {
                            super(2, continuation);
                            this.$current = f;
                            this.$target = f2;
                            this.$onValueChangeFinished = function0;
                            this.$isStart = z;
                            this.$rawOffsetStart = mutableState;
                            this.$rawOffsetEnd = mutableState2;
                            this.$onValueChangeState = state;
                            this.$minPx = f3;
                            this.$maxPx = f4;
                            this.$valueRange = closedFloatingPointRange;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            return new AnonymousClass1(this.$current, this.$target, this.$onValueChangeFinished, this.$isStart, this.$rawOffsetStart, this.$rawOffsetEnd, this.$onValueChangeState, this.$minPx, this.$maxPx, this.$valueRange, continuation);
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
                                Animatable animatableAnimatable$default = AnimatableKt.Animatable$default(this.$current, 0.0f, 2, null);
                                Float fBoxFloat = Boxing.boxFloat(this.$target);
                                TweenSpec tweenSpec = SliderKt.SliderToTickAnimation;
                                Float fBoxFloat2 = Boxing.boxFloat(0.0f);
                                final boolean z = this.$isStart;
                                final MutableState<Float> mutableState = this.$rawOffsetStart;
                                final MutableState<Float> mutableState2 = this.$rawOffsetEnd;
                                final State<Function1<ClosedFloatingPointRange<Float>, Unit>> state = this.$onValueChangeState;
                                final float f = this.$minPx;
                                final float f2 = this.$maxPx;
                                final ClosedFloatingPointRange<Float> closedFloatingPointRange = this.$valueRange;
                                this.label = 1;
                                if (animatableAnimatable$default.animateTo(fBoxFloat, tweenSpec, fBoxFloat2, new Function1<Animatable<Float, AnimationVector1D>, Unit>() { // from class: androidx.compose.material.SliderKt.RangeSlider.2.gestureEndAction.1.1.1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    /* JADX WARN: Multi-variable type inference failed */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(Animatable<Float, AnimationVector1D> animatable) {
                                        invoke2(animatable);
                                        return Unit.INSTANCE;
                                    }

                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(Animatable<Float, AnimationVector1D> animateTo) {
                                        Intrinsics.checkNotNullParameter(animateTo, "$this$animateTo");
                                        (z ? mutableState : mutableState2).setValue(animateTo.getValue());
                                        state.getValue().invoke(SliderKt.C03872.invoke$scaleToUserValue(f, f2, closedFloatingPointRange, RangesKt.rangeTo(mutableState.getValue().floatValue(), mutableState2.getValue().floatValue())));
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
                            Function0<Unit> function0 = this.$onValueChangeFinished;
                            if (function0 != null) {
                                function0.invoke();
                            }
                            return Unit.INSTANCE;
                        }
                    }
                }, composer, 0);
                Modifier modifier = this.$modifier;
                MutableInteractionSource mutableInteractionSource = this.$startInteractionSource;
                MutableInteractionSource mutableInteractionSource2 = this.$endInteractionSource;
                MutableState mutableState3 = mutableState;
                MutableState mutableState4 = mutableState2;
                boolean z2 = this.$enabled;
                ClosedFloatingPointRange<Float> closedFloatingPointRange6 = this.$valueRange;
                Float fValueOf = Float.valueOf(0.0f);
                Float fValueOf2 = Float.valueOf(fM4346getMaxWidthimpl);
                final State<Function1<ClosedFloatingPointRange<Float>, Unit>> state2 = this.$onValueChangeState;
                final ClosedFloatingPointRange<Float> closedFloatingPointRange7 = this.$valueRange;
                Object[] objArr = {mutableState, fValueOf, mutableState2, fValueOf2, state2, closedFloatingPointRange7};
                composer.startReplaceableGroup(-3685570);
                ComposerKt.sourceInformation(composer, "C(remember)P(1):Composables.kt#9igjgp");
                int i3 = 0;
                boolean zChanged = false;
                while (i3 < 6) {
                    Object obj = objArr[i3];
                    i3++;
                    zChanged |= composer.changed(obj);
                }
                Object objRememberedValue4 = composer.rememberedValue();
                if (zChanged || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                    final float f2 = 0.0f;
                    objRememberedValue4 = (Function2) new Function2<Boolean, Float, Unit>() { // from class: androidx.compose.material.SliderKt$RangeSlider$2$pressDrag$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, Float f3) {
                            invoke(bool.booleanValue(), f3.floatValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(boolean z3, float f3) {
                            if (z3) {
                                MutableState<Float> mutableState5 = mutableState;
                                mutableState5.setValue(Float.valueOf(RangesKt.coerceIn(mutableState5.getValue().floatValue() + f3, f2, mutableState2.getValue().floatValue())));
                            } else {
                                MutableState<Float> mutableState6 = mutableState2;
                                mutableState6.setValue(Float.valueOf(RangesKt.coerceIn(mutableState6.getValue().floatValue() + f3, mutableState.getValue().floatValue(), fM4346getMaxWidthimpl)));
                            }
                            state2.getValue().invoke(SliderKt.C03872.invoke$scaleToUserValue(f2, fM4346getMaxWidthimpl, closedFloatingPointRange7, RangesKt.rangeTo(mutableState.getValue().floatValue(), mutableState2.getValue().floatValue())));
                        }
                    };
                    composer.updateRememberedValue(objRememberedValue4);
                }
                composer.endReplaceableGroup();
                Modifier modifierRangeSliderPressDragModifier = SliderKt.rangeSliderPressDragModifier(modifier, mutableInteractionSource, mutableInteractionSource2, mutableState3, mutableState4, z2, z, fM4346getMaxWidthimpl, closedFloatingPointRange6, stateRememberUpdatedState, (Function2) objRememberedValue4);
                float fCoerceIn = RangesKt.coerceIn(this.$values.getStart().floatValue(), this.$valueRange.getStart().floatValue(), this.$values.getEndInclusive().floatValue());
                float fCoerceIn2 = RangesKt.coerceIn(this.$values.getEndInclusive().floatValue(), this.$values.getStart().floatValue(), this.$valueRange.getEndInclusive().floatValue());
                float fCalcFraction = SliderKt.calcFraction(this.$valueRange.getStart().floatValue(), this.$valueRange.getEndInclusive().floatValue(), fCoerceIn);
                float fCalcFraction2 = SliderKt.calcFraction(this.$valueRange.getStart().floatValue(), this.$valueRange.getEndInclusive().floatValue(), fCoerceIn2);
                boolean z3 = this.$enabled;
                List<Float> list2 = this.$tickFractions;
                SliderColors sliderColors = this.$colors;
                MutableInteractionSource mutableInteractionSource3 = this.$startInteractionSource;
                MutableInteractionSource mutableInteractionSource4 = this.$endInteractionSource;
                Modifier modifierThen = modifierRangeSliderPressDragModifier.then(this.$modifier);
                int i4 = this.$$dirty;
                SliderKt.RangeSliderImpl(z3, fCalcFraction, fCalcFraction2, list2, sliderColors, fM4346getMaxWidthimpl, mutableInteractionSource3, mutableInteractionSource4, modifierThen, composer, ((i4 >> 9) & 14) | 14159872 | ((i4 >> 9) & 57344));
                return;
            }
            composer.skipToGroupEnd();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final ClosedFloatingPointRange<Float> invoke$scaleToUserValue(float f, float f2, ClosedFloatingPointRange<Float> closedFloatingPointRange, ClosedFloatingPointRange<Float> closedFloatingPointRange2) {
            return SliderKt.scale(f, f2, closedFloatingPointRange2, closedFloatingPointRange.getStart().floatValue(), closedFloatingPointRange.getEndInclusive().floatValue());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final float invoke$scaleToOffset(ClosedFloatingPointRange<Float> closedFloatingPointRange, float f, float f2, float f3) {
            return SliderKt.scale(closedFloatingPointRange.getStart().floatValue(), closedFloatingPointRange.getEndInclusive().floatValue(), f3, f, f2);
        }

        /* compiled from: Slider.kt */
        @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
        /* renamed from: androidx.compose.material.SliderKt$RangeSlider$2$1, reason: invalid class name */
        /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Float, Float> {
            final /* synthetic */ float $maxPx;
            final /* synthetic */ float $minPx;
            final /* synthetic */ ClosedFloatingPointRange<Float> $valueRange;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(ClosedFloatingPointRange<Float> closedFloatingPointRange, float f, float f2) {
                super(1, Intrinsics.Kotlin.class, "scaleToOffset", "invoke$scaleToOffset(Lkotlin/ranges/ClosedFloatingPointRange;FFF)F", 0);
                this.$valueRange = closedFloatingPointRange;
                this.$minPx = f;
                this.$maxPx = f2;
            }

            public final float invoke(float f) {
                return C03872.invoke$scaleToOffset(this.$valueRange, this.$minPx, this.$maxPx, f);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Float invoke(Float f) {
                return Float.valueOf(invoke(f.floatValue()));
            }
        }

        /* compiled from: Slider.kt */
        @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
        /* renamed from: androidx.compose.material.SliderKt$RangeSlider$2$2, reason: invalid class name and collision with other inner class name */
        /* synthetic */ class C00672 extends FunctionReferenceImpl implements Function1<Float, Float> {
            final /* synthetic */ float $maxPx;
            final /* synthetic */ float $minPx;
            final /* synthetic */ ClosedFloatingPointRange<Float> $valueRange;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00672(ClosedFloatingPointRange<Float> closedFloatingPointRange, float f, float f2) {
                super(1, Intrinsics.Kotlin.class, "scaleToOffset", "invoke$scaleToOffset(Lkotlin/ranges/ClosedFloatingPointRange;FFF)F", 0);
                this.$valueRange = closedFloatingPointRange;
                this.$minPx = f;
                this.$maxPx = f2;
            }

            public final float invoke(float f) {
                return C03872.invoke$scaleToOffset(this.$valueRange, this.$minPx, this.$maxPx, f);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Float invoke(Float f) {
                return Float.valueOf(invoke(f.floatValue()));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void SliderImpl(final boolean z, final float f, final List<Float> list, final SliderColors sliderColors, final float f2, final MutableInteractionSource mutableInteractionSource, final Modifier modifier, Composer composer, final int i) {
        Composer composerStartRestartGroup = composer.startRestartGroup(1568553854);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(SliderImpl)P(1,4,5!1,6)506@22148L781:Slider.kt#jmzs0o");
        Modifier modifierThen = modifier.then(DefaultSliderConstraints);
        composerStartRestartGroup.startReplaceableGroup(-1990474327);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
        MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false, composerStartRestartGroup, 0);
        composerStartRestartGroup.startReplaceableGroup(1376089335);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(Layout)P(!1,2)71@2788L7,72@2843L7,73@2855L389:Layout.kt#80mrfh");
        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 103361330, "C:CompositionLocal.kt#9igjgp");
        Object objConsume = composerStartRestartGroup.consume(localDensity);
        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
        Density density = (Density) objConsume;
        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 103361330, "C:CompositionLocal.kt#9igjgp");
        Object objConsume2 = composerStartRestartGroup.consume(localLayoutDirection);
        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
        LayoutDirection layoutDirection = (LayoutDirection) objConsume2;
        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
        Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf = LayoutKt.materializerOf(modifierThen);
        if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
            ComposablesKt.invalidApplier();
        }
        composerStartRestartGroup.startReusableNode();
        if (composerStartRestartGroup.getInserting()) {
            composerStartRestartGroup.createNode(constructor);
        } else {
            composerStartRestartGroup.useNode();
        }
        composerStartRestartGroup.disableReusing();
        Composer composerM1518constructorimpl = Updater.m1518constructorimpl(composerStartRestartGroup);
        Updater.m1525setimpl(composerM1518constructorimpl, measurePolicyRememberBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
        Updater.m1525setimpl(composerM1518constructorimpl, density, ComposeUiNode.INSTANCE.getSetDensity());
        Updater.m1525setimpl(composerM1518constructorimpl, layoutDirection, ComposeUiNode.INSTANCE.getSetLayoutDirection());
        composerStartRestartGroup.enableReusing();
        function3MaterializerOf.invoke(SkippableUpdater.m1509boximpl(SkippableUpdater.m1510constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 0);
        composerStartRestartGroup.startReplaceableGroup(2058660585);
        composerStartRestartGroup.startReplaceableGroup(-1253629305);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C72@3384L9:Box.kt#2w3rfo");
        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
        composerStartRestartGroup.startReplaceableGroup(618021173);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C*510@22308L7,520@22626L214,530@22849L74:Slider.kt#jmzs0o");
        ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 103361330, "C:CompositionLocal.kt#9igjgp");
        Object objConsume3 = composerStartRestartGroup.consume(localDensity2);
        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
        Density density2 = (Density) objConsume3;
        float fMo577toPx0680j_4 = density2.mo577toPx0680j_4(getTrackHeight());
        float fMo577toPx0680j_42 = density2.mo577toPx0680j_4(getThumbRadius());
        float fMo573toDpu2uoSUM = density2.mo573toDpu2uoSUM(f2);
        float fM4390constructorimpl = Dp.m4390constructorimpl(getThumbRadius() * 2);
        float fM4390constructorimpl2 = Dp.m4390constructorimpl(Dp.m4390constructorimpl(fMo573toDpu2uoSUM - fM4390constructorimpl) * f);
        Modifier modifierAlign = boxScopeInstance.align(Modifier.INSTANCE, Alignment.INSTANCE.getCenterStart());
        Track(SizeKt.fillMaxSize$default(modifierAlign, 0.0f, 1, null), sliderColors, z, 0.0f, f, list, fMo577toPx0680j_42, fMo577toPx0680j_4, composerStartRestartGroup, ((i >> 6) & 112) | 265216 | ((i << 6) & 896) | ((i << 9) & 57344));
        m1398SliderThumbgNmqVrs(modifierAlign, fM4390constructorimpl2, mutableInteractionSource, sliderColors, z, fM4390constructorimpl, composerStartRestartGroup, ((i >> 9) & 896) | 196608 | (i & 7168) | ((i << 12) & 57344));
        composerStartRestartGroup.endReplaceableGroup();
        composerStartRestartGroup.endReplaceableGroup();
        composerStartRestartGroup.endReplaceableGroup();
        composerStartRestartGroup.endNode();
        composerStartRestartGroup.endReplaceableGroup();
        composerStartRestartGroup.endReplaceableGroup();
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.SliderKt.SliderImpl.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                invoke(composer2, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer2, int i2) {
                SliderKt.SliderImpl(z, f, list, sliderColors, f2, mutableInteractionSource, modifier, composer2, i | 1);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void RangeSliderImpl(final boolean z, final float f, final float f2, final List<Float> list, final SliderColors sliderColors, final float f3, final MutableInteractionSource mutableInteractionSource, final MutableInteractionSource mutableInteractionSource2, final Modifier modifier, Composer composer, final int i) {
        Composer composerStartRestartGroup = composer.startRestartGroup(-1161720431);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(RangeSliderImpl)P(1,5,4,7!1,8,6)547@23276L1178:Slider.kt#jmzs0o");
        Modifier modifierThen = modifier.then(DefaultSliderConstraints);
        composerStartRestartGroup.startReplaceableGroup(-1990474327);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
        MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false, composerStartRestartGroup, 0);
        composerStartRestartGroup.startReplaceableGroup(1376089335);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(Layout)P(!1,2)71@2788L7,72@2843L7,73@2855L389:Layout.kt#80mrfh");
        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 103361330, "C:CompositionLocal.kt#9igjgp");
        Object objConsume = composerStartRestartGroup.consume(localDensity);
        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
        Density density = (Density) objConsume;
        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 103361330, "C:CompositionLocal.kt#9igjgp");
        Object objConsume2 = composerStartRestartGroup.consume(localLayoutDirection);
        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
        LayoutDirection layoutDirection = (LayoutDirection) objConsume2;
        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
        Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf = LayoutKt.materializerOf(modifierThen);
        if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
            ComposablesKt.invalidApplier();
        }
        composerStartRestartGroup.startReusableNode();
        if (composerStartRestartGroup.getInserting()) {
            composerStartRestartGroup.createNode(constructor);
        } else {
            composerStartRestartGroup.useNode();
        }
        composerStartRestartGroup.disableReusing();
        Composer composerM1518constructorimpl = Updater.m1518constructorimpl(composerStartRestartGroup);
        Updater.m1525setimpl(composerM1518constructorimpl, measurePolicyRememberBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
        Updater.m1525setimpl(composerM1518constructorimpl, density, ComposeUiNode.INSTANCE.getSetDensity());
        Updater.m1525setimpl(composerM1518constructorimpl, layoutDirection, ComposeUiNode.INSTANCE.getSetLayoutDirection());
        composerStartRestartGroup.enableReusing();
        function3MaterializerOf.invoke(SkippableUpdater.m1509boximpl(SkippableUpdater.m1510constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 0);
        composerStartRestartGroup.startReplaceableGroup(2058660585);
        composerStartRestartGroup.startReplaceableGroup(-1253629305);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C72@3384L9:Box.kt#2w3rfo");
        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
        composerStartRestartGroup.startReplaceableGroup(-1690176212);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C*551@23436L7,560@23772L267,571@24049L197,579@24255L193:Slider.kt#jmzs0o");
        ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 103361330, "C:CompositionLocal.kt#9igjgp");
        Object objConsume3 = composerStartRestartGroup.consume(localDensity2);
        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
        Density density2 = (Density) objConsume3;
        float fMo577toPx0680j_4 = density2.mo577toPx0680j_4(getTrackHeight());
        float fMo577toPx0680j_42 = density2.mo577toPx0680j_4(getThumbRadius());
        float fMo573toDpu2uoSUM = density2.mo573toDpu2uoSUM(f3);
        float fM4390constructorimpl = Dp.m4390constructorimpl(getThumbRadius() * 2);
        float f4 = fMo573toDpu2uoSUM - fM4390constructorimpl;
        float fM4390constructorimpl2 = Dp.m4390constructorimpl(Dp.m4390constructorimpl(f4) * f);
        float fM4390constructorimpl3 = Dp.m4390constructorimpl(Dp.m4390constructorimpl(f4) * f2);
        int i2 = i << 6;
        Track(SizeKt.fillMaxSize$default(boxScopeInstance.align(Modifier.INSTANCE, Alignment.INSTANCE.getCenterStart()), 0.0f, 1, null), sliderColors, z, f, f2, list, fMo577toPx0680j_42, fMo577toPx0680j_4, composerStartRestartGroup, ((i >> 9) & 112) | 262144 | (i2 & 896) | (i2 & 7168) | (i2 & 57344));
        int i3 = (i >> 3) & 7168;
        int i4 = (i << 12) & 57344;
        m1398SliderThumbgNmqVrs(boxScopeInstance.align(Modifier.INSTANCE, Alignment.INSTANCE.getCenterStart()), fM4390constructorimpl2, mutableInteractionSource, sliderColors, z, fM4390constructorimpl, composerStartRestartGroup, ((i >> 12) & 896) | 196608 | i3 | i4);
        m1398SliderThumbgNmqVrs(boxScopeInstance.align(Modifier.INSTANCE, Alignment.INSTANCE.getCenterStart()), fM4390constructorimpl3, mutableInteractionSource2, sliderColors, z, fM4390constructorimpl, composerStartRestartGroup, ((i >> 15) & 896) | 196608 | i3 | i4);
        composerStartRestartGroup.endReplaceableGroup();
        composerStartRestartGroup.endReplaceableGroup();
        composerStartRestartGroup.endReplaceableGroup();
        composerStartRestartGroup.endNode();
        composerStartRestartGroup.endReplaceableGroup();
        composerStartRestartGroup.endReplaceableGroup();
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.SliderKt.RangeSliderImpl.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                invoke(composer2, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer2, int i5) {
                SliderKt.RangeSliderImpl(z, f, f2, list, sliderColors, f3, mutableInteractionSource, mutableInteractionSource2, modifier, composer2, i | 1);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: SliderThumb-gNmqVrs, reason: not valid java name */
    public static final void m1398SliderThumbgNmqVrs(final Modifier modifier, final float f, final MutableInteractionSource mutableInteractionSource, final SliderColors sliderColors, final boolean z, final float f2, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(1690330031);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(SliderThumb)P(3,4:c#ui.unit.Dp,2!,5:c#ui.unit.Dp)599@24658L1423:Slider.kt#jmzs0o");
        if ((i & 14) == 0) {
            i2 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 112) == 0) {
            i2 |= composerStartRestartGroup.changed(f) ? 32 : 16;
        }
        if ((i & 896) == 0) {
            i2 |= composerStartRestartGroup.changed(mutableInteractionSource) ? 256 : 128;
        }
        if ((i & 7168) == 0) {
            i2 |= composerStartRestartGroup.changed(sliderColors) ? 2048 : 1024;
        }
        if ((57344 & i) == 0) {
            i2 |= composerStartRestartGroup.changed(z) ? 16384 : 8192;
        }
        if ((458752 & i) == 0) {
            i2 |= composerStartRestartGroup.changed(f2) ? 131072 : 65536;
        }
        if (((374491 & i2) ^ 74898) != 0 || !composerStartRestartGroup.getSkipping()) {
            Modifier modifierM694paddingqDBjuR0$default = PaddingKt.m694paddingqDBjuR0$default(modifier, f, 0.0f, 0.0f, 0.0f, 14, null);
            composerStartRestartGroup.startReplaceableGroup(-1990474327);
            ComposerKt.sourceInformation(composerStartRestartGroup, "C(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false, composerStartRestartGroup, 0);
            composerStartRestartGroup.startReplaceableGroup(1376089335);
            ComposerKt.sourceInformation(composerStartRestartGroup, "C(Layout)P(!1,2)71@2788L7,72@2843L7,73@2855L389:Layout.kt#80mrfh");
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 103361330, "C:CompositionLocal.kt#9igjgp");
            Object objConsume = composerStartRestartGroup.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Density density = (Density) objConsume;
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 103361330, "C:CompositionLocal.kt#9igjgp");
            Object objConsume2 = composerStartRestartGroup.consume(localLayoutDirection);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            LayoutDirection layoutDirection = (LayoutDirection) objConsume2;
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf = LayoutKt.materializerOf(modifierM694paddingqDBjuR0$default);
            if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor);
            } else {
                composerStartRestartGroup.useNode();
            }
            composerStartRestartGroup.disableReusing();
            Composer composerM1518constructorimpl = Updater.m1518constructorimpl(composerStartRestartGroup);
            Updater.m1525setimpl(composerM1518constructorimpl, measurePolicyRememberBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m1525setimpl(composerM1518constructorimpl, density, ComposeUiNode.INSTANCE.getSetDensity());
            Updater.m1525setimpl(composerM1518constructorimpl, layoutDirection, ComposeUiNode.INSTANCE.getSetLayoutDirection());
            composerStartRestartGroup.enableReusing();
            function3MaterializerOf.invoke(SkippableUpdater.m1509boximpl(SkippableUpdater.m1510constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 0);
            composerStartRestartGroup.startReplaceableGroup(2058660585);
            composerStartRestartGroup.startReplaceableGroup(-1253629305);
            ComposerKt.sourceInformation(composerStartRestartGroup, "C72@3384L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            composerStartRestartGroup.startReplaceableGroup(-528165580);
            ComposerKt.sourceInformation(composerStartRestartGroup, "C600@24725L46,601@24814L658,601@24780L692,624@25828L59,627@26026L19,619@25634L441:Slider.kt#jmzs0o");
            composerStartRestartGroup.startReplaceableGroup(-3687241);
            ComposerKt.sourceInformation(composerStartRestartGroup, "C(remember):Composables.kt#9igjgp");
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = SnapshotStateKt.mutableStateListOf();
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            composerStartRestartGroup.endReplaceableGroup();
            SnapshotStateList snapshotStateList = (SnapshotStateList) objRememberedValue;
            int i3 = i2 >> 6;
            int i4 = i3 & 14;
            composerStartRestartGroup.startReplaceableGroup(-3686552);
            ComposerKt.sourceInformation(composerStartRestartGroup, "C(remember)P(1,2):Composables.kt#9igjgp");
            boolean zChanged = composerStartRestartGroup.changed(mutableInteractionSource) | composerStartRestartGroup.changed(snapshotStateList);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = (Function2) new SliderKt$SliderThumb$1$1$1(mutableInteractionSource, snapshotStateList, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            composerStartRestartGroup.endReplaceableGroup();
            EffectsKt.LaunchedEffect(mutableInteractionSource, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue2, composerStartRestartGroup, i4);
            float fM4390constructorimpl = snapshotStateList.isEmpty() ^ true ? ThumbPressedElevation : ThumbDefaultElevation;
            Modifier modifierIndication = IndicationKt.indication(SizeKt.m735sizeVpY3zN4(Modifier.INSTANCE, f2, f2), mutableInteractionSource, RippleKt.m1486rememberRipple9IZ8Weo(false, ThumbRippleRadius, 0L, composerStartRestartGroup, 54, 4));
            if (!z) {
                fM4390constructorimpl = Dp.m4390constructorimpl(0);
            }
            SpacerKt.Spacer(BackgroundKt.m426backgroundbw27NRU(ShadowKt.m1559shadowziNgDLE(modifierIndication, fM4390constructorimpl, RoundedCornerShapeKt.getCircleShape(), false), sliderColors.thumbColor(z, composerStartRestartGroup, ((i2 >> 12) & 14) | (i3 & 112)).getValue().m1887unboximpl(), RoundedCornerShapeKt.getCircleShape()), composerStartRestartGroup, 0);
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endNode();
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endReplaceableGroup();
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.SliderKt$SliderThumb$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                invoke(composer2, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer2, int i5) {
                SliderKt.m1398SliderThumbgNmqVrs(modifier, f, mutableInteractionSource, sliderColors, z, f2, composer2, i | 1);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void Track(final Modifier modifier, final SliderColors sliderColors, final boolean z, final float f, final float f2, final List<Float> list, final float f3, final float f4, Composer composer, final int i) {
        Composer composerStartRestartGroup = composer.startRestartGroup(1052525940);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(Track)P(2!2,4!1,6)643@26374L35,644@26444L34,645@26514L34,646@26582L33,647@26620L1427:Slider.kt#jmzs0o");
        int i2 = ((i >> 6) & 14) | 48 | ((i << 3) & 896);
        final State<Color> stateTrackColor = sliderColors.trackColor(z, false, composerStartRestartGroup, i2);
        final State<Color> stateTrackColor2 = sliderColors.trackColor(z, true, composerStartRestartGroup, i2);
        final State<Color> stateTickColor = sliderColors.tickColor(z, false, composerStartRestartGroup, i2);
        final State<Color> stateTickColor2 = sliderColors.tickColor(z, true, composerStartRestartGroup, i2);
        CanvasKt.Canvas(modifier, new Function1<DrawScope, Unit>() { // from class: androidx.compose.material.SliderKt.Track.1
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
                boolean z2 = Canvas.getLayoutDirection() == LayoutDirection.Rtl;
                long jOffset = OffsetKt.Offset(f3, Offset.m1640getYimpl(Canvas.mo2416getCenterF1C5BW0()));
                long jOffset2 = OffsetKt.Offset(Size.m1708getWidthimpl(Canvas.mo2417getSizeNHjbRc()) - f3, Offset.m1640getYimpl(Canvas.mo2416getCenterF1C5BW0()));
                long j = z2 ? jOffset2 : jOffset;
                long j2 = z2 ? jOffset : jOffset2;
                long j3 = j2;
                long j4 = j;
                DrawScope.m2404drawLineNGM6Ib0$default(Canvas, stateTrackColor.getValue().m1887unboximpl(), j, j2, f4, StrokeCap.INSTANCE.m2221getRoundKaPHkGw(), null, 0.0f, null, 0, DefaultFrameSampler.DESIRED_FRAME_WIDTH, null);
                DrawScope.m2404drawLineNGM6Ib0$default(Canvas, stateTrackColor2.getValue().m1887unboximpl(), OffsetKt.Offset(Offset.m1639getXimpl(j4) + ((Offset.m1639getXimpl(j3) - Offset.m1639getXimpl(j4)) * f), Offset.m1640getYimpl(Canvas.mo2416getCenterF1C5BW0())), OffsetKt.Offset(Offset.m1639getXimpl(j4) + ((Offset.m1639getXimpl(j3) - Offset.m1639getXimpl(j4)) * f2), Offset.m1640getYimpl(Canvas.mo2416getCenterF1C5BW0())), f4, StrokeCap.INSTANCE.m2221getRoundKaPHkGw(), null, 0.0f, null, 0, DefaultFrameSampler.DESIRED_FRAME_WIDTH, null);
                List<Float> list2 = list;
                float f5 = f2;
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                for (Object obj : list2) {
                    Boolean boolValueOf = Boolean.valueOf(((Number) obj).floatValue() > f5);
                    Object obj2 = linkedHashMap.get(boolValueOf);
                    if (obj2 == null) {
                        obj2 = (List) new ArrayList();
                        linkedHashMap.put(boolValueOf, obj2);
                    }
                    ((List) obj2).add(obj);
                }
                State<Color> state = stateTickColor;
                State<Color> state2 = stateTickColor2;
                float f6 = f4;
                for (Map.Entry entry : linkedHashMap.entrySet()) {
                    boolean zBooleanValue = ((Boolean) entry.getKey()).booleanValue();
                    List list3 = (List) entry.getValue();
                    ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list3, 10));
                    Iterator it = list3.iterator();
                    while (it.hasNext()) {
                        arrayList.add(Offset.m1628boximpl(OffsetKt.Offset(Offset.m1639getXimpl(OffsetKt.m1662lerpWko1d7g(j4, j3, ((Number) it.next()).floatValue())), Offset.m1640getYimpl(Canvas.mo2416getCenterF1C5BW0()))));
                    }
                    long j5 = j3;
                    long j6 = j4;
                    DrawScope.m2409drawPointsF8ZwMP8$default(Canvas, arrayList, PointMode.INSTANCE.m2173getPointsr_lszbg(), (zBooleanValue ? state : state2).getValue().m1887unboximpl(), f6, StrokeCap.INSTANCE.m2221getRoundKaPHkGw(), null, 0.0f, null, 0, DefaultFrameSampler.DESIRED_FRAME_WIDTH, null);
                    j4 = j6;
                    f6 = f6;
                    j3 = j5;
                }
            }
        }, composerStartRestartGroup, i & 14);
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.SliderKt.Track.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                invoke(composer2, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer2, int i3) {
                SliderKt.Track(modifier, sliderColors, z, f, f2, list, f3, f4, composer2, i | 1);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float snapValueToTick(float f, List<Float> list, float f2, float f3) {
        Object obj;
        Iterator<T> it = list.iterator();
        if (it.hasNext()) {
            Object next = it.next();
            if (it.hasNext()) {
                float fAbs = Math.abs(MathHelpersKt.lerp(f2, f3, ((Number) next).floatValue()) - f);
                do {
                    Object next2 = it.next();
                    float fAbs2 = Math.abs(MathHelpersKt.lerp(f2, f3, ((Number) next2).floatValue()) - f);
                    if (Float.compare(fAbs, fAbs2) > 0) {
                        next = next2;
                        fAbs = fAbs2;
                    }
                } while (it.hasNext());
            }
            obj = next;
        } else {
            obj = null;
        }
        Float f4 = (Float) obj;
        return f4 == null ? f : MathHelpersKt.lerp(f2, f3, f4.floatValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /* renamed from: awaitSlop-rnUCldI, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object m1401awaitSloprnUCldI(androidx.compose.ui.input.pointer.AwaitPointerEventScope r5, long r6, kotlin.coroutines.Continuation<? super kotlin.Pair<androidx.compose.ui.input.pointer.PointerInputChange, java.lang.Float>> r8) {
        /*
            boolean r0 = r8 instanceof androidx.compose.material.SliderKt$awaitSlop$1
            if (r0 == 0) goto L14
            r0 = r8
            androidx.compose.material.SliderKt$awaitSlop$1 r0 = (androidx.compose.material.SliderKt$awaitSlop$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L19
        L14:
            androidx.compose.material.SliderKt$awaitSlop$1 r0 = new androidx.compose.material.SliderKt$awaitSlop$1
            r0.<init>(r8)
        L19:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L36
            if (r2 != r3) goto L2e
            java.lang.Object r5 = r0.L$0
            kotlin.jvm.internal.Ref$FloatRef r5 = (kotlin.jvm.internal.Ref.FloatRef) r5
            kotlin.ResultKt.throwOnFailure(r8)
            goto L53
        L2e:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L36:
            kotlin.ResultKt.throwOnFailure(r8)
            kotlin.jvm.internal.Ref$FloatRef r8 = new kotlin.jvm.internal.Ref$FloatRef
            r8.<init>()
            androidx.compose.material.SliderKt$awaitSlop$postTouchSlop$1 r2 = new androidx.compose.material.SliderKt$awaitSlop$postTouchSlop$1
            r2.<init>()
            kotlin.jvm.functions.Function2 r2 = (kotlin.jvm.functions.Function2) r2
            r0.L$0 = r8
            r0.label = r3
            java.lang.Object r5 = androidx.compose.foundation.gestures.DragGestureDetectorKt.m510awaitHorizontalTouchSlopOrCancellationjO51t88(r5, r6, r2, r0)
            if (r5 != r1) goto L50
            return r1
        L50:
            r4 = r8
            r8 = r5
            r5 = r4
        L53:
            androidx.compose.ui.input.pointer.PointerInputChange r8 = (androidx.compose.ui.input.pointer.PointerInputChange) r8
            if (r8 == 0) goto L62
            float r5 = r5.element
            java.lang.Float r5 = kotlin.coroutines.jvm.internal.Boxing.boxFloat(r5)
            kotlin.Pair r5 = kotlin.TuplesKt.to(r8, r5)
            goto L63
        L62:
            r5 = 0
        L63:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.SliderKt.m1401awaitSloprnUCldI(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final List<Float> stepsToTickFractions(int i) {
        if (i == 0) {
            return CollectionsKt.emptyList();
        }
        int i2 = i + 2;
        ArrayList arrayList = new ArrayList(i2);
        for (int i3 = 0; i3 < i2; i3++) {
            arrayList.add(Float.valueOf(i3 / (i + 1)));
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float scale(float f, float f2, float f3, float f4, float f5) {
        return MathHelpersKt.lerp(f4, f5, calcFraction(f, f2, f3));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ClosedFloatingPointRange<Float> scale(float f, float f2, ClosedFloatingPointRange<Float> closedFloatingPointRange, float f3, float f4) {
        return RangesKt.rangeTo(scale(f, f2, closedFloatingPointRange.getStart().floatValue(), f3, f4), scale(f, f2, closedFloatingPointRange.getEndInclusive().floatValue(), f3, f4));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float calcFraction(float f, float f2, float f3) {
        float f4 = f2 - f;
        return RangesKt.coerceIn(f4 == 0.0f ? 0.0f : (f3 - f) / f4, 0.0f, 1.0f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void CorrectValueSideEffect(final Function1<? super Float, Float> function1, final ClosedFloatingPointRange<Float> closedFloatingPointRange, final MutableState<Float> mutableState, final float f, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(1481631415);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(CorrectValueSideEffect)P(!1,2,3)739@29831L220,739@29820L231:Slider.kt#jmzs0o");
        if ((i & 14) == 0) {
            i2 = (composerStartRestartGroup.changed(function1) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 112) == 0) {
            i2 |= composerStartRestartGroup.changed(closedFloatingPointRange) ? 32 : 16;
        }
        if ((i & 896) == 0) {
            i2 |= composerStartRestartGroup.changed(mutableState) ? 256 : 128;
        }
        if ((i & 7168) == 0) {
            i2 |= composerStartRestartGroup.changed(f) ? 2048 : 1024;
        }
        if (((i2 & 5851) ^ 1170) != 0 || !composerStartRestartGroup.getSkipping()) {
            Object[] objArr = {closedFloatingPointRange, function1, Float.valueOf(f), mutableState};
            composerStartRestartGroup.startReplaceableGroup(-3685570);
            ComposerKt.sourceInformation(composerStartRestartGroup, "C(remember)P(1):Composables.kt#9igjgp");
            int i3 = 0;
            boolean zChanged = false;
            while (i3 < 4) {
                Object obj = objArr[i3];
                i3++;
                zChanged |= composerStartRestartGroup.changed(obj);
            }
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = (Function0) new Function0<Unit>() { // from class: androidx.compose.material.SliderKt$CorrectValueSideEffect$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        float fFloatValue = (closedFloatingPointRange.getEndInclusive().floatValue() - closedFloatingPointRange.getStart().floatValue()) / 1000;
                        float fFloatValue2 = function1.invoke(Float.valueOf(f)).floatValue();
                        if (Math.abs(fFloatValue2 - mutableState.getValue().floatValue()) > fFloatValue) {
                            mutableState.setValue(Float.valueOf(fFloatValue2));
                        }
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            composerStartRestartGroup.endReplaceableGroup();
            EffectsKt.SideEffect((Function0) objRememberedValue, composerStartRestartGroup, 0);
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.SliderKt.CorrectValueSideEffect.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                invoke(composer2, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer2, int i4) {
                SliderKt.CorrectValueSideEffect(function1, closedFloatingPointRange, mutableState, f, composer2, i | 1);
            }
        });
    }

    static /* synthetic */ Modifier sliderSemantics$default(Modifier modifier, float f, List list, boolean z, Function1 function1, ClosedFloatingPointRange closedFloatingPointRange, int i, int i2, Object obj) {
        if ((i2 & 16) != 0) {
            closedFloatingPointRange = RangesKt.rangeTo(0.0f, 1.0f);
        }
        ClosedFloatingPointRange closedFloatingPointRange2 = closedFloatingPointRange;
        if ((i2 & 32) != 0) {
            i = 0;
        }
        return sliderSemantics(modifier, f, list, z, function1, closedFloatingPointRange2, i);
    }

    private static final Modifier sliderSemantics(Modifier modifier, float f, final List<Float> list, final boolean z, final Function1<? super Float, Unit> function1, final ClosedFloatingPointRange<Float> closedFloatingPointRange, final int i) {
        final float fCoerceIn = RangesKt.coerceIn(f, closedFloatingPointRange.getStart().floatValue(), closedFloatingPointRange.getEndInclusive().floatValue());
        return ProgressSemanticsKt.progressSemantics(SemanticsModifierKt.semantics(modifier, true, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material.SliderKt.sliderSemantics.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                invoke2(semanticsPropertyReceiver);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SemanticsPropertyReceiver semantics) {
                Intrinsics.checkNotNullParameter(semantics, "$this$semantics");
                if (!z) {
                    SemanticsPropertiesKt.disabled(semantics);
                }
                final ClosedFloatingPointRange<Float> closedFloatingPointRange2 = closedFloatingPointRange;
                final int i2 = i;
                final List<Float> list2 = list;
                final float f2 = fCoerceIn;
                final Function1<Float, Unit> function12 = function1;
                SemanticsPropertiesKt.setProgress$default(semantics, null, new Function1<Float, Boolean>() { // from class: androidx.compose.material.SliderKt.sliderSemantics.1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Boolean invoke(Float f3) {
                        return Boolean.valueOf(invoke(f3.floatValue()));
                    }

                    public final boolean invoke(float f3) {
                        Object obj;
                        float fCoerceIn2 = RangesKt.coerceIn(f3, closedFloatingPointRange2.getStart().floatValue(), closedFloatingPointRange2.getEndInclusive().floatValue());
                        if (i2 > 0) {
                            List<Float> list3 = list2;
                            ClosedFloatingPointRange<Float> closedFloatingPointRange3 = closedFloatingPointRange2;
                            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list3, 10));
                            Iterator<T> it = list3.iterator();
                            while (it.hasNext()) {
                                arrayList.add(Float.valueOf(MathHelpersKt.lerp(closedFloatingPointRange3.getStart().floatValue(), closedFloatingPointRange3.getEndInclusive().floatValue(), ((Number) it.next()).floatValue())));
                            }
                            Iterator it2 = arrayList.iterator();
                            if (it2.hasNext()) {
                                Object next = it2.next();
                                if (it2.hasNext()) {
                                    float fAbs = Math.abs(((Number) next).floatValue() - fCoerceIn2);
                                    do {
                                        Object next2 = it2.next();
                                        float fAbs2 = Math.abs(((Number) next2).floatValue() - fCoerceIn2);
                                        if (Float.compare(fAbs, fAbs2) > 0) {
                                            next = next2;
                                            fAbs = fAbs2;
                                        }
                                    } while (it2.hasNext());
                                }
                                obj = next;
                            } else {
                                obj = null;
                            }
                            Float f4 = (Float) obj;
                            if (f4 != null) {
                                fCoerceIn2 = f4.floatValue();
                            }
                        }
                        if (fCoerceIn2 == f2) {
                            return false;
                        }
                        function12.invoke(Float.valueOf(fCoerceIn2));
                        return true;
                    }
                }, 1, null);
            }
        }), f, closedFloatingPointRange, i);
    }

    /* compiled from: Slider.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/PointerInputScope;"}, k = 3, mv = {1, 5, 1}, xi = 48)
    @DebugMetadata(c = "androidx.compose.material.SliderKt$sliderPressModifier$1", f = "Slider.kt", i = {}, l = {793}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: androidx.compose.material.SliderKt$sliderPressModifier$1, reason: invalid class name and case insensitive filesystem */
    static final class C03941 extends SuspendLambda implements Function2<PointerInputScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ DraggableState $draggableState;
        final /* synthetic */ State<Function1<Float, Unit>> $gestureEndAction;
        final /* synthetic */ MutableInteractionSource $interactionSource;
        final /* synthetic */ boolean $isRtl;
        final /* synthetic */ float $maxPx;
        final /* synthetic */ State<Float> $rawOffset;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C03941(DraggableState draggableState, MutableInteractionSource mutableInteractionSource, State<? extends Function1<? super Float, Unit>> state, boolean z, float f, State<Float> state2, Continuation<? super C03941> continuation) {
            super(2, continuation);
            this.$draggableState = draggableState;
            this.$interactionSource = mutableInteractionSource;
            this.$gestureEndAction = state;
            this.$isRtl = z;
            this.$maxPx = f;
            this.$rawOffset = state2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C03941 c03941 = new C03941(this.$draggableState, this.$interactionSource, this.$gestureEndAction, this.$isRtl, this.$maxPx, this.$rawOffset, continuation);
            c03941.L$0 = obj;
            return c03941;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
            return ((C03941) create(pointerInputScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (TapGestureDetectorKt.detectTapGestures$default((PointerInputScope) this.L$0, null, null, new C00721(this.$draggableState, this.$interactionSource, this.$gestureEndAction, this.$isRtl, this.$maxPx, this.$rawOffset, null), null, this, 11, null) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i == 1) {
                ResultKt.throwOnFailure(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }

        /* compiled from: Slider.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/gestures/PressGestureScope;", Constants.INAPP_POSITION, "Landroidx/compose/ui/geometry/Offset;"}, k = 3, mv = {1, 5, 1}, xi = 48)
        @DebugMetadata(c = "androidx.compose.material.SliderKt$sliderPressModifier$1$1", f = "Slider.kt", i = {0, 0, 1, 1, 2}, l = {795, 800, LeicaMakernoteDirectory.TAG_WB_GREEN_LEVEL, 813}, m = "invokeSuspend", n = {"$this$detectTapGestures", Constants.INAPP_POSITION, "$this$detectTapGestures", "interaction", "interaction"}, s = {"L$0", "J$0", "L$0", "L$1", "L$0"})
        /* renamed from: androidx.compose.material.SliderKt$sliderPressModifier$1$1, reason: invalid class name and collision with other inner class name */
        static final class C00721 extends SuspendLambda implements Function3<PressGestureScope, Offset, Continuation<? super Unit>, Object> {
            final /* synthetic */ DraggableState $draggableState;
            final /* synthetic */ State<Function1<Float, Unit>> $gestureEndAction;
            final /* synthetic */ MutableInteractionSource $interactionSource;
            final /* synthetic */ boolean $isRtl;
            final /* synthetic */ float $maxPx;
            final /* synthetic */ State<Float> $rawOffset;
            /* synthetic */ long J$0;
            private /* synthetic */ Object L$0;
            Object L$1;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C00721(DraggableState draggableState, MutableInteractionSource mutableInteractionSource, State<? extends Function1<? super Float, Unit>> state, boolean z, float f, State<Float> state2, Continuation<? super C00721> continuation) {
                super(3, continuation);
                this.$draggableState = draggableState;
                this.$interactionSource = mutableInteractionSource;
                this.$gestureEndAction = state;
                this.$isRtl = z;
                this.$maxPx = f;
                this.$rawOffset = state2;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(PressGestureScope pressGestureScope, Offset offset, Continuation<? super Unit> continuation) {
                return m1402invoked4ec7I(pressGestureScope, offset.getPackedValue(), continuation);
            }

            /* renamed from: invoke-d-4ec7I, reason: not valid java name */
            public final Object m1402invoked4ec7I(PressGestureScope pressGestureScope, long j, Continuation<? super Unit> continuation) {
                C00721 c00721 = new C00721(this.$draggableState, this.$interactionSource, this.$gestureEndAction, this.$isRtl, this.$maxPx, this.$rawOffset, continuation);
                c00721.L$0 = pressGestureScope;
                c00721.J$0 = j;
                return c00721.invokeSuspend(Unit.INSTANCE);
            }

            /* compiled from: Slider.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/gestures/DragScope;"}, k = 3, mv = {1, 5, 1}, xi = 48)
            @DebugMetadata(c = "androidx.compose.material.SliderKt$sliderPressModifier$1$1$1", f = "Slider.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: androidx.compose.material.SliderKt$sliderPressModifier$1$1$1, reason: invalid class name and collision with other inner class name */
            static final class C00731 extends SuspendLambda implements Function2<DragScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ boolean $isRtl;
                final /* synthetic */ float $maxPx;
                final /* synthetic */ long $pos;
                final /* synthetic */ State<Float> $rawOffset;
                private /* synthetic */ Object L$0;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C00731(boolean z, float f, long j, State<Float> state, Continuation<? super C00731> continuation) {
                    super(2, continuation);
                    this.$isRtl = z;
                    this.$maxPx = f;
                    this.$pos = j;
                    this.$rawOffset = state;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    C00731 c00731 = new C00731(this.$isRtl, this.$maxPx, this.$pos, this.$rawOffset, continuation);
                    c00731.L$0 = obj;
                    return c00731;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(DragScope dragScope, Continuation<? super Unit> continuation) {
                    return ((C00731) create(dragScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    ((DragScope) this.L$0).dragBy((this.$isRtl ? this.$maxPx - Offset.m1639getXimpl(this.$pos) : Offset.m1639getXimpl(this.$pos)) - this.$rawOffset.getValue().floatValue());
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: Removed duplicated region for block: B:25:0x00ad A[RETURN] */
            /* JADX WARN: Removed duplicated region for block: B:28:0x00c6 A[Catch: CancellationException -> 0x00d6, TryCatch #0 {CancellationException -> 0x00d6, blocks: (B:12:0x0028, B:26:0x00ae, B:28:0x00c6, B:29:0x00ce, B:23:0x009d), top: B:37:0x000d }] */
            /* JADX WARN: Removed duplicated region for block: B:29:0x00ce A[Catch: CancellationException -> 0x00d6, TRY_LEAVE, TryCatch #0 {CancellationException -> 0x00d6, blocks: (B:12:0x0028, B:26:0x00ae, B:28:0x00c6, B:29:0x00ce, B:23:0x009d), top: B:37:0x000d }] */
            /* JADX WARN: Removed duplicated region for block: B:34:0x00f1 A[RETURN] */
            /* JADX WARN: Type inference failed for: r2v0, types: [androidx.compose.foundation.interaction.PressInteraction$Press, int] */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r20) {
                /*
                    Method dump skipped, instructions count: 245
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.SliderKt.C03941.C00721.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Modifier sliderPressModifier(Modifier modifier, DraggableState draggableState, MutableInteractionSource mutableInteractionSource, float f, boolean z, State<Float> state, State<? extends Function1<? super Float, Unit>> state2, boolean z2) {
        return z2 ? SuspendingPointerInputFilterKt.pointerInput(modifier, new Object[]{draggableState, mutableInteractionSource, Float.valueOf(f), Boolean.valueOf(z)}, (Function2<? super PointerInputScope, ? super Continuation<? super Unit>, ? extends Object>) new C03941(draggableState, mutableInteractionSource, state2, z, f, state, null)) : modifier;
    }

    /* compiled from: Slider.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/gestures/DragScope;"}, k = 3, mv = {1, 5, 1}, xi = 48)
    @DebugMetadata(c = "androidx.compose.material.SliderKt$animateToTarget$2", f = "Slider.kt", i = {}, l = {829}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: androidx.compose.material.SliderKt$animateToTarget$2, reason: invalid class name and case insensitive filesystem */
    static final class C03922 extends SuspendLambda implements Function2<DragScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ float $current;
        final /* synthetic */ float $target;
        final /* synthetic */ float $velocity;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03922(float f, float f2, float f3, Continuation<? super C03922> continuation) {
            super(2, continuation);
            this.$current = f;
            this.$target = f2;
            this.$velocity = f3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C03922 c03922 = new C03922(this.$current, this.$target, this.$velocity, continuation);
            c03922.L$0 = obj;
            return c03922;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(DragScope dragScope, Continuation<? super Unit> continuation) {
            return ((C03922) create(dragScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                final DragScope dragScope = (DragScope) this.L$0;
                final Ref.FloatRef floatRef = new Ref.FloatRef();
                floatRef.element = this.$current;
                this.label = 1;
                if (AnimatableKt.Animatable$default(this.$current, 0.0f, 2, null).animateTo(Boxing.boxFloat(this.$target), SliderKt.SliderToTickAnimation, Boxing.boxFloat(this.$velocity), new Function1<Animatable<Float, AnimationVector1D>, Unit>() { // from class: androidx.compose.material.SliderKt.animateToTarget.2.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Animatable<Float, AnimationVector1D> animatable) {
                        invoke2(animatable);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Animatable<Float, AnimationVector1D> animateTo) {
                        Intrinsics.checkNotNullParameter(animateTo, "$this$animateTo");
                        dragScope.dragBy(animateTo.getValue().floatValue() - floatRef.element);
                        floatRef.element = animateTo.getValue().floatValue();
                    }
                }, this) == coroutine_suspended) {
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

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object animateToTarget(DraggableState draggableState, float f, float f2, float f3, Continuation<? super Unit> continuation) {
        Object objDrag$default = DraggableState.drag$default(draggableState, null, new C03922(f, f2, f3, null), continuation, 1, null);
        return objDrag$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objDrag$default : Unit.INSTANCE;
    }

    /* compiled from: Slider.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/PointerInputScope;"}, k = 3, mv = {1, 5, 1}, xi = 48)
    @DebugMetadata(c = "androidx.compose.material.SliderKt$rangeSliderPressDragModifier$1", f = "Slider.kt", i = {}, l = {857}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: androidx.compose.material.SliderKt$rangeSliderPressDragModifier$1, reason: invalid class name and case insensitive filesystem */
    static final class C03931 extends SuspendLambda implements Function2<PointerInputScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ MutableInteractionSource $endInteractionSource;
        final /* synthetic */ State<Function1<Boolean, Unit>> $gestureEndAction;
        final /* synthetic */ boolean $isRtl;
        final /* synthetic */ float $maxPx;
        final /* synthetic */ Function2<Boolean, Float, Unit> $onDrag;
        final /* synthetic */ State<Float> $rawOffsetEnd;
        final /* synthetic */ State<Float> $rawOffsetStart;
        final /* synthetic */ MutableInteractionSource $startInteractionSource;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C03931(MutableInteractionSource mutableInteractionSource, MutableInteractionSource mutableInteractionSource2, State<Float> state, State<Float> state2, Function2<? super Boolean, ? super Float, Unit> function2, boolean z, float f, State<? extends Function1<? super Boolean, Unit>> state3, Continuation<? super C03931> continuation) {
            super(2, continuation);
            this.$startInteractionSource = mutableInteractionSource;
            this.$endInteractionSource = mutableInteractionSource2;
            this.$rawOffsetStart = state;
            this.$rawOffsetEnd = state2;
            this.$onDrag = function2;
            this.$isRtl = z;
            this.$maxPx = f;
            this.$gestureEndAction = state3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C03931 c03931 = new C03931(this.$startInteractionSource, this.$endInteractionSource, this.$rawOffsetStart, this.$rawOffsetEnd, this.$onDrag, this.$isRtl, this.$maxPx, this.$gestureEndAction, continuation);
            c03931.L$0 = obj;
            return c03931;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
            return ((C03931) create(pointerInputScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                PointerInputScope pointerInputScope = (PointerInputScope) this.L$0;
                RangeSliderLogic rangeSliderLogic = new RangeSliderLogic(this.$startInteractionSource, this.$endInteractionSource, this.$rawOffsetStart, this.$rawOffsetEnd, this.$onDrag);
                this.label = 1;
                if (CoroutineScopeKt.coroutineScope(new C00691(pointerInputScope, this.$isRtl, this.$maxPx, this.$rawOffsetEnd, this.$rawOffsetStart, rangeSliderLogic, this.$gestureEndAction, this.$onDrag, null), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i == 1) {
                ResultKt.throwOnFailure(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }

        /* compiled from: Slider.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 5, 1}, xi = 48)
        @DebugMetadata(c = "androidx.compose.material.SliderKt$rangeSliderPressDragModifier$1$1", f = "Slider.kt", i = {}, l = {858}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: androidx.compose.material.SliderKt$rangeSliderPressDragModifier$1$1, reason: invalid class name and collision with other inner class name */
        static final class C00691 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ PointerInputScope $$this$pointerInput;
            final /* synthetic */ State<Function1<Boolean, Unit>> $gestureEndAction;
            final /* synthetic */ boolean $isRtl;
            final /* synthetic */ float $maxPx;
            final /* synthetic */ Function2<Boolean, Float, Unit> $onDrag;
            final /* synthetic */ RangeSliderLogic $rangeSliderLogic;
            final /* synthetic */ State<Float> $rawOffsetEnd;
            final /* synthetic */ State<Float> $rawOffsetStart;
            private /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C00691(PointerInputScope pointerInputScope, boolean z, float f, State<Float> state, State<Float> state2, RangeSliderLogic rangeSliderLogic, State<? extends Function1<? super Boolean, Unit>> state3, Function2<? super Boolean, ? super Float, Unit> function2, Continuation<? super C00691> continuation) {
                super(2, continuation);
                this.$$this$pointerInput = pointerInputScope;
                this.$isRtl = z;
                this.$maxPx = f;
                this.$rawOffsetEnd = state;
                this.$rawOffsetStart = state2;
                this.$rangeSliderLogic = rangeSliderLogic;
                this.$gestureEndAction = state3;
                this.$onDrag = function2;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C00691 c00691 = new C00691(this.$$this$pointerInput, this.$isRtl, this.$maxPx, this.$rawOffsetEnd, this.$rawOffsetStart, this.$rangeSliderLogic, this.$gestureEndAction, this.$onDrag, continuation);
                c00691.L$0 = obj;
                return c00691;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C00691) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* compiled from: Slider.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/PointerInputScope;"}, k = 3, mv = {1, 5, 1}, xi = 48)
            @DebugMetadata(c = "androidx.compose.material.SliderKt$rangeSliderPressDragModifier$1$1$1", f = "Slider.kt", i = {}, l = {859}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: androidx.compose.material.SliderKt$rangeSliderPressDragModifier$1$1$1, reason: invalid class name and collision with other inner class name */
            static final class C00701 extends SuspendLambda implements Function2<PointerInputScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ CoroutineScope $$this$coroutineScope;
                final /* synthetic */ State<Function1<Boolean, Unit>> $gestureEndAction;
                final /* synthetic */ boolean $isRtl;
                final /* synthetic */ float $maxPx;
                final /* synthetic */ Function2<Boolean, Float, Unit> $onDrag;
                final /* synthetic */ RangeSliderLogic $rangeSliderLogic;
                final /* synthetic */ State<Float> $rawOffsetEnd;
                final /* synthetic */ State<Float> $rawOffsetStart;
                private /* synthetic */ Object L$0;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                C00701(boolean z, float f, State<Float> state, State<Float> state2, RangeSliderLogic rangeSliderLogic, CoroutineScope coroutineScope, State<? extends Function1<? super Boolean, Unit>> state3, Function2<? super Boolean, ? super Float, Unit> function2, Continuation<? super C00701> continuation) {
                    super(2, continuation);
                    this.$isRtl = z;
                    this.$maxPx = f;
                    this.$rawOffsetEnd = state;
                    this.$rawOffsetStart = state2;
                    this.$rangeSliderLogic = rangeSliderLogic;
                    this.$$this$coroutineScope = coroutineScope;
                    this.$gestureEndAction = state3;
                    this.$onDrag = function2;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    C00701 c00701 = new C00701(this.$isRtl, this.$maxPx, this.$rawOffsetEnd, this.$rawOffsetStart, this.$rangeSliderLogic, this.$$this$coroutineScope, this.$gestureEndAction, this.$onDrag, continuation);
                    c00701.L$0 = obj;
                    return c00701;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
                    return ((C00701) create(pointerInputScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                /* compiled from: Slider.kt */
                @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {1, 5, 1}, xi = 48)
                @DebugMetadata(c = "androidx.compose.material.SliderKt$rangeSliderPressDragModifier$1$1$1$1", f = "Slider.kt", i = {0, 0, 0}, l = {863, 883, 904}, m = "invokeSuspend", n = {"$this$awaitPointerEventScope", "thumbCaptured", "draggingStart"}, s = {"L$0", "L$1", "L$2"})
                /* renamed from: androidx.compose.material.SliderKt$rangeSliderPressDragModifier$1$1$1$1, reason: invalid class name and collision with other inner class name */
                static final class C00711 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
                    final /* synthetic */ CoroutineScope $$this$coroutineScope;
                    final /* synthetic */ State<Function1<Boolean, Unit>> $gestureEndAction;
                    final /* synthetic */ boolean $isRtl;
                    final /* synthetic */ float $maxPx;
                    final /* synthetic */ Function2<Boolean, Float, Unit> $onDrag;
                    final /* synthetic */ RangeSliderLogic $rangeSliderLogic;
                    final /* synthetic */ State<Float> $rawOffsetEnd;
                    final /* synthetic */ State<Float> $rawOffsetStart;
                    float F$0;
                    private /* synthetic */ Object L$0;
                    Object L$1;
                    Object L$2;
                    Object L$3;
                    Object L$4;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    C00711(boolean z, float f, State<Float> state, State<Float> state2, RangeSliderLogic rangeSliderLogic, CoroutineScope coroutineScope, State<? extends Function1<? super Boolean, Unit>> state3, Function2<? super Boolean, ? super Float, Unit> function2, Continuation<? super C00711> continuation) {
                        super(2, continuation);
                        this.$isRtl = z;
                        this.$maxPx = f;
                        this.$rawOffsetEnd = state;
                        this.$rawOffsetStart = state2;
                        this.$rangeSliderLogic = rangeSliderLogic;
                        this.$$this$coroutineScope = coroutineScope;
                        this.$gestureEndAction = state3;
                        this.$onDrag = function2;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        C00711 c00711 = new C00711(this.$isRtl, this.$maxPx, this.$rawOffsetEnd, this.$rawOffsetStart, this.$rangeSliderLogic, this.$$this$coroutineScope, this.$gestureEndAction, this.$onDrag, continuation);
                        c00711.L$0 = obj;
                        return c00711;
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
                        return ((C00711) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    /* JADX WARN: Removed duplicated region for block: B:34:0x0116  */
                    /* JADX WARN: Removed duplicated region for block: B:52:0x0157  */
                    /* JADX WARN: Removed duplicated region for block: B:55:0x0187 A[RETURN] */
                    /* JADX WARN: Removed duplicated region for block: B:56:0x0188  */
                    /* JADX WARN: Removed duplicated region for block: B:59:0x0191 A[Catch: CancellationException -> 0x01a2, TryCatch #1 {CancellationException -> 0x01a2, blocks: (B:8:0x001d, B:57:0x0189, B:59:0x0191, B:60:0x0199), top: B:69:0x001d }] */
                    /* JADX WARN: Removed duplicated region for block: B:60:0x0199 A[Catch: CancellationException -> 0x01a2, TRY_LEAVE, TryCatch #1 {CancellationException -> 0x01a2, blocks: (B:8:0x001d, B:57:0x0189, B:59:0x0191, B:60:0x0199), top: B:69:0x001d }] */
                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct add '--show-bad-code' argument
                    */
                    public final java.lang.Object invokeSuspend(java.lang.Object r17) {
                        /*
                            Method dump skipped, instructions count: 464
                            To view this dump add '--comments-level debug' option
                        */
                        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.SliderKt.C03931.C00691.C00701.C00711.invokeSuspend(java.lang.Object):java.lang.Object");
                    }

                    /* compiled from: Slider.kt */
                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 5, 1}, xi = 48)
                    @DebugMetadata(c = "androidx.compose.material.SliderKt$rangeSliderPressDragModifier$1$1$1$1$2", f = "Slider.kt", i = {}, l = {921}, m = "invokeSuspend", n = {}, s = {})
                    /* renamed from: androidx.compose.material.SliderKt$rangeSliderPressDragModifier$1$1$1$1$2, reason: invalid class name */
                    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        final /* synthetic */ Ref.BooleanRef $draggingStart;
                        final /* synthetic */ PressInteraction $finishInteraction;
                        final /* synthetic */ RangeSliderLogic $rangeSliderLogic;
                        int label;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        AnonymousClass2(RangeSliderLogic rangeSliderLogic, Ref.BooleanRef booleanRef, PressInteraction pressInteraction, Continuation<? super AnonymousClass2> continuation) {
                            super(2, continuation);
                            this.$rangeSliderLogic = rangeSliderLogic;
                            this.$draggingStart = booleanRef;
                            this.$finishInteraction = pressInteraction;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            return new AnonymousClass2(this.$rangeSliderLogic, this.$draggingStart, this.$finishInteraction, continuation);
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
                                if (this.$rangeSliderLogic.activeInteraction(this.$draggingStart.element).emit(this.$finishInteraction, this) == coroutine_suspended) {
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
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        this.label = 1;
                        if (((PointerInputScope) this.L$0).awaitPointerEventScope(new C00711(this.$isRtl, this.$maxPx, this.$rawOffsetEnd, this.$rawOffsetStart, this.$rangeSliderLogic, this.$$this$coroutineScope, this.$gestureEndAction, this.$onDrag, null), this) == coroutine_suspended) {
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

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                    this.label = 1;
                    if (ForEachGestureKt.forEachGesture(this.$$this$pointerInput, new C00701(this.$isRtl, this.$maxPx, this.$rawOffsetEnd, this.$rawOffsetStart, this.$rangeSliderLogic, coroutineScope, this.$gestureEndAction, this.$onDrag, null), this) == coroutine_suspended) {
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
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Modifier rangeSliderPressDragModifier(Modifier modifier, MutableInteractionSource mutableInteractionSource, MutableInteractionSource mutableInteractionSource2, State<Float> state, State<Float> state2, boolean z, boolean z2, float f, ClosedFloatingPointRange<Float> closedFloatingPointRange, State<? extends Function1<? super Boolean, Unit>> state3, Function2<? super Boolean, ? super Float, Unit> function2) {
        return z ? SuspendingPointerInputFilterKt.pointerInput(modifier, new Object[]{mutableInteractionSource, mutableInteractionSource2, Float.valueOf(f), Boolean.valueOf(z2), closedFloatingPointRange}, (Function2<? super PointerInputScope, ? super Continuation<? super Unit>, ? extends Object>) new C03931(mutableInteractionSource, mutableInteractionSource2, state, state2, function2, z2, f, state3, null)) : modifier;
    }

    static {
        float fM4390constructorimpl = Dp.m4390constructorimpl(48);
        SliderHeight = fM4390constructorimpl;
        float fM4390constructorimpl2 = Dp.m4390constructorimpl(144);
        SliderMinWidth = fM4390constructorimpl2;
        DefaultSliderConstraints = SizeKt.m721heightInVpY3zN4$default(SizeKt.m740widthInVpY3zN4$default(Modifier.INSTANCE, fM4390constructorimpl2, 0.0f, 2, null), 0.0f, fM4390constructorimpl, 1, null);
        SliderToTickAnimation = new TweenSpec<>(100, 0, null, 6, null);
    }
}
