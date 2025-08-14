package com.drew.imaging.heif;

import com.drew.metadata.Metadata;
import com.drew.metadata.heif.HeifBoxHandler;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.DataFormatException;

/* loaded from: classes5.dex */
public class HeifMetadataReader {
    public static Metadata readMetadata(InputStream inputStream) throws IOException {
        try {
            Metadata metadata = new Metadata();
            new HeifReader().extract(metadata, inputStream, new HeifBoxHandler(metadata));
            return metadata;
        } catch (DataFormatException e) {
            e.printStackTrace();
            return null;
        }
    }
}
