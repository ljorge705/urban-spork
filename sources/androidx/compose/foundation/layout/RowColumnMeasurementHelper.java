package androidx.compose.foundation.layout;

import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import com.facebook.react.uimanager.ViewProps;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.IntIterator;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;
import org.bouncycastle.crypto.CryptoServicesPermission;

/* compiled from: RowColumnMeasurementHelper.kt */
@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u0015\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001Br\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012*\u0010\u0004\u001a&\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\n0\u0005\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012\u0012\u000e\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00160\u0015ø\u0001\u0000¢\u0006\u0002\u0010\u0017J2\u0010+\u001a\u00020\u00062\u0006\u0010,\u001a\u00020\u00162\b\u0010-\u001a\u0004\u0018\u00010)2\u0006\u0010.\u001a\u00020\u00062\u0006\u0010/\u001a\u00020\b2\u0006\u00100\u001a\u00020\u0006H\u0002J(\u00101\u001a\u00020\u00072\u0006\u00102\u001a\u00020\u00062\u0006\u00103\u001a\u00020\u00072\u0006\u00101\u001a\u00020\u00072\u0006\u00104\u001a\u000205H\u0002J3\u00106\u001a\u0002072\u0006\u00104\u001a\u0002052\u0006\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020\u00062\u0006\u0010;\u001a\u00020\u0006ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b<\u0010=J&\u0010>\u001a\u00020\n2\u0006\u0010?\u001a\u00020@2\u0006\u0010A\u001a\u0002072\u0006\u0010B\u001a\u00020\u00062\u0006\u0010/\u001a\u00020\bJ\n\u0010\r\u001a\u00020\u0006*\u00020\u0016J\n\u0010C\u001a\u00020\u0006*\u00020\u0016R5\u0010\u0004\u001a&\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\n0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u001c\u0010\u000b\u001a\u00020\fø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u001c\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u001b\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00160\u0015¢\u0006\n\n\u0002\u0010'\u001a\u0004\b%\u0010&R\u0018\u0010(\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010)0\u0015X\u0082\u0004¢\u0006\u0004\n\u0002\u0010*\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006D"}, d2 = {"Landroidx/compose/foundation/layout/RowColumnMeasurementHelper;", "", "orientation", "Landroidx/compose/foundation/layout/LayoutOrientation;", "arrangement", "Lkotlin/Function5;", "", "", "Landroidx/compose/ui/unit/LayoutDirection;", "Landroidx/compose/ui/unit/Density;", "", "arrangementSpacing", "Landroidx/compose/ui/unit/Dp;", "crossAxisSize", "Landroidx/compose/foundation/layout/SizeMode;", "crossAxisAlignment", "Landroidx/compose/foundation/layout/CrossAxisAlignment;", "measurables", "", "Landroidx/compose/ui/layout/Measurable;", "placeables", "", "Landroidx/compose/ui/layout/Placeable;", "(Landroidx/compose/foundation/layout/LayoutOrientation;Lkotlin/jvm/functions/Function5;FLandroidx/compose/foundation/layout/SizeMode;Landroidx/compose/foundation/layout/CrossAxisAlignment;Ljava/util/List;[Landroidx/compose/ui/layout/Placeable;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getArrangement", "()Lkotlin/jvm/functions/Function5;", "getArrangementSpacing-D9Ej5fM", "()F", "F", "getCrossAxisAlignment", "()Landroidx/compose/foundation/layout/CrossAxisAlignment;", "getCrossAxisSize", "()Landroidx/compose/foundation/layout/SizeMode;", "getMeasurables", "()Ljava/util/List;", "getOrientation", "()Landroidx/compose/foundation/layout/LayoutOrientation;", "getPlaceables", "()[Landroidx/compose/ui/layout/Placeable;", "[Landroidx/compose/ui/layout/Placeable;", "rowColumnParentData", "Landroidx/compose/foundation/layout/RowColumnParentData;", "[Landroidx/compose/foundation/layout/RowColumnParentData;", "getCrossAxisPosition", "placeable", "parentData", "crossAxisLayoutSize", ViewProps.LAYOUT_DIRECTION, "beforeCrossAxisAlignmentLine", "mainAxisPositions", "mainAxisLayoutSize", "childrenMainAxisSize", "measureScope", "Landroidx/compose/ui/layout/MeasureScope;", "measureWithoutPlacing", "Landroidx/compose/foundation/layout/RowColumnMeasureHelperResult;", CryptoServicesPermission.CONSTRAINTS, "Landroidx/compose/ui/unit/Constraints;", "startIndex", "endIndex", "measureWithoutPlacing-_EkL_-Y", "(Landroidx/compose/ui/layout/MeasureScope;JII)Landroidx/compose/foundation/layout/RowColumnMeasureHelperResult;", "placeHelper", "placeableScope", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "measureResult", "crossAxisOffset", "mainAxisSize", "foundation-layout_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class RowColumnMeasurementHelper {
    private final Function5<Integer, int[], LayoutDirection, Density, int[], Unit> arrangement;
    private final float arrangementSpacing;
    private final CrossAxisAlignment crossAxisAlignment;
    private final SizeMode crossAxisSize;
    private final List<Measurable> measurables;
    private final LayoutOrientation orientation;
    private final Placeable[] placeables;
    private final RowColumnParentData[] rowColumnParentData;

    public /* synthetic */ RowColumnMeasurementHelper(LayoutOrientation layoutOrientation, Function5 function5, float f, SizeMode sizeMode, CrossAxisAlignment crossAxisAlignment, List list, Placeable[] placeableArr, DefaultConstructorMarker defaultConstructorMarker) {
        this(layoutOrientation, function5, f, sizeMode, crossAxisAlignment, list, placeableArr);
    }

    public final Function5<Integer, int[], LayoutDirection, Density, int[], Unit> getArrangement() {
        return this.arrangement;
    }

    /* renamed from: getArrangementSpacing-D9Ej5fM, reason: not valid java name and from getter */
    public final float getArrangementSpacing() {
        return this.arrangementSpacing;
    }

    public final CrossAxisAlignment getCrossAxisAlignment() {
        return this.crossAxisAlignment;
    }

    public final SizeMode getCrossAxisSize() {
        return this.crossAxisSize;
    }

    public final List<Measurable> getMeasurables() {
        return this.measurables;
    }

    public final LayoutOrientation getOrientation() {
        return this.orientation;
    }

    public final Placeable[] getPlaceables() {
        return this.placeables;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private RowColumnMeasurementHelper(LayoutOrientation layoutOrientation, Function5<? super Integer, ? super int[], ? super LayoutDirection, ? super Density, ? super int[], Unit> function5, float f, SizeMode sizeMode, CrossAxisAlignment crossAxisAlignment, List<? extends Measurable> list, Placeable[] placeableArr) {
        this.orientation = layoutOrientation;
        this.arrangement = function5;
        this.arrangementSpacing = f;
        this.crossAxisSize = sizeMode;
        this.crossAxisAlignment = crossAxisAlignment;
        this.measurables = list;
        this.placeables = placeableArr;
        int size = list.size();
        RowColumnParentData[] rowColumnParentDataArr = new RowColumnParentData[size];
        for (int i = 0; i < size; i++) {
            rowColumnParentDataArr[i] = RowColumnImplKt.getRowColumnParentData(this.measurables.get(i));
        }
        this.rowColumnParentData = rowColumnParentDataArr;
    }

    public final int mainAxisSize(Placeable placeable) {
        Intrinsics.checkNotNullParameter(placeable, "<this>");
        return this.orientation == LayoutOrientation.Horizontal ? placeable.getWidth() : placeable.getHeight();
    }

    public final int crossAxisSize(Placeable placeable) {
        Intrinsics.checkNotNullParameter(placeable, "<this>");
        return this.orientation == LayoutOrientation.Horizontal ? placeable.getHeight() : placeable.getWidth();
    }

    /* renamed from: measureWithoutPlacing-_EkL_-Y, reason: not valid java name */
    public final RowColumnMeasureHelperResult m713measureWithoutPlacing_EkL_Y(MeasureScope measureScope, long constraints, int startIndex, int endIndex) {
        int i;
        int mainAxisMin;
        int iMax;
        int iCoerceAtMost;
        float f;
        int i2;
        int iMax2;
        int i3;
        int iMax3;
        int i4;
        int i5;
        int i6;
        Intrinsics.checkNotNullParameter(measureScope, "measureScope");
        OrientationIndependentConstraints orientationIndependentConstraints = new OrientationIndependentConstraints(constraints, this.orientation, null);
        int i7 = measureScope.mo571roundToPx0680j_4(this.arrangementSpacing);
        int i8 = endIndex - startIndex;
        float f2 = 0.0f;
        int i9 = startIndex;
        float f3 = 0.0f;
        int i10 = 0;
        int iMax4 = 0;
        int i11 = 0;
        int iMainAxisSize = 0;
        boolean z = false;
        while (true) {
            i = Integer.MAX_VALUE;
            if (i9 >= endIndex) {
                break;
            }
            Measurable measurable = this.measurables.get(i9);
            RowColumnParentData rowColumnParentData = this.rowColumnParentData[i9];
            float weight = RowColumnImplKt.getWeight(rowColumnParentData);
            if (weight > 0.0f) {
                f3 += weight;
                i11++;
                i6 = i9;
            } else {
                int mainAxisMax = orientationIndependentConstraints.getMainAxisMax();
                Placeable placeableMo3396measureBRTryo0 = this.placeables[i9];
                if (placeableMo3396measureBRTryo0 == null) {
                    i4 = mainAxisMax;
                    i5 = iMax4;
                    i6 = i9;
                    placeableMo3396measureBRTryo0 = measurable.mo3396measureBRTryo0(OrientationIndependentConstraints.copy$default(orientationIndependentConstraints, 0, mainAxisMax == Integer.MAX_VALUE ? Integer.MAX_VALUE : mainAxisMax - iMainAxisSize, 0, 0, 8, null).m682toBoxConstraintsOenEA2s(this.orientation));
                } else {
                    i4 = mainAxisMax;
                    i5 = iMax4;
                    i6 = i9;
                }
                int iMin = Math.min(i7, (i4 - iMainAxisSize) - mainAxisSize(placeableMo3396measureBRTryo0));
                iMainAxisSize += mainAxisSize(placeableMo3396measureBRTryo0) + iMin;
                iMax4 = Math.max(i5, crossAxisSize(placeableMo3396measureBRTryo0));
                z = z || RowColumnImplKt.isRelative(rowColumnParentData);
                this.placeables[i6] = placeableMo3396measureBRTryo0;
                i10 = iMin;
            }
            i9 = i6 + 1;
        }
        int i12 = iMax4;
        if (i11 == 0) {
            iMainAxisSize -= i10;
            iMax = i12;
            iCoerceAtMost = 0;
        } else {
            if (f3 > 0.0f && orientationIndependentConstraints.getMainAxisMax() != Integer.MAX_VALUE) {
                mainAxisMin = orientationIndependentConstraints.getMainAxisMax();
            } else {
                mainAxisMin = orientationIndependentConstraints.getMainAxisMin();
            }
            int i13 = i7 * (i11 - 1);
            int i14 = (mainAxisMin - iMainAxisSize) - i13;
            float f4 = f3 > 0.0f ? i14 / f3 : 0.0f;
            Iterator<Integer> it = RangesKt.until(startIndex, endIndex).iterator();
            int iRoundToInt = 0;
            while (it.hasNext()) {
                iRoundToInt += MathKt.roundToInt(RowColumnImplKt.getWeight(this.rowColumnParentData[((IntIterator) it).nextInt()]) * f4);
            }
            int i15 = i14 - iRoundToInt;
            int i16 = startIndex;
            iMax = i12;
            int iMainAxisSize2 = 0;
            while (i16 < endIndex) {
                if (this.placeables[i16] == null) {
                    Measurable measurable2 = this.measurables.get(i16);
                    RowColumnParentData rowColumnParentData2 = this.rowColumnParentData[i16];
                    float weight2 = RowColumnImplKt.getWeight(rowColumnParentData2);
                    if (weight2 <= f2) {
                        throw new IllegalStateException("All weights <= 0 should have placeables".toString());
                    }
                    int sign = MathKt.getSign(i15);
                    int i17 = i15 - sign;
                    int iMax5 = Math.max(0, MathKt.roundToInt(weight2 * f4) + sign);
                    if (!RowColumnImplKt.getFill(rowColumnParentData2) || iMax5 == i) {
                        f = f4;
                        i2 = 0;
                    } else {
                        f = f4;
                        i2 = iMax5;
                    }
                    Placeable placeableMo3396measureBRTryo02 = measurable2.mo3396measureBRTryo0(new OrientationIndependentConstraints(i2, iMax5, 0, orientationIndependentConstraints.getCrossAxisMax()).m682toBoxConstraintsOenEA2s(this.orientation));
                    iMainAxisSize2 += mainAxisSize(placeableMo3396measureBRTryo02);
                    iMax = Math.max(iMax, crossAxisSize(placeableMo3396measureBRTryo02));
                    z = z || RowColumnImplKt.isRelative(rowColumnParentData2);
                    this.placeables[i16] = placeableMo3396measureBRTryo02;
                    i15 = i17;
                } else {
                    f = f4;
                }
                i16++;
                f4 = f;
                i = Integer.MAX_VALUE;
                f2 = 0.0f;
            }
            iCoerceAtMost = RangesKt.coerceAtMost(iMainAxisSize2 + i13, orientationIndependentConstraints.getMainAxisMax() - iMainAxisSize);
        }
        if (z) {
            int iMax6 = 0;
            iMax2 = 0;
            for (int i18 = startIndex; i18 < endIndex; i18++) {
                Placeable placeable = this.placeables[i18];
                Intrinsics.checkNotNull(placeable);
                CrossAxisAlignment crossAxisAlignment = RowColumnImplKt.getCrossAxisAlignment(this.rowColumnParentData[i18]);
                Integer numCalculateAlignmentLinePosition$foundation_layout_release = crossAxisAlignment != null ? crossAxisAlignment.calculateAlignmentLinePosition$foundation_layout_release(placeable) : null;
                if (numCalculateAlignmentLinePosition$foundation_layout_release != null) {
                    Integer num = numCalculateAlignmentLinePosition$foundation_layout_release;
                    int iIntValue = num.intValue();
                    if (iIntValue == Integer.MIN_VALUE) {
                        iIntValue = 0;
                    }
                    iMax6 = Math.max(iMax6, iIntValue);
                    int iCrossAxisSize = crossAxisSize(placeable);
                    int iIntValue2 = num.intValue();
                    if (iIntValue2 == Integer.MIN_VALUE) {
                        iIntValue2 = crossAxisSize(placeable);
                    }
                    iMax2 = Math.max(iMax2, iCrossAxisSize - iIntValue2);
                }
            }
            i3 = iMax6;
        } else {
            iMax2 = 0;
            i3 = 0;
        }
        int iMax7 = Math.max(iMainAxisSize + iCoerceAtMost, orientationIndependentConstraints.getMainAxisMin());
        if (orientationIndependentConstraints.getCrossAxisMax() != Integer.MAX_VALUE && this.crossAxisSize == SizeMode.Expand) {
            iMax3 = orientationIndependentConstraints.getCrossAxisMax();
        } else {
            iMax3 = Math.max(iMax, Math.max(orientationIndependentConstraints.getCrossAxisMin(), iMax2 + i3));
        }
        int[] iArr = new int[i8];
        for (int i19 = 0; i19 < i8; i19++) {
            iArr[i19] = 0;
        }
        int[] iArr2 = new int[i8];
        for (int i20 = 0; i20 < i8; i20++) {
            Placeable placeable2 = this.placeables[i20 + startIndex];
            Intrinsics.checkNotNull(placeable2);
            iArr2[i20] = mainAxisSize(placeable2);
        }
        return new RowColumnMeasureHelperResult(iMax3, iMax7, startIndex, endIndex, i3, mainAxisPositions(iMax7, iArr2, iArr, measureScope));
    }

    private final int[] mainAxisPositions(int mainAxisLayoutSize, int[] childrenMainAxisSize, int[] mainAxisPositions, MeasureScope measureScope) {
        this.arrangement.invoke(Integer.valueOf(mainAxisLayoutSize), childrenMainAxisSize, measureScope.getLayoutDirection(), measureScope, mainAxisPositions);
        return mainAxisPositions;
    }

    private final int getCrossAxisPosition(Placeable placeable, RowColumnParentData parentData, int crossAxisLayoutSize, LayoutDirection layoutDirection, int beforeCrossAxisAlignmentLine) {
        CrossAxisAlignment crossAxisAlignment;
        if (parentData == null || (crossAxisAlignment = parentData.getCrossAxisAlignment()) == null) {
            crossAxisAlignment = this.crossAxisAlignment;
        }
        int iCrossAxisSize = crossAxisLayoutSize - crossAxisSize(placeable);
        if (this.orientation == LayoutOrientation.Horizontal) {
            layoutDirection = LayoutDirection.Ltr;
        }
        return crossAxisAlignment.align$foundation_layout_release(iCrossAxisSize, layoutDirection, placeable, beforeCrossAxisAlignmentLine);
    }

    public final void placeHelper(Placeable.PlacementScope placeableScope, RowColumnMeasureHelperResult measureResult, int crossAxisOffset, LayoutDirection layoutDirection) {
        Intrinsics.checkNotNullParameter(placeableScope, "placeableScope");
        Intrinsics.checkNotNullParameter(measureResult, "measureResult");
        Intrinsics.checkNotNullParameter(layoutDirection, "layoutDirection");
        int endIndex = measureResult.getEndIndex();
        for (int startIndex = measureResult.getStartIndex(); startIndex < endIndex; startIndex++) {
            Placeable placeable = this.placeables[startIndex];
            Intrinsics.checkNotNull(placeable);
            int[] mainAxisPositions = measureResult.getMainAxisPositions();
            Object parentData = this.measurables.get(startIndex).getParentData();
            int crossAxisPosition = getCrossAxisPosition(placeable, parentData instanceof RowColumnParentData ? (RowColumnParentData) parentData : null, measureResult.getCrossAxisSize(), layoutDirection, measureResult.getBeforeCrossAxisAlignmentLine()) + crossAxisOffset;
            if (this.orientation == LayoutOrientation.Horizontal) {
                Placeable.PlacementScope.place$default(placeableScope, placeable, mainAxisPositions[startIndex - measureResult.getStartIndex()], crossAxisPosition, 0.0f, 4, null);
            } else {
                Placeable.PlacementScope.place$default(placeableScope, placeable, crossAxisPosition, mainAxisPositions[startIndex - measureResult.getStartIndex()], 0.0f, 4, null);
            }
        }
    }
}
