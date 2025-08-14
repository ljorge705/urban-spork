package com.clevertap.android.sdk;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import androidx.autofill.HintConstants;
import androidx.core.content.ContextCompat;
import com.clevertap.android.sdk.bitmap.BitmapDownloadRequest;
import com.clevertap.android.sdk.bitmap.HttpBitmapLoader;
import com.clevertap.android.sdk.network.DownloadedBitmap;
import com.clevertap.android.sdk.network.DownloadedBitmapFactory;
import com.google.firebase.messaging.RemoteMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public final class Utils {
    private static final Pattern normalizedNameExcludePattern = Pattern.compile("\\s+");

    public static boolean containsIgnoreCase(Collection<String> collection, String str) {
        if (collection != null && str != null) {
            Iterator<String> it = collection.iterator();
            while (it.hasNext()) {
                if (str.equalsIgnoreCase(it.next())) {
                    return true;
                }
            }
        }
        return false;
    }

    public static HashMap<String, Object> convertBundleObjectToHashMap(Bundle bundle) {
        HashMap<String, Object> map = new HashMap<>();
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            if (obj instanceof Bundle) {
                map.putAll(convertBundleObjectToHashMap((Bundle) obj));
            } else {
                map.put(str, bundle.get(str));
            }
        }
        return map;
    }

    public static ArrayList<HashMap<String, Object>> convertJSONArrayOfJSONObjectsToArrayListOfHashMaps(JSONArray jSONArray) {
        ArrayList<HashMap<String, Object>> arrayList = new ArrayList<>();
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    arrayList.add(convertJSONObjectToHashMap(jSONArray.getJSONObject(i)));
                } catch (JSONException e) {
                    Logger.v("Could not convert JSONArray of JSONObjects to ArrayList of HashMaps - " + e.getMessage());
                }
            }
        }
        return arrayList;
    }

    public static ArrayList<String> convertJSONArrayToArrayList(JSONArray jSONArray) {
        ArrayList<String> arrayList = new ArrayList<>();
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    arrayList.add(jSONArray.getString(i));
                } catch (JSONException e) {
                    Logger.v("Could not convert JSONArray to ArrayList - " + e.getMessage());
                }
            }
        }
        return arrayList;
    }

    public static HashMap<String, Object> convertJSONObjectToHashMap(JSONObject jSONObject) {
        HashMap<String, Object> map = new HashMap<>();
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            try {
                String next = itKeys.next();
                Object obj = jSONObject.get(next);
                if (obj instanceof JSONObject) {
                    map.putAll(convertJSONObjectToHashMap((JSONObject) obj));
                } else {
                    map.put(next, jSONObject.get(next));
                }
            } catch (Throwable unused) {
            }
        }
        return map;
    }

    public static String convertToTitleCase(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (char lowerCase : str.toCharArray()) {
            if (Character.isSpaceChar(lowerCase)) {
                z = true;
            } else if (z) {
                lowerCase = Character.toTitleCase(lowerCase);
                z = false;
            } else {
                lowerCase = Character.toLowerCase(lowerCase);
            }
            sb.append(lowerCase);
        }
        return sb.toString();
    }

    public static Bitmap getBitmapFromURL(String str) {
        return HttpBitmapLoader.getHttpBitmap(HttpBitmapLoader.HttpBitmapOperation.DOWNLOAD_INAPP_BITMAP, new BitmapDownloadRequest(str)).getBitmap();
    }

    public static long getNowInMillis() {
        return System.currentTimeMillis();
    }

    public static String getCurrentNetworkType(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return "Unavailable";
            }
            NetworkInfo networkInfo = connectivityManager.getNetworkInfo(1);
            return (networkInfo == null || !networkInfo.isConnected()) ? getDeviceNetworkType(context) : "WiFi";
        } catch (Throwable unused) {
            return "Unavailable";
        }
    }

    public static String getDeviceNetworkType(Context context) {
        int networkType;
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(HintConstants.AUTOFILL_HINT_PHONE);
        if (telephonyManager == null) {
            return "Unavailable";
        }
        if (Build.VERSION.SDK_INT >= 30) {
            if (hasPermission(context, "android.permission.READ_PHONE_STATE")) {
                try {
                    networkType = telephonyManager.getDataNetworkType();
                } catch (SecurityException e) {
                    Logger.d("Security Exception caught while fetch network type" + e.getMessage());
                }
            } else {
                Logger.d("READ_PHONE_STATE permission not asked by the app or not granted by the user");
            }
            networkType = 0;
        } else {
            networkType = telephonyManager.getNetworkType();
        }
        if (networkType == 20) {
            return "5G";
        }
        switch (networkType) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
                return "2G";
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
                return "3G";
            case 13:
                return "4G";
            default:
                return "Unknown";
        }
    }

    public static long getMemoryConsumption() {
        return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }

    public static DownloadedBitmap getDownloadedBitmapPostFallbackIconCheck(boolean z, Context context, DownloadedBitmap downloadedBitmap) {
        return (downloadedBitmap.getBitmap() == null && z) ? getAppIcon(context) : downloadedBitmap;
    }

    public static DownloadedBitmap getNotificationBitmapWithTimeoutAndSize(String str, boolean z, Context context, CleverTapInstanceConfig cleverTapInstanceConfig, long j, int i) throws NullPointerException {
        return HttpBitmapLoader.getHttpBitmap(HttpBitmapLoader.HttpBitmapOperation.DOWNLOAD_SIZE_CONSTRAINED_GZIP_NOTIFICATION_BITMAP_WITH_TIME_LIMIT, new BitmapDownloadRequest(str, z, context, cleverTapInstanceConfig, j, i));
    }

    public static DownloadedBitmap getNotificationBitmapWithTimeout(String str, boolean z, Context context, CleverTapInstanceConfig cleverTapInstanceConfig, long j) throws NullPointerException {
        return HttpBitmapLoader.getHttpBitmap(HttpBitmapLoader.HttpBitmapOperation.DOWNLOAD_GZIP_NOTIFICATION_BITMAP_WITH_TIME_LIMIT, new BitmapDownloadRequest(str, z, context, cleverTapInstanceConfig, j));
    }

    public static int getNow() {
        return (int) (System.currentTimeMillis() / 1000);
    }

    public static int getThumbnailImage(Context context, String str) {
        if (context != null) {
            return context.getResources().getIdentifier(str, "drawable", context.getPackageName());
        }
        return -1;
    }

    public static boolean hasPermission(Context context, String str) {
        try {
            return ContextCompat.checkSelfPermission(context, str) == 0;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean isActivityDead(Activity activity) {
        return activity == null || activity.isFinishing() || activity.isDestroyed();
    }

    public static boolean isServiceAvailable(Context context, Class cls) {
        if (cls == null) {
            return false;
        }
        try {
            for (ServiceInfo serviceInfo : context.getPackageManager().getPackageInfo(context.getPackageName(), 4).services) {
                if (serviceInfo.name.equals(cls.getName())) {
                    Logger.v("Service " + serviceInfo.name + " found");
                    return true;
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            Logger.d("Intent Service name not found exception - " + e.getLocalizedMessage());
        }
        return false;
    }

    public static String optionalStringKey(JSONObject jSONObject, String str) throws JSONException {
        if (!jSONObject.has(str) || jSONObject.isNull(str)) {
            return null;
        }
        return jSONObject.getString(str);
    }

    public static void runOnUiThread(Runnable runnable) {
        if (runnable != null) {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                runnable.run();
            } else {
                new Handler(Looper.getMainLooper()).post(runnable);
            }
        }
    }

    public static void setPackageNameFromResolveInfoList(Context context, Intent intent) {
        List<ResolveInfo> listQueryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 0);
        if (listQueryIntentActivities != null) {
            String packageName = context.getPackageName();
            Iterator<ResolveInfo> it = listQueryIntentActivities.iterator();
            while (it.hasNext()) {
                if (packageName.equals(it.next().activityInfo.packageName)) {
                    intent.setPackage(packageName);
                    return;
                }
            }
        }
    }

    public static Bundle stringToBundle(String str) throws JSONException {
        Bundle bundle = new Bundle();
        if (!TextUtils.isEmpty(str)) {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                bundle.putString(next, jSONObject.getString(next));
            }
        }
        return bundle;
    }

    public static boolean validateCTID(String str) {
        if (str == null) {
            Logger.i("CLEVERTAP_USE_CUSTOM_ID has been set as 1 in AndroidManifest.xml but custom CleverTap ID passed is NULL.");
            return false;
        }
        if (str.isEmpty()) {
            Logger.i("CLEVERTAP_USE_CUSTOM_ID has been set as 1 in AndroidManifest.xml but custom CleverTap ID passed is empty.");
            return false;
        }
        if (str.length() > 64) {
            Logger.i("Custom CleverTap ID passed is greater than 64 characters. ");
            return false;
        }
        if (str.matches("[=|<>;+.A-Za-z0-9()!:$@_-]*")) {
            return true;
        }
        Logger.i("Custom CleverTap ID cannot contain special characters apart from : =,(,),_,!,@,$,|<,>,;,+,. and - ");
        return false;
    }

    static Bitmap drawableToBitmap(Drawable drawable) throws NullPointerException {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmapCreateBitmap;
    }

    private static DownloadedBitmap getAppIcon(Context context) throws Exception {
        try {
            Drawable applicationLogo = context.getPackageManager().getApplicationLogo(context.getApplicationInfo());
            if (applicationLogo == null) {
                throw new Exception("Logo is null");
            }
            return DownloadedBitmapFactory.INSTANCE.successBitmap(drawableToBitmap(applicationLogo), 0L, null);
        } catch (Exception e) {
            e.printStackTrace();
            return DownloadedBitmapFactory.INSTANCE.successBitmap(drawableToBitmap(context.getPackageManager().getApplicationIcon(context.getApplicationInfo())), 0L, null);
        }
    }

    public static String getSCDomain(String str) {
        String[] strArrSplit = str.split("\\.", 2);
        return strArrSplit[0] + ".auth." + strArrSplit[1];
    }

    public static boolean isRenderFallback(RemoteMessage remoteMessage, Context context) {
        return !Boolean.parseBoolean(remoteMessage.getData().get(Constants.WZRK_TSR_FB)) && Boolean.parseBoolean(remoteMessage.getData().get(Constants.NOTIFICATION_RENDER_FALLBACK));
    }

    public static void navigateToAndroidSettingsForNotifications(Context context) {
        Intent intent = new Intent();
        if (Build.VERSION.SDK_INT >= 26) {
            intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
            intent.putExtra("android.provider.extra.APP_PACKAGE", context.getPackageName());
            intent.addFlags(268435456);
        } else {
            intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
            intent.putExtra("app_package", context.getPackageName());
            intent.putExtra("app_uid", context.getApplicationInfo().uid);
        }
        context.startActivity(intent);
    }

    public static boolean isMainProcess(Context context, String str) {
        try {
            List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
            int iMyPid = Process.myPid();
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.pid == iMyPid && str.equals(runningAppProcessInfo.processName)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<JSONObject> toJSONObjectList(JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(jSONArray.getJSONObject(i));
        }
        return arrayList;
    }

    public static double haversineDistance(Location location, Location location2) {
        double latitude = location.getLatitude() * 0.017453292519943295d;
        double latitude2 = location2.getLatitude() * 0.017453292519943295d;
        double latitude3 = (location2.getLatitude() - location.getLatitude()) * 0.017453292519943295d;
        double longitude = (location2.getLongitude() - location.getLongitude()) * 0.017453292519943295d;
        double dSin = Math.sin(latitude3 / 2.0d);
        double dSin2 = Math.sin(longitude / 2.0d);
        double dCos = (dSin * dSin) + (Math.cos(latitude) * Math.cos(latitude2) * dSin2 * dSin2);
        return Math.atan2(Math.sqrt(dCos), Math.sqrt(1.0d - dCos)) * 12756.4d;
    }

    public static String readAssetFile(Context context, String str) throws IOException {
        InputStream inputStreamOpen = context.getAssets().open(str);
        try {
            String next = new Scanner(inputStreamOpen).useDelimiter("\\A").next();
            if (inputStreamOpen != null) {
                inputStreamOpen.close();
            }
            return next;
        } catch (Throwable th) {
            if (inputStreamOpen != null) {
                try {
                    inputStreamOpen.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public static String getNormalizedName(String str) {
        if (str == null) {
            return null;
        }
        return normalizedNameExcludePattern.matcher(str).replaceAll("").toLowerCase(Locale.ENGLISH);
    }

    public static boolean areNamesNormalizedEqual(String str, String str2) {
        return Objects.equals(getNormalizedName(str), getNormalizedName(str2));
    }
}
