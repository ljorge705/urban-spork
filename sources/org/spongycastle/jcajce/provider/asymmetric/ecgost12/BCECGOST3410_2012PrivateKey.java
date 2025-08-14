package org.spongycastle.jcajce.provider.asymmetric.ecgost12;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.interfaces.ECPrivateKey;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.util.Enumeration;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Null;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERBitString;
import org.spongycastle.asn1.DERNull;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.cryptopro.ECGOST3410NamedCurves;
import org.spongycastle.asn1.cryptopro.GOST3410PublicKeyAlgParameters;
import org.spongycastle.asn1.pkcs.PrivateKeyInfo;
import org.spongycastle.asn1.rosstandart.RosstandartObjectIdentifiers;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;
import org.spongycastle.asn1.x9.X962Parameters;
import org.spongycastle.asn1.x9.X9ECParameters;
import org.spongycastle.crypto.params.ECDomainParameters;
import org.spongycastle.crypto.params.ECPrivateKeyParameters;
import org.spongycastle.jcajce.provider.asymmetric.util.EC5Util;
import org.spongycastle.jcajce.provider.asymmetric.util.ECUtil;
import org.spongycastle.jcajce.provider.asymmetric.util.PKCS12BagAttributeCarrierImpl;
import org.spongycastle.jce.ECGOST3410NamedCurveTable;
import org.spongycastle.jce.interfaces.ECPointEncoder;
import org.spongycastle.jce.interfaces.PKCS12BagAttributeCarrier;
import org.spongycastle.jce.provider.BouncyCastleProvider;
import org.spongycastle.jce.spec.ECNamedCurveParameterSpec;
import org.spongycastle.jce.spec.ECNamedCurveSpec;
import org.spongycastle.jce.spec.ECPrivateKeySpec;
import org.spongycastle.math.ec.ECCurve;

/* loaded from: classes7.dex */
public class BCECGOST3410_2012PrivateKey implements ECPrivateKey, org.spongycastle.jce.interfaces.ECPrivateKey, PKCS12BagAttributeCarrier, ECPointEncoder {
    static final long serialVersionUID = 7245981689601667138L;
    private String algorithm;
    private transient PKCS12BagAttributeCarrierImpl attrCarrier;
    private transient BigInteger d;
    private transient ECParameterSpec ecSpec;
    private transient GOST3410PublicKeyAlgParameters gostParams;
    private transient DERBitString publicKey;
    private boolean withCompression;

    @Override // java.security.Key
    public String getAlgorithm() {
        return this.algorithm;
    }

    @Override // org.spongycastle.jce.interfaces.ECPrivateKey
    public BigInteger getD() {
        return this.d;
    }

    @Override // java.security.Key
    public String getFormat() {
        return "PKCS#8";
    }

    @Override // java.security.interfaces.ECKey
    public ECParameterSpec getParams() {
        return this.ecSpec;
    }

    @Override // java.security.interfaces.ECPrivateKey
    public BigInteger getS() {
        return this.d;
    }

    protected BCECGOST3410_2012PrivateKey() {
        this.algorithm = "ECGOST3410-2012";
        this.attrCarrier = new PKCS12BagAttributeCarrierImpl();
    }

    public BCECGOST3410_2012PrivateKey(ECPrivateKey eCPrivateKey) {
        this.algorithm = "ECGOST3410-2012";
        this.attrCarrier = new PKCS12BagAttributeCarrierImpl();
        this.d = eCPrivateKey.getS();
        this.algorithm = eCPrivateKey.getAlgorithm();
        this.ecSpec = eCPrivateKey.getParams();
    }

    public BCECGOST3410_2012PrivateKey(ECPrivateKeySpec eCPrivateKeySpec) {
        this.algorithm = "ECGOST3410-2012";
        this.attrCarrier = new PKCS12BagAttributeCarrierImpl();
        this.d = eCPrivateKeySpec.getD();
        if (eCPrivateKeySpec.getParams() != null) {
            this.ecSpec = EC5Util.convertSpec(EC5Util.convertCurve(eCPrivateKeySpec.getParams().getCurve(), eCPrivateKeySpec.getParams().getSeed()), eCPrivateKeySpec.getParams());
        } else {
            this.ecSpec = null;
        }
    }

