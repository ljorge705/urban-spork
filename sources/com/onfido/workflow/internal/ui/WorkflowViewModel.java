package com.onfido.workflow.internal.ui;

import androidx.exifinterface.media.ExifInterface;
import androidx.lifecycle.ViewModel;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.host.MotionHostFragment;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.FlowTracker;
import com.onfido.android.sdk.capture.internal.navigation.Navigator;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.android.sdk.capture.internal.util.logging.Timber;
import com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureScreen;
import com.onfido.android.sdk.capture.ui.camera.exception.InvalidCertificateException;
import com.onfido.android.sdk.capture.ui.camera.exception.TokenExpiredException;
import com.onfido.android.sdk.capture.ui.camera.exception.UnknownCameraException;
import com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureScreen;
import com.onfido.android.sdk.capture.ui.camera.selfie.SelfieCaptureScreen;
import com.onfido.android.sdk.capture.utils.RxExtensionsKt;
import com.onfido.android.sdk.workflow.internal.workflow.WorkflowHttpExceptionMapper;
import com.onfido.hosted.web.module.model.HostedWebModuleFailed;
import com.onfido.javax.inject.Inject;
import com.onfido.workflow.OnfidoWorkflow;
import com.onfido.workflow.WorkflowConfig;
import com.onfido.workflow.internal.data.WorkflowTaskDataSource;
import com.onfido.workflow.internal.di.WorkflowComponent;
import com.onfido.workflow.internal.di.WorkflowScope;
import com.onfido.workflow.internal.ui.model.FlowTask;
import com.onfido.workflow.internal.ui.model.OneOffUIEvent;
import com.onfido.workflow.internal.ui.model.ToolbarState;
import com.onfido.workflow.internal.ui.model.UIEvent;
import com.onfido.workflow.internal.ui.processor.BackstackEventsProcessor;
import com.onfido.workflow.internal.ui.processor.DisplayDocumentCaptureFlowProcessor;
import com.onfido.workflow.internal.ui.processor.DisplayFaceCaptureFlowProcessor;
import com.onfido.workflow.internal.ui.processor.DisplayMotionFlowProcessor;
import com.onfido.workflow.internal.ui.processor.HostedWebModuleFlowProcessor;
import com.onfido.workflow.internal.ui.processor.PoaFlowProcessor;
import com.onfido.workflow.internal.ui.processor.RetryTaskProcessor;
import com.onfido.workflow.internal.ui.processor.biometric.token.BiometricTokenRetrievalFlowProcessor;
import com.onfido.workflow.internal.ui.processor.biometric.token.BiometricTokenStorageFlowProcessor;
import com.onfido.workflow.internal.utils.NavigatorExtKt;
import com.onfido.workflow.internal.workflow.WorkflowPoller;
import com.onfido.workflow.internal.workflow.WorkflowTask;
import com.onfido.workflow.internal.workflow.model.CaptureResult;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.ObservableTransformer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.sentry.SentryEvent;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;

