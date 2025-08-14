package com.clevertap.android.sdk.inapp.customtemplates;

import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.inapp.customtemplates.CustomTemplate;
import com.clevertap.android.sdk.inapp.customtemplates.CustomTemplateType;
import com.clevertap.android.sdk.inapp.customtemplates.TemplateArgumentType;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: JsonTemplatesProducer.kt */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0000\b\u0016\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u000f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0003H\u0002J\u0018\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0016\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00140\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u001c\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u001c0\u001b2\u0006\u0010\r\u001a\u00020\u000eH\u0002R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/clevertap/android/sdk/inapp/customtemplates/JsonTemplatesProducer;", "Lcom/clevertap/android/sdk/inapp/customtemplates/TemplateProducer;", "jsonTemplatesDefinition", "", "templatesPresenter", "Lcom/clevertap/android/sdk/inapp/customtemplates/TemplatePresenter;", "functionsPresenter", "Lcom/clevertap/android/sdk/inapp/customtemplates/FunctionPresenter;", "(Ljava/lang/String;Lcom/clevertap/android/sdk/inapp/customtemplates/TemplatePresenter;Lcom/clevertap/android/sdk/inapp/customtemplates/FunctionPresenter;)V", "addJsonArgumentsToBuilder", "", "builder", "Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplate$FunctionBuilder;", "json", "Lorg/json/JSONObject;", "Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplate$TemplateBuilder;", "argumentTypeFromStringOrThrow", "Lcom/clevertap/android/sdk/inapp/customtemplates/TemplateArgumentType;", "argumentTypeString", "createTemplateFromJson", "Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplate;", "templateName", "defineTemplates", "", "ctConfig", "Lcom/clevertap/android/sdk/CleverTapInstanceConfig;", "jsonArgToMap", "", "", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public class JsonTemplatesProducer implements TemplateProducer {
    private final FunctionPresenter functionsPresenter;
    private final String jsonTemplatesDefinition;
    private final TemplatePresenter templatesPresenter;

    /* compiled from: JsonTemplatesProducer.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[CustomTemplateType.values().length];
            try {
                iArr[CustomTemplateType.TEMPLATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[CustomTemplateType.FUNCTION.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[TemplateArgumentType.values().length];
            try {
                iArr2[TemplateArgumentType.BOOLEAN.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr2[TemplateArgumentType.NUMBER.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr2[TemplateArgumentType.STRING.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr2[TemplateArgumentType.FILE.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr2[TemplateArgumentType.ACTION.ordinal()] = 5;
            } catch (NoSuchFieldError unused7) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    public JsonTemplatesProducer(String jsonTemplatesDefinition, TemplatePresenter templatePresenter, FunctionPresenter functionPresenter) {
        Intrinsics.checkNotNullParameter(jsonTemplatesDefinition, "jsonTemplatesDefinition");
        this.jsonTemplatesDefinition = jsonTemplatesDefinition;
        this.templatesPresenter = templatePresenter;
        this.functionsPresenter = functionPresenter;
    }

    @Override // com.clevertap.android.sdk.inapp.customtemplates.TemplateProducer
    public Set<CustomTemplate> defineTemplates(CleverTapInstanceConfig ctConfig) throws JSONException {
        Intrinsics.checkNotNullParameter(ctConfig, "ctConfig");
        try {
            JSONObject jSONObject = new JSONObject(this.jsonTemplatesDefinition);
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            Iterator<String> itKeys = jSONObject.keys();
            Intrinsics.checkNotNullExpressionValue(itKeys, "jsonDefinitions.keys()");
            while (itKeys.hasNext()) {
                String templateName = itKeys.next();
                Intrinsics.checkNotNullExpressionValue(templateName, "templateName");
                JSONObject jSONObject2 = jSONObject.getJSONObject(templateName);
                Intrinsics.checkNotNullExpressionValue(jSONObject2, "jsonDefinitions.getJSONObject(templateName)");
                linkedHashSet.add(createTemplateFromJson(templateName, jSONObject2));
            }
            return linkedHashSet;
        } catch (JSONException e) {
            throw new CustomTemplateException("Invalid JSON format for templates' definitions", e);
        }
    }

    private final CustomTemplate createTemplateFromJson(String templateName, JSONObject json) throws JSONException {
        String stringType = json.getString("type");
        CustomTemplateType.Companion companion = CustomTemplateType.INSTANCE;
        Intrinsics.checkNotNullExpressionValue(stringType, "stringType");
        CustomTemplateType customTemplateTypeFromString = companion.fromString(stringType);
        if (customTemplateTypeFromString == null) {
            throw new CustomTemplateException("Invalid template type: \"" + stringType + '\"', null, 2, null);
        }
        int i = WhenMappings.$EnumSwitchMapping$0[customTemplateTypeFromString.ordinal()];
        if (i == 1) {
            if (this.templatesPresenter == null) {
                throw new CustomTemplateException("JSON definition contains a template definition and a templates presenter is required", null, 2, null);
            }
            CustomTemplate.TemplateBuilder templateBuilder = new CustomTemplate.TemplateBuilder();
            templateBuilder.name(templateName);
            templateBuilder.presenter(this.templatesPresenter);
            JSONObject jSONObject = json.getJSONObject("arguments");
            Intrinsics.checkNotNullExpressionValue(jSONObject, "json.getJSONObject(\"arguments\")");
            addJsonArgumentsToBuilder(templateBuilder, jSONObject);
            return templateBuilder.build();
        }
        if (i != 2) {
            throw new NoWhenBranchMatchedException();
        }
        if (this.functionsPresenter == null) {
            throw new CustomTemplateException("JSON definition contains a function definition and a function presenter is required", null, 2, null);
        }
        CustomTemplate.FunctionBuilder functionBuilder = new CustomTemplate.FunctionBuilder(json.getBoolean("isVisual"));
        functionBuilder.name(templateName);
        functionBuilder.presenter(this.functionsPresenter);
        JSONObject jSONObject2 = json.getJSONObject("arguments");
        Intrinsics.checkNotNullExpressionValue(jSONObject2, "json.getJSONObject(\"arguments\")");
        addJsonArgumentsToBuilder(functionBuilder, jSONObject2);
        return functionBuilder.build();
    }

    private final void addJsonArgumentsToBuilder(CustomTemplate.TemplateBuilder builder, JSONObject json) throws JSONException {
        Iterator<String> itKeys = json.keys();
        Intrinsics.checkNotNullExpressionValue(itKeys, "json.keys()");
        while (itKeys.hasNext()) {
            String key = itKeys.next();
            JSONObject jSONObject = json.getJSONObject(key);
            String typeString = jSONObject.getString("type");
            if (Intrinsics.areEqual(typeString, "object")) {
                Intrinsics.checkNotNullExpressionValue(key, "key");
                JSONObject jSONObject2 = jSONObject.getJSONObject("value");
                Intrinsics.checkNotNullExpressionValue(jSONObject2, "argumentJson.getJSONObject(\"value\")");
                builder.mapArgument(key, jsonArgToMap(jSONObject2));
            } else {
                TemplateArgumentType.Companion companion = TemplateArgumentType.INSTANCE;
                Intrinsics.checkNotNullExpressionValue(typeString, "typeString");
                TemplateArgumentType templateArgumentTypeFromString = companion.fromString(typeString);
                if (templateArgumentTypeFromString == null) {
                    throw new CustomTemplateException("Unsupported argument type: \"" + typeString + '\"', null, 2, null);
                }
                int i = WhenMappings.$EnumSwitchMapping$1[templateArgumentTypeFromString.ordinal()];
                if (i == 1) {
                    boolean z = jSONObject.getBoolean("value");
                    Intrinsics.checkNotNullExpressionValue(key, "key");
                    builder.booleanArgument(key, z);
                } else if (i == 2) {
                    double d = jSONObject.getDouble("value");
                    Intrinsics.checkNotNullExpressionValue(key, "key");
                    builder.doubleArgument(key, d);
                } else if (i == 3) {
                    String value = jSONObject.getString("value");
                    Intrinsics.checkNotNullExpressionValue(key, "key");
                    Intrinsics.checkNotNullExpressionValue(value, "value");
                    builder.stringArgument(key, value);
                } else if (i != 4) {
                    if (i != 5) {
                        continue;
                    } else {
                        if (jSONObject.has("value")) {
                            throw new CustomTemplateException("Action arguments should not specify a value. Remove value from argument: \"" + key + '\"', null, 2, null);
                        }
                        Intrinsics.checkNotNullExpressionValue(key, "key");
                        builder.actionArgument(key);
                    }
                } else {
                    if (jSONObject.has("value")) {
                        throw new CustomTemplateException("File arguments should not specify a value. Remove value from argument: \"" + key + '\"', null, 2, null);
                    }
                    Intrinsics.checkNotNullExpressionValue(key, "key");
                    builder.fileArgument(key);
                }
            }
        }
    }

    private final void addJsonArgumentsToBuilder(CustomTemplate.FunctionBuilder builder, JSONObject json) throws JSONException {
        Iterator<String> itKeys = json.keys();
        Intrinsics.checkNotNullExpressionValue(itKeys, "json.keys()");
        while (itKeys.hasNext()) {
            String key = itKeys.next();
            JSONObject jSONObject = json.getJSONObject(key);
            String typeString = jSONObject.getString("type");
            if (Intrinsics.areEqual(typeString, "object")) {
                Intrinsics.checkNotNullExpressionValue(key, "key");
                JSONObject jSONObject2 = jSONObject.getJSONObject("value");
                Intrinsics.checkNotNullExpressionValue(jSONObject2, "argumentJson.getJSONObject(\"value\")");
                builder.mapArgument(key, jsonArgToMap(jSONObject2));
            } else {
                Intrinsics.checkNotNullExpressionValue(typeString, "typeString");
                int i = WhenMappings.$EnumSwitchMapping$1[argumentTypeFromStringOrThrow(typeString).ordinal()];
                if (i == 1) {
                    boolean z = jSONObject.getBoolean("value");
                    Intrinsics.checkNotNullExpressionValue(key, "key");
                    builder.booleanArgument(key, z);
                } else if (i == 2) {
                    double d = jSONObject.getDouble("value");
                    Intrinsics.checkNotNullExpressionValue(key, "key");
                    builder.doubleArgument(key, d);
                } else if (i == 3) {
                    String value = jSONObject.getString("value");
                    Intrinsics.checkNotNullExpressionValue(key, "key");
                    Intrinsics.checkNotNullExpressionValue(value, "value");
                    builder.stringArgument(key, value);
                } else if (i != 4) {
                    if (i == 5) {
                        throw new CustomTemplateException("Function templates cannot have action arguments. Remove argument: \"" + key + '\"', null, 2, null);
                    }
                } else {
                    if (jSONObject.has("value")) {
                        throw new CustomTemplateException("File arguments should not specify a value. Remove value from argument: \"" + key + '\"', null, 2, null);
                    }
                    Intrinsics.checkNotNullExpressionValue(key, "key");
                    builder.fileArgument(key);
                }
            }
        }
    }

    private final Map<String, Object> jsonArgToMap(JSONObject json) throws JSONException {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterator<String> itKeys = json.keys();
        Intrinsics.checkNotNullExpressionValue(itKeys, "json.keys()");
        while (itKeys.hasNext()) {
            String key = itKeys.next();
            JSONObject jSONObject = json.getJSONObject(key);
            String typeString = jSONObject.getString("type");
            if (Intrinsics.areEqual(typeString, "object")) {
                Intrinsics.checkNotNullExpressionValue(key, "key");
                JSONObject jSONObject2 = jSONObject.getJSONObject("value");
                Intrinsics.checkNotNullExpressionValue(jSONObject2, "argumentJson.getJSONObject(\"value\")");
                linkedHashMap.put(key, jsonArgToMap(jSONObject2));
            } else {
                Intrinsics.checkNotNullExpressionValue(typeString, "typeString");
                int i = WhenMappings.$EnumSwitchMapping$1[argumentTypeFromStringOrThrow(typeString).ordinal()];
                if (i == 1) {
                    Intrinsics.checkNotNullExpressionValue(key, "key");
                    linkedHashMap.put(key, Boolean.valueOf(jSONObject.getBoolean("value")));
                } else if (i == 2) {
                    Intrinsics.checkNotNullExpressionValue(key, "key");
                    linkedHashMap.put(key, Double.valueOf(jSONObject.getDouble("value")));
                } else if (i == 3) {
                    Intrinsics.checkNotNullExpressionValue(key, "key");
                    String string = jSONObject.getString("value");
                    Intrinsics.checkNotNullExpressionValue(string, "argumentJson.getString(\"value\")");
                    linkedHashMap.put(key, string);
                } else if (i == 4 || i == 5) {
                    throw new CustomTemplateException("Nesting of file and action arguments within objects is not supported. To define nested file and actions use '.' notation in the argument name.", null, 2, null);
                }
            }
        }
        return linkedHashMap;
    }

    private final TemplateArgumentType argumentTypeFromStringOrThrow(String argumentTypeString) {
        TemplateArgumentType templateArgumentTypeFromString = TemplateArgumentType.INSTANCE.fromString(argumentTypeString);
        if (templateArgumentTypeFromString != null) {
            return templateArgumentTypeFromString;
        }
        throw new CustomTemplateException("Unsupported argument type: \"" + argumentTypeString + '\"', null, 2, null);
    }
}
