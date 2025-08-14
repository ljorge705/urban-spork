package com.onfido.android.sdk.capture.config;

import com.onfido.reactnative.sdk.ReactNativeBridgeUtiles;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MediaCallback.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\u0012"}, d2 = {"Lcom/onfido/android/sdk/capture/config/MediaFile;", "", ReactNativeBridgeUtiles.KEY_FILE_DATA, "", ReactNativeBridgeUtiles.KEY_FILE_TYPE, "", ReactNativeBridgeUtiles.KEY_FILE_NAME, "([BLjava/lang/String;Ljava/lang/String;)V", "getFileData", "()[B", "getFileName", "()Ljava/lang/String;", "getFileType", "equals", "", "other", "hashCode", "", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class MediaFile {
    private final byte[] fileData;
    private final String fileName;
    private final String fileType;

    public final byte[] getFileData() {
        return this.fileData;
    }

    public final String getFileName() {
        return this.fileName;
    }

    public final String getFileType() {
        return this.fileType;
    }

    public MediaFile(byte[] fileData, String fileType, String fileName) {
        Intrinsics.checkNotNullParameter(fileData, "fileData");
        Intrinsics.checkNotNullParameter(fileType, "fileType");
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        this.fileData = fileData;
        this.fileType = fileType;
        this.fileName = fileName;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(getClass(), other != null ? other.getClass() : null)) {
            return false;
        }
        Intrinsics.checkNotNull(other, "null cannot be cast to non-null type com.onfido.android.sdk.capture.config.MediaFile");
        MediaFile mediaFile = (MediaFile) other;
        return Arrays.equals(this.fileData, mediaFile.fileData) && Intrinsics.areEqual(this.fileType, mediaFile.fileType) && Intrinsics.areEqual(this.fileName, mediaFile.fileName);
    }

    public int hashCode() {
        return (((Arrays.hashCode(this.fileData) * 31) + this.fileType.hashCode()) * 31) + this.fileName.hashCode();
    }
}
