package com.onfido.android.sdk.capture.ui.camera.document;

import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.internal.validation.DocumentProcessingResults;
import com.onfido.android.sdk.capture.ui.camera.DocumentDetectionFrame;
import com.onfido.android.sdk.capture.utils.CountryCode;
import com.onfido.android.sdk.capture.validation.PostCaptureDocumentValidationsManager;
import com.onfido.api.client.data.DocSide;
import io.reactivex.rxjava3.core.Single;
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
@DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureViewModel$applyPostCaptureValidations$1", f = "DocumentCaptureViewModel.kt", i = {}, l = {743}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class DocumentCaptureViewModel$applyPostCaptureValidations$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ DocumentDetectionFrame $frame;
    int label;
    final /* synthetic */ DocumentCaptureViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DocumentCaptureViewModel$applyPostCaptureValidations$1(DocumentCaptureViewModel documentCaptureViewModel, DocumentDetectionFrame documentDetectionFrame, Continuation<? super DocumentCaptureViewModel$applyPostCaptureValidations$1> continuation) {
        super(2, continuation);
        this.this$0 = documentCaptureViewModel;
        this.$frame = documentDetectionFrame;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new DocumentCaptureViewModel$applyPostCaptureValidations$1(this.this$0, this.$frame, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            PostCaptureDocumentValidationsManager postCaptureDocumentValidationsManager = this.this$0.postCaptureDocumentValidationsManager;
            DocumentDetectionFrame documentDetectionFrame = this.$frame;
            DocumentType documentType$onfido_capture_sdk_core_release = this.this$0.getDocumentType$onfido_capture_sdk_core_release();
            CountryCode countryCode = this.this$0.getCountryCode();
            DocSide documentSide = this.this$0.getDocumentSide();
            if (documentSide == null) {
                documentSide = DocSide.FRONT;
            }
            Single<DocumentProcessingResults> singleValidate$onfido_capture_sdk_core_release = postCaptureDocumentValidationsManager.validate$onfido_capture_sdk_core_release(documentDetectionFrame, documentType$onfido_capture_sdk_core_release, countryCode, documentSide);
            this.label = 1;
            obj = RxAwaitKt.await(singleValidate$onfido_capture_sdk_core_release, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        Intrinsics.checkNotNullExpressionValue(obj, "await(...)");
        DocumentProcessingResults documentProcessingResults = (DocumentProcessingResults) obj;
        this.this$0.onPostCaptureValidationsFinished(documentProcessingResults, this.$frame);
        this.this$0.documentProcessingFailureAnalyzer.analyzeDocumentProcessingResults(documentProcessingResults);
        return Unit.INSTANCE;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DocumentCaptureViewModel$applyPostCaptureValidations$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
