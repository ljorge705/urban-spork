package androidx.compose.material;

import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Dp;
import com.facebook.react.uimanager.ViewProps;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jmrtd.lds.LDSFile;

/* compiled from: ModalBottomSheet.kt */
@Metadata(d1 = {"\u0000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u001a\u008c\u0001\u0010\u0000\u001a\u00020\u00012\u001c\u0010\u0002\u001a\u0018\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u0005¢\u0006\u0002\b\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u0012\u001a\u00020\u00102\u0011\u0010\u0013\u001a\r\u0012\u0004\u0012\u00020\u00010\u0014¢\u0006\u0002\b\u0005H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0015\u0010\u0016\u001a3\u0010\u0017\u001a\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u00102\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00010\u00142\u0006\u0010\u001a\u001a\u00020\u001bH\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001c\u0010\u001d\u001a;\u0010\u001e\u001a\u00020\n2\u0006\u0010\u001f\u001a\u00020 2\u000e\b\u0002\u0010!\u001a\b\u0012\u0004\u0012\u00020#0\"2\u0014\b\u0002\u0010$\u001a\u000e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\u001b0\u0003H\u0007¢\u0006\u0002\u0010%\u001a,\u0010&\u001a\u00020\b*\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010'\u001a\u00020#2\u000e\u0010(\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010#0)H\u0002\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006*"}, d2 = {"ModalBottomSheetLayout", "", "sheetContent", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "modifier", "Landroidx/compose/ui/Modifier;", "sheetState", "Landroidx/compose/material/ModalBottomSheetState;", "sheetShape", "Landroidx/compose/ui/graphics/Shape;", "sheetElevation", "Landroidx/compose/ui/unit/Dp;", "sheetBackgroundColor", "Landroidx/compose/ui/graphics/Color;", "sheetContentColor", "scrimColor", "content", "Lkotlin/Function0;", "ModalBottomSheetLayout-BzaUkTc", "(Lkotlin/jvm/functions/Function3;Landroidx/compose/ui/Modifier;Landroidx/compose/material/ModalBottomSheetState;Landroidx/compose/ui/graphics/Shape;FJJJLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "Scrim", "color", "onDismiss", ViewProps.VISIBLE, "", "Scrim-3J-VO9M", "(JLkotlin/jvm/functions/Function0;ZLandroidx/compose/runtime/Composer;I)V", "rememberModalBottomSheetState", "initialValue", "Landroidx/compose/material/ModalBottomSheetValue;", "animationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "", "confirmStateChange", "(Landroidx/compose/material/ModalBottomSheetValue;Landroidx/compose/animation/core/AnimationSpec;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material/ModalBottomSheetState;", "bottomSheetSwipeable", "fullHeight", "sheetHeightState", "Landroidx/compose/runtime/State;", "material_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class ModalBottomSheetKt {
    @ExperimentalMaterialApi
    public static final ModalBottomSheetState rememberModalBottomSheetState(final ModalBottomSheetValue initialValue, final AnimationSpec<Float> animationSpec, final Function1<? super ModalBottomSheetValue, Boolean> function1, Composer composer, int i, int i2) {
        Intrinsics.checkNotNullParameter(initialValue, "initialValue");
        composer.startReplaceableGroup(170040195);
        ComposerKt.sourceInformation(composer, "C(rememberModalBottomSheetState)P(2)189@6877L358:ModalBottomSheet.kt#jmzs0o");
        if ((i2 & 2) != 0) {
            animationSpec = SwipeableDefaults.INSTANCE.getAnimationSpec();
        }
        if ((i2 & 4) != 0) {
            function1 = new Function1<ModalBottomSheetValue, Boolean>() { // from class: androidx.compose.material.ModalBottomSheetKt.rememberModalBottomSheetState.1
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final boolean invoke2(ModalBottomSheetValue it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return true;
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Boolean invoke(ModalBottomSheetValue modalBottomSheetValue) {
                    return Boolean.valueOf(invoke2(modalBottomSheetValue));
                }
            };
        }
        ModalBottomSheetState modalBottomSheetState = (ModalBottomSheetState) RememberSaveableKt.m1531rememberSaveable(new Object[0], (Saver) ModalBottomSheetState.INSTANCE.Saver(animationSpec, function1), (String) null, (Function0) new Function0<ModalBottomSheetState>() { // from class: androidx.compose.material.ModalBottomSheetKt.rememberModalBottomSheetState.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ModalBottomSheetState invoke() {
                return new ModalBottomSheetState(initialValue, animationSpec, function1);
            }
        }, composer, 72, 4);
        composer.endReplaceableGroup();
        return modalBottomSheetState;
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0179  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x017e  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0184  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0189  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x019d  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x01a3  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x01b2  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x01b8  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x01c1  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x01c6  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x01d3  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x01d9  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x01e4  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x01ea  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x01f5  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x022c  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x029e  */
    /* JADX WARN: Removed duplicated region for block: B:164:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0116  */
    @androidx.compose.material.ExperimentalMaterialApi
    /* renamed from: ModalBottomSheetLayout-BzaUkTc, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1336ModalBottomSheetLayoutBzaUkTc(final kotlin.jvm.functions.Function3<? super androidx.compose.foundation.layout.ColumnScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r30, androidx.compose.ui.Modifier r31, androidx.compose.material.ModalBottomSheetState r32, androidx.compose.ui.graphics.Shape r33, float r34, long r35, long r37, long r39, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r41, androidx.compose.runtime.Composer r42, final int r43, final int r44) {
        /*
            Method dump skipped, instructions count: 693
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.ModalBottomSheetKt.m1336ModalBottomSheetLayoutBzaUkTc(kotlin.jvm.functions.Function3, androidx.compose.ui.Modifier, androidx.compose.material.ModalBottomSheetState, androidx.compose.ui.graphics.Shape, float, long, long, long, kotlin.jvm.functions.Function2, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Modifier bottomSheetSwipeable(Modifier modifier, ModalBottomSheetState modalBottomSheetState, float f, State<Float> state) {
        Modifier.Companion companionM1423swipeablepPrIpRY;
        Map mapMapOf;
        Float value = state.getValue();
        if (value == null) {
            companionM1423swipeablepPrIpRY = Modifier.INSTANCE;
        } else {
            float f2 = f / 2;
            if (value.floatValue() < f2) {
                mapMapOf = MapsKt.mapOf(TuplesKt.to(Float.valueOf(f), ModalBottomSheetValue.Hidden), TuplesKt.to(Float.valueOf(f - value.floatValue()), ModalBottomSheetValue.Expanded));
            } else {
                mapMapOf = MapsKt.mapOf(TuplesKt.to(Float.valueOf(f), ModalBottomSheetValue.Hidden), TuplesKt.to(Float.valueOf(f2), ModalBottomSheetValue.HalfExpanded), TuplesKt.to(Float.valueOf(Math.max(0.0f, f - value.floatValue())), ModalBottomSheetValue.Expanded));
            }
            Map map = mapMapOf;
            companionM1423swipeablepPrIpRY = SwipeableKt.m1423swipeablepPrIpRY(Modifier.INSTANCE, modalBottomSheetState, map, Orientation.Vertical, (32 & 8) != 0 ? true : modalBottomSheetState.getCurrentValue() != ModalBottomSheetValue.Hidden, (32 & 16) != 0 ? false : false, (32 & 32) != 0 ? null : null, (32 & 64) != 0 ? new Function2<T, T, FixedThreshold>() { // from class: androidx.compose.material.SwipeableKt$swipeable$1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function2
                public final FixedThreshold invoke(T t, T t2) {
                    return new FixedThreshold(Dp.m4390constructorimpl(56), null);
                }
            } : null, (32 & 128) != 0 ? SwipeableDefaults.resistanceConfig$default(SwipeableDefaults.INSTANCE, map.keySet(), 0.0f, 0.0f, 6, null) : null, (32 & 256) != 0 ? SwipeableDefaults.INSTANCE.m1422getVelocityThresholdD9Ej5fM() : 0.0f);
        }
        return modifier.then(companionM1423swipeablepPrIpRY);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Scrim-3J-VO9M, reason: not valid java name */
    public static final void m1337Scrim3JVO9M(final long j, final Function0<Unit> function0, final boolean z, Composer composer, final int i) {
        int i2;
        Modifier.Companion companionSemantics;
        Composer composerStartRestartGroup = composer.startRestartGroup(1010543443);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(Scrim)P(0:c#ui.graphics.Color):ModalBottomSheet.kt#jmzs0o");
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
            composerStartRestartGroup.startReplaceableGroup(1010543551);
            ComposerKt.sourceInformation(composerStartRestartGroup, "356@13430L121,360@13577L29,376@14092L62,372@13983L171");
            final State stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(z ? 1.0f : 0.0f, new TweenSpec(0, 0, null, 7, null), 0.0f, null, composerStartRestartGroup, 0, 12);
            final String strM1416getString4foXLRw = Strings_androidKt.m1416getString4foXLRw(Strings.INSTANCE.m1413getCloseSheetUdPEhr4(), composerStartRestartGroup, 0);
            if (z) {
                composerStartRestartGroup.startReplaceableGroup(1010543794);
                ComposerKt.sourceInformation(composerStartRestartGroup, "363@13714L37,364@13804L121");
                Modifier.Companion companion = Modifier.INSTANCE;
                composerStartRestartGroup.startReplaceableGroup(-3686930);
                ComposerKt.sourceInformation(composerStartRestartGroup, "C(remember)P(1):Composables.kt#9igjgp");
                boolean zChanged = composerStartRestartGroup.changed(function0);
                ModalBottomSheetKt$Scrim$dismissModifier$1$1 modalBottomSheetKt$Scrim$dismissModifier$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
                if (zChanged || modalBottomSheetKt$Scrim$dismissModifier$1$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                    modalBottomSheetKt$Scrim$dismissModifier$1$1RememberedValue = new ModalBottomSheetKt$Scrim$dismissModifier$1$1(function0, null);
                    composerStartRestartGroup.updateRememberedValue(modalBottomSheetKt$Scrim$dismissModifier$1$1RememberedValue);
                }
                composerStartRestartGroup.endReplaceableGroup();
                Modifier modifierPointerInput = SuspendingPointerInputFilterKt.pointerInput(companion, function0, (Function2<? super PointerInputScope, ? super Continuation<? super Unit>, ? extends Object>) modalBottomSheetKt$Scrim$dismissModifier$1$1RememberedValue);
                composerStartRestartGroup.startReplaceableGroup(-3686552);
                ComposerKt.sourceInformation(composerStartRestartGroup, "C(remember)P(1,2):Composables.kt#9igjgp");
                boolean zChanged2 = composerStartRestartGroup.changed(strM1416getString4foXLRw) | composerStartRestartGroup.changed(function0);
                Object objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (zChanged2 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material.ModalBottomSheetKt$Scrim$dismissModifier$2$1
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
                            SemanticsPropertiesKt.setContentDescription(semantics, strM1416getString4foXLRw);
                            final Function0<Unit> function02 = function0;
                            SemanticsPropertiesKt.onClick$default(semantics, null, new Function0<Boolean>() { // from class: androidx.compose.material.ModalBottomSheetKt$Scrim$dismissModifier$2$1.1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(0);
                                }

                                @Override // kotlin.jvm.functions.Function0
                                public /* bridge */ /* synthetic */ Boolean invoke() {
                                    return Boolean.valueOf(invoke2());
                                }

                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final boolean invoke2() {
                                    function02.invoke();
                                    return true;
                                }
                            }, 1, null);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                composerStartRestartGroup.endReplaceableGroup();
                companionSemantics = SemanticsModifierKt.semantics(modifierPointerInput, true, (Function1) objRememberedValue);
                composerStartRestartGroup.endReplaceableGroup();
            } else {
                composerStartRestartGroup.startReplaceableGroup(1010544085);
                composerStartRestartGroup.endReplaceableGroup();
                companionSemantics = Modifier.INSTANCE;
            }
            Modifier modifierThen = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null).then(companionSemantics);
            Color colorM1867boximpl = Color.m1867boximpl(j);
            composerStartRestartGroup.startReplaceableGroup(-3686552);
            ComposerKt.sourceInformation(composerStartRestartGroup, "C(remember)P(1,2):Composables.kt#9igjgp");
            boolean zChanged3 = composerStartRestartGroup.changed(colorM1867boximpl) | composerStartRestartGroup.changed(stateAnimateFloatAsState);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (zChanged3 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material.ModalBottomSheetKt$Scrim$1$1
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
                        DrawScope.m2412drawRectnJ9OG0$default(Canvas, j, 0L, 0L, ModalBottomSheetKt.m1338Scrim_3J_VO9M$lambda0(stateAnimateFloatAsState), null, null, 0, LDSFile.EF_DG4_TAG, null);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            composerStartRestartGroup.endReplaceableGroup();
            CanvasKt.Canvas(modifierThen, (Function1) objRememberedValue2, composerStartRestartGroup, 0);
            composerStartRestartGroup.endReplaceableGroup();
        } else {
            composerStartRestartGroup.startReplaceableGroup(1010544304);
            composerStartRestartGroup.endReplaceableGroup();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ModalBottomSheetKt$Scrim$2
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
                ModalBottomSheetKt.m1337Scrim3JVO9M(j, function0, z, composer2, i | 1);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Scrim_3J_VO9M$lambda-0, reason: not valid java name */
    public static final float m1338Scrim_3J_VO9M$lambda0(State<Float> state) {
        return state.getValue().floatValue();
    }
}
