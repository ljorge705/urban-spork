package com.onfido.android.sdk.capture.ui.options;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Deprecated(level = DeprecationLevel.WARNING, message = "This step will be deprecated soon, and should be removed from every flow using it")
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/options/MessageScreenStep;", "Lcom/onfido/android/sdk/capture/ui/options/FlowStep;", "title", "", "message", "nextButtonText", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class MessageScreenStep extends FlowStep {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MessageScreenStep(String title, String message, String nextButtonText) {
        super(FlowAction.MESSAGE);
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(nextButtonText, "nextButtonText");
        setOptions(new MessageScreenOptions(title, message, nextButtonText));
    }
}
