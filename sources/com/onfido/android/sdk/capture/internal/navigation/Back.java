package com.onfido.android.sdk.capture.internal.navigation;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0013\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0096\u0002J\b\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/navigation/Back;", "Lcom/onfido/android/sdk/capture/internal/navigation/NavigationCommand;", "()V", "equals", "", "other", "", "hashCode", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class Back extends NavigationCommand {
    public static final Back INSTANCE = new Back();

    private Back() {
        super(null);
    }

    public boolean equals(Object other) {
        return other != null && Intrinsics.areEqual(other.getClass(), Back.class);
    }

    public int hashCode() {
        return 1;
    }
}
