package org.spongycastle.eac.jcajce;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/* loaded from: classes7.dex */
interface EACHelper {
    KeyFactory createKeyFactory(String str) throws NoSuchAlgorithmException, NoSuchProviderException;
}
