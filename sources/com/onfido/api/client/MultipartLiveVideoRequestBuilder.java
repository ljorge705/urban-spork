package com.onfido.api.client;

import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.onfido.api.client.data.LiveVideoChallenges;
import com.onfido.api.client.data.LiveVideoLanguage;
import com.onfido.api.client.data.PayloadIntegrity;
import com.onfido.api.client.data.SdkUploadMetaData;
import com.onfido.reactnative.sdk.ReactNativeBridgeUtiles;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KTypeProjection;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.json.Json;
import okhttp3.MultipartBody;

/* compiled from: MultipartLiveVideoRequestBuilder.kt */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000  2\u00020\u0001:\u0001 B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0003H\u0002J\u0010\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0010\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0003H\u0002J\u0010\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0003H\u0002J^\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00032\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u00182\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u00182\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001f¨\u0006!"}, d2 = {"Lcom/onfido/api/client/MultipartLiveVideoRequestBuilder;", "Lcom/onfido/api/client/MultiPartRequestBuilder;", "sdkSource", "", RemoteConfigConstants.RequestFieldKey.SDK_VERSION, "(Ljava/lang/String;Ljava/lang/String;)V", "setChallengeId", "", "id", "setChallengeSwitch", "challengeSwitchTimestamp", "", "setChallenges", "challengesRepresentation", "setLanguages", "localeCode", "setMultipartRequestBody", "Lokhttp3/MultipartBody$Builder;", ReactNativeBridgeUtiles.KEY_FILE_NAME, ReactNativeBridgeUtiles.KEY_FILE_TYPE, "data", "", "challengeId", "challenges", "", "Lcom/onfido/api/client/data/LiveVideoChallenges$LiveVideoChallenge;", MultipartLiveVideoRequestBuilder.LANGUAGE_KEY, "Lcom/onfido/api/client/data/LiveVideoLanguage;", "sdkUploadMetaData", "Lcom/onfido/api/client/data/SdkUploadMetaData;", "payloadIntegrity", "Lcom/onfido/api/client/data/PayloadIntegrity;", "Companion", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class MultipartLiveVideoRequestBuilder extends MultiPartRequestBuilder {
    private static final String CHALLENGES_ID_KEY = "challenge_id";
    private static final String CHALLENGES_KEY = "challenge";
    private static final String CHALLENGE_SWITCH_KEY = "challenge_switch_at";
    private static final String LANGUAGE_KEY = "languages";

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MultipartLiveVideoRequestBuilder(String sdkSource, String sdkVersion) {
        super(sdkSource, sdkVersion);
        Intrinsics.checkNotNullParameter(sdkSource, "sdkSource");
        Intrinsics.checkNotNullParameter(sdkVersion, "sdkVersion");
    }

    public final MultipartBody.Builder setMultipartRequestBody(String fileName, String fileType, byte[] data, String challengeId, List<? extends LiveVideoChallenges.LiveVideoChallenge> challenges, long challengeSwitchTimestamp, List<LiveVideoLanguage> languages, SdkUploadMetaData sdkUploadMetaData, PayloadIntegrity payloadIntegrity) {
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        Intrinsics.checkNotNullParameter(fileType, "fileType");
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(challengeId, "challengeId");
        Intrinsics.checkNotNullParameter(challenges, "challenges");
        Intrinsics.checkNotNullParameter(languages, "languages");
        setChallengeId(challengeId);
        Json jsonParserInstance = JsonParserFactoryKt.getJsonParserInstance();
        setChallenges(jsonParserInstance.encodeToString(SerializersKt.serializer(jsonParserInstance.getSerializersModule(), Reflection.typeOf(List.class, KTypeProjection.INSTANCE.invariant(Reflection.typeOf(LiveVideoChallenges.LiveVideoChallenge.class)))), challenges));
        setFile(fileName, fileType, data);
        setChallengeSwitch(challengeSwitchTimestamp);
        Json jsonParserInstance2 = JsonParserFactoryKt.getJsonParserInstance();
        setLanguages(jsonParserInstance2.encodeToString(SerializersKt.serializer(jsonParserInstance2.getSerializersModule(), Reflection.typeOf(List.class, KTypeProjection.INSTANCE.invariant(Reflection.typeOf(LiveVideoLanguage.class)))), languages));
        setSdkMetadata(sdkUploadMetaData);
        if (payloadIntegrity != null && !payloadIntegrity.isEmpty()) {
            String signatureBase64 = payloadIntegrity.getSignatureBase64();
            Intrinsics.checkNotNull(signatureBase64);
            setSignature(signatureBase64);
            String clientNonce = payloadIntegrity.getClientNonce();
            Intrinsics.checkNotNull(clientNonce);
            setClientNonce(clientNonce);
        }
        return super.getBuilder();
    }

    private final void setChallenges(String challengesRepresentation) {
        setFormData("challenge", challengesRepresentation);
    }

    private final void setChallengeSwitch(long challengeSwitchTimestamp) {
        setFormData(CHALLENGE_SWITCH_KEY, String.valueOf(challengeSwitchTimestamp));
    }

    private final void setChallengeId(String id) {
        setFormData(CHALLENGES_ID_KEY, id);
    }

    private final void setLanguages(String localeCode) {
        setFormData(LANGUAGE_KEY, localeCode);
    }
}
