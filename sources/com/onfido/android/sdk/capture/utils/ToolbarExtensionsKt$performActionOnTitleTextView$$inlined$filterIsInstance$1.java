package com.onfido.android.sdk.capture.utils;

import android.widget.TextView;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u00012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\n¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"<anonymous>", "", "R", "it", "", "invoke", "(Ljava/lang/Object;)Ljava/lang/Boolean;", "kotlin/sequences/SequencesKt___SequencesKt$filterIsInstance$1"}, k = 3, mv = {1, 9, 0}, xi = 176)
/* loaded from: classes2.dex */
public final class ToolbarExtensionsKt$performActionOnTitleTextView$$inlined$filterIsInstance$1 extends Lambda implements Function1<Object, Boolean> {
    public static final ToolbarExtensionsKt$performActionOnTitleTextView$$inlined$filterIsInstance$1 INSTANCE = new ToolbarExtensionsKt$performActionOnTitleTextView$$inlined$filterIsInstance$1();

    public ToolbarExtensionsKt$performActionOnTitleTextView$$inlined$filterIsInstance$1() {
        super(1);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function1
    public final Boolean invoke(Object obj) {
        return Boolean.valueOf(obj instanceof TextView);
    }
}
