package com.clevertap.android.sdk.inapp.images.memory;

import android.graphics.Bitmap;
import androidx.exifinterface.media.ExifInterface;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: MemoryAccessObject.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0003\u0004\u0005\u0006B\u0007\b\u0004¢\u0006\u0002\u0010\u0003\u0082\u0001\u0003\u0007\b\t¨\u0006\n"}, d2 = {"Lcom/clevertap/android/sdk/inapp/images/memory/MemoryDataTransformationType;", ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, "", "()V", "ToBitmap", "ToByteArray", "ToFile", "Lcom/clevertap/android/sdk/inapp/images/memory/MemoryDataTransformationType$ToBitmap;", "Lcom/clevertap/android/sdk/inapp/images/memory/MemoryDataTransformationType$ToByteArray;", "Lcom/clevertap/android/sdk/inapp/images/memory/MemoryDataTransformationType$ToFile;", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public abstract class MemoryDataTransformationType<A> {
    public /* synthetic */ MemoryDataTransformationType(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private MemoryDataTransformationType() {
    }

    /* compiled from: MemoryAccessObject.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/clevertap/android/sdk/inapp/images/memory/MemoryDataTransformationType$ToBitmap;", "Lcom/clevertap/android/sdk/inapp/images/memory/MemoryDataTransformationType;", "Landroid/graphics/Bitmap;", "()V", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class ToBitmap extends MemoryDataTransformationType<Bitmap> {
        public static final ToBitmap INSTANCE = new ToBitmap();

        private ToBitmap() {
            super(null);
        }
    }

    /* compiled from: MemoryAccessObject.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/clevertap/android/sdk/inapp/images/memory/MemoryDataTransformationType$ToByteArray;", "Lcom/clevertap/android/sdk/inapp/images/memory/MemoryDataTransformationType;", "", "()V", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class ToByteArray extends MemoryDataTransformationType<byte[]> {
        public static final ToByteArray INSTANCE = new ToByteArray();

        private ToByteArray() {
            super(null);
        }
    }

    /* compiled from: MemoryAccessObject.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/clevertap/android/sdk/inapp/images/memory/MemoryDataTransformationType$ToFile;", "Lcom/clevertap/android/sdk/inapp/images/memory/MemoryDataTransformationType;", "Ljava/io/File;", "()V", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class ToFile extends MemoryDataTransformationType<File> {
        public static final ToFile INSTANCE = new ToFile();

        private ToFile() {
            super(null);
        }
    }
}
