package com.drew.lang;

import com.drew.metadata.StringValue;

/* loaded from: classes5.dex */
public class KeyValuePair {
    private final String _key;
    private final StringValue _value;

    public String getKey() {
        return this._key;
    }

    public StringValue getValue() {
        return this._value;
    }

    public KeyValuePair(String str, StringValue stringValue) {
        this._key = str;
        this._value = stringValue;
    }
}
