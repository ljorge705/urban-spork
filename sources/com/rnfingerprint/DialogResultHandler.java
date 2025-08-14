package com.rnfingerprint;

import com.facebook.react.bridge.Callback;
import com.rnfingerprint.FingerprintDialog;

/* loaded from: classes6.dex */
public class DialogResultHandler implements FingerprintDialog.DialogResultListener {
    private Callback errorCallback;
    private Callback successCallback;

    public DialogResultHandler(Callback callback, Callback callback2) {
        this.errorCallback = callback;
        this.successCallback = callback2;
    }

    @Override // com.rnfingerprint.FingerprintDialog.DialogResultListener
    public void onAuthenticated() {
        FingerprintAuthModule.inProgress = false;
        this.successCallback.invoke("Successfully authenticated.");
    }

    @Override // com.rnfingerprint.FingerprintDialog.DialogResultListener
    public void onError(String str, int i) {
        FingerprintAuthModule.inProgress = false;
        this.errorCallback.invoke(str, Integer.valueOf(i));
    }

    @Override // com.rnfingerprint.FingerprintDialog.DialogResultListener
    public void onCancelled() {
        FingerprintAuthModule.inProgress = false;
        this.errorCallback.invoke("cancelled", 106);
    }
}
