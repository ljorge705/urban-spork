package com.onfido.android.sdk.capture.internal.navigation;

import com.onfido.android.sdk.capture.internal.navigation.Screen;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001R\u0014\u0010\u0002\u001a\u00020\u00038gX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/navigation/ScreenWithResIdTitle;", "Lcom/onfido/android/sdk/capture/internal/navigation/Screen;", "titleResId", "", "getTitleResId", "()I", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface ScreenWithResIdTitle extends Screen {

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class DefaultImpls {
        public static String screenKey(ScreenWithResIdTitle screenWithResIdTitle) {
            return Screen.DefaultImpls.screenKey(screenWithResIdTitle);
        }
    }

    int getTitleResId();
}
