package com.drew.imaging.mp3;

import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import com.drew.metadata.file.FileSystemMetadataReader;
import com.drew.metadata.mp3.Mp3Reader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes5.dex */
public class Mp3MetadataReader {
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

    public static Metadata readMetadata(InputStream inputStream) throws ImageProcessingException, IOException {
        Metadata metadata = new Metadata();
        new Mp3Reader().extract(inputStream, metadata);
        return metadata;
    }
}
