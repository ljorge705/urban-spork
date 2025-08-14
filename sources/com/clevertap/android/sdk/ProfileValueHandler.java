package com.clevertap.android.sdk;

import com.clevertap.android.sdk.utils.CTJsonConverter;
import com.clevertap.android.sdk.validation.ValidationResult;
import com.clevertap.android.sdk.validation.ValidationResultFactory;
import com.clevertap.android.sdk.validation.ValidationResultStack;
import com.clevertap.android.sdk.validation.Validator;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;

/* compiled from: ProfileValueHandler.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0004\n\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0001\u001dB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J.\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f2\u001a\u0010\r\u001a\u0016\u0012\u0004\u0012\u00020\f\u0018\u00010\u000ej\n\u0012\u0004\u0012\u00020\f\u0018\u0001`\u000fH\u0002J\u001c\u0010\u0010\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0011\u001a\u00020\f2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001H\u0002J\u0012\u0010\u0013\u001a\u00020\u00142\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0002J\u0012\u0010\u0015\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\"\u0010\u0018\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0011\u001a\u00020\f2\b\u0010\u0019\u001a\u0004\u0018\u00010\u0017J,\u0010\u001a\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0011\u001a\u00020\f2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001J\u0012\u0010\u001c\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0016\u001a\u00020\u0001H\u0002R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/clevertap/android/sdk/ProfileValueHandler;", "", "validator", "Lcom/clevertap/android/sdk/validation/Validator;", "validationResultStack", "Lcom/clevertap/android/sdk/validation/ValidationResultStack;", "(Lcom/clevertap/android/sdk/validation/Validator;Lcom/clevertap/android/sdk/validation/ValidationResultStack;)V", "numberValueType", "Lcom/clevertap/android/sdk/ProfileValueHandler$NumberValueType;", "cleanMultiValues", "Lorg/json/JSONArray;", Constants.KEY_KEY, "", "values", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "constructExistingMultiValue", "command", "existing", "generateEmptyMultiValueError", "", "getNumberValueType", "value", "", "handleIncrementDecrementValues", "existingValue", "handleMultiValues", "existingValues", "stringifyAndCleanScalarProfilePropValue", "NumberValueType", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class ProfileValueHandler {
    private NumberValueType numberValueType;
    private final ValidationResultStack validationResultStack;
    private final Validator validator;

    /* compiled from: ProfileValueHandler.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0080\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/clevertap/android/sdk/ProfileValueHandler$NumberValueType;", "", "(Ljava/lang/String;I)V", "INT_NUMBER", "FLOAT_NUMBER", "DOUBLE_NUMBER", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public enum NumberValueType {
        INT_NUMBER,
        FLOAT_NUMBER,
        DOUBLE_NUMBER
    }

    /* compiled from: ProfileValueHandler.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[NumberValueType.values().length];
            try {
                iArr[NumberValueType.DOUBLE_NUMBER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[NumberValueType.FLOAT_NUMBER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public ProfileValueHandler(Validator validator, ValidationResultStack validationResultStack) {
        Intrinsics.checkNotNullParameter(validator, "validator");
        Intrinsics.checkNotNullParameter(validationResultStack, "validationResultStack");
        this.validator = validator;
        this.validationResultStack = validationResultStack;
    }

    public final Number handleIncrementDecrementValues(Number value, String command, Number existingValue) {
        int i;
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(command, "command");
        if (existingValue == null) {
            NumberValueType numberValueType = getNumberValueType(value);
            i = numberValueType != null ? WhenMappings.$EnumSwitchMapping$0[numberValueType.ordinal()] : -1;
            if (i == 1) {
                if (Intrinsics.areEqual(command, Constants.COMMAND_INCREMENT)) {
                    return Double.valueOf(value.doubleValue());
                }
                if (Intrinsics.areEqual(command, Constants.COMMAND_DECREMENT)) {
                    return Double.valueOf(-value.doubleValue());
                }
                return null;
            }
            if (i == 2) {
                if (Intrinsics.areEqual(command, Constants.COMMAND_INCREMENT)) {
                    return Float.valueOf(value.floatValue());
                }
                if (Intrinsics.areEqual(command, Constants.COMMAND_DECREMENT)) {
                    return Float.valueOf(-value.floatValue());
                }
                return null;
            }
            if (Intrinsics.areEqual(command, Constants.COMMAND_INCREMENT)) {
                return Integer.valueOf(value.intValue());
            }
            if (Intrinsics.areEqual(command, Constants.COMMAND_DECREMENT)) {
                return Integer.valueOf(-value.intValue());
            }
            return null;
        }
        NumberValueType numberValueType2 = getNumberValueType(existingValue);
        i = numberValueType2 != null ? WhenMappings.$EnumSwitchMapping$0[numberValueType2.ordinal()] : -1;
        if (i == 1) {
            if (Intrinsics.areEqual(command, Constants.COMMAND_INCREMENT)) {
                return Double.valueOf(existingValue.doubleValue() + value.doubleValue());
            }
            if (Intrinsics.areEqual(command, Constants.COMMAND_DECREMENT)) {
                return Double.valueOf(existingValue.doubleValue() - value.doubleValue());
            }
            return null;
        }
        if (i == 2) {
            if (Intrinsics.areEqual(command, Constants.COMMAND_INCREMENT)) {
                return Float.valueOf(existingValue.floatValue() + value.floatValue());
            }
            if (Intrinsics.areEqual(command, Constants.COMMAND_DECREMENT)) {
                return Float.valueOf(existingValue.floatValue() - value.floatValue());
            }
            return null;
        }
        if (Intrinsics.areEqual(command, Constants.COMMAND_INCREMENT)) {
            return Integer.valueOf(existingValue.intValue() + value.intValue());
        }
        if (Intrinsics.areEqual(command, Constants.COMMAND_DECREMENT)) {
            return Integer.valueOf(existingValue.intValue() - value.intValue());
        }
        return null;
    }

    private final NumberValueType getNumberValueType(Number value) {
        return Intrinsics.areEqual(value, Integer.valueOf(value.intValue())) ? NumberValueType.INT_NUMBER : Intrinsics.areEqual(value, Double.valueOf(value.doubleValue())) ? NumberValueType.DOUBLE_NUMBER : Intrinsics.areEqual(value, Float.valueOf(value.floatValue())) ? NumberValueType.FLOAT_NUMBER : this.numberValueType;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final JSONArray handleMultiValues(String key, JSONArray values, String command, Object existingValues) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(command, "command");
        JSONArray jSONArrayConstructExistingMultiValue = constructExistingMultiValue(command, existingValues);
        Intrinsics.checkNotNull(values);
        ArrayList<?> list = CTJsonConverter.toList(values);
        Intrinsics.checkNotNull(list, "null cannot be cast to non-null type java.util.ArrayList<kotlin.String>{ kotlin.collections.TypeAliasesKt.ArrayList<kotlin.String> }");
        JSONArray jSONArrayCleanMultiValues = cleanMultiValues(key, list);
        if (jSONArrayConstructExistingMultiValue == null || jSONArrayCleanMultiValues == null) {
            return null;
        }
        ValidationResult validationResultMergeMultiValuePropertyForKey = this.validator.mergeMultiValuePropertyForKey(jSONArrayConstructExistingMultiValue, jSONArrayCleanMultiValues, Intrinsics.areEqual(command, Constants.COMMAND_REMOVE) ? Validator.REMOVE_VALUES_OPERATION : Validator.ADD_VALUES_OPERATION, key);
        if (validationResultMergeMultiValuePropertyForKey.getErrorCode() != 0) {
            this.validationResultStack.pushValidationResult(validationResultMergeMultiValuePropertyForKey);
        }
        Object object = validationResultMergeMultiValuePropertyForKey.getObject();
        Intrinsics.checkNotNull(object, "null cannot be cast to non-null type org.json.JSONArray");
        JSONArray jSONArray = (JSONArray) object;
        if (jSONArray.length() > 0) {
            return jSONArray;
        }
        return null;
    }

    private final JSONArray constructExistingMultiValue(String command, Object existing) {
        boolean zAreEqual = Intrinsics.areEqual(command, Constants.COMMAND_REMOVE);
        boolean zAreEqual2 = Intrinsics.areEqual(command, Constants.COMMAND_ADD);
        if (!zAreEqual && !zAreEqual2) {
            return new JSONArray();
        }
        if (existing == null) {
            if (zAreEqual) {
                return null;
            }
            return new JSONArray();
        }
        if (existing instanceof JSONArray) {
            return (JSONArray) existing;
        }
        JSONArray jSONArray = zAreEqual2 ? new JSONArray() : null;
        String strStringifyAndCleanScalarProfilePropValue = stringifyAndCleanScalarProfilePropValue(existing);
        return strStringifyAndCleanScalarProfilePropValue != null ? new JSONArray().put(strStringifyAndCleanScalarProfilePropValue) : jSONArray;
    }

    private final JSONArray cleanMultiValues(String key, ArrayList<String> values) {
        if (values == null) {
            return null;
        }
        try {
            JSONArray jSONArray = new JSONArray();
            Iterator<String> it = values.iterator();
            while (it.hasNext()) {
                String value = it.next();
                ValidationResult validationResultCleanMultiValuePropertyValue = this.validator.cleanMultiValuePropertyValue(value);
                Intrinsics.checkNotNullExpressionValue(validationResultCleanMultiValuePropertyValue, "validator.cleanMultiValuePropertyValue(value)");
                if (validationResultCleanMultiValuePropertyValue.getErrorCode() != 0) {
                    this.validationResultStack.pushValidationResult(validationResultCleanMultiValuePropertyValue);
                }
                String string = validationResultCleanMultiValuePropertyValue.getObject() != null ? validationResultCleanMultiValuePropertyValue.getObject().toString() : null;
                Intrinsics.checkNotNullExpressionValue(value, "value");
                if (value.length() == 0) {
                    generateEmptyMultiValueError(key);
                    return null;
                }
                jSONArray.put(string);
            }
            return jSONArray;
        } catch (Throwable th) {
            Logger.v("Error cleaning multi values for key " + key, th);
            generateEmptyMultiValueError(key);
            return null;
        }
    }

    private final void generateEmptyMultiValueError(String key) {
        ValidationResult validationResultCreate = ValidationResultFactory.create(512, 1, key);
        this.validationResultStack.pushValidationResult(validationResultCreate);
        Logger.v(validationResultCreate.getErrorDesc());
    }

    private final String stringifyAndCleanScalarProfilePropValue(Object value) {
        String jsonString = CTJsonConverter.toJsonString(value);
        if (jsonString == null) {
            return jsonString;
        }
        ValidationResult validationResultCleanMultiValuePropertyValue = this.validator.cleanMultiValuePropertyValue(jsonString);
        if (validationResultCleanMultiValuePropertyValue.getErrorCode() != 0) {
            this.validationResultStack.pushValidationResult(validationResultCleanMultiValuePropertyValue);
        }
        if (validationResultCleanMultiValuePropertyValue.getObject() != null) {
            return validationResultCleanMultiValuePropertyValue.getObject().toString();
        }
        return null;
    }
}
