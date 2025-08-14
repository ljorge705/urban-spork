package com.facebook.crypto;

import java.nio.charset.Charset;

/* loaded from: classes5.dex */
public class Entity {
    private static final Charset UTF_16 = Charset.forName("UTF-16");
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private byte[] mBytes;

    public byte[] getBytes() {
        return this.mBytes;
    }

    @Deprecated
    public Entity(String str) {
        this.mBytes = str.getBytes(UTF_16);
    }

    private Entity(byte[] bArr) {
        this.mBytes = bArr;
    }

    @Deprecated
    public static Entity utf16(String str) {
        return new Entity(str);
    }

    public static Entity create(String str) {
        return new Entity(str.getBytes(UTF_8));
    }
}
