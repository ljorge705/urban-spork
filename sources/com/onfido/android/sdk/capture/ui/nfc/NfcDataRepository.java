package com.onfido.android.sdk.capture.ui.nfc;

import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.document.DocumentMediaType;
import com.onfido.android.sdk.capture.internal.nfc.data.NfcPassportExtraction;
import com.onfido.android.sdk.capture.internal.nfc.data.NfcUploadData;
import com.onfido.android.sdk.capture.network.OnfidoApiService;
import com.onfido.android.sdk.capture.utils.ByteArrayExtensionsKt;
import com.onfido.api.client.data.DocumentMediaUploadResponse;
import com.onfido.javax.inject.Inject;
import io.reactivex.rxjava3.core.Single;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.Charsets;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.json.Json;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0017\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J \u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0092\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0092\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/nfc/NfcDataRepository;", "", "onfidoApiService", "Lcom/onfido/android/sdk/capture/network/OnfidoApiService;", "jsonParser", "Lkotlinx/serialization/json/Json;", "(Lcom/onfido/android/sdk/capture/network/OnfidoApiService;Lkotlinx/serialization/json/Json;)V", "uploadData", "Lio/reactivex/rxjava3/core/Single;", "Lcom/onfido/api/client/data/DocumentMediaUploadResponse;", "nfcData", "Lcom/onfido/android/sdk/capture/internal/nfc/data/NfcPassportExtraction;", "documentType", "Lcom/onfido/android/sdk/capture/DocumentType;", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public class NfcDataRepository {
    public static final String FILE_NAME_NFC_DATA = "onfido-nfc-data.json";
    public static final String FILE_TYPE_JSON = "application/json";
    private final Json jsonParser;
    private final OnfidoApiService onfidoApiService;

    @Inject
    public NfcDataRepository(OnfidoApiService onfidoApiService, Json jsonParser) {
        Intrinsics.checkNotNullParameter(onfidoApiService, "onfidoApiService");
        Intrinsics.checkNotNullParameter(jsonParser, "jsonParser");
        this.onfidoApiService = onfidoApiService;
        this.jsonParser = jsonParser;
    }

    public Single<DocumentMediaUploadResponse> uploadData(NfcPassportExtraction nfcData, DocumentType documentType) {
        Intrinsics.checkNotNullParameter(nfcData, "nfcData");
        int[] positiveIntArray = ByteArrayExtensionsKt.toPositiveIntArray(nfcData.getDg1());
        int[] positiveIntArray2 = ByteArrayExtensionsKt.toPositiveIntArray(nfcData.getDg2());
        byte[] dg11 = nfcData.getDg11();
        int[] positiveIntArray3 = dg11 != null ? ByteArrayExtensionsKt.toPositiveIntArray(dg11) : null;
        byte[] dg12 = nfcData.getDg12();
        int[] positiveIntArray4 = dg12 != null ? ByteArrayExtensionsKt.toPositiveIntArray(dg12) : null;
        byte[] dg13 = nfcData.getDg13();
        int[] positiveIntArray5 = dg13 != null ? ByteArrayExtensionsKt.toPositiveIntArray(dg13) : null;
        byte[] dg14 = nfcData.getDg14();
        int[] positiveIntArray6 = dg14 != null ? ByteArrayExtensionsKt.toPositiveIntArray(dg14) : null;
        byte[] dg15 = nfcData.getDg15();
        int[] positiveIntArray7 = dg15 != null ? ByteArrayExtensionsKt.toPositiveIntArray(dg15) : null;
        int[] positiveIntArray8 = ByteArrayExtensionsKt.toPositiveIntArray(nfcData.getSod());
        byte[] aaResponse = nfcData.getAaResponse();
        NfcUploadData nfcUploadData = new NfcUploadData(positiveIntArray, positiveIntArray2, positiveIntArray3, positiveIntArray4, positiveIntArray5, positiveIntArray6, positiveIntArray7, positiveIntArray8, aaResponse != null ? ByteArrayExtensionsKt.toPositiveIntArray(aaResponse) : null);
        OnfidoApiService onfidoApiService = this.onfidoApiService;
        DocumentMediaType documentMediaType = DocumentMediaType.DOCUMENT_NFC_DATA;
        Json json = this.jsonParser;
        byte[] bytes = json.encodeToString(SerializersKt.serializer(json.getSerializersModule(), Reflection.typeOf(NfcUploadData.class)), nfcUploadData).getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        return onfidoApiService.uploadDocument$onfido_capture_sdk_core_release(FILE_NAME_NFC_DATA, FILE_TYPE_JSON, documentMediaType, documentType, bytes, null);
    }
}
