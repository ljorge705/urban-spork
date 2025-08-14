package com.onfido.android.sdk.capture.internal.analytics.inhouse.domain;

import androidx.core.app.FrameMetricsAggregator;
import com.clevertap.android.sdk.Constants;
import com.google.mlkit.common.sdkinternal.OptionalModuleUtils;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001:\u0001(Bq\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\fJ\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0003HÆ\u0003Ju\u0010 \u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010$\u001a\u00020%HÖ\u0001J\t\u0010&\u001a\u00020'HÖ\u0001R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000eR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000eR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000eR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000e¨\u0006)"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/UiAlerts;", "", "faceVideoTimeout", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/UiAlerts$UiAlertType;", "noFace", "multipleFaces", "requestError", "document", "glare", "blur", OptionalModuleUtils.BARCODE, "cutoff", "(Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/UiAlerts$UiAlertType;Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/UiAlerts$UiAlertType;Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/UiAlerts$UiAlertType;Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/UiAlerts$UiAlertType;Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/UiAlerts$UiAlertType;Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/UiAlerts$UiAlertType;Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/UiAlerts$UiAlertType;Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/UiAlerts$UiAlertType;Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/UiAlerts$UiAlertType;)V", "getBarcode", "()Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/UiAlerts$UiAlertType;", "getBlur", "getCutoff", "getDocument", "getFaceVideoTimeout", "getGlare", "getMultipleFaces", "getNoFace", "getRequestError", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "", "toString", "", "UiAlertType", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class UiAlerts {
    private final UiAlertType barcode;
    private final UiAlertType blur;
    private final UiAlertType cutoff;
    private final UiAlertType document;
    private final UiAlertType faceVideoTimeout;
    private final UiAlertType glare;
    private final UiAlertType multipleFaces;
    private final UiAlertType noFace;
    private final UiAlertType requestError;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/UiAlerts$UiAlertType;", "", "(Ljava/lang/String;I)V", "WARNING", "ERROR", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class UiAlertType {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ UiAlertType[] $VALUES;
        public static final UiAlertType WARNING = new UiAlertType("WARNING", 0);
        public static final UiAlertType ERROR = new UiAlertType("ERROR", 1);

        private static final /* synthetic */ UiAlertType[] $values() {
            return new UiAlertType[]{WARNING, ERROR};
        }

        static {
            UiAlertType[] uiAlertTypeArr$values = $values();
            $VALUES = uiAlertTypeArr$values;
            $ENTRIES = EnumEntriesKt.enumEntries(uiAlertTypeArr$values);
        }

        private UiAlertType(String str, int i) {
        }

        public static EnumEntries<UiAlertType> getEntries() {
            return $ENTRIES;
        }

        public static UiAlertType valueOf(String str) {
            return (UiAlertType) Enum.valueOf(UiAlertType.class, str);
        }

        public static UiAlertType[] values() {
            return (UiAlertType[]) $VALUES.clone();
        }
    }

    public UiAlerts() {
        this(null, null, null, null, null, null, null, null, null, FrameMetricsAggregator.EVERY_DURATION, null);
    }

    /* renamed from: component1, reason: from getter */
    public final UiAlertType getFaceVideoTimeout() {
        return this.faceVideoTimeout;
    }

    /* renamed from: component2, reason: from getter */
    public final UiAlertType getNoFace() {
        return this.noFace;
    }

    /* renamed from: component3, reason: from getter */
    public final UiAlertType getMultipleFaces() {
        return this.multipleFaces;
    }

    /* renamed from: component4, reason: from getter */
    public final UiAlertType getRequestError() {
        return this.requestError;
    }

    /* renamed from: component5, reason: from getter */
    public final UiAlertType getDocument() {
        return this.document;
    }

    /* renamed from: component6, reason: from getter */
    public final UiAlertType getGlare() {
        return this.glare;
    }

    /* renamed from: component7, reason: from getter */
    public final UiAlertType getBlur() {
        return this.blur;
    }

    /* renamed from: component8, reason: from getter */
    public final UiAlertType getBarcode() {
        return this.barcode;
    }

    /* renamed from: component9, reason: from getter */
    public final UiAlertType getCutoff() {
        return this.cutoff;
    }

    public final UiAlerts copy(UiAlertType faceVideoTimeout, UiAlertType noFace, UiAlertType multipleFaces, UiAlertType requestError, UiAlertType document, UiAlertType glare, UiAlertType blur, UiAlertType barcode, UiAlertType cutoff) {
        return new UiAlerts(faceVideoTimeout, noFace, multipleFaces, requestError, document, glare, blur, barcode, cutoff);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UiAlerts)) {
            return false;
        }
        UiAlerts uiAlerts = (UiAlerts) other;
        return this.faceVideoTimeout == uiAlerts.faceVideoTimeout && this.noFace == uiAlerts.noFace && this.multipleFaces == uiAlerts.multipleFaces && this.requestError == uiAlerts.requestError && this.document == uiAlerts.document && this.glare == uiAlerts.glare && this.blur == uiAlerts.blur && this.barcode == uiAlerts.barcode && this.cutoff == uiAlerts.cutoff;
    }

    public final UiAlertType getBarcode() {
        return this.barcode;
    }

    public final UiAlertType getBlur() {
        return this.blur;
    }

    public final UiAlertType getCutoff() {
        return this.cutoff;
    }

    public final UiAlertType getDocument() {
        return this.document;
    }

    public final UiAlertType getFaceVideoTimeout() {
        return this.faceVideoTimeout;
    }

    public final UiAlertType getGlare() {
        return this.glare;
    }

    public final UiAlertType getMultipleFaces() {
        return this.multipleFaces;
    }

    public final UiAlertType getNoFace() {
        return this.noFace;
    }

    public final UiAlertType getRequestError() {
        return this.requestError;
    }

    public int hashCode() {
        UiAlertType uiAlertType = this.faceVideoTimeout;
        int iHashCode = (uiAlertType == null ? 0 : uiAlertType.hashCode()) * 31;
        UiAlertType uiAlertType2 = this.noFace;
        int iHashCode2 = (iHashCode + (uiAlertType2 == null ? 0 : uiAlertType2.hashCode())) * 31;
        UiAlertType uiAlertType3 = this.multipleFaces;
        int iHashCode3 = (iHashCode2 + (uiAlertType3 == null ? 0 : uiAlertType3.hashCode())) * 31;
        UiAlertType uiAlertType4 = this.requestError;
        int iHashCode4 = (iHashCode3 + (uiAlertType4 == null ? 0 : uiAlertType4.hashCode())) * 31;
        UiAlertType uiAlertType5 = this.document;
        int iHashCode5 = (iHashCode4 + (uiAlertType5 == null ? 0 : uiAlertType5.hashCode())) * 31;
        UiAlertType uiAlertType6 = this.glare;
        int iHashCode6 = (iHashCode5 + (uiAlertType6 == null ? 0 : uiAlertType6.hashCode())) * 31;
        UiAlertType uiAlertType7 = this.blur;
        int iHashCode7 = (iHashCode6 + (uiAlertType7 == null ? 0 : uiAlertType7.hashCode())) * 31;
        UiAlertType uiAlertType8 = this.barcode;
        int iHashCode8 = (iHashCode7 + (uiAlertType8 == null ? 0 : uiAlertType8.hashCode())) * 31;
        UiAlertType uiAlertType9 = this.cutoff;
        return iHashCode8 + (uiAlertType9 != null ? uiAlertType9.hashCode() : 0);
    }

    public String toString() {
        return "UiAlerts(faceVideoTimeout=" + this.faceVideoTimeout + ", noFace=" + this.noFace + ", multipleFaces=" + this.multipleFaces + ", requestError=" + this.requestError + ", document=" + this.document + ", glare=" + this.glare + ", blur=" + this.blur + ", barcode=" + this.barcode + ", cutoff=" + this.cutoff + ')';
    }

    public UiAlerts(UiAlertType uiAlertType, UiAlertType uiAlertType2, UiAlertType uiAlertType3, UiAlertType uiAlertType4, UiAlertType uiAlertType5, UiAlertType uiAlertType6, UiAlertType uiAlertType7, UiAlertType uiAlertType8, UiAlertType uiAlertType9) {
        this.faceVideoTimeout = uiAlertType;
        this.noFace = uiAlertType2;
        this.multipleFaces = uiAlertType3;
        this.requestError = uiAlertType4;
        this.document = uiAlertType5;
        this.glare = uiAlertType6;
        this.blur = uiAlertType7;
        this.barcode = uiAlertType8;
        this.cutoff = uiAlertType9;
    }

    public /* synthetic */ UiAlerts(UiAlertType uiAlertType, UiAlertType uiAlertType2, UiAlertType uiAlertType3, UiAlertType uiAlertType4, UiAlertType uiAlertType5, UiAlertType uiAlertType6, UiAlertType uiAlertType7, UiAlertType uiAlertType8, UiAlertType uiAlertType9, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : uiAlertType, (i & 2) != 0 ? null : uiAlertType2, (i & 4) != 0 ? null : uiAlertType3, (i & 8) != 0 ? null : uiAlertType4, (i & 16) != 0 ? null : uiAlertType5, (i & 32) != 0 ? null : uiAlertType6, (i & 64) != 0 ? null : uiAlertType7, (i & 128) != 0 ? null : uiAlertType8, (i & 256) == 0 ? uiAlertType9 : null);
    }
}
