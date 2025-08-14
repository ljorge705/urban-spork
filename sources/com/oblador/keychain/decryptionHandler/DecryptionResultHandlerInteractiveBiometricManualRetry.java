package com.oblador.keychain.decryptionHandler;

import android.os.Looper;
import android.util.Log;
import androidx.biometric.BiometricPrompt;
import androidx.fragment.app.FragmentActivity;
import com.facebook.react.bridge.ReactApplicationContext;
import com.oblador.keychain.cipherStorage.CipherStorage;

/* loaded from: classes2.dex */
public class DecryptionResultHandlerInteractiveBiometricManualRetry extends DecryptionResultHandlerInteractiveBiometric implements DecryptionResultHandler {
    private Boolean didFailBiometric;
    private BiometricPrompt presentedPrompt;

    public DecryptionResultHandlerInteractiveBiometricManualRetry(ReactApplicationContext reactApplicationContext, CipherStorage cipherStorage, BiometricPrompt.PromptInfo promptInfo) {
        super(reactApplicationContext, cipherStorage, promptInfo);
        this.didFailBiometric = false;
    }

    private void cancelPresentedAuthentication() {
        Log.d(LOG_TAG, "Cancelling authentication");
        BiometricPrompt biometricPrompt = this.presentedPrompt;
        if (biometricPrompt == null) {
            return;
        }
        try {
            try {
                biometricPrompt.cancelAuthentication();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
            this.presentedPrompt = null;
        }
    }

    @Override // com.oblador.keychain.decryptionHandler.DecryptionResultHandlerInteractiveBiometric, androidx.biometric.BiometricPrompt.AuthenticationCallback
    public void onAuthenticationError(int i, CharSequence charSequence) throws InterruptedException {
        if (this.didFailBiometric.booleanValue()) {
            this.presentedPrompt = null;
            this.didFailBiometric = false;
            retryAuthentication();
            return;
        }
        super.onAuthenticationError(i, charSequence);
    }

    @Override // androidx.biometric.BiometricPrompt.AuthenticationCallback
    public void onAuthenticationFailed() {
        Log.d(LOG_TAG, "Authentication failed: biometric not recognized.");
        if (this.presentedPrompt != null) {
            this.didFailBiometric = true;
            cancelPresentedAuthentication();
        }
    }

    @Override // com.oblador.keychain.decryptionHandler.DecryptionResultHandlerInteractiveBiometric, androidx.biometric.BiometricPrompt.AuthenticationCallback
    public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult authenticationResult) {
        this.presentedPrompt = null;
        this.didFailBiometric = false;
        super.onAuthenticationSucceeded(authenticationResult);
    }

    @Override // com.oblador.keychain.decryptionHandler.DecryptionResultHandlerInteractiveBiometric
    public void startAuthentication() {
        FragmentActivity currentActivity = getCurrentActivity();
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            currentActivity.runOnUiThread(new Runnable() { // from class: com.oblador.keychain.decryptionHandler.DecryptionResultHandlerInteractiveBiometricManualRetry$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.startAuthentication();
                }
            });
            waitResult();
        } else {
            this.presentedPrompt = authenticateWithPrompt(currentActivity);
        }
    }

    protected void retryAuthentication() throws InterruptedException {
        Log.d(LOG_TAG, "Retrying biometric authentication.");
        FragmentActivity currentActivity = getCurrentActivity();
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            try {
                Thread.sleep(100L);
            } catch (InterruptedException unused) {
            }
            currentActivity.runOnUiThread(new Runnable() { // from class: com.oblador.keychain.decryptionHandler.DecryptionResultHandlerInteractiveBiometricManualRetry$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() throws InterruptedException {
                    this.f$0.retryAuthentication();
                }
            });
        } else {
            this.presentedPrompt = authenticateWithPrompt(currentActivity);
        }
    }
}
