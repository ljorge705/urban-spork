package com.onfido.api.client;

import com.clevertap.android.sdk.Constants;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.onfido.api.client.data.SdkUploadMetaData;
import com.onfido.reactnative.sdk.ReactNativeBridgeUtiles;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.json.Json;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/* compiled from: MultiPartRequestBuilder.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0010\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\u0017\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\u0006\u0010\b\u001a\u00020\u0007J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0003H\u0004J\u0016\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0003J\u001e\u0010\f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0012J&\u0010\f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0003J\u0016\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u0003J\u0010\u0010\u0017\u001a\u00020\n2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019J\u0010\u0010\u001a\u001a\u00020\n2\u0006\u0010\u001b\u001a\u00020\u0003H\u0004R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/onfido/api/client/MultiPartRequestBuilder;", "", "sdkSource", "", RemoteConfigConstants.RequestFieldKey.SDK_VERSION, "(Ljava/lang/String;Ljava/lang/String;)V", "builder", "Lokhttp3/MultipartBody$Builder;", "getBuilder", "setClientNonce", "", "clientNonce", "setFile", "file", "Ljava/io/File;", ReactNativeBridgeUtiles.KEY_FILE_TYPE, ReactNativeBridgeUtiles.KEY_FILE_NAME, "data", "", "fileDataKey", "setFormData", Constants.KEY_KEY, "value", "setSdkMetadata", "metaData", "Lcom/onfido/api/client/data/SdkUploadMetaData;", "setSignature", "signature", "Companion", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public class MultiPartRequestBuilder {
    private static final String CLIENT_NONCE_KEY = "n";
    private static final String DEVICE_METADATA_KEY = "sdk_metadata";
    private static final String FILE_DATA_KEY = "file";
    private static final String FILE_NAME_KEY = "name";
    private static final String SIGNATURE_KEY = "s";
    private static final String SOURCE_INFO = "sdk_source";
    private static final String SOURCE_VERSION = "sdk_version";
    private final MultipartBody.Builder builder;

    public final MultipartBody.Builder getBuilder() {
        return this.builder;
    }

    public MultiPartRequestBuilder(String sdkSource, String sdkVersion) {
        Intrinsics.checkNotNullParameter(sdkSource, "sdkSource");
        Intrinsics.checkNotNullParameter(sdkVersion, "sdkVersion");
        MultipartBody.Builder builder = new MultipartBody.Builder(null, 1, null);
        this.builder = builder;
        setFormData(SOURCE_INFO, sdkSource);
        setFormData(SOURCE_VERSION, sdkVersion);
        builder.setType(MultipartBody.FORM);
    }

    public final void setFile(String fileName, String fileType, byte[] data) {
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        Intrinsics.checkNotNullParameter(fileType, "fileType");
        Intrinsics.checkNotNullParameter(data, "data");
        setFile(fileName, fileType, data, "file");
    }

    public final void setFile(String fileName, String fileType, byte[] data, String fileDataKey) {
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        Intrinsics.checkNotNullParameter(fileType, "fileType");
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(fileDataKey, "fileDataKey");
        this.builder.addFormDataPart(fileDataKey, fileName).addFormDataPart(fileDataKey, fileName, RequestBody.Companion.create$default(RequestBody.INSTANCE, data, MediaType.INSTANCE.get(fileType), 0, 0, 6, (Object) null));
    }

    public final void setFile(File file, String fileType) {
        Intrinsics.checkNotNullParameter(file, "file");
        Intrinsics.checkNotNullParameter(fileType, "fileType");
        RequestBody requestBodyCreate = RequestBody.INSTANCE.create(file, MediaType.INSTANCE.get(fileType));
        MultipartBody.Builder builder = this.builder;
        String name = file.getName();
        Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
        builder.addFormDataPart("name", name).addFormDataPart("file", file.getName(), requestBodyCreate);
    }

    public final void setFormData(String key, String value) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        this.builder.addFormDataPart(key, value);
    }

    public final void setSdkMetadata(SdkUploadMetaData metaData) {
        Json jsonParserInstance = JsonParserFactoryKt.getJsonParserInstance();
        setFormData(DEVICE_METADATA_KEY, jsonParserInstance.encodeToString(SerializersKt.serializer(jsonParserInstance.getSerializersModule(), Reflection.nullableTypeOf(SdkUploadMetaData.class)), metaData));
    }

    protected final void setSignature(String signature) {
        Intrinsics.checkNotNullParameter(signature, "signature");
        setFormData(SIGNATURE_KEY, signature);
    }

    protected final void setClientNonce(String clientNonce) {
        Intrinsics.checkNotNullParameter(clientNonce, "clientNonce");
        setFormData("n", clientNonce);
    }
}
