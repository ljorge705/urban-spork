package com.onfido.android.sdk.capture.ui.camera;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import androidx.core.graphics.BlendModeColorFilterCompat;
import androidx.core.graphics.BlendModeCompat;
import androidx.core.view.OneShotPreDrawListener;
import com.facebook.react.uimanager.ViewProps;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.databinding.OnfidoOverlayViewBinding;
import com.onfido.android.sdk.capture.internal.ui.overlay.OverlayViewAnimations;
import com.onfido.android.sdk.capture.internal.ui.overlay.OverlayViewHorizontalWeights;
import com.onfido.android.sdk.capture.internal.ui.overlay.OverlayViewPositionHelper;
import com.onfido.android.sdk.capture.ui.CaptureType;
import com.onfido.android.sdk.capture.utils.ContextUtilsKt;
import com.onfido.android.sdk.capture.utils.ViewExtensionsKt;
import com.onfido.android.sdk.capture.utils.listeners.AnimatorListener;
import com.onfido.reactnative.sdk.ReactNativeBridgeUtiles;
import io.sentry.Session;
import java.util.NoSuchElementException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.Delegates;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;

@Metadata(d1 = {"\u0000 \u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0011\n\u0002\b\u0005\b\u0000\u0018\u0000 z2\u00020\u0001:\u0003z{|B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0010\u0010I\u001a\u00020J2\u0006\u0010K\u001a\u00020LH\u0002J\u0018\u0010M\u001a\u00020N2\u0006\u0010O\u001a\u00020P2\u0006\u0010Q\u001a\u00020RH\u0002J\u0018\u0010S\u001a\u00020N2\u0006\u0010O\u001a\u00020P2\u0006\u0010Q\u001a\u00020RH\u0002J\u0006\u0010T\u001a\u00020\u000eJ\u001e\u0010U\u001a\u00020N2\f\u0010V\u001a\b\u0012\u0004\u0012\u00020N0W2\u0006\u0010B\u001a\u00020&H\u0002J\u0014\u0010X\u001a\u00020N2\f\u0010V\u001a\b\u0012\u0004\u0012\u00020N0WJ\u0016\u0010Y\u001a\u00020N2\f\u0010V\u001a\b\u0012\u0004\u0012\u00020N0WH\u0002J\u0006\u0010Z\u001a\u00020NJ\u0006\u0010[\u001a\u00020NJ\u0010\u0010\\\u001a\u00020N2\u0006\u0010O\u001a\u00020PH\u0014J\u0016\u0010]\u001a\u00020N2\u000e\b\u0002\u0010V\u001a\b\u0012\u0004\u0012\u00020N0WJ\u0006\u0010^\u001a\u00020NJ\u0006\u0010_\u001a\u00020NJ$\u0010`\u001a\u00020N2\b\b\u0002\u0010a\u001a\u00020.2\b\b\u0002\u0010b\u001a\u00020.2\b\b\u0002\u0010c\u001a\u00020.J\u001a\u0010d\u001a\u00020N2\b\b\u0001\u0010e\u001a\u00020\t2\b\b\u0002\u0010f\u001a\u00020.J\u000e\u0010g\u001a\u00020N2\u0006\u0010-\u001a\u00020.J\u0010\u0010h\u001a\u00020N2\b\u0010i\u001a\u0004\u0018\u00010jJ\u0018\u0010k\u001a\u00020N2\u0006\u0010l\u001a\u00020m2\b\u0010i\u001a\u0004\u0018\u00010jJ\u0016\u0010n\u001a\u00020N2\u000e\b\u0002\u0010V\u001a\b\u0012\u0004\u0012\u00020N0WJ\u000e\u0010o\u001a\u00020N2\u0006\u0010p\u001a\u00020.J\u001c\u0010q\u001a\u00020N2\b\b\u0001\u0010r\u001a\u00020\t2\b\b\u0001\u0010s\u001a\u00020\tH\u0002J\b\u0010t\u001a\u00020NH\u0002J'\u0010u\u001a\u00020N*\u00020P2\u0006\u0010v\u001a\u00020R2\f\u0010w\u001a\b\u0012\u0004\u0012\u00020&0xH\u0002¢\u0006\u0002\u0010yR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R+\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u000e8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R+\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u000e8@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\b\u0019\u0010\u0015\u001a\u0004\b\u0017\u0010\u0011\"\u0004\b\u0018\u0010\u0013R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u001c\u001a\u00020\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b\u001d\u0010\u0011R\u000e\u0010 \u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010%\u001a\u00020&8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b)\u0010\u001f\u001a\u0004\b'\u0010(R\u001b\u0010*\u001a\u00020&8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b,\u0010\u001f\u001a\u0004\b+\u0010(R\u000e\u0010-\u001a\u00020.X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010/\u001a\u00020\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b1\u0010\u001f\u001a\u0004\b0\u0010\u0011R\u000e\u00102\u001a\u00020&X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u00103\u001a\u0004\u0018\u000104X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R+\u00107\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u000e8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b:\u0010\u0015\u001a\u0004\b8\u0010\u0011\"\u0004\b9\u0010\u0013R+\u0010<\u001a\u00020;2\u0006\u0010\r\u001a\u00020;8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\bA\u0010\u0015\u001a\u0004\b=\u0010>\"\u0004\b?\u0010@R\u001b\u0010B\u001a\u00020&8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bD\u0010\u001f\u001a\u0004\bC\u0010(R+\u0010E\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u000e8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\bH\u0010\u0015\u001a\u0004\bF\u0010\u0011\"\u0004\bG\u0010\u0013¨\u0006}"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/OverlayView;", "Landroid/widget/RelativeLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", Session.JsonKeys.ATTRS, "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "animations", "Lcom/onfido/android/sdk/capture/internal/ui/overlay/OverlayViewAnimations;", "<set-?>", "", ViewProps.ASPECT_RATIO, "getAspectRatio", "()F", "setAspectRatio", "(F)V", "aspectRatio$delegate", "Lkotlin/properties/ReadWriteProperty;", "bigHorizontalWeight", "getBigHorizontalWeight$onfido_capture_sdk_core_release", "setBigHorizontalWeight$onfido_capture_sdk_core_release", "bigHorizontalWeight$delegate", "binding", "Lcom/onfido/android/sdk/capture/databinding/OnfidoOverlayViewBinding;", "borderStrokeWidth", "getBorderStrokeWidth", "borderStrokeWidth$delegate", "Lkotlin/Lazy;", "captureAreaColor", "captureAreaMode", "Landroid/graphics/PorterDuffXfermode;", "colorOutsideOverlay", "defaultStrokeWidth", "documentOverlayStrokePaint", "Landroid/graphics/Paint;", "getDocumentOverlayStrokePaint", "()Landroid/graphics/Paint;", "documentOverlayStrokePaint$delegate", "faceStrokePaint", "getFaceStrokePaint", "faceStrokePaint$delegate", "isProofOfAddress", "", "ovalDashLength", "getOvalDashLength", "ovalDashLength$delegate", "overlayCleanerPaint", "overlayViewPositionHelper", "Lcom/onfido/android/sdk/capture/internal/ui/overlay/OverlayViewPositionHelper;", "rectangleBorderRadius", "rectangleRadius", "smallHorizontalWeight", "getSmallHorizontalWeight", "setSmallHorizontalWeight", "smallHorizontalWeight$delegate", "Lcom/onfido/android/sdk/capture/ui/camera/OverlayView$OverlayType;", "type", "getType", "()Lcom/onfido/android/sdk/capture/ui/camera/OverlayView$OverlayType;", "setType", "(Lcom/onfido/android/sdk/capture/ui/camera/OverlayView$OverlayType;)V", "type$delegate", "videoStrokePaint", "getVideoStrokePaint", "videoStrokePaint$delegate", "visibleHorizontalWeight", "getVisibleHorizontalWeight", "setVisibleHorizontalWeight", "visibleHorizontalWeight$delegate", "canvasRect", "Landroid/graphics/Rect;", "it", "Landroid/view/View;", "drawCaptureOverlay", "", "canvas", "Landroid/graphics/Canvas;", "overlayRectangle", "Landroid/graphics/RectF;", "drawDocumentOverlay", "getVerticalWeight", "growOval", "onAnimationFinish", "Lkotlin/Function0;", "inflateDocumentDetectionTick", "inflateFaceDetectionTick", "onDocumentVideoRecordFinished", "onDocumentVideoRecordStarted", "onDraw", "onSingleFrameAutoCaptured", "paintCaptureArea", "resetDocumentOverlay", "resetFaceDetectedState", "withAnimation", "shouldClearOverlay", "shouldResetOvalStrokeWidth", "setColorOutsideOverlay", "color", "animate", "setIsProofOfAddress", "setListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/onfido/android/sdk/capture/ui/camera/OverlayView$Listener;", "setUp", ReactNativeBridgeUtiles.KEY_CAPTURE_TYPE, "Lcom/onfido/android/sdk/capture/ui/CaptureType;", "showFaceConfirmationTick", "switchConfirmationMode", "isConfirmationMode", "updateConfirmationIconColor", ViewProps.BACKGROUND_COLOR, "tickIconColor", "updateTickIconColor", "drawMultipleOvals", "rectangle", "paints", "", "(Landroid/graphics/Canvas;Landroid/graphics/RectF;[Landroid/graphics/Paint;)V", "Companion", "Listener", "OverlayType", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class OverlayView extends RelativeLayout {
    private static final long BACKGROUND_ANIM_DURATION_MS = 300;
    private static final float BORDER_STROKE_OFFSET_RATIO = 2.0f;
    private static final float DOCUMENT_DETECTED_TICK_ANIMATION_ALPHA_END = 1.0f;
    private static final float DOCUMENT_DETECTED_TICK_ANIMATION_ALPHA_START = 0.15f;
    private static final long FACE_DETECTED_TICK_ANIMATION_DURATION_MS = 500;
    private static final long SHORT_ANIM_DURATION_MS = 300;
    private final OverlayViewAnimations animations;

    /* renamed from: aspectRatio$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty aspectRatio;

    /* renamed from: bigHorizontalWeight$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty bigHorizontalWeight;
    private final OnfidoOverlayViewBinding binding;

    /* renamed from: borderStrokeWidth$delegate, reason: from kotlin metadata */
    private final Lazy borderStrokeWidth;
    private int captureAreaColor;
    private PorterDuffXfermode captureAreaMode;
    private int colorOutsideOverlay;
    private float defaultStrokeWidth;

    /* renamed from: documentOverlayStrokePaint$delegate, reason: from kotlin metadata */
    private final Lazy documentOverlayStrokePaint;

    /* renamed from: faceStrokePaint$delegate, reason: from kotlin metadata */
    private final Lazy faceStrokePaint;
    private boolean isProofOfAddress;

    /* renamed from: ovalDashLength$delegate, reason: from kotlin metadata */
    private final Lazy ovalDashLength;
    private final Paint overlayCleanerPaint;
    private OverlayViewPositionHelper overlayViewPositionHelper;
    private final float rectangleBorderRadius;
    private final float rectangleRadius;

    /* renamed from: smallHorizontalWeight$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty smallHorizontalWeight;

    /* renamed from: type$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty type;

    /* renamed from: videoStrokePaint$delegate, reason: from kotlin metadata */
    private final Lazy videoStrokePaint;

    /* renamed from: visibleHorizontalWeight$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty visibleHorizontalWeight;
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(OverlayView.class, ViewProps.ASPECT_RATIO, "getAspectRatio()F", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(OverlayView.class, "bigHorizontalWeight", "getBigHorizontalWeight$onfido_capture_sdk_core_release()F", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(OverlayView.class, "smallHorizontalWeight", "getSmallHorizontalWeight()F", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(OverlayView.class, "visibleHorizontalWeight", "getVisibleHorizontalWeight()F", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(OverlayView.class, "type", "getType()Lcom/onfido/android/sdk/capture/ui/camera/OverlayView$OverlayType;", 0))};

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bæ\u0080\u0001\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/OverlayView$Listener;", "", "onOverlayMetrics", "", "overlayMetrics", "Lcom/onfido/android/sdk/capture/ui/camera/OverlayMetrics;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface Listener {
        void onOverlayMetrics(OverlayMetrics overlayMetrics);
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\b\b\u0082\u0081\u0002\u0018\u0000 \n2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\nB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\u000b"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/OverlayView$OverlayType;", "", "value", "", "(Ljava/lang/String;II)V", "getValue", "()I", "FACE_OVERLAY", "DOCUMENT_OVERLAY", "VIDEO_OVERLAY", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class OverlayType {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ OverlayType[] $VALUES;

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE;
        private final int value;
        public static final OverlayType FACE_OVERLAY = new OverlayType("FACE_OVERLAY", 0, 0);
        public static final OverlayType DOCUMENT_OVERLAY = new OverlayType("DOCUMENT_OVERLAY", 1, 1);
        public static final OverlayType VIDEO_OVERLAY = new OverlayType("VIDEO_OVERLAY", 2, 2);

        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/OverlayView$OverlayType$Companion;", "", "()V", "fromIntValue", "Lcom/onfido/android/sdk/capture/ui/camera/OverlayView$OverlayType;", "value", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Companion {
            private Companion() {
            }

            public final OverlayType fromIntValue(int value) {
                for (OverlayType overlayType : OverlayType.values()) {
                    if (overlayType.getValue() == value) {
                        return overlayType;
                    }
                }
                throw new NoSuchElementException("Array contains no element matching the predicate.");
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }
        }

        private static final /* synthetic */ OverlayType[] $values() {
            return new OverlayType[]{FACE_OVERLAY, DOCUMENT_OVERLAY, VIDEO_OVERLAY};
        }

        static {
            OverlayType[] overlayTypeArr$values = $values();
            $VALUES = overlayTypeArr$values;
            $ENTRIES = EnumEntriesKt.enumEntries(overlayTypeArr$values);
            INSTANCE = new Companion(null);
        }

        private OverlayType(String str, int i, int i2) {
            this.value = i2;
        }

        public static EnumEntries<OverlayType> getEntries() {
            return $ENTRIES;
        }

        public static OverlayType valueOf(String str) {
            return (OverlayType) Enum.valueOf(OverlayType.class, str);
        }

        public static OverlayType[] values() {
            return (OverlayType[]) $VALUES.clone();
        }

        public final int getValue() {
            return this.value;
        }
    }

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[OverlayType.values().length];
            try {
                iArr[OverlayType.FACE_OVERLAY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[OverlayType.DOCUMENT_OVERLAY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[OverlayType.VIDEO_OVERLAY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OverlayView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        OnfidoOverlayViewBinding onfidoOverlayViewBindingInflate = OnfidoOverlayViewBinding.inflate(LayoutInflater.from(getContext()), this, true);
        Intrinsics.checkNotNullExpressionValue(onfidoOverlayViewBindingInflate, "inflate(...)");
        this.binding = onfidoOverlayViewBindingInflate;
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        this.overlayCleanerPaint = paint;
        this.borderStrokeWidth = LazyKt.lazy(new Function0<Float>() { // from class: com.onfido.android.sdk.capture.ui.camera.OverlayView$borderStrokeWidth$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Float invoke() {
                return Float.valueOf(this.this$0.getContext().getResources().getDimension(R.dimen.onfido_doc_capture_overlay_stroke_width));
            }
        });
        this.documentOverlayStrokePaint = LazyKt.lazy(new Function0<Paint>() { // from class: com.onfido.android.sdk.capture.ui.camera.OverlayView$documentOverlayStrokePaint$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Paint invoke() {
                Paint paint2 = new Paint();
                OverlayView overlayView = this.this$0;
                paint2.setAntiAlias(true);
                Context context2 = overlayView.getContext();
                Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
                paint2.setColor(ContextUtilsKt.colorFromAttr(context2, R.attr.onfidoColorDocumentOverlayStroke));
                paint2.setStrokeWidth(overlayView.getBorderStrokeWidth());
                paint2.setStyle(Paint.Style.STROKE);
                return paint2;
            }
        });
        this.faceStrokePaint = LazyKt.lazy(new Function0<Paint>() { // from class: com.onfido.android.sdk.capture.ui.camera.OverlayView$faceStrokePaint$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Paint invoke() {
                Paint paint2 = new Paint();
                Context context2 = this.this$0.getContext();
                Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
                paint2.setColor(ContextUtilsKt.colorFromAttr(context2, R.attr.onfidoColorContentMainDark));
                paint2.setStyle(Paint.Style.STROKE);
                paint2.setAntiAlias(true);
                return paint2;
            }
        });
        this.ovalDashLength = LazyKt.lazy(new Function0<Float>() { // from class: com.onfido.android.sdk.capture.ui.camera.OverlayView$ovalDashLength$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Float invoke() {
                return Float.valueOf(this.this$0.getResources().getDimensionPixelSize(R.dimen.onfido_liveness_face_detection_oval_dash_length));
            }
        });
        this.videoStrokePaint = LazyKt.lazy(new Function0<Paint>() { // from class: com.onfido.android.sdk.capture.ui.camera.OverlayView$videoStrokePaint$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Paint invoke() {
                Paint paint2 = new Paint();
                OverlayView overlayView = this.this$0;
                Context context2 = overlayView.getContext();
                Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
                paint2.setColor(ContextUtilsKt.colorFromAttr(context2, R.attr.onfidoColorContentMainDark));
                paint2.setStyle(Paint.Style.STROKE);
                Intrinsics.checkNotNullExpressionValue(overlayView.getContext(), "getContext(...)");
                paint2.setStrokeWidth(ContextUtilsKt.dimenInt(r2, R.dimen.onfido_liveness_face_detected_oval_stroke_width));
                paint2.setAntiAlias(true);
                paint2.setPathEffect(new DashPathEffect(new float[]{overlayView.getOvalDashLength(), overlayView.getOvalDashLength()}, 0.0f));
                return paint2;
            }
        });
        this.rectangleRadius = getResources().getDimensionPixelOffset(R.dimen.onfido_capture_rectangle_radius);
        this.rectangleBorderRadius = getResources().getDimensionPixelOffset(R.dimen.onfido_capture_rectangle_border_radius);
        this.captureAreaMode = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);
        Delegates delegates = Delegates.INSTANCE;
        this.aspectRatio = delegates.notNull();
        this.bigHorizontalWeight = delegates.notNull();
        this.smallHorizontalWeight = delegates.notNull();
        this.visibleHorizontalWeight = delegates.notNull();
        this.type = delegates.notNull();
        this.animations = new OverlayViewAnimations(this);
        throw new IllegalStateException("Load OverlayView only from a XML layout.".toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Rect canvasRect(View it) {
        return new Rect(0, 0, it.getWidth(), it.getHeight());
    }

    private final void drawCaptureOverlay(Canvas canvas, RectF overlayRectangle) {
        int i = WhenMappings.$EnumSwitchMapping$0[getType().ordinal()];
        if (i == 1) {
            drawMultipleOvals(canvas, overlayRectangle, new Paint[]{this.overlayCleanerPaint, getFaceStrokePaint()});
        } else if (i == 2) {
            drawDocumentOverlay(canvas, overlayRectangle);
        } else {
            if (i != 3) {
                return;
            }
            drawMultipleOvals(canvas, overlayRectangle, new Paint[]{this.overlayCleanerPaint, getVideoStrokePaint()});
        }
    }

    private final void drawDocumentOverlay(Canvas canvas, RectF overlayRectangle) {
        float f = this.rectangleRadius;
        canvas.drawRoundRect(overlayRectangle, f, f, this.overlayCleanerPaint);
        float borderStrokeWidth = getBorderStrokeWidth() * 2.0f;
        float f2 = overlayRectangle.left - borderStrokeWidth;
        float f3 = overlayRectangle.top - borderStrokeWidth;
        float f4 = overlayRectangle.right + borderStrokeWidth;
        float f5 = overlayRectangle.bottom + borderStrokeWidth;
        float f6 = this.rectangleBorderRadius;
        canvas.drawRoundRect(f2, f3, f4, f5, f6, f6, getDocumentOverlayStrokePaint());
    }

    private final void drawMultipleOvals(Canvas canvas, RectF rectF, Paint[] paintArr) {
        for (Paint paint : paintArr) {
            canvas.drawOval(rectF, paint);
        }
    }

    private final float getAspectRatio() {
        return ((Number) this.aspectRatio.getValue(this, $$delegatedProperties[0])).floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final float getBorderStrokeWidth() {
        return ((Number) this.borderStrokeWidth.getValue()).floatValue();
    }

    private final Paint getDocumentOverlayStrokePaint() {
        return (Paint) this.documentOverlayStrokePaint.getValue();
    }

    private final Paint getFaceStrokePaint() {
        return (Paint) this.faceStrokePaint.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final float getOvalDashLength() {
        return ((Number) this.ovalDashLength.getValue()).floatValue();
    }

    private final float getSmallHorizontalWeight() {
        return ((Number) this.smallHorizontalWeight.getValue(this, $$delegatedProperties[2])).floatValue();
    }

    private final OverlayType getType() {
        return (OverlayType) this.type.getValue(this, $$delegatedProperties[4]);
    }

    private final Paint getVideoStrokePaint() {
        return (Paint) this.videoStrokePaint.getValue();
    }

    private final float getVisibleHorizontalWeight() {
        return ((Number) this.visibleHorizontalWeight.getValue(this, $$delegatedProperties[3])).floatValue();
    }

    private final void growOval(final Function0<Unit> onAnimationFinish, final Paint videoStrokePaint) {
        float f = this.defaultStrokeWidth;
        Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(f, ContextUtilsKt.dimenInt(r1, R.dimen.onfido_liveness_face_detected_oval_stroke_width));
        valueAnimatorOfFloat.addListener(new AnimatorListener() { // from class: com.onfido.android.sdk.capture.ui.camera.OverlayView$growOval$1$1
            @Override // com.onfido.android.sdk.capture.utils.listeners.AnimatorListener, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                AnimatorListener.DefaultImpls.onAnimationCancel(this, animator);
            }

            @Override // com.onfido.android.sdk.capture.utils.listeners.AnimatorListener, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
                this.this$0.inflateFaceDetectionTick(onAnimationFinish);
            }

            @Override // com.onfido.android.sdk.capture.utils.listeners.AnimatorListener, android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
                AnimatorListener.DefaultImpls.onAnimationRepeat(this, animator);
            }

            @Override // com.onfido.android.sdk.capture.utils.listeners.AnimatorListener, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                AnimatorListener.DefaultImpls.onAnimationStart(this, animator);
            }
        });
        valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.onfido.android.sdk.capture.ui.camera.OverlayView$$ExternalSyntheticLambda1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                OverlayView.growOval$lambda$10$lambda$9(videoStrokePaint, this, valueAnimator);
            }
        });
        valueAnimatorOfFloat.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void growOval$lambda$10$lambda$9(Paint videoStrokePaint, OverlayView this$0, ValueAnimator it) {
        Intrinsics.checkNotNullParameter(videoStrokePaint, "$videoStrokePaint");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        Object animatedValue = it.getAnimatedValue();
        Intrinsics.checkNotNull(animatedValue, "null cannot be cast to non-null type kotlin.Float");
        videoStrokePaint.setStrokeWidth(((Float) animatedValue).floatValue());
        this$0.invalidate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void inflateFaceDetectionTick(final Function0<Unit> onAnimationFinish) {
        OverlayViewPositionHelper overlayViewPositionHelper = this.overlayViewPositionHelper;
        OverlayMetrics overlayMetrics = overlayViewPositionHelper != null ? overlayViewPositionHelper.getOverlayMetrics() : null;
        Intrinsics.checkNotNull(overlayMetrics);
        final RectF visibleCaptureRect = overlayMetrics.getVisibleCaptureRect();
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        ValueAnimator valueAnimatorOfInt = ValueAnimator.ofInt(0, ContextUtilsKt.dimenInt(context, R.dimen.onfido_liveness_face_detected_tick_size));
        valueAnimatorOfInt.setDuration(500L);
        valueAnimatorOfInt.setInterpolator(new OvershootInterpolator());
        valueAnimatorOfInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.onfido.android.sdk.capture.ui.camera.OverlayView$$ExternalSyntheticLambda0
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                OverlayView.inflateFaceDetectionTick$lambda$6$lambda$5(this.f$0, visibleCaptureRect, valueAnimator);
            }
        });
        valueAnimatorOfInt.addListener(new AnimatorListener() { // from class: com.onfido.android.sdk.capture.ui.camera.OverlayView$inflateFaceDetectionTick$1$2
            @Override // com.onfido.android.sdk.capture.utils.listeners.AnimatorListener, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                AnimatorListener.DefaultImpls.onAnimationCancel(this, animator);
            }

            @Override // com.onfido.android.sdk.capture.utils.listeners.AnimatorListener, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
                onAnimationFinish.invoke();
            }

            @Override // com.onfido.android.sdk.capture.utils.listeners.AnimatorListener, android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
                AnimatorListener.DefaultImpls.onAnimationRepeat(this, animator);
            }

            @Override // com.onfido.android.sdk.capture.utils.listeners.AnimatorListener, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
                FrameLayout tickContainer = this.this$0.binding.tickContainer;
                Intrinsics.checkNotNullExpressionValue(tickContainer, "tickContainer");
                tickContainer.setVisibility(0);
            }
        });
        valueAnimatorOfInt.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void inflateFaceDetectionTick$lambda$6$lambda$5(OverlayView this$0, RectF visibleOverlayRectangle, ValueAnimator valueAnimator) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(visibleOverlayRectangle, "$visibleOverlayRectangle");
        Intrinsics.checkNotNullParameter(valueAnimator, "valueAnimator");
        FrameLayout tickContainer = this$0.binding.tickContainer;
        Intrinsics.checkNotNullExpressionValue(tickContainer, "tickContainer");
        ViewGroup.LayoutParams layoutParams = tickContainer.getLayoutParams();
        if (layoutParams == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup.LayoutParams");
        }
        Object animatedValue = valueAnimator.getAnimatedValue();
        Intrinsics.checkNotNull(animatedValue, "null cannot be cast to non-null type kotlin.Int");
        layoutParams.width = ((Integer) animatedValue).intValue();
        Object animatedValue2 = valueAnimator.getAnimatedValue();
        Intrinsics.checkNotNull(animatedValue2, "null cannot be cast to non-null type kotlin.Int");
        layoutParams.height = ((Integer) animatedValue2).intValue();
        this$0.binding.tickContainer.setY(visibleOverlayRectangle.top + ((visibleOverlayRectangle.height() - layoutParams.height) / 2.0f));
        tickContainer.setLayoutParams(layoutParams);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void onSingleFrameAutoCaptured$default(OverlayView overlayView, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            function0 = new Function0<Unit>() { // from class: com.onfido.android.sdk.capture.ui.camera.OverlayView.onSingleFrameAutoCaptured.1
                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }
            };
        }
        overlayView.onSingleFrameAutoCaptured(function0);
    }

    public static /* synthetic */ void resetFaceDetectedState$default(OverlayView overlayView, boolean z, boolean z2, boolean z3, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        if ((i & 2) != 0) {
            z2 = true;
        }
        if ((i & 4) != 0) {
            z3 = true;
        }
        overlayView.resetFaceDetectedState(z, z2, z3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void resetFaceDetectedState$lambda$11(Function0 collapseTickContainer, OverlayView this$0) {
        Intrinsics.checkNotNullParameter(collapseTickContainer, "$collapseTickContainer");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        collapseTickContainer.invoke();
        this$0.binding.tickContainer.setAlpha(1.0f);
    }

    private final void setAspectRatio(float f) {
        this.aspectRatio.setValue(this, $$delegatedProperties[0], Float.valueOf(f));
    }

    public static /* synthetic */ void setColorOutsideOverlay$default(OverlayView overlayView, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = true;
        }
        overlayView.setColorOutsideOverlay(i, z);
    }

    private final void setSmallHorizontalWeight(float f) {
        this.smallHorizontalWeight.setValue(this, $$delegatedProperties[2], Float.valueOf(f));
    }

    private final void setType(OverlayType overlayType) {
        this.type.setValue(this, $$delegatedProperties[4], overlayType);
    }

    private final void setVisibleHorizontalWeight(float f) {
        this.visibleHorizontalWeight.setValue(this, $$delegatedProperties[3], Float.valueOf(f));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void showFaceConfirmationTick$default(OverlayView overlayView, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            function0 = new Function0<Unit>() { // from class: com.onfido.android.sdk.capture.ui.camera.OverlayView.showFaceConfirmationTick.1
                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }
            };
        }
        overlayView.showFaceConfirmationTick(function0);
    }

    private final void updateConfirmationIconColor(int backgroundColor, int tickIconColor) {
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        int iColorFromAttr = ContextUtilsKt.colorFromAttr(context, backgroundColor);
        Context context2 = getContext();
        Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
        int iColorFromAttr2 = ContextUtilsKt.colorFromAttr(context2, tickIconColor);
        this.binding.tickContainer.getBackground().setColorFilter(BlendModeColorFilterCompat.createBlendModeColorFilterCompat(iColorFromAttr, BlendModeCompat.SRC_ATOP));
        this.binding.tickIcon.setColorFilter(iColorFromAttr2, PorterDuff.Mode.SRC_ATOP);
    }

    private final void updateTickIconColor() {
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        int iColorFromAttr = ContextUtilsKt.colorFromAttr(context, R.attr.onfidoColorDocumentOvalSuccess);
        Context context2 = getContext();
        Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
        int iColorFromAttr2 = ContextUtilsKt.colorFromAttr(context2, R.attr.onfidoColorDocumentTickSuccess);
        this.binding.tickContainer.getBackground().setColorFilter(BlendModeColorFilterCompat.createBlendModeColorFilterCompat(iColorFromAttr, BlendModeCompat.SRC_ATOP));
        this.binding.tickIcon.setColorFilter(iColorFromAttr2, PorterDuff.Mode.SRC_ATOP);
    }

    public final float getBigHorizontalWeight$onfido_capture_sdk_core_release() {
        return ((Number) this.bigHorizontalWeight.getValue(this, $$delegatedProperties[1])).floatValue();
    }

    public final float getVerticalWeight() {
        OverlayViewPositionHelper overlayViewPositionHelper = this.overlayViewPositionHelper;
        if (overlayViewPositionHelper != null) {
            return overlayViewPositionHelper.getVerticalWeight();
        }
        return 0.0f;
    }

    public final void inflateDocumentDetectionTick(final Function0<Unit> onAnimationFinish) {
        Intrinsics.checkNotNullParameter(onAnimationFinish, "onAnimationFinish");
        updateTickIconColor();
        OverlayViewPositionHelper overlayViewPositionHelper = this.overlayViewPositionHelper;
        if (overlayViewPositionHelper == null) {
            throw new IllegalArgumentException("Required value was null.".toString());
        }
        RectF visibleCaptureRect = overlayViewPositionHelper.getOverlayMetrics().getVisibleCaptureRect();
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        int iDimenInt = ContextUtilsKt.dimenInt(context, R.dimen.onfido_doc_capture_tick_size);
        FrameLayout tickContainer = this.binding.tickContainer;
        Intrinsics.checkNotNullExpressionValue(tickContainer, "tickContainer");
        ViewGroup.LayoutParams layoutParams = tickContainer.getLayoutParams();
        if (layoutParams == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup.LayoutParams");
        }
        layoutParams.width = iDimenInt;
        layoutParams.height = iDimenInt;
        this.binding.tickContainer.setY(visibleCaptureRect.top + ((visibleCaptureRect.height() - layoutParams.height) / 2.0f));
        tickContainer.setLayoutParams(layoutParams);
        FrameLayout frameLayout = this.binding.tickContainer;
        frameLayout.setAlpha(0.15f);
        frameLayout.animate().alpha(1.0f).setDuration(300L).setListener(new AnimatorListener() { // from class: com.onfido.android.sdk.capture.ui.camera.OverlayView$inflateDocumentDetectionTick$2$1
            @Override // com.onfido.android.sdk.capture.utils.listeners.AnimatorListener, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                AnimatorListener.DefaultImpls.onAnimationCancel(this, animator);
            }

            @Override // com.onfido.android.sdk.capture.utils.listeners.AnimatorListener, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
                onAnimationFinish.invoke();
            }

            @Override // com.onfido.android.sdk.capture.utils.listeners.AnimatorListener, android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
                AnimatorListener.DefaultImpls.onAnimationRepeat(this, animator);
            }

            @Override // com.onfido.android.sdk.capture.utils.listeners.AnimatorListener, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
                FrameLayout tickContainer2 = this.this$0.binding.tickContainer;
                Intrinsics.checkNotNullExpressionValue(tickContainer2, "tickContainer");
                tickContainer2.setVisibility(0);
            }
        });
    }

    public final void onDocumentVideoRecordFinished() {
        updateConfirmationIconColor(R.attr.onfidoColorDocumentOvalSuccess, R.attr.onfidoColorDocumentTickSuccess);
        this.animations.animatePaintColorChange(R.attr.onfidoColorDocumentOverlayStrokeRecording, R.attr.onfidoColorDocumentOverlayStrokeSuccess, getDocumentOverlayStrokePaint(), 300L);
    }

    public final void onDocumentVideoRecordStarted() {
        this.animations.animatePaintColorChange(R.attr.onfidoColorDocumentOverlayStroke, R.attr.onfidoColorDocumentOverlayStrokeRecording, getDocumentOverlayStrokePaint(), 300L);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        super.onDraw(canvas);
        canvas.drawColor(this.colorOutsideOverlay);
        this.overlayCleanerPaint.setStyle(Paint.Style.FILL);
        this.overlayCleanerPaint.setXfermode(this.captureAreaMode);
        this.overlayCleanerPaint.setColor(this.captureAreaColor);
        OverlayViewPositionHelper overlayViewPositionHelper = this.overlayViewPositionHelper;
        if (overlayViewPositionHelper != null) {
            drawCaptureOverlay(canvas, overlayViewPositionHelper.getOverlayMetrics().getVisibleCaptureRect());
        }
    }

    public final void onSingleFrameAutoCaptured(Function0<Unit> onAnimationFinish) {
        Intrinsics.checkNotNullParameter(onAnimationFinish, "onAnimationFinish");
        inflateDocumentDetectionTick(onAnimationFinish);
        this.animations.animatePaintColorChange(R.attr.onfidoColorOverlayStroke, R.attr.onfidoColorDocumentOverlayStrokeSuccess, getDocumentOverlayStrokePaint(), 300L);
    }

    public final void paintCaptureArea() {
        Paint paint = this.overlayCleanerPaint;
        Paint.Style style = Paint.Style.FILL;
        paint.setStyle(style);
        this.overlayCleanerPaint.setXfermode(this.captureAreaMode);
        this.overlayCleanerPaint.setColor(this.captureAreaColor);
        this.overlayCleanerPaint.reset();
        this.captureAreaColor = this.colorOutsideOverlay;
        this.captureAreaMode = new PorterDuffXfermode(PorterDuff.Mode.SRC);
        getVideoStrokePaint().setPathEffect(null);
        getVideoStrokePaint().setStrokeWidth(this.defaultStrokeWidth);
        this.overlayCleanerPaint.setStyle(style);
        Paint videoStrokePaint = getVideoStrokePaint();
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        videoStrokePaint.setColor(ContextUtilsKt.colorFromAttr(context, R.attr.onfidoColorLivenessFaceDetectedStroke));
        invalidate();
    }

    public final void resetDocumentOverlay() {
        FrameLayout tickContainer = this.binding.tickContainer;
        Intrinsics.checkNotNullExpressionValue(tickContainer, "tickContainer");
        tickContainer.setVisibility(8);
        this.captureAreaColor = 0;
        this.captureAreaMode = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);
        Paint documentOverlayStrokePaint = getDocumentOverlayStrokePaint();
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        documentOverlayStrokePaint.setColor(ContextUtilsKt.colorFromAttr(context, R.attr.onfidoColorDocumentOverlayStroke));
        Paint documentOverlayStrokePaint2 = getDocumentOverlayStrokePaint();
        Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
        documentOverlayStrokePaint2.setStrokeWidth(ContextUtilsKt.dimenInt(r1, R.dimen.onfido_doc_capture_overlay_stroke_width));
        invalidate();
    }

    public final void resetFaceDetectedState(boolean withAnimation, boolean shouldClearOverlay, boolean shouldResetOvalStrokeWidth) {
        final Function0<Unit> function0 = new Function0<Unit>() { // from class: com.onfido.android.sdk.capture.ui.camera.OverlayView$resetFaceDetectedState$collapseTickContainer$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                FrameLayout tickContainer = this.this$0.binding.tickContainer;
                Intrinsics.checkNotNullExpressionValue(tickContainer, "tickContainer");
                ViewGroup.LayoutParams layoutParams = tickContainer.getLayoutParams();
                if (layoutParams == null) {
                    throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup.LayoutParams");
                }
                layoutParams.width = 0;
                layoutParams.height = 0;
                tickContainer.setLayoutParams(layoutParams);
            }
        };
        if (withAnimation) {
            FrameLayout tickContainer = this.binding.tickContainer;
            Intrinsics.checkNotNullExpressionValue(tickContainer, "tickContainer");
            ViewExtensionsKt.alphaAnimator$default(tickContainer, 0.0f, 0L, 0L, 6, null).withEndAction(new Runnable() { // from class: com.onfido.android.sdk.capture.ui.camera.OverlayView$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    OverlayView.resetFaceDetectedState$lambda$11(function0, this);
                }
            });
        } else {
            function0.invoke();
        }
        if (shouldResetOvalStrokeWidth) {
            getVideoStrokePaint().setStrokeWidth(this.defaultStrokeWidth);
        }
        if (shouldClearOverlay) {
            this.captureAreaMode = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);
            this.captureAreaColor = 0;
        }
        int i = shouldClearOverlay ? R.attr.onfidoColorOverlayStroke : R.attr.onfidoColorLivenessFaceDetectedStroke;
        Paint videoStrokePaint = getVideoStrokePaint();
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        videoStrokePaint.setColor(ContextUtilsKt.colorFromAttr(context, i));
        invalidate();
    }

    public final void setBigHorizontalWeight$onfido_capture_sdk_core_release(float f) {
        this.bigHorizontalWeight.setValue(this, $$delegatedProperties[1], Float.valueOf(f));
    }

    public final void setColorOutsideOverlay(int color, boolean animate) {
        if (animate) {
            this.animations.animateBackgroundColor(this.colorOutsideOverlay, color, 300L, new Function1<Integer, Unit>() { // from class: com.onfido.android.sdk.capture.ui.camera.OverlayView.setColorOutsideOverlay.1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                    invoke(num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(int i) {
                    OverlayView.this.colorOutsideOverlay = i;
                }
            });
        } else {
            this.colorOutsideOverlay = color;
        }
    }

    public final void setIsProofOfAddress(boolean isProofOfAddress) {
        this.isProofOfAddress = isProofOfAddress;
    }

    public final void setListener(Listener listener) {
        OverlayViewPositionHelper overlayViewPositionHelper = this.overlayViewPositionHelper;
        if (overlayViewPositionHelper == null) {
            return;
        }
        overlayViewPositionHelper.setListener(listener);
    }

    public final void setUp(CaptureType captureType, Listener listener) {
        Intrinsics.checkNotNullParameter(captureType, "captureType");
        OverlayViewHorizontalWeights overlayViewHorizontalWeights = new OverlayViewHorizontalWeights(getBigHorizontalWeight$onfido_capture_sdk_core_release(), getSmallHorizontalWeight(), getVisibleHorizontalWeight());
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        this.overlayViewPositionHelper = new OverlayViewPositionHelper(context, getAspectRatio(), overlayViewHorizontalWeights, captureType, listener);
        if (getWidth() == 0 || getHeight() == 0) {
            Intrinsics.checkNotNullExpressionValue(OneShotPreDrawListener.add(this, new Runnable() { // from class: com.onfido.android.sdk.capture.ui.camera.OverlayView$setUp$$inlined$doOnPreDraw$1
                @Override // java.lang.Runnable
                public final void run() {
                    Rect rectCanvasRect = this.canvasRect(this);
                    OverlayViewPositionHelper overlayViewPositionHelper = this.overlayViewPositionHelper;
                    if (overlayViewPositionHelper != null) {
                        overlayViewPositionHelper.onViewLaidOut(rectCanvasRect, this.isProofOfAddress);
                    }
                }
            }), "View.doOnPreDraw(\n    cr…dd(this) { action(this) }");
            return;
        }
        Rect rectCanvasRect = canvasRect(this);
        OverlayViewPositionHelper overlayViewPositionHelper = this.overlayViewPositionHelper;
        if (overlayViewPositionHelper != null) {
            overlayViewPositionHelper.onViewLaidOut(rectCanvasRect, this.isProofOfAddress);
        }
    }

    public final void showFaceConfirmationTick(Function0<Unit> onAnimationFinish) {
        Intrinsics.checkNotNullParameter(onAnimationFinish, "onAnimationFinish");
        getVideoStrokePaint().setPathEffect(null);
        Paint videoStrokePaint = getVideoStrokePaint();
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        videoStrokePaint.setColor(ContextUtilsKt.colorFromAttr(context, R.attr.onfidoColorDocumentOverlayStrokeSuccess));
        updateTickIconColor();
        growOval(onAnimationFinish, getVideoStrokePaint());
    }

    public final void switchConfirmationMode(boolean isConfirmationMode) {
        setVisibleHorizontalWeight(isConfirmationMode ? getBigHorizontalWeight$onfido_capture_sdk_core_release() : getSmallHorizontalWeight());
        OverlayViewPositionHelper overlayViewPositionHelper = this.overlayViewPositionHelper;
        if (overlayViewPositionHelper != null) {
            overlayViewPositionHelper.updateVisibleHorizontalWeight(getVisibleHorizontalWeight(), this.isProofOfAddress);
        }
        if (isConfirmationMode) {
            resetDocumentOverlay();
        }
        invalidate();
        FrameLayout tickContainer = this.binding.tickContainer;
        Intrinsics.checkNotNullExpressionValue(tickContainer, "tickContainer");
        tickContainer.setVisibility(8);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OverlayView(Context context, AttributeSet attrs) {
        super(context, attrs, R.styleable.OnfidoOverlayViewStyle_onfidoFaceOverlayViewStyle);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        OnfidoOverlayViewBinding onfidoOverlayViewBindingInflate = OnfidoOverlayViewBinding.inflate(LayoutInflater.from(getContext()), this, true);
        Intrinsics.checkNotNullExpressionValue(onfidoOverlayViewBindingInflate, "inflate(...)");
        this.binding = onfidoOverlayViewBindingInflate;
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        this.overlayCleanerPaint = paint;
        this.borderStrokeWidth = LazyKt.lazy(new Function0<Float>() { // from class: com.onfido.android.sdk.capture.ui.camera.OverlayView$borderStrokeWidth$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Float invoke() {
                return Float.valueOf(this.this$0.getContext().getResources().getDimension(R.dimen.onfido_doc_capture_overlay_stroke_width));
            }
        });
        this.documentOverlayStrokePaint = LazyKt.lazy(new Function0<Paint>() { // from class: com.onfido.android.sdk.capture.ui.camera.OverlayView$documentOverlayStrokePaint$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Paint invoke() {
                Paint paint2 = new Paint();
                OverlayView overlayView = this.this$0;
                paint2.setAntiAlias(true);
                Context context2 = overlayView.getContext();
                Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
                paint2.setColor(ContextUtilsKt.colorFromAttr(context2, R.attr.onfidoColorDocumentOverlayStroke));
                paint2.setStrokeWidth(overlayView.getBorderStrokeWidth());
                paint2.setStyle(Paint.Style.STROKE);
                return paint2;
            }
        });
        this.faceStrokePaint = LazyKt.lazy(new Function0<Paint>() { // from class: com.onfido.android.sdk.capture.ui.camera.OverlayView$faceStrokePaint$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Paint invoke() {
                Paint paint2 = new Paint();
                Context context2 = this.this$0.getContext();
                Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
                paint2.setColor(ContextUtilsKt.colorFromAttr(context2, R.attr.onfidoColorContentMainDark));
                paint2.setStyle(Paint.Style.STROKE);
                paint2.setAntiAlias(true);
                return paint2;
            }
        });
        this.ovalDashLength = LazyKt.lazy(new Function0<Float>() { // from class: com.onfido.android.sdk.capture.ui.camera.OverlayView$ovalDashLength$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Float invoke() {
                return Float.valueOf(this.this$0.getResources().getDimensionPixelSize(R.dimen.onfido_liveness_face_detection_oval_dash_length));
            }
        });
        this.videoStrokePaint = LazyKt.lazy(new Function0<Paint>() { // from class: com.onfido.android.sdk.capture.ui.camera.OverlayView$videoStrokePaint$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Paint invoke() {
                Paint paint2 = new Paint();
                OverlayView overlayView = this.this$0;
                Context context2 = overlayView.getContext();
                Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
                paint2.setColor(ContextUtilsKt.colorFromAttr(context2, R.attr.onfidoColorContentMainDark));
                paint2.setStyle(Paint.Style.STROKE);
                Intrinsics.checkNotNullExpressionValue(overlayView.getContext(), "getContext(...)");
                paint2.setStrokeWidth(ContextUtilsKt.dimenInt(r2, R.dimen.onfido_liveness_face_detected_oval_stroke_width));
                paint2.setAntiAlias(true);
                paint2.setPathEffect(new DashPathEffect(new float[]{overlayView.getOvalDashLength(), overlayView.getOvalDashLength()}, 0.0f));
                return paint2;
            }
        });
        this.rectangleRadius = getResources().getDimensionPixelOffset(R.dimen.onfido_capture_rectangle_radius);
        this.rectangleBorderRadius = getResources().getDimensionPixelOffset(R.dimen.onfido_capture_rectangle_border_radius);
        this.captureAreaMode = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);
        Delegates delegates = Delegates.INSTANCE;
        this.aspectRatio = delegates.notNull();
        this.bigHorizontalWeight = delegates.notNull();
        this.smallHorizontalWeight = delegates.notNull();
        this.visibleHorizontalWeight = delegates.notNull();
        this.type = delegates.notNull();
        this.animations = new OverlayViewAnimations(this);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.OnfidoOverlayView);
        Intrinsics.checkNotNullExpressionValue(typedArrayObtainStyledAttributes, "obtainStyledAttributes(...)");
        try {
            setType(OverlayType.INSTANCE.fromIntValue(typedArrayObtainStyledAttributes.getInt(R.styleable.OnfidoOverlayView_onfidoOverlayType, 0)));
            setAspectRatio(typedArrayObtainStyledAttributes.getFloat(R.styleable.OnfidoOverlayView_onfidoOverlayAspectRatio, 1.0f));
            setBigHorizontalWeight$onfido_capture_sdk_core_release(typedArrayObtainStyledAttributes.getFloat(R.styleable.OnfidoOverlayView_onfidoOverlayBigHorizontalWeight, 1.0f));
            setSmallHorizontalWeight(typedArrayObtainStyledAttributes.getFloat(R.styleable.OnfidoOverlayView_onfidoOverlaySmallHorizontalWeight, typedArrayObtainStyledAttributes.getFloat(R.styleable.OnfidoOverlayView_onfidoOverlayBigHorizontalWeight, 1.0f)));
            setVisibleHorizontalWeight(getSmallHorizontalWeight());
            this.defaultStrokeWidth = typedArrayObtainStyledAttributes.getDimension(R.styleable.OnfidoOverlayView_onfidoOverlayStrokeWidth, 1.0f);
            typedArrayObtainStyledAttributes.recycle();
            setBackgroundColor(0);
            setLayerType(1, null);
        } catch (Throwable th) {
            typedArrayObtainStyledAttributes.recycle();
            throw th;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OverlayView(Context context, AttributeSet attrs, int i) {
        super(context, attrs, i);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        OnfidoOverlayViewBinding onfidoOverlayViewBindingInflate = OnfidoOverlayViewBinding.inflate(LayoutInflater.from(getContext()), this, true);
        Intrinsics.checkNotNullExpressionValue(onfidoOverlayViewBindingInflate, "inflate(...)");
        this.binding = onfidoOverlayViewBindingInflate;
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        this.overlayCleanerPaint = paint;
        this.borderStrokeWidth = LazyKt.lazy(new Function0<Float>() { // from class: com.onfido.android.sdk.capture.ui.camera.OverlayView$borderStrokeWidth$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Float invoke() {
                return Float.valueOf(this.this$0.getContext().getResources().getDimension(R.dimen.onfido_doc_capture_overlay_stroke_width));
            }
        });
        this.documentOverlayStrokePaint = LazyKt.lazy(new Function0<Paint>() { // from class: com.onfido.android.sdk.capture.ui.camera.OverlayView$documentOverlayStrokePaint$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Paint invoke() {
                Paint paint2 = new Paint();
                OverlayView overlayView = this.this$0;
                paint2.setAntiAlias(true);
                Context context2 = overlayView.getContext();
                Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
                paint2.setColor(ContextUtilsKt.colorFromAttr(context2, R.attr.onfidoColorDocumentOverlayStroke));
                paint2.setStrokeWidth(overlayView.getBorderStrokeWidth());
                paint2.setStyle(Paint.Style.STROKE);
                return paint2;
            }
        });
        this.faceStrokePaint = LazyKt.lazy(new Function0<Paint>() { // from class: com.onfido.android.sdk.capture.ui.camera.OverlayView$faceStrokePaint$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Paint invoke() {
                Paint paint2 = new Paint();
                Context context2 = this.this$0.getContext();
                Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
                paint2.setColor(ContextUtilsKt.colorFromAttr(context2, R.attr.onfidoColorContentMainDark));
                paint2.setStyle(Paint.Style.STROKE);
                paint2.setAntiAlias(true);
                return paint2;
            }
        });
        this.ovalDashLength = LazyKt.lazy(new Function0<Float>() { // from class: com.onfido.android.sdk.capture.ui.camera.OverlayView$ovalDashLength$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Float invoke() {
                return Float.valueOf(this.this$0.getResources().getDimensionPixelSize(R.dimen.onfido_liveness_face_detection_oval_dash_length));
            }
        });
        this.videoStrokePaint = LazyKt.lazy(new Function0<Paint>() { // from class: com.onfido.android.sdk.capture.ui.camera.OverlayView$videoStrokePaint$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Paint invoke() {
                Paint paint2 = new Paint();
                OverlayView overlayView = this.this$0;
                Context context2 = overlayView.getContext();
                Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
                paint2.setColor(ContextUtilsKt.colorFromAttr(context2, R.attr.onfidoColorContentMainDark));
                paint2.setStyle(Paint.Style.STROKE);
                Intrinsics.checkNotNullExpressionValue(overlayView.getContext(), "getContext(...)");
                paint2.setStrokeWidth(ContextUtilsKt.dimenInt(r2, R.dimen.onfido_liveness_face_detected_oval_stroke_width));
                paint2.setAntiAlias(true);
                paint2.setPathEffect(new DashPathEffect(new float[]{overlayView.getOvalDashLength(), overlayView.getOvalDashLength()}, 0.0f));
                return paint2;
            }
        });
        this.rectangleRadius = getResources().getDimensionPixelOffset(R.dimen.onfido_capture_rectangle_radius);
        this.rectangleBorderRadius = getResources().getDimensionPixelOffset(R.dimen.onfido_capture_rectangle_border_radius);
        this.captureAreaMode = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);
        Delegates delegates = Delegates.INSTANCE;
        this.aspectRatio = delegates.notNull();
        this.bigHorizontalWeight = delegates.notNull();
        this.smallHorizontalWeight = delegates.notNull();
        this.visibleHorizontalWeight = delegates.notNull();
        this.type = delegates.notNull();
        this.animations = new OverlayViewAnimations(this);
        throw new IllegalStateException("Style not supported here.".toString());
    }
}
