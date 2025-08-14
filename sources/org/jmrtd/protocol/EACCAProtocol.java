package org.jmrtd.protocol;

import com.oblador.keychain.cipherStorage.CipherStorageKeystoreAesCbc;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import javax.crypto.interfaces.DHPublicKey;
import net.sf.scuba.smartcards.CardServiceException;
import net.sf.scuba.tlv.TLVUtil;
import org.jmrtd.APDULevelEACCACapable;
import org.jmrtd.CardServiceProtocolException;
import org.jmrtd.Util;
import org.jmrtd.lds.ChipAuthenticationInfo;
import org.jmrtd.lds.SecurityInfo;

/* loaded from: classes4.dex */
public class EACCAProtocol {
    private static final int COMMAND_CHAINING_CHUNK_SIZE = 223;
    private int maxTranceiveLength;
    private APDULevelEACCACapable service;
    private boolean shouldCheckMAC;
    private SecureMessagingWrapper wrapper;
    private static final Logger LOGGER = Logger.getLogger("org.jmrtd");
    private static final Provider BC_PROVIDER = Util.getBouncyCastleProvider();

    public SecureMessagingWrapper getWrapper() {
        return this.wrapper;
    }

    public EACCAProtocol(APDULevelEACCACapable aPDULevelEACCACapable, SecureMessagingWrapper secureMessagingWrapper, int i, boolean z) {
        this.service = aPDULevelEACCACapable;
        this.wrapper = secureMessagingWrapper;
        this.maxTranceiveLength = i;
        this.shouldCheckMAC = z;
    }

    public EACCAResult doCA(BigInteger bigInteger, String str, String str2, PublicKey publicKey) throws NoSuchAlgorithmException, CardServiceException, InvalidAlgorithmParameterException {
        String keyAgreementAlgorithm;
        if (publicKey == null) {
            throw new IllegalArgumentException("PICC public key is null");
        }
        if (str == null) {
            str = inferChipAuthenticationOIDfromPublicKeyOID(str2);
        }
        AlgorithmParameterSpec params = null;
        try {
            keyAgreementAlgorithm = ChipAuthenticationInfo.toKeyAgreementAlgorithm(str);
        } catch (NumberFormatException e) {
            LOGGER.log(Level.WARNING, "Unknown object identifier " + str, (Throwable) e);
            keyAgreementAlgorithm = null;
        }
        if (!"ECDH".equals(keyAgreementAlgorithm) && !"DH".equals(keyAgreementAlgorithm)) {
            throw new IllegalArgumentException("Unsupported agreement algorithm, expected ECDH or DH, found " + keyAgreementAlgorithm);
        }
        try {
            if ("DH".equals(keyAgreementAlgorithm)) {
                params = ((DHPublicKey) publicKey).getParams();
            } else if ("ECDH".equals(keyAgreementAlgorithm)) {
                params = ((ECPublicKey) publicKey).getParams();
            }
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(keyAgreementAlgorithm, BC_PROVIDER);
            keyPairGenerator.initialize(params);
            KeyPair keyPairGenerateKeyPair = keyPairGenerator.generateKeyPair();
            PublicKey publicKey2 = keyPairGenerateKeyPair.getPublic();
            PrivateKey privateKey = keyPairGenerateKeyPair.getPrivate();
            sendPublicKey(this.service, this.wrapper, str, bigInteger, publicKey2);
            byte[] keyHash = getKeyHash(keyAgreementAlgorithm, publicKey2);
            this.wrapper = restartSecureMessaging(str, computeSharedSecret(keyAgreementAlgorithm, publicKey, privateKey), this.maxTranceiveLength, this.shouldCheckMAC);
            return new EACCAResult(bigInteger, publicKey, keyHash, publicKey2, privateKey, this.wrapper);
        } catch (GeneralSecurityException e2) {
            throw new CardServiceException("Security exception during Chip Authentication", e2);
        }
    }

    public static void sendPublicKey(APDULevelEACCACapable aPDULevelEACCACapable, SecureMessagingWrapper secureMessagingWrapper, String str, BigInteger bigInteger, PublicKey publicKey) throws CardServiceException {
        String keyAgreementAlgorithm = ChipAuthenticationInfo.toKeyAgreementAlgorithm(str);
        String cipherAlgorithm = ChipAuthenticationInfo.toCipherAlgorithm(str);
        byte[] keyData = getKeyData(keyAgreementAlgorithm, publicKey);
        if (cipherAlgorithm.startsWith("DESede")) {
            try {
                aPDULevelEACCACapable.sendMSEKAT(secureMessagingWrapper, TLVUtil.wrapDO(145, keyData), bigInteger != null ? TLVUtil.wrapDO(132, Util.i2os(bigInteger)) : null);
            } catch (Exception e) {
                throw new CardServiceProtocolException("Exception during MSE KAT", 1, e);
            }
        } else {
            if (cipherAlgorithm.startsWith(CipherStorageKeystoreAesCbc.ALGORITHM_AES)) {
                try {
                    aPDULevelEACCACapable.sendMSESetATIntAuth(secureMessagingWrapper, str, bigInteger);
                    try {
                        sendGeneralAuthenticate(aPDULevelEACCACapable, secureMessagingWrapper, TLVUtil.wrapDO(128, keyData));
                        return;
                    } catch (Exception e2) {
                        throw new CardServiceProtocolException("Exception during General Authenticate", 2, e2);
                    }
                } catch (Exception e3) {
                    throw new CardServiceProtocolException("Exception during MSE Set AT Int Auth", 1, e3);
                }
            }
            throw new IllegalStateException("Cannot set up secure channel with cipher " + cipherAlgorithm);
        }
    }

