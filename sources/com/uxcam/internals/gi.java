package com.uxcam.internals;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import android.os.Handler;
import android.os.Message;
import android.view.Surface;
import androidx.media3.common.MimeTypes;
import com.uxcam.screenaction.di.ScreenActionModule;
import com.uxcam.screenaction.tracker.ScreenActionTracker;
import com.uxcam.screenaction.utils.Util;
import com.uxcam.screenshot.di.ScreenshotModule;
import com.uxcam.screenshot.helper.OnScreenshotTakenCallback;
import com.uxcam.screenshot.helper.ScreenshotHelper;
import com.uxcam.screenshot.state.ScreenshotStateHolder;
import com.uxcam.screenshot.utils.ScreenShotBitmapUtil;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes6.dex */
public final class gi {
    public static int k = 1;

    /* renamed from: a, reason: collision with root package name */
    public String f173a;
    public ab b;
    public final ac c;
    public final ArrayList<bv> d = new ArrayList<>();
    public MediaCodec e;
    public Surface f;
    public MediaMuxer g;
    public int h;
    public boolean i;
    public MediaCodec.BufferInfo j;

    public static class aa extends Handler {

        /* renamed from: a, reason: collision with root package name */
        public final gi f174a;

        public aa(gi giVar) {
            this.f174a = giVar;
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            if (message.what == 100) {
                int i = message.arg1;
                if (i == 101) {
                    Iterator<bv> it = this.f174a.d.iterator();
                    while (it.hasNext()) {
                        it.next().a();
                    }
                } else if (i == 102) {
                    Iterator<bv> it2 = this.f174a.d.iterator();
                    while (it2.hasNext()) {
                        it2.next().b();
                    }
                }
            }
        }
    }

    public interface ab {
    }

    public class ac extends Thread {
        public ac() {
        }

        public final void a(final long j) {
            if (!ff.i) {
                a(j, null);
                return;
            }
            ScreenshotHelper screenshotHelper = ScreenshotModule.getInstance().getScreenshotHelper();
            ScreenshotStateHolder screenshotStateHolder = ScreenshotModule.getInstance().getScreenshotStateHolder();
            int i = ((Activity) Util.getCurrentContext()).getResources().getConfiguration().orientation;
            if (screenshotStateHolder.getD() != i && !screenshotStateHolder.getE()) {
                screenshotStateHolder.setOrientation(i);
                if (bi.D == null) {
                    bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
                }
                bi biVar = bi.D;
                Intrinsics.checkNotNull(biVar);
                gs gsVarH = biVar.h();
                if (bi.D == null) {
                    bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
                }
                bi biVar2 = bi.D;
                Intrinsics.checkNotNull(biVar2);
                hy hyVarM = biVar2.m();
                if (gsVarH != null && hyVarM != null) {
                    hyVarM.a(10, 0.0f, 0.0f);
                }
                screenshotStateHolder.setLastVisibleDecorViewHeight(0);
                screenshotStateHolder.setKeyboardHeight(-1);
            }
            if (ga.B) {
                new ScreenActionTracker(com.uxcam.aa.i, ScreenActionModule.getInstance().getScreenActionViewsRepository()).loopLayout();
            }
            if (bi.D == null) {
                bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
            }
            bi biVar3 = bi.D;
            Intrinsics.checkNotNull(biVar3);
            eq eqVarC = biVar3.c();
            Activity activity = (Activity) Util.getCurrentContext();
            if (bi.D == null) {
                bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
            }
            bi biVar4 = bi.D;
            Intrinsics.checkNotNull(biVar4);
            ArrayList arrayListA = ((er) eqVarC).a(activity, ((fv) biVar4.f()).k, ga.p);
            if (bi.D == null) {
                bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
            }
            bi biVar5 = bi.D;
            Intrinsics.checkNotNull(biVar5);
            String strE = ((fa) biVar5.d()).d.e();
            if (bi.D == null) {
                bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
            }
            bi biVar6 = bi.D;
            Intrinsics.checkNotNull(biVar6);
            screenshotHelper.takeScreenshotAndEncode(strE, Boolean.valueOf(((fv) biVar6.f()).j), Integer.valueOf(ga.p), arrayListA, (Activity) Util.getCurrentContext(), new OnScreenshotTakenCallback() { // from class: com.uxcam.internals.gi$ac$$ExternalSyntheticLambda0
                @Override // com.uxcam.screenshot.helper.OnScreenshotTakenCallback
                public final void onScreenshotTaken(Bitmap bitmap) {
                    this.f$0.a(j, bitmap);
                }
            });
        }

