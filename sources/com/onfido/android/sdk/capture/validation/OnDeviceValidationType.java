package com.onfido.android.sdk.capture.validation;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lcom/onfido/android/sdk/capture/validation/OnDeviceValidationType;", "", "(Ljava/lang/String;I)V", "GLARE_DETECTION", "BLUR_DETECTION", "EDGES_DETECTION", "PDF_417_BARCODE_DETECTION", "MRZ_DETECTION", "FACE_ON_DOCUMENT_DETECTION", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class OnDeviceValidationType {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ OnDeviceValidationType[] $VALUES;
    public static final OnDeviceValidationType GLARE_DETECTION = new OnDeviceValidationType("GLARE_DETECTION", 0);
    public static final OnDeviceValidationType BLUR_DETECTION = new OnDeviceValidationType("BLUR_DETECTION", 1);
    public static final OnDeviceValidationType EDGES_DETECTION = new OnDeviceValidationType("EDGES_DETECTION", 2);
    public static final OnDeviceValidationType PDF_417_BARCODE_DETECTION = new OnDeviceValidationType("PDF_417_BARCODE_DETECTION", 3);
    public static final OnDeviceValidationType MRZ_DETECTION = new OnDeviceValidationType("MRZ_DETECTION", 4);
    public static final OnDeviceValidationType FACE_ON_DOCUMENT_DETECTION = new OnDeviceValidationType("FACE_ON_DOCUMENT_DETECTION", 5);

    private static final /* synthetic */ OnDeviceValidationType[] $values() {
        return new OnDeviceValidationType[]{GLARE_DETECTION, BLUR_DETECTION, EDGES_DETECTION, PDF_417_BARCODE_DETECTION, MRZ_DETECTION, FACE_ON_DOCUMENT_DETECTION};
    }

    static {
        OnDeviceValidationType[] onDeviceValidationTypeArr$values = $values();
        $VALUES = onDeviceValidationTypeArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(onDeviceValidationTypeArr$values);
    }

    private OnDeviceValidationType(String str, int i) {
    }

    public static EnumEntries<OnDeviceValidationType> getEntries() {
        return $ENTRIES;
    }

    public static OnDeviceValidationType valueOf(String str) {
        return (OnDeviceValidationType) Enum.valueOf(OnDeviceValidationType.class, str);
    }

    public static OnDeviceValidationType[] values() {
        return (OnDeviceValidationType[]) $VALUES.clone();
    }
}
