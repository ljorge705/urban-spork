package org.bouncycastle.pqc.crypto.crystals.dilithium;

import com.drew.metadata.exif.makernotes.OlympusMakernoteDirectory;
import com.drew.metadata.iptc.IptcDirectory;
import java.security.SecureRandom;
import org.bouncycastle.crypto.digests.SHAKEDigest;
import org.bouncycastle.pqc.crypto.crystals.dilithium.Symmetric;
import org.bouncycastle.util.Arrays;
import org.spongycastle.crypto.tls.CipherSuite;

/* loaded from: classes4.dex */
class DilithiumEngine {
    public static final int CrhBytes = 64;
    public static final int DilithiumD = 13;
    public static final int DilithiumN = 256;
    public static final int DilithiumPolyT0PackedBytes = 416;
    public static final int DilithiumPolyT1PackedBytes = 320;
    public static final int DilithiumQ = 8380417;
    public static final int DilithiumQinv = 58728449;
    public static final int DilithiumRootOfUnity = 1753;
    public static final int RndBytes = 32;
    public static final int SeedBytes = 32;
    public static final int TrBytes = 64;
    private final int CryptoBytes;
    private final int CryptoPublicKeyBytes;
    private final int CryptoSecretKeyBytes;
    private final int DilithiumBeta;
    private final int DilithiumCTilde;
    private final int DilithiumEta;
    private final int DilithiumGamma1;
    private final int DilithiumGamma2;
    private final int DilithiumK;
    private final int DilithiumL;
    private final int DilithiumMode;
    private final int DilithiumOmega;
    private final int DilithiumPolyEtaPackedBytes;
    private final int DilithiumPolyVecHPackedBytes;
    private final int DilithiumPolyW1PackedBytes;
    private final int DilithiumPolyZPackedBytes;
    private final int DilithiumTau;
    private final int PolyUniformGamma1NBlocks;
    private final SecureRandom random;
    private final SHAKEDigest shake128Digest = new SHAKEDigest(128);
    private final SHAKEDigest shake256Digest = new SHAKEDigest(256);
    private final Symmetric symmetric;

    DilithiumEngine(int i, SecureRandom secureRandom, boolean z) {
        int i2;
        int i3;
        this.DilithiumMode = i;
        if (i != 2) {
            if (i == 3) {
                this.DilithiumK = 6;
                this.DilithiumL = 5;
                this.DilithiumEta = 4;
                this.DilithiumTau = 49;
                this.DilithiumBeta = CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA256;
                this.DilithiumGamma1 = 524288;
                this.DilithiumGamma2 = 261888;
                this.DilithiumOmega = 55;
                this.DilithiumPolyZPackedBytes = OlympusMakernoteDirectory.TAG_PREVIEW_IMAGE;
                this.DilithiumPolyW1PackedBytes = 128;
                this.DilithiumPolyEtaPackedBytes = 128;
                i3 = 48;
            } else {
                if (i != 5) {
                    throw new IllegalArgumentException("The mode " + i + "is not supported by Crystals Dilithium!");
                }
                this.DilithiumK = 8;
                this.DilithiumL = 7;
                this.DilithiumEta = 2;
                this.DilithiumTau = 60;
                this.DilithiumBeta = 120;
                this.DilithiumGamma1 = 524288;
                this.DilithiumGamma2 = 261888;
                this.DilithiumOmega = 75;
                this.DilithiumPolyZPackedBytes = OlympusMakernoteDirectory.TAG_PREVIEW_IMAGE;
                this.DilithiumPolyW1PackedBytes = 128;
                this.DilithiumPolyEtaPackedBytes = 96;
                i3 = 64;
            }
            this.DilithiumCTilde = i3;
        } else {
            this.DilithiumK = 4;
            this.DilithiumL = 4;
            this.DilithiumEta = 2;
            this.DilithiumTau = 39;
            this.DilithiumBeta = 78;
            this.DilithiumGamma1 = 131072;
            this.DilithiumGamma2 = 95232;
            this.DilithiumOmega = 80;
            this.DilithiumPolyZPackedBytes = 576;
            this.DilithiumPolyW1PackedBytes = 192;
            this.DilithiumPolyEtaPackedBytes = 96;
            this.DilithiumCTilde = 32;
        }
        this.symmetric = z ? new Symmetric.AesSymmetric() : new Symmetric.ShakeSymmetric();
        this.random = secureRandom;
        int i4 = this.DilithiumOmega;
        int i5 = this.DilithiumK;
        int i6 = i4 + i5;
        this.DilithiumPolyVecHPackedBytes = i6;
        this.CryptoPublicKeyBytes = (i5 * 320) + 32;
        int i7 = this.DilithiumL;
        int i8 = this.DilithiumPolyEtaPackedBytes;
        this.CryptoSecretKeyBytes = (i7 * i8) + 96 + (i8 * i5) + (i5 * DilithiumPolyT0PackedBytes);
        this.CryptoBytes = this.DilithiumCTilde + (i7 * this.DilithiumPolyZPackedBytes) + i6;
        int i9 = this.DilithiumGamma1;
        if (i9 == 131072) {
            i2 = this.symmetric.stream256BlockBytes + IptcDirectory.TAG_DIGITAL_TIME_CREATED;
        } else {
            if (i9 != 524288) {
                throw new RuntimeException("Wrong Dilithium Gamma1!");
            }
            i2 = this.symmetric.stream256BlockBytes + 639;
        }
        this.PolyUniformGamma1NBlocks = i2 / this.symmetric.stream256BlockBytes;
    }

