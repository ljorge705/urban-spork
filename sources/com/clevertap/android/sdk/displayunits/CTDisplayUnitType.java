package com.clevertap.android.sdk.displayunits;

import android.text.TextUtils;
import android.util.Log;
import androidx.room.FtsOptions;
import com.clevertap.android.sdk.Constants;

/* loaded from: classes5.dex */
public enum CTDisplayUnitType {
    SIMPLE(FtsOptions.TOKENIZER_SIMPLE),
    SIMPLE_WITH_IMAGE("simple-image"),
    CAROUSEL("carousel"),
    CAROUSEL_WITH_IMAGE("carousel-image"),
    MESSAGE_WITH_ICON("message-icon"),
    CUSTOM_KEY_VALUE("custom-key-value");

    public final String type;

    @Override // java.lang.Enum
    public String toString() {
        return this.type;
    }

    public static CTDisplayUnitType type(String str) {
        if (!TextUtils.isEmpty(str)) {
            str.hashCode();
            switch (str) {
                case "carousel-image":
                    return CAROUSEL_WITH_IMAGE;
                case "message-icon":
                    return MESSAGE_WITH_ICON;
                case "simple":
                    return SIMPLE;
                case "custom-key-value":
                    return CUSTOM_KEY_VALUE;
                case "carousel":
                    return CAROUSEL;
                case "simple-image":
                    return SIMPLE_WITH_IMAGE;
            }
        }
        Log.d(Constants.FEATURE_DISPLAY_UNIT, "Unsupported Display Unit Type");
        return null;
    }

    CTDisplayUnitType(String str) {
        this.type = str;
    }
}
