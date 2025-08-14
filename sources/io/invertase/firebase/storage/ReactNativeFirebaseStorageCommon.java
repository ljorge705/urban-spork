package io.invertase.firebase.storage;

import android.net.Uri;
import android.os.Environment;
import android.webkit.MimeTypeMap;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageException;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import io.invertase.firebase.app.ReactNativeFirebaseApp;
import io.invertase.firebase.common.ReactNativeFirebaseModule;
import io.invertase.firebase.common.SharedUtils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import javax.annotation.Nullable;

/* loaded from: classes6.dex */
class ReactNativeFirebaseStorageCommon {
    private static final String CODE_BUCKET_NOT_FOUND = "bucket-not-found";
    static final String CODE_CANCELLED = "cancelled";
    private static final String CODE_FILE_NOT_FOUND = "file-not-found";
    private static final String CODE_NON_MATCHING_CHECKSUM = "non-matching-checksum";
    private static final String CODE_OBJECT_NOT_FOUND = "object-not-found";
    private static final String CODE_PROJECT_NOT_FOUND = "project-not-found";
    private static final String CODE_QUOTA_EXCEEDED = "quota-exceeded";
    private static final String CODE_RETRY_LIMIT_EXCEEDED = "retry-limit-exceeded";
    private static final String CODE_UNAUTHENTICATED = "unauthenticated";
    private static final String CODE_UNAUTHORIZED = "unauthorized";
    private static final String KEY_BUCKET = "bucket";
    private static final String KEY_CACHE_CONTROL = "cacheControl";
    private static final String KEY_CONTENT_DISPOSITION = "contentDisposition";
    private static final String KEY_CONTENT_ENCODING = "contentEncoding";
    private static final String KEY_CONTENT_LANG = "contentLanguage";
    private static final String KEY_CONTENT_TYPE = "contentType";
    private static final String KEY_CUSTOM_META = "customMetadata";
    private static final String KEY_FULL_PATH = "fullPath";
    private static final String KEY_GENERATION = "generation";
    private static final String KEY_MD5_HASH = "md5Hash";
    private static final String KEY_META_GENERATION = "metageneration";
    private static final String KEY_NAME = "name";
    private static final String KEY_SIZE = "size";
    private static final String KEY_TIME_CREATED = "timeCreated";
    private static final String KEY_UPDATED = "updated";
    static final String STATUS_CANCELLED = "cancelled";
    static final String STATUS_ERROR = "error";
    private static final String STATUS_PAUSED = "paused";
    private static final String STATUS_RUNNING = "running";
    private static final String STATUS_SUCCESS = "success";
    private static final String STATUS_UNKNOWN = "unknown";

    ReactNativeFirebaseStorageCommon() {
    }

    static String getTaskStatus(@Nullable StorageTask<?> storageTask) {
        return storageTask == null ? "unknown" : storageTask.isInProgress() ? STATUS_RUNNING : storageTask.isPaused() ? STATUS_PAUSED : (storageTask.isSuccessful() || storageTask.isComplete()) ? "success" : storageTask.isCanceled() ? "cancelled" : storageTask.getException() != null ? "error" : "unknown";
    }

