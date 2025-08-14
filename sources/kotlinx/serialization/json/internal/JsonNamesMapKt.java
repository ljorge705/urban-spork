package kotlinx.serialization.json.internal;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonNames;
import kotlinx.serialization.json.JsonSchemaCacheKt;
import kotlinx.serialization.json.internal.DescriptorSchemaCache;

/* compiled from: JsonNamesMap.kt */
@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\u001a\u0018\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002*\u00020\nH\u0000\u001a\u001c\u0010\u000b\u001a\u00020\u0004*\u00020\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0003H\u0000\u001a\u001c\u0010\u000f\u001a\u00020\u0004*\u00020\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0003H\u0000\u001aF\u0010\u0010\u001a\u00020\u0011*\u00020\r2\u0006\u0010\u0012\u001a\u00020\n2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00110\u00142\u000e\u0010\u0015\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00142\u000e\b\u0002\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u0014H\u0080\bø\u0001\u0000\".\u0010\u0000\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00020\u00018\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0018"}, d2 = {"JsonAlternativeNamesKey", "Lkotlinx/serialization/json/internal/DescriptorSchemaCache$Key;", "", "", "", "getJsonAlternativeNamesKey$annotations", "()V", "getJsonAlternativeNamesKey", "()Lkotlinx/serialization/json/internal/DescriptorSchemaCache$Key;", "buildAlternativeNamesMap", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getJsonNameIndex", "json", "Lkotlinx/serialization/json/Json;", "name", "getJsonNameIndexOrThrow", "tryCoerceValue", "", "elementDescriptor", "peekNull", "Lkotlin/Function0;", "peekString", "onEnumCoercing", "", "kotlinx-serialization-json"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public final class JsonNamesMapKt {
    private static final DescriptorSchemaCache.Key<Map<String, Integer>> JsonAlternativeNamesKey = new DescriptorSchemaCache.Key<>();

    public static final DescriptorSchemaCache.Key<Map<String, Integer>> getJsonAlternativeNamesKey() {
        return JsonAlternativeNamesKey;
    }

    public static /* synthetic */ void getJsonAlternativeNamesKey$annotations() {
    }

    private static final void buildAlternativeNamesMap$putOrThrow(Map<String, Integer> map, SerialDescriptor serialDescriptor, String str, int i) {
        if (map.containsKey(str)) {
            throw new JsonException("The suggested name '" + str + "' for property " + serialDescriptor.getElementName(i) + " is already one of the names for property " + serialDescriptor.getElementName(((Number) MapsKt.getValue(map, str)).intValue()) + " in " + serialDescriptor);
        }
        map.put(str, Integer.valueOf(i));
    }

    public static final Map<String, Integer> buildAlternativeNamesMap(SerialDescriptor serialDescriptor) {
        String[] strArr;
        Intrinsics.checkNotNullParameter(serialDescriptor, "<this>");
        int elementsCount = serialDescriptor.getElementsCount();
        Map<String, Integer> mapCreateMapForCache = null;
        if (elementsCount > 0) {
            int i = 0;
            while (true) {
                int i2 = i + 1;
                List<Annotation> elementAnnotations = serialDescriptor.getElementAnnotations(i);
                ArrayList arrayList = new ArrayList();
                for (Object obj : elementAnnotations) {
                    if (obj instanceof JsonNames) {
                        arrayList.add(obj);
                    }
                }
                JsonNames jsonNames = (JsonNames) CollectionsKt.singleOrNull((List) arrayList);
                if (jsonNames != null && (strArr = jsonNames.get_names()) != null) {
                    for (String str : strArr) {
                        if (mapCreateMapForCache == null) {
                            mapCreateMapForCache = CreateMapForCacheKt.createMapForCache(serialDescriptor.getElementsCount());
                        }
                        Intrinsics.checkNotNull(mapCreateMapForCache);
                        buildAlternativeNamesMap$putOrThrow(mapCreateMapForCache, serialDescriptor, str, i);
                    }
                }
                if (i2 >= elementsCount) {
                    break;
                }
                i = i2;
            }
        }
        return mapCreateMapForCache == null ? MapsKt.emptyMap() : mapCreateMapForCache;
    }

    public static final int getJsonNameIndex(SerialDescriptor serialDescriptor, Json json, String name) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "<this>");
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.checkNotNullParameter(name, "name");
        int elementIndex = serialDescriptor.getElementIndex(name);
        if (elementIndex != -3 || !json.getConfiguration().getUseAlternativeNames()) {
            return elementIndex;
        }
        Integer num = (Integer) ((Map) JsonSchemaCacheKt.getSchemaCache(json).getOrPut(serialDescriptor, JsonAlternativeNamesKey, new JsonNamesMapKt$getJsonNameIndex$alternativeNamesMap$1(serialDescriptor))).get(name);
        if (num == null) {
            return -3;
        }
        return num.intValue();
    }

    public static final int getJsonNameIndexOrThrow(SerialDescriptor serialDescriptor, Json json, String name) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "<this>");
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.checkNotNullParameter(name, "name");
        int jsonNameIndex = getJsonNameIndex(serialDescriptor, json, name);
        if (jsonNameIndex != -3) {
            return jsonNameIndex;
        }
        throw new SerializationException(serialDescriptor.getSerialName() + " does not contain element with name '" + name + '\'');
    }

    public static /* synthetic */ boolean tryCoerceValue$default(Json json, SerialDescriptor elementDescriptor, Function0 peekNull, Function0 peekString, Function0 onEnumCoercing, int i, Object obj) {
        String str;
        if ((i & 8) != 0) {
            onEnumCoercing = new Function0<Unit>() { // from class: kotlinx.serialization.json.internal.JsonNamesMapKt.tryCoerceValue.1
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }
            };
        }
        Intrinsics.checkNotNullParameter(json, "<this>");
        Intrinsics.checkNotNullParameter(elementDescriptor, "elementDescriptor");
        Intrinsics.checkNotNullParameter(peekNull, "peekNull");
        Intrinsics.checkNotNullParameter(peekString, "peekString");
        Intrinsics.checkNotNullParameter(onEnumCoercing, "onEnumCoercing");
        if (!elementDescriptor.isNullable() && ((Boolean) peekNull.invoke()).booleanValue()) {
            return true;
        }
        if (!Intrinsics.areEqual(elementDescriptor.getKind(), SerialKind.ENUM.INSTANCE) || (str = (String) peekString.invoke()) == null || getJsonNameIndex(elementDescriptor, json, str) != -3) {
            return false;
        }
        onEnumCoercing.invoke();
        return true;
    }

    public static final boolean tryCoerceValue(Json json, SerialDescriptor elementDescriptor, Function0<Boolean> peekNull, Function0<String> peekString, Function0<Unit> onEnumCoercing) {
        String strInvoke;
        Intrinsics.checkNotNullParameter(json, "<this>");
        Intrinsics.checkNotNullParameter(elementDescriptor, "elementDescriptor");
        Intrinsics.checkNotNullParameter(peekNull, "peekNull");
        Intrinsics.checkNotNullParameter(peekString, "peekString");
        Intrinsics.checkNotNullParameter(onEnumCoercing, "onEnumCoercing");
        if (!elementDescriptor.isNullable() && peekNull.invoke().booleanValue()) {
            return true;
        }
        if (!Intrinsics.areEqual(elementDescriptor.getKind(), SerialKind.ENUM.INSTANCE) || (strInvoke = peekString.invoke()) == null || getJsonNameIndex(elementDescriptor, json, strInvoke) != -3) {
            return false;
        }
        onEnumCoercing.invoke();
        return true;
    }
}
