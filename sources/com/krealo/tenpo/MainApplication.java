package com.krealo.tenpo;

import android.app.Application;
import android.content.Context;
import com.clevertap.android.sdk.ActivityLifecycleCallback;
import com.clevertap.react.CleverTapRnAPI;
import com.facebook.react.PackageList;
import com.facebook.react.ReactApplication;
import com.facebook.react.ReactHost;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.defaults.DefaultReactHost;
import com.facebook.react.defaults.DefaultReactNativeHost;
import com.facebook.soloader.SoLoader;
import com.microsoft.codepush.react.CodePush;
import com.screenprotection.ScreenProtectionPackage;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MainApplication.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\f\u001a\u00020\rH\u0016R\u0014\u0010\u0004\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u000e"}, d2 = {"Lcom/krealo/tenpo/MainApplication;", "Landroid/app/Application;", "Lcom/facebook/react/ReactApplication;", "()V", "reactHost", "Lcom/facebook/react/ReactHost;", "getReactHost", "()Lcom/facebook/react/ReactHost;", "reactNativeHost", "Lcom/facebook/react/ReactNativeHost;", "getReactNativeHost", "()Lcom/facebook/react/ReactNativeHost;", "onCreate", "", "app_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class MainApplication extends Application implements ReactApplication {
    private final ReactNativeHost reactNativeHost = new DefaultReactNativeHost(this) { // from class: com.krealo.tenpo.MainApplication$reactNativeHost$1
        private final boolean isHermesEnabled;
        private final boolean isNewArchEnabled;

        @Override // com.facebook.react.ReactNativeHost
        protected String getJSMainModuleName() {
            return "index";
        }

        @Override // com.facebook.react.ReactNativeHost
        public boolean getUseDeveloperSupport() {
            return false;
        }

        @Override // com.facebook.react.defaults.DefaultReactNativeHost
        /* renamed from: isNewArchEnabled, reason: from getter */
        protected boolean getIsNewArchEnabled() {
            return this.isNewArchEnabled;
        }

        {
            super(this);
            this.isHermesEnabled = true;
        }

        @Override // com.facebook.react.ReactNativeHost
        protected List<ReactPackage> getPackages() {
            ArrayList<ReactPackage> packages = new PackageList(this).getPackages();
            packages.add(new ScreenProtectionPackage());
            Intrinsics.checkNotNullExpressionValue(packages, "apply(...)");
            return packages;
        }

        @Override // com.facebook.react.defaults.DefaultReactNativeHost
        protected Boolean isHermesEnabled() {
            return Boolean.valueOf(this.isHermesEnabled);
        }

        @Override // com.facebook.react.ReactNativeHost
        protected String getJSBundleFile() {
            String jSBundleFile = CodePush.getJSBundleFile();
            Intrinsics.checkNotNullExpressionValue(jSBundleFile, "getJSBundleFile(...)");
            return jSBundleFile;
        }
    };

    @Override // com.facebook.react.ReactApplication
    public ReactNativeHost getReactNativeHost() {
        return this.reactNativeHost;
    }

    @Override // com.facebook.react.ReactApplication
    public ReactHost getReactHost() {
        Context applicationContext = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        return DefaultReactHost.getDefaultReactHost(applicationContext, getReactNativeHost());
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        MainApplication mainApplication = this;
        SoLoader.init((Context) mainApplication, false);
        ActivityLifecycleCallback.register(this);
        CleverTapRnAPI.initReactNativeIntegration(mainApplication);
        new RootedVerification(mainApplication).evaluateRootedDevice();
    }
}
