package io.grpc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;

/* loaded from: classes6.dex */
final class ServiceProviders {

    public interface PriorityAccessor<T> {
        int getPriority(T t);

        boolean isAvailable(T t);
    }

    private ServiceProviders() {
    }

    public static <T> T load(Class<T> cls, Iterable<Class<?>> iterable, ClassLoader classLoader, PriorityAccessor<T> priorityAccessor) {
        List listLoadAll = loadAll(cls, iterable, classLoader, priorityAccessor);
        if (listLoadAll.isEmpty()) {
            return null;
        }
        return (T) listLoadAll.get(0);
    }

    public static <T> List<T> loadAll(Class<T> cls, Iterable<Class<?>> iterable, ClassLoader classLoader, final PriorityAccessor<T> priorityAccessor) {
        Iterable candidatesViaServiceLoader;
        if (isAndroid(classLoader)) {
            candidatesViaServiceLoader = getCandidatesViaHardCoded(cls, iterable);
        } else {
            candidatesViaServiceLoader = getCandidatesViaServiceLoader(cls, classLoader);
        }
        ArrayList arrayList = new ArrayList();
        for (T t : candidatesViaServiceLoader) {
            if (priorityAccessor.isAvailable(t)) {
                arrayList.add(t);
            }
        }
        Collections.sort(arrayList, Collections.reverseOrder(new Comparator<T>() { // from class: io.grpc.ServiceProviders.1
            @Override // java.util.Comparator
            public int compare(T t2, T t3) {
                int priority = priorityAccessor.getPriority(t2) - priorityAccessor.getPriority(t3);
                return priority != 0 ? priority : t2.getClass().getName().compareTo(t3.getClass().getName());
            }
        }));
        return Collections.unmodifiableList(arrayList);
    }

    static boolean isAndroid(ClassLoader classLoader) throws ClassNotFoundException {
        try {
            Class.forName("android.app.Application", false, classLoader);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static <T> Iterable<T> getCandidatesViaServiceLoader(Class<T> cls, ClassLoader classLoader) {
        ServiceLoader serviceLoaderLoad = ServiceLoader.load(cls, classLoader);
        return !serviceLoaderLoad.iterator().hasNext() ? ServiceLoader.load(cls) : serviceLoaderLoad;
    }

    static <T> Iterable<T> getCandidatesViaHardCoded(Class<T> cls, Iterable<Class<?>> iterable) {
        ArrayList arrayList = new ArrayList();
        Iterator<Class<?>> it = iterable.iterator();
        while (it.hasNext()) {
            Object objCreateForHardCoded = createForHardCoded(cls, it.next());
            if (objCreateForHardCoded != null) {
                arrayList.add(objCreateForHardCoded);
            }
        }
        return arrayList;
    }

    private static <T> T createForHardCoded(Class<T> cls, Class<?> cls2) {
        try {
            return (T) cls2.asSubclass(cls).getConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (ClassCastException unused) {
            return null;
        } catch (Throwable th) {
            throw new ServiceConfigurationError(String.format("Provider %s could not be instantiated %s", cls2.getName(), th), th);
        }
    }
}
