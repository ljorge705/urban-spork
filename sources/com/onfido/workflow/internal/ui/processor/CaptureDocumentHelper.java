package com.onfido.workflow.internal.ui.processor;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.onfido.android.sdk.capture.DocumentFormat;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.document.DocumentConfigurationManager;
import com.onfido.android.sdk.capture.flow.CaptureStepDataBundle;
import com.onfido.android.sdk.capture.flow.NfcArguments;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.navigation.Navigator;
import com.onfido.android.sdk.capture.internal.util.logging.Timber;
import com.onfido.android.sdk.capture.model.NFCOptions;
import com.onfido.android.sdk.capture.model.NFCOptionsKt;
import com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureScreen;
import com.onfido.android.sdk.capture.ui.nfc.model.NfcProperties;
import com.onfido.android.sdk.capture.utils.CountryCode;
import com.onfido.api.client.data.DocSide;
import com.onfido.javax.inject.Inject;
import com.onfido.reactnative.sdk.ReactNativeBridgeUtiles;
import com.onfido.workflow.internal.ui.model.MediaUpload;
import com.onfido.workflow.internal.ui.model.UIEvent;
import com.onfido.workflow.internal.ui.processor.DisplayDocumentCaptureFlowProcessor;
import com.onfido.workflow.internal.ui.processor.nfc.NfcFlowHelper;
import com.onfido.workflow.internal.utils.NavigatorExtKt;
import com.onfido.workflow.internal.workflow.SubmitDocumentTaskCompletionUseCase;
import com.onfido.workflow.internal.workflow.WorkflowTask;
import com.onfido.workflow.internal.workflow.model.CaptureResult;
import com.onfido.workflow.internal.workflow.model.DocumentUploadPayload;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;

