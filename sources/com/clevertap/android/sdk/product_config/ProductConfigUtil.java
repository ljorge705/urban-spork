package com.clevertap.android.sdk.product_config;

import com.clevertap.android.sdk.CleverTapInstanceConfig;

@Deprecated
/* loaded from: classes5.dex */
class ProductConfigUtil {
    ProductConfigUtil() {
    }

    @Deprecated
    static String getLogTag(CleverTapInstanceConfig cleverTapInstanceConfig) {
        return (cleverTapInstanceConfig != null ? cleverTapInstanceConfig.getAccountId() : "") + CTProductConfigConstants.TAG_PRODUCT_CONFIG;
    }

    @Deprecated
    static boolean isSupportedDataType(Object obj) {
        return (obj instanceof String) || (obj instanceof Number) || (obj instanceof Boolean);
    }
}
