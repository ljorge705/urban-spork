package com.uxcam.internals;

import android.opengl.GLES20;
import android.opengl.Matrix;
import com.drew.metadata.exif.makernotes.FujifilmMakernoteDirectory;
import com.onfido.android.sdk.capture.internal.camera.camerax.DefaultFrameSampler;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/* loaded from: classes6.dex */
public final class co {

    /* renamed from: a, reason: collision with root package name */
    public final float[] f112a = new float[16];
    public final int b;
    public final int c;
    public fi d;

    public co(int i, int i2) {
        this.b = i;
        this.c = i2;
    }

    public static int a(String str, int i) {
        int iGlCreateShader = GLES20.glCreateShader(i);
        GLES20.glShaderSource(iGlCreateShader, str);
        GLES20.glCompileShader(iGlCreateShader);
        return iGlCreateShader;
    }

    public final void b() {
        GLES20.glViewport(0, 0, this.b, this.c);
        float f = this.b / this.c;
        Matrix.frustumM(this.f112a, 0, -f, f, -1.0f, 1.0f, 3.0f, 7.0f);
    }

    public final void c() {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        this.d = new fi();
    }

    public final void a() {
        GLES20.glClear(FujifilmMakernoteDirectory.TAG_FACES_DETECTED);
        this.d.a();
        fi fiVar = this.d;
        int iGlGetUniformLocation = GLES20.glGetUniformLocation(fiVar.b, "uScreen");
        int iGlGetUniformLocation2 = GLES20.glGetUniformLocation(fiVar.b, "uTexture");
        FloatBuffer floatBufferAsFloatBuffer = ByteBuffer.allocateDirect(64).order(ByteOrder.nativeOrder()).asFloatBuffer();
        floatBufferAsFloatBuffer.put(new float[]{2.0f / 320, 0.0f, 0.0f, 0.0f, 0.0f, (-2.0f) / DefaultFrameSampler.DESIRED_FRAME_WIDTH, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 1.0f, 0.0f, 1.0f}).position(0);
        GLES20.glUniformMatrix4fv(iGlGetUniformLocation, floatBufferAsFloatBuffer.limit() / 16, false, floatBufferAsFloatBuffer);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, fiVar.f151a[0]);
        GLES20.glUniform1i(iGlGetUniformLocation2, 0);
        fi fiVar2 = this.d;
        int iGlGetAttribLocation = GLES20.glGetAttribLocation(fiVar2.b, "aPosition");
        int iGlGetAttribLocation2 = GLES20.glGetAttribLocation(fiVar2.b, "aTexPos");
        FloatBuffer floatBufferAsFloatBuffer2 = ByteBuffer.allocateDirect(64).order(ByteOrder.nativeOrder()).asFloatBuffer();
        floatBufferAsFloatBuffer2.put(new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 480.0f, 0.0f, 1.0f, 320.0f, 0.0f, 1.0f, 0.0f, 320.0f, 480.0f, 1.0f, 1.0f});
        floatBufferAsFloatBuffer2.position(0);
        GLES20.glVertexAttribPointer(iGlGetAttribLocation, 2, FujifilmMakernoteDirectory.TAG_MAX_APERTURE_AT_MIN_FOCAL, false, 16, (Buffer) floatBufferAsFloatBuffer2);
        GLES20.glEnableVertexAttribArray(iGlGetAttribLocation);
        floatBufferAsFloatBuffer2.position(2);
        GLES20.glVertexAttribPointer(iGlGetAttribLocation2, 2, FujifilmMakernoteDirectory.TAG_MAX_APERTURE_AT_MIN_FOCAL, false, 16, (Buffer) floatBufferAsFloatBuffer2);
        GLES20.glEnableVertexAttribArray(iGlGetAttribLocation2);
        GLES20.glDrawArrays(5, 0, 4);
    }
}
