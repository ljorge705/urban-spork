package org.spongycastle.cert.crmf;

import java.io.IOException;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERUTF8String;
import org.spongycastle.asn1.crmf.AttributeTypeAndValue;
import org.spongycastle.asn1.crmf.CRMFObjectIdentifiers;
import org.spongycastle.asn1.crmf.CertReqMsg;
import org.spongycastle.asn1.crmf.CertTemplate;
import org.spongycastle.asn1.crmf.Controls;
import org.spongycastle.asn1.crmf.PKIArchiveOptions;
import org.spongycastle.asn1.crmf.POPOSigningKey;
import org.spongycastle.asn1.crmf.ProofOfPossession;
import org.spongycastle.cert.CertIOException;
import org.spongycastle.operator.ContentVerifier;
import org.spongycastle.operator.ContentVerifierProvider;
import org.spongycastle.operator.OperatorCreationException;
import org.spongycastle.util.Encodable;

/* loaded from: classes4.dex */
public class CertificateRequestMessage implements Encodable {
    public static final int popKeyAgreement = 3;
    public static final int popKeyEncipherment = 2;
    public static final int popRaVerified = 0;
    public static final int popSigningKey = 1;
    private final CertReqMsg certReqMsg;
    private final Controls controls;

    public boolean hasControls() {
        return this.controls != null;
    }

    public CertReqMsg toASN1Structure() {
        return this.certReqMsg;
    }

    private static CertReqMsg parseBytes(byte[] bArr) throws IOException {
        try {
            return CertReqMsg.getInstance(ASN1Primitive.fromByteArray(bArr));
        } catch (ClassCastException e) {
            throw new CertIOException("malformed data: " + e.getMessage(), e);
        } catch (IllegalArgumentException e2) {
            throw new CertIOException("malformed data: " + e2.getMessage(), e2);
        }
    }

    public CertificateRequestMessage(byte[] bArr) throws IOException {
        this(parseBytes(bArr));
    }

    public CertificateRequestMessage(CertReqMsg certReqMsg) {
        this.certReqMsg = certReqMsg;
        this.controls = certReqMsg.getCertReq().getControls();
    }

    public CertTemplate getCertTemplate() {
        return this.certReqMsg.getCertReq().getCertTemplate();
    }

    public boolean hasControl(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return findControl(aSN1ObjectIdentifier) != null;
    }

    public Control getControl(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        AttributeTypeAndValue attributeTypeAndValueFindControl = findControl(aSN1ObjectIdentifier);
        if (attributeTypeAndValueFindControl == null) {
            return null;
        }
        if (attributeTypeAndValueFindControl.getType().equals(CRMFObjectIdentifiers.id_regCtrl_pkiArchiveOptions)) {
            return new PKIArchiveControl(PKIArchiveOptions.getInstance(attributeTypeAndValueFindControl.getValue()));
        }
        if (attributeTypeAndValueFindControl.getType().equals(CRMFObjectIdentifiers.id_regCtrl_regToken)) {
            return new RegTokenControl(DERUTF8String.getInstance(attributeTypeAndValueFindControl.getValue()));
        }
        if (attributeTypeAndValueFindControl.getType().equals(CRMFObjectIdentifiers.id_regCtrl_authenticator)) {
            return new AuthenticatorControl(DERUTF8String.getInstance(attributeTypeAndValueFindControl.getValue()));
        }
        return null;
    }

    private AttributeTypeAndValue findControl(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        Controls controls = this.controls;
        if (controls == null) {
            return null;
        }
        AttributeTypeAndValue[] attributeTypeAndValueArray = controls.toAttributeTypeAndValueArray();
        for (int i = 0; i != attributeTypeAndValueArray.length; i++) {
            if (attributeTypeAndValueArray[i].getType().equals(aSN1ObjectIdentifier)) {
                return attributeTypeAndValueArray[i];
            }
        }
        return null;
    }

    public boolean hasProofOfPossession() {
        return this.certReqMsg.getPopo() != null;
    }

    public int getProofOfPossessionType() {
        return this.certReqMsg.getPopo().getType();
    }

    public boolean hasSigningKeyProofOfPossessionWithPKMAC() {
        ProofOfPossession popo = this.certReqMsg.getPopo();
        return popo.getType() == 1 && POPOSigningKey.getInstance(popo.getObject()).getPoposkInput().getPublicKeyMAC() != null;
    }

    public boolean isValidSigningKeyPOP(ContentVerifierProvider contentVerifierProvider) throws IllegalStateException, CRMFException {
        ProofOfPossession popo = this.certReqMsg.getPopo();
        if (popo.getType() == 1) {
            POPOSigningKey pOPOSigningKey = POPOSigningKey.getInstance(popo.getObject());
            if (pOPOSigningKey.getPoposkInput() != null && pOPOSigningKey.getPoposkInput().getPublicKeyMAC() != null) {
                throw new IllegalStateException("verification requires password check");
            }
            return verifySignature(contentVerifierProvider, pOPOSigningKey);
        }
        throw new IllegalStateException("not Signing Key type of proof of possession");
    }

    public boolean isValidSigningKeyPOP(ContentVerifierProvider contentVerifierProvider, PKMACBuilder pKMACBuilder, char[] cArr) throws IllegalStateException, CRMFException {
        ProofOfPossession popo = this.certReqMsg.getPopo();
        if (popo.getType() == 1) {
            POPOSigningKey pOPOSigningKey = POPOSigningKey.getInstance(popo.getObject());
            if (pOPOSigningKey.getPoposkInput() == null || pOPOSigningKey.getPoposkInput().getSender() != null) {
                throw new IllegalStateException("no PKMAC present in proof of possession");
            }
            if (new PKMACValueVerifier(pKMACBuilder).isValid(pOPOSigningKey.getPoposkInput().getPublicKeyMAC(), cArr, getCertTemplate().getPublicKey())) {
                return verifySignature(contentVerifierProvider, pOPOSigningKey);
            }
            return false;
        }
        throw new IllegalStateException("not Signing Key type of proof of possession");
    }

    private boolean verifySignature(ContentVerifierProvider contentVerifierProvider, POPOSigningKey pOPOSigningKey) throws CRMFException {
        try {
            ContentVerifier contentVerifier = contentVerifierProvider.get(pOPOSigningKey.getAlgorithmIdentifier());
            if (pOPOSigningKey.getPoposkInput() != null) {
                CRMFUtil.derEncodeToStream(pOPOSigningKey.getPoposkInput(), contentVerifier.getOutputStream());
            } else {
                CRMFUtil.derEncodeToStream(this.certReqMsg.getCertReq(), contentVerifier.getOutputStream());
            }
            return contentVerifier.verify(pOPOSigningKey.getSignature().getOctets());
        } catch (OperatorCreationException e) {
            throw new CRMFException("unable to create verifier: " + e.getMessage(), e);
        }
    }

    @Override // org.spongycastle.util.Encodable
    public byte[] getEncoded() throws IOException {
        return this.certReqMsg.getEncoded();
    }
}
