package org.bouncycastle.pqc.crypto.rainbow;

import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.KeyGenerationParameters;

/* loaded from: classes4.dex */
public class RainbowKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    private RainbowKeyComputation rkc;
    private Version version;

    /* renamed from: org.bouncycastle.pqc.crypto.rainbow.RainbowKeyPairGenerator$1, reason: invalid class name */
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

    private void initialize(KeyGenerationParameters keyGenerationParameters) {
        RainbowParameters parameters = ((RainbowKeyGenerationParameters) keyGenerationParameters).getParameters();
        this.rkc = new RainbowKeyComputation(parameters, keyGenerationParameters.getRandom());
        this.version = parameters.getVersion();
    }

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public AsymmetricCipherKeyPair generateKeyPair() {
        int i = AnonymousClass1.$SwitchMap$org$bouncycastle$pqc$crypto$rainbow$Version[this.version.ordinal()];
        if (i == 1) {
            return this.rkc.genKeyPairClassical();
        }
        if (i == 2) {
            return this.rkc.genKeyPairCircumzenithal();
        }
        if (i == 3) {
            return this.rkc.genKeyPairCompressed();
        }
        throw new IllegalArgumentException("No valid version. Please choose one of the following: classic, circumzenithal, compressed");
    }

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public void init(KeyGenerationParameters keyGenerationParameters) {
        initialize(keyGenerationParameters);
    }
}
