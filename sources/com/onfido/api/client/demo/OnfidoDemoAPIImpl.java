package com.onfido.api.client.demo;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.onfido.api.client.OnfidoAPI;
import com.onfido.api.client.ValidationLevel;
import com.onfido.api.client.ValidationType;
import com.onfido.api.client.data.DeviceInfo;
import com.onfido.api.client.data.DocType;
import com.onfido.api.client.data.DocumentMediaIntegrity;
import com.onfido.api.client.data.DocumentMediaUploadResponse;
import com.onfido.api.client.data.DocumentUpload;
import com.onfido.api.client.data.InternalDocSide;
import com.onfido.api.client.data.LivePhotoUpload;
import com.onfido.api.client.data.LiveVideoChallenges;
import com.onfido.api.client.data.LiveVideoLanguage;
import com.onfido.api.client.data.LiveVideoUpload;
import com.onfido.api.client.data.MRZDocument;
import com.onfido.api.client.data.NfcProperties;
import com.onfido.api.client.data.PayloadIntegrity;
import com.onfido.api.client.data.PoaDocumentType;
import com.onfido.api.client.data.PoaDocumentUpload;
import com.onfido.api.client.data.SdkConfiguration;
import com.onfido.api.client.data.SdkUploadMetaData;
import com.onfido.api.client.data.SupportedDocuments;
import com.onfido.reactnative.sdk.ReactNativeBridgeUtiles;
import io.reactivex.rxjava3.core.Single;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonObject;

