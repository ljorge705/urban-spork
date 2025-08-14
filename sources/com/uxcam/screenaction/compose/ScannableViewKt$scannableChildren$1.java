package com.uxcam.screenaction.compose;

import android.view.View;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.SequenceScope;

@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0003\u001a\u00020\u0002*\b\u0012\u0004\u0012\u00020\u00010\u0000H\u008a@"}, d2 = {"Lkotlin/sequences/SequenceScope;", "Lcom/uxcam/screenaction/compose/ScannableView;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
@DebugMetadata(c = "com.uxcam.screenaction.compose.ScannableViewKt$scannableChildren$1", f = "ScannableView.kt", i = {0, 0, 1, 1}, l = {69, 82}, m = "invokeSuspend", n = {"$this$sequence", "parsedComposables", "$this$sequence", "i"}, s = {"L$0", "Z$0", "L$0", "I$0"})
/* loaded from: classes6.dex */
final class ScannableViewKt$scannableChildren$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super ScannableView>, Continuation<? super Unit>, Object> {

    /* renamed from: a, reason: collision with root package name */
    public boolean f246a;
    public int b;
    public int c;
    public int d;
    public /* synthetic */ Object e;
    public final /* synthetic */ View f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ScannableViewKt$scannableChildren$1(View view, Continuation<? super ScannableViewKt$scannableChildren$1> continuation) {
        super(2, continuation);
        this.f = view;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ScannableViewKt$scannableChildren$1 scannableViewKt$scannableChildren$1 = new ScannableViewKt$scannableChildren$1(this.f, continuation);
        scannableViewKt$scannableChildren$1.e = obj;
        return scannableViewKt$scannableChildren$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(SequenceScope<? super ScannableView> sequenceScope, Continuation<? super Unit> continuation) {
        return ((ScannableViewKt$scannableChildren$1) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x011b  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:41:0x0102 -> B:46:0x0119). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:44:0x0116 -> B:46:0x0119). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r12) throws java.lang.IllegalAccessException, java.lang.NoSuchFieldException, java.lang.SecurityException, java.lang.IllegalArgumentException {
        /*
            Method dump skipped, instructions count: 286
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.uxcam.screenaction.compose.ScannableViewKt$scannableChildren$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
