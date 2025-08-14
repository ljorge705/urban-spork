package androidx.compose.ui.modifier;

import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.platform.InspectorValueInfo;
import androidx.exifinterface.media.ExifInterface;
import com.clevertap.android.sdk.Constants;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ModifierLocalProvider.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a.\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"modifierLocalProvider", "Landroidx/compose/ui/Modifier;", ExifInterface.GPS_DIRECTION_TRUE, Constants.KEY_KEY, "Landroidx/compose/ui/modifier/ProvidableModifierLocal;", "value", "Lkotlin/Function0;", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ModifierLocalProviderKt {

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* compiled from: ModifierLocalProvider.kt */
    @Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u00012\u00020\u0002R\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001b\u0010\u0007\u001a\u00028\u00008VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\t¨\u0006\f"}, d2 = {"androidx/compose/ui/modifier/ModifierLocalProviderKt$modifierLocalProvider$1", "Landroidx/compose/ui/modifier/ModifierLocalProvider;", "Landroidx/compose/ui/platform/InspectorValueInfo;", Constants.KEY_KEY, "Landroidx/compose/ui/modifier/ProvidableModifierLocal;", "getKey", "()Landroidx/compose/ui/modifier/ProvidableModifierLocal;", "value", "getValue", "()Ljava/lang/Object;", "value$delegate", "Landroidx/compose/runtime/State;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* renamed from: androidx.compose.ui.modifier.ModifierLocalProviderKt$modifierLocalProvider$1, reason: invalid class name */
    public static final class AnonymousClass1<T> extends InspectorValueInfo implements ModifierLocalProvider<T> {
        private final ProvidableModifierLocal<T> key;

        /* renamed from: value$delegate, reason: from kotlin metadata */
        private final State value;

        @Override // androidx.compose.ui.modifier.ModifierLocalProvider
        public ProvidableModifierLocal<T> getKey() {
            return this.key;
        }

        AnonymousClass1(ProvidableModifierLocal<T> providableModifierLocal, Function0<? extends T> function0, Function1<? super InspectorInfo, Unit> function1) {
            super(function1);
            this.key = providableModifierLocal;
            this.value = SnapshotStateKt.derivedStateOf(function0);
        }

        @Override // androidx.compose.ui.modifier.ModifierLocalProvider
        public T getValue() {
            return (T) this.value.getValue();
        }
    }

    public static final <T> Modifier modifierLocalProvider(Modifier modifier, final ProvidableModifierLocal<T> key, final Function0<? extends T> value) {
        Intrinsics.checkNotNullParameter(modifier, "<this>");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        return modifier.then(new AnonymousClass1(key, value, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.ui.modifier.ModifierLocalProviderKt$modifierLocalProvider$$inlined$debugInspectorInfo$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(InspectorInfo inspectorInfo) {
                invoke2(inspectorInfo);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(InspectorInfo inspectorInfo) {
                Intrinsics.checkNotNullParameter(inspectorInfo, "$this$null");
                inspectorInfo.setName("modifierLocalProvider");
                inspectorInfo.getProperties().set(Constants.KEY_KEY, key);
                inspectorInfo.getProperties().set("value", value);
            }
        } : InspectableValueKt.getNoInspectorInfo()));
    }
}
