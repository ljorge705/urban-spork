package com.onfido.android.sdk.capture.utils;

import android.content.res.Resources;
import android.os.Build;
import androidx.core.os.ConfigurationCompat;
import com.onfido.javax.inject.Inject;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0007\b\u0007¢\u0006\u0002\u0010\u0002J\r\u0010\u0003\u001a\u00020\u0004H\u0000¢\u0006\u0002\b\u0005J\r\u0010\u0006\u001a\u00020\u0004H\u0000¢\u0006\u0002\b\u0007¨\u0006\b"}, d2 = {"Lcom/onfido/android/sdk/capture/utils/DeviceUtils;", "", "()V", "getDeviceLanguages", "", "getDeviceLanguages$onfido_capture_sdk_core_release", "getDeviceModel", "getDeviceModel$onfido_capture_sdk_core_release", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DeviceUtils {
    @Inject
    public DeviceUtils() {
    }

    public final String getDeviceLanguages$onfido_capture_sdk_core_release() {
        String languageTags = ConfigurationCompat.getLocales(Resources.getSystem().getConfiguration()).toLanguageTags();
        Intrinsics.checkNotNullExpressionValue(languageTags, "toLanguageTags(...)");
        return languageTags;
    }

    public final String getDeviceModel$onfido_capture_sdk_core_release() {
        String str = Build.MANUFACTURER;
        String str2 = Build.MODEL;
        Intrinsics.checkNotNull(str2);
        Locale locale = Locale.ROOT;
        String lowerCase = str2.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        Intrinsics.checkNotNull(str);
        String lowerCase2 = str.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
        return StringsKt.startsWith$default(lowerCase, lowerCase2, false, 2, (Object) null) ? str2 : str + ' ' + str2;
    }
}
