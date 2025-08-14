package com.clevertap.android.sdk.inapp.images.preload;

import com.clevertap.android.sdk.inapp.data.CtCacheType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.TimeoutKt;

/* compiled from: FilePreloaderCoroutine.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = 48)
@DebugMetadata(c = "com.clevertap.android.sdk.inapp.images.preload.FilePreloaderCoroutine$preloadAssets$job$1", f = "FilePreloaderCoroutine.kt", i = {0}, l = {99}, m = "invokeSuspend", n = {"results"}, s = {"L$0"})
/* loaded from: classes5.dex */
final class FilePreloaderCoroutine$preloadAssets$job$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1<Pair<String, ? extends CtCacheType>, Object> $assetBlock;
    final /* synthetic */ Function1<Pair<String, ? extends CtCacheType>, Unit> $failureBlock;
    final /* synthetic */ Function1<Map<String, Boolean>, Unit> $preloadFinished;
    final /* synthetic */ Function1<Pair<String, ? extends CtCacheType>, Unit> $startedBlock;
    final /* synthetic */ Function1<Pair<String, ? extends CtCacheType>, Unit> $successBlock;
    final /* synthetic */ List<Pair<String, CtCacheType>> $urlMetas;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ FilePreloaderCoroutine this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    FilePreloaderCoroutine$preloadAssets$job$1(List<? extends Pair<String, ? extends CtCacheType>> list, FilePreloaderCoroutine filePreloaderCoroutine, Function1<? super Map<String, Boolean>, Unit> function1, Function1<? super Pair<String, ? extends CtCacheType>, Unit> function12, Function1<? super Pair<String, ? extends CtCacheType>, ? extends Object> function13, Function1<? super Pair<String, ? extends CtCacheType>, Unit> function14, Function1<? super Pair<String, ? extends CtCacheType>, Unit> function15, Continuation<? super FilePreloaderCoroutine$preloadAssets$job$1> continuation) {
        super(2, continuation);
        this.$urlMetas = list;
        this.this$0 = filePreloaderCoroutine;
        this.$preloadFinished = function1;
        this.$startedBlock = function12;
        this.$assetBlock = function13;
        this.$successBlock = function14;
        this.$failureBlock = function15;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        FilePreloaderCoroutine$preloadAssets$job$1 filePreloaderCoroutine$preloadAssets$job$1 = new FilePreloaderCoroutine$preloadAssets$job$1(this.$urlMetas, this.this$0, this.$preloadFinished, this.$startedBlock, this.$assetBlock, this.$successBlock, this.$failureBlock, continuation);
        filePreloaderCoroutine$preloadAssets$job$1.L$0 = obj;
        return filePreloaderCoroutine$preloadAssets$job$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((FilePreloaderCoroutine$preloadAssets$job$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object objWithTimeoutOrNull;
        Map<String, Boolean> map;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            map = (Map) this.L$0;
            ResultKt.throwOnFailure(obj);
            objWithTimeoutOrNull = obj;
        } else {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            ArrayList arrayList = new ArrayList();
            List<Pair<String, CtCacheType>> list = this.$urlMetas;
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                arrayList2.add(TuplesKt.to(((Pair) it.next()).getFirst(), Boxing.boxBoolean(false)));
            }
            ArrayList<Pair> arrayList3 = arrayList2;
            LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(arrayList3, 10)), 16));
            for (Pair pair : arrayList3) {
                Pair pair2 = TuplesKt.to(pair.getFirst(), pair.getSecond());
                linkedHashMap.put(pair2.getFirst(), pair2.getSecond());
            }
            Map<String, Boolean> mutableMap = MapsKt.toMutableMap(linkedHashMap);
            List<Pair<String, CtCacheType>> list2 = this.$urlMetas;
            FilePreloaderCoroutine filePreloaderCoroutine = this.this$0;
            Function1<Pair<String, ? extends CtCacheType>, Unit> function1 = this.$startedBlock;
            Function1<Pair<String, ? extends CtCacheType>, Object> function12 = this.$assetBlock;
            Function1<Pair<String, ? extends CtCacheType>, Unit> function13 = this.$successBlock;
            Function1<Pair<String, ? extends CtCacheType>, Unit> function14 = this.$failureBlock;
            Iterator<T> it2 = list2.iterator();
            while (it2.hasNext()) {
                Function1<Pair<String, ? extends CtCacheType>, Unit> function15 = function14;
                arrayList.add(BuildersKt__Builders_commonKt.async$default(coroutineScope, null, null, new FilePreloaderCoroutine$preloadAssets$job$1$1$deferred$1(filePreloaderCoroutine, (Pair) it2.next(), function1, mutableMap, function12, function13, function15, null), 3, null));
                filePreloaderCoroutine = filePreloaderCoroutine;
                mutableMap = mutableMap;
                function13 = function13;
                function12 = function12;
                function1 = function1;
                function14 = function15;
            }
            Map<String, Boolean> map2 = mutableMap;
            this.L$0 = map2;
            this.label = 1;
            objWithTimeoutOrNull = TimeoutKt.withTimeoutOrNull(this.this$0.getTimeoutForPreload(), new FilePreloaderCoroutine$preloadAssets$job$1$pairs$1(arrayList, null), this);
            if (objWithTimeoutOrNull == coroutine_suspended) {
                return coroutine_suspended;
            }
            map = map2;
        }
        List list3 = (List) objWithTimeoutOrNull;
        if (list3 != null) {
            this.$preloadFinished.invoke(MapsKt.toMap(list3));
        } else {
            this.$preloadFinished.invoke(map);
        }
        return Unit.INSTANCE;
    }
}
