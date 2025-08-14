package org.jmrtd.protocol;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.ECPublicKey;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.interfaces.DHPublicKey;
import net.sf.scuba.smartcards.CardServiceException;
import net.sf.scuba.tlv.TLVOutputStream;
import net.sf.scuba.tlv.TLVUtil;
import org.bouncycastle.jce.interfaces.ECPrivateKey;
import org.jmrtd.APDULevelEACTACapable;
import org.jmrtd.CardServiceProtocolException;
import org.jmrtd.Util;
import org.jmrtd.cert.CVCAuthorizationTemplate;
import org.jmrtd.cert.CVCPrincipal;
import org.jmrtd.cert.CardVerifiableCertificate;
import org.jmrtd.lds.icao.MRZInfo;

/* loaded from: classes4.dex */
public class EACTAProtocol {
    private static final int TAG_CVCERTIFICATE_SIGNATURE = 24375;
    private APDULevelEACTACapable service;
    private SecureMessagingWrapper wrapper;
    private static final Logger LOGGER = Logger.getLogger("org.jmrtd.protocol");
    private static final Provider BC_PROVIDER = Util.getBouncyCastleProvider();

    public EACTAProtocol(APDULevelEACTACapable aPDULevelEACTACapable, SecureMessagingWrapper secureMessagingWrapper) {
        this.service = aPDULevelEACTACapable;
        this.wrapper = secureMessagingWrapper;
    }

    public synchronized EACTAResult doEACTA(CVCPrincipal cVCPrincipal, List<CardVerifiableCertificate> list, PrivateKey privateKey, String str, EACCAResult eACCAResult, String str2) throws CardServiceException {
        return doTA(cVCPrincipal, list, privateKey, str, eACCAResult, deriveIdentifier(str2));
    }

    public synchronized EACTAResult doTA(CVCPrincipal cVCPrincipal, List<CardVerifiableCertificate> list, PrivateKey privateKey, String str, EACCAResult eACCAResult, PACEResult pACEResult) throws CardServiceException {
        try {
        } catch (NoSuchAlgorithmException e) {
            throw new CardServiceException("No such algorithm", e);
        }
        return doTA(cVCPrincipal, list, privateKey, str, eACCAResult, deriveIdentifier(pACEResult.getPICCPublicKey()));
    }

