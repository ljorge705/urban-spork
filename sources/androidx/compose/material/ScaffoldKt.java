package androidx.compose.material;

import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.layout.SubcomposeLayoutKt;
import androidx.compose.ui.layout.SubcomposeMeasureScope;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import com.facebook.react.uimanager.ViewProps;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Scaffold.kt */
@Metadata(d1 = {"\u0000x\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a¢\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\u0013\b\u0002\u0010\u000e\u001a\r\u0012\u0004\u0012\u00020\t0\u000f¢\u0006\u0002\b\u00102\u0013\b\u0002\u0010\u0011\u001a\r\u0012\u0004\u0012\u00020\t0\u000f¢\u0006\u0002\b\u00102\u0019\b\u0002\u0010\u0012\u001a\u0013\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\t0\u0013¢\u0006\u0002\b\u00102\u0013\b\u0002\u0010\u0015\u001a\r\u0012\u0004\u0012\u00020\t0\u000f¢\u0006\u0002\b\u00102\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u00192 \b\u0002\u0010\u001a\u001a\u001a\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\t\u0018\u00010\u0013¢\u0006\u0002\b\u0010¢\u0006\u0002\b\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u00192\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010 \u001a\u00020\u00012\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020\"2\b\b\u0002\u0010$\u001a\u00020\"2\b\b\u0002\u0010%\u001a\u00020\"2\b\b\u0002\u0010&\u001a\u00020\"2\u0017\u0010'\u001a\u0013\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020\t0\u0013¢\u0006\u0002\b\u0010H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b)\u0010*\u001a\u008a\u0001\u0010+\u001a\u00020\t2\u0006\u0010,\u001a\u00020\u00192\u0006\u0010-\u001a\u00020\u00172\u0011\u0010\u000e\u001a\r\u0012\u0004\u0012\u00020\t0\u000f¢\u0006\u0002\b\u00102\u0017\u0010'\u001a\u0013\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020\t0\u0013¢\u0006\u0002\b\u00102\u0011\u0010.\u001a\r\u0012\u0004\u0012\u00020\t0\u000f¢\u0006\u0002\b\u00102\u0011\u0010/\u001a\r\u0012\u0004\u0012\u00020\t0\u000f¢\u0006\u0002\b\u00102\u0011\u0010\u0011\u001a\r\u0012\u0004\u0012\u00020\t0\u000f¢\u0006\u0002\b\u0010H\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b0\u00101\u001a!\u00102\u001a\u00020\r2\b\b\u0002\u00103\u001a\u0002042\b\b\u0002\u00105\u001a\u00020\u0014H\u0007¢\u0006\u0002\u00106\"\u0013\u0010\u0000\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\"\u001c\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u00067"}, d2 = {"FabSpacing", "Landroidx/compose/ui/unit/Dp;", "F", "LocalFabPlacement", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "Landroidx/compose/material/FabPlacement;", "getLocalFabPlacement", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "Scaffold", "", "modifier", "Landroidx/compose/ui/Modifier;", "scaffoldState", "Landroidx/compose/material/ScaffoldState;", "topBar", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "bottomBar", "snackbarHost", "Lkotlin/Function1;", "Landroidx/compose/material/SnackbarHostState;", "floatingActionButton", "floatingActionButtonPosition", "Landroidx/compose/material/FabPosition;", "isFloatingActionButtonDocked", "", "drawerContent", "Landroidx/compose/foundation/layout/ColumnScope;", "Lkotlin/ExtensionFunctionType;", "drawerGesturesEnabled", "drawerShape", "Landroidx/compose/ui/graphics/Shape;", "drawerElevation", "drawerBackgroundColor", "Landroidx/compose/ui/graphics/Color;", "drawerContentColor", "drawerScrimColor", ViewProps.BACKGROUND_COLOR, "contentColor", "content", "Landroidx/compose/foundation/layout/PaddingValues;", "Scaffold-27mzLpw", "(Landroidx/compose/ui/Modifier;Landroidx/compose/material/ScaffoldState;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;IZLkotlin/jvm/functions/Function3;ZLandroidx/compose/ui/graphics/Shape;FJJJJJLkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "ScaffoldLayout", "isFabDocked", "fabPosition", "snackbar", "fab", "ScaffoldLayout-MDYNRJg", "(ZILkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "rememberScaffoldState", "drawerState", "Landroidx/compose/material/DrawerState;", "snackbarHostState", "(Landroidx/compose/material/DrawerState;Landroidx/compose/material/SnackbarHostState;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material/ScaffoldState;", "material_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class ScaffoldKt {
    private static final ProvidableCompositionLocal<FabPlacement> LocalFabPlacement = CompositionLocalKt.staticCompositionLocalOf(new Function0<FabPlacement>() { // from class: androidx.compose.material.ScaffoldKt$LocalFabPlacement$1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final FabPlacement invoke() {
            return null;
        }
    });
    private static final float FabSpacing = Dp.m4390constructorimpl(16);

    public static final ProvidableCompositionLocal<FabPlacement> getLocalFabPlacement() {
        return LocalFabPlacement;
    }

    public static final ScaffoldState rememberScaffoldState(DrawerState drawerState, SnackbarHostState snackbarHostState, Composer composer, int i, int i2) {
        composer.startReplaceableGroup(-1962071859);
        ComposerKt.sourceInformation(composer, "C(rememberScaffoldState)62@2223L39,63@2307L32,64@2359L62:Scaffold.kt#jmzs0o");
        if ((i2 & 1) != 0) {
            drawerState = DrawerKt.rememberDrawerState(DrawerValue.Closed, null, composer, 6, 2);
        }
        if ((i2 & 2) != 0) {
            composer.startReplaceableGroup(-3687241);
            ComposerKt.sourceInformation(composer, "C(remember):Composables.kt#9igjgp");
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new SnackbarHostState();
                composer.updateRememberedValue(objRememberedValue);
            }
            composer.endReplaceableGroup();
            snackbarHostState = (SnackbarHostState) objRememberedValue;
        }
        composer.startReplaceableGroup(-3687241);
        ComposerKt.sourceInformation(composer, "C(remember):Composables.kt#9igjgp");
        Object objRememberedValue2 = composer.rememberedValue();
        if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue2 = new ScaffoldState(drawerState, snackbarHostState);
            composer.updateRememberedValue(objRememberedValue2);
        }
        composer.endReplaceableGroup();
        ScaffoldState scaffoldState = (ScaffoldState) objRememberedValue2;
        composer.endReplaceableGroup();
        return scaffoldState;
    }

    /* JADX WARN: Removed duplicated region for block: B:196:0x0263  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x02ef  */
    /* JADX WARN: Removed duplicated region for block: B:243:0x02f4  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x02f9  */
    /* JADX WARN: Removed duplicated region for block: B:247:0x0300  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x030c  */
    /* JADX WARN: Removed duplicated region for block: B:251:0x0314  */
    /* JADX WARN: Removed duplicated region for block: B:254:0x0321  */
    /* JADX WARN: Removed duplicated region for block: B:257:0x032e  */
    /* JADX WARN: Removed duplicated region for block: B:258:0x0337  */
    /* JADX WARN: Removed duplicated region for block: B:261:0x033d  */
    /* JADX WARN: Removed duplicated region for block: B:262:0x0346  */
    /* JADX WARN: Removed duplicated region for block: B:265:0x034c  */
    /* JADX WARN: Removed duplicated region for block: B:266:0x0355  */
    /* JADX WARN: Removed duplicated region for block: B:268:0x0359  */
    /* JADX WARN: Removed duplicated region for block: B:270:0x035c  */
    /* JADX WARN: Removed duplicated region for block: B:271:0x035e  */
    /* JADX WARN: Removed duplicated region for block: B:273:0x0362  */
    /* JADX WARN: Removed duplicated region for block: B:274:0x0364  */
    /* JADX WARN: Removed duplicated region for block: B:277:0x036a  */
    /* JADX WARN: Removed duplicated region for block: B:278:0x0379  */
    /* JADX WARN: Removed duplicated region for block: B:281:0x037f  */
    /* JADX WARN: Removed duplicated region for block: B:282:0x0388  */
    /* JADX WARN: Removed duplicated region for block: B:285:0x0394  */
    /* JADX WARN: Removed duplicated region for block: B:286:0x03a4  */
    /* JADX WARN: Removed duplicated region for block: B:289:0x03ae  */
    /* JADX WARN: Removed duplicated region for block: B:290:0x03b9  */
    /* JADX WARN: Removed duplicated region for block: B:293:0x03bf  */
    /* JADX WARN: Removed duplicated region for block: B:294:0x03cb  */
    /* JADX WARN: Removed duplicated region for block: B:297:0x03d9  */
    /* JADX WARN: Removed duplicated region for block: B:298:0x03e6  */
    /* JADX WARN: Removed duplicated region for block: B:301:0x03ec  */
    /* JADX WARN: Removed duplicated region for block: B:302:0x03f7  */
    /* JADX WARN: Removed duplicated region for block: B:306:0x0441  */
    /* JADX WARN: Removed duplicated region for block: B:307:0x04b1  */
    /* JADX WARN: Removed duplicated region for block: B:312:0x04e5  */
    /* JADX WARN: Removed duplicated region for block: B:314:? A[RETURN, SYNTHETIC] */
    /* renamed from: Scaffold-27mzLpw, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1393Scaffold27mzLpw(androidx.compose.ui.Modifier r33, androidx.compose.material.ScaffoldState r34, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r35, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r36, kotlin.jvm.functions.Function3<? super androidx.compose.material.SnackbarHostState, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r37, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r38, int r39, boolean r40, kotlin.jvm.functions.Function3<? super androidx.compose.foundation.layout.ColumnScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r41, boolean r42, androidx.compose.ui.graphics.Shape r43, float r44, long r45, long r47, long r49, long r51, long r53, final kotlin.jvm.functions.Function3<? super androidx.compose.foundation.layout.PaddingValues, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r55, androidx.compose.runtime.Composer r56, final int r57, final int r58, final int r59) {
        /*
            Method dump skipped, instructions count: 1290
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.ScaffoldKt.m1393Scaffold27mzLpw(androidx.compose.ui.Modifier, androidx.compose.material.ScaffoldState, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function3, kotlin.jvm.functions.Function2, int, boolean, kotlin.jvm.functions.Function3, boolean, androidx.compose.ui.graphics.Shape, float, long, long, long, long, long, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: ScaffoldLayout-MDYNRJg, reason: not valid java name */
    public static final void m1394ScaffoldLayoutMDYNRJg(final boolean z, final int i, final Function2<? super Composer, ? super Integer, Unit> function2, final Function3<? super PaddingValues, ? super Composer, ? super Integer, Unit> function3, final Function2<? super Composer, ? super Integer, Unit> function22, final Function2<? super Composer, ? super Integer, Unit> function23, final Function2<? super Composer, ? super Integer, Unit> function24, Composer composer, final int i2) {
        int i3;
        int i4;
        Composer composerStartRestartGroup = composer.startRestartGroup(-2103106784);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(ScaffoldLayout)P(4,3:c#material.FabPosition,6,1,5,2)234@10158L4498,234@10141L4515:Scaffold.kt#jmzs0o");
        if ((i2 & 14) == 0) {
            i3 = (composerStartRestartGroup.changed(z) ? 4 : 2) | i2;
        } else {
            i3 = i2;
        }
        if ((i2 & 112) == 0) {
            i3 |= composerStartRestartGroup.changed(i) ? 32 : 16;
        }
        if ((i2 & 896) == 0) {
            i3 |= composerStartRestartGroup.changed(function2) ? 256 : 128;
        }
        if ((i2 & 7168) == 0) {
            i3 |= composerStartRestartGroup.changed(function3) ? 2048 : 1024;
        }
        if ((57344 & i2) == 0) {
            i3 |= composerStartRestartGroup.changed(function22) ? 16384 : 8192;
        }
        if ((458752 & i2) == 0) {
            i3 |= composerStartRestartGroup.changed(function23) ? 131072 : 65536;
        }
        if ((3670016 & i2) == 0) {
            i3 |= composerStartRestartGroup.changed(function24) ? 1048576 : 524288;
        }
        final int i5 = i3;
        if (((i5 & 2995931) ^ 599186) != 0 || !composerStartRestartGroup.getSkipping()) {
            Object[] objArr = {function2, function22, function23, FabPosition.m1305boximpl(i), Boolean.valueOf(z), function24, function3};
            composerStartRestartGroup.startReplaceableGroup(-3685570);
            ComposerKt.sourceInformation(composerStartRestartGroup, "C(remember)P(1):Composables.kt#9igjgp");
            int i6 = 0;
            boolean zChanged = false;
            while (i6 < 7) {
                Object obj = objArr[i6];
                i6++;
                zChanged |= composerStartRestartGroup.changed(obj);
            }
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                i4 = 0;
                objRememberedValue = (Function2) new Function2<SubcomposeMeasureScope, Constraints, MeasureResult>() { // from class: androidx.compose.material.ScaffoldKt$ScaffoldLayout$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ MeasureResult invoke(SubcomposeMeasureScope subcomposeMeasureScope, Constraints constraints) {
                        return m1396invoke0kLqBqw(subcomposeMeasureScope, constraints.getValue());
                    }

                    /* renamed from: invoke-0kLqBqw, reason: not valid java name */
                    public final MeasureResult m1396invoke0kLqBqw(final SubcomposeMeasureScope SubcomposeLayout, long j) {
                        Intrinsics.checkNotNullParameter(SubcomposeLayout, "$this$SubcomposeLayout");
                        final int iM4346getMaxWidthimpl = Constraints.m4346getMaxWidthimpl(j);
                        final int iM4345getMaxHeightimpl = Constraints.m4345getMaxHeightimpl(j);
                        final long jM4337copyZbe2FdA$default = Constraints.m4337copyZbe2FdA$default(j, 0, 0, 0, 0, 10, null);
                        final Function2<Composer, Integer, Unit> function25 = function2;
                        final Function2<Composer, Integer, Unit> function26 = function22;
                        final Function2<Composer, Integer, Unit> function27 = function23;
                        final int i7 = i;
                        final boolean z2 = z;
                        final Function2<Composer, Integer, Unit> function28 = function24;
                        final int i8 = i5;
                        final Function3<PaddingValues, Composer, Integer, Unit> function32 = function3;
                        return MeasureScope.layout$default(SubcomposeLayout, iM4346getMaxWidthimpl, iM4345getMaxHeightimpl, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material.ScaffoldKt$ScaffoldLayout$1$1.1
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
                                Object obj2;
                                Object obj3;
                                final FabPlacement fabPlacement;
                                Object obj4;
                                int height;
                                int i9;
                                int height2;
                                Integer numValueOf;
                                int iIntValue;
                                Object obj5;
                                Object obj6;
                                int i10;
                                Intrinsics.checkNotNullParameter(layout, "$this$layout");
                                List<Measurable> listSubcompose = SubcomposeLayout.subcompose(ScaffoldLayoutContent.TopBar, function25);
                                long j2 = jM4337copyZbe2FdA$default;
                                ArrayList arrayList = new ArrayList(listSubcompose.size());
                                int size = listSubcompose.size() - 1;
                                if (size >= 0) {
                                    int i11 = 0;
                                    while (true) {
                                        int i12 = i11 + 1;
                                        arrayList.add(listSubcompose.get(i11).mo3396measureBRTryo0(j2));
                                        if (i12 > size) {
                                            break;
                                        } else {
                                            i11 = i12;
                                        }
                                    }
                                }
                                ArrayList arrayList2 = arrayList;
                                if (arrayList2.isEmpty()) {
                                    obj2 = null;
                                } else {
                                    obj2 = arrayList2.get(0);
                                    int height3 = ((Placeable) obj2).getHeight();
                                    int lastIndex = CollectionsKt.getLastIndex(arrayList2);
                                    if (1 <= lastIndex) {
                                        int i13 = 1;
                                        while (true) {
                                            int i14 = i13 + 1;
                                            Object obj7 = arrayList2.get(i13);
                                            int height4 = ((Placeable) obj7).getHeight();
                                            if (height3 < height4) {
                                                obj2 = obj7;
                                                height3 = height4;
                                            }
                                            if (i13 == lastIndex) {
                                                break;
                                            } else {
                                                i13 = i14;
                                            }
                                        }
                                    }
                                }
                                Placeable placeable = (Placeable) obj2;
                                int height5 = placeable == null ? 0 : placeable.getHeight();
                                List<Measurable> listSubcompose2 = SubcomposeLayout.subcompose(ScaffoldLayoutContent.Snackbar, function26);
                                long j3 = jM4337copyZbe2FdA$default;
                                ArrayList arrayList3 = new ArrayList(listSubcompose2.size());
                                int size2 = listSubcompose2.size() - 1;
                                if (size2 >= 0) {
                                    int i15 = 0;
                                    while (true) {
                                        int i16 = i15 + 1;
                                        arrayList3.add(listSubcompose2.get(i15).mo3396measureBRTryo0(j3));
                                        if (i16 > size2) {
                                            break;
                                        } else {
                                            i15 = i16;
                                        }
                                    }
                                }
                                ArrayList arrayList4 = arrayList3;
                                if (arrayList4.isEmpty()) {
                                    obj3 = null;
                                } else {
                                    obj3 = arrayList4.get(0);
                                    int height6 = ((Placeable) obj3).getHeight();
                                    int lastIndex2 = CollectionsKt.getLastIndex(arrayList4);
                                    if (1 <= lastIndex2) {
                                        int i17 = 1;
                                        while (true) {
                                            int i18 = i17 + 1;
                                            Object obj8 = arrayList4.get(i17);
                                            int height7 = ((Placeable) obj8).getHeight();
                                            if (height6 < height7) {
                                                obj3 = obj8;
                                                height6 = height7;
                                            }
                                            if (i17 == lastIndex2) {
                                                break;
                                            } else {
                                                i17 = i18;
                                            }
                                        }
                                    }
                                }
                                Placeable placeable2 = (Placeable) obj3;
                                int height8 = placeable2 == null ? 0 : placeable2.getHeight();
                                List<Measurable> listSubcompose3 = SubcomposeLayout.subcompose(ScaffoldLayoutContent.Fab, function27);
                                long j4 = jM4337copyZbe2FdA$default;
                                ArrayList arrayList5 = new ArrayList();
                                Iterator<T> it = listSubcompose3.iterator();
                                while (it.hasNext()) {
                                    Placeable placeableMo3396measureBRTryo0 = ((Measurable) it.next()).mo3396measureBRTryo0(j4);
                                    if (placeableMo3396measureBRTryo0.getHeight() == 0 || placeableMo3396measureBRTryo0.getWidth() == 0) {
                                        placeableMo3396measureBRTryo0 = null;
                                    }
                                    if (placeableMo3396measureBRTryo0 != null) {
                                        arrayList5.add(placeableMo3396measureBRTryo0);
                                    }
                                }
                                ArrayList arrayList6 = arrayList5;
                                if (!(!arrayList6.isEmpty())) {
                                    fabPlacement = null;
                                } else {
                                    if (arrayList6.isEmpty()) {
                                        obj5 = null;
                                    } else {
                                        obj5 = arrayList6.get(0);
                                        int width = ((Placeable) obj5).getWidth();
                                        int lastIndex3 = CollectionsKt.getLastIndex(arrayList6);
                                        if (1 <= lastIndex3) {
                                            int i19 = 1;
                                            while (true) {
                                                int i20 = i19 + 1;
                                                Object obj9 = arrayList6.get(i19);
                                                int width2 = ((Placeable) obj9).getWidth();
                                                if (width < width2) {
                                                    width = width2;
                                                    obj5 = obj9;
                                                }
                                                if (i19 == lastIndex3) {
                                                    break;
                                                } else {
                                                    i19 = i20;
                                                }
                                            }
                                        }
                                    }
                                    Placeable placeable3 = (Placeable) obj5;
                                    Intrinsics.checkNotNull(placeable3);
                                    int width3 = placeable3.getWidth();
                                    if (arrayList6.isEmpty()) {
                                        obj6 = null;
                                    } else {
                                        obj6 = arrayList6.get(0);
                                        int height9 = ((Placeable) obj6).getHeight();
                                        int lastIndex4 = CollectionsKt.getLastIndex(arrayList6);
                                        if (1 <= lastIndex4) {
                                            int i21 = 1;
                                            while (true) {
                                                int i22 = i21 + 1;
                                                Object obj10 = arrayList6.get(i21);
                                                int height10 = ((Placeable) obj10).getHeight();
                                                if (height9 < height10) {
                                                    height9 = height10;
                                                    obj6 = obj10;
                                                }
                                                if (i21 == lastIndex4) {
                                                    break;
                                                } else {
                                                    i21 = i22;
                                                }
                                            }
                                        }
                                    }
                                    Placeable placeable4 = (Placeable) obj6;
                                    Intrinsics.checkNotNull(placeable4);
                                    int height11 = placeable4.getHeight();
                                    if (FabPosition.m1308equalsimpl0(i7, FabPosition.INSTANCE.m1313getEnd5ygKITE())) {
                                        if (SubcomposeLayout.getLayoutDirection() == LayoutDirection.Ltr) {
                                            i10 = (iM4346getMaxWidthimpl - SubcomposeLayout.mo571roundToPx0680j_4(ScaffoldKt.FabSpacing)) - width3;
                                        } else {
                                            i10 = SubcomposeLayout.mo571roundToPx0680j_4(ScaffoldKt.FabSpacing);
                                        }
                                    } else {
                                        i10 = (iM4346getMaxWidthimpl - width3) / 2;
                                    }
                                    fabPlacement = new FabPlacement(z2, i10, width3, height11);
                                }
                                SubcomposeMeasureScope subcomposeMeasureScope = SubcomposeLayout;
                                ScaffoldLayoutContent scaffoldLayoutContent = ScaffoldLayoutContent.BottomBar;
                                final Function2<Composer, Integer, Unit> function29 = function28;
                                final int i23 = i8;
                                List<Measurable> listSubcompose4 = subcomposeMeasureScope.subcompose(scaffoldLayoutContent, ComposableLambdaKt.composableLambdaInstance(-985538854, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ScaffoldKt$ScaffoldLayout$1$1$1$bottomBarPlaceables$1
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

                                    public final void invoke(Composer composer2, int i24) {
                                        ComposerKt.sourceInformation(composer2, "C283@12203L144:Scaffold.kt#jmzs0o");
                                        if (((i24 & 11) ^ 2) != 0 || !composer2.getSkipping()) {
                                            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ScaffoldKt.getLocalFabPlacement().provides(fabPlacement)}, function29, composer2, ((i23 >> 15) & 112) | 8);
                                        } else {
                                            composer2.skipToGroupEnd();
                                        }
                                    }
                                }));
                                long j5 = jM4337copyZbe2FdA$default;
                                ArrayList arrayList7 = new ArrayList(listSubcompose4.size());
                                int size3 = listSubcompose4.size() - 1;
                                if (size3 >= 0) {
                                    int i24 = 0;
                                    while (true) {
                                        int i25 = i24 + 1;
                                        arrayList7.add(listSubcompose4.get(i24).mo3396measureBRTryo0(j5));
                                        if (i25 > size3) {
                                            break;
                                        } else {
                                            i24 = i25;
                                        }
                                    }
                                }
                                ArrayList arrayList8 = arrayList7;
                                if (arrayList8.isEmpty()) {
                                    obj4 = null;
                                } else {
                                    obj4 = arrayList8.get(0);
                                    int height12 = ((Placeable) obj4).getHeight();
                                    int lastIndex5 = CollectionsKt.getLastIndex(arrayList8);
                                    if (1 <= lastIndex5) {
                                        int i26 = 1;
                                        while (true) {
                                            int i27 = i26 + 1;
                                            Object obj11 = arrayList8.get(i26);
                                            int height13 = ((Placeable) obj11).getHeight();
                                            if (height12 < height13) {
                                                obj4 = obj11;
                                                height12 = height13;
                                            }
                                            if (i26 == lastIndex5) {
                                                break;
                                            } else {
                                                i26 = i27;
                                            }
                                        }
                                    }
                                }
                                Placeable placeable5 = (Placeable) obj4;
                                final int height14 = placeable5 == null ? 0 : placeable5.getHeight();
                                if (fabPlacement == null) {
                                    numValueOf = null;
                                } else {
                                    SubcomposeMeasureScope subcomposeMeasureScope2 = SubcomposeLayout;
                                    boolean z3 = z2;
                                    if (height14 == 0) {
                                        height = fabPlacement.getHeight();
                                        i9 = subcomposeMeasureScope2.mo571roundToPx0680j_4(ScaffoldKt.FabSpacing);
                                    } else if (z3) {
                                        height2 = height14 + (fabPlacement.getHeight() / 2);
                                        numValueOf = Integer.valueOf(height2);
                                    } else {
                                        height = fabPlacement.getHeight() + height14;
                                        i9 = subcomposeMeasureScope2.mo571roundToPx0680j_4(ScaffoldKt.FabSpacing);
                                    }
                                    height2 = height + i9;
                                    numValueOf = Integer.valueOf(height2);
                                }
                                if (height8 != 0) {
                                    iIntValue = height8 + (numValueOf == null ? height14 : numValueOf.intValue());
                                } else {
                                    iIntValue = 0;
                                }
                                int i28 = iM4345getMaxHeightimpl - height5;
                                SubcomposeMeasureScope subcomposeMeasureScope3 = SubcomposeLayout;
                                ScaffoldLayoutContent scaffoldLayoutContent2 = ScaffoldLayoutContent.MainContent;
                                final SubcomposeMeasureScope subcomposeMeasureScope4 = SubcomposeLayout;
                                final Function3<PaddingValues, Composer, Integer, Unit> function33 = function32;
                                final int i29 = i8;
                                List<Measurable> listSubcompose5 = subcomposeMeasureScope3.subcompose(scaffoldLayoutContent2, ComposableLambdaKt.composableLambdaInstance(-985545322, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ScaffoldKt$ScaffoldLayout$1$1$1$bodyContentPlaceables$1
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

                                    public final void invoke(Composer composer2, int i30) {
                                        ComposerKt.sourceInformation(composer2, "C315@13625L21:Scaffold.kt#jmzs0o");
                                        if (((i30 & 11) ^ 2) != 0 || !composer2.getSkipping()) {
                                            function33.invoke(PaddingKt.m687PaddingValuesa9UjIt4$default(0.0f, 0.0f, 0.0f, subcomposeMeasureScope4.mo574toDpu2uoSUM(height14), 7, null), composer2, Integer.valueOf((i29 >> 6) & 112));
                                        } else {
                                            composer2.skipToGroupEnd();
                                        }
                                    }
                                }));
                                long j6 = jM4337copyZbe2FdA$default;
                                ArrayList arrayList9 = new ArrayList(listSubcompose5.size());
                                int size4 = listSubcompose5.size() - 1;
                                if (size4 >= 0) {
                                    int i30 = 0;
                                    while (true) {
                                        int i31 = i30 + 1;
                                        List<Measurable> list = listSubcompose5;
                                        long j7 = j6;
                                        arrayList9.add(listSubcompose5.get(i30).mo3396measureBRTryo0(Constraints.m4337copyZbe2FdA$default(j6, 0, 0, 0, i28, 7, null)));
                                        if (i31 > size4) {
                                            break;
                                        }
                                        i30 = i31;
                                        listSubcompose5 = list;
                                        j6 = j7;
                                    }
                                }
                                ArrayList arrayList10 = arrayList9;
                                int size5 = arrayList10.size() - 1;
                                if (size5 >= 0) {
                                    int i32 = 0;
                                    while (true) {
                                        ArrayList arrayList11 = arrayList10;
                                        int i33 = size5;
                                        Placeable.PlacementScope.place$default(layout, (Placeable) arrayList10.get(i32), 0, height5, 0.0f, 4, null);
                                        i32++;
                                        if (i32 > i33) {
                                            break;
                                        }
                                        size5 = i33;
                                        arrayList10 = arrayList11;
                                    }
                                }
                                int size6 = arrayList2.size() - 1;
                                if (size6 >= 0) {
                                    int i34 = 0;
                                    while (true) {
                                        int i35 = i34 + 1;
                                        Placeable.PlacementScope.place$default(layout, (Placeable) arrayList2.get(i34), 0, 0, 0.0f, 4, null);
                                        if (i35 > size6) {
                                            break;
                                        } else {
                                            i34 = i35;
                                        }
                                    }
                                }
                                int i36 = iM4345getMaxHeightimpl;
                                int size7 = arrayList4.size() - 1;
                                if (size7 >= 0) {
                                    int i37 = 0;
                                    while (true) {
                                        int i38 = i37 + 1;
                                        Placeable.PlacementScope.place$default(layout, (Placeable) arrayList4.get(i37), 0, i36 - iIntValue, 0.0f, 4, null);
                                        if (i38 > size7) {
                                            break;
                                        } else {
                                            i37 = i38;
                                        }
                                    }
                                }
                                int i39 = iM4345getMaxHeightimpl;
                                int size8 = arrayList8.size() - 1;
                                if (size8 >= 0) {
                                    int i40 = 0;
                                    while (true) {
                                        int i41 = i40 + 1;
                                        Placeable.PlacementScope.place$default(layout, (Placeable) arrayList8.get(i40), 0, i39 - height14, 0.0f, 4, null);
                                        if (i41 > size8) {
                                            break;
                                        } else {
                                            i40 = i41;
                                        }
                                    }
                                }
                                if (fabPlacement == null) {
                                    return;
                                }
                                int i42 = iM4345getMaxHeightimpl;
                                int size9 = arrayList6.size() - 1;
                                if (size9 >= 0) {
                                    int i43 = 0;
                                    while (true) {
                                        int i44 = i43 + 1;
                                        Placeable placeable6 = (Placeable) arrayList6.get(i43);
                                        int left = fabPlacement.getLeft();
                                        Intrinsics.checkNotNull(numValueOf);
                                        Placeable.PlacementScope.place$default(layout, placeable6, left, i42 - numValueOf.intValue(), 0.0f, 4, null);
                                        if (i44 > size9) {
                                            break;
                                        } else {
                                            i43 = i44;
                                        }
                                    }
                                }
                                Unit unit = Unit.INSTANCE;
                                Unit unit2 = Unit.INSTANCE;
                            }
                        }, 4, null);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            } else {
                i4 = 0;
            }
            composerStartRestartGroup.endReplaceableGroup();
            SubcomposeLayoutKt.SubcomposeLayout(null, (Function2) objRememberedValue, composerStartRestartGroup, i4, 1);
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ScaffoldKt$ScaffoldLayout$2
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

            public final void invoke(Composer composer2, int i7) {
                ScaffoldKt.m1394ScaffoldLayoutMDYNRJg(z, i, function2, function3, function22, function23, function24, composer2, i2 | 1);
            }
        });
    }
}
