package com.RNRSA;

import android.content.Context;
import android.security.keystore.KeyGenParameterSpec;
import android.util.Base64;
import com.oblador.keychain.cipherStorage.CipherStorageBase;
import com.oblador.keychain.cipherStorage.CipherStorageKeystoreAesCbc;
import com.oblador.keychain.cipherStorage.CipherStorageKeystoreRsaEcb;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.pkcs.PrivateKeyInfo;
import org.spongycastle.asn1.pkcs.RSAPrivateKey;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;
import org.spongycastle.openssl.PEMParser;
import org.spongycastle.operator.OperatorCreationException;
import org.spongycastle.pkcs.PKCS10CertificationRequest;
import org.spongycastle.util.io.pem.PemObject;
import org.spongycastle.util.io.pem.PemReader;
import org.spongycastle.util.io.pem.PemWriter;

/* loaded from: classes5.dex */
public class RSA {
    public static final String ALGORITHM = "RSA";
    private static final String CSR_HEADER = "CERTIFICATE REQUEST";
    public static Charset CharsetUTF_8 = null;
    private static final String PRIVATE_HEADER = "RSA PRIVATE KEY";
    private static final String PUBLIC_HEADER = "RSA PUBLIC KEY";
    private PKCS10CertificationRequest csr;
    private String keyTag;
    private PrivateKey privateKey;
    private PublicKey publicKey;

    public RSA() {
        setupCharset();
    }

    public RSA(String str) throws NoSuchAlgorithmException, IOException, KeyStoreException, CertificateException, UnrecoverableEntryException {
        setupCharset();
        this.keyTag = str;
        loadFromKeystore();
    }

    private void setupCharset() {
        CharsetUTF_8 = StandardCharsets.UTF_8;
    }

    public String getPublicKey() throws IOException {
        return dataToPem(PUBLIC_HEADER, publicKeyToPkcs1(this.publicKey));
    }

    public String getPrivateKey() throws IOException {
        return dataToPem(PRIVATE_HEADER, privateKeyToPkcs1(this.privateKey));
    }

