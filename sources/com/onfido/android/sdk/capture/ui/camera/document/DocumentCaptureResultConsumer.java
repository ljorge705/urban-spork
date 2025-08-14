package com.onfido.android.sdk.capture.ui.camera.document;

import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.core.OnfidoLauncher;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.validation.DocumentProcessingResults;
import com.onfido.android.sdk.capture.ui.camera.rx.DocumentCaptureDelayTransformer;
import com.onfido.android.sdk.capture.validation.OnfidoCaptureValidationBubble;
import io.reactivex.rxjava3.functions.Consumer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendFunction;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.MutableStateFlow;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u001e\b\u0001\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0003B[\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000e\u0012\u000e\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000e\u0012\u000e\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0011\u0012\u000e\u0010\u0013\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u0011¢\u0006\u0002\u0010\u0015J\u0010\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u0002H\u0016J\u0016\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u0002H\u0096B¢\u0006\u0002\u0010\u001aJ\u0015\u0010\u001b\u001a\u00020\u00052\u0006\u0010\u001c\u001a\u00020\u001dH\u0000¢\u0006\u0002\b\u001eR\u0016\u0010\u0013\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/document/DocumentCaptureResultConsumer;", "Lio/reactivex/rxjava3/functions/Consumer;", "Lcom/onfido/android/sdk/capture/ui/camera/rx/DocumentCaptureDelayTransformer$DocumentCaptureResult;", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "", "viewModel", "Lcom/onfido/android/sdk/capture/ui/camera/document/DocumentCaptureViewModel;", "remoteConfig", "Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;", "shouldAutocapture", "", "shouldHideOverlay", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "startCapture", "showLiveValidationBubble", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/onfido/android/sdk/capture/validation/OnfidoCaptureValidationBubble$Content;", "liveValidationBubbleVisibility", "Lcom/onfido/android/sdk/capture/validation/OnfidoCaptureValidationBubble$VisibilityCommand;", "(Lcom/onfido/android/sdk/capture/ui/camera/document/DocumentCaptureViewModel;Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;ZLkotlinx/coroutines/flow/MutableSharedFlow;Lkotlinx/coroutines/flow/MutableSharedFlow;Lkotlinx/coroutines/flow/MutableStateFlow;Lkotlinx/coroutines/flow/MutableStateFlow;)V", "accept", "transformerResult", "invoke", OnfidoLauncher.KEY_RESULT, "(Lcom/onfido/android/sdk/capture/ui/camera/rx/DocumentCaptureDelayTransformer$DocumentCaptureResult;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onImageProcessingResult", "documentProcessingResults", "Lcom/onfido/android/sdk/capture/internal/validation/DocumentProcessingResults;", "onImageProcessingResult$onfido_capture_sdk_core_release", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DocumentCaptureResultConsumer implements Consumer<DocumentCaptureDelayTransformer.DocumentCaptureResult>, Function2<DocumentCaptureDelayTransformer.DocumentCaptureResult, Continuation<? super Unit>, Object>, SuspendFunction {
    private final MutableStateFlow<OnfidoCaptureValidationBubble.VisibilityCommand> liveValidationBubbleVisibility;
    private final OnfidoRemoteConfig remoteConfig;
    private final boolean shouldAutocapture;
    private final MutableSharedFlow<Boolean> shouldHideOverlay;
    private final MutableStateFlow<OnfidoCaptureValidationBubble.Content> showLiveValidationBubble;
    private final MutableSharedFlow<Boolean> startCapture;
    private final DocumentCaptureViewModel viewModel;

    public DocumentCaptureResultConsumer(DocumentCaptureViewModel viewModel, OnfidoRemoteConfig remoteConfig, boolean z, MutableSharedFlow<Boolean> shouldHideOverlay, MutableSharedFlow<Boolean> startCapture, MutableStateFlow<OnfidoCaptureValidationBubble.Content> showLiveValidationBubble, MutableStateFlow<OnfidoCaptureValidationBubble.VisibilityCommand> liveValidationBubbleVisibility) {
        Intrinsics.checkNotNullParameter(viewModel, "viewModel");
        Intrinsics.checkNotNullParameter(remoteConfig, "remoteConfig");
        Intrinsics.checkNotNullParameter(shouldHideOverlay, "shouldHideOverlay");
        Intrinsics.checkNotNullParameter(startCapture, "startCapture");
        Intrinsics.checkNotNullParameter(showLiveValidationBubble, "showLiveValidationBubble");
        Intrinsics.checkNotNullParameter(liveValidationBubbleVisibility, "liveValidationBubbleVisibility");
        this.viewModel = viewModel;
        this.remoteConfig = remoteConfig;
        this.shouldAutocapture = z;
        this.shouldHideOverlay = shouldHideOverlay;
        this.startCapture = startCapture;
        this.showLiveValidationBubble = showLiveValidationBubble;
        this.liveValidationBubbleVisibility = liveValidationBubbleVisibility;
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public void accept(DocumentCaptureDelayTransformer.DocumentCaptureResult transformerResult) {
        Intrinsics.checkNotNullParameter(transformerResult, "transformerResult");
        if (transformerResult instanceof DocumentCaptureDelayTransformer.DocumentCaptureResult.ImageObservableResult) {
            onImageProcessingResult$onfido_capture_sdk_core_release(((DocumentCaptureDelayTransformer.DocumentCaptureResult.ImageObservableResult) transformerResult).getProcessingResults());
        } else if (Intrinsics.areEqual(transformerResult, DocumentCaptureDelayTransformer.DocumentCaptureResult.PassportDelayTimerFinished.INSTANCE)) {
            this.shouldHideOverlay.tryEmit(Boolean.TRUE);
        }
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(DocumentCaptureDelayTransformer.DocumentCaptureResult documentCaptureResult, Continuation<? super Unit> continuation) {
        accept(documentCaptureResult);
        return Unit.INSTANCE;
    }

    public final void onImageProcessingResult$onfido_capture_sdk_core_release(DocumentProcessingResults documentProcessingResults) {
        MutableStateFlow<OnfidoCaptureValidationBubble.Content> mutableStateFlow;
        OnfidoCaptureValidationBubble.Content error;
        Intrinsics.checkNotNullParameter(documentProcessingResults, "documentProcessingResults");
        boolean wasExecuted = documentProcessingResults.getGlareResults().getWasExecuted();
        boolean wasExecuted2 = documentProcessingResults.getBarcodeResults().getWasExecuted();
        boolean hasGlare = documentProcessingResults.getGlareResults().getHasGlare();
        boolean z = documentProcessingResults.getEdgeDetectionResults().hasAny() && !documentProcessingResults.getBarcodeResults().getHasBarcode();
        boolean z2 = (wasExecuted && hasGlare) || (wasExecuted2 && z);
        if (this.shouldAutocapture && this.viewModel.isDocumentFrameValidForAutoCapture$onfido_capture_sdk_core_release(documentProcessingResults)) {
            this.viewModel.setAutoCaptured$onfido_capture_sdk_core_release(true);
            this.startCapture.tryEmit(Boolean.valueOf(!this.remoteConfig.isMultiImageCaptureEnabled()));
        }
        OnfidoCaptureValidationBubble.VisibilityCommand visible = z2 ? new OnfidoCaptureValidationBubble.VisibilityCommand.Visible(new OnfidoCaptureValidationBubble.FocusType.AnnounceContent(false, 1, null)) : OnfidoCaptureValidationBubble.VisibilityCommand.Invisible.INSTANCE;
        if (z2) {
            if (hasGlare) {
                mutableStateFlow = this.showLiveValidationBubble;
                error = new OnfidoCaptureValidationBubble.Content.Info(R.string.onfido_doc_capture_alert_glare_title, Integer.valueOf(R.string.onfido_doc_capture_alert_glare_detail));
            } else if (z) {
                mutableStateFlow = this.showLiveValidationBubble;
                error = new OnfidoCaptureValidationBubble.Content.Error(R.string.onfido_doc_capture_alert_no_barcode_title, Integer.valueOf(R.string.onfido_doc_capture_alert_no_barcode_detail));
            }
            mutableStateFlow.tryEmit(error);
        }
        this.liveValidationBubbleVisibility.tryEmit(visible);
    }
}
