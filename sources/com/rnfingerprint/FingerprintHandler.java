package com.rnfingerprint;

import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.os.CancellationSignal;

/* loaded from: classes6.dex */
public class FingerprintHandler extends FingerprintManager.AuthenticationCallback {
    private CancellationSignal cancellationSignal;
    private final Callback mCallback;
    private final FingerprintManager mFingerprintManager;
    private boolean selfCancelled;

    public interface Callback {
        void onAuthenticated();

        void onCancelled();

        void onError(String str, int i);
    }

    public FingerprintHandler(Context context, Callback callback) {
        this.mFingerprintManager = (FingerprintManager) context.getSystemService(FingerprintManager.class);
        this.mCallback = callback;
    }

    public void startAuth(FingerprintManager.CryptoObject cryptoObject) {
        CancellationSignal cancellationSignal = new CancellationSignal();
        this.cancellationSignal = cancellationSignal;
        this.selfCancelled = false;
        this.mFingerprintManager.authenticate(cryptoObject, cancellationSignal, 0, this, null);
    }

    public void endAuth() {
        cancelAuthenticationSignal();
    }

    @Override // android.hardware.fingerprint.FingerprintManager.AuthenticationCallback
    public void onAuthenticationError(int i, CharSequence charSequence) {
        if (this.selfCancelled) {
            return;
        }
        this.mCallback.onError(charSequence.toString(), i);
    }

    @Override // android.hardware.fingerprint.FingerprintManager.AuthenticationCallback
    public void onAuthenticationFailed() {
        this.mCallback.onError("Not recognized. Try again.", 105);
    }

    @Override // android.hardware.fingerprint.FingerprintManager.AuthenticationCallback
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult authenticationResult) {
        this.mCallback.onAuthenticated();
        cancelAuthenticationSignal();
    }

    private void cancelAuthenticationSignal() {
        this.selfCancelled = true;
        CancellationSignal cancellationSignal = this.cancellationSignal;
        if (cancellationSignal != null) {
            cancellationSignal.cancel();
            this.cancellationSignal = null;
        }
    }
}
