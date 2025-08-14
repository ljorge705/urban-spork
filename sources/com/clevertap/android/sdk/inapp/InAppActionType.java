package com.clevertap.android.sdk.inapp;

import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.variables.CTVariableUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CTInAppAction.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\b\u0080\u0001\u0018\u0000 \u000b2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000bB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0003H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\f"}, d2 = {"Lcom/clevertap/android/sdk/inapp/InAppActionType;", "", "stringValue", "", "(Ljava/lang/String;ILjava/lang/String;)V", "toString", "CLOSE", "OPEN_URL", "KEY_VALUES", "CUSTOM_CODE", "REQUEST_FOR_PERMISSIONS", "Companion", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public enum InAppActionType {
    CLOSE(Constants.KEY_HIDE_CLOSE),
    OPEN_URL("url"),
    KEY_VALUES(Constants.KEY_KV),
    CUSTOM_CODE("custom-code"),
    REQUEST_FOR_PERMISSIONS(Constants.KEY_REQUEST_FOR_NOTIFICATION_PERMISSION);


    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String stringValue;

    @Override // java.lang.Enum
    public String toString() {
        return this.stringValue;
    }

    InAppActionType(String str) {
        this.stringValue = str;
    }

    /* compiled from: CTInAppAction.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/clevertap/android/sdk/inapp/InAppActionType$Companion;", "", "()V", "fromString", "Lcom/clevertap/android/sdk/inapp/InAppActionType;", CTVariableUtils.STRING, "", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final InAppActionType fromString(String string) {
            Intrinsics.checkNotNullParameter(string, "string");
            for (InAppActionType inAppActionType : InAppActionType.values()) {
                if (Intrinsics.areEqual(inAppActionType.stringValue, string)) {
                    return inAppActionType;
                }
            }
            return null;
        }
    }
}
