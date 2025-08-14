package com.clevertap.android.sdk.inapp.customtemplates;

import androidx.exifinterface.media.ExifInterface;
import io.sentry.protocol.OperatingSystem;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.ClassUtils;

/* compiled from: CustomTemplate.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0003\u001c\u001d\u001eB9\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\u0013\u0010\u0017\u001a\u00020\u00072\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0019\u001a\u00020\u001aH\u0016J\b\u0010\u001b\u001a\u00020\u0003H\u0016R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0015\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u000b\u001a\u00020\fX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u001f"}, d2 = {"Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplate;", "", "name", "", "presenter", "Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplatePresenter;", "isVisual", "", "args", "", "Lcom/clevertap/android/sdk/inapp/customtemplates/TemplateArgument;", "type", "Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplateType;", "(Ljava/lang/String;Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplatePresenter;ZLjava/util/List;Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplateType;)V", "getArgs$clevertap_core_release", "()Ljava/util/List;", "()Z", "getName", "()Ljava/lang/String;", "getPresenter", "()Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplatePresenter;", "getType$clevertap_core_release", "()Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplateType;", "equals", "other", "hashCode", "", "toString", "Builder", "FunctionBuilder", "TemplateBuilder", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class CustomTemplate {
    private final List<TemplateArgument> args;
    private final boolean isVisual;
    private final String name;
    private final CustomTemplatePresenter<?> presenter;
    private final CustomTemplateType type;

    public /* synthetic */ CustomTemplate(String str, CustomTemplatePresenter customTemplatePresenter, boolean z, List list, CustomTemplateType customTemplateType, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, customTemplatePresenter, z, list, customTemplateType);
    }

    public final List<TemplateArgument> getArgs$clevertap_core_release() {
        return this.args;
    }

    public final String getName() {
        return this.name;
    }

    public final CustomTemplatePresenter<?> getPresenter() {
        return this.presenter;
    }

    /* renamed from: getType$clevertap_core_release, reason: from getter */
    public final CustomTemplateType getType() {
        return this.type;
    }

    /* renamed from: isVisual, reason: from getter */
    public final boolean getIsVisual() {
        return this.isVisual;
    }

    private CustomTemplate(String str, CustomTemplatePresenter<?> customTemplatePresenter, boolean z, List<TemplateArgument> list, CustomTemplateType customTemplateType) {
        this.name = str;
        this.presenter = customTemplatePresenter;
        this.isVisual = z;
        this.args = list;
        this.type = customTemplateType;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(getClass(), other != null ? other.getClass() : null)) {
            return false;
        }
        Intrinsics.checkNotNull(other, "null cannot be cast to non-null type com.clevertap.android.sdk.inapp.customtemplates.CustomTemplate");
        return Intrinsics.areEqual(this.name, ((CustomTemplate) other).name);
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    public String toString() {
        return "CustomTemplate {\nname = " + this.name + ",\nisVisual = " + this.isVisual + ",\ntype = " + this.type + ",\nargs = {\n" + CollectionsKt.joinToString$default(this.args, ",\n", null, null, 0, null, new Function1<TemplateArgument, CharSequence>() { // from class: com.clevertap.android.sdk.inapp.customtemplates.CustomTemplate.toString.1
            @Override // kotlin.jvm.functions.Function1
            public final CharSequence invoke(TemplateArgument it) {
                Intrinsics.checkNotNullParameter(it, "it");
                StringBuilder sbAppend = new StringBuilder("\t").append(it.getName()).append(" = ");
                Object defaultValue = it.getDefaultValue();
                if (defaultValue == null) {
                    defaultValue = it.getType();
                }
                return sbAppend.append(defaultValue).toString();
            }
        }, 30, null) + "\n}}";
    }

    /* compiled from: CustomTemplate.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00000\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005R\u0014\u0010\u0006\u001a\u00020\u0000X\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplate$FunctionBuilder;", "Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplate$Builder;", "Lcom/clevertap/android/sdk/inapp/customtemplates/FunctionPresenter;", "isVisual", "", "(Z)V", "thisRef", "getThisRef", "()Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplate$FunctionBuilder;", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class FunctionBuilder extends Builder<FunctionPresenter, FunctionBuilder> {
        private final FunctionBuilder thisRef;

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.clevertap.android.sdk.inapp.customtemplates.CustomTemplate.Builder
        public FunctionBuilder getThisRef() {
            return this.thisRef;
        }

        public FunctionBuilder(boolean z) {
            super(CustomTemplateType.FUNCTION, z, null);
            this.thisRef = this;
        }
    }

    /* compiled from: CustomTemplate.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00000\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u000e\u0010\u0007\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\tR\u0014\u0010\u0004\u001a\u00020\u0000X\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\n"}, d2 = {"Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplate$TemplateBuilder;", "Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplate$Builder;", "Lcom/clevertap/android/sdk/inapp/customtemplates/TemplatePresenter;", "()V", "thisRef", "getThisRef", "()Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplate$TemplateBuilder;", "actionArgument", "name", "", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class TemplateBuilder extends Builder<TemplatePresenter, TemplateBuilder> {
        private final TemplateBuilder thisRef;

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.clevertap.android.sdk.inapp.customtemplates.CustomTemplate.Builder
        public TemplateBuilder getThisRef() {
            return this.thisRef;
        }

        public TemplateBuilder() {
            super(CustomTemplateType.TEMPLATE, true, null);
            this.thisRef = this;
        }

        public final TemplateBuilder actionArgument(String name) {
            Intrinsics.checkNotNullParameter(name, "name");
            addArgument$clevertap_core_release(name, TemplateArgumentType.ACTION, null);
            return this;
        }
    }

    /* compiled from: CustomTemplate.kt */
    @Metadata(d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0010\n\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\f\b\u0000\u0010\u0001*\u0006\u0012\u0002\b\u00030\u0002*\u0014\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00030\u00002\u00020\u0004B\u0017\b\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ'\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u0005\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0004H\u0000¢\u0006\u0002\b\u001cJ\u001b\u0010\u001d\u001a\u00028\u00012\u0006\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\b¢\u0006\u0002\u0010\u001eJ\u0006\u0010\u001f\u001a\u00020 J\u001b\u0010!\u001a\u00028\u00012\u0006\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\"¢\u0006\u0002\u0010#J\u001b\u0010$\u001a\u00028\u00012\u0006\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020%¢\u0006\u0002\u0010&J\u0013\u0010'\u001a\u00028\u00012\u0006\u0010\u0019\u001a\u00020\u000f¢\u0006\u0002\u0010(J\u001b\u0010)\u001a\u00028\u00012\u0006\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020*¢\u0006\u0002\u0010+J\u000e\u0010,\u001a\b\u0012\u0004\u0012\u00020\f0-H\u0002J\u001b\u0010.\u001a\u00028\u00012\u0006\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020/¢\u0006\u0002\u00100J\u001b\u00101\u001a\u00028\u00012\u0006\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u000202¢\u0006\u0002\u00103J'\u00104\u001a\u00028\u00012\u0006\u0010\u0019\u001a\u00020\u000f2\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000406¢\u0006\u0002\u00107J\u0013\u0010\u0019\u001a\u00028\u00012\u0006\u0010\u0019\u001a\u00020\u000f¢\u0006\u0002\u0010(J\u0013\u0010\u0011\u001a\u00028\u00012\u0006\u0010\u0011\u001a\u00028\u0000¢\u0006\u0002\u00108J\u001b\u00109\u001a\u00028\u00012\u0006\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020:¢\u0006\u0002\u0010;J\u001b\u0010<\u001a\u00028\u00012\u0006\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000f¢\u0006\u0002\u0010=J\u0010\u0010>\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u000fH\u0002R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0011\u001a\u0004\u0018\u00018\u0000X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0012R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0014\u001a\u00028\u0001X¤\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0001\u0002?@¨\u0006A"}, d2 = {"Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplate$Builder;", "P", "Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplatePresenter;", ExifInterface.GPS_DIRECTION_TRUE, "", "type", "Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplateType;", "isVisual", "", "(Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplateType;Z)V", "args", "", "Lcom/clevertap/android/sdk/inapp/customtemplates/TemplateArgument;", "argsNames", "", "", "parentArgsNames", "presenter", "Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplatePresenter;", "templateName", "thisRef", "getThisRef", "()Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplate$Builder;", "addArgument", "", "name", "Lcom/clevertap/android/sdk/inapp/customtemplates/TemplateArgumentType;", "defaultValue", "addArgument$clevertap_core_release", "booleanArgument", "(Ljava/lang/String;Z)Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplate$Builder;", OperatingSystem.JsonKeys.BUILD, "Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplate;", "byteArgument", "", "(Ljava/lang/String;B)Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplate$Builder;", "doubleArgument", "", "(Ljava/lang/String;D)Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplate$Builder;", "fileArgument", "(Ljava/lang/String;)Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplate$Builder;", "floatArgument", "", "(Ljava/lang/String;F)Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplate$Builder;", "getOrderedArgs", "", "intArgument", "", "(Ljava/lang/String;I)Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplate$Builder;", "longArgument", "", "(Ljava/lang/String;J)Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplate$Builder;", "mapArgument", "value", "", "(Ljava/lang/String;Ljava/util/Map;)Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplate$Builder;", "(Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplatePresenter;)Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplate$Builder;", "shortArgument", "", "(Ljava/lang/String;S)Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplate$Builder;", "stringArgument", "(Ljava/lang/String;Ljava/lang/String;)Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplate$Builder;", "trackParentNames", "Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplate$FunctionBuilder;", "Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplate$TemplateBuilder;", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static abstract class Builder<P extends CustomTemplatePresenter<?>, T extends Builder<P, T>> {
        private final List<TemplateArgument> args;
        private final Set<String> argsNames;
        private final boolean isVisual;
        private final Set<String> parentArgsNames;
        private P presenter;
        private String templateName;
        private final CustomTemplateType type;

        public /* synthetic */ Builder(CustomTemplateType customTemplateType, boolean z, DefaultConstructorMarker defaultConstructorMarker) {
            this(customTemplateType, z);
        }

        protected abstract T getThisRef();

        private Builder(CustomTemplateType customTemplateType, boolean z) {
            this.type = customTemplateType;
            this.isVisual = z;
            this.argsNames = new LinkedHashSet();
            this.parentArgsNames = new LinkedHashSet();
            this.args = new ArrayList();
        }

        public final T name(String name) {
            Intrinsics.checkNotNullParameter(name, "name");
            if (this.templateName != null) {
                throw new CustomTemplateException("CustomTemplate name is already set as \"" + this.templateName + '\"', null, 2, null);
            }
            if (StringsKt.isBlank(name)) {
                throw new CustomTemplateException("CustomTemplate must have a non-blank name", null, 2, null);
            }
            this.templateName = name;
            return (T) getThisRef();
        }

        public final T stringArgument(String name, String defaultValue) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
            addArgument$clevertap_core_release(name, TemplateArgumentType.STRING, defaultValue);
            return (T) getThisRef();
        }

        public final T booleanArgument(String name, boolean defaultValue) {
            Intrinsics.checkNotNullParameter(name, "name");
            addArgument$clevertap_core_release(name, TemplateArgumentType.BOOLEAN, Boolean.valueOf(defaultValue));
            return (T) getThisRef();
        }

        public final T byteArgument(String name, byte defaultValue) {
            Intrinsics.checkNotNullParameter(name, "name");
            addArgument$clevertap_core_release(name, TemplateArgumentType.NUMBER, Byte.valueOf(defaultValue));
            return (T) getThisRef();
        }

        public final T shortArgument(String name, short defaultValue) {
            Intrinsics.checkNotNullParameter(name, "name");
            addArgument$clevertap_core_release(name, TemplateArgumentType.NUMBER, Short.valueOf(defaultValue));
            return (T) getThisRef();
        }

        public final T intArgument(String name, int defaultValue) {
            Intrinsics.checkNotNullParameter(name, "name");
            addArgument$clevertap_core_release(name, TemplateArgumentType.NUMBER, Integer.valueOf(defaultValue));
            return (T) getThisRef();
        }

        public final T longArgument(String name, long defaultValue) {
            Intrinsics.checkNotNullParameter(name, "name");
            addArgument$clevertap_core_release(name, TemplateArgumentType.NUMBER, Long.valueOf(defaultValue));
            return (T) getThisRef();
        }

        public final T floatArgument(String name, float defaultValue) {
            Intrinsics.checkNotNullParameter(name, "name");
            addArgument$clevertap_core_release(name, TemplateArgumentType.NUMBER, Float.valueOf(defaultValue));
            return (T) getThisRef();
        }

        public final T doubleArgument(String name, double defaultValue) {
            Intrinsics.checkNotNullParameter(name, "name");
            addArgument$clevertap_core_release(name, TemplateArgumentType.NUMBER, Double.valueOf(defaultValue));
            return (T) getThisRef();
        }

        public final T fileArgument(String name) {
            Intrinsics.checkNotNullParameter(name, "name");
            addArgument$clevertap_core_release(name, TemplateArgumentType.FILE, null);
            return (T) getThisRef();
        }

        public final T mapArgument(String name, Map<String, ? extends Object> value) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(value, "value");
            if (value.isEmpty()) {
                throw new CustomTemplateException("Map argument must not be empty", null, 2, null);
            }
            for (Map.Entry<String, ? extends Object> entry : value.entrySet()) {
                Object value2 = entry.getValue();
                String str = name + ClassUtils.PACKAGE_SEPARATOR_CHAR + entry.getKey();
                if (value2 instanceof Byte) {
                    byteArgument(str, ((Number) value2).byteValue());
                } else if (value2 instanceof Short) {
                    shortArgument(str, ((Number) value2).shortValue());
                } else if (value2 instanceof Integer) {
                    intArgument(str, ((Number) value2).intValue());
                } else if (value2 instanceof Long) {
                    longArgument(str, ((Number) value2).longValue());
                } else if (value2 instanceof Float) {
                    floatArgument(str, ((Number) value2).floatValue());
                } else if (value2 instanceof Double) {
                    doubleArgument(str, ((Number) value2).doubleValue());
                } else if (value2 instanceof Boolean) {
                    booleanArgument(str, ((Boolean) value2).booleanValue());
                } else if (value2 instanceof String) {
                    stringArgument(str, (String) value2);
                } else {
                    if (!(value2 instanceof Map)) {
                        throw new CustomTemplateException("Unsupported value type " + value2.getClass() + " for argument " + str, null, 2, null);
                    }
                    Intrinsics.checkNotNull(value2, "null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, kotlin.Any>");
                    mapArgument(str, (Map) value2);
                }
            }
            return (T) getThisRef();
        }

        public final T presenter(P presenter) {
            Intrinsics.checkNotNullParameter(presenter, "presenter");
            this.presenter = presenter;
            return (T) getThisRef();
        }

        public final CustomTemplate build() {
            P p = this.presenter;
            if (p == null) {
                throw new CustomTemplateException("CustomTemplate must have a presenter", null, 2, null);
            }
            String str = this.templateName;
            if (str == null) {
                throw new CustomTemplateException("CustomTemplate must have a name", null, 2, null);
            }
            return new CustomTemplate(str, p, this.isVisual, getOrderedArgs(), this.type, null);
        }

        public final void addArgument$clevertap_core_release(String name, TemplateArgumentType type, Object defaultValue) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(type, "type");
            String str = name;
            if (StringsKt.isBlank(str)) {
                throw new CustomTemplateException("Argument name must not be blank", null, 2, null);
            }
            if (StringsKt.startsWith$default(name, ".", false, 2, (Object) null) || StringsKt.endsWith$default(name, ".", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str, (CharSequence) "..", false, 2, (Object) null)) {
                throw new CustomTemplateException("Argument name must not begin or end with a \".\" nor have consecutive \".\"", null, 2, null);
            }
            if (this.argsNames.contains(name)) {
                throw new CustomTemplateException("Argument with name \"" + name + "\" is already defined", null, 2, null);
            }
            trackParentNames(name);
            this.args.add(new TemplateArgument(name, type, defaultValue));
            this.argsNames.add(name);
        }

        private final void trackParentNames(String name) {
            String str = name;
            for (int iIndexOf$default = StringsKt.indexOf$default((CharSequence) str, ClassUtils.PACKAGE_SEPARATOR_CHAR, 0, false, 4, (Object) null); iIndexOf$default != -1; iIndexOf$default = StringsKt.indexOf$default((CharSequence) str, ClassUtils.PACKAGE_SEPARATOR_CHAR, iIndexOf$default + 1, false, 4, (Object) null)) {
                String strSubstring = name.substring(0, iIndexOf$default);
                Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
                if (this.argsNames.contains(strSubstring)) {
                    throw new CustomTemplateException("Argument with name \"" + name + "\" is already defined", null, 2, null);
                }
                this.parentArgsNames.add(strSubstring);
            }
            if (this.parentArgsNames.contains(name)) {
                throw new CustomTemplateException("Argument with name \"" + name + "\" is already defined", null, 2, null);
            }
        }

        private final List<TemplateArgument> getOrderedArgs() {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (TemplateArgument templateArgument : this.args) {
                String str = (String) CollectionsKt.first(StringsKt.split$default((CharSequence) templateArgument.getName(), new String[]{"."}, false, 2, 2, (Object) null));
                LinkedHashMap linkedHashMap2 = linkedHashMap;
                if (linkedHashMap2.containsKey(str)) {
                    List list = (List) linkedHashMap.get(str);
                    if (list != null) {
                        list.add(templateArgument);
                    }
                } else {
                    linkedHashMap2.put(str, CollectionsKt.mutableListOf(templateArgument));
                }
            }
            ArrayList arrayList = new ArrayList();
            Iterator it = linkedHashMap.entrySet().iterator();
            while (it.hasNext()) {
                CollectionsKt.addAll(arrayList, CollectionsKt.sortedWith(CollectionsKt.toList((Iterable) ((Map.Entry) it.next()).getValue()), new Comparator() { // from class: com.clevertap.android.sdk.inapp.customtemplates.CustomTemplate$Builder$getOrderedArgs$lambda$1$$inlined$sortedBy$1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // java.util.Comparator
                    public final int compare(T t, T t2) {
                        return ComparisonsKt.compareValues(((TemplateArgument) t).getName(), ((TemplateArgument) t2).getName());
                    }
                }));
            }
            return arrayList;
        }
    }
}
