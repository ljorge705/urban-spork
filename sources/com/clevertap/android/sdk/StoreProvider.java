package com.clevertap.android.sdk;

import android.content.Context;
import com.clevertap.android.sdk.cryption.CryptHandler;
import com.clevertap.android.sdk.inapp.store.preference.FileStore;
import com.clevertap.android.sdk.inapp.store.preference.ImpressionStore;
import com.clevertap.android.sdk.inapp.store.preference.InAppAssetsStore;
import com.clevertap.android.sdk.inapp.store.preference.InAppStore;
import com.clevertap.android.sdk.inapp.store.preference.LegacyInAppStore;
import com.clevertap.android.sdk.store.preference.CTPreference;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* compiled from: StoreProvider.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u0005¢\u0006\u0002\u0010\u0002J\"\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0004H\u0007J\u0016\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\u0004J\u001e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004J\u0016\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\u0004J&\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004J\u0016\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\u0004¨\u0006\u001b"}, d2 = {"Lcom/clevertap/android/sdk/StoreProvider;", "", "()V", "constructStorePreferenceName", "", "storeType", "", Constants.DEVICE_ID_TAG, Constants.KEY_ACCOUNT_ID, "getCTPreference", "Lcom/clevertap/android/sdk/store/preference/CTPreference;", "context", "Landroid/content/Context;", "prefName", "provideFileStore", "Lcom/clevertap/android/sdk/inapp/store/preference/FileStore;", "provideImpressionStore", "Lcom/clevertap/android/sdk/inapp/store/preference/ImpressionStore;", "provideInAppAssetsStore", "Lcom/clevertap/android/sdk/inapp/store/preference/InAppAssetsStore;", "provideInAppStore", "Lcom/clevertap/android/sdk/inapp/store/preference/InAppStore;", "cryptHandler", "Lcom/clevertap/android/sdk/cryption/CryptHandler;", "provideLegacyInAppStore", "Lcom/clevertap/android/sdk/inapp/store/preference/LegacyInAppStore;", "Companion", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class StoreProvider {
    private static final String ASSET_STORE_PREFIX = "inapp_assets";

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String FILE_STORE_PREFIX = "ct_files";
    private static volatile StoreProvider INSTANCE;

    @JvmStatic
    public static final StoreProvider getInstance() {
        return INSTANCE.getInstance();
    }

    /* compiled from: StoreProvider.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\b\u001a\u00020\u0007H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/clevertap/android/sdk/StoreProvider$Companion;", "", "()V", "ASSET_STORE_PREFIX", "", "FILE_STORE_PREFIX", "INSTANCE", "Lcom/clevertap/android/sdk/StoreProvider;", "getInstance", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final StoreProvider getInstance() {
            StoreProvider storeProvider = StoreProvider.INSTANCE;
            if (storeProvider == null) {
                synchronized (this) {
                    storeProvider = StoreProvider.INSTANCE;
                    if (storeProvider == null) {
                        storeProvider = new StoreProvider();
                        Companion companion = StoreProvider.INSTANCE;
                        StoreProvider.INSTANCE = storeProvider;
                    }
                }
            }
            return storeProvider;
        }
    }

    public final InAppAssetsStore provideInAppAssetsStore(Context context, String accountId) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(accountId, "accountId");
        return new InAppAssetsStore(getCTPreference(context, constructStorePreferenceName$default(this, 4, null, accountId, 2, null)));
    }

    public final FileStore provideFileStore(Context context, String accountId) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(accountId, "accountId");
        return new FileStore(getCTPreference(context, constructStorePreferenceName$default(this, 5, null, accountId, 2, null)));
    }

    public final InAppStore provideInAppStore(Context context, CryptHandler cryptHandler, String deviceId, String accountId) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(cryptHandler, "cryptHandler");
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(accountId, "accountId");
        return new InAppStore(getCTPreference(context, constructStorePreferenceName(1, deviceId, accountId)), cryptHandler);
    }

    public final ImpressionStore provideImpressionStore(Context context, String deviceId, String accountId) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(accountId, "accountId");
        return new ImpressionStore(getCTPreference(context, constructStorePreferenceName(2, deviceId, accountId)));
    }

    public final LegacyInAppStore provideLegacyInAppStore(Context context, String accountId) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(accountId, "accountId");
        return new LegacyInAppStore(getCTPreference(context, constructStorePreferenceName$default(this, 3, null, null, 6, null)), accountId);
    }

    public final CTPreference getCTPreference(Context context, String prefName) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(prefName, "prefName");
        return new CTPreference(context, prefName);
    }

    public static /* synthetic */ String constructStorePreferenceName$default(StoreProvider storeProvider, int i, String str, String str2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            str = "";
        }
        if ((i2 & 4) != 0) {
            str2 = "";
        }
        return storeProvider.constructStorePreferenceName(i, str, str2);
    }

    public final String constructStorePreferenceName(int storeType, String deviceId, String accountId) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(accountId, "accountId");
        if (storeType == 1) {
            return "inApp:" + deviceId + AbstractJsonLexerKt.COLON + accountId;
        }
        if (storeType == 2) {
            return "counts_per_inapp:" + deviceId + AbstractJsonLexerKt.COLON + accountId;
        }
        if (storeType == 3) {
            return Constants.CLEVERTAP_STORAGE_TAG;
        }
        if (storeType != 4) {
            return storeType != 5 ? Constants.CLEVERTAP_STORAGE_TAG : "ct_files:" + accountId;
        }
        return "inapp_assets:" + accountId;
    }
}
