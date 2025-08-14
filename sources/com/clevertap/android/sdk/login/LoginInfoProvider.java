package com.clevertap.android.sdk.login;

import android.content.Context;
import android.text.TextUtils;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.DeviceInfo;
import com.clevertap.android.sdk.StorageHelper;
import com.clevertap.android.sdk.cryption.CryptHandler;
import com.clevertap.android.sdk.cryption.CryptUtils;
import com.clevertap.android.sdk.utils.CTJsonConverter;
import java.util.Iterator;
import java.util.Objects;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class LoginInfoProvider {
    private final CleverTapInstanceConfig config;
    private final Context context;
    private CryptHandler cryptHandler;
    private final DeviceInfo deviceInfo;

    public LoginInfoProvider(Context context, CleverTapInstanceConfig cleverTapInstanceConfig, DeviceInfo deviceInfo) {
        this.context = context;
        this.config = cleverTapInstanceConfig;
        this.deviceInfo = deviceInfo;
    }

    public LoginInfoProvider(Context context, CleverTapInstanceConfig cleverTapInstanceConfig, DeviceInfo deviceInfo, CryptHandler cryptHandler) {
        this.context = context;
        this.config = cleverTapInstanceConfig;
        this.deviceInfo = deviceInfo;
        this.cryptHandler = cryptHandler;
    }

    public void cacheGUIDForIdentifier(String str, String str2, String str3) {
        if (isErrorDeviceId() || str == null || str2 == null || str3 == null) {
            return;
        }
        String strEncrypt = this.cryptHandler.encrypt(str3, str2);
        if (strEncrypt == null) {
            CryptUtils.updateEncryptionFlagOnFailure(this.context, this.config, 1, this.cryptHandler);
        } else {
            str3 = strEncrypt;
        }
        String str4 = str2 + "_" + str3;
        JSONObject cachedGUIDs = getCachedGUIDs();
        try {
            cachedGUIDs.put(str4, str);
            setCachedGUIDs(cachedGUIDs);
        } catch (Throwable th) {
            this.config.getLogger().verbose(this.config.getAccountId(), "Error caching guid: " + th);
        }
    }

    public void removeValueFromCachedGUIDForIdentifier(String str, String str2) {
        if (isErrorDeviceId() || str == null || str2 == null) {
            return;
        }
        JSONObject cachedGUIDs = getCachedGUIDs();
        try {
            Iterator<String> itKeys = cachedGUIDs.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                if (next.toLowerCase().contains(str2.toLowerCase()) && cachedGUIDs.getString(next).equals(str)) {
                    cachedGUIDs.remove(next);
                    if (cachedGUIDs.length() == 0) {
                        removeCachedGuidFromSharedPrefs();
                    } else {
                        setCachedGUIDs(cachedGUIDs);
                    }
                }
            }
        } catch (Throwable th) {
            this.config.getLogger().verbose(this.config.getAccountId(), "Error removing cached key: " + th);
        }
    }

    public boolean deviceIsMultiUser() {
        boolean z = getCachedGUIDs().length() > 1;
        this.config.log(LoginConstants.LOG_TAG_ON_USER_LOGIN, "deviceIsMultiUser:[" + z + "]");
        return z;
    }

    public JSONObject getCachedGUIDs() {
        String stringFromPrefs = StorageHelper.getStringFromPrefs(this.context, this.config, Constants.CACHED_GUIDS_KEY, null);
        this.config.log(LoginConstants.LOG_TAG_ON_USER_LOGIN, "getCachedGUIDs:[" + stringFromPrefs + "]");
        return CTJsonConverter.toJsonObject(stringFromPrefs, this.config.getLogger(), this.config.getAccountId());
    }

    public void setCachedGUIDs(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        try {
            String string = jSONObject.toString();
            StorageHelper.putString(this.context, StorageHelper.storageKeyWithSuffix(this.config, Constants.CACHED_GUIDS_KEY), string);
            this.config.log(LoginConstants.LOG_TAG_ON_USER_LOGIN, "setCachedGUIDs:[" + string + "]");
        } catch (Throwable th) {
            this.config.getLogger().verbose(this.config.getAccountId(), "Error persisting guid cache: " + th);
        }
    }

    public void removeCachedGuidFromSharedPrefs() {
        try {
            StorageHelper.remove(this.context, StorageHelper.storageKeyWithSuffix(this.config, Constants.CACHED_GUIDS_KEY));
            this.config.log(LoginConstants.LOG_TAG_ON_USER_LOGIN, "removeCachedGUIDs:[]");
        } catch (Throwable th) {
            this.config.getLogger().verbose(this.config.getAccountId(), "Error removing guid cache: " + th);
        }
    }

    public String getCachedIdentityKeysForAccount() {
        String stringFromPrefs = StorageHelper.getStringFromPrefs(this.context, this.config, Constants.SP_KEY_PROFILE_IDENTITIES, "");
        this.config.log(LoginConstants.LOG_TAG_ON_USER_LOGIN, "getCachedIdentityKeysForAccount:" + stringFromPrefs);
        return stringFromPrefs;
    }

    public String getGUIDForIdentifier(String str, String str2) {
        if (str != null && str2 != null) {
            String strEncrypt = this.cryptHandler.encrypt(str2, str);
            String str3 = str + "_" + strEncrypt;
            JSONObject cachedGUIDs = getCachedGUIDs();
            try {
                String string = cachedGUIDs.getString(str3);
                this.config.log(LoginConstants.LOG_TAG_ON_USER_LOGIN, "getGUIDForIdentifier:[Key:" + str + ", value:" + string + "]");
                return string;
            } catch (Throwable th) {
                this.config.getLogger().verbose(this.config.getAccountId(), "Error reading guid cache: " + th);
                if (Objects.equals(strEncrypt, str2)) {
                    return null;
                }
                try {
                    String string2 = cachedGUIDs.getString(str + "_" + str2);
                    this.config.log(LoginConstants.LOG_TAG_ON_USER_LOGIN, "getGUIDForIdentifier:[Key:" + str + ", value:" + string2 + "] after retry");
                    return string2;
                } catch (Throwable th2) {
                    this.config.getLogger().verbose(this.config.getAccountId(), "Error reading guid cache after retry: " + th2);
                }
            }
        }
        return null;
    }

    public boolean isAnonymousDevice() {
        boolean z = getCachedGUIDs().length() <= 0;
        this.config.log(LoginConstants.LOG_TAG_ON_USER_LOGIN, "isAnonymousDevice:[" + z + "]");
        return z;
    }

    public boolean isLegacyProfileLoggedIn() {
        JSONObject cachedGUIDs = getCachedGUIDs();
        boolean z = cachedGUIDs != null && cachedGUIDs.length() > 0 && TextUtils.isEmpty(getCachedIdentityKeysForAccount());
        this.config.log(LoginConstants.LOG_TAG_ON_USER_LOGIN, "isLegacyProfileLoggedIn:" + z);
        return z;
    }

    public void saveIdentityKeysForAccount(String str) {
        StorageHelper.putString(this.context, this.config, Constants.SP_KEY_PROFILE_IDENTITIES, str);
        this.config.log(LoginConstants.LOG_TAG_ON_USER_LOGIN, "saveIdentityKeysForAccount:" + str);
    }

    private boolean isErrorDeviceId() {
        boolean zIsErrorDeviceId = this.deviceInfo.isErrorDeviceId();
        this.config.log(LoginConstants.LOG_TAG_ON_USER_LOGIN, "isErrorDeviceId:[" + zIsErrorDeviceId + "]");
        return zIsErrorDeviceId;
    }
}
