package com.uxcam.internals;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.accessibility.AccessibilityManager;
import com.google.firebase.dynamiclinks.DynamicLink;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.uxcam.OnVerificationListener;
import com.uxcam.screenaction.di.ScreenActionModule;
import com.uxcam.screenaction.utils.FilePath;
import com.uxcam.screenaction.utils.Util;
import com.uxcam.screenshot.di.ScreenshotModule;
import com.uxcam.screenshot.utils.DeviceInfo;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public final class gb {
    public static final double[][] f = {new double[]{0.5d, 320.0d}, new double[]{0.5d, 384.0d}, new double[]{1.0d, 432.0d}, new double[]{1.0d, 432.0d}, new double[]{0.5d, 128.0d}};
    public static final double[][] g = {new double[]{0.5d, 160.0d}, new double[]{0.5d, 240.0d}, new double[]{1.0d, 320.0d}, new double[]{1.0d, 432.0d}, new double[]{0.5d, 128.0d}};
    public static final double[][] h = {new double[]{0.5d, 160.0d}, new double[]{0.5d, 320.0d}, new double[]{0.5d, 320.0d}, new double[]{0.5d, 384.0d}, new double[]{0.5d, 128.0d}};
    public static final double[][] i = {new double[]{0.5d, 160.0d}, new double[]{0.5d, 240.0d}, new double[]{0.5d, 272.0d}, new double[]{0.5d, 320.0d}, new double[]{0.5d, 128.0d}};

    /* renamed from: a, reason: collision with root package name */
    public final JSONObject f169a;
    public final Context b;
    public final String c;
    public final bj d;
    public final fp e;

    public interface aa {
        void a(String str);
    }

    public gb(JSONObject wholeResponse, Context context, bk dashboardOcclusionHandler, fp serviceHandler) {
        Intrinsics.checkNotNullParameter(wholeResponse, "wholeResponse");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(dashboardOcclusionHandler, "dashboardOcclusionHandler");
        Intrinsics.checkNotNullParameter(serviceHandler, "serviceHandler");
        this.c = "isFragmentEnabled";
        ga.d = wholeResponse.optString(RemoteConfigConstants.RequestFieldKey.APP_ID);
        JSONObject jSONObjectOptJSONObject = wholeResponse.optJSONObject("data");
        Intrinsics.checkNotNullExpressionValue(jSONObjectOptJSONObject, "wholeResponse.optJSONObject(DATA)");
        this.f169a = jSONObjectOptJSONObject;
        this.b = context;
        this.d = dashboardOcclusionHandler;
        this.e = serviceHandler;
    }

    public final void a() throws IOException {
        String[] strArr;
        JSONObject jSONObjectOptJSONObject = this.f169a.optJSONObject("settings");
        if (jSONObjectOptJSONObject == null) {
            jSONObjectOptJSONObject = new JSONObject();
        }
        Context context = this.b;
        SharedPreferences sharedPreferences = context != null ? context.getSharedPreferences("UXCamPreferences", 0) : null;
        boolean z = !(sharedPreferences != null && sharedPreferences.getBoolean("opt_out_of_video_recording", false));
        boolean zOptBoolean = this.f169a.optBoolean("videoRecording", true);
        if (zOptBoolean && !z) {
            bl.i = true;
        }
        ga.f = z && zOptBoolean;
        ga.v = jSONObjectOptJSONObject.optBoolean("subscriptionSessionLimitReached", false);
        boolean zOptBoolean2 = jSONObjectOptJSONObject.optBoolean("screenAction", true);
        Object systemService = this.b.getSystemService("accessibility");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.accessibility.AccessibilityManager");
        AccessibilityManager accessibilityManager = (AccessibilityManager) systemService;
        ga.B = zOptBoolean2 & (!accessibilityManager.isTouchExplorationEnabled()) & (!accessibilityManager.isEnabled());
        ga.C = jSONObjectOptJSONObject.optBoolean("encrypt", true);
        if (bi.D == null) {
            bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
        }
        bi biVar = bi.D;
        Intrinsics.checkNotNull(biVar);
        gc gcVarG = biVar.g();
        Intrinsics.checkNotNull(gcVarG);
        ((gd) gcVarG).f170a = jSONObjectOptJSONObject.optBoolean(this.c, false);
        if (this.f169a.optBoolean("stopRecording")) {
            if (bi.D == null) {
                bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
            }
            bi biVar2 = bi.D;
            Intrinsics.checkNotNull(biVar2);
            String str = ((hs) biVar2.l()).a().b;
            if (sharedPreferences != null) {
                sharedPreferences.edit().putString("killed_app_key", str).apply();
            }
            Util.deleteRecursive(new File(FilePath.getRootUrl(true)));
        }
        JSONArray jSONArrayOptJSONArray = jSONObjectOptJSONObject.optJSONArray("rage");
        if (jSONArrayOptJSONArray != null) {
            ga.w = new int[]{jSONArrayOptJSONArray.optInt(0), jSONArrayOptJSONArray.optInt(1), jSONArrayOptJSONArray.optInt(2)};
        }
        JSONArray jSONArrayOptJSONArray2 = jSONObjectOptJSONObject.optJSONArray("eventLimit");
        if (jSONArrayOptJSONArray2 != null) {
            ga.y = new int[]{jSONArrayOptJSONArray2.optInt(0), jSONArrayOptJSONArray2.optInt(1), jSONArrayOptJSONArray2.optInt(2)};
        }
        JSONArray jSONArrayOptJSONArray3 = jSONObjectOptJSONObject.optJSONArray("anr");
        if (jSONArrayOptJSONArray3 != null) {
            ga.x = new int[]{jSONArrayOptJSONArray3.optInt(0), jSONArrayOptJSONArray3.optInt(1)};
        }
        this.f169a.optString(DynamicLink.Builder.KEY_DOMAIN);
        ga.k = this.f169a.optString("deviceUrl");
        ga.l = this.f169a.optString("sessionUrl");
        ga.m = this.f169a.optString("misc");
        ga.e = !this.f169a.optBoolean("appIcon", false);
        JSONObject jSONObjectOptJSONObject2 = this.f169a.optJSONObject("s3");
        if (jSONObjectOptJSONObject2 == null) {
            jSONObjectOptJSONObject2 = new JSONObject();
        }
        ga.j = jSONObjectOptJSONObject2;
        ga.t = jSONObjectOptJSONObject.optJSONArray("filtersDataSession");
        ga.u = jSONObjectOptJSONObject.optJSONArray("filters");
        jSONObjectOptJSONObject.optString("url");
        a(jSONObjectOptJSONObject.optInt("videoQuality", 2), ff.f());
        if (jSONObjectOptJSONObject.optInt("uploadNetwork", 1) == 2) {
            ga.h = jSONObjectOptJSONObject.optInt("mobileDataLimit", 0);
        } else {
            ga.h = 0;
        }
        ga.i = jSONObjectOptJSONObject.optBoolean("mobileDataDataOnly", false);
        JSONArray jSONArrayOptJSONArray4 = jSONObjectOptJSONObject.optJSONArray("activitiesToIgnore");
        if (jSONArrayOptJSONArray4 != null) {
            int length = jSONArrayOptJSONArray4.length();
            for (int i2 = 0; i2 < length; i2++) {
                try {
                    String string = jSONArrayOptJSONArray4.get(i2).toString();
                    boolean z2 = com.uxcam.aa.h;
                    ga.D.add(string);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        this.d.b(jSONObjectOptJSONObject);
        this.d.a(jSONObjectOptJSONObject);
        jSONObjectOptJSONObject.optBoolean("upload_crashed_session", true);
        int i3 = ga.f167a;
        ga.I = this.f169a.optString("sessionId");
        ga.J = jSONObjectOptJSONObject.optBoolean("recordAppLog");
        ga.K = jSONObjectOptJSONObject.optBoolean("bundleFiles");
        Context context2 = this.b;
        if (hz.c == null) {
            hz.c = new hz(context2.getSharedPreferences("UXCamPreferences", 0));
        }
        hz hzVar = hz.c;
        String str2 = ga.I;
        String[] strArr2 = hzVar.b;
        int length2 = strArr2.length - 1;
        if (str2 == null) {
            String str3 = strArr2[length2];
        } else {
            int i4 = 0;
            boolean z3 = false;
            while (true) {
                strArr = hzVar.b;
                if (i4 >= strArr.length) {
                    break;
                }
                if (strArr[i4].equals(str2)) {
                    length2 = i4;
                    z3 = true;
                }
                i4++;
            }
            if (z3) {
                String str4 = strArr[length2 - 1];
            } else {
                String str5 = strArr[length2];
            }
        }
        if (str2 != null && !str2.isEmpty()) {
            String[] strArr3 = hzVar.b;
            int length3 = strArr3.length;
            int i5 = 0;
            while (true) {
                if (i5 >= length3) {
                    String[] strArr4 = hzVar.b;
                    int length4 = strArr4.length - 1;
                    if (length4 >= 0) {
                        System.arraycopy(strArr4, 1, strArr4, 0, length4);
                    }
                    String[] strArr5 = hzVar.b;
                    strArr5[length4] = "";
                    strArr5[strArr5.length - 1] = str2;
                    JSONArray jSONArray = new JSONArray();
                    for (String str6 : hzVar.b) {
                        jSONArray.put(str6);
                    }
                    hzVar.f203a.edit().putString("last_session_id", jSONArray.toString()).commit();
                    jSONArray.toString();
                } else if (str2.equals(strArr3[i5])) {
                    JSONArray jSONArray2 = new JSONArray();
                    for (String str7 : hzVar.b) {
                        jSONArray2.put(str7);
                    }
                    hzVar.f203a.edit().putString("last_session_id", jSONArray2.toString()).commit();
                    jSONArray2.toString();
                } else {
                    i5++;
                }
            }
        }
        bh.f97a = true;
        this.e.c();
        if (bi.D == null) {
            bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
        }
        bi biVar3 = bi.D;
        Intrinsics.checkNotNull(biVar3);
        Iterator it = ((fv) biVar3.f()).d.iterator();
        while (it.hasNext()) {
            OnVerificationListener onVerificationListener = (OnVerificationListener) it.next();
            Util.getCurrentApplicationContext();
            onVerificationListener.onVerificationSuccess();
        }
        try {
            if (bi.D == null) {
                bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
            }
            bi biVar4 = bi.D;
            Intrinsics.checkNotNull(biVar4);
            ck ckVarA = biVar4.a();
            Context currentContext = Util.getCurrentContext();
            Intrinsics.checkNotNull(currentContext, "null cannot be cast to non-null type android.app.Activity");
            ckVarA.getClass();
            ck.a((Activity) currentContext);
        } catch (Exception unused) {
        }
        if (!this.f169a.has("appIcon") || ga.e) {
            return;
        }
        Context context3 = this.b;
        Drawable applicationIcon = context3.getPackageManager().getApplicationIcon(context3.getApplicationInfo());
        File file = new File(FilePath.getSessionRootUrl(ga.b, Boolean.TRUE), FilePath.getIconFileName());
        try {
            int intrinsicWidth = applicationIcon.getIntrinsicWidth();
            int intrinsicHeight = applicationIcon.getIntrinsicHeight();
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmapCreateBitmap);
            applicationIcon.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
            applicationIcon.draw(canvas);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            bitmapCreateBitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException unused2) {
            gk.a("IconSender").getClass();
        }
        new ag().b(context3, file);
    }

    public final void a(int i2, boolean z) {
        if (i2 > 5 || i2 < 1) {
            gk.a("SettingsHandler").getClass();
            i2 = 2;
        }
        boolean zIsTablet = DeviceInfo.isTablet(this.b);
        gk.a("SettingsHandler").getClass();
        if (z && zIsTablet) {
            double[] dArr = f[i2 - 1];
            a(dArr[0], (int) dArr[1], i2);
        } else if (z) {
            double[] dArr2 = g[i2 - 1];
            a(dArr2[0], (int) dArr2[1], i2);
        } else if (zIsTablet) {
            double[] dArr3 = h[i2 - 1];
            a(dArr3[0], (int) dArr3[1], i2);
        } else {
            double[] dArr4 = i[i2 - 1];
            a(dArr4[0], (int) dArr4[1], i2);
        }
    }

    public final void a(double d, int i2, int i3) {
        if (Util.getCurrentApplicationContext().getResources().getDisplayMetrics().widthPixels < i2 && i3 != 1) {
            a(i3 - 1, true);
            return;
        }
        ga.p = i2;
        int i4 = (int) (1000 / d);
        ga.g = i4;
        int i5 = 1000 / i4;
        gi.k = i5;
        if (i5 < 1) {
            gi.k = 1;
        }
        cn.l = gi.k;
        gk.a("SettingsHandler").getClass();
    }
}
