package com.onfido.android.sdk.capture.ui.camera.document;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bp\u0018\u00002\u00020\u0001:\u0003\u0002\u0003\u0004\u0082\u0001\u0003\u0005\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/document/CameraState;", "", "OFF", "ON", "Uninitialized", "Lcom/onfido/android/sdk/capture/ui/camera/document/CameraState$OFF;", "Lcom/onfido/android/sdk/capture/ui/camera/document/CameraState$ON;", "Lcom/onfido/android/sdk/capture/ui/camera/document/CameraState$Uninitialized;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface CameraState {

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/document/CameraState$OFF;", "Lcom/onfido/android/sdk/capture/ui/camera/document/CameraState;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class OFF implements CameraState {
        public static final OFF INSTANCE = new OFF();

        private OFF() {
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/document/CameraState$ON;", "Lcom/onfido/android/sdk/capture/ui/camera/document/CameraState;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class ON implements CameraState {
        public static final ON INSTANCE = new ON();

        private ON() {
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/document/CameraState$Uninitialized;", "Lcom/onfido/android/sdk/capture/ui/camera/document/CameraState;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Uninitialized implements CameraState {
        public static final Uninitialized INSTANCE = new Uninitialized();

        private Uninitialized() {
        }
    }
}
