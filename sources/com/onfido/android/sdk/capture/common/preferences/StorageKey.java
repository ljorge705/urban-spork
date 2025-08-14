package com.onfido.android.sdk.capture.common.preferences;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0080\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lcom/onfido/android/sdk/capture/common/preferences/StorageKey;", "", "(Ljava/lang/String;I)V", "SESSION_ID", "SESSION_ID_CREATED_AT", "PERSISTED_UUID", "SELECTED_LOCALE", "IS_IN_DARK_MODE", "SINGLE_RUN_SESSION_ID", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class StorageKey {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ StorageKey[] $VALUES;
    public static final StorageKey SESSION_ID = new StorageKey("SESSION_ID", 0);
    public static final StorageKey SESSION_ID_CREATED_AT = new StorageKey("SESSION_ID_CREATED_AT", 1);
    public static final StorageKey PERSISTED_UUID = new StorageKey("PERSISTED_UUID", 2);
    public static final StorageKey SELECTED_LOCALE = new StorageKey("SELECTED_LOCALE", 3);
    public static final StorageKey IS_IN_DARK_MODE = new StorageKey("IS_IN_DARK_MODE", 4);
    public static final StorageKey SINGLE_RUN_SESSION_ID = new StorageKey("SINGLE_RUN_SESSION_ID", 5);

    private static final /* synthetic */ StorageKey[] $values() {
        return new StorageKey[]{SESSION_ID, SESSION_ID_CREATED_AT, PERSISTED_UUID, SELECTED_LOCALE, IS_IN_DARK_MODE, SINGLE_RUN_SESSION_ID};
    }

    static {
        StorageKey[] storageKeyArr$values = $values();
        $VALUES = storageKeyArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(storageKeyArr$values);
    }

    private StorageKey(String str, int i) {
    }

    public static EnumEntries<StorageKey> getEntries() {
        return $ENTRIES;
    }

    public static StorageKey valueOf(String str) {
        return (StorageKey) Enum.valueOf(StorageKey.class, str);
    }

    public static StorageKey[] values() {
        return (StorageKey[]) $VALUES.clone();
    }
}
