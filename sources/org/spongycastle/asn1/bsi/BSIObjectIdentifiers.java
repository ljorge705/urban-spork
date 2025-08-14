package org.spongycastle.asn1.bsi;

import androidx.exifinterface.media.ExifInterface;
import org.ejbca.cvc.CVCObjectIdentifiers;
import org.spongycastle.asn1.ASN1ObjectIdentifier;

/* loaded from: classes4.dex */
public interface BSIObjectIdentifiers {
    public static final ASN1ObjectIdentifier bsi_de;
    public static final ASN1ObjectIdentifier ecdsa_plain_RIPEMD160;
    public static final ASN1ObjectIdentifier ecdsa_plain_SHA1;
    public static final ASN1ObjectIdentifier ecdsa_plain_SHA224;
    public static final ASN1ObjectIdentifier ecdsa_plain_SHA256;
    public static final ASN1ObjectIdentifier ecdsa_plain_SHA384;
    public static final ASN1ObjectIdentifier ecdsa_plain_SHA512;
    public static final ASN1ObjectIdentifier ecdsa_plain_signatures;
    public static final ASN1ObjectIdentifier id_ecc;

    static {
        ASN1ObjectIdentifier aSN1ObjectIdentifier = new ASN1ObjectIdentifier(CVCObjectIdentifiers.bsi_de);
        bsi_de = aSN1ObjectIdentifier;
        ASN1ObjectIdentifier aSN1ObjectIdentifierBranch = aSN1ObjectIdentifier.branch("1.1");
        id_ecc = aSN1ObjectIdentifierBranch;
        ASN1ObjectIdentifier aSN1ObjectIdentifierBranch2 = aSN1ObjectIdentifierBranch.branch("4.1");
        ecdsa_plain_signatures = aSN1ObjectIdentifierBranch2;
        ecdsa_plain_SHA1 = aSN1ObjectIdentifierBranch2.branch("1");
        ecdsa_plain_SHA224 = aSN1ObjectIdentifierBranch2.branch(ExifInterface.GPS_MEASUREMENT_2D);
        ecdsa_plain_SHA256 = aSN1ObjectIdentifierBranch2.branch(ExifInterface.GPS_MEASUREMENT_3D);
        ecdsa_plain_SHA384 = aSN1ObjectIdentifierBranch2.branch("4");
        ecdsa_plain_SHA512 = aSN1ObjectIdentifierBranch2.branch("5");
        ecdsa_plain_RIPEMD160 = aSN1ObjectIdentifierBranch2.branch("6");
    }
}
