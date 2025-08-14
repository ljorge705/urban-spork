package com.sha256lib;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* loaded from: classes6.dex */
public class Sha256Module extends ReactContextBaseJavaModule {
    private final ReactApplicationContext reactContext;

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "sha256Lib";
    }

    public Sha256Module(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.reactContext = reactApplicationContext;
    }

    private static byte[] readableArrayToByteArray(ReadableArray readableArray) {
        byte[] bArr = new byte[readableArray.size()];
        for (int i = 0; i < readableArray.size(); i++) {
            bArr[i] = (byte) readableArray.getInt(i);
        }
        return bArr;
    }

    String buildHash(String str, String str2, Integer num) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest messageDigest = MessageDigest.getInstance(str2);
        messageDigest.update(str.getBytes("UTF-8"));
        return String.format("%0" + num.toString() + "x", new BigInteger(1, messageDigest.digest()));
    }

    String buildHashWithBytes(ReadableArray readableArray, String str, Integer num) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest messageDigest = MessageDigest.getInstance(str);
        messageDigest.update(readableArrayToByteArray(readableArray));
        return String.format("%0" + num.toString() + "x", new BigInteger(1, messageDigest.digest()));
    }

    @ReactMethod
    public void sha256(String str, Promise promise) {
        try {
            promise.resolve(buildHash(str, "SHA-256", 64));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            promise.reject("sha256", e.getMessage());
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            promise.reject("sha256", e2.getMessage());
        }
    }

    @ReactMethod
    public void sha256Bytes(ReadableArray readableArray, Promise promise) {
        try {
            promise.resolve(buildHashWithBytes(readableArray, "SHA-256", 64));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            promise.reject("sha256", e.getMessage());
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            promise.reject("sha256", e2.getMessage());
        }
    }

    @ReactMethod
    public void sha1(String str, Promise promise) {
        try {
            promise.resolve(buildHash(str, "SHA-1", 40));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            promise.reject("sha1", e.getMessage());
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            promise.reject("sha1", e2.getMessage());
        }
    }

    @ReactMethod
    public void sha1Bytes(ReadableArray readableArray, Promise promise) {
        try {
            promise.resolve(buildHashWithBytes(readableArray, "SHA-1", 40));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            promise.reject("sha1", e.getMessage());
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            promise.reject("sha1", e2.getMessage());
        }
    }
}
