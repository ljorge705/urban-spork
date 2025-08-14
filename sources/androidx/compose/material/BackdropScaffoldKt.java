package androidx.compose.material;

import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.ZIndexModifierKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.RectangleShapeKt;
import androidx.compose.ui.graphics.TransformOrigin;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.layout.SubcomposeLayoutKt;
import androidx.compose.ui.layout.SubcomposeMeasureScope;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.events.TouchesHelper;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import org.jmrtd.lds.LDSFile;

/* compiled from: BackdropScaffold.kt */
@Metadata(d1 = {"\u0000l\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a;\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0011\u0010\u0007\u001a\r\u0012\u0004\u0012\u00020\u00040\b¢\u0006\u0002\b\t2\u0011\u0010\n\u001a\r\u0012\u0004\u0012\u00020\u00040\b¢\u0006\u0002\b\tH\u0003¢\u0006\u0002\u0010\u000b\u001aõ\u0001\u0010\f\u001a\u00020\u00042\u0011\u0010\u0007\u001a\r\u0012\u0004\u0012\u00020\u00040\b¢\u0006\u0002\b\t2\u0011\u0010\r\u001a\r\u0012\u0004\u0012\u00020\u00040\b¢\u0006\u0002\b\t2\u0011\u0010\u000e\u001a\r\u0012\u0004\u0012\u00020\u00040\b¢\u0006\u0002\b\t2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00012\b\b\u0002\u0010\u0016\u001a\u00020\u00012\b\b\u0002\u0010\u0017\u001a\u00020\u00142\b\b\u0002\u0010\u0018\u001a\u00020\u00142\b\b\u0002\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u001a2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u00012\b\b\u0002\u0010\u001f\u001a\u00020\u001a2\b\b\u0002\u0010 \u001a\u00020\u001a2\b\b\u0002\u0010!\u001a\u00020\u001a2\u0019\b\u0002\u0010\"\u001a\u0013\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020\u00040#¢\u0006\u0002\b\tH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b%\u0010&\u001a^\u0010'\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00102\u0011\u0010(\u001a\r\u0012\u0004\u0012\u00020\u00040\b¢\u0006\u0002\b\t2\u0012\u0010)\u001a\u000e\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020*0#2\u001d\u0010+\u001a\u0019\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020-\u0012\u0004\u0012\u00020\u00040,¢\u0006\u0002\b\tH\u0003ø\u0001\u0000¢\u0006\u0002\u0010.\u001a3\u0010/\u001a\u00020\u00042\u0006\u00100\u001a\u00020\u001a2\f\u00101\u001a\b\u0012\u0004\u0012\u00020\u00040\b2\u0006\u00102\u001a\u00020\u0014H\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b3\u00104\u001aE\u00105\u001a\u00020\u00122\u0006\u00106\u001a\u00020\u00062\u000e\b\u0002\u00107\u001a\b\u0012\u0004\u0012\u00020-082\u0014\b\u0002\u00109\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00140#2\b\b\u0002\u0010:\u001a\u00020$H\u0007¢\u0006\u0002\u0010;\"\u0013\u0010\u0000\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006<"}, d2 = {"AnimationSlideOffset", "Landroidx/compose/ui/unit/Dp;", "F", "BackLayerTransition", "", TouchesHelper.TARGET_KEY, "Landroidx/compose/material/BackdropValue;", "appBar", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "content", "(Landroidx/compose/material/BackdropValue;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "BackdropScaffold", "backLayerContent", "frontLayerContent", "modifier", "Landroidx/compose/ui/Modifier;", "scaffoldState", "Landroidx/compose/material/BackdropScaffoldState;", "gesturesEnabled", "", "peekHeight", "headerHeight", "persistentAppBar", "stickyFrontLayer", "backLayerBackgroundColor", "Landroidx/compose/ui/graphics/Color;", "backLayerContentColor", "frontLayerShape", "Landroidx/compose/ui/graphics/Shape;", "frontLayerElevation", "frontLayerBackgroundColor", "frontLayerContentColor", "frontLayerScrimColor", "snackbarHost", "Lkotlin/Function1;", "Landroidx/compose/material/SnackbarHostState;", "BackdropScaffold-BZszfkY", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;Landroidx/compose/material/BackdropScaffoldState;ZFFZZJJLandroidx/compose/ui/graphics/Shape;FJJJLkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "BackdropStack", "backLayer", "calculateBackLayerConstraints", "Landroidx/compose/ui/unit/Constraints;", "frontLayer", "Lkotlin/Function2;", "", "(Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function4;Landroidx/compose/runtime/Composer;I)V", "Scrim", "color", "onDismiss", ViewProps.VISIBLE, "Scrim-3J-VO9M", "(JLkotlin/jvm/functions/Function0;ZLandroidx/compose/runtime/Composer;I)V", "rememberBackdropScaffoldState", "initialValue", "animationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "confirmStateChange", "snackbarHostState", "(Landroidx/compose/material/BackdropValue;Landroidx/compose/animation/core/AnimationSpec;Lkotlin/jvm/functions/Function1;Landroidx/compose/material/SnackbarHostState;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material/BackdropScaffoldState;", "material_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class BackdropScaffoldKt {
    private static final float AnimationSlideOffset = Dp.m4390constructorimpl(20);

    @ExperimentalMaterialApi
    public static final BackdropScaffoldState rememberBackdropScaffoldState(final BackdropValue initialValue, final AnimationSpec<Float> animationSpec, final Function1<? super BackdropValue, Boolean> function1, final SnackbarHostState snackbarHostState, Composer composer, int i, int i2) {
        Intrinsics.checkNotNullParameter(initialValue, "initialValue");
        composer.startReplaceableGroup(380713820);
        ComposerKt.sourceInformation(composer, "C(rememberBackdropScaffoldState)P(2)170@6407L32,172@6478L538:BackdropScaffold.kt#jmzs0o");
        if ((i2 & 2) != 0) {
            animationSpec = SwipeableDefaults.INSTANCE.getAnimationSpec();
        }
        if ((i2 & 4) != 0) {
            function1 = new Function1<BackdropValue, Boolean>() { // from class: androidx.compose.material.BackdropScaffoldKt.rememberBackdropScaffoldState.1
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final boolean invoke2(BackdropValue it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return true;
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Boolean invoke(BackdropValue backdropValue) {
                    return Boolean.valueOf(invoke2(backdropValue));
                }
            };
        }
        if ((i2 & 8) != 0) {
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
        BackdropScaffoldState backdropScaffoldState = (BackdropScaffoldState) RememberSaveableKt.m1531rememberSaveable(new Object[]{animationSpec, function1, snackbarHostState}, (Saver) BackdropScaffoldState.INSTANCE.Saver(animationSpec, function1, snackbarHostState), (String) null, (Function0) new Function0<BackdropScaffoldState>() { // from class: androidx.compose.material.BackdropScaffoldKt.rememberBackdropScaffoldState.3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BackdropScaffoldState invoke() {
                return new BackdropScaffoldState(initialValue, animationSpec, function1, snackbarHostState);
            }
        }, composer, 72, 4);
        composer.endReplaceableGroup();
        return backdropScaffoldState;
    }

    /* JADX WARN: Removed duplicated region for block: B:109:0x015a  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x016e  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0175  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0189  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x018f  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x01a5  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x01ab  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x01c1  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x01ca  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x01dc  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x01e3  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x01f8  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x01ff  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x0212  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x0219  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x022f  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x023e  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x026f  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x02f1  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x02f6  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x02ff  */
    /* JADX WARN: Removed duplicated region for block: B:243:0x031b  */
    /* JADX WARN: Removed duplicated region for block: B:245:0x0322  */
    /* JADX WARN: Removed duplicated region for block: B:246:0x0324  */
    /* JADX WARN: Removed duplicated region for block: B:249:0x032a  */
    /* JADX WARN: Removed duplicated region for block: B:250:0x0333  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x0339  */
    /* JADX WARN: Removed duplicated region for block: B:254:0x0342  */
    /* JADX WARN: Removed duplicated region for block: B:256:0x0346  */
    /* JADX WARN: Removed duplicated region for block: B:257:0x0348  */
    /* JADX WARN: Removed duplicated region for block: B:259:0x034c  */
    /* JADX WARN: Removed duplicated region for block: B:260:0x034e  */
    /* JADX WARN: Removed duplicated region for block: B:263:0x0355  */
    /* JADX WARN: Removed duplicated region for block: B:264:0x0364  */
    /* JADX WARN: Removed duplicated region for block: B:267:0x036a  */
    /* JADX WARN: Removed duplicated region for block: B:268:0x0373  */
    /* JADX WARN: Removed duplicated region for block: B:271:0x037d  */
    /* JADX WARN: Removed duplicated region for block: B:272:0x0387  */
    /* JADX WARN: Removed duplicated region for block: B:275:0x038d  */
    /* JADX WARN: Removed duplicated region for block: B:276:0x0396  */
    /* JADX WARN: Removed duplicated region for block: B:279:0x039e  */
    /* JADX WARN: Removed duplicated region for block: B:280:0x03ae  */
    /* JADX WARN: Removed duplicated region for block: B:283:0x03b9  */
    /* JADX WARN: Removed duplicated region for block: B:284:0x03c6  */
    /* JADX WARN: Removed duplicated region for block: B:287:0x03cc  */
    /* JADX WARN: Removed duplicated region for block: B:288:0x03d8  */
    /* JADX WARN: Removed duplicated region for block: B:291:0x03e0  */
    /* JADX WARN: Removed duplicated region for block: B:292:0x03e9  */
    /* JADX WARN: Removed duplicated region for block: B:298:0x047b  */
    /* JADX WARN: Removed duplicated region for block: B:303:0x0512  */
    /* JADX WARN: Removed duplicated region for block: B:305:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x010a  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0110  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0115  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x013a  */
    @androidx.compose.material.ExperimentalMaterialApi
    /* renamed from: BackdropScaffold-BZszfkY, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1173BackdropScaffoldBZszfkY(final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r50, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r51, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r52, androidx.compose.ui.Modifier r53, androidx.compose.material.BackdropScaffoldState r54, boolean r55, float r56, float r57, boolean r58, boolean r59, long r60, long r62, androidx.compose.ui.graphics.Shape r64, float r65, long r66, long r68, long r70, kotlin.jvm.functions.Function3<? super androidx.compose.material.SnackbarHostState, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r72, androidx.compose.runtime.Composer r73, final int r74, final int r75, final int r76) {
        /*
            Method dump skipped, instructions count: 1329
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.BackdropScaffoldKt.m1173BackdropScaffoldBZszfkY(kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, androidx.compose.ui.Modifier, androidx.compose.material.BackdropScaffoldState, boolean, float, float, boolean, boolean, long, long, androidx.compose.ui.graphics.Shape, float, long, long, long, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Scrim-3J-VO9M, reason: not valid java name */
    public static final void m1174Scrim3JVO9M(final long j, final Function0<Unit> function0, final boolean z, Composer composer, final int i) {
        int i2;
        Modifier.Companion companionPointerInput;
        Composer composerStartRestartGroup = composer.startRestartGroup(1010546681);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(Scrim)P(0:c#ui.graphics.Color):BackdropScaffold.kt#jmzs0o");
        if ((i & 14) == 0) {
            i2 = (composerStartRestartGroup.changed(j) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 112) == 0) {
            i2 |= composerStartRestartGroup.changed(function0) ? 32 : 16;
        }
        if ((i & 896) == 0) {
            i2 |= composerStartRestartGroup.changed(z) ? 256 : 128;
        }
        if (((i2 & 731) ^ 146) == 0 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else if (j != Color.INSTANCE.m1913getUnspecified0d7_KjU()) {
            composerStartRestartGroup.startReplaceableGroup(1010546789);
            ComposerKt.sourceInformation(composerStartRestartGroup, "387@16668L121,400@17078L62,396@16969L171");
            final State stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(z ? 1.0f : 0.0f, new TweenSpec(0, 0, null, 7, null), 0.0f, null, composerStartRestartGroup, 0, 12);
            if (z) {
                composerStartRestartGroup.startReplaceableGroup(1010546977);
                ComposerKt.sourceInformation(composerStartRestartGroup, "392@16875L37");
                Modifier.Companion companion = Modifier.INSTANCE;
                Unit unit = Unit.INSTANCE;
                composerStartRestartGroup.startReplaceableGroup(-3686930);
                ComposerKt.sourceInformation(composerStartRestartGroup, "C(remember)P(1):Composables.kt#9igjgp");
                boolean zChanged = composerStartRestartGroup.changed(function0);
                BackdropScaffoldKt$Scrim$dismissModifier$1$1 backdropScaffoldKt$Scrim$dismissModifier$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
                if (zChanged || backdropScaffoldKt$Scrim$dismissModifier$1$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                    backdropScaffoldKt$Scrim$dismissModifier$1$1RememberedValue = new BackdropScaffoldKt$Scrim$dismissModifier$1$1(function0, null);
                    composerStartRestartGroup.updateRememberedValue(backdropScaffoldKt$Scrim$dismissModifier$1$1RememberedValue);
                }
                composerStartRestartGroup.endReplaceableGroup();
                companionPointerInput = SuspendingPointerInputFilterKt.pointerInput(companion, unit, (Function2<? super PointerInputScope, ? super Continuation<? super Unit>, ? extends Object>) backdropScaffoldKt$Scrim$dismissModifier$1$1RememberedValue);
                composerStartRestartGroup.endReplaceableGroup();
            } else {
                composerStartRestartGroup.startReplaceableGroup(1010547072);
                composerStartRestartGroup.endReplaceableGroup();
                companionPointerInput = Modifier.INSTANCE;
            }
            Modifier modifierThen = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null).then(companionPointerInput);
            Color colorM1867boximpl = Color.m1867boximpl(j);
            composerStartRestartGroup.startReplaceableGroup(-3686552);
            ComposerKt.sourceInformation(composerStartRestartGroup, "C(remember)P(1,2):Composables.kt#9igjgp");
            boolean zChanged2 = composerStartRestartGroup.changed(colorM1867boximpl) | composerStartRestartGroup.changed(stateAnimateFloatAsState);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged2 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material.BackdropScaffoldKt$Scrim$1$1
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
                        DrawScope.m2412drawRectnJ9OG0$default(Canvas, j, 0L, 0L, BackdropScaffoldKt.m1175Scrim_3J_VO9M$lambda4(stateAnimateFloatAsState), null, null, 0, LDSFile.EF_DG4_TAG, null);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            composerStartRestartGroup.endReplaceableGroup();
            CanvasKt.Canvas(modifierThen, (Function1) objRememberedValue, composerStartRestartGroup, 0);
            composerStartRestartGroup.endReplaceableGroup();
        } else {
            composerStartRestartGroup.startReplaceableGroup(1010547290);
            composerStartRestartGroup.endReplaceableGroup();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.BackdropScaffoldKt$Scrim$2
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
                BackdropScaffoldKt.m1174Scrim3JVO9M(j, function0, z, composer2, i | 1);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void BackLayerTransition(final BackdropValue backdropValue, final Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function22, Composer composer, final int i) {
        int i2;
        final Function2<? super Composer, ? super Integer, Unit> function23 = function22;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1142038671);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(BackLayerTransition)P(2)420@17800L112,*423@17962L7,428@18136L486:BackdropScaffold.kt#jmzs0o");
        if ((i & 14) == 0) {
            i2 = (composerStartRestartGroup.changed(backdropValue) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 112) == 0) {
            i2 |= composerStartRestartGroup.changed(function2) ? 32 : 16;
        }
        if ((i & 896) == 0) {
            i2 |= composerStartRestartGroup.changed(function23) ? 256 : 128;
        }
        int i3 = i2;
        if (((i3 & 731) ^ 146) != 0 || !composerStartRestartGroup.getSkipping()) {
            State stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(backdropValue == BackdropValue.Revealed ? 0.0f : 2.0f, new TweenSpec(0, 0, null, 7, null), 0.0f, null, composerStartRestartGroup, 0, 12);
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 103361330, "C:CompositionLocal.kt#9igjgp");
            Object objConsume = composerStartRestartGroup.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            float fMo577toPx0680j_4 = ((Density) objConsume).mo577toPx0680j_4(AnimationSlideOffset);
            float f = 1;
            float fCoerceIn = RangesKt.coerceIn(m1172BackLayerTransition$lambda7(stateAnimateFloatAsState) - f, 0.0f, 1.0f);
            float fCoerceIn2 = RangesKt.coerceIn(f - m1172BackLayerTransition$lambda7(stateAnimateFloatAsState), 0.0f, 1.0f);
            composerStartRestartGroup.startReplaceableGroup(-1990474327);
            ComposerKt.sourceInformation(composerStartRestartGroup, "C(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
            Modifier.Companion companion = Modifier.INSTANCE;
            MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false, composerStartRestartGroup, 0);
            composerStartRestartGroup.startReplaceableGroup(1376089335);
            ComposerKt.sourceInformation(composerStartRestartGroup, "C(Layout)P(!1,2)71@2788L7,72@2843L7,73@2855L389:Layout.kt#80mrfh");
            ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 103361330, "C:CompositionLocal.kt#9igjgp");
            Object objConsume2 = composerStartRestartGroup.consume(localDensity2);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Density density = (Density) objConsume2;
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 103361330, "C:CompositionLocal.kt#9igjgp");
            Object objConsume3 = composerStartRestartGroup.consume(localLayoutDirection);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            LayoutDirection layoutDirection = (LayoutDirection) objConsume3;
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
            Updater.m1525setimpl(composerM1518constructorimpl, measurePolicyRememberBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m1525setimpl(composerM1518constructorimpl, density, ComposeUiNode.INSTANCE.getSetDensity());
            Updater.m1525setimpl(composerM1518constructorimpl, layoutDirection, ComposeUiNode.INSTANCE.getSetLayoutDirection());
            composerStartRestartGroup.enableReusing();
            function3MaterializerOf.invoke(SkippableUpdater.m1509boximpl(SkippableUpdater.m1510constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 0);
            composerStartRestartGroup.startReplaceableGroup(2058660585);
            composerStartRestartGroup.startReplaceableGroup(-1253629305);
            ComposerKt.sourceInformation(composerStartRestartGroup, "C72@3384L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            composerStartRestartGroup.startReplaceableGroup(-1538629202);
            ComposerKt.sourceInformation(composerStartRestartGroup, "C429@18150L226,437@18385L231:BackdropScaffold.kt#jmzs0o");
            Modifier modifierM2026graphicsLayersKFY_QE = GraphicsLayerModifierKt.m2026graphicsLayersKFY_QE(ZIndexModifierKt.zIndex(Modifier.INSTANCE, fCoerceIn), (8184 & 1) != 0 ? 1.0f : 0.0f, (8184 & 2) != 0 ? 1.0f : 0.0f, (8184 & 4) == 0 ? fCoerceIn : 1.0f, (8184 & 8) != 0 ? 0.0f : 0.0f, (8184 & 16) != 0 ? 0.0f : (f - fCoerceIn) * fMo577toPx0680j_4, (8184 & 32) != 0 ? 0.0f : 0.0f, (8184 & 64) != 0 ? 0.0f : 0.0f, (8184 & 128) != 0 ? 0.0f : 0.0f, (8184 & 256) == 0 ? 0.0f : 0.0f, (8184 & 512) != 0 ? 8.0f : 0.0f, (8184 & 1024) != 0 ? TransformOrigin.INSTANCE.m2258getCenterSzJe1aQ() : 0L, (8184 & 2048) != 0 ? RectangleShapeKt.getRectangleShape() : null, (8184 & 4096) != 0 ? false : false);
            composerStartRestartGroup.startReplaceableGroup(-1990474327);
            ComposerKt.sourceInformation(composerStartRestartGroup, "C(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyRememberBoxMeasurePolicy2 = BoxKt.rememberBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false, composerStartRestartGroup, 0);
            composerStartRestartGroup.startReplaceableGroup(1376089335);
            ComposerKt.sourceInformation(composerStartRestartGroup, "C(Layout)P(!1,2)71@2788L7,72@2843L7,73@2855L389:Layout.kt#80mrfh");
            ProvidableCompositionLocal<Density> localDensity3 = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 103361330, "C:CompositionLocal.kt#9igjgp");
            Object objConsume4 = composerStartRestartGroup.consume(localDensity3);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Density density2 = (Density) objConsume4;
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection2 = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 103361330, "C:CompositionLocal.kt#9igjgp");
            Object objConsume5 = composerStartRestartGroup.consume(localLayoutDirection2);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            LayoutDirection layoutDirection2 = (LayoutDirection) objConsume5;
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf2 = LayoutKt.materializerOf(modifierM2026graphicsLayersKFY_QE);
            if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor2);
            } else {
                composerStartRestartGroup.useNode();
            }
            composerStartRestartGroup.disableReusing();
            Composer composerM1518constructorimpl2 = Updater.m1518constructorimpl(composerStartRestartGroup);
            Updater.m1525setimpl(composerM1518constructorimpl2, measurePolicyRememberBoxMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m1525setimpl(composerM1518constructorimpl2, density2, ComposeUiNode.INSTANCE.getSetDensity());
            Updater.m1525setimpl(composerM1518constructorimpl2, layoutDirection2, ComposeUiNode.INSTANCE.getSetLayoutDirection());
            composerStartRestartGroup.enableReusing();
            function3MaterializerOf2.invoke(SkippableUpdater.m1509boximpl(SkippableUpdater.m1510constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 0);
            composerStartRestartGroup.startReplaceableGroup(2058660585);
            composerStartRestartGroup.startReplaceableGroup(-1253629305);
            ComposerKt.sourceInformation(composerStartRestartGroup, "C72@3384L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
            composerStartRestartGroup.startReplaceableGroup(-481855329);
            ComposerKt.sourceInformation(composerStartRestartGroup, "C435@18358L8:BackdropScaffold.kt#jmzs0o");
            function2.invoke(composerStartRestartGroup, Integer.valueOf((i3 >> 3) & 14));
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endNode();
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endReplaceableGroup();
            Modifier modifierM2026graphicsLayersKFY_QE2 = GraphicsLayerModifierKt.m2026graphicsLayersKFY_QE(ZIndexModifierKt.zIndex(Modifier.INSTANCE, fCoerceIn2), (8184 & 1) != 0 ? 1.0f : 0.0f, (8184 & 2) != 0 ? 1.0f : 0.0f, (8184 & 4) == 0 ? fCoerceIn2 : 1.0f, (8184 & 8) != 0 ? 0.0f : 0.0f, (8184 & 16) != 0 ? 0.0f : (f - fCoerceIn2) * (-fMo577toPx0680j_4), (8184 & 32) != 0 ? 0.0f : 0.0f, (8184 & 64) != 0 ? 0.0f : 0.0f, (8184 & 128) != 0 ? 0.0f : 0.0f, (8184 & 256) == 0 ? 0.0f : 0.0f, (8184 & 512) != 0 ? 8.0f : 0.0f, (8184 & 1024) != 0 ? TransformOrigin.INSTANCE.m2258getCenterSzJe1aQ() : 0L, (8184 & 2048) != 0 ? RectangleShapeKt.getRectangleShape() : null, (8184 & 4096) != 0 ? false : false);
            composerStartRestartGroup.startReplaceableGroup(-1990474327);
            ComposerKt.sourceInformation(composerStartRestartGroup, "C(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyRememberBoxMeasurePolicy3 = BoxKt.rememberBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false, composerStartRestartGroup, 0);
            composerStartRestartGroup.startReplaceableGroup(1376089335);
            ComposerKt.sourceInformation(composerStartRestartGroup, "C(Layout)P(!1,2)71@2788L7,72@2843L7,73@2855L389:Layout.kt#80mrfh");
            ProvidableCompositionLocal<Density> localDensity4 = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 103361330, "C:CompositionLocal.kt#9igjgp");
            Object objConsume6 = composerStartRestartGroup.consume(localDensity4);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Density density3 = (Density) objConsume6;
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection3 = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 103361330, "C:CompositionLocal.kt#9igjgp");
            Object objConsume7 = composerStartRestartGroup.consume(localLayoutDirection3);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            LayoutDirection layoutDirection3 = (LayoutDirection) objConsume7;
            Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf3 = LayoutKt.materializerOf(modifierM2026graphicsLayersKFY_QE2);
            if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor3);
            } else {
                composerStartRestartGroup.useNode();
            }
            composerStartRestartGroup.disableReusing();
            Composer composerM1518constructorimpl3 = Updater.m1518constructorimpl(composerStartRestartGroup);
            Updater.m1525setimpl(composerM1518constructorimpl3, measurePolicyRememberBoxMeasurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m1525setimpl(composerM1518constructorimpl3, density3, ComposeUiNode.INSTANCE.getSetDensity());
            Updater.m1525setimpl(composerM1518constructorimpl3, layoutDirection3, ComposeUiNode.INSTANCE.getSetLayoutDirection());
            composerStartRestartGroup.enableReusing();
            function3MaterializerOf3.invoke(SkippableUpdater.m1509boximpl(SkippableUpdater.m1510constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 0);
            composerStartRestartGroup.startReplaceableGroup(2058660585);
            composerStartRestartGroup.startReplaceableGroup(-1253629305);
            ComposerKt.sourceInformation(composerStartRestartGroup, "C72@3384L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
            composerStartRestartGroup.startReplaceableGroup(-481855090);
            ComposerKt.sourceInformation(composerStartRestartGroup, "C443@18597L9:BackdropScaffold.kt#jmzs0o");
            function23 = function22;
            function23.invoke(composerStartRestartGroup, Integer.valueOf((i3 >> 6) & 14));
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endNode();
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endReplaceableGroup();
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
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.BackdropScaffoldKt.BackLayerTransition.2
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
                BackdropScaffoldKt.BackLayerTransition(backdropValue, function2, function23, composer2, i | 1);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void BackdropStack(final Modifier modifier, final Function2<? super Composer, ? super Integer, Unit> function2, final Function1<? super Constraints, Constraints> function1, final Function4<? super Constraints, ? super Float, ? super Composer, ? super Integer, Unit> function4, Composer composer, final int i) {
        final int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(1200747690);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(BackdropStack)P(3)455@18885L890,455@18858L917:BackdropScaffold.kt#jmzs0o");
        if ((i & 14) == 0) {
            i2 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 112) == 0) {
            i2 |= composerStartRestartGroup.changed(function2) ? 32 : 16;
        }
        if ((i & 896) == 0) {
            i2 |= composerStartRestartGroup.changed(function1) ? 256 : 128;
        }
        if ((i & 7168) == 0) {
            i2 |= composerStartRestartGroup.changed(function4) ? 2048 : 1024;
        }
        if (((i2 & 5851) ^ 1170) != 0 || !composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.startReplaceableGroup(-3686095);
            ComposerKt.sourceInformation(composerStartRestartGroup, "C(remember)P(1,2,3):Composables.kt#9igjgp");
            boolean zChanged = composerStartRestartGroup.changed(function2) | composerStartRestartGroup.changed(function1) | composerStartRestartGroup.changed(function4);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = (Function2) new Function2<SubcomposeMeasureScope, Constraints, MeasureResult>() { // from class: androidx.compose.material.BackdropScaffoldKt$BackdropStack$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ MeasureResult invoke(SubcomposeMeasureScope subcomposeMeasureScope, Constraints constraints) {
                        return m1181invoke0kLqBqw(subcomposeMeasureScope, constraints.getValue());
                    }

                    /* renamed from: invoke-0kLqBqw, reason: not valid java name */
                    public final MeasureResult m1181invoke0kLqBqw(SubcomposeMeasureScope SubcomposeLayout, final long j) {
                        Intrinsics.checkNotNullParameter(SubcomposeLayout, "$this$SubcomposeLayout");
                        final Placeable placeableMo3396measureBRTryo0 = ((Measurable) CollectionsKt.first((List) SubcomposeLayout.subcompose(BackdropLayers.Back, function2))).mo3396measureBRTryo0(function1.invoke(Constraints.m4334boximpl(j)).getValue());
                        final float height = placeableMo3396measureBRTryo0.getHeight();
                        BackdropLayers backdropLayers = BackdropLayers.Front;
                        final Function4<Constraints, Float, Composer, Integer, Unit> function42 = function4;
                        final int i3 = i2;
                        List<Measurable> listSubcompose = SubcomposeLayout.subcompose(backdropLayers, ComposableLambdaKt.composableLambdaInstance(-985548218, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.BackdropScaffoldKt$BackdropStack$1$1$placeables$1
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
                                ComposerKt.sourceInformation(composer2, "C464@19223L40:BackdropScaffold.kt#jmzs0o");
                                if (((i4 & 11) ^ 2) == 0 && composer2.getSkipping()) {
                                    composer2.skipToGroupEnd();
                                } else {
                                    function42.invoke(Constraints.m4334boximpl(j), Float.valueOf(height), composer2, Integer.valueOf((i3 >> 3) & 896));
                                }
                            }
                        }));
                        ArrayList arrayList = new ArrayList(listSubcompose.size());
                        int size = listSubcompose.size() - 1;
                        int i4 = 0;
                        if (size >= 0) {
                            int i5 = 0;
                            while (true) {
                                int i6 = i5 + 1;
                                arrayList.add(listSubcompose.get(i5).mo3396measureBRTryo0(j));
                                if (i6 > size) {
                                    break;
                                }
                                i5 = i6;
                            }
                        }
                        final ArrayList arrayList2 = arrayList;
                        int iMax = Math.max(Constraints.m4348getMinWidthimpl(j), placeableMo3396measureBRTryo0.getWidth());
                        int iMax2 = Math.max(Constraints.m4347getMinHeightimpl(j), placeableMo3396measureBRTryo0.getHeight());
                        int size2 = arrayList2.size() - 1;
                        if (size2 >= 0) {
                            while (true) {
                                int i7 = i4 + 1;
                                Placeable placeable = (Placeable) arrayList2.get(i4);
                                iMax = Math.max(iMax, placeable.getWidth());
                                iMax2 = Math.max(iMax2, placeable.getHeight());
                                if (i7 > size2) {
                                    break;
                                }
                                i4 = i7;
                            }
                        }
                        return MeasureScope.layout$default(SubcomposeLayout, iMax, iMax2, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material.BackdropScaffoldKt$BackdropStack$1$1.2
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
                                List<Placeable> list = arrayList2;
                                int size3 = list.size() - 1;
                                if (size3 < 0) {
                                    return;
                                }
                                int i8 = 0;
                                while (true) {
                                    int i9 = i8 + 1;
                                    Placeable.PlacementScope.placeRelative$default(layout, list.get(i8), 0, 0, 0.0f, 4, null);
                                    if (i9 > size3) {
                                        return;
                                    } else {
                                        i8 = i9;
                                    }
                                }
                            }
                        }, 4, null);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            composerStartRestartGroup.endReplaceableGroup();
            SubcomposeLayoutKt.SubcomposeLayout(modifier, (Function2) objRememberedValue, composerStartRestartGroup, i2 & 14, 0);
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.BackdropScaffoldKt.BackdropStack.2
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

            public final void invoke(Composer composer2, int i3) {
                BackdropScaffoldKt.BackdropStack(modifier, function2, function1, function4, composer2, i | 1);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Scrim_3J_VO9M$lambda-4, reason: not valid java name */
    public static final float m1175Scrim_3J_VO9M$lambda4(State<Float> state) {
        return state.getValue().floatValue();
    }

    /* renamed from: BackLayerTransition$lambda-7, reason: not valid java name */
    private static final float m1172BackLayerTransition$lambda7(State<Float> state) {
        return state.getValue().floatValue();
    }
}
