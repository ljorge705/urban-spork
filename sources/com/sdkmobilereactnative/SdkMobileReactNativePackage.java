package com.sdkmobilereactnative;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import com.sdkmobilereactnative.appUsage.AppUsageModule;
import com.sdkmobilereactnative.appsInstalledInfo.AppsInstalledInfoModule;
import com.sdkmobilereactnative.deviceInfo.DeviceInfoModule;
import com.sdkmobilereactnative.initContextualDataCollection.InitContextualDataCollectionModule;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes6.dex */
public class SdkMobileReactNativePackage implements ReactPackage {
    @Override // com.facebook.react.ReactPackage
    public List<NativeModule> createNativeModules(ReactApplicationContext reactApplicationContext) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new AppsInstalledInfoModule(reactApplicationContext));
        arrayList.add(new AppUsageModule(reactApplicationContext));
        arrayList.add(new DeviceInfoModule(reactApplicationContext));
        arrayList.add(new InitContextualDataCollectionModule(reactApplicationContext));
        return arrayList;
    }

    @Override // com.facebook.react.ReactPackage
    public List<ViewManager> createViewManagers(ReactApplicationContext reactApplicationContext) {
        return Collections.emptyList();
    }
}
