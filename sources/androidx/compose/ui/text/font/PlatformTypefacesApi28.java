package androidx.compose.ui.text.font;

import android.content.Context;
import androidx.compose.ui.text.font.FontVariation;
import com.facebook.react.uimanager.ViewProps;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PlatformTypefaces.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J1\u0010\u0003\u001a\u00020\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000b\u0010\fJ%\u0010\r\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000e\u0010\u000fJ-\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0013\u0010\u0014J/\u0010\u0015\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\nH\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0019\u0010\fJ?\u0010\u001a\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\n2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001f\u0010 \u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006!"}, d2 = {"Landroidx/compose/ui/text/font/PlatformTypefacesApi28;", "Landroidx/compose/ui/text/font/PlatformTypefaces;", "()V", "createAndroidTypefaceApi28", "Landroid/graphics/Typeface;", "genericFontFamily", "", ViewProps.FONT_WEIGHT, "Landroidx/compose/ui/text/font/FontWeight;", ViewProps.FONT_STYLE, "Landroidx/compose/ui/text/font/FontStyle;", "createAndroidTypefaceApi28-RetOiIg", "(Ljava/lang/String;Landroidx/compose/ui/text/font/FontWeight;I)Landroid/graphics/Typeface;", "createDefault", "createDefault-FO1MlWM", "(Landroidx/compose/ui/text/font/FontWeight;I)Landroid/graphics/Typeface;", "createNamed", "name", "Landroidx/compose/ui/text/font/GenericFontFamily;", "createNamed-RetOiIg", "(Landroidx/compose/ui/text/font/GenericFontFamily;Landroidx/compose/ui/text/font/FontWeight;I)Landroid/graphics/Typeface;", "loadNamedFromTypefaceCacheOrNull", "familyName", "weight", "style", "loadNamedFromTypefaceCacheOrNull-RetOiIg", "optionalOnDeviceFontFamilyByName", "variationSettings", "Landroidx/compose/ui/text/font/FontVariation$Settings;", "context", "Landroid/content/Context;", "optionalOnDeviceFontFamilyByName-78DK7lM", "(Ljava/lang/String;Landroidx/compose/ui/text/font/FontWeight;ILandroidx/compose/ui/text/font/FontVariation$Settings;Landroid/content/Context;)Landroid/graphics/Typeface;", "ui-text_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
final class PlatformTypefacesApi28 implements PlatformTypefaces {
    @Override // androidx.compose.ui.text.font.PlatformTypefaces
    /* renamed from: optionalOnDeviceFontFamilyByName-78DK7lM */
    public android.graphics.Typeface mo4017optionalOnDeviceFontFamilyByName78DK7lM(String familyName, FontWeight weight, int style, FontVariation.Settings variationSettings, Context context) {
        android.graphics.Typeface typefaceMo4016createNamedRetOiIg;
        Intrinsics.checkNotNullParameter(familyName, "familyName");
        Intrinsics.checkNotNullParameter(weight, "weight");
        Intrinsics.checkNotNullParameter(variationSettings, "variationSettings");
        Intrinsics.checkNotNullParameter(context, "context");
        if (Intrinsics.areEqual(familyName, FontFamily.INSTANCE.getSansSerif().getName())) {
            typefaceMo4016createNamedRetOiIg = mo4016createNamedRetOiIg(FontFamily.INSTANCE.getSansSerif(), weight, style);
        } else if (Intrinsics.areEqual(familyName, FontFamily.INSTANCE.getSerif().getName())) {
            typefaceMo4016createNamedRetOiIg = mo4016createNamedRetOiIg(FontFamily.INSTANCE.getSerif(), weight, style);
        } else if (Intrinsics.areEqual(familyName, FontFamily.INSTANCE.getMonospace().getName())) {
            typefaceMo4016createNamedRetOiIg = mo4016createNamedRetOiIg(FontFamily.INSTANCE.getMonospace(), weight, style);
        } else {
            typefaceMo4016createNamedRetOiIg = Intrinsics.areEqual(familyName, FontFamily.INSTANCE.getCursive().getName()) ? mo4016createNamedRetOiIg(FontFamily.INSTANCE.getCursive(), weight, style) : m4023loadNamedFromTypefaceCacheOrNullRetOiIg(familyName, weight, style);
        }
        return PlatformTypefacesKt.setFontVariationSettings(typefaceMo4016createNamedRetOiIg, variationSettings, context);
    }

