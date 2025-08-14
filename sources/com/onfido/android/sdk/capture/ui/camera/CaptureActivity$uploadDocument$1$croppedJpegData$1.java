package com.onfido.android.sdk.capture.ui.camera;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0012\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.CaptureActivity$uploadDocument$1$croppedJpegData$1", f = "CaptureActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class CaptureActivity$uploadDocument$1$croppedJpegData$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super byte[]>, Object> {
    final /* synthetic */ byte[] $jpegData;
    int label;
    final /* synthetic */ CaptureActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CaptureActivity$uploadDocument$1$croppedJpegData$1(CaptureActivity captureActivity, byte[] bArr, Continuation<? super CaptureActivity$uploadDocument$1$croppedJpegData$1> continuation) {
        super(2, continuation);
        this.this$0 = captureActivity;
        this.$jpegData = bArr;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new CaptureActivity$uploadDocument$1$croppedJpegData$1(this.this$0, this.$jpegData, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        return this.this$0.getOnfidoRemoteConfig$onfido_capture_sdk_core_release().isDocumentCropDisabled() ? this.$jpegData : this.this$0.getImageUtils$onfido_capture_sdk_core_release().cropImage$onfido_capture_sdk_core_release(this.$jpegData, this.this$0.getPresenter$onfido_capture_sdk_core_release().getDocFrame$onfido_capture_sdk_core_release(), this.this$0.getOnfidoRemoteConfig$onfido_capture_sdk_core_release().getDocumentCapture().getImageCompressionQuality(), this.this$0.getPresenter$onfido_capture_sdk_core_release().isCameraXEnabled$onfido_capture_sdk_core_release(), this.this$0.getPresenter$onfido_capture_sdk_core_release().isFourByThreeEnabled$onfido_capture_sdk_core_release(), !this.this$0.getEnvironmentIntegrityChecker$onfido_capture_sdk_core_release().hasEnvironmentIntegrity()).getImageContent();
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super byte[]> continuation) {
        return ((CaptureActivity$uploadDocument$1$croppedJpegData$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
