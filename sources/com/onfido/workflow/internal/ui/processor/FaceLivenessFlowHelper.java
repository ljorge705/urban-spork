package com.onfido.workflow.internal.ui.processor;

import com.onfido.android.sdk.capture.flow.CaptureStepDataBundle;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.model.UploadedArtifact;
import com.onfido.android.sdk.capture.internal.navigation.Navigator;
import com.onfido.android.sdk.capture.internal.util.logging.Timber;
import com.onfido.android.sdk.capture.ui.CaptureType;
import com.onfido.android.sdk.capture.ui.camera.liveness.LivenessConfirmationFragment;
import com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureScreen;
import com.onfido.android.sdk.capture.ui.camera.liveness.challenges.LivenessPerformedChallenges;
import com.onfido.javax.inject.Inject;
import com.onfido.workflow.internal.ui.FaceLivenessIntroScreen;
import com.onfido.workflow.internal.ui.LivenessConfirmationScreen;
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
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import okhttp3.internal.ws.WebSocketProtocol;

/* compiled from: FaceLivenessFlowHelper.kt */
@Metadata(d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u000e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0002J8\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\f2\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00160\u0014H\u0002J\u001e\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J$\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\f2\u0006\u0010\u0018\u001a\u00020\u0010H\u0002J$\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\r0\f2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\f2\u0006\u0010\u0018\u001a\u00020\u0010H\u0002J$\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\r0\f2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\f2\u0006\u0010\u0018\u001a\u00020\u0010H\u0002J\u001c\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\f2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\fH\u0002J\u001c\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\f2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\fH\u0002J8\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u001a0\f2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\f2\u0012\u0010#\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00160\u00142\u0006\u0010$\u001a\u00020%H\u0002J\u001a\u0010&\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\r0'2\u0006\u0010\u000f\u001a\u00020\u0010J\u001d\u0010(\u001a\u00070)¢\u0006\u0002\b*2\u0006\u0010\u0018\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J$\u0010.\u001a\b\u0012\u0004\u0012\u00020/0\f2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\f2\u0006\u0010\u0018\u001a\u00020\u0010H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00060"}, d2 = {"Lcom/onfido/workflow/internal/ui/processor/FaceLivenessFlowHelper;", "", "navigator", "Lcom/onfido/android/sdk/capture/internal/navigation/Navigator;", "submitTaskCompletionUseCase", "Lcom/onfido/workflow/internal/workflow/SubmitTaskCompletionUseCase;", "permissionsFlowHelper", "Lcom/onfido/workflow/internal/ui/processor/PermissionsFlowHelper;", "onfidoRemoteConfig", "Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;", "(Lcom/onfido/android/sdk/capture/internal/navigation/Navigator;Lcom/onfido/workflow/internal/workflow/SubmitTaskCompletionUseCase;Lcom/onfido/workflow/internal/ui/processor/PermissionsFlowHelper;Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;)V", "finishFlow", "Lio/reactivex/rxjava3/core/Observable;", "Lcom/onfido/workflow/internal/ui/processor/DisplayFaceCaptureFlowProcessor$ProcessorOutcome;", "handleFaceUploadActivityResult", "workflowTask", "Lcom/onfido/workflow/internal/workflow/WorkflowTask$FaceVideoTask;", "uiEvents", "Lcom/onfido/workflow/internal/ui/model/UIEvent;", "faceUploadActivityResult", "Lkotlin/Pair;", "", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessPerformedChallenges;", "handleLivenessConfirmationResult", "task", "livenessConfirmationResult", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/LivenessConfirmationFragment$LivenessConfirmationResult;", "launchLiveness", "launchLivenessFragmentAndObserveResult", "launchLivenessLegacyAndObserveResult", "observeBackButtonOnLivenessConfirmation", "Lcom/onfido/workflow/internal/ui/processor/DisplayFaceCaptureFlowProcessor$ProcessorOutcome$OpenFaceLivenessCaptureActivity;", "observeFaceLivenessCaptureActivityResult", "Lcom/onfido/workflow/internal/workflow/model/CaptureResult$FaceUploadVideo;", "openLivenessConfirmationScreen", "faceUploadVideo", "showRecordedVideo", "", "process", "Lio/reactivex/rxjava3/core/ObservableTransformer;", "submitTaskCompletion", "Lio/reactivex/rxjava3/core/Completable;", "Lio/reactivex/rxjava3/annotations/NonNull;", "Lcom/onfido/workflow/internal/workflow/WorkflowTask;", "uploadedArtifact", "Lcom/onfido/android/sdk/capture/internal/model/UploadedArtifact;", "tryOpeningIntro", "", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class FaceLivenessFlowHelper {
    private final Navigator navigator;
    private final OnfidoRemoteConfig onfidoRemoteConfig;
    private final PermissionsFlowHelper permissionsFlowHelper;
    private final SubmitTaskCompletionUseCase submitTaskCompletionUseCase;

    @Inject
    public FaceLivenessFlowHelper(Navigator navigator, SubmitTaskCompletionUseCase submitTaskCompletionUseCase, PermissionsFlowHelper permissionsFlowHelper, OnfidoRemoteConfig onfidoRemoteConfig) {
        Intrinsics.checkNotNullParameter(navigator, "navigator");
        Intrinsics.checkNotNullParameter(submitTaskCompletionUseCase, "submitTaskCompletionUseCase");
        Intrinsics.checkNotNullParameter(permissionsFlowHelper, "permissionsFlowHelper");
        Intrinsics.checkNotNullParameter(onfidoRemoteConfig, "onfidoRemoteConfig");
        this.navigator = navigator;
        this.submitTaskCompletionUseCase = submitTaskCompletionUseCase;
        this.permissionsFlowHelper = permissionsFlowHelper;
        this.onfidoRemoteConfig = onfidoRemoteConfig;
    }

    public final ObservableTransformer<UIEvent, DisplayFaceCaptureFlowProcessor.ProcessorOutcome> process(final WorkflowTask.FaceVideoTask workflowTask) {
        Intrinsics.checkNotNullParameter(workflowTask, "workflowTask");
        return new ObservableTransformer() { // from class: com.onfido.workflow.internal.ui.processor.FaceLivenessFlowHelper$$ExternalSyntheticLambda4
            @Override // io.reactivex.rxjava3.core.ObservableTransformer
            public final ObservableSource apply(Observable observable) {
                return FaceLivenessFlowHelper.process$lambda$0(this.f$0, workflowTask, observable);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ObservableSource process$lambda$0(final FaceLivenessFlowHelper this$0, final WorkflowTask.FaceVideoTask workflowTask, final Observable uiEvents) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(workflowTask, "$workflowTask");
        Intrinsics.checkNotNullParameter(uiEvents, "uiEvents");
        return this$0.tryOpeningIntro(uiEvents, workflowTask).switchMap(new Function() { // from class: com.onfido.workflow.internal.ui.processor.FaceLivenessFlowHelper$process$1$1
            @Override // io.reactivex.rxjava3.functions.Function
            public final ObservableSource<? extends CaptureStepDataBundle> apply(Unit it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return this.this$0.permissionsFlowHelper.checkRuntimePermissions(uiEvents, new CaptureStepDataBundle(CaptureType.VIDEO, null, null, null, null, null, null, WebSocketProtocol.PAYLOAD_SHORT, null));
            }
        }).switchMap(new Function() { // from class: com.onfido.workflow.internal.ui.processor.FaceLivenessFlowHelper$process$1$2
            @Override // io.reactivex.rxjava3.functions.Function
            public final ObservableSource<? extends DisplayFaceCaptureFlowProcessor.ProcessorOutcome> apply(CaptureStepDataBundle it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return this.this$0.launchLiveness(uiEvents, workflowTask);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Observable<DisplayFaceCaptureFlowProcessor.ProcessorOutcome> launchLiveness(Observable<UIEvent> uiEvents, WorkflowTask.FaceVideoTask task) {
        if (this.onfidoRemoteConfig.isRefactoredCaptureEnabled()) {
            return launchLivenessFragmentAndObserveResult(uiEvents, task);
        }
        return launchLivenessLegacyAndObserveResult(uiEvents, task);
    }

    private final Observable<DisplayFaceCaptureFlowProcessor.ProcessorOutcome> launchLivenessFragmentAndObserveResult(final Observable<UIEvent> uiEvents, final WorkflowTask.FaceVideoTask task) {
        Completable completableFromAction = Completable.fromAction(new Action() { // from class: com.onfido.workflow.internal.ui.processor.FaceLivenessFlowHelper$$ExternalSyntheticLambda1
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                FaceLivenessFlowHelper.launchLivenessFragmentAndObserveResult$lambda$1(this.f$0);
            }
        });
        ObservableSource observableSourceCast = uiEvents.filter(new Predicate() { // from class: com.onfido.workflow.internal.ui.processor.FaceLivenessFlowHelper$launchLivenessFragmentAndObserveResult$$inlined$filterIsInstance$1
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it instanceof UIEvent.OnFragmentResult.OnFaceLivenessCaptureFragmentResult;
            }
        }).cast(UIEvent.OnFragmentResult.OnFaceLivenessCaptureFragmentResult.class);
        Intrinsics.checkNotNullExpressionValue(observableSourceCast, "cast(...)");
        Observable map = completableFromAction.andThen(observableSourceCast).map(new Function() { // from class: com.onfido.workflow.internal.ui.processor.FaceLivenessFlowHelper.launchLivenessFragmentAndObserveResult.2
            @Override // io.reactivex.rxjava3.functions.Function
            public final LivenessCaptureScreen.LivenessCaptureResult apply(UIEvent.OnFragmentResult.OnFaceLivenessCaptureFragmentResult it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.getResult();
            }
        });
        Intrinsics.checkNotNullExpressionValue(map, "map(...)");
        Observable observableCast = map.filter(new Predicate() { // from class: com.onfido.workflow.internal.ui.processor.FaceLivenessFlowHelper$launchLivenessFragmentAndObserveResult$$inlined$filterIsInstance$2
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it instanceof LivenessCaptureScreen.LivenessCaptureResult.Completed;
            }
        }).cast(LivenessCaptureScreen.LivenessCaptureResult.Completed.class);
        Intrinsics.checkNotNullExpressionValue(observableCast, "cast(...)");
        Observable<DisplayFaceCaptureFlowProcessor.ProcessorOutcome> observableSwitchMap = observableCast.map(new Function() { // from class: com.onfido.workflow.internal.ui.processor.FaceLivenessFlowHelper.launchLivenessFragmentAndObserveResult.3
            @Override // io.reactivex.rxjava3.functions.Function
            public final Pair<String, LivenessPerformedChallenges> apply(LivenessCaptureScreen.LivenessCaptureResult.Completed it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return TuplesKt.to(it.getVideoPath(), it.getPerformedChallenges());
            }
        }).switchMap(new Function() { // from class: com.onfido.workflow.internal.ui.processor.FaceLivenessFlowHelper.launchLivenessFragmentAndObserveResult.4
            @Override // io.reactivex.rxjava3.functions.Function
            public final ObservableSource<? extends DisplayFaceCaptureFlowProcessor.ProcessorOutcome> apply(Pair<String, LivenessPerformedChallenges> result) {
                Intrinsics.checkNotNullParameter(result, "result");
                return FaceLivenessFlowHelper.this.handleFaceUploadActivityResult(task, uiEvents, result);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableSwitchMap, "switchMap(...)");
        return observableSwitchMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void launchLivenessFragmentAndObserveResult$lambda$1(FaceLivenessFlowHelper this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.navigator.navigateTo(LivenessCaptureScreen.INSTANCE);
    }

    private final Observable<DisplayFaceCaptureFlowProcessor.ProcessorOutcome> launchLivenessLegacyAndObserveResult(final Observable<UIEvent> uiEvents, final WorkflowTask.FaceVideoTask task) {
        Observable<DisplayFaceCaptureFlowProcessor.ProcessorOutcome> observableConcat = Observable.concat(Observable.just(DisplayFaceCaptureFlowProcessor.ProcessorOutcome.OpenFaceLivenessCaptureActivity.INSTANCE), observeFaceLivenessCaptureActivityResult(uiEvents).switchMap(new Function() { // from class: com.onfido.workflow.internal.ui.processor.FaceLivenessFlowHelper.launchLivenessLegacyAndObserveResult.1
            @Override // io.reactivex.rxjava3.functions.Function
            public final ObservableSource<? extends DisplayFaceCaptureFlowProcessor.ProcessorOutcome> apply(CaptureResult.FaceUploadVideo result) {
                Intrinsics.checkNotNullParameter(result, "result");
                return FaceLivenessFlowHelper.this.handleFaceUploadActivityResult(task, uiEvents, TuplesKt.to(result.getVideoPath(), result.getLivenessChallenges()));
            }
        }));
        Intrinsics.checkNotNullExpressionValue(observableConcat, "concat(...)");
        return observableConcat;
    }

    private final Observable<Unit> tryOpeningIntro(Observable<UIEvent> uiEvents, WorkflowTask.FaceVideoTask task) {
        if (task.getShowIntro()) {
            Completable completableFromAction = Completable.fromAction(new Action() { // from class: com.onfido.workflow.internal.ui.processor.FaceLivenessFlowHelper$$ExternalSyntheticLambda5
                @Override // io.reactivex.rxjava3.functions.Action
                public final void run() {
                    FaceLivenessFlowHelper.tryOpeningIntro$lambda$2(this.f$0);
                }
            });
            ObservableSource observableSourceCast = uiEvents.filter(new Predicate() { // from class: com.onfido.workflow.internal.ui.processor.FaceLivenessFlowHelper$tryOpeningIntro$$inlined$filterIsInstance$1
                @Override // io.reactivex.rxjava3.functions.Predicate
                public final boolean test(Object it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return it instanceof UIEvent.OnFragmentResult.OnFaceLivenessIntroFragmentResult;
                }
            }).cast(UIEvent.OnFragmentResult.OnFaceLivenessIntroFragmentResult.class);
            Intrinsics.checkNotNullExpressionValue(observableSourceCast, "cast(...)");
            Observable<Unit> map = completableFromAction.andThen(observableSourceCast).map(new Function() { // from class: com.onfido.workflow.internal.ui.processor.FaceLivenessFlowHelper.tryOpeningIntro.2
                public final void apply(UIEvent.OnFragmentResult.OnFaceLivenessIntroFragmentResult it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                }

                @Override // io.reactivex.rxjava3.functions.Function
                public /* bridge */ /* synthetic */ Object apply(Object obj) {
                    apply((UIEvent.OnFragmentResult.OnFaceLivenessIntroFragmentResult) obj);
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
    public static final void tryOpeningIntro$lambda$2(FaceLivenessFlowHelper this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.navigator.navigateTo(new FaceLivenessIntroScreen(false, 1, null));
    }

    private final Observable<LivenessConfirmationFragment.LivenessConfirmationResult> openLivenessConfirmationScreen(Observable<UIEvent> uiEvents, final Pair<String, LivenessPerformedChallenges> faceUploadVideo, final boolean showRecordedVideo) {
        Completable completableFromAction = Completable.fromAction(new Action() { // from class: com.onfido.workflow.internal.ui.processor.FaceLivenessFlowHelper$$ExternalSyntheticLambda2
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                FaceLivenessFlowHelper.openLivenessConfirmationScreen$lambda$3(this.f$0, faceUploadVideo, showRecordedVideo);
            }
        });
        Observable<U> observableCast = uiEvents.filter(new Predicate() { // from class: com.onfido.workflow.internal.ui.processor.FaceLivenessFlowHelper$openLivenessConfirmationScreen$$inlined$filterIsInstance$1
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it instanceof UIEvent.OnFragmentResult.LivenessConfirmationFragmentResult;
            }
        }).cast(UIEvent.OnFragmentResult.LivenessConfirmationFragmentResult.class);
        Intrinsics.checkNotNullExpressionValue(observableCast, "cast(...)");
        Observable<LivenessConfirmationFragment.LivenessConfirmationResult> observableAndThen = completableFromAction.andThen(observableCast.map(new FaceLivenessFlowHelper$sam$io_reactivex_rxjava3_functions_Function$0(new PropertyReference1Impl() { // from class: com.onfido.workflow.internal.ui.processor.FaceLivenessFlowHelper.openLivenessConfirmationScreen.2
            @Override // kotlin.jvm.internal.PropertyReference1Impl, kotlin.reflect.KProperty1
            public Object get(Object obj) {
                return ((UIEvent.OnFragmentResult.LivenessConfirmationFragmentResult) obj).getResult();
            }
        })));
        Intrinsics.checkNotNullExpressionValue(observableAndThen, "andThen(...)");
        return observableAndThen;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void openLivenessConfirmationScreen$lambda$3(FaceLivenessFlowHelper this$0, Pair faceUploadVideo, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(faceUploadVideo, "$faceUploadVideo");
        this$0.navigator.navigateTo(new LivenessConfirmationScreen((String) faceUploadVideo.getFirst(), (LivenessPerformedChallenges) faceUploadVideo.getSecond(), z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Observable<DisplayFaceCaptureFlowProcessor.ProcessorOutcome> handleLivenessConfirmationResult(WorkflowTask.FaceVideoTask task, LivenessConfirmationFragment.LivenessConfirmationResult livenessConfirmationResult) {
        if (Intrinsics.areEqual(livenessConfirmationResult, LivenessConfirmationFragment.LivenessConfirmationResult.Exit.INSTANCE)) {
            Observable observableJust = Observable.just(DisplayFaceCaptureFlowProcessor.ProcessorOutcome.OpenFaceLivenessCaptureActivity.INSTANCE);
            final Navigator navigator = this.navigator;
            Observable<DisplayFaceCaptureFlowProcessor.ProcessorOutcome> observableConcat = Observable.concat(observableJust, Observable.fromAction(new Action() { // from class: com.onfido.workflow.internal.ui.processor.FaceLivenessFlowHelper$$ExternalSyntheticLambda3
                @Override // io.reactivex.rxjava3.functions.Action
                public final void run() {
                    navigator.exitCurrentScreen();
                }
            }));
            Intrinsics.checkNotNullExpressionValue(observableConcat, "concat(...)");
            return observableConcat;
        }
        if ((livenessConfirmationResult instanceof LivenessConfirmationFragment.LivenessConfirmationResult.OnError.OnInvalidCertificate) || Intrinsics.areEqual(livenessConfirmationResult, LivenessConfirmationFragment.LivenessConfirmationResult.OnError.OnTokenExpired.INSTANCE)) {
            Observable<DisplayFaceCaptureFlowProcessor.ProcessorOutcome> observableEmpty = Observable.empty();
            Intrinsics.checkNotNullExpressionValue(observableEmpty, "empty(...)");
            return observableEmpty;
        }
        if (!(livenessConfirmationResult instanceof LivenessConfirmationFragment.LivenessConfirmationResult.VideoUploadedSuccessfully)) {
            throw new NoWhenBranchMatchedException();
        }
        Observable<DisplayFaceCaptureFlowProcessor.ProcessorOutcome> observableAndThen = submitTaskCompletion(task, ((LivenessConfirmationFragment.LivenessConfirmationResult.VideoUploadedSuccessfully) livenessConfirmationResult).getUploadResult()).andThen(finishFlow());
        Intrinsics.checkNotNullExpressionValue(observableAndThen, "andThen(...)");
        return observableAndThen;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Observable<DisplayFaceCaptureFlowProcessor.ProcessorOutcome> handleFaceUploadActivityResult(final WorkflowTask.FaceVideoTask workflowTask, Observable<UIEvent> uiEvents, Pair<String, LivenessPerformedChallenges> faceUploadActivityResult) {
        Observable<DisplayFaceCaptureFlowProcessor.ProcessorOutcome> observableMergeWith = openLivenessConfirmationScreen(uiEvents, faceUploadActivityResult, workflowTask.getShowVideoConfirmation()).switchMap(new Function() { // from class: com.onfido.workflow.internal.ui.processor.FaceLivenessFlowHelper.handleFaceUploadActivityResult.1
            @Override // io.reactivex.rxjava3.functions.Function
            public final ObservableSource<? extends DisplayFaceCaptureFlowProcessor.ProcessorOutcome> apply(LivenessConfirmationFragment.LivenessConfirmationResult result) {
                Intrinsics.checkNotNullParameter(result, "result");
                return FaceLivenessFlowHelper.this.handleLivenessConfirmationResult(workflowTask, result);
            }
        }).mergeWith(observeBackButtonOnLivenessConfirmation(uiEvents));
        Intrinsics.checkNotNullExpressionValue(observableMergeWith, "mergeWith(...)");
        return observableMergeWith;
    }

    private final Completable submitTaskCompletion(final WorkflowTask task, UploadedArtifact uploadedArtifact) {
        Completable completableOnErrorComplete = this.submitTaskCompletionUseCase.execute(task, uploadedArtifact.getId()).onErrorComplete(new Predicate() { // from class: com.onfido.workflow.internal.ui.processor.FaceLivenessFlowHelper.submitTaskCompletion.1
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

    private final Observable<DisplayFaceCaptureFlowProcessor.ProcessorOutcome> finishFlow() {
        Observable<DisplayFaceCaptureFlowProcessor.ProcessorOutcome> observableAndThen = Completable.fromAction(new Action() { // from class: com.onfido.workflow.internal.ui.processor.FaceLivenessFlowHelper$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                FaceLivenessFlowHelper.finishFlow$lambda$4(this.f$0);
            }
        }).andThen(Observable.just(DisplayFaceCaptureFlowProcessor.ProcessorOutcome.FaceCapturingFlowFinished.INSTANCE));
        Intrinsics.checkNotNullExpressionValue(observableAndThen, "andThen(...)");
        return observableAndThen;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void finishFlow$lambda$4(FaceLivenessFlowHelper this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        NavigatorExtKt.backToWorkflowRoot(this$0.navigator);
    }

    private final Observable<CaptureResult.FaceUploadVideo> observeFaceLivenessCaptureActivityResult(Observable<UIEvent> uiEvents) {
        Observable<U> observableCast = uiEvents.filter(new Predicate() { // from class: com.onfido.workflow.internal.ui.processor.FaceLivenessFlowHelper$observeFaceLivenessCaptureActivityResult$$inlined$filterIsInstance$1
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it instanceof UIEvent.OnActivityResult;
            }
        }).cast(UIEvent.OnActivityResult.class);
        Intrinsics.checkNotNullExpressionValue(observableCast, "cast(...)");
        Observable map = observableCast.map(new FaceLivenessFlowHelper$sam$io_reactivex_rxjava3_functions_Function$0(new PropertyReference1Impl() { // from class: com.onfido.workflow.internal.ui.processor.FaceLivenessFlowHelper.observeFaceLivenessCaptureActivityResult.1
            @Override // kotlin.jvm.internal.PropertyReference1Impl, kotlin.reflect.KProperty1
            public Object get(Object obj) {
                return ((UIEvent.OnActivityResult) obj).getCaptureResult();
            }
        }));
        Intrinsics.checkNotNullExpressionValue(map, "map(...)");
        Observable<CaptureResult.FaceUploadVideo> observableCast2 = map.filter(new Predicate() { // from class: com.onfido.workflow.internal.ui.processor.FaceLivenessFlowHelper$observeFaceLivenessCaptureActivityResult$$inlined$filterIsInstance$2
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it instanceof CaptureResult.FaceUploadVideo;
            }
        }).cast(CaptureResult.FaceUploadVideo.class);
        Intrinsics.checkNotNullExpressionValue(observableCast2, "cast(...)");
        return observableCast2;
    }

    private final Observable<DisplayFaceCaptureFlowProcessor.ProcessorOutcome.OpenFaceLivenessCaptureActivity> observeBackButtonOnLivenessConfirmation(Observable<UIEvent> uiEvents) {
        Observable<U> observableCast = uiEvents.filter(new Predicate() { // from class: com.onfido.workflow.internal.ui.processor.FaceLivenessFlowHelper$observeBackButtonOnLivenessConfirmation$$inlined$filterIsInstance$1
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it instanceof UIEvent.OnBackPressed;
            }
        }).cast(UIEvent.OnBackPressed.class);
        Intrinsics.checkNotNullExpressionValue(observableCast, "cast(...)");
        Observable<DisplayFaceCaptureFlowProcessor.ProcessorOutcome.OpenFaceLivenessCaptureActivity> map = observableCast.filter(new Predicate() { // from class: com.onfido.workflow.internal.ui.processor.FaceLivenessFlowHelper.observeBackButtonOnLivenessConfirmation.1
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(UIEvent.OnBackPressed it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return CollectionsKt.lastOrNull((List) it.getBackstackSnapshot()) instanceof LivenessConfirmationScreen;
            }
        }).map(new Function() { // from class: com.onfido.workflow.internal.ui.processor.FaceLivenessFlowHelper.observeBackButtonOnLivenessConfirmation.2
            @Override // io.reactivex.rxjava3.functions.Function
            public final DisplayFaceCaptureFlowProcessor.ProcessorOutcome.OpenFaceLivenessCaptureActivity apply(UIEvent.OnBackPressed it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return DisplayFaceCaptureFlowProcessor.ProcessorOutcome.OpenFaceLivenessCaptureActivity.INSTANCE;
            }
        });
        Intrinsics.checkNotNullExpressionValue(map, "map(...)");
        return map;
    }
}
