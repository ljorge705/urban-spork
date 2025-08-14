package com.clevertap.android.sdk.utils;

import android.graphics.Bitmap;
import com.clevertap.android.sdk.inapp.images.memory.Memory;
import java.io.File;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CTCaches.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B1\b\u0002\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\u0002\u0010\bJ\u0006\u0010\t\u001a\u00020\nJ\u0018\u0010\u000b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000e0\r0\fJ\u0006\u0010\u000f\u001a\u00020\nJ\u0018\u0010\u0010\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000e0\r0\fJ\u0006\u0010\u0011\u001a\u00020\nJ\u0018\u0010\u0012\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000e0\r0\fR\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/clevertap/android/sdk/utils/CTCaches;", "", "inAppImageMemoryV1", "Lcom/clevertap/android/sdk/inapp/images/memory/Memory;", "Landroid/graphics/Bitmap;", "inAppGifMemoryV1", "", "fileMemory", "(Lcom/clevertap/android/sdk/inapp/images/memory/Memory;Lcom/clevertap/android/sdk/inapp/images/memory/Memory;Lcom/clevertap/android/sdk/inapp/images/memory/Memory;)V", "fileDiskMemory", "Lcom/clevertap/android/sdk/utils/DiskMemory;", "fileInMemory", "Lcom/clevertap/android/sdk/utils/InMemoryLruCache;", "Lkotlin/Pair;", "Ljava/io/File;", "gifDiskMemory", "gifInMemory", "imageDiskMemory", "imageInMemory", "Companion", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class CTCaches {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static CTCaches ctCaches;
    private final Memory<byte[]> fileMemory;
    private final Memory<byte[]> inAppGifMemoryV1;
    private final Memory<Bitmap> inAppImageMemoryV1;

    public /* synthetic */ CTCaches(Memory memory, Memory memory2, Memory memory3, DefaultConstructorMarker defaultConstructorMarker) {
        this(memory, memory2, memory3);
    }

    private CTCaches(Memory<Bitmap> memory, Memory<byte[]> memory2, Memory<byte[]> memory3) {
        this.inAppImageMemoryV1 = memory;
        this.inAppGifMemoryV1 = memory2;
        this.fileMemory = memory3;
    }

    /* compiled from: CTCaches.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0006J0\u0010\u0007\u001a\u00020\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\t2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\tR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/clevertap/android/sdk/utils/CTCaches$Companion;", "", "()V", "ctCaches", "Lcom/clevertap/android/sdk/utils/CTCaches;", "clear", "", "instance", "inAppImageMemoryV1", "Lcom/clevertap/android/sdk/inapp/images/memory/Memory;", "Landroid/graphics/Bitmap;", "inAppGifMemoryV1", "", "fileMemory", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final CTCaches instance(Memory<Bitmap> inAppImageMemoryV1, Memory<byte[]> inAppGifMemoryV1, Memory<byte[]> fileMemory) {
            Intrinsics.checkNotNullParameter(inAppImageMemoryV1, "inAppImageMemoryV1");
            Intrinsics.checkNotNullParameter(inAppGifMemoryV1, "inAppGifMemoryV1");
            Intrinsics.checkNotNullParameter(fileMemory, "fileMemory");
            if (CTCaches.ctCaches == null) {
                synchronized (this) {
                    if (CTCaches.ctCaches == null) {
                        Companion companion = CTCaches.INSTANCE;
                        CTCaches.ctCaches = new CTCaches(inAppImageMemoryV1, inAppGifMemoryV1, fileMemory, null);
                    }
                    Unit unit = Unit.INSTANCE;
                }
            }
            CTCaches cTCaches = CTCaches.ctCaches;
            Intrinsics.checkNotNull(cTCaches);
            return cTCaches;
        }

        public final void clear() {
            synchronized (this) {
                Companion companion = CTCaches.INSTANCE;
                CTCaches.ctCaches = null;
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    public final InMemoryLruCache<Pair<Bitmap, File>> imageInMemory() {
        return this.inAppImageMemoryV1.createInMemory();
    }

    public final InMemoryLruCache<Pair<byte[], File>> gifInMemory() {
        return this.inAppGifMemoryV1.createInMemory();
    }

    public final InMemoryLruCache<Pair<byte[], File>> fileInMemory() {
        return this.fileMemory.createInMemory();
    }

    public final DiskMemory imageDiskMemory() {
        return this.inAppImageMemoryV1.createDiskMemory();
    }

    public final DiskMemory gifDiskMemory() {
        return this.inAppGifMemoryV1.createDiskMemory();
    }

    public final DiskMemory fileDiskMemory() {
        return this.fileMemory.createDiskMemory();
    }
}
