package com.onfido.android.sdk.capture.ui;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcelable;
import com.onfido.android.sdk.capture.OnfidoConfig;
import com.onfido.android.sdk.capture.common.preferences.SharedPreferencesDataSource;
import com.onfido.android.sdk.capture.common.preferences.StorageKey;
import com.onfido.android.sdk.capture.internal.OnfidoConstants;
import com.onfido.api.client.JsonParserFactoryKt;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\"\u0010\u0007\u001a\u00020\b2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\bH\u0016J\u0012\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0002¨\u0006\u000f"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/OnfidoStarterService;", "Landroid/app/Service;", "()V", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onStartCommand", "", "flags", "startId", "saveSelectedLocale", "", "locale", "Ljava/util/Locale;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class OnfidoStarterService extends Service {
    private final void saveSelectedLocale(Locale locale) {
        SharedPreferencesDataSource sharedPreferencesDataSource = new SharedPreferencesDataSource(this, JsonParserFactoryKt.getJsonParserInstance());
        if (locale == null) {
            sharedPreferencesDataSource.delete$onfido_capture_sdk_core_release(StorageKey.SELECTED_LOCALE);
            return;
        }
        StorageKey storageKey = StorageKey.SELECTED_LOCALE;
        SharedPreferences prefs$onfido_capture_sdk_core_release = sharedPreferencesDataSource.getPrefs$onfido_capture_sdk_core_release();
        Intrinsics.checkNotNullExpressionValue(prefs$onfido_capture_sdk_core_release, "<get-prefs>(...)");
        sharedPreferencesDataSource.set(prefs$onfido_capture_sdk_core_release, storageKey.name(), locale);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        Parcelable parcelableExtra = intent.getParcelableExtra(OnfidoConstants.ONFIDO_CONFIG);
        OnfidoConfig onfidoConfig = parcelableExtra instanceof OnfidoConfig ? (OnfidoConfig) parcelableExtra : null;
        saveSelectedLocale(onfidoConfig != null ? onfidoConfig.getLocale() : null);
        return new Binder();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        stopSelf();
        return 1;
    }
}
