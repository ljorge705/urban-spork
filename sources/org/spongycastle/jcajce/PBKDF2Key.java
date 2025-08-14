package org.spongycastle.jcajce;

import org.spongycastle.crypto.CharToByteConverter;
import org.spongycastle.util.Arrays;

/* loaded from: classes7.dex */
public class PBKDF2Key implements PBKDFKey {
    private final CharToByteConverter converter;
    private final char[] password;

    @Override // java.security.Key
    public String getAlgorithm() {
        return "PBKDF2";
    }

    public char[] getPassword() {
        return this.password;
    }

    public PBKDF2Key(char[] cArr, CharToByteConverter charToByteConverter) {
        this.password = Arrays.clone(cArr);
        this.converter = charToByteConverter;
    }

    @Override // java.security.Key
    public String getFormat() {
        return this.converter.getType();
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        return this.converter.convert(this.password);
    }
}
