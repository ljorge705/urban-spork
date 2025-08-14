package com.onfido.android.sdk.capture.common.di;

import com.onfido.android.sdk.capture.document.supported.data.remote.datasource.AllDocumentsDataSource;
import com.onfido.android.sdk.capture.document.supported.data.remote.datasource.AllDocumentsRemoteDataSourceImpl;
import com.onfido.android.sdk.capture.internal.config.DefaultOnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.config.MutableOnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.util.DispatchersProvider;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.dagger.Module;
import com.onfido.dagger.Provides;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Module
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÁ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\r\u0010\u0007\u001a\u00020\bH\u0001¢\u0006\u0002\b\tJ\r\u0010\n\u001a\u00020\u000bH\u0001¢\u0006\u0002\b\fJ\r\u0010\r\u001a\u00020\u000eH\u0001¢\u0006\u0002\b\u000fJ\r\u0010\u0010\u001a\u00020\u0011H\u0001¢\u0006\u0002\b\u0012¨\u0006\u0013"}, d2 = {"Lcom/onfido/android/sdk/capture/common/di/SdkProductionModule;", "", "()V", "provideAllDocumentDataSource", "Lcom/onfido/android/sdk/capture/document/supported/data/remote/datasource/AllDocumentsDataSource;", "allDocumentsRemoteDataSourceImpl", "Lcom/onfido/android/sdk/capture/document/supported/data/remote/datasource/AllDocumentsRemoteDataSourceImpl;", "provideDispatchersProvider", "Lcom/onfido/android/sdk/capture/internal/util/DispatchersProvider;", "provideDispatchersProvider$onfido_capture_sdk_core_release", "provideMutableOnfidoRemoteConfig", "Lcom/onfido/android/sdk/capture/internal/config/MutableOnfidoRemoteConfig;", "provideMutableOnfidoRemoteConfig$onfido_capture_sdk_core_release", "provideOnfidoRemoteConfig", "Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;", "provideOnfidoRemoteConfig$onfido_capture_sdk_core_release", "provideSchedulers", "Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;", "provideSchedulers$onfido_capture_sdk_core_release", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class SdkProductionModule {
    public static final SdkProductionModule INSTANCE = new SdkProductionModule();

    private SdkProductionModule() {
    }

    @Provides
    public final AllDocumentsDataSource provideAllDocumentDataSource(AllDocumentsRemoteDataSourceImpl allDocumentsRemoteDataSourceImpl) {
        Intrinsics.checkNotNullParameter(allDocumentsRemoteDataSourceImpl, "allDocumentsRemoteDataSourceImpl");
        return allDocumentsRemoteDataSourceImpl;
    }

    @Provides
    public final DispatchersProvider provideDispatchersProvider$onfido_capture_sdk_core_release() {
        return DispatchersProvider.INSTANCE.getDefault();
    }

    @Provides
    public final MutableOnfidoRemoteConfig provideMutableOnfidoRemoteConfig$onfido_capture_sdk_core_release() {
        return DefaultOnfidoRemoteConfig.INSTANCE;
    }

    @Provides
    public final OnfidoRemoteConfig provideOnfidoRemoteConfig$onfido_capture_sdk_core_release() {
        return DefaultOnfidoRemoteConfig.INSTANCE;
    }

    @Provides
    public final SchedulersProvider provideSchedulers$onfido_capture_sdk_core_release() {
        return SchedulersProvider.INSTANCE.getDefault();
    }
}
