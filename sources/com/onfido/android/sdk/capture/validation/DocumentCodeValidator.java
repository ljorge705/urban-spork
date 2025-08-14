package com.onfido.android.sdk.capture.validation;

import com.oblador.keychain.KeychainModule;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b`\u0018\u0000 \u00062\u00020\u0001:\u0001\u0006J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0007"}, d2 = {"Lcom/onfido/android/sdk/capture/validation/DocumentCodeValidator;", "", "validate", "Lcom/onfido/android/sdk/capture/validation/DocumentCodeValidatorResult;", "text", "", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface DocumentCodeValidator {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/onfido/android/sdk/capture/validation/DocumentCodeValidator$Companion;", "", "()V", KeychainModule.AccessControl.NONE, "Lcom/onfido/android/sdk/capture/validation/DocumentCodeValidator;", "getNone", "()Lcom/onfido/android/sdk/capture/validation/DocumentCodeValidator;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final DocumentCodeValidator None = new DocumentCodeValidator() { // from class: com.onfido.android.sdk.capture.validation.DocumentCodeValidator$Companion$None$1
            @Override // com.onfido.android.sdk.capture.validation.DocumentCodeValidator
            public DocumentCodeValidatorResult validate(String text) {
                Intrinsics.checkNotNullParameter(text, "text");
                return new DocumentCodeValidatorResult(true, "");
            }
        };

        private Companion() {
        }

        public final DocumentCodeValidator getNone() {
            return None;
        }
    }

    DocumentCodeValidatorResult validate(String text);
}
