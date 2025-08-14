package com.onfido.android.sdk.capture.ui.options.stepbuilder.document;

import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.document.DocumentPages;
import com.onfido.android.sdk.capture.ui.options.CaptureScreenStep;
import com.onfido.android.sdk.capture.ui.options.FlowStep;
import com.onfido.android.sdk.capture.ui.options.stepbuilder.DocumentCaptureWithGenericDocumentStepBuilder;
import com.onfido.android.sdk.capture.utils.CountryCode;
import io.sentry.protocol.OperatingSystem;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GenericDocumentCaptureBuilder.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \f2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\fB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0004H\u0016J\u0010\u0010\n\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0006H\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/options/stepbuilder/document/GenericDocumentCaptureBuilder;", "Lcom/onfido/android/sdk/capture/ui/options/stepbuilder/DocumentCaptureWithGenericDocumentStepBuilder;", "()V", RemoteConfigConstants.RequestFieldKey.COUNTRY_CODE, "Lcom/onfido/android/sdk/capture/utils/CountryCode;", "documentPages", "Lcom/onfido/android/sdk/capture/document/DocumentPages;", OperatingSystem.JsonKeys.BUILD, "Lcom/onfido/android/sdk/capture/ui/options/FlowStep;", "withCountry", "withDocumentPages", "pages", "Companion", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class GenericDocumentCaptureBuilder extends DocumentCaptureWithGenericDocumentStepBuilder<GenericDocumentCaptureBuilder> {
    private CountryCode countryCode;
    private DocumentPages documentPages;
    private static final DocumentType CURRENT_DOCUMENT_TYPE = DocumentType.GENERIC;

    @Override // com.onfido.android.sdk.capture.ui.options.stepbuilder.DocumentCaptureWithGenericDocumentStepBuilder
    public GenericDocumentCaptureBuilder withCountry(CountryCode countryCode) {
        Intrinsics.checkNotNullParameter(countryCode, "countryCode");
        this.countryCode = countryCode;
        return this;
    }

    @Override // com.onfido.android.sdk.capture.ui.options.stepbuilder.DocumentCaptureWithGenericDocumentStepBuilder
    public GenericDocumentCaptureBuilder withDocumentPages(DocumentPages pages) {
        Intrinsics.checkNotNullParameter(pages, "pages");
        this.documentPages = pages;
        return this;
    }

    @Override // com.onfido.android.sdk.capture.ui.options.stepbuilder.BaseDocumentCaptureScreenStepBuilder
    public FlowStep build() {
        return new CaptureScreenStep(CURRENT_DOCUMENT_TYPE, this.countryCode, this.documentPages);
    }
}
