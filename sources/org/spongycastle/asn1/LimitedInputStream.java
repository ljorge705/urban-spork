package org.spongycastle.asn1;

import java.io.InputStream;

/* loaded from: classes4.dex */
abstract class LimitedInputStream extends InputStream {
    protected final InputStream _in;
    private int _limit;

    int getRemaining() {
        return this._limit;
    }

    LimitedInputStream(InputStream inputStream, int i) {
        this._in = inputStream;
        this._limit = i;
    }

    protected void setParentEofDetect(boolean z) {
        InputStream inputStream = this._in;
        if (inputStream instanceof IndefiniteLengthInputStream) {
            ((IndefiniteLengthInputStream) inputStream).setEofOn00(z);
        }
    }
}
