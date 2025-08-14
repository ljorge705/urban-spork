package com.rnfingerprint;

import android.app.Activity;
import android.app.KeyguardManager;
import android.hardware.fingerprint.FingerprintManager;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import io.sentry.SentryEvent;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import javax.crypto.Cipher;

/* loaded from: classes6.dex */
public class FingerprintAuthModule extends ReactContextBaseJavaModule implements LifecycleEventListener {
    private static final String FRAGMENT_TAG = "fingerprint_dialog";
    public static boolean inProgress = false;
    private boolean isAppActive;
    private KeyguardManager keyguardManager;

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "FingerprintAuth";
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
        this.isAppActive = false;
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
        this.isAppActive = false;
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
        this.isAppActive = true;
    }

    public FingerprintAuthModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        reactApplicationContext.addLifecycleEventListener(this);
    }

    private KeyguardManager getKeyguardManager() {
        KeyguardManager keyguardManager = this.keyguardManager;
        if (keyguardManager != null) {
            return keyguardManager;
        }
        Activity currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            return null;
        }
        KeyguardManager keyguardManager2 = (KeyguardManager) currentActivity.getSystemService("keyguard");
        this.keyguardManager = keyguardManager2;
        return keyguardManager2;
    }

    @ReactMethod
    public void isSupported(Callback callback, Callback callback2) {
        if (getCurrentActivity() == null) {
            return;
        }
        int iIsFingerprintAuthAvailable = isFingerprintAuthAvailable();
        if (iIsFingerprintAuthAvailable == 100) {
            callback2.invoke("Is supported.");
        } else {
            callback.invoke("Not supported.", Integer.valueOf(iIsFingerprintAuthAvailable));
        }
    }

    @ReactMethod
    public void authenticate(String str, ReadableMap readableMap, Callback callback, Callback callback2) throws NoSuchAlgorithmException, IOException, InvalidKeyException, CertificateException {
        Activity currentActivity = getCurrentActivity();
        if (inProgress || !this.isAppActive || currentActivity == null) {
            return;
        }
        inProgress = true;
        int iIsFingerprintAuthAvailable = isFingerprintAuthAvailable();
        if (iIsFingerprintAuthAvailable != 100) {
            inProgress = false;
            callback.invoke("Not supported", Integer.valueOf(iIsFingerprintAuthAvailable));
            return;
        }
        Cipher cipher = new FingerprintCipher().getCipher();
        if (cipher == null) {
            inProgress = false;
            callback.invoke("Not supported", 103);
            return;
        }
        FingerprintManager.CryptoObject cryptoObject = new FingerprintManager.CryptoObject(cipher);
        DialogResultHandler dialogResultHandler = new DialogResultHandler(callback, callback2);
        FingerprintDialog fingerprintDialog = new FingerprintDialog();
        fingerprintDialog.setCryptoObject(cryptoObject);
        fingerprintDialog.setReasonForAuthentication(str);
        fingerprintDialog.setAuthConfig(readableMap);
        fingerprintDialog.setDialogCallback(dialogResultHandler);
        if (this.isAppActive) {
            fingerprintDialog.show(currentActivity.getFragmentManager(), FRAGMENT_TAG);
        } else {
            inProgress = false;
        }
    }

    private int isFingerprintAuthAvailable() {
        Activity currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            return 103;
        }
        KeyguardManager keyguardManager = getKeyguardManager();
        FingerprintManager fingerprintManager = (FingerprintManager) currentActivity.getSystemService(SentryEvent.JsonKeys.FINGERPRINT);
        if (fingerprintManager == null || !fingerprintManager.isHardwareDetected()) {
            return 102;
        }
        if (keyguardManager == null || !keyguardManager.isKeyguardSecure()) {
            return 103;
        }
        return !fingerprintManager.hasEnrolledFingerprints() ? 104 : 100;
    }
}
