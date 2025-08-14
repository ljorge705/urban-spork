package com.onfido.android.sdk.capture.validation;

import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.analytics.IdentityInteractor;
import com.onfido.android.sdk.capture.utils.CountryCode;
import com.onfido.api.client.data.DocSide;
import com.onfido.javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0016\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J/\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016¢\u0006\u0002\u0010\u000eJ'\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0012¢\u0006\u0002\u0010\u0010J'\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0012¢\u0006\u0002\u0010\u0010J\u0013\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0012¢\u0006\u0002\u0010\u0013R\u000e\u0010\u0002\u001a\u00020\u0003X\u0092\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/onfido/android/sdk/capture/validation/RealTimeDocumentValidationsManager;", "Lcom/onfido/android/sdk/capture/validation/OnDeviceDocumentValidationsManager;", "identityInteractor", "Lcom/onfido/android/sdk/capture/analytics/IdentityInteractor;", "(Lcom/onfido/android/sdk/capture/analytics/IdentityInteractor;)V", "getRequiredValidations", "", "Lcom/onfido/android/sdk/capture/validation/OnDeviceValidationType;", "documentType", "Lcom/onfido/android/sdk/capture/DocumentType;", RemoteConfigConstants.RequestFieldKey.COUNTRY_CODE, "Lcom/onfido/android/sdk/capture/utils/CountryCode;", "documentSide", "Lcom/onfido/api/client/data/DocSide;", "(Lcom/onfido/android/sdk/capture/DocumentType;Lcom/onfido/android/sdk/capture/utils/CountryCode;Lcom/onfido/api/client/data/DocSide;)[Lcom/onfido/android/sdk/capture/validation/OnDeviceValidationType;", "getRequiredValidationsForDrivingLicence", "(Lcom/onfido/android/sdk/capture/utils/CountryCode;Lcom/onfido/api/client/data/DocSide;)[Lcom/onfido/android/sdk/capture/validation/OnDeviceValidationType;", "getRequiredValidationsForID", "getRequiredValidationsForPassport", "()[Lcom/onfido/android/sdk/capture/validation/OnDeviceValidationType;", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public class RealTimeDocumentValidationsManager implements OnDeviceDocumentValidationsManager {
    private static final Companion Companion = new Companion(null);
    private static final OnDeviceValidationType[] DEFAULT_VALIDATION_REQUIREMENTS;
    private static final OnDeviceValidationType[] DUTCH_ID_VALIDATION_REQUIREMENTS;
    private static final OnDeviceValidationType[] PASSPORT_VALIDATION_REQUIREMENTS;
    private static final OnDeviceValidationType[] ROMANIAN_ID_VALIDATION_REQUIREMENTS;
    private static final OnDeviceValidationType[] US_DRIVING_BACK_HIGH_END_VALIDATION_REQUIREMENTS;
    private static final OnDeviceValidationType[] US_DRIVING_BACK_VALIDATION_REQUIREMENTS;
    private static final OnDeviceValidationType[] US_DRIVING_FRONT_VALIDATION_REQUIREMENTS;
    private final IdentityInteractor identityInteractor;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0010\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0019\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0019\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\n\u0010\u0007R\u0019\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\f\u0010\u0007R\u0019\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u000e\u0010\u0007R\u0019\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0010\u0010\u0007R\u0019\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0012\u0010\u0007R\u0019\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0014\u0010\u0007¨\u0006\u0015"}, d2 = {"Lcom/onfido/android/sdk/capture/validation/RealTimeDocumentValidationsManager$Companion;", "", "()V", "DEFAULT_VALIDATION_REQUIREMENTS", "", "Lcom/onfido/android/sdk/capture/validation/OnDeviceValidationType;", "getDEFAULT_VALIDATION_REQUIREMENTS", "()[Lcom/onfido/android/sdk/capture/validation/OnDeviceValidationType;", "[Lcom/onfido/android/sdk/capture/validation/OnDeviceValidationType;", "DUTCH_ID_VALIDATION_REQUIREMENTS", "getDUTCH_ID_VALIDATION_REQUIREMENTS", "PASSPORT_VALIDATION_REQUIREMENTS", "getPASSPORT_VALIDATION_REQUIREMENTS", "ROMANIAN_ID_VALIDATION_REQUIREMENTS", "getROMANIAN_ID_VALIDATION_REQUIREMENTS", "US_DRIVING_BACK_HIGH_END_VALIDATION_REQUIREMENTS", "getUS_DRIVING_BACK_HIGH_END_VALIDATION_REQUIREMENTS", "US_DRIVING_BACK_VALIDATION_REQUIREMENTS", "getUS_DRIVING_BACK_VALIDATION_REQUIREMENTS", "US_DRIVING_FRONT_VALIDATION_REQUIREMENTS", "getUS_DRIVING_FRONT_VALIDATION_REQUIREMENTS", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class Companion {
        private Companion() {
        }

        public final OnDeviceValidationType[] getDEFAULT_VALIDATION_REQUIREMENTS() {
            return RealTimeDocumentValidationsManager.DEFAULT_VALIDATION_REQUIREMENTS;
        }

        public final OnDeviceValidationType[] getDUTCH_ID_VALIDATION_REQUIREMENTS() {
            return RealTimeDocumentValidationsManager.DUTCH_ID_VALIDATION_REQUIREMENTS;
        }

        public final OnDeviceValidationType[] getPASSPORT_VALIDATION_REQUIREMENTS() {
            return RealTimeDocumentValidationsManager.PASSPORT_VALIDATION_REQUIREMENTS;
        }

        public final OnDeviceValidationType[] getROMANIAN_ID_VALIDATION_REQUIREMENTS() {
            return RealTimeDocumentValidationsManager.ROMANIAN_ID_VALIDATION_REQUIREMENTS;
        }

        public final OnDeviceValidationType[] getUS_DRIVING_BACK_HIGH_END_VALIDATION_REQUIREMENTS() {
            return RealTimeDocumentValidationsManager.US_DRIVING_BACK_HIGH_END_VALIDATION_REQUIREMENTS;
        }

        public final OnDeviceValidationType[] getUS_DRIVING_BACK_VALIDATION_REQUIREMENTS() {
            return RealTimeDocumentValidationsManager.US_DRIVING_BACK_VALIDATION_REQUIREMENTS;
        }

        public final OnDeviceValidationType[] getUS_DRIVING_FRONT_VALIDATION_REQUIREMENTS() {
            return RealTimeDocumentValidationsManager.US_DRIVING_FRONT_VALIDATION_REQUIREMENTS;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[DocumentType.values().length];
            try {
                iArr[DocumentType.PASSPORT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[DocumentType.DRIVING_LICENCE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[DocumentType.NATIONAL_IDENTITY_CARD.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    static {
        OnDeviceValidationType onDeviceValidationType = OnDeviceValidationType.GLARE_DETECTION;
        OnDeviceValidationType onDeviceValidationType2 = OnDeviceValidationType.BLUR_DETECTION;
        OnDeviceValidationType onDeviceValidationType3 = OnDeviceValidationType.EDGES_DETECTION;
        US_DRIVING_FRONT_VALIDATION_REQUIREMENTS = new OnDeviceValidationType[]{onDeviceValidationType, onDeviceValidationType2, onDeviceValidationType3};
        US_DRIVING_BACK_HIGH_END_VALIDATION_REQUIREMENTS = new OnDeviceValidationType[]{onDeviceValidationType3, OnDeviceValidationType.PDF_417_BARCODE_DETECTION};
        US_DRIVING_BACK_VALIDATION_REQUIREMENTS = new OnDeviceValidationType[]{onDeviceValidationType3};
        PASSPORT_VALIDATION_REQUIREMENTS = new OnDeviceValidationType[]{onDeviceValidationType, onDeviceValidationType2, onDeviceValidationType3};
        ROMANIAN_ID_VALIDATION_REQUIREMENTS = new OnDeviceValidationType[0];
        DUTCH_ID_VALIDATION_REQUIREMENTS = new OnDeviceValidationType[]{onDeviceValidationType, onDeviceValidationType2, onDeviceValidationType3};
        DEFAULT_VALIDATION_REQUIREMENTS = new OnDeviceValidationType[]{onDeviceValidationType};
    }

    @Inject
    public RealTimeDocumentValidationsManager(IdentityInteractor identityInteractor) {
        Intrinsics.checkNotNullParameter(identityInteractor, "identityInteractor");
        this.identityInteractor = identityInteractor;
    }

    private OnDeviceValidationType[] getRequiredValidationsForDrivingLicence(CountryCode countryCode, DocSide documentSide) {
        CountryCode countryCode2 = CountryCode.US;
        boolean z = countryCode == countryCode2 && documentSide == DocSide.FRONT;
        boolean z2 = countryCode == countryCode2 && documentSide == DocSide.BACK;
        return z ? US_DRIVING_FRONT_VALIDATION_REQUIREMENTS : z2 && this.identityInteractor.isDeviceHighEnd() ? US_DRIVING_BACK_HIGH_END_VALIDATION_REQUIREMENTS : z2 ? US_DRIVING_BACK_VALIDATION_REQUIREMENTS : DEFAULT_VALIDATION_REQUIREMENTS;
    }

    private OnDeviceValidationType[] getRequiredValidationsForID(CountryCode countryCode, DocSide documentSide) {
        return countryCode == CountryCode.RO && documentSide == DocSide.BACK ? ROMANIAN_ID_VALIDATION_REQUIREMENTS : countryCode == CountryCode.NL && documentSide == DocSide.FRONT ? DUTCH_ID_VALIDATION_REQUIREMENTS : DEFAULT_VALIDATION_REQUIREMENTS;
    }

    private OnDeviceValidationType[] getRequiredValidationsForPassport() {
        return PASSPORT_VALIDATION_REQUIREMENTS;
    }

    @Override // com.onfido.android.sdk.capture.validation.OnDeviceDocumentValidationsManager
    public OnDeviceValidationType[] getRequiredValidations(DocumentType documentType, CountryCode countryCode, DocSide documentSide) {
        Intrinsics.checkNotNullParameter(documentType, "documentType");
        int i = WhenMappings.$EnumSwitchMapping$0[documentType.ordinal()];
        return i != 1 ? i != 2 ? i != 3 ? DEFAULT_VALIDATION_REQUIREMENTS : getRequiredValidationsForID(countryCode, documentSide) : getRequiredValidationsForDrivingLicence(countryCode, documentSide) : getRequiredValidationsForPassport();
    }
}
