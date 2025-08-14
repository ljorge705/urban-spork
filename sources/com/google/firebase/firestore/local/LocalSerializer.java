package com.google.firebase.firestore.local;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.core.Query;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.FieldIndex;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.ObjectValue;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.model.mutation.Mutation;
import com.google.firebase.firestore.model.mutation.MutationBatch;
import com.google.firebase.firestore.proto.MaybeDocument;
import com.google.firebase.firestore.proto.NoDocument;
import com.google.firebase.firestore.proto.Target;
import com.google.firebase.firestore.proto.UnknownDocument;
import com.google.firebase.firestore.proto.WriteBatch;
import com.google.firebase.firestore.remote.RemoteSerializer;
import com.google.firebase.firestore.util.Assert;
import com.google.firestore.admin.v1.Index;
import com.google.firestore.bundle.BundledQuery;
import com.google.firestore.v1.Document;
import com.google.firestore.v1.DocumentTransform;
import com.google.firestore.v1.Target;
import com.google.firestore.v1.Write;
import com.google.protobuf.ByteString;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public final class LocalSerializer {
    private final RemoteSerializer rpcSerializer;

    public LocalSerializer(RemoteSerializer remoteSerializer) {
        this.rpcSerializer = remoteSerializer;
    }

    MaybeDocument encodeMaybeDocument(Document document) {
        MaybeDocument.Builder builderNewBuilder = MaybeDocument.newBuilder();
        if (document.isNoDocument()) {
            builderNewBuilder.setNoDocument(encodeNoDocument(document));
        } else if (document.isFoundDocument()) {
            builderNewBuilder.setDocument(encodeDocument(document));
        } else if (document.isUnknownDocument()) {
            builderNewBuilder.setUnknownDocument(encodeUnknownDocument(document));
        } else {
            throw Assert.fail("Cannot encode invalid document %s", document);
        }
        builderNewBuilder.setHasCommittedMutations(document.hasCommittedMutations());
        return builderNewBuilder.build();
    }

    MutableDocument decodeMaybeDocument(MaybeDocument maybeDocument) {
        int i = AnonymousClass1.$SwitchMap$com$google$firebase$firestore$proto$MaybeDocument$DocumentTypeCase[maybeDocument.getDocumentTypeCase().ordinal()];
        if (i == 1) {
            return decodeDocument(maybeDocument.getDocument(), maybeDocument.getHasCommittedMutations());
        }
        if (i == 2) {
            return decodeNoDocument(maybeDocument.getNoDocument(), maybeDocument.getHasCommittedMutations());
        }
        if (i == 3) {
            return decodeUnknownDocument(maybeDocument.getUnknownDocument());
        }
        throw Assert.fail("Unknown MaybeDocument %s", maybeDocument);
    }

    private com.google.firestore.v1.Document encodeDocument(Document document) {
        Document.Builder builderNewBuilder = com.google.firestore.v1.Document.newBuilder();
        builderNewBuilder.setName(this.rpcSerializer.encodeKey(document.getKey()));
        builderNewBuilder.putAllFields(document.getData().getFieldsMap());
        builderNewBuilder.setUpdateTime(this.rpcSerializer.encodeTimestamp(document.getVersion().getTimestamp()));
        return builderNewBuilder.build();
    }

    private MutableDocument decodeDocument(com.google.firestore.v1.Document document, boolean z) {
        MutableDocument mutableDocumentNewFoundDocument = MutableDocument.newFoundDocument(this.rpcSerializer.decodeKey(document.getName()), this.rpcSerializer.decodeVersion(document.getUpdateTime()), ObjectValue.fromMap(document.getFieldsMap()));
        return z ? mutableDocumentNewFoundDocument.setHasCommittedMutations() : mutableDocumentNewFoundDocument;
    }

    private NoDocument encodeNoDocument(com.google.firebase.firestore.model.Document document) {
        NoDocument.Builder builderNewBuilder = NoDocument.newBuilder();
        builderNewBuilder.setName(this.rpcSerializer.encodeKey(document.getKey()));
        builderNewBuilder.setReadTime(this.rpcSerializer.encodeTimestamp(document.getVersion().getTimestamp()));
        return builderNewBuilder.build();
    }

    private MutableDocument decodeNoDocument(NoDocument noDocument, boolean z) {
        MutableDocument mutableDocumentNewNoDocument = MutableDocument.newNoDocument(this.rpcSerializer.decodeKey(noDocument.getName()), this.rpcSerializer.decodeVersion(noDocument.getReadTime()));
        return z ? mutableDocumentNewNoDocument.setHasCommittedMutations() : mutableDocumentNewNoDocument;
    }

    private UnknownDocument encodeUnknownDocument(com.google.firebase.firestore.model.Document document) {
        UnknownDocument.Builder builderNewBuilder = UnknownDocument.newBuilder();
        builderNewBuilder.setName(this.rpcSerializer.encodeKey(document.getKey()));
        builderNewBuilder.setVersion(this.rpcSerializer.encodeTimestamp(document.getVersion().getTimestamp()));
        return builderNewBuilder.build();
    }

    private MutableDocument decodeUnknownDocument(UnknownDocument unknownDocument) {
        return MutableDocument.newUnknownDocument(this.rpcSerializer.decodeKey(unknownDocument.getName()), this.rpcSerializer.decodeVersion(unknownDocument.getVersion()));
    }

    WriteBatch encodeMutationBatch(MutationBatch mutationBatch) {
        WriteBatch.Builder builderNewBuilder = WriteBatch.newBuilder();
        builderNewBuilder.setBatchId(mutationBatch.getBatchId());
        builderNewBuilder.setLocalWriteTime(this.rpcSerializer.encodeTimestamp(mutationBatch.getLocalWriteTime()));
        Iterator<Mutation> it = mutationBatch.getBaseMutations().iterator();
        while (it.hasNext()) {
            builderNewBuilder.addBaseWrites(this.rpcSerializer.encodeMutation(it.next()));
        }
        Iterator<Mutation> it2 = mutationBatch.getMutations().iterator();
        while (it2.hasNext()) {
            builderNewBuilder.addWrites(this.rpcSerializer.encodeMutation(it2.next()));
        }
        return builderNewBuilder.build();
    }

    MutationBatch decodeMutationBatch(WriteBatch writeBatch) {
        int batchId = writeBatch.getBatchId();
        Timestamp timestampDecodeTimestamp = this.rpcSerializer.decodeTimestamp(writeBatch.getLocalWriteTime());
        int baseWritesCount = writeBatch.getBaseWritesCount();
        ArrayList arrayList = new ArrayList(baseWritesCount);
        for (int i = 0; i < baseWritesCount; i++) {
            arrayList.add(this.rpcSerializer.decodeMutation(writeBatch.getBaseWrites(i)));
        }
        ArrayList arrayList2 = new ArrayList(writeBatch.getWritesCount());
        int i2 = 0;
        while (i2 < writeBatch.getWritesCount()) {
            Write writes = writeBatch.getWrites(i2);
            int i3 = i2 + 1;
            if (i3 < writeBatch.getWritesCount() && writeBatch.getWrites(i3).hasTransform()) {
                Assert.hardAssert(writeBatch.getWrites(i2).hasUpdate(), "TransformMutation should be preceded by a patch or set mutation", new Object[0]);
                Write.Builder builderNewBuilder = Write.newBuilder(writes);
                Iterator<DocumentTransform.FieldTransform> it = writeBatch.getWrites(i3).getTransform().getFieldTransformsList().iterator();
                while (it.hasNext()) {
                    builderNewBuilder.addUpdateTransforms(it.next());
                }
                arrayList2.add(this.rpcSerializer.decodeMutation(builderNewBuilder.build()));
                i2 = i3;
            } else {
                arrayList2.add(this.rpcSerializer.decodeMutation(writes));
            }
            i2++;
        }
        return new MutationBatch(batchId, timestampDecodeTimestamp, arrayList, arrayList2);
    }

    Target encodeTargetData(TargetData targetData) {
        Assert.hardAssert(QueryPurpose.LISTEN.equals(targetData.getPurpose()), "Only queries with purpose %s may be stored, got %s", QueryPurpose.LISTEN, targetData.getPurpose());
        Target.Builder builderNewBuilder = Target.newBuilder();
        builderNewBuilder.setTargetId(targetData.getTargetId()).setLastListenSequenceNumber(targetData.getSequenceNumber()).setLastLimboFreeSnapshotVersion(this.rpcSerializer.encodeVersion(targetData.getLastLimboFreeSnapshotVersion())).setSnapshotVersion(this.rpcSerializer.encodeVersion(targetData.getSnapshotVersion())).setResumeToken(targetData.getResumeToken());
        com.google.firebase.firestore.core.Target target = targetData.getTarget();
        if (target.isDocumentQuery()) {
            builderNewBuilder.setDocuments(this.rpcSerializer.encodeDocumentsTarget(target));
        } else {
            builderNewBuilder.setQuery(this.rpcSerializer.encodeQueryTarget(target));
        }
        return builderNewBuilder.build();
    }

    TargetData decodeTargetData(Target target) {
        com.google.firebase.firestore.core.Target targetDecodeDocumentsTarget;
        int targetId = target.getTargetId();
        SnapshotVersion snapshotVersionDecodeVersion = this.rpcSerializer.decodeVersion(target.getSnapshotVersion());
        SnapshotVersion snapshotVersionDecodeVersion2 = this.rpcSerializer.decodeVersion(target.getLastLimboFreeSnapshotVersion());
        ByteString resumeToken = target.getResumeToken();
        long lastListenSequenceNumber = target.getLastListenSequenceNumber();
        int i = AnonymousClass1.$SwitchMap$com$google$firebase$firestore$proto$Target$TargetTypeCase[target.getTargetTypeCase().ordinal()];
        if (i == 1) {
            targetDecodeDocumentsTarget = this.rpcSerializer.decodeDocumentsTarget(target.getDocuments());
        } else if (i == 2) {
            targetDecodeDocumentsTarget = this.rpcSerializer.decodeQueryTarget(target.getQuery());
        } else {
            throw Assert.fail("Unknown targetType %d", target.getTargetTypeCase());
        }
        return new TargetData(targetDecodeDocumentsTarget, targetId, lastListenSequenceNumber, QueryPurpose.LISTEN, snapshotVersionDecodeVersion, snapshotVersionDecodeVersion2, resumeToken, null);
    }

    /* renamed from: com.google.firebase.firestore.local.LocalSerializer$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$firestore$proto$MaybeDocument$DocumentTypeCase;
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$firestore$proto$Target$TargetTypeCase;

        static {
            int[] iArr = new int[Target.TargetTypeCase.values().length];
            $SwitchMap$com$google$firebase$firestore$proto$Target$TargetTypeCase = iArr;
            try {
                iArr[Target.TargetTypeCase.DOCUMENTS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$proto$Target$TargetTypeCase[Target.TargetTypeCase.QUERY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[MaybeDocument.DocumentTypeCase.values().length];
            $SwitchMap$com$google$firebase$firestore$proto$MaybeDocument$DocumentTypeCase = iArr2;
            try {
                iArr2[MaybeDocument.DocumentTypeCase.DOCUMENT.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$proto$MaybeDocument$DocumentTypeCase[MaybeDocument.DocumentTypeCase.NO_DOCUMENT.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$proto$MaybeDocument$DocumentTypeCase[MaybeDocument.DocumentTypeCase.UNKNOWN_DOCUMENT.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public BundledQuery encodeBundledQuery(com.google.firebase.firestore.bundle.BundledQuery bundledQuery) {
        BundledQuery.LimitType limitType;
        Target.QueryTarget queryTargetEncodeQueryTarget = this.rpcSerializer.encodeQueryTarget(bundledQuery.getTarget());
        BundledQuery.Builder builderNewBuilder = BundledQuery.newBuilder();
        if (bundledQuery.getLimitType().equals(Query.LimitType.LIMIT_TO_FIRST)) {
            limitType = BundledQuery.LimitType.FIRST;
        } else {
            limitType = BundledQuery.LimitType.LAST;
        }
        builderNewBuilder.setLimitType(limitType);
        builderNewBuilder.setParent(queryTargetEncodeQueryTarget.getParent());
        builderNewBuilder.setStructuredQuery(queryTargetEncodeQueryTarget.getStructuredQuery());
        return builderNewBuilder.build();
    }

    public com.google.firebase.firestore.bundle.BundledQuery decodeBundledQuery(BundledQuery bundledQuery) {
        Query.LimitType limitType;
        if (bundledQuery.getLimitType().equals(BundledQuery.LimitType.FIRST)) {
            limitType = Query.LimitType.LIMIT_TO_FIRST;
        } else {
            limitType = Query.LimitType.LIMIT_TO_LAST;
        }
        return new com.google.firebase.firestore.bundle.BundledQuery(this.rpcSerializer.decodeQueryTarget(bundledQuery.getParent(), bundledQuery.getStructuredQuery()), limitType);
    }

    public Index encodeFieldIndexSegments(List<FieldIndex.Segment> list) {
        Index.Builder builderNewBuilder = Index.newBuilder();
        builderNewBuilder.setQueryScope(Index.QueryScope.COLLECTION_GROUP);
        for (FieldIndex.Segment segment : list) {
            Index.IndexField.Builder builderNewBuilder2 = Index.IndexField.newBuilder();
            builderNewBuilder2.setFieldPath(segment.getFieldPath().canonicalString());
            if (segment.getKind() == FieldIndex.Segment.Kind.CONTAINS) {
                builderNewBuilder2.setArrayConfig(Index.IndexField.ArrayConfig.CONTAINS);
            } else if (segment.getKind() == FieldIndex.Segment.Kind.ASCENDING) {
                builderNewBuilder2.setOrder(Index.IndexField.Order.ASCENDING);
            } else {
                builderNewBuilder2.setOrder(Index.IndexField.Order.DESCENDING);
            }
            builderNewBuilder.addFields(builderNewBuilder2);
        }
        return builderNewBuilder.build();
    }

    public List<FieldIndex.Segment> decodeFieldIndexSegments(Index index) {
        FieldIndex.Segment.Kind kind;
        ArrayList arrayList = new ArrayList();
        for (Index.IndexField indexField : index.getFieldsList()) {
            FieldPath fieldPathFromServerFormat = FieldPath.fromServerFormat(indexField.getFieldPath());
            if (indexField.getValueModeCase().equals(Index.IndexField.ValueModeCase.ARRAY_CONFIG)) {
                kind = FieldIndex.Segment.Kind.CONTAINS;
            } else if (indexField.getOrder().equals(Index.IndexField.Order.ASCENDING)) {
                kind = FieldIndex.Segment.Kind.ASCENDING;
            } else {
                kind = FieldIndex.Segment.Kind.DESCENDING;
            }
            arrayList.add(FieldIndex.Segment.create(fieldPathFromServerFormat, kind));
        }
        return arrayList;
    }

    public Mutation decodeMutation(Write write) {
        return this.rpcSerializer.decodeMutation(write);
    }

    public Write encodeMutation(Mutation mutation) {
        return this.rpcSerializer.encodeMutation(mutation);
    }
}
