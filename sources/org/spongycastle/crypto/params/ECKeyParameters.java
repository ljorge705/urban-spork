package org.spongycastle.crypto.params;

/* loaded from: classes7.dex */
public class ECKeyParameters extends AsymmetricKeyParameter {
    ECDomainParameters params;

    public ECDomainParameters getParameters() {
        return this.params;
    }

    protected ECKeyParameters(boolean z, ECDomainParameters eCDomainParameters) {
        super(z);
        this.params = eCDomainParameters;
    }
}
