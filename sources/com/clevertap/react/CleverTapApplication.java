package com.clevertap.react;

import com.clevertap.android.sdk.ActivityLifecycleCallback;
import com.clevertap.android.sdk.Application;
import kotlin.Metadata;

/* compiled from: CleverTapApplication.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lcom/clevertap/react/CleverTapApplication;", "Lcom/clevertap/android/sdk/Application;", "()V", "onCreate", "", "clevertap-react-native_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes5.dex */
public class CleverTapApplication extends Application {
    @Override // com.clevertap.android.sdk.Application, android.app.Application
    public void onCreate() {
        ActivityLifecycleCallback.register(this);
        super.onCreate();
        CleverTapRnAPI.initReactNativeIntegration(this);
    }
}
