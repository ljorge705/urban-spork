package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.model;

import kotlin.Metadata;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bp\u0018\u00002\u00020\u0001:\n\u0002\u0003\u0004\u0005\u0006\u0007\b\t\n\u000b\u0082\u0001\n\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015¨\u0006\u0016"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/model/FaceAlignmentFeedback;", "", "FaceAligned", "FaceNotCentered", "FaceNotDetected", "Idle", "MoveBack", "MoveCloser", "MoveDeviceDown", "MoveDeviceLeft", "MoveDeviceRight", "MoveDeviceUp", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/model/FaceAlignmentFeedback$FaceAligned;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/model/FaceAlignmentFeedback$FaceNotCentered;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/model/FaceAlignmentFeedback$FaceNotDetected;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/model/FaceAlignmentFeedback$Idle;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/model/FaceAlignmentFeedback$MoveBack;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/model/FaceAlignmentFeedback$MoveCloser;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/model/FaceAlignmentFeedback$MoveDeviceDown;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/model/FaceAlignmentFeedback$MoveDeviceLeft;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/model/FaceAlignmentFeedback$MoveDeviceRight;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/model/FaceAlignmentFeedback$MoveDeviceUp;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface FaceAlignmentFeedback {

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/model/FaceAlignmentFeedback$FaceAligned;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/model/FaceAlignmentFeedback;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class FaceAligned implements FaceAlignmentFeedback {
        public static final FaceAligned INSTANCE = new FaceAligned();

        private FaceAligned() {
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/model/FaceAlignmentFeedback$FaceNotCentered;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/model/FaceAlignmentFeedback;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class FaceNotCentered implements FaceAlignmentFeedback {
        public static final FaceNotCentered INSTANCE = new FaceNotCentered();

        private FaceNotCentered() {
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/model/FaceAlignmentFeedback$FaceNotDetected;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/model/FaceAlignmentFeedback;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class FaceNotDetected implements FaceAlignmentFeedback {
        public static final FaceNotDetected INSTANCE = new FaceNotDetected();

        private FaceNotDetected() {
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/model/FaceAlignmentFeedback$Idle;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/model/FaceAlignmentFeedback;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Idle implements FaceAlignmentFeedback {
        public static final Idle INSTANCE = new Idle();

        private Idle() {
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/model/FaceAlignmentFeedback$MoveBack;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/model/FaceAlignmentFeedback;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class MoveBack implements FaceAlignmentFeedback {
        public static final MoveBack INSTANCE = new MoveBack();

        private MoveBack() {
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/model/FaceAlignmentFeedback$MoveCloser;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/model/FaceAlignmentFeedback;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class MoveCloser implements FaceAlignmentFeedback {
        public static final MoveCloser INSTANCE = new MoveCloser();

        private MoveCloser() {
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/model/FaceAlignmentFeedback$MoveDeviceDown;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/model/FaceAlignmentFeedback;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class MoveDeviceDown implements FaceAlignmentFeedback {
        public static final MoveDeviceDown INSTANCE = new MoveDeviceDown();

        private MoveDeviceDown() {
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/model/FaceAlignmentFeedback$MoveDeviceLeft;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/model/FaceAlignmentFeedback;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class MoveDeviceLeft implements FaceAlignmentFeedback {
        public static final MoveDeviceLeft INSTANCE = new MoveDeviceLeft();

        private MoveDeviceLeft() {
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/model/FaceAlignmentFeedback$MoveDeviceRight;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/model/FaceAlignmentFeedback;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class MoveDeviceRight implements FaceAlignmentFeedback {
        public static final MoveDeviceRight INSTANCE = new MoveDeviceRight();

        private MoveDeviceRight() {
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/model/FaceAlignmentFeedback$MoveDeviceUp;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/model/FaceAlignmentFeedback;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class MoveDeviceUp implements FaceAlignmentFeedback {
        public static final MoveDeviceUp INSTANCE = new MoveDeviceUp();

        private MoveDeviceUp() {
        }
    }
}
