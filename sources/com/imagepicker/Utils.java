package com.imagepicker;

import android.app.Activity;
import android.content.ClipData;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Base64;
import android.webkit.MimeTypeMap;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.exifinterface.media.ExifInterface;
import com.ReactNativeBlobUtil.ReactNativeBlobUtilConst;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.onfido.reactnative.sdk.ReactNativeBridgeUtiles;
import com.reactnativecommunity.clipboard.ClipboardModule;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/* loaded from: classes2.dex */
public class Utils {
    public static String cameraPermissionDescription = "This library does not require Manifest.permission.CAMERA, if you add this permission in manifest then you have to obtain the same.";
    public static String errCameraUnavailable = "camera_unavailable";
    public static String errOthers = "others";
    public static String errPermission = "permission";
    public static String fileNamePrefix = "rn_image_picker_lib_temp_";
    public static String mediaTypePhoto = "photo";
    public static String mediaTypeVideo = "video";

    static boolean isValidRequestCode(int i) {
        switch (i) {
            case ImagePickerModule.REQUEST_LAUNCH_IMAGE_CAPTURE /* 13001 */:
            case ImagePickerModule.REQUEST_LAUNCH_VIDEO_CAPTURE /* 13002 */:
            case ImagePickerModule.REQUEST_LAUNCH_LIBRARY /* 13003 */:
                return true;
            default:
                return false;
        }
    }

    public static File createFile(Context context, String str) throws IOException {
        try {
            File file = new File(context.getCacheDir(), fileNamePrefix + UUID.randomUUID() + "." + str);
            file.createNewFile();
            return file;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Uri createUri(File file, Context context) {
        return FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".imagepickerprovider", file);
    }

    public static void saveToPublicDirectory(Uri uri, Context context, String str) throws IOException {
        Uri uriInsert;
        ContentResolver contentResolver = context.getContentResolver();
        ContentValues contentValues = new ContentValues();
        if (str.equals("video")) {
            contentValues.put("_display_name", UUID.randomUUID().toString());
            contentValues.put("mime_type", contentResolver.getType(uri));
            uriInsert = contentResolver.insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, contentValues);
        } else {
            contentValues.put("_display_name", UUID.randomUUID().toString());
            contentValues.put("mime_type", contentResolver.getType(uri));
            uriInsert = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
        }
        copyUri(uri, uriInsert, contentResolver);
    }

