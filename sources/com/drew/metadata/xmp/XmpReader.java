package com.drew.metadata.xmp;

import com.adobe.internal.xmp.XMPException;
import com.adobe.internal.xmp.XMPIterator;
import com.adobe.internal.xmp.XMPMetaFactory;
import com.adobe.internal.xmp.options.ParseOptions;
import com.adobe.internal.xmp.properties.XMPPropertyInfo;
import com.drew.imaging.jpeg.JpegSegmentMetadataReader;
import com.drew.imaging.jpeg.JpegSegmentType;
import com.drew.lang.SequentialByteArrayReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.StringValue;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;

/* loaded from: classes5.dex */
public class XmpReader implements JpegSegmentMetadataReader {
    private static final String ATTRIBUTE_EXTENDED_XMP = "xmpNote:HasExtendedXMP";
    private static final int EXTENDED_XMP_GUID_LENGTH = 32;
    private static final int EXTENDED_XMP_INT_LENGTH = 4;
    private static final ParseOptions PARSE_OPTIONS = new ParseOptions().setXMPNodesToLimit(Collections.singletonMap("photoshop:DocumentAncestors", 1000));
    private static final String SCHEMA_XMP_NOTES = "http://ns.adobe.com/xmp/note/";
    private static final String XMP_EXTENSION_JPEG_PREAMBLE = "http://ns.adobe.com/xmp/extension/\u0000";
    private static final String XMP_JPEG_PREAMBLE = "http://ns.adobe.com/xap/1.0/\u0000";

    @Override // com.drew.imaging.jpeg.JpegSegmentMetadataReader
    public Iterable<JpegSegmentType> getSegmentTypes() {
        return Collections.singletonList(JpegSegmentType.APP1);
    }

    @Override // com.drew.imaging.jpeg.JpegSegmentMetadataReader
    public void readJpegSegments(Iterable<byte[]> iterable, Metadata metadata, JpegSegmentType jpegSegmentType) {
        byte[] bArrProcessExtendedXMPChunk = null;
        String extendedXMPGUID = null;
        for (byte[] bArr : iterable) {
            if (bArr.length >= 29 && (XMP_JPEG_PREAMBLE.equalsIgnoreCase(new String(bArr, 0, 29)) || "XMP".equalsIgnoreCase(new String(bArr, 0, 3)))) {
                int length = bArr.length - 29;
                byte[] bArr2 = new byte[length];
                System.arraycopy(bArr, 29, bArr2, 0, length);
                extract(bArr2, metadata);
                extendedXMPGUID = getExtendedXMPGUID(metadata);
            } else if (extendedXMPGUID != null && bArr.length >= 35 && XMP_EXTENSION_JPEG_PREAMBLE.equalsIgnoreCase(new String(bArr, 0, 35))) {
                bArrProcessExtendedXMPChunk = processExtendedXMPChunk(metadata, bArr, extendedXMPGUID, bArrProcessExtendedXMPChunk);
            }
        }
        if (bArrProcessExtendedXMPChunk != null) {
            extract(bArrProcessExtendedXMPChunk, metadata);
        }
    }

    public void extract(byte[] bArr, Metadata metadata) {
        extract(bArr, metadata, (Directory) null);
    }

