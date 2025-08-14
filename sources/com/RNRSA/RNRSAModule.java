package com.RNRSA;

import android.os.AsyncTask;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableNativeMap;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class RNRSAModule extends ReactContextBaseJavaModule {
    private static final String SHA256withRSA = "SHA256withRSA";
    private static final String SHA512withRSA = "SHA512withRSA";
    private final ReactApplicationContext reactContext;

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "RNRSA";
    }

    public RNRSAModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.reactContext = reactApplicationContext;
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    public Map<String, Object> getConstants() {
        HashMap map = new HashMap();
        map.put(SHA256withRSA, SHA256withRSA);
        map.put(SHA512withRSA, SHA512withRSA);
        return map;
    }

    @ReactMethod
    public void generate(Promise promise) {
        generateKeys(2048, promise);
    }

    @ReactMethod
    public void generateKeys(final int i, final Promise promise) {
        AsyncTask.execute(new Runnable() { // from class: com.RNRSA.RNRSAModule.1
            @Override // java.lang.Runnable
            public void run() {
                WritableNativeMap writableNativeMap = new WritableNativeMap();
                try {
                    RSA rsa = new RSA();
                    rsa.generate(i);
                    writableNativeMap.putString("public", rsa.getPublicKey());
                    writableNativeMap.putString("private", rsa.getPrivateKey());
                    promise.resolve(writableNativeMap);
                } catch (NoSuchAlgorithmException e) {
                    promise.reject("Error", e.getMessage());
                } catch (Exception e2) {
                    promise.reject("Error", e2.getMessage());
                }
            }
        });
    }

    @ReactMethod
    public void encrypt(final String str, final String str2, final Promise promise) {
        AsyncTask.execute(new Runnable() { // from class: com.RNRSA.RNRSAModule.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    RSA rsa = new RSA();
                    rsa.setPublicKey(str2);
                    promise.resolve(rsa.encrypt(str));
                } catch (Exception e) {
                    promise.reject("Error", e.getMessage());
                }
            }
        });
    }

    @ReactMethod
    public void encrypt64(final String str, final String str2, final Promise promise) {
        AsyncTask.execute(new Runnable() { // from class: com.RNRSA.RNRSAModule.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    RSA rsa = new RSA();
                    rsa.setPublicKey(str2);
                    promise.resolve(rsa.encrypt64(str));
                } catch (Exception e) {
                    promise.reject("Error", e.getMessage());
                }
            }
        });
    }

    @ReactMethod
    public void decrypt(final String str, final String str2, final Promise promise) {
        AsyncTask.execute(new Runnable() { // from class: com.RNRSA.RNRSAModule.4
            @Override // java.lang.Runnable
            public void run() {
                try {
                    RSA rsa = new RSA();
                    rsa.setPrivateKey(str2);
                    promise.resolve(rsa.decrypt(str));
                } catch (Exception e) {
                    promise.reject("Error", e.getMessage());
                }
            }
        });
    }

    @ReactMethod
    public void decrypt64(final String str, final String str2, final Promise promise) {
        AsyncTask.execute(new Runnable() { // from class: com.RNRSA.RNRSAModule.5
            @Override // java.lang.Runnable
            public void run() {
                try {
                    RSA rsa = new RSA();
                    rsa.setPrivateKey(str2);
                    promise.resolve(rsa.decrypt64(str));
                } catch (Exception e) {
                    promise.reject("Error", e.getMessage());
                }
            }
        });
    }

    @ReactMethod
    public void sign(final String str, final String str2, final Promise promise) {
        AsyncTask.execute(new Runnable() { // from class: com.RNRSA.RNRSAModule.6
            @Override // java.lang.Runnable
            public void run() {
                try {
                    RSA rsa = new RSA();
                    rsa.setPrivateKey(str2);
                    promise.resolve(rsa.sign(str, RNRSAModule.SHA512withRSA));
                } catch (Exception e) {
                    promise.reject("Error", e.getMessage());
                }
            }
        });
    }

    @ReactMethod
    public void signWithAlgorithm(final String str, final String str2, final String str3, final Promise promise) {
        AsyncTask.execute(new Runnable() { // from class: com.RNRSA.RNRSAModule.7
            @Override // java.lang.Runnable
            public void run() {
                try {
                    RSA rsa = new RSA();
                    rsa.setPrivateKey(str2);
                    promise.resolve(rsa.sign(str, str3));
                } catch (Exception e) {
                    promise.reject("Error", e.getMessage());
                }
            }
        });
    }

    @ReactMethod
    public void sign64(final String str, final String str2, final Promise promise) {
        AsyncTask.execute(new Runnable() { // from class: com.RNRSA.RNRSAModule.8
            @Override // java.lang.Runnable
            public void run() {
                try {
                    RSA rsa = new RSA();
                    rsa.setPrivateKey(str2);
                    promise.resolve(rsa.sign64(str, RNRSAModule.SHA512withRSA));
                } catch (Exception e) {
                    promise.reject("Error", e.getMessage());
                }
            }
        });
    }

    @ReactMethod
    public void sign64WithAlgorithm(final String str, final String str2, final String str3, final Promise promise) {
        AsyncTask.execute(new Runnable() { // from class: com.RNRSA.RNRSAModule.9
            @Override // java.lang.Runnable
            public void run() {
                try {
                    RSA rsa = new RSA();
                    rsa.setPrivateKey(str2);
                    promise.resolve(rsa.sign64(str, str3));
                } catch (Exception e) {
                    promise.reject("Error", e.getMessage());
                }
            }
        });
    }

    @ReactMethod
    public void verify(final String str, final String str2, final String str3, final Promise promise) {
        AsyncTask.execute(new Runnable() { // from class: com.RNRSA.RNRSAModule.10
            @Override // java.lang.Runnable
            public void run() {
                try {
                    RSA rsa = new RSA();
                    rsa.setPublicKey(str3);
                    promise.resolve(Boolean.valueOf(rsa.verify(str, str2, RNRSAModule.SHA512withRSA)));
                } catch (Exception e) {
                    promise.reject("Error", e.getMessage());
                }
            }
        });
    }

    @ReactMethod
    public void verifyWithAlgorithm(final String str, final String str2, final String str3, final String str4, final Promise promise) {
        AsyncTask.execute(new Runnable() { // from class: com.RNRSA.RNRSAModule.11
            @Override // java.lang.Runnable
            public void run() {
                try {
                    RSA rsa = new RSA();
                    rsa.setPublicKey(str3);
                    promise.resolve(Boolean.valueOf(rsa.verify(str, str2, str4)));
                } catch (Exception e) {
                    promise.reject("Error", e.getMessage());
                }
            }
        });
    }

    @ReactMethod
    public void verify64(final String str, final String str2, final String str3, final Promise promise) {
        AsyncTask.execute(new Runnable() { // from class: com.RNRSA.RNRSAModule.12
            @Override // java.lang.Runnable
            public void run() {
                try {
                    RSA rsa = new RSA();
                    rsa.setPublicKey(str3);
                    promise.resolve(Boolean.valueOf(rsa.verify64(str, str2, RNRSAModule.SHA512withRSA)));
                } catch (Exception e) {
                    promise.reject("Error", e.getMessage());
                }
            }
        });
    }

    @ReactMethod
    public void verify64WithAlgorithm(final String str, final String str2, final String str3, final String str4, final Promise promise) {
        AsyncTask.execute(new Runnable() { // from class: com.RNRSA.RNRSAModule.13
            @Override // java.lang.Runnable
            public void run() {
                try {
                    RSA rsa = new RSA();
                    rsa.setPublicKey(str3);
                    promise.resolve(Boolean.valueOf(rsa.verify64(str, str2, str4)));
                } catch (Exception e) {
                    promise.reject("Error", e.getMessage());
                }
            }
        });
    }
}
