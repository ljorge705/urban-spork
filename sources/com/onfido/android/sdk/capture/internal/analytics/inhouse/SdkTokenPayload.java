package com.onfido.android.sdk.capture.internal.analytics.inhouse;

import com.clevertap.android.sdk.Constants;
import io.sentry.protocol.App;
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
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0081\b\u0018\u0000 \u001f2\u00020\u0001:\u0003\u001e\u001f B%\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bB\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\tJ\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\u0013\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J&\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cHÁ\u0001¢\u0006\u0002\b\u001dR\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006!"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/SdkTokenPayload;", "", "seen1", "", "payload", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/SdkTokenPayload$OnfidoTokenPayload;", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/onfido/android/sdk/capture/internal/analytics/inhouse/SdkTokenPayload$OnfidoTokenPayload;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/SdkTokenPayload$OnfidoTokenPayload;)V", "getPayload$annotations", "()V", "getPayload", "()Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/SdkTokenPayload$OnfidoTokenPayload;", "component1", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_capture_sdk_core_release", "$serializer", "Companion", "OnfidoTokenPayload", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Serializable
/* loaded from: classes2.dex */
public final /* data */ class SdkTokenPayload {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final OnfidoTokenPayload payload;

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/SdkTokenPayload$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/SdkTokenPayload;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public final KSerializer<SdkTokenPayload> serializer() {
            return SdkTokenPayload$$serializer.INSTANCE;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 \"2\u00020\u0001:\u0002!\"B1\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tB\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\nJ\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0005HÖ\u0001J&\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fHÁ\u0001¢\u0006\u0002\b R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR\u001c\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000f\u0010\f\u001a\u0004\b\u0010\u0010\u000e¨\u0006#"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/SdkTokenPayload$OnfidoTokenPayload;", "", "seen1", "", "applicantUuid", "", "clientUuid", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Ljava/lang/String;Ljava/lang/String;)V", "getApplicantUuid$annotations", "()V", "getApplicantUuid", "()Ljava/lang/String;", "getClientUuid$annotations", "getClientUuid", "component1", "component2", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_capture_sdk_core_release", "$serializer", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    @Serializable
    public static final /* data */ class OnfidoTokenPayload {

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final String applicantUuid;
        private final String clientUuid;

        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/SdkTokenPayload$OnfidoTokenPayload$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/SdkTokenPayload$OnfidoTokenPayload;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Companion {
            private Companion() {
            }

            public final KSerializer<OnfidoTokenPayload> serializer() {
                return SdkTokenPayload$OnfidoTokenPayload$$serializer.INSTANCE;
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }
        }

        @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
        public /* synthetic */ OnfidoTokenPayload(int i, @SerialName(App.TYPE) String str, @SerialName("client_uuid") String str2, SerializationConstructorMarker serializationConstructorMarker) {
            if (3 != (i & 3)) {
                PluginExceptionsKt.throwMissingFieldException(i, 3, SdkTokenPayload$OnfidoTokenPayload$$serializer.INSTANCE.getDescriptor());
            }
            this.applicantUuid = str;
            this.clientUuid = str2;
        }

        public static /* synthetic */ OnfidoTokenPayload copy$default(OnfidoTokenPayload onfidoTokenPayload, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = onfidoTokenPayload.applicantUuid;
            }
            if ((i & 2) != 0) {
                str2 = onfidoTokenPayload.clientUuid;
            }
            return onfidoTokenPayload.copy(str, str2);
        }

        @SerialName(App.TYPE)
        public static /* synthetic */ void getApplicantUuid$annotations() {
        }

        @SerialName("client_uuid")
        public static /* synthetic */ void getClientUuid$annotations() {
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$onfido_capture_sdk_core_release(OnfidoTokenPayload self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeStringElement(serialDesc, 0, self.applicantUuid);
            output.encodeStringElement(serialDesc, 1, self.clientUuid);
        }

        /* renamed from: component1, reason: from getter */
        public final String getApplicantUuid() {
            return this.applicantUuid;
        }

        /* renamed from: component2, reason: from getter */
        public final String getClientUuid() {
            return this.clientUuid;
        }

        public final OnfidoTokenPayload copy(String applicantUuid, String clientUuid) {
            Intrinsics.checkNotNullParameter(applicantUuid, "applicantUuid");
            Intrinsics.checkNotNullParameter(clientUuid, "clientUuid");
            return new OnfidoTokenPayload(applicantUuid, clientUuid);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof OnfidoTokenPayload)) {
                return false;
            }
            OnfidoTokenPayload onfidoTokenPayload = (OnfidoTokenPayload) other;
            return Intrinsics.areEqual(this.applicantUuid, onfidoTokenPayload.applicantUuid) && Intrinsics.areEqual(this.clientUuid, onfidoTokenPayload.clientUuid);
        }

        public final String getApplicantUuid() {
            return this.applicantUuid;
        }

        public final String getClientUuid() {
            return this.clientUuid;
        }

        public int hashCode() {
            return (this.applicantUuid.hashCode() * 31) + this.clientUuid.hashCode();
        }

        public String toString() {
            return "OnfidoTokenPayload(applicantUuid=" + this.applicantUuid + ", clientUuid=" + this.clientUuid + ')';
        }

        public OnfidoTokenPayload(String applicantUuid, String clientUuid) {
            Intrinsics.checkNotNullParameter(applicantUuid, "applicantUuid");
            Intrinsics.checkNotNullParameter(clientUuid, "clientUuid");
            this.applicantUuid = applicantUuid;
            this.clientUuid = clientUuid;
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ SdkTokenPayload(int i, @SerialName("payload") OnfidoTokenPayload onfidoTokenPayload, SerializationConstructorMarker serializationConstructorMarker) {
        if (1 != (i & 1)) {
            PluginExceptionsKt.throwMissingFieldException(i, 1, SdkTokenPayload$$serializer.INSTANCE.getDescriptor());
        }
        this.payload = onfidoTokenPayload;
    }

    public static /* synthetic */ SdkTokenPayload copy$default(SdkTokenPayload sdkTokenPayload, OnfidoTokenPayload onfidoTokenPayload, int i, Object obj) {
        if ((i & 1) != 0) {
            onfidoTokenPayload = sdkTokenPayload.payload;
        }
        return sdkTokenPayload.copy(onfidoTokenPayload);
    }

    @SerialName("payload")
    public static /* synthetic */ void getPayload$annotations() {
    }

    /* renamed from: component1, reason: from getter */
    public final OnfidoTokenPayload getPayload() {
        return this.payload;
    }

    public final SdkTokenPayload copy(OnfidoTokenPayload payload) {
        Intrinsics.checkNotNullParameter(payload, "payload");
        return new SdkTokenPayload(payload);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof SdkTokenPayload) && Intrinsics.areEqual(this.payload, ((SdkTokenPayload) other).payload);
    }

    public final OnfidoTokenPayload getPayload() {
        return this.payload;
    }

    public int hashCode() {
        return this.payload.hashCode();
    }

    public String toString() {
        return "SdkTokenPayload(payload=" + this.payload + ')';
    }

    public SdkTokenPayload(OnfidoTokenPayload payload) {
        Intrinsics.checkNotNullParameter(payload, "payload");
        this.payload = payload;
    }
}
