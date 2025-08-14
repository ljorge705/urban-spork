package org.tensorflow.lite.support.common;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import org.tensorflow.lite.support.common.internal.SupportPreconditions;

/* loaded from: classes7.dex */
public class FileUtil {
    private FileUtil() {
    }

    public static List<String> loadLabels(Context context, String filePath) throws IOException {
        return loadLabels(context, filePath, Charset.defaultCharset());
    }

    public static List<String> loadLabels(Context context, String filePath, Charset cs) throws IOException {
        SupportPreconditions.checkNotNull(context, "Context cannot be null.");
        SupportPreconditions.checkNotNull(filePath, "File path cannot be null.");
        InputStream inputStreamOpen = context.getAssets().open(filePath);
        try {
            List<String> listLoadLabels = loadLabels(inputStreamOpen, cs);
            if (inputStreamOpen != null) {
                inputStreamOpen.close();
            }
            return listLoadLabels;
        } catch (Throwable th) {
            if (inputStreamOpen != null) {
                try {
                    inputStreamOpen.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public static List<String> loadLabels(InputStream inputStream) throws IOException {
        return loadLabels(inputStream, Charset.defaultCharset());
    }

    public static List<String> loadLabels(InputStream inputStream, Charset cs) throws IOException {
        ArrayList arrayList = new ArrayList();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, cs));
        while (true) {
            try {
                String line = bufferedReader.readLine();
                if (line != null) {
                    if (line.trim().length() > 0) {
                        arrayList.add(line);
                    }
                } else {
                    bufferedReader.close();
                    return arrayList;
                }
            } catch (Throwable th) {
                try {
                    bufferedReader.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
    }

    public static List<String> loadSingleColumnTextFile(Context context, String filePath, Charset cs) throws IOException {
        return loadLabels(context, filePath, cs);
    }

    public static List<String> loadSingleColumnTextFile(InputStream inputStream, Charset cs) throws IOException {
        return loadLabels(inputStream, cs);
    }

    public static MappedByteBuffer loadMappedFile(Context context, String filePath) throws IOException {
        SupportPreconditions.checkNotNull(context, "Context should not be null.");
        SupportPreconditions.checkNotNull(filePath, "File path cannot be null.");
        AssetFileDescriptor assetFileDescriptorOpenFd = context.getAssets().openFd(filePath);
        try {
            FileInputStream fileInputStream = new FileInputStream(assetFileDescriptorOpenFd.getFileDescriptor());
            try {
                MappedByteBuffer map = fileInputStream.getChannel().map(FileChannel.MapMode.READ_ONLY, assetFileDescriptorOpenFd.getStartOffset(), assetFileDescriptorOpenFd.getDeclaredLength());
                fileInputStream.close();
                if (assetFileDescriptorOpenFd != null) {
                    assetFileDescriptorOpenFd.close();
                }
                return map;
            } finally {
            }
        } catch (Throwable th) {
            if (assetFileDescriptorOpenFd != null) {
                try {
                    assetFileDescriptorOpenFd.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public static byte[] loadByteFromFile(Context context, String filePath) throws IOException {
        MappedByteBuffer mappedByteBufferLoadMappedFile = loadMappedFile(context, filePath);
        byte[] bArr = new byte[mappedByteBufferLoadMappedFile.remaining()];
        mappedByteBufferLoadMappedFile.get(bArr);
        return bArr;
    }
}
