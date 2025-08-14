package com.onfido.hosted.web.module.model;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0080\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/onfido/hosted/web/module/model/HostedWebModuleScriptState;", "", "(Ljava/lang/String;I)V", "NOT_EVALUATED", "SHOULD_EVALUATE", "EVALUATED", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class HostedWebModuleScriptState {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ HostedWebModuleScriptState[] $VALUES;
    public static final HostedWebModuleScriptState NOT_EVALUATED = new HostedWebModuleScriptState("NOT_EVALUATED", 0);
    public static final HostedWebModuleScriptState SHOULD_EVALUATE = new HostedWebModuleScriptState("SHOULD_EVALUATE", 1);
    public static final HostedWebModuleScriptState EVALUATED = new HostedWebModuleScriptState("EVALUATED", 2);

    private static final /* synthetic */ HostedWebModuleScriptState[] $values() {
        return new HostedWebModuleScriptState[]{NOT_EVALUATED, SHOULD_EVALUATE, EVALUATED};
    }

    static {
        HostedWebModuleScriptState[] hostedWebModuleScriptStateArr$values = $values();
        $VALUES = hostedWebModuleScriptStateArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(hostedWebModuleScriptStateArr$values);
    }

    private HostedWebModuleScriptState(String str, int i) {
    }

    public static EnumEntries<HostedWebModuleScriptState> getEntries() {
        return $ENTRIES;
    }

    public static HostedWebModuleScriptState valueOf(String str) {
        return (HostedWebModuleScriptState) Enum.valueOf(HostedWebModuleScriptState.class, str);
    }

    public static HostedWebModuleScriptState[] values() {
        return (HostedWebModuleScriptState[]) $VALUES.clone();
    }
}
