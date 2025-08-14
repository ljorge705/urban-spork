package com.drew.metadata.webp;

import com.drew.imaging.riff.RiffHandler;
import com.drew.lang.ByteArrayReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifReader;
import com.drew.metadata.icc.IccReader;
import com.drew.metadata.xmp.XmpReader;
import java.io.IOException;

/* loaded from: classes5.dex */
public class WebpRiffHandler implements RiffHandler {
    private final Metadata _metadata;

    @Override // com.drew.imaging.riff.RiffHandler
    public boolean shouldAcceptList(String str) {
        return false;
    }

    public WebpRiffHandler(Metadata metadata) {
        this._metadata = metadata;
    }

    @Override // com.drew.imaging.riff.RiffHandler
    public boolean shouldAcceptRiffIdentifier(String str) {
        return str.equals(WebpDirectory.FORMAT);
    }

    @Override // com.drew.imaging.riff.RiffHandler
    public boolean shouldAcceptChunk(String str) {
        return str.equals(WebpDirectory.CHUNK_VP8X) || str.equals(WebpDirectory.CHUNK_VP8L) || str.equals(WebpDirectory.CHUNK_VP8) || str.equals(WebpDirectory.CHUNK_EXIF) || str.equals(WebpDirectory.CHUNK_ICCP) || str.equals(WebpDirectory.CHUNK_XMP);
    }

    @Override // com.drew.imaging.riff.RiffHandler
    public void processChunk(String str, byte[] bArr) {
        WebpDirectory webpDirectory = new WebpDirectory();
        if (str.equals(WebpDirectory.CHUNK_EXIF)) {
            new ExifReader().extract(new ByteArrayReader(bArr), this._metadata);
            return;
        }
        if (str.equals(WebpDirectory.CHUNK_ICCP)) {
            new IccReader().extract(new ByteArrayReader(bArr), this._metadata);
            return;
        }
        if (str.equals(WebpDirectory.CHUNK_XMP)) {
            new XmpReader().extract(bArr, this._metadata);
            return;
        }
        if (str.equals(WebpDirectory.CHUNK_VP8X) && bArr.length == 10) {
            ByteArrayReader byteArrayReader = new ByteArrayReader(bArr);
            byteArrayReader.setMotorolaByteOrder(false);
            try {
                boolean bit = byteArrayReader.getBit(1);
                boolean bit2 = byteArrayReader.getBit(4);
                int int24 = byteArrayReader.getInt24(4);
                int int242 = byteArrayReader.getInt24(7);
                webpDirectory.setInt(2, int24 + 1);
                webpDirectory.setInt(1, int242 + 1);
                webpDirectory.setBoolean(3, bit2);
                webpDirectory.setBoolean(4, bit);
                this._metadata.addDirectory(webpDirectory);
                return;
            } catch (IOException e) {
                e.printStackTrace(System.err);
                return;
            }
        }
        if (str.equals(WebpDirectory.CHUNK_VP8L) && bArr.length > 4) {
            ByteArrayReader byteArrayReader2 = new ByteArrayReader(bArr);
            byteArrayReader2.setMotorolaByteOrder(false);
            try {
                if (byteArrayReader2.getInt8(0) != 47) {
                    return;
                }
                short uInt8 = byteArrayReader2.getUInt8(1);
                short uInt82 = byteArrayReader2.getUInt8(2);
                int uInt83 = ((byteArrayReader2.getUInt8(4) & 15) << 10) | (byteArrayReader2.getUInt8(3) << 2) | ((uInt82 & 192) >> 6);
                webpDirectory.setInt(2, (uInt8 | ((uInt82 & 63) << 8)) + 1);
                webpDirectory.setInt(1, uInt83 + 1);
                this._metadata.addDirectory(webpDirectory);
                return;
            } catch (IOException e2) {
                e2.printStackTrace(System.err);
                return;
            }
        }
        if (!str.equals(WebpDirectory.CHUNK_VP8) || bArr.length <= 9) {
            return;
        }
        ByteArrayReader byteArrayReader3 = new ByteArrayReader(bArr);
        byteArrayReader3.setMotorolaByteOrder(false);
        try {
            if (byteArrayReader3.getUInt8(3) == 157 && byteArrayReader3.getUInt8(4) == 1 && byteArrayReader3.getUInt8(5) == 42) {
                int uInt16 = byteArrayReader3.getUInt16(6);
                int uInt162 = byteArrayReader3.getUInt16(8);
                webpDirectory.setInt(2, uInt16);
                webpDirectory.setInt(1, uInt162);
                this._metadata.addDirectory(webpDirectory);
            }
        } catch (IOException e3) {
            webpDirectory.addError(e3.getMessage());
        }
    }
}
