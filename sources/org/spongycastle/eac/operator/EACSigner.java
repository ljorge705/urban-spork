package org.spongycastle.eac.operator;

import java.io.OutputStream;
import org.spongycastle.asn1.ASN1ObjectIdentifier;

/* loaded from: classes7.dex */
public interface EACSigner {
    OutputStream getOutputStream();

    byte[] getSignature();

    ASN1ObjectIdentifier getUsageIdentifier();
}
