package com.onfido.android.sdk.capture.detector.face;

import com.clevertap.android.sdk.Constants;
import com.facebook.react.uimanager.ViewProps;
import com.onfido.android.sdk.capture.internal.util.OnfidoRectF;
import io.sentry.protocol.ViewHierarchyNode;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\b\u0018\u0000 '2\u00020\u0001:\u0002'(B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\u0015\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0000H\u0000¢\u0006\u0002\b\u0014J1\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00122\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001J\r\u0010\u0019\u001a\u00020\u0003H\u0000¢\u0006\u0002\b\u001aJ%\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u0003H\u0000¢\u0006\u0002\b\u001fJ\r\u0010 \u001a\u00020!H\u0000¢\u0006\u0002\b\"J\t\u0010#\u001a\u00020$HÖ\u0001J\r\u0010%\u001a\u00020\u0003H\u0000¢\u0006\u0002\b&R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006)"}, d2 = {"Lcom/onfido/android/sdk/capture/detector/face/FaceDetectionRect;", "", "left", "", "top", ViewProps.RIGHT, ViewProps.BOTTOM, "(IIII)V", "getBottom", "()I", "getLeft", "getRight", "getTop", "component1", "component2", "component3", "component4", "contains", "", "otherRect", "contains$onfido_capture_sdk_core_release", Constants.COPY_TYPE, "equals", "other", "hashCode", "height", "height$onfido_capture_sdk_core_release", "rotate", "rotationIdentifier", "frameWidth", "frameHeight", "rotate$onfido_capture_sdk_core_release", "toOnfidoRect", "Lcom/onfido/android/sdk/capture/internal/util/OnfidoRectF;", "toOnfidoRect$onfido_capture_sdk_core_release", "toString", "", "width", "width$onfido_capture_sdk_core_release", "Companion", "Rotation", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class FaceDetectionRect {
    public static final int ANGLE_0_IDENTIFIER = 0;
    public static final int ANGLE_180_IDENTIFIER = 2;
    public static final int ANGLE_270_IDENTIFIER = 3;
    public static final int ANGLE_90_IDENTIFIER = 1;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final int bottom;
    private final int left;
    private final int right;
    private final int top;

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0000¢\u0006\u0002\b\fJ\u000e\u0010\r\u001a\u0004\u0018\u00010\u000e*\u00020\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/onfido/android/sdk/capture/detector/face/FaceDetectionRect$Companion;", "", "()V", "ANGLE_0_IDENTIFIER", "", "ANGLE_180_IDENTIFIER", "ANGLE_270_IDENTIFIER", "ANGLE_90_IDENTIFIER", "fromOnfidoRect", "Lcom/onfido/android/sdk/capture/detector/face/FaceDetectionRect;", "onfidoRectF", "Lcom/onfido/android/sdk/capture/internal/util/OnfidoRectF;", "fromOnfidoRect$onfido_capture_sdk_core_release", "getRotation", "Lcom/onfido/android/sdk/capture/detector/face/FaceDetectionRect$Rotation;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Rotation getRotation(int i) {
            for (Rotation rotation : Rotation.values()) {
                if (rotation.getIdentifier() == i) {
                    return rotation;
                }
            }
            return null;
        }

        public final FaceDetectionRect fromOnfidoRect$onfido_capture_sdk_core_release(OnfidoRectF onfidoRectF) {
            Intrinsics.checkNotNullParameter(onfidoRectF, "onfidoRectF");
            return new FaceDetectionRect((int) onfidoRectF.getLeft(), (int) onfidoRectF.getTop(), (int) onfidoRectF.getRight(), (int) onfidoRectF.getBottom());
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\b\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lcom/onfido/android/sdk/capture/detector/face/FaceDetectionRect$Rotation;", "", ViewHierarchyNode.JsonKeys.IDENTIFIER, "", "(Ljava/lang/String;II)V", "getIdentifier", "()I", "ANGLE_0", "ANGLE_90", "ANGLE_180", "ANGLE_270", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class Rotation {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ Rotation[] $VALUES;
        private final int identifier;
        public static final Rotation ANGLE_0 = new Rotation("ANGLE_0", 0, 0);
        public static final Rotation ANGLE_90 = new Rotation("ANGLE_90", 1, 1);
        public static final Rotation ANGLE_180 = new Rotation("ANGLE_180", 2, 2);
        public static final Rotation ANGLE_270 = new Rotation("ANGLE_270", 3, 3);

        private static final /* synthetic */ Rotation[] $values() {
            return new Rotation[]{ANGLE_0, ANGLE_90, ANGLE_180, ANGLE_270};
        }

        static {
            Rotation[] rotationArr$values = $values();
            $VALUES = rotationArr$values;
            $ENTRIES = EnumEntriesKt.enumEntries(rotationArr$values);
        }

        private Rotation(String str, int i, int i2) {
            this.identifier = i2;
        }

        public static EnumEntries<Rotation> getEntries() {
            return $ENTRIES;
        }

        public static Rotation valueOf(String str) {
            return (Rotation) Enum.valueOf(Rotation.class, str);
        }

        public static Rotation[] values() {
            return (Rotation[]) $VALUES.clone();
        }

        public final int getIdentifier() {
            return this.identifier;
        }
    }

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Rotation.values().length];
            try {
                iArr[Rotation.ANGLE_90.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Rotation.ANGLE_180.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[Rotation.ANGLE_270.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public FaceDetectionRect(int i, int i2, int i3, int i4) {
        this.left = i;
        this.top = i2;
        this.right = i3;
        this.bottom = i4;
    }

    public static /* synthetic */ FaceDetectionRect copy$default(FaceDetectionRect faceDetectionRect, int i, int i2, int i3, int i4, int i5, Object obj) {
        if ((i5 & 1) != 0) {
            i = faceDetectionRect.left;
        }
        if ((i5 & 2) != 0) {
            i2 = faceDetectionRect.top;
        }
        if ((i5 & 4) != 0) {
            i3 = faceDetectionRect.right;
        }
        if ((i5 & 8) != 0) {
            i4 = faceDetectionRect.bottom;
        }
        return faceDetectionRect.copy(i, i2, i3, i4);
    }

    /* renamed from: component1, reason: from getter */
    public final int getLeft() {
        return this.left;
    }

    /* renamed from: component2, reason: from getter */
    public final int getTop() {
        return this.top;
    }

    /* renamed from: component3, reason: from getter */
    public final int getRight() {
        return this.right;
    }

    /* renamed from: component4, reason: from getter */
    public final int getBottom() {
        return this.bottom;
    }

    public final boolean contains$onfido_capture_sdk_core_release(FaceDetectionRect otherRect) {
        Intrinsics.checkNotNullParameter(otherRect, "otherRect");
        return this.left <= otherRect.left && this.right >= otherRect.right && this.top <= otherRect.top && this.bottom >= otherRect.bottom;
    }

    public final FaceDetectionRect copy(int left, int top, int right, int bottom) {
        return new FaceDetectionRect(left, top, right, bottom);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FaceDetectionRect)) {
            return false;
        }
        FaceDetectionRect faceDetectionRect = (FaceDetectionRect) other;
        return this.left == faceDetectionRect.left && this.top == faceDetectionRect.top && this.right == faceDetectionRect.right && this.bottom == faceDetectionRect.bottom;
    }

    public final int getBottom() {
        return this.bottom;
    }

    public final int getLeft() {
        return this.left;
    }

    public final int getRight() {
        return this.right;
    }

    public final int getTop() {
        return this.top;
    }

    public int hashCode() {
        return (((((Integer.hashCode(this.left) * 31) + Integer.hashCode(this.top)) * 31) + Integer.hashCode(this.right)) * 31) + Integer.hashCode(this.bottom);
    }

    public final int height$onfido_capture_sdk_core_release() {
        return this.bottom - this.top;
    }

    public final FaceDetectionRect rotate$onfido_capture_sdk_core_release(int rotationIdentifier, int frameWidth, int frameHeight) {
        Rotation rotation = INSTANCE.getRotation(rotationIdentifier);
        int i = rotation == null ? -1 : WhenMappings.$EnumSwitchMapping$0[rotation.ordinal()];
        if (i == 1) {
            int iHeight$onfido_capture_sdk_core_release = (frameWidth - this.top) - height$onfido_capture_sdk_core_release();
            int i2 = this.left;
            return new FaceDetectionRect(iHeight$onfido_capture_sdk_core_release, i2, frameWidth - this.top, width$onfido_capture_sdk_core_release() + i2);
        }
        if (i == 2) {
            return new FaceDetectionRect(this.left, (frameHeight - this.top) - height$onfido_capture_sdk_core_release(), this.right, frameHeight - this.top);
        }
        if (i != 3) {
            return this;
        }
        int i3 = this.top;
        return new FaceDetectionRect(i3, this.left, height$onfido_capture_sdk_core_release() + i3, this.left + width$onfido_capture_sdk_core_release());
    }

    public final OnfidoRectF toOnfidoRect$onfido_capture_sdk_core_release() {
        return new OnfidoRectF(this.left, this.top, this.right, this.bottom);
    }

    public String toString() {
        return "FaceDetectionRect(left=" + this.left + ", top=" + this.top + ", right=" + this.right + ", bottom=" + this.bottom + ')';
    }

    public final int width$onfido_capture_sdk_core_release() {
        return this.right - this.left;
    }
}
