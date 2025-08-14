package com.onfido.android.sdk.capture.component.active.video.capture.presentation.host;

import com.onfido.android.sdk.capture.internal.navigation.Screen;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bp\u0018\u00002\u00020\u0001\u0082\u0001\u0006\u0002\u0003\u0004\u0005\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/host/AvcScreen;", "Lcom/onfido/android/sdk/capture/internal/navigation/Screen;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/host/AvcUploadErrorScreen;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/host/MotionCaptureScreen;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/host/MotionIntroScreen;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/host/MotionNoFaceDetectedScreen;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/host/MotionUploadScreen;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/host/PermissionsScreen;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface AvcScreen extends Screen {

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class DefaultImpls {
        public static String screenKey(AvcScreen avcScreen) {
            return Screen.DefaultImpls.screenKey(avcScreen);
        }
    }
}
