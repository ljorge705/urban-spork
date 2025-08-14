package com.clevertap.android.sdk;

import android.app.Activity;
import android.location.Location;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class CoreMetaData extends CleverTapMetaData {
    private static int activityCount = 0;
    private static boolean appForeground = false;
    private static WeakReference<Activity> currentActivity;
    private static int initialAppEnteredForegroundTime;
    private WeakReference<Activity> appInboxActivity;
    private boolean isProductConfigRequested;
    private boolean offline;
    private boolean webInterfaceInitializedExternally;
    private long appInstallTime = 0;
    private boolean appLaunchPushed = false;
    private final Object appLaunchPushedLock = new Object();
    private String currentScreenName = null;
    private int currentSessionId = 0;
    private boolean currentUserOptedOut = false;
    private boolean firstRequestInSession = false;
    private boolean firstSession = false;
    private int geofenceSDKVersion = 0;
    private boolean installReferrerDataSent = false;
    private boolean isBgPing = false;
    private boolean isLocationForGeofence = false;
    private int lastSessionLength = 0;
    private Location locationFromUser = null;
    private final Object optOutFlagLock = new Object();
    private HashMap<String, Integer> customSdkVersions = new HashMap<>();
    private long referrerClickTime = 0;
    private String source = null;
    private String medium = null;
    private String campaign = null;
    private JSONObject wzrkParams = null;

    public static int getActivityCount() {
        return activityCount;
    }

    static int getInitialAppEnteredForegroundTime() {
        return initialAppEnteredForegroundTime;
    }

    static void incrementActivityCount() {
        activityCount++;
    }

    public static boolean isAppForeground() {
        return appForeground;
    }

    public static void setActivityCount(int i) {
        activityCount = i;
    }

    public static void setAppForeground(boolean z) {
        appForeground = z;
    }

    static void setInitialAppEnteredForegroundTime(int i) {
        initialAppEnteredForegroundTime = i;
    }

    public HashMap<String, Integer> getAllCustomSdkVersions() {
        return this.customSdkVersions;
    }

    public long getAppInstallTime() {
        return this.appInstallTime;
    }

    public int getCurrentSessionId() {
        return this.currentSessionId;
    }

    public int getGeofenceSDKVersion() {
        return this.geofenceSDKVersion;
    }

    public int getLastSessionLength() {
        return this.lastSessionLength;
    }

    public Location getLocationFromUser() {
        return this.locationFromUser;
    }

    public long getReferrerClickTime() {
        return this.referrerClickTime;
    }

    public String getScreenName() {
        return this.currentScreenName;
    }

    public boolean inCurrentSession() {
        return this.currentSessionId > 0;
    }

    public boolean isBgPing() {
        return this.isBgPing;
    }

    public boolean isFirstRequestInSession() {
        return this.firstRequestInSession;
    }

    public boolean isFirstSession() {
        return this.firstSession;
    }

    public boolean isInstallReferrerDataSent() {
        return this.installReferrerDataSent;
    }

    public boolean isLocationForGeofence() {
        return this.isLocationForGeofence;
    }

    public boolean isOffline() {
        return this.offline;
    }

    public boolean isProductConfigRequested() {
        return this.isProductConfigRequested;
    }

    public boolean isWebInterfaceInitializedExternally() {
        return this.webInterfaceInitializedExternally;
    }

    public void setAppInstallTime(long j) {
        this.appInstallTime = j;
    }

    public void setBgPing(boolean z) {
        this.isBgPing = z;
    }

    public void setCurrentScreenName(String str) {
        this.currentScreenName = str;
    }

    void setCurrentSessionId(int i) {
        this.currentSessionId = i;
    }

    public void setFirstRequestInSession(boolean z) {
        this.firstRequestInSession = z;
    }

    void setFirstSession(boolean z) {
        this.firstSession = z;
    }

    public void setGeofenceSDKVersion(int i) {
        this.geofenceSDKVersion = i;
    }

    void setInstallReferrerDataSent(boolean z) {
        this.installReferrerDataSent = z;
    }

    void setLastSessionLength(int i) {
        this.lastSessionLength = i;
    }

    public void setLocationForGeofence(boolean z) {
        this.isLocationForGeofence = z;
    }

    public void setLocationFromUser(Location location) {
        this.locationFromUser = location;
    }

    void setOffline(boolean z) {
        this.offline = z;
    }

    public void setProductConfigRequested(boolean z) {
        this.isProductConfigRequested = z;
    }

    void setReferrerClickTime(long j) {
        this.referrerClickTime = j;
    }

    public void setWebInterfaceInitializedExternally(boolean z) {
        this.webInterfaceInitializedExternally = z;
    }

    public static Activity getCurrentActivity() {
        WeakReference<Activity> weakReference = currentActivity;
        if (weakReference == null) {
            return null;
        }
        return weakReference.get();
    }

    public static void setCurrentActivity(Activity activity) {
        if (activity == null) {
            currentActivity = null;
        } else {
            if (activity.getLocalClassName().contains("InAppNotificationActivity")) {
                return;
            }
            currentActivity = new WeakReference<>(activity);
        }
    }

    public static String getCurrentActivityName() {
        Activity currentActivity2 = getCurrentActivity();
        if (currentActivity2 != null) {
            return currentActivity2.getLocalClassName();
        }
        return null;
    }

    public void setAppInboxActivity(Activity activity) {
        this.appInboxActivity = new WeakReference<>(activity);
    }

    public Activity getAppInboxActivity() {
        WeakReference<Activity> weakReference = this.appInboxActivity;
        if (weakReference == null) {
            return null;
        }
        return weakReference.get();
    }

    synchronized void clearCampaign() {
        this.campaign = null;
    }

    synchronized void clearMedium() {
        this.medium = null;
    }

    synchronized void clearSource() {
        this.source = null;
    }

    synchronized void clearWzrkParams() {
        this.wzrkParams = null;
    }

    synchronized void setCampaign(String str) {
        if (this.campaign == null) {
            this.campaign = str;
        }
    }

    public synchronized String getCampaign() {
        return this.campaign;
    }

    public int getCustomSdkVersion(String str) {
        Integer num = this.customSdkVersions.get(str);
        if (num != null) {
            return num.intValue();
        }
        return 0;
    }

    public void setCustomSdkVersion(String str, int i) {
        this.customSdkVersions.put(str, Integer.valueOf(i));
    }

    synchronized void setMedium(String str) {
        if (this.medium == null) {
            this.medium = str;
        }
    }

    public synchronized String getMedium() {
        return this.medium;
    }

    public synchronized String getSource() {
        return this.source;
    }

    synchronized void setSource(String str) {
        if (this.source == null) {
            this.source = str;
        }
    }

    public synchronized void setWzrkParams(JSONObject jSONObject) {
        if (this.wzrkParams == null) {
            this.wzrkParams = jSONObject;
        }
    }

    public synchronized JSONObject getWzrkParams() {
        return this.wzrkParams;
    }

    void setAppLaunchPushed(boolean z) {
        synchronized (this.appLaunchPushedLock) {
            this.appLaunchPushed = z;
        }
    }

    public boolean isAppLaunchPushed() {
        boolean z;
        synchronized (this.appLaunchPushedLock) {
            z = this.appLaunchPushed;
        }
        return z;
    }

    public void setCurrentUserOptedOut(boolean z) {
        synchronized (this.optOutFlagLock) {
            this.currentUserOptedOut = z;
        }
    }

    public boolean isCurrentUserOptedOut() {
        boolean z;
        synchronized (this.optOutFlagLock) {
            z = this.currentUserOptedOut;
        }
        return z;
    }
}
