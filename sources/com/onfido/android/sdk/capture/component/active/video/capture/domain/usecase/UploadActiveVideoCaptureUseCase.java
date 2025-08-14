package com.onfido.android.sdk.capture.component.active.video.capture.domain.usecase;

import android.accounts.NetworkErrorException;
import com.onfido.android.sdk.capture.component.active.video.capture.domain.model.ActiveVideoCaptureResult;
import com.onfido.android.sdk.capture.component.active.video.capture.domain.repository.ActiveVideoCaptureRepository;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.android.sdk.capture.utils.RxExtensionsKt;
import com.onfido.javax.inject.Inject;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.functions.Function;
import java.io.File;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0000\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0086\u0002J\u0018\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\t0\b*\b\u0012\u0004\u0012\u00020\t0\bH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/domain/usecase/UploadActiveVideoCaptureUseCase;", "", "repository", "Lcom/onfido/android/sdk/capture/component/active/video/capture/domain/repository/ActiveVideoCaptureRepository;", "schedulersProvider", "Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;", "(Lcom/onfido/android/sdk/capture/component/active/video/capture/domain/repository/ActiveVideoCaptureRepository;Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;)V", "invoke", "Lio/reactivex/rxjava3/core/Single;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/domain/model/ActiveVideoCaptureResult;", "videoFile", "Ljava/io/File;", "isAudioEnabled", "", "retryAfterDelay", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class UploadActiveVideoCaptureUseCase {
    public static final long RETRY_COUNT = 3;
    private static final long RETRY_DELAY_MS = 1500;
    private final ActiveVideoCaptureRepository repository;
    private final SchedulersProvider schedulersProvider;

    @Inject
    public UploadActiveVideoCaptureUseCase(ActiveVideoCaptureRepository repository, SchedulersProvider schedulersProvider) {
        Intrinsics.checkNotNullParameter(repository, "repository");
        Intrinsics.checkNotNullParameter(schedulersProvider, "schedulersProvider");
        this.repository = repository;
        this.schedulersProvider = schedulersProvider;
    }

    private final Single<ActiveVideoCaptureResult> retryAfterDelay(Single<ActiveVideoCaptureResult> single) {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        Single<R> singleFlatMap = single.flatMap(new Function() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.domain.usecase.UploadActiveVideoCaptureUseCase.retryAfterDelay.1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // io.reactivex.rxjava3.functions.Function
            public final SingleSource<? extends ActiveVideoCaptureResult> apply(ActiveVideoCaptureResult result) {
                Single<T> singleError;
                Intrinsics.checkNotNullParameter(result, "result");
                if (result instanceof ActiveVideoCaptureResult.Success) {
                    singleError = UploadActiveVideoCaptureUseCase.this.repository.deleteVideoFiles().andThen(Single.just(result));
                } else {
                    if (!(result instanceof ActiveVideoCaptureResult.Error)) {
                        throw new NoWhenBranchMatchedException();
                    }
                    objectRef.element = result;
                    singleError = Single.error(new NetworkErrorException("Thrown to trigger retryWithDelay(). Will not pass downstream"));
                }
                Intrinsics.checkNotNull(singleError);
                return singleError;
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleFlatMap, "flatMap(...)");
        Single<ActiveVideoCaptureResult> singleOnErrorReturn = RxExtensionsKt.retryWithDelay$default(singleFlatMap, 3L, 1500L, null, this.schedulersProvider.getTimer(), 4, null).onErrorReturn(new Function() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.domain.usecase.UploadActiveVideoCaptureUseCase$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.functions.Function
            public final Object apply(Object obj) {
                return UploadActiveVideoCaptureUseCase.retryAfterDelay$lambda$0(objectRef, (Throwable) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleOnErrorReturn, "onErrorReturn(...)");
        return singleOnErrorReturn;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ActiveVideoCaptureResult retryAfterDelay$lambda$0(Ref.ObjectRef error, Throwable it) {
        Intrinsics.checkNotNullParameter(error, "$error");
        Intrinsics.checkNotNullParameter(it, "it");
        return (ActiveVideoCaptureResult) error.element;
    }

    public final Single<ActiveVideoCaptureResult> invoke(File videoFile, boolean isAudioEnabled) {
        Intrinsics.checkNotNullParameter(videoFile, "videoFile");
        return retryAfterDelay(this.repository.uploadActiveVideoCapture(videoFile, isAudioEnabled));
    }
}
