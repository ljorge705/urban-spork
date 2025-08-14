package androidx.compose.foundation.text;

import androidx.compose.foundation.text.selection.MouseSelectionObserver;
import androidx.compose.foundation.text.selection.MultiWidgetSelectionDelegate;
import androidx.compose.foundation.text.selection.Selectable;
import androidx.compose.foundation.text.selection.Selection;
import androidx.compose.foundation.text.selection.SelectionAdjustment;
import androidx.compose.foundation.text.selection.SelectionRegistrar;
import androidx.compose.foundation.text.selection.SelectionRegistrarKt;
import androidx.compose.foundation.text.selection.TextSelectionMouseDetectorKt;
import androidx.compose.runtime.RememberObserver;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.ClipOp;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.input.pointer.PointerIconKt;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.AlignmentLineKt;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.OnGloballyPositionedModifierKt;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.IntSize;
import com.facebook.react.uimanager.ViewProps;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;

/* compiled from: CoreText.kt */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\b\u0010\u001f\u001a\u00020 H\u0016J\b\u0010!\u001a\u00020 H\u0016J\b\u0010\"\u001a\u00020 H\u0016J%\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020&H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b(\u0010)J\u000e\u0010*\u001a\u00020 2\u0006\u0010+\u001a\u00020,J\u0010\u0010-\u001a\u00020 2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016J\f\u0010.\u001a\u00020\u0006*\u00020\u0006H\u0003R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00020\bX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0013R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001b\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006/"}, d2 = {"Landroidx/compose/foundation/text/TextController;", "Landroidx/compose/runtime/RememberObserver;", "state", "Landroidx/compose/foundation/text/TextState;", "(Landroidx/compose/foundation/text/TextState;)V", "coreModifiers", "Landroidx/compose/ui/Modifier;", "longPressDragObserver", "Landroidx/compose/foundation/text/TextDragObserver;", "getLongPressDragObserver", "()Landroidx/compose/foundation/text/TextDragObserver;", "setLongPressDragObserver", "(Landroidx/compose/foundation/text/TextDragObserver;)V", "measurePolicy", "Landroidx/compose/ui/layout/MeasurePolicy;", "getMeasurePolicy", "()Landroidx/compose/ui/layout/MeasurePolicy;", "modifiers", "getModifiers", "()Landroidx/compose/ui/Modifier;", "selectionModifiers", "selectionRegistrar", "Landroidx/compose/foundation/text/selection/SelectionRegistrar;", "<set-?>", "semanticsModifier", "getSemanticsModifier$foundation_release", "getState", "()Landroidx/compose/foundation/text/TextState;", "createSemanticsModifierFor", "text", "Landroidx/compose/ui/text/AnnotatedString;", "onAbandoned", "", "onForgotten", "onRemembered", "outOfBoundary", "", ViewProps.START, "Landroidx/compose/ui/geometry/Offset;", ViewProps.END, "outOfBoundary-0a9Yr6o", "(JJ)Z", "setTextDelegate", "textDelegate", "Landroidx/compose/foundation/text/TextDelegate;", "update", "drawTextAndSelectionBehind", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class TextController implements RememberObserver {
    private final Modifier coreModifiers;
    public TextDragObserver longPressDragObserver;
    private final MeasurePolicy measurePolicy;
    private Modifier selectionModifiers;
    private SelectionRegistrar selectionRegistrar;
    private Modifier semanticsModifier;
    private final TextState state;

    public final MeasurePolicy getMeasurePolicy() {
        return this.measurePolicy;
    }

    /* renamed from: getSemanticsModifier$foundation_release, reason: from getter */
    public final Modifier getSemanticsModifier() {
        return this.semanticsModifier;
    }

    public final TextState getState() {
        return this.state;
    }

    public final void setLongPressDragObserver(TextDragObserver textDragObserver) {
        Intrinsics.checkNotNullParameter(textDragObserver, "<set-?>");
        this.longPressDragObserver = textDragObserver;
    }

    public TextController(TextState state) {
        Intrinsics.checkNotNullParameter(state, "state");
        this.state = state;
        this.measurePolicy = new MeasurePolicy() { // from class: androidx.compose.foundation.text.TextController$measurePolicy$1
            @Override // androidx.compose.ui.layout.MeasurePolicy
            /* renamed from: measure-3p2s80s */
            public MeasureResult mo287measure3p2s80s(MeasureScope measure, List<? extends Measurable> measurables, long j) {
                int i;
                Pair pair;
                SelectionRegistrar selectionRegistrar;
                Intrinsics.checkNotNullParameter(measure, "$this$measure");
                Intrinsics.checkNotNullParameter(measurables, "measurables");
                this.this$0.getState().getLayoutInvalidation();
                TextLayoutResult layoutResult = this.this$0.getState().getLayoutResult();
                TextLayoutResult textLayoutResultM1036layoutNN6EwU = this.this$0.getState().getTextDelegate().m1036layoutNN6EwU(j, measure.getLayoutDirection(), layoutResult);
                if (!Intrinsics.areEqual(layoutResult, textLayoutResultM1036layoutNN6EwU)) {
                    this.this$0.getState().getOnTextLayout().invoke(textLayoutResultM1036layoutNN6EwU);
                    if (layoutResult != null) {
                        TextController textController = this.this$0;
                        if (!Intrinsics.areEqual(layoutResult.getLayoutInput().getText(), textLayoutResultM1036layoutNN6EwU.getLayoutInput().getText()) && (selectionRegistrar = textController.selectionRegistrar) != null) {
                            selectionRegistrar.notifySelectableChange(textController.getState().getSelectableId());
                        }
                    }
                }
                this.this$0.getState().setLayoutResult(textLayoutResultM1036layoutNN6EwU);
                if (measurables.size() < textLayoutResultM1036layoutNN6EwU.getPlaceholderRects().size()) {
                    throw new IllegalStateException("Check failed.".toString());
                }
                List<Rect> placeholderRects = textLayoutResultM1036layoutNN6EwU.getPlaceholderRects();
                ArrayList arrayList = new ArrayList(placeholderRects.size());
                int size = placeholderRects.size();
                int i2 = 0;
                while (i2 < size) {
                    Rect rect = placeholderRects.get(i2);
                    if (rect != null) {
                        i = i2;
                        pair = new Pair(measurables.get(i2).mo3396measureBRTryo0(ConstraintsKt.Constraints$default(0, (int) Math.floor(rect.getWidth()), 0, (int) Math.floor(rect.getHeight()), 5, null)), IntOffset.m4499boximpl(IntOffsetKt.IntOffset(MathKt.roundToInt(rect.getLeft()), MathKt.roundToInt(rect.getTop()))));
                    } else {
                        i = i2;
                        pair = null;
                    }
                    if (pair != null) {
                        arrayList.add(pair);
                    }
                    i2 = i + 1;
                }
                final ArrayList arrayList2 = arrayList;
                return measure.layout(IntSize.m4550getWidthimpl(textLayoutResultM1036layoutNN6EwU.getSize()), IntSize.m4549getHeightimpl(textLayoutResultM1036layoutNN6EwU.getSize()), MapsKt.mapOf(TuplesKt.to(AlignmentLineKt.getFirstBaseline(), Integer.valueOf(MathKt.roundToInt(textLayoutResultM1036layoutNN6EwU.getFirstBaseline()))), TuplesKt.to(AlignmentLineKt.getLastBaseline(), Integer.valueOf(MathKt.roundToInt(textLayoutResultM1036layoutNN6EwU.getLastBaseline())))), new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.foundation.text.TextController$measurePolicy$1$measure$2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
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
                        List<Pair<Placeable, IntOffset>> list = arrayList2;
                        int size2 = list.size();
                        for (int i3 = 0; i3 < size2; i3++) {
                            Pair<Placeable, IntOffset> pair2 = list.get(i3);
                            Placeable.PlacementScope.m3444place70tqf50$default(layout, pair2.component1(), pair2.component2().getPackedValue(), 0.0f, 2, null);
                        }
                    }
                });
            }

            @Override // androidx.compose.ui.layout.MeasurePolicy
            public int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> measurables, int i) {
                Intrinsics.checkNotNullParameter(intrinsicMeasureScope, "<this>");
                Intrinsics.checkNotNullParameter(measurables, "measurables");
                this.this$0.getState().getTextDelegate().layoutIntrinsics(intrinsicMeasureScope.getLayoutDirection());
                return this.this$0.getState().getTextDelegate().getMinIntrinsicWidth();
            }

            @Override // androidx.compose.ui.layout.MeasurePolicy
            public int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> measurables, int i) {
                Intrinsics.checkNotNullParameter(intrinsicMeasureScope, "<this>");
                Intrinsics.checkNotNullParameter(measurables, "measurables");
                return IntSize.m4549getHeightimpl(TextDelegate.m1033layoutNN6EwU$default(this.this$0.getState().getTextDelegate(), ConstraintsKt.Constraints(0, i, 0, Integer.MAX_VALUE), intrinsicMeasureScope.getLayoutDirection(), null, 4, null).getSize());
            }

            @Override // androidx.compose.ui.layout.MeasurePolicy
            public int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> measurables, int i) {
                Intrinsics.checkNotNullParameter(intrinsicMeasureScope, "<this>");
                Intrinsics.checkNotNullParameter(measurables, "measurables");
                this.this$0.getState().getTextDelegate().layoutIntrinsics(intrinsicMeasureScope.getLayoutDirection());
                return this.this$0.getState().getTextDelegate().getMaxIntrinsicWidth();
            }

            @Override // androidx.compose.ui.layout.MeasurePolicy
            public int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> measurables, int i) {
                Intrinsics.checkNotNullParameter(intrinsicMeasureScope, "<this>");
                Intrinsics.checkNotNullParameter(measurables, "measurables");
                return IntSize.m4549getHeightimpl(TextDelegate.m1033layoutNN6EwU$default(this.this$0.getState().getTextDelegate(), ConstraintsKt.Constraints(0, i, 0, Integer.MAX_VALUE), intrinsicMeasureScope.getLayoutDirection(), null, 4, null).getSize());
            }
        };
        this.coreModifiers = OnGloballyPositionedModifierKt.onGloballyPositioned(drawTextAndSelectionBehind(Modifier.INSTANCE), new Function1<LayoutCoordinates, Unit>() { // from class: androidx.compose.foundation.text.TextController$coreModifiers$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(LayoutCoordinates layoutCoordinates) {
                invoke2(layoutCoordinates);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(LayoutCoordinates it) {
                SelectionRegistrar selectionRegistrar;
                Intrinsics.checkNotNullParameter(it, "it");
                this.this$0.getState().setLayoutCoordinates(it);
                if (SelectionRegistrarKt.hasSelection(this.this$0.selectionRegistrar, this.this$0.getState().getSelectableId())) {
                    long jPositionInWindow = LayoutCoordinatesKt.positionInWindow(it);
                    if (!Offset.m1636equalsimpl0(jPositionInWindow, this.this$0.getState().getPreviousGlobalPosition()) && (selectionRegistrar = this.this$0.selectionRegistrar) != null) {
                        selectionRegistrar.notifyPositionChange(this.this$0.getState().getSelectableId());
                    }
                    this.this$0.getState().m1070setPreviousGlobalPositionk4lQ0M(jPositionInWindow);
                }
            }
        });
        this.semanticsModifier = createSemanticsModifierFor(state.getTextDelegate().getText());
        this.selectionModifiers = Modifier.INSTANCE;
    }

    public final TextDragObserver getLongPressDragObserver() {
        TextDragObserver textDragObserver = this.longPressDragObserver;
        if (textDragObserver != null) {
            return textDragObserver;
        }
        Intrinsics.throwUninitializedPropertyAccessException("longPressDragObserver");
        return null;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [androidx.compose.foundation.text.TextController$update$mouseSelectionObserver$1, java.lang.Object] */
    public final void update(final SelectionRegistrar selectionRegistrar) {
        Modifier.Companion companionPointerHoverIcon$default;
        this.selectionRegistrar = selectionRegistrar;
        if (selectionRegistrar != null) {
            if (TouchMode_androidKt.isInTouchMode()) {
                setLongPressDragObserver(new TextDragObserver() { // from class: androidx.compose.foundation.text.TextController.update.1
                    private long lastPosition = Offset.INSTANCE.m1655getZeroF1C5BW0();
                    private long dragTotalDistance = Offset.INSTANCE.m1655getZeroF1C5BW0();

                    public final long getDragTotalDistance() {
                        return this.dragTotalDistance;
                    }

                    public final long getLastPosition() {
                        return this.lastPosition;
                    }

                    @Override // androidx.compose.foundation.text.TextDragObserver
                    /* renamed from: onDown-k-4lQ0M, reason: not valid java name */
                    public void mo1026onDownk4lQ0M(long point) {
                    }

                    @Override // androidx.compose.foundation.text.TextDragObserver
                    public void onUp() {
                    }

                    public final void setDragTotalDistance(long j) {
                        this.dragTotalDistance = j;
                    }

                    public final void setLastPosition(long j) {
                        this.lastPosition = j;
                    }

                    @Override // androidx.compose.foundation.text.TextDragObserver
                    /* renamed from: onStart-k-4lQ0M, reason: not valid java name */
                    public void mo1028onStartk4lQ0M(long startPoint) {
                        LayoutCoordinates layoutCoordinates = TextController.this.getState().getLayoutCoordinates();
                        if (layoutCoordinates != null) {
                            TextController textController = TextController.this;
                            SelectionRegistrar selectionRegistrar2 = selectionRegistrar;
                            if (!layoutCoordinates.isAttached()) {
                                return;
                            }
                            if (textController.m1025outOfBoundary0a9Yr6o(startPoint, startPoint)) {
                                selectionRegistrar2.notifySelectionUpdateSelectAll(textController.getState().getSelectableId());
                            } else {
                                selectionRegistrar2.mo1138notifySelectionUpdateStartd4ec7I(layoutCoordinates, startPoint, SelectionAdjustment.INSTANCE.getWord());
                            }
                            this.lastPosition = startPoint;
                        }
                        if (SelectionRegistrarKt.hasSelection(selectionRegistrar, TextController.this.getState().getSelectableId())) {
                            this.dragTotalDistance = Offset.INSTANCE.m1655getZeroF1C5BW0();
                        }
                    }

                    @Override // androidx.compose.foundation.text.TextDragObserver
                    /* renamed from: onDrag-k-4lQ0M, reason: not valid java name */
                    public void mo1027onDragk4lQ0M(long delta) {
                        LayoutCoordinates layoutCoordinates = TextController.this.getState().getLayoutCoordinates();
                        if (layoutCoordinates != null) {
                            SelectionRegistrar selectionRegistrar2 = selectionRegistrar;
                            TextController textController = TextController.this;
                            if (layoutCoordinates.isAttached() && SelectionRegistrarKt.hasSelection(selectionRegistrar2, textController.getState().getSelectableId())) {
                                long jM1644plusMKHz9U = Offset.m1644plusMKHz9U(this.dragTotalDistance, delta);
                                this.dragTotalDistance = jM1644plusMKHz9U;
                                long jM1644plusMKHz9U2 = Offset.m1644plusMKHz9U(this.lastPosition, jM1644plusMKHz9U);
                                if (textController.m1025outOfBoundary0a9Yr6o(this.lastPosition, jM1644plusMKHz9U2) || !selectionRegistrar2.mo1137notifySelectionUpdate5iVPX68(layoutCoordinates, jM1644plusMKHz9U2, this.lastPosition, false, SelectionAdjustment.INSTANCE.getCharacterWithWordAccelerate())) {
                                    return;
                                }
                                this.lastPosition = jM1644plusMKHz9U2;
                                this.dragTotalDistance = Offset.INSTANCE.m1655getZeroF1C5BW0();
                            }
                        }
                    }

                    @Override // androidx.compose.foundation.text.TextDragObserver
                    public void onStop() {
                        if (SelectionRegistrarKt.hasSelection(selectionRegistrar, TextController.this.getState().getSelectableId())) {
                            selectionRegistrar.notifySelectionUpdateEnd();
                        }
                    }

                    @Override // androidx.compose.foundation.text.TextDragObserver
                    public void onCancel() {
                        if (SelectionRegistrarKt.hasSelection(selectionRegistrar, TextController.this.getState().getSelectableId())) {
                            selectionRegistrar.notifySelectionUpdateEnd();
                        }
                    }
                });
                companionPointerHoverIcon$default = SuspendingPointerInputFilterKt.pointerInput(Modifier.INSTANCE, getLongPressDragObserver(), new AnonymousClass2(null));
            } else {
                ?? r0 = new MouseSelectionObserver() { // from class: androidx.compose.foundation.text.TextController$update$mouseSelectionObserver$1
                    private long lastPosition = Offset.INSTANCE.m1655getZeroF1C5BW0();

                    public final long getLastPosition() {
                        return this.lastPosition;
                    }

                    public final void setLastPosition(long j) {
                        this.lastPosition = j;
                    }

                    @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
                    /* renamed from: onExtend-k-4lQ0M, reason: not valid java name */
                    public boolean mo1030onExtendk4lQ0M(long downPosition) {
                        LayoutCoordinates layoutCoordinates = this.this$0.getState().getLayoutCoordinates();
                        if (layoutCoordinates == null) {
                            return false;
                        }
                        SelectionRegistrar selectionRegistrar2 = selectionRegistrar;
                        TextController textController = this.this$0;
                        if (!layoutCoordinates.isAttached()) {
                            return false;
                        }
                        if (selectionRegistrar2.mo1137notifySelectionUpdate5iVPX68(layoutCoordinates, downPosition, this.lastPosition, false, SelectionAdjustment.INSTANCE.getNone())) {
                            this.lastPosition = downPosition;
                        }
                        return SelectionRegistrarKt.hasSelection(selectionRegistrar2, textController.getState().getSelectableId());
                    }

                    @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
                    /* renamed from: onExtendDrag-k-4lQ0M, reason: not valid java name */
                    public boolean mo1031onExtendDragk4lQ0M(long dragPosition) {
                        LayoutCoordinates layoutCoordinates = this.this$0.getState().getLayoutCoordinates();
                        if (layoutCoordinates == null) {
                            return true;
                        }
                        SelectionRegistrar selectionRegistrar2 = selectionRegistrar;
                        TextController textController = this.this$0;
                        if (!layoutCoordinates.isAttached() || !SelectionRegistrarKt.hasSelection(selectionRegistrar2, textController.getState().getSelectableId())) {
                            return false;
                        }
                        if (!selectionRegistrar2.mo1137notifySelectionUpdate5iVPX68(layoutCoordinates, dragPosition, this.lastPosition, false, SelectionAdjustment.INSTANCE.getNone())) {
                            return true;
                        }
                        this.lastPosition = dragPosition;
                        return true;
                    }

                    @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
                    /* renamed from: onStart-3MmeM6k, reason: not valid java name */
                    public boolean mo1032onStart3MmeM6k(long downPosition, SelectionAdjustment adjustment) {
                        Intrinsics.checkNotNullParameter(adjustment, "adjustment");
                        LayoutCoordinates layoutCoordinates = this.this$0.getState().getLayoutCoordinates();
                        if (layoutCoordinates == null) {
                            return false;
                        }
                        SelectionRegistrar selectionRegistrar2 = selectionRegistrar;
                        TextController textController = this.this$0;
                        if (!layoutCoordinates.isAttached()) {
                            return false;
                        }
                        selectionRegistrar2.mo1138notifySelectionUpdateStartd4ec7I(layoutCoordinates, downPosition, adjustment);
                        this.lastPosition = downPosition;
                        return SelectionRegistrarKt.hasSelection(selectionRegistrar2, textController.getState().getSelectableId());
                    }

                    @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
                    /* renamed from: onDrag-3MmeM6k, reason: not valid java name */
                    public boolean mo1029onDrag3MmeM6k(long dragPosition, SelectionAdjustment adjustment) {
                        Intrinsics.checkNotNullParameter(adjustment, "adjustment");
                        LayoutCoordinates layoutCoordinates = this.this$0.getState().getLayoutCoordinates();
                        if (layoutCoordinates != null) {
                            SelectionRegistrar selectionRegistrar2 = selectionRegistrar;
                            TextController textController = this.this$0;
                            if (!layoutCoordinates.isAttached() || !SelectionRegistrarKt.hasSelection(selectionRegistrar2, textController.getState().getSelectableId())) {
                                return false;
                            }
                            if (selectionRegistrar2.mo1137notifySelectionUpdate5iVPX68(layoutCoordinates, dragPosition, this.lastPosition, false, adjustment)) {
                                this.lastPosition = dragPosition;
                            }
                        }
                        return true;
                    }
                };
                companionPointerHoverIcon$default = PointerIconKt.pointerHoverIcon$default(SuspendingPointerInputFilterKt.pointerInput(Modifier.INSTANCE, (Object) r0, new AnonymousClass3(r0, null)), TextPointerIcon_androidKt.getTextPointerIcon(), false, 2, null);
            }
        } else {
            companionPointerHoverIcon$default = Modifier.INSTANCE;
        }
        this.selectionModifiers = companionPointerHoverIcon$default;
    }

    /* compiled from: CoreText.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/PointerInputScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.TextController$update$2", f = "CoreText.kt", i = {}, l = {192}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: androidx.compose.foundation.text.TextController$update$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<PointerInputScope, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = TextController.this.new AnonymousClass2(continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(pointerInputScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (LongPressTextDragObserverKt.detectDragGesturesAfterLongPressWithObserver((PointerInputScope) this.L$0, TextController.this.getLongPressDragObserver(), this) == coroutine_suspended) {
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

    /* compiled from: CoreText.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/PointerInputScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.TextController$update$3", f = "CoreText.kt", i = {}, l = {283}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: androidx.compose.foundation.text.TextController$update$3, reason: invalid class name */
    static final class AnonymousClass3 extends SuspendLambda implements Function2<PointerInputScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ TextController$update$mouseSelectionObserver$1 $mouseSelectionObserver;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(TextController$update$mouseSelectionObserver$1 textController$update$mouseSelectionObserver$1, Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
            this.$mouseSelectionObserver = textController$update$mouseSelectionObserver$1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass3 anonymousClass3 = new AnonymousClass3(this.$mouseSelectionObserver, continuation);
            anonymousClass3.L$0 = obj;
            return anonymousClass3;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass3) create(pointerInputScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (TextSelectionMouseDetectorKt.mouseSelectionDetector((PointerInputScope) this.L$0, this.$mouseSelectionObserver, this) == coroutine_suspended) {
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

    public final void setTextDelegate(TextDelegate textDelegate) {
        Intrinsics.checkNotNullParameter(textDelegate, "textDelegate");
        if (this.state.getTextDelegate() == textDelegate) {
            return;
        }
        this.state.setTextDelegate(textDelegate);
        this.semanticsModifier = createSemanticsModifierFor(this.state.getTextDelegate().getText());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: outOfBoundary-0a9Yr6o, reason: not valid java name */
    public final boolean m1025outOfBoundary0a9Yr6o(long start, long end) {
        TextLayoutResult layoutResult = this.state.getLayoutResult();
        if (layoutResult == null) {
            return false;
        }
        int length = layoutResult.getLayoutInput().getText().getText().length();
        int iM3877getOffsetForPositionk4lQ0M = layoutResult.m3877getOffsetForPositionk4lQ0M(start);
        int iM3877getOffsetForPositionk4lQ0M2 = layoutResult.m3877getOffsetForPositionk4lQ0M(end);
        int i = length - 1;
        return (iM3877getOffsetForPositionk4lQ0M >= i && iM3877getOffsetForPositionk4lQ0M2 >= i) || (iM3877getOffsetForPositionk4lQ0M < 0 && iM3877getOffsetForPositionk4lQ0M2 < 0);
    }

    private final Modifier drawTextAndSelectionBehind(Modifier modifier) {
        return DrawModifierKt.drawBehind(GraphicsLayerModifierKt.m2023graphicsLayerAp8cVGQ$default(modifier, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0L, null, false, null, 0L, 0L, 0, 131071, null), new Function1<DrawScope, Unit>() { // from class: androidx.compose.foundation.text.TextController.drawTextAndSelectionBehind.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope) {
                invoke2(drawScope);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DrawScope drawBehind) {
                int offset;
                int offset2;
                Map<Long, Selection> subselections;
                Intrinsics.checkNotNullParameter(drawBehind, "$this$drawBehind");
                TextLayoutResult layoutResult = TextController.this.getState().getLayoutResult();
                if (layoutResult != null) {
                    TextController textController = TextController.this;
                    textController.getState().getDrawScopeInvalidation();
                    SelectionRegistrar selectionRegistrar = textController.selectionRegistrar;
                    Selection selection = (selectionRegistrar == null || (subselections = selectionRegistrar.getSubselections()) == null) ? null : subselections.get(Long.valueOf(textController.getState().getSelectableId()));
                    Selectable selectable = textController.getState().getSelectable();
                    int lastVisibleOffset = selectable != null ? selectable.getLastVisibleOffset() : 0;
                    if (selection != null) {
                        if (!selection.getHandlesCrossed()) {
                            offset = selection.getStart().getOffset();
                        } else {
                            offset = selection.getEnd().getOffset();
                        }
                        int iCoerceIn = RangesKt.coerceIn(offset, 0, lastVisibleOffset);
                        if (!selection.getHandlesCrossed()) {
                            offset2 = selection.getEnd().getOffset();
                        } else {
                            offset2 = selection.getStart().getOffset();
                        }
                        int iCoerceIn2 = RangesKt.coerceIn(offset2, 0, lastVisibleOffset);
                        if (iCoerceIn != iCoerceIn2) {
                            Path pathForRange = layoutResult.getMultiParagraph().getPathForRange(iCoerceIn, iCoerceIn2);
                            if (TextOverflow.m4302equalsimpl0(layoutResult.getLayoutInput().getOverflow(), TextOverflow.INSTANCE.m4311getVisiblegIe3tQ8())) {
                                DrawScope.m2408drawPathLG529CI$default(drawBehind, pathForRange, textController.getState().getSelectionBackgroundColor(), 0.0f, null, null, 0, 60, null);
                            } else {
                                float fM1708getWidthimpl = Size.m1708getWidthimpl(drawBehind.mo2417getSizeNHjbRc());
                                float fM1705getHeightimpl = Size.m1705getHeightimpl(drawBehind.mo2417getSizeNHjbRc());
                                int iM1866getIntersectrtfAjoo = ClipOp.INSTANCE.m1866getIntersectrtfAjoo();
                                DrawContext drawContext = drawBehind.getDrawContext();
                                long jMo2342getSizeNHjbRc = drawContext.mo2342getSizeNHjbRc();
                                drawContext.getCanvas().save();
                                drawContext.getTransform().mo2345clipRectN_I0leg(0.0f, 0.0f, fM1708getWidthimpl, fM1705getHeightimpl, iM1866getIntersectrtfAjoo);
                                DrawScope.m2408drawPathLG529CI$default(drawBehind, pathForRange, textController.getState().getSelectionBackgroundColor(), 0.0f, null, null, 0, 60, null);
                                drawContext.getCanvas().restore();
                                drawContext.mo2343setSizeuvyYCjk(jMo2342getSizeNHjbRc);
                            }
                        }
                    }
                    TextDelegate.INSTANCE.paint(drawBehind.getDrawContext().getCanvas(), layoutResult);
                }
            }
        });
    }

    private final Modifier createSemanticsModifierFor(final AnnotatedString text) {
        return SemanticsModifierKt.semantics$default(Modifier.INSTANCE, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.foundation.text.TextController.createSemanticsModifierFor.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                SemanticsPropertiesKt.setText(semantics, text);
                final TextController textController = this;
                SemanticsPropertiesKt.getTextLayoutResult$default(semantics, null, new Function1<List<TextLayoutResult>, Boolean>() { // from class: androidx.compose.foundation.text.TextController.createSemanticsModifierFor.1.1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Boolean invoke(List<TextLayoutResult> it) {
                        boolean z;
                        Intrinsics.checkNotNullParameter(it, "it");
                        if (textController.getState().getLayoutResult() != null) {
                            TextLayoutResult layoutResult = textController.getState().getLayoutResult();
                            Intrinsics.checkNotNull(layoutResult);
                            it.add(layoutResult);
                            z = true;
                        } else {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    }
                }, 1, null);
            }
        }, 1, null);
    }

    public final Modifier getModifiers() {
        return HeightInLinesModifierKt.heightInLines$default(this.coreModifiers, this.state.getTextDelegate().getStyle(), this.state.getTextDelegate().getMinLines(), 0, 4, null).then(this.semanticsModifier).then(this.selectionModifiers);
    }

    @Override // androidx.compose.runtime.RememberObserver
    public void onRemembered() {
        SelectionRegistrar selectionRegistrar = this.selectionRegistrar;
        if (selectionRegistrar != null) {
            this.state.setSelectable(selectionRegistrar.subscribe(new MultiWidgetSelectionDelegate(this.state.getSelectableId(), new Function0<LayoutCoordinates>() { // from class: androidx.compose.foundation.text.TextController$onRemembered$1$1
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final LayoutCoordinates invoke() {
                    return this.this$0.getState().getLayoutCoordinates();
                }
            }, new Function0<TextLayoutResult>() { // from class: androidx.compose.foundation.text.TextController$onRemembered$1$2
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final TextLayoutResult invoke() {
                    return this.this$0.getState().getLayoutResult();
                }
            })));
        }
    }

    @Override // androidx.compose.runtime.RememberObserver
    public void onForgotten() {
        SelectionRegistrar selectionRegistrar;
        Selectable selectable = this.state.getSelectable();
        if (selectable == null || (selectionRegistrar = this.selectionRegistrar) == null) {
            return;
        }
        selectionRegistrar.unsubscribe(selectable);
    }

    @Override // androidx.compose.runtime.RememberObserver
    public void onAbandoned() {
        SelectionRegistrar selectionRegistrar;
        Selectable selectable = this.state.getSelectable();
        if (selectable == null || (selectionRegistrar = this.selectionRegistrar) == null) {
            return;
        }
        selectionRegistrar.unsubscribe(selectable);
    }
}