    public BCECGOST3410_2012PrivateKey(java.security.spec.ECPrivateKeySpec eCPrivateKeySpec) {
        this.algorithm = "ECGOST3410-2012";
        this.attrCarrier = new PKCS12BagAttributeCarrierImpl();
        this.d = eCPrivateKeySpec.getS();
        this.ecSpec = eCPrivateKeySpec.getParams();
    }

    public BCECGOST3410_2012PrivateKey(BCECGOST3410_2012PrivateKey bCECGOST3410_2012PrivateKey) {
        this.algorithm = "ECGOST3410-2012";
        this.attrCarrier = new PKCS12BagAttributeCarrierImpl();
        this.d = bCECGOST3410_2012PrivateKey.d;
        this.ecSpec = bCECGOST3410_2012PrivateKey.ecSpec;
        this.withCompression = bCECGOST3410_2012PrivateKey.withCompression;
        this.attrCarrier = bCECGOST3410_2012PrivateKey.attrCarrier;
        this.publicKey = bCECGOST3410_2012PrivateKey.publicKey;
        this.gostParams = bCECGOST3410_2012PrivateKey.gostParams;
    }

    public BCECGOST3410_2012PrivateKey(String str, ECPrivateKeyParameters eCPrivateKeyParameters, BCECGOST3410_2012PublicKey bCECGOST3410_2012PublicKey, ECParameterSpec eCParameterSpec) {
        this.algorithm = "ECGOST3410-2012";
        this.attrCarrier = new PKCS12BagAttributeCarrierImpl();
        ECDomainParameters parameters = eCPrivateKeyParameters.getParameters();
        this.algorithm = str;
        this.d = eCPrivateKeyParameters.getD();
        if (eCParameterSpec == null) {
            this.ecSpec = new ECParameterSpec(EC5Util.convertCurve(parameters.getCurve(), parameters.getSeed()), new ECPoint(parameters.getG().getAffineXCoord().toBigInteger(), parameters.getG().getAffineYCoord().toBigInteger()), parameters.getN(), parameters.getH().intValue());
        } else {
            this.ecSpec = eCParameterSpec;
        }
        this.gostParams = bCECGOST3410_2012PublicKey.getGostParams();
        this.publicKey = getPublicKeyDetails(bCECGOST3410_2012PublicKey);
    }

    public BCECGOST3410_2012PrivateKey(String str, ECPrivateKeyParameters eCPrivateKeyParameters, BCECGOST3410_2012PublicKey bCECGOST3410_2012PublicKey, org.spongycastle.jce.spec.ECParameterSpec eCParameterSpec) {
        this.algorithm = "ECGOST3410-2012";
        this.attrCarrier = new PKCS12BagAttributeCarrierImpl();
        ECDomainParameters parameters = eCPrivateKeyParameters.getParameters();
        this.algorithm = str;
        this.d = eCPrivateKeyParameters.getD();
        if (eCParameterSpec == null) {
            this.ecSpec = new ECParameterSpec(EC5Util.convertCurve(parameters.getCurve(), parameters.getSeed()), new ECPoint(parameters.getG().getAffineXCoord().toBigInteger(), parameters.getG().getAffineYCoord().toBigInteger()), parameters.getN(), parameters.getH().intValue());
        } else {
            this.ecSpec = new ECParameterSpec(EC5Util.convertCurve(eCParameterSpec.getCurve(), eCParameterSpec.getSeed()), new ECPoint(eCParameterSpec.getG().getAffineXCoord().toBigInteger(), eCParameterSpec.getG().getAffineYCoord().toBigInteger()), eCParameterSpec.getN(), eCParameterSpec.getH().intValue());
        }
        this.gostParams = bCECGOST3410_2012PublicKey.getGostParams();
        this.publicKey = getPublicKeyDetails(bCECGOST3410_2012PublicKey);
    }

