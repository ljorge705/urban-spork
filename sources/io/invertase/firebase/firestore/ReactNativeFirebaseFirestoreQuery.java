package io.invertase.firebase.firestore;

import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.inapp.evaluation.TriggerAdapter;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Source;
import io.invertase.firebase.common.RCTConvertFirebase;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/* loaded from: classes6.dex */
public class ReactNativeFirebaseFirestoreQuery {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    String appName;
    Query query;

    ReactNativeFirebaseFirestoreQuery(String str, Query query, ReadableArray readableArray, ReadableArray readableArray2, ReadableMap readableMap) {
        this.appName = str;
        this.query = query;
        applyFilters(readableArray);
        applyOrders(readableArray2);
        applyOptions(readableMap);
    }

    public Task<WritableMap> get(Executor executor, final Source source) {
        return Tasks.call(executor, new Callable() { // from class: io.invertase.firebase.firestore.ReactNativeFirebaseFirestoreQuery$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.lambda$get$0(source);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ WritableMap lambda$get$0(Source source) throws Exception {
        return ReactNativeFirebaseFirestoreSerialize.snapshotToWritableMap(this.appName, "get", (QuerySnapshot) Tasks.await(this.query.get(source)), null);
    }

    private void applyFilters(ReadableArray readableArray) {
        FieldPath fieldPathOf;
        Object typeMap;
        for (int i = 0; i < readableArray.size(); i++) {
            ReadableMap map = readableArray.getMap(i);
            if (map.hasKey("fieldPath")) {
                fieldPathOf = FieldPath.of((String[]) Objects.requireNonNull((String[]) ((ReadableArray) Objects.requireNonNull(((ReadableMap) Objects.requireNonNull(map)).getArray("fieldPath"))).toArrayList().toArray(new String[0])));
                String string = map.getString(TriggerAdapter.INAPP_OPERATOR);
                typeMap = ReactNativeFirebaseFirestoreSerialize.parseTypeMap(this.query.getFirestore(), (ReadableArray) Objects.requireNonNull(map.getArray("value")));
                String str = (String) Objects.requireNonNull(string);
                str.hashCode();
                switch (str) {
                    case "LESS_THAN_OR_EQUAL":
                        this.query = this.query.whereLessThanOrEqualTo((FieldPath) Objects.requireNonNull(fieldPathOf), Objects.requireNonNull(typeMap));
                        break;
                    case "NOT_IN":
                        this.query = this.query.whereNotIn((FieldPath) Objects.requireNonNull(fieldPathOf), (List<? extends Object>) Objects.requireNonNull((List) typeMap));
                        break;
                    case "LESS_THAN":
                        this.query = this.query.whereLessThan((FieldPath) Objects.requireNonNull(fieldPathOf), Objects.requireNonNull(typeMap));
                        break;
                    case "ARRAY_CONTAINS_ANY":
                        this.query = this.query.whereArrayContainsAny((FieldPath) Objects.requireNonNull(fieldPathOf), (List<? extends Object>) Objects.requireNonNull((List) typeMap));
                        break;
                    case "IN":
                        this.query = this.query.whereIn((FieldPath) Objects.requireNonNull(fieldPathOf), (List<? extends Object>) Objects.requireNonNull((List) typeMap));
                        break;
                    case "EQUAL":
                        this.query = this.query.whereEqualTo((FieldPath) Objects.requireNonNull(fieldPathOf), typeMap);
                        break;
                    case "ARRAY_CONTAINS":
                        this.query = this.query.whereArrayContains((FieldPath) Objects.requireNonNull(fieldPathOf), Objects.requireNonNull(typeMap));
                        break;
                    case "GREATER_THAN":
                        this.query = this.query.whereGreaterThan((FieldPath) Objects.requireNonNull(fieldPathOf), Objects.requireNonNull(typeMap));
                        break;
                    case "GREATER_THAN_OR_EQUAL":
                        this.query = this.query.whereGreaterThanOrEqualTo((FieldPath) Objects.requireNonNull(fieldPathOf), Objects.requireNonNull(typeMap));
                        break;
                    case "NOT_EQUAL":
                        this.query = this.query.whereNotEqualTo((FieldPath) Objects.requireNonNull(fieldPathOf), typeMap);
                        break;
                }
            } else if (map.hasKey(TriggerAdapter.INAPP_OPERATOR) && map.hasKey("queries")) {
                this.query = this.query.where(applyFilterQueries(map));
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0068  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private com.google.firebase.firestore.Filter applyFilterQueries(com.facebook.react.bridge.ReadableMap r8) {
        /*
            Method dump skipped, instructions count: 456
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.invertase.firebase.firestore.ReactNativeFirebaseFirestoreQuery.applyFilterQueries(com.facebook.react.bridge.ReadableMap):com.google.firebase.firestore.Filter");
    }

    private void applyOrders(ReadableArray readableArray) {
        Iterator<Object> it = RCTConvertFirebase.toArrayList(readableArray).iterator();
        while (it.hasNext()) {
            Map map = (Map) it.next();
            if (map.get("fieldPath") instanceof List) {
                this.query = this.query.orderBy((FieldPath) Objects.requireNonNull(FieldPath.of((String[]) ((ArrayList) map.get("fieldPath")).toArray(new String[0]))), Query.Direction.valueOf((String) map.get("direction")));
            } else {
                this.query = this.query.orderBy((String) Objects.requireNonNull((String) map.get("fieldPath")), Query.Direction.valueOf((String) map.get("direction")));
            }
        }
    }

    private void applyOptions(ReadableMap readableMap) {
        if (readableMap.hasKey(Constants.KEY_LIMIT)) {
            this.query = this.query.limit(readableMap.getInt(Constants.KEY_LIMIT));
        }
        if (readableMap.hasKey("limitToLast")) {
            this.query = this.query.limitToLast(readableMap.getInt("limitToLast"));
        }
        if (readableMap.hasKey("startAt")) {
            this.query = this.query.startAt((Object[]) Objects.requireNonNull(ReactNativeFirebaseFirestoreSerialize.parseReadableArray(this.query.getFirestore(), readableMap.getArray("startAt")).toArray()));
        }
        if (readableMap.hasKey("startAfter")) {
            this.query = this.query.startAfter((Object[]) Objects.requireNonNull(ReactNativeFirebaseFirestoreSerialize.parseReadableArray(this.query.getFirestore(), readableMap.getArray("startAfter")).toArray()));
        }
        if (readableMap.hasKey("endAt")) {
            this.query = this.query.endAt((Object[]) Objects.requireNonNull(ReactNativeFirebaseFirestoreSerialize.parseReadableArray(this.query.getFirestore(), readableMap.getArray("endAt")).toArray()));
        }
        if (readableMap.hasKey("endBefore")) {
            this.query = this.query.endBefore((Object[]) Objects.requireNonNull(ReactNativeFirebaseFirestoreSerialize.parseReadableArray(this.query.getFirestore(), readableMap.getArray("endBefore")).toArray()));
        }
    }
}
