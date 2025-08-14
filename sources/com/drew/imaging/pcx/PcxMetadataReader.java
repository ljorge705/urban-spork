package com.drew.imaging.pcx;

import com.drew.imaging.ImageProcessingException;
import com.drew.lang.StreamReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.file.FileSystemMetadataReader;
import com.drew.metadata.pcx.PcxReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes5.dex */
public class PcxMetadataReader {
    public static Metadata readMetadata(File file) throws IOException {
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

    public static Metadata readMetadata(InputStream inputStream) throws ImageProcessingException {
        Metadata metadata = new Metadata();
        new PcxReader().extract(new StreamReader(inputStream), metadata);
        return metadata;
    }
}
