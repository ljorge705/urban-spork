package com.drew.metadata.iptc;

import com.drew.imaging.jpeg.JpegSegmentMetadataReader;
import com.drew.imaging.jpeg.JpegSegmentType;
import com.drew.lang.SequentialByteArrayReader;
import com.drew.lang.SequentialReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import java.io.IOException;
import java.util.Collections;

/* loaded from: classes5.dex */
public class IptcReader implements JpegSegmentMetadataReader {
    private static final byte IptcMarkerByte = 28;

    @Override // com.drew.imaging.jpeg.JpegSegmentMetadataReader
    public Iterable<JpegSegmentType> getSegmentTypes() {
        return Collections.singletonList(JpegSegmentType.APPD);
    }

    @Override // com.drew.imaging.jpeg.JpegSegmentMetadataReader
    public void readJpegSegments(Iterable<byte[]> iterable, Metadata metadata, JpegSegmentType jpegSegmentType) {
        for (byte[] bArr : iterable) {
            if (bArr.length != 0 && bArr[0] == 28) {
                extract(new SequentialByteArrayReader(bArr), metadata, bArr.length);
            }
        }
    }

    public void extract(SequentialReader sequentialReader, Metadata metadata, long j) {
        extract(sequentialReader, metadata, j, null);
    }

    public void extract(SequentialReader sequentialReader, Metadata metadata, long j, Directory directory) {
        IptcDirectory iptcDirectory = new IptcDirectory();
        metadata.addDirectory(iptcDirectory);
        if (directory != null) {
            iptcDirectory.setParent(directory);
        }
        int i = 0;
        while (i < j) {
            try {
                short uInt8 = sequentialReader.getUInt8();
                int i2 = i + 1;
                if (uInt8 != 28) {
                    if (i2 != j) {
                        iptcDirectory.addError("Invalid IPTC tag marker at offset " + i + ". Expected '0x" + Integer.toHexString(28) + "' but got '0x" + Integer.toHexString(uInt8) + "'.");
                        return;
                    }
                    return;
                }
                if (i + 5 > j) {
                    iptcDirectory.addError("Too few bytes remain for a valid IPTC tag");
                    return;
                }
                try {
                    short uInt82 = sequentialReader.getUInt8();
                    short uInt83 = sequentialReader.getUInt8();
                    int uInt16 = sequentialReader.getUInt16();
                    if (uInt16 > 32767) {
                        uInt16 = ((uInt16 & 32767) << 16) | sequentialReader.getUInt16();
                        i2 = i + 3;
                    }
                    int i3 = uInt16;
                    i = i2 + 4 + i3;
                    if (i > j) {
                        iptcDirectory.addError("Data for tag extends beyond end of IPTC segment");
                        return;
                    } else {
                        try {
                            processTag(sequentialReader, iptcDirectory, uInt82, uInt83, i3);
                        } catch (IOException unused) {
                            iptcDirectory.addError("Error processing IPTC tag");
                            return;
                        }
                    }
                } catch (IOException unused2) {
                    iptcDirectory.addError("IPTC data segment ended mid-way through tag descriptor");
                    return;
                }
            } catch (IOException unused3) {
                iptcDirectory.addError("Unable to read starting byte of IPTC tag");
                return;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0049  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void processTag(com.drew.lang.SequentialReader r4, com.drew.metadata.Directory r5, int r6, int r7, int r8) throws java.io.IOException {
        /*
            r3 = this;
            int r6 = r6 << 8
            r6 = r6 | r7
            if (r8 != 0) goto Lb
            java.lang.String r4 = ""
            r5.setString(r6, r4)
            return
        Lb:
            r7 = 256(0x100, float:3.59E-43)
            r0 = 346(0x15a, float:4.85E-43)
            r1 = 1
            if (r6 == r7) goto L49
            r7 = 278(0x116, float:3.9E-43)
            if (r6 == r7) goto L49
            if (r6 == r0) goto L36
            r7 = 378(0x17a, float:5.3E-43)
            if (r6 == r7) goto L49
            r7 = 512(0x200, float:7.17E-43)
            if (r6 == r7) goto L49
            r7 = 522(0x20a, float:7.31E-43)
            if (r6 == r7) goto L29
            r7 = 582(0x246, float:8.16E-43)
            if (r6 == r7) goto L49
            goto L59
        L29:
            short r7 = r4.getUInt8()
            r5.setInt(r6, r7)
            int r8 = r8 - r1
            long r5 = (long) r8
            r4.skip(r5)
            return
        L36:
            byte[] r4 = r4.getBytes(r8)
            java.lang.String r7 = com.drew.metadata.iptc.Iso2022Converter.convertISO2022CharsetToJavaCharset(r4)
            if (r7 != 0) goto L45
            java.lang.String r7 = new java.lang.String
            r7.<init>(r4)
        L45:
            r5.setString(r6, r7)
            return
        L49:
            r7 = 2
            if (r8 < r7) goto L59
            int r0 = r4.getUInt16()
            int r8 = r8 - r7
            long r7 = (long) r8
            r4.skip(r7)
            r5.setInt(r6, r0)
            return
        L59:
            java.lang.String r7 = r5.getString(r0)
            r0 = 0
            if (r7 == 0) goto L65
            java.nio.charset.Charset r2 = java.nio.charset.Charset.forName(r7)     // Catch: java.lang.Throwable -> L65
            goto L66
        L65:
            r2 = r0
        L66:
            if (r7 == 0) goto L6d
            com.drew.metadata.StringValue r4 = r4.getStringValue(r8, r2)
            goto L84
        L6d:
            byte[] r4 = r4.getBytes(r8)
            java.nio.charset.Charset r7 = com.drew.metadata.iptc.Iso2022Converter.guessCharSet(r4)
            if (r7 == 0) goto L7e
            com.drew.metadata.StringValue r8 = new com.drew.metadata.StringValue
            r8.<init>(r4, r7)
            r4 = r8
            goto L84
        L7e:
            com.drew.metadata.StringValue r7 = new com.drew.metadata.StringValue
            r7.<init>(r4, r0)
            r4 = r7
        L84:
            boolean r7 = r5.containsTag(r6)
            if (r7 == 0) goto La5
            com.drew.metadata.StringValue[] r7 = r5.getStringValueArray(r6)
            if (r7 != 0) goto L93
            com.drew.metadata.StringValue[] r7 = new com.drew.metadata.StringValue[r1]
            goto L9d
        L93:
            int r8 = r7.length
            int r8 = r8 + r1
            com.drew.metadata.StringValue[] r8 = new com.drew.metadata.StringValue[r8]
            int r0 = r7.length
            r2 = 0
            java.lang.System.arraycopy(r7, r2, r8, r2, r0)
            r7 = r8
        L9d:
            int r8 = r7.length
            int r8 = r8 - r1
            r7[r8] = r4
            r5.setStringValueArray(r6, r7)
            goto La8
        La5:
            r5.setStringValue(r6, r4)
        La8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.drew.metadata.iptc.IptcReader.processTag(com.drew.lang.SequentialReader, com.drew.metadata.Directory, int, int, int):void");
    }
}
