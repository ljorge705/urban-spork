package com.onfido.android.sdk.capture.ui.camera;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
/* synthetic */ class CaptureUploadService$uploadDocumentVideo$1 extends FunctionReferenceImpl implements Function1<String, Unit> {
    CaptureUploadService$uploadDocumentVideo$1(Object obj) {
        super(1, obj, CaptureUploadServiceListener.class, "onDocumentVideoUploaded", "onDocumentVideoUploaded(Ljava/lang/String;)V", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(String str) {
        invoke2(str);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(String p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        ((CaptureUploadServiceListener) this.receiver).onDocumentVideoUploaded(p0);
    }
}
