package com.onfido.workflow.internal.ui.processor.nfc;

import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.core.OnfidoLauncher;
import com.onfido.android.sdk.capture.internal.navigation.Navigator;
import com.onfido.android.sdk.capture.model.NFCOptions;
import com.onfido.android.sdk.capture.model.NFCOptionsKt;
import com.onfido.android.sdk.capture.ui.nfc.NfcHostFragment;
import com.onfido.android.sdk.capture.ui.nfc.model.NfcProperties;
import com.onfido.android.sdk.capture.upload.ErrorType;
import com.onfido.android.sdk.capture.utils.CountryCode;
import com.onfido.javax.inject.Inject;
import com.onfido.workflow.internal.ui.LoadingScreen;
import com.onfido.workflow.internal.ui.NfcFlowScreen;
import com.onfido.workflow.internal.ui.model.MediaUpload;
import com.onfido.workflow.internal.ui.model.UIEvent;
import com.onfido.workflow.internal.ui.processor.nfc.NfcFlowHelper;
import com.onfido.workflow.internal.ui.processor.nfc.WorkflowNfcCreateDocumentUseCase;
import com.onfido.workflow.internal.utils.NavigatorExtKt;
import com.onfido.workflow.internal.workflow.WorkflowTask;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.ObservableTransformer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;

