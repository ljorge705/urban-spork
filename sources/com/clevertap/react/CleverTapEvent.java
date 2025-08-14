package com.clevertap.react;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: CleverTapEvent.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b!\b\u0086\u0081\u0002\u0018\u0000 %2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001%B\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u000b\u001a\u00020\u0003H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b j\u0002\b!j\u0002\b\"j\u0002\b#j\u0002\b$¨\u0006&"}, d2 = {"Lcom/clevertap/react/CleverTapEvent;", "", "eventName", "", "bufferable", "", "(Ljava/lang/String;ILjava/lang/String;Z)V", "getBufferable", "()Z", "getEventName", "()Ljava/lang/String;", "toString", "CLEVERTAP_PROFILE_DID_INITIALIZE", "CLEVERTAP_PROFILE_SYNC", "CLEVERTAP_IN_APP_NOTIFICATION_DISMISSED", "CLEVERTAP_IN_APP_NOTIFICATION_SHOWED", "CLEVERTAP_INBOX_DID_INITIALIZE", "CLEVERTAP_INBOX_MESSAGES_DID_UPDATE", "CLEVERTAP_ON_INBOX_BUTTON_CLICK", "CLEVERTAP_ON_INBOX_MESSAGE_CLICK", "CLEVERTAP_ON_INAPP_BUTTON_CLICK", "CLEVERTAP_ON_DISPLAY_UNITS_LOADED", "CLEVERTAP_FEATURE_FLAGS_DID_UPDATE", "CLEVERTAP_PRODUCT_CONFIG_DID_INITIALIZE", "CLEVERTAP_PRODUCT_CONFIG_DID_FETCH", "CLEVERTAP_PRODUCT_CONFIG_DID_ACTIVATE", "CLEVERTAP_PUSH_NOTIFICATION_CLICKED", "CLEVERTAP_ON_PUSH_PERMISSION_RESPONSE", "CLEVERTAP_ON_VARIABLES_CHANGED", "CLEVERTAP_ON_ONE_TIME_VARIABLES_CHANGED", "CLEVERTAP_ON_VALUE_CHANGED", "CLEVERTAP_CUSTOM_TEMPLATE_PRESENT", "CLEVERTAP_CUSTOM_FUNCTION_PRESENT", "CLEVERTAP_CUSTOM_TEMPLATE_CLOSE", "CLEVERTAP_ON_FILE_VALUE_CHANGED", "CLEVERTAP_ON_VARIABLES_CHANGED_AND_NO_DOWNLOADS_PENDING", "CLEVERTAP_ONCE_VARIABLES_CHANGED_AND_NO_DOWNLOADS_PENDING", "Companion", "clevertap-react-native_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class CleverTapEvent {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ CleverTapEvent[] $VALUES;
    public static final CleverTapEvent CLEVERTAP_CUSTOM_TEMPLATE_CLOSE;
    public static final CleverTapEvent CLEVERTAP_INBOX_MESSAGES_DID_UPDATE;
    public static final CleverTapEvent CLEVERTAP_ON_FILE_VALUE_CHANGED;
    public static final CleverTapEvent CLEVERTAP_ON_INBOX_BUTTON_CLICK;
    public static final CleverTapEvent CLEVERTAP_ON_INBOX_MESSAGE_CLICK;
    public static final CleverTapEvent CLEVERTAP_ON_ONE_TIME_VARIABLES_CHANGED;
    public static final CleverTapEvent CLEVERTAP_ON_PUSH_PERMISSION_RESPONSE;
    public static final CleverTapEvent CLEVERTAP_ON_VALUE_CHANGED;
    public static final CleverTapEvent CLEVERTAP_ON_VARIABLES_CHANGED;
    public static final CleverTapEvent CLEVERTAP_PRODUCT_CONFIG_DID_ACTIVATE;
    public static final CleverTapEvent CLEVERTAP_PRODUCT_CONFIG_DID_FETCH;
    public static final CleverTapEvent CLEVERTAP_PROFILE_SYNC;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE;
    private final boolean bufferable;
    private final String eventName;
    public static final CleverTapEvent CLEVERTAP_PROFILE_DID_INITIALIZE = new CleverTapEvent("CLEVERTAP_PROFILE_DID_INITIALIZE", 0, "CleverTapProfileDidInitialize", true);
    public static final CleverTapEvent CLEVERTAP_IN_APP_NOTIFICATION_DISMISSED = new CleverTapEvent("CLEVERTAP_IN_APP_NOTIFICATION_DISMISSED", 2, "CleverTapInAppNotificationDismissed", true);
    public static final CleverTapEvent CLEVERTAP_IN_APP_NOTIFICATION_SHOWED = new CleverTapEvent("CLEVERTAP_IN_APP_NOTIFICATION_SHOWED", 3, "CleverTapInAppNotificationShowed", true);
    public static final CleverTapEvent CLEVERTAP_INBOX_DID_INITIALIZE = new CleverTapEvent("CLEVERTAP_INBOX_DID_INITIALIZE", 4, "CleverTapInboxDidInitialize", true);
    public static final CleverTapEvent CLEVERTAP_ON_INAPP_BUTTON_CLICK = new CleverTapEvent("CLEVERTAP_ON_INAPP_BUTTON_CLICK", 8, "CleverTapInAppNotificationButtonTapped", true);
    public static final CleverTapEvent CLEVERTAP_ON_DISPLAY_UNITS_LOADED = new CleverTapEvent("CLEVERTAP_ON_DISPLAY_UNITS_LOADED", 9, "CleverTapDisplayUnitsLoaded", true);
    public static final CleverTapEvent CLEVERTAP_FEATURE_FLAGS_DID_UPDATE = new CleverTapEvent("CLEVERTAP_FEATURE_FLAGS_DID_UPDATE", 10, "CleverTapFeatureFlagsDidUpdate", true);
    public static final CleverTapEvent CLEVERTAP_PRODUCT_CONFIG_DID_INITIALIZE = new CleverTapEvent("CLEVERTAP_PRODUCT_CONFIG_DID_INITIALIZE", 11, "CleverTapProductConfigDidInitialize", true);
    public static final CleverTapEvent CLEVERTAP_PUSH_NOTIFICATION_CLICKED = new CleverTapEvent("CLEVERTAP_PUSH_NOTIFICATION_CLICKED", 14, "CleverTapPushNotificationClicked", true);
    public static final CleverTapEvent CLEVERTAP_CUSTOM_TEMPLATE_PRESENT = new CleverTapEvent("CLEVERTAP_CUSTOM_TEMPLATE_PRESENT", 19, "CleverTapCustomTemplatePresent", true);
    public static final CleverTapEvent CLEVERTAP_CUSTOM_FUNCTION_PRESENT = new CleverTapEvent("CLEVERTAP_CUSTOM_FUNCTION_PRESENT", 20, "CleverTapCustomFunctionPresent", true);
    public static final CleverTapEvent CLEVERTAP_ON_VARIABLES_CHANGED_AND_NO_DOWNLOADS_PENDING = new CleverTapEvent("CLEVERTAP_ON_VARIABLES_CHANGED_AND_NO_DOWNLOADS_PENDING", 23, "CleverTapOnVariablesChangedAndNoDownloadsPending", false, 2, null);
    public static final CleverTapEvent CLEVERTAP_ONCE_VARIABLES_CHANGED_AND_NO_DOWNLOADS_PENDING = new CleverTapEvent("CLEVERTAP_ONCE_VARIABLES_CHANGED_AND_NO_DOWNLOADS_PENDING", 24, "CleverTapOnceVariablesChangedAndNoDownloadsPending", false, 2, null);

    private static final /* synthetic */ CleverTapEvent[] $values() {
        return new CleverTapEvent[]{CLEVERTAP_PROFILE_DID_INITIALIZE, CLEVERTAP_PROFILE_SYNC, CLEVERTAP_IN_APP_NOTIFICATION_DISMISSED, CLEVERTAP_IN_APP_NOTIFICATION_SHOWED, CLEVERTAP_INBOX_DID_INITIALIZE, CLEVERTAP_INBOX_MESSAGES_DID_UPDATE, CLEVERTAP_ON_INBOX_BUTTON_CLICK, CLEVERTAP_ON_INBOX_MESSAGE_CLICK, CLEVERTAP_ON_INAPP_BUTTON_CLICK, CLEVERTAP_ON_DISPLAY_UNITS_LOADED, CLEVERTAP_FEATURE_FLAGS_DID_UPDATE, CLEVERTAP_PRODUCT_CONFIG_DID_INITIALIZE, CLEVERTAP_PRODUCT_CONFIG_DID_FETCH, CLEVERTAP_PRODUCT_CONFIG_DID_ACTIVATE, CLEVERTAP_PUSH_NOTIFICATION_CLICKED, CLEVERTAP_ON_PUSH_PERMISSION_RESPONSE, CLEVERTAP_ON_VARIABLES_CHANGED, CLEVERTAP_ON_ONE_TIME_VARIABLES_CHANGED, CLEVERTAP_ON_VALUE_CHANGED, CLEVERTAP_CUSTOM_TEMPLATE_PRESENT, CLEVERTAP_CUSTOM_FUNCTION_PRESENT, CLEVERTAP_CUSTOM_TEMPLATE_CLOSE, CLEVERTAP_ON_FILE_VALUE_CHANGED, CLEVERTAP_ON_VARIABLES_CHANGED_AND_NO_DOWNLOADS_PENDING, CLEVERTAP_ONCE_VARIABLES_CHANGED_AND_NO_DOWNLOADS_PENDING};
    }

    @JvmStatic
    public static final CleverTapEvent fromName(String str) {
        return INSTANCE.fromName(str);
    }

    public static EnumEntries<CleverTapEvent> getEntries() {
        return $ENTRIES;
    }

    public static CleverTapEvent valueOf(String str) {
        return (CleverTapEvent) Enum.valueOf(CleverTapEvent.class, str);
    }

    public static CleverTapEvent[] values() {
        return (CleverTapEvent[]) $VALUES.clone();
    }

    public final boolean getBufferable() {
        return this.bufferable;
    }

    public final String getEventName() {
        return this.eventName;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.eventName;
    }

    private CleverTapEvent(String str, int i, String str2, boolean z) {
        this.eventName = str2;
        this.bufferable = z;
    }

    /* synthetic */ CleverTapEvent(String str, int i, String str2, boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, i, str2, (i2 & 2) != 0 ? false : z);
    }

    static {
        boolean z = false;
        int i = 2;
        DefaultConstructorMarker defaultConstructorMarker = null;
        CLEVERTAP_PROFILE_SYNC = new CleverTapEvent("CLEVERTAP_PROFILE_SYNC", 1, "CleverTapProfileSync", z, i, defaultConstructorMarker);
        CLEVERTAP_INBOX_MESSAGES_DID_UPDATE = new CleverTapEvent("CLEVERTAP_INBOX_MESSAGES_DID_UPDATE", 5, "CleverTapInboxMessagesDidUpdate", z, i, defaultConstructorMarker);
        boolean z2 = false;
        int i2 = 2;
        DefaultConstructorMarker defaultConstructorMarker2 = null;
        CLEVERTAP_ON_INBOX_BUTTON_CLICK = new CleverTapEvent("CLEVERTAP_ON_INBOX_BUTTON_CLICK", 6, "CleverTapInboxMessageButtonTapped", z2, i2, defaultConstructorMarker2);
        CLEVERTAP_ON_INBOX_MESSAGE_CLICK = new CleverTapEvent("CLEVERTAP_ON_INBOX_MESSAGE_CLICK", 7, "CleverTapInboxMessageTapped", z, i, defaultConstructorMarker);
        CLEVERTAP_PRODUCT_CONFIG_DID_FETCH = new CleverTapEvent("CLEVERTAP_PRODUCT_CONFIG_DID_FETCH", 12, "CleverTapProductConfigDidFetch", z, i, defaultConstructorMarker);
        CLEVERTAP_PRODUCT_CONFIG_DID_ACTIVATE = new CleverTapEvent("CLEVERTAP_PRODUCT_CONFIG_DID_ACTIVATE", 13, "CleverTapProductConfigDidActivate", z2, i2, defaultConstructorMarker2);
        CLEVERTAP_ON_PUSH_PERMISSION_RESPONSE = new CleverTapEvent("CLEVERTAP_ON_PUSH_PERMISSION_RESPONSE", 15, "CleverTapPushPermissionResponseReceived", z, i, defaultConstructorMarker);
        CLEVERTAP_ON_VARIABLES_CHANGED = new CleverTapEvent("CLEVERTAP_ON_VARIABLES_CHANGED", 16, "CleverTapOnVariablesChanged", z2, i2, defaultConstructorMarker2);
        CLEVERTAP_ON_ONE_TIME_VARIABLES_CHANGED = new CleverTapEvent("CLEVERTAP_ON_ONE_TIME_VARIABLES_CHANGED", 17, "CleverTapOnOneTimeVariablesChanged", z, i, defaultConstructorMarker);
        CLEVERTAP_ON_VALUE_CHANGED = new CleverTapEvent("CLEVERTAP_ON_VALUE_CHANGED", 18, "CleverTapOnValueChanged", z2, i2, defaultConstructorMarker2);
        CLEVERTAP_CUSTOM_TEMPLATE_CLOSE = new CleverTapEvent("CLEVERTAP_CUSTOM_TEMPLATE_CLOSE", 21, "CleverTapCustomTemplateClose", z, i, defaultConstructorMarker);
        CLEVERTAP_ON_FILE_VALUE_CHANGED = new CleverTapEvent("CLEVERTAP_ON_FILE_VALUE_CHANGED", 22, "CleverTapOnFileValueChanged", z2, i2, defaultConstructorMarker2);
        CleverTapEvent[] cleverTapEventArr$values = $values();
        $VALUES = cleverTapEventArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(cleverTapEventArr$values);
        INSTANCE = new Companion(null);
    }

    /* compiled from: CleverTapEvent.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/clevertap/react/CleverTapEvent$Companion;", "", "()V", "fromName", "Lcom/clevertap/react/CleverTapEvent;", "eventName", "", "clevertap-react-native_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final CleverTapEvent fromName(String eventName) {
            Intrinsics.checkNotNullParameter(eventName, "eventName");
            for (CleverTapEvent cleverTapEvent : CleverTapEvent.values()) {
                if (Intrinsics.areEqual(cleverTapEvent.getEventName(), eventName)) {
                    return cleverTapEvent;
                }
            }
            return null;
        }
    }
}
