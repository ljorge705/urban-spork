package com.onfido.android.sdk.capture.ui.options;

import com.onfido.android.sdk.capture.DocumentFormat;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.config.MediaCallbackResultReceiver;
import com.onfido.android.sdk.capture.document.DocumentPages;
import com.onfido.android.sdk.capture.ui.country_selection.CountryAlternatives;
import com.onfido.android.sdk.capture.utils.CountryCode;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CaptureScreenStep.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0017\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u0017\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tB#\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0002\u0010\fB\u001f\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0002\u0010\u000fB\u001f\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0002\u0010\u0010¨\u0006\u0011"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/options/CaptureScreenStep;", "Lcom/onfido/android/sdk/capture/ui/options/FlowStep;", "documentType", "Lcom/onfido/android/sdk/capture/DocumentType;", MediaCallbackResultReceiver.KEY_COUNTRY, "Lcom/onfido/android/sdk/capture/utils/CountryCode;", "(Lcom/onfido/android/sdk/capture/DocumentType;Lcom/onfido/android/sdk/capture/utils/CountryCode;)V", "countryAlternatives", "Lcom/onfido/android/sdk/capture/ui/country_selection/CountryAlternatives;", "(Lcom/onfido/android/sdk/capture/DocumentType;Lcom/onfido/android/sdk/capture/ui/country_selection/CountryAlternatives;)V", "genericDocPages", "Lcom/onfido/android/sdk/capture/document/DocumentPages;", "(Lcom/onfido/android/sdk/capture/DocumentType;Lcom/onfido/android/sdk/capture/utils/CountryCode;Lcom/onfido/android/sdk/capture/document/DocumentPages;)V", "documentFormat", "Lcom/onfido/android/sdk/capture/DocumentFormat;", "(Lcom/onfido/android/sdk/capture/DocumentType;Lcom/onfido/android/sdk/capture/ui/country_selection/CountryAlternatives;Lcom/onfido/android/sdk/capture/DocumentFormat;)V", "(Lcom/onfido/android/sdk/capture/DocumentType;Lcom/onfido/android/sdk/capture/utils/CountryCode;Lcom/onfido/android/sdk/capture/DocumentFormat;)V", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class CaptureScreenStep extends FlowStep {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CaptureScreenStep(DocumentType documentType, CountryCode country) {
        super(FlowAction.CAPTURE_DOCUMENT);
        Intrinsics.checkNotNullParameter(documentType, "documentType");
        Intrinsics.checkNotNullParameter(country, "country");
        setOptions(new CaptureScreenOptions(documentType, country, null, null, null, null, 60, null));
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CaptureScreenStep(DocumentType documentType, CountryAlternatives countryAlternatives) {
        super(FlowAction.CAPTURE_DOCUMENT);
        Intrinsics.checkNotNullParameter(documentType, "documentType");
        Intrinsics.checkNotNullParameter(countryAlternatives, "countryAlternatives");
        setOptions(new CaptureScreenOptions(documentType, null, countryAlternatives, null, null, null, 58, null));
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CaptureScreenStep(DocumentType documentType, CountryCode countryCode, DocumentPages documentPages) {
        super(FlowAction.CAPTURE_DOCUMENT);
        Intrinsics.checkNotNullParameter(documentType, "documentType");
        setOptions(new CaptureScreenOptions(documentType, countryCode, countryCode == null ? CountryAlternatives.NO_COUNTRY : null, null, null, documentPages, 24, null));
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CaptureScreenStep(DocumentType documentType, CountryAlternatives countryAlternatives, DocumentFormat documentFormat) {
        super(FlowAction.CAPTURE_DOCUMENT);
        Intrinsics.checkNotNullParameter(documentType, "documentType");
        Intrinsics.checkNotNullParameter(countryAlternatives, "countryAlternatives");
        Intrinsics.checkNotNullParameter(documentFormat, "documentFormat");
        setOptions(new CaptureScreenOptions(documentType, null, countryAlternatives, documentFormat, null, null, 50, null));
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CaptureScreenStep(DocumentType documentType, CountryCode country, DocumentFormat documentFormat) {
        super(FlowAction.CAPTURE_DOCUMENT);
        Intrinsics.checkNotNullParameter(documentType, "documentType");
        Intrinsics.checkNotNullParameter(country, "country");
        Intrinsics.checkNotNullParameter(documentFormat, "documentFormat");
        setOptions(new CaptureScreenOptions(documentType, country, null, documentFormat, null, null, 52, null));
    }
}
