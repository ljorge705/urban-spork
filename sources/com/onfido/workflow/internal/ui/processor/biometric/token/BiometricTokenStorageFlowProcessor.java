package com.onfido.workflow.internal.ui.processor.biometric.token;

import com.onfido.android.sdk.capture.errors.OnfidoException;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.OnfidoAnalytics;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.BiometricTokenEvents;
import com.onfido.android.sdk.capture.internal.util.logging.Timber;
import com.onfido.javax.inject.Inject;
import com.onfido.workflow.internal.network.BiometricTokenApi;
import com.onfido.workflow.internal.network.BiometricTokenEncryptionRequest;
import com.onfido.workflow.internal.network.BiometricTokenEncryptionResponse;
import com.onfido.workflow.internal.ui.processor.biometric.token.BiometricTokenStorageFlowProcessor;
import com.onfido.workflow.internal.workflow.SubmitTaskCompletionUseCase;
import com.onfido.workflow.internal.workflow.WorkflowTask;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BiometricTokenStorageFlowProcessor.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B1\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0010\u001a\u00020\u0011J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0010\u001a\u00020\u0014H\u0002R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/onfido/workflow/internal/ui/processor/biometric/token/BiometricTokenStorageFlowProcessor;", "", "biometricTokenApi", "Lcom/onfido/workflow/internal/network/BiometricTokenApi;", "submitTaskCompletionUseCase", "Lcom/onfido/workflow/internal/workflow/SubmitTaskCompletionUseCase;", "customerUserHash", "", "biometricTokenRepository", "Lcom/onfido/workflow/internal/ui/processor/biometric/token/BiometricTokenRepository;", "analytics", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/OnfidoAnalytics;", "(Lcom/onfido/workflow/internal/network/BiometricTokenApi;Lcom/onfido/workflow/internal/workflow/SubmitTaskCompletionUseCase;Ljava/lang/String;Lcom/onfido/workflow/internal/ui/processor/biometric/token/BiometricTokenRepository;Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/OnfidoAnalytics;)V", "process", "Lio/reactivex/rxjava3/core/Observable;", "", "task", "Lcom/onfido/workflow/internal/workflow/WorkflowTask$BiometricTokenStorageTask;", "submitTaskCompletion", "Lio/reactivex/rxjava3/core/Completable;", "Lcom/onfido/workflow/internal/workflow/WorkflowTask;", "Companion", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class BiometricTokenStorageFlowProcessor {
    public static final String EMPTY_REQUEST_OBJECT = "{}";
    private final OnfidoAnalytics analytics;
    private final BiometricTokenApi biometricTokenApi;
    private final BiometricTokenRepository biometricTokenRepository;
    private final String customerUserHash;
    private final SubmitTaskCompletionUseCase submitTaskCompletionUseCase;

    @Inject
    public BiometricTokenStorageFlowProcessor(BiometricTokenApi biometricTokenApi, SubmitTaskCompletionUseCase submitTaskCompletionUseCase, String customerUserHash, BiometricTokenRepository biometricTokenRepository, OnfidoAnalytics analytics) {
        Intrinsics.checkNotNullParameter(biometricTokenApi, "biometricTokenApi");
        Intrinsics.checkNotNullParameter(submitTaskCompletionUseCase, "submitTaskCompletionUseCase");
        Intrinsics.checkNotNullParameter(customerUserHash, "customerUserHash");
        Intrinsics.checkNotNullParameter(biometricTokenRepository, "biometricTokenRepository");
        Intrinsics.checkNotNullParameter(analytics, "analytics");
        this.biometricTokenApi = biometricTokenApi;
        this.submitTaskCompletionUseCase = submitTaskCompletionUseCase;
        this.customerUserHash = customerUserHash;
        this.biometricTokenRepository = biometricTokenRepository;
        this.analytics = analytics;
    }

    public final Observable<Unit> process(WorkflowTask.BiometricTokenStorageTask task) {
        Intrinsics.checkNotNullParameter(task, "task");
        final String value = this.biometricTokenRepository.getType().getValue();
        this.analytics.track(new BiometricTokenEvents.BiometricTokenStorage(value));
        if (this.customerUserHash.length() == 0) {
            this.analytics.track(new BiometricTokenEvents.BiometricTokenStorageError(value, "Customer user hash must be provided"));
            Observable<Unit> observableError = Observable.error(new OnfidoException("Customer user hash must be provided"));
            Intrinsics.checkNotNull(observableError);
            return observableError;
        }
        Observable<Unit> observableAndThen = this.biometricTokenApi.encrypt(new BiometricTokenEncryptionRequest(task.getMediaUuid())).flatMapCompletable(new AnonymousClass1(task, value)).doOnError(new Consumer() { // from class: com.onfido.workflow.internal.ui.processor.biometric.token.BiometricTokenStorageFlowProcessor.process.2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable error) {
                Intrinsics.checkNotNullParameter(error, "error");
                BiometricTokenStorageFlowProcessor.this.analytics.track(new BiometricTokenEvents.BiometricTokenStorageError(value, error.getMessage()));
            }
        }).andThen(Observable.just(Unit.INSTANCE));
        Intrinsics.checkNotNull(observableAndThen);
        return observableAndThen;
    }

    /* compiled from: BiometricTokenStorageFlowProcessor.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Lio/reactivex/rxjava3/core/CompletableSource;", "encryptionResponse", "Lcom/onfido/workflow/internal/network/BiometricTokenEncryptionResponse;", "apply"}, k = 3, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.onfido.workflow.internal.ui.processor.biometric.token.BiometricTokenStorageFlowProcessor$process$1, reason: invalid class name */
    static final class AnonymousClass1<T, R> implements Function {
        final /* synthetic */ String $storageType;
        final /* synthetic */ WorkflowTask.BiometricTokenStorageTask $task;

        AnonymousClass1(WorkflowTask.BiometricTokenStorageTask biometricTokenStorageTask, String str) {
            this.$task = biometricTokenStorageTask;
            this.$storageType = str;
        }

        @Override // io.reactivex.rxjava3.functions.Function
        public final CompletableSource apply(BiometricTokenEncryptionResponse encryptionResponse) {
            Intrinsics.checkNotNullParameter(encryptionResponse, "encryptionResponse");
            Completable completableStoreToken = BiometricTokenStorageFlowProcessor.this.biometricTokenRepository.storeToken(BiometricTokenStorageFlowProcessor.this.customerUserHash, encryptionResponse.getBiometricToken());
            final BiometricTokenStorageFlowProcessor biometricTokenStorageFlowProcessor = BiometricTokenStorageFlowProcessor.this;
            final String str = this.$storageType;
            return completableStoreToken.doOnComplete(new Action() { // from class: com.onfido.workflow.internal.ui.processor.biometric.token.BiometricTokenStorageFlowProcessor$process$1$$ExternalSyntheticLambda0
                @Override // io.reactivex.rxjava3.functions.Action
                public final void run() {
                    BiometricTokenStorageFlowProcessor.AnonymousClass1.apply$lambda$0(biometricTokenStorageFlowProcessor, str);
                }
            }).andThen(BiometricTokenStorageFlowProcessor.this.submitTaskCompletion(this.$task));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void apply$lambda$0(BiometricTokenStorageFlowProcessor this$0, String storageType) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(storageType, "$storageType");
            this$0.analytics.track(new BiometricTokenEvents.BiometricTokenStorageCompleted(storageType));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Completable submitTaskCompletion(final WorkflowTask task) {
        Completable completableOnErrorComplete = this.submitTaskCompletionUseCase.executeCustom(task, EMPTY_REQUEST_OBJECT).onErrorComplete(new Predicate() { // from class: com.onfido.workflow.internal.ui.processor.biometric.token.BiometricTokenStorageFlowProcessor.submitTaskCompletion.1
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Throwable throwable) {
                Intrinsics.checkNotNullParameter(throwable, "throwable");
                Timber.INSTANCE.e(throwable, "Error submitting Biometric Token Storage: " + task + " completion", new Object[0]);
                return true;
            }
        });
        Intrinsics.checkNotNullExpressionValue(completableOnErrorComplete, "onErrorComplete(...)");
        return completableOnErrorComplete;
    }
}
