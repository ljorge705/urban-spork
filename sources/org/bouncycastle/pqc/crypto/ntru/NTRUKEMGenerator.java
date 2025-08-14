package org.bouncycastle.pqc.crypto.ntru;

import java.security.SecureRandom;
import org.bouncycastle.crypto.EncapsulatedSecretGenerator;
import org.bouncycastle.crypto.SecretWithEncapsulation;
import org.bouncycastle.crypto.digests.SHA3Digest;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.pqc.crypto.util.SecretWithEncapsulationImpl;
import org.bouncycastle.pqc.math.ntru.Polynomial;
import org.bouncycastle.pqc.math.ntru.parameters.NTRUParameterSet;
import org.bouncycastle.util.Arrays;

/* loaded from: classes4.dex */
public class NTRUKEMGenerator implements EncapsulatedSecretGenerator {
    private final SecureRandom random;

    public NTRUKEMGenerator(SecureRandom secureRandom) {
        this.random = secureRandom;
    }

    @Override // org.bouncycastle.crypto.EncapsulatedSecretGenerator
    public SecretWithEncapsulation generateEncapsulated(AsymmetricKeyParameter asymmetricKeyParameter) {
        NTRUPublicKeyParameters nTRUPublicKeyParameters = (NTRUPublicKeyParameters) asymmetricKeyParameter;
        NTRUParameterSet nTRUParameterSet = nTRUPublicKeyParameters.getParameters().parameterSet;
        NTRUSampling nTRUSampling = new NTRUSampling(nTRUParameterSet);
        NTRUOWCPA ntruowcpa = new NTRUOWCPA(nTRUParameterSet);
        int iOwcpaMsgBytes = nTRUParameterSet.owcpaMsgBytes();
        byte[] bArr = new byte[iOwcpaMsgBytes];
        byte[] bArr2 = new byte[nTRUParameterSet.sampleRmBytes()];
        this.random.nextBytes(bArr2);
        PolynomialPair polynomialPairSampleRm = nTRUSampling.sampleRm(bArr2);
        Polynomial polynomialR = polynomialPairSampleRm.r();
        Polynomial polynomialM = polynomialPairSampleRm.m();
        byte[] bArrS3ToBytes = polynomialR.s3ToBytes(nTRUParameterSet.owcpaMsgBytes());
        System.arraycopy(bArrS3ToBytes, 0, bArr, 0, bArrS3ToBytes.length);
        byte[] bArrS3ToBytes2 = polynomialM.s3ToBytes(iOwcpaMsgBytes - nTRUParameterSet.packTrinaryBytes());
        System.arraycopy(bArrS3ToBytes2, 0, bArr, nTRUParameterSet.packTrinaryBytes(), bArrS3ToBytes2.length);
        SHA3Digest sHA3Digest = new SHA3Digest(256);
        sHA3Digest.update(bArr, 0, iOwcpaMsgBytes);
        byte[] bArr3 = new byte[sHA3Digest.getDigestSize()];
        sHA3Digest.doFinal(bArr3, 0);
        polynomialR.z3ToZq();
        byte[] bArrEncrypt = ntruowcpa.encrypt(polynomialR, polynomialM, nTRUPublicKeyParameters.publicKey);
        byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr3, 0, nTRUParameterSet.sharedKeyBytes());
        Arrays.clear(bArr3);
        return new SecretWithEncapsulationImpl(bArrCopyOfRange, bArrEncrypt);
    }
}
