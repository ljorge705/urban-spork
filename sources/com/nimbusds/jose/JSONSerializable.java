package com.nimbusds.jose;

import java.util.Map;

/* loaded from: classes2.dex */
public interface JSONSerializable {
    Map<String, Object> toFlattenedJSONObject();

    Map<String, Object> toGeneralJSONObject();
}
