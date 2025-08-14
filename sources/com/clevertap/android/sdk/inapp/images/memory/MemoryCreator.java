package com.clevertap.android.sdk.inapp.images.memory;

import android.graphics.Bitmap;
import com.clevertap.android.sdk.ILogger;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MemoryCreator.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/clevertap/android/sdk/inapp/images/memory/MemoryCreator;", "", "()V", "Companion", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class MemoryCreator {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final long FILE_CACHE_MIN_KB = 15360;
    private static final long FILE_SIZE_MAX_DISK = 5120;
    private static final long GIF_CACHE_MIN_KB = 5120;
    private static final long IMAGE_CACHE_MIN_KB = 20480;
    private static final long IMAGE_SIZE_MAX_DISK = 5120;

    /* compiled from: MemoryCreator.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fJ\u001e\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fJ\u001e\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\n2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/clevertap/android/sdk/inapp/images/memory/MemoryCreator$Companion;", "", "()V", "FILE_CACHE_MIN_KB", "", "FILE_SIZE_MAX_DISK", "GIF_CACHE_MIN_KB", "IMAGE_CACHE_MIN_KB", "IMAGE_SIZE_MAX_DISK", "createFileMemoryV2", "Lcom/clevertap/android/sdk/inapp/images/memory/Memory;", "", "diskMemoryLocation", "Ljava/io/File;", "logger", "Lcom/clevertap/android/sdk/ILogger;", "createInAppGifMemoryV1", "createInAppImageMemoryV1", "Landroid/graphics/Bitmap;", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Memory<byte[]> createInAppGifMemoryV1(File diskMemoryLocation, ILogger logger) {
            Intrinsics.checkNotNullParameter(diskMemoryLocation, "diskMemoryLocation");
            return new InAppGifMemoryV1(new MemoryConfig(5120L, Runtime.getRuntime().maxMemory() / 32768, 5120L, diskMemoryLocation), logger);
        }

        public final Memory<Bitmap> createInAppImageMemoryV1(File diskMemoryLocation, ILogger logger) {
            Intrinsics.checkNotNullParameter(diskMemoryLocation, "diskMemoryLocation");
            return new InAppImageMemoryV1(new MemoryConfig(MemoryCreator.IMAGE_CACHE_MIN_KB, Runtime.getRuntime().maxMemory() / 32768, 5120L, diskMemoryLocation), logger);
        }

        public final Memory<byte[]> createFileMemoryV2(File diskMemoryLocation, ILogger logger) {
            Intrinsics.checkNotNullParameter(diskMemoryLocation, "diskMemoryLocation");
            return new FileMemoryV2(new MemoryConfig(MemoryCreator.FILE_CACHE_MIN_KB, Runtime.getRuntime().maxMemory() / 32768, 5120L, diskMemoryLocation), logger);
        }
    }
}