    static StorageMetadata buildMetadataFromMap(ReadableMap readableMap, @Nullable Uri uri, @Nullable StorageMetadata storageMetadata) {
        StorageMetadata.Builder builder = new StorageMetadata.Builder();
        String mimeTypeFromExtension = null;
        if (readableMap != null) {
            if (readableMap.hasKey(KEY_CUSTOM_META) || storageMetadata != null) {
                HashMap map = new HashMap();
                ReadableMap map2 = readableMap.getMap(KEY_CUSTOM_META);
                HashMap map3 = new HashMap();
                if (storageMetadata != null) {
                    for (String str : storageMetadata.getCustomMetadataKeys()) {
                        map.put(str, storageMetadata.getCustomMetadata(str));
                        map3.put(str, storageMetadata.getCustomMetadata(str));
                    }
                }
                if (map2 != null) {
                    map.putAll(map2.toHashMap());
                }
                for (Map.Entry entry : map.entrySet()) {
                    builder.setCustomMetadata((String) entry.getKey(), (String) entry.getValue());
                    if (map2 == null || !map2.hasKey((String) entry.getKey())) {
                        builder.setCustomMetadata((String) entry.getKey(), null);
                    }
                }
            }
            if (readableMap.hasKey(KEY_CACHE_CONTROL)) {
                builder.setCacheControl(readableMap.getString(KEY_CACHE_CONTROL));
            }
            if (readableMap.hasKey(KEY_CONTENT_ENCODING)) {
                builder.setContentEncoding(readableMap.getString(KEY_CONTENT_ENCODING));
            }
            if (readableMap.hasKey(KEY_CONTENT_LANG)) {
                builder.setContentLanguage(readableMap.getString(KEY_CONTENT_LANG));
            }
            if (readableMap.hasKey(KEY_CONTENT_DISPOSITION)) {
                builder.setContentDisposition(readableMap.getString(KEY_CONTENT_DISPOSITION));
            }
        }
        if (readableMap != null && readableMap.hasKey("contentType")) {
            builder.setContentType(readableMap.getString("contentType"));
        } else if (uri != null) {
            String scheme = uri.getScheme();
            if (scheme != null && scheme.equals("content")) {
                mimeTypeFromExtension = ReactNativeFirebaseApp.getApplicationContext().getContentResolver().getType(uri);
            }
            if (mimeTypeFromExtension == null) {
                mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(uri.toString()).toLowerCase(Locale.ROOT));
            }
            if (mimeTypeFromExtension != null) {
                builder.setContentType(mimeTypeFromExtension);
            }
        }
        return builder.build();
    }

    static WritableMap getMetadataAsMap(@Nullable StorageMetadata storageMetadata) {
        if (storageMetadata == null) {
            return null;
        }
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putString(KEY_BUCKET, storageMetadata.getBucket());
        writableMapCreateMap.putString(KEY_GENERATION, storageMetadata.getGeneration());
        writableMapCreateMap.putString(KEY_META_GENERATION, storageMetadata.getMetadataGeneration());
        writableMapCreateMap.putString(KEY_FULL_PATH, storageMetadata.getPath());
        writableMapCreateMap.putString("name", storageMetadata.getName());
        writableMapCreateMap.putDouble("size", storageMetadata.getSizeBytes());
        writableMapCreateMap.putString(KEY_TIME_CREATED, SharedUtils.timestampToUTC(storageMetadata.getCreationTimeMillis() / 1000));
        writableMapCreateMap.putString(KEY_UPDATED, SharedUtils.timestampToUTC(storageMetadata.getUpdatedTimeMillis() / 1000));
        writableMapCreateMap.putString(KEY_MD5_HASH, storageMetadata.getMd5Hash());
        if (storageMetadata.getCacheControl() != null && storageMetadata.getCacheControl().length() > 0) {
            writableMapCreateMap.putString(KEY_CACHE_CONTROL, storageMetadata.getCacheControl());
        }
        if (storageMetadata.getContentLanguage() != null && storageMetadata.getContentLanguage().length() > 0) {
            writableMapCreateMap.putString(KEY_CONTENT_LANG, storageMetadata.getContentLanguage());
        }
        if (storageMetadata.getContentDisposition() != null && storageMetadata.getContentDisposition().length() > 0) {
            writableMapCreateMap.putString(KEY_CONTENT_DISPOSITION, storageMetadata.getContentDisposition());
        }
        if (storageMetadata.getContentEncoding() != null && storageMetadata.getContentEncoding().length() > 0) {
            writableMapCreateMap.putString(KEY_CONTENT_ENCODING, storageMetadata.getContentEncoding());
        }
        if (storageMetadata.getContentType() != null && storageMetadata.getContentType().length() > 0) {
            writableMapCreateMap.putString("contentType", storageMetadata.getContentType());
        }
        if (storageMetadata.getCustomMetadataKeys().size() > 0) {
            WritableMap writableMapCreateMap2 = Arguments.createMap();
            for (String str : storageMetadata.getCustomMetadataKeys()) {
                writableMapCreateMap2.putString(str, storageMetadata.getCustomMetadata(str));
            }
            writableMapCreateMap.putMap(KEY_CUSTOM_META, writableMapCreateMap2);
        }
        return writableMapCreateMap;
    }

    static WritableMap getListResultAsMap(ListResult listResult) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putString("nextPageToken", listResult.getPageToken());
        WritableArray writableArrayCreateArray = Arguments.createArray();
        WritableArray writableArrayCreateArray2 = Arguments.createArray();
        Iterator<StorageReference> it = listResult.getItems().iterator();
        while (it.hasNext()) {
            writableArrayCreateArray.pushString(it.next().getPath());
        }
        Iterator<StorageReference> it2 = listResult.getPrefixes().iterator();
        while (it2.hasNext()) {
            writableArrayCreateArray2.pushString(it2.next().getPath());
        }
        writableMapCreateMap.putArray(FirebaseAnalytics.Param.ITEMS, writableArrayCreateArray);
        writableMapCreateMap.putArray("prefixes", writableArrayCreateArray2);
        return writableMapCreateMap;
    }

    static String[] getExceptionCodeAndMessage(@Nullable Exception exc) {
        String message;
        String str;
        String str2 = "unknown";
        if (exc != null) {
            message = exc.getMessage();
            if (exc instanceof StorageException) {
                StorageException storageException = (StorageException) exc;
                Throwable cause = storageException.getCause();
                int errorCode = storageException.getErrorCode();
                if (errorCode == -13040) {
                    str = "cancelled";
                    message = "User cancelled the operation.";
                } else if (errorCode == -13031) {
                    str = CODE_NON_MATCHING_CHECKSUM;
                    message = "File on the client does not match the checksum of the file received by the server.";
                } else if (errorCode == -13030) {
                    str = CODE_RETRY_LIMIT_EXCEEDED;
                    message = "The maximum time limit on an operation (upload, download, delete, etc.) has been exceeded.";
                } else if (errorCode == -13021) {
                    str = CODE_UNAUTHORIZED;
                    message = "User is not authorized to perform the desired action.";
                } else if (errorCode != -13020) {
                    switch (errorCode) {
                        case StorageException.ERROR_QUOTA_EXCEEDED /* -13013 */:
                            str = CODE_QUOTA_EXCEEDED;
                            message = "Quota on your Firebase Storage bucket has been exceeded.";
                            break;
                        case StorageException.ERROR_PROJECT_NOT_FOUND /* -13012 */:
                            str = CODE_PROJECT_NOT_FOUND;
                            message = "No project is configured for Firebase Storage.";
                            break;
                        case StorageException.ERROR_BUCKET_NOT_FOUND /* -13011 */:
                            str = CODE_BUCKET_NOT_FOUND;
                            message = "No bucket is configured for Firebase Storage.";
                            break;
                        case StorageException.ERROR_OBJECT_NOT_FOUND /* -13010 */:
                            message = "No object exists at the desired reference.";
                            str = CODE_OBJECT_NOT_FOUND;
                            break;
                        default:
                            str = "unknown";
                            break;
                    }
                } else {
                    str = CODE_UNAUTHENTICATED;
                    message = "User is unauthenticated. Authenticate and try again.";
                }
                if (!str.equals("unknown") || cause == null) {
                    str2 = str;
                } else if (cause.getMessage().contains("No such file or directory")) {
                    str2 = CODE_FILE_NOT_FOUND;
                    message = "The local file specified does not exist on the device.";
                } else if (cause.getMessage().contains("Not Found.  Could not get object")) {
                    message = "No object exists at the desired reference.";
                    str2 = CODE_OBJECT_NOT_FOUND;
                } else {
                    message = cause.getMessage();
                    str2 = str;
                }
            }
        } else {
            message = "An unknown error has occurred.";
        }
        return new String[]{str2, message};
    }

    static void promiseRejectStorageException(Promise promise, @Nullable Exception exc) {
        String[] exceptionCodeAndMessage = getExceptionCodeAndMessage(exc);
        ReactNativeFirebaseModule.rejectPromiseWithCodeAndMessage(promise, exceptionCodeAndMessage[0], exceptionCodeAndMessage[1]);
    }

    static boolean isExternalStorageWritable() {
        boolean z;
        boolean z2;
        String externalStorageState = Environment.getExternalStorageState();
        if ("mounted".equals(externalStorageState)) {
            z = true;
        } else {
            if ("mounted_ro".equals(externalStorageState)) {
                z2 = false;
                z = true;
                return z && z2;
            }
            z = false;
        }
        z2 = z;
        if (z) {
            return false;
        }
    }
}
