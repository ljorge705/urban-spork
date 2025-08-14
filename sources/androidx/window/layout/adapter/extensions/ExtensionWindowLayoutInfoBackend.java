package androidx.window.layout.adapter.extensions;

import android.app.Activity;
import android.content.Context;
import androidx.window.core.ConsumerAdapter;
import androidx.window.core.ExtensionsUtil;
import androidx.window.extensions.core.util.function.Consumer;
import androidx.window.extensions.layout.WindowLayoutComponent;
import androidx.window.extensions.layout.WindowLayoutInfo;
import androidx.window.layout.adapter.WindowBackend;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* compiled from: ExtensionWindowLayoutInfoBackend.kt */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001:\u0001\u001eB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0015\u001a\u00020\u0016H\u0007J&\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u001b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013H\u0016J\u0016\u0010\u001d\u001a\u00020\u00182\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\b8\u0002X\u0083\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\r0\b8\u0002X\u0083\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\t0\b8\u0002X\u0083\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u0012\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u0013\u0012\u0004\u0012\u00020\u000f0\b8\u0002X\u0083\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Landroidx/window/layout/adapter/extensions/ExtensionWindowLayoutInfoBackend;", "Landroidx/window/layout/adapter/WindowBackend;", "component", "Landroidx/window/extensions/layout/WindowLayoutComponent;", "consumerAdapter", "Landroidx/window/core/ConsumerAdapter;", "(Landroidx/window/extensions/layout/WindowLayoutComponent;Landroidx/window/core/ConsumerAdapter;)V", "consumerToOemConsumer", "", "Landroidx/window/layout/adapter/extensions/ExtensionWindowLayoutInfoBackend$MulticastConsumer;", "Landroidx/window/extensions/core/util/function/Consumer;", "Landroidx/window/extensions/layout/WindowLayoutInfo;", "consumerToToken", "Landroidx/window/core/ConsumerAdapter$Subscription;", "contextToListeners", "Landroid/content/Context;", "extensionWindowBackendLock", "Ljava/util/concurrent/locks/ReentrantLock;", "listenerToContext", "Landroidx/core/util/Consumer;", "Landroidx/window/layout/WindowLayoutInfo;", "hasRegisteredListeners", "", "registerLayoutChangeCallback", "", "context", "executor", "Ljava/util/concurrent/Executor;", "callback", "unregisterLayoutChangeCallback", "MulticastConsumer", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class ExtensionWindowLayoutInfoBackend implements WindowBackend {
    private final WindowLayoutComponent component;
    private final ConsumerAdapter consumerAdapter;
    private final Map<MulticastConsumer, Consumer<WindowLayoutInfo>> consumerToOemConsumer;
    private final Map<MulticastConsumer, ConsumerAdapter.Subscription> consumerToToken;
    private final Map<Context, MulticastConsumer> contextToListeners;
    private final ReentrantLock extensionWindowBackendLock;
    private final Map<androidx.core.util.Consumer<androidx.window.layout.WindowLayoutInfo>, Context> listenerToContext;

    public ExtensionWindowLayoutInfoBackend(WindowLayoutComponent component, ConsumerAdapter consumerAdapter) {
        Intrinsics.checkNotNullParameter(component, "component");
        Intrinsics.checkNotNullParameter(consumerAdapter, "consumerAdapter");
        this.component = component;
        this.consumerAdapter = consumerAdapter;
        this.extensionWindowBackendLock = new ReentrantLock();
        this.contextToListeners = new LinkedHashMap();
        this.listenerToContext = new LinkedHashMap();
        this.consumerToToken = new LinkedHashMap();
        this.consumerToOemConsumer = new LinkedHashMap();
    }

    @Override // androidx.window.layout.adapter.WindowBackend
    public void registerLayoutChangeCallback(Context context, Executor executor, androidx.core.util.Consumer<androidx.window.layout.WindowLayoutInfo> callback) {
        Unit unit;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(executor, "executor");
        Intrinsics.checkNotNullParameter(callback, "callback");
        ReentrantLock reentrantLock = this.extensionWindowBackendLock;
        reentrantLock.lock();
        try {
            MulticastConsumer multicastConsumer = this.contextToListeners.get(context);
            if (multicastConsumer != null) {
                multicastConsumer.addListener(callback);
                this.listenerToContext.put(callback, context);
                unit = Unit.INSTANCE;
            } else {
                unit = null;
            }
            if (unit == null) {
                ExtensionWindowLayoutInfoBackend extensionWindowLayoutInfoBackend = this;
                final MulticastConsumer multicastConsumer2 = new MulticastConsumer(context);
                this.contextToListeners.put(context, multicastConsumer2);
                this.listenerToContext.put(callback, context);
                multicastConsumer2.addListener(callback);
                if (ExtensionsUtil.INSTANCE.getSafeVendorApiLevel() < 2) {
                    Function1<WindowLayoutInfo, Unit> function1 = new Function1<WindowLayoutInfo, Unit>() { // from class: androidx.window.layout.adapter.extensions.ExtensionWindowLayoutInfoBackend$registerLayoutChangeCallback$1$2$consumeWindowLayoutInfo$1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(WindowLayoutInfo windowLayoutInfo) {
                            invoke2(windowLayoutInfo);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(WindowLayoutInfo value) {
                            Intrinsics.checkNotNullParameter(value, "value");
                            multicastConsumer2.accept(value);
                        }
                    };
                    if (context instanceof Activity) {
                        this.consumerToToken.put(multicastConsumer2, this.consumerAdapter.createSubscription((Object) this.component, Reflection.getOrCreateKotlinClass(WindowLayoutInfo.class), "addWindowLayoutInfoListener", "removeWindowLayoutInfoListener", (Activity) context, (Function1) function1));
                    } else {
                        multicastConsumer2.accept(new WindowLayoutInfo(CollectionsKt.emptyList()));
                        return;
                    }
                } else {
                    Consumer<WindowLayoutInfo> consumer = new Consumer() { // from class: androidx.window.layout.adapter.extensions.ExtensionWindowLayoutInfoBackend$$ExternalSyntheticLambda0
                        @Override // androidx.window.extensions.core.util.function.Consumer
                        public final void accept(Object obj) {
                            ExtensionWindowLayoutInfoBackend.registerLayoutChangeCallback$lambda$3$lambda$2$lambda$1(multicastConsumer2, (WindowLayoutInfo) obj);
                        }
                    };
                    this.consumerToOemConsumer.put(multicastConsumer2, consumer);
                    this.component.addWindowLayoutInfoListener(context, consumer);
                }
            }
            Unit unit2 = Unit.INSTANCE;
        } finally {
            reentrantLock.unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void registerLayoutChangeCallback$lambda$3$lambda$2$lambda$1(MulticastConsumer consumer, WindowLayoutInfo info) {
        Intrinsics.checkNotNullParameter(consumer, "$consumer");
        Intrinsics.checkNotNullExpressionValue(info, "info");
        consumer.accept(info);
    }

    @Override // androidx.window.layout.adapter.WindowBackend
    public void unregisterLayoutChangeCallback(androidx.core.util.Consumer<androidx.window.layout.WindowLayoutInfo> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        ReentrantLock reentrantLock = this.extensionWindowBackendLock;
        reentrantLock.lock();
        try {
            Context context = this.listenerToContext.get(callback);
            if (context == null) {
                return;
            }
            MulticastConsumer multicastConsumer = this.contextToListeners.get(context);
            if (multicastConsumer == null) {
                return;
            }
            multicastConsumer.removeListener(callback);
            this.listenerToContext.remove(callback);
            if (multicastConsumer.isEmpty()) {
                this.contextToListeners.remove(context);
                if (ExtensionsUtil.INSTANCE.getSafeVendorApiLevel() < 2) {
                    ConsumerAdapter.Subscription subscriptionRemove = this.consumerToToken.remove(multicastConsumer);
                    if (subscriptionRemove != null) {
                        subscriptionRemove.dispose();
                    }
                } else {
                    Consumer<WindowLayoutInfo> consumerRemove = this.consumerToOemConsumer.remove(multicastConsumer);
                    if (consumerRemove != null) {
                        this.component.removeWindowLayoutInfoListener(consumerRemove);
                    }
                }
            }
            Unit unit = Unit.INSTANCE;
        } finally {
            reentrantLock.unlock();
        }
    }

    public final boolean hasRegisteredListeners() {
        return (this.contextToListeners.isEmpty() && this.listenerToContext.isEmpty() && this.consumerToToken.isEmpty()) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ExtensionWindowLayoutInfoBackend.kt */
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0002H\u0016J\u0014\u0010\u000f\u001a\u00020\r2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00070\u0001J\u0006\u0010\u0011\u001a\u00020\u0012J\u0014\u0010\u0013\u001a\u00020\r2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00070\u0001R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00010\u000b8\u0002X\u0083\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Landroidx/window/layout/adapter/extensions/ExtensionWindowLayoutInfoBackend$MulticastConsumer;", "Landroidx/core/util/Consumer;", "Landroidx/window/extensions/layout/WindowLayoutInfo;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "lastKnownValue", "Landroidx/window/layout/WindowLayoutInfo;", "multicastConsumerLock", "Ljava/util/concurrent/locks/ReentrantLock;", "registeredListeners", "", "accept", "", "value", "addListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "isEmpty", "", "removeListener", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    static final class MulticastConsumer implements androidx.core.util.Consumer<WindowLayoutInfo> {
        private final Context context;
        private androidx.window.layout.WindowLayoutInfo lastKnownValue;
        private final ReentrantLock multicastConsumerLock;
        private final Set<androidx.core.util.Consumer<androidx.window.layout.WindowLayoutInfo>> registeredListeners;

        public MulticastConsumer(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            this.context = context;
            this.multicastConsumerLock = new ReentrantLock();
            this.registeredListeners = new LinkedHashSet();
        }

        @Override // androidx.core.util.Consumer
        public void accept(WindowLayoutInfo value) {
            Intrinsics.checkNotNullParameter(value, "value");
            ReentrantLock reentrantLock = this.multicastConsumerLock;
            reentrantLock.lock();
            try {
                this.lastKnownValue = ExtensionsWindowLayoutInfoAdapter.INSTANCE.translate$window_release(this.context, value);
                Iterator<T> it = this.registeredListeners.iterator();
                while (it.hasNext()) {
                    ((androidx.core.util.Consumer) it.next()).accept(this.lastKnownValue);
                }
                Unit unit = Unit.INSTANCE;
            } finally {
                reentrantLock.unlock();
            }
        }

        public final void addListener(androidx.core.util.Consumer<androidx.window.layout.WindowLayoutInfo> listener) {
            Intrinsics.checkNotNullParameter(listener, "listener");
            ReentrantLock reentrantLock = this.multicastConsumerLock;
            reentrantLock.lock();
            try {
                androidx.window.layout.WindowLayoutInfo windowLayoutInfo = this.lastKnownValue;
                if (windowLayoutInfo != null) {
                    listener.accept(windowLayoutInfo);
                }
                this.registeredListeners.add(listener);
            } finally {
                reentrantLock.unlock();
            }
        }

        public final void removeListener(androidx.core.util.Consumer<androidx.window.layout.WindowLayoutInfo> listener) {
            Intrinsics.checkNotNullParameter(listener, "listener");
            ReentrantLock reentrantLock = this.multicastConsumerLock;
            reentrantLock.lock();
            try {
                this.registeredListeners.remove(listener);
            } finally {
                reentrantLock.unlock();
            }
        }

        public final boolean isEmpty() {
            return this.registeredListeners.isEmpty();
        }
    }
}
