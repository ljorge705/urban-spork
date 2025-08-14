package com.onfido.android.sdk.capture.internal.util;

import android.content.SharedPreferences;
import com.onfido.android.sdk.capture.common.preferences.SharedPreferencesDataSource;
import com.onfido.android.sdk.capture.common.preferences.StorageKey;
import com.onfido.android.sdk.capture.internal.util.logging.Timber;
import com.onfido.android.sdk.capture.utils.TimeProvider;
import com.onfido.android.sdk.capture.utils.UuidProvider;
import com.onfido.javax.inject.Inject;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0000\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\r\u001a\u00020\u000eH\u0002J\b\u0010\u000f\u001a\u00020\nH\u0002J\b\u0010\u0010\u001a\u00020\fH\u0002J\u0006\u0010\u0011\u001a\u00020\nJ\b\u0010\u0012\u001a\u00020\u0013H\u0002R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/util/SessionIdProvider;", "", "uuidProvider", "Lcom/onfido/android/sdk/capture/utils/UuidProvider;", "timeProvider", "Lcom/onfido/android/sdk/capture/utils/TimeProvider;", "sharedPreferencesDataSource", "Lcom/onfido/android/sdk/capture/common/preferences/SharedPreferencesDataSource;", "(Lcom/onfido/android/sdk/capture/utils/UuidProvider;Lcom/onfido/android/sdk/capture/utils/TimeProvider;Lcom/onfido/android/sdk/capture/common/preferences/SharedPreferencesDataSource;)V", "sessionId", "", "sessionIdCreatedAt", "", "generateNewSessionId", "", "getPersistedSessionId", "getPersistedSessionIdCreatedAt", "getSessionId", "isSessionExpired", "", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class SessionIdProvider {
    private static final long SESSION_DURATION_MINUTE = 30;
    private volatile String sessionId;
    private volatile long sessionIdCreatedAt;
    private final SharedPreferencesDataSource sharedPreferencesDataSource;
    private final TimeProvider timeProvider;
    private final UuidProvider uuidProvider;

    @Inject
    public SessionIdProvider(UuidProvider uuidProvider, TimeProvider timeProvider, SharedPreferencesDataSource sharedPreferencesDataSource) {
        Intrinsics.checkNotNullParameter(uuidProvider, "uuidProvider");
        Intrinsics.checkNotNullParameter(timeProvider, "timeProvider");
        Intrinsics.checkNotNullParameter(sharedPreferencesDataSource, "sharedPreferencesDataSource");
        this.uuidProvider = uuidProvider;
        this.timeProvider = timeProvider;
        this.sharedPreferencesDataSource = sharedPreferencesDataSource;
        this.sessionId = "";
        this.sessionIdCreatedAt = getPersistedSessionIdCreatedAt();
        this.sessionId = getPersistedSessionId();
    }

    private final void generateNewSessionId() {
        long currentTimestamp = this.timeProvider.getCurrentTimestamp();
        this.sessionId = this.uuidProvider.getRandomUuid();
        this.sessionIdCreatedAt = currentTimestamp;
        SharedPreferencesDataSource sharedPreferencesDataSource = this.sharedPreferencesDataSource;
        StorageKey storageKey = StorageKey.SESSION_ID;
        String str = this.sessionId;
        SharedPreferences prefs$onfido_capture_sdk_core_release = sharedPreferencesDataSource.getPrefs$onfido_capture_sdk_core_release();
        Intrinsics.checkNotNullExpressionValue(prefs$onfido_capture_sdk_core_release, "<get-prefs>(...)");
        sharedPreferencesDataSource.set(prefs$onfido_capture_sdk_core_release, storageKey.name(), str);
        SharedPreferencesDataSource sharedPreferencesDataSource2 = this.sharedPreferencesDataSource;
        StorageKey storageKey2 = StorageKey.SESSION_ID_CREATED_AT;
        Long lValueOf = Long.valueOf(this.sessionIdCreatedAt);
        SharedPreferences prefs$onfido_capture_sdk_core_release2 = sharedPreferencesDataSource2.getPrefs$onfido_capture_sdk_core_release();
        Intrinsics.checkNotNullExpressionValue(prefs$onfido_capture_sdk_core_release2, "<get-prefs>(...)");
        sharedPreferencesDataSource2.set(prefs$onfido_capture_sdk_core_release2, storageKey2.name(), lValueOf);
    }

    private final String getPersistedSessionId() {
        String string;
        Object locale;
        SharedPreferencesDataSource sharedPreferencesDataSource = this.sharedPreferencesDataSource;
        StorageKey storageKey = StorageKey.SESSION_ID;
        SharedPreferences prefs$onfido_capture_sdk_core_release = sharedPreferencesDataSource.getPrefs$onfido_capture_sdk_core_release();
        Intrinsics.checkNotNullExpressionValue(prefs$onfido_capture_sdk_core_release, "<get-prefs>(...)");
        String strName = storageKey.name();
        if (prefs$onfido_capture_sdk_core_release.contains(strName)) {
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(String.class);
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
                string = prefs$onfido_capture_sdk_core_release.getString(strName, "");
                if (string == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                }
            } else {
                if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                    locale = Integer.valueOf(prefs$onfido_capture_sdk_core_release.getInt(strName, -1));
                } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                    locale = Boolean.valueOf(prefs$onfido_capture_sdk_core_release.getBoolean(strName, false));
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
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                    }
                }
                string = (String) locale;
            }
        } else {
            string = null;
        }
        return string == null ? "" : string;
    }

    private final long getPersistedSessionIdCreatedAt() {
        Object locale;
        Long lValueOf;
        Object locale2;
        String string = null;
        try {
            SharedPreferencesDataSource sharedPreferencesDataSource = this.sharedPreferencesDataSource;
            StorageKey storageKey = StorageKey.SESSION_ID_CREATED_AT;
            SharedPreferences prefs$onfido_capture_sdk_core_release = sharedPreferencesDataSource.getPrefs$onfido_capture_sdk_core_release();
            Intrinsics.checkNotNullExpressionValue(prefs$onfido_capture_sdk_core_release, "<get-prefs>(...)");
            String strName = storageKey.name();
            if (prefs$onfido_capture_sdk_core_release.contains(strName)) {
                KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Long.class);
                if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
                    locale2 = prefs$onfido_capture_sdk_core_release.getString(strName, "");
                    if (locale2 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Long");
                    }
                } else {
                    if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                        locale2 = Integer.valueOf(prefs$onfido_capture_sdk_core_release.getInt(strName, -1));
                    } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                        locale2 = Boolean.valueOf(prefs$onfido_capture_sdk_core_release.getBoolean(strName, false));
                    } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Float.TYPE))) {
                        locale2 = Float.valueOf(prefs$onfido_capture_sdk_core_release.getFloat(strName, -1.0f));
                    } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                        lValueOf = Long.valueOf(prefs$onfido_capture_sdk_core_release.getLong(strName, -1L));
                    } else {
                        if (!Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Locale.class))) {
                            throw SharedPreferencesDataSource.Companion.getUNSUPPORTED_TYPE_EXCEPTION();
                        }
                        locale2 = sharedPreferencesDataSource.getLocale(prefs$onfido_capture_sdk_core_release, strName);
                        if (locale2 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.Long");
                        }
                    }
                    lValueOf = (Long) locale2;
                }
                lValueOf = (Long) locale2;
            } else {
                lValueOf = null;
            }
            if (lValueOf == null) {
                lValueOf = 0L;
            }
            return lValueOf.longValue();
        } catch (ClassCastException e) {
            Timber.INSTANCE.i(e);
            SharedPreferencesDataSource sharedPreferencesDataSource2 = this.sharedPreferencesDataSource;
            StorageKey storageKey2 = StorageKey.SESSION_ID_CREATED_AT;
            SharedPreferences prefs$onfido_capture_sdk_core_release2 = sharedPreferencesDataSource2.getPrefs$onfido_capture_sdk_core_release();
            Intrinsics.checkNotNullExpressionValue(prefs$onfido_capture_sdk_core_release2, "<get-prefs>(...)");
            String strName2 = storageKey2.name();
            if (prefs$onfido_capture_sdk_core_release2.contains(strName2)) {
                KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(String.class);
                if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(String.class))) {
                    string = prefs$onfido_capture_sdk_core_release2.getString(strName2, "");
                    if (string == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                    }
                } else {
                    if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                        locale = Integer.valueOf(prefs$onfido_capture_sdk_core_release2.getInt(strName2, -1));
                    } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                        locale = Boolean.valueOf(prefs$onfido_capture_sdk_core_release2.getBoolean(strName2, false));
                    } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Float.TYPE))) {
                        locale = Float.valueOf(prefs$onfido_capture_sdk_core_release2.getFloat(strName2, -1.0f));
                    } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                        locale = Long.valueOf(prefs$onfido_capture_sdk_core_release2.getLong(strName2, -1L));
                    } else {
                        if (!Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Locale.class))) {
                            throw SharedPreferencesDataSource.Companion.getUNSUPPORTED_TYPE_EXCEPTION();
                        }
                        locale = sharedPreferencesDataSource2.getLocale(prefs$onfido_capture_sdk_core_release2, strName2);
                        if (locale == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                        }
                    }
                    string = (String) locale;
                }
            }
            if (string == null) {
                string = "0";
            }
            return Long.parseLong(string);
        }
    }

    private final boolean isSessionExpired() {
        return this.timeProvider.getCurrentTimestamp() - this.sessionIdCreatedAt > TimeUnit.MINUTES.toMillis(SESSION_DURATION_MINUTE);
    }

    public final synchronized String getSessionId() {
        if (isSessionExpired()) {
            generateNewSessionId();
        }
        return this.sessionId;
    }
}
