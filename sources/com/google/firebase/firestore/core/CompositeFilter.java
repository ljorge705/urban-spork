package com.google.firebase.firestore.core;

import android.text.TextUtils;
import com.clevertap.android.sdk.Constants;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.util.Function;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class CompositeFilter extends Filter {
    private final List<Filter> filters;
    private List<FieldFilter> memoizedFlattenedFilters;
    private final Operator operator;

    public Operator getOperator() {
        return this.operator;
    }

    public enum Operator {
        AND("and"),
        OR("or");

        private final String text;

        @Override // java.lang.Enum
        public String toString() {
            return this.text;
        }

        Operator(String str) {
            this.text = str;
        }
    }

    public CompositeFilter(List<Filter> list, Operator operator) {
        this.filters = new ArrayList(list);
        this.operator = operator;
    }

    @Override // com.google.firebase.firestore.core.Filter
    public List<Filter> getFilters() {
        return Collections.unmodifiableList(this.filters);
    }

    @Override // com.google.firebase.firestore.core.Filter
    public List<FieldFilter> getFlattenedFilters() {
        List<FieldFilter> list = this.memoizedFlattenedFilters;
        if (list != null) {
            return Collections.unmodifiableList(list);
        }
        this.memoizedFlattenedFilters = new ArrayList();
        Iterator<Filter> it = this.filters.iterator();
        while (it.hasNext()) {
            this.memoizedFlattenedFilters.addAll(it.next().getFlattenedFilters());
        }
        return Collections.unmodifiableList(this.memoizedFlattenedFilters);
    }

    @Override // com.google.firebase.firestore.core.Filter
    public FieldPath getFirstInequalityField() {
        FieldFilter fieldFilterFindFirstMatchingFilter = findFirstMatchingFilter(new Function() { // from class: com.google.firebase.firestore.core.CompositeFilter$$ExternalSyntheticLambda0
            @Override // com.google.firebase.firestore.util.Function
            public final Object apply(Object obj) {
                return Boolean.valueOf(((FieldFilter) obj).isInequality());
            }
        });
        if (fieldFilterFindFirstMatchingFilter != null) {
            return fieldFilterFindFirstMatchingFilter.getField();
        }
        return null;
    }

    public boolean isConjunction() {
        return this.operator == Operator.AND;
    }

    public boolean isDisjunction() {
        return this.operator == Operator.OR;
    }

    public boolean isFlatConjunction() {
        return isFlat() && isConjunction();
    }

    public boolean isFlat() {
        Iterator<Filter> it = this.filters.iterator();
        while (it.hasNext()) {
            if (it.next() instanceof CompositeFilter) {
                return false;
            }
        }
        return true;
    }

    public CompositeFilter withAddedFilters(List<Filter> list) {
        ArrayList arrayList = new ArrayList(this.filters);
        arrayList.addAll(list);
        return new CompositeFilter(arrayList, this.operator);
    }

    private FieldFilter findFirstMatchingFilter(Function<FieldFilter, Boolean> function) {
        for (FieldFilter fieldFilter : getFlattenedFilters()) {
            if (function.apply(fieldFilter).booleanValue()) {
                return fieldFilter;
            }
        }
        return null;
    }

    @Override // com.google.firebase.firestore.core.Filter
    public boolean matches(Document document) {
        if (isConjunction()) {
            Iterator<Filter> it = this.filters.iterator();
            while (it.hasNext()) {
                if (!it.next().matches(document)) {
                    return false;
                }
            }
            return true;
        }
        Iterator<Filter> it2 = this.filters.iterator();
        while (it2.hasNext()) {
            if (it2.next().matches(document)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.firebase.firestore.core.Filter
    public String getCanonicalId() {
        StringBuilder sb = new StringBuilder();
        if (isFlatConjunction()) {
            Iterator<Filter> it = this.filters.iterator();
            while (it.hasNext()) {
                sb.append(it.next().getCanonicalId());
            }
            return sb.toString();
        }
        sb.append(this.operator.toString() + "(");
        sb.append(TextUtils.join(Constants.SEPARATOR_COMMA, this.filters));
        sb.append(")");
        return sb.toString();
    }

    public String toString() {
        return getCanonicalId();
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof CompositeFilter)) {
            return false;
        }
        CompositeFilter compositeFilter = (CompositeFilter) obj;
        return this.operator == compositeFilter.operator && this.filters.equals(compositeFilter.filters);
    }

    public int hashCode() {
        return ((1147 + this.operator.hashCode()) * 31) + this.filters.hashCode();
    }
}
