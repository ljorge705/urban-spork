package com.nimbusds.jose.crypto.impl;

import com.nimbusds.jose.JOSEException;
import javax.crypto.SecretKey;
import net.jcip.annotations.ThreadSafe;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.modes.GCMBlockCipher;
import org.bouncycastle.crypto.params.AEADParameters;
import org.bouncycastle.crypto.params.KeyParameter;

@ThreadSafe
/* loaded from: classes2.dex */
public class LegacyAESGCM {
    public static final int AUTH_TAG_BIT_LENGTH = 128;

    public static AESEngine createAESCipher(SecretKey secretKey, boolean z) {
        AESEngine aESEngine = new AESEngine();
        aESEngine.init(z, new KeyParameter(secretKey.getEncoded()));
        return aESEngine;
    }

    private static GCMBlockCipher createAESGCMCipher(SecretKey secretKey, boolean z, byte[] bArr, byte[] bArr2) throws IllegalArgumentException {
        GCMBlockCipher gCMBlockCipher = new GCMBlockCipher(createAESCipher(secretKey, z));
        gCMBlockCipher.init(z, new AEADParameters(new KeyParameter(secretKey.getEncoded()), 128, bArr, bArr2));
        return gCMBlockCipher;
    }

    public static AuthenticatedCipherText encrypt(SecretKey secretKey, byte[] bArr, byte[] bArr2, byte[] bArr3) throws DataLengthException, JOSEException, IllegalArgumentException {
        GCMBlockCipher gCMBlockCipherCreateAESGCMCipher = createAESGCMCipher(secretKey, true, bArr, bArr3);
        byte[] bArr4 = new byte[gCMBlockCipherCreateAESGCMCipher.getOutputSize(bArr2.length)];
        int iProcessBytes = gCMBlockCipherCreateAESGCMCipher.processBytes(bArr2, 0, bArr2.length, bArr4, 0);
        try {
            int iDoFinal = (iProcessBytes + gCMBlockCipherCreateAESGCMCipher.doFinal(bArr4, iProcessBytes)) - 16;
            byte[] bArr5 = new byte[iDoFinal];
            byte[] bArr6 = new byte[16];
            System.arraycopy(bArr4, 0, bArr5, 0, iDoFinal);
            System.arraycopy(bArr4, iDoFinal, bArr6, 0, 16);
            return new AuthenticatedCipherText(bArr5, bArr6);
        } catch (InvalidCipherTextException e) {
            throw new JOSEException("Couldn't generate GCM authentication tag: " + e.getMessage(), e);
        }
    }

    public static byte[] decrypt(SecretKey secretKey, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) throws IllegalStateException, JOSEException, IllegalArgumentException {
        GCMBlockCipher gCMBlockCipherCreateAESGCMCipher = createAESGCMCipher(secretKey, false, bArr, bArr3);
        int length = bArr2.length + bArr4.length;
        byte[] bArr5 = new byte[length];
        System.arraycopy(bArr2, 0, bArr5, 0, bArr2.length);
        System.arraycopy(bArr4, 0, bArr5, bArr2.length, bArr4.length);
        byte[] bArr6 = new byte[gCMBlockCipherCreateAESGCMCipher.getOutputSize(length)];
        try {
            gCMBlockCipherCreateAESGCMCipher.doFinal(bArr6, gCMBlockCipherCreateAESGCMCipher.processBytes(bArr5, 0, length, bArr6, 0));
            return bArr6;
        } catch (InvalidCipherTextException e) {
            throw new JOSEException("Couldn't validate GCM authentication tag: " + e.getMessage(), e);
        }
    }

    private LegacyAESGCM() {
    }
}
