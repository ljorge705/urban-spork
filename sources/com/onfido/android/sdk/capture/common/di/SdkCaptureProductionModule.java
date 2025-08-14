package com.onfido.android.sdk.capture.common.di;

import android.content.Context;
import android.nfc.NfcAdapter;
import com.onfido.android.sdk.capture.internal.nfc.NfcInteractor;
import com.onfido.android.sdk.capture.internal.nfc.NfcInteractorImpl;
import com.onfido.dagger.Binds;
import com.onfido.dagger.Module;
import com.onfido.dagger.Provides;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Module
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b'\u0018\u0000 \b2\u00020\u0001:\u0001\bB\u0005¢\u0006\u0002\u0010\u0002J\u0015\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H!¢\u0006\u0002\b\u0007¨\u0006\t"}, d2 = {"Lcom/onfido/android/sdk/capture/common/di/SdkCaptureProductionModule;", "", "()V", "provideNfcInteractor", "Lcom/onfido/android/sdk/capture/internal/nfc/NfcInteractor;", "nfcInteractorImpl", "Lcom/onfido/android/sdk/capture/internal/nfc/NfcInteractorImpl;", "provideNfcInteractor$onfido_capture_sdk_core_release", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public abstract class SdkCaptureProductionModule {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/onfido/android/sdk/capture/common/di/SdkCaptureProductionModule$Companion;", "", "()V", "provideNfcAdapter", "Landroid/nfc/NfcAdapter;", "context", "Landroid/content/Context;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        @Provides
        public final NfcAdapter provideNfcAdapter(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return NfcAdapter.getDefaultAdapter(context);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Binds
    public abstract NfcInteractor provideNfcInteractor$onfido_capture_sdk_core_release(NfcInteractorImpl nfcInteractorImpl);
}
