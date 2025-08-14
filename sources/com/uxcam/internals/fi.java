package com.uxcam.internals;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import androidx.work.Data;
import com.uxcam.screenaction.di.ScreenActionModule;
import com.uxcam.screenaction.tracker.ScreenActionTracker;
import com.uxcam.screenaction.utils.Util;
import com.uxcam.screenshot.di.ScreenshotModule;
import com.uxcam.screenshot.helper.OnScreenshotTakenCallback;
import com.uxcam.screenshot.helper.ScreenshotHelper;
import com.uxcam.screenshot.state.ScreenshotStateHolder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes6.dex */
public final class fi {

    /* renamed from: a, reason: collision with root package name */
    public final int[] f151a = new int[1];
    public final int b;

    public fi() {
        int iA = co.a("uniform mat4 uScreen;\nattribute vec2 aPosition;\nattribute vec2 aTexPos;\nvarying vec2 vTexPos;\nvoid main() {\n  vTexPos = aTexPos;\n  gl_Position = uScreen * vec4(aPosition.xy, 0.0, 1.0);\n}", 35633);
        int iA2 = co.a("precision mediump float;\nuniform sampler2D uTexture;\nvarying vec2 vTexPos;\nvoid main(void)\n{\n  gl_FragColor = texture2D(uTexture, vTexPos);\n}", 35632);
        int iGlCreateProgram = GLES20.glCreateProgram();
        this.b = iGlCreateProgram;
        GLES20.glAttachShader(iGlCreateProgram, iA);
        GLES20.glAttachShader(iGlCreateProgram, iA2);
        GLES20.glLinkProgram(iGlCreateProgram);
    }

    public static void a(Bitmap bitmap) {
        if (bi.D == null) {
            bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
        }
        bi biVar = bi.D;
        Intrinsics.checkNotNull(biVar);
        biVar.f98a.getBitmapSource().add(bitmap.copy(bitmap.getConfig(), false));
        if (bi.D == null) {
            bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
        }
        bi biVar2 = bi.D;
        Intrinsics.checkNotNull(biVar2);
        biVar2.f98a.getBitmapSource().count();
    }

    public static void b(Bitmap bitmap) {
        if (bi.D == null) {
            bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
        }
        bi biVar = bi.D;
        Intrinsics.checkNotNull(biVar);
        Bitmap bitmapRemoveFromQueue = biVar.f98a.getBitmapSource().removeFromQueue();
        if (bitmapRemoveFromQueue == null) {
            if (bi.D == null) {
                bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
            }
            bi biVar2 = bi.D;
            Intrinsics.checkNotNull(biVar2);
            bitmapRemoveFromQueue = biVar2.f98a.getBitmapSource().getLastFrameCache();
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmapRemoveFromQueue.getWidth(), Util.getDivisibleBySixteenInt(bitmapRemoveFromQueue.getHeight()), Bitmap.Config.RGB_565);
        new Canvas(bitmapCreateBitmap).drawBitmap(bitmapRemoveFromQueue, 0.0f, 0.0f, (Paint) null);
        GLUtils.texImage2D(3553, 0, bitmapCreateBitmap, 0);
    }

    public final void a() {
        GLES20.glUseProgram(this.b);
        GLES20.glGenTextures(1, this.f151a, 0);
        int i = this.f151a[0];
        if (i != 0) {
            GLES20.glBindTexture(3553, i);
            GLES20.glTexParameteri(3553, 10241, 9728);
            GLES20.glTexParameteri(3553, Data.MAX_DATA_BYTES, 9728);
            try {
                Timer timer = ff.d;
                ScreenshotModule screenshotModule = ScreenshotModule.getInstance();
                ScreenshotStateHolder screenshotStateHolder = screenshotModule.getScreenshotStateHolder();
                ScreenshotHelper screenshotHelper = screenshotModule.getScreenshotHelper();
                if (screenshotStateHolder.isWaitingToStop()) {
                    if (bi.D == null) {
                        bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
                    }
                    bi biVar = bi.D;
                    Intrinsics.checkNotNull(biVar);
                    Bitmap lastFrameCache = biVar.f98a.getBitmapSource().getLastFrameCache();
                    if (lastFrameCache != null && screenshotStateHolder.isImprovedScreenCaptureInUse()) {
                        a(lastFrameCache);
                    }
                }
                b();
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
                fu fuVarF = biVar3.f();
                ArrayList arrayListA = ((er) eqVarC).a(activity, ((fv) fuVarF).k, ga.p);
                if (bi.D == null) {
                    bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
                }
                bi biVar4 = bi.D;
                Intrinsics.checkNotNull(biVar4);
                screenshotHelper.takeScreenshotAndEncode(((fa) biVar4.d()).d.e(), Boolean.valueOf(((fv) fuVarF).j), Integer.valueOf(ga.p), arrayListA, (Activity) Util.getCurrentContext(), new OnScreenshotTakenCallback() { // from class: com.uxcam.internals.fi$$ExternalSyntheticLambda0
                    @Override // com.uxcam.screenshot.helper.OnScreenshotTakenCallback
                    public final void onScreenshotTaken(Bitmap bitmap) {
                        fi.b(bitmap);
                    }
                });
                return;
            } catch (Exception e) {
                HashMap map = new HashMap();
                String strReplace = "[ #event# ]".replace("#event#", "Exception");
                map.put("site_of_error", "ScreenshotTexture::onSurfaceCreated()");
                map.put("reason", e.getMessage());
                ht.a(strReplace, (Map<String, String>) map);
                return;
            }
        }
        throw new RuntimeException("Error loading texture");
    }

    public static void b() {
        ScreenshotStateHolder screenshotStateHolder = ScreenshotModule.getInstance().getScreenshotStateHolder();
        int i = ((Activity) Util.getCurrentContext()).getResources().getConfiguration().orientation;
        if (screenshotStateHolder.getOrientation() == i || screenshotStateHolder.isWaitingToStop()) {
            return;
        }
        screenshotStateHolder.setOrientation(i);
        if (bi.D == null) {
            bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
        }
        bi biVar = bi.D;
        Intrinsics.checkNotNull(biVar);
        biVar.m().a(10, 0.0f, 0.0f);
        screenshotStateHolder.setLastVisibleDecorViewHeight(0);
        screenshotStateHolder.setKeyboardHeight(-1);
    }
}
