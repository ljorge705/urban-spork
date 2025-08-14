package com.onfido.workflow.internal.ui.processor.biometric.token;

import com.facebook.hermes.intl.Constants;
import io.reactivex.rxjava3.annotations.SchedulerSupport;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: BiometricTokenRepositoryType.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0080\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lcom/onfido/workflow/internal/ui/processor/biometric/token/BiometricTokenRepositoryType;", "", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "INTERNAL", "EXTERNAL", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class BiometricTokenRepositoryType {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ BiometricTokenRepositoryType[] $VALUES;
    private final String value;
    public static final BiometricTokenRepositoryType INTERNAL = new BiometricTokenRepositoryType("INTERNAL", 0, Constants.COLLATION_DEFAULT);
    public static final BiometricTokenRepositoryType EXTERNAL = new BiometricTokenRepositoryType("EXTERNAL", 1, SchedulerSupport.CUSTOM);

    private static final /* synthetic */ BiometricTokenRepositoryType[] $values() {
        return new BiometricTokenRepositoryType[]{INTERNAL, EXTERNAL};
    }

    public static EnumEntries<BiometricTokenRepositoryType> getEntries() {
        return $ENTRIES;
    }

    public static BiometricTokenRepositoryType valueOf(String str) {
        return (BiometricTokenRepositoryType) Enum.valueOf(BiometricTokenRepositoryType.class, str);
    }

    public static BiometricTokenRepositoryType[] values() {
        return (BiometricTokenRepositoryType[]) $VALUES.clone();
    }

    public final String getValue() {
        return this.value;
    }

    private BiometricTokenRepositoryType(String str, int i, String str2) {
        this.value = str2;
    }

    static {
        BiometricTokenRepositoryType[] biometricTokenRepositoryTypeArr$values = $values();
        $VALUES = biometricTokenRepositoryTypeArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(biometricTokenRepositoryTypeArr$values);
    }
}
