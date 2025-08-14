package androidx.compose.material;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.unit.Dp;
import com.facebook.react.uimanager.ViewProps;
import kotlin.Metadata;

/* compiled from: TextFieldDefaults.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001d\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002Jç\u0001\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00112\b\b\u0002\u0010\u0013\u001a\u00020\u00112\b\b\u0002\u0010\u0014\u001a\u00020\u00112\b\b\u0002\u0010\u0015\u001a\u00020\u00112\b\b\u0002\u0010\u0016\u001a\u00020\u00112\b\b\u0002\u0010\u0017\u001a\u00020\u00112\b\b\u0002\u0010\u0018\u001a\u00020\u00112\b\b\u0002\u0010\u0019\u001a\u00020\u00112\b\b\u0002\u0010\u001a\u001a\u00020\u00112\b\b\u0002\u0010\u001b\u001a\u00020\u00112\b\b\u0002\u0010\u001c\u001a\u00020\u00112\b\b\u0002\u0010\u001d\u001a\u00020\u00112\b\b\u0002\u0010\u001e\u001a\u00020\u00112\b\b\u0002\u0010\u001f\u001a\u00020\u00112\b\b\u0002\u0010 \u001a\u00020\u00112\b\b\u0002\u0010!\u001a\u00020\u00112\b\b\u0002\u0010\"\u001a\u00020\u00112\b\b\u0002\u0010#\u001a\u00020\u00112\b\b\u0002\u0010$\u001a\u00020\u00112\b\b\u0002\u0010%\u001a\u00020\u0011H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b&\u0010'Jç\u0001\u0010(\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00112\b\b\u0002\u0010\u0013\u001a\u00020\u00112\b\b\u0002\u0010\u0014\u001a\u00020\u00112\b\b\u0002\u0010\u0015\u001a\u00020\u00112\b\b\u0002\u0010)\u001a\u00020\u00112\b\b\u0002\u0010*\u001a\u00020\u00112\b\b\u0002\u0010+\u001a\u00020\u00112\b\b\u0002\u0010,\u001a\u00020\u00112\b\b\u0002\u0010\u001a\u001a\u00020\u00112\b\b\u0002\u0010\u001b\u001a\u00020\u00112\b\b\u0002\u0010\u001c\u001a\u00020\u00112\b\b\u0002\u0010\u001d\u001a\u00020\u00112\b\b\u0002\u0010\u001e\u001a\u00020\u00112\b\b\u0002\u0010\u001f\u001a\u00020\u00112\b\b\u0002\u0010 \u001a\u00020\u00112\b\b\u0002\u0010!\u001a\u00020\u00112\b\b\u0002\u0010\"\u001a\u00020\u00112\b\b\u0002\u0010#\u001a\u00020\u00112\b\b\u0002\u0010$\u001a\u00020\u00112\b\b\u0002\u0010%\u001a\u00020\u0011H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b-\u0010'R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u001c\u0010\u0006\u001a\u00020\u0007ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u001c\u0010\u000b\u001a\u00020\u0007ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\f\u0010\tR\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006."}, d2 = {"Landroidx/compose/material/TextFieldDefaults;", "", "()V", "BackgroundOpacity", "", "IconOpacity", "MinHeight", "Landroidx/compose/ui/unit/Dp;", "getMinHeight-D9Ej5fM", "()F", "F", "MinWidth", "getMinWidth-D9Ej5fM", "UnfocusedIndicatorLineOpacity", "outlinedTextFieldColors", "Landroidx/compose/material/TextFieldColors;", "textColor", "Landroidx/compose/ui/graphics/Color;", "disabledTextColor", ViewProps.BACKGROUND_COLOR, "cursorColor", "errorCursorColor", "focusedBorderColor", "unfocusedBorderColor", "disabledBorderColor", "errorBorderColor", "leadingIconColor", "disabledLeadingIconColor", "errorLeadingIconColor", "trailingIconColor", "disabledTrailingIconColor", "errorTrailingIconColor", "focusedLabelColor", "unfocusedLabelColor", "disabledLabelColor", "errorLabelColor", "placeholderColor", "disabledPlaceholderColor", "outlinedTextFieldColors-dx8h9Zs", "(JJJJJJJJJJJJJJJJJJJJJLandroidx/compose/runtime/Composer;IIII)Landroidx/compose/material/TextFieldColors;", "textFieldColors", "focusedIndicatorColor", "unfocusedIndicatorColor", "disabledIndicatorColor", "errorIndicatorColor", "textFieldColors-dx8h9Zs", "material_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class TextFieldDefaults {
    public static final int $stable = 0;
    public static final float BackgroundOpacity = 0.12f;
    public static final float IconOpacity = 0.54f;
    public static final float UnfocusedIndicatorLineOpacity = 0.42f;
    public static final TextFieldDefaults INSTANCE = new TextFieldDefaults();
    private static final float MinHeight = Dp.m4390constructorimpl(56);
    private static final float MinWidth = Dp.m4390constructorimpl(280);

    /* renamed from: getMinHeight-D9Ej5fM, reason: not valid java name */
    public final float m1454getMinHeightD9Ej5fM() {
        return MinHeight;
    }

    /* renamed from: getMinWidth-D9Ej5fM, reason: not valid java name */
    public final float m1455getMinWidthD9Ej5fM() {
        return MinWidth;
    }

    private TextFieldDefaults() {
    }

    /* renamed from: textFieldColors-dx8h9Zs, reason: not valid java name */
    public final TextFieldColors m1457textFieldColorsdx8h9Zs(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, Composer composer, int i, int i2, int i3, int i4) {
        long jM1876copywmQWz5c$default;
        composer.startReplaceableGroup(137433512);
        ComposerKt.sourceInformation(composer, "C(textFieldColors)P(17:c#ui.graphics.Color,6:c#ui.graphics.Color,0:c#ui.graphics.Color,1:c#ui.graphics.Color,8:c#ui.graphics.Color,13:c#ui.graphics.Color,19:c#ui.graphics.Color,2:c#ui.graphics.Color,9:c#ui.graphics.Color,15:c#ui.graphics.Color,4:c#ui.graphics.Color,11:c#ui.graphics.Color,18:c#ui.graphics.Color,7:c#ui.graphics.Color,12:c#ui.graphics.Color,14:c#ui.graphics.Color,20:c#ui.graphics.Color,3:c#ui.graphics.Color,10:c#ui.graphics.Color,16:c#ui.graphics.Color,5:c#ui.graphics.Color)164@5810L7,164@5841L7,165@5914L8,166@5972L6,167@6065L6,168@6129L6,170@6208L6,170@6249L4,172@6323L6,173@6475L8,174@6537L6,176@6611L6,177@6740L8,180@6869L6,181@7000L8,182@7065L6,184@7140L6,184@7181L4,185@7239L6,185@7274L6,186@7357L8,187@7415L6,188@7477L6,188@7512L6,189@7598L8:TextFieldDefaults.kt#jmzs0o");
        if ((i4 & 1) != 0) {
            ProvidableCompositionLocal<Color> localContentColor = ContentColorKt.getLocalContentColor();
            ComposerKt.sourceInformationMarkerStart(composer, 103361330, "C:CompositionLocal.kt#9igjgp");
            Object objConsume = composer.consume(localContentColor);
            ComposerKt.sourceInformationMarkerEnd(composer);
            long jM1887unboximpl = ((Color) objConsume).m1887unboximpl();
            ProvidableCompositionLocal<Float> localContentAlpha = ContentAlphaKt.getLocalContentAlpha();
            ComposerKt.sourceInformationMarkerStart(composer, 103361330, "C:CompositionLocal.kt#9igjgp");
            Object objConsume2 = composer.consume(localContentAlpha);
            ComposerKt.sourceInformationMarkerEnd(composer);
            jM1876copywmQWz5c$default = Color.m1876copywmQWz5c$default(jM1887unboximpl, ((Number) objConsume2).floatValue(), 0.0f, 0.0f, 0.0f, 14, null);
        } else {
            jM1876copywmQWz5c$default = j;
        }
        long jM1876copywmQWz5c$default2 = (i4 & 2) != 0 ? Color.m1876copywmQWz5c$default(jM1876copywmQWz5c$default, ContentAlpha.INSTANCE.getDisabled(composer, 0), 0.0f, 0.0f, 0.0f, 14, null) : j2;
        long jM1876copywmQWz5c$default3 = (i4 & 4) != 0 ? Color.m1876copywmQWz5c$default(MaterialTheme.INSTANCE.getColors(composer, 0).m1241getOnSurface0d7_KjU(), 0.12f, 0.0f, 0.0f, 0.0f, 14, null) : j3;
        long jM1242getPrimary0d7_KjU = (i4 & 8) != 0 ? MaterialTheme.INSTANCE.getColors(composer, 0).m1242getPrimary0d7_KjU() : j4;
        long jM1236getError0d7_KjU = (i4 & 16) != 0 ? MaterialTheme.INSTANCE.getColors(composer, 0).m1236getError0d7_KjU() : j5;
        long jM1876copywmQWz5c$default4 = (i4 & 32) != 0 ? Color.m1876copywmQWz5c$default(MaterialTheme.INSTANCE.getColors(composer, 0).m1242getPrimary0d7_KjU(), ContentAlpha.INSTANCE.getHigh(composer, 0), 0.0f, 0.0f, 0.0f, 14, null) : j6;
        long jM1876copywmQWz5c$default5 = (i4 & 64) != 0 ? Color.m1876copywmQWz5c$default(MaterialTheme.INSTANCE.getColors(composer, 0).m1241getOnSurface0d7_KjU(), 0.42f, 0.0f, 0.0f, 0.0f, 14, null) : j7;
        long jM1876copywmQWz5c$default6 = (i4 & 128) != 0 ? Color.m1876copywmQWz5c$default(jM1876copywmQWz5c$default5, ContentAlpha.INSTANCE.getDisabled(composer, 0), 0.0f, 0.0f, 0.0f, 14, null) : j8;
        long jM1236getError0d7_KjU2 = (i4 & 256) != 0 ? MaterialTheme.INSTANCE.getColors(composer, 0).m1236getError0d7_KjU() : j9;
        long jM1876copywmQWz5c$default7 = (i4 & 512) != 0 ? Color.m1876copywmQWz5c$default(MaterialTheme.INSTANCE.getColors(composer, 0).m1241getOnSurface0d7_KjU(), 0.54f, 0.0f, 0.0f, 0.0f, 14, null) : j10;
        long jM1876copywmQWz5c$default8 = (i4 & 1024) != 0 ? Color.m1876copywmQWz5c$default(jM1876copywmQWz5c$default7, ContentAlpha.INSTANCE.getDisabled(composer, 0), 0.0f, 0.0f, 0.0f, 14, null) : j11;
        long j22 = (i4 & 2048) != 0 ? jM1876copywmQWz5c$default7 : j12;
        long jM1876copywmQWz5c$default9 = (i4 & 4096) != 0 ? Color.m1876copywmQWz5c$default(MaterialTheme.INSTANCE.getColors(composer, 0).m1241getOnSurface0d7_KjU(), 0.54f, 0.0f, 0.0f, 0.0f, 14, null) : j13;
        long jM1876copywmQWz5c$default10 = (i4 & 8192) != 0 ? Color.m1876copywmQWz5c$default(jM1876copywmQWz5c$default9, ContentAlpha.INSTANCE.getDisabled(composer, 0), 0.0f, 0.0f, 0.0f, 14, null) : j14;
        long jM1236getError0d7_KjU3 = (i4 & 16384) != 0 ? MaterialTheme.INSTANCE.getColors(composer, 0).m1236getError0d7_KjU() : j15;
        long jM1876copywmQWz5c$default11 = (32768 & i4) != 0 ? Color.m1876copywmQWz5c$default(MaterialTheme.INSTANCE.getColors(composer, 0).m1242getPrimary0d7_KjU(), ContentAlpha.INSTANCE.getHigh(composer, 0), 0.0f, 0.0f, 0.0f, 14, null) : j16;
        long jM1876copywmQWz5c$default12 = (65536 & i4) != 0 ? Color.m1876copywmQWz5c$default(MaterialTheme.INSTANCE.getColors(composer, 0).m1241getOnSurface0d7_KjU(), ContentAlpha.INSTANCE.getMedium(composer, 0), 0.0f, 0.0f, 0.0f, 14, null) : j17;
        long jM1876copywmQWz5c$default13 = (131072 & i4) != 0 ? Color.m1876copywmQWz5c$default(jM1876copywmQWz5c$default12, ContentAlpha.INSTANCE.getDisabled(composer, 0), 0.0f, 0.0f, 0.0f, 14, null) : j18;
        long jM1236getError0d7_KjU4 = (262144 & i4) != 0 ? MaterialTheme.INSTANCE.getColors(composer, 0).m1236getError0d7_KjU() : j19;
        long jM1876copywmQWz5c$default14 = (524288 & i4) != 0 ? Color.m1876copywmQWz5c$default(MaterialTheme.INSTANCE.getColors(composer, 0).m1241getOnSurface0d7_KjU(), ContentAlpha.INSTANCE.getMedium(composer, 0), 0.0f, 0.0f, 0.0f, 14, null) : j20;
        DefaultTextFieldColors defaultTextFieldColors = new DefaultTextFieldColors(jM1876copywmQWz5c$default, jM1876copywmQWz5c$default2, jM1242getPrimary0d7_KjU, jM1236getError0d7_KjU, jM1876copywmQWz5c$default4, jM1876copywmQWz5c$default5, jM1236getError0d7_KjU2, jM1876copywmQWz5c$default6, jM1876copywmQWz5c$default7, jM1876copywmQWz5c$default8, j22, jM1876copywmQWz5c$default9, jM1876copywmQWz5c$default10, jM1236getError0d7_KjU3, jM1876copywmQWz5c$default3, jM1876copywmQWz5c$default11, jM1876copywmQWz5c$default12, jM1876copywmQWz5c$default13, jM1236getError0d7_KjU4, jM1876copywmQWz5c$default14, (i4 & 1048576) != 0 ? Color.m1876copywmQWz5c$default(jM1876copywmQWz5c$default14, ContentAlpha.INSTANCE.getDisabled(composer, 0), 0.0f, 0.0f, 0.0f, 14, null) : j21, null);
        composer.endReplaceableGroup();
        return defaultTextFieldColors;
    }

    /* renamed from: outlinedTextFieldColors-dx8h9Zs, reason: not valid java name */
    public final TextFieldColors m1456outlinedTextFieldColorsdx8h9Zs(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, Composer composer, int i, int i2, int i3, int i4) {
        long jM1876copywmQWz5c$default;
        composer.startReplaceableGroup(-429565418);
        ComposerKt.sourceInformation(composer, "C(outlinedTextFieldColors)P(17:c#ui.graphics.Color,6:c#ui.graphics.Color,0:c#ui.graphics.Color,1:c#ui.graphics.Color,9:c#ui.graphics.Color,13:c#ui.graphics.Color,19:c#ui.graphics.Color,2:c#ui.graphics.Color,8:c#ui.graphics.Color,15:c#ui.graphics.Color,4:c#ui.graphics.Color,11:c#ui.graphics.Color,18:c#ui.graphics.Color,7:c#ui.graphics.Color,12:c#ui.graphics.Color,14:c#ui.graphics.Color,20:c#ui.graphics.Color,3:c#ui.graphics.Color,10:c#ui.graphics.Color,16:c#ui.graphics.Color,5:c#ui.graphics.Color)222@9126L7,222@9157L7,223@9230L8,225@9336L6,226@9400L6,228@9476L6,228@9517L4,230@9588L6,230@9631L8,231@9726L8,232@9785L6,234@9859L6,235@9988L8,238@10117L6,239@10248L8,240@10313L6,242@10388L6,242@10429L4,243@10487L6,243@10522L6,244@10605L8,245@10663L6,246@10725L6,246@10760L6,247@10846L8:TextFieldDefaults.kt#jmzs0o");
        if ((i4 & 1) != 0) {
            ProvidableCompositionLocal<Color> localContentColor = ContentColorKt.getLocalContentColor();
            ComposerKt.sourceInformationMarkerStart(composer, 103361330, "C:CompositionLocal.kt#9igjgp");
            Object objConsume = composer.consume(localContentColor);
            ComposerKt.sourceInformationMarkerEnd(composer);
            long jM1887unboximpl = ((Color) objConsume).m1887unboximpl();
            ProvidableCompositionLocal<Float> localContentAlpha = ContentAlphaKt.getLocalContentAlpha();
            ComposerKt.sourceInformationMarkerStart(composer, 103361330, "C:CompositionLocal.kt#9igjgp");
            Object objConsume2 = composer.consume(localContentAlpha);
            ComposerKt.sourceInformationMarkerEnd(composer);
            jM1876copywmQWz5c$default = Color.m1876copywmQWz5c$default(jM1887unboximpl, ((Number) objConsume2).floatValue(), 0.0f, 0.0f, 0.0f, 14, null);
        } else {
            jM1876copywmQWz5c$default = j;
        }
        long jM1876copywmQWz5c$default2 = (i4 & 2) != 0 ? Color.m1876copywmQWz5c$default(jM1876copywmQWz5c$default, ContentAlpha.INSTANCE.getDisabled(composer, 0), 0.0f, 0.0f, 0.0f, 14, null) : j2;
        long jM1912getTransparent0d7_KjU = (i4 & 4) != 0 ? Color.INSTANCE.m1912getTransparent0d7_KjU() : j3;
        long jM1242getPrimary0d7_KjU = (i4 & 8) != 0 ? MaterialTheme.INSTANCE.getColors(composer, 0).m1242getPrimary0d7_KjU() : j4;
        long jM1236getError0d7_KjU = (i4 & 16) != 0 ? MaterialTheme.INSTANCE.getColors(composer, 0).m1236getError0d7_KjU() : j5;
        long jM1876copywmQWz5c$default3 = (i4 & 32) != 0 ? Color.m1876copywmQWz5c$default(MaterialTheme.INSTANCE.getColors(composer, 0).m1242getPrimary0d7_KjU(), ContentAlpha.INSTANCE.getHigh(composer, 0), 0.0f, 0.0f, 0.0f, 14, null) : j6;
        long jM1876copywmQWz5c$default4 = (i4 & 64) != 0 ? Color.m1876copywmQWz5c$default(MaterialTheme.INSTANCE.getColors(composer, 0).m1241getOnSurface0d7_KjU(), ContentAlpha.INSTANCE.getDisabled(composer, 0), 0.0f, 0.0f, 0.0f, 14, null) : j7;
        long jM1876copywmQWz5c$default5 = (i4 & 128) != 0 ? Color.m1876copywmQWz5c$default(jM1876copywmQWz5c$default4, ContentAlpha.INSTANCE.getDisabled(composer, 0), 0.0f, 0.0f, 0.0f, 14, null) : j8;
        long jM1236getError0d7_KjU2 = (i4 & 256) != 0 ? MaterialTheme.INSTANCE.getColors(composer, 0).m1236getError0d7_KjU() : j9;
        long jM1876copywmQWz5c$default6 = (i4 & 512) != 0 ? Color.m1876copywmQWz5c$default(MaterialTheme.INSTANCE.getColors(composer, 0).m1241getOnSurface0d7_KjU(), 0.54f, 0.0f, 0.0f, 0.0f, 14, null) : j10;
        long jM1876copywmQWz5c$default7 = (i4 & 1024) != 0 ? Color.m1876copywmQWz5c$default(jM1876copywmQWz5c$default6, ContentAlpha.INSTANCE.getDisabled(composer, 0), 0.0f, 0.0f, 0.0f, 14, null) : j11;
        long j22 = (i4 & 2048) != 0 ? jM1876copywmQWz5c$default6 : j12;
        long jM1876copywmQWz5c$default8 = (i4 & 4096) != 0 ? Color.m1876copywmQWz5c$default(MaterialTheme.INSTANCE.getColors(composer, 0).m1241getOnSurface0d7_KjU(), 0.54f, 0.0f, 0.0f, 0.0f, 14, null) : j13;
        long jM1876copywmQWz5c$default9 = (i4 & 8192) != 0 ? Color.m1876copywmQWz5c$default(jM1876copywmQWz5c$default8, ContentAlpha.INSTANCE.getDisabled(composer, 0), 0.0f, 0.0f, 0.0f, 14, null) : j14;
        long jM1236getError0d7_KjU3 = (i4 & 16384) != 0 ? MaterialTheme.INSTANCE.getColors(composer, 0).m1236getError0d7_KjU() : j15;
        long jM1876copywmQWz5c$default10 = (32768 & i4) != 0 ? Color.m1876copywmQWz5c$default(MaterialTheme.INSTANCE.getColors(composer, 0).m1242getPrimary0d7_KjU(), ContentAlpha.INSTANCE.getHigh(composer, 0), 0.0f, 0.0f, 0.0f, 14, null) : j16;
        long jM1876copywmQWz5c$default11 = (65536 & i4) != 0 ? Color.m1876copywmQWz5c$default(MaterialTheme.INSTANCE.getColors(composer, 0).m1241getOnSurface0d7_KjU(), ContentAlpha.INSTANCE.getMedium(composer, 0), 0.0f, 0.0f, 0.0f, 14, null) : j17;
        long jM1876copywmQWz5c$default12 = (131072 & i4) != 0 ? Color.m1876copywmQWz5c$default(jM1876copywmQWz5c$default11, ContentAlpha.INSTANCE.getDisabled(composer, 0), 0.0f, 0.0f, 0.0f, 14, null) : j18;
        long jM1236getError0d7_KjU4 = (262144 & i4) != 0 ? MaterialTheme.INSTANCE.getColors(composer, 0).m1236getError0d7_KjU() : j19;
        long jM1876copywmQWz5c$default13 = (524288 & i4) != 0 ? Color.m1876copywmQWz5c$default(MaterialTheme.INSTANCE.getColors(composer, 0).m1241getOnSurface0d7_KjU(), ContentAlpha.INSTANCE.getMedium(composer, 0), 0.0f, 0.0f, 0.0f, 14, null) : j20;
        DefaultTextFieldColors defaultTextFieldColors = new DefaultTextFieldColors(jM1876copywmQWz5c$default, jM1876copywmQWz5c$default2, jM1242getPrimary0d7_KjU, jM1236getError0d7_KjU, jM1876copywmQWz5c$default3, jM1876copywmQWz5c$default4, jM1236getError0d7_KjU2, jM1876copywmQWz5c$default5, jM1876copywmQWz5c$default6, jM1876copywmQWz5c$default7, j22, jM1876copywmQWz5c$default8, jM1876copywmQWz5c$default9, jM1236getError0d7_KjU3, jM1912getTransparent0d7_KjU, jM1876copywmQWz5c$default10, jM1876copywmQWz5c$default11, jM1876copywmQWz5c$default12, jM1236getError0d7_KjU4, jM1876copywmQWz5c$default13, (i4 & 1048576) != 0 ? Color.m1876copywmQWz5c$default(jM1876copywmQWz5c$default13, ContentAlpha.INSTANCE.getDisabled(composer, 0), 0.0f, 0.0f, 0.0f, 14, null) : j21, null);
        composer.endReplaceableGroup();
        return defaultTextFieldColors;
    }
}
