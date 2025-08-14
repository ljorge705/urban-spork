package io.invertase.firebase.firestore;

import android.util.Base64;
import android.util.Log;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.Blob;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.firestore.MetadataChanges;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SnapshotMetadata;
import io.invertase.firebase.common.RCTConvertFirebase;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.annotation.Nullable;

/* loaded from: classes6.dex */
public class ReactNativeFirebaseFirestoreSerialize {
    private static final String CHANGE_ADDED = "a";
    private static final String CHANGE_MODIFIED = "m";
    private static final String CHANGE_REMOVED = "r";
    private static final int INT_ARRAY = 10;
    private static final int INT_BLOB = 14;
    private static final int INT_BOOLEAN_FALSE = 6;
    private static final int INT_BOOLEAN_TRUE = 5;
    private static final int INT_DOCUMENTID = 4;
    private static final int INT_DOUBLE = 7;
    private static final int INT_FIELDVALUE = 15;
    private static final int INT_GEOPOINT = 12;
    private static final int INT_INTEGER = 17;
    private static final int INT_NAN = 0;
    private static final int INT_NEGATIVE_INFINITY = 1;
    private static final int INT_NEGATIVE_ZERO = 18;
    private static final int INT_NULL = 3;
    private static final int INT_OBJECT = 16;
    private static final int INT_POSITIVE_INFINITY = 2;
    private static final int INT_REFERENCE = 11;
    private static final int INT_STRING = 8;
    private static final int INT_STRING_EMPTY = 9;
    private static final int INT_TIMESTAMP = 13;
    private static final int INT_UNKNOWN = -999;
    private static final String KEY_CHANGES = "changes";
    private static final String KEY_DATA = "data";
    private static final String KEY_DOCUMENTS = "documents";
    private static final String KEY_DOC_CHANGE_DOCUMENT = "doc";
    private static final String KEY_DOC_CHANGE_NEW_INDEX = "ni";
    private static final String KEY_DOC_CHANGE_OLD_INDEX = "oi";
    private static final String KEY_DOC_CHANGE_TYPE = "type";
    private static final String KEY_EXISTS = "exists";
    private static final String KEY_META = "metadata";
    private static final String KEY_OPTIONS = "options";
    private static final String KEY_PATH = "path";
    private static final String TAG = "FirestoreSerialize";
    private static final String TYPE = "type";