    public static byte[] computeSharedSecret(String str, PublicKey publicKey, PrivateKey privateKey) throws IllegalStateException, NoSuchAlgorithmException, InvalidKeyException {
        KeyAgreement keyAgreement = KeyAgreement.getInstance(str, BC_PROVIDER);
        keyAgreement.init(privateKey);
        keyAgreement.doPhase(publicKey, true);
        return keyAgreement.generateSecret();
    }

    public static SecureMessagingWrapper restartSecureMessaging(String str, byte[] bArr, int i, boolean z) throws GeneralSecurityException {
        String cipherAlgorithm = ChipAuthenticationInfo.toCipherAlgorithm(str);
        int keyLength = ChipAuthenticationInfo.toKeyLength(str);
        SecretKey secretKeyDeriveKey = Util.deriveKey(bArr, cipherAlgorithm, keyLength, 1);
        SecretKey secretKeyDeriveKey2 = Util.deriveKey(bArr, cipherAlgorithm, keyLength, 2);
        if (cipherAlgorithm.startsWith("DESede")) {
            return new DESedeSecureMessagingWrapper(secretKeyDeriveKey, secretKeyDeriveKey2, i, z, 0L);
        }
        if (cipherAlgorithm.startsWith(CipherStorageKeystoreAesCbc.ALGORITHM_AES)) {
            return new AESSecureMessagingWrapper(secretKeyDeriveKey, secretKeyDeriveKey2, i, z, 0L);
        }
        throw new IllegalStateException("Unsupported cipher algorithm " + cipherAlgorithm);
    }

    public static byte[] getKeyHash(String str, PublicKey publicKey) throws NoSuchAlgorithmException {
        if ("DH".equals(str) || (publicKey instanceof DHPublicKey)) {
            return MessageDigest.getInstance("SHA-1").digest(getKeyData(str, publicKey));
        }
        if ("ECDH".equals(str) || (publicKey instanceof ECPublicKey)) {
            return Util.alignKeyDataToSize(Util.i2os(((org.bouncycastle.jce.interfaces.ECPublicKey) publicKey).getQ().getAffineXCoord().toBigInteger()), (int) Math.ceil(r5.getParameters().getCurve().getFieldSize() / 8.0d));
        }
        throw new IllegalArgumentException("Unsupported agreement algorithm " + str);
    }

    private static void sendGeneralAuthenticate(APDULevelEACCACapable aPDULevelEACCACapable, SecureMessagingWrapper secureMessagingWrapper, byte[] bArr) throws CardServiceException {
        try {
            aPDULevelEACCACapable.sendGeneralAuthenticate(secureMessagingWrapper, bArr, true);
        } catch (CardServiceException e) {
            LOGGER.log(Level.WARNING, "Failed to send GENERAL AUTHENTICATE, falling back to command chaining", (Throwable) e);
            List<byte[]> listPartition = Util.partition(223, bArr);
            Iterator<byte[]> it = listPartition.iterator();
            int i = 0;
            while (it.hasNext()) {
                i++;
                aPDULevelEACCACapable.sendGeneralAuthenticate(secureMessagingWrapper, it.next(), i >= listPartition.size());
            }
        }
    }

    private static byte[] getKeyData(String str, PublicKey publicKey) {
        if ("DH".equals(str)) {
            return Util.i2os(((DHPublicKey) publicKey).getY());
        }
        if ("ECDH".equals(str)) {
            return ((org.bouncycastle.jce.interfaces.ECPublicKey) publicKey).getQ().getEncoded(false);
        }
        throw new IllegalArgumentException("Unsupported agreement algorithm " + str);
    }

    private static String inferChipAuthenticationOIDfromPublicKeyOID(String str) {
        if (SecurityInfo.ID_PK_ECDH.equals(str)) {
            LOGGER.warning("Could not determine ChipAuthentication algorithm, defaulting to id-CA-ECDH-3DES-CBC-CBC");
            return SecurityInfo.ID_CA_ECDH_3DES_CBC_CBC;
        }
        if (SecurityInfo.ID_PK_DH.equals(str)) {
            LOGGER.warning("Could not determine ChipAuthentication algorithm, defaulting to id-CA-DH-3DES-CBC-CBC");
            return SecurityInfo.ID_CA_DH_3DES_CBC_CBC;
        }
        LOGGER.warning("No ChipAuthenticationInfo and unsupported ChipAuthenticationPublicKeyInfo public key OID " + str);
        return null;
    }
}
