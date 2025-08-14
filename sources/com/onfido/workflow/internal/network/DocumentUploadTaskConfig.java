package com.onfido.workflow.internal.network;

import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.AnalyticsPropertyKeys;
import com.onfido.api.client.data.DocType;
import com.onfido.api.client.data.DocType$$serializer;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.DebugKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;

/* compiled from: WorkflowResponse.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0001\u0018\u0000 \u001d2\u00020\u0001:\u0004\u001c\u001d\u001e\u001fB7\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0010\b\u0001\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000bB!\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\u0010\fJ&\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aHÁ\u0001¢\u0006\u0002\b\u001bR\"\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00078\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\u000e\u001a\u0004\b\u0012\u0010\u0013¨\u0006 "}, d2 = {"Lcom/onfido/workflow/internal/network/DocumentUploadTaskConfig;", "", "seen1", "", "nfcProcessingOption", "Lcom/onfido/workflow/internal/network/DocumentUploadTaskConfig$NFCProcessingOption;", "documentSelection", "", "Lcom/onfido/workflow/internal/network/DocumentUploadTaskConfig$DocumentSelectionItem;", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/onfido/workflow/internal/network/DocumentUploadTaskConfig$NFCProcessingOption;Ljava/util/List;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Lcom/onfido/workflow/internal/network/DocumentUploadTaskConfig$NFCProcessingOption;Ljava/util/List;)V", "getDocumentSelection$annotations", "()V", "getDocumentSelection", "()Ljava/util/List;", "getNfcProcessingOption$annotations", "getNfcProcessingOption", "()Lcom/onfido/workflow/internal/network/DocumentUploadTaskConfig$NFCProcessingOption;", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_workflow_release", "$serializer", "Companion", "DocumentSelectionItem", "NFCProcessingOption", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Serializable
/* loaded from: classes6.dex */
public final class DocumentUploadTaskConfig {
    private final List<DocumentSelectionItem> documentSelection;
    private final NFCProcessingOption nfcProcessingOption;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final KSerializer<Object>[] $childSerializers = {null, new ArrayListSerializer(DocumentUploadTaskConfig$DocumentSelectionItem$$serializer.INSTANCE)};

    /* JADX WARN: Multi-variable type inference failed */
    public DocumentUploadTaskConfig() {
        this((NFCProcessingOption) null, (List) (0 == true ? 1 : 0), 3, (DefaultConstructorMarker) (0 == true ? 1 : 0));
    }

    @SerialName("document_selection")
    public static /* synthetic */ void getDocumentSelection$annotations() {
    }

    @SerialName("nfc_options")
    public static /* synthetic */ void getNfcProcessingOption$annotations() {
    }

    public final List<DocumentSelectionItem> getDocumentSelection() {
        return this.documentSelection;
    }

    public final NFCProcessingOption getNfcProcessingOption() {
        return this.nfcProcessingOption;
    }

    /* compiled from: WorkflowResponse.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/onfido/workflow/internal/network/DocumentUploadTaskConfig$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/workflow/internal/network/DocumentUploadTaskConfig;", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<DocumentUploadTaskConfig> serializer() {
            return DocumentUploadTaskConfig$$serializer.INSTANCE;
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ DocumentUploadTaskConfig(int i, @SerialName("nfc_options") NFCProcessingOption nFCProcessingOption, @SerialName("document_selection") List list, SerializationConstructorMarker serializationConstructorMarker) {
        this.nfcProcessingOption = (i & 1) == 0 ? null : nFCProcessingOption;
        if ((i & 2) == 0) {
            this.documentSelection = CollectionsKt.emptyList();
        } else {
            this.documentSelection = list;
        }
    }

    public DocumentUploadTaskConfig(NFCProcessingOption nFCProcessingOption, List<DocumentSelectionItem> documentSelection) {
        Intrinsics.checkNotNullParameter(documentSelection, "documentSelection");
        this.nfcProcessingOption = nFCProcessingOption;
        this.documentSelection = documentSelection;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$onfido_workflow_release(DocumentUploadTaskConfig self, CompositeEncoder output, SerialDescriptor serialDesc) {
        KSerializer<Object>[] kSerializerArr = $childSerializers;
        if (output.shouldEncodeElementDefault(serialDesc, 0) || self.nfcProcessingOption != null) {
            output.encodeNullableSerializableElement(serialDesc, 0, DocumentUploadTaskConfig$NFCProcessingOption$$serializer.INSTANCE, self.nfcProcessingOption);
        }
        if (!output.shouldEncodeElementDefault(serialDesc, 1) && Intrinsics.areEqual(self.documentSelection, CollectionsKt.emptyList())) {
            return;
        }
        output.encodeSerializableElement(serialDesc, 1, kSerializerArr[1], self.documentSelection);
    }

    /* compiled from: WorkflowResponse.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 \u001c2\u00020\u0001:\u0002\u001b\u001cB1\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nB\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\u000bJ&\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019HÁ\u0001¢\u0006\u0002\b\u001aR\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\r\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001d"}, d2 = {"Lcom/onfido/workflow/internal/network/DocumentUploadTaskConfig$DocumentSelectionItem;", "", "seen1", "", "documentType", "Lcom/onfido/api/client/data/DocType;", "iso3CountryCode", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/onfido/api/client/data/DocType;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Lcom/onfido/api/client/data/DocType;Ljava/lang/String;)V", "getDocumentType$annotations", "()V", "getDocumentType", "()Lcom/onfido/api/client/data/DocType;", "getIso3CountryCode$annotations", "getIso3CountryCode", "()Ljava/lang/String;", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_workflow_release", "$serializer", "Companion", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    @Serializable
    public static final class DocumentSelectionItem {

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final DocType documentType;
        private final String iso3CountryCode;

        @SerialName(AnalyticsPropertyKeys.DOCUMENT_TYPE)
        public static /* synthetic */ void getDocumentType$annotations() {
        }

