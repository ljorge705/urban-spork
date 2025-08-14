package com.onfido.android.sdk.capture.internal.util.logging.network;

import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.analytics.OnfidoAnalyticsEventResultReceiver;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.LinkedHashMapSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonElementSerializer;

@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b.\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0081\b\u0018\u0000 S2\u00020\u0001:\u0002RSBµ\u0001\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\u0016\b\u0001\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e\u0012\n\b\u0001\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u0012\n\b\u0001\u0010\u0012\u001a\u0004\u0018\u00010\u0013\u0012\n\b\u0001\u0010\u0014\u001a\u0004\u0018\u00010\u0015\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017¢\u0006\u0002\u0010\u0018By\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\u0006\u0010\f\u001a\u00020\u0005\u0012\u0014\b\u0002\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000f0\u000e\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0015¢\u0006\u0002\u0010\u0019J\t\u00108\u001a\u00020\u0005HÆ\u0003J\t\u00109\u001a\u00020\u0011HÆ\u0003J\u000b\u0010:\u001a\u0004\u0018\u00010\u0013HÆ\u0003J\t\u0010;\u001a\u00020\u0015HÆ\u0003J\t\u0010<\u001a\u00020\u0005HÆ\u0003J\t\u0010=\u001a\u00020\u0005HÆ\u0003J\t\u0010>\u001a\u00020\u0005HÆ\u0003J\t\u0010?\u001a\u00020\u0005HÆ\u0003J\t\u0010@\u001a\u00020\u0005HÆ\u0003J\t\u0010A\u001a\u00020\u0005HÆ\u0003J\t\u0010B\u001a\u00020\u0005HÆ\u0003J\u0015\u0010C\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000f0\u000eHÆ\u0003J\u008f\u0001\u0010D\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u00052\u0014\b\u0002\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000f0\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u0015HÆ\u0001J\u0013\u0010E\u001a\u00020F2\b\u0010G\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010H\u001a\u00020\u0003HÖ\u0001J\t\u0010I\u001a\u00020\u0005HÖ\u0001J&\u0010J\u001a\u00020K2\u0006\u0010L\u001a\u00020\u00002\u0006\u0010M\u001a\u00020N2\u0006\u0010O\u001a\u00020PHÁ\u0001¢\u0006\u0002\bQR\u001c\u0010\n\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u001c\u0010\t\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001e\u0010\u001b\u001a\u0004\b\u001f\u0010\u001dR\u001c\u0010\u000b\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b \u0010\u001b\u001a\u0004\b!\u0010\u001dR\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\"\u0010\u001b\u001a\u0004\b#\u0010\u001dR\u001e\u0010\u0012\u001a\u0004\u0018\u00010\u00138\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b$\u0010\u001b\u001a\u0004\b%\u0010&R\u001c\u0010\u0007\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b'\u0010\u001b\u001a\u0004\b(\u0010\u001dR\u001c\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b)\u0010\u001b\u001a\u0004\b*\u0010\u001dR(\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000f0\u000e8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b+\u0010\u001b\u001a\u0004\b,\u0010-R\u001c\u0010\u0014\u001a\u00020\u00158\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b.\u0010\u001b\u001a\u0004\b/\u00100R\u001c\u0010\f\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b1\u0010\u001b\u001a\u0004\b2\u0010\u001dR\u001c\u0010\b\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b3\u0010\u001b\u001a\u0004\b4\u0010\u001dR\u001c\u0010\u0010\u001a\u00020\u00118\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b5\u0010\u001b\u001a\u0004\b6\u00107¨\u0006T"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/util/logging/network/LogBody;", "", "seen1", "", "event", "", "eventUuid", "eventTime", "source", "applicantUuid", "anonymousUuid", "clientUuid", "sessionUuid", OnfidoAnalyticsEventResultReceiver.KEY_PROPERTIES, "", "Lkotlinx/serialization/json/JsonElement;", "sourceMetadata", "Lcom/onfido/android/sdk/capture/internal/util/logging/network/SourceMetadata;", "eventMetadata", "Lcom/onfido/android/sdk/capture/internal/util/logging/network/EventMetadata;", "sdkConfig", "Lcom/onfido/android/sdk/capture/internal/util/logging/network/SdkConfig;", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Lcom/onfido/android/sdk/capture/internal/util/logging/network/SourceMetadata;Lcom/onfido/android/sdk/capture/internal/util/logging/network/EventMetadata;Lcom/onfido/android/sdk/capture/internal/util/logging/network/SdkConfig;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Lcom/onfido/android/sdk/capture/internal/util/logging/network/SourceMetadata;Lcom/onfido/android/sdk/capture/internal/util/logging/network/EventMetadata;Lcom/onfido/android/sdk/capture/internal/util/logging/network/SdkConfig;)V", "getAnonymousUuid$annotations", "()V", "getAnonymousUuid", "()Ljava/lang/String;", "getApplicantUuid$annotations", "getApplicantUuid", "getClientUuid$annotations", "getClientUuid", "getEvent$annotations", "getEvent", "getEventMetadata$annotations", "getEventMetadata", "()Lcom/onfido/android/sdk/capture/internal/util/logging/network/EventMetadata;", "getEventTime$annotations", "getEventTime", "getEventUuid$annotations", "getEventUuid", "getProperties$annotations", "getProperties", "()Ljava/util/Map;", "getSdkConfig$annotations", "getSdkConfig", "()Lcom/onfido/android/sdk/capture/internal/util/logging/network/SdkConfig;", "getSessionUuid$annotations", "getSessionUuid", "getSource$annotations", "getSource", "getSourceMetadata$annotations", "getSourceMetadata", "()Lcom/onfido/android/sdk/capture/internal/util/logging/network/SourceMetadata;", "component1", "component10", "component11", "component12", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_capture_sdk_core_release", "$serializer", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Serializable
/* loaded from: classes2.dex */
public final /* data */ class LogBody {
    private final String anonymousUuid;
    private final String applicantUuid;
    private final String clientUuid;
    private final String event;
    private final EventMetadata eventMetadata;
    private final String eventTime;
    private final String eventUuid;
    private final Map<String, JsonElement> properties;
    private final SdkConfig sdkConfig;
    private final String sessionUuid;
    private final String source;
    private final SourceMetadata sourceMetadata;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final KSerializer<Object>[] $childSerializers = {null, null, null, null, null, null, null, null, new LinkedHashMapSerializer(StringSerializer.INSTANCE, JsonElementSerializer.INSTANCE), null, null, null};

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/util/logging/network/LogBody$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/android/sdk/capture/internal/util/logging/network/LogBody;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public final KSerializer<LogBody> serializer() {
            return LogBody$$serializer.INSTANCE;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ LogBody(int i, @SerialName("event") String str, @SerialName("event_uuid") String str2, @SerialName("event_time") String str3, @SerialName("source") String str4, @SerialName("applicant_uuid") String str5, @SerialName("anonymous_uuid") String str6, @SerialName("client_uuid") String str7, @SerialName("session_uuid") String str8, @SerialName(OnfidoAnalyticsEventResultReceiver.KEY_PROPERTIES) Map map, @SerialName("source_metadata") SourceMetadata sourceMetadata, @SerialName("event_metadata") EventMetadata eventMetadata, @SerialName("sdk_config") SdkConfig sdkConfig, SerializationConstructorMarker serializationConstructorMarker) {
        if (2807 != (i & 2807)) {
            PluginExceptionsKt.throwMissingFieldException(i, 2807, LogBody$$serializer.INSTANCE.getDescriptor());
        }
        this.event = str;
        this.eventUuid = str2;
        this.eventTime = str3;
        if ((i & 8) == 0) {
            this.source = "sdk";
        } else {
            this.source = str4;
        }
        this.applicantUuid = str5;
        this.anonymousUuid = str6;
        this.clientUuid = str7;
        this.sessionUuid = str8;
        if ((i & 256) == 0) {
            this.properties = MapsKt.emptyMap();
        } else {
            this.properties = map;
        }
        this.sourceMetadata = sourceMetadata;
        if ((i & 1024) == 0) {
            this.eventMetadata = null;
        } else {
            this.eventMetadata = eventMetadata;
        }
        this.sdkConfig = sdkConfig;
    }

    @SerialName("anonymous_uuid")
    public static /* synthetic */ void getAnonymousUuid$annotations() {
    }

    @SerialName("applicant_uuid")
    public static /* synthetic */ void getApplicantUuid$annotations() {
    }

    @SerialName("client_uuid")
    public static /* synthetic */ void getClientUuid$annotations() {
    }

    @SerialName("event")
    public static /* synthetic */ void getEvent$annotations() {
    }

    @SerialName("event_metadata")
    public static /* synthetic */ void getEventMetadata$annotations() {
    }

    @SerialName("event_time")
    public static /* synthetic */ void getEventTime$annotations() {
    }

    @SerialName("event_uuid")
    public static /* synthetic */ void getEventUuid$annotations() {
    }

    @SerialName(OnfidoAnalyticsEventResultReceiver.KEY_PROPERTIES)
    public static /* synthetic */ void getProperties$annotations() {
    }

    @SerialName("sdk_config")
    public static /* synthetic */ void getSdkConfig$annotations() {
    }

    @SerialName("session_uuid")
    public static /* synthetic */ void getSessionUuid$annotations() {
    }

    @SerialName("source")
    public static /* synthetic */ void getSource$annotations() {
    }

    @SerialName("source_metadata")
    public static /* synthetic */ void getSourceMetadata$annotations() {
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$onfido_capture_sdk_core_release(LogBody self, CompositeEncoder output, SerialDescriptor serialDesc) {
        KSerializer<Object>[] kSerializerArr = $childSerializers;
        output.encodeStringElement(serialDesc, 0, self.event);
        output.encodeStringElement(serialDesc, 1, self.eventUuid);
        output.encodeStringElement(serialDesc, 2, self.eventTime);
        if (output.shouldEncodeElementDefault(serialDesc, 3) || !Intrinsics.areEqual(self.source, "sdk")) {
            output.encodeStringElement(serialDesc, 3, self.source);
        }
        output.encodeStringElement(serialDesc, 4, self.applicantUuid);
        output.encodeStringElement(serialDesc, 5, self.anonymousUuid);
        output.encodeStringElement(serialDesc, 6, self.clientUuid);
        output.encodeStringElement(serialDesc, 7, self.sessionUuid);
        if (output.shouldEncodeElementDefault(serialDesc, 8) || !Intrinsics.areEqual(self.properties, MapsKt.emptyMap())) {
            output.encodeSerializableElement(serialDesc, 8, kSerializerArr[8], self.properties);
        }
        output.encodeSerializableElement(serialDesc, 9, SourceMetadata$$serializer.INSTANCE, self.sourceMetadata);
        if (output.shouldEncodeElementDefault(serialDesc, 10) || self.eventMetadata != null) {
            output.encodeNullableSerializableElement(serialDesc, 10, EventMetadata$$serializer.INSTANCE, self.eventMetadata);
        }
        output.encodeSerializableElement(serialDesc, 11, SdkConfig$$serializer.INSTANCE, self.sdkConfig);
    }

    /* renamed from: component1, reason: from getter */
    public final String getEvent() {
        return this.event;
    }

    /* renamed from: component10, reason: from getter */
    public final SourceMetadata getSourceMetadata() {
        return this.sourceMetadata;
    }

    /* renamed from: component11, reason: from getter */
    public final EventMetadata getEventMetadata() {
        return this.eventMetadata;
    }

    /* renamed from: component12, reason: from getter */
    public final SdkConfig getSdkConfig() {
        return this.sdkConfig;
    }

    /* renamed from: component2, reason: from getter */
    public final String getEventUuid() {
        return this.eventUuid;
    }

    /* renamed from: component3, reason: from getter */
    public final String getEventTime() {
        return this.eventTime;
    }

    /* renamed from: component4, reason: from getter */
    public final String getSource() {
        return this.source;
    }

    /* renamed from: component5, reason: from getter */
    public final String getApplicantUuid() {
        return this.applicantUuid;
    }

    /* renamed from: component6, reason: from getter */
    public final String getAnonymousUuid() {
        return this.anonymousUuid;
    }

    /* renamed from: component7, reason: from getter */
    public final String getClientUuid() {
        return this.clientUuid;
    }

    /* renamed from: component8, reason: from getter */
    public final String getSessionUuid() {
        return this.sessionUuid;
    }

    public final Map<String, JsonElement> component9() {
        return this.properties;
    }

    public final LogBody copy(String event, String eventUuid, String eventTime, String source, String applicantUuid, String anonymousUuid, String clientUuid, String sessionUuid, Map<String, ? extends JsonElement> properties, SourceMetadata sourceMetadata, EventMetadata eventMetadata, SdkConfig sdkConfig) {
        Intrinsics.checkNotNullParameter(event, "event");
        Intrinsics.checkNotNullParameter(eventUuid, "eventUuid");
        Intrinsics.checkNotNullParameter(eventTime, "eventTime");
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(applicantUuid, "applicantUuid");
        Intrinsics.checkNotNullParameter(anonymousUuid, "anonymousUuid");
        Intrinsics.checkNotNullParameter(clientUuid, "clientUuid");
        Intrinsics.checkNotNullParameter(sessionUuid, "sessionUuid");
        Intrinsics.checkNotNullParameter(properties, "properties");
        Intrinsics.checkNotNullParameter(sourceMetadata, "sourceMetadata");
        Intrinsics.checkNotNullParameter(sdkConfig, "sdkConfig");
        return new LogBody(event, eventUuid, eventTime, source, applicantUuid, anonymousUuid, clientUuid, sessionUuid, properties, sourceMetadata, eventMetadata, sdkConfig);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LogBody)) {
            return false;
        }
        LogBody logBody = (LogBody) other;
        return Intrinsics.areEqual(this.event, logBody.event) && Intrinsics.areEqual(this.eventUuid, logBody.eventUuid) && Intrinsics.areEqual(this.eventTime, logBody.eventTime) && Intrinsics.areEqual(this.source, logBody.source) && Intrinsics.areEqual(this.applicantUuid, logBody.applicantUuid) && Intrinsics.areEqual(this.anonymousUuid, logBody.anonymousUuid) && Intrinsics.areEqual(this.clientUuid, logBody.clientUuid) && Intrinsics.areEqual(this.sessionUuid, logBody.sessionUuid) && Intrinsics.areEqual(this.properties, logBody.properties) && Intrinsics.areEqual(this.sourceMetadata, logBody.sourceMetadata) && Intrinsics.areEqual(this.eventMetadata, logBody.eventMetadata) && Intrinsics.areEqual(this.sdkConfig, logBody.sdkConfig);
    }

    public final String getAnonymousUuid() {
        return this.anonymousUuid;
    }

    public final String getApplicantUuid() {
        return this.applicantUuid;
    }

    public final String getClientUuid() {
        return this.clientUuid;
    }

    public final String getEvent() {
        return this.event;
    }

    public final EventMetadata getEventMetadata() {
        return this.eventMetadata;
    }

    public final String getEventTime() {
        return this.eventTime;
    }

    public final String getEventUuid() {
        return this.eventUuid;
    }

    public final Map<String, JsonElement> getProperties() {
        return this.properties;
    }

    public final SdkConfig getSdkConfig() {
        return this.sdkConfig;
    }

    public final String getSessionUuid() {
        return this.sessionUuid;
    }

    public final String getSource() {
        return this.source;
    }

    public final SourceMetadata getSourceMetadata() {
        return this.sourceMetadata;
    }

    public int hashCode() {
        int iHashCode = ((((((((((((((((((this.event.hashCode() * 31) + this.eventUuid.hashCode()) * 31) + this.eventTime.hashCode()) * 31) + this.source.hashCode()) * 31) + this.applicantUuid.hashCode()) * 31) + this.anonymousUuid.hashCode()) * 31) + this.clientUuid.hashCode()) * 31) + this.sessionUuid.hashCode()) * 31) + this.properties.hashCode()) * 31) + this.sourceMetadata.hashCode()) * 31;
        EventMetadata eventMetadata = this.eventMetadata;
        return ((iHashCode + (eventMetadata == null ? 0 : eventMetadata.hashCode())) * 31) + this.sdkConfig.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("LogBody(event=");
        sb.append(this.event).append(", eventUuid=").append(this.eventUuid).append(", eventTime=").append(this.eventTime).append(", source=").append(this.source).append(", applicantUuid=").append(this.applicantUuid).append(", anonymousUuid=").append(this.anonymousUuid).append(", clientUuid=").append(this.clientUuid).append(", sessionUuid=").append(this.sessionUuid).append(", properties=").append(this.properties).append(", sourceMetadata=").append(this.sourceMetadata).append(", eventMetadata=").append(this.eventMetadata).append(", sdkConfig=");
        sb.append(this.sdkConfig).append(')');
        return sb.toString();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public LogBody(String event, String eventUuid, String eventTime, String source, String applicantUuid, String anonymousUuid, String clientUuid, String sessionUuid, Map<String, ? extends JsonElement> properties, SourceMetadata sourceMetadata, EventMetadata eventMetadata, SdkConfig sdkConfig) {
        Intrinsics.checkNotNullParameter(event, "event");
        Intrinsics.checkNotNullParameter(eventUuid, "eventUuid");
        Intrinsics.checkNotNullParameter(eventTime, "eventTime");
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(applicantUuid, "applicantUuid");
        Intrinsics.checkNotNullParameter(anonymousUuid, "anonymousUuid");
        Intrinsics.checkNotNullParameter(clientUuid, "clientUuid");
        Intrinsics.checkNotNullParameter(sessionUuid, "sessionUuid");
        Intrinsics.checkNotNullParameter(properties, "properties");
        Intrinsics.checkNotNullParameter(sourceMetadata, "sourceMetadata");
        Intrinsics.checkNotNullParameter(sdkConfig, "sdkConfig");
        this.event = event;
        this.eventUuid = eventUuid;
        this.eventTime = eventTime;
        this.source = source;
        this.applicantUuid = applicantUuid;
        this.anonymousUuid = anonymousUuid;
        this.clientUuid = clientUuid;
        this.sessionUuid = sessionUuid;
        this.properties = properties;
        this.sourceMetadata = sourceMetadata;
        this.eventMetadata = eventMetadata;
        this.sdkConfig = sdkConfig;
    }

    public /* synthetic */ LogBody(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, Map map, SourceMetadata sourceMetadata, EventMetadata eventMetadata, SdkConfig sdkConfig, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, (i & 8) != 0 ? "sdk" : str4, str5, str6, str7, str8, (i & 256) != 0 ? MapsKt.emptyMap() : map, sourceMetadata, (i & 1024) != 0 ? null : eventMetadata, sdkConfig);
    }
}
