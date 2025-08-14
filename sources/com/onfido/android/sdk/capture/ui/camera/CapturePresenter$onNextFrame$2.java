package com.onfido.android.sdk.capture.ui.camera;

import com.onfido.android.sdk.capture.detector.mrzextraction.MRZDocument;
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
@DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.CapturePresenter$onNextFrame$2", f = "CapturePresenter.kt", i = {}, l = {1179}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class CapturePresenter$onNextFrame$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ DocumentDetectionFrame $frame;
    int label;
    final /* synthetic */ CapturePresenter this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CapturePresenter$onNextFrame$2(CapturePresenter capturePresenter, DocumentDetectionFrame documentDetectionFrame, Continuation<? super CapturePresenter$onNextFrame$2> continuation) {
        super(2, continuation);
        this.this$0 = capturePresenter;
        this.$frame = documentDetectionFrame;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new CapturePresenter$onNextFrame$2(this.this$0, this.$frame, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            MRZDocument extractedMRZDocument = this.this$0.getExtractedMRZDocument();
            boolean z = extractedMRZDocument != null && extractedMRZDocument.isValid();
            if (this.this$0.onfidoRemoteConfig.isOnDeviceMRZExtractionEnabled() && !z) {
                CapturePresenter capturePresenter = this.this$0;
                DocumentDetectionFrame documentDetectionFrame = this.$frame;
                this.label = 1;
                if (capturePresenter.processFrameForMRZ(documentDetectionFrame, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
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
        return ((CapturePresenter$onNextFrame$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
