package com.google.firebase.firestore;

import io.sentry.protocol.MetricSummary;
import java.util.Objects;

/* loaded from: classes2.dex */
public abstract class AggregateField {
    private final String alias;
    private final FieldPath fieldPath;
    private final String operator;

    public String getAlias() {
        return this.alias;
    }

    public String getOperator() {
        return this.operator;
    }

    private AggregateField(FieldPath fieldPath, String str) {
        this.fieldPath = fieldPath;
        this.operator = str;
        this.alias = str + (fieldPath == null ? "" : "_" + fieldPath);
    }

    public String getFieldPath() {
        FieldPath fieldPath = this.fieldPath;
        return fieldPath == null ? "" : fieldPath.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AggregateField)) {
            return false;
        }
        AggregateField aggregateField = (AggregateField) obj;
        FieldPath fieldPath = this.fieldPath;
        return (fieldPath == null || aggregateField.fieldPath == null) ? fieldPath == null && aggregateField.fieldPath == null : this.operator.equals(aggregateField.getOperator()) && getFieldPath().equals(aggregateField.getFieldPath());
    }

    public int hashCode() {
        return Objects.hash(getOperator(), getFieldPath());
    }

    public static CountAggregateField count() {
        return new CountAggregateField();
    }

    public static SumAggregateField sum(String str) {
        return new SumAggregateField(FieldPath.fromDotSeparatedPath(str));
    }

    public static SumAggregateField sum(FieldPath fieldPath) {
        return new SumAggregateField(fieldPath);
    }

    public static AverageAggregateField average(String str) {
        return new AverageAggregateField(FieldPath.fromDotSeparatedPath(str));
    }

    public static AverageAggregateField average(FieldPath fieldPath) {
        return new AverageAggregateField(fieldPath);
    }

    public static class CountAggregateField extends AggregateField {
        /* JADX WARN: Multi-variable type inference failed */
        private CountAggregateField() {
            super(null, "count");
        }
    }

    public static class SumAggregateField extends AggregateField {
        private SumAggregateField(FieldPath fieldPath) {
            super(fieldPath, MetricSummary.JsonKeys.SUM);
        }
    }

    public static class AverageAggregateField extends AggregateField {
        private AverageAggregateField(FieldPath fieldPath) {
            super(fieldPath, "average");
        }
    }
}
