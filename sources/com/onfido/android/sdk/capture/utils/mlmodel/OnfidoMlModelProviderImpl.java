package com.onfido.android.sdk.capture.utils.mlmodel;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import androidx.exifinterface.media.ExifInterface;
import com.ReactNativeBlobUtil.ReactNativeBlobUtilConst;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.CaptureTracker;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.utils.TimeProvider;
import com.onfido.javax.inject.Inject;
import io.sentry.protocol.Device;
import java.io.File;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB1\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u001c\u0010\u0011\u001a\u0002H\u0012\"\u0004\b\u0000\u0010\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0096@¢\u0006\u0002\u0010\u0015J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J!\u0010\u0018\u001a\u0004\u0018\u0001H\u0012\"\u0004\b\u0000\u0010\u0012*\u00020\u00192\u0006\u0010\u0013\u001a\u00020\u0014H\u0002¢\u0006\u0002\u0010\u001aR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/onfido/android/sdk/capture/utils/mlmodel/OnfidoMlModelProviderImpl;", "Lcom/onfido/android/sdk/capture/utils/mlmodel/OnfidoMlModelProvider;", "context", "Landroid/content/Context;", "captureTracker", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/CaptureTracker;", "timeProvider", "Lcom/onfido/android/sdk/capture/utils/TimeProvider;", "documentDetectorFactory", "Lcom/onfido/android/sdk/capture/utils/mlmodel/OnfidoDocumentDetectorFactory;", "remoteConfig", "Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;", "(Landroid/content/Context;Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/CaptureTracker;Lcom/onfido/android/sdk/capture/utils/TimeProvider;Lcom/onfido/android/sdk/capture/utils/mlmodel/OnfidoDocumentDetectorFactory;Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;)V", "modelUri", "Landroid/net/Uri;", "threshold", "", "getModel", ExifInterface.GPS_DIRECTION_TRUE, Device.JsonKeys.MODEL, "Lcom/onfido/android/sdk/capture/utils/mlmodel/OnfidoMlModels;", "(Lcom/onfido/android/sdk/capture/utils/mlmodel/OnfidoMlModels;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "requestToDownload", "", "createModel", "Ljava/io/File;", "(Ljava/io/File;Lcom/onfido/android/sdk/capture/utils/mlmodel/OnfidoMlModels;)Ljava/lang/Object;", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class OnfidoMlModelProviderImpl implements OnfidoMlModelProvider {
    private static final Companion Companion = new Companion(null);

    @Deprecated
    public static final long DELAY_BEFORE_RETRY_IN_MS = 500;
    private final CaptureTracker captureTracker;
    private final Context context;
    private final OnfidoDocumentDetectorFactory documentDetectorFactory;
    private final Uri modelUri;
    private final float threshold;
    private final TimeProvider timeProvider;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/onfido/android/sdk/capture/utils/mlmodel/OnfidoMlModelProviderImpl$Companion;", "", "()V", "DELAY_BEFORE_RETRY_IN_MS", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.onfido.android.sdk.capture.utils.mlmodel.OnfidoMlModelProviderImpl", f = "OnfidoMlModelProviderImpl.kt", i = {0, 0, 0, 0}, l = {40}, m = "getModel", n = {"this", Device.JsonKeys.MODEL, "modelFile", "startTime"}, s = {"L$0", "L$1", "L$2", "J$0"})
    /* renamed from: com.onfido.android.sdk.capture.utils.mlmodel.OnfidoMlModelProviderImpl$getModel$1, reason: invalid class name */
    static final class AnonymousClass1<T> extends ContinuationImpl {
        long J$0;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return OnfidoMlModelProviderImpl.this.getModel(null, this);
        }
    }

    @Inject
    public OnfidoMlModelProviderImpl(Context context, CaptureTracker captureTracker, TimeProvider timeProvider, OnfidoDocumentDetectorFactory documentDetectorFactory, OnfidoRemoteConfig remoteConfig) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(captureTracker, "captureTracker");
        Intrinsics.checkNotNullParameter(timeProvider, "timeProvider");
        Intrinsics.checkNotNullParameter(documentDetectorFactory, "documentDetectorFactory");
        Intrinsics.checkNotNullParameter(remoteConfig, "remoteConfig");
        this.context = context;
        this.captureTracker = captureTracker;
        this.timeProvider = timeProvider;
        this.documentDetectorFactory = documentDetectorFactory;
        this.threshold = remoteConfig.getDocumentDetectionExperiment().getThreshold();
        Uri uri = Uri.parse(ReactNativeBlobUtilConst.FILE_PREFIX_CONTENT + context.getPackageName() + ".onfidomodelprovider");
        Intrinsics.checkNotNullExpressionValue(uri, "parse(...)");
        this.modelUri = uri;
    }

    private final <T> T createModel(File file, OnfidoMlModels onfidoMlModels) {
        if (file.exists()) {
            return onfidoMlModels == OnfidoMlModels.DOCUMENT_DETECTION ? (T) this.documentDetectorFactory.create(file, this.threshold) : (T) Unit.INSTANCE;
        }
        return null;
    }

    private final void requestToDownload(OnfidoMlModels model) {
        Cursor cursorQuery = this.context.getContentResolver().query(this.modelUri, null, model.name(), null, null, null);
        if (cursorQuery != null) {
            cursorQuery.close();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // com.onfido.android.sdk.capture.utils.mlmodel.OnfidoMlModelProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public <T> java.lang.Object getModel(com.onfido.android.sdk.capture.utils.mlmodel.OnfidoMlModels r10, kotlin.coroutines.Continuation<? super T> r11) {
        /*
            r9 = this;
            boolean r0 = r11 instanceof com.onfido.android.sdk.capture.utils.mlmodel.OnfidoMlModelProviderImpl.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r11
            com.onfido.android.sdk.capture.utils.mlmodel.OnfidoMlModelProviderImpl$getModel$1 r0 = (com.onfido.android.sdk.capture.utils.mlmodel.OnfidoMlModelProviderImpl.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.onfido.android.sdk.capture.utils.mlmodel.OnfidoMlModelProviderImpl$getModel$1 r0 = new com.onfido.android.sdk.capture.utils.mlmodel.OnfidoMlModelProviderImpl$getModel$1
            r0.<init>(r11)
        L18:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3f
            if (r2 != r3) goto L37
            long r4 = r0.J$0
            java.lang.Object r10 = r0.L$2
            java.io.File r10 = (java.io.File) r10
            java.lang.Object r2 = r0.L$1
            com.onfido.android.sdk.capture.utils.mlmodel.OnfidoMlModels r2 = (com.onfido.android.sdk.capture.utils.mlmodel.OnfidoMlModels) r2
            java.lang.Object r6 = r0.L$0
            com.onfido.android.sdk.capture.utils.mlmodel.OnfidoMlModelProviderImpl r6 = (com.onfido.android.sdk.capture.utils.mlmodel.OnfidoMlModelProviderImpl) r6
            kotlin.ResultKt.throwOnFailure(r11)
            goto L5a
        L37:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L3f:
            kotlin.ResultKt.throwOnFailure(r11)
            com.onfido.android.sdk.capture.utils.TimeProvider r11 = r9.timeProvider
            long r4 = r11.getCurrentTimestamp()
            java.io.File r11 = new java.io.File
            android.content.Context r2 = r9.context
            java.io.File r2 = r2.getCacheDir()
            java.lang.String r6 = r10.getAssetName()
            r11.<init>(r2, r6)
            r6 = r9
            r2 = r10
            r10 = r11
        L5a:
            java.lang.Object r11 = r6.createModel(r10, r2)
            if (r11 == 0) goto L6d
            com.onfido.android.sdk.capture.utils.TimeProvider r10 = r6.timeProvider
            long r0 = r10.getCurrentTimestamp()
            long r0 = r0 - r4
            com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.CaptureTracker r10 = r6.captureTracker
            r10.trackOnfidoMlModelReady$onfido_capture_sdk_core_release(r2, r0)
            return r11
        L6d:
            r6.requestToDownload(r2)
            r0.L$0 = r6
            r0.L$1 = r2
            r0.L$2 = r10
            r0.J$0 = r4
            r0.label = r3
            r7 = 500(0x1f4, double:2.47E-321)
            java.lang.Object r11 = kotlinx.coroutines.DelayKt.delay(r7, r0)
            if (r11 != r1) goto L5a
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onfido.android.sdk.capture.utils.mlmodel.OnfidoMlModelProviderImpl.getModel(com.onfido.android.sdk.capture.utils.mlmodel.OnfidoMlModels, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
