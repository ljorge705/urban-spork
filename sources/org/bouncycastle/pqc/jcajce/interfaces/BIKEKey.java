package org.bouncycastle.pqc.jcajce.interfaces;

import java.security.Key;
import org.bouncycastle.pqc.jcajce.spec.BIKEParameterSpec;

/* loaded from: classes4.dex */
public interface BIKEKey extends Key {
    BIKEParameterSpec getParameterSpec();
}
