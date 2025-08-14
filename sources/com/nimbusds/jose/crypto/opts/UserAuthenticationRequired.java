package com.nimbusds.jose.crypto.opts;

import com.nimbusds.jose.JWSSignerOption;
import net.jcip.annotations.Immutable;

@Immutable
/* loaded from: classes2.dex */
public final class UserAuthenticationRequired implements JWSSignerOption {
    private static final UserAuthenticationRequired SINGLETON = new UserAuthenticationRequired();

    public static UserAuthenticationRequired getInstance() {
        return SINGLETON;
    }

    public String toString() {
        return "UserAuthenticationRequired";
    }

    private UserAuthenticationRequired() {
    }
}
