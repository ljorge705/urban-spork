package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.headturn;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.headturn.OnfidoAnimWebView;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.util.OvalAlignedView;
import com.onfido.android.sdk.capture.databinding.OnfidoAvcViewHeadTurnAnimBinding;
import com.onfido.android.sdk.capture.utils.ViewExtensionsKt;
import io.sentry.Session;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0006\u0010\r\u001a\u00020\u000eJ\u0006\u0010\u000f\u001a\u00020\u000eJ\u001c\u0010\u0010\u001a\u00020\u000e*\u00020\u00112\u000e\b\u0002\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0013H\u0002R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/view/headturn/HeadTurnGuidanceVideoView;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/view/util/OvalAlignedView;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", Session.JsonKeys.ATTRS, "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "binding", "Lcom/onfido/android/sdk/capture/databinding/OnfidoAvcViewHeadTurnAnimBinding;", "hide", "", "show", "loadAnim", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/view/headturn/OnfidoAnimWebView;", "onPageFinished", "Lkotlin/Function0;", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class HeadTurnGuidanceVideoView extends OvalAlignedView {
    public static final String CAPTURE_ANIM_PATH = "motion-helper.json";
    private final OnfidoAvcViewHeadTurnAnimBinding binding;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HeadTurnGuidanceVideoView(Context context) throws JSONException {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        OnfidoAvcViewHeadTurnAnimBinding onfidoAvcViewHeadTurnAnimBindingInflate = OnfidoAvcViewHeadTurnAnimBinding.inflate(LayoutInflater.from(getContext()), this, true);
        Intrinsics.checkNotNullExpressionValue(onfidoAvcViewHeadTurnAnimBindingInflate, "inflate(...)");
        this.binding = onfidoAvcViewHeadTurnAnimBindingInflate;
        OnfidoAnimWebView animWebView = onfidoAvcViewHeadTurnAnimBindingInflate.animWebView;
        Intrinsics.checkNotNullExpressionValue(animWebView, "animWebView");
        loadAnim$default(this, animWebView, null, 1, null);
    }

    private final void loadAnim(OnfidoAnimWebView onfidoAnimWebView, Function0<Unit> function0) throws JSONException {
        onfidoAnimWebView.loadAnim(CAPTURE_ANIM_PATH, (30 & 2) != 0 ? false : false, (30 & 4) != 0 ? OnfidoAnimWebView.BACKGROUND_TRANSPARENT : null, (30 & 8) != 0 ? false : false, (30 & 16) != 0 ? 5 : 0, R.string.onfido_avc_intro_video_accessibility, R.string.onfido_video_intro_button_play_accessibility, R.string.onfido_video_intro_button_pause_accessibility, (30 & 256) != 0 ? OnfidoAnimWebView.AnonymousClass1.INSTANCE : function0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ void loadAnim$default(HeadTurnGuidanceVideoView headTurnGuidanceVideoView, OnfidoAnimWebView onfidoAnimWebView, Function0 function0, int i, Object obj) throws JSONException {
        if ((i & 1) != 0) {
            function0 = new Function0<Unit>() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.headturn.HeadTurnGuidanceVideoView.loadAnim.1
                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }
            };
        }
        headTurnGuidanceVideoView.loadAnim(onfidoAnimWebView, function0);
    }

    public final void hide() {
        ViewExtensionsKt.hideWithAlphaAnim$default(this, 0.0f, 0L, 3, null);
    }

    public final void show() throws JSONException {
        OnfidoAnimWebView onfidoAnimWebView = this.binding.animWebView;
        Intrinsics.checkNotNull(onfidoAnimWebView);
        loadAnim(onfidoAnimWebView, new Function0<Unit>() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.headturn.HeadTurnGuidanceVideoView$show$1$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                ViewExtensionsKt.showWithAlphaAnim$default(this.this$0, 0.0f, 0L, 3, null);
            }
        });
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HeadTurnGuidanceVideoView(Context context, AttributeSet attributeSet) throws JSONException {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        OnfidoAvcViewHeadTurnAnimBinding onfidoAvcViewHeadTurnAnimBindingInflate = OnfidoAvcViewHeadTurnAnimBinding.inflate(LayoutInflater.from(getContext()), this, true);
        Intrinsics.checkNotNullExpressionValue(onfidoAvcViewHeadTurnAnimBindingInflate, "inflate(...)");
        this.binding = onfidoAvcViewHeadTurnAnimBindingInflate;
        OnfidoAnimWebView animWebView = onfidoAvcViewHeadTurnAnimBindingInflate.animWebView;
        Intrinsics.checkNotNullExpressionValue(animWebView, "animWebView");
        loadAnim$default(this, animWebView, null, 1, null);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HeadTurnGuidanceVideoView(Context context, AttributeSet attributeSet, int i) throws JSONException {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        OnfidoAvcViewHeadTurnAnimBinding onfidoAvcViewHeadTurnAnimBindingInflate = OnfidoAvcViewHeadTurnAnimBinding.inflate(LayoutInflater.from(getContext()), this, true);
        Intrinsics.checkNotNullExpressionValue(onfidoAvcViewHeadTurnAnimBindingInflate, "inflate(...)");
        this.binding = onfidoAvcViewHeadTurnAnimBindingInflate;
        OnfidoAnimWebView animWebView = onfidoAvcViewHeadTurnAnimBindingInflate.animWebView;
        Intrinsics.checkNotNullExpressionValue(animWebView, "animWebView");
        loadAnim$default(this, animWebView, null, 1, null);
    }
}
