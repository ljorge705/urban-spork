package com.drew.imaging.tiff;

import com.drew.lang.RandomAccessFileReader;
import com.drew.lang.RandomAccessReader;
import com.drew.lang.RandomAccessStreamReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifTiffHandler;
import com.drew.metadata.file.FileSystemMetadataReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

/* loaded from: classes5.dex */
public class TiffMetadataReader {
    public static Metadata readMetadata(File file) throws TiffProcessingException, IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        try {
            Metadata metadata = readMetadata(new RandomAccessFileReader(randomAccessFile));
            randomAccessFile.close();
            new FileSystemMetadataReader().read(file, metadata);
            return metadata;
        } catch (Throwable th) {
            randomAccessFile.close();
            throw th;
        }
    }

    public static Metadata readMetadata(InputStream inputStream) throws TiffProcessingException, IOException {
        return readMetadata(new RandomAccessStreamReader(inputStream));
    }

    public static Metadata readMetadata(RandomAccessReader randomAccessReader) throws Throwable {
        Metadata metadata = new Metadata();
        new TiffReader().processTiff(randomAccessReader, new ExifTiffHandler(metadata, null), 0);
        return metadata;
    }
}
