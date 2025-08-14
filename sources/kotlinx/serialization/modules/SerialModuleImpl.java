package kotlinx.serialization.modules;

import androidx.exifinterface.media.ExifInterface;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.reflect.KClass;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.internal.PlatformKt;
import kotlinx.serialization.modules.ContextualProvider;

/* compiled from: SerializersModule.kt */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B´\u0001\u0012\u0016\u0010\u0002\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012*\u0010\u0006\u001a&\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0004\u0012\u0018\u0012\u0016\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0004\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u00030\u0003\u0012&\u0010\b\u001a\"\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0004\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\t\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u00030\u0003\u0012A\u0010\n\u001a=\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0004\u0012/\u0012-\u0012\u0015\u0012\u0013\u0018\u00010\t¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u000f0\u000bj\u0006\u0012\u0002\b\u0003`\u00100\u0003¢\u0006\u0002\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J:\u0010\u0016\u001a\n\u0012\u0004\u0012\u0002H\u0017\u0018\u00010\u0007\"\b\b\u0000\u0010\u0017*\u00020\u00182\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00170\u00042\u0010\u0010\u001a\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u001bH\u0016J7\u0010\u001c\u001a\n\u0012\u0004\u0012\u0002H\u0017\u0018\u00010\u001d\"\b\b\u0000\u0010\u0017*\u00020\u00182\u000e\u0010\u001e\u001a\n\u0012\u0006\b\u0000\u0012\u0002H\u00170\u00042\u0006\u0010\u001f\u001a\u0002H\u0017H\u0016¢\u0006\u0002\u0010 J6\u0010\u001c\u001a\f\u0012\u0006\b\u0001\u0012\u0002H\u0017\u0018\u00010\u000f\"\b\b\u0000\u0010\u0017*\u00020\u00182\u000e\u0010\u001e\u001a\n\u0012\u0006\b\u0000\u0012\u0002H\u00170\u00042\b\u0010!\u001a\u0004\u0018\u00010\tH\u0016R\u001e\u0010\u0002\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0004\u0012\u0004\u0012\u00020\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000RI\u0010\n\u001a=\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0004\u0012/\u0012-\u0012\u0015\u0012\u0013\u0018\u00010\t¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u000f0\u000bj\u0006\u0012\u0002\b\u0003`\u00100\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R.\u0010\b\u001a\"\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0004\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\t\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u00030\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R4\u0010\u0006\u001a&\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0004\u0012\u0018\u0012\u0016\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0004\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u00030\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lkotlinx/serialization/modules/SerialModuleImpl;", "Lkotlinx/serialization/modules/SerializersModule;", "class2ContextualFactory", "", "Lkotlin/reflect/KClass;", "Lkotlinx/serialization/modules/ContextualProvider;", "polyBase2Serializers", "Lkotlinx/serialization/KSerializer;", "polyBase2NamedSerializers", "", "polyBase2DefaultProvider", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "className", "Lkotlinx/serialization/DeserializationStrategy;", "Lkotlinx/serialization/modules/PolymorphicProvider;", "(Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;)V", "dumpTo", "", "collector", "Lkotlinx/serialization/modules/SerializersModuleCollector;", "getContextual", ExifInterface.GPS_DIRECTION_TRUE, "", "kClass", "typeArgumentsSerializers", "", "getPolymorphic", "Lkotlinx/serialization/SerializationStrategy;", "baseClass", "value", "(Lkotlin/reflect/KClass;Ljava/lang/Object;)Lkotlinx/serialization/SerializationStrategy;", "serializedClassName", "kotlinx-serialization-core"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public final class SerialModuleImpl extends SerializersModule {
    private final Map<KClass<?>, ContextualProvider> class2ContextualFactory;
    private final Map<KClass<?>, Function1<String, DeserializationStrategy<?>>> polyBase2DefaultProvider;
    private final Map<KClass<?>, Map<String, KSerializer<?>>> polyBase2NamedSerializers;
    public final Map<KClass<?>, Map<KClass<?>, KSerializer<?>>> polyBase2Serializers;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public SerialModuleImpl(Map<KClass<?>, ? extends ContextualProvider> class2ContextualFactory, Map<KClass<?>, ? extends Map<KClass<?>, ? extends KSerializer<?>>> polyBase2Serializers, Map<KClass<?>, ? extends Map<String, ? extends KSerializer<?>>> polyBase2NamedSerializers, Map<KClass<?>, ? extends Function1<? super String, ? extends DeserializationStrategy<?>>> polyBase2DefaultProvider) {
        super(null);
        Intrinsics.checkNotNullParameter(class2ContextualFactory, "class2ContextualFactory");
        Intrinsics.checkNotNullParameter(polyBase2Serializers, "polyBase2Serializers");
        Intrinsics.checkNotNullParameter(polyBase2NamedSerializers, "polyBase2NamedSerializers");
        Intrinsics.checkNotNullParameter(polyBase2DefaultProvider, "polyBase2DefaultProvider");
        this.class2ContextualFactory = class2ContextualFactory;
        this.polyBase2Serializers = polyBase2Serializers;
        this.polyBase2NamedSerializers = polyBase2NamedSerializers;
        this.polyBase2DefaultProvider = polyBase2DefaultProvider;
    }

    @Override // kotlinx.serialization.modules.SerializersModule
    public <T> SerializationStrategy<T> getPolymorphic(KClass<? super T> baseClass, T value) {
        Intrinsics.checkNotNullParameter(baseClass, "baseClass");
        Intrinsics.checkNotNullParameter(value, "value");
        if (!PlatformKt.isInstanceOf(value, baseClass)) {
            return null;
        }
        Map<KClass<?>, KSerializer<?>> map = this.polyBase2Serializers.get(baseClass);
        KSerializer<?> kSerializer = map == null ? null : map.get(Reflection.getOrCreateKotlinClass(value.getClass()));
        if (kSerializer instanceof SerializationStrategy) {
            return kSerializer;
        }
        return null;
    }

    @Override // kotlinx.serialization.modules.SerializersModule
    public <T> DeserializationStrategy<? extends T> getPolymorphic(KClass<? super T> baseClass, String serializedClassName) {
        Intrinsics.checkNotNullParameter(baseClass, "baseClass");
        Map<String, KSerializer<?>> map = this.polyBase2NamedSerializers.get(baseClass);
        KSerializer<?> kSerializer = map == null ? null : map.get(serializedClassName);
        if (!(kSerializer instanceof KSerializer)) {
            kSerializer = null;
        }
        if (kSerializer != null) {
            return kSerializer;
        }
        Function1<String, DeserializationStrategy<?>> function1 = this.polyBase2DefaultProvider.get(baseClass);
        Function1<String, DeserializationStrategy<?>> function12 = TypeIntrinsics.isFunctionOfArity(function1, 1) ? function1 : null;
        if (function12 == null) {
            return null;
        }
        return (DeserializationStrategy) function12.invoke(serializedClassName);
    }

    @Override // kotlinx.serialization.modules.SerializersModule
    public <T> KSerializer<T> getContextual(KClass<T> kClass, List<? extends KSerializer<?>> typeArgumentsSerializers) {
        Intrinsics.checkNotNullParameter(kClass, "kClass");
        Intrinsics.checkNotNullParameter(typeArgumentsSerializers, "typeArgumentsSerializers");
        ContextualProvider contextualProvider = this.class2ContextualFactory.get(kClass);
        KSerializer<?> kSerializerInvoke = contextualProvider == null ? null : contextualProvider.invoke(typeArgumentsSerializers);
        if (kSerializerInvoke instanceof KSerializer) {
            return (KSerializer<T>) kSerializerInvoke;
        }
        return null;
    }

    @Override // kotlinx.serialization.modules.SerializersModule
    public void dumpTo(SerializersModuleCollector collector) {
        Intrinsics.checkNotNullParameter(collector, "collector");
        for (Map.Entry<KClass<?>, ContextualProvider> entry : this.class2ContextualFactory.entrySet()) {
            KClass<?> key = entry.getKey();
            ContextualProvider value = entry.getValue();
            if (value instanceof ContextualProvider.Argless) {
                collector.contextual(key, ((ContextualProvider.Argless) value).getSerializer());
            } else if (value instanceof ContextualProvider.WithTypeArguments) {
                collector.contextual(key, ((ContextualProvider.WithTypeArguments) value).getProvider());
            }
        }
        for (Map.Entry<KClass<?>, Map<KClass<?>, KSerializer<?>>> entry2 : this.polyBase2Serializers.entrySet()) {
            KClass<?> key2 = entry2.getKey();
            for (Map.Entry<KClass<?>, KSerializer<?>> entry3 : entry2.getValue().entrySet()) {
                collector.polymorphic(key2, entry3.getKey(), entry3.getValue());
            }
        }
        for (Map.Entry<KClass<?>, Function1<String, DeserializationStrategy<?>>> entry4 : this.polyBase2DefaultProvider.entrySet()) {
            collector.polymorphicDefault(entry4.getKey(), entry4.getValue());
        }
    }
}
