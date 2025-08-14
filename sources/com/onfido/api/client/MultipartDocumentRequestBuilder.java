package com.onfido.api.client;

import com.onfido.api.client.data.DocType;
import com.onfido.api.client.data.InternalDocSide;
import com.onfido.api.client.data.PoaDocumentType;
import com.onfido.api.client.data.SdkUploadMetaData;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import kotlinx.serialization.json.JsonElementKt;
import kotlinx.serialization.json.JsonObject;
import okhttp3.MultipartBody;

/* loaded from: classes6.dex */
class MultipartDocumentRequestBuilder extends MultiPartRequestBuilder {
    private static final String DOCUMENT_ID_KEY = "document_id";
    private static final String FILE_TYPE_KEY = "type";
    private static final String FILE_TYPE_MP4 = "video/mp4";
    private static final String ISSUING_COUNTRY = "issuing_country";
    private static final String MEDIA_TYPE = "media_type";
    private static final String SIDE = "side";
    private static final String VALIDATE = "sdk_validations";

    MultipartDocumentRequestBuilder(String str, String str2) {
        super(str, str2);
    }

    public MultipartBody.Builder setMultipartRequestBody(String str, DocType docType, String str2, byte[] bArr, Map<ValidationType, ValidationLevel> map, InternalDocSide internalDocSide, String str3, SdkUploadMetaData sdkUploadMetaData, String str4) {
        setProperties(str, docType, str2, bArr, map, internalDocSide, str3, str4);
        setSdkMetadata(sdkUploadMetaData);
        return super.getBuilder();
    }

    public MultipartBody.Builder setPoaMultipartRequestBody(String str, PoaDocumentType poaDocumentType, String str2, byte[] bArr, String str3, SdkUploadMetaData sdkUploadMetaData) {
        setPoaProperties(str, poaDocumentType, str2, bArr, str3, sdkUploadMetaData);
        return super.getBuilder();
    }

    public MultipartBody.Builder setMultipartRequestBody(String str, String str2, SdkUploadMetaData sdkUploadMetaData, InternalDocSide internalDocSide, DocType docType, String str3) {
        setDocumentId(str);
        setSdkMetadata(sdkUploadMetaData);
        setDocumentType(docType);
        setSide(internalDocSide);
        if (str3 != null && !str3.isEmpty()) {
            setIssuingCountry(str3);
        }
        setFile(new File(str2), "video/mp4");
        return super.getBuilder();
    }

    private void setProperties(String str, DocType docType, String str2, byte[] bArr, Map<ValidationType, ValidationLevel> map, InternalDocSide internalDocSide, String str3, String str4) {
        setFile(str, str2, bArr);
        setDocumentType(docType);
        setValidate(map);
        if (internalDocSide != null) {
            setSide(internalDocSide);
        }
        if (str3 != null && !str3.isEmpty()) {
            setIssuingCountry(str3);
        }
        if (str4 == null || str4.isEmpty()) {
            return;
        }
        setMediaType(str4);
    }

    private void setPoaProperties(String str, PoaDocumentType poaDocumentType, String str2, byte[] bArr, String str3, SdkUploadMetaData sdkUploadMetaData) {
        setFile(str, str2, bArr);
        setPoaDocumentType(poaDocumentType);
        if (str3 != null) {
            setIssuingCountry(str3);
        }
        setSdkMetadata(sdkUploadMetaData);
    }

    private void setDocumentId(String str) {
        setFormData(DOCUMENT_ID_KEY, str);
    }

    private void setDocumentType(DocType docType) {
        setFormData("type", docType.getId());
    }

    private void setPoaDocumentType(PoaDocumentType poaDocumentType) {
        setFormData("type", poaDocumentType.getId());
    }

    private void setSide(InternalDocSide internalDocSide) {
        setFormData("side", internalDocSide.getId());
    }

    private void setIssuingCountry(String str) {
        setFormData(ISSUING_COUNTRY, str);
    }

    private void setMediaType(String str) {
        setFormData(MEDIA_TYPE, str);
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
