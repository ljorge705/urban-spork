package org.spongycastle.mozilla;

import java.io.IOException;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.X509EncodedKeySpec;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERBitString;
import org.spongycastle.asn1.DEROutputStream;
import org.spongycastle.asn1.mozilla.PublicKeyAndChallenge;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;
import org.spongycastle.operator.ContentVerifier;
import org.spongycastle.operator.ContentVerifierProvider;
import org.spongycastle.operator.OperatorCreationException;
import org.spongycastle.util.Encodable;

/* loaded from: classes7.dex */
public class SignedPublicKeyAndChallenge implements Encodable {
    protected final org.spongycastle.asn1.mozilla.SignedPublicKeyAndChallenge spkacSeq;

    public org.spongycastle.asn1.mozilla.SignedPublicKeyAndChallenge toASN1Structure() {
        return this.spkacSeq;
    }

    public SignedPublicKeyAndChallenge(byte[] bArr) {
        this.spkacSeq = org.spongycastle.asn1.mozilla.SignedPublicKeyAndChallenge.getInstance(bArr);
    }

    protected SignedPublicKeyAndChallenge(org.spongycastle.asn1.mozilla.SignedPublicKeyAndChallenge signedPublicKeyAndChallenge) {
        this.spkacSeq = signedPublicKeyAndChallenge;
    }

    public ASN1Primitive toASN1Primitive() {
        return this.spkacSeq.toASN1Primitive();
    }

    public PublicKeyAndChallenge getPublicKeyAndChallenge() {
        return this.spkacSeq.getPublicKeyAndChallenge();
    }

    public boolean isSignatureValid(ContentVerifierProvider contentVerifierProvider) throws OperatorCreationException, IOException {
        ContentVerifier contentVerifier = contentVerifierProvider.get(this.spkacSeq.getSignatureAlgorithm());
        OutputStream outputStream = contentVerifier.getOutputStream();
        new DEROutputStream(outputStream).writeObject(this.spkacSeq.getPublicKeyAndChallenge());
        outputStream.close();
        return contentVerifier.verify(this.spkacSeq.getSignature().getOctets());
    }

    public boolean verify() throws NoSuchAlgorithmException, SignatureException, InvalidKeyException, NoSuchProviderException {
        return verify(null);
    }

    public boolean verify(String str) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException, NoSuchProviderException {
        Signature signature;
        if (str == null) {
            signature = Signature.getInstance(this.spkacSeq.getSignatureAlgorithm().getAlgorithm().getId());
        } else {
            signature = Signature.getInstance(this.spkacSeq.getSignatureAlgorithm().getAlgorithm().getId(), str);
        }
        signature.initVerify(getPublicKey(str));
        try {
            signature.update(this.spkacSeq.getPublicKeyAndChallenge().getEncoded());
            return signature.verify(this.spkacSeq.getSignature().getBytes());
        } catch (Exception unused) {
            throw new InvalidKeyException("error encoding public key");
        }
    }

    public SubjectPublicKeyInfo getSubjectPublicKeyInfo() {
        return this.spkacSeq.getPublicKeyAndChallenge().getSubjectPublicKeyInfo();
    }

    public String getChallenge() {
        return this.spkacSeq.getPublicKeyAndChallenge().getChallenge().getString();
    }

    public PublicKey getPublicKey(String str) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException {
        SubjectPublicKeyInfo subjectPublicKeyInfo = this.spkacSeq.getPublicKeyAndChallenge().getSubjectPublicKeyInfo();
        try {
            return KeyFactory.getInstance(subjectPublicKeyInfo.getAlgorithm().getAlgorithm().getId(), str).generatePublic(new X509EncodedKeySpec(new DERBitString(subjectPublicKeyInfo).getOctets()));
        } catch (Exception unused) {
            throw new InvalidKeyException("error encoding public key");
        }
    }

    @Override // org.spongycastle.util.Encodable
    public byte[] getEncoded() throws IOException {
        return toASN1Structure().getEncoded();
    }
}
