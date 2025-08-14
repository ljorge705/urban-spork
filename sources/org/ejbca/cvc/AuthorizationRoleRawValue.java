package org.ejbca.cvc;

/* loaded from: classes4.dex */
public class AuthorizationRoleRawValue implements AuthorizationRole {
    private final byte value;

    @Override // org.ejbca.cvc.AuthorizationRole
    public byte getValue() {
        return this.value;
    }

    @Override // org.ejbca.cvc.AuthorizationRole
    public boolean isAccreditationBodyDV() {
        return false;
    }

    @Override // org.ejbca.cvc.AuthorizationRole
    public boolean isAuthenticationTerminal() {
        return false;
    }

    @Override // org.ejbca.cvc.AuthorizationRole
    public boolean isCVCA() {
        return false;
    }

    @Override // org.ejbca.cvc.AuthorizationRole
    public boolean isCertificationServiceProviderDV() {
        return false;
    }

    @Override // org.ejbca.cvc.AuthorizationRole
    public boolean isDV() {
        return false;
    }

    @Override // org.ejbca.cvc.AuthorizationRole
    public boolean isDomesticDV() {
        return false;
    }

    @Override // org.ejbca.cvc.AuthorizationRole
    public boolean isForeignDV() {
        return false;
    }

    @Override // org.ejbca.cvc.AuthorizationRole
    public boolean isIS() {
        return false;
    }

    @Override // org.ejbca.cvc.AuthorizationRole
    public boolean isSignatureTerminal() {
        return false;
    }

    @Override // org.ejbca.cvc.AuthorizationRole
    public String name() {
        return "RAW_AUTHORIZATION_ROLE";
    }

    AuthorizationRoleRawValue(byte b) {
        this.value = b;
    }

    public String toString() {
        return "AuthorizationRoleRawValue(" + Integer.toString(this.value & 255, 16).toUpperCase() + ")";
    }
}
