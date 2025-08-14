package com.onfido.android.sdk.capture.ui;

import android.content.Context;
import android.view.MenuItem;
import androidx.core.app.NavUtils;
import androidx.core.view.KeyEventDispatcher;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.clevertap.android.sdk.leanplum.Constants;
import com.onfido.android.sdk.capture.internal.util.logging.Timber;
import com.onfido.android.sdk.capture.utils.LoadingFragment;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u0007\b\u0010¢\u0006\u0002\u0010\u0002B\u0011\b\u0010\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\r\u0010\f\u001a\u00020\rH\u0000¢\u0006\u0002\b\u000eJ\u0010\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\rH\u0016J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0017J\b\u0010\u0017\u001a\u00020\rH\u0016J\u0015\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\u001aH\u0000¢\u0006\u0002\b\u001bR\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b¨\u0006\u001c"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/BaseFragment;", "Landroidx/fragment/app/Fragment;", "()V", "layoutRes", "", "(I)V", "nextActionListener", "Lcom/onfido/android/sdk/capture/ui/NextActionListener;", "getNextActionListener$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/ui/NextActionListener;", "setNextActionListener$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/ui/NextActionListener;)V", "dismissLoadingDialog", "", "dismissLoadingDialog$onfido_capture_sdk_core_release", "onAttach", "context", "Landroid/content/Context;", "onDetach", "onOptionsItemSelected", "", Constants.IAP_ITEM_PARAM, "Landroid/view/MenuItem;", "onPause", "showLoadingDialog", "dialogMode", "Lcom/onfido/android/sdk/capture/utils/LoadingFragment$Companion$DialogMode;", "showLoadingDialog$onfido_capture_sdk_core_release", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public abstract class BaseFragment extends Fragment {
    private NextActionListener nextActionListener;

    public BaseFragment() {
    }

    public final void dismissLoadingDialog$onfido_capture_sdk_core_release() {
        FragmentActivity activity = getActivity();
        if (activity instanceof BaseActivity) {
            ((BaseActivity) activity).dismissLoadingDialog$onfido_capture_sdk_core_release();
            return;
        }
        Fragment fragmentFindFragmentByTag = getChildFragmentManager().findFragmentByTag(LoadingFragment.INSTANCE.getTAG());
        DialogFragment dialogFragment = fragmentFindFragmentByTag instanceof DialogFragment ? (DialogFragment) fragmentFindFragmentByTag : null;
        if (dialogFragment != null) {
            dialogFragment.dismissAllowingStateLoss();
        }
    }

    /* renamed from: getNextActionListener$onfido_capture_sdk_core_release, reason: from getter */
    public final NextActionListener getNextActionListener() {
        return this.nextActionListener;
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.onAttach(context);
        try {
            KeyEventDispatcher.Component activity = getActivity();
            this.nextActionListener = activity instanceof NextActionListener ? (NextActionListener) activity : null;
        } catch (ClassCastException e) {
            Timber.INSTANCE.e(e, "Activity " + requireActivity().getClass().getSimpleName() + " did't implement NextActionListener interface.", new Object[0]);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        dismissLoadingDialog$onfido_capture_sdk_core_release();
        this.nextActionListener = null;
        super.onDetach();
    }

    @Override // androidx.fragment.app.Fragment
    @Deprecated(message = "Overrides a deprecated method, compilation error with Kotlin 1.9")
    public boolean onOptionsItemSelected(MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (item.getItemId() != 16908332) {
            return super.onOptionsItemSelected(item);
        }
        NavUtils.navigateUpFromSameTask(requireActivity());
        return true;
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        dismissLoadingDialog$onfido_capture_sdk_core_release();
        super.onPause();
    }

    public final void setNextActionListener$onfido_capture_sdk_core_release(NextActionListener nextActionListener) {
        this.nextActionListener = nextActionListener;
    }

    public final void showLoadingDialog$onfido_capture_sdk_core_release(LoadingFragment.Companion.DialogMode dialogMode) {
        Intrinsics.checkNotNullParameter(dialogMode, "dialogMode");
        FragmentActivity activity = getActivity();
        if (activity instanceof BaseActivity) {
            ((BaseActivity) activity).showLoadingDialog$onfido_capture_sdk_core_release(dialogMode);
            return;
        }
        dismissLoadingDialog$onfido_capture_sdk_core_release();
        LoadingFragment.Companion companion = LoadingFragment.INSTANCE;
        FragmentManager childFragmentManager = getChildFragmentManager();
        Intrinsics.checkNotNullExpressionValue(childFragmentManager, "getChildFragmentManager(...)");
        companion.show(dialogMode, childFragmentManager);
    }

    public BaseFragment(int i) {
        super(i);
    }
}
