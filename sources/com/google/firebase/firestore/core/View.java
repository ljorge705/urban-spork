package com.google.firebase.firestore.core;

import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.core.DocumentViewChange;
import com.google.firebase.firestore.core.LimboDocumentChange;
import com.google.firebase.firestore.core.ViewSnapshot;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.DocumentSet;
import com.google.firebase.firestore.remote.TargetChange;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Util;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class View {
    private boolean current;
    private DocumentSet documentSet;
    private final Query query;
    private ImmutableSortedSet<DocumentKey> syncedDocuments;
    private ViewSnapshot.SyncState syncState = ViewSnapshot.SyncState.NONE;
    private ImmutableSortedSet<DocumentKey> limboDocuments = DocumentKey.emptyKeySet();
    private ImmutableSortedSet<DocumentKey> mutatedKeys = DocumentKey.emptyKeySet();

    ImmutableSortedSet<DocumentKey> getLimboDocuments() {
        return this.limboDocuments;
    }

    public ViewSnapshot.SyncState getSyncState() {
        return this.syncState;
    }

    ImmutableSortedSet<DocumentKey> getSyncedDocuments() {
        return this.syncedDocuments;
    }

    public static class DocumentChanges {
        final DocumentViewChangeSet changeSet;
        final DocumentSet documentSet;
        final ImmutableSortedSet<DocumentKey> mutatedKeys;
        private final boolean needsRefill;

        public boolean needsRefill() {
            return this.needsRefill;
        }

        /* synthetic */ DocumentChanges(DocumentSet documentSet, DocumentViewChangeSet documentViewChangeSet, ImmutableSortedSet immutableSortedSet, boolean z, AnonymousClass1 anonymousClass1) {
            this(documentSet, documentViewChangeSet, immutableSortedSet, z);
        }

        private DocumentChanges(DocumentSet documentSet, DocumentViewChangeSet documentViewChangeSet, ImmutableSortedSet<DocumentKey> immutableSortedSet, boolean z) {
            this.documentSet = documentSet;
            this.changeSet = documentViewChangeSet;
            this.mutatedKeys = immutableSortedSet;
            this.needsRefill = z;
        }
    }

    public View(Query query, ImmutableSortedSet<DocumentKey> immutableSortedSet) {
        this.query = query;
        this.documentSet = DocumentSet.emptySet(query.comparator());
        this.syncedDocuments = immutableSortedSet;
    }

    public DocumentChanges computeDocChanges(ImmutableSortedMap<DocumentKey, Document> immutableSortedMap) {
        return computeDocChanges(immutableSortedMap, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:72:0x0133 A[PHI: r17
      0x0133: PHI (r17v4 com.google.firebase.firestore.model.DocumentSet) = 
      (r17v0 com.google.firebase.firestore.model.DocumentSet)
      (r17v0 com.google.firebase.firestore.model.DocumentSet)
      (r17v5 com.google.firebase.firestore.model.DocumentSet)
      (r17v5 com.google.firebase.firestore.model.DocumentSet)
     binds: [B:70:0x012f, B:71:0x0131, B:58:0x0102, B:55:0x00f4] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.google.firebase.firestore.core.View.DocumentChanges computeDocChanges(com.google.firebase.database.collection.ImmutableSortedMap<com.google.firebase.firestore.model.DocumentKey, com.google.firebase.firestore.model.Document> r19, com.google.firebase.firestore.core.View.DocumentChanges r20) {
        /*
            Method dump skipped, instructions count: 459
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.firestore.core.View.computeDocChanges(com.google.firebase.database.collection.ImmutableSortedMap, com.google.firebase.firestore.core.View$DocumentChanges):com.google.firebase.firestore.core.View$DocumentChanges");
    }

    private boolean shouldWaitForSyncedDocument(Document document, Document document2) {
        return document.hasLocalMutations() && document2.hasCommittedMutations() && !document2.hasLocalMutations();
    }

    public ViewChange applyChanges(DocumentChanges documentChanges) {
        return applyChanges(documentChanges, null);
    }

    public ViewChange applyChanges(DocumentChanges documentChanges, TargetChange targetChange) {
        ViewSnapshot viewSnapshot;
        Assert.hardAssert(!documentChanges.needsRefill, "Cannot apply changes that need a refill", new Object[0]);
        DocumentSet documentSet = this.documentSet;
        this.documentSet = documentChanges.documentSet;
        this.mutatedKeys = documentChanges.mutatedKeys;
        List<DocumentViewChange> changes = documentChanges.changeSet.getChanges();
        Collections.sort(changes, new Comparator() { // from class: com.google.firebase.firestore.core.View$$ExternalSyntheticLambda0
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return this.f$0.m5268lambda$applyChanges$0$comgooglefirebasefirestorecoreView((DocumentViewChange) obj, (DocumentViewChange) obj2);
            }
        });
        applyTargetChange(targetChange);
        List<LimboDocumentChange> listUpdateLimboDocuments = updateLimboDocuments();
        ViewSnapshot.SyncState syncState = (this.limboDocuments.size() == 0 && this.current) ? ViewSnapshot.SyncState.SYNCED : ViewSnapshot.SyncState.LOCAL;
        boolean z = syncState != this.syncState;
        this.syncState = syncState;
        if (changes.size() != 0 || z) {
            viewSnapshot = new ViewSnapshot(this.query, documentChanges.documentSet, documentSet, changes, syncState == ViewSnapshot.SyncState.LOCAL, documentChanges.mutatedKeys, z, false, (targetChange == null || targetChange.getResumeToken().isEmpty()) ? false : true);
        } else {
            viewSnapshot = null;
        }
        return new ViewChange(viewSnapshot, listUpdateLimboDocuments);
    }

    /* renamed from: lambda$applyChanges$0$com-google-firebase-firestore-core-View, reason: not valid java name */
    /* synthetic */ int m5268lambda$applyChanges$0$comgooglefirebasefirestorecoreView(DocumentViewChange documentViewChange, DocumentViewChange documentViewChange2) {
        int iCompareIntegers = Util.compareIntegers(changeTypeOrder(documentViewChange), changeTypeOrder(documentViewChange2));
        documentViewChange.getType().compareTo(documentViewChange2.getType());
        return iCompareIntegers != 0 ? iCompareIntegers : this.query.comparator().compare(documentViewChange.getDocument(), documentViewChange2.getDocument());
    }

    public ViewChange applyOnlineStateChange(OnlineState onlineState) {
        if (this.current && onlineState == OnlineState.OFFLINE) {
            this.current = false;
            return applyChanges(new DocumentChanges(this.documentSet, new DocumentViewChangeSet(), this.mutatedKeys, false, null));
        }
        return new ViewChange(null, Collections.emptyList());
    }

    private void applyTargetChange(TargetChange targetChange) {
        if (targetChange != null) {
            Iterator<DocumentKey> it = targetChange.getAddedDocuments().iterator();
            while (it.hasNext()) {
                this.syncedDocuments = this.syncedDocuments.insert(it.next());
            }
            Iterator<DocumentKey> it2 = targetChange.getModifiedDocuments().iterator();
            while (it2.hasNext()) {
                DocumentKey next = it2.next();
                Assert.hardAssert(this.syncedDocuments.contains(next), "Modified document %s not found in view.", next);
            }
            Iterator<DocumentKey> it3 = targetChange.getRemovedDocuments().iterator();
            while (it3.hasNext()) {
                this.syncedDocuments = this.syncedDocuments.remove(it3.next());
            }
            this.current = targetChange.isCurrent();
        }
    }

    private List<LimboDocumentChange> updateLimboDocuments() {
        if (!this.current) {
            return Collections.emptyList();
        }
        ImmutableSortedSet<DocumentKey> immutableSortedSet = this.limboDocuments;
        this.limboDocuments = DocumentKey.emptyKeySet();
        Iterator<Document> it = this.documentSet.iterator();
        while (it.hasNext()) {
            Document next = it.next();
            if (shouldBeLimboDoc(next.getKey())) {
                this.limboDocuments = this.limboDocuments.insert(next.getKey());
            }
        }
        ArrayList arrayList = new ArrayList(immutableSortedSet.size() + this.limboDocuments.size());
        Iterator<DocumentKey> it2 = immutableSortedSet.iterator();
        while (it2.hasNext()) {
            DocumentKey next2 = it2.next();
            if (!this.limboDocuments.contains(next2)) {
                arrayList.add(new LimboDocumentChange(LimboDocumentChange.Type.REMOVED, next2));
            }
        }
        Iterator<DocumentKey> it3 = this.limboDocuments.iterator();
        while (it3.hasNext()) {
            DocumentKey next3 = it3.next();
            if (!immutableSortedSet.contains(next3)) {
                arrayList.add(new LimboDocumentChange(LimboDocumentChange.Type.ADDED, next3));
            }
        }
        return arrayList;
    }

    private boolean shouldBeLimboDoc(DocumentKey documentKey) {
        Document document;
        return (this.syncedDocuments.contains(documentKey) || (document = this.documentSet.getDocument(documentKey)) == null || document.hasLocalMutations()) ? false : true;
    }

    /* renamed from: com.google.firebase.firestore.core.View$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$firestore$core$DocumentViewChange$Type;

        static {
            int[] iArr = new int[DocumentViewChange.Type.values().length];
            $SwitchMap$com$google$firebase$firestore$core$DocumentViewChange$Type = iArr;
            try {
                iArr[DocumentViewChange.Type.ADDED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$DocumentViewChange$Type[DocumentViewChange.Type.MODIFIED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$DocumentViewChange$Type[DocumentViewChange.Type.METADATA.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$DocumentViewChange$Type[DocumentViewChange.Type.REMOVED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private static int changeTypeOrder(DocumentViewChange documentViewChange) {
        int i = AnonymousClass1.$SwitchMap$com$google$firebase$firestore$core$DocumentViewChange$Type[documentViewChange.getType().ordinal()];
        int i2 = 1;
        if (i != 1) {
            i2 = 2;
            if (i != 2 && i != 3) {
                if (i == 4) {
                    return 0;
                }
                throw new IllegalArgumentException("Unknown change type: " + documentViewChange.getType());
            }
        }
        return i2;
    }
}
