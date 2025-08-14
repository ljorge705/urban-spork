package com.onfido.android.sdk.capture.internal.usecase.validation;

import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.detector.rectangle.RectangleTransformer;
import com.onfido.android.sdk.capture.internal.usecase.DocumentPosition;
import com.onfido.android.sdk.capture.internal.usecase.DocumentPositionUseCase;
import com.onfido.android.sdk.capture.internal.usecase.validation.DocumentValidationResult;
import com.onfido.android.sdk.capture.internal.util.DispatchersProvider;
import com.onfido.android.sdk.capture.native_detector.NativeDetector;
import com.onfido.android.sdk.capture.ui.camera.DocumentDetectionFrame;
import com.onfido.android.sdk.capture.utils.TimeProvider;
import com.onfido.android.sdk.capture.utils.mlmodel.OnfidoDocumentDetector;
import com.onfido.android.sdk.capture.utils.mlmodel.OnfidoMlDocument;
import com.onfido.android.sdk.capture.utils.mlmodel.OnfidoMlModelProvider;
import com.onfido.api.client.data.DocSide;
import com.onfido.javax.inject.Inject;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.Duration;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.SharedFlowKt;

@Metadata(d1 = {"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0010\u0018\u00002\u00020\u0001B7\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\u001e\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)H\u0096B¢\u0006\u0002\u0010*J\u0010\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\u0017H\u0012J\u0016\u0010.\u001a\u00020\u0011*\u0004\u0018\u00010/2\u0006\u0010(\u001a\u00020)H\u0012J$\u00100\u001a\u00020\u0011*\u0002012\u0006\u00102\u001a\u00020,2\u0006\u00103\u001a\u00020,2\u0006\u0010(\u001a\u00020)H\u0012J\u000e\u00104\u001a\u0004\u0018\u000105*\u000206H\u0012R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0092\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0016\u001a\u00020\u0017X\u0092\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u000e\u0010\u001a\u001a\u00020\u001bX\u0092.¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0092\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0092\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u00020\rX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0092\u0004¢\u0006\u0002\n\u0000R\u001a\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00110!X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#¨\u00067"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/usecase/validation/DocumentValidationUseCase;", "", "onfidoMlModelProvider", "Lcom/onfido/android/sdk/capture/utils/mlmodel/OnfidoMlModelProvider;", "transformer", "Lcom/onfido/android/sdk/capture/detector/rectangle/RectangleTransformer;", "documentPositionUseCase", "Lcom/onfido/android/sdk/capture/internal/usecase/DocumentPositionUseCase;", "nativeDetector", "Lcom/onfido/android/sdk/capture/native_detector/NativeDetector;", "dispatchersProvider", "Lcom/onfido/android/sdk/capture/internal/util/DispatchersProvider;", "timeProvider", "Lcom/onfido/android/sdk/capture/utils/TimeProvider;", "(Lcom/onfido/android/sdk/capture/utils/mlmodel/OnfidoMlModelProvider;Lcom/onfido/android/sdk/capture/detector/rectangle/RectangleTransformer;Lcom/onfido/android/sdk/capture/internal/usecase/DocumentPositionUseCase;Lcom/onfido/android/sdk/capture/native_detector/NativeDetector;Lcom/onfido/android/sdk/capture/internal/util/DispatchersProvider;Lcom/onfido/android/sdk/capture/utils/TimeProvider;)V", "_validationResult", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lcom/onfido/android/sdk/capture/internal/usecase/validation/DocumentValidationResult;", "getDispatchersProvider", "()Lcom/onfido/android/sdk/capture/internal/util/DispatchersProvider;", "getDocumentPositionUseCase", "()Lcom/onfido/android/sdk/capture/internal/usecase/DocumentPositionUseCase;", "firstSuccessTime", "", "getNativeDetector", "()Lcom/onfido/android/sdk/capture/native_detector/NativeDetector;", "onfidoDocumentDetector", "Lcom/onfido/android/sdk/capture/utils/mlmodel/OnfidoDocumentDetector;", "shouldProcessNextFrame", "Ljava/util/concurrent/atomic/AtomicBoolean;", "getTimeProvider", "()Lcom/onfido/android/sdk/capture/utils/TimeProvider;", "validationResult", "Lkotlinx/coroutines/flow/SharedFlow;", "getValidationResult", "()Lkotlinx/coroutines/flow/SharedFlow;", "invoke", "", "frame", "Lcom/onfido/android/sdk/capture/ui/camera/DocumentDetectionFrame;", "targets", "Lcom/onfido/android/sdk/capture/internal/usecase/validation/DocumentValidationTargets;", "(Lcom/onfido/android/sdk/capture/ui/camera/DocumentDetectionFrame;Lcom/onfido/android/sdk/capture/internal/usecase/validation/DocumentValidationTargets;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isHoldDurationComplete", "", "holdDuration", "checkTargets", "Lcom/onfido/android/sdk/capture/utils/mlmodel/OnfidoMlDocument;", "mapToValidationResult", "Lcom/onfido/android/sdk/capture/internal/usecase/DocumentPosition;", "hasBlur", "hasGlare", "toDocSide", "Lcom/onfido/api/client/data/DocSide;", "Lcom/onfido/android/sdk/capture/utils/mlmodel/OnfidoMlDocument$Side;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public class DocumentValidationUseCase {
    private final MutableSharedFlow<DocumentValidationResult> _validationResult;
    private final DispatchersProvider dispatchersProvider;
    private final DocumentPositionUseCase documentPositionUseCase;
    private long firstSuccessTime;
    private final NativeDetector nativeDetector;
    private OnfidoDocumentDetector onfidoDocumentDetector;
    private final OnfidoMlModelProvider onfidoMlModelProvider;
    private AtomicBoolean shouldProcessNextFrame;
    private final TimeProvider timeProvider;
    private final RectangleTransformer transformer;
    private final SharedFlow<DocumentValidationResult> validationResult;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[OnfidoMlDocument.Side.values().length];
            try {
                iArr[OnfidoMlDocument.Side.FRONT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[OnfidoMlDocument.Side.BACK.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[OnfidoMlDocument.Side.UNKNOWN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.onfido.android.sdk.capture.internal.usecase.validation.DocumentValidationUseCase", f = "DocumentValidationUseCase.kt", i = {0, 0}, l = {71}, m = "invoke$suspendImpl", n = {"$this", "frame"}, s = {"L$0", "L$1"})
    /* renamed from: com.onfido.android.sdk.capture.internal.usecase.validation.DocumentValidationUseCase$invoke$1, reason: invalid class name */
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DocumentValidationUseCase.invoke$suspendImpl(DocumentValidationUseCase.this, null, null, this);
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.onfido.android.sdk.capture.internal.usecase.validation.DocumentValidationUseCase$invoke$2", f = "DocumentValidationUseCase.kt", i = {0, 1, 1, 2, 2, 3, 3}, l = {73, 81, 82, 83, 87, 112}, m = "invokeSuspend", n = {"$this$withContext", "hasBlurDeferred", "hasGlareDeferred", "hasGlareDeferred", "document", "document", "hasBlur"}, s = {"L$0", "L$0", "L$1", "L$0", "L$1", "L$0", "Z$0"})
    /* renamed from: com.onfido.android.sdk.capture.internal.usecase.validation.DocumentValidationUseCase$invoke$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ DocumentDetectionFrame $frame;
        final /* synthetic */ DocumentValidationTargets $targets;
        private /* synthetic */ Object L$0;
        Object L$1;
        boolean Z$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(DocumentValidationTargets documentValidationTargets, DocumentDetectionFrame documentDetectionFrame, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$targets = documentValidationTargets;
            this.$frame = documentDetectionFrame;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = DocumentValidationUseCase.this.new AnonymousClass2(this.$targets, this.$frame, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        /* JADX WARN: Removed duplicated region for block: B:20:0x00b4 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:21:0x00b5  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x00c4  */
        /* JADX WARN: Removed duplicated region for block: B:25:0x00c6  */
        /* JADX WARN: Removed duplicated region for block: B:36:0x0100 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:37:0x0101  */
        /* JADX WARN: Removed duplicated region for block: B:40:0x0118 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:41:0x0119  */
        /* JADX WARN: Removed duplicated region for block: B:44:0x0133  */
        /* JADX WARN: Removed duplicated region for block: B:49:0x0148  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r12) {
            /*
                Method dump skipped, instructions count: 436
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.onfido.android.sdk.capture.internal.usecase.validation.DocumentValidationUseCase.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    @Inject
    public DocumentValidationUseCase(OnfidoMlModelProvider onfidoMlModelProvider, RectangleTransformer transformer, DocumentPositionUseCase documentPositionUseCase, NativeDetector nativeDetector, DispatchersProvider dispatchersProvider, TimeProvider timeProvider) {
        Intrinsics.checkNotNullParameter(onfidoMlModelProvider, "onfidoMlModelProvider");
        Intrinsics.checkNotNullParameter(transformer, "transformer");
        Intrinsics.checkNotNullParameter(documentPositionUseCase, "documentPositionUseCase");
        Intrinsics.checkNotNullParameter(nativeDetector, "nativeDetector");
        Intrinsics.checkNotNullParameter(dispatchersProvider, "dispatchersProvider");
        Intrinsics.checkNotNullParameter(timeProvider, "timeProvider");
        this.onfidoMlModelProvider = onfidoMlModelProvider;
        this.transformer = transformer;
        this.documentPositionUseCase = documentPositionUseCase;
        this.nativeDetector = nativeDetector;
        this.dispatchersProvider = dispatchersProvider;
        this.timeProvider = timeProvider;
        this.shouldProcessNextFrame = new AtomicBoolean(true);
        MutableSharedFlow<DocumentValidationResult> mutableSharedFlowMutableSharedFlow$default = SharedFlowKt.MutableSharedFlow$default(0, 0, null, 7, null);
        this._validationResult = mutableSharedFlowMutableSharedFlow$default;
        this.firstSuccessTime = -1L;
        this.validationResult = FlowKt.asSharedFlow(mutableSharedFlowMutableSharedFlow$default);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public DocumentValidationResult checkTargets(OnfidoMlDocument onfidoMlDocument, DocumentValidationTargets documentValidationTargets) {
        return onfidoMlDocument == null ? DocumentValidationResult.Warning.NoDocument.INSTANCE : (documentValidationTargets.getDocumentType() != DocumentType.PASSPORT || onfidoMlDocument.getSide() == OnfidoMlDocument.Side.FRONT) ? toDocSide(onfidoMlDocument.getSide()) != documentValidationTargets.getDocumentSide() ? new DocumentValidationResult.Warning.WrongSide(documentValidationTargets.getDocumentSide(), toDocSide(onfidoMlDocument.getSide())) : DocumentValidationResult.Success.INSTANCE : new DocumentValidationResult.Warning.WrongDocument(documentValidationTargets.getDocumentType(), null, 2, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static /* synthetic */ java.lang.Object invoke$suspendImpl(com.onfido.android.sdk.capture.internal.usecase.validation.DocumentValidationUseCase r6, com.onfido.android.sdk.capture.ui.camera.DocumentDetectionFrame r7, com.onfido.android.sdk.capture.internal.usecase.validation.DocumentValidationTargets r8, kotlin.coroutines.Continuation<? super kotlin.Unit> r9) {
        /*
            boolean r0 = r9 instanceof com.onfido.android.sdk.capture.internal.usecase.validation.DocumentValidationUseCase.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r9
            com.onfido.android.sdk.capture.internal.usecase.validation.DocumentValidationUseCase$invoke$1 r0 = (com.onfido.android.sdk.capture.internal.usecase.validation.DocumentValidationUseCase.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.onfido.android.sdk.capture.internal.usecase.validation.DocumentValidationUseCase$invoke$1 r0 = new com.onfido.android.sdk.capture.internal.usecase.validation.DocumentValidationUseCase$invoke$1
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L3b
            if (r2 != r4) goto L33
            java.lang.Object r6 = r0.L$1
            r7 = r6
            com.onfido.android.sdk.capture.ui.camera.DocumentDetectionFrame r7 = (com.onfido.android.sdk.capture.ui.camera.DocumentDetectionFrame) r7
            java.lang.Object r6 = r0.L$0
            com.onfido.android.sdk.capture.internal.usecase.validation.DocumentValidationUseCase r6 = (com.onfido.android.sdk.capture.internal.usecase.validation.DocumentValidationUseCase) r6
            kotlin.ResultKt.throwOnFailure(r9)     // Catch: java.lang.Throwable -> L6e java.lang.RuntimeException -> L70
            goto L61
        L33:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L3b:
            kotlin.ResultKt.throwOnFailure(r9)
            java.util.concurrent.atomic.AtomicBoolean r9 = r6.shouldProcessNextFrame
            boolean r9 = r9.getAndSet(r3)
            if (r9 == 0) goto L87
            com.onfido.android.sdk.capture.internal.util.DispatchersProvider r9 = r6.getDispatchersProvider()     // Catch: java.lang.Throwable -> L6e java.lang.RuntimeException -> L70
            kotlinx.coroutines.CoroutineDispatcher r9 = r9.mo5607getDefault()     // Catch: java.lang.Throwable -> L6e java.lang.RuntimeException -> L70
            com.onfido.android.sdk.capture.internal.usecase.validation.DocumentValidationUseCase$invoke$2 r2 = new com.onfido.android.sdk.capture.internal.usecase.validation.DocumentValidationUseCase$invoke$2     // Catch: java.lang.Throwable -> L6e java.lang.RuntimeException -> L70
            r5 = 0
            r2.<init>(r8, r7, r5)     // Catch: java.lang.Throwable -> L6e java.lang.RuntimeException -> L70
            r0.L$0 = r6     // Catch: java.lang.Throwable -> L6e java.lang.RuntimeException -> L70
            r0.L$1 = r7     // Catch: java.lang.Throwable -> L6e java.lang.RuntimeException -> L70
            r0.label = r4     // Catch: java.lang.Throwable -> L6e java.lang.RuntimeException -> L70
            java.lang.Object r8 = kotlinx.coroutines.BuildersKt.withContext(r9, r2, r0)     // Catch: java.lang.Throwable -> L6e java.lang.RuntimeException -> L70
            if (r8 != r1) goto L61
            return r1
        L61:
            android.graphics.Bitmap r7 = r7.getBitmap()
            r7.recycle()
            java.util.concurrent.atomic.AtomicBoolean r6 = r6.shouldProcessNextFrame
            r6.set(r4)
            goto L87
        L6e:
            r8 = move-exception
            goto L7a
        L70:
            com.onfido.android.sdk.capture.internal.util.logging.Timber$Forest r8 = com.onfido.android.sdk.capture.internal.util.logging.Timber.INSTANCE     // Catch: java.lang.Throwable -> L6e
            java.lang.String r9 = "Document Validation Failed"
            java.lang.Object[] r0 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> L6e
            r8.i(r9, r0)     // Catch: java.lang.Throwable -> L6e
            goto L61
        L7a:
            android.graphics.Bitmap r7 = r7.getBitmap()
            r7.recycle()
            java.util.concurrent.atomic.AtomicBoolean r6 = r6.shouldProcessNextFrame
            r6.set(r4)
            throw r8
        L87:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onfido.android.sdk.capture.internal.usecase.validation.DocumentValidationUseCase.invoke$suspendImpl(com.onfido.android.sdk.capture.internal.usecase.validation.DocumentValidationUseCase, com.onfido.android.sdk.capture.ui.camera.DocumentDetectionFrame, com.onfido.android.sdk.capture.internal.usecase.validation.DocumentValidationTargets, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private boolean isHoldDurationComplete(long holdDuration) {
        return this.firstSuccessTime + holdDuration <= getTimeProvider().getCurrentTimestamp();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public DocumentValidationResult mapToValidationResult(DocumentPosition documentPosition, boolean z, boolean z2, DocumentValidationTargets documentValidationTargets) {
        if (Intrinsics.areEqual(documentPosition, DocumentPosition.DocumentNormal.INSTANCE)) {
            if (z) {
                return DocumentValidationResult.Warning.Blur.INSTANCE;
            }
            if (z2) {
                return DocumentValidationResult.Warning.Glare.INSTANCE;
            }
            if (this.firstSuccessTime == -1) {
                this.firstSuccessTime = getTimeProvider().getCurrentTimestamp();
            } else if (isHoldDurationComplete(Duration.m7440getInWholeMillisecondsimpl(documentValidationTargets.m5606getHoldDurationUwyO8pc()))) {
                this.firstSuccessTime = -1L;
                return DocumentValidationResult.Success.INSTANCE;
            }
            return DocumentValidationResult.Hold.INSTANCE;
        }
        if (Intrinsics.areEqual(documentPosition, DocumentPosition.DocumentClose.INSTANCE)) {
            return DocumentValidationResult.Warning.DocumentTooClose.INSTANCE;
        }
        if (Intrinsics.areEqual(documentPosition, DocumentPosition.DocumentFar.INSTANCE)) {
            return DocumentValidationResult.Warning.DocumentTooFar.INSTANCE;
        }
        if (Intrinsics.areEqual(documentPosition, DocumentPosition.DocumentNotFound.INSTANCE)) {
            return DocumentValidationResult.Warning.NoDocument.INSTANCE;
        }
        if (Intrinsics.areEqual(documentPosition, DocumentPosition.DocumentBottom.INSTANCE) || Intrinsics.areEqual(documentPosition, DocumentPosition.DocumentTooBottom.INSTANCE) || Intrinsics.areEqual(documentPosition, DocumentPosition.DocumentTooLeft.INSTANCE) || Intrinsics.areEqual(documentPosition, DocumentPosition.DocumentTooRight.INSTANCE) || Intrinsics.areEqual(documentPosition, DocumentPosition.DocumentTooTop.INSTANCE) || Intrinsics.areEqual(documentPosition, DocumentPosition.DocumentTop.INSTANCE)) {
            return DocumentValidationResult.Warning.CutOff.INSTANCE;
        }
        throw new NoWhenBranchMatchedException();
    }

    private DocSide toDocSide(OnfidoMlDocument.Side side) {
        int i = WhenMappings.$EnumSwitchMapping$0[side.ordinal()];
        if (i == 1) {
            return DocSide.FRONT;
        }
        if (i == 2) {
            return DocSide.BACK;
        }
        if (i == 3) {
            return null;
        }
        throw new NoWhenBranchMatchedException();
    }

    public DispatchersProvider getDispatchersProvider() {
        return this.dispatchersProvider;
    }

    public DocumentPositionUseCase getDocumentPositionUseCase() {
        return this.documentPositionUseCase;
    }

    public NativeDetector getNativeDetector() {
        return this.nativeDetector;
    }

    public TimeProvider getTimeProvider() {
        return this.timeProvider;
    }

    public SharedFlow<DocumentValidationResult> getValidationResult() {
        return this.validationResult;
    }

    public Object invoke(DocumentDetectionFrame documentDetectionFrame, DocumentValidationTargets documentValidationTargets, Continuation<? super Unit> continuation) {
        return invoke$suspendImpl(this, documentDetectionFrame, documentValidationTargets, continuation);
    }
}
