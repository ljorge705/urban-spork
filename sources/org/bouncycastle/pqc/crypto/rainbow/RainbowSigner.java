package org.bouncycastle.pqc.crypto.rainbow;

import java.lang.reflect.Array;
import java.security.SecureRandom;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.pqc.crypto.MessageSigner;
import org.bouncycastle.util.Arrays;

/* loaded from: classes4.dex */
public class RainbowSigner implements MessageSigner {
    private static final int MAXITS = 65536;
    private ComputeInField cf = new ComputeInField();
    private Digest hashAlgo;
    private RainbowKeyParameters key;
    private SecureRandom random;
    int signableDocumentLength;
    private Version version;

    /* renamed from: org.bouncycastle.pqc.crypto.rainbow.RainbowSigner$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$bouncycastle$pqc$crypto$rainbow$Version;

        static {
            int[] iArr = new int[Version.values().length];
            $SwitchMap$org$bouncycastle$pqc$crypto$rainbow$Version = iArr;
            try {
                iArr[Version.CLASSIC.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$bouncycastle$pqc$crypto$rainbow$Version[Version.CIRCUMZENITHAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$bouncycastle$pqc$crypto$rainbow$Version[Version.COMPRESSED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private byte[] genSignature(byte[] bArr) throws RuntimeException {
        short[][] sArr;
        byte[] bArr2;
        byte[] bArr3 = new byte[this.hashAlgo.getDigestSize()];
        this.hashAlgo.update(bArr, 0, bArr.length);
        this.hashAlgo.doFinal(bArr3, 0);
        int v1 = this.key.getParameters().getV1();
        int o1 = this.key.getParameters().getO1();
        int o2 = this.key.getParameters().getO2();
        int m = this.key.getParameters().getM();
        int n2 = this.key.getParameters().getN();
        RainbowPrivateKeyParameters rainbowPrivateKeyParameters = (RainbowPrivateKeyParameters) this.key;
        this.random = new RainbowDRBG(RainbowUtil.hash(this.hashAlgo, rainbowPrivateKeyParameters.sk_seed, bArr3, new byte[this.hashAlgo.getDigestSize()]), rainbowPrivateKeyParameters.getParameters().getHash_algo());
        short[] sArr2 = new short[v1];
        short[] sArr3 = new short[o1];
        short[] sArr4 = new short[o2];
        short[][] sArr5 = (short[][]) Array.newInstance((Class<?>) Short.TYPE, o2, o1);
        short[][] sArr6 = (short[][]) Array.newInstance((Class<?>) Short.TYPE, o2, o2);
        byte[] bArr4 = new byte[rainbowPrivateKeyParameters.getParameters().getLen_salt()];
        short[] sArr7 = new short[o1];
        short[] sArr8 = null;
        short[] sArr9 = new short[m];
        short[] sArr10 = new short[o2];
        short[][] sArrInverse = null;
        int i = 0;
        while (sArrInverse == null && i < 65536) {
            byte[] bArr5 = new byte[v1];
            this.random.nextBytes(bArr5);
            int i2 = 0;
            while (true) {
                bArr2 = bArr3;
                if (i2 >= v1) {
                    break;
                }
                sArr2[i2] = (short) (bArr5[i2] & 255);
                i2++;
                bArr3 = bArr2;
            }
            short[][] sArr11 = (short[][]) Array.newInstance((Class<?>) Short.TYPE, o1, o1);
            for (int i3 = 0; i3 < v1; i3++) {
                int i4 = 0;
                while (i4 < o1) {
                    byte[] bArr6 = bArr4;
                    int i5 = 0;
                    while (true) {
                        int i6 = m;
                        if (i5 < o1) {
                            short[][] sArr12 = sArr6;
                            short sMultElem = GF2Field.multElem(rainbowPrivateKeyParameters.l1_F2[i4][i3][i5], sArr2[i3]);
                            short[] sArr13 = sArr11[i4];
                            sArr13[i5] = GF2Field.addElem(sArr13[i5], sMultElem);
                            i5++;
                            m = i6;
                            sArr6 = sArr12;
                            sArr5 = sArr5;
                        }
                    }
                    i4++;
                    bArr4 = bArr6;
                }
            }
            sArrInverse = this.cf.inverse(sArr11);
            i++;
            bArr3 = bArr2;
        }
        byte[] bArr7 = bArr3;
        int i7 = m;
        short[][] sArr14 = sArr5;
        short[][] sArr15 = sArr6;
        byte[] bArr8 = bArr4;
        for (int i8 = 0; i8 < o1; i8++) {
            sArr3[i8] = this.cf.multiplyMatrix_quad(rainbowPrivateKeyParameters.l1_F1[i8], sArr2);
        }
        for (int i9 = 0; i9 < v1; i9++) {
            for (int i10 = 0; i10 < o2; i10++) {
                sArr4[i10] = this.cf.multiplyMatrix_quad(rainbowPrivateKeyParameters.l2_F1[i10], sArr2);
                for (int i11 = 0; i11 < o1; i11++) {
                    short sMultElem2 = GF2Field.multElem(rainbowPrivateKeyParameters.l2_F2[i10][i9][i11], sArr2[i9]);
                    short[] sArr16 = sArr14[i10];
                    sArr16[i11] = GF2Field.addElem(sArr16[i11], sMultElem2);
                }
                for (int i12 = 0; i12 < o2; i12++) {
                    short sMultElem3 = GF2Field.multElem(rainbowPrivateKeyParameters.l2_F3[i10][i9][i12], sArr2[i9]);
                    short[] sArr17 = sArr15[i10];
                    sArr17[i12] = GF2Field.addElem(sArr17[i12], sMultElem3);
                }
            }
        }
        int i13 = i7;
        byte[] bArr9 = new byte[i13];
        short[] sArr18 = sArr7;
        while (sArr8 == null && i < 65536) {
            short[][] sArr19 = (short[][]) Array.newInstance((Class<?>) Short.TYPE, o2, o2);
            this.random.nextBytes(bArr8);
            short[] sArrMakeMessageRepresentative = makeMessageRepresentative(RainbowUtil.hash(this.hashAlgo, bArr7, bArr8, bArr9));
            byte[] bArr10 = bArr9;
            short[] sArr20 = sArr9;
            System.arraycopy(this.cf.addVect(Arrays.copyOf(sArrMakeMessageRepresentative, o1), this.cf.multiplyMatrix(rainbowPrivateKeyParameters.s1, Arrays.copyOfRange(sArrMakeMessageRepresentative, o1, i13))), 0, sArr20, 0, o1);
            System.arraycopy(sArrMakeMessageRepresentative, o1, sArr20, o1, o2);
            short[] sArrMultiplyMatrix = this.cf.multiplyMatrix(sArrInverse, this.cf.addVect(sArr3, Arrays.copyOf(sArr20, o1)));
            short[][] sArr21 = sArr14;
            short[] sArrMultiplyMatrix2 = this.cf.multiplyMatrix(sArr21, sArrMultiplyMatrix);
            short[] sArr22 = sArr3;
            int i14 = 0;
            while (true) {
                sArr = sArrInverse;
                if (i14 >= o2) {
                    break;
                }
                sArr10[i14] = this.cf.multiplyMatrix_quad(rainbowPrivateKeyParameters.l2_F5[i14], sArrMultiplyMatrix);
                i14++;
                sArrInverse = sArr;
                sArr21 = sArr21;
            }
            sArr14 = sArr21;
            short[] sArr23 = sArr10;
            short[] sArrAddVect = this.cf.addVect(this.cf.addVect(this.cf.addVect(sArrMultiplyMatrix2, sArr23), sArr4), Arrays.copyOfRange(sArr20, o1, i13));
            for (int i15 = 0; i15 < o1; i15++) {
                int i16 = 0;
                while (true) {
                    int i17 = i13;
                    if (i16 < o2) {
                        int i18 = 0;
                        while (i18 < o2) {
                            short[] sArr24 = sArr4;
                            short[] sArr25 = sArr23;
                            short sMultElem4 = GF2Field.multElem(rainbowPrivateKeyParameters.l2_F6[i16][i15][i18], sArrMultiplyMatrix[i15]);
                            short[] sArr26 = sArr19[i16];
                            sArr26[i18] = GF2Field.addElem(sArr26[i18], sMultElem4);
                            i18++;
                            sArrMultiplyMatrix = sArrMultiplyMatrix;
                            sArr4 = sArr24;
                            sArr23 = sArr25;
                        }
                        i16++;
                        i13 = i17;
                    }
                }
            }
            int i19 = i13;
            short[] sArr27 = sArr23;
            short[] sArrSolveEquation = this.cf.solveEquation(this.cf.addMatrix(sArr19, sArr15), sArrAddVect);
            i++;
            sArr18 = sArrMultiplyMatrix;
            bArr9 = bArr10;
            sArr3 = sArr22;
            sArrInverse = sArr;
            sArr8 = sArrSolveEquation;
            sArr9 = sArr20;
            i13 = i19;
            sArr10 = sArr27;
        }
        short[] sArr28 = sArr8 == null ? new short[o2] : sArr8;
        short[] sArrAddVect2 = this.cf.addVect(this.cf.addVect(sArr2, this.cf.multiplyMatrix(rainbowPrivateKeyParameters.t1, sArr18)), this.cf.multiplyMatrix(rainbowPrivateKeyParameters.t4, sArr28));
        short[] sArrAddVect3 = this.cf.addVect(sArr18, this.cf.multiplyMatrix(rainbowPrivateKeyParameters.t3, sArr28));
        short[] sArrCopyOf = Arrays.copyOf(sArrAddVect2, n2);
        System.arraycopy(sArrAddVect3, 0, sArrCopyOf, v1, o1);
        System.arraycopy(sArr28, 0, sArrCopyOf, o1 + v1, o2);
        if (i != 65536) {
            return Arrays.concatenate(RainbowUtil.convertArray(sArrCopyOf), bArr8);
        }
        throw new IllegalStateException("unable to generate signature - LES not solvable");
    }

    private short[] makeMessageRepresentative(byte[] bArr) {
        int i = this.signableDocumentLength;
        short[] sArr = new short[i];
        int i2 = 0;
        int i3 = 0;
        while (i2 < bArr.length) {
            sArr[i2] = (short) (bArr[i3] & 255);
            i3++;
            i2++;
            if (i2 >= i) {
                break;
            }
        }
        return sArr;
    }

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public byte[] generateSignature(byte[] bArr) {
        return genSignature(bArr);
    }

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public void init(boolean z, CipherParameters cipherParameters) {
        RainbowKeyParameters rainbowKeyParameters;
        if (z) {
            if (cipherParameters instanceof ParametersWithRandom) {
                ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
                this.random = parametersWithRandom.getRandom();
                rainbowKeyParameters = (RainbowKeyParameters) parametersWithRandom.getParameters();
            } else {
                rainbowKeyParameters = (RainbowKeyParameters) cipherParameters;
                SecureRandom secureRandom = CryptoServicesRegistrar.getSecureRandom();
                byte[] bArr = new byte[rainbowKeyParameters.getParameters().getLen_skseed()];
                secureRandom.nextBytes(bArr);
                this.random = new RainbowDRBG(bArr, rainbowKeyParameters.getParameters().getHash_algo());
            }
            this.version = rainbowKeyParameters.getParameters().getVersion();
            this.key = rainbowKeyParameters;
        } else {
            RainbowKeyParameters rainbowKeyParameters2 = (RainbowKeyParameters) cipherParameters;
            this.key = rainbowKeyParameters2;
            this.version = rainbowKeyParameters2.getParameters().getVersion();
        }
        this.signableDocumentLength = this.key.getDocLength();
        this.hashAlgo = this.key.getParameters().getHash_algo();
    }

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public boolean verifySignature(byte[] bArr, byte[] bArr2) {
        short[] sArrPublicMap;
        byte[] bArr3 = new byte[this.hashAlgo.getDigestSize()];
        this.hashAlgo.update(bArr, 0, bArr.length);
        this.hashAlgo.doFinal(bArr3, 0);
        int m = this.key.getParameters().getM();
        int n2 = this.key.getParameters().getN();
        RainbowPublicMap rainbowPublicMap = new RainbowPublicMap(this.key.getParameters());
        short[] sArrMakeMessageRepresentative = makeMessageRepresentative(RainbowUtil.hash(this.hashAlgo, bArr3, Arrays.copyOfRange(bArr2, n2, bArr2.length), new byte[m]));
        short[] sArrConvertArray = RainbowUtil.convertArray(Arrays.copyOfRange(bArr2, 0, n2));
        int i = AnonymousClass1.$SwitchMap$org$bouncycastle$pqc$crypto$rainbow$Version[this.version.ordinal()];
        if (i == 1) {
            sArrPublicMap = rainbowPublicMap.publicMap((RainbowPublicKeyParameters) this.key, sArrConvertArray);
        } else {
            if (i != 2 && i != 3) {
                throw new IllegalArgumentException("No valid version. Please choose one of the following: classic, circumzenithal, compressed");
            }
            sArrPublicMap = rainbowPublicMap.publicMap_cyclic((RainbowPublicKeyParameters) this.key, sArrConvertArray);
        }
        return RainbowUtil.equals(sArrMakeMessageRepresentative, sArrPublicMap);
    }
}
