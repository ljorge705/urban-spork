package com.onfido.android.sdk.capture.ui.documentselection;

import com.oblador.keychain.KeychainModule;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.document.DocumentPages;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\r"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/documentselection/GenericDocumentItem;", "Lcom/onfido/android/sdk/capture/ui/documentselection/DocumentItem;", "title", "", KeychainModule.AuthPromptOptions.SUBTITLE, "pages", "Lcom/onfido/android/sdk/capture/document/DocumentPages;", "(Ljava/lang/String;Ljava/lang/String;Lcom/onfido/android/sdk/capture/document/DocumentPages;)V", "getPages", "()Lcom/onfido/android/sdk/capture/document/DocumentPages;", "getSubtitle", "()Ljava/lang/String;", "getTitle", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class GenericDocumentItem extends DocumentItem {
    private final DocumentPages pages;
    private final String subtitle;
    private final String title;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GenericDocumentItem(String title, String str, DocumentPages pages) {
        super(DocumentType.GENERIC);
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(pages, "pages");
        this.title = title;
        this.subtitle = str;
        this.pages = pages;
    }

    public final DocumentPages getPages() {
        return this.pages;
    }

    public final String getSubtitle() {
        return this.subtitle;
    }

    public final String getTitle() {
        return this.title;
    }
}
