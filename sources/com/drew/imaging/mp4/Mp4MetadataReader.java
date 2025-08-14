package com.drew.imaging.mp4;

import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import com.drew.metadata.file.FileSystemMetadataReader;
import com.drew.metadata.mp4.Mp4BoxHandler;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes5.dex */
public class Mp4MetadataReader {
    public static Metadata readMetadata(File file) throws ImageProcessingException, IOException {
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

    public static Metadata readMetadata(InputStream inputStream) throws IOException {
        Metadata metadata = new Metadata();
        Mp4Reader.extract(inputStream, new Mp4BoxHandler(metadata));
        return metadata;
    }
}
