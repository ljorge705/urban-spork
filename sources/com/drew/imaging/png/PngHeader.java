package com.drew.imaging.png;

import com.drew.lang.SequentialByteArrayReader;
import java.io.IOException;

/* loaded from: classes5.dex */
public class PngHeader {
    private byte _bitsPerSample;
    private PngColorType _colorType;
    private byte _compressionType;
    private byte _filterMethod;
    private int _imageHeight;
    private int _imageWidth;
    private byte _interlaceMethod;

    public byte getBitsPerSample() {
        return this._bitsPerSample;
    }

    public PngColorType getColorType() {
        return this._colorType;
    }

    public byte getCompressionType() {
        return this._compressionType;
    }

    public byte getFilterMethod() {
        return this._filterMethod;
    }

    public int getImageHeight() {
        return this._imageHeight;
    }

    public int getImageWidth() {
        return this._imageWidth;
    }

    public byte getInterlaceMethod() {
        return this._interlaceMethod;
    }

    public PngHeader(byte[] bArr) throws PngProcessingException {
        if (bArr.length != 13) {
            throw new PngProcessingException("PNG header chunk must have 13 data bytes");
        }
        SequentialByteArrayReader sequentialByteArrayReader = new SequentialByteArrayReader(bArr);
        try {
            this._imageWidth = sequentialByteArrayReader.getInt32();
            this._imageHeight = sequentialByteArrayReader.getInt32();
            this._bitsPerSample = sequentialByteArrayReader.getInt8();
            byte int8 = sequentialByteArrayReader.getInt8();
            PngColorType pngColorTypeFromNumericValue = PngColorType.fromNumericValue(int8);
            if (pngColorTypeFromNumericValue == null) {
                throw new PngProcessingException("Unexpected PNG color type: " + ((int) int8));
            }
            this._colorType = pngColorTypeFromNumericValue;
            this._compressionType = sequentialByteArrayReader.getInt8();
            this._filterMethod = sequentialByteArrayReader.getInt8();
            this._interlaceMethod = sequentialByteArrayReader.getInt8();
        } catch (IOException e) {
            throw new PngProcessingException(e);
        }
    }
}
