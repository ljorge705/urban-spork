package androidx.compose.ui.platform;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: WindowInfo.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a0\u0010\u0000\u001a\u00020\u00012!\u0010\u0002\u001a\u001d\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\u00010\u0003H\u0001¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"WindowFocusObserver", "", "onWindowFocusChanged", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "isWindowFocused", "(Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)V", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class WindowInfoKt {
    public static final void WindowFocusObserver(final Function1<? super Boolean, Unit> onWindowFocusChanged, Composer composer, final int i) {
        int i2;
        Intrinsics.checkNotNullParameter(onWindowFocusChanged, "onWindowFocusChanged");
        Composer composerStartRestartGroup = composer.startRestartGroup(127829799);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(WindowFocusObserver)54@2046L7,55@2073L42,56@2147L90,56@2120L117:WindowInfo.kt#itgzvw");
        if ((i & 14) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(onWindowFocusChanged) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i2 & 11) != 2 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(127829799, i2, -1, "androidx.compose.ui.platform.WindowFocusObserver (WindowInfo.kt:53)");
            }
            ProvidableCompositionLocal<WindowInfo> localWindowInfo = CompositionLocalsKt.getLocalWindowInfo();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = composerStartRestartGroup.consume(localWindowInfo);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            WindowInfo windowInfo = (WindowInfo) objConsume;
            State stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(onWindowFocusChanged, composerStartRestartGroup, i2 & 14);
            composerStartRestartGroup.startReplaceableGroup(511388516);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(remember)P(1,2):Composables.kt#9igjgp");
            boolean zChanged = composerStartRestartGroup.changed(windowInfo) | composerStartRestartGroup.changed(stateRememberUpdatedState);
            WindowInfoKt$WindowFocusObserver$1$1 windowInfoKt$WindowFocusObserver$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || windowInfoKt$WindowFocusObserver$1$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                windowInfoKt$WindowFocusObserver$1$1RememberedValue = new WindowInfoKt$WindowFocusObserver$1$1(windowInfo, stateRememberUpdatedState, null);
                composerStartRestartGroup.updateRememberedValue(windowInfoKt$WindowFocusObserver$1$1RememberedValue);
            }
            composerStartRestartGroup.endReplaceableGroup();
            EffectsKt.LaunchedEffect(windowInfo, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) windowInfoKt$WindowFocusObserver$1$1RememberedValue, composerStartRestartGroup, 64);
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
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.platform.WindowInfoKt.WindowFocusObserver.2
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
                WindowInfoKt.WindowFocusObserver(onWindowFocusChanged, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
            }
        });
    }
}
