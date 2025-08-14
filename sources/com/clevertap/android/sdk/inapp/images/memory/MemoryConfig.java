package com.clevertap.android.sdk.inapp.images.memory;

import com.clevertap.android.sdk.Constants;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MemoryConfig.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J1\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\f¨\u0006\u001b"}, d2 = {"Lcom/clevertap/android/sdk/inapp/images/memory/MemoryConfig;", "", "minInMemorySizeKB", "", "optimistic", "maxDiskSizeKB", "diskDirectory", "Ljava/io/File;", "(JJJLjava/io/File;)V", "getDiskDirectory", "()Ljava/io/File;", "getMaxDiskSizeKB", "()J", "getMinInMemorySizeKB", "getOptimistic", "component1", "component2", "component3", "component4", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "", "toString", "", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final /* data */ class MemoryConfig {
    private final File diskDirectory;
    private final long maxDiskSizeKB;
    private final long minInMemorySizeKB;
    private final long optimistic;

    /* renamed from: component1, reason: from getter */
    public final long getMinInMemorySizeKB() {
        return this.minInMemorySizeKB;
    }

    /* renamed from: component2, reason: from getter */
    public final long getOptimistic() {
        return this.optimistic;
    }

    /* renamed from: component3, reason: from getter */
    public final long getMaxDiskSizeKB() {
        return this.maxDiskSizeKB;
    }

    /* renamed from: component4, reason: from getter */
    public final File getDiskDirectory() {
        return this.diskDirectory;
    }

    public final MemoryConfig copy(long minInMemorySizeKB, long optimistic, long maxDiskSizeKB, File diskDirectory) {
        Intrinsics.checkNotNullParameter(diskDirectory, "diskDirectory");
        return new MemoryConfig(minInMemorySizeKB, optimistic, maxDiskSizeKB, diskDirectory);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MemoryConfig)) {
            return false;
        }
        MemoryConfig memoryConfig = (MemoryConfig) other;
        return this.minInMemorySizeKB == memoryConfig.minInMemorySizeKB && this.optimistic == memoryConfig.optimistic && this.maxDiskSizeKB == memoryConfig.maxDiskSizeKB && Intrinsics.areEqual(this.diskDirectory, memoryConfig.diskDirectory);
    }

    public final File getDiskDirectory() {
        return this.diskDirectory;
    }

    public final long getMaxDiskSizeKB() {
        return this.maxDiskSizeKB;
    }

    public final long getMinInMemorySizeKB() {
        return this.minInMemorySizeKB;
    }

    public final long getOptimistic() {
        return this.optimistic;
    }

    public int hashCode() {
        return (((((Long.hashCode(this.minInMemorySizeKB) * 31) + Long.hashCode(this.optimistic)) * 31) + Long.hashCode(this.maxDiskSizeKB)) * 31) + this.diskDirectory.hashCode();
    }

    public String toString() {
        return "MemoryConfig(minInMemorySizeKB=" + this.minInMemorySizeKB + ", optimistic=" + this.optimistic + ", maxDiskSizeKB=" + this.maxDiskSizeKB + ", diskDirectory=" + this.diskDirectory + ')';
    }

    public MemoryConfig(long j, long j2, long j3, File diskDirectory) {
        Intrinsics.checkNotNullParameter(diskDirectory, "diskDirectory");
        this.minInMemorySizeKB = j;
        this.optimistic = j2;
        this.maxDiskSizeKB = j3;
        this.diskDirectory = diskDirectory;
    }
}
