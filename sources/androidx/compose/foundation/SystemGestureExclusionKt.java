package androidx.compose.foundation;

import android.os.Build;
import android.view.View;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectorInfo;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: SystemGestureExclusion.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a&\u0010\u0000\u001a\u00020\u00012\u0016\b\b\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0003H\u0083\b¢\u0006\u0002\u0010\u0006\u001a\n\u0010\u0007\u001a\u00020\u0001*\u00020\u0001\u001a\u001e\u0010\u0007\u001a\u00020\u0001*\u00020\u00012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¨\u0006\b"}, d2 = {"excludeFromSystemGestureQ", "Landroidx/compose/ui/Modifier;", "exclusion", "Lkotlin/Function1;", "Landroidx/compose/ui/layout/LayoutCoordinates;", "Landroidx/compose/ui/geometry/Rect;", "(Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/Modifier;", "systemGestureExclusion", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class SystemGestureExclusionKt {
    private static final Modifier excludeFromSystemGestureQ(Function1<? super LayoutCoordinates, Rect> function1, Composer composer, int i) {
        composer.startReplaceableGroup(1687674107);
        ComposerKt.sourceInformation(composer, "CC(excludeFromSystemGestureQ)79@2790L7,80@2817L79,81@2901L98:SystemGestureExclusion.kt#71ulvw");
        ProvidableCompositionLocal<View> localView = AndroidCompositionLocals_androidKt.getLocalView();
        ComposerKt.sourceInformationMarkerStart(composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = composer.consume(localView);
        ComposerKt.sourceInformationMarkerEnd(composer);
        View view = (View) objConsume;
        composer.startReplaceableGroup(511388516);
        ComposerKt.sourceInformation(composer, "CC(remember)P(1,2):Composables.kt#9igjgp");
        boolean zChanged = composer.changed(view) | composer.changed(function1);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new ExcludeFromSystemGestureModifier(view, function1);
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        ExcludeFromSystemGestureModifier excludeFromSystemGestureModifier = (ExcludeFromSystemGestureModifier) objRememberedValue;
        EffectsKt.DisposableEffect(excludeFromSystemGestureModifier, new AnonymousClass1(excludeFromSystemGestureModifier), composer, 0);
        composer.endReplaceableGroup();
        return excludeFromSystemGestureModifier;
    }

    /* compiled from: SystemGestureExclusion.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* renamed from: androidx.compose.foundation.SystemGestureExclusionKt$excludeFromSystemGestureQ$1, reason: invalid class name */
    public static final class AnonymousClass1 extends Lambda implements Function1<DisposableEffectScope, DisposableEffectResult> {
        final /* synthetic */ ExcludeFromSystemGestureModifier $modifier;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(ExcludeFromSystemGestureModifier excludeFromSystemGestureModifier) {
            super(1);
            this.$modifier = excludeFromSystemGestureModifier;
        }

        @Override // kotlin.jvm.functions.Function1
        public final DisposableEffectResult invoke(DisposableEffectScope DisposableEffect) {
            Intrinsics.checkNotNullParameter(DisposableEffect, "$this$DisposableEffect");
            final ExcludeFromSystemGestureModifier excludeFromSystemGestureModifier = this.$modifier;
            return new DisposableEffectResult() { // from class: androidx.compose.foundation.SystemGestureExclusionKt$excludeFromSystemGestureQ$1$invoke$$inlined$onDispose$1
                @Override // androidx.compose.runtime.DisposableEffectResult
                public void dispose() {
                    excludeFromSystemGestureModifier.removeRect();
                }
            };
        }
    }

    public static final Modifier systemGestureExclusion(Modifier modifier) {
        Intrinsics.checkNotNullParameter(modifier, "<this>");
        if (Build.VERSION.SDK_INT < 29) {
            return modifier;
        }
        return ComposedModifierKt.composed(modifier, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.SystemGestureExclusionKt$systemGestureExclusion$$inlined$debugInspectorInfo$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(InspectorInfo inspectorInfo) {
                invoke2(inspectorInfo);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(InspectorInfo inspectorInfo) {
                Intrinsics.checkNotNullParameter(inspectorInfo, "$this$null");
                inspectorInfo.setName("systemGestureExclusion");
            }
        } : InspectableValueKt.getNoInspectorInfo(), new Function3<Modifier, Composer, Integer, Modifier>() { // from class: androidx.compose.foundation.SystemGestureExclusionKt.systemGestureExclusion.2
            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier2, Composer composer, Integer num) {
                return invoke(modifier2, composer, num.intValue());
            }

            public final Modifier invoke(Modifier composed, Composer composer, int i) {
                Intrinsics.checkNotNullParameter(composed, "$this$composed");
                composer.startReplaceableGroup(1120057036);
                ComposerKt.sourceInformation(composer, "C47@1700L31:SystemGestureExclusion.kt#71ulvw");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1120057036, i, -1, "androidx.compose.foundation.systemGestureExclusion.<anonymous> (SystemGestureExclusion.kt:46)");
                }
                composer.startReplaceableGroup(1687674107);
                ComposerKt.sourceInformation(composer, "CC(excludeFromSystemGestureQ)79@2790L7,80@2817L79,81@2901L98:SystemGestureExclusion.kt#71ulvw");
                ProvidableCompositionLocal<View> localView = AndroidCompositionLocals_androidKt.getLocalView();
                ComposerKt.sourceInformationMarkerStart(composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume = composer.consume(localView);
                ComposerKt.sourceInformationMarkerEnd(composer);
                View view = (View) objConsume;
                composer.startReplaceableGroup(511388516);
                ComposerKt.sourceInformation(composer, "CC(remember)P(1,2):Composables.kt#9igjgp");
                boolean zChanged = composer.changed(view) | composer.changed((Object) null);
                Object objRememberedValue = composer.rememberedValue();
                if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new ExcludeFromSystemGestureModifier(view, null);
                    composer.updateRememberedValue(objRememberedValue);
                }
                composer.endReplaceableGroup();
                ExcludeFromSystemGestureModifier excludeFromSystemGestureModifier = (ExcludeFromSystemGestureModifier) objRememberedValue;
                EffectsKt.DisposableEffect(excludeFromSystemGestureModifier, new AnonymousClass1(excludeFromSystemGestureModifier), composer, 0);
                composer.endReplaceableGroup();
                ExcludeFromSystemGestureModifier excludeFromSystemGestureModifier2 = excludeFromSystemGestureModifier;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                composer.endReplaceableGroup();
                return excludeFromSystemGestureModifier2;
            }
        });
    }

    public static final Modifier systemGestureExclusion(Modifier modifier, final Function1<? super LayoutCoordinates, Rect> exclusion) {
        Intrinsics.checkNotNullParameter(modifier, "<this>");
        Intrinsics.checkNotNullParameter(exclusion, "exclusion");
        if (Build.VERSION.SDK_INT < 29) {
            return modifier;
        }
        return ComposedModifierKt.composed(modifier, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.SystemGestureExclusionKt$systemGestureExclusion$$inlined$debugInspectorInfo$2
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
                inspectorInfo.setName("systemGestureExclusion");
                inspectorInfo.getProperties().set("exclusion", exclusion);
            }
        } : InspectableValueKt.getNoInspectorInfo(), new Function3<Modifier, Composer, Integer, Modifier>() { // from class: androidx.compose.foundation.SystemGestureExclusionKt.systemGestureExclusion.4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier2, Composer composer, Integer num) {
                return invoke(modifier2, composer, num.intValue());
            }

            public final Modifier invoke(Modifier composed, Composer composer, int i) {
                Intrinsics.checkNotNullParameter(composed, "$this$composed");
                composer.startReplaceableGroup(108999);
                ComposerKt.sourceInformation(composer, "C69@2452L36:SystemGestureExclusion.kt#71ulvw");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(108999, i, -1, "androidx.compose.foundation.systemGestureExclusion.<anonymous> (SystemGestureExclusion.kt:68)");
                }
                Function1<LayoutCoordinates, Rect> function1 = exclusion;
                composer.startReplaceableGroup(1687674107);
                ComposerKt.sourceInformation(composer, "CC(excludeFromSystemGestureQ)79@2790L7,80@2817L79,81@2901L98:SystemGestureExclusion.kt#71ulvw");
                ProvidableCompositionLocal<View> localView = AndroidCompositionLocals_androidKt.getLocalView();
                ComposerKt.sourceInformationMarkerStart(composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume = composer.consume(localView);
                ComposerKt.sourceInformationMarkerEnd(composer);
                View view = (View) objConsume;
                composer.startReplaceableGroup(511388516);
                ComposerKt.sourceInformation(composer, "CC(remember)P(1,2):Composables.kt#9igjgp");
                boolean zChanged = composer.changed(view) | composer.changed(function1);
                Object objRememberedValue = composer.rememberedValue();
                if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new ExcludeFromSystemGestureModifier(view, function1);
                    composer.updateRememberedValue(objRememberedValue);
                }
                composer.endReplaceableGroup();
                ExcludeFromSystemGestureModifier excludeFromSystemGestureModifier = (ExcludeFromSystemGestureModifier) objRememberedValue;
                EffectsKt.DisposableEffect(excludeFromSystemGestureModifier, new AnonymousClass1(excludeFromSystemGestureModifier), composer, 0);
                composer.endReplaceableGroup();
                ExcludeFromSystemGestureModifier excludeFromSystemGestureModifier2 = excludeFromSystemGestureModifier;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                composer.endReplaceableGroup();
                return excludeFromSystemGestureModifier2;
            }
        });
    }
}
