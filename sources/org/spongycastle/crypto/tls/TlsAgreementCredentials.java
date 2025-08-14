package org.spongycastle.crypto.tls;

import java.io.IOException;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;

/* loaded from: classes7.dex */
public interface TlsAgreementCredentials extends TlsCredentials {
    byte[] generateAgreement(AsymmetricKeyParameter asymmetricKeyParameter) throws IOException;
}
