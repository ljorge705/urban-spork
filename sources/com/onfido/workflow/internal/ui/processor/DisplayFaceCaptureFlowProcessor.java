package com.onfido.workflow.internal.ui.processor;

import android.content.Context;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.flow.CaptureStepDataBundle;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.model.UploadedArtifact;
import com.onfido.android.sdk.capture.internal.navigation.Navigator;
import com.onfido.android.sdk.capture.internal.util.logging.Timber;
import com.onfido.android.sdk.capture.ui.CaptureType;
import com.onfido.android.sdk.capture.ui.camera.selfie.SelfieCaptureScreen;
import com.onfido.javax.inject.Inject;
import com.onfido.workflow.internal.ui.FaceSelfieIntroScreen;
import com.onfido.workflow.internal.ui.model.UIEvent;
import com.onfido.workflow.internal.ui.processor.DisplayFaceCaptureFlowProcessor;
import com.onfido.workflow.internal.utils.NavigatorExtKt;
import com.onfido.workflow.internal.workflow.SubmitTaskCompletionUseCase;
import com.onfido.workflow.internal.workflow.WorkflowTask;
import com.onfido.workflow.internal.workflow.model.CaptureResult;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.ObservableTransformer;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import okhttp3.internal.ws.WebSocketProtocol;

