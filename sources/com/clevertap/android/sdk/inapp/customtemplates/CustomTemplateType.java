package com.clevertap.android.sdk.inapp.customtemplates;

import com.clevertap.android.sdk.variables.CTVariableUtils;
import io.sentry.protocol.SentryStackFrame;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CustomTemplate.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0080\u0001\u0018\u0000 \b2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\bB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0003H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000j\u0002\b\u0006j\u0002\b\u0007¨\u0006\t"}, d2 = {"Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplateType;", "", "stringName", "", "(Ljava/lang/String;ILjava/lang/String;)V", "toString", "TEMPLATE", "FUNCTION", "Companion", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public enum CustomTemplateType {
    TEMPLATE("template"),
    FUNCTION(SentryStackFrame.JsonKeys.FUNCTION);


    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String stringName;

    @Override // java.lang.Enum
    public String toString() {
        return this.stringName;
    }

    CustomTemplateType(String str) {
        this.stringName = str;
    }

    /* compiled from: CustomTemplate.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplateType$Companion;", "", "()V", "fromString", "Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplateType;", CTVariableUtils.STRING, "", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final CustomTemplateType fromString(String string) {
            Intrinsics.checkNotNullParameter(string, "string");
            for (CustomTemplateType customTemplateType : CustomTemplateType.values()) {
                if (Intrinsics.areEqual(customTemplateType.stringName, string)) {
                    return customTemplateType;
                }
            }
            return null;
        }
    }
}