        public final void b() {
            gi.this.j = new MediaCodec.BufferInfo();
            ((ge) gi.this.b).getClass();
            int divisibleBySixteenInt = Util.getDivisibleBySixteenInt(ScreenShotBitmapUtil.getInstance().getBitmapWidth());
            ((ge) gi.this.b).getClass();
            MediaFormat mediaFormatCreateVideoFormat = MediaFormat.createVideoFormat(MimeTypes.VIDEO_H264, divisibleBySixteenInt, Util.getDivisibleBySixteenInt(ScreenShotBitmapUtil.getInstance().getBitmapHeight()));
            mediaFormatCreateVideoFormat.setInteger("color-format", 2130708361);
            gi.this.getClass();
            mediaFormatCreateVideoFormat.setInteger("bitrate", 150000);
            mediaFormatCreateVideoFormat.setInteger("frame-rate", gi.k);
            mediaFormatCreateVideoFormat.setInteger("i-frame-interval", 100);
            ((ge) gi.this.b).getClass();
            mediaFormatCreateVideoFormat.setInteger("stride", Util.getDivisibleBySixteenInt(ScreenShotBitmapUtil.getInstance().getBitmapWidth()));
            ((ge) gi.this.b).getClass();
            mediaFormatCreateVideoFormat.setInteger("slice-height", Util.getDivisibleBySixteenInt(ScreenShotBitmapUtil.getInstance().getBitmapHeight()));
            try {
                gi.this.e = MediaCodec.createEncoderByType(MimeTypes.VIDEO_H264);
            } catch (IOException e) {
                fj fjVarB = new fj().b("EncoderThread::prepareEncoder()");
                fjVarB.a("reason", e.getMessage());
                fjVarB.a(2);
            }
            gi.this.e.configure(mediaFormatCreateVideoFormat, (Surface) null, (MediaCrypto) null, 1);
            gi giVar = gi.this;
            giVar.f = giVar.e.createInputSurface();
            gi.this.e.start();
            try {
                gi.this.g = new MediaMuxer(gi.this.f173a, 0);
                gi giVar2 = gi.this;
                giVar2.h = -1;
                giVar2.i = false;
            } catch (IOException e2) {
                fj fjVarB2 = new fj().b("EncoderThread::prepareEncoder()");
                fjVarB2.a("reason", e2.getMessage());
                fjVarB2.a("invokes_next", "throws RuntimeException() :: application has crashed!! ");
                fjVarB2.a(2);
                throw new RuntimeException("MediaMuxer creation failed", e2);
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() {
            char c;
            if (gi.this.b == null) {
                throw new NullPointerException("Need to set an encoder source on the surfaceEncoder");
            }
            try {
                try {
                    b();
                    int i = 0;
                    loop0: while (!ff.g) {
                        a(false);
                        a((i * 1000) / gi.k);
                        i++;
                        if (i == 1) {
                            if (bi.D == null) {
                                bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
                            }
                            bi biVar = bi.D;
                            Intrinsics.checkNotNull(biVar);
                            ((gw) biVar.i()).f = Util.getCurrentUxcamTime(fp.f157n);
                            Util.getCurrentUxcamTime(fp.f157n);
                        }
                        for (int i2 = 0; i2 < 100; i2++) {
                            Thread.sleep(10 / gi.k);
                            if (ff.g) {
                                break loop0;
                            }
                        }
                    }
                    a((i * 1000) / gi.k);
                    a(true);
                    c();
                    c = 'e';
                } catch (Exception e) {
                    gk.a("SurfaceEncoder").getClass();
                    fj fjVarB = new fj().b("EncoderThread::run()");
                    fjVarB.a("reason", e.getMessage());
                    fjVarB.a(2);
                    c();
                    c = 'f';
                }
                if (c == 'e') {
                    Iterator<bv> it = gi.this.d.iterator();
                    while (it.hasNext()) {
                        it.next().a();
                    }
                } else if (c == 'f') {
                    Iterator<bv> it2 = gi.this.d.iterator();
                    while (it2.hasNext()) {
                        it2.next().b();
                    }
                }
            } catch (Throwable th) {
                c();
                throw th;
            }
        }

        public /* synthetic */ ac(gi giVar, int i) {
            this();
        }

        public final void c() {
            MediaCodec mediaCodec = gi.this.e;
            if (mediaCodec != null) {
                try {
                    mediaCodec.stop();
                    gi.this.e.release();
                    gi.this.e = null;
                } catch (Exception e) {
                    gk.a("SurfaceEncoder").getClass();
                    fj fjVarB = new fj().b("EncoderThread::releaseEncoder()");
                    fjVarB.a("reason", e.getMessage());
                    fjVarB.a("crash_cause", "for mEncoder ...").a(2);
                }
            }
            Surface surface = gi.this.f;
            if (surface != null) {
                try {
                    surface.release();
                    gi.this.f = null;
                } catch (Exception e2) {
                    gk.a("SurfaceEncoder").getClass();
                    fj fjVarB2 = new fj().b("EncoderThread::releaseEncoder()");
                    fjVarB2.a("reason", e2.getMessage());
                    fjVarB2.a("crash_cause", "for mSurface ...").a(2);
                }
            }
            MediaMuxer mediaMuxer = gi.this.g;
            if (mediaMuxer != null) {
                try {
                    mediaMuxer.stop();
                    gi.this.g.release();
                    gi.this.g = null;
                } catch (Exception e3) {
                    gk.a("SurfaceEncoder").getClass();
                    fj fjVarB3 = new fj().b("EncoderThread::releaseEncoder()");
                    fjVarB3.a("reason", e3.getMessage());
                    fjVarB3.a("crash_cause", "for mMuxer ...").a(2);
                }
            }
        }

        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public final void a(long j, Bitmap bitmap) {
            Canvas canvasA;
            try {
                if (gi.this.f == null || (canvasA = a()) == null) {
                    return;
                }
                ab abVar = gi.this.b;
                int i = 1000 / gi.k;
                ((ge) abVar).a(canvasA, bitmap);
                gi.this.f.unlockCanvasAndPost(canvasA);
            } catch (IllegalArgumentException | NullPointerException e) {
                fj fjVarB = new fj().b("SurfaceEncoder::renderBitmap(long)");
                fjVarB.a("reason", e.getMessage());
                fjVarB.a("crash_cause", "Exception to be raised at Surface").a(2);
            }
        }

        public final Canvas a() {
            try {
                return gi.this.f.lockCanvas(null);
            } catch (Surface.OutOfResourcesException e) {
                gk.a("SurfaceEncoder").getClass();
                fj fjVarB = new fj().b("EncoderThread::renderFromSource()");
                fjVarB.a("reason", e.getMessage());
                fjVarB.a("crash_cause", "There are no more resources to continue ...").a(2);
                return null;
            } catch (IllegalArgumentException e2) {
                fj fjVarB2 = new fj().b("EncoderThread::renderFromSource()");
                fjVarB2.a("reason", e2.getMessage());
                fj fjVarA = fjVarB2.a("crash_cause", "IllegalArgumentException to be raised at lockCanvas");
                fjVarA.a("invokes_next", "throws RuntimeException() :: application has crashed!! ");
                fjVarA.a(2);
                return null;
            } catch (Exception e3) {
                e3.printStackTrace();
                return null;
            }
        }

        public final void a(boolean z) {
            if (z) {
                gi.this.e.signalEndOfInputStream();
            }
            ByteBuffer[] outputBuffers = gi.this.e.getOutputBuffers();
            while (true) {
                gi giVar = gi.this;
                int iDequeueOutputBuffer = giVar.e.dequeueOutputBuffer(giVar.j, 10000L);
                if (iDequeueOutputBuffer == -1) {
                    if (!z) {
                        return;
                    }
                } else if (iDequeueOutputBuffer == -3) {
                    outputBuffers = gi.this.e.getOutputBuffers();
                } else if (iDequeueOutputBuffer == -2) {
                    gi giVar2 = gi.this;
                    if (!giVar2.i) {
                        MediaFormat outputFormat = giVar2.e.getOutputFormat();
                        Objects.toString(outputFormat);
                        gi giVar3 = gi.this;
                        giVar3.h = giVar3.g.addTrack(outputFormat);
                        gi.this.g.start();
                        gi.this.i = true;
                    } else {
                        throw new RuntimeException("format changed twice");
                    }
                } else if (iDequeueOutputBuffer >= 0) {
                    ByteBuffer byteBuffer = outputBuffers[iDequeueOutputBuffer];
                    if (byteBuffer != null) {
                        gi giVar4 = gi.this;
                        MediaCodec.BufferInfo bufferInfo = giVar4.j;
                        if ((bufferInfo.flags & 2) != 0) {
                            bufferInfo.size = 0;
                        }
                        if (bufferInfo.size != 0) {
                            if (giVar4.i) {
                                byteBuffer.position(bufferInfo.offset);
                                MediaCodec.BufferInfo bufferInfo2 = gi.this.j;
                                byteBuffer.limit(bufferInfo2.offset + bufferInfo2.size);
                                gi giVar5 = gi.this;
                                giVar5.g.writeSampleData(giVar5.h, byteBuffer, giVar5.j);
                            } else {
                                throw new RuntimeException("muxer hasn't started");
                            }
                        }
                        gi.this.e.releaseOutputBuffer(iDequeueOutputBuffer, false);
                        if ((gi.this.j.flags & 4) != 0) {
                            return;
                        }
                    } else {
                        throw new RuntimeException("encoderOutputBuffer " + iDequeueOutputBuffer + " was null");
                    }
                } else {
                    continue;
                }
            }
        }
    }

    public gi() {
        new aa(this);
        ac acVar = new ac(this, 0);
        this.c = acVar;
        acVar.setName("uxSurfaceEncode");
    }
}
