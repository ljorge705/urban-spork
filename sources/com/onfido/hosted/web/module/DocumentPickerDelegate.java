package com.onfido.hosted.web.module;

import android.net.Uri;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import io.sentry.protocol.Request;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0012\u001a\u00020\u0007H\u0002J1\u0010\u0013\u001a\u00020\u000f2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0014\u0010\u0015\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\u0004\u0012\u00020\u000f0\u000eH\u0016¢\u0006\u0002\u0010\u0016J\u001e\u0010\u0017\u001a\u00020\u000f2\u0014\u0010\u0015\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\u0004\u0012\u00020\u000f0\u000eH\u0016R\u001c\u0010\u0005\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00070\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R(\u0010\n\u001a\u001c\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\f \b*\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b0\u000b0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\r\u001a\u0018\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000ej\u0004\u0018\u0001`\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u0011\u001a\u0018\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000ej\u0004\u0018\u0001`\u0010X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/onfido/hosted/web/module/DocumentPickerDelegate;", "Lcom/onfido/hosted/web/module/PickerDelegate;", Request.JsonKeys.FRAGMENT, "Landroidx/fragment/app/Fragment;", "(Landroidx/fragment/app/Fragment;)V", "cameraAppLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/net/Uri;", "kotlin.jvm.PlatformType", "capturedPictureUri", "documentPicker", "", "", "documentPickerResultCallback", "Lkotlin/Function1;", "", "Lcom/onfido/hosted/web/module/ResultCallback;", "takePictureResultCallback", "createImageFileUri", "openDocumentPicker", "mimeTypes", "callback", "([Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V", "openTakePicture", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class DocumentPickerDelegate implements PickerDelegate {
    private final ActivityResultLauncher<Uri> cameraAppLauncher;
    private Uri capturedPictureUri;
    private final ActivityResultLauncher<String[]> documentPicker;
    private Function1<? super Uri, Unit> documentPickerResultCallback;
    private final Fragment fragment;
    private Function1<? super Uri, Unit> takePictureResultCallback;

    public DocumentPickerDelegate(Fragment fragment) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        this.fragment = fragment;
        ActivityResultLauncher<Uri> activityResultLauncherRegisterForActivityResult = fragment.registerForActivityResult(new ActivityResultContracts.TakePicture(), new ActivityResultCallback() { // from class: com.onfido.hosted.web.module.DocumentPickerDelegate$$ExternalSyntheticLambda0
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                DocumentPickerDelegate.cameraAppLauncher$lambda$0(this.f$0, ((Boolean) obj).booleanValue());
            }
        });
        Intrinsics.checkNotNullExpressionValue(activityResultLauncherRegisterForActivityResult, "registerForActivityResult(...)");
        this.cameraAppLauncher = activityResultLauncherRegisterForActivityResult;
        ActivityResultLauncher<String[]> activityResultLauncherRegisterForActivityResult2 = fragment.registerForActivityResult(new ActivityResultContracts.OpenDocument(), new ActivityResultCallback() { // from class: com.onfido.hosted.web.module.DocumentPickerDelegate$$ExternalSyntheticLambda1
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                DocumentPickerDelegate.documentPicker$lambda$1(this.f$0, (Uri) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(activityResultLauncherRegisterForActivityResult2, "registerForActivityResult(...)");
        this.documentPicker = activityResultLauncherRegisterForActivityResult2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void cameraAppLauncher$lambda$0(DocumentPickerDelegate this$0, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Function1<? super Uri, Unit> function1 = this$0.takePictureResultCallback;
        if (function1 == null) {
            throw new IllegalStateException("Launched a takePicture with missing result callback".toString());
        }
        Uri uri = this$0.capturedPictureUri;
        if (uri == null) {
            throw new IllegalStateException("Launched takePicture with missing pictureUri".toString());
        }
        if (!z) {
            uri = null;
        }
        function1.invoke(uri);
        this$0.takePictureResultCallback = null;
    }

    private final Uri createImageFileUri() throws IOException {
        String str = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date());
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        File externalFilesDir = this.fragment.requireContext().getExternalFilesDir(null);
        if (externalFilesDir == null) {
            externalFilesDir = this.fragment.requireContext().getFilesDir();
            Intrinsics.checkNotNullExpressionValue(externalFilesDir, "getFilesDir(...)");
        }
        Uri uriForFile = FileProvider.getUriForFile(this.fragment.requireContext(), this.fragment.requireContext().getPackageName() + ".onfido.take_picture.provider", File.createTempFile("JPEG_" + str + '_', ".jpg", externalFilesDir));
        Intrinsics.checkNotNullExpressionValue(uriForFile, "getUriForFile(...)");
        return uriForFile;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void documentPicker$lambda$1(DocumentPickerDelegate this$0, Uri uri) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Function1<? super Uri, Unit> function1 = this$0.documentPickerResultCallback;
        if (function1 == null) {
            throw new IllegalStateException("Launched a picker with missing result callback".toString());
        }
        function1.invoke(uri);
        this$0.documentPickerResultCallback = null;
    }

    @Override // com.onfido.hosted.web.module.PickerDelegate
    public void openDocumentPicker(String[] mimeTypes, Function1<? super Uri, Unit> callback) {
        Intrinsics.checkNotNullParameter(mimeTypes, "mimeTypes");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.documentPickerResultCallback = callback;
        this.documentPicker.launch(mimeTypes);
    }

    @Override // com.onfido.hosted.web.module.PickerDelegate
    public void openTakePicture(Function1<? super Uri, Unit> callback) throws IOException {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.takePictureResultCallback = callback;
        Uri uriCreateImageFileUri = createImageFileUri();
        this.capturedPictureUri = uriCreateImageFileUri;
        this.cameraAppLauncher.launch(uriCreateImageFileUri);
    }
}
