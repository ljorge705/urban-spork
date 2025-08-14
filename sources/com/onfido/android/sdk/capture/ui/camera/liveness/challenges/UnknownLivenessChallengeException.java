package com.onfido.android.sdk.capture.ui.camera.liveness.challenges;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/UnknownLivenessChallengeException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "challengeString", "", "(Ljava/lang/String;)V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class UnknownLivenessChallengeException extends Exception {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UnknownLivenessChallengeException(String challengeString) {
        super("The retrieved challenge is unknown and cannot be processed: " + challengeString);
        Intrinsics.checkNotNullParameter(challengeString, "challengeString");
    }
}
