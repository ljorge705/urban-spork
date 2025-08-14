package com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers;

import com.onfido.android.sdk.capture.OnfidoTheme;
import com.onfido.android.sdk.capture.analytics.OnfidoAnalyticsEventType;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.headturn.OnfidoAnimWebView;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.OnfidoAnalytics;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.AnalyticsEvent;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.AnalyticsPropertyKeys;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.Event;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.EventNames;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.EventType;
import com.onfido.javax.inject.Inject;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0017\u0018\u0000 \u001d2\u00020\u0001:\u0002\u001d\u001eB\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0017J\b\u0010\u0007\u001a\u00020\u0006H\u0017J\u0015\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nH\u0010¢\u0006\u0002\b\u000bJ\r\u0010\f\u001a\u00020\u0006H\u0010¢\u0006\u0002\b\rJ\u0010\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\nH\u0017J\u0010\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\nH\u0017J\u0010\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\nH\u0017J'\u0010\u0012\u001a\u00020\u00062\b\u0010\u0013\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\nH\u0010¢\u0006\u0002\b\u0016J\u001d\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0010¢\u0006\u0002\b\u001cR\u000e\u0010\u0002\u001a\u00020\u0003X\u0092\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/FlowTracker;", "", "onfidoAnalytics", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/OnfidoAnalytics;", "(Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/OnfidoAnalytics;)V", "trackFlowCancellation", "", "trackFlowCompletion", "trackFlowInterruptedWithError", "errorMessage", "", "trackFlowInterruptedWithError$onfido_capture_sdk_core_release", "trackFlowStart", "trackFlowStart$onfido_capture_sdk_core_release", "trackFlowUserExitButtonClicked", AnalyticsPropertyKeys.STEP, "trackFlowUserExitCanceled", "trackFlowUserExitConfirmed", "trackLanguageConfig", "initialLanguage", "displayLanguage", "devicesLanguages", "trackLanguageConfig$onfido_capture_sdk_core_release", "trackUiThemeConfig", "selectedSdkTheme", "Lcom/onfido/android/sdk/capture/OnfidoTheme;", "isSystemDarkModeEnabled", "", "trackUiThemeConfig$onfido_capture_sdk_core_release", "Companion", "UiTheme", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public class FlowTracker {
    public static final String STEP_DOCUMENT_CAPTURE = "document_capture";
    public static final String STEP_FACE_CAPTURE = "face_capture";
    public static final String STEP_VIDEO_CAPTURE = "video_capture";
    private final OnfidoAnalytics onfidoAnalytics;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/FlowTracker$UiTheme;", "", "trackingValue", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getTrackingValue", "()Ljava/lang/String;", "LIGHT", "DARK", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class UiTheme {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ UiTheme[] $VALUES;
        private final String trackingValue;
        public static final UiTheme LIGHT = new UiTheme("LIGHT", 0, OnfidoAnimWebView.THEME_LIGHT);
        public static final UiTheme DARK = new UiTheme("DARK", 1, OnfidoAnimWebView.THEME_DARK);

        private static final /* synthetic */ UiTheme[] $values() {
            return new UiTheme[]{LIGHT, DARK};
        }

        static {
            UiTheme[] uiThemeArr$values = $values();
            $VALUES = uiThemeArr$values;
            $ENTRIES = EnumEntriesKt.enumEntries(uiThemeArr$values);
        }

        private UiTheme(String str, int i, String str2) {
            this.trackingValue = str2;
        }

        public static EnumEntries<UiTheme> getEntries() {
            return $ENTRIES;
        }

        public static UiTheme valueOf(String str) {
            return (UiTheme) Enum.valueOf(UiTheme.class, str);
        }

        public static UiTheme[] values() {
            return (UiTheme[]) $VALUES.clone();
        }

        public final String getTrackingValue() {
            return this.trackingValue;
        }
    }

    @Inject
    public FlowTracker(OnfidoAnalytics onfidoAnalytics) {
        Intrinsics.checkNotNullParameter(onfidoAnalytics, "onfidoAnalytics");
        this.onfidoAnalytics = onfidoAnalytics;
    }

    public void trackFlowCancellation() {
        this.onfidoAnalytics.track(new AnalyticsEvent(new Event(EventNames.Flow.FLOW_EXITED, EventType.FLOW, null, null, 12, null), null, null, 6, null));
    }

    public void trackFlowCompletion() {
        this.onfidoAnalytics.track(new AnalyticsEvent(new Event(EventNames.Flow.FLOW_COMPLETED, EventType.FLOW, null, null, 12, null), null, null, 6, null));
    }

    public void trackFlowInterruptedWithError$onfido_capture_sdk_core_release(String errorMessage) {
        Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
        this.onfidoAnalytics.track(new AnalyticsEvent(new Event(EventNames.Flow.INTERRUPTED_FLOW_ERROR, EventType.FLOW, null, null, 12, null), MapsKt.mapOf(TuplesKt.to("error", errorMessage)), null, 4, null));
    }

    public void trackFlowStart$onfido_capture_sdk_core_release() {
        this.onfidoAnalytics.track(new AnalyticsEvent(new Event(EventNames.Flow.FLOW_STARTED, EventType.FLOW, null, null, 12, null), null, null, 6, null));
    }

    public void trackFlowUserExitButtonClicked(String step) {
        Intrinsics.checkNotNullParameter(step, "step");
        this.onfidoAnalytics.track(new AnalyticsEvent(new Event(EventNames.Flow.USER_EXIT_TAPPED_EXIT_BUTTON, EventType.ACTION, null, null, 12, null), MapsKt.mapOf(TuplesKt.to(AnalyticsPropertyKeys.STEP, step)), null, 4, null));
    }

    public void trackFlowUserExitCanceled(String step) {
        Intrinsics.checkNotNullParameter(step, "step");
        this.onfidoAnalytics.track(new AnalyticsEvent(new Event(EventNames.Flow.USER_EXIT_TAPPED_ALERT_CANCEL, EventType.ACTION, null, null, 12, null), MapsKt.mapOf(TuplesKt.to(AnalyticsPropertyKeys.STEP, step)), null, 4, null));
    }

    public void trackFlowUserExitConfirmed(String step) {
        Intrinsics.checkNotNullParameter(step, "step");
        this.onfidoAnalytics.track(new AnalyticsEvent(new Event(EventNames.Flow.USER_EXIT_TAPPED_ALERT_CONFIRM, EventType.ACTION, EventNames.PublicNames.Flow.USER_EXITED_FLOW, OnfidoAnalyticsEventType.ACTION), MapsKt.mapOf(TuplesKt.to(AnalyticsPropertyKeys.STEP, step)), null, 4, null));
    }

    public void trackLanguageConfig$onfido_capture_sdk_core_release(String initialLanguage, String displayLanguage, String devicesLanguages) {
        Intrinsics.checkNotNullParameter(displayLanguage, "displayLanguage");
        Intrinsics.checkNotNullParameter(devicesLanguages, "devicesLanguages");
        Event event = new Event(EventNames.Flow.LANGUAGE_DISPLAYED, EventType.FLOW, null, null, 12, null);
        Pair pair = TuplesKt.to(AnalyticsPropertyKeys.DEVICE_LANGUAGE, devicesLanguages);
        Pair pair2 = TuplesKt.to(AnalyticsPropertyKeys.DISPLAYED_LANGUAGE, displayLanguage);
        if (initialLanguage == null) {
            initialLanguage = "";
        }
        this.onfidoAnalytics.track(new AnalyticsEvent(event, MapsKt.mapOf(pair, pair2, TuplesKt.to(AnalyticsPropertyKeys.INIT_LANGUAGE, initialLanguage)), null, 4, null));
    }

    public void trackUiThemeConfig$onfido_capture_sdk_core_release(OnfidoTheme selectedSdkTheme, boolean isSystemDarkModeEnabled) {
        Intrinsics.checkNotNullParameter(selectedSdkTheme, "selectedSdkTheme");
        UiTheme uiTheme = isSystemDarkModeEnabled ? UiTheme.DARK : UiTheme.LIGHT;
        UiTheme uiTheme2 = (selectedSdkTheme == OnfidoTheme.DARK || (selectedSdkTheme == OnfidoTheme.AUTOMATIC && isSystemDarkModeEnabled)) ? UiTheme.DARK : UiTheme.LIGHT;
        Event event = new Event(EventNames.Flow.UI_THEME_DISPLAYED, EventType.FLOW, null, null, 12, null);
        String lowerCase = selectedSdkTheme.name().toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        this.onfidoAnalytics.track(new AnalyticsEvent(event, MapsKt.mapOf(TuplesKt.to(AnalyticsPropertyKeys.INIT_THEME, lowerCase), TuplesKt.to(AnalyticsPropertyKeys.DEVICE_THEME, uiTheme.getTrackingValue()), TuplesKt.to(AnalyticsPropertyKeys.DISPLAYED_THEME, uiTheme2.getTrackingValue())), null, 4, null));
    }
}
