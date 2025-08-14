package com.onfido.hosted.web.module;

import android.graphics.Bitmap;
import android.net.Uri;
import android.webkit.PermissionRequest;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import com.onfido.android.sdk.capture.common.permissions.PermissionResult;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001c\u0010\u0007\u001a\u00020\b2\u0012\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\nH\u0002J\b\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J,\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0012\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\n2\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u001c\u0010\u0018\u001a\u00020\b2\u0012\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\nH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/onfido/hosted/web/module/ChromeClient;", "Landroid/webkit/WebChromeClient;", "permissionDelegate", "Lcom/onfido/hosted/web/module/PermissionDelegate;", "pickerDelegate", "Lcom/onfido/hosted/web/module/PickerDelegate;", "(Lcom/onfido/hosted/web/module/PermissionDelegate;Lcom/onfido/hosted/web/module/PickerDelegate;)V", "askPermissionAndTakePicture", "", "filePathCallback", "Landroid/webkit/ValueCallback;", "", "Landroid/net/Uri;", "getDefaultVideoPoster", "Landroid/graphics/Bitmap;", "onPermissionRequest", "request", "Landroid/webkit/PermissionRequest;", "onShowFileChooser", "", "webView", "Landroid/webkit/WebView;", "fileChooserParams", "Landroid/webkit/WebChromeClient$FileChooserParams;", "takePicture", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class ChromeClient extends WebChromeClient {
    private final PermissionDelegate permissionDelegate;
    private final PickerDelegate pickerDelegate;

    public ChromeClient(PermissionDelegate permissionDelegate, PickerDelegate pickerDelegate) {
        Intrinsics.checkNotNullParameter(permissionDelegate, "permissionDelegate");
        Intrinsics.checkNotNullParameter(pickerDelegate, "pickerDelegate");
        this.permissionDelegate = permissionDelegate;
        this.pickerDelegate = pickerDelegate;
    }

    private final void askPermissionAndTakePicture(final ValueCallback<Uri[]> filePathCallback) {
        if (this.permissionDelegate.hasPermission("android.permission.CAMERA")) {
            takePicture(filePathCallback);
        } else {
            this.permissionDelegate.registerForPermission(new String[]{"android.permission.CAMERA"}, new Function1<PermissionResult, Unit>() { // from class: com.onfido.hosted.web.module.ChromeClient.askPermissionAndTakePicture.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(PermissionResult permissionResult) {
                    invoke2(permissionResult);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(PermissionResult it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    if (Intrinsics.areEqual(it, PermissionResult.Canceled.INSTANCE)) {
                        filePathCallback.onReceiveValue(new Uri[0]);
                    } else if (it instanceof PermissionResult.Granted) {
                        this.takePicture(filePathCallback);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void takePicture(final ValueCallback<Uri[]> filePathCallback) {
        this.pickerDelegate.openTakePicture(new Function1<Uri, Unit>() { // from class: com.onfido.hosted.web.module.ChromeClient.takePicture.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Uri uri) {
                invoke2(uri);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Uri uri) {
                if (uri != null) {
                    filePathCallback.onReceiveValue(new Uri[]{uri});
                } else {
                    filePathCallback.onReceiveValue(new Uri[0]);
                }
            }
        });
    }

    @Override // android.webkit.WebChromeClient
    public Bitmap getDefaultVideoPoster() {
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(10, 10, Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(bitmapCreateBitmap, "createBitmap(...)");
        return bitmapCreateBitmap;
    }

    @Override // android.webkit.WebChromeClient
    public void onPermissionRequest(final PermissionRequest request) {
        String str;
        Intrinsics.checkNotNullParameter(request, "request");
        final ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        String[] resources = request.getResources();
        if (resources != null) {
            for (String str2 : resources) {
                String str3 = "android.webkit.resource.VIDEO_CAPTURE";
                if (Intrinsics.areEqual(str2, "android.webkit.resource.VIDEO_CAPTURE")) {
                    str = "android.permission.CAMERA";
                } else {
                    str3 = "android.webkit.resource.AUDIO_CAPTURE";
                    if (Intrinsics.areEqual(str2, "android.webkit.resource.AUDIO_CAPTURE")) {
                        str = "android.permission.RECORD_AUDIO";
                    }
                }
                arrayList2.add(str);
                arrayList.add(str3);
            }
        }
        ArrayList arrayList3 = new ArrayList();
        for (Object obj : arrayList2) {
            if (!this.permissionDelegate.hasPermission((String) obj)) {
                arrayList3.add(obj);
            }
        }
        if (!arrayList3.isEmpty()) {
            this.permissionDelegate.registerForPermission((String[]) arrayList3.toArray(new String[0]), new Function1<PermissionResult, Unit>() { // from class: com.onfido.hosted.web.module.ChromeClient.onPermissionRequest.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(PermissionResult permissionResult) {
                    invoke2(permissionResult);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(PermissionResult result) {
                    Intrinsics.checkNotNullParameter(result, "result");
                    if (Intrinsics.areEqual(result, PermissionResult.Canceled.INSTANCE)) {
                        request.deny();
                    } else if (result instanceof PermissionResult.Granted) {
                        request.grant((String[]) arrayList.toArray(new String[0]));
                    }
                }
            });
        } else {
            request.grant((String[]) arrayList.toArray(new String[0]));
        }
    }

    @Override // android.webkit.WebChromeClient
    public boolean onShowFileChooser(WebView webView, final ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams) {
        Intrinsics.checkNotNullParameter(webView, "webView");
        Intrinsics.checkNotNullParameter(filePathCallback, "filePathCallback");
        Intrinsics.checkNotNullParameter(fileChooserParams, "fileChooserParams");
        if (fileChooserParams.isCaptureEnabled()) {
            askPermissionAndTakePicture(filePathCallback);
            return true;
        }
        PickerDelegate pickerDelegate = this.pickerDelegate;
        String[] acceptTypes = fileChooserParams.getAcceptTypes();
        Intrinsics.checkNotNullExpressionValue(acceptTypes, "getAcceptTypes(...)");
        pickerDelegate.openDocumentPicker(acceptTypes, new Function1<Uri, Unit>() { // from class: com.onfido.hosted.web.module.ChromeClient.onShowFileChooser.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Uri uri) {
                invoke2(uri);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Uri uri) {
                if (uri != null) {
                    filePathCallback.onReceiveValue(new Uri[]{uri});
                } else {
                    filePathCallback.onReceiveValue(new Uri[0]);
                }
            }
        });
        return true;
    }
}
