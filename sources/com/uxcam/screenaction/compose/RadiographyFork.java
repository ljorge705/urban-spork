package com.uxcam.screenaction.compose;

import android.graphics.Rect;
import androidx.compose.ui.unit.IntOffsetKt;
import com.uxcam.screenaction.compose.ScannableView;
import com.uxcam.screenaction.models.GestureData;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.Dispatchers;

@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/uxcam/screenaction/compose/RadiographyFork;", "Lcom/uxcam/screenaction/compose/ComposeScreenActionProvider;", "screenaction_littleRelease"}, k = 1, mv = {1, 8, 0})
/* loaded from: classes6.dex */
public final class RadiographyFork implements ComposeScreenActionProvider {

    /* renamed from: a, reason: collision with root package name */
    public final CoroutineDispatcher f241a;

    public RadiographyFork() {
        this(0);
    }

    public /* synthetic */ RadiographyFork(int i) {
        this(Dispatchers.getIO());
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    @Override // com.uxcam.screenaction.compose.ComposeScreenActionProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object a(kotlin.coroutines.Continuation r18) {
        /*
            r17 = this;
            r0 = r18
            boolean r1 = r0 instanceof com.uxcam.screenaction.compose.RadiographyFork$getScreenAction$1
            if (r1 == 0) goto L17
            r1 = r0
            com.uxcam.screenaction.compose.RadiographyFork$getScreenAction$1 r1 = (com.uxcam.screenaction.compose.RadiographyFork$getScreenAction$1) r1
            int r2 = r1.g
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r2 & r3
            if (r4 == 0) goto L17
            int r2 = r2 - r3
            r1.g = r2
            r2 = r17
            goto L1e
        L17:
            com.uxcam.screenaction.compose.RadiographyFork$getScreenAction$1 r1 = new com.uxcam.screenaction.compose.RadiographyFork$getScreenAction$1
            r2 = r17
            r1.<init>(r2, r0)
        L1e:
            java.lang.Object r0 = r1.e
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r1.g
            if (r4 == 0) goto La0
            r6 = 1
            if (r4 != r6) goto L98
            java.util.Iterator r4 = r1.d
            java.util.List r13 = r1.c
            com.uxcam.screenaction.models.GestureData r14 = r1.b
            com.uxcam.screenaction.compose.RadiographyFork r15 = r1.f242a
            kotlin.ResultKt.throwOnFailure(r0)
        L36:
            boolean r0 = r4.hasNext()
            if (r0 == 0) goto L64
            java.lang.Object r0 = r4.next()
            r9 = r0
            com.uxcam.screenaction.compose.ScannableView r9 = (com.uxcam.screenaction.compose.ScannableView) r9
            kotlinx.coroutines.CoroutineDispatcher r0 = r15.f241a
            com.uxcam.screenaction.compose.RadiographyFork$getScreenAction$2 r12 = new com.uxcam.screenaction.compose.RadiographyFork$getScreenAction$2
            r16 = 0
            r7 = r12
            r8 = r15
            r10 = r14
            r11 = r13
            r5 = r12
            r12 = r16
            r7.<init>(r8, r9, r10, r11, r12)
            r1.f242a = r15
            r1.b = r14
            r1.c = r13
            r1.d = r4
            r1.g = r6
            java.lang.Object r0 = kotlinx.coroutines.BuildersKt.withContext(r0, r5, r1)
            if (r0 != r3) goto L36
            return r3
        L64:
            java.lang.Object r0 = kotlin.collections.CollectionsKt.firstOrNull(r13)
            com.uxcam.screenaction.compose.ComposeScreenAction r0 = (com.uxcam.screenaction.compose.ComposeScreenAction) r0
            if (r0 == 0) goto L96
            java.lang.String r1 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
            com.uxcam.screenaction.models.ScreenAction r1 = new com.uxcam.screenaction.models.ScreenAction
            android.graphics.Rect r6 = r0.b
            com.uxcam.screenaction.models.GestureData r3 = r0.c
            float r7 = r3.getTime()
            java.lang.String r9 = r0.d
            r4 = -1
            java.lang.String r5 = ""
            r8 = -1
            r10 = 0
            r11 = 0
            r3 = r1
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11)
            java.lang.String r0 = r0.f231a
            if (r0 != 0) goto L8d
            java.lang.String r0 = ""
        L8d:
            r1.setName(r0)
            r0 = 19
            r1.event = r0
            r5 = r1
            goto L97
        L96:
            r5 = 0
        L97:
            return r5
        L98:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        La0:
            kotlin.ResultKt.throwOnFailure(r0)
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r0 = 0
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.uxcam.screenaction.compose.RadiographyFork.a(kotlin.coroutines.Continuation):java.lang.Object");
    }

    public RadiographyFork(CoroutineDispatcher ioDispatcher) {
        Intrinsics.checkNotNullParameter(ioDispatcher, "ioDispatcher");
        this.f241a = ioDispatcher;
    }

    public static ComposeScreenAction a(ScannableView scannableView, GestureData gestureData) {
        try {
            long jIntOffset = IntOffsetKt.IntOffset(gestureData.getX(), gestureData.getY());
            Iterator<ScannableView> it = scannableView.a().iterator();
            ComposeScreenAction composeScreenActionA = null;
            while (it.hasNext() && (composeScreenActionA = a(it.next(), gestureData)) == null) {
            }
            if (composeScreenActionA != null) {
                return composeScreenActionA;
            }
            Intrinsics.checkNotNull(scannableView, "null cannot be cast to non-null type com.uxcam.screenaction.compose.ScannableView.ComposeView");
            ScannableView.ComposeView composeView = (ScannableView.ComposeView) scannableView;
            if (!composeView.b.m4527containsgyyYBs(jIntOffset)) {
                return null;
            }
            String strB = ScannableViewKt.b(composeView);
            if (strB == null || strB.length() == 0) {
                strB = ScannableViewKt.a(composeView);
            }
            return new ComposeScreenAction(strB, new Rect(composeView.b.getLeft(), composeView.b.getTop(), composeView.b.getRight(), composeView.b.getBottom()), gestureData, composeView.f245a);
        } catch (Exception unused) {
            return null;
        }
    }
}
