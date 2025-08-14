package com.onfido.android.sdk.capture.component.active.video.capture.di.host;

import com.onfido.android.sdk.capture.internal.navigation.Navigator;
import com.onfido.android.sdk.capture.internal.navigation.OnfidoNavigation;
import com.onfido.android.sdk.capture.internal.navigation.navigator.NavigationManagerHolder;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.dagger.Module;
import com.onfido.dagger.Provides;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Module
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÁ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0007¨\u0006\f"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/di/host/NavigationModule;", "", "()V", "provideNavigationManagerHolder", "Lcom/onfido/android/sdk/capture/internal/navigation/navigator/NavigationManagerHolder;", "onfidoNavigation", "Lcom/onfido/android/sdk/capture/internal/navigation/OnfidoNavigation;", "provideNavigator", "Lcom/onfido/android/sdk/capture/internal/navigation/Navigator;", "provideOnfidoNavigation", "schedulersProvider", "Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class NavigationModule {
    public static final NavigationModule INSTANCE = new NavigationModule();

    private NavigationModule() {
    }

    @Provides
    public final NavigationManagerHolder provideNavigationManagerHolder(OnfidoNavigation onfidoNavigation) {
        Intrinsics.checkNotNullParameter(onfidoNavigation, "onfidoNavigation");
        return onfidoNavigation.getNavigationManagerHolder();
    }

    @Provides
    public final Navigator provideNavigator(OnfidoNavigation onfidoNavigation) {
        Intrinsics.checkNotNullParameter(onfidoNavigation, "onfidoNavigation");
        return onfidoNavigation.getNavigator();
    }

    @FeatureScope
    @Provides
    public final OnfidoNavigation provideOnfidoNavigation(SchedulersProvider schedulersProvider) {
        Intrinsics.checkNotNullParameter(schedulersProvider, "schedulersProvider");
        return new OnfidoNavigation(schedulersProvider);
    }
}
