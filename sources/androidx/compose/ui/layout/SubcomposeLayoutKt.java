package androidx.compose.ui.layout;

import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionContext;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SubcomposeLayout.kt */
@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u001a9\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u001d\u0010\u0004\u001a\u0019\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0005¢\u0006\u0002\b\tH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\n\u001aA\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u001d\u0010\u0004\u001a\u0019\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0005¢\u0006\u0002\b\tH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\r\u001a\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"}, d2 = {"SubcomposeLayout", "", "modifier", "Landroidx/compose/ui/Modifier;", "measurePolicy", "Lkotlin/Function2;", "Landroidx/compose/ui/layout/SubcomposeMeasureScope;", "Landroidx/compose/ui/unit/Constraints;", "Landroidx/compose/ui/layout/MeasureResult;", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "state", "Landroidx/compose/ui/layout/SubcomposeLayoutState;", "(Landroidx/compose/ui/layout/SubcomposeLayoutState;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "SubcomposeSlotReusePolicy", "Landroidx/compose/ui/layout/SubcomposeSlotReusePolicy;", "maxSlotsToRetainForReuse", "", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class SubcomposeLayoutKt {
    public static final void SubcomposeLayout(final Modifier modifier, final Function2<? super SubcomposeMeasureScope, ? super Constraints, ? extends MeasureResult> measurePolicy, Composer composer, final int i, final int i2) {
        int i3;
        Intrinsics.checkNotNullParameter(measurePolicy, "measurePolicy");
        Composer composerStartRestartGroup = composer.startRestartGroup(-1298353104);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(SubcomposeLayout)P(1)75@3400L36,74@3366L144:SubcomposeLayout.kt#80mrfh");
        int i4 = i2 & 1;
        if (i4 != 0) {
            i3 = i | 6;
        } else if ((i & 14) == 0) {
            i3 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i2 & 2) != 0) {
            i3 |= 48;
        } else if ((i & 112) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(measurePolicy) ? 32 : 16;
        }
        if ((i3 & 91) != 18 || !composerStartRestartGroup.getSkipping()) {
            if (i4 != 0) {
                modifier = Modifier.INSTANCE;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1298353104, i3, -1, "androidx.compose.ui.layout.SubcomposeLayout (SubcomposeLayout.kt:70)");
            }
            composerStartRestartGroup.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(remember):Composables.kt#9igjgp");
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new SubcomposeLayoutState();
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            composerStartRestartGroup.endReplaceableGroup();
            SubcomposeLayoutState subcomposeLayoutState = (SubcomposeLayoutState) objRememberedValue;
            int i5 = i3 << 3;
            SubcomposeLayout(subcomposeLayoutState, modifier, measurePolicy, composerStartRestartGroup, (i5 & 112) | 8 | (i5 & 896), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.layout.SubcomposeLayoutKt.SubcomposeLayout.2
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

            public final void invoke(Composer composer2, int i6) {
                SubcomposeLayoutKt.SubcomposeLayout(modifier, measurePolicy, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
            }
        });
    }

    public static final void SubcomposeLayout(final SubcomposeLayoutState state, Modifier modifier, final Function2<? super SubcomposeMeasureScope, ? super Constraints, ? extends MeasureResult> measurePolicy, Composer composer, final int i, final int i2) {
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(measurePolicy, "measurePolicy");
        Composer composerStartRestartGroup = composer.startRestartGroup(-511989831);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(SubcomposeLayout)P(2,1)108@4858L28,110@4979L7,111@5034L7,112@5093L7,113@5105L519,130@5764L27,131@5819L89,131@5796L112:SubcomposeLayout.kt#80mrfh");
        if ((i2 & 2) != 0) {
            modifier = Modifier.INSTANCE;
        }
        final Modifier modifier2 = modifier;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-511989831, i, -1, "androidx.compose.ui.layout.SubcomposeLayout (SubcomposeLayout.kt:103)");
        }
        CompositionContext compositionContextRememberCompositionContext = ComposablesKt.rememberCompositionContext(composerStartRestartGroup, 0);
        Modifier modifierMaterialize = ComposedModifierKt.materialize(composerStartRestartGroup, modifier2);
        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = composerStartRestartGroup.consume(localDensity);
        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
        Density density = (Density) objConsume;
        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume2 = composerStartRestartGroup.consume(localLayoutDirection);
        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
        LayoutDirection layoutDirection = (LayoutDirection) objConsume2;
        ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume3 = composerStartRestartGroup.consume(localViewConfiguration);
        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
        ViewConfiguration viewConfiguration = (ViewConfiguration) objConsume3;
        final Function0<LayoutNode> constructor$ui_release = LayoutNode.INSTANCE.getConstructor$ui_release();
        composerStartRestartGroup.startReplaceableGroup(1886828752);
        ComposerKt.sourceInformation(composerStartRestartGroup, "CC(ComposeNode):Composables.kt#9igjgp");
        if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
            ComposablesKt.invalidApplier();
        }
        composerStartRestartGroup.startNode();
        if (composerStartRestartGroup.getInserting()) {
            composerStartRestartGroup.createNode(new Function0<LayoutNode>() { // from class: androidx.compose.ui.layout.SubcomposeLayoutKt$SubcomposeLayout$$inlined$ComposeNode$1
                {
                    super(0);
                }

                /* JADX WARN: Type inference failed for: r0v1, types: [androidx.compose.ui.node.LayoutNode, java.lang.Object] */
                @Override // kotlin.jvm.functions.Function0
                public final LayoutNode invoke() {
                    return constructor$ui_release.invoke();
                }
            });
        } else {
            composerStartRestartGroup.useNode();
        }
        Composer composerM1518constructorimpl = Updater.m1518constructorimpl(composerStartRestartGroup);
        Updater.m1525setimpl(composerM1518constructorimpl, state, state.getSetRoot$ui_release());
        Updater.m1525setimpl(composerM1518constructorimpl, compositionContextRememberCompositionContext, state.getSetCompositionContext$ui_release());
        Updater.m1525setimpl(composerM1518constructorimpl, measurePolicy, state.getSetMeasurePolicy$ui_release());
        Updater.m1525setimpl(composerM1518constructorimpl, density, ComposeUiNode.INSTANCE.getSetDensity());
        Updater.m1525setimpl(composerM1518constructorimpl, layoutDirection, ComposeUiNode.INSTANCE.getSetLayoutDirection());
        Updater.m1525setimpl(composerM1518constructorimpl, viewConfiguration, ComposeUiNode.INSTANCE.getSetViewConfiguration());
        Updater.m1525setimpl(composerM1518constructorimpl, modifierMaterialize, ComposeUiNode.INSTANCE.getSetModifier());
        composerStartRestartGroup.endNode();
        composerStartRestartGroup.endReplaceableGroup();
        composerStartRestartGroup.startReplaceableGroup(-607848778);
        ComposerKt.sourceInformation(composerStartRestartGroup, "126@5670L65");
        if (!composerStartRestartGroup.getSkipping()) {
            EffectsKt.SideEffect(new Function0<Unit>() { // from class: androidx.compose.ui.layout.SubcomposeLayoutKt.SubcomposeLayout.4
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
                    state.forceRecomposeChildren$ui_release();
                }
            }, composerStartRestartGroup, 0);
        }
        composerStartRestartGroup.endReplaceableGroup();
        final State stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(state, composerStartRestartGroup, 8);
        Unit unit = Unit.INSTANCE;
        composerStartRestartGroup.startReplaceableGroup(1157296644);
        ComposerKt.sourceInformation(composerStartRestartGroup, "CC(remember)P(1):Composables.kt#9igjgp");
        boolean zChanged = composerStartRestartGroup.changed(stateRememberUpdatedState);
        Object objRememberedValue = composerStartRestartGroup.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = (Function1) new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.ui.layout.SubcomposeLayoutKt$SubcomposeLayout$5$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final DisposableEffectResult invoke(DisposableEffectScope DisposableEffect) {
                    Intrinsics.checkNotNullParameter(DisposableEffect, "$this$DisposableEffect");
                    final State<SubcomposeLayoutState> state2 = stateRememberUpdatedState;
                    return new DisposableEffectResult() { // from class: androidx.compose.ui.layout.SubcomposeLayoutKt$SubcomposeLayout$5$1$invoke$$inlined$onDispose$1
                        @Override // androidx.compose.runtime.DisposableEffectResult
                        public void dispose() {
                            ((SubcomposeLayoutState) state2.getValue()).disposeCurrentNodes$ui_release();
                        }
                    };
                }
            };
            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
        }
        composerStartRestartGroup.endReplaceableGroup();
        EffectsKt.DisposableEffect(unit, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue, composerStartRestartGroup, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.layout.SubcomposeLayoutKt.SubcomposeLayout.6
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
                SubcomposeLayoutKt.SubcomposeLayout(state, modifier2, measurePolicy, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
            }
        });
    }

    public static final SubcomposeSlotReusePolicy SubcomposeSlotReusePolicy(int i) {
        return new FixedCountSubcomposeSlotReusePolicy(i);
    }
}
