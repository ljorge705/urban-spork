package com.google.firebase.firestore.core;

import com.google.firebase.firestore.core.OrderBy;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.util.Assert;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public final class Query {
    private static final OrderBy KEY_ORDERING_ASC = OrderBy.getInstance(OrderBy.Direction.ASCENDING, FieldPath.KEY_PATH);
    private static final OrderBy KEY_ORDERING_DESC = OrderBy.getInstance(OrderBy.Direction.DESCENDING, FieldPath.KEY_PATH);
    private final String collectionGroup;
    private final Bound endAt;
    private final List<OrderBy> explicitSortOrder;
    private final List<Filter> filters;
    private final long limit;
    private final LimitType limitType;
    private List<OrderBy> memoizedOrderBy;
    private Target memoizedTarget;
    private final ResourcePath path;
    private final Bound startAt;

    public enum LimitType {
        LIMIT_TO_FIRST,
        LIMIT_TO_LAST
    }

    public String getCollectionGroup() {
        return this.collectionGroup;
    }

    public Bound getEndAt() {
        return this.endAt;
    }

    public List<OrderBy> getExplicitOrderBy() {
        return this.explicitSortOrder;
    }

    public List<Filter> getFilters() {
        return this.filters;
    }

    public long getLimit() {
        return this.limit;
    }

    public LimitType getLimitType() {
        return this.limitType;
    }

    public ResourcePath getPath() {
        return this.path;
    }

    public Bound getStartAt() {
        return this.startAt;
    }

    public boolean hasLimit() {
        return this.limit != -1;
    }

    public boolean isCollectionGroupQuery() {
        return this.collectionGroup != null;
    }

    public static Query atPath(ResourcePath resourcePath) {
        return new Query(resourcePath, null);
    }

    public Query(ResourcePath resourcePath, String str, List<Filter> list, List<OrderBy> list2, long j, LimitType limitType, Bound bound, Bound bound2) {
        this.path = resourcePath;
        this.collectionGroup = str;
        this.explicitSortOrder = list2;
        this.filters = list;
        this.limit = j;
        this.limitType = limitType;
        this.startAt = bound;
        this.endAt = bound2;
    }

    public Query(ResourcePath resourcePath, String str) {
        this(resourcePath, str, Collections.emptyList(), Collections.emptyList(), -1L, LimitType.LIMIT_TO_FIRST, null, null);
    }

    public boolean isDocumentQuery() {
        return DocumentKey.isDocumentKey(this.path) && this.collectionGroup == null && this.filters.isEmpty();
    }

    public boolean matchesAllDocuments() {
        if (this.filters.isEmpty() && this.limit == -1 && this.startAt == null && this.endAt == null) {
            if (getExplicitOrderBy().isEmpty()) {
                return true;
            }
            if (getExplicitOrderBy().size() == 1 && getFirstOrderByField().isKeyField()) {
                return true;
            }
        }
        return false;
    }

    public FieldPath getFirstOrderByField() {
        if (this.explicitSortOrder.isEmpty()) {
            return null;
        }
        return this.explicitSortOrder.get(0).getField();
    }

    public FieldPath inequalityField() {
        Iterator<Filter> it = this.filters.iterator();
        while (it.hasNext()) {
            FieldPath firstInequalityField = it.next().getFirstInequalityField();
            if (firstInequalityField != null) {
                return firstInequalityField;
            }
        }
        return null;
    }

    public Query filter(Filter filter) {
        boolean z = true;
        Assert.hardAssert(!isDocumentQuery(), "No filter is allowed for document query", new Object[0]);
        FieldPath firstInequalityField = filter.getFirstInequalityField();
        FieldPath fieldPathInequalityField = inequalityField();
        Assert.hardAssert(fieldPathInequalityField == null || firstInequalityField == null || fieldPathInequalityField.equals(firstInequalityField), "Query must only have one inequality field", new Object[0]);
        if (!this.explicitSortOrder.isEmpty() && firstInequalityField != null && !this.explicitSortOrder.get(0).field.equals(firstInequalityField)) {
            z = false;
        }
        Assert.hardAssert(z, "First orderBy must match inequality field", new Object[0]);
        ArrayList arrayList = new ArrayList(this.filters);
        arrayList.add(filter);
        return new Query(this.path, this.collectionGroup, arrayList, this.explicitSortOrder, this.limit, this.limitType, this.startAt, this.endAt);
    }

    public Query orderBy(OrderBy orderBy) {
        FieldPath fieldPathInequalityField;
        Assert.hardAssert(!isDocumentQuery(), "No ordering is allowed for document query", new Object[0]);
        if (this.explicitSortOrder.isEmpty() && (fieldPathInequalityField = inequalityField()) != null && !fieldPathInequalityField.equals(orderBy.field)) {
            throw Assert.fail("First orderBy must match inequality field", new Object[0]);
        }
        ArrayList arrayList = new ArrayList(this.explicitSortOrder);
        arrayList.add(orderBy);
        return new Query(this.path, this.collectionGroup, this.filters, arrayList, this.limit, this.limitType, this.startAt, this.endAt);
    }

    public Query limitToFirst(long j) {
        return new Query(this.path, this.collectionGroup, this.filters, this.explicitSortOrder, j, LimitType.LIMIT_TO_FIRST, this.startAt, this.endAt);
    }

    public Query limitToLast(long j) {
        return new Query(this.path, this.collectionGroup, this.filters, this.explicitSortOrder, j, LimitType.LIMIT_TO_LAST, this.startAt, this.endAt);
    }

    public Query startAt(Bound bound) {
        return new Query(this.path, this.collectionGroup, this.filters, this.explicitSortOrder, this.limit, this.limitType, bound, this.endAt);
    }

    public Query endAt(Bound bound) {
        return new Query(this.path, this.collectionGroup, this.filters, this.explicitSortOrder, this.limit, this.limitType, this.startAt, bound);
    }

    public Query asCollectionQueryAtPath(ResourcePath resourcePath) {
        return new Query(resourcePath, null, this.filters, this.explicitSortOrder, this.limit, this.limitType, this.startAt, this.endAt);
    }

    public List<OrderBy> getOrderBy() {
        OrderBy.Direction direction;
        if (this.memoizedOrderBy == null) {
            FieldPath fieldPathInequalityField = inequalityField();
            FieldPath firstOrderByField = getFirstOrderByField();
            boolean z = false;
            if (fieldPathInequalityField != null && firstOrderByField == null) {
                if (fieldPathInequalityField.isKeyField()) {
                    this.memoizedOrderBy = Collections.singletonList(KEY_ORDERING_ASC);
                } else {
                    this.memoizedOrderBy = Arrays.asList(OrderBy.getInstance(OrderBy.Direction.ASCENDING, fieldPathInequalityField), KEY_ORDERING_ASC);
                }
            } else {
                ArrayList arrayList = new ArrayList();
                for (OrderBy orderBy : this.explicitSortOrder) {
                    arrayList.add(orderBy);
                    if (orderBy.getField().equals(FieldPath.KEY_PATH)) {
                        z = true;
                    }
                }
                if (!z) {
                    if (this.explicitSortOrder.size() > 0) {
                        List<OrderBy> list = this.explicitSortOrder;
                        direction = list.get(list.size() - 1).getDirection();
                    } else {
                        direction = OrderBy.Direction.ASCENDING;
                    }
                    arrayList.add(direction.equals(OrderBy.Direction.ASCENDING) ? KEY_ORDERING_ASC : KEY_ORDERING_DESC);
                }
                this.memoizedOrderBy = arrayList;
            }
        }
        return this.memoizedOrderBy;
    }

    private boolean matchesPathAndCollectionGroup(Document document) {
        ResourcePath path = document.getKey().getPath();
        if (this.collectionGroup != null) {
            return document.getKey().hasCollectionId(this.collectionGroup) && this.path.isPrefixOf(path);
        }
        if (DocumentKey.isDocumentKey(this.path)) {
            return this.path.equals(path);
        }
        return this.path.isPrefixOf(path) && this.path.length() == path.length() - 1;
    }

    private boolean matchesFilters(Document document) {
        Iterator<Filter> it = this.filters.iterator();
        while (it.hasNext()) {
            if (!it.next().matches(document)) {
                return false;
            }
        }
        return true;
    }

    private boolean matchesOrderBy(Document document) {
        for (OrderBy orderBy : getOrderBy()) {
            if (!orderBy.getField().equals(FieldPath.KEY_PATH) && document.getField(orderBy.field) == null) {
                return false;
            }
        }
        return true;
    }

    private boolean matchesBounds(Document document) {
        Bound bound = this.startAt;
        if (bound != null && !bound.sortsBeforeDocument(getOrderBy(), document)) {
            return false;
        }
        Bound bound2 = this.endAt;
        return bound2 == null || bound2.sortsAfterDocument(getOrderBy(), document);
    }

    public boolean matches(Document document) {
        return document.isFoundDocument() && matchesPathAndCollectionGroup(document) && matchesOrderBy(document) && matchesFilters(document) && matchesBounds(document);
    }

    public Comparator<Document> comparator() {
        return new QueryComparator(getOrderBy());
    }

    private static class QueryComparator implements Comparator<Document> {
        private final List<OrderBy> sortOrder;

        QueryComparator(List<OrderBy> list) {
            boolean z;
            Iterator<OrderBy> it = list.iterator();
            loop0: while (true) {
                z = false;
                while (it.hasNext()) {
                    z = (z || it.next().getField().equals(FieldPath.KEY_PATH)) ? true : z;
                }
            }
            if (!z) {
                throw new IllegalArgumentException("QueryComparator needs to have a key ordering");
            }
            this.sortOrder = list;
        }

        @Override // java.util.Comparator
        public int compare(Document document, Document document2) {
            Iterator<OrderBy> it = this.sortOrder.iterator();
            while (it.hasNext()) {
                int iCompare = it.next().compare(document, document2);
                if (iCompare != 0) {
                    return iCompare;
                }
            }
            return 0;
        }
    }

    public Target toTarget() {
        OrderBy.Direction direction;
        if (this.memoizedTarget == null) {
            if (this.limitType == LimitType.LIMIT_TO_FIRST) {
                this.memoizedTarget = new Target(getPath(), getCollectionGroup(), getFilters(), getOrderBy(), this.limit, getStartAt(), getEndAt());
            } else {
                ArrayList arrayList = new ArrayList();
                for (OrderBy orderBy : getOrderBy()) {
                    if (orderBy.getDirection() == OrderBy.Direction.DESCENDING) {
                        direction = OrderBy.Direction.ASCENDING;
                    } else {
                        direction = OrderBy.Direction.DESCENDING;
                    }
                    arrayList.add(OrderBy.getInstance(direction, orderBy.getField()));
                }
                this.memoizedTarget = new Target(getPath(), getCollectionGroup(), getFilters(), arrayList, this.limit, this.endAt != null ? new Bound(this.endAt.getPosition(), this.endAt.isInclusive()) : null, this.startAt != null ? new Bound(this.startAt.getPosition(), this.startAt.isInclusive()) : null);
            }
        }
        return this.memoizedTarget;
    }

    public String getCanonicalId() {
        return toTarget().getCanonicalId() + "|lt:" + this.limitType;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Query query = (Query) obj;
        if (this.limitType != query.limitType) {
            return false;
        }
        return toTarget().equals(query.toTarget());
    }

    public int hashCode() {
        return (toTarget().hashCode() * 31) + this.limitType.hashCode();
    }

    public String toString() {
        return "Query(target=" + toTarget().toString() + ";limitType=" + this.limitType.toString() + ")";
    }
}
