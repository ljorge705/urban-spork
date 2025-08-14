package com.drew.imaging.gif;

import com.drew.lang.StreamReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.file.FileSystemMetadataReader;
import com.drew.metadata.gif.GifReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes5.dex */
public class GifMetadataReader {
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

    public static Metadata readMetadata(InputStream inputStream) {
        Metadata metadata = new Metadata();
        new GifReader().extract(new StreamReader(inputStream), metadata);
        return metadata;
    }
}
