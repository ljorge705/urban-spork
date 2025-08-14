package com.google.firebase.firestore.remote;

import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.util.Assert;
import com.google.protobuf.ByteString;
import io.grpc.Status;
import java.util.List;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* loaded from: classes2.dex */
public abstract class WatchChange {

    public enum WatchTargetChangeType {
        NoChange,
        Added,
        Removed,
        Current,
        Reset
    }

    private WatchChange() {
    }

    public static final class DocumentChange extends WatchChange {
        private final DocumentKey documentKey;
        private final MutableDocument newDocument;
        private final List<Integer> removedTargetIds;
        private final List<Integer> updatedTargetIds;

        public DocumentKey getDocumentKey() {
            return this.documentKey;
        }

        public MutableDocument getNewDocument() {
            return this.newDocument;
        }

        public List<Integer> getRemovedTargetIds() {
            return this.removedTargetIds;
        }

        public List<Integer> getUpdatedTargetIds() {
            return this.updatedTargetIds;
        }

        public DocumentChange(List<Integer> list, List<Integer> list2, DocumentKey documentKey, MutableDocument mutableDocument) {
            super();
            this.updatedTargetIds = list;
            this.removedTargetIds = list2;
            this.documentKey = documentKey;
            this.newDocument = mutableDocument;
        }

        public String toString() {
            return "DocumentChange{updatedTargetIds=" + this.updatedTargetIds + ", removedTargetIds=" + this.removedTargetIds + ", key=" + this.documentKey + ", newDocument=" + this.newDocument + AbstractJsonLexerKt.END_OBJ;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            DocumentChange documentChange = (DocumentChange) obj;
            if (!this.updatedTargetIds.equals(documentChange.updatedTargetIds) || !this.removedTargetIds.equals(documentChange.removedTargetIds) || !this.documentKey.equals(documentChange.documentKey)) {
                return false;
            }
            MutableDocument mutableDocument = this.newDocument;
            MutableDocument mutableDocument2 = documentChange.newDocument;
            return mutableDocument != null ? mutableDocument.equals(mutableDocument2) : mutableDocument2 == null;
        }

        public int hashCode() {
            int iHashCode = ((((this.updatedTargetIds.hashCode() * 31) + this.removedTargetIds.hashCode()) * 31) + this.documentKey.hashCode()) * 31;
            MutableDocument mutableDocument = this.newDocument;
            return iHashCode + (mutableDocument != null ? mutableDocument.hashCode() : 0);
        }
    }

    public static final class ExistenceFilterWatchChange extends WatchChange {
        private final ExistenceFilter existenceFilter;
        private final int targetId;

        public ExistenceFilter getExistenceFilter() {
            return this.existenceFilter;
        }

        public int getTargetId() {
            return this.targetId;
        }

        public ExistenceFilterWatchChange(int i, ExistenceFilter existenceFilter) {
            super();
            this.targetId = i;
            this.existenceFilter = existenceFilter;
        }

        public String toString() {
            return "ExistenceFilterWatchChange{targetId=" + this.targetId + ", existenceFilter=" + this.existenceFilter + AbstractJsonLexerKt.END_OBJ;
        }
    }

    public static final class WatchTargetChange extends WatchChange {
        private final Status cause;
        private final WatchTargetChangeType changeType;
        private final ByteString resumeToken;
        private final List<Integer> targetIds;

        public Status getCause() {
            return this.cause;
        }

        public WatchTargetChangeType getChangeType() {
            return this.changeType;
        }

        public ByteString getResumeToken() {
            return this.resumeToken;
        }

        public List<Integer> getTargetIds() {
            return this.targetIds;
        }

        public WatchTargetChange(WatchTargetChangeType watchTargetChangeType, List<Integer> list) {
            this(watchTargetChangeType, list, WatchStream.EMPTY_RESUME_TOKEN, null);
        }

        public WatchTargetChange(WatchTargetChangeType watchTargetChangeType, List<Integer> list, ByteString byteString) {
            this(watchTargetChangeType, list, byteString, null);
        }

        public WatchTargetChange(WatchTargetChangeType watchTargetChangeType, List<Integer> list, ByteString byteString, Status status) {
            super();
            Assert.hardAssert(status == null || watchTargetChangeType == WatchTargetChangeType.Removed, "Got cause for a target change that was not a removal", new Object[0]);
            this.changeType = watchTargetChangeType;
            this.targetIds = list;
            this.resumeToken = byteString;
            if (status == null || status.isOk()) {
                this.cause = null;
            } else {
                this.cause = status;
            }
        }

        public String toString() {
            return "WatchTargetChange{changeType=" + this.changeType + ", targetIds=" + this.targetIds + AbstractJsonLexerKt.END_OBJ;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            WatchTargetChange watchTargetChange = (WatchTargetChange) obj;
            if (this.changeType != watchTargetChange.changeType || !this.targetIds.equals(watchTargetChange.targetIds) || !this.resumeToken.equals(watchTargetChange.resumeToken)) {
                return false;
            }
            Status status = this.cause;
            return status != null ? watchTargetChange.cause != null && status.getCode().equals(watchTargetChange.cause.getCode()) : watchTargetChange.cause == null;
        }

        public int hashCode() {
            int iHashCode = ((((this.changeType.hashCode() * 31) + this.targetIds.hashCode()) * 31) + this.resumeToken.hashCode()) * 31;
            Status status = this.cause;
            return iHashCode + (status != null ? status.getCode().hashCode() : 0);
        }
    }
}
