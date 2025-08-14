package com.onfido.android.sdk.capture.ui.camera.document;

import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.internal.util.logging.Timber;
import com.onfido.android.sdk.capture.ui.camera.rx.DocumentCaptureDelayTransformer;
import com.onfido.android.sdk.capture.utils.CountryCode;
import com.onfido.api.client.data.DocSide;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureViewModel$prepareCaptureStart$1", f = "DocumentCaptureViewModel.kt", i = {}, l = {426}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class DocumentCaptureViewModel$prepareCaptureStart$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ boolean $isFirstStart;
    int label;
    final /* synthetic */ DocumentCaptureViewModel this$0;

    @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u00022\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/onfido/android/sdk/capture/ui/camera/rx/DocumentCaptureDelayTransformer$DocumentCaptureResult;", "it", ""}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureViewModel$prepareCaptureStart$1$2", f = "DocumentCaptureViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureViewModel$prepareCaptureStart$1$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function3<FlowCollector<? super DocumentCaptureDelayTransformer.DocumentCaptureResult>, Throwable, Continuation<? super Unit>, Object> {
        int label;
        final /* synthetic */ DocumentCaptureViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(DocumentCaptureViewModel documentCaptureViewModel, Continuation<? super AnonymousClass2> continuation) {
            super(3, continuation);
            this.this$0 = documentCaptureViewModel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            this.this$0.getImageProcessingFinished$onfido_capture_sdk_core_release().tryEmit(Boxing.boxBoolean(true));
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super DocumentCaptureDelayTransformer.DocumentCaptureResult> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            return new AnonymousClass2(this.this$0, continuation).invokeSuspend(Unit.INSTANCE);
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0004\u001a\u00020\u0005H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/onfido/android/sdk/capture/ui/camera/rx/DocumentCaptureDelayTransformer$DocumentCaptureResult;", "it", ""}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureViewModel$prepareCaptureStart$1$3", f = "DocumentCaptureViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureViewModel$prepareCaptureStart$1$3, reason: invalid class name */
    static final class AnonymousClass3 extends SuspendLambda implements Function3<FlowCollector<? super DocumentCaptureDelayTransformer.DocumentCaptureResult>, Throwable, Continuation<? super Unit>, Object> {
        /* synthetic */ Object L$0;
        int label;

        AnonymousClass3(Continuation<? super AnonymousClass3> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            Timber.INSTANCE.e((Throwable) this.L$0, "Error on glare detector", new Object[0]);
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super DocumentCaptureDelayTransformer.DocumentCaptureResult> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            AnonymousClass3 anonymousClass3 = new AnonymousClass3(continuation);
            anonymousClass3.L$0 = th;
            return anonymousClass3.invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DocumentCaptureViewModel$prepareCaptureStart$1(DocumentCaptureViewModel documentCaptureViewModel, boolean z, Continuation<? super DocumentCaptureViewModel$prepareCaptureStart$1> continuation) {
        super(2, continuation);
        this.this$0 = documentCaptureViewModel;
        this.$isFirstStart = z;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new DocumentCaptureViewModel$prepareCaptureStart$1(this.this$0, this.$isFirstStart, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            DocumentCaptureDelayTransformer documentCaptureDelayTransformer = this.this$0.documentDelayTransformer;
            boolean z = this.$isFirstStart;
            boolean z2 = this.this$0.edgeDetectionFallbackTimerReached != null;
            DocumentType documentType$onfido_capture_sdk_core_release = this.this$0.getDocumentType$onfido_capture_sdk_core_release();
            CountryCode countryCode = this.this$0.getCountryCode();
            DocSide documentSide = this.this$0.getDocumentSide();
            final DocumentCaptureViewModel documentCaptureViewModel = this.this$0;
            Flow flowOnEach = FlowKt.onEach(FlowKt.m7628catch(FlowKt.onCompletion(documentCaptureDelayTransformer.getImageProcessingTransformedFlow$onfido_capture_sdk_core_release(z, z2, documentType$onfido_capture_sdk_core_release, countryCode, documentSide, new Function0<Unit>() { // from class: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureViewModel$prepareCaptureStart$1.1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    documentCaptureViewModel.startManualFallbackTimer();
                }
            }, this.$isFirstStart, this.this$0.shouldEnableAccessibilityCapture$onfido_capture_sdk_core_release()), new AnonymousClass2(this.this$0, null)), new AnonymousClass3(null)), this.this$0.getDocumentCaptureResultConsumer$onfido_capture_sdk_core_release());
            this.label = 1;
            if (FlowKt.collect(flowOnEach, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DocumentCaptureViewModel$prepareCaptureStart$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
