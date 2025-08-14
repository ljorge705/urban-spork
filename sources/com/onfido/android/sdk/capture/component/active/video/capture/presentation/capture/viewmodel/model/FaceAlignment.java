package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.model;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bp\u0018\u00002\u00020\u0001:\u0002\u0002\u0003\u0082\u0001\u0002\u0004\u0005¨\u0006\u0006"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/model/FaceAlignment;", "", "FaceCenterIsAligned", "FaceCenterIsNotAligned", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/model/FaceAlignment$FaceCenterIsAligned;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/model/FaceAlignment$FaceCenterIsNotAligned;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface FaceAlignment {

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0003\u0002\u0003\u0004\u0082\u0001\u0003\u0005\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/model/FaceAlignment$FaceCenterIsAligned;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/model/FaceAlignment;", "FaceIsAligned", "FaceIsTooBig", "FaceIsTooSmall", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/model/FaceAlignment$FaceCenterIsAligned$FaceIsAligned;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/model/FaceAlignment$FaceCenterIsAligned$FaceIsTooBig;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/model/FaceAlignment$FaceCenterIsAligned$FaceIsTooSmall;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface FaceCenterIsAligned extends FaceAlignment {

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/model/FaceAlignment$FaceCenterIsAligned$FaceIsAligned;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/model/FaceAlignment$FaceCenterIsAligned;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class FaceIsAligned implements FaceCenterIsAligned {
            public static final FaceIsAligned INSTANCE = new FaceIsAligned();

            private FaceIsAligned() {
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/model/FaceAlignment$FaceCenterIsAligned$FaceIsTooBig;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/model/FaceAlignment$FaceCenterIsAligned;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class FaceIsTooBig implements FaceCenterIsAligned {
            public static final FaceIsTooBig INSTANCE = new FaceIsTooBig();

            private FaceIsTooBig() {
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/model/FaceAlignment$FaceCenterIsAligned$FaceIsTooSmall;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/model/FaceAlignment$FaceCenterIsAligned;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class FaceIsTooSmall implements FaceCenterIsAligned {
            public static final FaceIsTooSmall INSTANCE = new FaceIsTooSmall();

            private FaceIsTooSmall() {
            }
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0004\u0002\u0003\u0004\u0005\u0082\u0001\u0004\u0006\u0007\b\t¨\u0006\n"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/model/FaceAlignment$FaceCenterIsNotAligned;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/model/FaceAlignment;", "FaceIsTooDown", "FaceIsTooLeft", "FaceIsTooRight", "FaceIsTooUp", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/model/FaceAlignment$FaceCenterIsNotAligned$FaceIsTooDown;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/model/FaceAlignment$FaceCenterIsNotAligned$FaceIsTooLeft;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/model/FaceAlignment$FaceCenterIsNotAligned$FaceIsTooRight;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/model/FaceAlignment$FaceCenterIsNotAligned$FaceIsTooUp;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface FaceCenterIsNotAligned extends FaceAlignment {

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/model/FaceAlignment$FaceCenterIsNotAligned$FaceIsTooDown;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/model/FaceAlignment$FaceCenterIsNotAligned;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class FaceIsTooDown implements FaceCenterIsNotAligned {
            public static final FaceIsTooDown INSTANCE = new FaceIsTooDown();

            private FaceIsTooDown() {
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/model/FaceAlignment$FaceCenterIsNotAligned$FaceIsTooLeft;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/model/FaceAlignment$FaceCenterIsNotAligned;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class FaceIsTooLeft implements FaceCenterIsNotAligned {
            public static final FaceIsTooLeft INSTANCE = new FaceIsTooLeft();

            private FaceIsTooLeft() {
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/model/FaceAlignment$FaceCenterIsNotAligned$FaceIsTooRight;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/model/FaceAlignment$FaceCenterIsNotAligned;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class FaceIsTooRight implements FaceCenterIsNotAligned {
            public static final FaceIsTooRight INSTANCE = new FaceIsTooRight();

            private FaceIsTooRight() {
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/model/FaceAlignment$FaceCenterIsNotAligned$FaceIsTooUp;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/model/FaceAlignment$FaceCenterIsNotAligned;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class FaceIsTooUp implements FaceCenterIsNotAligned {
            public static final FaceIsTooUp INSTANCE = new FaceIsTooUp();

            private FaceIsTooUp() {
            }
        }
    }
}
