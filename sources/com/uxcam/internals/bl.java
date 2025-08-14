package com.uxcam.internals;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Pair;
import com.clevertap.android.sdk.Constants;
import com.google.firebase.dynamiclinks.DynamicLink;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.uxcam.env.Environment;
import com.uxcam.internals.at;
import com.uxcam.internals.gk;
import com.uxcam.screenaction.di.ScreenActionModule;
import com.uxcam.screenaction.utils.FilePath;
import com.uxcam.screenaction.utils.Util;
import com.uxcam.screenshot.di.ScreenshotModule;
import com.uxcam.screenshot.utils.DeviceInfo;
import com.uxcam.screenshot.utils.ScreenShotBitmapUtil;
import io.sentry.Session;
import io.sentry.cache.EnvelopeCache;
import io.sentry.protocol.Device;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public final class bl {
    public static final String[] f = {"cordova", "xamarin", "react-native", "appcelerator", "flutter"};
    public static boolean g = false;
    public static boolean h = false;
    public static boolean i = false;

    /* renamed from: a, reason: collision with root package name */
    public final ArrayList<Integer> f101a = new ArrayList<>();
    public long b = -1;
    public int c = -1;
    public final bo d;
    public final dk e;

    public class aa implements FilenameFilter {
        @Override // java.io.FilenameFilter
        public final boolean accept(File file, String str) {
            return FilePath.isVideoFile(str);
        }
    }

    public bl(bo boVar) {
        this.d = boVar;
    }

    public final void a(Context context, JSONObject jSONObject) throws JSONException {
        try {
            if (bi.D == null) {
                bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
            }
            bi biVar = bi.D;
            Intrinsics.checkNotNull(biVar);
            hs hsVar = (hs) biVar.l();
            jSONObject.put("pixelCopyCaptureEnabled", hsVar.a().f);
            Environment environmentA = hsVar.a().h;
            if (environmentA == null) {
                environmentA = this.d.a(context);
            }
            jSONObject.put("environment", environmentA.toString());
        } catch (JSONException e) {
            e.printStackTrace();
            ht.a("textFileWriteException-uxConfigDetails", (Map<String, String>) null);
        }
    }

    public final boolean a() {
        return this.c > 0;
    }

    public final void b(int i2, int i3, boolean z) {
        ArrayList arrayList = new ArrayList();
        if (bi.D == null) {
            bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
        }
        bi biVar = bi.D;
        Intrinsics.checkNotNull(biVar);
        gw gwVar = (gw) biVar.i();
        arrayList.addAll(gwVar.m());
        arrayList.addAll(gwVar.b);
        cf cfVar = new cf(arrayList, ga.u, z, i3, i2);
        if (cfVar.d()) {
            return;
        }
        this.f101a.addAll(cfVar.d);
        g = true;
        gk.a("filter11").getClass();
    }

    public bl(bo boVar, dk dkVar) {
        this.d = boVar;
        this.e = dkVar;
    }

    public static void a(float f2) {
        try {
            if (bi.D == null) {
                bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
            }
            bi biVar = bi.D;
            Intrinsics.checkNotNull(biVar);
            gp gpVar = ((gw) biVar.i()).f186a.get(r0.size() - 1);
            gpVar.e = f2 - gpVar.b;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final float a(Context context) throws SecurityException, IllegalArgumentException {
        float currentUxcamTime = Util.getCurrentUxcamTime(fp.f157n);
        String str = ga.b;
        Boolean bool = Boolean.TRUE;
        File file = new File(FilePath.getScreenVideoImageUrl(str, bool), FilePath.getScreenFileName(bool));
        if (currentUxcamTime < (ga.g / 1000.0f) + 0.3d) {
            h = true;
            this.f101a.add(6);
        } else if (file.exists()) {
            try {
                new HashMap();
                new MediaMetadataRetriever().setDataSource(context, Uri.fromFile(file));
                float fIntValue = Integer.valueOf(r2.extractMetadata(9)).intValue() / 1000.0f;
                if (fIntValue > currentUxcamTime) {
                    currentUxcamTime = fIntValue;
                }
                this.b = file.length();
                if (bi.D == null) {
                    bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
                }
                bi biVar = bi.D;
                Intrinsics.checkNotNull(biVar);
                if (biVar.p == null) {
                    biVar.p = new du();
                }
                du duVar = biVar.p;
                Intrinsics.checkNotNull(duVar);
                duVar.f127a.c = this.b / 1024.0d;
            } catch (Exception unused) {
                gk.a("bl").getClass();
                gk.a("bl").getClass();
            }
        }
        return currentUxcamTime;
    }

    public final File a(String str, fr frVar, String str2) {
        JSONObject jSONObject;
        String str3;
        String str4;
        double dA;
        boolean z;
        String str5;
        SharedPreferences sharedPreferences;
        String str6;
        String string;
        String str7;
        String str8;
        Object obj;
        Object obj2;
        String str9;
        JSONArray jSONArray;
        JSONArray jSONArray2;
        hy hyVarM = bi.b().m();
        try {
            ht.a("fileWriteStarted", (HashMap) null);
            JSONObject jSONObject2 = new JSONObject();
            JSONObject jSONObject3 = new JSONObject();
            JSONObject jSONObject4 = new JSONObject();
            JSONObject jSONObject5 = new JSONObject();
            fu fuVarF = bi.b().f();
            hx hxVar = ((fv) fuVarF).e;
            hx hxVar2 = hxVar.c;
            if (hxVar2 == null) {
                jSONObject = new JSONObject(hxVar.b);
                try {
                    String str10 = hxVar.f201a;
                    if (str10 != null && !str10.isEmpty()) {
                        jSONObject.put("kUXCam_UserIdentity", hxVar.f201a);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                hxVar.a();
                str3 = "metrics";
            } else {
                hxVar2.getClass();
                str3 = "metrics";
                jSONObject = new JSONObject(hxVar2.b);
                try {
                    String str11 = hxVar2.f201a;
                    if (str11 != null && !str11.isEmpty()) {
                        jSONObject.put("kUXCam_UserIdentity", hxVar2.f201a);
                    }
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
                if (!a()) {
                    hxVar.c = null;
                }
            }
            jSONObject2.put("usr", jSONObject);
            gk.aa aaVarA = gk.a("userTest");
            jSONObject.toString();
            aaVarA.getClass();
            Context currentApplicationContext = Util.getCurrentApplicationContext();
            if (a()) {
                str4 = "vs";
                dA = 0.0d;
            } else {
                jSONObject3.put("cust", ((fv) fuVarF).b.a());
                ((fv) fuVarF).k();
                DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance(Locale.US);
                decimalFormat.applyPattern("0.000");
                str4 = "vs";
                dA = a(currentApplicationContext);
                if (hyVarM != null) {
                    a((float) dA);
                }
                jSONObject3.put("tt", Double.valueOf(decimalFormat.format(dA)));
            }
            jSONObject3.put("networkSummary", at.ab.a());
            a(currentApplicationContext, jSONObject3);
            int i2 = ga.f167a;
            jSONObject3.put(Constants.NOTIF_TITLE, Util.getNetworkType(currentApplicationContext, true));
            jSONObject3.put("isvo", ga.z);
            String str12 = ga.I;
            if (str12 != null && !str12.isEmpty()) {
                jSONObject3.put("id", ga.I);
            }
            String str13 = ga.d;
            if (str13 != null && !str13.isEmpty()) {
                jSONObject3.put(RemoteConfigConstants.RequestFieldKey.APP_ID, ga.d);
            }
            if (str.isEmpty()) {
                z = false;
            } else {
                jSONObject2.put("crashData", new JSONObject(str));
                z = true;
            }
            jSONObject3.put("isc", z);
            jSONObject2.put(EnvelopeCache.PREFIX_CURRENT_SESSION_FILE, jSONObject3);
            JSONArray jSONArray3 = new JSONArray();
            JSONObject jSONObject6 = ga.G;
            if (jSONObject6 != null) {
                jSONArray3.put(jSONObject6);
            }
            JSONObject batteryLevel = Util.getBatteryLevel(currentApplicationContext, fp.f157n);
            if (batteryLevel != null) {
                jSONArray3.put(batteryLevel);
            }
            jSONObject4.put("battery", jSONArray3);
            if (currentApplicationContext != null) {
                str5 = "ds";
                sharedPreferences = currentApplicationContext.getSharedPreferences("UXCamPreferences", 0);
            } else {
                str5 = "ds";
                sharedPreferences = null;
            }
            if (sharedPreferences == null) {
                str6 = "UXCamPreferences";
                string = "";
            } else {
                str6 = "UXCamPreferences";
                string = sharedPreferences.getString("push_notification_token", null);
            }
            jSONObject4.put("pushToken", string);
            jSONObject4.put(Session.JsonKeys.DID, DeviceInfo.generateUniqueId(currentApplicationContext));
            jSONObject4.put("osn", DeviceInfo.getAndroidOSName());
            jSONObject4.put("dvt", DeviceInfo.getDeviceType(currentApplicationContext));
            Point deviceResolution = DeviceInfo.getDeviceResolution(currentApplicationContext);
            int i3 = deviceResolution.y;
            int i4 = deviceResolution.x;
            if (i4 > i3) {
                jSONObject4.put("dwt", Integer.toString(i3));
                jSONObject4.put("dht", Integer.toString(i4));
            } else {
                jSONObject4.put("dwt", i4);
                jSONObject4.put("dht", i3);
            }
            jSONObject4.put("ahp", ScreenShotBitmapUtil.getInstance().getHeightOffset());
            Point deviceResolution2 = DeviceInfo.getDeviceResolution(currentApplicationContext);
            JSONArray jSONArray4 = new JSONArray();
            jSONArray4.put(deviceResolution2.x);
            jSONArray4.put(deviceResolution2.y);
            jSONObject4.put("ar", jSONArray4);
            DisplayMetrics displayMetrics = currentApplicationContext.getResources().getDisplayMetrics();
            JSONArray jSONArray5 = new JSONArray();
            jSONArray5.put(displayMetrics.xdpi);
            jSONArray5.put(displayMetrics.ydpi);
            jSONObject4.put("xyDpi", jSONArray5);
            jSONObject4.put("dpi", DeviceInfo.getDpi(currentApplicationContext));
            jSONObject4.put("osv", DeviceInfo.getOSVersion());
            jSONObject4.put("mnf", Build.MANUFACTURER);
            jSONObject4.put("mdl", Build.MODEL);
            jSONObject4.put("isr", Util.isRooted());
            jSONObject4.put("ttr", Util.getTotalRam(currentApplicationContext));
            jSONObject4.put("tts", (int) Util.getStorageSize());
            jSONObject4.put("tfs", (int) Util.getAvailableStorageSize());
            jSONObject4.put("cr", Util.getCarrier(currentApplicationContext));
            jSONObject4.put("cc", Util.getCarrierCode(currentApplicationContext));
            jSONObject4.put("cnt", Locale.getDefault().getDisplayCountry());
            jSONObject4.put("lng", Locale.getDefault().getDisplayLanguage());
            if (com.uxcam.aa.i == null || !Arrays.asList(f).contains(com.uxcam.aa.i)) {
                str7 = "";
                str8 = str7;
            } else {
                String str14 = com.uxcam.aa.i;
                str8 = com.uxcam.aa.j;
                str7 = str14;
            }
            jSONObject4.put("plf", Constants.KEY_ANDROID);
            jSONObject2.put(Device.TYPE, jSONObject4);
            gk.aa aaVarA2 = gk.a("bl");
            jSONObject4.toString();
            aaVarA2.getClass();
            jSONObject5.put("version", "3.6.13");
            jSONObject5.put("versionNumber", 580);
            jSONObject5.put("pluginType", str7);
            jSONObject5.put("pluginVersion", str8);
            jSONObject2.put("sdkv", jSONObject5);
            jSONObject2.put("ron", a(dA));
            Pair<String, Integer> applicationVersionName = Util.getApplicationVersionName(currentApplicationContext);
            jSONObject2.put("appv", applicationVersionName.first);
            jSONObject2.put("appvc", applicationVersionName.second);
            jSONObject2.put("appn", Util.getApplicationName(Util.getCurrentApplicationContext()));
            jSONObject2.put("misc", new JSONObject(ga.m));
            jSONObject2.put("pushNotificationData", ek.a(currentApplicationContext, dA));
            if (ga.A) {
                this.f101a.add(10);
            }
            JSONArray jSONArray6 = new JSONArray();
            if (a()) {
                obj = "reasonForNoVideo";
                obj2 = "recordStatus";
            } else {
                int iN = ((gw) bi.b().i()).n();
                if (!ga.r && (jSONArray2 = ga.t) != null && jSONArray2.length() > 0) {
                    a(iN, (int) dA, z);
                }
                if (!ga.s && (jSONArray = ga.u) != null && jSONArray.length() > 0) {
                    b(iN, (int) dA, z);
                }
                if (i) {
                    this.f101a.add(8);
                }
                JSONObject jSONObject7 = new JSONObject();
                jSONObject7.put("recordStatus", ga.f);
                if (!this.f101a.isEmpty()) {
                    Iterator<Integer> it = this.f101a.iterator();
                    while (it.hasNext()) {
                        jSONArray6.put(it.next().intValue());
                    }
                    jSONObject7.putOpt("reasonForNoVideo", jSONArray6);
                }
                if (ga.f) {
                    obj = "reasonForNoVideo";
                    obj2 = "recordStatus";
                    jSONObject7.putOpt("videoSize", Long.valueOf(this.b));
                } else {
                    obj = "reasonForNoVideo";
                    obj2 = "recordStatus";
                }
                jSONObject3.put("video", jSONObject7);
                if (hyVarM != null) {
                    hyVarM.a();
                }
                gv gvVarI = bi.b().i();
                gs gsVarH = bi.b().h();
                gw gwVar = (gw) gvVarI;
                jSONObject2.put("evt", gwVar.o());
                jSONObject2.put("anr", gwVar.k());
                jSONObject2.put("bugReport", gwVar.c());
                gwVar.h();
                gwVar.d();
                gwVar.j();
                gwVar.i();
                gk.a("bl").getClass();
                jSONObject2.put(DynamicLink.SocialMetaTagParameters.KEY_SOCIAL_TITLE, ((gu) gsVarH).a());
                if (ga.f168n) {
                    jSONObject2.put("sessionCancelled", 1);
                    g = true;
                    ga.f168n = false;
                } else if (ga.o) {
                    jSONObject2.put("sessionCancelled", 6);
                    g = true;
                    ga.o = false;
                } else if (g && ga.v) {
                    jSONObject2.put("sessionCancelled", 7);
                } else if (h) {
                    h = false;
                    g = true;
                }
            }
            if (a()) {
                jSONObject2.put("sessionCancelled", this.c);
            }
            bi biVarB = bi.b();
            if (biVarB.p == null) {
                biVarB.p = new du();
            }
            du duVar = biVarB.p;
            Intrinsics.checkNotNull(duVar);
            duVar.getClass();
            JSONObject jSONObject8 = new JSONObject();
            jSONObject8.put("vrt", duVar.f127a.f126a);
            Object obj3 = obj;
            String str15 = str5;
            jSONObject8.put(str15, duVar.f127a.b);
            jSONObject8.put(str4, duVar.f127a.c);
            String str16 = str3;
            jSONObject2.put(str16, jSONObject8);
            a(jSONObject3, dA);
            bi biVarB2 = bi.b();
            if (biVarB2.p == null) {
                biVarB2.p = new du();
            }
            du duVar2 = biVarB2.p;
            Intrinsics.checkNotNull(duVar2);
            duVar2.a(jSONObject2.toString().getBytes().length);
            bi biVarB3 = bi.b();
            if (biVarB3.p == null) {
                biVarB3.p = new du();
            }
            du duVar3 = biVarB3.p;
            Intrinsics.checkNotNull(duVar3);
            duVar3.getClass();
            JSONObject jSONObject9 = new JSONObject();
            jSONObject9.put("vrt", duVar3.f127a.f126a);
            jSONObject9.put(str15, duVar3.f127a.b);
            jSONObject9.put(str4, duVar3.f127a.c);
            jSONObject2.put(str16, jSONObject9);
            gk.aa aaVarA3 = gk.a("bl");
            jSONObject2.toString();
            aaVarA3.getClass();
            if (frVar != null) {
                frVar.a(jSONObject2.toString());
                str9 = str2;
            } else {
                str9 = str2;
                a(jSONObject2.toString(), str9);
            }
            File file = new File(str9 + FilePath.getTextFileName(Boolean.valueOf(ga.C)));
            ga.r = false;
            ga.s = false;
            if (g && !a()) {
                g = false;
                try {
                    File[] fileArrListFiles = file.getParentFile().listFiles(new aa());
                    Util.deleteRecursive(fileArrListFiles[0]);
                    gk.aa aaVarA4 = gk.a("filter11");
                    fileArrListFiles[0].getAbsolutePath();
                    aaVarA4.getClass();
                    HashMap map = new HashMap();
                    map.put(obj2, Boolean.valueOf(ga.f));
                    map.put("ignoreScreenVideo", Boolean.valueOf(g));
                    map.put(obj3, jSONArray6.toString());
                    ht.a("screenVideoIgnored", map);
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
                SharedPreferences sharedPreferences2 = currentApplicationContext.getSharedPreferences(str6, 0);
                int i5 = (sharedPreferences2 == null ? 0 : sharedPreferences2.getInt("recorded_session_count", 0)) + 1;
                if (sharedPreferences2 != null) {
                    sharedPreferences2.edit().putInt("recorded_session_count", i5).apply();
                }
            } else {
                SharedPreferences sharedPreferences3 = currentApplicationContext.getSharedPreferences(str6, 0);
                int i6 = (sharedPreferences3 == null ? 0 : sharedPreferences3.getInt("recorded_session_count", 0)) + 1;
                if (sharedPreferences3 != null) {
                    sharedPreferences3.edit().putInt("recorded_session_count", i6).apply();
                }
            }
            HashMap map2 = new HashMap();
            map2.put("file_size", Long.valueOf(file.length() / 1024));
            map2.put("cancelled", Integer.valueOf(jSONObject2.optInt("sessionCancelled")));
            ht.a("fileWriteCompleted", map2);
            if (!a()) {
                i = false;
                if (!bh.f97a) {
                    fp.f157n = 0L;
                }
            }
            return file;
        } catch (JSONException unused) {
            gk.a("bl").getClass();
            gk.c.getClass();
            ht.a("textFileWriteException", (Map<String, String>) null);
            return null;
        }
    }

    public final void a(JSONObject jSONObject, double d) throws JSONException {
        try {
            if (ga.J && this.e != null) {
                JSONArray jSONArrayA = this.e.a((long) (System.currentTimeMillis() - (d * 1000.0d)));
                jSONObject.put("appLogJson", jSONArrayA);
                jSONArrayA.length();
            } else {
                jSONObject.put("appLogError", "recordAppLog flag is disabled.");
            }
        } catch (IOException | JSONException e) {
            fj fjVarB = new fj().b("DataFile::addLogcatToData()");
            fjVarB.a("reason", e.getMessage());
            fjVarB.a(2);
        }
    }

    public static String a(double d) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS z", Locale.US);
        Calendar calendar = Calendar.getInstance();
        calendar.set(14, (int) (calendar.get(14) - (d * 1000.0d)));
        return simpleDateFormat.format(calendar.getTime());
    }

    public final void a(int i2, int i3, boolean z) {
        ArrayList arrayList = new ArrayList();
        if (bi.D == null) {
            bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
        }
        bi biVar = bi.D;
        Intrinsics.checkNotNull(biVar);
        gw gwVar = (gw) biVar.i();
        arrayList.addAll(gwVar.m());
        arrayList.addAll(gwVar.b);
        cf cfVar = new cf(arrayList, ga.t, z, i3, i2);
        if (cfVar.d()) {
            return;
        }
        this.f101a.addAll(cfVar.d);
        ga.o = true;
        gk.a("filter11").getClass();
    }

    public static void a(String str, String str2) throws JSONException, IOException {
        try {
            File file = new File(str2);
            if (!file.exists()) {
                file.mkdirs();
            }
            if (ga.C) {
                ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(new File(file, FilePath.getTextFileName(Boolean.valueOf(ga.C)))));
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                hd hdVar = new hd();
                GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(hdVar.a(byteArrayOutputStream));
                gZIPOutputStream.write(str.getBytes());
                gZIPOutputStream.close();
                zipOutputStream.putNextEntry(new ZipEntry("data.json.gz.aes"));
                zipOutputStream.write(byteArrayOutputStream.toByteArray());
                zipOutputStream.closeEntry();
                zipOutputStream.putNextEntry(new ZipEntry("metadata.json"));
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("decryptKey", hdVar.c());
                jSONObject.put("decryptiv", hdVar.b());
                jSONObject.toString();
                zipOutputStream.write(jSONObject.toString().getBytes());
                zipOutputStream.closeEntry();
                zipOutputStream.close();
                return;
            }
            FileOutputStream fileOutputStream = new FileOutputStream(new File(file, FilePath.getTextFileName(Boolean.valueOf(ga.C))));
            fileOutputStream.write(str.getBytes());
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            fj fjVarB = new fj().b("DataFile::generateFileOnSD() -> catch1");
            fjVarB.a("reason", e.getMessage());
            fjVarB.a("file_path", str2).a(2);
        } catch (JSONException e2) {
            e2.printStackTrace();
            fj fjVarB2 = new fj().b("DataFile::generateFileOnSD() -> catch2");
            fjVarB2.a("reason", e2.getMessage());
            fjVarB2.a(2);
        }
    }

    public static void a(String str) {
        try {
            File file = new File(FilePath.getSessionRootUrl(ga.b, Boolean.TRUE));
            if (!file.exists()) {
                file.mkdirs();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(new File(file, str));
            fileOutputStream.write("".getBytes());
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            fj fjVarB = new fj().b("DataFile::generateEmptyDataFileOnSD()");
            fjVarB.a("reason", e.getMessage());
            fjVarB.a(2);
        }
    }
}
