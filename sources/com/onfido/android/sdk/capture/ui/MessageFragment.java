package com.onfido.android.sdk.capture.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.oblador.keychain.KeychainModule;
import com.onfido.android.sdk.capture.databinding.OnfidoFragmentMessageBinding;
import io.sentry.rrweb.RRWebVideoEvent;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0016\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u0005¢\u0006\u0002\u0010\u0002J&\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\u0018\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014H\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0017"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/MessageFragment;", "Lcom/onfido/android/sdk/capture/ui/PageFragment;", "()V", "_binding", "Lcom/onfido/android/sdk/capture/databinding/OnfidoFragmentMessageBinding;", "binding", "getBinding", "()Lcom/onfido/android/sdk/capture/databinding/OnfidoFragmentMessageBinding;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", RRWebVideoEvent.JsonKeys.CONTAINER, "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "", "setInfo", "title", "", KeychainModule.AuthPromptOptions.SUBTITLE, "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public class MessageFragment extends PageFragment {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String MESSAGE_PARAM = "message";
    private static final String TITLE_PARAM = "title";
    private OnfidoFragmentMessageBinding _binding;

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/MessageFragment$Companion;", "", "()V", "MESSAGE_PARAM", "", "TITLE_PARAM", "createInstance", "Lcom/onfido/android/sdk/capture/ui/MessageFragment;", "title", KeychainModule.AuthPromptOptions.SUBTITLE, "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        public final MessageFragment createInstance(String title, String subtitle) {
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(subtitle, "subtitle");
            MessageFragment messageFragment = new MessageFragment();
            messageFragment.setInfo(title, subtitle);
            return messageFragment;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @JvmStatic
    public static final MessageFragment createInstance(String str, String str2) {
        return INSTANCE.createInstance(str, str2);
    }

    private final OnfidoFragmentMessageBinding getBinding() {
        OnfidoFragmentMessageBinding onfidoFragmentMessageBinding = this._binding;
        Intrinsics.checkNotNull(onfidoFragmentMessageBinding);
        return onfidoFragmentMessageBinding;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreateView$lambda$2$lambda$1$lambda$0(MessageFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        NextActionListener nextActionListener = this$0.getNextActionListener();
        if (nextActionListener != null) {
            nextActionListener.onNext();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this._binding = OnfidoFragmentMessageBinding.inflate(inflater, container, false);
        RelativeLayout root = getBinding().getRoot();
        Bundle arguments = getArguments();
        if (arguments != null) {
            String string = arguments.getString("title");
            String string2 = arguments.getString("message");
            getBinding().title.setText(string);
            getBinding().subtitle.setText(string2);
            getBinding().next.setOnClickListener(new View.OnClickListener() { // from class: com.onfido.android.sdk.capture.ui.MessageFragment$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    MessageFragment.onCreateView$lambda$2$lambda$1$lambda$0(this.f$0, view);
                }
            });
        }
        return root;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this._binding = null;
    }

    public void setInfo(String title, String subtitle) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(subtitle, "subtitle");
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putString("message", subtitle);
        setArguments(bundle);
    }
}
