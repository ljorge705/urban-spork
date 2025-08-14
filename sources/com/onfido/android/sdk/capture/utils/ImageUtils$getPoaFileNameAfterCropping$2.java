package com.onfido.android.sdk.capture.utils;

import com.onfido.android.sdk.capture.internal.util.logging.Timber;
import com.onfido.android.sdk.capture.ui.camera.DocumentDetectionFrame;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "com.onfido.android.sdk.capture.utils.ImageUtils$getPoaFileNameAfterCropping$2", f = "ImageUtils.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class ImageUtils$getPoaFileNameAfterCropping$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super String>, Object> {
    final /* synthetic */ File $capturedFilesDir;
    final /* synthetic */ DocumentDetectionFrame $frame;
    final /* synthetic */ byte[] $jpegData;
    final /* synthetic */ String $poaImageFileName;
    int label;
    final /* synthetic */ ImageUtils this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ImageUtils$getPoaFileNameAfterCropping$2(ImageUtils imageUtils, byte[] bArr, DocumentDetectionFrame documentDetectionFrame, File file, String str, Continuation<? super ImageUtils$getPoaFileNameAfterCropping$2> continuation) {
        super(2, continuation);
        this.this$0 = imageUtils;
        this.$jpegData = bArr;
        this.$frame = documentDetectionFrame;
        this.$capturedFilesDir = file;
        this.$poaImageFileName = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ImageUtils$getPoaFileNameAfterCropping$2(this.this$0, this.$jpegData, this.$frame, this.$capturedFilesDir, this.$poaImageFileName, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws IOException {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        byte[] imageContent = this.this$0.cropImageForScreenShowOnly$onfido_capture_sdk_core_release(this.$jpegData, this.$frame).getImageContent();
        File file = new File(this.$capturedFilesDir, this.$poaImageFileName);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file.getPath());
            fileOutputStream.write(imageContent);
            fileOutputStream.close();
            return file.getPath();
        } catch (IOException e) {
            Timber.INSTANCE.e(e, "POA_Debug: Failed saving image", new Object[0]);
            return null;
        }
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super String> continuation) {
        return ((ImageUtils$getPoaFileNameAfterCropping$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
