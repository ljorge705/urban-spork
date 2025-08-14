package androidx.compose.material;

import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.PainterModifierKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.ImageBitmap;
import androidx.compose.ui.graphics.painter.BitmapPainter;
import androidx.compose.ui.graphics.painter.Painter;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Icon.kt */
@Metadata(d1 = {"\u0000@\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a;\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\u00012\b\b\u0002\u0010\t\u001a\u00020\nH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000b\u0010\f\u001a;\u0010\u0002\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\u00012\b\b\u0002\u0010\t\u001a\u00020\nH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000b\u0010\u000f\u001a;\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\u00012\b\b\u0002\u0010\t\u001a\u00020\nH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000b\u0010\u0012\u001a\u0014\u0010\u0013\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\r\u001a\u00020\u000eH\u0002\u001a\u0019\u0010\u0014\u001a\u00020\u0015*\u00020\u0016H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0017\u0010\u0018\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006\u0019"}, d2 = {"DefaultIconSizeModifier", "Landroidx/compose/ui/Modifier;", "Icon", "", "bitmap", "Landroidx/compose/ui/graphics/ImageBitmap;", "contentDescription", "", "modifier", "tint", "Landroidx/compose/ui/graphics/Color;", "Icon-ww6aTOc", "(Landroidx/compose/ui/graphics/ImageBitmap;Ljava/lang/String;Landroidx/compose/ui/Modifier;JLandroidx/compose/runtime/Composer;II)V", "painter", "Landroidx/compose/ui/graphics/painter/Painter;", "(Landroidx/compose/ui/graphics/painter/Painter;Ljava/lang/String;Landroidx/compose/ui/Modifier;JLandroidx/compose/runtime/Composer;II)V", "imageVector", "Landroidx/compose/ui/graphics/vector/ImageVector;", "(Landroidx/compose/ui/graphics/vector/ImageVector;Ljava/lang/String;Landroidx/compose/ui/Modifier;JLandroidx/compose/runtime/Composer;II)V", "defaultSizeFor", "isInfinite", "", "Landroidx/compose/ui/geometry/Size;", "isInfinite-uvyYCjk", "(J)Z", "material_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class IconKt {
    private static final Modifier DefaultIconSizeModifier = SizeKt.m733size3ABfNKs(Modifier.INSTANCE, Dp.m4390constructorimpl(24));

    /* JADX WARN: Removed duplicated region for block: B:26:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0121  */
    /* JADX WARN: Removed duplicated region for block: B:75:? A[RETURN, SYNTHETIC] */
    /* renamed from: Icon-ww6aTOc, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1322Iconww6aTOc(final androidx.compose.ui.graphics.vector.ImageVector r17, final java.lang.String r18, androidx.compose.ui.Modifier r19, long r20, androidx.compose.runtime.Composer r22, final int r23, final int r24) {
        /*
            Method dump skipped, instructions count: 311
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.IconKt.m1322Iconww6aTOc(androidx.compose.ui.graphics.vector.ImageVector, java.lang.String, androidx.compose.ui.Modifier, long, androidx.compose.runtime.Composer, int, int):void");
    }

    /* renamed from: Icon-ww6aTOc, reason: not valid java name */
    public static final void m1320Iconww6aTOc(final ImageBitmap bitmap, final String str, Modifier modifier, long j, Composer composer, final int i, final int i2) {
        long jM1876copywmQWz5c$default;
        int i3;
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        Composer composerStartRestartGroup = composer.startRestartGroup(1547383838);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(Icon)P(!,3:c#ui.graphics.Color)86@3624L7,86@3663L7,88@3694L42,89@3741L136:Icon.kt#jmzs0o");
        Modifier modifier2 = (i2 & 4) != 0 ? Modifier.INSTANCE : modifier;
        if ((i2 & 8) != 0) {
            ProvidableCompositionLocal<Color> localContentColor = ContentColorKt.getLocalContentColor();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 103361330, "C:CompositionLocal.kt#9igjgp");
            Object objConsume = composerStartRestartGroup.consume(localContentColor);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            long jM1887unboximpl = ((Color) objConsume).m1887unboximpl();
            ProvidableCompositionLocal<Float> localContentAlpha = ContentAlphaKt.getLocalContentAlpha();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 103361330, "C:CompositionLocal.kt#9igjgp");
            Object objConsume2 = composerStartRestartGroup.consume(localContentAlpha);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            jM1876copywmQWz5c$default = Color.m1876copywmQWz5c$default(jM1887unboximpl, ((Number) objConsume2).floatValue(), 0.0f, 0.0f, 0.0f, 14, null);
            i3 = i & (-7169);
        } else {
            jM1876copywmQWz5c$default = j;
            i3 = i;
        }
        composerStartRestartGroup.startReplaceableGroup(-3686930);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(remember)P(1):Composables.kt#9igjgp");
        boolean zChanged = composerStartRestartGroup.changed(bitmap);
        Object objRememberedValue = composerStartRestartGroup.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            BitmapPainter bitmapPainter = new BitmapPainter(bitmap, 0L, 0L, 6, null);
            composerStartRestartGroup.updateRememberedValue(bitmapPainter);
            objRememberedValue = bitmapPainter;
        }
        composerStartRestartGroup.endReplaceableGroup();
        m1321Iconww6aTOc((BitmapPainter) objRememberedValue, str, modifier2, jM1876copywmQWz5c$default, composerStartRestartGroup, (i3 & 112) | 8 | (i3 & 896) | (i3 & 7168), 0);
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier3 = modifier2;
        final long j2 = jM1876copywmQWz5c$default;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.IconKt$Icon$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                invoke(composer2, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer2, int i4) {
                IconKt.m1320Iconww6aTOc(bitmap, str, modifier3, j2, composer2, i | 1, i2);
            }
        });
    }

    /* renamed from: Icon-ww6aTOc, reason: not valid java name */
    public static final void m1321Iconww6aTOc(final Painter painter, final String str, Modifier modifier, long j, Composer composer, final int i, final int i2) {
        long jM1876copywmQWz5c$default;
        Modifier.Companion companionSemantics$default;
        Intrinsics.checkNotNullParameter(painter, "painter");
        Composer composerStartRestartGroup = composer.startRestartGroup(1547384967);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(Icon)P(2!,3:c#ui.graphics.Color)115@4750L7,115@4789L7,127@5172L253:Icon.kt#jmzs0o");
        Modifier modifier2 = (i2 & 4) != 0 ? Modifier.INSTANCE : modifier;
        if ((i2 & 8) != 0) {
            ProvidableCompositionLocal<Color> localContentColor = ContentColorKt.getLocalContentColor();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 103361330, "C:CompositionLocal.kt#9igjgp");
            Object objConsume = composerStartRestartGroup.consume(localContentColor);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            long jM1887unboximpl = ((Color) objConsume).m1887unboximpl();
            ProvidableCompositionLocal<Float> localContentAlpha = ContentAlphaKt.getLocalContentAlpha();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 103361330, "C:CompositionLocal.kt#9igjgp");
            Object objConsume2 = composerStartRestartGroup.consume(localContentAlpha);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            jM1876copywmQWz5c$default = Color.m1876copywmQWz5c$default(jM1887unboximpl, ((Number) objConsume2).floatValue(), 0.0f, 0.0f, 0.0f, 14, null);
        } else {
            jM1876copywmQWz5c$default = j;
        }
        ColorFilter colorFilterM1918tintxETnrds$default = Color.m1878equalsimpl0(jM1876copywmQWz5c$default, Color.INSTANCE.m1913getUnspecified0d7_KjU()) ? null : ColorFilter.Companion.m1918tintxETnrds$default(ColorFilter.INSTANCE, jM1876copywmQWz5c$default, 0, 2, null);
        if (str != null) {
            composerStartRestartGroup.startReplaceableGroup(1547385352);
            ComposerKt.sourceInformation(composerStartRestartGroup, "120@5028L103");
            Modifier.Companion companion = Modifier.INSTANCE;
            composerStartRestartGroup.startReplaceableGroup(-3686930);
            ComposerKt.sourceInformation(composerStartRestartGroup, "C(remember)P(1):Composables.kt#9igjgp");
            boolean zChanged = composerStartRestartGroup.changed(str);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material.IconKt$Icon$semantics$1$1
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
                        SemanticsPropertiesKt.setContentDescription(semantics, str);
                        SemanticsPropertiesKt.m3766setRolekuIjeqM(semantics, Role.INSTANCE.m3755getImageo7Vup1c());
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            composerStartRestartGroup.endReplaceableGroup();
            companionSemantics$default = SemanticsModifierKt.semantics$default(companion, false, (Function1) objRememberedValue, 1, null);
            composerStartRestartGroup.endReplaceableGroup();
        } else {
            composerStartRestartGroup.startReplaceableGroup(1547385496);
            composerStartRestartGroup.endReplaceableGroup();
            companionSemantics$default = Modifier.INSTANCE;
        }
        BoxKt.Box(PainterModifierKt.paint$default(defaultSizeFor(GraphicsLayerModifierKt.toolingGraphicsLayer(modifier2), painter), painter, false, null, ContentScale.INSTANCE.getFit(), 0.0f, colorFilterM1918tintxETnrds$default, 22, null).then(companionSemantics$default), composerStartRestartGroup, 0);
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier3 = modifier2;
        final long j2 = jM1876copywmQWz5c$default;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.IconKt$Icon$3
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
                IconKt.m1321Iconww6aTOc(painter, str, modifier3, j2, composer2, i | 1, i2);
            }
        });
    }

    private static final Modifier defaultSizeFor(Modifier modifier, Painter painter) {
        return modifier.then((Size.m1704equalsimpl0(painter.getIntrinsicSize(), Size.INSTANCE.m1716getUnspecifiedNHjbRc()) || m1323isInfiniteuvyYCjk(painter.getIntrinsicSize())) ? DefaultIconSizeModifier : Modifier.INSTANCE);
    }

    /* renamed from: isInfinite-uvyYCjk, reason: not valid java name */
    private static final boolean m1323isInfiniteuvyYCjk(long j) {
        return Float.isInfinite(Size.m1708getWidthimpl(j)) && Float.isInfinite(Size.m1705getHeightimpl(j));
    }
}
