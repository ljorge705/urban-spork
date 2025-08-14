package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.util;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\b\u0080\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\r¨\u0006\u000e"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/camera2/util/ApiLevel;", "", "versionCode", "", "(Ljava/lang/String;II)V", "getVersionCode", "()I", "LOLLIPOP", "MARSHMALLOW", "NOUGAT", "OREO", "PIE", "V10", "V11", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ApiLevel {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ ApiLevel[] $VALUES;
    public static final ApiLevel LOLLIPOP = new ApiLevel("LOLLIPOP", 0, 21);
    public static final ApiLevel MARSHMALLOW = new ApiLevel("MARSHMALLOW", 1, 23);
    public static final ApiLevel NOUGAT = new ApiLevel("NOUGAT", 2, 24);
    public static final ApiLevel OREO = new ApiLevel("OREO", 3, 26);
    public static final ApiLevel PIE = new ApiLevel("PIE", 4, 28);
    public static final ApiLevel V10 = new ApiLevel("V10", 5, 29);
    public static final ApiLevel V11 = new ApiLevel("V11", 6, 30);
    private final int versionCode;

    private static final /* synthetic */ ApiLevel[] $values() {
        return new ApiLevel[]{LOLLIPOP, MARSHMALLOW, NOUGAT, OREO, PIE, V10, V11};
    }

    static {
        ApiLevel[] apiLevelArr$values = $values();
        $VALUES = apiLevelArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(apiLevelArr$values);
    }

    private ApiLevel(String str, int i, int i2) {
        this.versionCode = i2;
    }

    public static EnumEntries<ApiLevel> getEntries() {
        return $ENTRIES;
    }

    public static ApiLevel valueOf(String str) {
        return (ApiLevel) Enum.valueOf(ApiLevel.class, str);
    }

    public static ApiLevel[] values() {
        return (ApiLevel[]) $VALUES.clone();
    }

    public final int getVersionCode() {
        return this.versionCode;
    }
}
