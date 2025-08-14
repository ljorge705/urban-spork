package com.onfido.android.sdk.capture.internal.performance.domain;

import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.EventNames;
import com.onfido.android.sdk.capture.ui.OnfidoActivity;
import com.onfido.android.sdk.capture.ui.options.FlowAction;
import com.onfido.api.client.data.SdkConfiguration;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b!\b\u0087\u0081\u0002\u0018\u0000 #2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001#B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b j\u0002\b!j\u0002\b\"¨\u0006$"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/performance/domain/PerformanceAnalyticsScreen;", "", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "SPLASH", EventNames.PublicNames.Other.WELCOME, EventNames.PublicNames.Other.USER_CONSENT, "DOCUMENT_TYPE_SELECTION", "COUNTRY_SELECTION", "PERMISSION_MANAGEMENT", "FACE_SELFIE_INTRO", EventNames.Face.FACE_VIDEO_INTRO, EventNames.Motion.FACE_LIVENESS_INTRO, "DOCUMENT_CAPTURE", EventNames.Face.FACE_SELFIE_CAPTURE, EventNames.Face.FACE_VIDEO_CAPTURE, "MOTION_CAPTURE", EventNames.Face.FACE_VIDEO_CONFIRMATION, "MOTION_NO_FACE_DETECTED", "MOTION_CONNECTION_ERROR", "MOTION_UPLOAD", "MOTION_OUTRO", "NFC_INTRO", "POA_VERIFY_ADDRESS", "POA_COUNTRY_SELECTION", "POA_DOCUMENT_SUBMISSION", "POA_DOCUMENT_DETAILS", "POA_DOCUMENT_TYPE_SELECTION", "FINAL", "UNKNOWN", "DYNAMIC_CONTENT", "MESSAGE", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class PerformanceAnalyticsScreen {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ PerformanceAnalyticsScreen[] $VALUES;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE;
    private final String value;
    public static final PerformanceAnalyticsScreen SPLASH = new PerformanceAnalyticsScreen("SPLASH", 0, "splash");
    public static final PerformanceAnalyticsScreen WELCOME = new PerformanceAnalyticsScreen(EventNames.PublicNames.Other.WELCOME, 1, "welcome");
    public static final PerformanceAnalyticsScreen USER_CONSENT = new PerformanceAnalyticsScreen(EventNames.PublicNames.Other.USER_CONSENT, 2, "user_consent");
    public static final PerformanceAnalyticsScreen DOCUMENT_TYPE_SELECTION = new PerformanceAnalyticsScreen("DOCUMENT_TYPE_SELECTION", 3, "document_type_selection");
    public static final PerformanceAnalyticsScreen COUNTRY_SELECTION = new PerformanceAnalyticsScreen("COUNTRY_SELECTION", 4, "country_selection");
    public static final PerformanceAnalyticsScreen PERMISSION_MANAGEMENT = new PerformanceAnalyticsScreen("PERMISSION_MANAGEMENT", 5, OnfidoActivity.Companion.RequestKey.KEY_REQUEST_PERMISSIONS_MANAGEMENT);
    public static final PerformanceAnalyticsScreen FACE_SELFIE_INTRO = new PerformanceAnalyticsScreen("FACE_SELFIE_INTRO", 6, OnfidoActivity.Companion.RequestKey.KEY_REQUEST_FACE_SELFIE_INTRO);
    public static final PerformanceAnalyticsScreen FACE_VIDEO_INTRO = new PerformanceAnalyticsScreen(EventNames.Face.FACE_VIDEO_INTRO, 7, "face_video_intro");
    public static final PerformanceAnalyticsScreen FACE_LIVENESS_INTRO = new PerformanceAnalyticsScreen(EventNames.Motion.FACE_LIVENESS_INTRO, 8, OnfidoActivity.Companion.RequestKey.KEY_REQUEST_FACE_LIVENESS_INTRO);
    public static final PerformanceAnalyticsScreen DOCUMENT_CAPTURE = new PerformanceAnalyticsScreen("DOCUMENT_CAPTURE", 9, "document_capture");
    public static final PerformanceAnalyticsScreen FACE_SELFIE_CAPTURE = new PerformanceAnalyticsScreen(EventNames.Face.FACE_SELFIE_CAPTURE, 10, SdkConfiguration.FIELD_SELFIE_CAPTURE);
    public static final PerformanceAnalyticsScreen FACE_VIDEO_CAPTURE = new PerformanceAnalyticsScreen(EventNames.Face.FACE_VIDEO_CAPTURE, 11, SdkConfiguration.FIELD_LIVENESS_CAPTURE);
    public static final PerformanceAnalyticsScreen MOTION_CAPTURE = new PerformanceAnalyticsScreen("MOTION_CAPTURE", 12, "face_liveness_capture");
    public static final PerformanceAnalyticsScreen FACE_VIDEO_CONFIRMATION = new PerformanceAnalyticsScreen(EventNames.Face.FACE_VIDEO_CONFIRMATION, 13, "face_video_confirmation");
    public static final PerformanceAnalyticsScreen MOTION_NO_FACE_DETECTED = new PerformanceAnalyticsScreen("MOTION_NO_FACE_DETECTED", 14, "face_liveness_no_face_detected");
    public static final PerformanceAnalyticsScreen MOTION_CONNECTION_ERROR = new PerformanceAnalyticsScreen("MOTION_CONNECTION_ERROR", 15, "face_liveness_connection_error");
    public static final PerformanceAnalyticsScreen MOTION_UPLOAD = new PerformanceAnalyticsScreen("MOTION_UPLOAD", 16, "face_liveness_upload");
    public static final PerformanceAnalyticsScreen MOTION_OUTRO = new PerformanceAnalyticsScreen("MOTION_OUTRO", 17, "face_liveness_outro");
    public static final PerformanceAnalyticsScreen NFC_INTRO = new PerformanceAnalyticsScreen("NFC_INTRO", 18, "nfc_intro");
    public static final PerformanceAnalyticsScreen POA_VERIFY_ADDRESS = new PerformanceAnalyticsScreen("POA_VERIFY_ADDRESS", 19, "poa_verify_address");
    public static final PerformanceAnalyticsScreen POA_COUNTRY_SELECTION = new PerformanceAnalyticsScreen("POA_COUNTRY_SELECTION", 20, "poa_country_selection");
    public static final PerformanceAnalyticsScreen POA_DOCUMENT_SUBMISSION = new PerformanceAnalyticsScreen("POA_DOCUMENT_SUBMISSION", 21, "poa_document_submission");
    public static final PerformanceAnalyticsScreen POA_DOCUMENT_DETAILS = new PerformanceAnalyticsScreen("POA_DOCUMENT_DETAILS", 22, "poa_document_details");
    public static final PerformanceAnalyticsScreen POA_DOCUMENT_TYPE_SELECTION = new PerformanceAnalyticsScreen("POA_DOCUMENT_TYPE_SELECTION", 23, "poa_document_type_selection");
    public static final PerformanceAnalyticsScreen FINAL = new PerformanceAnalyticsScreen("FINAL", 24, "complete");
    public static final PerformanceAnalyticsScreen UNKNOWN = new PerformanceAnalyticsScreen("UNKNOWN", 25, "unknown");
    public static final PerformanceAnalyticsScreen DYNAMIC_CONTENT = new PerformanceAnalyticsScreen("DYNAMIC_CONTENT", 26, "dynamic_content");
    public static final PerformanceAnalyticsScreen MESSAGE = new PerformanceAnalyticsScreen("MESSAGE", 27, "message");

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/performance/domain/PerformanceAnalyticsScreen$Companion;", "", "()V", "fromFlowAction", "Lcom/onfido/android/sdk/capture/internal/performance/domain/PerformanceAnalyticsScreen;", "flowAction", "Lcom/onfido/android/sdk/capture/ui/options/FlowAction;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {

        @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[FlowAction.values().length];
                try {
                    iArr[FlowAction.WELCOME.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[FlowAction.USER_CONSENT.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[FlowAction.INTRO_FACE_CAPTURE.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                try {
                    iArr[FlowAction.INTRO_LIVENESS_CAPTURE.ordinal()] = 4;
                } catch (NoSuchFieldError unused4) {
                }
                try {
                    iArr[FlowAction.CAPTURE_FACE.ordinal()] = 5;
                } catch (NoSuchFieldError unused5) {
                }
                try {
                    iArr[FlowAction.CAPTURE_DOCUMENT.ordinal()] = 6;
                } catch (NoSuchFieldError unused6) {
                }
                try {
                    iArr[FlowAction.CAPTURE_LIVENESS.ordinal()] = 7;
                } catch (NoSuchFieldError unused7) {
                }
                try {
                    iArr[FlowAction.CAPTURE_LIVENESS_CONFIRMATION.ordinal()] = 8;
                } catch (NoSuchFieldError unused8) {
                }
                try {
                    iArr[FlowAction.ACTIVE_VIDEO_CAPTURE.ordinal()] = 9;
                } catch (NoSuchFieldError unused9) {
                }
                try {
                    iArr[FlowAction.NFC_HOST_FEATURE.ordinal()] = 10;
                } catch (NoSuchFieldError unused10) {
                }
                try {
                    iArr[FlowAction.PROOF_OF_ADDRESS.ordinal()] = 11;
                } catch (NoSuchFieldError unused11) {
                }
                try {
                    iArr[FlowAction.FINAL.ordinal()] = 12;
                } catch (NoSuchFieldError unused12) {
                }
                try {
                    iArr[FlowAction.MESSAGE.ordinal()] = 13;
                } catch (NoSuchFieldError unused13) {
                }
                try {
                    iArr[FlowAction.DYNAMIC_CONTENT.ordinal()] = 14;
                } catch (NoSuchFieldError unused14) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        private Companion() {
        }

        public final PerformanceAnalyticsScreen fromFlowAction(FlowAction flowAction) {
            Intrinsics.checkNotNullParameter(flowAction, "flowAction");
            switch (WhenMappings.$EnumSwitchMapping$0[flowAction.ordinal()]) {
                case 1:
                    return PerformanceAnalyticsScreen.WELCOME;
                case 2:
                    return PerformanceAnalyticsScreen.USER_CONSENT;
                case 3:
                    return PerformanceAnalyticsScreen.FACE_SELFIE_INTRO;
                case 4:
                    return PerformanceAnalyticsScreen.FACE_VIDEO_INTRO;
                case 5:
                    return PerformanceAnalyticsScreen.FACE_SELFIE_CAPTURE;
                case 6:
                    return PerformanceAnalyticsScreen.DOCUMENT_CAPTURE;
                case 7:
                    return PerformanceAnalyticsScreen.FACE_VIDEO_CAPTURE;
                case 8:
                    return PerformanceAnalyticsScreen.FACE_VIDEO_CONFIRMATION;
                case 9:
                    return PerformanceAnalyticsScreen.MOTION_CAPTURE;
                case 10:
                    return PerformanceAnalyticsScreen.NFC_INTRO;
                case 11:
                    return PerformanceAnalyticsScreen.POA_VERIFY_ADDRESS;
                case 12:
                    return PerformanceAnalyticsScreen.FINAL;
                case 13:
                    return PerformanceAnalyticsScreen.MESSAGE;
                case 14:
                    return PerformanceAnalyticsScreen.DYNAMIC_CONTENT;
                default:
                    throw new NoWhenBranchMatchedException();
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private static final /* synthetic */ PerformanceAnalyticsScreen[] $values() {
        return new PerformanceAnalyticsScreen[]{SPLASH, WELCOME, USER_CONSENT, DOCUMENT_TYPE_SELECTION, COUNTRY_SELECTION, PERMISSION_MANAGEMENT, FACE_SELFIE_INTRO, FACE_VIDEO_INTRO, FACE_LIVENESS_INTRO, DOCUMENT_CAPTURE, FACE_SELFIE_CAPTURE, FACE_VIDEO_CAPTURE, MOTION_CAPTURE, FACE_VIDEO_CONFIRMATION, MOTION_NO_FACE_DETECTED, MOTION_CONNECTION_ERROR, MOTION_UPLOAD, MOTION_OUTRO, NFC_INTRO, POA_VERIFY_ADDRESS, POA_COUNTRY_SELECTION, POA_DOCUMENT_SUBMISSION, POA_DOCUMENT_DETAILS, POA_DOCUMENT_TYPE_SELECTION, FINAL, UNKNOWN, DYNAMIC_CONTENT, MESSAGE};
    }

    static {
        PerformanceAnalyticsScreen[] performanceAnalyticsScreenArr$values = $values();
        $VALUES = performanceAnalyticsScreenArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(performanceAnalyticsScreenArr$values);
        INSTANCE = new Companion(null);
    }

    private PerformanceAnalyticsScreen(String str, int i, String str2) {
        this.value = str2;
    }

    public static EnumEntries<PerformanceAnalyticsScreen> getEntries() {
        return $ENTRIES;
    }

    public static PerformanceAnalyticsScreen valueOf(String str) {
        return (PerformanceAnalyticsScreen) Enum.valueOf(PerformanceAnalyticsScreen.class, str);
    }

    public static PerformanceAnalyticsScreen[] values() {
        return (PerformanceAnalyticsScreen[]) $VALUES.clone();
    }

    public final String getValue() {
        return this.value;
    }
}
