package com.nimbusds.jose;

import com.nimbusds.jose.util.Base64URL;
import java.security.Signature;

/* loaded from: classes2.dex */
public interface CompletableJWSObjectSigning {
    Base64URL complete() throws JOSEException;

    Signature getInitializedSignature();
}
