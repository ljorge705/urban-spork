package org.spongycastle.pqc.jcajce.provider.newhope;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.ShortBufferException;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.jcajce.provider.asymmetric.util.BaseAgreementSpi;
import org.spongycastle.pqc.crypto.ExchangePair;
import org.spongycastle.pqc.crypto.newhope.NHAgreement;
import org.spongycastle.pqc.crypto.newhope.NHExchangePairGenerator;
import org.spongycastle.pqc.crypto.newhope.NHPublicKeyParameters;
import org.spongycastle.util.Arrays;

/* loaded from: classes7.dex */
public class KeyAgreementSpi extends BaseAgreementSpi {
    private NHAgreement agreement;
    private NHExchangePairGenerator exchangePairGenerator;
    private BCNHPublicKey otherPartyKey;
    private byte[] shared;

    public KeyAgreementSpi() {
        super("NH", null);
    }

    @Override // javax.crypto.KeyAgreementSpi
    protected void engineInit(Key key, SecureRandom secureRandom) throws InvalidKeyException {
        if (key != null) {
            NHAgreement nHAgreement = new NHAgreement();
            this.agreement = nHAgreement;
            nHAgreement.init(((BCNHPrivateKey) key).getKeyParams());
            return;
        }
        this.exchangePairGenerator = new NHExchangePairGenerator(secureRandom);
    }

    @Override // javax.crypto.KeyAgreementSpi
    protected void engineInit(Key key, AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidKeyException, InvalidAlgorithmParameterException {
        throw new InvalidAlgorithmParameterException("NewHope does not require parameters");
    }

    @Override // javax.crypto.KeyAgreementSpi
    protected Key engineDoPhase(Key key, boolean z) throws IllegalStateException, InvalidKeyException {
        if (!z) {
            throw new IllegalStateException("NewHope can only be between two parties.");
        }
        BCNHPublicKey bCNHPublicKey = (BCNHPublicKey) key;
        this.otherPartyKey = bCNHPublicKey;
        NHExchangePairGenerator nHExchangePairGenerator = this.exchangePairGenerator;
        if (nHExchangePairGenerator != null) {
            ExchangePair exchangePairGenerateExchange = nHExchangePairGenerator.generateExchange((AsymmetricKeyParameter) bCNHPublicKey.getKeyParams());
            this.shared = exchangePairGenerateExchange.getSharedValue();
            return new BCNHPublicKey((NHPublicKeyParameters) exchangePairGenerateExchange.getPublicKey());
        }
        this.shared = this.agreement.calculateAgreement(bCNHPublicKey.getKeyParams());
        return null;
    }

    @Override // org.spongycastle.jcajce.provider.asymmetric.util.BaseAgreementSpi, javax.crypto.KeyAgreementSpi
    protected byte[] engineGenerateSecret() throws IllegalStateException {
        byte[] bArrClone = Arrays.clone(this.shared);
        Arrays.fill(this.shared, (byte) 0);
        return bArrClone;
    }

    @Override // org.spongycastle.jcajce.provider.asymmetric.util.BaseAgreementSpi, javax.crypto.KeyAgreementSpi
    protected int engineGenerateSecret(byte[] bArr, int i) throws IllegalStateException, ShortBufferException {
        byte[] bArr2 = this.shared;
        System.arraycopy(bArr2, 0, bArr, i, bArr2.length);
        Arrays.fill(this.shared, (byte) 0);
        return this.shared.length;
    }

    @Override // org.spongycastle.jcajce.provider.asymmetric.util.BaseAgreementSpi
    protected byte[] calcSecret() {
        return engineGenerateSecret();
    }
}
