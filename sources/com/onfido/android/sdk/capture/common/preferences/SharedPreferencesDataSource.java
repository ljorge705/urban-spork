package com.onfido.android.sdk.capture.common.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.exifinterface.media.ExifInterface;
import com.clevertap.android.sdk.Constants;
import com.onfido.javax.inject.Inject;
import java.util.Locale;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.json.Json;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\f\b\u0017\u0018\u0000 .2\u00020\u0001:\u0001.B\u0019\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0015\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0000¢\u0006\u0002\b\u0014J&\u0010\u0015\u001a\u0004\u0018\u0001H\u0016\"\n\b\u0000\u0010\u0016\u0018\u0001*\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u0013H\u0080\b¢\u0006\u0004\b\u0017\u0010\u0018J\u0018\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u001cH\u0002J5\u0010\u001d\u001a\u0002H\u0016\"\n\b\u0000\u0010\u0016\u0018\u0001*\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u00132\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u00160\u001fH\u0080\bø\u0001\u0000¢\u0006\u0004\b \u0010!J\u0015\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u001cH\u0010¢\u0006\u0002\b%J,\u0010&\u001a\u00020\u0011\"\n\b\u0000\u0010\u0016\u0018\u0001*\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010'\u001a\u0002H\u0016H\u0080\b¢\u0006\u0004\b(\u0010)J\u0015\u0010*\u001a\u00020\u00112\u0006\u0010$\u001a\u00020\u001cH\u0010¢\u0006\u0002\b+J(\u0010\u0015\u001a\u0004\u0018\u0001H\u0016\"\n\b\u0000\u0010\u0016\u0018\u0001*\u00020\u0001*\u00020\b2\u0006\u0010\u0012\u001a\u00020\u001cH\u0082\b¢\u0006\u0002\u0010,J\u001e\u0010-\u001a\u00020\u0011*\u00020\b2\u0006\u0010\u0012\u001a\u00020\u001c2\b\u0010'\u001a\u0004\u0018\u00010\u0001H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R)\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\b8@X\u0081\u0084\u0002¢\u0006\u0012\n\u0004\b\u000e\u0010\u000f\u0012\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006/"}, d2 = {"Lcom/onfido/android/sdk/capture/common/preferences/SharedPreferencesDataSource;", "", "context", "Landroid/content/Context;", "jsonParser", "Lkotlinx/serialization/json/Json;", "(Landroid/content/Context;Lkotlinx/serialization/json/Json;)V", "prefs", "Landroid/content/SharedPreferences;", "kotlin.jvm.PlatformType", "getPrefs$onfido_capture_sdk_core_release$annotations", "()V", "getPrefs$onfido_capture_sdk_core_release", "()Landroid/content/SharedPreferences;", "prefs$delegate", "Lkotlin/Lazy;", "delete", "", Constants.KEY_KEY, "Lcom/onfido/android/sdk/capture/common/preferences/StorageKey;", "delete$onfido_capture_sdk_core_release", "get", ExifInterface.GPS_DIRECTION_TRUE, "get$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/common/preferences/StorageKey;)Ljava/lang/Object;", "getLocale", "Ljava/util/Locale;", "pref", "", "getOrDefault", com.facebook.hermes.intl.Constants.COLLATION_DEFAULT, "Lkotlin/Function0;", "getOrDefault$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/common/preferences/StorageKey;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "hasRequestedPermission", "", "permission", "hasRequestedPermission$onfido_capture_sdk_core_release", "save", "value", "save$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/common/preferences/StorageKey;Ljava/lang/Object;)V", "setHasRequestedPermission", "setHasRequestedPermission$onfido_capture_sdk_core_release", "(Landroid/content/SharedPreferences;Ljava/lang/String;)Ljava/lang/Object;", "set", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public class SharedPreferencesDataSource {
    private static final String LOCALE_ROOT = "locale-root";
    private static final String ONFIDO_PREFERENCES_ID = "ONFIDO_PREFERENCES";
    private final Json jsonParser;

    /* renamed from: prefs$delegate, reason: from kotlin metadata */
    private final Lazy prefs;
    private static final Companion Companion = new Companion(null);
    private static final Lazy<UnsupportedOperationException> UNSUPPORTED_TYPE_EXCEPTION$delegate = LazyKt.lazy(new Function0<UnsupportedOperationException>() { // from class: com.onfido.android.sdk.capture.common.preferences.SharedPreferencesDataSource$Companion$UNSUPPORTED_TYPE_EXCEPTION$2
        @Override // kotlin.jvm.functions.Function0
        public final UnsupportedOperationException invoke() {
            return new UnsupportedOperationException("Preference type not supported yet");
        }
    });

    /* JADX INFO: Access modifiers changed from: private */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u001f\u0010\u0006\u001a\u00060\u0007j\u0002`\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\n¨\u0006\r"}, d2 = {"Lcom/onfido/android/sdk/capture/common/preferences/SharedPreferencesDataSource$Companion;", "", "()V", "LOCALE_ROOT", "", "ONFIDO_PREFERENCES_ID", "UNSUPPORTED_TYPE_EXCEPTION", "Ljava/lang/UnsupportedOperationException;", "Lkotlin/UnsupportedOperationException;", "getUNSUPPORTED_TYPE_EXCEPTION", "()Ljava/lang/UnsupportedOperationException;", "UNSUPPORTED_TYPE_EXCEPTION$delegate", "Lkotlin/Lazy;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final UnsupportedOperationException getUNSUPPORTED_TYPE_EXCEPTION() {
            return (UnsupportedOperationException) SharedPreferencesDataSource.UNSUPPORTED_TYPE_EXCEPTION$delegate.getValue();
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Inject
    public SharedPreferencesDataSource(final Context context, Json jsonParser) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(jsonParser, "jsonParser");
        this.jsonParser = jsonParser;
        this.prefs = LazyKt.lazy(new Function0<SharedPreferences>() { // from class: com.onfido.android.sdk.capture.common.preferences.SharedPreferencesDataSource$prefs$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final SharedPreferences invoke() {
                return context.getSharedPreferences("ONFIDO_PREFERENCES", 0);
            }
        });
    }

    private final /* synthetic */ <T> T get(SharedPreferences sharedPreferences, String str) {
        Object locale;
        if (!sharedPreferences.contains(str)) {
            return null;
        }
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
            locale = sharedPreferences.getString(str, "");
        } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
            locale = Integer.valueOf(sharedPreferences.getInt(str, -1));
        } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
            locale = Boolean.valueOf(sharedPreferences.getBoolean(str, false));
        } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Float.TYPE))) {
            locale = Float.valueOf(sharedPreferences.getFloat(str, -1.0f));
        } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
            locale = Long.valueOf(sharedPreferences.getLong(str, -1L));
        } else {
            if (!Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Locale.class))) {
                throw Companion.getUNSUPPORTED_TYPE_EXCEPTION();
            }
            locale = getLocale(sharedPreferences, str);
        }
        Intrinsics.reifiedOperationMarker(1, ExifInterface.GPS_DIRECTION_TRUE);
        return (T) locale;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Locale getLocale(SharedPreferences pref, String key) {
        String string = pref.getString(key, null);
        if (Intrinsics.areEqual(string, LOCALE_ROOT)) {
            Locale locale = Locale.ROOT;
            Intrinsics.checkNotNull(locale);
            return locale;
        }
        if (string != null) {
            Json json = this.jsonParser;
            Locale locale2 = (Locale) json.decodeFromString(SerializersKt.serializer(json.getSerializersModule(), Reflection.nullableTypeOf(Locale.class)), string);
            if (locale2 != null) {
                return locale2;
            }
        }
        Locale locale3 = Locale.getDefault();
        Intrinsics.checkNotNullExpressionValue(locale3, "getDefault(...)");
        return locale3;
    }

    public static /* synthetic */ void getPrefs$onfido_capture_sdk_core_release$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void set(SharedPreferences sharedPreferences, String str, Object obj) {
        SharedPreferences.Editor editor;
        String strEncodeToString;
        if (obj != null && !(obj instanceof String)) {
            if (obj instanceof Integer) {
                editor = sharedPreferences.edit();
                Intrinsics.checkNotNullExpressionValue(editor, "editor");
                editor.putInt(str, ((Number) obj).intValue());
            } else if (obj instanceof Boolean) {
                editor = sharedPreferences.edit();
                Intrinsics.checkNotNullExpressionValue(editor, "editor");
                editor.putBoolean(str, ((Boolean) obj).booleanValue());
            } else if (obj instanceof Float) {
                editor = sharedPreferences.edit();
                Intrinsics.checkNotNullExpressionValue(editor, "editor");
                editor.putFloat(str, ((Number) obj).floatValue());
            } else if (obj instanceof Long) {
                editor = sharedPreferences.edit();
                Intrinsics.checkNotNullExpressionValue(editor, "editor");
                editor.putLong(str, ((Number) obj).longValue());
            } else {
                if (obj != null && !(obj instanceof Locale)) {
                    throw Companion.getUNSUPPORTED_TYPE_EXCEPTION();
                }
                editor = sharedPreferences.edit();
                Intrinsics.checkNotNullExpressionValue(editor, "editor");
                if (Intrinsics.areEqual(obj, Locale.ROOT)) {
                    strEncodeToString = LOCALE_ROOT;
                } else {
                    Json json = this.jsonParser;
                    strEncodeToString = json.encodeToString(SerializersKt.serializer(json.getSerializersModule(), Reflection.nullableTypeOf(Locale.class)), obj);
                }
            }
            editor.apply();
        }
        editor = sharedPreferences.edit();
        Intrinsics.checkNotNullExpressionValue(editor, "editor");
        strEncodeToString = (String) obj;
        editor.putString(str, strEncodeToString);
        editor.apply();
    }

    public final void delete$onfido_capture_sdk_core_release(StorageKey key) {
        Intrinsics.checkNotNullParameter(key, "key");
        SharedPreferences prefs$onfido_capture_sdk_core_release = getPrefs$onfido_capture_sdk_core_release();
        Intrinsics.checkNotNullExpressionValue(prefs$onfido_capture_sdk_core_release, "<get-prefs>(...)");
        SharedPreferences.Editor editor = prefs$onfido_capture_sdk_core_release.edit();
        Intrinsics.checkNotNullExpressionValue(editor, "editor");
        editor.remove(key.name());
        editor.apply();
    }

    public final /* synthetic */ <T> T get$onfido_capture_sdk_core_release(StorageKey key) {
        Object locale;
        Intrinsics.checkNotNullParameter(key, "key");
        SharedPreferences prefs$onfido_capture_sdk_core_release = getPrefs$onfido_capture_sdk_core_release();
        Intrinsics.checkNotNullExpressionValue(prefs$onfido_capture_sdk_core_release, "<get-prefs>(...)");
        String strName = key.name();
        if (!prefs$onfido_capture_sdk_core_release.contains(strName)) {
            return null;
        }
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
            locale = prefs$onfido_capture_sdk_core_release.getString(strName, "");
        } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
            locale = Integer.valueOf(prefs$onfido_capture_sdk_core_release.getInt(strName, -1));
        } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
            locale = Boolean.valueOf(prefs$onfido_capture_sdk_core_release.getBoolean(strName, false));
        } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Float.TYPE))) {
            locale = Float.valueOf(prefs$onfido_capture_sdk_core_release.getFloat(strName, -1.0f));
        } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
            locale = Long.valueOf(prefs$onfido_capture_sdk_core_release.getLong(strName, -1L));
        } else {
            if (!Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Locale.class))) {
                throw Companion.getUNSUPPORTED_TYPE_EXCEPTION();
            }
            locale = getLocale(prefs$onfido_capture_sdk_core_release, strName);
        }
        Intrinsics.reifiedOperationMarker(1, ExifInterface.GPS_DIRECTION_TRUE);
        return (T) locale;
    }

    public final /* synthetic */ <T> T getOrDefault$onfido_capture_sdk_core_release(StorageKey key, Function0<? extends T> function0) {
        T t;
        Object locale;
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(function0, "default");
        SharedPreferences prefs$onfido_capture_sdk_core_release = getPrefs$onfido_capture_sdk_core_release();
        Intrinsics.checkNotNullExpressionValue(prefs$onfido_capture_sdk_core_release, "<get-prefs>(...)");
        String strName = key.name();
        if (prefs$onfido_capture_sdk_core_release.contains(strName)) {
            Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
                locale = prefs$onfido_capture_sdk_core_release.getString(strName, "");
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                locale = Integer.valueOf(prefs$onfido_capture_sdk_core_release.getInt(strName, -1));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                locale = Boolean.valueOf(prefs$onfido_capture_sdk_core_release.getBoolean(strName, false));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Float.TYPE))) {
                locale = Float.valueOf(prefs$onfido_capture_sdk_core_release.getFloat(strName, -1.0f));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                locale = Long.valueOf(prefs$onfido_capture_sdk_core_release.getLong(strName, -1L));
            } else {
                if (!Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Locale.class))) {
                    throw Companion.getUNSUPPORTED_TYPE_EXCEPTION();
                }
                locale = getLocale(prefs$onfido_capture_sdk_core_release, strName);
            }
            Intrinsics.reifiedOperationMarker(1, ExifInterface.GPS_DIRECTION_TRUE);
            t = (T) locale;
        } else {
            t = null;
        }
        return t == null ? function0.invoke() : t;
    }

    public final SharedPreferences getPrefs$onfido_capture_sdk_core_release() {
        return (SharedPreferences) this.prefs.getValue();
    }

    public boolean hasRequestedPermission$onfido_capture_sdk_core_release(String permission) {
        Boolean boolValueOf;
        Object locale;
        Intrinsics.checkNotNullParameter(permission, "permission");
        SharedPreferences prefs$onfido_capture_sdk_core_release = getPrefs$onfido_capture_sdk_core_release();
        Intrinsics.checkNotNullExpressionValue(prefs$onfido_capture_sdk_core_release, "<get-prefs>(...)");
        if (prefs$onfido_capture_sdk_core_release.contains(permission)) {
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Boolean.class);
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
                locale = prefs$onfido_capture_sdk_core_release.getString(permission, "");
                if (locale == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Boolean");
                }
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                locale = Integer.valueOf(prefs$onfido_capture_sdk_core_release.getInt(permission, -1));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                boolValueOf = Boolean.valueOf(prefs$onfido_capture_sdk_core_release.getBoolean(permission, false));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Float.TYPE))) {
                locale = Float.valueOf(prefs$onfido_capture_sdk_core_release.getFloat(permission, -1.0f));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                locale = Long.valueOf(prefs$onfido_capture_sdk_core_release.getLong(permission, -1L));
            } else {
                if (!Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Locale.class))) {
                    throw Companion.getUNSUPPORTED_TYPE_EXCEPTION();
                }
                locale = getLocale(prefs$onfido_capture_sdk_core_release, permission);
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

    public final /* synthetic */ <T> void save$onfido_capture_sdk_core_release(StorageKey key, T value) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        SharedPreferences prefs$onfido_capture_sdk_core_release = getPrefs$onfido_capture_sdk_core_release();
        Intrinsics.checkNotNullExpressionValue(prefs$onfido_capture_sdk_core_release, "<get-prefs>(...)");
        set(prefs$onfido_capture_sdk_core_release, key.name(), value);
    }

    public void setHasRequestedPermission$onfido_capture_sdk_core_release(String permission) {
        Intrinsics.checkNotNullParameter(permission, "permission");
        SharedPreferences prefs$onfido_capture_sdk_core_release = getPrefs$onfido_capture_sdk_core_release();
        Intrinsics.checkNotNullExpressionValue(prefs$onfido_capture_sdk_core_release, "<get-prefs>(...)");
        set(prefs$onfido_capture_sdk_core_release, permission, Boolean.TRUE);
    }
}
