package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.recorder.persistentsurface;

import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.utils.RawResourceReader;
import com.onfido.javax.inject.Inject;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/camera2/recorder/persistentsurface/LocalPersistentRecorderSurfaceRepository;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/camera2/recorder/persistentsurface/PersistentRecorderSurfaceRepository;", "rawResourceReader", "Lcom/onfido/android/sdk/capture/utils/RawResourceReader;", "(Lcom/onfido/android/sdk/capture/utils/RawResourceReader;)V", "getUnsupportedDeviceList", "", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class LocalPersistentRecorderSurfaceRepository implements PersistentRecorderSurfaceRepository {
    private final RawResourceReader rawResourceReader;

    @Inject
    public LocalPersistentRecorderSurfaceRepository(RawResourceReader rawResourceReader) {
        Intrinsics.checkNotNullParameter(rawResourceReader, "rawResourceReader");
        this.rawResourceReader = rawResourceReader;
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.recorder.persistentsurface.PersistentRecorderSurfaceRepository
    public List<String> getUnsupportedDeviceList() {
        return StringsKt.split$default((CharSequence) this.rawResourceReader.read(R.raw.onfido_avc_persistent_recorder_surface_unsupported_devices), new String[]{Constants.SEPARATOR_COMMA}, false, 0, 6, (Object) null);
    }
}
