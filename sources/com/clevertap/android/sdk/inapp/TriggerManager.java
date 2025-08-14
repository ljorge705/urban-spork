package com.clevertap.android.sdk.inapp;

import android.content.Context;
import android.content.SharedPreferences;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.DeviceInfo;
import com.clevertap.android.sdk.StorageHelper;
import com.clevertap.android.sdk.db.Column;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* compiled from: TriggerManager.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0005J\u000e\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0005J\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0012\u001a\u00020\u0005J\u0018\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0005H\u0002J\u000e\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u0012\u001a\u00020\u0005J\b\u0010\u001b\u001a\u0004\u0018\u00010\u0018J \u0010\u001c\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u0011H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R(\u0010\t\u001a\u0010\u0012\f\u0012\n \u000b*\u0004\u0018\u00010\u00030\u00030\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/clevertap/android/sdk/inapp/TriggerManager;", "", "context", "Landroid/content/Context;", Constants.KEY_ACCOUNT_ID, "", "deviceInfo", "Lcom/clevertap/android/sdk/DeviceInfo;", "(Landroid/content/Context;Ljava/lang/String;Lcom/clevertap/android/sdk/DeviceInfo;)V", "contextRef", "Ljava/lang/ref/WeakReference;", "kotlin.jvm.PlatformType", "getContextRef", "()Ljava/lang/ref/WeakReference;", "setContextRef", "(Ljava/lang/ref/WeakReference;)V", "getTriggers", "", Column.CAMPAIGN, "getTriggersKey", "increment", "", "read", "prefs", "Landroid/content/SharedPreferences;", "storageKey", "removeTriggers", "sharedPrefs", "write", "triggerCount", "Companion", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class TriggerManager {
    public static final String PREF_PREFIX = "__triggers";
    private final String accountId;
    private WeakReference<Context> contextRef;
    private final DeviceInfo deviceInfo;

    public final WeakReference<Context> getContextRef() {
        return this.contextRef;
    }

    public final void setContextRef(WeakReference<Context> weakReference) {
        Intrinsics.checkNotNullParameter(weakReference, "<set-?>");
        this.contextRef = weakReference;
    }

    public TriggerManager(Context context, String accountId, DeviceInfo deviceInfo) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(accountId, "accountId");
        Intrinsics.checkNotNullParameter(deviceInfo, "deviceInfo");
        this.accountId = accountId;
        this.deviceInfo = deviceInfo;
        this.contextRef = new WeakReference<>(context);
    }

    public final int getTriggers(String campaignId) {
        Intrinsics.checkNotNullParameter(campaignId, "campaignId");
        SharedPreferences sharedPreferencesSharedPrefs = sharedPrefs();
        if (sharedPreferencesSharedPrefs == null) {
            return 0;
        }
        return read(sharedPreferencesSharedPrefs, getTriggersKey(campaignId));
    }

    public final void increment(String campaignId) {
        Intrinsics.checkNotNullParameter(campaignId, "campaignId");
        SharedPreferences sharedPreferencesSharedPrefs = sharedPrefs();
        if (sharedPreferencesSharedPrefs == null) {
            return;
        }
        write(sharedPreferencesSharedPrefs, getTriggersKey(campaignId), getTriggers(campaignId) + 1);
    }

    public final void removeTriggers(String campaignId) {
        Intrinsics.checkNotNullParameter(campaignId, "campaignId");
        SharedPreferences sharedPreferencesSharedPrefs = sharedPrefs();
        if (sharedPreferencesSharedPrefs == null) {
            return;
        }
        sharedPreferencesSharedPrefs.edit().remove(getTriggersKey(campaignId)).apply();
    }

    private final int read(SharedPreferences prefs, String storageKey) {
        return prefs.getInt(storageKey, 0);
    }

    private final void write(SharedPreferences prefs, String storageKey, int triggerCount) {
        prefs.edit().putInt(storageKey, triggerCount).apply();
    }

    public final SharedPreferences sharedPrefs() {
        String str = "triggers_per_inapp:" + this.deviceInfo.getDeviceID() + AbstractJsonLexerKt.COLON + this.accountId;
        Context context = this.contextRef.get();
        if (context == null) {
            return null;
        }
        return StorageHelper.getPreferences(context, str);
    }

    public final String getTriggersKey(String campaignId) {
        Intrinsics.checkNotNullParameter(campaignId, "campaignId");
        return "__triggers_" + campaignId;
    }
}
