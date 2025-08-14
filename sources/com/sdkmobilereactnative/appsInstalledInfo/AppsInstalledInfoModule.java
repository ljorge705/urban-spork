package com.sdkmobilereactnative.appsInstalledInfo;

import com.clevertap.android.sdk.Constants;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.fivvy.profiler.application.UseCaseService;
import com.fivvy.profiler.domain.models.AppInstalledInfo;

@ReactModule(name = AppsInstalledInfoModule.NAME)
/* loaded from: classes6.dex */
public class AppsInstalledInfoModule extends ReactContextBaseJavaModule {
    public static final String NAME = "AppsInstalledInfoModule";
    private final ReactApplicationContext reactContext;
    private final UseCaseService useCaseService;

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    public AppsInstalledInfoModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.reactContext = reactApplicationContext;
        this.useCaseService = new UseCaseService(reactApplicationContext);
    }

    @ReactMethod
    public void getApps(Promise promise) {
        WritableArray writableArrayCreateArray = Arguments.createArray();
        for (AppInstalledInfo appInstalledInfo : this.useCaseService.createGetInstalledAppsUseCase()) {
            WritableMap writableMapCreateMap = Arguments.createMap();
            writableMapCreateMap.putString("appName", appInstalledInfo.appName);
            writableMapCreateMap.putString("packageName", appInstalledInfo.packageName);
            writableMapCreateMap.putString("versionCode", appInstalledInfo.versionCode);
            writableMapCreateMap.putString("versionName", appInstalledInfo.versionName);
            writableMapCreateMap.putString("installTime", appInstalledInfo.installTime);
            writableMapCreateMap.putString(Constants.KEY_ICON, appInstalledInfo.icon);
            writableMapCreateMap.putString("lastUpdateTime", appInstalledInfo.lastUpdateTime);
            writableMapCreateMap.putString("category", appInstalledInfo.category);
            writableArrayCreateArray.pushMap(writableMapCreateMap);
        }
        promise.resolve(writableArrayCreateArray);
    }
}
