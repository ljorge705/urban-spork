package com.uxcam.internals;

import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLExt;
import android.opengl.EGLSurface;
import android.os.SystemClock;
import android.view.Surface;
import androidx.media3.common.MimeTypes;
import com.uxcam.screenaction.di.ScreenActionModule;
import com.uxcam.screenaction.utils.Util;
import com.uxcam.screenshot.di.ScreenshotModule;
import com.uxcam.screenshot.utils.ScreenShotBitmapUtil;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes6.dex */
public final class cn {
    public static int l = 1;
    public String b;
    public MediaCodec f;
    public aa g;
    public MediaMuxer h;
    public int i;
    public boolean j;
    public MediaCodec.BufferInfo k;

    /* renamed from: a, reason: collision with root package name */
    public final ArrayList<bv> f110a = new ArrayList<>();
    public int c = -1;
    public int d = -1;
    public int e = -1;

    public final void a(long j) {
        a(false);
        co coVar = new co(this.c, this.d);
        coVar.c();
        coVar.b();
        coVar.a();
        this.g.a((SystemClock.elapsedRealtime() - j) * 1000000);
        this.g.d();
    }

    public final void b() {
        this.k = new MediaCodec.BufferInfo();
        MediaFormat mediaFormatCreateVideoFormat = MediaFormat.createVideoFormat(MimeTypes.VIDEO_H264, this.c, this.d);
        mediaFormatCreateVideoFormat.setInteger("color-format", 2130708361);
        mediaFormatCreateVideoFormat.setInteger("bitrate", this.e);
        mediaFormatCreateVideoFormat.setInteger("frame-rate", l);
        mediaFormatCreateVideoFormat.setInteger("i-frame-interval", 10);
        try {
            this.f = MediaCodec.createEncoderByType(MimeTypes.VIDEO_H264);
        } catch (IOException e) {
            e.printStackTrace();
            fj fjVarB = new fj().b("GLMediaCodecEncoder::prepareEncoder()");
            fjVarB.a("reason", e.getMessage());
            fjVarB.a(2);
        }
        this.f.configure(mediaFormatCreateVideoFormat, (Surface) null, (MediaCrypto) null, 1);
        this.g = new aa(this.f.createInputSurface());
        this.f.start();
        try {
            this.h = new MediaMuxer(this.b, 0);
            this.i = -1;
            this.j = false;
        } catch (IOException e2) {
            fj fjVarB2 = new fj().b("GLMediaCodecEncoder::encodeVideoToMp4()");
            fjVarB2.a("reason", e2.getMessage());
            fj fjVarA = fjVarB2.a("crash_cause", "crashed when trying to init MediaMuxer");
            fjVarA.a("invokes_next", "RuntimeException :: app crashed at this point.");
            fjVarA.a(2);
            throw new RuntimeException("MediaMuxer creation failed", e2);
        }
    }

    public final void c() {
        MediaCodec mediaCodec = this.f;
        if (mediaCodec != null) {
            try {
                mediaCodec.stop();
                this.f.release();
                this.f = null;
            } catch (Exception e) {
                fj fjVarB = new fj().b("GLMediaCodecEncoder::releaseEncoder()");
                fjVarB.a("reason", e.getMessage());
                fjVarB.a("crash_interest", "for mEncoder").a(2);
            }
        }
        aa aaVar = this.g;
        if (aaVar != null) {
            try {
                aaVar.c();
                this.g = null;
            } catch (Exception e2) {
                fj fjVarB2 = new fj().b("GLMediaCodecEncoder::releaseEncoder()");
                fjVarB2.a("reason", e2.getMessage());
                fjVarB2.a("crash_interest", "for mInputSurface").a(2);
            }
        }
        MediaMuxer mediaMuxer = this.h;
        if (mediaMuxer != null) {
            try {
                mediaMuxer.stop();
                this.h.release();
                this.h = null;
            } catch (Exception e3) {
                fj fjVarB3 = new fj().b("GLMediaCodecEncoder::releaseEncoder()");
                fjVarB3.a("reason", e3.getMessage());
                fjVarB3.a("crash_interest", "for mMuxer").a(2);
            }
        }
    }

    public final void a() {
        this.c = Util.getDivisibleBySixteenInt(ScreenShotBitmapUtil.getInstance().getBitmapWidth());
        this.d = Util.getDivisibleBySixteenInt(ScreenShotBitmapUtil.getInstance().getBitmapHeight());
        this.e = 150000;
        try {
            try {
                b();
                this.g.b();
                long j = fp.f157n;
                loop0: while (!ff.g) {
                    a(j);
                    if (bi.D == null) {
                        bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
                    }
                    bi biVar = bi.D;
                    Intrinsics.checkNotNull(biVar);
                    gv gvVarI = biVar.i();
                    if (((gw) gvVarI).f <= 0.0f) {
                        ((gw) gvVarI).f = Util.getCurrentUxcamTime(fp.f157n);
                    }
                    for (int i = 0; i < 100; i++) {
                        Thread.sleep(10 / l);
                        if (ff.g) {
                            break loop0;
                        }
                    }
                }
                a(true);
                c();
                Iterator<bv> it = this.f110a.iterator();
                while (it.hasNext()) {
                    it.next().a();
                }
            } catch (Exception e) {
                fj fjVarB = new fj().b("GLMediaCodecEncoder::encodeVideoToMp4()");
                fjVarB.a("reason", e.getMessage());
                fjVarB.a(2);
                c();
                Iterator<bv> it2 = this.f110a.iterator();
                while (it2.hasNext()) {
                    it2.next().b();
                }
            }
        } catch (Throwable th) {
            c();
            throw th;
        }
    }

