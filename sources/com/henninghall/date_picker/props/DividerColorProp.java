package com.henninghall.date_picker.props;

import com.facebook.react.bridge.Dynamic;

/* loaded from: classes2.dex */
public class DividerColorProp extends Prop<String> {
    public static final String name = "dividerColor";

    @Override // com.henninghall.date_picker.props.Prop
    public String toValue(Dynamic dynamic) {
        return dynamic.asString();
    }
}
