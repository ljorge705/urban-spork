package com.onfido.api.client.demo;

import com.clevertap.android.sdk.db.Column;
import com.google.android.gms.actions.SearchIntents;
import com.google.android.gms.fido.u2f.api.common.ClientData;
import com.onfido.android.sdk.capture.config.MediaCallbackResultReceiver;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.AnalyticsPropertyKeys;
import com.onfido.api.client.data.DocType;
import com.onfido.api.client.data.InternalDocSide;
import com.onfido.api.client.data.PoaDocumentType;
import com.onfido.api.client.data.SdkConfiguration;
import com.onfido.reactnative.sdk.ReactNativeBridgeUtiles;
import io.sentry.protocol.DebugImage;
import io.sentry.rrweb.RRWebMetaEvent;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import kotlinx.serialization.json.JsonArray;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonElementKt;
import kotlinx.serialization.json.JsonObject;

/* loaded from: classes6.dex */
class OnfidoDemoAPISerializer {
    private static final String ANY_ID = "ANY_ID";
    private static final String PROPERTY_BLUR = "blur";
    private static final String PROPERTY_MAX_TOTAL_RETRIES = "max_total_retries";
    private static final String PROPERTY_ON_DEVICE = "on_device";
    private static final String PROPERTY_SDK_FEATURES = "sdk_features";
    private static final String PROPERTY_VALIDATIONS = "validations";
    private SecureRandom random = new SecureRandom();

    OnfidoDemoAPISerializer() {
    }

    private Map<String, JsonElement> serializeBaseTypeUpload() {
        HashMap map = new HashMap();
        map.put("id", JsonElementKt.JsonPrimitive(ANY_ID));
        map.put(Column.CREATED_AT, JsonElementKt.JsonPrimitive("2022-01-20T17:43:32+0000"));
        map.put(MediaCallbackResultReceiver.KEY_FILE_NAME, JsonElementKt.JsonPrimitive("file_name.mp4"));
        map.put(MediaCallbackResultReceiver.KEY_FILE_TYPE, JsonElementKt.JsonPrimitive(MediaCallbackResultReceiver.KEY_FILE_TYPE));
        map.put(RRWebMetaEvent.JsonKeys.HREF, JsonElementKt.JsonPrimitive(RRWebMetaEvent.JsonKeys.HREF));
        map.put("download_href", JsonElementKt.JsonPrimitive("download_href"));
        map.put("file_size", JsonElementKt.JsonPrimitive("1024"));
        return map;
    }

    Map<String, JsonElement> serializeDocumentUpload(DocType docType, InternalDocSide internalDocSide) {
        Map<String, JsonElement> mapSerializeBaseTypeUpload = serializeBaseTypeUpload();
        mapSerializeBaseTypeUpload.put("applicant_id", JsonElementKt.JsonPrimitive("applicant_id"));
        mapSerializeBaseTypeUpload.put("type", JsonElementKt.JsonPrimitive(docType.getId()));
        mapSerializeBaseTypeUpload.put(ReactNativeBridgeUtiles.KEY_DOCUMENT_SIDE, JsonElementKt.JsonPrimitive(internalDocSide != null ? internalDocSide.getId() : InternalDocSide.FRONT.getId()));
        return mapSerializeBaseTypeUpload;
    }

    Map<String, JsonElement> serializePoaDocumentUpload(PoaDocumentType poaDocumentType, InternalDocSide internalDocSide) {
        Map<String, JsonElement> mapSerializeBaseTypeUpload = serializeBaseTypeUpload();
        mapSerializeBaseTypeUpload.put("applicant_id", JsonElementKt.JsonPrimitive("applicant_id"));
        mapSerializeBaseTypeUpload.put("type", JsonElementKt.JsonPrimitive(poaDocumentType.getId()));
        mapSerializeBaseTypeUpload.put(ReactNativeBridgeUtiles.KEY_DOCUMENT_SIDE, JsonElementKt.JsonPrimitive(internalDocSide != null ? internalDocSide.getId() : InternalDocSide.FRONT.getId()));
        return mapSerializeBaseTypeUpload;
    }

    JsonObject serializeLivePhotoUpload() {
        return new JsonObject(serializeBaseTypeUpload());
    }

    JsonObject serializeLiveVideoUpload() {
        return new JsonObject(serializeBaseTypeUpload());
    }

    JsonObject serializeLiveVideoChallenges() {
        HashMap map = new HashMap();
        map.put("data", new JsonObject(serializeLiveVideoChallengesData()));
        return new JsonObject(map);
    }

