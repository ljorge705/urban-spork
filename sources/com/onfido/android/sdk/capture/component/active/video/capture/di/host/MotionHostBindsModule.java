package com.onfido.android.sdk.capture.component.active.video.capture.di.host;

import com.onfido.android.sdk.capture.common.result.FailureReason;
import com.onfido.android.sdk.capture.component.active.video.capture.data.repository.ActiveVideoCaptureRepositoryImpl;
import com.onfido.android.sdk.capture.component.active.video.capture.data.repository.mapper.ThrowableToErrorMessageMapper;
import com.onfido.android.sdk.capture.component.active.video.capture.domain.repository.ActiveVideoCaptureRepository;
import com.onfido.android.sdk.capture.utils.mapper.Mapper;
import com.onfido.dagger.Binds;
import com.onfido.dagger.Module;
import kotlin.Metadata;

@Module
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\ba\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H'J\u001c\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u00072\u0006\u0010\n\u001a\u00020\u000bH'¨\u0006\f"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/di/host/MotionHostBindsModule;", "", "provideActiveVideoCaptureRepository", "Lcom/onfido/android/sdk/capture/component/active/video/capture/domain/repository/ActiveVideoCaptureRepository;", "repo", "Lcom/onfido/android/sdk/capture/component/active/video/capture/data/repository/ActiveVideoCaptureRepositoryImpl;", "provideThrowableToErrorMessageMapper", "Lcom/onfido/android/sdk/capture/utils/mapper/Mapper;", "", "Lcom/onfido/android/sdk/capture/common/result/FailureReason;", "mapper", "Lcom/onfido/android/sdk/capture/component/active/video/capture/data/repository/mapper/ThrowableToErrorMessageMapper;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface MotionHostBindsModule {
    @Binds
    ActiveVideoCaptureRepository provideActiveVideoCaptureRepository(ActiveVideoCaptureRepositoryImpl repo);

    @Binds
    Mapper<Throwable, FailureReason> provideThrowableToErrorMessageMapper(ThrowableToErrorMessageMapper mapper);
}