/* compiled from: OnfidoDemoAPIImpl.kt */
@Metadata(d1 = {"\u0000Î\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J$\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0016\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\b2\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u000e\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\bH\u0016JJ\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\b2\b\u0010\u0019\u001a\u0004\u0018\u00010\u000e2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u000e2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u000e2\b\u0010 \u001a\u0004\u0018\u00010!H\u0016Jf\u0010\"\u001a\u00020#2\u0006\u0010\u0019\u001a\u00020\u000e2\u0006\u0010$\u001a\u00020%2\u0006\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u001e2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020(0'2\u0012\u0010)\u001a\u000e\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020,0*2\b\u0010-\u001a\u0004\u0018\u00010.2\b\u0010\u001f\u001a\u0004\u0018\u00010\u000e2\u0006\u0010 \u001a\u00020!H\u0016J^\u0010/\u001a\b\u0012\u0004\u0012\u0002000\b2\u0006\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u000e2\u0006\u00101\u001a\u00020\u000e2\b\u00102\u001a\u0004\u0018\u00010\u000e2\b\u00103\u001a\u0004\u0018\u00010\u000e2\u0012\u0010)\u001a\u000e\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020,0*2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020!H\u0016JH\u00104\u001a\b\u0012\u0004\u0012\u00020\u000e0\b2\u0006\u00105\u001a\u00020\u000e2\u0006\u00106\u001a\u00020\u000e2\u0006\u0010 \u001a\u00020!2\u0006\u00107\u001a\u0002082\u0006\u00102\u001a\u00020.2\u0006\u00103\u001a\u00020%2\b\u0010\u001f\u001a\u0004\u0018\u00010\u000eH\u0016JF\u00109\u001a\u00020#2\u0006\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010:\u001a\u00020;2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020<0'2\u0006\u0010 \u001a\u00020!2\u0006\u0010=\u001a\u00020>H\u0016Jb\u0010?\u001a\b\u0012\u0004\u0012\u00020@0\b2\u0006\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010A\u001a\u00020\u000e2\f\u0010B\u001a\b\u0012\u0004\u0012\u00020C0\r2\u0006\u0010D\u001a\u00020E2\f\u0010F\u001a\b\u0012\u0004\u0012\u00020G0\r2\u0006\u0010 \u001a\u00020!2\u0006\u0010=\u001a\u00020>H\u0016JJ\u0010H\u001a\b\u0012\u0004\u0012\u00020(0\b2\b\u0010\u0019\u001a\u0004\u0018\u00010\u000e2\b\u0010$\u001a\u0004\u0018\u00010%2\b\u0010\u001c\u001a\u0004\u0018\u00010\u000e2\b\u00101\u001a\u0004\u0018\u00010\u000e2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\b\u0010 \u001a\u0004\u0018\u00010!H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006I"}, d2 = {"Lcom/onfido/api/client/demo/OnfidoDemoAPIImpl;", "Lcom/onfido/api/client/OnfidoAPI;", "jsonParser", "Lkotlinx/serialization/json/Json;", "(Lkotlinx/serialization/json/Json;)V", "onfidoDemoAPISerializer", "Lcom/onfido/api/client/demo/OnfidoDemoAPISerializer;", "getLiveVideoChallenges", "Lio/reactivex/rxjava3/core/Single;", "Lcom/onfido/api/client/data/LiveVideoChallenges;", "getNfcProperties", "Lcom/onfido/api/client/data/NfcProperties;", "documentIds", "", "", "mrzDocument", "Lcom/onfido/api/client/data/MRZDocument;", "getSDKConfig", "Lcom/onfido/api/client/data/SdkConfiguration;", "deviceInfo", "Lcom/onfido/api/client/data/DeviceInfo;", "getSupportedDocuments", "Lcom/onfido/api/client/data/SupportedDocuments;", "poaUpload", "Lcom/onfido/api/client/data/PoaDocumentUpload;", ReactNativeBridgeUtiles.KEY_FILE_NAME, "poaDocumentType", "Lcom/onfido/api/client/data/PoaDocumentType;", ReactNativeBridgeUtiles.KEY_FILE_TYPE, "data", "", ReactNativeBridgeUtiles.KEY_DOCUMENT_ISSUING_COUNTRY, "sdkUploadMetaData", "Lcom/onfido/api/client/data/SdkUploadMetaData;", "upload", "", "documentType", "Lcom/onfido/api/client/data/DocType;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/onfido/api/client/OnfidoAPI$Listener;", "Lcom/onfido/api/client/data/DocumentUpload;", "validations", "", "Lcom/onfido/api/client/ValidationType;", "Lcom/onfido/api/client/ValidationLevel;", ReactNativeBridgeUtiles.KEY_DOCUMENT_SIDE, "Lcom/onfido/api/client/data/InternalDocSide;", "uploadDocumentMedia", "Lcom/onfido/api/client/data/DocumentMediaUploadResponse;", "mediaType", "docSide", "docType", "uploadDocumentVideo", "documentId", "videoPath", "documentMediaIntegrity", "Lcom/onfido/api/client/data/DocumentMediaIntegrity;", "uploadLivePhoto", "advancedValidation", "", "Lcom/onfido/api/client/data/LivePhotoUpload;", "payloadIntegrity", "Lcom/onfido/api/client/data/PayloadIntegrity;", "uploadLiveVideo", "Lcom/onfido/api/client/data/LiveVideoUpload;", "challengeId", "challengeList", "Lcom/onfido/api/client/data/LiveVideoChallenges$LiveVideoChallenge;", "challengeSwitchTimestamp", "", "languages", "Lcom/onfido/api/client/data/LiveVideoLanguage;", "uploadSingle", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class OnfidoDemoAPIImpl implements OnfidoAPI {
    private final Json jsonParser;
    private final OnfidoDemoAPISerializer onfidoDemoAPISerializer;

    public OnfidoDemoAPIImpl(Json jsonParser) {
        Intrinsics.checkNotNullParameter(jsonParser, "jsonParser");
        this.jsonParser = jsonParser;
        this.onfidoDemoAPISerializer = new OnfidoDemoAPISerializer();
    }

    @Override // com.onfido.api.client.OnfidoAPI
    public /* bridge */ /* synthetic */ Single uploadLiveVideo(String str, String str2, byte[] bArr, String str3, List list, Long l, List list2, SdkUploadMetaData sdkUploadMetaData, PayloadIntegrity payloadIntegrity) {
        return uploadLiveVideo(str, str2, bArr, str3, (List<? extends LiveVideoChallenges.LiveVideoChallenge>) list, l.longValue(), (List<LiveVideoLanguage>) list2, sdkUploadMetaData, payloadIntegrity);
    }

    @Override // com.onfido.api.client.OnfidoAPI
    public void upload(String fileName, DocType documentType, String fileType, byte[] data, OnfidoAPI.Listener<DocumentUpload> listener, Map<ValidationType, ? extends ValidationLevel> validations, InternalDocSide side, String issuingCountry, SdkUploadMetaData sdkUploadMetaData) {
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        Intrinsics.checkNotNullParameter(documentType, "documentType");
        Intrinsics.checkNotNullParameter(fileType, "fileType");
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(listener, "listener");
        Intrinsics.checkNotNullParameter(validations, "validations");
        Intrinsics.checkNotNullParameter(sdkUploadMetaData, "sdkUploadMetaData");
        Json json = this.jsonParser;
        Map<String, JsonElement> mapSerializeDocumentUpload = this.onfidoDemoAPISerializer.serializeDocumentUpload(documentType, side);
        Intrinsics.checkNotNullExpressionValue(mapSerializeDocumentUpload, "serializeDocumentUpload(...)");
        listener.onSuccess((DocumentUpload) json.decodeFromJsonElement(SerializersKt.serializer(json.getSerializersModule(), Reflection.typeOf(DocumentUpload.class)), new JsonObject(mapSerializeDocumentUpload)));
    }

    @Override // com.onfido.api.client.OnfidoAPI
    public Single<DocumentUpload> uploadSingle(String fileName, DocType documentType, String fileType, String mediaType, byte[] data, SdkUploadMetaData sdkUploadMetaData) {
        Json json = this.jsonParser;
        Map<String, JsonElement> mapSerializeDocumentUpload = this.onfidoDemoAPISerializer.serializeDocumentUpload(documentType, InternalDocSide.FRONT);
        Intrinsics.checkNotNullExpressionValue(mapSerializeDocumentUpload, "serializeDocumentUpload(...)");
        Single<DocumentUpload> singleJust = Single.just((DocumentUpload) json.decodeFromJsonElement(SerializersKt.serializer(json.getSerializersModule(), Reflection.typeOf(DocumentUpload.class)), new JsonObject(mapSerializeDocumentUpload)));
        Intrinsics.checkNotNullExpressionValue(singleJust, "just(...)");
        return singleJust;
    }

    @Override // com.onfido.api.client.OnfidoAPI
    public Single<PoaDocumentUpload> poaUpload(String fileName, PoaDocumentType poaDocumentType, String fileType, byte[] data, String issuingCountry, SdkUploadMetaData sdkUploadMetaData) {
        Json json = this.jsonParser;
        Map<String, JsonElement> mapSerializePoaDocumentUpload = this.onfidoDemoAPISerializer.serializePoaDocumentUpload(poaDocumentType, null);
        Intrinsics.checkNotNullExpressionValue(mapSerializePoaDocumentUpload, "serializePoaDocumentUpload(...)");
        Single<PoaDocumentUpload> singleJust = Single.just((PoaDocumentUpload) json.decodeFromJsonElement(SerializersKt.serializer(json.getSerializersModule(), Reflection.typeOf(PoaDocumentUpload.class)), new JsonObject(mapSerializePoaDocumentUpload)));
        Intrinsics.checkNotNullExpressionValue(singleJust, "just(...)");
        return singleJust;
    }

    @Override // com.onfido.api.client.OnfidoAPI
    public Single<String> uploadDocumentVideo(String documentId, String videoPath, SdkUploadMetaData sdkUploadMetaData, DocumentMediaIntegrity documentMediaIntegrity, InternalDocSide docSide, DocType docType, String issuingCountry) {
        Intrinsics.checkNotNullParameter(documentId, "documentId");
        Intrinsics.checkNotNullParameter(videoPath, "videoPath");
        Intrinsics.checkNotNullParameter(sdkUploadMetaData, "sdkUploadMetaData");
        Intrinsics.checkNotNullParameter(documentMediaIntegrity, "documentMediaIntegrity");
        Intrinsics.checkNotNullParameter(docSide, "docSide");
        Intrinsics.checkNotNullParameter(docType, "docType");
        Single<String> singleJust = Single.just("");
        Intrinsics.checkNotNullExpressionValue(singleJust, "just(...)");
        return singleJust;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.onfido.api.client.OnfidoAPI
    public void uploadLivePhoto(String fileName, String fileType, byte[] data, boolean advancedValidation, OnfidoAPI.Listener<LivePhotoUpload> listener, SdkUploadMetaData sdkUploadMetaData, PayloadIntegrity payloadIntegrity) {
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        Intrinsics.checkNotNullParameter(fileType, "fileType");
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(listener, "listener");
        Intrinsics.checkNotNullParameter(sdkUploadMetaData, "sdkUploadMetaData");
        Intrinsics.checkNotNullParameter(payloadIntegrity, "payloadIntegrity");
        Json json = this.jsonParser;
        JsonObject jsonObjectSerializeLivePhotoUpload = this.onfidoDemoAPISerializer.serializeLivePhotoUpload();
        Intrinsics.checkNotNullExpressionValue(jsonObjectSerializeLivePhotoUpload, "serializeLivePhotoUpload(...)");
        listener.onSuccess(json.decodeFromJsonElement(SerializersKt.serializer(json.getSerializersModule(), Reflection.typeOf(LivePhotoUpload.class)), jsonObjectSerializeLivePhotoUpload));
    }

    public Single<LiveVideoUpload> uploadLiveVideo(String fileName, String fileType, byte[] data, String challengeId, List<? extends LiveVideoChallenges.LiveVideoChallenge> challengeList, long challengeSwitchTimestamp, List<LiveVideoLanguage> languages, SdkUploadMetaData sdkUploadMetaData, PayloadIntegrity payloadIntegrity) {
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        Intrinsics.checkNotNullParameter(fileType, "fileType");
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(challengeId, "challengeId");
        Intrinsics.checkNotNullParameter(challengeList, "challengeList");
        Intrinsics.checkNotNullParameter(languages, "languages");
        Intrinsics.checkNotNullParameter(sdkUploadMetaData, "sdkUploadMetaData");
        Intrinsics.checkNotNullParameter(payloadIntegrity, "payloadIntegrity");
        Single<LiveVideoUpload> singleFromCallable = Single.fromCallable(new Callable() { // from class: com.onfido.api.client.demo.OnfidoDemoAPIImpl$$ExternalSyntheticLambda2
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return OnfidoDemoAPIImpl.uploadLiveVideo$lambda$0(this.f$0);
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleFromCallable, "fromCallable(...)");
        return singleFromCallable;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final LiveVideoUpload uploadLiveVideo$lambda$0(OnfidoDemoAPIImpl this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Json json = this$0.jsonParser;
        JsonObject jsonObjectSerializeLiveVideoUpload = this$0.onfidoDemoAPISerializer.serializeLiveVideoUpload();
        Intrinsics.checkNotNullExpressionValue(jsonObjectSerializeLiveVideoUpload, "serializeLiveVideoUpload(...)");
        return (LiveVideoUpload) json.decodeFromJsonElement(SerializersKt.serializer(json.getSerializersModule(), Reflection.typeOf(LiveVideoUpload.class)), jsonObjectSerializeLiveVideoUpload);
    }

    @Override // com.onfido.api.client.OnfidoAPI
    public Single<LiveVideoChallenges> getLiveVideoChallenges() {
        Single<LiveVideoChallenges> singleFromCallable = Single.fromCallable(new Callable() { // from class: com.onfido.api.client.demo.OnfidoDemoAPIImpl$$ExternalSyntheticLambda4
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return OnfidoDemoAPIImpl.getLiveVideoChallenges$lambda$1(this.f$0);
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleFromCallable, "fromCallable(...)");
        return singleFromCallable;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final LiveVideoChallenges getLiveVideoChallenges$lambda$1(OnfidoDemoAPIImpl this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Json json = this$0.jsonParser;
        JsonObject jsonObjectSerializeLiveVideoChallenges = this$0.onfidoDemoAPISerializer.serializeLiveVideoChallenges();
        Intrinsics.checkNotNullExpressionValue(jsonObjectSerializeLiveVideoChallenges, "serializeLiveVideoChallenges(...)");
        return (LiveVideoChallenges) json.decodeFromJsonElement(SerializersKt.serializer(json.getSerializersModule(), Reflection.typeOf(LiveVideoChallenges.class)), jsonObjectSerializeLiveVideoChallenges);
    }

    @Override // com.onfido.api.client.OnfidoAPI
    public Single<DocumentMediaUploadResponse> uploadDocumentMedia(String fileName, String fileType, String mediaType, String docSide, String docType, Map<ValidationType, ? extends ValidationLevel> validations, byte[] data, SdkUploadMetaData sdkUploadMetaData) {
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        Intrinsics.checkNotNullParameter(fileType, "fileType");
        Intrinsics.checkNotNullParameter(mediaType, "mediaType");
        Intrinsics.checkNotNullParameter(validations, "validations");
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(sdkUploadMetaData, "sdkUploadMetaData");
        Single<DocumentMediaUploadResponse> singleFromCallable = Single.fromCallable(new Callable() { // from class: com.onfido.api.client.demo.OnfidoDemoAPIImpl$$ExternalSyntheticLambda3
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return OnfidoDemoAPIImpl.uploadDocumentMedia$lambda$2(this.f$0);
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleFromCallable, "fromCallable(...)");
        return singleFromCallable;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DocumentMediaUploadResponse uploadDocumentMedia$lambda$2(OnfidoDemoAPIImpl this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Json json = this$0.jsonParser;
        Map<String, JsonElement> mapSerializeBinaryUpload = this$0.onfidoDemoAPISerializer.serializeBinaryUpload();
        Intrinsics.checkNotNullExpressionValue(mapSerializeBinaryUpload, "serializeBinaryUpload(...)");
        return (DocumentMediaUploadResponse) json.decodeFromJsonElement(SerializersKt.serializer(json.getSerializersModule(), Reflection.typeOf(DocumentMediaUploadResponse.class)), new JsonObject(mapSerializeBinaryUpload));
    }

    @Override // com.onfido.api.client.OnfidoAPI
    public Single<SdkConfiguration> getSDKConfig(DeviceInfo deviceInfo) {
        Intrinsics.checkNotNullParameter(deviceInfo, "deviceInfo");
        Single<SdkConfiguration> singleFromCallable = Single.fromCallable(new Callable() { // from class: com.onfido.api.client.demo.OnfidoDemoAPIImpl$$ExternalSyntheticLambda5
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return OnfidoDemoAPIImpl.getSDKConfig$lambda$3(this.f$0);
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleFromCallable, "fromCallable(...)");
        return singleFromCallable;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final SdkConfiguration getSDKConfig$lambda$3(OnfidoDemoAPIImpl this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Json json = this$0.jsonParser;
        JsonObject jsonObjectSerializeSDKConfig = this$0.onfidoDemoAPISerializer.serializeSDKConfig();
        Intrinsics.checkNotNullExpressionValue(jsonObjectSerializeSDKConfig, "serializeSDKConfig(...)");
        return (SdkConfiguration) json.decodeFromJsonElement(SerializersKt.serializer(json.getSerializersModule(), Reflection.typeOf(SdkConfiguration.class)), jsonObjectSerializeSDKConfig);
    }

    @Override // com.onfido.api.client.OnfidoAPI
    public Single<NfcProperties> getNfcProperties(List<String> documentIds, MRZDocument mrzDocument) {
        Intrinsics.checkNotNullParameter(documentIds, "documentIds");
        Intrinsics.checkNotNullParameter(mrzDocument, "mrzDocument");
        Single<NfcProperties> singleFromCallable = Single.fromCallable(new Callable() { // from class: com.onfido.api.client.demo.OnfidoDemoAPIImpl$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return OnfidoDemoAPIImpl.getNfcProperties$lambda$4(this.f$0);
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleFromCallable, "fromCallable(...)");
        return singleFromCallable;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final NfcProperties getNfcProperties$lambda$4(OnfidoDemoAPIImpl this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Json json = this$0.jsonParser;
        Map<String, JsonElement> mapSerializeNfcProperties = this$0.onfidoDemoAPISerializer.serializeNfcProperties();
        Intrinsics.checkNotNullExpressionValue(mapSerializeNfcProperties, "serializeNfcProperties(...)");
        return (NfcProperties) json.decodeFromJsonElement(SerializersKt.serializer(json.getSerializersModule(), Reflection.typeOf(NfcProperties.class)), new JsonObject(mapSerializeNfcProperties));
    }

    @Override // com.onfido.api.client.OnfidoAPI
    public Single<SupportedDocuments> getSupportedDocuments() {
        Single<SupportedDocuments> singleFromCallable = Single.fromCallable(new Callable() { // from class: com.onfido.api.client.demo.OnfidoDemoAPIImpl$$ExternalSyntheticLambda1
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return OnfidoDemoAPIImpl.getSupportedDocuments$lambda$5(this.f$0);
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleFromCallable, "fromCallable(...)");
        return singleFromCallable;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final SupportedDocuments getSupportedDocuments$lambda$5(OnfidoDemoAPIImpl this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Json json = this$0.jsonParser;
        JsonObject jsonObjectSupportedDocuments = this$0.onfidoDemoAPISerializer.supportedDocuments();
        Intrinsics.checkNotNullExpressionValue(jsonObjectSupportedDocuments, "supportedDocuments(...)");
        return (SupportedDocuments) json.decodeFromJsonElement(SerializersKt.serializer(json.getSerializersModule(), Reflection.typeOf(SupportedDocuments.class)), new JsonObject(jsonObjectSupportedDocuments));
    }
}
