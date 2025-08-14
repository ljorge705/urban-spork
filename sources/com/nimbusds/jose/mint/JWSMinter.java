package com.nimbusds.jose.mint;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.proc.SecurityContext;

/* loaded from: classes2.dex */
public interface JWSMinter<C extends SecurityContext> {
    JWSObject mint(JWSHeader jWSHeader, Payload payload, C c) throws JOSEException;
}
