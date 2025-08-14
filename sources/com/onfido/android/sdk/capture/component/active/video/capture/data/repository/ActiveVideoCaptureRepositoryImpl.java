package com.onfido.android.sdk.capture.component.active.video.capture.data.repository;

import android.content.Context;
import com.onfido.android.sdk.capture.common.result.FailureReason;
import com.onfido.android.sdk.capture.component.active.video.capture.AVCConstants;
import com.onfido.android.sdk.capture.component.active.video.capture.data.remote.model.AVCUploadResponse;
import com.onfido.android.sdk.capture.component.active.video.capture.domain.model.ActiveVideoCaptureResult;
import com.onfido.android.sdk.capture.component.active.video.capture.domain.repository.ActiveVideoCaptureRepository;
import com.onfido.android.sdk.capture.internal.model.UploadedArtifactKt;
import com.onfido.android.sdk.capture.utils.mapper.Mapper;
import com.onfido.javax.inject.Inject;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Function;
import java.io.File;
import java.io.FilenameFilter;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0000\u0018\u00002\u00020\u0001B-\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005\u0012\b\b\u0001\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010\u000b\u001a\u00020\fH\u0016J\u001e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/data/repository/ActiveVideoCaptureRepositoryImpl;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/domain/repository/ActiveVideoCaptureRepository;", "motionDataSource", "Lcom/onfido/android/sdk/capture/component/active/video/capture/data/repository/MotionDataSource;", "errorMapper", "Lcom/onfido/android/sdk/capture/utils/mapper/Mapper;", "", "Lcom/onfido/android/sdk/capture/common/result/FailureReason;", "context", "Landroid/content/Context;", "(Lcom/onfido/android/sdk/capture/component/active/video/capture/data/repository/MotionDataSource;Lcom/onfido/android/sdk/capture/utils/mapper/Mapper;Landroid/content/Context;)V", "deleteVideoFiles", "Lio/reactivex/rxjava3/core/Completable;", "uploadActiveVideoCapture", "Lio/reactivex/rxjava3/core/Single;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/domain/model/ActiveVideoCaptureResult;", "videoFile", "Ljava/io/File;", "isAudioEnabled", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ActiveVideoCaptureRepositoryImpl implements ActiveVideoCaptureRepository {
    private final Context context;
    private final Mapper<Throwable, FailureReason> errorMapper;
    private final MotionDataSource motionDataSource;

    @Inject
    public ActiveVideoCaptureRepositoryImpl(MotionDataSource motionDataSource, Mapper<Throwable, FailureReason> errorMapper, Context context) {
        Intrinsics.checkNotNullParameter(motionDataSource, "motionDataSource");
        Intrinsics.checkNotNullParameter(errorMapper, "errorMapper");
        Intrinsics.checkNotNullParameter(context, "context");
        this.motionDataSource = motionDataSource;
        this.errorMapper = errorMapper;
        this.context = context;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void deleteVideoFiles$lambda$3(ActiveVideoCaptureRepositoryImpl this$0) {
        File[] fileArrListFiles;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        File cacheDir = this$0.context.getCacheDir();
        if (cacheDir == null || (fileArrListFiles = cacheDir.listFiles(new FilenameFilter() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.data.repository.ActiveVideoCaptureRepositoryImpl$$ExternalSyntheticLambda0
            @Override // java.io.FilenameFilter
            public final boolean accept(File file, String str) {
                return ActiveVideoCaptureRepositoryImpl.deleteVideoFiles$lambda$3$lambda$1(file, str);
            }
        })) == null) {
            return;
        }
        for (File file : fileArrListFiles) {
            file.delete();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean deleteVideoFiles$lambda$3$lambda$1(File file, String str) {
        Intrinsics.checkNotNull(str);
        return StringsKt.startsWith$default(str, AVCConstants.MOTION_RECORDING_FILE_PREFIX, false, 2, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ActiveVideoCaptureResult uploadActiveVideoCapture$lambda$0(ActiveVideoCaptureRepositoryImpl this$0, Throwable t) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(t, "t");
        return new ActiveVideoCaptureResult.Error(this$0.errorMapper.map(t));
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.domain.repository.ActiveVideoCaptureRepository
    public Completable deleteVideoFiles() {
        Completable completableFromAction = Completable.fromAction(new Action() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.data.repository.ActiveVideoCaptureRepositoryImpl$$ExternalSyntheticLambda1
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                ActiveVideoCaptureRepositoryImpl.deleteVideoFiles$lambda$3(this.f$0);
            }
        });
        Intrinsics.checkNotNullExpressionValue(completableFromAction, "fromAction(...)");
        return completableFromAction;
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.domain.repository.ActiveVideoCaptureRepository
    public Single<ActiveVideoCaptureResult> uploadActiveVideoCapture(File videoFile, boolean isAudioEnabled) {
        Intrinsics.checkNotNullParameter(videoFile, "videoFile");
        Single<ActiveVideoCaptureResult> singleOnErrorReturn = this.motionDataSource.uploadMotionCapture(videoFile, isAudioEnabled).map(new Function() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.data.repository.ActiveVideoCaptureRepositoryImpl.uploadActiveVideoCapture.1
            @Override // io.reactivex.rxjava3.functions.Function
            public final ActiveVideoCaptureResult apply(AVCUploadResponse avcUploadResponse) {
                Intrinsics.checkNotNullParameter(avcUploadResponse, "avcUploadResponse");
                return new ActiveVideoCaptureResult.Success(UploadedArtifactKt.toUploadedArtifact(avcUploadResponse));
            }
        }).onErrorReturn(new Function() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.data.repository.ActiveVideoCaptureRepositoryImpl$$ExternalSyntheticLambda2
            @Override // io.reactivex.rxjava3.functions.Function
            public final Object apply(Object obj) {
                return ActiveVideoCaptureRepositoryImpl.uploadActiveVideoCapture$lambda$0(this.f$0, (Throwable) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleOnErrorReturn, "onErrorReturn(...)");
        return singleOnErrorReturn;
    }
}
