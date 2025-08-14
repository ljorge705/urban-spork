package com.onfido.hosted.web.module;

import android.content.Context;
import android.content.res.Resources;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.utils.FontInfo;
import com.onfido.hosted.web.module.WebModuleScriptBuilder;
import com.onfido.hosted.web.module.utils.ContextUtilsKt;
import io.sentry.protocol.OperatingSystem;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u001c\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0016\u0010\f\u001a\u00020\u000b*\u00020\u00062\b\b\u0001\u0010\r\u001a\u00020\u000eH\u0002¨\u0006\u000f"}, d2 = {"Lcom/onfido/hosted/web/module/WebModuleThemeBuilder;", "", "()V", OperatingSystem.JsonKeys.BUILD, "Lcom/onfido/hosted/web/module/WebModuleScriptBuilder$WebSDKTheme;", "context", "Landroid/content/Context;", "isDarkModuleEnabled", "", "getAndroidTheme", "", "", "resolveFontNameFromAttr", "attr", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class WebModuleThemeBuilder {
    public static final WebModuleThemeBuilder INSTANCE = new WebModuleThemeBuilder();

    private WebModuleThemeBuilder() {
    }

    private final Map<String, String> getAndroidTheme(Context context) {
        Resources resources = context.getResources();
        int dimension = (int) (resources.getDimension(R.dimen.onfidoButtonCornerRadius) / resources.getDisplayMetrics().density);
        Pair pair = TuplesKt.to("onfidoColorBackground", ContextUtilsKt.hexFrom(context, R.attr.onfidoColorBackground));
        Pair pair2 = TuplesKt.to("onfidoColorContentMain", ContextUtilsKt.hexFrom(context, R.attr.onfidoColorContentMain));
        Pair pair3 = TuplesKt.to("onfidoColorContentSecondary", ContextUtilsKt.hexFrom(context, R.attr.onfidoColorContentSecondary));
        Pair pair4 = TuplesKt.to("onfidoColorContentMainDark", ContextUtilsKt.hexFrom(context, R.attr.onfidoColorContentMainDark));
        Pair pair5 = TuplesKt.to("onfidoColorContentNegative", ContextUtilsKt.hexFrom(context, R.attr.onfidoColorContentNegative));
        Pair pair6 = TuplesKt.to("onfidoColorContentDisclaimer", ContextUtilsKt.hexFrom(context, R.attr.onfidoColorContentDisclaimer));
        Pair pair7 = TuplesKt.to("onfidoColorWatermark", ContextUtilsKt.hexFrom(context, R.attr.onfidoColorWatermark));
        Pair pair8 = TuplesKt.to("onfidoColorWatermarkDark", ContextUtilsKt.hexFrom(context, R.attr.onfidoColorWatermarkDark));
        Pair pair9 = TuplesKt.to("onfidoColorProgressIndicator", ContextUtilsKt.hexFrom(context, R.attr.onfidoColorProgressIndicator));
        Pair pair10 = TuplesKt.to("onfidoColorProgressTrack", ContextUtilsKt.hexFrom(context, R.attr.onfidoColorProgressTrack));
        Pair pair11 = TuplesKt.to("onfidoColorIconStrokeNegative", ContextUtilsKt.hexFrom(context, R.attr.onfidoColorIconStrokeNegative));
        Pair pair12 = TuplesKt.to("onfidoColorActionMain", ContextUtilsKt.hexFrom(context, R.attr.onfidoColorActionMain));
        Pair pair13 = TuplesKt.to("onfidoColorActionMainPressed", ContextUtilsKt.hexFrom(context, R.attr.onfidoColorActionMainPressed));
        Pair pair14 = TuplesKt.to("onfidoColorActionMainDisabled", ContextUtilsKt.hexFrom(context, R.attr.onfidoColorActionMainDisabled));
        Pair pair15 = TuplesKt.to("onfidoColorContentOnAction", ContextUtilsKt.hexFrom(context, R.attr.onfidoColorContentOnAction));
        Pair pair16 = TuplesKt.to("onfidoColorContentOnActionDisabled", ContextUtilsKt.hexFrom(context, R.attr.onfidoColorContentOnActionDisabled));
        Pair pair17 = TuplesKt.to("onfidoColorActionSecondary", ContextUtilsKt.hexFrom(context, R.attr.onfidoColorBackground));
        Pair pair18 = TuplesKt.to("onfidoColorActionSecondaryDisabled", ContextUtilsKt.hexFrom(context, R.attr.onfidoColorActionSecondaryDisabled));
        Pair pair19 = TuplesKt.to("onfidoColorActionSecondaryPressed", ContextUtilsKt.hexFrom(context, R.attr.onfidoColorActionSecondaryPressed));
        Pair pair20 = TuplesKt.to("onfidoColorContentOnActionSecondary", ContextUtilsKt.hexFrom(context, R.attr.onfidoColorContentOnActionSecondary));
        Pair pair21 = TuplesKt.to("onfidoColorContentOnActionSecondaryDisabled", ContextUtilsKt.hexFrom(context, R.attr.onfidoColorContentOnActionSecondaryDisabled));
        Pair pair22 = TuplesKt.to("onfidoColorActionSecondaryBorder", ContextUtilsKt.hexFrom(context, R.attr.onfidoColorActionSecondaryBorder));
        Pair pair23 = TuplesKt.to("onfidoColorActionSecondaryBorderDisabled", ContextUtilsKt.hexFrom(context, R.attr.onfidoColorActionSecondaryBorderDisabled));
        Pair pair24 = TuplesKt.to("onfidoColorToolbarBackground", ContextUtilsKt.hexFrom(context, R.attr.onfidoColorToolbarBackground));
        Pair pair25 = TuplesKt.to("onfidoColorContentToolbarTitle", ContextUtilsKt.hexFrom(context, R.attr.onfidoColorContentToolbarTitle));
        Pair pair26 = TuplesKt.to("onfidoColorIconStroke", ContextUtilsKt.hexFrom(context, R.attr.onfidoColorIconStroke));
        Pair pair27 = TuplesKt.to("onfidoColorIconFill", ContextUtilsKt.hexFrom(context, R.attr.onfidoColorIconFill));
        Pair pair28 = TuplesKt.to("onfidoColorIconBackground", ContextUtilsKt.hexFrom(context, R.attr.onfidoColorIconBackground));
        Pair pair29 = TuplesKt.to("onfidoColorIconAccent", ContextUtilsKt.hexFrom(context, R.attr.onfidoColorIconAccent));
        Pair pair30 = TuplesKt.to("onfidoColorIconDisclaimer", ContextUtilsKt.hexFrom(context, R.attr.onfidoColorIconDisclaimer));
        Pair pair31 = TuplesKt.to("onfidoColorDisclaimerBackground", ContextUtilsKt.hexFrom(context, R.attr.onfidoColorDisclaimerBackground));
        Pair pair32 = TuplesKt.to("onfidoBottomSheetBackground", ContextUtilsKt.hexFrom(context, R.attr.onfidoBottomSheetBackground));
        Pair pair33 = TuplesKt.to("onfidoColorProgressIndicatorIndeterminate", ContextUtilsKt.hexFrom(context, R.attr.onfidoColorProgressIndicatorIndeterminate));
        Pair pair34 = TuplesKt.to("onfidoColorProgressTrackIndeterminate", ContextUtilsKt.hexFrom(context, R.attr.onfidoColorProgressTrackIndeterminate));
        WebModuleThemeBuilder webModuleThemeBuilder = INSTANCE;
        Pair pair35 = TuplesKt.to("onfidoFontFamily", webModuleThemeBuilder.resolveFontNameFromAttr(context, R.attr.onfidoFontFamily));
        Pair pair36 = TuplesKt.to("onfidoFontFamilyTitleAttr", webModuleThemeBuilder.resolveFontNameFromAttr(context, R.attr.onfidoFontFamilyTitle));
        String strResolveFontNameFromAttr = webModuleThemeBuilder.resolveFontNameFromAttr(context, R.attr.onfidoFontFamilyBody);
        if (strResolveFontNameFromAttr.length() == 0) {
            strResolveFontNameFromAttr = webModuleThemeBuilder.resolveFontNameFromAttr(context, R.attr.onfidoFontFamily);
        }
        return MapsKt.mutableMapOf(pair, pair2, pair3, pair4, pair5, pair6, pair7, pair8, pair9, pair10, pair11, pair12, pair13, pair14, pair15, pair16, pair17, pair18, pair19, pair20, pair21, pair22, pair23, pair24, pair25, pair26, pair27, pair28, pair29, pair30, pair31, pair32, pair33, pair34, pair35, pair36, TuplesKt.to("onfidoFontFamilyBodyAttr", strResolveFontNameFromAttr), TuplesKt.to("onfidoFontFamilySubtitleAttr", webModuleThemeBuilder.resolveFontNameFromAttr(context, R.attr.onfidoFontFamilySubtitle)), TuplesKt.to("onfidoFontFamilyButtonAttr", webModuleThemeBuilder.resolveFontNameFromAttr(context, R.attr.onfidoFontFamilyButton)), TuplesKt.to("onfidoFontFamilyToolbarTitleAttr", webModuleThemeBuilder.resolveFontNameFromAttr(context, R.attr.onfidoFontFamilyToolbarTitle)), TuplesKt.to("onfidoFontFamilyDialogButtonAttr", webModuleThemeBuilder.resolveFontNameFromAttr(context, R.attr.onfidoFontFamilyDialogButton)), TuplesKt.to("onfidoButtonCornerRadius", dimension + "px"));
    }

    private final String resolveFontNameFromAttr(Context context, int i) {
        FontInfo fontInfoResolveFontFromAttr = com.onfido.android.sdk.capture.utils.ContextUtilsKt.resolveFontFromAttr(context, i);
        String fontName = fontInfoResolveFontFromAttr != null ? fontInfoResolveFontFromAttr.getFontName() : null;
        return fontName == null ? "" : fontName;
    }

    public final WebModuleScriptBuilder.WebSDKTheme build(Context context, boolean isDarkModuleEnabled) {
        WebModuleScriptBuilder.WebSDKTheme.Config configCopy$default;
        Intrinsics.checkNotNullParameter(context, "context");
        Map<String, String> androidTheme = getAndroidTheme(context);
        String str = androidTheme.get("onfidoFontFamilyTitleAttr");
        String str2 = androidTheme.get("onfidoFontFamilySubtitleAttr");
        WebModuleScriptBuilder.WebSDKTheme.Config config = new WebModuleScriptBuilder.WebSDKTheme.Config(null, null, null, null, 15, null);
        if (str == null || !StringsKt.endsWith$default(str, "-medium", false, 2, (Object) null)) {
            configCopy$default = config;
        } else {
            androidTheme.remove("onfidoFontFamilyTitleAttr");
            configCopy$default = new WebModuleScriptBuilder.WebSDKTheme.Config(StringsKt.removeSuffix(str, (CharSequence) "-medium"), 500, null, null, 12, null);
        }
        if (str2 != null && StringsKt.endsWith$default(str2, "-medium", false, 2, (Object) null)) {
            androidTheme.remove("onfidoFontFamilySubtitleAttr");
            configCopy$default = WebModuleScriptBuilder.WebSDKTheme.Config.copy$default(configCopy$default, null, null, StringsKt.removeSuffix(str2, (CharSequence) "-medium"), 500, 3, null);
        }
        return new WebModuleScriptBuilder.WebSDKTheme(isDarkModuleEnabled, androidTheme, configCopy$default);
    }
}
