package kotlinx.serialization.internal;

import androidx.exifinterface.media.ExifInterface;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Polymorphic;
import kotlinx.serialization.PolymorphicSerializer;
import kotlinx.serialization.Serializable;

/* compiled from: Platform.kt */
@Metadata(d1 = {"\u0000N\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0018\n\u0002\b\u0005\n\u0002\u0010\u0001\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a(\u0010\u0000\u001a\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0005H\u0002\u001aO\u0010\u0006\u001a\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u00052\"\u0010\u0007\u001a\u0012\u0012\u000e\b\u0001\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00010\b\"\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0001H\u0002¢\u0006\u0002\u0010\t\u001a\u0016\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\rH\u0000\u001a\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u0003*\u0006\u0012\u0002\b\u00030\u0005H\u0002\u001a$\u0010\u000f\u001a\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\rH\u0000\u001aM\u0010\u0010\u001a\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\r2\"\u0010\u0007\u001a\u0012\u0012\u000e\b\u0001\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00010\b\"\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0001H\u0000¢\u0006\u0002\u0010\u0011\u001a$\u0010\u0012\u001a\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u0005H\u0002\u001a&\u0010\u0013\u001a\u0002H\u0002\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\b2\u0006\u0010\u0014\u001a\u00020\u0015H\u0080\b¢\u0006\u0002\u0010\u0016\u001a\u0015\u0010\u0013\u001a\u00020\u000b*\u00020\u00172\u0006\u0010\u0014\u001a\u00020\u0015H\u0080\b\u001a$\u0010\u0018\u001a\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\rH\u0002\u001a\u0018\u0010\u0019\u001a\u00020\u000b*\u00020\u00032\n\u0010\u001a\u001a\u0006\u0012\u0002\b\u00030\rH\u0000\u001a\u001c\u0010\u001b\u001a\u00020\u000b\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u0005H\u0002\u001a\u0010\u0010\u001c\u001a\u00020\u001d*\u0006\u0012\u0002\b\u00030\rH\u0000\u001a$\u0010\u001e\u001a\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\rH\u0002\u001aK\u0010\u001f\u001a\b\u0012\u0004\u0012\u0002H 0\b\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\n\b\u0001\u0010 *\u0004\u0018\u0001H\u0002*\u0012\u0012\u0004\u0012\u0002H 0!j\b\u0012\u0004\u0012\u0002H `\"2\f\u0010#\u001a\b\u0012\u0004\u0012\u0002H\u00020\rH\u0000¢\u0006\u0002\u0010$¨\u0006%"}, d2 = {"findObjectSerializer", "Lkotlinx/serialization/KSerializer;", ExifInterface.GPS_DIRECTION_TRUE, "", "jClass", "Ljava/lang/Class;", "invokeSerializerOnCompanion", "args", "", "(Ljava/lang/Class;[Lkotlinx/serialization/KSerializer;)Lkotlinx/serialization/KSerializer;", "isReferenceArray", "", "rootClass", "Lkotlin/reflect/KClass;", "companionOrNull", "compiledSerializerImpl", "constructSerializerForGivenTypeArgs", "(Lkotlin/reflect/KClass;[Lkotlinx/serialization/KSerializer;)Lkotlinx/serialization/KSerializer;", "createEnumSerializer", "getChecked", "index", "", "([Ljava/lang/Object;I)Ljava/lang/Object;", "", "interfaceSerializer", "isInstanceOf", "kclass", "isNotAnnotated", "platformSpecificSerializerNotRegistered", "", "polymorphicSerializer", "toNativeArrayImpl", ExifInterface.LONGITUDE_EAST, "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "eClass", "(Ljava/util/ArrayList;Lkotlin/reflect/KClass;)[Ljava/lang/Object;", "kotlinx-serialization-core"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class PlatformKt {
    public static final <T> T getChecked(T[] tArr, int i) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        return tArr[i];
    }

    public static final boolean getChecked(boolean[] zArr, int i) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        return zArr[i];
    }

    public static final <T> KSerializer<T> compiledSerializerImpl(KClass<T> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "<this>");
        return constructSerializerForGivenTypeArgs(kClass, new KSerializer[0]);
    }

    public static final <T, E extends T> E[] toNativeArrayImpl(ArrayList<E> arrayList, KClass<T> eClass) throws NegativeArraySizeException {
        Intrinsics.checkNotNullParameter(arrayList, "<this>");
        Intrinsics.checkNotNullParameter(eClass, "eClass");
        Object objNewInstance = Array.newInstance((Class<?>) JvmClassMappingKt.getJavaClass((KClass) eClass), arrayList.size());
        if (objNewInstance == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<E of kotlinx.serialization.internal.PlatformKt.toNativeArrayImpl>");
        }
        E[] eArr = (E[]) arrayList.toArray((Object[]) objNewInstance);
        Intrinsics.checkNotNullExpressionValue(eArr, "toArray(java.lang.reflec….java, size) as Array<E>)");
        return eArr;
    }

    public static final Void platformSpecificSerializerNotRegistered(KClass<?> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "<this>");
        Platform_commonKt.serializerNotRegistered(kClass);
        throw new KotlinNothingValueException();
    }

    public static final <T> KSerializer<T> constructSerializerForGivenTypeArgs(KClass<T> kClass, KSerializer<Object>... args) {
        Field field;
        Intrinsics.checkNotNullParameter(kClass, "<this>");
        Intrinsics.checkNotNullParameter(args, "args");
        Class javaClass = JvmClassMappingKt.getJavaClass((KClass) kClass);
        if (javaClass.isEnum() && isNotAnnotated(javaClass)) {
            return createEnumSerializer(javaClass);
        }
        if (javaClass.isInterface()) {
            return interfaceSerializer(kClass);
        }
        KSerializer<T> kSerializerInvokeSerializerOnCompanion = invokeSerializerOnCompanion(javaClass, (KSerializer[]) Arrays.copyOf(args, args.length));
        if (kSerializerInvokeSerializerOnCompanion != null) {
            return kSerializerInvokeSerializerOnCompanion;
        }
        KSerializer<T> kSerializerFindObjectSerializer = findObjectSerializer(javaClass);
        if (kSerializerFindObjectSerializer != null) {
            return kSerializerFindObjectSerializer;
        }
        KSerializer<T> kSerializer = null;
        try {
            Class<?>[] declaredClasses = javaClass.getDeclaredClasses();
            Intrinsics.checkNotNullExpressionValue(declaredClasses, "jClass.declaredClasses");
            Class<?>[] clsArr = declaredClasses;
            int length = clsArr.length;
            int i = 0;
            Class<?> cls = null;
            boolean z = false;
            while (true) {
                if (i >= length) {
                    if (!z) {
                        break;
                    }
                } else {
                    Class<?> cls2 = clsArr[i];
                    if (Intrinsics.areEqual(cls2.getSimpleName(), "$serializer")) {
                        if (z) {
                            break;
                        }
                        z = true;
                        cls = cls2;
                    }
                    i++;
                }
            }
            cls = null;
            Class<?> cls3 = cls;
            Object obj = (cls3 == null || (field = cls3.getField("INSTANCE")) == null) ? null : field.get(null);
            if (obj instanceof KSerializer) {
                kSerializer = (KSerializer) obj;
            }
        } catch (NoSuchFieldException unused) {
        }
        return kSerializer != null ? kSerializer : polymorphicSerializer(kClass);
    }

    private static final <T> boolean isNotAnnotated(Class<T> cls) {
        return cls.getAnnotation(Serializable.class) == null && cls.getAnnotation(Polymorphic.class) == null;
    }

    private static final <T> KSerializer<T> polymorphicSerializer(KClass<T> kClass) {
        Class javaClass = JvmClassMappingKt.getJavaClass((KClass) kClass);
        if (javaClass.getAnnotation(Polymorphic.class) != null) {
            return new PolymorphicSerializer(kClass);
        }
        Serializable serializable = (Serializable) javaClass.getAnnotation(Serializable.class);
        if (serializable == null || !Intrinsics.areEqual(Reflection.getOrCreateKotlinClass(serializable.with()), Reflection.getOrCreateKotlinClass(PolymorphicSerializer.class))) {
            return null;
        }
        return new PolymorphicSerializer(kClass);
    }

    private static final <T> KSerializer<T> interfaceSerializer(KClass<T> kClass) {
        Serializable serializable = (Serializable) JvmClassMappingKt.getJavaClass((KClass) kClass).getAnnotation(Serializable.class);
        if (serializable == null || Intrinsics.areEqual(Reflection.getOrCreateKotlinClass(serializable.with()), Reflection.getOrCreateKotlinClass(PolymorphicSerializer.class))) {
            return new PolymorphicSerializer(kClass);
        }
        return null;
    }

    private static final <T> KSerializer<T> invokeSerializerOnCompanion(Class<?> cls, KSerializer<Object>... kSerializerArr) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class[] clsArr;
        Object objCompanionOrNull = companionOrNull(cls);
        if (objCompanionOrNull == null) {
            return null;
        }
        try {
            if (kSerializerArr.length != 0) {
                int length = kSerializerArr.length;
                Class[] clsArr2 = new Class[length];
                for (int i = 0; i < length; i++) {
                    clsArr2[i] = KSerializer.class;
                }
                clsArr = clsArr2;
            } else {
                clsArr = new Class[0];
            }
            Object objInvoke = objCompanionOrNull.getClass().getDeclaredMethod("serializer", (Class[]) Arrays.copyOf(clsArr, clsArr.length)).invoke(objCompanionOrNull, Arrays.copyOf(kSerializerArr, kSerializerArr.length));
            if (objInvoke instanceof KSerializer) {
                return (KSerializer) objInvoke;
            }
            return null;
        } catch (NoSuchMethodException unused) {
            return null;
        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            if (cause == null) {
                throw e;
            }
            String message = cause.getMessage();
            if (message == null) {
                message = e.getMessage();
            }
            throw new InvocationTargetException(cause, message);
        }
    }

    private static final Object companionOrNull(Class<?> cls) {
        try {
            Field declaredField = cls.getDeclaredField("Companion");
            declaredField.setAccessible(true);
            return declaredField.get(null);
        } catch (Throwable unused) {
            return null;
        }
    }

    private static final <T> KSerializer<T> createEnumSerializer(Class<T> cls) {
        T[] enumConstants = cls.getEnumConstants();
        String canonicalName = cls.getCanonicalName();
        Intrinsics.checkNotNullExpressionValue(canonicalName, "canonicalName");
        if (enumConstants == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<out kotlin.Enum<*>>");
        }
        EnumSerializer enumSerializer = new EnumSerializer(canonicalName, (Enum[]) enumConstants);
        if (enumSerializer instanceof KSerializer) {
            return enumSerializer;
        }
        return null;
    }

    private static final <T> KSerializer<T> findObjectSerializer(Class<T> cls) throws IllegalAccessException, SecurityException, IllegalArgumentException, InvocationTargetException {
        Field[] declaredFields = cls.getDeclaredFields();
        Intrinsics.checkNotNullExpressionValue(declaredFields, "jClass.declaredFields");
        Field[] fieldArr = declaredFields;
        int length = fieldArr.length;
        Field field = null;
        int i = 0;
        boolean z = false;
        while (true) {
            if (i >= length) {
                if (!z) {
                    break;
                }
            } else {
                Field field2 = fieldArr[i];
                Field field3 = field2;
                if (Intrinsics.areEqual(field3.getName(), "INSTANCE") && Intrinsics.areEqual(field3.getType(), cls) && Modifier.isStatic(field3.getModifiers())) {
                    if (z) {
                        break;
                    }
                    z = true;
                    field = field2;
                }
                i++;
            }
        }
        field = null;
        Field field4 = field;
        if (field4 == null) {
            return null;
        }
        Object obj = field4.get(null);
        Method[] methods = cls.getMethods();
        Intrinsics.checkNotNullExpressionValue(methods, "jClass.methods");
        Method[] methodArr = methods;
        int length2 = methodArr.length;
        Method method = null;
        int i2 = 0;
        boolean z2 = false;
        while (true) {
            if (i2 >= length2) {
                if (!z2) {
                    break;
                }
            } else {
                Method method2 = methodArr[i2];
                Method method3 = method2;
                if (Intrinsics.areEqual(method3.getName(), "serializer")) {
                    Class<?>[] parameterTypes = method3.getParameterTypes();
                    Intrinsics.checkNotNullExpressionValue(parameterTypes, "it.parameterTypes");
                    if (parameterTypes.length == 0 && Intrinsics.areEqual(method3.getReturnType(), KSerializer.class)) {
                        if (z2) {
                            break;
                        }
                        z2 = true;
                        method = method2;
                    }
                }
                i2++;
            }
        }
        method = null;
        Method method4 = method;
        if (method4 == null) {
            return null;
        }
        Object objInvoke = method4.invoke(obj, new Object[0]);
        if (objInvoke instanceof KSerializer) {
            return (KSerializer) objInvoke;
        }
        return null;
    }

    public static final boolean isInstanceOf(Object obj, KClass<?> kclass) {
        Intrinsics.checkNotNullParameter(obj, "<this>");
        Intrinsics.checkNotNullParameter(kclass, "kclass");
        return JvmClassMappingKt.getJavaObjectType(kclass).isInstance(obj);
    }

    public static final boolean isReferenceArray(KClass<Object> rootClass) {
        Intrinsics.checkNotNullParameter(rootClass, "rootClass");
        return JvmClassMappingKt.getJavaClass((KClass) rootClass).isArray();
    }
}
