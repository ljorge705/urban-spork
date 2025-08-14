package com.google.firebase.firestore;

/* loaded from: classes2.dex */
public final class MemoryCacheSettings implements LocalCacheSettings {
    private MemoryGarbageCollectorSettings gcSettings;

    public MemoryGarbageCollectorSettings getGarbageCollectorSettings() {
        return this.gcSettings;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    private MemoryCacheSettings(MemoryGarbageCollectorSettings memoryGarbageCollectorSettings) {
        this.gcSettings = memoryGarbageCollectorSettings;
    }

    public int hashCode() {
        return this.gcSettings.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return getGarbageCollectorSettings().equals(((MemoryCacheSettings) obj).getGarbageCollectorSettings());
    }

    public String toString() {
        return "MemoryCacheSettings{gcSettings=" + getGarbageCollectorSettings() + "}";
    }

    public static class Builder {
        private MemoryGarbageCollectorSettings gcSettings;

        public Builder setGcSettings(MemoryGarbageCollectorSettings memoryGarbageCollectorSettings) {
            this.gcSettings = memoryGarbageCollectorSettings;
            return this;
        }

        private Builder() {
            this.gcSettings = MemoryEagerGcSettings.newBuilder().build();
        }

        public MemoryCacheSettings build() {
            return new MemoryCacheSettings(this.gcSettings);
        }
    }
}
