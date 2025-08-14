package com.onfido.android.sdk.capture.errors;

import com.clevertap.android.sdk.Constants;
import com.oblador.keychain.KeychainModule;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ErrorDescriptor.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0007J$\u0010\r\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u000eJ\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Lcom/onfido/android/sdk/capture/errors/ErrorDescriptor;", "", "title", "", KeychainModule.AuthPromptOptions.SUBTITLE, "(ILjava/lang/Integer;)V", "getSubtitle", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getTitle", "()I", "component1", "component2", Constants.COPY_TYPE, "(ILjava/lang/Integer;)Lcom/onfido/android/sdk/capture/errors/ErrorDescriptor;", "equals", "", "other", "hashCode", "toString", "", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class ErrorDescriptor {
    private final Integer subtitle;
    private final int title;

    public static /* synthetic */ ErrorDescriptor copy$default(ErrorDescriptor errorDescriptor, int i, Integer num, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = errorDescriptor.title;
        }
        if ((i2 & 2) != 0) {
            num = errorDescriptor.subtitle;
        }
        return errorDescriptor.copy(i, num);
    }

    /* renamed from: component1, reason: from getter */
    public final int getTitle() {
        return this.title;
    }

    /* renamed from: component2, reason: from getter */
    public final Integer getSubtitle() {
        return this.subtitle;
    }

    public final ErrorDescriptor copy(int title, Integer subtitle) {
        return new ErrorDescriptor(title, subtitle);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ErrorDescriptor)) {
            return false;
        }
        ErrorDescriptor errorDescriptor = (ErrorDescriptor) other;
        return this.title == errorDescriptor.title && Intrinsics.areEqual(this.subtitle, errorDescriptor.subtitle);
    }

    public final Integer getSubtitle() {
        return this.subtitle;
    }

    public final int getTitle() {
        return this.title;
    }

    public int hashCode() {
        int iHashCode = Integer.hashCode(this.title) * 31;
        Integer num = this.subtitle;
        return iHashCode + (num == null ? 0 : num.hashCode());
    }

    public String toString() {
        return "ErrorDescriptor(title=" + this.title + ", subtitle=" + this.subtitle + ")";
    }

    public ErrorDescriptor(int i, Integer num) {
        this.title = i;
        this.subtitle = num;
    }

    public /* synthetic */ ErrorDescriptor(int i, Integer num, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, (i2 & 2) != 0 ? null : num);
    }
}
