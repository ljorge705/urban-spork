package com.onfido.android.sdk.capture.ui.proofOfAddress.documentSubmission;

import android.graphics.Bitmap;
import com.onfido.javax.inject.Inject;
import io.reactivex.rxjava3.core.Single;
import java.io.ByteArrayOutputStream;
import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 \b2\u00020\u0001:\u0001\bB\u0007\b\u0007¢\u0006\u0002\u0010\u0002J\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0086\u0002¨\u0006\t"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/proofOfAddress/documentSubmission/CompressPoaDocumentUseCase;", "", "()V", "invoke", "Lio/reactivex/rxjava3/core/Single;", "", "bitmap", "Landroid/graphics/Bitmap;", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class CompressPoaDocumentUseCase {
    private static final Companion Companion = new Companion(null);
    private static final int QUALITY_PERCENTAGE = 100;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/proofOfAddress/documentSubmission/CompressPoaDocumentUseCase$Companion;", "", "()V", "QUALITY_PERCENTAGE", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Inject
    public CompressPoaDocumentUseCase() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final byte[] invoke$lambda$0(Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "$bitmap");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public final Single<byte[]> invoke(final Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        Single<byte[]> singleFromCallable = Single.fromCallable(new Callable() { // from class: com.onfido.android.sdk.capture.ui.proofOfAddress.documentSubmission.CompressPoaDocumentUseCase$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return CompressPoaDocumentUseCase.invoke$lambda$0(bitmap);
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleFromCallable, "fromCallable(...)");
        return singleFromCallable;
    }
}
