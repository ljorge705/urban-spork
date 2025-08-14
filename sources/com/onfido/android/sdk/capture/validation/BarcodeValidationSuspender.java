package com.onfido.android.sdk.capture.validation;

import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.android.sdk.capture.internal.validation.DocumentProcessingResults;
import com.onfido.javax.inject.Inject;
import com.onfido.javax.inject.Singleton;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Action;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0015\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0000¢\u0006\u0002\b\u0010J\u0015\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0000¢\u0006\u0002\b\u0015J\r\u0010\u0016\u001a\u00020\u0012H\u0000¢\u0006\u0002\b\u0017J\b\u0010\u0018\u001a\u00020\u0012H\u0002J\r\u0010\u0019\u001a\u00020\u0012H\u0000¢\u0006\u0002\b\u001aR\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/onfido/android/sdk/capture/validation/BarcodeValidationSuspender;", "", "remoteConfig", "Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;", "schedulersProvider", "Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;", "(Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;)V", "compositeDisposable", "Lio/reactivex/rxjava3/disposables/CompositeDisposable;", "skipBarcodeValidation", "Ljava/util/concurrent/atomic/AtomicBoolean;", "timerStarted", "isValidationSuspended", "", "validation", "Lcom/onfido/android/sdk/capture/validation/OnDeviceValidationType;", "isValidationSuspended$onfido_capture_sdk_core_release", "onValidationResult", "", "processingUseCase", "Lcom/onfido/android/sdk/capture/internal/validation/DocumentProcessingResults;", "onValidationResult$onfido_capture_sdk_core_release", "reset", "reset$onfido_capture_sdk_core_release", "startBarcodeProcessingTimeout", "stopTimer", "stopTimer$onfido_capture_sdk_core_release", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Singleton
/* loaded from: classes2.dex */
public final class BarcodeValidationSuspender {
    private final CompositeDisposable compositeDisposable;
    private final OnfidoRemoteConfig remoteConfig;
    private final SchedulersProvider schedulersProvider;
    private final AtomicBoolean skipBarcodeValidation;
    private final AtomicBoolean timerStarted;

    @Inject
    public BarcodeValidationSuspender(OnfidoRemoteConfig remoteConfig, SchedulersProvider schedulersProvider) {
        Intrinsics.checkNotNullParameter(remoteConfig, "remoteConfig");
        Intrinsics.checkNotNullParameter(schedulersProvider, "schedulersProvider");
        this.remoteConfig = remoteConfig;
        this.schedulersProvider = schedulersProvider;
        this.compositeDisposable = new CompositeDisposable();
        this.skipBarcodeValidation = new AtomicBoolean(false);
        this.timerStarted = new AtomicBoolean(false);
    }

    private final void startBarcodeProcessingTimeout() {
        if (this.timerStarted.compareAndSet(false, true)) {
            this.compositeDisposable.add(Completable.timer(this.remoteConfig.getDocumentCapture().getBarcodeDetectionTimeoutMs(), TimeUnit.MILLISECONDS, this.schedulersProvider.getTimer()).subscribe(new Action() { // from class: com.onfido.android.sdk.capture.validation.BarcodeValidationSuspender$$ExternalSyntheticLambda0
                @Override // io.reactivex.rxjava3.functions.Action
                public final void run() {
                    BarcodeValidationSuspender.startBarcodeProcessingTimeout$lambda$0(this.f$0);
                }
            }));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startBarcodeProcessingTimeout$lambda$0(BarcodeValidationSuspender this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.skipBarcodeValidation.compareAndSet(false, true);
    }

    public final boolean isValidationSuspended$onfido_capture_sdk_core_release(OnDeviceValidationType validation) {
        Intrinsics.checkNotNullParameter(validation, "validation");
        return this.skipBarcodeValidation.get() && validation == OnDeviceValidationType.PDF_417_BARCODE_DETECTION;
    }

    public final void onValidationResult$onfido_capture_sdk_core_release(DocumentProcessingResults processingUseCase) {
        Intrinsics.checkNotNullParameter(processingUseCase, "processingUseCase");
        if (processingUseCase.getBarcodeResults().isValid()) {
            return;
        }
        startBarcodeProcessingTimeout();
    }

    public final void reset$onfido_capture_sdk_core_release() {
        this.skipBarcodeValidation.compareAndSet(true, false);
        this.timerStarted.compareAndSet(true, false);
    }

    public final void stopTimer$onfido_capture_sdk_core_release() {
        this.compositeDisposable.clear();
    }
}
