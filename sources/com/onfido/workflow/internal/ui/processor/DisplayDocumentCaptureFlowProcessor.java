package com.onfido.workflow.internal.ui.processor;

import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.DocumentFormat;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.flow.CaptureStepDataBundle;
import com.onfido.android.sdk.capture.flow.NfcArguments;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.navigation.Navigator;
import com.onfido.android.sdk.capture.internal.navigation.screens.DocumentSelectionScreen;
import com.onfido.android.sdk.capture.internal.nfc.NfcInteractor;
import com.onfido.android.sdk.capture.model.NFCOptions;
import com.onfido.android.sdk.capture.ui.CaptureType;
import com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureScreen;
import com.onfido.android.sdk.capture.utils.CountryCode;
import com.onfido.android.sdk.workflow.internal.workflow.tasks.documentupload.WorkflowSupportedDocumentsStore;
import com.onfido.api.client.data.DocSide;
import com.onfido.javax.inject.Inject;
import com.onfido.workflow.internal.ui.NfcNotSupportedScreen;
import com.onfido.workflow.internal.ui.model.UIEvent;
import com.onfido.workflow.internal.ui.processor.CaptureDocumentHelper;
import com.onfido.workflow.internal.workflow.WorkflowTask;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DisplayDocumentCaptureFlowProcessor.kt */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001:\u0001$B7\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ,\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u00102\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u001e\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J>\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u00102\u0018\u0010\u001c\u001a\u0014\u0012\u0004\u0012\u00020\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020 0\u001f0\u001d2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u00102\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\"\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0016\u001a\u00020\u00172\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u0010J$\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0016\u001a\u00020\u00172\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u0010H\u0002J\u001c\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u0010H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lcom/onfido/workflow/internal/ui/processor/DisplayDocumentCaptureFlowProcessor;", "", "navigator", "Lcom/onfido/android/sdk/capture/internal/navigation/Navigator;", "permissionsFlowHelper", "Lcom/onfido/workflow/internal/ui/processor/PermissionsFlowHelper;", "captureDocumentHelper", "Lcom/onfido/workflow/internal/ui/processor/CaptureDocumentHelper;", "workflowSupportedDocumentsStore", "Lcom/onfido/android/sdk/workflow/internal/workflow/tasks/documentupload/WorkflowSupportedDocumentsStore;", "nfcInteractor", "Lcom/onfido/android/sdk/capture/internal/nfc/NfcInteractor;", "onfidoRemoteConfig", "Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;", "(Lcom/onfido/android/sdk/capture/internal/navigation/Navigator;Lcom/onfido/workflow/internal/ui/processor/PermissionsFlowHelper;Lcom/onfido/workflow/internal/ui/processor/CaptureDocumentHelper;Lcom/onfido/android/sdk/workflow/internal/workflow/tasks/documentupload/WorkflowSupportedDocumentsStore;Lcom/onfido/android/sdk/capture/internal/nfc/NfcInteractor;Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;)V", "checkRuntimePermissionsThenOpenDocumentCapture", "Lio/reactivex/rxjava3/core/Observable;", "Lcom/onfido/workflow/internal/ui/processor/DisplayDocumentCaptureFlowProcessor$ProcessorOutcome;", "uiEventObservable", "Lcom/onfido/workflow/internal/ui/model/UIEvent;", "captureStepDataBundle", "Lcom/onfido/android/sdk/capture/flow/CaptureStepDataBundle;", "documentTask", "Lcom/onfido/workflow/internal/workflow/WorkflowTask$DocumentTask;", "launchDocumentCapture", "captureDataBundle", "openDocumentSelectionScreen", "Lcom/onfido/workflow/internal/ui/model/UIEvent$OnFragmentResult$DocumentSelectionFragmentResult;", "supportedDocs", "", "Lcom/onfido/android/sdk/capture/utils/CountryCode;", "", "Lcom/onfido/android/sdk/capture/DocumentType;", "process", "processDocumentCapture", "processNfcNotSupported", "ProcessorOutcome", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class DisplayDocumentCaptureFlowProcessor {
    private final CaptureDocumentHelper captureDocumentHelper;
    private final Navigator navigator;
    private final NfcInteractor nfcInteractor;
    private final OnfidoRemoteConfig onfidoRemoteConfig;
    private final PermissionsFlowHelper permissionsFlowHelper;
    private final WorkflowSupportedDocumentsStore workflowSupportedDocumentsStore;

    @Inject
    public DisplayDocumentCaptureFlowProcessor(Navigator navigator, PermissionsFlowHelper permissionsFlowHelper, CaptureDocumentHelper captureDocumentHelper, WorkflowSupportedDocumentsStore workflowSupportedDocumentsStore, NfcInteractor nfcInteractor, OnfidoRemoteConfig onfidoRemoteConfig) {
        Intrinsics.checkNotNullParameter(navigator, "navigator");
        Intrinsics.checkNotNullParameter(permissionsFlowHelper, "permissionsFlowHelper");
        Intrinsics.checkNotNullParameter(captureDocumentHelper, "captureDocumentHelper");
        Intrinsics.checkNotNullParameter(workflowSupportedDocumentsStore, "workflowSupportedDocumentsStore");
        Intrinsics.checkNotNullParameter(nfcInteractor, "nfcInteractor");
        Intrinsics.checkNotNullParameter(onfidoRemoteConfig, "onfidoRemoteConfig");
        this.navigator = navigator;
        this.permissionsFlowHelper = permissionsFlowHelper;
        this.captureDocumentHelper = captureDocumentHelper;
        this.workflowSupportedDocumentsStore = workflowSupportedDocumentsStore;
        this.nfcInteractor = nfcInteractor;
        this.onfidoRemoteConfig = onfidoRemoteConfig;
    }

    public final Observable<ProcessorOutcome> process(WorkflowTask.DocumentTask documentTask, Observable<UIEvent> uiEventObservable) {
        Intrinsics.checkNotNullParameter(documentTask, "documentTask");
        Intrinsics.checkNotNullParameter(uiEventObservable, "uiEventObservable");
        if (Intrinsics.areEqual(documentTask.getNfcOptions(), NFCOptions.Enabled.Required.INSTANCE) && !this.nfcInteractor.isNfcSupported()) {
            return processNfcNotSupported(uiEventObservable);
        }
        return processDocumentCapture(documentTask, uiEventObservable);
    }

    private final Observable<ProcessorOutcome> processDocumentCapture(final WorkflowTask.DocumentTask documentTask, final Observable<UIEvent> uiEventObservable) {
        Observable<ProcessorOutcome> observableRetry = Completable.fromAction(new Action() { // from class: com.onfido.workflow.internal.ui.processor.DisplayDocumentCaptureFlowProcessor$$ExternalSyntheticLambda2
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                DisplayDocumentCaptureFlowProcessor.processDocumentCapture$lambda$0(this.f$0, documentTask);
            }
        }).andThen(openDocumentSelectionScreen(documentTask.getSupportedDocs(), uiEventObservable, documentTask)).switchMap(new Function() { // from class: com.onfido.workflow.internal.ui.processor.DisplayDocumentCaptureFlowProcessor.processDocumentCapture.2
            @Override // io.reactivex.rxjava3.functions.Function
            public final ObservableSource<? extends ProcessorOutcome> apply(UIEvent.OnFragmentResult.DocumentSelectionFragmentResult documentTypeSelectionResult) {
                Intrinsics.checkNotNullParameter(documentTypeSelectionResult, "documentTypeSelectionResult");
                return DisplayDocumentCaptureFlowProcessor.this.checkRuntimePermissionsThenOpenDocumentCapture(uiEventObservable, new CaptureStepDataBundle(CaptureType.DOCUMENT, documentTypeSelectionResult.getDocumentType(), documentTypeSelectionResult.getCountryCode(), null, DocSide.FRONT, null, null, 104, null), documentTask);
            }
        }).mergeWith(this.captureDocumentHelper.captureDocumentAndSubmit(documentTask, uiEventObservable)).retry(new Predicate() { // from class: com.onfido.workflow.internal.ui.processor.DisplayDocumentCaptureFlowProcessor.processDocumentCapture.3
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Throwable error) {
                Intrinsics.checkNotNullParameter(error, "error");
                return error instanceof CaptureDocumentHelper.ShowCaptureScreenException;
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableRetry, "retry(...)");
        return observableRetry;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void processDocumentCapture$lambda$0(DisplayDocumentCaptureFlowProcessor this$0, WorkflowTask.DocumentTask documentTask) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(documentTask, "$documentTask");
        this$0.workflowSupportedDocumentsStore.storeWorkflowSupportedDocuments(documentTask.getSupportedDocs());
    }

    private final Observable<UIEvent.OnFragmentResult.DocumentSelectionFragmentResult> openDocumentSelectionScreen(Map<CountryCode, ? extends List<? extends DocumentType>> supportedDocs, Observable<UIEvent> uiEventObservable, final WorkflowTask.DocumentTask documentTask) {
        if (supportedDocs.size() == 1 && ((List) CollectionsKt.first(supportedDocs.values())).size() == 1) {
            Observable<UIEvent.OnFragmentResult.DocumentSelectionFragmentResult> observableJust = Observable.just(new UIEvent.OnFragmentResult.DocumentSelectionFragmentResult((DocumentType) CollectionsKt.first((List) CollectionsKt.first(supportedDocs.values())), (CountryCode) CollectionsKt.first(supportedDocs.keySet())));
            Intrinsics.checkNotNull(observableJust);
            return observableJust;
        }
        final CountryCode countryCode = supportedDocs.keySet().size() == 1 ? (CountryCode) CollectionsKt.first(supportedDocs.keySet()) : null;
        Completable completableFromAction = Completable.fromAction(new Action() { // from class: com.onfido.workflow.internal.ui.processor.DisplayDocumentCaptureFlowProcessor$$ExternalSyntheticLambda1
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                DisplayDocumentCaptureFlowProcessor.openDocumentSelectionScreen$lambda$1(this.f$0, countryCode, documentTask);
            }
        });
        ObservableSource observableSourceCast = uiEventObservable.filter(new Predicate() { // from class: com.onfido.workflow.internal.ui.processor.DisplayDocumentCaptureFlowProcessor$openDocumentSelectionScreen$$inlined$filterIsInstance$1
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it instanceof UIEvent.OnFragmentResult.DocumentSelectionFragmentResult;
            }
        }).cast(UIEvent.OnFragmentResult.DocumentSelectionFragmentResult.class);
        Intrinsics.checkNotNullExpressionValue(observableSourceCast, "cast(...)");
        Observable<UIEvent.OnFragmentResult.DocumentSelectionFragmentResult> observableAndThen = completableFromAction.andThen(observableSourceCast);
        Intrinsics.checkNotNull(observableAndThen);
        return observableAndThen;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void openDocumentSelectionScreen$lambda$1(DisplayDocumentCaptureFlowProcessor this$0, CountryCode countryCode, WorkflowTask.DocumentTask documentTask) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(documentTask, "$documentTask");
        this$0.navigator.navigateTo(new DocumentSelectionScreen(countryCode, null, Intrinsics.areEqual(documentTask.getNfcOptions(), NFCOptions.Enabled.Required.INSTANCE), true, 2, null));
    }

    private final Observable<ProcessorOutcome> processNfcNotSupported(Observable<UIEvent> uiEventObservable) {
        this.navigator.navigateTo(NfcNotSupportedScreen.INSTANCE);
        Observable<U> observableCast = uiEventObservable.filter(new Predicate() { // from class: com.onfido.workflow.internal.ui.processor.DisplayDocumentCaptureFlowProcessor$processNfcNotSupported$$inlined$filterIsInstance$1
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it instanceof UIEvent.OnFragmentResult.OnNfcNotSupportedFragmentResult;
            }
        }).cast(UIEvent.OnFragmentResult.OnNfcNotSupportedFragmentResult.class);
        Intrinsics.checkNotNullExpressionValue(observableCast, "cast(...)");
        Observable<ProcessorOutcome> map = observableCast.map(new Function() { // from class: com.onfido.workflow.internal.ui.processor.DisplayDocumentCaptureFlowProcessor.processNfcNotSupported.1
            @Override // io.reactivex.rxjava3.functions.Function
            public final ProcessorOutcome apply(UIEvent.OnFragmentResult.OnNfcNotSupportedFragmentResult it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return ProcessorOutcome.ExitNfcFlow.INSTANCE;
            }
        });
        Intrinsics.checkNotNullExpressionValue(map, "map(...)");
        return map;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Observable<ProcessorOutcome> checkRuntimePermissionsThenOpenDocumentCapture(Observable<UIEvent> uiEventObservable, CaptureStepDataBundle captureStepDataBundle, final WorkflowTask.DocumentTask documentTask) {
        Observable observableFlatMap = this.permissionsFlowHelper.checkRuntimePermissions(uiEventObservable, captureStepDataBundle).flatMap(new Function() { // from class: com.onfido.workflow.internal.ui.processor.DisplayDocumentCaptureFlowProcessor.checkRuntimePermissionsThenOpenDocumentCapture.1
            @Override // io.reactivex.rxjava3.functions.Function
            public final ObservableSource<? extends ProcessorOutcome> apply(CaptureStepDataBundle captureDataBundle) {
                Intrinsics.checkNotNullParameter(captureDataBundle, "captureDataBundle");
                return DisplayDocumentCaptureFlowProcessor.this.launchDocumentCapture(captureDataBundle, documentTask);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableFlatMap, "flatMap(...)");
        return observableFlatMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Observable<ProcessorOutcome> launchDocumentCapture(final CaptureStepDataBundle captureDataBundle, final WorkflowTask.DocumentTask documentTask) {
        if (!this.onfidoRemoteConfig.isRefactoredCaptureEnabled()) {
            DocumentType documentType = captureDataBundle.getDocumentType();
            if (documentType == null) {
                throw new IllegalArgumentException("Required value was null.".toString());
            }
            DocSide docSide = captureDataBundle.getDocSide();
            if (docSide == null) {
                throw new IllegalArgumentException("Required value was null.".toString());
            }
            Observable<ProcessorOutcome> observableJust = Observable.just(new ProcessorOutcome.OpenDocumentCaptureActivity(documentType, docSide, captureDataBundle.getCountryCode(), captureDataBundle.getDocumentFormat(), new NfcArguments(documentTask.getNfcOptions(), null, 2, null)));
            Intrinsics.checkNotNullExpressionValue(observableJust, "just(...)");
            return observableJust;
        }
        Observable<ProcessorOutcome> observable = Completable.fromAction(new Action() { // from class: com.onfido.workflow.internal.ui.processor.DisplayDocumentCaptureFlowProcessor$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                DisplayDocumentCaptureFlowProcessor.launchDocumentCapture$lambda$2(this.f$0, captureDataBundle, documentTask);
            }
        }).toObservable();
        Intrinsics.checkNotNullExpressionValue(observable, "toObservable(...)");
        return observable;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void launchDocumentCapture$lambda$2(DisplayDocumentCaptureFlowProcessor this$0, CaptureStepDataBundle captureDataBundle, WorkflowTask.DocumentTask documentTask) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(captureDataBundle, "$captureDataBundle");
        Intrinsics.checkNotNullParameter(documentTask, "$documentTask");
        Navigator navigator = this$0.navigator;
        DocSide docSide = captureDataBundle.getDocSide();
        if (docSide != null) {
            navigator.navigateTo(new DocumentCaptureScreen(captureDataBundle, docSide == DocSide.FRONT, new NfcArguments(documentTask.getNfcOptions(), null, 2, null)));
            return;
        }
        throw new IllegalArgumentException("Required value was null.".toString());
    }

    /* compiled from: DisplayDocumentCaptureFlowProcessor.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0003\u0006\u0007\b¨\u0006\t"}, d2 = {"Lcom/onfido/workflow/internal/ui/processor/DisplayDocumentCaptureFlowProcessor$ProcessorOutcome;", "", "()V", "DocumentCaptureFlowFinished", "ExitNfcFlow", "OpenDocumentCaptureActivity", "Lcom/onfido/workflow/internal/ui/processor/DisplayDocumentCaptureFlowProcessor$ProcessorOutcome$DocumentCaptureFlowFinished;", "Lcom/onfido/workflow/internal/ui/processor/DisplayDocumentCaptureFlowProcessor$ProcessorOutcome$ExitNfcFlow;", "Lcom/onfido/workflow/internal/ui/processor/DisplayDocumentCaptureFlowProcessor$ProcessorOutcome$OpenDocumentCaptureActivity;", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static abstract class ProcessorOutcome {
        public /* synthetic */ ProcessorOutcome(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private ProcessorOutcome() {
        }

        /* compiled from: DisplayDocumentCaptureFlowProcessor.kt */
        @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\tHÆ\u0003J\t\u0010\u001b\u001a\u00020\u000bHÆ\u0003J?\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\n\u001a\u00020\u000bHÆ\u0001J\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 HÖ\u0003J\t\u0010!\u001a\u00020\"HÖ\u0001J\t\u0010#\u001a\u00020$HÖ\u0001R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006%"}, d2 = {"Lcom/onfido/workflow/internal/ui/processor/DisplayDocumentCaptureFlowProcessor$ProcessorOutcome$OpenDocumentCaptureActivity;", "Lcom/onfido/workflow/internal/ui/processor/DisplayDocumentCaptureFlowProcessor$ProcessorOutcome;", "documentType", "Lcom/onfido/android/sdk/capture/DocumentType;", "docSide", "Lcom/onfido/api/client/data/DocSide;", "countrySelection", "Lcom/onfido/android/sdk/capture/utils/CountryCode;", "documentFormat", "Lcom/onfido/android/sdk/capture/DocumentFormat;", "nfcArguments", "Lcom/onfido/android/sdk/capture/flow/NfcArguments;", "(Lcom/onfido/android/sdk/capture/DocumentType;Lcom/onfido/api/client/data/DocSide;Lcom/onfido/android/sdk/capture/utils/CountryCode;Lcom/onfido/android/sdk/capture/DocumentFormat;Lcom/onfido/android/sdk/capture/flow/NfcArguments;)V", "getCountrySelection", "()Lcom/onfido/android/sdk/capture/utils/CountryCode;", "getDocSide", "()Lcom/onfido/api/client/data/DocSide;", "getDocumentFormat", "()Lcom/onfido/android/sdk/capture/DocumentFormat;", "getDocumentType", "()Lcom/onfido/android/sdk/capture/DocumentType;", "getNfcArguments", "()Lcom/onfido/android/sdk/capture/flow/NfcArguments;", "component1", "component2", "component3", "component4", "component5", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class OpenDocumentCaptureActivity extends ProcessorOutcome {
            private final CountryCode countrySelection;
            private final DocSide docSide;
            private final DocumentFormat documentFormat;
            private final DocumentType documentType;
            private final NfcArguments nfcArguments;

            public static /* synthetic */ OpenDocumentCaptureActivity copy$default(OpenDocumentCaptureActivity openDocumentCaptureActivity, DocumentType documentType, DocSide docSide, CountryCode countryCode, DocumentFormat documentFormat, NfcArguments nfcArguments, int i, Object obj) {
                if ((i & 1) != 0) {
                    documentType = openDocumentCaptureActivity.documentType;
                }
                if ((i & 2) != 0) {
                    docSide = openDocumentCaptureActivity.docSide;
                }
                DocSide docSide2 = docSide;
                if ((i & 4) != 0) {
                    countryCode = openDocumentCaptureActivity.countrySelection;
                }
                CountryCode countryCode2 = countryCode;
                if ((i & 8) != 0) {
                    documentFormat = openDocumentCaptureActivity.documentFormat;
                }
                DocumentFormat documentFormat2 = documentFormat;
                if ((i & 16) != 0) {
                    nfcArguments = openDocumentCaptureActivity.nfcArguments;
                }
                return openDocumentCaptureActivity.copy(documentType, docSide2, countryCode2, documentFormat2, nfcArguments);
            }

            /* renamed from: component1, reason: from getter */
            public final DocumentType getDocumentType() {
                return this.documentType;
            }

            /* renamed from: component2, reason: from getter */
            public final DocSide getDocSide() {
                return this.docSide;
            }

            /* renamed from: component3, reason: from getter */
            public final CountryCode getCountrySelection() {
                return this.countrySelection;
            }

            /* renamed from: component4, reason: from getter */
            public final DocumentFormat getDocumentFormat() {
                return this.documentFormat;
            }

            /* renamed from: component5, reason: from getter */
            public final NfcArguments getNfcArguments() {
                return this.nfcArguments;
            }

            public final OpenDocumentCaptureActivity copy(DocumentType documentType, DocSide docSide, CountryCode countrySelection, DocumentFormat documentFormat, NfcArguments nfcArguments) {
                Intrinsics.checkNotNullParameter(documentType, "documentType");
                Intrinsics.checkNotNullParameter(docSide, "docSide");
                Intrinsics.checkNotNullParameter(nfcArguments, "nfcArguments");
                return new OpenDocumentCaptureActivity(documentType, docSide, countrySelection, documentFormat, nfcArguments);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof OpenDocumentCaptureActivity)) {
                    return false;
                }
                OpenDocumentCaptureActivity openDocumentCaptureActivity = (OpenDocumentCaptureActivity) other;
                return this.documentType == openDocumentCaptureActivity.documentType && this.docSide == openDocumentCaptureActivity.docSide && this.countrySelection == openDocumentCaptureActivity.countrySelection && this.documentFormat == openDocumentCaptureActivity.documentFormat && Intrinsics.areEqual(this.nfcArguments, openDocumentCaptureActivity.nfcArguments);
            }

            public final CountryCode getCountrySelection() {
                return this.countrySelection;
            }

            public final DocSide getDocSide() {
                return this.docSide;
            }

            public final DocumentFormat getDocumentFormat() {
                return this.documentFormat;
            }

            public final DocumentType getDocumentType() {
                return this.documentType;
            }

            public final NfcArguments getNfcArguments() {
                return this.nfcArguments;
            }

            public int hashCode() {
                int iHashCode = ((this.documentType.hashCode() * 31) + this.docSide.hashCode()) * 31;
                CountryCode countryCode = this.countrySelection;
                int iHashCode2 = (iHashCode + (countryCode == null ? 0 : countryCode.hashCode())) * 31;
                DocumentFormat documentFormat = this.documentFormat;
                return ((iHashCode2 + (documentFormat != null ? documentFormat.hashCode() : 0)) * 31) + this.nfcArguments.hashCode();
            }

            public String toString() {
                return "OpenDocumentCaptureActivity(documentType=" + this.documentType + ", docSide=" + this.docSide + ", countrySelection=" + this.countrySelection + ", documentFormat=" + this.documentFormat + ", nfcArguments=" + this.nfcArguments + ")";
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public OpenDocumentCaptureActivity(DocumentType documentType, DocSide docSide, CountryCode countryCode, DocumentFormat documentFormat, NfcArguments nfcArguments) {
                super(null);
                Intrinsics.checkNotNullParameter(documentType, "documentType");
                Intrinsics.checkNotNullParameter(docSide, "docSide");
                Intrinsics.checkNotNullParameter(nfcArguments, "nfcArguments");
                this.documentType = documentType;
                this.docSide = docSide;
                this.countrySelection = countryCode;
                this.documentFormat = documentFormat;
                this.nfcArguments = nfcArguments;
            }
        }

        /* compiled from: DisplayDocumentCaptureFlowProcessor.kt */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/workflow/internal/ui/processor/DisplayDocumentCaptureFlowProcessor$ProcessorOutcome$DocumentCaptureFlowFinished;", "Lcom/onfido/workflow/internal/ui/processor/DisplayDocumentCaptureFlowProcessor$ProcessorOutcome;", "()V", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class DocumentCaptureFlowFinished extends ProcessorOutcome {
            public static final DocumentCaptureFlowFinished INSTANCE = new DocumentCaptureFlowFinished();

            private DocumentCaptureFlowFinished() {
                super(null);
            }
        }

        /* compiled from: DisplayDocumentCaptureFlowProcessor.kt */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/workflow/internal/ui/processor/DisplayDocumentCaptureFlowProcessor$ProcessorOutcome$ExitNfcFlow;", "Lcom/onfido/workflow/internal/ui/processor/DisplayDocumentCaptureFlowProcessor$ProcessorOutcome;", "()V", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class ExitNfcFlow extends ProcessorOutcome {
            public static final ExitNfcFlow INSTANCE = new ExitNfcFlow();

            private ExitNfcFlow() {
                super(null);
            }
        }
    }
}
