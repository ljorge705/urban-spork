package com.onfido.android.sdk.capture.utils;

import android.content.Context;
import android.content.res.Configuration;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¨\u0006\b"}, d2 = {"Lcom/onfido/android/sdk/capture/utils/LocalizationUtil;", "", "()V", "applyLanguage", "Landroid/content/Context;", "context", "locale", "Ljava/util/Locale;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class LocalizationUtil {
    public static final LocalizationUtil INSTANCE = new LocalizationUtil();

    private LocalizationUtil() {
    }

    public final Context applyLanguage(Context context, Locale locale) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (locale == null) {
            return context;
        }
        Configuration configuration = context.getResources().getConfiguration();
        Locale.setDefault(locale);
        configuration.setLocale(locale);
        Context contextCreateConfigurationContext = context.createConfigurationContext(configuration);
        Intrinsics.checkNotNullExpressionValue(contextCreateConfigurationContext, "createConfigurationContext(...)");
        return contextCreateConfigurationContext;
    }
}
