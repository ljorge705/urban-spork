package com.onfido.android.sdk.capture.ui.options.stepbuilder.document;

import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.ui.country_selection.CountryAlternatives;
import com.onfido.android.sdk.capture.ui.options.CaptureScreenStep;
import com.onfido.android.sdk.capture.ui.options.FlowStep;
import com.onfido.android.sdk.capture.ui.options.stepbuilder.BaseDocumentCaptureScreenStepBuilder;
import io.sentry.protocol.OperatingSystem;
import kotlin.Metadata;

/* compiled from: PassportCaptureStepBuilder.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/options/stepbuilder/document/PassportCaptureStepBuilder;", "Lcom/onfido/android/sdk/capture/ui/options/stepbuilder/BaseDocumentCaptureScreenStepBuilder;", "()V", OperatingSystem.JsonKeys.BUILD, "Lcom/onfido/android/sdk/capture/ui/options/FlowStep;", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class PassportCaptureStepBuilder extends BaseDocumentCaptureScreenStepBuilder {
    @Override // com.onfido.android.sdk.capture.ui.options.stepbuilder.BaseDocumentCaptureScreenStepBuilder
    public FlowStep build() {
        return new CaptureScreenStep(DocumentType.PASSPORT, CountryAlternatives.NO_COUNTRY);
    }
}
