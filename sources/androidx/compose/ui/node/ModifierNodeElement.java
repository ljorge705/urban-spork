package androidx.compose.ui.node;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.Modifier.Node;
import androidx.compose.ui.platform.InspectableValue;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.platform.ValueElement;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.KotlinReflectionNotSupportedError;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KCallable;
import kotlin.reflect.KProperty1;
import kotlin.reflect.jvm.KCallablesJvm;
import kotlin.sequences.Sequence;

/* compiled from: ModifierNodeElement.kt */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\b'\u0018\u0000 '*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u00032\u00020\u0004:\u0001'B\u0005¢\u0006\u0002\u0010\u0005J\r\u0010\u001c\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u001dJ\u0013\u0010\u001e\u001a\u00020\t2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0019H¦\u0002J\b\u0010 \u001a\u00020!H&J\u0015\u0010\"\u001a\u00028\u00002\u0006\u0010#\u001a\u00028\u0000H&¢\u0006\u0002\u0010$J\f\u0010%\u001a\u00020&*\u00020\u0007H\u0016R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\u00078BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u00158F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0013\u0010\u0018\u001a\u0004\u0018\u00010\u00198F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001b¨\u0006("}, d2 = {"Landroidx/compose/ui/node/ModifierNodeElement;", "N", "Landroidx/compose/ui/Modifier$Node;", "Landroidx/compose/ui/Modifier$Element;", "Landroidx/compose/ui/platform/InspectableValue;", "()V", "_inspectorValues", "Landroidx/compose/ui/platform/InspectorInfo;", "autoInvalidate", "", "getAutoInvalidate", "()Z", "inspectableElements", "Lkotlin/sequences/Sequence;", "Landroidx/compose/ui/platform/ValueElement;", "getInspectableElements", "()Lkotlin/sequences/Sequence;", "inspectorValues", "getInspectorValues", "()Landroidx/compose/ui/platform/InspectorInfo;", "nameFallback", "", "getNameFallback", "()Ljava/lang/String;", "valueOverride", "", "getValueOverride", "()Ljava/lang/Object;", "create", "()Landroidx/compose/ui/Modifier$Node;", "equals", "other", "hashCode", "", "update", "node", "(Landroidx/compose/ui/Modifier$Node;)Landroidx/compose/ui/Modifier$Node;", "inspectableProperties", "", "Companion", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class ModifierNodeElement<N extends Modifier.Node> implements Modifier.Element, InspectableValue {
    public static final int $stable = 0;
    private static final Companion Companion = new Companion(null);

    @Deprecated
    private static final Lazy<Set<String>> builtInProperties$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<Set<? extends String>>() { // from class: androidx.compose.ui.node.ModifierNodeElement$Companion$builtInProperties$2
        @Override // kotlin.jvm.functions.Function0
        public final Set<? extends String> invoke() {
            try {
                Set setCreateSetBuilder = SetsKt.createSetBuilder();
                Iterator<T> it = Reflection.getOrCreateKotlinClass(ModifierNodeElement.class).getMembers().iterator();
                while (it.hasNext()) {
                    KCallable kCallable = (KCallable) it.next();
                    if (kCallable instanceof KProperty1) {
                        setCreateSetBuilder.add(kCallable.getName());
                    }
                }
                return SetsKt.build(setCreateSetBuilder);
            } catch (Exception unused) {
                return SetsKt.emptySet();
            } catch (KotlinReflectionNotSupportedError unused2) {
                return SetsKt.emptySet();
            }
        }
    });
    private InspectorInfo _inspectorValues;

    public abstract N create();

    public abstract boolean equals(Object other);

    public boolean getAutoInvalidate() {
        return true;
    }

    public abstract int hashCode();

    public abstract N update(N node);

    private final InspectorInfo getInspectorValues() {
        InspectorInfo inspectorInfo = this._inspectorValues;
        if (inspectorInfo != null) {
            return inspectorInfo;
        }
        InspectorInfo inspectorInfo2 = new InspectorInfo();
        inspectorInfo2.setName(Reflection.getOrCreateKotlinClass(getClass()).getSimpleName());
        inspectableProperties(inspectorInfo2);
        this._inspectorValues = inspectorInfo2;
        return inspectorInfo2;
    }

    @Override // androidx.compose.ui.platform.InspectableValue
    public final String getNameFallback() {
        return getInspectorValues().getName();
    }

    @Override // androidx.compose.ui.platform.InspectableValue
    public final Object getValueOverride() {
        return getInspectorValues().getValue();
    }

    @Override // androidx.compose.ui.platform.InspectableValue
    public final Sequence<ValueElement> getInspectableElements() {
        return getInspectorValues().getProperties();
    }

    public void inspectableProperties(InspectorInfo inspectorInfo) {
        Intrinsics.checkNotNullParameter(inspectorInfo, "<this>");
        try {
            List listSortedWith = CollectionsKt.sortedWith(Reflection.getOrCreateKotlinClass(getClass()).getMembers(), new Comparator() { // from class: androidx.compose.ui.node.ModifierNodeElement$inspectableProperties$$inlined$sortedBy$1
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    return ComparisonsKt.compareValues(((KCallable) t).getName(), ((KCallable) t2).getName());
                }
            });
            int size = listSortedWith.size();
            for (int i = 0; i < size; i++) {
                KCallable kCallable = (KCallable) listSortedWith.get(i);
                if ((kCallable instanceof KProperty1) && !Companion.getBuiltInProperties().contains(kCallable.getName())) {
                    try {
                        KProperty1 kProperty1 = (KProperty1) kCallable;
                        KCallablesJvm.setAccessible(kProperty1, true);
                        inspectorInfo.getProperties().set(kProperty1.getName(), kProperty1.get(this));
                    } catch (Exception unused) {
                    }
                }
            }
        } catch (KotlinReflectionNotSupportedError unused2) {
            inspectorInfo.getProperties().set("inspector error", "Can't automatically resolve properties of " + this + " because Kotlin reflection is unavailable. Consider adding'debugImplementation \"org.jetbrains.kotlin:kotlin-reflect:$kotlin_version\"' to your module's gradle dependencies block.");
        }
    }

    /* compiled from: ModifierNodeElement.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R!\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Landroidx/compose/ui/node/ModifierNodeElement$Companion;", "", "()V", "builtInProperties", "", "", "getBuiltInProperties", "()Ljava/util/Set;", "builtInProperties$delegate", "Lkotlin/Lazy;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Set<String> getBuiltInProperties() {
            return (Set) ModifierNodeElement.builtInProperties$delegate.getValue();
        }
    }
}
