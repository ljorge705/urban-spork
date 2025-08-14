package com.drew.imaging.raf;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Metadata;
import com.drew.metadata.file.FileSystemMetadataReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes5.dex */
public class RafMetadataReader {
    public static Metadata readMetadata(File file) throws IOException, JpegProcessingException {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            Metadata metadata = readMetadata(fileInputStream);
            fileInputStream.close();
            new FileSystemMetadataReader().read(file, metadata);
            return metadata;
        } catch (Throwable th) {
            fileInputStream.close();
            throw th;
        }
    }

    public static Metadata readMetadata(InputStream inputStream) throws IOException, JpegProcessingException {
        if (!inputStream.markSupported()) {
            throw new IOException("Stream must support mark/reset");
        }
        inputStream.mark(512);
        byte[] bArr = new byte[512];
        int i = inputStream.read(bArr);
        if (i == -1) {
            throw new IOException("Stream is empty");
        }
        inputStream.reset();
        int i2 = 0;
        while (true) {
            if (i2 >= i - 2) {
                break;
            }
            if (bArr[i2] == -1 && bArr[i2 + 1] == -40 && bArr[i2 + 2] == -1) {
                long j = i2;
                if (inputStream.skip(j) != j) {
                    throw new IOException("Skipping stream bytes failed");
                }
            } else {
                i2++;
            }
        }
        return JpegMetadataReader.readMetadata(inputStream);
    }

    private RafMetadataReader() throws Exception {
        throw new Exception("Not intended for instantiation");
    }
}
