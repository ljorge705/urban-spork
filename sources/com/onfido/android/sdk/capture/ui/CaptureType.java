package com.onfido.android.sdk.capture.ui;

import com.onfido.android.sdk.capture.config.MediaCallbackResultReceiver;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: CaptureType.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u0006\u0010\u0005\u001a\u00020\u0004J\u0006\u0010\u0006\u001a\u00020\u0004j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/CaptureType;", "", "(Ljava/lang/String;I)V", MediaCallbackResultReceiver.KEY_CAPTURE_TYPE, "", "isPicture", "isVideo", "FACE", "DOCUMENT", "VIDEO", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class CaptureType {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ CaptureType[] $VALUES;
    public static final CaptureType FACE = new CaptureType("FACE", 0);
    public static final CaptureType DOCUMENT = new CaptureType("DOCUMENT", 1);
    public static final CaptureType VIDEO = new CaptureType("VIDEO", 2);

    private static final /* synthetic */ CaptureType[] $values() {
        return new CaptureType[]{FACE, DOCUMENT, VIDEO};
    }

    public static EnumEntries<CaptureType> getEntries() {
        return $ENTRIES;
    }

    public static CaptureType valueOf(String str) {
        return (CaptureType) Enum.valueOf(CaptureType.class, str);
    }

    public static CaptureType[] values() {
        return (CaptureType[]) $VALUES.clone();
    }

    public final boolean isDocument() {
        return this == DOCUMENT;
    }

    public final boolean isPicture() {
        return this == DOCUMENT || this == FACE;
    }

    public final boolean isVideo() {
        return this == VIDEO;
    }

    private CaptureType(String str, int i) {
    }

    static {
        CaptureType[] captureTypeArr$values = $values();
        $VALUES = captureTypeArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(captureTypeArr$values);
    }
}
