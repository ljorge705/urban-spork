package com.google.firebase.firestore.model;

import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.util.Assert;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* loaded from: classes2.dex */
public final class DocumentKey implements Comparable<DocumentKey> {
    private static final Comparator<DocumentKey> COMPARATOR;
    private static final ImmutableSortedSet<DocumentKey> EMPTY_KEY_SET;
    public static final String KEY_FIELD_NAME = "__name__";
    private final ResourcePath path;

    public static Comparator<DocumentKey> comparator() {
        return COMPARATOR;
    }

    public static ImmutableSortedSet<DocumentKey> emptyKeySet() {
        return EMPTY_KEY_SET;
    }

    public ResourcePath getPath() {
        return this.path;
    }

    static {
        Comparator<DocumentKey> comparator = new Comparator() { // from class: com.google.firebase.firestore.model.DocumentKey$$ExternalSyntheticLambda0
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return ((DocumentKey) obj).compareTo((DocumentKey) obj2);
            }
        };
        COMPARATOR = comparator;
        EMPTY_KEY_SET = new ImmutableSortedSet<>(Collections.emptyList(), comparator);
    }

    public static DocumentKey empty() {
        return fromSegments(Collections.emptyList());
    }

    public static DocumentKey fromName(String str) {
        ResourcePath resourcePathFromString = ResourcePath.fromString(str);
        boolean z = false;
        if (resourcePathFromString.length() > 4 && resourcePathFromString.getSegment(0).equals("projects") && resourcePathFromString.getSegment(2).equals("databases") && resourcePathFromString.getSegment(4).equals("documents")) {
            z = true;
        }
        Assert.hardAssert(z, "Tried to parse an invalid key: %s", resourcePathFromString);
        return fromPath(resourcePathFromString.popFirst(5));
    }

    public static DocumentKey fromPath(ResourcePath resourcePath) {
        return new DocumentKey(resourcePath);
    }

    public static DocumentKey fromSegments(List<String> list) {
        return new DocumentKey(ResourcePath.fromSegments(list));
    }

    public static DocumentKey fromPathString(String str) {
        return new DocumentKey(ResourcePath.fromString(str));
    }

    public static boolean isDocumentKey(ResourcePath resourcePath) {
        return resourcePath.length() % 2 == 0;
    }

    private DocumentKey(ResourcePath resourcePath) {
        Assert.hardAssert(isDocumentKey(resourcePath), "Not a document key path: %s", resourcePath);
        this.path = resourcePath;
    }

    public String getCollectionGroup() {
        return this.path.getSegment(r0.length() - 2);
    }

    public ResourcePath getCollectionPath() {
        return this.path.popLast();
    }

    public String getDocumentId() {
        return this.path.getLastSegment();
    }

    public boolean hasCollectionId(String str) {
        return this.path.length() >= 2 && this.path.segments.get(this.path.length() - 2).equals(str);
    }

    @Override // java.lang.Comparable
    public int compareTo(DocumentKey documentKey) {
        return this.path.compareTo(documentKey.path);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.path.equals(((DocumentKey) obj).path);
    }

    public int hashCode() {
        return this.path.hashCode();
    }

    public String toString() {
        return this.path.toString();
    }
}
