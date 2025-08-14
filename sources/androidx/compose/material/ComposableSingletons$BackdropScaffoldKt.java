package androidx.compose.material;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BackdropScaffold.kt */
@Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class ComposableSingletons$BackdropScaffoldKt {
    public static final ComposableSingletons$BackdropScaffoldKt INSTANCE = new ComposableSingletons$BackdropScaffoldKt();

    /* renamed from: lambda-1, reason: not valid java name */
    public static Function3<SnackbarHostState, Composer, Integer, Unit> f7lambda1 = ComposableLambdaKt.composableLambdaInstance(-985546645, false, new Function3<SnackbarHostState, Composer, Integer, Unit>() { // from class: androidx.compose.material.ComposableSingletons$BackdropScaffoldKt$lambda-1$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(SnackbarHostState snackbarHostState, Composer composer, Integer num) {
            invoke(snackbarHostState, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(SnackbarHostState it, Composer composer, int i) {
            Intrinsics.checkNotNullParameter(it, "it");
            ComposerKt.sourceInformation(composer, "C272@12586L16:BackdropScaffold.kt#jmzs0o");
            if ((i & 14) == 0) {
                i |= composer.changed(it) ? 4 : 2;
            }
            if (((i & 91) ^ 18) == 0 && composer.getSkipping()) {
                composer.skipToGroupEnd();
            } else {
                SnackbarHostKt.SnackbarHost(it, null, null, composer, i & 14, 6);
            }
        }
    });

    /* renamed from: getLambda-1$material_release, reason: not valid java name */
    public final Function3<SnackbarHostState, Composer, Integer, Unit> m1266getLambda1$material_release() {
        return f7lambda1;
    }
}
