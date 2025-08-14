package com.onfido.android.sdk.capture.ui.options.stepbuilder;

import androidx.exifinterface.media.ExifInterface;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.onfido.android.sdk.capture.document.DocumentPages;
import com.onfido.android.sdk.capture.ui.options.stepbuilder.BaseDocumentCaptureScreenStepBuilder;
import com.onfido.android.sdk.capture.utils.CountryCode;
import kotlin.Metadata;

/* compiled from: DocumentCaptureStepBuilderContract.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0015\u0010\u0004\u001a\u00028\u00002\u0006\u0010\u0005\u001a\u00020\u0006H&¢\u0006\u0002\u0010\u0007J\u0015\u0010\b\u001a\u00028\u00002\u0006\u0010\t\u001a\u00020\nH&¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/options/stepbuilder/DocumentCaptureWithGenericDocumentStepBuilder;", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/onfido/android/sdk/capture/ui/options/stepbuilder/BaseDocumentCaptureScreenStepBuilder;", "()V", "withCountry", RemoteConfigConstants.RequestFieldKey.COUNTRY_CODE, "Lcom/onfido/android/sdk/capture/utils/CountryCode;", "(Lcom/onfido/android/sdk/capture/utils/CountryCode;)Lcom/onfido/android/sdk/capture/ui/options/stepbuilder/BaseDocumentCaptureScreenStepBuilder;", "withDocumentPages", "pages", "Lcom/onfido/android/sdk/capture/document/DocumentPages;", "(Lcom/onfido/android/sdk/capture/document/DocumentPages;)Lcom/onfido/android/sdk/capture/ui/options/stepbuilder/BaseDocumentCaptureScreenStepBuilder;", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public abstract class DocumentCaptureWithGenericDocumentStepBuilder<T extends BaseDocumentCaptureScreenStepBuilder> extends BaseDocumentCaptureScreenStepBuilder {
    public abstract T withCountry(CountryCode countryCode);

    public abstract T withDocumentPages(DocumentPages pages);
}
