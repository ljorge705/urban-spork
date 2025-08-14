package com.clevertap.android.sdk.variables;

import android.text.TextUtils;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.Utils;
import com.clevertap.android.sdk.variables.callbacks.VariableCallback;
import io.sentry.rrweb.RRWebVideoEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class Var<T> {
    private static boolean printedCallbackWarning;
    private final CTVariables ctVariables;
    private T defaultValue;
    private String kind;
    private String name;
    private String[] nameComponents;
    private Double numberValue;
    public String stringValue;
    private T value;
    private boolean hadStarted = false;
    private final List<VariableCallback<T>> valueChangedHandlers = new ArrayList();
    private final List<VariableCallback<T>> fileReadyHandlers = new ArrayList();

    void clearStartFlag() {
        this.hadStarted = false;
    }

    public T defaultValue() {
        return this.defaultValue;
    }

    public String kind() {
        return this.kind;
    }

    public String name() {
        return this.name;
    }

    public String[] nameComponents() {
        return this.nameComponents;
    }

    public Var(CTVariables cTVariables) {
        this.ctVariables = cTVariables;
    }

    private static void log(String str) {
        Logger.v(RRWebVideoEvent.REPLAY_FRAME_RATE_TYPE_VARIABLE, str);
    }

    public static <T> Var<T> define(String str, T t, CTVariables cTVariables) {
        return define(str, t, CTVariableUtils.kindFromValue(t), cTVariables);
    }

    public static <T> Var<T> define(String str, T t, String str2, CTVariables cTVariables) {
        if (TextUtils.isEmpty(str)) {
            log("Empty name parameter provided.");
            return null;
        }
        if (str.startsWith(".") || str.endsWith(".")) {
            log("Variable name starts or ends with a `.` which is not allowed: " + str);
            return null;
        }
        if (!"file".equals(str2) && t == null) {
            Logger.d("Invalid Operation! Null values are not allowed as default values when defining the variable '" + str + "'.");
            return null;
        }
        Var<T> variable = cTVariables.getVarCache().getVariable(str);
        if (variable != null) {
            return variable;
        }
        Var<T> var = new Var<>(cTVariables);
        try {
            ((Var) var).name = str;
            ((Var) var).nameComponents = CTVariableUtils.getNameComponents(str);
            ((Var) var).defaultValue = t;
            ((Var) var).value = t;
            ((Var) var).kind = str2;
            var.cacheComputedValues();
            cTVariables.getVarCache().registerVariable(var);
            var.update();
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return var;
    }

    public synchronized void update() {
        T t = this.value;
        T t2 = (T) this.ctVariables.getVarCache().getMergedValueFromComponentArray(this.nameComponents);
        this.value = t2;
        if (t2 == null && t == null) {
            return;
        }
        if (t2 != null && t2.equals(t) && this.hadStarted) {
            return;
        }
        cacheComputedValues();
        if (this.ctVariables.hasVarsRequestCompleted().booleanValue()) {
            this.hadStarted = true;
            triggerValueChanged();
            if ("file".equals(this.kind)) {
                this.ctVariables.getVarCache().fileVarUpdated(this);
            }
        }
    }

    private void cacheComputedValues() {
        T t = this.value;
        if (t instanceof String) {
            String str = (String) t;
            this.stringValue = str;
            modifyNumberValue(str);
            modifyValue(this.numberValue);
            return;
        }
        if (t instanceof Number) {
            this.stringValue = "" + this.value;
            this.numberValue = Double.valueOf(((Number) this.value).doubleValue());
            modifyValue((Number) this.value);
        } else if (t == null || (t instanceof Iterable) || (t instanceof Map)) {
            this.stringValue = null;
            this.numberValue = null;
        } else {
            this.stringValue = t.toString();
            this.numberValue = null;
        }
    }

    private void modifyValue(Number number) {
        if (number == null) {
            return;
        }
        T t = this.defaultValue;
        if (t instanceof Byte) {
            this.value = (T) Byte.valueOf(number.byteValue());
            return;
        }
        if (t instanceof Short) {
            this.value = (T) Short.valueOf(number.shortValue());
            return;
        }
        if (t instanceof Integer) {
            this.value = (T) Integer.valueOf(number.intValue());
            return;
        }
        if (t instanceof Long) {
            this.value = (T) Long.valueOf(number.longValue());
            return;
        }
        if (t instanceof Float) {
            this.value = (T) Float.valueOf(number.floatValue());
        } else if (t instanceof Double) {
            this.value = (T) Double.valueOf(number.doubleValue());
        } else if (t instanceof Character) {
            this.value = (T) Character.valueOf((char) number.intValue());
        }
    }

    private void modifyNumberValue(String str) {
        try {
            this.numberValue = Double.valueOf(str);
        } catch (NumberFormatException unused) {
            this.numberValue = null;
            T t = this.defaultValue;
            if (t instanceof Number) {
                this.numberValue = Double.valueOf(((Number) t).doubleValue());
            }
        }
    }

    private void triggerValueChanged() {
        synchronized (this.valueChangedHandlers) {
            for (VariableCallback<T> variableCallback : this.valueChangedHandlers) {
                variableCallback.setVariable(this);
                Utils.runOnUiThread(variableCallback);
            }
        }
    }

    public String toString() {
        if ("file".equals(this.kind)) {
            return "Var(" + this.name + Constants.SEPARATOR_COMMA + this.ctVariables.getVarCache().filePathFromDisk(this.stringValue) + ")";
        }
        return "Var(" + this.name + Constants.SEPARATOR_COMMA + this.value + ")";
    }

    void warnIfNotStarted() {
        if (this.ctVariables.hasVarsRequestCompleted().booleanValue() || printedCallbackWarning) {
            return;
        }
        log("CleverTap hasn't finished retrieving values from the server. You should use a callback to make sure the value for " + this.name + " is ready. Otherwise, your app may not use the most up-to-date value.");
        printedCallbackWarning = true;
    }

    public T value() {
        warnIfNotStarted();
        return "file".equals(this.kind) ? (T) this.ctVariables.getVarCache().filePathFromDisk(this.stringValue) : this.value;
    }

    String rawFileValue() {
        if ("file".equals(this.kind)) {
            return this.stringValue;
        }
        return null;
    }

    public void addValueChangedCallback(VariableCallback<T> variableCallback) {
        if (variableCallback == null) {
            log("Invalid callback parameter provided.");
            return;
        }
        synchronized (this.valueChangedHandlers) {
            this.valueChangedHandlers.add(variableCallback);
        }
        if (this.ctVariables.hasVarsRequestCompleted().booleanValue()) {
            variableCallback.onValueChanged(this);
        }
    }

    public void removeValueChangedHandler(VariableCallback<T> variableCallback) {
        synchronized (this.valueChangedHandlers) {
            this.valueChangedHandlers.remove(variableCallback);
        }
    }

    public void triggerFileIsReady() {
        synchronized (this.fileReadyHandlers) {
            for (VariableCallback<T> variableCallback : this.fileReadyHandlers) {
                variableCallback.setVariable(this);
                Utils.runOnUiThread(variableCallback);
            }
        }
    }

    public Number numberValue() {
        warnIfNotStarted();
        return this.numberValue;
    }

    public String stringValue() {
        warnIfNotStarted();
        return "file".equals(this.kind) ? this.ctVariables.getVarCache().filePathFromDisk(this.stringValue) : this.stringValue;
    }

    public void addFileReadyHandler(VariableCallback<T> variableCallback) {
        synchronized (this.fileReadyHandlers) {
            this.fileReadyHandlers.add(variableCallback);
        }
    }

    public void removeFileReadyHandler(VariableCallback<T> variableCallback) {
        synchronized (this.fileReadyHandlers) {
            this.fileReadyHandlers.remove(variableCallback);
        }
    }
}
