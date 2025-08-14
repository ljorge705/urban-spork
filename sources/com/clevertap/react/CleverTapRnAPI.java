package com.clevertap.react;

import android.content.Context;
import android.net.Uri;
import com.ReactNativeBlobUtil.ReactNativeBlobUtilConst;
import com.clevertap.android.sdk.CleverTapAPI;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CleverTapRnAPI.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0012\u0010\u0007\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0007¨\u0006\n"}, d2 = {"Lcom/clevertap/react/CleverTapRnAPI;", "", "()V", "initReactNativeIntegration", "", "context", "Landroid/content/Context;", "setInitialUri", ReactNativeBlobUtilConst.DATA_ENCODE_URI, "Landroid/net/Uri;", "clevertap-react-native_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class CleverTapRnAPI {
    public static final CleverTapRnAPI INSTANCE = new CleverTapRnAPI();

    private CleverTapRnAPI() {
    }

    @JvmStatic
    public static final void initReactNativeIntegration(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        CleverTapAPI defaultInstance = CleverTapAPI.getDefaultInstance(context);
        if (defaultInstance != null) {
            CleverTapListenerProxy.INSTANCE.attachToInstance(defaultInstance);
        }
    }

    @JvmStatic
    public static final void setInitialUri(Uri uri) {
        CleverTapModuleImpl.setInitialUri(uri);
    }
}
