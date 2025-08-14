package com.google.firebase.firestore.model;

import com.google.firebase.firestore.util.Assert;

/* loaded from: classes2.dex */
public final class DatabaseId implements Comparable<DatabaseId> {
    public static final String DEFAULT_DATABASE_ID = "(default)";
    public static final DatabaseId EMPTY = forDatabase("", "");
    private final String databaseId;
    private final String projectId;

    public String getDatabaseId() {
        return this.databaseId;
    }

    public String getProjectId() {
        return this.projectId;
    }

    public static DatabaseId forProject(String str) {
        return forDatabase(str, DEFAULT_DATABASE_ID);
    }

    public static DatabaseId forDatabase(String str, String str2) {
        return new DatabaseId(str, str2);
    }

    private DatabaseId(String str, String str2) {
        this.projectId = str;
        this.databaseId = str2;
    }

    public static DatabaseId fromName(String str) {
        ResourcePath resourcePathFromString = ResourcePath.fromString(str);
        boolean z = false;
        if (resourcePathFromString.length() > 3 && resourcePathFromString.getSegment(0).equals("projects") && resourcePathFromString.getSegment(2).equals("databases")) {
            z = true;
        }
        Assert.hardAssert(z, "Tried to parse an invalid resource name: %s", resourcePathFromString);
        return new DatabaseId(resourcePathFromString.getSegment(1), resourcePathFromString.getSegment(3));
    }

    public String toString() {
        return "DatabaseId(" + this.projectId + ", " + this.databaseId + ")";
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DatabaseId databaseId = (DatabaseId) obj;
        return this.projectId.equals(databaseId.projectId) && this.databaseId.equals(databaseId.databaseId);
    }

    public int hashCode() {
        return (this.projectId.hashCode() * 31) + this.databaseId.hashCode();
    }

    @Override // java.lang.Comparable
    public int compareTo(DatabaseId databaseId) {
        int iCompareTo = this.projectId.compareTo(databaseId.projectId);
        return iCompareTo != 0 ? iCompareTo : this.databaseId.compareTo(databaseId.databaseId);
    }
}
