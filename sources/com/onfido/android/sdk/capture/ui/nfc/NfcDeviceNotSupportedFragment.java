package com.onfido.android.sdk.capture.ui.nfc;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import androidx.core.os.BundleKt;
import androidx.fragment.app.Fragment;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.common.SdkController;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.NfcTracker;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.ScreenTracker;
import com.onfido.javax.inject.Inject;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u001a\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016R\u001e\u0010\u0003\u001a\u00020\u00048\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001e\u0010\t\u001a\u00020\n8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0017"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/nfc/NfcDeviceNotSupportedFragment;", "Landroidx/fragment/app/Fragment;", "()V", "nfcTracker", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/NfcTracker;", "getNfcTracker$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/NfcTracker;", "setNfcTracker$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/NfcTracker;)V", "screenTracker", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/ScreenTracker;", "getScreenTracker$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/ScreenTracker;", "setScreenTracker$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/ScreenTracker;)V", "onStart", "", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class NfcDeviceNotSupportedFragment extends Fragment {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String REQUEST_KEY = "REQUEST_KEY";

    @Inject
    public NfcTracker nfcTracker;

    @Inject
    public ScreenTracker screenTracker;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/nfc/NfcDeviceNotSupportedFragment$Companion;", "", "()V", NfcDeviceNotSupportedFragment.REQUEST_KEY, "", "createInstance", "Lcom/onfido/android/sdk/capture/ui/nfc/NfcDeviceNotSupportedFragment;", "requestKey", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        public final NfcDeviceNotSupportedFragment createInstance(String requestKey) {
            Intrinsics.checkNotNullParameter(requestKey, "requestKey");
            NfcDeviceNotSupportedFragment nfcDeviceNotSupportedFragment = new NfcDeviceNotSupportedFragment();
            nfcDeviceNotSupportedFragment.setArguments(BundleKt.bundleOf(TuplesKt.to(NfcDeviceNotSupportedFragment.REQUEST_KEY, requestKey)));
            return nfcDeviceNotSupportedFragment;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public NfcDeviceNotSupportedFragment() {
        super(R.layout.onfido_fragment_nfc_device_not_supported);
    }

    @JvmStatic
    public static final NfcDeviceNotSupportedFragment createInstance(String str) {
        return INSTANCE.createInstance(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$0(NfcDeviceNotSupportedFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getNfcTracker$onfido_capture_sdk_core_release().trackNfcExitVerificationClicked$onfido_capture_sdk_core_release();
        String string = this$0.requireArguments().getString(REQUEST_KEY);
        if (string == null) {
            throw new IllegalArgumentException("Required value was null.".toString());
        }
        Intrinsics.checkNotNullExpressionValue(string, "requireNotNull(...)");
        this$0.getParentFragmentManager().setFragmentResult(string, Bundle.EMPTY);
    }

    public final NfcTracker getNfcTracker$onfido_capture_sdk_core_release() {
        NfcTracker nfcTracker = this.nfcTracker;
        if (nfcTracker != null) {
            return nfcTracker;
        }
        Intrinsics.throwUninitializedPropertyAccessException("nfcTracker");
        return null;
    }

    public final ScreenTracker getScreenTracker$onfido_capture_sdk_core_release() {
        ScreenTracker screenTracker = this.screenTracker;
        if (screenTracker != null) {
            return screenTracker;
        }
        Intrinsics.throwUninitializedPropertyAccessException("screenTracker");
        return null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        getScreenTracker$onfido_capture_sdk_core_release().trackNfcDeviceNotSupported$onfido_capture_sdk_core_release();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        SdkController companion = SdkController.INSTANCE.getInstance();
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext(...)");
        SdkController.getSdkComponent$default(companion, contextRequireContext, null, 2, null).inject$onfido_capture_sdk_core_release(this);
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.btnExit).setOnClickListener(new View.OnClickListener() { // from class: com.onfido.android.sdk.capture.ui.nfc.NfcDeviceNotSupportedFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                NfcDeviceNotSupportedFragment.onViewCreated$lambda$0(this.f$0, view2);
            }
        });
    }

    public final void setNfcTracker$onfido_capture_sdk_core_release(NfcTracker nfcTracker) {
        Intrinsics.checkNotNullParameter(nfcTracker, "<set-?>");
        this.nfcTracker = nfcTracker;
    }

    public final void setScreenTracker$onfido_capture_sdk_core_release(ScreenTracker screenTracker) {
        Intrinsics.checkNotNullParameter(screenTracker, "<set-?>");
        this.screenTracker = screenTracker;
    }
}
