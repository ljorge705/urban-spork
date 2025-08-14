package androidx.compose.ui.tooling;

import androidx.compose.runtime.Composer;
import androidx.compose.ui.tooling.PreviewLogger;
import androidx.exifinterface.media.ExifInterface;
import com.clevertap.android.sdk.variables.CTVariableUtils;
import com.facebook.react.util.JSStackTrace;
import com.singular.sdk.internal.Constants;
import io.sentry.profilemeasurements.ProfileMeasurement;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.ClassUtils;

/* compiled from: ComposableInvoker.kt */
@Deprecated(message = "Use androidx.compose.runtime.reflect.ComposableMethodInvoker instead")
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004H\u0002J1\u0010\t\u001a\u00020\n2\u0010\u0010\u000b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\r0\f2\u0010\u0010\u000e\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\r0\fH\u0002¢\u0006\u0002\u0010\u000fJ\u0010\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0004H\u0002J=\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u00172\u0016\u0010\u0018\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\f\"\u0004\u0018\u00010\u0001H\u0007¢\u0006\u0002\u0010\u0019J(\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u001b0\f\"\u0006\b\u0000\u0010\u001b\u0018\u0001*\u0002H\u001b2\u0006\u0010\u001c\u001a\u00020\u0004H\u0082\b¢\u0006\u0002\u0010\u001dJ5\u0010\u001e\u001a\u00020\u001f*\u0006\u0012\u0002\b\u00030\r2\u0006\u0010\u0015\u001a\u00020\u00142\u0016\u0010\u0018\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\f\"\u0004\u0018\u00010\u0001H\u0002¢\u0006\u0002\u0010 J9\u0010!\u001a\u00020\u001f*\u0006\u0012\u0002\b\u00030\r2\u0006\u0010\u0015\u001a\u00020\u00142\u001a\u0010\u0018\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\r0\f\"\u0006\u0012\u0002\b\u00030\rH\u0002¢\u0006\u0002\u0010\"J\u0012\u0010#\u001a\u0004\u0018\u00010\u0001*\u0006\u0012\u0002\b\u00030\rH\u0002J=\u0010$\u001a\u0004\u0018\u00010\u0001*\u00020\u001f2\b\u0010%\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0016\u001a\u00020\u00172\u0016\u0010\u0018\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\f\"\u0004\u0018\u00010\u0001H\u0002¢\u0006\u0002\u0010&R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Landroidx/compose/ui/tooling/ComposableInvoker;", "", "()V", "BITS_PER_INT", "", "SLOTS_PER_INT", "changedParamCount", "realValueParams", "thisParams", "compatibleTypes", "", "methodTypes", "", "Ljava/lang/Class;", "actualTypes", "([Ljava/lang/Class;[Ljava/lang/Class;)Z", "defaultParamCount", "invokeComposable", "", "className", "", JSStackTrace.METHOD_NAME_KEY, "composer", "Landroidx/compose/runtime/Composer;", "args", "(Ljava/lang/String;Ljava/lang/String;Landroidx/compose/runtime/Composer;[Ljava/lang/Object;)V", "dup", ExifInterface.GPS_DIRECTION_TRUE, "count", "(Ljava/lang/Object;I)[Ljava/lang/Object;", "findComposableMethod", "Ljava/lang/reflect/Method;", "(Ljava/lang/Class;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/reflect/Method;", "getDeclaredCompatibleMethod", "(Ljava/lang/Class;Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;", "getDefaultValue", "invokeComposableMethod", "instance", "(Ljava/lang/reflect/Method;Ljava/lang/Object;Landroidx/compose/runtime/Composer;[Ljava/lang/Object;)Ljava/lang/Object;", "ui-tooling_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ComposableInvoker {
    public static final int $stable = 0;
    private static final int BITS_PER_INT = 31;
    public static final ComposableInvoker INSTANCE = new ComposableInvoker();
    private static final int SLOTS_PER_INT = 10;

    private ComposableInvoker() {
    }

    private final boolean compatibleTypes(Class<?>[] methodTypes, Class<?>[] actualTypes) {
        if (methodTypes.length != actualTypes.length) {
            return false;
        }
        ArrayList arrayList = new ArrayList(methodTypes.length);
        int length = methodTypes.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            arrayList.add(Boolean.valueOf(methodTypes[i].isAssignableFrom(actualTypes[i2])));
            i++;
            i2++;
        }
        ArrayList arrayList2 = arrayList;
        if (!(arrayList2 instanceof Collection) || !arrayList2.isEmpty()) {
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                if (!((Boolean) it.next()).booleanValue()) {
                    return false;
                }
            }
        }
        return true;
    }

    private final Method getDeclaredCompatibleMethod(Class<?> cls, String str, Class<?>... clsArr) throws NoSuchMethodException, SecurityException {
        Method method;
        Class<?>[] clsArr2 = (Class[]) Arrays.copyOf(clsArr, clsArr.length);
        Method[] declaredMethods = cls.getDeclaredMethods();
        Intrinsics.checkNotNullExpressionValue(declaredMethods, "declaredMethods");
        Method[] methodArr = declaredMethods;
        int length = methodArr.length;
        int i = 0;
        while (true) {
            method = null;
            if (i >= length) {
                break;
            }
            Method method2 = methodArr[i];
            Method method3 = method2;
            if (!Intrinsics.areEqual(str, method3.getName())) {
                String name = method3.getName();
                Intrinsics.checkNotNullExpressionValue(name, "it.name");
                if (!StringsKt.startsWith$default(name, str + '-', false, 2, (Object) null)) {
                    continue;
                }
                i++;
            }
            ComposableInvoker composableInvoker = INSTANCE;
            Class<?>[] parameterTypes = method3.getParameterTypes();
            Intrinsics.checkNotNullExpressionValue(parameterTypes, "it.parameterTypes");
            if (composableInvoker.compatibleTypes(parameterTypes, clsArr2)) {
                method = method2;
                break;
            }
            i++;
        }
        Method method4 = method;
        if (method4 != null) {
            return method4;
        }
        throw new NoSuchMethodException(str + " not found");
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final /* synthetic */ <T> T[] dup(T t, int i) {
        IntRange intRangeUntil = RangesKt.until(0, i);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(intRangeUntil, 10));
        Iterator<Integer> it = intRangeUntil.iterator();
        while (it.hasNext()) {
            ((IntIterator) it).nextInt();
            arrayList.add(t);
        }
        Intrinsics.reifiedOperationMarker(0, "T?");
        return (T[]) arrayList.toArray(new Object[0]);
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x00cf A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00d0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final java.lang.reflect.Method findComposableMethod(java.lang.Class<?> r9, java.lang.String r10, java.lang.Object... r11) throws java.lang.NoSuchMethodException, java.lang.SecurityException {
        /*
            Method dump skipped, instructions count: 241
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.tooling.ComposableInvoker.findComposableMethod(java.lang.Class, java.lang.String, java.lang.Object[]):java.lang.reflect.Method");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private final Object getDefaultValue(Class<?> cls) {
        String name = cls.getName();
        if (name != null) {
            switch (name.hashCode()) {
                case -1325958191:
                    if (name.equals("double")) {
                        return Double.valueOf(0.0d);
                    }
                    break;
                case 104431:
                    if (name.equals("int")) {
                        return 0;
                    }
                    break;
                case 3039496:
                    if (name.equals(ProfileMeasurement.UNIT_BYTES)) {
                        return (byte) 0;
                    }
                    break;
                case 3052374:
                    if (name.equals("char")) {
                        return (char) 0;
                    }
                    break;
                case 3327612:
                    if (name.equals(Constants.LONG)) {
                        return 0L;
                    }
                    break;
                case 64711720:
                    if (name.equals(CTVariableUtils.BOOLEAN)) {
                        return false;
                    }
                    break;
                case 97526364:
                    if (name.equals("float")) {
                        return Float.valueOf(0.0f);
                    }
                    break;
                case 109413500:
                    if (name.equals("short")) {
                        return (short) 0;
                    }
                    break;
            }
        }
        return null;
    }

    private final Object invokeComposableMethod(Method method, Object obj, Composer composer, Object... objArr) {
        Object defaultValue;
        Class<?>[] parameterTypes = method.getParameterTypes();
        Intrinsics.checkNotNullExpressionValue(parameterTypes, "parameterTypes");
        Class<?>[] clsArr = parameterTypes;
        int i = -1;
        int length = clsArr.length - 1;
        if (length >= 0) {
            while (true) {
                int i2 = length - 1;
                if (Intrinsics.areEqual(clsArr[length], Composer.class)) {
                    i = length;
                    break;
                }
                if (i2 < 0) {
                    break;
                }
                length = i2;
            }
        }
        int i3 = i + 1;
        int iChangedParamCount = changedParamCount(i, obj != null ? 1 : 0) + i3;
        int length2 = method.getParameterTypes().length;
        if ((length2 != iChangedParamCount ? defaultParamCount(i) : 0) + iChangedParamCount != length2) {
            throw new IllegalStateException("Check failed.".toString());
        }
        Object[] objArr2 = new Object[length2];
        for (int i4 = 0; i4 < length2; i4++) {
            if (i4 < 0 || i4 >= i) {
                if (i4 == i) {
                    defaultValue = composer;
                } else if (i3 <= i4 && i4 < iChangedParamCount) {
                    defaultValue = 0;
                } else {
                    if (iChangedParamCount > i4 || i4 >= length2) {
                        throw new IllegalStateException("Unexpected index".toString());
                    }
                    defaultValue = 2097151;
                }
            } else if (i4 < 0 || i4 > ArraysKt.getLastIndex(objArr)) {
                ComposableInvoker composableInvoker = INSTANCE;
                Class<?> cls = method.getParameterTypes()[i4];
                Intrinsics.checkNotNullExpressionValue(cls, "parameterTypes[idx]");
                defaultValue = composableInvoker.getDefaultValue(cls);
            } else {
                defaultValue = objArr[i4];
            }
            objArr2[i4] = defaultValue;
        }
        return method.invoke(obj, Arrays.copyOf(objArr2, length2));
    }

    private final int changedParamCount(int realValueParams, int thisParams) {
        if (realValueParams == 0) {
            return 1;
        }
        return (int) Math.ceil((realValueParams + thisParams) / 10.0d);
    }

    private final int defaultParamCount(int realValueParams) {
        return (int) Math.ceil(realValueParams / 31.0d);
    }

    public final void invokeComposable(String className, String methodName, Composer composer, Object... args) throws SecurityException, ReflectiveOperationException {
        Intrinsics.checkNotNullParameter(className, "className");
        Intrinsics.checkNotNullParameter(methodName, "methodName");
        Intrinsics.checkNotNullParameter(composer, "composer");
        Intrinsics.checkNotNullParameter(args, "args");
        try {
            Class<?> composableClass = Class.forName(className);
            Intrinsics.checkNotNullExpressionValue(composableClass, "composableClass");
            Method methodFindComposableMethod = findComposableMethod(composableClass, methodName, Arrays.copyOf(args, args.length));
            methodFindComposableMethod.setAccessible(true);
            if (Modifier.isStatic(methodFindComposableMethod.getModifiers())) {
                invokeComposableMethod(methodFindComposableMethod, null, composer, Arrays.copyOf(args, args.length));
            } else {
                invokeComposableMethod(methodFindComposableMethod, composableClass.getConstructor(new Class[0]).newInstance(new Object[0]), composer, Arrays.copyOf(args, args.length));
            }
        } catch (ReflectiveOperationException e) {
            PreviewLogger.Companion.logWarning$ui_tooling_release$default(PreviewLogger.INSTANCE, "Failed to invoke Composable Method '" + className + ClassUtils.PACKAGE_SEPARATOR_CHAR + methodName + '\'', null, 2, null);
            throw e;
        }
    }
}
