package com.onfido.android.sdk.capture.ui.options.stepbuilder;

import androidx.exifinterface.media.ExifInterface;
import com.onfido.android.sdk.capture.DocumentFormat;
import com.onfido.android.sdk.capture.ui.options.stepbuilder.BaseDocumentCaptureScreenStepBuilder;
import kotlin.Metadata;

/* compiled from: DocumentCaptureStepBuilderContract.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003J\u0015\u0010\u0004\u001a\u00028\u00002\u0006\u0010\u0005\u001a\u00020\u0006H&¢\u0006\u0002\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/options/stepbuilder/DocumentFormatSelection;", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/onfido/android/sdk/capture/ui/options/stepbuilder/BaseDocumentCaptureScreenStepBuilder;", "", "withDocumentFormat", "documentFormat", "Lcom/onfido/android/sdk/capture/DocumentFormat;", "(Lcom/onfido/android/sdk/capture/DocumentFormat;)Lcom/onfido/android/sdk/capture/ui/options/stepbuilder/BaseDocumentCaptureScreenStepBuilder;", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface DocumentFormatSelection<T extends BaseDocumentCaptureScreenStepBuilder> {
    T withDocumentFormat(DocumentFormat documentFormat);
}
