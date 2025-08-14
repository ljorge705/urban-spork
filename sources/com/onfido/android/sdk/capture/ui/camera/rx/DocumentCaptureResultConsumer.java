package com.onfido.android.sdk.capture.ui.camera.rx;

import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.validation.DocumentProcessingResults;
import com.onfido.android.sdk.capture.ui.camera.CapturePresenter;
import com.onfido.android.sdk.capture.ui.camera.rx.DocumentCaptureDelayTransformer;
import com.onfido.android.sdk.capture.validation.OnfidoCaptureValidationBubble;
import io.reactivex.rxjava3.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0002H\u0016J\u0015\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000fH\u0000¢\u0006\u0002\b\u0010R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/rx/DocumentCaptureResultConsumer;", "Lio/reactivex/rxjava3/functions/Consumer;", "Lcom/onfido/android/sdk/capture/ui/camera/rx/DocumentCaptureDelayTransformer$DocumentCaptureResult;", "view", "Lcom/onfido/android/sdk/capture/ui/camera/CapturePresenter$View;", "presenter", "Lcom/onfido/android/sdk/capture/ui/camera/CapturePresenter;", "remoteConfig", "Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;", "(Lcom/onfido/android/sdk/capture/ui/camera/CapturePresenter$View;Lcom/onfido/android/sdk/capture/ui/camera/CapturePresenter;Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;)V", "accept", "", "transformerResult", "onImageProcessingResult", "documentProcessingResults", "Lcom/onfido/android/sdk/capture/internal/validation/DocumentProcessingResults;", "onImageProcessingResult$onfido_capture_sdk_core_release", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DocumentCaptureResultConsumer implements Consumer<DocumentCaptureDelayTransformer.DocumentCaptureResult> {
    private final CapturePresenter presenter;
    private final OnfidoRemoteConfig remoteConfig;
    private final CapturePresenter.View view;

    public DocumentCaptureResultConsumer(CapturePresenter.View view, CapturePresenter presenter, OnfidoRemoteConfig remoteConfig) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(presenter, "presenter");
        Intrinsics.checkNotNullParameter(remoteConfig, "remoteConfig");
        this.view = view;
        this.presenter = presenter;
        this.remoteConfig = remoteConfig;
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public void accept(DocumentCaptureDelayTransformer.DocumentCaptureResult transformerResult) {
        Intrinsics.checkNotNullParameter(transformerResult, "transformerResult");
        if (transformerResult instanceof DocumentCaptureDelayTransformer.DocumentCaptureResult.ImageObservableResult) {
            onImageProcessingResult$onfido_capture_sdk_core_release(((DocumentCaptureDelayTransformer.DocumentCaptureResult.ImageObservableResult) transformerResult).getProcessingResults());
        } else if (Intrinsics.areEqual(transformerResult, DocumentCaptureDelayTransformer.DocumentCaptureResult.PassportDelayTimerFinished.INSTANCE)) {
            this.view.hideDocumentOverlay();
        }
    }

    public final void onImageProcessingResult$onfido_capture_sdk_core_release(DocumentProcessingResults documentProcessingResults) {
        Intrinsics.checkNotNullParameter(documentProcessingResults, "documentProcessingResults");
        boolean wasExecuted = documentProcessingResults.getGlareResults().getWasExecuted();
        boolean wasExecuted2 = documentProcessingResults.getBarcodeResults().getWasExecuted();
        boolean hasGlare = documentProcessingResults.getGlareResults().getHasGlare();
        boolean z = documentProcessingResults.getEdgeDetectionResults().hasAny() && !documentProcessingResults.getBarcodeResults().getHasBarcode();
        boolean z2 = (wasExecuted && hasGlare) || (wasExecuted2 && z);
        if (this.presenter.shouldAutoCaptureDocument$onfido_capture_sdk_core_release()) {
            if (this.presenter.isDocumentFrameValidForAutoCapture$onfido_capture_sdk_core_release(documentProcessingResults)) {
                this.presenter.setAutoCaptured$onfido_capture_sdk_core_release(true);
                this.view.capture(!this.remoteConfig.isMultiImageCaptureEnabled());
            } else {
                this.view.deactivateCaptureButton();
            }
        }
        OnfidoCaptureValidationBubble.VisibilityCommand visible = z2 ? new OnfidoCaptureValidationBubble.VisibilityCommand.Visible(new OnfidoCaptureValidationBubble.FocusType.AnnounceContent(false, 1, null)) : OnfidoCaptureValidationBubble.VisibilityCommand.Invisible.INSTANCE;
        if (z2) {
            if (hasGlare) {
                this.view.setGlareWarningContent();
            } else if (z) {
                this.view.setLiveValidationBubbleContent(new OnfidoCaptureValidationBubble.Content.Error(R.string.onfido_doc_capture_alert_no_barcode_title, Integer.valueOf(R.string.onfido_doc_capture_alert_no_barcode_detail)));
            }
        }
        this.view.setLiveValidationBubbleVisibilityCommand(visible);
    }
}
