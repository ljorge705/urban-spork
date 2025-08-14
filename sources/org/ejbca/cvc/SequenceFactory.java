package org.ejbca.cvc;

/* loaded from: classes4.dex */
public class SequenceFactory {
    public static AbstractSequence createSequence(CVCTagEnum cVCTagEnum) {
        if (!cVCTagEnum.isSequence()) {
            throw new IllegalArgumentException("Tag " + cVCTagEnum + " is not a sequence");
        }
        switch (AnonymousClass1.$SwitchMap$org$ejbca$cvc$CVCTagEnum[cVCTagEnum.ordinal()]) {
            case 1:
                return new CVCertificate();
            case 2:
                return new CVCertificateBody();
            case 3:
                return new GenericPublicKeyField();
            case 4:
                return new CVCAuthorizationTemplate();
            case 5:
                return new CVCAuthenticatedRequest();
            case 6:
                return new CVCertificateExtensions();
            case 7:
                return new CVCDiscretionaryDataTemplate();
            default:
                throw new IllegalArgumentException("Unsupported type " + cVCTagEnum);
        }
    }

    /* renamed from: org.ejbca.cvc.SequenceFactory$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$ejbca$cvc$CVCTagEnum;

        static {
            int[] iArr = new int[CVCTagEnum.values().length];
            $SwitchMap$org$ejbca$cvc$CVCTagEnum = iArr;
            try {
                iArr[CVCTagEnum.CV_CERTIFICATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$ejbca$cvc$CVCTagEnum[CVCTagEnum.CERTIFICATE_BODY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$ejbca$cvc$CVCTagEnum[CVCTagEnum.PUBLIC_KEY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$ejbca$cvc$CVCTagEnum[CVCTagEnum.HOLDER_AUTH_TEMPLATE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$org$ejbca$cvc$CVCTagEnum[CVCTagEnum.REQ_AUTHENTICATION.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$org$ejbca$cvc$CVCTagEnum[CVCTagEnum.CERTIFICATE_EXTENSIONS.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$org$ejbca$cvc$CVCTagEnum[CVCTagEnum.DISCRETIONARY_DATA_TEMPLATE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }
}
