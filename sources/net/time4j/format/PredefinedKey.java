package net.time4j.format;

import net.time4j.engine.AttributeKey;

/* loaded from: classes4.dex */
final class PredefinedKey<A> implements AttributeKey<A> {
    private final String name;
    private final Class<A> type;

    @Override // net.time4j.engine.AttributeKey
    public String name() {
        return this.name;
    }

    @Override // net.time4j.engine.AttributeKey
    public Class<A> type() {
        return this.type;
    }

    private PredefinedKey(String str, Class<A> cls) {
        if (str == null) {
            throw new NullPointerException("Missing name of attribute key.");
        }
        if (cls == null) {
            throw new NullPointerException("Missing type of attribute.");
        }
        this.name = str;
        this.type = cls;
    }

    static <A> PredefinedKey<A> valueOf(String str, Class<A> cls) {
        return new PredefinedKey<>(str, cls);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PredefinedKey)) {
            return false;
        }
        PredefinedKey predefinedKey = (PredefinedKey) obj;
        return this.name.equals(predefinedKey.name) && this.type.equals(predefinedKey.type);
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    public String toString() {
        return this.type.getName() + "@" + this.name;
    }
}
