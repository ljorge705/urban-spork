package com.oblador.keychain.decryptionHandler;

import android.os.Looper;
import android.util.Log;
import androidx.biometric.BiometricPrompt;
import androidx.fragment.app.FragmentActivity;
import com.facebook.react.bridge.AssertionException;
import com.facebook.react.bridge.ReactApplicationContext;
import com.oblador.keychain.DeviceAvailability;
import com.oblador.keychain.cipherStorage.CipherStorage;
import com.oblador.keychain.cipherStorage.CipherStorageBase;
import com.oblador.keychain.exceptions.CryptoFailedException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/* loaded from: classes2.dex */
public class DecryptionResultHandlerInteractiveBiometric extends BiometricPrompt.AuthenticationCallback implements DecryptionResultHandler {
    protected static final String LOG_TAG = "DecryptionResultHandlerInteractiveBiometric";
    protected CipherStorage.DecryptionContext context;
    protected Throwable error;
    protected final Executor executor = Executors.newSingleThreadExecutor();
    protected BiometricPrompt.PromptInfo promptInfo;
    protected final ReactApplicationContext reactContext;
    protected CipherStorage.DecryptionResult result;
    protected final CipherStorageBase storage;

    @Override // com.oblador.keychain.decryptionHandler.DecryptionResultHandler
    public Throwable getError() {
        return this.error;
    }

    @Override // com.oblador.keychain.decryptionHandler.DecryptionResultHandler
    public CipherStorage.DecryptionResult getResult() {
        return this.result;
    }

    public DecryptionResultHandlerInteractiveBiometric(ReactApplicationContext reactApplicationContext, CipherStorage cipherStorage, BiometricPrompt.PromptInfo promptInfo) {
        this.reactContext = reactApplicationContext;
        this.storage = (CipherStorageBase) cipherStorage;
        this.promptInfo = promptInfo;
    }

    @Override // com.oblador.keychain.decryptionHandler.DecryptionResultHandler
    public void askAccessPermissions(CipherStorage.DecryptionContext decryptionContext) {
        this.context = decryptionContext;
        if (!DeviceAvailability.isPermissionsGranted(this.reactContext)) {
            onDecrypt(null, new CryptoFailedException("Could not start fingerprint Authentication. No permissions granted."));
        } else {
            startAuthentication();
        }
    }

    @Override // com.oblador.keychain.decryptionHandler.DecryptionResultHandler
    public void onDecrypt(CipherStorage.DecryptionResult decryptionResult, Throwable th) {
        this.result = decryptionResult;
        this.error = th;
        synchronized (this) {
            notifyAll();
        }
    }

    @Override // androidx.biometric.BiometricPrompt.AuthenticationCallback
    public void onAuthenticationError(int i, CharSequence charSequence) {
        onDecrypt(null, new CryptoFailedException("code: " + i + ", msg: " + ((Object) charSequence)));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.biometric.BiometricPrompt.AuthenticationCallback
    public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult authenticationResult) {
        try {
            if (this.context == null) {
                throw new NullPointerException("Decrypt context is not assigned yet.");
            }
            onDecrypt(new CipherStorage.DecryptionResult(this.storage.decryptBytes(this.context.key, (byte[]) this.context.username), this.storage.decryptBytes(this.context.key, (byte[]) this.context.password)), null);
        } catch (Throwable th) {
            onDecrypt(null, th);
        }
    }

    public void startAuthentication() {
        FragmentActivity currentActivity = getCurrentActivity();
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            currentActivity.runOnUiThread(new Runnable() { // from class: com.oblador.keychain.decryptionHandler.DecryptionResultHandlerInteractiveBiometric$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.startAuthentication();
                }
            });
            waitResult();
        } else {
            authenticateWithPrompt(currentActivity);
        }
    }

    protected FragmentActivity getCurrentActivity() {
        FragmentActivity fragmentActivity = (FragmentActivity) this.reactContext.getCurrentActivity();
        if (fragmentActivity != null) {
            return fragmentActivity;
        }
        throw new NullPointerException("Not assigned current activity");
    }

    protected BiometricPrompt authenticateWithPrompt(FragmentActivity fragmentActivity) {
        BiometricPrompt biometricPrompt = new BiometricPrompt(fragmentActivity, this.executor, this);
        biometricPrompt.authenticate(this.promptInfo);
        return biometricPrompt;
    }

    @Override // com.oblador.keychain.decryptionHandler.DecryptionResultHandler
    public void waitResult() {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            throw new AssertionException("method should not be executed from MAIN thread");
        }
        Log.i(LOG_TAG, "blocking thread. waiting for done UI operation.");
        try {
            synchronized (this) {
                wait();
            }
        } catch (InterruptedException unused) {
        }
        Log.i(LOG_TAG, "unblocking thread.");
    }
}
