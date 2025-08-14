package com.drew.metadata.jpeg;

import com.drew.imaging.jpeg.JpegSegmentMetadataReader;
import com.drew.imaging.jpeg.JpegSegmentType;
import com.drew.lang.SequentialByteArrayReader;
import com.drew.lang.SequentialReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.jpeg.HuffmanTablesDirectory;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;

/* loaded from: classes5.dex */
public class JpegDhtReader implements JpegSegmentMetadataReader {
    @Override // com.drew.imaging.jpeg.JpegSegmentMetadataReader
    public Iterable<JpegSegmentType> getSegmentTypes() {
        return Collections.singletonList(JpegSegmentType.DHT);
    }

    @Override // com.drew.imaging.jpeg.JpegSegmentMetadataReader
    public void readJpegSegments(Iterable<byte[]> iterable, Metadata metadata, JpegSegmentType jpegSegmentType) {
        Iterator<byte[]> it = iterable.iterator();
        while (it.hasNext()) {
            extract(new SequentialByteArrayReader(it.next()), metadata);
        }
    }

    public void extract(SequentialReader sequentialReader, Metadata metadata) {
        HuffmanTablesDirectory huffmanTablesDirectory = (HuffmanTablesDirectory) metadata.getFirstDirectoryOfType(HuffmanTablesDirectory.class);
        if (huffmanTablesDirectory == null) {
            huffmanTablesDirectory = new HuffmanTablesDirectory();
            metadata.addDirectory(huffmanTablesDirectory);
        }
        while (sequentialReader.available() > 0) {
            try {
                byte b = sequentialReader.getByte();
                HuffmanTablesDirectory.HuffmanTable.HuffmanTableClass huffmanTableClassTypeOf = HuffmanTablesDirectory.HuffmanTable.HuffmanTableClass.typeOf((b & 240) >> 4);
                int i = b & 15;
                byte[] bytes = getBytes(sequentialReader, 16);
                int i2 = 0;
                for (byte b2 : bytes) {
                    i2 += b2 & 255;
                }
                huffmanTablesDirectory.getTables().add(new HuffmanTablesDirectory.HuffmanTable(huffmanTableClassTypeOf, i, bytes, getBytes(sequentialReader, i2)));
            } catch (IOException e) {
                huffmanTablesDirectory.addError(e.getMessage());
            }
        }
        huffmanTablesDirectory.setInt(1, huffmanTablesDirectory.getTables().size());
    }

    private byte[] getBytes(SequentialReader sequentialReader, int i) throws IOException {
        byte b;
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            byte b2 = sequentialReader.getByte();
            if ((b2 & 255) == 255 && (b = sequentialReader.getByte()) != 0) {
                throw new IOException("Marker " + JpegSegmentType.fromByte(b) + " found inside DHT segment");
            }
            bArr[i2] = b2;
        }
        return bArr;
    }
}
