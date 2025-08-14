package com.reactnativecommunity.slider;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.TouchesHelper;

/* loaded from: classes6.dex */
public class ReactSlidingCompleteEvent extends Event<ReactSlidingCompleteEvent> {
    public static final String EVENT_NAME = "topSlidingComplete";
    private final double mValue;

    @Override // com.facebook.react.uimanager.events.Event
    public boolean canCoalesce() {
        return false;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public short getCoalescingKey() {
        return (short) 0;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return EVENT_NAME;
    }

    public double getValue() {
        return this.mValue;
    }

    public ReactSlidingCompleteEvent(int i, double d) {
        super(i);
        this.mValue = d;
    }

    @Override // com.facebook.react.uimanager.events.Event
    protected WritableMap getEventData() {
        return serializeEventData();
    }

    private WritableMap serializeEventData() {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putInt(TouchesHelper.TARGET_KEY, getViewTag());
        writableMapCreateMap.putDouble("value", getValue());
        return writableMapCreateMap;
    }
}
