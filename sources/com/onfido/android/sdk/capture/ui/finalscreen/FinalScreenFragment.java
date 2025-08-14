package com.onfido.android.sdk.capture.ui.finalscreen;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.onfido.android.sdk.capture.common.SdkController;
import com.onfido.android.sdk.capture.databinding.OnfidoFragmentFinalBinding;
import com.onfido.android.sdk.capture.ui.MessageFragment;
import com.onfido.android.sdk.capture.ui.NextActionListener;
import com.onfido.javax.inject.Inject;
import io.sentry.rrweb.RRWebVideoEvent;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u0005¢\u0006\u0002\u0010\u0002J$\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\b\u0010\u0018\u001a\u00020\u0017H\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u001e\u0010\b\u001a\u00020\t8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u001a"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/finalscreen/FinalScreenFragment;", "Lcom/onfido/android/sdk/capture/ui/MessageFragment;", "()V", "_binding", "Lcom/onfido/android/sdk/capture/databinding/OnfidoFragmentFinalBinding;", "binding", "getBinding", "()Lcom/onfido/android/sdk/capture/databinding/OnfidoFragmentFinalBinding;", "presenter", "Lcom/onfido/android/sdk/capture/ui/finalscreen/FinalScreenPresenter;", "getPresenter", "()Lcom/onfido/android/sdk/capture/ui/finalscreen/FinalScreenPresenter;", "setPresenter", "(Lcom/onfido/android/sdk/capture/ui/finalscreen/FinalScreenPresenter;)V", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", RRWebVideoEvent.JsonKeys.CONTAINER, "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "", "onStart", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FinalScreenFragment extends MessageFragment {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private OnfidoFragmentFinalBinding _binding;

    @Inject
    public FinalScreenPresenter presenter;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007¨\u0006\u0005"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/finalscreen/FinalScreenFragment$Companion;", "", "()V", "createInstance", "Lcom/onfido/android/sdk/capture/ui/finalscreen/FinalScreenFragment;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        public final FinalScreenFragment createInstance() {
            return new FinalScreenFragment();
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @JvmStatic
    public static final FinalScreenFragment createInstance() {
        return INSTANCE.createInstance();
    }

    private final OnfidoFragmentFinalBinding getBinding() {
        OnfidoFragmentFinalBinding onfidoFragmentFinalBinding = this._binding;
        Intrinsics.checkNotNull(onfidoFragmentFinalBinding);
        return onfidoFragmentFinalBinding;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreateView$lambda$1$lambda$0(FinalScreenFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        NextActionListener nextActionListener = this$0.getNextActionListener();
        if (nextActionListener != null) {
            nextActionListener.onNext();
        }
    }

    public final FinalScreenPresenter getPresenter() {
        FinalScreenPresenter finalScreenPresenter = this.presenter;
        if (finalScreenPresenter != null) {
            return finalScreenPresenter;
        }
        Intrinsics.throwUninitializedPropertyAccessException("presenter");
        return null;
    }

    @Override // com.onfido.android.sdk.capture.ui.MessageFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this._binding = OnfidoFragmentFinalBinding.inflate(inflater, container, false);
        OnfidoFragmentFinalBinding binding = getBinding();
        SdkController companion = SdkController.INSTANCE.getInstance();
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext(...)");
        SdkController.getSdkComponent$default(companion, contextRequireContext, null, 2, null).inject$onfido_capture_sdk_core_release(this);
        binding.submit.setOnClickListener(new View.OnClickListener() { // from class: com.onfido.android.sdk.capture.ui.finalscreen.FinalScreenFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FinalScreenFragment.onCreateView$lambda$1$lambda$0(this.f$0, view);
            }
        });
        RelativeLayout root = getBinding().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    @Override // com.onfido.android.sdk.capture.ui.MessageFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this._binding = null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        getPresenter().onStart();
    }

    public final void setPresenter(FinalScreenPresenter finalScreenPresenter) {
        Intrinsics.checkNotNullParameter(finalScreenPresenter, "<set-?>");
        this.presenter = finalScreenPresenter;
    }
}
