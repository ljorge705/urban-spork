package com.nimbusds.jose.shaded.gson;

/* loaded from: classes2.dex */
public final class JsonNull extends JsonElement {
    public static final JsonNull INSTANCE = new JsonNull();

    @Override // com.nimbusds.jose.shaded.gson.JsonElement
    public JsonNull deepCopy() {
        return INSTANCE;
    }

    @Deprecated
    public JsonNull() {
    }

    public int hashCode() {
        return JsonNull.class.hashCode();
    }

    public boolean equals(Object obj) {
        return obj instanceof JsonNull;
    }
}
