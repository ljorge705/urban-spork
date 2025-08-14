package com.onfido.hosted.web.module.model;

import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.analytics.OnfidoAnalyticsEventResultReceiver;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
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

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0081\b\u0018\u0000 %2\u00020\u0001:\u0003$%&B=\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0016\b\u0001\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000bB#\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0014\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\u0002\u0010\fJ\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\u0017\u0010\u0015\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007HÆ\u0003J+\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0016\b\u0002\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001b\u001a\u00020\bHÖ\u0001J&\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u00002\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"HÁ\u0001¢\u0006\u0002\b#R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R*\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\u000e\u001a\u0004\b\u0012\u0010\u0013¨\u0006'"}, d2 = {"Lcom/onfido/hosted/web/module/model/HostedWebModuleResponseAnalytics;", "", "seen1", "", "event", "Lcom/onfido/hosted/web/module/model/HostedWebModuleResponseAnalytics$Event;", OnfidoAnalyticsEventResultReceiver.KEY_PROPERTIES, "", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/onfido/hosted/web/module/model/HostedWebModuleResponseAnalytics$Event;Ljava/util/Map;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Lcom/onfido/hosted/web/module/model/HostedWebModuleResponseAnalytics$Event;Ljava/util/Map;)V", "getEvent$annotations", "()V", "getEvent", "()Lcom/onfido/hosted/web/module/model/HostedWebModuleResponseAnalytics$Event;", "getProperties$annotations", "getProperties", "()Ljava/util/Map;", "component1", "component2", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_capture_sdk_core_release", "$serializer", "Companion", "Event", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Serializable
/* loaded from: classes6.dex */
public final /* data */ class HostedWebModuleResponseAnalytics {
    private static final KSerializer<Object>[] $childSerializers;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Event event;
    private final Map<String, String> properties;

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/onfido/hosted/web/module/model/HostedWebModuleResponseAnalytics$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/hosted/web/module/model/HostedWebModuleResponseAnalytics;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public final KSerializer<HostedWebModuleResponseAnalytics> serializer() {
            return HostedWebModuleResponseAnalytics$$serializer.INSTANCE;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0081\b\u0018\u0000 \"2\u00020\u0001:\u0002!\"B1\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tB\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\nJ\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0005HÖ\u0001J&\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fHÁ\u0001¢\u0006\u0002\b R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR\u001c\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000f\u0010\f\u001a\u0004\b\u0010\u0010\u000e¨\u0006#"}, d2 = {"Lcom/onfido/hosted/web/module/model/HostedWebModuleResponseAnalytics$Event;", "", "seen1", "", "name", "", "type", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Ljava/lang/String;Ljava/lang/String;)V", "getName$annotations", "()V", "getName", "()Ljava/lang/String;", "getType$annotations", "getType", "component1", "component2", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_capture_sdk_core_release", "$serializer", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    @Serializable
    public static final /* data */ class Event {

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final String name;
        private final String type;

        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/onfido/hosted/web/module/model/HostedWebModuleResponseAnalytics$Event$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/hosted/web/module/model/HostedWebModuleResponseAnalytics$Event;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Companion {
            private Companion() {
            }

            public final KSerializer<Event> serializer() {
                return HostedWebModuleResponseAnalytics$Event$$serializer.INSTANCE;
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }
        }

        @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
        public /* synthetic */ Event(int i, @SerialName("eventName") String str, @SerialName("eventType") String str2, SerializationConstructorMarker serializationConstructorMarker) {
            if (3 != (i & 3)) {
                PluginExceptionsKt.throwMissingFieldException(i, 3, HostedWebModuleResponseAnalytics$Event$$serializer.INSTANCE.getDescriptor());
            }
            this.name = str;
            this.type = str2;
        }

        public static /* synthetic */ Event copy$default(Event event, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = event.name;
            }
            if ((i & 2) != 0) {
                str2 = event.type;
            }
            return event.copy(str, str2);
        }

        @SerialName("eventName")
        public static /* synthetic */ void getName$annotations() {
        }

        @SerialName("eventType")
        public static /* synthetic */ void getType$annotations() {
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$onfido_capture_sdk_core_release(Event self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeStringElement(serialDesc, 0, self.name);
            output.encodeStringElement(serialDesc, 1, self.type);
        }

        /* renamed from: component1, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* renamed from: component2, reason: from getter */
        public final String getType() {
            return this.type;
        }

        public final Event copy(String name, String type) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(type, "type");
            return new Event(name, type);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Event)) {
                return false;
            }
            Event event = (Event) other;
            return Intrinsics.areEqual(this.name, event.name) && Intrinsics.areEqual(this.type, event.type);
        }

        public final String getName() {
            return this.name;
        }

        public final String getType() {
            return this.type;
        }

        public int hashCode() {
            return (this.name.hashCode() * 31) + this.type.hashCode();
        }

        public String toString() {
            return "Event(name=" + this.name + ", type=" + this.type + ')';
        }

        public Event(String name, String type) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(type, "type");
            this.name = name;
            this.type = type;
        }
    }

    static {
        StringSerializer stringSerializer = StringSerializer.INSTANCE;
        $childSerializers = new KSerializer[]{null, new LinkedHashMapSerializer(stringSerializer, stringSerializer)};
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ HostedWebModuleResponseAnalytics(int i, @SerialName("event") Event event, @SerialName(OnfidoAnalyticsEventResultReceiver.KEY_PROPERTIES) Map map, SerializationConstructorMarker serializationConstructorMarker) {
        if (3 != (i & 3)) {
            PluginExceptionsKt.throwMissingFieldException(i, 3, HostedWebModuleResponseAnalytics$$serializer.INSTANCE.getDescriptor());
        }
        this.event = event;
        this.properties = map;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ HostedWebModuleResponseAnalytics copy$default(HostedWebModuleResponseAnalytics hostedWebModuleResponseAnalytics, Event event, Map map, int i, Object obj) {
        if ((i & 1) != 0) {
            event = hostedWebModuleResponseAnalytics.event;
        }
        if ((i & 2) != 0) {
            map = hostedWebModuleResponseAnalytics.properties;
        }
        return hostedWebModuleResponseAnalytics.copy(event, map);
    }

    @SerialName("event")
    public static /* synthetic */ void getEvent$annotations() {
    }

    @SerialName(OnfidoAnalyticsEventResultReceiver.KEY_PROPERTIES)
    public static /* synthetic */ void getProperties$annotations() {
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$onfido_capture_sdk_core_release(HostedWebModuleResponseAnalytics self, CompositeEncoder output, SerialDescriptor serialDesc) {
        KSerializer<Object>[] kSerializerArr = $childSerializers;
        output.encodeSerializableElement(serialDesc, 0, HostedWebModuleResponseAnalytics$Event$$serializer.INSTANCE, self.event);
        output.encodeNullableSerializableElement(serialDesc, 1, kSerializerArr[1], self.properties);
    }

    /* renamed from: component1, reason: from getter */
    public final Event getEvent() {
        return this.event;
    }

    public final Map<String, String> component2() {
        return this.properties;
    }

    public final HostedWebModuleResponseAnalytics copy(Event event, Map<String, String> properties) {
        Intrinsics.checkNotNullParameter(event, "event");
        return new HostedWebModuleResponseAnalytics(event, properties);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HostedWebModuleResponseAnalytics)) {
            return false;
        }
        HostedWebModuleResponseAnalytics hostedWebModuleResponseAnalytics = (HostedWebModuleResponseAnalytics) other;
        return Intrinsics.areEqual(this.event, hostedWebModuleResponseAnalytics.event) && Intrinsics.areEqual(this.properties, hostedWebModuleResponseAnalytics.properties);
    }

    public final Event getEvent() {
        return this.event;
    }

    public final Map<String, String> getProperties() {
        return this.properties;
    }

    public int hashCode() {
        int iHashCode = this.event.hashCode() * 31;
        Map<String, String> map = this.properties;
        return iHashCode + (map == null ? 0 : map.hashCode());
    }

    public String toString() {
        return "HostedWebModuleResponseAnalytics(event=" + this.event + ", properties=" + this.properties + ')';
    }

    public HostedWebModuleResponseAnalytics(Event event, Map<String, String> map) {
        Intrinsics.checkNotNullParameter(event, "event");
        this.event = event;
        this.properties = map;
    }
}
