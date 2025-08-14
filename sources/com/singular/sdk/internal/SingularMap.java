package com.singular.sdk.internal;

import java.util.HashMap;

/* loaded from: classes6.dex */
public abstract class SingularMap extends HashMap<String, String> {
    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public String put(String str, String str2) {
        if (Utils.isEmptyOrNull(str2)) {
            return null;
        }
        return (String) super.put((SingularMap) str, str2);
    }
}
