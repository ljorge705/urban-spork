package io.grpc.internal;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Logger;

/* loaded from: classes6.dex */
public final class ReflectionLongAdderCounter implements LongCounter {
    private static final Method addMethod;
    private static final Constructor<?> defaultConstructor;
    private static final RuntimeException initializationException;
    private static final Logger logger = Logger.getLogger(ReflectionLongAdderCounter.class.getName());
    private static final Object[] one;
    private static final Method sumMethod;
    private final Object instance;

    static boolean isAvailable() {
        return initializationException == null;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x005f  */
    static {
        /*
            java.lang.Class<io.grpc.internal.ReflectionLongAdderCounter> r0 = io.grpc.internal.ReflectionLongAdderCounter.class
            java.lang.String r0 = r0.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            io.grpc.internal.ReflectionLongAdderCounter.logger = r0
            r0 = 0
            java.lang.String r1 = "java.util.concurrent.atomic.LongAdder"
            java.lang.Class r1 = java.lang.Class.forName(r1)     // Catch: java.lang.Throwable -> L45
            java.lang.String r2 = "add"
            r3 = 1
            java.lang.Class[] r3 = new java.lang.Class[r3]     // Catch: java.lang.Throwable -> L45
            java.lang.Class r4 = java.lang.Long.TYPE     // Catch: java.lang.Throwable -> L45
            r5 = 0
            r3[r5] = r4     // Catch: java.lang.Throwable -> L45
            java.lang.reflect.Method r2 = r1.getMethod(r2, r3)     // Catch: java.lang.Throwable -> L45
            java.lang.String r3 = "sum"
            java.lang.Class[] r4 = new java.lang.Class[r5]     // Catch: java.lang.Throwable -> L42
            java.lang.reflect.Method r3 = r1.getMethod(r3, r4)     // Catch: java.lang.Throwable -> L42
            java.lang.reflect.Constructor[] r1 = r1.getConstructors()     // Catch: java.lang.Throwable -> L40
            int r4 = r1.length     // Catch: java.lang.Throwable -> L40
        L2e:
            if (r5 >= r4) goto L3d
            r6 = r1[r5]     // Catch: java.lang.Throwable -> L40
            java.lang.Class[] r7 = r6.getParameterTypes()     // Catch: java.lang.Throwable -> L40
            int r7 = r7.length     // Catch: java.lang.Throwable -> L40
            if (r7 != 0) goto L3a
            goto L3e
        L3a:
            int r5 = r5 + 1
            goto L2e
        L3d:
            r6 = r0
        L3e:
            r1 = r0
            goto L52
        L40:
            r1 = move-exception
            goto L48
        L42:
            r1 = move-exception
            r3 = r0
            goto L48
        L45:
            r1 = move-exception
            r2 = r0
            r3 = r2
        L48:
            java.util.logging.Logger r4 = io.grpc.internal.ReflectionLongAdderCounter.logger
            java.util.logging.Level r5 = java.util.logging.Level.FINE
            java.lang.String r6 = "LongAdder can not be found via reflection, this is normal for JDK7 and below"
            r4.log(r5, r6, r1)
            r6 = r0
        L52:
            if (r1 != 0) goto L5f
            if (r6 == 0) goto L5f
            io.grpc.internal.ReflectionLongAdderCounter.defaultConstructor = r6
            io.grpc.internal.ReflectionLongAdderCounter.addMethod = r2
            io.grpc.internal.ReflectionLongAdderCounter.sumMethod = r3
            io.grpc.internal.ReflectionLongAdderCounter.initializationException = r0
            goto L6c
        L5f:
            io.grpc.internal.ReflectionLongAdderCounter.defaultConstructor = r0
            io.grpc.internal.ReflectionLongAdderCounter.addMethod = r0
            io.grpc.internal.ReflectionLongAdderCounter.sumMethod = r0
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            r0.<init>(r1)
            io.grpc.internal.ReflectionLongAdderCounter.initializationException = r0
        L6c:
            r0 = 1
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            io.grpc.internal.ReflectionLongAdderCounter.one = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.internal.ReflectionLongAdderCounter.<clinit>():void");
    }

    ReflectionLongAdderCounter() {
        RuntimeException runtimeException = initializationException;
        if (runtimeException != null) {
            throw runtimeException;
        }
        try {
            this.instance = defaultConstructor.newInstance(new Object[0]);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e2) {
            throw new RuntimeException(e2);
        } catch (InvocationTargetException e3) {
            throw new RuntimeException(e3);
        }
    }

    @Override // io.grpc.internal.LongCounter
    public void add(long j) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        try {
            addMethod.invoke(this.instance, j == 1 ? one : new Object[]{Long.valueOf(j)});
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e2) {
            throw new RuntimeException(e2);
        }
    }

    @Override // io.grpc.internal.LongCounter
    public long value() {
        try {
            return ((Long) sumMethod.invoke(this.instance, new Object[0])).longValue();
        } catch (IllegalAccessException unused) {
            throw new RuntimeException();
        } catch (InvocationTargetException unused2) {
            throw new RuntimeException();
        }
    }
}
