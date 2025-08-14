package com.onfido.workflow.internal.ui.model;

import com.clevertap.android.sdk.Constants;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MediaUpload.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0080\b\u0018\u00002\u00020\u0001:\u0001\u0014B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Lcom/onfido/workflow/internal/ui/model/MediaUpload;", "", "id", "", "type", "Lcom/onfido/workflow/internal/ui/model/MediaUpload$Type;", "(Ljava/lang/String;Lcom/onfido/workflow/internal/ui/model/MediaUpload$Type;)V", "getId", "()Ljava/lang/String;", "getType", "()Lcom/onfido/workflow/internal/ui/model/MediaUpload$Type;", "component1", "component2", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "", "toString", "Type", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final /* data */ class MediaUpload {
    private final String id;
    private final Type type;

    public static /* synthetic */ MediaUpload copy$default(MediaUpload mediaUpload, String str, Type type, int i, Object obj) {
        if ((i & 1) != 0) {
            str = mediaUpload.id;
        }
        if ((i & 2) != 0) {
            type = mediaUpload.type;
        }
        return mediaUpload.copy(str, type);
    }

    /* renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* renamed from: component2, reason: from getter */
    public final Type getType() {
        return this.type;
    }

    public final MediaUpload copy(String id, Type type) {
        Intrinsics.checkNotNullParameter(id, "id");
        return new MediaUpload(id, type);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MediaUpload)) {
            return false;
        }
        MediaUpload mediaUpload = (MediaUpload) other;
        return Intrinsics.areEqual(this.id, mediaUpload.id) && this.type == mediaUpload.type;
    }

    public final String getId() {
        return this.id;
    }

    public final Type getType() {
        return this.type;
    }

    public int hashCode() {
        int iHashCode = this.id.hashCode() * 31;
        Type type = this.type;
        return iHashCode + (type == null ? 0 : type.hashCode());
    }

    public String toString() {
        return "MediaUpload(id=" + this.id + ", type=" + this.type + ")";
    }

    public MediaUpload(String id, Type type) {
        Intrinsics.checkNotNullParameter(id, "id");
        this.id = id;
        this.type = type;
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: MediaUpload.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/onfido/workflow/internal/ui/model/MediaUpload$Type;", "", "(Ljava/lang/String;I)V", "DOCUMENT_PHOTO", "DOCUMENT_VIDEO", "DOCUMENT_NFC", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Type {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ Type[] $VALUES;
        public static final Type DOCUMENT_PHOTO = new Type("DOCUMENT_PHOTO", 0);
        public static final Type DOCUMENT_VIDEO = new Type("DOCUMENT_VIDEO", 1);
        public static final Type DOCUMENT_NFC = new Type("DOCUMENT_NFC", 2);

        private static final /* synthetic */ Type[] $values() {
            return new Type[]{DOCUMENT_PHOTO, DOCUMENT_VIDEO, DOCUMENT_NFC};
        }

        public static EnumEntries<Type> getEntries() {
            return $ENTRIES;
        }

        public static Type valueOf(String str) {
            return (Type) Enum.valueOf(Type.class, str);
        }

        public static Type[] values() {
            return (Type[]) $VALUES.clone();
        }

        private Type(String str, int i) {
        }

        static {
            Type[] typeArr$values = $values();
            $VALUES = typeArr$values;
            $ENTRIES = EnumEntriesKt.enumEntries(typeArr$values);
        }
    }
}
