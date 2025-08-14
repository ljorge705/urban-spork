package com.uxcam.internals;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import com.clevertap.android.sdk.Constants;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.uxcam.internals.gk;
import com.uxcam.screenaction.di.ScreenActionModule;
import com.uxcam.screenaction.utils.FilePath;
import com.uxcam.screenaction.utils.Util;
import com.uxcam.screenshot.di.ScreenshotModule;
import com.uxcam.screenshot.utils.DeviceInfo;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.uxcam.internals.if, reason: invalid class name */
/* loaded from: classes6.dex */
public final class Cif {
    public static final String c = "if";
    public static final /* synthetic */ boolean d = true;

    /* renamed from: a, reason: collision with root package name */
    public final Context f208a;
    public JSONObject b;

    /* renamed from: com.uxcam.internals.if$aa */
    public class aa implements Callback {

        /* renamed from: a, reason: collision with root package name */
        public final /* synthetic */ ag f209a;

        public aa(ag agVar) {
            this.f209a = agVar;
        }

        @Override // okhttp3.Callback
        public final void onFailure(Call call, IOException iOException) {
            gk.aa aaVarA = gk.a("OkHttp");
            iOException.getMessage();
            aaVarA.getClass();
            String strReplace = "[#status#] #method#".replace("#method#", "verifyAndUpload").replace("#status#", "FAIL");
            String str = "" + iOException.getMessage();
            HashMap map = new HashMap();
            map.put("reason", str);
            ht.a(strReplace, (Map<String, String>) map);
        }

        @Override // okhttp3.Callback
        public final void onResponse(Call call, Response response) throws JSONException, IOException {
            boolean z;
            File[] fileArrListFiles;
            if (bi.D == null) {
                bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
            }
            bi biVar = bi.D;
            Intrinsics.checkNotNull(biVar);
            fu fuVarF = biVar.f();
            try {
            } catch (IOException | JSONException e) {
                String strReplace = "[#status#] #method#".replace("#method#", "verifyAndUpload").replace("#status#", "FAIL");
                HashMap map = new HashMap();
                map.put("site_of_error", "try { }");
                map.put("reason", e.getMessage());
                ht.a(strReplace, (Map<String, String>) map);
            }
            JSONObject jSONObject = response.body() != null ? new JSONObject(response.body().string()) : null;
            File parentFile = this.f209a.b.getParentFile();
            if (parentFile == null || (fileArrListFiles = parentFile.listFiles()) == null) {
                z = false;
            } else {
                int length = fileArrListFiles.length;
                int i = 0;
                while (i < length) {
                    File[] fileArr = fileArrListFiles;
                    int i2 = length;
                    if (fileArrListFiles[i].getName().endsWith(".usid")) {
                        z = true;
                        break;
                    } else {
                        i++;
                        length = i2;
                        fileArrListFiles = fileArr;
                    }
                }
                z = false;
            }
            if (ig.a(jSONObject, z)) {
                ((fv) fuVarF).c = false;
                String strReplace2 = "[#status#] #method#".replace("#method#", "verifyAndUpload").replace("#status#", "FAIL");
                HashMap map2 = new HashMap();
                map2.put("site_of_error", "shouldCancelSessionWithoutSendingCancellation");
                map2.put("reason", jSONObject.toString());
                ht.a(strReplace2, (Map<String, String>) map2);
                Util.deleteRecursive(this.f209a.b);
                return;
            }
            if (jSONObject == null || response.code() != 200) {
                ((fv) fuVarF).c = false;
                String strReplace3 = "[#status#] #method#".replace("#method#", "verifyAndUpload").replace("#status#", "FAIL");
                HashMap map3 = new HashMap();
                map3.put("site_of_error", "200 != response.code()");
                map3.put("reason", "Expected status code { 200 } but received was { " + response.code() + " }");
                ht.a(strReplace3, (Map<String, String>) map3);
                return;
            }
            try {
                if (jSONObject.optBoolean("status", true)) {
                    String string = jSONObject.getJSONObject("data").getString("sessionId");
                    Cif cif = Cif.this;
                    File file = this.f209a.b;
                    cif.getClass();
                    Cif.c(file, string);
                    Cif cif2 = Cif.this;
                    new eg(cif2.f208a).a(string, cif2.b.toString());
                    gk.aa aaVarA = gk.a(Cif.c);
                    jSONObject.getJSONObject("data").getString("sessionId");
                    aaVarA.getClass();
                    this.f209a.c = jSONObject.getJSONObject("data").getJSONObject("s3");
                    this.f209a.a(false);
                }
            } catch (JSONException e2) {
                String strReplace4 = "[#status#] #method#".replace("#method#", "verifyAndUpload").replace("#status#", "FAIL");
                HashMap map4 = new HashMap();
                map4.put("site_of_error", "try { }");
                map4.put("reason", String.valueOf(e2.getMessage()));
                ht.a(strReplace4, (Map<String, String>) map4);
            }
        }
    }

