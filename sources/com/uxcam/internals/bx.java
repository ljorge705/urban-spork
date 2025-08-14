package com.uxcam.internals;

import android.security.keystore.KeyGenParameterSpec;
import android.util.Base64;
import com.google.android.gms.stats.CodePackage;
import com.oblador.keychain.cipherStorage.CipherStorageBase;
import com.oblador.keychain.cipherStorage.CipherStorageKeystoreAesCbc;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;

/* loaded from: classes6.dex */
public final class bx extends bw {
    public final KeyStore d;

    public bx() throws NoSuchAlgorithmException, IOException, KeyStoreException, CertificateException {
        try {
            KeyStore keyStore = KeyStore.getInstance(CipherStorageBase.KEYSTORE_TYPE);
            this.d = keyStore;
            keyStore.load(null);
            this.f104a = true;
        } catch (IOException | KeyStoreException | NoSuchAlgorithmException | CertificateException unused) {
            this.f104a = false;
        }
    }

    public final SecretKey a() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException {
        KeyStore keyStore = this.d;
        if (keyStore != null && keyStore.containsAlias("fldsjfodasjifudslfjdsaofshaufihadsf")) {
            return ((KeyStore.SecretKeyEntry) this.d.getEntry("fldsjfodasjifudslfjdsaofshaufihadsf", null)).getSecretKey();
        }
        KeyGenerator keyGenerator = KeyGenerator.getInstance(CipherStorageKeystoreAesCbc.ALGORITHM_AES, CipherStorageBase.KEYSTORE_TYPE);
        keyGenerator.init(new KeyGenParameterSpec.Builder("fldsjfodasjifudslfjdsaofshaufihadsf", 3).setBlockModes(CodePackage.GCM).setEncryptionPaddings("NoPadding").build());
        return keyGenerator.generateKey();
    }

    public final String a(String str, byte[] bArr) throws InvalidKeyException, InvalidAlgorithmParameterException {
        if (!this.f104a || this.d == null) {
            return str;
        }
        try {
            this.c = Cipher.getInstance("AES/GCM/NoPadding");
            this.c.init(2, ((KeyStore.SecretKeyEntry) this.d.getEntry("fldsjfodasjifudslfjdsaofshaufihadsf", null)).getSecretKey(), new GCMParameterSpec(128, bArr));
            return new String(this.c.doFinal(Base64.decode(str, 2)), "UTF-8");
        } catch (Exception unused) {
            return "";
        }
    }
}
