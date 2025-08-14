package com.onfido.android.sdk.capture.ui;

import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.NfcFlowType;
import com.onfido.android.sdk.capture.upload.ErrorType;
import com.onfido.api.client.data.DocumentMediaUploadResponse;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH&¨\u0006\u000b"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/NfcDataServiceListener;", "", "onDataUploaded", "", "documentMediaUpload", "Lcom/onfido/api/client/data/DocumentMediaUploadResponse;", "nfcFlowType", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/NfcFlowType;", "onUploadError", "errorType", "Lcom/onfido/android/sdk/capture/upload/ErrorType;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface NfcDataServiceListener {
    void onDataUploaded(DocumentMediaUploadResponse documentMediaUpload, NfcFlowType nfcFlowType);

    void onUploadError(ErrorType errorType);
}
