package com.google.firebase.firestore.core;

import com.google.firebase.firestore.core.FieldFilter;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.model.Values;
import com.google.firebase.firestore.util.Assert;
import com.google.firestore.v1.Value;
import java.util.Iterator;

/* loaded from: classes2.dex */
public class ArrayContainsAnyFilter extends FieldFilter {
    ArrayContainsAnyFilter(FieldPath fieldPath, Value value) {
        super(fieldPath, FieldFilter.Operator.ARRAY_CONTAINS_ANY, value);
        Assert.hardAssert(Values.isArray(value), "ArrayContainsAnyFilter expects an ArrayValue", new Object[0]);
    }

    @Override // com.google.firebase.firestore.core.FieldFilter, com.google.firebase.firestore.core.Filter
    public boolean matches(Document document) {
        Value field = document.getField(getField());
        if (!Values.isArray(field)) {
            return false;
        }
        Iterator<Value> it = field.getArrayValue().getValuesList().iterator();
        while (it.hasNext()) {
            if (Values.contains(getValue().getArrayValue(), it.next())) {
                return true;
            }
        }
        return false;
    }
}
