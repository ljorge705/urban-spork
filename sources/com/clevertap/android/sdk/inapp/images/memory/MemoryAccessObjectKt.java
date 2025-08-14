package com.clevertap.android.sdk.inapp.images.memory;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.clevertap.android.sdk.inapp.images.ExtensionsKt;
import java.io.ByteArrayOutputStream;
import java.io.File;
import kotlin.Metadata;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MemoryAccessObject.kt */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\"0\u0010\u0000\u001a!\u0012\u0015\u0012\u0013\u0018\u00010\u0002¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b\".\u0010\t\u001a\u001f\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\b\"0\u0010\f\u001a!\u0012\u0015\u0012\u0013\u0018\u00010\r¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\b\"0\u0010\u0010\u001a!\u0012\u0015\u0012\u0013\u0018\u00010\r¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\b¨\u0006\u0012"}, d2 = {"bitmapToBytes", "Lkotlin/Function1;", "Landroid/graphics/Bitmap;", "Lkotlin/ParameterName;", "name", "bitmap", "", "getBitmapToBytes", "()Lkotlin/jvm/functions/Function1;", "bytesToBitmap", "bytes", "getBytesToBitmap", "fileToBitmap", "Ljava/io/File;", "file", "getFileToBitmap", "fileToBytes", "getFileToBytes", "clevertap-core_release"}, k = 2, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class MemoryAccessObjectKt {
    private static final Function1<File, Bitmap> fileToBitmap = new Function1<File, Bitmap>() { // from class: com.clevertap.android.sdk.inapp.images.memory.MemoryAccessObjectKt$fileToBitmap$1
        @Override // kotlin.jvm.functions.Function1
        public final Bitmap invoke(File file) {
            if (file != null && ExtensionsKt.hasValidBitmap(file)) {
                return BitmapFactory.decodeFile(file.getAbsolutePath());
            }
            return null;
        }
    };
    private static final Function1<File, byte[]> fileToBytes = new Function1<File, byte[]>() { // from class: com.clevertap.android.sdk.inapp.images.memory.MemoryAccessObjectKt$fileToBytes$1
        @Override // kotlin.jvm.functions.Function1
        public final byte[] invoke(File file) {
            if (file != null) {
                return FilesKt.readBytes(file);
            }
            return null;
        }
    };
    private static final Function1<byte[], Bitmap> bytesToBitmap = new Function1<byte[], Bitmap>() { // from class: com.clevertap.android.sdk.inapp.images.memory.MemoryAccessObjectKt$bytesToBitmap$1
        @Override // kotlin.jvm.functions.Function1
        public final Bitmap invoke(byte[] it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return BitmapFactory.decodeByteArray(it, 0, it.length);
        }
    };
    private static final Function1<Bitmap, byte[]> bitmapToBytes = new Function1<Bitmap, byte[]>() { // from class: com.clevertap.android.sdk.inapp.images.memory.MemoryAccessObjectKt$bitmapToBytes$1
        @Override // kotlin.jvm.functions.Function1
        public final byte[] invoke(Bitmap bitmap) {
            if (bitmap == null) {
                return null;
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        }
    };

    public static final Function1<Bitmap, byte[]> getBitmapToBytes() {
        return bitmapToBytes;
    }

    public static final Function1<byte[], Bitmap> getBytesToBitmap() {
        return bytesToBitmap;
    }

    public static final Function1<File, Bitmap> getFileToBitmap() {
        return fileToBitmap;
    }

    public static final Function1<File, byte[]> getFileToBytes() {
        return fileToBytes;
    }
}