    public static class aa {

        /* renamed from: a, reason: collision with root package name */
        public EGLDisplay f111a = EGL14.EGL_NO_DISPLAY;
        public EGLContext b = EGL14.EGL_NO_CONTEXT;
        public EGLSurface c = EGL14.EGL_NO_SURFACE;
        public Surface d;

        public aa(Surface surface) {
            surface.getClass();
            this.d = surface;
            a();
        }

        public final void a() {
            EGLDisplay eGLDisplayEglGetDisplay = EGL14.eglGetDisplay(0);
            this.f111a = eGLDisplayEglGetDisplay;
            if (eGLDisplayEglGetDisplay == EGL14.EGL_NO_DISPLAY) {
                throw new RuntimeException("unable to get EGL14 display");
            }
            int[] iArr = new int[2];
            if (!EGL14.eglInitialize(eGLDisplayEglGetDisplay, iArr, 0, iArr, 1)) {
                throw new RuntimeException("unable to initialize EGL14");
            }
            EGLConfig[] eGLConfigArr = new EGLConfig[1];
            EGL14.eglChooseConfig(this.f111a, new int[]{12324, 8, 12323, 8, 12322, 8, 12321, 8, 12352, 4, 12610, 1, 12344}, 0, eGLConfigArr, 0, 1, new int[1], 0);
            a("eglCreateContext RGB888+recordable ES2");
            this.b = EGL14.eglCreateContext(this.f111a, eGLConfigArr[0], EGL14.EGL_NO_CONTEXT, new int[]{12440, 2, 12344}, 0);
            a("eglCreateContext");
            this.c = EGL14.eglCreateWindowSurface(this.f111a, eGLConfigArr[0], this.d, new int[]{12344}, 0);
            a("eglCreateWindowSurface");
        }

        public final void b() {
            EGLDisplay eGLDisplay = this.f111a;
            EGLSurface eGLSurface = this.c;
            EGL14.eglMakeCurrent(eGLDisplay, eGLSurface, eGLSurface, this.b);
            a("eglMakeCurrent");
        }

        public final void c() {
            EGLDisplay eGLDisplay = this.f111a;
            if (eGLDisplay != EGL14.EGL_NO_DISPLAY) {
                EGLSurface eGLSurface = EGL14.EGL_NO_SURFACE;
                EGL14.eglMakeCurrent(eGLDisplay, eGLSurface, eGLSurface, EGL14.EGL_NO_CONTEXT);
                EGL14.eglDestroySurface(this.f111a, this.c);
                EGL14.eglDestroyContext(this.f111a, this.b);
                EGL14.eglReleaseThread();
                EGL14.eglTerminate(this.f111a);
            }
            this.d.release();
            this.f111a = EGL14.EGL_NO_DISPLAY;
            this.b = EGL14.EGL_NO_CONTEXT;
            this.c = EGL14.EGL_NO_SURFACE;
            this.d = null;
        }

        public final void d() {
            EGL14.eglSwapBuffers(this.f111a, this.c);
            a("eglSwapBuffers");
        }

        public final void a(long j) {
            EGLExt.eglPresentationTimeANDROID(this.f111a, this.c, j);
            a("eglPresentationTimeANDROID");
        }

        public static void a(String str) {
            int iEglGetError = EGL14.eglGetError();
            if (iEglGetError != 12288) {
                throw new RuntimeException(str + ": EGL error: 0x" + Integer.toHexString(iEglGetError));
            }
        }
    }

    public final void a(boolean z) {
        if (z) {
            this.f.signalEndOfInputStream();
        }
        ByteBuffer[] outputBuffers = this.f.getOutputBuffers();
        while (true) {
            int iDequeueOutputBuffer = this.f.dequeueOutputBuffer(this.k, 10000L);
            if (iDequeueOutputBuffer == -1) {
                if (!z) {
                    return;
                }
            } else if (iDequeueOutputBuffer == -3) {
                outputBuffers = this.f.getOutputBuffers();
            } else if (iDequeueOutputBuffer == -2) {
                if (!this.j) {
                    MediaFormat outputFormat = this.f.getOutputFormat();
                    Objects.toString(outputFormat);
                    this.i = this.h.addTrack(outputFormat);
                    this.h.start();
                    this.j = true;
                } else {
                    throw new RuntimeException("format changed twice");
                }
            } else if (iDequeueOutputBuffer >= 0) {
                ByteBuffer byteBuffer = outputBuffers[iDequeueOutputBuffer];
                if (byteBuffer != null) {
                    MediaCodec.BufferInfo bufferInfo = this.k;
                    if ((bufferInfo.flags & 2) != 0) {
                        bufferInfo.size = 0;
                    }
                    if (bufferInfo.size != 0) {
                        if (this.j) {
                            byteBuffer.position(bufferInfo.offset);
                            MediaCodec.BufferInfo bufferInfo2 = this.k;
                            byteBuffer.limit(bufferInfo2.offset + bufferInfo2.size);
                            this.h.writeSampleData(this.i, byteBuffer, this.k);
                        } else {
                            throw new RuntimeException("muxer hasn't started");
                        }
                    }
                    this.f.releaseOutputBuffer(iDequeueOutputBuffer, false);
                    if ((this.k.flags & 4) != 0) {
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
