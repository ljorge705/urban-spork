package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.recorder;

import android.content.Context;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.recorder.persistentsurface.IsPersistentSurfaceSupportedUseCase;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.util.SurfaceSizeProvider;
import com.onfido.javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B!\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/camera2/recorder/RecorderWrapperFactory;", "", "context", "Landroid/content/Context;", "isPersistentSurfaceSupportedUseCase", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/camera2/recorder/persistentsurface/IsPersistentSurfaceSupportedUseCase;", "surfaceSizeProvider", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/camera2/util/SurfaceSizeProvider;", "(Landroid/content/Context;Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/camera2/recorder/persistentsurface/IsPersistentSurfaceSupportedUseCase;Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/camera2/util/SurfaceSizeProvider;)V", "create", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/camera2/recorder/RecorderWrapper;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class RecorderWrapperFactory {
    private final Context context;
    private final IsPersistentSurfaceSupportedUseCase isPersistentSurfaceSupportedUseCase;
    private final SurfaceSizeProvider surfaceSizeProvider;

    @Inject
    public RecorderWrapperFactory(Context context, IsPersistentSurfaceSupportedUseCase isPersistentSurfaceSupportedUseCase, SurfaceSizeProvider surfaceSizeProvider) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(isPersistentSurfaceSupportedUseCase, "isPersistentSurfaceSupportedUseCase");
        Intrinsics.checkNotNullParameter(surfaceSizeProvider, "surfaceSizeProvider");
        this.context = context;
        this.isPersistentSurfaceSupportedUseCase = isPersistentSurfaceSupportedUseCase;
        this.surfaceSizeProvider = surfaceSizeProvider;
    }

    public final RecorderWrapper create() {
        return this.isPersistentSurfaceSupportedUseCase.invoke() ? new MarshmallowRecorderWrapper(this.context, this.surfaceSizeProvider) : new LollipopRecorderWrapper(this.context, this.surfaceSizeProvider);
    }
}
