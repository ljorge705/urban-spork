package com.onfido.reactnative.sdk;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.onfido.android.sdk.capture.config.DocumentMetadata;
import com.onfido.android.sdk.capture.config.MediaFile;
import com.onfido.android.sdk.capture.config.MediaResult;
import com.onfido.android.sdk.capture.ui.CaptureType;
import java.lang.reflect.Field;
import java.util.Arrays;

/* compiled from: ReactNativeBridgeUtils.java */
/* loaded from: classes6.dex */
class ReactNativeBridgeUtiles {
    public static final String KEY_CAPTURE_TYPE = "captureType";
    public static final String KEY_DOCUMENT_ISSUING_COUNTRY = "issuingCountry";
    public static final String KEY_DOCUMENT_SIDE = "side";
    public static final String KEY_DOCUMENT_TYPE = "type";
    public static final String KEY_FILE_DATA = "fileData";
    public static final String KEY_FILE_NAME = "fileName";
    public static final String KEY_FILE_TYPE = "fileType";

    ReactNativeBridgeUtiles() {
    }

    public static WritableMap convertPublicFieldsToWritableMap(Object obj) throws IllegalAccessException, SecurityException, IllegalArgumentException {
        WritableMap writableMapCreateMap = Arguments.createMap();
        for (Field field : obj.getClass().getFields()) {
            String name = field.getName();
            Object obj2 = field.get(obj);
            if (obj2 != null && !(obj2 instanceof Iterable)) {
                if (obj2 instanceof Boolean) {
                    writableMapCreateMap.putBoolean(name, ((Boolean) obj2).booleanValue());
                } else if (obj2 instanceof Integer) {
                    writableMapCreateMap.putInt(name, ((Integer) obj2).intValue());
                } else if (obj2 instanceof Double) {
                    writableMapCreateMap.putDouble(name, ((Double) obj2).doubleValue());
                } else if (obj2 instanceof String) {
                    writableMapCreateMap.putString(name, (String) obj2);
                } else if (obj2 instanceof Object) {
                    writableMapCreateMap.putMap(name, convertPublicFieldsToWritableMap(obj2));
                }
            }
        }
        return writableMapCreateMap;
    }

    public static WritableMap getMediaResultMap(MediaResult mediaResult) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        if (mediaResult instanceof MediaResult.DocumentResult) {
            MediaResult.DocumentResult documentResult = (MediaResult.DocumentResult) mediaResult;
            writableMapCreateMap.putString(KEY_CAPTURE_TYPE, CaptureType.DOCUMENT.name());
            MediaFile fileData = documentResult.getFileData();
            writableMapCreateMap.putString(KEY_FILE_DATA, Arrays.toString(fileData.getFileData()));
            writableMapCreateMap.putString(KEY_FILE_TYPE, fileData.getFileType());
            writableMapCreateMap.putString(KEY_FILE_NAME, fileData.getFileName());
            DocumentMetadata documentMetadata = documentResult.getDocumentMetadata();
            writableMapCreateMap.putString("type", documentMetadata.getType());
            writableMapCreateMap.putString(KEY_DOCUMENT_SIDE, documentMetadata.getSide());
            writableMapCreateMap.putString(KEY_DOCUMENT_ISSUING_COUNTRY, documentMetadata.getIssuingCountry());
        } else if (mediaResult instanceof MediaResult.LivenessResult) {
            MediaFile fileData2 = ((MediaResult.LivenessResult) mediaResult).getFileData();
            writableMapCreateMap.putString(KEY_FILE_DATA, Arrays.toString(fileData2.getFileData()));
            writableMapCreateMap.putString(KEY_FILE_TYPE, fileData2.getFileType());
            writableMapCreateMap.putString(KEY_FILE_NAME, fileData2.getFileName());
            writableMapCreateMap.putString(KEY_CAPTURE_TYPE, CaptureType.VIDEO.name());
        } else if (mediaResult instanceof MediaResult.SelfieResult) {
            MediaFile fileData3 = ((MediaResult.SelfieResult) mediaResult).getFileData();
            writableMapCreateMap.putString(KEY_FILE_DATA, Arrays.toString(fileData3.getFileData()));
            writableMapCreateMap.putString(KEY_FILE_TYPE, fileData3.getFileType());
            writableMapCreateMap.putString(KEY_FILE_NAME, fileData3.getFileName());
            writableMapCreateMap.putString(KEY_CAPTURE_TYPE, CaptureType.FACE.name());
        }
        return writableMapCreateMap;
    }
}
