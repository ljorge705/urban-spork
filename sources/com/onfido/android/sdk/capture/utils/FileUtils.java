package com.onfido.android.sdk.capture.utils;

import com.onfido.android.sdk.capture.internal.util.logging.Timber;
import com.onfido.reactnative.sdk.ReactNativeBridgeUtiles;
import java.io.File;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0004\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u001a\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\bH\u0007J\u000e\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u0006¨\u0006\u000f"}, d2 = {"Lcom/onfido/android/sdk/capture/utils/FileUtils;", "", "()V", "deleteFilesWithPrefixFromFolder", "", "folder", "Ljava/io/File;", "prefix", "", "fileFromByteArray", "byteArray", "", ReactNativeBridgeUtiles.KEY_FILE_NAME, "fileToByteArray", "file", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FileUtils {
    public static final FileUtils INSTANCE = new FileUtils();

    private FileUtils() {
    }

    public static /* synthetic */ File fileFromByteArray$default(FileUtils fileUtils, byte[] bArr, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            str = "tempFile";
        }
        return fileUtils.fileFromByteArray(bArr, str);
    }

    public final void deleteFilesWithPrefixFromFolder(File folder, String prefix) {
        Intrinsics.checkNotNullParameter(folder, "folder");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        File[] fileArrListFiles = folder.listFiles();
        if (fileArrListFiles != null) {
            for (File file : fileArrListFiles) {
                String name = file.getName();
                Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
                if (StringsKt.startsWith$default(name, prefix, false, 2, (Object) null) && !file.delete()) {
                    Timber.INSTANCE.e("Failed to delete: " + file.getName(), new Object[0]);
                }
            }
        }
    }

    public final File fileFromByteArray(byte[] byteArray) {
        Intrinsics.checkNotNullParameter(byteArray, "byteArray");
        return fileFromByteArray$default(this, byteArray, null, 2, null);
    }

    public final byte[] fileToByteArray(File file) {
        Intrinsics.checkNotNullParameter(file, "file");
        return FilesKt.readBytes(file);
    }

    public final File fileFromByteArray(byte[] byteArray, String fileName) throws IOException {
        Intrinsics.checkNotNullParameter(byteArray, "byteArray");
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        File fileCreateTempFile = File.createTempFile(fileName, null);
        Intrinsics.checkNotNull(fileCreateTempFile);
        FilesKt.writeBytes(fileCreateTempFile, byteArray);
        return fileCreateTempFile;
    }
}
