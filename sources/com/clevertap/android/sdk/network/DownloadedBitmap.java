package com.clevertap.android.sdk.network;

import android.graphics.Bitmap;
import com.clevertap.android.sdk.Constants;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DownloadedBitmap.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001:\u0001\u001fB+\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nJ\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0007HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\tHÆ\u0003J5\u0010\u0017\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tHÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u001b\u001a\u00020\u001cH\u0016J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006 "}, d2 = {"Lcom/clevertap/android/sdk/network/DownloadedBitmap;", "", "bitmap", "Landroid/graphics/Bitmap;", "status", "Lcom/clevertap/android/sdk/network/DownloadedBitmap$Status;", "downloadTime", "", "bytes", "", "(Landroid/graphics/Bitmap;Lcom/clevertap/android/sdk/network/DownloadedBitmap$Status;J[B)V", "getBitmap", "()Landroid/graphics/Bitmap;", "getBytes", "()[B", "getDownloadTime", "()J", "getStatus", "()Lcom/clevertap/android/sdk/network/DownloadedBitmap$Status;", "component1", "component2", "component3", "component4", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "", "toString", "", "Status", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final /* data */ class DownloadedBitmap {
    private final Bitmap bitmap;
    private final byte[] bytes;
    private final long downloadTime;
    private final Status status;

    public static /* synthetic */ DownloadedBitmap copy$default(DownloadedBitmap downloadedBitmap, Bitmap bitmap, Status status, long j, byte[] bArr, int i, Object obj) {
        if ((i & 1) != 0) {
            bitmap = downloadedBitmap.bitmap;
        }
        if ((i & 2) != 0) {
            status = downloadedBitmap.status;
        }
        Status status2 = status;
        if ((i & 4) != 0) {
            j = downloadedBitmap.downloadTime;
        }
        long j2 = j;
        if ((i & 8) != 0) {
            bArr = downloadedBitmap.bytes;
        }
        return downloadedBitmap.copy(bitmap, status2, j2, bArr);
    }

    /* renamed from: component1, reason: from getter */
    public final Bitmap getBitmap() {
        return this.bitmap;
    }

    /* renamed from: component2, reason: from getter */
    public final Status getStatus() {
        return this.status;
    }

    /* renamed from: component3, reason: from getter */
    public final long getDownloadTime() {
        return this.downloadTime;
    }

    /* renamed from: component4, reason: from getter */
    public final byte[] getBytes() {
        return this.bytes;
    }

    public final DownloadedBitmap copy(Bitmap bitmap, Status status, long downloadTime, byte[] bytes) {
        Intrinsics.checkNotNullParameter(status, "status");
        return new DownloadedBitmap(bitmap, status, downloadTime, bytes);
    }

    public final Bitmap getBitmap() {
        return this.bitmap;
    }

    public final byte[] getBytes() {
        return this.bytes;
    }

    public final long getDownloadTime() {
        return this.downloadTime;
    }

    public final Status getStatus() {
        return this.status;
    }

    public String toString() {
        return "DownloadedBitmap(bitmap=" + this.bitmap + ", status=" + this.status + ", downloadTime=" + this.downloadTime + ", bytes=" + Arrays.toString(this.bytes) + ')';
    }

    public DownloadedBitmap(Bitmap bitmap, Status status, long j, byte[] bArr) {
        Intrinsics.checkNotNullParameter(status, "status");
        this.bitmap = bitmap;
        this.status = status;
        this.downloadTime = j;
        this.bytes = bArr;
    }

    public /* synthetic */ DownloadedBitmap(Bitmap bitmap, Status status, long j, byte[] bArr, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(bitmap, status, j, (i & 8) != 0 ? null : bArr);
    }

    /* compiled from: DownloadedBitmap.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\r"}, d2 = {"Lcom/clevertap/android/sdk/network/DownloadedBitmap$Status;", "", "statusValue", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getStatusValue", "()Ljava/lang/String;", "NO_IMAGE", "SUCCESS", "DOWNLOAD_FAILED", "NO_NETWORK", "INIT_ERROR", "SIZE_LIMIT_EXCEEDED", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public enum Status {
        NO_IMAGE("NO_IMAGE"),
        SUCCESS("SUCCESS"),
        DOWNLOAD_FAILED("DOWNLOAD_FAILED"),
        NO_NETWORK("NO_NETWORK"),
        INIT_ERROR("INIT_ERROR"),
        SIZE_LIMIT_EXCEEDED("SIZE_LIMIT_EXCEEDED");

        private final String statusValue;

        public final String getStatusValue() {
            return this.statusValue;
        }

        Status(String str) {
            this.statusValue = str;
        }
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(getClass(), other != null ? other.getClass() : null)) {
            return false;
        }
        Intrinsics.checkNotNull(other, "null cannot be cast to non-null type com.clevertap.android.sdk.network.DownloadedBitmap");
        DownloadedBitmap downloadedBitmap = (DownloadedBitmap) other;
        return Intrinsics.areEqual(this.bitmap, downloadedBitmap.bitmap) && this.status == downloadedBitmap.status && this.downloadTime == downloadedBitmap.downloadTime && Arrays.equals(this.bytes, downloadedBitmap.bytes);
    }

    public int hashCode() {
        Bitmap bitmap = this.bitmap;
        return ((((((bitmap != null ? bitmap.hashCode() : 0) * 31) + this.status.hashCode()) * 31) + Long.hashCode(this.downloadTime)) * 31) + Arrays.hashCode(this.bytes);
    }
}
