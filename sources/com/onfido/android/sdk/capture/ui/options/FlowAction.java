package com.onfido.android.sdk.capture.ui.options;

import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.EventNames;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: FlowAction.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0011\b\u0086\u0081\u0002\u0018\u0000 \u00112\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0011B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010¨\u0006\u0012"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/options/FlowAction;", "", "(Ljava/lang/String;I)V", EventNames.PublicNames.Other.WELCOME, EventNames.PublicNames.Other.USER_CONSENT, "INTRO_FACE_CAPTURE", "INTRO_LIVENESS_CAPTURE", "CAPTURE_FACE", "CAPTURE_DOCUMENT", "CAPTURE_LIVENESS", "CAPTURE_LIVENESS_CONFIRMATION", "ACTIVE_VIDEO_CAPTURE", "NFC_HOST_FEATURE", "FINAL", "MESSAGE", "PROOF_OF_ADDRESS", "DYNAMIC_CONTENT", "Companion", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FlowAction {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ FlowAction[] $VALUES;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE;
    public static final FlowAction WELCOME = new FlowAction(EventNames.PublicNames.Other.WELCOME, 0);
    public static final FlowAction USER_CONSENT = new FlowAction(EventNames.PublicNames.Other.USER_CONSENT, 1);
    public static final FlowAction INTRO_FACE_CAPTURE = new FlowAction("INTRO_FACE_CAPTURE", 2);
    public static final FlowAction INTRO_LIVENESS_CAPTURE = new FlowAction("INTRO_LIVENESS_CAPTURE", 3);
    public static final FlowAction CAPTURE_FACE = new FlowAction("CAPTURE_FACE", 4);
    public static final FlowAction CAPTURE_DOCUMENT = new FlowAction("CAPTURE_DOCUMENT", 5);
    public static final FlowAction CAPTURE_LIVENESS = new FlowAction("CAPTURE_LIVENESS", 6);
    public static final FlowAction CAPTURE_LIVENESS_CONFIRMATION = new FlowAction("CAPTURE_LIVENESS_CONFIRMATION", 7);
    public static final FlowAction ACTIVE_VIDEO_CAPTURE = new FlowAction("ACTIVE_VIDEO_CAPTURE", 8);
    public static final FlowAction NFC_HOST_FEATURE = new FlowAction("NFC_HOST_FEATURE", 9);
    public static final FlowAction FINAL = new FlowAction("FINAL", 10);
    public static final FlowAction MESSAGE = new FlowAction("MESSAGE", 11);
    public static final FlowAction PROOF_OF_ADDRESS = new FlowAction("PROOF_OF_ADDRESS", 12);
    public static final FlowAction DYNAMIC_CONTENT = new FlowAction("DYNAMIC_CONTENT", 13);

    private static final /* synthetic */ FlowAction[] $values() {
        return new FlowAction[]{WELCOME, USER_CONSENT, INTRO_FACE_CAPTURE, INTRO_LIVENESS_CAPTURE, CAPTURE_FACE, CAPTURE_DOCUMENT, CAPTURE_LIVENESS, CAPTURE_LIVENESS_CONFIRMATION, ACTIVE_VIDEO_CAPTURE, NFC_HOST_FEATURE, FINAL, MESSAGE, PROOF_OF_ADDRESS, DYNAMIC_CONTENT};
    }

    public static final FlowAction[] getDefaultFlow() {
        return INSTANCE.getDefaultFlow();
    }

    public static EnumEntries<FlowAction> getEntries() {
        return $ENTRIES;
    }

    public static final FlowAction[] getNonDuplicable() {
        return INSTANCE.getNonDuplicable();
    }

    public static FlowAction valueOf(String str) {
        return (FlowAction) Enum.valueOf(FlowAction.class, str);
    }

    public static FlowAction[] values() {
        return (FlowAction[]) $VALUES.clone();
    }

    private FlowAction(String str, int i) {
    }

    static {
        FlowAction[] flowActionArr$values = $values();
        $VALUES = flowActionArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(flowActionArr$values);
        INSTANCE = new Companion(null);
    }

    /* compiled from: FlowAction.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R \u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0006\u0010\u0002\u001a\u0004\b\u0007\u0010\bR \u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048FX\u0087\u0004¢\u0006\f\u0012\u0004\b\n\u0010\u0002\u001a\u0004\b\u000b\u0010\b¨\u0006\f"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/options/FlowAction$Companion;", "", "()V", "defaultFlow", "", "Lcom/onfido/android/sdk/capture/ui/options/FlowAction;", "getDefaultFlow$annotations", "getDefaultFlow", "()[Lcom/onfido/android/sdk/capture/ui/options/FlowAction;", "nonDuplicable", "getNonDuplicable$annotations", "getNonDuplicable", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public static /* synthetic */ void getDefaultFlow$annotations() {
        }

        @JvmStatic
        public static /* synthetic */ void getNonDuplicable$annotations() {
        }

        private Companion() {
        }

        public final FlowAction[] getDefaultFlow() {
            return new FlowAction[]{FlowAction.WELCOME, FlowAction.CAPTURE_DOCUMENT, FlowAction.CAPTURE_FACE, FlowAction.PROOF_OF_ADDRESS, FlowAction.FINAL};
        }

        public final FlowAction[] getNonDuplicable() {
            return new FlowAction[]{FlowAction.CAPTURE_DOCUMENT, FlowAction.CAPTURE_FACE, FlowAction.INTRO_FACE_CAPTURE, FlowAction.CAPTURE_LIVENESS, FlowAction.ACTIVE_VIDEO_CAPTURE, FlowAction.FINAL};
        }
    }
}
