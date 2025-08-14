package com.onfido.android.sdk.capture.internal.analytics.inhouse;

import com.onfido.android.sdk.capture.internal.analytics.inhouse.SdkTokenPayload;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.ApplicantInfo;
import com.onfido.api.client.codec.binary.Base64;
import com.onfido.javax.inject.Inject;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.json.Json;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/SdkTokenParser;", "", "jsonParser", "Lkotlinx/serialization/json/Json;", "(Lkotlinx/serialization/json/Json;)V", "getApplicantInfo", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/ApplicantInfo;", "sdkToken", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class SdkTokenParser {
    private final Json jsonParser;

    @Inject
    public SdkTokenParser(Json jsonParser) {
        Intrinsics.checkNotNullParameter(jsonParser, "jsonParser");
        this.jsonParser = jsonParser;
    }

    public final ApplicantInfo getApplicantInfo(String sdkToken) {
        Intrinsics.checkNotNullParameter(sdkToken, "sdkToken");
        List listSplit$default = StringsKt.split$default((CharSequence) sdkToken, new String[]{"."}, false, 0, 6, (Object) null);
        if (listSplit$default.size() == 1) {
            return null;
        }
        String strDecodeBase64String = Base64.decodeBase64String((String) listSplit$default.get(1));
        Json json = this.jsonParser;
        Intrinsics.checkNotNull(strDecodeBase64String);
        SdkTokenPayload.OnfidoTokenPayload payload = ((SdkTokenPayload) json.decodeFromString(SerializersKt.serializer(json.getSerializersModule(), Reflection.typeOf(SdkTokenPayload.class)), strDecodeBase64String)).getPayload();
        return new ApplicantInfo(payload.getApplicantUuid(), payload.getClientUuid());
    }
}
