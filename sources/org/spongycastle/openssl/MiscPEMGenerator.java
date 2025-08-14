package org.spongycastle.openssl;

import com.clevertap.android.sdk.Constants;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import net.sf.scuba.smartcards.ISO7816;
import org.jmrtd.lds.CVCAFile;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.cms.ContentInfo;
import org.spongycastle.asn1.oiw.OIWObjectIdentifiers;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.asn1.pkcs.PrivateKeyInfo;
import org.spongycastle.asn1.x509.DSAParameter;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;
import org.spongycastle.asn1.x9.X9ObjectIdentifiers;
import org.spongycastle.cert.X509AttributeCertificateHolder;
import org.spongycastle.cert.X509CRLHolder;
import org.spongycastle.cert.X509CertificateHolder;
import org.spongycastle.pkcs.PKCS10CertificationRequest;
import org.spongycastle.pkcs.PKCS8EncryptedPrivateKeyInfo;
import org.spongycastle.util.Strings;
import org.spongycastle.util.io.pem.PemGenerationException;
import org.spongycastle.util.io.pem.PemHeader;
import org.spongycastle.util.io.pem.PemObject;
import org.spongycastle.util.io.pem.PemObjectGenerator;

/* loaded from: classes7.dex */
public class MiscPEMGenerator implements PemObjectGenerator {
    private static final ASN1ObjectIdentifier[] dsaOids = {X9ObjectIdentifiers.id_dsa, OIWObjectIdentifiers.dsaWithSHA1};
    private static final byte[] hexEncodingTable = {ISO7816.INS_DECREASE, 49, ISO7816.INS_INCREASE, 51, ISO7816.INS_DECREASE_STAMPED, 53, 54, 55, 56, 57, 65, CVCAFile.CAR_TAG, 67, ISO7816.INS_REHABILITATE_CHV, 69, 70};
    private final PEMEncryptor encryptor;
    private final Object obj;

    public MiscPEMGenerator(Object obj) {
        this.obj = obj;
        this.encryptor = null;
    }

    public MiscPEMGenerator(Object obj, PEMEncryptor pEMEncryptor) {
        this.obj = obj;
        this.encryptor = pEMEncryptor;
    }

    private PemObject createPemObject(Object obj) throws IOException {
        byte[] encoded;
        String str;
        if (obj instanceof PemObject) {
            return (PemObject) obj;
        }
        if (obj instanceof PemObjectGenerator) {
            return ((PemObjectGenerator) obj).generate();
        }
        if (obj instanceof X509CertificateHolder) {
            encoded = ((X509CertificateHolder) obj).getEncoded();
            str = "CERTIFICATE";
        } else if (obj instanceof X509CRLHolder) {
            encoded = ((X509CRLHolder) obj).getEncoded();
            str = "X509 CRL";
        } else if (obj instanceof X509TrustedCertificateBlock) {
            encoded = ((X509TrustedCertificateBlock) obj).getEncoded();
            str = "TRUSTED CERTIFICATE";
        } else if (obj instanceof PrivateKeyInfo) {
            PrivateKeyInfo privateKeyInfo = (PrivateKeyInfo) obj;
            ASN1ObjectIdentifier algorithm = privateKeyInfo.getPrivateKeyAlgorithm().getAlgorithm();
            if (algorithm.equals(PKCSObjectIdentifiers.rsaEncryption)) {
                encoded = privateKeyInfo.parsePrivateKey().toASN1Primitive().getEncoded();
                str = "RSA PRIVATE KEY";
            } else {
                ASN1ObjectIdentifier[] aSN1ObjectIdentifierArr = dsaOids;
                if (algorithm.equals(aSN1ObjectIdentifierArr[0]) || algorithm.equals(aSN1ObjectIdentifierArr[1])) {
                    DSAParameter dSAParameter = DSAParameter.getInstance(privateKeyInfo.getPrivateKeyAlgorithm().getParameters());
                    ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
                    aSN1EncodableVector.add(new ASN1Integer(0L));
                    aSN1EncodableVector.add(new ASN1Integer(dSAParameter.getP()));
                    aSN1EncodableVector.add(new ASN1Integer(dSAParameter.getQ()));
                    aSN1EncodableVector.add(new ASN1Integer(dSAParameter.getG()));
                    BigInteger value = ASN1Integer.getInstance(privateKeyInfo.parsePrivateKey()).getValue();
                    aSN1EncodableVector.add(new ASN1Integer(dSAParameter.getG().modPow(value, dSAParameter.getP())));
                    aSN1EncodableVector.add(new ASN1Integer(value));
                    encoded = new DERSequence(aSN1EncodableVector).getEncoded();
                    str = "DSA PRIVATE KEY";
                } else if (algorithm.equals(X9ObjectIdentifiers.id_ecPublicKey)) {
                    encoded = privateKeyInfo.parsePrivateKey().toASN1Primitive().getEncoded();
                    str = "EC PRIVATE KEY";
                } else {
                    throw new IOException("Cannot identify private key");
                }
            }
        } else if (obj instanceof SubjectPublicKeyInfo) {
            encoded = ((SubjectPublicKeyInfo) obj).getEncoded();
            str = "PUBLIC KEY";
        } else if (obj instanceof X509AttributeCertificateHolder) {
            encoded = ((X509AttributeCertificateHolder) obj).getEncoded();
            str = "ATTRIBUTE CERTIFICATE";
        } else if (obj instanceof PKCS10CertificationRequest) {
            encoded = ((PKCS10CertificationRequest) obj).getEncoded();
            str = "CERTIFICATE REQUEST";
        } else if (obj instanceof PKCS8EncryptedPrivateKeyInfo) {
            encoded = ((PKCS8EncryptedPrivateKeyInfo) obj).getEncoded();
            str = "ENCRYPTED PRIVATE KEY";
        } else if (obj instanceof ContentInfo) {
            encoded = ((ContentInfo) obj).getEncoded();
            str = "PKCS7";
        } else {
            throw new PemGenerationException("unknown object passed - can't encode.");
        }
        PEMEncryptor pEMEncryptor = this.encryptor;
        if (pEMEncryptor != null) {
            String upperCase = Strings.toUpperCase(pEMEncryptor.getAlgorithm());
            if (upperCase.equals("DESEDE")) {
                upperCase = "DES-EDE3-CBC";
            }
            byte[] iv = this.encryptor.getIV();
            byte[] bArrEncrypt = this.encryptor.encrypt(encoded);
            ArrayList arrayList = new ArrayList(2);
            arrayList.add(new PemHeader("Proc-Type", "4,ENCRYPTED"));
            arrayList.add(new PemHeader("DEK-Info", upperCase + Constants.SEPARATOR_COMMA + getHexEncoded(iv)));
            return new PemObject(str, arrayList, bArrEncrypt);
        }
        return new PemObject(str, encoded);
    }

    private String getHexEncoded(byte[] bArr) throws IOException {
        char[] cArr = new char[bArr.length * 2];
        for (int i = 0; i != bArr.length; i++) {
            byte b = bArr[i];
            int i2 = i * 2;
            byte[] bArr2 = hexEncodingTable;
            cArr[i2] = (char) bArr2[(b & 255) >>> 4];
            cArr[i2 + 1] = (char) bArr2[b & 15];
        }
        return new String(cArr);
    }

    @Override // org.spongycastle.util.io.pem.PemObjectGenerator
    public PemObject generate() throws PemGenerationException {
        try {
            return createPemObject(this.obj);
        } catch (IOException e) {
            throw new PemGenerationException("encoding exception: " + e.getMessage(), e);
        }
    }
}
