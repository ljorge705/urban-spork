package androidx.compose.ui.text;

import androidx.compose.ui.text.style.Hyphens;
import androidx.compose.ui.text.style.LineBreak;
import androidx.compose.ui.text.style.LineHeightStyle;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDirection;
import androidx.compose.ui.text.style.TextIndent;
import androidx.compose.ui.text.style.TextIndentKt;
import androidx.compose.ui.text.style.TextMotion;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.unit.TextUnit;
import androidx.compose.ui.unit.TextUnitKt;
import com.facebook.react.uimanager.ViewProps;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ParagraphStyle.kt */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001a \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0007\u001a&\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u0005\u001a\u0004\u0018\u00010\n2\b\u0010\u0006\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0007\u001a\u00020\bH\u0002\u001a\u0018\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000eH\u0000\"\u0013\u0010\u0000\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000f"}, d2 = {"DefaultLineHeight", "Landroidx/compose/ui/unit/TextUnit;", "J", "lerp", "Landroidx/compose/ui/text/ParagraphStyle;", ViewProps.START, "stop", "fraction", "", "lerpPlatformStyle", "Landroidx/compose/ui/text/PlatformParagraphStyle;", "resolveParagraphStyleDefaults", "style", "direction", "Landroidx/compose/ui/unit/LayoutDirection;", "ui-text_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ParagraphStyleKt {
    private static final long DefaultLineHeight = TextUnit.INSTANCE.m4582getUnspecifiedXSAIIZE();

    public static final ParagraphStyle lerp(ParagraphStyle start, ParagraphStyle stop, float f) {
        Intrinsics.checkNotNullParameter(start, "start");
        Intrinsics.checkNotNullParameter(stop, "stop");
        TextAlign textAlign = (TextAlign) SpanStyleKt.lerpDiscrete(start.getTextAlign(), stop.getTextAlign(), f);
        TextDirection textDirection = (TextDirection) SpanStyleKt.lerpDiscrete(start.getTextDirection(), stop.getTextDirection(), f);
        long jM3870lerpTextUnitInheritableC3pnCVY = SpanStyleKt.m3870lerpTextUnitInheritableC3pnCVY(start.getLineHeight(), stop.getLineHeight(), f);
        TextIndent textIndent = start.getTextIndent();
        if (textIndent == null) {
            textIndent = TextIndent.INSTANCE.getNone();
        }
        TextIndent textIndent2 = stop.getTextIndent();
        if (textIndent2 == null) {
            textIndent2 = TextIndent.INSTANCE.getNone();
        }
        return new ParagraphStyle(textAlign, textDirection, jM3870lerpTextUnitInheritableC3pnCVY, TextIndentKt.lerp(textIndent, textIndent2, f), lerpPlatformStyle(start.getPlatformStyle(), stop.getPlatformStyle(), f), (LineHeightStyle) SpanStyleKt.lerpDiscrete(start.getLineHeightStyle(), stop.getLineHeightStyle(), f), (LineBreak) SpanStyleKt.lerpDiscrete(start.getLineBreak(), stop.getLineBreak(), f), (Hyphens) SpanStyleKt.lerpDiscrete(start.getHyphens(), stop.getHyphens(), f), (TextMotion) SpanStyleKt.lerpDiscrete(start.getTextMotion(), stop.getTextMotion(), f), (DefaultConstructorMarker) null);
    }

    private static final PlatformParagraphStyle lerpPlatformStyle(PlatformParagraphStyle platformParagraphStyle, PlatformParagraphStyle platformParagraphStyle2, float f) {
        if (platformParagraphStyle == null && platformParagraphStyle2 == null) {
            return null;
        }
        if (platformParagraphStyle == null) {
            platformParagraphStyle = PlatformParagraphStyle.INSTANCE.getDefault();
        }
        if (platformParagraphStyle2 == null) {
            platformParagraphStyle2 = PlatformParagraphStyle.INSTANCE.getDefault();
        }
        return AndroidTextStyle_androidKt.lerp(platformParagraphStyle, platformParagraphStyle2, f);
    }

    public static final ParagraphStyle resolveParagraphStyleDefaults(ParagraphStyle style, LayoutDirection direction) {
        Intrinsics.checkNotNullParameter(style, "style");
        Intrinsics.checkNotNullParameter(direction, "direction");
        TextAlign textAlign = style.getTextAlign();
        TextAlign textAlignM4255boximpl = TextAlign.m4255boximpl(textAlign != null ? textAlign.getValue() : TextAlign.INSTANCE.m4267getStarte0LSkKk());
        TextDirection textDirectionM4268boximpl = TextDirection.m4268boximpl(TextStyleKt.m3935resolveTextDirectionYj3eThk(direction, style.getTextDirection()));
        long lineHeight = TextUnitKt.m4589isUnspecifiedR2X_6o(style.getLineHeight()) ? DefaultLineHeight : style.getLineHeight();
        TextIndent textIndent = style.getTextIndent();
        if (textIndent == null) {
            textIndent = TextIndent.INSTANCE.getNone();
        }
        TextIndent textIndent2 = textIndent;
        PlatformParagraphStyle platformStyle = style.getPlatformStyle();
        LineHeightStyle lineHeightStyle = style.getLineHeightStyle();
        LineBreak lineBreak = style.getLineBreak();
        LineBreak lineBreakM4179boximpl = LineBreak.m4179boximpl(lineBreak != null ? lineBreak.getMask() : LineBreak.INSTANCE.m4194getSimplerAG3T2k());
        Hyphens hyphens = style.getHyphens();
        Hyphens hyphensM4170boximpl = Hyphens.m4170boximpl(hyphens != null ? hyphens.getValue() : Hyphens.INSTANCE.m4178getNonevmbZdU8());
        TextMotion textMotion = style.getTextMotion();
        if (textMotion == null) {
            textMotion = TextMotion.INSTANCE.getStatic();
        }
        return new ParagraphStyle(textAlignM4255boximpl, textDirectionM4268boximpl, lineHeight, textIndent2, platformStyle, lineHeightStyle, lineBreakM4179boximpl, hyphensM4170boximpl, textMotion, (DefaultConstructorMarker) null);
    }
}
