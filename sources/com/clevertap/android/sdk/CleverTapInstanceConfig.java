package com.clevertap.android.sdk;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.clevertap.android.sdk.CleverTapAPI;
import com.clevertap.android.sdk.cryption.CryptHandler;
import com.clevertap.android.sdk.login.LoginConstants;
import com.clevertap.android.sdk.pushnotification.PushNotificationUtil;
import com.clevertap.android.sdk.utils.CTJsonConverter;
import java.util.ArrayList;
import java.util.Arrays;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class CleverTapInstanceConfig implements Parcelable {
    public static final Parcelable.Creator<CleverTapInstanceConfig> CREATOR = new Parcelable.Creator<CleverTapInstanceConfig>() { // from class: com.clevertap.android.sdk.CleverTapInstanceConfig.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CleverTapInstanceConfig createFromParcel(Parcel parcel) {
            return new CleverTapInstanceConfig(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CleverTapInstanceConfig[] newArray(int i) {
            return new CleverTapInstanceConfig[i];
        }
    };
    private String accountId;
    private String accountRegion;
    private String accountToken;
    private ArrayList<String> allowedPushTypes;
    private boolean analyticsOnly;
    private boolean backgroundSync;
    private boolean beta;
    private boolean createdPostAppLaunch;
    private String customHandshakeDomain;
    private int debugLevel;
    private boolean disableAppLaunchedEvent;
    private boolean enableCustomCleverTapId;
    private int encryptionLevel;
    private String fcmSenderId;
    private String[] identityKeys;
    private boolean isDefaultInstance;
    private Logger logger;
    private String packageName;
    private boolean personalization;
    private String proxyDomain;
    private String spikyProxyDomain;
    private boolean sslPinning;
    private boolean useGoogleAdId;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void enablePersonalization(boolean z) {
        this.personalization = z;
    }

    public String getAccountId() {
        return this.accountId;
    }

    public String getAccountRegion() {
        return this.accountRegion;
    }

    public String getAccountToken() {
        return this.accountToken;
    }

    public ArrayList<String> getAllowedPushTypes() {
        return this.allowedPushTypes;
    }

    public String getCustomHandshakeDomain() {
        return this.customHandshakeDomain;
    }

    public int getDebugLevel() {
        return this.debugLevel;
    }

    public boolean getEnableCustomCleverTapId() {
        return this.enableCustomCleverTapId;
    }

    public int getEncryptionLevel() {
        return this.encryptionLevel;
    }

    public String getFcmSenderId() {
        return this.fcmSenderId;
    }

    public String[] getIdentityKeys() {
        return this.identityKeys;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public String getProxyDomain() {
        return this.proxyDomain;
    }

    public String getSpikyProxyDomain() {
        return this.spikyProxyDomain;
    }

    public boolean isAnalyticsOnly() {
        return this.analyticsOnly;
    }

    public boolean isBackgroundSync() {
        return this.backgroundSync;
    }

    public boolean isBeta() {
        return this.beta;
    }

    public boolean isCreatedPostAppLaunch() {
        return this.createdPostAppLaunch;
    }

    public boolean isDefaultInstance() {
        return this.isDefaultInstance;
    }

    boolean isDisableAppLaunchedEvent() {
        return this.disableAppLaunchedEvent;
    }

    boolean isPersonalizationEnabled() {
        return this.personalization;
    }

    public boolean isSslPinningEnabled() {
        return this.sslPinning;
    }

    boolean isUseGoogleAdId() {
        return this.useGoogleAdId;
    }

    public void setAnalyticsOnly(boolean z) {
        this.analyticsOnly = z;
    }

    public void setBackgroundSync(boolean z) {
        this.backgroundSync = z;
    }

    void setCreatedPostAppLaunch() {
        this.createdPostAppLaunch = true;
    }

    public void setCustomHandshakeDomain(String str) {
        this.customHandshakeDomain = str;
    }

    public void setDisableAppLaunchedEvent(boolean z) {
        this.disableAppLaunchedEvent = z;
    }

    public void setEnableCustomCleverTapId(boolean z) {
        this.enableCustomCleverTapId = z;
    }

    public void setProxyDomain(String str) {
        this.proxyDomain = str;
    }

    public void setSpikyProxyDomain(String str) {
        this.spikyProxyDomain = str;
    }

    public void useGoogleAdId(boolean z) {
        this.useGoogleAdId = z;
    }

    public static CleverTapInstanceConfig createInstance(Context context, String str, String str2) {
        return createInstance(context, str, str2, null);
    }

    public static CleverTapInstanceConfig createInstance(Context context, String str, String str2, String str3) {
        if (str == null || str2 == null) {
            Logger.i("CleverTap accountId and accountToken cannot be null");
            return null;
        }
        return createInstanceWithManifest(ManifestInfo.getInstance(context), str, str2, str3, false);
    }

    protected static CleverTapInstanceConfig createDefaultInstance(Context context, String str, String str2, String str3) {
        return createInstanceWithManifest(ManifestInfo.getInstance(context), str, str2, str3, true);
    }

    static CleverTapInstanceConfig createInstanceWithManifest(ManifestInfo manifestInfo, String str, String str2, String str3, boolean z) {
        return new CleverTapInstanceConfig(manifestInfo, str, str2, str3, z);
    }

    protected static CleverTapInstanceConfig createInstance(String str) {
        try {
            return new CleverTapInstanceConfig(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    CleverTapInstanceConfig(CleverTapInstanceConfig cleverTapInstanceConfig) {
        this.allowedPushTypes = PushNotificationUtil.getAll();
        this.identityKeys = Constants.NULL_STRING_ARRAY;
        this.accountId = cleverTapInstanceConfig.accountId;
        this.accountToken = cleverTapInstanceConfig.accountToken;
        this.accountRegion = cleverTapInstanceConfig.accountRegion;
        this.proxyDomain = cleverTapInstanceConfig.proxyDomain;
        this.spikyProxyDomain = cleverTapInstanceConfig.spikyProxyDomain;
        this.customHandshakeDomain = cleverTapInstanceConfig.customHandshakeDomain;
        this.isDefaultInstance = cleverTapInstanceConfig.isDefaultInstance;
        this.analyticsOnly = cleverTapInstanceConfig.analyticsOnly;
        this.personalization = cleverTapInstanceConfig.personalization;
        this.debugLevel = cleverTapInstanceConfig.debugLevel;
        this.logger = cleverTapInstanceConfig.logger;
        this.useGoogleAdId = cleverTapInstanceConfig.useGoogleAdId;
        this.disableAppLaunchedEvent = cleverTapInstanceConfig.disableAppLaunchedEvent;
        this.createdPostAppLaunch = cleverTapInstanceConfig.createdPostAppLaunch;
        this.sslPinning = cleverTapInstanceConfig.sslPinning;
        this.backgroundSync = cleverTapInstanceConfig.backgroundSync;
        this.enableCustomCleverTapId = cleverTapInstanceConfig.enableCustomCleverTapId;
        this.fcmSenderId = cleverTapInstanceConfig.fcmSenderId;
        this.packageName = cleverTapInstanceConfig.packageName;
        this.beta = cleverTapInstanceConfig.beta;
        this.identityKeys = cleverTapInstanceConfig.identityKeys;
        this.encryptionLevel = cleverTapInstanceConfig.encryptionLevel;
    }

    private CleverTapInstanceConfig(ManifestInfo manifestInfo, String str, String str2, String str3, boolean z) {
        this.allowedPushTypes = PushNotificationUtil.getAll();
        this.identityKeys = Constants.NULL_STRING_ARRAY;
        this.accountId = str;
        this.accountToken = str2;
        this.accountRegion = str3;
        this.isDefaultInstance = z;
        this.analyticsOnly = false;
        this.personalization = true;
        this.debugLevel = CleverTapAPI.LogLevel.INFO.intValue();
        this.logger = new Logger(this.debugLevel);
        this.createdPostAppLaunch = false;
        this.useGoogleAdId = manifestInfo.useGoogleAdId();
        this.disableAppLaunchedEvent = manifestInfo.isAppLaunchedDisabled();
        this.sslPinning = manifestInfo.isSSLPinningEnabled();
        this.backgroundSync = manifestInfo.isBackgroundSync();
        this.fcmSenderId = manifestInfo.getFCMSenderId();
        this.packageName = manifestInfo.getPackageName();
        this.enableCustomCleverTapId = manifestInfo.useCustomId();
        this.beta = manifestInfo.enableBeta();
        if (!this.isDefaultInstance) {
            this.encryptionLevel = 0;
            return;
        }
        this.encryptionLevel = manifestInfo.getEncryptionLevel();
        this.identityKeys = manifestInfo.getProfileKeys();
        log(LoginConstants.LOG_TAG_ON_USER_LOGIN, "Setting Profile Keys from Manifest: " + Arrays.toString(this.identityKeys));
    }

    private CleverTapInstanceConfig(String str) throws Throwable {
        this.allowedPushTypes = PushNotificationUtil.getAll();
        this.identityKeys = Constants.NULL_STRING_ARRAY;
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has(Constants.KEY_ACCOUNT_ID)) {
                this.accountId = jSONObject.getString(Constants.KEY_ACCOUNT_ID);
            }
            if (jSONObject.has(Constants.KEY_ACCOUNT_TOKEN)) {
                this.accountToken = jSONObject.getString(Constants.KEY_ACCOUNT_TOKEN);
            }
            if (jSONObject.has(Constants.KEY_PROXY_DOMAIN)) {
                this.proxyDomain = jSONObject.getString(Constants.KEY_PROXY_DOMAIN);
            }
            if (jSONObject.has(Constants.KEY_SPIKY_PROXY_DOMAIN)) {
                this.spikyProxyDomain = jSONObject.getString(Constants.KEY_SPIKY_PROXY_DOMAIN);
            }
            if (jSONObject.has(Constants.KEY_CUSTOM_HANDSHAKE_DOMAIN)) {
                this.customHandshakeDomain = jSONObject.optString(Constants.KEY_CUSTOM_HANDSHAKE_DOMAIN, null);
            }
            if (jSONObject.has(Constants.KEY_ACCOUNT_REGION)) {
                this.accountRegion = jSONObject.getString(Constants.KEY_ACCOUNT_REGION);
            }
            if (jSONObject.has(Constants.KEY_ANALYTICS_ONLY)) {
                this.analyticsOnly = jSONObject.getBoolean(Constants.KEY_ANALYTICS_ONLY);
            }
            if (jSONObject.has(Constants.KEY_DEFAULT_INSTANCE)) {
                this.isDefaultInstance = jSONObject.getBoolean(Constants.KEY_DEFAULT_INSTANCE);
            }
            if (jSONObject.has(Constants.KEY_USE_GOOGLE_AD_ID)) {
                this.useGoogleAdId = jSONObject.getBoolean(Constants.KEY_USE_GOOGLE_AD_ID);
            }
            if (jSONObject.has(Constants.KEY_DISABLE_APP_LAUNCHED)) {
                this.disableAppLaunchedEvent = jSONObject.getBoolean(Constants.KEY_DISABLE_APP_LAUNCHED);
            }
            if (jSONObject.has(Constants.KEY_PERSONALIZATION)) {
                this.personalization = jSONObject.getBoolean(Constants.KEY_PERSONALIZATION);
            }
            if (jSONObject.has(Constants.KEY_DEBUG_LEVEL)) {
                this.debugLevel = jSONObject.getInt(Constants.KEY_DEBUG_LEVEL);
            }
            this.logger = new Logger(this.debugLevel);
            if (jSONObject.has("packageName")) {
                this.packageName = jSONObject.getString("packageName");
            }
            if (jSONObject.has(Constants.KEY_CREATED_POST_APP_LAUNCH)) {
                this.createdPostAppLaunch = jSONObject.getBoolean(Constants.KEY_CREATED_POST_APP_LAUNCH);
            }
            if (jSONObject.has(Constants.KEY_SSL_PINNING)) {
                this.sslPinning = jSONObject.getBoolean(Constants.KEY_SSL_PINNING);
            }
            if (jSONObject.has(Constants.KEY_BACKGROUND_SYNC)) {
                this.backgroundSync = jSONObject.getBoolean(Constants.KEY_BACKGROUND_SYNC);
            }
            if (jSONObject.has(Constants.KEY_ENABLE_CUSTOM_CT_ID)) {
                this.enableCustomCleverTapId = jSONObject.getBoolean(Constants.KEY_ENABLE_CUSTOM_CT_ID);
            }
            if (jSONObject.has(Constants.KEY_FCM_SENDER_ID)) {
                this.fcmSenderId = jSONObject.getString(Constants.KEY_FCM_SENDER_ID);
            }
            if (jSONObject.has(Constants.KEY_BETA)) {
                this.beta = jSONObject.getBoolean(Constants.KEY_BETA);
            }
            if (jSONObject.has(Constants.KEY_IDENTITY_TYPES)) {
                this.identityKeys = (String[]) CTJsonConverter.toArray(jSONObject.getJSONArray(Constants.KEY_IDENTITY_TYPES));
            }
            if (jSONObject.has(Constants.KEY_ENCRYPTION_LEVEL)) {
                this.encryptionLevel = jSONObject.getInt(Constants.KEY_ENCRYPTION_LEVEL);
            }
        } catch (Throwable th) {
            Logger.v("Error constructing CleverTapInstanceConfig from JSON: " + str + ": ", th.getCause());
            throw th;
        }
    }

    private CleverTapInstanceConfig(Parcel parcel) {
        this.allowedPushTypes = PushNotificationUtil.getAll();
        this.identityKeys = Constants.NULL_STRING_ARRAY;
        this.accountId = parcel.readString();
        this.accountToken = parcel.readString();
        this.accountRegion = parcel.readString();
        this.proxyDomain = parcel.readString();
        this.spikyProxyDomain = parcel.readString();
        this.customHandshakeDomain = parcel.readString();
        this.analyticsOnly = parcel.readByte() != 0;
        this.isDefaultInstance = parcel.readByte() != 0;
        this.useGoogleAdId = parcel.readByte() != 0;
        this.disableAppLaunchedEvent = parcel.readByte() != 0;
        this.personalization = parcel.readByte() != 0;
        this.debugLevel = parcel.readInt();
        this.createdPostAppLaunch = parcel.readByte() != 0;
        this.sslPinning = parcel.readByte() != 0;
        this.backgroundSync = parcel.readByte() != 0;
        this.enableCustomCleverTapId = parcel.readByte() != 0;
        this.fcmSenderId = parcel.readString();
        this.packageName = parcel.readString();
        this.logger = new Logger(this.debugLevel);
        this.beta = parcel.readByte() != 0;
        ArrayList<String> arrayList = new ArrayList<>();
        this.allowedPushTypes = arrayList;
        parcel.readList(arrayList, String.class.getClassLoader());
        this.identityKeys = parcel.createStringArray();
        this.encryptionLevel = parcel.readInt();
    }

    public void setDebugLevel(CleverTapAPI.LogLevel logLevel) {
        setDebugLevel(logLevel.intValue());
    }

    public void setDebugLevel(int i) {
        this.debugLevel = i;
        Logger logger = this.logger;
        if (logger != null) {
            logger.setDebugLevel(i);
        }
    }

    public Logger getLogger() {
        if (this.logger == null) {
            this.logger = new Logger(this.debugLevel);
        }
        return this.logger;
    }

    public void log(String str, String str2) {
        this.logger.verbose(getDefaultSuffix(str), str2);
    }

    public void log(String str, String str2, Throwable th) {
        this.logger.verbose(getDefaultSuffix(str), str2, th);
    }

    public void setIdentityKeys(String... strArr) {
        if (this.isDefaultInstance) {
            return;
        }
        this.identityKeys = strArr;
        log(LoginConstants.LOG_TAG_ON_USER_LOGIN, "Setting Profile Keys via setter: " + Arrays.toString(this.identityKeys));
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.accountId);
        parcel.writeString(this.accountToken);
        parcel.writeString(this.accountRegion);
        parcel.writeString(this.proxyDomain);
        parcel.writeString(this.spikyProxyDomain);
        parcel.writeString(this.customHandshakeDomain);
        parcel.writeByte(this.analyticsOnly ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isDefaultInstance ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.useGoogleAdId ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.disableAppLaunchedEvent ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.personalization ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.debugLevel);
        parcel.writeByte(this.createdPostAppLaunch ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.sslPinning ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.backgroundSync ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.enableCustomCleverTapId ? (byte) 1 : (byte) 0);
        parcel.writeString(this.fcmSenderId);
        parcel.writeString(this.packageName);
        parcel.writeByte(this.beta ? (byte) 1 : (byte) 0);
        parcel.writeList(this.allowedPushTypes);
        parcel.writeStringArray(this.identityKeys);
        parcel.writeInt(this.encryptionLevel);
    }

    public void setEncryptionLevel(CryptHandler.EncryptionLevel encryptionLevel) {
        this.encryptionLevel = encryptionLevel.getValue();
    }

    String toJSONString() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(Constants.KEY_ACCOUNT_ID, getAccountId());
            jSONObject.put(Constants.KEY_ACCOUNT_TOKEN, getAccountToken());
            jSONObject.put(Constants.KEY_ACCOUNT_REGION, getAccountRegion());
            jSONObject.put(Constants.KEY_PROXY_DOMAIN, getProxyDomain());
            jSONObject.put(Constants.KEY_SPIKY_PROXY_DOMAIN, getSpikyProxyDomain());
            jSONObject.put(Constants.KEY_CUSTOM_HANDSHAKE_DOMAIN, getCustomHandshakeDomain());
            jSONObject.put(Constants.KEY_FCM_SENDER_ID, getFcmSenderId());
            jSONObject.put(Constants.KEY_ANALYTICS_ONLY, isAnalyticsOnly());
            jSONObject.put(Constants.KEY_DEFAULT_INSTANCE, isDefaultInstance());
            jSONObject.put(Constants.KEY_USE_GOOGLE_AD_ID, isUseGoogleAdId());
            jSONObject.put(Constants.KEY_DISABLE_APP_LAUNCHED, isDisableAppLaunchedEvent());
            jSONObject.put(Constants.KEY_PERSONALIZATION, isPersonalizationEnabled());
            jSONObject.put(Constants.KEY_DEBUG_LEVEL, getDebugLevel());
            jSONObject.put(Constants.KEY_CREATED_POST_APP_LAUNCH, isCreatedPostAppLaunch());
            jSONObject.put(Constants.KEY_SSL_PINNING, isSslPinningEnabled());
            jSONObject.put(Constants.KEY_BACKGROUND_SYNC, isBackgroundSync());
            jSONObject.put(Constants.KEY_ENABLE_CUSTOM_CT_ID, getEnableCustomCleverTapId());
            jSONObject.put("packageName", getPackageName());
            jSONObject.put(Constants.KEY_BETA, isBeta());
            jSONObject.put(Constants.KEY_ENCRYPTION_LEVEL, getEncryptionLevel());
            return jSONObject.toString();
        } catch (Throwable th) {
            Logger.v("Unable to convert config to JSON : ", th.getCause());
            return null;
        }
    }

    private String getDefaultSuffix(String str) {
        return "[" + (!TextUtils.isEmpty(str) ? ":" + str : "") + ":" + this.accountId + "]";
    }
}
