package com.onfido.android.sdk.capture.ui.camera;

import com.facebook.react.uimanager.ViewProps;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\bà\u0080\u0001\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007H&¨\u0006\n"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/FrameCallback;", "", "onNextFrame", "", "data", "", "width", "", "height", ViewProps.ROTATION, "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface FrameCallback {
    void onNextFrame(byte[] data, int width, int height, int rotation);
}
