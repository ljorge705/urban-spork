package com.clevertap.android.sdk.inapp.store.preference;

import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.StoreProvider;
import com.clevertap.android.sdk.cryption.CryptHandler;
import com.clevertap.android.sdk.login.ChangeUserCallback;
import com.clevertap.android.sdk.store.preference.ICTPreference;
import com.henninghall.date_picker.props.ModeProp;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: InAppStore.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0013\u0018\u0000 '2\u00020\u0001:\u0001'B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\nH\u0002J\u0018\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\nH\u0016J\u0006\u0010\u0018\u001a\u00020\bJ\u0006\u0010\u0019\u001a\u00020\u0012J\u0006\u0010\u001a\u001a\u00020\bJ\u0006\u0010\u001b\u001a\u00020\bJ\u0006\u0010\u001c\u001a\u00020\u0012J\b\u0010\u001d\u001a\u00020\u0015H\u0002J\b\u0010\u001e\u001a\u00020\u0015H\u0002J\u000e\u0010\u001f\u001a\u00020\u00152\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010 \u001a\u00020\u00152\u0006\u0010!\u001a\u00020\u0012J\u000e\u0010\"\u001a\u00020\u00152\u0006\u0010\u0010\u001a\u00020\bJ\u000e\u0010#\u001a\u00020\u00152\u0006\u0010$\u001a\u00020\bJ\u000e\u0010%\u001a\u00020\u00152\u0006\u0010&\u001a\u00020\u0012R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R(\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\t\u001a\u0004\u0018\u00010\n@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0010\u0010\u0010\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Lcom/clevertap/android/sdk/inapp/store/preference/InAppStore;", "Lcom/clevertap/android/sdk/login/ChangeUserCallback;", "ctPreference", "Lcom/clevertap/android/sdk/store/preference/ICTPreference;", "cryptHandler", "Lcom/clevertap/android/sdk/cryption/CryptHandler;", "(Lcom/clevertap/android/sdk/store/preference/ICTPreference;Lcom/clevertap/android/sdk/cryption/CryptHandler;)V", "clientSideInApps", "Lorg/json/JSONArray;", "value", "", ModeProp.name, "getMode", "()Ljava/lang/String;", "setMode", "(Ljava/lang/String;)V", "serverSideInApps", "migrateInAppHeaderPrefsForEventType", "Lorg/json/JSONObject;", "inAppIds", "onChangeUser", "", Constants.DEVICE_ID_TAG, Constants.KEY_ACCOUNT_ID, "readClientSideInApps", "readEvaluatedServerSideInAppIds", "readServerSideInApps", "readServerSideInAppsMetaData", "readSuppressedClientSideInAppIds", "removeClientSideInApps", "removeServerSideInAppsMetaData", "storeClientSideInApps", "storeEvaluatedServerSideInAppIds", "evaluatedServerSideInAppIds", "storeServerSideInApps", "storeServerSideInAppsMetaData", "serverSideInAppsMetaData", "storeSuppressedClientSideInAppIds", "suppressedClientSideInAppIds", "Companion", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class InAppStore implements ChangeUserCallback {
    public static final String CLIENT_SIDE_MODE = "CS";
    public static final String NO_MODE = "NO_MODE";
    public static final String SERVER_SIDE_MODE = "SS";
    private JSONArray clientSideInApps;
    private final CryptHandler cryptHandler;
    private final ICTPreference ctPreference;
    private String mode;
    private JSONArray serverSideInApps;

    public final String getMode() {
        return this.mode;
    }

    public InAppStore(ICTPreference ctPreference, CryptHandler cryptHandler) {
        Intrinsics.checkNotNullParameter(ctPreference, "ctPreference");
        Intrinsics.checkNotNullParameter(cryptHandler, "cryptHandler");
        this.ctPreference = ctPreference;
        this.cryptHandler = cryptHandler;
    }

    public final void setMode(String str) {
        if (Intrinsics.areEqual(this.mode, str)) {
            return;
        }
        this.mode = str;
        if (str != null) {
            int iHashCode = str.hashCode();
            if (iHashCode == -1437347487) {
                if (str.equals(NO_MODE)) {
                    removeServerSideInAppsMetaData();
                    removeClientSideInApps();
                    return;
                }
                return;
            }
            if (iHashCode == 2160) {
                if (str.equals(CLIENT_SIDE_MODE)) {
                    removeServerSideInAppsMetaData();
                }
            } else if (iHashCode == 2656 && str.equals(SERVER_SIDE_MODE)) {
                removeClientSideInApps();
            }
        }
    }

    private final void removeClientSideInApps() {
        this.ctPreference.remove("inapp_notifs_cs");
        this.clientSideInApps = null;
    }

    private final void removeServerSideInAppsMetaData() {
        this.ctPreference.remove("inapp_notifs_ss");
    }

    public final void storeClientSideInApps(JSONArray clientSideInApps) {
        Intrinsics.checkNotNullParameter(clientSideInApps, "clientSideInApps");
        this.clientSideInApps = clientSideInApps;
        CryptHandler cryptHandler = this.cryptHandler;
        String string = clientSideInApps.toString();
        Intrinsics.checkNotNullExpressionValue(string, "clientSideInApps.toString()");
        String strEncrypt = cryptHandler.encrypt(string);
        if (strEncrypt != null) {
            this.ctPreference.writeString("inapp_notifs_cs", strEncrypt);
        }
    }

    public final void storeServerSideInAppsMetaData(JSONArray serverSideInAppsMetaData) {
        Intrinsics.checkNotNullParameter(serverSideInAppsMetaData, "serverSideInAppsMetaData");
        ICTPreference iCTPreference = this.ctPreference;
        String string = serverSideInAppsMetaData.toString();
        Intrinsics.checkNotNullExpressionValue(string, "serverSideInAppsMetaData.toString()");
        iCTPreference.writeString("inapp_notifs_ss", string);
    }

    public final void storeServerSideInApps(JSONArray serverSideInApps) {
        Intrinsics.checkNotNullParameter(serverSideInApps, "serverSideInApps");
        this.serverSideInApps = serverSideInApps;
        CryptHandler cryptHandler = this.cryptHandler;
        String string = serverSideInApps.toString();
        Intrinsics.checkNotNullExpressionValue(string, "serverSideInApps.toString()");
        String strEncrypt = cryptHandler.encrypt(string);
        if (strEncrypt != null) {
            this.ctPreference.writeString(Constants.INAPP_KEY, strEncrypt);
        }
    }

    public final void storeEvaluatedServerSideInAppIds(JSONObject evaluatedServerSideInAppIds) {
        Intrinsics.checkNotNullParameter(evaluatedServerSideInAppIds, "evaluatedServerSideInAppIds");
        ICTPreference iCTPreference = this.ctPreference;
        String string = evaluatedServerSideInAppIds.toString();
        Intrinsics.checkNotNullExpressionValue(string, "evaluatedServerSideInAppIds.toString()");
        iCTPreference.writeString(Constants.PREFS_EVALUATED_INAPP_KEY_SS, string);
    }

    public final void storeSuppressedClientSideInAppIds(JSONObject suppressedClientSideInAppIds) {
        Intrinsics.checkNotNullParameter(suppressedClientSideInAppIds, "suppressedClientSideInAppIds");
        ICTPreference iCTPreference = this.ctPreference;
        String string = suppressedClientSideInAppIds.toString();
        Intrinsics.checkNotNullExpressionValue(string, "suppressedClientSideInAppIds.toString()");
        iCTPreference.writeString(Constants.PREFS_SUPPRESSED_INAPP_KEY_CS, string);
    }

    public final JSONArray readClientSideInApps() {
        JSONArray jSONArray;
        JSONArray jSONArray2 = this.clientSideInApps;
        if (jSONArray2 != null) {
            Intrinsics.checkNotNull(jSONArray2, "null cannot be cast to non-null type org.json.JSONArray");
            return jSONArray2;
        }
        String string = this.ctPreference.readString("inapp_notifs_cs", "");
        String str = string;
        if (str == null || StringsKt.isBlank(str)) {
            jSONArray = new JSONArray();
        } else {
            jSONArray = new JSONArray(this.cryptHandler.decrypt(string));
        }
        this.clientSideInApps = jSONArray;
        Intrinsics.checkNotNull(jSONArray, "null cannot be cast to non-null type org.json.JSONArray");
        return jSONArray;
    }

    public final JSONArray readServerSideInAppsMetaData() {
        String string = this.ctPreference.readString("inapp_notifs_ss", "");
        String str = string;
        if (str == null || StringsKt.isBlank(str)) {
            return new JSONArray();
        }
        return new JSONArray(string);
    }

    public final JSONObject readEvaluatedServerSideInAppIds() {
        String string = this.ctPreference.readString(Constants.PREFS_EVALUATED_INAPP_KEY_SS, "");
        String str = string;
        if (str == null || StringsKt.isBlank(str)) {
            return new JSONObject();
        }
        try {
            return new JSONObject(string);
        } catch (JSONException unused) {
            return migrateInAppHeaderPrefsForEventType(string);
        }
    }

    public final JSONObject readSuppressedClientSideInAppIds() {
        String string = this.ctPreference.readString(Constants.PREFS_SUPPRESSED_INAPP_KEY_CS, "");
        String str = string;
        if (str == null || StringsKt.isBlank(str)) {
            return new JSONObject();
        }
        try {
            return new JSONObject(string);
        } catch (JSONException unused) {
            return migrateInAppHeaderPrefsForEventType(string);
        }
    }

    private final JSONObject migrateInAppHeaderPrefsForEventType(String inAppIds) throws JSONException {
        JSONObject jSONObjectPut = new JSONObject().put(Constants.RAISED, new JSONArray(inAppIds));
        Intrinsics.checkNotNullExpressionValue(jSONObjectPut, "JSONObject().put(Constants.RAISED, jsonArray)");
        return jSONObjectPut;
    }

    public final JSONArray readServerSideInApps() {
        JSONArray jSONArray;
        JSONArray jSONArray2 = this.serverSideInApps;
        if (jSONArray2 != null) {
            Intrinsics.checkNotNull(jSONArray2, "null cannot be cast to non-null type org.json.JSONArray");
            return jSONArray2;
        }
        String string = this.ctPreference.readString(Constants.INAPP_KEY, "");
        String str = string;
        if (str == null || StringsKt.isBlank(str)) {
            jSONArray = new JSONArray();
        } else {
            jSONArray = new JSONArray(this.cryptHandler.decrypt(string));
        }
        this.serverSideInApps = jSONArray;
        Intrinsics.checkNotNull(jSONArray, "null cannot be cast to non-null type org.json.JSONArray");
        return jSONArray;
    }

    @Override // com.clevertap.android.sdk.login.ChangeUserCallback
    public void onChangeUser(String deviceId, String accountId) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(accountId, "accountId");
        this.ctPreference.changePreferenceName(StoreProvider.INSTANCE.getInstance().constructStorePreferenceName(1, deviceId, accountId));
    }
}
