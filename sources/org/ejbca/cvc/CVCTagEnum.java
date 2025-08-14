package org.ejbca.cvc;

import org.jmrtd.cbeff.ISO781611;

/* loaded from: classes4.dex */
public enum CVCTagEnum {
    CV_CERTIFICATE(32545, true),
    CERTIFICATE_BODY(32590, true),
    PROFILE_IDENTIFIER(24361),
    PUBLIC_KEY(32585, true),
    HOLDER_REFERENCE(24352),
    HOLDER_AUTH_TEMPLATE(32588, true),
    EFFECTIVE_DATE(24357),
    EXPIRATION_DATE(24356),
    SIGNATURE(24375),
    OID(6),
    CA_REFERENCE(66),
    REQ_AUTHENTICATION(103, true),
    ROLE_AND_ACCESS_RIGHTS(83),
    MODULUS(129),
    EXPONENT(130),
    COEFFICIENT_A(130),
    COEFFICIENT_B(131),
    BASE_POINT_G(132),
    BASE_POINT_R_ORDER(133),
    PUBLIC_POINT_Y(134),
    COFACTOR_F(135),
    CERTIFICATE_EXTENSIONS(101, true),
    DISCRETIONARY_DATA_TEMPLATE(ISO781611.DISCRETIONARY_DATA_FOR_PAYLOAD_CONSTRUCTED_TAG, true),
    ARBITRARY_DATA(83);

    private boolean isSequence;
    private int value;

    public int getValue() {
        return this.value;
    }

    public boolean isSequence() {
        return this.isSequence;
    }

    CVCTagEnum(int i) {
        this(i, false);
    }

    CVCTagEnum(int i, boolean z) {
        this.value = i;
        this.isSequence = z;
    }
}
