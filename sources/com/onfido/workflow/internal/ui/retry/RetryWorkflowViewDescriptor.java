package com.onfido.workflow.internal.ui.retry;

import android.os.Parcel;
import android.os.Parcelable;
import com.clevertap.android.sdk.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RetryWorkflowViewDescriptor.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0081\b\u0018\u00002\u00020\u0001:\u0002\u001c\u001dB\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0003J\t\u0010\u0014\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\u0019\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u000fHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u001e"}, d2 = {"Lcom/onfido/workflow/internal/ui/retry/RetryWorkflowViewDescriptor;", "Landroid/os/Parcelable;", "stringResIds", "Lcom/onfido/workflow/internal/ui/retry/RetryWorkflowViewDescriptor$StringResIds;", "texts", "Lcom/onfido/workflow/internal/ui/retry/RetryWorkflowViewDescriptor$Texts;", "(Lcom/onfido/workflow/internal/ui/retry/RetryWorkflowViewDescriptor$StringResIds;Lcom/onfido/workflow/internal/ui/retry/RetryWorkflowViewDescriptor$Texts;)V", "getStringResIds", "()Lcom/onfido/workflow/internal/ui/retry/RetryWorkflowViewDescriptor$StringResIds;", "getTexts", "()Lcom/onfido/workflow/internal/ui/retry/RetryWorkflowViewDescriptor$Texts;", "component1", "component2", Constants.COPY_TYPE, "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "StringResIds", "Texts", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final /* data */ class RetryWorkflowViewDescriptor implements Parcelable {
    public static final Parcelable.Creator<RetryWorkflowViewDescriptor> CREATOR = new Creator();
    private final StringResIds stringResIds;
    private final Texts texts;

    /* compiled from: RetryWorkflowViewDescriptor.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class Creator implements Parcelable.Creator<RetryWorkflowViewDescriptor> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final RetryWorkflowViewDescriptor createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new RetryWorkflowViewDescriptor(StringResIds.CREATOR.createFromParcel(parcel), parcel.readInt() == 0 ? null : Texts.CREATOR.createFromParcel(parcel));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final RetryWorkflowViewDescriptor[] newArray(int i) {
            return new RetryWorkflowViewDescriptor[i];
        }
    }

    public static /* synthetic */ RetryWorkflowViewDescriptor copy$default(RetryWorkflowViewDescriptor retryWorkflowViewDescriptor, StringResIds stringResIds, Texts texts, int i, Object obj) {
        if ((i & 1) != 0) {
            stringResIds = retryWorkflowViewDescriptor.stringResIds;
        }
        if ((i & 2) != 0) {
            texts = retryWorkflowViewDescriptor.texts;
        }
        return retryWorkflowViewDescriptor.copy(stringResIds, texts);
    }

    /* renamed from: component1, reason: from getter */
    public final StringResIds getStringResIds() {
        return this.stringResIds;
    }

    /* renamed from: component2, reason: from getter */
    public final Texts getTexts() {
        return this.texts;
    }

    public final RetryWorkflowViewDescriptor copy(StringResIds stringResIds, Texts texts) {
        Intrinsics.checkNotNullParameter(stringResIds, "stringResIds");
        return new RetryWorkflowViewDescriptor(stringResIds, texts);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RetryWorkflowViewDescriptor)) {
            return false;
        }
        RetryWorkflowViewDescriptor retryWorkflowViewDescriptor = (RetryWorkflowViewDescriptor) other;
        return Intrinsics.areEqual(this.stringResIds, retryWorkflowViewDescriptor.stringResIds) && Intrinsics.areEqual(this.texts, retryWorkflowViewDescriptor.texts);
    }

    public final StringResIds getStringResIds() {
        return this.stringResIds;
    }

    public final Texts getTexts() {
        return this.texts;
    }

    public int hashCode() {
        int iHashCode = this.stringResIds.hashCode() * 31;
        Texts texts = this.texts;
        return iHashCode + (texts == null ? 0 : texts.hashCode());
    }

    public String toString() {
        return "RetryWorkflowViewDescriptor(stringResIds=" + this.stringResIds + ", texts=" + this.texts + ")";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkNotNullParameter(parcel, "out");
        this.stringResIds.writeToParcel(parcel, flags);
        Texts texts = this.texts;
        if (texts == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            texts.writeToParcel(parcel, flags);
        }
    }

    public RetryWorkflowViewDescriptor(StringResIds stringResIds, Texts texts) {
        Intrinsics.checkNotNullParameter(stringResIds, "stringResIds");
        this.stringResIds = stringResIds;
        this.texts = texts;
    }

    public /* synthetic */ RetryWorkflowViewDescriptor(StringResIds stringResIds, Texts texts, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(stringResIds, (i & 2) != 0 ? null : texts);
    }

    /* compiled from: RetryWorkflowViewDescriptor.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B#\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\u0019\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u001c"}, d2 = {"Lcom/onfido/workflow/internal/ui/retry/RetryWorkflowViewDescriptor$StringResIds;", "Landroid/os/Parcelable;", "title", "", "description", "button", "(III)V", "getButton", "()I", "getDescription", "getTitle", "component1", "component2", "component3", Constants.COPY_TYPE, "describeContents", "equals", "", "other", "", "hashCode", "toString", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class StringResIds implements Parcelable {
        public static final Parcelable.Creator<StringResIds> CREATOR = new Creator();
        private final int button;
        private final int description;
        private final int title;

        /* compiled from: RetryWorkflowViewDescriptor.kt */
        @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<StringResIds> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final StringResIds createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new StringResIds(parcel.readInt(), parcel.readInt(), parcel.readInt());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final StringResIds[] newArray(int i) {
                return new StringResIds[i];
            }
        }

        public static /* synthetic */ StringResIds copy$default(StringResIds stringResIds, int i, int i2, int i3, int i4, Object obj) {
            if ((i4 & 1) != 0) {
                i = stringResIds.title;
            }
            if ((i4 & 2) != 0) {
                i2 = stringResIds.description;
            }
            if ((i4 & 4) != 0) {
                i3 = stringResIds.button;
            }
            return stringResIds.copy(i, i2, i3);
        }

        /* renamed from: component1, reason: from getter */
        public final int getTitle() {
            return this.title;
        }

        /* renamed from: component2, reason: from getter */
        public final int getDescription() {
            return this.description;
        }

        /* renamed from: component3, reason: from getter */
        public final int getButton() {
            return this.button;
        }

        public final StringResIds copy(int title, int description, int button) {
            return new StringResIds(title, description, button);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof StringResIds)) {
                return false;
            }
            StringResIds stringResIds = (StringResIds) other;
            return this.title == stringResIds.title && this.description == stringResIds.description && this.button == stringResIds.button;
        }

        public final int getButton() {
            return this.button;
        }

        public final int getDescription() {
            return this.description;
        }

        public final int getTitle() {
            return this.title;
        }

        public int hashCode() {
            return (((Integer.hashCode(this.title) * 31) + Integer.hashCode(this.description)) * 31) + Integer.hashCode(this.button);
        }

        public String toString() {
            return "StringResIds(title=" + this.title + ", description=" + this.description + ", button=" + this.button + ")";
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int flags) {
            Intrinsics.checkNotNullParameter(parcel, "out");
            parcel.writeInt(this.title);
            parcel.writeInt(this.description);
            parcel.writeInt(this.button);
        }

        public StringResIds(int i, int i2, int i3) {
            this.title = i;
            this.description = i2;
            this.button = i3;
        }
    }

    /* compiled from: RetryWorkflowViewDescriptor.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001J\u0019\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u001c"}, d2 = {"Lcom/onfido/workflow/internal/ui/retry/RetryWorkflowViewDescriptor$Texts;", "Landroid/os/Parcelable;", "title", "", "description", "button", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getButton", "()Ljava/lang/String;", "getDescription", "getTitle", "component1", "component2", "component3", Constants.COPY_TYPE, "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Texts implements Parcelable {
        public static final Parcelable.Creator<Texts> CREATOR = new Creator();
        private final String button;
        private final String description;
        private final String title;

        /* compiled from: RetryWorkflowViewDescriptor.kt */
        @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<Texts> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final Texts createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new Texts(parcel.readString(), parcel.readString(), parcel.readString());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final Texts[] newArray(int i) {
                return new Texts[i];
            }
        }

        public static /* synthetic */ Texts copy$default(Texts texts, String str, String str2, String str3, int i, Object obj) {
            if ((i & 1) != 0) {
                str = texts.title;
            }
            if ((i & 2) != 0) {
                str2 = texts.description;
            }
            if ((i & 4) != 0) {
                str3 = texts.button;
            }
            return texts.copy(str, str2, str3);
        }

        /* renamed from: component1, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        /* renamed from: component2, reason: from getter */
        public final String getDescription() {
            return this.description;
        }

        /* renamed from: component3, reason: from getter */
        public final String getButton() {
            return this.button;
        }

        public final Texts copy(String title, String description, String button) {
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(description, "description");
            Intrinsics.checkNotNullParameter(button, "button");
            return new Texts(title, description, button);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Texts)) {
                return false;
            }
            Texts texts = (Texts) other;
            return Intrinsics.areEqual(this.title, texts.title) && Intrinsics.areEqual(this.description, texts.description) && Intrinsics.areEqual(this.button, texts.button);
        }

        public final String getButton() {
            return this.button;
        }

        public final String getDescription() {
            return this.description;
        }

        public final String getTitle() {
            return this.title;
        }

        public int hashCode() {
            return (((this.title.hashCode() * 31) + this.description.hashCode()) * 31) + this.button.hashCode();
        }

        public String toString() {
            return "Texts(title=" + this.title + ", description=" + this.description + ", button=" + this.button + ")";
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int flags) {
            Intrinsics.checkNotNullParameter(parcel, "out");
            parcel.writeString(this.title);
            parcel.writeString(this.description);
            parcel.writeString(this.button);
        }

        public Texts(String title, String description, String button) {
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(description, "description");
            Intrinsics.checkNotNullParameter(button, "button");
            this.title = title;
            this.description = description;
            this.button = button;
        }
    }
}
