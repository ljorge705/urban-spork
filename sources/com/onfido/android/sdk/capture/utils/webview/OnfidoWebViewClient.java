package com.onfido.android.sdk.capture.utils.webview;

import android.graphics.Bitmap;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B=\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0016\b\u0002\u0010\u0006\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0004\u0012\u00020\u00040\u0007¢\u0006\u0002\u0010\tJ\u001c\u0010\u0002\u001a\u00020\u00042\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J&\u0010\u000e\u001a\u00020\u00042\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J&\u0010\u0011\u001a\u00020\u00042\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\bH\u0016R\u001c\u0010\u0006\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0004\u0012\u00020\u00040\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/onfido/android/sdk/capture/utils/webview/OnfidoWebViewClient;", "Landroid/webkit/WebViewClient;", "onPageFinished", "Lkotlin/Function0;", "", "onStarted", "onError", "Lkotlin/Function1;", "Landroid/webkit/WebResourceError;", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;)V", "view", "Landroid/webkit/WebView;", "url", "", "onPageStarted", "favicon", "Landroid/graphics/Bitmap;", "onReceivedError", "request", "Landroid/webkit/WebResourceRequest;", "error", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class OnfidoWebViewClient extends WebViewClient {
    private final Function1<WebResourceError, Unit> onError;
    private final Function0<Unit> onPageFinished;
    private final Function0<Unit> onStarted;

    public OnfidoWebViewClient() {
        this(null, null, null, 7, null);
    }

    @Override // android.webkit.WebViewClient
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        this.onPageFinished.invoke();
    }

    @Override // android.webkit.WebViewClient
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        this.onStarted.invoke();
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        this.onError.invoke(error);
        super.onReceivedError(view, request, error);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public OnfidoWebViewClient(Function0<Unit> onPageFinished, Function0<Unit> onStarted, Function1<? super WebResourceError, Unit> onError) {
        Intrinsics.checkNotNullParameter(onPageFinished, "onPageFinished");
        Intrinsics.checkNotNullParameter(onStarted, "onStarted");
        Intrinsics.checkNotNullParameter(onError, "onError");
        this.onPageFinished = onPageFinished;
        this.onStarted = onStarted;
        this.onError = onError;
    }

    public /* synthetic */ OnfidoWebViewClient(Function0 function0, Function0 function02, Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new Function0<Unit>() { // from class: com.onfido.android.sdk.capture.utils.webview.OnfidoWebViewClient.1
            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
            }
        } : function0, (i & 2) != 0 ? new Function0<Unit>() { // from class: com.onfido.android.sdk.capture.utils.webview.OnfidoWebViewClient.2
            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
            }
        } : function02, (i & 4) != 0 ? new Function1<WebResourceError, Unit>() { // from class: com.onfido.android.sdk.capture.utils.webview.OnfidoWebViewClient.3
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(WebResourceError webResourceError) {
                invoke2(webResourceError);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(WebResourceError webResourceError) {
            }
        } : function1);
    }
}
