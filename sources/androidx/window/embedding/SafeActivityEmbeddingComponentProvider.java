package androidx.window.embedding;

import android.app.Activity;
import androidx.window.core.ConsumerAdapter;
import androidx.window.core.ExtensionsUtil;
import androidx.window.extensions.WindowExtensions;
import androidx.window.extensions.core.util.function.Consumer;
import androidx.window.extensions.embedding.ActivityEmbeddingComponent;
import androidx.window.reflection.ReflectionUtils;
import androidx.window.reflection.WindowExtensionsConstants;
import java.lang.reflect.Method;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SafeActivityEmbeddingComponentProvider.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u000b\b\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\u0015\u001a\u00020\u0016H\u0002J\b\u0010\u0017\u001a\u00020\u0016H\u0002J\b\u0010\u0018\u001a\u00020\u0016H\u0002J\b\u0010\u0019\u001a\u00020\u0016H\u0002J\b\u0010\u001a\u001a\u00020\u0016H\u0002J\b\u0010\u001b\u001a\u00020\u0016H\u0002J\b\u0010\u001c\u001a\u00020\u0016H\u0002J\b\u0010\u001d\u001a\u00020\u0016H\u0002J\b\u0010\u001e\u001a\u00020\u0016H\u0002J\b\u0010\u001f\u001a\u00020\u0016H\u0002J\b\u0010 \u001a\u00020\u0016H\u0002R\u0013\u0010\t\u001a\u0004\u0018\u00010\n8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0018\u0010\r\u001a\u0006\u0012\u0002\b\u00030\u000e8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u000e8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0010R\u0018\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u000e8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0010¨\u0006!"}, d2 = {"Landroidx/window/embedding/SafeActivityEmbeddingComponentProvider;", "", "loader", "Ljava/lang/ClassLoader;", "consumerAdapter", "Landroidx/window/core/ConsumerAdapter;", "windowExtensions", "Landroidx/window/extensions/WindowExtensions;", "(Ljava/lang/ClassLoader;Landroidx/window/core/ConsumerAdapter;Landroidx/window/extensions/WindowExtensions;)V", "activityEmbeddingComponent", "Landroidx/window/extensions/embedding/ActivityEmbeddingComponent;", "getActivityEmbeddingComponent", "()Landroidx/window/extensions/embedding/ActivityEmbeddingComponent;", "activityEmbeddingComponentClass", "Ljava/lang/Class;", "getActivityEmbeddingComponentClass", "()Ljava/lang/Class;", "windowExtensionsClass", "getWindowExtensionsClass", "windowExtensionsProviderClass", "getWindowExtensionsProviderClass", "canUseActivityEmbeddingComponent", "", "hasValidVendorApiLevel1", "hasValidVendorApiLevel2", "isActivityEmbeddingComponentValid", "isMethodClearSplitInfoCallbackValid", "isMethodIsActivityEmbeddedValid", "isMethodSetEmbeddingRulesValid", "isMethodSetSplitInfoCallbackJavaConsumerValid", "isMethodSetSplitInfoCallbackWindowConsumerValid", "isMethodSplitAttributesCalculatorValid", "isWindowExtensionsValid", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class SafeActivityEmbeddingComponentProvider {
    private final ConsumerAdapter consumerAdapter;
    private final ClassLoader loader;
    private final WindowExtensions windowExtensions;

    public SafeActivityEmbeddingComponentProvider(ClassLoader loader, ConsumerAdapter consumerAdapter, WindowExtensions windowExtensions) {
        Intrinsics.checkNotNullParameter(loader, "loader");
        Intrinsics.checkNotNullParameter(consumerAdapter, "consumerAdapter");
        Intrinsics.checkNotNullParameter(windowExtensions, "windowExtensions");
        this.loader = loader;
        this.consumerAdapter = consumerAdapter;
        this.windowExtensions = windowExtensions;
    }

    public final ActivityEmbeddingComponent getActivityEmbeddingComponent() {
        if (!canUseActivityEmbeddingComponent()) {
            return null;
        }
        try {
            return this.windowExtensions.getActivityEmbeddingComponent();
        } catch (UnsupportedOperationException unused) {
            return null;
        }
    }

    private final boolean canUseActivityEmbeddingComponent() {
        if (!isWindowExtensionsValid() || !isActivityEmbeddingComponentValid()) {
            return false;
        }
        int safeVendorApiLevel = ExtensionsUtil.INSTANCE.getSafeVendorApiLevel();
        if (safeVendorApiLevel == 1) {
            return hasValidVendorApiLevel1();
        }
        if (2 > safeVendorApiLevel || safeVendorApiLevel > Integer.MAX_VALUE) {
            return false;
        }
        return hasValidVendorApiLevel2();
    }

    private final boolean hasValidVendorApiLevel1() {
        return isMethodSetEmbeddingRulesValid() && isMethodIsActivityEmbeddedValid() && isMethodSetSplitInfoCallbackJavaConsumerValid();
    }

    private final boolean hasValidVendorApiLevel2() {
        return hasValidVendorApiLevel1() && isMethodSetSplitInfoCallbackWindowConsumerValid() && isMethodClearSplitInfoCallbackValid() && isMethodSplitAttributesCalculatorValid();
    }

    private final boolean isMethodSetEmbeddingRulesValid() {
        return ReflectionUtils.validateReflection$window_release("ActivityEmbeddingComponent#setEmbeddingRules is not valid", new Function0<Boolean>() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider.isMethodSetEmbeddingRulesValid.1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() throws NoSuchMethodException, SecurityException {
                Method setEmbeddingRulesMethod = SafeActivityEmbeddingComponentProvider.this.getActivityEmbeddingComponentClass().getMethod("setEmbeddingRules", Set.class);
                ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
                Intrinsics.checkNotNullExpressionValue(setEmbeddingRulesMethod, "setEmbeddingRulesMethod");
                return Boolean.valueOf(reflectionUtils.isPublic$window_release(setEmbeddingRulesMethod));
            }
        });
    }

    private final boolean isMethodIsActivityEmbeddedValid() {
        return ReflectionUtils.validateReflection$window_release("ActivityEmbeddingComponent#isActivityEmbedded is not valid", new Function0<Boolean>() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider.isMethodIsActivityEmbeddedValid.1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() throws NoSuchMethodException, SecurityException {
                Method isActivityEmbeddedMethod = SafeActivityEmbeddingComponentProvider.this.getActivityEmbeddingComponentClass().getMethod("isActivityEmbedded", Activity.class);
                ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
                Intrinsics.checkNotNullExpressionValue(isActivityEmbeddedMethod, "isActivityEmbeddedMethod");
                return Boolean.valueOf(reflectionUtils.isPublic$window_release(isActivityEmbeddedMethod) && ReflectionUtils.INSTANCE.doesReturn$window_release(isActivityEmbeddedMethod, Boolean.TYPE));
            }
        });
    }

    private final boolean isMethodClearSplitInfoCallbackValid() {
        return ReflectionUtils.validateReflection$window_release("ActivityEmbeddingComponent#clearSplitInfoCallback is not valid", new Function0<Boolean>() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider.isMethodClearSplitInfoCallbackValid.1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() throws NoSuchMethodException, SecurityException {
                Method clearSplitInfoCallbackMethod = SafeActivityEmbeddingComponentProvider.this.getActivityEmbeddingComponentClass().getMethod("clearSplitInfoCallback", new Class[0]);
                ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
                Intrinsics.checkNotNullExpressionValue(clearSplitInfoCallbackMethod, "clearSplitInfoCallbackMethod");
                return Boolean.valueOf(reflectionUtils.isPublic$window_release(clearSplitInfoCallbackMethod));
            }
        });
    }

    private final boolean isMethodSplitAttributesCalculatorValid() {
        return ReflectionUtils.validateReflection$window_release("ActivityEmbeddingComponent#setSplitAttributesCalculator is not valid", new Function0<Boolean>() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider.isMethodSplitAttributesCalculatorValid.1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Removed duplicated region for block: B:7:0x003f  */
            @Override // kotlin.jvm.functions.Function0
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Boolean invoke() throws java.lang.NoSuchMethodException, java.lang.SecurityException {
                /*
                    r6 = this;
                    androidx.window.embedding.SafeActivityEmbeddingComponentProvider r0 = androidx.window.embedding.SafeActivityEmbeddingComponentProvider.this
                    java.lang.Class r0 = androidx.window.embedding.SafeActivityEmbeddingComponentProvider.access$getActivityEmbeddingComponentClass(r0)
                    r1 = 1
                    java.lang.Class[] r2 = new java.lang.Class[r1]
                    java.lang.Class<androidx.window.extensions.core.util.function.Function> r3 = androidx.window.extensions.core.util.function.Function.class
                    r4 = 0
                    r2[r4] = r3
                    java.lang.String r3 = "setSplitAttributesCalculator"
                    java.lang.reflect.Method r0 = r0.getMethod(r3, r2)
                    androidx.window.embedding.SafeActivityEmbeddingComponentProvider r2 = androidx.window.embedding.SafeActivityEmbeddingComponentProvider.this
                    java.lang.Class r2 = androidx.window.embedding.SafeActivityEmbeddingComponentProvider.access$getActivityEmbeddingComponentClass(r2)
                    java.lang.String r3 = "clearSplitAttributesCalculator"
                    java.lang.Class[] r5 = new java.lang.Class[r4]
                    java.lang.reflect.Method r2 = r2.getMethod(r3, r5)
                    androidx.window.reflection.ReflectionUtils r3 = androidx.window.reflection.ReflectionUtils.INSTANCE
                    java.lang.String r5 = "setSplitAttributesCalculatorMethod"
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r5)
                    boolean r0 = r3.isPublic$window_release(r0)
                    if (r0 == 0) goto L3f
                    androidx.window.reflection.ReflectionUtils r0 = androidx.window.reflection.ReflectionUtils.INSTANCE
                    java.lang.String r3 = "clearSplitAttributesCalculatorMethod"
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
                    boolean r0 = r0.isPublic$window_release(r2)
                    if (r0 == 0) goto L3f
                    goto L40
                L3f:
                    r1 = r4
                L40:
                    java.lang.Boolean r0 = java.lang.Boolean.valueOf(r1)
                    return r0
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.window.embedding.SafeActivityEmbeddingComponentProvider.C05181.invoke():java.lang.Boolean");
            }
        });
    }

    private final boolean isMethodSetSplitInfoCallbackJavaConsumerValid() {
        return ReflectionUtils.validateReflection$window_release("ActivityEmbeddingComponent#setSplitInfoCallback is not valid", new Function0<Boolean>() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider.isMethodSetSplitInfoCallbackJavaConsumerValid.1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() throws NoSuchMethodException, SecurityException {
                Class<?> clsConsumerClassOrNull$window_release = SafeActivityEmbeddingComponentProvider.this.consumerAdapter.consumerClassOrNull$window_release();
                if (clsConsumerClassOrNull$window_release == null) {
                    return false;
                }
                Method setSplitInfoCallbackMethod = SafeActivityEmbeddingComponentProvider.this.getActivityEmbeddingComponentClass().getMethod("setSplitInfoCallback", clsConsumerClassOrNull$window_release);
                ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
                Intrinsics.checkNotNullExpressionValue(setSplitInfoCallbackMethod, "setSplitInfoCallbackMethod");
                return Boolean.valueOf(reflectionUtils.isPublic$window_release(setSplitInfoCallbackMethod));
            }
        });
    }

    private final boolean isMethodSetSplitInfoCallbackWindowConsumerValid() {
        return ReflectionUtils.validateReflection$window_release("ActivityEmbeddingComponent#setSplitInfoCallback is not valid", new Function0<Boolean>() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider.isMethodSetSplitInfoCallbackWindowConsumerValid.1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() throws NoSuchMethodException, SecurityException {
                Method setSplitInfoCallbackMethod = SafeActivityEmbeddingComponentProvider.this.getActivityEmbeddingComponentClass().getMethod("setSplitInfoCallback", Consumer.class);
                ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
                Intrinsics.checkNotNullExpressionValue(setSplitInfoCallbackMethod, "setSplitInfoCallbackMethod");
                return Boolean.valueOf(reflectionUtils.isPublic$window_release(setSplitInfoCallbackMethod));
            }
        });
    }

    private final boolean isWindowExtensionsValid() {
        return ReflectionUtils.validateReflection$window_release("WindowExtensionsProvider#getWindowExtensions is not valid", new Function0<Boolean>() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider.isWindowExtensionsValid.1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() throws NoSuchMethodException, SecurityException, ClassNotFoundException {
                boolean z = false;
                Method getWindowExtensionsMethod = SafeActivityEmbeddingComponentProvider.this.getWindowExtensionsProviderClass().getDeclaredMethod("getWindowExtensions", new Class[0]);
                Class<?> windowExtensionsClass = SafeActivityEmbeddingComponentProvider.this.getWindowExtensionsClass();
                ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
                Intrinsics.checkNotNullExpressionValue(getWindowExtensionsMethod, "getWindowExtensionsMethod");
                if (reflectionUtils.isPublic$window_release(getWindowExtensionsMethod) && ReflectionUtils.INSTANCE.doesReturn$window_release(getWindowExtensionsMethod, windowExtensionsClass)) {
                    z = true;
                }
                return Boolean.valueOf(z);
            }
        });
    }

    private final boolean isActivityEmbeddingComponentValid() {
        return ReflectionUtils.validateReflection$window_release("WindowExtensions#getActivityEmbeddingComponent is not valid", new Function0<Boolean>() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider.isActivityEmbeddingComponentValid.1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() throws NoSuchMethodException, SecurityException, ClassNotFoundException {
                boolean z = false;
                Method getActivityEmbeddingComponentMethod = SafeActivityEmbeddingComponentProvider.this.getWindowExtensionsClass().getMethod("getActivityEmbeddingComponent", new Class[0]);
                Class<?> activityEmbeddingComponentClass = SafeActivityEmbeddingComponentProvider.this.getActivityEmbeddingComponentClass();
                ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
                Intrinsics.checkNotNullExpressionValue(getActivityEmbeddingComponentMethod, "getActivityEmbeddingComponentMethod");
                if (reflectionUtils.isPublic$window_release(getActivityEmbeddingComponentMethod) && ReflectionUtils.INSTANCE.doesReturn$window_release(getActivityEmbeddingComponentMethod, activityEmbeddingComponentClass)) {
                    z = true;
                }
                return Boolean.valueOf(z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Class<?> getWindowExtensionsProviderClass() throws ClassNotFoundException {
        Class<?> clsLoadClass = this.loader.loadClass(WindowExtensionsConstants.WINDOW_EXTENSIONS_PROVIDER_CLASS);
        Intrinsics.checkNotNullExpressionValue(clsLoadClass, "loader.loadClass(WINDOW_EXTENSIONS_PROVIDER_CLASS)");
        return clsLoadClass;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Class<?> getWindowExtensionsClass() throws ClassNotFoundException {
        Class<?> clsLoadClass = this.loader.loadClass(WindowExtensionsConstants.WINDOW_EXTENSIONS_CLASS);
        Intrinsics.checkNotNullExpressionValue(clsLoadClass, "loader.loadClass(WINDOW_EXTENSIONS_CLASS)");
        return clsLoadClass;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Class<?> getActivityEmbeddingComponentClass() throws ClassNotFoundException {
        Class<?> clsLoadClass = this.loader.loadClass(WindowExtensionsConstants.ACTIVITY_EMBEDDING_COMPONENT_CLASS);
        Intrinsics.checkNotNullExpressionValue(clsLoadClass, "loader.loadClass(ACTIVIT…MBEDDING_COMPONENT_CLASS)");
        return clsLoadClass;
    }
}