    public BCECGOST3410_2012PrivateKey(String str, ECPrivateKeyParameters eCPrivateKeyParameters) {
        this.algorithm = "ECGOST3410-2012";
        this.attrCarrier = new PKCS12BagAttributeCarrierImpl();
        this.algorithm = str;
        this.d = eCPrivateKeyParameters.getD();
        this.ecSpec = null;
    }

    BCECGOST3410_2012PrivateKey(PrivateKeyInfo privateKeyInfo) throws IOException {
        this.algorithm = "ECGOST3410-2012";
        this.attrCarrier = new PKCS12BagAttributeCarrierImpl();
        populateFromPrivKeyInfo(privateKeyInfo);
    }

    private void populateFromPrivKeyInfo(PrivateKeyInfo privateKeyInfo) throws IOException {
        ASN1Primitive aSN1Primitive = privateKeyInfo.getPrivateKeyAlgorithm().getParameters().toASN1Primitive();
        if ((aSN1Primitive instanceof ASN1Sequence) && (ASN1Sequence.getInstance(aSN1Primitive).size() == 2 || ASN1Sequence.getInstance(aSN1Primitive).size() == 3)) {
            GOST3410PublicKeyAlgParameters gOST3410PublicKeyAlgParameters = GOST3410PublicKeyAlgParameters.getInstance(privateKeyInfo.getPrivateKeyAlgorithm().getParameters());
            this.gostParams = gOST3410PublicKeyAlgParameters;
            ECNamedCurveParameterSpec parameterSpec = ECGOST3410NamedCurveTable.getParameterSpec(ECGOST3410NamedCurves.getName(gOST3410PublicKeyAlgParameters.getPublicKeyParamSet()));
            this.ecSpec = new ECNamedCurveSpec(ECGOST3410NamedCurves.getName(this.gostParams.getPublicKeyParamSet()), EC5Util.convertCurve(parameterSpec.getCurve(), parameterSpec.getSeed()), new ECPoint(parameterSpec.getG().getAffineXCoord().toBigInteger(), parameterSpec.getG().getAffineYCoord().toBigInteger()), parameterSpec.getN(), parameterSpec.getH());
            ASN1Encodable privateKey = privateKeyInfo.parsePrivateKey();
            if (privateKey instanceof ASN1Integer) {
                this.d = ASN1Integer.getInstance(privateKey).getPositiveValue();
                return;
            }
            byte[] octets = ASN1OctetString.getInstance(privateKey).getOctets();
            byte[] bArr = new byte[octets.length];
            for (int i = 0; i != octets.length; i++) {
                bArr[i] = octets[(octets.length - 1) - i];
            }
            this.d = new BigInteger(1, bArr);
            return;
        }
        X962Parameters x962Parameters = X962Parameters.getInstance(privateKeyInfo.getPrivateKeyAlgorithm().getParameters());
        if (x962Parameters.isNamedCurve()) {
            ASN1ObjectIdentifier aSN1ObjectIdentifier = ASN1ObjectIdentifier.getInstance(x962Parameters.getParameters());
            X9ECParameters namedCurveByOid = ECUtil.getNamedCurveByOid(aSN1ObjectIdentifier);
            if (namedCurveByOid == null) {
                ECDomainParameters byOID = ECGOST3410NamedCurves.getByOID(aSN1ObjectIdentifier);
                this.ecSpec = new ECNamedCurveSpec(ECGOST3410NamedCurves.getName(aSN1ObjectIdentifier), EC5Util.convertCurve(byOID.getCurve(), byOID.getSeed()), new ECPoint(byOID.getG().getAffineXCoord().toBigInteger(), byOID.getG().getAffineYCoord().toBigInteger()), byOID.getN(), byOID.getH());
            } else {
                this.ecSpec = new ECNamedCurveSpec(ECUtil.getCurveName(aSN1ObjectIdentifier), EC5Util.convertCurve(namedCurveByOid.getCurve(), namedCurveByOid.getSeed()), new ECPoint(namedCurveByOid.getG().getAffineXCoord().toBigInteger(), namedCurveByOid.getG().getAffineYCoord().toBigInteger()), namedCurveByOid.getN(), namedCurveByOid.getH());
            }
        } else if (x962Parameters.isImplicitlyCA()) {
            this.ecSpec = null;
        } else {
            X9ECParameters x9ECParameters = X9ECParameters.getInstance(x962Parameters.getParameters());
            this.ecSpec = new ECParameterSpec(EC5Util.convertCurve(x9ECParameters.getCurve(), x9ECParameters.getSeed()), new ECPoint(x9ECParameters.getG().getAffineXCoord().toBigInteger(), x9ECParameters.getG().getAffineYCoord().toBigInteger()), x9ECParameters.getN(), x9ECParameters.getH().intValue());
        }
        ASN1Encodable privateKey2 = privateKeyInfo.parsePrivateKey();
        if (privateKey2 instanceof ASN1Integer) {
            this.d = ASN1Integer.getInstance(privateKey2).getValue();
            return;
        }
        org.spongycastle.asn1.sec.ECPrivateKey eCPrivateKey = org.spongycastle.asn1.sec.ECPrivateKey.getInstance(privateKey2);
        this.d = eCPrivateKey.getKey();
        this.publicKey = eCPrivateKey.getPublicKey();
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        X962Parameters x962Parameters;
        int orderBitLength;
        org.spongycastle.asn1.sec.ECPrivateKey eCPrivateKey;
        boolean z = this.d.bitLength() > 256;
        ASN1ObjectIdentifier aSN1ObjectIdentifier = z ? RosstandartObjectIdentifiers.id_tc26_gost_3410_12_512 : RosstandartObjectIdentifiers.id_tc26_gost_3410_12_256;
        int i = z ? 64 : 32;
        if (this.gostParams != null) {
            byte[] bArr = new byte[i];
            extractBytes(bArr, i, 0, getS());
            try {
                return new PrivateKeyInfo(new AlgorithmIdentifier(aSN1ObjectIdentifier, this.gostParams), new DEROctetString(bArr)).getEncoded("DER");
            } catch (IOException unused) {
                return null;
            }
        }
        ECParameterSpec eCParameterSpec = this.ecSpec;
        if (eCParameterSpec instanceof ECNamedCurveSpec) {
            ASN1ObjectIdentifier namedCurveOid = ECUtil.getNamedCurveOid(((ECNamedCurveSpec) eCParameterSpec).getName());
            if (namedCurveOid == null) {
                namedCurveOid = new ASN1ObjectIdentifier(((ECNamedCurveSpec) this.ecSpec).getName());
            }
            x962Parameters = new X962Parameters(namedCurveOid);
            orderBitLength = ECUtil.getOrderBitLength(BouncyCastleProvider.CONFIGURATION, this.ecSpec.getOrder(), getS());
        } else if (eCParameterSpec == null) {
            x962Parameters = new X962Parameters((ASN1Null) DERNull.INSTANCE);
            orderBitLength = ECUtil.getOrderBitLength(BouncyCastleProvider.CONFIGURATION, null, getS());
        } else {
            ECCurve eCCurveConvertCurve = EC5Util.convertCurve(eCParameterSpec.getCurve());
            x962Parameters = new X962Parameters(new X9ECParameters(eCCurveConvertCurve, EC5Util.convertPoint(eCCurveConvertCurve, this.ecSpec.getGenerator(), this.withCompression), this.ecSpec.getOrder(), BigInteger.valueOf(this.ecSpec.getCofactor()), this.ecSpec.getCurve().getSeed()));
            orderBitLength = ECUtil.getOrderBitLength(BouncyCastleProvider.CONFIGURATION, this.ecSpec.getOrder(), getS());
        }
        if (this.publicKey != null) {
            eCPrivateKey = new org.spongycastle.asn1.sec.ECPrivateKey(orderBitLength, getS(), this.publicKey, x962Parameters);
        } else {
            eCPrivateKey = new org.spongycastle.asn1.sec.ECPrivateKey(orderBitLength, getS(), x962Parameters);
        }
        try {
            return new PrivateKeyInfo(new AlgorithmIdentifier(aSN1ObjectIdentifier, x962Parameters.toASN1Primitive()), eCPrivateKey.toASN1Primitive()).getEncoded("DER");
        } catch (IOException unused2) {
            return null;
        }
    }

