package com.onfido.android.sdk.capture.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlinx.serialization.json.JsonArray;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonObject;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0002\u001a\u0010\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0002\u001a\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005*\u0004\u0018\u00010\u0005H\u0007\u001a\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0005*\u0004\u0018\u00010\u0005H\u0007¨\u0006\u0007"}, d2 = {"camelToSnake", "", "str", "snakeToCamel", "convertCamelToSnakeCase", "Lkotlinx/serialization/json/JsonElement;", "convertSnakeToCamelCase", "onfido-capture-sdk-core_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class JsonExtKt {
    private static final String camelToSnake(String str) {
        String lowerCase = new Regex("([a-z])([A-Z])").replace(str, "$1_$2").toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        return lowerCase;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final JsonElement convertCamelToSnakeCase(JsonElement jsonElement) {
        if (jsonElement == 0) {
            return null;
        }
        if (!(jsonElement instanceof JsonObject)) {
            if (!(jsonElement instanceof JsonArray)) {
                return jsonElement;
            }
            ArrayList arrayList = new ArrayList();
            Iterator it = ((Iterable) jsonElement).iterator();
            while (it.hasNext()) {
                JsonElement jsonElementConvertCamelToSnakeCase = convertCamelToSnakeCase((JsonElement) it.next());
                if (jsonElementConvertCamelToSnakeCase != null) {
                    arrayList.add(jsonElementConvertCamelToSnakeCase);
                }
            }
            return new JsonArray(arrayList);
        }
        Map map = (Map) jsonElement;
        ArrayList arrayList2 = new ArrayList(map.size());
        for (Map.Entry entry : map.entrySet()) {
            String str = (String) entry.getKey();
            JsonElement jsonElement2 = (JsonElement) entry.getValue();
            String strCamelToSnake = camelToSnake(str);
            JsonElement jsonElementConvertCamelToSnakeCase2 = convertCamelToSnakeCase(jsonElement2);
            Intrinsics.checkNotNull(jsonElementConvertCamelToSnakeCase2, "null cannot be cast to non-null type kotlinx.serialization.json.JsonElement");
            arrayList2.add(TuplesKt.to(strCamelToSnake, jsonElementConvertCamelToSnakeCase2));
        }
        return new JsonObject(MapsKt.toMap(arrayList2));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final JsonElement convertSnakeToCamelCase(JsonElement jsonElement) {
        if (jsonElement == 0) {
            return null;
        }
        if (!(jsonElement instanceof JsonObject)) {
            if (!(jsonElement instanceof JsonArray)) {
                return jsonElement;
            }
            ArrayList arrayList = new ArrayList();
            Iterator it = ((Iterable) jsonElement).iterator();
            while (it.hasNext()) {
                JsonElement jsonElementConvertSnakeToCamelCase = convertSnakeToCamelCase((JsonElement) it.next());
                if (jsonElementConvertSnakeToCamelCase != null) {
                    arrayList.add(jsonElementConvertSnakeToCamelCase);
                }
            }
            return new JsonArray(arrayList);
        }
        Map map = (Map) jsonElement;
        ArrayList arrayList2 = new ArrayList(map.size());
        for (Map.Entry entry : map.entrySet()) {
            String str = (String) entry.getKey();
            JsonElement jsonElement2 = (JsonElement) entry.getValue();
            String strSnakeToCamel = snakeToCamel(str);
            JsonElement jsonElementConvertSnakeToCamelCase2 = convertSnakeToCamelCase(jsonElement2);
            Intrinsics.checkNotNull(jsonElementConvertSnakeToCamelCase2, "null cannot be cast to non-null type kotlinx.serialization.json.JsonElement");
            arrayList2.add(TuplesKt.to(strSnakeToCamel, jsonElementConvertSnakeToCamelCase2));
        }
        return new JsonObject(MapsKt.toMap(arrayList2));
    }

    private static final String snakeToCamel(String str) {
        Iterator it = Regex.findAll$default(new Regex("_([a-z])"), str, 0, 2, null).iterator();
        String strReplaceFirst$default = str;
        while (it.hasNext()) {
            String value = ((MatchResult) it.next()).getValue();
            String strValueOf = String.valueOf(value.charAt(1));
            Intrinsics.checkNotNull(strValueOf, "null cannot be cast to non-null type java.lang.String");
            String upperCase = strValueOf.toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
            strReplaceFirst$default = StringsKt.replaceFirst$default(strReplaceFirst$default, value, upperCase, false, 4, (Object) null);
        }
        return strReplaceFirst$default;
    }
}
