package com.drew.imaging.wav;

import com.drew.imaging.riff.RiffProcessingException;
import com.drew.imaging.riff.RiffReader;
import com.drew.lang.StreamReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.file.FileSystemMetadataReader;
import com.drew.metadata.wav.WavRiffHandler;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes5.dex */
public class WavMetadataReader {
    public static Metadata readMetadata(File file) throws RiffProcessingException, IOException {
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

    public static Metadata readMetadata(InputStream inputStream) throws RiffProcessingException, IOException {
        Metadata metadata = new Metadata();
        new RiffReader().processRiff(new StreamReader(inputStream), new WavRiffHandler(metadata));
        return metadata;
    }
}
