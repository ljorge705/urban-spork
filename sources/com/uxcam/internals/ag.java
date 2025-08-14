package com.uxcam.internals;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.media.session.PlaybackStateCompat;
import androidx.webkit.internal.AssetHelper;
import com.clevertap.android.sdk.Constants;
import com.google.common.net.HttpHeaders;
import com.onfido.android.sdk.capture.config.MediaCallbackResultReceiver;
import com.reactnativecommunity.clipboard.ClipboardModule;
import com.uxcam.internals.gk;
import com.uxcam.screenaction.utils.Connectivity;
import com.uxcam.screenaction.utils.FilePath;
import com.uxcam.screenaction.utils.Util;
import com.uxcam.service.HttpPostService;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public final class ag {

    /* renamed from: a, reason: collision with root package name */
    public Context f78a;
    public File b;
    public JSONObject c;
    public boolean d = false;

    public class aa implements Callback {

        /* renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f79a;
        public final /* synthetic */ String b;

        public aa(String str, String str2, String str3) {
            this.f79a = str;
            this.b = str3;
        }

        @Override // okhttp3.Callback
        public final void onFailure(Call call, IOException iOException) {
            try {
                gk.a("S3Uploader").getClass();
                ag agVar = ag.this;
                ag.a(agVar, agVar.b, iOException.getMessage(), -1);
            } catch (Exception unused) {
                gk.a("S3Uploader").getClass();
                String strReplace = "[#status#] #method#".replace("#status#", "FAIL");
                HashMap map = new HashMap();
                map.put("site_of_error", "AmazonUploader::anonymousCallback -> onFailure()");
                map.put("reason", "exception while trying to log failure : { " + iOException.getMessage() + " }");
                ht.a(strReplace, (Map<String, String>) map);
            }
        }

        @Override // okhttp3.Callback
        public final void onResponse(Call call, Response response) throws IOException {
            String str;
            File[] fileArrListFiles;
            if (!response.isSuccessful()) {
                ag agVar = ag.this;
                ag.a(agVar, agVar.b, response.message(), response.code());
            } else if (Integer.parseInt(this.f79a) == response.code()) {
                gk.aa aaVarA = gk.a("S3Uploader");
                ag.this.b.length();
                aaVarA.getClass();
                File file = ag.this.b;
                String str2 = HttpPostService.f290a;
                HttpPostService.aa.a(file);
                String strHeader = response.header(HttpHeaders.ETAG);
                if (strHeader != null && (str = this.b) != null && strHeader.contains(str)) {
                    if (Connectivity.isConnectedMobile(ag.this.f78a)) {
                        eg egVar = new eg(ag.this.f78a);
                        long length = ag.this.b.length();
                        SharedPreferences sharedPreferences = egVar.f135a;
                        egVar.a((sharedPreferences != null ? sharedPreferences.getLong("mobile_data_used_size", 0L) : 0L) + length);
                    }
                    ag.this.b.delete();
                    ag agVar2 = ag.this;
                    File parentFile = agVar2.b.getParentFile();
                    if (parentFile != null && (fileArrListFiles = parentFile.listFiles()) != null && fileArrListFiles.length == 1) {
                        String name = fileArrListFiles[0].getName();
                        if (name.endsWith(".usid")) {
                            String str3 = name.split(".usid")[0];
                            eg egVar2 = new eg(agVar2.f78a);
                            egVar2.a(str3);
                            egVar2.a("override_mobile_data_data_only_setting_" + parentFile.getName());
                            gk.a("S3Uploader").getClass();
                        }
                        fileArrListFiles[0].delete();
                        parentFile.delete();
                        gk.aa aaVarA2 = gk.a("S3Uploader");
                        parentFile.getName();
                        aaVarA2.getClass();
                    }
                }
                com.uxcam.aa.a();
                String strReplace = ag.this.b.getName().replace("$", "/");
                String strReplace2 = "[#status#] #method#".replace("#method#", "S3 File Upload").replace("#status#", "SUCCESS");
                HashMap map = new HashMap();
                map.put(MediaCallbackResultReceiver.KEY_FILE_NAME, strReplace);
                map.put("file_size", "" + ag.this.b.length());
                map.put("is_offline", "" + ag.this.d);
                ht.a(strReplace2, (Map<String, String>) map);
            } else {
                ag agVar3 = ag.this;
                ag.a(agVar3, agVar3.b, response.message(), response.code());
            }
            response.body().close();
        }
    }

    public static void a(ag agVar, File file, String str, int i) {
        agVar.getClass();
        gk.aa aaVarA = gk.a("S3Uploader");
        file.getAbsolutePath();
        file.length();
        aaVarA.getClass();
        String str2 = HttpPostService.f290a;
        HttpPostService.aa.a(file);
        com.uxcam.aa.a();
        String strReplace = file.getName().replace("$", "/");
        String strReplace2 = "[#status#] #method#".replace("#method#", "S3 File Upload").replace("#status#", "FAIL");
        HashMap map = new HashMap();
        map.put("http_response", str);
        map.put("response_code", "" + i);
        map.put("is_offline", String.valueOf(agVar.d));
        map.put(MediaCallbackResultReceiver.KEY_FILE_NAME, strReplace);
        ht.a(strReplace2, (Map<String, String>) map);
    }

    public final void b(Context context, File file) {
        String str = HttpPostService.f290a;
        Intrinsics.checkNotNullParameter(file, "file");
        Iterator<String> it = HttpPostService.c.iterator();
        while (true) {
            if (!it.hasNext()) {
                List<String> list = HttpPostService.c;
                String absolutePath = file.getAbsolutePath();
                Intrinsics.checkNotNullExpressionValue(absolutePath, "file.absolutePath");
                list.add(absolutePath);
                break;
            }
            if (Intrinsics.areEqual(file.getAbsolutePath(), it.next())) {
                break;
            }
        }
        a(context, file);
    }

    public final void a(Context context, File file) throws JSONException {
        this.f78a = context;
        if (file.exists()) {
            this.b = file;
            if (this.c == null) {
                this.c = ga.j;
            }
            boolean zIsConnectedMobile = Connectivity.isConnectedMobile(context);
            SharedPreferences sharedPreferences = context != null ? context.getSharedPreferences("UXCamPreferences", 0) : null;
            boolean z = sharedPreferences != null && sharedPreferences.getBoolean(new StringBuilder("override_mobile_data_data_only_setting_").append(file.getParentFile().getName()).toString(), false);
            if (zIsConnectedMobile && z) {
                String strReplace = "[#status#] #method#".replace("#status#", "START");
                HashMap map = new HashMap();
                map.put("site_of_error", "AmazonUploader::upload() -> if0");
                map.put("invokes_next", "upload(false)");
                ht.b(strReplace, map);
                a(false);
                return;
            }
            if (zIsConnectedMobile && ga.i) {
                String strReplace2 = "[#status#] #method#".replace("#status#", "START");
                HashMap map2 = new HashMap();
                map2.put("site_of_error", "AmazonUploader::upload() -> if1");
                map2.put("invokes_next", "upload(true)");
                ht.b(strReplace2, map2);
                a(true);
                return;
            }
            boolean zIsConnectedWifi = Connectivity.isConnectedWifi(this.f78a);
            boolean zIsConnectedMobile2 = Connectivity.isConnectedMobile(this.f78a);
            Context context2 = this.f78a;
            SharedPreferences sharedPreferences2 = context2 != null ? context2.getSharedPreferences("UXCamPreferences", 0) : null;
            int i = sharedPreferences2 == null ? 0 : sharedPreferences2.getInt("current_month", 0);
            int i2 = Calendar.getInstance().get(2) + 1;
            if (i != i2) {
                new eg(this.f78a).a("current_month", i2);
                new eg(this.f78a).a(0L);
                gk.a("S3Uploader").getClass();
            }
            if (zIsConnectedMobile2 && ga.h > 0) {
                long jFolderSize = Util.folderSize(this.b.getParentFile());
                long j = ga.h * PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
                Context context3 = this.f78a;
                SharedPreferences sharedPreferences3 = context3 != null ? context3.getSharedPreferences("UXCamPreferences", 0) : null;
                long j2 = sharedPreferences3 == null ? 0L : sharedPreferences3.getLong("mobile_data_used_size", 0L);
                gk.a("S3Uploader").getClass();
                if (jFolderSize > j - j2) {
                    gk.a("S3Uploader").getClass();
                    HashMap map3 = new HashMap();
                    String strReplace3 = "[ #event# ]".replace("#event#", "Monthly Data Limit Reached");
                    map3.put("site_of_error", "AmazonUploader::isBelowDataSizeLimit");
                    map3.put("data_limit_kb", "" + j);
                    ht.a(strReplace3, (Map<String, String>) map3);
                    return;
                }
            } else if (!zIsConnectedWifi) {
                gk.a("S3Uploader").getClass();
                return;
            }
            String strReplace4 = "[#status#] #method#".replace("#status#", "START");
            HashMap map4 = new HashMap();
            map4.put("site_of_error", "AmazonUploader::upload() -> if2");
            map4.put("invokes_next", "upload(false)");
            ht.b(strReplace4, map4);
            a(false);
            return;
        }
        String strReplace5 = "[#status#] #method#".replace("#status#", "START");
        HashMap map5 = new HashMap();
        map5.put("site_of_error", "AmazonUploader::upload() -> else");
        map5.put("name_of_file", file.getAbsolutePath());
        map5.put("condition_met -> is_below_data_size_limit", "true");
        ht.b(strReplace5, map5);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r15v1 */
    /* JADX WARN: Type inference failed for: r15v10 */
    /* JADX WARN: Type inference failed for: r15v11 */
    /* JADX WARN: Type inference failed for: r15v12, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r15v16 */
    /* JADX WARN: Type inference failed for: r15v3 */
    /* JADX WARN: Type inference failed for: r15v4 */
    /* JADX WARN: Type inference failed for: r15v6, types: [boolean] */
    /* JADX WARN: Type inference failed for: r15v8 */
    /* JADX WARN: Type inference failed for: r15v9 */
    public final void a(boolean z) throws JSONException {
        Object obj;
        Object obj2;
        String str;
        String str2;
        ?? StartsWith;
        String str3;
        String str4;
        Object obj3;
        String str5;
        JSONObject jSONObject;
        String string;
        String string2;
        String str6;
        String strReplaceExtensioin;
        String strReplace;
        HashMap map;
        String str7 = "bundle";
        try {
            File[] fileArrListFiles = this.b.listFiles();
            if (fileArrListFiles != null) {
                str5 = "file name comparison has failed, there exist no valid file named : ";
                try {
                    if (fileArrListFiles.length > 0) {
                        for (File file : fileArrListFiles) {
                            ag agVar = new ag();
                            agVar.d = this.d;
                            agVar.c = this.c;
                            agVar.a(this.f78a, file);
                        }
                        return;
                    }
                } catch (Exception e) {
                    e = e;
                    obj = "file_size";
                    obj2 = MediaCallbackResultReceiver.KEY_FILE_NAME;
                    str = "#status#";
                    str4 = "S3 File Upload";
                    str3 = "";
                    obj3 = "reason";
                    String strReplace2 = "[#status#] #method#".replace("#method#", str4).replace(str, "SUCCESS");
                    String str8 = "an exception was thrown " + e.getMessage();
                    HashMap map2 = new HashMap();
                    map2.put(obj3, str8);
                    map2.put(obj2, this.b.getName());
                    map2.put(obj, str3 + this.b.length());
                    map2.put("is_offline", str3 + this.d);
                    ht.a(strReplace2, (Map<String, String>) map2);
                }
            } else {
                str5 = "file name comparison has failed, there exist no valid file named : ";
            }
            String name = this.b.getName();
            boolean zStartsWith = name.startsWith("data");
            if (z && !zStartsWith) {
                File file2 = this.b;
                String str9 = HttpPostService.f290a;
                HttpPostService.aa.a(file2);
                return;
            }
            StartsWith = name.startsWith("video");
            str = "S3Uploader";
            obj = "file_size";
            str2 = "";
            obj2 = MediaCallbackResultReceiver.KEY_FILE_NAME;
            try {
                try {
                    if (StartsWith != 0) {
                        str6 = "video/mp4";
                        jSONObject = this.c.getJSONObject("video").getJSONObject("body");
                        string = this.c.getJSONObject("video").getString("url");
                        string2 = this.c.getJSONObject("video").getJSONObject("body").getString("success_action_status");
                    } else {
                        try {
                            if (name.startsWith("data")) {
                                str6 = AssetHelper.DEFAULT_MIME_TYPE;
                                jSONObject = this.c.getJSONObject("data").getJSONObject("body");
                                String string3 = this.c.getJSONObject("data").getString("url");
                                string2 = this.c.getJSONObject("data").getJSONObject("body").getString("success_action_status");
                                string = string3;
                            } else if (name.startsWith(Constants.KEY_ICON)) {
                                if (!this.c.has(Constants.KEY_ICON)) {
                                    gk.a("S3Uploader").getClass();
                                    this.b.delete();
                                    return;
                                } else {
                                    str6 = ClipboardModule.MIMETYPE_PNG;
                                    JSONObject jSONObject2 = this.c.getJSONObject(Constants.KEY_ICON).getJSONObject("body");
                                    string = this.c.getJSONObject(Constants.KEY_ICON).getString("url");
                                    string2 = this.c.getJSONObject(Constants.KEY_ICON).getJSONObject("body").getString("success_action_status");
                                    jSONObject = jSONObject2;
                                }
                            } else if (name.startsWith("bundle")) {
                                try {
                                    if (!this.c.has("bundle")) {
                                        str7 = "data";
                                    }
                                    jSONObject = this.c.getJSONObject(str7).getJSONObject("body");
                                    string = this.c.getJSONObject(str7).getString("url");
                                    string2 = this.c.getJSONObject(str7).getJSONObject("body").getString("success_action_status");
                                    str6 = "application/zip";
                                } catch (Exception e2) {
                                    e = e2;
                                    str = "#status#";
                                    StartsWith = str2;
                                    str2 = "S3 File Upload";
                                    obj3 = "reason";
                                    str4 = str2;
                                    str3 = StartsWith;
                                    String strReplace22 = "[#status#] #method#".replace("#method#", str4).replace(str, "SUCCESS");
                                    String str82 = "an exception was thrown " + e.getMessage();
                                    HashMap map22 = new HashMap();
                                    map22.put(obj3, str82);
                                    map22.put(obj2, this.b.getName());
                                    map22.put(obj, str3 + this.b.length());
                                    map22.put("is_offline", str3 + this.d);
                                    ht.a(strReplace22, (Map<String, String>) map22);
                                }
                            } else {
                                str = "#status#";
                                StartsWith = str2;
                                str2 = "S3 File Upload";
                                HashMap map3 = new HashMap();
                                map3.put("site_of_error", "AmazonUploader::upload -> else { }");
                                obj3 = "reason";
                                try {
                                    map3.put(obj3, str5 + this.b.getName());
                                    ht.a("[#status#] #method#", (Map<String, String>) map3);
                                    return;
                                } catch (Exception e3) {
                                    e = e3;
                                    str4 = str2;
                                    str3 = StartsWith;
                                    String strReplace222 = "[#status#] #method#".replace("#method#", str4).replace(str, "SUCCESS");
                                    String str822 = "an exception was thrown " + e.getMessage();
                                    HashMap map222 = new HashMap();
                                    map222.put(obj3, str822);
                                    map222.put(obj2, this.b.getName());
                                    map222.put(obj, str3 + this.b.length());
                                    map222.put("is_offline", str3 + this.d);
                                    ht.a(strReplace222, (Map<String, String>) map222);
                                }
                            }
                        } catch (Exception e4) {
                            e = e4;
                            str = "#status#";
                            obj3 = "reason";
                            StartsWith = str2;
                            str2 = "S3 File Upload";
                        }
                    }
                    jSONObject.remove("file");
                    strReplaceExtensioin = FilePath.replaceExtensioin(jSONObject.optString(Constants.KEY_KEY), FilePath.getExtension(this.b.getName()));
                    jSONObject.put(Constants.KEY_KEY, strReplaceExtensioin);
                    gk.aa aaVarA = gk.a("S3Uploader");
                    this.b.getAbsolutePath();
                    aaVarA.getClass();
                    strReplace = "[#status#] #method#".replace("#method#", "S3 File Upload").replace("#status#", "START");
                    map = new HashMap();
                } catch (Exception e5) {
                    e = e5;
                }
                try {
                    map.put(obj2, strReplaceExtensioin);
                    StartsWith = str2;
                    try {
                        obj2 = obj2;
                    } catch (Exception e6) {
                        e = e6;
                        str = "#status#";
                        str2 = "S3 File Upload";
                        obj2 = obj2;
                    }
                } catch (Exception e7) {
                    e = e7;
                    str = "#status#";
                    obj2 = obj2;
                    StartsWith = str2;
                    str2 = "S3 File Upload";
                    obj3 = "reason";
                    str4 = str2;
                    str3 = StartsWith;
                    String strReplace2222 = "[#status#] #method#".replace("#method#", str4).replace(str, "SUCCESS");
                    String str8222 = "an exception was thrown " + e.getMessage();
                    HashMap map2222 = new HashMap();
                    map2222.put(obj3, str8222);
                    map2222.put(obj2, this.b.getName());
                    map2222.put(obj, str3 + this.b.length());
                    map2222.put("is_offline", str3 + this.d);
                    ht.a(strReplace2222, (Map<String, String>) map2222);
                }
            } catch (Exception e8) {
                e = e8;
                str = "#status#";
                str4 = "S3 File Upload";
                obj3 = "reason";
                str3 = str2;
                String strReplace22222 = "[#status#] #method#".replace("#method#", str4).replace(str, "SUCCESS");
                String str82222 = "an exception was thrown " + e.getMessage();
                HashMap map22222 = new HashMap();
                map22222.put(obj3, str82222);
                map22222.put(obj2, this.b.getName());
                map22222.put(obj, str3 + this.b.length());
                map22222.put("is_offline", str3 + this.d);
                ht.a(strReplace22222, (Map<String, String>) map22222);
            }
            try {
                str = "#status#";
                str2 = "S3 File Upload";
                try {
                    map.put(obj, ((String) StartsWith) + this.b.length());
                    map.put("is_offline_session", ((String) StartsWith) + this.d);
                    ht.a(strReplace, (Map<String, String>) map);
                    OkHttpClient.Builder builder = new OkHttpClient.Builder();
                    TimeUnit timeUnit = TimeUnit.MILLISECONDS;
                    OkHttpClient okHttpClientBuild = builder.connectTimeout(30000L, timeUnit).writeTimeout(30000L, timeUnit).readTimeout(30000L, timeUnit).build();
                    MediaType mediaType = MediaType.parse(str6);
                    MultipartBody.Builder type = new MultipartBody.Builder().setType(MultipartBody.FORM);
                    Iterator<String> itKeys = jSONObject.keys();
                    while (itKeys.hasNext()) {
                        String next = itKeys.next();
                        type.addFormDataPart(next, jSONObject.getString(next));
                    }
                    type.addFormDataPart("file", "X", RequestBody.create(mediaType, this.b));
                    okHttpClientBuild.newCall(new Request.Builder().url(string).post(type.build()).build()).enqueue(new aa(string2, name, dm.a(this.b)));
                } catch (Exception e9) {
                    e = e9;
                    obj = obj;
                    obj3 = "reason";
                    str4 = str2;
                    str3 = StartsWith;
                    String strReplace222222 = "[#status#] #method#".replace("#method#", str4).replace(str, "SUCCESS");
                    String str822222 = "an exception was thrown " + e.getMessage();
                    HashMap map222222 = new HashMap();
                    map222222.put(obj3, str822222);
                    map222222.put(obj2, this.b.getName());
                    map222222.put(obj, str3 + this.b.length());
                    map222222.put("is_offline", str3 + this.d);
                    ht.a(strReplace222222, (Map<String, String>) map222222);
                }
            } catch (Exception e10) {
                e = e10;
                str = "#status#";
                StartsWith = StartsWith;
                str2 = "S3 File Upload";
                obj3 = "reason";
                str4 = str2;
                str3 = StartsWith;
                String strReplace2222222 = "[#status#] #method#".replace("#method#", str4).replace(str, "SUCCESS");
                String str8222222 = "an exception was thrown " + e.getMessage();
                HashMap map2222222 = new HashMap();
                map2222222.put(obj3, str8222222);
                map2222222.put(obj2, this.b.getName());
                map2222222.put(obj, str3 + this.b.length());
                map2222222.put("is_offline", str3 + this.d);
                ht.a(strReplace2222222, (Map<String, String>) map2222222);
            }
        } catch (Exception e11) {
            e = e11;
            obj = "file_size";
            obj2 = MediaCallbackResultReceiver.KEY_FILE_NAME;
            str = "#status#";
            str2 = "S3 File Upload";
            StartsWith = "";
        }
    }
}
