package com.onfido.api.client.data;

import com.clevertap.android.sdk.Constants;
import java.util.List;
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
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.LinkedHashMapSerializer;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

/* compiled from: ErrorData.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0087\b\u0018\u0000 %2\u00020\u0001:\u0003$%&B\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006B/\b\u0011\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0002\u0010\rB\u000f\u0012\b\b\u0002\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000eJ\t\u0010\u0015\u001a\u00020\nHÆ\u0003J\u0013\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\bHÖ\u0001J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001J&\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u00002\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"HÁ\u0001¢\u0006\u0002\b#R\u001c\u0010\t\u001a\u00020\n8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006'"}, d2 = {"Lcom/onfido/api/client/data/ErrorData;", "", "message", "", "type", "id", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "seen1", "", "error", "Lcom/onfido/api/client/data/ErrorData$Error;", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/onfido/api/client/data/ErrorData$Error;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Lcom/onfido/api/client/data/ErrorData$Error;)V", "getError$annotations", "()V", "getError", "()Lcom/onfido/api/client/data/ErrorData$Error;", "getMessage", "()Ljava/lang/String;", "component1", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_api_client", "$serializer", "Companion", "Error", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Serializable
/* loaded from: classes6.dex */
public final /* data */ class ErrorData {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Error error;
    private final String message;

    /* JADX WARN: Multi-variable type inference failed */
    public ErrorData() {
        this((Error) null, 1, (DefaultConstructorMarker) (0 == true ? 1 : 0));
    }

    public static /* synthetic */ ErrorData copy$default(ErrorData errorData, Error error, int i, Object obj) {
        if ((i & 1) != 0) {
            error = errorData.error;
        }
        return errorData.copy(error);
    }

    @SerialName("error")
    public static /* synthetic */ void getError$annotations() {
    }

    /* renamed from: component1, reason: from getter */
    public final Error getError() {
        return this.error;
    }

    public final ErrorData copy(Error error) {
        Intrinsics.checkNotNullParameter(error, "error");
        return new ErrorData(error);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof ErrorData) && Intrinsics.areEqual(this.error, ((ErrorData) other).error);
    }

    public final Error getError() {
        return this.error;
    }

    public final String getMessage() {
        return this.message;
    }

    public int hashCode() {
        return this.error.hashCode();
    }

    public String toString() {
        return "ErrorData(error=" + this.error + ")";
    }

    /* compiled from: ErrorData.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/onfido/api/client/data/ErrorData$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/api/client/data/ErrorData;", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<ErrorData> serializer() {
            return ErrorData$$serializer.INSTANCE;
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ ErrorData(int i, @SerialName("error") Error error, String str, SerializationConstructorMarker serializationConstructorMarker) {
        this.error = (i & 1) == 0 ? new Error((String) null, (String) null, (String) null, (Map) null, 15, (DefaultConstructorMarker) null) : error;
        if ((i & 2) == 0) {
            this.message = this.error.getMessage();
        } else {
            this.message = str;
        }
    }

    public ErrorData(Error error) {
        Intrinsics.checkNotNullParameter(error, "error");
        this.error = error;
        this.message = error.getMessage();
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$onfido_api_client(ErrorData self, CompositeEncoder output, SerialDescriptor serialDesc) {
        if (output.shouldEncodeElementDefault(serialDesc, 0) || !Intrinsics.areEqual(self.error, new Error((String) null, (String) null, (String) null, (Map) null, 15, (DefaultConstructorMarker) null))) {
            output.encodeSerializableElement(serialDesc, 0, ErrorData$Error$$serializer.INSTANCE, self.error);
        }
        if (!output.shouldEncodeElementDefault(serialDesc, 1) && Intrinsics.areEqual(self.message, self.error.getMessage())) {
            return;
        }
        output.encodeStringElement(serialDesc, 1, self.message);
    }

    public /* synthetic */ ErrorData(Error error, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new Error((String) null, (String) null, (String) null, (Map) null, 15, (DefaultConstructorMarker) null) : error);
    }

    /* compiled from: ErrorData.kt */
    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 12\u00020\u0001:\u000201B[\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\u001c\b\u0001\u0010\b\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\n\u0018\u00010\t\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0002\u0010\rBC\b\u0007\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u0012\u001c\b\u0002\u0010\b\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\n\u0018\u00010\t¢\u0006\u0002\u0010\u000eJ\t\u0010\u001e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0005HÆ\u0003J\t\u0010 \u001a\u00020\u0005HÆ\u0003J\u001d\u0010!\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\n\u0018\u00010\tHÆ\u0003JE\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\u001c\b\u0002\u0010\b\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\n\u0018\u00010\tHÆ\u0001J\u0013\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010&\u001a\u00020\u0003HÖ\u0001J\t\u0010'\u001a\u00020\u0005HÖ\u0001J&\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020\u00002\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.HÁ\u0001¢\u0006\u0002\b/R0\u0010\b\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\n\u0018\u00010\t8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R$\u0010\u0007\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0013\u0010\u0010\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R$\u0010\u0006\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0018\u0010\u0010\u001a\u0004\b\u0019\u0010\u0015\"\u0004\b\u001a\u0010\u0017R$\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u001b\u0010\u0010\u001a\u0004\b\u001c\u0010\u0015\"\u0004\b\u001d\u0010\u0017¨\u00062"}, d2 = {"Lcom/onfido/api/client/data/ErrorData$Error;", "", "seen1", "", "type", "", "message", "id", "fields", "", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V", "getFields$annotations", "()V", "getFields", "()Ljava/util/Map;", "getId$annotations", "getId", "()Ljava/lang/String;", "setId", "(Ljava/lang/String;)V", "getMessage$annotations", "getMessage", "setMessage", "getType$annotations", "getType", "setType", "component1", "component2", "component3", "component4", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_api_client", "$serializer", "Companion", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
    @Serializable
    public static final /* data */ class Error {
        private final Map<String, List<String>> fields;
        private String id;
        private String message;
        private String type;

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final KSerializer<Object>[] $childSerializers = {null, null, null, new LinkedHashMapSerializer(StringSerializer.INSTANCE, new ArrayListSerializer(StringSerializer.INSTANCE))};

        public Error() {
            this((String) null, (String) null, (String) null, (Map) null, 15, (DefaultConstructorMarker) null);
        }

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public Error(String type) {
            this(type, (String) null, (String) null, (Map) null, 14, (DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(type, "type");
        }

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public Error(String type, String message) {
            this(type, message, (String) null, (Map) null, 12, (DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(message, "message");
        }

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public Error(String type, String message, String id) {
            this(type, message, id, (Map) null, 8, (DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(message, "message");
            Intrinsics.checkNotNullParameter(id, "id");
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Error copy$default(Error error, String str, String str2, String str3, Map map, int i, Object obj) {
            if ((i & 1) != 0) {
                str = error.type;
            }
            if ((i & 2) != 0) {
                str2 = error.message;
            }
            if ((i & 4) != 0) {
                str3 = error.id;
            }
            if ((i & 8) != 0) {
                map = error.fields;
            }
            return error.copy(str, str2, str3, map);
        }

        @SerialName("fields")
        public static /* synthetic */ void getFields$annotations() {
        }

        @SerialName("id")
        public static /* synthetic */ void getId$annotations() {
        }

        @SerialName("message")
        public static /* synthetic */ void getMessage$annotations() {
        }

        @SerialName("type")
        public static /* synthetic */ void getType$annotations() {
        }

        /* renamed from: component1, reason: from getter */
        public final String getType() {
            return this.type;
        }

        /* renamed from: component2, reason: from getter */
        public final String getMessage() {
            return this.message;
        }

        /* renamed from: component3, reason: from getter */
        public final String getId() {
            return this.id;
        }

        public final Map<String, List<String>> component4() {
            return this.fields;
        }

        public final Error copy(String type, String message, String id, Map<String, ? extends List<String>> fields) {
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(message, "message");
            Intrinsics.checkNotNullParameter(id, "id");
            return new Error(type, message, id, fields);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Error)) {
                return false;
            }
            Error error = (Error) other;
            return Intrinsics.areEqual(this.type, error.type) && Intrinsics.areEqual(this.message, error.message) && Intrinsics.areEqual(this.id, error.id) && Intrinsics.areEqual(this.fields, error.fields);
        }

        public final Map<String, List<String>> getFields() {
            return this.fields;
        }

        public final String getId() {
            return this.id;
        }

        public final String getMessage() {
            return this.message;
        }

        public final String getType() {
            return this.type;
        }

        public int hashCode() {
            int iHashCode = ((((this.type.hashCode() * 31) + this.message.hashCode()) * 31) + this.id.hashCode()) * 31;
            Map<String, List<String>> map = this.fields;
            return iHashCode + (map == null ? 0 : map.hashCode());
        }

        public final void setId(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.id = str;
        }

        public final void setMessage(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.message = str;
        }

        public final void setType(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.type = str;
        }

        public String toString() {
            return "Error(type=" + this.type + ", message=" + this.message + ", id=" + this.id + ", fields=" + this.fields + ")";
        }

        /* compiled from: ErrorData.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/onfido/api/client/data/ErrorData$Error$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/api/client/data/ErrorData$Error;", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<Error> serializer() {
                return ErrorData$Error$$serializer.INSTANCE;
            }
        }

        @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
        public /* synthetic */ Error(int i, @SerialName("type") String str, @SerialName("message") String str2, @SerialName("id") String str3, @SerialName("fields") Map map, SerializationConstructorMarker serializationConstructorMarker) {
            if ((i & 1) == 0) {
                this.type = "Unexpected";
            } else {
                this.type = str;
            }
            if ((i & 2) == 0) {
                this.message = "Unexpected";
            } else {
                this.message = str2;
            }
            if ((i & 4) == 0) {
                this.id = "Unexpected";
            } else {
                this.id = str3;
            }
            if ((i & 8) == 0) {
                this.fields = null;
            } else {
                this.fields = map;
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public Error(String type, String message, String id, Map<String, ? extends List<String>> map) {
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(message, "message");
            Intrinsics.checkNotNullParameter(id, "id");
            this.type = type;
            this.message = message;
            this.id = id;
            this.fields = map;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$onfido_api_client(Error self, CompositeEncoder output, SerialDescriptor serialDesc) {
            KSerializer<Object>[] kSerializerArr = $childSerializers;
            if (output.shouldEncodeElementDefault(serialDesc, 0) || !Intrinsics.areEqual(self.type, "Unexpected")) {
                output.encodeStringElement(serialDesc, 0, self.type);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 1) || !Intrinsics.areEqual(self.message, "Unexpected")) {
                output.encodeStringElement(serialDesc, 1, self.message);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 2) || !Intrinsics.areEqual(self.id, "Unexpected")) {
                output.encodeStringElement(serialDesc, 2, self.id);
            }
            if (!output.shouldEncodeElementDefault(serialDesc, 3) && self.fields == null) {
                return;
            }
            output.encodeNullableSerializableElement(serialDesc, 3, kSerializerArr[3], self.fields);
        }

        public /* synthetic */ Error(String str, String str2, String str3, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? "Unexpected" : str, (i & 2) != 0 ? "Unexpected" : str2, (i & 4) != 0 ? "Unexpected" : str3, (i & 8) != 0 ? null : map);
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ErrorData(String message, String type, String id) {
        this(new Error(type, message, id, (Map) null, 8, (DefaultConstructorMarker) null));
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(id, "id");
    }
}
