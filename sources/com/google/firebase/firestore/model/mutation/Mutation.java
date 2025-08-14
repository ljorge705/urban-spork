package com.google.firebase.firestore.model.mutation;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.ObjectValue;
import com.google.firebase.firestore.util.Assert;
import com.google.firestore.v1.Value;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public abstract class Mutation {
    private final List<FieldTransform> fieldTransforms;
    private final DocumentKey key;
    private final Precondition precondition;

    public abstract FieldMask applyToLocalView(MutableDocument mutableDocument, FieldMask fieldMask, Timestamp timestamp);

    public abstract void applyToRemoteDocument(MutableDocument mutableDocument, MutationResult mutationResult);

    public abstract FieldMask getFieldMask();

    public List<FieldTransform> getFieldTransforms() {
        return this.fieldTransforms;
    }

    public DocumentKey getKey() {
        return this.key;
    }

    public Precondition getPrecondition() {
        return this.precondition;
    }

    Mutation(DocumentKey documentKey, Precondition precondition) {
        this(documentKey, precondition, new ArrayList());
    }

    Mutation(DocumentKey documentKey, Precondition precondition, List<FieldTransform> list) {
        this.key = documentKey;
        this.precondition = precondition;
        this.fieldTransforms = list;
    }

    public static Mutation calculateOverlayMutation(MutableDocument mutableDocument, FieldMask fieldMask) {
        if (!mutableDocument.hasLocalMutations()) {
            return null;
        }
        if (fieldMask != null && fieldMask.getMask().isEmpty()) {
            return null;
        }
        if (fieldMask == null) {
            if (mutableDocument.isNoDocument()) {
                return new DeleteMutation(mutableDocument.getKey(), Precondition.NONE);
            }
            return new SetMutation(mutableDocument.getKey(), mutableDocument.getData(), Precondition.NONE);
        }
        ObjectValue data = mutableDocument.getData();
        ObjectValue objectValue = new ObjectValue();
        HashSet hashSet = new HashSet();
        for (FieldPath fieldPathPopLast : fieldMask.getMask()) {
            if (!hashSet.contains(fieldPathPopLast)) {
                if (data.get(fieldPathPopLast) == null && fieldPathPopLast.length() > 1) {
                    fieldPathPopLast = fieldPathPopLast.popLast();
                }
                objectValue.set(fieldPathPopLast, data.get(fieldPathPopLast));
                hashSet.add(fieldPathPopLast);
            }
        }
        return new PatchMutation(mutableDocument.getKey(), objectValue, FieldMask.fromSet(hashSet), Precondition.NONE);
    }

    boolean hasSameKeyAndPrecondition(Mutation mutation) {
        return this.key.equals(mutation.key) && this.precondition.equals(mutation.precondition);
    }

    int keyAndPreconditionHashCode() {
        return (getKey().hashCode() * 31) + this.precondition.hashCode();
    }

    String keyAndPreconditionToString() {
        return "key=" + this.key + ", precondition=" + this.precondition;
    }

    void verifyKeyMatches(MutableDocument mutableDocument) {
        Assert.hardAssert(mutableDocument.getKey().equals(getKey()), "Can only apply a mutation to a document with the same key", new Object[0]);
    }

    protected Map<FieldPath, Value> serverTransformResults(MutableDocument mutableDocument, List<Value> list) {
        HashMap map = new HashMap(this.fieldTransforms.size());
        Assert.hardAssert(this.fieldTransforms.size() == list.size(), "server transform count (%d) should match field transform count (%d)", Integer.valueOf(list.size()), Integer.valueOf(this.fieldTransforms.size()));
        for (int i = 0; i < list.size(); i++) {
            FieldTransform fieldTransform = this.fieldTransforms.get(i);
            map.put(fieldTransform.getFieldPath(), fieldTransform.getOperation().applyToRemoteDocument(mutableDocument.getField(fieldTransform.getFieldPath()), list.get(i)));
        }
        return map;
    }

    protected Map<FieldPath, Value> localTransformResults(Timestamp timestamp, MutableDocument mutableDocument) {
        HashMap map = new HashMap(this.fieldTransforms.size());
        for (FieldTransform fieldTransform : this.fieldTransforms) {
            map.put(fieldTransform.getFieldPath(), fieldTransform.getOperation().applyToLocalView(mutableDocument.getField(fieldTransform.getFieldPath()), timestamp));
        }
        return map;
    }

    public ObjectValue extractTransformBaseValue(Document document) {
        ObjectValue objectValue = null;
        for (FieldTransform fieldTransform : this.fieldTransforms) {
            Value valueComputeBaseValue = fieldTransform.getOperation().computeBaseValue(document.getField(fieldTransform.getFieldPath()));
            if (valueComputeBaseValue != null) {
                if (objectValue == null) {
                    objectValue = new ObjectValue();
                }
                objectValue.set(fieldTransform.getFieldPath(), valueComputeBaseValue);
            }
        }
        return objectValue;
    }
}
