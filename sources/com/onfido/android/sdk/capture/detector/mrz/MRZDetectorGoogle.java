package com.onfido.android.sdk.capture.detector.mrz;

import android.graphics.Bitmap;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.mlkit.vision.text.latin.TextRecognizerOptions;
import com.onfido.android.sdk.capture.ui.camera.DocumentDetectionFrame;
import com.onfido.android.sdk.capture.utils.ImageUtils;
import com.onfido.android.sdk.capture.validation.DocumentCodeValidator;
import com.onfido.javax.inject.Inject;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0007\b\u0007¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J0\u0010\n\u001a\u00020\u000b*\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\b\u001a\u00020\t2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002¨\u0006\u0012"}, d2 = {"Lcom/onfido/android/sdk/capture/detector/mrz/MRZDetectorGoogle;", "Lcom/onfido/android/sdk/capture/detector/mrz/MRZDetector;", "()V", "detect", "Lio/reactivex/rxjava3/core/Single;", "", "frame", "Lcom/onfido/android/sdk/capture/ui/camera/DocumentDetectionFrame;", "validator", "Lcom/onfido/android/sdk/capture/validation/DocumentCodeValidator;", "addSuccessAndFailureListeners", "", "Lcom/google/android/gms/tasks/Task;", "Lcom/google/mlkit/vision/text/Text;", "emitter", "Lio/reactivex/rxjava3/core/SingleEmitter;", "textRecognizer", "Lcom/google/mlkit/vision/text/TextRecognizer;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class MRZDetectorGoogle implements MRZDetector {
    @Inject
    public MRZDetectorGoogle() {
    }

    private final void addSuccessAndFailureListeners(Task<Text> task, final DocumentCodeValidator documentCodeValidator, final SingleEmitter<Boolean> singleEmitter, final TextRecognizer textRecognizer) {
        final Function1<Text, Unit> function1 = new Function1<Text, Unit>() { // from class: com.onfido.android.sdk.capture.detector.mrz.MRZDetectorGoogle.addSuccessAndFailureListeners.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Text text) {
                invoke2(text);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Text text) {
                DocumentCodeValidator documentCodeValidator2 = documentCodeValidator;
                String text2 = text.getText();
                Intrinsics.checkNotNullExpressionValue(text2, "getText(...)");
                singleEmitter.onSuccess(Boolean.valueOf(documentCodeValidator2.validate(text2).getSuccess()));
            }
        };
        task.addOnSuccessListener(new OnSuccessListener() { // from class: com.onfido.android.sdk.capture.detector.mrz.MRZDetectorGoogle$$ExternalSyntheticLambda1
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                MRZDetectorGoogle.addSuccessAndFailureListeners$lambda$1(function1, obj);
            }
        });
        task.addOnFailureListener(new OnFailureListener() { // from class: com.onfido.android.sdk.capture.detector.mrz.MRZDetectorGoogle$$ExternalSyntheticLambda2
            @Override // com.google.android.gms.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                MRZDetectorGoogle.addSuccessAndFailureListeners$lambda$2(singleEmitter, exc);
            }
        });
        task.addOnCompleteListener(new OnCompleteListener() { // from class: com.onfido.android.sdk.capture.detector.mrz.MRZDetectorGoogle$$ExternalSyntheticLambda3
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task2) {
                MRZDetectorGoogle.addSuccessAndFailureListeners$lambda$3(textRecognizer, task2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void addSuccessAndFailureListeners$lambda$1(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void addSuccessAndFailureListeners$lambda$2(SingleEmitter emitter, Exception it) {
        Intrinsics.checkNotNullParameter(emitter, "$emitter");
        Intrinsics.checkNotNullParameter(it, "it");
        emitter.onSuccess(Boolean.FALSE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void addSuccessAndFailureListeners$lambda$3(TextRecognizer textRecognizer, Task it) {
        Intrinsics.checkNotNullParameter(textRecognizer, "$textRecognizer");
        Intrinsics.checkNotNullParameter(it, "it");
        textRecognizer.close();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void detect$lambda$0(DocumentDetectionFrame frame, MRZDetectorGoogle this$0, DocumentCodeValidator validator, SingleEmitter emitter) {
        Intrinsics.checkNotNullParameter(frame, "$frame");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(validator, "$validator");
        Intrinsics.checkNotNullParameter(emitter, "emitter");
        TextRecognizer client = TextRecognition.getClient(new TextRecognizerOptions.Builder().build());
        Intrinsics.checkNotNullExpressionValue(client, "getClient(...)");
        Bitmap bitmapDecodeScaledResource$default = ImageUtils.decodeScaledResource$default(new ImageUtils(), frame.getYuv(), frame.getFrameWidth(), frame.getFrameWidth(), null, null, 24, null);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmapDecodeScaledResource$default, frame.getOuterFrame().getLeft(), frame.getOuterFrame().getTop(), frame.getOuterFrame().getWidth(), frame.getOuterFrame().getHeight());
        Intrinsics.checkNotNullExpressionValue(bitmapCreateBitmap, "createBitmap(...)");
        bitmapDecodeScaledResource$default.recycle();
        Task<Text> taskProcess = client.process(InputImage.fromBitmap(bitmapCreateBitmap, 0));
        Intrinsics.checkNotNullExpressionValue(taskProcess, "process(...)");
        this$0.addSuccessAndFailureListeners(taskProcess, validator, emitter, client);
    }

    @Override // com.onfido.android.sdk.capture.detector.mrz.MRZDetector
    public Single<Boolean> detect(final DocumentDetectionFrame frame, final DocumentCodeValidator validator) {
        Intrinsics.checkNotNullParameter(frame, "frame");
        Intrinsics.checkNotNullParameter(validator, "validator");
        Single<Boolean> singleCreate = Single.create(new SingleOnSubscribe() { // from class: com.onfido.android.sdk.capture.detector.mrz.MRZDetectorGoogle$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
            public final void subscribe(SingleEmitter singleEmitter) {
                MRZDetectorGoogle.detect$lambda$0(frame, this, validator, singleEmitter);
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleCreate, "create(...)");
        return singleCreate;
    }
}
