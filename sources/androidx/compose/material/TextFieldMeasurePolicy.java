package androidx.compose.material;

import androidx.compose.ui.layout.AlignmentLineKt;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import org.bouncycastle.crypto.CryptoServicesPermission;

/* compiled from: TextField.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J8\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\b2\u0018\u0010\r\u001a\u0014\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u000eH\u0002J<\u0010\u000f\u001a\u00020\b*\u00020\u00102\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u0011\u001a\u00020\b2\u0018\u0010\r\u001a\u0014\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u000eH\u0002J\"\u0010\u0012\u001a\u00020\b*\u00020\u00102\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u0011\u001a\u00020\bH\u0016J\"\u0010\u0013\u001a\u00020\b*\u00020\u00102\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\bH\u0016J/\u0010\u0014\u001a\u00020\u0015*\u00020\u00162\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00170\n2\u0006\u0010\u0018\u001a\u00020\u0019H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001a\u0010\u001bJ\"\u0010\u001c\u001a\u00020\b*\u00020\u00102\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u0011\u001a\u00020\bH\u0016J\"\u0010\u001d\u001a\u00020\b*\u00020\u00102\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\bH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006\u001e"}, d2 = {"Landroidx/compose/material/TextFieldMeasurePolicy;", "Landroidx/compose/ui/layout/MeasurePolicy;", "singleLine", "", "animationProgress", "", "(ZF)V", "intrinsicWidth", "", "measurables", "", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "height", "intrinsicMeasurer", "Lkotlin/Function2;", "intrinsicHeight", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "width", "maxIntrinsicHeight", "maxIntrinsicWidth", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "Landroidx/compose/ui/layout/Measurable;", CryptoServicesPermission.CONSTRAINTS, "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Ljava/util/List;J)Landroidx/compose/ui/layout/MeasureResult;", "minIntrinsicHeight", "minIntrinsicWidth", "material_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
final class TextFieldMeasurePolicy implements MeasurePolicy {
    private final float animationProgress;
    private final boolean singleLine;

    public TextFieldMeasurePolicy(boolean z, float f) {
        this.singleLine = z;
        this.animationProgress = f;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* renamed from: measure-3p2s80s */
    public MeasureResult mo287measure3p2s80s(final MeasureScope receiver, List<? extends Measurable> measurables, long j) {
        Object next;
        Object next2;
        Placeable placeable;
        final Placeable placeableMo3396measureBRTryo0;
        Object next3;
        int iIntValue;
        Object next4;
        Intrinsics.checkNotNullParameter(receiver, "$receiver");
        Intrinsics.checkNotNullParameter(measurables, "measurables");
        int i = receiver.mo571roundToPx0680j_4(TextFieldImplKt.getTextFieldPadding());
        final int i2 = receiver.mo571roundToPx0680j_4(TextFieldKt.FirstBaselineOffset);
        int i3 = receiver.mo571roundToPx0680j_4(TextFieldKt.LastBaselineOffset);
        final int i4 = receiver.mo571roundToPx0680j_4(TextFieldKt.TextFieldTopPadding);
        long jM4337copyZbe2FdA$default = Constraints.m4337copyZbe2FdA$default(j, 0, 0, 0, 0, 10, null);
        List<? extends Measurable> list = measurables;
        Iterator<T> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId((Measurable) next), TextFieldImplKt.LeadingId)) {
                break;
            }
        }
        Measurable measurable = (Measurable) next;
        Placeable placeableMo3396measureBRTryo02 = measurable == null ? null : measurable.mo3396measureBRTryo0(jM4337copyZbe2FdA$default);
        int iWidthOrZero = TextFieldImplKt.widthOrZero(placeableMo3396measureBRTryo02);
        Iterator<T> it2 = list.iterator();
        while (true) {
            if (!it2.hasNext()) {
                next2 = null;
                break;
            }
            next2 = it2.next();
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId((Measurable) next2), TextFieldImplKt.TrailingId)) {
                break;
            }
        }
        Measurable measurable2 = (Measurable) next2;
        if (measurable2 == null) {
            placeable = placeableMo3396measureBRTryo02;
            placeableMo3396measureBRTryo0 = null;
        } else {
            placeable = placeableMo3396measureBRTryo02;
            placeableMo3396measureBRTryo0 = measurable2.mo3396measureBRTryo0(ConstraintsKt.m4363offsetNN6EwU$default(jM4337copyZbe2FdA$default, -iWidthOrZero, 0, 2, null));
        }
        int i5 = -i3;
        int i6 = -(iWidthOrZero + TextFieldImplKt.widthOrZero(placeableMo3396measureBRTryo0));
        long jM4362offsetNN6EwU = ConstraintsKt.m4362offsetNN6EwU(jM4337copyZbe2FdA$default, i6, i5);
        Iterator<T> it3 = list.iterator();
        while (true) {
            if (!it3.hasNext()) {
                next3 = null;
                break;
            }
            next3 = it3.next();
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId((Measurable) next3), TextFieldImplKt.LabelId)) {
                break;
            }
        }
        Measurable measurable3 = (Measurable) next3;
        Placeable placeableMo3396measureBRTryo03 = measurable3 == null ? null : measurable3.mo3396measureBRTryo0(jM4362offsetNN6EwU);
        Integer numValueOf = placeableMo3396measureBRTryo03 == null ? null : Integer.valueOf(placeableMo3396measureBRTryo03.get(AlignmentLineKt.getLastBaseline()));
        if (numValueOf == null) {
            iIntValue = 0;
        } else {
            iIntValue = numValueOf.intValue();
            if (iIntValue == Integer.MIN_VALUE) {
                iIntValue = placeableMo3396measureBRTryo03.getHeight();
            }
        }
        final int iMax = Math.max(iIntValue, i2);
        long jM4362offsetNN6EwU2 = ConstraintsKt.m4362offsetNN6EwU(Constraints.m4337copyZbe2FdA$default(j, 0, 0, 0, 0, 11, null), i6, placeableMo3396measureBRTryo03 != null ? (i5 - i4) - iMax : (-i) * 2);
        for (Measurable measurable4 : list) {
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable4), TextFieldImplKt.TextFieldId)) {
                final Placeable placeableMo3396measureBRTryo04 = measurable4.mo3396measureBRTryo0(jM4362offsetNN6EwU2);
                long jM4337copyZbe2FdA$default2 = Constraints.m4337copyZbe2FdA$default(jM4362offsetNN6EwU2, 0, 0, 0, 0, 14, null);
                Iterator<T> it4 = list.iterator();
                while (true) {
                    if (!it4.hasNext()) {
                        next4 = null;
                        break;
                    }
                    next4 = it4.next();
                    if (Intrinsics.areEqual(LayoutIdKt.getLayoutId((Measurable) next4), TextFieldImplKt.PlaceholderId)) {
                        break;
                    }
                }
                Measurable measurable5 = (Measurable) next4;
                final Placeable placeableMo3396measureBRTryo05 = measurable5 == null ? null : measurable5.mo3396measureBRTryo0(jM4337copyZbe2FdA$default2);
                final int iM1469calculateWidthVsPV1Ek = TextFieldKt.m1469calculateWidthVsPV1Ek(TextFieldImplKt.widthOrZero(placeable), TextFieldImplKt.widthOrZero(placeableMo3396measureBRTryo0), placeableMo3396measureBRTryo04.getWidth(), TextFieldImplKt.widthOrZero(placeableMo3396measureBRTryo03), TextFieldImplKt.widthOrZero(placeableMo3396measureBRTryo05), j);
                final int iM1468calculateHeightYbqEFUw = TextFieldKt.m1468calculateHeightYbqEFUw(placeableMo3396measureBRTryo04.getHeight(), placeableMo3396measureBRTryo03 != null, iMax, TextFieldImplKt.heightOrZero(placeable), TextFieldImplKt.heightOrZero(placeableMo3396measureBRTryo0), TextFieldImplKt.heightOrZero(placeableMo3396measureBRTryo05), j, receiver.getDensity());
                final Placeable placeable2 = placeableMo3396measureBRTryo03;
                final int i7 = iIntValue;
                final Placeable placeable3 = placeable;
                return MeasureScope.layout$default(receiver, iM1469calculateWidthVsPV1Ek, iM1468calculateHeightYbqEFUw, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material.TextFieldMeasurePolicy$measure$1
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
                        if (placeable2 == null) {
                            TextFieldKt.placeWithoutLabel(layout, iM1469calculateWidthVsPV1Ek, iM1468calculateHeightYbqEFUw, placeableMo3396measureBRTryo04, placeableMo3396measureBRTryo05, placeable3, placeableMo3396measureBRTryo0, this.singleLine, receiver.getDensity());
                            return;
                        }
                        int iCoerceAtLeast = RangesKt.coerceAtLeast(i2 - i7, 0);
                        TextFieldKt.placeWithLabel(layout, iM1469calculateWidthVsPV1Ek, iM1468calculateHeightYbqEFUw, placeableMo3396measureBRTryo04, placeable2, placeableMo3396measureBRTryo05, placeable3, placeableMo3396measureBRTryo0, this.singleLine, iCoerceAtLeast, i4 + iMax, this.animationProgress, receiver.getDensity());
                    }
                }, 4, null);
            }
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> measurables, int i) {
        Intrinsics.checkNotNullParameter(intrinsicMeasureScope, "<this>");
        Intrinsics.checkNotNullParameter(measurables, "measurables");
        return intrinsicHeight(intrinsicMeasureScope, measurables, i, new Function2<IntrinsicMeasurable, Integer, Integer>() { // from class: androidx.compose.material.TextFieldMeasurePolicy.maxIntrinsicHeight.1
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Integer invoke(IntrinsicMeasurable intrinsicMeasurable, Integer num) {
                return Integer.valueOf(invoke(intrinsicMeasurable, num.intValue()));
            }

            public final int invoke(IntrinsicMeasurable intrinsicMeasurable, int i2) {
                Intrinsics.checkNotNullParameter(intrinsicMeasurable, "intrinsicMeasurable");
                return intrinsicMeasurable.maxIntrinsicHeight(i2);
            }
        });
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> measurables, int i) {
        Intrinsics.checkNotNullParameter(intrinsicMeasureScope, "<this>");
        Intrinsics.checkNotNullParameter(measurables, "measurables");
        return intrinsicHeight(intrinsicMeasureScope, measurables, i, new Function2<IntrinsicMeasurable, Integer, Integer>() { // from class: androidx.compose.material.TextFieldMeasurePolicy.minIntrinsicHeight.1
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Integer invoke(IntrinsicMeasurable intrinsicMeasurable, Integer num) {
                return Integer.valueOf(invoke(intrinsicMeasurable, num.intValue()));
            }

            public final int invoke(IntrinsicMeasurable intrinsicMeasurable, int i2) {
                Intrinsics.checkNotNullParameter(intrinsicMeasurable, "intrinsicMeasurable");
                return intrinsicMeasurable.minIntrinsicHeight(i2);
            }
        });
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> measurables, int i) {
        Intrinsics.checkNotNullParameter(intrinsicMeasureScope, "<this>");
        Intrinsics.checkNotNullParameter(measurables, "measurables");
        return intrinsicWidth(measurables, i, new Function2<IntrinsicMeasurable, Integer, Integer>() { // from class: androidx.compose.material.TextFieldMeasurePolicy.maxIntrinsicWidth.1
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Integer invoke(IntrinsicMeasurable intrinsicMeasurable, Integer num) {
                return Integer.valueOf(invoke(intrinsicMeasurable, num.intValue()));
            }

            public final int invoke(IntrinsicMeasurable intrinsicMeasurable, int i2) {
                Intrinsics.checkNotNullParameter(intrinsicMeasurable, "intrinsicMeasurable");
                return intrinsicMeasurable.maxIntrinsicWidth(i2);
            }
        });
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> measurables, int i) {
        Intrinsics.checkNotNullParameter(intrinsicMeasureScope, "<this>");
        Intrinsics.checkNotNullParameter(measurables, "measurables");
        return intrinsicWidth(measurables, i, new Function2<IntrinsicMeasurable, Integer, Integer>() { // from class: androidx.compose.material.TextFieldMeasurePolicy.minIntrinsicWidth.1
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Integer invoke(IntrinsicMeasurable intrinsicMeasurable, Integer num) {
                return Integer.valueOf(invoke(intrinsicMeasurable, num.intValue()));
            }

            public final int invoke(IntrinsicMeasurable intrinsicMeasurable, int i2) {
                Intrinsics.checkNotNullParameter(intrinsicMeasurable, "intrinsicMeasurable");
                return intrinsicMeasurable.minIntrinsicWidth(i2);
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final int intrinsicWidth(List<? extends IntrinsicMeasurable> measurables, int height, Function2<? super IntrinsicMeasurable, ? super Integer, Integer> intrinsicMeasurer) {
        Object obj;
        Object next;
        Object next2;
        Object next3;
        List<? extends IntrinsicMeasurable> list = measurables;
        for (Object obj2 : list) {
            if (Intrinsics.areEqual(TextFieldKt.getLayoutId((IntrinsicMeasurable) obj2), TextFieldImplKt.TextFieldId)) {
                int iIntValue = intrinsicMeasurer.invoke(obj2, Integer.valueOf(height)).intValue();
                Iterator<T> it = list.iterator();
                while (true) {
                    obj = null;
                    if (!it.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it.next();
                    if (Intrinsics.areEqual(TextFieldKt.getLayoutId((IntrinsicMeasurable) next), TextFieldImplKt.TrailingId)) {
                        break;
                    }
                }
                IntrinsicMeasurable intrinsicMeasurable = (IntrinsicMeasurable) next;
                int iIntValue2 = intrinsicMeasurable == null ? 0 : intrinsicMeasurer.invoke(intrinsicMeasurable, Integer.valueOf(height)).intValue();
                Iterator<T> it2 = list.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        next2 = null;
                        break;
                    }
                    next2 = it2.next();
                    if (Intrinsics.areEqual(TextFieldKt.getLayoutId((IntrinsicMeasurable) next2), TextFieldImplKt.TrailingId)) {
                        break;
                    }
                }
                IntrinsicMeasurable intrinsicMeasurable2 = (IntrinsicMeasurable) next2;
                int iIntValue3 = intrinsicMeasurable2 == null ? 0 : intrinsicMeasurer.invoke(intrinsicMeasurable2, Integer.valueOf(height)).intValue();
                Iterator<T> it3 = list.iterator();
                while (true) {
                    if (!it3.hasNext()) {
                        next3 = null;
                        break;
                    }
                    next3 = it3.next();
                    if (Intrinsics.areEqual(TextFieldKt.getLayoutId((IntrinsicMeasurable) next3), TextFieldImplKt.LeadingId)) {
                        break;
                    }
                }
                IntrinsicMeasurable intrinsicMeasurable3 = (IntrinsicMeasurable) next3;
                int iIntValue4 = intrinsicMeasurable3 == null ? 0 : intrinsicMeasurer.invoke(intrinsicMeasurable3, Integer.valueOf(height)).intValue();
                Iterator<T> it4 = list.iterator();
                while (true) {
                    if (!it4.hasNext()) {
                        break;
                    }
                    Object next4 = it4.next();
                    if (Intrinsics.areEqual(TextFieldKt.getLayoutId((IntrinsicMeasurable) next4), TextFieldImplKt.PlaceholderId)) {
                        obj = next4;
                        break;
                    }
                }
                IntrinsicMeasurable intrinsicMeasurable4 = (IntrinsicMeasurable) obj;
                return TextFieldKt.m1469calculateWidthVsPV1Ek(iIntValue4, iIntValue3, iIntValue, iIntValue2, intrinsicMeasurable4 == null ? 0 : intrinsicMeasurer.invoke(intrinsicMeasurable4, Integer.valueOf(height)).intValue(), TextFieldKt.ZeroConstraints);
            }
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final int intrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i, Function2<? super IntrinsicMeasurable, ? super Integer, Integer> function2) {
        Object obj;
        Object next;
        Object next2;
        Object next3;
        List<? extends IntrinsicMeasurable> list2 = list;
        for (Object obj2 : list2) {
            if (Intrinsics.areEqual(TextFieldKt.getLayoutId((IntrinsicMeasurable) obj2), TextFieldImplKt.TextFieldId)) {
                int iIntValue = function2.invoke(obj2, Integer.valueOf(i)).intValue();
                Iterator<T> it = list2.iterator();
                while (true) {
                    obj = null;
                    if (!it.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it.next();
                    if (Intrinsics.areEqual(TextFieldKt.getLayoutId((IntrinsicMeasurable) next), TextFieldImplKt.TrailingId)) {
                        break;
                    }
                }
                IntrinsicMeasurable intrinsicMeasurable = (IntrinsicMeasurable) next;
                int iIntValue2 = intrinsicMeasurable == null ? 0 : function2.invoke(intrinsicMeasurable, Integer.valueOf(i)).intValue();
                Iterator<T> it2 = list2.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        next2 = null;
                        break;
                    }
                    next2 = it2.next();
                    if (Intrinsics.areEqual(TextFieldKt.getLayoutId((IntrinsicMeasurable) next2), TextFieldImplKt.TrailingId)) {
                        break;
                    }
                }
                IntrinsicMeasurable intrinsicMeasurable2 = (IntrinsicMeasurable) next2;
                int iIntValue3 = intrinsicMeasurable2 == null ? 0 : function2.invoke(intrinsicMeasurable2, Integer.valueOf(i)).intValue();
                Iterator<T> it3 = list2.iterator();
                while (true) {
                    if (!it3.hasNext()) {
                        next3 = null;
                        break;
                    }
                    next3 = it3.next();
                    if (Intrinsics.areEqual(TextFieldKt.getLayoutId((IntrinsicMeasurable) next3), TextFieldImplKt.LeadingId)) {
                        break;
                    }
                }
                IntrinsicMeasurable intrinsicMeasurable3 = (IntrinsicMeasurable) next3;
                int iIntValue4 = intrinsicMeasurable3 == null ? 0 : function2.invoke(intrinsicMeasurable3, Integer.valueOf(i)).intValue();
                Iterator<T> it4 = list2.iterator();
                while (true) {
                    if (!it4.hasNext()) {
                        break;
                    }
                    Object next4 = it4.next();
                    if (Intrinsics.areEqual(TextFieldKt.getLayoutId((IntrinsicMeasurable) next4), TextFieldImplKt.PlaceholderId)) {
                        obj = next4;
                        break;
                    }
                }
                IntrinsicMeasurable intrinsicMeasurable4 = (IntrinsicMeasurable) obj;
                return TextFieldKt.m1468calculateHeightYbqEFUw(iIntValue, iIntValue2 != 0, iIntValue2, iIntValue4, iIntValue3, intrinsicMeasurable4 == null ? 0 : function2.invoke(intrinsicMeasurable4, Integer.valueOf(i)).intValue(), TextFieldKt.ZeroConstraints, intrinsicMeasureScope.getDensity());
            }
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }
}
