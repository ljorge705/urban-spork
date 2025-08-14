package com.onfido.android.sdk.capture.internal.util.logging;

import android.os.Build;
import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.BuildConfig;
import com.onfido.android.sdk.capture.OnfidoConfig;
import com.onfido.android.sdk.capture.internal.util.SessionIdProvider;
import com.onfido.android.sdk.capture.internal.util.logging.network.EventMetadata;
import com.onfido.android.sdk.capture.internal.util.logging.network.LogBody;
import com.onfido.android.sdk.capture.internal.util.logging.network.LoggerRequest;
import com.onfido.android.sdk.capture.internal.util.logging.network.SdkConfig;
import com.onfido.android.sdk.capture.internal.util.logging.network.SourceMetadata;
import com.onfido.android.sdk.capture.ui.options.FlowStep;
import com.onfido.android.sdk.capture.utils.DeviceMetadataProvider;
import com.onfido.android.sdk.capture.utils.UuidProvider;
import com.onfido.api.client.data.DeviceMetadata;
import com.onfido.api.client.token.sdk.SDKTokenPayload;
import com.onfido.javax.inject.Inject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.JsonArray;
import kotlinx.serialization.json.JsonElementKt;
import kotlinx.serialization.json.JsonObject;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0012\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0002J\u0014\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/util/logging/OnfidoLogMapper;", "", "uuidProvider", "Lcom/onfido/android/sdk/capture/utils/UuidProvider;", "onfidoConfig", "Lcom/onfido/android/sdk/capture/OnfidoConfig;", "sessionIdProvider", "Lcom/onfido/android/sdk/capture/internal/util/SessionIdProvider;", "deviceMetadataProvider", "Lcom/onfido/android/sdk/capture/utils/DeviceMetadataProvider;", "(Lcom/onfido/android/sdk/capture/utils/UuidProvider;Lcom/onfido/android/sdk/capture/OnfidoConfig;Lcom/onfido/android/sdk/capture/internal/util/SessionIdProvider;Lcom/onfido/android/sdk/capture/utils/DeviceMetadataProvider;)V", "eventMetadata", "Lcom/onfido/android/sdk/capture/internal/util/logging/network/EventMetadata;", "getSdkConfig", "Lcom/onfido/android/sdk/capture/internal/util/logging/network/SdkConfig;", "sdkToken", "", "mapLogToRequest", "Lcom/onfido/android/sdk/capture/internal/util/logging/network/LoggerRequest;", "logs", "", "Lcom/onfido/android/sdk/capture/internal/util/logging/OnfidoRemoteLog;", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class OnfidoLogMapper {

    @Deprecated
    public static final String KEY_EVENT_TYPE = "event_type";

    @Deprecated
    public static final String KEY_LOG_LABELS = "log_labels";

    @Deprecated
    public static final String KEY_LOG_LEVEL = "log_level";

    @Deprecated
    public static final String KEY_LOG_METADATA = "log_metadata";

    @Deprecated
    public static final String LOG_EVENT_TYPE = "log";

    @Deprecated
    public static final String LOG_SOURCE = "sdk";

    @Deprecated
    public static final String OS = "Android";
    private static final String OS_VERSION;
    private final EventMetadata eventMetadata;
    private final OnfidoConfig onfidoConfig;
    private final SessionIdProvider sessionIdProvider;
    private final UuidProvider uuidProvider;
    private static final Companion Companion = new Companion(0 == true ? 1 : 0);
    private static final SourceMetadata SOURCE_METADATA = new SourceMetadata(BuildConfig.SDK_SOURCE, "22.7.0", "release");

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\n\u0010\u0012\u001a\u00020\u0013*\u00020\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0011\u0010\u000b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0015"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/util/logging/OnfidoLogMapper$Companion;", "", "()V", "KEY_EVENT_TYPE", "", "KEY_LOG_LABELS", "KEY_LOG_LEVEL", "KEY_LOG_METADATA", "LOG_EVENT_TYPE", "LOG_SOURCE", "OS", "OS_VERSION", "getOS_VERSION", "()Ljava/lang/String;", "SOURCE_METADATA", "Lcom/onfido/android/sdk/capture/internal/util/logging/network/SourceMetadata;", "getSOURCE_METADATA", "()Lcom/onfido/android/sdk/capture/internal/util/logging/network/SourceMetadata;", "toEventMetadata", "Lcom/onfido/android/sdk/capture/internal/util/logging/network/EventMetadata;", "Lcom/onfido/api/client/data/DeviceMetadata;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class Companion {
        private Companion() {
        }

        public final String getOS_VERSION() {
            return OnfidoLogMapper.OS_VERSION;
        }

        public final SourceMetadata getSOURCE_METADATA() {
            return OnfidoLogMapper.SOURCE_METADATA;
        }

        public final EventMetadata toEventMetadata(DeviceMetadata deviceMetadata) {
            Intrinsics.checkNotNullParameter(deviceMetadata, "<this>");
            return new EventMetadata("Android", getOS_VERSION(), deviceMetadata.getFingerprint(), deviceMetadata.getModel(), deviceMetadata.getManufacturer(), deviceMetadata.getBrand(), deviceMetadata.getProduct(), deviceMetadata.getHardware(), deviceMetadata.getAndroidApiLevel());
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    static {
        String str = Build.VERSION.RELEASE;
        String str2 = str != null ? str : null;
        if (str2 == null) {
            str2 = "";
        }
        OS_VERSION = str2;
    }

    @Inject
    public OnfidoLogMapper(UuidProvider uuidProvider, OnfidoConfig onfidoConfig, SessionIdProvider sessionIdProvider, DeviceMetadataProvider deviceMetadataProvider) {
        Intrinsics.checkNotNullParameter(uuidProvider, "uuidProvider");
        Intrinsics.checkNotNullParameter(onfidoConfig, "onfidoConfig");
        Intrinsics.checkNotNullParameter(sessionIdProvider, "sessionIdProvider");
        Intrinsics.checkNotNullParameter(deviceMetadataProvider, "deviceMetadataProvider");
        this.uuidProvider = uuidProvider;
        this.onfidoConfig = onfidoConfig;
        this.sessionIdProvider = sessionIdProvider;
        this.eventMetadata = Companion.toEventMetadata(deviceMetadataProvider.provideDeviceMetadata());
    }

    private final SdkConfig getSdkConfig(String sdkToken) {
        return new SdkConfig(CollectionsKt.joinToString$default(this.onfidoConfig.getFlowSteps(), Constants.SEPARATOR_COMMA, null, null, 0, null, new Function1<FlowStep, CharSequence>() { // from class: com.onfido.android.sdk.capture.internal.util.logging.OnfidoLogMapper$getSdkConfig$expectedFlowSteps$1
            @Override // kotlin.jvm.functions.Function1
            public final CharSequence invoke(FlowStep it) {
                Intrinsics.checkNotNullParameter(it, "it");
                String strName = it.getAction().name();
                Locale US = Locale.US;
                Intrinsics.checkNotNullExpressionValue(US, "US");
                String lowerCase = strName.toLowerCase(US);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                return lowerCase;
            }
        }, 30, null), sdkToken, CollectionsKt.emptyList());
    }

    public final LoggerRequest mapLogToRequest(List<OnfidoRemoteLog> logs) {
        String clientUuid;
        String applicantUuid;
        OnfidoLogMapper onfidoLogMapper = this;
        Intrinsics.checkNotNullParameter(logs, "logs");
        String tokenValue = onfidoLogMapper.onfidoConfig.getToken().getTokenValue();
        SDKTokenPayload sDKTokenPayload = SDKTokenPayload.INSTANCE.parseSDKTokenPayload(tokenValue);
        SdkConfig sdkConfig = onfidoLogMapper.getSdkConfig(tokenValue);
        int i = 10;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(logs, 10));
        Iterator it = logs.iterator();
        while (it.hasNext()) {
            OnfidoRemoteLog onfidoRemoteLog = (OnfidoRemoteLog) it.next();
            Pair pair = TuplesKt.to("event_type", JsonElementKt.JsonPrimitive(LOG_EVENT_TYPE));
            Pair pair2 = TuplesKt.to(KEY_LOG_LEVEL, JsonElementKt.JsonPrimitive(onfidoRemoteLog.getLevel()));
            List<String> labels = onfidoRemoteLog.getLabels();
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(labels, i));
            Iterator<T> it2 = labels.iterator();
            while (it2.hasNext()) {
                arrayList2.add(JsonElementKt.JsonPrimitive((String) it2.next()));
            }
            Pair pair3 = TuplesKt.to(KEY_LOG_LABELS, new JsonArray(arrayList2));
            Map<String, String> metadata = onfidoRemoteLog.getMetadata();
            LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(metadata.size()));
            Iterator<T> it3 = metadata.entrySet().iterator();
            while (it3.hasNext()) {
                Map.Entry entry = (Map.Entry) it3.next();
                linkedHashMap.put(entry.getKey(), JsonElementKt.JsonPrimitive((String) entry.getValue()));
            }
            Map mapMapOf = MapsKt.mapOf(pair, pair2, pair3, TuplesKt.to(KEY_LOG_METADATA, new JsonObject(linkedHashMap)));
            Iterator it4 = it;
            ArrayList arrayList3 = arrayList;
            arrayList3.add(new LogBody(LOG_EVENT_TYPE, onfidoLogMapper.uuidProvider.getRandomUuid(), onfidoRemoteLog.getTime(), "sdk", (sDKTokenPayload == null || (applicantUuid = sDKTokenPayload.getApplicantUuid()) == null) ? "unknown" : applicantUuid, onfidoLogMapper.uuidProvider.getPersistedUuid(), (sDKTokenPayload == null || (clientUuid = sDKTokenPayload.getClientUuid()) == null) ? "unknown" : clientUuid, onfidoLogMapper.sessionIdProvider.getSessionId(), mapMapOf, SOURCE_METADATA, onfidoLogMapper.eventMetadata, sdkConfig));
            onfidoLogMapper = this;
            arrayList = arrayList3;
            i = i;
            it = it4;
        }
        return new LoggerRequest(arrayList);
    }
}
