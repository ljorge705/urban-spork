package com.onfido.android.sdk.capture.ui.faceintro;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.clevertap.android.sdk.product_config.CTProductConfigConstants;
import com.oblador.keychain.KeychainModule;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.common.SdkController;
import com.onfido.android.sdk.capture.ui.BulletStepView;
import com.onfido.android.sdk.capture.ui.BulletedMessageFragment;
import com.onfido.javax.inject.Inject;
import io.sentry.rrweb.RRWebVideoEvent;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\b\u0007\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0005¢\u0006\u0002\u0010\u0002J&\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J\u0015\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\nH\u0010¢\u0006\u0002\b\u0014J\b\u0010\u0015\u001a\u00020\u0012H\u0016J\u001a\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\n2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016R\u001e\u0010\u0003\u001a\u00020\u00048\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u0019"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/faceintro/FaceIntroFragment;", "Lcom/onfido/android/sdk/capture/ui/BulletedMessageFragment;", "()V", "presenter", "Lcom/onfido/android/sdk/capture/ui/faceintro/FaceIntroPresenter;", "getPresenter$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/ui/faceintro/FaceIntroPresenter;", "setPresenter$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/ui/faceintro/FaceIntroPresenter;)V", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", RRWebVideoEvent.JsonKeys.CONTAINER, "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onNextClicked", "", CTProductConfigConstants.PRODUCT_CONFIG_JSON_KEY_FOR_VALUE, "onNextClicked$onfido_capture_sdk_core_release", "onStart", "onViewCreated", "view", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FaceIntroFragment extends BulletedMessageFragment {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @Inject
    public FaceIntroPresenter presenter;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J6\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00062\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u000bH\u0007¨\u0006\f"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/faceintro/FaceIntroFragment$Companion;", "", "()V", "createInstance", "Lcom/onfido/android/sdk/capture/ui/BulletedMessageFragment;", "requestKey", "", "title", KeychainModule.AuthPromptOptions.SUBTITLE, "infoText", "bullets", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        public final BulletedMessageFragment createInstance(String requestKey, String title, String subtitle, String infoText, List<String> bullets) {
            Intrinsics.checkNotNullParameter(requestKey, "requestKey");
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(subtitle, "subtitle");
            Intrinsics.checkNotNullParameter(infoText, "infoText");
            Intrinsics.checkNotNullParameter(bullets, "bullets");
            FaceIntroFragment faceIntroFragment = new FaceIntroFragment();
            BulletedMessageFragment.setInfo$onfido_capture_sdk_core_release$default(faceIntroFragment, requestKey, title, subtitle, bullets, false, false, infoText, 48, null);
            return faceIntroFragment;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @JvmStatic
    public static final BulletedMessageFragment createInstance(String str, String str2, String str3, String str4, List<String> list) {
        return INSTANCE.createInstance(str, str2, str3, str4, list);
    }

    public final FaceIntroPresenter getPresenter$onfido_capture_sdk_core_release() {
        FaceIntroPresenter faceIntroPresenter = this.presenter;
        if (faceIntroPresenter != null) {
            return faceIntroPresenter;
        }
        Intrinsics.throwUninitializedPropertyAccessException("presenter");
        return null;
    }

    @Override // com.onfido.android.sdk.capture.ui.BulletedMessageFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View viewOnCreateView = super.onCreateView(inflater, container, savedInstanceState);
        SdkController companion = SdkController.INSTANCE.getInstance();
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext(...)");
        SdkController.getSdkComponent$default(companion, contextRequireContext, null, 2, null).inject$onfido_capture_sdk_core_release(this);
        return viewOnCreateView;
    }

    @Override // com.onfido.android.sdk.capture.ui.BulletedMessageFragment
    public void onNextClicked$onfido_capture_sdk_core_release(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        getPresenter$onfido_capture_sdk_core_release().onNextClicked$onfido_capture_sdk_core_release();
        super.onNextClicked$onfido_capture_sdk_core_release(v);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        getPresenter$onfido_capture_sdk_core_release().onStart$onfido_capture_sdk_core_release();
        super.onStart();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        View childAt = getBinding().stepsContainer.getChildAt(1);
        Intrinsics.checkNotNull(childAt, "null cannot be cast to non-null type com.onfido.android.sdk.capture.ui.BulletStepView");
        ((BulletStepView) childAt).setIcon(R.drawable.onfido_face_icon);
        View childAt2 = getBinding().stepsContainer.getChildAt(2);
        Intrinsics.checkNotNull(childAt2, "null cannot be cast to non-null type com.onfido.android.sdk.capture.ui.BulletStepView");
        ((BulletStepView) childAt2).setIcon(R.drawable.onfido_glasses_icon);
        getBinding().next.setText(getString(R.string.onfido_selfie_intro_button_primary));
    }

    public final void setPresenter$onfido_capture_sdk_core_release(FaceIntroPresenter faceIntroPresenter) {
        Intrinsics.checkNotNullParameter(faceIntroPresenter, "<set-?>");
        this.presenter = faceIntroPresenter;
    }
}
