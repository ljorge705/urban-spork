package org.bouncycastle.pqc.jcajce.interfaces;

import java.security.Key;
import org.bouncycastle.pqc.jcajce.spec.FalconParameterSpec;

/* loaded from: classes4.dex */
public interface FalconKey extends Key {
    FalconParameterSpec getParameterSpec();
}