    protected Symmetric GetSymmetric() {
        return this.symmetric;
    }

    public byte[][] generateKeyPair() {
        byte[] bArr = new byte[32];
        byte[] bArr2 = new byte[128];
        byte[] bArr3 = new byte[64];
        byte[] bArr4 = new byte[32];
        byte[] bArr5 = new byte[64];
        byte[] bArr6 = new byte[32];
        PolyVecMatrix polyVecMatrix = new PolyVecMatrix(this);
        PolyVecL polyVecL = new PolyVecL(this);
        PolyVecK polyVecK = new PolyVecK(this);
        PolyVecK polyVecK2 = new PolyVecK(this);
        PolyVecK polyVecK3 = new PolyVecK(this);
        this.random.nextBytes(bArr);
        this.shake256Digest.update(bArr, 0, 32);
        this.shake256Digest.doFinal(bArr2, 0, 128);
        System.arraycopy(bArr2, 0, bArr4, 0, 32);
        System.arraycopy(bArr2, 32, bArr5, 0, 64);
        System.arraycopy(bArr2, 96, bArr6, 0, 32);
        polyVecMatrix.expandMatrix(bArr4);
        polyVecL.uniformEta(bArr5, (short) 0);
        polyVecK.uniformEta(bArr5, (short) this.DilithiumL);
        PolyVecL polyVecL2 = new PolyVecL(this);
        polyVecL.copyPolyVecL(polyVecL2);
        polyVecL2.polyVecNtt();
        polyVecMatrix.pointwiseMontgomery(polyVecK2, polyVecL2);
        polyVecK2.reduce();
        polyVecK2.invNttToMont();
        polyVecK2.addPolyVecK(polyVecK);
        polyVecK2.conditionalAddQ();
        polyVecK2.power2Round(polyVecK3);
        byte[] bArrPackPublicKey = Packing.packPublicKey(polyVecK2, this);
        this.shake256Digest.update(bArr4, 0, 32);
        this.shake256Digest.update(bArrPackPublicKey, 0, bArrPackPublicKey.length);
        this.shake256Digest.doFinal(bArr3, 0, 64);
        byte[][] bArrPackSecretKey = Packing.packSecretKey(bArr4, bArr3, bArr6, polyVecK3, polyVecL, polyVecK, this);
        return new byte[][]{bArrPackSecretKey[0], bArrPackSecretKey[1], bArrPackSecretKey[2], bArrPackSecretKey[3], bArrPackSecretKey[4], bArrPackSecretKey[5], bArrPackPublicKey};
    }

    int getCryptoBytes() {
        return this.CryptoBytes;
    }

    int getCryptoPublicKeyBytes() {
        return this.CryptoPublicKeyBytes;
    }

    int getCryptoSecretKeyBytes() {
        return this.CryptoSecretKeyBytes;
    }

    int getDilithiumBeta() {
        return this.DilithiumBeta;
    }

    int getDilithiumCTilde() {
        return this.DilithiumCTilde;
    }

    int getDilithiumEta() {
        return this.DilithiumEta;
    }

    int getDilithiumGamma1() {
        return this.DilithiumGamma1;
    }

    int getDilithiumGamma2() {
        return this.DilithiumGamma2;
    }

    int getDilithiumK() {
        return this.DilithiumK;
    }

    int getDilithiumL() {
        return this.DilithiumL;
    }

    int getDilithiumMode() {
        return this.DilithiumMode;
    }

    int getDilithiumOmega() {
        return this.DilithiumOmega;
    }

    int getDilithiumPolyEtaPackedBytes() {
        return this.DilithiumPolyEtaPackedBytes;
    }

    int getDilithiumPolyVecHPackedBytes() {
        return this.DilithiumPolyVecHPackedBytes;
    }

