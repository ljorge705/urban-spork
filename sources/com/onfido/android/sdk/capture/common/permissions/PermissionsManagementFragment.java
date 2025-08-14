package com.onfido.android.sdk.capture.common.permissions;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.os.BundleKt;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.common.SdkController;
import com.onfido.android.sdk.capture.common.permissions.PermissionResult;
import com.onfido.android.sdk.capture.common.permissions.PermissionsManagementPresenter;
import com.onfido.android.sdk.capture.databinding.OnfidoFragmentEmptyBinding;
import com.onfido.android.sdk.capture.databinding.OnfidoFragmentPermissionsRecoveryBinding;
import com.onfido.android.sdk.capture.databinding.OnfidoFragmentPermissionsRequestBinding;
import com.onfido.android.sdk.capture.flow.CaptureStepDataBundle;
import com.onfido.android.sdk.capture.ui.BulletStepView;
import com.onfido.android.sdk.capture.ui.PageFragment;
import com.onfido.android.sdk.capture.ui.widget.OnfidoButton;
import com.onfido.javax.inject.Inject;
import io.sentry.protocol.SentryStackFrame;
import io.sentry.rrweb.RRWebVideoEvent;
import java.io.Serializable;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0002\b\u0007\u0018\u0000 32\u00020\u00012\u00020\u0002:\u00013B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0016\u001a\u00020\u0017H\u0002J\u0010\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u0010\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\u0010\u0010\u001e\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J&\u0010\u001f\u001a\u0004\u0018\u00010 2\u0006\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010$2\b\u0010%\u001a\u0004\u0018\u00010&H\u0016J\b\u0010'\u001a\u00020\u0017H\u0016J\u0010\u0010(\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\b\u0010)\u001a\u00020\u0017H\u0016J\u001a\u0010*\u001a\u00020\u00172\u0006\u0010+\u001a\u00020 2\b\u0010%\u001a\u0004\u0018\u00010&H\u0016J\b\u0010,\u001a\u00020\u0017H\u0002J\b\u0010-\u001a\u00020\u0017H\u0002J\b\u0010.\u001a\u00020\u0017H\u0016J\u0010\u0010/\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\u0010\u00100\u001a\u00020\u00172\u0006\u00101\u001a\u000202H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\b\u001a\u00020\t8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00140\u0013X\u0082.¢\u0006\u0002\n\u0000¨\u00064"}, d2 = {"Lcom/onfido/android/sdk/capture/common/permissions/PermissionsManagementFragment;", "Lcom/onfido/android/sdk/capture/ui/PageFragment;", "Lcom/onfido/android/sdk/capture/common/permissions/PermissionsManagementPresenter$View;", "()V", "captureDataBundle", "Lcom/onfido/android/sdk/capture/flow/CaptureStepDataBundle;", "emptyBinding", "Lcom/onfido/android/sdk/capture/databinding/OnfidoFragmentEmptyBinding;", "presenter", "Lcom/onfido/android/sdk/capture/common/permissions/PermissionsManagementPresenter;", "getPresenter$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/common/permissions/PermissionsManagementPresenter;", "setPresenter$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/common/permissions/PermissionsManagementPresenter;)V", "recoveryBinding", "Lcom/onfido/android/sdk/capture/databinding/OnfidoFragmentPermissionsRecoveryBinding;", "requestBinding", "Lcom/onfido/android/sdk/capture/databinding/OnfidoFragmentPermissionsRequestBinding;", "requestPermissionLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "", "", "handleBackNavigation", "", "onAttach", "context", "Landroid/content/Context;", "onCameraAndMicrophonePermissionsNeeded", "isRecovery", "", "onCameraPermissionNeeded", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", RRWebVideoEvent.JsonKeys.CONTAINER, "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onMicrophonePermissionNeeded", "onStart", "onViewCreated", "view", "openSettings", "setCanceledResult", "setGrantedResult", "setIsRecovery", "setScreenTitle", "titleResId", "", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class PermissionsManagementFragment extends PageFragment implements PermissionsManagementPresenter.View {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String KEY_CAPTURE_STEP_DATA_BUNDLE = "KEY_CAPTURE_STEP_DATA_BUNDLE";
    private static final String KEY_REQUEST = "KEY_REQUEST";
    private static final String KEY_RESULT = "KEY_RESULT";
    private CaptureStepDataBundle captureDataBundle;
    private OnfidoFragmentEmptyBinding emptyBinding;

    @Inject
    public PermissionsManagementPresenter presenter;
    private OnfidoFragmentPermissionsRecoveryBinding recoveryBinding;
    private OnfidoFragmentPermissionsRequestBinding requestBinding;
    private ActivityResultLauncher<String[]> requestPermissionLauncher;

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0087\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000bH\u0007J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/onfido/android/sdk/capture/common/permissions/PermissionsManagementFragment$Companion;", "", "()V", PermissionsManagementFragment.KEY_CAPTURE_STEP_DATA_BUNDLE, "", PermissionsManagementFragment.KEY_REQUEST, PermissionsManagementFragment.KEY_RESULT, "createInstance", "Lcom/onfido/android/sdk/capture/common/permissions/PermissionsManagementFragment;", "requestKey", "captureDataBundle", "Lcom/onfido/android/sdk/capture/flow/CaptureStepDataBundle;", "getResult", "Lcom/onfido/android/sdk/capture/common/permissions/PermissionResult;", "bundle", "Landroid/os/Bundle;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public final PermissionsManagementFragment createInstance(String requestKey, CaptureStepDataBundle captureDataBundle) {
            Intrinsics.checkNotNullParameter(requestKey, "requestKey");
            Intrinsics.checkNotNullParameter(captureDataBundle, "captureDataBundle");
            PermissionsManagementFragment permissionsManagementFragment = new PermissionsManagementFragment();
            permissionsManagementFragment.setArguments(BundleKt.bundleOf(TuplesKt.to(PermissionsManagementFragment.KEY_CAPTURE_STEP_DATA_BUNDLE, captureDataBundle), TuplesKt.to(PermissionsManagementFragment.KEY_REQUEST, requestKey)));
            return permissionsManagementFragment;
        }

        public final PermissionResult getResult(Bundle bundle) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            Parcelable parcelable = bundle.getParcelable(PermissionsManagementFragment.KEY_RESULT);
            if (parcelable != null) {
                return (PermissionResult) parcelable;
            }
            throw new IllegalArgumentException("Required value was null.".toString());
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private final void handleBackNavigation() {
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback() { // from class: com.onfido.android.sdk.capture.common.permissions.PermissionsManagementFragment.handleBackNavigation.1
            {
                super(true);
            }

            @Override // androidx.activity.OnBackPressedCallback
            public void handleOnBackPressed() {
                PermissionsManagementFragment.this.setCanceledResult();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onAttach$lambda$3(PermissionsManagementFragment this$0, Map map) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        PermissionsManagementPresenter presenter$onfido_capture_sdk_core_release = this$0.getPresenter$onfido_capture_sdk_core_release();
        Intrinsics.checkNotNull(map);
        FragmentActivity fragmentActivityRequireActivity = this$0.requireActivity();
        Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "requireActivity(...)");
        CaptureStepDataBundle captureStepDataBundle = this$0.captureDataBundle;
        if (captureStepDataBundle == null) {
            Intrinsics.throwUninitializedPropertyAccessException("captureDataBundle");
            captureStepDataBundle = null;
        }
        presenter$onfido_capture_sdk_core_release.onPermissionResults(map, fragmentActivityRequireActivity, captureStepDataBundle.getCaptureType());
    }

    private final void openSettings() {
        Context context = getContext();
        if (context != null) {
            Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.fromParts(SentryStackFrame.JsonKeys.PACKAGE, context.getPackageName(), null));
            startActivity(intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setCanceledResult() {
        FragmentManager parentFragmentManager = getParentFragmentManager();
        String string = requireArguments().getString(KEY_REQUEST);
        if (string == null) {
            throw new IllegalArgumentException("Required value was null.".toString());
        }
        parentFragmentManager.setFragmentResult(string, BundleKt.bundleOf(TuplesKt.to(KEY_RESULT, PermissionResult.Canceled.INSTANCE)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setIsRecovery$lambda$6$lambda$4(PermissionsManagementFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        PermissionsManagementPresenter presenter$onfido_capture_sdk_core_release = this$0.getPresenter$onfido_capture_sdk_core_release();
        CaptureStepDataBundle captureStepDataBundle = this$0.captureDataBundle;
        if (captureStepDataBundle == null) {
            Intrinsics.throwUninitializedPropertyAccessException("captureDataBundle");
            captureStepDataBundle = null;
        }
        presenter$onfido_capture_sdk_core_release.onGoToSettingsButtonClicked(captureStepDataBundle.getCaptureType());
        this$0.openSettings();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setIsRecovery$lambda$6$lambda$5(PermissionsManagementFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        PermissionsManagementPresenter presenter$onfido_capture_sdk_core_release = this$0.getPresenter$onfido_capture_sdk_core_release();
        ActivityResultLauncher<String[]> activityResultLauncher = this$0.requestPermissionLauncher;
        if (activityResultLauncher == null) {
            Intrinsics.throwUninitializedPropertyAccessException("requestPermissionLauncher");
            activityResultLauncher = null;
        }
        presenter$onfido_capture_sdk_core_release.onEnableButtonClicked(activityResultLauncher);
    }

    public final PermissionsManagementPresenter getPresenter$onfido_capture_sdk_core_release() {
        PermissionsManagementPresenter permissionsManagementPresenter = this.presenter;
        if (permissionsManagementPresenter != null) {
            return permissionsManagementPresenter;
        }
        Intrinsics.throwUninitializedPropertyAccessException("presenter");
        return null;
    }

    @Override // com.onfido.android.sdk.capture.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.onAttach(context);
        ActivityResultLauncher<String[]> activityResultLauncherRegisterForActivityResult = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), new ActivityResultCallback() { // from class: com.onfido.android.sdk.capture.common.permissions.PermissionsManagementFragment$$ExternalSyntheticLambda2
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                PermissionsManagementFragment.onAttach$lambda$3(this.f$0, (Map) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(activityResultLauncherRegisterForActivityResult, "registerForActivityResult(...)");
        this.requestPermissionLauncher = activityResultLauncherRegisterForActivityResult;
    }

    @Override // com.onfido.android.sdk.capture.common.permissions.PermissionsManagementPresenter.View
    public void onCameraAndMicrophonePermissionsNeeded(boolean isRecovery) {
        if (!isRecovery) {
            OnfidoFragmentPermissionsRequestBinding onfidoFragmentPermissionsRequestBinding = this.requestBinding;
            if (onfidoFragmentPermissionsRequestBinding != null) {
                onfidoFragmentPermissionsRequestBinding.title.setText(getString(R.string.onfido_permission_title_both));
                onfidoFragmentPermissionsRequestBinding.requestSubtitle.setText(getString(R.string.onfido_permission_subtitle_both));
                onfidoFragmentPermissionsRequestBinding.requestDenyExplanation.setText(getString(R.string.onfido_permission_body_both));
                onfidoFragmentPermissionsRequestBinding.enable.setText(getString(R.string.onfido_permission_button_primary_both));
                onfidoFragmentPermissionsRequestBinding.permissionRequestIcon.setImageResource(R.drawable.onfido_permission_camera_and_mic);
                return;
            }
            return;
        }
        OnfidoFragmentPermissionsRecoveryBinding onfidoFragmentPermissionsRecoveryBinding = this.recoveryBinding;
        if (onfidoFragmentPermissionsRecoveryBinding != null) {
            onfidoFragmentPermissionsRecoveryBinding.title.setText(getString(R.string.onfido_permission_recovery_title_both));
            onfidoFragmentPermissionsRecoveryBinding.recoverySubtitle.setText(getString(R.string.onfido_permission_recovery_subtitle_both));
            BulletStepView bulletStepView = onfidoFragmentPermissionsRecoveryBinding.recoveryBulletMessage;
            String string = getString(R.string.onfido_permission_recovery_list_item_how_to_both);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            bulletStepView.setStepInfo(string, R.drawable.onfido_permissions_recovery_icon_camera);
            BulletStepView bulletStepView2 = onfidoFragmentPermissionsRecoveryBinding.recoveryBulletMessage2;
            String string2 = getString(R.string.onfido_permission_recovery_list_item_action_both);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
            bulletStepView2.setStepInfo(string2, R.drawable.onfido_permissions_recovery_icon_cog);
        }
    }

    @Override // com.onfido.android.sdk.capture.common.permissions.PermissionsManagementPresenter.View
    public void onCameraPermissionNeeded(boolean isRecovery) {
        if (!isRecovery) {
            OnfidoFragmentPermissionsRequestBinding onfidoFragmentPermissionsRequestBinding = this.requestBinding;
            if (onfidoFragmentPermissionsRequestBinding != null) {
                onfidoFragmentPermissionsRequestBinding.title.setText(getString(R.string.onfido_permission_title_cam));
                onfidoFragmentPermissionsRequestBinding.requestSubtitle.setText(getString(R.string.onfido_permission_subtitle_cam));
                onfidoFragmentPermissionsRequestBinding.requestDenyExplanation.setText(getString(R.string.onfido_permission_body_cam));
                onfidoFragmentPermissionsRequestBinding.enable.setText(getString(R.string.onfido_permission_button_primary_cam));
                onfidoFragmentPermissionsRequestBinding.permissionRequestIcon.setImageResource(R.drawable.onfido_permission_camera);
                return;
            }
            return;
        }
        OnfidoFragmentPermissionsRecoveryBinding onfidoFragmentPermissionsRecoveryBinding = this.recoveryBinding;
        if (onfidoFragmentPermissionsRecoveryBinding != null) {
            onfidoFragmentPermissionsRecoveryBinding.title.setText(getString(R.string.onfido_permission_recovery_title_cam));
            onfidoFragmentPermissionsRecoveryBinding.recoverySubtitle.setText(getString(R.string.onfido_permission_recovery_subtitle_cam));
            BulletStepView bulletStepView = onfidoFragmentPermissionsRecoveryBinding.recoveryBulletMessage;
            String string = getString(R.string.onfido_permission_recovery_list_item_how_to_cam);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            bulletStepView.setStepInfo(string, R.drawable.onfido_permissions_recovery_icon_camera);
            BulletStepView bulletStepView2 = onfidoFragmentPermissionsRecoveryBinding.recoveryBulletMessage2;
            String string2 = getString(R.string.onfido_permission_recovery_list_item_action_cam);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
            bulletStepView2.setStepInfo(string2, R.drawable.onfido_permissions_recovery_icon_cog);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        Bundle arguments = getArguments();
        if (arguments == null) {
            return null;
        }
        SdkController companion = SdkController.INSTANCE.getInstance();
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext(...)");
        SdkController.getSdkComponent$default(companion, contextRequireContext, null, 2, null).inject$onfido_capture_sdk_core_release(this);
        getPresenter$onfido_capture_sdk_core_release().attachView$onfido_capture_sdk_core_release(this);
        Serializable serializable = arguments.getSerializable(KEY_CAPTURE_STEP_DATA_BUNDLE);
        Intrinsics.checkNotNull(serializable, "null cannot be cast to non-null type com.onfido.android.sdk.capture.flow.CaptureStepDataBundle");
        this.captureDataBundle = (CaptureStepDataBundle) serializable;
        OnfidoFragmentEmptyBinding onfidoFragmentEmptyBindingInflate = OnfidoFragmentEmptyBinding.inflate(inflater, container, false);
        this.emptyBinding = onfidoFragmentEmptyBindingInflate;
        return onfidoFragmentEmptyBindingInflate.root;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.emptyBinding = null;
        this.recoveryBinding = null;
        this.requestBinding = null;
    }

    @Override // com.onfido.android.sdk.capture.common.permissions.PermissionsManagementPresenter.View
    public void onMicrophonePermissionNeeded(boolean isRecovery) {
        if (!isRecovery) {
            OnfidoFragmentPermissionsRequestBinding onfidoFragmentPermissionsRequestBinding = this.requestBinding;
            if (onfidoFragmentPermissionsRequestBinding != null) {
                onfidoFragmentPermissionsRequestBinding.title.setText(getString(R.string.onfido_permission_title_mic));
                onfidoFragmentPermissionsRequestBinding.requestSubtitle.setText(getString(R.string.onfido_permission_subtitle_mic));
                onfidoFragmentPermissionsRequestBinding.requestDenyExplanation.setText(getString(R.string.onfido_permission_body_mic));
                onfidoFragmentPermissionsRequestBinding.enable.setText(getString(R.string.onfido_permission_button_primary_mic));
                onfidoFragmentPermissionsRequestBinding.permissionRequestIcon.setImageResource(R.drawable.onfido_permission_mic);
                return;
            }
            return;
        }
        OnfidoFragmentPermissionsRecoveryBinding onfidoFragmentPermissionsRecoveryBinding = this.recoveryBinding;
        if (onfidoFragmentPermissionsRecoveryBinding != null) {
            onfidoFragmentPermissionsRecoveryBinding.title.setText(getString(R.string.onfido_permission_recovery_title_mic));
            onfidoFragmentPermissionsRecoveryBinding.recoverySubtitle.setText(getString(R.string.onfido_permission_recovery_subtitle_mic));
            BulletStepView bulletStepView = onfidoFragmentPermissionsRecoveryBinding.recoveryBulletMessage;
            String string = getString(R.string.onfido_permission_recovery_list_item_how_to_mic);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            bulletStepView.setStepInfo(string, R.drawable.onfido_permissions_recovery_icon_mic);
            BulletStepView bulletStepView2 = onfidoFragmentPermissionsRecoveryBinding.recoveryBulletMessage2;
            String string2 = getString(R.string.onfido_permission_recovery_list_item_action_mic);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
            bulletStepView2.setStepInfo(string2, R.drawable.onfido_permissions_recovery_icon_cog);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        FragmentActivity activity = getActivity();
        if (activity != null) {
            PermissionsManagementPresenter presenter$onfido_capture_sdk_core_release = getPresenter$onfido_capture_sdk_core_release();
            CaptureStepDataBundle captureStepDataBundle = this.captureDataBundle;
            if (captureStepDataBundle == null) {
                Intrinsics.throwUninitializedPropertyAccessException("captureDataBundle");
                captureStepDataBundle = null;
            }
            presenter$onfido_capture_sdk_core_release.checkPermissions(activity, captureStepDataBundle.getCaptureType(), MapsKt.emptyMap());
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        handleBackNavigation();
    }

    @Override // com.onfido.android.sdk.capture.common.permissions.PermissionsManagementPresenter.View
    public void setGrantedResult() {
        FragmentManager parentFragmentManager = getParentFragmentManager();
        String string = requireArguments().getString(KEY_REQUEST);
        if (string == null) {
            throw new IllegalArgumentException("Required value was null.".toString());
        }
        CaptureStepDataBundle captureStepDataBundle = this.captureDataBundle;
        if (captureStepDataBundle == null) {
            Intrinsics.throwUninitializedPropertyAccessException("captureDataBundle");
            captureStepDataBundle = null;
        }
        parentFragmentManager.setFragmentResult(string, BundleKt.bundleOf(TuplesKt.to(KEY_RESULT, new PermissionResult.Granted(captureStepDataBundle))));
    }

    @Override // com.onfido.android.sdk.capture.common.permissions.PermissionsManagementPresenter.View
    public void setIsRecovery(boolean isRecovery) {
        OnfidoButton onfidoButton;
        View.OnClickListener onClickListener;
        FrameLayout frameLayout;
        if (getView() != null) {
            OnfidoFragmentEmptyBinding onfidoFragmentEmptyBinding = this.emptyBinding;
            if (onfidoFragmentEmptyBinding != null && (frameLayout = onfidoFragmentEmptyBinding.root) != null) {
                frameLayout.removeAllViews();
            }
            if (isRecovery) {
                LayoutInflater layoutInflater = getLayoutInflater();
                OnfidoFragmentEmptyBinding onfidoFragmentEmptyBinding2 = this.emptyBinding;
                OnfidoFragmentPermissionsRecoveryBinding onfidoFragmentPermissionsRecoveryBindingInflate = OnfidoFragmentPermissionsRecoveryBinding.inflate(layoutInflater, onfidoFragmentEmptyBinding2 != null ? onfidoFragmentEmptyBinding2.root : null, true);
                this.recoveryBinding = onfidoFragmentPermissionsRecoveryBindingInflate;
                if (onfidoFragmentPermissionsRecoveryBindingInflate == null || (onfidoButton = onfidoFragmentPermissionsRecoveryBindingInflate.settingsButton) == null) {
                    return;
                } else {
                    onClickListener = new View.OnClickListener() { // from class: com.onfido.android.sdk.capture.common.permissions.PermissionsManagementFragment$$ExternalSyntheticLambda0
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            PermissionsManagementFragment.setIsRecovery$lambda$6$lambda$4(this.f$0, view);
                        }
                    };
                }
            } else {
                LayoutInflater layoutInflater2 = getLayoutInflater();
                OnfidoFragmentEmptyBinding onfidoFragmentEmptyBinding3 = this.emptyBinding;
                OnfidoFragmentPermissionsRequestBinding onfidoFragmentPermissionsRequestBindingInflate = OnfidoFragmentPermissionsRequestBinding.inflate(layoutInflater2, onfidoFragmentEmptyBinding3 != null ? onfidoFragmentEmptyBinding3.root : null, true);
                this.requestBinding = onfidoFragmentPermissionsRequestBindingInflate;
                if (onfidoFragmentPermissionsRequestBindingInflate == null || (onfidoButton = onfidoFragmentPermissionsRequestBindingInflate.enable) == null) {
                    return;
                } else {
                    onClickListener = new View.OnClickListener() { // from class: com.onfido.android.sdk.capture.common.permissions.PermissionsManagementFragment$$ExternalSyntheticLambda1
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            PermissionsManagementFragment.setIsRecovery$lambda$6$lambda$5(this.f$0, view);
                        }
                    };
                }
            }
            onfidoButton.setOnClickListener(onClickListener);
        }
    }

    public final void setPresenter$onfido_capture_sdk_core_release(PermissionsManagementPresenter permissionsManagementPresenter) {
        Intrinsics.checkNotNullParameter(permissionsManagementPresenter, "<set-?>");
        this.presenter = permissionsManagementPresenter;
    }

    @Override // com.onfido.android.sdk.capture.common.permissions.PermissionsManagementPresenter.View
    public void setScreenTitle(int titleResId) {
        requireActivity().setTitle(titleResId);
    }
}
