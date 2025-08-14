package com.onfido.hosted.web.module.externallink;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.onfido.android.sdk.capture.core.OnfidoLauncher;
import com.onfido.javax.inject.Inject;
import java.net.URL;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\tX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0010"}, d2 = {"Lcom/onfido/hosted/web/module/externallink/HostedWebModuleExternalLinkViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/onfido/hosted/web/module/externallink/HostedWebModuleExternalLinkRepository;", "(Lcom/onfido/hosted/web/module/externallink/HostedWebModuleExternalLinkRepository;)V", "_result", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/onfido/hosted/web/module/externallink/HostedWebModuleExternalLinkResult;", OnfidoLauncher.KEY_RESULT, "Lkotlinx/coroutines/flow/StateFlow;", "getResult$onfido_capture_sdk_core_release", "()Lkotlinx/coroutines/flow/StateFlow;", "onExternalLinkReceived", "", "url", "Ljava/net/URL;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class HostedWebModuleExternalLinkViewModel extends ViewModel {
    private final MutableStateFlow<HostedWebModuleExternalLinkResult> _result;
    private final HostedWebModuleExternalLinkRepository repository;
    private final StateFlow<HostedWebModuleExternalLinkResult> result;

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.onfido.hosted.web.module.externallink.HostedWebModuleExternalLinkViewModel$onExternalLinkReceived$1", f = "HostedWebModuleExternalLinkViewModel.kt", i = {}, l = {23}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.onfido.hosted.web.module.externallink.HostedWebModuleExternalLinkViewModel$onExternalLinkReceived$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ URL $url;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(URL url, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$url = url;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return HostedWebModuleExternalLinkViewModel.this.new AnonymousClass1(this.$url, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object objMo5751fetchExternalLinkgIAlus;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    HostedWebModuleExternalLinkRepository hostedWebModuleExternalLinkRepository = HostedWebModuleExternalLinkViewModel.this.repository;
                    URL url = this.$url;
                    this.label = 1;
                    objMo5751fetchExternalLinkgIAlus = hostedWebModuleExternalLinkRepository.mo5751fetchExternalLinkgIAlus(url, this);
                    if (objMo5751fetchExternalLinkgIAlus == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    objMo5751fetchExternalLinkgIAlus = ((Result) obj).getValue();
                }
                MutableStateFlow mutableStateFlow = HostedWebModuleExternalLinkViewModel.this._result;
                ResultKt.throwOnFailure(objMo5751fetchExternalLinkgIAlus);
                mutableStateFlow.setValue(objMo5751fetchExternalLinkgIAlus);
            } catch (Exception unused) {
                HostedWebModuleExternalLinkViewModel.this._result.setValue(Failed.INSTANCE);
            }
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    @Inject
    public HostedWebModuleExternalLinkViewModel(HostedWebModuleExternalLinkRepository repository) {
        Intrinsics.checkNotNullParameter(repository, "repository");
        this.repository = repository;
        MutableStateFlow<HostedWebModuleExternalLinkResult> MutableStateFlow = StateFlowKt.MutableStateFlow(Loading.INSTANCE);
        this._result = MutableStateFlow;
        this.result = FlowKt.asStateFlow(MutableStateFlow);
    }

    public final StateFlow<HostedWebModuleExternalLinkResult> getResult$onfido_capture_sdk_core_release() {
        return this.result;
    }

    public final void onExternalLinkReceived(URL url) {
        Intrinsics.checkNotNullParameter(url, "url");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new AnonymousClass1(url, null), 3, null);
    }
}
