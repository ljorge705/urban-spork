package com.uxcam.internals;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Debug;
import android.os.SystemClock;
import android.util.Pair;
import com.google.firebase.remoteconfig.internal.Personalization;
import com.uxcam.internals.aa;
import com.uxcam.internals.ff;
import com.uxcam.internals.gk;
import com.uxcam.screenaction.models.KeyConstant;
import com.uxcam.screenaction.utils.FilePath;
import com.uxcam.screenaction.utils.Util;
import com.uxcam.screenshot.di.ScreenshotModule;
import com.uxcam.screenshot.state.ScreenshotStateHolder;
import com.uxcam.service.HttpPostService;
import java.io.File;
import java.io.IOException;
import java.lang.Thread;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Timer;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public final class fp {

    /* renamed from: n, reason: collision with root package name */
    public static long f157n;
    public static final /* synthetic */ int o = 0;

    /* renamed from: a, reason: collision with root package name */
    public boolean f158a = false;
    public boolean b = false;
    public Context c;
    public aa d;
    public fr e;
    public Timer f;
    public final bl g;
    public final gv h;
    public final hr i;
    public final ex j;
    public final fy k;
    public final ca l;
    public final hy m;

    public fp(bl blVar, gv gvVar, hr hrVar, ex exVar, fy fyVar, ca caVar, hy hyVar) {
        this.g = blVar;
        this.h = gvVar;
        this.i = hrVar;
        this.j = exVar;
        this.k = fyVar;
        this.l = caVar;
        this.m = hyVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Pair pair, long j) throws JSONException, NumberFormatException {
        if (pair == null) {
            return;
        }
        float f = j / 1000.0f;
        try {
            float f2 = Float.parseFloat(String.format(Locale.ENGLISH, "%.3f", Float.valueOf(Util.getCurrentUxcamTime(f157n))));
            JSONObject jSONObject = ((JSONArray) pair.first).getJSONObject(0);
            String str = (String) pair.second;
            if (jSONObject.toString().contains("com.uxcam.internals")) {
                HashMap map = new HashMap();
                map.put("duration", String.valueOf(f));
                map.put("stacktrace", jSONObject.toString());
                ht.a("ANR", map);
            } else {
                int length = this.h.k().length();
                HashMap map2 = new HashMap();
                map2.put("duration", Float.valueOf(f));
                map2.put(KeyConstant.KEY_SCREEN, this.h.g());
                map2.put("topOfStack", str);
                map2.put("timeStamp", Long.valueOf(System.currentTimeMillis()));
                map2.put("anrNumber", Integer.valueOf(length));
                this.l.a("ANR", f2, map2);
                try {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("duration", f);
                    jSONObject2.put("stacktrace", jSONObject);
                    jSONObject2.put(KeyConstant.KEY_SCREEN, this.h.g());
                    jSONObject2.put("topOfStack", str);
                    jSONObject2.put("timeStamp", System.currentTimeMillis());
                    jSONObject2.put("anrNumber", length);
                    this.h.a(jSONObject2);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public final void b() {
        try {
            gk.aa aaVarA = gk.a("ANRTicker");
            Arrays.toString(ga.x);
            aaVarA.getClass();
            int[] iArr = ga.x;
            aa aaVar = new aa(iArr[0], iArr[1]);
            this.d = aaVar;
            aaVar.d = new aa.ac() { // from class: com.uxcam.internals.fp$$ExternalSyntheticLambda1
                @Override // com.uxcam.internals.aa.ac
                public final void a(Pair pair, long j) throws JSONException, NumberFormatException {
                    this.f$0.a(pair, j);
                }
            };
            aaVar.start();
        } catch (Exception e) {
            fj fjVarB = new fj().b("ServiceHandler::registerANRListener()");
            fjVarB.a("reason", e.getMessage());
            fjVarB.a(2);
        }
    }

    public final void c() {
        if (this.b) {
            this.f158a = true;
        }
        try {
            this.h.h();
            this.h.d();
            this.h.f();
            f157n = SystemClock.elapsedRealtime();
            a();
            this.c = Util.getCurrentApplicationContext();
            gk.aa aaVarA = gk.a("UXCam");
            int i = ga.f167a;
            aaVarA.getClass();
            String textFileName = FilePath.getTextFileName(Boolean.valueOf(ga.C));
            this.g.getClass();
            bl.a(textFileName);
            Util.createSessionRootDirectory(ga.b, Boolean.TRUE);
            String simpleName = Util.getCurrentContext().getClass().getSimpleName();
            String strA = this.h.a();
            if (!this.i.a().c) {
                if (strA != null && !strA.isEmpty()) {
                    this.h.a((String) null);
                } else {
                    strA = ((String) Objects.requireNonNull(this.j.e())).isEmpty() ? "unknown" : this.j.e();
                }
                this.k.a(this.c, strA);
            } else {
                this.k.b(this.c, simpleName);
            }
            gk.a(Personalization.ANALYTICS_ORIGIN_PERSONALIZATION).getClass();
            gk.a("UXCam").a("UXCam 3.6.13[580] : Application key is verified, UXCam has started capturing data as per configuration from UXCam settings page.", new Object[0]);
            if (ga.B) {
                this.f = new Timer();
                this.f.schedule(new fo(this), 0L, 1000L);
            }
            if (ga.f) {
                if (ff.g && ff.h != null) {
                    dd<ew> ddVar = ff.f;
                    if (ddVar != null) {
                        ddVar.clear();
                    }
                    new bt(ff.h);
                    bt.c = false;
                    ScreenshotModule.getInstance().getScreenshotStateHolder().resetImageCount();
                    ff.g = false;
                    try {
                        ff.h.a();
                    } catch (IOException unused) {
                        gk.a(Personalization.ANALYTICS_ORIGIN_PERSONALIZATION).getClass();
                    }
                    ff.k = null;
                    ff.h = null;
                    dd<ew> ddVar2 = ff.f;
                    if (ddVar2 != null) {
                        ddVar2.clear();
                        ff.f = null;
                    }
                    ScreenshotModule.getInstance().getScreenshotStateHolder().resetImageCount();
                }
                ff.g = false;
                gk.a("UXCam").getClass();
                ff ffVarA = ff.a();
                if (ffVarA != null) {
                    bt.c = true;
                    if (!ff.m && !ff.e) {
                        ff.e = true;
                        ff.d.schedule(new fh(ffVarA, ScreenshotModule.getInstance().getScreenshotHelper()), 0L, ff.l);
                    }
                } else {
                    HashMap map = new HashMap();
                    String strReplace = "[ #event# ]".replace("#event#", "Exception");
                    map.put("site_of_error", "ServiceHandler::startUxcamService()");
                    map.put("reason", "ScreenVideoHandler is null, not starting video capture.");
                    ht.a(strReplace, (Map<String, String>) map);
                }
            }
            JSONObject batteryLevel = Util.getBatteryLevel(this.c, f157n);
            if (batteryLevel != null) {
                ga.G = batteryLevel;
            }
            if (!Debug.isDebuggerConnected() && !Debug.waitingForDebugger()) {
                int[] iArr = ga.x;
                if (iArr[0] > 0 && iArr[1] > 0 && this.d == null) {
                    b();
                }
            }
            if (ga.K) {
                this.e = new fr(new File(FilePath.getSessionRootUrl(ga.b, Boolean.TRUE)));
            }
            Intent intent = new Intent(this.c, (Class<?>) HttpPostService.class);
            intent.putExtra("arg_which_service", "stop_foreground");
            this.c.startService(intent);
        } catch (Exception unused2) {
            gk.a(Personalization.ANALYTICS_ORIGIN_PERSONALIZATION).getClass();
        }
    }

    public final void a(final String str) throws InterruptedException {
        this.b = true;
        try {
            final boolean z = !str.isEmpty();
            bh.f97a = false;
            Timer timer = this.f;
            if (timer != null) {
                timer.cancel();
                this.f = null;
            }
            aa aaVar = this.d;
            if (aaVar != null) {
                aaVar.j = false;
            }
            this.d = null;
            ScreenshotStateHolder screenshotStateHolder = ScreenshotModule.getInstance().getScreenshotStateHolder();
            screenshotStateHolder.setWebView(null);
            screenshotStateHolder.setPreviousFrameOccluded(false);
            if (com.uxcam.aa.h) {
                Context context = this.c;
                SharedPreferences sharedPreferences = context != null ? context.getSharedPreferences("UXCamPreferences", 0) : null;
                String str2 = "override_mobile_data_data_only_setting_" + ga.b;
                if (sharedPreferences != null) {
                    sharedPreferences.edit().putBoolean(str2, true).apply();
                }
                com.uxcam.aa.h = false;
            }
            gk.a(Personalization.ANALYTICS_ORIGIN_PERSONALIZATION).getClass();
            if (ga.f) {
                Context context2 = this.c;
                SharedPreferences sharedPreferences2 = context2 != null ? context2.getSharedPreferences("UXCamPreferences", 0) : null;
                int i = (sharedPreferences2 == null ? 0 : sharedPreferences2.getInt("recorded_video_count", 0)) + 1;
                if (sharedPreferences2 != null) {
                    sharedPreferences2.edit().putInt("recorded_video_count", i).apply();
                }
                final CountDownLatch countDownLatch = new CountDownLatch(1);
                ff ffVarA = ff.a();
                if (ffVarA != null) {
                    ffVarA.c = new ff.ab() { // from class: com.uxcam.internals.fp$$ExternalSyntheticLambda0
                        @Override // com.uxcam.internals.ff.ab
                        public final void a(File file) {
                            this.f$0.a(countDownLatch, str, z, file);
                        }
                    };
                    ff.i = false;
                    ff.g = true;
                    if (!ff.m) {
                        ffVarA.a(ff.h);
                    }
                    try {
                        countDownLatch.await(10L, TimeUnit.SECONDS);
                    } catch (InterruptedException unused) {
                        gk.a(Personalization.ANALYTICS_ORIGIN_PERSONALIZATION).getClass();
                    }
                    if (!str.isEmpty()) {
                        a(str, z, null);
                    }
                } else {
                    HashMap map = new HashMap();
                    String strReplace = "[ #event# ]".replace("#event#", "Exception");
                    map.put("site_of_error", "ServiceHandler::stopUxcamService()");
                    map.put("reason", "ScreenVideoHandler is null, cannot stop video and upload.");
                    ht.a(strReplace, (Map<String, String>) map);
                }
            } else {
                a(str, z, null);
            }
            ga.A = false;
            gk.a(Personalization.ANALYTICS_ORIGIN_PERSONALIZATION).getClass();
        } catch (Exception unused2) {
            gk.a(Personalization.ANALYTICS_ORIGIN_PERSONALIZATION).getClass();
        }
        this.b = false;
        if (this.f158a) {
            this.f158a = false;
            c();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(CountDownLatch countDownLatch, String str, boolean z, File file) {
        gk.a(Personalization.ANALYTICS_ORIGIN_PERSONALIZATION).getClass();
        countDownLatch.countDown();
        if (str.isEmpty()) {
            a(str, z, file);
        }
    }

    public final void a(String str, boolean z, File file) {
        try {
            File fileA = this.g.a(str, this.e, FilePath.getSessionRootUrl(ga.b, Boolean.TRUE));
            if (this.e == null) {
                if (fileA == null || z) {
                    return;
                }
                new ag().b(this.c, fileA);
                return;
            }
            if (file != null && file.exists()) {
                this.e.a(file);
            }
            new ag().b(this.c, this.e.a());
        } catch (Exception e) {
            fj fjVarB = new fj().b("ServiceHandler::endAndUploadDataFile()");
            fjVarB.a("reason", e.getMessage());
            fjVarB.a(2);
        }
    }

    public final void a() {
        if (!this.i.a().e) {
            HashMap map = new HashMap();
            map.put("where", "ServiceHandler::initializeExceptionHandler");
            map.put("reason", "initializeExceptionHandler() must be called before startWithKey()");
            ht.c("[ CRASH LOGGinG ] Disabled", map);
            return;
        }
        Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        if (Thread.getDefaultUncaughtExceptionHandler() instanceof hv) {
            return;
        }
        Thread.setDefaultUncaughtExceptionHandler(new hv(defaultUncaughtExceptionHandler));
    }
}
