package com.clevertap.android.sdk.variables;

import android.text.TextUtils;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.variables.annotations.Variable;
import com.clevertap.android.sdk.variables.callbacks.VariableCallback;
import com.singular.sdk.internal.Constants;
import io.sentry.profilemeasurements.ProfileMeasurement;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.Map;

/* loaded from: classes5.dex */
public class Parser {
    private final CTVariables ctVariables;

    private static void log(String str) {
        Logger.v("variables", str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void log(String str, Throwable th) {
        Logger.v("variables", str, th);
    }

    public Parser(CTVariables cTVariables) {
        this.ctVariables = cTVariables;
    }

    public void parseVariables(Object... objArr) {
        try {
            for (Object obj : objArr) {
                parseVariablesHelper(obj, obj.getClass());
            }
        } catch (Throwable th) {
            log("Error parsing variables", th);
        }
    }

    public void parseVariablesForClasses(Class<?>... clsArr) {
        try {
            for (Class<?> cls : clsArr) {
                parseVariablesHelper(null, cls);
            }
        } catch (Throwable th) {
            log("Error parsing variables", th);
        }
    }

    void parseVariablesHelper(Object obj, Class<?> cls) {
        String strGroup;
        String name;
        try {
            for (Field field : cls.getFields()) {
                if (field.isAnnotationPresent(Variable.class)) {
                    Variable variable = (Variable) field.getAnnotation(Variable.class);
                    if (variable != null) {
                        strGroup = variable.group();
                        name = variable.name();
                    } else {
                        strGroup = "";
                        name = "";
                    }
                    if (TextUtils.isEmpty(name)) {
                        name = field.getName();
                    }
                    if (!TextUtils.isEmpty(strGroup)) {
                        name = strGroup + "." + name;
                    }
                    String str = name;
                    Class<?> type = field.getType();
                    String string = type.toString();
                    if (string.equals("int")) {
                        defineVariable(obj, str, Integer.valueOf(field.getInt(obj)), CTVariableUtils.NUMBER, field);
                    } else if (string.equals(ProfileMeasurement.UNIT_BYTES)) {
                        defineVariable(obj, str, Byte.valueOf(field.getByte(obj)), CTVariableUtils.NUMBER, field);
                    } else if (string.equals("short")) {
                        defineVariable(obj, str, Short.valueOf(field.getShort(obj)), CTVariableUtils.NUMBER, field);
                    } else if (string.equals(Constants.LONG)) {
                        defineVariable(obj, str, Long.valueOf(field.getLong(obj)), CTVariableUtils.NUMBER, field);
                    } else if (string.equals("char")) {
                        defineVariable(obj, str, Character.valueOf(field.getChar(obj)), CTVariableUtils.NUMBER, field);
                    } else if (string.equals("float")) {
                        defineVariable(obj, str, Float.valueOf(field.getFloat(obj)), CTVariableUtils.NUMBER, field);
                    } else if (string.equals("double")) {
                        defineVariable(obj, str, Double.valueOf(field.getDouble(obj)), CTVariableUtils.NUMBER, field);
                    } else if (string.equals(CTVariableUtils.BOOLEAN)) {
                        defineVariable(obj, str, Boolean.valueOf(field.getBoolean(obj)), CTVariableUtils.BOOLEAN, field);
                    } else if (type.isPrimitive()) {
                        log("Variable " + str + " is an unsupported primitive type.");
                    } else if (type.isArray()) {
                        log("Variable " + str + " is an unsupported type of Array.");
                    } else if (Map.class.isAssignableFrom(type)) {
                        defineVariable(obj, str, field.get(obj), "group", field);
                    } else {
                        Object obj2 = field.get(obj);
                        defineVariable(obj, str, obj2 == null ? null : obj2.toString(), CTVariableUtils.STRING, field);
                    }
                }
            }
        } catch (Throwable th) {
            log("Error parsing variables:", th);
            th.printStackTrace();
        }
    }

    <T> void defineVariable(Object obj, String str, T t, String str2, final Field field) {
        final Var varDefine = Var.define(str, t, str2, this.ctVariables);
        if (varDefine == null) {
            log("Something went wrong, variable '" + str + "' is null, returning");
            return;
        }
        final boolean z = obj != null;
        final WeakReference weakReference = new WeakReference(obj);
        varDefine.addValueChangedCallback(new VariableCallback<T>() { // from class: com.clevertap.android.sdk.variables.Parser.1
            @Override // com.clevertap.android.sdk.variables.callbacks.VariableCallback
            public void onValueChanged(Var<T> var) throws IllegalAccessException, IllegalArgumentException {
                Field field2;
                Object obj2 = weakReference.get();
                if ((z && obj2 == null) || (field2 = field) == null) {
                    varDefine.removeValueChangedHandler(this);
                    return;
                }
                try {
                    boolean zIsAccessible = field2.isAccessible();
                    if (!zIsAccessible) {
                        field.setAccessible(true);
                    }
                    field.set(obj2, varDefine.value());
                    if (zIsAccessible) {
                        return;
                    }
                    field.setAccessible(false);
                } catch (IllegalAccessException e) {
                    Parser.log("Error setting value for field " + varDefine.name(), e);
                } catch (IllegalArgumentException e2) {
                    Parser.log("Invalid value " + varDefine.value() + " for field " + varDefine.name(), e2);
                }
            }
        });
    }
}
