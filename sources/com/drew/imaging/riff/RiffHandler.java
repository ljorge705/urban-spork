package com.drew.imaging.riff;

/* loaded from: classes5.dex */
public interface RiffHandler {
    void processChunk(String str, byte[] bArr);

    boolean shouldAcceptChunk(String str);

    boolean shouldAcceptList(String str);

    boolean shouldAcceptRiffIdentifier(String str);
}