    int getDilithiumPolyW1PackedBytes() {
        return this.DilithiumPolyW1PackedBytes;
    }

    int getDilithiumPolyZPackedBytes() {
        return this.DilithiumPolyZPackedBytes;
    }

    int getDilithiumTau() {
        return this.DilithiumTau;
    }

    int getPolyUniformGamma1NBlocks() {
        return this.PolyUniformGamma1NBlocks;
    }

    SHAKEDigest getShake128Digest() {
        return this.shake128Digest;
    }

    SHAKEDigest getShake256Digest() {
        return this.shake256Digest;
    }

    public byte[] sign(byte[] bArr, int i, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5, byte[] bArr6, byte[] bArr7) {
        return signSignature(bArr, i, bArr2, bArr3, bArr4, bArr5, bArr6, bArr7);
    }

    public boolean signOpen(byte[] bArr, byte[] bArr2, int i, byte[] bArr3, byte[] bArr4) {
        return signVerify(bArr2, i, bArr, bArr.length, bArr3, bArr4);
    }

    public byte[] signSignature(byte[] bArr, int i, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5, byte[] bArr6, byte[] bArr7) {
        PolyVecK polyVecK;
        PolyVecK polyVecK2;
        byte[] bArr8 = new byte[this.CryptoBytes + i];
        byte[] bArr9 = new byte[64];
        byte[] bArr10 = new byte[64];
        PolyVecL polyVecL = new PolyVecL(this);
        PolyVecL polyVecL2 = new PolyVecL(this);
        PolyVecL polyVecL3 = new PolyVecL(this);
        PolyVecK polyVecK3 = new PolyVecK(this);
        PolyVecK polyVecK4 = new PolyVecK(this);
        PolyVecK polyVecK5 = new PolyVecK(this);
        PolyVecK polyVecK6 = new PolyVecK(this);
        PolyVecK polyVecK7 = new PolyVecK(this);
        Poly poly = new Poly(this);
        PolyVecMatrix polyVecMatrix = new PolyVecMatrix(this);
        Poly poly2 = poly;
        PolyVecK polyVecK8 = polyVecK7;
        PolyVecK polyVecK9 = polyVecK6;
        PolyVecK polyVecK10 = polyVecK5;
        PolyVecK polyVecK11 = polyVecK4;
        PolyVecK polyVecK12 = polyVecK3;
        Packing.unpackSecretKey(polyVecK3, polyVecL, polyVecK4, bArr5, bArr6, bArr7, this);
        int i2 = 0;
        this.shake256Digest.update(bArr4, 0, 64);
        this.shake256Digest.update(bArr, 0, i);
        this.shake256Digest.doFinal(bArr9, 0, 64);
        byte[] bArr11 = new byte[32];
        SecureRandom secureRandom = this.random;
        if (secureRandom != null) {
            secureRandom.nextBytes(bArr11);
        }
        byte[] bArrCopyOf = Arrays.copyOf(bArr3, 128);
        System.arraycopy(bArr11, 0, bArrCopyOf, 32, 32);
        System.arraycopy(bArr9, 0, bArrCopyOf, 64, 64);
        this.shake256Digest.update(bArrCopyOf, 0, 128);
        this.shake256Digest.doFinal(bArr10, 0, 64);
        polyVecMatrix.expandMatrix(bArr2);
        polyVecL.polyVecNtt();
        polyVecK11.polyVecNtt();
        polyVecK12.polyVecNtt();
        int i3 = 0;
        short s = 0;
        while (i3 < 1000) {
            i3++;
            short s2 = (short) (s + 1);
            polyVecL2.uniformGamma1(bArr10, s);
            polyVecL2.copyPolyVecL(polyVecL3);
            polyVecL3.polyVecNtt();
            PolyVecK polyVecK13 = polyVecK10;
            polyVecMatrix.pointwiseMontgomery(polyVecK13, polyVecL3);
            polyVecK13.reduce();
            polyVecK13.invNttToMont();
            polyVecK13.conditionalAddQ();
            PolyVecK polyVecK14 = polyVecK9;
            polyVecK13.decompose(polyVecK14);
            System.arraycopy(polyVecK13.packW1(), i2, bArr8, i2, this.DilithiumK * this.DilithiumPolyW1PackedBytes);
            this.shake256Digest.update(bArr9, i2, 64);
            this.shake256Digest.update(bArr8, i2, this.DilithiumK * this.DilithiumPolyW1PackedBytes);
            this.shake256Digest.doFinal(bArr8, i2, this.DilithiumCTilde);
            Poly poly3 = poly2;
            poly3.challenge(Arrays.copyOfRange(bArr8, i2, 32));
            poly3.polyNtt();
            polyVecL3.pointwisePolyMontgomery(poly3, polyVecL);
            polyVecL3.invNttToMont();
            polyVecL3.addPolyVecL(polyVecL2);
            polyVecL3.reduce();
            if (polyVecL3.checkNorm(this.DilithiumGamma1 - this.DilithiumBeta)) {
                polyVecK2 = polyVecK11;
                polyVecK = polyVecK8;
            } else {
                PolyVecK polyVecK15 = polyVecK11;
                polyVecK = polyVecK8;
                polyVecK.pointwisePolyMontgomery(poly3, polyVecK15);
                polyVecK.invNttToMont();
                polyVecK14.subtract(polyVecK);
                polyVecK14.reduce();
                polyVecK2 = polyVecK15;
                if (polyVecK14.checkNorm(this.DilithiumGamma2 - this.DilithiumBeta)) {
                    continue;
                } else {
                    PolyVecK polyVecK16 = polyVecK12;
                    polyVecK.pointwisePolyMontgomery(poly3, polyVecK16);
                    polyVecK.invNttToMont();
                    polyVecK.reduce();
                    if (polyVecK.checkNorm(this.DilithiumGamma2)) {
                        polyVecK12 = polyVecK16;
                    } else {
                        polyVecK14.addPolyVecK(polyVecK);
                        polyVecK14.conditionalAddQ();
                        polyVecK12 = polyVecK16;
                        if (polyVecK.makeHint(polyVecK14, polyVecK13) <= this.DilithiumOmega) {
                            return Packing.packSignature(bArr8, polyVecL3, polyVecK, this);
                        }
                    }
                }
            }
            polyVecK10 = polyVecK13;
            s = s2;
            polyVecK9 = polyVecK14;
            polyVecK8 = polyVecK;
            polyVecK11 = polyVecK2;
            i2 = 0;
            poly2 = poly3;
        }
        return null;
    }

