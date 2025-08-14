package com.drew.imaging.png;

/* loaded from: classes5.dex */
public class PngChunk {
    private final byte[] _bytes;
    private final PngChunkType _chunkType;

    public byte[] getBytes() {
        return this._bytes;
    }

    public PngChunkType getType() {
        return this._chunkType;
    }

    public PngChunk(PngChunkType pngChunkType, byte[] bArr) {
        this._chunkType = pngChunkType;
        this._bytes = bArr;
    }
}
