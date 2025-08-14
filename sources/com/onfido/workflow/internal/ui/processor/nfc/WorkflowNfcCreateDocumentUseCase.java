package com.onfido.workflow.internal.ui.processor.nfc;

import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.NfcTracker;
import com.onfido.android.sdk.capture.ui.nfc.NfcDataRepository;
import com.onfido.android.sdk.capture.ui.nfc.NfcHostFragment;
import com.onfido.android.sdk.capture.upload.ErrorType;
import com.onfido.api.client.data.DocumentMediaUploadResponse;
import com.onfido.api.client.exception.TokenExpiredException;
import com.onfido.javax.inject.Inject;
import com.onfido.workflow.internal.ui.model.MediaUpload;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import retrofit2.HttpException;

/* compiled from: WorkflowNfcCreateDocumentUseCase.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001:\u0001\u0013B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J$\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\b\u0010\r\u001a\u0004\u0018\u00010\u000eJ\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/onfido/workflow/internal/ui/processor/nfc/WorkflowNfcCreateDocumentUseCase;", "", "nfcDataRepository", "Lcom/onfido/android/sdk/capture/ui/nfc/NfcDataRepository;", "nfcTracker", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/NfcTracker;", "(Lcom/onfido/android/sdk/capture/ui/nfc/NfcDataRepository;Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/NfcTracker;)V", "execute", "Lio/reactivex/rxjava3/core/Single;", "Lcom/onfido/workflow/internal/ui/processor/nfc/WorkflowNfcCreateDocumentUseCase$Outcome;", "mediaUploads", "", "Lcom/onfido/workflow/internal/ui/model/MediaUpload;", "nfcSuccessResult", "Lcom/onfido/android/sdk/capture/ui/nfc/NfcHostFragment$NfcHostResult$NfcScanSuccess;", "mapThrowableToUploadError", "Lcom/onfido/android/sdk/capture/upload/ErrorType;", "throwable", "", "Outcome", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class WorkflowNfcCreateDocumentUseCase {
    private final NfcDataRepository nfcDataRepository;
    private final NfcTracker nfcTracker;

    @Inject
    public WorkflowNfcCreateDocumentUseCase(NfcDataRepository nfcDataRepository, NfcTracker nfcTracker) {
        Intrinsics.checkNotNullParameter(nfcDataRepository, "nfcDataRepository");
        Intrinsics.checkNotNullParameter(nfcTracker, "nfcTracker");
        this.nfcDataRepository = nfcDataRepository;
        this.nfcTracker = nfcTracker;
    }

    /* compiled from: WorkflowNfcCreateDocumentUseCase.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006¨\u0006\u0007"}, d2 = {"Lcom/onfido/workflow/internal/ui/processor/nfc/WorkflowNfcCreateDocumentUseCase$Outcome;", "", "()V", "Failure", "Success", "Lcom/onfido/workflow/internal/ui/processor/nfc/WorkflowNfcCreateDocumentUseCase$Outcome$Failure;", "Lcom/onfido/workflow/internal/ui/processor/nfc/WorkflowNfcCreateDocumentUseCase$Outcome$Success;", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static abstract class Outcome {
        public /* synthetic */ Outcome(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* compiled from: WorkflowNfcCreateDocumentUseCase.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/onfido/workflow/internal/ui/processor/nfc/WorkflowNfcCreateDocumentUseCase$Outcome$Success;", "Lcom/onfido/workflow/internal/ui/processor/nfc/WorkflowNfcCreateDocumentUseCase$Outcome;", "mediaUploads", "", "Lcom/onfido/workflow/internal/ui/model/MediaUpload;", "(Ljava/util/List;)V", "getMediaUploads", "()Ljava/util/List;", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Success extends Outcome {
            private final List<MediaUpload> mediaUploads;

            public final List<MediaUpload> getMediaUploads() {
                return this.mediaUploads;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public Success(List<MediaUpload> mediaUploads) {
                super(null);
                Intrinsics.checkNotNullParameter(mediaUploads, "mediaUploads");
                this.mediaUploads = mediaUploads;
            }
        }

        private Outcome() {
        }

        /* compiled from: WorkflowNfcCreateDocumentUseCase.kt */
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/onfido/workflow/internal/ui/processor/nfc/WorkflowNfcCreateDocumentUseCase$Outcome$Failure;", "Lcom/onfido/workflow/internal/ui/processor/nfc/WorkflowNfcCreateDocumentUseCase$Outcome;", "errorType", "Lcom/onfido/android/sdk/capture/upload/ErrorType;", "(Lcom/onfido/android/sdk/capture/upload/ErrorType;)V", "getErrorType", "()Lcom/onfido/android/sdk/capture/upload/ErrorType;", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Failure extends Outcome {
            private final ErrorType errorType;

            public final ErrorType getErrorType() {
                return this.errorType;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public Failure(ErrorType errorType) {
                super(null);
                Intrinsics.checkNotNullParameter(errorType, "errorType");
                this.errorType = errorType;
            }
        }
    }

    public final Single<Outcome> execute(final List<MediaUpload> mediaUploads, final NfcHostFragment.NfcHostResult.NfcScanSuccess nfcSuccessResult) {
        Intrinsics.checkNotNullParameter(mediaUploads, "mediaUploads");
        if (nfcSuccessResult == null) {
            Single<Outcome> singleJust = Single.just(new Outcome.Success(mediaUploads));
            Intrinsics.checkNotNull(singleJust);
            return singleJust;
        }
        Single<Outcome> singleDoOnSuccess = this.nfcDataRepository.uploadData(nfcSuccessResult.getNfcData(), DocumentType.PASSPORT).map(new Function() { // from class: com.onfido.workflow.internal.ui.processor.nfc.WorkflowNfcCreateDocumentUseCase.execute.1
            @Override // io.reactivex.rxjava3.functions.Function
            public final Outcome.Success apply(DocumentMediaUploadResponse documentMediaUpload) {
                Intrinsics.checkNotNullParameter(documentMediaUpload, "documentMediaUpload");
                return new Outcome.Success(CollectionsKt.plus((Collection<? extends MediaUpload>) mediaUploads, new MediaUpload(documentMediaUpload.mediaId(), MediaUpload.Type.DOCUMENT_NFC)));
            }
        }).cast(Outcome.class).onErrorReturn(new Function() { // from class: com.onfido.workflow.internal.ui.processor.nfc.WorkflowNfcCreateDocumentUseCase$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.functions.Function
            public final Object apply(Object obj) {
                return WorkflowNfcCreateDocumentUseCase.execute$lambda$0(this.f$0, (Throwable) obj);
            }
        }).doOnSubscribe(new Consumer() { // from class: com.onfido.workflow.internal.ui.processor.nfc.WorkflowNfcCreateDocumentUseCase.execute.3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Disposable it) {
                Intrinsics.checkNotNullParameter(it, "it");
                WorkflowNfcCreateDocumentUseCase.this.nfcTracker.trackDataUploadStarted(nfcSuccessResult.getNfcFlowType());
            }
        }).doOnSuccess(new Consumer() { // from class: com.onfido.workflow.internal.ui.processor.nfc.WorkflowNfcCreateDocumentUseCase.execute.4
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Outcome outcome) {
                WorkflowNfcCreateDocumentUseCase.this.nfcTracker.trackDataUploadCompleted(nfcSuccessResult.getNfcFlowType());
            }
        });
        Intrinsics.checkNotNull(singleDoOnSuccess);
        return singleDoOnSuccess;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Outcome execute$lambda$0(WorkflowNfcCreateDocumentUseCase this$0, Throwable throwable) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(throwable, "throwable");
        return new Outcome.Failure(this$0.mapThrowableToUploadError(throwable));
    }

    private final ErrorType mapThrowableToUploadError(Throwable throwable) {
        if (throwable instanceof TokenExpiredException) {
            return ErrorType.TokenExpired.INSTANCE;
        }
        if (!(throwable instanceof SSLPeerUnverifiedException)) {
            return ((throwable instanceof HttpException) || (throwable instanceof IOException)) ? ErrorType.Network.INSTANCE : ErrorType.Generic.INSTANCE;
        }
        String localizedMessage = ((SSLPeerUnverifiedException) throwable).getLocalizedMessage();
        if (localizedMessage == null) {
            localizedMessage = "";
        }
        return new ErrorType.InvalidCertificate(localizedMessage);
    }
}
