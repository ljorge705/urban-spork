package com.onfido.android.sdk.capture.ui.base;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\u0012\u0010\u0004\u001a\u00020\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\b\u0010\u0007\u001a\u00020\u0003H\u0016¨\u0006\b"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/base/BaseView;", "", "onHideLoading", "", "onShowError", "message", "", "onShowLoading", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface BaseView {

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class DefaultImpls {
        public static void onHideLoading(BaseView baseView) {
        }

        public static void onShowError(BaseView baseView, String str) {
        }

        public static void onShowLoading(BaseView baseView) {
        }
    }

    void onHideLoading();

    void onShowError(String message);

    void onShowLoading();
}
