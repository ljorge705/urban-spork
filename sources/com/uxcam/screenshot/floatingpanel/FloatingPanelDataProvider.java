package com.uxcam.screenshot.floatingpanel;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Rect;
import android.view.View;
import android.view.WindowManager;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes6.dex */
public class FloatingPanelDataProvider {
    public static final FloatingPanelDataProvider d = new FloatingPanelDataProvider();

    /* renamed from: a, reason: collision with root package name */
    public final HashMap<FieldCacheKey, Field> f261a = new HashMap<>();
    public final int[] b = new int[2];
    public Object c;

    public static final class FieldCacheKey {
    }

    public final ArrayList a(Activity activity) throws SecurityException {
        View view;
        Object objA = this.c;
        if (objA == null) {
            objA = a("mGlobal", activity.getWindowManager());
            this.c = objA;
        }
        ArrayList arrayList = (ArrayList) a("mRoots", objA);
        ArrayList arrayList2 = (ArrayList) a("mParams", objA);
        if (arrayList != null) {
            ArrayList arrayList3 = new ArrayList();
            for (int i = 0; i < arrayList.size(); i++) {
                if (((WindowManager.LayoutParams) arrayList2.get(i)).type != 1 && (view = (View) a("mView", arrayList.get(i))) != null) {
                    Context context = view.getContext();
                    while (context != null) {
                        if (context instanceof Activity) {
                            break;
                        }
                        if (!(context instanceof ContextWrapper)) {
                            break;
                        }
                        context = ((ContextWrapper) context).getBaseContext();
                    }
                    context = null;
                    if (context == activity && view.isShown()) {
                        view.getLocationOnScreen(this.b);
                        int[] iArr = this.b;
                        int i2 = iArr[0];
                        int i3 = iArr[1];
                        arrayList3.add(new FloatingPanelData(view, new Rect(i2, i3, view.getWidth() + i2, view.getHeight() + i3), (WindowManager.LayoutParams) arrayList2.get(i)));
                    }
                }
            }
            return arrayList3;
        }
        new IllegalStateException("failed to get view roots or params :" + arrayList.toString() + ", " + arrayList2.toString());
        return new ArrayList();
    }

    public final <T> T a(String str, Object obj) throws SecurityException {
        Field field;
        obj.getClass();
        FieldCacheKey fieldCacheKey = new FieldCacheKey();
        Field field2 = this.f261a.get(fieldCacheKey);
        if (field2 == null) {
            Class<?> superclass = obj.getClass();
            while (true) {
                if (superclass == null) {
                    field = null;
                    break;
                }
                Field[] declaredFields = superclass.getDeclaredFields();
                int length = declaredFields.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        field = null;
                        break;
                    }
                    field = declaredFields[i];
                    if (field.getName().equals(str)) {
                        break;
                    }
                    i++;
                }
                if (field != null) {
                    break;
                }
                superclass = superclass.getSuperclass();
            }
            if (field != null) {
                field.setAccessible(true);
                this.f261a.put(fieldCacheKey, field);
                field2 = field;
            }
        }
        try {
            return (T) field2.get(obj);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
