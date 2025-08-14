package com.clevertap.android.sdk.inapp.store.preference;

import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.StoreProvider;
import com.clevertap.android.sdk.db.Column;
import com.clevertap.android.sdk.login.ChangeUserCallback;
import com.clevertap.android.sdk.store.preference.ICTPreference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: ImpressionStore.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0010\t\n\u0002\b\u000b\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\bH\u0002J\u0018\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\bH\u0016J\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u0007\u001a\u00020\bJ\u001e\u0010\u0011\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\b2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0002J\u0016\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/clevertap/android/sdk/inapp/store/preference/ImpressionStore;", "Lcom/clevertap/android/sdk/login/ChangeUserCallback;", "ctPreference", "Lcom/clevertap/android/sdk/store/preference/ICTPreference;", "(Lcom/clevertap/android/sdk/store/preference/ICTPreference;)V", "clear", "", Column.CAMPAIGN, "", "getLongListFromPrefs", "", "", Constants.KEY_KEY, "onChangeUser", Constants.DEVICE_ID_TAG, Constants.KEY_ACCOUNT_ID, "read", "saveLongListToPrefs", "list", "write", "timestamp", "Companion", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class ImpressionStore implements ChangeUserCallback {
    public static final String PREF_PREFIX = "__impressions";
    private final ICTPreference ctPreference;

    public ImpressionStore(ICTPreference ctPreference) {
        Intrinsics.checkNotNullParameter(ctPreference, "ctPreference");
        this.ctPreference = ctPreference;
    }

    public final List<Long> read(String campaignId) {
        Intrinsics.checkNotNullParameter(campaignId, "campaignId");
        return getLongListFromPrefs("__impressions_" + campaignId);
    }

    public final void write(String campaignId, long timestamp) {
        Intrinsics.checkNotNullParameter(campaignId, "campaignId");
        List<Long> mutableList = CollectionsKt.toMutableList((Collection) read(campaignId));
        mutableList.add(Long.valueOf(timestamp));
        saveLongListToPrefs("__impressions_" + campaignId, mutableList);
    }

    public final void clear(String campaignId) {
        Intrinsics.checkNotNullParameter(campaignId, "campaignId");
        this.ctPreference.remove("__impressions_" + campaignId);
    }

    private final void saveLongListToPrefs(String key, List<Long> list) {
        this.ctPreference.writeString(key, CollectionsKt.joinToString$default(list, Constants.SEPARATOR_COMMA, null, null, 0, null, null, 62, null));
    }

    private final List<Long> getLongListFromPrefs(String key) {
        String string = this.ctPreference.readString(key, "");
        if (string == null || StringsKt.isBlank(string)) {
            return CollectionsKt.emptyList();
        }
        List listSplit$default = StringsKt.split$default((CharSequence) string, new String[]{Constants.SEPARATOR_COMMA}, false, 0, 6, (Object) null);
        ArrayList arrayList = new ArrayList();
        Iterator it = listSplit$default.iterator();
        while (it.hasNext()) {
            Long longOrNull = StringsKt.toLongOrNull((String) it.next());
            if (longOrNull != null) {
                arrayList.add(longOrNull);
            }
        }
        return arrayList;
    }

    @Override // com.clevertap.android.sdk.login.ChangeUserCallback
    public void onChangeUser(String deviceId, String accountId) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(accountId, "accountId");
        this.ctPreference.changePreferenceName(StoreProvider.INSTANCE.getInstance().constructStorePreferenceName(2, deviceId, accountId));
    }
}
