package com.clevertap.android.sdk.inapp.store.preference;

import com.clevertap.android.sdk.CTXtensions;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.product_config.CTProductConfigConstants;
import com.clevertap.android.sdk.store.preference.ICTPreference;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONException;

/* compiled from: LegacyInAppStore.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\b\u001a\u00020\tJ\u0006\u0010\n\u001a\u00020\u000bJ\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u000bJ\u000e\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/clevertap/android/sdk/inapp/store/preference/LegacyInAppStore;", "", "ctPreference", "Lcom/clevertap/android/sdk/store/preference/ICTPreference;", Constants.KEY_ACCOUNT_ID, "", "(Lcom/clevertap/android/sdk/store/preference/ICTPreference;Ljava/lang/String;)V", "inAppKey", "lastCleanupTs", "", "readInApps", "Lorg/json/JSONArray;", "removeInApps", "", "storeInApps", "inApps", "updateAssetCleanupTs", CTProductConfigConstants.KEY_LAST_FETCHED_TIMESTAMP, "Companion", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class LegacyInAppStore {
    private static final String ASSETS_CLEANUP_TS_KEY = "last_assets_cleanup";
    private final ICTPreference ctPreference;
    private final String inAppKey;

    public LegacyInAppStore(ICTPreference ctPreference, String accountId) {
        Intrinsics.checkNotNullParameter(ctPreference, "ctPreference");
        Intrinsics.checkNotNullParameter(accountId, "accountId");
        this.ctPreference = ctPreference;
        this.inAppKey = CTXtensions.concatIfNotNull(Constants.INAPP_KEY, accountId, ":");
    }

    public final void storeInApps(JSONArray inApps) {
        Intrinsics.checkNotNullParameter(inApps, "inApps");
        ICTPreference iCTPreference = this.ctPreference;
        String str = this.inAppKey;
        Intrinsics.checkNotNull(str);
        String string = inApps.toString();
        Intrinsics.checkNotNullExpressionValue(string, "inApps.toString()");
        iCTPreference.writeStringImmediate(str, string);
    }

    public final JSONArray readInApps() {
        ICTPreference iCTPreference = this.ctPreference;
        String str = this.inAppKey;
        Intrinsics.checkNotNull(str);
        try {
            return new JSONArray(iCTPreference.readString(str, "[]"));
        } catch (JSONException unused) {
            return new JSONArray();
        }
    }

    public final void removeInApps() {
        ICTPreference iCTPreference = this.ctPreference;
        String str = this.inAppKey;
        Intrinsics.checkNotNull(str);
        iCTPreference.remove(str);
    }

    public final void updateAssetCleanupTs(long ts) {
        this.ctPreference.writeLong(ASSETS_CLEANUP_TS_KEY, ts);
    }

    public final long lastCleanupTs() {
        return this.ctPreference.readLong(ASSETS_CLEANUP_TS_KEY, 0L);
    }
}
