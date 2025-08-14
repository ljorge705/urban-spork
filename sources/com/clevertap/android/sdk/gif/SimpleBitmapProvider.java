package com.clevertap.android.sdk.gif;

import android.graphics.Bitmap;
import com.clevertap.android.sdk.gif.GifDecoder;

/* loaded from: classes5.dex */
public class SimpleBitmapProvider implements GifDecoder.BitmapProvider {
    @Override // com.clevertap.android.sdk.gif.GifDecoder.BitmapProvider
    public void release(byte[] bArr) {
    }

    @Override // com.clevertap.android.sdk.gif.GifDecoder.BitmapProvider
    public void release(int[] iArr) {
    }

    @Override // com.clevertap.android.sdk.gif.GifDecoder.BitmapProvider
    public Bitmap obtain(int i, int i2, Bitmap.Config config) {
        return Bitmap.createBitmap(i, i2, config);
    }

    @Override // com.clevertap.android.sdk.gif.GifDecoder.BitmapProvider
    public byte[] obtainByteArray(int i) {
        return new byte[i];
    }

    @Override // com.clevertap.android.sdk.gif.GifDecoder.BitmapProvider
    public int[] obtainIntArray(int i) {
        return new int[i];
    }

    @Override // com.clevertap.android.sdk.gif.GifDecoder.BitmapProvider
    public void release(Bitmap bitmap) {
        bitmap.recycle();
    }
}
