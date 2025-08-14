package org.spongycastle.dvcs;

import org.spongycastle.asn1.x509.DigestInfo;

/* loaded from: classes7.dex */
public class MessageImprint {
    private final DigestInfo messageImprint;

    public DigestInfo toASN1Structure() {
        return this.messageImprint;
    }

    public MessageImprint(DigestInfo digestInfo) {
        this.messageImprint = digestInfo;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof MessageImprint) {
            return this.messageImprint.equals(((MessageImprint) obj).messageImprint);
        }
        return false;
    }

    public int hashCode() {
        return this.messageImprint.hashCode();
    }
}
