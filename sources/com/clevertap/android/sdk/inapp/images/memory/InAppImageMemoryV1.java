package com.clevertap.android.sdk.inapp.images.memory;

import android.graphics.Bitmap;
import com.clevertap.android.sdk.ILogger;
import com.clevertap.android.sdk.utils.DiskMemory;
import com.clevertap.android.sdk.utils.InMemoryLruCache;
import java.io.File;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: InAppImageMemoryV1.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\u0013\u001a\u00020\rH\u0016J\u001a\u0010\u0014\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00110\u00100\u000fH\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u0018H\u0016R\u0014\u0010\u0003\u001a\u00020\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\u000e\u001a\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00110\u0010\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/clevertap/android/sdk/inapp/images/memory/InAppImageMemoryV1;", "Lcom/clevertap/android/sdk/inapp/images/memory/Memory;", "Landroid/graphics/Bitmap;", "config", "Lcom/clevertap/android/sdk/inapp/images/memory/MemoryConfig;", "logger", "Lcom/clevertap/android/sdk/ILogger;", "(Lcom/clevertap/android/sdk/inapp/images/memory/MemoryConfig;Lcom/clevertap/android/sdk/ILogger;)V", "getConfig$clevertap_core_release", "()Lcom/clevertap/android/sdk/inapp/images/memory/MemoryConfig;", "diskMemoryLock", "", "imageDiskMemory", "Lcom/clevertap/android/sdk/utils/DiskMemory;", "imageInMemory", "Lcom/clevertap/android/sdk/utils/InMemoryLruCache;", "Lkotlin/Pair;", "Ljava/io/File;", "inMemoryLock", "createDiskMemory", "createInMemory", "freeInMemory", "", "inMemorySize", "", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class InAppImageMemoryV1 implements Memory<Bitmap> {
    private final MemoryConfig config;
    private final Object diskMemoryLock;
    private DiskMemory imageDiskMemory;
    private InMemoryLruCache<Pair<Bitmap, File>> imageInMemory;
    private final Object inMemoryLock;
    private final ILogger logger;

    /* renamed from: getConfig$clevertap_core_release, reason: from getter */
    public final MemoryConfig getConfig() {
        return this.config;
    }

    public InAppImageMemoryV1(MemoryConfig config, ILogger iLogger) {
        Intrinsics.checkNotNullParameter(config, "config");
        this.config = config;
        this.logger = iLogger;
        this.inMemoryLock = new Object();
        this.diskMemoryLock = new Object();
    }

    public /* synthetic */ InAppImageMemoryV1(MemoryConfig memoryConfig, ILogger iLogger, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(memoryConfig, (i & 2) != 0 ? null : iLogger);
    }

    @Override // com.clevertap.android.sdk.inapp.images.memory.Memory
    public InMemoryLruCache<Pair<Bitmap, File>> createInMemory() {
        if (this.imageInMemory == null) {
            synchronized (this.inMemoryLock) {
                if (this.imageInMemory == null) {
                    this.imageInMemory = new InMemoryLruCache<>(inMemorySize(), null, 2, null);
                }
                Unit unit = Unit.INSTANCE;
            }
        }
        InMemoryLruCache<Pair<Bitmap, File>> inMemoryLruCache = this.imageInMemory;
        Intrinsics.checkNotNull(inMemoryLruCache);
        return inMemoryLruCache;
    }

    @Override // com.clevertap.android.sdk.inapp.images.memory.Memory
    public DiskMemory createDiskMemory() {
        if (this.imageDiskMemory == null) {
            synchronized (this.diskMemoryLock) {
                if (this.imageDiskMemory == null) {
                    this.imageDiskMemory = new DiskMemory(this.config.getDiskDirectory(), (int) this.config.getMaxDiskSizeKB(), this.logger, null, 8, null);
                }
                Unit unit = Unit.INSTANCE;
            }
        }
        DiskMemory diskMemory = this.imageDiskMemory;
        Intrinsics.checkNotNull(diskMemory);
        return diskMemory;
    }

    @Override // com.clevertap.android.sdk.inapp.images.memory.Memory
    public int inMemorySize() {
        int iMax = (int) Math.max(this.config.getOptimistic(), this.config.getMinInMemorySizeKB());
        ILogger iLogger = this.logger;
        if (iLogger != null) {
            iLogger.verbose("Image cache:: max-mem/1024 = " + this.config.getOptimistic() + ", minCacheSize = " + this.config.getMinInMemorySizeKB() + ", selected = " + iMax);
        }
        return iMax;
    }

    @Override // com.clevertap.android.sdk.inapp.images.memory.Memory
    public void freeInMemory() {
        InMemoryLruCache<Pair<Bitmap, File>> inMemoryLruCache = this.imageInMemory;
        if (inMemoryLruCache != null) {
            inMemoryLruCache.empty();
        }
        this.imageInMemory = null;
    }
}
