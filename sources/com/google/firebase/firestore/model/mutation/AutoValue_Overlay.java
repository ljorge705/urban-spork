package com.google.firebase.firestore.model.mutation;

/* loaded from: classes2.dex */
final class AutoValue_Overlay extends Overlay {
    private final int largestBatchId;
    private final Mutation mutation;

    @Override // com.google.firebase.firestore.model.mutation.Overlay
    public int getLargestBatchId() {
        return this.largestBatchId;
    }

    @Override // com.google.firebase.firestore.model.mutation.Overlay
    public Mutation getMutation() {
        return this.mutation;
    }

    AutoValue_Overlay(int i, Mutation mutation) {
        this.largestBatchId = i;
        if (mutation == null) {
            throw new NullPointerException("Null mutation");
        }
        this.mutation = mutation;
    }

    public String toString() {
        return "Overlay{largestBatchId=" + this.largestBatchId + ", mutation=" + this.mutation + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Overlay)) {
            return false;
        }
        Overlay overlay = (Overlay) obj;
        return this.largestBatchId == overlay.getLargestBatchId() && this.mutation.equals(overlay.getMutation());
    }

    public int hashCode() {
        return ((this.largestBatchId ^ 1000003) * 1000003) ^ this.mutation.hashCode();
    }
}
