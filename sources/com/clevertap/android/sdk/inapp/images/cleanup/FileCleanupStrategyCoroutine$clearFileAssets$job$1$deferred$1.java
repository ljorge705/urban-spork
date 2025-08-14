package com.clevertap.android.sdk.inapp.images.cleanup;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: FileCleanupStrategyCoroutine.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = 48)
@DebugMetadata(c = "com.clevertap.android.sdk.inapp.images.cleanup.FileCleanupStrategyCoroutine$clearFileAssets$job$1$deferred$1", f = "FileCleanupStrategyCoroutine.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes5.dex */
final class FileCleanupStrategyCoroutine$clearFileAssets$job$1$deferred$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1<String, Unit> $successBlock;
    final /* synthetic */ String $url;
    int label;
    final /* synthetic */ FileCleanupStrategyCoroutine this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    FileCleanupStrategyCoroutine$clearFileAssets$job$1$deferred$1(FileCleanupStrategyCoroutine fileCleanupStrategyCoroutine, String str, Function1<? super String, Unit> function1, Continuation<? super FileCleanupStrategyCoroutine$clearFileAssets$job$1$deferred$1> continuation) {
        super(2, continuation);
        this.this$0 = fileCleanupStrategyCoroutine;
        this.$url = str;
        this.$successBlock = function1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new FileCleanupStrategyCoroutine$clearFileAssets$job$1$deferred$1(this.this$0, this.$url, this.$successBlock, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((FileCleanupStrategyCoroutine$clearFileAssets$job$1$deferred$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            this.this$0.getFileResourceProvider().deleteData(this.$url);
            this.$successBlock.invoke(this.$url);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
