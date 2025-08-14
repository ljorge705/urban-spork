package com.onfido.android.sdk.capture.internal.ui.countryselection;

import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.config.MediaCallbackResultReceiver;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.AnalyticsPropertyKeys;
import java.util.List;
import java.util.Locale;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0087\b\u0018\u0000 <2\u00020\u0001:\u0002;<B[\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\u0010\b\u0001\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0002\u0010\rB3\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\n¢\u0006\u0002\u0010\u000eJ\t\u0010(\u001a\u00020\u0005HÆ\u0003J\t\u0010)\u001a\u00020\u0005HÆ\u0003J\t\u0010*\u001a\u00020\u0005HÆ\u0003J\t\u0010+\u001a\u00020\u0005HÆ\u0003J\u000f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00050\nHÆ\u0003JA\u0010-\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\nHÆ\u0001J\u0013\u0010.\u001a\u00020\"2\b\u0010/\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00100\u001a\u00020\u0003HÖ\u0001J\t\u00101\u001a\u00020\u0005HÖ\u0001J&\u00102\u001a\u0002032\u0006\u00104\u001a\u00020\u00002\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u000208HÁ\u0001¢\u0006\u0002\b9J\u000e\u0010:\u001a\u0004\u0018\u00010\u001c*\u00020\u0005H\u0002R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0013\u0010\u0010\u001a\u0004\b\u0014\u0010\u0012R\u0011\u0010\u0015\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0012R\u001c\u0010\u0007\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0017\u0010\u0010\u001a\u0004\b\u0018\u0010\u0012R\u0013\u0010\u0019\u001a\u0004\u0018\u00010\u00058F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u0012R\u0013\u0010\u001b\u001a\u0004\u0018\u00010\u001c8F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u001c\u0010\b\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001f\u0010\u0010\u001a\u0004\b \u0010\u0012R\u0011\u0010!\u001a\u00020\"8F¢\u0006\u0006\u001a\u0004\b#\u0010$R\"\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\n8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b%\u0010\u0010\u001a\u0004\b&\u0010'¨\u0006="}, d2 = {"Lcom/onfido/android/sdk/capture/internal/ui/countryselection/SupportedDocument;", "", "seen1", "", "countryCodeAlpha2", "", "countryCodeAlpha3", "countryName", "documentTypeAlphaThree", "useCases", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "getCountryCodeAlpha2$annotations", "()V", "getCountryCodeAlpha2", "()Ljava/lang/String;", "getCountryCodeAlpha3$annotations", "getCountryCodeAlpha3", "countryLocaleName", "getCountryLocaleName", "getCountryName$annotations", "getCountryName", "countryNativeName", "getCountryNativeName", "documentType", "Lcom/onfido/android/sdk/capture/DocumentType;", "getDocumentType", "()Lcom/onfido/android/sdk/capture/DocumentType;", "getDocumentTypeAlphaThree$annotations", "getDocumentTypeAlphaThree", "hasValidUseCase", "", "getHasValidUseCase", "()Z", "getUseCases$annotations", "getUseCases", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "component5", Constants.COPY_TYPE, "equals", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_capture_sdk_core_release", "toDocumentType", "$serializer", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Serializable
/* loaded from: classes2.dex */
public final /* data */ class SupportedDocument {
    private final String countryCodeAlpha2;
    private final String countryCodeAlpha3;
    private final String countryName;
    private final String documentTypeAlphaThree;
    private final List<String> useCases;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final KSerializer<Object>[] $childSerializers = {null, null, null, null, new ArrayListSerializer(StringSerializer.INSTANCE)};

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/ui/countryselection/SupportedDocument$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/android/sdk/capture/internal/ui/countryselection/SupportedDocument;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public final KSerializer<SupportedDocument> serializer() {
            return SupportedDocument$$serializer.INSTANCE;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ SupportedDocument(int i, @SerialName("country_alpha2") String str, @SerialName("country_alpha3") String str2, @SerialName(MediaCallbackResultReceiver.KEY_COUNTRY) String str3, @SerialName(AnalyticsPropertyKeys.DOCUMENT_TYPE) String str4, @SerialName("usecases") List list, SerializationConstructorMarker serializationConstructorMarker) {
        if (31 != (i & 31)) {
            PluginExceptionsKt.throwMissingFieldException(i, 31, SupportedDocument$$serializer.INSTANCE.getDescriptor());
        }
        this.countryCodeAlpha2 = str;
        this.countryCodeAlpha3 = str2;
        this.countryName = str3;
        this.documentTypeAlphaThree = str4;
        this.useCases = list;
    }

    public static /* synthetic */ SupportedDocument copy$default(SupportedDocument supportedDocument, String str, String str2, String str3, String str4, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            str = supportedDocument.countryCodeAlpha2;
        }
        if ((i & 2) != 0) {
            str2 = supportedDocument.countryCodeAlpha3;
        }
        String str5 = str2;
        if ((i & 4) != 0) {
            str3 = supportedDocument.countryName;
        }
        String str6 = str3;
        if ((i & 8) != 0) {
            str4 = supportedDocument.documentTypeAlphaThree;
        }
        String str7 = str4;
        if ((i & 16) != 0) {
            list = supportedDocument.useCases;
        }
        return supportedDocument.copy(str, str5, str6, str7, list);
    }

    @SerialName("country_alpha2")
    public static /* synthetic */ void getCountryCodeAlpha2$annotations() {
    }

    @SerialName("country_alpha3")
    public static /* synthetic */ void getCountryCodeAlpha3$annotations() {
    }

    @SerialName(MediaCallbackResultReceiver.KEY_COUNTRY)
    public static /* synthetic */ void getCountryName$annotations() {
    }

    @SerialName(AnalyticsPropertyKeys.DOCUMENT_TYPE)
    public static /* synthetic */ void getDocumentTypeAlphaThree$annotations() {
    }

    @SerialName("usecases")
    public static /* synthetic */ void getUseCases$annotations() {
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private final DocumentType toDocumentType(String str) {
        switch (str.hashCode()) {
            case 67772:
                if (str.equals("DLD")) {
                    return DocumentType.DRIVING_LICENCE;
                }
                return null;
            case 77288:
                if (str.equals("NIC")) {
                    return DocumentType.NATIONAL_IDENTITY_CARD;
                }
                return null;
            case 79439:
                if (str.equals("PPO")) {
                    return DocumentType.PASSPORT;
                }
                return null;
            case 81021:
                if (str.equals("REP")) {
                    return DocumentType.RESIDENCE_PERMIT;
                }
                return null;
            case 84992:
                if (str.equals("VIS")) {
                    return DocumentType.VISA;
                }
                return null;
            case 86012:
                if (str.equals("WKP")) {
                    return DocumentType.WORK_PERMIT;
                }
                return null;
            default:
                return null;
        }
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$onfido_capture_sdk_core_release(SupportedDocument self, CompositeEncoder output, SerialDescriptor serialDesc) {
        KSerializer<Object>[] kSerializerArr = $childSerializers;
        output.encodeStringElement(serialDesc, 0, self.countryCodeAlpha2);
        output.encodeStringElement(serialDesc, 1, self.countryCodeAlpha3);
        output.encodeStringElement(serialDesc, 2, self.countryName);
        output.encodeStringElement(serialDesc, 3, self.documentTypeAlphaThree);
        output.encodeSerializableElement(serialDesc, 4, kSerializerArr[4], self.useCases);
    }

    /* renamed from: component1, reason: from getter */
    public final String getCountryCodeAlpha2() {
        return this.countryCodeAlpha2;
    }

    /* renamed from: component2, reason: from getter */
    public final String getCountryCodeAlpha3() {
        return this.countryCodeAlpha3;
    }

    /* renamed from: component3, reason: from getter */
    public final String getCountryName() {
        return this.countryName;
    }

    /* renamed from: component4, reason: from getter */
    public final String getDocumentTypeAlphaThree() {
        return this.documentTypeAlphaThree;
    }

    public final List<String> component5() {
        return this.useCases;
    }

    public final SupportedDocument copy(String countryCodeAlpha2, String countryCodeAlpha3, String countryName, String documentTypeAlphaThree, List<String> useCases) {
        Intrinsics.checkNotNullParameter(countryCodeAlpha2, "countryCodeAlpha2");
        Intrinsics.checkNotNullParameter(countryCodeAlpha3, "countryCodeAlpha3");
        Intrinsics.checkNotNullParameter(countryName, "countryName");
        Intrinsics.checkNotNullParameter(documentTypeAlphaThree, "documentTypeAlphaThree");
        Intrinsics.checkNotNullParameter(useCases, "useCases");
        return new SupportedDocument(countryCodeAlpha2, countryCodeAlpha3, countryName, documentTypeAlphaThree, useCases);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SupportedDocument)) {
            return false;
        }
        SupportedDocument supportedDocument = (SupportedDocument) other;
        return Intrinsics.areEqual(this.countryCodeAlpha2, supportedDocument.countryCodeAlpha2) && Intrinsics.areEqual(this.countryCodeAlpha3, supportedDocument.countryCodeAlpha3) && Intrinsics.areEqual(this.countryName, supportedDocument.countryName) && Intrinsics.areEqual(this.documentTypeAlphaThree, supportedDocument.documentTypeAlphaThree) && Intrinsics.areEqual(this.useCases, supportedDocument.useCases);
    }

    public final String getCountryCodeAlpha2() {
        return this.countryCodeAlpha2;
    }

    public final String getCountryCodeAlpha3() {
        return this.countryCodeAlpha3;
    }

    public final String getCountryLocaleName() {
        String displayName = new Locale("", this.countryCodeAlpha2).getDisplayName();
        Intrinsics.checkNotNullExpressionValue(displayName, "getDisplayName(...)");
        return displayName;
    }

    public final String getCountryName() {
        return this.countryName;
    }

    public final String getCountryNativeName() {
        return (String) CollectionsKt.getOrNull(StringsKt.split$default((CharSequence) this.countryName, new String[]{" | "}, false, 0, 6, (Object) null), 1);
    }

    public final DocumentType getDocumentType() {
        return toDocumentType(this.documentTypeAlphaThree);
    }

    public final String getDocumentTypeAlphaThree() {
        return this.documentTypeAlphaThree;
    }

    public final boolean getHasValidUseCase() {
        return this.useCases.contains("verify");
    }

    public final List<String> getUseCases() {
        return this.useCases;
    }

    public int hashCode() {
        return (((((((this.countryCodeAlpha2.hashCode() * 31) + this.countryCodeAlpha3.hashCode()) * 31) + this.countryName.hashCode()) * 31) + this.documentTypeAlphaThree.hashCode()) * 31) + this.useCases.hashCode();
    }

    public String toString() {
        return "SupportedDocument(countryCodeAlpha2=" + this.countryCodeAlpha2 + ", countryCodeAlpha3=" + this.countryCodeAlpha3 + ", countryName=" + this.countryName + ", documentTypeAlphaThree=" + this.documentTypeAlphaThree + ", useCases=" + this.useCases + ')';
    }

    public SupportedDocument(String countryCodeAlpha2, String countryCodeAlpha3, String countryName, String documentTypeAlphaThree, List<String> useCases) {
        Intrinsics.checkNotNullParameter(countryCodeAlpha2, "countryCodeAlpha2");
        Intrinsics.checkNotNullParameter(countryCodeAlpha3, "countryCodeAlpha3");
        Intrinsics.checkNotNullParameter(countryName, "countryName");
        Intrinsics.checkNotNullParameter(documentTypeAlphaThree, "documentTypeAlphaThree");
        Intrinsics.checkNotNullParameter(useCases, "useCases");
        this.countryCodeAlpha2 = countryCodeAlpha2;
        this.countryCodeAlpha3 = countryCodeAlpha3;
        this.countryName = countryName;
        this.documentTypeAlphaThree = documentTypeAlphaThree;
        this.useCases = useCases;
    }
}