    private void extractBytes(byte[] bArr, int i, int i2, BigInteger bigInteger) {
        byte[] byteArray = bigInteger.toByteArray();
        if (byteArray.length < i) {
            byte[] bArr2 = new byte[i];
            System.arraycopy(byteArray, 0, bArr2, i - byteArray.length, byteArray.length);
            byteArray = bArr2;
        }
        for (int i3 = 0; i3 != i; i3++) {
            bArr[i2 + i3] = byteArray[(byteArray.length - 1) - i3];
        }
    }

    @Override // org.spongycastle.jce.interfaces.ECKey
    public org.spongycastle.jce.spec.ECParameterSpec getParameters() {
        ECParameterSpec eCParameterSpec = this.ecSpec;
        if (eCParameterSpec == null) {
            return null;
        }
        return EC5Util.convertSpec(eCParameterSpec, this.withCompression);
    }

    org.spongycastle.jce.spec.ECParameterSpec engineGetSpec() {
        ECParameterSpec eCParameterSpec = this.ecSpec;
        if (eCParameterSpec != null) {
            return EC5Util.convertSpec(eCParameterSpec, this.withCompression);
        }
        return BouncyCastleProvider.CONFIGURATION.getEcImplicitlyCa();
    }

    @Override // org.spongycastle.jce.interfaces.PKCS12BagAttributeCarrier
    public void setBagAttribute(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Encodable aSN1Encodable) {
        this.attrCarrier.setBagAttribute(aSN1ObjectIdentifier, aSN1Encodable);
    }

