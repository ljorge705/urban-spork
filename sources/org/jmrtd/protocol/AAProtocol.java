package org.jmrtd.protocol;

import java.security.PublicKey;
import net.sf.scuba.smartcards.CardServiceException;
import org.jmrtd.APDULevelAACapable;
import org.jmrtd.CardServiceProtocolException;
import org.jmrtd.Util;

/* loaded from: classes4.dex */
public class AAProtocol {
    private APDULevelAACapable service;
    private SecureMessagingWrapper wrapper;

    public AAProtocol(APDULevelAACapable aPDULevelAACapable, SecureMessagingWrapper secureMessagingWrapper) {
        this.service = aPDULevelAACapable;
        this.wrapper = secureMessagingWrapper;
    }

    public AAResult doAA(PublicKey publicKey, String str, String str2, byte[] bArr) throws CardServiceException {
        if (bArr != null) {
            try {
                if (bArr.length == 8) {
                    return new AAResult(publicKey, str, str2, bArr, this.service.sendInternalAuthenticate(this.wrapper, Util.getApproximateSignatureSize(publicKey), bArr));
                }
            } catch (Exception e) {
                throw new CardServiceProtocolException("Exception", 1, e);
            }
        }
        throw new IllegalArgumentException("AA failed: bad challenge");
    }
}
