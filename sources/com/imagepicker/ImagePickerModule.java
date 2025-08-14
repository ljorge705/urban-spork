package com.imagepicker;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.module.annotations.ReactModule;
import io.sentry.rrweb.RRWebVideoEvent;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@ReactModule(name = ImagePickerModule.NAME)
/* loaded from: classes2.dex */
public class ImagePickerModule extends ReactContextBaseJavaModule implements ActivityEventListener {
    static final String NAME = "ImagePickerManager";
    public static final int REQUEST_LAUNCH_IMAGE_CAPTURE = 13001;
    public static final int REQUEST_LAUNCH_LIBRARY = 13003;
    public static final int REQUEST_LAUNCH_VIDEO_CAPTURE = 13002;
    Callback callback;
    Uri cameraCaptureURI;
    private Uri fileUri;
    Options options;
    final ReactApplicationContext reactContext;

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    @Override // com.facebook.react.bridge.ActivityEventListener
    public void onNewIntent(Intent intent) {
    }

    public ImagePickerModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.reactContext = reactApplicationContext;
        reactApplicationContext.addActivityEventListener(this);
    }

    @ReactMethod
    public void launchCamera(ReadableMap readableMap, Callback callback) throws IOException {
        Intent intent;
        File fileCreateFile;
        int i;
        if (!Utils.isCameraAvailable(this.reactContext)) {
            callback.invoke(Utils.getErrorMap(Utils.errCameraUnavailable, null));
            return;
        }
        Activity currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            callback.invoke(Utils.getErrorMap(Utils.errOthers, "Activity error"));
            return;
        }
        if (!Utils.isCameraPermissionFulfilled(this.reactContext, currentActivity)) {
            callback.invoke(Utils.getErrorMap(Utils.errOthers, Utils.cameraPermissionDescription));
            return;
        }
        this.callback = callback;
        Options options = new Options(readableMap);
        this.options = options;
        if (options.saveToPhotos.booleanValue() && Build.VERSION.SDK_INT <= 28 && !Utils.hasPermission(currentActivity)) {
            callback.invoke(Utils.getErrorMap(Utils.errPermission, null));
            return;
        }
        if (this.options.mediaType.equals(Utils.mediaTypeVideo)) {
            intent = new Intent("android.media.action.VIDEO_CAPTURE");
            intent.putExtra("android.intent.extra.videoQuality", this.options.videoQuality);
            if (this.options.durationLimit > 0) {
                intent.putExtra("android.intent.extra.durationLimit", this.options.durationLimit);
            }
            fileCreateFile = Utils.createFile(this.reactContext, RRWebVideoEvent.REPLAY_CONTAINER);
            this.cameraCaptureURI = Utils.createUri(fileCreateFile, this.reactContext);
            i = REQUEST_LAUNCH_VIDEO_CAPTURE;
        } else {
            intent = new Intent("android.media.action.IMAGE_CAPTURE");
            fileCreateFile = Utils.createFile(this.reactContext, "jpg");
            this.cameraCaptureURI = Utils.createUri(fileCreateFile, this.reactContext);
            i = REQUEST_LAUNCH_IMAGE_CAPTURE;
        }
        if (this.options.useFrontCamera.booleanValue()) {
            Utils.setFrontCamera(intent);
        }
        this.fileUri = Uri.fromFile(fileCreateFile);
        intent.putExtra("output", this.cameraCaptureURI);
        intent.addFlags(3);
        try {
            currentActivity.startActivityForResult(intent, i);
        } catch (ActivityNotFoundException e) {
            callback.invoke(Utils.getErrorMap(Utils.errOthers, e.getMessage()));
            this.callback = null;
        }
    }

    @ReactMethod
    public void launchImageLibrary(ReadableMap readableMap, Callback callback) {
        Intent intent;
        Activity currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            callback.invoke(Utils.getErrorMap(Utils.errOthers, "Activity error"));
            return;
        }
        this.callback = callback;
        Options options = new Options(readableMap);
        this.options = options;
        int pickImagesMaxLimit = options.selectionLimit;
        boolean z = pickImagesMaxLimit == 1;
        boolean zEquals = this.options.mediaType.equals(Utils.mediaTypePhoto);
        boolean zEquals2 = this.options.mediaType.equals(Utils.mediaTypeVideo);
        if (Build.VERSION.SDK_INT >= 33) {
            intent = new Intent("android.provider.action.PICK_IMAGES");
        } else if (z && (zEquals || zEquals2)) {
            intent = new Intent("android.intent.action.PICK");
        } else {
            intent = new Intent("android.intent.action.GET_CONTENT");
            intent.addCategory("android.intent.category.OPENABLE");
        }
        if (!z) {
            if (Build.VERSION.SDK_INT < 33) {
                intent.putExtra("android.intent.extra.ALLOW_MULTIPLE", true);
            } else if (pickImagesMaxLimit != 1) {
                if (pickImagesMaxLimit == 0) {
                    pickImagesMaxLimit = MediaStore.getPickImagesMaxLimit();
                }
                intent.putExtra("android.provider.extra.PICK_IMAGES_MAX", pickImagesMaxLimit);
            }
        }
        if (zEquals) {
            intent.setType("image/*");
        } else if (zEquals2) {
            intent.setType("video/*");
        } else if (Build.VERSION.SDK_INT < 33) {
            intent.setType("*/*");
            intent.putExtra("android.intent.extra.MIME_TYPES", new String[]{"image/*", "video/*"});
        }
        try {
            currentActivity.startActivityForResult(intent, REQUEST_LAUNCH_LIBRARY);
        } catch (ActivityNotFoundException e) {
            callback.invoke(Utils.getErrorMap(Utils.errOthers, e.getMessage()));
            this.callback = null;
        }
    }

    void onAssetsObtained(List<Uri> list) {
        try {
            try {
                this.callback.invoke(Utils.getResponseMap(list, this.options, this.reactContext));
            } catch (RuntimeException e) {
                this.callback.invoke(Utils.getErrorMap(Utils.errOthers, e.getMessage()));
            }
        } finally {
            this.callback = null;
        }
    }

    @Override // com.facebook.react.bridge.ActivityEventListener
    public void onActivityResult(Activity activity, int i, int i2, Intent intent) throws IOException {
        if (!Utils.isValidRequestCode(i) || this.callback == null) {
            return;
        }
        if (i2 != -1) {
            if (i == 13001) {
                Utils.deleteFile(this.fileUri);
            }
            try {
                this.callback.invoke(Utils.getCancelMap());
                return;
            } catch (RuntimeException e) {
                this.callback.invoke(Utils.getErrorMap(Utils.errOthers, e.getMessage()));
            } finally {
                this.callback = null;
            }
        }
        switch (i) {
            case REQUEST_LAUNCH_IMAGE_CAPTURE /* 13001 */:
                if (this.options.saveToPhotos.booleanValue()) {
                    Utils.saveToPublicDirectory(this.cameraCaptureURI, this.reactContext, "photo");
                }
                onAssetsObtained(Collections.singletonList(this.fileUri));
                return;
            case REQUEST_LAUNCH_VIDEO_CAPTURE /* 13002 */:
                if (this.options.saveToPhotos.booleanValue()) {
                    Utils.saveToPublicDirectory(this.cameraCaptureURI, this.reactContext, "video");
                }
                onAssetsObtained(Collections.singletonList(this.fileUri));
                return;
            case REQUEST_LAUNCH_LIBRARY /* 13003 */:
                onAssetsObtained(Utils.collectUrisFromData(intent));
                return;
            default:
                return;
        }
    }
}
