package androidx.compose.runtime;

import androidx.compose.runtime.collection.IdentityArrayIntMap;
import androidx.compose.runtime.collection.IdentityArrayMap;
import androidx.compose.runtime.collection.IdentityArraySet;
import com.facebook.react.uimanager.ViewProps;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RecomposeScopeImpl.kt */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0005J\u000e\u00107\u001a\u00020\u00102\u0006\u0010\u0003\u001a\u00020\u0004J\u000e\u00108\u001a\u00020\u00102\u0006\u00109\u001a\u00020\u000eJ\u001c\u0010:\u001a\u0010\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u00020\u0010\u0018\u00010;2\u0006\u0010=\u001a\u00020\u000fJ\b\u0010>\u001a\u00020\u0010H\u0016J\u0010\u0010?\u001a\u00020@2\b\u0010\u0019\u001a\u0004\u0018\u00010/J\u0016\u0010A\u001a\u00020\u00122\u000e\u0010B\u001a\n\u0012\u0004\u0012\u00020/\u0018\u00010CJ\u000e\u0010D\u001a\u00020\u00102\u0006\u0010E\u001a\u00020/J\u0006\u0010F\u001a\u00020\u0010J\u0006\u0010G\u001a\u00020\u0010J\u0006\u0010H\u001a\u00020\u0010J\u000e\u0010I\u001a\u00020\u00102\u0006\u0010=\u001a\u00020\u000fJ\"\u0010J\u001a\u00020\u00102\u0018\u0010\f\u001a\u0014\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\rH\u0016R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\"\u0010\f\u001a\u0016\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u0010\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0011\u001a\u00020\u00128F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\"\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0015\u001a\u0004\u0018\u00010\u0004@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0018\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u00128F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u001b\u0010\u0014\"\u0004\b\u001c\u0010\u001dR$\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u00128F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u001f\u0010\u0014\"\u0004\b \u0010\u001dR\u000e\u0010!\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\"\u001a\u00020\u00128F¢\u0006\u0006\u001a\u0004\b\"\u0010\u0014R$\u0010#\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u00128F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b$\u0010\u0014\"\u0004\b%\u0010\u001dR$\u0010&\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u00128B@BX\u0082\u000e¢\u0006\f\u001a\u0004\b'\u0010\u0014\"\u0004\b(\u0010\u001dR$\u0010)\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u00128@@BX\u0080\u000e¢\u0006\f\u001a\u0004\b*\u0010\u0014\"\u0004\b+\u0010\u001dR\"\u0010,\u001a\u0016\u0012\b\u0012\u0006\u0012\u0002\b\u00030.\u0012\u0006\u0012\u0004\u0018\u00010/\u0018\u00010-X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00100\u001a\u0004\u0018\u000101X\u0082\u000e¢\u0006\u0002\n\u0000R$\u00102\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u00128F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b3\u0010\u0014\"\u0004\b4\u0010\u001dR\u0011\u00105\u001a\u00020\u00128F¢\u0006\u0006\u001a\u0004\b6\u0010\u0014¨\u0006K"}, d2 = {"Landroidx/compose/runtime/RecomposeScopeImpl;", "Landroidx/compose/runtime/ScopeUpdateScope;", "Landroidx/compose/runtime/RecomposeScope;", "composition", "Landroidx/compose/runtime/CompositionImpl;", "(Landroidx/compose/runtime/CompositionImpl;)V", "anchor", "Landroidx/compose/runtime/Anchor;", "getAnchor", "()Landroidx/compose/runtime/Anchor;", "setAnchor", "(Landroidx/compose/runtime/Anchor;)V", "block", "Lkotlin/Function2;", "Landroidx/compose/runtime/Composer;", "", "", "canRecompose", "", "getCanRecompose", "()Z", "<set-?>", "getComposition", "()Landroidx/compose/runtime/CompositionImpl;", "currentToken", "value", "defaultsInScope", "getDefaultsInScope", "setDefaultsInScope", "(Z)V", "defaultsInvalid", "getDefaultsInvalid", "setDefaultsInvalid", "flags", "isConditional", "requiresRecompose", "getRequiresRecompose", "setRequiresRecompose", "rereading", "getRereading", "setRereading", "skipped", "getSkipped$runtime_release", "setSkipped", "trackedDependencies", "Landroidx/compose/runtime/collection/IdentityArrayMap;", "Landroidx/compose/runtime/DerivedState;", "", "trackedInstances", "Landroidx/compose/runtime/collection/IdentityArrayIntMap;", "used", "getUsed", "setUsed", "valid", "getValid", "adoptedBy", "compose", "composer", ViewProps.END, "Lkotlin/Function1;", "Landroidx/compose/runtime/Composition;", "token", "invalidate", "invalidateForResult", "Landroidx/compose/runtime/InvalidationResult;", "isInvalidFor", "instances", "Landroidx/compose/runtime/collection/IdentityArraySet;", "recordRead", "instance", "release", "rereadTrackedInstances", "scopeSkipped", ViewProps.START, "updateScope", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class RecomposeScopeImpl implements ScopeUpdateScope, RecomposeScope {
    private Anchor anchor;
    private Function2<? super Composer, ? super Integer, Unit> block;
    private CompositionImpl composition;
    private int currentToken;
    private int flags;
    private IdentityArrayMap<DerivedState<?>, Object> trackedDependencies;
    private IdentityArrayIntMap trackedInstances;

    private final boolean getRereading() {
        return (this.flags & 32) != 0;
    }

    private final void setRereading(boolean z) {
        if (z) {
            this.flags |= 32;
        } else {
            this.flags &= -33;
        }
    }

    private final void setSkipped(boolean z) {
        if (z) {
            this.flags |= 16;
        } else {
            this.flags &= -17;
        }
    }

    public final void adoptedBy(CompositionImpl composition) {
        Intrinsics.checkNotNullParameter(composition, "composition");
        this.composition = composition;
    }

    public final Anchor getAnchor() {
        return this.anchor;
    }

    public final boolean getCanRecompose() {
        return this.block != null;
    }

    public final CompositionImpl getComposition() {
        return this.composition;
    }

    public final boolean getDefaultsInScope() {
        return (this.flags & 2) != 0;
    }

    public final boolean getDefaultsInvalid() {
        return (this.flags & 4) != 0;
    }

    public final boolean getRequiresRecompose() {
        return (this.flags & 8) != 0;
    }

    public final boolean getSkipped$runtime_release() {
        return (this.flags & 16) != 0;
    }

    public final boolean getUsed() {
        return (this.flags & 1) != 0;
    }

    public final boolean isConditional() {
        return this.trackedDependencies != null;
    }

    public final void release() {
        this.composition = null;
        this.trackedInstances = null;
        this.trackedDependencies = null;
    }

    public final void setAnchor(Anchor anchor) {
        this.anchor = anchor;
    }

    public final void setDefaultsInScope(boolean z) {
        if (z) {
            this.flags |= 2;
        } else {
            this.flags &= -3;
        }
    }

    public final void setDefaultsInvalid(boolean z) {
        if (z) {
            this.flags |= 4;
        } else {
            this.flags &= -5;
        }
    }

    public final void setRequiresRecompose(boolean z) {
        if (z) {
            this.flags |= 8;
        } else {
            this.flags &= -9;
        }
    }

    public final void setUsed(boolean z) {
        if (z) {
            this.flags |= 1;
        } else {
            this.flags &= -2;
        }
    }

    @Override // androidx.compose.runtime.ScopeUpdateScope
    public void updateScope(Function2<? super Composer, ? super Integer, Unit> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        this.block = block;
    }

    public RecomposeScopeImpl(CompositionImpl compositionImpl) {
        this.composition = compositionImpl;
    }

    public final boolean getValid() {
        Anchor anchor;
        return (this.composition == null || (anchor = this.anchor) == null || !anchor.getValid()) ? false : true;
    }

    public final void compose(Composer composer) {
        Unit unit;
        Intrinsics.checkNotNullParameter(composer, "composer");
        Function2<? super Composer, ? super Integer, Unit> function2 = this.block;
        if (function2 != null) {
            function2.invoke(composer, 1);
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        if (unit == null) {
            throw new IllegalStateException("Invalid restart scope".toString());
        }
    }

    public final InvalidationResult invalidateForResult(Object value) {
        InvalidationResult invalidationResultInvalidate;
        CompositionImpl compositionImpl = this.composition;
        return (compositionImpl == null || (invalidationResultInvalidate = compositionImpl.invalidate(this, value)) == null) ? InvalidationResult.IGNORED : invalidationResultInvalidate;
    }

    @Override // androidx.compose.runtime.RecomposeScope
    public void invalidate() {
        CompositionImpl compositionImpl = this.composition;
        if (compositionImpl != null) {
            compositionImpl.invalidate(this, null);
        }
    }

    public final void start(int token) {
        this.currentToken = token;
        setSkipped(false);
    }

    public final void scopeSkipped() {
        setSkipped(true);
    }

    public final void recordRead(Object instance) {
        Intrinsics.checkNotNullParameter(instance, "instance");
        if (getRereading()) {
            return;
        }
        IdentityArrayIntMap identityArrayIntMap = this.trackedInstances;
        if (identityArrayIntMap == null) {
            identityArrayIntMap = new IdentityArrayIntMap();
            this.trackedInstances = identityArrayIntMap;
        }
        identityArrayIntMap.add(instance, this.currentToken);
        if (instance instanceof DerivedState) {
            IdentityArrayMap<DerivedState<?>, Object> identityArrayMap = this.trackedDependencies;
            if (identityArrayMap == null) {
                identityArrayMap = new IdentityArrayMap<>(0, 1, null);
                this.trackedDependencies = identityArrayMap;
            }
            identityArrayMap.set(instance, ((DerivedState) instance).getCurrentValue());
        }
    }

    public final boolean isInvalidFor(IdentityArraySet<Object> instances) {
        IdentityArrayMap<DerivedState<?>, Object> identityArrayMap;
        if (instances != null && (identityArrayMap = this.trackedDependencies) != null && instances.isNotEmpty()) {
            IdentityArraySet<Object> identityArraySet = instances;
            if ((identityArraySet instanceof Collection) && identityArraySet.isEmpty()) {
                return false;
            }
            for (Object obj : identityArraySet) {
                if (obj instanceof DerivedState) {
                    DerivedState<?> derivedState = (DerivedState) obj;
                    SnapshotMutationPolicy<?> policy = derivedState.getPolicy();
                    if (policy == null) {
                        policy = SnapshotStateKt.structuralEqualityPolicy();
                    }
                    if (policy.equivalent(derivedState.getCurrentValue(), identityArrayMap.get(derivedState))) {
                    }
                }
            }
            return false;
        }
        return true;
    }

    public final void rereadTrackedInstances() {
        IdentityArrayIntMap identityArrayIntMap;
        CompositionImpl compositionImpl = this.composition;
        if (compositionImpl == null || (identityArrayIntMap = this.trackedInstances) == null) {
            return;
        }
        setRereading(true);
        try {
            int size = identityArrayIntMap.getSize();
            for (int i = 0; i < size; i++) {
                Object obj = identityArrayIntMap.getKeys()[i];
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Any");
                int i2 = identityArrayIntMap.getValues()[i];
                compositionImpl.recordReadOf(obj);
            }
        } finally {
            setRereading(false);
        }
    }

    public final Function1<Composition, Unit> end(final int token) {
        final IdentityArrayIntMap identityArrayIntMap = this.trackedInstances;
        if (identityArrayIntMap == null || getSkipped$runtime_release()) {
            return null;
        }
        int size = identityArrayIntMap.getSize();
        for (int i = 0; i < size; i++) {
            Intrinsics.checkNotNull(identityArrayIntMap.getKeys()[i], "null cannot be cast to non-null type kotlin.Any");
            if (identityArrayIntMap.getValues()[i] != token) {
                return new Function1<Composition, Unit>() { // from class: androidx.compose.runtime.RecomposeScopeImpl$end$1$2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Composition composition) {
                        invoke2(composition);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Composition composition) {
                        Intrinsics.checkNotNullParameter(composition, "composition");
                        if (this.this$0.currentToken == token && Intrinsics.areEqual(identityArrayIntMap, this.this$0.trackedInstances) && (composition instanceof CompositionImpl)) {
                            IdentityArrayIntMap identityArrayIntMap2 = identityArrayIntMap;
                            int i2 = token;
                            RecomposeScopeImpl recomposeScopeImpl = this.this$0;
                            int size2 = identityArrayIntMap2.getSize();
                            int i3 = 0;
                            for (int i4 = 0; i4 < size2; i4++) {
                                Object obj = identityArrayIntMap2.getKeys()[i4];
                                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Any");
                                int i5 = identityArrayIntMap2.getValues()[i4];
                                boolean z = i5 != i2;
                                if (z) {
                                    CompositionImpl compositionImpl = (CompositionImpl) composition;
                                    compositionImpl.removeObservation$runtime_release(obj, recomposeScopeImpl);
                                    DerivedState<?> derivedState = obj instanceof DerivedState ? (DerivedState) obj : null;
                                    if (derivedState != null) {
                                        compositionImpl.removeDerivedStateObservation$runtime_release(derivedState);
                                        IdentityArrayMap identityArrayMap = recomposeScopeImpl.trackedDependencies;
                                        if (identityArrayMap != null) {
                                            identityArrayMap.remove(derivedState);
                                            if (identityArrayMap.getSize() == 0) {
                                                recomposeScopeImpl.trackedDependencies = null;
                                            }
                                        }
                                    }
                                }
                                if (!z) {
                                    if (i3 != i4) {
                                        identityArrayIntMap2.getKeys()[i3] = obj;
                                        identityArrayIntMap2.getValues()[i3] = i5;
                                    }
                                    i3++;
                                }
                            }
                            int size3 = identityArrayIntMap2.getSize();
                            for (int i6 = i3; i6 < size3; i6++) {
                                identityArrayIntMap2.getKeys()[i6] = null;
                            }
                            identityArrayIntMap2.setSize(i3);
                            if (identityArrayIntMap.getSize() == 0) {
                                this.this$0.trackedInstances = null;
                            }
                        }
                    }
                };
            }
        }
        return null;
    }
}
