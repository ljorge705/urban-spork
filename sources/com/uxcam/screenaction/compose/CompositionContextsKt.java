package com.uxcam.screenaction.compose;

import com.uxcam.screenaction.compose.CompositionContextsKt$REFLECTION_CONSTANTS$2;
import java.lang.reflect.Field;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

@Metadata(d1 = {"\u0000\u0002\n\u0000¨\u0006\u0000"}, d2 = {"screenaction_littleRelease"}, k = 2, mv = {1, 8, 0})
/* loaded from: classes6.dex */
public final class CompositionContextsKt {

    /* renamed from: a, reason: collision with root package name */
    public static final Lazy f236a = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0) new Function0<CompositionContextsKt$REFLECTION_CONSTANTS$2.AnonymousClass1>() { // from class: com.uxcam.screenaction.compose.CompositionContextsKt$REFLECTION_CONSTANTS$2

        @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0000\n\u0000\b\n\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"com/uxcam/screenaction/compose/CompositionContextsKt$REFLECTION_CONSTANTS$2$1", "", "screenaction_littleRelease"}, k = 1, mv = {1, 8, 0})
        /* renamed from: com.uxcam.screenaction.compose.CompositionContextsKt$REFLECTION_CONSTANTS$2$1, reason: invalid class name */
        public final class AnonymousClass1 {

            /* renamed from: a, reason: collision with root package name */
            public final Class<?> f238a;
            public final Class<?> b;
            public final Field c;
            public final Field d;

            public AnonymousClass1() throws NoSuchFieldException, ClassNotFoundException, SecurityException {
                Class<?> cls = Class.forName("androidx.compose.runtime.ComposerImpl$CompositionContextHolder");
                this.f238a = cls;
                Class<?> cls2 = Class.forName("androidx.compose.runtime.ComposerImpl$CompositionContextImpl");
                this.b = cls2;
                Field declaredField = cls.getDeclaredField("ref");
                declaredField.setAccessible(true);
                this.c = declaredField;
                Field declaredField2 = cls2.getDeclaredField("composers");
                declaredField2.setAccessible(true);
                this.d = declaredField2;
            }
        }

        @Override // kotlin.jvm.functions.Function0
        public final AnonymousClass1 invoke() {
            try {
                return new AnonymousClass1();
            } catch (Throwable unused) {
                return null;
            }
        }
    });
}
