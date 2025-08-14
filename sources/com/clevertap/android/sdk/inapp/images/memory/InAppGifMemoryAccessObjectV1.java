package com.clevertap.android.sdk.inapp.images.memory;

import androidx.exifinterface.media.ExifInterface;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.ILogger;
import com.clevertap.android.sdk.inapp.images.memory.MemoryDataTransformationType;
import com.clevertap.android.sdk.inapp.images.repo.FileResourcesRepoImplKt;
import com.clevertap.android.sdk.utils.CTCaches;
import java.io.File;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: InAppGifMemoryAccessObjectV1.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\u0012\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J+\u0010\f\u001a\u0004\u0018\u0001H\r\"\u0004\b\u0000\u0010\r2\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\r0\u000fH\u0016¢\u0006\u0002\u0010\u0010J\u001e\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\t\u0018\u00010\u00122\u0006\u0010\n\u001a\u00020\u000bH\u0016J+\u0010\u0013\u001a\u0004\u0018\u0001H\r\"\u0004\b\u0000\u0010\r2\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\r0\u000fH\u0016¢\u0006\u0002\u0010\u0010J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u001e\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\t\u0018\u00010\u00122\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0018\u0010\u0017\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u0002H\u0016J$\u0010\u0019\u001a\u00020\u00152\u0006\u0010\n\u001a\u00020\u000b2\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\t0\u0012H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/clevertap/android/sdk/inapp/images/memory/InAppGifMemoryAccessObjectV1;", "Lcom/clevertap/android/sdk/inapp/images/memory/MemoryAccessObject;", "", "ctCaches", "Lcom/clevertap/android/sdk/utils/CTCaches;", "logger", "Lcom/clevertap/android/sdk/ILogger;", "(Lcom/clevertap/android/sdk/utils/CTCaches;Lcom/clevertap/android/sdk/ILogger;)V", "fetchDiskMemory", "Ljava/io/File;", Constants.KEY_KEY, "", "fetchDiskMemoryAndTransform", ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, "transformTo", "Lcom/clevertap/android/sdk/inapp/images/memory/MemoryDataTransformationType;", "(Ljava/lang/String;Lcom/clevertap/android/sdk/inapp/images/memory/MemoryDataTransformationType;)Ljava/lang/Object;", "fetchInMemory", "Lkotlin/Pair;", "fetchInMemoryAndTransform", "removeDiskMemory", "", "removeInMemory", "saveDiskMemory", "data", "saveInMemory", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class InAppGifMemoryAccessObjectV1 implements MemoryAccessObject<byte[]> {
    private final CTCaches ctCaches;
    private final ILogger logger;

    public InAppGifMemoryAccessObjectV1(CTCaches ctCaches, ILogger iLogger) {
        Intrinsics.checkNotNullParameter(ctCaches, "ctCaches");
        this.ctCaches = ctCaches;
        this.logger = iLogger;
    }

    @Override // com.clevertap.android.sdk.inapp.images.memory.MemoryAccessObject
    public Pair<byte[], File> fetchInMemory(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.ctCaches.gifInMemory().get(key);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.clevertap.android.sdk.inapp.images.memory.MemoryAccessObject
    public <A> A fetchInMemoryAndTransform(String key, MemoryDataTransformationType<A> transformTo) {
        Object obj;
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(transformTo, "transformTo");
        Pair<byte[], File> pairFetchInMemory = fetchInMemory(key);
        if (pairFetchInMemory == null) {
            return null;
        }
        ILogger iLogger = this.logger;
        if (iLogger != null) {
            iLogger.verbose(FileResourcesRepoImplKt.TAG_FILE_DOWNLOAD, key + " data found in GIF in-memory");
        }
        if (Intrinsics.areEqual(transformTo, MemoryDataTransformationType.ToBitmap.INSTANCE)) {
            Object objInvoke = MemoryAccessObjectKt.getBytesToBitmap().invoke(pairFetchInMemory.getFirst());
            obj = objInvoke;
            if (objInvoke == null) {
                return null;
            }
        } else if (Intrinsics.areEqual(transformTo, MemoryDataTransformationType.ToByteArray.INSTANCE)) {
            Object first = pairFetchInMemory.getFirst();
            obj = first;
            if (first == null) {
                return null;
            }
        } else {
            if (!Intrinsics.areEqual(transformTo, MemoryDataTransformationType.ToFile.INSTANCE)) {
                throw new NoWhenBranchMatchedException();
            }
            Object second = pairFetchInMemory.getSecond();
            obj = second;
            if (second == null) {
                return null;
            }
        }
        return obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0054  */
    @Override // com.clevertap.android.sdk.inapp.images.memory.MemoryAccessObject
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public <A> A fetchDiskMemoryAndTransform(java.lang.String r6, com.clevertap.android.sdk.inapp.images.memory.MemoryDataTransformationType<A> r7) {
        /*
            r5 = this;
            java.lang.String r0 = "key"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            java.lang.String r0 = "transformTo"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            java.io.File r0 = r5.fetchDiskMemory(r6)
            r1 = 0
            if (r0 == 0) goto L78
            com.clevertap.android.sdk.ILogger r2 = r5.logger
            if (r2 == 0) goto L2e
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.StringBuilder r3 = r3.append(r6)
            java.lang.String r4 = " data found in GIF disk memory"
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r3 = r3.toString()
            java.lang.String r4 = "FileDownload"
            r2.verbose(r4, r3)
        L2e:
            kotlin.jvm.functions.Function1 r2 = com.clevertap.android.sdk.inapp.images.memory.MemoryAccessObjectKt.getFileToBytes()
            java.lang.Object r2 = r2.invoke(r0)
            byte[] r2 = (byte[]) r2
            if (r2 == 0) goto L42
            kotlin.Pair r3 = new kotlin.Pair
            r3.<init>(r2, r0)
            r5.saveInMemory(r6, r3)
        L42:
            com.clevertap.android.sdk.inapp.images.memory.MemoryDataTransformationType$ToBitmap r6 = com.clevertap.android.sdk.inapp.images.memory.MemoryDataTransformationType.ToBitmap.INSTANCE
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual(r7, r6)
            if (r6 == 0) goto L56
            kotlin.jvm.functions.Function1 r6 = com.clevertap.android.sdk.inapp.images.memory.MemoryAccessObjectKt.getFileToBitmap()
            java.lang.Object r0 = r6.invoke(r0)
            if (r0 != 0) goto L70
        L54:
            r0 = r1
            goto L70
        L56:
            com.clevertap.android.sdk.inapp.images.memory.MemoryDataTransformationType$ToByteArray r6 = com.clevertap.android.sdk.inapp.images.memory.MemoryDataTransformationType.ToByteArray.INSTANCE
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual(r7, r6)
            if (r6 == 0) goto L64
            boolean r6 = r2 instanceof java.lang.Object
            if (r6 == 0) goto L54
            r0 = r2
            goto L70
        L64:
            com.clevertap.android.sdk.inapp.images.memory.MemoryDataTransformationType$ToFile r6 = com.clevertap.android.sdk.inapp.images.memory.MemoryDataTransformationType.ToFile.INSTANCE
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual(r7, r6)
            if (r6 == 0) goto L72
            boolean r6 = r0 instanceof java.lang.Object
            if (r6 == 0) goto L54
        L70:
            r1 = r0
            goto L78
        L72:
            kotlin.NoWhenBranchMatchedException r6 = new kotlin.NoWhenBranchMatchedException
            r6.<init>()
            throw r6
        L78:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.clevertap.android.sdk.inapp.images.memory.InAppGifMemoryAccessObjectV1.fetchDiskMemoryAndTransform(java.lang.String, com.clevertap.android.sdk.inapp.images.memory.MemoryDataTransformationType):java.lang.Object");
    }

    @Override // com.clevertap.android.sdk.inapp.images.memory.MemoryAccessObject
    public File fetchDiskMemory(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        ILogger iLogger = this.logger;
        if (iLogger != null) {
            iLogger.verbose(FileResourcesRepoImplKt.TAG_FILE_DOWNLOAD, "GIF In-Memory cache miss for " + key + " data");
        }
        return this.ctCaches.gifDiskMemory().get(key);
    }

    @Override // com.clevertap.android.sdk.inapp.images.memory.MemoryAccessObject
    public File saveDiskMemory(String key, byte[] data) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(data, "data");
        return this.ctCaches.gifDiskMemory().addAndReturnFileInstance(key, data);
    }

    @Override // com.clevertap.android.sdk.inapp.images.memory.MemoryAccessObject
    public boolean removeDiskMemory(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        ILogger iLogger = this.logger;
        if (iLogger != null) {
            iLogger.verbose(FileResourcesRepoImplKt.TAG_FILE_DOWNLOAD, "If present, will remove " + key + " data from GIF disk-memory");
        }
        return this.ctCaches.gifDiskMemory().remove(key);
    }

    @Override // com.clevertap.android.sdk.inapp.images.memory.MemoryAccessObject
    public Pair<byte[], File> removeInMemory(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        ILogger iLogger = this.logger;
        if (iLogger != null) {
            iLogger.verbose(FileResourcesRepoImplKt.TAG_FILE_DOWNLOAD, "If present, will remove " + key + " data from GIF in-memory");
        }
        return this.ctCaches.gifInMemory().remove(key);
    }

    @Override // com.clevertap.android.sdk.inapp.images.memory.MemoryAccessObject
    public boolean saveInMemory(String key, Pair<? extends byte[], ? extends File> data) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(data, "data");
        ILogger iLogger = this.logger;
        if (iLogger != null) {
            iLogger.verbose(FileResourcesRepoImplKt.TAG_FILE_DOWNLOAD, "Saving " + key + " data in GIF in-memory");
        }
        return this.ctCaches.gifInMemory().add(key, data);
    }
}
