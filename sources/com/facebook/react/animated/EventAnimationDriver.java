package com.facebook.react.animated;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.UnexpectedNativeTypeException;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTModernEventEmitter;
import com.facebook.react.uimanager.events.TouchEvent;
import java.util.List;

/* loaded from: classes5.dex */
class EventAnimationDriver implements RCTModernEventEmitter {
    String mEventName;
    private List<String> mEventPath;
    ValueAnimatedNode mValueNode;
    int mViewTag;

    public EventAnimationDriver(String str, int i, List<String> list, ValueAnimatedNode valueAnimatedNode) {
        this.mEventName = str;
        this.mViewTag = i;
        this.mEventPath = list;
        this.mValueNode = valueAnimatedNode;
    }

    @Override // com.facebook.react.uimanager.events.RCTEventEmitter
    public void receiveEvent(int i, String str, WritableMap writableMap) throws NumberFormatException {
        receiveEvent(-1, i, str, writableMap);
    }

    @Override // com.facebook.react.uimanager.events.RCTModernEventEmitter
    public void receiveEvent(int i, int i2, String str, WritableMap writableMap) throws NumberFormatException {
        receiveEvent(i, i2, str, false, 0, writableMap, 2);
    }

    @Override // com.facebook.react.uimanager.events.RCTEventEmitter
    public void receiveTouches(String str, WritableArray writableArray, WritableArray writableArray2) {
        throw new UnsupportedOperationException("receiveTouches is not support by native animated events");
    }

    @Override // com.facebook.react.uimanager.events.RCTModernEventEmitter
    public void receiveTouches(TouchEvent touchEvent) {
        throw new UnsupportedOperationException("receiveTouches is not support by native animated events");
    }

    @Override // com.facebook.react.uimanager.events.RCTModernEventEmitter
    public void receiveEvent(int i, int i2, String str, boolean z, int i3, WritableMap writableMap, int i4) throws NumberFormatException {
        ReadableArray array;
        ReadableMap map;
        if (writableMap == null) {
            throw new IllegalArgumentException("Native animated events must have event data.");
        }
        ReadableArray array2 = null;
        for (int i5 = 0; i5 < this.mEventPath.size() - 1; i5++) {
            if (writableMap != null) {
                String str2 = this.mEventPath.get(i5);
                ReadableType type = writableMap.getType(str2);
                if (type == ReadableType.Map) {
                    map = writableMap.getMap(str2);
                    array = null;
                } else if (type == ReadableType.Array) {
                    array = writableMap.getArray(str2);
                    map = null;
                } else {
                    throw new UnexpectedNativeTypeException("Unexpected type " + type + " for key '" + str2 + "'");
                }
                writableMap = map;
                array2 = array;
            } else {
                int i6 = Integer.parseInt(this.mEventPath.get(i5));
                ReadableType type2 = array2.getType(i6);
                if (type2 == ReadableType.Map) {
                    writableMap = array2.getMap(i6);
                    array2 = null;
                } else if (type2 == ReadableType.Array) {
                    array2 = array2.getArray(i6);
                    writableMap = null;
                } else {
                    throw new UnexpectedNativeTypeException("Unexpected type " + type2 + " for index '" + i6 + "'");
                }
            }
        }
        String str3 = this.mEventPath.get(r2.size() - 1);
        if (writableMap != null) {
            this.mValueNode.mValue = writableMap.getDouble(str3);
        } else {
            this.mValueNode.mValue = array2.getDouble(Integer.parseInt(str3));
        }
    }
}
