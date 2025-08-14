package com.onfido.api.client.data;

import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.AnalyticsPropertyKeys;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.SerializationConstructorMarker;

/* compiled from: DocumentFeatures.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 /2\u00020\u0001:\u0002./BK\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0001\u0010\b\u001a\u00020\u0003\u0012\b\b\u0001\u0010\t\u001a\u00020\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0002\u0010\fB7\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003¢\u0006\u0002\u0010\rJ\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J;\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u0003HÆ\u0001J\u0013\u0010!\u001a\u00020\u00052\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010#\u001a\u00020\u0003HÖ\u0001J\t\u0010$\u001a\u00020%HÖ\u0001J&\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020\u00002\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,HÁ\u0001¢\u0006\u0002\b-R\u001c\u0010\b\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u000f\u001a\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0015\u0010\u000f\u001a\u0004\b\u0016\u0010\u0014R\u001c\u0010\u0007\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0017\u0010\u000f\u001a\u0004\b\u0018\u0010\u0014R\u001c\u0010\t\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0019\u0010\u000f\u001a\u0004\b\u001a\u0010\u0011¨\u00060"}, d2 = {"Lcom/onfido/api/client/data/DocumentFeatures;", "", "seen1", "", "hasNFC", "", "hasCan", "hasPin", "canLength", "pinLength", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IZZZIILkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(ZZZII)V", "getCanLength$annotations", "()V", "getCanLength", "()I", "getHasCan$annotations", "getHasCan", "()Z", "getHasNFC$annotations", "getHasNFC", "getHasPin$annotations", "getHasPin", "getPinLength$annotations", "getPinLength", "component1", "component2", "component3", "component4", "component5", Constants.COPY_TYPE, "equals", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_api_client", "$serializer", "Companion", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Serializable
/* loaded from: classes6.dex */
public final /* data */ class DocumentFeatures {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final int canLength;
    private final boolean hasCan;
    private final boolean hasNFC;
    private final boolean hasPin;
    private final int pinLength;

    public DocumentFeatures() {
        this(false, false, false, 0, 0, 31, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ DocumentFeatures copy$default(DocumentFeatures documentFeatures, boolean z, boolean z2, boolean z3, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            z = documentFeatures.hasNFC;
        }
        if ((i3 & 2) != 0) {
            z2 = documentFeatures.hasCan;
        }
        boolean z4 = z2;
        if ((i3 & 4) != 0) {
            z3 = documentFeatures.hasPin;
        }
        boolean z5 = z3;
        if ((i3 & 8) != 0) {
            i = documentFeatures.canLength;
        }
        int i4 = i;
        if ((i3 & 16) != 0) {
            i2 = documentFeatures.pinLength;
        }
        return documentFeatures.copy(z, z4, z5, i4, i2);
    }

    @SerialName(AnalyticsPropertyKeys.CAN_LENGTH)
    public static /* synthetic */ void getCanLength$annotations() {
    }

    @SerialName(AnalyticsPropertyKeys.HAS_CAN)
    public static /* synthetic */ void getHasCan$annotations() {
    }

    @SerialName("has_nfc")
    public static /* synthetic */ void getHasNFC$annotations() {
    }

    @SerialName(AnalyticsPropertyKeys.HAS_PIN)
    public static /* synthetic */ void getHasPin$annotations() {
    }

    @SerialName(AnalyticsPropertyKeys.PIN_LENGTH)
    public static /* synthetic */ void getPinLength$annotations() {
    }

    /* renamed from: component1, reason: from getter */
    public final boolean getHasNFC() {
        return this.hasNFC;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getHasCan() {
        return this.hasCan;
    }

    /* renamed from: component3, reason: from getter */
    public final boolean getHasPin() {
        return this.hasPin;
    }

    /* renamed from: component4, reason: from getter */
    public final int getCanLength() {
        return this.canLength;
    }

    /* renamed from: component5, reason: from getter */
    public final int getPinLength() {
        return this.pinLength;
    }

    public final DocumentFeatures copy(boolean hasNFC, boolean hasCan, boolean hasPin, int canLength, int pinLength) {
        return new DocumentFeatures(hasNFC, hasCan, hasPin, canLength, pinLength);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DocumentFeatures)) {
            return false;
        }
        DocumentFeatures documentFeatures = (DocumentFeatures) other;
        return this.hasNFC == documentFeatures.hasNFC && this.hasCan == documentFeatures.hasCan && this.hasPin == documentFeatures.hasPin && this.canLength == documentFeatures.canLength && this.pinLength == documentFeatures.pinLength;
    }

    public final int getCanLength() {
        return this.canLength;
    }

    public final boolean getHasCan() {
        return this.hasCan;
    }

    public final boolean getHasNFC() {
        return this.hasNFC;
    }

    public final boolean getHasPin() {
        return this.hasPin;
    }

    public final int getPinLength() {
        return this.pinLength;
    }

    public int hashCode() {
        return (((((((Boolean.hashCode(this.hasNFC) * 31) + Boolean.hashCode(this.hasCan)) * 31) + Boolean.hashCode(this.hasPin)) * 31) + Integer.hashCode(this.canLength)) * 31) + Integer.hashCode(this.pinLength);
    }

    public String toString() {
        return "DocumentFeatures(hasNFC=" + this.hasNFC + ", hasCan=" + this.hasCan + ", hasPin=" + this.hasPin + ", canLength=" + this.canLength + ", pinLength=" + this.pinLength + ")";
    }

    /* compiled from: DocumentFeatures.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/onfido/api/client/data/DocumentFeatures$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/api/client/data/DocumentFeatures;", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<DocumentFeatures> serializer() {
            return DocumentFeatures$$serializer.INSTANCE;
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ DocumentFeatures(int i, @SerialName("has_nfc") boolean z, @SerialName(AnalyticsPropertyKeys.HAS_CAN) boolean z2, @SerialName(AnalyticsPropertyKeys.HAS_PIN) boolean z3, @SerialName(AnalyticsPropertyKeys.CAN_LENGTH) int i2, @SerialName(AnalyticsPropertyKeys.PIN_LENGTH) int i3, SerializationConstructorMarker serializationConstructorMarker) {
        if ((i & 1) == 0) {
            this.hasNFC = false;
        } else {
            this.hasNFC = z;
        }
        if ((i & 2) == 0) {
            this.hasCan = false;
        } else {
            this.hasCan = z2;
        }
        if ((i & 4) == 0) {
            this.hasPin = false;
        } else {
            this.hasPin = z3;
        }
        if ((i & 8) == 0) {
            this.canLength = 0;
        } else {
            this.canLength = i2;
        }
        if ((i & 16) == 0) {
            this.pinLength = 0;
        } else {
            this.pinLength = i3;
        }
    }

    public DocumentFeatures(boolean z, boolean z2, boolean z3, int i, int i2) {
        this.hasNFC = z;
        this.hasCan = z2;
        this.hasPin = z3;
        this.canLength = i;
        this.pinLength = i2;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$onfido_api_client(DocumentFeatures self, CompositeEncoder output, SerialDescriptor serialDesc) {
        if (output.shouldEncodeElementDefault(serialDesc, 0) || self.hasNFC) {
            output.encodeBooleanElement(serialDesc, 0, self.hasNFC);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 1) || self.hasCan) {
            output.encodeBooleanElement(serialDesc, 1, self.hasCan);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 2) || self.hasPin) {
            output.encodeBooleanElement(serialDesc, 2, self.hasPin);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 3) || self.canLength != 0) {
            output.encodeIntElement(serialDesc, 3, self.canLength);
        }
        if (!output.shouldEncodeElementDefault(serialDesc, 4) && self.pinLength == 0) {
            return;
        }
        output.encodeIntElement(serialDesc, 4, self.pinLength);
    }

    public /* synthetic */ DocumentFeatures(boolean z, boolean z2, boolean z3, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? false : z, (i3 & 2) != 0 ? false : z2, (i3 & 4) != 0 ? false : z3, (i3 & 8) != 0 ? 0 : i, (i3 & 16) != 0 ? 0 : i2);
    }
}
