package com.onfido.hosted.web.module;

import android.net.Uri;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b`\u0018\u00002\u00020\u0001J1\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0014\u0010\u0007\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\t\u0012\u0004\u0012\u00020\u00030\bH&¢\u0006\u0002\u0010\nJ\u001e\u0010\u000b\u001a\u00020\u00032\u0014\u0010\u0007\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\t\u0012\u0004\u0012\u00020\u00030\bH&¨\u0006\f"}, d2 = {"Lcom/onfido/hosted/web/module/PickerDelegate;", "", "openDocumentPicker", "", "mimeTypes", "", "", "callback", "Lkotlin/Function1;", "Landroid/net/Uri;", "([Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V", "openTakePicture", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public interface PickerDelegate {
    void openDocumentPicker(String[] mimeTypes, Function1<? super Uri, Unit> callback);

    void openTakePicture(Function1<? super Uri, Unit> callback);
}
