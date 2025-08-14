package com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers;

import com.onfido.android.sdk.capture.internal.analytics.inhouse.OnfidoAnalytics;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.AnalyticsEvent;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.AnalyticsPropertyKeys;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.Event;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.EventNames;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.EventType;
import com.onfido.android.sdk.capture.utils.TimeProvider;
import com.onfido.javax.inject.Inject;
import com.onfido.javax.inject.Singleton;
import java.util.List;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0004\b\u0017\u0018\u00002\u00020\u0001:\u0003\u0019\u001a\u001bB\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013H\u0016J\u0018\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013H\u0016J&\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\b0\u0018H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0092\u0004¢\u0006\u0002\n\u0000R$\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0090\u000e¢\u0006\u0016\n\u0002\u0010\u000f\u0012\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u000e\u0010\u0004\u001a\u00020\u0005X\u0092\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/WaitingScreenTracker;", "", "onfidoAnalytics", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/OnfidoAnalytics;", "timeProvider", "Lcom/onfido/android/sdk/capture/utils/TimeProvider;", "(Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/OnfidoAnalytics;Lcom/onfido/android/sdk/capture/utils/TimeProvider;)V", "startTime", "", "getStartTime$onfido_capture_sdk_core_release$annotations", "()V", "getStartTime$onfido_capture_sdk_core_release", "()Ljava/lang/Long;", "setStartTime$onfido_capture_sdk_core_release", "(Ljava/lang/Long;)V", "Ljava/lang/Long;", "trackWaitingScreenCompletion", "", "taskType", "", "reason", "trackWaitingScreenEnded", "trackWaitingScreenStart", "thresholds", "", "ClassicFlowWaitingReason", "StudioFlowWaitingReason", "WaitingTaskTypes", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Singleton
/* loaded from: classes2.dex */
public class WaitingScreenTracker {
    private final OnfidoAnalytics onfidoAnalytics;
    private Long startTime;
    private final TimeProvider timeProvider;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/WaitingScreenTracker$ClassicFlowWaitingReason;", "", "()V", "REASON_GET_USER_CONSENT", "", "REASON_GRANT_USER_CONSENT", "REASON_POA_DOCUMENT_UPLOAD", "REASON_REVOKE_USER_CONSENT", "REASON_UPLOADING_DOCUMENT", "REASON_UPLOADING_FACE", "REASON_UPLOADING_FACE_MOTION", "REASON_UPLOADING_VIDEO", "REASON_UPLOAD_NFC_DATA", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class ClassicFlowWaitingReason {
        public static final ClassicFlowWaitingReason INSTANCE = new ClassicFlowWaitingReason();
        public static final String REASON_GET_USER_CONSENT = "GET_USER_CONSENT";
        public static final String REASON_GRANT_USER_CONSENT = "GRANT_USER_CONSENT";
        public static final String REASON_POA_DOCUMENT_UPLOAD = "POA_DOCUMENT_UPLOAD";
        public static final String REASON_REVOKE_USER_CONSENT = "REVOKE_USER_CONSENT";
        public static final String REASON_UPLOADING_DOCUMENT = "UPLOADING_DOCUMENT";
        public static final String REASON_UPLOADING_FACE = "UPLOADING_FACE";
        public static final String REASON_UPLOADING_FACE_MOTION = "UPLOADING_FACE_MOTION";
        public static final String REASON_UPLOADING_VIDEO = "UPLOADING_VIDEO";
        public static final String REASON_UPLOAD_NFC_DATA = "UPLOAD_NFC_DATA";

