package com.clevertap.android.sdk.cryption;

import android.content.Context;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.StorageHelper;
import com.clevertap.android.sdk.db.DBAdapter;
import com.clevertap.android.sdk.utils.CTJsonConverter;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: CryptUtils.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J(\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J(\u0010\r\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J8\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J(\u0010\u0013\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000fH\u0007J(\u0010\u0014\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\fH\u0007¨\u0006\u0016"}, d2 = {"Lcom/clevertap/android/sdk/cryption/CryptUtils;", "", "()V", "migrateCachedGuidsKeyPref", "", "encrypt", "", "config", "Lcom/clevertap/android/sdk/CleverTapInstanceConfig;", "context", "Landroid/content/Context;", "cryptHandler", "Lcom/clevertap/android/sdk/cryption/CryptHandler;", "migrateDBProfile", "dbAdapter", "Lcom/clevertap/android/sdk/db/DBAdapter;", "migrateEncryption", "", Constants.KEY_ENCRYPTION_FLAG_STATUS, "migrateEncryptionLevel", "updateEncryptionFlagOnFailure", "failedFlag", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class CryptUtils {
    public static final CryptUtils INSTANCE = new CryptUtils();

    private CryptUtils() {
    }

    @JvmStatic
    public static final void migrateEncryptionLevel(Context context, CleverTapInstanceConfig config, CryptHandler cryptHandler, DBAdapter dbAdapter) throws JSONException {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(cryptHandler, "cryptHandler");
        Intrinsics.checkNotNullParameter(dbAdapter, "dbAdapter");
        int encryptionLevel = config.getEncryptionLevel();
        int i = StorageHelper.getInt(context, StorageHelper.storageKeyWithSuffix(config, Constants.KEY_ENCRYPTION_LEVEL), -1);
        if (i == -1 && encryptionLevel == 0) {
            return;
        }
        int i2 = i != encryptionLevel ? 0 : StorageHelper.getInt(context, StorageHelper.storageKeyWithSuffix(config, Constants.KEY_ENCRYPTION_FLAG_STATUS), 0);
        StorageHelper.putInt(context, StorageHelper.storageKeyWithSuffix(config, Constants.KEY_ENCRYPTION_LEVEL), encryptionLevel);
        if (i2 == 3) {
            config.getLogger().verbose(config.getAccountId(), "Encryption flag status is 100% success, no need to migrate");
            cryptHandler.setEncryptionFlagStatus(3);
        } else {
            config.getLogger().verbose(config.getAccountId(), "Migrating encryption level from " + i + " to " + encryptionLevel + " with current flag status " + i2);
            INSTANCE.migrateEncryption(encryptionLevel == 1, context, config, cryptHandler, i2, dbAdapter);
        }
    }

    private final void migrateEncryption(boolean encrypt, Context context, CleverTapInstanceConfig config, CryptHandler cryptHandler, int encryptionFlagStatus, DBAdapter dbAdapter) throws JSONException {
        int iMigrateCachedGuidsKeyPref = encryptionFlagStatus & 1;
        if (iMigrateCachedGuidsKeyPref == 0) {
            iMigrateCachedGuidsKeyPref = migrateCachedGuidsKeyPref(encrypt, config, context, cryptHandler);
        }
        int iMigrateDBProfile = encryptionFlagStatus & 2;
        if (iMigrateDBProfile == 0) {
            iMigrateDBProfile = migrateDBProfile(encrypt, config, cryptHandler, dbAdapter);
        }
        int i = iMigrateCachedGuidsKeyPref | iMigrateDBProfile;
        config.getLogger().verbose(config.getAccountId(), "Updating encryption flag status to " + i);
        StorageHelper.putInt(context, StorageHelper.storageKeyWithSuffix(config, Constants.KEY_ENCRYPTION_FLAG_STATUS), i);
        cryptHandler.setEncryptionFlagStatus(i);
    }

    private final int migrateCachedGuidsKeyPref(boolean encrypt, CleverTapInstanceConfig config, Context context, CryptHandler cryptHandler) {
        String strDecrypt;
        config.getLogger().verbose(config.getAccountId(), "Migrating encryption level for cachedGUIDsKey prefs");
        String str = null;
        JSONObject jsonObject = CTJsonConverter.toJsonObject(StorageHelper.getStringFromPrefs(context, config, Constants.CACHED_GUIDS_KEY, null), config.getLogger(), config.getAccountId());
        JSONObject jSONObject = new JSONObject();
        try {
            Iterator<String> itKeys = jsonObject.keys();
            int i = 1;
            while (itKeys.hasNext()) {
                String nextJSONObjKey = itKeys.next();
                Intrinsics.checkNotNullExpressionValue(nextJSONObjKey, "nextJSONObjKey");
                String strSubstringBefore$default = StringsKt.substringBefore$default(nextJSONObjKey, "_", str, 2, str);
                String strSubstringAfter$default = StringsKt.substringAfter$default(nextJSONObjKey, "_", str, 2, str);
                if (encrypt) {
                    strDecrypt = cryptHandler.encrypt(strSubstringAfter$default, strSubstringBefore$default);
                } else {
                    strDecrypt = cryptHandler.decrypt(strSubstringAfter$default, Constants.KEY_ENCRYPTION_MIGRATION);
                }
                if (strDecrypt == null) {
                    config.getLogger().verbose(config.getAccountId(), "Error migrating " + strSubstringAfter$default + " in Cached Guid Key Pref");
                    i = 0;
                } else {
                    strSubstringAfter$default = strDecrypt;
                }
                jSONObject.put(strSubstringBefore$default + '_' + strSubstringAfter$default, jsonObject.get(nextJSONObjKey));
                str = null;
            }
            if (jsonObject.length() > 0) {
                String string = jSONObject.toString();
                Intrinsics.checkNotNullExpressionValue(string, "newGuidJsonObj.toString()");
                StorageHelper.putString(context, StorageHelper.storageKeyWithSuffix(config, Constants.CACHED_GUIDS_KEY), string);
                config.getLogger().verbose(config.getAccountId(), "setCachedGUIDs after migration:[" + string + AbstractJsonLexerKt.END_LIST);
            }
            return i;
        } catch (Throwable th) {
            config.getLogger().verbose(config.getAccountId(), "Error migrating cached guids: " + th);
            return 0;
        }
    }

    private final int migrateDBProfile(boolean encrypt, CleverTapInstanceConfig config, CryptHandler cryptHandler, DBAdapter dbAdapter) throws JSONException {
        String strDecrypt;
        config.getLogger().verbose(config.getAccountId(), "Migrating encryption level for user profiles in DB");
        int i = 2;
        for (Map.Entry<String, JSONObject> entry : dbAdapter.fetchUserProfilesByAccountId(config.getAccountId()).entrySet()) {
            JSONObject value = entry.getValue();
            try {
                Iterator<String> it = Constants.piiDBKeys.iterator();
                while (it.hasNext()) {
                    String piiKey = it.next();
                    if (value.has(piiKey)) {
                        Object obj = value.get(piiKey);
                        if (obj instanceof String) {
                            if (encrypt) {
                                Intrinsics.checkNotNullExpressionValue(piiKey, "piiKey");
                                strDecrypt = cryptHandler.encrypt((String) obj, piiKey);
                            } else {
                                strDecrypt = cryptHandler.decrypt((String) obj, Constants.KEY_ENCRYPTION_MIGRATION);
                            }
                            if (strDecrypt == null) {
                                config.getLogger().verbose(config.getAccountId(), "Error migrating " + piiKey + " entry in db profile");
                                strDecrypt = (String) obj;
                                i = 0;
                            }
                            value.put(piiKey, strDecrypt);
                        }
                    }
                }
            } catch (Exception e) {
                config.getLogger().verbose(config.getAccountId(), "Error migrating local DB profile for " + entry + ".key: " + e);
            }
            if (dbAdapter.storeUserProfile(config.getAccountId(), entry.getKey(), value) <= -1) {
                i = 0;
            }
        }
        return i;
    }

    @JvmStatic
    public static final void updateEncryptionFlagOnFailure(Context context, CleverTapInstanceConfig config, int failedFlag, CryptHandler cryptHandler) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(cryptHandler, "cryptHandler");
        int encryptionFlagStatus = (cryptHandler.getEncryptionFlagStatus() ^ failedFlag) & cryptHandler.getEncryptionFlagStatus();
        config.getLogger().verbose(config.getAccountId(), "Updating encryption flag status after error in " + failedFlag + " to " + encryptionFlagStatus);
        StorageHelper.putInt(context, StorageHelper.storageKeyWithSuffix(config, Constants.KEY_ENCRYPTION_FLAG_STATUS), encryptionFlagStatus);
        cryptHandler.setEncryptionFlagStatus(encryptionFlagStatus);
    }
}
