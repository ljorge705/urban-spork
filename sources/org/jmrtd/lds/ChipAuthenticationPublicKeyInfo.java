package org.jmrtd.lds;

import java.math.BigInteger;
import java.security.PublicKey;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DLSequence;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.jmrtd.Util;

/* loaded from: classes4.dex */
public class ChipAuthenticationPublicKeyInfo extends SecurityInfo {
    private static final Logger LOGGER = Logger.getLogger("org.jmrtd");
    private static final long serialVersionUID = 5687291829854501771L;
    private BigInteger keyId;
    private String oid;
    private PublicKey publicKey;

    public BigInteger getKeyId() {
        return this.keyId;
    }

    @Override // org.jmrtd.lds.SecurityInfo
    public String getObjectIdentifier() {
        return this.oid;
    }

    public PublicKey getSubjectPublicKey() {
        return this.publicKey;
    }

    public ChipAuthenticationPublicKeyInfo(PublicKey publicKey) {
        this(publicKey, (BigInteger) null);
    }

    public ChipAuthenticationPublicKeyInfo(PublicKey publicKey, BigInteger bigInteger) {
        this(Util.inferProtocolIdentifier(publicKey), publicKey, bigInteger);
    }

    public ChipAuthenticationPublicKeyInfo(String str, PublicKey publicKey) {
        this(str, publicKey, null);
    }

    public ChipAuthenticationPublicKeyInfo(String str, PublicKey publicKey, BigInteger bigInteger) {
        this.oid = str;
        this.publicKey = Util.reconstructPublicKey(publicKey);
        this.keyId = bigInteger;
        checkFields();
    }

    @Override // org.jmrtd.lds.SecurityInfo
    @Deprecated
    public ASN1Primitive getDERObject() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        SubjectPublicKeyInfo subjectPublicKeyInfo = Util.toSubjectPublicKeyInfo(this.publicKey);
        if (subjectPublicKeyInfo == null) {
            LOGGER.log(Level.WARNING, "Could not convert public key to subject-public-key-info structure");
        } else {
            aSN1EncodableVector.add(new ASN1ObjectIdentifier(this.oid));
            aSN1EncodableVector.add(subjectPublicKeyInfo.toASN1Primitive());
            if (this.keyId != null) {
                aSN1EncodableVector.add(new ASN1Integer(this.keyId));
            }
        }
        return new DLSequence(aSN1EncodableVector);
    }

    @Override // org.jmrtd.lds.SecurityInfo
    public String getProtocolOIDString() {
        return toProtocolOIDString(this.oid);
    }

    protected void checkFields() {
        try {
            if (checkRequiredIdentifier(this.oid)) {
            } else {
                throw new IllegalArgumentException("Wrong identifier: " + this.oid);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Malformed ChipAuthenticationInfo", e);
        }
    }

    public static boolean checkRequiredIdentifier(String str) {
        return ID_PK_DH.equals(str) || ID_PK_ECDH.equals(str);
    }

    public String toString() {
        StringBuilder sbAppend = new StringBuilder("ChipAuthenticationPublicKeyInfo [protocol: ").append(toProtocolOIDString(this.oid)).append(", chipAuthenticationPublicKey: ").append(Util.getDetailedPublicKeyAlgorithm(getSubjectPublicKey())).append(", keyId: ");
        BigInteger bigInteger = this.keyId;
        return sbAppend.append(bigInteger == null ? "-" : bigInteger.toString()).append("]").toString();
    }

    public int hashCode() {
        int iHashCode = this.oid.hashCode();
        BigInteger bigInteger = this.keyId;
        int iHashCode2 = iHashCode + (bigInteger == null ? 111 : bigInteger.hashCode());
        PublicKey publicKey = this.publicKey;
        return ((iHashCode2 + (publicKey != null ? publicKey.hashCode() : 111)) * 1337) + 123;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!ChipAuthenticationPublicKeyInfo.class.equals(obj.getClass())) {
            return false;
        }
        ChipAuthenticationPublicKeyInfo chipAuthenticationPublicKeyInfo = (ChipAuthenticationPublicKeyInfo) obj;
        if (!this.oid.equals(chipAuthenticationPublicKeyInfo.oid)) {
            return false;
        }
        BigInteger bigInteger = this.keyId;
        return ((bigInteger == null && chipAuthenticationPublicKeyInfo.keyId == null) || (bigInteger != null && bigInteger.equals(chipAuthenticationPublicKeyInfo.keyId))) && this.publicKey.equals(chipAuthenticationPublicKeyInfo.publicKey);
    }

    public static String toKeyAgreementAlgorithm(String str) {
        if (str == null) {
            throw new NumberFormatException("Unknown OID: null");
        }
        if (ID_PK_DH.equals(str)) {
            return "DH";
        }
        if (ID_PK_ECDH.equals(str)) {
            return "ECDH";
        }
        throw new NumberFormatException("Unknown OID: \"" + str + "\"");
    }

    private static String toProtocolOIDString(String str) {
        return ID_PK_DH.equals(str) ? "id-PK-DH" : ID_PK_ECDH.equals(str) ? "id-PK-ECDH" : str;
    }
}
