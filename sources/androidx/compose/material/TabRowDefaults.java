package androidx.compose.material;

import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.OffsetKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.unit.Dp;
import com.oblador.keychain.cipherStorage.CipherStorageKeystoreRsaEcb;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TabRow.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J3\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00062\b\b\u0002\u0010\u0013\u001a\u00020\u0014H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0015\u0010\u0016J3\u0010\u0017\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0018\u001a\u00020\u00062\b\b\u0002\u0010\u0013\u001a\u00020\u0014H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0019\u0010\u0016J\u0012\u0010\u001a\u001a\u00020\u0011*\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u001cR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u001c\u0010\u0005\u001a\u00020\u0006ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\u001c\u0010\n\u001a\u00020\u0006ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u000b\u0010\bR\u001c\u0010\f\u001a\u00020\u0006ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\r\u0010\b\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u001d"}, d2 = {"Landroidx/compose/material/TabRowDefaults;", "", "()V", "DividerOpacity", "", "DividerThickness", "Landroidx/compose/ui/unit/Dp;", "getDividerThickness-D9Ej5fM", "()F", "F", "IndicatorHeight", "getIndicatorHeight-D9Ej5fM", "ScrollableTabRowPadding", "getScrollableTabRowPadding-D9Ej5fM", "Divider", "", "modifier", "Landroidx/compose/ui/Modifier;", "thickness", "color", "Landroidx/compose/ui/graphics/Color;", "Divider-9IZ8Weo", "(Landroidx/compose/ui/Modifier;FJLandroidx/compose/runtime/Composer;II)V", "Indicator", "height", "Indicator-9IZ8Weo", "tabIndicatorOffset", "currentTabPosition", "Landroidx/compose/material/TabPosition;", "material_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class TabRowDefaults {
    public static final int $stable = 0;
    public static final float DividerOpacity = 0.12f;
    public static final TabRowDefaults INSTANCE = new TabRowDefaults();
    private static final float DividerThickness = Dp.m4390constructorimpl(1);
    private static final float IndicatorHeight = Dp.m4390constructorimpl(2);
    private static final float ScrollableTabRowPadding = Dp.m4390constructorimpl(52);

    /* renamed from: getDividerThickness-D9Ej5fM, reason: not valid java name */
    public final float m1445getDividerThicknessD9Ej5fM() {
        return DividerThickness;
    }

    /* renamed from: getIndicatorHeight-D9Ej5fM, reason: not valid java name */
    public final float m1446getIndicatorHeightD9Ej5fM() {
        return IndicatorHeight;
    }

    /* renamed from: getScrollableTabRowPadding-D9Ej5fM, reason: not valid java name */
    public final float m1447getScrollableTabRowPaddingD9Ej5fM() {
        return ScrollableTabRowPadding;
    }

    private TabRowDefaults() {
    }

    /* renamed from: Divider-9IZ8Weo, reason: not valid java name */
    public final void m1443Divider9IZ8Weo(Modifier modifier, float f, long j, Composer composer, final int i, final int i2) {
        final Modifier modifier2;
        int i3;
        float f2;
        long j2;
        float fM1445getDividerThicknessD9Ej5fM;
        long jM1876copywmQWz5c$default;
        long j3;
        final float f3;
        final long j4;
        Composer composerStartRestartGroup = composer.startRestartGroup(-2003284867);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(Divider)P(1,2:c#ui.unit.Dp,0:c#ui.graphics.Color)359@16108L7,361@16187L66:TabRow.kt#jmzs0o");
        int i4 = i2 & 1;
        if (i4 != 0) {
            i3 = i | 6;
            modifier2 = modifier;
        } else if ((i & 14) == 0) {
            modifier2 = modifier;
            i3 = (composerStartRestartGroup.changed(modifier2) ? 4 : 2) | i;
        } else {
            modifier2 = modifier;
            i3 = i;
        }
        if ((i & 112) == 0) {
            if ((i2 & 2) == 0) {
                f2 = f;
                int i5 = composerStartRestartGroup.changed(f2) ? 32 : 16;
                i3 |= i5;
            } else {
                f2 = f;
            }
            i3 |= i5;
        } else {
            f2 = f;
        }
        if ((i & 896) == 0) {
            j2 = j;
            i3 |= ((i2 & 4) == 0 && composerStartRestartGroup.changed(j2)) ? 256 : 128;
        } else {
            j2 = j;
        }
        if ((i2 & 8) != 0) {
            i3 |= CipherStorageKeystoreRsaEcb.ENCRYPTION_KEY_SIZE;
        } else if ((i & 7168) == 0) {
            i3 |= composerStartRestartGroup.changed(this) ? 2048 : 1024;
        }
        if (((i3 & 5851) ^ 1170) != 0 || !composerStartRestartGroup.getSkipping()) {
            if ((i & 1) != 0 && !composerStartRestartGroup.getDefaultsInvalid()) {
                composerStartRestartGroup.skipCurrentGroup();
                if ((i2 & 2) != 0) {
                    i3 &= -113;
                }
                if ((i2 & 4) != 0) {
                    i3 &= -897;
                }
                j3 = j2;
            } else {
                composerStartRestartGroup.startDefaults();
                Modifier.Companion companion = i4 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i2 & 2) != 0) {
                    fM1445getDividerThicknessD9Ej5fM = m1445getDividerThicknessD9Ej5fM();
                    i3 &= -113;
                } else {
                    fM1445getDividerThicknessD9Ej5fM = f2;
                }
                if ((i2 & 4) != 0) {
                    ProvidableCompositionLocal<Color> localContentColor = ContentColorKt.getLocalContentColor();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 103361330, "C:CompositionLocal.kt#9igjgp");
                    Object objConsume = composerStartRestartGroup.consume(localContentColor);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    jM1876copywmQWz5c$default = Color.m1876copywmQWz5c$default(((Color) objConsume).m1887unboximpl(), 0.12f, 0.0f, 0.0f, 0.0f, 14, null);
                    i3 &= -897;
                } else {
                    jM1876copywmQWz5c$default = j2;
                }
                composerStartRestartGroup.endDefaults();
                j3 = jM1876copywmQWz5c$default;
                f2 = fM1445getDividerThicknessD9Ej5fM;
                modifier2 = companion;
            }
            DividerKt.m1279DivideroMI9zvI(modifier2, j3, f2, 0.0f, composerStartRestartGroup, (i3 & 14) | ((i3 >> 3) & 112) | ((i3 << 3) & 896), 8);
            f3 = f2;
            j4 = j3;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            f3 = f2;
            j4 = j2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.TabRowDefaults$Divider$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                invoke(composer2, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer2, int i6) {
                this.$tmp0_rcvr.m1443Divider9IZ8Weo(modifier2, f3, j4, composer2, i | 1, i2);
            }
        });
    }

    /* renamed from: Indicator-9IZ8Weo, reason: not valid java name */
    public final void m1444Indicator9IZ8Weo(Modifier modifier, float f, long j, Composer composer, final int i, final int i2) {
        final Modifier modifier2;
        int i3;
        float f2;
        long j2;
        Modifier.Companion companion;
        float fM1446getIndicatorHeightD9Ej5fM;
        long jM1887unboximpl;
        final long j3;
        final float f3;
        Composer composerStartRestartGroup = composer.startRestartGroup(500351573);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(Indicator)P(2,1:c#ui.unit.Dp,0:c#ui.graphics.Color)376@16696L7,378@16720L142:TabRow.kt#jmzs0o");
        int i4 = i2 & 1;
        if (i4 != 0) {
            i3 = i | 6;
            modifier2 = modifier;
        } else if ((i & 14) == 0) {
            modifier2 = modifier;
            i3 = (composerStartRestartGroup.changed(modifier2) ? 4 : 2) | i;
        } else {
            modifier2 = modifier;
            i3 = i;
        }
        if ((i & 112) == 0) {
            if ((i2 & 2) == 0) {
                f2 = f;
                int i5 = composerStartRestartGroup.changed(f2) ? 32 : 16;
                i3 |= i5;
            } else {
                f2 = f;
            }
            i3 |= i5;
        } else {
            f2 = f;
        }
        if ((i & 896) == 0) {
            j2 = j;
            i3 |= ((i2 & 4) == 0 && composerStartRestartGroup.changed(j2)) ? 256 : 128;
        } else {
            j2 = j;
        }
        if ((i2 & 8) != 0) {
            i3 |= CipherStorageKeystoreRsaEcb.ENCRYPTION_KEY_SIZE;
        } else if ((i & 7168) == 0) {
            i3 |= composerStartRestartGroup.changed(this) ? 2048 : 1024;
        }
        if (((i3 & 5851) ^ 1170) != 0 || !composerStartRestartGroup.getSkipping()) {
            if ((i & 1) == 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                composerStartRestartGroup.startDefaults();
                companion = i4 != 0 ? Modifier.INSTANCE : modifier2;
                fM1446getIndicatorHeightD9Ej5fM = (i2 & 2) != 0 ? m1446getIndicatorHeightD9Ej5fM() : f2;
                if ((i2 & 4) != 0) {
                    ProvidableCompositionLocal<Color> localContentColor = ContentColorKt.getLocalContentColor();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 103361330, "C:CompositionLocal.kt#9igjgp");
                    Object objConsume = composerStartRestartGroup.consume(localContentColor);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    jM1887unboximpl = ((Color) objConsume).m1887unboximpl();
                } else {
                    jM1887unboximpl = j2;
                }
                composerStartRestartGroup.endDefaults();
            } else {
                composerStartRestartGroup.skipCurrentGroup();
                companion = modifier2;
                fM1446getIndicatorHeightD9Ej5fM = f2;
                jM1887unboximpl = j2;
            }
            BoxKt.Box(BackgroundKt.m427backgroundbw27NRU$default(SizeKt.m719height3ABfNKs(SizeKt.fillMaxWidth$default(companion, 0.0f, 1, null), fM1446getIndicatorHeightD9Ej5fM), jM1887unboximpl, null, 2, null), composerStartRestartGroup, 0);
            j3 = jM1887unboximpl;
            f3 = fM1446getIndicatorHeightD9Ej5fM;
            modifier2 = companion;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            f3 = f2;
            j3 = j2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.TabRowDefaults$Indicator$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                invoke(composer2, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer2, int i6) {
                this.$tmp0_rcvr.m1444Indicator9IZ8Weo(modifier2, f3, j3, composer2, i | 1, i2);
            }
        });
    }

    public final Modifier tabIndicatorOffset(Modifier modifier, final TabPosition currentTabPosition) {
        Intrinsics.checkNotNullParameter(modifier, "<this>");
        Intrinsics.checkNotNullParameter(currentTabPosition, "currentTabPosition");
        return ComposedModifierKt.composed(modifier, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.material.TabRowDefaults$tabIndicatorOffset$$inlined$debugInspectorInfo$1
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
                inspectorInfo.setName("tabIndicatorOffset");
                inspectorInfo.setValue(currentTabPosition);
            }
        } : InspectableValueKt.getNoInspectorInfo(), new Function3<Modifier, Composer, Integer, Modifier>() { // from class: androidx.compose.material.TabRowDefaults.tabIndicatorOffset.2
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier2, Composer composer, Integer num) {
                return invoke(modifier2, composer, num.intValue());
            }

            public final Modifier invoke(Modifier composed, Composer composer, int i) {
                Intrinsics.checkNotNullParameter(composed, "$this$composed");
                composer.startReplaceableGroup(321936238);
                ComposerKt.sourceInformation(composer, "C401@17548L165,405@17745L164:TabRow.kt#jmzs0o");
                State stateM357animateDpAsStateKz89ssw = AnimateAsStateKt.m357animateDpAsStateKz89ssw(currentTabPosition.getWidth(), AnimationSpecKt.tween$default(250, 0, EasingKt.getFastOutSlowInEasing(), 2, null), null, composer, 0, 4);
                Modifier modifierM738width3ABfNKs = SizeKt.m738width3ABfNKs(OffsetKt.m679offsetVpY3zN4$default(SizeKt.wrapContentSize$default(SizeKt.fillMaxWidth$default(composed, 0.0f, 1, null), Alignment.INSTANCE.getBottomStart(), false, 2, null), m1449invoke$lambda1(AnimateAsStateKt.m357animateDpAsStateKz89ssw(currentTabPosition.getLeft(), AnimationSpecKt.tween$default(250, 0, EasingKt.getFastOutSlowInEasing(), 2, null), null, composer, 0, 4)), 0.0f, 2, null), m1448invoke$lambda0(stateM357animateDpAsStateKz89ssw));
                composer.endReplaceableGroup();
                return modifierM738width3ABfNKs;
            }

            /* renamed from: invoke$lambda-0, reason: not valid java name */
            private static final float m1448invoke$lambda0(State<Dp> state) {
                return state.getValue().m4404unboximpl();
            }

            /* renamed from: invoke$lambda-1, reason: not valid java name */
            private static final float m1449invoke$lambda1(State<Dp> state) {
                return state.getValue().m4404unboximpl();
            }
        });
    }
}
