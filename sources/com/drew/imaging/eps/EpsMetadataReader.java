package com.drew.imaging.eps;

import com.drew.metadata.Metadata;
import com.drew.metadata.eps.EpsReader;
import com.drew.metadata.file.FileSystemMetadataReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/* loaded from: classes5.dex */
public class EpsMetadataReader {
    public static Metadata readMetadata(File file) throws Throwable {
        Metadata metadata = new Metadata();
        new EpsReader().extract(new FileInputStream(file), metadata);
        new FileSystemMetadataReader().read(file, metadata);
        return metadata;
    }

    public static Metadata readMetadata(InputStream inputStream) throws Throwable {
        Metadata metadata = new Metadata();
        new EpsReader().extract(inputStream, metadata);
        return metadata;
    }
}
