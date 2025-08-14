package com.onfido.android.sdk.capture.internal.analytics.inhouse.network;

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

@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b1\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0081\b\u0018\u0000 W2\u00020\u0001:\u0002VWBÁ\u0001\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\u0016\b\u0001\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000f\u0012\n\b\u0001\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u0012\n\b\u0001\u0010\u0013\u001a\u0004\u0018\u00010\u0014\u0012\n\b\u0001\u0010\u0015\u001a\u0004\u0018\u00010\u0016\u0012\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018¢\u0006\u0002\u0010\u0019B\u0083\u0001\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\u0006\u0010\f\u001a\u00020\u0005\u0012\u0006\u0010\r\u001a\u00020\u0005\u0012\u0014\b\u0002\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00100\u000f\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0014\u0012\u0006\u0010\u0015\u001a\u00020\u0016¢\u0006\u0002\u0010\u001aJ\t\u0010;\u001a\u00020\u0005HÆ\u0003J\u0015\u0010<\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00100\u000fHÆ\u0003J\t\u0010=\u001a\u00020\u0012HÆ\u0003J\u000b\u0010>\u001a\u0004\u0018\u00010\u0014HÆ\u0003J\t\u0010?\u001a\u00020\u0016HÆ\u0003J\t\u0010@\u001a\u00020\u0005HÆ\u0003J\u000b\u0010A\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010B\u001a\u00020\u0005HÆ\u0003J\t\u0010C\u001a\u00020\u0005HÆ\u0003J\t\u0010D\u001a\u00020\u0005HÆ\u0003J\t\u0010E\u001a\u00020\u0005HÆ\u0003J\t\u0010F\u001a\u00020\u0005HÆ\u0003J\t\u0010G\u001a\u00020\u0005HÆ\u0003J\u009b\u0001\u0010H\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u00052\b\b\u0002\u0010\r\u001a\u00020\u00052\u0014\b\u0002\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00100\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u00122\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u0016HÆ\u0001J\u0013\u0010I\u001a\u00020J2\b\u0010K\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010L\u001a\u00020\u0003HÖ\u0001J\t\u0010M\u001a\u00020\u0005HÖ\u0001J&\u0010N\u001a\u00020O2\u0006\u0010P\u001a\u00020\u00002\u0006\u0010Q\u001a\u00020R2\u0006\u0010S\u001a\u00020THÁ\u0001¢\u0006\u0002\bUR\u001c\u0010\u000b\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u001c\u0010\n\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001f\u0010\u001c\u001a\u0004\b \u0010\u001eR\u001c\u0010\f\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b!\u0010\u001c\u001a\u0004\b\"\u0010\u001eR\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b#\u0010\u001c\u001a\u0004\b$\u0010\u001eR\u001e\u0010\u0013\u001a\u0004\u0018\u00010\u00148\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b%\u0010\u001c\u001a\u0004\b&\u0010'R\u001c\u0010\b\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b(\u0010\u001c\u001a\u0004\b)\u0010\u001eR\u001c\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b*\u0010\u001c\u001a\u0004\b+\u0010\u001eR(\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00100\u000f8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b,\u0010\u001c\u001a\u0004\b-\u0010.R\u001c\u0010\u0015\u001a\u00020\u00168\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b/\u0010\u001c\u001a\u0004\b0\u00101R\u001c\u0010\r\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b2\u0010\u001c\u001a\u0004\b3\u0010\u001eR\u001c\u0010\t\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b4\u0010\u001c\u001a\u0004\b5\u0010\u001eR\u001c\u0010\u0011\u001a\u00020\u00128\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b6\u0010\u001c\u001a\u0004\b7\u00108R\u001e\u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b9\u0010\u001c\u001a\u0004\b:\u0010\u001e¨\u0006X"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/network/AnalyticsEventNetworkModel;", "", "seen1", "", "event", "", "eventUuid", "workflowRunUuid", "eventTime", "source", "applicantUuid", "anonymousUuid", "clientUuid", "sessionUuid", OnfidoAnalyticsEventResultReceiver.KEY_PROPERTIES, "", "Lkotlinx/serialization/json/JsonElement;", "sourceMetadata", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/network/SourceMetaData;", "eventMetadata", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/network/EventMetaData;", "sdkConfig", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/network/SdkConfig;", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/network/SourceMetaData;Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/network/EventMetaData;Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/network/SdkConfig;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/network/SourceMetaData;Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/network/EventMetaData;Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/network/SdkConfig;)V", "getAnonymousUuid$annotations", "()V", "getAnonymousUuid", "()Ljava/lang/String;", "getApplicantUuid$annotations", "getApplicantUuid", "getClientUuid$annotations", "getClientUuid", "getEvent$annotations", "getEvent", "getEventMetadata$annotations", "getEventMetadata", "()Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/network/EventMetaData;", "getEventTime$annotations", "getEventTime", "getEventUuid$annotations", "getEventUuid", "getProperties$annotations", "getProperties", "()Ljava/util/Map;", "getSdkConfig$annotations", "getSdkConfig", "()Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/network/SdkConfig;", "getSessionUuid$annotations", "getSessionUuid", "getSource$annotations", "getSource", "getSourceMetadata$annotations", "getSourceMetadata", "()Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/network/SourceMetaData;", "getWorkflowRunUuid$annotations", "getWorkflowRunUuid", "component1", "component10", "component11", "component12", "component13", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_capture_sdk_core_release", "$serializer", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Serializable
/* loaded from: classes2.dex */
public final /* data */ class AnalyticsEventNetworkModel {
    private final String anonymousUuid;
    private final String applicantUuid;
    private final String clientUuid;
    private final String event;
    private final EventMetaData eventMetadata;
    private final String eventTime;
    private final String eventUuid;
    private final Map<String, JsonElement> properties;
    private final SdkConfig sdkConfig;
    private final String sessionUuid;
    private final String source;
    private final SourceMetaData sourceMetadata;
    private final String workflowRunUuid;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final KSerializer<Object>[] $childSerializers = {null, null, null, null, null, null, null, null, null, new LinkedHashMapSerializer(StringSerializer.INSTANCE, JsonElementSerializer.INSTANCE), null, null, null};

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/network/AnalyticsEventNetworkModel$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/network/AnalyticsEventNetworkModel;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public final KSerializer<AnalyticsEventNetworkModel> serializer() {
            return AnalyticsEventNetworkModel$$serializer.INSTANCE;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ AnalyticsEventNetworkModel(int i, @SerialName("event") String str, @SerialName("event_uuid") String str2, @SerialName("workflow_run_uuid") String str3, @SerialName("event_time") String str4, @SerialName("source") String str5, @SerialName("applicant_uuid") String str6, @SerialName("anonymous_uuid") String str7, @SerialName("client_uuid") String str8, @SerialName("session_uuid") String str9, @SerialName(OnfidoAnalyticsEventResultReceiver.KEY_PROPERTIES) Map map, @SerialName("source_metadata") SourceMetaData sourceMetaData, @SerialName("event_metadata") EventMetaData eventMetaData, @SerialName("sdk_config") SdkConfig sdkConfig, SerializationConstructorMarker serializationConstructorMarker) {
        if (5615 != (i & 5615)) {
            PluginExceptionsKt.throwMissingFieldException(i, 5615, AnalyticsEventNetworkModel$$serializer.INSTANCE.getDescriptor());
        }
        this.event = str;
        this.eventUuid = str2;
        this.workflowRunUuid = str3;
        this.eventTime = str4;
        this.source = (i & 16) == 0 ? "sdk" : str5;
        this.applicantUuid = str6;
        this.anonymousUuid = str7;
        this.clientUuid = str8;
        this.sessionUuid = str9;
        this.properties = (i & 512) == 0 ? MapsKt.emptyMap() : map;
        this.sourceMetadata = sourceMetaData;
        this.eventMetadata = (i & 2048) == 0 ? null : eventMetaData;
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

    @SerialName("workflow_run_uuid")
    public static /* synthetic */ void getWorkflowRunUuid$annotations() {
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$onfido_capture_sdk_core_release(AnalyticsEventNetworkModel self, CompositeEncoder output, SerialDescriptor serialDesc) {
        KSerializer<Object>[] kSerializerArr = $childSerializers;
        output.encodeStringElement(serialDesc, 0, self.event);
        output.encodeStringElement(serialDesc, 1, self.eventUuid);
        output.encodeNullableSerializableElement(serialDesc, 2, StringSerializer.INSTANCE, self.workflowRunUuid);
        output.encodeStringElement(serialDesc, 3, self.eventTime);
        if (output.shouldEncodeElementDefault(serialDesc, 4) || !Intrinsics.areEqual(self.source, "sdk")) {
            output.encodeStringElement(serialDesc, 4, self.source);
        }
        output.encodeStringElement(serialDesc, 5, self.applicantUuid);
        output.encodeStringElement(serialDesc, 6, self.anonymousUuid);
        output.encodeStringElement(serialDesc, 7, self.clientUuid);
        output.encodeStringElement(serialDesc, 8, self.sessionUuid);
        if (output.shouldEncodeElementDefault(serialDesc, 9) || !Intrinsics.areEqual(self.properties, MapsKt.emptyMap())) {
            output.encodeSerializableElement(serialDesc, 9, kSerializerArr[9], self.properties);
        }
        output.encodeSerializableElement(serialDesc, 10, SourceMetaData$$serializer.INSTANCE, self.sourceMetadata);
        if (output.shouldEncodeElementDefault(serialDesc, 11) || self.eventMetadata != null) {
            output.encodeNullableSerializableElement(serialDesc, 11, EventMetaData$$serializer.INSTANCE, self.eventMetadata);
        }
        output.encodeSerializableElement(serialDesc, 12, SdkConfig$$serializer.INSTANCE, self.sdkConfig);
    }

    /* renamed from: component1, reason: from getter */
    public final String getEvent() {
        return this.event;
    }

    public final Map<String, JsonElement> component10() {
        return this.properties;
    }

    /* renamed from: component11, reason: from getter */
    public final SourceMetaData getSourceMetadata() {
        return this.sourceMetadata;
    }

    /* renamed from: component12, reason: from getter */
    public final EventMetaData getEventMetadata() {
        return this.eventMetadata;
    }

    /* renamed from: component13, reason: from getter */
    public final SdkConfig getSdkConfig() {
        return this.sdkConfig;
    }

    /* renamed from: component2, reason: from getter */
    public final String getEventUuid() {
        return this.eventUuid;
    }

    /* renamed from: component3, reason: from getter */
    public final String getWorkflowRunUuid() {
        return this.workflowRunUuid;
    }

    /* renamed from: component4, reason: from getter */
    public final String getEventTime() {
        return this.eventTime;
    }

    /* renamed from: component5, reason: from getter */
    public final String getSource() {
        return this.source;
    }

    /* renamed from: component6, reason: from getter */
    public final String getApplicantUuid() {
        return this.applicantUuid;
    }

    /* renamed from: component7, reason: from getter */
    public final String getAnonymousUuid() {
        return this.anonymousUuid;
    }

    /* renamed from: component8, reason: from getter */
    public final String getClientUuid() {
        return this.clientUuid;
    }

    /* renamed from: component9, reason: from getter */
    public final String getSessionUuid() {
        return this.sessionUuid;
    }

    public final AnalyticsEventNetworkModel copy(String event, String eventUuid, String workflowRunUuid, String eventTime, String source, String applicantUuid, String anonymousUuid, String clientUuid, String sessionUuid, Map<String, ? extends JsonElement> properties, SourceMetaData sourceMetadata, EventMetaData eventMetadata, SdkConfig sdkConfig) {
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
        return new AnalyticsEventNetworkModel(event, eventUuid, workflowRunUuid, eventTime, source, applicantUuid, anonymousUuid, clientUuid, sessionUuid, properties, sourceMetadata, eventMetadata, sdkConfig);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AnalyticsEventNetworkModel)) {
            return false;
        }
        AnalyticsEventNetworkModel analyticsEventNetworkModel = (AnalyticsEventNetworkModel) other;
        return Intrinsics.areEqual(this.event, analyticsEventNetworkModel.event) && Intrinsics.areEqual(this.eventUuid, analyticsEventNetworkModel.eventUuid) && Intrinsics.areEqual(this.workflowRunUuid, analyticsEventNetworkModel.workflowRunUuid) && Intrinsics.areEqual(this.eventTime, analyticsEventNetworkModel.eventTime) && Intrinsics.areEqual(this.source, analyticsEventNetworkModel.source) && Intrinsics.areEqual(this.applicantUuid, analyticsEventNetworkModel.applicantUuid) && Intrinsics.areEqual(this.anonymousUuid, analyticsEventNetworkModel.anonymousUuid) && Intrinsics.areEqual(this.clientUuid, analyticsEventNetworkModel.clientUuid) && Intrinsics.areEqual(this.sessionUuid, analyticsEventNetworkModel.sessionUuid) && Intrinsics.areEqual(this.properties, analyticsEventNetworkModel.properties) && Intrinsics.areEqual(this.sourceMetadata, analyticsEventNetworkModel.sourceMetadata) && Intrinsics.areEqual(this.eventMetadata, analyticsEventNetworkModel.eventMetadata) && Intrinsics.areEqual(this.sdkConfig, analyticsEventNetworkModel.sdkConfig);
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

    public final EventMetaData getEventMetadata() {
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

    public final SourceMetaData getSourceMetadata() {
        return this.sourceMetadata;
    }

    public final String getWorkflowRunUuid() {
        return this.workflowRunUuid;
    }

    public int hashCode() {
        int iHashCode = ((this.event.hashCode() * 31) + this.eventUuid.hashCode()) * 31;
        String str = this.workflowRunUuid;
        int iHashCode2 = (((((((((((((((((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + this.eventTime.hashCode()) * 31) + this.source.hashCode()) * 31) + this.applicantUuid.hashCode()) * 31) + this.anonymousUuid.hashCode()) * 31) + this.clientUuid.hashCode()) * 31) + this.sessionUuid.hashCode()) * 31) + this.properties.hashCode()) * 31) + this.sourceMetadata.hashCode()) * 31;
        EventMetaData eventMetaData = this.eventMetadata;
        return ((iHashCode2 + (eventMetaData != null ? eventMetaData.hashCode() : 0)) * 31) + this.sdkConfig.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("AnalyticsEventNetworkModel(event=");
        sb.append(this.event).append(", eventUuid=").append(this.eventUuid).append(", workflowRunUuid=").append(this.workflowRunUuid).append(", eventTime=").append(this.eventTime).append(", source=").append(this.source).append(", applicantUuid=").append(this.applicantUuid).append(", anonymousUuid=").append(this.anonymousUuid).append(", clientUuid=").append(this.clientUuid).append(", sessionUuid=").append(this.sessionUuid).append(", properties=").append(this.properties).append(", sourceMetadata=").append(this.sourceMetadata).append(", eventMetadata=");
        sb.append(this.eventMetadata).append(", sdkConfig=").append(this.sdkConfig).append(')');
        return sb.toString();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public AnalyticsEventNetworkModel(String event, String eventUuid, String str, String eventTime, String source, String applicantUuid, String anonymousUuid, String clientUuid, String sessionUuid, Map<String, ? extends JsonElement> properties, SourceMetaData sourceMetadata, EventMetaData eventMetaData, SdkConfig sdkConfig) {
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
        this.workflowRunUuid = str;
        this.eventTime = eventTime;
        this.source = source;
        this.applicantUuid = applicantUuid;
        this.anonymousUuid = anonymousUuid;
        this.clientUuid = clientUuid;
        this.sessionUuid = sessionUuid;
        this.properties = properties;
        this.sourceMetadata = sourceMetadata;
        this.eventMetadata = eventMetaData;
        this.sdkConfig = sdkConfig;
    }

    public /* synthetic */ AnalyticsEventNetworkModel(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, Map map, SourceMetaData sourceMetaData, EventMetaData eventMetaData, SdkConfig sdkConfig, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, (i & 16) != 0 ? "sdk" : str5, str6, str7, str8, str9, (i & 512) != 0 ? MapsKt.emptyMap() : map, sourceMetaData, (i & 2048) != 0 ? null : eventMetaData, sdkConfig);
    }
}
