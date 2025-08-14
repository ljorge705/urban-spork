package com.google.firebase.firestore;

/* loaded from: classes2.dex */
public final class MemoryEagerGcSettings implements MemoryGarbageCollectorSettings {
    public String toString() {
        return "MemoryEagerGcSettings{}";
    }

    private MemoryEagerGcSettings() {
    }

    public static class Builder {
        private Builder() {
        }

        public MemoryEagerGcSettings build() {
            return new MemoryEagerGcSettings();
        }
    }

    public int hashCode() {
        return super.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass();
    }

    public static Builder newBuilder() {
        return new Builder();
    }
}
