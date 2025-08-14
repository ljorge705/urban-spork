package com.uxcam.screenaction.compose;

import android.view.View;
import java.lang.reflect.Field;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

@Metadata(d1 = {"\u0000\u0002\n\u0000¨\u0006\u0000"}, d2 = {"screenaction_littleRelease"}, k = 2, mv = {1, 8, 0})
/* loaded from: classes6.dex */
public final class ComposeViewsKt {

    /* renamed from: a, reason: collision with root package name */
    public static final Lazy f232a;

    static {
        LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0) new Function0<Boolean>() { // from class: com.uxcam.screenaction.compose.ComposeViewsKt$isComposeAvailable$2
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                boolean z;
                try {
                    Class.forName("androidx.compose.ui.platform.AndroidComposeView");
                    z = true;
                } catch (Throwable unused) {
                    z = false;
                }
                return Boolean.valueOf(z);
            }
        });
        f232a = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0) new Function0<Field>() { // from class: com.uxcam.screenaction.compose.ComposeViewsKt$viewKeyedTagsField$2
            @Override // kotlin.jvm.functions.Function0
            public final Field invoke() throws NoSuchFieldException, SecurityException {
                try {
                    Field declaredField = View.class.getDeclaredField("mKeyedTags");
                    declaredField.setAccessible(true);
                    return declaredField;
                } catch (NoSuchFieldException unused) {
                    return null;
                }
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0087  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.sequences.Sequence<com.uxcam.screenaction.compose.ComposeLayoutInfo> a(android.view.View r5) throws java.lang.IllegalAccessException, java.lang.NoSuchFieldException, java.lang.SecurityException, java.lang.IllegalArgumentException {
        /*
            kotlin.Lazy r0 = com.uxcam.screenaction.compose.ComposeViewsKt.f232a
            java.lang.Object r0 = r0.getValue()
            java.lang.reflect.Field r0 = (java.lang.reflect.Field) r0
            r1 = 0
            if (r0 == 0) goto L10
            java.lang.Object r5 = r0.get(r5)
            goto L11
        L10:
            r5 = r1
        L11:
            android.util.SparseArray r5 = (android.util.SparseArray) r5
            r0 = 0
            if (r5 != 0) goto L1b
            android.util.SparseArray r5 = new android.util.SparseArray
            r5.<init>(r0)
        L1b:
            int r2 = r5.size()
        L1f:
            if (r0 >= r2) goto L2d
            java.lang.Object r3 = r5.valueAt(r0)
            boolean r4 = r3 instanceof androidx.compose.runtime.Composition
            if (r4 == 0) goto L2a
            goto L2e
        L2a:
            int r0 = r0 + 1
            goto L1f
        L2d:
            r3 = r1
        L2e:
            androidx.compose.runtime.Composition r3 = (androidx.compose.runtime.Composition) r3
            if (r3 != 0) goto L33
            return r1
        L33:
            java.lang.Class r5 = r3.getClass()
            java.lang.String r5 = r5.getName()
            java.lang.String r0 = "androidx.compose.ui.platform.WrappedComposition"
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual(r5, r0)
            r2 = 1
            if (r5 != 0) goto L45
            goto L5e
        L45:
            java.lang.Class r5 = java.lang.Class.forName(r0)
            java.lang.String r0 = "original"
            java.lang.reflect.Field r5 = r5.getDeclaredField(r0)
            r5.setAccessible(r2)
            java.lang.Object r5 = r5.get(r3)
            java.lang.String r0 = "null cannot be cast to non-null type androidx.compose.runtime.Composition"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r5, r0)
            r3 = r5
            androidx.compose.runtime.Composition r3 = (androidx.compose.runtime.Composition) r3
        L5e:
            java.lang.Class r5 = r3.getClass()
            java.lang.String r5 = r5.getName()
            java.lang.String r0 = "androidx.compose.runtime.CompositionImpl"
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual(r5, r0)
            if (r5 != 0) goto L6f
            goto L87
        L6f:
            java.lang.Class r5 = java.lang.Class.forName(r0)
            java.lang.String r0 = "composer"
            java.lang.reflect.Field r5 = r5.getDeclaredField(r0)
            r5.setAccessible(r2)
            java.lang.Object r5 = r5.get(r3)
            boolean r0 = r5 instanceof androidx.compose.runtime.Composer
            if (r0 == 0) goto L87
            androidx.compose.runtime.Composer r5 = (androidx.compose.runtime.Composer) r5
            goto L88
        L87:
            r5 = r1
        L88:
            if (r5 != 0) goto L8b
            return r1
        L8b:
            androidx.compose.runtime.tooling.CompositionData r5 = r5.getCompositionData()
            androidx.compose.ui.tooling.data.Group r5 = androidx.compose.ui.tooling.data.SlotTreeKt.asTree(r5)
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            java.lang.String r0 = ""
            kotlin.sequences.Sequence r5 = com.uxcam.screenaction.compose.ComposeLayoutInfoKt.a(r5, r0)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.uxcam.screenaction.compose.ComposeViewsKt.a(android.view.View):kotlin.sequences.Sequence");
    }
}
