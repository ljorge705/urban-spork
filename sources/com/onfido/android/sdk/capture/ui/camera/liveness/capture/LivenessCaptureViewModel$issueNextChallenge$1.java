package com.onfido.android.sdk.capture.ui.camera.liveness.capture;

import com.drew.metadata.mp4.media.Mp4VideoDirectory;
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
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.SharedFlow;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureViewModel$issueNextChallenge$1", f = "LivenessCaptureViewModel.kt", i = {}, l = {Mp4VideoDirectory.TAG_COMPRESSION_TYPE}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class LivenessCaptureViewModel$issueNextChallenge$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ LivenessCaptureViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    LivenessCaptureViewModel$issueNextChallenge$1(LivenessCaptureViewModel livenessCaptureViewModel, Continuation<? super LivenessCaptureViewModel$issueNextChallenge$1> continuation) {
        super(2, continuation);
        this.this$0 = livenessCaptureViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new LivenessCaptureViewModel$issueNextChallenge$1(this.this$0, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            SharedFlow<Unit> livenessControlButtonSharedFlow = this.this$0.livenessInteractor.getLivenessControlButtonSharedFlow();
            Intrinsics.checkNotNull(livenessControlButtonSharedFlow, "null cannot be cast to non-null type kotlinx.coroutines.flow.MutableSharedFlow<kotlin.Unit>");
            Unit unit = Unit.INSTANCE;
            this.label = 1;
            if (((MutableSharedFlow) livenessControlButtonSharedFlow).emit(unit, this) == coroutine_suspended) {
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
        return ((LivenessCaptureViewModel$issueNextChallenge$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
