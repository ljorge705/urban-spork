package org.spongycastle.asn1.rosstandart;

import androidx.exifinterface.media.ExifInterface;
import org.spongycastle.asn1.ASN1ObjectIdentifier;

/* loaded from: classes4.dex */
public interface RosstandartObjectIdentifiers {
    public static final ASN1ObjectIdentifier id_tc26;
    public static final ASN1ObjectIdentifier id_tc26_agreement;
    public static final ASN1ObjectIdentifier id_tc26_agreement_gost_3410_12_256;
    public static final ASN1ObjectIdentifier id_tc26_agreement_gost_3410_12_512;
    public static final ASN1ObjectIdentifier id_tc26_gost_28147_param_Z;
    public static final ASN1ObjectIdentifier id_tc26_gost_3410_12_256;
    public static final ASN1ObjectIdentifier id_tc26_gost_3410_12_256_paramSetA;
    public static final ASN1ObjectIdentifier id_tc26_gost_3410_12_512;
    public static final ASN1ObjectIdentifier id_tc26_gost_3410_12_512_paramSetA;
    public static final ASN1ObjectIdentifier id_tc26_gost_3410_12_512_paramSetB;
    public static final ASN1ObjectIdentifier id_tc26_gost_3410_12_512_paramSetC;
    public static final ASN1ObjectIdentifier id_tc26_gost_3411_12_256;
    public static final ASN1ObjectIdentifier id_tc26_gost_3411_12_512;
    public static final ASN1ObjectIdentifier id_tc26_hmac_gost_3411_12_256;
    public static final ASN1ObjectIdentifier id_tc26_hmac_gost_3411_12_512;
    public static final ASN1ObjectIdentifier id_tc26_signwithdigest_gost_3410_12_256;
    public static final ASN1ObjectIdentifier id_tc26_signwithdigest_gost_3410_12_512;
    public static final ASN1ObjectIdentifier rosstandart;

    static {
        ASN1ObjectIdentifier aSN1ObjectIdentifier = new ASN1ObjectIdentifier("1.2.643.7");
        rosstandart = aSN1ObjectIdentifier;
        ASN1ObjectIdentifier aSN1ObjectIdentifierBranch = aSN1ObjectIdentifier.branch("1");
        id_tc26 = aSN1ObjectIdentifierBranch;
        id_tc26_gost_3411_12_256 = aSN1ObjectIdentifierBranch.branch("1.2.2");
        id_tc26_gost_3411_12_512 = aSN1ObjectIdentifierBranch.branch("1.2.3");
        id_tc26_hmac_gost_3411_12_256 = aSN1ObjectIdentifierBranch.branch("1.4.1");
        id_tc26_hmac_gost_3411_12_512 = aSN1ObjectIdentifierBranch.branch("1.4.2");
        id_tc26_gost_3410_12_256 = aSN1ObjectIdentifierBranch.branch("1.1.1");
        id_tc26_gost_3410_12_512 = aSN1ObjectIdentifierBranch.branch("1.1.2");
        id_tc26_signwithdigest_gost_3410_12_256 = aSN1ObjectIdentifierBranch.branch("1.3.2");
        id_tc26_signwithdigest_gost_3410_12_512 = aSN1ObjectIdentifierBranch.branch("1.3.3");
        ASN1ObjectIdentifier aSN1ObjectIdentifierBranch2 = aSN1ObjectIdentifierBranch.branch("1.6");
        id_tc26_agreement = aSN1ObjectIdentifierBranch2;
        id_tc26_agreement_gost_3410_12_256 = aSN1ObjectIdentifierBranch2.branch("1");
        id_tc26_agreement_gost_3410_12_512 = aSN1ObjectIdentifierBranch2.branch(ExifInterface.GPS_MEASUREMENT_2D);
        id_tc26_gost_3410_12_256_paramSetA = aSN1ObjectIdentifierBranch.branch("2.1.1.1");
        id_tc26_gost_3410_12_512_paramSetA = aSN1ObjectIdentifierBranch.branch("2.1.2.1");
        id_tc26_gost_3410_12_512_paramSetB = aSN1ObjectIdentifierBranch.branch("2.1.2.2");
        id_tc26_gost_3410_12_512_paramSetC = aSN1ObjectIdentifierBranch.branch("2.1.2.3");
        id_tc26_gost_28147_param_Z = aSN1ObjectIdentifierBranch.branch("2.5.1.1");
    }
}
