package com.onfido.android.sdk.capture.ui.options;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0096\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0016R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\b\"\u0004\b\f\u0010\nR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\b\"\u0004\b\u000e\u0010\n¨\u0006\u0015"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/options/MessageScreenOptions;", "Lcom/onfido/android/sdk/capture/ui/options/BaseOptions;", "title", "", "message", "nextButtonText", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "setMessage", "(Ljava/lang/String;)V", "getNextButtonText", "setNextButtonText", "getTitle", "setTitle", "equals", "", "other", "", "hashCode", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class MessageScreenOptions extends BaseOptions {
    private String message;
    private String nextButtonText;
    private String title;

    public MessageScreenOptions(String title, String message, String nextButtonText) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(nextButtonText, "nextButtonText");
        this.title = title;
        this.message = message;
        this.nextButtonText = nextButtonText;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MessageScreenOptions)) {
            return false;
        }
        MessageScreenOptions messageScreenOptions = (MessageScreenOptions) other;
        return Intrinsics.areEqual(this.title, messageScreenOptions.title) && Intrinsics.areEqual(this.message, messageScreenOptions.message) && Intrinsics.areEqual(this.nextButtonText, messageScreenOptions.nextButtonText);
    }

    public final String getMessage() {
        return this.message;
    }

    public final String getNextButtonText() {
        return this.nextButtonText;
    }

    public final String getTitle() {
        return this.title;
    }

    public int hashCode() {
        return (((this.title.hashCode() * 31) + this.message.hashCode()) * 31) + this.nextButtonText.hashCode();
    }

    public final void setMessage(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.message = str;
    }

    public final void setNextButtonText(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.nextButtonText = str;
    }

    public final void setTitle(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.title = str;
    }
}
