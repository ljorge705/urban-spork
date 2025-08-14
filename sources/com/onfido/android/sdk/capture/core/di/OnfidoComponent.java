package com.onfido.android.sdk.capture.core.di;

import androidx.lifecycle.ViewModel;
import com.onfido.android.sdk.capture.common.di.NavigationModule;
import com.onfido.android.sdk.capture.common.di.SdkComponent;
import com.onfido.android.sdk.capture.common.di.ViewModelScope;
import com.onfido.android.sdk.capture.core.OnfidoFragment;
import com.onfido.android.sdk.capture.core.OnfidoViewModel;
import com.onfido.dagger.Component;
import kotlin.Metadata;

@ViewModelScope
@Component(dependencies = {SdkComponent.class}, modules = {NavigationModule.class})
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b!\u0018\u00002\u00020\u0001:\u0001\rB\u0005¢\u0006\u0002\u0010\u0002J\u0015\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH ¢\u0006\u0002\b\u000bJ\b\u0010\f\u001a\u00020\bH\u0014R\u0012\u0010\u0003\u001a\u00020\u0004X \u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000e"}, d2 = {"Lcom/onfido/android/sdk/capture/core/di/OnfidoComponent;", "Landroidx/lifecycle/ViewModel;", "()V", "onfidoViewModel", "Lcom/onfido/android/sdk/capture/core/OnfidoViewModel;", "getOnfidoViewModel$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/core/OnfidoViewModel;", "inject", "", "onfidoFragment", "Lcom/onfido/android/sdk/capture/core/OnfidoFragment;", "inject$onfido_capture_sdk_core_release", "onCleared", "Factory", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public abstract class OnfidoComponent extends ViewModel {

    @Component.Factory
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/onfido/android/sdk/capture/core/di/OnfidoComponent$Factory;", "", "create", "Lcom/onfido/android/sdk/capture/core/di/OnfidoComponent;", "appComponent", "Lcom/onfido/android/sdk/capture/common/di/SdkComponent;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface Factory {
        OnfidoComponent create(SdkComponent appComponent);
    }

    public abstract OnfidoViewModel getOnfidoViewModel$onfido_capture_sdk_core_release();

    public abstract void inject$onfido_capture_sdk_core_release(OnfidoFragment onfidoFragment);

    @Override // androidx.lifecycle.ViewModel
    protected void onCleared() {
        super.onCleared();
        getOnfidoViewModel$onfido_capture_sdk_core_release().onClear$onfido_capture_sdk_core_release();
    }
}
