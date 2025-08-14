package com.onfido.android.sdk.capture.component.active.video.capture.domain.usecase;

import com.google.android.gms.common.moduleinstall.ModuleAvailabilityResponse;
import com.google.android.gms.common.moduleinstall.ModuleInstallClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.face.FaceDetector;
import com.onfido.android.sdk.capture.component.active.video.capture.domain.usecase.GetFaceDetectionModuleAvailabilityUseCase;
import com.onfido.android.sdk.capture.internal.util.logging.Timber;
import com.onfido.javax.inject.Inject;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u0000 \n2\u00020\u0001:\u0002\n\u000bB\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0096\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0092\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0092\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/domain/usecase/GetFaceDetectionModuleAvailabilityUseCase;", "", "moduleInstallClient", "Lcom/google/android/gms/common/moduleinstall/ModuleInstallClient;", "mlKitFaceDetector", "Lcom/google/mlkit/vision/face/FaceDetector;", "(Lcom/google/android/gms/common/moduleinstall/ModuleInstallClient;Lcom/google/mlkit/vision/face/FaceDetector;)V", "invoke", "Lio/reactivex/rxjava3/core/Single;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/domain/usecase/GetFaceDetectionModuleAvailabilityUseCase$Result;", "Companion", "Result", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public class GetFaceDetectionModuleAvailabilityUseCase {
    public static final String ERROR_MESSAGE_ML_KIT = "Face Detection module is not available on the device";
    private final FaceDetector mlKitFaceDetector;
    private final ModuleInstallClient moduleInstallClient;

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006¨\u0006\u0007"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/domain/usecase/GetFaceDetectionModuleAvailabilityUseCase$Result;", "", "()V", "Available", "Missing", "Lcom/onfido/android/sdk/capture/component/active/video/capture/domain/usecase/GetFaceDetectionModuleAvailabilityUseCase$Result$Available;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/domain/usecase/GetFaceDetectionModuleAvailabilityUseCase$Result$Missing;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static abstract class Result {

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/domain/usecase/GetFaceDetectionModuleAvailabilityUseCase$Result$Available;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/domain/usecase/GetFaceDetectionModuleAvailabilityUseCase$Result;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Available extends Result {
            public static final Available INSTANCE = new Available();

            private Available() {
                super(null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/domain/usecase/GetFaceDetectionModuleAvailabilityUseCase$Result$Missing;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/domain/usecase/GetFaceDetectionModuleAvailabilityUseCase$Result;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Missing extends Result {
            public static final Missing INSTANCE = new Missing();

            private Missing() {
                super(null);
            }
        }

        private Result() {
        }

        public /* synthetic */ Result(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Inject
    public GetFaceDetectionModuleAvailabilityUseCase(ModuleInstallClient moduleInstallClient, FaceDetector mlKitFaceDetector) {
        Intrinsics.checkNotNullParameter(moduleInstallClient, "moduleInstallClient");
        Intrinsics.checkNotNullParameter(mlKitFaceDetector, "mlKitFaceDetector");
        this.moduleInstallClient = moduleInstallClient;
        this.mlKitFaceDetector = mlKitFaceDetector;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invoke$lambda$2(GetFaceDetectionModuleAvailabilityUseCase this$0, final SingleEmitter emitter) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(emitter, "emitter");
        Task<ModuleAvailabilityResponse> taskAreModulesAvailable = this$0.moduleInstallClient.areModulesAvailable(this$0.mlKitFaceDetector);
        final Function1<ModuleAvailabilityResponse, Unit> function1 = new Function1<ModuleAvailabilityResponse, Unit>() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.domain.usecase.GetFaceDetectionModuleAvailabilityUseCase$invoke$1$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ModuleAvailabilityResponse moduleAvailabilityResponse) {
                invoke2(moduleAvailabilityResponse);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ModuleAvailabilityResponse moduleAvailabilityResponse) {
                boolean zAreModulesAvailable = moduleAvailabilityResponse.areModulesAvailable();
                Timber.INSTANCE.tag("MLKitFaceDetection").d("Face Detection Module is available on the device: " + zAreModulesAvailable, new Object[0]);
                GetFaceDetectionModuleAvailabilityUseCase.Result result = zAreModulesAvailable ? GetFaceDetectionModuleAvailabilityUseCase.Result.Available.INSTANCE : GetFaceDetectionModuleAvailabilityUseCase.Result.Missing.INSTANCE;
                if (emitter.isDisposed()) {
                    return;
                }
                emitter.onSuccess(result);
            }
        };
        taskAreModulesAvailable.addOnSuccessListener(new OnSuccessListener() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.domain.usecase.GetFaceDetectionModuleAvailabilityUseCase$$ExternalSyntheticLambda0
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                GetFaceDetectionModuleAvailabilityUseCase.invoke$lambda$2$lambda$0(function1, obj);
            }
        }).addOnFailureListener(new OnFailureListener() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.domain.usecase.GetFaceDetectionModuleAvailabilityUseCase$$ExternalSyntheticLambda1
            @Override // com.google.android.gms.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                GetFaceDetectionModuleAvailabilityUseCase.invoke$lambda$2$lambda$1(emitter, exc);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invoke$lambda$2$lambda$0(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invoke$lambda$2$lambda$1(SingleEmitter emitter, Exception it) {
        Intrinsics.checkNotNullParameter(emitter, "$emitter");
        Intrinsics.checkNotNullParameter(it, "it");
        Timber.INSTANCE.tag("MLKitFaceDetection").e("Face Detection Module is not available on the device. " + it, new Object[0]);
        if (emitter.isDisposed()) {
            return;
        }
        emitter.onSuccess(Result.Missing.INSTANCE);
    }

    public Single<Result> invoke() {
        Single<Result> singleCreate = Single.create(new SingleOnSubscribe() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.domain.usecase.GetFaceDetectionModuleAvailabilityUseCase$$ExternalSyntheticLambda2
            @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
            public final void subscribe(SingleEmitter singleEmitter) {
                GetFaceDetectionModuleAvailabilityUseCase.invoke$lambda$2(this.f$0, singleEmitter);
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleCreate, "create(...)");
        return singleCreate;
    }
}
