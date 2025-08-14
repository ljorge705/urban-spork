package com.onfido.android.sdk.capture.ui.camera.liveness;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.microsoft.codepush.react.CodePushConstants;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.common.SdkController;
import com.onfido.android.sdk.capture.databinding.OnfidoFragmentLivenessIntroBinding;
import com.onfido.android.sdk.capture.ui.BaseFragment;
import com.onfido.android.sdk.capture.ui.camera.liveness.LivenessIntroPresenter;
import com.onfido.android.sdk.capture.ui.camera.liveness.intro.error.LivenessIntroVideoErrorType;
import com.onfido.android.sdk.capture.ui.camera.view.AutoPlayVideoView;
import com.onfido.android.sdk.capture.ui.camera.view.PlaybackControlsVideoPlayerView;
import com.onfido.android.sdk.capture.ui.widget.BulletView;
import com.onfido.android.sdk.capture.utils.BundleArgumentDelegateKt;
import com.onfido.android.sdk.capture.utils.ContextUtilsKt;
import com.onfido.android.sdk.capture.utils.ViewExtensionsKt;
import com.onfido.javax.inject.Inject;
import io.sentry.rrweb.RRWebVideoEvent;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference2Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;

@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\b\u0007\u0018\u0000 *2\u00020\u00012\u00020\u00022\u00020\u0003:\u0001*B\u0005¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J$\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\b\u0010\u001c\u001a\u00020\u0013H\u0016J\u0010\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\u0010\u0010 \u001a\u00020\u00132\u0006\u0010!\u001a\u00020\"H\u0016J\b\u0010#\u001a\u00020\u0013H\u0016J\b\u0010$\u001a\u00020\u0013H\u0016J\b\u0010%\u001a\u00020\u0013H\u0016J\u001a\u0010&\u001a\u00020\u00132\u0006\u0010'\u001a\u00020\u00172\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\u0010\u0010(\u001a\u00020\u00132\u0006\u0010)\u001a\u00020\u0011H\u0016R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\u00068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u001e\u0010\n\u001a\u00020\u000b8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/liveness/LivenessIntroFragment;", "Lcom/onfido/android/sdk/capture/ui/BaseFragment;", "Lcom/onfido/android/sdk/capture/ui/camera/view/AutoPlayVideoView$AutoPlayVideoViewListener;", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/LivenessIntroPresenter$View;", "()V", "_binding", "Lcom/onfido/android/sdk/capture/databinding/OnfidoFragmentLivenessIntroBinding;", "binding", "getBinding", "()Lcom/onfido/android/sdk/capture/databinding/OnfidoFragmentLivenessIntroBinding;", "presenter", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/LivenessIntroPresenter;", "getPresenter$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/ui/camera/liveness/LivenessIntroPresenter;", "setPresenter$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/ui/camera/liveness/LivenessIntroPresenter;)V", "shouldShowIntroVideo", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", RRWebVideoEvent.JsonKeys.CONTAINER, "Landroid/view/ViewGroup;", "onDestroyView", "onErrorFetchingLivenessIntroVideo", "type", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/intro/error/LivenessIntroVideoErrorType;", "onLivenessIntroVideoAvailable", "filePath", "", "onReloadPressed", "onStart", "onStop", "onViewCreated", "view", "setVideoIntroLoading", CodePushConstants.PENDING_UPDATE_IS_LOADING_KEY, "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class LivenessIntroFragment extends BaseFragment implements AutoPlayVideoView.AutoPlayVideoViewListener, LivenessIntroPresenter.View {
    private OnfidoFragmentLivenessIntroBinding _binding;

    @Inject
    public LivenessIntroPresenter presenter;
    private boolean shouldShowIntroVideo = true;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final ReadWriteProperty<? super Bundle, Boolean> showIntro$delegate = BundleArgumentDelegateKt.bundleArgument(Boolean.FALSE);
    private static final ReadWriteProperty<? super Bundle, String> requestKey$delegate = BundleArgumentDelegateKt.bundleArgumentNullable();

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\rH\u0007R5\u0010\u0005\u001a\u0004\u0018\u00010\u0004*\u0004\u0018\u00010\u00062\b\u0010\u0003\u001a\u0004\u0018\u00010\u00048B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u000b\u0010\f\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR1\u0010\u000e\u001a\u00020\r*\u0004\u0018\u00010\u00062\u0006\u0010\u0003\u001a\u00020\r8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0013\u0010\f\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006\u0016"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/liveness/LivenessIntroFragment$Companion;", "", "()V", "<set-?>", "", "requestKey", "Landroid/os/Bundle;", "getRequestKey", "(Landroid/os/Bundle;)Ljava/lang/String;", "setRequestKey", "(Landroid/os/Bundle;Ljava/lang/String;)V", "requestKey$delegate", "Lkotlin/properties/ReadWriteProperty;", "", "showIntro", "getShowIntro", "(Landroid/os/Bundle;)Z", "setShowIntro", "(Landroid/os/Bundle;Z)V", "showIntro$delegate", "createInstance", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/LivenessIntroFragment;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.mutableProperty2(new MutablePropertyReference2Impl(Companion.class, "showIntro", "getShowIntro(Landroid/os/Bundle;)Z", 0)), Reflection.mutableProperty2(new MutablePropertyReference2Impl(Companion.class, "requestKey", "getRequestKey(Landroid/os/Bundle;)Ljava/lang/String;", 0))};

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final String getRequestKey(Bundle bundle) {
            return (String) LivenessIntroFragment.requestKey$delegate.getValue(bundle, $$delegatedProperties[1]);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean getShowIntro(Bundle bundle) {
            return ((Boolean) LivenessIntroFragment.showIntro$delegate.getValue(bundle, $$delegatedProperties[0])).booleanValue();
        }

        private final void setRequestKey(Bundle bundle, String str) {
            LivenessIntroFragment.requestKey$delegate.setValue(bundle, $$delegatedProperties[1], str);
        }

        private final void setShowIntro(Bundle bundle, boolean z) {
            LivenessIntroFragment.showIntro$delegate.setValue(bundle, $$delegatedProperties[0], Boolean.valueOf(z));
        }

        @JvmStatic
        public final LivenessIntroFragment createInstance(String requestKey, boolean showIntro) {
            Intrinsics.checkNotNullParameter(requestKey, "requestKey");
            Bundle bundle = new Bundle();
            Companion companion = LivenessIntroFragment.INSTANCE;
            companion.setRequestKey(bundle, requestKey);
            companion.setShowIntro(bundle, showIntro);
            LivenessIntroFragment livenessIntroFragment = new LivenessIntroFragment();
            livenessIntroFragment.setArguments(bundle);
            return livenessIntroFragment;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LivenessIntroVideoErrorType.values().length];
            try {
                iArr[LivenessIntroVideoErrorType.TIMEOUT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @JvmStatic
    public static final LivenessIntroFragment createInstance(String str, boolean z) {
        return INSTANCE.createInstance(str, z);
    }

    private final OnfidoFragmentLivenessIntroBinding getBinding() {
        OnfidoFragmentLivenessIntroBinding onfidoFragmentLivenessIntroBinding = this._binding;
        Intrinsics.checkNotNull(onfidoFragmentLivenessIntroBinding);
        return onfidoFragmentLivenessIntroBinding;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreateView$lambda$5$lambda$4(LivenessIntroFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getPresenter$onfido_capture_sdk_core_release().onNextClicked();
        String requestKey = INSTANCE.getRequestKey(this$0.getArguments());
        if (requestKey == null) {
            throw new IllegalArgumentException("KEY_REQUEST must not be null".toString());
        }
        this$0.getParentFragmentManager().setFragmentResult(requestKey, Bundle.EMPTY);
    }

    public final LivenessIntroPresenter getPresenter$onfido_capture_sdk_core_release() {
        LivenessIntroPresenter livenessIntroPresenter = this.presenter;
        if (livenessIntroPresenter != null) {
            return livenessIntroPresenter;
        }
        Intrinsics.throwUninitializedPropertyAccessException("presenter");
        return null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.shouldShowIntroVideo = INSTANCE.getShowIntro(getArguments());
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this._binding = OnfidoFragmentLivenessIntroBinding.inflate(inflater, container, false);
        OnfidoFragmentLivenessIntroBinding binding = getBinding();
        SdkController companion = SdkController.INSTANCE.getInstance();
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext(...)");
        SdkController.getSdkComponent$default(companion, contextRequireContext, null, 2, null).inject$onfido_capture_sdk_core_release(this);
        getPresenter$onfido_capture_sdk_core_release().onCreateView(this);
        binding.title.setText(getString(R.string.onfido_video_intro_title));
        binding.subtitle.setText(getString(R.string.onfido_video_intro_subtitle));
        LinearLayout linearLayout = binding.stepsContainer;
        Context context = getBinding().getRoot().getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        BulletView bulletView = new BulletView(context);
        String string = bulletView.getContext().getString(R.string.onfido_video_intro_list_item_time_limit_copy, 25);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        bulletView.setStepTitle(string);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        Context context2 = bulletView.getContext();
        Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
        layoutParams.bottomMargin = ContextUtilsKt.dimen(context2, R.dimen.onfido_liveness_intro_video_bullet_margin_bottom);
        bulletView.setLayoutParams(layoutParams);
        linearLayout.addView(bulletView);
        LinearLayout linearLayout2 = binding.stepsContainer;
        Context context3 = getBinding().getRoot().getContext();
        Intrinsics.checkNotNullExpressionValue(context3, "getContext(...)");
        BulletView bulletView2 = new BulletView(context3);
        String string2 = bulletView2.getContext().getString(R.string.onfido_video_intro_list_item_move_speak);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        bulletView2.setStepTitle(string2);
        linearLayout2.addView(bulletView2);
        binding.next.setOnClickListener(new View.OnClickListener() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.LivenessIntroFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LivenessIntroFragment.onCreateView$lambda$5$lambda$4(this.f$0, view);
            }
        });
        RelativeLayout root = getBinding().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        getBinding().videoView.release();
        this._binding = null;
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.liveness.LivenessIntroPresenter.View
    public void onErrorFetchingLivenessIntroVideo(LivenessIntroVideoErrorType type) {
        Intrinsics.checkNotNullParameter(type, "type");
        if (type != LivenessIntroVideoErrorType.NO_VIDEOS_FOUND) {
            getBinding().videoView.setError(WhenMappings.$EnumSwitchMapping$0[type.ordinal()] == 1 ? R.string.onfido_video_intro_error_network : R.string.onfido_video_intro_error_load_offline);
            return;
        }
        PlaybackControlsVideoPlayerView videoView = getBinding().videoView;
        Intrinsics.checkNotNullExpressionValue(videoView, "videoView");
        ViewExtensionsKt.toGone$default(videoView, false, 1, null);
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.liveness.LivenessIntroPresenter.View
    public void onLivenessIntroVideoAvailable(String filePath) throws IllegalStateException, IOException, SecurityException, IllegalArgumentException {
        Intrinsics.checkNotNullParameter(filePath, "filePath");
        getBinding().videoView.setVideoUrl(filePath);
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.view.AutoPlayVideoView.AutoPlayVideoViewListener
    public void onReloadPressed() {
        getPresenter$onfido_capture_sdk_core_release().onReloadPressed();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() throws IllegalStateException {
        super.onStart();
        if (this.shouldShowIntroVideo) {
            if (getBinding().videoView.hasVideo()) {
                getBinding().videoView.resume();
            } else {
                getPresenter$onfido_capture_sdk_core_release().onStart();
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() throws IllegalStateException {
        super.onStop();
        getPresenter$onfido_capture_sdk_core_release().onStop();
        if (this.shouldShowIntroVideo) {
            getBinding().videoView.pause();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        getBinding().next.setText(getString(R.string.onfido_video_intro_button_primary));
        getBinding().videoView.setListener(this);
        if (this.shouldShowIntroVideo) {
            return;
        }
        PlaybackControlsVideoPlayerView videoView = getBinding().videoView;
        Intrinsics.checkNotNullExpressionValue(videoView, "videoView");
        ViewExtensionsKt.toGone$default(videoView, false, 1, null);
    }

    public final void setPresenter$onfido_capture_sdk_core_release(LivenessIntroPresenter livenessIntroPresenter) {
        Intrinsics.checkNotNullParameter(livenessIntroPresenter, "<set-?>");
        this.presenter = livenessIntroPresenter;
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.liveness.LivenessIntroPresenter.View
    public void setVideoIntroLoading(boolean isLoading) {
        getBinding().videoView.setLoading(isLoading);
    }
}
