package com.clevertap.android.sdk;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;

/* loaded from: classes5.dex */
public class ManifestInfo {
    private static final String LABEL_ACCOUNT_ID = "CLEVERTAP_ACCOUNT_ID";
    private static final String LABEL_BACKGROUND_SYNC = "CLEVERTAP_BACKGROUND_SYNC";
    private static final String LABEL_BETA = "CLEVERTAP_BETA";
    private static final String LABEL_CLEVERTAP_HANDSHAKE_DOMAIN = "CLEVERTAP_HANDSHAKE_DOMAIN";
    private static final String LABEL_CUSTOM_ID = "CLEVERTAP_USE_CUSTOM_ID";
    private static final String LABEL_DEFAULT_CHANNEL_ID = "CLEVERTAP_DEFAULT_CHANNEL_ID";
    private static final String LABEL_DISABLE_APP_LAUNCH = "CLEVERTAP_DISABLE_APP_LAUNCHED";
    private static final String LABEL_ENCRYPTION_LEVEL = "CLEVERTAP_ENCRYPTION_LEVEL";
    private static final String LABEL_FCM_SENDER_ID = "FCM_SENDER_ID";
    private static final String LABEL_INAPP_EXCLUDE = "CLEVERTAP_INAPP_EXCLUDE";
    private static final String LABEL_INTENT_SERVICE = "CLEVERTAP_INTENT_SERVICE";
    public static final String LABEL_NOTIFICATION_ICON = "CLEVERTAP_NOTIFICATION_ICON";
    private static final String LABEL_PACKAGE_NAME = "CLEVERTAP_APP_PACKAGE";
    private static final String LABEL_PROXY_DOMAIN = "CLEVERTAP_PROXY_DOMAIN";
    private static final String LABEL_REGION = "CLEVERTAP_REGION";
    private static final String LABEL_SPIKY_PROXY_DOMAIN = "CLEVERTAP_SPIKY_PROXY_DOMAIN";
    private static final String LABEL_SSL_PINNING = "CLEVERTAP_SSL_PINNING";
    private static final String LABEL_TOKEN = "CLEVERTAP_TOKEN";
    private static final String LABEL_USE_GOOGLE_AD_ID = "CLEVERTAP_USE_GOOGLE_AD_ID";
    private static String accountId;
    private static String accountRegion;
    private static String accountToken;
    private static String handshakeDomain;
    private static ManifestInfo instance;
    private static String proxyDomain;
    private static String spikyProxyDomain;
    private final boolean appLaunchedDisabled;
    private final boolean backgroundSync;
    private final boolean beta;
    private final String devDefaultPushChannelId;
    private final int encryptionLevel;
    private final String excludedActivitiesForInApps;
    private final String fcmSenderId;
    private final String intentServiceName;
    private final String notificationIcon;
    private final String packageName;
    private final String[] profileKeys;
    private final boolean sslPinning;
    private final boolean useADID;
    private final boolean useCustomID;

    static void changeCredentials(String str, String str2, String str3) {
        accountId = str;
        accountToken = str2;
        accountRegion = str3;
    }

    static void changeCredentials(String str, String str2, String str3, String str4) {
        accountId = str;
        accountToken = str2;
        proxyDomain = str3;
        spikyProxyDomain = str4;
    }

    static void changeCredentials(String str, String str2, String str3, String str4, String str5) {
        accountId = str;
        accountToken = str2;
        proxyDomain = str3;
        spikyProxyDomain = str4;
        handshakeDomain = str5;
    }

    boolean enableBeta() {
        return this.beta;
    }

    public String getAccountId() {
        return accountId;
    }

    String getAcountToken() {
        return accountToken;
    }

    public String getDevDefaultPushChannelId() {
        return this.devDefaultPushChannelId;
    }

    public int getEncryptionLevel() {
        return this.encryptionLevel;
    }

    public String getExcludedActivities() {
        return this.excludedActivitiesForInApps;
    }

    public String getFCMSenderId() {
        return this.fcmSenderId;
    }

    public String getIntentServiceName() {
        return this.intentServiceName;
    }

    public String getNotificationIcon() {
        return this.notificationIcon;
    }

    String getPackageName() {
        return this.packageName;
    }

    public String[] getProfileKeys() {
        return this.profileKeys;
    }

    boolean isAppLaunchedDisabled() {
        return this.appLaunchedDisabled;
    }

    boolean isBackgroundSync() {
        return this.backgroundSync;
    }

    public boolean isSSLPinningEnabled() {
        return this.sslPinning;
    }

    boolean useCustomId() {
        return this.useCustomID;
    }

    boolean useGoogleAdId() {
        return this.useADID;
    }

    public static synchronized ManifestInfo getInstance(Context context) {
        if (instance == null) {
            instance = new ManifestInfo(context);
        }
        return instance;
    }

