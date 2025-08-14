package com.onfido.android.sdk.capture.ui.options.stepbuilder;

import com.onfido.android.sdk.capture.ui.options.stepbuilder.document.DrivingLicenceCaptureStepBuilder;
import com.onfido.android.sdk.capture.ui.options.stepbuilder.document.GenericDocumentCaptureBuilder;
import com.onfido.android.sdk.capture.ui.options.stepbuilder.document.NationalIdentityCaptureStepBuilder;
import com.onfido.android.sdk.capture.ui.options.stepbuilder.document.PassportCaptureStepBuilder;
import com.onfido.android.sdk.capture.ui.options.stepbuilder.document.ResidencePermitStepCaptureBuilder;
import com.onfido.android.sdk.capture.ui.options.stepbuilder.document.VisaCaptureBuilder;
import com.onfido.android.sdk.capture.ui.options.stepbuilder.document.WorkPermitCaptureBuilder;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;

/* compiled from: DocumentCaptureStepBuilder.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007J\b\u0010\u0005\u001a\u00020\u0006H\u0007J\b\u0010\u0007\u001a\u00020\bH\u0007J\b\u0010\t\u001a\u00020\nH\u0007J\b\u0010\u000b\u001a\u00020\fH\u0007J\b\u0010\r\u001a\u00020\u000eH\u0007J\b\u0010\u000f\u001a\u00020\u0010H\u0007¨\u0006\u0011"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/options/stepbuilder/DocumentCaptureStepBuilder;", "", "()V", "forDrivingLicence", "Lcom/onfido/android/sdk/capture/ui/options/stepbuilder/document/DrivingLicenceCaptureStepBuilder;", "forGenericDocument", "Lcom/onfido/android/sdk/capture/ui/options/stepbuilder/document/GenericDocumentCaptureBuilder;", "forNationalIdentity", "Lcom/onfido/android/sdk/capture/ui/options/stepbuilder/document/NationalIdentityCaptureStepBuilder;", "forPassport", "Lcom/onfido/android/sdk/capture/ui/options/stepbuilder/document/PassportCaptureStepBuilder;", "forResidencePermit", "Lcom/onfido/android/sdk/capture/ui/options/stepbuilder/document/ResidencePermitStepCaptureBuilder;", "forVisa", "Lcom/onfido/android/sdk/capture/ui/options/stepbuilder/document/VisaCaptureBuilder;", "forWorkPermit", "Lcom/onfido/android/sdk/capture/ui/options/stepbuilder/document/WorkPermitCaptureBuilder;", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DocumentCaptureStepBuilder {
    public static final DocumentCaptureStepBuilder INSTANCE = new DocumentCaptureStepBuilder();

    private DocumentCaptureStepBuilder() {
    }

    @JvmStatic
    public static final PassportCaptureStepBuilder forPassport() {
        return new PassportCaptureStepBuilder();
    }

    @JvmStatic
    public static final NationalIdentityCaptureStepBuilder forNationalIdentity() {
        return new NationalIdentityCaptureStepBuilder();
    }

    @JvmStatic
    public static final DrivingLicenceCaptureStepBuilder forDrivingLicence() {
        return new DrivingLicenceCaptureStepBuilder();
    }

    @JvmStatic
    public static final ResidencePermitStepCaptureBuilder forResidencePermit() {
        return new ResidencePermitStepCaptureBuilder();
    }

    @JvmStatic
    public static final VisaCaptureBuilder forVisa() {
        return new VisaCaptureBuilder();
    }

    @JvmStatic
    public static final WorkPermitCaptureBuilder forWorkPermit() {
        return new WorkPermitCaptureBuilder();
    }

    @JvmStatic
    public static final GenericDocumentCaptureBuilder forGenericDocument() {
        return new GenericDocumentCaptureBuilder();
    }
}
