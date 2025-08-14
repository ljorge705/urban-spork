package com.nimbusds.jose.shaded.gson.internal.bind;

import com.nimbusds.jose.shaded.gson.Gson;
import com.nimbusds.jose.shaded.gson.JsonDeserializer;
import com.nimbusds.jose.shaded.gson.JsonSerializer;
import com.nimbusds.jose.shaded.gson.TypeAdapter;
import com.nimbusds.jose.shaded.gson.TypeAdapterFactory;
import com.nimbusds.jose.shaded.gson.annotations.JsonAdapter;
import com.nimbusds.jose.shaded.gson.internal.ConstructorConstructor;
import com.nimbusds.jose.shaded.gson.reflect.TypeToken;

/* loaded from: classes2.dex */
public final class JsonAdapterAnnotationTypeAdapterFactory implements TypeAdapterFactory {
    private final ConstructorConstructor constructorConstructor;

    public JsonAdapterAnnotationTypeAdapterFactory(ConstructorConstructor constructorConstructor) {
        this.constructorConstructor = constructorConstructor;
    }

    @Override // com.nimbusds.jose.shaded.gson.TypeAdapterFactory
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        JsonAdapter jsonAdapter = (JsonAdapter) typeToken.getRawType().getAnnotation(JsonAdapter.class);
        if (jsonAdapter == null) {
            return null;
        }
        return (TypeAdapter<T>) getTypeAdapter(this.constructorConstructor, gson, typeToken, jsonAdapter);
    }

    TypeAdapter<?> getTypeAdapter(ConstructorConstructor constructorConstructor, Gson gson, TypeToken<?> typeToken, JsonAdapter jsonAdapter) {
        TypeAdapter<?> treeTypeAdapter;
        Object objConstruct = constructorConstructor.get(TypeToken.get((Class) jsonAdapter.value())).construct();
        boolean zNullSafe = jsonAdapter.nullSafe();
        if (objConstruct instanceof TypeAdapter) {
            treeTypeAdapter = (TypeAdapter) objConstruct;
        } else if (objConstruct instanceof TypeAdapterFactory) {
            treeTypeAdapter = ((TypeAdapterFactory) objConstruct).create(gson, typeToken);
        } else {
            boolean z = objConstruct instanceof JsonSerializer;
            if (z || (objConstruct instanceof JsonDeserializer)) {
                treeTypeAdapter = new TreeTypeAdapter<>(z ? (JsonSerializer) objConstruct : null, objConstruct instanceof JsonDeserializer ? (JsonDeserializer) objConstruct : null, gson, typeToken, null, zNullSafe);
                zNullSafe = false;
            } else {
                throw new IllegalArgumentException("Invalid attempt to bind an instance of " + objConstruct.getClass().getName() + " as a @JsonAdapter for " + typeToken.toString() + ". @JsonAdapter value must be a TypeAdapter, TypeAdapterFactory, JsonSerializer or JsonDeserializer.");
            }
        }
        return (treeTypeAdapter == null || !zNullSafe) ? treeTypeAdapter : treeTypeAdapter.nullSafe();
    }
}