/* compiled from: DisplayFaceCaptureFlowProcessor.kt */
@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001:\u0001)B9\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\u001c\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00120\u00102\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\b\u0010\u0015\u001a\u00020\u0016H\u0002J\u000e\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00120\u0018H\u0002J$\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00120\u00182\u0006\u0010\u0013\u001a\u00020\u00142\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00110\u0018H\u0002J$\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00120\u00182\u0006\u0010\u0013\u001a\u00020\u00142\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00110\u0018H\u0002J$\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00120\u00182\u0006\u0010\u0013\u001a\u00020\u00142\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00110\u0018H\u0002J\u001c\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001e0\u00182\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00110\u0018H\u0002J\"\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00120\u00182\u0006\u0010 \u001a\u00020!2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00110\u0018J\u001d\u0010\"\u001a\u00070#¢\u0006\u0002\b$2\u0006\u0010\u0013\u001a\u00020!2\u0006\u0010%\u001a\u00020&H\u0002J$\u0010'\u001a\b\u0012\u0004\u0012\u00020(0\u00182\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00110\u00182\u0006\u0010\u0013\u001a\u00020\u0014H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lcom/onfido/workflow/internal/ui/processor/DisplayFaceCaptureFlowProcessor;", "", "context", "Landroid/content/Context;", "faceLivenessFlowHelper", "Lcom/onfido/workflow/internal/ui/processor/FaceLivenessFlowHelper;", "permissionsFlowHelper", "Lcom/onfido/workflow/internal/ui/processor/PermissionsFlowHelper;", "navigator", "Lcom/onfido/android/sdk/capture/internal/navigation/Navigator;", "submitTaskCompletionUseCase", "Lcom/onfido/workflow/internal/workflow/SubmitTaskCompletionUseCase;", "onfidoRemoteConfig", "Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;", "(Landroid/content/Context;Lcom/onfido/workflow/internal/ui/processor/FaceLivenessFlowHelper;Lcom/onfido/workflow/internal/ui/processor/PermissionsFlowHelper;Lcom/onfido/android/sdk/capture/internal/navigation/Navigator;Lcom/onfido/workflow/internal/workflow/SubmitTaskCompletionUseCase;Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;)V", "composeFaceSelfieFlowObservable", "Lio/reactivex/rxjava3/core/ObservableTransformer;", "Lcom/onfido/workflow/internal/ui/model/UIEvent;", "Lcom/onfido/workflow/internal/ui/processor/DisplayFaceCaptureFlowProcessor$ProcessorOutcome;", "task", "Lcom/onfido/workflow/internal/workflow/WorkflowTask$FacePhotoTask;", "createFaceSelfieIntroScreen", "Lcom/onfido/workflow/internal/ui/FaceSelfieIntroScreen;", "finishFlow", "Lio/reactivex/rxjava3/core/Observable;", "launchSelfie", "uiEvents", "launchSelfieFragmentAndObserveResult", "launchSelfieLegacyAndObserveResult", "observeSelfieCaptureOnActivityResult", "Lcom/onfido/workflow/internal/workflow/model/CaptureResult$FaceUploadPhoto;", "process", "workflowTask", "Lcom/onfido/workflow/internal/workflow/WorkflowTask;", "submitTaskCompletion", "Lio/reactivex/rxjava3/core/Completable;", "Lio/reactivex/rxjava3/annotations/NonNull;", "uploadedArtifact", "Lcom/onfido/android/sdk/capture/internal/model/UploadedArtifact;", "tryOpeningIntro", "", "ProcessorOutcome", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class DisplayFaceCaptureFlowProcessor {
    private final Context context;
    private final FaceLivenessFlowHelper faceLivenessFlowHelper;
    private final Navigator navigator;
    private final OnfidoRemoteConfig onfidoRemoteConfig;
    private final PermissionsFlowHelper permissionsFlowHelper;
    private final SubmitTaskCompletionUseCase submitTaskCompletionUseCase;

    @Inject
    public DisplayFaceCaptureFlowProcessor(Context context, FaceLivenessFlowHelper faceLivenessFlowHelper, PermissionsFlowHelper permissionsFlowHelper, Navigator navigator, SubmitTaskCompletionUseCase submitTaskCompletionUseCase, OnfidoRemoteConfig onfidoRemoteConfig) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(faceLivenessFlowHelper, "faceLivenessFlowHelper");
        Intrinsics.checkNotNullParameter(permissionsFlowHelper, "permissionsFlowHelper");
        Intrinsics.checkNotNullParameter(navigator, "navigator");
        Intrinsics.checkNotNullParameter(submitTaskCompletionUseCase, "submitTaskCompletionUseCase");
        Intrinsics.checkNotNullParameter(onfidoRemoteConfig, "onfidoRemoteConfig");
        this.context = context;
        this.faceLivenessFlowHelper = faceLivenessFlowHelper;
        this.permissionsFlowHelper = permissionsFlowHelper;
        this.navigator = navigator;
        this.submitTaskCompletionUseCase = submitTaskCompletionUseCase;
        this.onfidoRemoteConfig = onfidoRemoteConfig;
    }

    public final Observable<ProcessorOutcome> process(final WorkflowTask workflowTask, Observable<UIEvent> uiEvents) {
        Intrinsics.checkNotNullParameter(workflowTask, "workflowTask");
        Intrinsics.checkNotNullParameter(uiEvents, "uiEvents");
        if (!(workflowTask instanceof WorkflowTask.FaceVideoTask) && !(workflowTask instanceof WorkflowTask.FacePhotoTask)) {
            throw new IllegalArgumentException("DisplayFaceCaptureFlowProcessor only supports face interactive tasks".toString());
        }
        Observable observablePublish = uiEvents.publish(new Function() { // from class: com.onfido.workflow.internal.ui.processor.DisplayFaceCaptureFlowProcessor.process.2
            @Override // io.reactivex.rxjava3.functions.Function
            public final ObservableSource<ProcessorOutcome> apply(Observable<UIEvent> sharedUIEventsStream) {
                Observable<R> observableEmpty;
                Intrinsics.checkNotNullParameter(sharedUIEventsStream, "sharedUIEventsStream");
                WorkflowTask workflowTask2 = workflowTask;
                if (workflowTask2 instanceof WorkflowTask.FacePhotoTask) {
                    observableEmpty = sharedUIEventsStream.compose(this.composeFaceSelfieFlowObservable((WorkflowTask.FacePhotoTask) workflowTask2));
                } else if (workflowTask2 instanceof WorkflowTask.FaceVideoTask) {
                    observableEmpty = sharedUIEventsStream.compose(this.faceLivenessFlowHelper.process((WorkflowTask.FaceVideoTask) workflowTask));
                } else {
                    observableEmpty = Observable.empty();
                }
                return observableEmpty;
            }
        });
        Intrinsics.checkNotNullExpressionValue(observablePublish, "publish(...)");
        return observablePublish;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ObservableTransformer<UIEvent, ProcessorOutcome> composeFaceSelfieFlowObservable(final WorkflowTask.FacePhotoTask task) {
        return new ObservableTransformer() { // from class: com.onfido.workflow.internal.ui.processor.DisplayFaceCaptureFlowProcessor$$ExternalSyntheticLambda3
            @Override // io.reactivex.rxjava3.core.ObservableTransformer
            public final ObservableSource apply(Observable observable) {
                return DisplayFaceCaptureFlowProcessor.composeFaceSelfieFlowObservable$lambda$1(this.f$0, task, observable);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ObservableSource composeFaceSelfieFlowObservable$lambda$1(final DisplayFaceCaptureFlowProcessor this$0, final WorkflowTask.FacePhotoTask task, final Observable uiEvents) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(task, "$task");
        Intrinsics.checkNotNullParameter(uiEvents, "uiEvents");
        return this$0.tryOpeningIntro(uiEvents, task).switchMap(new Function() { // from class: com.onfido.workflow.internal.ui.processor.DisplayFaceCaptureFlowProcessor$composeFaceSelfieFlowObservable$1$1
            @Override // io.reactivex.rxjava3.functions.Function
            public final ObservableSource<? extends CaptureStepDataBundle> apply(Unit it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return this.this$0.permissionsFlowHelper.checkRuntimePermissions(uiEvents, new CaptureStepDataBundle(CaptureType.FACE, null, null, null, null, null, null, WebSocketProtocol.PAYLOAD_SHORT, null));
            }
        }).switchMap(new Function() { // from class: com.onfido.workflow.internal.ui.processor.DisplayFaceCaptureFlowProcessor$composeFaceSelfieFlowObservable$1$2
            @Override // io.reactivex.rxjava3.functions.Function
            public final ObservableSource<? extends DisplayFaceCaptureFlowProcessor.ProcessorOutcome> apply(CaptureStepDataBundle it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return this.this$0.launchSelfie(task, uiEvents);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Observable<ProcessorOutcome> launchSelfie(WorkflowTask.FacePhotoTask task, Observable<UIEvent> uiEvents) {
        if (this.onfidoRemoteConfig.isRefactoredCaptureEnabled()) {
            return launchSelfieFragmentAndObserveResult(task, uiEvents);
        }
        return launchSelfieLegacyAndObserveResult(task, uiEvents);
    }

    private final Observable<ProcessorOutcome> launchSelfieLegacyAndObserveResult(final WorkflowTask.FacePhotoTask task, Observable<UIEvent> uiEvents) {
        Observable<ProcessorOutcome> observableConcat = Observable.concat(Observable.just(ProcessorOutcome.OpenFaceSelfieCaptureActivity.INSTANCE), observeSelfieCaptureOnActivityResult(uiEvents).map(new Function() { // from class: com.onfido.workflow.internal.ui.processor.DisplayFaceCaptureFlowProcessor.launchSelfieLegacyAndObserveResult.1
            @Override // io.reactivex.rxjava3.functions.Function
            public final UploadedArtifact apply(CaptureResult.FaceUploadPhoto it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.getUploadArtifact();
            }
        }).switchMap(new Function() { // from class: com.onfido.workflow.internal.ui.processor.DisplayFaceCaptureFlowProcessor.launchSelfieLegacyAndObserveResult.2
            @Override // io.reactivex.rxjava3.functions.Function
            public final ObservableSource<? extends ProcessorOutcome> apply(UploadedArtifact uploadArtifact) {
                Intrinsics.checkNotNullParameter(uploadArtifact, "uploadArtifact");
                return DisplayFaceCaptureFlowProcessor.this.submitTaskCompletion(task, uploadArtifact).andThen(DisplayFaceCaptureFlowProcessor.this.finishFlow());
            }
        }));
        Intrinsics.checkNotNullExpressionValue(observableConcat, "concat(...)");
        return observableConcat;
    }

    private final Observable<ProcessorOutcome> launchSelfieFragmentAndObserveResult(final WorkflowTask.FacePhotoTask task, Observable<UIEvent> uiEvents) {
        Completable completableFromAction = Completable.fromAction(new Action() { // from class: com.onfido.workflow.internal.ui.processor.DisplayFaceCaptureFlowProcessor$$ExternalSyntheticLambda1
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                DisplayFaceCaptureFlowProcessor.launchSelfieFragmentAndObserveResult$lambda$2(this.f$0);
            }
        });
        ObservableSource observableSourceCast = uiEvents.filter(new Predicate() { // from class: com.onfido.workflow.internal.ui.processor.DisplayFaceCaptureFlowProcessor$launchSelfieFragmentAndObserveResult$$inlined$filterIsInstance$1
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it instanceof UIEvent.OnFragmentResult.OnFaceSelfieCaptureFragmentResult;
            }
        }).cast(UIEvent.OnFragmentResult.OnFaceSelfieCaptureFragmentResult.class);
        Intrinsics.checkNotNullExpressionValue(observableSourceCast, "cast(...)");
        Observable map = completableFromAction.andThen(observableSourceCast).map(new Function() { // from class: com.onfido.workflow.internal.ui.processor.DisplayFaceCaptureFlowProcessor.launchSelfieFragmentAndObserveResult.2
            @Override // io.reactivex.rxjava3.functions.Function
            public final SelfieCaptureScreen.SelfieCaptureResult apply(UIEvent.OnFragmentResult.OnFaceSelfieCaptureFragmentResult it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.getResult();
            }
        });
        Intrinsics.checkNotNullExpressionValue(map, "map(...)");
        Observable observableCast = map.filter(new Predicate() { // from class: com.onfido.workflow.internal.ui.processor.DisplayFaceCaptureFlowProcessor$launchSelfieFragmentAndObserveResult$$inlined$filterIsInstance$2
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it instanceof SelfieCaptureScreen.SelfieCaptureResult.Completed;
            }
        }).cast(SelfieCaptureScreen.SelfieCaptureResult.Completed.class);
        Intrinsics.checkNotNullExpressionValue(observableCast, "cast(...)");
        Observable<ProcessorOutcome> observableSwitchMap = observableCast.switchMap(new Function() { // from class: com.onfido.workflow.internal.ui.processor.DisplayFaceCaptureFlowProcessor.launchSelfieFragmentAndObserveResult.3
            @Override // io.reactivex.rxjava3.functions.Function
            public final ObservableSource<? extends ProcessorOutcome> apply(SelfieCaptureScreen.SelfieCaptureResult.Completed result) {
                Intrinsics.checkNotNullParameter(result, "result");
                return DisplayFaceCaptureFlowProcessor.this.submitTaskCompletion(task, result.getUploadedArtifact()).andThen(DisplayFaceCaptureFlowProcessor.this.finishFlow());
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableSwitchMap, "switchMap(...)");
        return observableSwitchMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void launchSelfieFragmentAndObserveResult$lambda$2(DisplayFaceCaptureFlowProcessor this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.navigator.navigateTo(SelfieCaptureScreen.INSTANCE);
    }

    private final Observable<Unit> tryOpeningIntro(Observable<UIEvent> uiEvents, WorkflowTask.FacePhotoTask task) {
        if (task.getShowIntro()) {
            Completable completableFromAction = Completable.fromAction(new Action() { // from class: com.onfido.workflow.internal.ui.processor.DisplayFaceCaptureFlowProcessor$$ExternalSyntheticLambda2
                @Override // io.reactivex.rxjava3.functions.Action
                public final void run() {
                    DisplayFaceCaptureFlowProcessor.tryOpeningIntro$lambda$3(this.f$0);
                }
            });
            ObservableSource observableSourceCast = uiEvents.filter(new Predicate() { // from class: com.onfido.workflow.internal.ui.processor.DisplayFaceCaptureFlowProcessor$tryOpeningIntro$$inlined$filterIsInstance$1
                @Override // io.reactivex.rxjava3.functions.Predicate
                public final boolean test(Object it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return it instanceof UIEvent.OnFragmentResult.OnFaceSelfieIntroFragmentResult;
                }
            }).cast(UIEvent.OnFragmentResult.OnFaceSelfieIntroFragmentResult.class);
            Intrinsics.checkNotNullExpressionValue(observableSourceCast, "cast(...)");
            Observable<Unit> map = completableFromAction.andThen(observableSourceCast).map(new Function() { // from class: com.onfido.workflow.internal.ui.processor.DisplayFaceCaptureFlowProcessor.tryOpeningIntro.2
                public final void apply(UIEvent.OnFragmentResult.OnFaceSelfieIntroFragmentResult it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                }

                @Override // io.reactivex.rxjava3.functions.Function
                public /* bridge */ /* synthetic */ Object apply(Object obj) {
                    apply((UIEvent.OnFragmentResult.OnFaceSelfieIntroFragmentResult) obj);
                    return Unit.INSTANCE;
                }
            });
            Intrinsics.checkNotNull(map);
            return map;
        }
        Observable<Unit> observableJust = Observable.just(Unit.INSTANCE);
        Intrinsics.checkNotNull(observableJust);
        return observableJust;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void tryOpeningIntro$lambda$3(DisplayFaceCaptureFlowProcessor this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.navigator.navigateTo(this$0.createFaceSelfieIntroScreen());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Completable submitTaskCompletion(final WorkflowTask task, UploadedArtifact uploadedArtifact) {
        Completable completableOnErrorComplete = this.submitTaskCompletionUseCase.execute(task, uploadedArtifact.getId()).onErrorComplete(new Predicate() { // from class: com.onfido.workflow.internal.ui.processor.DisplayFaceCaptureFlowProcessor.submitTaskCompletion.1
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Throwable throwable) {
                Intrinsics.checkNotNullParameter(throwable, "throwable");
                Timber.INSTANCE.e(throwable, "Error during submitting Face Capture: " + task + " completion", new Object[0]);
                return true;
            }
        });
        Intrinsics.checkNotNullExpressionValue(completableOnErrorComplete, "onErrorComplete(...)");
        return completableOnErrorComplete;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Observable<ProcessorOutcome> finishFlow() {
        Observable<ProcessorOutcome> observableAndThen = Completable.fromAction(new Action() { // from class: com.onfido.workflow.internal.ui.processor.DisplayFaceCaptureFlowProcessor$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                DisplayFaceCaptureFlowProcessor.finishFlow$lambda$4(this.f$0);
            }
        }).andThen(Observable.just(ProcessorOutcome.FaceCapturingFlowFinished.INSTANCE));
        Intrinsics.checkNotNullExpressionValue(observableAndThen, "andThen(...)");
        return observableAndThen;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void finishFlow$lambda$4(DisplayFaceCaptureFlowProcessor this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        NavigatorExtKt.backToWorkflowRoot(this$0.navigator);
    }

    private final FaceSelfieIntroScreen createFaceSelfieIntroScreen() {
        String string = this.context.getString(R.string.onfido_selfie_intro_title);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        String string2 = this.context.getString(R.string.onfido_selfie_intro_subtitle);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        String string3 = this.context.getString(R.string.onfido_selfie_intro_banner_nudity_message);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
        return new FaceSelfieIntroScreen(string, string2, string3, CollectionsKt.listOf((Object[]) new String[]{this.context.getString(R.string.onfido_selfie_intro_list_item_face_forward), this.context.getString(R.string.onfido_selfie_intro_list_item_no_face_cover)}));
    }

    /* compiled from: DisplayFaceCaptureFlowProcessor.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0003\u0006\u0007\b¨\u0006\t"}, d2 = {"Lcom/onfido/workflow/internal/ui/processor/DisplayFaceCaptureFlowProcessor$ProcessorOutcome;", "", "()V", "FaceCapturingFlowFinished", "OpenFaceLivenessCaptureActivity", "OpenFaceSelfieCaptureActivity", "Lcom/onfido/workflow/internal/ui/processor/DisplayFaceCaptureFlowProcessor$ProcessorOutcome$FaceCapturingFlowFinished;", "Lcom/onfido/workflow/internal/ui/processor/DisplayFaceCaptureFlowProcessor$ProcessorOutcome$OpenFaceLivenessCaptureActivity;", "Lcom/onfido/workflow/internal/ui/processor/DisplayFaceCaptureFlowProcessor$ProcessorOutcome$OpenFaceSelfieCaptureActivity;", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static abstract class ProcessorOutcome {
        public /* synthetic */ ProcessorOutcome(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* compiled from: DisplayFaceCaptureFlowProcessor.kt */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/workflow/internal/ui/processor/DisplayFaceCaptureFlowProcessor$ProcessorOutcome$OpenFaceLivenessCaptureActivity;", "Lcom/onfido/workflow/internal/ui/processor/DisplayFaceCaptureFlowProcessor$ProcessorOutcome;", "()V", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class OpenFaceLivenessCaptureActivity extends ProcessorOutcome {
            public static final OpenFaceLivenessCaptureActivity INSTANCE = new OpenFaceLivenessCaptureActivity();

            private OpenFaceLivenessCaptureActivity() {
                super(null);
            }
        }

        private ProcessorOutcome() {
        }

        /* compiled from: DisplayFaceCaptureFlowProcessor.kt */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/workflow/internal/ui/processor/DisplayFaceCaptureFlowProcessor$ProcessorOutcome$OpenFaceSelfieCaptureActivity;", "Lcom/onfido/workflow/internal/ui/processor/DisplayFaceCaptureFlowProcessor$ProcessorOutcome;", "()V", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class OpenFaceSelfieCaptureActivity extends ProcessorOutcome {
            public static final OpenFaceSelfieCaptureActivity INSTANCE = new OpenFaceSelfieCaptureActivity();

            private OpenFaceSelfieCaptureActivity() {
                super(null);
            }
        }

        /* compiled from: DisplayFaceCaptureFlowProcessor.kt */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/workflow/internal/ui/processor/DisplayFaceCaptureFlowProcessor$ProcessorOutcome$FaceCapturingFlowFinished;", "Lcom/onfido/workflow/internal/ui/processor/DisplayFaceCaptureFlowProcessor$ProcessorOutcome;", "()V", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class FaceCapturingFlowFinished extends ProcessorOutcome {
            public static final FaceCapturingFlowFinished INSTANCE = new FaceCapturingFlowFinished();

            private FaceCapturingFlowFinished() {
                super(null);
            }
        }
    }

    private final Observable<CaptureResult.FaceUploadPhoto> observeSelfieCaptureOnActivityResult(Observable<UIEvent> uiEvents) {
        Observable<U> observableCast = uiEvents.filter(new Predicate() { // from class: com.onfido.workflow.internal.ui.processor.DisplayFaceCaptureFlowProcessor$observeSelfieCaptureOnActivityResult$$inlined$filterIsInstance$1
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it instanceof UIEvent.OnActivityResult;
            }
        }).cast(UIEvent.OnActivityResult.class);
        Intrinsics.checkNotNullExpressionValue(observableCast, "cast(...)");
        final C08181 c08181 = new PropertyReference1Impl() { // from class: com.onfido.workflow.internal.ui.processor.DisplayFaceCaptureFlowProcessor.observeSelfieCaptureOnActivityResult.1
            @Override // kotlin.jvm.internal.PropertyReference1Impl, kotlin.reflect.KProperty1
            public Object get(Object obj) {
                return ((UIEvent.OnActivityResult) obj).getCaptureResult();
            }
        };
        Observable map = observableCast.map(new Function(c08181) { // from class: com.onfido.workflow.internal.ui.processor.DisplayFaceCaptureFlowProcessor$sam$io_reactivex_rxjava3_functions_Function$0
            private final /* synthetic */ Function1 function;

            {
                Intrinsics.checkNotNullParameter(c08181, "function");
                this.function = c08181;
            }

            @Override // io.reactivex.rxjava3.functions.Function
            public final /* synthetic */ Object apply(Object obj) {
                return this.function.invoke(obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(map, "map(...)");
        Observable<CaptureResult.FaceUploadPhoto> observableCast2 = map.filter(new Predicate() { // from class: com.onfido.workflow.internal.ui.processor.DisplayFaceCaptureFlowProcessor$observeSelfieCaptureOnActivityResult$$inlined$filterIsInstance$2
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it instanceof CaptureResult.FaceUploadPhoto;
            }
        }).cast(CaptureResult.FaceUploadPhoto.class);
        Intrinsics.checkNotNullExpressionValue(observableCast2, "cast(...)");
        return observableCast2;
    }
}
