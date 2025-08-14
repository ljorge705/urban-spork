package com.drew.imaging.quicktime;

import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import com.drew.metadata.file.FileSystemMetadataReader;
import com.drew.metadata.mov.QuickTimeAtomHandler;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes5.dex */
public class QuickTimeMetadataReader {
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

    public static Metadata readMetadata(InputStream inputStream) {
        Metadata metadata = new Metadata();
        QuickTimeReader.extract(inputStream, new QuickTimeAtomHandler(metadata));
        return metadata;
    }
}