    public boolean signVerify(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte[] bArr4) {
        byte[] bArr5 = new byte[64];
        byte[] bArr6 = new byte[this.DilithiumCTilde];
        Poly poly = new Poly(this);
        PolyVecMatrix polyVecMatrix = new PolyVecMatrix(this);
        PolyVecL polyVecL = new PolyVecL(this);
        PolyVecK polyVecK = new PolyVecK(this);
        PolyVecK polyVecK2 = new PolyVecK(this);
        PolyVecK polyVecK3 = new PolyVecK(this);
        if (i != this.CryptoBytes) {
            return false;
        }
        PolyVecK polyVecKUnpackPublicKey = Packing.unpackPublicKey(polyVecK, bArr4, this);
        if (!Packing.unpackSignature(polyVecL, polyVecK3, bArr, this)) {
            return false;
        }
        byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, 0, this.DilithiumCTilde);
        if (polyVecL.checkNorm(getDilithiumGamma1() - getDilithiumBeta())) {
            return false;
        }
        this.shake256Digest.update(bArr3, 0, bArr3.length);
        this.shake256Digest.update(bArr4, 0, bArr4.length);
        this.shake256Digest.doFinal(bArr5, 0, 64);
        this.shake256Digest.update(bArr5, 0, 64);
        this.shake256Digest.update(bArr2, 0, i2);
        this.shake256Digest.doFinal(bArr5, 0);
        poly.challenge(Arrays.copyOfRange(bArrCopyOfRange, 0, 32));
        polyVecMatrix.expandMatrix(bArr3);
        polyVecL.polyVecNtt();
        polyVecMatrix.pointwiseMontgomery(polyVecK2, polyVecL);
        poly.polyNtt();
        polyVecKUnpackPublicKey.shiftLeft();
        polyVecKUnpackPublicKey.polyVecNtt();
        polyVecKUnpackPublicKey.pointwisePolyMontgomery(poly, polyVecKUnpackPublicKey);
        polyVecK2.subtract(polyVecKUnpackPublicKey);
        polyVecK2.reduce();
        polyVecK2.invNttToMont();
        polyVecK2.conditionalAddQ();
        polyVecK2.useHint(polyVecK2, polyVecK3);
        byte[] bArrPackW1 = polyVecK2.packW1();
        SHAKEDigest sHAKEDigest = new SHAKEDigest(256);
        sHAKEDigest.update(bArr5, 0, 64);
        sHAKEDigest.update(bArrPackW1, 0, this.DilithiumK * this.DilithiumPolyW1PackedBytes);
        sHAKEDigest.doFinal(bArr6, 0, this.DilithiumCTilde);
        for (int i3 = 0; i3 < this.DilithiumCTilde; i3++) {
            if (bArrCopyOfRange[i3] != bArr6[i3]) {
                return false;
            }
        }
        return true;
    }
}
