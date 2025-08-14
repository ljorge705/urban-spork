package com.onfido.android.sdk.capture.utils.mlmodel;

import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.internal.util.OnfidoRectF;
import com.onfido.reactnative.sdk.ReactNativeBridgeUtiles;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0080\b\u0018\u00002\u00020\u0001:\u0001\u001aB\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0007HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001b"}, d2 = {"Lcom/onfido/android/sdk/capture/utils/mlmodel/OnfidoMlDocument;", "", "boundingBox", "Lcom/onfido/android/sdk/capture/internal/util/OnfidoRectF;", ReactNativeBridgeUtiles.KEY_DOCUMENT_SIDE, "Lcom/onfido/android/sdk/capture/utils/mlmodel/OnfidoMlDocument$Side;", "confidenceScore", "", "(Lcom/onfido/android/sdk/capture/internal/util/OnfidoRectF;Lcom/onfido/android/sdk/capture/utils/mlmodel/OnfidoMlDocument$Side;F)V", "getBoundingBox", "()Lcom/onfido/android/sdk/capture/internal/util/OnfidoRectF;", "getConfidenceScore", "()F", "getSide", "()Lcom/onfido/android/sdk/capture/utils/mlmodel/OnfidoMlDocument$Side;", "component1", "component2", "component3", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "", "toString", "", "Side", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class OnfidoMlDocument {
    private final OnfidoRectF boundingBox;
    private final float confidenceScore;
    private final Side side;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/onfido/android/sdk/capture/utils/mlmodel/OnfidoMlDocument$Side;", "", "(Ljava/lang/String;I)V", "FRONT", "BACK", "UNKNOWN", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Side {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ Side[] $VALUES;
        public static final Side FRONT = new Side("FRONT", 0);
        public static final Side BACK = new Side("BACK", 1);
        public static final Side UNKNOWN = new Side("UNKNOWN", 2);

        private static final /* synthetic */ Side[] $values() {
            return new Side[]{FRONT, BACK, UNKNOWN};
        }

        static {
            Side[] sideArr$values = $values();
            $VALUES = sideArr$values;
            $ENTRIES = EnumEntriesKt.enumEntries(sideArr$values);
        }

        private Side(String str, int i) {
        }

        public static EnumEntries<Side> getEntries() {
            return $ENTRIES;
        }

        public static Side valueOf(String str) {
            return (Side) Enum.valueOf(Side.class, str);
        }

        public static Side[] values() {
            return (Side[]) $VALUES.clone();
        }
    }

    public OnfidoMlDocument(OnfidoRectF boundingBox, Side side, float f) {
        Intrinsics.checkNotNullParameter(boundingBox, "boundingBox");
        Intrinsics.checkNotNullParameter(side, "side");
        this.boundingBox = boundingBox;
        this.side = side;
        this.confidenceScore = f;
    }

    public static /* synthetic */ OnfidoMlDocument copy$default(OnfidoMlDocument onfidoMlDocument, OnfidoRectF onfidoRectF, Side side, float f, int i, Object obj) {
        if ((i & 1) != 0) {
            onfidoRectF = onfidoMlDocument.boundingBox;
        }
        if ((i & 2) != 0) {
            side = onfidoMlDocument.side;
        }
        if ((i & 4) != 0) {
            f = onfidoMlDocument.confidenceScore;
        }
        return onfidoMlDocument.copy(onfidoRectF, side, f);
    }

    /* renamed from: component1, reason: from getter */
    public final OnfidoRectF getBoundingBox() {
        return this.boundingBox;
    }

    /* renamed from: component2, reason: from getter */
    public final Side getSide() {
        return this.side;
    }

    /* renamed from: component3, reason: from getter */
    public final float getConfidenceScore() {
        return this.confidenceScore;
    }

    public final OnfidoMlDocument copy(OnfidoRectF boundingBox, Side side, float confidenceScore) {
        Intrinsics.checkNotNullParameter(boundingBox, "boundingBox");
        Intrinsics.checkNotNullParameter(side, "side");
        return new OnfidoMlDocument(boundingBox, side, confidenceScore);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof OnfidoMlDocument)) {
            return false;
        }
        OnfidoMlDocument onfidoMlDocument = (OnfidoMlDocument) other;
        return Intrinsics.areEqual(this.boundingBox, onfidoMlDocument.boundingBox) && this.side == onfidoMlDocument.side && Float.compare(this.confidenceScore, onfidoMlDocument.confidenceScore) == 0;
    }

    public final OnfidoRectF getBoundingBox() {
        return this.boundingBox;
    }

    public final float getConfidenceScore() {
        return this.confidenceScore;
    }

    public final Side getSide() {
        return this.side;
    }

    public int hashCode() {
        return (((this.boundingBox.hashCode() * 31) + this.side.hashCode()) * 31) + Float.hashCode(this.confidenceScore);
    }

    public String toString() {
        return "OnfidoMlDocument(boundingBox=" + this.boundingBox + ", side=" + this.side + ", confidenceScore=" + this.confidenceScore + ')';
    }
}