        private ClassicFlowWaitingReason() {
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/WaitingScreenTracker$StudioFlowWaitingReason;", "", "()V", StudioFlowWaitingReason.NON_INTERACTIVE, "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class StudioFlowWaitingReason {
        public static final StudioFlowWaitingReason INSTANCE = new StudioFlowWaitingReason();
        public static final String NON_INTERACTIVE = "NON_INTERACTIVE";

        private StudioFlowWaitingReason() {
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/WaitingScreenTracker$WaitingTaskTypes;", "", "()V", WaitingTaskTypes.IN_BETWEEN_STUDIO_TASKS, "", "TASK_TYPE_CHECKING_IMAGE_QUALITY", "TASK_TYPE_LOADING", "TASK_TYPE_UPLOADING", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class WaitingTaskTypes {
        public static final WaitingTaskTypes INSTANCE = new WaitingTaskTypes();
        public static final String IN_BETWEEN_STUDIO_TASKS = "IN_BETWEEN_STUDIO_TASKS";
        public static final String TASK_TYPE_CHECKING_IMAGE_QUALITY = "CHECKING_IMAGE_QUALITY";
        public static final String TASK_TYPE_LOADING = "LOADING";
        public static final String TASK_TYPE_UPLOADING = "UPLOADING";

        private WaitingTaskTypes() {
        }
    }

    @Inject
    public WaitingScreenTracker(OnfidoAnalytics onfidoAnalytics, TimeProvider timeProvider) {
        Intrinsics.checkNotNullParameter(onfidoAnalytics, "onfidoAnalytics");
        Intrinsics.checkNotNullParameter(timeProvider, "timeProvider");
        this.onfidoAnalytics = onfidoAnalytics;
        this.timeProvider = timeProvider;
    }

    public static /* synthetic */ void getStartTime$onfido_capture_sdk_core_release$annotations() {
    }

    /* renamed from: getStartTime$onfido_capture_sdk_core_release, reason: from getter */
    public Long getStartTime() {
        return this.startTime;
    }

    public void setStartTime$onfido_capture_sdk_core_release(Long l) {
        this.startTime = l;
    }

    public void trackWaitingScreenCompletion(String taskType, String reason) {
        Intrinsics.checkNotNullParameter(taskType, "taskType");
        Intrinsics.checkNotNullParameter(reason, "reason");
        Long startTime = getStartTime();
        if (startTime != null) {
            this.onfidoAnalytics.track(new AnalyticsEvent(new Event(EventNames.WaitingScreen.WAITING_SCREEN_COMPLETED, EventType.SCREEN, null, null, 12, null), MapsKt.mapOf(TuplesKt.to(AnalyticsPropertyKeys.WAITING_SCREEN_TASK_TYPE, taskType), TuplesKt.to("reason", reason), TuplesKt.to("duration", Long.valueOf(this.timeProvider.getCurrentTimestamp() - startTime.longValue()))), null, 4, null));
        }
    }

    public void trackWaitingScreenEnded(String taskType, String reason) {
        Intrinsics.checkNotNullParameter(taskType, "taskType");
        Intrinsics.checkNotNullParameter(reason, "reason");
        Long startTime = getStartTime();
        if (startTime != null) {
            this.onfidoAnalytics.track(new AnalyticsEvent(new Event(EventNames.WaitingScreen.WAITING_SCREEN_ENDED, EventType.SCREEN, null, null, 12, null), MapsKt.mapOf(TuplesKt.to(AnalyticsPropertyKeys.WAITING_SCREEN_TASK_TYPE, taskType), TuplesKt.to("reason", reason), TuplesKt.to("duration", Long.valueOf(this.timeProvider.getCurrentTimestamp() - startTime.longValue()))), null, 4, null));
            setStartTime$onfido_capture_sdk_core_release(null);
        }
    }

    public void trackWaitingScreenStart(String taskType, String reason, List<Long> thresholds) {
        Intrinsics.checkNotNullParameter(taskType, "taskType");
        Intrinsics.checkNotNullParameter(reason, "reason");
        Intrinsics.checkNotNullParameter(thresholds, "thresholds");
        setStartTime$onfido_capture_sdk_core_release(Long.valueOf(this.timeProvider.getCurrentTimestamp()));
        this.onfidoAnalytics.track(new AnalyticsEvent(new Event(EventNames.WaitingScreen.WAITING_SCREEN_STARTED, EventType.SCREEN, null, null, 12, null), MapsKt.mapOf(TuplesKt.to(AnalyticsPropertyKeys.WAITING_SCREEN_TASK_TYPE, taskType), TuplesKt.to("reason", reason), TuplesKt.to(AnalyticsPropertyKeys.WAITING_SCREEN_THRESHOLDS_LIST, thresholds)), null, 4, null));
    }
}
