package com.onfido.android.sdk.capture.common.permissions;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0087\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lcom/onfido/android/sdk/capture/common/permissions/PermissionRequestStatus;", "", "id", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getId$onfido_capture_sdk_core_release", "()Ljava/lang/String;", "FIRST_REQUEST", "TEMPORARILY_DENIED", "PERMANENTLY_DENIED", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class PermissionRequestStatus {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ PermissionRequestStatus[] $VALUES;
    private final String id;
    public static final PermissionRequestStatus FIRST_REQUEST = new PermissionRequestStatus("FIRST_REQUEST", 0, "first_request");
    public static final PermissionRequestStatus TEMPORARILY_DENIED = new PermissionRequestStatus("TEMPORARILY_DENIED", 1, "temporarily_denied");
    public static final PermissionRequestStatus PERMANENTLY_DENIED = new PermissionRequestStatus("PERMANENTLY_DENIED", 2, "permanently_denied");

    private static final /* synthetic */ PermissionRequestStatus[] $values() {
        return new PermissionRequestStatus[]{FIRST_REQUEST, TEMPORARILY_DENIED, PERMANENTLY_DENIED};
    }

    static {
        PermissionRequestStatus[] permissionRequestStatusArr$values = $values();
        $VALUES = permissionRequestStatusArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(permissionRequestStatusArr$values);
    }

    private PermissionRequestStatus(String str, int i, String str2) {
        this.id = str2;
    }

    public static EnumEntries<PermissionRequestStatus> getEntries() {
        return $ENTRIES;
    }

    public static PermissionRequestStatus valueOf(String str) {
        return (PermissionRequestStatus) Enum.valueOf(PermissionRequestStatus.class, str);
    }

    public static PermissionRequestStatus[] values() {
        return (PermissionRequestStatus[]) $VALUES.clone();
    }

    /* renamed from: getId$onfido_capture_sdk_core_release, reason: from getter */
    public final String getId() {
        return this.id;
    }
}
