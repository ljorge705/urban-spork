package androidx.window.embedding;

import android.app.Activity;
import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ActivityEmbeddingController.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Landroidx/window/embedding/ActivityEmbeddingController;", "", "backend", "Landroidx/window/embedding/EmbeddingBackend;", "(Landroidx/window/embedding/EmbeddingBackend;)V", "isActivityEmbedded", "", "activity", "Landroid/app/Activity;", "Companion", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class ActivityEmbeddingController {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final EmbeddingBackend backend;

    @JvmStatic
    public static final ActivityEmbeddingController getInstance(Context context) {
        return INSTANCE.getInstance(context);
    }

    public ActivityEmbeddingController(EmbeddingBackend backend) {
        Intrinsics.checkNotNullParameter(backend, "backend");
        this.backend = backend;
    }

    public final boolean isActivityEmbedded(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        return this.backend.isActivityEmbedded(activity);
    }

    /* compiled from: ActivityEmbeddingController.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Landroidx/window/embedding/ActivityEmbeddingController$Companion;", "", "()V", "getInstance", "Landroidx/window/embedding/ActivityEmbeddingController;", "context", "Landroid/content/Context;", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final ActivityEmbeddingController getInstance(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return new ActivityEmbeddingController(EmbeddingBackend.INSTANCE.getInstance(context));
        }
    }
}
