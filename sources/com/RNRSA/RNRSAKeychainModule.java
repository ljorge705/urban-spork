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
public class RNRSAKeychainModule extends ReactContextBaseJavaModule {
    private static final String SHA1withRSA = "SHA1withRSA";
    private static final String SHA256withRSA = "SHA256withRSA";
    private static final String SHA512withRSA = "SHA512withRSA";
    private final ReactApplicationContext reactContext;

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "RNRSAKeychain";
    }

    public RNRSAKeychainModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.reactContext = reactApplicationContext;
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    public Map<String, Object> getConstants() {
        HashMap map = new HashMap();
        map.put(SHA256withRSA, SHA256withRSA);
        map.put(SHA512withRSA, SHA512withRSA);
        map.put(SHA1withRSA, SHA1withRSA);
        return map;
    }

    @ReactMethod
    public void generate(String str, Promise promise) {
        generateKeys(str, 2048, promise);
    }

    @ReactMethod
    public void generateKeys(final String str, final int i, final Promise promise) {
        final ReactApplicationContext reactApplicationContext = this.reactContext;
        AsyncTask.execute(new Runnable() { // from class: com.RNRSA.RNRSAKeychainModule.1
            @Override // java.lang.Runnable
            public void run() {
                WritableNativeMap writableNativeMap = new WritableNativeMap();
                try {
                    RSA rsa = new RSA();
                    rsa.generate(str, i, reactApplicationContext);
                    writableNativeMap.putString("public", rsa.getPublicKey());
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
    public void generateCSR(final String str, final String str2, final String str3, final Promise promise) {
        final ReactApplicationContext reactApplicationContext = this.reactContext;
        AsyncTask.execute(new Runnable() { // from class: com.RNRSA.RNRSAKeychainModule.2
            @Override // java.lang.Runnable
            public void run() {
                WritableNativeMap writableNativeMap = new WritableNativeMap();
                try {
                    RSA rsa = new RSA(str);
                    rsa.generateCSR(str2, str3, reactApplicationContext);
                    writableNativeMap.putString("csr", rsa.getCSR());
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
    public void generateCSRWithEC(final String str, final String str2, final int i, final Promise promise) {
        final ReactApplicationContext reactApplicationContext = this.reactContext;
        AsyncTask.execute(new Runnable() { // from class: com.RNRSA.RNRSAKeychainModule.3
            @Override // java.lang.Runnable
            public void run() {
                WritableNativeMap writableNativeMap = new WritableNativeMap();
                try {
                    RSA rsa = new RSA();
                    rsa.generateCSRWithEC(str, str2, i, reactApplicationContext);
                    writableNativeMap.putString("csr", rsa.getCSR());
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
    public void deletePrivateKey(final String str, final Promise promise) {
        AsyncTask.execute(new Runnable() { // from class: com.RNRSA.RNRSAKeychainModule.4
            @Override // java.lang.Runnable
            public void run() {
                try {
                    new RSA(str).deletePrivateKey();
                    promise.resolve(1);
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
        AsyncTask.execute(new Runnable() { // from class: com.RNRSA.RNRSAKeychainModule.5
            @Override // java.lang.Runnable
            public void run() {
                try {
                    promise.resolve(new RSA(str2).encrypt(str));
                } catch (Exception e) {
                    promise.reject("Error", e.getMessage());
                }
            }
        });
    }

    @ReactMethod
    public void encrypt64(final String str, final String str2, final Promise promise) {
        AsyncTask.execute(new Runnable() { // from class: com.RNRSA.RNRSAKeychainModule.6
            @Override // java.lang.Runnable
            public void run() {
                try {
                    promise.resolve(new RSA(str2).encrypt64(str));
                } catch (Exception e) {
                    promise.reject("Error", e.getMessage());
                }
            }
        });
    }

    @ReactMethod
    public void decrypt(final String str, final String str2, final Promise promise) {
        AsyncTask.execute(new Runnable() { // from class: com.RNRSA.RNRSAKeychainModule.7
            @Override // java.lang.Runnable
            public void run() {
                try {
                    promise.resolve(new RSA(str2).decrypt(str));
                } catch (Exception e) {
                    promise.reject("Error", e.getMessage());
                }
            }
        });
    }

    @ReactMethod
    public void decrypt64(final String str, final String str2, final Promise promise) {
        AsyncTask.execute(new Runnable() { // from class: com.RNRSA.RNRSAKeychainModule.8
            @Override // java.lang.Runnable
            public void run() {
                try {
                    promise.resolve(new RSA(str2).decrypt64(str));
                } catch (Exception e) {
                    promise.reject("Error", e.getMessage());
                }
            }
        });
    }

    @ReactMethod
    public void sign(final String str, final String str2, final Promise promise) {
        AsyncTask.execute(new Runnable() { // from class: com.RNRSA.RNRSAKeychainModule.9
            @Override // java.lang.Runnable
            public void run() {
                try {
                    promise.resolve(new RSA(str2).sign(str, RNRSAKeychainModule.SHA512withRSA));
                } catch (Exception e) {
                    promise.reject("Error", e.getMessage());
                }
            }
        });
    }

    @ReactMethod
    public void signWithAlgorithm(final String str, final String str2, final String str3, final Promise promise) {
        AsyncTask.execute(new Runnable() { // from class: com.RNRSA.RNRSAKeychainModule.10
            @Override // java.lang.Runnable
            public void run() {
                try {
                    promise.resolve(new RSA(str2).sign(str, str3));
                } catch (Exception e) {
                    promise.reject("Error", e.getMessage());
                }
            }
        });
    }

    @ReactMethod
    public void sign64(final String str, final String str2, final Promise promise) {
        AsyncTask.execute(new Runnable() { // from class: com.RNRSA.RNRSAKeychainModule.11
            @Override // java.lang.Runnable
            public void run() {
                try {
                    promise.resolve(new RSA(str2).sign64(str, RNRSAKeychainModule.SHA512withRSA));
                } catch (Exception e) {
                    promise.reject("Error", e.getMessage());
                }
            }
        });
    }

    @ReactMethod
    public void sign64WithAlgorithm(final String str, final String str2, final String str3, final Promise promise) {
        AsyncTask.execute(new Runnable() { // from class: com.RNRSA.RNRSAKeychainModule.12
            @Override // java.lang.Runnable
            public void run() {
                try {
                    promise.resolve(new RSA(str2).sign64(str, str3));
                } catch (Exception e) {
                    promise.reject("Error", e.getMessage());
                }
            }
        });
    }

    @ReactMethod
    public void verify(final String str, final String str2, final String str3, final Promise promise) {
        AsyncTask.execute(new Runnable() { // from class: com.RNRSA.RNRSAKeychainModule.13
            @Override // java.lang.Runnable
            public void run() {
                try {
                    promise.resolve(Boolean.valueOf(new RSA(str3).verify(str, str2, RNRSAKeychainModule.SHA512withRSA)));
                } catch (Exception e) {
                    promise.reject("Error", e.getMessage());
                }
            }
        });
    }

    @ReactMethod
    public void verifyWithAlgorithm(final String str, final String str2, final String str3, final String str4, final Promise promise) {
        AsyncTask.execute(new Runnable() { // from class: com.RNRSA.RNRSAKeychainModule.14
            @Override // java.lang.Runnable
            public void run() {
                try {
                    promise.resolve(Boolean.valueOf(new RSA(str3).verify(str, str2, str4)));
                } catch (Exception e) {
                    promise.reject("Error", e.getMessage());
                }
            }
        });
    }

    @ReactMethod
    public void verify64(final String str, final String str2, final String str3, final Promise promise) {
        AsyncTask.execute(new Runnable() { // from class: com.RNRSA.RNRSAKeychainModule.15
            @Override // java.lang.Runnable
            public void run() {
                try {
                    promise.resolve(Boolean.valueOf(new RSA(str3).verify64(str, str2, RNRSAKeychainModule.SHA512withRSA)));
                } catch (Exception e) {
                    promise.reject("Error", e.getMessage());
                }
            }
        });
    }

    @ReactMethod
    public void verify64WithAlgorithm(final String str, final String str2, final String str3, final String str4, final Promise promise) {
        AsyncTask.execute(new Runnable() { // from class: com.RNRSA.RNRSAKeychainModule.16
            @Override // java.lang.Runnable
            public void run() {
                try {
                    promise.resolve(Boolean.valueOf(new RSA(str3).verify64(str, str2, str4)));
                } catch (Exception e) {
                    promise.reject("Error", e.getMessage());
                }
            }
        });
    }

    @ReactMethod
    public void getPublicKey(final String str, final Promise promise) {
        AsyncTask.execute(new Runnable() { // from class: com.RNRSA.RNRSAKeychainModule.17
            @Override // java.lang.Runnable
            public void run() {
                new WritableNativeMap();
                try {
                    String publicKey = new RSA(str).getPublicKey();
                    if (publicKey != null) {
                        promise.resolve(publicKey);
                    } else {
                        promise.reject("Error", "Missing public key for that keyTag");
                    }
                } catch (Exception e) {
                    promise.reject("Error", e.getMessage());
                }
            }
        });
    }
}