    JsonObject serializeSDKConfig() {
        HashMap map = new HashMap();
        HashMap map2 = new HashMap();
        HashMap map3 = new HashMap();
        map3.put(PROPERTY_MAX_TOTAL_RETRIES, JsonElementKt.JsonPrimitive((Number) 1));
        map2.put(PROPERTY_BLUR, new JsonObject(map3));
        map.put(PROPERTY_ON_DEVICE, new JsonObject(map2));
        HashMap map4 = new HashMap();
        map4.put(PROPERTY_VALIDATIONS, new JsonObject(map));
        HashMap map5 = new HashMap();
        map5.put(SdkConfiguration.FIELD_ENABLE_REQUIRE_APPLICANT_CONSENTS, JsonElementKt.JsonPrimitive((Boolean) false));
        map5.put(SdkConfiguration.FIELD_ENABLE_IN_HOUSE_ANALYTICS, JsonElementKt.JsonPrimitive((Boolean) false));
        map4.put(PROPERTY_SDK_FEATURES, new JsonObject(map5));
        HashMap map6 = new HashMap();
        map6.put(SdkConfiguration.FIELD_MOTION_VIDEO_SETTINGS, new JsonObject(Collections.emptyMap()));
        map4.put(SdkConfiguration.FIELD_MOTION_CAPTURE, new JsonObject(map6));
        HashMap map7 = new HashMap();
        map7.put(SdkConfiguration.FIELD_IS_PAYLOAD_SIGNING_ENABLED, JsonElementKt.JsonPrimitive((Boolean) false));
        map4.put(SdkConfiguration.FIELD_LIVENESS_CAPTURE, new JsonObject(map7));
        map4.put(SdkConfiguration.FIELD_SELFIE_CAPTURE, new JsonObject(Collections.emptyMap()));
        return new JsonObject(map4);
    }

    private Map<String, JsonElement> serializeLiveVideoChallengesData() {
        Map<String, JsonElement> mapSerializeBaseTypeUpload = serializeBaseTypeUpload();
        mapSerializeBaseTypeUpload.put(ClientData.KEY_CHALLENGE, new JsonArray(serializeLiveVideoChallengesList()));
        return mapSerializeBaseTypeUpload;
    }

    private List<JsonElement> serializeLiveVideoChallengesList() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new JsonObject(serializeReciteLiveVideoChallenge()));
        arrayList.add(new JsonObject(serializeMovementLiveVideoChallenge()));
        return arrayList;
    }

    private Map<String, JsonElement> serializeReciteLiveVideoChallenge() {
        HashMap map = new HashMap();
        map.put("type", JsonElementKt.JsonPrimitive("recite"));
        ArrayList arrayList = new ArrayList();
        Iterator<Integer> it = generateRandomIntegers().iterator();
        while (it.hasNext()) {
            arrayList.add(JsonElementKt.JsonPrimitive(Integer.valueOf(it.next().intValue())));
        }
        map.put(SearchIntents.EXTRA_QUERY, new JsonArray(arrayList));
        return map;
    }

    private Map<String, JsonElement> serializeMovementLiveVideoChallenge() {
        HashMap map = new HashMap();
        map.put("type", JsonElementKt.JsonPrimitive("movement"));
        map.put(SearchIntents.EXTRA_QUERY, JsonElementKt.JsonPrimitive(generateRandomMovement()));
        return map;
    }

    private List<Integer> generateRandomIntegers() {
        return Arrays.asList(Integer.valueOf(generateRandomInt()), Integer.valueOf(generateRandomInt()), Integer.valueOf(generateRandomInt()));
    }

    private String generateRandomMovement() {
        return new String[]{"turnLeft", "turnRight"}[this.random.nextInt(2)];
    }

    private int generateRandomInt() {
        return ThreadLocalRandom.current().nextInt(0, 10);
    }

    Map<String, JsonElement> serializeBinaryUpload() {
        HashMap map = new HashMap();
        map.put("media_id", JsonElementKt.JsonPrimitive(ANY_ID));
        HashMap map2 = new HashMap();
        map2.put(DebugImage.JsonKeys.UUID, JsonElementKt.JsonPrimitive(ANY_ID));
        map.put("binary_media", new JsonObject(map2));
        return map;
    }

    Map<String, JsonElement> serializeDocumentUpload() {
        HashMap map = new HashMap();
        map.put(DebugImage.JsonKeys.UUID, JsonElementKt.JsonPrimitive(ANY_ID));
        map.put("applicant_id", JsonElementKt.JsonPrimitive(ANY_ID));
        map.put(AnalyticsPropertyKeys.DOCUMENT_TYPE, JsonElementKt.JsonPrimitive("IDENTITY_DOCUMENT"));
        return map;
    }

    Map<String, JsonElement> serializeNfcProperties() {
        HashMap map = new HashMap();
        map.put("nfc_supported", JsonElementKt.JsonPrimitive((Boolean) false));
        return map;
    }

    JsonObject supportedDocuments() {
        HashMap map = new HashMap();
        map.put("docs", new JsonArray(Collections.emptyList()));
        map.put("issuers", new JsonObject(Collections.emptyMap()));
        return new JsonObject(map);
    }
}
