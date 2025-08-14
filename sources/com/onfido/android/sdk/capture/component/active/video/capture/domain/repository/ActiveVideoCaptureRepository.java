package com.onfido.android.sdk.capture.component.active.video.capture.domain.repository;

import com.onfido.android.sdk.capture.component.active.video.capture.domain.model.ActiveVideoCaptureResult;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import java.io.File;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b`\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u001e\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH&¨\u0006\u000b"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/domain/repository/ActiveVideoCaptureRepository;", "", "deleteVideoFiles", "Lio/reactivex/rxjava3/core/Completable;", "uploadActiveVideoCapture", "Lio/reactivex/rxjava3/core/Single;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/domain/model/ActiveVideoCaptureResult;", "videoFile", "Ljava/io/File;", "isAudioEnabled", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface ActiveVideoCaptureRepository {
    Completable deleteVideoFiles();

    Single<ActiveVideoCaptureResult> uploadActiveVideoCapture(File videoFile, boolean isAudioEnabled);
}
