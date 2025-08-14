package com.onfido.hosted.web.module.model;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\b\u0080\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000e¨\u0006\u000f"}, d2 = {"Lcom/onfido/hosted/web/module/model/HostedWebModuleCallbacks;", "", "id", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getId$onfido_capture_sdk_core_release", "()Ljava/lang/String;", "CAPTURE_MODULE_FINISH", "CAPTURE_MODULE_ERROR", "CAPTURE_MODULE_UNSUPPORTED", "ANALYTICS_SEND", "BOOTSTRAP_ERROR", "NAVIGATION_BACK", "NAVIGATION_EXTERNAL_LINK", "NAVIGATION_EXIT", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class HostedWebModuleCallbacks {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ HostedWebModuleCallbacks[] $VALUES;
    private final String id;
    public static final HostedWebModuleCallbacks CAPTURE_MODULE_FINISH = new HostedWebModuleCallbacks("CAPTURE_MODULE_FINISH", 0, "captureModuleFinish");
    public static final HostedWebModuleCallbacks CAPTURE_MODULE_ERROR = new HostedWebModuleCallbacks("CAPTURE_MODULE_ERROR", 1, "captureModuleError");
    public static final HostedWebModuleCallbacks CAPTURE_MODULE_UNSUPPORTED = new HostedWebModuleCallbacks("CAPTURE_MODULE_UNSUPPORTED", 2, "captureModuleUnsupported");
    public static final HostedWebModuleCallbacks ANALYTICS_SEND = new HostedWebModuleCallbacks("ANALYTICS_SEND", 3, "analyticsSend");
    public static final HostedWebModuleCallbacks BOOTSTRAP_ERROR = new HostedWebModuleCallbacks("BOOTSTRAP_ERROR", 4, "bootstrapError");
    public static final HostedWebModuleCallbacks NAVIGATION_BACK = new HostedWebModuleCallbacks("NAVIGATION_BACK", 5, "navigationBack");
    public static final HostedWebModuleCallbacks NAVIGATION_EXTERNAL_LINK = new HostedWebModuleCallbacks("NAVIGATION_EXTERNAL_LINK", 6, "navigationExternalLink");
    public static final HostedWebModuleCallbacks NAVIGATION_EXIT = new HostedWebModuleCallbacks("NAVIGATION_EXIT", 7, "navigationExit");

    private static final /* synthetic */ HostedWebModuleCallbacks[] $values() {
        return new HostedWebModuleCallbacks[]{CAPTURE_MODULE_FINISH, CAPTURE_MODULE_ERROR, CAPTURE_MODULE_UNSUPPORTED, ANALYTICS_SEND, BOOTSTRAP_ERROR, NAVIGATION_BACK, NAVIGATION_EXTERNAL_LINK, NAVIGATION_EXIT};
    }

    static {
        HostedWebModuleCallbacks[] hostedWebModuleCallbacksArr$values = $values();
        $VALUES = hostedWebModuleCallbacksArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(hostedWebModuleCallbacksArr$values);
    }

    private HostedWebModuleCallbacks(String str, int i, String str2) {
        this.id = str2;
    }

    public static EnumEntries<HostedWebModuleCallbacks> getEntries() {
        return $ENTRIES;
    }

    public static HostedWebModuleCallbacks valueOf(String str) {
        return (HostedWebModuleCallbacks) Enum.valueOf(HostedWebModuleCallbacks.class, str);
    }

    public static HostedWebModuleCallbacks[] values() {
        return (HostedWebModuleCallbacks[]) $VALUES.clone();
    }

    /* renamed from: getId$onfido_capture_sdk_core_release, reason: from getter */
    public final String getId() {
        return this.id;
    }
}