/* compiled from: CaptureDocumentHelper.kt */
@Metadata(d1 = {"\u0000®\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u0000 B2\u00020\u0001:\u0003BCDB/\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\"\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u000eJ\b\u0010\u0014\u001a\u00020\u0015H\u0002J@\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u000e2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J>\u0010 \u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0018\u001a\u00020\u00192\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u000e2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0002J2\u0010!\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\"\u001a\u0004\u0018\u00010\u001d2\b\u0010#\u001a\u0004\u0018\u00010$2\u0006\u0010%\u001a\u00020&H\u0002J<\u0010'\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u000e2\u0006\u0010(\u001a\u00020)2\u0006\u0010%\u001a\u00020&2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u001e\u0010*\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010(\u001a\u00020)2\u0006\u0010%\u001a\u00020&H\u0002JB\u0010+\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u000e2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010(\u001a\u00020)2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0010\u001a\u00020\u0011H\u0002JN\u0010-\u001a\u00020\u00172\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u000e2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\f\u0010.\u001a\b\u0012\u0004\u0012\u0002000/2\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u000204H\u0002J\u001d\u00105\u001a\u00070\u0017¢\u0006\u0002\b62\u0006\u00107\u001a\u0002082\u0006\u00109\u001a\u00020:H\u0002J#\u00105\u001a\u00070\u0017¢\u0006\u0002\b62\u0006\u00107\u001a\u0002082\f\u0010;\u001a\b\u0012\u0004\u0012\u00020\u00190/H\u0002J \u0010<\u001a\b\u0012\u0004\u0012\u00020\u001f0\u000e*\b\u0012\u0004\u0012\u00020\u00130\u000e2\u0006\u0010=\u001a\u00020>H\u0002J \u0010?\u001a\b\u0012\u0004\u0012\u00020\u001f0\u000e*\b\u0012\u0004\u0012\u00020\u00130\u000e2\u0006\u0010=\u001a\u00020>H\u0002J \u0010@\u001a\b\u0012\u0004\u0012\u00020\u001f0\u000e*\b\u0012\u0004\u0012\u00020\u00130\u000e2\u0006\u0010=\u001a\u00020>H\u0002J\u0018\u0010A\u001a\b\u0012\u0004\u0012\u0002000/*\b\u0012\u0004\u0012\u00020\u00190/H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006E"}, d2 = {"Lcom/onfido/workflow/internal/ui/processor/CaptureDocumentHelper;", "", "documentConfigurationManager", "Lcom/onfido/android/sdk/capture/document/DocumentConfigurationManager;", "nfcFlowHelper", "Lcom/onfido/workflow/internal/ui/processor/nfc/NfcFlowHelper;", "navigator", "Lcom/onfido/android/sdk/capture/internal/navigation/Navigator;", "submitTaskCompletionUseCase", "Lcom/onfido/workflow/internal/workflow/SubmitDocumentTaskCompletionUseCase;", "onfidoRemoteConfig", "Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;", "(Lcom/onfido/android/sdk/capture/document/DocumentConfigurationManager;Lcom/onfido/workflow/internal/ui/processor/nfc/NfcFlowHelper;Lcom/onfido/android/sdk/capture/internal/navigation/Navigator;Lcom/onfido/workflow/internal/workflow/SubmitDocumentTaskCompletionUseCase;Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;)V", "captureDocumentAndSubmit", "Lio/reactivex/rxjava3/core/Observable;", "Lcom/onfido/workflow/internal/ui/processor/DisplayDocumentCaptureFlowProcessor$ProcessorOutcome;", "documentTask", "Lcom/onfido/workflow/internal/workflow/WorkflowTask$DocumentTask;", "uiEventObservable", "Lcom/onfido/workflow/internal/ui/model/UIEvent;", "finishDocumentSelectionScreen", "", "handleDocumentBackSide", "Lio/reactivex/rxjava3/core/Completable;", "firstSideDocumentPayload", "Lcom/onfido/workflow/internal/workflow/model/DocumentUploadPayload;", "documentType", "Lcom/onfido/android/sdk/capture/DocumentType;", RemoteConfigConstants.RequestFieldKey.COUNTRY_CODE, "Lcom/onfido/android/sdk/capture/utils/CountryCode;", "backSideResult", "Lcom/onfido/workflow/internal/workflow/model/CaptureResult$DocumentUpload;", "handleOneSidedDocument", "launchCaptureActivityForBackSide", "capturedCountryCode", "captureDocumentFormat", "Lcom/onfido/android/sdk/capture/DocumentFormat;", "nfcArguments", "Lcom/onfido/android/sdk/capture/flow/NfcArguments;", "launchDocumentCaptureForBackSide", "captureStepDataBundle", "Lcom/onfido/android/sdk/capture/flow/CaptureStepDataBundle;", "launchDocumentCaptureFragmentForBackSide", "processCaptureResultForBackSide", "launchCaptureObservable", "startScanNfcFlowAndSubmitTaskCompletion", "mediaUploads", "", "Lcom/onfido/workflow/internal/ui/model/MediaUpload;", "nfcProperties", "Lcom/onfido/android/sdk/capture/ui/nfc/model/NfcProperties;", "nfcOptions", "Lcom/onfido/android/sdk/capture/model/NFCOptions;", "submitTaskCompletion", "Lio/reactivex/rxjava3/annotations/NonNull;", "task", "Lcom/onfido/workflow/internal/workflow/WorkflowTask;", FirebaseAnalytics.Param.SUCCESS, "Lcom/onfido/workflow/internal/ui/processor/nfc/NfcFlowHelper$NfcFlowOutcome$Success;", "uploadArtifacts", "observeCaptureActivityResultForSide", ReactNativeBridgeUtiles.KEY_DOCUMENT_SIDE, "Lcom/onfido/api/client/data/DocSide;", "observeCaptureFragmentResultForSide", "observeCaptureResultForSide", "toMediaUploads", "Companion", "ExitFlowException", "ShowCaptureScreenException", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class CaptureDocumentHelper {
    private static final NfcProperties DEFAULT_NFC_PROPERTIES = new NfcProperties(false, "", new byte[0], null, false, false, 0, 0, null, 496, null);
    private final DocumentConfigurationManager documentConfigurationManager;
    private final Navigator navigator;
    private final NfcFlowHelper nfcFlowHelper;
    private final OnfidoRemoteConfig onfidoRemoteConfig;
    private final SubmitDocumentTaskCompletionUseCase submitTaskCompletionUseCase;

    @Inject
    public CaptureDocumentHelper(DocumentConfigurationManager documentConfigurationManager, NfcFlowHelper nfcFlowHelper, Navigator navigator, SubmitDocumentTaskCompletionUseCase submitTaskCompletionUseCase, OnfidoRemoteConfig onfidoRemoteConfig) {
        Intrinsics.checkNotNullParameter(documentConfigurationManager, "documentConfigurationManager");
        Intrinsics.checkNotNullParameter(nfcFlowHelper, "nfcFlowHelper");
        Intrinsics.checkNotNullParameter(navigator, "navigator");
        Intrinsics.checkNotNullParameter(submitTaskCompletionUseCase, "submitTaskCompletionUseCase");
        Intrinsics.checkNotNullParameter(onfidoRemoteConfig, "onfidoRemoteConfig");
        this.documentConfigurationManager = documentConfigurationManager;
        this.nfcFlowHelper = nfcFlowHelper;
        this.navigator = navigator;
        this.submitTaskCompletionUseCase = submitTaskCompletionUseCase;
        this.onfidoRemoteConfig = onfidoRemoteConfig;
    }

    public final Observable<DisplayDocumentCaptureFlowProcessor.ProcessorOutcome> captureDocumentAndSubmit(final WorkflowTask.DocumentTask documentTask, final Observable<UIEvent> uiEventObservable) {
        Intrinsics.checkNotNullParameter(documentTask, "documentTask");
        Intrinsics.checkNotNullParameter(uiEventObservable, "uiEventObservable");
        Observable<DisplayDocumentCaptureFlowProcessor.ProcessorOutcome> observableOnErrorResumeNext = observeCaptureResultForSide(uiEventObservable, DocSide.FRONT).switchMap(new Function() { // from class: com.onfido.workflow.internal.ui.processor.CaptureDocumentHelper.captureDocumentAndSubmit.1
            @Override // io.reactivex.rxjava3.functions.Function
            public final ObservableSource<? extends DisplayDocumentCaptureFlowProcessor.ProcessorOutcome> apply(CaptureResult.DocumentUpload frontDocumentUploadResult) {
                Intrinsics.checkNotNullParameter(frontDocumentUploadResult, "frontDocumentUploadResult");
                DocumentType documentType = frontDocumentUploadResult.getCaptureStepDataBundle().getDocumentType();
                if (documentType == null) {
                    throw new IllegalArgumentException("Required value was null.".toString());
                }
                CountryCode countryCode = frontDocumentUploadResult.getCaptureStepDataBundle().getCountryCode();
                DocumentUploadPayload documentUploadPayload = frontDocumentUploadResult.getDocumentUploadPayload();
                DocumentUploadPayload.ScannedNfcPayload scannedNfcPayload = documentUploadPayload instanceof DocumentUploadPayload.ScannedNfcPayload ? (DocumentUploadPayload.ScannedNfcPayload) documentUploadPayload : null;
                NfcArguments nfcArguments = new NfcArguments(documentTask.getNfcOptions(), scannedNfcPayload != null ? new NfcArguments.CapturedNfcData(scannedNfcPayload.getDocumentId(), scannedNfcPayload.getNfcProperties(), scannedNfcPayload.getNfcSupported()) : null);
                DocumentUploadPayload documentUploadPayload2 = frontDocumentUploadResult.getDocumentUploadPayload();
                return this.documentConfigurationManager.backSideCaptureNeeded(documentType, frontDocumentUploadResult.getCaptureStepDataBundle().getGenericDocPages()) ? this.launchDocumentCaptureForBackSide(uiEventObservable, CaptureStepDataBundle.copy$default(frontDocumentUploadResult.getCaptureStepDataBundle(), null, null, null, null, DocSide.BACK, null, null, 111, null), nfcArguments, documentUploadPayload2, documentTask) : this.handleOneSidedDocument(documentUploadPayload2, uiEventObservable, documentTask, documentType, countryCode);
            }
        }).onErrorResumeNext(new Function() { // from class: com.onfido.workflow.internal.ui.processor.CaptureDocumentHelper.captureDocumentAndSubmit.2
            @Override // io.reactivex.rxjava3.functions.Function
            public final ObservableSource<? extends DisplayDocumentCaptureFlowProcessor.ProcessorOutcome> apply(Throwable error) {
                Intrinsics.checkNotNullParameter(error, "error");
                if (error instanceof ExitFlowException) {
                    return Observable.just(DisplayDocumentCaptureFlowProcessor.ProcessorOutcome.ExitNfcFlow.INSTANCE);
                }
                return Observable.error(error);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableOnErrorResumeNext, "onErrorResumeNext(...)");
        return observableOnErrorResumeNext;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Observable<DisplayDocumentCaptureFlowProcessor.ProcessorOutcome> launchDocumentCaptureForBackSide(Observable<UIEvent> uiEventObservable, CaptureStepDataBundle captureStepDataBundle, NfcArguments nfcArguments, DocumentUploadPayload firstSideDocumentPayload, WorkflowTask.DocumentTask documentTask) {
        Observable<DisplayDocumentCaptureFlowProcessor.ProcessorOutcome> observableLaunchCaptureActivityForBackSide;
        if (this.onfidoRemoteConfig.isRefactoredCaptureEnabled()) {
            observableLaunchCaptureActivityForBackSide = launchDocumentCaptureFragmentForBackSide(captureStepDataBundle, nfcArguments);
        } else {
            DocumentType documentType = captureStepDataBundle.getDocumentType();
            if (documentType != null) {
                observableLaunchCaptureActivityForBackSide = launchCaptureActivityForBackSide(documentType, captureStepDataBundle.getCountryCode(), captureStepDataBundle.getDocumentFormat(), nfcArguments);
            } else {
                throw new IllegalArgumentException("Required value was null.".toString());
            }
        }
        return processCaptureResultForBackSide(uiEventObservable, observableLaunchCaptureActivityForBackSide, captureStepDataBundle, firstSideDocumentPayload, documentTask);
    }

    private final Observable<DisplayDocumentCaptureFlowProcessor.ProcessorOutcome> processCaptureResultForBackSide(final Observable<UIEvent> uiEventObservable, Observable<DisplayDocumentCaptureFlowProcessor.ProcessorOutcome> launchCaptureObservable, CaptureStepDataBundle captureStepDataBundle, final DocumentUploadPayload firstSideDocumentPayload, final WorkflowTask.DocumentTask documentTask) {
        final DocumentType documentType = captureStepDataBundle.getDocumentType();
        if (documentType == null) {
            throw new IllegalArgumentException("Required value was null.".toString());
        }
        final CountryCode countryCode = captureStepDataBundle.getCountryCode();
        Observable<DisplayDocumentCaptureFlowProcessor.ProcessorOutcome> observableConcat = Observable.concat(launchCaptureObservable, observeCaptureResultForSide(uiEventObservable, DocSide.BACK).take(1L).flatMapCompletable(new Function() { // from class: com.onfido.workflow.internal.ui.processor.CaptureDocumentHelper.processCaptureResultForBackSide.1
            @Override // io.reactivex.rxjava3.functions.Function
            public final CompletableSource apply(CaptureResult.DocumentUpload backSideResult) {
                Intrinsics.checkNotNullParameter(backSideResult, "backSideResult");
                return CaptureDocumentHelper.this.handleDocumentBackSide(firstSideDocumentPayload, uiEventObservable, documentTask, documentType, countryCode, backSideResult);
            }
        }).andThen(Observable.just(DisplayDocumentCaptureFlowProcessor.ProcessorOutcome.DocumentCaptureFlowFinished.INSTANCE)));
        Intrinsics.checkNotNullExpressionValue(observableConcat, "concat(...)");
        return observableConcat;
    }

    private final Observable<DisplayDocumentCaptureFlowProcessor.ProcessorOutcome> launchCaptureActivityForBackSide(DocumentType documentType, CountryCode capturedCountryCode, DocumentFormat captureDocumentFormat, NfcArguments nfcArguments) {
        Observable<DisplayDocumentCaptureFlowProcessor.ProcessorOutcome> observableJust = Observable.just(new DisplayDocumentCaptureFlowProcessor.ProcessorOutcome.OpenDocumentCaptureActivity(documentType, DocSide.BACK, capturedCountryCode, captureDocumentFormat, nfcArguments));
        Intrinsics.checkNotNullExpressionValue(observableJust, "just(...)");
        return observableJust;
    }

    private final Observable<DisplayDocumentCaptureFlowProcessor.ProcessorOutcome> launchDocumentCaptureFragmentForBackSide(final CaptureStepDataBundle captureStepDataBundle, final NfcArguments nfcArguments) {
        Observable<DisplayDocumentCaptureFlowProcessor.ProcessorOutcome> observable = Completable.fromAction(new Action() { // from class: com.onfido.workflow.internal.ui.processor.CaptureDocumentHelper$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                CaptureDocumentHelper.launchDocumentCaptureFragmentForBackSide$lambda$0(this.f$0, captureStepDataBundle, nfcArguments);
            }
        }).toObservable();
        Intrinsics.checkNotNullExpressionValue(observable, "toObservable(...)");
        return observable;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void launchDocumentCaptureFragmentForBackSide$lambda$0(CaptureDocumentHelper this$0, CaptureStepDataBundle captureStepDataBundle, NfcArguments nfcArguments) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(captureStepDataBundle, "$captureStepDataBundle");
        Intrinsics.checkNotNullParameter(nfcArguments, "$nfcArguments");
        this$0.navigator.navigateTo(new DocumentCaptureScreen(captureStepDataBundle, false, nfcArguments));
    }

    private static final Observable<DisplayDocumentCaptureFlowProcessor.ProcessorOutcome> handleOneSidedDocument$startNfcFlow(CaptureDocumentHelper captureDocumentHelper, Observable<UIEvent> observable, WorkflowTask.DocumentTask documentTask, DocumentType documentType, CountryCode countryCode, DocumentUploadPayload documentUploadPayload, NfcProperties nfcProperties) {
        Observable<DisplayDocumentCaptureFlowProcessor.ProcessorOutcome> observableAndThen = captureDocumentHelper.startScanNfcFlowAndSubmitTaskCompletion(observable, documentTask, documentType, countryCode, captureDocumentHelper.toMediaUploads(CollectionsKt.listOf(documentUploadPayload)), nfcProperties, documentTask.getNfcOptions()).andThen(Observable.just(DisplayDocumentCaptureFlowProcessor.ProcessorOutcome.DocumentCaptureFlowFinished.INSTANCE));
        Intrinsics.checkNotNullExpressionValue(observableAndThen, "andThen(...)");
        return observableAndThen;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Observable<DisplayDocumentCaptureFlowProcessor.ProcessorOutcome> handleOneSidedDocument(DocumentUploadPayload firstSideDocumentPayload, Observable<UIEvent> uiEventObservable, WorkflowTask.DocumentTask documentTask, DocumentType documentType, CountryCode countryCode) {
        boolean z = firstSideDocumentPayload instanceof DocumentUploadPayload.ScannedNfcPayload;
        if (!z && NFCOptionsKt.isRequired(documentTask.getNfcOptions())) {
            return handleOneSidedDocument$startNfcFlow(this, uiEventObservable, documentTask, documentType, countryCode, firstSideDocumentPayload, new NfcProperties(false, "", new byte[0], null, false, false, 0, 0, null, 496, null));
        }
        if (z) {
            return handleOneSidedDocument$startNfcFlow(this, uiEventObservable, documentTask, documentType, countryCode, firstSideDocumentPayload, ((DocumentUploadPayload.ScannedNfcPayload) firstSideDocumentPayload).getNfcProperties());
        }
        if (!(firstSideDocumentPayload instanceof DocumentUploadPayload.UploadedArtifactPayload)) {
            throw new NoWhenBranchMatchedException();
        }
        finishDocumentSelectionScreen();
        Observable<DisplayDocumentCaptureFlowProcessor.ProcessorOutcome> observableAndThen = submitTaskCompletion(documentTask, CollectionsKt.listOf(firstSideDocumentPayload)).andThen(Observable.just(DisplayDocumentCaptureFlowProcessor.ProcessorOutcome.DocumentCaptureFlowFinished.INSTANCE));
        Intrinsics.checkNotNull(observableAndThen);
        return observableAndThen;
    }

    private final Observable<CaptureResult.DocumentUpload> observeCaptureResultForSide(Observable<UIEvent> observable, DocSide docSide) {
        Observable<CaptureResult.DocumentUpload> observableMerge = Observable.merge(observeCaptureFragmentResultForSide(observable, docSide), observeCaptureActivityResultForSide(observable, docSide));
        Intrinsics.checkNotNullExpressionValue(observableMerge, "merge(...)");
        return observableMerge;
    }

    private final Completable startScanNfcFlowAndSubmitTaskCompletion(Observable<UIEvent> uiEventObservable, final WorkflowTask.DocumentTask documentTask, DocumentType documentType, CountryCode countryCode, List<MediaUpload> mediaUploads, NfcProperties nfcProperties, NFCOptions nfcOptions) {
        Completable completableFlatMapCompletable = this.nfcFlowHelper.process(uiEventObservable, documentType, countryCode, mediaUploads, nfcProperties, nfcOptions, documentTask).take(1L).flatMapCompletable(new Function() { // from class: com.onfido.workflow.internal.ui.processor.CaptureDocumentHelper.startScanNfcFlowAndSubmitTaskCompletion.1
            @Override // io.reactivex.rxjava3.functions.Function
            public final CompletableSource apply(NfcFlowHelper.NfcFlowOutcome outcome) {
                Intrinsics.checkNotNullParameter(outcome, "outcome");
                if (outcome instanceof NfcFlowHelper.NfcFlowOutcome.Success) {
                    CaptureDocumentHelper.this.finishDocumentSelectionScreen();
                    return CaptureDocumentHelper.this.submitTaskCompletion(documentTask, (NfcFlowHelper.NfcFlowOutcome.Success) outcome);
                }
                if (outcome instanceof NfcFlowHelper.NfcFlowOutcome.Error) {
                    return Completable.complete();
                }
                if (outcome instanceof NfcFlowHelper.NfcFlowOutcome.ExitFlow) {
                    return Completable.error(ExitFlowException.INSTANCE);
                }
                if (outcome instanceof NfcFlowHelper.NfcFlowOutcome.ShowCaptureScreen) {
                    return Completable.error(ShowCaptureScreenException.INSTANCE);
                }
                throw new NoWhenBranchMatchedException();
            }
        });
        Intrinsics.checkNotNullExpressionValue(completableFlatMapCompletable, "flatMapCompletable(...)");
        return completableFlatMapCompletable;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Completable handleDocumentBackSide(DocumentUploadPayload firstSideDocumentPayload, Observable<UIEvent> uiEventObservable, WorkflowTask.DocumentTask documentTask, DocumentType documentType, CountryCode countryCode, CaptureResult.DocumentUpload backSideResult) {
        NfcProperties nfcProperties;
        DocumentUploadPayload documentUploadPayload = backSideResult.getDocumentUploadPayload();
        DocumentUploadPayload.ScannedNfcPayload scannedNfcPayload = null;
        DocumentUploadPayload.ScannedNfcPayload scannedNfcPayload2 = documentUploadPayload instanceof DocumentUploadPayload.ScannedNfcPayload ? (DocumentUploadPayload.ScannedNfcPayload) documentUploadPayload : null;
        if (scannedNfcPayload2 != null) {
            scannedNfcPayload = scannedNfcPayload2;
        } else if (firstSideDocumentPayload instanceof DocumentUploadPayload.ScannedNfcPayload) {
            scannedNfcPayload = (DocumentUploadPayload.ScannedNfcPayload) firstSideDocumentPayload;
        }
        if (NFCOptionsKt.isRequired(documentTask.getNfcOptions()) || scannedNfcPayload != null) {
            List<MediaUpload> mediaUploads = toMediaUploads(CollectionsKt.listOf((Object[]) new DocumentUploadPayload[]{firstSideDocumentPayload, backSideResult.getDocumentUploadPayload()}));
            if (scannedNfcPayload == null || (nfcProperties = scannedNfcPayload.getNfcProperties()) == null) {
                nfcProperties = DEFAULT_NFC_PROPERTIES;
            }
            return startScanNfcFlowAndSubmitTaskCompletion(uiEventObservable, documentTask, documentType, countryCode, mediaUploads, nfcProperties, documentTask.getNfcOptions());
        }
        finishDocumentSelectionScreen();
        return submitTaskCompletion(documentTask, CollectionsKt.listOf((Object[]) new DocumentUploadPayload[]{firstSideDocumentPayload, backSideResult.getDocumentUploadPayload()}));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void finishDocumentSelectionScreen() {
        NavigatorExtKt.backToWorkflowRoot(this.navigator);
    }

    private final Completable submitTaskCompletion(WorkflowTask task, List<? extends DocumentUploadPayload> uploadArtifacts) {
        Completable completableOnErrorComplete = this.submitTaskCompletionUseCase.execute(task, toMediaUploads(uploadArtifacts)).onErrorComplete(new Predicate() { // from class: com.onfido.workflow.internal.ui.processor.CaptureDocumentHelper.submitTaskCompletion.1
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Throwable throwable) {
                Intrinsics.checkNotNullParameter(throwable, "throwable");
                Timber.INSTANCE.e(throwable, "Error during submitting DocumentCapture task completion", new Object[0]);
                return true;
            }
        });
        Intrinsics.checkNotNullExpressionValue(completableOnErrorComplete, "onErrorComplete(...)");
        return completableOnErrorComplete;
    }

    private final List<MediaUpload> toMediaUploads(List<? extends DocumentUploadPayload> list) {
        ArrayList arrayList = new ArrayList();
        for (DocumentUploadPayload documentUploadPayload : list) {
            CollectionsKt.addAll(arrayList, CollectionsKt.listOf((Object[]) new MediaUpload[]{new MediaUpload(documentUploadPayload.getDocumentId(), MediaUpload.Type.DOCUMENT_PHOTO), new MediaUpload(documentUploadPayload.getDocumentVideoId(), MediaUpload.Type.DOCUMENT_VIDEO)}));
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Completable submitTaskCompletion(WorkflowTask task, NfcFlowHelper.NfcFlowOutcome.Success success) {
        Completable completableOnErrorComplete = this.submitTaskCompletionUseCase.execute(task, success.getMediaUploads()).onErrorComplete(new Predicate() { // from class: com.onfido.workflow.internal.ui.processor.CaptureDocumentHelper.submitTaskCompletion.2
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Throwable throwable) {
                Intrinsics.checkNotNullParameter(throwable, "throwable");
                Timber.INSTANCE.e(throwable, "Error during submitting document with nfc task completion", new Object[0]);
                return true;
            }
        });
        Intrinsics.checkNotNullExpressionValue(completableOnErrorComplete, "onErrorComplete(...)");
        return completableOnErrorComplete;
    }

    /* compiled from: CaptureDocumentHelper.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÂ\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/onfido/workflow/internal/ui/processor/CaptureDocumentHelper$ExitFlowException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "()V", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class ExitFlowException extends Exception {
        public static final ExitFlowException INSTANCE = new ExitFlowException();

        private ExitFlowException() {
        }
    }

    /* compiled from: CaptureDocumentHelper.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/onfido/workflow/internal/ui/processor/CaptureDocumentHelper$ShowCaptureScreenException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "()V", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class ShowCaptureScreenException extends Exception {
        public static final ShowCaptureScreenException INSTANCE = new ShowCaptureScreenException();

        private ShowCaptureScreenException() {
        }
    }

    private final Observable<CaptureResult.DocumentUpload> observeCaptureActivityResultForSide(Observable<UIEvent> observable, final DocSide docSide) {
        Observable<U> observableCast = observable.filter(new Predicate() { // from class: com.onfido.workflow.internal.ui.processor.CaptureDocumentHelper$observeCaptureActivityResultForSide$$inlined$filterIsInstance$1
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it instanceof UIEvent.OnActivityResult;
            }
        }).cast(UIEvent.OnActivityResult.class);
        Intrinsics.checkNotNullExpressionValue(observableCast, "cast(...)");
        final C08081 c08081 = new PropertyReference1Impl() { // from class: com.onfido.workflow.internal.ui.processor.CaptureDocumentHelper.observeCaptureActivityResultForSide.1
            @Override // kotlin.jvm.internal.PropertyReference1Impl, kotlin.reflect.KProperty1
            public Object get(Object obj) {
                return ((UIEvent.OnActivityResult) obj).getCaptureResult();
            }
        };
        Observable map = observableCast.map(new Function(c08081) { // from class: com.onfido.workflow.internal.ui.processor.CaptureDocumentHelper$sam$io_reactivex_rxjava3_functions_Function$0
            private final /* synthetic */ Function1 function;

            {
                Intrinsics.checkNotNullParameter(c08081, "function");
                this.function = c08081;
            }

            @Override // io.reactivex.rxjava3.functions.Function
            public final /* synthetic */ Object apply(Object obj) {
                return this.function.invoke(obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(map, "map(...)");
        Observable observableCast2 = map.filter(new Predicate() { // from class: com.onfido.workflow.internal.ui.processor.CaptureDocumentHelper$observeCaptureActivityResultForSide$$inlined$filterIsInstance$2
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it instanceof CaptureResult.DocumentUpload;
            }
        }).cast(CaptureResult.DocumentUpload.class);
        Intrinsics.checkNotNullExpressionValue(observableCast2, "cast(...)");
        Observable<CaptureResult.DocumentUpload> observableFilter = observableCast2.filter(new Predicate() { // from class: com.onfido.workflow.internal.ui.processor.CaptureDocumentHelper.observeCaptureActivityResultForSide.2
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(CaptureResult.DocumentUpload it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.getCaptureStepDataBundle().getDocSide() == docSide;
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableFilter, "filter(...)");
        return observableFilter;
    }

    private final Observable<CaptureResult.DocumentUpload> observeCaptureFragmentResultForSide(Observable<UIEvent> observable, final DocSide docSide) {
        Observable<U> observableCast = observable.filter(new Predicate() { // from class: com.onfido.workflow.internal.ui.processor.CaptureDocumentHelper$observeCaptureFragmentResultForSide$$inlined$filterIsInstance$1
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it instanceof UIEvent.OnFragmentResult.OnDocumentCaptureFragmentResult;
            }
        }).cast(UIEvent.OnFragmentResult.OnDocumentCaptureFragmentResult.class);
        Intrinsics.checkNotNullExpressionValue(observableCast, "cast(...)");
        Observable map = observableCast.map(new Function() { // from class: com.onfido.workflow.internal.ui.processor.CaptureDocumentHelper.observeCaptureFragmentResultForSide.1
            @Override // io.reactivex.rxjava3.functions.Function
            public final DocumentCaptureScreen.DocumentCaptureResult apply(UIEvent.OnFragmentResult.OnDocumentCaptureFragmentResult it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.getResult();
            }
        });
        Intrinsics.checkNotNullExpressionValue(map, "map(...)");
        Observable observableCast2 = map.filter(new Predicate() { // from class: com.onfido.workflow.internal.ui.processor.CaptureDocumentHelper$observeCaptureFragmentResultForSide$$inlined$filterIsInstance$2
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it instanceof DocumentCaptureScreen.DocumentCaptureResult.Completed;
            }
        }).cast(DocumentCaptureScreen.DocumentCaptureResult.Completed.class);
        Intrinsics.checkNotNullExpressionValue(observableCast2, "cast(...)");
        Observable<CaptureResult.DocumentUpload> map2 = observableCast2.filter(new Predicate() { // from class: com.onfido.workflow.internal.ui.processor.CaptureDocumentHelper.observeCaptureFragmentResultForSide.2
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(DocumentCaptureScreen.DocumentCaptureResult.Completed it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.getDocumentData().getDocSide() == docSide;
            }
        }).map(new Function() { // from class: com.onfido.workflow.internal.ui.processor.CaptureDocumentHelper.observeCaptureFragmentResultForSide.3
            @Override // io.reactivex.rxjava3.functions.Function
            public final CaptureResult.DocumentUpload apply(DocumentCaptureScreen.DocumentCaptureResult.Completed it) {
                DocumentUploadPayload.UploadedArtifactPayload uploadedArtifactPayload;
                Intrinsics.checkNotNullParameter(it, "it");
                CaptureStepDataBundle documentData = it.getDocumentData();
                if (it.getNfcProperties() != null) {
                    String uploadId = it.getUploadId();
                    String videoUploadId = it.getVideoUploadId();
                    NfcProperties nfcProperties = it.getNfcProperties();
                    Intrinsics.checkNotNull(nfcProperties);
                    uploadedArtifactPayload = new DocumentUploadPayload.ScannedNfcPayload(uploadId, videoUploadId, nfcProperties, it.isNfcSupported());
                } else {
                    uploadedArtifactPayload = new DocumentUploadPayload.UploadedArtifactPayload(it.getUploadId(), it.getVideoUploadId());
                }
                return new CaptureResult.DocumentUpload(documentData, uploadedArtifactPayload);
            }
        });
        Intrinsics.checkNotNullExpressionValue(map2, "map(...)");
        return map2;
    }
}
