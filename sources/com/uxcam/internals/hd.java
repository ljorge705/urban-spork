package com.uxcam.internals;

import com.oblador.keychain.cipherStorage.CipherStorageKeystoreAesCbc;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes6.dex */
public final class hd {

    /* renamed from: a, reason: collision with root package name */
    public final byte[] f189a = new byte[16];
    public final SecretKeySpec b = a();
    public final IvParameterSpec c;

    public hd() {
        byte[] bArr = new byte[16];
        new SecureRandom().nextBytes(bArr);
        this.c = new IvParameterSpec(bArr);
    }

    public final SecretKeySpec a() {
        new SecureRandom().nextBytes(this.f189a);
        return new SecretKeySpec(this.f189a, CipherStorageKeystoreAesCbc.ALGORITHM_AES);
    }

    public final CipherOutputStream a(OutputStream outputStream) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(1, this.b, this.c);
            return new CipherOutputStream(outputStream, cipher);
        } catch (Exception e) {
            e.printStackTrace();
            fj fjVarB = new fj().b("UXCamAES::createCypherOutputStream()");
            fjVarB.a("reason", e.getMessage());
            fjVarB.a(2);
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0049  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String b() throws javax.crypto.BadPaddingException, javax.crypto.NoSuchPaddingException, java.security.spec.InvalidKeySpecException, javax.crypto.IllegalBlockSizeException, java.security.NoSuchAlgorithmException, java.security.InvalidKeyException {
        /*
            r7 = this;
            java.lang.String r0 = ""
            r1 = 0
            byte[] r2 = new byte[r1]
            javax.crypto.spec.IvParameterSpec r3 = r7.c     // Catch: java.lang.Exception -> L3b
            byte[] r3 = r3.getIV()     // Catch: java.lang.Exception -> L3b
            java.lang.String r4 = "RSA/ECB/PKCS1Padding"
            javax.crypto.Cipher r4 = javax.crypto.Cipher.getInstance(r4)     // Catch: java.lang.Exception -> L3b
            java.lang.String r5 = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDNUf8CVU/4PRJebkLWYKQIMAiN\nl8n/7/F76ExbRAC8B9SxjU+weoDH25P41j3NWAV6K1t3R5Ws7NPre524akdwFqTH\nhJzkFTHpvSqjxfqTbLepDkhInppZDMvpX6INOBGZQwEdaV37QgLp6cgfsK2oRhur\nUGCDTwVQhY8SdO6riQIDAQAB"
            java.security.spec.X509EncodedKeySpec r6 = new java.security.spec.X509EncodedKeySpec     // Catch: java.security.spec.InvalidKeySpecException -> L27 java.security.NoSuchAlgorithmException -> L2c java.lang.Exception -> L3b
            byte[] r5 = android.util.Base64.decode(r5, r1)     // Catch: java.security.spec.InvalidKeySpecException -> L27 java.security.NoSuchAlgorithmException -> L2c java.lang.Exception -> L3b
            r6.<init>(r5)     // Catch: java.security.spec.InvalidKeySpecException -> L27 java.security.NoSuchAlgorithmException -> L2c java.lang.Exception -> L3b
            java.lang.String r5 = "RSA"
            java.security.KeyFactory r5 = java.security.KeyFactory.getInstance(r5)     // Catch: java.security.spec.InvalidKeySpecException -> L27 java.security.NoSuchAlgorithmException -> L2c java.lang.Exception -> L3b
            java.security.PublicKey r5 = r5.generatePublic(r6)     // Catch: java.security.spec.InvalidKeySpecException -> L27 java.security.NoSuchAlgorithmException -> L2c java.lang.Exception -> L3b
            goto L31
        L27:
            r5 = move-exception
            r5.printStackTrace()     // Catch: java.lang.Exception -> L3b
            goto L30
        L2c:
            r5 = move-exception
            r5.printStackTrace()     // Catch: java.lang.Exception -> L3b
        L30:
            r5 = 0
        L31:
            r6 = 1
            r4.init(r6, r5)     // Catch: java.lang.Exception -> L3b
            byte[] r2 = r4.doFinal(r3)     // Catch: java.lang.Exception -> L3b
            r3 = r0
            goto L43
        L3b:
            r3 = move-exception
            r3.printStackTrace()
            java.lang.String r3 = r3.getMessage()
        L43:
            boolean r4 = android.text.TextUtils.isEmpty(r3)
            if (r4 != 0) goto L5d
            com.uxcam.internals.fj r4 = new com.uxcam.internals.fj
            r4.<init>()
            java.lang.String r5 = "UXCamAES::rsaEncryptedIv()"
            com.uxcam.internals.fj r4 = r4.b(r5)
            java.lang.String r5 = "reason"
            r4.a(r5, r3)
            r3 = 2
            r4.a(r3)
        L5d:
            java.lang.String r1 = android.util.Base64.encodeToString(r2, r1)
            java.lang.String r2 = "\\s"
            java.lang.String r0 = r1.replaceAll(r2, r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.uxcam.internals.hd.b():java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0045  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String c() throws javax.crypto.BadPaddingException, javax.crypto.NoSuchPaddingException, java.security.spec.InvalidKeySpecException, javax.crypto.IllegalBlockSizeException, java.security.NoSuchAlgorithmException, java.security.InvalidKeyException {
        /*
            r7 = this;
            java.lang.String r0 = ""
            r1 = 0
            byte[] r2 = new byte[r1]
            byte[] r3 = r7.f189a     // Catch: java.lang.Exception -> L37
            java.lang.String r4 = "RSA/ECB/PKCS1Padding"
            javax.crypto.Cipher r4 = javax.crypto.Cipher.getInstance(r4)     // Catch: java.lang.Exception -> L37
            java.lang.String r5 = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDNUf8CVU/4PRJebkLWYKQIMAiN\nl8n/7/F76ExbRAC8B9SxjU+weoDH25P41j3NWAV6K1t3R5Ws7NPre524akdwFqTH\nhJzkFTHpvSqjxfqTbLepDkhInppZDMvpX6INOBGZQwEdaV37QgLp6cgfsK2oRhur\nUGCDTwVQhY8SdO6riQIDAQAB"
            java.security.spec.X509EncodedKeySpec r6 = new java.security.spec.X509EncodedKeySpec     // Catch: java.security.spec.InvalidKeySpecException -> L23 java.security.NoSuchAlgorithmException -> L28 java.lang.Exception -> L37
            byte[] r5 = android.util.Base64.decode(r5, r1)     // Catch: java.security.spec.InvalidKeySpecException -> L23 java.security.NoSuchAlgorithmException -> L28 java.lang.Exception -> L37
            r6.<init>(r5)     // Catch: java.security.spec.InvalidKeySpecException -> L23 java.security.NoSuchAlgorithmException -> L28 java.lang.Exception -> L37
            java.lang.String r5 = "RSA"
            java.security.KeyFactory r5 = java.security.KeyFactory.getInstance(r5)     // Catch: java.security.spec.InvalidKeySpecException -> L23 java.security.NoSuchAlgorithmException -> L28 java.lang.Exception -> L37
            java.security.PublicKey r5 = r5.generatePublic(r6)     // Catch: java.security.spec.InvalidKeySpecException -> L23 java.security.NoSuchAlgorithmException -> L28 java.lang.Exception -> L37
            goto L2d
        L23:
            r5 = move-exception
            r5.printStackTrace()     // Catch: java.lang.Exception -> L37
            goto L2c
        L28:
            r5 = move-exception
            r5.printStackTrace()     // Catch: java.lang.Exception -> L37
        L2c:
            r5 = 0
        L2d:
            r6 = 1
            r4.init(r6, r5)     // Catch: java.lang.Exception -> L37
            byte[] r2 = r4.doFinal(r3)     // Catch: java.lang.Exception -> L37
            r3 = r0
            goto L3f
        L37:
            r3 = move-exception
            r3.printStackTrace()
            java.lang.String r3 = r3.getMessage()
        L3f:
            boolean r4 = android.text.TextUtils.isEmpty(r3)
            if (r4 != 0) goto L59
            com.uxcam.internals.fj r4 = new com.uxcam.internals.fj
            r4.<init>()
            java.lang.String r5 = "UXCamAES::rsaEncryptedKey()"
            com.uxcam.internals.fj r4 = r4.b(r5)
            java.lang.String r5 = "reason"
            r4.a(r5, r3)
            r3 = 2
            r4.a(r3)
        L59:
            java.lang.String r1 = android.util.Base64.encodeToString(r2, r1)
            java.lang.String r2 = "\\s"
            java.lang.String r0 = r1.replaceAll(r2, r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.uxcam.internals.hd.c():java.lang.String");
    }
}
