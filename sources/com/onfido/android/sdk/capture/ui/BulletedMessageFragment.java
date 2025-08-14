package com.onfido.android.sdk.capture.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.clevertap.android.sdk.product_config.CTProductConfigConstants;
import com.oblador.keychain.KeychainModule;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.databinding.OnfidoFragmentBulletedMessageBinding;
import com.onfido.android.sdk.capture.utils.StringExtensionsKt;
import com.onfido.android.sdk.capture.utils.ViewExtensionsKt;
import io.sentry.rrweb.RRWebVideoEvent;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\b'\u0018\u0000 !2\u00020\u0001:\u0001!B\u0005¢\u0006\u0002\u0010\u0002J&\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\u0015\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\tH\u0010¢\u0006\u0002\b\u0014JS\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u00172\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00170\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u001d2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u0017H\u0000¢\u0006\u0002\b R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00048DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\""}, d2 = {"Lcom/onfido/android/sdk/capture/ui/BulletedMessageFragment;", "Lcom/onfido/android/sdk/capture/ui/BaseFragment;", "()V", "_binding", "Lcom/onfido/android/sdk/capture/databinding/OnfidoFragmentBulletedMessageBinding;", "binding", "getBinding", "()Lcom/onfido/android/sdk/capture/databinding/OnfidoFragmentBulletedMessageBinding;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", RRWebVideoEvent.JsonKeys.CONTAINER, "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "", "onNextClicked", CTProductConfigConstants.PRODUCT_CONFIG_JSON_KEY_FOR_VALUE, "onNextClicked$onfido_capture_sdk_core_release", "setInfo", "requestKey", "", "title", KeychainModule.AuthPromptOptions.SUBTITLE, "bullets", "", "isSubtitleLarge", "", "isWithoutListHeader", "infoText", "setInfo$onfido_capture_sdk_core_release", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public abstract class BulletedMessageFragment extends BaseFragment {
    private static final String BULLETS_LIST_PARAM = "BULLETS_LIST";
    private static final String INFO_PARAM = "INFO_LARGE";
    private static final String REQUEST_KEY_PARAM = "REQUEST_KEY";
    private static final String SUBTITLE_LARGE_PARAM = "SUBTITLE_LARGE";
    private static final String SUBTITLE_PARAM = "SUBTITLE";
    private static final String TITLE_PARAM = "TITLE";
    private static final String WITHOUT_LIST_HEADER_PARAM = "WITHOUT_LIST_HEADER";
    private OnfidoFragmentBulletedMessageBinding _binding;

    public static /* synthetic */ void setInfo$onfido_capture_sdk_core_release$default(BulletedMessageFragment bulletedMessageFragment, String str, String str2, String str3, List list, boolean z, boolean z2, String str4, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setInfo");
        }
        bulletedMessageFragment.setInfo$onfido_capture_sdk_core_release(str, str2, str3, list, (i & 16) != 0 ? false : z, (i & 32) != 0 ? false : z2, (i & 64) != 0 ? null : str4);
    }

    protected final OnfidoFragmentBulletedMessageBinding getBinding() {
        OnfidoFragmentBulletedMessageBinding onfidoFragmentBulletedMessageBinding = this._binding;
        Intrinsics.checkNotNull(onfidoFragmentBulletedMessageBinding);
        return onfidoFragmentBulletedMessageBinding;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        OnfidoFragmentBulletedMessageBinding onfidoFragmentBulletedMessageBindingInflate = OnfidoFragmentBulletedMessageBinding.inflate(inflater, container, false);
        Intrinsics.checkNotNullExpressionValue(onfidoFragmentBulletedMessageBindingInflate, "inflate(...)");
        this._binding = onfidoFragmentBulletedMessageBindingInflate;
        Bundle arguments = getArguments();
        if (arguments != null) {
            String string = arguments.getString(TITLE_PARAM);
            String string2 = arguments.getString(SUBTITLE_PARAM);
            boolean z = arguments.getBoolean(SUBTITLE_LARGE_PARAM);
            String string3 = arguments.getString(INFO_PARAM);
            boolean z2 = arguments.getBoolean(WITHOUT_LIST_HEADER_PARAM);
            onfidoFragmentBulletedMessageBindingInflate.title.setText(string);
            onfidoFragmentBulletedMessageBindingInflate.subtitle.setText(string2);
            if (z) {
                onfidoFragmentBulletedMessageBindingInflate.subtitle.setTextSize(2, getResources().getDimension(R.dimen.onfido_fs_body_regular) / getResources().getDisplayMetrics().density);
            }
            if (z2) {
                float dimension = getResources().getDimension(R.dimen.onfido_proof_of_address_margin_top) / getResources().getDisplayMetrics().density;
                LinearLayout stepsContainer = onfidoFragmentBulletedMessageBindingInflate.stepsContainer;
                Intrinsics.checkNotNullExpressionValue(stepsContainer, "stepsContainer");
                ViewGroup.LayoutParams layoutParams = stepsContainer.getLayoutParams();
                if (layoutParams == null) {
                    throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
                }
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                marginLayoutParams.setMargins(marginLayoutParams.leftMargin, (int) dimension, marginLayoutParams.rightMargin, marginLayoutParams.bottomMargin);
                stepsContainer.setLayoutParams(marginLayoutParams);
                onfidoFragmentBulletedMessageBindingInflate.getRoot().requestLayout();
            }
            Iterable stringArrayList = arguments.getStringArrayList(BULLETS_LIST_PARAM);
            if (stringArrayList == null) {
                stringArrayList = CollectionsKt.emptyList();
            } else {
                Intrinsics.checkNotNull(stringArrayList);
            }
            int i = 0;
            for (Object obj : stringArrayList) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                String str = (String) obj;
                Context context = onfidoFragmentBulletedMessageBindingInflate.getRoot().getContext();
                Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
                BulletStepView bulletStepView = new BulletStepView(context, null, 0, 6, null);
                bulletStepView.setPadding(0, 0, 0, bulletStepView.getContext().getResources().getDimensionPixelSize(R.dimen.onfido_bullets_margin_vertical));
                Intrinsics.checkNotNull(str);
                bulletStepView.setStepInfo(i2, str);
                bulletStepView.setIcon(R.drawable.onfido_arrow_forward_white);
                bulletStepView.hideSeparators();
                onfidoFragmentBulletedMessageBindingInflate.stepsContainer.addView(bulletStepView);
                i = i2;
            }
            if (StringExtensionsKt.isNotNullOrEmpty(string3)) {
                TextView infoTextView = onfidoFragmentBulletedMessageBindingInflate.infoTextView;
                Intrinsics.checkNotNullExpressionValue(infoTextView, "infoTextView");
                ViewExtensionsKt.toVisible$default(infoTextView, false, 1, null);
                onfidoFragmentBulletedMessageBindingInflate.infoTextView.setText(string3);
            } else {
                TextView infoTextView2 = onfidoFragmentBulletedMessageBindingInflate.infoTextView;
                Intrinsics.checkNotNullExpressionValue(infoTextView2, "infoTextView");
                ViewExtensionsKt.toGone$default(infoTextView2, false, 1, null);
            }
            onfidoFragmentBulletedMessageBindingInflate.next.setOnClickListener(new View.OnClickListener() { // from class: com.onfido.android.sdk.capture.ui.BulletedMessageFragment$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.onNextClicked$onfido_capture_sdk_core_release(view);
                }
            });
        }
        return onfidoFragmentBulletedMessageBindingInflate.getRoot();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this._binding = null;
    }

    public void onNextClicked$onfido_capture_sdk_core_release(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        String string = requireArguments().getString(REQUEST_KEY_PARAM);
        if (string == null) {
            throw new IllegalArgumentException("Required value was null.".toString());
        }
        Intrinsics.checkNotNullExpressionValue(string, "requireNotNull(...)");
        getParentFragmentManager().setFragmentResult(string, Bundle.EMPTY);
    }

    public final void setInfo$onfido_capture_sdk_core_release(String requestKey, String title, String subtitle, List<String> bullets, boolean isSubtitleLarge, boolean isWithoutListHeader, String infoText) {
        Intrinsics.checkNotNullParameter(requestKey, "requestKey");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(subtitle, "subtitle");
        Intrinsics.checkNotNullParameter(bullets, "bullets");
        Bundle bundle = new Bundle();
        bundle.putString(REQUEST_KEY_PARAM, requestKey);
        bundle.putStringArrayList(BULLETS_LIST_PARAM, new ArrayList<>(bullets));
        bundle.putString(TITLE_PARAM, title);
        bundle.putString(SUBTITLE_PARAM, subtitle);
        bundle.putBoolean(SUBTITLE_LARGE_PARAM, isSubtitleLarge);
        bundle.putBoolean(WITHOUT_LIST_HEADER_PARAM, isWithoutListHeader);
        bundle.putString(INFO_PARAM, infoText);
        setArguments(bundle);
    }
}
