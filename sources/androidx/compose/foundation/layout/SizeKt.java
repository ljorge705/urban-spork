package androidx.compose.foundation.layout;

import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.DpSize;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import com.facebook.react.uimanager.ViewProps;
import io.sentry.protocol.MetricSummary;
import io.sentry.rrweb.RRWebVideoEvent;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Size.kt */
@Metadata(d1 = {"\u0000H\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u0017\u001a\u0010\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\rH\u0002\u001a\u0010\u0010\u000e\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\rH\u0002\u001a\u0010\u0010\u000f\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\rH\u0002\u001a\u0018\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0002\u001a\u0018\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u00162\u0006\u0010\u0013\u001a\u00020\u0014H\u0002\u001a\u0018\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u00182\u0006\u0010\u0013\u001a\u00020\u0014H\u0002\u001a-\u0010\u0019\u001a\u00020\u001a*\u00020\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u001cH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001e\u0010\u001f\u001a\u0016\u0010 \u001a\u00020\u001a*\u00020\u001a2\b\b\u0002\u0010\f\u001a\u00020\rH\u0007\u001a\u0016\u0010!\u001a\u00020\u001a*\u00020\u001a2\b\b\u0002\u0010\f\u001a\u00020\rH\u0007\u001a\u0016\u0010\"\u001a\u00020\u001a*\u00020\u001a2\b\b\u0002\u0010\f\u001a\u00020\rH\u0007\u001a!\u0010#\u001a\u00020\u001a*\u00020\u001a2\u0006\u0010#\u001a\u00020\u001cH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b$\u0010%\u001a-\u0010&\u001a\u00020\u001a*\u00020\u001a2\b\b\u0002\u0010'\u001a\u00020\u001c2\b\b\u0002\u0010(\u001a\u00020\u001cH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b)\u0010\u001f\u001a!\u0010*\u001a\u00020\u001a*\u00020\u001a2\u0006\u0010#\u001a\u00020\u001cH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b+\u0010%\u001a-\u0010,\u001a\u00020\u001a*\u00020\u001a2\b\b\u0002\u0010'\u001a\u00020\u001c2\b\b\u0002\u0010(\u001a\u00020\u001cH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b-\u0010\u001f\u001a!\u0010.\u001a\u00020\u001a*\u00020\u001a2\u0006\u0010/\u001a\u00020\u001cH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b0\u0010%\u001a)\u0010.\u001a\u00020\u001a*\u00020\u001a2\u0006\u00101\u001a\u00020\u001c2\u0006\u0010#\u001a\u00020\u001cH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b2\u0010\u001f\u001a!\u0010.\u001a\u00020\u001a*\u00020\u001a2\u0006\u0010/\u001a\u000203H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b4\u00105\u001aA\u00106\u001a\u00020\u001a*\u00020\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u001c2\b\b\u0002\u00107\u001a\u00020\u001c2\b\b\u0002\u00108\u001a\u00020\u001cH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b9\u0010:\u001a!\u0010;\u001a\u00020\u001a*\u00020\u001a2\u0006\u00101\u001a\u00020\u001cH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b<\u0010%\u001a-\u0010=\u001a\u00020\u001a*\u00020\u001a2\b\b\u0002\u0010'\u001a\u00020\u001c2\b\b\u0002\u0010(\u001a\u00020\u001cH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b>\u0010\u001f\u001a!\u0010/\u001a\u00020\u001a*\u00020\u001a2\u0006\u0010/\u001a\u00020\u001cH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b?\u0010%\u001a)\u0010/\u001a\u00020\u001a*\u00020\u001a2\u0006\u00101\u001a\u00020\u001c2\u0006\u0010#\u001a\u00020\u001cH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b@\u0010\u001f\u001a!\u0010/\u001a\u00020\u001a*\u00020\u001a2\u0006\u0010/\u001a\u000203H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bA\u00105\u001aA\u0010B\u001a\u00020\u001a*\u00020\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u001c2\b\b\u0002\u00107\u001a\u00020\u001c2\b\b\u0002\u00108\u001a\u00020\u001cH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bC\u0010:\u001a!\u00101\u001a\u00020\u001a*\u00020\u001a2\u0006\u00101\u001a\u00020\u001cH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bD\u0010%\u001a-\u0010E\u001a\u00020\u001a*\u00020\u001a2\b\b\u0002\u0010'\u001a\u00020\u001c2\b\b\u0002\u0010(\u001a\u00020\u001cH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bF\u0010\u001f\u001a \u0010G\u001a\u00020\u001a*\u00020\u001a2\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u0014H\u0007\u001a \u0010H\u001a\u00020\u001a*\u00020\u001a2\b\b\u0002\u0010\u0011\u001a\u00020\u00162\b\b\u0002\u0010\u0013\u001a\u00020\u0014H\u0007\u001a \u0010I\u001a\u00020\u001a*\u00020\u001a2\b\b\u0002\u0010\u0011\u001a\u00020\u00182\b\b\u0002\u0010\u0013\u001a\u00020\u0014H\u0007\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006J"}, d2 = {"FillWholeMaxHeight", "Landroidx/compose/foundation/layout/FillModifier;", "FillWholeMaxSize", "FillWholeMaxWidth", "WrapContentHeightCenter", "Landroidx/compose/foundation/layout/WrapContentModifier;", "WrapContentHeightTop", "WrapContentSizeCenter", "WrapContentSizeTopStart", "WrapContentWidthCenter", "WrapContentWidthStart", "createFillHeightModifier", "fraction", "", "createFillSizeModifier", "createFillWidthModifier", "createWrapContentHeightModifier", "align", "Landroidx/compose/ui/Alignment$Vertical;", "unbounded", "", "createWrapContentSizeModifier", "Landroidx/compose/ui/Alignment;", "createWrapContentWidthModifier", "Landroidx/compose/ui/Alignment$Horizontal;", "defaultMinSize", "Landroidx/compose/ui/Modifier;", ViewProps.MIN_WIDTH, "Landroidx/compose/ui/unit/Dp;", ViewProps.MIN_HEIGHT, "defaultMinSize-VpY3zN4", "(Landroidx/compose/ui/Modifier;FF)Landroidx/compose/ui/Modifier;", "fillMaxHeight", "fillMaxSize", "fillMaxWidth", "height", "height-3ABfNKs", "(Landroidx/compose/ui/Modifier;F)Landroidx/compose/ui/Modifier;", "heightIn", MetricSummary.JsonKeys.MIN, "max", "heightIn-VpY3zN4", "requiredHeight", "requiredHeight-3ABfNKs", "requiredHeightIn", "requiredHeightIn-VpY3zN4", "requiredSize", RRWebVideoEvent.JsonKeys.SIZE, "requiredSize-3ABfNKs", "width", "requiredSize-VpY3zN4", "Landroidx/compose/ui/unit/DpSize;", "requiredSize-6HolHcs", "(Landroidx/compose/ui/Modifier;J)Landroidx/compose/ui/Modifier;", "requiredSizeIn", ViewProps.MAX_WIDTH, ViewProps.MAX_HEIGHT, "requiredSizeIn-qDBjuR0", "(Landroidx/compose/ui/Modifier;FFFF)Landroidx/compose/ui/Modifier;", "requiredWidth", "requiredWidth-3ABfNKs", "requiredWidthIn", "requiredWidthIn-VpY3zN4", "size-3ABfNKs", "size-VpY3zN4", "size-6HolHcs", "sizeIn", "sizeIn-qDBjuR0", "width-3ABfNKs", "widthIn", "widthIn-VpY3zN4", "wrapContentHeight", "wrapContentSize", "wrapContentWidth", "foundation-layout_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class SizeKt {
    private static final FillModifier FillWholeMaxWidth = createFillWidthModifier(1.0f);
    private static final FillModifier FillWholeMaxHeight = createFillHeightModifier(1.0f);
    private static final FillModifier FillWholeMaxSize = createFillSizeModifier(1.0f);
    private static final WrapContentModifier WrapContentWidthCenter = createWrapContentWidthModifier(Alignment.INSTANCE.getCenterHorizontally(), false);
    private static final WrapContentModifier WrapContentWidthStart = createWrapContentWidthModifier(Alignment.INSTANCE.getStart(), false);
    private static final WrapContentModifier WrapContentHeightCenter = createWrapContentHeightModifier(Alignment.INSTANCE.getCenterVertically(), false);
    private static final WrapContentModifier WrapContentHeightTop = createWrapContentHeightModifier(Alignment.INSTANCE.getTop(), false);
    private static final WrapContentModifier WrapContentSizeCenter = createWrapContentSizeModifier(Alignment.INSTANCE.getCenter(), false);
    private static final WrapContentModifier WrapContentSizeTopStart = createWrapContentSizeModifier(Alignment.INSTANCE.getTopStart(), false);

    /* renamed from: width-3ABfNKs, reason: not valid java name */
    public static final Modifier m738width3ABfNKs(Modifier width, final float f) {
        Intrinsics.checkNotNullParameter(width, "$this$width");
        return width.then(new SizeModifier(f, 0.0f, f, 0.0f, true, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.layout.SizeKt$width-3ABfNKs$$inlined$debugInspectorInfo$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(InspectorInfo inspectorInfo) {
                invoke2(inspectorInfo);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(InspectorInfo inspectorInfo) {
                Intrinsics.checkNotNullParameter(inspectorInfo, "$this$null");
                inspectorInfo.setName("width");
                inspectorInfo.setValue(Dp.m4388boximpl(f));
            }
        } : InspectableValueKt.getNoInspectorInfo(), 10, null));
    }

    /* renamed from: height-3ABfNKs, reason: not valid java name */
    public static final Modifier m719height3ABfNKs(Modifier height, final float f) {
        Intrinsics.checkNotNullParameter(height, "$this$height");
        return height.then(new SizeModifier(0.0f, f, 0.0f, f, true, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.layout.SizeKt$height-3ABfNKs$$inlined$debugInspectorInfo$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(InspectorInfo inspectorInfo) {
                invoke2(inspectorInfo);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(InspectorInfo inspectorInfo) {
                Intrinsics.checkNotNullParameter(inspectorInfo, "$this$null");
                inspectorInfo.setName("height");
                inspectorInfo.setValue(Dp.m4388boximpl(f));
            }
        } : InspectableValueKt.getNoInspectorInfo(), 5, null));
    }

    /* renamed from: size-6HolHcs, reason: not valid java name */
    public static final Modifier m734size6HolHcs(Modifier size, long j) {
        Intrinsics.checkNotNullParameter(size, "$this$size");
        return m735sizeVpY3zN4(size, DpSize.m4488getWidthD9Ej5fM(j), DpSize.m4486getHeightD9Ej5fM(j));
    }

    /* renamed from: widthIn-VpY3zN4$default, reason: not valid java name */
    public static /* synthetic */ Modifier m740widthInVpY3zN4$default(Modifier modifier, float f, float f2, int i, Object obj) {
        if ((i & 1) != 0) {
            f = Dp.INSTANCE.m4410getUnspecifiedD9Ej5fM();
        }
        if ((i & 2) != 0) {
            f2 = Dp.INSTANCE.m4410getUnspecifiedD9Ej5fM();
        }
        return m739widthInVpY3zN4(modifier, f, f2);
    }

    /* renamed from: widthIn-VpY3zN4, reason: not valid java name */
    public static final Modifier m739widthInVpY3zN4(Modifier widthIn, final float f, final float f2) {
        Intrinsics.checkNotNullParameter(widthIn, "$this$widthIn");
        return widthIn.then(new SizeModifier(f, 0.0f, f2, 0.0f, true, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.layout.SizeKt$widthIn-VpY3zN4$$inlined$debugInspectorInfo$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(InspectorInfo inspectorInfo) {
                invoke2(inspectorInfo);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(InspectorInfo inspectorInfo) {
                Intrinsics.checkNotNullParameter(inspectorInfo, "$this$null");
                inspectorInfo.setName("widthIn");
                inspectorInfo.getProperties().set(MetricSummary.JsonKeys.MIN, Dp.m4388boximpl(f));
                inspectorInfo.getProperties().set("max", Dp.m4388boximpl(f2));
            }
        } : InspectableValueKt.getNoInspectorInfo(), 10, null));
    }

    /* renamed from: heightIn-VpY3zN4$default, reason: not valid java name */
    public static /* synthetic */ Modifier m721heightInVpY3zN4$default(Modifier modifier, float f, float f2, int i, Object obj) {
        if ((i & 1) != 0) {
            f = Dp.INSTANCE.m4410getUnspecifiedD9Ej5fM();
        }
        if ((i & 2) != 0) {
            f2 = Dp.INSTANCE.m4410getUnspecifiedD9Ej5fM();
        }
        return m720heightInVpY3zN4(modifier, f, f2);
    }

    /* renamed from: heightIn-VpY3zN4, reason: not valid java name */
    public static final Modifier m720heightInVpY3zN4(Modifier heightIn, final float f, final float f2) {
        Intrinsics.checkNotNullParameter(heightIn, "$this$heightIn");
        return heightIn.then(new SizeModifier(0.0f, f, 0.0f, f2, true, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.layout.SizeKt$heightIn-VpY3zN4$$inlined$debugInspectorInfo$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(InspectorInfo inspectorInfo) {
                invoke2(inspectorInfo);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(InspectorInfo inspectorInfo) {
                Intrinsics.checkNotNullParameter(inspectorInfo, "$this$null");
                inspectorInfo.setName("heightIn");
                inspectorInfo.getProperties().set(MetricSummary.JsonKeys.MIN, Dp.m4388boximpl(f));
                inspectorInfo.getProperties().set("max", Dp.m4388boximpl(f2));
            }
        } : InspectableValueKt.getNoInspectorInfo(), 5, null));
    }

    /* renamed from: sizeIn-qDBjuR0$default, reason: not valid java name */
    public static /* synthetic */ Modifier m737sizeInqDBjuR0$default(Modifier modifier, float f, float f2, float f3, float f4, int i, Object obj) {
        if ((i & 1) != 0) {
            f = Dp.INSTANCE.m4410getUnspecifiedD9Ej5fM();
        }
        if ((i & 2) != 0) {
            f2 = Dp.INSTANCE.m4410getUnspecifiedD9Ej5fM();
        }
        if ((i & 4) != 0) {
            f3 = Dp.INSTANCE.m4410getUnspecifiedD9Ej5fM();
        }
        if ((i & 8) != 0) {
            f4 = Dp.INSTANCE.m4410getUnspecifiedD9Ej5fM();
        }
        return m736sizeInqDBjuR0(modifier, f, f2, f3, f4);
    }

    /* renamed from: sizeIn-qDBjuR0, reason: not valid java name */
    public static final Modifier m736sizeInqDBjuR0(Modifier sizeIn, final float f, final float f2, final float f3, final float f4) {
        Intrinsics.checkNotNullParameter(sizeIn, "$this$sizeIn");
        return sizeIn.then(new SizeModifier(f, f2, f3, f4, true, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.layout.SizeKt$sizeIn-qDBjuR0$$inlined$debugInspectorInfo$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(InspectorInfo inspectorInfo) {
                invoke2(inspectorInfo);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(InspectorInfo inspectorInfo) {
                Intrinsics.checkNotNullParameter(inspectorInfo, "$this$null");
                inspectorInfo.setName("sizeIn");
                inspectorInfo.getProperties().set(ViewProps.MIN_WIDTH, Dp.m4388boximpl(f));
                inspectorInfo.getProperties().set(ViewProps.MIN_HEIGHT, Dp.m4388boximpl(f2));
                inspectorInfo.getProperties().set(ViewProps.MAX_WIDTH, Dp.m4388boximpl(f3));
                inspectorInfo.getProperties().set(ViewProps.MAX_HEIGHT, Dp.m4388boximpl(f4));
            }
        } : InspectableValueKt.getNoInspectorInfo(), null));
    }

    /* renamed from: requiredWidth-3ABfNKs, reason: not valid java name */
    public static final Modifier m730requiredWidth3ABfNKs(Modifier requiredWidth, final float f) {
        Intrinsics.checkNotNullParameter(requiredWidth, "$this$requiredWidth");
        return requiredWidth.then(new SizeModifier(f, 0.0f, f, 0.0f, false, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.layout.SizeKt$requiredWidth-3ABfNKs$$inlined$debugInspectorInfo$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(InspectorInfo inspectorInfo) {
                invoke2(inspectorInfo);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(InspectorInfo inspectorInfo) {
                Intrinsics.checkNotNullParameter(inspectorInfo, "$this$null");
                inspectorInfo.setName("requiredWidth");
                inspectorInfo.setValue(Dp.m4388boximpl(f));
            }
        } : InspectableValueKt.getNoInspectorInfo(), 10, null));
    }

    /* renamed from: requiredHeight-3ABfNKs, reason: not valid java name */
    public static final Modifier m722requiredHeight3ABfNKs(Modifier requiredHeight, final float f) {
        Intrinsics.checkNotNullParameter(requiredHeight, "$this$requiredHeight");
        return requiredHeight.then(new SizeModifier(0.0f, f, 0.0f, f, false, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.layout.SizeKt$requiredHeight-3ABfNKs$$inlined$debugInspectorInfo$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(InspectorInfo inspectorInfo) {
                invoke2(inspectorInfo);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(InspectorInfo inspectorInfo) {
                Intrinsics.checkNotNullParameter(inspectorInfo, "$this$null");
                inspectorInfo.setName("requiredHeight");
                inspectorInfo.setValue(Dp.m4388boximpl(f));
            }
        } : InspectableValueKt.getNoInspectorInfo(), 5, null));
    }

    /* renamed from: requiredSize-6HolHcs, reason: not valid java name */
    public static final Modifier m726requiredSize6HolHcs(Modifier requiredSize, long j) {
        Intrinsics.checkNotNullParameter(requiredSize, "$this$requiredSize");
        return m727requiredSizeVpY3zN4(requiredSize, DpSize.m4488getWidthD9Ej5fM(j), DpSize.m4486getHeightD9Ej5fM(j));
    }

    /* renamed from: requiredWidthIn-VpY3zN4$default, reason: not valid java name */
    public static /* synthetic */ Modifier m732requiredWidthInVpY3zN4$default(Modifier modifier, float f, float f2, int i, Object obj) {
        if ((i & 1) != 0) {
            f = Dp.INSTANCE.m4410getUnspecifiedD9Ej5fM();
        }
        if ((i & 2) != 0) {
            f2 = Dp.INSTANCE.m4410getUnspecifiedD9Ej5fM();
        }
        return m731requiredWidthInVpY3zN4(modifier, f, f2);
    }

    /* renamed from: requiredWidthIn-VpY3zN4, reason: not valid java name */
    public static final Modifier m731requiredWidthInVpY3zN4(Modifier requiredWidthIn, final float f, final float f2) {
        Intrinsics.checkNotNullParameter(requiredWidthIn, "$this$requiredWidthIn");
        return requiredWidthIn.then(new SizeModifier(f, 0.0f, f2, 0.0f, false, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.layout.SizeKt$requiredWidthIn-VpY3zN4$$inlined$debugInspectorInfo$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(InspectorInfo inspectorInfo) {
                invoke2(inspectorInfo);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(InspectorInfo inspectorInfo) {
                Intrinsics.checkNotNullParameter(inspectorInfo, "$this$null");
                inspectorInfo.setName("requiredWidthIn");
                inspectorInfo.getProperties().set(MetricSummary.JsonKeys.MIN, Dp.m4388boximpl(f));
                inspectorInfo.getProperties().set("max", Dp.m4388boximpl(f2));
            }
        } : InspectableValueKt.getNoInspectorInfo(), 10, null));
    }

    /* renamed from: requiredHeightIn-VpY3zN4$default, reason: not valid java name */
    public static /* synthetic */ Modifier m724requiredHeightInVpY3zN4$default(Modifier modifier, float f, float f2, int i, Object obj) {
        if ((i & 1) != 0) {
            f = Dp.INSTANCE.m4410getUnspecifiedD9Ej5fM();
        }
        if ((i & 2) != 0) {
            f2 = Dp.INSTANCE.m4410getUnspecifiedD9Ej5fM();
        }
        return m723requiredHeightInVpY3zN4(modifier, f, f2);
    }

    /* renamed from: requiredHeightIn-VpY3zN4, reason: not valid java name */
    public static final Modifier m723requiredHeightInVpY3zN4(Modifier requiredHeightIn, final float f, final float f2) {
        Intrinsics.checkNotNullParameter(requiredHeightIn, "$this$requiredHeightIn");
        return requiredHeightIn.then(new SizeModifier(0.0f, f, 0.0f, f2, false, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.layout.SizeKt$requiredHeightIn-VpY3zN4$$inlined$debugInspectorInfo$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(InspectorInfo inspectorInfo) {
                invoke2(inspectorInfo);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(InspectorInfo inspectorInfo) {
                Intrinsics.checkNotNullParameter(inspectorInfo, "$this$null");
                inspectorInfo.setName("requiredHeightIn");
                inspectorInfo.getProperties().set(MetricSummary.JsonKeys.MIN, Dp.m4388boximpl(f));
                inspectorInfo.getProperties().set("max", Dp.m4388boximpl(f2));
            }
        } : InspectableValueKt.getNoInspectorInfo(), 5, null));
    }

    /* renamed from: requiredSizeIn-qDBjuR0$default, reason: not valid java name */
    public static /* synthetic */ Modifier m729requiredSizeInqDBjuR0$default(Modifier modifier, float f, float f2, float f3, float f4, int i, Object obj) {
        if ((i & 1) != 0) {
            f = Dp.INSTANCE.m4410getUnspecifiedD9Ej5fM();
        }
        if ((i & 2) != 0) {
            f2 = Dp.INSTANCE.m4410getUnspecifiedD9Ej5fM();
        }
        if ((i & 4) != 0) {
            f3 = Dp.INSTANCE.m4410getUnspecifiedD9Ej5fM();
        }
        if ((i & 8) != 0) {
            f4 = Dp.INSTANCE.m4410getUnspecifiedD9Ej5fM();
        }
        return m728requiredSizeInqDBjuR0(modifier, f, f2, f3, f4);
    }

    /* renamed from: requiredSizeIn-qDBjuR0, reason: not valid java name */
    public static final Modifier m728requiredSizeInqDBjuR0(Modifier requiredSizeIn, final float f, final float f2, final float f3, final float f4) {
        Intrinsics.checkNotNullParameter(requiredSizeIn, "$this$requiredSizeIn");
        return requiredSizeIn.then(new SizeModifier(f, f2, f3, f4, false, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.layout.SizeKt$requiredSizeIn-qDBjuR0$$inlined$debugInspectorInfo$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(InspectorInfo inspectorInfo) {
                invoke2(inspectorInfo);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(InspectorInfo inspectorInfo) {
                Intrinsics.checkNotNullParameter(inspectorInfo, "$this$null");
                inspectorInfo.setName("requiredSizeIn");
                inspectorInfo.getProperties().set(ViewProps.MIN_WIDTH, Dp.m4388boximpl(f));
                inspectorInfo.getProperties().set(ViewProps.MIN_HEIGHT, Dp.m4388boximpl(f2));
                inspectorInfo.getProperties().set(ViewProps.MAX_WIDTH, Dp.m4388boximpl(f3));
                inspectorInfo.getProperties().set(ViewProps.MAX_HEIGHT, Dp.m4388boximpl(f4));
            }
        } : InspectableValueKt.getNoInspectorInfo(), null));
    }

    public static /* synthetic */ Modifier fillMaxWidth$default(Modifier modifier, float f, int i, Object obj) {
        if ((i & 1) != 0) {
            f = 1.0f;
        }
        return fillMaxWidth(modifier, f);
    }

    public static final Modifier fillMaxWidth(Modifier modifier, float f) {
        Intrinsics.checkNotNullParameter(modifier, "<this>");
        return modifier.then(f == 1.0f ? FillWholeMaxWidth : createFillWidthModifier(f));
    }

    public static /* synthetic */ Modifier fillMaxHeight$default(Modifier modifier, float f, int i, Object obj) {
        if ((i & 1) != 0) {
            f = 1.0f;
        }
        return fillMaxHeight(modifier, f);
    }

    public static final Modifier fillMaxHeight(Modifier modifier, float f) {
        Intrinsics.checkNotNullParameter(modifier, "<this>");
        return modifier.then(f == 1.0f ? FillWholeMaxHeight : createFillHeightModifier(f));
    }

    public static /* synthetic */ Modifier fillMaxSize$default(Modifier modifier, float f, int i, Object obj) {
        if ((i & 1) != 0) {
            f = 1.0f;
        }
        return fillMaxSize(modifier, f);
    }

    public static final Modifier fillMaxSize(Modifier modifier, float f) {
        Intrinsics.checkNotNullParameter(modifier, "<this>");
        return modifier.then(f == 1.0f ? FillWholeMaxSize : createFillSizeModifier(f));
    }

    public static /* synthetic */ Modifier wrapContentWidth$default(Modifier modifier, Alignment.Horizontal horizontal, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            horizontal = Alignment.INSTANCE.getCenterHorizontally();
        }
        if ((i & 2) != 0) {
            z = false;
        }
        return wrapContentWidth(modifier, horizontal, z);
    }

    public static final Modifier wrapContentWidth(Modifier modifier, Alignment.Horizontal align, boolean z) {
        WrapContentModifier wrapContentModifierCreateWrapContentWidthModifier;
        Intrinsics.checkNotNullParameter(modifier, "<this>");
        Intrinsics.checkNotNullParameter(align, "align");
        if (!Intrinsics.areEqual(align, Alignment.INSTANCE.getCenterHorizontally()) || z) {
            wrapContentModifierCreateWrapContentWidthModifier = (!Intrinsics.areEqual(align, Alignment.INSTANCE.getStart()) || z) ? createWrapContentWidthModifier(align, z) : WrapContentWidthStart;
        } else {
            wrapContentModifierCreateWrapContentWidthModifier = WrapContentWidthCenter;
        }
        return modifier.then(wrapContentModifierCreateWrapContentWidthModifier);
    }

    public static /* synthetic */ Modifier wrapContentHeight$default(Modifier modifier, Alignment.Vertical vertical, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            vertical = Alignment.INSTANCE.getCenterVertically();
        }
        if ((i & 2) != 0) {
            z = false;
        }
        return wrapContentHeight(modifier, vertical, z);
    }

    public static final Modifier wrapContentHeight(Modifier modifier, Alignment.Vertical align, boolean z) {
        WrapContentModifier wrapContentModifierCreateWrapContentHeightModifier;
        Intrinsics.checkNotNullParameter(modifier, "<this>");
        Intrinsics.checkNotNullParameter(align, "align");
        if (!Intrinsics.areEqual(align, Alignment.INSTANCE.getCenterVertically()) || z) {
            wrapContentModifierCreateWrapContentHeightModifier = (!Intrinsics.areEqual(align, Alignment.INSTANCE.getTop()) || z) ? createWrapContentHeightModifier(align, z) : WrapContentHeightTop;
        } else {
            wrapContentModifierCreateWrapContentHeightModifier = WrapContentHeightCenter;
        }
        return modifier.then(wrapContentModifierCreateWrapContentHeightModifier);
    }

    public static /* synthetic */ Modifier wrapContentSize$default(Modifier modifier, Alignment alignment, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            alignment = Alignment.INSTANCE.getCenter();
        }
        if ((i & 2) != 0) {
            z = false;
        }
        return wrapContentSize(modifier, alignment, z);
    }

    public static final Modifier wrapContentSize(Modifier modifier, Alignment align, boolean z) {
        WrapContentModifier wrapContentModifierCreateWrapContentSizeModifier;
        Intrinsics.checkNotNullParameter(modifier, "<this>");
        Intrinsics.checkNotNullParameter(align, "align");
        if (!Intrinsics.areEqual(align, Alignment.INSTANCE.getCenter()) || z) {
            wrapContentModifierCreateWrapContentSizeModifier = (!Intrinsics.areEqual(align, Alignment.INSTANCE.getTopStart()) || z) ? createWrapContentSizeModifier(align, z) : WrapContentSizeTopStart;
        } else {
            wrapContentModifierCreateWrapContentSizeModifier = WrapContentSizeCenter;
        }
        return modifier.then(wrapContentModifierCreateWrapContentSizeModifier);
    }

    /* renamed from: defaultMinSize-VpY3zN4$default, reason: not valid java name */
    public static /* synthetic */ Modifier m718defaultMinSizeVpY3zN4$default(Modifier modifier, float f, float f2, int i, Object obj) {
        if ((i & 1) != 0) {
            f = Dp.INSTANCE.m4410getUnspecifiedD9Ej5fM();
        }
        if ((i & 2) != 0) {
            f2 = Dp.INSTANCE.m4410getUnspecifiedD9Ej5fM();
        }
        return m717defaultMinSizeVpY3zN4(modifier, f, f2);
    }

    /* renamed from: defaultMinSize-VpY3zN4, reason: not valid java name */
    public static final Modifier m717defaultMinSizeVpY3zN4(Modifier defaultMinSize, final float f, final float f2) {
        Intrinsics.checkNotNullParameter(defaultMinSize, "$this$defaultMinSize");
        return defaultMinSize.then(new UnspecifiedConstraintsModifier(f, f2, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.layout.SizeKt$defaultMinSize-VpY3zN4$$inlined$debugInspectorInfo$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(InspectorInfo inspectorInfo) {
                invoke2(inspectorInfo);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(InspectorInfo inspectorInfo) {
                Intrinsics.checkNotNullParameter(inspectorInfo, "$this$null");
                inspectorInfo.setName("defaultMinSize");
                inspectorInfo.getProperties().set(ViewProps.MIN_WIDTH, Dp.m4388boximpl(f));
                inspectorInfo.getProperties().set(ViewProps.MIN_HEIGHT, Dp.m4388boximpl(f2));
            }
        } : InspectableValueKt.getNoInspectorInfo(), null));
    }

    private static final FillModifier createFillWidthModifier(final float f) {
        return new FillModifier(Direction.Horizontal, f, new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.layout.SizeKt.createFillWidthModifier.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(InspectorInfo inspectorInfo) {
                invoke2(inspectorInfo);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(InspectorInfo $receiver) {
                Intrinsics.checkNotNullParameter($receiver, "$this$$receiver");
                $receiver.setName("fillMaxWidth");
                $receiver.getProperties().set("fraction", Float.valueOf(f));
            }
        });
    }

    private static final FillModifier createFillHeightModifier(final float f) {
        return new FillModifier(Direction.Vertical, f, new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.layout.SizeKt.createFillHeightModifier.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(InspectorInfo inspectorInfo) {
                invoke2(inspectorInfo);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(InspectorInfo $receiver) {
                Intrinsics.checkNotNullParameter($receiver, "$this$$receiver");
                $receiver.setName("fillMaxHeight");
                $receiver.getProperties().set("fraction", Float.valueOf(f));
            }
        });
    }

    private static final FillModifier createFillSizeModifier(final float f) {
        return new FillModifier(Direction.Both, f, new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.layout.SizeKt.createFillSizeModifier.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(InspectorInfo inspectorInfo) {
                invoke2(inspectorInfo);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(InspectorInfo $receiver) {
                Intrinsics.checkNotNullParameter($receiver, "$this$$receiver");
                $receiver.setName("fillMaxSize");
                $receiver.getProperties().set("fraction", Float.valueOf(f));
            }
        });
    }

    private static final WrapContentModifier createWrapContentWidthModifier(final Alignment.Horizontal horizontal, final boolean z) {
        return new WrapContentModifier(Direction.Horizontal, z, new Function2<IntSize, LayoutDirection, IntOffset>() { // from class: androidx.compose.foundation.layout.SizeKt.createWrapContentWidthModifier.1
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ IntOffset invoke(IntSize intSize, LayoutDirection layoutDirection) {
                return IntOffset.m4499boximpl(m743invoke5SAbXVA(intSize.getPackedValue(), layoutDirection));
            }

            /* renamed from: invoke-5SAbXVA, reason: not valid java name */
            public final long m743invoke5SAbXVA(long j, LayoutDirection layoutDirection) {
                Intrinsics.checkNotNullParameter(layoutDirection, "layoutDirection");
                return IntOffsetKt.IntOffset(horizontal.align(0, IntSize.m4550getWidthimpl(j), layoutDirection), 0);
            }
        }, horizontal, new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.layout.SizeKt.createWrapContentWidthModifier.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(InspectorInfo inspectorInfo) {
                invoke2(inspectorInfo);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(InspectorInfo $receiver) {
                Intrinsics.checkNotNullParameter($receiver, "$this$$receiver");
                $receiver.setName("wrapContentWidth");
                $receiver.getProperties().set("align", horizontal);
                $receiver.getProperties().set("unbounded", Boolean.valueOf(z));
            }
        });
    }

    private static final WrapContentModifier createWrapContentHeightModifier(final Alignment.Vertical vertical, final boolean z) {
        return new WrapContentModifier(Direction.Vertical, z, new Function2<IntSize, LayoutDirection, IntOffset>() { // from class: androidx.compose.foundation.layout.SizeKt.createWrapContentHeightModifier.1
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ IntOffset invoke(IntSize intSize, LayoutDirection layoutDirection) {
                return IntOffset.m4499boximpl(m741invoke5SAbXVA(intSize.getPackedValue(), layoutDirection));
            }

            /* renamed from: invoke-5SAbXVA, reason: not valid java name */
            public final long m741invoke5SAbXVA(long j, LayoutDirection layoutDirection) {
                Intrinsics.checkNotNullParameter(layoutDirection, "<anonymous parameter 1>");
                return IntOffsetKt.IntOffset(0, vertical.align(0, IntSize.m4549getHeightimpl(j)));
            }
        }, vertical, new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.layout.SizeKt.createWrapContentHeightModifier.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(InspectorInfo inspectorInfo) {
                invoke2(inspectorInfo);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(InspectorInfo $receiver) {
                Intrinsics.checkNotNullParameter($receiver, "$this$$receiver");
                $receiver.setName("wrapContentHeight");
                $receiver.getProperties().set("align", vertical);
                $receiver.getProperties().set("unbounded", Boolean.valueOf(z));
            }
        });
    }

    private static final WrapContentModifier createWrapContentSizeModifier(final Alignment alignment, final boolean z) {
        return new WrapContentModifier(Direction.Both, z, new Function2<IntSize, LayoutDirection, IntOffset>() { // from class: androidx.compose.foundation.layout.SizeKt.createWrapContentSizeModifier.1
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ IntOffset invoke(IntSize intSize, LayoutDirection layoutDirection) {
                return IntOffset.m4499boximpl(m742invoke5SAbXVA(intSize.getPackedValue(), layoutDirection));
            }

            /* renamed from: invoke-5SAbXVA, reason: not valid java name */
            public final long m742invoke5SAbXVA(long j, LayoutDirection layoutDirection) {
                Intrinsics.checkNotNullParameter(layoutDirection, "layoutDirection");
                return alignment.mo1535alignKFBX0sM(IntSize.INSTANCE.m4555getZeroYbymL2g(), j, layoutDirection);
            }
        }, alignment, new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.layout.SizeKt.createWrapContentSizeModifier.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(InspectorInfo inspectorInfo) {
                invoke2(inspectorInfo);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(InspectorInfo $receiver) {
                Intrinsics.checkNotNullParameter($receiver, "$this$$receiver");
                $receiver.setName("wrapContentSize");
                $receiver.getProperties().set("align", alignment);
                $receiver.getProperties().set("unbounded", Boolean.valueOf(z));
            }
        });
    }

    /* renamed from: size-3ABfNKs, reason: not valid java name */
    public static final Modifier m733size3ABfNKs(Modifier size, final float f) {
        Intrinsics.checkNotNullParameter(size, "$this$size");
        return size.then(new SizeModifier(f, f, f, f, true, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.layout.SizeKt$size-3ABfNKs$$inlined$debugInspectorInfo$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(InspectorInfo inspectorInfo) {
                invoke2(inspectorInfo);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(InspectorInfo inspectorInfo) {
                Intrinsics.checkNotNullParameter(inspectorInfo, "$this$null");
                inspectorInfo.setName(RRWebVideoEvent.JsonKeys.SIZE);
                inspectorInfo.setValue(Dp.m4388boximpl(f));
            }
        } : InspectableValueKt.getNoInspectorInfo(), null));
    }

    /* renamed from: size-VpY3zN4, reason: not valid java name */
    public static final Modifier m735sizeVpY3zN4(Modifier size, final float f, final float f2) {
        Intrinsics.checkNotNullParameter(size, "$this$size");
        return size.then(new SizeModifier(f, f2, f, f2, true, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.layout.SizeKt$size-VpY3zN4$$inlined$debugInspectorInfo$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(InspectorInfo inspectorInfo) {
                invoke2(inspectorInfo);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(InspectorInfo inspectorInfo) {
                Intrinsics.checkNotNullParameter(inspectorInfo, "$this$null");
                inspectorInfo.setName(RRWebVideoEvent.JsonKeys.SIZE);
                inspectorInfo.getProperties().set("width", Dp.m4388boximpl(f));
                inspectorInfo.getProperties().set("height", Dp.m4388boximpl(f2));
            }
        } : InspectableValueKt.getNoInspectorInfo(), null));
    }

    /* renamed from: requiredSize-3ABfNKs, reason: not valid java name */
    public static final Modifier m725requiredSize3ABfNKs(Modifier requiredSize, final float f) {
        Intrinsics.checkNotNullParameter(requiredSize, "$this$requiredSize");
        return requiredSize.then(new SizeModifier(f, f, f, f, false, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.layout.SizeKt$requiredSize-3ABfNKs$$inlined$debugInspectorInfo$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(InspectorInfo inspectorInfo) {
                invoke2(inspectorInfo);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(InspectorInfo inspectorInfo) {
                Intrinsics.checkNotNullParameter(inspectorInfo, "$this$null");
                inspectorInfo.setName("requiredSize");
                inspectorInfo.setValue(Dp.m4388boximpl(f));
            }
        } : InspectableValueKt.getNoInspectorInfo(), null));
    }

    /* renamed from: requiredSize-VpY3zN4, reason: not valid java name */
    public static final Modifier m727requiredSizeVpY3zN4(Modifier requiredSize, final float f, final float f2) {
        Intrinsics.checkNotNullParameter(requiredSize, "$this$requiredSize");
        return requiredSize.then(new SizeModifier(f, f2, f, f2, false, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.layout.SizeKt$requiredSize-VpY3zN4$$inlined$debugInspectorInfo$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(InspectorInfo inspectorInfo) {
                invoke2(inspectorInfo);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(InspectorInfo inspectorInfo) {
                Intrinsics.checkNotNullParameter(inspectorInfo, "$this$null");
                inspectorInfo.setName("requiredSize");
                inspectorInfo.getProperties().set("width", Dp.m4388boximpl(f));
                inspectorInfo.getProperties().set("height", Dp.m4388boximpl(f2));
            }
        } : InspectableValueKt.getNoInspectorInfo(), null));
    }
}
