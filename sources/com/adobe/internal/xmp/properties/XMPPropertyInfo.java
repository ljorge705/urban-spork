package com.adobe.internal.xmp.properties;

import com.adobe.internal.xmp.options.PropertyOptions;

/* loaded from: classes5.dex */
public interface XMPPropertyInfo extends XMPProperty {
    String getNamespace();

    @Override // com.adobe.internal.xmp.properties.XMPProperty
    PropertyOptions getOptions();

    String getPath();

    @Override // com.adobe.internal.xmp.properties.XMPProperty
    String getValue();
}