    public void setPublicKey(String str) throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
        this.publicKey = pkcs1ToPublicKey(str);
    }

    public void setPrivateKey(String str) throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
        this.privateKey = pkcs1ToPrivateKey(pemToData(str));
    }

    private byte[] encrypt(byte[] bArr) throws BadPaddingException, NoSuchPaddingException, InvalidKeySpecException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException {
        Cipher cipher = Cipher.getInstance("RSA/NONE/PKCS1Padding");
        cipher.init(1, this.publicKey);
        return cipher.doFinal(bArr);
    }

    public String encrypt64(String str) throws BadPaddingException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException {
        return Base64.encodeToString(encrypt(Base64.decode(str, 0)), 0);
    }

    public String encrypt(String str) throws BadPaddingException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException {
        return Base64.encodeToString(encrypt(str.getBytes(CharsetUTF_8)), 0);
    }

    private byte[] decrypt(byte[] bArr) throws BadPaddingException, NoSuchPaddingException, InvalidKeySpecException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException {
        Cipher cipher = Cipher.getInstance("RSA/NONE/PKCS1Padding");
        cipher.init(2, this.privateKey);
        return cipher.doFinal(bArr);
    }

    public String decrypt(String str) throws BadPaddingException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException {
        return new String(decrypt(Base64.decode(str, 0)), CharsetUTF_8);
    }

    public String decrypt64(String str) throws BadPaddingException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException {
        return Base64.encodeToString(decrypt(Base64.decode(str, 0)), 0);
    }

    private String sign(byte[] bArr, String str) throws BadPaddingException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        Signature signature = Signature.getInstance(str);
        signature.initSign(this.privateKey);
        signature.update(bArr);
        return Base64.encodeToString(signature.sign(), 0);
    }

    public String sign64(String str, String str2) throws BadPaddingException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        return sign(Base64.decode(str, 0), str2);
    }

    public String sign(String str, String str2) throws BadPaddingException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        return sign(str.getBytes(CharsetUTF_8), str2);
    }

    private boolean verify(byte[] bArr, byte[] bArr2, String str) throws BadPaddingException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        Signature signature = Signature.getInstance(str);
        signature.initVerify(this.publicKey);
        signature.update(bArr2);
        return signature.verify(bArr);
    }

    public boolean verify64(String str, String str2, String str3) throws BadPaddingException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        Signature.getInstance(str3).initVerify(this.publicKey);
        return verify(Base64.decode(str, 0), Base64.decode(str2, 0), str3);
    }

    public boolean verify(String str, String str2, String str3) throws BadPaddingException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        Signature.getInstance(str3).initVerify(this.publicKey);
        return verify(Base64.decode(str, 0), str2.getBytes(CharsetUTF_8), str3);
    }

    private String dataToPem(String str, byte[] bArr) throws IOException {
        PemObject pemObject = new PemObject(str, bArr);
        StringWriter stringWriter = new StringWriter();
        PemWriter pemWriter = new PemWriter(stringWriter);
        pemWriter.writeObject(pemObject);
        pemWriter.close();
        return stringWriter.toString();
    }

    private byte[] pemToData(String str) throws IOException {
        return new PemReader(new StringReader(str)).readPemObject().getContent();
    }

    private PublicKey pkcs1ToPublicKey(String str) throws Throwable {
        StringReader stringReader = null;
        try {
            StringReader stringReader2 = new StringReader(str);
            try {
                PublicKey publicKeyGeneratePublic = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(((SubjectPublicKeyInfo) new PEMParser(stringReader2).readObject()).getEncoded()));
                stringReader2.close();
                return publicKeyGeneratePublic;
            } catch (Throwable th) {
                th = th;
                stringReader = stringReader2;
                if (stringReader != null) {
                    stringReader.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private PrivateKey pkcs1ToPrivateKey(byte[] bArr) throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
        RSAPrivateKey rSAPrivateKey = RSAPrivateKey.getInstance(new ASN1InputStream(bArr).readObject());
        return KeyFactory.getInstance("RSA").generatePrivate(new RSAPrivateKeySpec(rSAPrivateKey.getModulus(), rSAPrivateKey.getPrivateExponent()));
    }

    private byte[] publicKeyToPkcs1(PublicKey publicKey) throws IOException {
        return SubjectPublicKeyInfo.getInstance(publicKey.getEncoded()).parsePublicKey().getEncoded();
    }

    private byte[] privateKeyToPkcs1(PrivateKey privateKey) throws IOException {
        return PrivateKeyInfo.getInstance(privateKey.getEncoded()).parsePrivateKey().toASN1Primitive().getEncoded();
    }

    public void loadFromKeystore() throws NoSuchAlgorithmException, IOException, KeyStoreException, CertificateException, UnrecoverableEntryException {
        KeyStore keyStore = KeyStore.getInstance(CipherStorageBase.KEYSTORE_TYPE);
        keyStore.load(null);
        KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) keyStore.getEntry(this.keyTag, null);
        if (privateKeyEntry != null) {
            this.privateKey = privateKeyEntry.getPrivateKey();
            this.publicKey = privateKeyEntry.getCertificate().getPublicKey();
        }
    }

    public void deletePrivateKey() throws NoSuchAlgorithmException, IOException, KeyStoreException, CertificateException, UnrecoverableEntryException {
        KeyStore keyStore = KeyStore.getInstance(CipherStorageBase.KEYSTORE_TYPE);
        keyStore.load(null);
        keyStore.deleteEntry(this.keyTag);
        this.privateKey = null;
        this.publicKey = null;
    }

    public void generate() throws NoSuchAlgorithmException, IOException, InvalidAlgorithmParameterException {
        generate(2048);
    }

    public void generate(int i) throws NoSuchAlgorithmException, IOException, InvalidAlgorithmParameterException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(i);
        KeyPair keyPairGenKeyPair = keyPairGenerator.genKeyPair();
        this.publicKey = keyPairGenKeyPair.getPublic();
        this.privateKey = keyPairGenKeyPair.getPrivate();
    }

    public void generate(String str, Context context) throws NoSuchAlgorithmException, IOException, NoSuchProviderException, InvalidAlgorithmParameterException {
        generate(str, 2048, context);
    }

    public void generate(String str, int i, Context context) throws NoSuchAlgorithmException, IOException, NoSuchProviderException, InvalidAlgorithmParameterException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA", CipherStorageBase.KEYSTORE_TYPE);
        keyPairGenerator.initialize(new KeyGenParameterSpec.Builder(str, 15).setKeySize(i).setDigests("SHA-256", "SHA-512", "SHA-1").setEncryptionPaddings(CipherStorageKeystoreRsaEcb.PADDING_PKCS1).setSignaturePaddings("PKCS1").build());
        this.publicKey = keyPairGenerator.genKeyPair().getPublic();
    }

    public void generateCSR(String str, String str2, Context context) throws OperatorCreationException, IOException {
        this.csr = CsrHelper.generateCSR(this.publicKey, str, this.keyTag, str2);
    }

    public void generateCSRWithEC(String str, String str2, int i, Context context) throws NoSuchAlgorithmException, IOException, KeyStoreException, CertificateException, NoSuchProviderException, UnrecoverableEntryException, InvalidAlgorithmParameterException {
        deletePrivateKey();
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC", CipherStorageBase.KEYSTORE_TYPE);
        keyPairGenerator.initialize(new KeyGenParameterSpec.Builder(str2, 12).setDigests("SHA-256", "SHA-512", "SHA-384", "NONE").setKeySize(i).setAlgorithmParameterSpec(new ECGenParameterSpec("secp256r1")).setEncryptionPaddings(CipherStorageKeystoreAesCbc.PADDING_PKCS7).build());
        PublicKey publicKey = keyPairGenerator.genKeyPair().getPublic();
        this.publicKey = publicKey;
        try {
            this.csr = CsrHelper.generateCSRWithEC(publicKey, str, str2);
        } catch (OperatorCreationException e) {
            e.printStackTrace();
        }
    }

    public String getCSR() throws IOException {
        return dataToPem(CSR_HEADER, this.csr.getEncoded());
    }
}
