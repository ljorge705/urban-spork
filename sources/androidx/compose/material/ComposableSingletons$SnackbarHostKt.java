package androidx.compose.material;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SnackbarHost.kt */
@Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class ComposableSingletons$SnackbarHostKt {
    public static final ComposableSingletons$SnackbarHostKt INSTANCE = new ComposableSingletons$SnackbarHostKt();

    /* renamed from: lambda-1, reason: not valid java name */
    public static Function3<SnackbarData, Composer, Integer, Unit> f13lambda1 = ComposableLambdaKt.composableLambdaInstance(-985536016, false, new Function3<SnackbarData, Composer, Integer, Unit>() { // from class: androidx.compose.material.ComposableSingletons$SnackbarHostKt$lambda-1$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(SnackbarData snackbarData, Composer composer, Integer num) {
            invoke(snackbarData, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(SnackbarData it, Composer composer, int i) {
            int i2;
            Intrinsics.checkNotNullParameter(it, "it");
            ComposerKt.sourceInformation(composer, "C153@6325L12:SnackbarHost.kt#jmzs0o");
            if ((i & 14) == 0) {
                i2 = i | (composer.changed(it) ? 4 : 2);
            } else {
                i2 = i;
            }
            if (((i2 & 91) ^ 18) == 0 && composer.getSkipping()) {
                composer.skipToGroupEnd();
            } else {
                SnackbarKt.m1404SnackbarsPrSdHI(it, null, false, null, 0L, 0L, 0L, 0.0f, composer, i2 & 14, 254);
            }
        }
    });

    /* renamed from: getLambda-1$material_release, reason: not valid java name */
    public final Function3<SnackbarData, Composer, Integer, Unit> m1272getLambda1$material_release() {
        return f13lambda1;
    }
}
