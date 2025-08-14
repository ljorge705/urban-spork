package com.onfido.hosted.web.module;

import android.R;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.onfido.android.sdk.capture.common.permissions.PermissionResult;
import com.onfido.android.sdk.capture.common.permissions.PermissionsManagementFragment;
import com.onfido.android.sdk.capture.core.OnfidoLauncher;
import com.onfido.android.sdk.capture.flow.CaptureStepDataBundle;
import com.onfido.android.sdk.capture.flow.FlowStepDirection;
import com.onfido.android.sdk.capture.internal.util.FragmentManagerExtKt;
import com.onfido.android.sdk.capture.ui.CaptureType;
import com.onfido.hosted.web.module.PermissionDelegate;
import io.sentry.protocol.App;
import io.sentry.protocol.Request;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.ws.WebSocketProtocol;

@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0003H\u0002J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J/\u0010\u0016\u001a\u00020\r2%\u0010\u0017\u001a!\u0012\u0013\u0012\u00110\t¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r0\bj\u0002`\u000eH\u0016J\u001b\u0010\u0018\u001a\u00020\r2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00150\u001aH\u0016¢\u0006\u0002\u0010\u001bJ\u001a\u0010\u001c\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00032\b\b\u0002\u0010\u001d\u001a\u00020\u0013H\u0002J\b\u0010\u001e\u001a\u00020\rH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R1\u0010\u0007\u001a%\u0012\u0013\u0012\u00110\t¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r\u0018\u00010\bj\u0004\u0018\u0001`\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/onfido/hosted/web/module/FragmentPermissionDelegate;", "Lcom/onfido/hosted/web/module/PermissionDelegate;", "hostFragment", "Landroidx/fragment/app/Fragment;", "parentFragmentManager", "Landroidx/fragment/app/FragmentManager;", "(Landroidx/fragment/app/Fragment;Landroidx/fragment/app/FragmentManager;)V", "permissionCallback", "Lkotlin/Function1;", "Lcom/onfido/android/sdk/capture/common/permissions/PermissionResult;", "Lkotlin/ParameterName;", "name", OnfidoLauncher.KEY_RESULT, "", "Lcom/onfido/hosted/web/module/PermissionCallback;", "getFragmentReplacementTransaction", "Landroidx/fragment/app/FragmentTransaction;", Request.JsonKeys.FRAGMENT, "hasPermission", "", "permission", "", "registerForResult", "callback", "requestPermission", App.JsonKeys.APP_PERMISSIONS, "", "([Ljava/lang/String;)V", "setFragment", "shouldAddToBackStack", "showPermissionsManagementFragment", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class FragmentPermissionDelegate implements PermissionDelegate {
    private final Fragment hostFragment;
    private final FragmentManager parentFragmentManager;
    private Function1<? super PermissionResult, Unit> permissionCallback;

    public FragmentPermissionDelegate(Fragment hostFragment, FragmentManager parentFragmentManager) {
        Intrinsics.checkNotNullParameter(hostFragment, "hostFragment");
        Intrinsics.checkNotNullParameter(parentFragmentManager, "parentFragmentManager");
        this.hostFragment = hostFragment;
        this.parentFragmentManager = parentFragmentManager;
    }

    private final FragmentTransaction getFragmentReplacementTransaction(Fragment fragment) {
        FragmentTransaction fragmentTransactionBeginTransaction = this.parentFragmentManager.beginTransaction();
        FlowStepDirection flowStepDirection = FlowStepDirection.RIGHT_TO_LEFT;
        Integer slideInAnimation = flowStepDirection.getSlideInAnimation();
        Intrinsics.checkNotNull(slideInAnimation);
        int iIntValue = slideInAnimation.intValue();
        Integer slideOutAnimation = flowStepDirection.getSlideOutAnimation();
        Intrinsics.checkNotNull(slideOutAnimation);
        fragmentTransactionBeginTransaction.setCustomAnimations(iIntValue, slideOutAnimation.intValue(), R.anim.slide_in_left, R.anim.slide_out_right);
        ViewParent parent = this.hostFragment.requireView().getParent();
        Intrinsics.checkNotNull(parent, "null cannot be cast to non-null type android.view.ViewGroup");
        FragmentTransaction fragmentTransactionAdd = fragmentTransactionBeginTransaction.add(((ViewGroup) parent).getId(), fragment, "TAG_PERMISSIONS_MANAGEMENT");
        Intrinsics.checkNotNullExpressionValue(fragmentTransactionAdd, "add(...)");
        return fragmentTransactionAdd;
    }

    private final void setFragment(Fragment fragment, boolean shouldAddToBackStack) {
        FragmentTransaction fragmentReplacementTransaction = getFragmentReplacementTransaction(fragment);
        if (shouldAddToBackStack) {
            fragmentReplacementTransaction.addToBackStack(null);
        }
        fragmentReplacementTransaction.commit();
    }

    static /* synthetic */ void setFragment$default(FragmentPermissionDelegate fragmentPermissionDelegate, Fragment fragment, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        fragmentPermissionDelegate.setFragment(fragment, z);
    }

    private final void showPermissionsManagementFragment() {
        setFragment$default(this, PermissionsManagementFragment.INSTANCE.createInstance("KEY_RESULT_PERMISSIONS_MANAGEMENT", new CaptureStepDataBundle(CaptureType.FACE, null, null, null, null, null, null, WebSocketProtocol.PAYLOAD_SHORT, null)), false, 2, null);
        FragmentManager fragmentManager = this.parentFragmentManager;
        FragmentActivity fragmentActivityRequireActivity = this.hostFragment.requireActivity();
        Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "requireActivity(...)");
        FragmentManagerExtKt.setFragmentResultsListeners(fragmentManager, fragmentActivityRequireActivity, new String[]{"KEY_RESULT_PERMISSIONS_MANAGEMENT"}, new Function2<String, Bundle, Unit>() { // from class: com.onfido.hosted.web.module.FragmentPermissionDelegate.showPermissionsManagementFragment.1
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(String str, Bundle bundle) {
                invoke2(str, bundle);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String requestKey, Bundle bundle) {
                Intrinsics.checkNotNullParameter(requestKey, "requestKey");
                Intrinsics.checkNotNullParameter(bundle, "bundle");
                if (Intrinsics.areEqual(requestKey, "KEY_RESULT_PERMISSIONS_MANAGEMENT")) {
                    FragmentPermissionDelegate.this.parentFragmentManager.popBackStack();
                    PermissionResult result = PermissionsManagementFragment.INSTANCE.getResult(bundle);
                    Function1 function1 = FragmentPermissionDelegate.this.permissionCallback;
                    if (function1 != null) {
                        function1.invoke(result);
                    }
                }
            }
        });
    }

    @Override // com.onfido.hosted.web.module.PermissionDelegate
    public boolean hasPermission(String permission) {
        Intrinsics.checkNotNullParameter(permission, "permission");
        return ContextCompat.checkSelfPermission(this.hostFragment.requireContext(), permission) == 0;
    }

    @Override // com.onfido.hosted.web.module.PermissionDelegate
    public void registerForPermission(String[] strArr, Function1<? super PermissionResult, Unit> function1) {
        PermissionDelegate.DefaultImpls.registerForPermission(this, strArr, function1);
    }

    @Override // com.onfido.hosted.web.module.PermissionDelegate
    public void registerForResult(Function1<? super PermissionResult, Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.permissionCallback = callback;
    }

    @Override // com.onfido.hosted.web.module.PermissionDelegate
    public void requestPermission(String[] permissions) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Fragment fragmentFindFragmentByTag = this.parentFragmentManager.findFragmentByTag("TAG_PERMISSIONS_MANAGEMENT");
        if ((fragmentFindFragmentByTag instanceof PermissionsManagementFragment ? (PermissionsManagementFragment) fragmentFindFragmentByTag : null) == null) {
            showPermissionsManagementFragment();
        }
    }
}