/* compiled from: NfcFlowHelper.kt */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001:\u0001%B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J!\u0010\u0007\u001a\r\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\b\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0002J)\u0010\u000e\u001a\r\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\b\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J*\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\t0\b2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\b2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0002JR\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\t0\b2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\b2\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eJ\u000e\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0002J\u0014\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\t0!H\u0002J\f\u0010#\u001a\u00020$*\u00020\u001eH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lcom/onfido/workflow/internal/ui/processor/nfc/NfcFlowHelper;", "", "navigator", "Lcom/onfido/android/sdk/capture/internal/navigation/Navigator;", "createDocumentUseCase", "Lcom/onfido/workflow/internal/ui/processor/nfc/WorkflowNfcCreateDocumentUseCase;", "(Lcom/onfido/android/sdk/capture/internal/navigation/Navigator;Lcom/onfido/workflow/internal/ui/processor/nfc/WorkflowNfcCreateDocumentUseCase;)V", "handleNfcScanSkipped", "Lio/reactivex/rxjava3/core/Observable;", "Lcom/onfido/workflow/internal/ui/processor/nfc/NfcFlowHelper$NfcFlowOutcome;", "Lio/reactivex/rxjava3/annotations/NonNull;", "mediaUploads", "", "Lcom/onfido/workflow/internal/ui/model/MediaUpload;", "handleNfcScanSuccess", OnfidoLauncher.KEY_RESULT, "Lcom/onfido/android/sdk/capture/ui/nfc/NfcHostFragment$NfcHostResult$NfcScanSuccess;", "observeNfcFlowResult", "uiEventsObservable", "Lcom/onfido/workflow/internal/ui/model/UIEvent;", "process", "documentType", "Lcom/onfido/android/sdk/capture/DocumentType;", RemoteConfigConstants.RequestFieldKey.COUNTRY_CODE, "Lcom/onfido/android/sdk/capture/utils/CountryCode;", "nfcProperties", "Lcom/onfido/android/sdk/capture/ui/nfc/model/NfcProperties;", "nfcOptions", "Lcom/onfido/android/sdk/capture/model/NFCOptions;", "documentTask", "Lcom/onfido/workflow/internal/workflow/WorkflowTask$DocumentTask;", "showCaptureScreen", "transformCreateDocumentUseCaseOutcomeToNfcFlowOutcome", "Lio/reactivex/rxjava3/core/ObservableTransformer;", "Lcom/onfido/workflow/internal/ui/processor/nfc/WorkflowNfcCreateDocumentUseCase$Outcome;", "isOnlyOneDocAvailable", "", "NfcFlowOutcome", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class NfcFlowHelper {
    private final WorkflowNfcCreateDocumentUseCase createDocumentUseCase;
    private final Navigator navigator;

    @Inject
    public NfcFlowHelper(Navigator navigator, WorkflowNfcCreateDocumentUseCase createDocumentUseCase) {
        Intrinsics.checkNotNullParameter(navigator, "navigator");
        Intrinsics.checkNotNullParameter(createDocumentUseCase, "createDocumentUseCase");
        this.navigator = navigator;
        this.createDocumentUseCase = createDocumentUseCase;
    }

    /* compiled from: NfcFlowHelper.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0003\u0004\u0005\u0006B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0004\u0007\b\t\n¨\u0006\u000b"}, d2 = {"Lcom/onfido/workflow/internal/ui/processor/nfc/NfcFlowHelper$NfcFlowOutcome;", "", "()V", "Error", "ExitFlow", "ShowCaptureScreen", "Success", "Lcom/onfido/workflow/internal/ui/processor/nfc/NfcFlowHelper$NfcFlowOutcome$Error;", "Lcom/onfido/workflow/internal/ui/processor/nfc/NfcFlowHelper$NfcFlowOutcome$ExitFlow;", "Lcom/onfido/workflow/internal/ui/processor/nfc/NfcFlowHelper$NfcFlowOutcome$ShowCaptureScreen;", "Lcom/onfido/workflow/internal/ui/processor/nfc/NfcFlowHelper$NfcFlowOutcome$Success;", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static abstract class NfcFlowOutcome {
        public /* synthetic */ NfcFlowOutcome(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* compiled from: NfcFlowHelper.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/onfido/workflow/internal/ui/processor/nfc/NfcFlowHelper$NfcFlowOutcome$Success;", "Lcom/onfido/workflow/internal/ui/processor/nfc/NfcFlowHelper$NfcFlowOutcome;", "mediaUploads", "", "Lcom/onfido/workflow/internal/ui/model/MediaUpload;", "(Ljava/util/List;)V", "getMediaUploads", "()Ljava/util/List;", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Success extends NfcFlowOutcome {
            private final List<MediaUpload> mediaUploads;

            public final List<MediaUpload> getMediaUploads() {
                return this.mediaUploads;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public Success(List<MediaUpload> mediaUploads) {
                super(null);
                Intrinsics.checkNotNullParameter(mediaUploads, "mediaUploads");
                this.mediaUploads = mediaUploads;
            }
        }

        private NfcFlowOutcome() {
        }

        /* compiled from: NfcFlowHelper.kt */
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/onfido/workflow/internal/ui/processor/nfc/NfcFlowHelper$NfcFlowOutcome$Error;", "Lcom/onfido/workflow/internal/ui/processor/nfc/NfcFlowHelper$NfcFlowOutcome;", "error", "Lcom/onfido/android/sdk/capture/upload/ErrorType;", "(Lcom/onfido/android/sdk/capture/upload/ErrorType;)V", "getError", "()Lcom/onfido/android/sdk/capture/upload/ErrorType;", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Error extends NfcFlowOutcome {
            private final ErrorType error;

            public final ErrorType getError() {
                return this.error;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public Error(ErrorType error) {
                super(null);
                Intrinsics.checkNotNullParameter(error, "error");
                this.error = error;
            }
        }

        /* compiled from: NfcFlowHelper.kt */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/workflow/internal/ui/processor/nfc/NfcFlowHelper$NfcFlowOutcome$ExitFlow;", "Lcom/onfido/workflow/internal/ui/processor/nfc/NfcFlowHelper$NfcFlowOutcome;", "()V", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class ExitFlow extends NfcFlowOutcome {
            public static final ExitFlow INSTANCE = new ExitFlow();

            private ExitFlow() {
                super(null);
            }
        }

        /* compiled from: NfcFlowHelper.kt */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/workflow/internal/ui/processor/nfc/NfcFlowHelper$NfcFlowOutcome$ShowCaptureScreen;", "Lcom/onfido/workflow/internal/ui/processor/nfc/NfcFlowHelper$NfcFlowOutcome;", "()V", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class ShowCaptureScreen extends NfcFlowOutcome {
            public static final ShowCaptureScreen INSTANCE = new ShowCaptureScreen();

            private ShowCaptureScreen() {
                super(null);
            }
        }
    }

    private static final Observable<NfcFlowOutcome> process$navigateNfcScreen(final NfcFlowHelper nfcFlowHelper, Observable<UIEvent> observable, List<MediaUpload> list, final DocumentType documentType, final CountryCode countryCode, final NfcProperties nfcProperties, final WorkflowTask.DocumentTask documentTask, final NFCOptions.Enabled enabled) {
        Observable<NfcFlowOutcome> observableAndThen = Completable.fromAction(new Action() { // from class: com.onfido.workflow.internal.ui.processor.nfc.NfcFlowHelper$$ExternalSyntheticLambda2
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                NfcFlowHelper.process$navigateNfcScreen$lambda$0(this.f$0, documentType, countryCode, nfcProperties, enabled, documentTask);
            }
        }).andThen(nfcFlowHelper.observeNfcFlowResult(observable, list));
        Intrinsics.checkNotNullExpressionValue(observableAndThen, "andThen(...)");
        return observableAndThen;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void process$navigateNfcScreen$lambda$0(NfcFlowHelper this$0, DocumentType documentType, CountryCode countryCode, NfcProperties nfcProperties, NFCOptions.Enabled nfcOptions, WorkflowTask.DocumentTask documentTask) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(documentType, "$documentType");
        Intrinsics.checkNotNullParameter(nfcProperties, "$nfcProperties");
        Intrinsics.checkNotNullParameter(nfcOptions, "$nfcOptions");
        Intrinsics.checkNotNullParameter(documentTask, "$documentTask");
        this$0.navigator.navigateTo(new NfcFlowScreen(documentType, countryCode, nfcProperties, nfcOptions, this$0.isOnlyOneDocAvailable(documentTask), false, 32, null));
    }

    public final Observable<NfcFlowOutcome> process(Observable<UIEvent> uiEventsObservable, DocumentType documentType, CountryCode countryCode, List<MediaUpload> mediaUploads, NfcProperties nfcProperties, NFCOptions nfcOptions, WorkflowTask.DocumentTask documentTask) {
        Intrinsics.checkNotNullParameter(uiEventsObservable, "uiEventsObservable");
        Intrinsics.checkNotNullParameter(documentType, "documentType");
        Intrinsics.checkNotNullParameter(mediaUploads, "mediaUploads");
        Intrinsics.checkNotNullParameter(nfcProperties, "nfcProperties");
        Intrinsics.checkNotNullParameter(nfcOptions, "nfcOptions");
        Intrinsics.checkNotNullParameter(documentTask, "documentTask");
        if (nfcOptions instanceof NFCOptions.Enabled) {
            if (!NFCOptionsKt.isRequired(nfcOptions) && (!nfcProperties.getIsNfcSupported() || nfcProperties.getNfcKey().length() == 0)) {
                return handleNfcScanSkipped(mediaUploads);
            }
            return process$navigateNfcScreen(this, uiEventsObservable, mediaUploads, documentType, countryCode, nfcProperties, documentTask, (NFCOptions.Enabled) nfcOptions);
        }
        return handleNfcScanSkipped(mediaUploads);
    }

    private final boolean isOnlyOneDocAvailable(WorkflowTask.DocumentTask documentTask) {
        Map<CountryCode, List<DocumentType>> supportedDocs = documentTask.getSupportedDocs();
        ArrayList arrayList = new ArrayList();
        Iterator<Map.Entry<CountryCode, List<DocumentType>>> it = supportedDocs.entrySet().iterator();
        while (it.hasNext()) {
            CollectionsKt.addAll(arrayList, it.next().getValue());
        }
        return arrayList.size() == 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Observable<NfcFlowOutcome> showCaptureScreen() {
        Observable<NfcFlowOutcome> observableFromCallable = Observable.fromCallable(new Callable() { // from class: com.onfido.workflow.internal.ui.processor.nfc.NfcFlowHelper$$ExternalSyntheticLambda1
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return NfcFlowHelper.showCaptureScreen$lambda$2(this.f$0);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableFromCallable, "fromCallable(...)");
        return observableFromCallable;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final NfcFlowOutcome showCaptureScreen$lambda$2(NfcFlowHelper this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        NavigatorExtKt.backToWorkflowRoot(this$0.navigator);
        return NfcFlowOutcome.ShowCaptureScreen.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Observable<NfcFlowOutcome> handleNfcScanSuccess(List<MediaUpload> mediaUploads, NfcHostFragment.NfcHostResult.NfcScanSuccess result) {
        Observable observableCompose = this.createDocumentUseCase.execute(mediaUploads, result).toObservable().compose(transformCreateDocumentUseCaseOutcomeToNfcFlowOutcome());
        Intrinsics.checkNotNullExpressionValue(observableCompose, "compose(...)");
        return observableCompose;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Observable<NfcFlowOutcome> handleNfcScanSkipped(List<MediaUpload> mediaUploads) {
        Observable observableCompose = this.createDocumentUseCase.execute(mediaUploads, null).toObservable().compose(transformCreateDocumentUseCaseOutcomeToNfcFlowOutcome());
        Intrinsics.checkNotNullExpressionValue(observableCompose, "compose(...)");
        return observableCompose;
    }

    private final ObservableTransformer<WorkflowNfcCreateDocumentUseCase.Outcome, NfcFlowOutcome> transformCreateDocumentUseCaseOutcomeToNfcFlowOutcome() {
        return new ObservableTransformer() { // from class: com.onfido.workflow.internal.ui.processor.nfc.NfcFlowHelper$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.core.ObservableTransformer
            public final ObservableSource apply(Observable observable) {
                return NfcFlowHelper.transformCreateDocumentUseCaseOutcomeToNfcFlowOutcome$lambda$3(this.f$0, observable);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ObservableSource transformCreateDocumentUseCaseOutcomeToNfcFlowOutcome$lambda$3(final NfcFlowHelper this$0, Observable observable) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(observable, "observable");
        return observable.doOnNext(new Consumer() { // from class: com.onfido.workflow.internal.ui.processor.nfc.NfcFlowHelper$transformCreateDocumentUseCaseOutcomeToNfcFlowOutcome$1$1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(WorkflowNfcCreateDocumentUseCase.Outcome it) {
                Intrinsics.checkNotNullParameter(it, "it");
                NavigatorExtKt.backToWorkflowRoot(this.this$0.navigator);
            }
        }).doOnSubscribe(new Consumer() { // from class: com.onfido.workflow.internal.ui.processor.nfc.NfcFlowHelper$transformCreateDocumentUseCaseOutcomeToNfcFlowOutcome$1$2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Disposable it) {
                Intrinsics.checkNotNullParameter(it, "it");
                this.this$0.navigator.navigateTo(LoadingScreen.INSTANCE);
            }
        }).map(new Function() { // from class: com.onfido.workflow.internal.ui.processor.nfc.NfcFlowHelper$transformCreateDocumentUseCaseOutcomeToNfcFlowOutcome$1$3
            @Override // io.reactivex.rxjava3.functions.Function
            public final NfcFlowHelper.NfcFlowOutcome apply(WorkflowNfcCreateDocumentUseCase.Outcome outcome) {
                Intrinsics.checkNotNullParameter(outcome, "outcome");
                if (outcome instanceof WorkflowNfcCreateDocumentUseCase.Outcome.Failure) {
                    return new NfcFlowHelper.NfcFlowOutcome.Error(((WorkflowNfcCreateDocumentUseCase.Outcome.Failure) outcome).getErrorType());
                }
                if (outcome instanceof WorkflowNfcCreateDocumentUseCase.Outcome.Success) {
                    return new NfcFlowHelper.NfcFlowOutcome.Success(((WorkflowNfcCreateDocumentUseCase.Outcome.Success) outcome).getMediaUploads());
                }
                throw new NoWhenBranchMatchedException();
            }
        });
    }

    private final Observable<NfcFlowOutcome> observeNfcFlowResult(Observable<UIEvent> uiEventsObservable, final List<MediaUpload> mediaUploads) {
        Observable<U> observableCast = uiEventsObservable.filter(new Predicate() { // from class: com.onfido.workflow.internal.ui.processor.nfc.NfcFlowHelper$observeNfcFlowResult$$inlined$filterIsInstance$1
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it instanceof UIEvent.OnFragmentResult.OnNfcFlowResult;
            }
        }).cast(UIEvent.OnFragmentResult.OnNfcFlowResult.class);
        Intrinsics.checkNotNullExpressionValue(observableCast, "cast(...)");
        final AnonymousClass1 anonymousClass1 = new PropertyReference1Impl() { // from class: com.onfido.workflow.internal.ui.processor.nfc.NfcFlowHelper.observeNfcFlowResult.1
            @Override // kotlin.jvm.internal.PropertyReference1Impl, kotlin.reflect.KProperty1
            public Object get(Object obj) {
                return ((UIEvent.OnFragmentResult.OnNfcFlowResult) obj).getResult();
            }
        };
        Observable<NfcFlowOutcome> observableFlatMap = observableCast.map(new Function(anonymousClass1) { // from class: com.onfido.workflow.internal.ui.processor.nfc.NfcFlowHelper$sam$io_reactivex_rxjava3_functions_Function$0
            private final /* synthetic */ Function1 function;

            {
                Intrinsics.checkNotNullParameter(anonymousClass1, "function");
                this.function = anonymousClass1;
            }

            @Override // io.reactivex.rxjava3.functions.Function
            public final /* synthetic */ Object apply(Object obj) {
                return this.function.invoke(obj);
            }
        }).flatMap(new Function() { // from class: com.onfido.workflow.internal.ui.processor.nfc.NfcFlowHelper.observeNfcFlowResult.2
            @Override // io.reactivex.rxjava3.functions.Function
            public final ObservableSource<? extends NfcFlowOutcome> apply(NfcHostFragment.NfcHostResult result) {
                Observable observableJust;
                Intrinsics.checkNotNullParameter(result, "result");
                if (Intrinsics.areEqual(result, NfcHostFragment.NfcHostResult.Exit.INSTANCE)) {
                    observableJust = NfcFlowHelper.this.showCaptureScreen();
                } else if (Intrinsics.areEqual(result, NfcHostFragment.NfcHostResult.NfcScanSkipped.INSTANCE)) {
                    observableJust = NfcFlowHelper.this.handleNfcScanSkipped(mediaUploads);
                } else if (result instanceof NfcHostFragment.NfcHostResult.NfcScanSuccess) {
                    observableJust = NfcFlowHelper.this.handleNfcScanSuccess(mediaUploads, (NfcHostFragment.NfcHostResult.NfcScanSuccess) result);
                } else {
                    if (!Intrinsics.areEqual(result, NfcHostFragment.NfcHostResult.ExitOnfidoFlow.INSTANCE)) {
                        throw new NoWhenBranchMatchedException();
                    }
                    observableJust = Observable.just(NfcFlowOutcome.ExitFlow.INSTANCE);
                    Intrinsics.checkNotNullExpressionValue(observableJust, "just(...)");
                }
                return observableJust;
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableFlatMap, "flatMap(...)");
        return observableFlatMap;
    }
}
