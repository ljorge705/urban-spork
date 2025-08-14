package com.sdkmobilereactnative.appUsage;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.facebook.hermes.intl.Constants;
import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.fivvy.profiler.application.UseCaseService;
import com.fivvy.profiler.domain.models.AppUsageInfo;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.util.List;

/* loaded from: classes6.dex */
public class AppUsageModule extends ReactContextBaseJavaModule implements ActivityEventListener, LifecycleEventListener {
    private final ReactApplicationContext reactContext;
    private UseCaseService useCaseFactory;

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "AppUsageModule";
    }

    @Override // com.facebook.react.bridge.ActivityEventListener
    public void onActivityResult(Activity activity, int i, int i2, Intent intent) {
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
    }

    @Override // com.facebook.react.bridge.ActivityEventListener
    public void onNewIntent(Intent intent) {
    }

    public AppUsageModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.reactContext = reactApplicationContext;
        reactApplicationContext.addActivityEventListener(this);
        reactApplicationContext.addLifecycleEventListener(this);
        Activity currentActivity = reactApplicationContext.getCurrentActivity();
        if (currentActivity != null) {
            this.useCaseFactory = new UseCaseService(currentActivity);
        }
    }

    @ReactMethod
    public void getAppUsage(int i, Promise promise) {
        try {
            List<AppUsageInfo> listCreateGetAppUsageUseCase = this.useCaseFactory.createGetAppUsageUseCase(i);
            WritableArray writableArrayCreateArray = Arguments.createArray();
            for (AppUsageInfo appUsageInfo : listCreateGetAppUsageUseCase) {
                WritableMap writableMapCreateMap = Arguments.createMap();
                writableMapCreateMap.putString("appName", appUsageInfo.getAppName());
                writableMapCreateMap.putString("packageName", appUsageInfo.getPackageName());
                writableMapCreateMap.putDouble(Constants.COLLATION_OPTION_USAGE, appUsageInfo.getUsage());
                writableArrayCreateArray.pushMap(writableMapCreateMap);
            }
            if (writableArrayCreateArray.size() == 0) {
                promise.resolve(null);
            }
            promise.resolve(writableArrayCreateArray);
        } catch (Exception e) {
            promise.reject("APP_USAGE_ERROR", e.getMessage());
        }
    }

    @ReactMethod
    public void openUsageAccessSettings(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, Promise promise) throws FileNotFoundException {
        try {
            byte[] bytes = str4.getBytes();
            if (!str4.isEmpty()) {
                int identifier = this.reactContext.getResources().getIdentifier(str4, "drawable", this.reactContext.getPackageName());
                if (identifier == 0) {
                    throw new FileNotFoundException("Drawable " + str4 + " not found.");
                }
                Bitmap bitmapDecodeResource = BitmapFactory.decodeResource(this.reactContext.getResources(), identifier);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmapDecodeResource.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                bytes = byteArrayOutputStream.toByteArray();
                bitmapDecodeResource.recycle();
            }
            this.useCaseFactory.createOpenUsageSettingsUseCase(str, str2, str3, bytes, str5, str6, str7, str8);
            promise.resolve(true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            promise.reject("File not found", e);
        } catch (Exception e2) {
            e2.printStackTrace();
            promise.reject("Exception", e2);
        }
    }

    @ReactMethod
    public void openUsageAccessSettingsDirectly(Promise promise) {
        try {
            this.useCaseFactory.createOpenUsageSettingsDirectlyUseCase();
            promise.resolve(true);
        } catch (Exception e) {
            e.printStackTrace();
            promise.reject("Exception", e);
        }
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
        Activity currentActivity = this.reactContext.getCurrentActivity();
        if (currentActivity != null) {
            this.useCaseFactory = new UseCaseService(currentActivity);
        }
    }
}
