package com.onfido.android.sdk.capture.internal.ui.countryselection;

import android.telephony.TelephonyManager;
import com.onfido.javax.inject.Inject;
import io.reactivex.rxjava3.core.Single;
import io.sentry.protocol.OperatingSystem;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\b\u0010\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0092\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/ui/countryselection/GetCurrentCountryCodeUseCase;", "", "telephonyManager", "Landroid/telephony/TelephonyManager;", "(Landroid/telephony/TelephonyManager;)V", OperatingSystem.JsonKeys.BUILD, "Lio/reactivex/rxjava3/core/Single;", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public class GetCurrentCountryCodeUseCase {
    private final TelephonyManager telephonyManager;

    @Inject
    public GetCurrentCountryCodeUseCase(TelephonyManager telephonyManager) {
        Intrinsics.checkNotNullParameter(telephonyManager, "telephonyManager");
        this.telephonyManager = telephonyManager;
    }

    public Single<String> build() {
        Single<String> singleJust = Single.just(this.telephonyManager.getSimCountryIso());
        Intrinsics.checkNotNullExpressionValue(singleJust, "just(...)");
        return singleJust;
    }
}
