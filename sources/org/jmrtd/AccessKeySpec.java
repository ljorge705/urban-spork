package org.jmrtd;

import java.io.Serializable;
import java.security.spec.KeySpec;

/* loaded from: classes4.dex */
public interface AccessKeySpec extends Serializable, KeySpec {
    String getAlgorithm();

    byte[] getKey();
}
