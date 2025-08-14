package com.clevertap.react;

import android.content.Context;
import com.clevertap.android.sdk.CleverTapAPI;
import com.clevertap.android.sdk.inapp.customtemplates.CustomTemplateContext;
import com.clevertap.android.sdk.inapp.customtemplates.CustomTemplateException;
import com.clevertap.android.sdk.inapp.customtemplates.FunctionPresenter;
import com.clevertap.android.sdk.inapp.customtemplates.TemplatePresenter;
import com.facebook.common.util.UriUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import kotlin.Metadata;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CleverTapCustomTemplates.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\bH\u0002J)\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\n2\u0012\u0010\u000e\u001a\n\u0012\u0006\b\u0001\u0012\u00020\b0\u000f\"\u00020\bH\u0007¢\u0006\u0002\u0010\u0010R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/clevertap/react/CleverTapCustomTemplates;", "", "()V", "functionPresenter", "Lcom/clevertap/android/sdk/inapp/customtemplates/FunctionPresenter;", "templatePresenter", "Lcom/clevertap/android/sdk/inapp/customtemplates/TemplatePresenter;", "readAsset", "", "context", "Landroid/content/Context;", UriUtil.LOCAL_ASSET_SCHEME, "registerCustomTemplates", "", "jsonAssets", "", "(Landroid/content/Context;[Ljava/lang/String;)V", "clevertap-react-native_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class CleverTapCustomTemplates {
    public static final CleverTapCustomTemplates INSTANCE = new CleverTapCustomTemplates();
    private static final TemplatePresenter templatePresenter = new TemplatePresenter() { // from class: com.clevertap.react.CleverTapCustomTemplates$templatePresenter$1
        @Override // com.clevertap.android.sdk.inapp.customtemplates.CustomTemplatePresenter
        public void onPresent(CustomTemplateContext.TemplateContext context) {
            Intrinsics.checkNotNullParameter(context, "context");
            CleverTapEventEmitter.INSTANCE.emit(CleverTapEvent.CLEVERTAP_CUSTOM_TEMPLATE_PRESENT, context.getTemplateName());
        }

        @Override // com.clevertap.android.sdk.inapp.customtemplates.TemplatePresenter
        public void onClose(CustomTemplateContext.TemplateContext context) {
            Intrinsics.checkNotNullParameter(context, "context");
            CleverTapEventEmitter.INSTANCE.emit(CleverTapEvent.CLEVERTAP_CUSTOM_TEMPLATE_CLOSE, context.getTemplateName());
        }
    };
    private static final FunctionPresenter functionPresenter = new FunctionPresenter() { // from class: com.clevertap.react.CleverTapCustomTemplates$$ExternalSyntheticLambda0
        @Override // com.clevertap.android.sdk.inapp.customtemplates.CustomTemplatePresenter
        public final void onPresent(CustomTemplateContext customTemplateContext) {
            CleverTapCustomTemplates.functionPresenter$lambda$0((CustomTemplateContext.FunctionContext) customTemplateContext);
        }
    };

    private CleverTapCustomTemplates() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void functionPresenter$lambda$0(CustomTemplateContext.FunctionContext context) {
        Intrinsics.checkNotNullParameter(context, "context");
        CleverTapEventEmitter.INSTANCE.emit(CleverTapEvent.CLEVERTAP_CUSTOM_FUNCTION_PRESENT, context.getTemplateName());
    }

    @JvmStatic
    public static final void registerCustomTemplates(Context context, String... jsonAssets) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(jsonAssets, "jsonAssets");
        for (String str : jsonAssets) {
            CleverTapAPI.registerCustomInAppTemplates(INSTANCE.readAsset(context, str), templatePresenter, functionPresenter);
        }
    }

    private final String readAsset(Context context, String asset) throws IOException {
        try {
            InputStream inputStreamOpen = context.getAssets().open(asset);
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStreamOpen, StandardCharsets.UTF_8));
                StringBuilder sb = new StringBuilder();
                for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
                    sb.append(line);
                }
                String string = sb.toString();
                Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
                CloseableKt.closeFinally(inputStreamOpen, null);
                return string;
            } finally {
            }
        } catch (IOException e) {
            throw new CustomTemplateException("Could not read json asset", e);
        }
    }
}