    /* JADX INFO: Access modifiers changed from: package-private */
    public static WritableMap snapshotToWritableMap(String str, DocumentSnapshot documentSnapshot) {
        WritableArray writableArrayCreateArray = Arguments.createArray();
        WritableMap writableMapCreateMap = Arguments.createMap();
        SnapshotMetadata metadata = documentSnapshot.getMetadata();
        writableArrayCreateArray.pushBoolean(metadata.isFromCache());
        writableArrayCreateArray.pushBoolean(metadata.hasPendingWrites());
        writableMapCreateMap.putArray(KEY_META, writableArrayCreateArray);
        writableMapCreateMap.putString("path", documentSnapshot.getReference().getPath());
        writableMapCreateMap.putBoolean(KEY_EXISTS, documentSnapshot.exists());
        DocumentSnapshot.ServerTimestampBehavior serverTimestampBehavior = ReactNativeFirebaseFirestoreCommon.getServerTimestampBehavior(str);
        if (documentSnapshot.exists() && documentSnapshot.getData(serverTimestampBehavior) != null) {
            writableMapCreateMap.putMap("data", objectMapToWritable(documentSnapshot.getData(serverTimestampBehavior)));
        }
        return writableMapCreateMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static WritableMap snapshotToWritableMap(String str, String str2, QuerySnapshot querySnapshot, @Nullable MetadataChanges metadataChanges) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putString("source", str2);
        WritableArray writableArrayCreateArray = Arguments.createArray();
        WritableArray writableArrayCreateArray2 = Arguments.createArray();
        List<DocumentChange> documentChanges = querySnapshot.getDocumentChanges();
        if (metadataChanges == null || metadataChanges == MetadataChanges.EXCLUDE) {
            writableMapCreateMap.putBoolean("excludesMetadataChanges", true);
            writableMapCreateMap.putArray(KEY_CHANGES, documentChangesToWritableArray(str, documentChanges, null));
        } else {
            writableMapCreateMap.putBoolean("excludesMetadataChanges", false);
            writableMapCreateMap.putArray(KEY_CHANGES, documentChangesToWritableArray(str, querySnapshot.getDocumentChanges(MetadataChanges.INCLUDE), documentChanges));
        }
        SnapshotMetadata metadata = querySnapshot.getMetadata();
        Iterator<DocumentSnapshot> it = querySnapshot.getDocuments().iterator();
        while (it.hasNext()) {
            writableArrayCreateArray2.pushMap(snapshotToWritableMap(str, it.next()));
        }
        writableMapCreateMap.putArray(KEY_DOCUMENTS, writableArrayCreateArray2);
        writableArrayCreateArray.pushBoolean(metadata.isFromCache());
        writableArrayCreateArray.pushBoolean(metadata.hasPendingWrites());
        writableMapCreateMap.putArray(KEY_META, writableArrayCreateArray);
        return writableMapCreateMap;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0034  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static com.facebook.react.bridge.WritableArray documentChangesToWritableArray(java.lang.String r7, java.util.List<com.google.firebase.firestore.DocumentChange> r8, @javax.annotation.Nullable java.util.List<com.google.firebase.firestore.DocumentChange> r9) {
        /*
            com.facebook.react.bridge.WritableArray r0 = com.facebook.react.bridge.Arguments.createArray()
            r1 = 1
            r2 = 0
            if (r9 == 0) goto La
            r3 = r1
            goto Lb
        La:
            r3 = r2
        Lb:
            java.util.Iterator r8 = r8.iterator()
        Lf:
            boolean r4 = r8.hasNext()
            if (r4 == 0) goto L3d
            java.lang.Object r4 = r8.next()
            com.google.firebase.firestore.DocumentChange r4 = (com.google.firebase.firestore.DocumentChange) r4
            if (r3 == 0) goto L34
            int r5 = r4.hashCode()
            io.invertase.firebase.firestore.ReactNativeFirebaseFirestoreSerialize$$ExternalSyntheticLambda0 r6 = new io.invertase.firebase.firestore.ReactNativeFirebaseFirestoreSerialize$$ExternalSyntheticLambda0
            r6.<init>()
            com.google.common.base.Optional r5 = com.google.common.collect.Iterables.tryFind(r9, r6)
            java.lang.Object r5 = r5.orNull()
            com.google.firebase.firestore.DocumentChange r5 = (com.google.firebase.firestore.DocumentChange) r5
            if (r5 != 0) goto L34
            r5 = r1
            goto L35
        L34:
            r5 = r2
        L35:
            com.facebook.react.bridge.WritableMap r4 = documentChangeToWritableMap(r7, r4, r5)
            r0.pushMap(r4)
            goto Lf
        L3d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.invertase.firebase.firestore.ReactNativeFirebaseFirestoreSerialize.documentChangesToWritableArray(java.lang.String, java.util.List, java.util.List):com.facebook.react.bridge.WritableArray");
    }

    static /* synthetic */ boolean lambda$documentChangesToWritableArray$0(int i, DocumentChange documentChange) {
        return documentChange.hashCode() == i;
    }

    private static WritableMap documentChangeToWritableMap(String str, DocumentChange documentChange, boolean z) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putBoolean("isMetadataChange", z);
        int i = AnonymousClass1.$SwitchMap$com$google$firebase$firestore$DocumentChange$Type[documentChange.getType().ordinal()];
        if (i == 1) {
            writableMapCreateMap.putString("type", CHANGE_ADDED);
        } else if (i == 2) {
            writableMapCreateMap.putString("type", CHANGE_MODIFIED);
        } else if (i == 3) {
            writableMapCreateMap.putString("type", "r");
        }
        writableMapCreateMap.putMap(KEY_DOC_CHANGE_DOCUMENT, snapshotToWritableMap(str, documentChange.getDocument()));
        writableMapCreateMap.putInt(KEY_DOC_CHANGE_NEW_INDEX, documentChange.getNewIndex());
        writableMapCreateMap.putInt(KEY_DOC_CHANGE_OLD_INDEX, documentChange.getOldIndex());
        return writableMapCreateMap;
    }

    /* renamed from: io.invertase.firebase.firestore.ReactNativeFirebaseFirestoreSerialize$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$firestore$DocumentChange$Type;

        static {
            int[] iArr = new int[DocumentChange.Type.values().length];
            $SwitchMap$com$google$firebase$firestore$DocumentChange$Type = iArr;
            try {
                iArr[DocumentChange.Type.ADDED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$DocumentChange$Type[DocumentChange.Type.MODIFIED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$DocumentChange$Type[DocumentChange.Type.REMOVED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private static WritableMap objectMapToWritable(Map<String, Object> map) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            writableMapCreateMap.putArray(entry.getKey(), buildTypeMap(entry.getValue()));
        }
        return writableMapCreateMap;
    }

    private static WritableArray objectArrayToWritable(Object[] objArr) {
        WritableArray writableArrayCreateArray = Arguments.createArray();
        for (Object obj : objArr) {
            writableArrayCreateArray.pushArray(buildTypeMap(obj));
        }
        return writableArrayCreateArray;
    }

    private static WritableArray buildTypeMap(Object obj) {
        WritableArray writableArrayCreateArray = Arguments.createArray();
        if (obj == null) {
            writableArrayCreateArray.pushInt(3);
            return writableArrayCreateArray;
        }
        if (obj instanceof Boolean) {
            if (((Boolean) obj).booleanValue()) {
                writableArrayCreateArray.pushInt(5);
            } else {
                writableArrayCreateArray.pushInt(6);
            }
            return writableArrayCreateArray;
        }
        if (obj instanceof Integer) {
            writableArrayCreateArray.pushInt(7);
            writableArrayCreateArray.pushDouble(((Integer) obj).doubleValue());
            return writableArrayCreateArray;
        }
        if (obj instanceof Double) {
            Double d = (Double) obj;
            if (Double.isInfinite(d.doubleValue())) {
                if (d.equals(Double.valueOf(Double.NEGATIVE_INFINITY))) {
                    writableArrayCreateArray.pushInt(1);
                    return writableArrayCreateArray;
                }
                if (d.equals(Double.valueOf(Double.POSITIVE_INFINITY))) {
                    writableArrayCreateArray.pushInt(2);
                    return writableArrayCreateArray;
                }
            }
            if (Double.isNaN(d.doubleValue())) {
                writableArrayCreateArray.pushInt(0);
                return writableArrayCreateArray;
            }
            writableArrayCreateArray.pushInt(7);
            writableArrayCreateArray.pushDouble(d.doubleValue());
            return writableArrayCreateArray;
        }
        if (obj instanceof Float) {
            writableArrayCreateArray.pushInt(7);
            writableArrayCreateArray.pushDouble(((Float) obj).doubleValue());
            return writableArrayCreateArray;
        }
        if (obj instanceof Long) {
            writableArrayCreateArray.pushInt(7);
            writableArrayCreateArray.pushDouble(((Long) obj).doubleValue());
            return writableArrayCreateArray;
        }
        if (obj instanceof String) {
            if (obj == "") {
                writableArrayCreateArray.pushInt(9);
            } else {
                writableArrayCreateArray.pushInt(8);
                writableArrayCreateArray.pushString((String) obj);
            }
            return writableArrayCreateArray;
        }
        if (Map.class.isAssignableFrom(obj.getClass())) {
            writableArrayCreateArray.pushInt(16);
            writableArrayCreateArray.pushMap(objectMapToWritable((Map) obj));
            return writableArrayCreateArray;
        }
        if (List.class.isAssignableFrom(obj.getClass())) {
            writableArrayCreateArray.pushInt(10);
            List list = (List) obj;
            writableArrayCreateArray.pushArray(objectArrayToWritable(list.toArray(new Object[list.size()])));
            return writableArrayCreateArray;
        }
        if (obj instanceof DocumentReference) {
            writableArrayCreateArray.pushInt(11);
            writableArrayCreateArray.pushString(((DocumentReference) obj).getPath());
            return writableArrayCreateArray;
        }
        if (obj instanceof Timestamp) {
            writableArrayCreateArray.pushInt(13);
            WritableArray writableArrayCreateArray2 = Arguments.createArray();
            writableArrayCreateArray2.pushDouble(r6.getSeconds());
            writableArrayCreateArray2.pushInt(((Timestamp) obj).getNanoseconds());
            writableArrayCreateArray.pushArray(writableArrayCreateArray2);
            return writableArrayCreateArray;
        }
        if (obj instanceof GeoPoint) {
            writableArrayCreateArray.pushInt(12);
            WritableArray writableArrayCreateArray3 = Arguments.createArray();
            GeoPoint geoPoint = (GeoPoint) obj;
            writableArrayCreateArray3.pushDouble(geoPoint.getLatitude());
            writableArrayCreateArray3.pushDouble(geoPoint.getLongitude());
            writableArrayCreateArray.pushArray(writableArrayCreateArray3);
            return writableArrayCreateArray;
        }
        if (obj instanceof Blob) {
            writableArrayCreateArray.pushInt(14);
            writableArrayCreateArray.pushString(Base64.encodeToString(((Blob) obj).toBytes(), 2));
            return writableArrayCreateArray;
        }
        Log.w(TAG, "Unknown object of type " + obj.getClass());
        writableArrayCreateArray.pushInt(INT_UNKNOWN);
        return writableArrayCreateArray;
    }

    public static Map<String, Object> parseReadableMap(FirebaseFirestore firebaseFirestore, @Nullable ReadableMap readableMap) {
        HashMap map = new HashMap();
        if (readableMap == null) {
            return map;
        }
        ReadableMapKeySetIterator readableMapKeySetIteratorKeySetIterator = readableMap.keySetIterator();
        while (readableMapKeySetIteratorKeySetIterator.hasNextKey()) {
            String strNextKey = readableMapKeySetIteratorKeySetIterator.nextKey();
            map.put(strNextKey, parseTypeMap(firebaseFirestore, readableMap.getArray(strNextKey)));
        }
        return map;
    }

    static List<Object> parseReadableArray(FirebaseFirestore firebaseFirestore, @Nullable ReadableArray readableArray) {
        ArrayList arrayList = new ArrayList();
        if (readableArray == null) {
            return arrayList;
        }
        for (int i = 0; i < readableArray.size(); i++) {
            arrayList.add(parseTypeMap(firebaseFirestore, readableArray.getArray(i)));
        }
        return arrayList;
    }

    static Object parseTypeMap(FirebaseFirestore firebaseFirestore, ReadableArray readableArray) {
        switch (readableArray.getInt(0)) {
            case 0:
                return Double.valueOf(Double.NaN);
            case 1:
                return Double.valueOf(Double.NEGATIVE_INFINITY);
            case 2:
                return Double.valueOf(Double.POSITIVE_INFINITY);
            case 3:
            default:
                return null;
            case 4:
                return FieldPath.documentId();
            case 5:
                return true;
            case 6:
                return false;
            case 7:
                return Double.valueOf(readableArray.getDouble(1));
            case 8:
                return readableArray.getString(1);
            case 9:
                return "";
            case 10:
                return parseReadableArray(firebaseFirestore, readableArray.getArray(1));
            case 11:
                return firebaseFirestore.document((String) Objects.requireNonNull(readableArray.getString(1)));
            case 12:
                ReadableArray array = readableArray.getArray(1);
                return new GeoPoint(((ReadableArray) Objects.requireNonNull(array)).getDouble(0), array.getDouble(1));
            case 13:
                ReadableArray array2 = readableArray.getArray(1);
                return new Timestamp((long) ((ReadableArray) Objects.requireNonNull(array2)).getDouble(0), array2.getInt(1));
            case 14:
                return Blob.fromBytes(Base64.decode(readableArray.getString(1), 2));
            case 15:
                ReadableArray array3 = readableArray.getArray(1);
                String string = ((ReadableArray) Objects.requireNonNull(array3)).getString(0);
                if (((String) Objects.requireNonNull(string)).equals("timestamp")) {
                    return FieldValue.serverTimestamp();
                }
                if (((String) Objects.requireNonNull(string)).equals("increment")) {
                    return FieldValue.increment(array3.getDouble(1));
                }
                if (((String) Objects.requireNonNull(string)).equals("delete")) {
                    return FieldValue.delete();
                }
                if (((String) Objects.requireNonNull(string)).equals("array_union")) {
                    return FieldValue.arrayUnion(parseReadableArray(firebaseFirestore, array3.getArray(1)).toArray());
                }
                if (((String) Objects.requireNonNull(string)).equals("array_remove")) {
                    return FieldValue.arrayRemove(parseReadableArray(firebaseFirestore, array3.getArray(1)).toArray());
                }
                break;
            case 16:
                break;
            case 17:
                return Long.valueOf((long) readableArray.getDouble(1));
            case 18:
                return Double.valueOf(-0.0d);
        }
        return parseReadableMap(firebaseFirestore, readableArray.getMap(1));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List<Object> parseDocumentBatches(FirebaseFirestore firebaseFirestore, ReadableArray readableArray) {
        ArrayList arrayList = new ArrayList(readableArray.size());
        for (int i = 0; i < readableArray.size(); i++) {
            HashMap map = new HashMap();
            ReadableMap map2 = readableArray.getMap(i);
            map.put("type", map2.getString("type"));
            map.put("path", map2.getString("path"));
            if (map2.hasKey("data")) {
                map.put("data", parseReadableMap(firebaseFirestore, map2.getMap("data")));
            }
            if (map2.hasKey("options")) {
                map.put("options", RCTConvertFirebase.toHashMap(map2.getMap("options")));
            }
            arrayList.add(map);
        }
        return arrayList;
    }
}
