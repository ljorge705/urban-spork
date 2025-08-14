package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.headturn;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.ReactNativeBlobUtil.ReactNativeBlobUtilConst;
import com.facebook.react.modules.appstate.AppStateModule;
import io.sentry.Session;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

@Metadata(d1 = {"\u0000C\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0004*\u0001\u001a\b\u0000\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJd\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u000e2\b\b\u0002\u0010\u0012\u001a\u00020\u00102\b\b\u0002\u0010\u0013\u001a\u00020\t2\b\b\u0001\u0010\u0014\u001a\u00020\t2\b\b\u0001\u0010\u0015\u001a\u00020\t2\b\b\u0001\u0010\u0016\u001a\u00020\t2\u000e\b\u0002\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\f0\u0018J\u001b\u0010\u0019\u001a\u00020\u001a2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\f0\u0018H\u0002¢\u0006\u0002\u0010\u001b¨\u0006\u001d"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/view/headturn/OnfidoAnimWebView;", "Landroid/webkit/WebView;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", Session.JsonKeys.ATTRS, "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "loadAnim", "", ReactNativeBlobUtilConst.RNFB_RESPONSE_PATH, "", "isDarkMode", "", AppStateModule.APP_STATE_BACKGROUND, "controls", "controlsTimeout", "animAccessibility", "buttonPlayAccessibility", "buttonPauseAccessibility", "onPageFinished", "Lkotlin/Function0;", "webViewClient", "com/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/view/headturn/OnfidoAnimWebView$webViewClient$1", "(Lkotlin/jvm/functions/Function0;)Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/view/headturn/OnfidoAnimWebView$webViewClient$1;", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class OnfidoAnimWebView extends WebView {
    public static final String BACKGROUND_TRANSPARENT = "transparent";
    public static final String LOTTIE_PLAYER_URL = "https://assets.onfido.com/lottie/index.html?";
    public static final String THEME_DARK = "dark";
    public static final String THEME_LIGHT = "light";

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OnfidoAnimWebView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        setBackgroundColor(0);
        getSettings().setJavaScriptEnabled(true);
        setVerticalScrollBarEnabled(false);
        setHorizontalScrollBarEnabled(false);
        setOnTouchListener(new View.OnTouchListener() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.headturn.OnfidoAnimWebView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return OnfidoAnimWebView._init_$lambda$0(view, motionEvent);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean _init_$lambda$0(View view, MotionEvent motionEvent) {
        return motionEvent.getAction() == 2;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.headturn.OnfidoAnimWebView$webViewClient$1] */
    private final C05511 webViewClient(final Function0<Unit> onPageFinished) {
        return new WebViewClient() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.headturn.OnfidoAnimWebView.webViewClient.1
            @Override // android.webkit.WebViewClient
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                onPageFinished.invoke();
            }
        };
    }

    public final void loadAnim(String path, boolean isDarkMode, String background, boolean controls, int controlsTimeout, int animAccessibility, int buttonPlayAccessibility, int buttonPauseAccessibility, final Function0<Unit> onPageFinished) throws JSONException {
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(background, "background");
        Intrinsics.checkNotNullParameter(onPageFinished, "onPageFinished");
        setWebViewClient(webViewClient(new Function0<Unit>() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.headturn.OnfidoAnimWebView.loadAnim.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                onPageFinished.invoke();
            }
        }));
        String str = isDarkMode ? THEME_DARK : THEME_LIGHT;
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("anim_accessibility", getContext().getString(animAccessibility));
        jSONObject.put("button_play_accessibility", getContext().getString(buttonPlayAccessibility));
        jSONObject.put("button_pause_accessibility", getContext().getString(buttonPauseAccessibility));
        String string = jSONObject.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        loadUrl("https://assets.onfido.com/lottie/index.html?&path=" + path + "&theme=" + str + "&background=" + background + "&controls=" + controls + "&controlsTimeout=" + controlsTimeout + "&localization=" + Uri.encode(string));
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OnfidoAnimWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        setBackgroundColor(0);
        getSettings().setJavaScriptEnabled(true);
        setVerticalScrollBarEnabled(false);
        setHorizontalScrollBarEnabled(false);
        setOnTouchListener(new View.OnTouchListener() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.headturn.OnfidoAnimWebView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return OnfidoAnimWebView._init_$lambda$0(view, motionEvent);
            }
        });
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OnfidoAnimWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        setBackgroundColor(0);
        getSettings().setJavaScriptEnabled(true);
        setVerticalScrollBarEnabled(false);
        setHorizontalScrollBarEnabled(false);
        setOnTouchListener(new View.OnTouchListener() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.headturn.OnfidoAnimWebView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return OnfidoAnimWebView._init_$lambda$0(view, motionEvent);
            }
        });
    }
}
