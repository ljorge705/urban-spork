package org.spongycastle.tsp.cms;

import org.spongycastle.tsp.TimeStampToken;

/* loaded from: classes7.dex */
public class ImprintDigestInvalidException extends Exception {
    private TimeStampToken token;

    public TimeStampToken getTimeStampToken() {
        return this.token;
    }

    public ImprintDigestInvalidException(String str, TimeStampToken timeStampToken) {
        super(str);
        this.token = timeStampToken;
    }
}
