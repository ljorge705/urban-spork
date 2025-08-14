package org.bouncycastle.jcajce.provider.symmetric.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.InvalidAlgorithmParameterException;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.crypto.params.AEADParameters;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.internal.asn1.cms.GCMParameters;
import org.bouncycastle.util.Integers;

/* loaded from: classes4.dex */
public class GcmSpecUtil {
    private static final Constructor constructor;
    static final Class gcmSpecClass;
    private static final Method iv;
    private static final Method tLen;

    static {
        Method methodExtractMethod;
        Class clsLoadClass = ClassUtil.loadClass(GcmSpecUtil.class, "javax.crypto.spec.GCMParameterSpec");
        gcmSpecClass = clsLoadClass;
        if (clsLoadClass != null) {
            constructor = extractConstructor();
            tLen = extractMethod("getTLen");
            methodExtractMethod = extractMethod("getIV");
        } else {
            methodExtractMethod = null;
            constructor = null;
            tLen = null;
        }
        iv = methodExtractMethod;
    }

    static AEADParameters extractAeadParameters(final KeyParameter keyParameter, final AlgorithmParameterSpec algorithmParameterSpec) throws InvalidAlgorithmParameterException {
        try {
            return (AEADParameters) AccessController.doPrivileged(new PrivilegedExceptionAction() { // from class: org.bouncycastle.jcajce.provider.symmetric.util.GcmSpecUtil.3
                @Override // java.security.PrivilegedExceptionAction
                public Object run() throws Exception {
                    return new AEADParameters(keyParameter, ((Integer) GcmSpecUtil.tLen.invoke(algorithmParameterSpec, new Object[0])).intValue(), (byte[]) GcmSpecUtil.iv.invoke(algorithmParameterSpec, new Object[0]));
                }
            });
        } catch (Exception unused) {
            throw new InvalidAlgorithmParameterException("Cannot process GCMParameterSpec.");
        }
    }

    private static Constructor extractConstructor() {
        try {
            return (Constructor) AccessController.doPrivileged(new PrivilegedExceptionAction() { // from class: org.bouncycastle.jcajce.provider.symmetric.util.GcmSpecUtil.1
                @Override // java.security.PrivilegedExceptionAction
                public Object run() throws Exception {
                    return GcmSpecUtil.gcmSpecClass.getConstructor(Integer.TYPE, byte[].class);
                }
            });
        } catch (PrivilegedActionException unused) {
            return null;
        }
    }

    public static GCMParameters extractGcmParameters(final AlgorithmParameterSpec algorithmParameterSpec) throws InvalidParameterSpecException {
        try {
            return (GCMParameters) AccessController.doPrivileged(new PrivilegedExceptionAction() { // from class: org.bouncycastle.jcajce.provider.symmetric.util.GcmSpecUtil.4
                @Override // java.security.PrivilegedExceptionAction
                public Object run() throws Exception {
                    return new GCMParameters((byte[]) GcmSpecUtil.iv.invoke(algorithmParameterSpec, new Object[0]), ((Integer) GcmSpecUtil.tLen.invoke(algorithmParameterSpec, new Object[0])).intValue() / 8);
                }
            });
        } catch (Exception unused) {
            throw new InvalidParameterSpecException("Cannot process GCMParameterSpec");
        }
    }

    public static AlgorithmParameterSpec extractGcmSpec(ASN1Primitive aSN1Primitive) throws InvalidParameterSpecException {
        try {
            GCMParameters gCMParameters = GCMParameters.getInstance(aSN1Primitive);
            return (AlgorithmParameterSpec) constructor.newInstance(Integers.valueOf(gCMParameters.getIcvLen() * 8), gCMParameters.getNonce());
        } catch (Exception e) {
            throw new InvalidParameterSpecException("Construction failed: " + e.getMessage());
        }
    }

    private static Method extractMethod(final String str) {
        try {
            return (Method) AccessController.doPrivileged(new PrivilegedExceptionAction() { // from class: org.bouncycastle.jcajce.provider.symmetric.util.GcmSpecUtil.2
                @Override // java.security.PrivilegedExceptionAction
                public Object run() throws Exception {
                    return GcmSpecUtil.gcmSpecClass.getDeclaredMethod(str, new Class[0]);
                }
            });
        } catch (PrivilegedActionException unused) {
            return null;
        }
    }

    public static boolean gcmSpecExists() {
        return gcmSpecClass != null;
    }

    public static boolean gcmSpecExtractable() {
        return constructor != null;
    }

    public static boolean isGcmSpec(Class cls) {
        return gcmSpecClass == cls;
    }

    public static boolean isGcmSpec(AlgorithmParameterSpec algorithmParameterSpec) {
        Class cls = gcmSpecClass;
        return cls != null && cls.isInstance(algorithmParameterSpec);
    }
}
