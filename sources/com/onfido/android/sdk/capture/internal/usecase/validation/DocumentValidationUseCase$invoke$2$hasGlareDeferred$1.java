package com.onfido.android.sdk.capture.internal.usecase.validation;

import com.onfido.android.sdk.capture.ui.camera.DocumentDetectionFrame;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "com.onfido.android.sdk.capture.internal.usecase.validation.DocumentValidationUseCase$invoke$2$hasGlareDeferred$1", f = "DocumentValidationUseCase.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class DocumentValidationUseCase$invoke$2$hasGlareDeferred$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
    final /* synthetic */ DocumentDetectionFrame $frame;
    int label;
    final /* synthetic */ DocumentValidationUseCase this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DocumentValidationUseCase$invoke$2$hasGlareDeferred$1(DocumentValidationUseCase documentValidationUseCase, DocumentDetectionFrame documentDetectionFrame, Continuation<? super DocumentValidationUseCase$invoke$2$hasGlareDeferred$1> continuation) {
        super(2, continuation);
        this.this$0 = documentValidationUseCase;
        this.$frame = documentDetectionFrame;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new DocumentValidationUseCase$invoke$2$hasGlareDeferred$1(this.this$0, this.$frame, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        return Boxing.boxBoolean(this.this$0.getNativeDetector().isGlareDetected(this.$frame));
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
        return ((DocumentValidationUseCase$invoke$2$hasGlareDeferred$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
