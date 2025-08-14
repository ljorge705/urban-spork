package com.onfido.api.client.data;

import com.clevertap.android.sdk.Constants;
import java.util.ArrayList;
import java.util.Iterator;
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
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;

/* compiled from: DocumentCreate.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000  2\u00020\u0001:\u0002\u001f B+\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0010\b\u0001\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tB\u0013\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\nJ\u000f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\u0019\u0010\u0010\u001a\u00020\u00002\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J&\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dHÁ\u0001¢\u0006\u0002\b\u001eR\"\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006!"}, d2 = {"Lcom/onfido/api/client/data/DocumentCreate;", "", "seen1", "", "documentMediaList", "", "Lcom/onfido/api/client/data/DocumentMedia;", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/util/List;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Ljava/util/List;)V", "getDocumentMediaList$annotations", "()V", "getDocumentMediaList", "()Ljava/util/List;", "component1", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_api_client", "$serializer", "Companion", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Serializable
/* loaded from: classes6.dex */
public final /* data */ class DocumentCreate {
    private final List<DocumentMedia> documentMediaList;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final KSerializer<Object>[] $childSerializers = {new ArrayListSerializer(DocumentMedia$$serializer.INSTANCE)};

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ DocumentCreate copy$default(DocumentCreate documentCreate, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = documentCreate.documentMediaList;
        }
        return documentCreate.copy(list);
    }

    @JvmStatic
    public static final DocumentCreate fromBinaryMediaUuidList(List<String> list) {
        return INSTANCE.fromBinaryMediaUuidList(list);
    }

    @SerialName("document_media")
    public static /* synthetic */ void getDocumentMediaList$annotations() {
    }

    public final List<DocumentMedia> component1() {
        return this.documentMediaList;
    }

    public final DocumentCreate copy(List<DocumentMedia> documentMediaList) {
        Intrinsics.checkNotNullParameter(documentMediaList, "documentMediaList");
        return new DocumentCreate(documentMediaList);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof DocumentCreate) && Intrinsics.areEqual(this.documentMediaList, ((DocumentCreate) other).documentMediaList);
    }

    public final List<DocumentMedia> getDocumentMediaList() {
        return this.documentMediaList;
    }

    public int hashCode() {
        return this.documentMediaList.hashCode();
    }

    public String toString() {
        return "DocumentCreate(documentMediaList=" + this.documentMediaList + ")";
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ DocumentCreate(int i, @SerialName("document_media") List list, SerializationConstructorMarker serializationConstructorMarker) {
        if (1 != (i & 1)) {
            PluginExceptionsKt.throwMissingFieldException(i, 1, DocumentCreate$$serializer.INSTANCE.getDescriptor());
        }
        this.documentMediaList = list;
    }

    public DocumentCreate(List<DocumentMedia> documentMediaList) {
        Intrinsics.checkNotNullParameter(documentMediaList, "documentMediaList");
        this.documentMediaList = documentMediaList;
    }

    /* compiled from: DocumentCreate.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u000e\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006H\u0007J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\tHÆ\u0001¨\u0006\n"}, d2 = {"Lcom/onfido/api/client/data/DocumentCreate$Companion;", "", "()V", "fromBinaryMediaUuidList", "Lcom/onfido/api/client/data/DocumentCreate;", "uuidList", "", "", "serializer", "Lkotlinx/serialization/KSerializer;", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<DocumentCreate> serializer() {
            return DocumentCreate$$serializer.INSTANCE;
        }

        @JvmStatic
        public final DocumentCreate fromBinaryMediaUuidList(List<String> uuidList) {
            Intrinsics.checkNotNullParameter(uuidList, "uuidList");
            List listFilterNotNull = CollectionsKt.filterNotNull(uuidList);
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listFilterNotNull, 10));
            Iterator it = listFilterNotNull.iterator();
            while (it.hasNext()) {
                arrayList.add(DocumentMedia.INSTANCE.valueOf((String) it.next()));
            }
            return new DocumentCreate(arrayList);
        }
    }
}
