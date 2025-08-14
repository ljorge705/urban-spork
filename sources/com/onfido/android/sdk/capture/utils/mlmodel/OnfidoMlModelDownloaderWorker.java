package com.onfido.android.sdk.capture.utils.mlmodel;

import android.content.Context;
import androidx.work.CoroutineWorker;
import androidx.work.WorkerParameters;
import java.io.File;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.Duration;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\u0007\u001a\u00020\bH\u0096@¢\u0006\u0002\u0010\tJ\u001e\u0010\n\u001a\u00020\u000b*\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0012"}, d2 = {"Lcom/onfido/android/sdk/capture/utils/mlmodel/OnfidoMlModelDownloaderWorker;", "Landroidx/work/CoroutineWorker;", "context", "Landroid/content/Context;", "workerParams", "Landroidx/work/WorkerParameters;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "doWork", "Landroidx/work/ListenableWorker$Result;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isOlderThan", "", "Ljava/io/File;", "duration", "Lkotlin/time/Duration;", "isOlderThan-HG0u8IE", "(Ljava/io/File;J)Z", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class OnfidoMlModelDownloaderWorker extends CoroutineWorker {
    private static final long CACHE_EXPIRE_IN;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String MODEL_ASSET_NAME_KEY = "model_name";
    public static final String MODEL_ASSET_URL_KEY = "asset_url";

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0019\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\b\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u000b"}, d2 = {"Lcom/onfido/android/sdk/capture/utils/mlmodel/OnfidoMlModelDownloaderWorker$Companion;", "", "()V", "CACHE_EXPIRE_IN", "Lkotlin/time/Duration;", "getCACHE_EXPIRE_IN-UwyO8pc", "()J", "J", "MODEL_ASSET_NAME_KEY", "", "MODEL_ASSET_URL_KEY", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        /* renamed from: getCACHE_EXPIRE_IN-UwyO8pc, reason: not valid java name */
        public final long m5743getCACHE_EXPIRE_INUwyO8pc() {
            return OnfidoMlModelDownloaderWorker.CACHE_EXPIRE_IN;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.onfido.android.sdk.capture.utils.mlmodel.OnfidoMlModelDownloaderWorker", f = "OnfidoMlModelDownloaderWorker.kt", i = {0}, l = {39}, m = "doWork", n = {"downloadedModel"}, s = {"L$0"})
    /* renamed from: com.onfido.android.sdk.capture.utils.mlmodel.OnfidoMlModelDownloaderWorker$doWork$1, reason: invalid class name */
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return OnfidoMlModelDownloaderWorker.this.doWork(this);
        }
    }

    static {
        Duration.Companion companion = Duration.INSTANCE;
        CACHE_EXPIRE_IN = DurationKt.toDuration(7, DurationUnit.DAYS);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OnfidoMlModelDownloaderWorker(Context context, WorkerParameters workerParams) {
        super(context, workerParams);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(workerParams, "workerParams");
    }

    /* renamed from: isOlderThan-HG0u8IE, reason: not valid java name */
    private final boolean m5742isOlderThanHG0u8IE(File file, long j) {
        return System.currentTimeMillis() - file.lastModified() > Duration.m7440getInWholeMillisecondsimpl(j);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // androidx.work.CoroutineWorker
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object doWork(kotlin.coroutines.Continuation<? super androidx.work.ListenableWorker.Result> r10) throws java.io.IOException {
        /*
            r9 = this;
            boolean r0 = r10 instanceof com.onfido.android.sdk.capture.utils.mlmodel.OnfidoMlModelDownloaderWorker.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r10
            com.onfido.android.sdk.capture.utils.mlmodel.OnfidoMlModelDownloaderWorker$doWork$1 r0 = (com.onfido.android.sdk.capture.utils.mlmodel.OnfidoMlModelDownloaderWorker.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.onfido.android.sdk.capture.utils.mlmodel.OnfidoMlModelDownloaderWorker$doWork$1 r0 = new com.onfido.android.sdk.capture.utils.mlmodel.OnfidoMlModelDownloaderWorker$doWork$1
            r0.<init>(r10)
        L18:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            java.lang.String r3 = "success(...)"
            r4 = 1
            if (r2 == 0) goto L38
            if (r2 != r4) goto L30
            java.lang.Object r0 = r0.L$0
            java.io.File r0 = (java.io.File) r0
            kotlin.ResultKt.throwOnFailure(r10)
            goto La5
        L30:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r0)
            throw r10
        L38:
            kotlin.ResultKt.throwOnFailure(r10)
            androidx.work.Data r10 = r9.getInputData()
            java.lang.String r2 = "model_name"
            java.lang.String r10 = r10.getString(r2)
            java.lang.String r2 = ""
            if (r10 != 0) goto L4a
            r10 = r2
        L4a:
            androidx.work.Data r5 = r9.getInputData()
            java.lang.String r6 = "asset_url"
            java.lang.String r5 = r5.getString(r6)
            if (r5 != 0) goto L57
            goto L58
        L57:
            r2 = r5
        L58:
            android.content.Context r5 = r9.getApplicationContext()
            java.io.File r5 = r5.getCacheDir()
            java.io.File r6 = new java.io.File
            r6.<init>(r5, r10)
            boolean r5 = r6.exists()
            if (r5 == 0) goto L7f
            long r7 = com.onfido.android.sdk.capture.utils.mlmodel.OnfidoMlModelDownloaderWorker.CACHE_EXPIRE_IN
            boolean r5 = r9.m5742isOlderThanHG0u8IE(r6, r7)
            if (r5 == 0) goto L77
            r6.delete()
            goto L7f
        L77:
            androidx.work.ListenableWorker$Result r10 = androidx.work.ListenableWorker.Result.success()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r10, r3)
            return r10
        L7f:
            retrofit2.Retrofit$Builder r5 = new retrofit2.Retrofit$Builder
            r5.<init>()
            retrofit2.Retrofit$Builder r2 = r5.baseUrl(r2)
            retrofit2.Retrofit r2 = r2.build()
            java.lang.String r5 = "build(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r5)
            java.lang.Class<com.onfido.android.sdk.capture.internal.analytics.inhouse.network.OnfidoAssetsApi> r5 = com.onfido.android.sdk.capture.internal.analytics.inhouse.network.OnfidoAssetsApi.class
            java.lang.Object r2 = r2.create(r5)
            com.onfido.android.sdk.capture.internal.analytics.inhouse.network.OnfidoAssetsApi r2 = (com.onfido.android.sdk.capture.internal.analytics.inhouse.network.OnfidoAssetsApi) r2
            r0.L$0 = r6
            r0.label = r4
            java.lang.Object r10 = r2.getModel(r10, r0)
            if (r10 != r1) goto La4
            return r1
        La4:
            r0 = r6
        La5:
            okhttp3.ResponseBody r10 = (okhttp3.ResponseBody) r10
            byte[] r10 = r10.bytes()
            int r1 = r10.length
            java.nio.ByteBuffer r1 = java.nio.ByteBuffer.allocateDirect(r1)
            r1.put(r10)
            r1.flip()
            java.io.FileOutputStream r1 = new java.io.FileOutputStream
            r1.<init>(r0)
            r1.write(r10)     // Catch: java.lang.Throwable -> Lc5
            kotlin.Unit r10 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> Lc5
            r10 = 0
            kotlin.io.CloseableKt.closeFinally(r1, r10)
            goto L77
        Lc5:
            r10 = move-exception
            throw r10     // Catch: java.lang.Throwable -> Lc7
        Lc7:
            r0 = move-exception
            kotlin.io.CloseableKt.closeFinally(r1, r10)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onfido.android.sdk.capture.utils.mlmodel.OnfidoMlModelDownloaderWorker.doWork(kotlin.coroutines.Continuation):java.lang.Object");
    }
}