    public void extract(byte[] bArr, Metadata metadata, Directory directory) {
        extract(bArr, 0, bArr.length, metadata, directory);
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0016 A[Catch: XMPException -> 0x0029, TryCatch #0 {XMPException -> 0x0029, blocks: (B:6:0x000c, B:8:0x000f, B:10:0x0025, B:9:0x0016), top: B:18:0x000c }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void extract(byte[] r2, int r3, int r4, com.drew.metadata.Metadata r5, com.drew.metadata.Directory r6) {
        /*
            r1 = this;
            com.drew.metadata.xmp.XmpDirectory r0 = new com.drew.metadata.xmp.XmpDirectory
            r0.<init>()
            if (r6 == 0) goto La
            r0.setParent(r6)
        La:
            if (r3 != 0) goto L16
            int r6 = r2.length     // Catch: com.adobe.internal.xmp.XMPException -> L29
            if (r4 != r6) goto L16
            com.adobe.internal.xmp.options.ParseOptions r3 = com.drew.metadata.xmp.XmpReader.PARSE_OPTIONS     // Catch: com.adobe.internal.xmp.XMPException -> L29
            com.adobe.internal.xmp.XMPMeta r2 = com.adobe.internal.xmp.XMPMetaFactory.parseFromBuffer(r2, r3)     // Catch: com.adobe.internal.xmp.XMPException -> L29
            goto L25
        L16:
            com.adobe.internal.xmp.impl.ByteBuffer r6 = new com.adobe.internal.xmp.impl.ByteBuffer     // Catch: com.adobe.internal.xmp.XMPException -> L29
            r6.<init>(r2, r3, r4)     // Catch: com.adobe.internal.xmp.XMPException -> L29
            java.io.InputStream r2 = r6.getByteStream()     // Catch: com.adobe.internal.xmp.XMPException -> L29
            com.adobe.internal.xmp.options.ParseOptions r3 = com.drew.metadata.xmp.XmpReader.PARSE_OPTIONS     // Catch: com.adobe.internal.xmp.XMPException -> L29
            com.adobe.internal.xmp.XMPMeta r2 = com.adobe.internal.xmp.XMPMetaFactory.parse(r2, r3)     // Catch: com.adobe.internal.xmp.XMPException -> L29
        L25:
            r0.setXMPMeta(r2)     // Catch: com.adobe.internal.xmp.XMPException -> L29
            goto L40
        L29:
            r2 = move-exception
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "Error processing XMP data: "
            r3.<init>(r4)
            java.lang.String r2 = r2.getMessage()
            java.lang.StringBuilder r2 = r3.append(r2)
            java.lang.String r2 = r2.toString()
            r0.addError(r2)
        L40:
            boolean r2 = r0.isEmpty()
            if (r2 != 0) goto L49
            r5.addDirectory(r0)
        L49:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.drew.metadata.xmp.XmpReader.extract(byte[], int, int, com.drew.metadata.Metadata, com.drew.metadata.Directory):void");
    }

    public void extract(String str, Metadata metadata) {
        extract(str, metadata, (Directory) null);
    }

    public void extract(StringValue stringValue, Metadata metadata) {
        extract(stringValue.getBytes(), metadata, (Directory) null);
    }

    public void extract(String str, Metadata metadata, Directory directory) {
        XmpDirectory xmpDirectory = new XmpDirectory();
        if (directory != null) {
            xmpDirectory.setParent(directory);
        }
        try {
            xmpDirectory.setXMPMeta(XMPMetaFactory.parseFromString(str, PARSE_OPTIONS));
        } catch (XMPException e) {
            xmpDirectory.addError("Error processing XMP data: " + e.getMessage());
        }
        if (xmpDirectory.isEmpty()) {
            return;
        }
        metadata.addDirectory(xmpDirectory);
    }

    private static String getExtendedXMPGUID(Metadata metadata) {
        Iterator it = metadata.getDirectoriesOfType(XmpDirectory.class).iterator();
        while (it.hasNext()) {
            try {
                XMPIterator it2 = ((XmpDirectory) it.next()).getXMPMeta().iterator("http://ns.adobe.com/xmp/note/", null, null);
                if (it2 != null) {
                    while (it2.hasNext()) {
                        XMPPropertyInfo xMPPropertyInfo = (XMPPropertyInfo) it2.next();
                        if (ATTRIBUTE_EXTENDED_XMP.equals(xMPPropertyInfo.getPath())) {
                            return xMPPropertyInfo.getValue();
                        }
                    }
                }
            } catch (XMPException unused) {
            }
        }
        return null;
    }

    private static byte[] processExtendedXMPChunk(Metadata metadata, byte[] bArr, String str, byte[] bArr2) {
        int length = bArr.length;
        if (length >= 75) {
            try {
                SequentialByteArrayReader sequentialByteArrayReader = new SequentialByteArrayReader(bArr);
                sequentialByteArrayReader.skip(35);
                if (str.equals(sequentialByteArrayReader.getString(32))) {
                    int uInt32 = (int) sequentialByteArrayReader.getUInt32();
                    int uInt322 = (int) sequentialByteArrayReader.getUInt32();
                    if (bArr2 == null) {
                        bArr2 = new byte[uInt32];
                    }
                    if (bArr2.length == uInt32) {
                        System.arraycopy(bArr, 75, bArr2, uInt322, length - 75);
                    } else {
                        XmpDirectory xmpDirectory = new XmpDirectory();
                        xmpDirectory.addError(String.format("Inconsistent length for the Extended XMP buffer: %d instead of %d", Integer.valueOf(uInt32), Integer.valueOf(bArr2.length)));
                        metadata.addDirectory(xmpDirectory);
                    }
                }
            } catch (IOException e) {
                XmpDirectory xmpDirectory2 = new XmpDirectory();
                xmpDirectory2.addError(e.getMessage());
                metadata.addDirectory(xmpDirectory2);
            }
        }
        return bArr2;
    }
}
