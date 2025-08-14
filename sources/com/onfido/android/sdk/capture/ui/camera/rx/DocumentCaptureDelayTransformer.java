package com.onfido.android.sdk.capture.ui.camera.rx;

import com.clevertap.android.sdk.Constants;
import com.facebook.react.uimanager.ViewProps;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.document.DocumentConfigurationManager;
import com.onfido.android.sdk.capture.internal.usecase.DocumentProcessingUseCase;
import com.onfido.android.sdk.capture.internal.usecase.DocumentProcessingUseCaseResult;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.android.sdk.capture.internal.validation.DocumentProcessingResults;
import com.onfido.android.sdk.capture.native_detector.NativeDetector;
import com.onfido.android.sdk.capture.ui.camera.DocumentDetectionFrame;
import com.onfido.android.sdk.capture.ui.camera.rx.DocumentCaptureDelayTransformer;
import com.onfido.android.sdk.capture.utils.CountryCode;
import com.onfido.api.client.data.DocSide;
import com.onfido.javax.inject.Inject;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.ObservableTransformer;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.rx3.RxConvertKt;

@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\t\b\u0007\u0018\u0000 22\u00020\u0001:\u000223B'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ$\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00160\u00152\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0018H\u0002J,\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00160\u001b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001b2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0018H\u0002J4\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00160\u001b2\u0006\u0010\u001f\u001a\u00020 2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001b2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0018H\u0002J_\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00160\"2\b\b\u0002\u0010#\u001a\u00020\u00182\u0006\u0010$\u001a\u00020\u00182\u0006\u0010\u001f\u001a\u00020 2\b\u0010%\u001a\u0004\u0018\u00010&2\b\u0010'\u001a\u0004\u0018\u00010(2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020+0*2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0018H\u0000¢\u0006\u0002\b,J\u0016\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00160\u001b2\u0006\u0010\u001f\u001a\u00020 H\u0002J\u001a\u0010.\u001a\u00020\u00182\u0006\u0010\u001f\u001a\u00020 2\b\u0010%\u001a\u0004\u0018\u00010&H\u0002J\u0010\u0010/\u001a\u00020\u00182\u0006\u0010\u001f\u001a\u00020 H\u0002J;\u00100\u001a\u000e\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u00160\u00152\u0006\u0010\u001f\u001a\u00020 2\b\u0010%\u001a\u0004\u0018\u00010&2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0018H\u0000¢\u0006\u0002\b1R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R.\u0010\u000b\u001a\u0015\u0012\f\u0012\n \u000e*\u0004\u0018\u00010\r0\r0\f¢\u0006\u0002\b\u000f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00064"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/rx/DocumentCaptureDelayTransformer;", "", "nativeDetector", "Lcom/onfido/android/sdk/capture/native_detector/NativeDetector;", "documentConfigurationManager", "Lcom/onfido/android/sdk/capture/document/DocumentConfigurationManager;", "schedulersProvider", "Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;", "documentProcessingUseCase", "Lcom/onfido/android/sdk/capture/internal/usecase/DocumentProcessingUseCase;", "(Lcom/onfido/android/sdk/capture/native_detector/NativeDetector;Lcom/onfido/android/sdk/capture/document/DocumentConfigurationManager;Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;Lcom/onfido/android/sdk/capture/internal/usecase/DocumentProcessingUseCase;)V", "firstFrameMaybe", "Lio/reactivex/rxjava3/core/Maybe;", "Lcom/onfido/android/sdk/capture/ui/camera/DocumentDetectionFrame;", "kotlin.jvm.PlatformType", "Lio/reactivex/rxjava3/annotations/NonNull;", "getFirstFrameMaybe", "()Lio/reactivex/rxjava3/core/Maybe;", "firstFrameMaybe$delegate", "Lkotlin/Lazy;", "firstFrameDelayTransformer", "Lio/reactivex/rxjava3/core/ObservableTransformer;", "Lcom/onfido/android/sdk/capture/ui/camera/rx/DocumentCaptureDelayTransformer$DocumentCaptureResult;", "isFirstStart", "", "isAccessibilityEnabled", "getAutoCaptureWithNoOverlayDelayObservable", "Lio/reactivex/rxjava3/core/Observable;", "upstream", "Lcom/onfido/android/sdk/capture/internal/validation/DocumentProcessingResults;", "getAutoCaptureWithOverlayDelayObservable", "documentType", "Lcom/onfido/android/sdk/capture/DocumentType;", "getImageProcessingTransformedFlow", "Lkotlinx/coroutines/flow/Flow;", "enableManualFallback", "isEdgeDetectionTimeoutStarted", RemoteConfigConstants.RequestFieldKey.COUNTRY_CODE, "Lcom/onfido/android/sdk/capture/utils/CountryCode;", "documentSide", "Lcom/onfido/api/client/data/DocSide;", "manualFallbackCallback", "Lkotlin/Function0;", "", "getImageProcessingTransformedFlow$onfido_capture_sdk_core_release", "passportDelayTimerFinishedObservable", "shouldAutoCapture", "shouldShowInitialOverlay", ViewProps.TRANSFORM, "transform$onfido_capture_sdk_core_release", "Companion", "DocumentCaptureResult", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DocumentCaptureDelayTransformer {
    private static final long AUTOCAPTURE_NO_OVERLAY_DELAY_MS = 2000;
    private static final Companion Companion = new Companion(null);
    private static final long IMAGE_PROCESSING_START_TIMER_MS = 3000;
    private static final long PASSPORT_OVERLAY_DELAY_MS = 3000;
    private final DocumentConfigurationManager documentConfigurationManager;
    private final DocumentProcessingUseCase documentProcessingUseCase;

    /* renamed from: firstFrameMaybe$delegate, reason: from kotlin metadata */
    private final Lazy firstFrameMaybe;
    private final NativeDetector nativeDetector;
    private final SchedulersProvider schedulersProvider;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/rx/DocumentCaptureDelayTransformer$Companion;", "", "()V", "AUTOCAPTURE_NO_OVERLAY_DELAY_MS", "", "IMAGE_PROCESSING_START_TIMER_MS", "PASSPORT_OVERLAY_DELAY_MS", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006¨\u0006\u0007"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/rx/DocumentCaptureDelayTransformer$DocumentCaptureResult;", "", "()V", "ImageObservableResult", "PassportDelayTimerFinished", "Lcom/onfido/android/sdk/capture/ui/camera/rx/DocumentCaptureDelayTransformer$DocumentCaptureResult$ImageObservableResult;", "Lcom/onfido/android/sdk/capture/ui/camera/rx/DocumentCaptureDelayTransformer$DocumentCaptureResult$PassportDelayTimerFinished;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static abstract class DocumentCaptureResult {

        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/rx/DocumentCaptureDelayTransformer$DocumentCaptureResult$ImageObservableResult;", "Lcom/onfido/android/sdk/capture/ui/camera/rx/DocumentCaptureDelayTransformer$DocumentCaptureResult;", "processingResults", "Lcom/onfido/android/sdk/capture/internal/validation/DocumentProcessingResults;", "(Lcom/onfido/android/sdk/capture/internal/validation/DocumentProcessingResults;)V", "getProcessingResults", "()Lcom/onfido/android/sdk/capture/internal/validation/DocumentProcessingResults;", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class ImageObservableResult extends DocumentCaptureResult {
            private final DocumentProcessingResults processingResults;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public ImageObservableResult(DocumentProcessingResults processingResults) {
                super(null);
                Intrinsics.checkNotNullParameter(processingResults, "processingResults");
                this.processingResults = processingResults;
            }

            public static /* synthetic */ ImageObservableResult copy$default(ImageObservableResult imageObservableResult, DocumentProcessingResults documentProcessingResults, int i, Object obj) {
                if ((i & 1) != 0) {
                    documentProcessingResults = imageObservableResult.processingResults;
                }
                return imageObservableResult.copy(documentProcessingResults);
            }

            /* renamed from: component1, reason: from getter */
            public final DocumentProcessingResults getProcessingResults() {
                return this.processingResults;
            }

            public final ImageObservableResult copy(DocumentProcessingResults processingResults) {
                Intrinsics.checkNotNullParameter(processingResults, "processingResults");
                return new ImageObservableResult(processingResults);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof ImageObservableResult) && Intrinsics.areEqual(this.processingResults, ((ImageObservableResult) other).processingResults);
            }

            public final DocumentProcessingResults getProcessingResults() {
                return this.processingResults;
            }

            public int hashCode() {
                return this.processingResults.hashCode();
            }

            public String toString() {
                return "ImageObservableResult(processingResults=" + this.processingResults + ')';
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/rx/DocumentCaptureDelayTransformer$DocumentCaptureResult$PassportDelayTimerFinished;", "Lcom/onfido/android/sdk/capture/ui/camera/rx/DocumentCaptureDelayTransformer$DocumentCaptureResult;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class PassportDelayTimerFinished extends DocumentCaptureResult {
            public static final PassportDelayTimerFinished INSTANCE = new PassportDelayTimerFinished();

            private PassportDelayTimerFinished() {
                super(null);
            }
        }

        private DocumentCaptureResult() {
        }

        public /* synthetic */ DocumentCaptureResult(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Inject
    public DocumentCaptureDelayTransformer(NativeDetector nativeDetector, DocumentConfigurationManager documentConfigurationManager, SchedulersProvider schedulersProvider, DocumentProcessingUseCase documentProcessingUseCase) {
        Intrinsics.checkNotNullParameter(nativeDetector, "nativeDetector");
        Intrinsics.checkNotNullParameter(documentConfigurationManager, "documentConfigurationManager");
        Intrinsics.checkNotNullParameter(schedulersProvider, "schedulersProvider");
        Intrinsics.checkNotNullParameter(documentProcessingUseCase, "documentProcessingUseCase");
        this.nativeDetector = nativeDetector;
        this.documentConfigurationManager = documentConfigurationManager;
        this.schedulersProvider = schedulersProvider;
        this.documentProcessingUseCase = documentProcessingUseCase;
        this.firstFrameMaybe = LazyKt.lazy(new Function0<Maybe<DocumentDetectionFrame>>() { // from class: com.onfido.android.sdk.capture.ui.camera.rx.DocumentCaptureDelayTransformer$firstFrameMaybe$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Maybe<DocumentDetectionFrame> invoke() {
                return this.this$0.nativeDetector.getFrameData().firstElement();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ObservableTransformer<DocumentCaptureResult, DocumentCaptureResult> firstFrameDelayTransformer(final boolean isFirstStart, final boolean isAccessibilityEnabled) {
        return new ObservableTransformer() { // from class: com.onfido.android.sdk.capture.ui.camera.rx.DocumentCaptureDelayTransformer$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.core.ObservableTransformer
            public final ObservableSource apply(Observable observable) {
                return DocumentCaptureDelayTransformer.firstFrameDelayTransformer$lambda$2(isFirstStart, isAccessibilityEnabled, this, observable);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ObservableSource firstFrameDelayTransformer$lambda$2(boolean z, boolean z2, final DocumentCaptureDelayTransformer this$0, final Observable upstream) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(upstream, "upstream");
        if (!z || !z2) {
            return upstream;
        }
        Observable observableFlatMap = this$0.getFirstFrameMaybe().flatMapObservable(new Function() { // from class: com.onfido.android.sdk.capture.ui.camera.rx.DocumentCaptureDelayTransformer$firstFrameDelayTransformer$1$1
            @Override // io.reactivex.rxjava3.functions.Function
            public final ObservableSource<? extends Long> apply(DocumentDetectionFrame documentDetectionFrame) {
                return Observable.timer(3000L, TimeUnit.MILLISECONDS, this.this$0.schedulersProvider.getTimer());
            }
        }).flatMap(new Function() { // from class: com.onfido.android.sdk.capture.ui.camera.rx.DocumentCaptureDelayTransformer$firstFrameDelayTransformer$1$2
            public final ObservableSource<? extends DocumentCaptureDelayTransformer.DocumentCaptureResult> apply(long j) {
                return upstream;
            }

            @Override // io.reactivex.rxjava3.functions.Function
            public /* bridge */ /* synthetic */ Object apply(Object obj) {
                return apply(((Number) obj).longValue());
            }
        });
        Intrinsics.checkNotNull(observableFlatMap);
        return observableFlatMap;
    }

    private final Observable<DocumentCaptureResult> getAutoCaptureWithNoOverlayDelayObservable(final Observable<DocumentProcessingResults> upstream, boolean isFirstStart, boolean isAccessibilityEnabled) {
        Observable<DocumentCaptureResult> observableCompose = Observable.timer(2000L, TimeUnit.MILLISECONDS, this.schedulersProvider.getTimer()).flatMap(new Function() { // from class: com.onfido.android.sdk.capture.ui.camera.rx.DocumentCaptureDelayTransformer.getAutoCaptureWithNoOverlayDelayObservable.1
            public final ObservableSource<? extends DocumentCaptureResult.ImageObservableResult> apply(long j) {
                return upstream.map(new Function() { // from class: com.onfido.android.sdk.capture.ui.camera.rx.DocumentCaptureDelayTransformer.getAutoCaptureWithNoOverlayDelayObservable.1.1
                    @Override // io.reactivex.rxjava3.functions.Function
                    public final DocumentCaptureResult.ImageObservableResult apply(DocumentProcessingResults p0) {
                        Intrinsics.checkNotNullParameter(p0, "p0");
                        return new DocumentCaptureResult.ImageObservableResult(p0);
                    }
                });
            }

            @Override // io.reactivex.rxjava3.functions.Function
            public /* bridge */ /* synthetic */ Object apply(Object obj) {
                return apply(((Number) obj).longValue());
            }
        }).compose(firstFrameDelayTransformer(isFirstStart, isAccessibilityEnabled));
        Intrinsics.checkNotNullExpressionValue(observableCompose, "compose(...)");
        return observableCompose;
    }

    private final Observable<DocumentCaptureResult> getAutoCaptureWithOverlayDelayObservable(final DocumentType documentType, final Observable<DocumentProcessingResults> upstream, final boolean isFirstStart, final boolean isAccessibilityEnabled) {
        Observable observableFlatMap = Observable.timer(3000L, TimeUnit.MILLISECONDS, this.schedulersProvider.getTimer()).flatMap(new Function() { // from class: com.onfido.android.sdk.capture.ui.camera.rx.DocumentCaptureDelayTransformer.getAutoCaptureWithOverlayDelayObservable.1
            public final ObservableSource<? extends DocumentCaptureResult> apply(long j) {
                return Observable.concat(DocumentCaptureDelayTransformer.this.passportDelayTimerFinishedObservable(documentType), upstream.map(new Function() { // from class: com.onfido.android.sdk.capture.ui.camera.rx.DocumentCaptureDelayTransformer.getAutoCaptureWithOverlayDelayObservable.1.1
                    @Override // io.reactivex.rxjava3.functions.Function
                    public final DocumentCaptureResult.ImageObservableResult apply(DocumentProcessingResults p0) {
                        Intrinsics.checkNotNullParameter(p0, "p0");
                        return new DocumentCaptureResult.ImageObservableResult(p0);
                    }
                }).compose(DocumentCaptureDelayTransformer.this.firstFrameDelayTransformer(isFirstStart, isAccessibilityEnabled)));
            }

            @Override // io.reactivex.rxjava3.functions.Function
            public /* bridge */ /* synthetic */ Object apply(Object obj) {
                return apply(((Number) obj).longValue());
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableFlatMap, "flatMap(...)");
        return observableFlatMap;
    }

    private final Maybe<DocumentDetectionFrame> getFirstFrameMaybe() {
        return (Maybe) this.firstFrameMaybe.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Observable<DocumentCaptureResult> passportDelayTimerFinishedObservable(final DocumentType documentType) {
        Observable<DocumentCaptureResult> map = Observable.fromCallable(new Callable() { // from class: com.onfido.android.sdk.capture.ui.camera.rx.DocumentCaptureDelayTransformer$$ExternalSyntheticLambda2
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return DocumentCaptureDelayTransformer.passportDelayTimerFinishedObservable$lambda$1(documentType);
            }
        }).filter(new Predicate() { // from class: com.onfido.android.sdk.capture.ui.camera.rx.DocumentCaptureDelayTransformer.passportDelayTimerFinishedObservable.2
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Boolean bool) {
                Intrinsics.checkNotNull(bool);
                return bool.booleanValue();
            }
        }).map(new Function() { // from class: com.onfido.android.sdk.capture.ui.camera.rx.DocumentCaptureDelayTransformer.passportDelayTimerFinishedObservable.3
            @Override // io.reactivex.rxjava3.functions.Function
            public final DocumentCaptureResult apply(Boolean bool) {
                return DocumentCaptureResult.PassportDelayTimerFinished.INSTANCE;
            }
        });
        Intrinsics.checkNotNullExpressionValue(map, "map(...)");
        return map;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Boolean passportDelayTimerFinishedObservable$lambda$1(DocumentType documentType) {
        Intrinsics.checkNotNullParameter(documentType, "$documentType");
        return Boolean.valueOf(documentType == DocumentType.PASSPORT);
    }

    private final boolean shouldAutoCapture(DocumentType documentType, CountryCode countryCode) {
        return this.documentConfigurationManager.shouldAutocapture(documentType, countryCode);
    }

    private final boolean shouldShowInitialOverlay(DocumentType documentType) {
        return this.documentConfigurationManager.shouldShowInitialOverlay(documentType);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ObservableSource transform$lambda$0(DocumentCaptureDelayTransformer this$0, boolean z, DocumentType documentType, CountryCode countryCode, boolean z2, Observable upstream) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(documentType, "$documentType");
        Intrinsics.checkNotNullParameter(upstream, "upstream");
        if (!this$0.nativeDetector.hasNativeLibrary()) {
            return Observable.empty();
        }
        if (z) {
            return this$0.passportDelayTimerFinishedObservable(documentType).delay(3000L, TimeUnit.MILLISECONDS);
        }
        if (this$0.shouldAutoCapture(documentType, countryCode)) {
            return (z2 && this$0.shouldShowInitialOverlay(documentType)) ? this$0.getAutoCaptureWithOverlayDelayObservable(documentType, upstream, z2, z) : this$0.getAutoCaptureWithNoOverlayDelayObservable(upstream, z2, z);
        }
        Observable observableCompose = upstream.map(new Function() { // from class: com.onfido.android.sdk.capture.ui.camera.rx.DocumentCaptureDelayTransformer$transform$1$1
            @Override // io.reactivex.rxjava3.functions.Function
            public final DocumentCaptureDelayTransformer.DocumentCaptureResult.ImageObservableResult apply(DocumentProcessingResults p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new DocumentCaptureDelayTransformer.DocumentCaptureResult.ImageObservableResult(p0);
            }
        }).compose(this$0.firstFrameDelayTransformer(z2, z));
        Intrinsics.checkNotNull(observableCompose);
        return observableCompose;
    }

    public final Flow<DocumentCaptureResult> getImageProcessingTransformedFlow$onfido_capture_sdk_core_release(final boolean enableManualFallback, final boolean isEdgeDetectionTimeoutStarted, DocumentType documentType, CountryCode countryCode, DocSide documentSide, final Function0<Unit> manualFallbackCallback, boolean isFirstStart, boolean isAccessibilityEnabled) {
        Intrinsics.checkNotNullParameter(documentType, "documentType");
        Intrinsics.checkNotNullParameter(manualFallbackCallback, "manualFallbackCallback");
        Observable<DocumentProcessingUseCaseResult> observableDoOnNext = this.documentProcessingUseCase.execute$onfido_capture_sdk_core_release(documentType, countryCode, documentSide).doOnNext(new Consumer() { // from class: com.onfido.android.sdk.capture.ui.camera.rx.DocumentCaptureDelayTransformer$getImageProcessingTransformedFlow$1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(DocumentProcessingUseCaseResult documentProcessingUseCaseResult) {
                Intrinsics.checkNotNullParameter(documentProcessingUseCaseResult, "<name for destructuring parameter 0>");
                DocumentProcessingResults documentProcessingResults = documentProcessingUseCaseResult.getDocumentProcessingResults();
                if (enableManualFallback && !isEdgeDetectionTimeoutStarted && documentProcessingResults.getEdgeDetectionResults().hasAny()) {
                    manualFallbackCallback.invoke();
                }
            }
        });
        final DocumentCaptureDelayTransformer$getImageProcessingTransformedFlow$2 documentCaptureDelayTransformer$getImageProcessingTransformedFlow$2 = new PropertyReference1Impl() { // from class: com.onfido.android.sdk.capture.ui.camera.rx.DocumentCaptureDelayTransformer$getImageProcessingTransformedFlow$2
            @Override // kotlin.jvm.internal.PropertyReference1Impl, kotlin.reflect.KProperty1
            public Object get(Object obj) {
                return ((DocumentProcessingUseCaseResult) obj).getDocumentProcessingResults();
            }
        };
        Observable observableCompose = observableDoOnNext.map(new Function(documentCaptureDelayTransformer$getImageProcessingTransformedFlow$2) { // from class: com.onfido.android.sdk.capture.ui.camera.rx.DocumentCaptureDelayTransformer$sam$io_reactivex_rxjava3_functions_Function$0
            private final /* synthetic */ Function1 function;

            {
                Intrinsics.checkNotNullParameter(documentCaptureDelayTransformer$getImageProcessingTransformedFlow$2, "function");
                this.function = documentCaptureDelayTransformer$getImageProcessingTransformedFlow$2;
            }

            @Override // io.reactivex.rxjava3.functions.Function
            public final /* synthetic */ Object apply(Object obj) {
                return this.function.invoke(obj);
            }
        }).compose(transform$onfido_capture_sdk_core_release(documentType, countryCode, isFirstStart, isAccessibilityEnabled));
        Intrinsics.checkNotNullExpressionValue(observableCompose, "compose(...)");
        return RxConvertKt.asFlow(observableCompose);
    }

    public final ObservableTransformer<DocumentProcessingResults, DocumentCaptureResult> transform$onfido_capture_sdk_core_release(final DocumentType documentType, final CountryCode countryCode, final boolean isFirstStart, final boolean isAccessibilityEnabled) {
        Intrinsics.checkNotNullParameter(documentType, "documentType");
        return new ObservableTransformer() { // from class: com.onfido.android.sdk.capture.ui.camera.rx.DocumentCaptureDelayTransformer$$ExternalSyntheticLambda1
            @Override // io.reactivex.rxjava3.core.ObservableTransformer
            public final ObservableSource apply(Observable observable) {
                return DocumentCaptureDelayTransformer.transform$lambda$0(this.f$0, isAccessibilityEnabled, documentType, countryCode, isFirstStart, observable);
            }
        };
    }
}