    public static void copyUri(Uri uri, Uri uri2, ContentResolver contentResolver) throws IOException {
        try {
            OutputStream outputStreamOpenOutputStream = contentResolver.openOutputStream(uri2);
            InputStream inputStreamOpenInputStream = contentResolver.openInputStream(uri);
            byte[] bArr = new byte[8192];
            while (true) {
                int i = inputStreamOpenInputStream.read(bArr);
                if (i == -1) {
                    return;
                } else {
                    outputStreamOpenOutputStream.write(bArr, 0, i);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Uri getAppSpecificStorageUri(Uri uri, Context context) throws IOException {
        if (uri == null) {
            return null;
        }
        ContentResolver contentResolver = context.getContentResolver();
        Uri uriFromFile = Uri.fromFile(createFile(context, getFileTypeFromMime(contentResolver.getType(uri))));
        copyUri(uri, uriFromFile, contentResolver);
        return uriFromFile;
    }

    public static boolean isCameraAvailable(Context context) {
        return context.getPackageManager().hasSystemFeature("android.hardware.camera") || context.getPackageManager().hasSystemFeature("android.hardware.camera.any");
    }

    public static void setFrontCamera(Intent intent) {
        intent.putExtra("android.intent.extras.CAMERA_FACING", 0);
        if (Build.VERSION.SDK_INT >= 26) {
            intent.putExtra("android.intent.extra.USE_FRONT_CAMERA", true);
        }
    }

    public static int[] getImageDimensions(Uri uri, Context context) throws FileNotFoundException {
        try {
            InputStream inputStreamOpenInputStream = context.getContentResolver().openInputStream(uri);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(inputStreamOpenInputStream, null, options);
            return new int[]{options.outWidth, options.outHeight};
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new int[]{0, 0};
        }
    }

    static boolean hasPermission(Activity activity) {
        return ActivityCompat.checkSelfPermission(activity, "android.permission.WRITE_EXTERNAL_STORAGE") == 0;
    }

    static String getBase64String(Uri uri, Context context) throws IOException {
        try {
            InputStream inputStreamOpenInputStream = context.getContentResolver().openInputStream(uri);
            byte[] bArr = new byte[8192];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (true) {
                try {
                    int i = inputStreamOpenInputStream.read(bArr);
                    if (i == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, i);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2);
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static Uri resizeImage(Uri uri, Context context, Options options) throws Throwable {
        try {
            int[] imageDimensions = getImageDimensions(uri, context);
            if (!shouldResizeImage(imageDimensions[0], imageDimensions[1], options)) {
                return uri;
            }
            int[] imageDimensBasedOnConstraints = getImageDimensBasedOnConstraints(imageDimensions[0], imageDimensions[1], options);
            InputStream inputStreamOpenInputStream = context.getContentResolver().openInputStream(uri);
            String mimeTypeFromFileUri = getMimeTypeFromFileUri(uri);
            Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeStream(inputStreamOpenInputStream), imageDimensBasedOnConstraints[0], imageDimensBasedOnConstraints[1], true);
            String orientation = getOrientation(uri, context);
            File fileCreateFile = createFile(context, getFileTypeFromMime(mimeTypeFromFileUri));
            bitmapCreateScaledBitmap.compress(getBitmapCompressFormat(mimeTypeFromFileUri), options.quality, context.getContentResolver().openOutputStream(Uri.fromFile(fileCreateFile)));
            setOrientation(fileCreateFile, orientation, context);
            deleteFile(uri);
            return Uri.fromFile(fileCreateFile);
        } catch (Exception e) {
            e.printStackTrace();
            return uri;
        }
    }

    static String getOrientation(Uri uri, Context context) throws IOException {
        return new ExifInterface(context.getContentResolver().openInputStream(uri)).getAttribute(ExifInterface.TAG_ORIENTATION);
    }

    static void setOrientation(File file, String str, Context context) throws Throwable {
        if (str.equals(String.valueOf(1)) || str.equals(String.valueOf(0))) {
            return;
        }
        ExifInterface exifInterface = new ExifInterface(file);
        exifInterface.setAttribute(ExifInterface.TAG_ORIENTATION, str);
        exifInterface.saveAttributes();
    }

    static int[] getImageDimensBasedOnConstraints(int i, int i2, Options options) {
        if (options.maxWidth == 0 || options.maxHeight == 0) {
            return new int[]{i, i2};
        }
        if (options.maxWidth < i) {
            i2 = (int) ((options.maxWidth / i) * i2);
            i = options.maxWidth;
        }
        if (options.maxHeight < i2) {
            i = (int) ((options.maxHeight / i2) * i);
            i2 = options.maxHeight;
        }
        return new int[]{i, i2};
    }

    static double getFileSize(Uri uri, Context context) {
        try {
            return context.getContentResolver().openFileDescriptor(uri, "r").getStatSize();
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0d;
        }
    }

    static boolean shouldResizeImage(int i, int i2, Options options) {
        if ((options.maxWidth == 0 || options.maxHeight == 0) && options.quality == 100) {
            return false;
        }
        return options.maxWidth < i || options.maxHeight < i2 || options.quality != 100;
    }

    static Bitmap.CompressFormat getBitmapCompressFormat(String str) {
        str.hashCode();
        if (str.equals("image/jpeg")) {
            return Bitmap.CompressFormat.JPEG;
        }
        if (str.equals(ClipboardModule.MIMETYPE_PNG)) {
            return Bitmap.CompressFormat.PNG;
        }
        return Bitmap.CompressFormat.JPEG;
    }

    static String getFileTypeFromMime(String str) {
        if (str == null) {
            return "jpg";
        }
        str.hashCode();
        switch (str) {
            case "image/jpeg":
                return "jpg";
            case "image/gif":
                return "gif";
            case "image/png":
                return "png";
            default:
                return MimeTypeMap.getSingleton().getExtensionFromMimeType(str);
        }
    }

    static void deleteFile(Uri uri) {
        new File(uri.getPath()).delete();
    }

    static String getMimeTypeFromFileUri(Uri uri) {
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(uri.toString()));
    }

    public static boolean isCameraPermissionFulfilled(Context context, Activity activity) {
        try {
            String[] strArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 4096).requestedPermissions;
            if (strArr != null && Arrays.asList(strArr).contains("android.permission.CAMERA")) {
                if (ActivityCompat.checkSelfPermission(activity, "android.permission.CAMERA") != 0) {
                    return false;
                }
            }
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return true;
        }
    }

    static boolean isImageType(Uri uri, Context context) {
        return isContentType("image/", uri, context);
    }

    static boolean isVideoType(Uri uri, Context context) {
        return isContentType("video/", uri, context);
    }

    static boolean isContentType(String str, Uri uri, Context context) {
        String mimeType = getMimeType(uri, context);
        if (mimeType != null) {
            return mimeType.contains(str);
        }
        return false;
    }

    static String getMimeType(Uri uri, Context context) {
        if (uri.getScheme().equals("file")) {
            return getMimeTypeFromFileUri(uri);
        }
        return context.getContentResolver().getType(uri);
    }

    static List<Uri> collectUrisFromData(Intent intent) {
        if (intent.getClipData() == null) {
            return Collections.singletonList(intent.getData());
        }
        ClipData clipData = intent.getClipData();
        ArrayList arrayList = new ArrayList(clipData.getItemCount());
        for (int i = 0; i < clipData.getItemCount(); i++) {
            arrayList.add(clipData.getItemAt(i).getUri());
        }
        return arrayList;
    }

    static ReadableMap getImageResponseMap(Uri uri, Options options, Context context) throws FileNotFoundException {
        String lastPathSegment = uri.getLastPathSegment();
        ImageMetadata imageMetadata = new ImageMetadata(uri, context);
        int[] imageDimensions = getImageDimensions(uri, context);
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putString(ReactNativeBlobUtilConst.DATA_ENCODE_URI, uri.toString());
        writableMapCreateMap.putDouble("fileSize", getFileSize(uri, context));
        writableMapCreateMap.putString(ReactNativeBridgeUtiles.KEY_FILE_NAME, lastPathSegment);
        writableMapCreateMap.putString("type", getMimeTypeFromFileUri(uri));
        writableMapCreateMap.putInt("width", imageDimensions[0]);
        writableMapCreateMap.putInt("height", imageDimensions[1]);
        writableMapCreateMap.putString("type", getMimeType(uri, context));
        if (options.includeBase64.booleanValue()) {
            writableMapCreateMap.putString("base64", getBase64String(uri, context));
        }
        if (options.includeExtra.booleanValue()) {
            writableMapCreateMap.putString("timestamp", imageMetadata.getDateTime());
            writableMapCreateMap.putString("id", lastPathSegment);
        }
        return writableMapCreateMap;
    }

    static ReadableMap getVideoResponseMap(Uri uri, Options options, Context context) {
        String lastPathSegment = uri.getLastPathSegment();
        WritableMap writableMapCreateMap = Arguments.createMap();
        VideoMetadata videoMetadata = new VideoMetadata(uri, context);
        writableMapCreateMap.putString(ReactNativeBlobUtilConst.DATA_ENCODE_URI, uri.toString());
        writableMapCreateMap.putDouble("fileSize", getFileSize(uri, context));
        writableMapCreateMap.putInt("duration", videoMetadata.getDuration());
        writableMapCreateMap.putInt("bitrate", videoMetadata.getBitrate());
        writableMapCreateMap.putString(ReactNativeBridgeUtiles.KEY_FILE_NAME, lastPathSegment);
        writableMapCreateMap.putString("type", getMimeType(uri, context));
        writableMapCreateMap.putInt("width", videoMetadata.getWidth());
        writableMapCreateMap.putInt("height", videoMetadata.getHeight());
        if (options.includeExtra.booleanValue()) {
            writableMapCreateMap.putString("timestamp", videoMetadata.getDateTime());
            writableMapCreateMap.putString("id", lastPathSegment);
        }
        return writableMapCreateMap;
    }

    static ReadableMap getResponseMap(List<Uri> list, Options options, Context context) throws IOException, RuntimeException {
        WritableArray writableArrayCreateArray = Arguments.createArray();
        for (int i = 0; i < list.size(); i++) {
            Uri appSpecificStorageUri = list.get(i);
            if (isImageType(appSpecificStorageUri, context)) {
                if (appSpecificStorageUri.getScheme().contains("content")) {
                    appSpecificStorageUri = getAppSpecificStorageUri(appSpecificStorageUri, context);
                }
                writableArrayCreateArray.pushMap(getImageResponseMap(resizeImage(appSpecificStorageUri, context, options), options, context));
            } else if (isVideoType(appSpecificStorageUri, context)) {
                if (appSpecificStorageUri.getScheme().contains("content")) {
                    appSpecificStorageUri = getAppSpecificStorageUri(appSpecificStorageUri, context);
                }
                writableArrayCreateArray.pushMap(getVideoResponseMap(appSpecificStorageUri, options, context));
            } else {
                throw new RuntimeException("Unsupported file type");
            }
        }
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putArray("assets", writableArrayCreateArray);
        return writableMapCreateMap;
    }

    static ReadableMap getErrorMap(String str, String str2) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putString("errorCode", str);
        if (str2 != null) {
            writableMapCreateMap.putString("errorMessage", str2);
        }
        return writableMapCreateMap;
    }

    static ReadableMap getCancelMap() {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putBoolean("didCancel", true);
        return writableMapCreateMap;
    }
}
