package com.onfido.android.sdk.capture.component.active.video.capture.presentation.upload;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.common.result.FailureReason;
import com.onfido.android.sdk.capture.component.active.video.capture.analytics.AvcAnalyticsEvent;
import com.onfido.android.sdk.capture.component.active.video.capture.domain.model.ActiveVideoCaptureResult;
import com.onfido.android.sdk.capture.component.active.video.capture.domain.usecase.UploadActiveVideoCaptureUseCase;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.OnfidoAnalytics;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.WaitingScreenTracker;
import com.onfido.android.sdk.capture.internal.model.UploadedArtifact;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.android.sdk.capture.utils.LoadingFragment;
import com.onfido.android.sdk.capture.utils.RxExtensionsKt;
import com.onfido.javax.inject.Inject;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import java.io.File;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001:\u0001\u001bB'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010\u0014\u001a\u00020\u0015H\u0014J\u0016\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aR\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\r0\u00118F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/upload/MotionUploadViewModel;", "Landroidx/lifecycle/ViewModel;", "uploadActiveVideoCapture", "Lcom/onfido/android/sdk/capture/component/active/video/capture/domain/usecase/UploadActiveVideoCaptureUseCase;", "schedulersProvider", "Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;", "analytics", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/OnfidoAnalytics;", "waitingScreenTracker", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/WaitingScreenTracker;", "(Lcom/onfido/android/sdk/capture/component/active/video/capture/domain/usecase/UploadActiveVideoCaptureUseCase;Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/OnfidoAnalytics;Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/WaitingScreenTracker;)V", "_uploadResult", "Landroidx/lifecycle/MutableLiveData;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/upload/MotionUploadViewModel$UploadResult;", "compositeDisposable", "Lio/reactivex/rxjava3/disposables/CompositeDisposable;", "uploadResult", "Landroidx/lifecycle/LiveData;", "getUploadResult", "()Landroidx/lifecycle/LiveData;", "onCleared", "", "upload", "videoFile", "Ljava/io/File;", "isAudioEnabled", "", "UploadResult", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class MotionUploadViewModel extends ViewModel {
    private final MutableLiveData<UploadResult> _uploadResult;
    private final OnfidoAnalytics analytics;
    private final CompositeDisposable compositeDisposable;
    private final SchedulersProvider schedulersProvider;
    private final UploadActiveVideoCaptureUseCase uploadActiveVideoCapture;
    private final WaitingScreenTracker waitingScreenTracker;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0003\u0002\u0003\u0004\u0082\u0001\u0003\u0005\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/upload/MotionUploadViewModel$UploadResult;", "", "Failure", "Initial", "Success", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/upload/MotionUploadViewModel$UploadResult$Failure;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/upload/MotionUploadViewModel$UploadResult$Initial;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/upload/MotionUploadViewModel$UploadResult$Success;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface UploadResult {

        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/upload/MotionUploadViewModel$UploadResult$Failure;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/upload/MotionUploadViewModel$UploadResult;", "reason", "Lcom/onfido/android/sdk/capture/common/result/FailureReason;", "(Lcom/onfido/android/sdk/capture/common/result/FailureReason;)V", "getReason", "()Lcom/onfido/android/sdk/capture/common/result/FailureReason;", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class Failure implements UploadResult {
            private final FailureReason reason;

            public Failure(FailureReason reason) {
                Intrinsics.checkNotNullParameter(reason, "reason");
                this.reason = reason;
            }

            public static /* synthetic */ Failure copy$default(Failure failure, FailureReason failureReason, int i, Object obj) {
                if ((i & 1) != 0) {
                    failureReason = failure.reason;
                }
                return failure.copy(failureReason);
            }

            /* renamed from: component1, reason: from getter */
            public final FailureReason getReason() {
                return this.reason;
            }

            public final Failure copy(FailureReason reason) {
                Intrinsics.checkNotNullParameter(reason, "reason");
                return new Failure(reason);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof Failure) && Intrinsics.areEqual(this.reason, ((Failure) other).reason);
            }

            public final FailureReason getReason() {
                return this.reason;
            }

            public int hashCode() {
                return this.reason.hashCode();
            }

            public String toString() {
                return "Failure(reason=" + this.reason + ')';
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/upload/MotionUploadViewModel$UploadResult$Initial;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/upload/MotionUploadViewModel$UploadResult;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Initial implements UploadResult {
            public static final Initial INSTANCE = new Initial();

            private Initial() {
            }
        }

        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/upload/MotionUploadViewModel$UploadResult$Success;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/upload/MotionUploadViewModel$UploadResult;", "uploadArtifact", "Lcom/onfido/android/sdk/capture/internal/model/UploadedArtifact;", "(Lcom/onfido/android/sdk/capture/internal/model/UploadedArtifact;)V", "getUploadArtifact", "()Lcom/onfido/android/sdk/capture/internal/model/UploadedArtifact;", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class Success implements UploadResult {
            private final UploadedArtifact uploadArtifact;

            public Success(UploadedArtifact uploadArtifact) {
                Intrinsics.checkNotNullParameter(uploadArtifact, "uploadArtifact");
                this.uploadArtifact = uploadArtifact;
            }

            public static /* synthetic */ Success copy$default(Success success, UploadedArtifact uploadedArtifact, int i, Object obj) {
                if ((i & 1) != 0) {
                    uploadedArtifact = success.uploadArtifact;
                }
                return success.copy(uploadedArtifact);
            }

            /* renamed from: component1, reason: from getter */
            public final UploadedArtifact getUploadArtifact() {
                return this.uploadArtifact;
            }

            public final Success copy(UploadedArtifact uploadArtifact) {
                Intrinsics.checkNotNullParameter(uploadArtifact, "uploadArtifact");
                return new Success(uploadArtifact);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof Success) && Intrinsics.areEqual(this.uploadArtifact, ((Success) other).uploadArtifact);
            }

            public final UploadedArtifact getUploadArtifact() {
                return this.uploadArtifact;
            }

            public int hashCode() {
                return this.uploadArtifact.hashCode();
            }

            public String toString() {
                return "Success(uploadArtifact=" + this.uploadArtifact + ')';
            }
        }
    }

    @Inject
    public MotionUploadViewModel(UploadActiveVideoCaptureUseCase uploadActiveVideoCapture, SchedulersProvider schedulersProvider, OnfidoAnalytics analytics, WaitingScreenTracker waitingScreenTracker) {
        Intrinsics.checkNotNullParameter(uploadActiveVideoCapture, "uploadActiveVideoCapture");
        Intrinsics.checkNotNullParameter(schedulersProvider, "schedulersProvider");
        Intrinsics.checkNotNullParameter(analytics, "analytics");
        Intrinsics.checkNotNullParameter(waitingScreenTracker, "waitingScreenTracker");
        this.uploadActiveVideoCapture = uploadActiveVideoCapture;
        this.schedulersProvider = schedulersProvider;
        this.analytics = analytics;
        this.waitingScreenTracker = waitingScreenTracker;
        this._uploadResult = new MutableLiveData<>(UploadResult.Initial.INSTANCE);
        this.compositeDisposable = new CompositeDisposable();
    }

    public final LiveData<UploadResult> getUploadResult() {
        return this._uploadResult;
    }

    @Override // androidx.lifecycle.ViewModel
    protected void onCleared() {
        super.onCleared();
        this.compositeDisposable.dispose();
    }

    public final void upload(File videoFile, boolean isAudioEnabled) {
        Intrinsics.checkNotNullParameter(videoFile, "videoFile");
        this.analytics.track(AvcAnalyticsEvent.Upload.INSTANCE);
        CompositeDisposable compositeDisposable = this.compositeDisposable;
        Disposable disposableSubscribe = this.uploadActiveVideoCapture.invoke(videoFile, isAudioEnabled).subscribeOn(this.schedulersProvider.getIo()).observeOn(this.schedulersProvider.getUi()).subscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.upload.MotionUploadViewModel.upload.1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(ActiveVideoCaptureResult result) {
                UploadResult failure;
                Intrinsics.checkNotNullParameter(result, "result");
                if (result instanceof ActiveVideoCaptureResult.Success) {
                    MotionUploadViewModel.this.analytics.track(AvcAnalyticsEvent.UploadCompleted.INSTANCE);
                    LoadingFragment.Companion.DialogMode.Uploading uploading = new LoadingFragment.Companion.DialogMode.Uploading(WaitingScreenTracker.ClassicFlowWaitingReason.REASON_UPLOADING_FACE_MOTION);
                    MotionUploadViewModel.this.waitingScreenTracker.trackWaitingScreenCompletion(uploading.toTaskType(), uploading.getReason());
                    failure = new UploadResult.Success(((ActiveVideoCaptureResult.Success) result).getUploadedArtifact());
                } else {
                    if (!(result instanceof ActiveVideoCaptureResult.Error)) {
                        throw new NoWhenBranchMatchedException();
                    }
                    failure = new UploadResult.Failure(((ActiveVideoCaptureResult.Error) result).getReason());
                }
                MotionUploadViewModel.this._uploadResult.postValue(failure);
            }
        }, new Consumer() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.upload.MotionUploadViewModel.upload.2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable it) {
                Intrinsics.checkNotNullParameter(it, "it");
                MotionUploadViewModel.this._uploadResult.postValue(new UploadResult.Failure(new FailureReason(it, "HTTP FAILED Exception")));
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        RxExtensionsKt.plusAssign(compositeDisposable, disposableSubscribe);
    }
}
