package com.clevertap.android.sdk.inapp.customtemplates;

import android.content.Context;
import androidx.exifinterface.media.ExifInterface;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.inapp.CTInAppAction;
import com.clevertap.android.sdk.inapp.CTInAppNotification;
import com.clevertap.android.sdk.inapp.InAppActionType;
import com.clevertap.android.sdk.inapp.InAppListener;
import com.clevertap.android.sdk.inapp.images.FileResourceProvider;
import com.google.firebase.remoteconfig.RemoteConfigComponent;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.text.StringsKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.apache.commons.lang3.ClassUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: CustomTemplateContext.kt */
@Metadata(d1 = {"\u0000¨\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\n\n\u0002\b\u0007\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 N2\u00020\u0001:\u0004MNOPB9\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\u0012\u0010#\u001a\u00020\u00112\b\u0010$\u001a\u0004\u0018\u00010%H\u0002J\u0015\u0010&\u001a\u0004\u0018\u00010\u001a2\u0006\u0010'\u001a\u00020\u0011¢\u0006\u0002\u0010(J\u0015\u0010)\u001a\u0004\u0018\u00010*2\u0006\u0010'\u001a\u00020\u0011¢\u0006\u0002\u0010+J\u0015\u0010,\u001a\u0004\u0018\u00010-2\u0006\u0010'\u001a\u00020\u0011¢\u0006\u0002\u0010.J\u0010\u0010/\u001a\u0004\u0018\u00010\u00112\u0006\u0010'\u001a\u00020\u0011J\u0015\u00100\u001a\u0004\u0018\u0001012\u0006\u0010'\u001a\u00020\u0011¢\u0006\u0002\u00102J\u0015\u00103\u001a\u0004\u0018\u0001042\u0006\u0010'\u001a\u00020\u0011¢\u0006\u0002\u00105J\u0015\u00106\u001a\u0004\u0018\u0001072\u0006\u0010'\u001a\u00020\u0011¢\u0006\u0002\u00108J\u001c\u00109\u001a\u0010\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00102\u0006\u0010'\u001a\u00020\u0011J\u001c\u0010:\u001a\u0004\u0018\u00010\u00012\u0006\u0010;\u001a\u00020<2\b\u0010=\u001a\u0004\u0018\u00010>H\u0002J\u0015\u0010?\u001a\u0004\u0018\u00010@2\u0006\u0010'\u001a\u00020\u0011¢\u0006\u0002\u0010AJ\u0010\u0010B\u001a\u0004\u0018\u00010\u00112\u0006\u0010'\u001a\u00020\u0011J \u0010C\u001a\u0004\u0018\u0001HD\"\u0006\b\u0000\u0010D\u0018\u00012\u0006\u0010'\u001a\u00020\u0011H\u0082\b¢\u0006\u0002\u0010EJ,\u0010F\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00010\u00102\f\u0010G\u001a\b\u0012\u0004\u0012\u00020<0H2\b\u0010=\u001a\u0004\u0018\u00010>H\u0002J\u0006\u0010I\u001a\u00020JJ\b\u0010K\u001a\u00020JH\u0016J\b\u0010L\u001a\u00020\u0011H\u0016R \u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00010\u0010X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\u0014\u001a\u0010\u0012\f\u0012\n \u0016*\u0004\u0018\u00010\u00070\u00070\u0015X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u00020\rX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0014\u0010\u0004\u001a\u00020\u0005X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010 \u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"\u0082\u0001\u0002QR¨\u0006S"}, d2 = {"Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplateContext;", "", "template", "Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplate;", "notification", "Lcom/clevertap/android/sdk/inapp/CTInAppNotification;", "inAppListener", "Lcom/clevertap/android/sdk/inapp/InAppListener;", "resourceProvider", "Lcom/clevertap/android/sdk/inapp/images/FileResourceProvider;", "dismissListener", "Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplateContext$ContextDismissListener;", "logger", "Lcom/clevertap/android/sdk/Logger;", "(Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplate;Lcom/clevertap/android/sdk/inapp/CTInAppNotification;Lcom/clevertap/android/sdk/inapp/InAppListener;Lcom/clevertap/android/sdk/inapp/images/FileResourceProvider;Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplateContext$ContextDismissListener;Lcom/clevertap/android/sdk/Logger;)V", "argumentValues", "", "", "getArgumentValues", "()Ljava/util/Map;", "inAppListenerRef", "Ljava/lang/ref/WeakReference;", "kotlin.jvm.PlatformType", "getInAppListenerRef$clevertap_core_release", "()Ljava/lang/ref/WeakReference;", "isAction", "", "isVisual", "getLogger", "()Lcom/clevertap/android/sdk/Logger;", "getNotification", "()Lcom/clevertap/android/sdk/inapp/CTInAppNotification;", "templateName", "getTemplateName", "()Ljava/lang/String;", "getActionName", Constants.KEY_ACTION, "Lcom/clevertap/android/sdk/inapp/CTInAppAction;", "getBoolean", "name", "(Ljava/lang/String;)Ljava/lang/Boolean;", "getByte", "", "(Ljava/lang/String;)Ljava/lang/Byte;", "getDouble", "", "(Ljava/lang/String;)Ljava/lang/Double;", "getFile", "getFloat", "", "(Ljava/lang/String;)Ljava/lang/Float;", "getInt", "", "(Ljava/lang/String;)Ljava/lang/Integer;", "getLong", "", "(Ljava/lang/String;)Ljava/lang/Long;", "getMap", "getOverrideValue", "argument", "Lcom/clevertap/android/sdk/inapp/customtemplates/TemplateArgument;", "overrides", "Lorg/json/JSONObject;", "getShort", "", "(Ljava/lang/String;)Ljava/lang/Short;", "getString", "getValue", ExifInterface.GPS_DIRECTION_TRUE, "(Ljava/lang/String;)Ljava/lang/Object;", "mergeArguments", RemoteConfigComponent.DEFAULTS_FILE_NAME, "", "setDismissed", "", "setPresented", "toString", "ContextDismissListener", "Factory", "FunctionContext", "TemplateContext", "Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplateContext$FunctionContext;", "Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplateContext$TemplateContext;", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public abstract class CustomTemplateContext {
    private static final String ARGS_KEY_ACTIONS = "actions";

    /* renamed from: Factory, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Map<String, Object> argumentValues;
    private ContextDismissListener dismissListener;
    private final WeakReference<InAppListener> inAppListenerRef;
    private final boolean isAction;
    private final boolean isVisual;
    private final Logger logger;
    private final CTInAppNotification notification;
    private final FileResourceProvider resourceProvider;
    private final String templateName;

    /* compiled from: CustomTemplateContext.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bà\u0080\u0001\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplateContext$ContextDismissListener;", "", "onDismissContext", "", "context", "Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplateContext;", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public interface ContextDismissListener {
        void onDismissContext(CustomTemplateContext context);
    }

    /* compiled from: CustomTemplateContext.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[TemplateArgumentType.values().length];
            try {
                iArr[TemplateArgumentType.STRING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[TemplateArgumentType.BOOLEAN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[TemplateArgumentType.NUMBER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[TemplateArgumentType.FILE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[TemplateArgumentType.ACTION.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public /* synthetic */ CustomTemplateContext(CustomTemplate customTemplate, CTInAppNotification cTInAppNotification, InAppListener inAppListener, FileResourceProvider fileResourceProvider, ContextDismissListener contextDismissListener, Logger logger, DefaultConstructorMarker defaultConstructorMarker) {
        this(customTemplate, cTInAppNotification, inAppListener, fileResourceProvider, contextDismissListener, logger);
    }

    protected final Map<String, Object> getArgumentValues() {
        return this.argumentValues;
    }

    public final WeakReference<InAppListener> getInAppListenerRef$clevertap_core_release() {
        return this.inAppListenerRef;
    }

    protected final Logger getLogger() {
        return this.logger;
    }

    protected final CTInAppNotification getNotification() {
        return this.notification;
    }

    public final String getTemplateName() {
        return this.templateName;
    }

    private CustomTemplateContext(CustomTemplate customTemplate, CTInAppNotification cTInAppNotification, InAppListener inAppListener, FileResourceProvider fileResourceProvider, ContextDismissListener contextDismissListener, Logger logger) {
        this.notification = cTInAppNotification;
        this.resourceProvider = fileResourceProvider;
        this.dismissListener = contextDismissListener;
        this.logger = logger;
        this.templateName = customTemplate.getName();
        List<TemplateArgument> args$clevertap_core_release = customTemplate.getArgs$clevertap_core_release();
        CustomTemplateInAppData customTemplateData = cTInAppNotification.getCustomTemplateData();
        this.argumentValues = mergeArguments(args$clevertap_core_release, customTemplateData != null ? customTemplateData.getArguments() : null);
        this.inAppListenerRef = new WeakReference<>(inAppListener);
        CustomTemplateInAppData customTemplateData2 = cTInAppNotification.getCustomTemplateData();
        this.isAction = customTemplateData2 != null ? customTemplateData2.getIsAction() : false;
        this.isVisual = customTemplate.getIsVisual();
    }

    /* compiled from: CustomTemplateContext.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J?\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0000¢\u0006\u0002\b\u0013R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplateContext$Factory;", "", "()V", "ARGS_KEY_ACTIONS", "", "createContext", "Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplateContext;", "template", "Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplate;", "notification", "Lcom/clevertap/android/sdk/inapp/CTInAppNotification;", "inAppListener", "Lcom/clevertap/android/sdk/inapp/InAppListener;", "resourceProvider", "Lcom/clevertap/android/sdk/inapp/images/FileResourceProvider;", "dismissListener", "Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplateContext$ContextDismissListener;", "logger", "Lcom/clevertap/android/sdk/Logger;", "createContext$clevertap_core_release", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* renamed from: com.clevertap.android.sdk.inapp.customtemplates.CustomTemplateContext$Factory, reason: from kotlin metadata */
    public static final class Companion {

        /* compiled from: CustomTemplateContext.kt */
        @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
        /* renamed from: com.clevertap.android.sdk.inapp.customtemplates.CustomTemplateContext$Factory$WhenMappings */
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

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
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final CustomTemplateContext createContext$clevertap_core_release(CustomTemplate template, CTInAppNotification notification, InAppListener inAppListener, FileResourceProvider resourceProvider, ContextDismissListener dismissListener, Logger logger) {
            Intrinsics.checkNotNullParameter(template, "template");
            Intrinsics.checkNotNullParameter(notification, "notification");
            Intrinsics.checkNotNullParameter(inAppListener, "inAppListener");
            Intrinsics.checkNotNullParameter(resourceProvider, "resourceProvider");
            Intrinsics.checkNotNullParameter(logger, "logger");
            int i = WhenMappings.$EnumSwitchMapping$0[template.getType().ordinal()];
            if (i == 1) {
                return new TemplateContext(template, notification, inAppListener, resourceProvider, dismissListener, logger);
            }
            if (i == 2) {
                return new FunctionContext(template, notification, inAppListener, resourceProvider, dismissListener, logger);
            }
            throw new NoWhenBranchMatchedException();
        }
    }

    public final Map<String, Object> getMap(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        String str = name + ClassUtils.PACKAGE_SEPARATOR_CHAR;
        Map<String, Object> map = this.argumentValues;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (StringsKt.startsWith$default(entry.getKey(), str, false, 2, (Object) null)) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        LinkedHashMap linkedHashMap2 = linkedHashMap;
        if (linkedHashMap2.isEmpty()) {
            return null;
        }
        Map linkedHashMap3 = new LinkedHashMap();
        for (Map.Entry entry2 : linkedHashMap2.entrySet()) {
            String str2 = (String) entry2.getKey();
            Object value = entry2.getValue();
            List<String> listSplit$default = StringsKt.split$default((CharSequence) StringsKt.removePrefix(str2, (CharSequence) str), new String[]{"."}, false, 0, 6, (Object) null);
            if (value instanceof CTInAppAction) {
                value = getActionName((CTInAppAction) value);
            }
            Map map2 = linkedHashMap3;
            int i = 0;
            for (String str3 : listSplit$default) {
                int i2 = i + 1;
                if (i == CollectionsKt.getLastIndex(listSplit$default)) {
                    map2.put(str3, value);
                } else {
                    Object obj = map2.get(str3);
                    Map linkedHashMap4 = TypeIntrinsics.isMutableMap(obj) ? (Map) obj : null;
                    if (linkedHashMap4 == null) {
                        linkedHashMap4 = new LinkedHashMap();
                        map2.put(str3, linkedHashMap4);
                    }
                    map2 = linkedHashMap4;
                }
                i = i2;
            }
        }
        return linkedHashMap3;
    }

    public void setPresented() {
        if (this.isAction) {
            return;
        }
        InAppListener inAppListener = this.inAppListenerRef.get();
        if (inAppListener != null) {
            inAppListener.inAppNotificationDidShow(this.notification, null);
        } else {
            this.logger.debug("CustomTemplates", "Cannot set template as presented");
        }
    }

    public final void setDismissed() {
        ContextDismissListener contextDismissListener = this.dismissListener;
        if (contextDismissListener != null) {
            contextDismissListener.onDismissContext(this);
        }
        this.dismissListener = null;
        if (!this.isAction || this.isVisual) {
            InAppListener inAppListener = this.inAppListenerRef.get();
            if (inAppListener != null) {
                inAppListener.inAppNotificationDidDismiss(this.notification, null);
            } else {
                this.logger.debug("CustomTemplates", "Cannot set template as dismissed");
            }
            this.inAppListenerRef.clear();
        }
    }

    private final Map<String, Object> mergeArguments(List<TemplateArgument> defaults, JSONObject overrides) throws JSONException {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (TemplateArgument templateArgument : defaults) {
            Object overrideValue = getOverrideValue(templateArgument, overrides);
            if (overrideValue == null) {
                overrideValue = templateArgument.getDefaultValue();
            }
            if (overrideValue != null) {
                linkedHashMap.put(templateArgument.getName(), overrideValue);
            }
        }
        return linkedHashMap;
    }

    private final Object getOverrideValue(TemplateArgument argument, JSONObject overrides) throws JSONException {
        Object string;
        if (overrides == null || !overrides.has(argument.getName())) {
            return null;
        }
        try {
            int i = WhenMappings.$EnumSwitchMapping$0[argument.getType().ordinal()];
            if (i == 1) {
                string = overrides.getString(argument.getName());
            } else if (i == 2) {
                string = Boolean.valueOf(overrides.getBoolean(argument.getName()));
            } else if (i == 3) {
                Object defaultValue = argument.getDefaultValue();
                if (defaultValue instanceof Byte) {
                    string = Byte.valueOf((byte) overrides.getInt(argument.getName()));
                } else if (defaultValue instanceof Short) {
                    string = Short.valueOf((short) overrides.getInt(argument.getName()));
                } else if (defaultValue instanceof Integer) {
                    string = Integer.valueOf(overrides.getInt(argument.getName()));
                } else if (defaultValue instanceof Long) {
                    string = Long.valueOf(overrides.getLong(argument.getName()));
                } else {
                    string = defaultValue instanceof Float ? Float.valueOf((float) overrides.getDouble(argument.getName())) : Double.valueOf(overrides.getDouble(argument.getName()));
                }
            } else if (i == 4) {
                string = overrides.getString(argument.getName());
            } else {
                if (i != 5) {
                    throw new NoWhenBranchMatchedException();
                }
                CTInAppAction.Companion companion = CTInAppAction.INSTANCE;
                JSONObject jSONObjectOptJSONObject = overrides.optJSONObject(argument.getName());
                string = companion.createFromJson(jSONObjectOptJSONObject != null ? jSONObjectOptJSONObject.optJSONObject("actions") : null);
            }
            return string;
        } catch (JSONException unused) {
            this.logger.debug("CustomTemplates", "Received argument with invalid type. Expected type: " + argument.getType() + " for argument: " + argument.getName());
            return null;
        }
    }

    private final /* synthetic */ <T> T getValue(String name) {
        T t = (T) this.argumentValues.get(name);
        Intrinsics.reifiedOperationMarker(2, ExifInterface.GPS_DIRECTION_TRUE);
        return t;
    }

    public String toString() {
        String string;
        StringBuilder sbAppend = new StringBuilder("CustomTemplateContext {\ntemplateName = ").append(this.templateName).append(",\nargs = {\n");
        Map<String, Object> map = this.argumentValues;
        ArrayList arrayList = new ArrayList(map.size());
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            StringBuilder sbAppend2 = new StringBuilder("\t").append(entry.getKey()).append(" = ");
            if (entry.getValue() instanceof CTInAppAction) {
                StringBuilder sb = new StringBuilder("Action {");
                Object value = entry.getValue();
                string = sb.append(getActionName(value instanceof CTInAppAction ? (CTInAppAction) value : null)).append(AbstractJsonLexerKt.END_OBJ).toString();
            } else {
                string = entry.getValue().toString();
            }
            arrayList.add(sbAppend2.append(string).toString());
        }
        return sbAppend.append(CollectionsKt.joinToString$default(arrayList, ",\n", null, null, 0, null, null, 62, null)).append("\n}}").toString();
    }

    private final String getActionName(CTInAppAction action) {
        InAppActionType type;
        CustomTemplateInAppData customTemplateInAppData;
        String templateName;
        return (action == null || (customTemplateInAppData = action.getCustomTemplateInAppData()) == null || (templateName = customTemplateInAppData.getTemplateName()) == null) ? (action == null || (type = action.getType()) == null) ? "" : type.getStringValue() : templateName;
    }

    /* compiled from: CustomTemplateContext.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B9\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\u001a\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0014¨\u0006\u0015"}, d2 = {"Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplateContext$TemplateContext;", "Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplateContext;", "template", "Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplate;", "notification", "Lcom/clevertap/android/sdk/inapp/CTInAppNotification;", "inAppListener", "Lcom/clevertap/android/sdk/inapp/InAppListener;", "resourceProvider", "Lcom/clevertap/android/sdk/inapp/images/FileResourceProvider;", "dismissListener", "Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplateContext$ContextDismissListener;", "logger", "Lcom/clevertap/android/sdk/Logger;", "(Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplate;Lcom/clevertap/android/sdk/inapp/CTInAppNotification;Lcom/clevertap/android/sdk/inapp/InAppListener;Lcom/clevertap/android/sdk/inapp/images/FileResourceProvider;Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplateContext$ContextDismissListener;Lcom/clevertap/android/sdk/Logger;)V", "triggerActionArgument", "", "actionArgumentName", "", "activityContext", "Landroid/content/Context;", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class TemplateContext extends CustomTemplateContext {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public TemplateContext(CustomTemplate template, CTInAppNotification notification, InAppListener inAppListener, FileResourceProvider resourceProvider, ContextDismissListener contextDismissListener, Logger logger) {
            super(template, notification, inAppListener, resourceProvider, contextDismissListener, logger, null);
            Intrinsics.checkNotNullParameter(template, "template");
            Intrinsics.checkNotNullParameter(notification, "notification");
            Intrinsics.checkNotNullParameter(inAppListener, "inAppListener");
            Intrinsics.checkNotNullParameter(resourceProvider, "resourceProvider");
            Intrinsics.checkNotNullParameter(logger, "logger");
        }

        public static /* synthetic */ void triggerActionArgument$default(TemplateContext templateContext, String str, Context context, int i, Object obj) {
            if ((i & 2) != 0) {
                context = null;
            }
            templateContext.triggerActionArgument(str, context);
        }

        public final void triggerActionArgument(String actionArgumentName, Context activityContext) {
            String templateName;
            Intrinsics.checkNotNullParameter(actionArgumentName, "actionArgumentName");
            Object obj = getArgumentValues().get(actionArgumentName);
            if (!(obj instanceof CTInAppAction)) {
                getLogger().info("CustomTemplates", "No argument of type action with name " + actionArgumentName + " exists for template " + getTemplateName());
                return;
            }
            InAppListener inAppListener = getInAppListenerRef$clevertap_core_release().get();
            if (inAppListener != null) {
                CTInAppNotification notification = getNotification();
                CTInAppAction cTInAppAction = (CTInAppAction) obj;
                CustomTemplateInAppData customTemplateInAppData = cTInAppAction.getCustomTemplateInAppData();
                inAppListener.inAppNotificationActionTriggered(notification, cTInAppAction, (customTemplateInAppData == null || (templateName = customTemplateInAppData.getTemplateName()) == null) ? actionArgumentName : templateName, null, activityContext);
                return;
            }
            getLogger().debug("CustomTemplates", "Cannot trigger action");
        }
    }

    /* compiled from: CustomTemplateContext.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B9\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000e¨\u0006\u000f"}, d2 = {"Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplateContext$FunctionContext;", "Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplateContext;", "template", "Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplate;", "notification", "Lcom/clevertap/android/sdk/inapp/CTInAppNotification;", "inAppListener", "Lcom/clevertap/android/sdk/inapp/InAppListener;", "resourceProvider", "Lcom/clevertap/android/sdk/inapp/images/FileResourceProvider;", "dismissListener", "Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplateContext$ContextDismissListener;", "logger", "Lcom/clevertap/android/sdk/Logger;", "(Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplate;Lcom/clevertap/android/sdk/inapp/CTInAppNotification;Lcom/clevertap/android/sdk/inapp/InAppListener;Lcom/clevertap/android/sdk/inapp/images/FileResourceProvider;Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplateContext$ContextDismissListener;Lcom/clevertap/android/sdk/Logger;)V", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class FunctionContext extends CustomTemplateContext {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public FunctionContext(CustomTemplate template, CTInAppNotification notification, InAppListener inAppListener, FileResourceProvider resourceProvider, ContextDismissListener contextDismissListener, Logger logger) {
            super(template, notification, inAppListener, resourceProvider, contextDismissListener, logger, null);
            Intrinsics.checkNotNullParameter(template, "template");
            Intrinsics.checkNotNullParameter(notification, "notification");
            Intrinsics.checkNotNullParameter(inAppListener, "inAppListener");
            Intrinsics.checkNotNullParameter(resourceProvider, "resourceProvider");
            Intrinsics.checkNotNullParameter(logger, "logger");
        }
    }

    public final String getString(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        Object obj = this.argumentValues.get(name);
        if (!(obj instanceof String)) {
            obj = null;
        }
        return (String) obj;
    }

    public final Boolean getBoolean(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        Object obj = this.argumentValues.get(name);
        if (!(obj instanceof Boolean)) {
            obj = null;
        }
        return (Boolean) obj;
    }

    public final Byte getByte(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        Object obj = this.argumentValues.get(name);
        if (!(obj instanceof Byte)) {
            obj = null;
        }
        return (Byte) obj;
    }

    public final Short getShort(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        Object obj = this.argumentValues.get(name);
        if (!(obj instanceof Short)) {
            obj = null;
        }
        return (Short) obj;
    }

    public final Integer getInt(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        Object obj = this.argumentValues.get(name);
        if (!(obj instanceof Integer)) {
            obj = null;
        }
        return (Integer) obj;
    }

    public final Long getLong(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        Object obj = this.argumentValues.get(name);
        if (!(obj instanceof Long)) {
            obj = null;
        }
        return (Long) obj;
    }

    public final Float getFloat(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        Object obj = this.argumentValues.get(name);
        if (!(obj instanceof Float)) {
            obj = null;
        }
        return (Float) obj;
    }

    public final Double getDouble(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        Object obj = this.argumentValues.get(name);
        if (!(obj instanceof Double)) {
            obj = null;
        }
        return (Double) obj;
    }

    public final String getFile(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        Object obj = this.argumentValues.get(name);
        if (!(obj instanceof String)) {
            obj = null;
        }
        String str = (String) obj;
        if (str != null) {
            return this.resourceProvider.cachedFilePath(str);
        }
        return null;
    }
}
