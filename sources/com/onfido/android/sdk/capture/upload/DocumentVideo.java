package com.onfido.android.sdk.capture.upload;

import com.clevertap.android.sdk.Constants;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Captures.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lcom/onfido/android/sdk/capture/upload/DocumentVideo;", "Ljava/io/Serializable;", "id", "", "(Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class DocumentVideo implements Serializable {
    private final String id;

    public static /* synthetic */ DocumentVideo copy$default(DocumentVideo documentVideo, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = documentVideo.id;
        }
        return documentVideo.copy(str);
    }

    /* renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    public final DocumentVideo copy(String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        return new DocumentVideo(id);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof DocumentVideo) && Intrinsics.areEqual(this.id, ((DocumentVideo) other).id);
    }

    public final String getId() {
        return this.id;
    }

    public int hashCode() {
        return this.id.hashCode();
    }

    public String toString() {
        return "DocumentVideo(id=" + this.id + ")";
    }

    public DocumentVideo(String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        this.id = id;
    }
}
