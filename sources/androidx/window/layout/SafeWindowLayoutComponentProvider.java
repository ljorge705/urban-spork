package androidx.window.layout;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import androidx.window.core.ConsumerAdapter;
import androidx.window.core.ExtensionsUtil;
import androidx.window.extensions.WindowExtensionsProvider;
import androidx.window.extensions.core.util.function.Consumer;
import androidx.window.extensions.layout.WindowLayoutComponent;
import androidx.window.reflection.ReflectionUtils;
import androidx.window.reflection.WindowExtensionsConstants;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* compiled from: SafeWindowLayoutComponentProvider.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\t\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0015\u001a\u00020\u0016H\u0002J\b\u0010\u0017\u001a\u00020\u0016H\u0002J\b\u0010\u0018\u001a\u00020\u0016H\u0002J\b\u0010\u0019\u001a\u00020\u0016H\u0002J\b\u0010\u001a\u001a\u00020\u0016H\u0002J\b\u0010\u001b\u001a\u00020\u0016H\u0002J\b\u0010\u001c\u001a\u00020\u0016H\u0002J\b\u0010\u001d\u001a\u00020\u0016H\u0002J\b\u0010\u001e\u001a\u00020\u0016H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\nR\u0018\u0010\r\u001a\u0006\u0012\u0002\b\u00030\b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\nR\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u00108F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0018\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\n¨\u0006\u001f"}, d2 = {"Landroidx/window/layout/SafeWindowLayoutComponentProvider;", "", "loader", "Ljava/lang/ClassLoader;", "consumerAdapter", "Landroidx/window/core/ConsumerAdapter;", "(Ljava/lang/ClassLoader;Landroidx/window/core/ConsumerAdapter;)V", "foldingFeatureClass", "Ljava/lang/Class;", "getFoldingFeatureClass", "()Ljava/lang/Class;", "windowExtensionsClass", "getWindowExtensionsClass", "windowExtensionsProviderClass", "getWindowExtensionsProviderClass", "windowLayoutComponent", "Landroidx/window/extensions/layout/WindowLayoutComponent;", "getWindowLayoutComponent", "()Landroidx/window/extensions/layout/WindowLayoutComponent;", "windowLayoutComponentClass", "getWindowLayoutComponentClass", "canUseWindowLayoutComponent", "", "hasValidVendorApiLevel1", "hasValidVendorApiLevel2", "isFoldingFeatureValid", "isMethodWindowLayoutInfoListenerJavaConsumerValid", "isMethodWindowLayoutInfoListenerWindowConsumerValid", "isWindowExtensionsPresent", "isWindowExtensionsValid", "isWindowLayoutProviderValid", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class SafeWindowLayoutComponentProvider {
    private final ConsumerAdapter consumerAdapter;
    private final ClassLoader loader;

    public SafeWindowLayoutComponentProvider(ClassLoader loader, ConsumerAdapter consumerAdapter) {
        Intrinsics.checkNotNullParameter(loader, "loader");
        Intrinsics.checkNotNullParameter(consumerAdapter, "consumerAdapter");
        this.loader = loader;
        this.consumerAdapter = consumerAdapter;
    }

    public final WindowLayoutComponent getWindowLayoutComponent() {
        if (!canUseWindowLayoutComponent()) {
            return null;
        }
        try {
            return WindowExtensionsProvider.getWindowExtensions().getWindowLayoutComponent();
        } catch (UnsupportedOperationException unused) {
            return null;
        }
    }

    private final boolean canUseWindowLayoutComponent() {
        if (!isWindowExtensionsPresent() || !isWindowExtensionsValid() || !isWindowLayoutProviderValid() || !isFoldingFeatureValid()) {
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

    private final boolean isWindowExtensionsPresent() {
        return ReflectionUtils.INSTANCE.checkIsPresent$window_release(new Function0<Class<?>>() { // from class: androidx.window.layout.SafeWindowLayoutComponentProvider.isWindowExtensionsPresent.1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Class<?> invoke() throws ClassNotFoundException {
                Class<?> clsLoadClass = SafeWindowLayoutComponentProvider.this.loader.loadClass(WindowExtensionsConstants.WINDOW_EXTENSIONS_PROVIDER_CLASS);
                Intrinsics.checkNotNullExpressionValue(clsLoadClass, "loader.loadClass(WINDOW_EXTENSIONS_PROVIDER_CLASS)");
                return clsLoadClass;
            }
        });
    }

    private final boolean hasValidVendorApiLevel1() {
        return isMethodWindowLayoutInfoListenerJavaConsumerValid();
    }

    private final boolean hasValidVendorApiLevel2() {
        return hasValidVendorApiLevel1() && isMethodWindowLayoutInfoListenerWindowConsumerValid();
    }

    private final boolean isWindowExtensionsValid() {
        return ReflectionUtils.validateReflection$window_release("WindowExtensionsProvider#getWindowExtensions is not valid", new Function0<Boolean>() { // from class: androidx.window.layout.SafeWindowLayoutComponentProvider.isWindowExtensionsValid.1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() throws NoSuchMethodException, SecurityException, ClassNotFoundException {
                boolean z = false;
                Method getWindowExtensionsMethod = SafeWindowLayoutComponentProvider.this.getWindowExtensionsProviderClass().getDeclaredMethod("getWindowExtensions", new Class[0]);
                Class<?> windowExtensionsClass = SafeWindowLayoutComponentProvider.this.getWindowExtensionsClass();
                ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
                Intrinsics.checkNotNullExpressionValue(getWindowExtensionsMethod, "getWindowExtensionsMethod");
                if (reflectionUtils.doesReturn$window_release(getWindowExtensionsMethod, windowExtensionsClass) && ReflectionUtils.INSTANCE.isPublic$window_release(getWindowExtensionsMethod)) {
                    z = true;
                }
                return Boolean.valueOf(z);
            }
        });
    }

    private final boolean isWindowLayoutProviderValid() {
        return ReflectionUtils.validateReflection$window_release("WindowExtensions#getWindowLayoutComponent is not valid", new Function0<Boolean>() { // from class: androidx.window.layout.SafeWindowLayoutComponentProvider.isWindowLayoutProviderValid.1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() throws NoSuchMethodException, SecurityException, ClassNotFoundException {
                boolean z = false;
                Method getWindowLayoutComponentMethod = SafeWindowLayoutComponentProvider.this.getWindowExtensionsClass().getMethod("getWindowLayoutComponent", new Class[0]);
                Class<?> windowLayoutComponentClass = SafeWindowLayoutComponentProvider.this.getWindowLayoutComponentClass();
                ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
                Intrinsics.checkNotNullExpressionValue(getWindowLayoutComponentMethod, "getWindowLayoutComponentMethod");
                if (reflectionUtils.isPublic$window_release(getWindowLayoutComponentMethod) && ReflectionUtils.INSTANCE.doesReturn$window_release(getWindowLayoutComponentMethod, windowLayoutComponentClass)) {
                    z = true;
                }
                return Boolean.valueOf(z);
            }
        });
    }

    private final boolean isFoldingFeatureValid() {
        return ReflectionUtils.validateReflection$window_release("FoldingFeature class is not valid", new Function0<Boolean>() { // from class: androidx.window.layout.SafeWindowLayoutComponentProvider.isFoldingFeatureValid.1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() throws NoSuchMethodException, ClassNotFoundException, SecurityException {
                Class foldingFeatureClass = SafeWindowLayoutComponentProvider.this.getFoldingFeatureClass();
                boolean z = false;
                Method getBoundsMethod = foldingFeatureClass.getMethod("getBounds", new Class[0]);
                Method getTypeMethod = foldingFeatureClass.getMethod("getType", new Class[0]);
                Method getStateMethod = foldingFeatureClass.getMethod("getState", new Class[0]);
                ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
                Intrinsics.checkNotNullExpressionValue(getBoundsMethod, "getBoundsMethod");
                if (reflectionUtils.doesReturn$window_release(getBoundsMethod, Reflection.getOrCreateKotlinClass(Rect.class)) && ReflectionUtils.INSTANCE.isPublic$window_release(getBoundsMethod)) {
                    ReflectionUtils reflectionUtils2 = ReflectionUtils.INSTANCE;
                    Intrinsics.checkNotNullExpressionValue(getTypeMethod, "getTypeMethod");
                    if (reflectionUtils2.doesReturn$window_release(getTypeMethod, Reflection.getOrCreateKotlinClass(Integer.TYPE)) && ReflectionUtils.INSTANCE.isPublic$window_release(getTypeMethod)) {
                        ReflectionUtils reflectionUtils3 = ReflectionUtils.INSTANCE;
                        Intrinsics.checkNotNullExpressionValue(getStateMethod, "getStateMethod");
                        if (reflectionUtils3.doesReturn$window_release(getStateMethod, Reflection.getOrCreateKotlinClass(Integer.TYPE)) && ReflectionUtils.INSTANCE.isPublic$window_release(getStateMethod)) {
                            z = true;
                        }
                    }
                }
                return Boolean.valueOf(z);
            }
        });
    }

    private final boolean isMethodWindowLayoutInfoListenerJavaConsumerValid() {
        return ReflectionUtils.validateReflection$window_release("WindowLayoutComponent#addWindowLayoutInfoListener(" + Activity.class.getName() + ", java.util.function.Consumer) is not valid", new Function0<Boolean>() { // from class: androidx.window.layout.SafeWindowLayoutComponentProvider.isMethodWindowLayoutInfoListenerJavaConsumerValid.1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() throws NoSuchMethodException, ClassNotFoundException, SecurityException {
                Class<?> clsConsumerClassOrNull$window_release = SafeWindowLayoutComponentProvider.this.consumerAdapter.consumerClassOrNull$window_release();
                boolean z = false;
                if (clsConsumerClassOrNull$window_release == null) {
                    return false;
                }
                Class windowLayoutComponentClass = SafeWindowLayoutComponentProvider.this.getWindowLayoutComponentClass();
                Method addListenerMethod = windowLayoutComponentClass.getMethod("addWindowLayoutInfoListener", Activity.class, clsConsumerClassOrNull$window_release);
                Method removeListenerMethod = windowLayoutComponentClass.getMethod("removeWindowLayoutInfoListener", clsConsumerClassOrNull$window_release);
                ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
                Intrinsics.checkNotNullExpressionValue(addListenerMethod, "addListenerMethod");
                if (reflectionUtils.isPublic$window_release(addListenerMethod)) {
                    ReflectionUtils reflectionUtils2 = ReflectionUtils.INSTANCE;
                    Intrinsics.checkNotNullExpressionValue(removeListenerMethod, "removeListenerMethod");
                    if (reflectionUtils2.isPublic$window_release(removeListenerMethod)) {
                        z = true;
                    }
                }
                return Boolean.valueOf(z);
            }
        });
    }

    private final boolean isMethodWindowLayoutInfoListenerWindowConsumerValid() {
        return ReflectionUtils.validateReflection$window_release("WindowLayoutComponent#addWindowLayoutInfoListener(" + Context.class.getName() + ", androidx.window.extensions.core.util.function.Consumer) is not valid", new Function0<Boolean>() { // from class: androidx.window.layout.SafeWindowLayoutComponentProvider.isMethodWindowLayoutInfoListenerWindowConsumerValid.1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() throws NoSuchMethodException, ClassNotFoundException, SecurityException {
                Class windowLayoutComponentClass = SafeWindowLayoutComponentProvider.this.getWindowLayoutComponentClass();
                boolean z = false;
                Method addListenerMethod = windowLayoutComponentClass.getMethod("addWindowLayoutInfoListener", Context.class, Consumer.class);
                Method removeListenerMethod = windowLayoutComponentClass.getMethod("removeWindowLayoutInfoListener", Consumer.class);
                ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
                Intrinsics.checkNotNullExpressionValue(addListenerMethod, "addListenerMethod");
                if (reflectionUtils.isPublic$window_release(addListenerMethod)) {
                    ReflectionUtils reflectionUtils2 = ReflectionUtils.INSTANCE;
                    Intrinsics.checkNotNullExpressionValue(removeListenerMethod, "removeListenerMethod");
                    if (reflectionUtils2.isPublic$window_release(removeListenerMethod)) {
                        z = true;
                    }
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
    public final Class<?> getFoldingFeatureClass() throws ClassNotFoundException {
        Class<?> clsLoadClass = this.loader.loadClass(WindowExtensionsConstants.FOLDING_FEATURE_CLASS);
        Intrinsics.checkNotNullExpressionValue(clsLoadClass, "loader.loadClass(FOLDING_FEATURE_CLASS)");
        return clsLoadClass;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Class<?> getWindowLayoutComponentClass() throws ClassNotFoundException {
        Class<?> clsLoadClass = this.loader.loadClass(WindowExtensionsConstants.WINDOW_LAYOUT_COMPONENT_CLASS);
        Intrinsics.checkNotNullExpressionValue(clsLoadClass, "loader.loadClass(WINDOW_LAYOUT_COMPONENT_CLASS)");
        return clsLoadClass;
    }
}
