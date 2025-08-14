package com.nimbusds.jose.proc;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEDecrypter;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.JWEProvider;
import java.security.Key;

/* loaded from: classes2.dex */
public interface JWEDecrypterFactory extends JWEProvider {
    JWEDecrypter createJWEDecrypter(JWEHeader jWEHeader, Key key) throws JOSEException;
}
