package com.onfido.workflow.internal.di;

import android.os.ResultReceiver;
import com.onfido.android.sdk.FlowConfig;
import com.onfido.android.sdk.capture.OnfidoConfig;
import com.onfido.api.client.OnfidoFetcher;
import com.onfido.dagger.Binds;
import com.onfido.dagger.Module;
import com.onfido.dagger.Provides;
import com.onfido.workflow.WorkflowConfig;
import com.onfido.workflow.internal.network.BiometricTokenApi;
import com.onfido.workflow.internal.network.WorkflowApi;
import com.onfido.workflow.internal.ui.processor.biometric.token.BiometricTokenExternalRepository;
import com.onfido.workflow.internal.ui.processor.biometric.token.BiometricTokenInternalRepository;
import com.onfido.workflow.internal.ui.processor.biometric.token.BiometricTokenRepository;
import com.onfido.workflow.internal.utils.WorkflowIntentLauncher;
import com.onfido.workflow.internal.utils.WorkflowIntentLauncherFactory;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WorkflowModule.kt */
@Module
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bá\u0080\u0001\u0018\u0000 \u00062\u00020\u0001:\u0001\u0006J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H'¨\u0006\u0007"}, d2 = {"Lcom/onfido/workflow/internal/di/WorkflowModule;", "", "bindWorkflowIntentLauncherFactory", "Lcom/onfido/workflow/internal/utils/WorkflowIntentLauncher$Factory;", "impl", "Lcom/onfido/workflow/internal/utils/WorkflowIntentLauncherFactory;", "Companion", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public interface WorkflowModule {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @Binds
    WorkflowIntentLauncher.Factory bindWorkflowIntentLauncherFactory(WorkflowIntentLauncherFactory impl);

    /* compiled from: WorkflowModule.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\nH\u0007J \u0010\u000b\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0007J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\nH\u0007¨\u0006\u0015"}, d2 = {"Lcom/onfido/workflow/internal/di/WorkflowModule$Companion;", "", "()V", "provideBiometricTokenApi", "Lcom/onfido/workflow/internal/network/BiometricTokenApi;", "onfidoFetcher", "Lcom/onfido/api/client/OnfidoFetcher;", "provideBiometricTokenCallback", "Landroid/os/ResultReceiver;", "onfidoConfig", "Lcom/onfido/android/sdk/capture/OnfidoConfig;", "provideBiometricTokenRepository", "Lcom/onfido/workflow/internal/ui/processor/biometric/token/BiometricTokenRepository;", "internalRepository", "Lcom/onfido/workflow/internal/ui/processor/biometric/token/BiometricTokenInternalRepository;", "externalRepository", "Lcom/onfido/workflow/internal/ui/processor/biometric/token/BiometricTokenExternalRepository;", "provideWorkflowApi", "Lcom/onfido/workflow/internal/network/WorkflowApi;", "provideWorkflowConfig", "Lcom/onfido/workflow/WorkflowConfig;", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        @Provides
        public final WorkflowApi provideWorkflowApi(OnfidoFetcher onfidoFetcher) {
            Intrinsics.checkNotNullParameter(onfidoFetcher, "onfidoFetcher");
            return (WorkflowApi) onfidoFetcher.api(WorkflowApi.class);
        }

        @Provides
        public final BiometricTokenApi provideBiometricTokenApi(OnfidoFetcher onfidoFetcher) {
            Intrinsics.checkNotNullParameter(onfidoFetcher, "onfidoFetcher");
            return (BiometricTokenApi) onfidoFetcher.api(BiometricTokenApi.class);
        }

        @Provides
        public final WorkflowConfig provideWorkflowConfig(OnfidoConfig onfidoConfig) {
            Intrinsics.checkNotNullParameter(onfidoConfig, "onfidoConfig");
            FlowConfig workflowConfig = onfidoConfig.getWorkflowConfig();
            Intrinsics.checkNotNull(workflowConfig, "null cannot be cast to non-null type com.onfido.workflow.WorkflowConfig");
            return (WorkflowConfig) workflowConfig;
        }

        @Provides
        public final ResultReceiver provideBiometricTokenCallback(OnfidoConfig onfidoConfig) {
            Intrinsics.checkNotNullParameter(onfidoConfig, "onfidoConfig");
            return onfidoConfig.getBiometricTokenCallback();
        }

        @Provides
        public final BiometricTokenRepository provideBiometricTokenRepository(OnfidoConfig onfidoConfig, BiometricTokenInternalRepository internalRepository, BiometricTokenExternalRepository externalRepository) {
            Intrinsics.checkNotNullParameter(onfidoConfig, "onfidoConfig");
            Intrinsics.checkNotNullParameter(internalRepository, "internalRepository");
            Intrinsics.checkNotNullParameter(externalRepository, "externalRepository");
            if (onfidoConfig.getBiometricTokenCallback() != null) {
                return externalRepository;
            }
            return internalRepository;
        }
    }
}
