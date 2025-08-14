package com.clevertap.android.sdk.inapp.customtemplates;

import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.variables.CTVariableUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TemplateArgument.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\b\u0080\u0001\u0018\u0000 \u000b2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000bB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0003H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\f"}, d2 = {"Lcom/clevertap/android/sdk/inapp/customtemplates/TemplateArgumentType;", "", "stringName", "", "(Ljava/lang/String;ILjava/lang/String;)V", "toString", "STRING", "BOOLEAN", "NUMBER", "FILE", "ACTION", "Companion", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public enum TemplateArgumentType {
    STRING(CTVariableUtils.STRING),
    BOOLEAN(CTVariableUtils.BOOLEAN),
    NUMBER(CTVariableUtils.NUMBER),
    FILE("file"),
    ACTION(Constants.KEY_ACTION);


    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String stringName;

    @Override // java.lang.Enum
    public String toString() {
        return this.stringName;
    }

    TemplateArgumentType(String str) {
        this.stringName = str;
    }

    /* compiled from: TemplateArgument.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/clevertap/android/sdk/inapp/customtemplates/TemplateArgumentType$Companion;", "", "()V", "fromString", "Lcom/clevertap/android/sdk/inapp/customtemplates/TemplateArgumentType;", CTVariableUtils.STRING, "", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final TemplateArgumentType fromString(String string) {
            Intrinsics.checkNotNullParameter(string, "string");
            for (TemplateArgumentType templateArgumentType : TemplateArgumentType.values()) {
                if (Intrinsics.areEqual(templateArgumentType.stringName, string)) {
                    return templateArgumentType;
                }
            }
            return null;
        }
    }
}
