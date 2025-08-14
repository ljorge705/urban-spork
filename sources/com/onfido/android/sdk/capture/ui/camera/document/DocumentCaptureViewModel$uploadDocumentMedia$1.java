package com.onfido.android.sdk.capture.ui.camera.document;

import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.detector.mrzextraction.MRZDocument;
import com.onfido.android.sdk.capture.flow.CaptureStepDataBundle;
import com.onfido.android.sdk.capture.flow.NfcArguments;
import com.onfido.android.sdk.capture.internal.usecase.NfcUseCase;
import com.onfido.android.sdk.capture.ui.camera.DocumentService;
import com.onfido.android.sdk.capture.ui.camera.DocumentUploadResult;
import com.onfido.android.sdk.capture.ui.camera.UploadBinaryResult;
import com.onfido.android.sdk.capture.validation.Validation;
import com.onfido.api.client.data.DocSide;
import io.reactivex.rxjava3.core.Single;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.rx3.RxAwaitKt;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureViewModel$uploadDocumentMedia$1", f = "DocumentCaptureViewModel.kt", i = {}, l = {863, 864}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class DocumentCaptureViewModel$uploadDocumentMedia$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ byte[] $jpegData;
    final /* synthetic */ List<Validation> $validations;
    int label;
    final /* synthetic */ DocumentCaptureViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DocumentCaptureViewModel$uploadDocumentMedia$1(DocumentCaptureViewModel documentCaptureViewModel, byte[] bArr, List<Validation> list, Continuation<? super DocumentCaptureViewModel$uploadDocumentMedia$1> continuation) {
        super(2, continuation);
        this.this$0 = documentCaptureViewModel;
        this.$jpegData = bArr;
        this.$validations = list;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new DocumentCaptureViewModel$uploadDocumentMedia$1(this.this$0, this.$jpegData, this.$validations, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
        } finally {
            try {
                this.this$0.hideLoading();
                return Unit.INSTANCE;
            } catch (Throwable th) {
            }
        }
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            DocumentService documentService = this.this$0.documentService;
            byte[] bArr = this.$jpegData;
            String str = this.this$0.extraFileInfo;
            DocSide docSide = this.this$0.getDocumentData$onfido_capture_sdk_core_release().getDocSide();
            if (docSide == null) {
                docSide = DocSide.FRONT;
            }
            DocSide docSide2 = docSide;
            DocumentType documentType = this.this$0.getDocumentData$onfido_capture_sdk_core_release().getDocumentType();
            if (documentType == null) {
                documentType = DocumentType.UNKNOWN;
            }
            DocumentType documentType2 = documentType;
            List<Validation> list = this.$validations;
            DocumentCaptureViewModel documentCaptureViewModel = this.this$0;
            Single<DocumentUploadResult> singleUploadDocumentMedia = documentService.uploadDocumentMedia(bArr, str, docSide2, documentType2, list, documentCaptureViewModel.sdkUploadMetadata(documentCaptureViewModel.getDocumentData$onfido_capture_sdk_core_release()), this.this$0.getDocumentData$onfido_capture_sdk_core_release().getCountryCode());
            this.label = 1;
            obj = RxAwaitKt.await(singleUploadDocumentMedia, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                this.this$0.onBinaryUploaded((UploadBinaryResult) obj);
                this.this$0.hideLoading();
                return Unit.INSTANCE;
            }
            ResultKt.throwOnFailure(obj);
        }
        Intrinsics.checkNotNullExpressionValue(obj, "await(...)");
        NfcUseCase nfcUseCase = this.this$0.nfcUseCase;
        NfcArguments nfcArguments = this.this$0.getNfcArguments();
        MRZDocument extractedMRZDocument = this.this$0.getExtractedMRZDocument();
        CaptureStepDataBundle documentData$onfido_capture_sdk_core_release = this.this$0.getDocumentData$onfido_capture_sdk_core_release();
        DocumentType documentType$onfido_capture_sdk_core_release = this.this$0.getDocumentType$onfido_capture_sdk_core_release();
        this.label = 2;
        obj = nfcUseCase.processDocumentUploadResult$onfido_capture_sdk_core_release((DocumentUploadResult) obj, nfcArguments, extractedMRZDocument, documentData$onfido_capture_sdk_core_release, documentType$onfido_capture_sdk_core_release, this);
        if (obj == coroutine_suspended) {
            return coroutine_suspended;
        }
        this.this$0.onBinaryUploaded((UploadBinaryResult) obj);
        this.this$0.hideLoading();
        return Unit.INSTANCE;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DocumentCaptureViewModel$uploadDocumentMedia$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
