package com.google.firebase.firestore;

import java.util.Arrays;
import java.util.List;

/* loaded from: classes2.dex */
public abstract class FieldValue {
    private static final DeleteFieldValue DELETE_INSTANCE = new DeleteFieldValue();
    private static final ServerTimestampFieldValue SERVER_TIMESTAMP_INSTANCE = new ServerTimestampFieldValue();

    public static FieldValue delete() {
        return DELETE_INSTANCE;
    }

    public static FieldValue serverTimestamp() {
        return SERVER_TIMESTAMP_INSTANCE;
    }

    abstract String getMethodName();

    FieldValue() {
    }

    static class DeleteFieldValue extends FieldValue {
        @Override // com.google.firebase.firestore.FieldValue
        String getMethodName() {
            return "FieldValue.delete";
        }

        DeleteFieldValue() {
        }
    }

    static class ServerTimestampFieldValue extends FieldValue {
        @Override // com.google.firebase.firestore.FieldValue
        String getMethodName() {
            return "FieldValue.serverTimestamp";
        }

        ServerTimestampFieldValue() {
        }
    }

    static class ArrayUnionFieldValue extends FieldValue {
        private final List<Object> elements;

        List<Object> getElements() {
            return this.elements;
        }

        @Override // com.google.firebase.firestore.FieldValue
        String getMethodName() {
            return "FieldValue.arrayUnion";
        }

        ArrayUnionFieldValue(List<Object> list) {
            this.elements = list;
        }
    }

    static class ArrayRemoveFieldValue extends FieldValue {
        private final List<Object> elements;

        List<Object> getElements() {
            return this.elements;
        }

        @Override // com.google.firebase.firestore.FieldValue
        String getMethodName() {
            return "FieldValue.arrayRemove";
        }

        ArrayRemoveFieldValue(List<Object> list) {
            this.elements = list;
        }
    }

    static class NumericIncrementFieldValue extends FieldValue {
        private final Number operand;

        @Override // com.google.firebase.firestore.FieldValue
        String getMethodName() {
            return "FieldValue.increment";
        }

        Number getOperand() {
            return this.operand;
        }

        NumericIncrementFieldValue(Number number) {
            this.operand = number;
        }
    }

    public static FieldValue arrayUnion(Object... objArr) {
        return new ArrayUnionFieldValue(Arrays.asList(objArr));
    }

    public static FieldValue arrayRemove(Object... objArr) {
        return new ArrayRemoveFieldValue(Arrays.asList(objArr));
    }

    public static FieldValue increment(long j) {
        return new NumericIncrementFieldValue(Long.valueOf(j));
    }

    public static FieldValue increment(double d) {
        return new NumericIncrementFieldValue(Double.valueOf(d));
    }
}