    public Cif(Context context) throws JSONException {
        this.f208a = context;
        String strGenerateUniqueId = DeviceInfo.generateUniqueId(context);
        String applicationPackageName = Util.getApplicationPackageName(context, ga.c);
        String deviceType = DeviceInfo.getDeviceType(context);
        String str = (String) Util.getApplicationVersionName(context).first;
        String strValueOf = String.valueOf(580);
        String str2 = Build.VERSION.RELEASE;
        String str3 = Build.MODEL;
        int iA = new eg(context).a();
        int iB = new eg(context).b();
        try {
            JSONObject jSONObject = new JSONObject();
            this.b = jSONObject;
            jSONObject.putOpt("buildIdentifier", applicationPackageName);
            this.b.putOpt(Constants.DEVICE_ID_TAG, strGenerateUniqueId);
            this.b.putOpt("osVersion", str2);
            this.b.putOpt("platform", hq.b);
            this.b.putOpt("deviceType", deviceType);
            this.b.putOpt("deviceModelName", str3);
            this.b.putOpt(RemoteConfigConstants.RequestFieldKey.APP_VERSION, str);
            this.b.putOpt(RemoteConfigConstants.RequestFieldKey.SDK_VERSION, "3.6.13");
            this.b.putOpt("sdkVersionNumber", strValueOf);
            this.b.putOpt("sessionsRecordedOnDevice", Integer.valueOf(iA));
            this.b.putOpt("videosRecordedOnDevice", Integer.valueOf(iB));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void c(File file, String str) throws IOException {
        if (!file.exists()) {
            file.mkdirs();
        }
        File file2 = new File(file, str + ".usid");
        gk.aa aaVarA = gk.a("file72");
        file2.getAbsolutePath();
        aaVarA.getClass();
        File[] fileArrListFiles = file.listFiles(new FilenameFilter() { // from class: com.uxcam.internals.if$$ExternalSyntheticLambda1
            @Override // java.io.FilenameFilter
            public final boolean accept(File file3, String str2) {
                return str2.toLowerCase(Locale.ENGLISH).endsWith(".usid");
            }
        });
        if (fileArrListFiles == null || fileArrListFiles.length != 0) {
            return;
        }
        try {
            file2.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:59:0x01d0 A[Catch: Exception -> 0x0228, LOOP:0: B:57:0x01ca->B:59:0x01d0, LOOP_END, TryCatch #1 {Exception -> 0x0228, blocks: (B:11:0x006c, B:14:0x0077, B:15:0x007c, B:17:0x0084, B:18:0x008b, B:20:0x00ad, B:22:0x00ce, B:24:0x00d4, B:26:0x00ef, B:28:0x010d, B:30:0x012d, B:32:0x0135, B:34:0x013d, B:35:0x0148, B:37:0x0150, B:38:0x0163, B:42:0x017a, B:61:0x01dc, B:62:0x01e9, B:64:0x01f7, B:68:0x0211, B:70:0x021b, B:41:0x016d, B:43:0x0188, B:56:0x01a9, B:57:0x01ca, B:59:0x01d0, B:54:0x01a5, B:21:0x00b8, B:65:0x01f9), top: B:77:0x006c, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x01dc A[Catch: Exception -> 0x0228, TryCatch #1 {Exception -> 0x0228, blocks: (B:11:0x006c, B:14:0x0077, B:15:0x007c, B:17:0x0084, B:18:0x008b, B:20:0x00ad, B:22:0x00ce, B:24:0x00d4, B:26:0x00ef, B:28:0x010d, B:30:0x012d, B:32:0x0135, B:34:0x013d, B:35:0x0148, B:37:0x0150, B:38:0x0163, B:42:0x017a, B:61:0x01dc, B:62:0x01e9, B:64:0x01f7, B:68:0x0211, B:70:0x021b, B:41:0x016d, B:43:0x0188, B:56:0x01a9, B:57:0x01ca, B:59:0x01d0, B:54:0x01a5, B:21:0x00b8, B:65:0x01f9), top: B:77:0x006c, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x01e9 A[Catch: Exception -> 0x0228, TryCatch #1 {Exception -> 0x0228, blocks: (B:11:0x006c, B:14:0x0077, B:15:0x007c, B:17:0x0084, B:18:0x008b, B:20:0x00ad, B:22:0x00ce, B:24:0x00d4, B:26:0x00ef, B:28:0x010d, B:30:0x012d, B:32:0x0135, B:34:0x013d, B:35:0x0148, B:37:0x0150, B:38:0x0163, B:42:0x017a, B:61:0x01dc, B:62:0x01e9, B:64:0x01f7, B:68:0x0211, B:70:0x021b, B:41:0x016d, B:43:0x0188, B:56:0x01a9, B:57:0x01ca, B:59:0x01d0, B:54:0x01a5, B:21:0x00b8, B:65:0x01f9), top: B:77:0x006c, inners: #0 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a(org.json.JSONObject r17, boolean r18, java.lang.String r19) throws org.json.JSONException {
        /*
            Method dump skipped, instructions count: 576
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.uxcam.internals.Cif.a(org.json.JSONObject, boolean, java.lang.String):void");
    }

    public final void a(ag agVar, String str) {
        String str2;
        aa aaVar = new aa(agVar);
        File[] fileArrListFiles = agVar.b.listFiles(new FilenameFilter() { // from class: com.uxcam.internals.if$$ExternalSyntheticLambda0
            @Override // java.io.FilenameFilter
            public final boolean accept(File file, String str3) {
                return str3.endsWith(".usid");
            }
        });
        if (fileArrListFiles == null || fileArrListFiles.length <= 0) {
            str2 = null;
        } else {
            str2 = fileArrListFiles[0].getName().split("\\.")[0];
            StringBuilder sb = new StringBuilder();
            for (File file : fileArrListFiles) {
                sb.append(" | ").append(file);
            }
            HashMap map = new HashMap();
            map.put("data_size", "" + fileArrListFiles.length);
            map.put("files", "" + ((Object) sb));
            ht.b("verifyAndUpload", map);
        }
        a(str, aaVar, str2, false);
    }

    public final void a(final String str) {
        String str2 = c;
        gk.a(str2).getClass();
        Context context = this.f208a;
        SharedPreferences sharedPreferences = context != null ? context.getSharedPreferences("UXCamPreferences", 0) : null;
        String string = sharedPreferences == null ? "" : sharedPreferences.getString("settings_" + str.hashCode(), null);
        if (string == null) {
            gk.a(str2).getClass();
            HashMap map = new HashMap();
            map.put("reason", "cacheResponse is null.");
            map.put("site_of_error", "autoVerify()");
            ht.a("[FAIL] Auto Verify", (Map<String, String>) map);
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(string);
            int i = ga.f167a;
            File[] fileArrListFiles = new File(FilePath.getRootUrl(true)).listFiles(new FilenameFilter() { // from class: com.uxcam.internals.if$$ExternalSyntheticLambda2
                @Override // java.io.FilenameFilter
                public final boolean accept(File file, String str3) {
                    return Cif.a(str, file, str3);
                }
            });
            int length = fileArrListFiles == null ? 0 : fileArrListFiles.length;
            gk.a(str2).getClass();
            if (length >= jSONObject.getJSONObject("data").getJSONObject("settings").optInt("maxOfflineVideos", 0)) {
                ga.A = true;
                jSONObject.getJSONObject("data").put("videoRecording", false);
            }
            a(jSONObject, true, str);
        } catch (Exception e) {
            gk.a(c).getClass();
            HashMap map2 = new HashMap();
            map2.put("reason", "exception was thrown : " + e.getMessage());
            map2.put("at", "autoVerify()");
            ht.a("[FAIL] Auto Verify", (Map<String, String>) map2);
        }
    }

    public static /* synthetic */ boolean a(String str, File file, String str2) {
        try {
            boolean z = str2.endsWith(String.valueOf(str.hashCode())) && Util.containsMp4(new File(file, str2)) && !Util.hasEmptyDataFile(new File(file, str2));
            gk.a("val72").getClass();
            return z;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean a(Context context, JSONObject jSONObject) {
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (jSONObject.has("stopUntil") && System.currentTimeMillis() < jSONObject.optDouble("stopUntil") * 1000.0d) {
            return true;
        }
        if (jSONObject.has("sdkVersionNewerThan") && 580.0d <= jSONObject.optDouble("sdkVersionNewerThan")) {
            return true;
        }
        if (jSONObject.has("appVersionNotInList")) {
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("appVersionNotInList");
            if (!d && jSONArrayOptJSONArray == null) {
                throw new AssertionError();
            }
            String str = (String) Util.getApplicationVersionName(context).first;
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                if (jSONArrayOptJSONArray.getString(i).equalsIgnoreCase(str)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x000e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a(java.lang.String r9, okhttp3.Callback r10, java.lang.String r11, boolean r12) {
        /*
            Method dump skipped, instructions count: 376
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.uxcam.internals.Cif.a(java.lang.String, okhttp3.Callback, java.lang.String, boolean):void");
    }
}
