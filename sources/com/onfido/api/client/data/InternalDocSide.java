package com.onfido.api.client.data;

import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.EnumDescriptor;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.StringSerializer;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: InternalDocSide.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0087\u0081\u0002\u0018\u0000 \n2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0002\t\nB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\u000b"}, d2 = {"Lcom/onfido/api/client/data/InternalDocSide;", "", "id", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getId", "()Ljava/lang/String;", "FRONT", "BACK", "$serializer", "Companion", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Serializable
/* loaded from: classes6.dex */
public final class InternalDocSide {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ InternalDocSide[] $VALUES;
    private static final Lazy<KSerializer<Object>> $cachedSerializer$delegate;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE;
    private final String id;

    @SerialName("front")
    public static final InternalDocSide FRONT = new InternalDocSide("FRONT", 0, "front");

    @SerialName("back")
    public static final InternalDocSide BACK = new InternalDocSide("BACK", 1, "back");

    private static final /* synthetic */ InternalDocSide[] $values() {
        return new InternalDocSide[]{FRONT, BACK};
    }

    public static EnumEntries<InternalDocSide> getEntries() {
        return $ENTRIES;
    }

    public static InternalDocSide valueOf(String str) {
        return (InternalDocSide) Enum.valueOf(InternalDocSide.class, str);
    }

    public static InternalDocSide[] values() {
        return (InternalDocSide[]) $VALUES.clone();
    }

    public final String getId() {
        return this.id;
    }

    /* compiled from: InternalDocSide.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/onfido/api/client/data/InternalDocSide$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/api/client/data/InternalDocSide;", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        private final /* synthetic */ KSerializer get$cachedSerializer() {
            return (KSerializer) InternalDocSide.$cachedSerializer$delegate.getValue();
        }

        public final KSerializer<InternalDocSide> serializer() {
            return get$cachedSerializer();
        }
    }

    private InternalDocSide(String str, int i, String str2) {
        this.id = str2;
    }

    static {
        InternalDocSide[] internalDocSideArr$values = $values();
        $VALUES = internalDocSideArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(internalDocSideArr$values);
        INSTANCE = new Companion(null);
        $cachedSerializer$delegate = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0) new Function0<KSerializer<Object>>() { // from class: com.onfido.api.client.data.InternalDocSide.Companion.1
            @Override // kotlin.jvm.functions.Function0
            public final KSerializer<Object> invoke() {
                return new GeneratedSerializer<InternalDocSide>() { // from class: com.onfido.api.client.data.InternalDocSide$$serializer
                    private static final /* synthetic */ EnumDescriptor descriptor;

                    static {
                        EnumDescriptor enumDescriptor = new EnumDescriptor("com.onfido.api.client.data.InternalDocSide", 2);
                        enumDescriptor.addElement("front", false);
                        enumDescriptor.addElement("back", false);
                        descriptor = enumDescriptor;
                    }

                    @Override // kotlinx.serialization.internal.GeneratedSerializer
                    public KSerializer<?>[] childSerializers() {
                        return new KSerializer[]{StringSerializer.INSTANCE};
                    }

                    @Override // kotlinx.serialization.DeserializationStrategy
                    public InternalDocSide deserialize(Decoder decoder) {
                        Intrinsics.checkNotNullParameter(decoder, "decoder");
                        return InternalDocSide.values()[decoder.decodeEnum(getDescriptor())];
                    }

                    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
                    public SerialDescriptor getDescriptor() {
                        return descriptor;
                    }

                    @Override // kotlinx.serialization.SerializationStrategy
                    public void serialize(Encoder encoder, InternalDocSide value) {
                        Intrinsics.checkNotNullParameter(encoder, "encoder");
                        Intrinsics.checkNotNullParameter(value, "value");
                        encoder.encodeEnum(getDescriptor(), value.ordinal());
                    }

                    @Override // kotlinx.serialization.internal.GeneratedSerializer
                    public KSerializer<?>[] typeParametersSerializers() {
                        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
                    }
                };
            }
        });
    }
}
