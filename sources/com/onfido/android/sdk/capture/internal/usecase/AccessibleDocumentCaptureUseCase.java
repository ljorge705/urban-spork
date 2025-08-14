package com.onfido.android.sdk.capture.internal.usecase;

import android.content.Context;
import com.clevertap.android.sdk.Constants;
import com.facebook.react.uimanager.ViewProps;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.detector.rectangle.RectDetectionResult;
import com.onfido.android.sdk.capture.internal.service.VibratorService;
import com.onfido.android.sdk.capture.internal.usecase.AccessibleDocumentCaptureUseCase;
import com.onfido.android.sdk.capture.internal.usecase.AccessibleDocumentCaptureUseCaseResult;
import com.onfido.android.sdk.capture.internal.usecase.DocumentPosition;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.android.sdk.capture.native_detector.NativeDetector;
import com.onfido.android.sdk.capture.ui.camera.DocumentDetectionFrame;
import com.onfido.android.sdk.capture.utils.StringExtensionsKt;
import com.onfido.javax.inject.Inject;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Timed;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;

@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0017\u0018\u0000 $2\u00020\u0001:\u0003#$%B1\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\b\u0001\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u0016\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0012J\u0016\u0010\u0012\u001a\u00020\u00132\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0012J!\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u00152\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u0015H\u0010¢\u0006\u0002\b\u0019J\f\u0010\u001a\u001a\u00020\u001b*\u00020\u0011H\u0012J\u0018\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015*\b\u0012\u0004\u0012\u00020\u00110\u0015H\u0012J\u0018\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015*\b\u0012\u0004\u0012\u00020\u00110\u0015H\u0012J\u0014\u0010\u001e\u001a\u00020\u0013*\u00020\u00132\u0006\u0010\u001f\u001a\u00020\u0013H\u0012J%\u0010 \u001a\u0015\u0012\f\u0012\n !*\u0004\u0018\u00010\u00110\u00110\u0015¢\u0006\u0002\b\"*\b\u0012\u0004\u0012\u00020\u00110\u0015H\u0012R\u000e\u0010\n\u001a\u00020\u000bX\u0092\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0092\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0092\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0092\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0092\u0004¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/usecase/AccessibleDocumentCaptureUseCase;", "", "documentPositionUseCase", "Lcom/onfido/android/sdk/capture/internal/usecase/DocumentPositionUseCase;", "schedulersProvider", "Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;", "nativeDetector", "Lcom/onfido/android/sdk/capture/native_detector/NativeDetector;", "vibratorService", "Lcom/onfido/android/sdk/capture/internal/service/VibratorService;", "context", "Landroid/content/Context;", "(Lcom/onfido/android/sdk/capture/internal/usecase/DocumentPositionUseCase;Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;Lcom/onfido/android/sdk/capture/native_detector/NativeDetector;Lcom/onfido/android/sdk/capture/internal/service/VibratorService;Landroid/content/Context;)V", "mapToAutocaptureResult", "Lcom/onfido/android/sdk/capture/internal/usecase/AccessibleDocumentCaptureUseCase$AutocaptureResult;", ViewProps.POSITION, "Lio/reactivex/rxjava3/schedulers/Timed;", "Lcom/onfido/android/sdk/capture/internal/usecase/DocumentPosition;", "mapToPositionResult", "Lcom/onfido/android/sdk/capture/internal/usecase/AccessibleDocumentCaptureUseCase$PositionResult;", "observeAccessibilityCapture", "Lio/reactivex/rxjava3/core/Observable;", "Lcom/onfido/android/sdk/capture/internal/usecase/AccessibleDocumentCaptureUseCaseResult;", "rectDetectionObservable", "Lcom/onfido/android/sdk/capture/detector/rectangle/RectDetectionResult;", "observeAccessibilityCapture$onfido_capture_sdk_core_release", "getAccessibilityTextDuration", "", "observeAutoCapture", "observePosition", "validateWith", "previousResult", "vibrateWhenDocumentNormal", "kotlin.jvm.PlatformType", "Lio/reactivex/rxjava3/annotations/NonNull;", "AutocaptureResult", "Companion", "PositionResult", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public class AccessibleDocumentCaptureUseCase {

    @Deprecated
    public static final long ACCESSIBILITY_AUTO_CAPTURE_DELAY = 2000;
    private static final Companion Companion = new Companion(null);

    @Deprecated
    public static final long FRAME_SAMPLING_PERIOD_MS = 350;

    @Deprecated
    public static final long READ_ACCESSIBILITY_REPEAT_DELAY = 4000;
    private final Context context;
    private final DocumentPositionUseCase documentPositionUseCase;
    private final NativeDetector nativeDetector;
    private final SchedulersProvider schedulersProvider;
    private final VibratorService vibratorService;

    /* JADX INFO: Access modifiers changed from: private */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0007HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00072\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0019"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/usecase/AccessibleDocumentCaptureUseCase$AutocaptureResult;", "", "timeStamp", "", "documentPosition", "Lcom/onfido/android/sdk/capture/internal/usecase/DocumentPosition;", "shouldAutocapture", "", "(JLcom/onfido/android/sdk/capture/internal/usecase/DocumentPosition;Z)V", "getDocumentPosition", "()Lcom/onfido/android/sdk/capture/internal/usecase/DocumentPosition;", "getShouldAutocapture", "()Z", "getTimeStamp", "()J", "component1", "component2", "component3", Constants.COPY_TYPE, "equals", "other", "hashCode", "", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    static final /* data */ class AutocaptureResult {
        private final DocumentPosition documentPosition;
        private final boolean shouldAutocapture;
        private final long timeStamp;

        public AutocaptureResult(long j, DocumentPosition documentPosition, boolean z) {
            Intrinsics.checkNotNullParameter(documentPosition, "documentPosition");
            this.timeStamp = j;
            this.documentPosition = documentPosition;
            this.shouldAutocapture = z;
        }

        public static /* synthetic */ AutocaptureResult copy$default(AutocaptureResult autocaptureResult, long j, DocumentPosition documentPosition, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                j = autocaptureResult.timeStamp;
            }
            if ((i & 2) != 0) {
                documentPosition = autocaptureResult.documentPosition;
            }
            if ((i & 4) != 0) {
                z = autocaptureResult.shouldAutocapture;
            }
            return autocaptureResult.copy(j, documentPosition, z);
        }

        /* renamed from: component1, reason: from getter */
        public final long getTimeStamp() {
            return this.timeStamp;
        }

        /* renamed from: component2, reason: from getter */
        public final DocumentPosition getDocumentPosition() {
            return this.documentPosition;
        }

        /* renamed from: component3, reason: from getter */
        public final boolean getShouldAutocapture() {
            return this.shouldAutocapture;
        }

        public final AutocaptureResult copy(long timeStamp, DocumentPosition documentPosition, boolean shouldAutocapture) {
            Intrinsics.checkNotNullParameter(documentPosition, "documentPosition");
            return new AutocaptureResult(timeStamp, documentPosition, shouldAutocapture);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof AutocaptureResult)) {
                return false;
            }
            AutocaptureResult autocaptureResult = (AutocaptureResult) other;
            return this.timeStamp == autocaptureResult.timeStamp && Intrinsics.areEqual(this.documentPosition, autocaptureResult.documentPosition) && this.shouldAutocapture == autocaptureResult.shouldAutocapture;
        }

        public final DocumentPosition getDocumentPosition() {
            return this.documentPosition;
        }

        public final boolean getShouldAutocapture() {
            return this.shouldAutocapture;
        }

        public final long getTimeStamp() {
            return this.timeStamp;
        }

        public int hashCode() {
            return (((Long.hashCode(this.timeStamp) * 31) + this.documentPosition.hashCode()) * 31) + Boolean.hashCode(this.shouldAutocapture);
        }

        public String toString() {
            return "AutocaptureResult(timeStamp=" + this.timeStamp + ", documentPosition=" + this.documentPosition + ", shouldAutocapture=" + this.shouldAutocapture + ')';
        }

        public /* synthetic */ AutocaptureResult(long j, DocumentPosition documentPosition, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(j, documentPosition, (i & 4) != 0 ? false : z);
        }
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0010\u0010\u000b\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0012\u0010\f\u001a\u00020\r*\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/usecase/AccessibleDocumentCaptureUseCase$Companion;", "", "()V", "ACCESSIBILITY_AUTO_CAPTURE_DELAY", "", "FRAME_SAMPLING_PERIOD_MS", "READ_ACCESSIBILITY_REPEAT_DELAY", "getMainText", "", "documentPosition", "Lcom/onfido/android/sdk/capture/internal/usecase/DocumentPosition;", "getMainTextAccessibility", "shouldAutocapture", "", "Lcom/onfido/android/sdk/capture/internal/usecase/AccessibleDocumentCaptureUseCase$AutocaptureResult;", "previousResult", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class Companion {
        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final int getMainText(DocumentPosition documentPosition) {
            if (Intrinsics.areEqual(documentPosition, DocumentPosition.DocumentNormal.INSTANCE)) {
                return R.string.onfido_doc_capture_header_live_guidance_doc_position_ok;
            }
            if (Intrinsics.areEqual(documentPosition, DocumentPosition.DocumentNotFound.INSTANCE)) {
                return R.string.onfido_doc_capture_header_live_guidance_no_doc;
            }
            if (Intrinsics.areEqual(documentPosition, DocumentPosition.DocumentTooBottom.INSTANCE)) {
                return R.string.onfido_doc_capture_header_live_guidance_doc_too_low;
            }
            if (Intrinsics.areEqual(documentPosition, DocumentPosition.DocumentClose.INSTANCE)) {
                return R.string.onfido_doc_capture_header_live_guidance_distance_close;
            }
            if (Intrinsics.areEqual(documentPosition, DocumentPosition.DocumentFar.INSTANCE)) {
                return R.string.onfido_doc_capture_header_live_guidance_distance_far;
            }
            if (Intrinsics.areEqual(documentPosition, DocumentPosition.DocumentTooLeft.INSTANCE)) {
                return R.string.onfido_doc_capture_header_live_guidance_doc_too_left;
            }
            if (Intrinsics.areEqual(documentPosition, DocumentPosition.DocumentTooRight.INSTANCE)) {
                return R.string.onfido_doc_capture_header_live_guidance_doc_too_right;
            }
            if (Intrinsics.areEqual(documentPosition, DocumentPosition.DocumentTooTop.INSTANCE)) {
                return R.string.onfido_doc_capture_header_live_guidance_doc_too_high;
            }
            if (Intrinsics.areEqual(documentPosition, DocumentPosition.DocumentBottom.INSTANCE)) {
                return R.string.onfido_doc_capture_header_live_guidance_doc_slightly_low;
            }
            if (Intrinsics.areEqual(documentPosition, DocumentPosition.DocumentTop.INSTANCE)) {
                return R.string.onfido_doc_capture_header_live_guidance_doc_slightly_high;
            }
            throw new NoWhenBranchMatchedException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final int getMainTextAccessibility(DocumentPosition documentPosition) {
            if (Intrinsics.areEqual(documentPosition, DocumentPosition.DocumentNormal.INSTANCE)) {
                return R.string.onfido_doc_capture_header_live_guidance_doc_position_ok_accessibility;
            }
            if (Intrinsics.areEqual(documentPosition, DocumentPosition.DocumentNotFound.INSTANCE)) {
                return R.string.onfido_doc_capture_header_live_guidance_no_doc_accessibility;
            }
            if (Intrinsics.areEqual(documentPosition, DocumentPosition.DocumentTooBottom.INSTANCE)) {
                return R.string.onfido_doc_capture_header_live_guidance_doc_too_low_accessibility;
            }
            if (Intrinsics.areEqual(documentPosition, DocumentPosition.DocumentClose.INSTANCE)) {
                return R.string.onfido_doc_capture_header_live_guidance_distance_close_accessibility;
            }
            if (Intrinsics.areEqual(documentPosition, DocumentPosition.DocumentFar.INSTANCE)) {
                return R.string.onfido_doc_capture_header_live_guidance_distance_far_accessibility;
            }
            if (Intrinsics.areEqual(documentPosition, DocumentPosition.DocumentTooLeft.INSTANCE)) {
                return R.string.onfido_doc_capture_header_live_guidance_doc_too_left_accessibility;
            }
            if (Intrinsics.areEqual(documentPosition, DocumentPosition.DocumentTooRight.INSTANCE)) {
                return R.string.onfido_doc_capture_header_live_guidance_doc_too_right_accessibility;
            }
            if (Intrinsics.areEqual(documentPosition, DocumentPosition.DocumentTooTop.INSTANCE)) {
                return R.string.onfido_doc_capture_header_live_guidance_doc_too_high_accessibility;
            }
            if (Intrinsics.areEqual(documentPosition, DocumentPosition.DocumentBottom.INSTANCE)) {
                return R.string.onfido_doc_capture_header_live_guidance_doc_slightly_low_accessibility;
            }
            if (Intrinsics.areEqual(documentPosition, DocumentPosition.DocumentTop.INSTANCE)) {
                return R.string.onfido_doc_capture_header_live_guidance_doc_slightly_high_accessibility;
            }
            throw new NoWhenBranchMatchedException();
        }

        public final boolean shouldAutocapture(AutocaptureResult autocaptureResult, AutocaptureResult previousResult) {
            Intrinsics.checkNotNullParameter(autocaptureResult, "<this>");
            Intrinsics.checkNotNullParameter(previousResult, "previousResult");
            return autocaptureResult.getTimeStamp() - previousResult.getTimeStamp() >= 2000;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J1\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00072\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000b¨\u0006\u001c"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/usecase/AccessibleDocumentCaptureUseCase$PositionResult;", "", "timeStamp", "", "documentPosition", "Lcom/onfido/android/sdk/capture/internal/usecase/DocumentPosition;", "shouldProcess", "", "accessibilityTextDuration", "(JLcom/onfido/android/sdk/capture/internal/usecase/DocumentPosition;ZJ)V", "getAccessibilityTextDuration", "()J", "getDocumentPosition", "()Lcom/onfido/android/sdk/capture/internal/usecase/DocumentPosition;", "getShouldProcess", "()Z", "getTimeStamp", "component1", "component2", "component3", "component4", Constants.COPY_TYPE, "equals", "other", "hashCode", "", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    static final /* data */ class PositionResult {
        private final long accessibilityTextDuration;
        private final DocumentPosition documentPosition;
        private final boolean shouldProcess;
        private final long timeStamp;

        public PositionResult(long j, DocumentPosition documentPosition, boolean z, long j2) {
            Intrinsics.checkNotNullParameter(documentPosition, "documentPosition");
            this.timeStamp = j;
            this.documentPosition = documentPosition;
            this.shouldProcess = z;
            this.accessibilityTextDuration = j2;
        }

        public static /* synthetic */ PositionResult copy$default(PositionResult positionResult, long j, DocumentPosition documentPosition, boolean z, long j2, int i, Object obj) {
            if ((i & 1) != 0) {
                j = positionResult.timeStamp;
            }
            long j3 = j;
            if ((i & 2) != 0) {
                documentPosition = positionResult.documentPosition;
            }
            DocumentPosition documentPosition2 = documentPosition;
            if ((i & 4) != 0) {
                z = positionResult.shouldProcess;
            }
            boolean z2 = z;
            if ((i & 8) != 0) {
                j2 = positionResult.accessibilityTextDuration;
            }
            return positionResult.copy(j3, documentPosition2, z2, j2);
        }

        /* renamed from: component1, reason: from getter */
        public final long getTimeStamp() {
            return this.timeStamp;
        }

        /* renamed from: component2, reason: from getter */
        public final DocumentPosition getDocumentPosition() {
            return this.documentPosition;
        }

        /* renamed from: component3, reason: from getter */
        public final boolean getShouldProcess() {
            return this.shouldProcess;
        }

        /* renamed from: component4, reason: from getter */
        public final long getAccessibilityTextDuration() {
            return this.accessibilityTextDuration;
        }

        public final PositionResult copy(long timeStamp, DocumentPosition documentPosition, boolean shouldProcess, long accessibilityTextDuration) {
            Intrinsics.checkNotNullParameter(documentPosition, "documentPosition");
            return new PositionResult(timeStamp, documentPosition, shouldProcess, accessibilityTextDuration);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof PositionResult)) {
                return false;
            }
            PositionResult positionResult = (PositionResult) other;
            return this.timeStamp == positionResult.timeStamp && Intrinsics.areEqual(this.documentPosition, positionResult.documentPosition) && this.shouldProcess == positionResult.shouldProcess && this.accessibilityTextDuration == positionResult.accessibilityTextDuration;
        }

        public final long getAccessibilityTextDuration() {
            return this.accessibilityTextDuration;
        }

        public final DocumentPosition getDocumentPosition() {
            return this.documentPosition;
        }

        public final boolean getShouldProcess() {
            return this.shouldProcess;
        }

        public final long getTimeStamp() {
            return this.timeStamp;
        }

        public int hashCode() {
            return (((((Long.hashCode(this.timeStamp) * 31) + this.documentPosition.hashCode()) * 31) + Boolean.hashCode(this.shouldProcess)) * 31) + Long.hashCode(this.accessibilityTextDuration);
        }

        public String toString() {
            return "PositionResult(timeStamp=" + this.timeStamp + ", documentPosition=" + this.documentPosition + ", shouldProcess=" + this.shouldProcess + ", accessibilityTextDuration=" + this.accessibilityTextDuration + ')';
        }

        public /* synthetic */ PositionResult(long j, DocumentPosition documentPosition, boolean z, long j2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(j, documentPosition, (i & 4) != 0 ? true : z, j2);
        }
    }

    @Inject
    public AccessibleDocumentCaptureUseCase(DocumentPositionUseCase documentPositionUseCase, SchedulersProvider schedulersProvider, NativeDetector nativeDetector, VibratorService vibratorService, Context context) {
        Intrinsics.checkNotNullParameter(documentPositionUseCase, "documentPositionUseCase");
        Intrinsics.checkNotNullParameter(schedulersProvider, "schedulersProvider");
        Intrinsics.checkNotNullParameter(nativeDetector, "nativeDetector");
        Intrinsics.checkNotNullParameter(vibratorService, "vibratorService");
        Intrinsics.checkNotNullParameter(context, "context");
        this.documentPositionUseCase = documentPositionUseCase;
        this.schedulersProvider = schedulersProvider;
        this.nativeDetector = nativeDetector;
        this.vibratorService = vibratorService;
        this.context = context;
    }

    private long getAccessibilityTextDuration(DocumentPosition documentPosition) {
        String string = this.context.getString(Companion.getMainTextAccessibility(documentPosition));
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return StringExtensionsKt.readingTimeMilliseconds(string);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AutocaptureResult mapToAutocaptureResult(Timed<DocumentPosition> position) {
        long jTime = position.time();
        DocumentPosition documentPositionValue = position.value();
        Intrinsics.checkNotNullExpressionValue(documentPositionValue, "value(...)");
        return new AutocaptureResult(jTime, documentPositionValue, false, 4, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PositionResult mapToPositionResult(Timed<DocumentPosition> position) {
        long jTime = position.time();
        DocumentPosition documentPositionValue = position.value();
        Intrinsics.checkNotNullExpressionValue(documentPositionValue, "value(...)");
        DocumentPosition documentPositionValue2 = position.value();
        Intrinsics.checkNotNullExpressionValue(documentPositionValue2, "value(...)");
        return new PositionResult(jTime, documentPositionValue, false, getAccessibilityTextDuration(documentPositionValue2), 4, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Observable<AccessibleDocumentCaptureUseCaseResult> observeAutoCapture(Observable<DocumentPosition> observable) {
        Observable observableScan = observable.timestamp(this.schedulersProvider.getTimer()).map(new Function() { // from class: com.onfido.android.sdk.capture.internal.usecase.AccessibleDocumentCaptureUseCase$observeAutoCapture$autocaptureObservable$1
            @Override // io.reactivex.rxjava3.functions.Function
            public final AccessibleDocumentCaptureUseCase.AutocaptureResult apply(Timed<DocumentPosition> p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return this.$tmp0.mapToAutocaptureResult(p0);
            }
        }).scan(new BiFunction() { // from class: com.onfido.android.sdk.capture.internal.usecase.AccessibleDocumentCaptureUseCase$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.functions.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return AccessibleDocumentCaptureUseCase.observeAutoCapture$lambda$0((AccessibleDocumentCaptureUseCase.AutocaptureResult) obj, (AccessibleDocumentCaptureUseCase.AutocaptureResult) obj2);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableScan, "scan(...)");
        Observable<DocumentDetectionFrame> observableSample = this.nativeDetector.getFrameData().sample(350L, TimeUnit.MILLISECONDS, this.schedulersProvider.getTimer());
        final NativeDetector nativeDetector = this.nativeDetector;
        ObservableSource map = observableSample.map(new Function() { // from class: com.onfido.android.sdk.capture.internal.usecase.AccessibleDocumentCaptureUseCase$observeAutoCapture$blurObservable$1
            @Override // io.reactivex.rxjava3.functions.Function
            public final Boolean apply(DocumentDetectionFrame p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return Boolean.valueOf(nativeDetector.isBlurDetected(p0));
            }
        });
        Intrinsics.checkNotNullExpressionValue(map, "map(...)");
        Observable<AccessibleDocumentCaptureUseCaseResult> map2 = Observable.combineLatest(map, observableScan, new BiFunction() { // from class: com.onfido.android.sdk.capture.internal.usecase.AccessibleDocumentCaptureUseCase.observeAutoCapture.1
            @Override // io.reactivex.rxjava3.functions.BiFunction
            public final Pair<Boolean, AutocaptureResult> apply(Boolean p0, AutocaptureResult p1) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                Intrinsics.checkNotNullParameter(p1, "p1");
                return new Pair<>(p0, p1);
            }
        }).filter(new Predicate() { // from class: com.onfido.android.sdk.capture.internal.usecase.AccessibleDocumentCaptureUseCase.observeAutoCapture.2
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Pair<Boolean, AutocaptureResult> pair) {
                Intrinsics.checkNotNullParameter(pair, "<name for destructuring parameter 0>");
                return !pair.component1().booleanValue() && pair.component2().getShouldAutocapture();
            }
        }).map(new Function() { // from class: com.onfido.android.sdk.capture.internal.usecase.AccessibleDocumentCaptureUseCase.observeAutoCapture.3
            @Override // io.reactivex.rxjava3.functions.Function
            public final AccessibleDocumentCaptureUseCaseResult apply(Pair<Boolean, AutocaptureResult> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return AccessibleDocumentCaptureUseCaseResult.AutoCaptured.INSTANCE;
            }
        });
        Intrinsics.checkNotNullExpressionValue(map2, "map(...)");
        return map2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final AutocaptureResult observeAutoCapture$lambda$0(AutocaptureResult previousResult, AutocaptureResult newResult) {
        Intrinsics.checkNotNullParameter(previousResult, "previousResult");
        Intrinsics.checkNotNullParameter(newResult, "newResult");
        DocumentPosition documentPosition = newResult.getDocumentPosition();
        DocumentPosition.DocumentNormal documentNormal = DocumentPosition.DocumentNormal.INSTANCE;
        return (Intrinsics.areEqual(documentPosition, documentNormal) && Intrinsics.areEqual(previousResult.getDocumentPosition(), documentNormal)) ? AutocaptureResult.copy$default(newResult, previousResult.getTimeStamp(), null, Companion.shouldAutocapture(newResult, previousResult), 2, null) : newResult;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Observable<AccessibleDocumentCaptureUseCaseResult> observePosition(Observable<DocumentPosition> observable) {
        String string = this.context.getString(R.string.onfido_doc_capture_frame_accessibility_pp_auto);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        Observable observableScan = observable.skip(StringExtensionsKt.readingTimeMilliseconds(string), TimeUnit.MILLISECONDS, this.schedulersProvider.getTimer()).timestamp(this.schedulersProvider.getTimer()).map(new Function() { // from class: com.onfido.android.sdk.capture.internal.usecase.AccessibleDocumentCaptureUseCase.observePosition.1
            @Override // io.reactivex.rxjava3.functions.Function
            public final PositionResult apply(Timed<DocumentPosition> p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return AccessibleDocumentCaptureUseCase.this.mapToPositionResult(p0);
            }
        }).scan(new BiFunction() { // from class: com.onfido.android.sdk.capture.internal.usecase.AccessibleDocumentCaptureUseCase$$ExternalSyntheticLambda1
            @Override // io.reactivex.rxjava3.functions.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return AccessibleDocumentCaptureUseCase.observePosition$lambda$1(this.f$0, (AccessibleDocumentCaptureUseCase.PositionResult) obj, (AccessibleDocumentCaptureUseCase.PositionResult) obj2);
            }
        });
        final C05843 c05843 = new PropertyReference1Impl() { // from class: com.onfido.android.sdk.capture.internal.usecase.AccessibleDocumentCaptureUseCase.observePosition.3
            @Override // kotlin.jvm.internal.PropertyReference1Impl, kotlin.reflect.KProperty1
            public Object get(Object obj) {
                return Boolean.valueOf(((PositionResult) obj).getShouldProcess());
            }
        };
        Observable observableFilter = observableScan.filter(new Predicate(c05843) { // from class: com.onfido.android.sdk.capture.internal.usecase.AccessibleDocumentCaptureUseCase$sam$io_reactivex_rxjava3_functions_Predicate$0
            private final /* synthetic */ Function1 function;

            {
                Intrinsics.checkNotNullParameter(c05843, "function");
                this.function = c05843;
            }

            @Override // io.reactivex.rxjava3.functions.Predicate
            public final /* synthetic */ boolean test(Object obj) {
                return ((Boolean) this.function.invoke(obj)).booleanValue();
            }
        });
        final AnonymousClass4 anonymousClass4 = new PropertyReference1Impl() { // from class: com.onfido.android.sdk.capture.internal.usecase.AccessibleDocumentCaptureUseCase.observePosition.4
            @Override // kotlin.jvm.internal.PropertyReference1Impl, kotlin.reflect.KProperty1
            public Object get(Object obj) {
                return ((PositionResult) obj).getDocumentPosition();
            }
        };
        Observable<AccessibleDocumentCaptureUseCaseResult> map = observableFilter.map(new Function(anonymousClass4) { // from class: com.onfido.android.sdk.capture.internal.usecase.AccessibleDocumentCaptureUseCase$sam$io_reactivex_rxjava3_functions_Function$0
            private final /* synthetic */ Function1 function;

            {
                Intrinsics.checkNotNullParameter(anonymousClass4, "function");
                this.function = anonymousClass4;
            }

            @Override // io.reactivex.rxjava3.functions.Function
            public final /* synthetic */ Object apply(Object obj) {
                return this.function.invoke(obj);
            }
        }).publish(new Function() { // from class: com.onfido.android.sdk.capture.internal.usecase.AccessibleDocumentCaptureUseCase.observePosition.5
            @Override // io.reactivex.rxjava3.functions.Function
            public final ObservableSource<DocumentPosition> apply(Observable<DocumentPosition> shared) {
                Intrinsics.checkNotNullParameter(shared, "shared");
                return Observable.mergeArray(shared, AccessibleDocumentCaptureUseCase.this.vibrateWhenDocumentNormal(shared));
            }
        }).map(new Function() { // from class: com.onfido.android.sdk.capture.internal.usecase.AccessibleDocumentCaptureUseCase.observePosition.6
            @Override // io.reactivex.rxjava3.functions.Function
            public final AccessibleDocumentCaptureUseCaseResult apply(DocumentPosition documentPosition) {
                Companion companion = AccessibleDocumentCaptureUseCase.Companion;
                Intrinsics.checkNotNull(documentPosition);
                return new AccessibleDocumentCaptureUseCaseResult.DocumentPositionChanged(companion.getMainText(documentPosition), AccessibleDocumentCaptureUseCase.Companion.getMainTextAccessibility(documentPosition));
            }
        });
        Intrinsics.checkNotNullExpressionValue(map, "map(...)");
        return map;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final PositionResult observePosition$lambda$1(AccessibleDocumentCaptureUseCase this$0, PositionResult previousResult, PositionResult newResult) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(previousResult, "previousResult");
        Intrinsics.checkNotNullParameter(newResult, "newResult");
        return this$0.validateWith(newResult, previousResult);
    }

    private PositionResult validateWith(PositionResult positionResult, PositionResult positionResult2) {
        long accessibilityTextDuration = positionResult2.getAccessibilityTextDuration() + positionResult2.getTimeStamp();
        return (4000 + accessibilityTextDuration >= positionResult.getTimeStamp() && !(!Intrinsics.areEqual(positionResult.getDocumentPosition(), positionResult2.getDocumentPosition()) && (accessibilityTextDuration > positionResult.getTimeStamp() ? 1 : (accessibilityTextDuration == positionResult.getTimeStamp() ? 0 : -1)) < 0)) ? PositionResult.copy$default(positionResult2, 0L, null, false, 0L, 11, null) : PositionResult.copy$default(positionResult, 0L, null, true, 0L, 11, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Observable<DocumentPosition> vibrateWhenDocumentNormal(Observable<DocumentPosition> observable) {
        Observable<U> observableCast = observable.filter(new Predicate() { // from class: com.onfido.android.sdk.capture.internal.usecase.AccessibleDocumentCaptureUseCase$vibrateWhenDocumentNormal$$inlined$filterIsInstance$1
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it instanceof DocumentPosition.DocumentNormal;
            }
        }).cast(DocumentPosition.DocumentNormal.class);
        Intrinsics.checkNotNullExpressionValue(observableCast, "cast(...)");
        Observable<DocumentPosition> observable2 = observableCast.flatMapCompletable(new Function() { // from class: com.onfido.android.sdk.capture.internal.usecase.AccessibleDocumentCaptureUseCase.vibrateWhenDocumentNormal.1
            @Override // io.reactivex.rxjava3.functions.Function
            public final CompletableSource apply(DocumentPosition.DocumentNormal it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return AccessibleDocumentCaptureUseCase.this.vibratorService.vibrateForSuccess();
            }
        }).toObservable();
        Intrinsics.checkNotNullExpressionValue(observable2, "toObservable(...)");
        return observable2;
    }

    public Observable<AccessibleDocumentCaptureUseCaseResult> observeAccessibilityCapture$onfido_capture_sdk_core_release(Observable<RectDetectionResult> rectDetectionObservable) {
        Intrinsics.checkNotNullParameter(rectDetectionObservable, "rectDetectionObservable");
        Observable<AccessibleDocumentCaptureUseCaseResult> observablePublish = rectDetectionObservable.map(new Function() { // from class: com.onfido.android.sdk.capture.internal.usecase.AccessibleDocumentCaptureUseCase$observeAccessibilityCapture$1
            @Override // io.reactivex.rxjava3.functions.Function
            public final DocumentPosition apply(RectDetectionResult it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return this.this$0.documentPositionUseCase.invoke$onfido_capture_sdk_core_release(it);
            }
        }).publish(new Function() { // from class: com.onfido.android.sdk.capture.internal.usecase.AccessibleDocumentCaptureUseCase$observeAccessibilityCapture$2
            @Override // io.reactivex.rxjava3.functions.Function
            public final ObservableSource<AccessibleDocumentCaptureUseCaseResult> apply(Observable<DocumentPosition> shared) {
                Intrinsics.checkNotNullParameter(shared, "shared");
                return Observable.merge(this.this$0.observePosition(shared), this.this$0.observeAutoCapture(shared));
            }
        });
        Intrinsics.checkNotNullExpressionValue(observablePublish, "publish(...)");
        return observablePublish;
    }
}
