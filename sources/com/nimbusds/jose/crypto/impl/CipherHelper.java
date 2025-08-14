package com.nimbusds.jose.crypto.impl;

import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
/* loaded from: classes2.dex */
public class CipherHelper {
    public static Cipher getInstance(String str, Provider provider) throws NoSuchPaddingException, NoSuchAlgorithmException {
        if (provider == null) {
            return Cipher.getInstance(str);
        }
        return Cipher.getInstance(str, provider);
    }
}
