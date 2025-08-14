package com.onfido.android.sdk.capture.internal.camera;

import android.graphics.RectF;
import androidx.media3.common.MimeTypes;
import com.clevertap.android.sdk.Constants;
import com.facebook.react.uimanager.ViewProps;
import com.oblador.keychain.KeychainModule;
import com.onfido.android.sdk.capture.ui.camera.capture.PhotoCaptureConfig;
import io.reactivex.rxjava3.core.Observable;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b`\u0018\u00002\u00020\u0001:\u0006\u001e\u001f !\"#J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&J\u0010\u0010\n\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u000bH&J3\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000e2!\u0010\u000f\u001a\u001d\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u00070\u0010H&J\b\u0010\u0015\u001a\u00020\u0007H&J5\u0010\u0016\u001a\u00020\u00072\b\b\u0002\u0010\u0017\u001a\u00020\u00182!\u0010\u000f\u001a\u001d\u0012\u0013\u0012\u00110\u0019¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u00070\u0010H&J-\u0010\u001b\u001a\u0004\u0018\u00010\u001c2!\u0010\u000f\u001a\u001d\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u00070\u0010H&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006$"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera;", "", "cameraControl", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera$CameraControl;", "getCameraControl", "()Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera$CameraControl;", "centerPreviewAccordingTo", "", "rect", "Landroid/graphics/RectF;", "observeFrame", "Lio/reactivex/rxjava3/core/Observable;", ViewProps.START, "cameraFacing", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera$CameraFacing;", "callback", "Lkotlin/Function1;", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera$CameraStatus;", "Lkotlin/ParameterName;", "name", "cameraStatus", "stop", "takePicture", "photoCaptureConfig", "Lcom/onfido/android/sdk/capture/ui/camera/capture/PhotoCaptureConfig;", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera$PictureCaptureEvent;", "event", "takeVideo", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera$VideoRecorder;", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera$VideoCaptureEvent;", "CameraControl", "CameraFacing", "CameraStatus", "PictureCaptureEvent", "VideoCaptureEvent", "VideoRecorder", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface OnfidoCamera {

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera$CameraControl;", "", "enableTorch", "", ViewProps.ENABLED, "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface CameraControl {
        void enableTorch(boolean enabled);
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera$CameraFacing;", "", "(Ljava/lang/String;I)V", "FRONT", "BACK", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class CameraFacing {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ CameraFacing[] $VALUES;
        public static final CameraFacing FRONT = new CameraFacing("FRONT", 0);
        public static final CameraFacing BACK = new CameraFacing("BACK", 1);

        private static final /* synthetic */ CameraFacing[] $values() {
            return new CameraFacing[]{FRONT, BACK};
        }

        static {
            CameraFacing[] cameraFacingArr$values = $values();
            $VALUES = cameraFacingArr$values;
            $ENTRIES = EnumEntriesKt.enumEntries(cameraFacingArr$values);
        }

        private CameraFacing(String str, int i) {
        }

        public static EnumEntries<CameraFacing> getEntries() {
            return $ENTRIES;
        }

        public static CameraFacing valueOf(String str) {
            return (CameraFacing) Enum.valueOf(CameraFacing.class, str);
        }

        public static CameraFacing[] values() {
            return (CameraFacing[]) $VALUES.clone();
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0004\u0002\u0003\u0004\u0005\u0082\u0001\u0004\u0006\u0007\b\t¨\u0006\n"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera$CameraStatus;", "", "Failed", "NotAvailable", "NotFound", "Started", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera$CameraStatus$Failed;", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera$CameraStatus$NotAvailable;", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera$CameraStatus$NotFound;", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera$CameraStatus$Started;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface CameraStatus {

        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera$CameraStatus$Failed;", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera$CameraStatus;", "error", "", "(Ljava/lang/Throwable;)V", "getError", "()Ljava/lang/Throwable;", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class Failed implements CameraStatus {
            private final Throwable error;

            public Failed(Throwable error) {
                Intrinsics.checkNotNullParameter(error, "error");
                this.error = error;
            }

            public static /* synthetic */ Failed copy$default(Failed failed, Throwable th, int i, Object obj) {
                if ((i & 1) != 0) {
                    th = failed.error;
                }
                return failed.copy(th);
            }

            /* renamed from: component1, reason: from getter */
            public final Throwable getError() {
                return this.error;
            }

            public final Failed copy(Throwable error) {
                Intrinsics.checkNotNullParameter(error, "error");
                return new Failed(error);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof Failed) && Intrinsics.areEqual(this.error, ((Failed) other).error);
            }

            public final Throwable getError() {
                return this.error;
            }

            public int hashCode() {
                return this.error.hashCode();
            }

            public String toString() {
                return "Failed(error=" + this.error + ')';
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera$CameraStatus$NotAvailable;", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera$CameraStatus;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class NotAvailable implements CameraStatus {
            public static final NotAvailable INSTANCE = new NotAvailable();

            private NotAvailable() {
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera$CameraStatus$NotFound;", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera$CameraStatus;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class NotFound implements CameraStatus {
            public static final NotFound INSTANCE = new NotFound();

            private NotFound() {
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera$CameraStatus$Started;", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera$CameraStatus;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Started implements CameraStatus {
            public static final Started INSTANCE = new Started();

            private Started() {
            }
        }
    }

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class DefaultImpls {
        public static /* synthetic */ void takePicture$default(OnfidoCamera onfidoCamera, PhotoCaptureConfig photoCaptureConfig, Function1 function1, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: takePicture");
            }
            if ((i & 1) != 0) {
                photoCaptureConfig = new PhotoCaptureConfig(false, 1, null);
            }
            onfidoCamera.takePicture(photoCaptureConfig, function1);
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0002\u0002\u0003\u0082\u0001\u0002\u0004\u0005¨\u0006\u0006"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera$PictureCaptureEvent;", "", "Captured", "Error", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera$PictureCaptureEvent$Captured;", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera$PictureCaptureEvent$Error;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface PictureCaptureEvent {

        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera$PictureCaptureEvent$Captured;", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera$PictureCaptureEvent;", MimeTypes.BASE_TYPE_IMAGE, "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoImage;", "(Lcom/onfido/android/sdk/capture/internal/camera/OnfidoImage;)V", "getImage", "()Lcom/onfido/android/sdk/capture/internal/camera/OnfidoImage;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Captured implements PictureCaptureEvent {
            private final OnfidoImage image;

            public Captured(OnfidoImage image) {
                Intrinsics.checkNotNullParameter(image, "image");
                this.image = image;
            }

            public final OnfidoImage getImage() {
                return this.image;
            }
        }

        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera$PictureCaptureEvent$Error;", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera$PictureCaptureEvent;", "error", "", "(Ljava/lang/Throwable;)V", "getError", "()Ljava/lang/Throwable;", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class Error implements PictureCaptureEvent {
            private final Throwable error;

            public Error(Throwable error) {
                Intrinsics.checkNotNullParameter(error, "error");
                this.error = error;
            }

            public static /* synthetic */ Error copy$default(Error error, Throwable th, int i, Object obj) {
                if ((i & 1) != 0) {
                    th = error.error;
                }
                return error.copy(th);
            }

            /* renamed from: component1, reason: from getter */
            public final Throwable getError() {
                return this.error;
            }

            public final Error copy(Throwable error) {
                Intrinsics.checkNotNullParameter(error, "error");
                return new Error(error);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof Error) && Intrinsics.areEqual(this.error, ((Error) other).error);
            }

            public final Throwable getError() {
                return this.error;
            }

            public int hashCode() {
                return this.error.hashCode();
            }

            public String toString() {
                return "Error(error=" + this.error + ')';
            }
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0005\u0002\u0003\u0004\u0005\u0006\u0082\u0001\u0005\u0007\b\t\n\u000b¨\u0006\f"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera$VideoCaptureEvent;", "", "Canceled", "Error", "Recorded", "Started", "Timeout", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera$VideoCaptureEvent$Canceled;", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera$VideoCaptureEvent$Error;", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera$VideoCaptureEvent$Recorded;", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera$VideoCaptureEvent$Started;", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera$VideoCaptureEvent$Timeout;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface VideoCaptureEvent {

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera$VideoCaptureEvent$Canceled;", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera$VideoCaptureEvent;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Canceled implements VideoCaptureEvent {
            public static final Canceled INSTANCE = new Canceled();

            private Canceled() {
            }
        }

        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera$VideoCaptureEvent$Error;", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera$VideoCaptureEvent;", "error", "", "(Ljava/lang/Throwable;)V", "getError", "()Ljava/lang/Throwable;", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class Error implements VideoCaptureEvent {
            private final Throwable error;

            public Error(Throwable error) {
                Intrinsics.checkNotNullParameter(error, "error");
                this.error = error;
            }

            public static /* synthetic */ Error copy$default(Error error, Throwable th, int i, Object obj) {
                if ((i & 1) != 0) {
                    th = error.error;
                }
                return error.copy(th);
            }

            /* renamed from: component1, reason: from getter */
            public final Throwable getError() {
                return this.error;
            }

            public final Error copy(Throwable error) {
                Intrinsics.checkNotNullParameter(error, "error");
                return new Error(error);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof Error) && Intrinsics.areEqual(this.error, ((Error) other).error);
            }

            public final Throwable getError() {
                return this.error;
            }

            public int hashCode() {
                return this.error.hashCode();
            }

            public String toString() {
                return "Error(error=" + this.error + ')';
            }
        }

        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera$VideoCaptureEvent$Recorded;", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera$VideoCaptureEvent;", "filePath", "", "(Ljava/lang/String;)V", "getFilePath", "()Ljava/lang/String;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Recorded implements VideoCaptureEvent {
            private final String filePath;

            public Recorded(String filePath) {
                Intrinsics.checkNotNullParameter(filePath, "filePath");
                this.filePath = filePath;
            }

            public final String getFilePath() {
                return this.filePath;
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera$VideoCaptureEvent$Started;", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera$VideoCaptureEvent;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Started implements VideoCaptureEvent {
            public static final Started INSTANCE = new Started();

            private Started() {
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera$VideoCaptureEvent$Timeout;", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera$VideoCaptureEvent;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Timeout implements VideoCaptureEvent {
            public static final Timeout INSTANCE = new Timeout();

            private Timeout() {
            }
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\bH&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0005¨\u0006\n"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera$VideoRecorder;", "", "hasValidRecording", "", "getHasValidRecording", "()Z", "isRecording", KeychainModule.AuthPromptOptions.CANCEL, "", "finish", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface VideoRecorder {
        void cancel();

        void finish();

        boolean getHasValidRecording();

        boolean isRecording();
    }

    void centerPreviewAccordingTo(RectF rect);

    CameraControl getCameraControl();

    Observable<? extends Object> observeFrame();

    void start(CameraFacing cameraFacing, Function1<? super CameraStatus, Unit> callback);

    void stop();

    void takePicture(PhotoCaptureConfig photoCaptureConfig, Function1<? super PictureCaptureEvent, Unit> callback);

    VideoRecorder takeVideo(Function1<? super VideoCaptureEvent, Unit> callback);
}
