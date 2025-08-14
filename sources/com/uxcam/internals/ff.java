package com.uxcam.internals;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import com.uxcam.screenaction.di.ScreenActionModule;
import com.uxcam.screenaction.tracker.ScreenActionTracker;
import com.uxcam.screenaction.utils.FilePath;
import com.uxcam.screenaction.utils.Util;
import com.uxcam.screenshot.di.ScreenshotModule;
import com.uxcam.screenshot.helper.OnScreenshotTakenCallback;
import com.uxcam.screenshot.helper.ScreenshotHelper;
import com.uxcam.screenshot.state.ScreenshotStateHolder;
import com.uxcam.screenshot.utils.ScreenShotBitmapUtil;
import com.uxcam.service.HttpPostService;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes6.dex */
public final class ff {
    public static dd<ew> f;
    public static boolean g;
    public static fn h;
    public static ff k;

    /* renamed from: a, reason: collision with root package name */
    public final Handler f147a = new Handler(Looper.getMainLooper());
    public final File b;
    public ab c;
    public static final Timer d = new Timer();
    public static boolean e = false;
    public static boolean i = false;
    public static long j = 0;
    public static int l = (int) TimeUnit.SECONDS.toMillis(1);
    public static boolean m = false;

    public class aa implements bv {
        public aa() {
        }

        @Override // com.uxcam.internals.bv
        public final void a() {
            Timer timer = ff.d;
            gk.a("ff").getClass();
            ff ffVar = ff.this;
            ab abVar = ffVar.c;
            if (abVar != null) {
                abVar.a(ffVar.b);
                ffVar.c = null;
            }
            ff ffVar2 = ff.this;
            ffVar2.getClass();
            ScreenshotModule.getInstance().getScreenshotStateHolder().resetImageCount();
            ff.g = false;
            ff.k = null;
            ff.h = null;
            dd<ew> ddVar = ff.f;
            if (ddVar != null) {
                ddVar.clear();
                ff.f = null;
            }
            ScreenshotModule.getInstance().getScreenshotStateHolder().resetImageCount();
            if (ga.K) {
                return;
            }
            ffVar2.g();
        }

        @Override // com.uxcam.internals.bv
        public final void b() {
            Timer timer = ff.d;
            gk.a("ff").getClass();
            ff.this.getClass();
            ff.e();
        }
    }

    public interface ab {
        void a(File file);
    }

    public ff() {
        File fileB = b();
        int i2 = ga.f167a;
        this.b = new File(fileB, FilePath.getScreenFileName(Boolean.TRUE));
        m = f();
        c();
    }

    public static File b() {
        File file = new File(FilePath.getScreenVideoImageUrl(ga.b, Boolean.TRUE));
        if (!file.exists()) {
            file.mkdir();
        }
        return file;
    }

    public static boolean f() {
        return true;
    }