    private ManifestInfo(Context context) {
        Bundle bundle;
        try {
            bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
        } catch (Throwable unused) {
            bundle = null;
        }
        bundle = bundle == null ? new Bundle() : bundle;
        if (accountId == null) {
            accountId = _getManifestStringValueForKey(bundle, LABEL_ACCOUNT_ID);
        }
        if (accountToken == null) {
            accountToken = _getManifestStringValueForKey(bundle, LABEL_TOKEN);
        }
        if (accountRegion == null) {
            accountRegion = _getManifestStringValueForKey(bundle, LABEL_REGION);
        }
        if (proxyDomain == null) {
            proxyDomain = _getManifestStringValueForKey(bundle, LABEL_PROXY_DOMAIN);
        }
        if (spikyProxyDomain == null) {
            spikyProxyDomain = _getManifestStringValueForKey(bundle, LABEL_SPIKY_PROXY_DOMAIN);
        }
        if (handshakeDomain == null) {
            handshakeDomain = _getManifestStringValueForKey(bundle, LABEL_CLEVERTAP_HANDSHAKE_DOMAIN);
        }
        this.notificationIcon = _getManifestStringValueForKey(bundle, LABEL_NOTIFICATION_ICON);
        this.useADID = "1".equals(_getManifestStringValueForKey(bundle, LABEL_USE_GOOGLE_AD_ID));
        this.appLaunchedDisabled = "1".equals(_getManifestStringValueForKey(bundle, LABEL_DISABLE_APP_LAUNCH));
        this.excludedActivitiesForInApps = _getManifestStringValueForKey(bundle, LABEL_INAPP_EXCLUDE);
        this.sslPinning = "1".equals(_getManifestStringValueForKey(bundle, LABEL_SSL_PINNING));
        this.backgroundSync = "1".equals(_getManifestStringValueForKey(bundle, LABEL_BACKGROUND_SYNC));
        this.useCustomID = "1".equals(_getManifestStringValueForKey(bundle, LABEL_CUSTOM_ID));
        String str_getManifestStringValueForKey = _getManifestStringValueForKey(bundle, LABEL_FCM_SENDER_ID);
        this.fcmSenderId = str_getManifestStringValueForKey != null ? str_getManifestStringValueForKey.replace("id:", "") : str_getManifestStringValueForKey;
        int i = 0;
        try {
            int i2 = Integer.parseInt(_getManifestStringValueForKey(bundle, LABEL_ENCRYPTION_LEVEL));
            if (i2 < 0 || i2 > 1) {
                Logger.v("Supported encryption levels are only 0 and 1. Setting it to 0 by default");
            } else {
                i = i2;
            }
        } catch (Throwable th) {
            Logger.v("Unable to parse encryption level from the Manifest, Setting it to 0 by default", th.getCause());
        }
        this.encryptionLevel = i;
        this.packageName = _getManifestStringValueForKey(bundle, LABEL_PACKAGE_NAME);
        this.beta = "1".equals(_getManifestStringValueForKey(bundle, LABEL_BETA));
        this.intentServiceName = _getManifestStringValueForKey(bundle, LABEL_INTENT_SERVICE);
        this.devDefaultPushChannelId = _getManifestStringValueForKey(bundle, LABEL_DEFAULT_CHANNEL_ID);
        this.profileKeys = parseProfileKeys(bundle);
    }

    ManifestInfo(String str, String str2, String str3, String str4, String str5, String str6, boolean z, boolean z2, String str7, String str8, boolean z3, boolean z4, boolean z5, String str9, String str10, boolean z6, String str11, String str12, String[] strArr, int i) {
        if (accountId == null) {
            accountId = str;
        }
        if (accountToken == null) {
            accountToken = str2;
        }
        if (accountRegion == null) {
            accountRegion = str3;
        }
        if (proxyDomain == null) {
            proxyDomain = str4;
        }
        if (spikyProxyDomain == null) {
            spikyProxyDomain = str5;
        }
        if (handshakeDomain == null) {
            handshakeDomain = str6;
        }
        this.useADID = z;
        this.appLaunchedDisabled = z2;
        this.notificationIcon = str7;
        this.excludedActivitiesForInApps = str8;
        this.sslPinning = z3;
        this.backgroundSync = z4;
        this.useCustomID = z5;
        this.fcmSenderId = str9;
        this.packageName = str10;
        this.beta = z6;
        this.intentServiceName = str11;
        this.devDefaultPushChannelId = str12;
        this.profileKeys = strArr;
        this.encryptionLevel = i;
    }

    public String getAccountRegion() {
        Logger.v("ManifestInfo: getAccountRegion called, returning region:" + accountRegion);
        return accountRegion;
    }

    public String getProxyDomain() {
        Logger.v("ManifestInfo: getProxyDomain called, returning proxyDomain:" + proxyDomain);
        return proxyDomain;
    }

    public String getSpikeyProxyDomain() {
        Logger.v("ManifestInfo: getSpikeyProxyDomain called, returning spikeyProxyDomain:" + spikyProxyDomain);
        return spikyProxyDomain;
    }

    public String getHandshakeDomain() {
        Logger.v("ManifestInfo: getHandshakeDomain called, returning handshakeDomain:" + handshakeDomain);
        return handshakeDomain;
    }

    private String[] parseProfileKeys(Bundle bundle) {
        String str_getManifestStringValueForKey = _getManifestStringValueForKey(bundle, Constants.CLEVERTAP_IDENTIFIER);
        return !TextUtils.isEmpty(str_getManifestStringValueForKey) ? str_getManifestStringValueForKey.split(Constants.SEPARATOR_COMMA) : Constants.NULL_STRING_ARRAY;
    }

    private String _getManifestStringValueForKey(Bundle bundle, String str) {
        try {
            Object obj = bundle.get(str);
            if (obj != null) {
                return obj.toString();
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }
}
