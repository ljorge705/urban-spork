package com.google.firebase.firestore.local;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.text.TextUtils;
import com.google.firebase.firestore.local.MemoryIndexManager;
import com.google.firebase.firestore.local.SQLitePersistence;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.proto.Target;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Consumer;
import com.google.firebase.firestore.util.Function;
import com.google.firebase.firestore.util.Logger;
import com.google.protobuf.InvalidProtocolBufferException;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
class SQLiteSchema {
    static final int MIGRATION_BATCH_SIZE = 100;
    static final int VERSION = 16;
    private final SQLiteDatabase db;
    private final LocalSerializer serializer;

    SQLiteSchema(SQLiteDatabase sQLiteDatabase, LocalSerializer localSerializer) {
        this.db = sQLiteDatabase;
        this.serializer = localSerializer;
    }

    void runSchemaUpgrades() {
        runSchemaUpgrades(0);
    }

    void runSchemaUpgrades(int i) {
        runSchemaUpgrades(i, 16);
    }

    void runSchemaUpgrades(int i, int i2) throws SQLException {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (i < 1 && i2 >= 1) {
            createV1MutationQueue();
            createV1TargetCache();
            createV1RemoteDocumentCache();
        }
        if (i < 3 && i2 >= 3 && i != 0) {
            dropV1TargetCache();
            createV1TargetCache();
        }
        if (i < 4 && i2 >= 4) {
            ensureTargetGlobal();
            addTargetCount();
        }
        if (i < 5 && i2 >= 5) {
            addSequenceNumber();
        }
        if (i < 6 && i2 >= 6) {
            removeAcknowledgedMutations();
        }
        if (i < 7 && i2 >= 7) {
            ensureSequenceNumbers();
        }
        if (i < 8 && i2 >= 8) {
            createV8CollectionParentsIndex();
        }
        if (i < 9 && i2 >= 9) {
            if (!hasReadTime()) {
                addReadTime();
            } else {
                dropLastLimboFreeSnapshotVersion();
            }
        }
        if (i == 9 && i2 >= 10) {
            dropLastLimboFreeSnapshotVersion();
        }
        if (i < 11 && i2 >= 11) {
            rewriteCanonicalIds();
        }
        if (i < 12 && i2 >= 12) {
            createBundleCache();
        }
        if (i < 13 && i2 >= 13) {
            addPathLength();
            ensurePathLength();
        }
        if (i < 14 && i2 >= 14) {
            createOverlays();
            createDataMigrationTable();
            addPendingDataMigration(Persistence.DATA_MIGRATION_BUILD_OVERLAYS);
        }
        if (i < 15 && i2 >= 15) {
            ensureReadTime();
        }
        if (i < 16 && i2 >= 16) {
            createFieldIndex();
        }
        Logger.debug("SQLiteSchema", "Migration from version %s to %s took %s milliseconds", Integer.valueOf(i), Integer.valueOf(i2), Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
    }

    private void ifTablesDontExist(String[] strArr, Runnable runnable) {
        String str;
        String str2 = "[" + TextUtils.join(", ", strArr) + "]";
        boolean z = false;
        for (int i = 0; i < strArr.length; i++) {
            String str3 = strArr[i];
            boolean zTableExists = tableExists(str3);
            if (i == 0) {
                z = zTableExists;
            } else if (zTableExists != z) {
                String str4 = "Expected all of " + str2 + " to either exist or not, but ";
                if (z) {
                    str = str4 + strArr[0] + " exists and " + str3 + " does not";
                } else {
                    str = str4 + strArr[0] + " does not exist and " + str3 + " does";
                }
                throw new IllegalStateException(str);
            }
        }
        if (!z) {
            runnable.run();
        } else {
            Logger.debug("SQLiteSchema", "Skipping migration because all of " + str2 + " already exist", new Object[0]);
        }
    }

    private void createV1MutationQueue() {
        ifTablesDontExist(new String[]{"mutation_queues", "mutations", "document_mutations"}, new Runnable() { // from class: com.google.firebase.firestore.local.SQLiteSchema$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() throws SQLException {
                this.f$0.m5317xc194b013();
            }
        });
    }

