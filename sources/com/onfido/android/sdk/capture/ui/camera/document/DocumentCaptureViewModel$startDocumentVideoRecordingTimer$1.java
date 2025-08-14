package com.onfido.android.sdk.capture.ui.camera.document;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureViewModel$startDocumentVideoRecordingTimer$1", f = "DocumentCaptureViewModel.kt", i = {}, l = {1098, 1103, 1105, 1107}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class DocumentCaptureViewModel$startDocumentVideoRecordingTimer$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function0<Boolean> $hasValidRecording;
    final /* synthetic */ long $recordingTimeoutMs;
    final /* synthetic */ long $torchTurnOnTimeMs;
    final /* synthetic */ long $videoLengthMs;
    int label;
    final /* synthetic */ DocumentCaptureViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DocumentCaptureViewModel$startDocumentVideoRecordingTimer$1(long j, long j2, long j3, DocumentCaptureViewModel documentCaptureViewModel, Function0<Boolean> function0, Continuation<? super DocumentCaptureViewModel$startDocumentVideoRecordingTimer$1> continuation) {
        super(2, continuation);
        this.$recordingTimeoutMs = j;
        this.$torchTurnOnTimeMs = j2;
        this.$videoLengthMs = j3;
        this.this$0 = documentCaptureViewModel;
        this.$hasValidRecording = function0;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new DocumentCaptureViewModel$startDocumentVideoRecordingTimer$1(this.$recordingTimeoutMs, this.$torchTurnOnTimeMs, this.$videoLengthMs, this.this$0, this.$hasValidRecording, continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0073 A[RETURN] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r11) {
        /*
            r10 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r10.label
            r2 = 4
            r3 = 3
            r4 = 2
            r5 = 1
            if (r1 == 0) goto L2d
            if (r1 == r5) goto L25
            if (r1 == r4) goto L21
            if (r1 == r3) goto L1d
            if (r1 != r2) goto L15
            goto L1d
        L15:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r0)
            throw r11
        L1d:
            kotlin.ResultKt.throwOnFailure(r11)     // Catch: java.lang.Throwable -> L29 kotlinx.coroutines.TimeoutCancellationException -> L2b
            goto L8e
        L21:
            kotlin.ResultKt.throwOnFailure(r11)     // Catch: java.lang.Throwable -> L29 kotlinx.coroutines.TimeoutCancellationException -> L2b
            goto L59
        L25:
            kotlin.ResultKt.throwOnFailure(r11)     // Catch: java.lang.Throwable -> L29 kotlinx.coroutines.TimeoutCancellationException -> L2b
            goto L42
        L29:
            r11 = move-exception
            goto L96
        L2b:
            r11 = move-exception
            goto L7f
        L2d:
            kotlin.ResultKt.throwOnFailure(r11)
            long r6 = r10.$recordingTimeoutMs     // Catch: java.lang.Throwable -> L29 kotlinx.coroutines.TimeoutCancellationException -> L2b
            com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureViewModel$startDocumentVideoRecordingTimer$1$1 r11 = new com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureViewModel$startDocumentVideoRecordingTimer$1$1     // Catch: java.lang.Throwable -> L29 kotlinx.coroutines.TimeoutCancellationException -> L2b
            kotlin.jvm.functions.Function0<java.lang.Boolean> r1 = r10.$hasValidRecording     // Catch: java.lang.Throwable -> L29 kotlinx.coroutines.TimeoutCancellationException -> L2b
            r11.<init>()     // Catch: java.lang.Throwable -> L29 kotlinx.coroutines.TimeoutCancellationException -> L2b
            r10.label = r5     // Catch: java.lang.Throwable -> L29 kotlinx.coroutines.TimeoutCancellationException -> L2b
            java.lang.Object r11 = com.onfido.android.sdk.capture.utils.FlowExtKt.delayUntil(r6, r11, r10)     // Catch: java.lang.Throwable -> L29 kotlinx.coroutines.TimeoutCancellationException -> L2b
            if (r11 != r0) goto L42
            return r0
        L42:
            long r6 = r10.$torchTurnOnTimeMs     // Catch: java.lang.Throwable -> L29 kotlinx.coroutines.TimeoutCancellationException -> L2b
            r8 = 1
            int r11 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
            if (r11 > 0) goto L74
            long r8 = r10.$videoLengthMs     // Catch: java.lang.Throwable -> L29 kotlinx.coroutines.TimeoutCancellationException -> L2b
            int r11 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r11 >= 0) goto L74
            r10.label = r4     // Catch: java.lang.Throwable -> L29 kotlinx.coroutines.TimeoutCancellationException -> L2b
            java.lang.Object r11 = kotlinx.coroutines.DelayKt.delay(r6, r10)     // Catch: java.lang.Throwable -> L29 kotlinx.coroutines.TimeoutCancellationException -> L2b
            if (r11 != r0) goto L59
            return r0
        L59:
            com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureViewModel r11 = r10.this$0     // Catch: java.lang.Throwable -> L29 kotlinx.coroutines.TimeoutCancellationException -> L2b
            kotlinx.coroutines.flow.MutableStateFlow r11 = com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureViewModel.access$get_shouldEnableTorch$p(r11)     // Catch: java.lang.Throwable -> L29 kotlinx.coroutines.TimeoutCancellationException -> L2b
            java.lang.Boolean r1 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r5)     // Catch: java.lang.Throwable -> L29 kotlinx.coroutines.TimeoutCancellationException -> L2b
            r11.setValue(r1)     // Catch: java.lang.Throwable -> L29 kotlinx.coroutines.TimeoutCancellationException -> L2b
            long r1 = r10.$videoLengthMs     // Catch: java.lang.Throwable -> L29 kotlinx.coroutines.TimeoutCancellationException -> L2b
            long r4 = r10.$torchTurnOnTimeMs     // Catch: java.lang.Throwable -> L29 kotlinx.coroutines.TimeoutCancellationException -> L2b
            long r1 = r1 - r4
            r10.label = r3     // Catch: java.lang.Throwable -> L29 kotlinx.coroutines.TimeoutCancellationException -> L2b
            java.lang.Object r11 = kotlinx.coroutines.DelayKt.delay(r1, r10)     // Catch: java.lang.Throwable -> L29 kotlinx.coroutines.TimeoutCancellationException -> L2b
            if (r11 != r0) goto L8e
            return r0
        L74:
            long r3 = r10.$videoLengthMs     // Catch: java.lang.Throwable -> L29 kotlinx.coroutines.TimeoutCancellationException -> L2b
            r10.label = r2     // Catch: java.lang.Throwable -> L29 kotlinx.coroutines.TimeoutCancellationException -> L2b
            java.lang.Object r11 = kotlinx.coroutines.DelayKt.delay(r3, r10)     // Catch: java.lang.Throwable -> L29 kotlinx.coroutines.TimeoutCancellationException -> L2b
            if (r11 != r0) goto L8e
            return r0
        L7f:
            com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureViewModel r0 = r10.this$0     // Catch: java.lang.Throwable -> L29
            com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureViewModel.access$onErrorVideoTaking(r0)     // Catch: java.lang.Throwable -> L29
            com.onfido.android.sdk.capture.internal.util.logging.Timber$Forest r0 = com.onfido.android.sdk.capture.internal.util.logging.Timber.INSTANCE     // Catch: java.lang.Throwable -> L29
            java.lang.String r1 = "startDocumentVideoRecordingTimer failed"
            r2 = 0
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> L29
            r0.e(r11, r1, r2)     // Catch: java.lang.Throwable -> L29
        L8e:
            com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureViewModel r11 = r10.this$0
            com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureViewModel.access$stopDocumentRecording(r11)
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        L96:
            com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureViewModel r0 = r10.this$0
            com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureViewModel.access$stopDocumentRecording(r0)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureViewModel$startDocumentVideoRecordingTimer$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DocumentCaptureViewModel$startDocumentVideoRecordingTimer$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
