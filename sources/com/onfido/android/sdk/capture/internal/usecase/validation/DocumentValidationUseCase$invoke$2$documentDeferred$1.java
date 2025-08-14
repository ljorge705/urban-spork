package com.onfido.android.sdk.capture.internal.usecase.validation;

import com.onfido.android.sdk.capture.ui.camera.DocumentDetectionFrame;
import com.onfido.android.sdk.capture.utils.mlmodel.OnfidoDocumentDetector;
import com.onfido.android.sdk.capture.utils.mlmodel.OnfidoMlDocument;
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

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "Lcom/onfido/android/sdk/capture/utils/mlmodel/OnfidoMlDocument;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "com.onfido.android.sdk.capture.internal.usecase.validation.DocumentValidationUseCase$invoke$2$documentDeferred$1", f = "DocumentValidationUseCase.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class DocumentValidationUseCase$invoke$2$documentDeferred$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends OnfidoMlDocument>>, Object> {
    final /* synthetic */ DocumentDetectionFrame $frame;
    int label;
    final /* synthetic */ DocumentValidationUseCase this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DocumentValidationUseCase$invoke$2$documentDeferred$1(DocumentValidationUseCase documentValidationUseCase, DocumentDetectionFrame documentDetectionFrame, Continuation<? super DocumentValidationUseCase$invoke$2$documentDeferred$1> continuation) {
        super(2, continuation);
        this.this$0 = documentValidationUseCase;
        this.$frame = documentDetectionFrame;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new DocumentValidationUseCase$invoke$2$documentDeferred$1(this.this$0, this.$frame, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends OnfidoMlDocument>> continuation) {
        return invoke2(coroutineScope, (Continuation<? super List<OnfidoMlDocument>>) continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        OnfidoDocumentDetector onfidoDocumentDetector = this.this$0.onfidoDocumentDetector;
        if (onfidoDocumentDetector == null) {
            Intrinsics.throwUninitializedPropertyAccessException("onfidoDocumentDetector");
            onfidoDocumentDetector = null;
        }
        return onfidoDocumentDetector.detect(this.$frame.getBitmap());
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super List<OnfidoMlDocument>> continuation) {
        return ((DocumentValidationUseCase$invoke$2$documentDeferred$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
