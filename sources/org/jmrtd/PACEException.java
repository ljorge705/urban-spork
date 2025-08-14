package org.jmrtd;

@Deprecated
/* loaded from: classes4.dex */
public class PACEException extends CardServiceProtocolException {
    private static final long serialVersionUID = 8383980807753919040L;

    public PACEException(String str, int i) {
        super(str, i);
    }

    public PACEException(String str, int i, Throwable th) {
        super(str, i, th);
    }

    public PACEException(String str, int i, int i2) {
        super(str, i, i2);
    }

    public PACEException(String str, int i, Throwable th, int i2) {
        super(str, i, th, i2);
    }
}
