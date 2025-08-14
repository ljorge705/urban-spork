package com.singular.sdk.internal;

import android.content.Context;
import android.content.pm.InstallSourceInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.webkit.WebSettings;
import androidx.autofill.HintConstants;
import com.facebook.fbreact.specs.NativeDeviceInfoSpec;
import com.google.android.material.timepicker.TimeModel;
import java.util.Locale;

/* loaded from: classes6.dex */
class DeviceInfo {
    private static final SingularLog logger = SingularLog.getLogger(NativeDeviceInfoSpec.NAME);
    String abi;
    String aifa;
    String amid;
    String andi;
    String apiLevel;
    String appName;
    String appVersion;
    String asid;
    String buildProduct;
    String country;
    String customUserId;
    String deviceBrand;
    String deviceBuild;
    String deviceManufacturer;
    String deviceModel;
    String deviceType;
    String deviceUserAgent;
    String displayHeight;
    String displayWidth;
    String fcmRegId;
    long firstInstallTime;
    String gcmProcessId;
    String gcmRegId;
    String hardwareName;
    String imei;
    String installSource;
    boolean isAmazonAvailable;
    boolean isAmazonLimitAdTrackingEnabled;
    boolean isGooglePlayServicesAvailable;
    boolean isLimitedTrackingEnabled;
    String language;
    long lastUpdateTime;
    Locale locale;
    String oaid;
    String osVersion;
    String packageName;
    String platform;
    String preloadCampaign;
    String preloadGroup;
    String preloadSource;
    String screenDensity;
    String screenFormat;
    String screenSize;
    SLSingularDeviceIdentifier sdid;
    String sdkVersion;

    private String getDeviceType(int i) {
        int i2 = i & 15;
        if (i2 == 1 || i2 == 2) {
            return HintConstants.AUTOFILL_HINT_PHONE;
        }
        if (i2 == 3 || i2 == 4) {
            return com.clevertap.android.sdk.Constants.KEY_IS_TABLET;
        }
        return null;
    }

    private String getScreenFormat(int i) {
        int i2 = i & 48;
        if (i2 == 16) {
            return "normal";
        }
        if (i2 != 32) {
            return null;
        }
        return Constants.LONG;
    }

    private String getScreenSize(int i) {
        int i2 = i & 15;
        if (i2 == 1) {
            return Constants.SMALL;
        }
        if (i2 == 2) {
            return "normal";
        }
        if (i2 == 3) {
            return Constants.LARGE;
        }
        if (i2 != 4) {
            return null;
        }
        return Constants.XLARGE;
    }

    void setCustomUserId(String str) {
        this.customUserId = str;
    }

    void setFcmRegId(String str) {
        this.fcmRegId = str;
    }

    void setGcmRegId(String str) {
        this.gcmRegId = str;
    }

    void setImei(String str) {
        this.imei = str;
    }

    DeviceInfo(Context context, boolean z, Boolean bool) throws PackageManager.NameNotFoundException, Settings.SettingNotFoundException {
        setAppInfo(context);
        setPreloadCampaign(context);
        Resources resources = context.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();
        Locale locale = Utils.getLocale(configuration);
        this.locale = locale;
        this.language = locale.getLanguage();
        this.country = this.locale.getCountry();
        int i = configuration.screenLayout;
        this.screenSize = getScreenSize(i);
        this.screenFormat = getScreenFormat(i);
        this.deviceType = getDeviceType(i);
        this.screenDensity = getScreenDensity(displayMetrics);
        this.displayWidth = getDisplayWidth(displayMetrics);
        this.displayHeight = getDisplayHeight(displayMetrics);
        this.isGooglePlayServicesAvailable = Utils.isGooglePlayServicesAvailable();
        if (!bool.booleanValue()) {
            if (this.isGooglePlayServicesAvailable) {
                this.isLimitedTrackingEnabled = Utils.isLimitedTrackingEnabled(context);
                this.aifa = Utils.getPlayStoreAdId(context);
            }
            if (Utils.isEmptyOrNull(this.aifa)) {
                this.aifa = Utils.getPlayStoreAdIdByBackupMethod(context);
            }
            this.andi = Utils.getAndroidId(context);
            setImei(Utils.getImei());
        }
        if (z) {
            this.oaid = OAIDHelper.getOpenAdvertisingId(context);
        }
        boolean zIsAmazonAvailable = Utils.isAmazonAvailable(context);
        this.isAmazonAvailable = zIsAmazonAvailable;
        if (zIsAmazonAvailable) {
            this.isAmazonLimitAdTrackingEnabled = Utils.isAmazonLimitAdTracking(context);
            this.amid = Utils.getAmazonId(context);
        }
        SLSingularDeviceIdentifier sLSingularDeviceIdentifier = new SLSingularDeviceIdentifier();
        this.sdid = sLSingularDeviceIdentifier;
        sLSingularDeviceIdentifier.loadSdidFromPrefs(context);
        this.asid = Utils.getAppSetId(context);
        this.installSource = getInstallSource(context);
        setFcmRegId(context.getSharedPreferences(Constants.PREF_SESSION, 0).getString(Constants.FCM_DEVICE_TOKEN_KEY, ""));
        setGcmRegId(context.getSharedPreferences(Constants.PREF_SESSION, 0).getString(Constants.GCM_DEVICE_TOKEN_KEY, ""));
        getInstallTimestamps(context);
        setCustomUserId(context.getSharedPreferences(Constants.PREF_SESSION, 0).getString(Constants.CUSTOM_USER_ID_KEY, ""));
        this.deviceUserAgent = getDeviceUserAgent(context);
        log();
    }

