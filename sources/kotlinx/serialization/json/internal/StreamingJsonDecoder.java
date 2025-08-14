package kotlinx.serialization.json.internal;

import androidx.exifinterface.media.ExifInterface;
import com.clevertap.android.sdk.Constants;
import com.henninghall.date_picker.props.ModeProp;
import com.onfido.android.sdk.capture.core.OnfidoLauncher;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.encoding.AbstractDecoder;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonConfiguration;
import kotlinx.serialization.json.JsonDecoder;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.modules.SerializersModule;

/* compiled from: StreamingJsonDecoder.kt */
@Metadata(d1 = {"\u0000¨\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0010\u0018\u00002\u00020\u00012\u00020\u0002B%\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u001a\u001a\u00020\u001bH\u0002J\u0018\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u001e\u001a\u00020\u000fH\u0002J\b\u0010\u001f\u001a\u00020\u001dH\u0016J\b\u0010 \u001a\u00020!H\u0016J\b\u0010\"\u001a\u00020#H\u0016J\b\u0010$\u001a\u00020%H\u0016J\u0010\u0010&\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010'\u001a\u00020\u000f2\u0006\u0010(\u001a\u00020\nH\u0016J\b\u0010)\u001a\u00020*H\u0016J\u0010\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\nH\u0016J\b\u0010.\u001a\u00020\u000fH\u0016J\b\u0010/\u001a\u000200H\u0016J\b\u00101\u001a\u00020\u000fH\u0002J\b\u00102\u001a\u000203H\u0016J\b\u00104\u001a\u00020\u000fH\u0002J\b\u00105\u001a\u00020\u001dH\u0016J\n\u00106\u001a\u0004\u0018\u000107H\u0016J\u0010\u00108\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\nH\u0002J!\u00109\u001a\u0002H:\"\u0004\b\u0000\u0010:2\f\u0010;\u001a\b\u0012\u0004\u0012\u0002H:0<H\u0016¢\u0006\u0002\u0010=J\b\u0010>\u001a\u00020?H\u0016J\b\u0010@\u001a\u00020AH\u0016J\b\u0010B\u001a\u00020AH\u0002J\u0010\u0010C\u001a\u00020\u001b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010D\u001a\u00020\u001d2\u0006\u0010E\u001a\u00020AH\u0002J\u0010\u0010F\u001a\u00020\u001b2\u0006\u0010\t\u001a\u00020\nH\u0002R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0010\u0010\u0007\u001a\u00020\b8\u0000X\u0081\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\u00020\u0015X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006G"}, d2 = {"Lkotlinx/serialization/json/internal/StreamingJsonDecoder;", "Lkotlinx/serialization/json/JsonDecoder;", "Lkotlinx/serialization/encoding/AbstractDecoder;", "json", "Lkotlinx/serialization/json/Json;", ModeProp.name, "Lkotlinx/serialization/json/internal/WriteMode;", "lexer", "Lkotlinx/serialization/json/internal/AbstractJsonLexer;", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "(Lkotlinx/serialization/json/Json;Lkotlinx/serialization/json/internal/WriteMode;Lkotlinx/serialization/json/internal/AbstractJsonLexer;Lkotlinx/serialization/descriptors/SerialDescriptor;)V", OnfidoLauncher.KEY_CONFIG, "Lkotlinx/serialization/json/JsonConfiguration;", "currentIndex", "", "elementMarker", "Lkotlinx/serialization/json/internal/JsonElementMarker;", "getJson", "()Lkotlinx/serialization/json/Json;", "serializersModule", "Lkotlinx/serialization/modules/SerializersModule;", "getSerializersModule", "()Lkotlinx/serialization/modules/SerializersModule;", "beginStructure", "Lkotlinx/serialization/encoding/CompositeDecoder;", "checkLeadingComma", "", "coerceInputValue", "", "index", "decodeBoolean", "decodeByte", "", "decodeChar", "", "decodeDouble", "", "decodeElementIndex", "decodeEnum", "enumDescriptor", "decodeFloat", "", "decodeInline", "Lkotlinx/serialization/encoding/Decoder;", "inlineDescriptor", "decodeInt", "decodeJsonElement", "Lkotlinx/serialization/json/JsonElement;", "decodeListIndex", "decodeLong", "", "decodeMapIndex", "decodeNotNullMark", "decodeNull", "", "decodeObjectIndex", "decodeSerializableValue", ExifInterface.GPS_DIRECTION_TRUE, "deserializer", "Lkotlinx/serialization/DeserializationStrategy;", "(Lkotlinx/serialization/DeserializationStrategy;)Ljava/lang/Object;", "decodeShort", "", "decodeString", "", "decodeStringKey", "endStructure", "handleUnknown", Constants.KEY_KEY, "skipLeftoverElements", "kotlinx-serialization-json"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public class StreamingJsonDecoder extends AbstractDecoder implements JsonDecoder {
    private final JsonConfiguration configuration;
    private int currentIndex;
    private final JsonElementMarker elementMarker;
    private final Json json;
    public final AbstractJsonLexer lexer;
    private final WriteMode mode;
    private final SerializersModule serializersModule;

    /* compiled from: StreamingJsonDecoder.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[WriteMode.values().length];
            iArr[WriteMode.LIST.ordinal()] = 1;
            iArr[WriteMode.MAP.ordinal()] = 2;
            iArr[WriteMode.POLY_OBJ.ordinal()] = 3;
            iArr[WriteMode.OBJ.ordinal()] = 4;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public Void decodeNull() {
        return null;
    }

    @Override // kotlinx.serialization.json.JsonDecoder
    public final Json getJson() {
        return this.json;
    }

    @Override // kotlinx.serialization.encoding.Decoder, kotlinx.serialization.encoding.CompositeDecoder
    public SerializersModule getSerializersModule() {
        return this.serializersModule;
    }

    public StreamingJsonDecoder(Json json, WriteMode mode, AbstractJsonLexer lexer, SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(lexer, "lexer");
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        this.json = json;
        this.mode = mode;
        this.lexer = lexer;
        this.serializersModule = json.getSerializersModule();
        this.currentIndex = -1;
        JsonConfiguration configuration = json.getConfiguration();
        this.configuration = configuration;
        this.elementMarker = configuration.getExplicitNulls() ? null : new JsonElementMarker(descriptor);
    }

    @Override // kotlinx.serialization.json.JsonDecoder
    public JsonElement decodeJsonElement() {
        return new JsonTreeReader(this.json.getConfiguration(), this.lexer).read();
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public <T> T decodeSerializableValue(DeserializationStrategy<T> deserializer) {
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        return (T) PolymorphicKt.decodeSerializableValuePolymorphic(this, deserializer);
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public CompositeDecoder beginStructure(SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        WriteMode writeModeSwitchMode = WriteModeKt.switchMode(this.json, descriptor);
        this.lexer.consumeNextToken(writeModeSwitchMode.begin);
        checkLeadingComma();
        int i = WhenMappings.$EnumSwitchMapping$0[writeModeSwitchMode.ordinal()];
        if (i == 1 || i == 2 || i == 3) {
            return new StreamingJsonDecoder(this.json, writeModeSwitchMode, this.lexer, descriptor);
        }
        return (this.mode == writeModeSwitchMode && this.json.getConfiguration().getExplicitNulls()) ? this : new StreamingJsonDecoder(this.json, writeModeSwitchMode, this.lexer, descriptor);
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.CompositeDecoder
    public void endStructure(SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        if (this.json.getConfiguration().getIgnoreUnknownKeys() && descriptor.getElementsCount() == 0) {
            skipLeftoverElements(descriptor);
        }
        this.lexer.consumeNextToken(this.mode.end);
    }

    private final void skipLeftoverElements(SerialDescriptor descriptor) {
        while (decodeElementIndex(descriptor) != -1) {
        }
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public boolean decodeNotNullMark() {
        JsonElementMarker jsonElementMarker = this.elementMarker;
        return (jsonElementMarker == null || !jsonElementMarker.getIsUnmarkedNull()) && this.lexer.tryConsumeNotNull();
    }

    private final void checkLeadingComma() {
        if (this.lexer.peekNextToken() != 4) {
            return;
        }
        AbstractJsonLexer.fail$default(this.lexer, "Unexpected leading comma", 0, 2, null);
        throw new KotlinNothingValueException();
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public int decodeElementIndex(SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        int i = WhenMappings.$EnumSwitchMapping$0[this.mode.ordinal()];
        if (i == 2) {
            return decodeMapIndex();
        }
        if (i == 4) {
            return decodeObjectIndex(descriptor);
        }
        return decodeListIndex();
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0027  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x005d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final int decodeMapIndex() {
        /*
            r6 = this;
            int r0 = r6.currentIndex
            int r1 = r0 % 2
            r2 = 1
            r3 = 0
            if (r1 == 0) goto La
            r1 = r2
            goto Lb
        La:
            r1 = r3
        Lb:
            r4 = -1
            if (r1 == 0) goto L17
            if (r0 == r4) goto L1e
            kotlinx.serialization.json.internal.AbstractJsonLexer r0 = r6.lexer
            boolean r0 = r0.tryConsumeComma()
            goto L1f
        L17:
            kotlinx.serialization.json.internal.AbstractJsonLexer r0 = r6.lexer
            r5 = 58
            r0.consumeNextToken(r5)
        L1e:
            r0 = r3
        L1f:
            kotlinx.serialization.json.internal.AbstractJsonLexer r5 = r6.lexer
            boolean r5 = r5.canConsumeValue()
            if (r5 == 0) goto L5d
            if (r1 == 0) goto L56
            int r1 = r6.currentIndex
            if (r1 != r4) goto L42
            kotlinx.serialization.json.internal.AbstractJsonLexer r1 = r6.lexer
            r0 = r0 ^ r2
            int r3 = kotlinx.serialization.json.internal.AbstractJsonLexer.access$getCurrentPosition$p(r1)
            if (r0 == 0) goto L37
            goto L56
        L37:
            java.lang.String r0 = "Unexpected trailing comma"
            r1.fail(r0, r3)
            kotlin.KotlinNothingValueException r0 = new kotlin.KotlinNothingValueException
            r0.<init>()
            throw r0
        L42:
            kotlinx.serialization.json.internal.AbstractJsonLexer r1 = r6.lexer
            int r3 = kotlinx.serialization.json.internal.AbstractJsonLexer.access$getCurrentPosition$p(r1)
            if (r0 == 0) goto L4b
            goto L56
        L4b:
            java.lang.String r0 = "Expected comma after the key-value pair"
            r1.fail(r0, r3)
            kotlin.KotlinNothingValueException r0 = new kotlin.KotlinNothingValueException
            r0.<init>()
            throw r0
        L56:
            int r0 = r6.currentIndex
            int r4 = r0 + 1
            r6.currentIndex = r4
            goto L5f
        L5d:
            if (r0 != 0) goto L60
        L5f:
            return r4
        L60:
            kotlinx.serialization.json.internal.AbstractJsonLexer r0 = r6.lexer
            java.lang.String r1 = "Expected '}', but had ',' instead"
            r2 = 0
            r4 = 2
            kotlinx.serialization.json.internal.AbstractJsonLexer.fail$default(r0, r1, r3, r4, r2)
            kotlin.KotlinNothingValueException r0 = new kotlin.KotlinNothingValueException
            r0.<init>()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.serialization.json.internal.StreamingJsonDecoder.decodeMapIndex():int");
    }

    private final boolean coerceInputValue(SerialDescriptor descriptor, int index) {
        String strPeekString;
        Json json = this.json;
        SerialDescriptor elementDescriptor = descriptor.getElementDescriptor(index);
        if (!elementDescriptor.isNullable() && (!this.lexer.tryConsumeNotNull())) {
            return true;
        }
        if (!Intrinsics.areEqual(elementDescriptor.getKind(), SerialKind.ENUM.INSTANCE) || (strPeekString = this.lexer.peekString(this.configuration.getIsLenient())) == null || JsonNamesMapKt.getJsonNameIndex(elementDescriptor, json, strPeekString) != -3) {
            return false;
        }
        this.lexer.consumeString();
        return true;
    }

    private final int decodeObjectIndex(SerialDescriptor descriptor) {
        int jsonNameIndex;
        boolean zTryConsumeComma;
        boolean zTryConsumeComma2 = this.lexer.tryConsumeComma();
        while (true) {
            boolean z = false;
            if (!this.lexer.canConsumeValue()) {
                if (zTryConsumeComma2) {
                    AbstractJsonLexer.fail$default(this.lexer, "Unexpected trailing comma", 0, 2, null);
                    throw new KotlinNothingValueException();
                }
                JsonElementMarker jsonElementMarker = this.elementMarker;
                if (jsonElementMarker == null) {
                    return -1;
                }
                return jsonElementMarker.nextUnmarkedIndex$kotlinx_serialization_json();
            }
            String strDecodeStringKey = decodeStringKey();
            this.lexer.consumeNextToken(AbstractJsonLexerKt.COLON);
            jsonNameIndex = JsonNamesMapKt.getJsonNameIndex(descriptor, this.json, strDecodeStringKey);
            if (jsonNameIndex == -3) {
                z = true;
                zTryConsumeComma = false;
            } else {
                if (!this.configuration.getCoerceInputValues() || !coerceInputValue(descriptor, jsonNameIndex)) {
                    break;
                }
                zTryConsumeComma = this.lexer.tryConsumeComma();
            }
            zTryConsumeComma2 = z ? handleUnknown(strDecodeStringKey) : zTryConsumeComma;
        }
        JsonElementMarker jsonElementMarker2 = this.elementMarker;
        if (jsonElementMarker2 != null) {
            jsonElementMarker2.mark$kotlinx_serialization_json(jsonNameIndex);
        }
        return jsonNameIndex;
    }

    private final boolean handleUnknown(String key) {
        if (this.configuration.getIgnoreUnknownKeys()) {
            this.lexer.skipElement(this.configuration.getIsLenient());
        } else {
            this.lexer.failOnUnknownKey(key);
        }
        return this.lexer.tryConsumeComma();
    }

    private final int decodeListIndex() {
        boolean zTryConsumeComma = this.lexer.tryConsumeComma();
        if (!this.lexer.canConsumeValue()) {
            if (!zTryConsumeComma) {
                return -1;
            }
            AbstractJsonLexer.fail$default(this.lexer, "Unexpected trailing comma", 0, 2, null);
            throw new KotlinNothingValueException();
        }
        int i = this.currentIndex;
        if (i != -1 && !zTryConsumeComma) {
            AbstractJsonLexer.fail$default(this.lexer, "Expected end of the array or comma", 0, 2, null);
            throw new KotlinNothingValueException();
        }
        int i2 = i + 1;
        this.currentIndex = i2;
        return i2;
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public boolean decodeBoolean() {
        if (this.configuration.getIsLenient()) {
            return this.lexer.consumeBooleanLenient();
        }
        return this.lexer.consumeBoolean();
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public byte decodeByte() {
        long jConsumeNumericLiteral = this.lexer.consumeNumericLiteral();
        byte b = (byte) jConsumeNumericLiteral;
        if (jConsumeNumericLiteral == b) {
            return b;
        }
        AbstractJsonLexer.fail$default(this.lexer, "Failed to parse byte for input '" + jConsumeNumericLiteral + '\'', 0, 2, null);
        throw new KotlinNothingValueException();
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public short decodeShort() {
        long jConsumeNumericLiteral = this.lexer.consumeNumericLiteral();
        short s = (short) jConsumeNumericLiteral;
        if (jConsumeNumericLiteral == s) {
            return s;
        }
        AbstractJsonLexer.fail$default(this.lexer, "Failed to parse short for input '" + jConsumeNumericLiteral + '\'', 0, 2, null);
        throw new KotlinNothingValueException();
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public int decodeInt() {
        long jConsumeNumericLiteral = this.lexer.consumeNumericLiteral();
        int i = (int) jConsumeNumericLiteral;
        if (jConsumeNumericLiteral == i) {
            return i;
        }
        AbstractJsonLexer.fail$default(this.lexer, "Failed to parse int for input '" + jConsumeNumericLiteral + '\'', 0, 2, null);
        throw new KotlinNothingValueException();
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public long decodeLong() {
        return this.lexer.consumeNumericLiteral();
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public char decodeChar() {
        String strConsumeStringLenient = this.lexer.consumeStringLenient();
        if (strConsumeStringLenient.length() != 1) {
            AbstractJsonLexer.fail$default(this.lexer, "Expected single char, but got '" + strConsumeStringLenient + '\'', 0, 2, null);
            throw new KotlinNothingValueException();
        }
        return strConsumeStringLenient.charAt(0);
    }

    private final String decodeStringKey() {
        if (this.configuration.getIsLenient()) {
            return this.lexer.consumeStringLenientNotNull();
        }
        return this.lexer.consumeKeyString();
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public String decodeString() {
        if (this.configuration.getIsLenient()) {
            return this.lexer.consumeStringLenientNotNull();
        }
        return this.lexer.consumeString();
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public Decoder decodeInline(SerialDescriptor inlineDescriptor) {
        Intrinsics.checkNotNullParameter(inlineDescriptor, "inlineDescriptor");
        return StreamingJsonEncoderKt.isUnsignedNumber(inlineDescriptor) ? new JsonDecoderForUnsignedTypes(this.lexer, this.json) : super.decodeInline(inlineDescriptor);
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public int decodeEnum(SerialDescriptor enumDescriptor) {
        Intrinsics.checkNotNullParameter(enumDescriptor, "enumDescriptor");
        return JsonNamesMapKt.getJsonNameIndexOrThrow(enumDescriptor, this.json, decodeString());
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public float decodeFloat() throws NumberFormatException {
        AbstractJsonLexer abstractJsonLexer = this.lexer;
        String strConsumeStringLenient = abstractJsonLexer.consumeStringLenient();
        try {
            float f = Float.parseFloat(strConsumeStringLenient);
            if (this.json.getConfiguration().getAllowSpecialFloatingPointValues() || !(Float.isInfinite(f) || Float.isNaN(f))) {
                return f;
            }
            JsonExceptionsKt.throwInvalidFloatingPointDecoded(this.lexer, Float.valueOf(f));
            throw new KotlinNothingValueException();
        } catch (IllegalArgumentException unused) {
            AbstractJsonLexer.fail$default(abstractJsonLexer, "Failed to parse type 'float' for input '" + strConsumeStringLenient + '\'', 0, 2, null);
            throw new KotlinNothingValueException();
        }
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public double decodeDouble() throws NumberFormatException {
        AbstractJsonLexer abstractJsonLexer = this.lexer;
        String strConsumeStringLenient = abstractJsonLexer.consumeStringLenient();
        try {
            double d = Double.parseDouble(strConsumeStringLenient);
            if (this.json.getConfiguration().getAllowSpecialFloatingPointValues() || !(Double.isInfinite(d) || Double.isNaN(d))) {
                return d;
            }
            JsonExceptionsKt.throwInvalidFloatingPointDecoded(this.lexer, Double.valueOf(d));
            throw new KotlinNothingValueException();
        } catch (IllegalArgumentException unused) {
            AbstractJsonLexer.fail$default(abstractJsonLexer, "Failed to parse type 'double' for input '" + strConsumeStringLenient + '\'', 0, 2, null);
            throw new KotlinNothingValueException();
        }
    }
}
