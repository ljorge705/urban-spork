package com.google.firebase.firestore.bundle;

import android.util.Base64;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.core.Bound;
import com.google.firebase.firestore.core.FieldFilter;
import com.google.firebase.firestore.core.Filter;
import com.google.firebase.firestore.core.OrderBy;
import com.google.firebase.firestore.core.Query;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.ObjectValue;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.model.Values;
import com.google.firebase.firestore.remote.RemoteSerializer;
import com.google.firebase.messaging.Constants;
import com.google.firestore.v1.ArrayValue;
import com.google.firestore.v1.MapValue;
import com.google.firestore.v1.Value;
import com.google.protobuf.ByteString;
import com.google.protobuf.NullValue;
import com.google.type.LatLng;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class BundleSerializer {
    private static final long MILLIS_PER_SECOND = 1000;
    private final RemoteSerializer remoteSerializer;
    private final SimpleDateFormat timestampFormat;

    public BundleSerializer(RemoteSerializer remoteSerializer) {
        this.remoteSerializer = remoteSerializer;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
        this.timestampFormat = simpleDateFormat;
        GregorianCalendar gregorianCalendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        gregorianCalendar.setGregorianChange(new Date(Long.MIN_VALUE));
        simpleDateFormat.setCalendar(gregorianCalendar);
    }

    public NamedQuery decodeNamedQuery(JSONObject jSONObject) throws JSONException {
        return new NamedQuery(jSONObject.getString("name"), decodeBundledQuery(jSONObject.getJSONObject("bundledQuery")), decodeSnapshotVersion(jSONObject.get("readTime")));
    }

    public BundleMetadata decodeBundleMetadata(JSONObject jSONObject) throws JSONException {
        return new BundleMetadata(jSONObject.getString("id"), jSONObject.getInt("version"), decodeSnapshotVersion(jSONObject.get("createTime")), jSONObject.getInt("totalDocuments"), jSONObject.getLong("totalBytes"));
    }

    public BundledDocumentMetadata decodeBundledDocumentMetadata(JSONObject jSONObject) throws JSONException {
        DocumentKey documentKeyFromPath = DocumentKey.fromPath(decodeName(jSONObject.getString("name")));
        SnapshotVersion snapshotVersionDecodeSnapshotVersion = decodeSnapshotVersion(jSONObject.get("readTime"));
        boolean zOptBoolean = jSONObject.optBoolean("exists", false);
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("queries");
        ArrayList arrayList = new ArrayList();
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                arrayList.add(jSONArrayOptJSONArray.getString(i));
            }
        }
        return new BundledDocumentMetadata(documentKeyFromPath, snapshotVersionDecodeSnapshotVersion, zOptBoolean, arrayList);
    }

    BundleDocument decodeDocument(JSONObject jSONObject) throws JSONException {
        DocumentKey documentKeyFromPath = DocumentKey.fromPath(decodeName(jSONObject.getString("name")));
        SnapshotVersion snapshotVersionDecodeSnapshotVersion = decodeSnapshotVersion(jSONObject.get("updateTime"));
        Value.Builder builderNewBuilder = Value.newBuilder();
        decodeMapValue(builderNewBuilder, jSONObject.getJSONObject("fields"));
        return new BundleDocument(MutableDocument.newFoundDocument(documentKeyFromPath, snapshotVersionDecodeSnapshotVersion, ObjectValue.fromMap(builderNewBuilder.getMapValue().getFieldsMap())));
    }

    private ResourcePath decodeName(String str) {
        ResourcePath resourcePathFromString = ResourcePath.fromString(str);
        if (!this.remoteSerializer.isLocalResourceName(resourcePathFromString)) {
            throw new IllegalArgumentException("Resource name is not valid for current instance: " + str);
        }
        return resourcePathFromString.popFirst(5);
    }

    private SnapshotVersion decodeSnapshotVersion(Object obj) throws JSONException {
        return new SnapshotVersion(decodeTimestamp(obj));
    }

    private BundledQuery decodeBundledQuery(JSONObject jSONObject) throws JSONException {
        String string;
        JSONObject jSONObject2 = jSONObject.getJSONObject("structuredQuery");
        verifyNoSelect(jSONObject2);
        ResourcePath resourcePathDecodeName = decodeName(jSONObject.getString("parent"));
        JSONArray jSONArray = jSONObject2.getJSONArray(Constants.MessagePayloadKeys.FROM);
        verifyCollectionSelector(jSONArray);
        JSONObject jSONObject3 = jSONArray.getJSONObject(0);
        if (jSONObject3.optBoolean("allDescendants", false)) {
            string = jSONObject3.getString("collectionId");
        } else {
            resourcePathDecodeName = resourcePathDecodeName.append(jSONObject3.getString("collectionId"));
            string = null;
        }
        List<Filter> listDecodeWhere = decodeWhere(jSONObject2.optJSONObject("where"));
        List<OrderBy> listDecodeOrderBy = decodeOrderBy(jSONObject2.optJSONArray("orderBy"));
        Bound boundDecodeStartAtBound = decodeStartAtBound(jSONObject2.optJSONObject("startAt"));
        Bound boundDecodeEndAtBound = decodeEndAtBound(jSONObject2.optJSONObject("endAt"));
        verifyNoOffset(jSONObject2);
        return new BundledQuery(new Query(resourcePathDecodeName, string, listDecodeWhere, listDecodeOrderBy, decodeLimit(jSONObject2), Query.LimitType.LIMIT_TO_FIRST, boundDecodeStartAtBound, boundDecodeEndAtBound).toTarget(), decodeLimitType(jSONObject));
    }

    private int decodeLimit(JSONObject jSONObject) {
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(com.clevertap.android.sdk.Constants.KEY_LIMIT);
        if (jSONObjectOptJSONObject != null) {
            return jSONObjectOptJSONObject.optInt("value", -1);
        }
        return jSONObject.optInt(com.clevertap.android.sdk.Constants.KEY_LIMIT, -1);
    }

    private Bound decodeStartAtBound(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        return new Bound(decodePosition(jSONObject), jSONObject.optBoolean("before", false));
    }

    private Bound decodeEndAtBound(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        return new Bound(decodePosition(jSONObject), !jSONObject.optBoolean("before", false));
    }

    private List<Value> decodePosition(JSONObject jSONObject) throws JSONException {
        ArrayList arrayList = new ArrayList();
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("values");
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                arrayList.add(decodeValue(jSONArrayOptJSONArray.getJSONObject(i)));
            }
        }
        return arrayList;
    }

    private List<OrderBy> decodeOrderBy(JSONArray jSONArray) throws JSONException {
        OrderBy.Direction direction;
        ArrayList arrayList = new ArrayList();
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                FieldPath fieldPathDecodeFieldReference = decodeFieldReference(jSONObject.getJSONObject("field"));
                if (jSONObject.optString("direction", "ASCENDING").equals("ASCENDING")) {
                    direction = OrderBy.Direction.ASCENDING;
                } else {
                    direction = OrderBy.Direction.DESCENDING;
                }
                arrayList.add(OrderBy.getInstance(direction, fieldPathDecodeFieldReference));
            }
        }
        return arrayList;
    }

    private List<Filter> decodeWhere(JSONObject jSONObject) throws JSONException {
        ArrayList arrayList = new ArrayList();
        if (jSONObject != null) {
            decodeFilter(arrayList, jSONObject);
        }
        return arrayList;
    }

    private void decodeFilter(List<Filter> list, JSONObject jSONObject) throws JSONException {
        if (jSONObject.has("compositeFilter")) {
            decodeCompositeFilter(list, jSONObject.getJSONObject("compositeFilter"));
        } else if (jSONObject.has("fieldFilter")) {
            decodeFieldFilter(list, jSONObject.getJSONObject("fieldFilter"));
        } else if (jSONObject.has("unaryFilter")) {
            decodeUnaryFilter(list, jSONObject.getJSONObject("unaryFilter"));
        }
    }

    private void decodeCompositeFilter(List<Filter> list, JSONObject jSONObject) throws JSONException {
        if (!jSONObject.getString("op").equals("AND")) {
            throw new IllegalArgumentException("The Android SDK only supports composite filters of type 'AND'");
        }
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("filters");
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                decodeFilter(list, jSONArrayOptJSONArray.getJSONObject(i));
            }
        }
    }

    private void decodeFieldFilter(List<Filter> list, JSONObject jSONObject) throws JSONException {
        list.add(FieldFilter.create(decodeFieldReference(jSONObject.getJSONObject("field")), decodeFieldFilterOperator(jSONObject.getString("op")), decodeValue(jSONObject.getJSONObject("value"))));
    }

    private Value decodeValue(JSONObject jSONObject) throws JSONException {
        Value.Builder builderNewBuilder = Value.newBuilder();
        if (jSONObject.has("nullValue")) {
            builderNewBuilder.setNullValue(NullValue.NULL_VALUE);
        } else if (jSONObject.has("booleanValue")) {
            builderNewBuilder.setBooleanValue(jSONObject.optBoolean("booleanValue", false));
        } else if (jSONObject.has("integerValue")) {
            builderNewBuilder.setIntegerValue(jSONObject.optLong("integerValue"));
        } else if (jSONObject.has("doubleValue")) {
            builderNewBuilder.setDoubleValue(jSONObject.optDouble("doubleValue"));
        } else if (jSONObject.has("timestampValue")) {
            decodeTimestamp(builderNewBuilder, jSONObject.get("timestampValue"));
        } else if (jSONObject.has("stringValue")) {
            builderNewBuilder.setStringValue(jSONObject.optString("stringValue", ""));
        } else if (jSONObject.has("bytesValue")) {
            builderNewBuilder.setBytesValue(ByteString.copyFrom(Base64.decode(jSONObject.getString("bytesValue"), 0)));
        } else if (jSONObject.has("referenceValue")) {
            builderNewBuilder.setReferenceValue(jSONObject.getString("referenceValue"));
        } else if (jSONObject.has("geoPointValue")) {
            decodeGeoPoint(builderNewBuilder, jSONObject.getJSONObject("geoPointValue"));
        } else if (jSONObject.has("arrayValue")) {
            decodeArrayValue(builderNewBuilder, jSONObject.getJSONObject("arrayValue").optJSONArray("values"));
        } else if (jSONObject.has("mapValue")) {
            decodeMapValue(builderNewBuilder, jSONObject.getJSONObject("mapValue").optJSONObject("fields"));
        } else {
            throw new IllegalArgumentException("Unexpected value type: " + jSONObject);
        }
        return builderNewBuilder.build();
    }

    private void decodeArrayValue(Value.Builder builder, JSONArray jSONArray) throws JSONException {
        ArrayValue.Builder builderNewBuilder = ArrayValue.newBuilder();
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                builderNewBuilder.addValues(decodeValue(jSONArray.getJSONObject(i)));
            }
        }
        builder.setArrayValue(builderNewBuilder);
    }

    private void decodeMapValue(Value.Builder builder, JSONObject jSONObject) throws JSONException {
        MapValue.Builder builderNewBuilder = MapValue.newBuilder();
        if (jSONObject != null) {
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                builderNewBuilder.putFields(next, decodeValue(jSONObject.getJSONObject(next)));
            }
        }
        builder.setMapValue(builderNewBuilder);
    }

    private void decodeGeoPoint(Value.Builder builder, JSONObject jSONObject) {
        builder.setGeoPointValue(LatLng.newBuilder().setLatitude(jSONObject.optDouble("latitude")).setLongitude(jSONObject.optDouble("longitude")));
    }

    private Timestamp decodeTimestamp(JSONObject jSONObject) {
        return new Timestamp(jSONObject.optLong("seconds"), jSONObject.optInt("nanos"));
    }

    private Timestamp decodeTimestamp(String str) {
        try {
            int iIndexOf = str.indexOf(84);
            if (iIndexOf == -1) {
                throw new IllegalArgumentException("Invalid timestamp: " + str);
            }
            int iIndexOf2 = str.indexOf(90, iIndexOf);
            if (iIndexOf2 == -1) {
                iIndexOf2 = str.indexOf(43, iIndexOf);
            }
            if (iIndexOf2 == -1) {
                iIndexOf2 = str.indexOf(45, iIndexOf);
            }
            if (iIndexOf2 == -1) {
                throw new IllegalArgumentException("Invalid timestamp: Missing valid timezone offset: " + str);
            }
            int nanos = 0;
            String strSubstring = str.substring(0, iIndexOf2);
            String strSubstring2 = "";
            int iIndexOf3 = strSubstring.indexOf(46);
            if (iIndexOf3 != -1) {
                String strSubstring3 = strSubstring.substring(0, iIndexOf3);
                strSubstring2 = strSubstring.substring(iIndexOf3 + 1);
                strSubstring = strSubstring3;
            }
            long time = this.timestampFormat.parse(strSubstring).getTime() / 1000;
            if (!strSubstring2.isEmpty()) {
                nanos = parseNanos(strSubstring2);
            }
            if (str.charAt(iIndexOf2) == 'Z') {
                if (str.length() != iIndexOf2 + 1) {
                    throw new IllegalArgumentException("Invalid timestamp: Invalid trailing data \"" + str.substring(iIndexOf2) + "\"");
                }
            } else {
                long jDecodeTimezoneOffset = decodeTimezoneOffset(str.substring(iIndexOf2 + 1));
                time = str.charAt(iIndexOf2) == '+' ? time - jDecodeTimezoneOffset : time + jDecodeTimezoneOffset;
            }
            return new Timestamp(time, nanos);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Failed to parse timestamp", e);
        }
    }

    private Timestamp decodeTimestamp(Object obj) throws JSONException {
        if (obj instanceof String) {
            return decodeTimestamp((String) obj);
        }
        if (!(obj instanceof JSONObject)) {
            throw new IllegalArgumentException("Timestamps must be either ISO 8601-formatted strings or JSON objects");
        }
        return decodeTimestamp((JSONObject) obj);
    }

    private void decodeTimestamp(Value.Builder builder, Object obj) throws JSONException {
        Timestamp timestampDecodeTimestamp = decodeTimestamp(obj);
        builder.setTimestampValue(com.google.protobuf.Timestamp.newBuilder().setSeconds(timestampDecodeTimestamp.getSeconds()).setNanos(timestampDecodeTimestamp.getNanoseconds()));
    }

    private static int parseNanos(String str) {
        int iCharAt = 0;
        for (int i = 0; i < 9; i++) {
            iCharAt *= 10;
            if (i < str.length()) {
                if (str.charAt(i) < '0' || str.charAt(i) > '9') {
                    throw new IllegalArgumentException("Invalid nanoseconds: " + str);
                }
                iCharAt += str.charAt(i) - '0';
            }
        }
        return iCharAt;
    }

    private static long decodeTimezoneOffset(String str) {
        int iIndexOf = str.indexOf(58);
        if (iIndexOf == -1) {
            throw new IllegalArgumentException("Invalid offset value: " + str);
        }
        return ((Long.parseLong(str.substring(0, iIndexOf)) * 60) + Long.parseLong(str.substring(iIndexOf + 1))) * 60;
    }

    private FieldFilter.Operator decodeFieldFilterOperator(String str) {
        return FieldFilter.Operator.valueOf(str);
    }

    private void decodeUnaryFilter(List<Filter> list, JSONObject jSONObject) throws JSONException {
        FieldPath fieldPathDecodeFieldReference;
        String string;
        fieldPathDecodeFieldReference = decodeFieldReference(jSONObject.getJSONObject("field"));
        string = jSONObject.getString("op");
        string.hashCode();
        switch (string) {
            case "IS_NAN":
                list.add(FieldFilter.create(fieldPathDecodeFieldReference, FieldFilter.Operator.EQUAL, Values.NAN_VALUE));
                return;
            case "IS_NULL":
                list.add(FieldFilter.create(fieldPathDecodeFieldReference, FieldFilter.Operator.EQUAL, Values.NULL_VALUE));
                return;
            case "IS_NOT_NAN":
                list.add(FieldFilter.create(fieldPathDecodeFieldReference, FieldFilter.Operator.NOT_EQUAL, Values.NAN_VALUE));
                return;
            case "IS_NOT_NULL":
                list.add(FieldFilter.create(fieldPathDecodeFieldReference, FieldFilter.Operator.NOT_EQUAL, Values.NULL_VALUE));
                return;
            default:
                throw new IllegalArgumentException("Unexpected unary filter: " + string);
        }
    }

    private FieldPath decodeFieldReference(JSONObject jSONObject) throws JSONException {
        return FieldPath.fromServerFormat(jSONObject.getString("fieldPath"));
    }

    private Query.LimitType decodeLimitType(JSONObject jSONObject) {
        String strOptString = jSONObject.optString("limitType", "FIRST");
        if (strOptString.equals("FIRST")) {
            return Query.LimitType.LIMIT_TO_FIRST;
        }
        if (strOptString.equals("LAST")) {
            return Query.LimitType.LIMIT_TO_LAST;
        }
        throw new IllegalArgumentException("Invalid limit type for bundle query: " + strOptString);
    }

    private void verifyCollectionSelector(JSONArray jSONArray) {
        if (jSONArray.length() != 1) {
            throw new IllegalArgumentException("Only queries with a single 'from' clause are supported by the Android SDK");
        }
    }

    private void verifyNoOffset(JSONObject jSONObject) {
        if (jSONObject.has("offset")) {
            throw new IllegalArgumentException("Queries with offsets are not supported by the Android SDK");
        }
    }

    private void verifyNoSelect(JSONObject jSONObject) {
        if (jSONObject.has("select")) {
            throw new IllegalArgumentException("Queries with 'select' statements are not supported by the Android SDK");
        }
    }
}
