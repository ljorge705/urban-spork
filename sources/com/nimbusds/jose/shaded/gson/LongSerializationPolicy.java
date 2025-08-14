package com.nimbusds.jose.shaded.gson;

/* loaded from: classes2.dex */
public enum LongSerializationPolicy {
    DEFAULT { // from class: com.nimbusds.jose.shaded.gson.LongSerializationPolicy.1
        @Override // com.nimbusds.jose.shaded.gson.LongSerializationPolicy
        public JsonElement serialize(Long l) {
            if (l == null) {
                return JsonNull.INSTANCE;
            }
            return new JsonPrimitive(l);
        }
    },
    STRING { // from class: com.nimbusds.jose.shaded.gson.LongSerializationPolicy.2
        @Override // com.nimbusds.jose.shaded.gson.LongSerializationPolicy
        public JsonElement serialize(Long l) {
            if (l == null) {
                return JsonNull.INSTANCE;
            }
            return new JsonPrimitive(l.toString());
        }
    };

    public abstract JsonElement serialize(Long l);
}
