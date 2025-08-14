package com.uxcam.screenaction.compose;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes6.dex */
final /* synthetic */ class ComposeViewsKt$getComposeScannableViews$scannableViews$1 extends FunctionReferenceImpl implements Function1<ComposeLayoutInfo, ScannableView> {

    /* renamed from: a, reason: collision with root package name */
    public static final ComposeViewsKt$getComposeScannableViews$scannableViews$1 f233a = new ComposeViewsKt$getComposeScannableViews$scannableViews$1();

    public ComposeViewsKt$getComposeScannableViews$scannableViews$1() {
        super(1, ScannableViewKt.class, "toScannableView", "toScannableView(Lcom/uxcam/screenaction/compose/ComposeLayoutInfo;)Lcom/uxcam/screenaction/compose/ScannableView;", 1);
    }

    @Override // kotlin.jvm.functions.Function1
    public final ScannableView invoke(ComposeLayoutInfo composeLayoutInfo) {
        ComposeLayoutInfo p0 = composeLayoutInfo;
        Intrinsics.checkNotNullParameter(p0, "p0");
        return ScannableViewKt.a(p0);
    }
}
