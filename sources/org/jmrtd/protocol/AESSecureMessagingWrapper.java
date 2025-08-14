package org.jmrtd.protocol;

import com.oblador.keychain.cipherStorage.CipherStorageKeystoreAesCbc;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.security.GeneralSecurityException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import org.jmrtd.Util;

/* loaded from: classes4.dex */
public class AESSecureMessagingWrapper extends SecureMessagingWrapper implements Serializable {
    private static final Logger LOGGER = Logger.getLogger("org.jmrtd");
    private static final long serialVersionUID = 2086301081448345496L;
    private transient Cipher sscIVCipher;

    @Override // org.jmrtd.protocol.SecureMessagingWrapper
    public int getPadLength() {
        return 16;
    }

    @Override // net.sf.scuba.smartcards.APDUWrapper
    public String getType() {
        return CipherStorageKeystoreAesCbc.ALGORITHM_AES;
    }

    public AESSecureMessagingWrapper(SecretKey secretKey, SecretKey secretKey2, long j) throws GeneralSecurityException {
        this(secretKey, secretKey2, 256, true, j);
    }

    public AESSecureMessagingWrapper(AESSecureMessagingWrapper aESSecureMessagingWrapper) throws GeneralSecurityException {
        this(aESSecureMessagingWrapper.getEncryptionKey(), aESSecureMessagingWrapper.getMACKey(), aESSecureMessagingWrapper.getMaxTranceiveLength(), aESSecureMessagingWrapper.shouldCheckMAC(), aESSecureMessagingWrapper.getSendSequenceCounter());
    }

    public AESSecureMessagingWrapper(SecretKey secretKey, SecretKey secretKey2, int i, boolean z, long j) throws GeneralSecurityException {
        super(secretKey, secretKey2, "AES/CBC/NoPadding", "AESCMAC", i, z, j);
        this.sscIVCipher = Util.getCipher("AES/ECB/NoPadding", 1, secretKey);
    }

    @Override // org.jmrtd.protocol.SecureMessagingWrapper
    public byte[] getEncodedSendSequenceCounter() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(16);
        try {
            try {
                byteArrayOutputStream.write(0);
                byteArrayOutputStream.write(0);
                byteArrayOutputStream.write(0);
                byteArrayOutputStream.write(0);
                byteArrayOutputStream.write(0);
                byteArrayOutputStream.write(0);
                byteArrayOutputStream.write(0);
                byteArrayOutputStream.write(0);
                DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
                dataOutputStream.writeLong(getSendSequenceCounter());
                dataOutputStream.close();
                return byteArrayOutputStream.toByteArray();
            } catch (IOException e) {
                LOGGER.log(Level.FINE, "Error writing to stream", (Throwable) e);
                try {
                    byteArrayOutputStream.close();
                    return null;
                } catch (IOException e2) {
                    LOGGER.log(Level.FINE, "Error closing stream", (Throwable) e2);
                    return null;
                }
            }
        } finally {
            try {
                byteArrayOutputStream.close();
            } catch (IOException e3) {
                LOGGER.log(Level.FINE, "Error closing stream", (Throwable) e3);
            }
        }
    }

    @Override // org.jmrtd.protocol.SecureMessagingWrapper
    public String toString() {
        return "AESSecureMessagingWrapper [ssc: " + getSendSequenceCounter() + ", kEnc: " + getEncryptionKey() + ", kMac: " + getMACKey() + ", shouldCheckMAC: " + shouldCheckMAC() + ", maxTranceiveLength: " + getMaxTranceiveLength() + "]";
    }

    @Override // org.jmrtd.protocol.SecureMessagingWrapper
    public int hashCode() {
        return (super.hashCode() * 71) + 17;
    }

    @Override // org.jmrtd.protocol.SecureMessagingWrapper
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return super.equals(obj);
        }
        return false;
    }

    @Override // org.jmrtd.protocol.SecureMessagingWrapper
    protected IvParameterSpec getIV() throws GeneralSecurityException {
        return new IvParameterSpec(this.sscIVCipher.doFinal(getEncodedSendSequenceCounter()));
    }
}