        @SerialName("issuing_country")
        public static /* synthetic */ void getIso3CountryCode$annotations() {
        }

        public final DocType getDocumentType() {
            return this.documentType;
        }

        public final String getIso3CountryCode() {
            return this.iso3CountryCode;
        }

        /* compiled from: WorkflowResponse.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/onfido/workflow/internal/network/DocumentUploadTaskConfig$DocumentSelectionItem$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/workflow/internal/network/DocumentUploadTaskConfig$DocumentSelectionItem;", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<DocumentSelectionItem> serializer() {
                return DocumentUploadTaskConfig$DocumentSelectionItem$$serializer.INSTANCE;
            }
        }

        @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
        public /* synthetic */ DocumentSelectionItem(int i, @SerialName(AnalyticsPropertyKeys.DOCUMENT_TYPE) DocType docType, @SerialName("issuing_country") String str, SerializationConstructorMarker serializationConstructorMarker) {
            if (3 != (i & 3)) {
                PluginExceptionsKt.throwMissingFieldException(i, 3, DocumentUploadTaskConfig$DocumentSelectionItem$$serializer.INSTANCE.getDescriptor());
            }
            this.documentType = docType;
            this.iso3CountryCode = str;
        }

        public DocumentSelectionItem(DocType documentType, String iso3CountryCode) {
            Intrinsics.checkNotNullParameter(documentType, "documentType");
            Intrinsics.checkNotNullParameter(iso3CountryCode, "iso3CountryCode");
            this.documentType = documentType;
            this.iso3CountryCode = iso3CountryCode;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$onfido_workflow_release(DocumentSelectionItem self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeSerializableElement(serialDesc, 0, DocType$$serializer.INSTANCE, self.documentType);
            output.encodeStringElement(serialDesc, 1, self.iso3CountryCode);
        }
    }

    public /* synthetic */ DocumentUploadTaskConfig(NFCProcessingOption nFCProcessingOption, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : nFCProcessingOption, (i & 2) != 0 ? CollectionsKt.emptyList() : list);
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: WorkflowResponse.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\b\u0087\u0081\u0002\u0018\u0000 \u000b2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0002\n\u000bB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\f"}, d2 = {"Lcom/onfido/workflow/internal/network/DocumentUploadTaskConfig$NFCProcessingOption;", "", "rawName", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getRawName", "()Ljava/lang/String;", "OFF", "OPTIONAL", "ONLY", "$serializer", "Companion", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    @Serializable
    public static final class NFCProcessingOption {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ NFCProcessingOption[] $VALUES;
        private static final Lazy<KSerializer<Object>> $cachedSerializer$delegate;

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE;
        private final String rawName;

        @SerialName(DebugKt.DEBUG_PROPERTY_VALUE_OFF)
        public static final NFCProcessingOption OFF = new NFCProcessingOption("OFF", 0, DebugKt.DEBUG_PROPERTY_VALUE_OFF);

        @SerialName("optional")
        public static final NFCProcessingOption OPTIONAL = new NFCProcessingOption("OPTIONAL", 1, "optional");

        @SerialName("required")
        public static final NFCProcessingOption ONLY = new NFCProcessingOption("ONLY", 2, "required");

        private static final /* synthetic */ NFCProcessingOption[] $values() {
            return new NFCProcessingOption[]{OFF, OPTIONAL, ONLY};
        }

        public static EnumEntries<NFCProcessingOption> getEntries() {
            return $ENTRIES;
        }

        public static NFCProcessingOption valueOf(String str) {
            return (NFCProcessingOption) Enum.valueOf(NFCProcessingOption.class, str);
        }

        public static NFCProcessingOption[] values() {
            return (NFCProcessingOption[]) $VALUES.clone();
        }

        public final String getRawName() {
            return this.rawName;
        }

        /* compiled from: WorkflowResponse.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/onfido/workflow/internal/network/DocumentUploadTaskConfig$NFCProcessingOption$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/workflow/internal/network/DocumentUploadTaskConfig$NFCProcessingOption;", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            private final /* synthetic */ KSerializer get$cachedSerializer() {
                return (KSerializer) NFCProcessingOption.$cachedSerializer$delegate.getValue();
            }

            public final KSerializer<NFCProcessingOption> serializer() {
                return get$cachedSerializer();
            }
        }

        private NFCProcessingOption(String str, int i, String str2) {
            this.rawName = str2;
        }

        static {
            NFCProcessingOption[] nFCProcessingOptionArr$values = $values();
            $VALUES = nFCProcessingOptionArr$values;
            $ENTRIES = EnumEntriesKt.enumEntries(nFCProcessingOptionArr$values);
            INSTANCE = new Companion(null);
            $cachedSerializer$delegate = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0) new Function0<KSerializer<Object>>() { // from class: com.onfido.workflow.internal.network.DocumentUploadTaskConfig.NFCProcessingOption.Companion.1
                @Override // kotlin.jvm.functions.Function0
                public final KSerializer<Object> invoke() {
                    return DocumentUploadTaskConfig$NFCProcessingOption$$serializer.INSTANCE;
                }
            });
        }
    }
}
