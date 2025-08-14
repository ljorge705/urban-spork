package com.onfido.android.sdk.capture.common.data;

import android.content.SharedPreferences;
import com.oblador.keychain.KeychainModule;
import com.onfido.android.sdk.capture.common.preferences.SharedPreferencesDataSource;
import com.onfido.android.sdk.capture.common.preferences.StorageKey;
import com.onfido.javax.inject.Inject;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/onfido/android/sdk/capture/common/data/ThemeDataSource;", "", KeychainModule.Maps.STORAGE, "Lcom/onfido/android/sdk/capture/common/preferences/SharedPreferencesDataSource;", "(Lcom/onfido/android/sdk/capture/common/preferences/SharedPreferencesDataSource;)V", "isDarkModeEnabled", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ThemeDataSource {
    private final SharedPreferencesDataSource storage;

    @Inject
    public ThemeDataSource(SharedPreferencesDataSource storage) {
        Intrinsics.checkNotNullParameter(storage, "storage");
        this.storage = storage;
    }

    public final boolean isDarkModeEnabled() {
        Boolean boolValueOf;
        Object locale;
        SharedPreferencesDataSource sharedPreferencesDataSource = this.storage;
        StorageKey storageKey = StorageKey.IS_IN_DARK_MODE;
        SharedPreferences prefs$onfido_capture_sdk_core_release = sharedPreferencesDataSource.getPrefs$onfido_capture_sdk_core_release();
        Intrinsics.checkNotNullExpressionValue(prefs$onfido_capture_sdk_core_release, "<get-prefs>(...)");
        String strName = storageKey.name();
        if (prefs$onfido_capture_sdk_core_release.contains(strName)) {
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Boolean.class);
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
                locale = prefs$onfido_capture_sdk_core_release.getString(strName, "");
                if (locale == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Boolean");
                }
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                locale = Integer.valueOf(prefs$onfido_capture_sdk_core_release.getInt(strName, -1));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                boolValueOf = Boolean.valueOf(prefs$onfido_capture_sdk_core_release.getBoolean(strName, false));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Float.TYPE))) {
                locale = Float.valueOf(prefs$onfido_capture_sdk_core_release.getFloat(strName, -1.0f));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                locale = Long.valueOf(prefs$onfido_capture_sdk_core_release.getLong(strName, -1L));
            } else {
                if (!Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Locale.class))) {
                    throw SharedPreferencesDataSource.Companion.getUNSUPPORTED_TYPE_EXCEPTION();
                }
                locale = sharedPreferencesDataSource.getLocale(prefs$onfido_capture_sdk_core_release, strName);
                if (locale == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Boolean");
                }
            }
            boolValueOf = (Boolean) locale;
        } else {
            boolValueOf = null;
        }
        if (boolValueOf != null) {
            return boolValueOf.booleanValue();
        }
        return false;
    }
}
