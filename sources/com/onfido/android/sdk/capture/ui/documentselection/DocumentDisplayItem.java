package com.onfido.android.sdk.capture.ui.documentselection;

import com.onfido.android.sdk.capture.DocumentType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\n\b\u0010\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0007\u001a\u00020\u0005¢\u0006\u0002\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\f¨\u0006\u000f"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/documentselection/DocumentDisplayItem;", "", "documentType", "Lcom/onfido/android/sdk/capture/DocumentType;", "iconRes", "", "titleId", "subtitleId", "(Lcom/onfido/android/sdk/capture/DocumentType;III)V", "getDocumentType", "()Lcom/onfido/android/sdk/capture/DocumentType;", "getIconRes", "()I", "getSubtitleId", "getTitleId", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public class DocumentDisplayItem {
    private final DocumentType documentType;
    private final int iconRes;
    private final int subtitleId;
    private final int titleId;

    public DocumentDisplayItem(DocumentType documentType, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(documentType, "documentType");
        this.documentType = documentType;
        this.iconRes = i;
        this.titleId = i2;
        this.subtitleId = i3;
    }

    public final DocumentType getDocumentType() {
        return this.documentType;
    }

    public final int getIconRes() {
        return this.iconRes;
    }

    public final int getSubtitleId() {
        return this.subtitleId;
    }

    public final int getTitleId() {
        return this.titleId;
    }
}
