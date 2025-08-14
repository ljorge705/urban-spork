package com.onfido.android.sdk.capture.component.active.video.capture.data.repository;

import com.onfido.android.sdk.capture.component.active.video.capture.data.remote.model.AVCUploadResponse;
import io.reactivex.rxjava3.core.Single;
import java.io.File;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b`\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH&¨\u0006\t"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/data/repository/MotionDataSource;", "", "uploadMotionCapture", "Lio/reactivex/rxjava3/core/Single;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/data/remote/model/AVCUploadResponse;", "videoFile", "Ljava/io/File;", "isAudioEnabled", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface MotionDataSource {
    Single<AVCUploadResponse> uploadMotionCapture(File videoFile, boolean isAudioEnabled);
}
