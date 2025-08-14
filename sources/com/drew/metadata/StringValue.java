package com.drew.metadata;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/* loaded from: classes5.dex */
public final class StringValue {
    private final byte[] _bytes;
    private final Charset _charset;

    public byte[] getBytes() {
        return this._bytes;
    }

    public Charset getCharset() {
        return this._charset;
    }

    public StringValue(byte[] bArr, Charset charset) {
        this._bytes = bArr;
        this._charset = charset;
    }

    public String toString() {
        return toString(this._charset);
    }

    public String toString(Charset charset) {
        if (charset != null) {
            try {
                return new String(this._bytes, charset.name());
            } catch (UnsupportedEncodingException unused) {
            }
        }
        return new String(this._bytes);
    }
}
