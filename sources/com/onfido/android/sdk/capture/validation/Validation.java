package com.onfido.android.sdk.capture.validation;

import com.onfido.api.client.ValidationLevel;
import com.onfido.api.client.ValidationType;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0017\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u000b\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0010"}, d2 = {"Lcom/onfido/android/sdk/capture/validation/Validation;", "", "validationType", "Lcom/onfido/api/client/ValidationType;", "validationLevel", "Lcom/onfido/api/client/ValidationLevel;", "(Lcom/onfido/api/client/ValidationType;Lcom/onfido/api/client/ValidationLevel;)V", "level", "", "getLevel", "()Ljava/lang/String;", "type", "getType", "getValidationType$onfido_capture_sdk_core_release", "()Lcom/onfido/api/client/ValidationType;", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class Validation {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String level;
    private final String type;
    private final ValidationType validationType;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001d\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0000¢\u0006\u0002\b\t¨\u0006\n"}, d2 = {"Lcom/onfido/android/sdk/capture/validation/Validation$Companion;", "", "()V", "fromInternalOnfidoTypes", "Lcom/onfido/android/sdk/capture/validation/Validation;", "validationType", "Lcom/onfido/api/client/ValidationType;", "validationLevel", "Lcom/onfido/api/client/ValidationLevel;", "fromInternalOnfidoTypes$onfido_capture_sdk_core_release", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public final Validation fromInternalOnfidoTypes$onfido_capture_sdk_core_release(ValidationType validationType, ValidationLevel validationLevel) {
            Intrinsics.checkNotNullParameter(validationType, "validationType");
            Intrinsics.checkNotNullParameter(validationLevel, "validationLevel");
            return new Validation(validationType, validationLevel);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public Validation(ValidationType validationType, ValidationLevel validationLevel) {
        Intrinsics.checkNotNullParameter(validationType, "validationType");
        Intrinsics.checkNotNullParameter(validationLevel, "validationLevel");
        this.validationType = validationType;
        String id = validationType.getId();
        Intrinsics.checkNotNullExpressionValue(id, "getId(...)");
        this.type = id;
        String id2 = validationLevel.getId();
        Intrinsics.checkNotNullExpressionValue(id2, "getId(...)");
        this.level = id2;
    }

    public final String getLevel() {
        return this.level;
    }

    public final String getType() {
        return this.type;
    }

    /* renamed from: getValidationType$onfido_capture_sdk_core_release, reason: from getter */
    public final ValidationType getValidationType() {
        return this.validationType;
    }
}
