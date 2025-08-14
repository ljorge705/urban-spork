package org.spongycastle.crypto.params;

/* loaded from: classes7.dex */
public class DHKeyParameters extends AsymmetricKeyParameter {
    private DHParameters params;

    public DHParameters getParameters() {
        return this.params;
    }

    protected DHKeyParameters(boolean z, DHParameters dHParameters) {
        super(z);
        this.params = dHParameters;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DHKeyParameters)) {
            return false;
        }
        DHKeyParameters dHKeyParameters = (DHKeyParameters) obj;
        DHParameters dHParameters = this.params;
        if (dHParameters == null) {
            return dHKeyParameters.getParameters() == null;
        }
        return dHParameters.equals(dHKeyParameters.getParameters());
    }

    public int hashCode() {
        int i = !isPrivate() ? 1 : 0;
        DHParameters dHParameters = this.params;
        return dHParameters != null ? i ^ dHParameters.hashCode() : i;
    }
}
