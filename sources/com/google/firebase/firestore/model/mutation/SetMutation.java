package com.google.firebase.firestore.model.mutation;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.ObjectValue;
import com.google.firestore.v1.Value;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public final class SetMutation extends Mutation {
    private final ObjectValue value;

    @Override // com.google.firebase.firestore.model.mutation.Mutation
    public FieldMask getFieldMask() {
        return null;
    }

    public ObjectValue getValue() {
        return this.value;
    }

    public SetMutation(DocumentKey documentKey, ObjectValue objectValue, Precondition precondition) {
        this(documentKey, objectValue, precondition, new ArrayList());
    }

    public SetMutation(DocumentKey documentKey, ObjectValue objectValue, Precondition precondition, List<FieldTransform> list) {
        super(documentKey, precondition, list);
        this.value = objectValue;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SetMutation setMutation = (SetMutation) obj;
        return hasSameKeyAndPrecondition(setMutation) && this.value.equals(setMutation.value) && getFieldTransforms().equals(setMutation.getFieldTransforms());
    }

    public int hashCode() {
        return (keyAndPreconditionHashCode() * 31) + this.value.hashCode();
    }

    public String toString() {
        return "SetMutation{" + keyAndPreconditionToString() + ", value=" + this.value + "}";
    }

    @Override // com.google.firebase.firestore.model.mutation.Mutation
    public void applyToRemoteDocument(MutableDocument mutableDocument, MutationResult mutationResult) {
        verifyKeyMatches(mutableDocument);
        ObjectValue objectValueM5329clone = this.value.m5329clone();
        objectValueM5329clone.setAll(serverTransformResults(mutableDocument, mutationResult.getTransformResults()));
        mutableDocument.convertToFoundDocument(mutationResult.getVersion(), objectValueM5329clone).setHasCommittedMutations();
    }

    @Override // com.google.firebase.firestore.model.mutation.Mutation
    public FieldMask applyToLocalView(MutableDocument mutableDocument, FieldMask fieldMask, Timestamp timestamp) {
        verifyKeyMatches(mutableDocument);
        if (!getPrecondition().isValidFor(mutableDocument)) {
            return fieldMask;
        }
        Map<FieldPath, Value> mapLocalTransformResults = localTransformResults(timestamp, mutableDocument);
        ObjectValue objectValueM5329clone = this.value.m5329clone();
        objectValueM5329clone.setAll(mapLocalTransformResults);
        mutableDocument.convertToFoundDocument(mutableDocument.getVersion(), objectValueM5329clone).setHasLocalMutations();
        return null;
    }
}
