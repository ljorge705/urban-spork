package org.spongycastle.crypto;

/* loaded from: classes.dex */
public interface CharToByteConverter {
    byte[] convert(char[] cArr);

    String getType();
}