    private void setAppInfo(Context context) {
        this.abi = getABI();
        this.deviceBrand = Build.BRAND;
        this.deviceBuild = Build.DEVICE;
        this.packageName = context.getPackageName();
        this.deviceManufacturer = Build.MANUFACTURER;
        this.deviceModel = Build.MODEL;
        this.platform = "Android";
        this.buildProduct = Build.PRODUCT;
        this.osVersion = Build.VERSION.RELEASE;
        this.hardwareName = Build.DISPLAY;
        this.sdkVersion = Constants.SDK_VERSION;
        this.appName = getAppName(context);
        this.appVersion = getAppVersion(context);
        this.apiLevel = String.format(TimeModel.NUMBER_FORMAT, Integer.valueOf(Build.VERSION.SDK_INT));
    }

    private String getAppVersion(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return TextUtils.isEmpty(packageInfo.versionName) ? "unknown" : packageInfo.versionName;
        } catch (Throwable unused) {
            return "unknown";
        }
    }

    private String getInstallSource(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            if (Build.VERSION.SDK_INT >= 30) {
                InstallSourceInfo installSourceInfo = packageManager.getInstallSourceInfo(context.getPackageName());
                if (installSourceInfo != null) {
                    this.installSource = installSourceInfo.getInitiatingPackageName();
                }
            } else {
                this.installSource = packageManager.getInstallerPackageName(context.getPackageName());
            }
            return this.installSource;
        } catch (Throwable unused) {
            return null;
        }
    }

    private String getAppName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            return packageManager.getApplicationLabel(packageManager.getApplicationInfo(context.getPackageName(), 128)).toString();
        } catch (Throwable unused) {
            return "unknown";
        }
    }

    private String getScreenDensity(DisplayMetrics displayMetrics) {
        int i = displayMetrics.densityDpi;
        if (i == 0) {
            return null;
        }
        return i < 140 ? Constants.LOW : i > 200 ? "high" : "medium";
    }

    private String getDisplayWidth(DisplayMetrics displayMetrics) {
        return String.valueOf(displayMetrics.widthPixels);
    }

    private String getDisplayHeight(DisplayMetrics displayMetrics) {
        return String.valueOf(displayMetrics.heightPixels);
    }

    private String getFacebookAttributionId(Context context) {
        try {
            Cursor cursorQuery = context.getContentResolver().query(Uri.parse("content://com.facebook.katana.provider.AttributionIdProvider"), new String[]{"aid"}, null, null, null);
            if (cursorQuery == null) {
                return null;
            }
            if (!cursorQuery.moveToFirst()) {
                cursorQuery.close();
                return null;
            }
            String string = cursorQuery.getString(cursorQuery.getColumnIndex("aid"));
            cursorQuery.close();
            return string;
        } catch (Throwable unused) {
            return null;
        }
    }

    private String getABI() {
        String[] supportedAbis = Utils.getSupportedAbis();
        if (supportedAbis == null || supportedAbis.length == 0) {
            return Utils.getCpuAbi();
        }
        return supportedAbis[0];
    }

    private String getDeviceUserAgent(Context context) {
        String property = System.getProperty("http.agent", "");
        if (!TextUtils.isEmpty(property)) {
            return property;
        }
        try {
            Class.forName("android.os.AsyncTask");
            return WebSettings.getDefaultUserAgent(context);
        } catch (Throwable unused) {
            return property;
        }
    }

    public boolean hasPreloadCampaign() {
        return (Utils.isEmptyOrNull(this.preloadCampaign) || Utils.isEmptyOrNull(this.preloadGroup) || Utils.isEmptyOrNull(this.preloadSource)) ? false : true;
    }

    private void setPreloadCampaign(Context context) {
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
            this.preloadCampaign = bundle.getString(Constants.PRELOAD_CAMPAIGN);
            this.preloadGroup = bundle.getString(Constants.PRELOAD_GROUP);
            this.preloadSource = bundle.getString(Constants.PRELOAD_SOURCE);
        } catch (Throwable th) {
            logger.error("error in setPreloadCampaign()", th);
        }
    }

    private void getInstallTimestamps(Context context) throws PackageManager.NameNotFoundException {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            this.firstInstallTime = packageInfo.firstInstallTime;
            this.lastUpdateTime = packageInfo.lastUpdateTime;
        } catch (PackageManager.NameNotFoundException unused) {
        } catch (RuntimeException e) {
            logger.error("getInstallDates failed", e);
        }
    }

    void log() {
        SingularLog singularLog = logger;
        if (singularLog.isDebugEnabled()) {
            singularLog.debug("DeviceInfo ----> ");
            singularLog.debug("\t imei : %s", this.imei);
            singularLog.debug("\t andi : %s", this.andi);
            singularLog.debug("\t asid : %s", this.asid);
            singularLog.debug("\t aifa : %s", this.aifa);
            Object[] objArr = new Object[1];
            SLSingularDeviceIdentifier sLSingularDeviceIdentifier = this.sdid;
            objArr[0] = sLSingularDeviceIdentifier == null ? "" : sLSingularDeviceIdentifier.getValue();
            singularLog.debug("\t sdid : %s", objArr);
            singularLog.debug("\t isGooglePlayServicesAvailable : %b", Boolean.valueOf(this.isGooglePlayServicesAvailable));
            singularLog.debug("\t isLimitedTrackingEnabled : %b", Boolean.valueOf(this.isLimitedTrackingEnabled));
            singularLog.debug("\t appVersion : %s", this.appVersion);
            singularLog.debug("\t sdkVersion : %s", this.sdkVersion);
            singularLog.debug("\t packageName : %s", this.packageName);
            singularLog.debug("\t appName : %s", this.appName);
            singularLog.debug("\t preloadCampaign : %s", this.preloadCampaign);
            singularLog.debug("\t preloadGroup : %s", this.preloadGroup);
            singularLog.debug("\t preloadSource : %s", this.preloadSource);
            singularLog.debug("\t installSource : %s", this.installSource);
            singularLog.debug("\t abi : %s", this.abi);
            singularLog.debug("\t deviceBrand : %s", this.deviceBrand);
            singularLog.debug("\t deviceBuild : %s", this.deviceBuild);
            singularLog.debug("\t deviceManufacturer : %s", this.deviceManufacturer);
            singularLog.debug("\t deviceModel : %s", this.deviceModel);
            singularLog.debug("\t platform : %s", this.platform);
            singularLog.debug("\t buildProduct : %s", this.buildProduct);
            singularLog.debug("\t osVersion : %s", this.osVersion);
            singularLog.debug("\t apiLevel : %s", this.apiLevel);
            singularLog.debug("\t hardwareName : %s", this.hardwareName);
            singularLog.debug("\t locale : %s", this.locale);
            singularLog.debug("\t language : %s", this.language);
            singularLog.debug("\t country : %s", this.country);
            singularLog.debug("\t screenSize : %s", this.screenSize);
            singularLog.debug("\t screenFormat : %s", this.screenFormat);
            singularLog.debug("\t screenDensity : %s", this.screenDensity);
            singularLog.debug("\t displayWidth : %s", this.displayWidth);
            singularLog.debug("\t displayHeight : %s", this.displayHeight);
            singularLog.debug("\t gcmProcessId : %s", this.gcmProcessId);
            singularLog.debug("\t gcmRegId : %s", this.gcmRegId);
            singularLog.debug("\t fcmRegId : %s", this.fcmRegId);
            singularLog.debug("\t firstInstallTime : %d", Long.valueOf(this.firstInstallTime));
            singularLog.debug("\t lastUpdateTime : %d", Long.valueOf(this.lastUpdateTime));
            singularLog.debug("\t deviceType : %s", this.deviceType);
            singularLog.debug("\t customUserId : %s", this.customUserId);
            singularLog.debug("\t deviceUserAgent: %s", this.deviceUserAgent);
        }
    }
}
