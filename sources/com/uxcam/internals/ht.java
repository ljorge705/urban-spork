package com.uxcam.internals;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.exifinterface.media.ExifInterface;
import com.uxcam.screenaction.di.ScreenActionModule;
import com.uxcam.screenaction.models.KeyConstant;
import com.uxcam.screenaction.utils.Util;
import com.uxcam.screenshot.di.ScreenshotModule;
import io.sentry.protocol.Message;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public final class ht {
    public static SharedPreferences b;

    /* renamed from: a, reason: collision with root package name */
    public static JSONArray f198a = new JSONArray();
    public static int c = 0;

    public static void a(String str, String str2, Map<String, String> map) throws JSONException {
        String[] strArr;
        String str3;
        try {
            Context currentApplicationContext = Util.getCurrentApplicationContext();
            if (currentApplicationContext == null) {
                return;
            }
            int i = 0;
            if (b == null) {
                b = currentApplicationContext.getSharedPreferences("UXCamLog", 0);
            }
            if (b.getBoolean("enableDebugLog", true)) {
                if (f198a == null) {
                    f198a = new JSONArray(b.getString("events", "[]"));
                }
                if (f198a.length() > 1000) {
                    f198a.remove(0);
                }
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("tag", str2);
                jSONObject.put("logLevel", str);
                jSONObject.put("time", System.currentTimeMillis());
                jSONObject.put("timeline", Util.getCurrentUxcamTime(fp.f157n));
                if (bi.D == null) {
                    bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
                }
                bi biVar = bi.D;
                Intrinsics.checkNotNull(biVar);
                jSONObject.put(KeyConstant.KEY_SCREEN, ((gw) biVar.i()).g());
                if (hz.c == null) {
                    hz.c = new hz(currentApplicationContext.getSharedPreferences("UXCamPreferences", 0));
                }
                hz hzVar = hz.c;
                String str4 = ga.I;
                String[] strArr2 = hzVar.b;
                int length = strArr2.length - 1;
                if (str4 == null) {
                    str3 = strArr2[length];
                } else {
                    boolean z = false;
                    while (true) {
                        strArr = hzVar.b;
                        if (i >= strArr.length) {
                            break;
                        }
                        if (strArr[i].equals(str4)) {
                            length = i;
                            z = true;
                        }
                        i++;
                    }
                    str3 = z ? strArr[length - 1] : strArr[length];
                }
                jSONObject.put("lastSessionID", str3);
                jSONObject.put("sessionID", ga.I);
                if (map != null) {
                    jSONObject.put(Message.JsonKeys.PARAMS, new JSONObject(map));
                }
                f198a.put(jSONObject);
                jSONObject.toString();
                b.edit().putString("events", f198a.toString()).apply();
            }
        } catch (ArrayIndexOutOfBoundsException | ConcurrentModificationException | JSONException e) {
            e.getMessage();
        }
    }

    public static void b(String str, HashMap map) {
        int i = ga.H;
        if (i == 3 || i == 4) {
            a("I", str, map);
        }
    }

    public static void c(String str, HashMap map) {
        int i = ga.H;
        if (i == 2 || i == 4) {
            a(ExifInterface.LONGITUDE_WEST, str, map);
        }
    }

    public static void d(Context context) {
        context.getSharedPreferences("UXCamLog", 0).edit().putBoolean("enableDebugLog", true).apply();
    }

    public static void b(Context context) {
        context.getSharedPreferences("UXCamLog", 0).edit().putBoolean("enableDebugLog", false).apply();
    }

    public static void c(Context context) {
        f198a = new JSONArray();
        context.getSharedPreferences("UXCamLog", 0).edit().putString("events", f198a.toString()).apply();
    }

    public static void a(String str, Map<String, String> map) {
        int i = ga.H;
        if (i == 1 || i == 4) {
            a("IE", str, map);
        }
    }

    public static void a(String str, HashMap map) {
        if (ga.H == 4) {
            a(ExifInterface.GPS_MEASUREMENT_INTERRUPTED, str, map);
        }
    }

    public static void a(Context context) {
        try {
            f198a.toString();
            JSONArray jSONArray = new JSONArray();
            for (int i = c + 1; i < f198a.length(); i++) {
                jSONArray.put(f198a.get(i));
            }
            f198a = jSONArray;
            if (jSONArray.length() > 0) {
                jSONArray.length();
                Objects.toString(jSONArray.get(0));
            }
            f198a.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            e.getMessage();
            e.getMessage();
        }
        context.getSharedPreferences("UXCamLog", 0).edit().putString("events", f198a.toString()).apply();
        f198a.length();
    }
}
