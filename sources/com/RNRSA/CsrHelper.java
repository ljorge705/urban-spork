package com.RNRSA;

import android.util.Log;
import com.oblador.keychain.cipherStorage.CipherStorageBase;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;
import org.jmrtd.lds.SignedDataUtil;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.asn1.x500.X500Name;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.ExtensionsGenerator;
import org.spongycastle.operator.ContentSigner;
import org.spongycastle.operator.OperatorCreationException;
import org.spongycastle.pkcs.PKCS10CertificationRequest;
import org.spongycastle.pkcs.jcajce.JcaPKCS10CertificationRequestBuilder;

/* loaded from: classes5.dex */
public class CsrHelper {
    private static final String CN_PATTERN = "CN=%s";
    private static final String DEFAULT_SIGNATURE_ALGORITHM = "SHA256withECDSA";

    private static class JCESigner implements ContentSigner {
        private static Map<String, AlgorithmIdentifier> ALGOS;
        private String mAlgo;
        private ByteArrayOutputStream outputStream;
        private Signature signature;

        @Override // org.spongycastle.operator.ContentSigner
        public OutputStream getOutputStream() {
            return this.outputStream;
        }

        static {
            HashMap map = new HashMap();
            ALGOS = map;
            map.put(CsrHelper.DEFAULT_SIGNATURE_ALGORITHM.toLowerCase(), new AlgorithmIdentifier(new ASN1ObjectIdentifier(SignedDataUtil.X9_SHA256_WITH_ECDSA_OID)));
            ALGOS.put("SHA256withRSA".toLowerCase(), new AlgorithmIdentifier(new ASN1ObjectIdentifier(SignedDataUtil.PKCS1_SHA256_WITH_RSA_OID)));
            ALGOS.put("SHA1withRSA".toLowerCase(), new AlgorithmIdentifier(new ASN1ObjectIdentifier(SignedDataUtil.PKCS1_SHA1_WITH_RSA_OID)));
        }

        public JCESigner(String str, String str2) throws InvalidKeyException {
            this.mAlgo = str.toLowerCase();
            try {
                KeyStore.Entry entry = getEntry(str2);
                this.outputStream = new ByteArrayOutputStream();
                this.signature = Signature.getInstance(str);
                this.signature.initSign(((KeyStore.PrivateKeyEntry) entry).getPrivateKey());
            } catch (IOException e) {
                Log.e("generateCSR", "IOException: " + e.getMessage());
                throw new IllegalArgumentException(e.getMessage());
            } catch (GeneralSecurityException e2) {
                Log.e("generateCSR", "generateCSR: " + e2.getMessage());
                throw new IllegalArgumentException(e2.getMessage());
            }
        }

        public KeyStore.Entry getEntry(String str) throws GeneralSecurityException, IOException {
            KeyStore keyStore = KeyStore.getInstance(CipherStorageBase.KEYSTORE_TYPE);
            keyStore.load(null);
            return keyStore.getEntry(str, null);
        }

        @Override // org.spongycastle.operator.ContentSigner
        public AlgorithmIdentifier getAlgorithmIdentifier() {
            AlgorithmIdentifier algorithmIdentifier = ALGOS.get(this.mAlgo);
            if (algorithmIdentifier != null) {
                return algorithmIdentifier;
            }
            throw new IllegalArgumentException("Does not support algo: " + this.mAlgo);
        }

        @Override // org.spongycastle.operator.ContentSigner
        public byte[] getSignature() throws SignatureException {
            try {
                this.signature.update(this.outputStream.toByteArray());
                return this.signature.sign();
            } catch (GeneralSecurityException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public static PKCS10CertificationRequest generateCSR(PublicKey publicKey, String str, String str2, String str3) throws OperatorCreationException, IOException {
        String str4 = String.format(CN_PATTERN, str);
        JCESigner jCESigner = new JCESigner(str3, str2);
        JcaPKCS10CertificationRequestBuilder jcaPKCS10CertificationRequestBuilder = new JcaPKCS10CertificationRequestBuilder(new X500Name(str4), publicKey);
        jcaPKCS10CertificationRequestBuilder.addAttribute(PKCSObjectIdentifiers.pkcs_9_at_extensionRequest, new ExtensionsGenerator().generate());
        return jcaPKCS10CertificationRequestBuilder.build(jCESigner);
    }

    public static PKCS10CertificationRequest generateCSRWithEC(PublicKey publicKey, String str, String str2) throws OperatorCreationException, IOException {
        return generateCSR(publicKey, str, str2, DEFAULT_SIGNATURE_ALGORITHM);
    }
}