    public final void d() {
        ScreenShotBitmapUtil.getInstance().calculateBitmapMetricsForNewSession(ga.p);
        l = ga.g;
        gk.a("ff").getClass();
        i = true;
        final cn cnVar = new cn();
        File file = new File(FilePath.getScreenVideoImageUrl(ga.b, Boolean.TRUE));
        if (!file.exists()) {
            file.mkdir();
        }
        cnVar.b = this.b.getAbsolutePath();
        cnVar.f110a.add(new aa());
        new Thread(new Runnable() { // from class: com.uxcam.internals.ff$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                cnVar.a();
            }
        }).start();
    }

    public final void g() {
        try {
            ht.a("encodingComplete", (HashMap) null);
            if (ga.C && this.b.exists()) {
                File file = this.b;
                ih ihVar = new ih(file);
                ihVar.a();
                Util.deleteRecursive(file);
                Util.deleteRecursive(ihVar.b);
                HashMap map = new HashMap();
                map.put("site_of_error", "ScreenVideoHandler::startUploadService()");
                ht.b("[ #event# ]".replace("#event#", "Encoding Complete"), map);
            }
            Intent intent = new Intent(Util.getCurrentApplicationContext(), (Class<?>) HttpPostService.class);
            intent.putExtra("arg_which_service", "screen_upload");
            Util.getCurrentApplicationContext().startService(intent);
        } catch (Exception e2) {
            fj fjVarB = new fj().b("ScreenVideoHandler::startUploadService()");
            fjVarB.a("reason", e2.getMessage());
            fjVarB.a(2);
        }
    }

    public static ff a() {
        if (k == null) {
            try {
                final CountDownLatch countDownLatch = new CountDownLatch(1);
                new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.uxcam.internals.ff$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        ff.a(countDownLatch);
                    }
                });
                countDownLatch.await(500L, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
                HashMap map = new HashMap();
                String strReplace = "[ #event# ]".replace("#event#", "Exception");
                map.put("site_of_error", "ScreenVideoHandler::getInstance()");
                map.put("reason", e2.getMessage());
                ht.a(strReplace, (Map<String, String>) map);
            }
        }
        return k;
    }

    public final void c() {
        String str;
        if (!m) {
            e();
            str = "JCodec";
        } else if (Build.MODEL.contains("SM-G93")) {
            d();
            str = "GLMediaCodec";
        } else {
            ScreenShotBitmapUtil.getInstance().calculateBitmapMetricsForNewSession(ga.p);
            l = ga.g;
            gk.a("ff").getClass();
            i = true;
            gi giVar = new gi();
            File file = new File(FilePath.getScreenVideoImageUrl(ga.b, Boolean.TRUE));
            if (!file.exists()) {
                file.mkdir();
            }
            giVar.f173a = this.b.getAbsolutePath();
            giVar.b = new ge();
            giVar.d.add(new fg(this));
            giVar.c.start();
            str = "MediaCodec";
        }
        HashMap map = new HashMap();
        String strReplace = "[ #event# ]".replace("#event#", "Initialized Media Codec");
        map.put("site_of_error", "ScreenVideoHandler::takeScreenshotShotAndEncode()");
        map.put("codec_type", str);
        map.put("frame_time", "" + ga.g);
        ht.b(strReplace, map);
    }

    public static void e() {
        m = false;
        try {
            if (h == null) {
                String str = ga.b;
                Boolean bool = Boolean.TRUE;
                File file = new File(FilePath.getScreenVideoImageUrl(str, bool));
                if (!file.exists()) {
                    file.mkdir();
                }
                h = new fn(new File(file, FilePath.getScreenFileName(bool)));
            }
            gk.a("ff").getClass();
        } catch (IOException e2) {
            gk.c.getClass();
            fj fjVarB = new fj().b("ScreenVideoHandler::initializeJCodec()");
            fjVarB.a("reason", e2.getMessage());
            fjVarB.a(2);
        }
        f = new dd<>();
        ScreenShotBitmapUtil.getInstance().calculateBitmapMetricsForNewSession(ga.p);
        l = ga.g;
    }

    public static void a(CountDownLatch countDownLatch) {
        try {
            k = new ff();
            countDownLatch.countDown();
        } catch (Exception e2) {
            e2.printStackTrace();
            HashMap map = new HashMap();
            String strReplace = "[ #event# ]".replace("#event#", "Exception");
            map.put("site_of_error", "ScreenVideoHandler::getInstance()::run()");
            map.put("reason", e2.getMessage());
            ht.a(strReplace, (Map<String, String>) map);
        }
    }

    public final void a(fn fnVar) {
        dd<ew> ddVar = f;
        if (ddVar != null && ddVar.size() == 0 && g && ga.f) {
            ScreenshotModule.getInstance().getScreenshotStateHolder().resetImageCount();
            g = false;
            try {
                fnVar.a();
                gk.a("ff").getClass();
                ab abVar = this.c;
                if (abVar != null) {
                    abVar.a(this.b);
                    this.c = null;
                }
            } catch (Exception e2) {
                ab abVar2 = this.c;
                if (abVar2 != null) {
                    abVar2.a(this.b);
                    this.c = null;
                }
                gk.a("ff").getClass();
                HashMap map = new HashMap();
                String strReplace = "[ #event# ]".replace("#event#", "Exception");
                map.put("site_of_error", "ScreenVideoHandler::finishEncodingAndSendHttp()");
                map.put("reason", e2.getMessage());
                ht.a(strReplace, (Map<String, String>) map);
            }
            k = null;
            h = null;
            dd<ew> ddVar2 = f;
            if (ddVar2 != null) {
                ddVar2.clear();
            }
            f = null;
            ScreenshotModule.getInstance().getScreenshotStateHolder().resetImageCount();
            g();
        }
    }

    public static void a(ScreenshotHelper screenshotHelper) {
        dd<ew> ddVar = f;
        if (ddVar != null) {
            final int size = ddVar.size();
            ScreenshotStateHolder screenshotStateHolder = ScreenshotModule.getInstance().getScreenshotStateHolder();
            try {
                int i2 = ((Activity) Util.getCurrentContext()).getResources().getConfiguration().orientation;
                if (screenshotStateHolder.getD() != i2 && !screenshotStateHolder.getE()) {
                    screenshotStateHolder.setOrientation(i2);
                    if (bi.D == null) {
                        bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
                    }
                    bi biVar = bi.D;
                    Intrinsics.checkNotNull(biVar);
                    biVar.m().a(10, 0.0f, 0.0f);
                    screenshotStateHolder.setLastVisibleDecorViewHeight(0);
                    screenshotStateHolder.setKeyboardHeight(-1);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            if (ga.B) {
                new ScreenActionTracker(com.uxcam.aa.i, ScreenActionModule.getInstance().getScreenActionViewsRepository()).loopLayout();
            }
            if (bi.D == null) {
                bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
            }
            bi biVar2 = bi.D;
            Intrinsics.checkNotNull(biVar2);
            eq eqVarC = biVar2.c();
            Activity activity = (Activity) Util.getCurrentContext();
            if (bi.D == null) {
                bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
            }
            bi biVar3 = bi.D;
            Intrinsics.checkNotNull(biVar3);
            fv fvVar = (fv) biVar3.f();
            ArrayList arrayListA = ((er) eqVarC).a(activity, fvVar.k, ga.p);
            if (bi.D == null) {
                bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
            }
            bi biVar4 = bi.D;
            Intrinsics.checkNotNull(biVar4);
            screenshotHelper.takeScreenshotAndEncode(((fa) biVar4.d()).d.e(), Boolean.valueOf(fvVar.j), Integer.valueOf(ga.p), arrayListA, (Activity) Util.getCurrentContext(), new OnScreenshotTakenCallback() { // from class: com.uxcam.internals.ff$$ExternalSyntheticLambda2
                @Override // com.uxcam.screenshot.helper.OnScreenshotTakenCallback
                public final void onScreenshotTaken(Bitmap bitmap) {
                    ff.a(size, bitmap);
                }
            });
        }
    }

    public static /* synthetic */ void a(int i2, Bitmap bitmap) {
        if (m) {
            return;
        }
        ScreenshotStateHolder screenshotStateHolder = ScreenshotModule.getInstance().getScreenshotStateHolder();
        dd<ew> ddVar = f;
        if (ddVar != null) {
            ddVar.a(new ew(bitmap, i2, screenshotStateHolder.getImageCount().intValue()));
        }
    }
}
