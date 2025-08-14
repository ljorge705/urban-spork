package com.onfido.android.sdk.capture.ui.camera.document;

import com.drew.metadata.iptc.IptcDirectory;
import com.onfido.android.sdk.capture.validation.OnfidoCaptureValidationBubble;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$onManualFallbackDelayFinished$1$1", f = "DocumentCaptureFragment.kt", i = {}, l = {IptcDirectory.TAG_COPYRIGHT_NOTICE}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class DocumentCaptureFragment$onManualFallbackDelayFinished$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ OnfidoCaptureValidationBubble $this_with;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DocumentCaptureFragment$onManualFallbackDelayFinished$1$1(OnfidoCaptureValidationBubble onfidoCaptureValidationBubble, Continuation<? super DocumentCaptureFragment$onManualFallbackDelayFinished$1$1> continuation) {
        super(2, continuation);
        this.$this_with = onfidoCaptureValidationBubble;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new DocumentCaptureFragment$onManualFallbackDelayFinished$1$1(this.$this_with, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            long j = this.$this_with.readingTimeMilliseconds$onfido_capture_sdk_core_release();
            this.label = 1;
            if (DelayKt.delay(j, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        this.$this_with.setState$onfido_capture_sdk_core_release(OnfidoCaptureValidationBubble.State.SOFT_LOCK);
        return Unit.INSTANCE;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DocumentCaptureFragment$onManualFallbackDelayFinished$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
