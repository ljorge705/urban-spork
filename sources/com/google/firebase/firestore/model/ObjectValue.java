package com.google.firebase.firestore.model;

import com.google.firebase.firestore.model.mutation.FieldMask;
import com.google.firebase.firestore.util.Assert;
import com.google.firestore.v1.MapValue;
import com.google.firestore.v1.Value;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* loaded from: classes2.dex */
public final class ObjectValue implements Cloneable {
    private final Map<String, Object> overlayMap;
    private Value partialValue;

    public static ObjectValue fromMap(Map<String, Value> map) {
        return new ObjectValue(Value.newBuilder().setMapValue(MapValue.newBuilder().putAllFields(map)).build());
    }

    public ObjectValue(Value value) {
        this.overlayMap = new HashMap();
        Assert.hardAssert(value.getValueTypeCase() == Value.ValueTypeCase.MAP_VALUE, "ObjectValues should be backed by a MapValue", new Object[0]);
        Assert.hardAssert(!ServerTimestamps.isServerTimestamp(value), "ServerTimestamps should not be used as an ObjectValue", new Object[0]);
        this.partialValue = value;
    }

    public ObjectValue() {
        this(Value.newBuilder().setMapValue(MapValue.getDefaultInstance()).build());
    }

    public Map<String, Value> getFieldsMap() {
        return buildProto().getMapValue().getFieldsMap();
    }

    public FieldMask getFieldMask() {
        return extractFieldMask(buildProto().getMapValue());
    }

    private FieldMask extractFieldMask(MapValue mapValue) {
        HashSet hashSet = new HashSet();
        for (Map.Entry<String, Value> entry : mapValue.getFieldsMap().entrySet()) {
            FieldPath fieldPathFromSingleSegment = FieldPath.fromSingleSegment(entry.getKey());
            if (Values.isMapValue(entry.getValue())) {
                Set<FieldPath> mask = extractFieldMask(entry.getValue().getMapValue()).getMask();
                if (mask.isEmpty()) {
                    hashSet.add(fieldPathFromSingleSegment);
                } else {
                    Iterator<FieldPath> it = mask.iterator();
                    while (it.hasNext()) {
                        hashSet.add(fieldPathFromSingleSegment.append(it.next()));
                    }
                }
            } else {
                hashSet.add(fieldPathFromSingleSegment);
            }
        }
        return FieldMask.fromSet(hashSet);
    }

    public Value get(FieldPath fieldPath) {
        return extractNestedValue(buildProto(), fieldPath);
    }

    private Value extractNestedValue(Value value, FieldPath fieldPath) {
        if (fieldPath.isEmpty()) {
            return value;
        }
        for (int i = 0; i < fieldPath.length() - 1; i++) {
            value = value.getMapValue().getFieldsOrDefault(fieldPath.getSegment(i), null);
            if (!Values.isMapValue(value)) {
                return null;
            }
        }
        return value.getMapValue().getFieldsOrDefault(fieldPath.getLastSegment(), null);
    }

    private Value buildProto() {
        synchronized (this.overlayMap) {
            MapValue mapValueApplyOverlay = applyOverlay(FieldPath.EMPTY_PATH, this.overlayMap);
            if (mapValueApplyOverlay != null) {
                this.partialValue = Value.newBuilder().setMapValue(mapValueApplyOverlay).build();
                this.overlayMap.clear();
            }
        }
        return this.partialValue;
    }

    public void delete(FieldPath fieldPath) {
        Assert.hardAssert(!fieldPath.isEmpty(), "Cannot delete field for empty path on ObjectValue", new Object[0]);
        setOverlay(fieldPath, null);
    }

    public void set(FieldPath fieldPath, Value value) {
        Assert.hardAssert(!fieldPath.isEmpty(), "Cannot set field for empty path on ObjectValue", new Object[0]);
        setOverlay(fieldPath, value);
    }

    public void setAll(Map<FieldPath, Value> map) {
        for (Map.Entry<FieldPath, Value> entry : map.entrySet()) {
            FieldPath key = entry.getKey();
            if (entry.getValue() == null) {
                delete(key);
            } else {
                set(key, entry.getValue());
            }
        }
    }

    private void setOverlay(FieldPath fieldPath, Value value) {
        Map<String, Object> map;
        Map<String, Object> map2 = this.overlayMap;
        for (int i = 0; i < fieldPath.length() - 1; i++) {
            String segment = fieldPath.getSegment(i);
            Object obj = map2.get(segment);
            if (obj instanceof Map) {
                map = (Map) obj;
            } else {
                if (obj instanceof Value) {
                    Value value2 = (Value) obj;
                    if (value2.getValueTypeCase() == Value.ValueTypeCase.MAP_VALUE) {
                        HashMap map3 = new HashMap(value2.getMapValue().getFieldsMap());
                        map2.put(segment, map3);
                        map2 = map3;
                    }
                }
                map = new HashMap<>();
                map2.put(segment, map);
            }
            map2 = map;
        }
        map2.put(fieldPath.getLastSegment(), value);
    }

    private MapValue applyOverlay(FieldPath fieldPath, Map<String, Object> map) {
        MapValue.Builder builderNewBuilder;
        Value valueExtractNestedValue = extractNestedValue(this.partialValue, fieldPath);
        if (Values.isMapValue(valueExtractNestedValue)) {
            builderNewBuilder = valueExtractNestedValue.getMapValue().toBuilder();
        } else {
            builderNewBuilder = MapValue.newBuilder();
        }
        boolean z = false;
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof Map) {
                MapValue mapValueApplyOverlay = applyOverlay(fieldPath.append(key), (Map) value);
                if (mapValueApplyOverlay != null) {
                    builderNewBuilder.putFields(key, Value.newBuilder().setMapValue(mapValueApplyOverlay).build());
                    z = true;
                }
            } else {
                if (value instanceof Value) {
                    builderNewBuilder.putFields(key, (Value) value);
                } else if (builderNewBuilder.containsFields(key)) {
                    Assert.hardAssert(value == null, "Expected entry to be a Map, a Value or null", new Object[0]);
                    builderNewBuilder.removeFields(key);
                }
                z = true;
            }
        }
        if (z) {
            return builderNewBuilder.build();
        }
        return null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ObjectValue) {
            return Values.equals(buildProto(), ((ObjectValue) obj).buildProto());
        }
        return false;
    }

    public int hashCode() {
        return buildProto().hashCode();
    }

    public String toString() {
        return "ObjectValue{internalValue=" + Values.canonicalId(buildProto()) + AbstractJsonLexerKt.END_OBJ;
    }

    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public ObjectValue m5329clone() {
        return new ObjectValue(buildProto());
    }
}