    /* renamed from: lambda$createV1MutationQueue$0$com-google-firebase-firestore-local-SQLiteSchema, reason: not valid java name */
    /* synthetic */ void m5317xc194b013() throws SQLException {
        this.db.execSQL("CREATE TABLE mutation_queues (uid TEXT PRIMARY KEY, last_acknowledged_batch_id INTEGER, last_stream_token BLOB)");
        this.db.execSQL("CREATE TABLE mutations (uid TEXT, batch_id INTEGER, mutations BLOB, PRIMARY KEY (uid, batch_id))");
        this.db.execSQL("CREATE TABLE document_mutations (uid TEXT, path TEXT, batch_id INTEGER, PRIMARY KEY (uid, path, batch_id))");
    }

    private void removeAcknowledgedMutations() {
        new SQLitePersistence.Query(this.db, "SELECT uid, last_acknowledged_batch_id FROM mutation_queues").forEach(new Consumer() { // from class: com.google.firebase.firestore.local.SQLiteSchema$$ExternalSyntheticLambda15
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                this.f$0.m5323x4d5efda4((Cursor) obj);
            }
        });
    }

    /* renamed from: lambda$removeAcknowledgedMutations$2$com-google-firebase-firestore-local-SQLiteSchema, reason: not valid java name */
    /* synthetic */ void m5323x4d5efda4(Cursor cursor) {
        final String string = cursor.getString(0);
        new SQLitePersistence.Query(this.db, "SELECT batch_id FROM mutations WHERE uid = ? AND batch_id <= ?").binding(string, Long.valueOf(cursor.getLong(1))).forEach(new Consumer() { // from class: com.google.firebase.firestore.local.SQLiteSchema$$ExternalSyntheticLambda0
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) throws SQLException {
                this.f$0.m5322x5bb55785(string, (Cursor) obj);
            }
        });
    }

    /* renamed from: lambda$removeAcknowledgedMutations$1$com-google-firebase-firestore-local-SQLiteSchema, reason: not valid java name */
    /* synthetic */ void m5322x5bb55785(String str, Cursor cursor) throws SQLException {
        removeMutationBatch(str, cursor.getInt(0));
    }

    private void removeMutationBatch(String str, int i) throws SQLException {
        SQLiteStatement sQLiteStatementCompileStatement = this.db.compileStatement("DELETE FROM mutations WHERE uid = ? AND batch_id = ?");
        sQLiteStatementCompileStatement.bindString(1, str);
        sQLiteStatementCompileStatement.bindLong(2, i);
        Assert.hardAssert(sQLiteStatementCompileStatement.executeUpdateDelete() != 0, "Mutation batch (%s, %d) did not exist", str, Integer.valueOf(i));
        this.db.execSQL("DELETE FROM document_mutations WHERE uid = ? AND batch_id = ?", new Object[]{str, Integer.valueOf(i)});
    }

    private void createV1TargetCache() {
        ifTablesDontExist(new String[]{"targets", "target_globals", "target_documents"}, new Runnable() { // from class: com.google.firebase.firestore.local.SQLiteSchema$$ExternalSyntheticLambda9
            @Override // java.lang.Runnable
            public final void run() throws SQLException {
                this.f$0.m5319x3db62447();
            }
        });
    }

    /* renamed from: lambda$createV1TargetCache$3$com-google-firebase-firestore-local-SQLiteSchema, reason: not valid java name */
    /* synthetic */ void m5319x3db62447() throws SQLException {
        this.db.execSQL("CREATE TABLE targets (target_id INTEGER PRIMARY KEY, canonical_id TEXT, snapshot_version_seconds INTEGER, snapshot_version_nanos INTEGER, resume_token BLOB, last_listen_sequence_number INTEGER,target_proto BLOB)");
        this.db.execSQL("CREATE INDEX query_targets ON targets (canonical_id, target_id)");
        this.db.execSQL("CREATE TABLE target_globals (highest_target_id INTEGER, highest_listen_sequence_number INTEGER, last_remote_snapshot_version_seconds INTEGER, last_remote_snapshot_version_nanos INTEGER)");
        this.db.execSQL("CREATE TABLE target_documents (target_id INTEGER, path TEXT, PRIMARY KEY (target_id, path))");
        this.db.execSQL("CREATE INDEX document_targets ON target_documents (path, target_id)");
    }

    private void dropV1TargetCache() throws SQLException {
        if (tableExists("targets")) {
            this.db.execSQL("DROP TABLE targets");
        }
        if (tableExists("target_globals")) {
            this.db.execSQL("DROP TABLE target_globals");
        }
        if (tableExists("target_documents")) {
            this.db.execSQL("DROP TABLE target_documents");
        }
    }

    private void createV1RemoteDocumentCache() {
        ifTablesDontExist(new String[]{"remote_documents"}, new Runnable() { // from class: com.google.firebase.firestore.local.SQLiteSchema$$ExternalSyntheticLambda14
            @Override // java.lang.Runnable
            public final void run() throws SQLException {
                this.f$0.m5318xd80c1c56();
            }
        });
    }

    /* renamed from: lambda$createV1RemoteDocumentCache$4$com-google-firebase-firestore-local-SQLiteSchema, reason: not valid java name */
    /* synthetic */ void m5318xd80c1c56() throws SQLException {
        this.db.execSQL("CREATE TABLE remote_documents (path TEXT PRIMARY KEY, contents BLOB)");
    }

    private void createFieldIndex() {
        ifTablesDontExist(new String[]{"index_configuration", "index_state", "index_entries"}, new Runnable() { // from class: com.google.firebase.firestore.local.SQLiteSchema$$ExternalSyntheticLambda13
            @Override // java.lang.Runnable
            public final void run() throws SQLException {
                this.f$0.m5315x6d36fdb1();
            }
        });
    }

    /* renamed from: lambda$createFieldIndex$5$com-google-firebase-firestore-local-SQLiteSchema, reason: not valid java name */
    /* synthetic */ void m5315x6d36fdb1() throws SQLException {
        this.db.execSQL("CREATE TABLE index_configuration (index_id INTEGER, collection_group TEXT, index_proto BLOB, PRIMARY KEY (index_id))");
        this.db.execSQL("CREATE TABLE index_state (index_id INTEGER, uid TEXT, sequence_number INTEGER, read_time_seconds INTEGER, read_time_nanos INTEGER, document_key TEXT, largest_batch_id INTEGER, PRIMARY KEY (index_id, uid))");
        this.db.execSQL("CREATE TABLE index_entries (index_id INTEGER, uid TEXT, array_value BLOB, directional_value BLOB, document_key TEXT, PRIMARY KEY (index_id, uid, array_value, directional_value, document_key))");
        this.db.execSQL("CREATE INDEX read_time ON remote_documents(read_time_seconds, read_time_nanos)");
    }

    private void ensureTargetGlobal() throws SQLException {
        if (DatabaseUtils.queryNumEntries(this.db, "target_globals") == 1) {
            return;
        }
        this.db.execSQL("INSERT INTO target_globals (highest_target_id, highest_listen_sequence_number, last_remote_snapshot_version_seconds, last_remote_snapshot_version_nanos) VALUES (?, ?, ?, ?)", new String[]{"0", "0", "0", "0"});
    }

    private void addTargetCount() throws SQLException {
        if (!tableContainsColumn("target_globals", "target_count")) {
            this.db.execSQL("ALTER TABLE target_globals ADD COLUMN target_count INTEGER");
        }
        long jQueryNumEntries = DatabaseUtils.queryNumEntries(this.db, "targets");
        ContentValues contentValues = new ContentValues();
        contentValues.put("target_count", Long.valueOf(jQueryNumEntries));
        this.db.update("target_globals", contentValues, null, null);
    }

    private void addSequenceNumber() throws SQLException {
        if (tableContainsColumn("target_documents", "sequence_number")) {
            return;
        }
        this.db.execSQL("ALTER TABLE target_documents ADD COLUMN sequence_number INTEGER");
    }

    private void addPathLength() throws SQLException {
        if (tableContainsColumn("remote_documents", "path_length")) {
            return;
        }
        this.db.execSQL("ALTER TABLE remote_documents ADD COLUMN path_length INTEGER");
    }

    private boolean hasReadTime() {
        boolean zTableContainsColumn = tableContainsColumn("remote_documents", "read_time_seconds");
        boolean zTableContainsColumn2 = tableContainsColumn("remote_documents", "read_time_nanos");
        Assert.hardAssert(zTableContainsColumn == zTableContainsColumn2, "Table contained just one of read_time_seconds or read_time_nanos", new Object[0]);
        return zTableContainsColumn && zTableContainsColumn2;
    }

    private void addReadTime() throws SQLException {
        this.db.execSQL("ALTER TABLE remote_documents ADD COLUMN read_time_seconds INTEGER");
        this.db.execSQL("ALTER TABLE remote_documents ADD COLUMN read_time_nanos INTEGER");
    }

    private void dropLastLimboFreeSnapshotVersion() {
        new SQLitePersistence.Query(this.db, "SELECT target_id, target_proto FROM targets").forEach(new Consumer() { // from class: com.google.firebase.firestore.local.SQLiteSchema$$ExternalSyntheticLambda17
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) throws SQLException {
                this.f$0.m5321x75baead4((Cursor) obj);
            }
        });
    }

    /* renamed from: lambda$dropLastLimboFreeSnapshotVersion$6$com-google-firebase-firestore-local-SQLiteSchema, reason: not valid java name */
    /* synthetic */ void m5321x75baead4(Cursor cursor) throws SQLException {
        int i = cursor.getInt(0);
        try {
            this.db.execSQL("UPDATE targets SET target_proto = ? WHERE target_id = ?", new Object[]{Target.parseFrom(cursor.getBlob(1)).toBuilder().clearLastLimboFreeSnapshotVersion().build().toByteArray(), Integer.valueOf(i)});
        } catch (InvalidProtocolBufferException unused) {
            throw Assert.fail("Failed to decode Query data for target %s", Integer.valueOf(i));
        }
    }

    private void ensureSequenceNumbers() throws SQLException {
        Long l = (Long) new SQLitePersistence.Query(this.db, "SELECT highest_listen_sequence_number FROM target_globals LIMIT 1").firstValue(new Function() { // from class: com.google.firebase.firestore.local.SQLiteSchema$$ExternalSyntheticLambda1
            @Override // com.google.firebase.firestore.util.Function
            public final Object apply(Object obj) {
                return Long.valueOf(((Cursor) obj).getLong(0));
            }
        });
        Assert.hardAssert(l != null, "Missing highest sequence number", new Object[0]);
        final long jLongValue = l.longValue();
        final SQLiteStatement sQLiteStatementCompileStatement = this.db.compileStatement("INSERT INTO target_documents (target_id, path, sequence_number) VALUES (0, ?, ?)");
        SQLitePersistence.Query queryBinding = new SQLitePersistence.Query(this.db, "SELECT RD.path FROM remote_documents AS RD WHERE NOT EXISTS (SELECT TD.path FROM target_documents AS TD WHERE RD.path = TD.path AND TD.target_id = 0) LIMIT ?").binding(100);
        final boolean[] zArr = new boolean[1];
        do {
            zArr[0] = false;
            queryBinding.forEach(new Consumer() { // from class: com.google.firebase.firestore.local.SQLiteSchema$$ExternalSyntheticLambda2
                @Override // com.google.firebase.firestore.util.Consumer
                public final void accept(Object obj) {
                    SQLiteSchema.lambda$ensureSequenceNumbers$8(zArr, sQLiteStatementCompileStatement, jLongValue, (Cursor) obj);
                }
            });
        } while (zArr[0]);
    }

    static /* synthetic */ void lambda$ensureSequenceNumbers$8(boolean[] zArr, SQLiteStatement sQLiteStatement, long j, Cursor cursor) {
        zArr[0] = true;
        sQLiteStatement.clearBindings();
        sQLiteStatement.bindString(1, cursor.getString(0));
        sQLiteStatement.bindLong(2, j);
        Assert.hardAssert(sQLiteStatement.executeInsert() != -1, "Failed to insert a sentinel row", new Object[0]);
    }

    private void createV8CollectionParentsIndex() throws SQLException {
        ifTablesDontExist(new String[]{"collection_parents"}, new Runnable() { // from class: com.google.firebase.firestore.local.SQLiteSchema$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() throws SQLException {
                this.f$0.m5320xa9cab21c();
            }
        });
        final MemoryIndexManager.MemoryCollectionParentIndex memoryCollectionParentIndex = new MemoryIndexManager.MemoryCollectionParentIndex();
        final SQLiteStatement sQLiteStatementCompileStatement = this.db.compileStatement("INSERT OR REPLACE INTO collection_parents (collection_id, parent) VALUES (?, ?)");
        final Consumer consumer = new Consumer() { // from class: com.google.firebase.firestore.local.SQLiteSchema$$ExternalSyntheticLambda5
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                SQLiteSchema.lambda$createV8CollectionParentsIndex$10(memoryCollectionParentIndex, sQLiteStatementCompileStatement, (ResourcePath) obj);
            }
        };
        new SQLitePersistence.Query(this.db, "SELECT path FROM remote_documents").forEach(new Consumer() { // from class: com.google.firebase.firestore.local.SQLiteSchema$$ExternalSyntheticLambda6
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                consumer.accept(EncodedPath.decodeResourcePath(((Cursor) obj).getString(0)).popLast());
            }
        });
        new SQLitePersistence.Query(this.db, "SELECT path FROM document_mutations").forEach(new Consumer() { // from class: com.google.firebase.firestore.local.SQLiteSchema$$ExternalSyntheticLambda7
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                consumer.accept(EncodedPath.decodeResourcePath(((Cursor) obj).getString(0)).popLast());
            }
        });
    }

    /* renamed from: lambda$createV8CollectionParentsIndex$9$com-google-firebase-firestore-local-SQLiteSchema, reason: not valid java name */
    /* synthetic */ void m5320xa9cab21c() throws SQLException {
        this.db.execSQL("CREATE TABLE collection_parents (collection_id TEXT, parent TEXT, PRIMARY KEY(collection_id, parent))");
    }

    static /* synthetic */ void lambda$createV8CollectionParentsIndex$10(MemoryIndexManager.MemoryCollectionParentIndex memoryCollectionParentIndex, SQLiteStatement sQLiteStatement, ResourcePath resourcePath) {
        if (memoryCollectionParentIndex.add(resourcePath)) {
            String lastSegment = resourcePath.getLastSegment();
            ResourcePath resourcePathPopLast = resourcePath.popLast();
            sQLiteStatement.clearBindings();
            sQLiteStatement.bindString(1, lastSegment);
            sQLiteStatement.bindString(2, EncodedPath.encode(resourcePathPopLast));
            sQLiteStatement.execute();
        }
    }

    private boolean tableContainsColumn(String str, String str2) {
        return getTableColumns(str).indexOf(str2) != -1;
    }

    List<String> getTableColumns(String str) {
        ArrayList arrayList = new ArrayList();
        Cursor cursorRawQuery = null;
        try {
            cursorRawQuery = this.db.rawQuery("PRAGMA table_info(" + str + ")", null);
            int columnIndex = cursorRawQuery.getColumnIndex("name");
            while (cursorRawQuery.moveToNext()) {
                arrayList.add(cursorRawQuery.getString(columnIndex));
            }
            return arrayList;
        } finally {
            if (cursorRawQuery != null) {
                cursorRawQuery.close();
            }
        }
    }

    private void rewriteCanonicalIds() {
        new SQLitePersistence.Query(this.db, "SELECT target_id, target_proto FROM targets").forEach(new Consumer() { // from class: com.google.firebase.firestore.local.SQLiteSchema$$ExternalSyntheticLambda10
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) throws SQLException {
                this.f$0.m5324x9cba5780((Cursor) obj);
            }
        });
    }

    /* renamed from: lambda$rewriteCanonicalIds$13$com-google-firebase-firestore-local-SQLiteSchema, reason: not valid java name */
    /* synthetic */ void m5324x9cba5780(Cursor cursor) throws SQLException {
        int i = cursor.getInt(0);
        try {
            this.db.execSQL("UPDATE targets SET canonical_id  = ? WHERE target_id = ?", new Object[]{this.serializer.decodeTargetData(Target.parseFrom(cursor.getBlob(1))).getTarget().getCanonicalId(), Integer.valueOf(i)});
        } catch (InvalidProtocolBufferException unused) {
            throw Assert.fail("Failed to decode Query data for target %s", Integer.valueOf(i));
        }
    }

    private void ensurePathLength() throws SQLException {
        SQLitePersistence.Query queryBinding = new SQLitePersistence.Query(this.db, "SELECT path FROM remote_documents WHERE path_length IS NULL LIMIT ?").binding(100);
        final SQLiteStatement sQLiteStatementCompileStatement = this.db.compileStatement("UPDATE remote_documents SET path_length = ? WHERE path = ?");
        final boolean[] zArr = new boolean[1];
        do {
            zArr[0] = false;
            queryBinding.forEach(new Consumer() { // from class: com.google.firebase.firestore.local.SQLiteSchema$$ExternalSyntheticLambda8
                @Override // com.google.firebase.firestore.util.Consumer
                public final void accept(Object obj) {
                    SQLiteSchema.lambda$ensurePathLength$14(zArr, sQLiteStatementCompileStatement, (Cursor) obj);
                }
            });
        } while (zArr[0]);
    }

    static /* synthetic */ void lambda$ensurePathLength$14(boolean[] zArr, SQLiteStatement sQLiteStatement, Cursor cursor) {
        zArr[0] = true;
        String string = cursor.getString(0);
        ResourcePath resourcePathDecodeResourcePath = EncodedPath.decodeResourcePath(string);
        sQLiteStatement.clearBindings();
        sQLiteStatement.bindLong(1, resourcePathDecodeResourcePath.length());
        sQLiteStatement.bindString(2, string);
        Assert.hardAssert(sQLiteStatement.executeUpdateDelete() != -1, "Failed to update document path", new Object[0]);
    }

    private void ensureReadTime() throws SQLException {
        this.db.execSQL("UPDATE remote_documents SET read_time_seconds = 0, read_time_nanos = 0 WHERE read_time_seconds IS NULL");
    }

    private void createBundleCache() {
        ifTablesDontExist(new String[]{"bundles", "named_queries"}, new Runnable() { // from class: com.google.firebase.firestore.local.SQLiteSchema$$ExternalSyntheticLambda16
            @Override // java.lang.Runnable
            public final void run() throws SQLException {
                this.f$0.m5313x6e4b0732();
            }
        });
    }

    /* renamed from: lambda$createBundleCache$15$com-google-firebase-firestore-local-SQLiteSchema, reason: not valid java name */
    /* synthetic */ void m5313x6e4b0732() throws SQLException {
        this.db.execSQL("CREATE TABLE bundles (bundle_id TEXT PRIMARY KEY, create_time_seconds INTEGER, create_time_nanos INTEGER, schema_version INTEGER, total_documents INTEGER, total_bytes INTEGER)");
        this.db.execSQL("CREATE TABLE named_queries (name TEXT PRIMARY KEY, read_time_seconds INTEGER, read_time_nanos INTEGER, bundled_query_proto BLOB)");
    }

    private void createOverlays() {
        ifTablesDontExist(new String[]{"document_overlays"}, new Runnable() { // from class: com.google.firebase.firestore.local.SQLiteSchema$$ExternalSyntheticLambda12
            @Override // java.lang.Runnable
            public final void run() throws SQLException {
                this.f$0.m5316xe88164a6();
            }
        });
    }

    /* renamed from: lambda$createOverlays$16$com-google-firebase-firestore-local-SQLiteSchema, reason: not valid java name */
    /* synthetic */ void m5316xe88164a6() throws SQLException {
        this.db.execSQL("CREATE TABLE document_overlays (uid TEXT, collection_path TEXT, document_id TEXT, collection_group TEXT, largest_batch_id INTEGER, overlay_mutation BLOB, PRIMARY KEY (uid, collection_path, document_id))");
        this.db.execSQL("CREATE INDEX batch_id_overlay ON document_overlays (uid, largest_batch_id)");
        this.db.execSQL("CREATE INDEX collection_group_overlay ON document_overlays (uid, collection_group)");
    }

    private void createDataMigrationTable() {
        ifTablesDontExist(new String[]{"data_migrations"}, new Runnable() { // from class: com.google.firebase.firestore.local.SQLiteSchema$$ExternalSyntheticLambda11
            @Override // java.lang.Runnable
            public final void run() throws SQLException {
                this.f$0.m5314x9f4946cc();
            }
        });
    }

    /* renamed from: lambda$createDataMigrationTable$17$com-google-firebase-firestore-local-SQLiteSchema, reason: not valid java name */
    /* synthetic */ void m5314x9f4946cc() throws SQLException {
        this.db.execSQL("CREATE TABLE data_migrations (migration_name TEXT, PRIMARY KEY (migration_name))");
    }

    private void addPendingDataMigration(String str) throws SQLException {
        this.db.execSQL("INSERT OR IGNORE INTO data_migrations (migration_name) VALUES (?)", new String[]{str});
    }

    private boolean tableExists(String str) {
        return !new SQLitePersistence.Query(this.db, "SELECT 1=1 FROM sqlite_master WHERE tbl_name = ?").binding(str).isEmpty();
    }
}
