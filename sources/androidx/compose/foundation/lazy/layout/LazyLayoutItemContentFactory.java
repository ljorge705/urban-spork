package androidx.compose.foundation.lazy.layout;

import androidx.compose.foundation.lazy.layout.LazyLayoutItemContentFactory;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.SaveableStateHolder;
import com.clevertap.android.sdk.Constants;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LazyLayoutItemContentFactory.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b\u0001\u0018\u00002\u00020\u0001:\u0001\u0015B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J&\u0010\r\u001a\r\u0012\u0004\u0012\u00020\u000e0\u0005¢\u0006\u0002\b\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0001¢\u0006\u0002\u0010\u0013J\u0012\u0010\u0014\u001a\u0004\u0018\u00010\u00012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001e\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\u0001\u0012\b\u0012\u00060\fR\u00020\u00000\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Landroidx/compose/foundation/lazy/layout/LazyLayoutItemContentFactory;", "", "saveableStateHolder", "Landroidx/compose/runtime/saveable/SaveableStateHolder;", "itemProvider", "Lkotlin/Function0;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutItemProvider;", "(Landroidx/compose/runtime/saveable/SaveableStateHolder;Lkotlin/jvm/functions/Function0;)V", "getItemProvider", "()Lkotlin/jvm/functions/Function0;", "lambdasCache", "", "Landroidx/compose/foundation/lazy/layout/LazyLayoutItemContentFactory$CachedItemContent;", "getContent", "", "Landroidx/compose/runtime/Composable;", "index", "", Constants.KEY_KEY, "(ILjava/lang/Object;)Lkotlin/jvm/functions/Function2;", "getContentType", "CachedItemContent", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class LazyLayoutItemContentFactory {
    private final Function0<LazyLayoutItemProvider> itemProvider;
    private final Map<Object, CachedItemContent> lambdasCache;
    private final SaveableStateHolder saveableStateHolder;

    public final Function0<LazyLayoutItemProvider> getItemProvider() {
        return this.itemProvider;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public LazyLayoutItemContentFactory(SaveableStateHolder saveableStateHolder, Function0<? extends LazyLayoutItemProvider> itemProvider) {
        Intrinsics.checkNotNullParameter(saveableStateHolder, "saveableStateHolder");
        Intrinsics.checkNotNullParameter(itemProvider, "itemProvider");
        this.saveableStateHolder = saveableStateHolder;
        this.itemProvider = itemProvider;
        this.lambdasCache = new LinkedHashMap();
    }

    public final Object getContentType(Object key) {
        CachedItemContent cachedItemContent = this.lambdasCache.get(key);
        if (cachedItemContent != null) {
            return cachedItemContent.getType();
        }
        LazyLayoutItemProvider lazyLayoutItemProviderInvoke = this.itemProvider.invoke();
        Integer num = lazyLayoutItemProviderInvoke.getKeyToIndexMap().get(key);
        if (num != null) {
            return lazyLayoutItemProviderInvoke.getContentType(num.intValue());
        }
        return null;
    }

    public final Function2<Composer, Integer, Unit> getContent(int index, Object key) {
        Intrinsics.checkNotNullParameter(key, "key");
        CachedItemContent cachedItemContent = this.lambdasCache.get(key);
        Object contentType = this.itemProvider.invoke().getContentType(index);
        if (cachedItemContent != null && cachedItemContent.getLastKnownIndex() == index && Intrinsics.areEqual(cachedItemContent.getType(), contentType)) {
            return cachedItemContent.getContent();
        }
        CachedItemContent cachedItemContent2 = new CachedItemContent(this, index, key, contentType);
        this.lambdasCache.put(key, cachedItemContent2);
        return cachedItemContent2.getContent();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: LazyLayoutItemContentFactory.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\b\u0082\u0004\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0001\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0001¢\u0006\u0002\u0010\u0006J\u0018\u0010\u001a\u001a\r\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\b\nH\u0002¢\u0006\u0002\u0010\u000eR\u001d\u0010\u0007\u001a\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010\b¢\u0006\u0002\b\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u001c\u0010\f\u001a\r\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\b\n8F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R+\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00038F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0010¨\u0006\u001b"}, d2 = {"Landroidx/compose/foundation/lazy/layout/LazyLayoutItemContentFactory$CachedItemContent;", "", "initialIndex", "", Constants.KEY_KEY, "type", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutItemContentFactory;ILjava/lang/Object;Ljava/lang/Object;)V", "_content", "Lkotlin/Function0;", "", "Landroidx/compose/runtime/Composable;", "Lkotlin/jvm/functions/Function2;", "content", "getContent", "()Lkotlin/jvm/functions/Function2;", "getKey", "()Ljava/lang/Object;", "<set-?>", "lastKnownIndex", "getLastKnownIndex", "()I", "setLastKnownIndex", "(I)V", "lastKnownIndex$delegate", "Landroidx/compose/runtime/MutableState;", "getType", "createContentLambda", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    final class CachedItemContent {
        private Function2<? super Composer, ? super Integer, Unit> _content;
        private final Object key;

        /* renamed from: lastKnownIndex$delegate, reason: from kotlin metadata */
        private final MutableState lastKnownIndex;
        final /* synthetic */ LazyLayoutItemContentFactory this$0;
        private final Object type;

        public final Object getKey() {
            return this.key;
        }

        public final Object getType() {
            return this.type;
        }

        public CachedItemContent(LazyLayoutItemContentFactory lazyLayoutItemContentFactory, int i, Object key, Object obj) {
            Intrinsics.checkNotNullParameter(key, "key");
            this.this$0 = lazyLayoutItemContentFactory;
            this.key = key;
            this.type = obj;
            this.lastKnownIndex = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Integer.valueOf(i), null, 2, null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void setLastKnownIndex(int i) {
            this.lastKnownIndex.setValue(Integer.valueOf(i));
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final int getLastKnownIndex() {
            return ((Number) this.lastKnownIndex.getValue()).intValue();
        }

        public final Function2<Composer, Integer, Unit> getContent() {
            Function2 function2 = this._content;
            if (function2 != null) {
                return function2;
            }
            Function2<Composer, Integer, Unit> function2CreateContentLambda = createContentLambda();
            this._content = function2CreateContentLambda;
            return function2CreateContentLambda;
        }

        private final Function2<Composer, Integer, Unit> createContentLambda() {
            final LazyLayoutItemContentFactory lazyLayoutItemContentFactory = this.this$0;
            return ComposableLambdaKt.composableLambdaInstance(1403994769, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutItemContentFactory$CachedItemContent$createContentLambda$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i) {
                    final int lastKnownIndex;
                    ComposerKt.sourceInformation(composer, "C102@3898L219:LazyLayoutItemContentFactory.kt#wow0x6");
                    if ((i & 11) != 2 || !composer.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1403994769, i, -1, "androidx.compose.foundation.lazy.layout.LazyLayoutItemContentFactory.CachedItemContent.createContentLambda.<anonymous> (LazyLayoutItemContentFactory.kt:89)");
                        }
                        final LazyLayoutItemProvider lazyLayoutItemProviderInvoke = lazyLayoutItemContentFactory.getItemProvider().invoke();
                        Integer num = lazyLayoutItemProviderInvoke.getKeyToIndexMap().get(this.getKey());
                        if (num != null) {
                            Integer num2 = num;
                            this.setLastKnownIndex(num2.intValue());
                            lastKnownIndex = num2.intValue();
                        } else {
                            lastKnownIndex = this.getLastKnownIndex();
                        }
                        composer.startReplaceableGroup(-715770513);
                        ComposerKt.sourceInformation(composer, "97@3754L99");
                        if (lastKnownIndex < lazyLayoutItemProviderInvoke.getItemCount()) {
                            Object key = lazyLayoutItemProviderInvoke.getKey(lastKnownIndex);
                            if (Intrinsics.areEqual(key, this.getKey())) {
                                lazyLayoutItemContentFactory.saveableStateHolder.SaveableStateProvider(key, ComposableLambdaKt.composableLambda(composer, -1238863364, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutItemContentFactory$CachedItemContent$createContentLambda$1.1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num3) {
                                        invoke(composer2, num3.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer composer2, int i2) {
                                        ComposerKt.sourceInformation(composer2, "C98@3820L11:LazyLayoutItemContentFactory.kt#wow0x6");
                                        if ((i2 & 11) == 2 && composer2.getSkipping()) {
                                            composer2.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-1238863364, i2, -1, "androidx.compose.foundation.lazy.layout.LazyLayoutItemContentFactory.CachedItemContent.createContentLambda.<anonymous>.<anonymous> (LazyLayoutItemContentFactory.kt:97)");
                                        }
                                        lazyLayoutItemProviderInvoke.Item(lastKnownIndex, composer2, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }
                                }), composer, 568);
                            }
                        }
                        composer.endReplaceableGroup();
                        Object key2 = this.getKey();
                        final LazyLayoutItemContentFactory.CachedItemContent cachedItemContent = this;
                        EffectsKt.DisposableEffect(key2, new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutItemContentFactory$CachedItemContent$createContentLambda$1.2
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public final DisposableEffectResult invoke(DisposableEffectScope DisposableEffect) {
                                Intrinsics.checkNotNullParameter(DisposableEffect, "$this$DisposableEffect");
                                final LazyLayoutItemContentFactory.CachedItemContent cachedItemContent2 = cachedItemContent;
                                return new DisposableEffectResult() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutItemContentFactory$CachedItemContent$createContentLambda$1$2$invoke$$inlined$onDispose$1
                                    @Override // androidx.compose.runtime.DisposableEffectResult
                                    public void dispose() {
                                        cachedItemContent2._content = null;
                                    }
                                };
                            }
                        }, composer, 8);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    composer.skipToGroupEnd();
                }
            });
        }
    }
}
