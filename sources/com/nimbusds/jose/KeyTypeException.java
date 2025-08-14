package com.nimbusds.jose;

import java.security.Key;
import java.util.Arrays;

/* loaded from: classes2.dex */
public class KeyTypeException extends KeyException {
    public KeyTypeException(Class<? extends Key> cls) {
        super("Invalid key: Must be an instance of " + cls);
    }

    public KeyTypeException(Class<? extends Key> cls, Class<?>... clsArr) {
        super("Invalid key: Must be an instance of " + cls + " and implement all of the following interfaces " + Arrays.toString(clsArr));
    }
}
