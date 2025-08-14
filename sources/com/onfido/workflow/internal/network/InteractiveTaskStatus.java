package com.onfido.workflow.internal.network;

import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.debug.internal.DebugCoroutineInfoImplKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: WorkflowResponse.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\b\u0081\u0081\u0002\u0018\u0000 \f2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0002\u000b\fB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\r"}, d2 = {"Lcom/onfido/workflow/internal/network/InteractiveTaskStatus;", "", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "ABANDONED", "ERROR", "FINISHED", DebugCoroutineInfoImplKt.RUNNING, "$serializer", "Companion", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Serializable
/* loaded from: classes6.dex */
public final class InteractiveTaskStatus {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ InteractiveTaskStatus[] $VALUES;
    private static final Lazy<KSerializer<Object>> $cachedSerializer$delegate;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE;
    private final String value;

    @SerialName("abandoned")
    public static final InteractiveTaskStatus ABANDONED = new InteractiveTaskStatus("ABANDONED", 0, "abandoned");

    @SerialName("error")
    public static final InteractiveTaskStatus ERROR = new InteractiveTaskStatus("ERROR", 1, "error");

    @SerialName("finished")
    public static final InteractiveTaskStatus FINISHED = new InteractiveTaskStatus("FINISHED", 2, "finished");

    @SerialName("running")
    public static final InteractiveTaskStatus RUNNING = new InteractiveTaskStatus(DebugCoroutineInfoImplKt.RUNNING, 3, "running");

    private static final /* synthetic */ InteractiveTaskStatus[] $values() {
        return new InteractiveTaskStatus[]{ABANDONED, ERROR, FINISHED, RUNNING};
    }

    public static EnumEntries<InteractiveTaskStatus> getEntries() {
        return $ENTRIES;
    }

    public static InteractiveTaskStatus valueOf(String str) {
        return (InteractiveTaskStatus) Enum.valueOf(InteractiveTaskStatus.class, str);
    }

    public static InteractiveTaskStatus[] values() {
        return (InteractiveTaskStatus[]) $VALUES.clone();
    }

    public final String getValue() {
        return this.value;
    }

    /* compiled from: WorkflowResponse.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/onfido/workflow/internal/network/InteractiveTaskStatus$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/workflow/internal/network/InteractiveTaskStatus;", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        private final /* synthetic */ KSerializer get$cachedSerializer() {
            return (KSerializer) InteractiveTaskStatus.$cachedSerializer$delegate.getValue();
        }

        public final KSerializer<InteractiveTaskStatus> serializer() {
            return get$cachedSerializer();
        }
    }

    private InteractiveTaskStatus(String str, int i, String str2) {
        this.value = str2;
    }

    static {
        InteractiveTaskStatus[] interactiveTaskStatusArr$values = $values();
        $VALUES = interactiveTaskStatusArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(interactiveTaskStatusArr$values);
        INSTANCE = new Companion(null);
        $cachedSerializer$delegate = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0) new Function0<KSerializer<Object>>() { // from class: com.onfido.workflow.internal.network.InteractiveTaskStatus.Companion.1
            @Override // kotlin.jvm.functions.Function0
            public final KSerializer<Object> invoke() {
                return InteractiveTaskStatus$$serializer.INSTANCE;
            }
        });
    }
}
