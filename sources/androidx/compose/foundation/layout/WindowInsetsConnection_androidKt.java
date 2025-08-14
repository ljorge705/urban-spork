package androidx.compose.foundation.layout;

import android.os.Build;
import android.view.View;
import android.view.ViewConfiguration;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import androidx.compose.ui.input.nestedscroll.NestedScrollModifierKt;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import com.onfido.reactnative.sdk.ReactNativeBridgeUtiles;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WindowInsetsConnection.android.kt */
@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001a%\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0001ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0012\u0010\u0013\u001a\f\u0010\u0014\u001a\u00020\u0015*\u00020\u0015H\u0007\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006\u0016"}, d2 = {"DecelMinusOne", "", "DecelerationRate", "EndTension", "", "GravityEarth", "InchesPerMeter", "Inflection", "P1", "P2", "PlatformFlingScrollFriction", "StartTension", "rememberWindowInsetsConnection", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "windowInsets", "Landroidx/compose/foundation/layout/AndroidWindowInsets;", ReactNativeBridgeUtiles.KEY_DOCUMENT_SIDE, "Landroidx/compose/foundation/layout/WindowInsetsSides;", "rememberWindowInsetsConnection-VRgvIgI", "(Landroidx/compose/foundation/layout/AndroidWindowInsets;ILandroidx/compose/runtime/Composer;I)Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "imeNestedScroll", "Landroidx/compose/ui/Modifier;", "foundation-layout_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class WindowInsetsConnection_androidKt {
    private static final double DecelMinusOne;
    private static final double DecelerationRate;
    private static final float EndTension = 1.0f;
    private static final float GravityEarth = 9.80665f;
    private static final float InchesPerMeter = 39.37f;
    private static final float Inflection = 0.35f;
    private static final float P1 = 0.175f;
    private static final float P2 = 0.35000002f;
    private static final float PlatformFlingScrollFriction = ViewConfiguration.getScrollFriction();
    private static final float StartTension = 0.5f;

    /* renamed from: rememberWindowInsetsConnection-VRgvIgI, reason: not valid java name */
    public static final NestedScrollConnection m747rememberWindowInsetsConnectionVRgvIgI(AndroidWindowInsets windowInsets, int i, Composer composer, int i2) {
        Intrinsics.checkNotNullParameter(windowInsets, "windowInsets");
        composer.startReplaceableGroup(-1011341039);
        ComposerKt.sourceInformation(composer, "C(rememberWindowInsetsConnection)P(1,0:c#foundation.layout.WindowInsetsSides)111@4499L7,113@4612L7,114@4651L7,115@4680L149,118@4834L99:WindowInsetsConnection.android.kt#2w3rfo");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1011341039, i2, -1, "androidx.compose.foundation.layout.rememberWindowInsetsConnection (WindowInsetsConnection.android.kt:104)");
        }
        if (Build.VERSION.SDK_INT < 30) {
            DoNothingNestedScrollConnection doNothingNestedScrollConnection = DoNothingNestedScrollConnection.INSTANCE;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composer.endReplaceableGroup();
            return doNothingNestedScrollConnection;
        }
        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
        ComposerKt.sourceInformationMarkerStart(composer, 2023513938, "C:CompositionLocal.kt#9igjgp");
        Object objConsume = composer.consume(localLayoutDirection);
        ComposerKt.sourceInformationMarkerEnd(composer);
        SideCalculator sideCalculatorM716chooseCalculatorni1skBw = SideCalculator.INSTANCE.m716chooseCalculatorni1skBw(i, (LayoutDirection) objConsume);
        ProvidableCompositionLocal<View> localView = AndroidCompositionLocals_androidKt.getLocalView();
        ComposerKt.sourceInformationMarkerStart(composer, 2023513938, "C:CompositionLocal.kt#9igjgp");
        Object objConsume2 = composer.consume(localView);
        ComposerKt.sourceInformationMarkerEnd(composer);
        View view = (View) objConsume2;
        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
        ComposerKt.sourceInformationMarkerStart(composer, 2023513938, "C:CompositionLocal.kt#9igjgp");
        Object objConsume3 = composer.consume(localDensity);
        ComposerKt.sourceInformationMarkerEnd(composer);
        Density density = (Density) objConsume3;
        Object[] objArr = {windowInsets, view, sideCalculatorM716chooseCalculatorni1skBw, density};
        composer.startReplaceableGroup(-568225417);
        ComposerKt.sourceInformation(composer, "C(remember)P(1):Composables.kt#9igjgp");
        boolean zChanged = false;
        for (int i3 = 0; i3 < 4; i3++) {
            zChanged |= composer.changed(objArr[i3]);
        }
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new WindowInsetsNestedScrollConnection(windowInsets, view, sideCalculatorM716chooseCalculatorni1skBw, density);
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        final WindowInsetsNestedScrollConnection windowInsetsNestedScrollConnection = (WindowInsetsNestedScrollConnection) objRememberedValue;
        EffectsKt.DisposableEffect(windowInsetsNestedScrollConnection, new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.foundation.layout.WindowInsetsConnection_androidKt$rememberWindowInsetsConnection$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final DisposableEffectResult invoke(DisposableEffectScope DisposableEffect) {
                Intrinsics.checkNotNullParameter(DisposableEffect, "$this$DisposableEffect");
                final WindowInsetsNestedScrollConnection windowInsetsNestedScrollConnection2 = windowInsetsNestedScrollConnection;
                return new DisposableEffectResult() { // from class: androidx.compose.foundation.layout.WindowInsetsConnection_androidKt$rememberWindowInsetsConnection$1$invoke$$inlined$onDispose$1
                    @Override // androidx.compose.runtime.DisposableEffectResult
                    public void dispose() {
                        windowInsetsNestedScrollConnection2.dispose();
                    }
                };
            }
        }, composer, 8);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return windowInsetsNestedScrollConnection;
    }

    static {
        double dLog = Math.log(0.78d) / Math.log(0.9d);
        DecelerationRate = dLog;
        DecelMinusOne = dLog - 1.0d;
    }

    public static final Modifier imeNestedScroll(Modifier modifier) {
        Intrinsics.checkNotNullParameter(modifier, "<this>");
        if (Build.VERSION.SDK_INT < 30) {
            return modifier;
        }
        return ComposedModifierKt.composed(modifier, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.layout.WindowInsetsConnection_androidKt$imeNestedScroll$$inlined$debugInspectorInfo$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(InspectorInfo inspectorInfo) {
                invoke2(inspectorInfo);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(InspectorInfo inspectorInfo) {
                Intrinsics.checkNotNullParameter(inspectorInfo, "$this$null");
                inspectorInfo.setName("imeNestedScroll");
            }
        } : InspectableValueKt.getNoInspectorInfo(), new Function3<Modifier, Composer, Integer, Modifier>() { // from class: androidx.compose.foundation.layout.WindowInsetsConnection_androidKt.imeNestedScroll.2
            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier2, Composer composer, Integer num) {
                return invoke(modifier2, composer, num.intValue());
            }

            public final Modifier invoke(Modifier composed, Composer composer, int i) {
                Intrinsics.checkNotNullParameter(composed, "$this$composed");
                composer.startReplaceableGroup(-369978792);
                ComposerKt.sourceInformation(composer, "C80@3170L9,79@3107L124:WindowInsetsConnection.android.kt#2w3rfo");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-369978792, i, -1, "androidx.compose.foundation.layout.imeNestedScroll.<anonymous> (WindowInsetsConnection.android.kt:78)");
                }
                Modifier modifierNestedScroll$default = NestedScrollModifierKt.nestedScroll$default(composed, WindowInsetsConnection_androidKt.m747rememberWindowInsetsConnectionVRgvIgI(WindowInsetsHolder.INSTANCE.current(composer, 8).getIme(), WindowInsetsSides.INSTANCE.m769getBottomJoeWqyM(), composer, 48), null, 2, null);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                composer.endReplaceableGroup();
                return modifierNestedScroll$default;
            }
        });
    }
}
