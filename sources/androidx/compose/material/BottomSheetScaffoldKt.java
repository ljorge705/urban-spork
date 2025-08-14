package androidx.compose.material;

import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
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
import kotlin.math.MathKt;

/* compiled from: BottomSheetScaffold.kt */
@Metadata(d1 = {"\u0000\u0088\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aá\u0002\u0010\u0003\u001a\u00020\u00042\u001c\u0010\u0005\u001a\u0018\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00040\u0006¢\u0006\u0002\b\b¢\u0006\u0002\b\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\u0015\b\u0002\u0010\u000e\u001a\u000f\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u000f¢\u0006\u0002\b\b2\u0019\b\u0002\u0010\u0010\u001a\u0013\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00040\u0006¢\u0006\u0002\b\b2\u0015\b\u0002\u0010\u0012\u001a\u000f\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u000f¢\u0006\u0002\b\b2\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u00012\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u001b2\b\b\u0002\u0010\u001d\u001a\u00020\u00012 \b\u0002\u0010\u001e\u001a\u001a\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0006¢\u0006\u0002\b\b¢\u0006\u0002\b\t2\b\b\u0002\u0010\u001f\u001a\u00020\u00162\b\b\u0002\u0010 \u001a\u00020\u00182\b\b\u0002\u0010!\u001a\u00020\u00012\b\b\u0002\u0010\"\u001a\u00020\u001b2\b\b\u0002\u0010#\u001a\u00020\u001b2\b\b\u0002\u0010$\u001a\u00020\u001b2\b\b\u0002\u0010%\u001a\u00020\u001b2\b\b\u0002\u0010&\u001a\u00020\u001b2\u0017\u0010'\u001a\u0013\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020\u00040\u0006¢\u0006\u0002\b\bH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b)\u0010*\u001aw\u0010+\u001a\u00020\u00042\u0011\u0010,\u001a\r\u0012\u0004\u0012\u00020\u00040\u000f¢\u0006\u0002\b\b2\u0011\u0010-\u001a\r\u0012\u0004\u0012\u00020\u00040\u000f¢\u0006\u0002\b\b2\u0011\u0010\u0012\u001a\r\u0012\u0004\u0012\u00020\u00040\u000f¢\u0006\u0002\b\b2\u0011\u0010\u0010\u001a\r\u0012\u0004\u0012\u00020\u00040\u000f¢\u0006\u0002\b\b2\f\u0010.\u001a\b\u0012\u0004\u0012\u0002000/2\u0006\u0010\u0013\u001a\u00020\u0014H\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b1\u00102\u001a+\u00103\u001a\u00020\r2\b\b\u0002\u00104\u001a\u0002052\b\b\u0002\u00106\u001a\u0002072\b\b\u0002\u00108\u001a\u00020\u0011H\u0007¢\u0006\u0002\u00109\u001a;\u0010:\u001a\u0002072\u0006\u0010;\u001a\u00020<2\u000e\b\u0002\u0010=\u001a\b\u0012\u0004\u0012\u0002000>2\u0014\b\u0002\u0010?\u001a\u000e\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u00020\u00160\u0006H\u0007¢\u0006\u0002\u0010@\"\u0013\u0010\u0000\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006A"}, d2 = {"FabEndSpacing", "Landroidx/compose/ui/unit/Dp;", "F", "BottomSheetScaffold", "", "sheetContent", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "modifier", "Landroidx/compose/ui/Modifier;", "scaffoldState", "Landroidx/compose/material/BottomSheetScaffoldState;", "topBar", "Lkotlin/Function0;", "snackbarHost", "Landroidx/compose/material/SnackbarHostState;", "floatingActionButton", "floatingActionButtonPosition", "Landroidx/compose/material/FabPosition;", "sheetGesturesEnabled", "", "sheetShape", "Landroidx/compose/ui/graphics/Shape;", "sheetElevation", "sheetBackgroundColor", "Landroidx/compose/ui/graphics/Color;", "sheetContentColor", "sheetPeekHeight", "drawerContent", "drawerGesturesEnabled", "drawerShape", "drawerElevation", "drawerBackgroundColor", "drawerContentColor", "drawerScrimColor", ViewProps.BACKGROUND_COLOR, "contentColor", "content", "Landroidx/compose/foundation/layout/PaddingValues;", "BottomSheetScaffold-bGncdBI", "(Lkotlin/jvm/functions/Function3;Landroidx/compose/ui/Modifier;Landroidx/compose/material/BottomSheetScaffoldState;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;IZLandroidx/compose/ui/graphics/Shape;FJJFLkotlin/jvm/functions/Function3;ZLandroidx/compose/ui/graphics/Shape;FJJJJJLkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;IIII)V", "BottomSheetScaffoldStack", "body", "bottomSheet", "bottomSheetOffset", "Landroidx/compose/runtime/State;", "", "BottomSheetScaffoldStack-SlNgfk0", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/State;ILandroidx/compose/runtime/Composer;I)V", "rememberBottomSheetScaffoldState", "drawerState", "Landroidx/compose/material/DrawerState;", "bottomSheetState", "Landroidx/compose/material/BottomSheetState;", "snackbarHostState", "(Landroidx/compose/material/DrawerState;Landroidx/compose/material/BottomSheetState;Landroidx/compose/material/SnackbarHostState;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material/BottomSheetScaffoldState;", "rememberBottomSheetState", "initialValue", "Landroidx/compose/material/BottomSheetValue;", "animationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "confirmStateChange", "(Landroidx/compose/material/BottomSheetValue;Landroidx/compose/animation/core/AnimationSpec;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material/BottomSheetState;", "material_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class BottomSheetScaffoldKt {
    private static final float FabEndSpacing = Dp.m4390constructorimpl(16);

    @ExperimentalMaterialApi
    public static final BottomSheetState rememberBottomSheetState(final BottomSheetValue initialValue, final AnimationSpec<Float> animationSpec, final Function1<? super BottomSheetValue, Boolean> function1, Composer composer, int i, int i2) {
        Intrinsics.checkNotNullParameter(initialValue, "initialValue");
        composer.startReplaceableGroup(1877845424);
        ComposerKt.sourceInformation(composer, "C(rememberBottomSheetState)P(2)156@5647L371:BottomSheetScaffold.kt#jmzs0o");
        if ((i2 & 2) != 0) {
            animationSpec = SwipeableDefaults.INSTANCE.getAnimationSpec();
        }
        if ((i2 & 4) != 0) {
            function1 = new Function1<BottomSheetValue, Boolean>() { // from class: androidx.compose.material.BottomSheetScaffoldKt.rememberBottomSheetState.1
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final boolean invoke2(BottomSheetValue it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return true;
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Boolean invoke(BottomSheetValue bottomSheetValue) {
                    return Boolean.valueOf(invoke2(bottomSheetValue));
                }
            };
        }
        BottomSheetState bottomSheetState = (BottomSheetState) RememberSaveableKt.m1531rememberSaveable(new Object[]{animationSpec}, (Saver) BottomSheetState.INSTANCE.Saver(animationSpec, function1), (String) null, (Function0) new Function0<BottomSheetState>() { // from class: androidx.compose.material.BottomSheetScaffoldKt.rememberBottomSheetState.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BottomSheetState invoke() {
                return new BottomSheetState(initialValue, animationSpec, function1);
            }
        }, composer, 72, 4);
        composer.endReplaceableGroup();
        return bottomSheetState;
    }

    @ExperimentalMaterialApi
    public static final BottomSheetScaffoldState rememberBottomSheetScaffoldState(DrawerState drawerState, BottomSheetState bottomSheetState, SnackbarHostState snackbarHostState, Composer composer, int i, int i2) {
        composer.startReplaceableGroup(1807566285);
        ComposerKt.sourceInformation(composer, "C(rememberBottomSheetScaffoldState)P(1)196@6892L39,197@6974L52,198@7071L32,200@7145L248:BottomSheetScaffold.kt#jmzs0o");
        if ((i2 & 1) != 0) {
            drawerState = DrawerKt.rememberDrawerState(DrawerValue.Closed, null, composer, 6, 2);
        }
        if ((i2 & 2) != 0) {
            bottomSheetState = rememberBottomSheetState(BottomSheetValue.Collapsed, null, null, composer, 6, 6);
        }
        if ((i2 & 4) != 0) {
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
        composer.startReplaceableGroup(-3686095);
        ComposerKt.sourceInformation(composer, "C(remember)P(1,2,3):Composables.kt#9igjgp");
        boolean zChanged = composer.changed(drawerState) | composer.changed(bottomSheetState) | composer.changed(snackbarHostState);
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChanged || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue2 = new BottomSheetScaffoldState(drawerState, bottomSheetState, snackbarHostState);
            composer.updateRememberedValue(objRememberedValue2);
        }
        composer.endReplaceableGroup();
        BottomSheetScaffoldState bottomSheetScaffoldState = (BottomSheetScaffoldState) objRememberedValue2;
        composer.endReplaceableGroup();
        return bottomSheetScaffoldState;
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0141  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0157  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x015d  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0173  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x017b  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x018f  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0195  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x01ab  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x01b3  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x01b6  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x01d0  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x01d3  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x01f6  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x020d  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x0215  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x0229  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x0231  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x0245  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x024d  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x0263  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x026b  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x027f  */
    /* JADX WARN: Removed duplicated region for block: B:215:0x0287  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x0298  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x029f  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x02b5  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x02bd  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x02c0  */
    /* JADX WARN: Removed duplicated region for block: B:245:0x02db  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x0321  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:304:0x03d2  */
    /* JADX WARN: Removed duplicated region for block: B:306:0x03d7  */
    /* JADX WARN: Removed duplicated region for block: B:307:0x03de  */
    /* JADX WARN: Removed duplicated region for block: B:310:0x03e4  */
    /* JADX WARN: Removed duplicated region for block: B:311:0x0403  */
    /* JADX WARN: Removed duplicated region for block: B:313:0x0410  */
    /* JADX WARN: Removed duplicated region for block: B:314:0x0412  */
    /* JADX WARN: Removed duplicated region for block: B:317:0x0418  */
    /* JADX WARN: Removed duplicated region for block: B:319:0x0425  */
    /* JADX WARN: Removed duplicated region for block: B:320:0x0427  */
    /* JADX WARN: Removed duplicated region for block: B:323:0x042d  */
    /* JADX WARN: Removed duplicated region for block: B:324:0x0436  */
    /* JADX WARN: Removed duplicated region for block: B:326:0x043a  */
    /* JADX WARN: Removed duplicated region for block: B:327:0x043c  */
    /* JADX WARN: Removed duplicated region for block: B:330:0x0443  */
    /* JADX WARN: Removed duplicated region for block: B:331:0x0452  */
    /* JADX WARN: Removed duplicated region for block: B:334:0x0458  */
    /* JADX WARN: Removed duplicated region for block: B:335:0x0461  */
    /* JADX WARN: Removed duplicated region for block: B:338:0x0469  */
    /* JADX WARN: Removed duplicated region for block: B:339:0x0479  */
    /* JADX WARN: Removed duplicated region for block: B:342:0x0483  */
    /* JADX WARN: Removed duplicated region for block: B:343:0x048c  */
    /* JADX WARN: Removed duplicated region for block: B:346:0x0496  */
    /* JADX WARN: Removed duplicated region for block: B:347:0x049f  */
    /* JADX WARN: Removed duplicated region for block: B:349:0x04a3  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:350:0x04a5  */
    /* JADX WARN: Removed duplicated region for block: B:352:0x04a9  */
    /* JADX WARN: Removed duplicated region for block: B:353:0x04ac  */
    /* JADX WARN: Removed duplicated region for block: B:356:0x04b7  */
    /* JADX WARN: Removed duplicated region for block: B:357:0x04cb  */
    /* JADX WARN: Removed duplicated region for block: B:360:0x04d3  */
    /* JADX WARN: Removed duplicated region for block: B:361:0x04dc  */
    /* JADX WARN: Removed duplicated region for block: B:364:0x04e4  */
    /* JADX WARN: Removed duplicated region for block: B:365:0x04f7  */
    /* JADX WARN: Removed duplicated region for block: B:368:0x0501  */
    /* JADX WARN: Removed duplicated region for block: B:369:0x050e  */
    /* JADX WARN: Removed duplicated region for block: B:372:0x0516  */
    /* JADX WARN: Removed duplicated region for block: B:373:0x0522  */
    /* JADX WARN: Removed duplicated region for block: B:376:0x052b  */
    /* JADX WARN: Removed duplicated region for block: B:377:0x053a  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:380:0x0541  */
    /* JADX WARN: Removed duplicated region for block: B:381:0x054a  */
    /* JADX WARN: Removed duplicated region for block: B:385:0x05a1  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:390:0x0653  */
    /* JADX WARN: Removed duplicated region for block: B:392:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0123  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0139  */
    @androidx.compose.material.ExperimentalMaterialApi
    /* renamed from: BottomSheetScaffold-bGncdBI, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1198BottomSheetScaffoldbGncdBI(final kotlin.jvm.functions.Function3<? super androidx.compose.foundation.layout.ColumnScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r69, androidx.compose.ui.Modifier r70, androidx.compose.material.BottomSheetScaffoldState r71, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r72, kotlin.jvm.functions.Function3<? super androidx.compose.material.SnackbarHostState, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r73, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r74, int r75, boolean r76, androidx.compose.ui.graphics.Shape r77, float r78, long r79, long r81, float r83, kotlin.jvm.functions.Function3<? super androidx.compose.foundation.layout.ColumnScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r84, boolean r85, androidx.compose.ui.graphics.Shape r86, float r87, long r88, long r90, long r92, long r94, long r96, final kotlin.jvm.functions.Function3<? super androidx.compose.foundation.layout.PaddingValues, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r98, androidx.compose.runtime.Composer r99, final int r100, final int r101, final int r102, final int r103) {
        /*
            Method dump skipped, instructions count: 1650
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.BottomSheetScaffoldKt.m1198BottomSheetScaffoldbGncdBI(kotlin.jvm.functions.Function3, androidx.compose.ui.Modifier, androidx.compose.material.BottomSheetScaffoldState, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function3, kotlin.jvm.functions.Function2, int, boolean, androidx.compose.ui.graphics.Shape, float, long, long, float, kotlin.jvm.functions.Function3, boolean, androidx.compose.ui.graphics.Shape, float, long, long, long, long, long, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: BottomSheetScaffoldStack-SlNgfk0, reason: not valid java name */
    public static final void m1199BottomSheetScaffoldStackSlNgfk0(final Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function22, final Function2<? super Composer, ? super Integer, Unit> function23, final Function2<? super Composer, ? super Integer, Unit> function24, final State<Float> state, final int i, Composer composer, final int i2) {
        int i3;
        Composer composerStartRestartGroup = composer.startRestartGroup(1491542599);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(BottomSheetScaffoldStack)P(!2,3,5!,4:c#material.FabPosition)390@16235L1315:BottomSheetScaffold.kt#jmzs0o");
        if ((i2 & 14) == 0) {
            i3 = (composerStartRestartGroup.changed(function2) ? 4 : 2) | i2;
        } else {
            i3 = i2;
        }
        if ((i2 & 112) == 0) {
            i3 |= composerStartRestartGroup.changed(function22) ? 32 : 16;
        }
        if ((i2 & 896) == 0) {
            i3 |= composerStartRestartGroup.changed(function23) ? 256 : 128;
        }
        if ((i2 & 7168) == 0) {
            i3 |= composerStartRestartGroup.changed(function24) ? 2048 : 1024;
        }
        if ((57344 & i2) == 0) {
            i3 |= composerStartRestartGroup.changed(state) ? 16384 : 8192;
        }
        if ((458752 & i2) == 0) {
            i3 |= composerStartRestartGroup.changed(i) ? 131072 : 65536;
        }
        if (((374491 & i3) ^ 74898) != 0 || !composerStartRestartGroup.getSkipping()) {
            MeasurePolicy measurePolicy = new MeasurePolicy() { // from class: androidx.compose.material.BottomSheetScaffoldKt$BottomSheetScaffoldStack$2
                @Override // androidx.compose.ui.layout.MeasurePolicy
                public int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i4) {
                    return MeasurePolicy.DefaultImpls.maxIntrinsicHeight(this, intrinsicMeasureScope, list, i4);
                }

                @Override // androidx.compose.ui.layout.MeasurePolicy
                public int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i4) {
                    return MeasurePolicy.DefaultImpls.maxIntrinsicWidth(this, intrinsicMeasureScope, list, i4);
                }

                @Override // androidx.compose.ui.layout.MeasurePolicy
                public int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i4) {
                    return MeasurePolicy.DefaultImpls.minIntrinsicHeight(this, intrinsicMeasureScope, list, i4);
                }

                @Override // androidx.compose.ui.layout.MeasurePolicy
                public int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i4) {
                    return MeasurePolicy.DefaultImpls.minIntrinsicWidth(this, intrinsicMeasureScope, list, i4);
                }

                @Override // androidx.compose.ui.layout.MeasurePolicy
                /* renamed from: measure-3p2s80s */
                public final MeasureResult mo287measure3p2s80s(final MeasureScope Layout, final List<? extends Measurable> measurables, final long j) {
                    Intrinsics.checkNotNullParameter(Layout, "$this$Layout");
                    Intrinsics.checkNotNullParameter(measurables, "measurables");
                    final Placeable placeableMo3396measureBRTryo0 = ((Measurable) CollectionsKt.first((List) measurables)).mo3396measureBRTryo0(j);
                    int width = placeableMo3396measureBRTryo0.getWidth();
                    int height = placeableMo3396measureBRTryo0.getHeight();
                    final State<Float> state2 = state;
                    final int i4 = i;
                    return MeasureScope.layout$default(Layout, width, height, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material.BottomSheetScaffoldKt$BottomSheetScaffoldStack$2$measure$1
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
                            Placeable.PlacementScope.placeRelative$default(layout, placeableMo3396measureBRTryo0, 0, 0, 0.0f, 4, null);
                            List listDrop = CollectionsKt.drop(measurables, 1);
                            long j2 = j;
                            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listDrop, 10));
                            Iterator it = listDrop.iterator();
                            while (it.hasNext()) {
                                arrayList.add(((Measurable) it.next()).mo3396measureBRTryo0(Constraints.m4337copyZbe2FdA$default(j2, 0, 0, 0, 0, 10, null)));
                            }
                            ArrayList arrayList2 = arrayList;
                            Placeable placeable = (Placeable) arrayList2.get(0);
                            Placeable placeable2 = (Placeable) arrayList2.get(1);
                            Placeable placeable3 = (Placeable) arrayList2.get(2);
                            int iRoundToInt = MathKt.roundToInt(state2.getValue().floatValue());
                            Placeable.PlacementScope.placeRelative$default(layout, placeable, 0, iRoundToInt, 0.0f, 4, null);
                            Placeable.PlacementScope.placeRelative$default(layout, placeable2, FabPosition.m1308equalsimpl0(i4, FabPosition.INSTANCE.m1312getCenter5ygKITE()) ? (placeableMo3396measureBRTryo0.getWidth() - placeable2.getWidth()) / 2 : (placeableMo3396measureBRTryo0.getWidth() - placeable2.getWidth()) - Layout.mo571roundToPx0680j_4(BottomSheetScaffoldKt.FabEndSpacing), iRoundToInt - (placeable2.getHeight() / 2), 0.0f, 4, null);
                            Placeable.PlacementScope.placeRelative$default(layout, placeable3, (placeableMo3396measureBRTryo0.getWidth() - placeable3.getWidth()) / 2, placeableMo3396measureBRTryo0.getHeight() - placeable3.getHeight(), 0.0f, 4, null);
                        }
                    }, 4, null);
                }
            };
            composerStartRestartGroup.startReplaceableGroup(1376089335);
            ComposerKt.sourceInformation(composerStartRestartGroup, "C(Layout)P(!1,2)71@2788L7,72@2843L7,73@2855L389:Layout.kt#80mrfh");
            Modifier.Companion companion = Modifier.INSTANCE;
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
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf = LayoutKt.materializerOf(companion);
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
            Updater.m1525setimpl(composerM1518constructorimpl, measurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m1525setimpl(composerM1518constructorimpl, density, ComposeUiNode.INSTANCE.getSetDensity());
            Updater.m1525setimpl(composerM1518constructorimpl, layoutDirection, ComposeUiNode.INSTANCE.getSetLayoutDirection());
            composerStartRestartGroup.enableReusing();
            function3MaterializerOf.invoke(SkippableUpdater.m1509boximpl(SkippableUpdater.m1510constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 0);
            composerStartRestartGroup.startReplaceableGroup(2058660585);
            composerStartRestartGroup.startReplaceableGroup(-526644304);
            ComposerKt.sourceInformation(composerStartRestartGroup, "C392@16275L6,393@16294L13,394@16320L22,395@16355L14:BottomSheetScaffold.kt#jmzs0o");
            function2.invoke(composerStartRestartGroup, Integer.valueOf(i3 & 14));
            function22.invoke(composerStartRestartGroup, Integer.valueOf((i3 >> 3) & 14));
            function23.invoke(composerStartRestartGroup, Integer.valueOf((i3 >> 6) & 14));
            function24.invoke(composerStartRestartGroup, Integer.valueOf((i3 >> 9) & 14));
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endNode();
            composerStartRestartGroup.endReplaceableGroup();
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.BottomSheetScaffoldKt$BottomSheetScaffoldStack$3
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
                BottomSheetScaffoldKt.m1199BottomSheetScaffoldStackSlNgfk0(function2, function22, function23, function24, state, i, composer2, i2 | 1);
            }
        });
    }
}