    public synchronized EACTAResult doTA(CVCPrincipal cVCPrincipal, List<CardVerifiableCertificate> list, PrivateKey privateKey, String str, EACCAResult eACCAResult, byte[] bArr) throws CardServiceException {
        byte[] bArrSendGetChallenge;
        if (list != null) {
            try {
                try {
                    if (!list.isEmpty()) {
                        if (eACCAResult == null) {
                            throw new IllegalArgumentException("Could not get EAC-CA key hash");
                        }
                        byte[] keyHash = eACCAResult.getKeyHash();
                        if (keyHash == null) {
                            throw new IllegalArgumentException("Could nnot get EAC-CA key hash");
                        }
                        CardVerifiableCertificate cardVerifiableCertificate = list.get(0);
                        if (CVCAuthorizationTemplate.Role.CVCA.equals(cardVerifiableCertificate.getAuthorizationTemplate().getRole())) {
                            CVCPrincipal holderReference = cardVerifiableCertificate.getHolderReference();
                            if (cVCPrincipal != null && !cVCPrincipal.equals(holderReference)) {
                                throw new CardServiceException("First certificate holds wrong authority, found \"" + holderReference.getName() + "\", expected \"" + cVCPrincipal.getName() + "\"");
                            }
                            if (cVCPrincipal == null) {
                                cVCPrincipal = holderReference;
                            }
                            list.remove(0);
                        }
                        CVCPrincipal authorityReference = cardVerifiableCertificate.getAuthorityReference();
                        if (cVCPrincipal != null && !cVCPrincipal.equals(authorityReference)) {
                            throw new CardServiceException("First certificate not signed by expected CA, found " + authorityReference.getName() + ", expected " + cVCPrincipal.getName());
                        }
                        if (cVCPrincipal == null) {
                            cVCPrincipal = authorityReference;
                        }
                        CardVerifiableCertificate cardVerifiableCertificate2 = list.get(list.size() - 1);
                        CVCAuthorizationTemplate.Role role = cardVerifiableCertificate2.getAuthorizationTemplate().getRole();
                        if (!CVCAuthorizationTemplate.Role.IS.equals(role)) {
                            throw new CardServiceException("Last certificate in chain (" + cardVerifiableCertificate2.getHolderReference().getName() + ") does not have role IS, but has role " + role);
                        }
                        for (CardVerifiableCertificate cardVerifiableCertificate3 : list) {
                            try {
                                this.service.sendMSESetDST(this.wrapper, TLVUtil.wrapDO(131, cardVerifiableCertificate3.getAuthorityReference().getName().getBytes("ISO-8859-1")));
                                try {
                                    byte[] certBodyData = cardVerifiableCertificate3.getCertBodyData();
                                    byte[] signature = cardVerifiableCertificate3.getSignature();
                                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                    TLVOutputStream tLVOutputStream = new TLVOutputStream(byteArrayOutputStream);
                                    tLVOutputStream.writeTag(TAG_CVCERTIFICATE_SIGNATURE);
                                    tLVOutputStream.writeValue(signature);
                                    tLVOutputStream.close();
                                    this.service.sendPSOExtendedLengthMode(this.wrapper, certBodyData, byteArrayOutputStream.toByteArray());
                                } catch (Exception e) {
                                    throw new CardServiceProtocolException("Exception", 2, e);
                                }
                            } catch (Exception e2) {
                                throw new CardServiceProtocolException("Exception in MSE:SetDST", 1, e2);
                            }
                        }
                        if (privateKey == null) {
                            throw new CardServiceException("No terminal key");
                        }
                        try {
                            this.service.sendMSESetATExtAuth(this.wrapper, TLVUtil.wrapDO(131, cardVerifiableCertificate2.getHolderReference().getName().getBytes("ISO-8859-1")));
                            try {
                                bArrSendGetChallenge = this.service.sendGetChallenge(this.wrapper);
                                try {
                                    ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                                    byteArrayOutputStream2.write(bArr);
                                    byteArrayOutputStream2.write(bArrSendGetChallenge);
                                    byteArrayOutputStream2.write(keyHash);
                                    byteArrayOutputStream2.close();
                                    byte[] byteArray = byteArrayOutputStream2.toByteArray();
                                    String sigAlgName = cardVerifiableCertificate2.getSigAlgName();
                                    if (sigAlgName == null) {
                                        throw new IllegalStateException("Could not determine signature algorithm for terminal certificate " + cardVerifiableCertificate2.getHolderReference().getName());
                                    }
                                    Signature signature2 = Signature.getInstance(sigAlgName, BC_PROVIDER);
                                    signature2.initSign(privateKey);
                                    signature2.update(byteArray);
                                    byte[] bArrSign = signature2.sign();
                                    if (sigAlgName.toUpperCase().endsWith("ECDSA")) {
                                        bArrSign = Util.getRawECDSASignature(bArrSign, (int) Math.ceil(((ECPrivateKey) privateKey).getParameters().getCurve().getFieldSize() / 8.0d));
                                    }
                                    this.service.sendMutualAuthenticate(this.wrapper, bArrSign);
                                } catch (Exception e3) {
                                    LOGGER.log(Level.WARNING, "Exception", (Throwable) e3);
                                    throw new CardServiceProtocolException("Exception in External Authenticate", 5, e3);
                                }
                            } catch (Exception e4) {
                                throw new CardServiceProtocolException("Exception in Get Challenge", 4, e4);
                            }
                        } catch (Exception e5) {
                            throw new CardServiceProtocolException("Exception in MSE Set AT", 3, e5);
                        }
                    }
                } catch (CardServiceException e6) {
                    throw e6;
                }
            } catch (Exception e7) {
                throw new CardServiceException("Unexpected exception", e7);
            }
        }
        throw new IllegalArgumentException("Need at least 1 certificate to perform TA, found: " + list);
        return new EACTAResult(eACCAResult, cVCPrincipal, list, privateKey, null, bArrSendGetChallenge);
    }

    public static byte[] deriveIdentifier(String str) {
        if (str == null) {
            return null;
        }
        int length = str.length();
        byte[] bArr = new byte[length + 1];
        try {
            System.arraycopy(str.getBytes("ISO-8859-1"), 0, bArr, 0, length);
            bArr[length] = (byte) MRZInfo.checkDigit(str);
            return bArr;
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("Unsupported encoding", e);
        }
    }

    public static byte[] deriveIdentifier(PublicKey publicKey) throws NoSuchAlgorithmException {
        if (publicKey == null) {
            return null;
        }
        String algorithm = publicKey.getAlgorithm();
        if ("DH".equals(algorithm) || (publicKey instanceof DHPublicKey)) {
            return MessageDigest.getInstance("SHA-1").digest(Util.i2os(((DHPublicKey) publicKey).getY()));
        }
        if ("ECDH".equals(algorithm) || (publicKey instanceof ECPublicKey)) {
            return Util.alignKeyDataToSize(Util.i2os(((org.bouncycastle.jce.interfaces.ECPublicKey) publicKey).getQ().getAffineXCoord().toBigInteger()), (int) Math.ceil(r5.getParameters().getCurve().getFieldSize() / 8.0d));
        }
        throw new NoSuchAlgorithmException("Unsupported agreement algorithm " + algorithm);
    }
}
