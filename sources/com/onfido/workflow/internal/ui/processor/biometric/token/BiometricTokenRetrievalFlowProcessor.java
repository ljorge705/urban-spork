package com.onfido.workflow.internal.ui.processor.biometric.token;

import com.onfido.android.sdk.capture.internal.analytics.inhouse.OnfidoAnalytics;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.BiometricTokenEvents;
import com.onfido.android.sdk.capture.internal.navigation.Navigator;
import com.onfido.android.sdk.capture.internal.util.logging.Timber;
import com.onfido.javax.inject.Inject;
import com.onfido.workflow.internal.utils.NavigatorExtKt;
import com.onfido.workflow.internal.workflow.SubmitTaskCompletionUseCase;
import com.onfido.workflow.internal.workflow.WorkflowTask;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.JsonArray;
import kotlinx.serialization.json.JsonElementKt;
import kotlinx.serialization.json.JsonObject;

/* compiled from: BiometricTokenRetrievalFlowProcessor.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B1\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0007H\u0002J\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u0013\u001a\u00020\u0014J\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0013\u001a\u00020\u00172\u0006\u0010\u000f\u001a\u00020\u0007H\u0002R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/onfido/workflow/internal/ui/processor/biometric/token/BiometricTokenRetrievalFlowProcessor;", "", "navigator", "Lcom/onfido/android/sdk/capture/internal/navigation/Navigator;", "submitTaskCompletionUseCase", "Lcom/onfido/workflow/internal/workflow/SubmitTaskCompletionUseCase;", "customerUserHash", "", "biometricTokenRepository", "Lcom/onfido/workflow/internal/ui/processor/biometric/token/BiometricTokenRepository;", "analytics", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/OnfidoAnalytics;", "(Lcom/onfido/android/sdk/capture/internal/navigation/Navigator;Lcom/onfido/workflow/internal/workflow/SubmitTaskCompletionUseCase;Ljava/lang/String;Lcom/onfido/workflow/internal/ui/processor/biometric/token/BiometricTokenRepository;Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/OnfidoAnalytics;)V", "getCustomUploadObject", "Lkotlinx/serialization/json/JsonArray;", "biometricToken", "process", "Lio/reactivex/rxjava3/core/Observable;", "", "task", "Lcom/onfido/workflow/internal/workflow/WorkflowTask$BiometricTokenRetrievalTask;", "submitTaskCompletion", "Lio/reactivex/rxjava3/core/Completable;", "Lcom/onfido/workflow/internal/workflow/WorkflowTask;", "Companion", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class BiometricTokenRetrievalFlowProcessor {
    public static final String KEY_ENCRYPTED_BIOMETRIC_TOKEN = "encrypted_biometric_token";
    private final OnfidoAnalytics analytics;
    private final BiometricTokenRepository biometricTokenRepository;
    private final String customerUserHash;
    private final Navigator navigator;
    private final SubmitTaskCompletionUseCase submitTaskCompletionUseCase;

    @Inject
    public BiometricTokenRetrievalFlowProcessor(Navigator navigator, SubmitTaskCompletionUseCase submitTaskCompletionUseCase, String customerUserHash, BiometricTokenRepository biometricTokenRepository, OnfidoAnalytics analytics) {
        Intrinsics.checkNotNullParameter(navigator, "navigator");
        Intrinsics.checkNotNullParameter(submitTaskCompletionUseCase, "submitTaskCompletionUseCase");
        Intrinsics.checkNotNullParameter(customerUserHash, "customerUserHash");
        Intrinsics.checkNotNullParameter(biometricTokenRepository, "biometricTokenRepository");
        Intrinsics.checkNotNullParameter(analytics, "analytics");
        this.navigator = navigator;
        this.submitTaskCompletionUseCase = submitTaskCompletionUseCase;
        this.customerUserHash = customerUserHash;
        this.biometricTokenRepository = biometricTokenRepository;
        this.analytics = analytics;
    }

    public final Observable<Unit> process(final WorkflowTask.BiometricTokenRetrievalTask task) {
        Intrinsics.checkNotNullParameter(task, "task");
        final String value = this.biometricTokenRepository.getType().getValue();
        this.analytics.track(new BiometricTokenEvents.BiometricTokenRetrieval(value));
        Observable<Unit> observableOnErrorResumeNext = this.biometricTokenRepository.retrieveToken(this.customerUserHash).flatMap(new Function() { // from class: com.onfido.workflow.internal.ui.processor.biometric.token.BiometricTokenRetrievalFlowProcessor.process.1
            @Override // io.reactivex.rxjava3.functions.Function
            public final ObservableSource<? extends Unit> apply(String token) {
                Intrinsics.checkNotNullParameter(token, "token");
                BiometricTokenRetrievalFlowProcessor.this.analytics.track(new BiometricTokenEvents.BiometricTokenRetrievalCompleted(value));
                Completable completableSubmitTaskCompletion = BiometricTokenRetrievalFlowProcessor.this.submitTaskCompletion(task, token);
                final BiometricTokenRetrievalFlowProcessor biometricTokenRetrievalFlowProcessor = BiometricTokenRetrievalFlowProcessor.this;
                return completableSubmitTaskCompletion.doOnSubscribe(new Consumer() { // from class: com.onfido.workflow.internal.ui.processor.biometric.token.BiometricTokenRetrievalFlowProcessor.process.1.1
                    @Override // io.reactivex.rxjava3.functions.Consumer
                    public final void accept(Disposable it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        NavigatorExtKt.backToWorkflowRoot(biometricTokenRetrievalFlowProcessor.navigator);
                    }
                }).andThen(Observable.just(Unit.INSTANCE));
            }
        }).onErrorResumeNext(new Function() { // from class: com.onfido.workflow.internal.ui.processor.biometric.token.BiometricTokenRetrievalFlowProcessor.process.2
            @Override // io.reactivex.rxjava3.functions.Function
            public final ObservableSource<? extends Unit> apply(Throwable error) {
                Intrinsics.checkNotNullParameter(error, "error");
                BiometricTokenRetrievalFlowProcessor.this.analytics.track(new BiometricTokenEvents.BiometricTokenRetrievalError(value, error.getMessage()));
                return Observable.error(error);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableOnErrorResumeNext, "onErrorResumeNext(...)");
        return observableOnErrorResumeNext;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Completable submitTaskCompletion(final WorkflowTask task, String biometricToken) {
        Completable completableOnErrorComplete = this.submitTaskCompletionUseCase.executeCustom(task, getCustomUploadObject(biometricToken).toString()).onErrorComplete(new Predicate() { // from class: com.onfido.workflow.internal.ui.processor.biometric.token.BiometricTokenRetrievalFlowProcessor.submitTaskCompletion.1
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Throwable throwable) {
                Intrinsics.checkNotNullParameter(throwable, "throwable");
                Timber.INSTANCE.e(throwable, "Error submitting Biometric Token Retrieval: " + task + " completion", new Object[0]);
                return true;
            }
        });
        Intrinsics.checkNotNullExpressionValue(completableOnErrorComplete, "onErrorComplete(...)");
        return completableOnErrorComplete;
    }

    private final JsonArray getCustomUploadObject(String biometricToken) {
        return new JsonArray(CollectionsKt.listOf(new JsonObject(MapsKt.mapOf(TuplesKt.to("value", JsonElementKt.JsonPrimitive(biometricToken)), TuplesKt.to("type", JsonElementKt.JsonPrimitive(KEY_ENCRYPTED_BIOMETRIC_TOKEN))))));
    }
}
