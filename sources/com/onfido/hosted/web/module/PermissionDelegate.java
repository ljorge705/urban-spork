package com.onfido.hosted.web.module;

import com.onfido.android.sdk.capture.common.permissions.PermissionResult;
import com.onfido.android.sdk.capture.core.OnfidoLauncher;
import io.sentry.protocol.App;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b`\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J>\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\t2!\u0010\n\u001a\u001d\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u00070\u000bH\u0016¢\u0006\u0002\u0010\u0010J+\u0010\u0011\u001a\u00020\u00072!\u0010\n\u001a\u001d\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u00070\u000bH&J\u001b\u0010\u0012\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\tH&¢\u0006\u0002\u0010\u0013¨\u0006\u0014"}, d2 = {"Lcom/onfido/hosted/web/module/PermissionDelegate;", "", "hasPermission", "", "permission", "", "registerForPermission", "", App.JsonKeys.APP_PERMISSIONS, "", "callback", "Lkotlin/Function1;", "Lcom/onfido/android/sdk/capture/common/permissions/PermissionResult;", "Lkotlin/ParameterName;", "name", OnfidoLauncher.KEY_RESULT, "([Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V", "registerForResult", "requestPermission", "([Ljava/lang/String;)V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public interface PermissionDelegate {

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class DefaultImpls {
        public static void registerForPermission(PermissionDelegate permissionDelegate, String[] permissions, Function1<? super PermissionResult, Unit> callback) {
            Intrinsics.checkNotNullParameter(permissions, "permissions");
            Intrinsics.checkNotNullParameter(callback, "callback");
            permissionDelegate.registerForResult(callback);
            permissionDelegate.requestPermission(permissions);
        }
    }

    boolean hasPermission(String permission);

    void registerForPermission(String[] permissions, Function1<? super PermissionResult, Unit> callback);

    void registerForResult(Function1<? super PermissionResult, Unit> callback);

    void requestPermission(String[] permissions);
}
