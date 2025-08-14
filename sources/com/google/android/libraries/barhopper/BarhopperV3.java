package com.google.android.libraries.barhopper;

import android.graphics.Bitmap;
import android.util.Log;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdi;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdn;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzen;
import com.google.barhopper.deeplearning.BarhopperV3Options;
import com.google.photos.vision.barhopper.BarhopperProto$BarhopperResponse;
import java.io.Closeable;
import java.io.IOException;
import java.nio.ByteBuffer;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes3.dex */
public class BarhopperV3 implements Closeable {
    private static final long NULL_NATIVE_CONTEXT = 0;
    private static final String TAG = "BarhopperV3";
    private long nativeContext;

    public BarhopperV3() {
        System.loadLibrary("barhopper_v3");
    }

    private native void closeNative(long j);

    private native long createNative();

    private native long createNativeWithClientOptions(byte[] bArr);

    private native byte[] recognizeBitmapNative(long j, Bitmap bitmap, RecognitionOptions recognitionOptions);

    private native byte[] recognizeBufferNative(long j, int i, int i2, ByteBuffer byteBuffer, RecognitionOptions recognitionOptions);

    private native byte[] recognizeNative(long j, int i, int i2, byte[] bArr, RecognitionOptions recognitionOptions);

    private native byte[] recognizeStridedBufferNative(long j, int i, int i2, int i3, ByteBuffer byteBuffer, RecognitionOptions recognitionOptions);

    private native byte[] recognizeStridedNative(long j, int i, int i2, int i3, byte[] bArr, RecognitionOptions recognitionOptions);

    private static BarhopperProto$BarhopperResponse toProto(byte[] bArr) {
        try {
            return BarhopperProto$BarhopperResponse.zzb(bArr, zzdn.zza());
        } catch (zzen e) {
            throw new IllegalStateException("Received unexpected BarhopperResponse buffer: {0}".concat(e.toString()));
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        long j = this.nativeContext;
        if (j != 0) {
            closeNative(j);
            this.nativeContext = 0L;
        }
    }

    public void create() {
        if (this.nativeContext != 0) {
            Log.w(TAG, "Native context already exists.");
            return;
        }
        long jCreateNative = createNative();
        this.nativeContext = jCreateNative;
        if (jCreateNative == 0) {
            throw new IllegalStateException("Failed to create native context.");
        }
    }

    protected void finalize() throws Throwable {
        try {
            close();
        } finally {
            super.finalize();
        }
    }

    public BarhopperProto$BarhopperResponse recognize(int i, int i2, int i3, ByteBuffer byteBuffer, RecognitionOptions recognitionOptions) {
        long j = this.nativeContext;
        if (j != 0) {
            return toProto(recognizeStridedBufferNative(j, i, i2, i3, byteBuffer, recognitionOptions));
        }
        throw new IllegalStateException("Native context does not exist.");
    }

    public void create(BarhopperV3Options barhopperV3Options) {
        if (this.nativeContext != 0) {
            Log.w(TAG, "Native context already exists.");
            return;
        }
        try {
            byte[] bArr = new byte[barhopperV3Options.zzE()];
            zzdi zzdiVarZzF = zzdi.zzF(bArr);
            barhopperV3Options.zzW(zzdiVarZzF);
            zzdiVarZzF.zzG();
            long jCreateNativeWithClientOptions = createNativeWithClientOptions(bArr);
            this.nativeContext = jCreateNativeWithClientOptions;
            if (jCreateNativeWithClientOptions == 0) {
                throw new IllegalArgumentException("Failed to create native context with client options.");
            }
        } catch (IOException e) {
            String name = barhopperV3Options.getClass().getName();
            StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 72);
            sb.append("Serializing ");
            sb.append(name);
            sb.append(" to a byte array threw an IOException (should never happen).");
            throw new RuntimeException(sb.toString(), e);
        }
    }

    public BarhopperProto$BarhopperResponse recognize(int i, int i2, int i3, byte[] bArr, RecognitionOptions recognitionOptions) {
        long j = this.nativeContext;
        if (j == 0) {
            throw new IllegalStateException("Native context does not exist.");
        }
        return toProto(recognizeStridedNative(j, i, i2, i3, bArr, recognitionOptions));
    }

    public BarhopperProto$BarhopperResponse recognize(int i, int i2, ByteBuffer byteBuffer, RecognitionOptions recognitionOptions) {
        long j = this.nativeContext;
        if (j == 0) {
            throw new IllegalStateException("Native context does not exist.");
        }
        return toProto(recognizeBufferNative(j, i, i2, byteBuffer, recognitionOptions));
    }

    public BarhopperProto$BarhopperResponse recognize(int i, int i2, byte[] bArr, RecognitionOptions recognitionOptions) {
        long j = this.nativeContext;
        if (j == 0) {
            throw new IllegalStateException("Native context does not exist.");
        }
        return toProto(recognizeNative(j, i, i2, bArr, recognitionOptions));
    }

    public BarhopperProto$BarhopperResponse recognize(Bitmap bitmap, RecognitionOptions recognitionOptions) {
        long j = this.nativeContext;
        if (j == 0) {
            throw new IllegalStateException("Native context does not exist.");
        }
        return toProto(recognizeBitmapNative(j, bitmap, recognitionOptions));
    }
}
