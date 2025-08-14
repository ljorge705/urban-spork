package com.uxcam.screenshot.utils;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.view.WindowManager;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.security.SecureRandom;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes6.dex */
public class DeviceInfo {

    /* renamed from: a, reason: collision with root package name */
    public static String f285a;

    public static String generateUniqueId(Context context) {
        String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
        f285a = string;
        if (string.isEmpty()) {
            f285a = new BigInteger(64, new SecureRandom()).toString(16);
        }
        return f285a;
    }

    public static String getAndroidOSName() throws IllegalAccessException, SecurityException, IllegalArgumentException {
        int i;
        StringBuilder sb = new StringBuilder();
        for (Field field : Build.VERSION_CODES.class.getFields()) {
            String name = field.getName();
            try {
                i = field.getInt(new Object());
            } catch (IllegalAccessException | IllegalArgumentException | NullPointerException e) {
                e.printStackTrace();
                i = -1;
            }
            if (i == Build.VERSION.SDK_INT) {
                sb.append(StringUtils.SPACE).append(name);
            }
        }
        return sb.toString();
    }

    public static Pair<Double, Double> getDeviceDimensionInInch(Context context) {
        Point deviceResolution = getDeviceResolution(context);
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return new Pair<>(Double.valueOf(deviceResolution.x / displayMetrics.xdpi), Double.valueOf(deviceResolution.y / displayMetrics.ydpi));
    }

    public static Point getDeviceResolution(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        Point point = new Point();
        if (windowManager != null) {
            windowManager.getDefaultDisplay().getRealSize(point);
        }
        return point;
    }

    public static double getDeviceScreenSizeInInch(Context context) {
        Pair<Double, Double> deviceDimensionInInch = getDeviceDimensionInInch(context);
        return Math.sqrt((((Double) deviceDimensionInInch.second).doubleValue() * ((Double) deviceDimensionInInch.second).doubleValue()) + (((Double) deviceDimensionInInch.first).doubleValue() * ((Double) deviceDimensionInInch.first).doubleValue()));
    }

    public static String getDeviceType(Context context) {
        return isTablet(context) ? "Tablet" : "Phone";
    }

    public static int getDeviceWidthHeight(Context context, int i) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        if (i == 1) {
            return displayMetrics.widthPixels;
        }
        if (i == 2) {
            return displayMetrics.heightPixels;
        }
        return 0;
    }

    public static int getDpi(Context context) {
        Point deviceResolution = getDeviceResolution(context);
        int i = deviceResolution.x;
        int i2 = deviceResolution.y;
        return (int) (Math.sqrt((i2 * i2) + (i * i)) / getDeviceScreenSizeInInch(context));
    }

    public static String getOSVersion() {
        return Build.VERSION.RELEASE;
    }

    public static boolean isTablet(Context context) {
        return ((context.getResources().getConfiguration().screenLayout & 15) == 4) || ((context.getResources().getConfiguration().screenLayout & 15) == 3);
    }
}
