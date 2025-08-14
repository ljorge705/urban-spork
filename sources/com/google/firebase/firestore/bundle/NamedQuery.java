package com.google.firebase.firestore.bundle;

import com.google.firebase.firestore.model.SnapshotVersion;

/* loaded from: classes2.dex */
public class NamedQuery implements BundleElement {
    private final BundledQuery bundledQuery;
    private final String name;
    private final SnapshotVersion readTime;

    public BundledQuery getBundledQuery() {
        return this.bundledQuery;
    }

    public String getName() {
        return this.name;
    }

    public SnapshotVersion getReadTime() {
        return this.readTime;
    }

    public NamedQuery(String str, BundledQuery bundledQuery, SnapshotVersion snapshotVersion) {
        this.name = str;
        this.bundledQuery = bundledQuery;
        this.readTime = snapshotVersion;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        NamedQuery namedQuery = (NamedQuery) obj;
        if (this.name.equals(namedQuery.name) && this.bundledQuery.equals(namedQuery.bundledQuery)) {
            return this.readTime.equals(namedQuery.readTime);
        }
        return false;
    }

    public int hashCode() {
        return (((this.name.hashCode() * 31) + this.bundledQuery.hashCode()) * 31) + this.readTime.hashCode();
    }
}
