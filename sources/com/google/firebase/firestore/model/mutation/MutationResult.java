package com.google.firebase.firestore.model.mutation;

import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.util.Preconditions;
import com.google.firestore.v1.Value;
import java.util.List;

/* loaded from: classes2.dex */
public final class MutationResult {
    private final List<Value> transformResults;
    private final SnapshotVersion version;

    public List<Value> getTransformResults() {
        return this.transformResults;
    }

    public SnapshotVersion getVersion() {
        return this.version;
    }

    public MutationResult(SnapshotVersion snapshotVersion, List<Value> list) {
        this.version = (SnapshotVersion) Preconditions.checkNotNull(snapshotVersion);
        this.transformResults = list;
    }
}