    @Override // androidx.compose.ui.text.font.PlatformTypefaces
    /* renamed from: createDefault-FO1MlWM */
    public android.graphics.Typeface mo4015createDefaultFO1MlWM(FontWeight fontWeight, int fontStyle) {
        Intrinsics.checkNotNullParameter(fontWeight, "fontWeight");
        return m4021createAndroidTypefaceApi28RetOiIg(null, fontWeight, fontStyle);
    }

    @Override // androidx.compose.ui.text.font.PlatformTypefaces
    /* renamed from: createNamed-RetOiIg */
    public android.graphics.Typeface mo4016createNamedRetOiIg(GenericFontFamily name, FontWeight fontWeight, int fontStyle) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(fontWeight, "fontWeight");
        return m4021createAndroidTypefaceApi28RetOiIg(name.getName(), fontWeight, fontStyle);
    }

    /* renamed from: loadNamedFromTypefaceCacheOrNull-RetOiIg, reason: not valid java name */
    private final android.graphics.Typeface m4023loadNamedFromTypefaceCacheOrNullRetOiIg(String familyName, FontWeight weight, int style) {
        if (familyName.length() == 0) {
            return null;
        }
        android.graphics.Typeface typefaceM4021createAndroidTypefaceApi28RetOiIg = m4021createAndroidTypefaceApi28RetOiIg(familyName, weight, style);
        boolean zM3990equalsimpl0 = FontStyle.m3990equalsimpl0(style, FontStyle.INSTANCE.m3994getItalic_LCdwA());
        TypefaceHelperMethodsApi28 typefaceHelperMethodsApi28 = TypefaceHelperMethodsApi28.INSTANCE;
        android.graphics.Typeface DEFAULT = android.graphics.Typeface.DEFAULT;
        Intrinsics.checkNotNullExpressionValue(DEFAULT, "DEFAULT");
        if (Intrinsics.areEqual(typefaceM4021createAndroidTypefaceApi28RetOiIg, typefaceHelperMethodsApi28.create(DEFAULT, weight.getWeight(), zM3990equalsimpl0)) || Intrinsics.areEqual(typefaceM4021createAndroidTypefaceApi28RetOiIg, m4021createAndroidTypefaceApi28RetOiIg(null, weight, style))) {
            return null;
        }
        return typefaceM4021createAndroidTypefaceApi28RetOiIg;
    }

    /* renamed from: createAndroidTypefaceApi28-RetOiIg$default, reason: not valid java name */
    static /* synthetic */ android.graphics.Typeface m4022createAndroidTypefaceApi28RetOiIg$default(PlatformTypefacesApi28 platformTypefacesApi28, String str, FontWeight fontWeight, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = null;
        }
        return platformTypefacesApi28.m4021createAndroidTypefaceApi28RetOiIg(str, fontWeight, i);
    }

    /* renamed from: createAndroidTypefaceApi28-RetOiIg, reason: not valid java name */
    private final android.graphics.Typeface m4021createAndroidTypefaceApi28RetOiIg(String genericFontFamily, FontWeight fontWeight, int fontStyle) {
        android.graphics.Typeface typefaceCreate;
        String str;
        if (FontStyle.m3990equalsimpl0(fontStyle, FontStyle.INSTANCE.m3995getNormal_LCdwA()) && Intrinsics.areEqual(fontWeight, FontWeight.INSTANCE.getNormal()) && ((str = genericFontFamily) == null || str.length() == 0)) {
            android.graphics.Typeface DEFAULT = android.graphics.Typeface.DEFAULT;
            Intrinsics.checkNotNullExpressionValue(DEFAULT, "DEFAULT");
            return DEFAULT;
        }
        if (genericFontFamily == null) {
            typefaceCreate = android.graphics.Typeface.DEFAULT;
        } else {
            typefaceCreate = android.graphics.Typeface.create(genericFontFamily, 0);
        }
        android.graphics.Typeface typefaceCreate2 = android.graphics.Typeface.create(typefaceCreate, fontWeight.getWeight(), FontStyle.m3990equalsimpl0(fontStyle, FontStyle.INSTANCE.m3994getItalic_LCdwA()));
        Intrinsics.checkNotNullExpressionValue(typefaceCreate2, "create(\n            fami…ontStyle.Italic\n        )");
        return typefaceCreate2;
    }
}
