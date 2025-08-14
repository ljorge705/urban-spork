package com.onfido.android.sdk.capture.ui.camera;

import com.onfido.android.sdk.capture.internal.usecase.validation.DocumentValidationTargets;
import com.onfido.android.sdk.capture.internal.usecase.validation.DocumentValidationUseCase;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.CapturePresenter$onNextFrame$1$1", f = "CapturePresenter.kt", i = {}, l = {1167}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class CapturePresenter$onNextFrame$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ DocumentDetectionFrame $frame;
    final /* synthetic */ DocumentValidationTargets $targetValidation;
    int label;
    final /* synthetic */ CapturePresenter this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CapturePresenter$onNextFrame$1$1(CapturePresenter capturePresenter, DocumentDetectionFrame documentDetectionFrame, DocumentValidationTargets documentValidationTargets, Continuation<? super CapturePresenter$onNextFrame$1$1> continuation) {
        super(2, continuation);
        this.this$0 = capturePresenter;
        this.$frame = documentDetectionFrame;
        this.$targetValidation = documentValidationTargets;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new CapturePresenter$onNextFrame$1$1(this.this$0, this.$frame, this.$targetValidation, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            DocumentValidationUseCase documentValidationUseCase = this.this$0.documentValidationUseCase;
            DocumentDetectionFrame documentDetectionFrame = this.$frame;
            DocumentValidationTargets documentValidationTargets = this.$targetValidation;
            this.label = 1;
            if (documentValidationUseCase.invoke(documentDetectionFrame, documentValidationTargets, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CapturePresenter$onNextFrame$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
