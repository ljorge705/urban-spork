package org.bouncycastle.pqc.crypto.rainbow;

import java.lang.reflect.Array;
import org.bouncycastle.util.Arrays;

/* loaded from: classes4.dex */
public class RainbowPrivateKeyParameters extends RainbowKeyParameters {
    final short[][][] l1_F1;
    final short[][][] l1_F2;
    final short[][][] l2_F1;
    final short[][][] l2_F2;
    final short[][][] l2_F3;
    final short[][][] l2_F5;
    final short[][][] l2_F6;
    private byte[] pk_encoded;
    private final byte[] pk_seed;
    final short[][] s1;
    final byte[] sk_seed;
    final short[][] t1;
    final short[][] t3;
    final short[][] t4;

    public RainbowPrivateKeyParameters(RainbowParameters rainbowParameters, byte[] bArr) {
        super(true, rainbowParameters);
        if (rainbowParameters.getVersion() == Version.COMPRESSED) {
            byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, 0, rainbowParameters.getLen_pkseed());
            this.pk_seed = bArrCopyOfRange;
            byte[] bArrCopyOfRange2 = Arrays.copyOfRange(bArr, rainbowParameters.getLen_pkseed(), rainbowParameters.getLen_pkseed() + rainbowParameters.getLen_skseed());
            this.sk_seed = bArrCopyOfRange2;
            RainbowPrivateKeyParameters rainbowPrivateKeyParametersGeneratePrivateKey = new RainbowKeyComputation(rainbowParameters, bArrCopyOfRange, bArrCopyOfRange2).generatePrivateKey();
            this.pk_encoded = rainbowPrivateKeyParametersGeneratePrivateKey.pk_encoded;
            this.s1 = rainbowPrivateKeyParametersGeneratePrivateKey.s1;
            this.t1 = rainbowPrivateKeyParametersGeneratePrivateKey.t1;
            this.t3 = rainbowPrivateKeyParametersGeneratePrivateKey.t3;
            this.t4 = rainbowPrivateKeyParametersGeneratePrivateKey.t4;
            this.l1_F1 = rainbowPrivateKeyParametersGeneratePrivateKey.l1_F1;
            this.l1_F2 = rainbowPrivateKeyParametersGeneratePrivateKey.l1_F2;
            this.l2_F1 = rainbowPrivateKeyParametersGeneratePrivateKey.l2_F1;
            this.l2_F2 = rainbowPrivateKeyParametersGeneratePrivateKey.l2_F2;
            this.l2_F3 = rainbowPrivateKeyParametersGeneratePrivateKey.l2_F3;
            this.l2_F5 = rainbowPrivateKeyParametersGeneratePrivateKey.l2_F5;
            this.l2_F6 = rainbowPrivateKeyParametersGeneratePrivateKey.l2_F6;
            return;
        }
        int v1 = rainbowParameters.getV1();
        int o1 = rainbowParameters.getO1();
        int o2 = rainbowParameters.getO2();
        short[][] sArr = (short[][]) Array.newInstance((Class<?>) Short.TYPE, o1, o2);
        this.s1 = sArr;
        short[][] sArr2 = (short[][]) Array.newInstance((Class<?>) Short.TYPE, v1, o1);
        this.t1 = sArr2;
        short[][] sArr3 = (short[][]) Array.newInstance((Class<?>) Short.TYPE, v1, o2);
        this.t4 = sArr3;
        short[][] sArr4 = (short[][]) Array.newInstance((Class<?>) Short.TYPE, o1, o2);
        this.t3 = sArr4;
        short[][][] sArr5 = (short[][][]) Array.newInstance((Class<?>) Short.TYPE, o1, v1, v1);
        this.l1_F1 = sArr5;
        short[][][] sArr6 = (short[][][]) Array.newInstance((Class<?>) Short.TYPE, o1, v1, o1);
        this.l1_F2 = sArr6;
        short[][][] sArr7 = (short[][][]) Array.newInstance((Class<?>) Short.TYPE, o2, v1, v1);
        this.l2_F1 = sArr7;
        short[][][] sArr8 = (short[][][]) Array.newInstance((Class<?>) Short.TYPE, o2, v1, o1);
        this.l2_F2 = sArr8;
        short[][][] sArr9 = (short[][][]) Array.newInstance((Class<?>) Short.TYPE, o2, v1, o2);
        this.l2_F3 = sArr9;
        short[][][] sArr10 = (short[][][]) Array.newInstance((Class<?>) Short.TYPE, o2, o1, o1);
        this.l2_F5 = sArr10;
        short[][][] sArr11 = (short[][][]) Array.newInstance((Class<?>) Short.TYPE, o2, o1, o2);
        this.l2_F6 = sArr11;
        this.pk_seed = null;
        byte[] bArrCopyOfRange3 = Arrays.copyOfRange(bArr, 0, rainbowParameters.getLen_skseed());
        this.sk_seed = bArrCopyOfRange3;
        int length = bArrCopyOfRange3.length;
        int iLoadEncoded = length + RainbowUtil.loadEncoded(sArr, bArr, length);
        int iLoadEncoded2 = iLoadEncoded + RainbowUtil.loadEncoded(sArr2, bArr, iLoadEncoded);
        int iLoadEncoded3 = iLoadEncoded2 + RainbowUtil.loadEncoded(sArr3, bArr, iLoadEncoded2);
        int iLoadEncoded4 = iLoadEncoded3 + RainbowUtil.loadEncoded(sArr4, bArr, iLoadEncoded3);
        int iLoadEncoded5 = iLoadEncoded4 + RainbowUtil.loadEncoded(sArr5, bArr, iLoadEncoded4, true);
        int iLoadEncoded6 = iLoadEncoded5 + RainbowUtil.loadEncoded(sArr6, bArr, iLoadEncoded5, false);
        int iLoadEncoded7 = iLoadEncoded6 + RainbowUtil.loadEncoded(sArr7, bArr, iLoadEncoded6, true);
        int iLoadEncoded8 = iLoadEncoded7 + RainbowUtil.loadEncoded(sArr8, bArr, iLoadEncoded7, false);
        int iLoadEncoded9 = iLoadEncoded8 + RainbowUtil.loadEncoded(sArr9, bArr, iLoadEncoded8, false);
        int iLoadEncoded10 = iLoadEncoded9 + RainbowUtil.loadEncoded(sArr10, bArr, iLoadEncoded9, true);
        this.pk_encoded = Arrays.copyOfRange(bArr, iLoadEncoded10 + RainbowUtil.loadEncoded(sArr11, bArr, iLoadEncoded10, false), bArr.length);
    }

    RainbowPrivateKeyParameters(RainbowParameters rainbowParameters, byte[] bArr, byte[] bArr2, byte[] bArr3) {
        super(true, rainbowParameters);
        RainbowPrivateKeyParameters rainbowPrivateKeyParametersGeneratePrivateKey = new RainbowKeyComputation(rainbowParameters, bArr, bArr2).generatePrivateKey();
        this.pk_seed = bArr;
        this.pk_encoded = bArr3;
        this.sk_seed = bArr2;
        this.s1 = rainbowPrivateKeyParametersGeneratePrivateKey.s1;
        this.t1 = rainbowPrivateKeyParametersGeneratePrivateKey.t1;
        this.t3 = rainbowPrivateKeyParametersGeneratePrivateKey.t3;
        this.t4 = rainbowPrivateKeyParametersGeneratePrivateKey.t4;
        this.l1_F1 = rainbowPrivateKeyParametersGeneratePrivateKey.l1_F1;
        this.l1_F2 = rainbowPrivateKeyParametersGeneratePrivateKey.l1_F2;
        this.l2_F1 = rainbowPrivateKeyParametersGeneratePrivateKey.l2_F1;
        this.l2_F2 = rainbowPrivateKeyParametersGeneratePrivateKey.l2_F2;
        this.l2_F3 = rainbowPrivateKeyParametersGeneratePrivateKey.l2_F3;
        this.l2_F5 = rainbowPrivateKeyParametersGeneratePrivateKey.l2_F5;
        this.l2_F6 = rainbowPrivateKeyParametersGeneratePrivateKey.l2_F6;
    }

    RainbowPrivateKeyParameters(RainbowParameters rainbowParameters, byte[] bArr, short[][] sArr, short[][] sArr2, short[][] sArr3, short[][] sArr4, short[][][] sArr5, short[][][] sArr6, short[][][] sArr7, short[][][] sArr8, short[][][] sArr9, short[][][] sArr10, short[][][] sArr11, byte[] bArr2) {
        super(true, rainbowParameters);
        this.pk_seed = null;
        this.pk_encoded = bArr2;
        this.sk_seed = (byte[]) bArr.clone();
        this.s1 = RainbowUtil.cloneArray(sArr);
        this.t1 = RainbowUtil.cloneArray(sArr2);
        this.t3 = RainbowUtil.cloneArray(sArr3);
        this.t4 = RainbowUtil.cloneArray(sArr4);
        this.l1_F1 = RainbowUtil.cloneArray(sArr5);
        this.l1_F2 = RainbowUtil.cloneArray(sArr6);
        this.l2_F1 = RainbowUtil.cloneArray(sArr7);
        this.l2_F2 = RainbowUtil.cloneArray(sArr8);
        this.l2_F3 = RainbowUtil.cloneArray(sArr9);
        this.l2_F5 = RainbowUtil.cloneArray(sArr10);
        this.l2_F6 = RainbowUtil.cloneArray(sArr11);
    }

    public byte[] getEncoded() {
        return getParameters().getVersion() == Version.COMPRESSED ? Arrays.concatenate(this.pk_seed, this.sk_seed) : Arrays.concatenate(getPrivateKey(), this.pk_encoded);
    }

    short[][][] getL1_F1() {
        return RainbowUtil.cloneArray(this.l1_F1);
    }

    short[][][] getL1_F2() {
        return RainbowUtil.cloneArray(this.l1_F2);
    }

    short[][][] getL2_F1() {
        return RainbowUtil.cloneArray(this.l2_F1);
    }

    short[][][] getL2_F2() {
        return RainbowUtil.cloneArray(this.l2_F2);
    }

    short[][][] getL2_F3() {
        return RainbowUtil.cloneArray(this.l2_F3);
    }

    short[][][] getL2_F5() {
        return RainbowUtil.cloneArray(this.l2_F5);
    }

    short[][][] getL2_F6() {
        return RainbowUtil.cloneArray(this.l2_F6);
    }

    public byte[] getPrivateKey() {
        return getParameters().getVersion() == Version.COMPRESSED ? Arrays.concatenate(this.pk_seed, this.sk_seed) : Arrays.concatenate(Arrays.concatenate(Arrays.concatenate(Arrays.concatenate(Arrays.concatenate(Arrays.concatenate(Arrays.concatenate(Arrays.concatenate(Arrays.concatenate(Arrays.concatenate(Arrays.concatenate(this.sk_seed, RainbowUtil.getEncoded(this.s1)), RainbowUtil.getEncoded(this.t1)), RainbowUtil.getEncoded(this.t4)), RainbowUtil.getEncoded(this.t3)), RainbowUtil.getEncoded(this.l1_F1, true)), RainbowUtil.getEncoded(this.l1_F2, false)), RainbowUtil.getEncoded(this.l2_F1, true)), RainbowUtil.getEncoded(this.l2_F2, false)), RainbowUtil.getEncoded(this.l2_F3, false)), RainbowUtil.getEncoded(this.l2_F5, true)), RainbowUtil.getEncoded(this.l2_F6, false));
    }

    public byte[] getPublicKey() {
        return this.pk_encoded;
    }

    short[][] getS1() {
        return RainbowUtil.cloneArray(this.s1);
    }

    byte[] getSk_seed() {
        return Arrays.clone(this.sk_seed);
    }

    short[][] getT1() {
        return RainbowUtil.cloneArray(this.t1);
    }

    short[][] getT3() {
        return RainbowUtil.cloneArray(this.t3);
    }

    short[][] getT4() {
        return RainbowUtil.cloneArray(this.t4);
    }
}
