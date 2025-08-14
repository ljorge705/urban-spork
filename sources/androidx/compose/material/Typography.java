package androidx.compose.material;

import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextDirection;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.text.style.TextIndent;
import androidx.compose.ui.unit.TextUnitKt;
import com.clevertap.android.sdk.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Typography.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0093\u0001\b\u0016\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\t\u001a\u00020\u0005\u0012\b\b\u0002\u0010\n\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\f\u001a\u00020\u0005\u0012\b\b\u0002\u0010\r\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0005¢\u0006\u0002\u0010\u0012Bo\b\u0000\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\u0006\u0010\f\u001a\u00020\u0005\u0012\u0006\u0010\r\u001a\u00020\u0005\u0012\u0006\u0010\u000e\u001a\u00020\u0005\u0012\u0006\u0010\u000f\u001a\u00020\u0005\u0012\u0006\u0010\u0010\u001a\u00020\u0005\u0012\u0006\u0010\u0011\u001a\u00020\u0005¢\u0006\u0002\u0010\u0013J\u0088\u0001\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u00052\b\b\u0002\u0010\r\u001a\u00020\u00052\b\b\u0002\u0010\u000e\u001a\u00020\u00052\b\b\u0002\u0010\u000f\u001a\u00020\u00052\b\b\u0002\u0010\u0010\u001a\u00020\u00052\b\b\u0002\u0010\u0011\u001a\u00020\u0005J\u0013\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010&\u001a\u00020'H\u0016J\b\u0010(\u001a\u00020)H\u0016R\u0011\u0010\r\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u000e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u0011\u0010\u000f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015R\u0011\u0010\u0010\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0015R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0015R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0015R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0015R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0015R\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0015R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0015R\u0011\u0010\u0011\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0015R\u0011\u0010\u000b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0015R\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0015¨\u0006*"}, d2 = {"Landroidx/compose/material/Typography;", "", "defaultFontFamily", "Landroidx/compose/ui/text/font/FontFamily;", "h1", "Landroidx/compose/ui/text/TextStyle;", "h2", "h3", "h4", "h5", "h6", "subtitle1", "subtitle2", "body1", "body2", "button", "caption", "overline", "(Landroidx/compose/ui/text/font/FontFamily;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;)V", "(Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;)V", "getBody1", "()Landroidx/compose/ui/text/TextStyle;", "getBody2", "getButton", "getCaption", "getH1", "getH2", "getH3", "getH4", "getH5", "getH6", "getOverline", "getSubtitle1", "getSubtitle2", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "", "toString", "", "material_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class Typography {
    private final TextStyle body1;
    private final TextStyle body2;
    private final TextStyle button;
    private final TextStyle caption;
    private final TextStyle h1;
    private final TextStyle h2;
    private final TextStyle h3;
    private final TextStyle h4;
    private final TextStyle h5;
    private final TextStyle h6;
    private final TextStyle overline;
    private final TextStyle subtitle1;
    private final TextStyle subtitle2;

    public final TextStyle getBody1() {
        return this.body1;
    }

    public final TextStyle getBody2() {
        return this.body2;
    }

    public final TextStyle getButton() {
        return this.button;
    }

    public final TextStyle getCaption() {
        return this.caption;
    }

    public final TextStyle getH1() {
        return this.h1;
    }

    public final TextStyle getH2() {
        return this.h2;
    }

    public final TextStyle getH3() {
        return this.h3;
    }

    public final TextStyle getH4() {
        return this.h4;
    }

    public final TextStyle getH5() {
        return this.h5;
    }

    public final TextStyle getH6() {
        return this.h6;
    }

    public final TextStyle getOverline() {
        return this.overline;
    }

    public final TextStyle getSubtitle1() {
        return this.subtitle1;
    }

    public final TextStyle getSubtitle2() {
        return this.subtitle2;
    }

    public Typography(TextStyle h1, TextStyle h2, TextStyle h3, TextStyle h4, TextStyle h5, TextStyle h6, TextStyle subtitle1, TextStyle subtitle2, TextStyle body1, TextStyle body2, TextStyle button, TextStyle caption, TextStyle overline) {
        Intrinsics.checkNotNullParameter(h1, "h1");
        Intrinsics.checkNotNullParameter(h2, "h2");
        Intrinsics.checkNotNullParameter(h3, "h3");
        Intrinsics.checkNotNullParameter(h4, "h4");
        Intrinsics.checkNotNullParameter(h5, "h5");
        Intrinsics.checkNotNullParameter(h6, "h6");
        Intrinsics.checkNotNullParameter(subtitle1, "subtitle1");
        Intrinsics.checkNotNullParameter(subtitle2, "subtitle2");
        Intrinsics.checkNotNullParameter(body1, "body1");
        Intrinsics.checkNotNullParameter(body2, "body2");
        Intrinsics.checkNotNullParameter(button, "button");
        Intrinsics.checkNotNullParameter(caption, "caption");
        Intrinsics.checkNotNullParameter(overline, "overline");
        this.h1 = h1;
        this.h2 = h2;
        this.h3 = h3;
        this.h4 = h4;
        this.h5 = h5;
        this.h6 = h6;
        this.subtitle1 = subtitle1;
        this.subtitle2 = subtitle2;
        this.body1 = body1;
        this.body2 = body2;
        this.button = button;
        this.caption = caption;
        this.overline = overline;
    }

    public /* synthetic */ Typography(FontFamily fontFamily, TextStyle textStyle, TextStyle textStyle2, TextStyle textStyle3, TextStyle textStyle4, TextStyle textStyle5, TextStyle textStyle6, TextStyle textStyle7, TextStyle textStyle8, TextStyle textStyle9, TextStyle textStyle10, TextStyle textStyle11, TextStyle textStyle12, TextStyle textStyle13, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? FontFamily.INSTANCE.getDefault() : fontFamily, (i & 2) != 0 ? new TextStyle(0L, TextUnitKt.getSp(96), FontWeight.INSTANCE.getLight(), (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, TextUnitKt.getSp(-1.5d), (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (TextAlign) null, (TextDirection) null, 0L, (TextIndent) null, 262009, (DefaultConstructorMarker) null) : textStyle, (i & 4) != 0 ? new TextStyle(0L, TextUnitKt.getSp(60), FontWeight.INSTANCE.getLight(), (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, TextUnitKt.getSp(-0.5d), (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (TextAlign) null, (TextDirection) null, 0L, (TextIndent) null, 262009, (DefaultConstructorMarker) null) : textStyle2, (i & 8) != 0 ? new TextStyle(0L, TextUnitKt.getSp(48), FontWeight.INSTANCE.getNormal(), (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, TextUnitKt.getSp(0), (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (TextAlign) null, (TextDirection) null, 0L, (TextIndent) null, 262009, (DefaultConstructorMarker) null) : textStyle3, (i & 16) != 0 ? new TextStyle(0L, TextUnitKt.getSp(34), FontWeight.INSTANCE.getNormal(), (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, TextUnitKt.getSp(0.25d), (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (TextAlign) null, (TextDirection) null, 0L, (TextIndent) null, 262009, (DefaultConstructorMarker) null) : textStyle4, (i & 32) != 0 ? new TextStyle(0L, TextUnitKt.getSp(24), FontWeight.INSTANCE.getNormal(), (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, TextUnitKt.getSp(0), (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (TextAlign) null, (TextDirection) null, 0L, (TextIndent) null, 262009, (DefaultConstructorMarker) null) : textStyle5, (i & 64) != 0 ? new TextStyle(0L, TextUnitKt.getSp(20), FontWeight.INSTANCE.getMedium(), (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, TextUnitKt.getSp(0.15d), (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (TextAlign) null, (TextDirection) null, 0L, (TextIndent) null, 262009, (DefaultConstructorMarker) null) : textStyle6, (i & 128) != 0 ? new TextStyle(0L, TextUnitKt.getSp(16), FontWeight.INSTANCE.getNormal(), (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, TextUnitKt.getSp(0.15d), (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (TextAlign) null, (TextDirection) null, 0L, (TextIndent) null, 262009, (DefaultConstructorMarker) null) : textStyle7, (i & 256) != 0 ? new TextStyle(0L, TextUnitKt.getSp(14), FontWeight.INSTANCE.getMedium(), (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, TextUnitKt.getSp(0.1d), (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (TextAlign) null, (TextDirection) null, 0L, (TextIndent) null, 262009, (DefaultConstructorMarker) null) : textStyle8, (i & 512) != 0 ? new TextStyle(0L, TextUnitKt.getSp(16), FontWeight.INSTANCE.getNormal(), (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, TextUnitKt.getSp(0.5d), (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (TextAlign) null, (TextDirection) null, 0L, (TextIndent) null, 262009, (DefaultConstructorMarker) null) : textStyle9, (i & 1024) != 0 ? new TextStyle(0L, TextUnitKt.getSp(14), FontWeight.INSTANCE.getNormal(), (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, TextUnitKt.getSp(0.25d), (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (TextAlign) null, (TextDirection) null, 0L, (TextIndent) null, 262009, (DefaultConstructorMarker) null) : textStyle10, (i & 2048) != 0 ? new TextStyle(0L, TextUnitKt.getSp(14), FontWeight.INSTANCE.getMedium(), (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, TextUnitKt.getSp(1.25d), (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (TextAlign) null, (TextDirection) null, 0L, (TextIndent) null, 262009, (DefaultConstructorMarker) null) : textStyle11, (i & 4096) != 0 ? new TextStyle(0L, TextUnitKt.getSp(12), FontWeight.INSTANCE.getNormal(), (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, TextUnitKt.getSp(0.4d), (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (TextAlign) null, (TextDirection) null, 0L, (TextIndent) null, 262009, (DefaultConstructorMarker) null) : textStyle12, (i & 8192) != 0 ? new TextStyle(0L, TextUnitKt.getSp(10), FontWeight.INSTANCE.getNormal(), (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, TextUnitKt.getSp(1.5d), (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (TextAlign) null, (TextDirection) null, 0L, (TextIndent) null, 262009, (DefaultConstructorMarker) null) : textStyle13);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public Typography(FontFamily defaultFontFamily, TextStyle h1, TextStyle h2, TextStyle h3, TextStyle h4, TextStyle h5, TextStyle h6, TextStyle subtitle1, TextStyle subtitle2, TextStyle body1, TextStyle body2, TextStyle button, TextStyle caption, TextStyle overline) {
        this(TypographyKt.withDefaultFontFamily(h1, defaultFontFamily), TypographyKt.withDefaultFontFamily(h2, defaultFontFamily), TypographyKt.withDefaultFontFamily(h3, defaultFontFamily), TypographyKt.withDefaultFontFamily(h4, defaultFontFamily), TypographyKt.withDefaultFontFamily(h5, defaultFontFamily), TypographyKt.withDefaultFontFamily(h6, defaultFontFamily), TypographyKt.withDefaultFontFamily(subtitle1, defaultFontFamily), TypographyKt.withDefaultFontFamily(subtitle2, defaultFontFamily), TypographyKt.withDefaultFontFamily(body1, defaultFontFamily), TypographyKt.withDefaultFontFamily(body2, defaultFontFamily), TypographyKt.withDefaultFontFamily(button, defaultFontFamily), TypographyKt.withDefaultFontFamily(caption, defaultFontFamily), TypographyKt.withDefaultFontFamily(overline, defaultFontFamily));
        Intrinsics.checkNotNullParameter(defaultFontFamily, "defaultFontFamily");
        Intrinsics.checkNotNullParameter(h1, "h1");
        Intrinsics.checkNotNullParameter(h2, "h2");
        Intrinsics.checkNotNullParameter(h3, "h3");
        Intrinsics.checkNotNullParameter(h4, "h4");
        Intrinsics.checkNotNullParameter(h5, "h5");
        Intrinsics.checkNotNullParameter(h6, "h6");
        Intrinsics.checkNotNullParameter(subtitle1, "subtitle1");
        Intrinsics.checkNotNullParameter(subtitle2, "subtitle2");
        Intrinsics.checkNotNullParameter(body1, "body1");
        Intrinsics.checkNotNullParameter(body2, "body2");
        Intrinsics.checkNotNullParameter(button, "button");
        Intrinsics.checkNotNullParameter(caption, "caption");
        Intrinsics.checkNotNullParameter(overline, "overline");
    }

    public final Typography copy(TextStyle h1, TextStyle h2, TextStyle h3, TextStyle h4, TextStyle h5, TextStyle h6, TextStyle subtitle1, TextStyle subtitle2, TextStyle body1, TextStyle body2, TextStyle button, TextStyle caption, TextStyle overline) {
        Intrinsics.checkNotNullParameter(h1, "h1");
        Intrinsics.checkNotNullParameter(h2, "h2");
        Intrinsics.checkNotNullParameter(h3, "h3");
        Intrinsics.checkNotNullParameter(h4, "h4");
        Intrinsics.checkNotNullParameter(h5, "h5");
        Intrinsics.checkNotNullParameter(h6, "h6");
        Intrinsics.checkNotNullParameter(subtitle1, "subtitle1");
        Intrinsics.checkNotNullParameter(subtitle2, "subtitle2");
        Intrinsics.checkNotNullParameter(body1, "body1");
        Intrinsics.checkNotNullParameter(body2, "body2");
        Intrinsics.checkNotNullParameter(button, "button");
        Intrinsics.checkNotNullParameter(caption, "caption");
        Intrinsics.checkNotNullParameter(overline, "overline");
        return new Typography(h1, h2, h3, h4, h5, h6, subtitle1, subtitle2, body1, body2, button, caption, overline);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Typography)) {
            return false;
        }
        Typography typography = (Typography) other;
        return Intrinsics.areEqual(this.h1, typography.h1) && Intrinsics.areEqual(this.h2, typography.h2) && Intrinsics.areEqual(this.h3, typography.h3) && Intrinsics.areEqual(this.h4, typography.h4) && Intrinsics.areEqual(this.h5, typography.h5) && Intrinsics.areEqual(this.h6, typography.h6) && Intrinsics.areEqual(this.subtitle1, typography.subtitle1) && Intrinsics.areEqual(this.subtitle2, typography.subtitle2) && Intrinsics.areEqual(this.body1, typography.body1) && Intrinsics.areEqual(this.body2, typography.body2) && Intrinsics.areEqual(this.button, typography.button) && Intrinsics.areEqual(this.caption, typography.caption) && Intrinsics.areEqual(this.overline, typography.overline);
    }

    public int hashCode() {
        return (((((((((((((((((((((((this.h1.hashCode() * 31) + this.h2.hashCode()) * 31) + this.h3.hashCode()) * 31) + this.h4.hashCode()) * 31) + this.h5.hashCode()) * 31) + this.h6.hashCode()) * 31) + this.subtitle1.hashCode()) * 31) + this.subtitle2.hashCode()) * 31) + this.body1.hashCode()) * 31) + this.body2.hashCode()) * 31) + this.button.hashCode()) * 31) + this.caption.hashCode()) * 31) + this.overline.hashCode();
    }

    public String toString() {
        return "Typography(h1=" + this.h1 + ", h2=" + this.h2 + ", h3=" + this.h3 + ", h4=" + this.h4 + ", h5=" + this.h5 + ", h6=" + this.h6 + ", subtitle1=" + this.subtitle1 + ", subtitle2=" + this.subtitle2 + ", body1=" + this.body1 + ", body2=" + this.body2 + ", button=" + this.button + ", caption=" + this.caption + ", overline=" + this.overline + ')';
    }
}
