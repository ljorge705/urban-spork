package com.onfido.api.client.data;

import com.clevertap.android.sdk.Constants;
import com.nimbusds.jose.jwk.JWKParameterNames;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

/* compiled from: DocumentProperty.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 (2\u00020\u0001:\u0002'(BK\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\b\u0001\u0010\u0004\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\u0005\u0012\b\b\u0001\u0010\u0007\u001a\u00020\b\u0012\u0012\b\u0001\u0010\t\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\u0005\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0002\u0010\fB3\u0012\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\u0010\b\u0002\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005¢\u0006\u0002\u0010\rJ\u0011\u0010\u0017\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\bHÆ\u0003J\u0011\u0010\u0019\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005HÆ\u0003J7\u0010\u001a\u001a\u00020\u00002\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\u0010\b\u0002\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005HÆ\u0001J\u0013\u0010\u001b\u001a\u00020\b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001e\u001a\u00020\u0006HÖ\u0001J&\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u00002\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%HÁ\u0001¢\u0006\u0002\b&R\u001c\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R$\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u000f\u001a\u0004\b\u0013\u0010\u0014R$\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0015\u0010\u000f\u001a\u0004\b\u0016\u0010\u0014¨\u0006)"}, d2 = {"Lcom/onfido/api/client/data/DocumentProperty;", "", "seen1", "", "useCases", "", "", "backRequired", "", "versions", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/util/List;ZLjava/util/List;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Ljava/util/List;ZLjava/util/List;)V", "getBackRequired$annotations", "()V", "getBackRequired", "()Z", "getUseCases$annotations", "getUseCases", "()Ljava/util/List;", "getVersions$annotations", "getVersions", "component1", "component2", "component3", Constants.COPY_TYPE, "equals", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_api_client", "$serializer", "Companion", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Serializable
/* loaded from: classes6.dex */
public final /* data */ class DocumentProperty {
    private final boolean backRequired;
    private final List<String> useCases;
    private final List<String> versions;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final KSerializer<Object>[] $childSerializers = {new ArrayListSerializer(BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE)), null, new ArrayListSerializer(BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE))};

    public DocumentProperty() {
        this((List) null, false, (List) null, 7, (DefaultConstructorMarker) null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ DocumentProperty copy$default(DocumentProperty documentProperty, List list, boolean z, List list2, int i, Object obj) {
        if ((i & 1) != 0) {
            list = documentProperty.useCases;
        }
        if ((i & 2) != 0) {
            z = documentProperty.backRequired;
        }
        if ((i & 4) != 0) {
            list2 = documentProperty.versions;
        }
        return documentProperty.copy(list, z, list2);
    }

    @SerialName("back_required")
    public static /* synthetic */ void getBackRequired$annotations() {
    }

    @SerialName(JWKParameterNames.PUBLIC_KEY_USE)
    public static /* synthetic */ void getUseCases$annotations() {
    }

    @SerialName("versions")
    public static /* synthetic */ void getVersions$annotations() {
    }

    public final List<String> component1() {
        return this.useCases;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getBackRequired() {
        return this.backRequired;
    }

    public final List<String> component3() {
        return this.versions;
    }

    public final DocumentProperty copy(List<String> useCases, boolean backRequired, List<String> versions) {
        Intrinsics.checkNotNullParameter(useCases, "useCases");
        Intrinsics.checkNotNullParameter(versions, "versions");
        return new DocumentProperty(useCases, backRequired, versions);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DocumentProperty)) {
            return false;
        }
        DocumentProperty documentProperty = (DocumentProperty) other;
        return Intrinsics.areEqual(this.useCases, documentProperty.useCases) && this.backRequired == documentProperty.backRequired && Intrinsics.areEqual(this.versions, documentProperty.versions);
    }

    public final boolean getBackRequired() {
        return this.backRequired;
    }

    public final List<String> getUseCases() {
        return this.useCases;
    }

    public final List<String> getVersions() {
        return this.versions;
    }

    public int hashCode() {
        return (((this.useCases.hashCode() * 31) + Boolean.hashCode(this.backRequired)) * 31) + this.versions.hashCode();
    }

    public String toString() {
        return "DocumentProperty(useCases=" + this.useCases + ", backRequired=" + this.backRequired + ", versions=" + this.versions + ")";
    }

    /* compiled from: DocumentProperty.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/onfido/api/client/data/DocumentProperty$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/api/client/data/DocumentProperty;", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<DocumentProperty> serializer() {
            return DocumentProperty$$serializer.INSTANCE;
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ DocumentProperty(int i, @SerialName(JWKParameterNames.PUBLIC_KEY_USE) List list, @SerialName("back_required") boolean z, @SerialName("versions") List list2, SerializationConstructorMarker serializationConstructorMarker) {
        this.useCases = (i & 1) == 0 ? CollectionsKt.emptyList() : list;
        if ((i & 2) == 0) {
            this.backRequired = false;
        } else {
            this.backRequired = z;
        }
        if ((i & 4) == 0) {
            this.versions = CollectionsKt.emptyList();
        } else {
            this.versions = list2;
        }
    }

    public DocumentProperty(List<String> useCases, boolean z, List<String> versions) {
        Intrinsics.checkNotNullParameter(useCases, "useCases");
        Intrinsics.checkNotNullParameter(versions, "versions");
        this.useCases = useCases;
        this.backRequired = z;
        this.versions = versions;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$onfido_api_client(DocumentProperty self, CompositeEncoder output, SerialDescriptor serialDesc) {
        KSerializer<Object>[] kSerializerArr = $childSerializers;
        if (output.shouldEncodeElementDefault(serialDesc, 0) || !Intrinsics.areEqual(self.useCases, CollectionsKt.emptyList())) {
            output.encodeSerializableElement(serialDesc, 0, kSerializerArr[0], self.useCases);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 1) || self.backRequired) {
            output.encodeBooleanElement(serialDesc, 1, self.backRequired);
        }
        if (!output.shouldEncodeElementDefault(serialDesc, 2) && Intrinsics.areEqual(self.versions, CollectionsKt.emptyList())) {
            return;
        }
        output.encodeSerializableElement(serialDesc, 2, kSerializerArr[2], self.versions);
    }

    public /* synthetic */ DocumentProperty(List list, boolean z, List list2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? CollectionsKt.emptyList() : list, (i & 2) != 0 ? false : z, (i & 4) != 0 ? CollectionsKt.emptyList() : list2);
    }
}
