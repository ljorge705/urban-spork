package com.clevertap.android.sdk.inapp.images.preload;

import com.clevertap.android.sdk.ILogger;
import com.clevertap.android.sdk.inapp.data.CtCacheType;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: FilePreloaderCoroutine.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u000b\n\u0002\u0018\u0002\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001*\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "Lkotlin/Pair;", "", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = 48)
@DebugMetadata(c = "com.clevertap.android.sdk.inapp.images.preload.FilePreloaderCoroutine$preloadAssets$job$1$1$deferred$1", f = "FilePreloaderCoroutine.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes5.dex */
final class FilePreloaderCoroutine$preloadAssets$job$1$1$deferred$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Pair<? extends String, ? extends Boolean>>, Object> {
    final /* synthetic */ Function1<Pair<String, ? extends CtCacheType>, Object> $assetBlock;
    final /* synthetic */ Function1<Pair<String, ? extends CtCacheType>, Unit> $failureBlock;
    final /* synthetic */ Pair<String, CtCacheType> $meta;
    final /* synthetic */ Map<String, Boolean> $results;
    final /* synthetic */ Function1<Pair<String, ? extends CtCacheType>, Unit> $startedBlock;
    final /* synthetic */ Function1<Pair<String, ? extends CtCacheType>, Unit> $successBlock;
    int label;
    final /* synthetic */ FilePreloaderCoroutine this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    FilePreloaderCoroutine$preloadAssets$job$1$1$deferred$1(FilePreloaderCoroutine filePreloaderCoroutine, Pair<String, ? extends CtCacheType> pair, Function1<? super Pair<String, ? extends CtCacheType>, Unit> function1, Map<String, Boolean> map, Function1<? super Pair<String, ? extends CtCacheType>, ? extends Object> function12, Function1<? super Pair<String, ? extends CtCacheType>, Unit> function13, Function1<? super Pair<String, ? extends CtCacheType>, Unit> function14, Continuation<? super FilePreloaderCoroutine$preloadAssets$job$1$1$deferred$1> continuation) {
        super(2, continuation);
        this.this$0 = filePreloaderCoroutine;
        this.$meta = pair;
        this.$startedBlock = function1;
        this.$results = map;
        this.$assetBlock = function12;
        this.$successBlock = function13;
        this.$failureBlock = function14;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new FilePreloaderCoroutine$preloadAssets$job$1$1$deferred$1(this.this$0, this.$meta, this.$startedBlock, this.$results, this.$assetBlock, this.$successBlock, this.$failureBlock, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super Pair<? extends String, ? extends Boolean>> continuation) {
        return invoke2(coroutineScope, (Continuation<? super Pair<String, Boolean>>) continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super Pair<String, Boolean>> continuation) {
        return ((FilePreloaderCoroutine$preloadAssets$job$1$1$deferred$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            ILogger logger = this.this$0.getLogger();
            if (logger != null) {
                logger.verbose("started asset url fetch " + this.$meta);
            }
            this.$startedBlock.invoke(this.$meta);
            Ref.BooleanRef booleanRef = new Ref.BooleanRef();
            Function1<Pair<String, ? extends CtCacheType>, Object> function1 = this.$assetBlock;
            Pair<String, CtCacheType> pair = this.$meta;
            Function1<Pair<String, ? extends CtCacheType>, Unit> function12 = this.$successBlock;
            Function1<Pair<String, ? extends CtCacheType>, Unit> function13 = this.$failureBlock;
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (function1.invoke(pair) != null) {
                function12.invoke(pair);
                booleanRef.element = true;
            } else {
                function13.invoke(pair);
                booleanRef.element = false;
            }
            long jCurrentTimeMillis2 = System.currentTimeMillis() - jCurrentTimeMillis;
            ILogger logger2 = this.this$0.getLogger();
            if (logger2 != null) {
                logger2.verbose("finished asset url fetch " + this.$meta + " in " + jCurrentTimeMillis2 + " ms");
            }
            this.$results.put(this.$meta.getFirst(), Boxing.boxBoolean(booleanRef.element));
            return TuplesKt.to(this.$meta.getFirst(), Boxing.boxBoolean(booleanRef.element));
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