    @Override // org.spongycastle.jce.interfaces.PKCS12BagAttributeCarrier
    public ASN1Encodable getBagAttribute(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return this.attrCarrier.getBagAttribute(aSN1ObjectIdentifier);
    }

    @Override // org.spongycastle.jce.interfaces.PKCS12BagAttributeCarrier
    public Enumeration getBagAttributeKeys() {
        return this.attrCarrier.getBagAttributeKeys();
    }

    @Override // org.spongycastle.jce.interfaces.ECPointEncoder
    public void setPointFormat(String str) {
        this.withCompression = !"UNCOMPRESSED".equalsIgnoreCase(str);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof BCECGOST3410_2012PrivateKey)) {
            return false;
        }
        BCECGOST3410_2012PrivateKey bCECGOST3410_2012PrivateKey = (BCECGOST3410_2012PrivateKey) obj;
        return getD().equals(bCECGOST3410_2012PrivateKey.getD()) && engineGetSpec().equals(bCECGOST3410_2012PrivateKey.engineGetSpec());
    }

    public int hashCode() {
        return getD().hashCode() ^ engineGetSpec().hashCode();
    }

    public String toString() {
        return ECUtil.privateKeyToString(this.algorithm, this.d, engineGetSpec());
    }

    private DERBitString getPublicKeyDetails(BCECGOST3410_2012PublicKey bCECGOST3410_2012PublicKey) {
        try {
            return SubjectPublicKeyInfo.getInstance(ASN1Primitive.fromByteArray(bCECGOST3410_2012PublicKey.getEncoded())).getPublicKeyData();
        } catch (IOException unused) {
            return null;
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        populateFromPrivKeyInfo(PrivateKeyInfo.getInstance(ASN1Primitive.fromByteArray((byte[]) objectInputStream.readObject())));
        this.attrCarrier = new PKCS12BagAttributeCarrierImpl();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(getEncoded());
    }
}
