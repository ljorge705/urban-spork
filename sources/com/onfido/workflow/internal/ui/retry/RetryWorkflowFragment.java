package com.onfido.workflow.internal.ui.retry;

import android.os.Bundle;
import android.view.View;
import androidx.core.os.BundleKt;
import androidx.fragment.app.Fragment;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.databinding.OnfidoWorkflowRetryFragmentBinding;
import com.onfido.workflow.internal.ui.retry.RetryWorkflowViewDescriptor;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* compiled from: RetryWorkflowFragment.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lcom/onfido/workflow/internal/ui/retry/RetryWorkflowFragment;", "Landroidx/fragment/app/Fragment;", "()V", "viewDescriptor", "Lcom/onfido/workflow/internal/ui/retry/RetryWorkflowViewDescriptor;", "getViewDescriptor", "()Lcom/onfido/workflow/internal/ui/retry/RetryWorkflowViewDescriptor;", "viewDescriptor$delegate", "Lkotlin/Lazy;", "onViewCreated", "", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "Companion", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class RetryWorkflowFragment extends Fragment {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String KEY_DESCRIPTOR = "key_descriptor";
    private static final String KEY_REQUEST = "retry_workflow_request_key";

    /* renamed from: viewDescriptor$delegate, reason: from kotlin metadata */
    private final Lazy viewDescriptor;

    public RetryWorkflowFragment() {
        super(R.layout.onfido_workflow_retry_fragment);
        this.viewDescriptor = LazyKt.lazy(new Function0<RetryWorkflowViewDescriptor>() { // from class: com.onfido.workflow.internal.ui.retry.RetryWorkflowFragment$viewDescriptor$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final RetryWorkflowViewDescriptor invoke() {
                RetryWorkflowViewDescriptor retryWorkflowViewDescriptor = (RetryWorkflowViewDescriptor) this.this$0.requireArguments().getParcelable("key_descriptor");
                if (retryWorkflowViewDescriptor != null) {
                    return retryWorkflowViewDescriptor;
                }
                throw new IllegalArgumentException(("RetryWorkflowFragment can't work without a " + Reflection.getOrCreateKotlinClass(RetryWorkflowViewDescriptor.class).getSimpleName()).toString());
            }
        });
    }

    private final RetryWorkflowViewDescriptor getViewDescriptor() {
        return (RetryWorkflowViewDescriptor) this.viewDescriptor.getValue();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        String string;
        String string2;
        String string3;
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        OnfidoWorkflowRetryFragmentBinding onfidoWorkflowRetryFragmentBindingBind = OnfidoWorkflowRetryFragmentBinding.bind(view);
        Intrinsics.checkNotNullExpressionValue(onfidoWorkflowRetryFragmentBindingBind, "bind(...)");
        RetryWorkflowViewDescriptor.Texts texts = getViewDescriptor().getTexts();
        if (texts == null || (string = texts.getTitle()) == null) {
            string = getString(getViewDescriptor().getStringResIds().getTitle());
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        }
        RetryWorkflowViewDescriptor.Texts texts2 = getViewDescriptor().getTexts();
        if (texts2 == null || (string2 = texts2.getDescription()) == null) {
            string2 = getString(getViewDescriptor().getStringResIds().getDescription());
            Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        }
        RetryWorkflowViewDescriptor.Texts texts3 = getViewDescriptor().getTexts();
        if (texts3 == null || (string3 = texts3.getButton()) == null) {
            string3 = getString(getViewDescriptor().getStringResIds().getButton());
            Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
        }
        onfidoWorkflowRetryFragmentBindingBind.title.setText(string);
        onfidoWorkflowRetryFragmentBindingBind.description.setText(string2);
        onfidoWorkflowRetryFragmentBindingBind.retryButton.setText(string3);
        onfidoWorkflowRetryFragmentBindingBind.retryButton.setOnClickListener(new View.OnClickListener() { // from class: com.onfido.workflow.internal.ui.retry.RetryWorkflowFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                RetryWorkflowFragment.onViewCreated$lambda$1(this.f$0, view2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$1(RetryWorkflowFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String string = this$0.requireArguments().getString(KEY_REQUEST);
        if (string == null) {
            throw new IllegalArgumentException("retry_workflow_request_key is missing".toString());
        }
        Intrinsics.checkNotNullExpressionValue(string, "requireNotNull(...)");
        this$0.getParentFragmentManager().setFragmentResult(string, BundleKt.bundleOf());
    }

    /* compiled from: RetryWorkflowFragment.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/onfido/workflow/internal/ui/retry/RetryWorkflowFragment$Companion;", "", "()V", "KEY_DESCRIPTOR", "", "KEY_REQUEST", "newInstance", "Lcom/onfido/workflow/internal/ui/retry/RetryWorkflowFragment;", "requestKey", "workflowRetryViewDescriptor", "Lcom/onfido/workflow/internal/ui/retry/RetryWorkflowViewDescriptor;", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final RetryWorkflowFragment newInstance(String requestKey, RetryWorkflowViewDescriptor workflowRetryViewDescriptor) {
            Intrinsics.checkNotNullParameter(requestKey, "requestKey");
            Intrinsics.checkNotNullParameter(workflowRetryViewDescriptor, "workflowRetryViewDescriptor");
            RetryWorkflowFragment retryWorkflowFragment = new RetryWorkflowFragment();
            retryWorkflowFragment.setArguments(BundleKt.bundleOf(TuplesKt.to(RetryWorkflowFragment.KEY_DESCRIPTOR, workflowRetryViewDescriptor), TuplesKt.to(RetryWorkflowFragment.KEY_REQUEST, requestKey)));
            return retryWorkflowFragment;
        }
    }
}
