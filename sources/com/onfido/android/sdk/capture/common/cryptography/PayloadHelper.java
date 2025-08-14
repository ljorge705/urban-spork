package com.onfido.android.sdk.capture.common.cryptography;

import android.util.Base64;
import com.onfido.android.sdk.capture.common.cryptography.Cryptography;
import com.onfido.android.sdk.capture.utils.ByteArrayExtensionsKt;
import com.onfido.api.client.data.PayloadIntegrity;
import com.onfido.api.client.data.SdkUploadMetaData;
import com.onfido.api.client.token.sdk.ApplicantId;
import com.onfido.javax.inject.Inject;
import java.io.File;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.json.Json;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\r\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u001e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u0016\u0010\u000e\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u0017J\u001e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u0018\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u0018\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0016\u001a\u00020\u0017H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/onfido/android/sdk/capture/common/cryptography/PayloadHelper;", "", "cryptography", "Lcom/onfido/android/sdk/capture/common/cryptography/Cryptography;", "jsonParser", "Lkotlinx/serialization/json/Json;", "applicantId", "Lcom/onfido/api/client/token/sdk/ApplicantId;", "(Lcom/onfido/android/sdk/capture/common/cryptography/Cryptography;Lkotlinx/serialization/json/Json;Lcom/onfido/api/client/token/sdk/ApplicantId;)V", "getClientNoncePart", "Lokhttp3/RequestBody;", "payload", "Lcom/onfido/android/sdk/capture/common/cryptography/Cryptography$Result;", "getSignaturePart", "getSignedPayload", "Lcom/onfido/api/client/data/PayloadIntegrity;", "file", "Ljava/io/File;", "metadataJson", "Lcom/onfido/api/client/data/SdkUploadMetaData;", "isSigningEnabled", "", "metadata", "", "binaryCapture", "", "getUnsignedPayload", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class PayloadHelper {
    private final ApplicantId applicantId;
    private final Cryptography cryptography;
    private final Json jsonParser;

    @Inject
    public PayloadHelper(Cryptography cryptography, Json jsonParser, ApplicantId applicantId) {
        Intrinsics.checkNotNullParameter(cryptography, "cryptography");
        Intrinsics.checkNotNullParameter(jsonParser, "jsonParser");
        Intrinsics.checkNotNullParameter(applicantId, "applicantId");
        this.cryptography = cryptography;
        this.jsonParser = jsonParser;
        this.applicantId = applicantId;
    }

    private final byte[] getUnsignedPayload(File file, String metadata) {
        return getUnsignedPayload(FilesKt.readBytes(file), metadata);
    }

    public final RequestBody getClientNoncePart(Cryptography.Result payload) {
        Intrinsics.checkNotNullParameter(payload, "payload");
        return RequestBody.INSTANCE.create(StringsKt.decodeToString(payload.getClientNonce()), MultipartBody.FORM);
    }

    public final RequestBody getSignaturePart(Cryptography.Result payload) {
        Intrinsics.checkNotNullParameter(payload, "payload");
        byte[] bArrEncode = Base64.encode(payload.getSignature(), 2);
        RequestBody.Companion companion = RequestBody.INSTANCE;
        Intrinsics.checkNotNull(bArrEncode);
        return RequestBody.Companion.create$default(companion, bArrEncode, MultipartBody.FORM, 0, 0, 6, (Object) null);
    }

    public final Cryptography.Result getSignedPayload(File file, String metadata) {
        Intrinsics.checkNotNullParameter(file, "file");
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        return this.cryptography.sign(getUnsignedPayload(file, metadata), this.applicantId);
    }

    private final byte[] getUnsignedPayload(byte[] binaryCapture, String metadata) {
        byte[] bytes = metadata.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        return ArraysKt.plus(binaryCapture, bytes);
    }

    public final PayloadIntegrity getSignedPayload(File file, SdkUploadMetaData metadataJson, boolean isSigningEnabled) {
        Intrinsics.checkNotNullParameter(file, "file");
        Intrinsics.checkNotNullParameter(metadataJson, "metadataJson");
        return getSignedPayload(FilesKt.readBytes(file), metadataJson, isSigningEnabled);
    }

    public final PayloadIntegrity getSignedPayload(byte[] binaryCapture, SdkUploadMetaData metadata, boolean isSigningEnabled) {
        Intrinsics.checkNotNullParameter(binaryCapture, "binaryCapture");
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        if (!isSigningEnabled) {
            return PayloadIntegrity.INSTANCE.getEMPTY();
        }
        Json json = this.jsonParser;
        Cryptography.Result resultSign = this.cryptography.sign(getUnsignedPayload(binaryCapture, json.encodeToString(SerializersKt.serializer(json.getSerializersModule(), Reflection.typeOf(SdkUploadMetaData.class)), metadata)), this.applicantId);
        return new PayloadIntegrity(ByteArrayExtensionsKt.toBase64String(resultSign.getSignature()), StringsKt.decodeToString(resultSign.getClientNonce()));
    }
}
