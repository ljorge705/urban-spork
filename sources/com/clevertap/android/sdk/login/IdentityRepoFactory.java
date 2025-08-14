package com.clevertap.android.sdk.login;

import android.content.Context;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.DeviceInfo;
import com.clevertap.android.sdk.validation.ValidationResultStack;

/* loaded from: classes5.dex */
public class IdentityRepoFactory {
    public static IdentityRepo getRepo(Context context, CleverTapInstanceConfig cleverTapInstanceConfig, DeviceInfo deviceInfo, ValidationResultStack validationResultStack) {
        IdentityRepo configurableIdentityRepo;
        if (new LoginInfoProvider(context, cleverTapInstanceConfig, deviceInfo).isLegacyProfileLoggedIn()) {
            configurableIdentityRepo = new LegacyIdentityRepo(cleverTapInstanceConfig);
        } else {
            configurableIdentityRepo = new ConfigurableIdentityRepo(context, cleverTapInstanceConfig, deviceInfo, validationResultStack);
        }
        cleverTapInstanceConfig.log(LoginConstants.LOG_TAG_ON_USER_LOGIN, "Repo provider: " + configurableIdentityRepo.getClass().getSimpleName());
        return configurableIdentityRepo;
    }

    private IdentityRepoFactory() {
    }
}
