package org.spongycastle.cert.crmf;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.security.SecureRandom;
import org.spongycastle.asn1.DERNull;
import org.spongycastle.asn1.cmp.CMPObjectIdentifiers;
import org.spongycastle.asn1.cmp.PBMParameter;
import org.spongycastle.asn1.iana.IANAObjectIdentifiers;
import org.spongycastle.asn1.oiw.OIWObjectIdentifiers;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.operator.GenericKey;
import org.spongycastle.operator.MacCalculator;
import org.spongycastle.operator.RuntimeOperatorException;
import org.spongycastle.util.Strings;

/* loaded from: classes4.dex */
public class PKMACBuilder {
    private PKMACValuesCalculator calculator;
    private int iterationCount;
    private AlgorithmIdentifier mac;
    private int maxIterations;
    private AlgorithmIdentifier owf;
    private PBMParameter parameters;
    private SecureRandom random;
    private int saltLength;

    public PKMACBuilder setSecureRandom(SecureRandom secureRandom) {
        this.random = secureRandom;
        return this;
    }

    public PKMACBuilder(PKMACValuesCalculator pKMACValuesCalculator) {
        this(new AlgorithmIdentifier(OIWObjectIdentifiers.idSHA1), 1000, new AlgorithmIdentifier(IANAObjectIdentifiers.hmacSHA1, DERNull.INSTANCE), pKMACValuesCalculator);
    }

    public PKMACBuilder(PKMACValuesCalculator pKMACValuesCalculator, int i) {
        this.saltLength = 20;
        this.maxIterations = i;
        this.calculator = pKMACValuesCalculator;
    }

    private PKMACBuilder(AlgorithmIdentifier algorithmIdentifier, int i, AlgorithmIdentifier algorithmIdentifier2, PKMACValuesCalculator pKMACValuesCalculator) {
        this.saltLength = 20;
        this.owf = algorithmIdentifier;
        this.iterationCount = i;
        this.mac = algorithmIdentifier2;
        this.calculator = pKMACValuesCalculator;
    }

    public PKMACBuilder setSaltLength(int i) {
        if (i < 8) {
            throw new IllegalArgumentException("salt length must be at least 8 bytes");
        }
        this.saltLength = i;
        return this;
    }

    public PKMACBuilder setIterationCount(int i) {
        if (i < 100) {
            throw new IllegalArgumentException("iteration count must be at least 100");
        }
        checkIterationCountCeiling(i);
        this.iterationCount = i;
        return this;
    }

    public PKMACBuilder setParameters(PBMParameter pBMParameter) {
        checkIterationCountCeiling(pBMParameter.getIterationCount().getValue().intValue());
        this.parameters = pBMParameter;
        return this;
    }

    public MacCalculator build(char[] cArr) throws CRMFException {
        PBMParameter pBMParameter = this.parameters;
        if (pBMParameter != null) {
            return genCalculator(pBMParameter, cArr);
        }
        byte[] bArr = new byte[this.saltLength];
        if (this.random == null) {
            this.random = new SecureRandom();
        }
        this.random.nextBytes(bArr);
        return genCalculator(new PBMParameter(bArr, this.owf, this.iterationCount, this.mac), cArr);
    }

    private void checkIterationCountCeiling(int i) {
        int i2 = this.maxIterations;
        if (i2 > 0 && i > i2) {
            throw new IllegalArgumentException("iteration count exceeds limit (" + i + " > " + this.maxIterations + ")");
        }
    }

    private MacCalculator genCalculator(final PBMParameter pBMParameter, char[] cArr) throws CRMFException {
        byte[] uTF8ByteArray = Strings.toUTF8ByteArray(cArr);
        byte[] octets = pBMParameter.getSalt().getOctets();
        final byte[] bArrCalculateDigest = new byte[uTF8ByteArray.length + octets.length];
        System.arraycopy(uTF8ByteArray, 0, bArrCalculateDigest, 0, uTF8ByteArray.length);
        System.arraycopy(octets, 0, bArrCalculateDigest, uTF8ByteArray.length, octets.length);
        this.calculator.setup(pBMParameter.getOwf(), pBMParameter.getMac());
        int iIntValue = pBMParameter.getIterationCount().getValue().intValue();
        do {
            bArrCalculateDigest = this.calculator.calculateDigest(bArrCalculateDigest);
            iIntValue--;
        } while (iIntValue > 0);
        return new MacCalculator() { // from class: org.spongycastle.cert.crmf.PKMACBuilder.1
            ByteArrayOutputStream bOut = new ByteArrayOutputStream();

            @Override // org.spongycastle.operator.MacCalculator
            public OutputStream getOutputStream() {
                return this.bOut;
            }

            @Override // org.spongycastle.operator.MacCalculator
            public AlgorithmIdentifier getAlgorithmIdentifier() {
                return new AlgorithmIdentifier(CMPObjectIdentifiers.passwordBasedMac, pBMParameter);
            }

            @Override // org.spongycastle.operator.MacCalculator
            public GenericKey getKey() {
                return new GenericKey(getAlgorithmIdentifier(), bArrCalculateDigest);
            }

            @Override // org.spongycastle.operator.MacCalculator
            public byte[] getMac() {
                try {
                    return PKMACBuilder.this.calculator.calculateMac(bArrCalculateDigest, this.bOut.toByteArray());
                } catch (CRMFException e) {
                    throw new RuntimeOperatorException("exception calculating mac: " + e.getMessage(), e);
                }
            }
        };
    }
}
