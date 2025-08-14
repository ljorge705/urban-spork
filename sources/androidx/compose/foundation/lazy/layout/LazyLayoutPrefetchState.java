package androidx.compose.foundation.lazy.layout;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import com.oblador.keychain.KeychainModule;
import kotlin.Metadata;
import org.bouncycastle.crypto.CryptoServicesPermission;

/* compiled from: LazyLayoutPrefetchState.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001:\u0002\u0014\u0015B\u0005¢\u0006\u0002\u0010\u0002J#\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0012\u0010\u0013R/\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u00048@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\b\n\u0010\u000b\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006\u0016"}, d2 = {"Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState;", "", "()V", "<set-?>", "Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState$Prefetcher;", "prefetcher", "getPrefetcher$foundation_release", "()Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState$Prefetcher;", "setPrefetcher$foundation_release", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState$Prefetcher;)V", "prefetcher$delegate", "Landroidx/compose/runtime/MutableState;", "schedulePrefetch", "Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState$PrefetchHandle;", "index", "", CryptoServicesPermission.CONSTRAINTS, "Landroidx/compose/ui/unit/Constraints;", "schedulePrefetch-0kLqBqw", "(IJ)Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState$PrefetchHandle;", "PrefetchHandle", "Prefetcher", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class LazyLayoutPrefetchState {
    public static final int $stable = 0;

    /* renamed from: prefetcher$delegate, reason: from kotlin metadata */
    private final MutableState prefetcher = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);

    /* compiled from: LazyLayoutPrefetchState.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&\u0082\u0001\u0002\u0004\u0005ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0006À\u0006\u0001"}, d2 = {"Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState$PrefetchHandle;", "", KeychainModule.AuthPromptOptions.CANCEL, "", "Landroidx/compose/foundation/lazy/layout/DummyHandle;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetcher$PrefetchRequest;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public interface PrefetchHandle {
        void cancel();
    }

    /* compiled from: LazyLayoutPrefetchState.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b`\u0018\u00002\u00020\u0001J%\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\b\u0010\tø\u0001\u0002\u0082\u0002\u0011\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019\n\u0004\b!0\u0001¨\u0006\nÀ\u0006\u0001"}, d2 = {"Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState$Prefetcher;", "", "schedulePrefetch", "Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState$PrefetchHandle;", "index", "", CryptoServicesPermission.CONSTRAINTS, "Landroidx/compose/ui/unit/Constraints;", "schedulePrefetch-0kLqBqw", "(IJ)Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState$PrefetchHandle;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public interface Prefetcher {
        /* renamed from: schedulePrefetch-0kLqBqw, reason: not valid java name */
        PrefetchHandle mo895schedulePrefetch0kLqBqw(int index, long constraints);
    }

    public final Prefetcher getPrefetcher$foundation_release() {
        return (Prefetcher) this.prefetcher.getValue();
    }

    /* renamed from: schedulePrefetch-0kLqBqw, reason: not valid java name */
    public final PrefetchHandle m894schedulePrefetch0kLqBqw(int index, long constraints) {
        PrefetchHandle prefetchHandleMo895schedulePrefetch0kLqBqw;
        Prefetcher prefetcher$foundation_release = getPrefetcher$foundation_release();
        return (prefetcher$foundation_release == null || (prefetchHandleMo895schedulePrefetch0kLqBqw = prefetcher$foundation_release.mo895schedulePrefetch0kLqBqw(index, constraints)) == null) ? DummyHandle.INSTANCE : prefetchHandleMo895schedulePrefetch0kLqBqw;
    }

    public final void setPrefetcher$foundation_release(Prefetcher prefetcher) {
        this.prefetcher.setValue(prefetcher);
    }
}
