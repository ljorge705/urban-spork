package kotlinx.serialization.modules;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.ExperimentalSerializationApi;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.modules.ContextualProvider;

/* compiled from: SerializersModule.kt */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0015\u0010\u0006\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u0001H\u0086\u0004\u001a\u0015\u0010\b\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u0001H\u0086\u0002\"\u001c\u0010\u0000\u001a\u00020\u00018\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0002\u0010\u0003\u001a\u0004\b\u0004\u0010\u0005*`\b\u0000\u0010\t\u001a\u0004\b\u0000\u0010\n\")\u0012\u0015\u0012\u0013\u0018\u00010\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u000e\u0012\f\u0012\u0006\b\u0001\u0012\u0002H\n\u0018\u00010\u00100\u000b2)\u0012\u0015\u0012\u0013\u0018\u00010\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u000e\u0012\f\u0012\u0006\b\u0001\u0012\u0002H\n\u0018\u00010\u00100\u000b¨\u0006\u0011"}, d2 = {"EmptySerializersModule", "Lkotlinx/serialization/modules/SerializersModule;", "getEmptySerializersModule$annotations", "()V", "getEmptySerializersModule", "()Lkotlinx/serialization/modules/SerializersModule;", "overwriteWith", "other", "plus", "PolymorphicProvider", "Base", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "className", "Lkotlinx/serialization/DeserializationStrategy;", "kotlinx-serialization-core"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public final class SerializersModuleKt {
    private static final SerializersModule EmptySerializersModule = new SerialModuleImpl(MapsKt.emptyMap(), MapsKt.emptyMap(), MapsKt.emptyMap(), MapsKt.emptyMap());

    public static final SerializersModule getEmptySerializersModule() {
        return EmptySerializersModule;
    }

    @ExperimentalSerializationApi
    public static /* synthetic */ void getEmptySerializersModule$annotations() {
    }

    public static final SerializersModule plus(SerializersModule serializersModule, SerializersModule other) {
        Intrinsics.checkNotNullParameter(serializersModule, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        SerializersModuleBuilder serializersModuleBuilder = new SerializersModuleBuilder();
        serializersModuleBuilder.include(serializersModule);
        serializersModuleBuilder.include(other);
        return serializersModuleBuilder.build();
    }

    public static final SerializersModule overwriteWith(SerializersModule serializersModule, SerializersModule other) {
        Intrinsics.checkNotNullParameter(serializersModule, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        final SerializersModuleBuilder serializersModuleBuilder = new SerializersModuleBuilder();
        serializersModuleBuilder.include(serializersModule);
        other.dumpTo(new SerializersModuleCollector() { // from class: kotlinx.serialization.modules.SerializersModuleKt$overwriteWith$1$1
            @Override // kotlinx.serialization.modules.SerializersModuleCollector
            public <T> void contextual(KClass<T> kClass, KSerializer<T> serializer) {
                Intrinsics.checkNotNullParameter(kClass, "kClass");
                Intrinsics.checkNotNullParameter(serializer, "serializer");
                serializersModuleBuilder.registerSerializer(kClass, new ContextualProvider.Argless(serializer), true);
            }

            @Override // kotlinx.serialization.modules.SerializersModuleCollector
            public <T> void contextual(KClass<T> kClass, Function1<? super List<? extends KSerializer<?>>, ? extends KSerializer<?>> provider) {
                Intrinsics.checkNotNullParameter(kClass, "kClass");
                Intrinsics.checkNotNullParameter(provider, "provider");
                serializersModuleBuilder.registerSerializer(kClass, new ContextualProvider.WithTypeArguments(provider), true);
            }

            @Override // kotlinx.serialization.modules.SerializersModuleCollector
            public <Base, Sub extends Base> void polymorphic(KClass<Base> baseClass, KClass<Sub> actualClass, KSerializer<Sub> actualSerializer) {
                Intrinsics.checkNotNullParameter(baseClass, "baseClass");
                Intrinsics.checkNotNullParameter(actualClass, "actualClass");
                Intrinsics.checkNotNullParameter(actualSerializer, "actualSerializer");
                serializersModuleBuilder.registerPolymorphicSerializer(baseClass, actualClass, actualSerializer, true);
            }

            @Override // kotlinx.serialization.modules.SerializersModuleCollector
            public <Base> void polymorphicDefault(KClass<Base> baseClass, Function1<? super String, ? extends DeserializationStrategy<? extends Base>> defaultSerializerProvider) {
                Intrinsics.checkNotNullParameter(baseClass, "baseClass");
                Intrinsics.checkNotNullParameter(defaultSerializerProvider, "defaultSerializerProvider");
                serializersModuleBuilder.registerDefaultPolymorphicSerializer(baseClass, defaultSerializerProvider, true);
            }
        });
        return serializersModuleBuilder.build();
    }
}
