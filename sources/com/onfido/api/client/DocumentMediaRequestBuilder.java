package com.onfido.api.client;

import com.onfido.api.client.data.SdkUploadMetaData;
import java.util.HashMap;
import java.util.Map;
import kotlinx.serialization.json.JsonElementKt;
import kotlinx.serialization.json.JsonObject;
import okhttp3.MultipartBody;

/* loaded from: classes6.dex */
public class DocumentMediaRequestBuilder extends MultiPartRequestBuilder {
    private static final String KEY_DOCUMENT_SIDE = "sdk_selected_document_side";
    private static final String KEY_DOCUMENT_TYPE = "sdk_selected_document_type";
    private static final String KEY_MEDIA_DATA = "binary_media";
    private static final String KEY_MEDIA_TYPE = "media_type";
    private static final String VALIDATE = "sdk_validations";

    DocumentMediaRequestBuilder(String str, String str2) {
        super(str, str2);
    }

    public MultipartBody.Builder setMultipartRequestBody(String str, String str2, byte[] bArr, String str3, String str4, String str5, Map<ValidationType, ValidationLevel> map, SdkUploadMetaData sdkUploadMetaData) {
        setFile(str, str2, bArr, KEY_MEDIA_DATA);
        setFormData(KEY_MEDIA_TYPE, str3);
        if (str4 != null) {
            setFormData(KEY_DOCUMENT_SIDE, str4);
        }
        if (str5 != null) {
            setFormData(KEY_DOCUMENT_TYPE, str5);
        }
        setValidate(map);
        setSdkMetadata(sdkUploadMetaData);
        return super.getBuilder();
    }

    private void setValidate(Map<ValidationType, ValidationLevel> map) {
        HashMap map2 = new HashMap();
        if (map != null) {
            for (Map.Entry<ValidationType, ValidationLevel> entry : map.entrySet()) {
                map2.put(entry.getKey().getId(), JsonElementKt.JsonPrimitive(entry.getValue().getId()));
            }
        }
        setFormData(VALIDATE, new JsonObject(map2).toString());
    }
}