/* compiled from: WorkflowViewModel.kt */
@WorkflowScope
@Metadata(d1 = {"\u0000\u0088\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0001\u0018\u0000 \u0081\u00012\u00020\u0001:\u0002\u0081\u0001B\u0087\u0001\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0015\u0012\u0006\u0010\u0016\u001a\u00020\u0017\u0012\u0006\u0010\u0018\u001a\u00020\u0019\u0012\u0006\u0010\u001a\u001a\u00020\u001b\u0012\u0006\u0010\u001c\u001a\u00020\u001d\u0012\u0006\u0010\u001e\u001a\u00020\u001f\u0012\u0006\u0010 \u001a\u00020!¢\u0006\u0002\u0010\"J\u001e\u0010?\u001a\u000e\u0012\u0004\u0012\u0002HA\u0012\u0004\u0012\u0002HA0@\"\b\b\u0000\u0010A*\u00020BH\u0002J\u000e\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020,J\b\u0010F\u001a\u00020DH\u0002J\u0006\u0010G\u001a\u00020DJ\u0006\u0010H\u001a\u00020DJ\u0006\u0010I\u001a\u00020DJ\u0014\u0010J\u001a\u00020K2\n\u0010L\u001a\u00060Mj\u0002`NH\u0002J\u0010\u0010O\u001a\u00020D2\u0006\u0010P\u001a\u00020QH\u0003J\u0010\u0010R\u001a\u00020D2\u0006\u0010S\u001a\u00020TH\u0003J\u0010\u0010U\u001a\u00020D2\u0006\u0010L\u001a\u00020KH\u0002J\u0010\u0010V\u001a\u00020D2\u0006\u0010W\u001a\u00020XH\u0003J\u0010\u0010Y\u001a\u00020D2\u0006\u0010P\u001a\u00020ZH\u0003J\u0010\u0010[\u001a\u00020D2\u0006\u0010P\u001a\u00020\\H\u0002J\u0010\u0010]\u001a\u00020D2\u0006\u0010P\u001a\u00020^H\u0003J\b\u0010_\u001a\u00020DH\u0002J\b\u0010`\u001a\u00020DH\u0002J\b\u0010a\u001a\u00020DH\u0002J\b\u0010b\u001a\u00020DH\u0002J\b\u0010c\u001a\u00020DH\u0002J\b\u0010d\u001a\u00020DH\u0002J\b\u0010e\u001a\u00020DH\u0002J\b\u0010f\u001a\u00020DH\u0002J\b\u0010g\u001a\u00020DH\u0002J\b\u0010h\u001a\u00020DH\u0002J\b\u0010i\u001a\u00020DH\u0002J\b\u0010j\u001a\u00020DH\u0002J\b\u0010k\u001a\u00020DH\u0002J\b\u0010l\u001a\u00020DH\u0002J\b\u0010m\u001a\u00020DH\u0002J\b\u0010n\u001a\u00020DH\u0002J\b\u0010o\u001a\u00020DH\u0002J\b\u0010p\u001a\u00020DH\u0002J\b\u0010q\u001a\u00020DH\u0002J\b\u0010r\u001a\u00020DH\u0002J\b\u0010s\u001a\u00020DH\u0002J\b\u0010t\u001a\u00020DH\u0014J\u0006\u0010u\u001a\u00020DJ\u0012\u0010v\u001a\u00020D2\b\b\u0002\u0010w\u001a\u00020xH\u0003J\u000e\u0010y\u001a\u00020D2\u0006\u0010z\u001a\u000206J\b\u0010{\u001a\u00020DH\u0002J\b\u0010|\u001a\u00020DH\u0002J%\u0010}\u001a\b\u0012\u0004\u0012\u0002HA0:\"\n\b\u0000\u0010A\u0018\u0001*\u00020~*\b\u0012\u0004\u0012\u00020.0:H\u0082\bJ\u0018\u0010\u007f\u001a\b\u0012\u0004\u0012\u00020~0:*\b\u0012\u0004\u0012\u00020.0:H\u0002J\u000f\u0010\u0080\u0001\u001a\u000206*\u0004\u0018\u00010.H\u0002R!\u0010#\u001a\u0015\u0012\f\u0012\n &*\u0004\u0018\u00010%0%0$¢\u0006\u0002\b'X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010(\u001a\r\u0012\u0004\u0012\u00020*0)¢\u0006\u0002\b'X\u0082\u0004¢\u0006\u0002\n\u0000R!\u0010+\u001a\u0015\u0012\f\u0012\n &*\u0004\u0018\u00010,0,0$¢\u0006\u0002\b'X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R'\u0010-\u001a\b\u0012\u0004\u0012\u00020.0)8@X\u0081\u0084\u0002¢\u0006\u0012\n\u0004\b3\u00104\u0012\u0004\b/\u00100\u001a\u0004\b1\u00102R\u0010\u00105\u001a\u0004\u0018\u000106X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00107\u001a\u000208X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u00109\u001a\b\u0012\u0004\u0012\u00020%0:¢\u0006\b\n\u0000\u001a\u0004\b;\u0010<R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010=\u001a\b\u0012\u0004\u0012\u00020*0:¢\u0006\b\n\u0000\u001a\u0004\b>\u0010<R\u000e\u0010 \u001a\u00020!X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0082\u0001"}, d2 = {"Lcom/onfido/workflow/internal/ui/WorkflowViewModel;", "Landroidx/lifecycle/ViewModel;", "workflowPoller", "Lcom/onfido/workflow/internal/workflow/WorkflowPoller;", "schedulersProvider", "Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;", "navigator", "Lcom/onfido/android/sdk/capture/internal/navigation/Navigator;", "backstackEventsProcessor", "Lcom/onfido/workflow/internal/ui/processor/BackstackEventsProcessor;", "faceTaskProcessor", "Lcom/onfido/workflow/internal/ui/processor/DisplayFaceCaptureFlowProcessor;", "motionTaskProcessor", "Lcom/onfido/workflow/internal/ui/processor/DisplayMotionFlowProcessor;", "biometricTokenRetrievalFlowProcessor", "Lcom/onfido/workflow/internal/ui/processor/biometric/token/BiometricTokenRetrievalFlowProcessor;", "biometricTokenStorageFlowProcessor", "Lcom/onfido/workflow/internal/ui/processor/biometric/token/BiometricTokenStorageFlowProcessor;", "documentTaskProcessor", "Lcom/onfido/workflow/internal/ui/processor/DisplayDocumentCaptureFlowProcessor;", "retryTaskProcessor", "Lcom/onfido/workflow/internal/ui/processor/RetryTaskProcessor;", "poaTaskProcessor", "Lcom/onfido/workflow/internal/ui/processor/PoaFlowProcessor;", "hostedWebModuleFlowProcessor", "Lcom/onfido/workflow/internal/ui/processor/HostedWebModuleFlowProcessor;", "flowTracker", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/FlowTracker;", "workflowHttpExceptionMapper", "Lcom/onfido/android/sdk/workflow/internal/workflow/WorkflowHttpExceptionMapper;", "workflowTaskDataSource", "Lcom/onfido/workflow/internal/data/WorkflowTaskDataSource;", "workflowConfig", "Lcom/onfido/workflow/WorkflowConfig;", "(Lcom/onfido/workflow/internal/workflow/WorkflowPoller;Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;Lcom/onfido/android/sdk/capture/internal/navigation/Navigator;Lcom/onfido/workflow/internal/ui/processor/BackstackEventsProcessor;Lcom/onfido/workflow/internal/ui/processor/DisplayFaceCaptureFlowProcessor;Lcom/onfido/workflow/internal/ui/processor/DisplayMotionFlowProcessor;Lcom/onfido/workflow/internal/ui/processor/biometric/token/BiometricTokenRetrievalFlowProcessor;Lcom/onfido/workflow/internal/ui/processor/biometric/token/BiometricTokenStorageFlowProcessor;Lcom/onfido/workflow/internal/ui/processor/DisplayDocumentCaptureFlowProcessor;Lcom/onfido/workflow/internal/ui/processor/RetryTaskProcessor;Lcom/onfido/workflow/internal/ui/processor/PoaFlowProcessor;Lcom/onfido/workflow/internal/ui/processor/HostedWebModuleFlowProcessor;Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/FlowTracker;Lcom/onfido/android/sdk/workflow/internal/workflow/WorkflowHttpExceptionMapper;Lcom/onfido/workflow/internal/data/WorkflowTaskDataSource;Lcom/onfido/workflow/WorkflowConfig;)V", "_oneOffUiEvents", "Lio/reactivex/rxjava3/subjects/PublishSubject;", "Lcom/onfido/workflow/internal/ui/model/OneOffUIEvent;", "kotlin.jvm.PlatformType", "Lio/reactivex/rxjava3/annotations/NonNull;", "_toolbarState", "Lio/reactivex/rxjava3/subjects/BehaviorSubject;", "Lcom/onfido/workflow/internal/ui/model/ToolbarState;", "_uiEvents", "Lcom/onfido/workflow/internal/ui/model/UIEvent;", "currentTask", "Lcom/onfido/workflow/internal/ui/model/FlowTask;", "getCurrentTask$onfido_workflow_release$annotations", "()V", "getCurrentTask$onfido_workflow_release", "()Lio/reactivex/rxjava3/subjects/BehaviorSubject;", "currentTask$delegate", "Lkotlin/Lazy;", "customBridgeUrl", "", "disposables", "Lio/reactivex/rxjava3/disposables/CompositeDisposable;", "oneOffUiEvents", "Lio/reactivex/rxjava3/core/Observable;", "getOneOffUiEvents", "()Lio/reactivex/rxjava3/core/Observable;", "toolbarState", "getToolbarState", "bindToScreenAppearance", "Lio/reactivex/rxjava3/core/ObservableTransformer;", ExifInterface.GPS_DIRECTION_TRUE, "", "dispatchUIEvent", "", "uiEvent", "exitFlow", "flowUserExit", "flowUserExitCanceled", "flowUserExitConfirmed", "handleCaptureException", "Lcom/onfido/workflow/OnfidoWorkflow$WorkflowException;", SentryEvent.JsonKeys.EXCEPTION, "Ljava/lang/Exception;", "Lkotlin/Exception;", "handleDocumentTaskProcessorOutcome", "outcome", "Lcom/onfido/workflow/internal/ui/processor/DisplayDocumentCaptureFlowProcessor$ProcessorOutcome;", "handleErrorResult", "errorResult", "Lcom/onfido/workflow/internal/workflow/model/CaptureResult$ErrorResult;", "handleExceptionResult", "handleExceptions", "throwable", "", "handleFaceTaskProcessorOutcome", "Lcom/onfido/workflow/internal/ui/processor/DisplayFaceCaptureFlowProcessor$ProcessorOutcome;", "handleHostedWebModuleOutcome", "Lcom/onfido/workflow/internal/ui/processor/HostedWebModuleFlowProcessor$ProcessorOutcome;", "handlePoaTaskProcessorOutcome", "Lcom/onfido/workflow/internal/ui/processor/PoaFlowProcessor$ProcessorOutcome;", "observeBiometricTokenRetrievalTasks", "observeBiometricTokenStorageTasks", "observeDocumentTasks", "observeErrorCaptureActivityResult", "observeErrorDocumentCaptureResult", "observeErrorLivenessCaptureResult", "observeErrorResults", "observeErrorSelfieCaptureResult", "observeFinishFlowTask", "observeFlowCancellationCaptureActivityResult", "observeFlowExitCaptureActivityResult", "observeMotionErrorResults", "observeMotionTasks", "observePoaTasks", "observeRetryTasks", "observeSelfieAndFaceVideoTasks", "observeTasks", "observeToolbarEvents", "observeUnsupportedTasks", "observeWebModuleErrorResults", "observeWebModuleTask", "onCleared", "onDetach", "resetState", "previousStepCompleted", "", "setCustomBridgeUrl", "url", "showLoadingOnFragmentStart", "startPolling", "getInteractiveTask", "Lcom/onfido/workflow/internal/workflow/WorkflowTask;", "getInteractiveTasks", "toStep", "Companion", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class WorkflowViewModel extends ViewModel {
    private static final String STEP_NON_INTERACTIVE_TASK = "non_interactive_task";
    private static final String STEP_NO_RUNNING_TASK = "no_running_task";
    private static final String STEP_WAITING_SCREEN = "waiting_screen";
    private final PublishSubject<OneOffUIEvent> _oneOffUiEvents;
    private final BehaviorSubject<ToolbarState> _toolbarState;
    private final PublishSubject<UIEvent> _uiEvents;
    private final BackstackEventsProcessor backstackEventsProcessor;
    private final BiometricTokenRetrievalFlowProcessor biometricTokenRetrievalFlowProcessor;
    private final BiometricTokenStorageFlowProcessor biometricTokenStorageFlowProcessor;

    /* renamed from: currentTask$delegate, reason: from kotlin metadata */
    private final Lazy currentTask;
    private String customBridgeUrl;
    private final CompositeDisposable disposables;
    private final DisplayDocumentCaptureFlowProcessor documentTaskProcessor;
    private final DisplayFaceCaptureFlowProcessor faceTaskProcessor;
    private final FlowTracker flowTracker;
    private final HostedWebModuleFlowProcessor hostedWebModuleFlowProcessor;
    private final DisplayMotionFlowProcessor motionTaskProcessor;
    private final Navigator navigator;
    private final Observable<OneOffUIEvent> oneOffUiEvents;
    private final PoaFlowProcessor poaTaskProcessor;
    private final RetryTaskProcessor retryTaskProcessor;
    private final SchedulersProvider schedulersProvider;
    private final Observable<ToolbarState> toolbarState;
    private final WorkflowConfig workflowConfig;
    private final WorkflowHttpExceptionMapper workflowHttpExceptionMapper;
    private final WorkflowPoller workflowPoller;
    private final WorkflowTaskDataSource workflowTaskDataSource;

    public static /* synthetic */ void getCurrentTask$onfido_workflow_release$annotations() {
    }

    public final Observable<OneOffUIEvent> getOneOffUiEvents() {
        return this.oneOffUiEvents;
    }

    public final Observable<ToolbarState> getToolbarState() {
        return this.toolbarState;
    }

    public final void setCustomBridgeUrl(String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        this.customBridgeUrl = url;
    }

    @Inject
    public WorkflowViewModel(WorkflowPoller workflowPoller, SchedulersProvider schedulersProvider, Navigator navigator, BackstackEventsProcessor backstackEventsProcessor, DisplayFaceCaptureFlowProcessor faceTaskProcessor, DisplayMotionFlowProcessor motionTaskProcessor, BiometricTokenRetrievalFlowProcessor biometricTokenRetrievalFlowProcessor, BiometricTokenStorageFlowProcessor biometricTokenStorageFlowProcessor, DisplayDocumentCaptureFlowProcessor documentTaskProcessor, RetryTaskProcessor retryTaskProcessor, PoaFlowProcessor poaTaskProcessor, HostedWebModuleFlowProcessor hostedWebModuleFlowProcessor, FlowTracker flowTracker, WorkflowHttpExceptionMapper workflowHttpExceptionMapper, WorkflowTaskDataSource workflowTaskDataSource, WorkflowConfig workflowConfig) {
        Intrinsics.checkNotNullParameter(workflowPoller, "workflowPoller");
        Intrinsics.checkNotNullParameter(schedulersProvider, "schedulersProvider");
        Intrinsics.checkNotNullParameter(navigator, "navigator");
        Intrinsics.checkNotNullParameter(backstackEventsProcessor, "backstackEventsProcessor");
        Intrinsics.checkNotNullParameter(faceTaskProcessor, "faceTaskProcessor");
        Intrinsics.checkNotNullParameter(motionTaskProcessor, "motionTaskProcessor");
        Intrinsics.checkNotNullParameter(biometricTokenRetrievalFlowProcessor, "biometricTokenRetrievalFlowProcessor");
        Intrinsics.checkNotNullParameter(biometricTokenStorageFlowProcessor, "biometricTokenStorageFlowProcessor");
        Intrinsics.checkNotNullParameter(documentTaskProcessor, "documentTaskProcessor");
        Intrinsics.checkNotNullParameter(retryTaskProcessor, "retryTaskProcessor");
        Intrinsics.checkNotNullParameter(poaTaskProcessor, "poaTaskProcessor");
        Intrinsics.checkNotNullParameter(hostedWebModuleFlowProcessor, "hostedWebModuleFlowProcessor");
        Intrinsics.checkNotNullParameter(flowTracker, "flowTracker");
        Intrinsics.checkNotNullParameter(workflowHttpExceptionMapper, "workflowHttpExceptionMapper");
        Intrinsics.checkNotNullParameter(workflowTaskDataSource, "workflowTaskDataSource");
        Intrinsics.checkNotNullParameter(workflowConfig, "workflowConfig");
        this.workflowPoller = workflowPoller;
        this.schedulersProvider = schedulersProvider;
        this.navigator = navigator;
        this.backstackEventsProcessor = backstackEventsProcessor;
        this.faceTaskProcessor = faceTaskProcessor;
        this.motionTaskProcessor = motionTaskProcessor;
        this.biometricTokenRetrievalFlowProcessor = biometricTokenRetrievalFlowProcessor;
        this.biometricTokenStorageFlowProcessor = biometricTokenStorageFlowProcessor;
        this.documentTaskProcessor = documentTaskProcessor;
        this.retryTaskProcessor = retryTaskProcessor;
        this.poaTaskProcessor = poaTaskProcessor;
        this.hostedWebModuleFlowProcessor = hostedWebModuleFlowProcessor;
        this.flowTracker = flowTracker;
        this.workflowHttpExceptionMapper = workflowHttpExceptionMapper;
        this.workflowTaskDataSource = workflowTaskDataSource;
        this.workflowConfig = workflowConfig;
        this.disposables = new CompositeDisposable();
        this.currentTask = LazyKt.lazy(new Function0<BehaviorSubject<FlowTask>>() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel$currentTask$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BehaviorSubject<FlowTask> invoke() {
                return this.this$0.workflowTaskDataSource.getFlowTask();
            }
        });
        PublishSubject<UIEvent> publishSubjectCreate = PublishSubject.create();
        Intrinsics.checkNotNullExpressionValue(publishSubjectCreate, "create(...)");
        this._uiEvents = publishSubjectCreate;
        PublishSubject<OneOffUIEvent> publishSubjectCreate2 = PublishSubject.create();
        Intrinsics.checkNotNullExpressionValue(publishSubjectCreate2, "create(...)");
        this._oneOffUiEvents = publishSubjectCreate2;
        BehaviorSubject<ToolbarState> behaviorSubjectCreateDefault = BehaviorSubject.createDefault(new ToolbarState(false, false, false, 7, null));
        Intrinsics.checkNotNullExpressionValue(behaviorSubjectCreateDefault, "createDefault(...)");
        this._toolbarState = behaviorSubjectCreateDefault;
        Observable<ToolbarState> observableHide = behaviorSubjectCreateDefault.hide();
        Intrinsics.checkNotNullExpressionValue(observableHide, "hide(...)");
        this.toolbarState = observableHide;
        Observable<OneOffUIEvent> observableHide2 = publishSubjectCreate2.hide();
        Intrinsics.checkNotNullExpressionValue(observableHide2, "hide(...)");
        this.oneOffUiEvents = observableHide2;
        startPolling();
        observeToolbarEvents();
        showLoadingOnFragmentStart();
        observeTasks();
        observeErrorResults();
    }

    public final BehaviorSubject<FlowTask> getCurrentTask$onfido_workflow_release() {
        return (BehaviorSubject) this.currentTask.getValue();
    }

    public final void dispatchUIEvent(UIEvent uiEvent) {
        Intrinsics.checkNotNullParameter(uiEvent, "uiEvent");
        this._uiEvents.onNext(uiEvent);
    }

    private final void startPolling() {
        CompositeDisposable compositeDisposable = this.disposables;
        Disposable disposableSubscribe = getCurrentTask$onfido_workflow_release().distinctUntilChanged().switchMap(new Function() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel.startPolling.1
            @Override // io.reactivex.rxjava3.functions.Function
            public final ObservableSource<? extends FlowTask> apply(FlowTask flowTask) {
                WorkflowPoller workflowPoller = WorkflowViewModel.this.workflowPoller;
                Intrinsics.checkNotNull(flowTask);
                return workflowPoller.startPolling(flowTask);
            }
        }).compose(bindToScreenAppearance()).share().subscribeOn(this.schedulersProvider.getComputation()).observeOn(this.schedulersProvider.getUi()).subscribe(new Consumer() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel.startPolling.2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(FlowTask task) {
                Intrinsics.checkNotNullParameter(task, "task");
                WorkflowViewModel.this.getCurrentTask$onfido_workflow_release().onNext(task);
            }
        }, new Consumer() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel.startPolling.3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                WorkflowViewModel.this.handleExceptions(p0);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        RxExtensionsKt.plusAssign(compositeDisposable, disposableSubscribe);
    }

    private final void observeToolbarEvents() {
        CompositeDisposable compositeDisposable = this.disposables;
        BackstackEventsProcessor backstackEventsProcessor = this.backstackEventsProcessor;
        Observable<UIEvent> observableHide = this._uiEvents.hide();
        Intrinsics.checkNotNullExpressionValue(observableHide, "hide(...)");
        Observable<ToolbarState> observableObserveOn = backstackEventsProcessor.process(observableHide, new Function0<Unit>() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel.observeToolbarEvents.1
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
                WorkflowViewModel.this.exitFlow();
            }
        }).subscribeOn(this.schedulersProvider.getComputation()).observeOn(this.schedulersProvider.getUi());
        final BehaviorSubject<ToolbarState> behaviorSubject = this._toolbarState;
        Disposable disposableSubscribe = observableObserveOn.subscribe(new Consumer() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel.observeToolbarEvents.2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(ToolbarState toolbarState) {
                behaviorSubject.onNext(toolbarState);
            }
        }, new Consumer() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel.observeToolbarEvents.3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable throwable) {
                Intrinsics.checkNotNullParameter(throwable, "throwable");
                Timber.INSTANCE.e(throwable, "Failed to listen for toolbar state", new Object[0]);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        RxExtensionsKt.plusAssign(compositeDisposable, disposableSubscribe);
        CompositeDisposable compositeDisposable2 = this.disposables;
        Disposable disposableSubscribe2 = getInteractiveTasks(getCurrentTask$onfido_workflow_release()).subscribeOn(this.schedulersProvider.getComputation()).observeOn(this.schedulersProvider.getUi()).subscribe(new Consumer() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel.observeToolbarEvents.4
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(WorkflowTask it) {
                Intrinsics.checkNotNullParameter(it, "it");
                ToolbarState toolbarState = (ToolbarState) WorkflowViewModel.this._toolbarState.getValue();
                if (toolbarState == null) {
                    toolbarState = new ToolbarState(false, false, false, 7, null);
                }
                WorkflowViewModel.this._toolbarState.onNext(ToolbarState.copy$default(toolbarState, false, false, it instanceof WorkflowTask.CaptureSdkModuleTask, 3, null));
            }
        }, new Consumer() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel.observeToolbarEvents.5
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable throwable) {
                Intrinsics.checkNotNullParameter(throwable, "throwable");
                Timber.INSTANCE.e(throwable, "Error during handling changing toolbar state", new Object[0]);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe2, "subscribe(...)");
        RxExtensionsKt.plusAssign(compositeDisposable2, disposableSubscribe2);
    }

    private final void showLoadingOnFragmentStart() {
        CompositeDisposable compositeDisposable = this.disposables;
        Observable<U> observableCast = this._uiEvents.filter(new Predicate() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel$showLoadingOnFragmentStart$$inlined$filterIsInstance$1
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it instanceof UIEvent.OnStart;
            }
        }).cast(UIEvent.OnStart.class);
        Intrinsics.checkNotNullExpressionValue(observableCast, "cast(...)");
        Disposable disposableSubscribe = observableCast.take(1L).subscribe(new Consumer() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel.showLoadingOnFragmentStart.1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(UIEvent.OnStart it) {
                Intrinsics.checkNotNullParameter(it, "it");
                NavigatorExtKt.backToWorkflowRoot(WorkflowViewModel.this.navigator);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        RxExtensionsKt.plusAssign(compositeDisposable, disposableSubscribe);
    }

    private final void observeTasks() {
        observeDocumentTasks();
        observeSelfieAndFaceVideoTasks();
        observeMotionTasks();
        observeBiometricTokenRetrievalTasks();
        observeBiometricTokenStorageTasks();
        observePoaTasks();
        observeWebModuleTask();
        observeRetryTasks();
        observeFinishFlowTask();
        observeUnsupportedTasks();
    }

    private final void observeErrorResults() {
        observeFlowCancellationCaptureActivityResult();
        observeFlowExitCaptureActivityResult();
        observeErrorCaptureActivityResult();
        observeErrorDocumentCaptureResult();
        observeErrorSelfieCaptureResult();
        observeErrorLivenessCaptureResult();
        observeMotionErrorResults();
        observeWebModuleErrorResults();
    }

    private final void observeDocumentTasks() {
        CompositeDisposable compositeDisposable = this.disposables;
        Observable<U> observableCast = getCurrentTask$onfido_workflow_release().filter(WorkflowViewModel$getInteractiveTask$$inlined$filterIsInstance$1.INSTANCE).cast(FlowTask.InteractiveTask.class);
        Intrinsics.checkNotNullExpressionValue(observableCast, "cast(...)");
        Observable map = observableCast.map(AnonymousClass1.INSTANCE);
        Intrinsics.checkNotNullExpressionValue(map, "map(...)");
        Observable observableCast2 = map.filter(new Predicate() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel$observeDocumentTasks$$inlined$getInteractiveTask$1
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it instanceof WorkflowTask.DocumentTask;
            }
        }).cast(WorkflowTask.DocumentTask.class);
        Intrinsics.checkNotNullExpressionValue(observableCast2, "cast(...)");
        Observable observableDistinctUntilChanged = observableCast2.distinctUntilChanged();
        Intrinsics.checkNotNullExpressionValue(observableDistinctUntilChanged, "distinctUntilChanged(...)");
        Disposable disposableSubscribe = observableDistinctUntilChanged.switchMap(new Function() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel.observeDocumentTasks.1
            @Override // io.reactivex.rxjava3.functions.Function
            public final ObservableSource<? extends DisplayDocumentCaptureFlowProcessor.ProcessorOutcome> apply(WorkflowTask.DocumentTask task) {
                Intrinsics.checkNotNullParameter(task, "task");
                DisplayDocumentCaptureFlowProcessor displayDocumentCaptureFlowProcessor = WorkflowViewModel.this.documentTaskProcessor;
                Observable<UIEvent> observableHide = WorkflowViewModel.this._uiEvents.hide();
                Intrinsics.checkNotNullExpressionValue(observableHide, "hide(...)");
                return displayDocumentCaptureFlowProcessor.process(task, observableHide);
            }
        }).subscribeOn(this.schedulersProvider.getComputation()).observeOn(this.schedulersProvider.getUi()).subscribe(new Consumer() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel.observeDocumentTasks.2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(DisplayDocumentCaptureFlowProcessor.ProcessorOutcome p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                WorkflowViewModel.this.handleDocumentTaskProcessorOutcome(p0);
            }
        }, new Consumer() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel.observeDocumentTasks.3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable throwable) {
                Intrinsics.checkNotNullParameter(throwable, "throwable");
                Timber.INSTANCE.e(throwable, "Error during handling document processor outcome", new Object[0]);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        RxExtensionsKt.plusAssign(compositeDisposable, disposableSubscribe);
    }

    private final void observeMotionTasks() {
        CompositeDisposable compositeDisposable = this.disposables;
        Observable<U> observableCast = getCurrentTask$onfido_workflow_release().filter(WorkflowViewModel$getInteractiveTask$$inlined$filterIsInstance$1.INSTANCE).cast(FlowTask.InteractiveTask.class);
        Intrinsics.checkNotNullExpressionValue(observableCast, "cast(...)");
        Observable map = observableCast.map(AnonymousClass1.INSTANCE);
        Intrinsics.checkNotNullExpressionValue(map, "map(...)");
        Observable observableCast2 = map.filter(new Predicate() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel$observeMotionTasks$$inlined$getInteractiveTask$1
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it instanceof WorkflowTask.FaceMotionTask;
            }
        }).cast(WorkflowTask.FaceMotionTask.class);
        Intrinsics.checkNotNullExpressionValue(observableCast2, "cast(...)");
        Observable observableDistinctUntilChanged = observableCast2.distinctUntilChanged();
        Intrinsics.checkNotNullExpressionValue(observableDistinctUntilChanged, "distinctUntilChanged(...)");
        Disposable disposableSubscribe = observableDistinctUntilChanged.switchMap(new Function() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel.observeMotionTasks.1
            @Override // io.reactivex.rxjava3.functions.Function
            public final ObservableSource<? extends Unit> apply(WorkflowTask.FaceMotionTask task) {
                Intrinsics.checkNotNullParameter(task, "task");
                DisplayMotionFlowProcessor displayMotionFlowProcessor = WorkflowViewModel.this.motionTaskProcessor;
                Observable<UIEvent> observableHide = WorkflowViewModel.this._uiEvents.hide();
                Intrinsics.checkNotNullExpressionValue(observableHide, "hide(...)");
                return displayMotionFlowProcessor.process(task, observableHide);
            }
        }).subscribeOn(this.schedulersProvider.getComputation()).observeOn(this.schedulersProvider.getUi()).subscribe(new Consumer() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel.observeMotionTasks.2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Unit it) {
                Intrinsics.checkNotNullParameter(it, "it");
                WorkflowViewModel.resetState$default(WorkflowViewModel.this, false, 1, null);
            }
        }, new Consumer() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel.observeMotionTasks.3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable throwable) {
                Intrinsics.checkNotNullParameter(throwable, "throwable");
                Timber.INSTANCE.e(throwable, "Error happened during motion flow", new Object[0]);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        RxExtensionsKt.plusAssign(compositeDisposable, disposableSubscribe);
    }

    private final void observeBiometricTokenRetrievalTasks() {
        CompositeDisposable compositeDisposable = this.disposables;
        Observable<U> observableCast = getCurrentTask$onfido_workflow_release().filter(WorkflowViewModel$getInteractiveTask$$inlined$filterIsInstance$1.INSTANCE).cast(FlowTask.InteractiveTask.class);
        Intrinsics.checkNotNullExpressionValue(observableCast, "cast(...)");
        Observable map = observableCast.map(AnonymousClass1.INSTANCE);
        Intrinsics.checkNotNullExpressionValue(map, "map(...)");
        Observable observableCast2 = map.filter(new Predicate() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel$observeBiometricTokenRetrievalTasks$$inlined$getInteractiveTask$1
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it instanceof WorkflowTask.BiometricTokenRetrievalTask;
            }
        }).cast(WorkflowTask.BiometricTokenRetrievalTask.class);
        Intrinsics.checkNotNullExpressionValue(observableCast2, "cast(...)");
        Observable observableDistinctUntilChanged = observableCast2.distinctUntilChanged();
        Intrinsics.checkNotNullExpressionValue(observableDistinctUntilChanged, "distinctUntilChanged(...)");
        Disposable disposableSubscribe = observableDistinctUntilChanged.switchMap(new Function() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel.observeBiometricTokenRetrievalTasks.1
            @Override // io.reactivex.rxjava3.functions.Function
            public final ObservableSource<? extends Unit> apply(WorkflowTask.BiometricTokenRetrievalTask task) {
                Intrinsics.checkNotNullParameter(task, "task");
                return WorkflowViewModel.this.biometricTokenRetrievalFlowProcessor.process(task);
            }
        }).subscribeOn(this.schedulersProvider.getComputation()).observeOn(this.schedulersProvider.getUi()).subscribe(new Consumer() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel.observeBiometricTokenRetrievalTasks.2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Unit it) {
                Intrinsics.checkNotNullParameter(it, "it");
                WorkflowViewModel.resetState$default(WorkflowViewModel.this, false, 1, null);
            }
        }, new Consumer() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel.observeBiometricTokenRetrievalTasks.3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable throwable) {
                Intrinsics.checkNotNullParameter(throwable, "throwable");
                Timber.INSTANCE.e(throwable, "Error happened during biometric token retrieval flow", new Object[0]);
                WorkflowViewModel.this._oneOffUiEvents.onNext(new OneOffUIEvent.FinishFlow.Error(new OnfidoWorkflow.WorkflowException.WorkflowBiometricTokenRetrievalException("Error happened during biometric token retrieval flow", throwable)));
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        RxExtensionsKt.plusAssign(compositeDisposable, disposableSubscribe);
    }

    private final void observeBiometricTokenStorageTasks() {
        CompositeDisposable compositeDisposable = this.disposables;
        Observable<U> observableCast = getCurrentTask$onfido_workflow_release().filter(WorkflowViewModel$getInteractiveTask$$inlined$filterIsInstance$1.INSTANCE).cast(FlowTask.InteractiveTask.class);
        Intrinsics.checkNotNullExpressionValue(observableCast, "cast(...)");
        Observable map = observableCast.map(AnonymousClass1.INSTANCE);
        Intrinsics.checkNotNullExpressionValue(map, "map(...)");
        Observable observableCast2 = map.filter(new Predicate() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel$observeBiometricTokenStorageTasks$$inlined$getInteractiveTask$1
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it instanceof WorkflowTask.BiometricTokenStorageTask;
            }
        }).cast(WorkflowTask.BiometricTokenStorageTask.class);
        Intrinsics.checkNotNullExpressionValue(observableCast2, "cast(...)");
        Observable observableDistinctUntilChanged = observableCast2.distinctUntilChanged();
        Intrinsics.checkNotNullExpressionValue(observableDistinctUntilChanged, "distinctUntilChanged(...)");
        Disposable disposableSubscribe = observableDistinctUntilChanged.switchMap(new Function() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel.observeBiometricTokenStorageTasks.1
            @Override // io.reactivex.rxjava3.functions.Function
            public final ObservableSource<? extends Unit> apply(WorkflowTask.BiometricTokenStorageTask task) {
                Intrinsics.checkNotNullParameter(task, "task");
                return WorkflowViewModel.this.biometricTokenStorageFlowProcessor.process(task);
            }
        }).subscribeOn(this.schedulersProvider.getComputation()).observeOn(this.schedulersProvider.getUi()).subscribe(new Consumer() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel.observeBiometricTokenStorageTasks.2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Unit it) {
                Intrinsics.checkNotNullParameter(it, "it");
                WorkflowViewModel.resetState$default(WorkflowViewModel.this, false, 1, null);
            }
        }, new Consumer() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel.observeBiometricTokenStorageTasks.3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable throwable) {
                Intrinsics.checkNotNullParameter(throwable, "throwable");
                Timber.INSTANCE.e(throwable, "Error happened while storing a biometric token", new Object[0]);
                WorkflowViewModel.this._oneOffUiEvents.onNext(new OneOffUIEvent.FinishFlow.Error(new OnfidoWorkflow.WorkflowException.WorkflowBiometricTokenStorageException("Error happened while storing a biometric token", throwable)));
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        RxExtensionsKt.plusAssign(compositeDisposable, disposableSubscribe);
    }

    private final void observeSelfieAndFaceVideoTasks() {
        CompositeDisposable compositeDisposable = this.disposables;
        Disposable disposableSubscribe = getInteractiveTasks(getCurrentTask$onfido_workflow_release()).filter(new Predicate() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel.observeSelfieAndFaceVideoTasks.1
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(WorkflowTask task) {
                Intrinsics.checkNotNullParameter(task, "task");
                return (task instanceof WorkflowTask.FaceVideoTask) || (task instanceof WorkflowTask.FacePhotoTask);
            }
        }).distinctUntilChanged().switchMap(new Function() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel.observeSelfieAndFaceVideoTasks.2
            @Override // io.reactivex.rxjava3.functions.Function
            public final ObservableSource<? extends DisplayFaceCaptureFlowProcessor.ProcessorOutcome> apply(WorkflowTask task) {
                Intrinsics.checkNotNullParameter(task, "task");
                return WorkflowViewModel.this.faceTaskProcessor.process(task, WorkflowViewModel.this._uiEvents);
            }
        }).subscribeOn(this.schedulersProvider.getComputation()).observeOn(this.schedulersProvider.getUi()).subscribe(new Consumer() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel.observeSelfieAndFaceVideoTasks.3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(DisplayFaceCaptureFlowProcessor.ProcessorOutcome p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                WorkflowViewModel.this.handleFaceTaskProcessorOutcome(p0);
            }
        }, new Consumer() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel.observeSelfieAndFaceVideoTasks.4
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable throwable) {
                Intrinsics.checkNotNullParameter(throwable, "throwable");
                Timber.INSTANCE.e(throwable, "Error happened during handling face capturing processor outcome", new Object[0]);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        RxExtensionsKt.plusAssign(compositeDisposable, disposableSubscribe);
    }

    private final void observePoaTasks() {
        CompositeDisposable compositeDisposable = this.disposables;
        Observable<U> observableCast = getCurrentTask$onfido_workflow_release().filter(WorkflowViewModel$getInteractiveTask$$inlined$filterIsInstance$1.INSTANCE).cast(FlowTask.InteractiveTask.class);
        Intrinsics.checkNotNullExpressionValue(observableCast, "cast(...)");
        Observable map = observableCast.map(AnonymousClass1.INSTANCE);
        Intrinsics.checkNotNullExpressionValue(map, "map(...)");
        Observable observableCast2 = map.filter(new Predicate() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel$observePoaTasks$$inlined$getInteractiveTask$1
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it instanceof WorkflowTask.ProofOfAddressTask;
            }
        }).cast(WorkflowTask.ProofOfAddressTask.class);
        Intrinsics.checkNotNullExpressionValue(observableCast2, "cast(...)");
        Observable observableDistinctUntilChanged = observableCast2.distinctUntilChanged();
        Intrinsics.checkNotNullExpressionValue(observableDistinctUntilChanged, "distinctUntilChanged(...)");
        Disposable disposableSubscribe = observableDistinctUntilChanged.switchMap(new Function() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel.observePoaTasks.1
            @Override // io.reactivex.rxjava3.functions.Function
            public final ObservableSource<? extends PoaFlowProcessor.ProcessorOutcome> apply(WorkflowTask.ProofOfAddressTask task) {
                Intrinsics.checkNotNullParameter(task, "task");
                PoaFlowProcessor poaFlowProcessor = WorkflowViewModel.this.poaTaskProcessor;
                Observable<UIEvent> observableHide = WorkflowViewModel.this._uiEvents.hide();
                Intrinsics.checkNotNullExpressionValue(observableHide, "hide(...)");
                return poaFlowProcessor.process(task, observableHide);
            }
        }).subscribeOn(this.schedulersProvider.getComputation()).observeOn(this.schedulersProvider.getUi()).subscribe(new Consumer() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel.observePoaTasks.2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(PoaFlowProcessor.ProcessorOutcome p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                WorkflowViewModel.this.handlePoaTaskProcessorOutcome(p0);
            }
        }, new Consumer() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel.observePoaTasks.3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable throwable) {
                Intrinsics.checkNotNullParameter(throwable, "throwable");
                Timber.INSTANCE.e(throwable, "Error during poa flow", new Object[0]);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        RxExtensionsKt.plusAssign(compositeDisposable, disposableSubscribe);
    }

    private final void observeWebModuleTask() {
        CompositeDisposable compositeDisposable = this.disposables;
        Observable<U> observableCast = getCurrentTask$onfido_workflow_release().filter(WorkflowViewModel$getInteractiveTask$$inlined$filterIsInstance$1.INSTANCE).cast(FlowTask.InteractiveTask.class);
        Intrinsics.checkNotNullExpressionValue(observableCast, "cast(...)");
        Observable map = observableCast.map(AnonymousClass1.INSTANCE);
        Intrinsics.checkNotNullExpressionValue(map, "map(...)");
        Observable observableCast2 = map.filter(new Predicate() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel$observeWebModuleTask$$inlined$getInteractiveTask$1
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it instanceof WorkflowTask.CaptureSdkModuleTask;
            }
        }).cast(WorkflowTask.CaptureSdkModuleTask.class);
        Intrinsics.checkNotNullExpressionValue(observableCast2, "cast(...)");
        Observable observableDistinctUntilChanged = observableCast2.distinctUntilChanged();
        Intrinsics.checkNotNullExpressionValue(observableDistinctUntilChanged, "distinctUntilChanged(...)");
        Disposable disposableSubscribe = observableDistinctUntilChanged.switchMap(new Function() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel.observeWebModuleTask.1
            @Override // io.reactivex.rxjava3.functions.Function
            public final ObservableSource<? extends HostedWebModuleFlowProcessor.ProcessorOutcome> apply(WorkflowTask.CaptureSdkModuleTask task) {
                Intrinsics.checkNotNullParameter(task, "task");
                HostedWebModuleFlowProcessor hostedWebModuleFlowProcessor = WorkflowViewModel.this.hostedWebModuleFlowProcessor;
                String workflowRunId = WorkflowViewModel.this.workflowConfig.getWorkflowRunId();
                Observable<UIEvent> observableHide = WorkflowViewModel.this._uiEvents.hide();
                Intrinsics.checkNotNullExpressionValue(observableHide, "hide(...)");
                return hostedWebModuleFlowProcessor.process(workflowRunId, task, observableHide, WorkflowViewModel.this.customBridgeUrl);
            }
        }).subscribeOn(this.schedulersProvider.getComputation()).observeOn(this.schedulersProvider.getUi()).subscribe(new Consumer() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel.observeWebModuleTask.2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(HostedWebModuleFlowProcessor.ProcessorOutcome p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                WorkflowViewModel.this.handleHostedWebModuleOutcome(p0);
            }
        }, new Consumer() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel.observeWebModuleTask.3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable throwable) {
                Intrinsics.checkNotNullParameter(throwable, "throwable");
                PublishSubject publishSubject = WorkflowViewModel.this._oneOffUiEvents;
                String message = throwable.getMessage();
                if (message == null) {
                    message = "";
                }
                publishSubject.onNext(new OneOffUIEvent.FinishFlow.Error(new OnfidoWorkflow.WorkflowException.WorkflowUnknownException(message, null, 2, null)));
                Timber.INSTANCE.e(throwable, "Error during Qes Consent flow", new Object[0]);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        RxExtensionsKt.plusAssign(compositeDisposable, disposableSubscribe);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleHostedWebModuleOutcome(HostedWebModuleFlowProcessor.ProcessorOutcome outcome) {
        if (Intrinsics.areEqual(outcome, HostedWebModuleFlowProcessor.ProcessorOutcome.Success.INSTANCE)) {
            resetState$default(this, false, 1, null);
        } else if (Intrinsics.areEqual(outcome, HostedWebModuleFlowProcessor.ProcessorOutcome.Exit.INSTANCE)) {
            this._oneOffUiEvents.onNext(new OneOffUIEvent.FinishFlow.Exit(OneOffUIEvent.FinishFlow.Exit.Reason.USER_EXIT_FLOW));
        }
    }

    private final void observeRetryTasks() {
        CompositeDisposable compositeDisposable = this.disposables;
        Observable<U> observableCast = getCurrentTask$onfido_workflow_release().filter(WorkflowViewModel$getInteractiveTask$$inlined$filterIsInstance$1.INSTANCE).cast(FlowTask.InteractiveTask.class);
        Intrinsics.checkNotNullExpressionValue(observableCast, "cast(...)");
        Observable map = observableCast.map(AnonymousClass1.INSTANCE);
        Intrinsics.checkNotNullExpressionValue(map, "map(...)");
        Observable observableCast2 = map.filter(new Predicate() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel$observeRetryTasks$$inlined$getInteractiveTask$1
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it instanceof WorkflowTask.RetryTask;
            }
        }).cast(WorkflowTask.RetryTask.class);
        Intrinsics.checkNotNullExpressionValue(observableCast2, "cast(...)");
        Observable observableDistinctUntilChanged = observableCast2.distinctUntilChanged();
        Intrinsics.checkNotNullExpressionValue(observableDistinctUntilChanged, "distinctUntilChanged(...)");
        Disposable disposableSubscribe = observableDistinctUntilChanged.switchMap(new Function() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel.observeRetryTasks.1
            @Override // io.reactivex.rxjava3.functions.Function
            public final ObservableSource<? extends Unit> apply(WorkflowTask.RetryTask task) {
                Intrinsics.checkNotNullParameter(task, "task");
                RetryTaskProcessor retryTaskProcessor = WorkflowViewModel.this.retryTaskProcessor;
                Observable<UIEvent> observableHide = WorkflowViewModel.this._uiEvents.hide();
                Intrinsics.checkNotNullExpressionValue(observableHide, "hide(...)");
                return retryTaskProcessor.process(task, observableHide);
            }
        }).subscribeOn(this.schedulersProvider.getComputation()).observeOn(this.schedulersProvider.getUi()).subscribe(new Consumer() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel.observeRetryTasks.2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Unit it) {
                Intrinsics.checkNotNullParameter(it, "it");
                WorkflowViewModel.this.resetState(false);
            }
        }, new Consumer() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel.observeRetryTasks.3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable throwable) {
                Intrinsics.checkNotNullParameter(throwable, "throwable");
                Timber.INSTANCE.e(throwable, "Error during handling retry task outcome", new Object[0]);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        RxExtensionsKt.plusAssign(compositeDisposable, disposableSubscribe);
    }

    private final void observeFinishFlowTask() {
        CompositeDisposable compositeDisposable = this.disposables;
        Observable<U> observableCast = getCurrentTask$onfido_workflow_release().filter(WorkflowViewModel$getInteractiveTask$$inlined$filterIsInstance$1.INSTANCE).cast(FlowTask.InteractiveTask.class);
        Intrinsics.checkNotNullExpressionValue(observableCast, "cast(...)");
        Observable map = observableCast.map(AnonymousClass1.INSTANCE);
        Intrinsics.checkNotNullExpressionValue(map, "map(...)");
        Observable observableCast2 = map.filter(new Predicate() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel$observeFinishFlowTask$$inlined$getInteractiveTask$1
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it instanceof WorkflowTask.FinishFlowTask;
            }
        }).cast(WorkflowTask.FinishFlowTask.class);
        Intrinsics.checkNotNullExpressionValue(observableCast2, "cast(...)");
        Observable observableDistinctUntilChanged = observableCast2.distinctUntilChanged();
        Intrinsics.checkNotNullExpressionValue(observableDistinctUntilChanged, "distinctUntilChanged(...)");
        Disposable disposableSubscribe = observableDistinctUntilChanged.take(1L).subscribe(new Consumer() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel.observeFinishFlowTask.1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(WorkflowTask.FinishFlowTask result) {
                Intrinsics.checkNotNullParameter(result, "result");
                if (result.getError() == null) {
                    WorkflowViewModel.this._oneOffUiEvents.onNext(new OneOffUIEvent.FinishFlow.Success(-1));
                    WorkflowViewModel.this.flowTracker.trackFlowCompletion();
                } else {
                    WorkflowViewModel.this._oneOffUiEvents.onNext(new OneOffUIEvent.FinishFlow.Error(result.getError()));
                }
            }
        }, new Consumer() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel.observeFinishFlowTask.2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable throwable) {
                Intrinsics.checkNotNullParameter(throwable, "throwable");
                Timber.INSTANCE.e(throwable, "Error in finishing flow", new Object[0]);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        RxExtensionsKt.plusAssign(compositeDisposable, disposableSubscribe);
    }

    private final void observeUnsupportedTasks() {
        CompositeDisposable compositeDisposable = this.disposables;
        Observable<U> observableCast = getCurrentTask$onfido_workflow_release().filter(WorkflowViewModel$getInteractiveTask$$inlined$filterIsInstance$1.INSTANCE).cast(FlowTask.InteractiveTask.class);
        Intrinsics.checkNotNullExpressionValue(observableCast, "cast(...)");
        Observable map = observableCast.map(AnonymousClass1.INSTANCE);
        Intrinsics.checkNotNullExpressionValue(map, "map(...)");
        Observable observableCast2 = map.filter(new Predicate() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel$observeUnsupportedTasks$$inlined$getInteractiveTask$1
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it instanceof WorkflowTask.UnsupportedTask;
            }
        }).cast(WorkflowTask.UnsupportedTask.class);
        Intrinsics.checkNotNullExpressionValue(observableCast2, "cast(...)");
        Observable observableDistinctUntilChanged = observableCast2.distinctUntilChanged();
        Intrinsics.checkNotNullExpressionValue(observableDistinctUntilChanged, "distinctUntilChanged(...)");
        Disposable disposableSubscribe = observableDistinctUntilChanged.subscribe(new Consumer() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel.observeUnsupportedTasks.1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(WorkflowTask.UnsupportedTask task) {
                Intrinsics.checkNotNullParameter(task, "task");
                WorkflowViewModel.this._oneOffUiEvents.onNext(new OneOffUIEvent.FinishFlow.Error(new OnfidoWorkflow.WorkflowException.WorkflowUnsupportedTaskException("Received unsupported task: " + task.getTaskName(), task.getReason())));
            }
        }, new Consumer() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel.observeUnsupportedTasks.2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable throwable) {
                Intrinsics.checkNotNullParameter(throwable, "throwable");
                Timber.INSTANCE.e(throwable, "Failed to end the flow", new Object[0]);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        RxExtensionsKt.plusAssign(compositeDisposable, disposableSubscribe);
    }

    private final void observeFlowCancellationCaptureActivityResult() {
        CompositeDisposable compositeDisposable = this.disposables;
        Observable<U> observableCast = this._uiEvents.filter(new Predicate() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel$observeFlowCancellationCaptureActivityResult$$inlined$filterIsInstance$1
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it instanceof UIEvent.OnActivityResult;
            }
        }).cast(UIEvent.OnActivityResult.class);
        Intrinsics.checkNotNullExpressionValue(observableCast, "cast(...)");
        Observable map = observableCast.map(new WorkflowViewModel$sam$io_reactivex_rxjava3_functions_Function$0(new PropertyReference1Impl() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel.observeFlowCancellationCaptureActivityResult.1
            @Override // kotlin.jvm.internal.PropertyReference1Impl, kotlin.reflect.KProperty1
            public Object get(Object obj) {
                return ((UIEvent.OnActivityResult) obj).getCaptureResult();
            }
        }));
        Intrinsics.checkNotNullExpressionValue(map, "map(...)");
        Observable observableCast2 = map.filter(new Predicate() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel$observeFlowCancellationCaptureActivityResult$$inlined$filterIsInstance$2
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it instanceof CaptureResult.Cancelled;
            }
        }).cast(CaptureResult.Cancelled.class);
        Intrinsics.checkNotNullExpressionValue(observableCast2, "cast(...)");
        Disposable disposableSubscribe = observableCast2.subscribe(new Consumer() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel.observeFlowCancellationCaptureActivityResult.2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(CaptureResult.Cancelled it) {
                Intrinsics.checkNotNullParameter(it, "it");
                WorkflowViewModel.this.flowTracker.trackFlowCancellation();
                WorkflowViewModel.this._oneOffUiEvents.onNext(new OneOffUIEvent.FinishFlow.Error(new OnfidoWorkflow.WorkflowException.WorkflowCaptureCancelledException("Workflow has been exited by the user")));
            }
        }, new Consumer() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel.observeFlowCancellationCaptureActivityResult.3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable throwable) {
                Intrinsics.checkNotNullParameter(throwable, "throwable");
                Timber.INSTANCE.e(throwable, "Error in listening for cancelled result", new Object[0]);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        RxExtensionsKt.plusAssign(compositeDisposable, disposableSubscribe);
    }

    private final void observeFlowExitCaptureActivityResult() {
        CompositeDisposable compositeDisposable = this.disposables;
        Observable<U> observableCast = this._uiEvents.filter(new Predicate() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel$observeFlowExitCaptureActivityResult$$inlined$filterIsInstance$1
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it instanceof UIEvent.OnActivityResult;
            }
        }).cast(UIEvent.OnActivityResult.class);
        Intrinsics.checkNotNullExpressionValue(observableCast, "cast(...)");
        Observable map = observableCast.map(new WorkflowViewModel$sam$io_reactivex_rxjava3_functions_Function$0(new PropertyReference1Impl() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel.observeFlowExitCaptureActivityResult.1
            @Override // kotlin.jvm.internal.PropertyReference1Impl, kotlin.reflect.KProperty1
            public Object get(Object obj) {
                return ((UIEvent.OnActivityResult) obj).getCaptureResult();
            }
        }));
        Intrinsics.checkNotNullExpressionValue(map, "map(...)");
        Observable observableCast2 = map.filter(new Predicate() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel$observeFlowExitCaptureActivityResult$$inlined$filterIsInstance$2
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it instanceof CaptureResult.Exit;
            }
        }).cast(CaptureResult.Exit.class);
        Intrinsics.checkNotNullExpressionValue(observableCast2, "cast(...)");
        Disposable disposableSubscribe = observableCast2.subscribe(new Consumer() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel.observeFlowExitCaptureActivityResult.2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(CaptureResult.Exit it) {
                Intrinsics.checkNotNullParameter(it, "it");
                WorkflowViewModel.this.exitFlow();
            }
        }, new Consumer() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel.observeFlowExitCaptureActivityResult.3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable throwable) {
                Intrinsics.checkNotNullParameter(throwable, "throwable");
                Timber.INSTANCE.e(throwable, "Error in listening for cancelled result", new Object[0]);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        RxExtensionsKt.plusAssign(compositeDisposable, disposableSubscribe);
    }

    private final void observeErrorCaptureActivityResult() {
        CompositeDisposable compositeDisposable = this.disposables;
        Observable<U> observableCast = this._uiEvents.filter(new Predicate() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel$observeErrorCaptureActivityResult$$inlined$filterIsInstance$1
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it instanceof UIEvent.OnActivityResult;
            }
        }).cast(UIEvent.OnActivityResult.class);
        Intrinsics.checkNotNullExpressionValue(observableCast, "cast(...)");
        Observable map = observableCast.map(new WorkflowViewModel$sam$io_reactivex_rxjava3_functions_Function$0(new PropertyReference1Impl() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel.observeErrorCaptureActivityResult.1
            @Override // kotlin.jvm.internal.PropertyReference1Impl, kotlin.reflect.KProperty1
            public Object get(Object obj) {
                return ((UIEvent.OnActivityResult) obj).getCaptureResult();
            }
        }));
        Intrinsics.checkNotNullExpressionValue(map, "map(...)");
        Observable observableCast2 = map.filter(new Predicate() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel$observeErrorCaptureActivityResult$$inlined$filterIsInstance$2
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it instanceof CaptureResult.ErrorResult;
            }
        }).cast(CaptureResult.ErrorResult.class);
        Intrinsics.checkNotNullExpressionValue(observableCast2, "cast(...)");
        Disposable disposableSubscribe = observableCast2.subscribe(new Consumer() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel.observeErrorCaptureActivityResult.2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(CaptureResult.ErrorResult p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                WorkflowViewModel.this.handleErrorResult(p0);
            }
        }, new Consumer() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel.observeErrorCaptureActivityResult.3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable throwable) {
                Intrinsics.checkNotNullParameter(throwable, "throwable");
                Timber.INSTANCE.e(throwable, "Error in listening for ErrorResult", new Object[0]);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        RxExtensionsKt.plusAssign(compositeDisposable, disposableSubscribe);
    }

    private final void observeErrorDocumentCaptureResult() {
        CompositeDisposable compositeDisposable = this.disposables;
        Observable<U> observableCast = this._uiEvents.filter(new Predicate() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel$observeErrorDocumentCaptureResult$$inlined$filterIsInstance$1
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it instanceof UIEvent.OnFragmentResult;
            }
        }).cast(UIEvent.OnFragmentResult.class);
        Intrinsics.checkNotNullExpressionValue(observableCast, "cast(...)");
        Observable observableCast2 = observableCast.filter(new Predicate() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel$observeErrorDocumentCaptureResult$$inlined$filterIsInstance$2
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it instanceof UIEvent.OnFragmentResult.OnDocumentCaptureFragmentResult;
            }
        }).cast(UIEvent.OnFragmentResult.OnDocumentCaptureFragmentResult.class);
        Intrinsics.checkNotNullExpressionValue(observableCast2, "cast(...)");
        Observable map = observableCast2.map(new Function() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel.observeErrorDocumentCaptureResult.1
            @Override // io.reactivex.rxjava3.functions.Function
            public final DocumentCaptureScreen.DocumentCaptureResult apply(UIEvent.OnFragmentResult.OnDocumentCaptureFragmentResult it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.getResult();
            }
        });
        Intrinsics.checkNotNullExpressionValue(map, "map(...)");
        Observable observableCast3 = map.filter(new Predicate() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel$observeErrorDocumentCaptureResult$$inlined$filterIsInstance$3
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it instanceof DocumentCaptureScreen.DocumentCaptureResult.Error;
            }
        }).cast(DocumentCaptureScreen.DocumentCaptureResult.Error.class);
        Intrinsics.checkNotNullExpressionValue(observableCast3, "cast(...)");
        Disposable disposableSubscribe = observableCast3.map(new Function() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel.observeErrorDocumentCaptureResult.2
            @Override // io.reactivex.rxjava3.functions.Function
            public final OnfidoWorkflow.WorkflowException apply(DocumentCaptureScreen.DocumentCaptureResult.Error it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return WorkflowViewModel.this.handleCaptureException(it.getException());
            }
        }).subscribe(new Consumer() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel.observeErrorDocumentCaptureResult.3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(OnfidoWorkflow.WorkflowException p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                WorkflowViewModel.this.handleExceptionResult(p0);
            }
        }, new Consumer() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel.observeErrorDocumentCaptureResult.4
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable throwable) {
                Intrinsics.checkNotNullParameter(throwable, "throwable");
                Timber.INSTANCE.e(throwable, "Error in listening for ErrorResult", new Object[0]);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        RxExtensionsKt.plusAssign(compositeDisposable, disposableSubscribe);
    }

    private final void observeErrorSelfieCaptureResult() {
        CompositeDisposable compositeDisposable = this.disposables;
        Observable<U> observableCast = this._uiEvents.filter(new Predicate() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel$observeErrorSelfieCaptureResult$$inlined$filterIsInstance$1
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it instanceof UIEvent.OnFragmentResult;
            }
        }).cast(UIEvent.OnFragmentResult.class);
        Intrinsics.checkNotNullExpressionValue(observableCast, "cast(...)");
        Observable observableCast2 = observableCast.filter(new Predicate() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel$observeErrorSelfieCaptureResult$$inlined$filterIsInstance$2
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it instanceof UIEvent.OnFragmentResult.OnFaceSelfieCaptureFragmentResult;
            }
        }).cast(UIEvent.OnFragmentResult.OnFaceSelfieCaptureFragmentResult.class);
        Intrinsics.checkNotNullExpressionValue(observableCast2, "cast(...)");
        Observable map = observableCast2.map(new Function() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel.observeErrorSelfieCaptureResult.1
            @Override // io.reactivex.rxjava3.functions.Function
            public final SelfieCaptureScreen.SelfieCaptureResult apply(UIEvent.OnFragmentResult.OnFaceSelfieCaptureFragmentResult it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.getResult();
            }
        });
        Intrinsics.checkNotNullExpressionValue(map, "map(...)");
        Observable observableCast3 = map.filter(new Predicate() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel$observeErrorSelfieCaptureResult$$inlined$filterIsInstance$3
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it instanceof SelfieCaptureScreen.SelfieCaptureResult.Error;
            }
        }).cast(SelfieCaptureScreen.SelfieCaptureResult.Error.class);
        Intrinsics.checkNotNullExpressionValue(observableCast3, "cast(...)");
        Disposable disposableSubscribe = observableCast3.map(new Function() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel.observeErrorSelfieCaptureResult.2
            @Override // io.reactivex.rxjava3.functions.Function
            public final OnfidoWorkflow.WorkflowException apply(SelfieCaptureScreen.SelfieCaptureResult.Error it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return WorkflowViewModel.this.handleCaptureException(it.getException());
            }
        }).subscribe(new Consumer() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel.observeErrorSelfieCaptureResult.3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(OnfidoWorkflow.WorkflowException p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                WorkflowViewModel.this.handleExceptionResult(p0);
            }
        }, new Consumer() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel.observeErrorSelfieCaptureResult.4
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable throwable) {
                Intrinsics.checkNotNullParameter(throwable, "throwable");
                Timber.INSTANCE.e(throwable, "Error in listening for ErrorResult", new Object[0]);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        RxExtensionsKt.plusAssign(compositeDisposable, disposableSubscribe);
    }

    private final void observeErrorLivenessCaptureResult() {
        CompositeDisposable compositeDisposable = this.disposables;
        Observable<U> observableCast = this._uiEvents.filter(new Predicate() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel$observeErrorLivenessCaptureResult$$inlined$filterIsInstance$1
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it instanceof UIEvent.OnFragmentResult;
            }
        }).cast(UIEvent.OnFragmentResult.class);
        Intrinsics.checkNotNullExpressionValue(observableCast, "cast(...)");
        Observable observableCast2 = observableCast.filter(new Predicate() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel$observeErrorLivenessCaptureResult$$inlined$filterIsInstance$2
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it instanceof UIEvent.OnFragmentResult.OnFaceLivenessCaptureFragmentResult;
            }
        }).cast(UIEvent.OnFragmentResult.OnFaceLivenessCaptureFragmentResult.class);
        Intrinsics.checkNotNullExpressionValue(observableCast2, "cast(...)");
        Observable map = observableCast2.map(new Function() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel.observeErrorLivenessCaptureResult.1
            @Override // io.reactivex.rxjava3.functions.Function
            public final LivenessCaptureScreen.LivenessCaptureResult apply(UIEvent.OnFragmentResult.OnFaceLivenessCaptureFragmentResult it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.getResult();
            }
        });
        Intrinsics.checkNotNullExpressionValue(map, "map(...)");
        Observable observableCast3 = map.filter(new Predicate() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel$observeErrorLivenessCaptureResult$$inlined$filterIsInstance$3
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it instanceof LivenessCaptureScreen.LivenessCaptureResult.Error;
            }
        }).cast(LivenessCaptureScreen.LivenessCaptureResult.Error.class);
        Intrinsics.checkNotNullExpressionValue(observableCast3, "cast(...)");
        Disposable disposableSubscribe = observableCast3.map(new Function() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel.observeErrorLivenessCaptureResult.2
            @Override // io.reactivex.rxjava3.functions.Function
            public final OnfidoWorkflow.WorkflowException apply(LivenessCaptureScreen.LivenessCaptureResult.Error it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return WorkflowViewModel.this.handleCaptureException(it.getException());
            }
        }).subscribe(new Consumer() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel.observeErrorLivenessCaptureResult.3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(OnfidoWorkflow.WorkflowException p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                WorkflowViewModel.this.handleExceptionResult(p0);
            }
        }, new Consumer() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel.observeErrorLivenessCaptureResult.4
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable throwable) {
                Intrinsics.checkNotNullParameter(throwable, "throwable");
                Timber.INSTANCE.e(throwable, "Error in listening for ErrorResult", new Object[0]);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        RxExtensionsKt.plusAssign(compositeDisposable, disposableSubscribe);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void exitFlow() {
        this.flowTracker.trackFlowCancellation();
        this._oneOffUiEvents.onNext(new OneOffUIEvent.FinishFlow.Exit(OneOffUIEvent.FinishFlow.Exit.Reason.USER_EXIT_FLOW));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final OnfidoWorkflow.WorkflowException handleCaptureException(Exception exception) {
        if (Intrinsics.areEqual(exception, TokenExpiredException.INSTANCE)) {
            return new OnfidoWorkflow.WorkflowException.WorkflowTokenExpiredException("token expired");
        }
        if (exception instanceof InvalidCertificateException) {
            return new OnfidoWorkflow.WorkflowException.WorkflowInvalidSSLCertificateException(((InvalidCertificateException) exception).getMessage());
        }
        if (exception instanceof UnknownCameraException) {
            Throwable cause = exception.getCause();
            String message = cause != null ? cause.getMessage() : null;
            return new OnfidoWorkflow.WorkflowException.WorkflowUnknownCameraException(message != null ? message : "");
        }
        String localizedMessage = exception.getLocalizedMessage();
        return new OnfidoWorkflow.WorkflowException.WorkflowUnknownException(localizedMessage != null ? localizedMessage : "", exception);
    }

    private final void observeMotionErrorResults() {
        CompositeDisposable compositeDisposable = this.disposables;
        Observable<U> observableCast = this._uiEvents.filter(new Predicate() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel$observeMotionErrorResults$$inlined$filterIsInstance$1
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it instanceof UIEvent.OnFragmentResult.OnMotionResult;
            }
        }).cast(UIEvent.OnFragmentResult.OnMotionResult.class);
        Intrinsics.checkNotNullExpressionValue(observableCast, "cast(...)");
        Observable map = observableCast.map(new WorkflowViewModel$sam$io_reactivex_rxjava3_functions_Function$0(new PropertyReference1Impl() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel.observeMotionErrorResults.1
            @Override // kotlin.jvm.internal.PropertyReference1Impl, kotlin.reflect.KProperty1
            public Object get(Object obj) {
                return ((UIEvent.OnFragmentResult.OnMotionResult) obj).getAvcHostResult();
            }
        }));
        Intrinsics.checkNotNullExpressionValue(map, "map(...)");
        Observable observableCast2 = map.filter(new Predicate() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel$observeMotionErrorResults$$inlined$filterIsInstance$2
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it instanceof MotionHostFragment.AvcHostResult.Error;
            }
        }).cast(MotionHostFragment.AvcHostResult.Error.class);
        Intrinsics.checkNotNullExpressionValue(observableCast2, "cast(...)");
        Disposable disposableSubscribe = observableCast2.subscribe(new Consumer() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel.observeMotionErrorResults.2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(MotionHostFragment.AvcHostResult.Error error) {
                Intrinsics.checkNotNullParameter(error, "error");
                WorkflowViewModel.this._oneOffUiEvents.onNext(new OneOffUIEvent.FinishFlow.Error(new OnfidoWorkflow.WorkflowException.WorkflowUnknownException(error.getMessage(), null, 2, null)));
            }
        }, new Consumer() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel.observeMotionErrorResults.3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable throwable) {
                Intrinsics.checkNotNullParameter(throwable, "throwable");
                Timber.INSTANCE.e(throwable, "Failed to listen to Motion errors", new Object[0]);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        RxExtensionsKt.plusAssign(compositeDisposable, disposableSubscribe);
    }

    private final void observeWebModuleErrorResults() {
        CompositeDisposable compositeDisposable = this.disposables;
        Observable<U> observableCast = this._uiEvents.filter(new Predicate() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel$observeWebModuleErrorResults$$inlined$filterIsInstance$1
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it instanceof UIEvent.OnFragmentResult.OnHostedWebModuleResult;
            }
        }).cast(UIEvent.OnFragmentResult.OnHostedWebModuleResult.class);
        Intrinsics.checkNotNullExpressionValue(observableCast, "cast(...)");
        Observable map = observableCast.map(new WorkflowViewModel$sam$io_reactivex_rxjava3_functions_Function$0(new PropertyReference1Impl() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel.observeWebModuleErrorResults.1
            @Override // kotlin.jvm.internal.PropertyReference1Impl, kotlin.reflect.KProperty1
            public Object get(Object obj) {
                return ((UIEvent.OnFragmentResult.OnHostedWebModuleResult) obj).getHostedWebModuleResult();
            }
        }));
        Intrinsics.checkNotNullExpressionValue(map, "map(...)");
        Observable observableCast2 = map.filter(new Predicate() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel$observeWebModuleErrorResults$$inlined$filterIsInstance$2
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it instanceof HostedWebModuleFailed;
            }
        }).cast(HostedWebModuleFailed.class);
        Intrinsics.checkNotNullExpressionValue(observableCast2, "cast(...)");
        Disposable disposableSubscribe = observableCast2.subscribe(new Consumer() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel.observeWebModuleErrorResults.2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(HostedWebModuleFailed it) {
                Intrinsics.checkNotNullParameter(it, "it");
                WorkflowViewModel.this._oneOffUiEvents.onNext(new OneOffUIEvent.FinishFlow.Error(new OnfidoWorkflow.WorkflowException.WorkflowUnknownException(it.getBody(), null, 2, null)));
            }
        }, new Consumer() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel.observeWebModuleErrorResults.3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable throwable) {
                Intrinsics.checkNotNullParameter(throwable, "throwable");
                Timber.INSTANCE.e(throwable, "Failed to listen to Webview errors", new Object[0]);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        RxExtensionsKt.plusAssign(compositeDisposable, disposableSubscribe);
    }

    @Override // androidx.lifecycle.ViewModel
    protected void onCleared() {
        this.disposables.dispose();
        super.onCleared();
    }

    /* compiled from: WorkflowViewModel.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Lcom/onfido/workflow/internal/workflow/WorkflowTask;", ExifInterface.GPS_DIRECTION_TRUE, "it", "Lcom/onfido/workflow/internal/ui/model/FlowTask$InteractiveTask;", "apply"}, k = 3, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.onfido.workflow.internal.ui.WorkflowViewModel$getInteractiveTask$1, reason: invalid class name */
    public static final class AnonymousClass1<T, R> implements Function {
        public static final AnonymousClass1<T, R> INSTANCE = new AnonymousClass1<>();

        @Override // io.reactivex.rxjava3.functions.Function
        public final WorkflowTask apply(FlowTask.InteractiveTask it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return it.getWorkflowTask();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleDocumentTaskProcessorOutcome(DisplayDocumentCaptureFlowProcessor.ProcessorOutcome outcome) {
        if (Intrinsics.areEqual(outcome, DisplayDocumentCaptureFlowProcessor.ProcessorOutcome.DocumentCaptureFlowFinished.INSTANCE)) {
            resetState$default(this, false, 1, null);
            return;
        }
        if (outcome instanceof DisplayDocumentCaptureFlowProcessor.ProcessorOutcome.OpenDocumentCaptureActivity) {
            DisplayDocumentCaptureFlowProcessor.ProcessorOutcome.OpenDocumentCaptureActivity openDocumentCaptureActivity = (DisplayDocumentCaptureFlowProcessor.ProcessorOutcome.OpenDocumentCaptureActivity) outcome;
            this._oneOffUiEvents.onNext(new OneOffUIEvent.NavigateToDocumentFlow(openDocumentCaptureActivity.getDocumentType(), openDocumentCaptureActivity.getCountrySelection(), openDocumentCaptureActivity.getDocSide(), openDocumentCaptureActivity.getDocumentFormat(), openDocumentCaptureActivity.getNfcArguments()));
            return;
        }
        if (!(outcome instanceof DisplayDocumentCaptureFlowProcessor.ProcessorOutcome.ExitNfcFlow)) {
            throw new NoWhenBranchMatchedException();
        }
        this._oneOffUiEvents.onNext(new OneOffUIEvent.FinishFlow.Exit(OneOffUIEvent.FinishFlow.Exit.Reason.EXIT_NFC_REQUIRED_FLOW));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handlePoaTaskProcessorOutcome(PoaFlowProcessor.ProcessorOutcome outcome) {
        if (Intrinsics.areEqual(outcome, PoaFlowProcessor.ProcessorOutcome.PoaFlowFinished.INSTANCE)) {
            resetState$default(this, false, 1, null);
        } else if (Intrinsics.areEqual(outcome, PoaFlowProcessor.ProcessorOutcome.Exit.INSTANCE)) {
            this._oneOffUiEvents.onNext(new OneOffUIEvent.FinishFlow.Exit(OneOffUIEvent.FinishFlow.Exit.Reason.USER_EXIT_FLOW));
        } else if (Intrinsics.areEqual(outcome, PoaFlowProcessor.ProcessorOutcome.Cancelled.INSTANCE)) {
            this._oneOffUiEvents.onNext(OneOffUIEvent.DoNothing.INSTANCE);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleFaceTaskProcessorOutcome(DisplayFaceCaptureFlowProcessor.ProcessorOutcome outcome) {
        if (Intrinsics.areEqual(outcome, DisplayFaceCaptureFlowProcessor.ProcessorOutcome.FaceCapturingFlowFinished.INSTANCE)) {
            resetState$default(this, false, 1, null);
        } else if (Intrinsics.areEqual(outcome, DisplayFaceCaptureFlowProcessor.ProcessorOutcome.OpenFaceLivenessCaptureActivity.INSTANCE)) {
            this._oneOffUiEvents.onNext(OneOffUIEvent.NavigateToFaceVideoFlow.INSTANCE);
        } else {
            if (!Intrinsics.areEqual(outcome, DisplayFaceCaptureFlowProcessor.ProcessorOutcome.OpenFaceSelfieCaptureActivity.INSTANCE)) {
                throw new NoWhenBranchMatchedException();
            }
            this._oneOffUiEvents.onNext(OneOffUIEvent.NavigateToFaceUploadFlow.INSTANCE);
        }
    }

    private final <T> ObservableTransformer<T, T> bindToScreenAppearance() {
        return new ObservableTransformer() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.core.ObservableTransformer
            public final ObservableSource apply(Observable observable) {
                return WorkflowViewModel.bindToScreenAppearance$lambda$0(this.f$0, observable);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ObservableSource bindToScreenAppearance$lambda$0(final WorkflowViewModel this$0, final Observable observable) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(observable, "observable");
        Observable<U> observableCast = this$0._uiEvents.filter(new Predicate() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel$bindToScreenAppearance$lambda$0$$inlined$filterIsInstance$1
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it instanceof UIEvent.OnStart;
            }
        }).cast(UIEvent.OnStart.class);
        Intrinsics.checkNotNullExpressionValue(observableCast, "cast(...)");
        return observableCast.switchMap(new Function() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel$bindToScreenAppearance$1$1
            @Override // io.reactivex.rxjava3.functions.Function
            public final ObservableSource<? extends T> apply(UIEvent.OnStart it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Observable<T> observable2 = observable;
                ObservableSource observableSourceCast = this$0._uiEvents.filter(new Predicate() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel$bindToScreenAppearance$1$1$apply$$inlined$filterIsInstance$1
                    @Override // io.reactivex.rxjava3.functions.Predicate
                    public final boolean test(Object it2) {
                        Intrinsics.checkNotNullParameter(it2, "it");
                        return it2 instanceof UIEvent.OnStop;
                    }
                }).cast(UIEvent.OnStop.class);
                Intrinsics.checkNotNullExpressionValue(observableSourceCast, "cast(...)");
                return observable2.takeUntil(observableSourceCast);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleErrorResult(CaptureResult.ErrorResult errorResult) {
        OnfidoWorkflow.WorkflowException.WorkflowUnknownCameraException workflowUnknownCameraException;
        if (errorResult instanceof CaptureResult.ErrorResult.InvalidSSLCertificate) {
            workflowUnknownCameraException = new OnfidoWorkflow.WorkflowException.WorkflowInvalidSSLCertificateException(((CaptureResult.ErrorResult.InvalidSSLCertificate) errorResult).getMessage());
        } else if (Intrinsics.areEqual(errorResult, CaptureResult.ErrorResult.OnTokenExpired.INSTANCE)) {
            workflowUnknownCameraException = new OnfidoWorkflow.WorkflowException.WorkflowTokenExpiredException("token expired");
        } else {
            if (!(errorResult instanceof CaptureResult.ErrorResult.UnknownCamera)) {
                throw new NoWhenBranchMatchedException();
            }
            String message = ((CaptureResult.ErrorResult.UnknownCamera) errorResult).getCause().getMessage();
            if (message == null) {
                message = "";
            }
            workflowUnknownCameraException = new OnfidoWorkflow.WorkflowException.WorkflowUnknownCameraException(message);
        }
        handleExceptionResult(workflowUnknownCameraException);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleExceptionResult(OnfidoWorkflow.WorkflowException exception) {
        this._oneOffUiEvents.onNext(new OneOffUIEvent.FinishFlow.Error(exception));
    }

    static /* synthetic */ void resetState$default(WorkflowViewModel workflowViewModel, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        workflowViewModel.resetState(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void resetState(boolean previousStepCompleted) {
        if (previousStepCompleted) {
            getCurrentTask$onfido_workflow_release().onNext(FlowTask.AwaitingNextTask.INSTANCE);
        } else {
            getCurrentTask$onfido_workflow_release().onNext(FlowTask.NoRunningTask.INSTANCE);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleExceptions(Throwable throwable) {
        Timber.INSTANCE.e(throwable, "Failed to listen for orchestrationState", new Object[0]);
        this._oneOffUiEvents.onNext(new OneOffUIEvent.FinishFlow.Error(this.workflowHttpExceptionMapper.map(throwable)));
    }

    public final void onDetach() {
        WorkflowComponent.INSTANCE.destroy();
    }

    public final void flowUserExit() {
        this.flowTracker.trackFlowUserExitButtonClicked(toStep(getCurrentTask$onfido_workflow_release().getValue()));
    }

    public final void flowUserExitConfirmed() {
        this.flowTracker.trackFlowUserExitConfirmed(toStep(getCurrentTask$onfido_workflow_release().getValue()));
    }

    public final void flowUserExitCanceled() {
        this.flowTracker.trackFlowUserExitCanceled(toStep(getCurrentTask$onfido_workflow_release().getValue()));
    }

    private final String toStep(FlowTask flowTask) {
        return Intrinsics.areEqual(flowTask, FlowTask.AwaitingNextTask.INSTANCE) ? STEP_WAITING_SCREEN : flowTask instanceof FlowTask.InteractiveTask ? ((FlowTask.InteractiveTask) flowTask).getWorkflowTask().getTaskDefId() : Intrinsics.areEqual(flowTask, FlowTask.NoRunningTask.INSTANCE) ? STEP_NO_RUNNING_TASK : Intrinsics.areEqual(flowTask, FlowTask.NonInteractiveTask.INSTANCE) ? STEP_NON_INTERACTIVE_TASK : "";
    }

    private final Observable<WorkflowTask> getInteractiveTasks(Observable<FlowTask> observable) {
        Observable<U> observableCast = observable.filter(new Predicate() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel$getInteractiveTasks$$inlined$filterIsInstance$1
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it instanceof FlowTask.InteractiveTask;
            }
        }).cast(FlowTask.InteractiveTask.class);
        Intrinsics.checkNotNullExpressionValue(observableCast, "cast(...)");
        Observable<WorkflowTask> map = observableCast.map(new Function() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel.getInteractiveTasks.1
            @Override // io.reactivex.rxjava3.functions.Function
            public final WorkflowTask apply(FlowTask.InteractiveTask it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.getWorkflowTask();
            }
        });
        Intrinsics.checkNotNullExpressionValue(map, "map(...)");
        return map;
    }

    private final /* synthetic */ <T extends WorkflowTask> Observable<T> getInteractiveTask(Observable<FlowTask> observable) {
        Observable observableCast = observable.filter(WorkflowViewModel$getInteractiveTask$$inlined$filterIsInstance$1.INSTANCE).cast(FlowTask.InteractiveTask.class);
        Intrinsics.checkNotNullExpressionValue(observableCast, "cast(...)");
        Observable map = observableCast.map(AnonymousClass1.INSTANCE);
        Intrinsics.checkNotNullExpressionValue(map, "map(...)");
        Intrinsics.needClassReification();
        Observable observableFilter = map.filter(new Predicate() { // from class: com.onfido.workflow.internal.ui.WorkflowViewModel$getInteractiveTask$$inlined$filterIsInstance$2
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Intrinsics.reifiedOperationMarker(3, ExifInterface.GPS_DIRECTION_TRUE);
                return it instanceof Object;
            }
        });
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        Observable observableCast2 = observableFilter.cast(Object.class);
        Intrinsics.checkNotNullExpressionValue(observableCast2, "cast(...)");
        Observable<T> observableDistinctUntilChanged = observableCast2.distinctUntilChanged();
        Intrinsics.checkNotNullExpressionValue(observableDistinctUntilChanged, "distinctUntilChanged(...)");
        return observableDistinctUntilChanged;
    }
}
