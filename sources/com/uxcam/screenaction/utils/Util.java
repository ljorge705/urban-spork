package com.uxcam.screenaction.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.os.SystemClock;
import android.support.v4.media.session.PlaybackStateCompat;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.util.Pair;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import androidx.autofill.HintConstants;
import androidx.core.app.NotificationManagerCompat;
import com.clevertap.android.sdk.Constants;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class Util {
    public static Context b = null;
    public static final /* synthetic */ boolean d = true;

    /* renamed from: a, reason: collision with root package name */
    public static final String[] f257a = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"};
    public static WeakReference<Activity> c = new WeakReference<>(null);

    public static boolean containsMp4(File file) {
        if (!file.isDirectory()) {
            return FilePath.isVideoFile(file.getName());
        }
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles == null) {
            return false;
        }
        for (File file2 : fileArrListFiles) {
            if (FilePath.isVideoFile(file2.getName())) {
                return true;
            }
        }
        return false;
    }

    public static void createSessionRootDirectory(String str, Boolean bool) {
        File file = new File(FilePath.getSessionRootUrl(str, bool));
        if (file.exists()) {
            return;
        }
        file.mkdirs();
    }

    public static boolean dataFileExist(File file) {
        if (file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                if (file2.getName().contains("data")) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void deleteDataAndVideoFile(File file) {
        if (file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                if (file2.getName().contains("data") || file2.getName().contains("video")) {
                    file2.delete();
                }
            }
        }
    }

    public static void deleteRecursive(File file) {
        File[] fileArrListFiles;
        if (file == null || !file.exists()) {
            return;
        }
        if (file.isDirectory() && (fileArrListFiles = file.listFiles()) != null) {
            for (File file2 : fileArrListFiles) {
                deleteRecursive(file2);
            }
        }
        file.delete();
    }

    public static boolean findPermission(String[] strArr, boolean z) throws PackageManager.NameNotFoundException {
        PackageInfo packageInfo;
        PackageManager packageManager = b.getPackageManager();
        StringBuilder sb = new StringBuilder();
        try {
            packageInfo = packageManager.getPackageInfo(b.getPackageName(), 4096);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            packageInfo = null;
        }
        if (!d && packageInfo == null) {
            throw new AssertionError();
        }
        String[] strArr2 = packageInfo.requestedPermissions;
        if (strArr2 != null) {
            for (String str : strArr) {
                boolean z2 = false;
                for (String str2 : strArr2) {
                    if (str.compareTo(str2) == 0) {
                        z2 = true;
                    }
                }
                if (!z2) {
                    sb.append(Constants.SEPARATOR_COMMA).append(str);
                    if (z) {
                        Log.i("UXCam", "Missed permission : " + str + ". Add this permission to project’s manifest.");
                    }
                }
            }
            if (sb.length() != 0) {
                return false;
            }
        }
        return true;
    }

    public static long folderSize(File file) {
        long length = 0;
        try {
            for (File file2 : (File[]) Objects.requireNonNull(file.listFiles())) {
                length += file2.isFile() ? file2.length() : folderSize(file2);
            }
            return length;
        } catch (Exception e) {
            e.printStackTrace();
            return length;
        }
    }

    public static String getAppKeyString(Context context) {
        try {
            Object obj = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.get("uxcam_app_key");
            if (obj instanceof Integer) {
                return obj.toString();
            }
            if (obj instanceof String) {
                return (String) obj;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static Application getApplicationContext() {
        return (Application) Class.forName("android.app.ActivityThread").getMethod("currentApplication", new Class[0]).invoke(null, null);
    }

    public static String getApplicationName(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        int i = applicationInfo.labelRes;
        return i == 0 ? applicationInfo.nonLocalizedLabel.toString() : context.getString(i);
    }

    public static String getApplicationPackageName(Context context, String str) {
        return (str == null || str.isEmpty()) ? context.getApplicationInfo().packageName : str;
    }

    public static long getAvailableHeapMemory() {
        return Runtime.getRuntime().maxMemory() - Runtime.getRuntime().totalMemory();
    }

    public static float getAvailableHeapMemoryPercentage() {
        return (1.0f - (Runtime.getRuntime().totalMemory() / Runtime.getRuntime().maxMemory())) * 100.0f;
    }

    public static float getAvailableStorageSize() {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        return (statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong()) / 1048576.0f;
    }

    public static String[] getBasicPermissions() {
        return f257a;
    }

    public static JSONObject getBatteryLevel(Context context, long j) throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject();
            Intent intentRegisterReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            int intExtra = intentRegisterReceiver.getIntExtra("level", -1);
            int intExtra2 = intentRegisterReceiver.getIntExtra("scale", -1);
            int intExtra3 = intentRegisterReceiver.getIntExtra("status", -1);
            int i = 1;
            if (intExtra3 != 1) {
                int i2 = 2;
                if (intExtra3 != 2) {
                    i2 = 3;
                    if (intExtra3 != 3) {
                        i = 4;
                        if (intExtra3 != 4) {
                            if (intExtra3 != 5) {
                                i = 0;
                            }
                            i = i2;
                        }
                    }
                } else {
                    i = i2;
                }
            } else {
                i = 0;
            }
            jSONObject.put("level", (intExtra / intExtra2) * 100.0f);
            jSONObject.put("chargeState", i);
            jSONObject.put("timeLine", getCurrentUxcamTime(j));
            return jSONObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getCarrier(Context context) {
        return ((TelephonyManager) context.getSystemService(HintConstants.AUTOFILL_HINT_PHONE)).getNetworkOperatorName();
    }

    public static String getCarrierCode(Context context) {
        return ((TelephonyManager) context.getSystemService(HintConstants.AUTOFILL_HINT_PHONE)).getNetworkOperator();
    }

    public static Context getCurrentContext() {
        return c.get();
    }

    public static float getCurrentUxcamTime(long j) {
        if (j <= 0) {
            return 0.0f;
        }
        return getTimeDifferenceInFloat(j, SystemClock.elapsedRealtime());
    }

    public static int getDeviceDefaultOrientation(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        Configuration configuration = context.getResources().getConfiguration();
        int rotation = windowManager.getDefaultDisplay().getRotation();
        return (((rotation == 0 || rotation == 2) && configuration.orientation == 2) || ((rotation == 1 || rotation == 3) && configuration.orientation == 1)) ? 2 : 1;
    }

    public static int getDivisibleBySixteenInt(int i) {
        int i2 = i % 16;
        return i + (i2 > 0 ? 16 - i2 : 0);
    }

    public static String getNetworkType(Context context, boolean z) {
        return Connectivity.isConnectedWifi(context) ? "wifi" : Connectivity.isConnectedMobile(context) ? "mobile data" : Connectivity.isConnected(context, z) ? "unknown" : "no network";
    }

    public static int getPendingSessionCount(boolean z) {
        try {
            return new File(FilePath.getRootUrl(z)).list().length;
        } catch (Exception unused) {
            return 0;
        }
    }

    public static String getResourceName(View view) {
        try {
            return view.getResources().getResourceName(view.getId());
        } catch (Exception unused) {
            return "";
        }
    }

    public static float getStorageSize() {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        return (statFs.getBlockCountLong() * statFs.getBlockSizeLong()) / 1048576.0f;
    }

    private static float getTimeDifferenceInFloat(long j, long j2) {
        return (j2 - j) / 1000.0f;
    }

    public static int getTotalRam(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        return (int) (memoryInfo.totalMem / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED);
    }

    public static boolean hasEmptyDataFile(File file) {
        if (!file.isDirectory()) {
            return file.length() == 0;
        }
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles == null) {
            return false;
        }
        for (File file2 : fileArrListFiles) {
            if (file2.getName().contains("data") && file2.length() == 0) {
                return true;
            }
        }
        return false;
    }

    public static boolean isBitmapLandscape(Bitmap bitmap) {
        return bitmap.getHeight() <= bitmap.getWidth();
    }

    public static boolean isClass(String str) throws ClassNotFoundException {
        try {
            Class.forName(str);
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    public static boolean isNotEmulator() {
        String str = Build.FINGERPRINT;
        if (!str.startsWith("generic") && !str.startsWith("unknown")) {
            String str2 = Build.MODEL;
            if (!str2.contains("google_sdk") && !str2.contains("Emulator") && !str2.contains("Android SDK built for x86") && !Build.MANUFACTURER.contains("Genymotion") && ((!Build.BRAND.startsWith("generic") || !Build.DEVICE.startsWith("generic")) && !"google_sdk".equals(Build.PRODUCT))) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNotificationEnabled(Context context) {
        return NotificationManagerCompat.from(context).areNotificationsEnabled();
    }

    public static boolean isRooted() {
        String[] strArr = {"/sbin/", "/system/bin/", "/system/xbin/", "/data/local/xbin/", "/data/local/bin/", "/system/sd/xbin/", "/system/bin/failsafe/", "/data/local/"};
        for (int i = 0; i < 8; i++) {
            if (new File(strArr[i] + "su").exists()) {
                return true;
            }
        }
        return false;
    }

    public static Map<String, Object> jsonObjectToMap(JSONObject jSONObject) {
        HashMap map = new HashMap();
        if (jSONObject != null) {
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                map.put(next, jSONObject.getString(next));
            }
        }
        return map;
    }

    public static float mmToPx(float f, Context context) {
        return TypedValue.applyDimension(5, f, context.getResources().getDisplayMetrics());
    }

    public static void saveToFile(Bitmap bitmap, String str) {
        try {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(new File(getCurrentContext().getCacheDir(), str)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void setCurrentApplicationContext(Context context) {
        b = context;
    }

    public static void setCurrentContext(Activity activity) {
        c = new WeakReference<>(activity);
        if (activity != null) {
            b = activity.getApplicationContext();
        }
    }

    public static Pair<String, Integer> getApplicationVersionName(Context context) throws PackageManager.NameNotFoundException {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            String str = packageInfo.versionName;
            return new Pair<>(str != null ? str : "Unknown app version", Integer.valueOf(packageInfo.versionCode));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return new Pair<>("Unknown app version", 0);
        }
    }

    public static Context getCurrentApplicationContext() {
        if (b == null) {
            try {
                b = getApplicationContext();
            } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            }
        }
        return b;
    }
}
